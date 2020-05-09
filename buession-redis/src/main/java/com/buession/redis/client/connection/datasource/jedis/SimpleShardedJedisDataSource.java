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
import com.buession.lang.Status;
import com.buession.redis.client.ClientConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yong.Teng
 */
public class SimpleShardedJedisDataSource extends AbstractJedisRedisDataSource<ShardedJedis> implements
		ShardedJedisDataSource {

	private ShardedJedis shardedJedis;

	private final static Logger logger = LoggerFactory.getLogger(SimpleShardedJedisDataSource.class);

	public SimpleShardedJedisDataSource(){
		super();
	}

	public SimpleShardedJedisDataSource(ClientConfiguration clientConfiguration){
		super(clientConfiguration);
	}

	@Override
	public Status connect(){
		ClientConfiguration configuration = getClientConfiguration();
		List<JedisShardInfo> jedisShardInfos = new ArrayList<>();

		JedisShardInfo jedisShardInfo = new JedisShardInfo(configuration.getHost(), configuration.getClientName(),
				configuration.getPort(), 0, configuration.getWeight(), configuration.isUseSsl(), configuration
				.getSslSocketFactory(), configuration.getSslParameters(), configuration.getHostnameVerifier());

		jedisShardInfo.setConnectionTimeout(configuration.getConnectTimeout());
		jedisShardInfo.setSoTimeout(configuration.getSoTimeout());

		ReflectUtils.setField(jedisShardInfo, "db", configuration.getDatabase());

		shardedJedis = new ShardedJedis(jedisShardInfos);

		logger.info("Simple sharded jedis datasource initialize with db {} success, name: {}.", configuration
				.getDatabase(), configuration.getClientName());

		return null;
	}

	@Override
	public ShardedJedis getRedisClient(){
		return shardedJedis;
	}

	@Override
	public boolean isClosed(){
		return shardedJedis == null ? true : false;
	}

	@Override
	public void disconnect() throws IOException{
		if(shardedJedis != null){
			shardedJedis.disconnect();
		}
	}

	@Override
	public void close() throws IOException{
		if(shardedJedis != null){
			shardedJedis.close();
		}
	}

}
