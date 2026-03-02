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
 * | Copyright @ 2013-2026 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.client.lettuce.command;

import com.buession.core.converter.Converter;
import com.buession.core.converter.ListConverter;
import com.buession.core.utils.NumberUtils;
import com.buession.lang.KeyValue;
import com.buession.redis.client.lettuce.LettuceRedisClient;
import com.buession.redis.core.Aggregate;
import com.buession.redis.core.Keyword;
import com.buession.redis.core.MinMax;
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.Tuple;
import com.buession.redis.core.command.Command;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.SortedSetCommands;
import com.buession.redis.core.command.args.ZAddArgument;
import com.buession.redis.core.command.args.ZRangeArgument;
import com.buession.redis.core.internal.convert.lettuce.params.ZAddArgumentConverter;
import com.buession.redis.core.internal.convert.lettuce.response.KeyValueConverter;
import com.buession.redis.core.internal.convert.lettuce.response.ScanCursorConverter;
import com.buession.redis.core.internal.convert.lettuce.response.ScoredValueTupleConverter;
import com.buession.redis.core.internal.lettuce.LettuceScanArgs;
import com.buession.redis.core.internal.lettuce.LettuceScanCursor;
import com.buession.redis.core.internal.lettuce.LettuceZAggregateArgs;
import com.buession.redis.core.internal.lettuce.LettuceZPopArgs;
import com.buession.redis.core.internal.lettuce.LettuceZStoreArgs;
import com.buession.redis.utils.SafeEncoder;
import io.lettuce.core.Limit;
import io.lettuce.core.Range;
import io.lettuce.core.ScoredValue;

import java.util.List;

