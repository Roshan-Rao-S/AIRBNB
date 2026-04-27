package com.airbnb.review.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.airbnb.review.dto.ReviewDTO;
import com.airbnb.review.entity.Review;
import com.airbnb.review.service.ReviewService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private static final Logger logger = LoggerFactory.getLogger(ReviewController.class);
    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping
    public Review addReview(@jakarta.validation.Valid @RequestBody ReviewDTO dto, HttpServletRequest request) {

        String email = (String) request.getAttribute("email");

        return reviewService.addReview(dto, email);
    }

    @GetMapping("/{propertyId}")
    public List<Review> getReviews(@PathVariable Long propertyId) {

        return reviewService.getReviewsByProperty(propertyId);
    }
    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

            String uploadDir = "D:/airbnb-images/";
            java.io.File dir = new java.io.File(uploadDir);
            if (!dir.exists()) dir.mkdirs();

            java.io.File destination = new java.io.File(uploadDir + fileName);
            file.transferTo(destination);

            // return path to save in DB
            return ResponseEntity.ok("images/" + fileName);

        } catch (Exception e) {
            logger.error("Image upload failed", e);
            return ResponseEntity.status(500).body("Upload failed");
        }
    }
}
