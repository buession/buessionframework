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
import com.buession.core.converter.SetConverter;
import com.buession.lang.Status;
import com.buession.redis.client.lettuce.LettuceClusterClient;
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.Command;
import com.buession.redis.core.internal.convert.Converters;
import com.buession.redis.core.internal.convert.lettuce.response.ScanCursorConverter;
import com.buession.redis.core.internal.lettuce.LettuceScanArgs;
import com.buession.redis.core.internal.lettuce.LettuceScanCursor;
import com.buession.redis.utils.SafeEncoder;
import io.lettuce.core.ScanArgs;
import io.lettuce.core.ScanCursor;
import io.lettuce.core.ValueScanCursor;

import java.util.List;
import java.util.Set;

/**
 * Lettuce 集群模式集合命令操作
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public final class LettuceClusterSetOperations extends AbstractSetOperations<LettuceClusterClient> {

	public LettuceClusterSetOperations(final LettuceClusterClient client) {
		super(client);
	}

	@Override
	public Long sAdd(final byte[] key, final byte[]... members) {
		final CommandArguments args = CommandArguments.create("key", key).put("members", (Object[]) members);

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.SADD, (cmd)->cmd.sadd(key, members),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.SADD, (cmd)->cmd.sadd(key, members),
					(v)->v)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, Command.SADD, (cmd)->cmd.sadd(key, members), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long sCard(final byte[] key) {
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.SCARD, (cmd)->cmd.scard(key), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.SCARD, (cmd)->cmd.scard(key), (v)->v)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, Command.SCARD, (cmd)->cmd.scard(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public Set<String> sDiff(final String... keys) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys);
		final byte[][] bKeys = SafeEncoder.encode(keys);
		final SetConverter<byte[], String> binaryToStringSetConverter = Converters.setBinaryToString();

		return sDiff(bKeys, binaryToStringSetConverter, args);
	}

	@Override
	public Set<byte[]> sDiff(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys);
		return sDiff(keys, (v)->v, args);
	}

	@Override
	public Long sDiffStore(final byte[] destKey, final byte[]... keys) {
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("keys", (Object[]) keys);

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.SDIFFSTORE,
					(cmd)->cmd.sdiffstore(destKey, keys), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.SDIFFSTORE,
					(cmd)->cmd.sdiffstore(destKey, keys), (v)->v)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, Command.SDIFFSTORE, (cmd)->cmd.sdiffstore(destKey, keys),
					(v)->v)
					.run(args);
		}
	}

	@Override
	public Set<String> sInter(final String... keys) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys);
		final byte[][] bKeys = SafeEncoder.encode(keys);
		final SetConverter<byte[], String> binaryToStringSetConverter = Converters.setBinaryToString();

		return sInter(bKeys, binaryToStringSetConverter, args);
	}

	@Override
	public Set<byte[]> sInter(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys);
		return sInter(keys, (v)->v, args);
	}

	@Override
	public Long sInterStore(final byte[] destKey, final byte[]... keys) {
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("keys", (Object[]) keys);

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.SINTERSTORE,
					(cmd)->cmd.sinterstore(destKey, keys), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.SINTERSTORE,
					(cmd)->cmd.sinterstore(destKey, keys), (v)->v)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, Command.SINTERSTORE,
					(cmd)->cmd.sinterstore(destKey, keys), (v)->v)
					.run(args);
		}
	}

	@Override
	public Boolean sIsMember(final byte[] key, final byte[] member) {
		final CommandArguments args = CommandArguments.create("key", key).put("member", member);

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.SISMEMBER,
					(cmd)->cmd.sismember(key, member), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.SISMEMBER,
					(cmd)->cmd.sismember(key, member), (v)->v)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, Command.SISMEMBER, (cmd)->cmd.sismember(key, member),
					(v)->v)
					.run(args);
		}
	}

	@Override
	public List<Boolean> smIsMember(final String key, final String... members) {
		final CommandArguments args = CommandArguments.create("key", key).put("members", (Object[]) members);
		return smIsMember(args);
	}

	@Override
	public List<Boolean> smIsMember(final byte[] key, final byte[]... members) {
		final CommandArguments args = CommandArguments.create("key", key).put("members", (Object[]) members);
		return smIsMember(args);
	}

	@Override
	public Set<String> sMembers(final String key) {
		final CommandArguments args = CommandArguments.create("key", key);
		final byte[] bKey = SafeEncoder.encode(key);
		final SetConverter<byte[], String> binaryToStringSetConverter = Converters.setBinaryToString();

		return sMembers(bKey, binaryToStringSetConverter, args);
	}

	@Override
	public Set<byte[]> sMembers(final byte[] key) {
		final CommandArguments args = CommandArguments.create("key", key);
		return sMembers(key, (v)->v, args);
	}

	@Override
	public Status sMove(final byte[] key, final byte[] destKey, final byte[] member) {
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey).put("member", member);

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.SMOVE,
					(cmd)->cmd.smove(key, destKey, member), booleanStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.SMOVE,
					(cmd)->cmd.smove(key, destKey, member), booleanStatusConverter)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, Command.SMOVE, (cmd)->cmd.smove(key, destKey, member),
					booleanStatusConverter)
					.run(args);
		}
	}

	@Override
	public String sPop(final String key) {
		final CommandArguments args = CommandArguments.create("key", key);
		final byte[] bKey = SafeEncoder.encode(key);

		return sPop(bKey, SafeEncoder::encode, args);
	}

	@Override
	public byte[] sPop(final byte[] key) {
		final CommandArguments args = CommandArguments.create("key", key);
		return sPop(key, (v)->v, args);
	}

	@Override
	public Set<String> sPop(final String key, final int count) {
		final CommandArguments args = CommandArguments.create("key", key);
		final byte[] bKey = SafeEncoder.encode(key);
		final SetConverter<byte[], String> binaryToStringSetConverter = Converters.setBinaryToString();

		return sPop(bKey, count, binaryToStringSetConverter, args);
	}

	@Override
	public Set<byte[]> sPop(final byte[] key, final int count) {
		final CommandArguments args = CommandArguments.create("key", key).put("count", count);
		return sPop(key, count, (v)->v, args);
	}

	@Override
	public String sRandMember(final String key) {
		final CommandArguments args = CommandArguments.create("key", key);
		final byte[] bKey = SafeEncoder.encode(key);

		return sRandMember(bKey, SafeEncoder::encode, args);
	}

	@Override
	public byte[] sRandMember(final byte[] key) {
		final CommandArguments args = CommandArguments.create("key", key);
		return sRandMember(key, (v)->v, args);
	}

	@Override
	public List<String> sRandMember(final String key, final int count) {
		final CommandArguments args = CommandArguments.create("key", key).put("count", count);
		final byte[] bKey = SafeEncoder.encode(key);
		final ListConverter<byte[], String> listConverter = Converters.listBinaryToString();

		return sRandMember(bKey, count, listConverter, args);
	}

	@Override
	public List<byte[]> sRandMember(final byte[] key, final int count) {
		final CommandArguments args = CommandArguments.create("key", key).put("count", count);
		return sRandMember(key, count, (v)->v, args);
	}

	@Override
	public Long sRem(final byte[] key, final byte[]... members) {
		final CommandArguments args = CommandArguments.create("key", key).put("members", (Object[]) members);

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.SREM, (cmd)->cmd.srem(key, members),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.SREM, (cmd)->cmd.srem(key, members),
					(v)->v)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, Command.SREM, (cmd)->cmd.srem(key, members), (v)->v)
					.run(args);
		}
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final String cursor) {
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor);
		final byte[] bKey = SafeEncoder.encode(key);
		final ScanCursor scanCursor = new LettuceScanCursor(cursor);
		final ScanCursorConverter.ValueScanCursorConverter.BSKeyScanCursorConverter bsKeyScanCursorConverter =
				new ScanCursorConverter.ValueScanCursorConverter.BSKeyScanCursorConverter();

		return sScan(bKey, scanCursor, bsKeyScanCursorConverter, args);
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor) {
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor);
		final ScanCursor scanCursor = new LettuceScanCursor(cursor);
		final ScanCursorConverter.ValueScanCursorConverter<byte[]> valueScanCursorConverter = new ScanCursorConverter.ValueScanCursorConverter<>();

		return sScan(key, scanCursor, valueScanCursorConverter, args);
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final String cursor, final String pattern) {
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor).put("pattern", pattern);
		final byte[] bKey = SafeEncoder.encode(key);
		final ScanCursor scanCursor = new LettuceScanCursor(pattern);
		final ScanCursorConverter.ValueScanCursorConverter.BSKeyScanCursorConverter bsKeyScanCursorConverter =
				new ScanCursorConverter.ValueScanCursorConverter.BSKeyScanCursorConverter();

		return sScan(bKey, scanCursor, bsKeyScanCursorConverter, args);
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor, final byte[] pattern) {
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor).put("pattern", pattern);
		final ScanCursor scanCursor = new LettuceScanCursor(pattern);
		final ScanCursorConverter.ValueScanCursorConverter<byte[]> valueScanCursorConverter = new ScanCursorConverter.ValueScanCursorConverter<>();

		return sScan(key, scanCursor, valueScanCursorConverter, args);
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final String cursor, final int count) {
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor).put("count", count);
		final byte[] bKey = SafeEncoder.encode(key);
		final ScanCursor scanCursor = new LettuceScanCursor(cursor);
		final ScanArgs scanArgs = new LettuceScanArgs(count);
		final ScanCursorConverter.ValueScanCursorConverter.BSKeyScanCursorConverter bsKeyScanCursorConverter =
				new ScanCursorConverter.ValueScanCursorConverter.BSKeyScanCursorConverter();

		return sScan(bKey, scanCursor, scanArgs, bsKeyScanCursorConverter, args);
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor, final int count) {
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor).put("count", count);
		final ScanCursor scanCursor = new LettuceScanCursor(cursor);
		final ScanArgs scanArgs = new LettuceScanArgs(count);
		final ScanCursorConverter.ValueScanCursorConverter<byte[]> valueScanCursorConverter = new ScanCursorConverter.ValueScanCursorConverter<>();

		return sScan(key, scanCursor, scanArgs, valueScanCursorConverter, args);
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final String cursor, final String pattern,
										  final int count) {
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor).put("pattern", pattern)
				.put("count", count);
		final byte[] bKey = SafeEncoder.encode(key);
		final ScanCursor scanCursor = new LettuceScanCursor(cursor);
		final ScanArgs scanArgs = new LettuceScanArgs(pattern, count);
		final ScanCursorConverter.ValueScanCursorConverter.BSKeyScanCursorConverter bsKeyScanCursorConverter =
				new ScanCursorConverter.ValueScanCursorConverter.BSKeyScanCursorConverter();

		return sScan(bKey, scanCursor, scanArgs, bsKeyScanCursorConverter, args);
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor, final byte[] pattern,
										  final int count) {
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor).put("pattern", pattern)
				.put("count", count);
		final ScanCursor scanCursor = new LettuceScanCursor(cursor);
		final ScanArgs scanArgs = new LettuceScanArgs(pattern, count);
		final ScanCursorConverter.ValueScanCursorConverter<byte[]> valueScanCursorConverter = new ScanCursorConverter.ValueScanCursorConverter<>();

		return sScan(key, scanCursor, scanArgs, valueScanCursorConverter, args);
	}

	@Override
	public Set<String> sUnion(final String... keys) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys);
		final byte[][] bKeys = SafeEncoder.encode(keys);
		final SetConverter<byte[], String> binaryToStringSetConverter = Converters.setBinaryToString();

		return sUnion(bKeys, binaryToStringSetConverter, args);
	}

	@Override
	public Set<byte[]> sUnion(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys);
		return sUnion(keys, (v)->v, args);
	}

	@Override
	public Long sUnionStore(final byte[] destKey, final byte[]... keys) {
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("keys", (Object[]) keys);

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.SUNIONSTORE,
					(cmd)->cmd.sunionstore(destKey, keys), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.SUNIONSTORE,
					(cmd)->cmd.sunionstore(destKey, keys), (v)->v)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, Command.SUNIONSTORE,
					(cmd)->cmd.sunionstore(destKey, keys), (v)->v)
					.run(args);
		}
	}

	private <V> Set<V> sDiff(final byte[][] keys, final Converter<Set<byte[]>, Set<V>> converter,
							 final CommandArguments args) {
		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.SDIFF, (cmd)->cmd.sdiff(keys), converter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.SDIFF, (cmd)->cmd.sdiff(keys),
					converter)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, Command.SDIFF, (cmd)->cmd.sdiff(keys), converter)
					.run(args);
		}
	}

	private <V> Set<V> sInter(final byte[][] keys, final Converter<Set<byte[]>, Set<V>> converter,
							  final CommandArguments args) {
		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.SINTER, (cmd)->cmd.sinter(keys),
					converter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.SINTER, (cmd)->cmd.sinter(keys),
					converter)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, Command.SINTER, (cmd)->cmd.sinter(keys), converter)
					.run(args);
		}
	}

	private List<Boolean> smIsMember(final CommandArguments args) {
		if(isPipeline()){
			return new LettuceClusterPipelineCommand<List<Boolean>, List<Boolean>>(client, Command.SMISMEMBER)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<List<Boolean>, List<Boolean>>(client,
					Command.SMISMEMBER)
					.run(args);
		}else{
			return new LettuceClusterCommand<List<Boolean>, List<Boolean>>(client, Command.SMISMEMBER)
					.run(args);
		}
	}

	private <V> Set<V> sMembers(final byte[] key, final Converter<Set<byte[]>, Set<V>> converter,
								final CommandArguments args) {
		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.SMEMBERS, (cmd)->cmd.smembers(key),
					converter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.SMEMBERS, (cmd)->cmd.smembers(key),
					converter)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, Command.SMEMBERS, (cmd)->cmd.smembers(key), converter)
					.run(args);
		}
	}

	private <V> V sPop(final byte[] key, final Converter<byte[], V> converter, final CommandArguments args) {
		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.SPOP, (cmd)->cmd.spop(key), converter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.SPOP, (cmd)->cmd.spop(key), converter)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, Command.SPOP, (cmd)->cmd.spop(key), converter)
					.run(args);
		}
	}

	private <V> Set<V> sPop(final byte[] key, final int count, final Converter<Set<byte[]>, Set<V>> converter,
							final CommandArguments args) {
		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.SPOP, (cmd)->cmd.spop(key, count),
					converter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.SPOP, (cmd)->cmd.spop(key, count),
					converter)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, Command.SPOP, (cmd)->cmd.spop(key, count), converter)
					.run(args);
		}
	}

	private <V> V sRandMember(final byte[] key, final Converter<byte[], V> converter, final CommandArguments args) {
		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.SPOP, (cmd)->cmd.srandmember(key),
					converter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.SPOP, (cmd)->cmd.srandmember(key),
					converter)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, Command.SRANDMEMBER, (cmd)->cmd.srandmember(key),
					converter)
					.run(args);
		}
	}

	private <V> List<V> sRandMember(final byte[] key, final int count,
									final Converter<List<byte[]>, List<V>> converter, final CommandArguments args) {
		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.SRANDMEMBER,
					(cmd)->cmd.srandmember(key, count), converter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.SRANDMEMBER,
					(cmd)->cmd.srandmember(key, count), converter)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, Command.SRANDMEMBER, (cmd)->cmd.srandmember(key, count),
					converter)
					.run(args);
		}
	}

	private <V> ScanResult<List<V>> sScan(final byte[] key, final ScanCursor cursor,
										  final Converter<ValueScanCursor<byte[]>, ScanResult<List<V>>> converter,
										  final CommandArguments args) {
		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.SSCAN, (cmd)->cmd.sscan(key, cursor),
					converter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.SSCAN, (cmd)->cmd.sscan(key, cursor),
					converter)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, Command.SSCAN, (cmd)->cmd.sscan(key, cursor), converter)
					.run(args);
		}
	}

	private <V> ScanResult<List<V>> sScan(final byte[] key, final ScanCursor cursor, final ScanArgs scanArgs,
										  final Converter<ValueScanCursor<byte[]>, ScanResult<List<V>>> converter,
										  final CommandArguments args) {
		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.SSCAN,
					(cmd)->cmd.sscan(key, cursor, scanArgs), converter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.SSCAN,
					(cmd)->cmd.sscan(key, cursor, scanArgs), converter)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, Command.SSCAN, (cmd)->cmd.sscan(key, cursor, scanArgs),
					converter)
					.run(args);
		}
	}

	private <V> Set<V> sUnion(final byte[][] keys, final Converter<Set<byte[]>, Set<V>> converter,
							  final CommandArguments args) {
		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.SUNION, (cmd)->cmd.sunion(keys),
					converter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.SUNION, (cmd)->cmd.sunion(keys),
					converter)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, Command.SUNION, (cmd)->cmd.sunion(keys), converter)
					.run(args);
		}
	}

}