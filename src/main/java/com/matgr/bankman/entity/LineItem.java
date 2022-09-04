package com.matgr.bankman.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;

@Entity
@Data
@Table(uniqueConstraints = {
	@UniqueConstraint(name="UniqueLineItem", columnNames= {"date","description_id","amount","balance"})
})
public class LineItem {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private LocalDate date;
	@ManyToOne
	private Description description;
	private BigDecimal amount;
	private BigDecimal balance;

}
