package com.saif.foodmanagement.dao;

import com.saif.foodmanagement.model.Food;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.saif.foodmanagement.model.Food.*;

/**
 * @author saifuzzaman
 */
@Repository
public class FoodDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Food saveOrUpdate(Food food) {
        if (food.isNew()) {
            entityManager.persist(food);
        } else {
            food = entityManager.merge(food);
        }

        return food;
    }

    public Optional<Food> findById(int id) {
        return Optional.ofNullable(entityManager.find(Food.class, id));
    }

    @Transactional
    public void delete(int id) {
        entityManager.remove(entityManager.getReference(Food.class, id));
    }

    public List<Food> findAll() {
        return entityManager.createNamedQuery(FIND_APPROVED_FOODS, Food.class).getResultList();
    }

    public List<Food> findByServeDate(Date date) {
        return entityManager.createNamedQuery(FIND_BY_SERVE_DATE, Food.class)
                .setParameter("date", date)
                .getResultList();
    }

    public List<Food> findAllUnapprovedFoods() {
        return entityManager.createNamedQuery(FIND_PENDING_FOODS, Food.class).getResultList();
    }

    public List<Food> findByCreator(int addedById) {
        return entityManager.createNamedQuery(FIND_BY_CREATOR)
                .setParameter("added_by_id", addedById)
                .getResultList();
    }

    public List<Food> findByName(String name) {
        return entityManager.createNamedQuery(FIND_BY_NAME)
                .setParameter("name", name)
                .getResultList();
    }
}
