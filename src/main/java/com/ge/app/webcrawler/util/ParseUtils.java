package com.ge.app.webcrawler.util;

import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import com.ge.app.webcrawler.model.*;

public class ParseUtils {
	
	public static final String WEBPAGES = "pages";
	public static final String WEBPAGES_ADDRESS = "address";
	public static final String WEBPAGES_LINKS = "links";
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Set<WebPage> parseFileToRetrievePages(String fileName){
		JSONParser parser = new JSONParser();
		Set<WebPage> webpages = new LinkedHashSet();
		WebPage webpage;
		Set<String> links;
		try{
			Object object = parser.parse(new FileReader(fileName));
			JSONObject jsonObject = (JSONObject) object;
			JSONArray jsonObjects = (JSONArray) jsonObject.get(WEBPAGES);
			Iterator jsonIterator = jsonObjects.iterator();
			while(jsonIterator.hasNext()){
				JSONObject webpageJson = (JSONObject) jsonIterator.next();
				
				webpage = new WebPage();
				webpage.setAddress((String) webpageJson.get(WEBPAGES_ADDRESS));
				
				links = new LinkedHashSet<>();
				JSONArray jsonLinksObjects = (JSONArray) webpageJson.get(WEBPAGES_LINKS);
				Iterator jsonLinkIterator = jsonLinksObjects.iterator();
				while(jsonLinkIterator.hasNext()){
					String webpageLinkJson = (String) jsonLinkIterator.next();
					links.add(webpageLinkJson);
				}
				webpage.setLinks(links);
				webpages.add(webpage);
			}
			
		}
		catch(ParseException | JSONException e){
			System.out.println("Invalid Json File. Please retry using valid Json file.");
			} 
		catch (IOException e) {
			System.out.println("Missing input file location / File not found.");
			}
		return webpages;
	}
}