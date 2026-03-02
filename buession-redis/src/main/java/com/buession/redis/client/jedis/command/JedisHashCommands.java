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
package com.buession.redis.client.jedis.command;

import com.buession.core.converter.ListMapEntryMapConverter;
import com.buession.lang.Status;
import com.buession.redis.client.jedis.JedisRedisClient;
import com.buession.redis.core.ExpireOption;
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.command.Command;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.HashCommands;
import com.buession.redis.core.command.args.GetExArgument;
import com.buession.redis.core.command.args.HSetExArgument;
import com.buession.redis.core.internal.convert.jedis.params.ExpireOptionConverter;
import com.buession.redis.core.internal.convert.jedis.params.GetExArgumentHGetExParamsConverter;
import com.buession.redis.core.internal.convert.jedis.params.HSetExArgumentConverter;
import com.buession.redis.core.internal.convert.jedis.response.ScanResultConverter;
import com.buession.redis.core.internal.convert.response.OkStatusConverter;
import com.buession.redis.core.internal.convert.response.OneStatusConverter;
import com.buession.redis.core.internal.jedis.JedisScanParams;
import redis.clients.jedis.UnifiedJedis;
import redis.clients.jedis.params.HGetExParams;
import redis.clients.jedis.params.HSetExParams;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Jedis 哈希表命令
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public final class JedisHashCommands extends AbstractJedisRedisCommands implements HashCommands {

	public JedisHashCommands(final JedisRedisClient client) {
		super(client);
	}

	@Override
	public Long hDel(final String key, final String... fields) {
		final CommandArguments args = CommandArguments.create(key).add(fields);
		return executeCommand(Command.HDEL, args, (cmd)->cmd.hdel(key, fields), (v)->v);
	}

	@Override
	public Long hDel(final byte[] key, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(fields);
		return executeCommand(Command.HDEL, args, (cmd)->cmd.hdel(key, fields), (v)->v);
	}

	@Override
	public Boolean hExists(final String key, final String field) {
		final CommandArguments args = CommandArguments.create(key).add(field);
		return executeCommand(Command.HEXISTS, args, (cmd)->cmd.hexists(key, field), (v)->v);
	}

	@Override
	public Boolean hExists(final byte[] key, final byte[] field) {
		final CommandArguments args = CommandArguments.create(key).add(field);
		return executeCommand(Command.HEXISTS, args, (cmd)->cmd.hexists(key, field), (v)->v);
	}

	@Override
	public List<Long> hExpire(final String key, final long ttl, final String... fields) {
		final CommandArguments args = CommandArguments.create(key).add(ttl).add("FIELDS").add(fields.length)
				.add(fields);
		return hExpire(args, (cmd)->cmd.hexpire(key, ttl, fields));
	}

	@Override
	public List<Long> hExpire(final byte[] key, final long ttl, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(ttl).add("FIELDS").add(fields.length)
				.add(fields);
		return hExpire(args, (cmd)->cmd.hexpire(key, ttl, fields));
	}

	@Override
	public List<Long> hExpire(final String key, final long ttl, final ExpireOption option, final String... fields) {
		final CommandArguments args = CommandArguments.create(key).add(ttl).add(option).add("FIELDS").add(fields.length)
				.add(fields);
		final ExpireOptionConverter optionConverter = new ExpireOptionConverter();
		return hExpire(args, (cmd)->cmd.hexpire(key, ttl, optionConverter.convert(option), fields));
	}

	@Override
	public List<Long> hExpire(final byte[] key, final long ttl, final ExpireOption option, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(ttl).add("FIELDS").add(fields.length)
				.add(fields);
		final ExpireOptionConverter optionConverter = new ExpireOptionConverter();
		return hExpire(args, (cmd)->cmd.hexpire(key, ttl, optionConverter.convert(option), fields));
	}

	@Override
	public List<Long> hExpireAt(final String key, final long unixTimestamp, final String... fields) {
		final CommandArguments args = CommandArguments.create(key).add(unixTimestamp).add("FIELDS").add(fields.length)
				.add(fields);
		return hExpireAt(args, (cmd)->cmd.hexpireAt(key, unixTimestamp, fields));
	}

	@Override
	public List<Long> hExpireAt(final byte[] key, final long unixTimestamp, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(unixTimestamp).add("FIELDS").add(fields.length)
				.add(fields);
		return hExpireAt(args, (cmd)->cmd.hexpireAt(key, unixTimestamp, fields));
	}

	@Override
	public List<Long> hExpireAt(final String key, final long unixTimestamp, final ExpireOption option,
								final String... fields) {
		final CommandArguments args = CommandArguments.create(key).add(unixTimestamp).add(option).add("FIELDS")
				.add(fields.length).add(fields);
		final ExpireOptionConverter optionConverter = new ExpireOptionConverter();
		return hExpireAt(args, (cmd)->cmd.hexpireAt(key, unixTimestamp, optionConverter.convert(option), fields));
	}

	@Override
	public List<Long> hExpireAt(final byte[] key, final long unixTimestamp, final ExpireOption option,
								final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(unixTimestamp).add(option).add("FIELDS")
				.add(fields.length).add(fields);
		final ExpireOptionConverter optionConverter = new ExpireOptionConverter();
		return hExpireAt(args, (cmd)->cmd.hexpireAt(key, unixTimestamp, optionConverter.convert(option), fields));
	}

	@Override
	public List<Long> hExpireTime(final String key, final String... fields) {
		final CommandArguments args = CommandArguments.create(key).add("FIELDS").add(fields.length).add(fields);
		return executeCommand(Command.HEXPIRETIME, args, (cmd)->cmd.hexpireTime(key, fields), (v)->v);
	}

	@Override
	public List<Long> hExpireTime(final byte[] key, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add("FIELDS").add(fields.length).add(fields);
		return executeCommand(Command.HEXPIRETIME, args, (cmd)->cmd.hexpireTime(key, fields), (v)->v);
	}

	@Override
	public String hGet(final String key, final String field) {
		final CommandArguments args = CommandArguments.create(key).add(field);
		return executeCommand(Command.HGET, args, (cmd)->cmd.hget(key, field), (v)->v);
	}

	@Override
	public byte[] hGet(final byte[] key, final byte[] field) {
		final CommandArguments args = CommandArguments.create(key).add(field);
		return executeCommand(Command.HGET, args, (cmd)->cmd.hget(key, field), (v)->v);
	}

	@Override
	public Map<String, String> hGetAll(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.HGETALL, args, (cmd)->cmd.hgetAll(key), (v)->v);
	}

	@Override
	public Map<byte[], byte[]> hGetAll(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.HGETALL, args, (cmd)->cmd.hgetAll(key), (v)->v);
	}

	@Override
	public List<String> hGetDel(final String key, final String... fields) {
		final CommandArguments args = CommandArguments.create(key).add(fields);
		return executeCommand(Command.HGETDEL, args, (cmd)->cmd.hgetdel(key, fields), (v)->v);
	}

	@Override
	public List<byte[]> hGetDel(final byte[] key, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(fields);
		return executeCommand(Command.HGETDEL, args, (cmd)->cmd.hgetdel(key, fields), (v)->v);
	}

	@Override
	public List<String> hGetEx(final String key, final String... fields) {
		final CommandArguments args = CommandArguments.create(key).add(fields);
		return hGetEx(args, (cmd)->cmd.hgetex(key, new HGetExParams(), fields));
	}

	@Override
	public List<byte[]> hGetEx(final byte[] key, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(fields);
		return hGetEx(args, (cmd)->cmd.hgetex(key, new HGetExParams(), fields));
	}

	@Override
	public List<String> hGetEx(final String key, final GetExArgument argument, final String... fields) {
		final CommandArguments args = CommandArguments.create(key).add(argument).add(fields);
		final GetExArgumentHGetExParamsConverter getExArgumentGetExParamsConverter = new GetExArgumentHGetExParamsConverter();
		return hGetEx(args, (cmd)->cmd.hgetex(key, getExArgumentGetExParamsConverter.convert(argument), fields));
	}

	@Override
	public List<byte[]> hGetEx(final byte[] key, final GetExArgument argument, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(argument).add(fields);
		final GetExArgumentHGetExParamsConverter getExArgumentGetExParamsConverter = new GetExArgumentHGetExParamsConverter();
		return hGetEx(args, (cmd)->cmd.hgetex(key, getExArgumentGetExParamsConverter.convert(argument), fields));
	}

	@Override
	public Long hIncrBy(final String key, final String field, final long value) {
		final CommandArguments args = CommandArguments.create(key).add(field).add(value);
		return executeCommand(Command.HINCRBY, args, (cmd)->cmd.hincrBy(key, field, value), (v)->v);
	}

	@Override
	public Long hIncrBy(final byte[] key, final byte[] field, final long value) {
		final CommandArguments args = CommandArguments.create(key).add(field).add(value);
		return executeCommand(Command.HINCRBY, args, (cmd)->cmd.hincrBy(key, field, value), (v)->v);
	}

	@Override
	public Double hIncrByFloat(final String key, final String field, final double value) {
		final CommandArguments args = CommandArguments.create(key).add(field).add(value);
		return executeCommand(Command.HINCRBYFLOAT, args, (cmd)->cmd.hincrByFloat(key, field, value), (v)->v);
	}

	@Override
	public Double hIncrByFloat(final byte[] key, final byte[] field, final double value) {
		final CommandArguments args = CommandArguments.create(key).add(field).add(value);
		return executeCommand(Command.HINCRBYFLOAT, args, (cmd)->cmd.hincrByFloat(key, field, value), (v)->v);
	}

	@Override
	public Set<String> hKeys(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.HKEYS, args, (cmd)->cmd.hkeys(key), (v)->v);
	}

	@Override
	public Set<byte[]> hKeys(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.HKEYS, args, (cmd)->cmd.hkeys(key), (v)->v);
	}

	@Override
	public Long hLen(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.HLEN, args, (cmd)->cmd.hlen(key), (v)->v);
	}

	@Override
	public Long hLen(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.HLEN, args, (cmd)->cmd.hlen(key), (v)->v);
	}

	@Override
	public List<String> hMGet(final String key, final String... fields) {
		final CommandArguments args = CommandArguments.create(key).add(fields);
		return executeCommand(Command.HMGET, args, (cmd)->cmd.hmget(key, fields), (v)->v);
	}

	@Override
	public List<byte[]> hMGet(final byte[] key, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(fields);
		return executeCommand(Command.HMGET, args, (cmd)->cmd.hmget(key, fields), (v)->v);
	}

	@Override
	public Status hMSet(final String key, final Map<String, String> data) {
		final CommandArguments args = CommandArguments.create(key).add(data);
		return executeCommand(Command.HMSET, args, (cmd)->cmd.hmset(key, data), new OkStatusConverter());
	}

	@Override
	public Status hMSet(final byte[] key, final Map<byte[], byte[]> data) {
		final CommandArguments args = CommandArguments.create(key).add(data);
		return executeCommand(Command.HMSET, args, (cmd)->cmd.hmset(key, data), new OkStatusConverter());
	}

	@Override
	public List<Long> hPersist(final String key, final String... fields) {
		final CommandArguments args = CommandArguments.create(key).add(fields);
		return executeCommand(Command.HPERSIST, args, (cmd)->cmd.hpersist(key, fields), (v)->v);
	}

	@Override
	public List<Long> hPersist(final byte[] key, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(fields);
		return executeCommand(Command.HPERSIST, args, (cmd)->cmd.hpersist(key, fields), (v)->v);
	}

	@Override
	public List<Long> hPExpire(final String key, final long ttl, final String... fields) {
		final CommandArguments args = CommandArguments.create(key).add(ttl).add("FIELDS").add(fields.length)
				.add(fields);
		return hPExpire(args, (cmd)->cmd.hpexpire(key, ttl, fields));
	}

	@Override
	public List<Long> hPExpire(final byte[] key, final long ttl, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(ttl).add("FIELDS").add(fields.length)
				.add(fields);
		return hPExpire(args, (cmd)->cmd.hpexpire(key, ttl, fields));
	}

	@Override
	public List<Long> hPExpire(final String key, final long ttl, final ExpireOption option, final String... fields) {
		final CommandArguments args = CommandArguments.create(key).add(ttl).add(option).add("FIELDS").add(fields.length)
				.add(fields);
		final ExpireOptionConverter optionConverter = new ExpireOptionConverter();
		return hPExpire(args, (cmd)->cmd.hpexpire(key, ttl, optionConverter.convert(option), fields));
	}

	@Override
	public List<Long> hPExpire(final byte[] key, final long ttl, final ExpireOption option, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(ttl).add(option).add("FIELDS").add(fields.length)
				.add(fields);
		final ExpireOptionConverter optionConverter = new ExpireOptionConverter();
		return hPExpire(args, (cmd)->cmd.hpexpire(key, ttl, optionConverter.convert(option), fields));
	}

	@Override
	public List<Long> hPExpireAt(final String key, final long unixTimestamp, final String... fields) {
		final CommandArguments args = CommandArguments.create(key).add(unixTimestamp).add("FIELDS").add(fields.length)
				.add(fields);
		return hPExpireAt(args, (cmd)->cmd.hpexpireAt(key, unixTimestamp, fields));
	}

	@Override
	public List<Long> hPExpireAt(final byte[] key, final long unixTimestamp, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(unixTimestamp).add("FIELDS").add(fields.length)
				.add(fields);
		return hPExpireAt(args, (cmd)->cmd.hpexpireAt(key, unixTimestamp, fields));
	}

	@Override
	public List<Long> hPExpireAt(final String key, final long unixTimestamp, final ExpireOption option,
								 final String... fields) {
		final CommandArguments args = CommandArguments.create(key).add(unixTimestamp).add(option).add("FIELDS")
				.add(fields.length).add(fields);
		final ExpireOptionConverter optionConverter = new ExpireOptionConverter();
		return hPExpireAt(args, (cmd)->cmd.hpexpireAt(key, unixTimestamp, optionConverter.convert(option), fields));
	}

	@Override
	public List<Long> hPExpireAt(final byte[] key, final long unixTimestamp, final ExpireOption option,
								 final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(unixTimestamp).add(option).add("FIELDS")
				.add(fields.length).add(fields);
		final ExpireOptionConverter optionConverter = new ExpireOptionConverter();
		return hPExpireAt(args, (cmd)->cmd.hpexpireAt(key, unixTimestamp, optionConverter.convert(option), fields));
	}

	@Override
	public List<Long> hPExpireTime(final String key, final String... fields) {
		final CommandArguments args = CommandArguments.create(key).add("FIELDS").add(fields.length).add(fields);
		return executeCommand(Command.HPEXPIRETIME, args, (cmd)->cmd.hpexpireTime(key, fields), (v)->v);
	}

	@Override
	public List<Long> hPExpireTime(final byte[] key, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add("FIELDS").add(fields.length).add(fields);
		return executeCommand(Command.HPEXPIRETIME, args, (cmd)->cmd.hpexpireTime(key, fields), (v)->v);
	}

	@Override
	public List<Long> hPTtl(final String key, final String... fields) {
		final CommandArguments args = CommandArguments.create(key).add("FIELDS").add(fields.length).add(fields);
		return executeCommand(Command.HPTTL, args, (cmd)->cmd.hpttl(key, fields), (v)->v);
	}

	@Override
	public List<Long> hPTtl(final byte[] key, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add("FIELDS").add(fields.length).add(fields);
		return executeCommand(Command.HPTTL, args, (cmd)->cmd.hpttl(key, fields), (v)->v);
	}

	@Override
	public String hRandField(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return hRandField(args, (cmd)->cmd.hrandfield(key));
	}

	@Override
	public byte[] hRandField(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return hRandField(args, (cmd)->cmd.hrandfield(key));
	}

	@Override
	public List<String> hRandField(final String key, final long count) {
		final CommandArguments args = CommandArguments.create(key).add(count);
		return hRandField(args, (cmd)->cmd.hrandfield(key, count));
	}

	@Override
	public List<byte[]> hRandField(final byte[] key, final long count) {
		final CommandArguments args = CommandArguments.create(key).add(count);
		return hRandField(args, (cmd)->cmd.hrandfield(key, count));
	}

	@Override
	public Map<String, String> hRandFieldWithValues(final String key, final long count) {
		final CommandArguments args = CommandArguments.create(key).add(count).add("WITHVALUES");
		return executeCommand(Command.HRANDFIELD, args, (cmd)->cmd.hrandfieldWithValues(key, count),
				new ListMapEntryMapConverter<>((k)->k, (v)->v));
	}

	@Override
	public Map<byte[], byte[]> hRandFieldWithValues(final byte[] key, final long count) {
		final CommandArguments args = CommandArguments.create(key).add(count).add("WITHVALUES");
		return executeCommand(Command.HRANDFIELD, args, (cmd)->cmd.hrandfieldWithValues(key, count),
				new ListMapEntryMapConverter<>((k)->k, (v)->v));
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final String cursor) {
		final CommandArguments args = CommandArguments.create(key).add(cursor);
		return hStringScan((cmd)->cmd.hscan(key, cursor), args);
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor) {
		final CommandArguments args = CommandArguments.create(key).add(cursor);
		return hBinaryScan((cmd)->cmd.hscan(key, cursor), args);
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final String cursor, final String pattern) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(pattern);
		return hStringScan((cmd)->cmd.hscan(key, cursor, new JedisScanParams(pattern)), args);
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor, final byte[] pattern) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(pattern);
		return hBinaryScan((cmd)->cmd.hscan(key, cursor, new JedisScanParams(pattern)), args);
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final String cursor, final long count) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(count);
		return hStringScan((cmd)->cmd.hscan(key, cursor, new JedisScanParams(count)), args);
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor, final long count) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(count);
		return hBinaryScan((cmd)->cmd.hscan(key, cursor, new JedisScanParams(count)), args);
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final String cursor, final String pattern,
												 final long count) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(pattern);
		return hStringScan((cmd)->cmd.hscan(key, cursor, new JedisScanParams(pattern, count)), args);
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor, final byte[] pattern,
												 final long count) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(pattern).add(count);
		return hBinaryScan((cmd)->cmd.hscan(key, cursor, new JedisScanParams(pattern, count)), args);
	}

	@Override
	public Long hSet(final String key, final Map<String, String> data) {
		final CommandArguments args = CommandArguments.create(key).add(data);
		return executeCommand(Command.HSET, args, (cmd)->cmd.hset(key, data), (v)->v);
	}

	@Override
	public Long hSet(final byte[] key, final Map<byte[], byte[]> data) {
		final CommandArguments args = CommandArguments.create(key).add(data);
		return executeCommand(Command.HSET, args, (cmd)->cmd.hset(key, data), (v)->v);
	}

	@Override
	public Status hSetEx(final String key, final Map<String, String> data) {
		final CommandArguments args = CommandArguments.create(key).add(data);
		return hSetEx(args, (cmd)->cmd.hsetex(key, new HSetExParams(), data));
	}

	@Override
	public Status hSetEx(final byte[] key, final Map<byte[], byte[]> data) {
		final CommandArguments args = CommandArguments.create(key).add(data);
		return hSetEx(args, (cmd)->cmd.hsetex(key, new HSetExParams(), data));
	}

	@Override
	public Status hSetEx(final String key, final Map<String, String> data, final HSetExArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(argument).add(data);
		final HSetExArgumentConverter hSetExArgumentConverter = new HSetExArgumentConverter();
		return hSetEx(args, (cmd)->cmd.hsetex(key, hSetExArgumentConverter.convert(argument), data));
	}

	@Override
	public Status hSetEx(final byte[] key, final Map<byte[], byte[]> data, final HSetExArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(argument).add(data);
		final HSetExArgumentConverter hSetExArgumentConverter = new HSetExArgumentConverter();
		return hSetEx(args, (cmd)->cmd.hsetex(key, hSetExArgumentConverter.convert(argument), data));
	}

	@Override
	public Status hSetNx(final String key, final String field, final String value) {
		final CommandArguments args = CommandArguments.create(key).add(field).add(value);
		return executeCommand(Command.HSETNX, args, (cmd)->cmd.hsetnx(key, field, value), new OneStatusConverter());
	}

	@Override
	public Status hSetNx(final byte[] key, final byte[] field, final byte[] value) {
		final CommandArguments args = CommandArguments.create(key).add(field).add(value);
		return executeCommand(Command.HSETNX, args, (cmd)->cmd.hsetnx(key, field, value), new OneStatusConverter());
	}

	@Override
	public Long hStrLen(final String key, final String field) {
		final CommandArguments args = CommandArguments.create(key).add(field);
		return executeCommand(Command.HSTRLEN, args, (cmd)->cmd.hstrlen(key, field), (v)->v);
	}

	@Override
	public Long hStrLen(final byte[] key, final byte[] field) {
		final CommandArguments args = CommandArguments.create(key).add(field);
		return executeCommand(Command.HSTRLEN, args, (cmd)->cmd.hstrlen(key, field), (v)->v);
	}

	@Override
	public List<Long> hTtl(final String key, final String... fields) {
		final CommandArguments args = CommandArguments.create(key).add(fields);
		return executeCommand(Command.HTTL, args, (cmd)->cmd.httl(key, fields), (v)->v);
	}

	@Override
	public List<Long> hTtl(final byte[] key, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(fields);
		return executeCommand(Command.HTTL, args, (cmd)->cmd.httl(key, fields), (v)->v);
	}

	@Override
	public List<String> hVals(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.HVALS, args, (cmd)->cmd.hvals(key), (v)->v);
	}

	@Override
	public List<byte[]> hVals(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.HVALS, args, (cmd)->cmd.hvals(key), (v)->v);
	}

	private List<Long> hExpire(final CommandArguments args,
							   final com.buession.redis.core.Command.Executor<UnifiedJedis, List<Long>> executor) {
		return executeCommand(Command.HEXPIRE, args, executor, (v)->v);
	}

	private List<Long> hExpireAt(final CommandArguments args,
								 final com.buession.redis.core.Command.Executor<UnifiedJedis, List<Long>> executor) {
		return executeCommand(Command.HEXPIREAT, args, executor, (v)->v);
	}

	private <R> List<R> hGetEx(final CommandArguments args,
							   final com.buession.redis.core.Command.Executor<UnifiedJedis, List<R>> executor) {
		return executeCommand(Command.HGETEX, args, executor, (v)->v);
	}

	private List<Long> hPExpire(final CommandArguments args,
								final com.buession.redis.core.Command.Executor<UnifiedJedis, List<Long>> executor) {
		return executeCommand(Command.HPEXPIRE, args, executor, (v)->v);
	}

	private List<Long> hPExpireAt(final CommandArguments args,
								  final com.buession.redis.core.Command.Executor<UnifiedJedis, List<Long>> executor) {
		return executeCommand(Command.HPEXPIREAT, args, executor, (v)->v);
	}

	private Status hSetEx(final CommandArguments args,
						  final com.buession.redis.core.Command.Executor<UnifiedJedis, Long> executor) {
		return executeCommand(Command.HSETEX, args, executor, new OneStatusConverter());
	}

	private <R> R hRandField(final CommandArguments args,
							 final com.buession.redis.core.Command.Executor<UnifiedJedis, R> executor) {
		return executeCommand(Command.HRANDFIELD, args, executor, (v)->v);
	}

	private ScanResult<Map<String, String>> hStringScan(
			final com.buession.redis.core.Command.Executor<UnifiedJedis, redis.clients.jedis.resps.ScanResult<Map.Entry<String, String>>> executor,
			final CommandArguments args) {
		return executeCommand(Command.HSCAN, args, executor, new ScanResultConverter.MapEntryScanResultConverter<>());
	}

	private ScanResult<Map<byte[], byte[]>> hBinaryScan(
			final com.buession.redis.core.Command.Executor<UnifiedJedis, redis.clients.jedis.resps.ScanResult<Map.Entry<byte[], byte[]>>> executor,
			final CommandArguments args) {
		return executeCommand(Command.HSCAN, args, executor, new ScanResultConverter.MapEntryScanResultConverter<>());
	}

}
