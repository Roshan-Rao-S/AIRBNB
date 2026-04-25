package com.airbnb.search.repository;

import org.springframework.data.repository.CrudRepository;
import com.airbnb.property.entity.Property;

import java.util.List;

public interface SearchRepository extends CrudRepository<Property, Long> {

    List<Property> findByLocationContainingIgnoreCase(String location);

    List<Property> findByPriceBetween(double min, double max);

    List<Property> findByLocationContainingIgnoreCaseAndPriceBetween(
            String location, double min, double max);
}