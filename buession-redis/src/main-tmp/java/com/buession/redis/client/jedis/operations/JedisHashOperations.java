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
package com.buession.redis.client.jedis.operations;

import com.buession.core.converter.ListConverter;
import com.buession.core.converter.MapEntryKeyValueConverter;
import com.buession.lang.KeyValue;
import com.buession.lang.Status;
import com.buession.redis.client.jedis.JedisStandaloneClient;
import com.buession.redis.core.ExpireOption;
import com.buession.redis.core.Keyword;
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.Command;
import com.buession.redis.core.command.args.HScanArgument;
import com.buession.redis.core.internal.convert.jedis.response.ScanResultConverter;
import com.buession.redis.core.internal.jedis.JedisScanParams;
import redis.clients.jedis.params.ScanParams;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Jedis 单机模式模式哈希表命令操作
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public final class JedisHashOperations extends AbstractHashOperations<JedisStandaloneClient> {

	public JedisHashOperations(final JedisStandaloneClient client) {
		super(client);
	}

	@Override
	public Long hDel(final String key, final String... fields) {
		final CommandArguments args = CommandArguments.create(key).add(fields);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, Command.HDEL, (cmd)->cmd.hdel(key, fields), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, Command.HDEL, (cmd)->cmd.hdel(key, fields), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, Command.HDEL, (cmd)->cmd.hdel(key, fields), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long hDel(final byte[] key, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(fields);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, Command.HDEL, (cmd)->cmd.hdel(key, fields), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, Command.HDEL, (cmd)->cmd.hdel(key, fields), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, Command.HDEL, (cmd)->cmd.hdel(key, fields), (v)->v)
					.run(args);
		}
	}

	@Override
	public Boolean hExists(final String key, final String field) {
		final CommandArguments args = CommandArguments.create(key).add(field);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, Command.HEXISTS, (cmd)->cmd.hexists(key, field), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, Command.HEXISTS, (cmd)->cmd.hexists(key, field), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, Command.HEXISTS, (cmd)->cmd.hexists(key, field), (v)->v)
					.run(args);
		}
	}

	@Override
	public Boolean hExists(final byte[] key, final byte[] field) {
		final CommandArguments args = CommandArguments.create(key).add(field);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, Command.HEXISTS, (cmd)->cmd.hexists(key, field), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, Command.HEXISTS, (cmd)->cmd.hexists(key, field), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, Command.HEXISTS, (cmd)->cmd.hexists(key, field), (v)->v)
					.run(args);
		}
	}

	@Override
	public List<Long> hExpire(final String key, final int lifetime, final String... fields) {
		final CommandArguments args = CommandArguments.create(key).add(lifetime).add(Keyword.Hash.FIELDS, fields.length)
				.add(fields);
		return notCommand(client, Command.HEXPIRE, args);
	}

	@Override
	public List<Long> hExpire(final byte[] key, final int lifetime, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(lifetime).add(Keyword.Hash.FIELDS, fields.length)
				.add(fields);
		return notCommand(client, Command.HEXPIRE, args);
	}

	@Override
	public List<Long> hExpire(final String key, final int lifetime, final ExpireOption expireOption,
							  final String... fields) {
		final CommandArguments args = CommandArguments.create(key).add(lifetime).add(expireOption)
				.add(Keyword.Hash.FIELDS, fields.length).add(fields);
		return notCommand(client, Command.HEXPIRE, args);
	}

	@Override
	public List<Long> hExpire(final byte[] key, final int lifetime, final ExpireOption expireOption,
							  final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(lifetime).add(expireOption)
				.add(Keyword.Hash.FIELDS, fields.length).add(fields);
		return notCommand(client, Command.HEXPIRE, args);
	}

	@Override
	public List<Long> hExpireAt(final byte[] key, final long unixTimestamp, final ExpireOption expireOption,
								final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(unixTimestamp).add(expireOption)
				.add(Keyword.Hash.FIELDS, fields.length).add(fields);
		return notCommand(client, Command.HEXPIREAT, args);
	}

	@Override
	public List<Long> hExpireAt(final String key, final long unixTimestamp, final String... fields) {
		final CommandArguments args = CommandArguments.create(key).add(unixTimestamp)
				.add(Keyword.Hash.FIELDS, fields.length).add(fields);
		return notCommand(client, Command.HEXPIREAT, args);
	}

	@Override
	public List<Long> hExpireAt(final byte[] key, final long unixTimestamp, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(unixTimestamp)
				.add(Keyword.Hash.FIELDS, fields.length).add(fields);
		return notCommand(client, Command.HEXPIREAT, args);
	}

	@Override
	public List<Long> hExpireAt(final String key, final long unixTimestamp, final ExpireOption expireOption,
								final String... fields) {
		final CommandArguments args = CommandArguments.create(key).add(unixTimestamp).add(expireOption)
				.add(Keyword.Hash.FIELDS, fields.length).add(fields);
		return notCommand(client, Command.HEXPIREAT, args);
	}

	@Override
	public List<Long> hExpireTime(final String key, final String... fields) {
		final CommandArguments args = CommandArguments.create(key).add(Keyword.Hash.FIELDS, fields.length).add(fields);
		return notCommand(client, Command.HEXPIRETIME, args);
	}

	@Override
	public List<Long> hExpireTime(final byte[] key, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(Keyword.Hash.FIELDS, fields.length).add(fields);
		return notCommand(client, Command.HEXPIRETIME, args);
	}

	@Override
	public String hGet(final String key, final String field) {
		final CommandArguments args = CommandArguments.create(key).add(field);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, Command.HGET, (cmd)->cmd.hget(key, field), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, Command.HGET, (cmd)->cmd.hget(key, field), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, Command.HGET, (cmd)->cmd.hget(key, field), (v)->v)
					.run(args);
		}
	}

	@Override
	public byte[] hGet(final byte[] key, final byte[] field) {
		final CommandArguments args = CommandArguments.create(key).add(field);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, Command.HGET, (cmd)->cmd.hget(key, field), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, Command.HGET, (cmd)->cmd.hget(key, field), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, Command.HGET, (cmd)->cmd.hget(key, field), (v)->v)
					.run(args);
		}
	}

	@Override
	public Map<String, String> hGetAll(final String key) {
		final CommandArguments args = CommandArguments.create(key);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, Command.HGETALL, (cmd)->cmd.hgetAll(key), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, Command.HGETALL, (cmd)->cmd.hgetAll(key), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, Command.HGETALL, (cmd)->cmd.hgetAll(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public Map<byte[], byte[]> hGetAll(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, Command.HGETALL, (cmd)->cmd.hgetAll(key), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, Command.HGETALL, (cmd)->cmd.hgetAll(key), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, Command.HGETALL, (cmd)->cmd.hgetAll(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long hIncrBy(final String key, final String field, final long value) {
		final CommandArguments args = CommandArguments.create(key).add(field).add(value);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, Command.HINCRBY, (cmd)->cmd.hincrBy(key, field, value), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, Command.HINCRBY, (cmd)->cmd.hincrBy(key, field, value), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, Command.HINCRBY, (cmd)->cmd.hincrBy(key, field, value), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long hIncrBy(final byte[] key, final byte[] field, final long value) {
		final CommandArguments args = CommandArguments.create(key).add(field).add(value);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, Command.HINCRBY, (cmd)->cmd.hincrBy(key, field, value), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, Command.HINCRBY, (cmd)->cmd.hincrBy(key, field, value), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, Command.HINCRBY, (cmd)->cmd.hincrBy(key, field, value), (v)->v)
					.run(args);
		}
	}

	@Override
	public Double hIncrByFloat(final String key, final String field, final double value) {
		final CommandArguments args = CommandArguments.create(key).add(field).add(value);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, Command.HINCRBYFLOAT, (cmd)->cmd.hincrByFloat(key, field, value),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, Command.HINCRBYFLOAT,
					(cmd)->cmd.hincrByFloat(key, field, value), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, Command.HINCRBYFLOAT, (cmd)->cmd.hincrByFloat(key, field, value), (v)->v)
					.run(args);
		}
	}

	@Override
	public Double hIncrByFloat(final byte[] key, final byte[] field, final double value) {
		final CommandArguments args = CommandArguments.create(key).add(field).add(value);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, Command.HINCRBYFLOAT, (cmd)->cmd.hincrByFloat(key, field, value),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, Command.HINCRBYFLOAT,
					(cmd)->cmd.hincrByFloat(key, field, value), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, Command.HINCRBYFLOAT, (cmd)->cmd.hincrByFloat(key, field, value), (v)->v)
					.run(args);
		}
	}

	@Override
	public Set<String> hKeys(final String key) {
		final CommandArguments args = CommandArguments.create(key);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, Command.HKEYS, (cmd)->cmd.hkeys(key), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, Command.HKEYS, (cmd)->cmd.hkeys(key), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, Command.HKEYS, (cmd)->cmd.hkeys(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public Set<byte[]> hKeys(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, Command.HKEYS, (cmd)->cmd.hkeys(key), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, Command.HKEYS, (cmd)->cmd.hkeys(key), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, Command.HKEYS, (cmd)->cmd.hkeys(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long hLen(final String key) {
		final CommandArguments args = CommandArguments.create(key);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, Command.HLEN, (cmd)->cmd.hlen(key), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, Command.HLEN, (cmd)->cmd.hlen(key), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, Command.HLEN, (cmd)->cmd.hlen(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long hLen(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, Command.HLEN, (cmd)->cmd.hlen(key), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, Command.HLEN, (cmd)->cmd.hlen(key), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, Command.HLEN, (cmd)->cmd.hlen(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public List<String> hMGet(final String key, final String... fields) {
		final CommandArguments args = CommandArguments.create(key).add(fields);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, Command.HMGET, (cmd)->cmd.hmget(key, fields), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, Command.HMGET, (cmd)->cmd.hmget(key, fields), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, Command.HMGET, (cmd)->cmd.hmget(key, fields), (v)->v)
					.run(args);
		}
	}

	@Override
	public List<byte[]> hMGet(final byte[] key, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(fields);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, Command.HMGET, (cmd)->cmd.hmget(key, fields), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, Command.HMGET, (cmd)->cmd.hmget(key, fields), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, Command.HMGET, (cmd)->cmd.hmget(key, fields), (v)->v)
					.run(args);
		}
	}

	@Override
	public Status hMSet(final String key, final Map<String, String> data) {
		final CommandArguments args = CommandArguments.create(key).add(data);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, Command.HMSET, (cmd)->cmd.hmset(key, data), okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, Command.HMSET, (cmd)->cmd.hmset(key, data), okStatusConverter)
					.run(args);
		}else{
			return new JedisCommand<>(client, Command.HMSET, (cmd)->cmd.hmset(key, data), okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status hMSet(final byte[] key, final Map<byte[], byte[]> data) {
		final CommandArguments args = CommandArguments.create(key).add(data);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, Command.HMSET, (cmd)->cmd.hmset(key, data), okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, Command.HMSET, (cmd)->cmd.hmset(key, data), okStatusConverter)
					.run(args);
		}else{
			return new JedisCommand<>(client, Command.HMSET, (cmd)->cmd.hmset(key, data), okStatusConverter)
					.run(args);
		}
	}

	@Override
	public List<Long> hPersist(final String key, final String... fields) {
		final CommandArguments args = CommandArguments.create(key).add(Keyword.Hash.FIELDS, fields);
		return notCommand(client, Command.HPERSIST, args);
	}

	@Override
	public List<Long> hPersist(final byte[] key, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(Keyword.Hash.FIELDS, fields);
		return notCommand(client, Command.HPERSIST, args);
	}

	@Override
	public List<Long> hpExpire(final String key, final int lifetime, final String... fields) {
		final CommandArguments args = CommandArguments.create(key).add(lifetime).add(Keyword.Hash.FIELDS, fields);
		return notCommand(client, Command.HPEXPIRE, args);
	}

	@Override
	public List<Long> hpExpire(final byte[] key, final int lifetime, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(lifetime).add(Keyword.Hash.FIELDS, fields);
		return notCommand(client, Command.HPEXPIRE, args);
	}

	@Override
	public List<Long> hpExpire(final String key, final int lifetime, final ExpireOption expireOption,
							   final String... fields) {
		final CommandArguments args =
				CommandArguments.create(key).add(lifetime).add(expireOption).add(Keyword.Hash.FIELDS, fields);
		return notCommand(client, Command.HPEXPIRE, args);
	}

	@Override
	public List<Long> hpExpire(final byte[] key, final int lifetime, final ExpireOption expireOption,
							   final byte[]... fields) {
		final CommandArguments args =
				CommandArguments.create(key).add(lifetime).add(expireOption).add(Keyword.Hash.FIELDS, fields);
		return notCommand(client, Command.HPEXPIRE, args);
	}

	@Override
	public List<Long> hpExpireAt(final String key, final long unixTimestamp, final String... fields) {
		final CommandArguments args = CommandArguments.create(key).add(unixTimestamp).add(Keyword.Hash.FIELDS, fields);
		return notCommand(client, Command.HPEXPIREAT, args);
	}

	@Override
	public List<Long> hpExpireAt(final byte[] key, final long unixTimestamp, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(unixTimestamp).add(Keyword.Hash.FIELDS, fields);
		return notCommand(client, Command.HPEXPIREAT, args);
	}

	@Override
	public List<Long> hpExpireAt(final String key, final long unixTimestamp, final ExpireOption expireOption,
								 final String... fields) {
		final CommandArguments args = CommandArguments.create(key).add(unixTimestamp).add(expireOption)
				.add(Keyword.Hash.FIELDS, fields);
		return notCommand(client, Command.HPEXPIREAT, args);
	}

	@Override
	public List<Long> hpExpireAt(final byte[] key, final long unixTimestamp, final ExpireOption expireOption,
								 final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(unixTimestamp).add(expireOption)
				.add(Keyword.Hash.FIELDS, fields);
		return notCommand(client, Command.HPEXPIREAT, args);
	}

	@Override
	public List<Long> hpExpireTime(final String key, final String... fields) {
		final CommandArguments args = CommandArguments.create(key).add(Keyword.Hash.FIELDS, fields);
		return notCommand(client, Command.HPEXPIRETIME, args);
	}

	@Override
	public List<Long> hpExpireTime(final byte[] key, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(Keyword.Hash.FIELDS, fields);
		return notCommand(client, Command.HPEXPIRETIME, args);
	}

	@Override
	public List<Long> hpTtl(final String key, final String... fields) {
		final CommandArguments args = CommandArguments.create(key).add(Keyword.Hash.FIELDS, fields);
		return notCommand(client, Command.HPTTL, args);
	}

	@Override
	public List<Long> hpTtl(final byte[] key, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(Keyword.Hash.FIELDS, fields);
		return notCommand(client, Command.HPTTL, args);
	}

	@Override
	public String hRandField(final String key) {
		final CommandArguments args = CommandArguments.create(key);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, Command.HRANDFIELD, (cmd)->cmd.hrandfield(key), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, Command.HRANDFIELD, (cmd)->cmd.hrandfield(key), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, Command.HRANDFIELD, (cmd)->cmd.hrandfield(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public byte[] hRandField(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, Command.HRANDFIELD, (cmd)->cmd.hrandfield(key), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, Command.HRANDFIELD, (cmd)->cmd.hrandfield(key), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, Command.HRANDFIELD, (cmd)->cmd.hrandfield(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public List<String> hRandField(final String key, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(count);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, Command.HRANDFIELD, (cmd)->cmd.hrandfield(key, count), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, Command.HRANDFIELD, (cmd)->cmd.hrandfield(key, count), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, Command.HRANDFIELD, (cmd)->cmd.hrandfield(key, count), (v)->v)
					.run(args);
		}
	}

	@Override
	public List<byte[]> hRandField(final byte[] key, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(count);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, Command.HRANDFIELD, (cmd)->cmd.hrandfield(key, count), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, Command.HRANDFIELD, (cmd)->cmd.hrandfield(key, count), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, Command.HRANDFIELD, (cmd)->cmd.hrandfield(key, count), (v)->v)
					.run(args);
		}
	}

	@Override
	public List<KeyValue<String, String>> hRandFieldWithValues(final String key, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(count).add(Keyword.Hash.WITHVALUES);
		final ListConverter<Map.Entry<String, String>, KeyValue<String, String>> converter =
				new ListConverter<>(new MapEntryKeyValueConverter<>((k)->k, (v)->v));

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, Command.HRANDFIELD, (cmd)->cmd.hrandfieldWithValues(key, count),
					converter)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, Command.HRANDFIELD,
					(cmd)->cmd.hrandfieldWithValues(key, count), converter)
					.run(args);
		}else{
			return new JedisCommand<>(client, Command.HRANDFIELD, (cmd)->cmd.hrandfieldWithValues(key, count),
					converter)
					.run(args);
		}
	}

	@Override
	public List<KeyValue<byte[], byte[]>> hRandFieldWithValues(final byte[] key, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(count).add(Keyword.Hash.WITHVALUES);
		final ListConverter<Map.Entry<byte[], byte[]>, KeyValue<byte[], byte[]>> converter =
				new ListConverter<>(new MapEntryKeyValueConverter<>((k)->k, (v)->v));

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, Command.HRANDFIELD, (cmd)->cmd.hrandfieldWithValues(key, count),
					converter)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, Command.HRANDFIELD,
					(cmd)->cmd.hrandfieldWithValues(key, count), converter)
					.run(args);
		}else{
			return new JedisCommand<>(client, Command.HRANDFIELD, (cmd)->cmd.hrandfieldWithValues(key, count),
					converter)
					.run(args);
		}
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final String cursor) {
		final CommandArguments args = CommandArguments.create(key).add(cursor);
		final ScanResultConverter.MapScanResultConverter<String, String> mapScanResultConverter =
				new ScanResultConverter.MapScanResultConverter<>();

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, Command.HSCAN, (cmd)->cmd.hscan(key, cursor),
					mapScanResultConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, Command.HSCAN, (cmd)->cmd.hscan(key, cursor),
					mapScanResultConverter)
					.run(args);
		}else{
			return new JedisCommand<>(client, Command.HSCAN, (cmd)->cmd.hscan(key, cursor), mapScanResultConverter)
					.run(args);
		}
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor) {
		final CommandArguments args = CommandArguments.create(key).add(cursor);
		final ScanResultConverter.MapScanResultConverter<byte[], byte[]> mapScanResultConverter =
				new ScanResultConverter.MapScanResultConverter<>();

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, Command.HSCAN, (cmd)->cmd.hscan(key, cursor),
					mapScanResultConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, Command.HSCAN, (cmd)->cmd.hscan(key, cursor),
					mapScanResultConverter)
					.run(args);
		}else{
			return new JedisCommand<>(client, Command.HSCAN, (cmd)->cmd.hscan(key, cursor), mapScanResultConverter)
					.run(args);
		}
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final String cursor,
												 final HScanArgument<String> scanArgument) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(scanArgument);
		final ScanParams scanParams = JedisScanParams.from(scanArgument);
		final ScanResultConverter.MapScanResultConverter<String, String> mapScanResultConverter =
				new ScanResultConverter.MapScanResultConverter<>();

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, Command.HSCAN, (cmd)->cmd.hscan(key, cursor, scanParams),
					mapScanResultConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, Command.HSCAN, (cmd)->cmd.hscan(key, cursor, scanParams),
					mapScanResultConverter)
					.run(args);
		}else{
			return new JedisCommand<>(client, Command.HSCAN, (cmd)->cmd.hscan(key, cursor, scanParams),
					mapScanResultConverter)
					.run(args);
		}
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor,
												 final HScanArgument<byte[]> scanArgument) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(scanArgument);
		final ScanParams scanParams = JedisScanParams.from(scanArgument);
		final ScanResultConverter.MapScanResultConverter<byte[], byte[]> mapScanResultConverter =
				new ScanResultConverter.MapScanResultConverter<>();

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, Command.HSCAN, (cmd)->cmd.hscan(key, cursor, scanParams),
					mapScanResultConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, Command.HSCAN, (cmd)->cmd.hscan(key, cursor, scanParams),
					mapScanResultConverter)
					.run(args);
		}else{
			return new JedisCommand<>(client, Command.HSCAN, (cmd)->cmd.hscan(key, cursor, scanParams),
					mapScanResultConverter)
					.run(args);
		}
	}

	@Override
	public Long hSet(final String key, final KeyValue<String, String>... data) {
		final CommandArguments args = CommandArguments.create(key).add(data);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, Command.HSET, (cmd)->cmd.hset(key, field, value), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, Command.HSET, (cmd)->cmd.hset(key, field, value),
					(v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, Command.HSET, (cmd)->cmd.hset(key, field, value), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long hSet(final byte[] key, final KeyValue<byte[], byte[]>... data) {
		final CommandArguments args = CommandArguments.create(key).add(data);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, Command.HSET, (cmd)->cmd.hset(key, field, value), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, Command.HSET, (cmd)->cmd.hset(key, field, value),
					(v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, Command.HSET, (cmd)->cmd.hset(key, field, value), (v)->v)
					.run(args);
		}
	}

	@Override
	public Status hSetNx(final String key, final String field, final String value) {
		final CommandArguments args = CommandArguments.create(key).add(field).add(value);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, Command.HSETNX, (cmd)->cmd.hsetnx(key, field, value),
					oneStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, Command.HSETNX, (cmd)->cmd.hsetnx(key, field, value),
					oneStatusConverter)
					.run(args);
		}else{
			return new JedisCommand<>(client, Command.HSETNX, (cmd)->cmd.hsetnx(key, field, value), oneStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status hSetNx(final byte[] key, final byte[] field, final byte[] value) {
		final CommandArguments args = CommandArguments.create(key).add(field).add(value);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, Command.HSETNX, (cmd)->cmd.hsetnx(key, field, value),
					oneStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, Command.HSETNX, (cmd)->cmd.hsetnx(key, field, value),
					oneStatusConverter)
					.run(args);
		}else{
			return new JedisCommand<>(client, Command.HSETNX, (cmd)->cmd.hsetnx(key, field, value), oneStatusConverter)
					.run(args);
		}
	}

	@Override
	public Long hStrLen(final String key, final String field) {
		final CommandArguments args = CommandArguments.create(key).add(field);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, Command.HSTRLEN, (cmd)->cmd.hstrlen(key, field), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, Command.HSTRLEN, (cmd)->cmd.hstrlen(key, field), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, Command.HSTRLEN, (cmd)->cmd.hstrlen(key, field), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long hStrLen(final byte[] key, final byte[] field) {
		final CommandArguments args = CommandArguments.create(key).add(field);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, Command.HSTRLEN, (cmd)->cmd.hstrlen(key, field), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, Command.HSTRLEN, (cmd)->cmd.hstrlen(key, field), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, Command.HSTRLEN, (cmd)->cmd.hstrlen(key, field), (v)->v)
					.run(args);
		}
	}

	@Override
	public List<Long> hTtl(final String key, final String... fields) {
		final CommandArguments args = CommandArguments.create(key).add(Keyword.Hash.FIELDS, fields);
		return notCommand(client, Command.HPTTL, args);
	}

	@Override
	public List<Long> hTtl(final byte[] key, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(Keyword.Hash.FIELDS, fields);
		return notCommand(client, Command.HPTTL, args);
	}

	@Override
	public List<String> hVals(final String key) {
		final CommandArguments args = CommandArguments.create(key);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, Command.HVALS, (cmd)->cmd.hvals(key), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, Command.HVALS, (cmd)->cmd.hvals(key), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, Command.HVALS, (cmd)->cmd.hvals(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public List<byte[]> hVals(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, Command.HVALS, (cmd)->cmd.hvals(key), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, Command.HVALS, (cmd)->cmd.hvals(key), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, Command.HVALS, (cmd)->cmd.hvals(key), (v)->v)
					.run(args);
		}
	}

}
