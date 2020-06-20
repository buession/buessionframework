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

import com.buession.lang.Status;
import com.buession.redis.client.RedisClient;
import com.buession.redis.client.operations.KeyOperations;
import com.buession.redis.core.MigrateOperation;
import com.buession.redis.core.Type;
import com.buession.redis.core.command.KeyCommands;
import com.buession.redis.core.convert.JedisConverters;
import com.buession.redis.utils.ReturnUtils;
import com.buession.redis.utils.SafeEncoder;
import org.springframework.core.convert.converter.Converter;
import redis.clients.jedis.SortingParams;
import redis.clients.jedis.commands.JedisCommands;
import redis.clients.jedis.params.MigrateParams;

import java.util.List;

/**
 * @author Yong.Teng
 */
public abstract class AbstractKeyOperations<C extends JedisCommands> extends AbstractJedisRedisOperations<C> implements KeyOperations {

	protected final static Converter<SortArgument, SortingParams> SORT_ARGUMENT_JEDIS_CONVERTER =
			JedisConverters.sortArgumentJedisConverter();

	protected final static Converter<MigrateOperation, MigrateParams> MIGRATE_OPERATION_JEDIS_CONVERTER =
			JedisConverters.migrateOperationJedisConverter();

	public AbstractKeyOperations(final RedisClient client){
		super(client);
	}

	@Override
	public byte[] dump(final String key){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().dump(key)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().dump(key)));
		}else{
			return execute((cmd)->cmd.dump(key));
		}
	}

	@Override
	public boolean exists(final String key){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().exists(key)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().exists(key)));
		}else{
			return execute((cmd)->cmd.exists(key));
		}
	}

	@Override
	public Status expire(final String key, final int lifetime){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().expire(key, lifetime),
					JedisConverters.equalOneToStatusConverter()));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().expire(key, lifetime),
					JedisConverters.equalOneToStatusConverter()));
		}else{
			return execute((cmd)->ReturnUtils.statusForBool(cmd.expire(key, lifetime) == 1));
		}
	}

	@Override
	public Status expireAt(final String key, final long unixTimestamp){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().expireAt(key, unixTimestamp),
					JedisConverters.equalOneToStatusConverter()));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().expireAt(key, unixTimestamp),
					JedisConverters.equalOneToStatusConverter()));
		}else{
			return execute((cmd)->ReturnUtils.statusForBool(cmd.expireAt(key, unixTimestamp) == 1));
		}
	}

	@Override
	public Status move(final String key, final int db){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().move(key, db),
					JedisConverters.positiveLongNumberToStatusConverter()));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().move(key, db),
					JedisConverters.positiveLongNumberToStatusConverter()));
		}else{
			return execute((cmd)->ReturnUtils.statusForBool(cmd.move(key, db) > 0));
		}
	}

	@Override
	public Status persist(final String key){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().persist(key),
					JedisConverters.positiveLongNumberToStatusConverter()));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().persist(key),
					JedisConverters.positiveLongNumberToStatusConverter()));
		}else{
			return execute((cmd)->ReturnUtils.statusForBool(cmd.persist(key) > 0));
		}
	}

	@Override
	public Status pExpire(final String key, final int lifetime){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().pexpire(key, lifetime),
					JedisConverters.equalOneToStatusConverter()));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().pexpire(key, lifetime),
					JedisConverters.equalOneToStatusConverter()));
		}else{
			return execute((cmd)->ReturnUtils.statusForBool(cmd.pexpire(key, lifetime) == 1));
		}
	}

	@Override
	public Status pExpireAt(final String key, final long unixTimestamp){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().pexpireAt(key, unixTimestamp),
					JedisConverters.equalOneToStatusConverter()));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().pexpireAt(key, unixTimestamp),
					JedisConverters.equalOneToStatusConverter()));
		}else{
			return execute((cmd)->ReturnUtils.statusForBool(cmd.pexpireAt(key, unixTimestamp) == 1));
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
		final byte[] serializedEncodeValue = SafeEncoder.encode(serializedValue);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().restore(key, ttl, serializedEncodeValue),
					JedisConverters.okToStatusConverter()));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().restore(key, ttl, serializedEncodeValue),
					JedisConverters.okToStatusConverter()));
		}else{
			return execute((cmd)->ReturnUtils.statusForOK(cmd.restore(key, ttl, serializedEncodeValue)));
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
		final SortingParams soringParams = SORT_ARGUMENT_JEDIS_CONVERTER.convert(sortArgument);

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
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().type(key),
					JedisConverters.enumConverter(Type.class)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().type(key),
					JedisConverters.enumConverter(Type.class)));
		}else{
			return execute((cmd)->ReturnUtils.enumValueOf(cmd.type(key), Type.class));
		}
	}

}
