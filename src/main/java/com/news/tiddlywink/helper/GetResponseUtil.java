package com.news.tiddlywink.helper;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetResponseUtil {
	private static final Logger logger = LoggerFactory.getLogger(GetResponseUtil.class);
	public static JSONArray getProductResponse(SearchResponse response) {
		// TODO Auto-generated method stub

		JSONArray products = new JSONArray();

		if (response != null) {

			SearchHit[] results = response.getHits().getHits();
			for (SearchHit hit : results) {
				String sourceAsString = hit.getSourceAsString();
				if (sourceAsString != null) {
					try {

						JSONObject productObj = new JSONObject(sourceAsString);
						products.put(productObj);
					} catch (JSONException e) {
						logger.error("Error Fetching the Product List" + e.getMessage());
						e.printStackTrace();
					}
				}
			}

		}

		return products;
	}

}
