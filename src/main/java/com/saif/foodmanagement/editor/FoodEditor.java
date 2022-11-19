package com.saif.foodmanagement.editor;

import com.saif.foodmanagement.model.Food;
import com.saif.foodmanagement.services.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;

import static java.util.Objects.isNull;

/**
 * @author saifuzzaman
 */
@Component
public class FoodEditor extends PropertyEditorSupport {

    @Autowired
    private FoodService foodService;

    @Override
    public String getAsText() {
        Food food = (Food) getValue();

        return isNull(food) ? "" : food.getName();
    }

    @Override
    public void setAsText(String id) {
        int foodId = Integer.parseInt(id);
        Food food = foodService.getOrCreate(foodId);
        setValue(food);
    }
}
