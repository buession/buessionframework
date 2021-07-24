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
 * | Copyright @ 2013-2021 Buession.com Inc.														       |
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
import com.buession.redis.exception.RedisException;
import com.buession.redis.exception.RedisExceptionUtils;
import com.buession.redis.pipeline.Pipeline;
import com.buession.redis.pipeline.jedis.JedisPipeline;
import com.buession.redis.pipeline.jedis.ShardedJedisPipeline;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.jedis.ShardedJedisPoolConfig;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Jedis 分片模式连接器
 *
 * @author Yong.Teng
 */
public class ShardedJedisConnection extends AbstractJedisRedisConnection implements RedisShardedConnection {

	/**
	 * 连接池配置
	 *
	 * @since 1.3.0
	 */
	private ShardedJedisPoolConfig poolConfig;

	/**
	 * 连接池
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
	public ShardedJedisConnection(ShardedJedisDataSource dataSource, ShardedJedisPoolConfig poolConfig){
		super(dataSource);
		this.poolConfig = poolConfig;
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
	public ShardedJedisConnection(ShardedJedisDataSource dataSource, ShardedJedisPoolConfig poolConfig,
								  int connectTimeout, int soTimeout){
		super(dataSource, connectTimeout, soTimeout);
		this.poolConfig = poolConfig;
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
	public ShardedJedisConnection(ShardedJedisDataSource dataSource, ShardedJedisPoolConfig poolConfig,
								  SslConfiguration sslConfiguration){
		super(dataSource, sslConfiguration);
		this.poolConfig = poolConfig;
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
	public ShardedJedisConnection(ShardedJedisDataSource dataSource, ShardedJedisPoolConfig poolConfig,
								  int connectTimeout, int soTimeout, SslConfiguration sslConfiguration){
		super(dataSource, connectTimeout, soTimeout, sslConfiguration);
		this.poolConfig = poolConfig;
	}

	/**
	 * 返回连接池配置 {@link ShardedJedisPoolConfig}
	 *
	 * @return 连接池配置
	 */
	public ShardedJedisPoolConfig getPoolConfig(){
		return poolConfig;
	}

	/**
	 * 设置连接池配置 {@link ShardedJedisPoolConfig}
	 *
	 * @param poolConfig
	 * 		连接池配置
	 */
	public void setPoolConfig(ShardedJedisPoolConfig poolConfig){
		this.poolConfig = poolConfig;
	}

	@Override
	public <C, R> R execute(Executor<C, R> executor) throws RedisException{
		try{
			return executor.execute((C) shardedJedis);
		}catch(Exception e){
			logger.error("Redis execute command failure: ", e);
			throw RedisExceptionUtils.convert(e);
		}
	}

	@Override
	public boolean isTransaction(){
		return JedisClientUtils.isInMulti(shardedJedis);
	}

	@Override
	public Pipeline getPipeline(){
		if(pipeline == null){
			pipeline = new JedisPipeline(new ShardedJedisPipeline(shardedJedis.pipelined()));
		}

		return pipeline;
	}

	@Override
	public boolean isConnect(){
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
	public boolean isClosed(){
		return isConnect() == false;
	}

	@Override
	protected void internalInit(){
		DataSource dataSource = getDataSource();
		if(dataSource == null){
			return;
		}

		ShardedJedisDataSource shardedJedisDataSource = (ShardedJedisDataSource) dataSource;
		if(Validate.isEmpty(shardedJedisDataSource.getNodes())){
			return;
		}

		if(isUsePool()){
			pool = createPool(shardedJedisDataSource);
		}else{
			shardedJedis = createShardedJedis(shardedJedisDataSource);
		}
	}

	protected List<JedisShardInfo> createJedisShardInfo(final Set<ShardedRedisNode> nodes){
		final SslConfiguration sslConfiguration = getSslConfiguration();
		final List<JedisShardInfo> shardInfos = new ArrayList<>(nodes.size());
		JedisShardInfo shardInfo;

		for(ShardedRedisNode node : nodes){
			if(sslConfiguration == null){
				logger.debug("Create JedisShardInfo, node: {}:{}.", node.getHost(), node.getPort());
				shardInfo = new JedisShardInfo(node.getHost(), node.getName(), node.getPort(), 0, node.getWeight(),
						isUseSsl());
			}else{
				logger.debug("Create JedisShardInfo with ssl, node: {}:{}.", node.getHost(), node.getPort());
				shardInfo = new JedisShardInfo(node.getHost(), node.getName(), node.getPort(), 0, node.getWeight(),
						isUseSsl(), sslConfiguration.getSslSocketFactory(), sslConfiguration.getSslParameters(),
						sslConfiguration.getHostnameVerifier());
			}

			shardInfo.setPassword(redisPassword(node.getPassword()));
			shardInfo.setConnectionTimeout(getConnectTimeout());
			shardInfo.setSoTimeout(getSoTimeout());

			if(node.getDatabase() != DEFAULT_DB){
				try{
					FieldUtils.writeDeclaredField(shardInfo, "db", node.getDatabase(), true);
				}catch(IllegalAccessException e){
					logger.error("Select db '{}' failure, node: {}:{}.", node.getDatabase(), node.getHost(),
							node.getPort());
				}
			}

			shardInfos.add(shardInfo);
		}

		return shardInfos;
	}

	protected ShardedJedisPool createPool(final ShardedJedisDataSource dataSource){
		return new ShardedJedisPool(getPoolConfig(), createJedisShardInfo(dataSource.getNodes()));
	}

	protected ShardedJedis createShardedJedis(final ShardedJedisDataSource dataSource){
		return new ShardedJedis(createJedisShardInfo(dataSource.getNodes()));
	}

	protected boolean isUsePool(){
		return getPoolConfig() != null;
	}

	@Override
	protected void doConnect() throws IOException{
		boolean usePool = isUsePool();

		try{
			if(usePool){
				shardedJedis = pool.getResource();
				if(logger.isInfoEnabled()){
					logger.info("ShardedJedis initialize with pool success, size: {}.",
							shardedJedis.getAllShardInfo().size());
				}
			}else{
				if(logger.isInfoEnabled()){
					logger.info("ShardedJedis initialize success, size: {}.", shardedJedis.getAllShardInfo().size());
				}
			}
		}catch(Exception e){
			if(logger.isErrorEnabled()){
				if(usePool){
					logger.error("Create sharded jedis from pool failure: {}", e.getMessage(), e);
				}else{
					logger.error("Create ShardedJedis instance failure: {}", e.getMessage(), e);
				}
			}

			throw RedisExceptionUtils.convert(e);
		}
	}

	@Override
	protected void doDestroy() throws IOException{
		super.doDestroy();

		logger.info("Sharded Jedis destroy.");
		if(pool != null){
			logger.info("Jedis Pool for {} destroy.", pool.getClass().getName());

			try{
				pool.destroy();
			}catch(Exception ex){
				logger.warn("Cannot properly close Jedis pool.", ex);
			}

			pool = null;
		}
	}

	@Override
	protected void doDisconnect() throws IOException{
		super.doDisconnect();

		logger.info("Sharded Jedis disconnect.");

		if(shardedJedis != null){
			shardedJedis.disconnect();
		}
	}

	@Override
	protected void doClose() throws IOException{
		super.doClose();

		logger.info("Sharded Jedis close.");
		if(isUsePool()){
			logger.info("Sharded Jedis close.");
			if(shardedJedis != null){
				shardedJedis.close();
			}
		}else{
			logger.info("Sharded Jedis disconnect.");
			if(shardedJedis != null){
				shardedJedis.disconnect();
			}
		}
	}

}
