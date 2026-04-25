package com.airbnb.calendar.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "availability")
public class Availability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long propertyId;

    private LocalDate blockedFrom;
    private LocalDate blockedTo;

    // getters setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getPropertyId() { return propertyId; }
    public void setPropertyId(Long propertyId) { this.propertyId = propertyId; }

    public LocalDate getBlockedFrom() { return blockedFrom; }
    public void setBlockedFrom(LocalDate blockedFrom) { this.blockedFrom = blockedFrom; }

    public LocalDate getBlockedTo() { return blockedTo; }
    public void setBlockedTo(LocalDate blockedTo) { this.blockedTo = blockedTo; }
}