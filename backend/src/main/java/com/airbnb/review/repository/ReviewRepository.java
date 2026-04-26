package com.airbnb.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.airbnb.review.entity.Review;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByPropertyId(Long propertyId);
}
