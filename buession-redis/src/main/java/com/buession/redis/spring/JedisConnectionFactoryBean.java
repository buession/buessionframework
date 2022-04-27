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
 * | Copyright @ 2013-2022 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.spring;

import com.buession.redis.client.connection.RedisConnectionUtils;
import com.buession.redis.client.connection.datasource.jedis.JedisDataSource;
import com.buession.redis.client.connection.datasource.jedis.JedisSentinelDataSource;
import com.buession.redis.client.connection.jedis.JedisClusterConnection;
import com.buession.redis.client.connection.jedis.JedisConnection;
import com.buession.redis.client.connection.jedis.JedisRedisConnection;
import com.buession.redis.client.connection.jedis.JedisSentinelConnection;
import com.buession.redis.spring.jedis.JedisClusterConfiguration;
import com.buession.redis.spring.jedis.JedisConfiguration;
import com.buession.redis.spring.jedis.JedisRedisConfiguration;
import com.buession.redis.spring.jedis.JedisSentinelConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Jedis Redis 连接工厂 Bean
 *
 * @author Yong.Teng
 */
public class JedisConnectionFactoryBean extends RedisConnectionFactoryBean<JedisRedisConnection> {

	private final static Logger logger = LoggerFactory.getLogger(JedisConnectionFactoryBean.class);

	/**
	 * 构造函数
	 *
	 * @param configuration
	 * 		连接配置
	 */
	public JedisConnectionFactoryBean(final JedisRedisConfiguration configuration){
		super(configuration);
	}

	@Override
	public void afterPropertiesSet() throws Exception{
		if(RedisConnectionUtils.isClusterConnection(getConfiguration())){
			setConnection(createJedisClusterConnection((JedisClusterConfiguration) getConfiguration()));
		}else if(RedisConnectionUtils.isSentinelConnection(getConfiguration())){
			setConnection(createJedisSentinelConnection((JedisSentinelConfiguration) getConfiguration()));
		}else{
			setConnection(createJedisConnection((JedisConfiguration) getConfiguration()));
		}
	}

	protected JedisConnection createJedisConnection(final JedisConfiguration configuration){
		final JedisDataSource dataSource = new JedisDataSource();

		dataSource.setHost(configuration.getHost());
		dataSource.setPort(configuration.getPort());
		dataSource.setUsername(configuration.getUsername());
		dataSource.setPassword(configuration.getPassword());
		dataSource.setDatabase(configuration.getDatabase());
		dataSource.setClientName(configuration.getClientName());

		final JedisConnection connection = new JedisConnection(dataSource, configuration.getConnectTimeout(),
				configuration.getSoTimeout(), configuration.getInfiniteSoTimeout(),
				configuration.getSslConfiguration());

		if(configuration.getPoolConfig() != null){
			connection.setPoolConfig(configuration.getPoolConfig());
			logger.debug("Initialize connection with pool.");
		}else{
			logger.debug("Initialize connection.");
		}

		return connection;
	}

	protected JedisSentinelConnection createJedisSentinelConnection(final JedisSentinelConfiguration configuration){
		final JedisSentinelDataSource dataSource = new JedisSentinelDataSource();

		dataSource.setUsername(configuration.getUsername());
		dataSource.setPassword(configuration.getPassword());
		dataSource.setDatabase(configuration.getDatabase());
		dataSource.setClientName(configuration.getClientName());

		dataSource.setSentinelClientName(configuration.getSentinelClientName());
		dataSource.setMasterName(configuration.getMasterName());
		dataSource.setSentinels(configuration.getSentinels());

		final JedisSentinelConnection connection = new JedisSentinelConnection(dataSource,
				configuration.getConnectTimeout(), configuration.getSoTimeout(), configuration.getInfiniteSoTimeout(),
				configuration.getSentinelConnectTimeout(), configuration.getSentinelSoTimeout(),
				configuration.getSslConfiguration());

		if(configuration.getPoolConfig() != null){
			connection.setPoolConfig(configuration.getPoolConfig());
			logger.debug("Initialize sentinel connection with pool.");
		}else{
			logger.debug("Initialize sentinel connection.");
		}

		return connection;
	}

	protected JedisClusterConnection createJedisClusterConnection(final JedisClusterConfiguration configuration){
		/*
		final JedisClusterDataSource dataSource = new JedisClusterDataSource(configuration.getHost(),
				configuration.getPort(), configuration.getPassword(), configuration.getDatabase(),
				configuration.getClientName());
		final JedisClusterConnection connection = new JedisClusterConnection(dataSource,
				getConfiguration().getConnectTimeout(),
				getConfiguration().getSoTimeout(), getConfiguration().getSslConfiguration());

		if(configuration.getPoolConfig() != null){
			connection.setPoolConfig(configuration.getPoolConfig());
			logger.debug("Initialize cluster connection with pool.");
		}else{
			logger.debug("Initialize cluster connection.");
		}

		return connection;

		 */

		return null;
	}

}
