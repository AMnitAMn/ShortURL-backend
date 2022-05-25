package com.capg.shorturl.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.TableGenerator;

@Entity
public class Link {
	
	@TableGenerator(name = "Link_Gen", initialValue = 100000)
	@Id
	@GeneratedValue(generator = "Link_Gen")
	private long id;
	
	private String url;
	
	public Link() {
		//Default Constructor
	}
	
	public Link(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public long getId() {
		return id;
	}
}
