package com.saif.foodmanagement.dao;

import com.saif.foodmanagement.model.Review;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 * @author saifuzzaman
 */
@Repository
public class ReviewDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Review addReview(Review review) {
        if (review.isNew()) {
            entityManager.persist(review);
        } else {
            review = entityManager.merge(review);
        }

        return review;
    }
}
