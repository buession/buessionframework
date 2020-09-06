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
import com.buession.core.utils.FieldUtils;
import com.buession.core.validator.Validate;
import com.buession.redis.client.connection.RedisShardedConnection;
import com.buession.redis.client.connection.SslConfiguration;
import com.buession.redis.client.connection.datasource.DataSource;
import com.buession.redis.client.connection.datasource.jedis.ShardedJedisDataSource;
import com.buession.redis.client.jedis.JedisClientUtils;
import com.buession.redis.core.ShardedRedisNode;
import com.buession.redis.core.convert.JedisConverters;
import com.buession.redis.pipeline.Pipeline;
import com.buession.redis.pipeline.jedis.JedisPipeline;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Jedis 分片模式连接器
 *
 * @author Yong.Teng
 */
public class ShardedJedisConnection extends AbstractJedisRedisConnection<ShardedJedis> implements JedisRedisConnection<ShardedJedis>, RedisShardedConnection {

	/**
	 * Sharded Jedis 连接池
	 */
	private ShardedJedisPool pool;

	/**
	 * ShardedJedis 对象
	 */
	private ShardedJedis shardedJedis;

	private final static Logger logger = LoggerFactory.getLogger(ShardedJedisConnection.class);

	/**
	 * 构造函数
	 */
	public ShardedJedisConnection(){
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		Redis 数据源
	 */
	public ShardedJedisConnection(ShardedJedisDataSource dataSource){
		super(dataSource);
	}

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		Redis 数据源
	 * @param connectTimeout
	 * 		连接超时
	 * @param soTimeout
	 * 		读取超时
	 */
	public ShardedJedisConnection(ShardedJedisDataSource dataSource, int connectTimeout, int soTimeout){
		super(dataSource, connectTimeout, soTimeout);
	}

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		Redis 数据源
	 * @param sslConfiguration
	 * 		SSL 配置
	 */
	public ShardedJedisConnection(ShardedJedisDataSource dataSource, SslConfiguration sslConfiguration){
		super(dataSource, sslConfiguration);
	}

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		Redis 数据源
	 * @param connectTimeout
	 * 		连接超时
	 * @param soTimeout
	 * 		读取超时
	 * @param sslConfiguration
	 * 		SSL 配置
	 */
	public ShardedJedisConnection(ShardedJedisDataSource dataSource, int connectTimeout, int soTimeout,
			SslConfiguration sslConfiguration){
		super(dataSource, connectTimeout, soTimeout, sslConfiguration);
	}

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		Redis 数据源
	 * @param poolConfig
	 * 		连接池配置
	 */
	public ShardedJedisConnection(ShardedJedisDataSource dataSource, JedisPoolConfig poolConfig){
		super(dataSource, poolConfig);
	}

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		Redis 数据源
	 * @param poolConfig
	 * 		连接池配置
	 * @param connectTimeout
	 * 		连接超时
	 * @param soTimeout
	 * 		读取超时
	 */
	public ShardedJedisConnection(ShardedJedisDataSource dataSource, JedisPoolConfig poolConfig, int connectTimeout,
			int soTimeout){
		super(dataSource, poolConfig, connectTimeout, soTimeout);
	}

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		Redis 数据源
	 * @param poolConfig
	 * 		连接池配置
	 * @param sslConfiguration
	 * 		SSL 配置
	 */
	public ShardedJedisConnection(ShardedJedisDataSource dataSource, JedisPoolConfig poolConfig,
			SslConfiguration sslConfiguration){
		super(dataSource, poolConfig, sslConfiguration);
	}

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		Redis 数据源
	 * @param poolConfig
	 * 		连接池配置
	 * @param connectTimeout
	 * 		连接超时
	 * @param soTimeout
	 * 		读取超时
	 * @param sslConfiguration
	 * 		SSL 配置
	 */
	public ShardedJedisConnection(ShardedJedisDataSource dataSource, JedisPoolConfig poolConfig, int connectTimeout,
			int soTimeout, SslConfiguration sslConfiguration){
		super(dataSource, poolConfig, connectTimeout, soTimeout, sslConfiguration);
	}

	@Override
	public ShardedJedisPool getPool(){
		return pool;
	}

	@Override
	public boolean isTransaction(){
		return JedisClientUtils.isInMulti(shardedJedis);
	}

	@Override
	public Pipeline getPipeline(){
		if(pipeline == null){
			pipeline = new JedisPipeline<>(shardedJedis.pipelined());
		}

		return pipeline;
	}

	protected List<JedisShardInfo> createJedisShardInfo(final Set<ShardedRedisNode> nodes){
		final SslConfiguration sslConfiguration = getSslConfiguration();
		final List<JedisShardInfo> shardInfos = new ArrayList<>(nodes.size());
		JedisShardInfo shardInfo;

		for(ShardedRedisNode node : nodes){
			if(sslConfiguration == null){
				shardInfo = new JedisShardInfo(node.getHost(), node.getName(), node.getPort(), 0, node.getWeight(),
						isUseSsl());
			}else{
				shardInfo = new JedisShardInfo(node.getHost(), node.getName(), node.getPort(), 0, node.getWeight(),
						isUseSsl(), sslConfiguration.getSslSocketFactory(), sslConfiguration.getSslParameters(),
						sslConfiguration.getHostnameVerifier());
			}

			shardInfo.setPassword(redisPassword(node.getPassword()));
			shardInfo.setConnectionTimeout(getConnectTimeout());
			shardInfo.setSoTimeout(getSoTimeout());

			try{
				FieldUtils.writeDeclaredField(shardInfo, "db", node.getDatabase(), true);
			}catch(IllegalAccessException e){
			}

			shardInfos.add(shardInfo);
		}

		return shardInfos;
	}

	protected ShardedJedisPool createPool(final ShardedJedisDataSource dataSource){
		final ShardedJedisPool pool = new ShardedJedisPool(getPoolConfig(),
				createJedisShardInfo(dataSource.getNodes()));

		return pool;
	}

	@Override
	protected void doConnect() throws IOException{
		DataSource dataSource = getDataSource();
		if(dataSource == null){
			return;
		}

		ShardedJedisDataSource shardedJedisDataSource = (ShardedJedisDataSource) dataSource;
		if(Validate.isEmpty(shardedJedisDataSource.getNodes())){
			return;
		}

		if(getPoolConfig() != null){
			pool = createPool(shardedJedisDataSource);

			try{
				shardedJedis = pool.getResource();
			}catch(Exception e){
				throw JedisConverters.exceptionConvert(e);
			}

			if(logger.isInfoEnabled()){
				logger.info("ShardedJedis initialize with pool success, size: {}.",
						shardedJedis.getAllShardInfo().size());
			}
		}else{
			List<JedisShardInfo> shardInfos = createJedisShardInfo(shardedJedisDataSource.getNodes());

			try{
				shardedJedis = new ShardedJedis(shardInfos);
			}catch(Exception e){
				throw JedisConverters.exceptionConvert(e);
			}

			logger.info("ShardedJedis initialize success, size: {}.", shardInfos.size());
		}
	}

	@Override
	protected <R> R doExecute(Executor<ShardedJedis, R> executor) throws Exception{
		return executor.execute(shardedJedis);
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
