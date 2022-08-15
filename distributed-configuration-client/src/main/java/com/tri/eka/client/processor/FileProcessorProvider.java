package com.tri.eka.client.processor;

public interface FileProcessorProvider {
	
	boolean validator(String filetype);
	
	FileProcessor process();

}
