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

import java.util.ArrayList;
import java.util.List;

import com.airbnb.booking.entity.Booking;
import com.airbnb.userservice.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    @JsonIgnore
    private User owner;

    // 🔥 NEW FIELDS
    private String imageUrl;
    private double rating;
    private int reviewCount;

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Booking> bookings = new ArrayList<>();

    // getters & setters

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getLocation() { return location; }

    public void setLocation(String location) { this.location = location; }

    public double getPrice() { return price; }

    public void setPrice(double price) { this.price = price; }

    public User getOwner() { return owner; }
    public void setOwner(User owner) { this.owner = owner; }

    public String getImageUrl() { return imageUrl; }

    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public double getRating() { return rating; }

    public void setRating(double rating) { this.rating = rating; }

    public int getReviewCount() { return reviewCount; }

    public void setReviewCount(int reviewCount) { this.reviewCount = reviewCount; }

    public List<Booking> getBookings() { return bookings; }
    public void setBookings(List<Booking> bookings) { this.bookings = bookings; }

    @JsonProperty("ownerEmail")
    public String getOwnerEmail() {
        return owner != null ? owner.getEmail() : null;
    }
}
