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
import com.buession.redis.core.command.args.sortedset.Aggregate;
import com.buession.redis.core.Keyword;
import com.buession.redis.core.command.args.MinMax;
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.Tuple;
import com.buession.redis.core.command.args.sortedset.ZRangeType;
import com.buession.redis.core.command.RedisCommand;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.SortedSetCommands;
import com.buession.redis.core.command.args.sortedset.ZAddArgument;
import com.buession.redis.core.internal.convert.Converters;
import com.buession.redis.core.internal.convert.lettuce.response.KeyValueConverter;
import com.buession.redis.core.internal.convert.lettuce.response.ScanCursorConverter;
import com.buession.redis.core.internal.convert.lettuce.response.ScoredValueKeyValueConverter;
import com.buession.redis.core.internal.convert.lettuce.response.ScoredValueTupleConverter;
import com.buession.redis.core.internal.lettuce.args.LettuceScanArgs;
import com.buession.redis.core.internal.lettuce.args.LettuceScanCursor;
import com.buession.redis.core.internal.lettuce.args.LettuceZAddArgs;
import com.buession.redis.core.internal.lettuce.args.LettuceZAggregateArgs;
import com.buession.redis.core.internal.lettuce.args.LettuceZPopArgs;
import com.buession.redis.core.internal.lettuce.args.LettuceZStoreArgs;
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
				.add(Keyword.Common.COUNT).add(count);
		return bzMPop(SafeEncoder.encode(keys), timeout, minMax, count, SafeEncoder::encode, args);
	}

	@Override
	public KeyValue<byte[], List<Tuple>> bzMPop(final byte[][] keys, final int timeout, final MinMax minMax,
	                                            final int count) {
		final CommandArguments args = CommandArguments.create(timeout).add(keys.length).add(keys).add(minMax)
				.add(Keyword.Common.COUNT).add(count);
		return bzMPop(keys, timeout, minMax, count, (k)->k, args);
	}

	@Override
	public KeyValue<String, Tuple> bzPopMax(final String[] keys, final int timeout) {
		final CommandArguments args = CommandArguments.create(keys).add(timeout);
		return executeCommand(RedisCommand.BZPOPMAX, args, (cmd)->cmd.bzpopmax(timeout, SafeEncoder.encode(keys)),
				(cmd)->cmd.bzpopmax(timeout, SafeEncoder.encode(keys)),
				new KeyValueConverter<>(SafeEncoder::encode, new ScoredValueTupleConverter()));
	}

	@Override
	public KeyValue<byte[], Tuple> bzPopMax(final byte[][] keys, final int timeout) {
		final CommandArguments args = CommandArguments.create(keys).add(timeout);
		return executeCommand(RedisCommand.BZPOPMAX, args, (cmd)->cmd.bzpopmax(timeout, keys),
				(cmd)->cmd.bzpopmax(timeout, keys),
				new KeyValueConverter<>((k)->k, new ScoredValueTupleConverter()));
	}

	@Override
	public KeyValue<String, Tuple> bzPopMin(final String[] keys, final int timeout) {
		final CommandArguments args = CommandArguments.create(keys).add(timeout);
		return executeCommand(RedisCommand.BZPOPMIN, args, (cmd)->cmd.bzpopmin(timeout, SafeEncoder.encode(keys)),
				(cmd)->cmd.bzpopmin(timeout, SafeEncoder.encode(keys)),
				new KeyValueConverter<>(SafeEncoder::encode, new ScoredValueTupleConverter()));
	}

	@Override
	public KeyValue<byte[], Tuple> bzPopMin(final byte[][] keys, final int timeout) {
		final CommandArguments args = CommandArguments.create(keys).add(timeout);
		return executeCommand(RedisCommand.BZPOPMIN, args, (cmd)->cmd.bzpopmin(timeout, keys),
				(cmd)->cmd.bzpopmin(timeout, keys),
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
		return executeCommand(RedisCommand.ZCARD, args, (cmd)->cmd.zcard(SafeEncoder.encode(key)),
				(cmd)->cmd.zcard(SafeEncoder.encode(key)));
	}

	@Override
	public Long zCard(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.ZCARD, args, (cmd)->cmd.zcard(key), (cmd)->cmd.zcard(key));
	}

	@Override
	public Long zCount(final String key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);
		return executeCommand(RedisCommand.ZCOUNT, args,
				(cmd)->cmd.zcount(SafeEncoder.encode(key), Range.create(min, max)),
				(cmd)->cmd.zcount(SafeEncoder.encode(key), Range.create(min, max)));
	}

	@Override
	public Long zCount(final byte[] key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);
		return executeCommand(RedisCommand.ZCOUNT, args, (cmd)->cmd.zcount(key, Range.create(min, max)),
				(cmd)->cmd.zcount(key, Range.create(min, max)));
	}

	@Override
	public List<String> zDiff(final String... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return executeCommand(RedisCommand.ZDIFF, args, (cmd)->cmd.zdiff(SafeEncoder.encode(keys)),
				(cmd)->cmd.zdiff(SafeEncoder.encode(keys)), Converters.binaryListStringListConverter());
	}

	@Override
	public List<byte[]> zDiff(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return executeCommand(RedisCommand.ZDIFF, args, (cmd)->cmd.zdiff(keys),
				(cmd)->cmd.zdiff(keys));
	}

	@Override
	public List<Tuple> zDiffWithScores(final String... keys) {
		final CommandArguments args = CommandArguments.create(keys).add(Keyword.Common.WITHSCORES);
		return zDiffWithScores(SafeEncoder.encode(keys), args);
	}

	@Override
	public List<Tuple> zDiffWithScores(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(keys).add(Keyword.Common.WITHSCORES);
		return zDiffWithScores(keys, args);
	}

	@Override
	public Long zDiffStore(final String destKey, final String... keys) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys);
		return executeCommand(RedisCommand.ZDIFFSTORE, args,
				(cmd)->cmd.zdiffstore(SafeEncoder.encode(destKey), SafeEncoder.encode(keys)),
				(cmd)->cmd.zdiffstore(SafeEncoder.encode(destKey), SafeEncoder.encode(keys)));
	}

	@Override
	public Long zDiffStore(final byte[] destKey, final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys);
		return executeCommand(RedisCommand.ZDIFFSTORE, args, (cmd)->cmd.zdiffstore(destKey, keys),
				(cmd)->cmd.zdiffstore(destKey, keys));
	}

	@Override
	public Double zIncrBy(final String key, final double increment, final String member) {
		final CommandArguments args = CommandArguments.create(key).add(increment).add(member);
		return executeCommand(RedisCommand.ZINCRBY, args,
				(cmd)->cmd.zincrby(SafeEncoder.encode(key), increment, SafeEncoder.encode(member)),
				(cmd)->cmd.zincrby(SafeEncoder.encode(key), increment, SafeEncoder.encode(member)));
	}

	@Override
	public Double zIncrBy(final byte[] key, final double increment, final byte[] member) {
		final CommandArguments args = CommandArguments.create(key).add(increment).add(member);
		return executeCommand(RedisCommand.ZINCRBY, args, (cmd)->cmd.zincrby(key, increment, member),
				(cmd)->cmd.zincrby(key, increment, member));
	}

	@Override
	public List<String> zInter(final String... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return executeCommand(RedisCommand.ZINTER, args, (cmd)->cmd.zinter(SafeEncoder.encode(keys)),
				(cmd)->cmd.zinter(SafeEncoder.encode(keys)), Converters.binaryListStringListConverter());
	}

	@Override
	public List<byte[]> zInter(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return executeCommand(RedisCommand.ZINTER, args, (cmd)->cmd.zinter(keys),
				(cmd)->cmd.zinter(keys));
	}

	@Override
	public List<String> zInter(final String[] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create(keys).add("AGGREGATE").add(aggregate);
		return stringZInter(SafeEncoder.encode(keys), new LettuceZAggregateArgs(aggregate), args);
	}

	@Override
	public List<byte[]> zInter(final byte[][] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create(keys).add("AGGREGATE").add(aggregate);
		return binaryZInter(keys, new LettuceZAggregateArgs(aggregate), args);
	}

	@Override
	public List<String> zInter(final String[] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add("WEIGHTS").add(weights);
		return stringZInter(SafeEncoder.encode(keys), new LettuceZAggregateArgs(weights), args);
	}

	@Override
	public List<byte[]> zInter(final byte[][] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add("WEIGHTS").add(weights);
		return binaryZInter(keys, new LettuceZAggregateArgs(weights), args);
	}

	@Override
	public List<String> zInter(final String[] keys, final Aggregate aggregate, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add("AGGREGATE").add(aggregate).add("WEIGHTS")
				.add(weights);
		return stringZInter(SafeEncoder.encode(keys), new LettuceZAggregateArgs(aggregate, weights), args);
	}

	@Override
	public List<byte[]> zInter(final byte[][] keys, final Aggregate aggregate, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add("AGGREGATE").add(aggregate).add("WEIGHTS")
				.add(weights);
		return binaryZInter(keys, new LettuceZAggregateArgs(aggregate, weights), args);
	}

	@Override
	public List<Tuple> zInterWithScores(final String... keys) {
		final CommandArguments args = CommandArguments.create(keys).add(Keyword.Common.WITHSCORES);
		return executeCommand(RedisCommand.ZINTER, args, (cmd)->cmd.zinterWithScores(SafeEncoder.encode(keys)),
				(cmd)->cmd.zinterWithScores(SafeEncoder.encode(keys)),
				new ListConverter<>(new ScoredValueTupleConverter()));
	}

	@Override
	public List<Tuple> zInterWithScores(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(keys).add(Keyword.Common.WITHSCORES);
		return executeCommand(RedisCommand.ZINTER, args, (cmd)->cmd.zinterWithScores(keys),
				(cmd)->cmd.zinterWithScores(keys), new ListConverter<>(new ScoredValueTupleConverter()));
	}

	@Override
	public List<Tuple> zInterWithScores(final String[] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create(keys).add("AGGREGATE").add(aggregate)
				.add(Keyword.Common.WITHSCORES);
		return zInterWithScores(SafeEncoder.encode(keys), new LettuceZAggregateArgs(aggregate), args);
	}

	@Override
	public List<Tuple> zInterWithScores(final byte[][] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create(keys).add("AGGREGATE").add(aggregate)
				.add(Keyword.Common.WITHSCORES);
		return zInterWithScores(keys, new LettuceZAggregateArgs(aggregate), args);
	}

	@Override
	public List<Tuple> zInterWithScores(final String[] keys, final Aggregate aggregate, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add("AGGREGATE").add(aggregate).add("WEIGHTS")
				.add(weights)
				.add(Keyword.Common.WITHSCORES);
		return zInterWithScores(SafeEncoder.encode(keys), new LettuceZAggregateArgs(aggregate), args);
	}

	@Override
	public List<Tuple> zInterWithScores(final byte[][] keys, final Aggregate aggregate, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add("AGGREGATE").add(aggregate).add("WEIGHTS")
				.add(weights)
				.add(Keyword.Common.WITHSCORES);
		return zInterWithScores(keys, new LettuceZAggregateArgs(aggregate), args);
	}

	@Override
	public List<Tuple> zInterWithScores(final String[] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add("WEIGHTS").add(weights)
				.add(Keyword.Common.WITHSCORES);
		return zInterWithScores(SafeEncoder.encode(keys), new LettuceZAggregateArgs(weights), args);
	}

	@Override
	public List<Tuple> zInterWithScores(final byte[][] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add("WEIGHTS").add(weights)
				.add(Keyword.Common.WITHSCORES);
		return zInterWithScores(keys, new LettuceZAggregateArgs(weights), args);
	}

	@Override
	public long zInterCard(final String... keys) {
		final CommandArguments args = CommandArguments.create(keys.length).add(keys);
		return executeCommand(RedisCommand.ZINTERCARD, args, (cmd)->cmd.zintercard(SafeEncoder.encode(keys)),
				(cmd)->cmd.zintercard(SafeEncoder.encode(keys)));
	}

	@Override
	public long zInterCard(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(keys.length).add(keys);
		return executeCommand(RedisCommand.ZINTERCARD, args, (cmd)->cmd.zintercard(keys),
				(cmd)->cmd.zintercard(keys));
	}

	@Override
	public long zInterCard(final String[] keys, final int limit) {
		final CommandArguments args = CommandArguments.create(keys.length).add(keys).add(Keyword.Common.LIMIT)
				.add(limit);
		return executeCommand(RedisCommand.ZINTERCARD, args, (cmd)->cmd.zintercard(limit, SafeEncoder.encode(keys)),
				(cmd)->cmd.zintercard(limit, SafeEncoder.encode(keys)));
	}

	@Override
	public long zInterCard(final byte[][] keys, final int limit) {
		final CommandArguments args = CommandArguments.create(keys.length).add(keys).add(Keyword.Common.LIMIT)
				.add(limit);
		return executeCommand(RedisCommand.ZINTERCARD, args, (cmd)->cmd.zintercard(limit, keys),
				(cmd)->cmd.zintercard(limit, keys));
	}

	@Override
	public Long zInterStore(final String destKey, final String... keys) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys.length).add(keys);
		return executeCommand(RedisCommand.ZINTERSTORE, args,
				(cmd)->cmd.zinterstore(SafeEncoder.encode(destKey), SafeEncoder.encode(keys)),
				(cmd)->cmd.zinterstore(SafeEncoder.encode(destKey), SafeEncoder.encode(keys)));
	}

	@Override
	public Long zInterStore(final byte[] destKey, final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys.length).add(keys);
		return executeCommand(RedisCommand.ZINTERSTORE, args, (cmd)->cmd.zinterstore(destKey, keys),
				(cmd)->cmd.zinterstore(destKey, keys));
	}

	@Override
	public Long zInterStore(final String destKey, final String[] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys.length).add(keys)
				.add("AGGREGATE").add(aggregate);
		return zInterStore(SafeEncoder.encode(destKey), SafeEncoder.encode(keys), new LettuceZStoreArgs(aggregate),
				args);
	}

	@Override
	public Long zInterStore(final byte[] destKey, final byte[][] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys.length).add(keys)
				.add("AGGREGATE").add(aggregate);
		return zInterStore(destKey, keys, new LettuceZStoreArgs(aggregate), args);
	}

	@Override
	public Long zInterStore(final String destKey, final String[] keys, final Aggregate aggregate,
	                        final double... weights) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys.length).add(keys)
				.add("AGGREGATE").add(aggregate).add("WEIGHTS").add(weights);
		return zInterStore(SafeEncoder.encode(destKey), SafeEncoder.encode(keys),
				new LettuceZStoreArgs(aggregate, weights), args);
	}

	@Override
	public Long zInterStore(final byte[] destKey, final byte[][] keys, final Aggregate aggregate,
	                        final double... weights) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys.length).add(keys)
				.add("AGGREGATE").add(aggregate).add("WEIGHTS").add(weights);
		return zInterStore(destKey, keys, new LettuceZStoreArgs(aggregate, weights), args);
	}

	@Override
	public Long zInterStore(final String destKey, final String[] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys.length).add(keys)
				.add("WEIGHTS").add(weights);
		return zInterStore(SafeEncoder.encode(destKey), SafeEncoder.encode(keys), new LettuceZStoreArgs(weights), args);
	}

	@Override
	public Long zInterStore(final byte[] destKey, final byte[][] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys.length).add(keys)
				.add("WEIGHTS").add(weights);
		return zInterStore(destKey, keys, new LettuceZStoreArgs(weights), args);
	}

	@Override
	public Long zLexCount(final String key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);
		return zLexCount(SafeEncoder.encode(key), min, max, args);
	}

	@Override
	public Long zLexCount(final byte[] key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);
		return zLexCount(key, min, max, args);
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
		final CommandArguments args = CommandArguments.create(keys.length).add(keys).add(minMax)
				.add(Keyword.Common.COUNT).add(count);
		return zMPop(SafeEncoder.encode(keys), minMax, count, SafeEncoder::encode, args);
	}

	@Override
	public KeyValue<byte[], List<Tuple>> zMPop(final byte[][] keys, final MinMax minMax, final int count) {
		final CommandArguments args = CommandArguments.create(keys.length).add(keys).add(minMax)
				.add(Keyword.Common.COUNT).add(count);
		return zMPop(keys, minMax, count, (k)->k, args);
	}

	@Override
	public List<Double> zMScore(final String key, final String... members) {
		final CommandArguments args = CommandArguments.create(key).add(members);
		return executeCommand(RedisCommand.ZMSCORE, args,
				(cmd)->cmd.zmscore(SafeEncoder.encode(key), SafeEncoder.encode(members)),
				(cmd)->cmd.zmscore(SafeEncoder.encode(key), SafeEncoder.encode(members)));
	}

	@Override
	public List<Double> zMScore(final byte[] key, final byte[]... members) {
		final CommandArguments args = CommandArguments.create(key).add(members);
		return executeCommand(RedisCommand.ZMSCORE, args, (cmd)->cmd.zmscore(key, members),
				(cmd)->cmd.zmscore(key, members));
	}

	@Override
	public Tuple zPopMax(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.ZPOPMAX, args, (cmd)->cmd.zpopmax(SafeEncoder.encode(key)),
				(cmd)->cmd.zpopmax(SafeEncoder.encode(key)), new ScoredValueTupleConverter());
	}

	@Override
	public Tuple zPopMax(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.ZPOPMAX, args, (cmd)->cmd.zpopmax(key),
				(cmd)->cmd.zpopmax(key), new ScoredValueTupleConverter());
	}

	@Override
	public List<Tuple> zPopMax(final String key, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(count);
		return executeCommand(RedisCommand.ZPOPMAX, args, (cmd)->cmd.zpopmax(SafeEncoder.encode(key), count),
				(cmd)->cmd.zpopmax(SafeEncoder.encode(key), count),
				new ListConverter<>(new ScoredValueTupleConverter()));
	}

	@Override
	public List<Tuple> zPopMax(final byte[] key, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(count);
		return executeCommand(RedisCommand.ZPOPMAX, args, (cmd)->cmd.zpopmax(key, count),
				(cmd)->cmd.zpopmax(key, count), new ListConverter<>(new ScoredValueTupleConverter()));
	}

	@Override
	public Tuple zPopMin(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.ZPOPMIN, args, (cmd)->cmd.zpopmin(SafeEncoder.encode(key)),
				(cmd)->cmd.zpopmin(SafeEncoder.encode(key)), new ScoredValueTupleConverter());
	}

	@Override
	public Tuple zPopMin(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.ZPOPMIN, args, (cmd)->cmd.zpopmin(key),
				(cmd)->cmd.zpopmin(key), new ScoredValueTupleConverter());
	}

	@Override
	public List<Tuple> zPopMin(final String key, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(count);
		return executeCommand(RedisCommand.ZPOPMIN, args, (cmd)->cmd.zpopmin(SafeEncoder.encode(key), count),
				(cmd)->cmd.zpopmin(SafeEncoder.encode(key), count),
				new ListConverter<>(new ScoredValueTupleConverter()));
	}

	@Override
	public List<Tuple> zPopMin(final byte[] key, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(count);
		return executeCommand(RedisCommand.ZPOPMIN, args, (cmd)->cmd.zpopmin(key, count),
				(cmd)->cmd.zpopmin(key, count), new ListConverter<>(new ScoredValueTupleConverter()));
	}

	@Override
	public String zRandMember(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.ZRANDMEMBER, args, (cmd)->cmd.zrandmember(SafeEncoder.encode(key)),
				(cmd)->cmd.zrandmember(SafeEncoder.encode(key)), SafeEncoder::encode);
	}

	@Override
	public byte[] zRandMember(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.ZRANDMEMBER, args, (cmd)->cmd.zrandmember(key),
				(cmd)->cmd.zrandmember(key));
	}

	@Override
	public List<String> zRandMember(final String key, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(count);
		return executeCommand(RedisCommand.ZRANDMEMBER, args, (cmd)->cmd.zrandmember(SafeEncoder.encode(key), count),
				(cmd)->cmd.zrandmember(SafeEncoder.encode(key), count), Converters.binaryListStringListConverter());
	}

	@Override
	public List<byte[]> zRandMember(final byte[] key, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(count);
		return executeCommand(RedisCommand.ZRANDMEMBER, args, (cmd)->cmd.zrandmember(key, count),
				(cmd)->cmd.zrandmember(key, count));
	}

	@Override
	public Tuple zRandMemberWithScores(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.ZRANDMEMBER, args, (cmd)->cmd.zrandmemberWithScores(SafeEncoder.encode(key)),
				(cmd)->cmd.zrandmemberWithScores(SafeEncoder.encode(key)), new ScoredValueTupleConverter());
	}

	@Override
	public Tuple zRandMemberWithScores(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.ZRANDMEMBER, args, (cmd)->cmd.zrandmemberWithScores(key),
				(cmd)->cmd.zrandmemberWithScores(key), new ScoredValueTupleConverter());
	}

	@Override
	public List<Tuple> zRandMemberWithScores(final String key, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(count);
		return executeCommand(RedisCommand.ZRANDMEMBER, args,
				(cmd)->cmd.zrandmemberWithScores(SafeEncoder.encode(key), count),
				(cmd)->cmd.zrandmemberWithScores(SafeEncoder.encode(key), count),
				new ListConverter<>(new ScoredValueTupleConverter()));
	}

	@Override
	public List<Tuple> zRandMemberWithScores(final byte[] key, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(count);
		return executeCommand(RedisCommand.ZRANDMEMBER, args, (cmd)->cmd.zrandmemberWithScores(key, count),
				(cmd)->cmd.zrandmemberWithScores(key, count),
				new ListConverter<>(new ScoredValueTupleConverter()));
	}

	@Override
	public List<String> zRange(final String key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end);
		return executeCommand(RedisCommand.ZRANGE, args, (cmd)->cmd.zrange(SafeEncoder.encode(key), start, end),
				(cmd)->cmd.zrange(SafeEncoder.encode(key), start, end), Converters.binaryListStringListConverter());
	}

	@Override
	public List<byte[]> zRange(final byte[] key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end);
		return executeCommand(RedisCommand.ZRANGE, args, (cmd)->cmd.zrange(key, start, end),
				(cmd)->cmd.zrange(key, start, end));
	}

	@Override
	public List<String> zRange(final String key, final long start, final long end, final ZRangeType type) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end).add(type);
		return zRange(SafeEncoder.encode(key), start, end, type, Converters.binaryListStringListConverter(), args);
	}

	@Override
	public List<byte[]> zRange(final byte[] key, final long start, final long end, final ZRangeType type) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end).add(type);
		return zRange(key, start, end, type, (v)->v, args);
	}

	@Override
	public List<String> zRange(final String key, final long start, final long end, final ZRangeType type,
	                           final boolean rev) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end).add(type)
				.add(rev ? "REV" : null);
		return zRange(SafeEncoder.encode(key), start, end, type, Converters.binaryListStringListConverter(), args);
	}

	@Override
	public List<byte[]> zRange(final byte[] key, final long start, final long end, final ZRangeType type,
	                           final boolean rev) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end).add(type)
				.add(rev ? "REV" : null);
		return zRange(key, start, end, type, (v)->v, args);
	}

	@Override
	public List<String> zRange(final String key, final long start, final long end, final boolean rev) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end).add(rev ? "REV" : null);
		return executeCommand(RedisCommand.ZRANGE, args, (cmd)->cmd.zrange(SafeEncoder.encode(key), start, end),
				(cmd)->cmd.zrange(SafeEncoder.encode(key), start, end), Converters.binaryListStringListConverter());
	}

	@Override
	public List<byte[]> zRange(final byte[] key, final long start, final long end, final boolean rev) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end).add(rev ? "REV" : null);
		return executeCommand(RedisCommand.ZRANGE, args, (cmd)->cmd.zrange(key, start, end),
				(cmd)->cmd.zrange(key, start, end));
	}

	@Override
	public List<String> zRange(final String key, final long start, final long end, final int offset, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end).add(Keyword.Common.LIMIT)
				.add(offset).add(count);
		return executeCommand(RedisCommand.ZRANGE, args, (cmd)->cmd.zrange(SafeEncoder.encode(key), start, end),
				(cmd)->cmd.zrange(SafeEncoder.encode(key), start, end), Converters.binaryListStringListConverter());
	}

	@Override
	public List<byte[]> zRange(final byte[] key, final long start, final long end, final int offset, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end).add(Keyword.Common.LIMIT)
				.add(offset).add(count);
		return executeCommand(RedisCommand.ZRANGE, args, (cmd)->cmd.zrange(key, start, end),
				(cmd)->cmd.zrange(key, start, end));
	}

	@Override
	public List<String> zRange(final String key, final long start, final long end, final ZRangeType type,
	                           final int offset, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end).add(type)
				.add(Keyword.Common.LIMIT)
				.add(offset).add(count);
		return zRange(SafeEncoder.encode(key), start, end, type, offset, count,
				Converters.binaryListStringListConverter(),
				args);
	}

	@Override
	public List<byte[]> zRange(final byte[] key, final long start, final long end, final ZRangeType type,
	                           final int offset, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end).add(type)
				.add(Keyword.Common.LIMIT)
				.add(offset).add(count);
		return zRange(key, start, end, type, offset, count, (v)->v, args);
	}

	@Override
	public List<String> zRange(final String key, final long start, final long end, final ZRangeType type,
	                           final boolean rev, final int offset, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end).add(type).add(rev ? "REV" : null)
				.add(Keyword.Common.LIMIT).add(offset).add(count);
		return zRange(SafeEncoder.encode(key), start, end, type, offset, count,
				Converters.binaryListStringListConverter(),
				args);
	}

	@Override
	public List<byte[]> zRange(final byte[] key, final long start, final long end, final ZRangeType type,
	                           final boolean rev, final int offset, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end).add(type).add(rev ? "REV" : null)
				.add(Keyword.Common.LIMIT).add(offset).add(count);
		return zRange(key, start, end, type, offset, count, (v)->v, args);
	}

	@Override
	public List<String> zRange(final String key, final long start, final long end, final boolean rev, final int offset,
	                           final int count) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end).add(rev ? "REV" : null)
				.add(Keyword.Common.LIMIT).add(offset).add(count);
		return executeCommand(RedisCommand.ZRANGE, args, (cmd)->cmd.zrange(SafeEncoder.encode(key), start, end),
				(cmd)->cmd.zrange(SafeEncoder.encode(key), start, end), Converters.binaryListStringListConverter());
	}

	@Override
	public List<byte[]> zRange(final byte[] key, final long start, final long end, final boolean rev, final int offset,
	                           final int count) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end).add(rev ? "REV" : null)
				.add(Keyword.Common.LIMIT).add(offset).add(count);
		return executeCommand(RedisCommand.ZRANGE, args, (cmd)->cmd.zrange(key, start, end),
				(cmd)->cmd.zrange(key, start, end));
	}

	@Override
	public List<Tuple> zRangeWithScores(final String key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end);
		return zRangeWithScores(SafeEncoder.encode(key), start, end, args);
	}

	@Override
	public List<Tuple> zRangeWithScores(final byte[] key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end);
		return zRangeWithScores(key, start, end, args);
	}

	@Override
	public List<Tuple> zRangeWithScores(final String key, final long start, final long end, final ZRangeType type) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end).add(type);
		return zRangeWithScores(SafeEncoder.encode(key), start, end, args);
	}

	@Override
	public List<Tuple> zRangeWithScores(final byte[] key, final long start, final long end, final ZRangeType type) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end).add(type);
		return zRangeWithScores(key, start, end, args);
	}

	@Override
	public List<Tuple> zRangeWithScores(final String key, final long start, final long end, final ZRangeType type,
	                                    final boolean rev) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end).add(type)
				.add(rev ? "REV" : null);
		return zRangeWithScores(SafeEncoder.encode(key), start, end, args);
	}

	@Override
	public List<Tuple> zRangeWithScores(final byte[] key, final long start, final long end, final ZRangeType type,
	                                    final boolean rev) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end).add(type)
				.add(rev ? "REV" : null);
		return zRangeWithScores(key, start, end, args);
	}

	@Override
	public List<Tuple> zRangeWithScores(final String key, final long start, final long end, final boolean rev) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end).add(rev ? "REV" : null);
		return zRangeWithScores(SafeEncoder.encode(key), start, end, args);
	}

	@Override
	public List<Tuple> zRangeWithScores(final byte[] key, final long start, final long end, final boolean rev) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end).add(rev ? "REV" : null);
		return zRangeWithScores(key, start, end, args);
	}

	@Override
	public List<Tuple> zRangeWithScores(final String key, final long start, final long end, final int offset,
	                                    final int count) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end).add(Keyword.Common.LIMIT)
				.add(offset).add(count);
		return zRangeWithScores(SafeEncoder.encode(key), start, end, args);
	}

	@Override
	public List<Tuple> zRangeWithScores(final byte[] key, final long start, final long end, final int offset,
	                                    final int count) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end).add(Keyword.Common.LIMIT)
				.add(offset).add(count);
		return zRangeWithScores(key, start, end, args);
	}

	@Override
	public List<Tuple> zRangeWithScores(final String key, final long start, final long end, final ZRangeType type,
	                                    final int offset, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end).add(type)
				.add(Keyword.Common.LIMIT)
				.add(offset).add(count);
		return zRangeWithScores(SafeEncoder.encode(key), start, end, args);
	}

	@Override
	public List<Tuple> zRangeWithScores(final byte[] key, final long start, final long end, final ZRangeType type,
	                                    final int offset, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end).add(type)
				.add(Keyword.Common.LIMIT)
				.add(offset).add(count);
		return zRangeWithScores(key, start, end, args);
	}

	@Override
	public List<Tuple> zRangeWithScores(final String key, final long start, final long end, final ZRangeType type,
	                                    final boolean rev, final int offset, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end).add(type).add(rev ? "REV" : null)
				.add(Keyword.Common.LIMIT).add(offset).add(count);
		return zRangeWithScores(SafeEncoder.encode(key), start, end, args);
	}

	@Override
	public List<Tuple> zRangeWithScores(final byte[] key, final long start, final long end, final ZRangeType type,
	                                    final boolean rev, final int offset, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end).add(type).add(rev ? "REV" : null)
				.add(Keyword.Common.LIMIT).add(offset).add(count);
		return zRangeWithScores(key, start, end, args);
	}

	@Override
	public List<Tuple> zRangeWithScores(final String key, final long start, final long end, final boolean rev,
	                                    final int offset, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end).add(rev ? "REV" : null)
				.add(Keyword.Common.LIMIT).add(offset).add(count);
		return zRangeWithScores(SafeEncoder.encode(key), start, end, args);
	}

	@Override
	public List<Tuple> zRangeWithScores(final byte[] key, final long start, final long end, final boolean rev,
	                                    final int offset, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end).add(rev ? "REV" : null)
				.add(Keyword.Common.LIMIT).add(offset).add(count);
		return zRangeWithScores(key, start, end, args);
	}

	@Override
	public List<String> zRangeByLex(final String key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);
		return zRangeByLex(SafeEncoder.encode(key), min, max, Converters.binaryListStringListConverter(), args);
	}

	@Override
	public List<byte[]> zRangeByLex(final byte[] key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);
		return zRangeByLex(key, min, max, (v)->v, args);
	}

	@Override
	public List<String> zRangeByLex(final String key, final double min, final double max, final int offset,
	                                final int count) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max).add(Keyword.Common.LIMIT)
				.add(offset).add(count);
		return zRangeByLex(SafeEncoder.encode(key), min, max, offset, count, Converters.binaryListStringListConverter(),
				args);
	}

	@Override
	public List<byte[]> zRangeByLex(final byte[] key, final double min, final double max, final int offset,
	                                final int count) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max).add(Keyword.Common.LIMIT)
				.add(offset).add(count);
		return zRangeByLex(key, min, max, offset, count, (v)->v, args);
	}

	@Override
	public List<String> zRangeByScore(final String key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);
		return executeCommand(RedisCommand.ZRANGEBYSCORE, args,
				(cmd)->cmd.zrangebyscore(SafeEncoder.encode(key), Range.create(min, max)),
				(cmd)->cmd.zrangebyscore(SafeEncoder.encode(key), Range.create(min, max)),
				Converters.binaryListStringListConverter());
	}

	@Override
	public List<byte[]> zRangeByScore(final byte[] key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);
		return executeCommand(RedisCommand.ZRANGEBYSCORE, args,
				(cmd)->cmd.zrangebyscore(key, Range.create(min, max)),
				(cmd)->cmd.zrangebyscore(key, Range.create(min, max)));
	}

	@Override
	public List<String> zRangeByScore(final String key, final double min, final double max, final int offset,
	                                  final int count) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max).add(Keyword.Common.LIMIT)
				.add(offset).add(count);
		return executeCommand(RedisCommand.ZRANGEBYSCORE, args,
				(cmd)->cmd.zrangebyscore(SafeEncoder.encode(key), Range.create(min, max), Limit.create(offset, count)),
				(cmd)->cmd.zrangebyscore(SafeEncoder.encode(key), Range.create(min, max), Limit.create(offset, count)),
				Converters.binaryListStringListConverter());
	}

	@Override
	public List<byte[]> zRangeByScore(final byte[] key, final double min, final double max, final int offset,
	                                  final int count) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max).add(Keyword.Common.LIMIT)
				.add(offset).add(count);
		return executeCommand(RedisCommand.ZRANGEBYSCORE, args,
				(cmd)->cmd.zrangebyscore(key, Range.create(min, max), Limit.create(offset, count)),
				(cmd)->cmd.zrangebyscore(key, Range.create(min, max), Limit.create(offset, count)));
	}

	@Override
	public List<Tuple> zRangeByScoreWithScores(final String key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max).add(Keyword.Common.WITHSCORES);
		return zRangeByScoreWithScores(SafeEncoder.encode(key), min, max, args);
	}

	@Override
	public List<Tuple> zRangeByScoreWithScores(final byte[] key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max).add(Keyword.Common.WITHSCORES);
		return zRangeByScoreWithScores(key, min, max, args);
	}

	@Override
	public List<Tuple> zRangeByScoreWithScores(final String key, final double min, final double max, final int offset,
	                                           final int count) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max).add(Keyword.Common.WITHSCORES)
				.add(Keyword.Common.LIMIT).add(offset).add(count);
		return zRangeByScoreWithScores(SafeEncoder.encode(key), min, max, offset, count, args);
	}

	@Override
	public List<Tuple> zRangeByScoreWithScores(final byte[] key, final double min, final double max, final int offset,
	                                           final int count) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max).add(Keyword.Common.WITHSCORES)
				.add(Keyword.Common.LIMIT).add(offset).add(count);
		return zRangeByScoreWithScores(key, min, max, offset, count, args);
	}

	@Override
	public Long zRangeStore(final String destKey, final String key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(destKey, key).add(start).add(end);
		return zRangeStore(SafeEncoder.encode(destKey), SafeEncoder.encode(key), start, end, Limit.unlimited(), args);
	}

	@Override
	public Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(destKey, key).add(start).add(end);
		return zRangeStore(destKey, key, start, end, Limit.unlimited(), args);
	}

	@Override
	public Long zRangeStore(final String destKey, final String key, final long start, final long end,
	                        final ZRangeType type) {
		final CommandArguments args = CommandArguments.create(destKey, key).add(start).add(end).add(type);
		return zRangeStore(SafeEncoder.encode(destKey), SafeEncoder.encode(key), start, end, type, Limit.unlimited(),
				args);
	}

	@Override
	public Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end,
	                        final ZRangeType type) {
		final CommandArguments args = CommandArguments.create(destKey, key).add(start).add(end).add(type);
		return zRangeStore(destKey, key, start, end, type, Limit.unlimited(), args);
	}

	@Override
	public Long zRangeStore(final String destKey, final String key, final long start, final long end,
	                        final ZRangeType type, final boolean rev) {
		final CommandArguments args = CommandArguments.create(destKey, key).add(start).add(end).add(type)
				.add(rev ? "REV" : null);
		return zRangeStore(SafeEncoder.encode(destKey), SafeEncoder.encode(key), start, end, type, Limit.unlimited(),
				args);
	}

	@Override
	public Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end,
	                        final ZRangeType type, final boolean rev) {
		final CommandArguments args = CommandArguments.create(destKey, key).add(start).add(end).add(type)
				.add(rev ? "REV" : null);
		return zRangeStore(destKey, key, start, end, type, Limit.unlimited(), args);
	}

	@Override
	public Long zRangeStore(final String destKey, final String key, final long start, final long end,
	                        final boolean rev) {
		final CommandArguments args = CommandArguments.create(destKey, key).add(start).add(end).add(rev ? "REV" : null);
		return zRangeStore(SafeEncoder.encode(destKey), SafeEncoder.encode(key), start, end, Limit.unlimited(), args);
	}

	@Override
	public Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end,
	                        final boolean rev) {
		final CommandArguments args = CommandArguments.create(destKey, key).add(start).add(end).add(rev ? "REV" : null);
		return zRangeStore(destKey, key, start, end, Limit.unlimited(), args);
	}

	@Override
	public Long zRangeStore(final String destKey, final String key, final long start, final long end, final int offset,
	                        final int count) {
		final CommandArguments args = CommandArguments.create(destKey, key).add(start).add(end)
				.add(Keyword.Common.LIMIT)
				.add(offset).add(count);
		return zRangeStore(SafeEncoder.encode(destKey), SafeEncoder.encode(key), start, end, Limit.unlimited(), args);
	}

	@Override
	public Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end, final int offset,
	                        final int count) {
		final CommandArguments args = CommandArguments.create(destKey, key).add(start).add(end)
				.add(Keyword.Common.LIMIT)
				.add(offset).add(count);
		return zRangeStore(destKey, key, start, end, Limit.unlimited(), args);
	}

	@Override
	public Long zRangeStore(final String destKey, final String key, final long start, final long end,
	                        final ZRangeType type, final int offset, final int count) {
		final CommandArguments args = CommandArguments.create(destKey, key).add(start).add(end).add(type)
				.add(Keyword.Common.LIMIT).add(offset).add(count);
		return zRangeStore(SafeEncoder.encode(destKey), SafeEncoder.encode(key), start, end, type,
				Limit.create(offset, count),
				args);
	}

	@Override
	public Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end,
	                        final ZRangeType type, final int offset, final int count) {
		final CommandArguments args = CommandArguments.create(destKey).add(key).add(start).add(end).add(type)
				.add(Keyword.Common.LIMIT).add(offset).add(count);
		return zRangeStore(destKey, key, start, end, type, Limit.create(offset, count), args);
	}

	@Override
	public Long zRangeStore(final String destKey, final String key, final long start, final long end,
	                        final ZRangeType type, final boolean rev, final int offset, final int count) {
		final CommandArguments args = CommandArguments.create(destKey, key).add(start).add(end).add(type)
				.add(rev ? "REV" : null).add(Keyword.Common.LIMIT).add(offset).add(count);
		return zRangeStore(SafeEncoder.encode(destKey), SafeEncoder.encode(key), start, end, type,
				Limit.create(offset, count),
				args);
	}

	@Override
	public Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end,
	                        final ZRangeType type, final boolean rev, final int offset, final int count) {
		final CommandArguments args = CommandArguments.create(destKey).add(key).add(start).add(end).add(type)
				.add(rev ? "REV" : null).add(Keyword.Common.LIMIT).add(offset).add(count);
		return zRangeStore(destKey, key, start, end, type, Limit.create(offset, count), args);
	}

	@Override
	public Long zRangeStore(final String destKey, final String key, final long start, final long end, final boolean rev,
	                        final int offset, final int count) {
		final CommandArguments args = CommandArguments.create(destKey, key).add(start).add(end).add(rev ? "REV" : null)
				.add(Keyword.Common.LIMIT).add(offset).add(count);
		return zRangeStore(SafeEncoder.encode(destKey), SafeEncoder.encode(key), start, end,
				Limit.create(offset, count), args);
	}

	@Override
	public Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end, final boolean rev,
	                        final int offset, final int count) {
		final CommandArguments args = CommandArguments.create(destKey).add(key).add(start).add(end)
				.add(rev ? "REV" : null)
				.add(Keyword.Common.LIMIT).add(offset).add(count);
		return zRangeStore(destKey, key, start, end, Limit.create(offset, count), args);
	}

	@Override
	public Long zRank(final String key, final String member) {
		final CommandArguments args = CommandArguments.create(key, member);
		return executeCommand(RedisCommand.ZRANK, args,
				(cmd)->cmd.zrank(SafeEncoder.encode(key), SafeEncoder.encode(member)),
				(cmd)->cmd.zrank(SafeEncoder.encode(key), SafeEncoder.encode(member)));
	}

	@Override
	public Long zRank(final byte[] key, final byte[] member) {
		final CommandArguments args = CommandArguments.create(key).add(member);
		return executeCommand(RedisCommand.ZRANK, args, (cmd)->cmd.zrank(key, member),
				(cmd)->cmd.zrank(key, member));
	}

	@Override
	public KeyValue<Long, Double> zRankWithScores(final String key, final String member) {
		final CommandArguments args = CommandArguments.create(key, member).add(Keyword.Common.WITHSCORE);
		return zRankWithScores(SafeEncoder.encode(key), SafeEncoder.encode(member), args);
	}

	@Override
	public KeyValue<Long, Double> zRankWithScores(final byte[] key, final byte[] member) {
		final CommandArguments args = CommandArguments.create(key).add(member).add(Keyword.Common.WITHSCORE);
		return zRankWithScores(key, member, args);
	}

	@Override
	public Long zRem(final String key, final String... members) {
		final CommandArguments args = CommandArguments.create(key).add(members);
		return executeCommand(RedisCommand.ZREM, args,
				(cmd)->cmd.zrem(SafeEncoder.encode(key), SafeEncoder.encode(members)),
				(cmd)->cmd.zrem(SafeEncoder.encode(key), SafeEncoder.encode(members)));
	}

	@Override
	public Long zRem(final byte[] key, final byte[]... members) {
		final CommandArguments args = CommandArguments.create(key).add(members);
		return executeCommand(RedisCommand.ZREM, args, (cmd)->cmd.zrem(key, members),
				(cmd)->cmd.zrem(key, members));
	}

	@Override
	public Long zRemRangeByLex(final String key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);
		return zRemRangeByLex(SafeEncoder.encode(key), min, max, args);
	}

	@Override
	public Long zRemRangeByLex(final byte[] key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);
		return zRemRangeByLex(key, min, max, args);
	}

	@Override
	public Long zRemRangeByRank(final String key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end);
		return executeCommand(RedisCommand.ZREMRANGEBYRANK, args,
				(cmd)->cmd.zremrangebyrank(SafeEncoder.encode(key), start, end),
				(cmd)->cmd.zremrangebyrank(SafeEncoder.encode(key), start, end));
	}

	@Override
	public Long zRemRangeByRank(final byte[] key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end);
		return executeCommand(RedisCommand.ZREMRANGEBYRANK, args, (cmd)->cmd.zremrangebyrank(key, start, end),
				(cmd)->cmd.zremrangebyrank(key, start, end));
	}

	@Override
	public Long zRemRangeByScore(final String key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);
		return executeCommand(RedisCommand.ZREMRANGEBYSCORE, args,
				(cmd)->cmd.zremrangebyscore(SafeEncoder.encode(key), Range.create(min, max)),
				(cmd)->cmd.zremrangebyscore(SafeEncoder.encode(key), Range.create(min, max)));
	}

	@Override
	public Long zRemRangeByScore(final byte[] key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);
		return executeCommand(RedisCommand.ZREMRANGEBYSCORE, args,
				(cmd)->cmd.zremrangebyscore(key, Range.create(min, max)),
				(cmd)->cmd.zremrangebyscore(key, Range.create(min, max)));
	}

	@Override
	public List<String> zRevRange(final String key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end);
		return executeCommand(RedisCommand.ZREVRANGE, args, (cmd)->cmd.zrevrange(SafeEncoder.encode(key), start, end),
				(cmd)->cmd.zrevrange(SafeEncoder.encode(key), start, end), Converters.binaryListStringListConverter());
	}

	@Override
	public List<byte[]> zRevRange(final byte[] key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end);
		return executeCommand(RedisCommand.ZREVRANGE, args, (cmd)->cmd.zrevrange(key, start, end),
				(cmd)->cmd.zrevrange(key, start, end));
	}

	@Override
	public List<Tuple> zRevRangeWithScores(final String key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end);
		return zRevRangeWithScores(SafeEncoder.encode(key), start, end, args);
	}

	@Override
	public List<Tuple> zRevRangeWithScores(final byte[] key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end);
		return zRevRangeWithScores(key, start, end, args);
	}

	@Override
	public List<String> zRevRangeByLex(final String key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);
		final Range<byte[]> range = Range.create(NumberUtils.double2bytes(min), NumberUtils.double2bytes(max));
		return executeCommand(RedisCommand.ZREVRANGEBYLEX, args,
				(cmd)->cmd.zrevrangebylex(SafeEncoder.encode(key), range),
				(cmd)->cmd.zrevrangebylex(SafeEncoder.encode(key), range),
				Converters.binaryListStringListConverter());
	}

	@Override
	public List<byte[]> zRevRangeByLex(final byte[] key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);
		final Range<byte[]> range = Range.create(NumberUtils.double2bytes(min), NumberUtils.double2bytes(max));
		return executeCommand(RedisCommand.ZREVRANGEBYLEX, args,
				(cmd)->cmd.zrevrangebylex(key, range),
				(cmd)->cmd.zrevrangebylex(key, range));
	}

	@Override
	public List<String> zRevRangeByLex(final String key, final double min, final double max, final int offset,
	                                   final int count) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max).add(Keyword.Common.LIMIT)
				.add(offset).add(count);
		final Range<byte[]> range = Range.create(NumberUtils.double2bytes(min), NumberUtils.double2bytes(max));
		final Limit limit = Limit.create(offset, count);
		return executeCommand(RedisCommand.ZREVRANGEBYLEX, args,
				(cmd)->cmd.zrevrangebylex(SafeEncoder.encode(key), range, limit),
				(cmd)->cmd.zrevrangebylex(SafeEncoder.encode(key), range, limit),
				Converters.binaryListStringListConverter());
	}

	@Override
	public List<byte[]> zRevRangeByLex(final byte[] key, final double min, final double max, final int offset,
	                                   final int count) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max).add(Keyword.Common.LIMIT)
				.add(offset).add(count);
		final Range<byte[]> range = Range.create(NumberUtils.double2bytes(min), NumberUtils.double2bytes(max));
		final Limit limit = Limit.create(offset, count);
		return executeCommand(RedisCommand.ZREVRANGEBYLEX, args,
				(cmd)->cmd.zrevrangebylex(key, range, limit),
				(cmd)->cmd.zrevrangebylex(key, range, limit));
	}

	@Override
	public List<String> zRevRangeByScore(final String key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);
		return executeCommand(RedisCommand.ZREVRANGEBYSCORE, args,
				(cmd)->cmd.zrevrangebyscore(SafeEncoder.encode(key), Range.create(min, max)),
				(cmd)->cmd.zrevrangebyscore(SafeEncoder.encode(key), Range.create(min, max)),
				Converters.binaryListStringListConverter());
	}

	@Override
	public List<byte[]> zRevRangeByScore(final byte[] key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);
		return executeCommand(RedisCommand.ZREVRANGEBYSCORE, args,
				(cmd)->cmd.zrevrangebyscore(key, Range.create(min, max)),
				(cmd)->cmd.zrevrangebyscore(key, Range.create(min, max)));
	}

	@Override
	public List<String> zRevRangeByScore(final String key, final double min, final double max, final int offset,
	                                     final int count) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max).add(Keyword.Common.LIMIT)
				.add(offset).add(count);
		return executeCommand(RedisCommand.ZREVRANGEBYSCORE, args,
				(cmd)->cmd.zrevrangebyscore(SafeEncoder.encode(key), Range.create(min, max),
						Limit.create(offset, count)),
				(cmd)->cmd.zrevrangebyscore(SafeEncoder.encode(key), Range.create(min, max),
						Limit.create(offset, count)),
				Converters.binaryListStringListConverter());
	}

	@Override
	public List<byte[]> zRevRangeByScore(final byte[] key, final double min, final double max, final int offset,
	                                     final int count) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max).add(Keyword.Common.LIMIT)
				.add(offset).add(count);
		return executeCommand(RedisCommand.ZREVRANGEBYSCORE, args,
				(cmd)->cmd.zrevrangebyscore(key, Range.create(min, max), Limit.create(offset, count)),
				(cmd)->cmd.zrevrangebyscore(key, Range.create(min, max), Limit.create(offset, count)));
	}

	@Override
	public List<Tuple> zRevRangeByScoreWithScores(final String key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);
		return zRevRangeByScoreWithScores(SafeEncoder.encode(key), min, max, args);
	}

	@Override
	public List<Tuple> zRevRangeByScoreWithScores(final byte[] key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);
		return zRevRangeByScoreWithScores(key, min, max, args);
	}

	@Override
	public List<Tuple> zRevRangeByScoreWithScores(final String key, final double min, final double max,
	                                              final int offset, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max).add(Keyword.Common.LIMIT)
				.add(offset).add(count);
		return zRevRangeByScoreWithScores(SafeEncoder.encode(key), min, max, offset, count, args);
	}

	@Override
	public List<Tuple> zRevRangeByScoreWithScores(final byte[] key, final double min, final double max,
	                                              final int offset, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max).add(Keyword.Common.LIMIT)
				.add(offset).add(count);
		return zRevRangeByScoreWithScores(key, min, max, offset, count, args);
	}

	@Override
	public Long zRevRank(final String key, final String member) {
		final CommandArguments args = CommandArguments.create(key, member);
		return executeCommand(RedisCommand.ZREVRANK, args,
				(cmd)->cmd.zrevrank(SafeEncoder.encode(key), SafeEncoder.encode(member)),
				(cmd)->cmd.zrevrank(SafeEncoder.encode(key), SafeEncoder.encode(member)));
	}

	@Override
	public Long zRevRank(final byte[] key, final byte[] member) {
		final CommandArguments args = CommandArguments.create(key, member);
		return executeCommand(RedisCommand.ZREVRANK, args, (cmd)->cmd.zrevrank(key, member),
				(cmd)->cmd.zrevrank(key, member));
	}

	@Override
	public KeyValue<Long, Double> zRevRankWithScore(final String key, final String member) {
		final CommandArguments args = CommandArguments.create(key, member).add(Keyword.Common.WITHSCORE);
		return executeCommand(RedisCommand.ZREVRANK, args,
				(cmd)->cmd.zrankWithScore(SafeEncoder.encode(key), SafeEncoder.encode(member)),
				(cmd)->cmd.zrankWithScore(SafeEncoder.encode(key), SafeEncoder.encode(member)),
				new ScoredValueKeyValueConverter<>((v)->v));
	}

	@Override
	public KeyValue<Long, Double> zRevRankWithScore(final byte[] key, final byte[] member) {
		final CommandArguments args = CommandArguments.create(key, member).add(Keyword.Common.WITHSCORE);
		return executeCommand(RedisCommand.ZREVRANK, args, (cmd)->cmd.zrankWithScore(key, member),
				(cmd)->cmd.zrankWithScore(key, member), new ScoredValueKeyValueConverter<>((v)->v));
	}

	@Override
	public ScanResult<Tuple> zScan(final String key, final String cursor) {
		final CommandArguments args = CommandArguments.create(key).add(cursor);
		return executeCommand(RedisCommand.ZSCAN, args,
				(cmd)->cmd.zscan(SafeEncoder.encode(key), new LettuceScanCursor(cursor)),
				(cmd)->cmd.zscan(SafeEncoder.encode(key), new LettuceScanCursor(cursor)),
				new ScanCursorConverter.ScoredValueScanCursorConverter());
	}

	@Override
	public ScanResult<Tuple> zScan(final byte[] key, final byte[] cursor) {
		final CommandArguments args = CommandArguments.create(key).add(cursor);
		return executeCommand(RedisCommand.ZSCAN, args, (cmd)->cmd.zscan(key, new LettuceScanCursor(cursor)),
				(cmd)->cmd.zscan(key, new LettuceScanCursor(cursor)),
				new ScanCursorConverter.ScoredValueScanCursorConverter());
	}

	@Override
	public ScanResult<Tuple> zScan(final String key, final String cursor, final String pattern) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(Keyword.Scan.MATCH).add(pattern);
		return zScan(SafeEncoder.encode(key), new LettuceScanCursor(cursor), new LettuceScanArgs(pattern), args);
	}

	@Override
	public ScanResult<Tuple> zScan(final byte[] key, final byte[] cursor, final byte[] pattern) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(Keyword.Scan.MATCH).add(pattern);
		return zScan(key, new LettuceScanCursor(cursor), new LettuceScanArgs(pattern), args);
	}

	@Override
	public ScanResult<Tuple> zScan(final String key, final String cursor, final String pattern, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(Keyword.Scan.MATCH).add(pattern)
				.add(Keyword.Common.COUNT).add(count);
		return zScan(SafeEncoder.encode(key), new LettuceScanCursor(cursor), new LettuceScanArgs(pattern, count), args);
	}

	@Override
	public ScanResult<Tuple> zScan(final byte[] key, final byte[] cursor, final byte[] pattern, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(Keyword.Scan.MATCH).add(pattern)
				.add(Keyword.Common.COUNT).add(count);
		return zScan(key, new LettuceScanCursor(cursor), new LettuceScanArgs(pattern, count), args);
	}

	@Override
	public ScanResult<Tuple> zScan(final String key, final String cursor, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(Keyword.Common.COUNT).add(count);
		return zScan(SafeEncoder.encode(key), new LettuceScanCursor(cursor), new LettuceScanArgs(count), args);
	}

	@Override
	public ScanResult<Tuple> zScan(final byte[] key, final byte[] cursor, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(Keyword.Common.COUNT).add(count);
		return zScan(key, new LettuceScanCursor(cursor), new LettuceScanArgs(count), args);
	}

	@Override
	public Double zScore(final String key, final String member) {
		final CommandArguments args = CommandArguments.create(key, member);
		return executeCommand(RedisCommand.ZSCORE, args,
				(cmd)->cmd.zscore(SafeEncoder.encode(key), SafeEncoder.encode(member)),
				(cmd)->cmd.zscore(SafeEncoder.encode(key), SafeEncoder.encode(member)));
	}

	@Override
	public Double zScore(final byte[] key, final byte[] member) {
		final CommandArguments args = CommandArguments.create(key, member);
		return executeCommand(RedisCommand.ZSCORE, args, (cmd)->cmd.zscore(key, member),
				(cmd)->cmd.zscore(key, member));
	}

	@Override
	public List<String> zUnion(final String... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return executeCommand(RedisCommand.ZUNION, args, (cmd)->cmd.zunion(SafeEncoder.encode(keys)),
				(cmd)->cmd.zunion(SafeEncoder.encode(keys)), Converters.binaryListStringListConverter());
	}

	@Override
	public List<byte[]> zUnion(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return executeCommand(RedisCommand.ZUNION, args, (cmd)->cmd.zunion(keys),
				(cmd)->cmd.zunion(keys));
	}

	@Override
	public List<String> zUnion(final String[] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create(keys).add("AGGREGATE").add(aggregate);
		return stringZUnion(SafeEncoder.encode(keys), new LettuceZAggregateArgs(aggregate), args);
	}

	@Override
	public List<byte[]> zUnion(final byte[][] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create(keys).add("AGGREGATE").add(aggregate);
		return binaryZUnion(keys, new LettuceZAggregateArgs(aggregate), args);
	}

	@Override
	public List<String> zUnion(final String[] keys, final Aggregate aggregate, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add("AGGREGATE").add(aggregate).add("WEIGHTS")
				.add(weights);
		return stringZUnion(SafeEncoder.encode(keys), new LettuceZAggregateArgs(aggregate, weights), args);
	}

	@Override
	public List<byte[]> zUnion(final byte[][] keys, final Aggregate aggregate, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add("AGGREGATE").add(aggregate).add("WEIGHTS")
				.add(weights);
		return binaryZUnion(keys, new LettuceZAggregateArgs(aggregate, weights), args);
	}

	@Override
	public List<String> zUnion(final String[] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add("WEIGHTS").add(weights);
		return stringZUnion(SafeEncoder.encode(keys), new LettuceZAggregateArgs(weights), args);
	}

	@Override
	public List<byte[]> zUnion(final byte[][] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add("WEIGHTS").add(weights);
		return binaryZUnion(keys, new LettuceZAggregateArgs(weights), args);
	}

	@Override
	public List<Tuple> zUnionWithScores(final String... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return executeCommand(RedisCommand.ZUNION, args, (cmd)->cmd.zunionWithScores(SafeEncoder.encode(keys)),
				(cmd)->cmd.zunionWithScores(SafeEncoder.encode(keys)),
				new ListConverter<>(new ScoredValueTupleConverter()));
	}

	@Override
	public List<Tuple> zUnionWithScores(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return executeCommand(RedisCommand.ZUNION, args, (cmd)->cmd.zunionWithScores(keys),
				(cmd)->cmd.zunionWithScores(keys), new ListConverter<>(new ScoredValueTupleConverter()));
	}

	@Override
	public List<Tuple> zUnionWithScores(final String[] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create(keys).add("AGGREGATE").add(aggregate);
		return zUnionWithScores(SafeEncoder.encode(keys), new LettuceZAggregateArgs(aggregate), args);
	}

	@Override
	public List<Tuple> zUnionWithScores(final byte[][] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create(keys).add("AGGREGATE").add(aggregate);
		return zUnionWithScores(keys, new LettuceZAggregateArgs(aggregate), args);
	}

	@Override
	public List<Tuple> zUnionWithScores(final String[] keys, final Aggregate aggregate, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add("AGGREGATE").add(aggregate).add("WEIGHTS")
				.add(weights);
		return zUnionWithScores(SafeEncoder.encode(keys), new LettuceZAggregateArgs(aggregate, weights), args);
	}

	@Override
	public List<Tuple> zUnionWithScores(final byte[][] keys, final Aggregate aggregate, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add("AGGREGATE").add(aggregate).add("WEIGHTS")
				.add(weights);
		return zUnionWithScores(keys, new LettuceZAggregateArgs(aggregate, weights), args);
	}

	@Override
	public List<Tuple> zUnionWithScores(final String[] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add("WEIGHTS").add(weights);
		return zUnionWithScores(SafeEncoder.encode(keys), new LettuceZAggregateArgs(weights), args);
	}

	@Override
	public List<Tuple> zUnionWithScores(final byte[][] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add("WEIGHTS").add(weights);
		return zUnionWithScores(keys, new LettuceZAggregateArgs(weights), args);
	}

	@Override
	public Long zUnionStore(final String destKey, final String... keys) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys);
		return executeCommand(RedisCommand.ZUNIONSTORE, args,
				(cmd)->cmd.zunionstore(SafeEncoder.encode(destKey), SafeEncoder.encode(keys)),
				(cmd)->cmd.zunionstore(SafeEncoder.encode(destKey), SafeEncoder.encode(keys)));
	}

	@Override
	public Long zUnionStore(final byte[] destKey, final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys);
		return executeCommand(RedisCommand.ZUNIONSTORE, args, (cmd)->cmd.zunionstore(destKey, keys),
				(cmd)->cmd.zunionstore(destKey, keys));
	}

	@Override
	public Long zUnionStore(final String destKey, final String[] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys).add("AGGREGATE").add(aggregate);
		return zUnionStore(SafeEncoder.encode(destKey), SafeEncoder.encode(keys), new LettuceZStoreArgs(aggregate),
				args);
	}

	@Override
	public Long zUnionStore(final byte[] destKey, final byte[][] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys).add("AGGREGATE").add(aggregate);
		return zUnionStore(destKey, keys, new LettuceZStoreArgs(aggregate), args);
	}

	@Override
	public Long zUnionStore(final String destKey, final String[] keys, final Aggregate aggregate,
	                        final double... weights) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys).add("AGGREGATE").add(aggregate)
				.add("WEIGHTS").add(weights);
		return zUnionStore(SafeEncoder.encode(destKey), SafeEncoder.encode(keys),
				new LettuceZStoreArgs(aggregate, weights), args);
	}

	@Override
	public Long zUnionStore(final byte[] destKey, final byte[][] keys, final Aggregate aggregate,
	                        final double... weights) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys).add("AGGREGATE").add(aggregate)
				.add("WEIGHTS").add(weights);
		return zUnionStore(destKey, keys, new LettuceZStoreArgs(aggregate, weights), args);
	}

	@Override
	public Long zUnionStore(final String destKey, final String[] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys).add("WEIGHTS").add(weights);
		return zUnionStore(SafeEncoder.encode(destKey), SafeEncoder.encode(keys), new LettuceZStoreArgs(weights), args);
	}

	@Override
	public Long zUnionStore(final byte[] destKey, final byte[][] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys).add("WEIGHTS").add(weights);
		return zUnionStore(destKey, keys, new LettuceZStoreArgs(weights), args);
	}

	private <K> KeyValue<K, List<Tuple>> bzMPop(final byte[][] keys, final int timeout, final MinMax minMax,
	                                            final int count, final Converter<byte[], K> keyConverter,
	                                            final CommandArguments args) {
		return executeCommand(RedisCommand.BZMPOP, args,
				(cmd)->cmd.bzmpop(timeout, (long) count, new LettuceZPopArgs(minMax), keys),
				(cmd)->cmd.bzmpop(timeout, (long) count, new LettuceZPopArgs(minMax), keys),
				new KeyValueConverter<>(keyConverter, new ListConverter<>(new ScoredValueTupleConverter())));
	}

	@SuppressWarnings({"unchecked"})
	private Long zAdd(final byte[] key, final Tuple[] members, final CommandArguments args) {
		final ScoredValue<byte[]>[] scoredValues = new ScoredValue[members.length];

		for(int i = 0; i < members.length; i++){
			scoredValues[i] = ScoredValue.just(members[i].getScore(), members[i].getBinaryElement());
		}

		return executeCommand(RedisCommand.ZADD, args, (cmd)->cmd.zadd(key, scoredValues),
				(cmd)->cmd.zadd(key, scoredValues));
	}

	@SuppressWarnings({"unchecked"})
	private Long zAdd(final byte[] key, final Tuple[] members, final ZAddArgument argument,
	                  final CommandArguments args) {
		final ScoredValue<byte[]>[] scoredValues = new ScoredValue[members.length];

		for(int i = 0; i < members.length; i++){
			scoredValues[i] = ScoredValue.just(members[i].getScore(), members[i].getBinaryElement());
		}

		return executeCommand(RedisCommand.ZADD, args,
				(cmd)->cmd.zadd(key, new LettuceZAddArgs(argument), scoredValues),
				(cmd)->cmd.zadd(key, new LettuceZAddArgs(argument), scoredValues));
	}

	private List<Tuple> zDiffWithScores(final byte[][] keys, final CommandArguments args) {
		return executeCommand(RedisCommand.ZDIFF, args, (cmd)->cmd.zdiffWithScores(keys),
				(cmd)->cmd.zdiffWithScores(keys), new ListConverter<>(new ScoredValueTupleConverter()));
	}

	private List<String> stringZInter(final byte[][] keys, final LettuceZAggregateArgs zAggregateArgs,
	                                  final CommandArguments args) {
		return executeCommand(RedisCommand.ZINTER, args, (cmd)->cmd.zinter(zAggregateArgs, keys),
				(cmd)->cmd.zinter(zAggregateArgs, keys), Converters.binaryListStringListConverter());
	}

	private List<byte[]> binaryZInter(final byte[][] keys, final LettuceZAggregateArgs zAggregateArgs,
	                                  final CommandArguments args) {
		return executeCommand(RedisCommand.ZINTER, args, (cmd)->cmd.zinter(zAggregateArgs, keys),
				(cmd)->cmd.zinter(zAggregateArgs, keys));
	}

	private List<Tuple> zInterWithScores(final byte[][] keys, final LettuceZAggregateArgs zAggregateArgs,
	                                     final CommandArguments args) {
		return executeCommand(RedisCommand.ZINTER, args, (cmd)->cmd.zinterWithScores(zAggregateArgs, keys),
				(cmd)->cmd.zinterWithScores(zAggregateArgs, keys),
				new ListConverter<>(new ScoredValueTupleConverter()));
	}

	private Long zInterStore(final byte[] destKey, final byte[][] keys, final LettuceZStoreArgs zStoreArgs,
	                         final CommandArguments args) {
		return executeCommand(RedisCommand.ZINTERSTORE, args, (cmd)->cmd.zinterstore(destKey, zStoreArgs, keys),
				(cmd)->cmd.zinterstore(destKey, zStoreArgs, keys));
	}

	private Long zLexCount(final byte[] key, final double min, final double max, final CommandArguments args) {
		final Range<byte[]> range = Range.create(NumberUtils.double2bytes(min), NumberUtils.double2bytes(max));
		return executeCommand(RedisCommand.ZLEXCOUNT, args, (cmd)->cmd.zlexcount(key, range),
				(cmd)->cmd.zlexcount(key, range));
	}

	private <K> KeyValue<K, List<Tuple>> zMPop(final byte[][] keys, final MinMax minMax, final int count,
	                                           final Converter<byte[], K> keyConverter, final CommandArguments args) {
		return executeCommand(RedisCommand.ZMPOP, args, (cmd)->cmd.zmpop(count, new LettuceZPopArgs(minMax), keys),
				(cmd)->cmd.zmpop(count, new LettuceZPopArgs(minMax), keys),
				new KeyValueConverter<>(keyConverter, new ListConverter<>(new ScoredValueTupleConverter())));
	}

	private <V> List<V> zRange(final byte[] key, final long start, final long end, final ZRangeType type,
	                           final Converter<List<byte[]>, List<V>> converter, final CommandArguments args) {
		if(type == ZRangeType.BYLEX){
			final Range<byte[]> range = Range.create(NumberUtils.long2bytes(start), NumberUtils.long2bytes(end));
			return executeCommand(RedisCommand.ZRANGE, args, (cmd)->cmd.zrangebylex(key, range),
					(cmd)->cmd.zrangebylex(key, range), converter);
		}else if(type == ZRangeType.BYSCORE){
			return executeCommand(RedisCommand.ZRANGE, args, (cmd)->cmd.zrangebyscore(key, Range.create(start, end)),
					(cmd)->cmd.zrangebyscore(key, Range.create(start, end)), converter);
		}else{
			return executeCommand(RedisCommand.ZRANGE, args, (cmd)->cmd.zrange(key, start, end),
					(cmd)->cmd.zrange(key, start, end), converter);
		}
	}

	private <V> List<V> zRange(final byte[] key, final long start, final long end, final ZRangeType type,
	                           final int offset, final int count, final Converter<List<byte[]>, List<V>> converter,
	                           final CommandArguments args) {
		final Limit limit = Limit.create(offset, count);
		if(type == ZRangeType.BYLEX){
			final Range<byte[]> range = Range.create(NumberUtils.long2bytes(start), NumberUtils.long2bytes(end));
			return executeCommand(RedisCommand.ZRANGE, args,
					(cmd)->cmd.zrangebylex(key, range, limit),
					(cmd)->cmd.zrangebylex(key, range, limit), converter);
		}else if(type == ZRangeType.BYSCORE){
			return executeCommand(RedisCommand.ZRANGE, args,
					(cmd)->cmd.zrangebyscore(key, Range.create(start, end), limit),
					(cmd)->cmd.zrangebyscore(key, Range.create(start, end), limit), converter);
		}else{
			return executeCommand(RedisCommand.ZRANGE, args, (cmd)->cmd.zrange(key, start, end),
					(cmd)->cmd.zrange(key, start, end), converter);
		}
	}

	private List<Tuple> zRangeWithScores(final byte[] key, final long start, final long end,
	                                     final CommandArguments args) {
		return executeCommand(RedisCommand.ZRANGE, args, (cmd)->cmd.zrangeWithScores(key, start, end),
				(cmd)->cmd.zrangeWithScores(key, start, end),
				new ListConverter<>(new ScoredValueTupleConverter()));
	}

	private <T> List<T> zRangeByLex(final byte[] key, final double min, final double max,
	                                final Converter<List<byte[]>, List<T>> converter, final CommandArguments args) {
		final Range<byte[]> range = Range.create(NumberUtils.double2bytes(min), NumberUtils.double2bytes(max));
		return executeCommand(RedisCommand.ZRANGEBYLEX, args, (cmd)->cmd.zrangebylex(key, range),
				(cmd)->cmd.zrangebylex(key, range), converter);
	}

	private <T> List<T> zRangeByLex(final byte[] key, final double min, final double max, final int offset,
	                                final int count, final Converter<List<byte[]>, List<T>> converter,
	                                final CommandArguments args) {
		final Range<byte[]> range = Range.create(NumberUtils.double2bytes(min), NumberUtils.double2bytes(max));
		final Limit limit = Limit.create(offset, count);
		return executeCommand(RedisCommand.ZRANGEBYLEX, args, (cmd)->cmd.zrangebylex(key, range, limit),
				(cmd)->cmd.zrangebylex(key, range, limit), converter);
	}

	private List<Tuple> zRangeByScoreWithScores(final byte[] key, final double min, final double max,
	                                            final CommandArguments args) {
		return executeCommand(RedisCommand.ZRANGEBYSCORE, args,
				(cmd)->cmd.zrangebyscoreWithScores(key, Range.create(min, max)),
				(cmd)->cmd.zrangebyscoreWithScores(key, Range.create(min, max)),
				new ListConverter<>(new ScoredValueTupleConverter()));
	}

	private List<Tuple> zRangeByScoreWithScores(final byte[] key, final double min, final double max, final int offset,
	                                            final int count, final CommandArguments args) {
		return executeCommand(RedisCommand.ZRANGEBYSCORE, args,
				(cmd)->cmd.zrangebyscoreWithScores(key, Range.create(min, max), Limit.create(offset, count)),
				(cmd)->cmd.zrangebyscoreWithScores(key, Range.create(min, max), Limit.create(offset, count)),
				new ListConverter<>(new ScoredValueTupleConverter()));
	}

	private Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end,
	                         final Limit limit, final CommandArguments args) {
		return executeCommand(RedisCommand.ZRANGESTORE, args,
				(cmd)->cmd.zrangestore(destKey, key, Range.create(start, end)),
				(cmd)->cmd.zrangestore(destKey, key, Range.create(start, end)));
	}

	private Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end,
	                         final ZRangeType type, final Limit limit, final CommandArguments args) {
		final Range<byte[]> range = Range.create(NumberUtils.long2bytes(start), NumberUtils.long2bytes(end));
		if(type == ZRangeType.BYLEX){
			return executeCommand(RedisCommand.ZRANGESTORE, args,
					(cmd)->cmd.zrangestorebylex(destKey, key, range, limit),
					(cmd)->cmd.zrangestorebylex(destKey, key, range, limit));
		}else if(type == ZRangeType.BYSCORE){
			return executeCommand(RedisCommand.ZRANGESTORE, args,
					(cmd)->cmd.zrangestorebyscore(destKey, key, Range.create(start, end), limit),
					(cmd)->cmd.zrangestorebyscore(destKey, key, Range.create(start, end), limit));
		}else{
			return executeCommand(RedisCommand.ZRANGESTORE, args,
					(cmd)->cmd.zrangestore(destKey, key, Range.create(start, end)),
					(cmd)->cmd.zrangestore(destKey, key, Range.create(start, end)));
		}
	}

	private KeyValue<Long, Double> zRankWithScores(final byte[] key, final byte[] member, final CommandArguments args) {
		return executeCommand(RedisCommand.ZRANK, args, (cmd)->cmd.zrankWithScore(key, member),
				(cmd)->cmd.zrankWithScore(key, member), new ScoredValueKeyValueConverter<>((v)->v));
	}

	private Long zRemRangeByLex(final byte[] key, final double min, final double max, final CommandArguments args) {
		final Range<byte[]> range = Range.create(NumberUtils.double2bytes(min), NumberUtils.double2bytes(max));
		return executeCommand(RedisCommand.ZREMRANGEBYLEX, args,
				(cmd)->cmd.zremrangebylex(key, range),
				(cmd)->cmd.zremrangebylex(key, range));
	}

	private List<Tuple> zRevRangeWithScores(final byte[] key, final long start, final long end,
	                                        final CommandArguments args) {
		return executeCommand(RedisCommand.ZREVRANGE, args, (cmd)->cmd.zrevrangeWithScores(key, start, end),
				(cmd)->cmd.zrevrangeWithScores(key, start, end), new ListConverter<>(new ScoredValueTupleConverter()));
	}

	private List<Tuple> zRevRangeByScoreWithScores(final byte[] key, final double min, final double max,
	                                               final CommandArguments args) {
		return executeCommand(RedisCommand.ZREVRANGEBYSCORE, args,
				(cmd)->cmd.zrevrangebyscoreWithScores(key, Range.create(min, max)),
				(cmd)->cmd.zrevrangebyscoreWithScores(key, Range.create(min, max)),
				new ListConverter<>(new ScoredValueTupleConverter()));
	}

	private List<Tuple> zRevRangeByScoreWithScores(final byte[] key, final double min, final double max,
	                                               final int offset, final int count, final CommandArguments args) {
		return executeCommand(RedisCommand.ZREVRANGEBYSCORE, args,
				(cmd)->cmd.zrevrangebyscoreWithScores(key, Range.create(min, max), Limit.create(offset, count)),
				(cmd)->cmd.zrevrangebyscoreWithScores(key, Range.create(min, max), Limit.create(offset, count)),
				new ListConverter<>(new ScoredValueTupleConverter()));
	}

	private ScanResult<Tuple> zScan(final byte[] key, final LettuceScanCursor cursor, final LettuceScanArgs scanArgs,
	                                final CommandArguments args) {
		return executeCommand(RedisCommand.ZSCAN, args, (cmd)->cmd.zscan(key, cursor, scanArgs),
				(cmd)->cmd.zscan(key, cursor, scanArgs), new ScanCursorConverter.ScoredValueScanCursorConverter());
	}

	private List<String> stringZUnion(final byte[][] keys, final LettuceZAggregateArgs aggregateArgs,
	                                  final CommandArguments args) {
		return executeCommand(RedisCommand.ZUNION, args, (cmd)->cmd.zunion(aggregateArgs, keys),
				(cmd)->cmd.zunion(aggregateArgs, keys), Converters.binaryListStringListConverter());
	}

	private List<byte[]> binaryZUnion(final byte[][] keys, final LettuceZAggregateArgs aggregateArgs,
	                                  final CommandArguments args) {
		return executeCommand(RedisCommand.ZUNION, args, (cmd)->cmd.zunion(aggregateArgs, keys),
				(cmd)->cmd.zunion(aggregateArgs, keys));
	}

	private List<Tuple> zUnionWithScores(final byte[][] keys, final LettuceZAggregateArgs aggregateArgs,
	                                     final CommandArguments args) {
		return executeCommand(RedisCommand.ZUNION, args, (cmd)->cmd.zunionWithScores(aggregateArgs, keys),
				(cmd)->cmd.zunionWithScores(aggregateArgs, keys),
				new ListConverter<>(new ScoredValueTupleConverter()));
	}

	private Long zUnionStore(final byte[] destKey, final byte[][] keys, final LettuceZStoreArgs zStoreArgs,
	                         final CommandArguments args) {
		return executeCommand(RedisCommand.ZUNIONSTORE, args, (cmd)->cmd.zunionstore(destKey, zStoreArgs, keys),
				(cmd)->cmd.zunionstore(destKey, zStoreArgs, keys));
	}

}
