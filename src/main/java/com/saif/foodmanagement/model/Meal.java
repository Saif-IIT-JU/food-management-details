package com.saif.foodmanagement.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author saifuzzaman
 */
public enum Meal {

    BREAKFAST("BREAKFAST"),
    LUNCH("LUNCH"),
    DINNER("DINNER");

    private String meal;

    Meal(String meal){
        this.meal = meal;
    }

    public String getMeal() {
        return this.meal;
    }

    public static List<String> getMeals() {
        List<String> meals = new ArrayList<>();

        for(Meal meal : values()) {
            meals.add(meal.name());
        }

        return meals;
    }
}
