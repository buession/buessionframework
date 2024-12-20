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
package com.buession.redis.client.connection;

import com.buession.core.utils.Assert;
import com.buession.redis.core.RedisMode;
import com.buession.redis.exception.RedisConnectionFailureException;
import com.buession.redis.transaction.TransactionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.lang.Nullable;

import java.io.IOException;

/**
 * @author Yong.Teng
 */
public final class RedisConnectionUtils {

	private final static Logger logger = LoggerFactory.getLogger(RedisConnectionUtils.class);

	private RedisConnectionUtils() {
	}

	/**
	 * 根据连接器获取 Redis 模式
	 *
	 * @param connection
	 * 		连接器
	 *
	 * @return Redis 模式
	 */
	public static RedisMode getRedisMode(final RedisConnection connection) {
		if(connection instanceof RedisSentinelConnection){
			return RedisMode.SENTINEL;
		}else if(connection instanceof RedisClusterConnection){
			return RedisMode.CLUSTER;
		}else{
			return RedisMode.STANDALONE;
		}
	}

	public static RedisConnection bindConnection(final RedisConnectionFactory factory) {
		return bindConnection(factory, false);
	}

	public static RedisConnection bindConnection(final RedisConnectionFactory factory,
												 final boolean enableTransactionSupport) {
		return doGetConnection(factory, true, true, enableTransactionSupport);
	}

	public static RedisConnection getConnection(final RedisConnectionFactory factory) {
		return getConnection(factory, false);
	}

	public static RedisConnection getConnection(final RedisConnectionFactory factory,
												final boolean enableTransactionSupport) {
		return doGetConnection(factory, true, false, enableTransactionSupport);
	}

	public static boolean isConnectionTransactional(final RedisConnectionFactory factory,
													final RedisConnection connection) {
		Assert.isNull(factory, "No RedisConnectionFactory specified");
		RedisConnectionHolder connHolder = TransactionUtils.getResource(factory);
		return (connHolder != null && connection == connHolder.getConnection());
	}

	public static void releaseConnection(final RedisConnectionFactory factory,
										 final @Nullable RedisConnection connection, final boolean transactionSupport) {
		if(connection == null){
			logger.error("Redis connection is null.");
			return;
		}

		RedisConnectionHolder connectionHolder = TransactionUtils.getResource(factory);

		if(connectionHolder != null && connectionHolder.isTransactionSyncronisationActive()){
			logger.debug("Redis Connection will be closed when transaction finished.");
			return;
		}

		if(isConnectionTransactional(factory, connection)){
			if(transactionSupport && TransactionUtils.isCurrentTransactionReadOnly()){
				logger.debug("Unbinding Redis Connection.");
				unbindConnection(factory);
			}else{
				logger.debug("Leaving bound Redis Connection attached.");
			}
		}else{
			logger.debug("Closing Redis Connection");
			connectionClose(connection);
		}
	}

	public static void unbindConnection(final RedisConnectionFactory factory) {
		RedisConnectionHolder connectionHolder = TransactionUtils.unbindResourceIfPossible(factory);
		if(connectionHolder == null){
			return;
		}

		if(connectionHolder.isTransactionSyncronisationActive()){
			logger.debug("Redis Connection will be closed when outer transaction finished.");
		}else{
			logger.debug("Closing bound connection.");
			connectionClose(connectionHolder.getConnection());
		}
	}

	private static RedisConnection doGetConnection(final RedisConnectionFactory factory, final boolean allowCreate,
												   final boolean bind, final boolean enableTransactionSupport) {
		Assert.isNull(factory, "No RedisConnectionFactory specified");

		RedisConnectionHolder connectionHolder = TransactionUtils.getResource(factory);

		if(connectionHolder != null){
			if(enableTransactionSupport){
				potentiallyRegisterTransactionSynchronisation(factory, connectionHolder);
			}

			logger.debug("Reopening Redis RedisConnection from transaction resource.");

			return connectionHolder.getConnection();
		}

		Assert.isFalse(allowCreate, "No redisConnection found and allowCreate = false");

		logger.debug("Opening Redis RedisConnection.");

		RedisConnection connection = factory.getConnection();

		long startTime = 0;
		if(logger.isDebugEnabled()){
			startTime = System.nanoTime();
		}

		if(connection.isConnect() == false){
			try{
				connection.connect();
			}catch(RedisConnectionFailureException e){
				logger.error("Redis connection failure: {}", e.getMessage());
				throw e;
			}finally{
				if(logger.isDebugEnabled()){
					long finishTime = System.nanoTime();
					logger.debug("Connection redis execution time: {}", finishTime - startTime);
				}
			}
		}

		if(bind){
			RedisConnection redisConnectionToBind = connection;

			if(enableTransactionSupport && TransactionUtils.isActualNonReadonlyTransactionActive()){
				redisConnectionToBind = createConnectionProxy(factory, connection);
			}

			connectionHolder = new RedisConnectionHolder(redisConnectionToBind);

			TransactionUtils.bindResource(factory, connectionHolder);
			if(enableTransactionSupport){
				potentiallyRegisterTransactionSynchronisation(factory, connectionHolder);
			}

			logger.debug("Bind RedisConnectionHolder.");
			return connectionHolder.getConnection();
		}

		return connection;
	}

	private static void potentiallyRegisterTransactionSynchronisation(final RedisConnectionFactory factory,
																	  final RedisConnectionHolder connectionHolder) {
		if(TransactionUtils.isActualNonReadonlyTransactionActive() &&
				connectionHolder.isTransactionSyncronisationActive() == false){
			connectionHolder.setTransactionSyncronisationActive(true);

			RedisConnection connection = connectionHolder.getConnection();
			connection.multi();

			logger.debug("Register Transaction Synchronization.");
			TransactionUtils.registerSynchronization(factory, connectionHolder, connection);
		}
	}

	private static RedisConnection createConnectionProxy(final RedisConnectionFactory factory,
														 final RedisConnection connection) {
		ProxyFactory proxyFactory = new ProxyFactory(connection);
		proxyFactory.addAdvice(new ConnectionSplittingInterceptor(factory));

		logger.debug("Create Redis Connection Proxy.");
		return (RedisConnection) proxyFactory.getProxy();
	}

	private static void connectionClose(final RedisConnection connection) {
		if(connection == null){
			return;
		}

		try{
			connection.close();
		}catch(IOException e){
			logger.error("Closing Redis Connection error.", e);
		}
	}

}