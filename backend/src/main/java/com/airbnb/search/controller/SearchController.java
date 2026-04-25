package com.airbnb.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.airbnb.property.entity.Property;
import com.airbnb.search.service.SearchService;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private SearchService service;

    // 🔍 search by location
    @GetMapping("/location")
    public List<Property> searchByLocation(@RequestParam String location) {
        return service.searchByLocation(location);
    }

    // 💰 filter by price
    @GetMapping("/price")
    public List<Property> filterByPrice(
            @RequestParam double min,
            @RequestParam double max) {
        return service.filterByPrice(min, max);
    }

    // 🔥 combined
    @GetMapping("/filter")
    public List<Property> searchAndFilter(
            @RequestParam String location,
            @RequestParam double min,
            @RequestParam double max) {
        return service.searchAndFilter(location, min, max);
    }
}