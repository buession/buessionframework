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
import com.buession.redis.client.jedis.JedisRedisClient;
import com.buession.redis.client.operations.StringOperations;
import com.buession.redis.core.BitOperation;
import com.buession.redis.core.ClusterMode;
import com.buession.redis.core.command.StringCommands;
import com.buession.redis.core.convert.JedisConverters;
import com.buession.redis.utils.ReturnUtils;
import com.buession.redis.utils.SafeEncoder;
import org.springframework.core.convert.converter.Converter;
import redis.clients.jedis.BitOP;
import redis.clients.jedis.BitPosParams;
import redis.clients.jedis.PipelineBase;
import redis.clients.jedis.commands.JedisCommands;
import redis.clients.jedis.params.SetParams;

import java.util.List;

/**
 * @author Yong.Teng
 */
public abstract class AbstractStringOperations<C extends JedisCommands, P extends PipelineBase> extends AbstractJedisRedisClientOperations<C, P> implements StringOperations {

	protected final static Converter<BitOperation, BitOP> BIT_OPERATION_JEDIS_CONVERTER =
			JedisConverters.bitOperationJedisConverter();

	protected final static Converter<StringCommands.SetArgument, SetParams> SET_ARGUMENT_JEDIS_CONVERTER =
			JedisConverters.setArgumentJedisConverter();

	public AbstractStringOperations(final JedisRedisClient<C> client, final ClusterMode clusterMode){
		super(client, clusterMode);
	}

