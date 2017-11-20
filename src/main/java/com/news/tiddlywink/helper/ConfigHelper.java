package com.news.tiddlywink.helper;

import java.io.FileReader;
import java.util.Iterator;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.json.simple.parser.JSONParser;

public class ConfigHelper {
	public static void main(String args[]){
		JSONObject regions = new JSONObject();
		try {
			System.out.println("*******getting Regions*******");
			JSONParser parser = new JSONParser();
			String data;
			data = (String) parser.parse(new FileReader("src/main/resources/mymovieplan_data.json")).toString();
			JSONObject citiesData = new JSONObject(data);
			Iterator itr = citiesData.keys();
			while(itr.hasNext()){
				String key = (String) itr.next();
				JSONObject regionsData = citiesData.getJSONObject(key);
				Iterator itr1 = regionsData.keys();
				JSONArray subRegions = new JSONArray();
				while(itr1.hasNext()){
					subRegions.put(itr1.next());
				}
				regions.put(key, subRegions);
			}
			System.out.println(regions);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
