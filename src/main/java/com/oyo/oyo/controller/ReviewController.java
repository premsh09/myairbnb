package com.oyo.oyo.controller;
import com.oyo.oyo.dto.ReviewDto;
import com.oyo.oyo.entity.Property;
import com.oyo.oyo.entity.PropertyUser;
import com.oyo.oyo.entity.Review;
import com.oyo.oyo.repository.PropertyRepository;
import com.oyo.oyo.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/oyo/review")
public class ReviewController {

    private ReviewService reviewService;
    private PropertyRepository propertyRepository;

    public ReviewController(ReviewService reviewService, PropertyRepository propertyRepository) {
        this.reviewService = reviewService;
        this.propertyRepository = propertyRepository;
    }

    @PostMapping("/addReview/{propertyId}")
    public ResponseEntity<String> addReview(
            @PathVariable long propertyId,
            @RequestBody Review review,
            @AuthenticationPrincipal PropertyUser user){

        String message = reviewService.addReview(propertyId, review, user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}