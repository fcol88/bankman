package com.matgr.bankman.dto;

import com.matgr.bankman.entity.Description;

import lombok.Data;

@Data
public class SiftDTO {
	
	public SiftDTO() {
	}
	
	public SiftDTO(Description description) {
		this.descriptionId = description.getId();
	}
	
	private Long descriptionId;
	private Long categoryId;
	private String newCategory;

}
