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
import redis.clients.jedis.ShardedJedis;

import java.util.Set;

/**
 * Jedis 分片模式数据源抽象类
 *
 * @author Yong.Teng
 */
public abstract class AbstractShardedJedisDataSource extends AbstractJedisDataSource<ShardedJedis> implements ShardedJedisDataSource {

	/**
	 * Redis 分片主机节点
	 */
	private Set<ShardedRedisNode> redisNodes;

	/**
	 * 构造函数
	 *
	 * @param redisNodes
	 * 		Redis 分片主机节点
	 */
	public AbstractShardedJedisDataSource(Set<ShardedRedisNode> redisNodes){
		super();
		this.redisNodes = redisNodes;
	}

	/**
	 * 构造函数
	 *
	 * @param redisNodes
	 * 		Redis 分片主机节点
	 * @param database
	 * 		数据库
	 */
	public AbstractShardedJedisDataSource(Set<ShardedRedisNode> redisNodes, int database){
		this(redisNodes);
		setDatabase(database);
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
	public AbstractShardedJedisDataSource(Set<ShardedRedisNode> redisNodes, int database, int connectTimeout,
			int soTimeout){
		this(redisNodes, database);
		setConnectTimeout(connectTimeout);
		setSoTimeout(soTimeout);
	}

	@Override
	public Set<ShardedRedisNode> getRedisNodes(){
		return redisNodes;
	}

}
