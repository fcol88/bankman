package com.matgr.bankman.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matgr.bankman.dto.SiftDTO;
import com.matgr.bankman.entity.Category;
import com.matgr.bankman.repository.ICategoryRepository;

@Service
public class CategoryService implements ICategoryService {
	
	@Autowired
	private ICategoryRepository categoryRepository;

	@Override
	public Category get(SiftDTO sift) {

		if(sift.getCategoryId() != null) {
			Optional<Category> opt = categoryRepository.findById(sift.getCategoryId());
			return opt.isPresent() ? opt.get() : null;
		}
		
		return categoryRepository.save(new Category(sift.getNewCategory()));
		
	}
	
	@Override
	public List<Category> findAll() {
		
		return categoryRepository.findByOrderByName();
		
	}

}
