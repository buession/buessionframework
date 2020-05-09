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

import com.buession.core.utils.ReflectUtils;
import com.buession.core.validator.Validate;
import com.buession.lang.Status;
import com.buession.redis.client.ClientConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yong.Teng
 */
public class ShardedJedisPoolDataSource extends AbstractJedisRedisDataSource<ShardedJedis> implements
		ShardedJedisDataSource, PoolJedisDataSource<ShardedJedis> {

	private JedisPoolConfig poolConfig;

	private ShardedJedisPool pool;

	private ShardedJedis shardedJedis;

	private final static Logger logger = LoggerFactory.getLogger(ShardedJedisPoolDataSource.class);

	public ShardedJedisPoolDataSource(){
		super();
	}

	public ShardedJedisPoolDataSource(ClientConfiguration clientConfiguration){
		super(clientConfiguration);
	}

	public ShardedJedisPoolDataSource(ClientConfiguration clientConfiguration, JedisPoolConfig poolConfig){
		super(clientConfiguration);
		this.poolConfig = poolConfig;
	}

	public JedisPoolConfig getPoolConfig(){
		return poolConfig;
	}

	public void setPoolConfig(JedisPoolConfig poolConfig){
		this.poolConfig = poolConfig;
	}

	@Override
	public ShardedJedisPool getPool(){
		return pool;
	}

	@Override
	public Status connect(){
		ClientConfiguration configuration = getClientConfiguration();
		List<JedisShardInfo> shardInfos = new ArrayList<>(1);
		JedisShardInfo jedisShardInfo = new JedisShardInfo(configuration.getHost(), configuration.getClientName(),
				configuration.getPort(), 0, configuration.getWeight(), configuration.isUseSsl(), configuration
				.getSslSocketFactory(), configuration.getSslParameters(), configuration.getHostnameVerifier());

		jedisShardInfo.setConnectionTimeout(configuration.getConnectTimeout());
		jedisShardInfo.setSoTimeout(configuration.getSoTimeout());

		shardInfos.add(jedisShardInfo);

		ReflectUtils.setField(jedisShardInfo, "db", configuration.getDatabase());

		pool = new ShardedJedisPool(getPoolConfig(), shardInfos);

		logger.info("Sharded jedis pool datasource initialize with for {} shard info.", shardInfos.size());

		return Status.SUCCESS;
	}

	@Override
	public ShardedJedis getRedisClient(){
		if(pool == null){
			return null;
		}

		shardedJedis = pool.getResource();
		return shardedJedis;
	}

	@Override
	public boolean isClosed(){
		return pool == null ? true : pool.isClosed();
	}

	@Override
	public void disconnect() throws IOException{
		if(shardedJedis != null){
			shardedJedis.disconnect();
		}
	}

	@Override
	public void close() throws IOException{
		shardedJedis.close();
	}

}
