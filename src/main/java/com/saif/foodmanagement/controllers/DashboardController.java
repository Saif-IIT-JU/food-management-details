package com.saif.foodmanagement.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

import static com.saif.foodmanagement.utils.Utils.setupUserRole;

/**
 * @author saifuzzaman
 */
@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    private static final String DASHBOARD_PAGE = "dashboard";

    @GetMapping
    public String show(ModelMap model, HttpSession session) {
        setupUserRole(model, session);
        return DASHBOARD_PAGE;
    }
}
