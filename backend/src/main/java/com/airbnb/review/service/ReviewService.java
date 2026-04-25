package com.airbnb.review.service;

import java.util.List;
import com.airbnb.review.entity.Review;
import com.airbnb.review.dto.ReviewDTO;

public interface ReviewService {

    Review addReview(ReviewDTO dto, String email);

    List<Review> getReviewsByProperty(Long propertyId);
}