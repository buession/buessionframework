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

/**
 * Redis 客户端
 *
 * @author Yong.Teng
 */
public interface RedisClient {

	/**
	 * 返回 Redis 连接对象 {@link RedisConnection}
	 *
	 * @return Redis 连接对象 {@link RedisConnection}
	 */
	RedisConnection getConnection();

	/**
	 * 设置 Redis 连接对象 {@link RedisConnection}
	 *
	 * @param connection
	 * 		Redis 连接对象 {@link RedisConnection}
	 */
	void setConnection(RedisConnection connection);

	/**
	 * 返回 BitMap 命令操作实例
	 *
	 * @return BitMap 命令操作实例
	 */
	BitMapOperations bitMapOperations();

	/**
	 * 返回集群命令操作实例
	 *
	 * @return 集群命令操作实例
	 */
	ClusterOperations clusterOperations();

	/**
	 * 返回连接命令操作实例
	 *
	 * @return 连接命令操作实例
	 */
	ConnectionOperations connectionOperations();

	/**
	 * 返回一般命令操作实例
	 *
	 * @return 一般命令操作实例
	 *
	 * @since 3.0.0
	 */
	GenericOperations genericOperations();

	/**
	 * 返回地理位置命令操作实例
	 *
	 * @return 地理位置命令操作实例
	 */
	GeoOperations geoOperations();

	/**
	 * 返回哈希表命令操作实例
	 *
	 * @return 哈希表命令操作实例
	 */
	HashOperations hashOperations();

	/**
	 * 返回 HyperLogLog 命令操作实例
	 *
	 * @return HyperLogLog 命令操作实例
	 */
	HyperLogLogOperations hyperLogLogOperations();

	/**
	 * 返回 KEY 命令操作实例
	 *
	 * @return KEY 命令操作实例
	 */
	KeyOperations keyOperations();

	/**
	 * 返回列表命令操作实例
	 *
	 * @return 列表命令操作实例
	 */
	ListOperations listOperations();

	/**
	 * 返回发布订阅命令操作实例
	 *
	 * @return 发布订阅命令操作实例
	 */
	PubSubOperations pubSubOperations();

	/**
	 * 返回 Script 命令操作实例
	 *
	 * @return Script 命令操作实例
	 */
	ScriptingOperations scriptingOperations();

	/**
	 * 返回服务端操作命令操作实例
	 *
	 * @return 服务端操作命令操作实例
	 */
	ServerOperations serverOperations();

	/**
	 * 返回集合命令操作实例
	 *
	 * @return 集合命令操作实例
	 */
	SetOperations setOperations();

	/**
	 * 返回有序集合命令操作实例
	 *
	 * @return 有序集合命令操作实例
	 */
	SortedSetOperations sortedSetOperations();

	/**
	 * 返回 Stream 命令操作实例
	 *
	 * @return Stream 命令操作实例
	 */
	StreamOperations streamOperations();

	/**
	 * 返回字符串命令操作实例
	 *
	 * @return 字符串命令操作实例
	 */
	StringOperations stringOperations();

	/**
	 * 返回事务命令操作实例
	 *
	 * @return 事务命令操作实例
	 */
	TransactionOperations transactionOperations();

	/**
	 * 执行命令
	 *
	 * @param command
	 * 		命令对象
	 * @param <R>
	 * 		返回值类型
	 *
	 * @return 命令执行结果
	 *
	 * @throws RedisException
	 * 		Redis Exception
	 */
	default <R> R execute(final Command<R> command) throws RedisException {
		return execute(command, null);
	}

	/**
	 * 执行命令
	 *
	 * @param command
	 * 		命令对象
	 * @param arguments
	 * 		命令参数对象
	 * @param <R>
	 * 		返回值类型
	 *
	 * @return 命令执行结果
	 *
	 * @throws RedisException
	 * 		Redis Exception
	 */
	<R> R execute(final Command<R> command, final CommandArguments arguments) throws RedisException;

}
