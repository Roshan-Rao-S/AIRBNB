//package com.airbnb.property.entity;
//
//import jakarta.persistence.*;
//
//@Entity
//@Table(name = "properties")
//public class Property {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String title;
//    private String location;
//    private double price;
//
//    private String ownerEmail;
//
//    // getters & setters
//    public Long getId() { return id; }
//    public void setId(Long id) { this.id = id; }
//
//    public String getTitle() { return title; }
//    public void setTitle(String title) { this.title = title; }
//
//    public String getLocation() { return location; }
//    public void setLocation(String location) { this.location = location; }
//
//    public double getPrice() { return price; }
//    public void setPrice(double price) { this.price = price; }
//
//    public String getOwnerEmail() { return ownerEmail; }
//    public void setOwnerEmail(String ownerEmail) { this.ownerEmail = ownerEmail; }
//}






package com.airbnb.property.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "properties")
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String location;
    private double price;

    private String ownerEmail;

    // 🔥 NEW FIELDS
    private String imageUrl;
    private double rating;
    private int reviewCount;

    // getters & setters

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getLocation() { return location; }

    public void setLocation(String location) { this.location = location; }

    public double getPrice() { return price; }

    public void setPrice(double price) { this.price = price; }

    public String getOwnerEmail() { return ownerEmail; }

    public void setOwnerEmail(String ownerEmail) { this.ownerEmail = ownerEmail; }

    public String getImageUrl() { return imageUrl; }

    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public double getRating() { return rating; }

    public void setRating(double rating) { this.rating = rating; }

    public int getReviewCount() { return reviewCount; }

    public void setReviewCount(int reviewCount) { this.reviewCount = reviewCount; }
}