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
import com.buession.redis.pipeline.Pipeline;

/**
 * @author Yong.Teng
 */
public interface RedisClient {

	RedisConnection getConnection();

	void setConnection(RedisConnection connection);

	Pipeline pipeline();

	BitMapOperations<? extends RedisConnection> bitMapOperations();

	ClusterOperations<? extends RedisConnection> clusterOperations();

	ConnectionOperations<? extends RedisConnection> connectionOperations();

	GeoOperations<? extends RedisConnection> geoOperations();

	HashOperations<? extends RedisConnection> hashOperations();

	HyperLogLogOperations<? extends RedisConnection> hyperLogLogOperations();

	KeyOperations<? extends RedisConnection> keyOperations();

	ListOperations<? extends RedisConnection> listOperations();

	PubSubOperations<? extends RedisConnection> pubSubOperations();

	ScriptingOperations<? extends RedisConnection> scriptingOperations();

	ServerOperations<? extends RedisConnection> serverOperations();

	SetOperations<? extends RedisConnection> setOperations();

	SortedSetOperations<? extends RedisConnection> sortedSetOperations();

	StreamOperations<? extends RedisConnection> streamOperations();

	StringOperations<? extends RedisConnection> stringOperations();

	TransactionOperations<? extends RedisConnection> transactionOperations();

	default <R> R execute(final Command<RedisConnection, R> command) throws RedisException{
		return execute(command, null);
	}

	<R> R execute(final Command<RedisConnection, R> command, final CommandArguments arguments)
			throws RedisException;

}
