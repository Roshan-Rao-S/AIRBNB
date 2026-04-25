package com.airbnb.search.service;

import java.util.List;
import com.airbnb.property.entity.Property;

public interface SearchService {

    List<Property> searchByLocation(String location);

    List<Property> filterByPrice(double min, double max);

    List<Property> searchAndFilter(String location, double min, double max);
}