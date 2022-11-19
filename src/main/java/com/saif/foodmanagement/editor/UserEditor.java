package com.saif.foodmanagement.editor;

import com.saif.foodmanagement.model.User;
import com.saif.foodmanagement.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;

import static java.util.Objects.isNull;

/**
 * @author saifuzzaman
 */
@Component
public class UserEditor extends PropertyEditorSupport {

    @Autowired
    private UserService userService;

    @Override
    public String getAsText() {
        User user = (User) getValue();

        return isNull(user) ? "" : user.getFirstName();
    }

    @Override
    public void setAsText(String id) {
        int userId = Integer.parseInt(id);
        User user = userService.getOrCreate(userId);
        setValue(user);
    }
}
