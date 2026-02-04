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

import com.buession.core.converter.ListConverter;
import com.buession.lang.Status;
import com.buession.redis.client.lettuce.LettuceRedisClient;
import com.buession.redis.client.operations.HashOperations;
import com.buession.redis.core.ExpireOption;
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.command.Command;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.args.GetExArgument;
import com.buession.redis.core.command.args.HSetExArgument;
import com.buession.redis.core.internal.convert.Converters;
import com.buession.redis.core.internal.convert.lettuce.response.ScanCursorConverter;
import com.buession.redis.core.internal.lettuce.CompositeArgumentUtils;
import com.buession.redis.core.internal.lettuce.LettuceScanArgs;
import com.buession.redis.core.internal.lettuce.LettuceScanCursor;
import com.buession.redis.utils.SafeEncoder;
import io.lettuce.core.KeyValue;
import io.lettuce.core.MapScanCursor;

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
		return LettuceCommandBuilder.<Long, Long>newBuilder(client, Command.HDEL).executor((cmd)->cmd.hdel(key, fields))
				.arguments(args).converter((v)->v).run();
	}

	@Override
	public Boolean hExists(final String key, final String field) {
		return hExists(SafeEncoder.encode(key), SafeEncoder.encode(field));
	}

	@Override
	public Boolean hExists(final byte[] key, final byte[] field) {
		final CommandArguments args = CommandArguments.create(key).add(field);
		return LettuceCommandBuilder.<Boolean, Boolean>newBuilder(client, Command.HEXISTS)
				.executor((cmd)->cmd.hexists(key, field)).arguments(args).converter((v)->v).run();
	}

	@Override
	public List<Long> hExpire(final String key, final long ttl, final String... fields) {
		return hExpire(SafeEncoder.encode(key), ttl, SafeEncoder.encode(fields));
	}

	@Override
	public List<Long> hExpire(final byte[] key, final long ttl, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(ttl).add("FIELDS").add(fields.length)
				.add(fields);
		return LettuceCommandBuilder.<List<Long>, List<Long>>newBuilder(client, Command.HEXPIRE)
				.executor((cmd)->cmd.hexpire(key, ttl, fields)).arguments(args).converter((v)->v).run();
	}

	@Override
	public List<Long> hExpire(final String key, final long ttl, final ExpireOption option, final String... fields) {
		return hExpire(SafeEncoder.encode(key), ttl, option, SafeEncoder.encode(fields));
	}

	@Override
	public List<Long> hExpire(final byte[] key, final long ttl, final ExpireOption option, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(ttl).add(option).add("FIELDS").add(fields.length)
				.add(fields);
		return LettuceCommandBuilder.<List<Long>, List<Long>>newBuilder(client, Command.HEXPIRE)
				.executor((cmd)->cmd.hexpire(key, ttl, CompositeArgumentUtils.expireArgs(option), fields))
				.arguments(args).converter((v)->v).run();
	}

	@Override
	public List<Long> hExpireAt(final String key, final long unixTimestamp, final String... fields) {
		return hExpireAt(SafeEncoder.encode(key), unixTimestamp, SafeEncoder.encode(fields));
	}

	@Override
	public List<Long> hExpireAt(final byte[] key, final long unixTimestamp, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(unixTimestamp).add("FIELDS").add(fields.length)
				.add(fields);
		return LettuceCommandBuilder.<List<Long>, List<Long>>newBuilder(client, Command.HEXPIREAT)
				.executor((cmd)->cmd.hexpireat(key, unixTimestamp, fields)).arguments(args).converter((v)->v).run();
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
		return LettuceCommandBuilder.<List<Long>, List<Long>>newBuilder(client, Command.HEXPIREAT)
				.executor((cmd)->cmd.hexpireat(key, unixTimestamp, CompositeArgumentUtils.expireArgs(option), fields))
				.arguments(args).converter((v)->v).run();
	}

	@Override
	public List<Long> hExpireTime(final String key, final String... fields) {
		return hExpireTime(SafeEncoder.encode(key), SafeEncoder.encode(fields));
	}

	@Override
	public List<Long> hExpireTime(final byte[] key, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add("FIELDS").add(fields.length).add(fields);
		return LettuceCommandBuilder.<List<Long>, List<Long>>newBuilder(client, Command.HEXPIRETIME)
				.executor((cmd)->cmd.hexpiretime(key, fields)).arguments(args).converter((v)->v).run();
	}

	@Override
	public String hGet(final String key, final String field) {
		final CommandArguments args = CommandArguments.create(key).add(field);
		return LettuceCommandBuilder.<byte[], String>newBuilder(client, Command.HGET)
				.executor((cmd)->cmd.hget(SafeEncoder.encode(key), SafeEncoder.encode(field))).arguments(args)
				.converter(Converters.binaryToStringConverter()).run();
	}

	@Override
	public byte[] hGet(final byte[] key, final byte[] field) {
		final CommandArguments args = CommandArguments.create(key).add(field);
		return LettuceCommandBuilder.<byte[], byte[]>newBuilder(client, Command.HGET)
				.executor((cmd)->cmd.hget(key, field)).arguments(args).converter((v)->v).run();
	}

	@Override
	public Map<String, String> hGetAll(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return LettuceCommandBuilder.<Map<byte[], byte[]>, Map<String, String>>newBuilder(client, Command.HGETALL)
				.executor((cmd)->cmd.hgetall(SafeEncoder.encode(key))).arguments(args)
				.converter(Converters.binaryMapToStringMapConverter()).run();
	}

	@Override
	public Map<byte[], byte[]> hGetAll(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return LettuceCommandBuilder.<Map<byte[], byte[]>, Map<byte[], byte[]>>newBuilder(client, Command.HGETALL)
				.executor((cmd)->cmd.hgetall(key)).arguments(args).converter((v)->v).run();
	}

	@Override
	public List<String> hGetDel(final String key, final String... fields) {
		final CommandArguments args = CommandArguments.create(key).add(fields);
		return LettuceCommandBuilder.<List<KeyValue<byte[], byte[]>>, List<String>>newBuilder(client, Command.HGETDEL)
				.executor((cmd)->cmd.hgetdel(SafeEncoder.encode(key), SafeEncoder.encode(fields))).arguments(args)
				.converter(new ListConverter<>(binaryKeyValueToStringValueConverter)).run();
	}

	@Override
	public List<byte[]> hGetDel(final byte[] key, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(fields);
		return LettuceCommandBuilder.<List<KeyValue<byte[], byte[]>>, List<byte[]>>newBuilder(client, Command.HGETDEL)
				.executor((cmd)->cmd.hgetdel(key, fields)).arguments(args)
				.converter(new ListConverter<>(binaryKeyValueToBinaryValueConverter)).run();
	}

	@Override
	public List<String> hGetEx(final String key, final String... fields) {
		final CommandArguments args = CommandArguments.create(key).add(fields);
		return LettuceCommandBuilder.<List<KeyValue<byte[], byte[]>>, List<String>>newBuilder(client, Command.HGETEX)
				.executor((cmd)->cmd.hgetex(SafeEncoder.encode(key), SafeEncoder.encode(fields))).arguments(args)
				.converter(new ListConverter<>(binaryKeyValueToStringValueConverter)).run();
	}

	@Override
	public List<byte[]> hGetEx(final byte[] key, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(fields);
		return LettuceCommandBuilder.<List<KeyValue<byte[], byte[]>>, List<byte[]>>newBuilder(client, Command.HGETEX)
				.executor((cmd)->cmd.hgetex(key, fields)).arguments(args)
				.converter(new ListConverter<>(binaryKeyValueToBinaryValueConverter)).run();
	}

	@Override
	public List<String> hGetEx(final String key, final GetExArgument argument, final String... fields) {
		final CommandArguments args = CommandArguments.create(key).add(argument).add(fields);
		return LettuceCommandBuilder.<List<KeyValue<byte[], byte[]>>, List<String>>newBuilder(client, Command.HGETEX)
				.executor((cmd)->cmd.hgetex(SafeEncoder.encode(key), CompositeArgumentUtils.hGetExArgs(argument),
						SafeEncoder.encode(fields))).arguments(args)
				.converter(new ListConverter<>(binaryKeyValueToStringValueConverter)).run();
	}

	@Override
	public List<byte[]> hGetEx(final byte[] key, final GetExArgument argument, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(argument).add(fields);
		return LettuceCommandBuilder.<List<KeyValue<byte[], byte[]>>, List<byte[]>>newBuilder(client, Command.HGETEX)
				.executor((cmd)->cmd.hgetex(key, CompositeArgumentUtils.hGetExArgs(argument), fields)).arguments(args)
				.converter(new ListConverter<>(binaryKeyValueToBinaryValueConverter)).run();
	}

	@Override
	public Long hIncrBy(final String key, final String field, final long value) {
		return hIncrBy(SafeEncoder.encode(key), SafeEncoder.encode(field), value);
	}

	@Override
	public Long hIncrBy(final byte[] key, final byte[] field, final long value) {
		final CommandArguments args = CommandArguments.create(key).add(field).add(value);
		return LettuceCommandBuilder.<Long, Long>newBuilder(client, Command.HINCRBY)
				.executor((cmd)->cmd.hincrby(key, field, value)).arguments(args).converter((v)->v).run();
	}

	@Override
	public Double hIncrByFloat(final String key, final String field, final double value) {
		return hIncrByFloat(SafeEncoder.encode(key), SafeEncoder.encode(field), value);
	}

	@Override
	public Double hIncrByFloat(final byte[] key, final byte[] field, final double value) {
		final CommandArguments args = CommandArguments.create(key).add(field).add(value);
		return LettuceCommandBuilder.<Double, Double>newBuilder(client, Command.HINCRBYFLOAT)
				.executor((cmd)->cmd.hincrbyfloat(key, field, value)).arguments(args).converter((v)->v).run();
	}

	@Override
	public Set<String> hKeys(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return LettuceCommandBuilder.<List<byte[]>, Set<String>>newBuilder(client, Command.HKEYS)
				.executor((cmd)->cmd.hkeys(SafeEncoder.encode(key))).arguments(args)
				.converter(Converters.binaryListToStringSetConverter()).run();
	}

	@Override
	public Set<byte[]> hKeys(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return LettuceCommandBuilder.<List<byte[]>, Set<byte[]>>newBuilder(client, Command.HKEYS)
				.executor((cmd)->cmd.hkeys(key)).arguments(args).converter(Converters.binaryListToBinarySetConverter())
				.run();
	}

	@Override
	public Long hLen(final String key) {
		return hLen(SafeEncoder.encode(key));
	}

	@Override
	public Long hLen(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return LettuceCommandBuilder.<Long, Long>newBuilder(client, Command.HLEN).executor((cmd)->cmd.hlen(key))
				.arguments(args).converter((v)->v).run();
	}

	@Override
	public List<String> hMGet(final String key, final String... fields) {
		final CommandArguments args = CommandArguments.create(key).add(fields);
		return LettuceCommandBuilder.<List<KeyValue<byte[], byte[]>>, List<String>>newBuilder(client, Command.HMGET)
				.executor((cmd)->cmd.hmget(SafeEncoder.encode(key), SafeEncoder.encode(fields))).arguments(args)
				.converter(new ListConverter<>(binaryKeyValueToStringValueConverter)).run();
	}

	@Override
	public List<byte[]> hMGet(final byte[] key, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(fields);
		return LettuceCommandBuilder.<List<KeyValue<byte[], byte[]>>, List<byte[]>>newBuilder(client, Command.HMGET)
				.executor((cmd)->cmd.hmget(key, fields)).arguments(args)
				.converter(new ListConverter<>(binaryKeyValueToBinaryValueConverter)).run();
	}

	@Override
	public Status hMSet(final String key, final Map<String, String> data) {
		return hMSet(SafeEncoder.encode(key), Converters.stringMapToBinaryMapConverter().convert(data));
	}

	@Override
	public Status hMSet(final byte[] key, final Map<byte[], byte[]> data) {
		final CommandArguments args = CommandArguments.create(key).add(data);
		return LettuceCommandBuilder.<String, Status>newBuilder(client, Command.HMSET)
				.executor((cmd)->cmd.hmset(key, data)).arguments(args).converter(Converters.okStatusConverter()).run();
	}

	@Override
	public List<Long> hPersist(final String key, final String... fields) {
		return hPersist(SafeEncoder.encode(key), SafeEncoder.encode(fields));
	}

	@Override
	public List<Long> hPersist(final byte[] key, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(fields);
		return LettuceCommandBuilder.<List<Long>, List<Long>>newBuilder(client, Command.HPERSIST)
				.executor((cmd)->cmd.hpersist(key, fields)).arguments(args).converter((v)->v).run();
	}

	@Override
	public List<Long> hPExpire(final String key, final long ttl, final String... fields) {
		return hPExpire(SafeEncoder.encode(key), ttl, SafeEncoder.encode(fields));
	}

	@Override
	public List<Long> hPExpire(final byte[] key, final long ttl, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(ttl).add("FIELDS").add(fields.length)
				.add(fields);
		return LettuceCommandBuilder.<List<Long>, List<Long>>newBuilder(client, Command.HPEXPIRE)
				.executor((cmd)->cmd.hpexpire(key, ttl, fields)).arguments(args).converter((v)->v).run();
	}

	@Override
	public List<Long> hPExpire(final String key, final long ttl, final ExpireOption option, final String... fields) {
		return hPExpire(SafeEncoder.encode(key), ttl, option, SafeEncoder.encode(fields));
	}

	@Override
	public List<Long> hPExpire(final byte[] key, final long ttl, final ExpireOption option, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(ttl).add(option).add("FIELDS").add(fields.length)
				.add(fields);
		return LettuceCommandBuilder.<List<Long>, List<Long>>newBuilder(client, Command.HPEXPIRE)
				.executor((cmd)->cmd.hpexpire(key, ttl, CompositeArgumentUtils.expireArgs(option), fields))
				.arguments(args).converter((v)->v).run();
	}

	@Override
	public List<Long> hPExpireAt(final String key, final long unixTimestamp, final String... fields) {
		return hPExpireAt(SafeEncoder.encode(key), unixTimestamp, SafeEncoder.encode(fields));
	}

	@Override
	public List<Long> hPExpireAt(final byte[] key, final long unixTimestamp, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(unixTimestamp).add("FIELDS").add(fields.length)
				.add(fields);
		return LettuceCommandBuilder.<List<Long>, List<Long>>newBuilder(client, Command.HPEXPIREAT)
				.executor((cmd)->cmd.hpexpireat(key, unixTimestamp, fields)).arguments(args).converter((v)->v).run();
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
		return LettuceCommandBuilder.<List<Long>, List<Long>>newBuilder(client, Command.HPEXPIREAT)
				.executor((cmd)->cmd.hpexpireat(key, unixTimestamp, CompositeArgumentUtils.expireArgs(option), fields))
				.arguments(args).converter((v)->v).run();
	}

	@Override
	public List<Long> hPExpireTime(final String key, final String... fields) {
		return hPExpireTime(SafeEncoder.encode(key), SafeEncoder.encode(fields));
	}

	@Override
	public List<Long> hPExpireTime(final byte[] key, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add("FIELDS").add(fields.length).add(fields);
		return LettuceCommandBuilder.<List<Long>, List<Long>>newBuilder(client, Command.HPEXPIRETIME)
				.executor((cmd)->cmd.hpexpiretime(key, fields)).arguments(args).converter((v)->v).run();
	}

	@Override
	public List<Long> hPTtl(final String key, final String... fields) {
		return hPTtl(SafeEncoder.encode(key), SafeEncoder.encode(fields));
	}

	@Override
	public List<Long> hPTtl(final byte[] key, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add("FIELDS").add(fields.length).add(fields);
		return LettuceCommandBuilder.<List<Long>, List<Long>>newBuilder(client, Command.HPTTL)
				.executor((cmd)->cmd.hpttl(key, fields)).arguments(args).converter((v)->v).run();
	}

	@Override
	public String hRandField(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return LettuceCommandBuilder.<byte[], String>newBuilder(client, Command.HRANDFIELD)
				.executor((cmd)->cmd.hrandfield(SafeEncoder.encode(key))).arguments(args)
				.converter(Converters.binaryToStringConverter()).run();
	}

	@Override
	public byte[] hRandField(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return LettuceCommandBuilder.<byte[], byte[]>newBuilder(client, Command.HRANDFIELD)
				.executor((cmd)->cmd.hrandfield(key)).arguments(args).converter((v)->v).run();
	}

	@Override
	public List<String> hRandField(final String key, final long count) {
		final CommandArguments args = CommandArguments.create(key).add(count);
		return LettuceCommandBuilder.<List<byte[]>, List<String>>newBuilder(client, Command.HRANDFIELD)
				.executor((cmd)->cmd.hrandfield(SafeEncoder.encode(key), count)).arguments(args)
				.converter(Converters.binaryListToStringListConverter()).run();
	}

	@Override
	public List<byte[]> hRandField(final byte[] key, final long count) {
		final CommandArguments args = CommandArguments.create(key).add(count);
		return LettuceCommandBuilder.<List<byte[]>, List<byte[]>>newBuilder(client, Command.HRANDFIELD)
				.executor((cmd)->cmd.hrandfield(key, count)).arguments(args).converter((v)->v).run();
	}

	@Override
	public Map<String, String> hRandFieldWithValues(final String key, final long count) {
		final CommandArguments args = CommandArguments.create(key).add(count).add("WITHVALUES");
		return LettuceCommandBuilder.<List<KeyValue<byte[], byte[]>>, Map<String, String>>newBuilder(client,
						Command.HRANDFIELD).executor((cmd)->cmd.hrandfieldWithvalues(SafeEncoder.encode(key), count))
				.arguments(args).converter(binaryListKeyValueToStringMapConverter).run();
	}

	@Override
	public Map<byte[], byte[]> hRandFieldWithValues(final byte[] key, final long count) {
		final CommandArguments args = CommandArguments.create(key).add(count).add("WITHVALUES");
		return LettuceCommandBuilder.<List<KeyValue<byte[], byte[]>>, Map<byte[], byte[]>>newBuilder(client,
						Command.HRANDFIELD).executor((cmd)->cmd.hrandfieldWithvalues(key, count)).arguments(args)
				.converter(binaryListKeyValueToBinaryMapConverter).run();
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final String cursor) {
		final CommandArguments args = CommandArguments.create(key).add(cursor);
		return hStringScan((cmd)->cmd.hscan(SafeEncoder.encode(key), new LettuceScanCursor(SafeEncoder.encode(cursor))),
				args);
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor) {
		final CommandArguments args = CommandArguments.create(key).add(cursor);
		return hBinaryScan((cmd)->cmd.hscan(key, new LettuceScanCursor(cursor)), args);
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final String cursor, final String pattern) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(pattern);
		return hStringScan((cmd)->cmd.hscan(SafeEncoder.encode(key), new LettuceScanCursor(SafeEncoder.encode(cursor)),
				new LettuceScanArgs(pattern)), args);
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor, final byte[] pattern) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(pattern);
		return hBinaryScan((cmd)->cmd.hscan(key, new LettuceScanCursor(cursor), new LettuceScanArgs(pattern)), args);
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final String cursor, final long count) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(count);
		return hStringScan((cmd)->cmd.hscan(SafeEncoder.encode(key), new LettuceScanCursor(SafeEncoder.encode(cursor)),
				new LettuceScanArgs(count)), args);
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor, final long count) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(count);
		return hBinaryScan((cmd)->cmd.hscan(key, new LettuceScanCursor(cursor), new LettuceScanArgs(count)), args);
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final String cursor, final String pattern,
												 final long count) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(pattern);
		return hStringScan((cmd)->cmd.hscan(SafeEncoder.encode(key), new LettuceScanCursor(SafeEncoder.encode(cursor)),
				new LettuceScanArgs(pattern, count)), args);
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor, final byte[] pattern,
												 final long count) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(pattern).add(count);
		return hBinaryScan((cmd)->cmd.hscan(key, new LettuceScanCursor(cursor), new LettuceScanArgs(pattern, count)),
				args);
	}

	@Override
	public Long hSet(final String key, final Map<String, String> data) {
		return hSet(SafeEncoder.encode(key), Converters.stringMapToBinaryMapConverter().convert(data));
	}

	@Override
	public Long hSet(final byte[] key, final Map<byte[], byte[]> data) {
		final CommandArguments args = CommandArguments.create(key).add(data);
		return LettuceCommandBuilder.<Long, Long>newBuilder(client, Command.HSET).executor((cmd)->cmd.hset(key, data))
				.arguments(args).converter((v)->v).run();
	}

	@Override
	public Status hSetEx(final String key, final Map<String, String> data) {
		return hSetEx(SafeEncoder.encode(key), Converters.stringMapToBinaryMapConverter().convert(data));
	}

	@Override
	public Status hSetEx(final byte[] key, final Map<byte[], byte[]> data) {
		final CommandArguments args = CommandArguments.create(key).add(data);
		return LettuceCommandBuilder.<Long, Status>newBuilder(client, Command.HSET)
				.executor((cmd)->cmd.hsetex(key, data))
				.arguments(args).converter(Converters.oneStatusConverter()).run();
	}

	@Override
	public Status hSetEx(final String key, final Map<String, String> data, final HSetExArgument argument) {
		return hSetEx(SafeEncoder.encode(key), Converters.stringMapToBinaryMapConverter().convert(data), argument);
	}

	@Override
	public Status hSetEx(final byte[] key, final Map<byte[], byte[]> data, final HSetExArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(argument).add(data);
		return LettuceCommandBuilder.<Long, Status>newBuilder(client, Command.HSET)
				.executor((cmd)->cmd.hsetex(key, CompositeArgumentUtils.hSetExArgs(argument), data))
				.arguments(args).converter(Converters.oneStatusConverter()).run();
	}

	@Override
	public Status hSetNx(final String key, final String field, final String value) {
		return hSetNx(SafeEncoder.encode(key), SafeEncoder.encode(field), SafeEncoder.encode(value));
	}

	@Override
	public Status hSetNx(final byte[] key, final byte[] field, final byte[] value) {
		final CommandArguments args = CommandArguments.create(key).add(field).add(value);
		return LettuceCommandBuilder.<Boolean, Status>newBuilder(client, Command.HSETNX)
				.executor((cmd)->cmd.hsetnx(key, field, value))
				.arguments(args).converter(Converters.booleanStatusConverter()).run();
	}

	@Override
	public Long hStrLen(final String key, final String field) {
		return hStrLen(SafeEncoder.encode(key), SafeEncoder.encode(field));
	}

	@Override
	public Long hStrLen(final byte[] key, final byte[] field) {
		final CommandArguments args = CommandArguments.create(key).add(field);
		return LettuceCommandBuilder.<Long, Long>newBuilder(client, Command.HSTRLEN)
				.executor((cmd)->cmd.hstrlen(key, field)).arguments(args).converter((v)->v).run();
	}

	@Override
	public List<Long> hTtl(final String key, final String... fields) {
		return hTtl(SafeEncoder.encode(key), SafeEncoder.encode(fields));
	}

	@Override
	public List<Long> hTtl(final byte[] key, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(fields);
		return LettuceCommandBuilder.<List<Long>, List<Long>>newBuilder(client, Command.HTTL)
				.executor((cmd)->cmd.httl(key, fields)).arguments(args).converter((v)->v).run();
	}

	@Override
	public List<String> hVals(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return LettuceCommandBuilder.<List<byte[]>, List<String>>newBuilder(client, Command.HVALS)
				.executor((cmd)->cmd.hvals(SafeEncoder.encode(key))).arguments(args)
				.converter(Converters.binaryListToStringListConverter()).run();
	}

	@Override
	public List<byte[]> hVals(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return LettuceCommandBuilder.<List<byte[]>, List<byte[]>>newBuilder(client, Command.HVALS)
				.executor((cmd)->cmd.hvals(key)).arguments(args).converter((v)->v).run();
	}

	private ScanResult<Map<String, String>> hStringScan(
			final com.buession.redis.core.Command.Executor<io.lettuce.core.RedisCommands<byte[], byte[]>, MapScanCursor<byte[], byte[]>> executor,
			final CommandArguments args) {
		return LettuceCommandBuilder.<MapScanCursor<byte[], byte[]>, ScanResult<Map<String, String>>>newBuilder(client,
						Command.HRANDFIELD).executor(executor).arguments(args)
				.converter(new ScanCursorConverter.MapScanCursorConverter.BvSvMapScanCursorConverter()).run();
	}

	private ScanResult<Map<byte[], byte[]>> hBinaryScan(
			final com.buession.redis.core.Command.Executor<io.lettuce.core.RedisCommands<byte[], byte[]>, MapScanCursor<byte[], byte[]>> executor,
			final CommandArguments args) {
		return LettuceCommandBuilder.<MapScanCursor<byte[], byte[]>, ScanResult<Map<byte[], byte[]>>>newBuilder(client,
						Command.HRANDFIELD).executor(executor).arguments(args)
				.converter(new ScanCursorConverter.MapScanCursorConverter<>()).run();
	}

}
