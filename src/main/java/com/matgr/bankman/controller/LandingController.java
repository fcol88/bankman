package com.matgr.bankman.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LandingController {
	
	@GetMapping("/")
	public ModelAndView home(ModelAndView model) {
		model.setViewName("home");
		return model;
	}

}
