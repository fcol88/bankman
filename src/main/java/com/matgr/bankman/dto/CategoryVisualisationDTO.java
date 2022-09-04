package com.matgr.bankman.dto;

import java.math.BigDecimal;
import java.util.Comparator;

import com.matgr.bankman.entity.LineItem;

import lombok.Data;

@Data
public class CategoryVisualisationDTO {

	public CategoryVisualisationDTO(LineItem item) {
		this.categoryId = item.getDescription().getCategory().getId();
		this.category = item.getDescription().getCategory().getName();
		this.amountSpent = item.getAmount();
	}

	private long categoryId;
	private String category;
	private BigDecimal amountSpent;
	private BigDecimal percentageOfTotal;

	public static final Comparator<CategoryVisualisationDTO> CATEGORY_COMPARATOR = (CategoryVisualisationDTO item1, CategoryVisualisationDTO item2) -> {

		String category1 = item1.getCategory().toUpperCase();
		String category2 = item2.getCategory().toUpperCase();
		return category1.compareTo(category2);

	};
	
	public static final Comparator<CategoryVisualisationDTO> AMOUNT_COMPARATOR = (CategoryVisualisationDTO item1, CategoryVisualisationDTO item2) -> {
		
		BigDecimal amount1 = item1.getAmountSpent();
		BigDecimal amount2 = item2.getAmountSpent();
		return amount2.compareTo(amount1);
		
	};

}
