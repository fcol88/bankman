package com.matgr.bankman.service;

import com.matgr.bankman.dto.SiftDTO;
import com.matgr.bankman.entity.Description;

public interface IDescriptionService {

	Description get(String description);
	Description getNextUnidentifiedDescription();
	int getUnidentifiedDescriptionCount();
	Description setCategory(SiftDTO dto);
	
}
