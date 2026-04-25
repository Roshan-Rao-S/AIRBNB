package com.airbnb.calendar.dto;

import java.time.LocalDate;

public class AvailabilityDTO {

    private Long propertyId;
    private LocalDate fromDate;
    private LocalDate toDate;

    public Long getPropertyId() { return propertyId; }
    public void setPropertyId(Long propertyId) { this.propertyId = propertyId; }

    public LocalDate getFromDate() { return fromDate; }
    public void setFromDate(LocalDate fromDate) { this.fromDate = fromDate; }

    public LocalDate getToDate() { return toDate; }
    public void setToDate(LocalDate toDate) { this.toDate = toDate; }
}