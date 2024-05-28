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

import com.buession.lang.Status;
import com.buession.redis.client.connection.RedisConnection;
import com.buession.redis.client.jedis.JedisStandaloneClient;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.ProtocolCommand;
import redis.clients.jedis.Builder;
import redis.clients.jedis.Response;

import java.util.List;

/**
 * Jedis 单机模式事务命令操作
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public final class JedisTransactionOperations extends AbstractTransactionOperations<JedisStandaloneClient> {

	public JedisTransactionOperations(final JedisStandaloneClient client) {
		super(client);
	}

	@Override
	public Status multi() {
		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.MULTI, (cmd)->{
				RedisConnection connection = client.getConnection();

				return new Response<>(new Builder<Status>() {

					@Override
					public Status build(Object data) {
						try{
							connection.multi();
							return Status.SUCCESS;
						}catch(Exception e){
							return Status.FAILURE;
						}
					}

				});
			}, (v)->v)
					.run();
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.MULTI, (cmd)->{
				RedisConnection connection = client.getConnection();

				return new Response<>(new Builder<Status>() {

					@Override
					public Status build(Object data) {
						try{
							connection.multi();
							return Status.SUCCESS;
						}catch(Exception e){
							return Status.FAILURE;
						}
					}

				});
			}, (v)->v)
					.run();
		}else{
			return new JedisCommand<>(client, ProtocolCommand.MULTI, (cmd)->{
				RedisConnection connection = client.getConnection();
				try{
					connection.multi();
					return Status.SUCCESS;
				}catch(Exception e){
					return Status.FAILURE;
				}
			}, (v)->v)
					.run();
		}
	}

	@Override
	public List<Object> exec() {
		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.EXEC, (cmd)->{
				RedisConnection connection = client.getConnection();

				return new Response<>(new Builder<List<Object>>() {

					@Override
					public List<Object> build(Object data) {
						return connection.exec();
					}

				});
			}, (v)->v).run();
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.EXEC, (cmd)->{
				RedisConnection connection = client.getConnection();

				return new Response<>(new Builder<List<Object>>() {

					@Override
					public List<Object> build(Object data) {
						return connection.exec();
					}

				});
			}, (v)->v).run();
		}else{
			return new JedisCommand<>(client, ProtocolCommand.EXEC, (cmd)->{
				RedisConnection connection = client.getConnection();
				return connection.exec();
			}, (v)->v).run();
		}
	}

	@Override
	public void discard() {
		if(isPipeline()){
			new JedisPipelineCommand<>(client, ProtocolCommand.DISCARD, (cmd)->{
				RedisConnection connection = client.getConnection();
				connection.discard();
				return null;
			}, (v)->v)
					.run();
		}else if(isTransaction()){
			new JedisTransactionCommand<>(client, ProtocolCommand.DISCARD, (cmd)->{
				RedisConnection connection = client.getConnection();
				connection.discard();
				return null;
			}, (v)->v)
					.run();
		}else{
			new JedisCommand<>(client, ProtocolCommand.DISCARD, (cmd)->{
				RedisConnection connection = client.getConnection();
				connection.discard();
				return null;
			}, (v)->v)
					.run();
		}
	}

	@Override
	public Status watch(final String... keys) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys);

		if(isPipeline()){
			return new JedisPipelineCommand<Status, Status>(client, ProtocolCommand.WATCH)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.WATCH,
					(cmd)->new Response<>(new Builder<String>() {

						@Override
						public String build(Object data) {
							return cmd.watch(keys);
						}

					}), okStatusConverter)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.WATCH, (cmd)->cmd.watch(keys), okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status watch(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys);

		if(isPipeline()){
			return new JedisPipelineCommand<Status, Status>(client, ProtocolCommand.WATCH)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.WATCH,
					(cmd)->new Response<>(new Builder<String>() {

						@Override
						public String build(Object data) {
							return cmd.watch(keys);
						}

					}), okStatusConverter)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.WATCH, (cmd)->cmd.watch(keys), okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status unwatch() {
		if(isPipeline()){
			return new JedisPipelineCommand<Status, Status>(client, ProtocolCommand.UNWATCH)
					.run();
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.UNWATCH,
					(cmd)->new Response<>(new Builder<String>() {

						@Override
						public String build(Object data) {
							return cmd.unwatch();
						}

					}), okStatusConverter)
					.run();
		}else{
			return new JedisCommand<>(client, ProtocolCommand.UNWATCH, (cmd)->cmd.unwatch(), okStatusConverter)
					.run();
		}
	}

}
