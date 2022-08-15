package com.tri.eka.client.manager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.tri.eka.client.factory.FileProcessorFactory;
import com.tri.eka.client.processor.FileProcessorProvider;

@Service
public class FileManager {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FileProcessorFactory.class);

	private static final String SCAN_PACKAGE_SPLIT_TOKEN = ",";

	private static final Object EMPTY_STRING = "";
	
	@Autowired
	List<FileProcessorProvider> processorProvider;

	@Value("${tdisconf.classpath}")
	private String scanPackage;
	
	@PostConstruct
	public void execute() {
		/**
		 * Split scan package
		 */
		LOGGER.info("<<<======= START SCANNING PACKAGE ======>>>");
		List<String> packageList = parseStringToStringList(scanPackage);
		Set<String> uniquePackageList = new HashSet<>();
		uniquePackageList.addAll(packageList);

		for (String pack : uniquePackageList) {
			/**
			 *  - scan all class anotated with @FileConfiguration from scan package
			 *  
			 *  - parsing and store key map
			 */
			
			FileProcessorFactory.getInstance().start();
		}
//		processorProvider.stream().filter(p -> p.validator(".properties")).findAny();
	}

	private List<String> parseStringToStringList(String scanPackage) {
		if (scanPackage == null || scanPackage.equals(EMPTY_STRING)) {
			return new ArrayList<>();
		}
		return Arrays.asList(scanPackage.split(SCAN_PACKAGE_SPLIT_TOKEN));
	}

}
