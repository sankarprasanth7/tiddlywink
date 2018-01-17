/**
 *
 */
package com.news.tiddlywink.api;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import com.news.tiddlywink.context.TiddlywinkServletContext;
import com.news.tiddlywink.utils.TiddlywinkConstants;

/**
 * @author naresh
 *
 */
public class TestECQueries {

	public static Client transportClient = null;
	public static final String SHOPAI_CLUSTER_NAME="movie-cluster";
	public static final String SHOPAI_ELASTIC_MASTER_HOST_NAME = "35.199.39.211";
	//public static final String SHOPAI_ELASTIC_MASTER_HOST_NAME = "176.9.140.162";
	public static final Integer SHOPAI_ELASTIC_PORT=9300;


	public static void getAllFieldsByCategory(){




		Settings settings = Settings.builder()
		        .put("cluster.name", "movie-cluster").put("transport.tcp.port", "9300").build();
		try {
			transportClient = new PreBuiltTransportClient(settings)
			        .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(TiddlywinkConstants.SHOPAI_ELASTIC_MASTER_HOST_NAME), TiddlywinkConstants.SHOPAI_ELASTIC_PORT));

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		GetResponse getResponse = transportClient
				.prepareGet("trending",
						"baidyabati", "beach_girls_2")
				.get();
		String sourceAsString = getResponse.getSourceAsString();
		System.out.println(sourceAsString);
	}
	public static void main(String args[]){
		getAllFieldsByCategory();
	}
}
