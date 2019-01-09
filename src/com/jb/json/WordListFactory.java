package com.jb.json;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.ParseException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class WordListFactory {
	
	private WordListFactory() {};
	
	/**
	 * Retrieves JSON word data from url
	 * @param url : URL string
	 * @return : Json object
	 * @throws IOException
	 * @throws ParseException 
	 */
	public static JSONObject getJsonList(String url) throws IOException, org.json.simple.parser.ParseException {
		InputStream is = new URL(url).openStream();
		
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = null;
		
		System.out.println("Starting the parse...");
		jsonObject = (JSONObject) jsonParser.parse(new InputStreamReader(is));
		System.out.println("JSON Data has been obtained");
		is.close();
		
		return jsonObject;
	}
	
}
