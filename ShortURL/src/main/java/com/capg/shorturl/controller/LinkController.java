package com.capg.shorturl.controller;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.capg.shorturl.service.LinkService;
import com.capg.shorturl.utils.URLValidator;

@RestController
public class LinkController {
	
	private final LinkService ls;
	@Autowired
	public LinkController(LinkService ls) {
		this.ls = ls;
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/shortener", method = RequestMethod.POST)
	public String getShortUrl(@RequestBody @Validated String url) throws Exception {
		
		if(URLValidator.INSTANCE.validateURL(url)) {
			String shortenedUrl = ls.getShortURL("http://localhost:8080", url);
			return shortenedUrl;
		}
		else {
			throw new Exception("Please enter a valid URL");
		}
		
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public RedirectView getUrl(@PathVariable String id, HttpServletRequest response) throws IOException, URISyntaxException, Exception {
		String redirectURLString = ls.getLongURLfromID(id);
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl(redirectURLString);
		return redirectView;
	}
	
}
