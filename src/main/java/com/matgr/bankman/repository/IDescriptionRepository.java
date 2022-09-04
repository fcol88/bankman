package com.matgr.bankman.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.matgr.bankman.entity.Description;

public interface IDescriptionRepository extends JpaRepository<Description, Long> {
	
	Optional<Description> findByValue(String value);
	int countByCategoryIsNull();
	Optional<Description> findFirstByCategoryIsNull();

}
