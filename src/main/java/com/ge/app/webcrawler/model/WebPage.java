/**
 * 
 */
package com.ge.app.webcrawler.model;

import java.util.Set;

/**
 * @author wHateVer
 *
 */
public class WebPage {

	private String address;
	private Set<String> links;
	
	public WebPage() {
	}
	
	public WebPage(String address, Set<String> links) {
		this.address = address;
		this.links = links;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Set<String> getLinks() {
		return links;
	}
	public void setLinks(Set<String> links) {
		this.links = links;
	}
	
	
}
