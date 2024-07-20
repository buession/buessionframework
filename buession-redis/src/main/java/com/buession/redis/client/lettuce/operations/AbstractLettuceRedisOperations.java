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
package com.buession.redis.client.lettuce.operations;

import com.buession.redis.client.lettuce.LettuceClusterClient;
import com.buession.redis.client.lettuce.LettuceRedisClient;
import com.buession.redis.client.lettuce.LettuceSentinelClient;
import com.buession.redis.client.lettuce.LettuceStandaloneClient;
import com.buession.redis.client.operations.AbstractRedisOperations;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.ProtocolCommand;

/**
 * Lettuce Redis 命令操作抽象类
 *
 * @param <C>
 * 		Redis Client {@link LettuceRedisClient}
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public abstract class AbstractLettuceRedisOperations<C extends LettuceRedisClient> extends AbstractRedisOperations<C>
		implements LettuceRedisOperations {

	public AbstractLettuceRedisOperations(final C client) {
		super(client);
	}

	protected <T> T notCommand(final LettuceStandaloneClient client, final ProtocolCommand command) {
		if(isPipeline()){
			return new LettucePipelineCommand<T, T>(client, command)
					.run();
		}else if(isTransaction()){
			return new LettuceTransactionCommand<T, T>(client, command)
					.run();
		}else{
			return new LettuceCommand<T, T>(client, command)
					.run();
		}
	}

	protected <T> T notCommand(final LettuceStandaloneClient client, final ProtocolCommand command,
							   final CommandArguments args) {
		if(isPipeline()){
			return new LettucePipelineCommand<T, T>(client, command)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<T, T>(client, command)
					.run(args);
		}else{
			return new LettuceCommand<T, T>(client, command)
					.run(args);
		}
	}

	protected <T> T notCommand(final LettuceSentinelClient client, final ProtocolCommand command) {
		if(isPipeline()){
			return new LettuceSentinelPipelineCommand<T, T>(client, command)
					.run();
		}else if(isTransaction()){
			return new LettuceSentinelTransactionCommand<T, T>(client, command)
					.run();
		}else{
			return new LettuceSentinelCommand<T, T>(client, command)
					.run();
		}
	}

	protected <T> T notCommand(final LettuceSentinelClient client, final ProtocolCommand command,
							   final CommandArguments args) {
		if(isPipeline()){
			return new LettuceSentinelPipelineCommand<T, T>(client, command)
					.run(args);
		}else if(isTransaction()){
			return new LettuceSentinelTransactionCommand<T, T>(client, command)
					.run(args);
		}else{
			return new LettuceSentinelCommand<T, T>(client, command)
					.run(args);
		}
	}

	protected <T> T notCommand(final LettuceClusterClient client, final ProtocolCommand command) {
		if(isPipeline()){
			return new LettuceClusterPipelineCommand<T, T>(client, command)
					.run();
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<T, T>(client, command)
					.run();
		}else{
			return new LettuceClusterCommand<T, T>(client, command)
					.run();
		}
	}

	protected <T> T notCommand(final LettuceClusterClient client, final ProtocolCommand command,
							   final CommandArguments args) {
		if(isPipeline()){
			return new LettuceClusterPipelineCommand<T, T>(client, command)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<T, T>(client, command)
					.run(args);
		}else{
			return new LettuceClusterCommand<T, T>(client, command)
					.run(args);
		}
	}

}
