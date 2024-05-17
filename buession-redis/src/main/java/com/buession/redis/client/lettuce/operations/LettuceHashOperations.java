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

import com.buession.core.converter.BooleanStatusConverter;
import com.buession.lang.Status;
import com.buession.redis.client.lettuce.LettuceStandaloneClient;
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.internal.convert.lettuce.response.MapScanCursorConverter;
import com.buession.redis.core.internal.convert.response.ListConverter;
import com.buession.redis.core.internal.convert.response.ListSetConverter;
import com.buession.redis.core.internal.convert.response.MapConverter;
import com.buession.redis.core.internal.convert.response.OkStatusConverter;
import com.buession.redis.core.internal.lettuce.LettuceScanArgs;
import com.buession.redis.core.internal.lettuce.LettuceScanCursor;
import com.buession.redis.utils.SafeEncoder;
import io.lettuce.core.Value;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Lettuce 单机模式模式哈希表命令操作
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public final class LettuceHashOperations extends AbstractHashOperations<LettuceStandaloneClient> {

	public LettuceHashOperations(final LettuceStandaloneClient client) {
		super(client);
	}

	@Override
	public Long hDel(final byte[] key, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create("key", key).put("fields", (Object[]) fields);
		return new LettuceCommand<>(client, ProtocolCommand.HDEL, (cmd)->cmd.hdel(key, fields), (v)->v)
				.run(args);
	}

	@Override
	public Boolean hExists(final byte[] key, final byte[] field) {
		final CommandArguments args = CommandArguments.create("key", key).put("field", field);
		return new LettuceCommand<>(client, ProtocolCommand.HEXISTS, (cmd)->cmd.hexists(key, field), (v)->v)
				.run(args);
	}

	@Override
	public String hGet(final String key, final String field) {
		final CommandArguments args = CommandArguments.create("key", key).put("field", field);
		return new LettuceCommand<>(client, ProtocolCommand.HGET, (cmd)->cmd.hget(SafeEncoder.encode(key),
				SafeEncoder.encode(field)), SafeEncoder::encode)
				.run(args);
	}

	@Override
	public byte[] hGet(final byte[] key, final byte[] field) {
		final CommandArguments args = CommandArguments.create("key", key).put("field", field);
		return new LettuceCommand<>(client, ProtocolCommand.HGET, (cmd)->cmd.hget(key, field), (v)->v)
				.run(args);
	}

	@Override
	public Map<String, String> hGetAll(final String key) {
		final CommandArguments args = CommandArguments.create("key", key);
		return new LettuceCommand<>(client, ProtocolCommand.HGETALL, (cmd)->cmd.hgetall(SafeEncoder.encode(key)),
				(new MapConverter.BinaryToStringMapConverter()))
				.run(args);
	}

	@Override
	public Map<byte[], byte[]> hGetAll(final byte[] key) {
		final CommandArguments args = CommandArguments.create("key", key);
		return new LettuceCommand<>(client, ProtocolCommand.HGETALL, (cmd)->cmd.hgetall(key), (v)->v)
				.run(args);
	}

	@Override
	public Long hIncrBy(final byte[] key, final byte[] field, final long value) {
		final CommandArguments args = CommandArguments.create("key", key).put("field", field).put("value", value);
		return new LettuceCommand<>(client, ProtocolCommand.HINCRBY, (cmd)->cmd.hincrby(key, field, value), (v)->v)
				.run(args);
	}

	@Override
	public Double hIncrByFloat(final byte[] key, final byte[] field, final double value) {
		final CommandArguments args = CommandArguments.create("key", key).put("field", field).put("value", value);
		return new LettuceCommand<>(client, ProtocolCommand.HINCRBYFLOAT, (cmd)->cmd.hincrbyfloat(key, field, value),
				(v)->v)
				.run(args);
	}

	@Override
	public Set<String> hKeys(final String key) {
		final CommandArguments args = CommandArguments.create("key", key);
		return new LettuceCommand<>(client, ProtocolCommand.HKEYS, (cmd)->cmd.hkeys(SafeEncoder.encode(key)),
				new ListSetConverter.BinaryToStringListSetConverter())
				.run(args);
	}

	@Override
	public Set<byte[]> hKeys(final byte[] key) {
		final CommandArguments args = CommandArguments.create("key", key);
		return new LettuceCommand<>(client, ProtocolCommand.HKEYS, (cmd)->cmd.hkeys(key), HashSet::new)
				.run(args);
	}

	@Override
	public Long hLen(final byte[] key) {
		final CommandArguments args = CommandArguments.create("key", key);
		return new LettuceCommand<>(client, ProtocolCommand.HLEN, (cmd)->cmd.hlen(key), (v)->v)
				.run(args);
	}

	@Override
	public List<String> hMGet(final String key, final String... fields) {
		final CommandArguments args = CommandArguments.create("key", key).put("fields", (Object[]) fields);
		return new LettuceCommand<>(client, ProtocolCommand.HMGET,
				(cmd)->cmd.hmget(SafeEncoder.encode(key), SafeEncoder.encode(fields)),
				new com.buession.core.converter.ListConverter<>((v)->SafeEncoder.encode(v.getValue())))
				.run(args);
	}

	@Override
	public List<byte[]> hMGet(final byte[] key, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create("key", key).put("fields", (Object[]) fields);
		return new LettuceCommand<>(client, ProtocolCommand.HMGET, (cmd)->cmd.hmget(key, fields),
				new com.buession.core.converter.ListConverter<>(Value::getValue))
				.run(args);
	}

	@Override
	public Status hMSet(final byte[] key, final Map<byte[], byte[]> data) {
		final CommandArguments args = CommandArguments.create("key", key).put("data", data);
		return new LettuceCommand<>(client, ProtocolCommand.HMGET, (cmd)->cmd.hmset(key, data),
				new OkStatusConverter())
				.run(args);
	}

	@Override
	public byte[] hRandField(final byte[] key) {
		final CommandArguments args = CommandArguments.create("key", key);
		return new LettuceCommand<byte[], byte[]>(client, ProtocolCommand.HRANDFIELD)
				.run(args);
	}

	@Override
	public List<String> hRandField(final String key, final long count) {
		final CommandArguments args = CommandArguments.create("key", key).put("count", count);
		return new LettuceCommand<List<String>, List<String>>(client, ProtocolCommand.HRANDFIELD)
				.run(args);
	}

	@Override
	public List<byte[]> hRandField(final byte[] key, final long count) {
		final CommandArguments args = CommandArguments.create("key", key).put("count", count);
		return new LettuceCommand<List<byte[]>, List<byte[]>>(client, ProtocolCommand.HRANDFIELD)
				.run(args);
	}

	@Override
	public Map<String, String> hRandFieldWithValues(final String key, final long count) {
		final CommandArguments args = CommandArguments.create("key", key).put("count", count);
		return new LettuceCommand<Map<String, String>, Map<String, String>>(client, ProtocolCommand.HRANDFIELD)
				.run(args);
	}

	@Override
	public Map<byte[], byte[]> hRandFieldWithValues(final byte[] key, final long count) {
		final CommandArguments args = CommandArguments.create("key", key).put("count", count);
		return new LettuceCommand<Map<byte[], byte[]>, Map<byte[], byte[]>>(client, ProtocolCommand.HRANDFIELD)
				.run(args);
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final String cursor) {
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor);
		return new LettuceCommand<>(client, ProtocolCommand.HSCAN,
				(cmd)->cmd.hscan(SafeEncoder.encode(key), new LettuceScanCursor(cursor)),
				new MapScanCursorConverter.BvSvMapScanCursorConverter())
				.run(args);
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor) {
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor);
		return new LettuceCommand<>(client, ProtocolCommand.HSCAN, (cmd)->cmd.hscan(key, new LettuceScanCursor(cursor)),
				new MapScanCursorConverter<>())
				.run(args);
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final String cursor, final String pattern) {
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor).put("pattern", pattern);
		return new LettuceCommand<>(client, ProtocolCommand.HSCAN,
				(cmd)->cmd.hscan(SafeEncoder.encode(key), new LettuceScanCursor(cursor), new LettuceScanArgs(pattern)),
				new MapScanCursorConverter.BvSvMapScanCursorConverter())
				.run(args);
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor, final byte[] pattern) {
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor).put("pattern", pattern);
		return new LettuceCommand<>(client, ProtocolCommand.HSCAN,
				(cmd)->cmd.hscan(key, new LettuceScanCursor(cursor), new LettuceScanArgs(pattern)),
				new MapScanCursorConverter<>())
				.run(args);
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final String cursor, final long count) {
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor).put("count", count);
		return new LettuceCommand<>(client, ProtocolCommand.HSCAN,
				(cmd)->cmd.hscan(SafeEncoder.encode(key), new LettuceScanCursor(cursor), new LettuceScanArgs(count)),
				new MapScanCursorConverter.BvSvMapScanCursorConverter())
				.run(args);
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor, final long count) {
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor).put("count", count);
		return new LettuceCommand<>(client, ProtocolCommand.HSCAN,
				(cmd)->cmd.hscan(key, new LettuceScanCursor(cursor), new LettuceScanArgs(count)),
				new MapScanCursorConverter<>())
				.run(args);
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final String cursor, final String pattern,
												 final long count) {
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor).put("pattern", pattern);
		return new LettuceCommand<>(client, ProtocolCommand.HSCAN,
				(cmd)->cmd.hscan(SafeEncoder.encode(key), new LettuceScanCursor(cursor),
						new LettuceScanArgs(pattern, count)), new MapScanCursorConverter.BvSvMapScanCursorConverter())
				.run(args);
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor, final byte[] pattern,
												 final long count) {
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor).put("pattern", pattern)
				.put("count", count);
		return new LettuceCommand<>(client, ProtocolCommand.HSCAN,
				(cmd)->cmd.hscan(key, new LettuceScanCursor(cursor), new LettuceScanArgs(pattern, count)),
				new MapScanCursorConverter<>())
				.run(args);
	}

	@Override
	public Long hSet(final byte[] key, final byte[] field, final byte[] value) {
		final CommandArguments args = CommandArguments.create("key", key).put("field", field).put("value", value);
		return new LettuceCommand<>(client, ProtocolCommand.HSET, (cmd)->cmd.hset(key, field, value),
				(v)->Boolean.TRUE.equals(v) ? 1L : 0L)
				.run(args);
	}

	@Override
	public Status hSetNx(final byte[] key, final byte[] field, final byte[] value) {
		final CommandArguments args = CommandArguments.create("key", key).put("field", field).put("value", value);
		return new LettuceCommand<>(client, ProtocolCommand.HSETNX, (cmd)->cmd.hsetnx(key, field, value),
				new BooleanStatusConverter())
				.run(args);
	}

	@Override
	public Long hStrLen(final byte[] key, final byte[] field) {
		final CommandArguments args = CommandArguments.create("key", key).put("field", field);
		return new LettuceCommand<>(client, ProtocolCommand.HSETNX, (cmd)->cmd.hstrlen(key, field), (v)->v)
				.run(args);
	}

	@Override
	public List<String> hVals(final String key) {
		final CommandArguments args = CommandArguments.create("key", key);
		return new LettuceCommand<>(client, ProtocolCommand.HVALS, (cmd)->cmd.hvals(SafeEncoder.encode(key)),
				new ListConverter.BinaryToStringListConverter())
				.run(args);
	}

	@Override
	public List<byte[]> hVals(final byte[] key) {
		final CommandArguments args = CommandArguments.create("key", key);
		return new LettuceCommand<>(client, ProtocolCommand.HVALS, (cmd)->cmd.hvals(key), (v)->v)
				.run(args);
	}

}
