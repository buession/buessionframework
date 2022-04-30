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
import com.buession.redis.client.connection.RedisConnectionFactory;
import com.buession.redis.client.connection.RedisConnectionUtils;
import com.buession.redis.client.connection.jedis.JedisClusterConnection;
import com.buession.redis.client.connection.jedis.JedisConnection;
import com.buession.redis.client.connection.jedis.JedisSentinelConnection;
import com.buession.redis.client.jedis.JedisStandaloneClient;
import com.buession.redis.client.jedis.JedisClusterClient;
import com.buession.redis.client.jedis.JedisSentinelClient;
import com.buession.redis.client.operations.BitMapOperations;
import com.buession.redis.client.operations.ClusterOperations;
import com.buession.redis.client.operations.ConnectionOperations;
import com.buession.redis.client.operations.GeoOperations;
import com.buession.redis.client.operations.HashOperations;
import com.buession.redis.client.operations.HyperLogLogOperations;
import com.buession.redis.client.operations.KeyOperations;
import com.buession.redis.client.operations.ListOperations;
import com.buession.redis.client.operations.PubSubOperations;
import com.buession.redis.client.operations.RedisOperations;
import com.buession.redis.client.operations.ScriptingOperations;
import com.buession.redis.client.operations.ServerOperations;
import com.buession.redis.client.operations.SetOperations;
import com.buession.redis.client.operations.SortedSetOperations;
import com.buession.redis.client.operations.StreamOperations;
import com.buession.redis.client.operations.StringOperations;
import com.buession.redis.client.operations.TransactionOperations;
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

	private RedisConnectionFactory connectionFactory;

	protected RedisConnection connection;

	protected RedisClient client;

	protected boolean enableTransactionSupport = false;

	protected AtomicInteger index = new AtomicInteger(-1);

	protected BitMapOperations bitMapOps;

	protected ClusterOperations clusterOps;

	protected ConnectionOperations connectionOps;

	protected GeoOperations geoOps;

	protected HashOperations hashOps;

	protected HyperLogLogOperations hyperLogLogOps;

	protected KeyOperations keyOps;

	protected ListOperations listOps;

	protected PubSubOperations pubSubOps;

	protected ScriptingOperations scriptingOps;

	protected ServerOperations serverOps;

	protected SetOperations setOps;

	protected SortedSetOperations sortedSetOps;

	protected StreamOperations streamOps;

	protected StringOperations stringOps;

	protected TransactionOperations transactionOps;

	static{
		DEFAULT_OPTIONS.setSerializer(DEFAULT_SERIALIZER);
	}

	public RedisAccessor(){
	}

	public RedisAccessor(RedisConnection connection){
		setConnection(connection);
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

	public RedisConnectionFactory getRequiredConnectionFactory(){
		RedisConnectionFactory connectionFactory = getConnectionFactory();

		if(connectionFactory == null){
			throw new IllegalStateException("RedisConnectionFactory is required");
		}

		return connectionFactory;
	}

	@Nullable
	public final RedisConnection getConnection(){
		return connection;
	}

	public final void setConnection(RedisConnection connection){
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

		connectionFactory = new RedisConnectionFactory(connection);
		client = doGetRedisClient();

		bitMapOps = client.bitMapOperations();
		clusterOps = client.clusterOperations();
		connectionOps = client.connectionOperations();
		geoOps = client.geoOperations();
		hashOps = client.hashOperations();
		hyperLogLogOps = client.hyperLogLogOperations();
		keyOps = client.keyOperations();
		listOps = client.listOperations();
		pubSubOps = client.pubSubOperations();
		scriptingOps = client.scriptingOperations();
		serverOps = client.serverOperations();
		setOps = client.setOperations();
		sortedSetOps = client.sortedSetOperations();
		streamOps = client.streamOperations();
		stringOps = client.stringOperations();
		transactionOps = client.transactionOperations();
	}

	public void pipeline(){
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

		if(enableTransactionSupport){
			connection = RedisConnectionUtils.bindConnection(connectionFactory, true);
		}else{
			connection = RedisConnectionUtils.getConnection(connectionFactory);
		}

		client.setConnection(connection);

		if(isTransactionOrPipeline()){
			index.getAndIncrement();
		}

		try{
			return callback.execute(getClient());
		}catch(Exception e){
			throw new RedisException(e.getMessage(), e);
		}finally{
			RedisConnectionUtils.releaseConnection(connectionFactory, connection, enableTransactionSupport);
		}
	}

	@Override
	public void close() throws IOException{
		if(connection != null){
			connection.close();
		}
	}

	protected <OPS extends RedisOperations, R> R execute(final OPS ops, final Executor<OPS, R> executor)
			throws RedisException{
		checkInitialized();

		if(enableTransactionSupport){
			// only bind resources in case of potential transaction synchronization
			connection = RedisConnectionUtils.bindConnection(connectionFactory, true);
		}else{
			connection = RedisConnectionUtils.getConnection(connectionFactory);
		}

		client.setConnection(connection);

		if(isTransactionOrPipeline()){
			index.getAndIncrement();
		}

		try{
			return executor.execute(ops);
		}catch(Exception e){
			throw new RedisException(e.getMessage(), e);
		}finally{
			RedisConnectionUtils.releaseConnection(connectionFactory, connection, enableTransactionSupport);
		}
	}

	protected <R> R bitMapOpsExecute(final Executor<BitMapOperations, R> executor){
		return execute(bitMapOps, executor);
	}

	protected <R> R clusterOpsExecute(final Executor<ClusterOperations, R> executor){
		return execute(clusterOps, executor);
	}

	protected <R> R connectionOpsExecute(final Executor<ConnectionOperations, R> executor){
		return execute(connectionOps, executor);
	}

	protected <R> R geoOpsExecute(final Executor<GeoOperations, R> executor){
		return execute(geoOps, executor);
	}

	protected <R> R hashOpsExecute(final Executor<HashOperations, R> executor){
		return execute(hashOps, executor);
	}

	protected <R> R hyperLogLogOpsExecute(final Executor<HyperLogLogOperations, R> executor){
		return execute(hyperLogLogOps, executor);
	}

	protected <R> R keyOpsExecute(final Executor<KeyOperations, R> executor){
		return execute(keyOps, executor);
	}

	protected <R> R listOpsExecute(final Executor<ListOperations, R> executor){
		return execute(listOps, executor);
	}

	protected <R> R pubSubOpsExecute(final Executor<PubSubOperations, R> executor){
		return execute(pubSubOps, executor);
	}

	protected <R> R scriptingOpsExecute(final Executor<ScriptingOperations, R> executor){
		return execute(scriptingOps, executor);
	}

	protected <R> R serverOpsExecute(final Executor<ServerOperations, R> executor){
		return execute(serverOps, executor);
	}

	protected <R> R setOpsExecute(final Executor<SetOperations, R> executor){
		return execute(setOps, executor);
	}

	protected <R> R sortedSetOpsExecute(final Executor<SortedSetOperations, R> executor){
		return execute(sortedSetOps, executor);
	}

	protected <R> R streamOpsOpsExecute(final Executor<StreamOperations, R> executor){
		return execute(streamOps, executor);
	}

	protected <R> R stringOpsOpsExecute(final Executor<StringOperations, R> executor){
		return execute(stringOps, executor);
	}

	protected <R> R transactionOpsExecute(final Executor<TransactionOperations, R> executor){
		return execute(transactionOps, executor);
	}

	protected RedisClient doGetRedisClient() throws RedisException{
		RedisConnectionFactory factory = getRequiredConnectionFactory();

		connection = factory.getConnection();

		if(connection instanceof JedisConnection){
			return new JedisStandaloneClient();
		}else if(connection instanceof JedisSentinelConnection){
			return new JedisSentinelClient();
		}else if(connection instanceof JedisClusterConnection){
			return new JedisClusterClient();
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
		return connection.isTransaction();
	}

	protected boolean isPipeline(){
		return connection.isPipeline();
	}

	protected boolean isTransactionOrPipeline(){
		return isTransaction() || isPipeline();
	}

}
