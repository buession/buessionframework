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
 * | Copyright @ 2013-2022 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.client.jedis.operations;

import com.buession.lang.Status;
import com.buession.redis.client.jedis.JedisStandaloneClient;
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.CommandNotSupported;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.internal.convert.Converters;
import com.buession.redis.core.internal.jedis.JedisScanParams;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Jedis 单机模式模式哈希表命令操作
 *
 * @author Yong.Teng
 */
public final class JedisHashOperations extends AbstractHashOperations<Jedis> {

	public JedisHashOperations(final JedisStandaloneClient client){
		super(client);
	}

	@Override
	public Long hDel(final String key, final String... fields){
		final CommandArguments args = CommandArguments.create("key", key).put("fields", fields);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().hdel(key, fields)), ProtocolCommand.HDEL, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().hdel(key, fields)), ProtocolCommand.HDEL,
					args);
		}else{
			return execute((cmd)->cmd.hdel(key, fields), ProtocolCommand.HDEL, args);
		}
	}

	@Override
	public Long hDel(final byte[] key, final byte[]... fields){
		final CommandArguments args = CommandArguments.create("key", key).put("fields", fields);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().hdel(key, fields)), ProtocolCommand.HDEL, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().hdel(key, fields)), ProtocolCommand.HDEL,
					args);
		}else{
			return execute((cmd)->cmd.hdel(key, fields), ProtocolCommand.HDEL, args);
		}
	}

	@Override
	public boolean hExists(final String key, final String field){
		final CommandArguments args = CommandArguments.create("key", key).put("field", field);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().hexists(key, field)), ProtocolCommand.HEXISTS,
					args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().hexists(key, field)),
					ProtocolCommand.HEXISTS, args);
		}else{
			return execute((cmd)->cmd.hexists(key, field), ProtocolCommand.HEXISTS, args);
		}
	}

	@Override
	public boolean hExists(final byte[] key, final byte[] field){
		final CommandArguments args = CommandArguments.create("key", key).put("field", field);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().hexists(key, field)), ProtocolCommand.HEXISTS,
					args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().hexists(key, field)),
					ProtocolCommand.HEXISTS, args);
		}else{
			return execute((cmd)->cmd.hexists(key, field), ProtocolCommand.HEXISTS, args);
		}
	}

	@Override
	public String hGet(final String key, final String field){
		final CommandArguments args = CommandArguments.create("key", key).put("field", field);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().hget(key, field)), ProtocolCommand.HGET,
					args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().hget(key, field)), ProtocolCommand.HGET,
					args);
		}else{
			return execute((cmd)->cmd.hget(key, field), ProtocolCommand.HGET, args);
		}
	}

	@Override
	public byte[] hGet(final byte[] key, final byte[] field){
		final CommandArguments args = CommandArguments.create("key", key).put("field", field);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().hget(key, field)), ProtocolCommand.HGET,
					args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().hget(key, field)), ProtocolCommand.HGET,
					args);
		}else{
			return execute((cmd)->cmd.hget(key, field), ProtocolCommand.HGET, args);
		}
	}

	@Override
	public Map<String, String> hGetAll(final String key){
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().hgetAll(key)), ProtocolCommand.HGETALL,
					args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().hgetAll(key)), ProtocolCommand.HGETALL,
					args);
		}else{
			return execute((cmd)->cmd.hgetAll(key), ProtocolCommand.HGETALL, args);
		}
	}

	@Override
	public Map<byte[], byte[]> hGetAll(final byte[] key){
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().hgetAll(key)), ProtocolCommand.HGETALL,
					args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().hgetAll(key)), ProtocolCommand.HGETALL,
					args);
		}else{
			return execute((cmd)->cmd.hgetAll(key), ProtocolCommand.HGETALL, args);
		}
	}

	@Override
	public Long hIncrBy(final String key, final String field, final long value){
		final CommandArguments args = CommandArguments.create("key", key).put("field", field).put("value", value);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().hincrBy(key, field, value)),
					ProtocolCommand.HINCRBY, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().hincrBy(key, field, value)),
					ProtocolCommand.HINCRBY, args);
		}else{
			return execute((cmd)->cmd.hincrBy(key, field, value), ProtocolCommand.HINCRBY, args);
		}
	}

	@Override
	public Long hIncrBy(final byte[] key, final byte[] field, final long value){
		final CommandArguments args = CommandArguments.create("key", key).put("field", field).put("value", value);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().hincrBy(key, field, value)),
					ProtocolCommand.HINCRBY, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().hincrBy(key, field, value)),
					ProtocolCommand.HINCRBY, args);
		}else{
			return execute((cmd)->cmd.hincrBy(key, field, value), ProtocolCommand.HINCRBY, args);
		}
	}

	@Override
	public Double hIncrByFloat(final String key, final String field, final double value){
		final CommandArguments args = CommandArguments.create("key", key).put("field", field).put("value", value);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().hincrByFloat(key, field, value)),
					ProtocolCommand.HINCRBYFLOAT, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().hincrByFloat(key, field, value)),
					ProtocolCommand.HINCRBYFLOAT, args);
		}else{
			return execute((cmd)->cmd.hincrByFloat(key, field, value), ProtocolCommand.HINCRBYFLOAT, args);
		}
	}

	@Override
	public Double hIncrByFloat(final byte[] key, final byte[] field, final double value){
		final CommandArguments args = CommandArguments.create("key", key).put("field", field).put("value", value);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().hincrByFloat(key, field, value)),
					ProtocolCommand.HINCRBYFLOAT, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().hincrByFloat(key, field, value)),
					ProtocolCommand.HINCRBYFLOAT, args);
		}else{
			return execute((cmd)->cmd.hincrByFloat(key, field, value), ProtocolCommand.HINCRBYFLOAT, args);
		}
	}

	@Override
	public Set<String> hKeys(final String key){
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().hkeys(key)), ProtocolCommand.HKEYS, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().hkeys(key)), ProtocolCommand.HKEYS, args);
		}else{
			return execute((cmd)->cmd.hkeys(key), ProtocolCommand.HKEYS, args);
		}
	}

	@Override
	public Set<byte[]> hKeys(final byte[] key){
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().hkeys(key)), ProtocolCommand.HKEYS, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().hkeys(key)), ProtocolCommand.HKEYS, args);
		}else{
			return execute((cmd)->cmd.hkeys(key), ProtocolCommand.HKEYS, args);
		}
	}

	@Override
	public Long hLen(final String key){
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().hlen(key)), ProtocolCommand.HLEN, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().hlen(key)), ProtocolCommand.HLEN, args);
		}else{
			return execute((cmd)->cmd.hlen(key), ProtocolCommand.HLEN, args);
		}
	}

	@Override
	public Long hLen(final byte[] key){
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().hlen(key)), ProtocolCommand.HLEN, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().hlen(key)), ProtocolCommand.HLEN, args);
		}else{
			return execute((cmd)->cmd.hlen(key), ProtocolCommand.HLEN, args);
		}
	}

	@Override
	public List<String> hMGet(final String key, final String... fields){
		final CommandArguments args = CommandArguments.create("key", key).put("fields", fields);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().hmget(key, fields)), ProtocolCommand.HMGET,
					args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().hmget(key, fields)), ProtocolCommand.HMGET,
					args);
		}else{
			return execute((cmd)->cmd.hmget(key, fields), ProtocolCommand.HMGET, args);
		}
	}

	@Override
	public List<byte[]> hMGet(final byte[] key, final byte[]... fields){
		final CommandArguments args = CommandArguments.create("key", key).put("fields", fields);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().hmget(key, fields)), ProtocolCommand.HMGET,
					args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().hmget(key, fields)), ProtocolCommand.HMGET,
					args);
		}else{
			return execute((cmd)->cmd.hmget(key, fields), ProtocolCommand.HMGET, args);
		}
	}

	@Override
	public Status hMSet(final String key, final Map<String, String> data){
		final CommandArguments args = CommandArguments.create("key", key).put("data", data);

		if(isPipeline()){
			return pipelineExecute(
					(cmd)->newJedisResult(getPipeline().hmset(key, data), Converters.OK_STATUS_CONVERTER),
					ProtocolCommand.HMSET, args);
		}else if(isTransaction()){
			return transactionExecute(
					(cmd)->newJedisResult(getTransaction().hmset(key, data), Converters.OK_STATUS_CONVERTER),
					ProtocolCommand.HMSET, args);
		}else{
			return execute((cmd)->cmd.hmset(key, data), Converters.OK_STATUS_CONVERTER, ProtocolCommand.HMSET, args);
		}
	}

	@Override
	public Status hMSet(final byte[] key, final Map<byte[], byte[]> data){
		final CommandArguments args = CommandArguments.create("key", key).put("data", data);

		if(isPipeline()){
			return pipelineExecute(
					(cmd)->newJedisResult(getPipeline().hmset(key, data), Converters.OK_STATUS_CONVERTER),
					ProtocolCommand.HMSET, args);
		}else if(isTransaction()){
			return transactionExecute(
					(cmd)->newJedisResult(getTransaction().hmset(key, data), Converters.OK_STATUS_CONVERTER),
					ProtocolCommand.HMSET, args);
		}else{
			return execute((cmd)->cmd.hmset(key, data), Converters.OK_STATUS_CONVERTER, ProtocolCommand.HMSET, args);
		}
	}

	@Override
	public String hRandField(final String key){
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().hrandfield(key)), ProtocolCommand.HRANDFIELD,
					args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().hrandfield(key)),
					ProtocolCommand.HRANDFIELD, args);
		}else{
			return execute((cmd)->cmd.hrandfield(key), ProtocolCommand.HRANDFIELD, args);
		}
	}

	@Override
	public byte[] hRandField(final byte[] key){
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().hrandfield(key)), ProtocolCommand.HRANDFIELD,
					args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().hrandfield(key)),
					ProtocolCommand.HRANDFIELD, args);
		}else{
			return execute((cmd)->cmd.hrandfield(key), ProtocolCommand.HRANDFIELD, args);
		}
	}

	@Override
	public List<String> hRandField(final String key, final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("count", count);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().hrandfield(key, count)),
					ProtocolCommand.HRANDFIELD, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().hrandfield(key, count)),
					ProtocolCommand.HRANDFIELD, args);
		}else{
			return execute((cmd)->cmd.hrandfield(key, count), ProtocolCommand.HRANDFIELD, args);
		}
	}

	@Override
	public List<byte[]> hRandField(final byte[] key, final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("count", count);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().hrandfield(key, count)),
					ProtocolCommand.HRANDFIELD, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().hrandfield(key, count)),
					ProtocolCommand.HRANDFIELD, args);
		}else{
			return execute((cmd)->cmd.hrandfield(key, count), ProtocolCommand.HRANDFIELD, args);
		}
	}

	@Override
	public Map<String, String> hRandFieldWithValues(final String key, final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("count", count);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().hrandfieldWithValues(key, count)),
					ProtocolCommand.HRANDFIELD, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().hrandfieldWithValues(key, count)),
					ProtocolCommand.HRANDFIELD, args);
		}else{
			return execute((cmd)->cmd.hrandfieldWithValues(key, count), ProtocolCommand.HRANDFIELD, args);
		}
	}

	@Override
	public Map<byte[], byte[]> hRandFieldWithValues(final byte[] key, final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("count", count);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().hrandfieldWithValues(key, count)),
					ProtocolCommand.HRANDFIELD, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().hrandfieldWithValues(key, count)),
					ProtocolCommand.HRANDFIELD, args);
		}else{
			return execute((cmd)->cmd.hrandfieldWithValues(key, count), ProtocolCommand.HRANDFIELD, args);
		}
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final String cursor){
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor);

		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.HRANDFIELD, args);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.HRANDFIELD, args);
		}else{
			return execute((cmd)->cmd.hscan(key, cursor), STRING_MAP_SCAN_RESULT_EXPOSE_CONVERTER,
					ProtocolCommand.HRANDFIELD, args);
		}
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor){
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor);

		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.HRANDFIELD, args);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.HRANDFIELD, args);
		}else{
			return execute((cmd)->cmd.hscan(key, cursor), BINARY_MAP_SCAN_RESULT_EXPOSE_CONVERTER,
					ProtocolCommand.HRANDFIELD, args);
		}
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final String cursor, final String pattern){
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor).put("pattern", pattern);

		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.HSCAN, args);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.HSCAN, args);
		}else{
			return execute((cmd)->cmd.hscan(key, cursor, new JedisScanParams(pattern)),
					STRING_MAP_SCAN_RESULT_EXPOSE_CONVERTER, ProtocolCommand.HSCAN, args);
		}
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor, final byte[] pattern){
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor).put("pattern", pattern);

		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.HSCAN, args);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.HSCAN, args);
		}else{
			return execute((cmd)->cmd.hscan(key, cursor, new JedisScanParams(pattern)),
					BINARY_MAP_SCAN_RESULT_EXPOSE_CONVERTER, ProtocolCommand.HSCAN, args);
		}
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final String cursor, final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor).put("count", count);

		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.HSCAN, args);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.HSCAN, args);
		}else{
			return execute((cmd)->cmd.hscan(key, cursor, new JedisScanParams(count)),
					STRING_MAP_SCAN_RESULT_EXPOSE_CONVERTER, ProtocolCommand.HSCAN, args);
		}
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor, final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor).put("count", count);

		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.HSCAN, args);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.HSCAN, args);
		}else{
			return execute((cmd)->cmd.hscan(key, cursor, new JedisScanParams(count)),
					BINARY_MAP_SCAN_RESULT_EXPOSE_CONVERTER, ProtocolCommand.HSCAN, args);
		}
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final String cursor, final String pattern,
												 final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor).put("pattern", pattern)
				.put("count", count);

		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.HSCAN, args);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.HSCAN, args);
		}else{
			return execute((cmd)->cmd.hscan(key, cursor, new JedisScanParams(pattern, count)),
					STRING_MAP_SCAN_RESULT_EXPOSE_CONVERTER, ProtocolCommand.HSCAN, args);
		}
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor, final byte[] pattern,
												 final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor).put("pattern", pattern)
				.put("count", count);

		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.HSCAN, args);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.HSCAN, args);
		}else{
			return execute((cmd)->cmd.hscan(key, cursor, new JedisScanParams(pattern, count)),
					BINARY_MAP_SCAN_RESULT_EXPOSE_CONVERTER, ProtocolCommand.HSCAN, args);
		}
	}

	@Override
	public Status hSet(final String key, final String field, final String value){
		final CommandArguments args = CommandArguments.create("key", key).put("field", field).put("value", value);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().hset(key, field, value),
					Converters.LONG_ZERO_ONE_STATUS_CONVERTER), ProtocolCommand.HSET, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().hset(key, field, value),
					Converters.LONG_ZERO_ONE_STATUS_CONVERTER), ProtocolCommand.HSET, args);
		}else{
			return execute((cmd)->cmd.hset(key, field, value), Converters.LONG_ZERO_ONE_STATUS_CONVERTER,
					ProtocolCommand.HSET, args);
		}
	}

	@Override
	public Status hSet(final byte[] key, final byte[] field, final byte[] value){
		final CommandArguments args = CommandArguments.create("key", key).put("field", field).put("value", value);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().hset(key, field, value),
					Converters.LONG_ZERO_ONE_STATUS_CONVERTER), ProtocolCommand.HSET, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().hset(key, field, value),
					Converters.LONG_ZERO_ONE_STATUS_CONVERTER), ProtocolCommand.HSET, args);
		}else{
			return execute((cmd)->cmd.hset(key, field, value), Converters.LONG_ZERO_ONE_STATUS_CONVERTER,
					ProtocolCommand.HSET, args);
		}
	}

	@Override
	public Status hSetNx(final String key, final String field, final String value){
		final CommandArguments args = CommandArguments.create("key", key).put("field", field).put("value", value);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().hsetnx(key, field, value),
					Converters.LONG_ZERO_ONE_STATUS_CONVERTER), ProtocolCommand.HSETNX, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().hsetnx(key, field, value),
					Converters.LONG_ZERO_ONE_STATUS_CONVERTER), ProtocolCommand.HSETNX, args);
		}else{
			return execute((cmd)->cmd.hsetnx(key, field, value), Converters.LONG_ZERO_ONE_STATUS_CONVERTER,
					ProtocolCommand.HSETNX, args);
		}
	}

	@Override
	public Status hSetNx(final byte[] key, final byte[] field, final byte[] value){
		final CommandArguments args = CommandArguments.create("key", key).put("field", field).put("value", value);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().hsetnx(key, field, value),
					Converters.LONG_ZERO_ONE_STATUS_CONVERTER), ProtocolCommand.HSETNX, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().hsetnx(key, field, value),
					Converters.LONG_ZERO_ONE_STATUS_CONVERTER), ProtocolCommand.HSETNX, args);
		}else{
			return execute((cmd)->cmd.hsetnx(key, field, value), Converters.LONG_ZERO_ONE_STATUS_CONVERTER,
					ProtocolCommand.HSETNX, args);
		}
	}

	@Override
	public Long hStrLen(final String key, final String field){
		final CommandArguments args = CommandArguments.create("key", key).put("field", field);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().hstrlen(key, field)), ProtocolCommand.HSTRLEN,
					args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().hstrlen(key, field)),
					ProtocolCommand.HSTRLEN, args);
		}else{
			return execute((cmd)->cmd.hstrlen(key, field), ProtocolCommand.HSTRLEN, args);
		}
	}

	@Override
	public Long hStrLen(final byte[] key, final byte[] field){
		final CommandArguments args = CommandArguments.create("key", key).put("field", field);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().hstrlen(key, field)), ProtocolCommand.HSTRLEN,
					args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().hstrlen(key, field)),
					ProtocolCommand.HSTRLEN, args);
		}else{
			return execute((cmd)->cmd.hstrlen(key, field), ProtocolCommand.HSTRLEN, args);
		}
	}

	@Override
	public List<String> hVals(final String key){
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().hvals(key)), ProtocolCommand.HVALS, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().hvals(key)), ProtocolCommand.HVALS, args);
		}else{
			return execute((cmd)->cmd.hvals(key), ProtocolCommand.HVALS, args);
		}
	}

	@Override
	public List<byte[]> hVals(final byte[] key){
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().hvals(key)), ProtocolCommand.HVALS, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().hvals(key)), ProtocolCommand.HVALS, args);
		}else{
			return execute((cmd)->cmd.hvals(key), ProtocolCommand.HVALS, args);
		}
	}

}
