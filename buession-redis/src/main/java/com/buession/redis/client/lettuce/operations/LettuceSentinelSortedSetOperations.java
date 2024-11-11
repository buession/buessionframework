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
import com.buession.core.utils.NumberUtils;
import com.buession.lang.KeyValue;
import com.buession.redis.client.lettuce.LettuceSentinelClient;
import com.buession.redis.core.Aggregate;
import com.buession.redis.core.GtLt;
import com.buession.redis.core.NxXx;
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.Tuple;
import com.buession.redis.core.ZRangeBy;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.internal.convert.lettuce.response.ScanCursorConverter;
import com.buession.redis.core.internal.lettuce.LettuceScanArgs;
import com.buession.redis.core.internal.lettuce.LettuceScanCursor;
import com.buession.redis.core.internal.lettuce.LettuceZAddArgs;
import com.buession.redis.core.internal.lettuce.LettuceZStoreArgs;
import com.buession.redis.utils.SafeEncoder;
import io.lettuce.core.Limit;
import io.lettuce.core.Range;
import io.lettuce.core.ScanArgs;
import io.lettuce.core.ScanCursor;
import io.lettuce.core.ScoredValue;
import io.lettuce.core.ScoredValueScanCursor;
import io.lettuce.core.ZAddArgs;
import io.lettuce.core.ZStoreArgs;

import java.util.List;
import java.util.Map;

