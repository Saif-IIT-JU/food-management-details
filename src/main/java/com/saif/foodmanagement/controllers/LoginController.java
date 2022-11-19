package com.saif.foodmanagement.controllers;

import com.saif.foodmanagement.commands.LoginCmd;
import com.saif.foodmanagement.helper.AuthenticationHelper;
import com.saif.foodmanagement.model.User;
import com.saif.foodmanagement.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import static java.util.Objects.nonNull;
import static com.saif.foodmanagement.utils.Constants.*;
import static com.saif.foodmanagement.utils.Utils.getMessage;
import static com.saif.foodmanagement.utils.Utils.redirectTo;

/**
 * @author saifuzzaman
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    private static final String DASHBOARD_PAGE = "dashboard";
    private static final String LOGIN_FORM_PAGE = "loginForm";
    private static final String LOGIN_PAGE = "login";
    private static final String COMMAND = "loginCmd";

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationHelper authenticationHelper;

    @Autowired
    private MessageSource messageSource;

    @InitBinder(COMMAND)
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @GetMapping
    public String show(ModelMap model, HttpSession session) {
        if (nonNull(session.getAttribute(SESSION_USER))) {
            return redirectTo(DASHBOARD_PAGE);
        }

        model.addAttribute(COMMAND, new LoginCmd());

        return LOGIN_FORM_PAGE;
    }

    @PostMapping("/process")
    public String process(@Valid @ModelAttribute(COMMAND) LoginCmd credential,
                          BindingResult bindingResult,
                          HttpSession session,
                          RedirectAttributes redirectAttributes) {

        String username = credential.getUsername();
        String password = credential.getPassword();

        if (bindingResult.hasErrors()) {
            return LOGIN_FORM_PAGE;
        } else if (authenticationHelper.isValidCredential(username, password)) {
            User user = userService.findByUsername(username).get();
            session.setAttribute(SESSION_USER, user.getRole().getType());
            session.setAttribute(USERNAME, user.getUsername());
            session.setAttribute(USER_ID, user.getId());

            return redirectTo(DASHBOARD_PAGE);
        } else {
            redirectAttributes.addFlashAttribute("LoginAlert",
                    getMessage(messageSource, "login.alert.incorrect.credential"));

            return redirectTo(LOGIN_PAGE);
        }
    }
}
