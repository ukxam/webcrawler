package com.ge.app.webcrawler.representation;

import java.util.Set;

public class WebPageCrawlResult {

	private String webPage;
	private Set<String> success;
	private Set<String> skipped;
	private Set<String> error;
	public String getWebPage() {
		return webPage;
	}
	public void setWebPage(String webPage) {
		this.webPage = webPage;
	}
	public Set<String> getSuccess() {
		return success;
	}
	public void setSuccess(Set<String> success) {
		this.success = success;
	}
	public Set<String> getSkipped() {
		return skipped;
	}
	public void setSkipped(Set<String> skipped) {
		this.skipped = skipped;
	}
	public Set<String> getError() {
		return error;
	}
	public void setError(Set<String> error) {
		this.error = error;
	}
	@Override
	public String toString() {
		return (webPage != null ? "webPage=" + webPage + ", " + System.lineSeparator() : "")
				+ (success != null ? "success=" + success + ", " + System.lineSeparator() : "")
				+ (skipped != null ? "skipped=" + skipped + ", " + System.lineSeparator() : "") 
				+ (error != null ? "error=" + error + System.lineSeparator() : "");
	}
	
	
	
	
}
