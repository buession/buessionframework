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
import com.buession.redis.client.jedis.JedisSentinelClient;
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.Command;
import com.buession.redis.core.internal.convert.jedis.response.ScanResultConverter;
import com.buession.redis.core.internal.jedis.JedisScanParams;
import redis.clients.jedis.params.ScanParams;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Jedis 哨兵模式哈希表命令操作
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public final class JedisSentinelHashOperations extends AbstractHashOperations<JedisSentinelClient> {

	public JedisSentinelHashOperations(final JedisSentinelClient client) {
		super(client);
	}

	@Override
	public Long hDel(final String key, final String... fields) {
		final CommandArguments args = CommandArguments.create("key", key).put("fields", (Object[]) fields);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.HDEL, (cmd)->cmd.hdel(key, fields),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.HDEL, (cmd)->cmd.hdel(key, fields),
					(v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.HDEL, (cmd)->cmd.hdel(key, fields), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long hDel(final byte[] key, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create("key", key).put("fields", (Object[]) fields);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.HDEL, (cmd)->cmd.hdel(key, fields),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.HDEL, (cmd)->cmd.hdel(key, fields),
					(v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.HDEL, (cmd)->cmd.hdel(key, fields), (v)->v)
					.run(args);
		}
	}

	@Override
	public Boolean hExists(final String key, final String field) {
		final CommandArguments args = CommandArguments.create("key", key).put("field", field);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.HEXISTS, (cmd)->cmd.hexists(key, field),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.HEXISTS,
					(cmd)->cmd.hexists(key, field), (v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.HEXISTS, (cmd)->cmd.hexists(key, field), (v)->v)
					.run(args);
		}
	}

	@Override
	public Boolean hExists(final byte[] key, final byte[] field) {
		final CommandArguments args = CommandArguments.create("key", key).put("field", field);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.HEXISTS, (cmd)->cmd.hexists(key, field),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.HEXISTS,
					(cmd)->cmd.hexists(key, field), (v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.HEXISTS, (cmd)->cmd.hexists(key, field), (v)->v)
					.run(args);
		}
	}

	@Override
	public String hGet(final String key, final String field) {
		final CommandArguments args = CommandArguments.create("key", key).put("field", field);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.HGET, (cmd)->cmd.hget(key, field), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.HGET, (cmd)->cmd.hget(key, field),
					(v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.HGET, (cmd)->cmd.hget(key, field), (v)->v)
					.run(args);
		}
	}

	@Override
	public byte[] hGet(final byte[] key, final byte[] field) {
		final CommandArguments args = CommandArguments.create("key", key).put("field", field);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.HGET, (cmd)->cmd.hget(key, field), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.HGET, (cmd)->cmd.hget(key, field),
					(v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.HGET, (cmd)->cmd.hget(key, field), (v)->v)
					.run(args);
		}
	}

	@Override
	public Map<String, String> hGetAll(final String key) {
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.HGETALL, (cmd)->cmd.hgetAll(key), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.HGETALL, (cmd)->cmd.hgetAll(key),
					(v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.HGETALL, (cmd)->cmd.hgetAll(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public Map<byte[], byte[]> hGetAll(final byte[] key) {
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.HGETALL, (cmd)->cmd.hgetAll(key), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.HGETALL, (cmd)->cmd.hgetAll(key),
					(v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.HGETALL, (cmd)->cmd.hgetAll(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long hIncrBy(final String key, final String field, final long value) {
		final CommandArguments args = CommandArguments.create("key", key).put("field", field).put("value", value);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.HINCRBY,
					(cmd)->cmd.hincrBy(key, field, value), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.HINCRBY,
					(cmd)->cmd.hincrBy(key, field, value), (v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.HINCRBY, (cmd)->cmd.hincrBy(key, field, value),
					(v)->v)
					.run(args);
		}
	}

	@Override
	public Long hIncrBy(final byte[] key, final byte[] field, final long value) {
		final CommandArguments args = CommandArguments.create("key", key).put("field", field).put("value", value);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.HINCRBY,
					(cmd)->cmd.hincrBy(key, field, value), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.HINCRBY,
					(cmd)->cmd.hincrBy(key, field, value), (v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.HINCRBY, (cmd)->cmd.hincrBy(key, field, value),
					(v)->v)
					.run(args);
		}
	}

	@Override
	public Double hIncrByFloat(final String key, final String field, final double value) {
		final CommandArguments args = CommandArguments.create("key", key).put("field", field).put("value", value);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.HINCRBYFLOAT,
					(cmd)->cmd.hincrByFloat(key, field, value), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.HINCRBYFLOAT,
					(cmd)->cmd.hincrByFloat(key, field, value), (v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.HINCRBYFLOAT,
					(cmd)->cmd.hincrByFloat(key, field, value), (v)->v)
					.run(args);
		}
	}

	@Override
	public Double hIncrByFloat(final byte[] key, final byte[] field, final double value) {
		final CommandArguments args = CommandArguments.create("key", key).put("field", field).put("value", value);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.HINCRBYFLOAT,
					(cmd)->cmd.hincrByFloat(key, field, value), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.HINCRBYFLOAT,
					(cmd)->cmd.hincrByFloat(key, field, value), (v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.HINCRBYFLOAT,
					(cmd)->cmd.hincrByFloat(key, field, value), (v)->v)
					.run(args);
		}
	}

	@Override
	public Set<String> hKeys(final String key) {
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.HKEYS, (cmd)->cmd.hkeys(key), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.HKEYS, (cmd)->cmd.hkeys(key), (v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.HKEYS, (cmd)->cmd.hkeys(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public Set<byte[]> hKeys(final byte[] key) {
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.HKEYS, (cmd)->cmd.hkeys(key), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.HKEYS, (cmd)->cmd.hkeys(key), (v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.HKEYS, (cmd)->cmd.hkeys(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long hLen(final String key) {
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.HLEN, (cmd)->cmd.hlen(key), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.HLEN, (cmd)->cmd.hlen(key), (v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.HLEN, (cmd)->cmd.hlen(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long hLen(final byte[] key) {
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.HLEN, (cmd)->cmd.hlen(key), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.HLEN, (cmd)->cmd.hlen(key), (v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.HLEN, (cmd)->cmd.hlen(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public List<String> hMGet(final String key, final String... fields) {
		final CommandArguments args = CommandArguments.create("key", key).put("fields", (Object[]) fields);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.HMGET, (cmd)->cmd.hmget(key, fields),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.HMGET, (cmd)->cmd.hmget(key, fields),
					(v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.HMGET, (cmd)->cmd.hmget(key, fields), (v)->v)
					.run(args);
		}
	}

	@Override
	public List<byte[]> hMGet(final byte[] key, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create("key", key).put("fields", (Object[]) fields);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.HMGET, (cmd)->cmd.hmget(key, fields),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.HMGET, (cmd)->cmd.hmget(key, fields),
					(v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.HMGET, (cmd)->cmd.hmget(key, fields), (v)->v)
					.run(args);
		}
	}

	@Override
	public Status hMSet(final String key, final Map<String, String> data) {
		final CommandArguments args = CommandArguments.create("key", key).put("data", data);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.HMGET, (cmd)->cmd.hmset(key, data),
					okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.HMGET, (cmd)->cmd.hmset(key, data),
					okStatusConverter)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.HMGET, (cmd)->cmd.hmset(key, data),
					okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status hMSet(final byte[] key, final Map<byte[], byte[]> data) {
		final CommandArguments args = CommandArguments.create("key", key).put("data", data);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.HMGET, (cmd)->cmd.hmset(key, data),
					okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.HMGET, (cmd)->cmd.hmset(key, data),
					okStatusConverter)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.HMGET, (cmd)->cmd.hmset(key, data),
					okStatusConverter)
					.run(args);
		}
	}

	@Override
	public String hRandField(final String key) {
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.HRANDFIELD, (cmd)->cmd.hrandfield(key),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.HRANDFIELD, (cmd)->cmd.hrandfield(key),
					(v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.HRANDFIELD, (cmd)->cmd.hrandfield(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public byte[] hRandField(final byte[] key) {
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.HRANDFIELD, (cmd)->cmd.hrandfield(key),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.HRANDFIELD, (cmd)->cmd.hrandfield(key),
					(v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.HRANDFIELD, (cmd)->cmd.hrandfield(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public List<String> hRandField(final String key, final int count) {
		final CommandArguments args = CommandArguments.create("key", key).put("count", count);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.HRANDFIELD,
					(cmd)->cmd.hrandfield(key, count), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.HRANDFIELD,
					(cmd)->cmd.hrandfield(key, count), (v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.HRANDFIELD, (cmd)->cmd.hrandfield(key, count),
					(v)->v)
					.run(args);
		}
	}

	@Override
	public List<byte[]> hRandField(final byte[] key, final int count) {
		final CommandArguments args = CommandArguments.create("key", key).put("count", count);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.HRANDFIELD,
					(cmd)->cmd.hrandfield(key, count), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.HRANDFIELD,
					(cmd)->cmd.hrandfield(key, count), (v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.HRANDFIELD, (cmd)->cmd.hrandfield(key, count),
					(v)->v)
					.run(args);
		}
	}

	@Override
	public List<KeyValue<String, String>> hRandFieldWithValues(final String key, final int count) {
		final CommandArguments args = CommandArguments.create("key", key).put("count", count);
		final ListConverter<Map.Entry<String, String>, KeyValue<String, String>> converter =
				new ListConverter<>(new MapEntryKeyValueConverter<>((k)->k, (v)->v));

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.HRANDFIELD,
					(cmd)->cmd.hrandfieldWithValues(key, count), converter)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.HRANDFIELD,
					(cmd)->cmd.hrandfieldWithValues(key, count), converter)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.HRANDFIELD,
					(cmd)->cmd.hrandfieldWithValues(key, count), converter)
					.run(args);
		}
	}

	@Override
	public List<KeyValue<byte[], byte[]>> hRandFieldWithValues(final byte[] key, final int count) {
		final CommandArguments args = CommandArguments.create("key", key).put("count", count);
		final ListConverter<Map.Entry<byte[], byte[]>, KeyValue<byte[], byte[]>> converter =
				new ListConverter<>(new MapEntryKeyValueConverter<>((k)->k, (v)->v));

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.HRANDFIELD,
					(cmd)->cmd.hrandfieldWithValues(key, count), converter)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.HRANDFIELD,
					(cmd)->cmd.hrandfieldWithValues(key, count), converter)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.HRANDFIELD,
					(cmd)->cmd.hrandfieldWithValues(key, count), converter)
					.run(args);
		}
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final String cursor) {
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor);
		final ScanResultConverter.MapScanResultConverter<String, String> mapScanResultConverter =
				new ScanResultConverter.MapScanResultConverter<>();

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.HSCAN, (cmd)->cmd.hscan(key, cursor),
					mapScanResultConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.HSCAN, (cmd)->cmd.hscan(key, cursor),
					mapScanResultConverter)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.HSCAN, (cmd)->cmd.hscan(key, cursor),
					mapScanResultConverter)
					.run(args);
		}
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor) {
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor);
		final ScanResultConverter.MapScanResultConverter<byte[], byte[]> mapScanResultConverter =
				new ScanResultConverter.MapScanResultConverter<>();

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.HSCAN, (cmd)->cmd.hscan(key, cursor),
					mapScanResultConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.HSCAN, (cmd)->cmd.hscan(key, cursor),
					mapScanResultConverter)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.HSCAN, (cmd)->cmd.hscan(key, cursor),
					mapScanResultConverter)
					.run(args);
		}
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final String cursor, final String pattern) {
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor).put("pattern", pattern);
		final ScanParams scanParams = new JedisScanParams(pattern);

		return hScan(key, cursor, scanParams, args);
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor, final byte[] pattern) {
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor).put("pattern", pattern);
		final ScanParams scanParams = new JedisScanParams(pattern);

		return hScan(key, cursor, scanParams, args);
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final String cursor, final int count) {
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor).put("count", count);
		final ScanParams scanParams = new JedisScanParams(count);

		return hScan(key, cursor, scanParams, args);
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor, final int count) {
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor).put("count", count);
		final ScanParams scanParams = new JedisScanParams(count);

		return hScan(key, cursor, scanParams, args);
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final String cursor, final String pattern,
												 final int count) {
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor).put("pattern", pattern);
		final ScanParams scanParams = new JedisScanParams(pattern, count);

		return hScan(key, cursor, scanParams, args);
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor, final byte[] pattern,
												 final int count) {
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor).put("pattern", pattern)
				.put("count", count);
		final ScanParams scanParams = new JedisScanParams(pattern, count);

		return hScan(key, cursor, scanParams, args);
	}

	@Override
	public Long hSet(final String key, final String field, final String value) {
		final CommandArguments args = CommandArguments.create("key", key).put("field", field).put("value", value);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.HSET, (cmd)->cmd.hset(key, field, value),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.HSET,
					(cmd)->cmd.hset(key, field, value), (v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.HSET, (cmd)->cmd.hset(key, field, value), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long hSet(final byte[] key, final byte[] field, final byte[] value) {
		final CommandArguments args = CommandArguments.create("key", key).put("field", field).put("value", value);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.HSET, (cmd)->cmd.hset(key, field, value),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.HSET,
					(cmd)->cmd.hset(key, field, value), (v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.HSET, (cmd)->cmd.hset(key, field, value), (v)->v)
					.run(args);
		}
	}

	@Override
	public Status hSetNx(final String key, final String field, final String value) {
		final CommandArguments args = CommandArguments.create("key", key).put("field", field).put("value", value);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.HSETNX,
					(cmd)->cmd.hsetnx(key, field, value), oneStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.HSETNX,
					(cmd)->cmd.hsetnx(key, field, value), oneStatusConverter)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.HSETNX, (cmd)->cmd.hsetnx(key, field, value),
					oneStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status hSetNx(final byte[] key, final byte[] field, final byte[] value) {
		final CommandArguments args = CommandArguments.create("key", key).put("field", field).put("value", value);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.HSETNX,
					(cmd)->cmd.hsetnx(key, field, value), oneStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.HSETNX,
					(cmd)->cmd.hsetnx(key, field, value), oneStatusConverter)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.HSETNX, (cmd)->cmd.hsetnx(key, field, value),
					oneStatusConverter)
					.run(args);
		}
	}

	@Override
	public Long hStrLen(final String key, final String field) {
		final CommandArguments args = CommandArguments.create("key", key).put("field", field);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.HSTRLEN, (cmd)->cmd.hstrlen(key, field),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.HSTRLEN,
					(cmd)->cmd.hstrlen(key, field), (v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.HSTRLEN, (cmd)->cmd.hstrlen(key, field), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long hStrLen(final byte[] key, final byte[] field) {
		final CommandArguments args = CommandArguments.create("key", key).put("field", field);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.HSTRLEN, (cmd)->cmd.hstrlen(key, field),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.HSTRLEN,
					(cmd)->cmd.hstrlen(key, field), (v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.HSTRLEN, (cmd)->cmd.hstrlen(key, field), (v)->v)
					.run(args);
		}
	}

	@Override
	public List<String> hVals(final String key) {
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.HVALS, (cmd)->cmd.hvals(key), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.HVALS, (cmd)->cmd.hvals(key), (v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.HVALS, (cmd)->cmd.hvals(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public List<byte[]> hVals(final byte[] key) {
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.HVALS, (cmd)->cmd.hvals(key), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.HVALS, (cmd)->cmd.hvals(key), (v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.HVALS, (cmd)->cmd.hvals(key), (v)->v)
					.run(args);
		}
	}

	private ScanResult<Map<String, String>> hScan(final String key, final String cursor, final ScanParams scanParams,
												  final CommandArguments args) {
		final ScanResultConverter.MapScanResultConverter<String, String> mapScanResultConverter =
				new ScanResultConverter.MapScanResultConverter<>();

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.HSCAN,
					(cmd)->cmd.hscan(key, cursor, scanParams), mapScanResultConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.HSCAN,
					(cmd)->cmd.hscan(key, cursor, scanParams), mapScanResultConverter)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.HSCAN, (cmd)->cmd.hscan(key, cursor, scanParams),
					mapScanResultConverter)
					.run(args);
		}
	}

	private ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor, final ScanParams scanParams,
												  final CommandArguments args) {
		final ScanResultConverter.MapScanResultConverter<byte[], byte[]> mapScanResultConverter =
				new ScanResultConverter.MapScanResultConverter<>();

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.HSCAN,
					(cmd)->cmd.hscan(key, cursor, scanParams), mapScanResultConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.HSCAN,
					(cmd)->cmd.hscan(key, cursor, scanParams), mapScanResultConverter)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.HSCAN, (cmd)->cmd.hscan(key, cursor, scanParams),
					mapScanResultConverter)
					.run(args);
		}
	}

}
