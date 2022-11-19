package com.saif.foodmanagement.helper;

import com.saif.foodmanagement.utils.HashGenerationUtil;
import com.saif.foodmanagement.model.User;
import com.saif.foodmanagement.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author saifuzzaman
 */
@Service
public class AuthenticationHelper {

    @Autowired
    private UserService userService;

    public boolean isValidCredential(String username, String password) {
        Optional<User> user = userService.findByUsername(username);

        return user.filter(value -> HashGenerationUtil.getSha512(password).equals(user.get().getPassword()) &&
                !userService.isBlocked(user.get().getId())).isPresent();
    }
}