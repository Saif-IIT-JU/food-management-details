package com.saif.foodmanagement.filters;

import com.saif.foodmanagement.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static java.util.Objects.nonNull;

/**
 * @author saifuzzaman
 */
@WebFilter(urlPatterns = {"/food", "/food/delete", "/food/unapproved"})
public class CookOrAdminAuthenticationFilter implements Filter {

    @Autowired
    private MessageSource messageSource;

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);

        if (nonNull(session.getAttribute(Constants.SESSION_USER)) &&
                (session.getAttribute(Constants.SESSION_USER).equals(Constants.COOK) ||
                        session.getAttribute(Constants.SESSION_USER).equals(Constants.ADMIN))) {

            res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            chain.doFilter(request, response);
        } else {
            session.setAttribute("LoginAlert", messageSource
                    .getMessage("login.alert.login.first", null, LocaleContextHolder.getLocale()));

            res.sendRedirect("/login");
        }
    }
}
