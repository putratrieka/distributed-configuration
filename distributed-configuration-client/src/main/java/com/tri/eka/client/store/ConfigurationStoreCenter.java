package com.tri.eka.client.store;

import java.util.HashMap;
import java.util.Map;

public class ConfigurationStoreCenter {

	/**
	 * store center should be 
	 * 
	 * {
	 * 	"fileNameKey:
	 * 		{"itemNameKey":"ItemProperty"}
	 * }
	 */
	private static Map<String, Map<String, Object>> storeCenter;

	public static void addNewFile(String fileName, Map<String, Object> fileItems) {
		if (storeCenter == null) {
			storeCenter = new HashMap<>();
		}
		
		storeCenter.put(fileName, fileItems);
	}
	
	public static void addNewItem(String fileKey, String itemKey, Object value) {
		Map<String, Object> items = getFile(itemKey);
		items.put(itemKey, value);
		
	}
	
	public static Map<String, Object> getFile(String fileName){
		Map<String, Object> files = storeCenter.get(fileName);
		if (files == null) {
			return new HashMap<>();
		}
		return files;
	}
	
	public static Object getItem(String fileName, String itemName) {
		return storeCenter.get(fileName) == null ? null : storeCenter.get(itemName).get(itemName);
	}
	
}
