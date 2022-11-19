package com.saif.foodmanagement.utils;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpSession;

import static java.util.Objects.nonNull;
import static com.saif.foodmanagement.utils.Constants.*;

/**
 * @author saifuzzaman
 */
public class Utils {

    private Utils() {
    }

    public static String getMessage(MessageSource messageSource, String code) {
        return messageSource.getMessage(code, null, LocaleContextHolder.getLocale());
    }

    public static String redirectTo(String path, int... ids) {
        return "redirect:/" + path + (ids.length > 0 ? "?id=" + ids[0] : "");
    }

    public static String redirectToPreviousPage(String referer) {
        return "redirect:" + referer;
    }

    public static boolean isValidId(int id) {
        return id >= 0;
    }

    public static void setupUserRole(ModelMap model, HttpSession session) {
        if ( nonNull(session.getAttribute(SESSION_USER)) && session.getAttribute(SESSION_USER).equals(ADMIN)) {
            model.addAttribute("isAdmin", true);
        } else if ( nonNull(session.getAttribute(SESSION_USER)) && session.getAttribute(SESSION_USER).equals(COOK)) {
            model.addAttribute("isCook", true);
        } else if ( nonNull(session.getAttribute(SESSION_USER)) && session.getAttribute(SESSION_USER).equals(USER)) {
            model.addAttribute("isUser", true);
        }
    }
}