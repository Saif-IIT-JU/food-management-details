package com.saif.foodmanagement.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

import static com.saif.foodmanagement.utils.Utils.redirectTo;

/**
 * @author saifuzzaman
 */
@Controller
@RequestMapping("/logout")
public class LogoutController {

    private static final String HOME_PAGE = "home";

    @GetMapping
    public String process(HttpSession session) {
        session.invalidate();

        return redirectTo(HOME_PAGE);
    }
}
