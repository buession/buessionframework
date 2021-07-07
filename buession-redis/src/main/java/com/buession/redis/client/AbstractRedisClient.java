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
package com.buession.redis.client;

import com.buession.core.Executor;
import com.buession.redis.client.connection.RedisConnection;
import com.buession.redis.client.connection.RedisConnectionFactory;
import com.buession.redis.client.connection.RedisConnectionUtils;
import com.buession.redis.client.operations.*;
import com.buession.redis.exception.RedisException;
import com.buession.redis.exception.RedisExceptionUtils;
import com.buession.redis.pipeline.Pipeline;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Yong.Teng
 */
public abstract class AbstractRedisClient<T> implements RedisClient {

	private RedisConnectionFactory connectionFactory;

	private RedisConnection connection;

	private boolean enableTransactionSupport = false;

	protected ConnectionOperations<T> connectionOperations = createConnectionOperations();

	protected GeoOperations<T> geoOperations = createGeoOperations();

	protected HashOperations<T> hashOperations = createHashOperations();

	protected HyperLogLogOperations<T> hyperLogLogOperations = createHyperLogLogOperations();

	protected KeyOperations<T> keyOperations = createKeyOperations();

	protected ListOperations<T> listOperations = createListOperations();

	protected PubSubOperations<T> pubSubOperations = createPubSubOperations();

	protected ScriptingOperations<T> scriptingOperations = createScriptingOperations();

	protected ServerOperations<T> serverOperations = createServerOperations();

	protected SetOperations<T> setOperations = createSetOperations();

	protected SortedSetOperations<T> sortedSetOperations = createSortedSetOperations();

	protected StringOperations<T> stringOperations = createStringOperations();

	protected TransactionOperations<T> transactionOperations = createTransactionOperations();

	private final static Logger logger = LoggerFactory.getLogger(AbstractRedisClient.class);

	public AbstractRedisClient(){
		super();
	}

	public AbstractRedisClient(RedisConnection connection){
		setConnection(connection);
	}

	@Override
	public RedisConnection getConnection(){
		return connection;
	}

	@Override
	public void setConnection(RedisConnection connection){
		this.connection = connection;
		connectionFactory = new RedisConnectionFactory(connection);
	}

	public boolean isEnableTransactionSupport(){
		return getEnableTransactionSupport();
	}

	public boolean getEnableTransactionSupport(){
		return enableTransactionSupport;
	}

	public void setEnableTransactionSupport(boolean enableTransactionSupport){
		this.enableTransactionSupport = enableTransactionSupport;
	}

	@Override
	public Pipeline pipeline(){
		return execute((cmd)->connection.getPipeline());
	}

	@Override
	public <C, R> R execute(final Executor<C, R> executor){
		RedisConnection connection;

		long start = 0;
		if(logger.isDebugEnabled()){
			start = System.nanoTime();
		}

		if(enableTransactionSupport){
			// only bind resources in case of potential transaction synchronization
			connection = RedisConnectionUtils.bindConnection(connectionFactory, true);
		}else{
			connection = RedisConnectionUtils.getConnection(connectionFactory);
		}

		try{
			return connection.execute(executor);
		}catch(RedisException e){
			logger.error("Redis execute command failure: {}", e.getMessage(), e);
			throw RedisExceptionUtils.convert(e);
		}finally{
			if(logger.isDebugEnabled()){
				long finish = System.nanoTime();
				logger.debug("Command execution time: {}", finish - start);
			}

			RedisConnectionUtils.releaseConnection(connectionFactory, connection, enableTransactionSupport);
		}
	}

	protected abstract ConnectionOperations<T> createConnectionOperations();

	protected abstract GeoOperations<T> createGeoOperations();

	protected abstract HashOperations<T> createHashOperations();

	protected abstract HyperLogLogOperations<T> createHyperLogLogOperations();

	protected abstract KeyOperations<T> createKeyOperations();

	protected abstract ListOperations<T> createListOperations();

	protected abstract PubSubOperations<T> createPubSubOperations();

	protected abstract ScriptingOperations<T> createScriptingOperations();

	protected abstract ServerOperations<T> createServerOperations();

	protected abstract SetOperations<T> createSetOperations();

	protected abstract SortedSetOperations<T> createSortedSetOperations();

	protected abstract StringOperations<T> createStringOperations();

	protected abstract TransactionOperations<T> createTransactionOperations();

	protected boolean isTransaction(){
		return getConnection().isTransaction();
	}

	protected boolean isPipeline(){
		return getConnection().isPipeline();
	}

}