	@Override
	public Long append(final String key, final String value){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().append(key, value)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().append(key, value)));
		}else{
			return execute((cmd)->cmd.append(key, value));
		}
	}

	@Override
	public Long bitCount(final String key){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().bitcount(key)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().bitcount(key)));
		}else{
			return execute((cmd)->cmd.bitcount(key));
		}
	}

	@Override
	public Long bitCount(final String key, final int start, final int end){
		return bitCount(key, (long) start, (long) end);
	}

	@Override
	public Long bitCount(final byte[] key, final int start, final int end){
		return bitCount(key, (long) start, (long) end);
	}

	@Override
	public Long bitCount(final String key, final long start, final long end){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().bitcount(key, start, end)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().bitcount(key, start, end)));
		}else{
			return execute((cmd)->cmd.bitcount(key, start, end));
		}
	}

	@Override
	public List<Long> bitField(final String key, final String... arguments){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().bitfield(key, arguments)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().bitfield(key, arguments)));
		}else{
			return execute((cmd)->cmd.bitfield(key, arguments));
		}
	}

	@Override
	public Long bitPos(final String key, final boolean value){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().bitpos(key, value)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().bitpos(key, value)));
		}else{
			return execute((cmd)->cmd.bitpos(key, value));
		}
	}

	@Override
	public Long bitPos(final String key, final boolean value, final int start, final int end){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().bitpos(key, value,
					new BitPosParams(start, end))));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().bitpos(key, value, new BitPosParams(start
					, end))));
		}else{
			return execute((cmd)->cmd.bitpos(key, value, new BitPosParams(start, end)));
		}
	}

	@Override
	public Long bitPos(final String key, final boolean value, final long start, final long end){
		return bitPos(key, value, (int) start, (int) end);
	}

	@Override
	public Long bitPos(final byte[] key, final boolean value, final long start, final long end){
		return bitPos(key, value, (int) start, (int) end);
	}

	@Override
	public Long decr(final String key){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().decr(key)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().decr(key)));
		}else{
			return execute((cmd)->cmd.decr(key));
		}
	}

	@Override
	public Long decrBy(final String key, final int value){
		return decrBy(key, (long) value);
	}

	@Override
	public Long decrBy(final byte[] key, final int value){
		return decrBy(key, (long) value);
	}

	@Override
	public Long decrBy(final String key, final long value){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().decrBy(key, value)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().decrBy(key, value)));
		}else{
			return execute((cmd)->cmd.decrBy(key, value));
		}
	}

	@Override
	public String get(final String key){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().get(key)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().get(key)));
		}else{
			return execute((cmd)->cmd.get(key));
		}
	}

	@Override
	public Status getBit(final String key, final int offset){
		return getBit(key, (long) offset);
	}

	@Override
	public Status getBit(final byte[] key, final int offset){
		return getBit(key, (long) offset);
	}

	@Override
	public Status getBit(final String key, final long offset){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().getbit(key, offset),
					BOOLEAN_TO_STATUS_CONVERTER));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().getbit(key, offset),
					BOOLEAN_TO_STATUS_CONVERTER));
		}else{
			return execute((cmd)->ReturnUtils.statusForBool(cmd.getbit(key, offset)));
		}
	}

	@Override
	public String getRange(final String key, final int start, final int end){
		return getRange(key, (long) start, (long) end);
	}

	@Override
	public byte[] getRange(final byte[] key, final int start, final int end){
		return getRange(key, (long) start, (long) end);
	}

	@Override
	public String getRange(final String key, final long start, final long end){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().getrange(key, start, end)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().getrange(key, start, end)));
		}else{
			return execute((cmd)->cmd.getrange(key, start, end));
		}
	}

	@Override
	public String getSet(final String key, final String value){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().getSet(key, value)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().getSet(key, value)));
		}else{
			return execute((cmd)->cmd.getSet(key, value));
		}
	}

	@Override
	public Long incr(final String key){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().incr(key)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().incr(key)));
		}else{
			return execute((cmd)->cmd.incr(key));
		}
	}

	@Override
	public Long incrBy(final String key, final int value){
		return incrBy(key, (long) value);
	}

	@Override
	public Long incrBy(final byte[] key, final int value){
		return incrBy(key, (long) value);
	}

	@Override
	public Long incrBy(final String key, final long value){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().incrBy(key, value)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().incrBy(key, value)));
		}else{
			return execute((cmd)->cmd.incrBy(key, value));
		}
	}

	@Override
	public Double incrByFloat(final String key, final float value){
		return incrByFloat(key, (double) value);
	}

	@Override
	public Double incrByFloat(final byte[] key, final float value){
		return incrByFloat(key, (double) value);
	}

	@Override
	public Double incrByFloat(final String key, final double value){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().incrByFloat(key, value)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().incrByFloat(key, value)));
		}else{
			return execute((cmd)->cmd.incrByFloat(key, value));
		}
	}

	@Override
	public Status pSetEx(final String key, final String value, final int lifetime){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().psetex(key, lifetime, value),
					OK_TO_STATUS_CONVERTER));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().psetex(key, lifetime, value),
					OK_TO_STATUS_CONVERTER));
		}else{
			return execute((cmd)->ReturnUtils.statusForOK(cmd.psetex(key, lifetime, value)));
		}
	}

	@Override
	public Status set(final String key, final String value){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().set(key, value), OK_TO_STATUS_CONVERTER));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().set(key, value), OK_TO_STATUS_CONVERTER));
		}else{
			return execute((cmd)->ReturnUtils.statusForOK(cmd.set(key, value)));
		}
	}

	@Override
	public Status set(final String key, final String value, final SetArgument setArgument){
		final SetParams setParams = SET_ARGUMENT_JEDIS_CONVERTER.convert(setArgument);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().set(key, value, setParams),
					OK_TO_STATUS_CONVERTER));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().set(key, value, setParams),
					OK_TO_STATUS_CONVERTER));
		}else{
			return execute((cmd)->ReturnUtils.statusForOK(cmd.set(key, value, setParams)));
		}
	}

	@Override
	public Status setBit(final String key, final int offset, final String value){
		return setBit(key, (long) offset, value);
	}

	@Override
	public Status setBit(final byte[] key, final int offset, final byte[] value){
		return setBit(key, (long) offset, value);
	}

	@Override
	public Status setBit(final String key, final long offset, final String value){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().setbit(SafeEncoder.encode(key), offset,
					SafeEncoder.encode(value)), BOOLEAN_TO_STATUS_CONVERTER));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().setbit(SafeEncoder.encode(key), offset,
					SafeEncoder.encode(value)), BOOLEAN_TO_STATUS_CONVERTER));
		}else{
			return execute((cmd)->ReturnUtils.statusForBool(cmd.setbit(key, offset, value)));
		}
	}

	@Override
	public Status setBit(final String key, final int offset, final boolean value){
		return setBit(key, (long) offset, value);
	}

	@Override
	public Status setBit(final byte[] key, final int offset, final boolean value){
		return setBit(key, (long) offset, value);
	}

	@Override
	public Status setBit(final String key, final long offset, final boolean value){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().setbit(key, offset, value),
					BOOLEAN_TO_STATUS_CONVERTER));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().setbit(key, offset, value),
					BOOLEAN_TO_STATUS_CONVERTER));
		}else{
			return execute((cmd)->ReturnUtils.statusForBool(cmd.setbit(key, offset, value)));
		}
	}

	@Override
	public Status setEx(final String key, final String value, final int lifetime){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().setex(key, lifetime, value),
					OK_TO_STATUS_CONVERTER));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().setex(key, lifetime, value),
					OK_TO_STATUS_CONVERTER));
		}else{
			return execute((cmd)->ReturnUtils.statusForOK(cmd.setex(key, lifetime, value)));
		}
	}

	@Override
	public Status setNx(final String key, final String value){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().setnx(key, value),
					POSITIVE_LONG_NUMBER_TO_STATUS_CONVERTER));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().setnx(key, value),
					POSITIVE_LONG_NUMBER_TO_STATUS_CONVERTER));
		}else{
			return execute((cmd)->ReturnUtils.statusForBool(cmd.setnx(key, value) > 0));
		}
	}

	@Override
	public Long setRange(final String key, final int offset, final String value){
		return setRange(key, (long) offset, value);
	}

	@Override
	public Long setRange(final byte[] key, final int offset, final byte[] value){
		return setRange(key, (long) offset, value);
	}

	@Override
	public Long setRange(final String key, final long offset, final String value){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().setrange(key, offset, value)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().setrange(key, offset, value)));
		}else{
			return execute((cmd)->cmd.setrange(key, offset, value));
		}
	}

	@Override
	public Long strlen(final String key){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().strlen(key)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().strlen(key)));
		}else{
			return execute((cmd)->cmd.strlen(key));
		}
	}

	@Override
	public String substr(final String key, final long start, final long end){
		return substr(key, (int) start, (int) end);
	}

	@Override
	public byte[] substr(final byte[] key, final long start, final long end){
		return substr(key, (int) start, (int) end);
	}

	@Override
	public String substr(final String key, final int start, final int end){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().substr(key, start, end)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().substr(key, start, end)));
		}else{
			return execute((cmd)->cmd.substr(key, start, end));
		}
	}

}
