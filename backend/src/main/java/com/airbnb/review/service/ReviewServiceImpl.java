//package com.airbnb.review.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.airbnb.review.dto.ReviewDTO;
//import com.airbnb.review.entity.Review;
//import com.airbnb.review.repository.ReviewRepository;
//
//import java.util.List;
//
//@Service
//public class ReviewServiceImpl implements ReviewService {
//
//    @Autowired
//    private ReviewRepository reviewRepository;
//
//    @Override
//    public Review addReview(ReviewDTO dto, String email) {
//
//        Review review = new Review();
//        review.setPropertyId(dto.getPropertyId());
//        review.setRating(dto.getRating());
//        review.setComment(dto.getComment());
//        review.setUserEmail(email);
//
//        return reviewRepository.save(review);
//    }
//
//    @Override
//    public List<Review> getReviewsByProperty(Long propertyId) {
//        return reviewRepository.findByPropertyId(propertyId);
//    }
//}








package com.airbnb.review.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airbnb.property.entity.Property;
import com.airbnb.property.repository.PropertyRepository;
import com.airbnb.review.dto.ReviewDTO;
import com.airbnb.review.entity.Review;
import com.airbnb.review.repository.ReviewRepository;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private PropertyRepository propertyRepository;

    @Override
    public Review addReview(ReviewDTO dto, String email) {

        // 1. Save review
        Review review = new Review();
        review.setPropertyId(dto.getPropertyId());
        review.setRating(dto.getRating());
        review.setComment(dto.getComment());
        review.setUserEmail(email);

        Review savedReview = reviewRepository.save(review);

        // 2. Recalculate rating
        List<Review> reviews = reviewRepository.findByPropertyId(dto.getPropertyId());

        double avgRating = reviews.stream()
                .mapToInt(Review::getRating)
                .average()
                .orElse(0.0);

        int count = reviews.size();

        // 3. Update property
        Property property = propertyRepository.findById(dto.getPropertyId())
                .orElseThrow(() -> new RuntimeException("Property not found"));

        property.setRating(avgRating);
        property.setReviewCount(count);

        propertyRepository.save(property);

        return savedReview;
    }

    @Override
    public List<Review> getReviewsByProperty(Long propertyId) {
        return reviewRepository.findByPropertyId(propertyId);
    }
}