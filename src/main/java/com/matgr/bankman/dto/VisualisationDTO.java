package com.matgr.bankman.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.matgr.bankman.entity.LineItem;

import lombok.Data;

@Data
public class VisualisationDTO {
	
	private List<CategoryVisualisationDTO> categories = new ArrayList<>();
	private BigDecimal totalSpent = new BigDecimal("0.00");
	private LocalDate from;
	private LocalDate to;

	public void addLineItem(LineItem item) {
		
		addCategory(item);
		totalSpent = totalSpent.add(item.getAmount());
		
	}
	
	public void setCategoryPercentages() {
		
		for(CategoryVisualisationDTO category : categories) {
			BigDecimal percentage = category.getAmountSpent().divide(totalSpent, 4, RoundingMode.HALF_UP);
			category.setPercentageOfTotal(percentage);
		}
		
	}
	
	private void addCategory(LineItem item) {
		
		long lineItemCategory = item.getDescription().getCategory().getId();
		
		for(CategoryVisualisationDTO category : categories) {
			if(category.getCategoryId() == lineItemCategory) {
				category.setAmountSpent(category.getAmountSpent().add(item.getAmount()));
				return;
			}
		}
		
		CategoryVisualisationDTO category = new CategoryVisualisationDTO(item);
		categories.add(category);
		
	}
	
}