/**
 * Lettuce 哨兵模式模式有序集合命令操作
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public final class LettuceSentinelSortedSetOperations extends AbstractSortedSetOperations<LettuceSentinelClient> {

	public LettuceSentinelSortedSetOperations(final LettuceSentinelClient client) {
		super(client);
	}

	@Override
	public Tuple zPopMin(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);

		if(isPipeline()){
			return new LettuceSentinelPipelineCommand<Tuple, Tuple>(client, ProtocolCommand.ZPOPMIN)
					.run(args);
		}else if(isTransaction()){
			return new LettuceSentinelTransactionCommand<Tuple, Tuple>(client, ProtocolCommand.ZPOPMIN)
					.run(args);
		}else{
			return new LettuceSentinelCommand<Tuple, Tuple>(client, ProtocolCommand.ZPOPMIN)
					.run(args);
		}
	}

	@Override
	public List<Tuple> zPopMin(final byte[] key, final long count) {
		final CommandArguments args = CommandArguments.create(key).add(count);

		if(isPipeline()){
			return new LettuceSentinelPipelineCommand<List<Tuple>, List<Tuple>>(client, ProtocolCommand.ZPOPMIN)
					.run(args);
		}else if(isTransaction()){
			return new LettuceSentinelTransactionCommand<List<Tuple>, List<Tuple>>(client, ProtocolCommand.ZPOPMIN)
					.run(args);
		}else{
			return new LettuceSentinelCommand<List<Tuple>, List<Tuple>>(client, ProtocolCommand.ZPOPMIN)
					.run(args);
		}
	}

	@Override
	public Tuple zPopMax(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);

		if(isPipeline()){
			return new LettuceSentinelPipelineCommand<Tuple, Tuple>(client, ProtocolCommand.ZPOPMAX)
					.run(args);
		}else if(isTransaction()){
			return new LettuceSentinelTransactionCommand<Tuple, Tuple>(client, ProtocolCommand.ZPOPMAX)
					.run(args);
		}else{
			return new LettuceSentinelCommand<Tuple, Tuple>(client, ProtocolCommand.ZPOPMAX)
					.run(args);
		}
	}

	@Override
	public List<Tuple> zPopMax(final byte[] key, final long count) {
		final CommandArguments args = CommandArguments.create(key).add(count);

		if(isPipeline()){
			return new LettuceSentinelPipelineCommand<List<Tuple>, List<Tuple>>(client, ProtocolCommand.ZPOPMAX)
					.run(args);
		}else if(isTransaction()){
			return new LettuceSentinelTransactionCommand<List<Tuple>, List<Tuple>>(client, ProtocolCommand.ZPOPMAX)
					.run(args);
		}else{
			return new LettuceSentinelCommand<List<Tuple>, List<Tuple>>(client, ProtocolCommand.ZPOPMAX)
					.run(args);
		}
	}

	@Override
	public KeyValue<String, Tuple> bzPopMin(final String[] keys, final int timeout) {
		final CommandArguments args = CommandArguments.create(keys).add(timeout);

		if(isPipeline()){
			return new LettuceSentinelPipelineCommand<KeyValue<String, Tuple>, KeyValue<String, Tuple>>(
					client, ProtocolCommand.BZPOPMIN)
					.run(args);
		}else if(isTransaction()){
			return new LettuceSentinelTransactionCommand<KeyValue<String, Tuple>, KeyValue<String, Tuple>>(
					client, ProtocolCommand.BZPOPMIN)
					.run(args);
		}else{
			return new LettuceSentinelCommand<KeyValue<String, Tuple>, KeyValue<String, Tuple>>(
					client, ProtocolCommand.BZPOPMIN)
					.run(args);
		}
	}

	@Override
	public KeyValue<byte[], Tuple> bzPopMin(final byte[][] keys, final int timeout) {
		final CommandArguments args = CommandArguments.create(keys).add(timeout);

		if(isPipeline()){
			return new LettuceSentinelPipelineCommand<KeyValue<byte[], Tuple>, KeyValue<byte[], Tuple>>(
					client, ProtocolCommand.BZPOPMIN)
					.run(args);
		}else if(isTransaction()){
			return new LettuceSentinelTransactionCommand<KeyValue<byte[], Tuple>, KeyValue<byte[], Tuple>>(
					client, ProtocolCommand.BZPOPMIN)
					.run(args);
		}else{
			return new LettuceSentinelCommand<KeyValue<byte[], Tuple>, KeyValue<byte[], Tuple>>(
					client, ProtocolCommand.BZPOPMIN)
					.run(args);
		}
	}

	@Override
	public KeyValue<String, Tuple> bzPopMax(final String[] keys, final int timeout) {
		final CommandArguments args = CommandArguments.create(keys).add(timeout);

		if(isPipeline()){
			return new LettuceSentinelPipelineCommand<KeyValue<String, Tuple>, KeyValue<String, Tuple>>(
					client, ProtocolCommand.BZPOPMAX)
					.run(args);
		}else if(isTransaction()){
			return new LettuceSentinelTransactionCommand<KeyValue<String, Tuple>, KeyValue<String, Tuple>>(
					client, ProtocolCommand.BZPOPMAX)
					.run(args);
		}else{
			return new LettuceSentinelCommand<KeyValue<String, Tuple>, KeyValue<String, Tuple>>(
					client, ProtocolCommand.BZPOPMAX)
					.run(args);
		}
	}

	@Override
	public KeyValue<byte[], Tuple> bzPopMax(final byte[][] keys, final int timeout) {
		final CommandArguments args = CommandArguments.create(keys).add(timeout);

		if(isPipeline()){
			return new LettuceSentinelPipelineCommand<KeyValue<byte[], Tuple>, KeyValue<byte[], Tuple>>(
					client, ProtocolCommand.BZPOPMAX)
					.run(args);
		}else if(isTransaction()){
			return new LettuceSentinelTransactionCommand<KeyValue<byte[], Tuple>, KeyValue<byte[], Tuple>>(
					client, ProtocolCommand.BZPOPMAX)
					.run(args);
		}else{
			return new LettuceSentinelCommand<KeyValue<byte[], Tuple>, KeyValue<byte[], Tuple>>(
					client, ProtocolCommand.BZPOPMAX)
					.run(args);
		}
	}

	@Override
	public Long zAdd(final String key, final Map<String, Double> members) {
		final CommandArguments args = CommandArguments.create(key).add(members);
		return zAdd(key, members, args);
	}

	@Override
	public Long zAdd(final byte[] key, final Map<byte[], Double> members) {
		final CommandArguments args = CommandArguments.create(key).add(members);
		return zAdd(key, members, args);
	}

	@Override
	public Long zAdd(final String key, final Map<String, Double> members, final NxXx nxXx) {
		final CommandArguments args = CommandArguments.create(key).add(members).add(nxXx);
		final ZAddArgs zAddArgs = new LettuceZAddArgs(nxXx);

		return zAdd(key, members, zAddArgs, args);
	}

	@Override
	public Long zAdd(final byte[] key, final Map<byte[], Double> members, final NxXx nxXx) {
		final CommandArguments args = CommandArguments.create(key).add(members).add(nxXx);
		final ZAddArgs zAddArgs = new LettuceZAddArgs(nxXx);

		return zAdd(key, members, zAddArgs, args);
	}

	@Override
	public Long zAdd(final String key, final Map<String, Double> members, final GtLt gtLt) {
		final CommandArguments args = CommandArguments.create(key).add(members).add(gtLt);
		final ZAddArgs zAddArgs = new LettuceZAddArgs(gtLt);

		return zAdd(key, members, zAddArgs, args);
	}

	@Override
	public Long zAdd(final byte[] key, final Map<byte[], Double> members, final GtLt gtLt) {
		final CommandArguments args = CommandArguments.create(key).add(members).add(gtLt);
		final ZAddArgs zAddArgs = new LettuceZAddArgs(gtLt);

		return zAdd(key, members, zAddArgs, args);
	}

	@Override
	public Long zAdd(final String key, final Map<String, Double> members, final boolean ch) {
		final CommandArguments args = CommandArguments.create(key).add(members).add(ch);
		final ZAddArgs zAddArgs = new LettuceZAddArgs(ch);

		return zAdd(key, members, zAddArgs, args);
	}

	@Override
	public Long zAdd(final byte[] key, final Map<byte[], Double> members, final boolean ch) {
		final CommandArguments args = CommandArguments.create(key).add(members).add(ch);
		final ZAddArgs zAddArgs = new LettuceZAddArgs(ch);

		return zAdd(key, members, zAddArgs, args);
	}

	@Override
	public Long zAdd(final String key, final Map<String, Double> members, final NxXx nxXx, final GtLt gtLt) {
		final CommandArguments args = CommandArguments.create(key).add(members).add(nxXx)
				.add(gtLt);
		final ZAddArgs zAddArgs = new LettuceZAddArgs(nxXx, gtLt);

		return zAdd(key, members, zAddArgs, args);
	}

	@Override
	public Long zAdd(final byte[] key, final Map<byte[], Double> members, final NxXx nxXx, final GtLt gtLt) {
		final CommandArguments args = CommandArguments.create(key).add(members).add(nxXx)
				.add(gtLt);
		final ZAddArgs zAddArgs = new LettuceZAddArgs(nxXx, gtLt);

		return zAdd(key, members, zAddArgs, args);
	}

	@Override
	public Long zAdd(final String key, final Map<String, Double> members, final NxXx nxXx, final boolean ch) {
		final CommandArguments args = CommandArguments.create(key).add(members).add(nxXx)
				.add(ch);
		final ZAddArgs zAddArgs = new LettuceZAddArgs(nxXx, ch);

		return zAdd(key, members, zAddArgs, args);
	}

	@Override
	public Long zAdd(final byte[] key, final Map<byte[], Double> members, final NxXx nxXx, final boolean ch) {
		final CommandArguments args = CommandArguments.create(key).add(members).add(nxXx)
				.add(ch);
		final ZAddArgs zAddArgs = new LettuceZAddArgs(nxXx, ch);

		return zAdd(key, members, zAddArgs, args);
	}

	@Override
	public Long zAdd(final String key, final Map<String, Double> members, final GtLt gtLt, final boolean ch) {
		final CommandArguments args = CommandArguments.create(key).add(members).add(gtLt)
				.add(ch);
		final ZAddArgs zAddArgs = new LettuceZAddArgs(gtLt, ch);

		return zAdd(key, members, zAddArgs, args);
	}

	@Override
	public Long zAdd(final byte[] key, final Map<byte[], Double> members, final GtLt gtLt, final boolean ch) {
		final CommandArguments args = CommandArguments.create(key).add(members).add(gtLt)
				.add(ch);
		final ZAddArgs zAddArgs = new LettuceZAddArgs(gtLt, ch);

		return zAdd(key, members, zAddArgs, args);
	}

	@Override
	public Long zAdd(final String key, final Map<String, Double> members, final NxXx nxXx, final GtLt gtLt,
					 final boolean ch) {
		final CommandArguments args = CommandArguments.create(key).add(members).add(nxXx)
				.add(gtLt).add(ch);
		final ZAddArgs zAddArgs = new LettuceZAddArgs(nxXx, gtLt, ch);

		return zAdd(key, members, zAddArgs, args);
	}

	@Override
	public Long zAdd(final byte[] key, final Map<byte[], Double> members, final NxXx nxXx, final GtLt gtLt,
					 final boolean ch) {
		final CommandArguments args = CommandArguments.create(key).add(members).add(nxXx)
				.add(gtLt).add(ch);
		final ZAddArgs zAddArgs = new LettuceZAddArgs(nxXx, gtLt, ch);

		return zAdd(key, members, zAddArgs, args);
	}

	@Override
	public Long zCard(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);

		if(isPipeline()){
			return new LettuceSentinelPipelineCommand<Long, Long>(client, ProtocolCommand.ZCARD)
					.run(args);
		}else if(isTransaction()){
			return new LettuceSentinelTransactionCommand<Long, Long>(client, ProtocolCommand.ZCARD)
					.run(args);
		}else{
			return new LettuceSentinelCommand<Long, Long>(client, ProtocolCommand.ZCARD)
					.run(args);
		}
	}

	@Override
	public Long zCount(final byte[] key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);
		final Range<Double> range = Range.create(min, max);

		if(isPipeline()){
			return new LettuceSentinelPipelineCommand<Long, Long>(client, ProtocolCommand.ZCOUNT)
					.run(args);
		}else if(isTransaction()){
			return new LettuceSentinelTransactionCommand<Long, Long>(client, ProtocolCommand.ZCOUNT)
					.run(args);
		}else{
			return new LettuceSentinelCommand<Long, Long>(client, ProtocolCommand.ZCOUNT)
					.run(args);
		}
	}

	@Override
	public List<String> zDiff(final String... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return zDiff(args);
	}

	@Override
	public List<byte[]> zDiff(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return zDiff(args);
	}

	@Override
	public List<Tuple> zDiffWithScores(final String... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return zDiff(args);
	}

	@Override
	public List<Tuple> zDiffWithScores(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return zDiff(args);
	}

	@Override
	public Long zDiffStore(final String destKey, final String... keys) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys);
		return zDiffStore(args);
	}

	@Override
	public Long zDiffStore(final byte[] destKey, final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys);
		return zDiffStore(args);
	}

	@Override
	public Double zIncrBy(final byte[] key, final double increment, final byte[] member) {
		final CommandArguments args = CommandArguments.create(key).add(increment)
				.add(member);

		if(isPipeline()){
			return new LettuceSentinelPipelineCommand<Double, Double>(client, ProtocolCommand.ZINCRBY)
					.run(args);
		}else if(isTransaction()){
			return new LettuceSentinelTransactionCommand<Double, Double>(client, ProtocolCommand.ZINCRBY)
					.run(args);
		}else{
			return new LettuceSentinelCommand<Double, Double>(client, ProtocolCommand.ZINCRBY)
					.run(args);
		}
	}

	@Override
	public List<String> zInter(final String... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return zInter(args);
	}

	@Override
	public List<byte[]> zInter(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return zInter(args);
	}

	@Override
	public List<String> zInter(final String[] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create(keys).add(aggregate);
		return zInter(args);
	}

	@Override
	public List<byte[]> zInter(final byte[][] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create(keys).add(aggregate);
		return zInter(args);
	}

	@Override
	public List<String> zInter(final String[] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add(weights);
		return zInter(args);
	}

	@Override
	public List<byte[]> zInter(final byte[][] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add(weights);
		return zInter(args);
	}

	@Override
	public List<String> zInter(final String[] keys, final Aggregate aggregate, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add(aggregate)
				.add(weights);
		return zInter(args);
	}

	@Override
	public List<byte[]> zInter(final byte[][] keys, final Aggregate aggregate, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add(aggregate)
				.add(weights);
		return zInter(args);
	}

	@Override
	public List<Tuple> zInterWithScores(final String... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return zInter(args);
	}

	@Override
	public List<Tuple> zInterWithScores(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return zInter(args);
	}

	@Override
	public List<Tuple> zInterWithScores(final String[] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create(keys).add(aggregate);
		return zInter(args);
	}

	@Override
	public List<Tuple> zInterWithScores(final byte[][] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create(keys).add(aggregate);
		return zInter(args);
	}

	@Override
	public List<Tuple> zInterWithScores(final String[] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add(weights);
		return zInter(args);
	}

	@Override
	public List<Tuple> zInterWithScores(final byte[][] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add(weights);
		return zInter(args);
	}

	@Override
	public List<Tuple> zInterWithScores(final String[] keys, final Aggregate aggregate, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add(aggregate)
				.add(weights);
		return zInter(args);
	}

	@Override
	public List<Tuple> zInterWithScores(final byte[][] keys, final Aggregate aggregate, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add(aggregate)
				.add(weights);
		return zInter(args);
	}

	@Override
	public Long zInterStore(final byte[] destKey, final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys);

		if(isPipeline()){
			return new LettuceSentinelPipelineCommand<Long, Long>(client, ProtocolCommand.ZINTERSTORE)
					.run(args);
		}else if(isTransaction()){
			return new LettuceSentinelTransactionCommand<Long, Long>(client, ProtocolCommand.ZINTERSTORE)
					.run(args);
		}else{
			return new LettuceSentinelCommand<Long, Long>(client, ProtocolCommand.ZINTERSTORE)
					.run(args);
		}
	}

	@Override
	public Long zInterStore(final byte[] destKey, final byte[][] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys)
				.add(aggregate);
		final ZStoreArgs zStoreArgs = new LettuceZStoreArgs(aggregate);

		return zInterStore(destKey, keys, zStoreArgs, args);
	}

	@Override
	public Long zInterStore(final byte[] destKey, final byte[][] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys)
				.add(weights);
		final ZStoreArgs zStoreArgs = new LettuceZStoreArgs(weights);

		return zInterStore(destKey, keys, zStoreArgs, args);
	}

	@Override
	public Long zInterStore(final byte[] destKey, final byte[][] keys, final Aggregate aggregate,
							final double... weights) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys)
				.add(aggregate).add(weights);
		final ZStoreArgs zStoreArgs = new LettuceZStoreArgs(aggregate, weights);

		return zInterStore(destKey, keys, zStoreArgs, args);
	}

	@Deprecated
	@Override
	public Long zLexCount(final byte[] key, final byte[] min, final byte[] max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);
		final Range<byte[]> range = Range.create(min, max);

		if(isPipeline()){
			return new LettuceSentinelPipelineCommand<Long, Long>(client, ProtocolCommand.ZLEXCOUNT)
					.run(args);
		}else if(isTransaction()){
			return new LettuceSentinelTransactionCommand<Long, Long>(client, ProtocolCommand.ZLEXCOUNT)
					.run(args);
		}else{
			return new LettuceSentinelCommand<Long, Long>(client, ProtocolCommand.ZLEXCOUNT)
					.run(args);
		}
	}

	@Override
	public List<Double> zMScore(final String key, final String... members) {
		final CommandArguments args = CommandArguments.create(key).add(members);
		return zMScore(args);
	}

	@Override
	public List<Double> zMScore(final byte[] key, final byte[]... members) {
		final CommandArguments args = CommandArguments.create(key).add(members);
		return zMScore(args);
	}

	@Override
	public String zRandMember(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return zRandMember(args);
	}

	@Override
	public byte[] zRandMember(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return zRandMember(args);
	}

	@Override
	public List<String> zRandMember(final String key, final long count) {
		final CommandArguments args = CommandArguments.create(key).add(count);
		return zRandMember(args);
	}

	@Override
	public List<byte[]> zRandMember(final byte[] key, final long count) {
		final CommandArguments args = CommandArguments.create(key).add(count);
		return zRandMember(args);
	}

	@Override
	public List<Tuple> zRandMemberWithScores(final String key, final long count) {
		final CommandArguments args = CommandArguments.create(key).add(count);
		return zRandMember(args);
	}

	@Override
	public List<Tuple> zRandMemberWithScores(final byte[] key, final long count) {
		final CommandArguments args = CommandArguments.create(key).add(count);
		return zRandMember(args);
	}

	@Override
	public List<String> zRange(final String key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end);
		final byte[] bKey = SafeEncoder.encode(key);

		return zRange(bKey, start, end, binaryToStringListConverter, args);
	}

	@Override
	public List<byte[]> zRange(final byte[] key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end);
		return zRange(key, start, end, (v)->v, args);
	}

	@Override
	public List<Tuple> zRangeWithScores(final byte[] key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end);

		if(isPipeline()){
			return new LettuceSentinelPipelineCommand<List<Tuple>, List<Tuple>>(client, ProtocolCommand.ZRANGE)
					.run(args);
		}else if(isTransaction()){
			return new LettuceSentinelTransactionCommand<List<Tuple>, List<Tuple>>(client, ProtocolCommand.ZRANGE)
					.run(args);
		}else{
			return new LettuceSentinelCommand<List<Tuple>, List<Tuple>>(client, ProtocolCommand.ZRANGE)
					.run(args);
		}
	}

	@Override
	public List<String> zRangeByLex(final String key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);
		final byte[] bKey = SafeEncoder.encode(key);
		final byte[] bMin = NumberUtils.double2bytes(min);
		final byte[] bMax = NumberUtils.double2bytes(max);

		return zRangeByLex(bKey, bMin, bMax, binaryToStringListConverter, args);
	}

	@Override
	public List<byte[]> zRangeByLex(final byte[] key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);
		final byte[] bMin = NumberUtils.double2bytes(min);
		final byte[] bMax = NumberUtils.double2bytes(max);

		return zRangeByLex(key, bMin, bMax, (v)->v, args);
	}

	@Deprecated
	@Override
	public List<String> zRangeByLex(final String key, final String min, final String max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);
		final byte[] bKey = SafeEncoder.encode(key);
		final byte[] bMin = SafeEncoder.encode(min);
		final byte[] bMax = SafeEncoder.encode(max);

		return zRangeByLex(bKey, bMin, bMax, binaryToStringListConverter, args);
	}

	@Deprecated
	@Override
	public List<byte[]> zRangeByLex(final byte[] key, final byte[] min, final byte[] max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);
		return zRangeByLex(key, min, max, (v)->v, args);
	}

	@Override
	public List<String> zRangeByLex(final String key, final double min, final double max, final long offset,
									final long count) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max)
				.add(offset).add(count);
		final byte[] bKey = SafeEncoder.encode(key);
		final byte[] bMin = NumberUtils.double2bytes(min);
		final byte[] bMax = NumberUtils.double2bytes(max);

		return zRangeByLex(bKey, bMin, bMax, offset, count, binaryToStringListConverter, args);
	}

	@Override
	public List<byte[]> zRangeByLex(final byte[] key, final double min, final double max, final long offset,
									final long count) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max)
				.add(offset).add(count);
		final byte[] bMin = NumberUtils.double2bytes(min);
		final byte[] bMax = NumberUtils.double2bytes(max);

		return zRangeByLex(key, bMin, bMax, offset, count, (v)->v, args);
	}

	@Deprecated
	@Override
	public List<String> zRangeByLex(final String key, final String min, final String max, final long offset,
									final long count) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max)
				.add(offset).add(count);
		final byte[] bKey = SafeEncoder.encode(key);
		final byte[] bMin = SafeEncoder.encode(min);
		final byte[] bMax = SafeEncoder.encode(max);

		return zRangeByLex(bKey, bMin, bMax, offset, count, binaryToStringListConverter, args);
	}

	@Deprecated
	@Override
	public List<byte[]> zRangeByLex(final byte[] key, final byte[] min, final byte[] max, final long offset,
									final long count) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max)
				.add(offset).add(count);
		return zRangeByLex(key, min, max, offset, count, (v)->v, args);
	}

	@Override
	public List<String> zRangeByScore(final String key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);
		final byte[] bKey = SafeEncoder.encode(key);

		return zRangeByScore(bKey, min, max, binaryToStringListConverter, args);
	}

	@Override
	public List<byte[]> zRangeByScore(final byte[] key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);
		return zRangeByScore(key, min, max, (v)->v, args);
	}

	@Override
	public List<String> zRangeByScore(final String key, final double min, final double max, final long offset,
									  final long count) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max)
				.add(offset).add(count);
		final byte[] bKey = SafeEncoder.encode(key);

		return zRangeByScore(bKey, min, max, offset, count, binaryToStringListConverter, args);
	}

	@Override
	public List<byte[]> zRangeByScore(final byte[] key, final double min, final double max, final long offset,
									  final long count) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max)
				.add(offset).add(count);
		return zRangeByScore(key, min, max, offset, count, (v)->v, args);
	}

	@Override
	public List<Tuple> zRangeByScoreWithScores(final byte[] key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);

		if(isPipeline()){
			return new LettuceSentinelPipelineCommand<List<Tuple>, List<Tuple>>(client, ProtocolCommand.ZRANGEBYSCORE)
					.run(args);
		}else if(isTransaction()){
			return new LettuceSentinelTransactionCommand<List<Tuple>, List<Tuple>>(client,
					ProtocolCommand.ZRANGEBYSCORE)
					.run(args);
		}else{
			return new LettuceSentinelCommand<List<Tuple>, List<Tuple>>(client, ProtocolCommand.ZRANGEBYSCORE)
					.run(args);
		}
	}

	@Override
	public List<Tuple> zRangeByScoreWithScores(final byte[] key, final double min, final double max, final long offset,
											   final long count) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max)
				.add(offset).add(count);

		if(isPipeline()){
			return new LettuceSentinelPipelineCommand<List<Tuple>, List<Tuple>>(client, ProtocolCommand.ZRANGEBYSCORE)
					.run(args);
		}else if(isTransaction()){
			return new LettuceSentinelTransactionCommand<List<Tuple>, List<Tuple>>(client,
					ProtocolCommand.ZRANGEBYSCORE)
					.run(args);
		}else{
			return new LettuceSentinelCommand<List<Tuple>, List<Tuple>>(client, ProtocolCommand.ZRANGEBYSCORE)
					.run(args);
		}
	}

	@Override
	public Long zRangeStore(final String destKey, final String key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(destKey).add(key).add(start)
				.add(end);
		return zRangeStore(args);
	}

	@Override
	public Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(destKey).add(key).add(start)
				.add(end);
		return zRangeStore(args);
	}

	@Override
	public Long zRangeStore(final String destKey, final String key, final long start, final long end,
							final ZRangeBy by) {
		final CommandArguments args = CommandArguments.create(destKey).add(key).add(start)
				.add(end).add(by);
		return zRangeStore(args);
	}

	@Override
	public Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end,
							final ZRangeBy by) {
		final CommandArguments args = CommandArguments.create(destKey).add(key).add(start)
				.add(end).add(by);
		return zRangeStore(args);
	}

	@Override
	public Long zRangeStore(final String destKey, final String key, final long start, final long end,
							final boolean rev) {
		final CommandArguments args = CommandArguments.create(destKey).add(key).add(start)
				.add(end).add(rev);
		return zRangeStore(args);
	}

	@Override
	public Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end,
							final boolean rev) {
		final CommandArguments args = CommandArguments.create(destKey).add(key).add(start)
				.add(end).add(rev);
		return zRangeStore(args);
	}

	@Override
	public Long zRangeStore(final String destKey, final String key, final long start, final long end, final long offset,
							final long count) {
		final CommandArguments args = CommandArguments.create(destKey).add(key).add(start)
				.add(end).add(offset).add(count);
		return zRangeStore(args);
	}

	@Override
	public Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end, final long offset,
							final long count) {
		final CommandArguments args = CommandArguments.create(destKey).add(key).add(start)
				.add(end).add(offset).add(count);
		return zRangeStore(args);
	}

	@Override
	public Long zRangeStore(final String destKey, final String key, final long start, final long end,
							final ZRangeBy by, final boolean rev) {
		final CommandArguments args = CommandArguments.create(destKey).add(key).add(start)
				.add(end).add(by).add(rev);
		return zRangeStore(args);
	}

	@Override
	public Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end,
							final ZRangeBy by, final boolean rev) {
		final CommandArguments args = CommandArguments.create(destKey).add(key).add(start)
				.add(end).add(by).add(rev);
		return zRangeStore(args);
	}

	@Override
	public Long zRangeStore(final String destKey, final String key, final long start, final long end,
							final ZRangeBy by, final long offset, final long count) {
		final CommandArguments args = CommandArguments.create(destKey).add(key).add(start)
				.add(end).add(by).add(offset).add(count);
		return zRangeStore(args);
	}

	@Override
	public Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end,
							final ZRangeBy by, final long offset, final long count) {
		final CommandArguments args = CommandArguments.create(destKey).add(key).add(start)
				.add(end).add(by).add(offset).add(count);
		return zRangeStore(args);
	}

	@Override
	public Long zRangeStore(final String destKey, final String key, final long start, final long end, final boolean rev,
							final long offset, final long count) {
		final CommandArguments args = CommandArguments.create(destKey).add(key).add(start)
				.add(end).add(rev).add(offset).add(count);
		return zRangeStore(args);
	}

	@Override
	public Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end, final boolean rev,
							final long offset, final long count) {
		final CommandArguments args = CommandArguments.create(destKey).add(key).add(start)
				.add(end).add(rev).add(offset).add(count);
		return zRangeStore(args);
	}

	@Override
	public Long zRangeStore(final String destKey, final String key, final long start, final long end, final ZRangeBy by,
							final boolean rev, final long offset, final long count) {
		final CommandArguments args = CommandArguments.create(destKey).add(key).add(start)
				.add(end).add(by).add(rev).add(offset).add(count);
		return zRangeStore(args);
	}

	@Override
	public Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end, final ZRangeBy by,
							final boolean rev, final long offset, final long count) {
		final CommandArguments args = CommandArguments.create(destKey).add(key).add(start)
				.add(end).add(by).add(rev).add(offset).add(count);
		return zRangeStore(args);
	}

	@Override
	public Long zRank(final byte[] key, final byte[] member) {
		final CommandArguments args = CommandArguments.create(key).add(member);

		if(isPipeline()){
			return new LettuceSentinelPipelineCommand<Long, Long>(client, ProtocolCommand.ZRANK)
					.run(args);
		}else if(isTransaction()){
			return new LettuceSentinelTransactionCommand<Long, Long>(client, ProtocolCommand.ZRANK)
					.run(args);
		}else{
			return new LettuceSentinelCommand<Long, Long>(client, ProtocolCommand.ZRANK)
					.run(args);
		}
	}

	@Override
	public Long zRem(final byte[] key, final byte[]... members) {
		final CommandArguments args = CommandArguments.create(key).add(members);

		if(isPipeline()){
			return new LettuceSentinelPipelineCommand<Long, Long>(client, ProtocolCommand.ZREM)
					.run(args);
		}else if(isTransaction()){
			return new LettuceSentinelTransactionCommand<Long, Long>(client, ProtocolCommand.ZREM)
					.run(args);
		}else{
			return new LettuceSentinelCommand<Long, Long>(client, ProtocolCommand.ZREM)
					.run(args);
		}
	}

	@Deprecated
	@Override
	public Long zRemRangeByLex(final byte[] key, final byte[] min, final byte[] max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);
		final Range<byte[]> range = Range.create(min, max);

		if(isPipeline()){
			return new LettuceSentinelPipelineCommand<Long, Long>(client, ProtocolCommand.ZREMRANGEBYLEX)
					.run(args);
		}else if(isTransaction()){
			return new LettuceSentinelTransactionCommand<Long, Long>(client, ProtocolCommand.ZREMRANGEBYLEX)
					.run(args);
		}else{
			return new LettuceSentinelCommand<Long, Long>(client, ProtocolCommand.ZREMRANGEBYLEX)
					.run(args);
		}
	}

	@Override
	public Long zRemRangeByScore(final byte[] key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);
		final Range<Double> range = Range.create(min, max);

		if(isPipeline()){
			return new LettuceSentinelPipelineCommand<Long, Long>(client, ProtocolCommand.ZREMRANGEBYSCORE)
					.run(args);
		}else if(isTransaction()){
			return new LettuceSentinelTransactionCommand<Long, Long>(client, ProtocolCommand.ZREMRANGEBYSCORE)
					.run(args);
		}else{
			return new LettuceSentinelCommand<Long, Long>(client, ProtocolCommand.ZREMRANGEBYSCORE)
					.run(args);
		}
	}

	@Override
	public Long zRemRangeByRank(final byte[] key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end);

		if(isPipeline()){
			return new LettuceSentinelPipelineCommand<Long, Long>(client, ProtocolCommand.ZREMRANGEBYRANK)
					.run(args);
		}else if(isTransaction()){
			return new LettuceSentinelTransactionCommand<Long, Long>(client, ProtocolCommand.ZREMRANGEBYRANK)
					.run(args);
		}else{
			return new LettuceSentinelCommand<Long, Long>(client, ProtocolCommand.ZREMRANGEBYRANK)
					.run(args);
		}
	}

	@Override
	public List<String> zRevRange(final String key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end);
		final byte[] bKey = SafeEncoder.encode(key);

		return zRevRange(bKey, start, end, binaryToStringListConverter, args);
	}

	@Override
	public List<byte[]> zRevRange(final byte[] key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end);
		return zRevRange(key, start, end, (v)->v, args);
	}

	@Override
	public List<Tuple> zRevRangeWithScores(final byte[] key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end);

		if(isPipeline()){
			return new LettuceSentinelPipelineCommand<List<Tuple>, List<Tuple>>(client, ProtocolCommand.ZREVRANGE)
					.run(args);
		}else if(isTransaction()){
			return new LettuceSentinelTransactionCommand<List<Tuple>, List<Tuple>>(client, ProtocolCommand.ZREVRANGE)
					.run(args);
		}else{
			return new LettuceSentinelCommand<List<Tuple>, List<Tuple>>(client, ProtocolCommand.ZREVRANGE)
					.run(args);
		}
	}

	@Override
	public List<String> zRevRangeByLex(final String key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);
		final byte[] bKey = SafeEncoder.encode(key);
		final byte[] bMin = NumberUtils.double2bytes(min);
		final byte[] bMax = NumberUtils.double2bytes(max);

		return zRevRangeByLex(bKey, bMin, bMax, binaryToStringListConverter, args);
	}

	@Override
	public List<byte[]> zRevRangeByLex(final byte[] key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);
		final byte[] bMin = NumberUtils.double2bytes(min);
		final byte[] bMax = NumberUtils.double2bytes(max);

		return zRevRangeByLex(key, bMin, bMax, (v)->v, args);
	}

	@Deprecated
	@Override
	public List<String> zRevRangeByLex(final String key, final String min, final String max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);
		final byte[] bKey = SafeEncoder.encode(key);
		final byte[] bMin = SafeEncoder.encode(min);
		final byte[] bMax = SafeEncoder.encode(max);

		return zRevRangeByLex(bKey, bMin, bMax, binaryToStringListConverter, args);
	}

	@Deprecated
	@Override
	public List<byte[]> zRevRangeByLex(final byte[] key, final byte[] min, final byte[] max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);
		return zRevRangeByLex(key, min, max, (v)->v, args);
	}

	@Override
	public List<String> zRevRangeByLex(final String key, final double min, final double max, final long offset,
									   final long count) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max).add(count);
		final byte[] bKey = SafeEncoder.encode(key);
		final byte[] bMin = NumberUtils.double2bytes(min);
		final byte[] bMax = NumberUtils.double2bytes(max);

		return zRevRangeByLex(bKey, bMin, bMax, offset, count, binaryToStringListConverter, args);
	}

	@Override
	public List<byte[]> zRevRangeByLex(final byte[] key, final double min, final double max, final long offset,
									   final long count) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max).add(count);
		final byte[] bMin = NumberUtils.double2bytes(min);
		final byte[] bMax = NumberUtils.double2bytes(max);

		return zRevRangeByLex(key, bMin, bMax, offset, count, (v)->v, args);
	}

	@Deprecated
	@Override
	public List<String> zRevRangeByLex(final String key, final String min, final String max, final long offset,
									   final long count) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max).add(count);
		final byte[] bKey = SafeEncoder.encode(key);
		final byte[] bMin = SafeEncoder.encode(min);
		final byte[] bMax = SafeEncoder.encode(max);

		return zRevRangeByLex(bKey, bMin, bMax, offset, count, binaryToStringListConverter, args);
	}

	@Deprecated
	@Override
	public List<byte[]> zRevRangeByLex(final byte[] key, final byte[] min, final byte[] max, final long offset,
									   final long count) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max).add(count);
		return zRevRangeByLex(key, min, max, offset, count, (v)->v, args);
	}

	@Override
	public List<String> zRevRangeByScore(final String key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);
		final byte[] bKey = SafeEncoder.encode(key);

		return zRevRangeByScore(bKey, min, max, binaryToStringListConverter, args);
	}

	@Override
	public List<byte[]> zRevRangeByScore(final byte[] key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);
		return zRevRangeByScore(key, min, max, (v)->v, args);
	}

	@Override
	public List<String> zRevRangeByScore(final String key, final double min, final double max, final long offset,
										 final long count) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max)
				.add(offset).add(count);
		final byte[] bKey = SafeEncoder.encode(key);

		return zRevRangeByScore(bKey, min, max, offset, count, binaryToStringListConverter, args);
	}

	@Override
	public List<byte[]> zRevRangeByScore(final byte[] key, final double min, final double max, final long offset,
										 final long count) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max)
				.add(offset).add(count);
		return zRevRangeByScore(key, min, max, offset, count, (v)->v, args);
	}

	@Override
	public List<Tuple> zRevRangeByScoreWithScores(final byte[] key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);

		if(isPipeline()){
			return new LettuceSentinelPipelineCommand<List<Tuple>, List<Tuple>>(client,
					ProtocolCommand.ZREVRANGEBYSCORE)
					.run(args);
		}else if(isTransaction()){
			return new LettuceSentinelTransactionCommand<List<Tuple>, List<Tuple>>(client,
					ProtocolCommand.ZREVRANGEBYSCORE)
					.run(args);
		}else{
			return new LettuceSentinelCommand<List<Tuple>, List<Tuple>>(client, ProtocolCommand.ZREVRANGEBYSCORE)
					.run(args);
		}
	}

	@Override
	public List<Tuple> zRevRangeByScoreWithScores(final byte[] key, final double min, final double max,
												  final long offset, final long count) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max)
				.add(offset).add(count);

		if(isPipeline()){
			return new LettuceSentinelPipelineCommand<List<Tuple>, List<Tuple>>(client,
					ProtocolCommand.ZREVRANGEBYSCORE)
					.run(args);
		}else if(isTransaction()){
			return new LettuceSentinelTransactionCommand<List<Tuple>, List<Tuple>>(client,
					ProtocolCommand.ZREVRANGEBYSCORE)
					.run(args);
		}else{
			return new LettuceSentinelCommand<List<Tuple>, List<Tuple>>(client, ProtocolCommand.ZREVRANGEBYSCORE)
					.run(args);
		}
	}

	@Override
	public Long zRevRank(final byte[] key, final byte[] member) {
		final CommandArguments args = CommandArguments.create(key).add(member);

		if(isPipeline()){
			return new LettuceSentinelPipelineCommand<Long, Long>(client, ProtocolCommand.ZREVRANK)
					.run(args);
		}else if(isTransaction()){
			return new LettuceSentinelTransactionCommand<Long, Long>(client, ProtocolCommand.ZREVRANK)
					.run(args);
		}else{
			return new LettuceSentinelCommand<Long, Long>(client, ProtocolCommand.ZREVRANK)
					.run(args);
		}
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final String cursor) {
		final CommandArguments args = CommandArguments.create(key).add(cursor);
		final byte[] bKey = SafeEncoder.encode(key);
		final ScanCursor scanCursor = new LettuceScanCursor(cursor);
		final ScanCursorConverter.ValueScanCursorConverter.ScoredValueScanCursorConverter scoredValueScanCursorConverter =
				new ScanCursorConverter.ValueScanCursorConverter.ScoredValueScanCursorConverter();

		return zScan(bKey, scanCursor, scoredValueScanCursorConverter, args);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor) {
		final CommandArguments args = CommandArguments.create(key).add(cursor);
		final ScanCursor scanCursor = new LettuceScanCursor(cursor);
		final ScanCursorConverter.ValueScanCursorConverter.ScoredValueScanCursorConverter scoredValueScanCursorConverter =
				new ScanCursorConverter.ValueScanCursorConverter.ScoredValueScanCursorConverter();

		return zScan(key, scanCursor, scoredValueScanCursorConverter, args);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final String cursor, final String pattern) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(pattern);
		final byte[] bKey = SafeEncoder.encode(key);
		final ScanCursor scanCursor = new LettuceScanCursor(cursor);
		final ScanArgs scanArgs = new LettuceScanArgs(pattern);
		final ScanCursorConverter.ValueScanCursorConverter.ScoredValueScanCursorConverter scoredValueScanCursorConverter =
				new ScanCursorConverter.ValueScanCursorConverter.ScoredValueScanCursorConverter();

		return zScan(bKey, scanCursor, scanArgs, scoredValueScanCursorConverter, args);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor, final byte[] pattern) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(pattern);
		final ScanCursor scanCursor = new LettuceScanCursor(cursor);
		final ScanArgs scanArgs = new LettuceScanArgs(pattern);
		final ScanCursorConverter.ValueScanCursorConverter.ScoredValueScanCursorConverter scoredValueScanCursorConverter =
				new ScanCursorConverter.ValueScanCursorConverter.ScoredValueScanCursorConverter();

		return zScan(key, scanCursor, scanArgs, scoredValueScanCursorConverter, args);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final String cursor, final long count) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(count);
		final byte[] bKey = SafeEncoder.encode(key);
		final ScanCursor scanCursor = new LettuceScanCursor(cursor);
		final ScanArgs scanArgs = new LettuceScanArgs(count);
		final ScanCursorConverter.ValueScanCursorConverter.ScoredValueScanCursorConverter scoredValueScanCursorConverter =
				new ScanCursorConverter.ValueScanCursorConverter.ScoredValueScanCursorConverter();

		return zScan(bKey, scanCursor, scanArgs, scoredValueScanCursorConverter, args);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor, final long count) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(count);
		final ScanCursor scanCursor = new LettuceScanCursor(cursor);
		final ScanArgs scanArgs = new LettuceScanArgs(count);
		final ScanCursorConverter.ValueScanCursorConverter.ScoredValueScanCursorConverter scoredValueScanCursorConverter =
				new ScanCursorConverter.ValueScanCursorConverter.ScoredValueScanCursorConverter();

		return zScan(key, scanCursor, scanArgs, scoredValueScanCursorConverter, args);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final String cursor, final String pattern,
										 final long count) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(pattern)
				.add(count);
		final byte[] bKey = SafeEncoder.encode(key);
		final ScanCursor scanCursor = new LettuceScanCursor(cursor);
		final ScanArgs scanArgs = new LettuceScanArgs(pattern, count);
		final ScanCursorConverter.ValueScanCursorConverter.ScoredValueScanCursorConverter scoredValueScanCursorConverter =
				new ScanCursorConverter.ValueScanCursorConverter.ScoredValueScanCursorConverter();

		return zScan(bKey, scanCursor, scanArgs, scoredValueScanCursorConverter, args);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor, final byte[] pattern,
										 final long count) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(pattern)
				.add(count);
		final ScanCursor scanCursor = new LettuceScanCursor(cursor);
		final ScanArgs scanArgs = new LettuceScanArgs(pattern, count);
		final ScanCursorConverter.ValueScanCursorConverter.ScoredValueScanCursorConverter scoredValueScanCursorConverter =
				new ScanCursorConverter.ValueScanCursorConverter.ScoredValueScanCursorConverter();

		return zScan(key, scanCursor, scanArgs, scoredValueScanCursorConverter, args);
	}

	@Override
	public Double zScore(final byte[] key, final byte[] member) {
		final CommandArguments args = CommandArguments.create(key).add(member);

		if(isPipeline()){
			return new LettuceSentinelPipelineCommand<Double, Double>(client, ProtocolCommand.ZSCORE)
					.run(args);
		}else if(isTransaction()){
			return new LettuceSentinelTransactionCommand<Double, Double>(client, ProtocolCommand.ZSCORE)
					.run(args);
		}else{
			return new LettuceSentinelCommand<Double, Double>(client, ProtocolCommand.ZSCORE)
					.run(args);
		}
	}

	@Override
	public List<String> zUnion(final String... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return zUnion(args);
	}

	@Override
	public List<byte[]> zUnion(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return zUnion(args);
	}

	@Override
	public List<String> zUnion(final String[] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create(keys).add(aggregate);
		return zUnion(args);
	}

	@Override
	public List<byte[]> zUnion(final byte[][] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create(keys).add(aggregate);
		return zUnion(args);
	}

	@Override
	public List<String> zUnion(final String[] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add(weights);
		return zUnion(args);
	}

	@Override
	public List<byte[]> zUnion(final byte[][] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add(weights);
		return zUnion(args);
	}

	@Override
	public List<String> zUnion(final String[] keys, final Aggregate aggregate, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add(aggregate)
				.add(weights);
		return zUnion(args);
	}

	@Override
	public List<byte[]> zUnion(final byte[][] keys, final Aggregate aggregate, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add(aggregate)
				.add(weights);
		return zUnion(args);
	}

	@Override
	public List<Tuple> zUnionWithScores(final String... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return zUnion(args);
	}

	@Override
	public List<Tuple> zUnionWithScores(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return zUnion(args);
	}

	@Override
	public List<Tuple> zUnionWithScores(final String[] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create(keys).add(aggregate);
		return zUnion(args);
	}

	@Override
	public List<Tuple> zUnionWithScores(final byte[][] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create(keys).add(aggregate);
		return zUnion(args);
	}

	@Override
	public List<Tuple> zUnionWithScores(final String[] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add(weights);
		return zUnion(args);
	}

	@Override
	public List<Tuple> zUnionWithScores(final byte[][] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add(weights);
		return zUnion(args);
	}

	@Override
	public List<Tuple> zUnionWithScores(final String[] keys, final Aggregate aggregate, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add(aggregate)
				.add(weights);
		return zUnion(args);
	}

	@Override
	public List<Tuple> zUnionWithScores(final byte[][] keys, final Aggregate aggregate, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add(aggregate)
				.add(weights);
		return zUnion(args);
	}

	@Override
	public Long zUnionStore(final byte[] destKey, final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys);

		if(isPipeline()){
			return new LettuceSentinelPipelineCommand<Long, Long>(client, ProtocolCommand.ZUNIONSTORE)
					.run(args);
		}else if(isTransaction()){
			return new LettuceSentinelTransactionCommand<Long, Long>(client, ProtocolCommand.ZUNIONSTORE)
					.run(args);
		}else{
			return new LettuceSentinelCommand<Long, Long>(client, ProtocolCommand.ZUNIONSTORE)
					.run(args);
		}
	}

	@Override
	public Long zUnionStore(final byte[] destKey, final byte[][] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys)
				.add(aggregate);
		final ZStoreArgs zStoreArgs = new LettuceZStoreArgs(aggregate);

		return zUnionStore(destKey, keys, zStoreArgs, args);
	}

	@Override
	public Long zUnionStore(final byte[] destKey, final byte[][] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys)
				.add(weights);
		final ZStoreArgs zStoreArgs = new LettuceZStoreArgs(weights);

		return zUnionStore(destKey, keys, zStoreArgs, args);
	}

	@Override
	public Long zUnionStore(final byte[] destKey, final byte[][] keys, final Aggregate aggregate,
							final double... weights) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys)
				.add(aggregate).add(weights);
		final ZStoreArgs zStoreArgs = new LettuceZStoreArgs(aggregate, weights);

		return zUnionStore(destKey, keys, zStoreArgs, args);
	}

	private Long zAdd(final String key, final Map<String, Double> members, final CommandArguments args) {
		final byte[] bKey = SafeEncoder.encode(key);
		final ScoredValue<byte[]>[] scoredValues = new ScoredValue[members.size()];
		int i = 0;

		for(Map.Entry<String, Double> e : members.entrySet()){
			scoredValues[i++] = e.getValue() == null ? ScoredValue.empty() : ScoredValue.just(e.getValue(),
					SafeEncoder.encode(e.getKey()));
		}

		return zAdd(bKey, scoredValues, args);
	}

	private Long zAdd(final String key, final Map<String, Double> members, final ZAddArgs zAddArgs,
					  final CommandArguments args) {
		final byte[] bKey = SafeEncoder.encode(key);
		final ScoredValue<byte[]>[] scoredValues = new ScoredValue[members.size()];
		int i = 0;

		for(Map.Entry<String, Double> e : members.entrySet()){
			scoredValues[i++] = e.getValue() == null ? ScoredValue.empty() : ScoredValue.just(e.getValue(),
					SafeEncoder.encode(e.getKey()));
		}

		return zAdd(bKey, scoredValues, zAddArgs, args);
	}

	private Long zAdd(final byte[] key, final Map<byte[], Double> members, final CommandArguments args) {
		final ScoredValue<byte[]>[] scoredValues = new ScoredValue[members.size()];
		int i = 0;

		for(Map.Entry<byte[], Double> e : members.entrySet()){
			scoredValues[i++] = e.getValue() == null ? ScoredValue.empty() : ScoredValue.just(e.getValue(),
					e.getKey());
		}

		return zAdd(key, scoredValues, args);
	}

	private Long zAdd(final byte[] key, final Map<byte[], Double> members, final ZAddArgs zAddArgs,
					  final CommandArguments args) {
		final ScoredValue<byte[]>[] scoredValues = new ScoredValue[members.size()];
		int i = 0;

		for(Map.Entry<byte[], Double> e : members.entrySet()){
			scoredValues[i++] = e.getValue() == null ? ScoredValue.empty() : ScoredValue.just(e.getValue(),
					e.getKey());
		}

		return zAdd(key, scoredValues, zAddArgs, args);
	}

	private Long zAdd(final byte[] key, final ScoredValue<byte[]>[] scoredValues, final CommandArguments args) {
		if(isPipeline()){
			return new LettuceSentinelPipelineCommand<Long, Long>(client, ProtocolCommand.ZADD)
					.run(args);
		}else if(isTransaction()){
			return new LettuceSentinelTransactionCommand<Long, Long>(client, ProtocolCommand.ZADD)
					.run(args);
		}else{
			return new LettuceSentinelCommand<Long, Long>(client, ProtocolCommand.ZADD)
					.run(args);
		}
	}

	private Long zAdd(final byte[] key, final ScoredValue<byte[]>[] scoredValues, final ZAddArgs zAddArgs,
					  final CommandArguments args) {
		if(isPipeline()){
			return new LettuceSentinelPipelineCommand<Long, Long>(client, ProtocolCommand.ZADD)
					.run(args);
		}else if(isTransaction()){
			return new LettuceSentinelTransactionCommand<Long, Long>(client, ProtocolCommand.ZADD)
					.run(args);
		}else{
			return new LettuceSentinelCommand<Long, Long>(client, ProtocolCommand.ZADD)
					.run(args);
		}
	}

	private <V> List<V> zDiff(final CommandArguments args) {
		if(isPipeline()){
			return new LettuceSentinelPipelineCommand<List<V>, List<V>>(client, ProtocolCommand.ZDIFF)
					.run(args);
		}else if(isTransaction()){
			return new LettuceSentinelTransactionCommand<List<V>, List<V>>(client, ProtocolCommand.ZDIFF)
					.run(args);
		}else{
			return new LettuceSentinelCommand<List<V>, List<V>>(client, ProtocolCommand.ZDIFF)
					.run(args);
		}
	}

	private Long zDiffStore(final CommandArguments args) {
		if(isPipeline()){
			return new LettuceSentinelPipelineCommand<Long, Long>(client, ProtocolCommand.ZDIFFSTORE)
					.run(args);
		}else if(isTransaction()){
			return new LettuceSentinelTransactionCommand<Long, Long>(client, ProtocolCommand.ZDIFFSTORE)
					.run(args);
		}else{
			return new LettuceSentinelCommand<Long, Long>(client, ProtocolCommand.ZDIFFSTORE)
					.run(args);
		}
	}

	private <V> List<V> zInter(final CommandArguments args) {
		if(isPipeline()){
			return new LettuceSentinelPipelineCommand<List<V>, List<V>>(client, ProtocolCommand.ZINTER)
					.run(args);
		}else if(isTransaction()){
			return new LettuceSentinelTransactionCommand<List<V>, List<V>>(client, ProtocolCommand.ZINTER)
					.run(args);
		}else{
			return new LettuceSentinelCommand<List<V>, List<V>>(client, ProtocolCommand.ZINTER)
					.run(args);
		}
	}

	private Long zInterStore(final byte[] destKey, final byte[][] keys, final ZStoreArgs zStoreArgs,
							 final CommandArguments args) {
		if(isPipeline()){
			return new LettuceSentinelPipelineCommand<Long, Long>(client, ProtocolCommand.ZINTERSTORE)
					.run(args);
		}else if(isTransaction()){
			return new LettuceSentinelTransactionCommand<Long, Long>(client, ProtocolCommand.ZINTERSTORE)
					.run(args);
		}else{
			return new LettuceSentinelCommand<Long, Long>(client, ProtocolCommand.ZINTERSTORE)
					.run(args);
		}
	}

	private List<Double> zMScore(final CommandArguments args) {
		if(isPipeline()){
			return new LettuceSentinelPipelineCommand<List<Double>, List<Double>>(client, ProtocolCommand.ZMSCORE)
					.run(args);
		}else if(isTransaction()){
			return new LettuceSentinelTransactionCommand<List<Double>, List<Double>>(client, ProtocolCommand.ZMSCORE)
					.run(args);
		}else{
			return new LettuceSentinelCommand<List<Double>, List<Double>>(client, ProtocolCommand.ZMSCORE)
					.run(args);
		}
	}

	private <V> V zRandMember(final CommandArguments args) {
		if(isPipeline()){
			return new LettuceSentinelPipelineCommand<V, V>(client, ProtocolCommand.ZRANDMEMBER)
					.run(args);
		}else if(isTransaction()){
			return new LettuceSentinelTransactionCommand<V, V>(client, ProtocolCommand.ZRANDMEMBER)
					.run(args);
		}else{
			return new LettuceSentinelCommand<V, V>(client, ProtocolCommand.ZRANDMEMBER)
					.run(args);
		}
	}

	private <V> List<V> zRange(final byte[] key, final long start, final long end,
							   final Converter<List<byte[]>, List<V>> converter, final CommandArguments args) {
		if(isPipeline()){
			return new LettuceSentinelPipelineCommand<List<V>, List<V>>(client, ProtocolCommand.ZRANGE)
					.run(args);
		}else if(isTransaction()){
			return new LettuceSentinelTransactionCommand<List<V>, List<V>>(client, ProtocolCommand.ZRANGE)
					.run(args);
		}else{
			return new LettuceSentinelCommand<List<V>, List<V>>(client, ProtocolCommand.ZRANGE)
					.run(args);
		}
	}

	private <V> List<V> zRangeByLex(final byte[] key, final byte[] min, final byte[] max,
									final Converter<List<byte[]>, List<V>> converter, final CommandArguments args) {
		return zRangeByLex(key, Range.create(min, max), converter, args);
	}

	private <V> List<V> zRangeByLex(final byte[] key, final Range<byte[]> range,
									final Converter<List<byte[]>, List<V>> converter, final CommandArguments args) {
		if(isPipeline()){
			return new LettuceSentinelPipelineCommand<List<V>, List<V>>(client, ProtocolCommand.ZRANGEBYLEX)
					.run(args);
		}else if(isTransaction()){
			return new LettuceSentinelTransactionCommand<List<V>, List<V>>(client, ProtocolCommand.ZRANGEBYLEX)
					.run(args);
		}else{
			return new LettuceSentinelCommand<List<V>, List<V>>(client, ProtocolCommand.ZRANGEBYLEX)
					.run(args);
		}
	}

	private <V> List<V> zRangeByLex(final byte[] key, final byte[] min, final byte[] max, final long offset,
									final long count, final Converter<List<byte[]>, List<V>> converter,
									final CommandArguments args) {
		return zRangeByLex(key, Range.create(min, max), Limit.create(offset, count), converter, args);
	}

	private <V> List<V> zRangeByLex(final byte[] key, final Range<byte[]> range, final Limit limit,
									final Converter<List<byte[]>, List<V>> converter, final CommandArguments args) {
		if(isPipeline()){
			return new LettuceSentinelPipelineCommand<List<V>, List<V>>(client, ProtocolCommand.ZRANGEBYLEX)
					.run(args);
		}else if(isTransaction()){
			return new LettuceSentinelTransactionCommand<List<V>, List<V>>(client, ProtocolCommand.ZRANGEBYLEX)
					.run(args);
		}else{
			return new LettuceSentinelCommand<List<V>, List<V>>(client, ProtocolCommand.ZRANGEBYLEX)
					.run(args);
		}
	}

	private <V> List<V> zRangeByScore(final byte[] key, final double min, final double max,
									  final Converter<List<byte[]>, List<V>> converter, final CommandArguments args) {
		return zRangeByScore(key, Range.create(min, max), converter, args);
	}

	private <V> List<V> zRangeByScore(final byte[] key, final Range<Double> range,
									  final Converter<List<byte[]>, List<V>> converter, final CommandArguments args) {
		if(isPipeline()){
			return new LettuceSentinelPipelineCommand<List<V>, List<V>>(client, ProtocolCommand.ZRANGEBYSCORE)
					.run(args);
		}else if(isTransaction()){
			return new LettuceSentinelTransactionCommand<List<V>, List<V>>(client, ProtocolCommand.ZRANGEBYSCORE)
					.run(args);
		}else{
			return new LettuceSentinelCommand<List<V>, List<V>>(client, ProtocolCommand.ZRANGEBYSCORE)
					.run(args);
		}
	}

	private <V> List<V> zRangeByScore(final byte[] key, final double min, final double max, final long offset,
									  final long count, final Converter<List<byte[]>, List<V>> converter,
									  final CommandArguments args) {
		return zRangeByScore(key, Range.create(min, max), Limit.create(offset, count), converter, args);
	}

	private <V> List<V> zRangeByScore(final byte[] key, final Range<Double> range, final Limit limit,
									  final Converter<List<byte[]>, List<V>> converter, final CommandArguments args) {
		if(isPipeline()){
			return new LettuceSentinelPipelineCommand<List<V>, List<V>>(client, ProtocolCommand.ZRANGEBYSCORE)
					.run(args);
		}else if(isTransaction()){
			return new LettuceSentinelTransactionCommand<List<V>, List<V>>(client, ProtocolCommand.ZRANGEBYSCORE)
					.run(args);
		}else{
			return new LettuceSentinelCommand<List<V>, List<V>>(client, ProtocolCommand.ZRANGEBYSCORE)
					.run(args);
		}
	}

	private Long zRangeStore(final CommandArguments args) {
		if(isPipeline()){
			return new LettuceSentinelPipelineCommand<Long, Long>(client, ProtocolCommand.ZRANGESTORE)
					.run(args);
		}else if(isTransaction()){
			return new LettuceSentinelTransactionCommand<Long, Long>(client, ProtocolCommand.ZRANGESTORE)
					.run(args);
		}else{
			return new LettuceSentinelCommand<Long, Long>(client, ProtocolCommand.ZRANGESTORE)
					.run(args);
		}
	}

	private <V> List<V> zRevRange(final byte[] key, final long start, final long end,
								  final Converter<List<byte[]>, List<V>> converter, final CommandArguments args) {
		if(isPipeline()){
			return new LettuceSentinelPipelineCommand<List<V>, List<V>>(client, ProtocolCommand.ZREVRANGE)
					.run(args);
		}else if(isTransaction()){
			return new LettuceSentinelTransactionCommand<List<V>, List<V>>(client, ProtocolCommand.ZREVRANGE)
					.run(args);
		}else{
			return new LettuceSentinelCommand<List<V>, List<V>>(client, ProtocolCommand.ZREVRANGE)
					.run(args);
		}
	}

	private <V> List<V> zRevRangeByLex(final byte[] key, final byte[] min, final byte[] max,
									   final Converter<List<byte[]>, List<V>> converter, final CommandArguments args) {
		return zRevRangeByLex(key, Range.create(min, max), converter, args);
	}

	private <V> List<V> zRevRangeByLex(final byte[] key, final Range<byte[]> range,
									   final Converter<List<byte[]>, List<V>> converter, final CommandArguments args) {
		if(isPipeline()){
			return new LettuceSentinelPipelineCommand<List<V>, List<V>>(client, ProtocolCommand.ZREVRANGEBYLEX)
					.run(args);
		}else if(isTransaction()){
			return new LettuceSentinelTransactionCommand<List<V>, List<V>>(client, ProtocolCommand.ZREVRANGEBYLEX)
					.run(args);
		}else{
			return new LettuceSentinelCommand<List<V>, List<V>>(client, ProtocolCommand.ZREVRANGEBYLEX)
					.run(args);
		}
	}

	private <V> List<V> zRevRangeByLex(final byte[] key, final byte[] min, final byte[] max, final long offset,
									   final long count, final Converter<List<byte[]>, List<V>> converter,
									   final CommandArguments args) {
		return zRevRangeByLex(key, Range.create(min, max), Limit.create(offset, count), converter, args);
	}

	private <V> List<V> zRevRangeByLex(final byte[] key, final Range<byte[]> range, final Limit limit,
									   final Converter<List<byte[]>, List<V>> converter, final CommandArguments args) {
		if(isPipeline()){
			return new LettuceSentinelPipelineCommand<List<V>, List<V>>(client, ProtocolCommand.ZREVRANGEBYLEX)
					.run(args);
		}else if(isTransaction()){
			return new LettuceSentinelTransactionCommand<List<V>, List<V>>(client, ProtocolCommand.ZREVRANGEBYLEX)
					.run(args);
		}else{
			return new LettuceSentinelCommand<List<V>, List<V>>(client, ProtocolCommand.ZREVRANGEBYLEX)
					.run(args);
		}
	}

	private <V> List<V> zRevRangeByScore(final byte[] key, final double min, final double max,
										 final Converter<List<byte[]>, List<V>> converter,
										 final CommandArguments args) {
		return zRevRangeByScore(key, Range.create(min, max), converter, args);
	}

	private <V> List<V> zRevRangeByScore(final byte[] key, final Range<Double> range,
										 final Converter<List<byte[]>, List<V>> converter,
										 final CommandArguments args) {
		if(isPipeline()){
			return new LettuceSentinelPipelineCommand<List<V>, List<V>>(client, ProtocolCommand.ZREVRANGEBYSCORE)
					.run(args);
		}else if(isTransaction()){
			return new LettuceSentinelTransactionCommand<List<V>, List<V>>(client, ProtocolCommand.ZREVRANGEBYSCORE)
					.run(args);
		}else{
			return new LettuceSentinelCommand<List<V>, List<V>>(client, ProtocolCommand.ZREVRANGEBYSCORE)
					.run(args);
		}
	}

	private <V> List<V> zRevRangeByScore(final byte[] key, final double min, final double max, final long offset,
										 final long count, final Converter<List<byte[]>, List<V>> converter,
										 final CommandArguments args) {
		return zRevRangeByScore(key, Range.create(min, max), Limit.create(offset, count), converter, args);
	}

	private <V> List<V> zRevRangeByScore(final byte[] key, final Range<Double> range, final Limit limit,
										 final Converter<List<byte[]>, List<V>> converter,
										 final CommandArguments args) {
		if(isPipeline()){
			return new LettuceSentinelPipelineCommand<List<V>, List<V>>(client, ProtocolCommand.ZREVRANGEBYSCORE)
					.run(args);
		}else if(isTransaction()){
			return new LettuceSentinelTransactionCommand<List<V>, List<V>>(client, ProtocolCommand.ZREVRANGEBYSCORE)
					.run(args);
		}else{
			return new LettuceSentinelCommand<List<V>, List<V>>(client, ProtocolCommand.ZREVRANGEBYSCORE)
					.run(args);
		}
	}

	private ScanResult<List<Tuple>> zScan(final byte[] key, final ScanCursor cursor,
										  final Converter<ScoredValueScanCursor<byte[]>, ScanResult<List<Tuple>>> converter,
										  final CommandArguments args) {
		if(isPipeline()){
			return new LettuceSentinelPipelineCommand<ScanResult<List<Tuple>>, ScanResult<List<Tuple>>>(client,
					ProtocolCommand.ZREVRANK)
					.run(args);
		}else if(isTransaction()){
			return new LettuceSentinelTransactionCommand<ScanResult<List<Tuple>>, ScanResult<List<Tuple>>>(client,
					ProtocolCommand.ZREVRANK)
					.run(args);
		}else{
			return new LettuceSentinelCommand<ScanResult<List<Tuple>>, ScanResult<List<Tuple>>>(client,
					ProtocolCommand.ZREVRANK)
					.run(args);
		}
	}

	private ScanResult<List<Tuple>> zScan(final byte[] key, final ScanCursor cursor, final ScanArgs scanArgs,
										  final Converter<ScoredValueScanCursor<byte[]>, ScanResult<List<Tuple>>> converter,
										  final CommandArguments args) {
		if(isPipeline()){
			return new LettuceSentinelPipelineCommand<ScanResult<List<Tuple>>, ScanResult<List<Tuple>>>(client,
					ProtocolCommand.ZREVRANK)
					.run(args);
		}else if(isTransaction()){
			return new LettuceSentinelTransactionCommand<ScanResult<List<Tuple>>, ScanResult<List<Tuple>>>(client,
					ProtocolCommand.ZREVRANK)
					.run(args);
		}else{
			return new LettuceSentinelCommand<ScanResult<List<Tuple>>, ScanResult<List<Tuple>>>(client,
					ProtocolCommand.ZREVRANK)
					.run(args);
		}
	}

	private <V> List<V> zUnion(final CommandArguments args) {
		if(isPipeline()){
			return new LettuceSentinelPipelineCommand<List<V>, List<V>>(client, ProtocolCommand.ZUNION)
					.run(args);
		}else if(isTransaction()){
			return new LettuceSentinelTransactionCommand<List<V>, List<V>>(client, ProtocolCommand.ZUNION)
					.run(args);
		}else{
			return new LettuceSentinelCommand<List<V>, List<V>>(client, ProtocolCommand.ZUNION)
					.run(args);
		}
	}

	private Long zUnionStore(final byte[] destKey, final byte[][] keys, final ZStoreArgs zStoreArgs,
							 final CommandArguments args) {
		if(isPipeline()){
			return new LettuceSentinelPipelineCommand<Long, Long>(client, ProtocolCommand.ZUNIONSTORE)
					.run(args);
		}else if(isTransaction()){
			return new LettuceSentinelTransactionCommand<Long, Long>(client, ProtocolCommand.ZUNIONSTORE)
					.run(args);
		}else{
			return new LettuceSentinelCommand<Long, Long>(client, ProtocolCommand.ZUNIONSTORE)
					.run(args);
		}
	}

}
