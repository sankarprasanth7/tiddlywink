///**
// *
// */
//package com.news.tiddlywink.connectors;
//
//import java.util.HashSet;
//import java.util.Set;
//
//import com.news.tiddlywink.utils.TiddlywinkConstants;
//
//import redis.clients.jedis.HostAndPort;
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.JedisCluster;
//import redis.clients.jedis.JedisPool;
//import redis.clients.jedis.JedisPoolConfig;
//import redis.clients.jedis.exceptions.JedisConnectionException;
//
///**
// * @author naresh
// *
// */
//public class RedisConnector {
//	public static Jedis jedis;
//	public static JedisPool jedisPool ;
//	public RedisConnector() {
//		//jedisPool = new JedisPool(new JedisPoolConfig(), "176.9.140.162");
//		jedisPool = new JedisPool(new JedisPoolConfig(),TiddlywinkConstants.REDIS_HOST_IP, 22185, 30000, "FUQSKOKZLBGEUGCV" );
//
//	}
//
//	public static Jedis getJedisConnection() {
//		try {
//
//			return jedisPool.getResource();
//		} catch (JedisConnectionException e) {
//			System.out.println("Could not establish Redis connection. Is the Redis running?");
//			throw e;
//		}
//	}
//
////	public static void init() {
////		/*
////		 * Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
////		 * //Jedis Cluster will attempt to discover cluster nodes automatically
////		 * jedisClusterNodes.add(new HostAndPort("176.9.140.162", 6379)); jc =
////		 * new JedisCluster(jedisClusterNodes);
////		 */
////		// jedis = new Jedis("176.9.140.162",6379);
////		// System.out.println("Redis Connection :"+jedis.isConnected());
////
////		pool = new JedisPool(new JedisPoolConfig(), "176.9.140.162");
////
////	}
//
////	public static Jedis getConnector() {
////		return pool.getResource();
////	}
//
//}
