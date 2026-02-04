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
package com.buession.redis.client.jedis.operations;

import com.buession.lang.Status;
import com.buession.redis.client.jedis.JedisRedisClient;
import com.buession.redis.client.operations.HashOperations;
import com.buession.redis.core.ExpireOption;
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.command.Command;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.args.GetExArgument;
import com.buession.redis.core.command.args.HSetExArgument;
import com.buession.redis.core.internal.convert.Converters;
import com.buession.redis.core.internal.convert.jedis.params.ExpireOptionConverter;
import com.buession.redis.core.internal.convert.jedis.response.ScanResultConverter;
import com.buession.redis.core.internal.jedis.IParamsUtils;
import com.buession.redis.core.internal.jedis.JedisScanParams;
import redis.clients.jedis.UnifiedJedis;
import redis.clients.jedis.params.HGetExParams;
import redis.clients.jedis.params.HSetExParams;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Jedis 哈希表命令操作
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public final class JedisHashOperations extends AbstractJedisRedisOperations implements HashOperations {

	public JedisHashOperations(final JedisRedisClient client) {
		super(client);
	}

	@Override
	public Long hDel(final String key, final String... fields) {
		final CommandArguments args = CommandArguments.create(key).add(fields);
		return JedisCommandBuilder.<Long, Long>newBuilder(client, Command.HDEL).executor((cmd)->cmd.hdel(key, fields))
				.arguments(args).converter((v)->v).run();
	}

	@Override
	public Long hDel(final byte[] key, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(fields);
		return JedisCommandBuilder.<Long, Long>newBuilder(client, Command.HDEL).executor((cmd)->cmd.hdel(key, fields))
				.arguments(args).converter((v)->v).run();
	}

	@Override
	public Boolean hExists(final String key, final String field) {
		final CommandArguments args = CommandArguments.create(key).add(field);
		return JedisCommandBuilder.<Boolean, Boolean>newBuilder(client, Command.HEXISTS)
				.executor((cmd)->cmd.hexists(key, field)).arguments(args).converter((v)->v).run();
	}

	@Override
	public Boolean hExists(final byte[] key, final byte[] field) {
		final CommandArguments args = CommandArguments.create(key).add(field);
		return JedisCommandBuilder.<Boolean, Boolean>newBuilder(client, Command.HEXISTS)
				.executor((cmd)->cmd.hexists(key, field)).arguments(args).converter((v)->v).run();
	}

	@Override
	public List<Long> hExpire(final String key, final long ttl, final String... fields) {
		final CommandArguments args = CommandArguments.create(key).add(ttl).add("FIELDS").add(fields.length)
				.add(fields);
		return JedisCommandBuilder.<List<Long>, List<Long>>newBuilder(client, Command.HEXPIRE)
				.executor((cmd)->cmd.hexpire(key, ttl, fields)).arguments(args).converter((v)->v).run();
	}

	@Override
	public List<Long> hExpire(final byte[] key, final long ttl, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(ttl).add("FIELDS").add(fields.length)
				.add(fields);
		return JedisCommandBuilder.<List<Long>, List<Long>>newBuilder(client, Command.HEXPIRE)
				.executor((cmd)->cmd.hexpire(key, ttl, fields)).arguments(args).converter((v)->v).run();
	}

	@Override
	public List<Long> hExpire(final String key, final long ttl, final ExpireOption option, final String... fields) {
		final CommandArguments args = CommandArguments.create(key).add(ttl).add(option).add("FIELDS").add(fields.length)
				.add(fields);
		final ExpireOptionConverter optionConverter = new ExpireOptionConverter();
		return JedisCommandBuilder.<List<Long>, List<Long>>newBuilder(client, Command.HEXPIRE)
				.executor((cmd)->cmd.hexpire(key, ttl, optionConverter.convert(option), fields)).arguments(args)
				.converter((v)->v).run();
	}

	@Override
	public List<Long> hExpire(final byte[] key, final long ttl, final ExpireOption option, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(ttl).add("FIELDS").add(fields.length)
				.add(fields);
		final ExpireOptionConverter optionConverter = new ExpireOptionConverter();
		return JedisCommandBuilder.<List<Long>, List<Long>>newBuilder(client, Command.HEXPIRE)
				.executor((cmd)->cmd.hexpire(key, ttl, optionConverter.convert(option), fields)).arguments(args)
				.converter((v)->v).run();
	}

	@Override
	public List<Long> hExpireAt(final String key, final long unixTimestamp, final String... fields) {
		final CommandArguments args = CommandArguments.create(key).add(unixTimestamp).add("FIELDS").add(fields.length)
				.add(fields);
		return JedisCommandBuilder.<List<Long>, List<Long>>newBuilder(client, Command.HEXPIREAT)
				.executor((cmd)->cmd.hexpireAt(key, unixTimestamp, fields)).arguments(args).converter((v)->v).run();
	}

	@Override
	public List<Long> hExpireAt(final byte[] key, final long unixTimestamp, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(unixTimestamp).add("FIELDS").add(fields.length)
				.add(fields);
		return JedisCommandBuilder.<List<Long>, List<Long>>newBuilder(client, Command.HEXPIREAT)
				.executor((cmd)->cmd.hexpireAt(key, unixTimestamp, fields)).arguments(args).converter((v)->v).run();
	}

	@Override
	public List<Long> hExpireAt(final String key, final long unixTimestamp, final ExpireOption option,
								final String... fields) {
		final CommandArguments args = CommandArguments.create(key).add(unixTimestamp).add(option).add("FIELDS")
				.add(fields.length).add(fields);
		final ExpireOptionConverter optionConverter = new ExpireOptionConverter();
		return JedisCommandBuilder.<List<Long>, List<Long>>newBuilder(client, Command.HEXPIREAT)
				.executor((cmd)->cmd.hexpireAt(key, unixTimestamp, optionConverter.convert(option), fields))
				.arguments(args).converter((v)->v).run();
	}

	@Override
	public List<Long> hExpireAt(final byte[] key, final long unixTimestamp, final ExpireOption option,
								final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(unixTimestamp).add(option).add("FIELDS")
				.add(fields.length).add(fields);
		final ExpireOptionConverter optionConverter = new ExpireOptionConverter();
		return JedisCommandBuilder.<List<Long>, List<Long>>newBuilder(client, Command.HEXPIREAT)
				.executor((cmd)->cmd.hexpireAt(key, unixTimestamp, optionConverter.convert(option), fields))
				.arguments(args).converter((v)->v).run();
	}

	@Override
	public List<Long> hExpireTime(final String key, final String... fields) {
		final CommandArguments args = CommandArguments.create(key).add("FIELDS").add(fields.length).add(fields);
		return JedisCommandBuilder.<List<Long>, List<Long>>newBuilder(client, Command.HEXPIRETIME)
				.executor((cmd)->cmd.hexpireTime(key, fields)).arguments(args).converter((v)->v).run();
	}

	@Override
	public List<Long> hExpireTime(final byte[] key, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add("FIELDS").add(fields.length).add(fields);
		return JedisCommandBuilder.<List<Long>, List<Long>>newBuilder(client, Command.HEXPIRETIME)
				.executor((cmd)->cmd.hexpireTime(key, fields)).arguments(args).converter((v)->v).run();
	}

	@Override
	public String hGet(final String key, final String field) {
		final CommandArguments args = CommandArguments.create(key).add(field);
		return JedisCommandBuilder.<String, String>newBuilder(client, Command.HGET)
				.executor((cmd)->cmd.hget(key, field)).arguments(args).converter((v)->v).run();
	}

	@Override
	public byte[] hGet(final byte[] key, final byte[] field) {
		final CommandArguments args = CommandArguments.create(key).add(field);
		return JedisCommandBuilder.<byte[], byte[]>newBuilder(client, Command.HGET)
				.executor((cmd)->cmd.hget(key, field)).arguments(args).converter((v)->v).run();
	}

	@Override
	public Map<String, String> hGetAll(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return JedisCommandBuilder.<Map<String, String>, Map<String, String>>newBuilder(client, Command.HGETALL)
				.executor((cmd)->cmd.hgetAll(key)).arguments(args).converter((v)->v).run();
	}

	@Override
	public Map<byte[], byte[]> hGetAll(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return JedisCommandBuilder.<Map<byte[], byte[]>, Map<byte[], byte[]>>newBuilder(client, Command.HGETALL)
				.executor((cmd)->cmd.hgetAll(key)).arguments(args).converter((v)->v).run();
	}

	@Override
	public List<String> hGetDel(final String key, final String... fields) {
		final CommandArguments args = CommandArguments.create(key).add(fields);
		return JedisCommandBuilder.<List<String>, List<String>>newBuilder(client, Command.HGETDEL)
				.executor((cmd)->cmd.hgetdel(key, fields)).arguments(args).converter((v)->v).run();
	}

	@Override
	public List<byte[]> hGetDel(final byte[] key, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(fields);
		return JedisCommandBuilder.<List<byte[]>, List<byte[]>>newBuilder(client, Command.HGETDEL)
				.executor((cmd)->cmd.hgetdel(key, fields)).arguments(args).converter((v)->v).run();
	}

	@Override
	public List<String> hGetEx(final String key, final String... fields) {
		final CommandArguments args = CommandArguments.create(key).add(fields);
		return JedisCommandBuilder.<List<String>, List<String>>newBuilder(client, Command.HGETEX)
				.executor((cmd)->cmd.hgetex(key, new HGetExParams(), fields)).arguments(args).converter((v)->v).run();
	}

	@Override
	public List<byte[]> hGetEx(final byte[] key, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(fields);
		return JedisCommandBuilder.<List<byte[]>, List<byte[]>>newBuilder(client, Command.HGETEX)
				.executor((cmd)->cmd.hgetex(key, new HGetExParams(), fields)).arguments(args).converter((v)->v).run();
	}

	@Override
	public List<String> hGetEx(final String key, final GetExArgument argument, final String... fields) {
		final CommandArguments args = CommandArguments.create(key).add(argument).add(fields);
		return JedisCommandBuilder.<List<String>, List<String>>newBuilder(client, Command.HGETEX)
				.executor((cmd)->cmd.hgetex(key, IParamsUtils.hGetExParams(argument), fields)).arguments(args)
				.converter((v)->v).run();
	}

	@Override
	public List<byte[]> hGetEx(final byte[] key, final GetExArgument argument, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(argument).add(fields);
		return JedisCommandBuilder.<List<byte[]>, List<byte[]>>newBuilder(client, Command.HGETEX)
				.executor((cmd)->cmd.hgetex(key, IParamsUtils.hGetExParams(argument), fields)).arguments(args)
				.converter((v)->v).run();
	}

	@Override
	public Long hIncrBy(final String key, final String field, final long value) {
		final CommandArguments args = CommandArguments.create(key).add(field).add(value);
		return JedisCommandBuilder.<Long, Long>newBuilder(client, Command.HINCRBY)
				.executor((cmd)->cmd.hincrBy(key, field, value)).arguments(args).converter((v)->v).run();
	}

	@Override
	public Long hIncrBy(final byte[] key, final byte[] field, final long value) {
		final CommandArguments args = CommandArguments.create(key).add(field).add(value);
		return JedisCommandBuilder.<Long, Long>newBuilder(client, Command.HINCRBY)
				.executor((cmd)->cmd.hincrBy(key, field, value)).arguments(args).converter((v)->v).run();
	}

	@Override
	public Double hIncrByFloat(final String key, final String field, final double value) {
		final CommandArguments args = CommandArguments.create(key).add(field).add(value);
		return JedisCommandBuilder.<Double, Double>newBuilder(client, Command.HINCRBYFLOAT)
				.executor((cmd)->cmd.hincrByFloat(key, field, value)).arguments(args).converter((v)->v).run();
	}

	@Override
	public Double hIncrByFloat(final byte[] key, final byte[] field, final double value) {
		final CommandArguments args = CommandArguments.create(key).add(field).add(value);
		return JedisCommandBuilder.<Double, Double>newBuilder(client, Command.HINCRBYFLOAT)
				.executor((cmd)->cmd.hincrByFloat(key, field, value)).arguments(args).converter((v)->v).run();
	}

	@Override
	public Set<String> hKeys(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return JedisCommandBuilder.<Set<String>, Set<String>>newBuilder(client, Command.HKEYS)
				.executor((cmd)->cmd.hkeys(key)).arguments(args).converter((v)->v).run();
	}

	@Override
	public Set<byte[]> hKeys(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return JedisCommandBuilder.<Set<byte[]>, Set<byte[]>>newBuilder(client, Command.HKEYS)
				.executor((cmd)->cmd.hkeys(key)).arguments(args).converter((v)->v).run();
	}

	@Override
	public Long hLen(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return JedisCommandBuilder.<Long, Long>newBuilder(client, Command.HLEN).executor((cmd)->cmd.hlen(key))
				.arguments(args).converter((v)->v).run();
	}

	@Override
	public Long hLen(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return JedisCommandBuilder.<Long, Long>newBuilder(client, Command.HLEN).executor((cmd)->cmd.hlen(key))
				.arguments(args).converter((v)->v).run();
	}

	@Override
	public List<String> hMGet(final String key, final String... fields) {
		final CommandArguments args = CommandArguments.create(key).add(fields);
		return JedisCommandBuilder.<List<String>, List<String>>newBuilder(client, Command.HMGET)
				.executor((cmd)->cmd.hmget(key, fields)).arguments(args).converter((v)->v).run();
	}

	@Override
	public List<byte[]> hMGet(final byte[] key, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(fields);
		return JedisCommandBuilder.<List<byte[]>, List<byte[]>>newBuilder(client, Command.HMGET)
				.executor((cmd)->cmd.hmget(key, fields)).arguments(args).converter((v)->v).run();
	}

	@Override
	public Status hMSet(final String key, final Map<String, String> data) {
		final CommandArguments args = CommandArguments.create(key).add(data);
		return JedisCommandBuilder.<String, Status>newBuilder(client, Command.HMSET)
				.executor((cmd)->cmd.hmset(key, data)).arguments(args).converter(Converters.okStatusConverter()).run();
	}

	@Override
	public Status hMSet(final byte[] key, final Map<byte[], byte[]> data) {
		final CommandArguments args = CommandArguments.create(key).add(data);
		return JedisCommandBuilder.<String, Status>newBuilder(client, Command.HMSET)
				.executor((cmd)->cmd.hmset(key, data)).arguments(args).converter(Converters.okStatusConverter()).run();
	}

	@Override
	public List<Long> hPersist(final String key, final String... fields) {
		final CommandArguments args = CommandArguments.create(key).add(fields);
		return JedisCommandBuilder.<List<Long>, List<Long>>newBuilder(client, Command.HPERSIST)
				.executor((cmd)->cmd.hpersist(key, fields)).arguments(args).converter((v)->v).run();
	}

	@Override
	public List<Long> hPersist(final byte[] key, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(fields);
		return JedisCommandBuilder.<List<Long>, List<Long>>newBuilder(client, Command.HPERSIST)
				.executor((cmd)->cmd.hpersist(key, fields)).arguments(args).converter((v)->v).run();
	}

	@Override
	public List<Long> hPExpire(final String key, final long ttl, final String... fields) {
		final CommandArguments args = CommandArguments.create(key).add(ttl).add("FIELDS").add(fields.length)
				.add(fields);
		return JedisCommandBuilder.<List<Long>, List<Long>>newBuilder(client, Command.HPEXPIRE)
				.executor((cmd)->cmd.hpexpire(key, ttl, fields)).arguments(args).converter((v)->v).run();
	}

	@Override
	public List<Long> hPExpire(final byte[] key, final long ttl, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(ttl).add("FIELDS").add(fields.length)
				.add(fields);
		return JedisCommandBuilder.<List<Long>, List<Long>>newBuilder(client, Command.HPEXPIRE)
				.executor((cmd)->cmd.hpexpire(key, ttl, fields)).arguments(args).converter((v)->v).run();
	}

	@Override
	public List<Long> hPExpire(final String key, final long ttl, final ExpireOption option, final String... fields) {
		final CommandArguments args = CommandArguments.create(key).add(ttl).add(option).add("FIELDS").add(fields.length)
				.add(fields);
		final ExpireOptionConverter optionConverter = new ExpireOptionConverter();
		return JedisCommandBuilder.<List<Long>, List<Long>>newBuilder(client, Command.HPEXPIRE)
				.executor((cmd)->cmd.hpexpire(key, ttl, optionConverter.convert(option), fields)).arguments(args)
				.converter((v)->v).run();
	}

	@Override
	public List<Long> hPExpire(final byte[] key, final long ttl, final ExpireOption option, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(ttl).add(option).add("FIELDS").add(fields.length)
				.add(fields);
		final ExpireOptionConverter optionConverter = new ExpireOptionConverter();
		return JedisCommandBuilder.<List<Long>, List<Long>>newBuilder(client, Command.HPEXPIRE)
				.executor((cmd)->cmd.hpexpire(key, ttl, optionConverter.convert(option), fields)).arguments(args)
				.converter((v)->v).run();
	}

	@Override
	public List<Long> hPExpireAt(final String key, final long unixTimestamp, final String... fields) {
		final CommandArguments args = CommandArguments.create(key).add(unixTimestamp).add("FIELDS").add(fields.length)
				.add(fields);
		return JedisCommandBuilder.<List<Long>, List<Long>>newBuilder(client, Command.HPEXPIREAT)
				.executor((cmd)->cmd.hpexpireAt(key, unixTimestamp, fields)).arguments(args).converter((v)->v).run();
	}

	@Override
	public List<Long> hPExpireAt(final byte[] key, final long unixTimestamp, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(unixTimestamp).add("FIELDS").add(fields.length)
				.add(fields);
		return JedisCommandBuilder.<List<Long>, List<Long>>newBuilder(client, Command.HPEXPIREAT)
				.executor((cmd)->cmd.hpexpireAt(key, unixTimestamp, fields)).arguments(args).converter((v)->v).run();
	}

	@Override
	public List<Long> hPExpireAt(final String key, final long unixTimestamp, final ExpireOption option,
								 final String... fields) {
		final CommandArguments args = CommandArguments.create(key).add(unixTimestamp).add(option).add("FIELDS")
				.add(fields.length).add(fields);
		final ExpireOptionConverter optionConverter = new ExpireOptionConverter();
		return JedisCommandBuilder.<List<Long>, List<Long>>newBuilder(client, Command.HPEXPIREAT)
				.executor((cmd)->cmd.hpexpireAt(key, unixTimestamp, optionConverter.convert(option), fields))
				.arguments(args).converter((v)->v).run();
	}

	@Override
	public List<Long> hPExpireAt(final byte[] key, final long unixTimestamp, final ExpireOption option,
								 final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(unixTimestamp).add(option).add("FIELDS")
				.add(fields.length).add(fields);
		final ExpireOptionConverter optionConverter = new ExpireOptionConverter();
		return JedisCommandBuilder.<List<Long>, List<Long>>newBuilder(client, Command.HPEXPIREAT)
				.executor((cmd)->cmd.hpexpireAt(key, unixTimestamp, optionConverter.convert(option), fields))
				.arguments(args).converter((v)->v).run();
	}

	@Override
	public List<Long> hPExpireTime(final String key, final String... fields) {
		final CommandArguments args = CommandArguments.create(key).add("FIELDS").add(fields.length).add(fields);
		return JedisCommandBuilder.<List<Long>, List<Long>>newBuilder(client, Command.HPEXPIRETIME)
				.executor((cmd)->cmd.hpexpireTime(key, fields)).arguments(args).converter((v)->v).run();
	}

	@Override
	public List<Long> hPExpireTime(final byte[] key, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add("FIELDS").add(fields.length).add(fields);
		return JedisCommandBuilder.<List<Long>, List<Long>>newBuilder(client, Command.HPEXPIRETIME)
				.executor((cmd)->cmd.hpexpireTime(key, fields)).arguments(args).converter((v)->v).run();
	}

	@Override
	public List<Long> hPTtl(final String key, final String... fields) {
		final CommandArguments args = CommandArguments.create(key).add("FIELDS").add(fields.length).add(fields);
		return JedisCommandBuilder.<List<Long>, List<Long>>newBuilder(client, Command.HPTTL)
				.executor((cmd)->cmd.hpttl(key, fields)).arguments(args).converter((v)->v).run();
	}

	@Override
	public List<Long> hPTtl(final byte[] key, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add("FIELDS").add(fields.length).add(fields);
		return JedisCommandBuilder.<List<Long>, List<Long>>newBuilder(client, Command.HPTTL)
				.executor((cmd)->cmd.hpttl(key, fields)).arguments(args).converter((v)->v).run();
	}

	@Override
	public String hRandField(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return JedisCommandBuilder.<String, String>newBuilder(client, Command.HRANDFIELD)
				.executor((cmd)->cmd.hrandfield(key)).arguments(args).converter((v)->v).run();
	}

	@Override
	public byte[] hRandField(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return JedisCommandBuilder.<byte[], byte[]>newBuilder(client, Command.HRANDFIELD)
				.executor((cmd)->cmd.hrandfield(key)).arguments(args).converter((v)->v).run();
	}

	@Override
	public List<String> hRandField(final String key, final long count) {
		final CommandArguments args = CommandArguments.create(key).add(count);
		return JedisCommandBuilder.<List<String>, List<String>>newBuilder(client, Command.HRANDFIELD)
				.executor((cmd)->cmd.hrandfield(key, count)).arguments(args).converter((v)->v).run();
	}

	@Override
	public List<byte[]> hRandField(final byte[] key, final long count) {
		final CommandArguments args = CommandArguments.create(key).add(count);
		return JedisCommandBuilder.<List<byte[]>, List<byte[]>>newBuilder(client, Command.HRANDFIELD)
				.executor((cmd)->cmd.hrandfield(key, count)).arguments(args).converter((v)->v).run();
	}

	@Override
	public Map<String, String> hRandFieldWithValues(final String key, final long count) {
		final CommandArguments args = CommandArguments.create(key).add(count).add("WITHVALUES");
		return JedisCommandBuilder.<List<Map.Entry<String, String>>, Map<String, String>>newBuilder(client,
						Command.HRANDFIELD).executor((cmd)->cmd.hrandfieldWithValues(key, count)).arguments(args)
				.converter(Converters.stringListMapEntryMapConverter()).run();
	}

	@Override
	public Map<byte[], byte[]> hRandFieldWithValues(final byte[] key, final long count) {
		final CommandArguments args = CommandArguments.create(key).add(count).add("WITHVALUES");
		return JedisCommandBuilder.<List<Map.Entry<byte[], byte[]>>, Map<byte[], byte[]>>newBuilder(client,
						Command.HRANDFIELD).executor((cmd)->cmd.hrandfieldWithValues(key, count)).arguments(args)
				.converter(Converters.binaryListMapEntryMapConverter()).run();
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
		return JedisCommandBuilder.<Long, Long>newBuilder(client, Command.HSET).executor((cmd)->cmd.hset(key, data))
				.arguments(args).converter((v)->v).run();
	}

	@Override
	public Long hSet(final byte[] key, final Map<byte[], byte[]> data) {
		final CommandArguments args = CommandArguments.create(key).add(data);
		return JedisCommandBuilder.<Long, Long>newBuilder(client, Command.HSET).executor((cmd)->cmd.hset(key, data))
				.arguments(args).converter((v)->v).run();
	}

	@Override
	public Status hSetEx(final String key, final Map<String, String> data) {
		final CommandArguments args = CommandArguments.create(key).add(data);
		return JedisCommandBuilder.<Long, Status>newBuilder(client, Command.HSETEX)
				.executor((cmd)->cmd.hsetex(key, new HSetExParams(), data)).arguments(args)
				.converter(Converters.oneStatusConverter()).run();
	}

	@Override
	public Status hSetEx(final byte[] key, final Map<byte[], byte[]> data) {
		final CommandArguments args = CommandArguments.create(key).add(data);
		return JedisCommandBuilder.<Long, Status>newBuilder(client, Command.HSETEX)
				.executor((cmd)->cmd.hsetex(key, new HSetExParams(), data)).arguments(args)
				.converter(Converters.oneStatusConverter()).run();
	}

	@Override
	public Status hSetEx(final String key, final Map<String, String> data, final HSetExArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(argument).add(data);
		return JedisCommandBuilder.<Long, Status>newBuilder(client, Command.HSETEX)
				.executor((cmd)->cmd.hsetex(key, IParamsUtils.hSetExParams(argument), data)).arguments(args)
				.converter(Converters.oneStatusConverter()).run();
	}

	@Override
	public Status hSetEx(final byte[] key, final Map<byte[], byte[]> data, final HSetExArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(argument).add(data);
		return JedisCommandBuilder.<Long, Status>newBuilder(client, Command.HSETEX)
				.executor((cmd)->cmd.hsetex(key, IParamsUtils.hSetExParams(argument), data)).arguments(args)
				.converter(Converters.oneStatusConverter()).run();
	}

	@Override
	public Status hSetNx(final String key, final String field, final String value) {
		final CommandArguments args = CommandArguments.create(key).add(field).add(value);
		return JedisCommandBuilder.<Long, Status>newBuilder(client, Command.HSETNX)
				.executor((cmd)->cmd.hsetnx(key, field, value)).arguments(args)
				.converter(Converters.oneStatusConverter()).run();
	}

	@Override
	public Status hSetNx(final byte[] key, final byte[] field, final byte[] value) {
		final CommandArguments args = CommandArguments.create(key).add(field).add(value);
		return JedisCommandBuilder.<Long, Status>newBuilder(client, Command.HSETNX)
				.executor((cmd)->cmd.hsetnx(key, field, value)).arguments(args)
				.converter(Converters.oneStatusConverter()).run();
	}

	@Override
	public Long hStrLen(final String key, final String field) {
		final CommandArguments args = CommandArguments.create(key).add(field);
		return JedisCommandBuilder.<Long, Long>newBuilder(client, Command.HSTRLEN)
				.executor((cmd)->cmd.hstrlen(key, field)).arguments(args).converter((v)->v).run();
	}

	@Override
	public Long hStrLen(final byte[] key, final byte[] field) {
		final CommandArguments args = CommandArguments.create(key).add(field);
		return JedisCommandBuilder.<Long, Long>newBuilder(client, Command.HSTRLEN)
				.executor((cmd)->cmd.hstrlen(key, field)).arguments(args).converter((v)->v).run();
	}

	@Override
	public List<Long> hTtl(final String key, final String... fields) {
		final CommandArguments args = CommandArguments.create(key).add(fields);
		return JedisCommandBuilder.<List<Long>, List<Long>>newBuilder(client, Command.HTTL)
				.executor((cmd)->cmd.httl(key, fields)).arguments(args).converter((v)->v).run();
	}

	@Override
	public List<Long> hTtl(final byte[] key, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(fields);
		return JedisCommandBuilder.<List<Long>, List<Long>>newBuilder(client, Command.HTTL)
				.executor((cmd)->cmd.httl(key, fields)).arguments(args).converter((v)->v).run();
	}

	@Override
	public List<String> hVals(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return JedisCommandBuilder.<List<String>, List<String>>newBuilder(client, Command.HVALS)
				.executor((cmd)->cmd.hvals(key)).arguments(args).converter((v)->v).run();
	}

	@Override
	public List<byte[]> hVals(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return JedisCommandBuilder.<List<byte[]>, List<byte[]>>newBuilder(client, Command.HVALS)
				.executor((cmd)->cmd.hvals(key)).arguments(args).converter((v)->v).run();
	}

	private ScanResult<Map<String, String>> hStringScan(
			final com.buession.redis.core.Command.Executor<UnifiedJedis, redis.clients.jedis.resps.ScanResult<Map.Entry<String, String>>> executor,
			final CommandArguments args) {
		return JedisCommandBuilder.<redis.clients.jedis.resps.ScanResult<Map.Entry<String, String>>, ScanResult<Map<String, String>>>newBuilder(
						client, Command.HSCAN).executor(executor).arguments(args)
				.converter(new ScanResultConverter.MapScanResultConverter<>()).run();
	}

	private ScanResult<Map<byte[], byte[]>> hBinaryScan(
			final com.buession.redis.core.Command.Executor<UnifiedJedis, redis.clients.jedis.resps.ScanResult<Map.Entry<byte[], byte[]>>> executor,
			final CommandArguments args) {
		return JedisCommandBuilder.<redis.clients.jedis.resps.ScanResult<Map.Entry<byte[], byte[]>>, ScanResult<Map<byte[], byte[]>>>newBuilder(
						client, Command.HSCAN).executor(executor).arguments(args)
				.converter(new ScanResultConverter.MapScanResultConverter<>()).run();
	}

}
