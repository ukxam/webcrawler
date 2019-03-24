package com.ge.app.webcrawler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.json.JSONException;
import org.json.simple.parser.ParseException;

import com.ge.app.webcrawler.model.WebPage;
import com.ge.app.webcrawler.processor.WebPageProcessor;
import com.ge.app.webcrawler.processor.WebPageProcessorImpl;
import com.ge.app.webcrawler.util.ParseUtils;

/**
 *
 *
 */
public class App {
	
	static String INPUT_FILE = "input.txt";
	
	public static void main(String[] args) throws InterruptedException, IOException{

		List<String> fileNames;
		try (Stream<String> stream = Files.lines(Paths.get(INPUT_FILE))) {
			fileNames = stream.collect(Collectors.toList());
		} catch (IOException e) {
			String msg = "Invalid or no input provided !!! ";
			System.out.println(msg);
			throw e;
		}
		
		if (fileNames.isEmpty()) {
			String msg = "No locations present in the input file !!! ";
			System.out.println(msg);
			throw new RuntimeException(msg);
		}

		ExecutorService executorService = Executors.newFixedThreadPool(10);
		for (String webPageName : fileNames) {
			WebPageProcessor processor = new WebPageProcessorImpl();
			CompletableFuture.supplyAsync(() ->ParseUtils.parseFileToRetrievePages(webPageName)
			, executorService)
			.thenApplyAsync(res -> processor.crawlWebPages(webPageName,res))
			.thenAcceptAsync(res -> processor.printCrawlingResults(res));
		}
	}
}
