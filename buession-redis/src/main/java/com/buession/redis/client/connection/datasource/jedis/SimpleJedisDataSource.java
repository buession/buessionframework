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

import com.buession.core.validator.Validate;
import com.buession.lang.Status;
import com.buession.redis.client.ClientConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Client;
import redis.clients.jedis.Jedis;

import java.io.IOException;

/**
 * @author Yong.Teng
 */
public class SimpleJedisDataSource extends AbstractJedisRedisDataSource<Jedis> implements JedisDataSource {

	private Jedis jedis;

	private final static Logger logger = LoggerFactory.getLogger(SimpleJedisDataSource.class);

	public SimpleJedisDataSource(){
		super();
	}

	public SimpleJedisDataSource(ClientConfiguration clientConfiguration){
		super(clientConfiguration);
	}

	@Override
	public Status connect(){
		ClientConfiguration configuration = getClientConfiguration();
		jedis = new Jedis(configuration.getHost(), configuration.getPort(), configuration.getConnectTimeout(),
				configuration.getSoTimeout(), configuration.isUseSsl(), configuration.getSslSocketFactory(),
				configuration.getSslParameters(), configuration.getHostnameVerifier());

		Client client = jedis.getClient();

		if(Validate.hasText(configuration.getPassword())){
			client.setPassword(configuration.getPassword());
		}
		client.setDb(configuration.getDatabase());

		jedis.connect();

		if(Validate.hasText(configuration.getClientName())){
			client.clientSetname(configuration.getClientName());
		}

		logger.info("Simple jedis datasource initialize with db {} success, name: {}.", configuration.getDatabase(),
				configuration.getClientName());

		return Status.SUCCESS;
	}

	@Override
	public Jedis getRedisClient(){
		return jedis;
	}

	@Override
	public boolean isClosed(){
		return jedis != null && jedis.isConnected() == false;
	}

	@Override
	public void disconnect() throws IOException{
		if(jedis != null){
			jedis.disconnect();
		}
	}

	@Override
	public void close() throws IOException{
		if(jedis != null){
			jedis.quit();
			disconnect();
		}
	}

}
