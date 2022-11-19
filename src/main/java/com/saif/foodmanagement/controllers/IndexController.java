package com.saif.foodmanagement.controllers;

import com.saif.foodmanagement.model.Food;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author saifuzzaman
 */
@Controller
@RequestMapping("/home")
public class IndexController {

    private static final String HOME_PAGE = "index";

    @GetMapping
    public String show(ModelMap model) {
        model.addAttribute("food", new Food());

        return HOME_PAGE;
    }
}
