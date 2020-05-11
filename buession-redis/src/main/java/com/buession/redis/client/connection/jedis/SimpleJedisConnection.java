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
package com.buession.redis.client.connection.jedis;

import com.buession.core.validator.Validate;
import com.buession.redis.client.connection.datasource.RedisDataSource;
import com.buession.redis.client.connection.datasource.jedis.SimpleJedisDataSource;
import com.buession.redis.client.jedis.JedisTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Client;
import redis.clients.jedis.Jedis;

import java.io.IOException;

/**
 * @author Yong.Teng
 */
public class SimpleJedisConnection extends AbstractJedisRedisConnection<Jedis> implements JedisConnection {

	private Jedis jedis;

	private final static Logger logger = LoggerFactory.getLogger(SimpleJedisConnection.class);

	public SimpleJedisConnection(){
		super();
	}

	public SimpleJedisConnection(SimpleJedisDataSource dataSource){
		super(dataSource);
	}

	@Override
	public void multi(){
		Jedis delegate = getDelegate();
		transaction = new JedisTransaction(delegate.multi());
	}

	@Override
	public void exec(){
		if(transaction != null){
			transaction.exec();
		}
	}

	@Override
	public void discard(){
		if(transaction != null){
			transaction.discard();
		}
	}

	@Override
	protected Jedis getDelegate(RedisDataSource dataSource){
		return jedis;
	}

	@Override
	protected void doConnect() throws IOException{
		if(getDataSource() == null || (getDataSource() instanceof SimpleJedisDataSource) == false){
			return;
		}

		SimpleJedisDataSource dataSource = (SimpleJedisDataSource) getDataSource();
		jedis = new Jedis(dataSource.getHost(), dataSource.getPort(), dataSource.getConnectTimeout(),
				dataSource.getSoTimeout(), dataSource.isUseSsl(), dataSource.getSslSocketFactory(),
				dataSource.getSslParameters(), dataSource.getHostnameVerifier());

		Client client = jedis.getClient();

		if(Validate.hasText(dataSource.getPassword())){
			client.setPassword(dataSource.getPassword());
		}
		client.setDb(dataSource.getDatabase());

		jedis.connect();

		if(Validate.hasText(dataSource.getClientName())){
			client.clientSetname(dataSource.getClientName());
		}

		logger.info("Jedis initialize with db {} success, name: {}.", dataSource.getDatabase(),
				dataSource.getClientName());
	}

	@Override
	protected boolean checkConnect(){
		return getDelegate() != null && getDelegate().isConnected();
	}

	@Override
	protected boolean checkClosed(){
		return getDelegate() == null || getDelegate().isConnected() == false;
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
			getDelegate().quit();
			disconnect();
		}
	}

}
