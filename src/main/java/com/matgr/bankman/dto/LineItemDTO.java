package com.matgr.bankman.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LineItemDTO {
	
	@JsonProperty("Date")
	private String date;
	@JsonProperty("Description")
	private String description;
	@JsonProperty("Amount")
	private String amount;
	@JsonProperty("Balance")
	private String balance;

}
