package com.saif.foodmanagement.commands;

import com.saif.foodmanagement.utils.Constants;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author saifuzzaman
 */
public class LoginCmd implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "{message.not.null}")
    @Size(max = Constants.MAX_NAME_LENGTH, message = "{max.name.length}")
    private String username;

    @NotNull(message = "{message.not.null}")
    @Size(max = Constants.MAX_PASSWORD_LENGTH, message = "{password.length}")
    private String password;

    public LoginCmd() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

