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
package com.buession.redis.client.jedis.operations;

import com.buession.redis.client.jedis.JedisClusterClient;
import com.buession.redis.client.jedis.JedisRedisClient;
import com.buession.redis.client.jedis.JedisSentinelClient;
import com.buession.redis.client.jedis.JedisStandaloneClient;
import com.buession.redis.client.operations.AbstractRedisOperations;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.ProtocolCommand;

/**
 * Jedis Redis 命令操作抽象类
 *
 * @param <C>
 * 		Redis Client {@link JedisRedisClient}
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public abstract class AbstractJedisRedisOperations<C extends JedisRedisClient> extends AbstractRedisOperations<C>
		implements JedisRedisOperations {

	public AbstractJedisRedisOperations(final C client) {
		super(client);
	}

	protected <T> T notCommand(final JedisStandaloneClient client, final ProtocolCommand command) {
		if(isPipeline()){
			return new JedisPipelineCommand<T, T>(client, command)
					.run();
		}else if(isTransaction()){
			return new JedisTransactionCommand<T, T>(client, command)
					.run();
		}else{
			return new JedisCommand<T, T>(client, command)
					.run();
		}
	}

	protected <T> T notCommand(final JedisStandaloneClient client, final ProtocolCommand command,
							   final CommandArguments args) {
		if(isPipeline()){
			return new JedisPipelineCommand<T, T>(client, command)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<T, T>(client, command)
					.run(args);
		}else{
			return new JedisCommand<T, T>(client, command)
					.run(args);
		}
	}

	protected <T> T notCommand(final JedisSentinelClient client, final ProtocolCommand command) {
		if(isPipeline()){
			return new JedisSentinelPipelineCommand<T, T>(client, command)
					.run();
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<T, T>(client, command)
					.run();
		}else{
			return new JedisSentinelCommand<T, T>(client, command)
					.run();
		}
	}

	protected <T> T notCommand(final JedisSentinelClient client, final ProtocolCommand command,
							   final CommandArguments args) {
		if(isPipeline()){
			return new JedisSentinelPipelineCommand<T, T>(client, command)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<T, T>(client, command)
					.run(args);
		}else{
			return new JedisSentinelCommand<T, T>(client, command)
					.run(args);
		}
	}

	protected <T> T notCommand(final JedisClusterClient client, final ProtocolCommand command) {
		if(isPipeline()){
			return new JedisClusterPipelineCommand<T, T>(client, command)
					.run();
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<T, T>(client, command)
					.run();
		}else{
			return new JedisClusterCommand<T, T>(client, command)
					.run();
		}
	}

	protected <T> T notCommand(final JedisClusterClient client, final ProtocolCommand command,
							   final CommandArguments args) {
		if(isPipeline()){
			return new JedisClusterPipelineCommand<T, T>(client, command)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<T, T>(client, command)
					.run(args);
		}else{
			return new JedisClusterCommand<T, T>(client, command)
					.run(args);
		}
	}

}
