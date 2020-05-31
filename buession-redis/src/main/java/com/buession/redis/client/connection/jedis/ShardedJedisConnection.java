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

import com.buession.core.Executor;
import com.buession.redis.client.connection.datasource.DataSource;
import com.buession.redis.client.connection.datasource.jedis.ShardedJedisDataSource;
import com.buession.redis.client.connection.datasource.jedis.ShardedJedisPoolDataSource;
import com.buession.redis.client.jedis.JedisClientUtils;
import com.buession.redis.core.ShardedRedisNode;
import com.buession.redis.exception.NotSupportedTransactionCommandException;
import com.buession.redis.exception.RedisException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * @author Yong.Teng
 */
public class ShardedJedisConnection extends AbstractJedisRedisConnection<ShardedJedis> implements JedisRedisConnection<ShardedJedis> {

	private ShardedJedisPool pool;

	private ShardedJedis shardedJedis;

	private final static Logger logger = LoggerFactory.getLogger(ShardedJedisConnection.class);

	public ShardedJedisConnection(){
		super();
	}

	public ShardedJedisConnection(ShardedJedisDataSource dataSource){
		super(dataSource);
	}

	@Override
	public boolean isTransaction(){
		return JedisClientUtils.isInMulti(shardedJedis);
	}

	@Override
	public void multi(){
		throw new NotSupportedTransactionCommandException("ShardedJedis cloud not suppported transaction.");
	}

	@Override
	public void exec(){
		throw new NotSupportedTransactionCommandException("ShardedJedis cloud not suppported transaction.");
	}

	@Override
	public void discard(){
		throw new NotSupportedTransactionCommandException("ShardedJedis cloud not suppported transaction.");
	}

	@Override
	protected void doConnect() throws IOException{
		DataSource dataSource = getDataSource();
		if(dataSource == null){
			return;
		}

		if(dataSource instanceof ShardedJedisPoolDataSource){
			ShardedJedisPoolDataSource shardedJedisPoolDataSource = (ShardedJedisPoolDataSource) dataSource;
			pool = shardedJedisPoolDataSource.getPool();
			shardedJedis = pool.getResource();

			if(logger.isInfoEnabled()){
				logger.info("ShardedJedis initialize with pool, db {} success, size: {}.", dataSource.getDatabase(),
						shardedJedis.getAllShardInfo().size());
			}
		}else{
			ShardedJedisDataSource shardedJedisDataSource = (ShardedJedisDataSource) dataSource;
			Set<ShardedRedisNode> shardedRedisNodes = shardedJedisDataSource.getRedisNodes();
			List<JedisShardInfo> shardInfos = JedisClientUtils.createJedisShardInfo(shardedRedisNodes,
					dataSource.getDatabase(), dataSource.getConnectTimeout(), dataSource.getSoTimeout(),
					dataSource.isUseSsl(), dataSource.getSslSocketFactory(), dataSource.getSslParameters(),
					dataSource.getHostnameVerifier());

			shardedJedis = new ShardedJedis(shardInfos);

			logger.info("ShardedJedis initialize with db {} success, size: {}.", dataSource.getDatabase(),
					shardInfos.size());
		}
	}

	@Override
	protected <C, R> R doExecute(Executor<C, R> executor) throws RedisException{
		return executor.execute((C) shardedJedis);
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
		return shardedJedis == null || checkConnect() == false;
	}

	@Override
	protected void doDisconnect() throws IOException{
		super.doDisconnect();
		if(shardedJedis != null){
			shardedJedis.disconnect();
		}
	}

	@Override
	protected void doClose() throws IOException{
		super.doClose();
		if(shardedJedis != null){
			shardedJedis.close();
		}
	}

}
