package com.matgr.bankman.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.matgr.bankman.dto.CategoryVisualisationDTO;
import com.matgr.bankman.dto.VisualisationDTO;
import com.matgr.bankman.service.ILineItemService;

@Controller
@RequestMapping("/visualise")
@SessionAttributes({"visualisation"})
public class VisualiseController {
	
	@Autowired
	private ILineItemService lineItemService;
	
	private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	@GetMapping("/")
	public ModelAndView visualiseHome(@RequestParam(name="from", required=false) String fromStr, 
			@RequestParam(name="to", required=false) String toStr, 
			ModelAndView model) {
		
		model.setViewName("visualise/home");
		
		if(fromStr == null) {
			return model;
		}
		
		LocalDate from;
		LocalDate to;
		
		try {
			from = LocalDate.parse(fromStr, DATE_FORMAT);
			to = LocalDate.parse(toStr, DATE_FORMAT);
		} catch(DateTimeParseException e) {
			model.addObject("message", "You have to enter two valid dates");
			model.addObject("visualisation", null);
			return model;
		}
		
		VisualisationDTO visualisation = lineItemService.visualiseBetweenDates(from, to);
		model.addObject("visualisation", visualisation);
		
		return model;
		
	}
	
	@GetMapping("/sort")
	public ModelAndView visualiseSort(@RequestParam("sort") int sort, ModelAndView model,
			@ModelAttribute("visualisation") VisualisationDTO visualisation) {
		
		if(visualisation == null) {
			model.setViewName("redirect:/visualise/");
			return model;
		}
		
		model.setViewName("visualise/home");
		
		if(sort == 0) {
			Collections.sort(visualisation.getCategories(), CategoryVisualisationDTO.AMOUNT_COMPARATOR);
		} else if(sort == 1) {
			Collections.sort(visualisation.getCategories(), CategoryVisualisationDTO.CATEGORY_COMPARATOR);
		}
		
		return model;
		
	}

}
