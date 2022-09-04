package com.matgr.bankman.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.matgr.bankman.service.ILineItemService;

@Controller
@RequestMapping("/csv")
public class CSVController {
	
	@Autowired
	private ILineItemService lineItemService;
	
	@GetMapping("/form")
	public ModelAndView getForm(ModelAndView model) {
		
		model.setViewName("csv/form");
		return model;
		
	}
	
	@PostMapping("/form")
	public ModelAndView postForm(@RequestParam("file") MultipartFile file, RedirectAttributes attributes,
			ModelAndView model) {
		
		lineItemService.save(file);
		model.setViewName("redirect:success");
		attributes.addFlashAttribute("message", "You successfully uploaded " + file.getOriginalFilename());
		return model;
		
	}
	
	@GetMapping("/success")
	public ModelAndView success(ModelAndView model) {
		model.setViewName("csv/success");
		return model;
	}

}
