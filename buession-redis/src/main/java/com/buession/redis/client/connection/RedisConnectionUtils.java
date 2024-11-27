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
import com.buession.redis.transaction.TransactionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.RawTargetAccess;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.lang.Nullable;

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

	/**
	 * Return whether the given Redis connection is transactional, that is, bound to the current thread by Spring's
	 * transaction facilities.
	 *
	 * @param factory
	 * 		Redis connection factory that the connection was created with
	 * @param connection
	 * 		Redis connection to check
	 *
	 * @return whether the connection is transactional or not
	 */
	public static boolean isConnectionTransactional(final RedisConnectionFactory factory,
													final RedisConnection connection) {
		Assert.isNull(factory, "No RedisConnectionFactory specified");

		RedisConnectionHolder connHolder = TransactionUtils.getResource(factory);
		return connHolder != null && connectionEquals(connHolder, connection);
	}

	/**
	 * Obtain a {@link RedisConnection} from the given {@link RedisConnectionFactory} and binds the connection to the
	 * current thread to be used in closure-scope, if none is already bound. Considers ongoing transactions by reusing the
	 * transaction-bound connection and allows reentrant connection retrieval. Does not bind the connection to potentially
	 * ongoing transactions.
	 *
	 * @param factory
	 * 		Redis connection factory
	 *
	 * @return A new Redis connection without transaction support.
	 */
	public static RedisConnection bindConnection(final RedisConnectionFactory factory) {
		return doGetConnection(factory, true, true, false);
	}

	/**
	 * Obtain a {@link RedisConnection} from the given {@link RedisConnectionFactory} and binds the connection to the
	 * current thread to be used in closure-scope, if none is already bound. Considers ongoing transactions by reusing the
	 * transaction-bound connection and allows reentrant connection retrieval. Binds also the connection to the ongoing
	 * transaction if no connection is already bound if {@code transactionSupport} is enabled.
	 *
	 * @param factory
	 * 		Redis connection factory.
	 * @param enableTransactionSupport
	 * 		whether transaction support is enabled.
	 *
	 * @return A new Redis connection with transaction support if requested.
	 */
	public static RedisConnection bindConnection(final RedisConnectionFactory factory,
												 final boolean enableTransactionSupport) {
		return doGetConnection(factory, true, true, enableTransactionSupport);
	}

	/**
	 * Obtain a {@link RedisConnection} from the given {@link RedisConnectionFactory}. Is aware of existing connections
	 * bound to the current transaction (when using a transaction manager) or the current thread (when binding a
	 * connection to a closure-scope). Does not bind newly created connections to ongoing transactions.
	 *
	 * @param factory
	 * 		Redis connection factory for creating the connection.
	 *
	 * @return An active Redis connection without transaction management.
	 */
	public static RedisConnection getConnection(final RedisConnectionFactory factory) {
		return getConnection(factory, false);
	}

	/**
	 * Obtain a {@link RedisConnection} from the given {@link RedisConnectionFactory}. Is aware of existing connections
	 * bound to the current transaction (when using a transaction manager) or the current thread (when binding a
	 * connection to a closure-scope).
	 *
	 * @param factory
	 * 		Redis connection factory for creating the connection.
	 * @param enableTransactionSupport
	 * 		whether transaction support is enabled.
	 *
	 * @return An active Redis connection with transaction management if requested.
	 */
	public static RedisConnection getConnection(final RedisConnectionFactory factory,
												final boolean enableTransactionSupport) {
		return doGetConnection(factory, true, false, enableTransactionSupport);
	}

	/**
	 * Actually obtain a {@link RedisConnection} from the given {@link RedisConnectionFactory}. Is aware of existing
	 * connections bound to the current transaction (when using a transaction manager) or the current thread (when binding
	 * a connection to a closure-scope). Will create a new {@link RedisConnection} otherwise, if {@code allowCreate} is
	 * {@literal true}. This method allows for re-entrance as {@link RedisConnectionHolder} keeps track of ref-count.
	 *
	 * @param factory
	 * 		Redis connection factory for creating the connection.
	 * @param allowCreate
	 * 		whether a new (unbound) connection should be created when no connection can be found for the current thread.
	 * @param bind
	 * 		binds the connection to the thread, in case one was created-
	 * @param enableTransactionSupport
	 * 		whether transaction support is enabled.
	 *
	 * @return An active Redis connection.
	 */
	private static RedisConnection doGetConnection(final RedisConnectionFactory factory, final boolean allowCreate,
												   final boolean bind, final boolean enableTransactionSupport) {
		Assert.isNull(factory, "No RedisConnectionFactory specified");
		RedisConnectionHolder connectionHolder = TransactionUtils.getResource(factory);

		if(connectionHolder != null &&
				(connectionHolder.hasConnection() || connectionHolder.isSynchronizedWithTransaction())){
			connectionHolder.requested();
			if(connectionHolder.hasConnection() == false){
				if(logger.isWarnEnabled()){
					logger.debug("Fetching resumed Redis Connection from RedisConnectionFactory");
				}
				connectionHolder.setConnection(fetchConnection(factory));
			}
			return connectionHolder.getRequiredConnection();
		}

		// Else we either got no holder or an empty thread-bound holder here.
		Assert.isFalse(allowCreate, "No connection found and allowCreate = false");

		if(logger.isWarnEnabled()){
			logger.debug("Fetching Redis Connection from RedisConnectionFactory");
		}
		RedisConnection connection = fetchConnection(factory);

		boolean bindSynchronization = TransactionUtils.isActualTransactionActive() && enableTransactionSupport;
		if(bind || bindSynchronization){
			if(bindSynchronization && TransactionUtils.isActualNonReadonlyTransactionActive()){
				connection = createConnectionSplittingProxy(factory, connection);
			}

			try{
				// Use same RedisConnection for further Redis actions within the transaction.
				// Thread-bound object will get removed by synchronization at transaction completion.
				RedisConnectionHolder holderToUse = connectionHolder;
				if(holderToUse == null){
					holderToUse = new RedisConnectionHolder(connection);
				}else{
					holderToUse.setConnection(connection);
				}
				holderToUse.requested();

				// Consider callback-scope connection binding vs. transaction scope binding
				if(bindSynchronization){
					potentiallyRegisterTransactionSynchronisation(factory, holderToUse);
				}

				if(holderToUse != connectionHolder){
					TransactionUtils.bindResource(factory, holderToUse);
				}
			}catch(RuntimeException e){
				// Unexpected exception from external delegation call -> close Connection and rethrow.
				releaseConnection(factory, connection);
				throw e;
			}

			return connection;
		}

		return connection;
	}

	/**
	 * Closes the given {@link RedisConnection}, created via the given factory if not managed externally (i.e. not bound
	 * to the transaction).
	 *
	 * @param factory
	 * 		The Redis factory that the connection was created with.
	 * @param connection
	 * 		The Redis connection to close.
	 */
	public static void releaseConnection(final RedisConnectionFactory factory,
										 @Nullable final RedisConnection connection) {
		if(connection == null){
			logger.warn("Redis connection is null.");
			return;
		}

		RedisConnectionHolder connectionHolder = TransactionUtils.getResource(factory);
		if(connectionHolder != null){
			if(connectionHolder.isTransactionActive()){
				if(connectionEquals(connectionHolder, connection)){
					if(logger.isDebugEnabled()){
						logger.debug("RedisConnection will be closed when transaction finished.");
					}

					// It's the transactional Connection: Don't close it.
					connectionHolder.released();
				}
				return;
			}

			// release transactional/read-only and non-transactional/non-bound connections.
			// transactional connections for read-only transactions get no synchronizer registered

			unbindConnection(factory);
			return;
		}

		doCloseConnection(connection);
	}

	/**
	 * Unbinds and closes the connection (if any) associated with the given factory from closure-scope. Considers ongoing
	 * transactions so transaction-bound connections aren't closed and reentrant closure-scope bound connections. Only the
	 * outer-most call to leads to releasing and closing the connection.
	 *
	 * @param factory
	 * 		Redis factory
	 */
	public static void unbindConnection(final RedisConnectionFactory factory) {
		RedisConnectionHolder connectionHolder = TransactionUtils.getResource(factory);
		if(connectionHolder == null){
			return;
		}

		if(logger.isDebugEnabled()){
			logger.debug("Unbinding Redis Connection.");
		}

		if(connectionHolder.isTransactionActive()){
			if(logger.isDebugEnabled()){
				logger.debug("Redis Connection will be closed when outer transaction finished.");
			}
		}else{
			RedisConnection connection = connectionHolder.getConnection();
			connectionHolder.released();

			if(connectionHolder.isOpen() == false){
				TransactionUtils.unbindResourceIfPossible(factory);
				doCloseConnection(connection);
			}
		}
	}

	/**
	 * Closes the given {@link RedisConnection}, created via the given factory if not managed externally (i.e. not bound
	 * to the transaction).
	 *
	 * @param factory
	 * 		The Redis factory that the connection was created with.
	 * @param connection
	 * 		The Redis connection to close.
	 * @param transactionSupport
	 * 		whether transaction support is enabled.
	 */
	public static void releaseConnection(final RedisConnectionFactory factory,
										 @Nullable final RedisConnection connection, final boolean transactionSupport) {
		releaseConnection(factory, connection);
	}

	/**
	 * Actually create a {@link RedisConnection} from the given {@link RedisConnectionFactory}.
	 *
	 * @param factory
	 * 		the {@link RedisConnectionFactory} to obtain RedisConnections from.
	 *
	 * @return a Redis Connection from the given {@link RedisConnectionFactory} (never {@literal null}).
	 *
	 * @see RedisConnectionFactory#getConnection()
	 */
	private static RedisConnection fetchConnection(final RedisConnectionFactory factory) {
		return factory.getConnection();
	}

	/**
	 * Return the innermost target {@link RedisConnection} of the given {@link RedisConnection}. If the given
	 * {@link RedisConnection} is a proxy, it will be unwrapped until a non-proxy {@link RedisConnection} is found.
	 * Otherwise, the passed-in {@link RedisConnection} will be returned as-is.
	 *
	 * @param connection
	 * 		The {@link RedisConnection} proxy to unwrap
	 *
	 * @return the innermost target Connection, or the passed-in one if no proxy
	 *
	 * @see RedisConnectionProxy#getTargetConnection()
	 */
	private static RedisConnection getTargetConnection(final RedisConnection connection) {
		RedisConnection connectionToUse = connection;

		while(connectionToUse instanceof RedisConnectionProxy){
			connectionToUse = ((RedisConnectionProxy) connectionToUse).getTargetConnection();
		}

		return connectionToUse;
	}

	private static void potentiallyRegisterTransactionSynchronisation(
			final RedisConnectionFactory factory, final RedisConnectionHolder connectionHolder) {
		// Should go actually into RedisTransactionManager
		if(connectionHolder.isTransactionActive() == false){
			connectionHolder.setTransactionActive(true);
			connectionHolder.setSynchronizedWithTransaction(true);
			connectionHolder.requested();

			RedisConnection connection = connectionHolder.getRequiredConnection();
			boolean readOnly = TransactionUtils.isCurrentTransactionReadOnly();
			if(readOnly == false){
				connection.multi();
			}

			if(logger.isDebugEnabled()){
				logger.debug("Register Transaction Synchronization.");
			}

			TransactionUtils.registerSynchronization(factory, connectionHolder, connection, false);
		}
	}

	private static RedisConnection createConnectionSplittingProxy(final RedisConnectionFactory factory, final
	RedisConnection connection) {
		ProxyFactory proxyFactory = new ProxyFactory(connection);
		proxyFactory.addAdvice(new ConnectionSplittingInterceptor(factory));
		proxyFactory.addInterface(RedisConnectionProxy.class);

		return RedisConnection.class.cast(proxyFactory.getProxy());
	}

	/**
	 * Determine whether the given two RedisConnections are equal, asking the target {@link RedisConnection} in case of a
	 * proxy. Used to detect equality even if the user passed in a raw target Connection while the held one is a proxy.
	 *
	 * @param connectionHolder
	 * 		The {@link RedisConnectionHolder} for the held Connection (potentially a proxy)
	 * @param connection
	 * 		The {@link RedisConnection} passed-in by the user (potentially a target Connection without proxy)
	 *
	 * @return whether the given Connections are equal
	 *
	 * @see #getTargetConnection
	 */
	private static boolean connectionEquals(final RedisConnectionHolder connectionHolder,
											final RedisConnection connection) {
		if(connectionHolder.hasConnection()){
			final RedisConnection heldCConnection = connectionHolder.getRequiredConnection();
			return heldCConnection.equals(connection) || getTargetConnection(heldCConnection).equals(connection);
		}else{
			return false;
		}
	}

	private static void doCloseConnection(@Nullable final RedisConnection connection) {
		if(connection == null){
			return;
		}

		if(logger.isDebugEnabled()){
			logger.debug("Closing Redis Connection.");
		}

		try{
			connection.close();
		}catch(DataAccessException e){
			logger.error("Could not close Redis Connection", e);
		}catch(Throwable t){
			logger.error("Unexpected exception on closing Redis Connection", t);
		}
	}

	/**
	 * Subinterface of {@link RedisConnection} to be implemented by {@link RedisConnection} proxies. Allows access to the
	 * underlying target {@link RedisConnection}.
	 *
	 * @see RedisConnectionUtils#getTargetConnection(RedisConnection)
	 * @since 3.0.1
	 */
	public interface RedisConnectionProxy extends RedisConnection, RawTargetAccess {

		/**
		 * Return the target {@link RedisConnection} of this proxy. This will typically be the native driver {@link RedisConnection} or a wrapper from a connection pool.
		 *
		 * @return The underlying {@link RedisConnection}.
		 */
		RedisConnection getTargetConnection();

	}

}