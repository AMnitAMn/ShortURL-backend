package com.capg.shorturl.utils;

import java.util.regex.Matcher;

public class URLValidator {
	
	public static final URLValidator INSTANCE = new URLValidator();
	
	private static final String URL_REGEX = "https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_\\+.~#?&//=]*)";
	private static final java.util.regex.Pattern URL_PATTERN = java.util.regex.Pattern.compile(URL_REGEX);
	
	private URLValidator() {
		
	}
	
	public boolean validateURL(String url) {
		Matcher m = URL_PATTERN.matcher(url);
		return m.matches();
	}
	
}