/**
 * Lettuce 有序集合命令
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public final class LettuceSortedSetCommands extends AbstractLettuceRedisCommands implements SortedSetCommands {

	public LettuceSortedSetCommands(final LettuceRedisClient client) {
		super(client);
	}

	@Override
	public KeyValue<String, List<Tuple>> bzMPop(final String[] keys, final int timeout, final MinMax minMax) {
		final CommandArguments args = CommandArguments.create(timeout).add(keys.length).add(keys).add(minMax);
		return bzMPop(SafeEncoder.encode(keys), timeout, minMax, 1, SafeEncoder::encode, args);
	}

	@Override
	public KeyValue<byte[], List<Tuple>> bzMPop(final byte[][] keys, final int timeout, final MinMax minMax) {
		final CommandArguments args = CommandArguments.create(timeout).add(keys.length).add(keys).add(minMax);
		return bzMPop(keys, timeout, minMax, 1, (k)->k, args);
	}

	@Override
	public KeyValue<String, List<Tuple>> bzMPop(final String[] keys, final int timeout, final MinMax minMax,
												final int count) {
		final CommandArguments args = CommandArguments.create(timeout).add(keys.length).add(keys).add(minMax)
				.add("COUNT", count);
		return bzMPop(SafeEncoder.encode(keys), timeout, minMax, count, SafeEncoder::encode, args);
	}

	@Override
	public KeyValue<byte[], List<Tuple>> bzMPop(final byte[][] keys, final int timeout, final MinMax minMax,
												final int count) {
		final CommandArguments args = CommandArguments.create(timeout).add(keys.length).add(keys).add(minMax)
				.add("COUNT", count);
		return bzMPop(keys, timeout, minMax, count, (k)->k, args);
	}

	@Override
	public KeyValue<String, Tuple> bzPopMax(final String[] keys, final int timeout) {
		final CommandArguments args = CommandArguments.create(keys).add(timeout);
		return executeCommand(Command.BZPOPMAX, args, (cmd)->cmd.bzpopmax(timeout, SafeEncoder.encode(keys)),
				new KeyValueConverter<>(SafeEncoder::encode, new ScoredValueTupleConverter()));
	}

	@Override
	public KeyValue<byte[], Tuple> bzPopMax(final byte[][] keys, final int timeout) {
		final CommandArguments args = CommandArguments.create(keys).add(timeout);
		return executeCommand(Command.BZPOPMAX, args, (cmd)->cmd.bzpopmax(timeout, keys),
				new KeyValueConverter<>((k)->k, new ScoredValueTupleConverter()));
	}

	@Override
	public com.buession.lang.KeyValue<String, Tuple> bzPopMin(final String[] keys, final int timeout) {
		final CommandArguments args = CommandArguments.create(keys).add(timeout);
		return executeCommand(Command.BZPOPMIN, args, (cmd)->cmd.bzpopmin(timeout, SafeEncoder.encode(keys)),
				new KeyValueConverter<>(SafeEncoder::encode, new ScoredValueTupleConverter()));
	}

	@Override
	public com.buession.lang.KeyValue<byte[], Tuple> bzPopMin(final byte[][] keys, final int timeout) {
		final CommandArguments args = CommandArguments.create(keys).add(timeout);
		return executeCommand(Command.BZPOPMIN, args, (cmd)->cmd.bzpopmin(timeout, keys),
				new KeyValueConverter<>((k)->k, new ScoredValueTupleConverter()));
	}

	@Override
	public Long zAdd(final String key, final Tuple... members) {
		final CommandArguments args = CommandArguments.create(key).add(members);
		return zAdd(SafeEncoder.encode(key), members, args);
	}

	@Override
	public Long zAdd(final byte[] key, final Tuple... members) {
		final CommandArguments args = CommandArguments.create(key).add(members);
		return zAdd(key, members, args);
	}

	@Override
	public Long zAdd(final String key, final Tuple[] members, final ZAddArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(argument).add(members);
		return zAdd(SafeEncoder.encode(key), members, argument, args);
	}

	@Override
	public Long zAdd(final byte[] key, final Tuple[] members, final ZAddArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(argument).add(members);
		return zAdd(key, members, argument, args);
	}

	@Override
	public Long zCard(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.ZCARD, args, (cmd)->cmd.zcard(SafeEncoder.encode(key)), (v)->v);
	}

	@Override
	public Long zCard(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.ZCARD, args, (cmd)->cmd.zcard(key), (v)->v);
	}

	@Override
	public Long zCount(final String key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);
		return executeCommand(Command.ZCOUNT, args, (cmd)->cmd.zcount(SafeEncoder.encode(key), Range.create(min, max)),
				(v)->v);
	}

	@Override
	public Long zCount(final byte[] key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);
		return executeCommand(Command.ZCOUNT, args, (cmd)->cmd.zcount(key, Range.create(min, max)), (v)->v);
	}

	@Override
	public List<String> zDiff(final String... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return executeCommand(Command.ZDIFF, args, (cmd)->cmd.zdiff(SafeEncoder.encode(keys)),
				new ListConverter<>(SafeEncoder::encode));
	}

	@Override
	public List<byte[]> zDiff(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return executeCommand(Command.ZDIFF, args, (cmd)->cmd.zdiff(keys), (v)->v);
	}

	@Override
	public List<Tuple> zDiffWithScores(final String... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return executeCommand(Command.ZDIFF, args, (cmd)->cmd.zdiffWithScores(SafeEncoder.encode(keys)),
				new ListConverter<>(new ScoredValueTupleConverter()));
	}

	@Override
	public List<Tuple> zDiffWithScores(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return executeCommand(Command.ZDIFF, args, (cmd)->cmd.zdiffWithScores(keys),
				new ListConverter<>(new ScoredValueTupleConverter()));
	}

	@Override
	public Long zDiffStore(final String destKey, final String... keys) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys);
		return executeCommand(Command.ZDIFFSTORE, args,
				(cmd)->cmd.zdiffstore(SafeEncoder.encode(destKey), SafeEncoder.encode(keys)), (v)->v);
	}

	@Override
	public Long zDiffStore(final byte[] destKey, final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys);
		return executeCommand(Command.ZDIFFSTORE, args, (cmd)->cmd.zdiffstore(destKey, keys), (v)->v);
	}

	@Override
	public Double zIncrBy(final String key, final double increment, final String member) {
		final CommandArguments args = CommandArguments.create(key).add(increment).add(member);
		return executeCommand(Command.ZINCRBY, args,
				(cmd)->cmd.zincrby(SafeEncoder.encode(key), increment, SafeEncoder.encode(member)), (v)->v);
	}

	@Override
	public Double zIncrBy(final byte[] key, final double increment, final byte[] member) {
		final CommandArguments args = CommandArguments.create(key).add(increment).add(member);
		return executeCommand(Command.ZINCRBY, args, (cmd)->cmd.zincrby(key, increment, member), (v)->v);
	}

	@Override
	public List<String> zInter(final String... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return executeCommand(Command.ZINTER, args, (cmd)->cmd.zinter(SafeEncoder.encode(keys)),
				new ListConverter<>(SafeEncoder::encode));
	}

	@Override
	public List<byte[]> zInter(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return executeCommand(Command.ZINTER, args, (cmd)->cmd.zinter(keys), (v)->v);
	}

	@Override
	public List<String> zInter(final String[] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create(keys).add("AGGREGATE", aggregate);
		return zInter(keys, new LettuceZAggregateArgs(aggregate), args);
	}

	@Override
	public List<byte[]> zInter(final byte[][] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create(keys).add("AGGREGATE", aggregate);
		return zInter(keys, new LettuceZAggregateArgs(aggregate), args);
	}

	@Override
	public List<String> zInter(final String[] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add("WEIGHTS", weights);
		return zInter(keys, new LettuceZAggregateArgs(weights), args);
	}

	@Override
	public List<byte[]> zInter(final byte[][] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add("WEIGHTS", weights);
		return zInter(keys, new LettuceZAggregateArgs(weights), args);
	}

	@Override
	public List<String> zInter(final String[] keys, final Aggregate aggregate, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add("AGGREGATE", aggregate).add("WEIGHTS", weights);
		return zInter(keys, new LettuceZAggregateArgs(aggregate, weights), args);
	}

	@Override
	public List<byte[]> zInter(final byte[][] keys, final Aggregate aggregate, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add("AGGREGATE", aggregate).add("WEIGHTS", weights);
		return zInter(keys, new LettuceZAggregateArgs(aggregate, weights), args);
	}

	@Override
	public List<Tuple> zInterWithScores(final String... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return executeCommand(Command.ZINTER, args, (cmd)->cmd.zinterWithScores(SafeEncoder.encode(keys)),
				new ListConverter<>(new ScoredValueTupleConverter()));
	}

	@Override
	public List<Tuple> zInterWithScores(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return executeCommand(Command.ZINTER, args, (cmd)->cmd.zinterWithScores(keys),
				new ListConverter<>(new ScoredValueTupleConverter()));
	}

	@Override
	public List<Tuple> zInterWithScores(final String[] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create(keys).add("AGGREGATE", aggregate);
		return zInterWithScores(keys, new LettuceZAggregateArgs(aggregate), args);
	}

	@Override
	public List<Tuple> zInterWithScores(final byte[][] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create(keys).add("AGGREGATE", aggregate);
		return zInterWithScores(keys, new LettuceZAggregateArgs(aggregate), args);
	}

	@Override
	public List<Tuple> zInterWithScores(final String[] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add("WEIGHTS", weights);
		return zInterWithScores(keys, new LettuceZAggregateArgs(weights), args);
	}

	@Override
	public List<Tuple> zInterWithScores(final byte[][] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add("WEIGHTS", weights);
		return zInterWithScores(keys, new LettuceZAggregateArgs(weights), args);
	}

	@Override
	public List<Tuple> zInterWithScores(final String[] keys, final Aggregate aggregate, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add("AGGREGATE", aggregate).add("WEIGHTS", weights);
		return zInterWithScores(keys, new LettuceZAggregateArgs(aggregate), args);
	}

	@Override
	public List<Tuple> zInterWithScores(final byte[][] keys, final Aggregate aggregate, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add("AGGREGATE", aggregate).add("WEIGHTS", weights);
		return zInterWithScores(keys, new LettuceZAggregateArgs(aggregate), args);
	}

	@Override
	public long zInterCard(final String... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return executeCommand(Command.ZINTERCARD, args, (cmd)->cmd.zintercard(SafeEncoder.encode(keys)), (v)->v);
	}

	@Override
	public long zInterCard(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return executeCommand(Command.ZINTERCARD, args, (cmd)->cmd.zintercard(keys), (v)->v);
	}

	@Override
	public long zInterCard(final String[] keys, final int limit) {
		final CommandArguments args = CommandArguments.create(keys).add(Keyword.Common.LIMIT, limit);
		return executeCommand(Command.ZINTERCARD, args, (cmd)->cmd.zintercard(limit, SafeEncoder.encode(keys)), (v)->v);
	}

	@Override
	public long zInterCard(final byte[][] keys, final int limit) {
		final CommandArguments args = CommandArguments.create(keys).add(Keyword.Common.LIMIT, limit);
		return executeCommand(Command.ZINTERCARD, args, (cmd)->cmd.zintercard(limit, keys), (v)->v);
	}

	@Override
	public Long zInterStore(final String destKey, final String... keys) {
		return zInterStore(SafeEncoder.encode(destKey), SafeEncoder.encode(keys));
	}

	@Override
	public Long zInterStore(final byte[] destKey, final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys.length).add(keys);
		return executeCommand(Command.ZINTERSTORE, args, (cmd)->cmd.zinterstore(destKey, keys), (v)->v);
	}

	@Override
	public Long zInterStore(final String destKey, final String[] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys.length).add(keys)
				.add("AGGREGATE", aggregate);
		return zInterStore(SafeEncoder.encode(destKey), SafeEncoder.encode(keys), new LettuceZStoreArgs(aggregate),
				args);
	}

	@Override
	public Long zInterStore(final byte[] destKey, final byte[][] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys.length).add(keys)
				.add("AGGREGATE", aggregate);
		return zInterStore(destKey, keys, new LettuceZStoreArgs(aggregate), args);
	}

	@Override
	public Long zInterStore(final String destKey, final String[] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys.length).add(keys)
				.add("WEIGHTS", weights);
		return zInterStore(SafeEncoder.encode(destKey), SafeEncoder.encode(keys), new LettuceZStoreArgs(weights), args);
	}

	@Override
	public Long zInterStore(final byte[] destKey, final byte[][] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys.length).add(keys)
				.add("WEIGHTS", weights);
		return zInterStore(destKey, keys, new LettuceZStoreArgs(weights), args);
	}

	@Override
	public Long zInterStore(final String destKey, final String[] keys, final Aggregate aggregate,
							final double... weights) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys.length).add(keys)
				.add("AGGREGATE", aggregate).add("WEIGHTS", weights);
		return zInterStore(SafeEncoder.encode(destKey), SafeEncoder.encode(keys),
				new LettuceZStoreArgs(aggregate, weights), args);
	}

	@Override
	public Long zInterStore(final byte[] destKey, final byte[][] keys, final Aggregate aggregate,
							final double... weights) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys.length).add(keys)
				.add("AGGREGATE", aggregate).add("WEIGHTS", weights);
		return zInterStore(destKey, keys, new LettuceZStoreArgs(aggregate, weights), args);
	}

	@Override
	public Long zLexCount(final String key, final double min, final double max) {
		return zLexCount(SafeEncoder.encode(key), min, max);
	}

	@Override
	public Long zLexCount(final byte[] key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);
		return executeCommand(Command.ZLEXCOUNT, args,
				(cmd)->cmd.zlexcount(key, Range.create(NumberUtils.double2bytes(min), NumberUtils.double2bytes(max))),
				(v)->v);
	}

	@Override
	public KeyValue<String, List<Tuple>> zMPop(final String[] keys, final MinMax minMax) {
		final CommandArguments args = CommandArguments.create(keys.length).add(keys).add(minMax);
		return zMPop(SafeEncoder.encode(keys), minMax, 1, SafeEncoder::encode, args);
	}

	@Override
	public KeyValue<byte[], List<Tuple>> zMPop(final byte[][] keys, final MinMax minMax) {
		final CommandArguments args = CommandArguments.create(keys.length).add(keys).add(minMax);
		return zMPop(keys, minMax, 1, (k)->k, args);
	}

	@Override
	public KeyValue<String, List<Tuple>> zMPop(final String[] keys, final MinMax minMax, final int count) {
		final CommandArguments args = CommandArguments.create(keys.length).add(keys).add(minMax).add("COUNT", count);
		return zMPop(SafeEncoder.encode(keys), minMax, count, SafeEncoder::encode, args);
	}

	@Override
	public KeyValue<byte[], List<Tuple>> zMPop(final byte[][] keys, final MinMax minMax, final int count) {
		final CommandArguments args = CommandArguments.create(keys.length).add(keys).add(minMax).add("COUNT", count);
		return zMPop(keys, minMax, count, (k)->k, args);
	}

	@Override
	public List<Double> zMScore(final String key, final String... members) {
		final CommandArguments args = CommandArguments.create(key).add(members);
		return executeCommand(Command.ZMSCORE, args,
				(cmd)->cmd.zmscore(SafeEncoder.encode(key), SafeEncoder.encode(members)), (v)->v);
	}

	@Override
	public List<Double> zMScore(final byte[] key, final byte[]... members) {
		final CommandArguments args = CommandArguments.create(key).add(members);
		return executeCommand(Command.ZMSCORE, args, (cmd)->cmd.zmscore(key, members), (v)->v);
	}

	@Override
	public Tuple zPopMax(final String key) {
		return zPopMax(SafeEncoder.encode(key));
	}

	@Override
	public Tuple zPopMax(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.ZPOPMAX, args, (cmd)->cmd.zpopmax(key), new ScoredValueTupleConverter());
	}

	@Override
	public List<Tuple> zPopMax(final String key, final int count) {
		return zPopMax(SafeEncoder.encode(key), count);
	}

	@Override
	public List<Tuple> zPopMax(final byte[] key, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(count);
		return executeCommand(Command.ZPOPMAX, args, (cmd)->cmd.zpopmax(key, count),
				new ListConverter<>(new ScoredValueTupleConverter()));
	}

	@Override
	public Tuple zPopMin(final String key) {
		return zPopMin(SafeEncoder.encode(key));
	}

	@Override
	public Tuple zPopMin(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.ZPOPMIN, args, (cmd)->cmd.zpopmin(key), new ScoredValueTupleConverter());
	}

	@Override
	public List<Tuple> zPopMin(final String key, final int count) {
		return zPopMin(SafeEncoder.encode(key), count);
	}

	@Override
	public List<Tuple> zPopMin(final byte[] key, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(count);
		return executeCommand(Command.ZPOPMIN, args, (cmd)->cmd.zpopmin(key, count),
				new ListConverter<>(new ScoredValueTupleConverter()));
	}

	@Override
	public String zRandMember(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.ZRANDMEMBER, args, (cmd)->cmd.zrandmember(SafeEncoder.encode(key)),
				SafeEncoder::encode);
	}

	@Override
	public byte[] zRandMember(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.ZRANDMEMBER, args, (cmd)->cmd.zrandmember(key), (v)->v);
	}

	@Override
	public List<String> zRandMember(final String key, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(count);
		return executeCommand(Command.ZRANDMEMBER, args, (cmd)->cmd.zrandmember(SafeEncoder.encode(key), count),
				new ListConverter<>(SafeEncoder::encode));
	}

	@Override
	public List<byte[]> zRandMember(final byte[] key, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(count);
		return executeCommand(Command.ZRANDMEMBER, args, (cmd)->cmd.zrandmember(key, count), (v)->v);
	}

	@Override
	public Tuple zRandMemberWithScores(final String key) {
		return zRandMemberWithScores(SafeEncoder.encode(key));
	}

	@Override
	public Tuple zRandMemberWithScores(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.ZRANDMEMBER, args, (cmd)->cmd.zrandmemberWithScores(key),
				new ScoredValueTupleConverter());
	}

	@Override
	public List<Tuple> zRandMemberWithScores(final String key, final int count) {
		return zRandMemberWithScores(SafeEncoder.encode(key), count);
	}

	@Override
	public List<Tuple> zRandMemberWithScores(final byte[] key, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(count);
		return executeCommand(Command.ZRANDMEMBER, args, (cmd)->cmd.zrandmemberWithScores(key, count),
				new ListConverter<>(new ScoredValueTupleConverter()));
	}

	@Override
	public List<String> zRange(final String key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end);
		return executeCommand(Command.ZRANGE, args, (cmd)->cmd.zrange(SafeEncoder.encode(key), start, end),
				new ListConverter<>(SafeEncoder::encode));
	}

	@Override
	public List<byte[]> zRange(final byte[] key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end);
		return executeCommand(Command.ZRANGE, args, (cmd)->cmd.zrange(key, start, end), (v)->v);
	}

	@Override
	public List<String> zRange(final String key, final long start, final long end, final ZRangeArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end).add(argument);
		return executeCommand(Command.ZRANGE, args, (cmd)->cmd.zrange(SafeEncoder.encode(key), start, end),
				new ListConverter<>(SafeEncoder::encode));
	}

	@Override
	public List<byte[]> zRange(final byte[] key, final long start, final long end, final ZRangeArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end).add(argument);
		return executeCommand(Command.ZRANGE, args, (cmd)->cmd.zrange(key, start, end), (v)->v);
	}

	@Override
	public List<String> zRange(final String key, final long start, final long end, final int offset, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end).add(Keyword.Common.LIMIT)
				.add(offset).add(count);
		return executeCommand(Command.ZRANGE, args, (cmd)->cmd.zrange(SafeEncoder.encode(key), start, end),
				new ListConverter<>(SafeEncoder::encode));
	}

	@Override
	public List<byte[]> zRange(final byte[] key, final long start, final long end, final int offset, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end).add(Keyword.Common.LIMIT)
				.add(offset).add(count);
		return executeCommand(Command.ZRANGE, args, (cmd)->cmd.zrange(key, start, end), (v)->v);
	}

	@Override
	public List<String> zRange(final String key, final long start, final long end, final ZRangeArgument argument,
							   final int offset, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end).add(argument)
				.add(Keyword.Common.LIMIT).add(offset).add(count);
		return executeCommand(Command.ZRANGE, args, (cmd)->cmd.zrange(SafeEncoder.encode(key), start, end),
				new ListConverter<>(SafeEncoder::encode));
	}

	@Override
	public List<byte[]> zRange(final byte[] key, final long start, final long end, final ZRangeArgument argument,
							   final int offset, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end).add(argument)
				.add(Keyword.Common.LIMIT).add(offset).add(count);
		return executeCommand(Command.ZRANGE, args, (cmd)->cmd.zrange(key, start, end), (v)->v);
	}

	@Override
	public List<Tuple> zRangeWithScores(final String key, final long start, final long end) {
		return zRangeWithScores(SafeEncoder.encode(key), start, end);
	}

	@Override
	public List<Tuple> zRangeWithScores(final byte[] key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end);
		return executeCommand(Command.ZRANGE, args, (cmd)->cmd.zrangeWithScores(key, start, end),
				new ListConverter<>(new ScoredValueTupleConverter()));
	}

	@Override
	public List<Tuple> zRangeWithScores(final String key, final long start, final long end,
										final ZRangeArgument argument) {
		return zRangeWithScores(SafeEncoder.encode(key), start, end, argument);
	}

	@Override
	public List<Tuple> zRangeWithScores(final byte[] key, final long start, final long end,
										final ZRangeArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end).add(argument);
		return executeCommand(Command.ZRANGE, args, (cmd)->cmd.zrangeWithScores(key, start, end),
				new ListConverter<>(new ScoredValueTupleConverter()));
	}

	@Override
	public List<Tuple> zRangeWithScores(final String key, final long start, final long end, final int offset,
										final int count) {
		return zRangeWithScores(SafeEncoder.encode(key), start, end, offset, count);
	}

	@Override
	public List<Tuple> zRangeWithScores(final byte[] key, final long start, final long end, final int offset,
										final int count) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end).add(Keyword.Common.LIMIT)
				.add(offset).add(count);
		return executeCommand(Command.ZRANGE, args, (cmd)->cmd.zrangeWithScores(key, start, end),
				new ListConverter<>(new ScoredValueTupleConverter()));
	}

	@Override
	public List<Tuple> zRangeWithScores(final String key, final long start, final long end,
										final ZRangeArgument argument, final int offset, final int count) {
		return zRangeWithScores(SafeEncoder.encode(key), start, end, argument, offset, count);
	}

	@Override
	public List<Tuple> zRangeWithScores(final byte[] key, final long start, final long end,
										final ZRangeArgument argument, final int offset, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end).add(argument)
				.add(Keyword.Common.LIMIT).add(offset).add(count);
		return executeCommand(Command.ZRANGE, args, (cmd)->cmd.zrangeWithScores(key, start, end),
				new ListConverter<>(new ScoredValueTupleConverter()));
	}

	@Override
	public List<String> zRangeByLex(final String key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);
		return executeCommand(Command.ZRANGEBYLEX, args, (cmd)->cmd.zrangebylex(SafeEncoder.encode(key),
						Range.create(NumberUtils.double2bytes(min), NumberUtils.double2bytes(max))),
				new ListConverter<>(SafeEncoder::encode));
	}

	@Override
	public List<byte[]> zRangeByLex(final byte[] key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);
		return executeCommand(Command.ZRANGEBYLEX, args,
				(cmd)->cmd.zrangebylex(key, Range.create(NumberUtils.double2bytes(min), NumberUtils.double2bytes(max))),
				(v)->v);
	}

	@Override
	public List<String> zRangeByLex(final String key, final double min, final double max, final int offset,
									final int count) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max).add(Keyword.Common.LIMIT)
				.add(offset).add(count);
		return executeCommand(Command.ZRANGEBYLEX, args, (cmd)->cmd.zrangebylex(SafeEncoder.encode(key),
				Range.create(NumberUtils.double2bytes(min), NumberUtils.double2bytes(max)),
				Limit.create(offset, count)), new ListConverter<>(SafeEncoder::encode));
	}

	@Override
	public List<byte[]> zRangeByLex(final byte[] key, final double min, final double max, final int offset,
									final int count) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max).add(Keyword.Common.LIMIT)
				.add(offset).add(count);
		return executeCommand(Command.ZRANGEBYLEX, args,
				(cmd)->cmd.zrangebylex(key, Range.create(NumberUtils.double2bytes(min), NumberUtils.double2bytes(max)),
						Limit.create(offset, count)), (v)->v);
	}

	@Override
	public List<String> zRangeByScore(final String key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);
		return executeCommand(Command.ZRANGEBYSCORE, args,
				(cmd)->cmd.zrangebyscore(SafeEncoder.encode(key), Range.create(min, max)),
				new ListConverter<>(SafeEncoder::encode));
	}

	@Override
	public List<byte[]> zRangeByScore(final byte[] key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);
		return executeCommand(Command.ZRANGEBYSCORE, args, (cmd)->cmd.zrangebyscore(key, Range.create(min, max)),
				(v)->v);
	}

	@Override
	public List<String> zRangeByScore(final String key, final double min, final double max, final int offset,
									  final int count) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max).add(Keyword.Common.LIMIT)
				.add(offset).add(count);
		return executeCommand(Command.ZRANGEBYSCORE, args,
				(cmd)->cmd.zrangebyscore(SafeEncoder.encode(key), Range.create(min, max), Limit.create(offset, count)),
				new ListConverter<>(SafeEncoder::encode));
	}

	@Override
	public List<byte[]> zRangeByScore(final byte[] key, final double min, final double max, final int offset,
									  final int count) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max).add(Keyword.Common.LIMIT)
				.add(offset).add(count);
		return executeCommand(Command.ZRANGEBYSCORE, args,
				(cmd)->cmd.zrangebyscore(key, Range.create(min, max), Limit.create(offset, count)), (v)->v);
	}

	@Override
	public List<Tuple> zRangeByScoreWithScores(final String key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);
		return executeCommand(Command.ZRANGEBYSCORE, args,
				(cmd)->cmd.zrangebyscoreWithScores(SafeEncoder.encode(key), Range.create(min, max)),
				new ListConverter<>(new ScoredValueTupleConverter()));
	}

	@Override
	public List<Tuple> zRangeByScoreWithScores(final byte[] key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);
		return executeCommand(Command.ZRANGEBYSCORE, args,
				(cmd)->cmd.zrangebyscoreWithScores(key, Range.create(min, max)),
				new ListConverter<>(new ScoredValueTupleConverter()));
	}

	@Override
	public List<Tuple> zRangeByScoreWithScores(final String key, final double min, final double max, final int offset,
											   final int count) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max).add(Keyword.Common.LIMIT)
				.add(offset).add(count);
		return executeCommand(Command.ZRANGEBYSCORE, args,
				(cmd)->cmd.zrangebyscoreWithScores(SafeEncoder.encode(key), Range.create(min, max),
						Limit.create(offset, count)), new ListConverter<>(new ScoredValueTupleConverter()));
	}

	@Override
	public List<Tuple> zRangeByScoreWithScores(final byte[] key, final double min, final double max, final int offset,
											   final int count) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max).add(Keyword.Common.LIMIT)
				.add(offset).add(count);
		return executeCommand(Command.ZRANGEBYSCORE, args,
				(cmd)->cmd.zrangebyscoreWithScores(key, Range.create(min, max), Limit.create(offset, count)),
				new ListConverter<>(new ScoredValueTupleConverter()));
	}

	@Override
	public Long zRangeStore(final String destKey, final String key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(destKey).add(key).add(start).add(end);
		return executeCommand(Command.ZRANGESTORE, args,
				(cmd)->cmd.zrangestore(SafeEncoder.encode(destKey), SafeEncoder.encode(key), Range.create(start, end)),
				(v)->v);
	}

	@Override
	public Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(destKey).add(key).add(start).add(end);
		return executeCommand(Command.ZRANGESTORE, args, (cmd)->cmd.zrangestore(destKey, key, Range.create(start, end)),
				(v)->v);
	}

	@Override
	public Long zRangeStore(final String destKey, final String key, final long start, final long end,
							final ZRangeArgument argument) {
		return zRangeStore(SafeEncoder.encode(destKey), SafeEncoder.encode(key), start, end, argument);
	}

	@Override
	public Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end,
							final ZRangeArgument argument) {
		final CommandArguments args = CommandArguments.create(destKey).add(key).add(start).add(end).add(argument);

		if(argument != null && argument.getBy() != null){
			switch(argument.getBy()){
				case BYLEX -> executeCommand(Command.ZRANGESTORE, args, (cmd)->cmd.zrangestorebylex(destKey, key,
								Range.create(NumberUtils.long2bytes(start), NumberUtils.long2bytes(end)), Limit.unlimited()),
						(v)->v);
				case BYSCORE -> executeCommand(Command.ZRANGESTORE, args,
						(cmd)->cmd.zrangestorebyscore(destKey, key, Range.create(start, end), Limit.unlimited()),
						(v)->v);
			}
		}
		return executeCommand(Command.ZRANGESTORE, args, (cmd)->cmd.zrangestore(destKey, key, Range.create(start, end)),
				(v)->v);
	}

	@Override
	public Long zRangeStore(final String destKey, final String key, final long start, final long end, final int offset,
							final int count) {
		final CommandArguments args = CommandArguments.create(destKey).add(key).add(start).add(end)
				.add(Keyword.Common.LIMIT).add(offset).add(count);

		return executeCommand(Command.ZRANGESTORE, args,
				(cmd)->cmd.zrangestore(SafeEncoder.encode(destKey), SafeEncoder.encode(key), Range.create(start, end)),
				(v)->v);
	}

	@Override
	public Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end, final int offset,
							final int count) {
		final CommandArguments args = CommandArguments.create(destKey).add(key).add(start).add(end)
				.add(Keyword.Common.LIMIT).add(offset).add(count);

		return executeCommand(Command.ZRANGESTORE, args, (cmd)->cmd.zrangestore(destKey, key, Range.create(start, end)),
				(v)->v);
	}

	@Override
	public Long zRangeStore(final String destKey, final String key, final long start, final long end,
							final ZRangeArgument argument, final int offset, final int count) {
		return zRangeStore(SafeEncoder.encode(destKey), SafeEncoder.encode(key), start, end, argument, offset, count);
	}

	@Override
	public Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end,
							final ZRangeArgument argument, final int offset, final int count) {
		final CommandArguments args = CommandArguments.create(destKey).add(key).add(start).add(end).add(argument)
				.add(Keyword.Common.LIMIT).add(offset).add(count);

		if(argument != null && argument.getBy() != null){
			switch(argument.getBy()){
				case BYLEX -> executeCommand(Command.ZRANGESTORE, args, (cmd)->cmd.zrangestorebylex(destKey, key,
						Range.create(NumberUtils.long2bytes(start), NumberUtils.long2bytes(end)),
						Limit.create(offset, count)), (v)->v);
				case BYSCORE -> executeCommand(Command.ZRANGESTORE, args,
						(cmd)->cmd.zrangestorebyscore(destKey, key, Range.create(start, end),
								Limit.create(offset, count)), (v)->v);
			}
		}
		return executeCommand(Command.ZRANGESTORE, args, (cmd)->cmd.zrangestore(destKey, key, Range.create(start, end)),
				(v)->v);
	}

	@Override
	public Long zRank(final String key, final String member) {
		return zRank(SafeEncoder.encode(key), SafeEncoder.encode(member));
	}

	@Override
	public Long zRank(final byte[] key, final byte[] member) {
		final CommandArguments args = CommandArguments.create(key).add(member);
		return executeCommand(Command.ZRANK, args, (cmd)->cmd.zrank(key, member), (v)->v);
	}

	@Override
	public Tuple zRankWithScores(final String key, final String member) {
		return zRankWithScores(SafeEncoder.encode(key), SafeEncoder.encode(member));
	}

	@Override
	public Tuple zRankWithScores(final byte[] key, final byte[] member) {
		final CommandArguments args = CommandArguments.create(key).add(member).add(Keyword.Common.WITHSCORE);
		return executeCommand(Command.ZRANK, args, (cmd)->cmd.zrankWithScore(key, member),
				new ScoredValueTupleConverter());
	}

	@Override
	public Long zRem(final String key, final String... members) {
		final CommandArguments args = CommandArguments.create(key).add(members);
		return executeCommand(Command.ZREM, args, (cmd)->cmd.zrem(SafeEncoder.encode(key), SafeEncoder.encode(members)),
				(v)->v);
	}

	@Override
	public Long zRem(final byte[] key, final byte[]... members) {
		final CommandArguments args = CommandArguments.create(key).add(members);
		return executeCommand(Command.ZREM, args, (cmd)->cmd.zrem(key, members), (v)->v);
	}

	@Override
	public Long zRemRangeByLex(final String key, final double min, final double max) {
		return zRemRangeByLex(SafeEncoder.encode(key), min, max);
	}

	@Override
	public Long zRemRangeByLex(final byte[] key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);
		return executeCommand(Command.ZREMRANGEBYLEX, args, (cmd)->cmd.zremrangebylex(key,
				Range.create(NumberUtils.double2bytes(min), NumberUtils.double2bytes(max))), (v)->v);
	}

	@Override
	public Long zRemRangeByRank(final String key, final long start, final long end) {
		return zRemRangeByRank(SafeEncoder.encode(key), start, end);
	}

	@Override
	public Long zRemRangeByRank(final byte[] key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end);
		return executeCommand(Command.ZREMRANGEBYRANK, args, (cmd)->cmd.zremrangebyrank(key, start, end), (v)->v);
	}

	@Override
	public Long zRemRangeByScore(final String key, final double min, final double max) {
		return zRemRangeByScore(SafeEncoder.encode(key), min, max);
	}

	@Override
	public Long zRemRangeByScore(final byte[] key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);
		return executeCommand(Command.ZREMRANGEBYSCORE, args, (cmd)->cmd.zremrangebyscore(key, Range.create(min, max)),
				(v)->v);
	}

	@Override
	public List<String> zRevRange(final String key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end);
		return executeCommand(Command.ZREVRANGE, args, (cmd)->cmd.zrevrange(SafeEncoder.encode(key), start, end),
				(v)->v);
	}

	@Override
	public List<byte[]> zRevRange(final byte[] key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end);
		return executeCommand(Command.ZREVRANGE, args, (cmd)->cmd.zrevrange(key, start, end), (v)->v);
	}

	@Override
	public List<Tuple> zRevRangeWithScores(final String key, final long start, final long end) {
		return zRevRangeWithScores(SafeEncoder.encode(key), start, end);
	}

	@Override
	public List<Tuple> zRevRangeWithScores(final byte[] key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end);
		return executeCommand(Command.ZREVRANGE, args, (cmd)->cmd.zrevrangeWithScores(key, start, end),
				new ListConverter<>(new ScoredValueTupleConverter()));
	}

	@Override
	public List<String> zRevRangeByLex(final String key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);
		return executeCommand(Command.ZREVRANGEBYLEX, args, (cmd)->cmd.zrevrangebylex(SafeEncoder.encode(key),
						Range.create(NumberUtils.double2bytes(min), NumberUtils.double2bytes(max))),
				new ListConverter<>(SafeEncoder::encode));
	}

	@Override
	public List<byte[]> zRevRangeByLex(final byte[] key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);
		return executeCommand(Command.ZREVRANGEBYLEX, args, (cmd)->cmd.zrevrangebylex(key,
				Range.create(NumberUtils.double2bytes(min), NumberUtils.double2bytes(max))), (v)->v);
	}

	@Override
	public List<String> zRevRangeByLex(final String key, final double min, final double max, final int offset,
									   final int count) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max).add(count);
		return executeCommand(Command.ZREVRANGEBYLEX, args, (cmd)->cmd.zrevrangebylex(SafeEncoder.encode(key),
				Range.create(NumberUtils.double2bytes(min), NumberUtils.double2bytes(max)),
				Limit.create(offset, count)), new ListConverter<>(SafeEncoder::encode));
	}

	@Override
	public List<byte[]> zRevRangeByLex(final byte[] key, final double min, final double max, final int offset,
									   final int count) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max).add(count);
		return executeCommand(Command.ZREVRANGEBYLEX, args, (cmd)->cmd.zrevrangebylex(key,
				Range.create(NumberUtils.double2bytes(min), NumberUtils.double2bytes(max)),
				Limit.create(offset, count)), (v)->v);
	}

	@Override
	public List<String> zRevRangeByScore(final String key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);
		return executeCommand(Command.ZREVRANGEBYSCORE, args,
				(cmd)->cmd.zrevrangebyscore(SafeEncoder.encode(key), Range.create(min, max)),
				new ListConverter<>(SafeEncoder::encode));
	}

	@Override
	public List<byte[]> zRevRangeByScore(final byte[] key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);
		return executeCommand(Command.ZREVRANGEBYSCORE, args, (cmd)->cmd.zrevrangebyscore(key, Range.create(min, max)),
				(v)->v);
	}

	@Override
	public List<String> zRevRangeByScore(final String key, final double min, final double max, final int offset,
										 final int count) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max).add(Keyword.Common.LIMIT)
				.add(offset).add(count);
		return executeCommand(Command.ZREVRANGEBYSCORE, args,
				(cmd)->cmd.zrevrangebyscore(SafeEncoder.encode(key), Range.create(min, max),
						Limit.create(offset, count)), new ListConverter<>(SafeEncoder::encode));
	}

	@Override
	public List<byte[]> zRevRangeByScore(final byte[] key, final double min, final double max, final int offset,
										 final int count) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max).add(Keyword.Common.LIMIT)
				.add(offset).add(count);
		return executeCommand(Command.ZREVRANGEBYSCORE, args,
				(cmd)->cmd.zrevrangebyscore(key, Range.create(min, max), Limit.create(offset, count)), (v)->v);
	}

	@Override
	public List<Tuple> zRevRangeByScoreWithScores(final String key, final double min, final double max) {
		return zRevRangeByScoreWithScores(SafeEncoder.encode(key), min, max);
	}

	@Override
	public List<Tuple> zRevRangeByScoreWithScores(final byte[] key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);
		return executeCommand(Command.ZREVRANGEBYSCORE, args,
				(cmd)->cmd.zrevrangebyscoreWithScores(key, Range.create(min, max)),
				new ListConverter<>(new ScoredValueTupleConverter()));
	}

	@Override
	public List<Tuple> zRevRangeByScoreWithScores(final String key, final double min, final double max,
												  final int offset, final int count) {
		return zRevRangeByScoreWithScores(SafeEncoder.encode(key), min, max, offset, count);
	}

	@Override
	public List<Tuple> zRevRangeByScoreWithScores(final byte[] key, final double min, final double max,
												  final int offset, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max).add(Keyword.Common.LIMIT)
				.add(offset).add(count);
		return executeCommand(Command.ZREVRANGEBYSCORE, args,
				(cmd)->cmd.zrevrangebyscoreWithScores(key, Range.create(min, max), Limit.create(offset, count)),
				new ListConverter<>(new ScoredValueTupleConverter()));
	}

	@Override
	public Long zRevRank(final String key, final String member) {
		return zRevRank(SafeEncoder.encode(key), SafeEncoder.encode(member));
	}

	@Override
	public Long zRevRank(final byte[] key, final byte[] member) {
		final CommandArguments args = CommandArguments.create(key).add(member);
		return executeCommand(Command.ZREVRANK, args, (cmd)->cmd.zrevrank(key, member), (v)->v);
	}

	@Override
	public Tuple zRevRankWithScore(final String key, final String member) {
		final CommandArguments args = CommandArguments.create(key).add(member).add(Keyword.Common.WITHSCORE);
		return executeCommand(Command.ZREVRANK, args, (cmd)->cmd.zrankWithScore(key, member), (v)->v);
	}

	@Override
	public Tuple zRevRankWithScore(final byte[] key, final byte[] member) {
		final CommandArguments args = CommandArguments.create(key).add(member).add(Keyword.Common.WITHSCORE);
		return executeCommand(Command.ZREVRANK, args, (cmd)->cmd.zrankWithScore(key, member), (v)->v);
	}

	@Override
	public ScanResult<Tuple> zScan(final String key, final String cursor) {
		final CommandArguments args = CommandArguments.create(key).add(cursor);
		return executeCommand(Command.ZSCAN, args,
				(cmd)->cmd.zscan(SafeEncoder.encode(key), new LettuceScanCursor(cursor)), new ScanCursorConverter<>());
	}

	@Override
	public ScanResult<Tuple> zScan(final byte[] key, final byte[] cursor) {
		final CommandArguments args = CommandArguments.create(key).add(cursor);
		return executeCommand(Command.ZSCAN, args, (cmd)->cmd.zscan(key, new LettuceScanCursor(cursor)),
				new ScanCursorConverter<>());
	}

	@Override
	public ScanResult<Tuple> zScan(final String key, final String cursor, final String pattern) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(Keyword.Scan.MATCH, pattern);
		return zScan(SafeEncoder.encode(key), new LettuceScanCursor(cursor), new LettuceScanArgs(pattern), args);
	}

	@Override
	public ScanResult<Tuple> zScan(final byte[] key, final byte[] cursor, final byte[] pattern) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(Keyword.Scan.MATCH, pattern);
		return zScan(key, new LettuceScanCursor(cursor), new LettuceScanArgs(pattern), args);
	}

	@Override
	public ScanResult<Tuple> zScan(final String key, final String cursor, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(Keyword.Common.COUNT, count);
		return zScan(SafeEncoder.encode(key), new LettuceScanCursor(cursor), new LettuceScanArgs(count), args);
	}

	@Override
	public ScanResult<Tuple> zScan(final byte[] key, final byte[] cursor, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(Keyword.Common.COUNT, count);
		return zScan(key, new LettuceScanCursor(cursor), new LettuceScanArgs(count), args);
	}

	@Override
	public ScanResult<Tuple> zScan(final String key, final String cursor, final String pattern, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(Keyword.Scan.MATCH, pattern)
				.add(Keyword.Common.COUNT, count);
		return zScan(SafeEncoder.encode(key), new LettuceScanCursor(cursor), new LettuceScanArgs(pattern, count), args);
	}

	@Override
	public ScanResult<Tuple> zScan(final byte[] key, final byte[] cursor, final byte[] pattern, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(Keyword.Scan.MATCH, pattern)
				.add(Keyword.Common.COUNT, count);
		return zScan(key, new LettuceScanCursor(cursor), new LettuceScanArgs(pattern, count), args);
	}

	@Override
	public Double zScore(final String key, final String member) {
		return zScore(SafeEncoder.encode(key), SafeEncoder.encode(member));
	}

	@Override
	public Double zScore(final byte[] key, final byte[] member) {
		final CommandArguments args = CommandArguments.create(key).add(member);
		return executeCommand(Command.ZSCORE, args, (cmd)->cmd.zscore(key, member), (v)->v);
	}

	@Override
	public List<String> zUnion(final String... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return executeCommand(Command.ZUNION, args, (cmd)->cmd.zunion(SafeEncoder.encode(keys)),
				new ListConverter<>(SafeEncoder::encode));
	}

	@Override
	public List<byte[]> zUnion(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return executeCommand(Command.ZUNION, args, (cmd)->cmd.zunion(keys), (v)->v);
	}

	@Override
	public List<String> zUnion(final String[] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create(keys).add("AGGREGATE", aggregate);
		return zUnion(keys, new LettuceZAggregateArgs(aggregate), args);
	}

	@Override
	public List<byte[]> zUnion(final byte[][] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create(keys).add("AGGREGATE", aggregate);
		return zUnion(keys, new LettuceZAggregateArgs(aggregate), args);
	}

	@Override
	public List<String> zUnion(final String[] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add("WEIGHTS", weights);
		return zUnion(keys, new LettuceZAggregateArgs(weights), args);
	}

	@Override
	public List<byte[]> zUnion(final byte[][] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add("WEIGHTS", weights);
		return zUnion(keys, new LettuceZAggregateArgs(weights), args);
	}

	@Override
	public List<String> zUnion(final String[] keys, final Aggregate aggregate, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add("AGGREGATE", aggregate).add("WEIGHTS", weights);
		return zUnion(keys, new LettuceZAggregateArgs(aggregate, weights), args);
	}

	@Override
	public List<byte[]> zUnion(final byte[][] keys, final Aggregate aggregate, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add("AGGREGATE", aggregate).add("WEIGHTS", weights);
		return zUnion(keys, new LettuceZAggregateArgs(aggregate, weights), args);
	}

	@Override
	public List<Tuple> zUnionWithScores(final String... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return executeCommand(Command.ZUNION, args, (cmd)->cmd.zunionWithScores(SafeEncoder.encode(keys)),
				new ListConverter<>(new ScoredValueTupleConverter()));
	}

	@Override
	public List<Tuple> zUnionWithScores(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return executeCommand(Command.ZUNION, args, (cmd)->cmd.zunionWithScores(keys),
				new ListConverter<>(new ScoredValueTupleConverter()));
	}

	@Override
	public List<Tuple> zUnionWithScores(final String[] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create(keys).add("AGGREGATE", aggregate);
		return zUnionWithScores(keys, new LettuceZAggregateArgs(aggregate), args);
	}

	@Override
	public List<Tuple> zUnionWithScores(final byte[][] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create(keys).add("AGGREGATE", aggregate);
		return zUnionWithScores(keys, new LettuceZAggregateArgs(aggregate), args);
	}

	@Override
	public List<Tuple> zUnionWithScores(final String[] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add("WEIGHTS", weights);
		return zUnionWithScores(keys, new LettuceZAggregateArgs(weights), args);
	}

	@Override
	public List<Tuple> zUnionWithScores(final byte[][] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add("WEIGHTS", weights);
		return zUnionWithScores(keys, new LettuceZAggregateArgs(weights), args);
	}

	@Override
	public List<Tuple> zUnionWithScores(final String[] keys, final Aggregate aggregate, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add("AGGREGATE", aggregate).add("WEIGHTS", weights);
		return zUnionWithScores(keys, new LettuceZAggregateArgs(aggregate, weights), args);
	}

	@Override
	public List<Tuple> zUnionWithScores(final byte[][] keys, final Aggregate aggregate, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add("AGGREGATE", aggregate).add("WEIGHTS", weights);
		return zUnionWithScores(keys, new LettuceZAggregateArgs(aggregate, weights), args);
	}

	@Override
	public Long zUnionStore(final String destKey, final String... keys) {
		return zUnionStore(SafeEncoder.encode(destKey), SafeEncoder.encode(keys));
	}

	@Override
	public Long zUnionStore(final byte[] destKey, final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys);
		return executeCommand(Command.ZUNIONSTORE, args, (cmd)->cmd.zunionstore(destKey, keys),
				(v)->v);
	}

	@Override
	public Long zUnionStore(final String destKey, final String[] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys).add("AGGREGATE", aggregate);
		return zUnionStore(destKey, keys, new LettuceZStoreArgs(aggregate), args);
	}

	@Override
	public Long zUnionStore(final byte[] destKey, final byte[][] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys).add("AGGREGATE", aggregate);
		return zUnionStore(destKey, keys, new LettuceZStoreArgs(aggregate), args);
	}

	@Override
	public Long zUnionStore(final String destKey, final String[] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys).add("WEIGHTS", weights);
		return zUnionStore(destKey, keys, new LettuceZStoreArgs(weights), args);
	}

	@Override
	public Long zUnionStore(final byte[] destKey, final byte[][] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys).add("WEIGHTS", weights);
		return zUnionStore(destKey, keys, new LettuceZStoreArgs(weights), args);
	}

	@Override
	public Long zUnionStore(final String destKey, final String[] keys, final Aggregate aggregate,
							final double... weights) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys).add("AGGREGATE", aggregate)
				.add("WEIGHTS", weights);
		return zUnionStore(destKey, keys, new LettuceZStoreArgs(aggregate, weights), args);
	}

	@Override
	public Long zUnionStore(final byte[] destKey, final byte[][] keys, final Aggregate aggregate,
							final double... weights) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys).add("AGGREGATE", aggregate)
				.add("WEIGHTS", weights);
		return zUnionStore(destKey, keys, new LettuceZStoreArgs(aggregate, weights), args);
	}

	private <K> KeyValue<K, List<Tuple>> bzMPop(final byte[][] keys, final int timeout, final MinMax minMax,
												final int count, final Converter<byte[], K> keyConverter,
												final CommandArguments args) {
		return executeCommand(Command.BZMPOP, args,
				(cmd)->cmd.bzmpop(timeout, (long) count, new LettuceZPopArgs(minMax), keys),
				new KeyValueConverter<>(keyConverter, new ListConverter<>(new ScoredValueTupleConverter())));
	}

	private Long zAdd(final byte[] key, final Tuple[] members, final CommandArguments args) {
		final ScoredValue<byte[]>[] scoredValues = new ScoredValue[members.length];

		for(int i = 0; i < members.length; i++){
			scoredValues[i] = ScoredValue.just(members[i].getScore(), members[i].getBinaryElement());
		}

		return executeCommand(Command.ZADD, args, (cmd)->cmd.zadd(key, scoredValues), (v)->v);
	}

	private Long zAdd(final byte[] key, final Tuple[] members, final ZAddArgument argument,
					  final CommandArguments args) {
		final ScoredValue<byte[]>[] scoredValues = new ScoredValue[members.length];
		final ZAddArgumentConverter zAddArgumentConverter = new ZAddArgumentConverter();

		for(int i = 0; i < members.length; i++){
			scoredValues[i] = ScoredValue.just(members[i].getScore(), members[i].getBinaryElement());
		}

		return executeCommand(Command.ZADD, args,
				(cmd)->cmd.zadd(key, zAddArgumentConverter.convert(argument), scoredValues), (v)->v);
	}

	private List<String> zInter(final String[] keys, final LettuceZAggregateArgs zAggregateArgs,
								final CommandArguments args) {
		return executeCommand(Command.ZINTER, args, (cmd)->cmd.zinter(zAggregateArgs, SafeEncoder.encode(keys)),
				new ListConverter<>(SafeEncoder::encode));
	}

	private List<byte[]> zInter(final byte[][] keys, final LettuceZAggregateArgs zAggregateArgs,
								final CommandArguments args) {
		return executeCommand(Command.ZINTER, args, (cmd)->cmd.zinter(zAggregateArgs, keys), (v)->v);
	}

	private List<Tuple> zInterWithScores(final String[] keys, final LettuceZAggregateArgs zAggregateArgs,
										 final CommandArguments args) {
		return executeCommand(Command.ZINTER, args,
				(cmd)->cmd.zinterWithScores(zAggregateArgs, SafeEncoder.encode(keys)),
				new ListConverter<>(new ScoredValueTupleConverter()));
	}

	private List<Tuple> zInterWithScores(final byte[][] keys, final LettuceZAggregateArgs zAggregateArgs,
										 final CommandArguments args) {
		return executeCommand(Command.ZINTER, args, (cmd)->cmd.zinterWithScores(zAggregateArgs, keys),
				new ListConverter<>(new ScoredValueTupleConverter()));
	}

	private Long zInterStore(final byte[] destKey, final byte[][] keys, final LettuceZStoreArgs zStoreArgs,
							 final CommandArguments args) {
		return executeCommand(Command.ZINTERSTORE, args, (cmd)->cmd.zinterstore(destKey, zStoreArgs, keys), (v)->v);
	}

	private <K> KeyValue<K, List<Tuple>> zMPop(final byte[][] keys, final MinMax minMax, final int count,
											   final Converter<byte[], K> keyConverter, final CommandArguments args) {
		return executeCommand(Command.ZMPOP, args, (cmd)->cmd.zmpop(count, new LettuceZPopArgs(minMax), keys),
				new KeyValueConverter<>(keyConverter, new ListConverter<>(new ScoredValueTupleConverter())));
	}

	private ScanResult<Tuple> zScan(final byte[] key, final LettuceScanCursor cursor, final LettuceScanArgs scanArgs,
									final CommandArguments args) {
		return executeCommand(Command.ZSCAN, args, (cmd)->cmd.zscan(key, cursor, scanArgs),
				new ScanCursorConverter<>());
	}

	private List<String> zUnion(final String[] keys, final LettuceZAggregateArgs aggregateArgs,
								final CommandArguments args) {
		return executeCommand(Command.ZUNION, args, (cmd)->cmd.zunion(aggregateArgs, SafeEncoder.encode(keys)),
				new ListConverter<>(SafeEncoder::encode));
	}

	private List<byte[]> zUnion(final byte[][] keys, final LettuceZAggregateArgs aggregateArgs,
								final CommandArguments args) {
		return executeCommand(Command.ZUNION, args, (cmd)->cmd.zunion(aggregateArgs, keys), (v)->v);
	}

	private List<Tuple> zUnionWithScores(final String[] keys, final LettuceZAggregateArgs aggregateArgs,
										 final CommandArguments args) {
		return executeCommand(Command.ZUNION, args,
				(cmd)->cmd.zunionWithScores(aggregateArgs, SafeEncoder.encode(keys)),
				new ListConverter<>(new ScoredValueTupleConverter()));
	}

	private List<Tuple> zUnionWithScores(final byte[][] keys, final LettuceZAggregateArgs aggregateArgs,
										 final CommandArguments args) {
		return executeCommand(Command.ZUNION, args, (cmd)->cmd.zunionWithScores(aggregateArgs, keys),
				new ListConverter<>(new ScoredValueTupleConverter()));
	}

	private Long zUnionStore(final String destKey, final String[] keys, final LettuceZStoreArgs zStoreArgs,
							 final CommandArguments args) {
		return executeCommand(Command.ZUNIONSTORE, args,
				(cmd)->cmd.zunionstore(SafeEncoder.encode(destKey), zStoreArgs, SafeEncoder.encode(keys)),
				(v)->v);
	}

	private Long zUnionStore(final byte[] destKey, final byte[][] keys, final LettuceZStoreArgs zStoreArgs,
							 final CommandArguments args) {
		return executeCommand(Command.ZUNIONSTORE, args, (cmd)->cmd.zunionstore(destKey, zStoreArgs, keys), (v)->v);
	}

}
