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

import com.buession.core.converter.EnumConverter;
import com.buession.core.converter.PredicateStatusConverter;
import com.buession.lang.Status;
import com.buession.redis.client.jedis.JedisRedisClient;
import com.buession.redis.client.operations.KeyOperations;
import com.buession.redis.core.Type;
import com.buession.redis.core.convert.OkStatusConverter;
import com.buession.redis.core.convert.jedis.MigrateOperationConverter;
import com.buession.redis.core.convert.jedis.SortArgumentConverter;
import com.buession.redis.utils.SafeEncoder;
import redis.clients.jedis.SortingParams;

import java.util.List;

/**
 * Jedis Key 命令操作抽象类
 *
 * @param <CMD>
 * 		Jedis 原始命令对象
 *
 * @author Yong.Teng
 */
public abstract class AbstractKeyOperations<CMD> extends AbstractJedisRedisOperations<CMD>
		implements KeyOperations<CMD> {

	protected final static MigrateOperationConverter.MigrateOperationJedisConverter MIGRATE_OPERATION_JEDIS_CONVERTER = new MigrateOperationConverter.MigrateOperationJedisConverter();

	public AbstractKeyOperations(final JedisRedisClient client){
		super(client);
	}

	@Override
	public Status persist(final String key){
		final PredicateStatusConverter<Long> converter = new PredicateStatusConverter<>((val)->val > 0);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().persist(key), converter));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().persist(key), converter));
		}else{
			return execute((cmd)->cmd.persist(key), converter);
		}
	}

	@Override
	public Status pExpire(final String key, final int lifetime){
		final PredicateStatusConverter<Long> converter = new PredicateStatusConverter<>((val)->val == 1);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().pexpire(key, lifetime), converter));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().pexpire(key, lifetime), converter));
		}else{
			return execute((cmd)->cmd.pexpire(key, lifetime), converter);
		}
	}

	@Override
	public Status pExpireAt(final String key, final long unixTimestamp){
		final PredicateStatusConverter<Long> converter = new PredicateStatusConverter<>((val)->val == 1);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().pexpireAt(key, unixTimestamp), converter));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().pexpireAt(key, unixTimestamp),
					converter));
		}else{
			return execute((cmd)->cmd.pexpireAt(key, unixTimestamp), converter);
		}
	}

	@Override
	public Long pTtl(final String key){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().pttl(key)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().pttl(key)));
		}else{
			return execute((cmd)->cmd.pttl(key));
		}
	}

	@Override
	public Status restore(final String key, final String serializedValue, final int ttl){
		final OkStatusConverter converter = new OkStatusConverter();
		final byte[] serializedEncodeValue = SafeEncoder.encode(serializedValue);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().restore(key, ttl, serializedEncodeValue),
					converter));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().restore(key, ttl, serializedEncodeValue),
					converter));
		}else{
			return execute((cmd)->cmd.restore(key, ttl, serializedEncodeValue), converter);
		}
	}

	@Override
	public List<String> sort(final String key){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().sort(key)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().sort(key)));
		}else{
			return execute((cmd)->cmd.sort(key));
		}
	}

	@Override
	public List<String> sort(final String key, final SortArgument sortArgument){
		final SortingParams soringParams = new SortArgumentConverter.SortArgumentJedisConverter().convert(sortArgument);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().sort(key, soringParams)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().sort(key, soringParams)));
		}else{
			return execute((cmd)->cmd.sort(key, soringParams));
		}
	}

	@Override
	public Long ttl(final String key){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().ttl(key)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().ttl(key)));
		}else{
			return execute((cmd)->cmd.ttl(key));
		}
	}

	@Override
	public Type type(final String key){
		final EnumConverter<Type> converter = new EnumConverter<>(Type.class);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().type(key), converter));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().type(key), converter));
		}else{
			return execute((cmd)->cmd.type(key), converter);
		}
	}

}
