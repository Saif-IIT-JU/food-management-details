package com.saif.foodmanagement.utils;

import com.saif.foodmanagement.model.Role;
import com.saif.foodmanagement.model.Food;
import com.saif.foodmanagement.model.User;
import com.saif.foodmanagement.services.FoodService;
import com.saif.foodmanagement.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author saifuzzaman
 */
@Service
public class AccessContext {

    @Autowired
    private UserService userService;

    @Autowired
    private FoodService foodService;

    public boolean isAccessible(int foodId, int userId) {
        if(foodId == 0){
            return true;
        }

        User user = userService.getOrCreate(userId);
        Role role = user.getRole();
        Food food = foodService.getOrCreate(foodId);

        return (role.equals(Role.ADMIN) || (role.equals(Role.COOK) && user.getId() == food.getAddedBy().getId()));
    }
}
