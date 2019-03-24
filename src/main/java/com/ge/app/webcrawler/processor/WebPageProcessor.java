package com.ge.app.webcrawler.processor;

import java.util.Map;
import java.util.Set;

import com.ge.app.webcrawler.model.WebPage;
import com.ge.app.webcrawler.representation.WebPageCrawlResult;

public interface WebPageProcessor {
	WebPageCrawlResult crawlWebPages(String sourcePage, Set<WebPage> pages);
	void printCrawlingResults(WebPageCrawlResult result);
}
