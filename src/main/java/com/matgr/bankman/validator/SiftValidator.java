package com.matgr.bankman.validator;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import com.matgr.bankman.dto.SiftDTO;
import com.matgr.bankman.entity.Category;
import com.matgr.bankman.repository.ICategoryRepository;

@Component
public class SiftValidator {
	
	@Autowired
	private ICategoryRepository categoryRepository;
	
	private static final String ERROR_SIFT = "error.sift";
	
	public void validate(SiftDTO sift, BindingResult result) {
		
		if(sift.getDescriptionId() == null) {
			result.rejectValue("descriptionId", ERROR_SIFT, "Stop mucking about!");
			return;
		}
		
		if(sift.getCategoryId() == null && 
				(sift.getNewCategory() == null || sift.getNewCategory().isBlank())) {
			result.rejectValue("categoryId", ERROR_SIFT, "You have to choose an existing category or add a new one");
			return;
		}
		
		if(sift.getCategoryId() != null && 
				sift.getNewCategory() != null && !sift.getNewCategory().isBlank()) {
			
			result.rejectValue("categoryId", ERROR_SIFT, "You can't have it both ways!");
			return;
		}
		
		if(sift.getNewCategory() != null && sift.getNewCategory().length() > 25) {
			
			result.rejectValue("newCategory", ERROR_SIFT, "Enter a shorter name for the category");
			return;
			
		}

		Optional<Category> category = categoryRepository.findByName(sift.getNewCategory());
		
		if(category.isPresent()) {
			
			result.rejectValue("newCategory", ERROR_SIFT, "That category name already exists!");
			
		}
		
	}

}
