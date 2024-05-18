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
 * | Copyright @ 2013-2024 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.client.connection.datasource.lettuce;

import com.buession.lang.Constants;
import com.buession.net.ssl.SslConfiguration;
import com.buession.redis.client.connection.RedisConnection;
import com.buession.redis.client.connection.datasource.StandaloneDataSource;
import com.buession.redis.client.connection.lettuce.LettuceConnection;
import com.buession.redis.core.RedisNode;
import io.lettuce.core.LettucePool;
import io.lettuce.core.LettucePoolConfig;
import io.lettuce.core.support.ConnectionPoolUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Lettuce 单机模式数据源
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public class LettuceDataSource extends AbstractLettuceDataSource implements StandaloneDataSource {

	/**
	 * Redis 主机地址
	 */
	private String host = RedisNode.DEFAULT_HOST;

	/**
	 * Redis 端口
	 */
	private int port = RedisNode.DEFAULT_PORT;

	/**
	 * 数据库
	 */
	private int database = RedisNode.DEFAULT_DATABASE;

	private LettucePool pool;

	private final static Logger logger = LoggerFactory.getLogger(LettuceDataSource.class);

	@Override
	public String getHost() {
		return host;
	}

	@Override
	public void setHost(String host) {
		this.host = host;
	}

	@Override
	public int getPort() {
		return port;
	}

	@Override
	public void setPort(int port) {
		this.port = port;
	}

	@Override
	public int getDatabase() {
		return database;
	}

	@Override
	public void setDatabase(int database) {
		this.database = database;
	}

	@Override
	public LettuceConnection getConnection() {
		if(isUsePool()){
			if(pool == null){
				pool = createPool();
			}
		}

		return new LettuceConnection(this, pool, getConnectTimeout(), getSoTimeout(), getInfiniteSoTimeout(),
				getSslConfiguration());
	}

	private LettucePool createPool() {
		final SslConfiguration sslConfiguration = getSslConfiguration();
		final LettucePoolConfig<byte[], byte[]> lettucePoolConfig = new LettucePoolConfig<>();

		getPoolConfig().toGenericObjectPoolConfig(lettucePoolConfig);

		final String password = Constants.EMPTY_STRING.equals(getPassword()) ? null : getPassword();
		if(sslConfiguration == null){
			logger.debug("Create lettuce pool.");
			return ConnectionPoolUtils.createLettucePool(lettucePoolConfig, getHost(), getPort(), getConnectTimeout(),
					getSoTimeout(), getUsername(), password, getDatabase(), getClientName(), isUseSsl());
		}else{
			logger.debug("Create lettuce pool with ssl.");
			return ConnectionPoolUtils.createLettucePool(lettucePoolConfig, getHost(), getPort(), getConnectTimeout(),
					getSoTimeout(), getUsername(), password, getDatabase(), getClientName(), isUseSsl(),
					sslConfiguration.getSslSocketFactory(), sslConfiguration.getSslParameters(),
					sslConfiguration.getHostnameVerifier());
		}
	}

}
