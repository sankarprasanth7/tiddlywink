/**
 * 
 */
package com.news.tiddlywink;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;

/**
 * @author naresh
 *
 */
public class TestECQueries {
	
	public static Client transportClient = null;
	public static final String SHOPAI_CLUSTER_NAME="news-cluster";
	public static final String SHOPAI_ELASTIC_MASTER_HOST_NAME = "localhost";
	//public static final String SHOPAI_ELASTIC_MASTER_HOST_NAME = "176.9.140.162";
	public static final Integer SHOPAI_ELASTIC_PORT=9300;
	
	
	public static void getAllFieldsByCategory(){
		
		
		
		
		Settings settings = Settings.settingsBuilder().put("cluster.name",SHOPAI_CLUSTER_NAME ).build();
		try {
			transportClient = TransportClient.builder().settings(settings).build()
					.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(SHOPAI_ELASTIC_MASTER_HOST_NAME), SHOPAI_ELASTIC_PORT));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();


        SearchRequestBuilder srb = transportClient.prepareSearch("shopcompare")
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH);
        MatchQueryBuilder mqb;

        mqb = QueryBuilders.matchQuery("category", "desktops");
        srb.setQuery(mqb);
        SearchResponse response = srb.execute().actionGet();
        long totalHitCount = response.getHits().getTotalHits();

        System.out.println(response.getHits().getTotalHits());

        for (SearchHit hit : response.getHits()) {          
            result.add(hit.getSource());
        }

        System.out.println(result);
		
	}
	
	
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		getAllFieldsByCategory();

	}

}
