package com.capg.shorturl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.shorturl.model.Link;
import com.capg.shorturl.repository.LinkRepository;
import com.capg.shorturl.utils.IDConverter;

@Service
public class LinkServiceImpl implements LinkService {

	private final LinkRepository lr;
	
	@Autowired
	public LinkServiceImpl(LinkRepository lr) {
		this.lr = lr;
	}

	@Override
	public String getShortURL(String localURL, String longURL) {
		Link link = new Link(longURL);
		lr.saveAndFlush(link);
		String uniqueID = IDConverter.INSTANCE.createUniqueID(link.getId());
		//String baseString = formateLocalURLFromShortener(localURL);
		String shortenedURL = localURL + "/" + uniqueID;
		return shortenedURL;
	}

	@Override
	public String getLongURLfromID(String uniqueID) throws Exception{
		Long dictionaryKey = IDConverter.INSTANCE.getDictionaryKeyFromUniqueID(uniqueID);
		String longUrl = lr.findById(dictionaryKey).get().getUrl();
		return longUrl;
	}
	
	private String formateLocalURLFromShortener(String localURL) {
		String[] addressComponents = localURL.split("/");
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < addressComponents.length - 1; ++i) {
			sb.append(addressComponents[i]);
		}
		sb.append('/');
		return sb.toString();
	}
	
}
