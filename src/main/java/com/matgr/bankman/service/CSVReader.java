package com.matgr.bankman.service;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

@Service
public class CSVReader implements ICSVReader {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CSVReader.class);
	
	@Override
	public <T> List<T> readValuesFromFile(Class<T> type, MultipartFile file) {
		
		try {
			
			CsvSchema csvSchema = CsvSchema.emptySchema().withHeader();
			CsvMapper mapper = new CsvMapper();
			MappingIterator<T> readValues = mapper.readerFor(type).with(csvSchema).readValues(file.getBytes());
			return readValues.readAll();
			
		} catch (Exception e) {
			
			LOGGER.error("Error occurred while loading object list from file " + file.getOriginalFilename(), e);
			return Collections.emptyList();
			
		}
		
	}

}
