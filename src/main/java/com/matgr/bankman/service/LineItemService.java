package com.matgr.bankman.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.matgr.bankman.dto.LineItemDTO;
import com.matgr.bankman.dto.VisualisationDTO;
import com.matgr.bankman.entity.LineItem;
import com.matgr.bankman.repository.ILineItemRepository;

@Service
public class LineItemService implements ILineItemService {
	
	@Autowired
	private ICSVReader csvReader;
	@Autowired
	private IDescriptionService descriptionService;
	@Autowired
	private ILineItemRepository repository;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LineItemService.class);
	private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	@Override
	public void save(MultipartFile file) {
		
		List<LineItemDTO> lineItems = csvReader.readValuesFromFile(LineItemDTO.class, file);	
		List<LineItem> entities = convert(lineItems);
		for(LineItem entity : entities) {
			try {
				repository.save(entity);
			} catch(DataIntegrityViolationException e) {
				LOGGER.warn("Line item for transaction already exists!");
			}
		}
		
	}
	
	@Override
	public VisualisationDTO visualiseBetweenDates(LocalDate from, LocalDate to) {

		Set<LineItem> items = repository.findByDateBetween(from, to);
		
		if(items.isEmpty()) {
			return null;
		}
		
		VisualisationDTO dto = new VisualisationDTO();
		
		for(LineItem item : items) {
			dto.addLineItem(item);
		}
		
		dto.setCategoryPercentages();
		dto.setFrom(from);
		dto.setTo(to);
		
		return dto;
		
	}
	
	private List<LineItem> convert(List<LineItemDTO> lineItems) {
		
		List<LineItem> entities = new ArrayList<>();
		//this expects that amounts are marked -0.00 for debits and 0.00 for credits
		lineItems.removeIf(x -> !x.getAmount().contains("-"));
		for(LineItemDTO item : lineItems) {
			//break at gaps in the file - this probably means there's a total at the bottom
			if(item.getDate().isEmpty()) {
				break;
			}
			
			LineItem entity = new LineItem();
			entity.setDate(LocalDate.parse(item.getDate(), DATE_FORMAT));
			entity.setDescription(descriptionService.get(item.getDescription()));
			entity.setAmount(new BigDecimal(item.getAmount().replaceAll("[^\\d.]", "")));
			entity.setBalance(new BigDecimal(item.getBalance().replaceAll("[^\\d.]", "")));
			entities.add(entity);
			
		}
		
		return entities;
		
	}

}
