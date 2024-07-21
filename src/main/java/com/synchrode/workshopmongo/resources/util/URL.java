package com.synchrode.workshopmongo.resources.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class URL {
	
	public static String decodeParam(String text) {
		try {
			return URLDecoder.decode(text, "UTF-8"); // UTF-8 é um padrão da web
		} catch (UnsupportedEncodingException e) {
			return "";
		} 
	}
}
