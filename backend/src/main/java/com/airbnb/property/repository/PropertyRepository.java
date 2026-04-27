package com.airbnb.property.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.airbnb.property.entity.Property;

public interface PropertyRepository extends JpaRepository<Property, Long> {
	 @Override
	 @EntityGraph(attributePaths = {"owner"})
	 List<Property> findAll();

	 @Override
	 @EntityGraph(attributePaths = {"owner"})
	 Optional<Property> findById(Long id);

	 @EntityGraph(attributePaths = {"owner"})
	 List<Property> findByOwner_Email(String email);

}
