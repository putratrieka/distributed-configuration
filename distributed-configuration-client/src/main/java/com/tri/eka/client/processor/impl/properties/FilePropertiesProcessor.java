package com.tri.eka.client.processor.impl.properties;

import org.springframework.stereotype.Service;

import com.tri.eka.client.processor.FileProcessor;
import com.tri.eka.client.processor.FileProcessorProvider;

@Service
public class FilePropertiesProcessor implements FileProcessorProvider{
	
	private static final String PROPERTIES_CRITERIA = ".properties";

	@Override
	public FileProcessor process() {
		return new FilePropertiesProcessorImpl();
	}

	@Override
	public boolean validator(String filetype) {
		if (filetype == null) {
			return false;
		}
		return PROPERTIES_CRITERIA.equals(filetype);
	}

}
