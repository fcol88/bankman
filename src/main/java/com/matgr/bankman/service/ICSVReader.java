package com.matgr.bankman.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface ICSVReader {
	
	<T> List<T> readValuesFromFile(Class<T> type, MultipartFile file);

}
