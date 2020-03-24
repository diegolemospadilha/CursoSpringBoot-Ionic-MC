package com.lemospadilha.curso.boot.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class URL {
	
	public static String decodeParam(String s) {
		try {
			return URLDecoder.decode(s, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
	
	public static List<Integer> decodeIntList(String s){
		String[] listString = s.split(",");
		List<Integer> result = new ArrayList<Integer>();
		
		for(String value: listString) {
			result.add(Integer.valueOf(value));
		}
		
		return result;
		
		//return Arrays.asList(s.split(",")).stream().map(x -> Integer.parseInt(x)).collect(Collectors.toList());
	}
	
	
}
