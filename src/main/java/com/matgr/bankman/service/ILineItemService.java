package com.matgr.bankman.service;

import java.time.LocalDate;

import org.springframework.web.multipart.MultipartFile;

import com.matgr.bankman.dto.VisualisationDTO;

public interface ILineItemService {

	void save(MultipartFile file);
	VisualisationDTO visualiseBetweenDates(LocalDate from, LocalDate to);
	
}
