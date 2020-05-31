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

import com.buession.redis.client.jedis.JedisClientUtils;
import com.buession.redis.core.ShardedRedisNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocketFactory;
import java.util.List;
import java.util.Set;

/**
 * Jedis 分片模式连接池数据源
 *
 * @author Yong.Teng
 */
public class ShardedJedisPoolDataSource extends AbstractShardedJedisDataSource implements PoolJedisDataSource<ShardedJedis> {

	private JedisPoolConfig poolConfig;

	private ShardedJedisPool pool;

	private final static Logger logger = LoggerFactory.getLogger(ShardedJedisPoolDataSource.class);

	public ShardedJedisPoolDataSource(){
		super();
	}

	public ShardedJedisPoolDataSource(JedisPoolConfig poolConfig){
		this.poolConfig = poolConfig;
	}

	public ShardedJedisPoolDataSource(Set<ShardedRedisNode> redisNodes, JedisPoolConfig poolConfig){
		super(redisNodes);
		this.poolConfig = poolConfig;
	}

	public ShardedJedisPoolDataSource(Set<ShardedRedisNode> redisNodes, int database, JedisPoolConfig poolConfig){
		super(redisNodes, database);
		this.poolConfig = poolConfig;
	}

	public ShardedJedisPoolDataSource(Set<ShardedRedisNode> redisNodes, int database, int connectTimeout,
									  int soTimeout, JedisPoolConfig poolConfig){
		super(redisNodes, database, connectTimeout, soTimeout);
		this.poolConfig = poolConfig;
	}

	public ShardedJedisPoolDataSource(Set<ShardedRedisNode> redisNodes, int database, boolean useSsl,
									  JedisPoolConfig poolConfig){
		super(redisNodes, database, useSsl);
		this.poolConfig = poolConfig;
	}

	public ShardedJedisPoolDataSource(Set<ShardedRedisNode> redisNodes, int database,
									  SSLSocketFactory sslSocketFactory, SSLParameters sslParameters,
									  HostnameVerifier hostnameVerifier, JedisPoolConfig poolConfig){
		super(redisNodes, database, sslSocketFactory, sslParameters, hostnameVerifier);
		this.poolConfig = poolConfig;
	}

	public ShardedJedisPoolDataSource(Set<ShardedRedisNode> redisNodes, int database, int connectTimeout,
									  int soTimeout, boolean useSsl, JedisPoolConfig poolConfig){
		super(redisNodes, database, connectTimeout, soTimeout, useSsl);
		this.poolConfig = poolConfig;
	}

	public ShardedJedisPoolDataSource(Set<ShardedRedisNode> redisNodes, int database, int connectTimeout,
									  int soTimeout, SSLSocketFactory sslSocketFactory, SSLParameters sslParameters,
									  HostnameVerifier hostnameVerifier, JedisPoolConfig poolConfig){
		super(redisNodes, database, connectTimeout, soTimeout, sslSocketFactory, sslParameters, hostnameVerifier);
		this.poolConfig = poolConfig;
	}

	public ShardedJedisPoolDataSource(Set<ShardedRedisNode> redisNodes, int database, int connectTimeout,
									  int soTimeout, boolean useSsl, SSLSocketFactory sslSocketFactory,
									  SSLParameters sslParameters, HostnameVerifier hostnameVerifier,
									  JedisPoolConfig poolConfig){
		super(redisNodes, database, connectTimeout, soTimeout, useSsl, sslSocketFactory, sslParameters,
				hostnameVerifier);
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

	@Override
	public ShardedJedisPool getPool(){
		if(pool == null){
			List<JedisShardInfo> shardInfos = JedisClientUtils.createJedisShardInfo(getRedisNodes(), getDatabase(),
					getConnectTimeout(), getSoTimeout(), isUseSsl(), getSslSocketFactory(), getSslParameters(),
					getHostnameVerifier());

			pool = new ShardedJedisPool(getPoolConfig(), shardInfos);

			logger.info("ShardedJedisPool initialize with for {} shard info.", shardInfos.size());
		}

		return pool;
	}

}
