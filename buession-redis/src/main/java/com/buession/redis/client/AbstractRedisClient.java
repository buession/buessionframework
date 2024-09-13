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
package com.buession.redis.client;

import com.buession.redis.client.connection.RedisConnection;
import com.buession.redis.client.operations.BitMapOperations;
import com.buession.redis.client.operations.ClusterOperations;
import com.buession.redis.client.operations.ConnectionOperations;
import com.buession.redis.client.operations.GenericOperations;
import com.buession.redis.client.operations.GeoOperations;
import com.buession.redis.client.operations.HashOperations;
import com.buession.redis.client.operations.HyperLogLogOperations;
import com.buession.redis.client.operations.KeyOperations;
import com.buession.redis.client.operations.ListOperations;
import com.buession.redis.client.operations.PubSubOperations;
import com.buession.redis.client.operations.ScriptingOperations;
import com.buession.redis.client.operations.ServerOperations;
import com.buession.redis.client.operations.SetOperations;
import com.buession.redis.client.operations.SortedSetOperations;
import com.buession.redis.client.operations.StreamOperations;
import com.buession.redis.client.operations.StringOperations;
import com.buession.redis.client.operations.TransactionOperations;
import com.buession.redis.core.Command;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.exception.RedisException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Redis 客户端抽象类
 *
 * @param <CONN>
 * 		Redis 连接对象类型 {@link RedisConnection}
 *
 * @author Yong.Teng
 */
public abstract class AbstractRedisClient<CONN extends RedisConnection> implements RedisClient {

	/**
	 * Redis 连接对象
	 */
	protected CONN connection;

	/**
	 * BitMap 命令操作
	 */
	protected BitMapOperations bitMapOperations;

	/**
	 * 集群命令操作
	 */
	protected ClusterOperations clusterOperations;

	/**
	 * 连接命令操作
	 */
	protected ConnectionOperations connectionOperations;

	/**
	 * 一般命令
	 *
	 * @since 3.0.0
	 */
	protected GenericOperations genericOperations;

	/**
	 * 地理位置操作命令
	 */
	protected GeoOperations geoOperations;

	/**
	 * 哈希表命令操作
	 */
	protected HashOperations hashOperations;

	/**
	 * HyperLogLog 命令操作
	 */
	protected HyperLogLogOperations hyperLogLogOperations;

	/**
	 * KEY 命令操作
	 */
	protected KeyOperations keyOperations;

	/**
	 * 列表命令操作
	 */
	protected ListOperations listOperations;

	/**
	 * 发布订阅命令操作
	 */
	protected PubSubOperations pubSubOperations;

	/**
	 * Script 命令操作
	 */
	protected ScriptingOperations scriptingOperations;

	/**
	 * 服务端操作命令
	 */
	protected ServerOperations serverOperations;

	/**
	 * 集合命令操作
	 */
	protected SetOperations setOperations;

	/**
	 * 有序集合命令操作
	 */
	protected SortedSetOperations sortedSetOperations;

	/**
	 * Stream 命令操作
	 */
	protected StreamOperations streamOperations;

	/**
	 * 字符串命令操作
	 */
	protected StringOperations stringOperations;

	/**
	 * 事务命令操作
	 */
	protected TransactionOperations transactionOperations;

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 构造函数
	 */
	public AbstractRedisClient() {
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param connection
	 * 		Redis 连接对象 {@link RedisConnection}
	 */
	public AbstractRedisClient(final CONN connection) {
		setConnection(connection);
	}

	@Override
	public CONN getConnection() {
		return connection;
	}

	@SuppressWarnings({"unchecked"})
	@Override
	public void setConnection(RedisConnection connection) {
		this.connection = (CONN) connection;
	}

	@Override
	public <R> R execute(final Command<R> command, final CommandArguments arguments) {
		long startTime = 0;
		if(logger.isDebugEnabled()){
			startTime = System.nanoTime();
		}

		if(logger.isDebugEnabled()){
			if(arguments != null){
				logger.debug("Execute command: {} {}", command.getCommand(), arguments);
			}else{
				logger.debug("Execute command: {}", command.getCommand());
			}
		}

		try{
			return doExecute(command);
		}catch(RedisException e){
			if(logger.isErrorEnabled()){
				if(arguments != null){
					logger.error("Execute command: {} {}, failure: {}", command.getCommand(), arguments,
							e.getMessage(), e);
				}else{
					logger.error("Execute command: {}, failure: {}", command.getCommand(), e.getMessage(), e);
				}
			}
			throw e;
		}finally{
			if(logger.isDebugEnabled()){
				long finishTime = System.nanoTime();
				logger.debug("Command execution time: {}", finishTime - startTime);
			}
		}
	}

	private <R> R doExecute(final Command<R> command) {
		return getConnection().execute((conn)->command.execute());
	}

}
