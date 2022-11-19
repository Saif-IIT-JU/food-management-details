package com.saif.foodmanagement.services;

import com.saif.foodmanagement.dao.ReviewDao;
import com.saif.foodmanagement.model.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

import static java.time.Instant.now;

/**
 * @author saifuzzaman
 */
@Service
public class ReviewService {

    @Autowired
    private ReviewDao reviewDao;

    public void addReview(Review review) {
        if (review.isNew()) {
            review.setCreatedOn(Date.from(now()));
        }

        review.setUpdatedOn(Date.from(now()));

        reviewDao.addReview(review);
    }
}
