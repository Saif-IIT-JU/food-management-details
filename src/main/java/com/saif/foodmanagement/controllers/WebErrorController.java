package com.saif.foodmanagement.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.saif.foodmanagement.utils.Utils.getMessage;

/**
 * @author saifuzzaman
 */
@Controller
@RequestMapping("/error")
public class WebErrorController implements ErrorController {

    private static final String ERROR_PAGE = "error";

    @Autowired
    private MessageSource messageSource;

    @GetMapping
    public String show(ModelMap model) {
        model.addAttribute("alert", getMessage(messageSource, "error.message"));

        return ERROR_PAGE;
    }
}
