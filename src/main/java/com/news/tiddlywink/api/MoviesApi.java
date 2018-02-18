package com.news.tiddlywink.api;

import java.io.FileReader;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.elasticsearch.action.admin.indices.mapping.get.GetMappingsResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.cluster.metadata.MappingMetaData;
import org.elasticsearch.common.collect.ImmutableOpenMap;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.carrotsearch.hppc.cursors.ObjectObjectCursor;
import com.news.tiddlywink.context.TiddlywinkServletContext;
import com.news.tiddlywink.helper.GetResponseUtil;
import com.news.tiddlywink.utils.TiddlywinkConstants;

@Path("movies")
public class MoviesApi {
	private static final Logger logger = LoggerFactory.getLogger(MoviesApi.class);

	@GET
	@Path("/nowshowing/{city}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getNowShowingMovies(@Context HttpServletRequest requestContext, @PathParam("city") String city,
			@QueryParam("ninja") String ninja) {
		SearchRequestBuilder search = null;
		SearchResponse response = null;
		BoolQueryBuilder catBoolQuery = QueryBuilders.boolQuery();
		search = TiddlywinkServletContext.transportClient.prepareSearch("trending");
		catBoolQuery.should(QueryBuilders.matchQuery("city", city));
		search.setQuery(catBoolQuery);
		response = search.execute().actionGet();
		JSONArray movies = GetResponseUtil.getProductResponse(response);
		System.out.println(movies);
		return Response.status(200).entity(movies).build();
	}
	
	@GET
	@Path("/trending/{city}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTrendingMovies(@Context HttpServletRequest requestContext, @PathParam("city") String city,
			@QueryParam("ninja") String ninja) {
		SearchRequestBuilder search = null;
		SearchResponse response = null;
		BoolQueryBuilder catBoolQuery = QueryBuilders.boolQuery();
		search = TiddlywinkServletContext.transportClient.prepareSearch("trending");
		catBoolQuery.should(QueryBuilders.matchQuery("city", city));
		search.setQuery(catBoolQuery);
		search.addSort("percentage", SortOrder.DESC);
		response = search.setSize(5).execute().actionGet();
		JSONArray movies = GetResponseUtil.getProductResponse(response);
		System.out.println(movies);
		return Response.status(200).entity(movies).build();
	}
	
	@GET
	@Path("/upcomming/{city}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUpcommingMovies(@Context HttpServletRequest requestContext, @PathParam("city") String city,
			@QueryParam("ninja") String ninja) {
		SearchRequestBuilder search = null;
		SearchResponse response = null;
		BoolQueryBuilder catBoolQuery = QueryBuilders.boolQuery();
		search = TiddlywinkServletContext.transportClient.prepareSearch("upcomming");
		catBoolQuery.should(QueryBuilders.matchQuery("city", city));
		search.setQuery(catBoolQuery);
		response = search.execute().actionGet();
		JSONArray movies = GetResponseUtil.getProductResponse(response);
		System.out.println(movies);
		return Response.status(200).entity(movies).build();
	}

	@GET
	@Path("/movieInfo/{movieName}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProductsByCategory(@Context HttpServletRequest requestContext,@PathParam("movieName") String movieName,
			@QueryParam("ninja") String ninja) {
		GetResponse getResponse = TiddlywinkServletContext.transportClient
				.prepareGet("movies","movieInfo", movieName)
				.get();
		String sourceAsString = getResponse.getSourceAsString();
		JSONObject searchResponse = null;
		try {
			searchResponse = new JSONObject(sourceAsString);
			return Response.status(200).entity(searchResponse).build();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@GET
	@Path("/regions")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRegions(@Context HttpServletRequest requestContext, @QueryParam("ninja") String ninja) {
		JSONObject regions;
		try {
			regions = new JSONObject(TiddlywinkConstants.regions);
			return Response.status(200).entity(regions).build();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
