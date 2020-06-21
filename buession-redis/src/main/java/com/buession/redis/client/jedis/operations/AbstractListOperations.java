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
import com.buession.redis.client.operations.ListOperations;
import com.buession.redis.core.ListPosition;
import com.buession.redis.core.convert.JedisConverters;
import com.buession.redis.utils.ReturnUtils;
import org.springframework.core.convert.converter.Converter;
import redis.clients.jedis.PipelineBase;
import redis.clients.jedis.commands.JedisCommands;

import java.util.List;

/**
 * @author Yong.Teng
 */
public abstract class AbstractListOperations<C extends JedisCommands, P extends PipelineBase> extends AbstractJedisRedisOperations<C, P> implements ListOperations {

	protected final static Converter<ListPosition, redis.clients.jedis.ListPosition> LISTPOSITION_JEDIS_CONVERTER =
			JedisConverters.listPositionJedisConverter();

	public AbstractListOperations(final RedisClient client){
		super(client);
	}

	@Override
	public String lIndex(final String key, final int index){
		return lIndex(key, (long) index);
	}

	@Override
	public String lIndex(final String key, final long index){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().lindex(key, index)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().lindex(key, index)));
		}else{
			return execute((cmd)->cmd.lindex(key, index));
		}
	}

	@Override
	public Long lInsert(final String key, final String value, final ListPosition position, final String pivot){
		final redis.clients.jedis.ListPosition pos = LISTPOSITION_JEDIS_CONVERTER.convert(position);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().linsert(key, pos, pivot, value)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().linsert(key, pos, pivot, value)));
		}else{
			return execute((cmd)->cmd.linsert(key, pos, pivot, value));
		}
	}

	@Override
	public Long lLen(final String key){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().llen(key)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().llen(key)));
		}else{
			return execute((cmd)->cmd.llen(key));
		}
	}

	@Override
	public String lPop(final String key){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().lpop(key)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().lpop(key)));
		}else{
			return execute((cmd)->cmd.lpop(key));
		}
	}

	@Override
	public Long lPush(final String key, final String... values){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().lpush(key, values)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().lpush(key, values)));
		}else{
			return execute((cmd)->cmd.lpush(key, values));
		}
	}

	@Override
	public Long lPushX(final String key, final String... values){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().lpushx(key, values)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().lpushx(key, values)));
		}else{
			return execute((cmd)->cmd.lpushx(key, values));
		}
	}

	@Override
	public List<String> lRange(final String key, final int start, final int end){
		return lRange(key, (long) start, (long) end);
	}

	@Override
	public List<String> lRange(final String key, final long start, final long end){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().lrange(key, start, end)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().lrange(key, start, end)));
		}else{
			return execute((cmd)->cmd.lrange(key, start, end));
		}
	}

	@Override
	public Long lRem(final String key, final String value, final int count){
		return lRem(key, value, (long) count);
	}

	@Override
	public Long lRem(final String key, final String value, final long count){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().lrem(key, count, value)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().lrem(key, count, value)));
		}else{
			return execute((cmd)->cmd.lrem(key, count, value));
		}
	}

	@Override
	public Status lSet(final String key, final int index, final String value){
		return lSet(key, (long) index, value);
	}

	@Override
	public Status lSet(final String key, final long index, final String value){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().lset(key, index, value),
					JedisConverters.okToStatusConverter()));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().lset(key, index, value),
					JedisConverters.okToStatusConverter()));
		}else{
			return execute((cmd)->ReturnUtils.statusForOK(cmd.lset(key, index, value)));
		}
	}

	@Override
	public Status lTrim(final String key, final int start, final int end){
		return lTrim(key, (long) start, (long) end);
	}

	@Override
	public Status lTrim(final String key, final long start, final long end){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().ltrim(key, start, end),
					JedisConverters.okToStatusConverter()));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().ltrim(key, start, end),
					JedisConverters.okToStatusConverter()));
		}else{
			return execute((cmd)->ReturnUtils.statusForOK(cmd.ltrim(key, start, end)));
		}
	}

	@Override
	public String rPop(final String key){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().rpop(key)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().rpop(key)));
		}else{
			return execute((cmd)->cmd.rpop(key));
		}
	}

	@Override
	public Long rPush(final String key, final String... values){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().rpush(key, values)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().rpush(key, values)));
		}else{
			return execute((cmd)->cmd.rpush(key, values));
		}
	}

	@Override
	public Long rPushX(final String key, final String... values){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().rpushx(key, values)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().rpushx(key, values)));
		}else{
			return execute((cmd)->cmd.rpushx(key, values));
		}
	}

}
