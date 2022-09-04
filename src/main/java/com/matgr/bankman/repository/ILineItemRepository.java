package com.matgr.bankman.repository;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.matgr.bankman.entity.LineItem;

public interface ILineItemRepository extends JpaRepository <LineItem, Long>{
	
	Set<LineItem> findByDateBetween(LocalDate from, LocalDate to);

}
