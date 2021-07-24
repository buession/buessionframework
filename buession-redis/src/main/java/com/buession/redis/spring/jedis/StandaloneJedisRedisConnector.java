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

import com.buession.redis.client.connection.datasource.jedis.JedisDataSource;
import com.buession.redis.client.connection.jedis.JedisConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Jedis Redis 连接器抽象类
 *
 * @author Yong.Teng
 * @since 1.3.0
 */
final class StandaloneJedisRedisConnector extends AbstractJedisRedisConnector<JedisConfiguration, JedisConnection> {

	private final static Logger logger = LoggerFactory.getLogger(StandaloneJedisRedisConnector.class);

	/**
	 * 构造函数
	 *
	 * @param configuration
	 * 		Sharded Jedis Redis 工厂配置
	 */
	public StandaloneJedisRedisConnector(final JedisConfiguration configuration){
		super(configuration);
	}

	@Override
	public JedisConnection create(){
		final JedisDataSource dataSource = new JedisDataSource(getConfiguration().getHost(),
				getConfiguration().getPort(), getConfiguration().getPassword(), getConfiguration().getDatabase(),
				getConfiguration().getClientName());
		final JedisConnection connection = new JedisConnection(dataSource, getConfiguration().getConnectTimeout(),
				getConfiguration().getSoTimeout(), getConfiguration().getSslConfiguration());

		if(getConfiguration().getPoolConfig() != null){
			connection.setPoolConfig(getConfiguration().getPoolConfig());
			logger.debug("Initialize connection with pool.");
		}else{
			logger.debug("Initialize connection.");
		}

		return connection;
	}

}
