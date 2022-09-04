package com.matgr.bankman.service;

import java.util.List;

import com.matgr.bankman.dto.SiftDTO;
import com.matgr.bankman.entity.Category;

public interface ICategoryService {
	
	Category get(SiftDTO sift);
	List<Category> findAll();

}
