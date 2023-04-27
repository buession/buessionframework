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
 * | Copyright @ 2013-2023 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.client.lettuce.operations;

import com.buession.lang.Status;
import com.buession.redis.client.lettuce.LettuceStandaloneClient;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.internal.convert.Converters;
import com.buession.redis.core.internal.convert.lettuce.params.SetArgumentConverter;
import com.buession.redis.core.internal.convert.response.OkStatusConverter;
import com.buession.redis.utils.SafeEncoder;
import io.lettuce.core.KeyValue;
import io.lettuce.core.SetArgs;
import io.lettuce.core.Value;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Lettuce 单机模式字符串命令操作
 *
 * @author Yong.Teng
 * @since 2.3.0
 */
public final class LettuceStringOperations extends AbstractStringOperations<LettuceStandaloneClient> {

	public LettuceStringOperations(final LettuceStandaloneClient client){
		super(client);
	}

	@Override
	public Long append(final String key, final String value){
		return append(SafeEncoder.encode(key), SafeEncoder.encode(value));
	}

	@Override
	public Long append(final byte[] key, final byte[] value){
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);
		return new LettuceCommand<Long>(client, ProtocolCommand.APPEND)
				.general((cmd)->cmd.append(key, value))
				.pipeline((cmd)->cmd.append(key, value))
				.transaction((cmd)->cmd.append(key, value))
				.run(args);
	}

	@Override
	public Long incr(final String key){
		return incr(SafeEncoder.encode(key));
	}

	@Override
	public Long incr(final byte[] key){
		final CommandArguments args = CommandArguments.create("key", key);
		return new LettuceCommand<Long>(client, ProtocolCommand.INCR)
				.general((cmd)->cmd.incr(key))
				.pipeline((cmd)->cmd.incr(key))
				.transaction((cmd)->cmd.incr(key))
				.run(args);
	}

	@Override
	public Long incrBy(final String key, final long value){
		return incrBy(SafeEncoder.encode(key), value);
	}

	@Override
	public Long incrBy(final byte[] key, final long value){
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);
		return new LettuceCommand<Long>(client, ProtocolCommand.INCRBY)
				.general((cmd)->cmd.incrby(key, value))
				.pipeline((cmd)->cmd.incrby(key, value))
				.transaction((cmd)->cmd.incrby(key, value))
				.run(args);
	}

	@Override
	public Double incrByFloat(final String key, final double value){
		return incrByFloat(SafeEncoder.encode(key), value);
	}

	@Override
	public Double incrByFloat(final byte[] key, final double value){
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);
		return new LettuceCommand<Double>(client, ProtocolCommand.INCRBYFLOAT)
				.general((cmd)->cmd.incrbyfloat(key, value))
				.pipeline((cmd)->cmd.incrbyfloat(key, value))
				.transaction((cmd)->cmd.incrbyfloat(key, value))
				.run(args);
	}

	@Override
	public Long decr(final String key){
		return decr(SafeEncoder.encode(key));
	}

	@Override
	public Long decr(final byte[] key){
		final CommandArguments args = CommandArguments.create("key", key);
		return new LettuceCommand<Long>(client, ProtocolCommand.DECR)
				.general((cmd)->cmd.decr(key))
				.pipeline((cmd)->cmd.decr(key))
				.transaction((cmd)->cmd.decr(key))
				.run(args);
	}

	@Override
	public Long decrBy(final String key, final long value){
		return decrBy(SafeEncoder.encode(key), value);
	}

	@Override
	public Long decrBy(final byte[] key, final long value){
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);
		return new LettuceCommand<Long>(client, ProtocolCommand.DECRBY)
				.general((cmd)->cmd.decrby(key, value))
				.pipeline((cmd)->cmd.decrby(key, value))
				.transaction((cmd)->cmd.decrby(key, value))
				.run(args);
	}

	@Override
	public String get(final String key){
		return SafeEncoder.encode(get(SafeEncoder.encode(key)));
	}

	@Override
	public byte[] get(final byte[] key){
		final CommandArguments args = CommandArguments.create("key", key);
		return new LettuceCommand<byte[]>(client, ProtocolCommand.GET)
				.general((cmd)->cmd.get(key))
				.pipeline((cmd)->cmd.get(key))
				.transaction((cmd)->cmd.get(key))
				.run(args);
	}

	@Override
	public String getEx(final String key, final GetExArgument getExArgument){
		return SafeEncoder.encode(getEx(SafeEncoder.encode(key), getExArgument));
	}

	@Override
	public byte[] getEx(final byte[] key, final GetExArgument getExArgument){
		final CommandArguments args = CommandArguments.create("key", key).put("getExArgument", getExArgument);
		return new LettuceCommand<byte[]>(client, ProtocolCommand.GETEX)
				.run(args);
	}

	@Override
	public String getSet(final String key, final String value){
		return SafeEncoder.encode(getSet(SafeEncoder.encode(key), SafeEncoder.encode(value)));
	}

	@Override
	public byte[] getSet(final byte[] key, final byte[] value){
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);
		return new LettuceCommand<byte[]>(client, ProtocolCommand.GETSET)
				.general((cmd)->cmd.getset(key, value))
				.pipeline((cmd)->cmd.getset(key, value))
				.transaction((cmd)->cmd.getset(key, value))
				.run(args);
	}

	@Override
	public String getDel(final String key){
		return SafeEncoder.encode(getDel(SafeEncoder.encode(key)));
	}

	@Override
	public byte[] getDel(final byte[] key){
		final CommandArguments args = CommandArguments.create("key", key);
		return new LettuceCommand<byte[]>(client, ProtocolCommand.GETDEL)
				.run(args);
	}

	@Override
	public List<String> mGet(final String... keys){
		final List<byte[]> values = mGet(SafeEncoder.encode(keys));
		return values == null ? null : values.stream().map(SafeEncoder::encode).collect(Collectors.toList());
	}

	@Override
	public List<byte[]> mGet(final byte[]... keys){
		final CommandArguments args = CommandArguments.create("keys", keys);
		final List<KeyValue<byte[], byte[]>> values = new LettuceCommand<List<KeyValue<byte[], byte[]>>>(client,
				ProtocolCommand.MGET)
				.general((cmd)->cmd.mget(keys))
				.pipeline((cmd)->cmd.mget(keys))
				.transaction((cmd)->cmd.mget(keys))
				.run(args);
		return values == null ? null : values.stream().map(Value::getValue).collect(Collectors.toList());
	}

	@Override
	public Status mSet(final Map<String, String> values){
		final CommandArguments args = CommandArguments.create("values", values);
		final Map<byte[], byte[]> temp = new LinkedHashMap<>(values.size());

		values.forEach((key, value)->{
			temp.put(SafeEncoder.encode(key), SafeEncoder.encode(value));
		});

		return new LettuceCommand<Status>(client, ProtocolCommand.MSET)
				.general((cmd)->cmd.mset(temp), OkStatusConverter.INSTANCE)
				.pipeline((cmd)->cmd.mset(temp), OkStatusConverter.INSTANCE)
				.transaction((cmd)->cmd.mset(temp), OkStatusConverter.INSTANCE)
				.run(args);
	}

	@Override
	public Status mSetNx(final Map<String, String> values){
		final CommandArguments args = CommandArguments.create("values", values);
		final Map<byte[], byte[]> temp = new LinkedHashMap<>(values.size());

		values.forEach((key, value)->{
			temp.put(SafeEncoder.encode(key), SafeEncoder.encode(value));
		});

		return new LettuceCommand<Status>(client, ProtocolCommand.MSETNX)
				.general((cmd)->cmd.msetnx(temp), Converters.BOOLEAN_STATUS_CONVERTER)
				.pipeline((cmd)->cmd.msetnx(temp), Converters.BOOLEAN_STATUS_CONVERTER)
				.transaction((cmd)->cmd.msetnx(temp), Converters.BOOLEAN_STATUS_CONVERTER)
				.run(args);
	}

	@Override
	public Status pSetEx(final String key, final String value, final int lifetime){
		return pSetEx(SafeEncoder.encode(key), SafeEncoder.encode(value), lifetime);
	}

	@Override
	public Status pSetEx(final byte[] key, final byte[] value, final int lifetime){
		final CommandArguments args = CommandArguments.create("key", key).put("value", value).put("lifetime", lifetime);
		return new LettuceCommand<Status>(client, ProtocolCommand.PSETEX)
				.general((cmd)->cmd.psetex(key, lifetime, value), OkStatusConverter.INSTANCE)
				.pipeline((cmd)->cmd.psetex(key, lifetime, value), OkStatusConverter.INSTANCE)
				.transaction((cmd)->cmd.psetex(key, lifetime, value), OkStatusConverter.INSTANCE)
				.run(args);
	}

	@Override
	public Status set(final String key, final String value){
		return set(SafeEncoder.encode(key), SafeEncoder.encode(value));
	}

	@Override
	public Status set(final byte[] key, final byte[] value){
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);
		return new LettuceCommand<Status>(client, ProtocolCommand.SET)
				.general((cmd)->cmd.set(key, value), OkStatusConverter.INSTANCE)
				.pipeline((cmd)->cmd.set(key, value), OkStatusConverter.INSTANCE)
				.transaction((cmd)->cmd.set(key, value), OkStatusConverter.INSTANCE)
				.run(args);
	}

	@Override
	public Status set(final String key, final String value, final SetArgument setArgument){
		return set(SafeEncoder.encode(key), SafeEncoder.encode(value), setArgument);
	}

	@Override
	public Status set(final byte[] key, final byte[] value, final SetArgument setArgument){
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);
		final SetArgs params = SetArgumentConverter.INSTANCE.convert(setArgument);
		return new LettuceCommand<Status>(client, ProtocolCommand.SET)
				.general((cmd)->cmd.set(key, value, params), OkStatusConverter.INSTANCE)
				.pipeline((cmd)->cmd.set(key, value, params), OkStatusConverter.INSTANCE)
				.transaction((cmd)->cmd.set(key, value, params), OkStatusConverter.INSTANCE)
				.run(args);
	}

	@Override
	public Status setEx(final String key, final String value, final int lifetime){
		return setEx(SafeEncoder.encode(key), SafeEncoder.encode(value), lifetime);
	}

	@Override
	public Status setEx(final byte[] key, final byte[] value, final int lifetime){
		final CommandArguments args = CommandArguments.create("key", key).put("value", value).put("lifetime", lifetime);
		return new LettuceCommand<Status>(client, ProtocolCommand.SETEX)
				.general((cmd)->cmd.setex(key, lifetime, value), OkStatusConverter.INSTANCE)
				.pipeline((cmd)->cmd.setex(key, lifetime, value), OkStatusConverter.INSTANCE)
				.transaction((cmd)->cmd.setex(key, lifetime, value), OkStatusConverter.INSTANCE)
				.run(args);
	}

	@Override
	public Status setNx(final String key, final String value){
		return setNx(SafeEncoder.encode(key), SafeEncoder.encode(value));
	}

	@Override
	public Status setNx(final byte[] key, final byte[] value){
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);
		return new LettuceCommand<Status>(client, ProtocolCommand.SETEX)
				.general((cmd)->cmd.setnx(key, value), Converters.BOOLEAN_STATUS_CONVERTER)
				.pipeline((cmd)->cmd.setnx(key, value), Converters.BOOLEAN_STATUS_CONVERTER)
				.transaction((cmd)->cmd.setnx(key, value), Converters.BOOLEAN_STATUS_CONVERTER)
				.run(args);
	}

	@Override
	public Long setRange(final String key, final long offset, final String value){
		return setRange(SafeEncoder.encode(key), offset, SafeEncoder.encode(value));
	}

	@Override
	public Long setRange(final byte[] key, final long offset, final byte[] value){
		final CommandArguments args = CommandArguments.create("key", key).put("offset", offset).put("value", value);
		return new LettuceCommand<Long>(client, ProtocolCommand.SETEX)
				.general((cmd)->cmd.setrange(key, offset, value))
				.pipeline((cmd)->cmd.setrange(key, offset, value))
				.transaction((cmd)->cmd.setrange(key, offset, value))
				.run(args);
	}

	@Override
	public String getRange(final String key, final long start, final long end){
		return SafeEncoder.encode(getRange(SafeEncoder.encode(key), start, end));
	}

	@Override
	public byte[] getRange(final byte[] key, final long start, final long end){
		final CommandArguments args = CommandArguments.create("key", key).put("start", start).put("end", end);
		return new LettuceCommand<byte[]>(client, ProtocolCommand.GETRANGE)
				.general((cmd)->cmd.getrange(key, start, end))
				.pipeline((cmd)->cmd.getrange(key, start, end))
				.transaction((cmd)->cmd.getrange(key, start, end))
				.run(args);
	}

	@Override
	public Long strlen(final String key){
		return strlen(SafeEncoder.encode(key));
	}

	@Override
	public Long strlen(final byte[] key){
		final CommandArguments args = CommandArguments.create("key", key);
		return new LettuceCommand<Long>(client, ProtocolCommand.STRLEN)
				.general((cmd)->cmd.strlen(key))
				.pipeline((cmd)->cmd.strlen(key))
				.transaction((cmd)->cmd.strlen(key))
				.run(args);
	}

	@Override
	public String substr(final String key, final long start, final long end){
		return SafeEncoder.encode(substr(SafeEncoder.encode(key), start, end));
	}

	@Override
	public byte[] substr(final byte[] key, final long start, final long end){
		final CommandArguments args = CommandArguments.create("key", key).put("start", start).put("end", end);
		return new LettuceCommand<byte[]>(client, ProtocolCommand.SUBSTR)
				.run(args);
	}

}
