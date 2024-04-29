package com.oyo.oyo.service;

import com.oyo.oyo.dto.ReviewDto;
import com.oyo.oyo.entity.Property;
import com.oyo.oyo.entity.PropertyUser;
import com.oyo.oyo.entity.Review;
import com.oyo.oyo.repository.PropertyRepository;
import com.oyo.oyo.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReviewService {

    private ReviewRepository reviewRepository;
    private PropertyRepository propertyRepository;

    public ReviewService(ReviewRepository reviewRepository, PropertyRepository propertyRepository) {
        this.reviewRepository = reviewRepository;
        this.propertyRepository = propertyRepository;
    }


    public String addReview(long propertyId, Review review, PropertyUser user) {
        Optional<Property> opProperty = propertyRepository.findById(propertyId);
        Property property = opProperty.orElseThrow(() -> new IllegalArgumentException("Property not found"));

        Review existingReview = reviewRepository.findReviewByUser(property, user);
        if (existingReview != null) {
            return "You have already added a review for this property";
        }

        review.setProperty(property);
        review.setPropertyUser(user);
        reviewRepository.save(review);
        return "Review added successfully";
    }


}
