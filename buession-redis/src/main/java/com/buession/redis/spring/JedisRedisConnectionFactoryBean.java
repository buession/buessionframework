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
package com.buession.redis.spring;

import com.buession.core.utils.FieldUtils;
import com.buession.redis.client.connection.datasource.jedis.JedisDataSource;
import com.buession.redis.client.connection.datasource.jedis.ShardedJedisDataSource;
import com.buession.redis.client.connection.jedis.JedisConnection;
import com.buession.redis.client.connection.jedis.JedisRedisConnection;
import com.buession.redis.client.connection.jedis.ShardedJedisConnection;
import com.buession.redis.exception.PoolException;
import com.buession.redis.spring.jedis.JedisConfiguration;
import com.buession.redis.spring.jedis.ShardedRedisConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Jedis Redis 连接工厂 Bean
 *
 * @author Yong.Teng
 */
public class JedisRedisConnectionFactoryBean extends RedisConnectionFactoryBean<JedisRedisConnection> {

	/**
	 * 连接池配置
	 */
	private JedisPoolConfig poolConfig = new JedisPoolConfig();

	private final static Logger logger = LoggerFactory.getLogger(JedisRedisConnectionFactoryBean.class);

	/**
	 * 构造函数
	 *
	 * @param configuration
	 * 		连接配置
	 */
	public JedisRedisConnectionFactoryBean(final RedisConfiguration configuration){
		super(configuration);
	}

	/**
	 * 构造函数
	 *
	 * @param configuration
	 * 		连接配置
	 * @param poolConfig
	 * 		连接池配置
	 */
	public JedisRedisConnectionFactoryBean(final RedisConfiguration configuration, final JedisPoolConfig poolConfig){
		this(configuration);
		this.poolConfig = poolConfig;
	}

	public JedisPoolConfig getPoolConfig(){
		return poolConfig;
	}

	@Override
	public void afterPropertiesSet() throws Exception{
		if(isShardedConnection()){
			final ShardedJedisDataSource dataSource = createShardedJedisDataSource();
			final ShardedJedisConnection connection = new ShardedJedisConnection(dataSource,
					getConfiguration().getConnectTimeout(), getConfiguration().getSoTimeout(),
					getConfiguration().getSslConfiguration());

			if(getPoolConfig() != null){
				connection.setPoolConfig(getPoolConfig());
				logger.debug("Initialize sharded connection with pool.");
			}else{
				logger.debug("Initialize sharded connection.");
			}

			setConnection(connection);
		}else{
			final JedisDataSource dataSource = createJedisDataSource();
			final JedisConnection connection = new JedisConnection(dataSource, getConfiguration().getConnectTimeout(),
					getConfiguration().getSoTimeout(), getConfiguration().getSslConfiguration());

			if(getPoolConfig() != null){
				connection.setPoolConfig(getPoolConfig());
				logger.debug("Initialize connection with pool.");
			}else{
				logger.debug("Initialize connection.");
			}

			setConnection(connection);
		}
	}

	protected JedisDataSource createJedisDataSource(){
		JedisConfiguration configuration = (JedisConfiguration) getConfiguration();
		return new JedisDataSource(configuration.getHost(), configuration.getPort(), configuration.getPassword(),
				configuration.getDatabase(), configuration.getClientName());
	}

	protected ShardedJedisDataSource createShardedJedisDataSource(){
		ShardedRedisConfiguration configuration = (ShardedRedisConfiguration) getConfiguration();
		return new ShardedJedisDataSource(configuration.getNodes());
	}

	@Override
	protected void afterDestroy(JedisRedisConnection connection){
		if(connection == null || connection.getPool() == null){
			return;
		}

		try{
			connection.getPool().destroy();
			FieldUtils.writeDeclaredField(connection, "pool", null, true);
		}catch(Exception e){
			logger.warn("Cannot properly close ShardedJedis pool", e);
			throw new PoolException(e.getMessage(), e);
		}
	}

}
