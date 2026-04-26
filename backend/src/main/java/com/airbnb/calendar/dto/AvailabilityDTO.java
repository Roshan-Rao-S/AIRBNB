package com.airbnb.calendar.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

public class AvailabilityDTO {

    @NotNull(message = "Property id is required")
    private Long propertyId;

    @NotNull(message = "From date is required")
    @FutureOrPresent(message = "From date must be today or in the future")
    private LocalDate fromDate;

    @NotNull(message = "To date is required")
    private LocalDate toDate;

    public Long getPropertyId() { return propertyId; }
    public void setPropertyId(Long propertyId) { this.propertyId = propertyId; }

    public LocalDate getFromDate() { return fromDate; }
    public void setFromDate(LocalDate fromDate) { this.fromDate = fromDate; }

    public LocalDate getToDate() { return toDate; }
    public void setToDate(LocalDate toDate) { this.toDate = toDate; }
}
