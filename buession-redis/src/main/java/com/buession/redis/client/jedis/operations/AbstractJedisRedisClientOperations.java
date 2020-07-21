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
 * | Copyright @ 2013-2020 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.client.jedis.operations;

import com.buession.core.Executor;
import com.buession.redis.client.jedis.JedisRedisClient;
import com.buession.redis.client.operations.AbstractRedisClientOperations;
import com.buession.redis.core.RedisMode;
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.Tuple;
import com.buession.redis.core.convert.JedisConverters;
import com.buession.redis.core.jedis.JedisResult;
import com.buession.redis.exception.RedisException;
import com.buession.redis.pipeline.Pipeline;
import com.buession.redis.pipeline.jedis.JedisPipeline;
import com.buession.redis.transaction.jedis.JedisTransaction;
import org.springframework.core.convert.converter.Converter;
import redis.clients.jedis.PipelineBase;
import redis.clients.jedis.Response;
import redis.clients.jedis.commands.JedisCommands;

import java.util.List;

/**
 * @author Yong.Teng
 */
public abstract class AbstractJedisRedisClientOperations<C extends JedisCommands, P extends PipelineBase> extends AbstractRedisClientOperations<C> implements JedisRedisClientOperations<C, P> {

	protected final static Converter<redis.clients.jedis.ScanResult<String>, ScanResult<List<String>>> STRING_LIST_SCANRESULT_EXPOSE_CONVERTER = JedisConverters.listScanResultExposeConverter();

	protected final static Converter<redis.clients.jedis.ScanResult<byte[]>, ScanResult<List<byte[]>>> BINARY_LIST_SCANRESULT_EXPOSE_CONVERTER = JedisConverters.listScanResultExposeConverter();

	protected final static Converter<redis.clients.jedis.ScanResult<redis.clients.jedis.Tuple>,
			ScanResult<List<Tuple>>> LIST_TUPLE_SCANRESULT_EXPOSE_CONVERTER =
			JedisConverters.listTupleScanResultExposeConverter();

	protected JedisRedisClient<C> client;

	public AbstractJedisRedisClientOperations(final JedisRedisClient<C> client, final RedisMode redisMode){
		this.client = client;
		setRedisMode(redisMode);
	}

	@Override
	public <R> R execute(final Executor<C, R> executor) throws RedisException{
		return client.execute(executor);
	}

	protected redis.clients.jedis.Transaction getTransaction(){
		JedisTransaction jedisTransaction = (JedisTransaction) client.getConnection().getTransaction();
		return jedisTransaction.primitive();
	}

	@Override
	protected boolean isTransaction(){
		return client.getConnection().isTransaction();
	}

	@SuppressWarnings({"unchecked"})
	protected P getPipeline(){
		Pipeline pipeline = client.getConnection().getPipeline();

		if(pipeline == null){
			return null;
		}

		JedisPipeline<P> jedisPipeline = (JedisPipeline<P>) pipeline;
		return jedisPipeline.primitive();
	}

	@Override
	protected boolean isPipeline(){
		return client.getConnection().isPipeline();
	}

	protected <R> R transactionExecute(final Executor<C, JedisResult> executor) throws RedisException{
		client.getTxResults().add(execute(executor));
		return null;
	}

	protected <R> R pipelineExecute(final Executor<C, JedisResult> executor) throws RedisException{
		client.getTxResults().add(execute(executor));
		return null;
	}

	protected JedisResult newJedisResult(final Response<?> response){
		return JedisResult.JedisResultBuilder.forResponse(response).build();
	}

	protected <T, R> JedisResult newJedisResult(final Response<T> response, final Converter<T, R> converter){
		return JedisResult.JedisResultBuilder.<T, R>forResponse(response).mappedWith(converter).build();
	}

}
