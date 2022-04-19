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
package com.buession.redis.client.jedis.operations;

import com.buession.core.validator.Validate;
import com.buession.lang.Status;
import com.buession.redis.client.connection.RedisConnection;
import com.buession.redis.client.connection.jedis.JedisSentinelConnection;
import com.buession.redis.client.jedis.JedisSentinelClient;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.internal.convert.TransactionResultConverter;
import com.buession.redis.core.internal.convert.response.OkStatusConverter;
import redis.clients.jedis.Builder;
import redis.clients.jedis.Response;

import java.util.List;

/**
 * Jedis 哨兵模式事务命令操作
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public final class JedisSentinelTransactionOperations extends AbstractTransactionOperations<JedisSentinelConnection> {

	public JedisSentinelTransactionOperations(final JedisSentinelClient client){
		super(client);
	}

	@Override
	public Status multi(){
		final JedisSentinelCommand<Status> command = JedisSentinelCommand.<Status>create(ProtocolCommand.MULTI)
				.general((cmd)->{
					RedisConnection connection = client.getConnection();
					connection.multi();

					return Status.SUCCESS;
				}).transaction((cmd)->{
					RedisConnection connection = client.getConnection();
					connection.multi();

					return new Response<>(new Builder<Status>() {

						@Override
						public Status build(Object data){
							return Status.SUCCESS;
						}

					});
				});
		return execute(command);
	}

	@Override
	public List<Object> exec(){
		final JedisSentinelCommand<List<Object>> command = JedisSentinelCommand.<List<Object>>create(
						ProtocolCommand.EXEC)
				.transaction((cmd)->{
					RedisConnection connection = client.getConnection();
					List<Object> results = connection.exec();

					return new Response<>(new Builder<List<Object>>() {

						@Override
						public List<Object> build(Object data){
							return Validate.isEmpty(results) ? results : new TransactionResultConverter<>(
									client.getTxResults()).convert(
									results);
						}

					});
				});
		return execute(command);
	}

	@Override
	public void discard(){
		final JedisSentinelCommand<Void> command = JedisSentinelCommand.<Void>create(ProtocolCommand.DISCARD)
				.transaction((cmd)->{
					RedisConnection connection = client.getConnection();
					connection.discard();
					return null;
				});
		execute(command);
	}

	@Override
	public Status watch(final String... keys){
		final CommandArguments args = CommandArguments.create("keys", keys);
		final JedisSentinelCommand<Status> command = JedisSentinelCommand.<Status>create(ProtocolCommand.WATCH)
				.general((cmd)->cmd.watch(keys), OkStatusConverter.INSTANCE)
				.transaction((cmd)->new Response<>(new Builder<String>() {

					@Override
					public String build(Object data){
						return cmd.watch(keys);
					}

				}), OkStatusConverter.INSTANCE);
		return execute(command, args);
	}

	@Override
	public Status watch(final byte[]... keys){
		final CommandArguments args = CommandArguments.create("keys", keys);
		final JedisSentinelCommand<Status> command = JedisSentinelCommand.<Status>create(ProtocolCommand.WATCH)
				.general((cmd)->cmd.watch(keys), OkStatusConverter.INSTANCE)
				.transaction((cmd)->new Response<>(new Builder<String>() {

					@Override
					public String build(Object data){
						return cmd.watch(keys);
					}

				}), OkStatusConverter.INSTANCE);
		return execute(command, args);
	}

	@Override
	public Status unwatch(){
		final JedisSentinelCommand<Status> command = JedisSentinelCommand.<Status>create(ProtocolCommand.UNWATCH)
				.general((cmd)->cmd.unwatch(), OkStatusConverter.INSTANCE)
				.transaction((cmd)->new Response<>(new Builder<String>() {

					@Override
					public String build(Object data){
						return cmd.unwatch();
					}

				}), OkStatusConverter.INSTANCE);
		return execute(command);
	}

}
