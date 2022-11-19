package com.saif.foodmanagement.controllers;

import com.saif.foodmanagement.model.Food;
import com.saif.foodmanagement.model.Review;
import com.saif.foodmanagement.model.User;
import com.saif.foodmanagement.services.FoodService;
import com.saif.foodmanagement.utils.AccessContext;
import com.saif.foodmanagement.validation.ValidationGroups.ValidationGroupOne;
import com.saif.foodmanagement.validation.ValidationGroups.ValidationGroupTwo;
import com.saif.foodmanagement.validation.ValidationGroups.ValidationGroupThree;
import com.saif.foodmanagement.editor.UserEditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.saif.foodmanagement.utils.Constants.*;
import static com.saif.foodmanagement.utils.Utils.*;
import static com.saif.foodmanagement.model.Meal.getMeals;

/**
 * @author saifuzzaman
 */
@Controller
@RequestMapping("/food")
public class FoodController {

    private static final String COMMAND = "food";
    private static final String FOOD_FORM_PAGE = "foodForm";
    private static final String FOODS_VIEW_PAGE = "food/approved";
    private static final String VIEW_ALL_FOODS = "showFoods";
    private static final String HOME_PAGE = "index";
    private static final String ERROR_PAGE = "error";
    private static final String VIEW_ALL_UNAPPROVED_FOODS = "showPendingFoods";
    private static final String[] disAllowedFields = {"createdOn", "updatedOn"};

    @Autowired
    private FoodService foodService;

    @Autowired
    private UserEditor userEditor;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private AccessContext accessContext;

    @InitBinder(COMMAND)
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
        CustomDateEditor customDateEditor = new CustomDateEditor(new SimpleDateFormat(DATE_FORMAT), true);
        dataBinder.registerCustomEditor(Date.class, "serveDate", customDateEditor);
        dataBinder.registerCustomEditor(User.class, userEditor);
        dataBinder.setDisallowedFields(disAllowedFields);
    }

    @GetMapping
    public String show(@RequestParam(value = "id", defaultValue = "0") int id,
                       ModelMap model,
                       HttpSession session) {

        if (!(isValidId(id) && accessContext.isAccessible(id, (int) session.getAttribute(USER_ID)))) {
            return redirectTo(ERROR_PAGE);
        } else {
            setupReferenceData(model, foodService.getOrCreate(id), getMeals());
            setupUserRole(model, session);
            return FOOD_FORM_PAGE;
        }
    }

    @PostMapping
    public String process(@Validated({ValidationGroupOne.class, ValidationGroupTwo.class, ValidationGroupThree.class})
                          @ModelAttribute(COMMAND) Food food,
                          BindingResult bindingResult,
                          ModelMap model,
                          RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("meals", getMeals());
            return FOOD_FORM_PAGE;
        }

        String message = food.isNew() ? "food.add.request" : "food.update";
        foodService.saveOrUpdate(food);
        redirectAttributes.addFlashAttribute("message", getMessage(messageSource, message));

        return redirectTo(FOODS_VIEW_PAGE);
    }

    @GetMapping("/approved")
    public String showAll(ModelMap model, HttpSession session) {
        setupReferenceData(model, foodService.findAll());
        setupUserRole(model, session);

        return VIEW_ALL_FOODS;
    }

    @GetMapping("/unapproved")
    public String showAllPendingFoods(ModelMap model, HttpSession session) {
        setupUserRole(model, session);
        setupReferenceData(model, foodService.findAllUnapprovedFoods());

        return VIEW_ALL_UNAPPROVED_FOODS;
    }

    @GetMapping("/approve")
    public String approve(@RequestParam(value = "id", defaultValue = "0") int id,
                          RedirectAttributes redirectAttributes) {

        if (!isValidId(id)) {
            return redirectTo(ERROR_PAGE);
        } else {
            foodService.approveOrDisapprove(id);
            redirectAttributes.addFlashAttribute("message", getMessage(messageSource, "food.approved"));
            return redirectTo(FOODS_VIEW_PAGE);
        }
    }

    @GetMapping("/cook")
    public String findFoodsByCreator(@RequestParam(value = "id", defaultValue = "0") int id,
                                     ModelMap model,
                                     HttpSession session) {

        if (!isValidId(id)) {
            return redirectTo(ERROR_PAGE);
        } else {
            setupReferenceData(model, foodService.findByCreator(id));
            setupUserRole(model, session);
            return VIEW_ALL_FOODS;
        }
    }

    @GetMapping("/disapprove")
    public String disapprove(@RequestParam(value = "id", defaultValue = "0") int id,
                             RedirectAttributes redirectAttributes) {

        if (!isValidId(id)) {
            return redirectTo(ERROR_PAGE);
        } else {
            foodService.approveOrDisapprove(id);
            redirectAttributes.addFlashAttribute("message", getMessage(messageSource, "food.unapproved"));
            return redirectTo(FOODS_VIEW_PAGE);
        }
    }

    @GetMapping("/delete")
    public String deleteFood(@RequestParam(value = "id", defaultValue = "0") int id,
                             RedirectAttributes redirectAttributes,
                             HttpSession session) {

        if (!(isValidId(id) && accessContext.isAccessible(id, (int) session.getAttribute(USER_ID)))) {
            return redirectTo(ERROR_PAGE);
        } else {
            foodService.delete(id);
            redirectAttributes.addFlashAttribute("message", getMessage(messageSource, "food.delete"));
            return redirectTo(FOODS_VIEW_PAGE);
        }
    }

    @PostMapping("/search")
    public String search(@Validated(ValidationGroupTwo.class) @ModelAttribute(COMMAND) Food food,
                         BindingResult bindingResult,
                         ModelMap model,
                         HttpSession session) {

        if (bindingResult.hasErrors()) {
            return HOME_PAGE;
        }

        setupUserRole(model, session);
        setupReferenceData(model, foodService.findByServeDate(food.getServeDate()));

        return VIEW_ALL_FOODS;
    }

    @GetMapping("/search")
    public String searchByFood(@Validated(ValidationGroupOne.class) @ModelAttribute(COMMAND) Food food,
                               BindingResult bindingResult,
                               ModelMap model,
                               HttpSession session) {

        if (bindingResult.hasErrors()) {
            return HOME_PAGE;
        }

        setupUserRole(model, session);
        setupReferenceData(model, foodService.findByName(food.getName()));

        return VIEW_ALL_FOODS;
    }

    private void setupReferenceData(ModelMap model, List<Food> foodList) {
        model.addAttribute("foods", foodList);
        model.addAttribute("review", new Review());
    }

    private void setupReferenceData(ModelMap model, Food food, List<String> meals) {
        model.addAttribute(COMMAND, food);
        model.addAttribute("meals", meals);
    }
}
