package com.capg.shorturl.service;

public interface LinkService {
	
	public String getShortURL(String localURL, String longURL);
	
	public String getLongURLfromID(String uniqueID) throws Exception;
}
