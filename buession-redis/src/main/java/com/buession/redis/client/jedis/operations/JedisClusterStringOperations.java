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
import com.buession.redis.client.connection.jedis.JedisClusterConnection;
import com.buession.redis.client.jedis.JedisClusterClient;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.internal.convert.jedis.JedisConverters;
import redis.clients.jedis.params.GetExParams;
import redis.clients.jedis.params.SetParams;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Jedis 集群模式字符串命令操作
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public final class JedisClusterStringOperations extends AbstractStringOperations<JedisClusterConnection> {

	public JedisClusterStringOperations(final JedisClusterClient client){
		super(client);
	}

	@Override
	public long append(final String key, final String value){
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);
		final JedisClusterCommand<Long> command = JedisClusterCommand.<Long>create(ProtocolCommand.APPEND)
				.general((cmd)->cmd.append(key, value)).pipeline((cmd)->cmd.append(key, value))
				.transaction((cmd)->cmd.append(key, value));
		return execute(command, args);
	}

	@Override
	public long append(final byte[] key, final byte[] value){
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);
		final JedisClusterCommand<Long> command = JedisClusterCommand.<Long>create(ProtocolCommand.APPEND)
				.general((cmd)->cmd.append(key, value)).pipeline((cmd)->cmd.append(key, value))
				.transaction((cmd)->cmd.append(key, value));
		return execute(command, args);
	}

	@Override
	public long incr(final String key){
		final CommandArguments args = CommandArguments.create("key", key);
		final JedisClusterCommand<Long> command = JedisClusterCommand.<Long>create(ProtocolCommand.INCR)
				.general((cmd)->cmd.incr(key)).pipeline((cmd)->cmd.incr(key)).transaction((cmd)->cmd.incr(key));
		return execute(command, args);
	}

	@Override
	public long incr(final byte[] key){
		final CommandArguments args = CommandArguments.create("key", key);
		final JedisClusterCommand<Long> command = JedisClusterCommand.<Long>create(ProtocolCommand.INCR)
				.general((cmd)->cmd.incr(key)).pipeline((cmd)->cmd.incr(key)).transaction((cmd)->cmd.incr(key));
		return execute(command, args);
	}

	@Override
	public long incrBy(final String key, final long value){
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);
		final JedisClusterCommand<Long> command = JedisClusterCommand.<Long>create(ProtocolCommand.INCRBY)
				.general((cmd)->cmd.incrBy(key, value)).pipeline((cmd)->cmd.incrBy(key, value))
				.transaction((cmd)->cmd.incrBy(key, value));
		return execute(command, args);
	}

	@Override
	public long incrBy(final byte[] key, final long value){
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);
		final JedisClusterCommand<Long> command = JedisClusterCommand.<Long>create(ProtocolCommand.INCRBY)
				.general((cmd)->cmd.incrBy(key, value)).pipeline((cmd)->cmd.incrBy(key, value))
				.transaction((cmd)->cmd.incrBy(key, value));
		return execute(command, args);
	}

	@Override
	public double incrByFloat(final String key, final double value){
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);
		final JedisClusterCommand<Double> command = JedisClusterCommand.<Double>create(ProtocolCommand.INCRBYFLOAT)
				.general((cmd)->cmd.incrByFloat(key, value)).pipeline((cmd)->cmd.incrByFloat(key, value))
				.transaction((cmd)->cmd.incrByFloat(key, value));
		return execute(command, args);
	}

	@Override
	public double incrByFloat(final byte[] key, final double value){
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);
		final JedisClusterCommand<Double> command = JedisClusterCommand.<Double>create(ProtocolCommand.INCRBYFLOAT)
				.general((cmd)->cmd.incrByFloat(key, value)).pipeline((cmd)->cmd.incrByFloat(key, value))
				.transaction((cmd)->cmd.incrByFloat(key, value));
		return execute(command, args);
	}

	@Override
	public long decr(final String key){
		final CommandArguments args = CommandArguments.create("key", key);
		final JedisClusterCommand<Long> command = JedisClusterCommand.<Long>create(ProtocolCommand.DECR)
				.general((cmd)->cmd.decr(key)).pipeline((cmd)->cmd.decr(key))
				.transaction((cmd)->cmd.decr(key));
		return execute(command, args);
	}

	@Override
	public long decr(final byte[] key){
		final CommandArguments args = CommandArguments.create("key", key);
		final JedisClusterCommand<Long> command = JedisClusterCommand.<Long>create(ProtocolCommand.DECR)
				.general((cmd)->cmd.decr(key)).pipeline((cmd)->cmd.decr(key))
				.transaction((cmd)->cmd.decr(key));
		return execute(command, args);
	}

	@Override
	public long decrBy(final String key, final long value){
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);
		final JedisClusterCommand<Long> command = JedisClusterCommand.<Long>create(ProtocolCommand.DECRBY)
				.general((cmd)->cmd.decrBy(key, value)).pipeline((cmd)->cmd.decrBy(key, value))
				.transaction((cmd)->cmd.decrBy(key, value));
		return execute(command, args);
	}

	@Override
	public long decrBy(final byte[] key, final long value){
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);
		final JedisClusterCommand<Long> command = JedisClusterCommand.<Long>create(ProtocolCommand.DECRBY)
				.general((cmd)->cmd.decrBy(key, value)).pipeline((cmd)->cmd.decrBy(key, value))
				.transaction((cmd)->cmd.decrBy(key, value));
		return execute(command, args);
	}

	@Override
	public String get(final String key){
		final CommandArguments args = CommandArguments.create("key", key);
		final JedisClusterCommand<String> command = JedisClusterCommand.<String>create(ProtocolCommand.GET)
				.general((cmd)->cmd.get(key)).pipeline((cmd)->cmd.get(key))
				.transaction((cmd)->cmd.get(key));
		return execute(command, args);
	}

	@Override
	public byte[] get(final byte[] key){
		final CommandArguments args = CommandArguments.create("key", key);
		final JedisClusterCommand<byte[]> command = JedisClusterCommand.<byte[]>create(ProtocolCommand.GET)
				.general((cmd)->cmd.get(key)).pipeline((cmd)->cmd.get(key))
				.transaction((cmd)->cmd.get(key));
		return execute(command, args);
	}

	@Override
	public String getEx(final String key, final GetExArgument getExArgument){
		final CommandArguments args = CommandArguments.create("key", key).put("getExArgument", getExArgument);
		final GetExParams getExParams = JedisConverters.GET_EX_ARGUMENT_CONVERTER.convert(getExArgument);
		final JedisClusterCommand<String> command = JedisClusterCommand.<String>create(ProtocolCommand.GETEX)
				.general((cmd)->cmd.getEx(key, getExParams)).pipeline((cmd)->cmd.getEx(key, getExParams))
				.transaction((cmd)->cmd.getEx(key, getExParams));
		return execute(command, args);
	}

	@Override
	public byte[] getEx(final byte[] key, final GetExArgument getExArgument){
		final CommandArguments args = CommandArguments.create("key", key).put("getExArgument", getExArgument);
		final GetExParams getExParams = JedisConverters.GET_EX_ARGUMENT_CONVERTER.convert(getExArgument);
		final JedisClusterCommand<byte[]> command = JedisClusterCommand.<byte[]>create(ProtocolCommand.GETEX)
				.general((cmd)->cmd.getEx(key, getExParams)).pipeline((cmd)->cmd.getEx(key, getExParams))
				.transaction((cmd)->cmd.getEx(key, getExParams));
		return execute(command, args);
	}

	@Override
	public String getSet(final String key, final String value){
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);
		final JedisClusterCommand<String> command = JedisClusterCommand.<String>create(ProtocolCommand.GETSET)
				.general((cmd)->cmd.getSet(key, value)).pipeline((cmd)->cmd.getSet(key, value))
				.transaction((cmd)->cmd.getSet(key, value));
		return execute(command, args);
	}

	@Override
	public byte[] getSet(final byte[] key, final byte[] value){
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);
		final JedisClusterCommand<byte[]> command = JedisClusterCommand.<byte[]>create(ProtocolCommand.GETSET)
				.general((cmd)->cmd.getSet(key, value)).pipeline((cmd)->cmd.getSet(key, value))
				.transaction((cmd)->cmd.getSet(key, value));
		return execute(command, args);
	}

	@Override
	public String getDel(final String key){
		final CommandArguments args = CommandArguments.create("key", key);
		final JedisClusterCommand<String> command = JedisClusterCommand.<String>create(ProtocolCommand.GETDEL)
				.general((cmd)->cmd.getDel(key)).pipeline((cmd)->cmd.getDel(key)).transaction((cmd)->cmd.getDel(key));
		return execute(command, args);
	}

	@Override
	public byte[] getDel(final byte[] key){
		final CommandArguments args = CommandArguments.create("key", key);
		final JedisClusterCommand<byte[]> command = JedisClusterCommand.<byte[]>create(ProtocolCommand.GETDEL)
				.general((cmd)->cmd.getDel(key)).pipeline((cmd)->cmd.getDel(key)).transaction((cmd)->cmd.getDel(key));
		return execute(command, args);
	}

	@Override
	public List<String> mGet(final String... keys){
		final CommandArguments args = CommandArguments.create("keys", keys);
		final JedisClusterCommand<List<String>> command = JedisClusterCommand.<List<String>>create(ProtocolCommand.MGET)
				.general((cmd)->cmd.mget(keys)).pipeline((cmd)->cmd.mget(keys)).transaction((cmd)->cmd.mget(keys));
		return execute(command, args);
	}

	@Override
	public List<byte[]> mGet(final byte[]... keys){
		final CommandArguments args = CommandArguments.create("keys", keys);
		final JedisClusterCommand<List<byte[]>> command = JedisClusterCommand.<List<byte[]>>create(ProtocolCommand.MGET)
				.general((cmd)->cmd.mget(keys)).pipeline((cmd)->cmd.mget(keys)).transaction((cmd)->cmd.mget(keys));
		return execute(command, args);
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
		final JedisClusterCommand<Status> command = JedisClusterCommand.<Status>create(ProtocolCommand.MSET)
				.general((cmd)->cmd.mset(keysValues), JedisConverters.OK_STATUS_CONVERTER)
				.pipeline((cmd)->cmd.mset(keysValues), JedisConverters.OK_STATUS_CONVERTER)
				.transaction((cmd)->cmd.mset(keysValues), JedisConverters.OK_STATUS_CONVERTER);
		return execute(command, args);
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
		final JedisClusterCommand<Status> command = JedisClusterCommand.<Status>create(ProtocolCommand.MSETNX)
				.general((cmd)->cmd.msetnx(keysValues), JedisConverters.ONE_STATUS_CONVERTER)
				.pipeline((cmd)->cmd.msetnx(keysValues), JedisConverters.ONE_STATUS_CONVERTER)
				.transaction((cmd)->cmd.msetnx(keysValues), JedisConverters.ONE_STATUS_CONVERTER);
		return execute(command, args);
	}

	@Override
	public Status pSetEx(final String key, final String value, final int lifetime){
		final CommandArguments args = CommandArguments.create("key", key).put("value", value).put("lifetime", lifetime);
		final JedisClusterCommand<Status> command = JedisClusterCommand.<Status>create(ProtocolCommand.PSETEX)
				.general((cmd)->cmd.psetex(key, lifetime, value), JedisConverters.OK_STATUS_CONVERTER)
				.pipeline((cmd)->cmd.psetex(key, lifetime, value), JedisConverters.OK_STATUS_CONVERTER)
				.transaction((cmd)->cmd.psetex(key, lifetime, value), JedisConverters.OK_STATUS_CONVERTER);
		return execute(command, args);
	}

	@Override
	public Status pSetEx(final byte[] key, final byte[] value, final int lifetime){
		final CommandArguments args = CommandArguments.create("key", key).put("value", value).put("lifetime", lifetime);
		final JedisClusterCommand<Status> command = JedisClusterCommand.<Status>create(ProtocolCommand.PSETEX)
				.general((cmd)->cmd.psetex(key, lifetime, value), JedisConverters.OK_STATUS_CONVERTER)
				.pipeline((cmd)->cmd.psetex(key, lifetime, value), JedisConverters.OK_STATUS_CONVERTER)
				.transaction((cmd)->cmd.psetex(key, lifetime, value), JedisConverters.OK_STATUS_CONVERTER);
		return execute(command, args);
	}

	@Override
	public Status set(final String key, final String value){
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);
		final JedisClusterCommand<Status> command = JedisClusterCommand.<Status>create(ProtocolCommand.SET)
				.general((cmd)->cmd.set(key, value), JedisConverters.OK_STATUS_CONVERTER)
				.pipeline((cmd)->cmd.set(key, value), JedisConverters.OK_STATUS_CONVERTER)
				.transaction((cmd)->cmd.set(key, value), JedisConverters.OK_STATUS_CONVERTER);
		return execute(command, args);
	}

	@Override
	public Status set(final byte[] key, final byte[] value){
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);
		final JedisClusterCommand<Status> command = JedisClusterCommand.<Status>create(ProtocolCommand.SET)
				.general((cmd)->cmd.set(key, value), JedisConverters.OK_STATUS_CONVERTER)
				.pipeline((cmd)->cmd.set(key, value), JedisConverters.OK_STATUS_CONVERTER)
				.transaction((cmd)->cmd.set(key, value), JedisConverters.OK_STATUS_CONVERTER);
		return execute(command, args);
	}

	@Override
	public Status set(final String key, final String value, final SetArgument setArgument){
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);
		final SetParams setParams = JedisConverters.SET_ARGUMENT_CONVERTER.convert(setArgument);
		final JedisClusterCommand<Status> command = JedisClusterCommand.<Status>create(ProtocolCommand.SET)
				.general((cmd)->cmd.set(key, value, setParams), JedisConverters.OK_STATUS_CONVERTER)
				.pipeline((cmd)->cmd.set(key, value, setParams), JedisConverters.OK_STATUS_CONVERTER)
				.transaction((cmd)->cmd.set(key, value, setParams), JedisConverters.OK_STATUS_CONVERTER);
		return execute(command, args);
	}

	@Override
	public Status set(final byte[] key, final byte[] value, final SetArgument setArgument){
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);
		final SetParams setParams = JedisConverters.SET_ARGUMENT_CONVERTER.convert(setArgument);
		final JedisClusterCommand<Status> command = JedisClusterCommand.<Status>create(ProtocolCommand.SET)
				.general((cmd)->cmd.set(key, value, setParams), JedisConverters.OK_STATUS_CONVERTER)
				.pipeline((cmd)->cmd.set(key, value, setParams), JedisConverters.OK_STATUS_CONVERTER)
				.transaction((cmd)->cmd.set(key, value, setParams), JedisConverters.OK_STATUS_CONVERTER);
		return execute(command, args);
	}

	@Override
	public Status setEx(final String key, final String value, final int lifetime){
		final CommandArguments args = CommandArguments.create("key", key).put("value", value).put("lifetime", lifetime);
		final JedisClusterCommand<Status> command = JedisClusterCommand.<Status>create(ProtocolCommand.SETEX)
				.general((cmd)->cmd.setex(key, lifetime, value), JedisConverters.OK_STATUS_CONVERTER)
				.pipeline((cmd)->cmd.setex(key, lifetime, value), JedisConverters.OK_STATUS_CONVERTER)
				.transaction((cmd)->cmd.setex(key, lifetime, value), JedisConverters.OK_STATUS_CONVERTER);
		return execute(command, args);
	}

	@Override
	public Status setEx(final byte[] key, final byte[] value, final int lifetime){
		final CommandArguments args = CommandArguments.create("key", key).put("value", value).put("lifetime", lifetime);
		final JedisClusterCommand<Status> command = JedisClusterCommand.<Status>create(ProtocolCommand.SETEX)
				.general((cmd)->cmd.setex(key, lifetime, value), JedisConverters.OK_STATUS_CONVERTER)
				.pipeline((cmd)->cmd.setex(key, lifetime, value), JedisConverters.OK_STATUS_CONVERTER)
				.transaction((cmd)->cmd.setex(key, lifetime, value), JedisConverters.OK_STATUS_CONVERTER);
		return execute(command, args);
	}

	@Override
	public Status setNx(final String key, final String value){
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);
		final JedisClusterCommand<Status> command = JedisClusterCommand.<Status>create(ProtocolCommand.SETEX)
				.general((cmd)->cmd.setnx(key, value), JedisConverters.ONE_STATUS_CONVERTER)
				.pipeline((cmd)->cmd.setnx(key, value), JedisConverters.ONE_STATUS_CONVERTER)
				.transaction((cmd)->cmd.setnx(key, value), JedisConverters.ONE_STATUS_CONVERTER);
		return execute(command, args);
	}

	@Override
	public Status setNx(final byte[] key, final byte[] value){
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);
		final JedisClusterCommand<Status> command = JedisClusterCommand.<Status>create(ProtocolCommand.SETEX)
				.general((cmd)->cmd.setnx(key, value), JedisConverters.ONE_STATUS_CONVERTER)
				.pipeline((cmd)->cmd.setnx(key, value), JedisConverters.ONE_STATUS_CONVERTER)
				.transaction((cmd)->cmd.setnx(key, value), JedisConverters.ONE_STATUS_CONVERTER);
		return execute(command, args);
	}

	@Override
	public long setRange(final String key, final long offset, final String value){
		final CommandArguments args = CommandArguments.create("key", key).put("offset", offset).put("value", value);
		final JedisClusterCommand<Long> command = JedisClusterCommand.<Long>create(ProtocolCommand.SETEX)
				.general((cmd)->cmd.setrange(key, offset, value)).pipeline((cmd)->cmd.setrange(key, offset, value));
		return execute(command, args);
	}

	@Override
	public long setRange(final byte[] key, final long offset, final byte[] value){
		final CommandArguments args = CommandArguments.create("key", key).put("offset", offset).put("value", value);
		final JedisClusterCommand<Long> command = JedisClusterCommand.<Long>create(ProtocolCommand.SETEX)
				.general((cmd)->cmd.setrange(key, offset, value)).pipeline((cmd)->cmd.setrange(key, offset, value));
		return execute(command, args);
	}

	@Override
	public String getRange(final String key, final long start, final long end){
		final CommandArguments args = CommandArguments.create("key", key).put("start", start).put("end", end);
		final JedisClusterCommand<String> command = JedisClusterCommand.<String>create(ProtocolCommand.GETRANGE)
				.general((cmd)->cmd.getrange(key, start, end)).pipeline((cmd)->cmd.getrange(key, start, end))
				.transaction((cmd)->cmd.getrange(key, start, end));
		return execute(command, args);
	}

	@Override
	public byte[] getRange(final byte[] key, final long start, final long end){
		final CommandArguments args = CommandArguments.create("key", key).put("start", start).put("end", end);
		final JedisClusterCommand<byte[]> command = JedisClusterCommand.<byte[]>create(ProtocolCommand.GETRANGE)
				.general((cmd)->cmd.getrange(key, start, end)).pipeline((cmd)->cmd.getrange(key, start, end))
				.transaction((cmd)->cmd.getrange(key, start, end));
		return execute(command, args);
	}

	@Override
	public long strlen(final String key){
		final CommandArguments args = CommandArguments.create("key", key);
		final JedisClusterCommand<Long> command = JedisClusterCommand.<Long>create(ProtocolCommand.STRLEN)
				.general((cmd)->cmd.strlen(key)).pipeline((cmd)->cmd.strlen(key)).transaction((cmd)->cmd.strlen(key));
		return execute(command, args);
	}

	@Override
	public long strlen(final byte[] key){
		final CommandArguments args = CommandArguments.create("key", key);
		final JedisClusterCommand<Long> command = JedisClusterCommand.<Long>create(ProtocolCommand.STRLEN)
				.general((cmd)->cmd.strlen(key)).pipeline((cmd)->cmd.strlen(key)).transaction((cmd)->cmd.strlen(key));
		return execute(command, args);
	}

	@Override
	public String substr(final String key, final long start, final long end){
		final CommandArguments args = CommandArguments.create("key", key).put("start", start).put("end", end);
		final JedisClusterCommand<String> command = JedisClusterCommand.<String>create(ProtocolCommand.SUBSTR)
				.general((cmd)->cmd.substr(key, (int) start, (int) end))
				.pipeline((cmd)->cmd.substr(key, (int) start, (int) end))
				.transaction((cmd)->cmd.substr(key, (int) start, (int) end));
		return execute(command, args);
	}

	@Override
	public byte[] substr(final byte[] key, final long start, final long end){
		final CommandArguments args = CommandArguments.create("key", key).put("start", start).put("end", end);
		final JedisClusterCommand<byte[]> command = JedisClusterCommand.<byte[]>create(ProtocolCommand.SUBSTR)
				.general((cmd)->cmd.substr(key, (int) start, (int) end))
				.pipeline((cmd)->cmd.substr(key, (int) start, (int) end))
				.transaction((cmd)->cmd.substr(key, (int) start, (int) end));
		return execute(command, args);
	}

}
