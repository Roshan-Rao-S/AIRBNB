package com.airbnb.property.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.airbnb.property.entity.Property;

public interface PropertyRepository extends JpaRepository<Property, Long> {
	 @EntityGraph(attributePaths = {"owner"})
	 @Query("select p from Property p")
	 List<Property> findAllWithOwner();

	 @Override
	 @EntityGraph(attributePaths = {"owner"})
	 Optional<Property> findById(Long id);

	 @EntityGraph(attributePaths = {"owner"})
	 List<Property> findByOwner_Email(String email);

}
