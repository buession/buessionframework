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

import com.buession.redis.client.connection.AbstractRedisConnection;
import com.buession.redis.client.connection.SslConfiguration;
import com.buession.redis.client.connection.datasource.jedis.JedisRedisDataSource;
import redis.clients.jedis.commands.JedisCommands;

import java.io.IOException;

/**
 * @author Yong.Teng
 */
public abstract class AbstractJedisRedisConnection<T extends JedisCommands> extends AbstractRedisConnection<T> implements JedisRedisConnection<T> {

	public AbstractJedisRedisConnection(){
		super();
	}

	public AbstractJedisRedisConnection(JedisRedisDataSource<T> dataSource){
		super(dataSource);
	}

	public AbstractJedisRedisConnection(JedisRedisDataSource<T> dataSource, SslConfiguration sslConfiguration){
		super(dataSource, sslConfiguration);
	}

	public AbstractJedisRedisConnection(JedisRedisDataSource<T> dataSource, String clientName){
		super(dataSource, clientName);
	}

	public AbstractJedisRedisConnection(JedisRedisDataSource<T> dataSource, String clientName,
			SslConfiguration sslConfiguration){
		super(dataSource, clientName, sslConfiguration);
	}

	public AbstractJedisRedisConnection(JedisRedisDataSource<T> dataSource, boolean useSsl){
		super(dataSource, useSsl);
	}

	public AbstractJedisRedisConnection(JedisRedisDataSource<T> dataSource, boolean useSsl,
			SslConfiguration sslConfiguration){
		super(dataSource, useSsl, sslConfiguration);
	}

	public AbstractJedisRedisConnection(JedisRedisDataSource<T> dataSource, String clientName, boolean useSsl){
		super(dataSource, clientName, useSsl);
	}

	public AbstractJedisRedisConnection(JedisRedisDataSource<T> dataSource, String clientName, boolean useSsl,
			SslConfiguration sslConfiguration){
		super(dataSource, clientName, useSsl, sslConfiguration);
	}

	@Override
	protected void doDisconnect() throws IOException{
	}

	@Override
	protected void doClose() throws IOException{
	}

}
