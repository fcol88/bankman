package com.matgr.bankman.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.matgr.bankman.dto.SiftDTO;
import com.matgr.bankman.entity.Description;
import com.matgr.bankman.service.ICategoryService;
import com.matgr.bankman.service.IDescriptionService;
import com.matgr.bankman.validator.SiftValidator;

@Controller
@RequestMapping("/data")
public class DataController {
	
	@Autowired
	private IDescriptionService descriptionService;
	@Autowired
	private ICategoryService categoryService;
	@Autowired
	private SiftValidator siftValidator;
	
	@GetMapping("/sift") 
	public ModelAndView siftForm(ModelAndView model) {
		
		Description description = descriptionService.getNextUnidentifiedDescription();
		model.addObject("description", description);
		model.addObject("categories", categoryService.findAll());
		if(description != null) {
			model.addObject("sift", new SiftDTO(description));
		}
		model.setViewName("data/sift");
		return model;
		
	}
	
	@PostMapping("/sift")
	public ModelAndView siftPost(@ModelAttribute("sift") SiftDTO sift, BindingResult result, ModelAndView model) {
		
		siftValidator.validate(sift, result);
		
		if(result.hasErrors()) {
			Description description = descriptionService.getNextUnidentifiedDescription();
			model.addObject("description", description);
			model.addObject("categories", categoryService.findAll());
			model.setViewName("data/sift");
			return model;
		}
		
		descriptionService.setCategory(sift);
		
		model.setViewName("redirect:sift");
		
		return model;
		
	}

}
