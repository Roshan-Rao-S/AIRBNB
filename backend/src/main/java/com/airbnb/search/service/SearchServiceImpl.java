package com.airbnb.search.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airbnb.property.entity.Property;
import com.airbnb.search.repository.SearchRepository;

import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {

    private final SearchRepository repository;

    @Autowired
    public SearchServiceImpl(SearchRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Property> searchByLocation(String location) {
        return repository.findByLocationContainingIgnoreCase(location);
    }

    @Override
    public List<Property> filterByPrice(double min, double max) {
        return repository.findByPriceBetween(min, max);
    }

    @Override
    public List<Property> searchAndFilter(String location, double min, double max) {
        return repository.findByLocationContainingIgnoreCaseAndPriceBetween(location, min, max);
    }
}
