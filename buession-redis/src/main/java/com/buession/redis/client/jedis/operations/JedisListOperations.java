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
import com.buession.redis.core.ListPosition;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.convert.JedisConverters;
import com.buession.redis.exception.NotSupportedPipelineCommandException;
import com.buession.redis.utils.ReturnUtils;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * @author Yong.Teng
 */
public class JedisListOperations extends AbstractListOperations<Jedis> {

	public JedisListOperations(final RedisClient client){
		super(client);
	}

	@Override
	public List<String> blPop(final String[] keys, final int timeout){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().blpop(keys[0])));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().blpop(timeout, keys)));
		}else{
			return execute((cmd)->cmd.blpop(timeout, keys));
		}
	}

	@Override
	public List<byte[]> blPop(final byte[][] keys, final int timeout){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().blpop(keys[0])));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().blpop(timeout, keys),
					STRING_TO_BINARY_LIST_CONVERTER));
		}else{
			return execute((cmd)->cmd.blpop(timeout, keys));
		}
	}

	@Override
	public List<String> brPop(final String[] keys, final int timeout){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().brpop(keys[0])));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().brpop(timeout, keys)));
		}else{
			return execute((cmd)->cmd.brpop(timeout, keys));
		}
	}

	@Override
	public List<byte[]> brPop(final byte[][] keys, final int timeout){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().brpop(keys[0])));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().brpop(timeout, keys),
					STRING_TO_BINARY_LIST_CONVERTER));
		}else{
			return execute((cmd)->cmd.brpop(timeout, keys));
		}
	}

	@Override
	public String brPoplPush(final String key, final String destKey, final int timeout){
		if(isPipeline()){
			throw new NotSupportedPipelineCommandException(ProtocolCommand.BRPOPLPUSH);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().brpoplpush(key, destKey, timeout)));
		}else{
			return execute((cmd)->cmd.brpoplpush(key, destKey, timeout));
		}
	}

	@Override
	public byte[] brPoplPush(final byte[] key, final byte[] destKey, final int timeout){
		if(isPipeline()){
			throw new NotSupportedPipelineCommandException(ProtocolCommand.BRPOPLPUSH);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().brpoplpush(key, destKey, timeout)));
		}else{
			return execute((cmd)->cmd.brpoplpush(key, destKey, timeout));
		}
	}

	@Override
	public byte[] lIndex(final byte[] key, final int index){
		return lIndex(key, (long) index);
	}

	@Override
	public byte[] lIndex(final byte[] key, final long index){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().lindex(key, index)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().lindex(key, index)));
		}else{
			return execute((cmd)->cmd.lindex(key, index));
		}
	}

	@Override
	public Long lInsert(final byte[] key, final byte[] value, final ListPosition position, final byte[] pivot){
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
	public Long lLen(final byte[] key){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().llen(key)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().llen(key)));
		}else{
			return execute((cmd)->cmd.llen(key));
		}
	}

	@Override
	public byte[] lPop(final byte[] key){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().lpop(key)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().lpop(key)));
		}else{
			return execute((cmd)->cmd.lpop(key));
		}
	}

	@Override
	public Long lPush(final byte[] key, final byte[]... values){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().lpush(key, values)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().lpush(key, values)));
		}else{
			return execute((cmd)->cmd.lpush(key, values));
		}
	}

	@Override
	public Long lPushX(final byte[] key, final byte[]... values){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().lpushx(key, values)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().lpushx(key, values)));
		}else{
			return execute((cmd)->cmd.lpushx(key, values));
		}
	}

	@Override
	public List<byte[]> lRange(final byte[] key, final int start, final int end){
		return lRange(key, (long) start, (long) end);
	}

	@Override
	public List<byte[]> lRange(final byte[] key, final long start, final long end){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().lrange(key, start, end)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().lrange(key, start, end)));
		}else{
			return execute((cmd)->cmd.lrange(key, start, end));
		}
	}

	@Override
	public Long lRem(final byte[] key, final byte[] value, final int count){
		return lRem(key, value, (long) count);
	}

	@Override
	public Long lRem(final byte[] key, final byte[] value, final long count){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().lrem(key, count, value)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().lrem(key, count, value)));
		}else{
			return execute((cmd)->cmd.lrem(key, count, value));
		}
	}

	@Override
	public Status lSet(final byte[] key, final int index, final byte[] value){
		return lSet(key, (long) index, value);
	}

	@Override
	public Status lSet(final byte[] key, final long index, final byte[] value){
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
	public Status lTrim(final byte[] key, final int start, final int end){
		return lTrim(key, (long) start, (long) end);
	}

	@Override
	public Status lTrim(final byte[] key, final long start, final long end){
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
	public byte[] rPop(final byte[] key){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().rpop(key)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().rpop(key)));
		}else{
			return execute((cmd)->cmd.rpop(key));
		}
	}

	@Override
	public String rPoplPush(final String key, final String destKey){
		if(isPipeline()){
			throw new NotSupportedPipelineCommandException(ProtocolCommand.RPOPLPUSH);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().rpoplpush(key, destKey)));
		}else{
			return execute((cmd)->cmd.rpoplpush(key, destKey));
		}
	}

	@Override
	public byte[] rPoplPush(final byte[] key, final byte[] destKey){
		if(isPipeline()){
			throw new NotSupportedPipelineCommandException(ProtocolCommand.RPOPLPUSH);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().rpoplpush(key, destKey)));
		}else{
			return execute((cmd)->cmd.rpoplpush(key, destKey));
		}
	}

	@Override
	public Long rPush(final byte[] key, final byte[]... values){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().rpush(key, values)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().rpush(key, values)));
		}else{
			return execute((cmd)->cmd.rpush(key, values));
		}
	}

	@Override
	public Long rPushX(final byte[] key, final byte[]... values){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().rpushx(key, values)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().rpushx(key, values)));
		}else{
			return execute((cmd)->cmd.rpushx(key, values));
		}
	}

}
