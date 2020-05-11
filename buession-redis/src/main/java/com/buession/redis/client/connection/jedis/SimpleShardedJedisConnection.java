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
 * | Copyright @ 2013-2019 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.client.connection.jedis;

import com.buession.core.utils.ReflectUtils;
import com.buession.redis.client.connection.datasource.RedisDataSource;
import com.buession.redis.client.connection.datasource.jedis.SimpleShardedJedisDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yong.Teng
 */
public class SimpleShardedJedisConnection extends AbstractJedisRedisConnection<ShardedJedis> implements ShardedJedisConnection {

	private ShardedJedis shardedJedis;

	private final static Logger logger = LoggerFactory.getLogger(SimpleShardedJedisConnection.class);

	public SimpleShardedJedisConnection(){
		super();
	}

	public SimpleShardedJedisConnection(SimpleShardedJedisDataSource dataSource){
		super(dataSource);
	}

	@Override
	protected ShardedJedis getDelegate(RedisDataSource dataSource){
		return shardedJedis;
	}

	@Override
	protected void doConnect() throws IOException{
		if(getDataSource() == null || (getDataSource() instanceof SimpleShardedJedisDataSource) == false){
			return;
		}

		SimpleShardedJedisDataSource dataSource = (SimpleShardedJedisDataSource) getDataSource();

		List<JedisShardInfo> shardInfos = new ArrayList<>();

		JedisShardInfo shardInfo = new JedisShardInfo(dataSource.getHost(), dataSource.getClientName(),
				dataSource.getPort(), 0, dataSource.getWeight(), dataSource.isUseSsl(),
				dataSource.getSslSocketFactory(), dataSource.getSslParameters(), dataSource.getHostnameVerifier());

		shardInfo.setConnectionTimeout(dataSource.getConnectTimeout());
		shardInfo.setSoTimeout(dataSource.getSoTimeout());

		ReflectUtils.setField(shardInfo, "db", dataSource.getDatabase());

		shardedJedis = new ShardedJedis(shardInfos);

		logger.info("ShardedJedis initialize with db {} success, name: {}.", dataSource.getDatabase(),
				dataSource.getClientName());
	}

	@Override
	protected boolean checkConnect(){
		if(shardedJedis != null){
			for(Jedis jedis : shardedJedis.getAllShards()){
				if(jedis.isConnected()){
					return true;
				}
			}
		}

		return false;
	}

	@Override
	protected boolean checkClosed(){
		return shardedJedis == null ? true : checkConnect() == false;
	}

	@Override
	protected void doDisconnect() throws IOException{
		if(getDelegate() != null){
			getDelegate().disconnect();
		}
	}

	@Override
	protected void doClose() throws IOException{
		if(getDelegate() != null){
			getDelegate().close();
		}
	}

}
