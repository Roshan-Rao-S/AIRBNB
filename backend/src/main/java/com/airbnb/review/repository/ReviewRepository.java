package com.airbnb.review.repository;

import org.springframework.data.repository.CrudRepository;
import com.airbnb.review.entity.Review;

import java.util.List;

public interface ReviewRepository extends CrudRepository<Review, Long> {

    List<Review> findByPropertyId(Long propertyId);
}