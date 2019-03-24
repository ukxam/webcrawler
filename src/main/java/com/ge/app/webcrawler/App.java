package com.ge.app.webcrawler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.ge.app.webcrawler.model.WebPage;
import com.ge.app.webcrawler.processor.WebPageProcessor;
import com.ge.app.webcrawler.processor.WebPageProcessorImpl;
import com.ge.app.webcrawler.util.ParseUtils;

/**
 *
 *
 */
public class App {
	public static void main(String[] args) throws InterruptedException {
		String inputFile = "input.txt";
		List<String> fileNames;
		try (Stream<String> stream = Files.lines(Paths.get(inputFile))) {
			fileNames = stream.collect(Collectors.toList());
		} catch (IOException e) {
			System.out.println("Invalid or no input provided !!! ");
			return;
		}
		if (fileNames.isEmpty()) {
			System.out.println("No locations present in the input file !!! ");
			return;
		}

		ExecutorService executorService = Executors.newFixedThreadPool(10);
		for (String webPageName : fileNames) {
			WebPageProcessor processor = new WebPageProcessorImpl();
			CompletableFuture.supplyAsync(() -> {
				return ParseUtils.parseFileToRetrievePages(webPageName);
			}, executorService)
			.thenApplyAsync(res -> processor.crawlWebPages(webPageName,res))
			.thenAcceptAsync(res -> processor.printCrawlingResults(res));
		}
	}
}
