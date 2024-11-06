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
import com.buession.core.utils.NumberUtils;
import com.buession.redis.client.lettuce.LettuceStandaloneClient;
import com.buession.redis.core.Aggregate;
import com.buession.redis.core.GtLt;
import com.buession.redis.core.NxXx;
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.Tuple;
import com.buession.redis.core.ZRangeBy;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.Command;
import com.buession.redis.core.internal.convert.Converters;
import com.buession.redis.core.internal.convert.lettuce.response.KeyValueConverter;
import com.buession.redis.core.internal.convert.lettuce.response.ScanCursorConverter;
import com.buession.redis.core.internal.convert.lettuce.response.ScoredValueTupleConverter;
import com.buession.redis.core.internal.lettuce.LettuceScanArgs;
import com.buession.redis.core.internal.lettuce.LettuceScanCursor;
import com.buession.redis.core.internal.lettuce.LettuceZAddArgs;
import com.buession.redis.core.internal.lettuce.LettuceZStoreArgs;
import com.buession.redis.core.internal.lettuce.utils.ScoredValueUtils;
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
 * Lettuce 单机模式模式有序集合命令操作
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public final class LettuceSortedSetOperations extends AbstractSortedSetOperations<LettuceStandaloneClient> {

	public LettuceSortedSetOperations(final LettuceStandaloneClient client) {
		super(client);
	}

	@Override
	public Tuple zPopMin(final byte[] key) {
		final CommandArguments args = CommandArguments.create("key", key);
		final ScoredValueTupleConverter.BinaryScoredValueTupleConverter scoredValueConverter = new ScoredValueTupleConverter.BinaryScoredValueTupleConverter();

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.ZPOPMIN, (cmd)->cmd.zpopmin(key),
					scoredValueConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.ZPOPMIN, (cmd)->cmd.zpopmin(key),
					scoredValueConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.ZPOPMIN, (cmd)->cmd.zpopmin(key), scoredValueConverter)
					.run(args);
		}
	}

	@Override
	public List<Tuple> zPopMin(final byte[] key, final int count) {
		final CommandArguments args = CommandArguments.create("key", key).put("count", count);
		final ListConverter<ScoredValue<byte[]>, Tuple> listScoredValueConverter = ScoredValueTupleConverter.BinaryScoredValueTupleConverter.listConverter();

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.ZPOPMIN, (cmd)->cmd.zpopmin(key, count),
					listScoredValueConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.ZPOPMIN, (cmd)->cmd.zpopmin(key, count),
					listScoredValueConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.ZPOPMIN, (cmd)->cmd.zpopmin(key, count),
					listScoredValueConverter)
					.run(args);
		}
	}

	@Override
	public Tuple zPopMax(final byte[] key) {
		final CommandArguments args = CommandArguments.create("key", key);
		final ScoredValueTupleConverter.BinaryScoredValueTupleConverter scoredValueConverter = new ScoredValueTupleConverter.BinaryScoredValueTupleConverter();

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.ZPOPMAX, (cmd)->cmd.zpopmin(key),
					scoredValueConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.ZPOPMAX, (cmd)->cmd.zpopmin(key),
					scoredValueConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.ZPOPMAX, (cmd)->cmd.zpopmin(key), scoredValueConverter)
					.run(args);
		}
	}

	@Override
	public List<Tuple> zPopMax(final byte[] key, final int count) {
		final CommandArguments args = CommandArguments.create("key", key).put("count", count);
		final ListConverter<ScoredValue<byte[]>, Tuple> listScoredValueConverter = ScoredValueTupleConverter.BinaryScoredValueTupleConverter.listConverter();

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.ZPOPMAX, (cmd)->cmd.zpopmin(key, count),
					listScoredValueConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.ZPOPMAX, (cmd)->cmd.zpopmin(key, count),
					listScoredValueConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.ZPOPMAX, (cmd)->cmd.zpopmin(key, count),
					listScoredValueConverter)
					.run(args);
		}
	}

	@Override
	public com.buession.lang.KeyValue<String, Tuple> bzPopMin(final String[] keys, final int timeout) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys).put("timeout", timeout);
		final byte[][] bKeys = SafeEncoder.encode(keys);
		final KeyValueConverter<byte[], ScoredValue<byte[]>, String, Tuple> converter = new KeyValueConverter<>(
				SafeEncoder::encode, new ScoredValueTupleConverter.BinaryScoredValueTupleConverter());

		return bzPopMin(bKeys, timeout, converter, args);
	}

	@Override
	public com.buession.lang.KeyValue<byte[], Tuple> bzPopMin(final byte[][] keys, final int timeout) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys).put("timeout", timeout);
		final KeyValueConverter<byte[], ScoredValue<byte[]>, byte[], Tuple> converter = new KeyValueConverter<>(
				(k)->k, new ScoredValueTupleConverter.BinaryScoredValueTupleConverter());

		return bzPopMin(keys, timeout, converter, args);
	}

	@Override
	public com.buession.lang.KeyValue<String, Tuple> bzPopMax(final String[] keys, final int timeout) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys).put("timeout", timeout);
		final byte[][] bKeys = SafeEncoder.encode(keys);
		final KeyValueConverter<byte[], ScoredValue<byte[]>, String, Tuple> converter = new KeyValueConverter<>(
				SafeEncoder::encode, new ScoredValueTupleConverter.BinaryScoredValueTupleConverter());

		return bzPopMax(bKeys, timeout, converter, args);
	}

	@Override
	public com.buession.lang.KeyValue<byte[], Tuple> bzPopMax(final byte[][] keys, final int timeout) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys).put("timeout", timeout);
		final KeyValueConverter<byte[], ScoredValue<byte[]>, byte[], Tuple> converter = new KeyValueConverter<>(
				(k)->k, new ScoredValueTupleConverter.BinaryScoredValueTupleConverter());

		return bzPopMax(keys, timeout, converter, args);
	}

	@Override
	public Long zAdd(final String key, final Map<String, Double> members) {
		final CommandArguments args = CommandArguments.create("keys", key).put("members", members);
		return zAdd(key, members, args);
	}

	@Override
	public Long zAdd(final byte[] key, final Map<byte[], Double> members) {
		final CommandArguments args = CommandArguments.create("keys", key).put("members", members);
		return zAdd(key, members, args);
	}

	@Override
	public Long zAdd(final String key, final Map<String, Double> members, final NxXx nxXx) {
		final CommandArguments args = CommandArguments.create("keys", key).put("members", members).put("nxXx", nxXx);
		final ZAddArgs zAddArgs = new LettuceZAddArgs(nxXx);

		return zAdd(key, members, zAddArgs, args);
	}

	@Override
	public Long zAdd(final byte[] key, final Map<byte[], Double> members, final NxXx nxXx) {
		final CommandArguments args = CommandArguments.create("keys", key).put("members", members).put("nxXx", nxXx);
		final ZAddArgs zAddArgs = new LettuceZAddArgs(nxXx);

		return zAdd(key, members, zAddArgs, args);
	}

	@Override
	public Long zAdd(final String key, final Map<String, Double> members, final GtLt gtLt) {
		final CommandArguments args = CommandArguments.create("keys", key).put("members", members).put("gtLt", gtLt);
		final ZAddArgs zAddArgs = new LettuceZAddArgs(gtLt);

		return zAdd(key, members, zAddArgs, args);
	}

	@Override
	public Long zAdd(final byte[] key, final Map<byte[], Double> members, final GtLt gtLt) {
		final CommandArguments args = CommandArguments.create("keys", key).put("members", members).put("gtLt", gtLt);
		final ZAddArgs zAddArgs = new LettuceZAddArgs(gtLt);

		return zAdd(key, members, zAddArgs, args);
	}

	@Override
	public Long zAdd(final String key, final Map<String, Double> members, final boolean ch) {
		final CommandArguments args = CommandArguments.create("keys", key).put("members", members).put("ch", ch);
		final ZAddArgs zAddArgs = new LettuceZAddArgs(ch);

		return zAdd(key, members, zAddArgs, args);
	}

	@Override
	public Long zAdd(final byte[] key, final Map<byte[], Double> members, final boolean ch) {
		final CommandArguments args = CommandArguments.create("keys", key).put("members", members).put("ch", ch);
		final ZAddArgs zAddArgs = new LettuceZAddArgs(ch);

		return zAdd(key, members, zAddArgs, args);
	}

	@Override
	public Long zAdd(final String key, final Map<String, Double> members, final NxXx nxXx, final GtLt gtLt) {
		final CommandArguments args = CommandArguments.create("keys", key).put("members", members).put("nxXx", nxXx)
				.put("gtLt", gtLt);
		final ZAddArgs zAddArgs = new LettuceZAddArgs(nxXx, gtLt);

		return zAdd(key, members, zAddArgs, args);
	}

	@Override
	public Long zAdd(final byte[] key, final Map<byte[], Double> members, final NxXx nxXx, final GtLt gtLt) {
		final CommandArguments args = CommandArguments.create("keys", key).put("members", members).put("nxXx", nxXx)
				.put("gtLt", gtLt);
		final ZAddArgs zAddArgs = new LettuceZAddArgs(nxXx, gtLt);

		return zAdd(key, members, zAddArgs, args);
	}

	@Override
	public Long zAdd(final String key, final Map<String, Double> members, final NxXx nxXx, final boolean ch) {
		final CommandArguments args = CommandArguments.create("keys", key).put("members", members).put("nxXx", nxXx)
				.put("ch", ch);
		final ZAddArgs zAddArgs = new LettuceZAddArgs(nxXx, ch);

		return zAdd(key, members, zAddArgs, args);
	}

	@Override
	public Long zAdd(final byte[] key, final Map<byte[], Double> members, final NxXx nxXx, final boolean ch) {
		final CommandArguments args = CommandArguments.create("keys", key).put("members", members).put("nxXx", nxXx)
				.put("ch", ch);
		final ZAddArgs zAddArgs = new LettuceZAddArgs(nxXx, ch);

		return zAdd(key, members, zAddArgs, args);
	}

	@Override
	public Long zAdd(final String key, final Map<String, Double> members, final GtLt gtLt, final boolean ch) {
		final CommandArguments args = CommandArguments.create("keys", key).put("members", members).put("gtLt", gtLt)
				.put("ch", ch);
		final ZAddArgs zAddArgs = new LettuceZAddArgs(gtLt, ch);

		return zAdd(key, members, zAddArgs, args);
	}

	@Override
	public Long zAdd(final byte[] key, final Map<byte[], Double> members, final GtLt gtLt, final boolean ch) {
		final CommandArguments args = CommandArguments.create("keys", key).put("members", members).put("gtLt", gtLt)
				.put("ch", ch);
		final ZAddArgs zAddArgs = new LettuceZAddArgs(gtLt, ch);

		return zAdd(key, members, zAddArgs, args);
	}

	@Override
	public Long zAdd(final String key, final Map<String, Double> members, final NxXx nxXx, final GtLt gtLt,
					 final boolean ch) {
		final CommandArguments args = CommandArguments.create("keys", key).put("members", members).put("nxXx", nxXx)
				.put("gtLt", gtLt).put("ch", ch);
		final ZAddArgs zAddArgs = new LettuceZAddArgs(nxXx, gtLt, ch);

		return zAdd(key, members, zAddArgs, args);
	}

	@Override
	public Long zAdd(final byte[] key, final Map<byte[], Double> members, final NxXx nxXx, final GtLt gtLt,
					 final boolean ch) {
		final CommandArguments args = CommandArguments.create("keys", key).put("members", members).put("nxXx", nxXx)
				.put("gtLt", gtLt).put("ch", ch);
		final ZAddArgs zAddArgs = new LettuceZAddArgs(nxXx, gtLt, ch);

		return zAdd(key, members, zAddArgs, args);
	}

	@Override
	public Long zCard(final byte[] key) {
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.ZCARD, (cmd)->cmd.zcard(key), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.ZCARD, (cmd)->cmd.zcard(key), (v)->v)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.ZCARD, (cmd)->cmd.zcard(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long zCount(final byte[] key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		final Range<Double> range = Range.create(min, max);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.ZCOUNT, (cmd)->cmd.zcount(key, range), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.ZCOUNT, (cmd)->cmd.zcount(key, range),
					(v)->v)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.ZCOUNT, (cmd)->cmd.zcount(key, range), (v)->v)
					.run(args);
		}
	}

	@Override
	public List<String> zDiff(final String... keys) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys);
		return zDiff(args);
	}

	@Override
	public List<byte[]> zDiff(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys);
		return zDiff(args);
	}

	@Override
	public List<Tuple> zDiffWithScores(final String... keys) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys);
		return zDiff(args);
	}

	@Override
	public List<Tuple> zDiffWithScores(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys);
		return zDiff(args);
	}

	@Override
	public Long zDiffStore(final String destKey, final String... keys) {
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("keys", (Object[]) keys);
		return zDiffStore(args);
	}

	@Override
	public Long zDiffStore(final byte[] destKey, final byte[]... keys) {
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("keys", (Object[]) keys);
		return zDiffStore(args);
	}

	@Override
	public Double zIncrBy(final byte[] key, final double increment, final byte[] member) {
		final CommandArguments args = CommandArguments.create("key", key).put("increment", increment)
				.put("member", member);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.ZINCRBY,
					(cmd)->cmd.zincrby(key, increment, member), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.ZINCRBY,
					(cmd)->cmd.zincrby(key, increment, member), (v)->v)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.ZINCRBY, (cmd)->cmd.zincrby(key, increment, member),
					(v)->v)
					.run(args);
		}
	}

	@Override
	public List<String> zInter(final String... keys) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys);
		return zInter(args);
	}

	@Override
	public List<byte[]> zInter(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys);
		return zInter(args);
	}

	@Override
	public List<String> zInter(final String[] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys).put("aggregate", aggregate);
		return zInter(args);
	}

	@Override
	public List<byte[]> zInter(final byte[][] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys).put("aggregate", aggregate);
		return zInter(args);
	}

	@Override
	public List<String> zInter(final String[] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys).put("weights", weights);
		return zInter(args);
	}

	@Override
	public List<byte[]> zInter(final byte[][] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys).put("weights", weights);
		return zInter(args);
	}

	@Override
	public List<String> zInter(final String[] keys, final Aggregate aggregate, final double... weights) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys).put("aggregate", aggregate)
				.put("weights", weights);
		return zInter(args);
	}

	@Override
	public List<byte[]> zInter(final byte[][] keys, final Aggregate aggregate, final double... weights) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys).put("aggregate", aggregate)
				.put("weights", weights);
		return zInter(args);
	}

	@Override
	public List<Tuple> zInterWithScores(final String... keys) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys);
		return zInter(args);
	}

	@Override
	public List<Tuple> zInterWithScores(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys);
		return zInter(args);
	}

	@Override
	public List<Tuple> zInterWithScores(final String[] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys).put("aggregate", aggregate);
		return zInter(args);
	}

	@Override
	public List<Tuple> zInterWithScores(final byte[][] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys).put("aggregate", aggregate);
		return zInter(args);
	}

	@Override
	public List<Tuple> zInterWithScores(final String[] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys).put("weights", weights);
		return zInter(args);
	}

	@Override
	public List<Tuple> zInterWithScores(final byte[][] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys).put("weights", weights);
		return zInter(args);
	}

	@Override
	public List<Tuple> zInterWithScores(final String[] keys, final Aggregate aggregate, final double... weights) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys).put("aggregate", aggregate)
				.put("weights", weights);
		return zInter(args);
	}

	@Override
	public List<Tuple> zInterWithScores(final byte[][] keys, final Aggregate aggregate, final double... weights) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys).put("aggregate", aggregate)
				.put("weights", weights);
		return zInter(args);
	}

	@Override
	public Long zInterStore(final byte[] destKey, final byte[]... keys) {
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("keys", (Object[]) keys);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.ZINTERSTORE,
					(cmd)->cmd.zinterstore(destKey, keys), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.ZINTERSTORE,
					(cmd)->cmd.zinterstore(destKey, keys), (v)->v)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.ZINTERSTORE, (cmd)->cmd.zinterstore(destKey, keys),
					(v)->v)
					.run(args);
		}
	}

	@Override
	public Long zInterStore(final byte[] destKey, final byte[][] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("keys", (Object[]) keys)
				.put("aggregate", aggregate);
		final ZStoreArgs zStoreArgs = new LettuceZStoreArgs(aggregate);

		return zInterStore(destKey, keys, zStoreArgs, args);
	}

	@Override
	public Long zInterStore(final byte[] destKey, final byte[][] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("keys", (Object[]) keys)
				.put("weights", weights);
		final ZStoreArgs zStoreArgs = new LettuceZStoreArgs(weights);

		return zInterStore(destKey, keys, zStoreArgs, args);
	}

	@Override
	public Long zInterStore(final byte[] destKey, final byte[][] keys, final Aggregate aggregate,
							final double... weights) {
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("keys", (Object[]) keys)
				.put("aggregate", aggregate).put("weights", weights);
		final ZStoreArgs zStoreArgs = new LettuceZStoreArgs(aggregate, weights);

		return zInterStore(destKey, keys, zStoreArgs, args);
	}

	@Override
	public List<Double> zMScore(final String key, final String... members) {
		final CommandArguments args = CommandArguments.create("key", key).put("members", (Object[]) members);
		return zMScore(args);
	}

	@Override
	public List<Double> zMScore(final byte[] key, final byte[]... members) {
		final CommandArguments args = CommandArguments.create("key", key).put("members", (Object[]) members);
		return zMScore(args);
	}

	@Override
	public String zRandMember(final String key) {
		final CommandArguments args = CommandArguments.create("key", key);
		return zRandMember(args);
	}

	@Override
	public byte[] zRandMember(final byte[] key) {
		final CommandArguments args = CommandArguments.create("key", key);
		return zRandMember(args);
	}

	@Override
	public List<String> zRandMember(final String key, final int count) {
		final CommandArguments args = CommandArguments.create("key", key).put("count", count);
		return zRandMember(args);
	}

	@Override
	public List<byte[]> zRandMember(final byte[] key, final int count) {
		final CommandArguments args = CommandArguments.create("key", key).put("count", count);
		return zRandMember(args);
	}

	@Override
	public List<Tuple> zRandMemberWithScores(final String key, final int count) {
		final CommandArguments args = CommandArguments.create("key", key).put("count", count);
		return zRandMember(args);
	}

	@Override
	public List<Tuple> zRandMemberWithScores(final byte[] key, final int count) {
		final CommandArguments args = CommandArguments.create("key", key).put("count", count);
		return zRandMember(args);
	}

	@Override
	public List<String> zRange(final String key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create("key", key).put("start", start).put("end", end);
		final byte[] bKey = SafeEncoder.encode(key);
		final ListConverter<byte[], String> listConverter = Converters.listBinaryToString();

		return zRange(bKey, start, end, listConverter, args);
	}

	@Override
	public List<byte[]> zRange(final byte[] key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create("key", key).put("start", start).put("end", end);
		return zRange(key, start, end, (v)->v, args);
	}

	@Override
	public List<Tuple> zRangeWithScores(final byte[] key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create("key", key).put("start", start).put("end", end);
		final ListConverter<ScoredValue<byte[]>, Tuple> listScoredValueConverter = ScoredValueTupleConverter.BinaryScoredValueTupleConverter.listConverter();

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.ZRANGE,
					(cmd)->cmd.zrangeWithScores(key, start, end), listScoredValueConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.ZRANGE,
					(cmd)->cmd.zrangeWithScores(key, start, end), listScoredValueConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.ZRANGE, (cmd)->cmd.zrangeWithScores(key, start, end),
					listScoredValueConverter)
					.run(args);
		}
	}

	@Override
	public List<String> zRangeByLex(final String key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		final byte[] bKey = SafeEncoder.encode(key);
		final byte[] bMin = NumberUtils.double2bytes(min);
		final byte[] bMax = NumberUtils.double2bytes(max);
		final ListConverter<byte[], String> listConverter = Converters.listBinaryToString();

		return zRangeByLex(bKey, bMin, bMax, listConverter, args);
	}

	@Override
	public List<byte[]> zRangeByLex(final byte[] key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		final byte[] bMin = NumberUtils.double2bytes(min);
		final byte[] bMax = NumberUtils.double2bytes(max);

		return zRangeByLex(key, bMin, bMax, (v)->v, args);
	}

	@Override
	public List<String> zRangeByLex(final String key, final double min, final double max, final long offset,
									final int count) {
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max)
				.put("offset", offset).put("count", count);
		final byte[] bKey = SafeEncoder.encode(key);
		final byte[] bMin = NumberUtils.double2bytes(min);
		final byte[] bMax = NumberUtils.double2bytes(max);
		final ListConverter<byte[], String> listConverter = Converters.listBinaryToString();

		return zRangeByLex(bKey, bMin, bMax, offset, count, listConverter, args);
	}

	@Override
	public List<byte[]> zRangeByLex(final byte[] key, final double min, final double max, final long offset,
									final int count) {
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max)
				.put("offset", offset).put("count", count);
		final byte[] bMin = NumberUtils.double2bytes(min);
		final byte[] bMax = NumberUtils.double2bytes(max);

		return zRangeByLex(key, bMin, bMax, offset, count, (v)->v, args);
	}

	@Override
	public List<String> zRangeByScore(final String key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		final byte[] bKey = SafeEncoder.encode(key);
		final ListConverter<byte[], String> listConverter = Converters.listBinaryToString();

		return zRangeByScore(bKey, min, max, listConverter, args);
	}

	@Override
	public List<byte[]> zRangeByScore(final byte[] key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		return zRangeByScore(key, min, max, (v)->v, args);
	}

	@Override
	public List<String> zRangeByScore(final String key, final double min, final double max, final long offset,
									  final int count) {
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max)
				.put("offset", offset).put("count", count);
		final byte[] bKey = SafeEncoder.encode(key);
		final ListConverter<byte[], String> listConverter = Converters.listBinaryToString();

		return zRangeByScore(bKey, min, max, offset, count, listConverter, args);
	}

	@Override
	public List<byte[]> zRangeByScore(final byte[] key, final double min, final double max, final long offset,
									  final int count) {
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max)
				.put("offset", offset).put("count", count);
		return zRangeByScore(key, min, max, offset, count, (v)->v, args);
	}

	@Override
	public List<Tuple> zRangeByScoreWithScores(final byte[] key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		final Range<Double> range = Range.create(min, max);
		final ListConverter<ScoredValue<byte[]>, Tuple> listScoredValueConverter = ScoredValueTupleConverter.BinaryScoredValueTupleConverter.listConverter();

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.ZRANGEBYSCORE,
					(cmd)->cmd.zrangebyscoreWithScores(key, range), listScoredValueConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.ZRANGEBYSCORE,
					(cmd)->cmd.zrangebyscoreWithScores(key, range), listScoredValueConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.ZRANGEBYSCORE,
					(cmd)->cmd.zrangebyscoreWithScores(key, range), listScoredValueConverter)
					.run(args);
		}
	}

	@Override
	public List<Tuple> zRangeByScoreWithScores(final byte[] key, final double min, final double max, final long offset,
											   final int count) {
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max)
				.put("offset", offset).put("count", count);
		final Range<Double> range = Range.create(min, max);
		final Limit limit = Limit.create(offset, count);
		final ListConverter<ScoredValue<byte[]>, Tuple> listScoredValueConverter = ScoredValueTupleConverter.BinaryScoredValueTupleConverter.listConverter();

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.ZRANGEBYSCORE,
					(cmd)->cmd.zrangebyscoreWithScores(key, range, limit), listScoredValueConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.ZRANGEBYSCORE,
					(cmd)->cmd.zrangebyscoreWithScores(key, range, limit), listScoredValueConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.ZRANGEBYSCORE,
					(cmd)->cmd.zrangebyscoreWithScores(key, range, limit), listScoredValueConverter)
					.run(args);
		}
	}

	@Override
	public Long zRangeStore(final String destKey, final String key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("key", key).put("start", start)
				.put("end", end);
		return zRangeStore(args);
	}

	@Override
	public Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("key", key).put("start", start)
				.put("end", end);
		return zRangeStore(args);
	}

	@Override
	public Long zRangeStore(final String destKey, final String key, final long start, final long end,
							final ZRangeBy by) {
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("key", key).put("start", start)
				.put("end", end).put("by", by);
		return zRangeStore(args);
	}

	@Override
	public Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end,
							final ZRangeBy by) {
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("key", key).put("start", start)
				.put("end", end).put("by", by);
		return zRangeStore(args);
	}

	@Override
	public Long zRangeStore(final String destKey, final String key, final long start, final long end,
							final boolean rev) {
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("key", key).put("start", start)
				.put("end", end).put("rev", rev);
		return zRangeStore(args);
	}

	@Override
	public Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end,
							final boolean rev) {
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("key", key).put("start", start)
				.put("end", end).put("rev", rev);
		return zRangeStore(args);
	}

	@Override
	public Long zRangeStore(final String destKey, final String key, final long start, final long end, final long offset,
							final int count) {
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("key", key).put("start", start)
				.put("end", end).put("offset", offset).put("count", count);
		return zRangeStore(args);
	}

	@Override
	public Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end, final long offset,
							final int count) {
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("key", key).put("start", start)
				.put("end", end).put("offset", offset).put("count", count);
		return zRangeStore(args);
	}

	@Override
	public Long zRangeStore(final String destKey, final String key, final long start, final long end,
							final ZRangeBy by, final boolean rev) {
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("key", key).put("start", start)
				.put("end", end).put("by", by).put("rev", rev);
		return zRangeStore(args);
	}

	@Override
	public Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end,
							final ZRangeBy by, final boolean rev) {
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("key", key).put("start", start)
				.put("end", end).put("by", by).put("rev", rev);
		return zRangeStore(args);
	}

	@Override
	public Long zRangeStore(final String destKey, final String key, final long start, final long end,
							final ZRangeBy by, final long offset, final int count) {
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("key", key).put("start", start)
				.put("end", end).put("by", by).put("offset", offset).put("count", count);
		return zRangeStore(args);
	}

	@Override
	public Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end,
							final ZRangeBy by, final long offset, final int count) {
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("key", key).put("start", start)
				.put("end", end).put("by", by).put("offset", offset).put("count", count);
		return zRangeStore(args);
	}

	@Override
	public Long zRangeStore(final String destKey, final String key, final long start, final long end, final boolean rev,
							final long offset, final int count) {
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("key", key).put("start", start)
				.put("end", end).put("rev", rev).put("offset", offset).put("count", count);
		return zRangeStore(args);
	}

	@Override
	public Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end, final boolean rev,
							final long offset, final int count) {
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("key", key).put("start", start)
				.put("end", end).put("rev", rev).put("offset", offset).put("count", count);
		return zRangeStore(args);
	}

	@Override
	public Long zRangeStore(final String destKey, final String key, final long start, final long end, final ZRangeBy by,
							final boolean rev, final long offset, final int count) {
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("key", key).put("start", start)
				.put("end", end).put("by", by).put("rev", rev).put("offset", offset).put("count", count);
		return zRangeStore(args);
	}

	@Override
	public Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end, final ZRangeBy by,
							final boolean rev, final long offset, final int count) {
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("key", key).put("start", start)
				.put("end", end).put("by", by).put("rev", rev).put("offset", offset).put("count", count);
		return zRangeStore(args);
	}

	@Override
	public Long zRank(final byte[] key, final byte[] member) {
		final CommandArguments args = CommandArguments.create("key", key).put("member", member);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.ZRANK, (cmd)->cmd.zrank(key, member), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.ZRANK, (cmd)->cmd.zrank(key, member), (v)->v)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.ZRANK, (cmd)->cmd.zrank(key, member), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long zRem(final byte[] key, final byte[]... members) {
		final CommandArguments args = CommandArguments.create("key", key).put("members", (Object[]) members);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.ZREM, (cmd)->cmd.zrem(key, members), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.ZREM, (cmd)->cmd.zrem(key, members), (v)->v)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.ZREM, (cmd)->cmd.zrem(key, members), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long zRemRangeByScore(final byte[] key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		final Range<Double> range = Range.create(min, max);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.ZREMRANGEBYSCORE,
					(cmd)->cmd.zremrangebyscore(key, range), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.ZREMRANGEBYSCORE,
					(cmd)->cmd.zremrangebyscore(key, range), (v)->v)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.ZREMRANGEBYSCORE,
					(cmd)->cmd.zremrangebyscore(key, range), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long zRemRangeByRank(final byte[] key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create("key", key).put("start", start).put("end", end);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.ZREMRANGEBYRANK,
					(cmd)->cmd.zremrangebyrank(key, start, end), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.ZREMRANGEBYRANK,
					(cmd)->cmd.zremrangebyrank(key, start, end), (v)->v)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.ZREMRANGEBYRANK,
					(cmd)->cmd.zremrangebyrank(key, start, end), (v)->v)
					.run(args);
		}
	}

	@Override
	public List<String> zRevRange(final String key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create("key", key).put("start", start).put("end", end);
		final byte[] bKey = SafeEncoder.encode(key);
		final ListConverter<byte[], String> listConverter = Converters.listBinaryToString();

		return zRevRange(bKey, start, end, listConverter, args);
	}

	@Override
	public List<byte[]> zRevRange(final byte[] key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create("key", key).put("start", start).put("end", end);
		return zRevRange(key, start, end, (v)->v, args);
	}

	@Override
	public List<Tuple> zRevRangeWithScores(final byte[] key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create("key", key).put("start", start).put("end", end);
		final ListConverter<ScoredValue<byte[]>, Tuple> listScoredValueConverter = ScoredValueTupleConverter.BinaryScoredValueTupleConverter.listConverter();

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.ZREVRANGE,
					(cmd)->cmd.zrevrangeWithScores(key, start, end), listScoredValueConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.ZREVRANGE,
					(cmd)->cmd.zrevrangeWithScores(key, start, end), listScoredValueConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.ZREVRANGE,
					(cmd)->cmd.zrevrangeWithScores(key, start, end), listScoredValueConverter)
					.run(args);
		}
	}

	@Override
	public List<String> zRevRangeByLex(final String key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		final byte[] bKey = SafeEncoder.encode(key);
		final byte[] bMin = NumberUtils.double2bytes(min);
		final byte[] bMax = NumberUtils.double2bytes(max);
		final ListConverter<byte[], String> listConverter = Converters.listBinaryToString();

		return zRevRangeByLex(bKey, bMin, bMax, listConverter, args);
	}

	@Override
	public List<byte[]> zRevRangeByLex(final byte[] key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		final byte[] bMin = NumberUtils.double2bytes(min);
		final byte[] bMax = NumberUtils.double2bytes(max);

		return zRevRangeByLex(key, bMin, bMax, (v)->v, args);
	}

	@Override
	public List<String> zRevRangeByLex(final String key, final double min, final double max, final long offset,
									   final int count) {
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max).put("count"
				, count);
		final byte[] bKey = SafeEncoder.encode(key);
		final byte[] bMin = NumberUtils.double2bytes(min);
		final byte[] bMax = NumberUtils.double2bytes(max);
		final ListConverter<byte[], String> listConverter = Converters.listBinaryToString();

		return zRevRangeByLex(bKey, bMin, bMax, offset, count, listConverter, args);
	}

	@Override
	public List<byte[]> zRevRangeByLex(final byte[] key, final double min, final double max, final long offset,
									   final int count) {
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max).put("count"
				, count);
		final byte[] bMin = NumberUtils.double2bytes(min);
		final byte[] bMax = NumberUtils.double2bytes(max);

		return zRevRangeByLex(key, bMin, bMax, offset, count, (v)->v, args);
	}

	@Override
	public List<String> zRevRangeByScore(final String key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		final byte[] bKey = SafeEncoder.encode(key);
		final ListConverter<byte[], String> listConverter = Converters.listBinaryToString();

		return zRevRangeByScore(bKey, min, max, listConverter, args);
	}

	@Override
	public List<byte[]> zRevRangeByScore(final byte[] key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		return zRevRangeByScore(key, min, max, (v)->v, args);
	}

	@Override
	public List<String> zRevRangeByScore(final String key, final double min, final double max, final long offset,
										 final int count) {
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max)
				.put("offset", offset).put("count", count);
		final byte[] bKey = SafeEncoder.encode(key);
		final ListConverter<byte[], String> listConverter = Converters.listBinaryToString();

		return zRevRangeByScore(bKey, min, max, offset, count, listConverter, args);
	}

	@Override
	public List<byte[]> zRevRangeByScore(final byte[] key, final double min, final double max, final long offset,
										 final int count) {
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max)
				.put("offset", offset).put("count", count);
		return zRevRangeByScore(key, min, max, offset, count, (v)->v, args);
	}

	@Override
	public List<Tuple> zRevRangeByScoreWithScores(final byte[] key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		final Range<Double> range = Range.create(min, max);
		final ListConverter<ScoredValue<byte[]>, Tuple> listScoredValueConverter = ScoredValueTupleConverter.BinaryScoredValueTupleConverter.listConverter();

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.ZREVRANGEBYSCORE,
					(cmd)->cmd.zrevrangebyscoreWithScores(key, range), listScoredValueConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.ZREVRANGEBYSCORE,
					(cmd)->cmd.zrevrangebyscoreWithScores(key, range), listScoredValueConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.ZREVRANGEBYSCORE,
					(cmd)->cmd.zrevrangebyscoreWithScores(key, range), listScoredValueConverter)
					.run(args);
		}
	}

	@Override
	public List<Tuple> zRevRangeByScoreWithScores(final byte[] key, final double min, final double max,
												  final long offset, final int count) {
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max)
				.put("offset", offset).put("count", count);
		final Range<Double> range = Range.create(min, max);
		final Limit limit = Limit.create(offset, count);
		final ListConverter<ScoredValue<byte[]>, Tuple> listScoredValueConverter = ScoredValueTupleConverter.BinaryScoredValueTupleConverter.listConverter();

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.ZREVRANGEBYSCORE,
					(cmd)->cmd.zrevrangebyscoreWithScores(key, range, limit), listScoredValueConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.ZREVRANGEBYSCORE,
					(cmd)->cmd.zrevrangebyscoreWithScores(key, range, limit), listScoredValueConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.ZREVRANGEBYSCORE,
					(cmd)->cmd.zrevrangebyscoreWithScores(key, range, limit), listScoredValueConverter)
					.run(args);
		}
	}

	@Override
	public Long zRevRank(final byte[] key, final byte[] member) {
		final CommandArguments args = CommandArguments.create("key", key).put("member", member);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.ZREVRANK, (cmd)->cmd.zrevrank(key, member),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.ZREVRANK, (cmd)->cmd.zrevrank(key, member),
					(v)->v)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.ZREVRANK, (cmd)->cmd.zrevrank(key, member), (v)->v)
					.run(args);
		}
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final String cursor) {
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor);
		final byte[] bKey = SafeEncoder.encode(key);
		final ScanCursor scanCursor = new LettuceScanCursor(cursor);
		final ScanCursorConverter.ValueScanCursorConverter.ScoredValueScanCursorConverter scoredValueScanCursorConverter =
				new ScanCursorConverter.ValueScanCursorConverter.ScoredValueScanCursorConverter();

		return zScan(bKey, scanCursor, scoredValueScanCursorConverter, args);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor) {
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor);
		final ScanCursor scanCursor = new LettuceScanCursor(cursor);
		final ScanCursorConverter.ValueScanCursorConverter.ScoredValueScanCursorConverter scoredValueScanCursorConverter =
				new ScanCursorConverter.ValueScanCursorConverter.ScoredValueScanCursorConverter();

		return zScan(key, scanCursor, scoredValueScanCursorConverter, args);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final String cursor, final String pattern) {
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor).put("pattern", pattern);
		final byte[] bKey = SafeEncoder.encode(key);
		final ScanCursor scanCursor = new LettuceScanCursor(cursor);
		final ScanArgs scanArgs = new LettuceScanArgs(pattern);
		final ScanCursorConverter.ValueScanCursorConverter.ScoredValueScanCursorConverter scoredValueScanCursorConverter =
				new ScanCursorConverter.ValueScanCursorConverter.ScoredValueScanCursorConverter();

		return zScan(bKey, scanCursor, scanArgs, scoredValueScanCursorConverter, args);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor, final byte[] pattern) {
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor).put("pattern", pattern);
		final ScanCursor scanCursor = new LettuceScanCursor(cursor);
		final ScanArgs scanArgs = new LettuceScanArgs(pattern);
		final ScanCursorConverter.ValueScanCursorConverter.ScoredValueScanCursorConverter scoredValueScanCursorConverter =
				new ScanCursorConverter.ValueScanCursorConverter.ScoredValueScanCursorConverter();

		return zScan(key, scanCursor, scanArgs, scoredValueScanCursorConverter, args);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final String cursor, final int count) {
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor).put("count", count);
		final byte[] bKey = SafeEncoder.encode(key);
		final ScanCursor scanCursor = new LettuceScanCursor(cursor);
		final ScanArgs scanArgs = new LettuceScanArgs(count);
		final ScanCursorConverter.ValueScanCursorConverter.ScoredValueScanCursorConverter scoredValueScanCursorConverter =
				new ScanCursorConverter.ValueScanCursorConverter.ScoredValueScanCursorConverter();

		return zScan(bKey, scanCursor, scanArgs, scoredValueScanCursorConverter, args);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor, final int count) {
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor).put("count", count);
		final ScanCursor scanCursor = new LettuceScanCursor(cursor);
		final ScanArgs scanArgs = new LettuceScanArgs(count);
		final ScanCursorConverter.ValueScanCursorConverter.ScoredValueScanCursorConverter scoredValueScanCursorConverter =
				new ScanCursorConverter.ValueScanCursorConverter.ScoredValueScanCursorConverter();

		return zScan(key, scanCursor, scanArgs, scoredValueScanCursorConverter, args);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final String cursor, final String pattern,
										 final int count) {
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor).put("pattern", pattern)
				.put("count", count);
		final byte[] bKey = SafeEncoder.encode(key);
		final ScanCursor scanCursor = new LettuceScanCursor(cursor);
		final ScanArgs scanArgs = new LettuceScanArgs(pattern, count);
		final ScanCursorConverter.ValueScanCursorConverter.ScoredValueScanCursorConverter scoredValueScanCursorConverter =
				new ScanCursorConverter.ValueScanCursorConverter.ScoredValueScanCursorConverter();

		return zScan(bKey, scanCursor, scanArgs, scoredValueScanCursorConverter, args);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor, final byte[] pattern,
										 final int count) {
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor).put("pattern", pattern)
				.put("count", count);
		final ScanCursor scanCursor = new LettuceScanCursor(cursor);
		final ScanArgs scanArgs = new LettuceScanArgs(pattern, count);
		final ScanCursorConverter.ValueScanCursorConverter.ScoredValueScanCursorConverter scoredValueScanCursorConverter =
				new ScanCursorConverter.ValueScanCursorConverter.ScoredValueScanCursorConverter();

		return zScan(key, scanCursor, scanArgs, scoredValueScanCursorConverter, args);
	}

	@Override
	public Double zScore(final byte[] key, final byte[] member) {
		final CommandArguments args = CommandArguments.create("key", key).put("member", member);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.ZSCORE, (cmd)->cmd.zscore(key, member), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.ZSCORE, (cmd)->cmd.zscore(key, member),
					(v)->v)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.ZSCORE, (cmd)->cmd.zscore(key, member), (v)->v)
					.run(args);
		}
	}

	@Override
	public List<String> zUnion(final String... keys) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys);
		return zUnion(args);
	}

	@Override
	public List<byte[]> zUnion(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys);
		return zUnion(args);
	}

	@Override
	public List<String> zUnion(final String[] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys).put("aggregate", aggregate);
		return zUnion(args);
	}

	@Override
	public List<byte[]> zUnion(final byte[][] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys).put("aggregate", aggregate);
		return zUnion(args);
	}

	@Override
	public List<String> zUnion(final String[] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys).put("weights", weights);
		return zUnion(args);
	}

	@Override
	public List<byte[]> zUnion(final byte[][] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys).put("weights", weights);
		return zUnion(args);
	}

	@Override
	public List<String> zUnion(final String[] keys, final Aggregate aggregate, final double... weights) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys).put("aggregate", aggregate)
				.put("weights", weights);
		return zUnion(args);
	}

	@Override
	public List<byte[]> zUnion(final byte[][] keys, final Aggregate aggregate, final double... weights) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys).put("aggregate", aggregate)
				.put("weights", weights);
		return zUnion(args);
	}

	@Override
	public List<Tuple> zUnionWithScores(final String... keys) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys);
		return zUnion(args);
	}

	@Override
	public List<Tuple> zUnionWithScores(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys);
		return zUnion(args);
	}

	@Override
	public List<Tuple> zUnionWithScores(final String[] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys).put("aggregate", aggregate);
		return zUnion(args);
	}

	@Override
	public List<Tuple> zUnionWithScores(final byte[][] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys).put("aggregate", aggregate);
		return zUnion(args);
	}

	@Override
	public List<Tuple> zUnionWithScores(final String[] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys).put("weights", weights);
		return zUnion(args);
	}

	@Override
	public List<Tuple> zUnionWithScores(final byte[][] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys).put("weights", weights);
		return zUnion(args);
	}

	@Override
	public List<Tuple> zUnionWithScores(final String[] keys, final Aggregate aggregate, final double... weights) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys).put("aggregate", aggregate)
				.put("weights", weights);
		return zUnion(args);
	}

	@Override
	public List<Tuple> zUnionWithScores(final byte[][] keys, final Aggregate aggregate, final double... weights) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys).put("aggregate", aggregate)
				.put("weights", weights);
		return zUnion(args);
	}

	@Override
	public Long zUnionStore(final byte[] destKey, final byte[]... keys) {
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("keys", (Object[]) keys);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.ZUNIONSTORE,
					(cmd)->cmd.zunionstore(destKey, keys), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.ZUNIONSTORE,
					(cmd)->cmd.zunionstore(destKey, keys), (v)->v)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.ZUNIONSTORE, (cmd)->cmd.zunionstore(destKey, keys),
					(v)->v)
					.run(args);
		}
	}

	@Override
	public Long zUnionStore(final byte[] destKey, final byte[][] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("keys", (Object[]) keys)
				.put("aggregate", aggregate);
		final ZStoreArgs zStoreArgs = new LettuceZStoreArgs(aggregate);

		return zUnionStore(destKey, keys, zStoreArgs, args);
	}

	@Override
	public Long zUnionStore(final byte[] destKey, final byte[][] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("keys", (Object[]) keys)
				.put("weights", weights);
		final ZStoreArgs zStoreArgs = new LettuceZStoreArgs(weights);

		return zUnionStore(destKey, keys, zStoreArgs, args);
	}

	@Override
	public Long zUnionStore(final byte[] destKey, final byte[][] keys, final Aggregate aggregate,
							final double... weights) {
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("keys", (Object[]) keys)
				.put("aggregate", aggregate).put("weights", weights);
		final ZStoreArgs zStoreArgs = new LettuceZStoreArgs(aggregate, weights);

		return zUnionStore(destKey, keys, zStoreArgs, args);
	}

	private <K> com.buession.lang.KeyValue<K, Tuple> bzPopMin(final byte[][] keys, final int timeout,
															  final KeyValueConverter<byte[], ScoredValue<byte[]>, K,
																	  Tuple> converter,
															  final CommandArguments args) {
		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.BZPOPMIN, (cmd)->cmd.bzpopmin(timeout, keys),
					converter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.BZPOPMIN,
					(cmd)->cmd.bzpopmin(timeout, keys), converter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.BZPOPMIN, (cmd)->cmd.bzpopmin(timeout, keys),
					converter)
					.run(args);
		}
	}

	private <K> com.buession.lang.KeyValue<K, Tuple> bzPopMax(final byte[][] keys, final int timeout,
															  final KeyValueConverter<byte[], ScoredValue<byte[]>,
																	  K, Tuple> converter,
															  final CommandArguments args) {
		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.BZPOPMAX, (cmd)->cmd.bzpopmax(timeout, keys),
					converter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.BZPOPMAX,
					(cmd)->cmd.bzpopmax(timeout, keys), converter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.BZPOPMAX, (cmd)->cmd.bzpopmax(timeout, keys),
					converter)
					.run(args);
		}
	}

	private Long zAdd(final String key, final Map<String, Double> members, final CommandArguments args) {
		return zAdd(SafeEncoder.encode(key), ScoredValueUtils.fromStringMap(members), args);
	}

	private Long zAdd(final String key, final Map<String, Double> members, final ZAddArgs zAddArgs,
					  final CommandArguments args) {
		return zAdd(SafeEncoder.encode(key), ScoredValueUtils.fromStringMap(members), zAddArgs, args);
	}

	private Long zAdd(final byte[] key, final Map<byte[], Double> members, final CommandArguments args) {
		return zAdd(key, ScoredValueUtils.fromBinaryMap(members), args);
	}

	private Long zAdd(final byte[] key, final Map<byte[], Double> members, final ZAddArgs zAddArgs,
					  final CommandArguments args) {
		return zAdd(key, ScoredValueUtils.fromBinaryMap(members), zAddArgs, args);
	}

	private Long zAdd(final byte[] key, final ScoredValue<byte[]>[] scoredValues, final CommandArguments args) {
		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.ZADD, (cmd)->cmd.zadd(key, scoredValues),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.ZADD, (cmd)->cmd.zadd(key, scoredValues),
					(v)->v)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.ZADD, (cmd)->cmd.zadd(key, scoredValues), (v)->v)
					.run(args);
		}
	}

	private Long zAdd(final byte[] key, final ScoredValue<byte[]>[] scoredValues, final ZAddArgs zAddArgs,
					  final CommandArguments args) {
		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.ZADD,
					(cmd)->cmd.zadd(key, zAddArgs, scoredValues), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.ZADD,
					(cmd)->cmd.zadd(key, zAddArgs, scoredValues), (v)->v)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.ZADD, (cmd)->cmd.zadd(key, zAddArgs, scoredValues),
					(v)->v)
					.run(args);
		}
	}

	private <V> List<V> zDiff(final CommandArguments args) {
		if(isPipeline()){
			return new LettucePipelineCommand<List<V>, List<V>>(client, Command.ZDIFF)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<List<V>, List<V>>(client, Command.ZDIFF)
					.run(args);
		}else{
			return new LettuceCommand<List<V>, List<V>>(client, Command.ZDIFF)
					.run(args);
		}
	}

	private Long zDiffStore(final CommandArguments args) {
		if(isPipeline()){
			return new LettucePipelineCommand<Long, Long>(client, Command.ZDIFFSTORE)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<Long, Long>(client, Command.ZDIFFSTORE)
					.run(args);
		}else{
			return new LettuceCommand<Long, Long>(client, Command.ZDIFFSTORE)
					.run(args);
		}
	}

	private <V> List<V> zInter(final CommandArguments args) {
		if(isPipeline()){
			return new LettucePipelineCommand<List<V>, List<V>>(client, Command.ZINTER)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<List<V>, List<V>>(client, Command.ZINTER)
					.run(args);
		}else{
			return new LettuceCommand<List<V>, List<V>>(client, Command.ZINTER)
					.run(args);
		}
	}

	private Long zInterStore(final byte[] destKey, final byte[][] keys, final ZStoreArgs zStoreArgs,
							 final CommandArguments args) {
		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.ZINTERSTORE,
					(cmd)->cmd.zinterstore(destKey, zStoreArgs, keys), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.ZINTERSTORE,
					(cmd)->cmd.zinterstore(destKey, zStoreArgs, keys), (v)->v)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.ZINTERSTORE,
					(cmd)->cmd.zinterstore(destKey, zStoreArgs, keys), (v)->v)
					.run(args);
		}
	}

	private List<Double> zMScore(final CommandArguments args) {
		if(isPipeline()){
			return new LettucePipelineCommand<List<Double>, List<Double>>(client, Command.ZMSCORE)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<List<Double>, List<Double>>(client, Command.ZMSCORE)
					.run(args);
		}else{
			return new LettuceCommand<List<Double>, List<Double>>(client, Command.ZMSCORE)
					.run(args);
		}
	}

	private <V> V zRandMember(final CommandArguments args) {
		if(isPipeline()){
			return new LettucePipelineCommand<V, V>(client, Command.ZRANDMEMBER)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<V, V>(client, Command.ZRANDMEMBER)
					.run(args);
		}else{
			return new LettuceCommand<V, V>(client, Command.ZRANDMEMBER)
					.run(args);
		}
	}

	private <V> List<V> zRange(final byte[] key, final long start, final long end,
							   final Converter<List<byte[]>, List<V>> converter, final CommandArguments args) {
		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.ZRANGE, (cmd)->cmd.zrange(key, start, end),
					converter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.ZRANGE, (cmd)->cmd.zrange(key, start, end),
					converter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.ZRANGE, (cmd)->cmd.zrange(key, start, end), converter)
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
			return new LettucePipelineCommand<>(client, Command.ZRANGEBYLEX, (cmd)->cmd.zrangebylex(key, range),
					converter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.ZRANGEBYLEX,
					(cmd)->cmd.zrangebylex(key, range), converter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.ZRANGEBYLEX, (cmd)->cmd.zrangebylex(key, range),
					converter)
					.run(args);
		}
	}

	private <V> List<V> zRangeByLex(final byte[] key, final byte[] min, final byte[] max, final long offset,
									final int count, final Converter<List<byte[]>, List<V>> converter,
									final CommandArguments args) {
		return zRangeByLex(key, Range.create(min, max), Limit.create(offset, count), converter, args);
	}

	private <V> List<V> zRangeByLex(final byte[] key, final Range<byte[]> range, final Limit limit,
									final Converter<List<byte[]>, List<V>> converter, final CommandArguments args) {
		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.ZRANGEBYLEX,
					(cmd)->cmd.zrangebylex(key, range, limit), converter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.ZRANGEBYLEX,
					(cmd)->cmd.zrangebylex(key, range, limit), converter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.ZRANGEBYLEX, (cmd)->cmd.zrangebylex(key, range, limit),
					converter)
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
			return new LettucePipelineCommand<>(client, Command.ZRANGEBYSCORE,
					(cmd)->cmd.zrangebyscore(key, range), converter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.ZRANGEBYSCORE,
					(cmd)->cmd.zrangebyscore(key, range), converter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.ZRANGEBYSCORE, (cmd)->cmd.zrangebyscore(key, range),
					converter)
					.run(args);
		}
	}

	private <V> List<V> zRangeByScore(final byte[] key, final double min, final double max, final long offset,
									  final int count, final Converter<List<byte[]>, List<V>> converter,
									  final CommandArguments args) {
		return zRangeByScore(key, Range.create(min, max), Limit.create(offset, count), converter, args);
	}

	private <V> List<V> zRangeByScore(final byte[] key, final Range<Double> range, final Limit limit,
									  final Converter<List<byte[]>, List<V>> converter, final CommandArguments args) {
		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.ZRANGEBYSCORE,
					(cmd)->cmd.zrangebyscore(key, range, limit), converter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.ZRANGEBYSCORE,
					(cmd)->cmd.zrangebyscore(key, range, limit), converter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.ZRANGEBYSCORE,
					(cmd)->cmd.zrangebyscore(key, range, limit), converter)
					.run(args);
		}
	}

	private Long zRangeStore(final CommandArguments args) {
		if(isPipeline()){
			return new LettucePipelineCommand<Long, Long>(client, Command.ZRANGESTORE)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<Long, Long>(client, Command.ZRANGESTORE)
					.run(args);
		}else{
			return new LettuceCommand<Long, Long>(client, Command.ZRANGESTORE)
					.run(args);
		}
	}

	private <V> List<V> zRevRange(final byte[] key, final long start, final long end,
								  final Converter<List<byte[]>, List<V>> converter, final CommandArguments args) {
		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.ZREVRANGE,
					(cmd)->cmd.zrevrange(key, start, end), converter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.ZREVRANGE,
					(cmd)->cmd.zrevrange(key, start, end), converter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.ZREVRANGE, (cmd)->cmd.zrevrange(key, start, end),
					converter)
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
			return new LettucePipelineCommand<>(client, Command.ZREVRANGEBYLEX,
					(cmd)->cmd.zrevrangebylex(key, range), converter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.ZREVRANGEBYLEX,
					(cmd)->cmd.zrevrangebylex(key, range), converter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.ZREVRANGEBYLEX, (cmd)->cmd.zrevrangebylex(key, range),
					converter)
					.run(args);
		}
	}

	private <V> List<V> zRevRangeByLex(final byte[] key, final byte[] min, final byte[] max, final long offset,
									   final int count, final Converter<List<byte[]>, List<V>> converter,
									   final CommandArguments args) {
		return zRevRangeByLex(key, Range.create(min, max), Limit.create(offset, count), converter, args);
	}

	private <V> List<V> zRevRangeByLex(final byte[] key, final Range<byte[]> range, final Limit limit,
									   final Converter<List<byte[]>, List<V>> converter, final CommandArguments args) {
		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.ZREVRANGEBYLEX,
					(cmd)->cmd.zrevrangebylex(key, range, limit), converter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.ZREVRANGEBYLEX,
					(cmd)->cmd.zrevrangebylex(key, range, limit), converter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.ZREVRANGEBYLEX,
					(cmd)->cmd.zrevrangebylex(key, range, limit), converter)
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
			return new LettucePipelineCommand<>(client, Command.ZREVRANGEBYSCORE,
					(cmd)->cmd.zrevrangebyscore(key, range), converter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.ZREVRANGEBYSCORE,
					(cmd)->cmd.zrevrangebyscore(key, range), converter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.ZREVRANGEBYSCORE,
					(cmd)->cmd.zrevrangebyscore(key, range), converter)
					.run(args);
		}
	}

	private <V> List<V> zRevRangeByScore(final byte[] key, final double min, final double max, final long offset,
										 final int count, final Converter<List<byte[]>, List<V>> converter,
										 final CommandArguments args) {
		return zRevRangeByScore(key, Range.create(min, max), Limit.create(offset, count), converter, args);
	}

	private <V> List<V> zRevRangeByScore(final byte[] key, final Range<Double> range, final Limit limit,
										 final Converter<List<byte[]>, List<V>> converter,
										 final CommandArguments args) {
		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.ZREVRANGEBYSCORE,
					(cmd)->cmd.zrevrangebyscore(key, range, limit), converter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.ZREVRANGEBYSCORE,
					(cmd)->cmd.zrevrangebyscore(key, range, limit), converter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.ZREVRANGEBYSCORE,
					(cmd)->cmd.zrevrangebyscore(key, range, limit), converter)
					.run(args);
		}
	}

	private ScanResult<List<Tuple>> zScan(final byte[] key, final ScanCursor cursor,
										  final Converter<ScoredValueScanCursor<byte[]>, ScanResult<List<Tuple>>> converter,
										  final CommandArguments args) {
		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.ZREVRANK, (cmd)->cmd.zscan(key, cursor),
					converter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.ZREVRANK, (cmd)->cmd.zscan(key, cursor),
					converter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.ZREVRANK, (cmd)->cmd.zscan(key, cursor), converter)
					.run(args);
		}
	}

	private ScanResult<List<Tuple>> zScan(final byte[] key, final ScanCursor cursor, final ScanArgs scanArgs,
										  final Converter<ScoredValueScanCursor<byte[]>, ScanResult<List<Tuple>>> converter,
										  final CommandArguments args) {
		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.ZSCAN, (cmd)->cmd.zscan(key, cursor, scanArgs),
					converter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.ZSCAN,
					(cmd)->cmd.zscan(key, cursor, scanArgs), converter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.ZSCAN, (cmd)->cmd.zscan(key, cursor, scanArgs),
					converter)
					.run(args);
		}
	}

	private <V> List<V> zUnion(final CommandArguments args) {
		if(isPipeline()){
			return new LettucePipelineCommand<List<V>, List<V>>(client, Command.ZUNION)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<List<V>, List<V>>(client, Command.ZUNION)
					.run(args);
		}else{
			return new LettuceCommand<List<V>, List<V>>(client, Command.ZUNION)
					.run(args);
		}
	}

	private Long zUnionStore(final byte[] destKey, final byte[][] keys, final ZStoreArgs zStoreArgs,
							 final CommandArguments args) {
		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.ZUNIONSTORE,
					(cmd)->cmd.zunionstore(destKey, zStoreArgs, keys), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.ZUNIONSTORE,
					(cmd)->cmd.zunionstore(destKey, zStoreArgs, keys), (v)->v)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.ZUNIONSTORE,
					(cmd)->cmd.zunionstore(destKey, zStoreArgs, keys), (v)->v)
					.run(args);
		}
	}

}
