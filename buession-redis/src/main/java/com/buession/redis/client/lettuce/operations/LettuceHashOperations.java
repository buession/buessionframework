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
 * | Copyright @ 2013-2026 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.client.lettuce.operations;

import com.buession.core.converter.BooleanStatusConverter;
import com.buession.core.converter.ListConverter;
import com.buession.core.converter.ListSetConverter;
import com.buession.core.converter.MapConverter;
import com.buession.lang.Status;
import com.buession.redis.client.lettuce.LettuceRedisClient;
import com.buession.redis.client.operations.HashOperations;
import com.buession.redis.core.ExpireOption;
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.command.Command;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.args.GetExArgument;
import com.buession.redis.core.command.args.HSetExArgument;
import com.buession.redis.core.internal.convert.lettuce.params.ExpireOptionConverter;
import com.buession.redis.core.internal.convert.lettuce.params.GetExArgumentConverter;
import com.buession.redis.core.internal.convert.lettuce.params.HSetExArgumentConverter;
import com.buession.redis.core.internal.convert.lettuce.response.ListKeyValueMapConverter;
import com.buession.redis.core.internal.convert.lettuce.response.ScanCursorConverter;
import com.buession.redis.core.internal.convert.response.OkStatusConverter;
import com.buession.redis.core.internal.convert.response.OneStatusConverter;
import com.buession.redis.core.internal.lettuce.LettuceScanArgs;
import com.buession.redis.core.internal.lettuce.LettuceScanCursor;
import com.buession.redis.utils.SafeEncoder;
import io.lettuce.core.MapScanCursor;
import io.lettuce.core.Value;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Lettuce 哈希表命令操作
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public final class LettuceHashOperations extends AbstractLettuceRedisOperations implements HashOperations {

	public LettuceHashOperations(final LettuceRedisClient client) {
		super(client);
	}

	@Override
	public Long hDel(final String key, final String... fields) {
		return hDel(SafeEncoder.encode(key), SafeEncoder.encode(fields));
	}

	@Override
	public Long hDel(final byte[] key, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(fields);
		return executeCommand(Command.HDEL, args, (cmd)->cmd.hdel(key, fields), (v)->v);
	}

	@Override
	public Boolean hExists(final String key, final String field) {
		return hExists(SafeEncoder.encode(key), SafeEncoder.encode(field));
	}

	@Override
	public Boolean hExists(final byte[] key, final byte[] field) {
		final CommandArguments args = CommandArguments.create(key).add(field);
		return executeCommand(Command.HEXISTS, args, (cmd)->cmd.hexists(key, field), (v)->v);
	}

	@Override
	public List<Long> hExpire(final String key, final long ttl, final String... fields) {
		return hExpire(SafeEncoder.encode(key), ttl, SafeEncoder.encode(fields));
	}

	@Override
	public List<Long> hExpire(final byte[] key, final long ttl, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(ttl).add("FIELDS").add(fields.length)
				.add(fields);
		return executeCommand(Command.HEXPIRE, args, (cmd)->cmd.hexpire(key, ttl, fields), (v)->v);
	}

	@Override
	public List<Long> hExpire(final String key, final long ttl, final ExpireOption option, final String... fields) {
		return hExpire(SafeEncoder.encode(key), ttl, option, SafeEncoder.encode(fields));
	}

	@Override
	public List<Long> hExpire(final byte[] key, final long ttl, final ExpireOption option, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(ttl).add(option).add("FIELDS").add(fields.length)
				.add(fields);
		final ExpireOptionConverter expireOptionConverter = new ExpireOptionConverter();
		return executeCommand(Command.HEXPIRE, args,
				(cmd)->cmd.hexpire(key, ttl, expireOptionConverter.convert(option), fields), (v)->v);
	}

	@Override
	public List<Long> hExpireAt(final String key, final long unixTimestamp, final String... fields) {
		return hExpireAt(SafeEncoder.encode(key), unixTimestamp, SafeEncoder.encode(fields));
	}

	@Override
	public List<Long> hExpireAt(final byte[] key, final long unixTimestamp, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(unixTimestamp).add("FIELDS").add(fields.length)
				.add(fields);
		return executeCommand(Command.HEXPIREAT, args, (cmd)->cmd.hexpireat(key, unixTimestamp, fields), (v)->v);
	}

	@Override
	public List<Long> hExpireAt(final String key, final long unixTimestamp, final ExpireOption option,
								final String... fields) {
		return hExpireAt(SafeEncoder.encode(key), unixTimestamp, option, SafeEncoder.encode(fields));
	}

	@Override
	public List<Long> hExpireAt(final byte[] key, final long unixTimestamp, final ExpireOption option,
								final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(unixTimestamp).add(option).add("FIELDS")
				.add(fields.length).add(fields);
		final ExpireOptionConverter expireOptionConverter = new ExpireOptionConverter();
		return executeCommand(Command.HEXPIREAT, args,
				(cmd)->cmd.hexpireat(key, unixTimestamp, expireOptionConverter.convert(option), fields), (v)->v);
	}

	@Override
	public List<Long> hExpireTime(final String key, final String... fields) {
		return hExpireTime(SafeEncoder.encode(key), SafeEncoder.encode(fields));
	}

	@Override
	public List<Long> hExpireTime(final byte[] key, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add("FIELDS").add(fields.length).add(fields);
		return executeCommand(Command.HEXPIRETIME, args, (cmd)->cmd.hexpiretime(key, fields), (v)->v);
	}

	@Override
	public String hGet(final String key, final String field) {
		final CommandArguments args = CommandArguments.create(key).add(field);
		return executeCommand(Command.HGET, args, (cmd)->cmd.hget(SafeEncoder.encode(key), SafeEncoder.encode(field)),
				SafeEncoder::encode);
	}

	@Override
	public byte[] hGet(final byte[] key, final byte[] field) {
		final CommandArguments args = CommandArguments.create(key).add(field);
		return executeCommand(Command.HGET, args, (cmd)->cmd.hget(key, field), (v)->v);
	}

	@Override
	public Map<String, String> hGetAll(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.HGETALL, args, (cmd)->cmd.hgetall(SafeEncoder.encode(key)),
				new MapConverter<>(SafeEncoder::encode, SafeEncoder::encode));
	}

	@Override
	public Map<byte[], byte[]> hGetAll(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.HGETALL, args, (cmd)->cmd.hgetall(key), (v)->v);
	}

	@Override
	public List<String> hGetDel(final String key, final String... fields) {
		final CommandArguments args = CommandArguments.create(key).add(fields);
		return executeCommand(Command.HGETDEL, args, (cmd)->cmd.hgetdel(SafeEncoder.encode(key),
				SafeEncoder.encode(fields)), new ListConverter<>((kv)->SafeEncoder.encode(kv.getValue())));
	}

	@Override
	public List<byte[]> hGetDel(final byte[] key, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(fields);
		return executeCommand(Command.HGETDEL, args, (cmd)->cmd.hgetdel(key, fields),
				new ListConverter<>(Value::getValue));
	}

	@Override
	public List<String> hGetEx(final String key, final String... fields) {
		final CommandArguments args = CommandArguments.create(key).add(fields);
		return executeCommand(Command.HGETEX, args, (cmd)->cmd.hgetex(SafeEncoder.encode(key),
				SafeEncoder.encode(fields)), new ListConverter<>((kv)->SafeEncoder.encode(kv.getValue())));
	}

	@Override
	public List<byte[]> hGetEx(final byte[] key, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(fields);
		return executeCommand(Command.HGETEX, args, (cmd)->cmd.hgetex(key, fields),
				new ListConverter<>(Value::getValue));
	}

	@Override
	public List<String> hGetEx(final String key, final GetExArgument argument, final String... fields) {
		final CommandArguments args = CommandArguments.create(key).add(argument).add(fields);
		final GetExArgumentConverter getExArgumentConverter = new GetExArgumentConverter();
		return executeCommand(Command.HGETEX, args, (cmd)->cmd.hgetex(SafeEncoder.encode(key),
						getExArgumentConverter.convert(argument), SafeEncoder.encode(fields)),
				new ListConverter<>((kv)->SafeEncoder.encode(kv.getValue())));
	}

	@Override
	public List<byte[]> hGetEx(final byte[] key, final GetExArgument argument, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(argument).add(fields);
		final GetExArgumentConverter getExArgumentConverter = new GetExArgumentConverter();
		return executeCommand(Command.HGETEX, args, (cmd)->cmd.hgetex(key,
				getExArgumentConverter.convert(argument), fields), new ListConverter<>(Value::getValue));
	}

	@Override
	public Long hIncrBy(final String key, final String field, final long value) {
		return hIncrBy(SafeEncoder.encode(key), SafeEncoder.encode(field), value);
	}

	@Override
	public Long hIncrBy(final byte[] key, final byte[] field, final long value) {
		final CommandArguments args = CommandArguments.create(key).add(field).add(value);
		return executeCommand(Command.HINCRBY, args, (cmd)->cmd.hincrby(key, field, value), (v)->v);
	}

	@Override
	public Double hIncrByFloat(final String key, final String field, final double value) {
		return hIncrByFloat(SafeEncoder.encode(key), SafeEncoder.encode(field), value);
	}

	@Override
	public Double hIncrByFloat(final byte[] key, final byte[] field, final double value) {
		final CommandArguments args = CommandArguments.create(key).add(field).add(value);
		return executeCommand(Command.HINCRBYFLOAT, args, (cmd)->cmd.hincrbyfloat(key, field, value), (v)->v);
	}

	@Override
	public Set<String> hKeys(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.HKEYS, args, (cmd)->cmd.hkeys(SafeEncoder.encode(key)),
				new ListSetConverter<>(SafeEncoder::encode));
	}

	@Override
	public Set<byte[]> hKeys(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.HKEYS, args, (cmd)->cmd.hkeys(key), new ListSetConverter<>((v)->v));
	}

	@Override
	public Long hLen(final String key) {
		return hLen(SafeEncoder.encode(key));
	}

	@Override
	public Long hLen(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.HLEN, args, (cmd)->cmd.hlen(key), (v)->v);
	}

	@Override
	public List<String> hMGet(final String key, final String... fields) {
		final CommandArguments args = CommandArguments.create(key).add(fields);
		return executeCommand(Command.HMGET, args, (cmd)->cmd.hmget(SafeEncoder.encode(key),
				SafeEncoder.encode(fields)), new ListConverter<>((kv)->SafeEncoder.encode(kv.getValue())));
	}

	@Override
	public List<byte[]> hMGet(final byte[] key, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(fields);
		return executeCommand(Command.HMGET, args, (cmd)->cmd.hmget(key, fields),
				new ListConverter<>(Value::getValue));
	}

	@Override
	public Status hMSet(final String key, final Map<String, String> data) {
		final MapConverter<String, String, byte[], byte[]> mapConverter = new MapConverter<>(SafeEncoder::encode,
				SafeEncoder::encode);
		return hMSet(SafeEncoder.encode(key), mapConverter.convert(data));
	}

	@Override
	public Status hMSet(final byte[] key, final Map<byte[], byte[]> data) {
		final CommandArguments args = CommandArguments.create(key).add(data);
		return executeCommand(Command.HMSET, args, (cmd)->cmd.hmset(key, data), new OkStatusConverter());
	}

	@Override
	public List<Long> hPersist(final String key, final String... fields) {
		return hPersist(SafeEncoder.encode(key), SafeEncoder.encode(fields));
	}

	@Override
	public List<Long> hPersist(final byte[] key, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(fields);
		return executeCommand(Command.HPERSIST, args, (cmd)->cmd.hpersist(key, fields), (v)->v);
	}

	@Override
	public List<Long> hPExpire(final String key, final long ttl, final String... fields) {
		return hPExpire(SafeEncoder.encode(key), ttl, SafeEncoder.encode(fields));
	}

	@Override
	public List<Long> hPExpire(final byte[] key, final long ttl, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(ttl).add("FIELDS").add(fields.length)
				.add(fields);
		return executeCommand(Command.HPEXPIRE, args, (cmd)->cmd.hpexpire(key, ttl, fields), (v)->v);
	}

	@Override
	public List<Long> hPExpire(final String key, final long ttl, final ExpireOption option, final String... fields) {
		return hPExpire(SafeEncoder.encode(key), ttl, option, SafeEncoder.encode(fields));
	}

	@Override
	public List<Long> hPExpire(final byte[] key, final long ttl, final ExpireOption option, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(ttl).add(option).add("FIELDS").add(fields.length)
				.add(fields);
		final ExpireOptionConverter expireOptionConverter = new ExpireOptionConverter();
		return executeCommand(Command.HPEXPIRE, args, (cmd)->cmd.hpexpire(key, ttl,
				expireOptionConverter.convert(option), fields), (v)->v);
	}

	@Override
	public List<Long> hPExpireAt(final String key, final long unixTimestamp, final String... fields) {
		return hPExpireAt(SafeEncoder.encode(key), unixTimestamp, SafeEncoder.encode(fields));
	}

	@Override
	public List<Long> hPExpireAt(final byte[] key, final long unixTimestamp, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(unixTimestamp).add("FIELDS").add(fields.length)
				.add(fields);
		return executeCommand(Command.HPEXPIREAT, args, (cmd)->cmd.hpexpireat(key, unixTimestamp, fields), (v)->v);
	}

	@Override
	public List<Long> hPExpireAt(final String key, final long unixTimestamp, final ExpireOption option,
								 final String... fields) {
		return hPExpireAt(SafeEncoder.encode(key), unixTimestamp, option, SafeEncoder.encode(fields));
	}

	@Override
	public List<Long> hPExpireAt(final byte[] key, final long unixTimestamp, final ExpireOption option,
								 final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(unixTimestamp).add(option).add("FIELDS")
				.add(fields.length).add(fields);
		final ExpireOptionConverter expireOptionConverter = new ExpireOptionConverter();
		return executeCommand(Command.HPEXPIREAT, args, (cmd)->cmd.hpexpireat(key, unixTimestamp,
				expireOptionConverter.convert(option), fields), (v)->v);
	}

	@Override
	public List<Long> hPExpireTime(final String key, final String... fields) {
		return hPExpireTime(SafeEncoder.encode(key), SafeEncoder.encode(fields));
	}

	@Override
	public List<Long> hPExpireTime(final byte[] key, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add("FIELDS").add(fields.length).add(fields);
		return executeCommand(Command.HPEXPIRETIME, args, (cmd)->cmd.hpexpiretime(key, fields), (v)->v);
	}

	@Override
	public List<Long> hPTtl(final String key, final String... fields) {
		return hPTtl(SafeEncoder.encode(key), SafeEncoder.encode(fields));
	}

	@Override
	public List<Long> hPTtl(final byte[] key, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add("FIELDS").add(fields.length).add(fields);
		return executeCommand(Command.HPTTL, args, (cmd)->cmd.hpttl(key, fields), (v)->v);
	}

	@Override
	public String hRandField(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.HRANDFIELD, args, (cmd)->cmd.hrandfield(SafeEncoder.encode(key)),
				SafeEncoder::encode);
	}

	@Override
	public byte[] hRandField(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.HRANDFIELD, args, (cmd)->cmd.hrandfield(key), (v)->v);
	}

	@Override
	public List<String> hRandField(final String key, final long count) {
		final CommandArguments args = CommandArguments.create(key).add(count);
		return executeCommand(Command.HRANDFIELD, args, (cmd)->cmd.hrandfield(SafeEncoder.encode(key), count),
				new ListConverter<>(SafeEncoder::encode));
	}

	@Override
	public List<byte[]> hRandField(final byte[] key, final long count) {
		final CommandArguments args = CommandArguments.create(key).add(count);
		return executeCommand(Command.HRANDFIELD, args, (cmd)->cmd.hrandfield(key, count), (v)->v);
	}

	@Override
	public Map<String, String> hRandFieldWithValues(final String key, final long count) {
		final CommandArguments args = CommandArguments.create(key).add(count).add("WITHVALUES");
		return executeCommand(Command.HRANDFIELD, args, (cmd)->cmd.hrandfieldWithvalues(SafeEncoder.encode(key),
				count), new ListKeyValueMapConverter<>(SafeEncoder::encode, SafeEncoder::encode));
	}

	@Override
	public Map<byte[], byte[]> hRandFieldWithValues(final byte[] key, final long count) {
		final CommandArguments args = CommandArguments.create(key).add(count).add("WITHVALUES");
		return executeCommand(Command.HRANDFIELD, args, (cmd)->cmd.hrandfieldWithvalues(key, count),
				new ListKeyValueMapConverter<>((k)->k, (v)->v));
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final String cursor) {
		final CommandArguments args = CommandArguments.create(key).add(cursor);
		return hStringScan(args, (cmd)->cmd.hscan(SafeEncoder.encode(key),
				new LettuceScanCursor(SafeEncoder.encode(cursor))));
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor) {
		final CommandArguments args = CommandArguments.create(key).add(cursor);
		return hBinaryScan(args, (cmd)->cmd.hscan(key, new LettuceScanCursor(cursor)));
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final String cursor, final String pattern) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(pattern);
		return hStringScan(args, (cmd)->cmd.hscan(SafeEncoder.encode(key),
				new LettuceScanCursor(SafeEncoder.encode(cursor)), new LettuceScanArgs(pattern)));
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor, final byte[] pattern) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(pattern);
		return hBinaryScan(args, (cmd)->cmd.hscan(key, new LettuceScanCursor(cursor), new LettuceScanArgs(pattern)));
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final String cursor, final long count) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(count);
		return hStringScan(args, (cmd)->cmd.hscan(SafeEncoder.encode(key),
				new LettuceScanCursor(SafeEncoder.encode(cursor)), new LettuceScanArgs(count)));
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor, final long count) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(count);
		return hBinaryScan(args, (cmd)->cmd.hscan(key, new LettuceScanCursor(cursor), new LettuceScanArgs(count)));
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final String cursor, final String pattern,
												 final long count) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(pattern);
		return hStringScan(args, (cmd)->cmd.hscan(SafeEncoder.encode(key),
				new LettuceScanCursor(SafeEncoder.encode(cursor)), new LettuceScanArgs(pattern, count)));
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor, final byte[] pattern,
												 final long count) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(pattern).add(count);
		return hBinaryScan(args, (cmd)->cmd.hscan(key, new LettuceScanCursor(cursor), new LettuceScanArgs(pattern,
				count)));
	}

	@Override
	public Long hSet(final String key, final Map<String, String> data) {
		final MapConverter<String, String, byte[], byte[]> mapConverter = new MapConverter<>(SafeEncoder::encode,
				SafeEncoder::encode);
		return hSet(SafeEncoder.encode(key), mapConverter.convert(data));
	}

	@Override
	public Long hSet(final byte[] key, final Map<byte[], byte[]> data) {
		final CommandArguments args = CommandArguments.create(key).add(data);
		return executeCommand(Command.HSET, args, (cmd)->cmd.hset(key, data), (v)->v);
	}

	@Override
	public Status hSetEx(final String key, final Map<String, String> data) {
		final MapConverter<String, String, byte[], byte[]> mapConverter = new MapConverter<>(SafeEncoder::encode,
				SafeEncoder::encode);
		return hSetEx(SafeEncoder.encode(key), mapConverter.convert(data));
	}

	@Override
	public Status hSetEx(final byte[] key, final Map<byte[], byte[]> data) {
		final CommandArguments args = CommandArguments.create(key).add(data);
		return executeCommand(Command.HSET, args, (cmd)->cmd.hsetex(key, data), new OneStatusConverter());
	}

	@Override
	public Status hSetEx(final String key, final Map<String, String> data, final HSetExArgument argument) {
		final MapConverter<String, String, byte[], byte[]> mapConverter = new MapConverter<>(SafeEncoder::encode,
				SafeEncoder::encode);
		return hSetEx(SafeEncoder.encode(key), mapConverter.convert(data), argument);
	}

	@Override
	public Status hSetEx(final byte[] key, final Map<byte[], byte[]> data, final HSetExArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(argument).add(data);
		final HSetExArgumentConverter hSetExArgumentConverter = new HSetExArgumentConverter();
		return executeCommand(Command.HSETEX, args, (cmd)->cmd.hsetex(key,
				hSetExArgumentConverter.convert(argument), data), new OneStatusConverter());
	}

	@Override
	public Status hSetNx(final String key, final String field, final String value) {
		return hSetNx(SafeEncoder.encode(key), SafeEncoder.encode(field), SafeEncoder.encode(value));
	}

	@Override
	public Status hSetNx(final byte[] key, final byte[] field, final byte[] value) {
		final CommandArguments args = CommandArguments.create(key).add(field).add(value);
		return executeCommand(Command.HSETNX, args, (cmd)->cmd.hsetnx(key, field, value), new BooleanStatusConverter());
	}

	@Override
	public Long hStrLen(final String key, final String field) {
		return hStrLen(SafeEncoder.encode(key), SafeEncoder.encode(field));
	}

	@Override
	public Long hStrLen(final byte[] key, final byte[] field) {
		final CommandArguments args = CommandArguments.create(key).add(field);
		return executeCommand(Command.HSTRLEN, args, (cmd)->cmd.hstrlen(key, field), (v)->v);
	}

	@Override
	public List<Long> hTtl(final String key, final String... fields) {
		return hTtl(SafeEncoder.encode(key), SafeEncoder.encode(fields));
	}

	@Override
	public List<Long> hTtl(final byte[] key, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(fields);
		return executeCommand(Command.HTTL, args, (cmd)->cmd.httl(key, fields), (v)->v);
	}

	@Override
	public List<String> hVals(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.HVALS, args, (cmd)->cmd.hvals(SafeEncoder.encode(key)),
				new ListConverter<>(SafeEncoder::encode));
	}

	@Override
	public List<byte[]> hVals(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.HVALS, args, (cmd)->cmd.hvals(key), (v)->v);
	}

	private ScanResult<Map<String, String>> hStringScan(final CommandArguments args,
														final com.buession.redis.core.Command.Executor<io.lettuce.core.RedisCommands<byte[], byte[]>, MapScanCursor<byte[], byte[]>> executor) {
		return executeCommand(Command.HRANDFIELD, args, executor,
				new ScanCursorConverter.MapScanCursorConverter.BvSvMapScanCursorConverter());
	}

	private ScanResult<Map<byte[], byte[]>> hBinaryScan(final CommandArguments args,
														final com.buession.redis.core.Command.Executor<io.lettuce.core.RedisCommands<byte[], byte[]>, MapScanCursor<byte[], byte[]>> executor) {
		return executeCommand(Command.HRANDFIELD, args, executor, new ScanCursorConverter.MapScanCursorConverter<>());
	}

}
