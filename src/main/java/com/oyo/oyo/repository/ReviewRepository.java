package com.oyo.oyo.repository;

import com.oyo.oyo.dto.ReviewDto;
import com.oyo.oyo.entity.Property;
import com.oyo.oyo.entity.PropertyUser;
import com.oyo.oyo.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("select r from Review r where r.property=:property and r.propertyUser=:user")
    Review findReviewByUser(@Param("property") Property property, @Param("user") PropertyUser user);
}