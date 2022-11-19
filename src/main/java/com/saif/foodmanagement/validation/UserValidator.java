package com.saif.foodmanagement.validation;

import com.saif.foodmanagement.model.User;
import com.saif.foodmanagement.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import static java.util.Objects.isNull;

/**
 * @author saifuzzaman
 */
@Component
public class UserValidator implements Validator {

    private static final String EMAIL_REGEX = "^[a-z0-9+_.-]+@[a-z0-9.-]+[.][a-z]+$";

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        String username = user.getUsername();
        String email = user.getEmail();

        if (userService.isExistsUsername(user)) {
            errors.rejectValue("username", "username.duplicate", "Duplicate username!");
        }

        if (userService.isExistsEmail(user)) {
            errors.rejectValue("email", "email.duplicate", "Duplicate email!");
        } else if (isNull(email) || !email.matches(EMAIL_REGEX)) {
            errors.rejectValue("email", "email.invalid", "Invalid email!");
        }
    }
}
