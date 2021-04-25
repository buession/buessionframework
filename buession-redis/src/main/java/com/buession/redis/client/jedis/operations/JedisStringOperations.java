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
 * | Copyright @ 2013-2021 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.client.jedis.operations;

import com.buession.core.converter.BooleanStatusConvert;
import com.buession.core.converter.PredicateStatusConverter;
import com.buession.lang.Status;
import com.buession.redis.client.jedis.JedisClient;
import com.buession.redis.core.BitOperation;
import com.buession.redis.core.Constants;
import com.buession.redis.core.convert.jedis.BitOperationJedisConverter;
import com.buession.redis.core.convert.jedis.SetArgumentJedisConverter;
import com.buession.redis.utils.ReturnUtils;
import com.buession.redis.utils.SafeEncoder;
import redis.clients.jedis.BitOP;
import redis.clients.jedis.BitPosParams;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.params.SetParams;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Yong.Teng
 */
public class JedisStringOperations extends AbstractStringOperations<Jedis, Pipeline> {

	public JedisStringOperations(final JedisClient client){
		super(client, null);
	}

	@Override
	public Long append(final byte[] key, final byte[] value){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().append(key, value)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().append(key, value)));
		}else{
			return execute((cmd)->cmd.append(key, value));
		}
	}

	@Override
	public Long bitCount(final byte[] key){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().bitcount(key)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().bitcount(key)));
		}else{
			return execute((cmd)->cmd.bitcount(key));
		}
	}

	@Override
	public Long bitCount(final byte[] key, final long start, final long end){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().bitcount(key, start, end)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().bitcount(key, start, end)));
		}else{
			return execute((cmd)->cmd.bitcount(key, start, end));
		}
	}

	@Override
	public List<Long> bitField(final byte[] key, final byte[]... arguments){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().bitfield(key, arguments)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().bitfield(key, arguments)));
		}else{
			return execute((cmd)->cmd.bitfield(key, arguments));
		}
	}

	@Override
	public Long bitOp(final BitOperation operation, final String destKey, final String... keys){
		final BitOP bitOP = new BitOperationJedisConverter().convert(operation);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().bitop(bitOP, destKey, keys)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().bitop(bitOP, destKey, keys)));
		}else{
			return execute((cmd)->cmd.bitop(bitOP, destKey, keys));
		}
	}

	@Override
	public Long bitOp(final BitOperation operation, final byte[] destKey, final byte[]... keys){
		final BitOP bitOP = new BitOperationJedisConverter().convert(operation);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().bitop(bitOP, destKey, keys)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().bitop(bitOP, destKey, keys)));
		}else{
			return execute((cmd)->cmd.bitop(bitOP, destKey, keys));
		}
	}

	@Override
	public Long bitPos(final byte[] key, final boolean value){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().bitpos(key, value)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().bitpos(key, value)));
		}else{
			return execute((cmd)->cmd.bitpos(key, value));
		}
	}

	@Override
	public Long bitPos(final byte[] key, final boolean value, final int start, final int end){
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
	public Long decr(final byte[] key){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().decr(key)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().decr(key)));
		}else{
			return execute((cmd)->cmd.decr(key));
		}
	}

	@Override
	public Long decrBy(final byte[] key, final long value){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().decrBy(key, value)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().decrBy(key, value)));
		}else{
			return execute((cmd)->cmd.decrBy(key, value));
		}
	}

	@Override
	public byte[] get(final byte[] key){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().get(key)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().get(key)));
		}else{
			return execute((cmd)->cmd.get(key));
		}
	}

	@Override
	public Status getBit(final byte[] key, final long offset){
		final BooleanStatusConvert convert = new BooleanStatusConvert();

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().getbit(key, offset), convert));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().getbit(key, offset), convert));
		}else{
			return execute((cmd)->cmd.getbit(key, offset), convert);
		}
	}

	@Override
	public byte[] getRange(final byte[] key, final long start, final long end){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().getrange(key, start, end)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().getrange(key, start, end)));
		}else{
			return execute((cmd)->cmd.getrange(key, start, end));
		}
	}

	@Override
	public byte[] getSet(final byte[] key, final byte[] value){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().getSet(key, value)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().getSet(key, value)));
		}else{
			return execute((cmd)->cmd.getSet(key, value));
		}
	}

	@Override
	public Long incr(final byte[] key){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().incr(key)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().incr(key)));
		}else{
			return execute((cmd)->cmd.incr(key));
		}
	}

	@Override
	public Long incrBy(final byte[] key, final long value){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().incrBy(key, value)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().incrBy(key, value)));
		}else{
			return execute((cmd)->cmd.incrBy(key, value));
		}
	}

	@Override
	public Double incrByFloat(final byte[] key, final double value){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().incrByFloat(key, value)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().incrByFloat(key, value)));
		}else{
			return execute((cmd)->cmd.incrByFloat(key, value));
		}
	}

	@Override
	public List<String> mGet(final String... keys){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().mget(keys)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().mget(keys)));
		}else{
			return execute((cmd)->cmd.mget(keys));
		}
	}

	@Override
	public List<byte[]> mGet(final byte[]... keys){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().mget(keys)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().mget(keys)));
		}else{
			return execute((cmd)->cmd.mget(keys));
		}
	}

	@Override
	public Status mSet(final Map<String, String> values){
		final List<String> temp = new ArrayList<>(values.size() * 2);

		values.forEach((key, value)->{
			temp.add(key);
			temp.add(value);
		});

		final String[] keysValues = temp.stream().toArray(String[]::new);
		final PredicateStatusConverter<String> converter =
				new PredicateStatusConverter<>((val)->Constants.OK.equalsIgnoreCase(val));

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().mset(keysValues), converter));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().mset(keysValues), converter));
		}else{
			return execute((cmd)->cmd.mset(keysValues), converter);
		}
	}

	@Override
	public Status mSetNx(final Map<String, String> values){
		final List<String> temp = new ArrayList<>(values.size() * 2);

		values.forEach((key, value)->{
			temp.add(key);
			temp.add(value);
		});

		final PredicateStatusConverter<Long> converter = new PredicateStatusConverter<>((val)->val > 0);
		final String[] keysValues = temp.stream().toArray(String[]::new);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().msetnx(keysValues), converter));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().msetnx(keysValues), converter));
		}else{
			return execute((cmd)->cmd.msetnx(keysValues), converter);
		}
	}

	@Override
	public Status pSetEx(final byte[] key, final byte[] value, final int lifetime){
		final PredicateStatusConverter<String> converter =
				new PredicateStatusConverter<>((val)->Constants.OK.equalsIgnoreCase(val));

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().psetex(key, lifetime, value), converter));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().psetex(key, lifetime, value), converter));
		}else{
			return execute((cmd)->cmd.psetex(key, lifetime, value), converter);
		}
	}

	@Override
	public Status set(final byte[] key, final byte[] value){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().set(key, value)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().set(key, value)));
		}else{
			return execute((cmd)->ReturnUtils.statusForOK(cmd.set(key, value)));
		}
	}

	@Override
	public Status set(final byte[] key, final byte[] value, final SetArgument setArgument){
		final SetParams setParams = new SetArgumentJedisConverter().convert(setArgument);
		final PredicateStatusConverter<String> converter =
				new PredicateStatusConverter<>((val)->Constants.OK.equalsIgnoreCase(val));

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().set(key, value, setParams), converter));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().set(key, value, setParams), converter));
		}else{
			return execute((cmd)->cmd.set(key, value, setParams), converter);
		}
	}

	@Override
	public Status setBit(final byte[] key, final long offset, final byte[] value){
		final BooleanStatusConvert convert = new BooleanStatusConvert();

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().setbit(key, offset, value), convert));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().setbit(key, offset, value), convert));
		}else{
			return execute((cmd)->cmd.setbit(key, offset, value), convert);
		}
	}

	@Override
	public Status setBit(final byte[] key, final long offset, final boolean value){
		final BooleanStatusConvert convert = new BooleanStatusConvert();

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().setbit(SafeEncoder.encode(key), offset, value),
					convert));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().setbit(SafeEncoder.encode(key), offset,
					value), convert));
		}else{
			return execute((cmd)->cmd.setbit(key, offset, value), convert);
		}
	}

	@Override
	public Status setEx(final byte[] key, final byte[] value, final int lifetime){
		final PredicateStatusConverter<String> converter =
				new PredicateStatusConverter<>((val)->Constants.OK.equalsIgnoreCase(val));

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().setex(key, lifetime, value), converter));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().setex(key, lifetime, value), converter));
		}else{
			return execute((cmd)->cmd.setex(key, lifetime, value), converter);
		}
	}

	@Override
	public Status setNx(final byte[] key, final byte[] value){
		final PredicateStatusConverter<Long> converter = new PredicateStatusConverter<>((val)->val > 0);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().setnx(key, value), converter));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().setnx(key, value), converter));
		}else{
			return execute((cmd)->cmd.setnx(key, value), converter);
		}
	}

	@Override
	public Long setRange(final byte[] key, final long offset, final byte[] value){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().setrange(key, offset, value)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().setrange(key, offset, value)));
		}else{
			return execute((cmd)->cmd.setrange(key, offset, value));
		}
	}

	@Override
	public Long strlen(final byte[] key){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().strlen(key)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().strlen(key)));
		}else{
			return execute((cmd)->cmd.strlen(key));
		}
	}

	@Override
	public byte[] substr(final byte[] key, final int start, final int end){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().substr(key, start, end),
					(value)->SafeEncoder.encode(value)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().substr(key, start, end),
					(value)->SafeEncoder.encode(value)));
		}else{
			return execute((cmd)->cmd.substr(key, start, end));
		}
	}

}
