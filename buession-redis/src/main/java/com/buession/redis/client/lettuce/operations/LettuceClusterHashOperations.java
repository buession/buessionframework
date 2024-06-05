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
package com.buession.redis.client.lettuce.operations;

import com.buession.core.converter.Converter;
import com.buession.core.converter.ListConverter;
import com.buession.core.converter.ListSetConverter;
import com.buession.core.converter.MapConverter;
import com.buession.lang.Status;
import com.buession.redis.client.lettuce.LettuceClusterClient;
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.internal.convert.Converters;
import com.buession.redis.core.internal.convert.lettuce.response.MapScanCursorConverter;
import com.buession.redis.core.internal.lettuce.LettuceScanArgs;
import com.buession.redis.core.internal.lettuce.LettuceScanCursor;
import com.buession.redis.utils.SafeEncoder;
import io.lettuce.core.KeyValue;
import io.lettuce.core.MapScanCursor;
import io.lettuce.core.ScanArgs;
import io.lettuce.core.ScanCursor;
import io.lettuce.core.Value;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Lettuce 集群模式模式哈希表命令操作
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public final class LettuceClusterHashOperations extends AbstractHashOperations<LettuceClusterClient> {

	public LettuceClusterHashOperations(final LettuceClusterClient client) {
		super(client);
	}

	@Override
	public Long hDel(final byte[] key, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create("key", key).put("fields", (Object[]) fields);

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, ProtocolCommand.HDEL, (cmd)->cmd.hdel(key, fields),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, ProtocolCommand.HDEL, (cmd)->cmd.hdel(key, fields),
					(v)->v)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, ProtocolCommand.HDEL, (cmd)->cmd.hdel(key, fields), (v)->v)
					.run(args);
		}
	}

	@Override
	public Boolean hExists(final byte[] key, final byte[] field) {
		final CommandArguments args = CommandArguments.create("key", key).put("field", field);

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, ProtocolCommand.HEXISTS, (cmd)->cmd.hexists(key, field),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, ProtocolCommand.HEXISTS,
					(cmd)->cmd.hexists(key, field), (v)->v)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, ProtocolCommand.HEXISTS, (cmd)->cmd.hexists(key, field), (v)->v)
					.run(args);
		}
	}

	@Override
	public String hGet(final String key, final String field) {
		final CommandArguments args = CommandArguments.create("key", key).put("field", field);
		final byte[] bKey = SafeEncoder.encode(key);
		final byte[] bField = SafeEncoder.encode(field);

		return hGet(bKey, bField, SafeEncoder::encode, args);
	}

	@Override
	public byte[] hGet(final byte[] key, final byte[] field) {
		final CommandArguments args = CommandArguments.create("key", key).put("field", field);
		return hGet(key, field, (v)->v, args);
	}

	@Override
	public Map<String, String> hGetAll(final String key) {
		final CommandArguments args = CommandArguments.create("key", key);
		final byte[] bKey = SafeEncoder.encode(key);
		final MapConverter<byte[], byte[], String, String> binaryToStringMapConverter = Converters.mapBinaryToString();

		return hGetAll(bKey, binaryToStringMapConverter, args);
	}

	@Override
	public Map<byte[], byte[]> hGetAll(final byte[] key) {
		final CommandArguments args = CommandArguments.create("key", key);
		return hGetAll(key, (v)->v, args);
	}

	@Override
	public Long hIncrBy(final byte[] key, final byte[] field, final long value) {
		final CommandArguments args = CommandArguments.create("key", key).put("field", field).put("value", value);

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, ProtocolCommand.HINCRBY,
					(cmd)->cmd.hincrby(key, field, value), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, ProtocolCommand.HINCRBY,
					(cmd)->cmd.hincrby(key, field, value), (v)->v)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, ProtocolCommand.HINCRBY, (cmd)->cmd.hincrby(key, field, value),
					(v)->v)
					.run(args);
		}
	}

	@Override
	public Double hIncrByFloat(final byte[] key, final byte[] field, final double value) {
		final CommandArguments args = CommandArguments.create("key", key).put("field", field).put("value", value);

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, ProtocolCommand.HINCRBYFLOAT,
					(cmd)->cmd.hincrbyfloat(key, field, value), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, ProtocolCommand.HINCRBYFLOAT,
					(cmd)->cmd.hincrbyfloat(key, field, value), (v)->v)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, ProtocolCommand.HINCRBYFLOAT,
					(cmd)->cmd.hincrbyfloat(key, field, value), (v)->v)
					.run(args);
		}
	}

	@Override
	public Set<String> hKeys(final String key) {
		final CommandArguments args = CommandArguments.create("key", key);
		final byte[] bKey = SafeEncoder.encode(key);
		final ListSetConverter<byte[], String> binaryToStringListSetConverter =
				Converters.listSetBinaryToString();

		return hKeys(bKey, binaryToStringListSetConverter, args);
	}

	@Override
	public Set<byte[]> hKeys(final byte[] key) {
		final CommandArguments args = CommandArguments.create("key", key);
		return hKeys(key, HashSet::new, args);
	}

	@Override
	public Long hLen(final byte[] key) {
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, ProtocolCommand.HLEN, (cmd)->cmd.hlen(key), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, ProtocolCommand.HLEN, (cmd)->cmd.hlen(key), (v)->v)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, ProtocolCommand.HLEN, (cmd)->cmd.hlen(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public List<String> hMGet(final String key, final String... fields) {
		final CommandArguments args = CommandArguments.create("key", key).put("fields", (Object[]) fields);
		final byte[] bKey = SafeEncoder.encode(key);
		final byte[][] bFields = SafeEncoder.encode(fields);
		final ListConverter<KeyValue<byte[], byte[]>, String> listConverter =
				new ListConverter<>((v)->SafeEncoder.encode(v.getValue()));

		return hMGet(bKey, bFields, listConverter, args);
	}

	@Override
	public List<byte[]> hMGet(final byte[] key, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create("key", key).put("fields", (Object[]) fields);
		final ListConverter<KeyValue<byte[], byte[]>, byte[]> listConverter = new ListConverter<>(Value::getValue);

		return hMGet(key, fields, listConverter, args);
	}

	@Override
	public Status hMSet(final byte[] key, final Map<byte[], byte[]> data) {
		final CommandArguments args = CommandArguments.create("key", key).put("data", data);

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, ProtocolCommand.HMGET, (cmd)->cmd.hmset(key, data),
					okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, ProtocolCommand.HMGET, (cmd)->cmd.hmset(key, data),
					okStatusConverter)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, ProtocolCommand.HMGET, (cmd)->cmd.hmset(key, data),
					okStatusConverter)
					.run(args);
		}
	}

	@Override
	public String hRandField(final String key) {
		final CommandArguments args = CommandArguments.create("key", key);
		return hRandField(args);
	}

	@Override
	public byte[] hRandField(final byte[] key) {
		final CommandArguments args = CommandArguments.create("key", key);
		return hRandField(args);
	}

	@Override
	public List<String> hRandField(final String key, final long count) {
		final CommandArguments args = CommandArguments.create("key", key).put("count", count);
		return hRandField(args);
	}

	@Override
	public List<byte[]> hRandField(final byte[] key, final long count) {
		final CommandArguments args = CommandArguments.create("key", key).put("count", count);
		return hRandField(args);
	}

	@Override
	public Map<String, String> hRandFieldWithValues(final String key, final long count) {
		final CommandArguments args = CommandArguments.create("key", key).put("count", count);
		return hRandField(args);
	}

	@Override
	public Map<byte[], byte[]> hRandFieldWithValues(final byte[] key, final long count) {
		final CommandArguments args = CommandArguments.create("key", key).put("count", count);
		return hRandField(args);
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final String cursor) {
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor);
		final byte[] bKey = SafeEncoder.encode(key);
		final ScanCursor scanCursor = new LettuceScanCursor(cursor);
		final MapScanCursorConverter.BvSvMapScanCursorConverter bvSvMapScanCursorConverter =
				new MapScanCursorConverter.BvSvMapScanCursorConverter();

		return hScan(bKey, scanCursor, bvSvMapScanCursorConverter, args);
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor) {
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor);
		final ScanCursor scanCursor = new LettuceScanCursor(cursor);
		final MapScanCursorConverter<byte[], byte[]> mapScanCursorConverter = new MapScanCursorConverter<>();

		return hScan(key, scanCursor, mapScanCursorConverter, args);
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final String cursor, final String pattern) {
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor).put("pattern", pattern);
		final byte[] bKey = SafeEncoder.encode(key);
		final ScanCursor scanCursor = new LettuceScanCursor(cursor);
		final ScanArgs scanArgs = new LettuceScanArgs(pattern);
		final MapScanCursorConverter.BvSvMapScanCursorConverter bvSvMapScanCursorConverter =
				new MapScanCursorConverter.BvSvMapScanCursorConverter();

		return hScan(bKey, scanCursor, scanArgs, bvSvMapScanCursorConverter, args);
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor, final byte[] pattern) {
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor).put("pattern", pattern);
		final ScanCursor scanCursor = new LettuceScanCursor(cursor);
		final ScanArgs scanArgs = new LettuceScanArgs(pattern);
		final MapScanCursorConverter<byte[], byte[]> mapScanCursorConverter = new MapScanCursorConverter<>();

		return hScan(key, scanCursor, scanArgs, mapScanCursorConverter, args);
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final String cursor, final long count) {
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor).put("count", count);
		final byte[] bKey = SafeEncoder.encode(key);
		final ScanCursor scanCursor = new LettuceScanCursor(cursor);
		final ScanArgs scanArgs = new LettuceScanArgs(count);
		final MapScanCursorConverter.BvSvMapScanCursorConverter bvSvMapScanCursorConverter =
				new MapScanCursorConverter.BvSvMapScanCursorConverter();

		return hScan(bKey, scanCursor, scanArgs, bvSvMapScanCursorConverter, args);
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor, final long count) {
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor).put("count", count);
		final ScanCursor scanCursor = new LettuceScanCursor(cursor);
		final ScanArgs scanArgs = new LettuceScanArgs(count);
		final MapScanCursorConverter<byte[], byte[]> mapScanCursorConverter = new MapScanCursorConverter<>();

		return hScan(key, scanCursor, scanArgs, mapScanCursorConverter, args);
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final String cursor, final String pattern,
												 final long count) {
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor).put("pattern", pattern);
		final byte[] bKey = SafeEncoder.encode(key);
		final ScanCursor scanCursor = new LettuceScanCursor(cursor);
		final ScanArgs scanArgs = new LettuceScanArgs(pattern, count);
		final MapScanCursorConverter.BvSvMapScanCursorConverter bvSvMapScanCursorConverter =
				new MapScanCursorConverter.BvSvMapScanCursorConverter();

		return hScan(bKey, scanCursor, scanArgs, bvSvMapScanCursorConverter, args);
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor, final byte[] pattern,
												 final long count) {
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor).put("pattern", pattern)
				.put("count", count);
		final ScanCursor scanCursor = new LettuceScanCursor(cursor);
		final ScanArgs scanArgs = new LettuceScanArgs(pattern, count);
		final MapScanCursorConverter<byte[], byte[]> mapScanCursorConverter = new MapScanCursorConverter<>();

		return hScan(key, scanCursor, scanArgs, mapScanCursorConverter, args);
	}

	@Override
	public Long hSet(final byte[] key, final byte[] field, final byte[] value) {
		final CommandArguments args = CommandArguments.create("key", key).put("field", field).put("value", value);
		final Converter<Boolean, Long> converter = (v)->Boolean.TRUE.equals(v) ? 1L : 0L;

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, ProtocolCommand.HSET, (cmd)->cmd.hset(key, field, value),
					converter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, ProtocolCommand.HSET,
					(cmd)->cmd.hset(key, field, value), converter)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, ProtocolCommand.HSET, (cmd)->cmd.hset(key, field, value),
					converter)
					.run(args);
		}
	}

	@Override
	public Status hSetNx(final byte[] key, final byte[] field, final byte[] value) {
		final CommandArguments args = CommandArguments.create("key", key).put("field", field).put("value", value);

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, ProtocolCommand.HSETNX,
					(cmd)->cmd.hsetnx(key, field, value), booleanStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, ProtocolCommand.HSETNX,
					(cmd)->cmd.hsetnx(key, field, value), booleanStatusConverter)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, ProtocolCommand.HSETNX, (cmd)->cmd.hsetnx(key, field, value),
					booleanStatusConverter)
					.run(args);
		}
	}

	@Override
	public Long hStrLen(final byte[] key, final byte[] field) {
		final CommandArguments args = CommandArguments.create("key", key).put("field", field);

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, ProtocolCommand.HSETNX, (cmd)->cmd.hstrlen(key, field),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, ProtocolCommand.HSETNX,
					(cmd)->cmd.hstrlen(key, field), (v)->v)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, ProtocolCommand.HSETNX, (cmd)->cmd.hstrlen(key, field), (v)->v)
					.run(args);
		}
	}

	@Override
	public List<String> hVals(final String key) {
		final CommandArguments args = CommandArguments.create("key", key);
		final byte[] bKey = SafeEncoder.encode(key);

		return hVals(bKey, binaryToStringListConverter, args);
	}

	@Override
	public List<byte[]> hVals(final byte[] key) {
		final CommandArguments args = CommandArguments.create("key", key);
		return hVals(key, (v)->v, args);
	}

	private <V> V hGet(final byte[] key, final byte[] field, final Converter<byte[], V> converter,
					   final CommandArguments args) {
		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, ProtocolCommand.HGET, (cmd)->cmd.hget(key, field),
					converter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, ProtocolCommand.HGET, (cmd)->cmd.hget(key, field),
					converter)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, ProtocolCommand.HGET, (cmd)->cmd.hget(key, field), converter)
					.run(args);
		}
	}

	private <K, V> Map<K, V> hGetAll(final byte[] key, final Converter<Map<byte[], byte[]>, Map<K, V>> converter,
									 final CommandArguments args) {
		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, ProtocolCommand.HGETALL, (cmd)->cmd.hgetall(key),
					converter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, ProtocolCommand.HGETALL, (cmd)->cmd.hgetall(key),
					converter)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, ProtocolCommand.HGETALL, (cmd)->cmd.hgetall(key), converter)
					.run(args);
		}
	}

	private <V> Set<V> hKeys(final byte[] key, final Converter<List<byte[]>, Set<V>> converter,
							 final CommandArguments args) {
		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, ProtocolCommand.HKEYS, (cmd)->cmd.hkeys(key), converter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, ProtocolCommand.HKEYS, (cmd)->cmd.hkeys(key),
					converter)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, ProtocolCommand.HKEYS, (cmd)->cmd.hkeys(key), converter)
					.run(args);
		}
	}

	private <V> List<V> hMGet(final byte[] key, final byte[][] fields,
							  final Converter<List<KeyValue<byte[], byte[]>>, List<V>> converter,
							  final CommandArguments args) {
		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, ProtocolCommand.HMGET, (cmd)->cmd.hmget(key, fields),
					converter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, ProtocolCommand.HMGET, (cmd)->cmd.hmget(key, fields),
					converter)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, ProtocolCommand.HMGET, (cmd)->cmd.hmget(key, fields), converter)
					.run(args);
		}
	}

	private <SV, TV> TV hRandField(final CommandArguments args) {
		if(isPipeline()){
			return new LettuceClusterPipelineCommand<SV, TV>(client, ProtocolCommand.HRANDFIELD)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<SV, TV>(client, ProtocolCommand.HRANDFIELD)
					.run(args);
		}else{
			return new LettuceClusterCommand<SV, TV>(client, ProtocolCommand.HRANDFIELD)
					.run(args);
		}
	}

	private <K, V> ScanResult<Map<K, V>> hScan(final byte[] key, final ScanCursor cursor,
											   final Converter<MapScanCursor<byte[], byte[]>, ScanResult<Map<K, V>>> converter,
											   CommandArguments args) {
		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, ProtocolCommand.HSCAN, (cmd)->cmd.hscan(key, cursor),
					converter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, ProtocolCommand.HSCAN, (cmd)->cmd.hscan(key, cursor),
					converter)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, ProtocolCommand.HSCAN, (cmd)->cmd.hscan(key, cursor), converter)
					.run(args);
		}
	}

	private <K, V> ScanResult<Map<K, V>> hScan(final byte[] key, final ScanCursor cursor, final ScanArgs scanArgs,
											   final Converter<MapScanCursor<byte[], byte[]>, ScanResult<Map<K, V>>> converter,
											   CommandArguments args) {
		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, ProtocolCommand.HSCAN,
					(cmd)->cmd.hscan(key, cursor, scanArgs), converter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, ProtocolCommand.HSCAN,
					(cmd)->cmd.hscan(key, cursor, scanArgs), converter)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, ProtocolCommand.HSCAN, (cmd)->cmd.hscan(key, cursor, scanArgs),
					converter)
					.run(args);
		}
	}

	private <V> List<V> hVals(final byte[] key, final Converter<List<byte[]>, List<V>> converter,
							  final CommandArguments args) {
		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, ProtocolCommand.HVALS, (cmd)->cmd.hvals(key), converter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, ProtocolCommand.HVALS, (cmd)->cmd.hvals(key),
					converter)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, ProtocolCommand.HVALS, (cmd)->cmd.hvals(key), converter)
					.run(args);
		}
	}

}
