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

import com.buession.core.converter.ListMapEntryMapConverter;
import com.buession.lang.Status;
import com.buession.redis.client.jedis.JedisStandaloneClient;
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.ProtocolCommand;
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
			return new JedisPipelineCommand<>(client, ProtocolCommand.HDEL, (cmd)->cmd.hdel(key, fields), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.HDEL, (cmd)->cmd.hdel(key, fields), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.HDEL, (cmd)->cmd.hdel(key, fields), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long hDel(final byte[] key, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(fields);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.HDEL, (cmd)->cmd.hdel(key, fields), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.HDEL, (cmd)->cmd.hdel(key, fields), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.HDEL, (cmd)->cmd.hdel(key, fields), (v)->v)
					.run(args);
		}
	}

	@Override
	public Boolean hExists(final String key, final String field) {
		final CommandArguments args = CommandArguments.create(key).add(field);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.HEXISTS, (cmd)->cmd.hexists(key, field), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.HEXISTS, (cmd)->cmd.hexists(key, field),
					(v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.HEXISTS, (cmd)->cmd.hexists(key, field), (v)->v)
					.run(args);
		}
	}

	@Override
	public Boolean hExists(final byte[] key, final byte[] field) {
		final CommandArguments args = CommandArguments.create(key).add(field);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.HEXISTS, (cmd)->cmd.hexists(key, field), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.HEXISTS, (cmd)->cmd.hexists(key, field),
					(v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.HEXISTS, (cmd)->cmd.hexists(key, field), (v)->v)
					.run(args);
		}
	}

	@Override
	public String hGet(final String key, final String field) {
		final CommandArguments args = CommandArguments.create(key).add(field);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.HGET, (cmd)->cmd.hget(key, field), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.HGET, (cmd)->cmd.hget(key, field), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.HGET, (cmd)->cmd.hget(key, field), (v)->v)
					.run(args);
		}
	}

	@Override
	public byte[] hGet(final byte[] key, final byte[] field) {
		final CommandArguments args = CommandArguments.create(key).add(field);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.HGET, (cmd)->cmd.hget(key, field), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.HGET, (cmd)->cmd.hget(key, field), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.HGET, (cmd)->cmd.hget(key, field), (v)->v)
					.run(args);
		}
	}

	@Override
	public Map<String, String> hGetAll(final String key) {
		final CommandArguments args = CommandArguments.create(key);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.HGETALL, (cmd)->cmd.hgetAll(key), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.HGETALL, (cmd)->cmd.hgetAll(key), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.HGETALL, (cmd)->cmd.hgetAll(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public Map<byte[], byte[]> hGetAll(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.HGETALL, (cmd)->cmd.hgetAll(key), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.HGETALL, (cmd)->cmd.hgetAll(key), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.HGETALL, (cmd)->cmd.hgetAll(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long hIncrBy(final String key, final String field, final long value) {
		final CommandArguments args = CommandArguments.create(key).add(field).add(value);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.HINCRBY, (cmd)->cmd.hincrBy(key, field, value),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.HINCRBY, (cmd)->cmd.hincrBy(key, field, value),
					(v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.HINCRBY, (cmd)->cmd.hincrBy(key, field, value),
					(v)->v)
					.run(args);
		}
	}

	@Override
	public Long hIncrBy(final byte[] key, final byte[] field, final long value) {
		final CommandArguments args = CommandArguments.create(key).add(field).add(value);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.HINCRBY, (cmd)->cmd.hincrBy(key, field, value),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.HINCRBY, (cmd)->cmd.hincrBy(key, field, value),
					(v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.HINCRBY, (cmd)->cmd.hincrBy(key, field, value),
					(v)->v)
					.run(args);
		}
	}

	@Override
	public Double hIncrByFloat(final String key, final String field, final double value) {
		final CommandArguments args = CommandArguments.create(key).add(field).add(value);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.HINCRBYFLOAT,
					(cmd)->cmd.hincrByFloat(key, field, value), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.HINCRBYFLOAT,
					(cmd)->cmd.hincrByFloat(key, field, value), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.HINCRBYFLOAT,
					(cmd)->cmd.hincrByFloat(key, field, value), (v)->v)
					.run(args);
		}
	}

	@Override
	public Double hIncrByFloat(final byte[] key, final byte[] field, final double value) {
		final CommandArguments args = CommandArguments.create(key).add(field).add(value);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.HINCRBYFLOAT,
					(cmd)->cmd.hincrByFloat(key, field, value), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.HINCRBYFLOAT,
					(cmd)->cmd.hincrByFloat(key, field, value), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.HINCRBYFLOAT,
					(cmd)->cmd.hincrByFloat(key, field, value), (v)->v)
					.run(args);
		}
	}

	@Override
	public Set<String> hKeys(final String key) {
		final CommandArguments args = CommandArguments.create(key);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.HKEYS, (cmd)->cmd.hkeys(key), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.HKEYS, (cmd)->cmd.hkeys(key), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.HKEYS, (cmd)->cmd.hkeys(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public Set<byte[]> hKeys(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.HKEYS, (cmd)->cmd.hkeys(key), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.HKEYS, (cmd)->cmd.hkeys(key), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.HKEYS, (cmd)->cmd.hkeys(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long hLen(final String key) {
		final CommandArguments args = CommandArguments.create(key);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.HLEN, (cmd)->cmd.hlen(key), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.HLEN, (cmd)->cmd.hlen(key), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.HLEN, (cmd)->cmd.hlen(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long hLen(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.HLEN, (cmd)->cmd.hlen(key), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.HLEN, (cmd)->cmd.hlen(key), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.HLEN, (cmd)->cmd.hlen(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public List<String> hMGet(final String key, final String... fields) {
		final CommandArguments args = CommandArguments.create(key).add(fields);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.HMGET, (cmd)->cmd.hmget(key, fields), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.HMGET, (cmd)->cmd.hmget(key, fields), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.HMGET, (cmd)->cmd.hmget(key, fields), (v)->v)
					.run(args);
		}
	}

	@Override
	public List<byte[]> hMGet(final byte[] key, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(fields);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.HMGET, (cmd)->cmd.hmget(key, fields), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.HMGET, (cmd)->cmd.hmget(key, fields), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.HMGET, (cmd)->cmd.hmget(key, fields), (v)->v)
					.run(args);
		}
	}

	@Override
	public Status hMSet(final String key, final Map<String, String> data) {
		final CommandArguments args = CommandArguments.create(key).add(data);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.HMGET, (cmd)->cmd.hmset(key, data),
					okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.HMGET, (cmd)->cmd.hmset(key, data),
					okStatusConverter)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.HMGET, (cmd)->cmd.hmset(key, data),
					okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status hMSet(final byte[] key, final Map<byte[], byte[]> data) {
		final CommandArguments args = CommandArguments.create(key).add(data);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.HMGET, (cmd)->cmd.hmset(key, data),
					okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.HMGET, (cmd)->cmd.hmset(key, data),
					okStatusConverter)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.HMGET, (cmd)->cmd.hmset(key, data), okStatusConverter)
					.run(args);
		}
	}

	@Override
	public String hRandField(final String key) {
		final CommandArguments args = CommandArguments.create(key);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.HRANDFIELD, (cmd)->cmd.hrandfield(key), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.HRANDFIELD, (cmd)->cmd.hrandfield(key), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.HRANDFIELD, (cmd)->cmd.hrandfield(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public byte[] hRandField(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.HRANDFIELD, (cmd)->cmd.hrandfield(key), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.HRANDFIELD, (cmd)->cmd.hrandfield(key), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.HRANDFIELD, (cmd)->cmd.hrandfield(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public List<String> hRandField(final String key, final long count) {
		final CommandArguments args = CommandArguments.create(key).add(count);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.HRANDFIELD, (cmd)->cmd.hrandfield(key, count),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.HRANDFIELD, (cmd)->cmd.hrandfield(key, count),
					(v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.HRANDFIELD, (cmd)->cmd.hrandfield(key, count), (v)->v)
					.run(args);
		}
	}

	@Override
	public List<byte[]> hRandField(final byte[] key, final long count) {
		final CommandArguments args = CommandArguments.create(key).add(count);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.HRANDFIELD, (cmd)->cmd.hrandfield(key, count),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.HRANDFIELD, (cmd)->cmd.hrandfield(key, count),
					(v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.HRANDFIELD, (cmd)->cmd.hrandfield(key, count), (v)->v)
					.run(args);
		}
	}

	@Override
	public Map<String, String> hRandFieldWithValues(final String key, final long count) {
		final CommandArguments args = CommandArguments.create(key).add(count);
		final ListMapEntryMapConverter<String, String, String, String> converter =
				new ListMapEntryMapConverter<>((k)->k, (v)->v);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.HRANDFIELD,
					(cmd)->cmd.hrandfieldWithValues(key, count), converter)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.HRANDFIELD,
					(cmd)->cmd.hrandfieldWithValues(key, count), converter)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.HRANDFIELD,
					(cmd)->cmd.hrandfieldWithValues(key, count), converter)
					.run(args);
		}
	}

	@Override
	public Map<byte[], byte[]> hRandFieldWithValues(final byte[] key, final long count) {
		final CommandArguments args = CommandArguments.create(key).add(count);
		final ListMapEntryMapConverter<byte[], byte[], byte[], byte[]> converter =
				new ListMapEntryMapConverter<>((k)->k, (v)->v);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.HRANDFIELD,
					(cmd)->cmd.hrandfieldWithValues(key, count), converter)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.HRANDFIELD,
					(cmd)->cmd.hrandfieldWithValues(key, count), converter)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.HRANDFIELD,
					(cmd)->cmd.hrandfieldWithValues(key, count), converter)
					.run(args);
		}
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final String cursor) {
		final CommandArguments args = CommandArguments.create(key).add(cursor);
		final ScanResultConverter.MapScanResultConverter<String, String> mapScanResultConverter =
				new ScanResultConverter.MapScanResultConverter<>();

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.HSCAN, (cmd)->cmd.hscan(key, cursor),
					mapScanResultConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.HSCAN, (cmd)->cmd.hscan(key, cursor),
					mapScanResultConverter)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.HSCAN, (cmd)->cmd.hscan(key, cursor),
					mapScanResultConverter)
					.run(args);
		}
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor) {
		final CommandArguments args = CommandArguments.create(key).add(cursor);
		final ScanResultConverter.MapScanResultConverter<byte[], byte[]> mapScanResultConverter =
				new ScanResultConverter.MapScanResultConverter<>();

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.HSCAN, (cmd)->cmd.hscan(key, cursor),
					mapScanResultConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.HSCAN, (cmd)->cmd.hscan(key, cursor),
					mapScanResultConverter)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.HSCAN, (cmd)->cmd.hscan(key, cursor),
					mapScanResultConverter)
					.run(args);
		}
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final String cursor, final String pattern) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(pattern);
		final ScanParams scanParams = new JedisScanParams(pattern);

		return hScan(key, cursor, scanParams, args);
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor, final byte[] pattern) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(pattern);
		final ScanParams scanParams = new JedisScanParams(pattern);

		return hScan(key, cursor, scanParams, args);
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final String cursor, final long count) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(count);
		final ScanParams scanParams = new JedisScanParams(count);

		return hScan(key, cursor, scanParams, args);
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor, final long count) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(count);
		final ScanParams scanParams = new JedisScanParams(count);

		return hScan(key, cursor, scanParams, args);
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final String cursor, final String pattern,
												 final long count) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(pattern);
		final ScanParams scanParams = new JedisScanParams(pattern, count);

		return hScan(key, cursor, scanParams, args);
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor, final byte[] pattern,
												 final long count) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(pattern)
				.add(count);
		final ScanParams scanParams = new JedisScanParams(pattern, count);

		return hScan(key, cursor, scanParams, args);
	}

	@Override
	public Long hSet(final String key, final String field, final String value) {
		final CommandArguments args = CommandArguments.create(key).add(field).add(value);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.HSET, (cmd)->cmd.hset(key, field, value), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.HSET, (cmd)->cmd.hset(key, field, value),
					(v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.HSET, (cmd)->cmd.hset(key, field, value), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long hSet(final byte[] key, final byte[] field, final byte[] value) {
		final CommandArguments args = CommandArguments.create(key).add(field).add(value);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.HSET, (cmd)->cmd.hset(key, field, value), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.HSET, (cmd)->cmd.hset(key, field, value),
					(v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.HSET, (cmd)->cmd.hset(key, field, value), (v)->v)
					.run(args);
		}
	}

	@Override
	public Status hSetNx(final String key, final String field, final String value) {
		final CommandArguments args = CommandArguments.create(key).add(field).add(value);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.HSETNX, (cmd)->cmd.hsetnx(key, field, value),
					oneStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.HSETNX, (cmd)->cmd.hsetnx(key, field, value),
					oneStatusConverter)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.HSETNX, (cmd)->cmd.hsetnx(key, field, value),
					oneStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status hSetNx(final byte[] key, final byte[] field, final byte[] value) {
		final CommandArguments args = CommandArguments.create(key).add(field).add(value);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.HSETNX, (cmd)->cmd.hsetnx(key, field, value),
					oneStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.HSETNX, (cmd)->cmd.hsetnx(key, field, value),
					oneStatusConverter)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.HSETNX, (cmd)->cmd.hsetnx(key, field, value),
					oneStatusConverter)
					.run(args);
		}
	}

	@Override
	public Long hStrLen(final String key, final String field) {
		final CommandArguments args = CommandArguments.create(key).add(field);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.HSTRLEN, (cmd)->cmd.hstrlen(key, field), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.HSTRLEN, (cmd)->cmd.hstrlen(key, field),
					(v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.HSTRLEN, (cmd)->cmd.hstrlen(key, field), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long hStrLen(final byte[] key, final byte[] field) {
		final CommandArguments args = CommandArguments.create(key).add(field);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.HSTRLEN, (cmd)->cmd.hstrlen(key, field), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.HSTRLEN, (cmd)->cmd.hstrlen(key, field),
					(v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.HSTRLEN, (cmd)->cmd.hstrlen(key, field), (v)->v)
					.run(args);
		}
	}

	@Override
	public List<String> hVals(final String key) {
		final CommandArguments args = CommandArguments.create(key);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.HVALS, (cmd)->cmd.hvals(key), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.HVALS, (cmd)->cmd.hvals(key), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.HVALS, (cmd)->cmd.hvals(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public List<byte[]> hVals(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.HVALS, (cmd)->cmd.hvals(key), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.HVALS, (cmd)->cmd.hvals(key), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.HVALS, (cmd)->cmd.hvals(key), (v)->v)
					.run(args);
		}
	}

	private ScanResult<Map<String, String>> hScan(final String key, final String cursor, final ScanParams scanParams,
												  final CommandArguments args) {
		final ScanResultConverter.MapScanResultConverter<String, String> mapScanResultConverter =
				new ScanResultConverter.MapScanResultConverter<>();

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.HSCAN, (cmd)->cmd.hscan(key, cursor, scanParams),
					mapScanResultConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.HSCAN,
					(cmd)->cmd.hscan(key, cursor, scanParams), mapScanResultConverter)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.HSCAN, (cmd)->cmd.hscan(key, cursor, scanParams),
					mapScanResultConverter)
					.run(args);
		}
	}

	private ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor, final ScanParams scanParams,
												  final CommandArguments args) {
		final ScanResultConverter.MapScanResultConverter<byte[], byte[]> mapScanResultConverter =
				new ScanResultConverter.MapScanResultConverter<>();

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.HSCAN, (cmd)->cmd.hscan(key, cursor, scanParams),
					mapScanResultConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.HSCAN,
					(cmd)->cmd.hscan(key, cursor, scanParams), mapScanResultConverter)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.HSCAN, (cmd)->cmd.hscan(key, cursor, scanParams),
					mapScanResultConverter)
					.run(args);
		}
	}

}
