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
import com.buession.lang.KeyValue;
import com.buession.lang.Status;
import com.buession.redis.client.lettuce.LettuceClusterClient;
import com.buession.redis.core.ExpireOption;
import com.buession.redis.core.Keyword;
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.Command;
import com.buession.redis.core.command.args.HScanArgument;
import com.buession.redis.core.internal.convert.Converters;
import com.buession.redis.core.internal.convert.lettuce.response.KeyValueConverter;
import com.buession.redis.core.internal.convert.lettuce.response.ScanCursorConverter;
import com.buession.redis.core.internal.convert.lettuce.response.ValueConverter;
import com.buession.redis.core.internal.lettuce.LettuceExpireArgs;
import com.buession.redis.core.internal.lettuce.LettuceScanArgs;
import com.buession.redis.core.internal.lettuce.LettuceScanCursor;
import com.buession.redis.utils.SafeEncoder;
import io.lettuce.core.ExpireArgs;
import io.lettuce.core.MapScanCursor;
import io.lettuce.core.ScanArgs;
import io.lettuce.core.ScanCursor;

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
		final CommandArguments args = CommandArguments.create(key).add(fields);

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.HDEL, (cmd)->cmd.hdel(key, fields), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.HDEL, (cmd)->cmd.hdel(key, fields), (v)->v)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, Command.HDEL, (cmd)->cmd.hdel(key, fields), (v)->v)
					.run(args);
		}
	}

	@Override
	public Boolean hExists(final byte[] key, final byte[] field) {
		final CommandArguments args = CommandArguments.create(key).add(field);

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.HEXISTS, (cmd)->cmd.hexists(key, field), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.HEXISTS, (cmd)->cmd.hexists(key, field),
					(v)->v)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, Command.HEXISTS, (cmd)->cmd.hexists(key, field), (v)->v)
					.run(args);
		}
	}

	@Override
	public List<Long> hExpire(final byte[] key, final int lifetime, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(lifetime).add(Keyword.Hash.FIELDS, fields.length)
				.add(fields);

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.HEXPIRE,
					(cmd)->cmd.hexpire(key, lifetime, fields), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.HEXPIRE,
					(cmd)->cmd.hexpire(key, lifetime, fields), (v)->v)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, Command.HEXPIRE, (cmd)->cmd.hexpire(key, lifetime, fields),
					(v)->v)
					.run(args);
		}
	}

	@Override
	public List<Long> hExpire(final byte[] key, final int lifetime, final ExpireOption expireOption,
							  final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(lifetime).add(expireOption)
				.add(Keyword.Hash.FIELDS, fields.length).add(fields);
		final ExpireArgs expireArgs = new LettuceExpireArgs(expireOption);

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.HEXPIRE,
					(cmd)->cmd.hexpire(key, lifetime, expireArgs, fields), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.HEXPIRE,
					(cmd)->cmd.hexpire(key, lifetime, expireArgs, fields), (v)->v)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, Command.HEXPIRE,
					(cmd)->cmd.hexpire(key, lifetime, expireArgs, fields), (v)->v)
					.run(args);
		}
	}

	@Override
	public List<Long> hExpireAt(final byte[] key, final long unixTimestamp, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(unixTimestamp)
				.add(Keyword.Hash.FIELDS, fields.length).add(fields);

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.HEXPIREAT,
					(cmd)->cmd.hexpireat(key, unixTimestamp, fields), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.HEXPIREAT,
					(cmd)->cmd.hexpireat(key, unixTimestamp, fields), (v)->v)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, Command.HEXPIREAT,
					(cmd)->cmd.hexpireat(key, unixTimestamp, fields), (v)->v)
					.run(args);
		}
	}

	@Override
	public List<Long> hExpireAt(final byte[] key, final long unixTimestamp, final ExpireOption expireOption,
								final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(unixTimestamp).add(expireOption)
				.add(Keyword.Hash.FIELDS, fields.length).add(fields);
		final ExpireArgs expireArgs = new LettuceExpireArgs(expireOption);

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.HEXPIREAT,
					(cmd)->cmd.hexpireat(key, unixTimestamp, expireArgs, fields), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.HEXPIREAT,
					(cmd)->cmd.hexpireat(key, unixTimestamp, expireArgs, fields), (v)->v)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, Command.HEXPIREAT,
					(cmd)->cmd.hexpireat(key, unixTimestamp, expireArgs, fields), (v)->v)
					.run(args);
		}
	}

	@Override
	public List<Long> hExpireTime(final byte[] key, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(Keyword.Hash.FIELDS, fields.length).add(fields);

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.HEXPIRETIME, (cmd)->cmd.hexpiretime(key, fields),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.HEXPIRETIME,
					(cmd)->cmd.hexpiretime(key, fields), (v)->v)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, Command.HEXPIRETIME, (cmd)->cmd.hexpiretime(key, fields), (v)->v)
					.run(args);
		}
	}

	@Override
	public String hGet(final String key, final String field) {
		final CommandArguments args = CommandArguments.create(key).add(field);
		final byte[] bKey = SafeEncoder.encode(key);
		final byte[] bField = SafeEncoder.encode(field);

		return hGet(bKey, bField, SafeEncoder::encode, args);
	}

	@Override
	public byte[] hGet(final byte[] key, final byte[] field) {
		final CommandArguments args = CommandArguments.create(key).add(field);
		return hGet(key, field, (v)->v, args);
	}

	@Override
	public Map<String, String> hGetAll(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		final byte[] bKey = SafeEncoder.encode(key);
		final MapConverter<byte[], byte[], String, String> binaryToStringMapConverter = Converters.mapBinaryToString();

		return hGetAll(bKey, binaryToStringMapConverter, args);
	}

	@Override
	public Map<byte[], byte[]> hGetAll(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return hGetAll(key, (v)->v, args);
	}

	@Override
	public Long hIncrBy(final byte[] key, final byte[] field, final long value) {
		final CommandArguments args = CommandArguments.create(key).add(field).add(value);

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.HINCRBY, (cmd)->cmd.hincrby(key, field, value),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.HINCRBY,
					(cmd)->cmd.hincrby(key, field, value), (v)->v)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, Command.HINCRBY, (cmd)->cmd.hincrby(key, field, value), (v)->v)
					.run(args);
		}
	}

	@Override
	public Double hIncrByFloat(final byte[] key, final byte[] field, final double value) {
		final CommandArguments args = CommandArguments.create(key).add(field).add(value);

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.HINCRBYFLOAT,
					(cmd)->cmd.hincrbyfloat(key, field, value), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.HINCRBYFLOAT,
					(cmd)->cmd.hincrbyfloat(key, field, value), (v)->v)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, Command.HINCRBYFLOAT, (cmd)->cmd.hincrbyfloat(key, field, value),
					(v)->v)
					.run(args);
		}
	}

	@Override
	public Set<String> hKeys(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		final byte[] bKey = SafeEncoder.encode(key);
		final ListSetConverter<byte[], String> binaryToStringListSetConverter =
				Converters.listSetBinaryToString();

		return hKeys(bKey, binaryToStringListSetConverter, args);
	}

	@Override
	public Set<byte[]> hKeys(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return hKeys(key, HashSet::new, args);
	}

	@Override
	public Long hLen(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.HLEN, (cmd)->cmd.hlen(key), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.HLEN, (cmd)->cmd.hlen(key), (v)->v)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, Command.HLEN, (cmd)->cmd.hlen(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public List<String> hMGet(final String key, final String... fields) {
		final CommandArguments args = CommandArguments.create(key).add(fields);
		final byte[] bKey = SafeEncoder.encode(key);
		final byte[][] bFields = SafeEncoder.encode(fields);
		final ListConverter<io.lettuce.core.KeyValue<byte[], byte[]>, String> listConverter =
				new ListConverter<>((v)->SafeEncoder.encode(v.getValue()));

		return hMGet(bKey, bFields, listConverter, args);
	}

	@Override
	public List<byte[]> hMGet(final byte[] key, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(fields);
		final ListConverter<io.lettuce.core.KeyValue<byte[], byte[]>, byte[]> listConverter =
				new ListConverter<>(io.lettuce.core.KeyValue::getValue);

		return hMGet(key, fields, listConverter, args);
	}

	@Override
	public Status hMSet(final byte[] key, final Map<byte[], byte[]> data) {
		final CommandArguments args = CommandArguments.create(key).add(data);

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.HMSET, (cmd)->cmd.hmset(key, data),
					okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.HMSET, (cmd)->cmd.hmset(key, data),
					okStatusConverter)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, Command.HMSET, (cmd)->cmd.hmset(key, data), okStatusConverter)
					.run(args);
		}
	}

	@Override
	public List<Long> hPersist(final byte[] key, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(Keyword.Hash.FIELDS, fields);

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.HPERSIST, (cmd)->cmd.hpersist(key, fields),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.HPERSIST, (cmd)->cmd.hpersist(key, fields),
					(v)->v)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, Command.HPERSIST, (cmd)->cmd.hpersist(key, fields), (v)->v)
					.run(args);
		}
	}

	@Override
	public List<Long> hpExpire(final byte[] key, final int lifetime, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(lifetime).add(Keyword.Hash.FIELDS, fields);

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.HPEXPIRE,
					(cmd)->cmd.hpexpire(key, lifetime, fields), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.HPEXPIRE,
					(cmd)->cmd.hpexpire(key, lifetime, fields), (v)->v)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, Command.HPEXPIRE, (cmd)->cmd.hpexpire(key, lifetime, fields),
					(v)->v)
					.run(args);
		}
	}

	@Override
	public List<Long> hpExpire(final byte[] key, final int lifetime, final ExpireOption expireOption,
							   final byte[]... fields) {
		final CommandArguments args =
				CommandArguments.create(key).add(lifetime).add(expireOption).add(Keyword.Hash.FIELDS, fields);
		final ExpireArgs expireArgs = new LettuceExpireArgs(expireOption);

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.HPEXPIRE,
					(cmd)->cmd.hpexpire(key, lifetime, expireArgs, fields), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.HPEXPIRE,
					(cmd)->cmd.hpexpire(key, lifetime, expireArgs, fields), (v)->v)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, Command.HPEXPIRE,
					(cmd)->cmd.hpexpire(key, lifetime, expireArgs, fields), (v)->v)
					.run(args);
		}
	}

	@Override
	public List<Long> hpExpireAt(final byte[] key, final long unixTimestamp, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(unixTimestamp).add(Keyword.Hash.FIELDS, fields);

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.HPEXPIRE,
					(cmd)->cmd.hpexpireat(key, unixTimestamp, fields), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.HPEXPIRE,
					(cmd)->cmd.hpexpireat(key, unixTimestamp, fields), (v)->v)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, Command.HPEXPIRE,
					(cmd)->cmd.hpexpireat(key, unixTimestamp, fields), (v)->v)
					.run(args);
		}
	}

	@Override
	public List<Long> hpExpireAt(final byte[] key, final long unixTimestamp, final ExpireOption expireOption,
								 final byte[]... fields) {
		final CommandArguments args =
				CommandArguments.create(key).add(unixTimestamp).add(expireOption).add(Keyword.Hash.FIELDS, fields);
		final ExpireArgs expireArgs = new LettuceExpireArgs(expireOption);

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.HPEXPIRE,
					(cmd)->cmd.hpexpireat(key, unixTimestamp, expireArgs, fields), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.HPEXPIRE,
					(cmd)->cmd.hpexpireat(key, unixTimestamp, expireArgs, fields), (v)->v)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, Command.HPEXPIRE,
					(cmd)->cmd.hpexpireat(key, unixTimestamp, expireArgs, fields), (v)->v)
					.run(args);
		}
	}

	@Override
	public List<Long> hpExpireTime(final byte[] key, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(Keyword.Hash.FIELDS, fields);

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.HPEXPIRETIME,
					(cmd)->cmd.hpexpiretime(key, fields), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.HPEXPIRETIME,
					(cmd)->cmd.hpexpiretime(key, fields), (v)->v)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, Command.HPEXPIRETIME, (cmd)->cmd.hpexpiretime(key, fields),
					(v)->v)
					.run(args);
		}
	}

	@Override
	public List<Long> hpTtl(final byte[] key, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(Keyword.Hash.FIELDS, fields);

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.HPTTL, (cmd)->cmd.hpttl(key, fields), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.HPTTL, (cmd)->cmd.hpttl(key, fields), (v)->v)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, Command.HPTTL, (cmd)->cmd.hpttl(key, fields), (v)->v)
					.run(args);
		}
	}

	@Override
	public String hRandField(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		final byte[] bKey = SafeEncoder.encode(key);

		return hRandField(bKey, SafeEncoder::encode, args);
	}

	@Override
	public byte[] hRandField(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return hRandField(key, (v)->v, args);
	}

	@Override
	public List<String> hRandField(final String key, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(count);
		final byte[] bKey = SafeEncoder.encode(key);
		final ListConverter<byte[], String> listConverter = Converters.listBinaryToString();

		return hRandField(bKey, count, listConverter, args);
	}

	@Override
	public List<byte[]> hRandField(final byte[] key, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(count);
		return hRandField(key, count, (v)->v, args);
	}

	@Override
	public List<KeyValue<String, String>> hRandFieldWithValues(final String key, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(count).add(Keyword.Hash.WITHVALUES);
		final byte[] bKey = SafeEncoder.encode(key);
		final ListConverter<io.lettuce.core.KeyValue<byte[], byte[]>, KeyValue<String, String>> listConverter =
				KeyValueConverter.listConverter(SafeEncoder::encode, SafeEncoder::encode);

		return hRandFieldWithValues(bKey, count, listConverter, args);
	}

	@Override
	public List<KeyValue<byte[], byte[]>> hRandFieldWithValues(final byte[] key, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(count).add(Keyword.Hash.WITHVALUES);
		final ListConverter<io.lettuce.core.KeyValue<byte[], byte[]>, KeyValue<byte[], byte[]>> listConverter =
				KeyValueConverter.listConverter((k)->k, (v)->v);

		return hRandFieldWithValues(key, count, listConverter, args);
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final String cursor) {
		final CommandArguments args = CommandArguments.create(key).add(cursor);
		final byte[] bKey = SafeEncoder.encode(key);
		final ScanCursor scanCursor = new LettuceScanCursor(cursor);
		final ScanCursorConverter.MapScanCursorConverter.BvSvMapScanCursorConverter bvSvMapScanCursorConverter =
				new ScanCursorConverter.MapScanCursorConverter.BvSvMapScanCursorConverter();

		return hScan(bKey, scanCursor, bvSvMapScanCursorConverter, args);
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor) {
		final CommandArguments args = CommandArguments.create(key).add(cursor);
		final ScanCursor scanCursor = new LettuceScanCursor(cursor);
		final ScanCursorConverter.MapScanCursorConverter<byte[], byte[]> mapScanCursorConverter = new ScanCursorConverter.MapScanCursorConverter<>();

		return hScan(key, scanCursor, mapScanCursorConverter, args);
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final String cursor,
												 final HScanArgument<String> scanArgument) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(scanArgument);
		final byte[] bKey = SafeEncoder.encode(key);
		final ScanCursor scanCursor = new LettuceScanCursor(cursor);
		final ScanArgs scanArgs = LettuceScanArgs.from(scanArgument);
		final ScanCursorConverter.MapScanCursorConverter.BvSvMapScanCursorConverter bvSvMapScanCursorConverter =
				new ScanCursorConverter.MapScanCursorConverter.BvSvMapScanCursorConverter();

		return hScan(bKey, scanCursor, scanArgs, bvSvMapScanCursorConverter, args);
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor,
												 final HScanArgument<byte[]> scanArgument) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(scanArgument);
		final ScanCursor scanCursor = new LettuceScanCursor(cursor);
		final ScanArgs scanArgs = LettuceScanArgs.from(scanArgument);
		final ScanCursorConverter.MapScanCursorConverter<byte[], byte[]> mapScanCursorConverter = new ScanCursorConverter.MapScanCursorConverter<>();

		return hScan(key, scanCursor, scanArgs, mapScanCursorConverter, args);
	}

	@Override
	public Long hSet(final byte[] key, final KeyValue<byte[], byte[]>... data) {
		final CommandArguments args = CommandArguments.create(key).add(data);

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.HSET, (cmd)->cmd.hset(key, field, value),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.HSET,
					(cmd)->cmd.hset(key, field, value), converter)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, Command.HSET, (cmd)->cmd.hset(key, field, value),
					converter)
					.run(args);
		}
	}

	@Override
	public Status hSetNx(final byte[] key, final byte[] field, final byte[] value) {
		final CommandArguments args = CommandArguments.create(key).add(field).add(value);

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.HSETNX, (cmd)->cmd.hsetnx(key, field, value),
					booleanStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.HSETNX, (cmd)->cmd.hsetnx(key, field, value),
					booleanStatusConverter)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, Command.HSETNX, (cmd)->cmd.hsetnx(key, field, value),
					booleanStatusConverter)
					.run(args);
		}
	}

	@Override
	public Long hStrLen(final byte[] key, final byte[] field) {
		final CommandArguments args = CommandArguments.create(key).add(field);

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.HSETNX, (cmd)->cmd.hstrlen(key, field), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.HSETNX, (cmd)->cmd.hstrlen(key, field),
					(v)->v)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, Command.HSETNX, (cmd)->cmd.hstrlen(key, field), (v)->v)
					.run(args);
		}
	}

	@Override
	public List<Long> hTtl(final byte[] key, final byte[]... fields) {
		final CommandArguments args = CommandArguments.create(key).add(fields);

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.HPTTL, (cmd)->cmd.httl(key, fields), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.HPTTL, (cmd)->cmd.httl(key, fields), (v)->v)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, Command.HSETNX, Command.HPTTL, (cmd)->cmd.httl(key, fields),
					(v)->v)
					.run(args);
		}
	}

	@Override
	public List<String> hVals(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		final byte[] bKey = SafeEncoder.encode(key);
		final ListConverter<byte[], String> listConverter = Converters.listBinaryToString();

		return hVals(bKey, listConverter, args);
	}

	@Override
	public List<byte[]> hVals(final byte[] key) {
		final CommandArguments args = CommandArguments.create("key", key);
		return hVals(key, (v)->v, args);
	}

	private <V> V hGet(final byte[] key, final byte[] field, final Converter<byte[], V> converter,
					   final CommandArguments args) {
		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.HGET, (cmd)->cmd.hget(key, field), converter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.HGET, (cmd)->cmd.hget(key, field), converter)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, Command.HGET, (cmd)->cmd.hget(key, field), converter)
					.run(args);
		}
	}

	private <K, V> Map<K, V> hGetAll(final byte[] key, final Converter<Map<byte[], byte[]>, Map<K, V>> converter,
									 final CommandArguments args) {
		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.HGETALL, (cmd)->cmd.hgetall(key), converter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.HGETALL, (cmd)->cmd.hgetall(key), converter)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, Command.HGETALL, (cmd)->cmd.hgetall(key), converter)
					.run(args);
		}
	}

	private <V> Set<V> hKeys(final byte[] key, final Converter<List<byte[]>, Set<V>> converter,
							 final CommandArguments args) {
		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.HKEYS, (cmd)->cmd.hkeys(key), converter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.HKEYS, (cmd)->cmd.hkeys(key), converter)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, Command.HKEYS, (cmd)->cmd.hkeys(key), converter)
					.run(args);
		}
	}

	private <V> List<V> hMGet(final byte[] key, final byte[][] fields,
							  final ListConverter<io.lettuce.core.KeyValue<byte[], byte[]>, V> converter,
							  final CommandArguments args) {
		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.HMGET, (cmd)->cmd.hmget(key, fields),
					converter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.HMGET, (cmd)->cmd.hmget(key, fields),
					converter)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, Command.HMGET, (cmd)->cmd.hmget(key, fields), converter)
					.run(args);
		}
	}

	private <V> V hRandField(final byte[] key, final Converter<byte[], V> converter, final CommandArguments args) {
		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.HRANDFIELD, (cmd)->cmd.hrandfield(key),
					converter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.HRANDFIELD, (cmd)->cmd.hrandfield(key),
					converter)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, Command.HRANDFIELD, (cmd)->cmd.hrandfield(key), converter)
					.run(args);
		}
	}

	private <V> List<V> hRandField(final byte[] key, final int count, final Converter<List<byte[]>, List<V>> converter,
								   final CommandArguments args) {
		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.HRANDFIELD, (cmd)->cmd.hrandfield(key, count),
					converter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.HRANDFIELD, (cmd)->cmd.hrandfield(key, count),
					converter)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, Command.HRANDFIELD, (cmd)->cmd.hrandfield(key, count), converter)
					.run(args);
		}
	}

	private <K, V> List<KeyValue<K, V>> hRandFieldWithValues(final byte[] key, final int count,
															 final ListConverter<io.lettuce.core.KeyValue<byte[], byte[]>,
																	 KeyValue<K, V>> converter,
															 final CommandArguments args) {
		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.HRANDFIELD,
					(cmd)->cmd.hrandfieldWithvalues(key, count), converter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.HRANDFIELD,
					(cmd)->cmd.hrandfieldWithvalues(key, count), converter)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, Command.HRANDFIELD, (cmd)->cmd.hrandfieldWithvalues(key, count),
					converter)
					.run(args);
		}
	}

	private <K, V> ScanResult<Map<K, V>> hScan(final byte[] key, final ScanCursor cursor,
											   final Converter<MapScanCursor<byte[], byte[]>, ScanResult<Map<K, V>>> converter,
											   CommandArguments args) {
		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.HSCAN, (cmd)->cmd.hscan(key, cursor),
					converter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.HSCAN, (cmd)->cmd.hscan(key, cursor),
					converter)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, Command.HSCAN, (cmd)->cmd.hscan(key, cursor), converter)
					.run(args);
		}
	}

	private <K, V> ScanResult<Map<K, V>> hScan(final byte[] key, final ScanCursor cursor, final ScanArgs scanArgs,
											   final Converter<MapScanCursor<byte[], byte[]>, ScanResult<Map<K, V>>> converter,
											   CommandArguments args) {
		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.HSCAN, (cmd)->cmd.hscan(key, cursor, scanArgs),
					converter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.HSCAN,
					(cmd)->cmd.hscan(key, cursor, scanArgs), converter)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, Command.HSCAN, (cmd)->cmd.hscan(key, cursor, scanArgs),
					converter)
					.run(args);
		}
	}

	private <V> List<V> hVals(final byte[] key, final Converter<List<byte[]>, List<V>> converter,
							  final CommandArguments args) {
		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.HVALS, (cmd)->cmd.hvals(key), converter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.HVALS, (cmd)->cmd.hvals(key),
					converter)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, Command.HVALS, (cmd)->cmd.hvals(key), converter)
					.run(args);
		}
	}

}
