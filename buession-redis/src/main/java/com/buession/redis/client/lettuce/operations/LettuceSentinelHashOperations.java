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

import com.buession.lang.KeyValue;
import com.buession.lang.Status;
import com.buession.redis.client.lettuce.LettuceSentinelClient;
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.ProtocolCommand;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Lettuce 哨兵模式模式哈希表命令操作
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public final class LettuceSentinelHashOperations extends AbstractHashOperations<LettuceSentinelClient> {

	public LettuceSentinelHashOperations(final LettuceSentinelClient client) {
		super(client);
	}

	@Override
	public Long hDel(final String key, final String... fields) {
		final CommandArguments args = CommandArguments.create("key", key).put("fields", (Object[]) fields);
		return notCommand(client, ProtocolCommand.HDEL, args);
	}

	@Override
	public Long hDel(final byte[] key, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create("key", key).put("fields", (Object[]) fields);
		return notCommand(client, ProtocolCommand.HDEL, args);
	}

	@Override
	public Boolean hExists(final String key, final String field) {
		final CommandArguments args = CommandArguments.create("key", key).put("field", field);
		return notCommand(client, ProtocolCommand.HEXISTS, args);
	}

	@Override
	public Boolean hExists(final byte[] key, final byte[] field) {
		final CommandArguments args = CommandArguments.create("key", key).put("field", field);
		return notCommand(client, ProtocolCommand.HEXISTS, args);
	}

	@Override
	public String hGet(final String key, final String field) {
		final CommandArguments args = CommandArguments.create("key", key).put("field", field);
		return notCommand(client, ProtocolCommand.HGET, args);
	}

	@Override
	public byte[] hGet(final byte[] key, final byte[] field) {
		final CommandArguments args = CommandArguments.create("key", key).put("field", field);
		return notCommand(client, ProtocolCommand.HGET, args);
	}

	@Override
	public Map<String, String> hGetAll(final String key) {
		final CommandArguments args = CommandArguments.create("key", key);
		return notCommand(client, ProtocolCommand.HGETALL, args);
	}

	@Override
	public Map<byte[], byte[]> hGetAll(final byte[] key) {
		final CommandArguments args = CommandArguments.create("key", key);
		return notCommand(client, ProtocolCommand.HGETALL, args);
	}

	@Override
	public Long hIncrBy(final String key, final String field, final long value) {
		final CommandArguments args = CommandArguments.create("key", key).put("field", field).put("value", value);
		return notCommand(client, ProtocolCommand.HINCRBY, args);
	}

	@Override
	public Long hIncrBy(final byte[] key, final byte[] field, final long value) {
		final CommandArguments args = CommandArguments.create("key", key).put("field", field).put("value", value);
		return notCommand(client, ProtocolCommand.HINCRBY, args);
	}

	@Override
	public Double hIncrByFloat(final String key, final String field, final double value) {
		final CommandArguments args = CommandArguments.create("key", key).put("field", field).put("value", value);
		return notCommand(client, ProtocolCommand.HINCRBYFLOAT, args);
	}

	@Override
	public Double hIncrByFloat(final byte[] key, final byte[] field, final double value) {
		final CommandArguments args = CommandArguments.create("key", key).put("field", field).put("value", value);
		return notCommand(client, ProtocolCommand.HINCRBYFLOAT, args);
	}

	@Override
	public Set<String> hKeys(final String key) {
		final CommandArguments args = CommandArguments.create("key", key);
		return notCommand(client, ProtocolCommand.HKEYS, args);
	}

	@Override
	public Set<byte[]> hKeys(final byte[] key) {
		final CommandArguments args = CommandArguments.create("key", key);
		return notCommand(client, ProtocolCommand.HKEYS, args);
	}

	@Override
	public Long hLen(final String key) {
		final CommandArguments args = CommandArguments.create("key", key);
		return notCommand(client, ProtocolCommand.HLEN, args);
	}

	@Override
	public Long hLen(final byte[] key) {
		final CommandArguments args = CommandArguments.create("key", key);
		return notCommand(client, ProtocolCommand.HLEN, args);
	}

	@Override
	public List<String> hMGet(final String key, final String... fields) {
		final CommandArguments args = CommandArguments.create("key", key).put("fields", (Object[]) fields);
		return notCommand(client, ProtocolCommand.HMGET, args);
	}

	@Override
	public List<byte[]> hMGet(final byte[] key, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create("key", key).put("fields", (Object[]) fields);
		return notCommand(client, ProtocolCommand.HMGET, args);
	}

	@Override
	public Status hMSet(final String key, final Map<String, String> data) {
		final CommandArguments args = CommandArguments.create("key", key).put("data", data);
		return notCommand(client, ProtocolCommand.HMGET, args);
	}

	@Override
	public Status hMSet(final byte[] key, final Map<byte[], byte[]> data) {
		final CommandArguments args = CommandArguments.create("key", key).put("data", data);
		return notCommand(client, ProtocolCommand.HMGET, args);
	}

	@Override
	public String hRandField(final String key) {
		final CommandArguments args = CommandArguments.create("key", key);
		return notCommand(client, ProtocolCommand.HRANDFIELD, args);
	}

	@Override
	public byte[] hRandField(final byte[] key) {
		final CommandArguments args = CommandArguments.create("key", key);
		return notCommand(client, ProtocolCommand.HRANDFIELD, args);
	}

	@Override
	public List<String> hRandField(final String key, final int count) {
		final CommandArguments args = CommandArguments.create("key", key).put("count", count);
		return notCommand(client, ProtocolCommand.HRANDFIELD, args);
	}

	@Override
	public List<byte[]> hRandField(final byte[] key, final int count) {
		final CommandArguments args = CommandArguments.create("key", key).put("count", count);
		return notCommand(client, ProtocolCommand.HRANDFIELD, args);
	}

	@Override
	public List<KeyValue<String, String>> hRandFieldWithValues(final String key, final int count) {
		final CommandArguments args = CommandArguments.create("key", key).put("count", count);
		return notCommand(client, ProtocolCommand.HRANDFIELD, args);
	}

	@Override
	public List<KeyValue<byte[], byte[]>> hRandFieldWithValues(final byte[] key, final int count) {
		final CommandArguments args = CommandArguments.create("key", key).put("count", count);
		return notCommand(client, ProtocolCommand.HRANDFIELD, args);
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final String cursor) {
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor);
		return notCommand(client, ProtocolCommand.HSCAN, args);
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor) {
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor);
		return notCommand(client, ProtocolCommand.HSCAN, args);
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final String cursor, final String pattern) {
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor).put("pattern", pattern);
		return notCommand(client, ProtocolCommand.HSCAN, args);
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor, final byte[] pattern) {
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor).put("pattern", pattern);
		return notCommand(client, ProtocolCommand.HSCAN, args);
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final String cursor, final int count) {
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor).put("count", count);
		return notCommand(client, ProtocolCommand.HSCAN, args);
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor, final int count) {
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor).put("count", count);
		return notCommand(client, ProtocolCommand.HSCAN, args);
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final String cursor, final String pattern,
												 final int count) {
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor).put("pattern", pattern);
		return notCommand(client, ProtocolCommand.HSCAN, args);
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor, final byte[] pattern,
												 final int count) {
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor).put("pattern", pattern)
				.put("count", count);
		return notCommand(client, ProtocolCommand.HSCAN, args);
	}

	@Override
	public Long hSet(final String key, final String field, final String value) {
		final CommandArguments args = CommandArguments.create("key", key).put("field", field).put("value", value);
		return notCommand(client, ProtocolCommand.HSET, args);
	}

	@Override
	public Long hSet(final byte[] key, final byte[] field, final byte[] value) {
		final CommandArguments args = CommandArguments.create("key", key).put("field", field).put("value", value);
		return notCommand(client, ProtocolCommand.HSET, args);
	}

	@Override
	public Status hSetNx(final String key, final String field, final String value) {
		final CommandArguments args = CommandArguments.create("key", key).put("field", field).put("value", value);
		return notCommand(client, ProtocolCommand.HSETNX, args);
	}

	@Override
	public Status hSetNx(final byte[] key, final byte[] field, final byte[] value) {
		final CommandArguments args = CommandArguments.create("key", key).put("field", field).put("value", value);
		return notCommand(client, ProtocolCommand.HSETNX, args);
	}

	@Override
	public Long hStrLen(final String key, final String field) {
		final CommandArguments args = CommandArguments.create("key", key).put("field", field);
		return notCommand(client, ProtocolCommand.HSTRLEN, args);
	}

	@Override
	public Long hStrLen(final byte[] key, final byte[] field) {
		final CommandArguments args = CommandArguments.create("key", key).put("field", field);
		return notCommand(client, ProtocolCommand.HSTRLEN, args);
	}

	@Override
	public List<String> hVals(final String key) {
		final CommandArguments args = CommandArguments.create("key", key);
		return notCommand(client, ProtocolCommand.HVALS, args);
	}

	@Override
	public List<byte[]> hVals(final byte[] key) {
		final CommandArguments args = CommandArguments.create("key", key);
		return notCommand(client, ProtocolCommand.HVALS, args);
	}

}
