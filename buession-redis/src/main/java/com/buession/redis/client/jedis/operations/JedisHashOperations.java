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
 * | Copyright @ 2013-2023 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.client.jedis.operations;

import com.buession.lang.Status;
import com.buession.redis.client.jedis.JedisStandaloneClient;
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.internal.convert.Converters;
import com.buession.redis.core.internal.convert.response.OkStatusConverter;
import com.buession.redis.core.internal.convert.jedis.response.ScanResultConverter;
import com.buession.redis.core.internal.jedis.JedisScanParams;

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

	public JedisHashOperations(final JedisStandaloneClient client){
		super(client);
	}

	@Override
	public Long hDel(final String key, final String... fields){
		final CommandArguments args = CommandArguments.create("key", key).put("fields", (Object[]) fields);
		return new JedisCommand<Long>(client, ProtocolCommand.HDEL)
				.general((cmd)->cmd.hdel(key, fields))
				.pipeline((cmd)->cmd.hdel(key, fields))
				.transaction((cmd)->cmd.hdel(key, fields))
				.run(args);
	}

	@Override
	public Long hDel(final byte[] key, final byte[]... fields){
		final CommandArguments args = CommandArguments.create("key", key).put("fields", (Object[]) fields);
		return new JedisCommand<Long>(client, ProtocolCommand.HDEL)
				.general((cmd)->cmd.hdel(key, fields))
				.pipeline((cmd)->cmd.hdel(key, fields))
				.transaction((cmd)->cmd.hdel(key, fields))
				.run(args);
	}

	@Override
	public Boolean hExists(final String key, final String field){
		final CommandArguments args = CommandArguments.create("key", key).put("field", field);
		return new JedisCommand<Boolean>(client, ProtocolCommand.HEXISTS)
				.general((cmd)->cmd.hexists(key, field))
				.pipeline((cmd)->cmd.hexists(key, field))
				.transaction((cmd)->cmd.hexists(key, field))
				.run(args);
	}

	@Override
	public Boolean hExists(final byte[] key, final byte[] field){
		final CommandArguments args = CommandArguments.create("key", key).put("field", field);
		return new JedisCommand<Boolean>(client, ProtocolCommand.HEXISTS)
				.general((cmd)->cmd.hexists(key, field))
				.pipeline((cmd)->cmd.hexists(key, field))
				.transaction((cmd)->cmd.hexists(key, field))
				.run(args);
	}

	@Override
	public String hGet(final String key, final String field){
		final CommandArguments args = CommandArguments.create("key", key).put("field", field);
		return new JedisCommand<String>(client, ProtocolCommand.HGET)
				.general((cmd)->cmd.hget(key, field))
				.pipeline((cmd)->cmd.hget(key, field))
				.transaction((cmd)->cmd.hget(key, field))
				.run(args);
	}

	@Override
	public byte[] hGet(final byte[] key, final byte[] field){
		final CommandArguments args = CommandArguments.create("key", key).put("field", field);
		return new JedisCommand<byte[]>(client, ProtocolCommand.HGET)
				.general((cmd)->cmd.hget(key, field))
				.pipeline((cmd)->cmd.hget(key, field))
				.transaction((cmd)->cmd.hget(key, field))
				.run(args);
	}

	@Override
	public Map<String, String> hGetAll(final String key){
		final CommandArguments args = CommandArguments.create("key", key);
		return new JedisCommand<Map<String, String>>(client, ProtocolCommand.HGETALL)
				.general((cmd)->cmd.hgetAll(key))
				.pipeline((cmd)->cmd.hgetAll(key))
				.transaction((cmd)->cmd.hgetAll(key))
				.run(args);
	}

	@Override
	public Map<byte[], byte[]> hGetAll(final byte[] key){
		final CommandArguments args = CommandArguments.create("key", key);
		return new JedisCommand<Map<byte[], byte[]>>(client, ProtocolCommand.HGETALL)
				.general((cmd)->cmd.hgetAll(key))
				.pipeline((cmd)->cmd.hgetAll(key))
				.transaction((cmd)->cmd.hgetAll(key))
				.run(args);
	}

	@Override
	public Long hIncrBy(final String key, final String field, final long value){
		final CommandArguments args = CommandArguments.create("key", key).put("field", field).put("value", value);
		return new JedisCommand<Long>(client, ProtocolCommand.HINCRBY)
				.general((cmd)->cmd.hincrBy(key, field, value))
				.pipeline((cmd)->cmd.hincrBy(key, field, value))
				.transaction((cmd)->cmd.hincrBy(key, field, value))
				.run(args);
	}

	@Override
	public Long hIncrBy(final byte[] key, final byte[] field, final long value){
		final CommandArguments args = CommandArguments.create("key", key).put("field", field).put("value", value);
		return new JedisCommand<Long>(client, ProtocolCommand.HINCRBY)
				.general((cmd)->cmd.hincrBy(key, field, value))
				.pipeline((cmd)->cmd.hincrBy(key, field, value))
				.transaction((cmd)->cmd.hincrBy(key, field, value))
				.run(args);
	}

	@Override
	public Double hIncrByFloat(final String key, final String field, final double value){
		final CommandArguments args = CommandArguments.create("key", key).put("field", field).put("value", value);
		return new JedisCommand<Double>(client, ProtocolCommand.HINCRBYFLOAT)
				.general((cmd)->cmd.hincrByFloat(key, field, value))
				.pipeline((cmd)->cmd.hincrByFloat(key, field, value))
				.transaction((cmd)->cmd.hincrByFloat(key, field, value))
				.run(args);
	}

	@Override
	public Double hIncrByFloat(final byte[] key, final byte[] field, final double value){
		final CommandArguments args = CommandArguments.create("key", key).put("field", field).put("value", value);
		return new JedisCommand<Double>(client, ProtocolCommand.HINCRBYFLOAT)
				.general((cmd)->cmd.hincrByFloat(key, field, value))
				.pipeline((cmd)->cmd.hincrByFloat(key, field, value))
				.transaction((cmd)->cmd.hincrByFloat(key, field, value))
				.run(args);
	}

	@Override
	public Set<String> hKeys(final String key){
		final CommandArguments args = CommandArguments.create("key", key);
		return new JedisCommand<Set<String>>(client, ProtocolCommand.HKEYS)
				.general((cmd)->cmd.hkeys(key))
				.pipeline((cmd)->cmd.hkeys(key))
				.transaction((cmd)->cmd.hkeys(key))
				.run(args);
	}

	@Override
	public Set<byte[]> hKeys(final byte[] key){
		final CommandArguments args = CommandArguments.create("key", key);
		return new JedisCommand<Set<byte[]>>(client, ProtocolCommand.HKEYS)
				.general((cmd)->cmd.hkeys(key))
				.pipeline((cmd)->cmd.hkeys(key))
				.transaction((cmd)->cmd.hkeys(key))
				.run(args);
	}

	@Override
	public Long hLen(final String key){
		final CommandArguments args = CommandArguments.create("key", key);
		return new JedisCommand<Long>(client, ProtocolCommand.HLEN)
				.general((cmd)->cmd.hlen(key))
				.pipeline((cmd)->cmd.hlen(key))
				.transaction((cmd)->cmd.hlen(key))
				.run(args);
	}

	@Override
	public Long hLen(final byte[] key){
		final CommandArguments args = CommandArguments.create("key", key);
		return new JedisCommand<Long>(client, ProtocolCommand.HLEN)
				.general((cmd)->cmd.hlen(key))
				.pipeline((cmd)->cmd.hlen(key))
				.transaction((cmd)->cmd.hlen(key))
				.run(args);
	}

	@Override
	public List<String> hMGet(final String key, final String... fields){
		final CommandArguments args = CommandArguments.create("key", key).put("fields", (Object[]) fields);
		return new JedisCommand<List<String>>(client, ProtocolCommand.HMGET)
				.general((cmd)->cmd.hmget(key, fields))
				.pipeline((cmd)->cmd.hmget(key, fields))
				.transaction((cmd)->cmd.hmget(key, fields))
				.run(args);
	}

	@Override
	public List<byte[]> hMGet(final byte[] key, final byte[]... fields){
		final CommandArguments args = CommandArguments.create("key", key).put("fields", (Object[]) fields);
		return new JedisCommand<List<byte[]>>(client, ProtocolCommand.HMGET)
				.general((cmd)->cmd.hmget(key, fields))
				.pipeline((cmd)->cmd.hmget(key, fields))
				.transaction((cmd)->cmd.hmget(key, fields))
				.run(args);
	}

	@Override
	public Status hMSet(final String key, final Map<String, String> data){
		final CommandArguments args = CommandArguments.create("key", key).put("data", data);
		return new JedisCommand<Status>(client, ProtocolCommand.HMGET)
				.general((cmd)->cmd.hmset(key, data), OkStatusConverter.INSTANCE)
				.pipeline((cmd)->cmd.hmset(key, data), OkStatusConverter.INSTANCE)
				.transaction((cmd)->cmd.hmset(key, data), OkStatusConverter.INSTANCE)
				.run(args);
	}

	@Override
	public Status hMSet(final byte[] key, final Map<byte[], byte[]> data){
		final CommandArguments args = CommandArguments.create("key", key).put("data", data);
		return new JedisCommand<Status>(client, ProtocolCommand.HMGET)
				.general((cmd)->cmd.hmset(key, data), OkStatusConverter.INSTANCE)
				.pipeline((cmd)->cmd.hmset(key, data), OkStatusConverter.INSTANCE)
				.transaction((cmd)->cmd.hmset(key, data), OkStatusConverter.INSTANCE)
				.run(args);
	}

	@Override
	public String hRandField(final String key){
		final CommandArguments args = CommandArguments.create("key", key);
		return new JedisCommand<String>(client, ProtocolCommand.HRANDFIELD)
				.general((cmd)->cmd.hrandfield(key))
				.pipeline((cmd)->cmd.hrandfield(key))
				.transaction((cmd)->cmd.hrandfield(key))
				.run(args);
	}

	@Override
	public byte[] hRandField(final byte[] key){
		final CommandArguments args = CommandArguments.create("key", key);
		return new JedisCommand<byte[]>(client, ProtocolCommand.HRANDFIELD)
				.general((cmd)->cmd.hrandfield(key))
				.pipeline((cmd)->cmd.hrandfield(key))
				.transaction((cmd)->cmd.hrandfield(key))
				.run(args);
	}

	@Override
	public List<String> hRandField(final String key, final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("count", count);
		return new JedisCommand<List<String>>(client, ProtocolCommand.HRANDFIELD)
				.general((cmd)->cmd.hrandfield(key, count))
				.pipeline((cmd)->cmd.hrandfield(key, count))
				.transaction((cmd)->cmd.hrandfield(key, count))
				.run(args);
	}

	@Override
	public List<byte[]> hRandField(final byte[] key, final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("count", count);
		return new JedisCommand<List<byte[]>>(client, ProtocolCommand.HRANDFIELD)
				.general((cmd)->cmd.hrandfield(key, count))
				.pipeline((cmd)->cmd.hrandfield(key, count))
				.transaction((cmd)->cmd.hrandfield(key, count))
				.run(args);
	}

	@Override
	public Map<String, String> hRandFieldWithValues(final String key, final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("count", count);
		return new JedisCommand<Map<String, String>>(client, ProtocolCommand.HRANDFIELD)
				.general((cmd)->cmd.hrandfieldWithValues(key, count))
				.pipeline((cmd)->cmd.hrandfieldWithValues(key, count))
				.transaction((cmd)->cmd.hrandfieldWithValues(key, count))
				.run(args);
	}

	@Override
	public Map<byte[], byte[]> hRandFieldWithValues(final byte[] key, final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("count", count);
		return new JedisCommand<Map<byte[], byte[]>>(client, ProtocolCommand.HRANDFIELD)
				.general((cmd)->cmd.hrandfieldWithValues(key, count))
				.pipeline((cmd)->cmd.hrandfieldWithValues(key, count))
				.transaction((cmd)->cmd.hrandfieldWithValues(key, count))
				.run(args);
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final String cursor){
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor);
		return new JedisCommand<ScanResult<Map<String, String>>>(client, ProtocolCommand.HSCAN)
				.general((cmd)->cmd.hscan(key, cursor), ScanResultConverter.MapScanResultConverter.STRING_MAP_CONVERTER)
				.pipeline((cmd)->cmd.hscan(key, cursor),
						ScanResultConverter.MapScanResultConverter.STRING_MAP_CONVERTER)
				.transaction((cmd)->cmd.hscan(key, cursor),
						ScanResultConverter.MapScanResultConverter.STRING_MAP_CONVERTER)
				.run(args);
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor){
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor);
		return new JedisCommand<ScanResult<Map<byte[], byte[]>>>(client, ProtocolCommand.HSCAN)
				.general((cmd)->cmd.hscan(key, cursor), ScanResultConverter.MapScanResultConverter.BINARY_MAP_CONVERTER)
				.pipeline((cmd)->cmd.hscan(key, cursor),
						ScanResultConverter.MapScanResultConverter.BINARY_MAP_CONVERTER)
				.transaction((cmd)->cmd.hscan(key, cursor),
						ScanResultConverter.MapScanResultConverter.BINARY_MAP_CONVERTER)
				.run(args);
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final String cursor, final String pattern){
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor).put("pattern", pattern);
		final JedisScanParams params = new JedisScanParams(pattern);
		return new JedisCommand<ScanResult<Map<String, String>>>(client, ProtocolCommand.HSCAN)
				.general((cmd)->cmd.hscan(key, cursor, params),
						ScanResultConverter.MapScanResultConverter.STRING_MAP_CONVERTER)
				.pipeline((cmd)->cmd.hscan(key, cursor, params),
						ScanResultConverter.MapScanResultConverter.STRING_MAP_CONVERTER)
				.transaction((cmd)->cmd.hscan(key, cursor, params),
						ScanResultConverter.MapScanResultConverter.STRING_MAP_CONVERTER)
				.run(args);
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor, final byte[] pattern){
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor).put("pattern", pattern);
		final JedisScanParams params = new JedisScanParams(pattern);
		return new JedisCommand<ScanResult<Map<byte[], byte[]>>>(client, ProtocolCommand.HSCAN)
				.general((cmd)->cmd.hscan(key, cursor, params),
						ScanResultConverter.MapScanResultConverter.BINARY_MAP_CONVERTER)
				.pipeline((cmd)->cmd.hscan(key, cursor, params),
						ScanResultConverter.MapScanResultConverter.BINARY_MAP_CONVERTER)
				.transaction((cmd)->cmd.hscan(key, cursor, params),
						ScanResultConverter.MapScanResultConverter.BINARY_MAP_CONVERTER)
				.run(args);
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final String cursor, final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor).put("count", count);
		final JedisScanParams params = new JedisScanParams(count);
		return new JedisCommand<ScanResult<Map<String, String>>>(client, ProtocolCommand.HSCAN)
				.general((cmd)->cmd.hscan(key, cursor, params),
						ScanResultConverter.MapScanResultConverter.STRING_MAP_CONVERTER)
				.pipeline((cmd)->cmd.hscan(key, cursor, params),
						ScanResultConverter.MapScanResultConverter.STRING_MAP_CONVERTER)
				.transaction((cmd)->cmd.hscan(key, cursor, params),
						ScanResultConverter.MapScanResultConverter.STRING_MAP_CONVERTER)
				.run(args);
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor, final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor).put("count", count);
		final JedisScanParams params = new JedisScanParams(count);
		return new JedisCommand<ScanResult<Map<byte[], byte[]>>>(client, ProtocolCommand.HSCAN)
				.general((cmd)->cmd.hscan(key, cursor, params),
						ScanResultConverter.MapScanResultConverter.BINARY_MAP_CONVERTER)
				.pipeline((cmd)->cmd.hscan(key, cursor, params),
						ScanResultConverter.MapScanResultConverter.BINARY_MAP_CONVERTER)
				.transaction((cmd)->cmd.hscan(key, cursor, params),
						ScanResultConverter.MapScanResultConverter.BINARY_MAP_CONVERTER)
				.run(args);
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final String cursor, final String pattern,
												 final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor).put("pattern", pattern);
		final JedisScanParams params = new JedisScanParams(pattern, count);
		return new JedisCommand<ScanResult<Map<String, String>>>(client, ProtocolCommand.HSCAN)
				.general((cmd)->cmd.hscan(key, cursor, params),
						ScanResultConverter.MapScanResultConverter.STRING_MAP_CONVERTER)
				.pipeline((cmd)->cmd.hscan(key, cursor, params),
						ScanResultConverter.MapScanResultConverter.STRING_MAP_CONVERTER)
				.transaction((cmd)->cmd.hscan(key, cursor, params),
						ScanResultConverter.MapScanResultConverter.STRING_MAP_CONVERTER)
				.run(args);
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor, final byte[] pattern,
												 final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor).put("pattern", pattern)
				.put("count", count);
		final JedisScanParams params = new JedisScanParams(pattern, count);
		return new JedisCommand<ScanResult<Map<byte[], byte[]>>>(client, ProtocolCommand.HSCAN)
				.general((cmd)->cmd.hscan(key, cursor, params),
						ScanResultConverter.MapScanResultConverter.BINARY_MAP_CONVERTER)
				.pipeline((cmd)->cmd.hscan(key, cursor, params),
						ScanResultConverter.MapScanResultConverter.BINARY_MAP_CONVERTER)
				.transaction((cmd)->cmd.hscan(key, cursor, params),
						ScanResultConverter.MapScanResultConverter.BINARY_MAP_CONVERTER)
				.run(args);
	}

	@Override
	public Long hSet(final String key, final String field, final String value){
		final CommandArguments args = CommandArguments.create("key", key).put("field", field).put("value", value);
		return new JedisCommand<Long>(client, ProtocolCommand.HSET)
				.general((cmd)->cmd.hset(key, field, value))
				.pipeline((cmd)->cmd.hset(key, field, value))
				.transaction((cmd)->cmd.hset(key, field, value))
				.run(args);
	}

	@Override
	public Long hSet(final byte[] key, final byte[] field, final byte[] value){
		final CommandArguments args = CommandArguments.create("key", key).put("field", field).put("value", value);
		return new JedisCommand<Long>(client, ProtocolCommand.HSET)
				.general((cmd)->cmd.hset(key, field, value))
				.pipeline((cmd)->cmd.hset(key, field, value))
				.transaction((cmd)->cmd.hset(key, field, value))
				.run(args);
	}

	@Override
	public Status hSetNx(final String key, final String field, final String value){
		final CommandArguments args = CommandArguments.create("key", key).put("field", field).put("value", value);
		return new JedisCommand<Status>(client, ProtocolCommand.HSETNX)
				.general((cmd)->cmd.hsetnx(key, field, value), Converters.ONE_STATUS_CONVERTER)
				.pipeline((cmd)->cmd.hsetnx(key, field, value), Converters.ONE_STATUS_CONVERTER)
				.transaction((cmd)->cmd.hsetnx(key, field, value), Converters.ONE_STATUS_CONVERTER)
				.run(args);
	}

	@Override
	public Status hSetNx(final byte[] key, final byte[] field, final byte[] value){
		final CommandArguments args = CommandArguments.create("key", key).put("field", field).put("value", value);
		return new JedisCommand<Status>(client, ProtocolCommand.HSETNX)
				.general((cmd)->cmd.hsetnx(key, field, value), Converters.ONE_STATUS_CONVERTER)
				.pipeline((cmd)->cmd.hsetnx(key, field, value), Converters.ONE_STATUS_CONVERTER)
				.transaction((cmd)->cmd.hsetnx(key, field, value), Converters.ONE_STATUS_CONVERTER)
				.run(args);
	}

	@Override
	public Long hStrLen(final String key, final String field){
		final CommandArguments args = CommandArguments.create("key", key).put("field", field);
		return new JedisCommand<Long>(client, ProtocolCommand.HSTRLEN)
				.general((cmd)->cmd.hstrlen(key, field))
				.pipeline((cmd)->cmd.hstrlen(key, field))
				.transaction((cmd)->cmd.hstrlen(key, field))
				.run(args);
	}

	@Override
	public Long hStrLen(final byte[] key, final byte[] field){
		final CommandArguments args = CommandArguments.create("key", key).put("field", field);
		return new JedisCommand<Long>(client, ProtocolCommand.HSTRLEN)
				.general((cmd)->cmd.hstrlen(key, field))
				.pipeline((cmd)->cmd.hstrlen(key, field))
				.transaction((cmd)->cmd.hstrlen(key, field))
				.run(args);
	}

	@Override
	public List<String> hVals(final String key){
		final CommandArguments args = CommandArguments.create("key", key);
		return new JedisCommand<List<String>>(client, ProtocolCommand.HVALS)
				.general((cmd)->cmd.hvals(key))
				.pipeline((cmd)->cmd.hvals(key))
				.transaction((cmd)->cmd.hvals(key))
				.run(args);
	}

	@Override
	public List<byte[]> hVals(final byte[] key){
		final CommandArguments args = CommandArguments.create("key", key);
		return new JedisCommand<List<byte[]>>(client, ProtocolCommand.HVALS)
				.general((cmd)->cmd.hvals(key))
				.pipeline((cmd)->cmd.hvals(key))
				.transaction((cmd)->cmd.hvals(key))
				.run(args);
	}

}
