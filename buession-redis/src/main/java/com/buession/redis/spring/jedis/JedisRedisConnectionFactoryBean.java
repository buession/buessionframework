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
package com.buession.redis.spring.jedis;

import com.buession.redis.client.connection.jedis.JedisRedisConnection;
import com.buession.redis.spring.RedisConnectionFactoryBean;
import org.springframework.beans.BeanUtils;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.ShardedJedisPoolConfig;

/**
 * Jedis Redis 连接工厂 Bean
 *
 * @author Yong.Teng
 */
public class JedisRedisConnectionFactoryBean extends RedisConnectionFactoryBean<JedisRedisConnection> {

	/**
	 * 连接池配置
	 */
	@Deprecated
	private JedisPoolConfig poolConfig = new JedisPoolConfig();

	/**
	 * 构造函数
	 *
	 * @param configuration
	 * 		连接配置
	 */
	public JedisRedisConnectionFactoryBean(final JedisRedisConfiguration configuration){
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
	@Deprecated
	public JedisRedisConnectionFactoryBean(final JedisRedisConfiguration configuration,
										   final JedisPoolConfig poolConfig){
		this(configuration);
		this.poolConfig = poolConfig;

		if(configuration instanceof ShardedRedisConfiguration){
			ShardedJedisPoolConfig shardedJedisPoolConfig = new ShardedJedisPoolConfig();

			BeanUtils.copyProperties(shardedJedisPoolConfig, poolConfig);
			((ShardedRedisConfiguration) configuration).setPoolConfig(shardedJedisPoolConfig);
		}else{
			((JedisConfiguration) configuration).setPoolConfig(poolConfig);
		}
	}

	@Deprecated
	public JedisPoolConfig getPoolConfig(){
		return poolConfig;
	}

	@Override
	public void afterPropertiesSet() throws Exception{
		if(isShardedConnection()){
			final ShardedJedisRedisConnector shardedJedisRedisConnector =
					new ShardedJedisRedisConnector((ShardedRedisConfiguration) getConfiguration());
			setConnection(shardedJedisRedisConnector.create());
		}else{
			final StandaloneJedisRedisConnector standaloneJedisRedisConnector =
					new StandaloneJedisRedisConnector((JedisConfiguration) getConfiguration());
			setConnection(standaloneJedisRedisConnector.create());
		}
	}

}
