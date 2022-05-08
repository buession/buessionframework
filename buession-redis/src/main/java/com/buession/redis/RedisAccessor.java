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
package com.buession.redis;

import com.buession.core.utils.Assert;
import com.buession.redis.client.RedisClient;
import com.buession.redis.client.connection.RedisConnection;
import com.buession.redis.client.connection.RedisConnectionFactory;
import com.buession.redis.client.connection.RedisConnectionUtils;
import com.buession.redis.client.connection.datasource.DataSource;
import com.buession.redis.client.connection.datasource.jedis.JedisClusterDataSource;
import com.buession.redis.client.connection.datasource.jedis.JedisDataSource;
import com.buession.redis.client.connection.datasource.jedis.JedisSentinelDataSource;
import com.buession.redis.client.jedis.JedisStandaloneClient;
import com.buession.redis.client.jedis.JedisClusterClient;
import com.buession.redis.client.jedis.JedisSentinelClient;
import com.buession.redis.core.Command;
import com.buession.redis.core.Options;
import com.buession.redis.core.SessionCallback;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.exception.RedisException;
import com.buession.redis.pipeline.Pipeline;
import com.buession.redis.serializer.JacksonJsonSerializer;
import com.buession.redis.serializer.Serializer;
import org.springframework.lang.Nullable;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Yong.Teng
 */
public abstract class RedisAccessor {

	protected final static Options DEFAULT_OPTIONS = new Options();

	protected final static Serializer DEFAULT_SERIALIZER = new JacksonJsonSerializer();

	protected Options options = DEFAULT_OPTIONS;

	protected Serializer serializer;

	protected DataSource dataSource;

	protected RedisConnectionFactory connectionFactory;

	protected RedisClient client;

	protected boolean enableTransactionSupport = false;

	protected AtomicInteger index = new AtomicInteger(-1);

	static{
		DEFAULT_OPTIONS.setSerializer(DEFAULT_SERIALIZER);
	}

	/**
	 * 构造函数
	 */
	public RedisAccessor(){
	}

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		数据源
	 */
	public RedisAccessor(DataSource dataSource){
		setDataSource(dataSource);
	}

	public Options getOptions(){
		return options;
	}

	public void setOptions(Options options){
		this.options = options;
	}

	@Nullable
	public RedisConnectionFactory getConnectionFactory(){
		return connectionFactory;
	}

	@Nullable
	public DataSource getDataSource(){
		return dataSource;
	}

	public void setDataSource(DataSource dataSource){
		this.dataSource = dataSource;
	}

	public RedisClient getClient(){
		return client;
	}

	public void afterPropertiesSet() throws RedisException{
		Assert.isNull(getDataSource(), "DataSource is required");

		Options options = getOptions();
		if(options != null){
			serializer = options.getSerializer();
			enableTransactionSupport = options.isEnableTransactionSupport();
		}
		if(serializer == null){
			serializer = DEFAULT_SERIALIZER;
		}

		connectionFactory = new RedisConnectionFactory(getDataSource());
	}

	public void pipeline(){
		RedisConnection connection;
		if(enableTransactionSupport){
			connection = RedisConnectionUtils.bindConnection(connectionFactory, true);
		}else{
			connection = RedisConnectionUtils.getConnection(connectionFactory);
		}

		RedisClient client = doGetRedisClient();
		
		client.setConnection(connection);
		client.execute(new Command<Pipeline>() {

			@Override
			public ProtocolCommand getCommand(){
				return null;
			}

			@Override
			public Pipeline execute() throws RedisException{
				return client.getConnection().openPipeline();
			}

			@Override
			public Pipeline run(final CommandArguments arguments) throws RedisException{
				return client.getConnection().openPipeline();
			}

		});
	}

	public <R> R execute(final SessionCallback<R> callback) throws RedisException{
		Assert.isNull(callback, "callback cloud not be null.");
		checkInitialized();

		RedisConnection connection;
		if(enableTransactionSupport){
			connection = RedisConnectionUtils.bindConnection(connectionFactory, true);
		}else{
			connection = RedisConnectionUtils.getConnection(connectionFactory);
		}

		RedisClient client = doGetRedisClient();
		client.setConnection(connection);

		if(isTransactionOrPipeline(connection)){
			index.getAndIncrement();
		}

		try{
			return callback.execute(client);
		}catch(Exception e){
			throw new RedisException(e.getMessage(), e);
		}finally{
			RedisConnectionUtils.releaseConnection(connectionFactory, connection, enableTransactionSupport);
		}
	}

	protected RedisClient doGetRedisClient() throws RedisException{
		DataSource dataSource = getDataSource();
		if(dataSource instanceof JedisDataSource){
			return new JedisStandaloneClient();
		}else if(dataSource instanceof JedisSentinelDataSource){
			return new JedisSentinelClient();
		}else if(dataSource instanceof JedisClusterDataSource){
			return new JedisClusterClient();
		}else{
			throw new RedisException("Cloud not initialize RedisClient for: " + dataSource);
		}
	}

	protected final void checkInitialized(){
		if(connectionFactory == null){
			throw new RedisException(
					"RedisConnectionFactory is not initialized. You can call the afterPropertiesSet method for initialize.");
		}
	}

	protected boolean isTransaction(final RedisConnection connection){
		return connection.isTransaction();
	}

	protected boolean isPipeline(final RedisConnection connection){
		return connection.isPipeline();
	}

	protected boolean isTransactionOrPipeline(final RedisConnection connection){
		return isTransaction(connection) || isPipeline(connection);
	}

}
