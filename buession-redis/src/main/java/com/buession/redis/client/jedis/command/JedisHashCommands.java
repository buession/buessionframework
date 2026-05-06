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

import com.buession.core.converter.ArrayKeyValueMapConverter;
import com.buession.core.converter.ListMapEntryMapConverter;
import com.buession.core.converter.MapEntryKeyValueConverter;
import com.buession.lang.KeyValue;
import com.buession.lang.Status;
import com.buession.redis.client.jedis.JedisRedisClient;
import com.buession.redis.core.command.args.ExpireOption;
import com.buession.redis.core.Keyword;
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.command.RedisCommand;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.HashCommands;
import com.buession.redis.core.command.args.FnxFxx;
import com.buession.redis.core.command.args.GetExType;
import com.buession.redis.core.command.args.PxExType;
import com.buession.redis.core.internal.convert.jedis.params.ExpireOptionConverter;
import com.buession.redis.core.internal.convert.jedis.response.ScanResultConverter;
import com.buession.redis.core.internal.convert.response.OkStatusConverter;
import com.buession.redis.core.internal.convert.response.OneStatusConverter;
import com.buession.redis.core.internal.jedis.args.JedisHGetExParams;
import com.buession.redis.core.internal.jedis.args.JedisHSetExParams;
import com.buession.redis.core.internal.jedis.args.JedisScanParams;
import redis.clients.jedis.params.HGetExParams;
import redis.clients.jedis.params.HSetExParams;
import redis.clients.jedis.params.ScanParams;

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
		return executeCommand(RedisCommand.HDEL, args, (cmd)->cmd.hdel(key, fields),
				(cmd)->cmd.hdel(key, fields), (cmd)->cmd.hdel(key, fields));
	}

	@Override
	public Long hDel(final byte[] key, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(fields);
		return executeCommand(RedisCommand.HDEL, args, (cmd)->cmd.hdel(key, fields),
				(cmd)->cmd.hdel(key, fields), (cmd)->cmd.hdel(key, fields));
	}

	@Override
	public Boolean hExists(final String key, final String field) {
		final CommandArguments args = CommandArguments.create(key, field);
		return executeCommand(RedisCommand.HEXISTS, args, (cmd)->cmd.hexists(key, field),
				(cmd)->cmd.hexists(key, field), (cmd)->cmd.hexists(key, field));
	}

	@Override
	public Boolean hExists(final byte[] key, final byte[] field) {
		final CommandArguments args = CommandArguments.create(key, field);
		return executeCommand(RedisCommand.HEXISTS, args, (cmd)->cmd.hexists(key, field),
				(cmd)->cmd.hexists(key, field), (cmd)->cmd.hexists(key, field));
	}

	@Override
	public List<Long> hExpire(final String key, final long ttl, final String... fields) {
		final CommandArguments args = CommandArguments.create(key).add(ttl).add(Keyword.Hash.FIELDS, fields.length)
				.add(fields);
		return executeCommand(RedisCommand.HEXPIRE, args, (cmd)->cmd.hexpire(key, ttl, fields),
				(cmd)->cmd.hexpire(key, ttl, fields), (cmd)->cmd.hexpire(key, ttl, fields));
	}

	@Override
	public List<Long> hExpire(final byte[] key, final long ttl, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(ttl).add(Keyword.Hash.FIELDS, fields.length)
				.add(fields);
		return executeCommand(RedisCommand.HEXPIRE, args, (cmd)->cmd.hexpire(key, ttl, fields),
				(cmd)->cmd.hexpire(key, ttl, fields), (cmd)->cmd.hexpire(key, ttl, fields));
	}

	@Override
	public List<Long> hExpire(final String key, final long ttl, final ExpireOption option, final String... fields) {
		final CommandArguments args = CommandArguments.create(key).add(ttl).add(option)
				.add(Keyword.Hash.FIELDS, fields.length).add(fields);
		final ExpireOptionConverter optionConverter = new ExpireOptionConverter();
		return executeCommand(RedisCommand.HEXPIRE, args,
				(cmd)->cmd.hexpire(key, ttl, optionConverter.convert(option), fields),
				(cmd)->cmd.hexpire(key, ttl, optionConverter.convert(option), fields),
				(cmd)->cmd.hexpire(key, ttl, optionConverter.convert(option), fields));
	}

	@Override
	public List<Long> hExpire(final byte[] key, final long ttl, final ExpireOption option, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(ttl).add(option)
				.add(Keyword.Hash.FIELDS, fields.length).add(fields);
		final ExpireOptionConverter optionConverter = new ExpireOptionConverter();
		return executeCommand(RedisCommand.HEXPIRE, args,
				(cmd)->cmd.hexpire(key, ttl, optionConverter.convert(option), fields),
				(cmd)->cmd.hexpire(key, ttl, optionConverter.convert(option), fields),
				(cmd)->cmd.hexpire(key, ttl, optionConverter.convert(option), fields));
	}

	@Override
	public List<Long> hExpireAt(final String key, final long unixTimestamp, final String... fields) {
		final CommandArguments args = CommandArguments.create(key).add(unixTimestamp)
				.add(Keyword.Hash.FIELDS, fields.length).add(fields);
		return executeCommand(RedisCommand.HEXPIREAT, args, (cmd)->cmd.hexpireAt(key, unixTimestamp, fields),
				(cmd)->cmd.hexpireAt(key, unixTimestamp, fields),
				(cmd)->cmd.hexpireAt(key, unixTimestamp, fields));
	}

	@Override
	public List<Long> hExpireAt(final byte[] key, final long unixTimestamp, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(unixTimestamp)
				.add(Keyword.Hash.FIELDS, fields.length).add(fields);
		return executeCommand(RedisCommand.HEXPIREAT, args, (cmd)->cmd.hexpireAt(key, unixTimestamp, fields),
				(cmd)->cmd.hexpireAt(key, unixTimestamp, fields),
				(cmd)->cmd.hexpireAt(key, unixTimestamp, fields));
	}

	@Override
	public List<Long> hExpireAt(final String key, final long unixTimestamp, final ExpireOption option,
	                            final String... fields) {
		final CommandArguments args = CommandArguments.create(key).add(unixTimestamp).add(option)
				.add(Keyword.Hash.FIELDS, fields.length).add(fields);
		final ExpireOptionConverter optionConverter = new ExpireOptionConverter();
		return executeCommand(RedisCommand.HEXPIREAT, args,
				(cmd)->cmd.hexpireAt(key, unixTimestamp, optionConverter.convert(option), fields),
				(cmd)->cmd.hexpireAt(key, unixTimestamp, optionConverter.convert(option), fields),
				(cmd)->cmd.hexpireAt(key, unixTimestamp, optionConverter.convert(option), fields));
	}

	@Override
	public List<Long> hExpireAt(final byte[] key, final long unixTimestamp, final ExpireOption option,
	                            final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(unixTimestamp).add(option)
				.add(Keyword.Hash.FIELDS, fields.length).add(fields);
		final ExpireOptionConverter optionConverter = new ExpireOptionConverter();
		return executeCommand(RedisCommand.HEXPIREAT, args,
				(cmd)->cmd.hexpireAt(key, unixTimestamp, optionConverter.convert(option), fields),
				(cmd)->cmd.hexpireAt(key, unixTimestamp, optionConverter.convert(option), fields),
				(cmd)->cmd.hexpireAt(key, unixTimestamp, optionConverter.convert(option), fields));
	}

	@Override
	public List<Long> hExpireTime(final String key, final String... fields) {
		final CommandArguments args = CommandArguments.create(key).add(Keyword.Hash.FIELDS, fields.length).add(fields);
		return executeCommand(RedisCommand.HEXPIRETIME, args, (cmd)->cmd.hexpireTime(key, fields),
				(cmd)->cmd.hexpireTime(key, fields), (cmd)->cmd.hexpireTime(key, fields));
	}

	@Override
	public List<Long> hExpireTime(final byte[] key, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(Keyword.Hash.FIELDS, fields.length).add(fields);
		return executeCommand(RedisCommand.HEXPIRETIME, args, (cmd)->cmd.hexpireTime(key, fields),
				(cmd)->cmd.hexpireTime(key, fields), (cmd)->cmd.hexpireTime(key, fields));
	}

	@Override
	public String hGet(final String key, final String field) {
		final CommandArguments args = CommandArguments.create(key, field);
		return executeCommand(RedisCommand.HGET, args, (cmd)->cmd.hget(key, field),
				(cmd)->cmd.hget(key, field), (cmd)->cmd.hget(key, field));
	}

	@Override
	public byte[] hGet(final byte[] key, final byte[] field) {
		final CommandArguments args = CommandArguments.create(key, field);
		return executeCommand(RedisCommand.HGET, args, (cmd)->cmd.hget(key, field),
				(cmd)->cmd.hget(key, field), (cmd)->cmd.hget(key, field));
	}

	@Override
	public Map<String, String> hGetAll(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.HGETALL, args, (cmd)->cmd.hgetAll(key),
				(cmd)->cmd.hgetAll(key), (cmd)->cmd.hgetAll(key));
	}

	@Override
	public Map<byte[], byte[]> hGetAll(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.HGETALL, args, (cmd)->cmd.hgetAll(key),
				(cmd)->cmd.hgetAll(key), (cmd)->cmd.hgetAll(key));
	}

	@Override
	public List<String> hGetDel(final String key, final String... fields) {
		final CommandArguments args = CommandArguments.create(key).add(Keyword.Hash.FIELDS, fields.length).add(fields);
		return executeCommand(RedisCommand.HGETDEL, args, (cmd)->cmd.hgetdel(key, fields),
				(cmd)->cmd.hgetdel(key, fields), (cmd)->cmd.hgetdel(key, fields));
	}

	@Override
	public List<byte[]> hGetDel(final byte[] key, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(Keyword.Hash.FIELDS, fields.length).add(fields);
		return executeCommand(RedisCommand.HGETDEL, args, (cmd)->cmd.hgetdel(key, fields),
				(cmd)->cmd.hgetdel(key, fields), (cmd)->cmd.hgetdel(key, fields));
	}

	@Override
	public List<String> hGetEx(final String key, final String... fields) {
		final CommandArguments args = CommandArguments.create(key).add(Keyword.Hash.FIELDS, fields.length).add(fields);
		return hGetEx(key, new JedisHGetExParams(), fields, args);
	}

	@Override
	public List<byte[]> hGetEx(final byte[] key, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(Keyword.Hash.FIELDS, fields.length).add(fields);
		return hGetEx(key, new JedisHGetExParams(), fields, args);
	}

	@Override
	public List<String> hGetEx(final String key, final GetExType exType, final long expires, final String... fields) {
		final CommandArguments args = CommandArguments.create(key).add(exType, expires)
				.add(Keyword.Hash.FIELDS, fields.length).add(fields);
		return hGetEx(key, new JedisHGetExParams(exType, expires), fields, args);
	}

	@Override
	public List<byte[]> hGetEx(final byte[] key, final GetExType exType, final long expires, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(exType, expires)
				.add(Keyword.Hash.FIELDS, fields.length).add(fields);
		return hGetEx(key, new JedisHGetExParams(exType, expires), fields, args);
	}

	@Override
	public Long hIncrBy(final String key, final String field, final long value) {
		final CommandArguments args = CommandArguments.create(key).add(field, value);
		return executeCommand(RedisCommand.HINCRBY, args, (cmd)->cmd.hincrBy(key, field, value),
				(cmd)->cmd.hincrBy(key, field, value), (cmd)->cmd.hincrBy(key, field, value));
	}

	@Override
	public Long hIncrBy(final byte[] key, final byte[] field, final long value) {
		final CommandArguments args = CommandArguments.create(key).add(field, value);
		return executeCommand(RedisCommand.HINCRBY, args, (cmd)->cmd.hincrBy(key, field, value),
				(cmd)->cmd.hincrBy(key, field, value), (cmd)->cmd.hincrBy(key, field, value));
	}

	@Override
	public Double hIncrByFloat(final String key, final String field, final double value) {
		final CommandArguments args = CommandArguments.create(key).add(field, value);
		return executeCommand(RedisCommand.HINCRBYFLOAT, args, (cmd)->cmd.hincrByFloat(key, field, value),
				(cmd)->cmd.hincrByFloat(key, field, value), (cmd)->cmd.hincrByFloat(key, field, value));
	}

	@Override
	public Double hIncrByFloat(final byte[] key, final byte[] field, final double value) {
		final CommandArguments args = CommandArguments.create(key).add(field, value);
		return executeCommand(RedisCommand.HINCRBYFLOAT, args, (cmd)->cmd.hincrByFloat(key, field, value),
				(cmd)->cmd.hincrByFloat(key, field, value), (cmd)->cmd.hincrByFloat(key, field, value));
	}

	@Override
	public Set<String> hKeys(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.HKEYS, args, (cmd)->cmd.hkeys(key), (cmd)->cmd.hkeys(key),
				(cmd)->cmd.hkeys(key));
	}

	@Override
	public Set<byte[]> hKeys(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.HKEYS, args, (cmd)->cmd.hkeys(key), (cmd)->cmd.hkeys(key),
				(cmd)->cmd.hkeys(key));
	}

	@Override
	public Long hLen(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.HLEN, args, (cmd)->cmd.hlen(key), (cmd)->cmd.hlen(key),
				(cmd)->cmd.hlen(key));
	}

	@Override
	public Long hLen(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.HLEN, args, (cmd)->cmd.hlen(key), (cmd)->cmd.hlen(key),
				(cmd)->cmd.hlen(key));
	}

	@Override
	public List<String> hMGet(final String key, final String... fields) {
		final CommandArguments args = CommandArguments.create(key).add(fields);
		return executeCommand(RedisCommand.HMGET, args, (cmd)->cmd.hmget(key, fields),
				(cmd)->cmd.hmget(key, fields), (cmd)->cmd.hmget(key, fields));
	}

	@Override
	public List<byte[]> hMGet(final byte[] key, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(fields);
		return executeCommand(RedisCommand.HMGET, args, (cmd)->cmd.hmget(key, fields),
				(cmd)->cmd.hmget(key, fields), (cmd)->cmd.hmget(key, fields));
	}

	@SuppressWarnings({"unchecked"})
	@Override
	public Status hMSet(final String key, final KeyValue<String, String>... data) {
		final CommandArguments args = CommandArguments.create(key).add(data);
		final ArrayKeyValueMapConverter<String, String, String, String> arrayKeyValueMapConverter = new ArrayKeyValueMapConverter<>(
				(k)->k, (v)->v);
		return executeCommand(RedisCommand.HMSET, args,
				(cmd)->cmd.hmset(key, arrayKeyValueMapConverter.convert(data)),
				(cmd)->cmd.hmset(key, arrayKeyValueMapConverter.convert(data)),
				(cmd)->cmd.hmset(key, arrayKeyValueMapConverter.convert(data)), new OkStatusConverter());
	}

	@SuppressWarnings({"unchecked"})
	@Override
	public Status hMSet(final byte[] key, final KeyValue<byte[], byte[]>... data) {
		final CommandArguments args = CommandArguments.create(key).add(data);
		final ArrayKeyValueMapConverter<byte[], byte[], byte[], byte[]> arrayKeyValueMapConverter = new ArrayKeyValueMapConverter<>(
				(k)->k, (v)->v);
		return executeCommand(RedisCommand.HMSET, args,
				(cmd)->cmd.hmset(key, arrayKeyValueMapConverter.convert(data)),
				(cmd)->cmd.hmset(key, arrayKeyValueMapConverter.convert(data)),
				(cmd)->cmd.hmset(key, arrayKeyValueMapConverter.convert(data)), new OkStatusConverter());
	}

	@Override
	public List<Long> hPersist(final String key, final String... fields) {
		final CommandArguments args = CommandArguments.create(key).add(Keyword.Hash.FIELDS, fields.length).add(fields);
		return executeCommand(RedisCommand.HPERSIST, args, (cmd)->cmd.hpersist(key, fields),
				(cmd)->cmd.hpersist(key, fields), (cmd)->cmd.hpersist(key, fields));
	}

	@Override
	public List<Long> hPersist(final byte[] key, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(Keyword.Hash.FIELDS, fields.length).add(fields);
		return executeCommand(RedisCommand.HPERSIST, args, (cmd)->cmd.hpersist(key, fields),
				(cmd)->cmd.hpersist(key, fields), (cmd)->cmd.hpersist(key, fields));
	}

	@Override
	public List<Long> hPExpire(final String key, final long ttl, final String... fields) {
		final CommandArguments args = CommandArguments.create(key).add(ttl).add(Keyword.Hash.FIELDS, fields.length)
				.add(fields);
		return executeCommand(RedisCommand.HPEXPIRE, args, (cmd)->cmd.hpexpire(key, ttl, fields),
				(cmd)->cmd.hpexpire(key, ttl, fields), (cmd)->cmd.hpexpire(key, ttl, fields));
	}

	@Override
	public List<Long> hPExpire(final byte[] key, final long ttl, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(ttl).add(Keyword.Hash.FIELDS, fields.length)
				.add(fields);
		return executeCommand(RedisCommand.HPEXPIRE, args, (cmd)->cmd.hpexpire(key, ttl, fields),
				(cmd)->cmd.hpexpire(key, ttl, fields), (cmd)->cmd.hpexpire(key, ttl, fields));
	}

	@Override
	public List<Long> hPExpire(final String key, final long ttl, final ExpireOption option, final String... fields) {
		final CommandArguments args = CommandArguments.create(key).add(ttl).add(option)
				.add(Keyword.Hash.FIELDS, fields.length).add(fields);
		final ExpireOptionConverter optionConverter = new ExpireOptionConverter();
		return executeCommand(RedisCommand.HPEXPIRE, args,
				(cmd)->cmd.hpexpire(key, ttl, optionConverter.convert(option), fields),
				(cmd)->cmd.hpexpire(key, ttl, optionConverter.convert(option), fields),
				(cmd)->cmd.hpexpire(key, ttl, optionConverter.convert(option), fields));
	}

	@Override
	public List<Long> hPExpire(final byte[] key, final long ttl, final ExpireOption option, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(ttl).add(option)
				.add(Keyword.Hash.FIELDS, fields.length).add(fields);
		final ExpireOptionConverter optionConverter = new ExpireOptionConverter();
		return executeCommand(RedisCommand.HPEXPIRE, args,
				(cmd)->cmd.hpexpire(key, ttl, optionConverter.convert(option), fields),
				(cmd)->cmd.hpexpire(key, ttl, optionConverter.convert(option), fields),
				(cmd)->cmd.hpexpire(key, ttl, optionConverter.convert(option), fields));
	}

	@Override
	public List<Long> hPExpireAt(final String key, final long unixTimestamp, final String... fields) {
		final CommandArguments args = CommandArguments.create(key).add(unixTimestamp)
				.add(Keyword.Hash.FIELDS, fields.length).add(fields);
		return executeCommand(RedisCommand.HPEXPIREAT, args, (cmd)->cmd.hpexpireAt(key, unixTimestamp, fields),
				(cmd)->cmd.hpexpireAt(key, unixTimestamp, fields),
				(cmd)->cmd.hpexpireAt(key, unixTimestamp, fields));
	}

	@Override
	public List<Long> hPExpireAt(final byte[] key, final long unixTimestamp, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(unixTimestamp)
				.add(Keyword.Hash.FIELDS, fields.length).add(fields);
		return executeCommand(RedisCommand.HPEXPIREAT, args, (cmd)->cmd.hpexpireAt(key, unixTimestamp, fields),
				(cmd)->cmd.hpexpireAt(key, unixTimestamp, fields),
				(cmd)->cmd.hpexpireAt(key, unixTimestamp, fields));
	}

	@Override
	public List<Long> hPExpireAt(final String key, final long unixTimestamp, final ExpireOption option,
	                             final String... fields) {
		final CommandArguments args = CommandArguments.create(key).add(unixTimestamp).add(option)
				.add(Keyword.Hash.FIELDS, fields.length).add(fields);
		final ExpireOptionConverter optionConverter = new ExpireOptionConverter();
		return executeCommand(RedisCommand.HPEXPIREAT, args,
				(cmd)->cmd.hpexpireAt(key, unixTimestamp, optionConverter.convert(option), fields),
				(cmd)->cmd.hpexpireAt(key, unixTimestamp, optionConverter.convert(option), fields),
				(cmd)->cmd.hpexpireAt(key, unixTimestamp, optionConverter.convert(option), fields));
	}

	@Override
	public List<Long> hPExpireAt(final byte[] key, final long unixTimestamp, final ExpireOption option,
	                             final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(unixTimestamp).add(option)
				.add(Keyword.Hash.FIELDS, fields.length).add(fields);
		final ExpireOptionConverter optionConverter = new ExpireOptionConverter();
		return executeCommand(RedisCommand.HPEXPIREAT, args,
				(cmd)->cmd.hpexpireAt(key, unixTimestamp, optionConverter.convert(option), fields),
				(cmd)->cmd.hpexpireAt(key, unixTimestamp, optionConverter.convert(option), fields),
				(cmd)->cmd.hpexpireAt(key, unixTimestamp, optionConverter.convert(option), fields));
	}

	@Override
	public List<Long> hPExpireTime(final String key, final String... fields) {
		final CommandArguments args = CommandArguments.create(key).add(Keyword.Hash.FIELDS, fields.length).add(fields);
		return executeCommand(RedisCommand.HPEXPIRETIME, args, (cmd)->cmd.hpexpireTime(key, fields),
				(cmd)->cmd.hpexpireTime(key, fields), (cmd)->cmd.hpexpireTime(key, fields));
	}

	@Override
	public List<Long> hPExpireTime(final byte[] key, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(Keyword.Hash.FIELDS, fields.length).add(fields);
		return executeCommand(RedisCommand.HPEXPIRETIME, args, (cmd)->cmd.hpexpireTime(key, fields),
				(cmd)->cmd.hpexpireTime(key, fields), (cmd)->cmd.hpexpireTime(key, fields));
	}

	@Override
	public List<Long> hPTtl(final String key, final String... fields) {
		final CommandArguments args = CommandArguments.create(key).add(Keyword.Hash.FIELDS, fields.length).add(fields);
		return executeCommand(RedisCommand.HPTTL, args, (cmd)->cmd.hpttl(key, fields),
				(cmd)->cmd.hpttl(key, fields), (cmd)->cmd.hpttl(key, fields));
	}

	@Override
	public List<Long> hPTtl(final byte[] key, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(Keyword.Hash.FIELDS, fields.length).add(fields);
		return executeCommand(RedisCommand.HPTTL, args, (cmd)->cmd.hpttl(key, fields),
				(cmd)->cmd.hpttl(key, fields), (cmd)->cmd.hpttl(key, fields));
	}

	@Override
	public String hRandField(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.HRANDFIELD, args, (cmd)->cmd.hrandfield(key),
				(cmd)->cmd.hrandfield(key), (cmd)->cmd.hrandfield(key));
	}

	@Override
	public byte[] hRandField(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.HRANDFIELD, args, (cmd)->cmd.hrandfield(key),
				(cmd)->cmd.hrandfield(key), (cmd)->cmd.hrandfield(key));
	}

	@Override
	public List<String> hRandField(final String key, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(count);
		return executeCommand(RedisCommand.HRANDFIELD, args, (cmd)->cmd.hrandfield(key, count),
				(cmd)->cmd.hrandfield(key, count), (cmd)->cmd.hrandfield(key, count));
	}

	@Override
	public List<byte[]> hRandField(final byte[] key, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(count);
		return executeCommand(RedisCommand.HRANDFIELD, args, (cmd)->cmd.hrandfield(key, count),
				(cmd)->cmd.hrandfield(key, count), (cmd)->cmd.hrandfield(key, count));
	}

	@Override
	public Map<String, String> hRandFieldWithValues(final String key, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(count).add(Keyword.Hash.WITHVALUES);
		return executeCommand(RedisCommand.HRANDFIELD, args, (cmd)->cmd.hrandfieldWithValues(key, count),
				(cmd)->cmd.hrandfieldWithValues(key, count),
				(cmd)->cmd.hrandfieldWithValues(key, count),
				new ListMapEntryMapConverter<>((k)->k, (v)->v));
	}

	@Override
	public Map<byte[], byte[]> hRandFieldWithValues(final byte[] key, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(count).add(Keyword.Hash.WITHVALUES);
		return executeCommand(RedisCommand.HRANDFIELD, args, (cmd)->cmd.hrandfieldWithValues(key, count),
				(cmd)->cmd.hrandfieldWithValues(key, count),
				(cmd)->cmd.hrandfieldWithValues(key, count),
				new ListMapEntryMapConverter<>((k)->k, (v)->v));
	}

	@Override
	public ScanResult<KeyValue<String, String>> hScan(final String key, final String cursor) {
		final CommandArguments args = CommandArguments.create(key).add(cursor);
		return executeCommand(RedisCommand.HSCAN, args, (cmd)->cmd.hscan(key, cursor),
				(cmd)->cmd.hscan(key, cursor), (cmd)->cmd.hscan(key, cursor),
				new ScanResultConverter<>(new MapEntryKeyValueConverter<>((k)->k, (v)->v)));
	}

	@Override
	public ScanResult<KeyValue<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor) {
		final CommandArguments args = CommandArguments.create(key).add(cursor);
		return executeCommand(RedisCommand.HSCAN, args, (cmd)->cmd.hscan(key, cursor),
				(cmd)->cmd.hscan(key, cursor), (cmd)->cmd.hscan(key, cursor),
				new ScanResultConverter<>(new MapEntryKeyValueConverter<>((k)->k, (v)->v)));
	}

	@Override
	public ScanResult<KeyValue<String, String>> hScan(final String key, final String cursor, final String pattern) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(Keyword.Scan.MATCH, pattern);
		return hScan(key, cursor, new JedisScanParams(pattern), args);
	}

	@Override
	public ScanResult<KeyValue<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor, final byte[] pattern) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(Keyword.Scan.MATCH, pattern);
		return hScan(key, cursor, new JedisScanParams(pattern), args);
	}

	@Override
	public ScanResult<KeyValue<String, String>> hScan(final String key, final String cursor, final String pattern,
	                                                  final int count) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(Keyword.Scan.MATCH, pattern)
				.add(Keyword.Common.COUNT, count);
		return hScan(key, cursor, new JedisScanParams(pattern, count), args);
	}

	@Override
	public ScanResult<KeyValue<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor, final byte[] pattern,
	                                                  final int count) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(Keyword.Scan.MATCH, pattern)
				.add(Keyword.Common.COUNT, count);
		return hScan(key, cursor, new JedisScanParams(pattern, count), args);
	}

	@Override
	public ScanResult<KeyValue<String, String>> hScan(final String key, final String cursor, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(Keyword.Common.COUNT, count);
		return hScan(key, cursor, new JedisScanParams(count), args);
	}

	@Override
	public ScanResult<KeyValue<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(Keyword.Common.COUNT, count);
		return hScan(key, cursor, new JedisScanParams(count), args);
	}

	@Override
	public ScanResult<String> hScanNoValues(final String key, final String cursor) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add("NOVALUES");
		return executeCommand(RedisCommand.HSCAN, args, (cmd)->cmd.hscanNoValues(key, cursor),
				(cmd)->cmd.hscanNoValues(key, cursor), (cmd)->cmd.hscanNoValues(key, cursor),
				new ScanResultConverter<>((k)->k));
	}

	@Override
	public ScanResult<byte[]> hScanNoValues(final byte[] key, final byte[] cursor) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add("NOVALUES");
		return executeCommand(RedisCommand.HSCAN, args, (cmd)->cmd.hscanNoValues(key, cursor),
				(cmd)->cmd.hscanNoValues(key, cursor), (cmd)->cmd.hscanNoValues(key, cursor),
				new ScanResultConverter<>((k)->k));
	}

	@Override
	public ScanResult<String> hScanNoValues(final String key, final String cursor, final String pattern) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(Keyword.Scan.MATCH, pattern)
				.add("NOVALUES");
		return hScanNoValues(key, cursor, new JedisScanParams(pattern), args);
	}

	@Override
	public ScanResult<byte[]> hScanNoValues(final byte[] key, final byte[] cursor, final byte[] pattern) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(Keyword.Scan.MATCH, pattern)
				.add("NOVALUES");
		return hScanNoValues(key, cursor, new JedisScanParams(pattern), args);
	}

	@Override
	public ScanResult<String> hScanNoValues(final String key, final String cursor, final String pattern,
	                                        final int count) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(Keyword.Scan.MATCH, pattern)
				.add(Keyword.Common.COUNT, count).add("NOVALUES");
		return hScanNoValues(key, cursor, new JedisScanParams(pattern, count), args);
	}

	@Override
	public ScanResult<byte[]> hScanNoValues(final byte[] key, final byte[] cursor, final byte[] pattern,
	                                        final int count) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(Keyword.Scan.MATCH, pattern)
				.add(Keyword.Common.COUNT, count).add("NOVALUES");
		return hScanNoValues(key, cursor, new JedisScanParams(pattern, count), args);
	}

	@Override
	public ScanResult<String> hScanNoValues(final String key, final String cursor, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(Keyword.Common.COUNT, count)
				.add("NOVALUES");
		return hScanNoValues(key, cursor, new JedisScanParams(count), args);
	}

	@Override
	public ScanResult<byte[]> hScanNoValues(final byte[] key, final byte[] cursor, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(Keyword.Common.COUNT, count)
				.add("NOVALUES");
		return hScanNoValues(key, cursor, new JedisScanParams(count), args);
	}

	@SuppressWarnings({"unchecked"})
	@Override
	public Long hSet(final String key, final KeyValue<String, String>... data) {
		final CommandArguments args = CommandArguments.create(key).add(data);
		final ArrayKeyValueMapConverter<String, String, String, String> arrayKeyValueMapConverter = new ArrayKeyValueMapConverter<>(
				(k)->k, (v)->v);
		return executeCommand(RedisCommand.HSET, args,
				(cmd)->cmd.hset(key, arrayKeyValueMapConverter.convert(data)),
				(cmd)->cmd.hset(key, arrayKeyValueMapConverter.convert(data)),
				(cmd)->cmd.hset(key, arrayKeyValueMapConverter.convert(data)));
	}

	@SuppressWarnings({"unchecked"})
	@Override
	public Long hSet(final byte[] key, final KeyValue<byte[], byte[]>... data) {
		final CommandArguments args = CommandArguments.create(key).add(data);
		final ArrayKeyValueMapConverter<byte[], byte[], byte[], byte[]> arrayKeyValueMapConverter = new ArrayKeyValueMapConverter<>(
				(k)->k, (v)->v);
		return executeCommand(RedisCommand.HSET, args,
				(cmd)->cmd.hset(key, arrayKeyValueMapConverter.convert(data)),
				(cmd)->cmd.hset(key, arrayKeyValueMapConverter.convert(data)),
				(cmd)->cmd.hset(key, arrayKeyValueMapConverter.convert(data)));
	}

	@SuppressWarnings({"unchecked"})
	@Override
	public Status hSetEx(final String key, final KeyValue<String, String>... data) {
		final CommandArguments args = CommandArguments.create(key).add(data);
		return hSetEx(key, data, new HSetExParams(), args);
	}

	@SuppressWarnings({"unchecked"})
	@Override
	public Status hSetEx(final byte[] key, final KeyValue<byte[], byte[]>... data) {
		final CommandArguments args = CommandArguments.create(key).add(data);
		return hSetEx(key, data, new HSetExParams(), args);
	}

	@SuppressWarnings({"unchecked"})
	@Override
	public Status hSetEx(final String key, final FnxFxx fnxFxx, final KeyValue<String, String>... data) {
		final CommandArguments args = CommandArguments.create(key).add(fnxFxx).add(data);
		return hSetEx(key, data, new JedisHSetExParams(fnxFxx), args);
	}

	@SuppressWarnings({"unchecked"})
	@Override
	public Status hSetEx(final byte[] key, final FnxFxx fnxFxx, final KeyValue<byte[], byte[]>... data) {
		final CommandArguments args = CommandArguments.create(key).add(fnxFxx).add(data);
		return hSetEx(key, data, new JedisHSetExParams(fnxFxx), args);
	}

	@SuppressWarnings({"unchecked"})
	@Override
	public Status hSetEx(final String key, final FnxFxx fnxFxx, final PxExType exType, final long expires,
	                     final KeyValue<String, String>... data) {
		final CommandArguments args = CommandArguments.create(key).add(fnxFxx).add(exType, expires).add(data);
		return hSetEx(key, data, new JedisHSetExParams(fnxFxx, exType, expires), args);
	}

	@SuppressWarnings({"unchecked"})
	@Override
	public Status hSetEx(final byte[] key, final FnxFxx fnxFxx, final PxExType exType, final long expires,
	                     final KeyValue<byte[], byte[]>... data) {
		final CommandArguments args = CommandArguments.create(key).add(fnxFxx).add(exType, expires).add(data);
		return hSetEx(key, data, new JedisHSetExParams(fnxFxx, exType, expires), args);
	}

	@SuppressWarnings({"unchecked"})
	@Override
	public Status hSetEx(final String key, final PxExType exType, final long expires,
	                     final KeyValue<String, String>... data) {
		final CommandArguments args = CommandArguments.create(key).add(exType, expires).add(data);
		return hSetEx(key, data, new JedisHSetExParams(exType, expires), args);
	}

	@SuppressWarnings({"unchecked"})
	@Override
	public Status hSetEx(final byte[] key, final PxExType exType, final long expires,
	                     final KeyValue<byte[], byte[]>... data) {
		final CommandArguments args = CommandArguments.create(key).add(exType, expires).add(data);
		return hSetEx(key, data, new JedisHSetExParams(exType, expires), args);
	}

	@Override
	public Status hSetNx(final String key, final String field, final String value) {
		final CommandArguments args = CommandArguments.create(key).add(field, value);
		return executeCommand(RedisCommand.HSETNX, args, (cmd)->cmd.hsetnx(key, field, value),
				(cmd)->cmd.hsetnx(key, field, value), (cmd)->cmd.hsetnx(key, field, value),
				new OneStatusConverter());
	}

	@Override
	public Status hSetNx(final byte[] key, final byte[] field, final byte[] value) {
		final CommandArguments args = CommandArguments.create(key).add(field, value);
		return executeCommand(RedisCommand.HSETNX, args, (cmd)->cmd.hsetnx(key, field, value),
				(cmd)->cmd.hsetnx(key, field, value), (cmd)->cmd.hsetnx(key, field, value),
				new OneStatusConverter());
	}

	@Override
	public Long hStrLen(final String key, final String field) {
		final CommandArguments args = CommandArguments.create(key).add(field);
		return executeCommand(RedisCommand.HSTRLEN, args, (cmd)->cmd.hstrlen(key, field),
				(cmd)->cmd.hstrlen(key, field), (cmd)->cmd.hstrlen(key, field));
	}

	@Override
	public Long hStrLen(final byte[] key, final byte[] field) {
		final CommandArguments args = CommandArguments.create(key).add(field);
		return executeCommand(RedisCommand.HSTRLEN, args, (cmd)->cmd.hstrlen(key, field),
				(cmd)->cmd.hstrlen(key, field), (cmd)->cmd.hstrlen(key, field));
	}

	@Override
	public List<Long> hTtl(final String key, final String... fields) {
		final CommandArguments args = CommandArguments.create(key).add(Keyword.Hash.FIELDS, fields.length).add(fields);
		return executeCommand(RedisCommand.HTTL, args, (cmd)->cmd.httl(key, fields),
				(cmd)->cmd.httl(key, fields), (cmd)->cmd.httl(key, fields));
	}

	@Override
	public List<Long> hTtl(final byte[] key, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(Keyword.Hash.FIELDS, fields.length).add(fields);
		return executeCommand(RedisCommand.HTTL, args, (cmd)->cmd.httl(key, fields),
				(cmd)->cmd.httl(key, fields), (cmd)->cmd.httl(key, fields));
	}

	@Override
	public List<String> hVals(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.HVALS, args, (cmd)->cmd.hvals(key), (cmd)->cmd.hvals(key),
				(cmd)->cmd.hvals(key));
	}

	@Override
	public List<byte[]> hVals(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.HVALS, args, (cmd)->cmd.hvals(key), (cmd)->cmd.hvals(key),
				(cmd)->cmd.hvals(key));
	}

	private List<String> hGetEx(final String key, final HGetExParams hGetExParams, final String[] fields,
	                            final CommandArguments args) {
		return executeCommand(RedisCommand.HGETEX, args, (cmd)->cmd.hgetex(key, hGetExParams, fields),
				(cmd)->cmd.hgetex(key, hGetExParams, fields), (cmd)->cmd.hgetex(key, hGetExParams, fields));
	}

	private List<byte[]> hGetEx(final byte[] key, final HGetExParams hGetExParams, final byte[][] fields,
	                            final CommandArguments args) {
		return executeCommand(RedisCommand.HGETEX, args, (cmd)->cmd.hgetex(key, hGetExParams, fields),
				(cmd)->cmd.hgetex(key, hGetExParams, fields), (cmd)->cmd.hgetex(key, hGetExParams, fields));
	}

	private ScanResult<KeyValue<String, String>> hScan(final String key, final String cursor,
	                                                   final ScanParams scanParams, final CommandArguments args) {
		return executeCommand(RedisCommand.HSCAN, args, (cmd)->cmd.hscan(key, cursor, scanParams),
				(cmd)->cmd.hscan(key, cursor, scanParams), (cmd)->cmd.hscan(key, cursor, scanParams),
				new ScanResultConverter<>(new MapEntryKeyValueConverter<>((k)->k, (v)->v)));
	}

	private ScanResult<KeyValue<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor,
	                                                   final ScanParams scanParams, final CommandArguments args) {
		return executeCommand(RedisCommand.HSCAN, args, (cmd)->cmd.hscan(key, cursor, scanParams),
				(cmd)->cmd.hscan(key, cursor, scanParams), (cmd)->cmd.hscan(key, cursor, scanParams),
				new ScanResultConverter<>(new MapEntryKeyValueConverter<>((k)->k, (v)->v)));
	}

	private ScanResult<String> hScanNoValues(final String key, final String cursor, final ScanParams scanParams,
	                                         final CommandArguments args) {
		return executeCommand(RedisCommand.HSCAN, args, (cmd)->cmd.hscanNoValues(key, cursor, scanParams),
				(cmd)->cmd.hscanNoValues(key, cursor, scanParams), (cmd)->cmd.hscanNoValues(key, cursor, scanParams),
				new ScanResultConverter<>((k)->k));
	}

	private ScanResult<byte[]> hScanNoValues(final byte[] key, final byte[] cursor, final ScanParams scanParams,
	                                         final CommandArguments args) {
		return executeCommand(RedisCommand.HSCAN, args, (cmd)->cmd.hscanNoValues(key, cursor, scanParams),
				(cmd)->cmd.hscanNoValues(key, cursor, scanParams), (cmd)->cmd.hscanNoValues(key, cursor, scanParams),
				new ScanResultConverter<>((k)->k));
	}

	private Status hSetEx(final String key, final KeyValue<String, String>[] data, final HSetExParams hSetExParams,
	                      final CommandArguments args) {
		final ArrayKeyValueMapConverter<String, String, String, String> arrayKeyValueMapConverter = new ArrayKeyValueMapConverter<>(
				(k)->k, (v)->v);
		return executeCommand(RedisCommand.HSETEX, args,
				(cmd)->cmd.hsetex(key, hSetExParams, arrayKeyValueMapConverter.convert(data)),
				(cmd)->cmd.hsetex(key, hSetExParams, arrayKeyValueMapConverter.convert(data)),
				(cmd)->cmd.hsetex(key, hSetExParams, arrayKeyValueMapConverter.convert(data)),
				new OneStatusConverter());
	}

	private Status hSetEx(final byte[] key, final KeyValue<byte[], byte[]>[] data, final HSetExParams hSetExParams,
	                      final CommandArguments args) {
		final ArrayKeyValueMapConverter<byte[], byte[], byte[], byte[]> arrayKeyValueMapConverter = new ArrayKeyValueMapConverter<>(
				(k)->k, (v)->v);
		return executeCommand(RedisCommand.HSETEX, args,
				(cmd)->cmd.hsetex(key, hSetExParams, arrayKeyValueMapConverter.convert(data)),
				(cmd)->cmd.hsetex(key, hSetExParams, arrayKeyValueMapConverter.convert(data)),
				(cmd)->cmd.hsetex(key, hSetExParams, arrayKeyValueMapConverter.convert(data)),
				new OneStatusConverter());
	}

}
