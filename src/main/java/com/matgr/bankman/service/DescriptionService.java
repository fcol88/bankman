package com.matgr.bankman.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matgr.bankman.dto.SiftDTO;
import com.matgr.bankman.entity.Description;
import com.matgr.bankman.repository.IDescriptionRepository;

@Service
public class DescriptionService implements IDescriptionService {
	
	@Autowired
	private IDescriptionRepository descriptionRepository;
	@Autowired
	private ICategoryService categoryService;

	@Override
	public Description get(String description) {

		Optional<Description> entity = descriptionRepository.findByValue(description);
		
		if(entity.isPresent()) {
			return entity.get();
		}
		
		return descriptionRepository.save(new Description(description));
		
	}
	
	@Override
	public Description getNextUnidentifiedDescription() {
		
		Optional<Description> description = descriptionRepository.findFirstByCategoryIsNull();
		return description.isPresent() ? description.get() : null;
		
	}
	
	@Override
	public Description setCategory(SiftDTO dto) {
		
		Optional<Description> opt = descriptionRepository.findById(dto.getDescriptionId());
		
		if(opt.isPresent()) {
			Description description = opt.get();
			description.setCategory(categoryService.get(dto));
			return descriptionRepository.save(description);
		}
		
		return null;
		
	}

}
