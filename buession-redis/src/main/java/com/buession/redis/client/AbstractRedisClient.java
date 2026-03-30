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
 * | Copyright @ 2013-2026 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.client;

import com.buession.redis.client.connection.RedisConnection;
import com.buession.redis.core.command.Command;
import com.buession.redis.core.command.*;
import com.buession.redis.exception.RedisException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Redis 客户端抽象类
 *
 * @author Yong.Teng
 */
public abstract class AbstractRedisClient implements RedisClient {

	/**
	 * 客户端选项
	 */
	protected ClientOptions clientOptions = new ClientOptions();

	/**
	 * Redis 连接对象
	 */
	protected RedisConnection connection;

	/**
	 * 自动提示命令
	 */
	protected AutoSuggestCommands autoSuggestCommands;

	/**
	 * BitMap 命令
	 */
	protected BitMapCommands bitMapCommands;

	/**
	 * 布隆过滤器命令
	 */
	protected BloomFilterCommands bloomFilterCommands;

	/**
	 * 布谷鸟过滤器命令
	 */
	protected CuckooFilterCommands cuckooFilterCommands;

	/**
	 * 集群命令
	 */
	protected ClusterCommands clusterCommands;

	/**
	 * 计数最小草图命令
	 */
	protected CountMinSketchCommands countMinSketchCommands;

	/**
	 * 连接命令
	 */
	protected ConnectionCommands connectionCommands;

	/**
	 * 一般命令
	 */
	protected GenericCommands genericCommands;

	/**
	 * 地理位置操作命令
	 */
	protected GeoCommands geoCommands;

	/**
	 * 哈希表命令
	 */
	protected HashCommands hashCommands;

	/**
	 * HyperLogLog 命令
	 */
	protected HyperLogLogCommands hyperLogLogCommands;

	/**
	 * JSON 命令
	 */
	protected JsonCommands jsonCommands;

	/**
	 * KEY 命令
	 */
	protected KeyCommands keyCommands;

	/**
	 * 列表命令
	 */
	protected ListCommands listCommands;

	/**
	 * 发布订阅命令
	 */
	protected PubSubCommands pubSubCommands;

	/**
	 * Script 命令
	 */
	protected ScriptingCommands scriptingCommands;

	/**
	 * 搜索命令
	 */
	protected SearchCommands searchCommands;

	/**
	 * 服务端操作命令
	 */
	protected ServerCommands serverCommands;

	/**
	 * 集合命令
	 */
	protected SetCommands setCommands;

	/**
	 * 有序集合命令
	 */
	protected SortedSetCommands sortedSetCommands;

	/**
	 * Stream 命令
	 */
	protected StreamCommands streamCommands;

	/**
	 * 字符串命令
	 */
	protected StringCommands stringCommands;

	/**
	 * T-Digest 命令
	 */
	protected TDigestCommands tDigestCommands;

	/**
	 * TimeSeries 命令
	 */
	protected TimeSeriesCommands timeSeriesCommands;

	/**
	 * TOP K 命令
	 */
	protected TopKCommands topKCommands;

	/**
	 * 事务命令
	 */
	protected TransactionCommands transactionCommands;

	/**
	 * Vector Set 命令
	 */
	protected VectorSetCommands vectorSetCommands;

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
	 * @param clientOptions
	 * 		客户端选项
	 * @param connection
	 * 		Redis 连接对象 {@link RedisConnection}
	 *
	 * @since 4.0.0
	 */
	public AbstractRedisClient(final ClientOptions clientOptions, final RedisConnection connection) {
		setClientOptions(clientOptions);
		setConnection(connection);
	}

	/**
	 * 构造函数
	 *
	 * @param connection
	 * 		Redis 连接对象 {@link RedisConnection}
	 */
	public AbstractRedisClient(final RedisConnection connection) {
		setConnection(connection);
	}

	@Override
	public ClientOptions getClientOptions() {
		return clientOptions;
	}

	@Override
	public void setClientOptions(ClientOptions clientOptions) {
		this.clientOptions = clientOptions;
	}

	@Override
	public RedisConnection getConnection() {
		return connection;
	}

	@Override
	public void setConnection(RedisConnection connection) {
		this.connection = connection;
	}

	@Override
	public <R> R execute(final Command<RedisConnection, R> command, final CommandArguments arguments) {
		long startTime = 0;
		if(logger.isDebugEnabled()){
			startTime = System.nanoTime();
		}

		if(logger.isDebugEnabled()){
			logger.debug("Execute command: {}", runCommand(command.getCommand(), arguments));
		}

		try{
			return getConnection().execute((conn)->command.execute(conn, arguments));
		}catch(RedisException e){
			if(logger.isErrorEnabled()){
				logger.error("Execute command: {}, failure: {}", runCommand(command.getCommand(), arguments),
						e.getMessage(), e);
			}
			throw e;
		}finally{
			if(logger.isDebugEnabled()){
				long finishTime = System.nanoTime();
				logger.debug("Command execution time: {}ns", finishTime - startTime);
			}
		}
	}

	protected static String runCommand(final RedisCommand command,
	                                   final CommandArguments arguments) {
		final StringBuilder sb = new StringBuilder(command.name());

		if(arguments != null){
			sb.append(" ").append(arguments);
		}

		return sb.toString();
	}

}
