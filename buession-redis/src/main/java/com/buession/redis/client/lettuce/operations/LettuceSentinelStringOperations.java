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
 * | Copyright @ 2013-2024 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.client.lettuce.operations;

import com.buession.lang.Status;
import com.buession.redis.client.lettuce.LettuceSentinelClient;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.ProtocolCommand;

import java.util.List;
import java.util.Map;

/**
 * Lettuce 哨兵模式字符串命令操作
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public final class LettuceSentinelStringOperations extends AbstractStringOperations<LettuceSentinelClient> {

	public LettuceSentinelStringOperations(final LettuceSentinelClient client) {
		super(client);
	}

	@Override
	public Long append(final String key, final String value) {
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);
		return notCommand(client, ProtocolCommand.APPEND, args);
	}

	@Override
	public Long append(final byte[] key, final byte[] value) {
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);
		return notCommand(client, ProtocolCommand.APPEND, args);
	}

	@Override
	public Long incr(final String key) {
		final CommandArguments args = CommandArguments.create("key", key);
		return notCommand(client, ProtocolCommand.INCR, args);
	}

	@Override
	public Long incr(final byte[] key) {
		final CommandArguments args = CommandArguments.create("key", key);
		return notCommand(client, ProtocolCommand.INCR, args);
	}

	@Override
	public Long incrBy(final String key, final long value) {
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);
		return notCommand(client, ProtocolCommand.INCRBY, args);
	}

	@Override
	public Long incrBy(final byte[] key, final long value) {
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);
		return notCommand(client, ProtocolCommand.INCRBY, args);
	}

	@Override
	public Double incrByFloat(final byte[] key, final double value) {
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);
		return notCommand(client, ProtocolCommand.INCRBYFLOAT, args);
	}

	@Override
	public Long decr(final String key) {
		final CommandArguments args = CommandArguments.create("key", key);
		return notCommand(client, ProtocolCommand.DECR, args);
	}

	@Override
	public Long decr(final byte[] key) {
		final CommandArguments args = CommandArguments.create("key", key);
		return notCommand(client, ProtocolCommand.DECR, args);
	}

	@Override
	public Long decrBy(final String key, final long value) {
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);
		return notCommand(client, ProtocolCommand.DECRBY, args);
	}

	@Override
	public Long decrBy(final byte[] key, final long value) {
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);
		return notCommand(client, ProtocolCommand.DECRBY, args);
	}

	@Override
	public String get(final String key) {
		final CommandArguments args = CommandArguments.create("key", key);
		return notCommand(client, ProtocolCommand.GET, args);
	}

	@Override
	public byte[] get(final byte[] key) {
		final CommandArguments args = CommandArguments.create("key", key);
		return notCommand(client, ProtocolCommand.GET, args);
	}

	@Override
	public String getEx(final String key, final GetExArgument getExArgument) {
		final CommandArguments args = CommandArguments.create("key", key).put("getExArgument", getExArgument);
		return notCommand(client, ProtocolCommand.GETEX, args);
	}

	@Override
	public byte[] getEx(final byte[] key, final GetExArgument getExArgument) {
		final CommandArguments args = CommandArguments.create("key", key).put("getExArgument", getExArgument);
		return notCommand(client, ProtocolCommand.GETEX, args);
	}

	@Override
	public String getSet(final String key, final String value) {
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);
		return notCommand(client, ProtocolCommand.GETSET, args);
	}

	@Override
	public byte[] getSet(final byte[] key, final byte[] value) {
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);
		return notCommand(client, ProtocolCommand.GETSET, args);
	}

	@Override
	public String getDel(final String key) {
		final CommandArguments args = CommandArguments.create("key", key);
		return notCommand(client, ProtocolCommand.GETDEL, args);
	}

	@Override
	public byte[] getDel(final byte[] key) {
		final CommandArguments args = CommandArguments.create("key", key);
		return notCommand(client, ProtocolCommand.GETDEL, args);
	}

	@Override
	public List<String> mGet(final String... keys) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys);
		return notCommand(client, ProtocolCommand.MGET, args);
	}

	@Override
	public List<byte[]> mGet(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys);
		return notCommand(client, ProtocolCommand.MGET, args);
	}

	@Override
	public Status mSet(final Map<String, String> values) {
		final CommandArguments args = CommandArguments.create("values", values);
		return notCommand(client, ProtocolCommand.MSET, args);
	}

	@Override
	public Status mSetNx(final Map<String, String> values) {
		final CommandArguments args = CommandArguments.create("values", values);
		return notCommand(client, ProtocolCommand.MSET, args);
	}

	@Override
	public Status pSetEx(final String key, final String value, final int lifetime) {
		final CommandArguments args = CommandArguments.create("key", key).put("value", value).put("lifetime", lifetime);
		return notCommand(client, ProtocolCommand.PSETEX, args);
	}

	@Override
	public Status pSetEx(final byte[] key, final byte[] value, final int lifetime) {
		final CommandArguments args = CommandArguments.create("key", key).put("value", value).put("lifetime", lifetime);
		return notCommand(client, ProtocolCommand.PSETEX, args);
	}

	@Override
	public Status set(final String key, final String value) {
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);
		return notCommand(client, ProtocolCommand.SET, args);
	}

	@Override
	public Status set(final byte[] key, final byte[] value) {
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);
		return notCommand(client, ProtocolCommand.SET, args);
	}

	@Override
	public Status set(final String key, final String value, final SetArgument setArgument) {
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);
		return notCommand(client, ProtocolCommand.SET, args);
	}

	@Override
	public Status set(final byte[] key, final byte[] value, final SetArgument setArgument) {
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);
		return notCommand(client, ProtocolCommand.SET, args);
	}

	@Override
	public Status setEx(final String key, final String value, final int lifetime) {
		final CommandArguments args = CommandArguments.create("key", key).put("value", value).put("lifetime", lifetime);
		return notCommand(client, ProtocolCommand.SETEX, args);
	}

	@Override
	public Status setEx(final byte[] key, final byte[] value, final int lifetime) {
		final CommandArguments args = CommandArguments.create("key", key).put("value", value).put("lifetime", lifetime);
		return notCommand(client, ProtocolCommand.SETEX, args);
	}

	@Override
	public Status setNx(final String key, final String value) {
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);
		return notCommand(client, ProtocolCommand.SETNX, args);
	}

	@Override
	public Status setNx(final byte[] key, final byte[] value) {
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);
		return notCommand(client, ProtocolCommand.SETNX, args);
	}

	@Override
	public Long setRange(final String key, final long offset, final String value) {
		final CommandArguments args = CommandArguments.create("key", key).put("offset", offset).put("value", value);
		return notCommand(client, ProtocolCommand.SETRANGE, args);
	}

	@Override
	public Long setRange(final byte[] key, final long offset, final byte[] value) {
		final CommandArguments args = CommandArguments.create("key", key).put("offset", offset).put("value", value);
		return notCommand(client, ProtocolCommand.SETRANGE, args);
	}

	@Override
	public String getRange(final String key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create("key", key).put("start", start).put("end", end);
		return notCommand(client, ProtocolCommand.GETRANGE, args);
	}

	@Override
	public byte[] getRange(final byte[] key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create("key", key).put("start", start).put("end", end);
		return notCommand(client, ProtocolCommand.GETRANGE, args);
	}

	@Override
	public Long strlen(final String key) {
		final CommandArguments args = CommandArguments.create("key", key);
		return notCommand(client, ProtocolCommand.STRLEN, args);
	}

	@Override
	public Long strlen(final byte[] key) {
		final CommandArguments args = CommandArguments.create("key", key);
		return notCommand(client, ProtocolCommand.STRLEN, args);
	}

	@Override
	public String substr(final String key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create("key", key).put("start", start).put("end", end);
		return notCommand(client, ProtocolCommand.SUBSTR, args);
	}

	@Override
	public byte[] substr(final byte[] key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create("key", key).put("start", start).put("end", end);
		return notCommand(client, ProtocolCommand.SUBSTR, args);
	}

}
