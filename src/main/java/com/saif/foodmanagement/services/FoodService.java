package com.saif.foodmanagement.services;

import com.saif.foodmanagement.dao.FoodDao;
import com.saif.foodmanagement.model.Food;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static java.time.Instant.now;

/**
 * @author saifuzzaman
 */
@Service
public class FoodService {

    @Autowired
    private FoodDao foodDao;

    @Autowired
    private UserService userService;

    public Food saveOrUpdate(Food food) {
        if (food.isNew()) {
            food.setCreatedOn(Date.from(now()));
            food.setApproval(false);
        }

        food.setUpdatedOn(Date.from(now()));

        return foodDao.saveOrUpdate(food);
    }

    private Optional<Food> find(int id) {
        return foodDao.findById(id);
    }

    public void delete(int id) {
        foodDao.delete(id);
    }

    public Food approveOrDisapprove(int id) {
        Food food = find(id).get();
        food.setApproval(!food.isApproval());

        return saveOrUpdate(food);
    }

    public Food getOrCreate(int id) {
        return find(id).orElse(new Food());
    }

    public List<Food> findAll() {
        return foodDao.findAll();
    }

    public List<Food> findByServeDate(Date date) {
        return foodDao.findByServeDate(date);
    }

    public List<Food> findAllUnapprovedFoods() {
        return foodDao.findAllUnapprovedFoods();
    }

    public List<Food> findByCreator(int addedById) {
        return foodDao.findByCreator(addedById);
    }

    public List<Food> findByName(String name) {
        return foodDao.findByName(name);
    }
}
