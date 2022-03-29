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

import com.buession.core.Executor;
import com.buession.core.converter.Converter;
import com.buession.redis.client.connection.jedis.JedisRedisConnection;
import com.buession.redis.client.jedis.JedisRedisClient;
import com.buession.redis.client.operations.AbstractRedisOperations;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.CommandNotSupported;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.internal.jedis.JedisResult;
import com.buession.redis.exception.RedisException;
import com.buession.redis.exception.RedisExceptionUtils;
import com.buession.redis.pipeline.Pipeline;
import com.buession.redis.pipeline.jedis.JedisPipeline;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Response;

/**
 * Jedis Redis 命令操作抽象类
 *
 * @param <CMD>
 * 		Jedis 原始命令对象
 *
 * @author Yong.Teng
 */
public abstract class AbstractJedisRedisOperations<CMD> extends AbstractRedisOperations<CMD>
		implements JedisRedisOperations<CMD> {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	protected JedisRedisClient client;

	public AbstractJedisRedisOperations(final JedisRedisClient client){
		this.client = client;
	}

	@Override
	public <R> R execute(final Executor<CMD, R> executor, final ProtocolCommand command) throws RedisException{
		return execute(executor, command, null);
	}

	@Override
	public <R> R execute(final Executor<CMD, R> executor, final ProtocolCommand command,
						 final CommandArguments arguments) throws RedisException{
		return client.execute(executor, command);
	}

	@Override
	public <SR, R> R execute(final Executor<CMD, SR> executor, final Converter<SR, R> converter,
							 final ProtocolCommand command) throws RedisException{
		return execute(executor, converter, command, null);
	}

	@Override
	public <SR, R> R execute(final Executor<CMD, SR> executor, final Converter<SR, R> converter,
							 final ProtocolCommand command, final CommandArguments arguments) throws RedisException{
		return converter.convert(execute(executor, command, arguments));
	}

	protected <R> R execute(final CommandNotSupported commandNotSupported, final ProtocolCommand command)
			throws RedisException{
		return execute(commandNotSupported, command, null);
	}

	protected <R> R execute(final CommandNotSupported commandNotSupported, final ProtocolCommand command,
							final CommandArguments arguments) throws RedisException{
		return execute((cmd)->commandNotSupported(command, commandNotSupported), command, arguments);
	}

	@SuppressWarnings({"unchecked"})
	protected <R> R transactionExecute(final Executor<CMD, JedisResult> executor, final ProtocolCommand command)
			throws RedisException{
		return transactionExecute(executor, command, null);
	}

	@SuppressWarnings({"unchecked"})
	protected <R> R transactionExecute(final Executor<CMD, JedisResult> executor, final ProtocolCommand command,
									   final CommandArguments arguments)
			throws RedisException{
		client.getTxResults().add(execute(executor, command, arguments));
		return null;
	}

	@SuppressWarnings({"unchecked"})
	protected <R> R pipelineExecute(final Executor<CMD, JedisResult> executor, final ProtocolCommand command)
			throws RedisException{
		return pipelineExecute(executor, command, null);
	}

	@SuppressWarnings({"unchecked"})
	protected <R> R pipelineExecute(final Executor<CMD, JedisResult> executor, final ProtocolCommand command,
									final CommandArguments arguments) throws RedisException{
		client.getTxResults().add(execute(executor, command, arguments));
		return null;
	}

	protected <R> R commandNotSupported(final ProtocolCommand command, final CommandNotSupported commandNotSupported){
		RedisExceptionUtils.commandNotSupportedException(command, commandNotSupported, client.getConnection());
		return null;
	}

	protected redis.clients.jedis.Transaction getTransaction(){
		JedisRedisConnection connection = (JedisRedisConnection) client.getConnection();
		redis.clients.jedis.Transaction transaction = connection.getTransaction();

		if(transaction == null){
			throw new IllegalStateException("Connection transaction does not active");
		}

		return transaction;
	}

	@Override
	protected boolean isTransaction(){
		return client.getConnection().isTransaction();
	}

	protected redis.clients.jedis.Pipeline getPipeline(){
		Pipeline pipeline = client.pipeline();

		if(pipeline == null){
			return null;
		}

		JedisPipeline jedisPipeline = (JedisPipeline) pipeline;
		return jedisPipeline.primitive();
	}

	@Override
	protected boolean isPipeline(){
		return client.getConnection().isPipeline();
	}

	protected <T, R> JedisResult<T, R> newJedisResult(final Response<T> response){
		return JedisResult.Builder.<T, R>forResponse(response).build();
	}

	protected <T, R> JedisResult<T, R> newJedisResult(final Response<T> response, final Converter<T, R> converter){
		return JedisResult.Builder.<T, R>forResponse(response).mappedWith(converter).build();
	}

}
