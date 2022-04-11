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

import com.buession.core.Executor;
import com.buession.core.utils.Assert;
import com.buession.redis.client.RedisClient;
import com.buession.redis.client.connection.RedisConnection;
import com.buession.redis.client.connection.jedis.JedisClusterConnection;
import com.buession.redis.client.connection.jedis.JedisConnection;
import com.buession.redis.client.connection.jedis.JedisSentinelConnection;
import com.buession.redis.client.jedis.JedisStandaloneClient;
import com.buession.redis.client.jedis.JedisClusterClient;
import com.buession.redis.client.jedis.JedisSentinelClient;
import com.buession.redis.client.jedis.operations.JedisClusterClusterOperations;
import com.buession.redis.client.jedis.operations.JedisClusterOperations;
import com.buession.redis.client.jedis.operations.JedisSentinelClusterOperations;
import com.buession.redis.client.operations.ClusterOperations;
import com.buession.redis.client.operations.ConnectionOperations;
import com.buession.redis.core.Options;
import com.buession.redis.exception.RedisException;
import com.buession.redis.pipeline.Pipeline;
import com.buession.redis.serializer.JacksonJsonSerializer;
import com.buession.redis.serializer.Serializer;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Yong.Teng
 */
public abstract class RedisAccessor implements Closeable {

	protected final static Options DEFAULT_OPTIONS = new Options();

	protected final static Serializer DEFAULT_SERIALIZER = new JacksonJsonSerializer();

	protected Options options = DEFAULT_OPTIONS;

	protected Serializer serializer;

	protected RedisConnection connection;

	protected RedisClient client;

	protected boolean enableTransactionSupport = false;

	protected AtomicInteger index = new AtomicInteger(-1);

	protected ClusterOperations<? extends RedisConnection> clusterOps;

	protected ConnectionOperations<? extends RedisConnection> connectionOps;

	static{
		DEFAULT_OPTIONS.setSerializer(DEFAULT_SERIALIZER);
	}

	public RedisAccessor(){
	}

	public RedisAccessor(RedisConnection connection){
		this.connection = connection;
	}

	public Options getOptions(){
		return options;
	}

	public void setOptions(Options options){
		this.options = options;
	}

	public RedisConnection getConnection(){
		return connection;
	}

	public void setConnection(RedisConnection connection){
		this.connection = connection;
	}

	public RedisClient getClient(){
		return client;
	}

	public void afterPropertiesSet() throws RedisException{
		Assert.isNull(connection, "RedisConnection is required");

		Options options = getOptions();
		if(options != null){
			serializer = options.getSerializer();
			enableTransactionSupport = options.isEnableTransactionSupport();
		}
		if(serializer == null){
			serializer = DEFAULT_SERIALIZER;
		}

		client = doGetRedisClient(connection);
	}

	public Pipeline pipeline(){
		//return execute((cmd)->client.pipeline());
		return null;
	}

	/*
	public <R> R execute(final SessionCallback<R> callback) throws RedisException{
		Assert.isNull(callback, "callback cloud not be null.");

		try{
			return doExecute(callback);
		}catch(Exception e){
			throw new RedisException(e.getMessage(), e);
		}
	}

	 */

	@Override
	public void close() throws IOException{
		if(connection != null){
			connection.close();
		}
	}

	protected <OPS, R> R execute(final OPS ops, final Executor<OPS, R> executor){
		checkInitialized();

		if(isTransactionOrPipeline()){
			index.getAndIncrement();
		}

		return executor.execute(ops);
	}

	protected <R> R clusterOpsExecute(final Executor<ClusterOperations<? extends RedisConnection>, R> executor){
		return execute(clusterOps, executor);
	}

	protected <R> R connectionOpsExecute(final Executor<ConnectionOperations<? extends RedisConnection>, R> executor){
		return execute(connectionOps, executor);
	}

	protected RedisClient doGetRedisClient(RedisConnection connection) throws RedisException{
		if(connection instanceof JedisConnection){
			JedisStandaloneClient jedisClient = new JedisStandaloneClient((JedisConnection) connection);

			jedisClient.setEnableTransactionSupport(enableTransactionSupport);

			clusterOps = new JedisClusterOperations(jedisClient);

			return jedisClient;
		}else if(connection instanceof JedisSentinelConnection){
			JedisSentinelClient jedisSentinelClient = new JedisSentinelClient(
					(JedisSentinelConnection) connection);

			jedisSentinelClient.setEnableTransactionSupport(enableTransactionSupport);

			clusterOps = new JedisSentinelClusterOperations(jedisSentinelClient);

			return jedisSentinelClient;
		}else if(connection instanceof JedisClusterConnection){
			JedisClusterClient jedisClusterClient = new JedisClusterClient(
					(JedisClusterConnection) connection);

			jedisClusterClient.setEnableTransactionSupport(enableTransactionSupport);

			clusterOps = new JedisClusterClusterOperations(jedisClusterClient);

			return jedisClusterClient;
		}else{
			throw new RedisException("Cloud not initialize RedisClient for: " + connection);
		}
	}

	protected final void checkInitialized(){
		if(client == null){
			throw new RedisException(
					"RedisClient is not initialized. You can call the afterPropertiesSet method for initialize.");
		}
	}

	protected boolean isTransaction(){
		return getConnection().isTransaction();
	}

	protected boolean isPipeline(){
		return getConnection().isPipeline();
	}

	protected boolean isTransactionOrPipeline(){
		return isTransaction() || isPipeline();
	}

}
