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

import com.buession.lang.Status;
import com.buession.redis.client.jedis.JedisSentinelClient;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.internal.convert.Converters;
import com.buession.redis.core.internal.convert.jedis.params.GetExArgumentConverter;
import com.buession.redis.core.internal.convert.jedis.params.SetArgumentConverter;
import com.buession.redis.core.internal.convert.response.OkStatusConverter;
import redis.clients.jedis.params.GetExParams;
import redis.clients.jedis.params.SetParams;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Jedis 哨兵模式字符串命令操作
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public final class JedisSentinelStringOperations extends AbstractStringOperations<JedisSentinelClient> {

	public JedisSentinelStringOperations(final JedisSentinelClient client){
		super(client);
	}

	@Override
	public long append(final String key, final String value){
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);
		return new JedisSentinelCommand<Long>(client, ProtocolCommand.APPEND)
				.general((cmd)->cmd.append(key, value)).pipeline((cmd)->cmd.append(key, value))
				.transaction((cmd)->cmd.append(key, value))
				.run(args);
	}

	@Override
	public long append(final byte[] key, final byte[] value){
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);
		return new JedisSentinelCommand<Long>(client, ProtocolCommand.APPEND)
				.general((cmd)->cmd.append(key, value)).pipeline((cmd)->cmd.append(key, value))
				.transaction((cmd)->cmd.append(key, value))
				.run(args);
	}

	@Override
	public long incr(final String key){
		final CommandArguments args = CommandArguments.create("key", key);
		return new JedisSentinelCommand<Long>(client, ProtocolCommand.INCR)
				.general((cmd)->cmd.incr(key))
				.pipeline((cmd)->cmd.incr(key))
				.transaction((cmd)->cmd.incr(key))
				.run(args);
	}

	@Override
	public long incr(final byte[] key){
		final CommandArguments args = CommandArguments.create("key", key);
		return new JedisSentinelCommand<Long>(client, ProtocolCommand.INCR)
				.general((cmd)->cmd.incr(key))
				.pipeline((cmd)->cmd.incr(key))
				.transaction((cmd)->cmd.incr(key))
				.run(args);
	}

	@Override
	public long incrBy(final String key, final long value){
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);
		return new JedisSentinelCommand<Long>(client, ProtocolCommand.INCRBY)
				.general((cmd)->cmd.incrBy(key, value))
				.pipeline((cmd)->cmd.incrBy(key, value))
				.transaction((cmd)->cmd.incrBy(key, value))
				.run(args);
	}

	@Override
	public long incrBy(final byte[] key, final long value){
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);
		return new JedisSentinelCommand<Long>(client, ProtocolCommand.INCRBY)
				.general((cmd)->cmd.incrBy(key, value))
				.pipeline((cmd)->cmd.incrBy(key, value))
				.transaction((cmd)->cmd.incrBy(key, value))
				.run(args);
	}

	@Override
	public double incrByFloat(final String key, final double value){
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);
		return new JedisSentinelCommand<Double>(client, ProtocolCommand.INCRBYFLOAT)
				.general((cmd)->cmd.incrByFloat(key, value))
				.pipeline((cmd)->cmd.incrByFloat(key, value))
				.transaction((cmd)->cmd.incrByFloat(key, value))
				.run(args);
	}

	@Override
	public double incrByFloat(final byte[] key, final double value){
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);
		return new JedisSentinelCommand<Double>(client, ProtocolCommand.INCRBYFLOAT)
				.general((cmd)->cmd.incrByFloat(key, value))
				.pipeline((cmd)->cmd.incrByFloat(key, value))
				.transaction((cmd)->cmd.incrByFloat(key, value))
				.run(args);
	}

	@Override
	public long decr(final String key){
		final CommandArguments args = CommandArguments.create("key", key);
		return new JedisSentinelCommand<Long>(client, ProtocolCommand.DECR)
				.general((cmd)->cmd.decr(key))
				.pipeline((cmd)->cmd.decr(key))
				.transaction((cmd)->cmd.decr(key))
				.run(args);
	}

	@Override
	public long decr(final byte[] key){
		final CommandArguments args = CommandArguments.create("key", key);
		return new JedisSentinelCommand<Long>(client, ProtocolCommand.DECR)
				.general((cmd)->cmd.decr(key))
				.pipeline((cmd)->cmd.decr(key))
				.transaction((cmd)->cmd.decr(key))
				.run(args);
	}

	@Override
	public long decrBy(final String key, final long value){
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);
		return new JedisSentinelCommand<Long>(client, ProtocolCommand.DECRBY)
				.general((cmd)->cmd.decrBy(key, value))
				.pipeline((cmd)->cmd.decrBy(key, value))
				.transaction((cmd)->cmd.decrBy(key, value))
				.run(args);
	}

	@Override
	public long decrBy(final byte[] key, final long value){
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);
		return new JedisSentinelCommand<Long>(client, ProtocolCommand.DECRBY)
				.general((cmd)->cmd.decrBy(key, value))
				.pipeline((cmd)->cmd.decrBy(key, value))
				.transaction((cmd)->cmd.decrBy(key, value))
				.run(args);
	}

	@Override
	public String get(final String key){
		final CommandArguments args = CommandArguments.create("key", key);
		return new JedisSentinelCommand<String>(client, ProtocolCommand.GET)
				.general((cmd)->cmd.get(key))
				.pipeline((cmd)->cmd.get(key))
				.transaction((cmd)->cmd.get(key))
				.run(args);
	}

	@Override
	public byte[] get(final byte[] key){
		final CommandArguments args = CommandArguments.create("key", key);
		return new JedisSentinelCommand<byte[]>(client, ProtocolCommand.GET)
				.general((cmd)->cmd.get(key))
				.pipeline((cmd)->cmd.get(key))
				.transaction((cmd)->cmd.get(key))
				.run(args);
	}

	@Override
	public String getEx(final String key, final GetExArgument getExArgument){
		final CommandArguments args = CommandArguments.create("key", key).put("getExArgument", getExArgument);
		final GetExParams params = GetExArgumentConverter.INSTANCE.convert(getExArgument);
		return new JedisSentinelCommand<String>(client, ProtocolCommand.GETEX)
				.general((cmd)->cmd.getEx(key, params))
				.pipeline((cmd)->cmd.getEx(key, params))
				.transaction((cmd)->cmd.getEx(key, params))
				.run(args);
	}

	@Override
	public byte[] getEx(final byte[] key, final GetExArgument getExArgument){
		final CommandArguments args = CommandArguments.create("key", key).put("getExArgument", getExArgument);
		final GetExParams params = GetExArgumentConverter.INSTANCE.convert(getExArgument);
		return new JedisSentinelCommand<byte[]>(client, ProtocolCommand.GETEX)
				.general((cmd)->cmd.getEx(key, params))
				.pipeline((cmd)->cmd.getEx(key, params))
				.transaction((cmd)->cmd.getEx(key, params))
				.run(args);
	}

	@Override
	public String getSet(final String key, final String value){
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);
		return new JedisSentinelCommand<String>(client, ProtocolCommand.GETSET)
				.general((cmd)->cmd.getSet(key, value))
				.pipeline((cmd)->cmd.getSet(key, value))
				.transaction((cmd)->cmd.getSet(key, value))
				.run(args);
	}

	@Override
	public byte[] getSet(final byte[] key, final byte[] value){
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);
		return new JedisSentinelCommand<byte[]>(client, ProtocolCommand.GETSET)
				.general((cmd)->cmd.getSet(key, value))
				.pipeline((cmd)->cmd.getSet(key, value))
				.transaction((cmd)->cmd.getSet(key, value))
				.run(args);
	}

	@Override
	public String getDel(final String key){
		final CommandArguments args = CommandArguments.create("key", key);
		return new JedisSentinelCommand<String>(client, ProtocolCommand.GETDEL)
				.general((cmd)->cmd.getDel(key))
				.pipeline((cmd)->cmd.getDel(key))
				.transaction((cmd)->cmd.getDel(key))
				.run(args);
	}

	@Override
	public byte[] getDel(final byte[] key){
		final CommandArguments args = CommandArguments.create("key", key);
		return new JedisSentinelCommand<byte[]>(client, ProtocolCommand.GETDEL)
				.general((cmd)->cmd.getDel(key))
				.pipeline((cmd)->cmd.getDel(key))
				.transaction((cmd)->cmd.getDel(key))
				.run(args);
	}

	@Override
	public List<String> mGet(final String... keys){
		final CommandArguments args = CommandArguments.create("keys", keys);
		return new JedisSentinelCommand<List<String>>(client, ProtocolCommand.MGET)
				.general((cmd)->cmd.mget(keys))
				.pipeline((cmd)->cmd.mget(keys))
				.transaction((cmd)->cmd.mget(keys))
				.run(args);
	}

	@Override
	public List<byte[]> mGet(final byte[]... keys){
		final CommandArguments args = CommandArguments.create("keys", keys);
		return new JedisSentinelCommand<List<byte[]>>(client, ProtocolCommand.MGET)
				.general((cmd)->cmd.mget(keys))
				.pipeline((cmd)->cmd.mget(keys))
				.transaction((cmd)->cmd.mget(keys))
				.run(args);
	}

	@Override
	public Status mSet(final Map<String, String> values){
		final CommandArguments args = CommandArguments.create("values", values);
		final List<String> temp = new ArrayList<>(values.size() * 2);

		values.forEach((key, value)->{
			temp.add(key);
			temp.add(value);
		});

		final String[] keysValues = temp.toArray(new String[0]);
		return new JedisSentinelCommand<Status>(client, ProtocolCommand.MSET)
				.general((cmd)->cmd.mset(keysValues), OkStatusConverter.INSTANCE)
				.pipeline((cmd)->cmd.mset(keysValues), OkStatusConverter.INSTANCE)
				.transaction((cmd)->cmd.mset(keysValues), OkStatusConverter.INSTANCE)
				.run(args);
	}

	@Override
	public Status mSetNx(final Map<String, String> values){
		final CommandArguments args = CommandArguments.create("values", values);
		final List<String> temp = new ArrayList<>(values.size() * 2);

		values.forEach((key, value)->{
			temp.add(key);
			temp.add(value);
		});

		final String[] keysValues = temp.toArray(new String[0]);
		return new JedisSentinelCommand<Status>(client, ProtocolCommand.MSETNX)
				.general((cmd)->cmd.msetnx(keysValues), Converters.ONE_STATUS_CONVERTER)
				.pipeline((cmd)->cmd.msetnx(keysValues), Converters.ONE_STATUS_CONVERTER)
				.transaction((cmd)->cmd.msetnx(keysValues), Converters.ONE_STATUS_CONVERTER)
				.run(args);
	}

	@Override
	public Status pSetEx(final String key, final String value, final int lifetime){
		final CommandArguments args = CommandArguments.create("key", key).put("value", value).put("lifetime", lifetime);
		return new JedisSentinelCommand<Status>(client, ProtocolCommand.PSETEX)
				.general((cmd)->cmd.psetex(key, lifetime, value), OkStatusConverter.INSTANCE)
				.pipeline((cmd)->cmd.psetex(key, lifetime, value), OkStatusConverter.INSTANCE)
				.transaction((cmd)->cmd.psetex(key, lifetime, value), OkStatusConverter.INSTANCE)
				.run(args);
	}

	@Override
	public Status pSetEx(final byte[] key, final byte[] value, final int lifetime){
		final CommandArguments args = CommandArguments.create("key", key).put("value", value).put("lifetime", lifetime);
		return new JedisSentinelCommand<Status>(client, ProtocolCommand.PSETEX)
				.general((cmd)->cmd.psetex(key, lifetime, value), OkStatusConverter.INSTANCE)
				.pipeline((cmd)->cmd.psetex(key, lifetime, value), OkStatusConverter.INSTANCE)
				.transaction((cmd)->cmd.psetex(key, lifetime, value), OkStatusConverter.INSTANCE)
				.run(args);
	}

	@Override
	public Status set(final String key, final String value){
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);
		return new JedisSentinelCommand<Status>(client, ProtocolCommand.SET)
				.general((cmd)->cmd.set(key, value), OkStatusConverter.INSTANCE)
				.pipeline((cmd)->cmd.set(key, value), OkStatusConverter.INSTANCE)
				.transaction((cmd)->cmd.set(key, value), OkStatusConverter.INSTANCE)
				.run(args);
	}

	@Override
	public Status set(final byte[] key, final byte[] value){
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);
		return new JedisSentinelCommand<Status>(client, ProtocolCommand.SET)
				.general((cmd)->cmd.set(key, value), OkStatusConverter.INSTANCE)
				.pipeline((cmd)->cmd.set(key, value), OkStatusConverter.INSTANCE)
				.transaction((cmd)->cmd.set(key, value), OkStatusConverter.INSTANCE)
				.run(args);
	}

	@Override
	public Status set(final String key, final String value, final SetArgument setArgument){
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);
		final SetParams params = SetArgumentConverter.INSTANCE.convert(setArgument);
		return new JedisSentinelCommand<Status>(client, ProtocolCommand.SET)
				.general((cmd)->cmd.set(key, value, params), OkStatusConverter.INSTANCE)
				.pipeline((cmd)->cmd.set(key, value, params), OkStatusConverter.INSTANCE)
				.transaction((cmd)->cmd.set(key, value, params), OkStatusConverter.INSTANCE)
				.run(args);
	}

	@Override
	public Status set(final byte[] key, final byte[] value, final SetArgument setArgument){
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);
		final SetParams params = SetArgumentConverter.INSTANCE.convert(setArgument);
		return new JedisSentinelCommand<Status>(client, ProtocolCommand.SET)
				.general((cmd)->cmd.set(key, value, params), OkStatusConverter.INSTANCE)
				.pipeline((cmd)->cmd.set(key, value, params), OkStatusConverter.INSTANCE)
				.transaction((cmd)->cmd.set(key, value, params), OkStatusConverter.INSTANCE)
				.run(args);
	}

	@Override
	public Status setEx(final String key, final String value, final int lifetime){
		final CommandArguments args = CommandArguments.create("key", key).put("value", value).put("lifetime", lifetime);
		return new JedisSentinelCommand<Status>(client, ProtocolCommand.SETEX)
				.general((cmd)->cmd.setex(key, lifetime, value), OkStatusConverter.INSTANCE)
				.pipeline((cmd)->cmd.setex(key, lifetime, value), OkStatusConverter.INSTANCE)
				.transaction((cmd)->cmd.setex(key, lifetime, value), OkStatusConverter.INSTANCE)
				.run(args);
	}

	@Override
	public Status setEx(final byte[] key, final byte[] value, final int lifetime){
		final CommandArguments args = CommandArguments.create("key", key).put("value", value).put("lifetime", lifetime);
		return new JedisSentinelCommand<Status>(client, ProtocolCommand.SETEX)
				.general((cmd)->cmd.setex(key, lifetime, value), OkStatusConverter.INSTANCE)
				.pipeline((cmd)->cmd.setex(key, lifetime, value), OkStatusConverter.INSTANCE)
				.transaction((cmd)->cmd.setex(key, lifetime, value), OkStatusConverter.INSTANCE)
				.run(args);
	}

	@Override
	public Status setNx(final String key, final String value){
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);
		return new JedisSentinelCommand<Status>(client, ProtocolCommand.SETEX)
				.general((cmd)->cmd.setnx(key, value), Converters.ONE_STATUS_CONVERTER)
				.pipeline((cmd)->cmd.setnx(key, value), Converters.ONE_STATUS_CONVERTER)
				.transaction((cmd)->cmd.setnx(key, value), Converters.ONE_STATUS_CONVERTER)
				.run(args);
	}

	@Override
	public Status setNx(final byte[] key, final byte[] value){
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);
		return new JedisSentinelCommand<Status>(client, ProtocolCommand.SETEX)
				.general((cmd)->cmd.setnx(key, value), Converters.ONE_STATUS_CONVERTER)
				.pipeline((cmd)->cmd.setnx(key, value), Converters.ONE_STATUS_CONVERTER)
				.transaction((cmd)->cmd.setnx(key, value), Converters.ONE_STATUS_CONVERTER)
				.run(args);
	}

	@Override
	public long setRange(final String key, final long offset, final String value){
		final CommandArguments args = CommandArguments.create("key", key).put("offset", offset).put("value", value);
		return new JedisSentinelCommand<Long>(client, ProtocolCommand.SETEX)
				.general((cmd)->cmd.setrange(key, offset, value))
				.pipeline((cmd)->cmd.setrange(key, offset, value))
				.run(args);
	}

	@Override
	public long setRange(final byte[] key, final long offset, final byte[] value){
		final CommandArguments args = CommandArguments.create("key", key).put("offset", offset).put("value", value);
		return new JedisSentinelCommand<Long>(client, ProtocolCommand.SETEX)
				.general((cmd)->cmd.setrange(key, offset, value))
				.pipeline((cmd)->cmd.setrange(key, offset, value))
				.run(args);
	}

	@Override
	public String getRange(final String key, final long start, final long end){
		final CommandArguments args = CommandArguments.create("key", key).put("start", start).put("end", end);
		return new JedisSentinelCommand<String>(client, ProtocolCommand.GETRANGE)
				.general((cmd)->cmd.getrange(key, start, end))
				.pipeline((cmd)->cmd.getrange(key, start, end))
				.transaction((cmd)->cmd.getrange(key, start, end))
				.run(args);
	}

	@Override
	public byte[] getRange(final byte[] key, final long start, final long end){
		final CommandArguments args = CommandArguments.create("key", key).put("start", start).put("end", end);
		return new JedisSentinelCommand<byte[]>(client, ProtocolCommand.GETRANGE)
				.general((cmd)->cmd.getrange(key, start, end))
				.pipeline((cmd)->cmd.getrange(key, start, end))
				.transaction((cmd)->cmd.getrange(key, start, end))
				.run(args);
	}

	@Override
	public long strlen(final String key){
		final CommandArguments args = CommandArguments.create("key", key);
		return new JedisSentinelCommand<Long>(client, ProtocolCommand.STRLEN)
				.general((cmd)->cmd.strlen(key))
				.pipeline((cmd)->cmd.strlen(key))
				.transaction((cmd)->cmd.strlen(key))
				.run(args);
	}

	@Override
	public long strlen(final byte[] key){
		final CommandArguments args = CommandArguments.create("key", key);
		return new JedisSentinelCommand<Long>(client, ProtocolCommand.STRLEN)
				.general((cmd)->cmd.strlen(key))
				.pipeline((cmd)->cmd.strlen(key))
				.transaction((cmd)->cmd.strlen(key))
				.run(args);
	}

	@Override
	public String substr(final String key, final long start, final long end){
		final CommandArguments args = CommandArguments.create("key", key).put("start", start).put("end", end);
		return new JedisSentinelCommand<String>(client, ProtocolCommand.SUBSTR)
				.general((cmd)->cmd.substr(key, (int) start, (int) end))
				.pipeline((cmd)->cmd.substr(key, (int) start, (int) end))
				.transaction((cmd)->cmd.substr(key, (int) start, (int) end))
				.run(args);
	}

	@Override
	public byte[] substr(final byte[] key, final long start, final long end){
		final CommandArguments args = CommandArguments.create("key", key).put("start", start).put("end", end);
		return new JedisSentinelCommand<byte[]>(client, ProtocolCommand.SUBSTR)
				.general((cmd)->cmd.substr(key, (int) start, (int) end))
				.pipeline((cmd)->cmd.substr(key, (int) start, (int) end))
				.transaction((cmd)->cmd.substr(key, (int) start, (int) end))
				.run(args);
	}

}
