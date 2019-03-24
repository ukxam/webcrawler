package com.ge.app.webcrawler.processor;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.ge.app.webcrawler.model.WebPage;
import com.ge.app.webcrawler.representation.WebPageCrawlResult;

public class WebPageProcessorImpl implements WebPageProcessor {
	Map<String,WebPage> allPagesRepository;
	String webPage;
	Set<String> successTraces,skippedTraces,errorTraces;
		
	@Override
	public WebPageCrawlResult crawlWebPages(String sourcePage, Set<WebPage> pages) {
		init(pages);

		// retrieve first page as source page for crawling
		WebPage firstPage = pages.stream().findFirst().get();
		crawlPage(firstPage);

		WebPageCrawlResult crawlResult = new WebPageCrawlResult();
		crawlResult.setWebPage(sourcePage);
		crawlResult.setSuccess(successTraces);
		crawlResult.setSkipped(skippedTraces);
		crawlResult.setError(errorTraces);
		return crawlResult;
	}
	
	// initialize the result sets and cache repo for all Pages
	private void init(Set<WebPage> pages){
		successTraces = new TreeSet<>();
		skippedTraces = new TreeSet<>();
		errorTraces = new TreeSet<>();
		allPagesRepository = pages.stream().collect(Collectors.toMap(WebPage::getAddress,Function.identity()));
	}

	// crawl each page and add to result sets
	private void crawlPage(WebPage page){
		if(page == null || page.getAddress().isEmpty())
			return;
		
		String currentPage = page.getAddress();
		if(successTraces.contains(currentPage)){
			skippedTraces.add(currentPage);
			return;
		}
		
		successTraces.add(currentPage);
		
		if(page.getLinks()==null || page.getLinks().isEmpty()){
			return;
		}
		for(String link : page.getLinks()){
			if(!(skippedTraces.contains(link) || errorTraces.contains(link))){
				if(!allPagesRepository.isEmpty() && allPagesRepository.keySet().contains(link))
					crawlPage(allPagesRepository.get(link));
				else
					errorTraces.add(link);
			}
		}
	}

	// print the result sets
	@Override
	public void printCrawlingResults(WebPageCrawlResult result) {
		System.out.println(result.toString());
	}

}
