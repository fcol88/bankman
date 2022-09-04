package com.matgr.bankman.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.matgr.bankman.entity.Category;

public interface ICategoryRepository extends JpaRepository<Category, Long> {
	
	Optional<Category> findByName(String name);
	List<Category> findByOrderByName();

}
