/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license agreements.
 * See the NOTICE file distributed with this work for additional information regarding copyright ownership.
 * The ASF licenses this file to you under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and limitations under the License.
 *
 * =========================================================================================================
 *
 * This software consists of voluntary contributions made by many individuals on behalf of the
 * Apache Software Foundation. For more information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 * +-------------------------------------------------------------------------------------------------------+
 * | License: http://www.apache.org/licenses/LICENSE-2.0.txt 										       |
 * | Author: Yong.Teng <webmaster@buession.com> 													       |
 * | Copyright @ 2013-2020 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.client.connection.datasource.jedis;

import com.buession.redis.core.ShardedRedisNode;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.ShardedJedis;

import java.util.Set;

/**
 * Jedis 分片模式连接池数据源
 *
 * @author Yong.Teng
 */
public class ShardedJedisPoolDataSource extends AbstractShardedJedisDataSource implements PoolJedisDataSource<ShardedJedis> {

	/**
	 * 连接池配置
	 */
	private JedisPoolConfig poolConfig;

	/**
	 * 构造函数
	 *
	 * @param redisNodes
	 * 		Redis 分片主机节点
	 */
	public ShardedJedisPoolDataSource(Set<ShardedRedisNode> redisNodes){
		super(redisNodes);
	}

	/**
	 * 构造函数
	 *
	 * @param redisNodes
	 * 		Redis 分片主机节点
	 * @param database
	 * 		数据库
	 */
	public ShardedJedisPoolDataSource(Set<ShardedRedisNode> redisNodes, int database){
		super(redisNodes, database);
	}

	/**
	 * 构造函数
	 *
	 * @param redisNodes
	 * 		Redis 分片主机节点
	 * @param database
	 * 		数据库
	 * @param connectTimeout
	 * 		连接超时
	 * @param soTimeout
	 * 		读取超时
	 */
	public ShardedJedisPoolDataSource(Set<ShardedRedisNode> redisNodes, int database, int connectTimeout,
			int soTimeout){
		super(redisNodes, database, connectTimeout, soTimeout);
	}

	/**
	 * 构造函数
	 *
	 * @param redisNodes
	 * 		Redis 分片主机节点
	 * @param poolConfig
	 * 		连接池配置
	 */
	public ShardedJedisPoolDataSource(Set<ShardedRedisNode> redisNodes, JedisPoolConfig poolConfig){
		super(redisNodes);
		this.poolConfig = poolConfig;
	}

	/**
	 * 构造函数
	 *
	 * @param redisNodes
	 * 		Redis 分片主机节点
	 * @param database
	 * 		数据库
	 * @param poolConfig
	 * 		连接池配置
	 */
	public ShardedJedisPoolDataSource(Set<ShardedRedisNode> redisNodes, int database, JedisPoolConfig poolConfig){
		super(redisNodes, database);
		this.poolConfig = poolConfig;
	}

	/**
	 * 构造函数
	 *
	 * @param redisNodes
	 * 		Redis 分片主机节点
	 * @param database
	 * 		数据库
	 * @param connectTimeout
	 * 		连接超时
	 * @param soTimeout
	 * 		读取超时
	 * @param poolConfig
	 * 		连接池配置
	 */
	public ShardedJedisPoolDataSource(Set<ShardedRedisNode> redisNodes, int database, int connectTimeout,
			int soTimeout, JedisPoolConfig poolConfig){
		super(redisNodes, database, connectTimeout, soTimeout);
		this.poolConfig = poolConfig;
	}

	@Override
	public JedisPoolConfig getPoolConfig(){
		return poolConfig;
	}

	@Override
	public void setPoolConfig(JedisPoolConfig poolConfig){
		this.poolConfig = poolConfig;
	}

}
