package com.saif.foodmanagement.controllers;

import com.saif.foodmanagement.model.Food;
import com.saif.foodmanagement.model.Review;
import com.saif.foodmanagement.model.User;
import com.saif.foodmanagement.services.ReviewService;
import com.saif.foodmanagement.services.UserService;
import com.saif.foodmanagement.editor.FoodEditor;
import com.saif.foodmanagement.editor.UserEditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import static com.saif.foodmanagement.utils.Utils.getMessage;
import static com.saif.foodmanagement.utils.Utils.redirectToPreviousPage;

/**
 * @author saifuzzaman
 */
@Controller
@RequestMapping("/review")
public class ReviewController {

    private static final String COMMAND = "review";
    private static final String[] disAllowedFields = {"createdOn", "updatedOn"};

    @Autowired
    private FoodEditor foodEditor;

    @Autowired
    private UserEditor userEditor;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private UserService userService;

    @InitBinder(COMMAND)
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
        dataBinder.setDisallowedFields(disAllowedFields);
        dataBinder.registerCustomEditor(User.class, userEditor);
        dataBinder.registerCustomEditor(Food.class, foodEditor);
    }

    @PostMapping
    public String process(@ModelAttribute(COMMAND) @Valid Review review,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes,
                          HttpServletRequest request,
                          HttpSession session) {

        String backLink = request.getHeader("Referer");

        if (bindingResult.hasErrors()) {
            return redirectToPreviousPage(backLink);
        }

        String username = (String) session.getAttribute("username");
        review.setUser(userService.findByUsername(username).get());
        reviewService.addReview(review);
        redirectAttributes.addFlashAttribute("message", getMessage(messageSource, "review.add"));

        return redirectToPreviousPage(backLink);
    }
}
