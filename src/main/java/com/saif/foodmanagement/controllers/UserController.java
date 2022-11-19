package com.saif.foodmanagement.controllers;

import com.saif.foodmanagement.model.User;
import com.saif.foodmanagement.services.UserService;
import com.saif.foodmanagement.validation.ValidationGroups.ValidationGroupOne;
import com.saif.foodmanagement.validation.ValidationGroups.ValidationGroupTwo;
import com.saif.foodmanagement.validation.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

import static com.saif.foodmanagement.utils.Utils.*;
import static com.saif.foodmanagement.model.Role.getRoles;

/**
 * @author saifuzzaman
 */
@Controller
@SessionAttributes("user")
@RequestMapping("/user")
public class UserController {

    private static final String COMMAND = "user";
    private static final String ERROR_PAGE = "error";
    private static final String VIEW_ALL_USERS = "showUsers";
    private static final String USERS_VIEW_PAGE = "user/all";
    private static final String USER_FORM_PAGE_ONE = "userFormOne";
    private static final String USER_FORM_PAGE_TWO = "userFormTwo";
    private static final String USER_FORM_PAGE_THREE = "userFormThree";
    private static final String[] disAllowedFields = {"createdOn", "updatedOn"};

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private UserService userService;

    @Autowired
    private MessageSource messageSource;

    @InitBinder(COMMAND)
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
        dataBinder.setDisallowedFields(disAllowedFields);
        dataBinder.addValidators(userValidator);
    }

    @GetMapping()
    public String showFormOne(@RequestParam(value = "id", defaultValue = "0") int id,
                              ModelMap model,
                              HttpSession session) {

        if (!isValidId(id)) {
            return redirectTo(ERROR_PAGE);
        } else {
            setupUserRole(model, session);
            setupReferenceData(model, userService.getOrCreate(id), getRoles());
            return USER_FORM_PAGE_ONE;
        }
    }

    @PostMapping(params = "next")
    public String showFormTwo(@Validated(ValidationGroupOne.class) @ModelAttribute(COMMAND) User user,
                              BindingResult bindingResult,
                              ModelMap model,
                              HttpSession session) {

        setupUserRole(model, session);

        if (bindingResult.hasErrors()) {
            return USER_FORM_PAGE_ONE;
        } else {
            setupReferenceData(model, getRoles());
            return USER_FORM_PAGE_TWO;
        }
    }

    @PostMapping(params = "finish")
    public String showFormThree(@Validated(ValidationGroupTwo.class) @ModelAttribute(COMMAND) User user,
                                BindingResult bindingResult,
                                ModelMap model,
                                HttpSession session) {

        setupUserRole(model, session);

        if (bindingResult.hasErrors()) {
            setupReferenceData(model, getRoles());
            return USER_FORM_PAGE_TWO;
        } else {
            return USER_FORM_PAGE_THREE;
        }
    }

    @PostMapping()
    public String process(@Validated({ValidationGroupOne.class, ValidationGroupTwo.class})
                          @ModelAttribute(COMMAND) User user,
                          BindingResult bindingResult,
                          HttpSession session,
                          ModelMap model,
                          RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            setupUserRole(model, session);
            return USER_FORM_PAGE_THREE;
        }

        String message = user.isNew() ? "user.add" : "user.update";
        userService.saveOrUpdate(user);
        redirectAttributes.addFlashAttribute("message", getMessage(messageSource, message));

        return redirectTo(USERS_VIEW_PAGE);
    }

    @GetMapping("/all")
    public String showAll(ModelMap model, HttpSession session) {
        model.addAttribute("users", userService.findAll());
        setupUserRole(model, session);

        return VIEW_ALL_USERS;
    }

    @GetMapping("/delete")
    public String delete(@RequestParam(value = "id", defaultValue = "0") int id,
                         RedirectAttributes redirectAttributes) {

        if (!isValidId(id)) {
            return redirectTo(ERROR_PAGE);
        } else {
            userService.delete(id);
            redirectAttributes.addFlashAttribute("message", getMessage(messageSource, "user.delete"));

            return redirectTo(USERS_VIEW_PAGE);
        }
    }

    @GetMapping("/block")
    public String block(@RequestParam(value = "id", defaultValue = "0") int id,
                        RedirectAttributes redirectAttributes) {

        if (!isValidId(id)) {
            return redirectTo(ERROR_PAGE);
        } else {
            userService.blockOrUnblock(id);
            redirectAttributes.addFlashAttribute("message", getMessage(messageSource, "user.block"));

            return redirectTo(USERS_VIEW_PAGE);
        }
    }

    @GetMapping("/unblock")
    public String unblock(@RequestParam(value = "id", defaultValue = "0") int id,
                          RedirectAttributes redirectAttributes) {

        if (!isValidId(id)) {
            return redirectTo(ERROR_PAGE);
        } else {
            userService.blockOrUnblock(id);
            redirectAttributes.addFlashAttribute("message", getMessage(messageSource, "user.unblock"));

            return redirectTo(USERS_VIEW_PAGE);
        }
    }

    private void setupReferenceData(ModelMap model, User user, List<String> roles) {
        model.addAttribute("roles", roles);
        model.addAttribute(COMMAND, user);
    }

    private void setupReferenceData(ModelMap model, List<String> roles) {
        model.addAttribute("roles", roles);
    }
}