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
package com.buession.redis.client.lettuce.command;

import com.buession.core.converter.ArrayKeyValueMapConverter;
import com.buession.core.converter.BooleanStatusConverter;
import com.buession.core.converter.ListConverter;
import com.buession.core.converter.ListSetConverter;
import com.buession.lang.KeyValue;
import com.buession.lang.Status;
import com.buession.redis.client.lettuce.LettuceRedisClient;
import com.buession.redis.core.command.args.ExpireOption;
import com.buession.redis.core.Keyword;
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.command.RedisCommand;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.HashCommands;
import com.buession.redis.core.command.args.FnxFxx;
import com.buession.redis.core.command.args.GetExType;
import com.buession.redis.core.command.args.PxExType;
import com.buession.redis.core.internal.convert.BinaryListStringListConverter;
import com.buession.redis.core.internal.convert.BinaryMapStringMapConverter;
import com.buession.redis.core.internal.convert.lettuce.response.ListKeyValueMapConverter;
import com.buession.redis.core.internal.convert.lettuce.response.ScanCursorConverter;
import com.buession.redis.core.internal.convert.response.OkStatusConverter;
import com.buession.redis.core.internal.convert.response.OneStatusConverter;
import com.buession.redis.core.internal.lettuce.args.LettuceExpireArgs;
import com.buession.redis.core.internal.lettuce.args.LettuceHGetExArgs;
import com.buession.redis.core.internal.lettuce.args.LettuceHSetExArgs;
import com.buession.redis.core.internal.lettuce.args.LettuceScanArgs;
import com.buession.redis.core.internal.lettuce.args.LettuceScanCursor;
import com.buession.redis.utils.SafeEncoder;
import io.lettuce.core.HSetExArgs;
import io.lettuce.core.ScanArgs;
import io.lettuce.core.Value;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Lettuce 哈希表命令
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public final class LettuceHashCommands extends AbstractLettuceRedisCommands implements HashCommands {

	public LettuceHashCommands(final LettuceRedisClient client) {
		super(client);
	}

	@Override
	public Long hDel(final String key, final String... fields) {
		final CommandArguments args = CommandArguments.create(key).add(fields);
		return executeCommand(RedisCommand.HDEL, args, (cmd)->cmd.hdel(rawBinaryKey(key), SafeEncoder.encode(fields)));
	}

	@Override
	public Long hDel(final byte[] key, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(fields);
		return executeCommand(RedisCommand.HDEL, args, (cmd)->cmd.hdel(rawKey(key), fields));
	}

	@Override
	public Boolean hExists(final String key, final String field) {
		final CommandArguments args = CommandArguments.create(key, field);
		return executeCommand(
				RedisCommand.HEXISTS, args, (cmd)->cmd.hexists(rawBinaryKey(key), SafeEncoder.encode(field)));
	}

	@Override
	public Boolean hExists(final byte[] key, final byte[] field) {
		final CommandArguments args = CommandArguments.create(key, field);
		return executeCommand(RedisCommand.HEXISTS, args, (cmd)->cmd.hexists(rawKey(key), field));
	}

	@Override
	public List<Long> hExpire(final String key, final long ttl, final String... fields) {
		final CommandArguments args = CommandArguments.create(key).add(ttl).add(Keyword.Hash.FIELDS, fields.length)
				.add(fields);
		return executeCommand(RedisCommand.HEXPIRE, args,
				(cmd)->cmd.hexpire(rawBinaryKey(key), ttl, SafeEncoder.encode(fields)));
	}

	@Override
	public List<Long> hExpire(final byte[] key, final long ttl, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(ttl).add(Keyword.Hash.FIELDS, fields.length)
				.add(fields);
		return executeCommand(RedisCommand.HEXPIRE, args, (cmd)->cmd.hexpire(rawKey(key), ttl, fields));
	}

	@Override
	public List<Long> hExpire(final String key, final long ttl, final ExpireOption option, final String... fields) {
		final CommandArguments args = CommandArguments.create(key).add(ttl).add(option)
				.add(Keyword.Hash.FIELDS, fields.length).add(fields);
		return executeCommand(RedisCommand.HEXPIRE, args,
				(cmd)->cmd.hexpire(rawBinaryKey(key), ttl, new LettuceExpireArgs(option),
						SafeEncoder.encode(fields)));
	}

	@Override
	public List<Long> hExpire(final byte[] key, final long ttl, final ExpireOption option, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(ttl).add(option)
				.add(Keyword.Hash.FIELDS, fields.length).add(fields);
		return executeCommand(RedisCommand.HEXPIRE, args,
				(cmd)->cmd.hexpire(rawKey(key), ttl, new LettuceExpireArgs(option), fields));
	}

	@Override
	public List<Long> hExpireAt(final String key, final long unixTimestamp, final String... fields) {
		final CommandArguments args = CommandArguments.create(key).add(unixTimestamp)
				.add(Keyword.Hash.FIELDS, fields.length).add(fields);
		return executeCommand(RedisCommand.HEXPIREAT, args,
				(cmd)->cmd.hexpireat(rawBinaryKey(key), unixTimestamp, SafeEncoder.encode(fields)));
	}

	@Override
	public List<Long> hExpireAt(final byte[] key, final long unixTimestamp, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(unixTimestamp)
				.add(Keyword.Hash.FIELDS, fields.length).add(fields);
		return executeCommand(RedisCommand.HEXPIREAT, args, (cmd)->cmd.hexpireat(rawKey(key), unixTimestamp, fields));
	}

	@Override
	public List<Long> hExpireAt(final String key, final long unixTimestamp, final ExpireOption option,
	                            final String... fields) {
		final CommandArguments args = CommandArguments.create(key).add(unixTimestamp).add(option)
				.add(Keyword.Hash.FIELDS, fields.length).add(fields);
		return executeCommand(RedisCommand.HEXPIREAT, args,
				(cmd)->cmd.hexpireat(rawBinaryKey(key), unixTimestamp, new LettuceExpireArgs(option),
						SafeEncoder.encode(fields)));
	}

	@Override
	public List<Long> hExpireAt(final byte[] key, final long unixTimestamp, final ExpireOption option,
	                            final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(unixTimestamp).add(option)
				.add(Keyword.Hash.FIELDS, fields.length).add(fields);
		return executeCommand(RedisCommand.HEXPIREAT, args,
				(cmd)->cmd.hexpireat(rawKey(key), unixTimestamp, new LettuceExpireArgs(option), fields));
	}

	@Override
	public List<Long> hExpireTime(final String key, final String... fields) {
		final CommandArguments args = CommandArguments.create(key).add(Keyword.Hash.FIELDS, fields.length).add(fields);
		return executeCommand(RedisCommand.HEXPIRETIME, args,
				(cmd)->cmd.hexpiretime(rawBinaryKey(key), SafeEncoder.encode(fields)));
	}

	@Override
	public List<Long> hExpireTime(final byte[] key, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(Keyword.Hash.FIELDS, fields.length).add(fields);
		return executeCommand(RedisCommand.HEXPIRETIME, args, (cmd)->cmd.hexpiretime(rawKey(key), fields));
	}

	@Override
	public String hGet(final String key, final String field) {
		final CommandArguments args = CommandArguments.create(key).add(field);
		return executeCommand(RedisCommand.HGET, args, (cmd)->cmd.hget(rawBinaryKey(key), SafeEncoder.encode(field)),
				SafeEncoder::encode);
	}

	@Override
	public byte[] hGet(final byte[] key, final byte[] field) {
		final CommandArguments args = CommandArguments.create(key).add(field);
		return executeCommand(RedisCommand.HGET, args, (cmd)->cmd.hget(rawKey(key), field));
	}

	@Override
	public Map<String, String> hGetAll(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.HGETALL, args, (cmd)->cmd.hgetall(rawBinaryKey(key)),
				new BinaryMapStringMapConverter());
	}

	@Override
	public Map<byte[], byte[]> hGetAll(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.HGETALL, args, (cmd)->cmd.hgetall(rawKey(key)));
	}

	@Override
	public List<String> hGetDel(final String key, final String... fields) {
		final CommandArguments args = CommandArguments.create(key).add(Keyword.Hash.FIELDS, fields.length).add(fields);
		return executeCommand(
				RedisCommand.HGETDEL, args, (cmd)->cmd.hgetdel(rawBinaryKey(key), SafeEncoder.encode(fields)),
				new ListConverter<>((kv)->SafeEncoder.encode(kv.getValue())));
	}

	@Override
	public List<byte[]> hGetDel(final byte[] key, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(Keyword.Hash.FIELDS, fields.length).add(fields);
		return executeCommand(RedisCommand.HGETDEL, args, (cmd)->cmd.hgetdel(rawKey(key), fields),
				new ListConverter<>(Value::getValue));
	}

	@Override
	public List<String> hGetEx(final String key, final String... fields) {
		final CommandArguments args = CommandArguments.create(key).add(Keyword.Hash.FIELDS, fields.length).add(fields);
		return executeCommand(
				RedisCommand.HGETEX, args, (cmd)->cmd.hgetex(rawBinaryKey(key), SafeEncoder.encode(fields)),
				new ListConverter<>((kv)->SafeEncoder.encode(kv.getValue())));
	}

	@Override
	public List<byte[]> hGetEx(final byte[] key, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(Keyword.Hash.FIELDS, fields.length).add(fields);
		return executeCommand(RedisCommand.HGETEX, args, (cmd)->cmd.hgetex(rawKey(key), fields),
				new ListConverter<>(Value::getValue));
	}

	@Override
	public List<String> hGetEx(final String key, final GetExType exType, final long expires, final String... fields) {
		final CommandArguments args = CommandArguments.create(key).add(exType, expires)
				.add(Keyword.Hash.FIELDS, fields.length).add(fields);
		return executeCommand(RedisCommand.HGETEX, args,
				(cmd)->cmd.hgetex(rawBinaryKey(key), new LettuceHGetExArgs(exType, expires),
						SafeEncoder.encode(fields)),
				new ListConverter<>((kv)->SafeEncoder.encode(kv.getValue())));
	}

	@Override
	public List<byte[]> hGetEx(final byte[] key, final GetExType exType, final long expires, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(exType, expires)
				.add(Keyword.Hash.FIELDS, fields.length).add(fields);
		return executeCommand(RedisCommand.HGETEX, args,
				(cmd)->cmd.hgetex(rawKey(key), new LettuceHGetExArgs(exType, expires), fields),
				new ListConverter<>(Value::getValue));
	}

	@Override
	public Long hIncrBy(final String key, final String field, final long value) {
		final CommandArguments args = CommandArguments.create(key).add(field, value);
		return executeCommand(RedisCommand.HINCRBY, args,
				(cmd)->cmd.hincrby(rawBinaryKey(key), SafeEncoder.encode(field), value));
	}

	@Override
	public Long hIncrBy(final byte[] key, final byte[] field, final long value) {
		final CommandArguments args = CommandArguments.create(key).add(field, value);
		return executeCommand(RedisCommand.HINCRBY, args, (cmd)->cmd.hincrby(rawKey(key), field, value));
	}

	@Override
	public Double hIncrByFloat(final String key, final String field, final double value) {
		final CommandArguments args = CommandArguments.create(key).add(field, value);
		return executeCommand(RedisCommand.HINCRBYFLOAT, args,
				(cmd)->cmd.hincrbyfloat(rawBinaryKey(key), SafeEncoder.encode(field), value));
	}

	@Override
	public Double hIncrByFloat(final byte[] key, final byte[] field, final double value) {
		final CommandArguments args = CommandArguments.create(key).add(field, value);
		return executeCommand(RedisCommand.HINCRBYFLOAT, args, (cmd)->cmd.hincrbyfloat(rawKey(key), field, value));
	}

	@Override
	public Set<String> hKeys(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.HKEYS, args, (cmd)->cmd.hkeys(rawBinaryKey(key)),
				new ListSetConverter<>(SafeEncoder::encode));
	}

	@Override
	public Set<byte[]> hKeys(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.HKEYS, args, (cmd)->cmd.hkeys(key), new ListSetConverter<>((v)->v));
	}

	@Override
	public Long hLen(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.HLEN, args, (cmd)->cmd.hlen(rawBinaryKey(key)));
	}

	@Override
	public Long hLen(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.HLEN, args, (cmd)->cmd.hlen(rawKey(key)));
	}

	@Override
	public List<String> hMGet(final String key, final String... fields) {
		final CommandArguments args = CommandArguments.create(key).add(fields);
		return executeCommand(RedisCommand.HMGET, args, (cmd)->cmd.hmget(rawBinaryKey(key), SafeEncoder.encode(fields)),
				new ListConverter<>((kv)->SafeEncoder.encode(kv.getValue())));
	}

	@Override
	public List<byte[]> hMGet(final byte[] key, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(fields);
		return executeCommand(RedisCommand.HMGET, args, (cmd)->cmd.hmget(rawKey(key), fields),
				new ListConverter<>(Value::getValue));
	}

	@SuppressWarnings({"unchecked"})
	@Override
	public Status hMSet(final String key, final KeyValue<String, String>... data) {
		final CommandArguments args = CommandArguments.create(key).add(data);
		final ArrayKeyValueMapConverter<String, String, byte[], byte[]> arrayKeyValueMapConverter = new ArrayKeyValueMapConverter<>(
				SafeEncoder::encode, SafeEncoder::encode);
		return executeCommand(RedisCommand.HMSET, args,
				(cmd)->cmd.hmset(rawBinaryKey(key), arrayKeyValueMapConverter.convert(data)), new OkStatusConverter());
	}

	@SuppressWarnings({"unchecked"})
	@Override
	public Status hMSet(final byte[] key, final KeyValue<byte[], byte[]>... data) {
		final CommandArguments args = CommandArguments.create(key).add(data);
		final ArrayKeyValueMapConverter<byte[], byte[], byte[], byte[]> arrayKeyValueMapConverter = new ArrayKeyValueMapConverter<>(
				(k)->k, (v)->v);
		return executeCommand(RedisCommand.HMSET, args,
				(cmd)->cmd.hmset(rawKey(key), arrayKeyValueMapConverter.convert(data)), new OkStatusConverter());
	}

	@Override
	public List<Long> hPersist(final String key, final String... fields) {
		final CommandArguments args = CommandArguments.create(key).add(Keyword.Hash.FIELDS, fields.length).add(fields);
		return executeCommand(RedisCommand.HPERSIST, args,
				(cmd)->cmd.hpersist(rawBinaryKey(key), SafeEncoder.encode(fields)));
	}

	@Override
	public List<Long> hPersist(final byte[] key, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(Keyword.Hash.FIELDS, fields.length).add(fields);
		return executeCommand(RedisCommand.HPERSIST, args, (cmd)->cmd.hpersist(rawKey(key), fields));
	}

	@Override
	public List<Long> hPExpire(final String key, final long ttl, final String... fields) {
		final CommandArguments args = CommandArguments.create(key).add(ttl).add(Keyword.Hash.FIELDS, fields.length)
				.add(fields);
		return executeCommand(RedisCommand.HPEXPIRE, args,
				(cmd)->cmd.hpexpire(rawBinaryKey(key), ttl, SafeEncoder.encode(fields)));
	}

	@Override
	public List<Long> hPExpire(final byte[] key, final long ttl, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(ttl).add(Keyword.Hash.FIELDS, fields.length)
				.add(fields);
		return executeCommand(RedisCommand.HPEXPIRE, args, (cmd)->cmd.hpexpire(rawKey(key), ttl, fields));
	}

	@Override
	public List<Long> hPExpire(final String key, final long ttl, final ExpireOption option, final String... fields) {
		final CommandArguments args = CommandArguments.create(key).add(ttl).add(option)
				.add(Keyword.Hash.FIELDS, fields.length).add(fields);
		return executeCommand(RedisCommand.HPEXPIRE, args,
				(cmd)->cmd.hpexpire(rawBinaryKey(key), ttl, new LettuceExpireArgs(option), SafeEncoder.encode(fields)));
	}

	@Override
	public List<Long> hPExpire(final byte[] key, final long ttl, final ExpireOption option, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(ttl).add(option)
				.add(Keyword.Hash.FIELDS, fields.length).add(fields);
		return executeCommand(RedisCommand.HPEXPIRE, args,
				(cmd)->cmd.hpexpire(rawKey(key), ttl, new LettuceExpireArgs(option), fields));
	}

	@Override
	public List<Long> hPExpireAt(final String key, final long unixTimestamp, final String... fields) {
		final CommandArguments args = CommandArguments.create(key).add(unixTimestamp)
				.add(Keyword.Hash.FIELDS, fields.length).add(fields);
		return executeCommand(RedisCommand.HPEXPIREAT, args,
				(cmd)->cmd.hpexpireat(rawBinaryKey(key), unixTimestamp, SafeEncoder.encode(fields)));
	}

	@Override
	public List<Long> hPExpireAt(final byte[] key, final long unixTimestamp, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(unixTimestamp)
				.add(Keyword.Hash.FIELDS, fields.length).add(fields);
		return executeCommand(RedisCommand.HPEXPIREAT, args, (cmd)->cmd.hpexpireat(rawKey(key), unixTimestamp, fields));
	}

	@Override
	public List<Long> hPExpireAt(final String key, final long unixTimestamp, final ExpireOption option,
	                             final String... fields) {
		final CommandArguments args = CommandArguments.create(key).add(unixTimestamp).add(option)
				.add(Keyword.Hash.FIELDS, fields.length).add(fields);
		return executeCommand(RedisCommand.HPEXPIREAT, args,
				(cmd)->cmd.hpexpireat(rawBinaryKey(key), unixTimestamp, new LettuceExpireArgs(option),
						SafeEncoder.encode(fields)));
	}

	@Override
	public List<Long> hPExpireAt(final byte[] key, final long unixTimestamp, final ExpireOption option,
	                             final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(unixTimestamp).add(option)
				.add(Keyword.Hash.FIELDS, fields.length).add(fields);
		return executeCommand(RedisCommand.HPEXPIREAT, args,
				(cmd)->cmd.hpexpireat(rawKey(key), unixTimestamp, new LettuceExpireArgs(option), fields));
	}

	@Override
	public List<Long> hPExpireTime(final String key, final String... fields) {
		final CommandArguments args = CommandArguments.create(key).add(Keyword.Hash.FIELDS, fields.length).add(fields);
		return executeCommand(RedisCommand.HPEXPIRETIME, args,
				(cmd)->cmd.hpexpiretime(rawBinaryKey(key), SafeEncoder.encode(fields)));
	}

	@Override
	public List<Long> hPExpireTime(final byte[] key, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(Keyword.Hash.FIELDS, fields.length).add(fields);
		return executeCommand(RedisCommand.HPEXPIRETIME, args, (cmd)->cmd.hpexpiretime(rawKey(key), fields));
	}

	@Override
	public List<Long> hPTtl(final String key, final String... fields) {
		final CommandArguments args = CommandArguments.create(key).add(Keyword.Hash.FIELDS, fields.length).add(fields);
		return executeCommand(RedisCommand.HPTTL, args,
				(cmd)->cmd.hpttl(rawBinaryKey(key), SafeEncoder.encode(fields)));
	}

	@Override
	public List<Long> hPTtl(final byte[] key, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(Keyword.Hash.FIELDS, fields.length).add(fields);
		return executeCommand(RedisCommand.HPTTL, args, (cmd)->cmd.hpttl(rawKey(key), fields));
	}

	@Override
	public String hRandField(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.HRANDFIELD, args, (cmd)->cmd.hrandfield(rawBinaryKey(key)),
				SafeEncoder::encode);
	}

	@Override
	public byte[] hRandField(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.HRANDFIELD, args, (cmd)->cmd.hrandfield(rawKey(key)));
	}

	@Override
	public List<String> hRandField(final String key, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(count);
		return executeCommand(RedisCommand.HRANDFIELD, args, (cmd)->cmd.hrandfield(rawBinaryKey(key), count),
				new BinaryListStringListConverter());
	}

	@Override
	public List<byte[]> hRandField(final byte[] key, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(count);
		return executeCommand(RedisCommand.HRANDFIELD, args, (cmd)->cmd.hrandfield(rawKey(key), count));
	}

	@Override
	public Map<String, String> hRandFieldWithValues(final String key, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(count).add(Keyword.Hash.WITHVALUES);
		return executeCommand(RedisCommand.HRANDFIELD, args, (cmd)->cmd.hrandfieldWithvalues(rawBinaryKey(key), count),
				new ListKeyValueMapConverter<>(SafeEncoder::encode, SafeEncoder::encode));
	}

	@Override
	public Map<byte[], byte[]> hRandFieldWithValues(final byte[] key, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(count).add(Keyword.Hash.WITHVALUES);
		return executeCommand(RedisCommand.HRANDFIELD, args, (cmd)->cmd.hrandfieldWithvalues(rawKey(key), count),
				new ListKeyValueMapConverter<>((k)->k, (v)->v));
	}

	@Override
	public ScanResult<KeyValue<String, String>> hScan(final String key, final String cursor) {
		final CommandArguments args = CommandArguments.create(key).add(cursor);
		return executeCommand(
				RedisCommand.SCAN, args, (cmd)->cmd.hscan(rawBinaryKey(key), new LettuceScanCursor(cursor)),
				new ScanCursorConverter.MapScanCursorConverter<>(SafeEncoder::encode, SafeEncoder::encode));
	}

	@Override
	public ScanResult<KeyValue<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor) {
		final CommandArguments args = CommandArguments.create(key).add(cursor);
		return executeCommand(RedisCommand.SCAN, args, (cmd)->cmd.hscan(rawKey(key), new LettuceScanCursor(cursor)),
				new ScanCursorConverter.MapScanCursorConverter<>((k)->k, (v)->v));
	}

	@Override
	public ScanResult<KeyValue<String, String>> hScan(final String key, final String cursor, final String pattern) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(Keyword.Scan.MATCH, pattern);
		return hStringScan(rawBinaryKey(key), cursor, new LettuceScanArgs(pattern), args);
	}

	@Override
	public ScanResult<KeyValue<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor, final byte[] pattern) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(Keyword.Scan.MATCH, pattern);
		return hBinaryScan(rawKey(key), cursor, new LettuceScanArgs(pattern), args);
	}

	@Override
	public ScanResult<KeyValue<String, String>> hScan(final String key, final String cursor, final String pattern,
	                                                  final int count) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(Keyword.Scan.MATCH, pattern)
				.add(Keyword.Common.COUNT, count);
		return hStringScan(rawBinaryKey(key), cursor, new LettuceScanArgs(pattern, count), args);
	}

	@Override
	public ScanResult<KeyValue<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor, final byte[] pattern,
	                                                  final int count) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(Keyword.Scan.MATCH, pattern)
				.add(Keyword.Common.COUNT, count);
		return hBinaryScan(rawKey(key), cursor, new LettuceScanArgs(pattern, count), args);
	}

	@Override
	public ScanResult<KeyValue<String, String>> hScan(final String key, final String cursor, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(Keyword.Common.COUNT, count);
		return hStringScan(rawBinaryKey(key), cursor, new LettuceScanArgs(count), args);
	}

	@Override
	public ScanResult<KeyValue<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(Keyword.Common.COUNT, count);
		return hBinaryScan(rawKey(key), cursor, new LettuceScanArgs(count), args);
	}

	@Override
	public ScanResult<String> hScanNoValues(final String key, final String cursor) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add("NOVALUES");
		return executeCommand(RedisCommand.SCAN, args,
				(cmd)->cmd.hscanNovalues(rawBinaryKey(key), new LettuceScanCursor(cursor)),
				new ScanCursorConverter.KeyScanCursorConverter<>(SafeEncoder::encode));
	}

	@Override
	public ScanResult<byte[]> hScanNoValues(final byte[] key, final byte[] cursor) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add("NOVALUES");
		return executeCommand(RedisCommand.SCAN, args,
				(cmd)->cmd.hscanNovalues(rawKey(key), new LettuceScanCursor(cursor)),
				new ScanCursorConverter.KeyScanCursorConverter<>((v)->v));
	}

	@Override
	public ScanResult<String> hScanNoValues(final String key, final String cursor, final String pattern) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(Keyword.Scan.MATCH, pattern)
				.add("NOVALUES");
		return hStringScanNoValues(rawBinaryKey(key), cursor, new LettuceScanArgs(pattern), args);
	}

	@Override
	public ScanResult<byte[]> hScanNoValues(final byte[] key, final byte[] cursor, final byte[] pattern) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(Keyword.Scan.MATCH, pattern)
				.add("NOVALUES");
		return hBinaryScanNoValues(rawKey(key), cursor, new LettuceScanArgs(pattern), args);
	}

	@Override
	public ScanResult<String> hScanNoValues(final String key, final String cursor, final String pattern,
	                                        final int count) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(Keyword.Scan.MATCH, pattern)
				.add(Keyword.Common.COUNT, count).add("NOVALUES");
		return hStringScanNoValues(rawBinaryKey(key), cursor, new LettuceScanArgs(pattern, count), args);
	}

	@Override
	public ScanResult<byte[]> hScanNoValues(final byte[] key, final byte[] cursor, final byte[] pattern,
	                                        final int count) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(Keyword.Scan.MATCH, pattern)
				.add(Keyword.Common.COUNT, count).add("NOVALUES");
		return hBinaryScanNoValues(rawKey(key), cursor, new LettuceScanArgs(pattern, count), args);
	}

	@Override
	public ScanResult<String> hScanNoValues(final String key, final String cursor, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(Keyword.Common.COUNT, count)
				.add("NOVALUES");
		return hStringScanNoValues(rawBinaryKey(key), cursor, new LettuceScanArgs(count), args);
	}

	@Override
	public ScanResult<byte[]> hScanNoValues(final byte[] key, final byte[] cursor, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(Keyword.Common.COUNT, count)
				.add("NOVALUES");
		return hBinaryScanNoValues(rawKey(key), cursor, new LettuceScanArgs(count), args);
	}

	@SuppressWarnings({"unchecked"})
	@Override
	public Long hSet(final String key, final KeyValue<String, String>... data) {
		final CommandArguments args = CommandArguments.create(key).add(data);
		final ArrayKeyValueMapConverter<String, String, byte[], byte[]> arrayKeyValueMapConverter =
				new ArrayKeyValueMapConverter<>(SafeEncoder::encode, SafeEncoder::encode);
		return executeCommand(RedisCommand.HSET, args,
				(cmd)->cmd.hset(rawBinaryKey(key), arrayKeyValueMapConverter.convert(data)));
	}

	@SuppressWarnings({"unchecked"})
	@Override
	public Long hSet(final byte[] key, final KeyValue<byte[], byte[]>... data) {
		final CommandArguments args = CommandArguments.create(key).add(data);
		final ArrayKeyValueMapConverter<byte[], byte[], byte[], byte[]> arrayKeyValueMapConverter = new ArrayKeyValueMapConverter<>(
				(k)->k, (v)->v);
		return executeCommand(RedisCommand.HSET, args,
				(cmd)->cmd.hset(rawKey(key), arrayKeyValueMapConverter.convert(data)));
	}

	@SuppressWarnings({"unchecked"})
	@Override
	public Status hSetEx(final String key, final KeyValue<String, String>... data) {
		final CommandArguments args = CommandArguments.create(key).add(data);
		final ArrayKeyValueMapConverter<String, String, byte[], byte[]> arrayKeyValueMapConverter =
				new ArrayKeyValueMapConverter<>(SafeEncoder::encode, SafeEncoder::encode);
		return executeCommand(RedisCommand.HSET, args,
				(cmd)->cmd.hsetex(rawBinaryKey(key), arrayKeyValueMapConverter.convert(data)),
				new OneStatusConverter());
	}

	@SuppressWarnings({"unchecked"})
	@Override
	public Status hSetEx(final byte[] key, final KeyValue<byte[], byte[]>... data) {
		final CommandArguments args = CommandArguments.create(key).add(data);
		final ArrayKeyValueMapConverter<byte[], byte[], byte[], byte[]> arrayKeyValueMapConverter = new ArrayKeyValueMapConverter<>(
				(k)->k, (v)->v);
		return executeCommand(RedisCommand.HSET, args,
				(cmd)->cmd.hsetex(rawKey(key), arrayKeyValueMapConverter.convert(data)),
				new OneStatusConverter());
	}

	@SuppressWarnings({"unchecked"})
	@Override
	public Status hSetEx(final String key, final FnxFxx fnxFxx, final KeyValue<String, String>... data) {
		final CommandArguments args = CommandArguments.create(key).add(fnxFxx).add(data);
		return hStringSetEx(rawBinaryKey(key), new LettuceHSetExArgs(fnxFxx), data, args);
	}

	@SuppressWarnings({"unchecked"})
	@Override
	public Status hSetEx(final byte[] key, final FnxFxx fnxFxx, final KeyValue<byte[], byte[]>... data) {
		final CommandArguments args = CommandArguments.create(key).add(fnxFxx).add(data);
		return hBinarySetEx(rawKey(key), new LettuceHSetExArgs(fnxFxx), data, args);
	}

	@SuppressWarnings({"unchecked"})
	@Override
	public Status hSetEx(final String key, final FnxFxx fnxFxx, final PxExType exType, final long expires,
	                     final KeyValue<String, String>... data) {
		final CommandArguments args = CommandArguments.create(key).add(fnxFxx).add(exType, expires).add(data);
		return hStringSetEx(rawBinaryKey(key), new LettuceHSetExArgs(fnxFxx, exType, expires), data, args);
	}

	@SuppressWarnings({"unchecked"})
	@Override
	public Status hSetEx(final byte[] key, final FnxFxx fnxFxx, final PxExType exType, final long expires,
	                     final KeyValue<byte[], byte[]>... data) {
		final CommandArguments args = CommandArguments.create(key).add(fnxFxx).add(exType, expires).add(data);
		return hBinarySetEx(rawKey(key), new LettuceHSetExArgs(fnxFxx, exType, expires), data, args);
	}

	@SuppressWarnings({"unchecked"})
	@Override
	public Status hSetEx(final String key, final PxExType exType, final long expires,
	                     final KeyValue<String, String>... data) {
		final CommandArguments args = CommandArguments.create(key).add(exType, expires).add(data);
		return hStringSetEx(rawBinaryKey(key), new LettuceHSetExArgs(exType, expires), data, args);
	}

	@SuppressWarnings({"unchecked"})
	@Override
	public Status hSetEx(final byte[] key, final PxExType exType, final long expires,
	                     final KeyValue<byte[], byte[]>... data) {
		final CommandArguments args = CommandArguments.create(key).add(exType, expires).add(data);
		return hBinarySetEx(rawKey(key), new LettuceHSetExArgs(exType, expires), data, args);
	}

	@Override
	public Status hSetNx(final String key, final String field, final String value) {
		final CommandArguments args = CommandArguments.create(key).add(field, value);
		return executeCommand(RedisCommand.HSETNX, args,
				(cmd)->cmd.hsetnx(rawBinaryKey(key), SafeEncoder.encode(field), SafeEncoder.encode(value)),
				new BooleanStatusConverter());
	}

	@Override
	public Status hSetNx(final byte[] key, final byte[] field, final byte[] value) {
		final CommandArguments args = CommandArguments.create(key).add(field, value);
		return executeCommand(RedisCommand.HSETNX, args, (cmd)->cmd.hsetnx(rawKey(key), field, value),
				new BooleanStatusConverter());
	}

	@Override
	public Long hStrLen(final String key, final String field) {
		final CommandArguments args = CommandArguments.create(key).add(field);
		return executeCommand(
				RedisCommand.HSTRLEN, args, (cmd)->cmd.hstrlen(rawBinaryKey(key), SafeEncoder.encode(field)));
	}

	@Override
	public Long hStrLen(final byte[] key, final byte[] field) {
		final CommandArguments args = CommandArguments.create(key).add(field);
		return executeCommand(RedisCommand.HSTRLEN, args, (cmd)->cmd.hstrlen(rawKey(key), field));
	}

	@Override
	public List<Long> hTtl(final String key, final String... fields) {
		final CommandArguments args = CommandArguments.create(key).add(Keyword.Hash.FIELDS, fields.length).add(fields);
		return executeCommand(RedisCommand.HTTL, args, (cmd)->cmd.httl(rawBinaryKey(key), SafeEncoder.encode(fields)));
	}

	@Override
	public List<Long> hTtl(final byte[] key, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(Keyword.Hash.FIELDS, fields.length).add(fields);
		return executeCommand(RedisCommand.HTTL, args, (cmd)->cmd.httl(rawKey(key), fields));
	}

	@Override
	public List<String> hVals(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.HVALS, args, (cmd)->cmd.hvals(rawBinaryKey(key)),
				new BinaryListStringListConverter());
	}

	@Override
	public List<byte[]> hVals(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.HVALS, args, (cmd)->cmd.hvals(rawKey(key)), (v)->v);
	}

	private ScanResult<KeyValue<String, String>> hStringScan(final byte[] key, final String cursor,
	                                                         final ScanArgs scanArgs, final CommandArguments args) {
		return executeCommand(RedisCommand.SCAN, args, (cmd)->cmd.hscan(key, new LettuceScanCursor(cursor), scanArgs),
				new ScanCursorConverter.MapScanCursorConverter<>(SafeEncoder::encode, SafeEncoder::encode));
	}

	private <V> ScanResult<KeyValue<byte[], byte[]>> hBinaryScan(final byte[] key, final byte[] cursor,
	                                                             final ScanArgs scanArgs, final CommandArguments args) {
		return executeCommand(RedisCommand.SCAN, args, (cmd)->cmd.hscan(key, new LettuceScanCursor(cursor), scanArgs),
				new ScanCursorConverter.MapScanCursorConverter<>((k)->k, (v)->v));
	}

	private ScanResult<String> hStringScanNoValues(final byte[] key, final String cursor,
	                                               final ScanArgs scanArgs, final CommandArguments args) {
		return executeCommand(RedisCommand.SCAN, args, (cmd)->cmd.hscanNovalues(key,
						new LettuceScanCursor(cursor), scanArgs),
				new ScanCursorConverter.KeyScanCursorConverter<>(SafeEncoder::encode));
	}

	private ScanResult<byte[]> hBinaryScanNoValues(final byte[] key, final byte[] cursor, final ScanArgs scanArgs,
	                                               final CommandArguments args) {
		return executeCommand(RedisCommand.SCAN, args,
				(cmd)->cmd.hscanNovalues(rawKey(key), new LettuceScanCursor(cursor),
						scanArgs), new ScanCursorConverter.KeyScanCursorConverter<>((v)->v));
	}

	private Status hStringSetEx(final byte[] key, final HSetExArgs hSetExArgs, final KeyValue<String, String>[] data,
	                            final CommandArguments args) {
		final ArrayKeyValueMapConverter<String, String, byte[], byte[]> arrayKeyValueMapConverter =
				new ArrayKeyValueMapConverter<>(SafeEncoder::encode, SafeEncoder::encode);
		return executeCommand(RedisCommand.HSETEX, args, (cmd)->cmd.hsetex(key, hSetExArgs,
				arrayKeyValueMapConverter.convert(data)), new OneStatusConverter());
	}

	private Status hBinarySetEx(final byte[] key, final HSetExArgs hSetExArgs, final KeyValue<byte[], byte[]>[] data,
	                            final CommandArguments args) {
		final ArrayKeyValueMapConverter<byte[], byte[], byte[], byte[]> arrayKeyValueMapConverter = new ArrayKeyValueMapConverter<>(
				(k)->k, (v)->v);
		return executeCommand(RedisCommand.HSETEX, args, (cmd)->cmd.hsetex(key, hSetExArgs,
				arrayKeyValueMapConverter.convert(data)), new OneStatusConverter());
	}

}
