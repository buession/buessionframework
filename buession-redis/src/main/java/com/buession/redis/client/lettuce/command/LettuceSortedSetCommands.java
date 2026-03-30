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
import com.buession.redis.core.internal.convert.BinaryListStringListConverter;
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
		final CommandArguments args = CommandArguments.create(timeout).add(keys.length, keys).add(minMax);
		return bzMPop(rawBinaryKeys(keys), timeout, minMax, 1, SafeEncoder::encode, args);
	}

	@Override
	public KeyValue<byte[], List<Tuple>> bzMPop(final byte[][] keys, final int timeout, final MinMax minMax) {
		final CommandArguments args = CommandArguments.create(timeout).add(keys.length, keys).add(minMax);
		return bzMPop(rawKeys(keys), timeout, minMax, 1, (k)->k, args);
	}

	@Override
	public KeyValue<String, List<Tuple>> bzMPop(final String[] keys, final int timeout, final MinMax minMax,
	                                            final int count) {
		final CommandArguments args = CommandArguments.create(timeout).add(keys.length, keys).add(minMax)
				.add(Keyword.Common.COUNT, count);
		return bzMPop(rawBinaryKeys(keys), timeout, minMax, count, SafeEncoder::encode, args);
	}

	@Override
	public KeyValue<byte[], List<Tuple>> bzMPop(final byte[][] keys, final int timeout, final MinMax minMax,
	                                            final int count) {
		final CommandArguments args = CommandArguments.create(timeout).add(keys.length, keys).add(minMax)
				.add(Keyword.Common.COUNT, count);
		return bzMPop(rawKeys(keys), timeout, minMax, count, (k)->k, args);
	}

	@Override
	public KeyValue<String, Tuple> bzPopMax(final String[] keys, final int timeout) {
		final CommandArguments args = CommandArguments.create(keys).add(timeout);
		return executeCommand(RedisCommand.BZPOPMAX, args, (cmd)->cmd.bzpopmax(timeout, rawBinaryKeys(keys)),
				new KeyValueConverter<>(SafeEncoder::encode, new ScoredValueTupleConverter()));
	}

	@Override
	public KeyValue<byte[], Tuple> bzPopMax(final byte[][] keys, final int timeout) {
		final CommandArguments args = CommandArguments.create(keys).add(timeout);
		return executeCommand(RedisCommand.BZPOPMAX, args, (cmd)->cmd.bzpopmax(timeout, rawKeys(keys)),
				new KeyValueConverter<>((k)->k, new ScoredValueTupleConverter()));
	}

	@Override
	public KeyValue<String, Tuple> bzPopMin(final String[] keys, final int timeout) {
		final CommandArguments args = CommandArguments.create(keys).add(timeout);
		return executeCommand(RedisCommand.BZPOPMIN, args, (cmd)->cmd.bzpopmin(timeout, rawBinaryKeys(keys)),
				new KeyValueConverter<>(SafeEncoder::encode, new ScoredValueTupleConverter()));
	}

	@Override
	public KeyValue<byte[], Tuple> bzPopMin(final byte[][] keys, final int timeout) {
		final CommandArguments args = CommandArguments.create(keys).add(timeout);
		return executeCommand(RedisCommand.BZPOPMIN, args, (cmd)->cmd.bzpopmin(timeout, rawKeys(keys)),
				new KeyValueConverter<>((k)->k, new ScoredValueTupleConverter()));
	}

	@Override
	public Long zAdd(final String key, final Tuple... members) {
		final CommandArguments args = CommandArguments.create(key).add(members);
		return zAdd(rawBinaryKey(key), members, args);
	}

	@Override
	public Long zAdd(final byte[] key, final Tuple... members) {
		final CommandArguments args = CommandArguments.create(key).add(members);
		return zAdd(rawKey(key), members, args);
	}

	@Override
	public Long zAdd(final String key, final Tuple[] members, final ZAddArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(argument).add(members);
		return zAdd(rawBinaryKey(key), members, argument, args);
	}

	@Override
	public Long zAdd(final byte[] key, final Tuple[] members, final ZAddArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(argument).add(members);
		return zAdd(rawKey(key), members, argument, args);
	}

	@Override
	public Long zCard(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.ZCARD, args, (cmd)->cmd.zcard(rawBinaryKey(key)));
	}

	@Override
	public Long zCard(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.ZCARD, args, (cmd)->cmd.zcard(rawKey(key)));
	}

	@Override
	public Long zCount(final String key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min, max);
		return executeCommand(RedisCommand.ZCOUNT, args, (cmd)->cmd.zcount(rawBinaryKey(key), Range.create(min, max)));
	}

	@Override
	public Long zCount(final byte[] key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min, max);
		return executeCommand(RedisCommand.ZCOUNT, args, (cmd)->cmd.zcount(rawKey(key), Range.create(min, max)));
	}

	@Override
	public List<String> zDiff(final String... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return executeCommand(RedisCommand.ZDIFF, args, (cmd)->cmd.zdiff(rawBinaryKeys(keys)),
				new BinaryListStringListConverter());
	}

	@Override
	public List<byte[]> zDiff(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return executeCommand(RedisCommand.ZDIFF, args, (cmd)->cmd.zdiff(rawKeys(keys)));
	}

	@Override
	public List<Tuple> zDiffWithScores(final String... keys) {
		final CommandArguments args = CommandArguments.create(keys).add(Keyword.Common.WITHSCORES);
		return zDiffWithScores(rawBinaryKeys(keys), args);
	}

	@Override
	public List<Tuple> zDiffWithScores(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(keys).add(Keyword.Common.WITHSCORES);
		return zDiffWithScores(rawKeys(keys), args);
	}

	@Override
	public Long zDiffStore(final String destKey, final String... keys) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys);
		return executeCommand(RedisCommand.ZDIFFSTORE, args,
				(cmd)->cmd.zdiffstore(rawBinaryKey(destKey), rawBinaryKeys(keys)));
	}

	@Override
	public Long zDiffStore(final byte[] destKey, final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys);
		return executeCommand(RedisCommand.ZDIFFSTORE, args, (cmd)->cmd.zdiffstore(rawKey(destKey), rawKeys(keys)));
	}

	@Override
	public Double zIncrBy(final String key, final double increment, final String member) {
		final CommandArguments args = CommandArguments.create(key).add(increment).add(member);
		return executeCommand(RedisCommand.ZINCRBY, args,
				(cmd)->cmd.zincrby(rawBinaryKey(key), increment, SafeEncoder.encode(member)));
	}

	@Override
	public Double zIncrBy(final byte[] key, final double increment, final byte[] member) {
		final CommandArguments args = CommandArguments.create(key).add(increment).add(member);
		return executeCommand(RedisCommand.ZINCRBY, args, (cmd)->cmd.zincrby(rawKey(key), increment, member));
	}

	@Override
	public List<String> zInter(final String... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return executeCommand(RedisCommand.ZINTER, args, (cmd)->cmd.zinter(rawBinaryKeys(keys)),
				new BinaryListStringListConverter());
	}

	@Override
	public List<byte[]> zInter(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return executeCommand(RedisCommand.ZINTER, args, (cmd)->cmd.zinter(rawKeys(keys)));
	}

	@Override
	public List<String> zInter(final String[] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create(keys).add("AGGREGATE", aggregate);
		return stringZInter(rawBinaryKeys(keys), new LettuceZAggregateArgs(aggregate), args);
	}

	@Override
	public List<byte[]> zInter(final byte[][] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create(keys).add("AGGREGATE", aggregate);
		return binaryZInter(rawKeys(keys), new LettuceZAggregateArgs(aggregate), args);
	}

	@Override
	public List<String> zInter(final String[] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add("WEIGHTS", weights);
		return stringZInter(rawBinaryKeys(keys), new LettuceZAggregateArgs(weights), args);
	}

	@Override
	public List<byte[]> zInter(final byte[][] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add("WEIGHTS", weights);
		return binaryZInter(rawKeys(keys), new LettuceZAggregateArgs(weights), args);
	}

	@Override
	public List<String> zInter(final String[] keys, final Aggregate aggregate, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add("AGGREGATE", aggregate).add("WEIGHTS", weights);
		return stringZInter(rawBinaryKeys(keys), new LettuceZAggregateArgs(aggregate, weights), args);
	}

	@Override
	public List<byte[]> zInter(final byte[][] keys, final Aggregate aggregate, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add("AGGREGATE", aggregate).add("WEIGHTS", weights);
		return binaryZInter(rawKeys(keys), new LettuceZAggregateArgs(aggregate, weights), args);
	}

	@Override
	public List<Tuple> zInterWithScores(final String... keys) {
		final CommandArguments args = CommandArguments.create(keys).add(Keyword.Common.WITHSCORES);
		return executeCommand(RedisCommand.ZINTER, args, (cmd)->cmd.zinterWithScores(rawBinaryKeys(keys)),
				new ListConverter<>(new ScoredValueTupleConverter()));
	}

	@Override
	public List<Tuple> zInterWithScores(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(keys).add(Keyword.Common.WITHSCORES);
		return executeCommand(RedisCommand.ZINTER, args, (cmd)->cmd.zinterWithScores(rawKeys(keys)),
				new ListConverter<>(new ScoredValueTupleConverter()));
	}

	@Override
	public List<Tuple> zInterWithScores(final String[] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create(keys).add("AGGREGATE", aggregate)
				.add(Keyword.Common.WITHSCORES);
		return zInterWithScores(rawBinaryKeys(keys), new LettuceZAggregateArgs(aggregate), args);
	}

	@Override
	public List<Tuple> zInterWithScores(final byte[][] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create(keys).add("AGGREGATE", aggregate)
				.add(Keyword.Common.WITHSCORES);
		return zInterWithScores(rawKeys(keys), new LettuceZAggregateArgs(aggregate), args);
	}

	@Override
	public List<Tuple> zInterWithScores(final String[] keys, final Aggregate aggregate, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add("AGGREGATE", aggregate).add("WEIGHTS", weights)
				.add(Keyword.Common.WITHSCORES);
		return zInterWithScores(rawBinaryKeys(keys), new LettuceZAggregateArgs(aggregate), args);
	}

	@Override
	public List<Tuple> zInterWithScores(final byte[][] keys, final Aggregate aggregate, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add("AGGREGATE", aggregate).add("WEIGHTS", weights)
				.add(Keyword.Common.WITHSCORES);
		return zInterWithScores(rawKeys(keys), new LettuceZAggregateArgs(aggregate), args);
	}

	@Override
	public List<Tuple> zInterWithScores(final String[] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add("WEIGHTS", weights)
				.add(Keyword.Common.WITHSCORES);
		return zInterWithScores(rawBinaryKeys(keys), new LettuceZAggregateArgs(weights), args);
	}

	@Override
	public List<Tuple> zInterWithScores(final byte[][] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add("WEIGHTS", weights)
				.add(Keyword.Common.WITHSCORES);
		return zInterWithScores(rawKeys(keys), new LettuceZAggregateArgs(weights), args);
	}

	@Override
	public long zInterCard(final String... keys) {
		final CommandArguments args = CommandArguments.create(keys.length).add(keys);
		return executeCommand(RedisCommand.ZINTERCARD, args, (cmd)->cmd.zintercard(rawBinaryKeys(keys)));
	}

	@Override
	public long zInterCard(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(keys.length).add(keys);
		return executeCommand(RedisCommand.ZINTERCARD, args, (cmd)->cmd.zintercard(rawKeys(keys)));
	}

	@Override
	public long zInterCard(final String[] keys, final int limit) {
		final CommandArguments args = CommandArguments.create(keys.length).add(keys).add(Keyword.Common.LIMIT, limit);
		return executeCommand(RedisCommand.ZINTERCARD, args, (cmd)->cmd.zintercard(limit, rawBinaryKeys(keys)));
	}

	@Override
	public long zInterCard(final byte[][] keys, final int limit) {
		final CommandArguments args = CommandArguments.create(keys.length).add(keys).add(Keyword.Common.LIMIT, limit);
		return executeCommand(RedisCommand.ZINTERCARD, args, (cmd)->cmd.zintercard(limit, rawKeys(keys)));
	}

	@Override
	public Long zInterStore(final String destKey, final String... keys) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys.length, keys);
		return executeCommand(RedisCommand.ZINTERSTORE, args,
				(cmd)->cmd.zinterstore(rawBinaryKey(destKey), rawBinaryKeys(keys)));
	}

	@Override
	public Long zInterStore(final byte[] destKey, final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys.length, keys);
		return executeCommand(RedisCommand.ZINTERSTORE, args, (cmd)->cmd.zinterstore(rawKey(destKey), rawKeys(keys)),
				(v)->v);
	}

	@Override
	public Long zInterStore(final String destKey, final String[] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys.length).add(keys)
				.add("AGGREGATE", aggregate);
		return zInterStore(rawBinaryKey(destKey), rawBinaryKeys(keys), new LettuceZStoreArgs(aggregate), args);
	}

	@Override
	public Long zInterStore(final byte[] destKey, final byte[][] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys.length).add(keys)
				.add("AGGREGATE", aggregate);
		return zInterStore(rawKey(destKey), rawKeys(keys), new LettuceZStoreArgs(aggregate), args);
	}

	@Override
	public Long zInterStore(final String destKey, final String[] keys, final Aggregate aggregate,
	                        final double... weights) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys.length).add(keys)
				.add("AGGREGATE", aggregate).add("WEIGHTS", weights);
		return zInterStore(rawBinaryKey(destKey), rawBinaryKeys(keys), new LettuceZStoreArgs(aggregate, weights), args);
	}

	@Override
	public Long zInterStore(final byte[] destKey, final byte[][] keys, final Aggregate aggregate,
	                        final double... weights) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys.length).add(keys)
				.add("AGGREGATE", aggregate).add("WEIGHTS", weights);
		return zInterStore(rawKey(destKey), rawKeys(keys), new LettuceZStoreArgs(aggregate, weights), args);
	}

	@Override
	public Long zInterStore(final String destKey, final String[] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys.length).add(keys)
				.add("WEIGHTS", weights);
		return zInterStore(rawBinaryKey(destKey), rawBinaryKeys(keys), new LettuceZStoreArgs(weights), args);
	}

	@Override
	public Long zInterStore(final byte[] destKey, final byte[][] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys.length).add(keys)
				.add("WEIGHTS", weights);
		return zInterStore(rawKey(destKey), rawKeys(keys), new LettuceZStoreArgs(weights), args);
	}

	@Override
	public Long zLexCount(final String key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min, max);
		return zLexCount(rawBinaryKey(key), min, max, args);
	}

	@Override
	public Long zLexCount(final byte[] key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min, max);
		return zLexCount(rawKey(key), min, max, args);
	}

	@Override
	public KeyValue<String, List<Tuple>> zMPop(final String[] keys, final MinMax minMax) {
		final CommandArguments args = CommandArguments.create(keys.length).add(keys).add(minMax);
		return zMPop(rawBinaryKeys(keys), minMax, 1, SafeEncoder::encode, args);
	}

	@Override
	public KeyValue<byte[], List<Tuple>> zMPop(final byte[][] keys, final MinMax minMax) {
		final CommandArguments args = CommandArguments.create(keys.length).add(keys).add(minMax);
		return zMPop(rawKeys(keys), minMax, 1, (k)->k, args);
	}

	@Override
	public KeyValue<String, List<Tuple>> zMPop(final String[] keys, final MinMax minMax, final int count) {
		final CommandArguments args = CommandArguments.create(keys.length).add(keys).add(minMax)
				.add(Keyword.Common.COUNT, count);
		return zMPop(rawBinaryKeys(keys), minMax, count, SafeEncoder::encode, args);
	}

	@Override
	public KeyValue<byte[], List<Tuple>> zMPop(final byte[][] keys, final MinMax minMax, final int count) {
		final CommandArguments args = CommandArguments.create(keys.length).add(keys).add(minMax)
				.add(Keyword.Common.COUNT, count);
		return zMPop(rawKeys(keys), minMax, count, (k)->k, args);
	}

	@Override
	public List<Double> zMScore(final String key, final String... members) {
		final CommandArguments args = CommandArguments.create(key).add(members);
		return executeCommand(RedisCommand.ZMSCORE, args,
				(cmd)->cmd.zmscore(rawBinaryKey(key), SafeEncoder.encode(members)));
	}

	@Override
	public List<Double> zMScore(final byte[] key, final byte[]... members) {
		final CommandArguments args = CommandArguments.create(key).add(members);
		return executeCommand(RedisCommand.ZMSCORE, args, (cmd)->cmd.zmscore(rawKey(key), members));
	}

	@Override
	public Tuple zPopMax(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.ZPOPMAX, args, (cmd)->cmd.zpopmax(rawBinaryKey(key)),
				new ScoredValueTupleConverter());
	}

	@Override
	public Tuple zPopMax(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(
				RedisCommand.ZPOPMAX, args, (cmd)->cmd.zpopmax(rawKey(key)), new ScoredValueTupleConverter());
	}

	@Override
	public List<Tuple> zPopMax(final String key, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(count);
		return executeCommand(RedisCommand.ZPOPMAX, args, (cmd)->cmd.zpopmax(rawBinaryKey(key), count),
				new ListConverter<>(new ScoredValueTupleConverter()));
	}

	@Override
	public List<Tuple> zPopMax(final byte[] key, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(count);
		return executeCommand(RedisCommand.ZPOPMAX, args, (cmd)->cmd.zpopmax(rawKey(key), count),
				new ListConverter<>(new ScoredValueTupleConverter()));
	}

	@Override
	public Tuple zPopMin(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.ZPOPMIN, args, (cmd)->cmd.zpopmin(rawBinaryKey(key)),
				new ScoredValueTupleConverter());
	}

	@Override
	public Tuple zPopMin(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(
				RedisCommand.ZPOPMIN, args, (cmd)->cmd.zpopmin(rawKey(key)), new ScoredValueTupleConverter());
	}

	@Override
	public List<Tuple> zPopMin(final String key, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(count);
		return executeCommand(RedisCommand.ZPOPMIN, args, (cmd)->cmd.zpopmin(rawBinaryKey(key), count),
				new ListConverter<>(new ScoredValueTupleConverter()));
	}

	@Override
	public List<Tuple> zPopMin(final byte[] key, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(count);
		return executeCommand(RedisCommand.ZPOPMIN, args, (cmd)->cmd.zpopmin(rawKey(key), count),
				new ListConverter<>(new ScoredValueTupleConverter()));
	}

	@Override
	public String zRandMember(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.ZRANDMEMBER, args, (cmd)->cmd.zrandmember(rawBinaryKey(key)),
				SafeEncoder::encode);
	}

	@Override
	public byte[] zRandMember(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.ZRANDMEMBER, args, (cmd)->cmd.zrandmember(rawKey(key)));
	}

	@Override
	public List<String> zRandMember(final String key, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(count);
		return executeCommand(RedisCommand.ZRANDMEMBER, args, (cmd)->cmd.zrandmember(rawBinaryKey(key), count),
				new BinaryListStringListConverter());
	}

	@Override
	public List<byte[]> zRandMember(final byte[] key, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(count);
		return executeCommand(RedisCommand.ZRANDMEMBER, args, (cmd)->cmd.zrandmember(rawKey(key), count));
	}

	@Override
	public Tuple zRandMemberWithScores(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.ZRANDMEMBER, args, (cmd)->cmd.zrandmemberWithScores(rawBinaryKey(key)),
				new ScoredValueTupleConverter());
	}

	@Override
	public Tuple zRandMemberWithScores(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.ZRANDMEMBER, args, (cmd)->cmd.zrandmemberWithScores(rawKey(key)),
				new ScoredValueTupleConverter());
	}

	@Override
	public List<Tuple> zRandMemberWithScores(final String key, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(count);
		return executeCommand(RedisCommand.ZRANDMEMBER, args,
				(cmd)->cmd.zrandmemberWithScores(rawBinaryKey(key), count),
				new ListConverter<>(new ScoredValueTupleConverter()));
	}

	@Override
	public List<Tuple> zRandMemberWithScores(final byte[] key, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(count);
		return executeCommand(RedisCommand.ZRANDMEMBER, args, (cmd)->cmd.zrandmemberWithScores(rawKey(key), count),
				new ListConverter<>(new ScoredValueTupleConverter()));
	}

	@Override
	public List<String> zRange(final String key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start, end);
		return executeCommand(RedisCommand.ZRANGE, args, (cmd)->cmd.zrange(rawBinaryKey(key), start, end),
				new BinaryListStringListConverter());
	}

	@Override
	public List<byte[]> zRange(final byte[] key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start, end);
		return executeCommand(RedisCommand.ZRANGE, args, (cmd)->cmd.zrange(rawKey(key), start, end));
	}

	@Override
	public List<String> zRange(final String key, final long start, final long end, final ZRangeType type) {
		final CommandArguments args = CommandArguments.create(key).add(start, end).add(type);
		return zRange(rawBinaryKey(key), start, end, type, new BinaryListStringListConverter(), args);
	}

	@Override
	public List<byte[]> zRange(final byte[] key, final long start, final long end, final ZRangeType type) {
		final CommandArguments args = CommandArguments.create(key).add(start, end).add(type);
		return zRange(rawKey(key), start, end, type, (v)->v, args);
	}

	@Override
	public List<String> zRange(final String key, final long start, final long end, final ZRangeType type,
	                           final boolean rev) {
		final CommandArguments args = CommandArguments.create(key).add(start, end).add(type).add(rev ? "REV" : null);
		return zRange(rawBinaryKey(key), start, end, type, new BinaryListStringListConverter(), args);
	}

	@Override
	public List<byte[]> zRange(final byte[] key, final long start, final long end, final ZRangeType type,
	                           final boolean rev) {
		final CommandArguments args = CommandArguments.create(key).add(start, end).add(type).add(rev ? "REV" : null);
		return zRange(rawKey(key), start, end, type, (v)->v, args);
	}

	@Override
	public List<String> zRange(final String key, final long start, final long end, final boolean rev) {
		final CommandArguments args = CommandArguments.create(key).add(start, end).add(rev ? "REV" : null);
		return executeCommand(RedisCommand.ZRANGE, args, (cmd)->cmd.zrange(rawBinaryKey(key), start, end),
				new BinaryListStringListConverter());
	}

	@Override
	public List<byte[]> zRange(final byte[] key, final long start, final long end, final boolean rev) {
		final CommandArguments args = CommandArguments.create(key).add(start, end).add(rev ? "REV" : null);
		return executeCommand(RedisCommand.ZRANGE, args, (cmd)->cmd.zrange(rawKey(key), start, end));
	}

	@Override
	public List<String> zRange(final String key, final long start, final long end, final int offset, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(start, end).add(Keyword.Common.LIMIT)
				.add(offset, count);
		return executeCommand(RedisCommand.ZRANGE, args, (cmd)->cmd.zrange(rawBinaryKey(key), start, end),
				new BinaryListStringListConverter());
	}

	@Override
	public List<byte[]> zRange(final byte[] key, final long start, final long end, final int offset, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(start, end).add(Keyword.Common.LIMIT)
				.add(offset, count);
		return executeCommand(RedisCommand.ZRANGE, args, (cmd)->cmd.zrange(rawKey(key), start, end));
	}

	@Override
	public List<String> zRange(final String key, final long start, final long end, final ZRangeType type,
	                           final int offset, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(start, end).add(type).add(Keyword.Common.LIMIT)
				.add(offset, count);
		return zRange(rawBinaryKey(key), start, end, type, offset, count, new BinaryListStringListConverter(), args);
	}

	@Override
	public List<byte[]> zRange(final byte[] key, final long start, final long end, final ZRangeType type,
	                           final int offset, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(start, end).add(type).add(Keyword.Common.LIMIT)
				.add(offset, count);
		return zRange(rawKey(key), start, end, type, offset, count, (v)->v, args);
	}

	@Override
	public List<String> zRange(final String key, final long start, final long end, final ZRangeType type,
	                           final boolean rev, final int offset, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(start, end).add(type).add(rev ? "REV" : null)
				.add(Keyword.Common.LIMIT).add(offset, count);
		return zRange(rawBinaryKey(key), start, end, type, offset, count, new BinaryListStringListConverter(), args);
	}

	@Override
	public List<byte[]> zRange(final byte[] key, final long start, final long end, final ZRangeType type,
	                           final boolean rev, final int offset, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(start, end).add(type).add(rev ? "REV" : null)
				.add(Keyword.Common.LIMIT).add(offset, count);
		return zRange(rawKey(key), start, end, type, offset, count, (v)->v, args);
	}

	@Override
	public List<String> zRange(final String key, final long start, final long end, final boolean rev, final int offset,
	                           final int count) {
		final CommandArguments args = CommandArguments.create(key).add(start, end).add(rev ? "REV" : null)
				.add(Keyword.Common.LIMIT).add(offset, count);
		return executeCommand(RedisCommand.ZRANGE, args, (cmd)->cmd.zrange(rawBinaryKey(key), start, end),
				new BinaryListStringListConverter());
	}

	@Override
	public List<byte[]> zRange(final byte[] key, final long start, final long end, final boolean rev, final int offset,
	                           final int count) {
		final CommandArguments args = CommandArguments.create(key).add(start, end).add(rev ? "REV" : null)
				.add(Keyword.Common.LIMIT).add(offset, count);
		return executeCommand(RedisCommand.ZRANGE, args, (cmd)->cmd.zrange(rawKey(key), start, end));
	}

	@Override
	public List<Tuple> zRangeWithScores(final String key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start, end);
		return zRangeWithScores(rawBinaryKey(key), start, end, args);
	}

	@Override
	public List<Tuple> zRangeWithScores(final byte[] key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start, end);
		return zRangeWithScores(rawKey(key), start, end, args);
	}

	@Override
	public List<Tuple> zRangeWithScores(final String key, final long start, final long end, final ZRangeType type) {
		final CommandArguments args = CommandArguments.create(key).add(start, end).add(type);
		return zRangeWithScores(rawBinaryKey(key), start, end, args);
	}

	@Override
	public List<Tuple> zRangeWithScores(final byte[] key, final long start, final long end, final ZRangeType type) {
		final CommandArguments args = CommandArguments.create(key).add(start, end).add(type);
		return zRangeWithScores(rawKey(key), start, end, args);
	}

	@Override
	public List<Tuple> zRangeWithScores(final String key, final long start, final long end, final ZRangeType type,
	                                    final boolean rev) {
		final CommandArguments args = CommandArguments.create(key).add(start, end).add(type).add(rev ? "REV" : null);
		return zRangeWithScores(rawBinaryKey(key), start, end, args);
	}

	@Override
	public List<Tuple> zRangeWithScores(final byte[] key, final long start, final long end, final ZRangeType type,
	                                    final boolean rev) {
		final CommandArguments args = CommandArguments.create(key).add(start, end).add(type).add(rev ? "REV" : null);
		return zRangeWithScores(rawKey(key), start, end, args);
	}

	@Override
	public List<Tuple> zRangeWithScores(final String key, final long start, final long end, final boolean rev) {
		final CommandArguments args = CommandArguments.create(key).add(start, end).add(rev ? "REV" : null);
		return zRangeWithScores(rawBinaryKey(key), start, end, args);
	}

	@Override
	public List<Tuple> zRangeWithScores(final byte[] key, final long start, final long end, final boolean rev) {
		final CommandArguments args = CommandArguments.create(key).add(start, end).add(rev ? "REV" : null);
		return zRangeWithScores(rawKey(key), start, end, args);
	}

	@Override
	public List<Tuple> zRangeWithScores(final String key, final long start, final long end, final int offset,
	                                    final int count) {
		final CommandArguments args = CommandArguments.create(key).add(start, end).add(Keyword.Common.LIMIT)
				.add(offset, count);
		return zRangeWithScores(rawBinaryKey(key), start, end, args);
	}

	@Override
	public List<Tuple> zRangeWithScores(final byte[] key, final long start, final long end, final int offset,
	                                    final int count) {
		final CommandArguments args = CommandArguments.create(key).add(start, end).add(Keyword.Common.LIMIT)
				.add(offset, count);
		return zRangeWithScores(rawKey(key), start, end, args);
	}

	@Override
	public List<Tuple> zRangeWithScores(final String key, final long start, final long end, final ZRangeType type,
	                                    final int offset, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(start, end).add(type).add(Keyword.Common.LIMIT)
				.add(offset, count);
		return zRangeWithScores(rawBinaryKey(key), start, end, args);
	}

	@Override
	public List<Tuple> zRangeWithScores(final byte[] key, final long start, final long end, final ZRangeType type,
	                                    final int offset, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(start, end).add(type).add(Keyword.Common.LIMIT)
				.add(offset, count);
		return zRangeWithScores(rawKey(key), start, end, args);
	}

	@Override
	public List<Tuple> zRangeWithScores(final String key, final long start, final long end, final ZRangeType type,
	                                    final boolean rev, final int offset, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(start, end).add(type).add(rev ? "REV" : null)
				.add(Keyword.Common.LIMIT).add(offset, count);
		return zRangeWithScores(rawBinaryKey(key), start, end, args);
	}

	@Override
	public List<Tuple> zRangeWithScores(final byte[] key, final long start, final long end, final ZRangeType type,
	                                    final boolean rev, final int offset, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(start, end).add(type).add(rev ? "REV" : null)
				.add(Keyword.Common.LIMIT).add(offset, count);
		return zRangeWithScores(rawKey(key), start, end, args);
	}

	@Override
	public List<Tuple> zRangeWithScores(final String key, final long start, final long end, final boolean rev,
	                                    final int offset, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(start, end).add(rev ? "REV" : null)
				.add(Keyword.Common.LIMIT).add(offset, count);
		return zRangeWithScores(rawBinaryKey(key), start, end, args);
	}

	@Override
	public List<Tuple> zRangeWithScores(final byte[] key, final long start, final long end, final boolean rev,
	                                    final int offset, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(start, end).add(rev ? "REV" : null)
				.add(Keyword.Common.LIMIT).add(offset, count);
		return zRangeWithScores(rawKey(key), start, end, args);
	}

	@Override
	public List<String> zRangeByLex(final String key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min, max);
		return zRangeByLex(rawBinaryKey(key), min, max, new BinaryListStringListConverter(), args);
	}

	@Override
	public List<byte[]> zRangeByLex(final byte[] key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min, max);
		return zRangeByLex(rawKey(key), min, max, (v)->v, args);
	}

	@Override
	public List<String> zRangeByLex(final String key, final double min, final double max, final int offset,
	                                final int count) {
		final CommandArguments args = CommandArguments.create(key).add(min, max).add(Keyword.Common.LIMIT)
				.add(offset, count);
		return zRangeByLex(rawBinaryKey(key), min, max, offset, count, new BinaryListStringListConverter(), args);
	}

	@Override
	public List<byte[]> zRangeByLex(final byte[] key, final double min, final double max, final int offset,
	                                final int count) {
		final CommandArguments args = CommandArguments.create(key).add(min, max).add(Keyword.Common.LIMIT)
				.add(offset, count);
		return zRangeByLex(rawKey(key), min, max, offset, count, (v)->v, args);
	}

	@Override
	public List<String> zRangeByScore(final String key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min, max);
		return executeCommand(RedisCommand.ZRANGEBYSCORE, args,
				(cmd)->cmd.zrangebyscore(rawBinaryKey(key), Range.create(min, max)),
				new BinaryListStringListConverter());
	}

	@Override
	public List<byte[]> zRangeByScore(final byte[] key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min, max);
		return executeCommand(RedisCommand.ZRANGEBYSCORE, args,
				(cmd)->cmd.zrangebyscore(rawKey(key), Range.create(min, max)));
	}

	@Override
	public List<String> zRangeByScore(final String key, final double min, final double max, final int offset,
	                                  final int count) {
		final CommandArguments args = CommandArguments.create(key).add(min, max).add(Keyword.Common.LIMIT)
				.add(offset, count);
		return executeCommand(RedisCommand.ZRANGEBYSCORE, args,
				(cmd)->cmd.zrangebyscore(rawBinaryKey(key), Range.create(min, max), Limit.create(offset, count)),
				new BinaryListStringListConverter());
	}

	@Override
	public List<byte[]> zRangeByScore(final byte[] key, final double min, final double max, final int offset,
	                                  final int count) {
		final CommandArguments args = CommandArguments.create(key).add(min, max).add(Keyword.Common.LIMIT)
				.add(offset, count);
		return executeCommand(RedisCommand.ZRANGEBYSCORE, args,
				(cmd)->cmd.zrangebyscore(rawKey(key), Range.create(min, max), Limit.create(offset, count)));
	}

	@Override
	public List<Tuple> zRangeByScoreWithScores(final String key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min, max).add(Keyword.Common.WITHSCORES);
		return zRangeByScoreWithScores(rawBinaryKey(key), min, max, args);
	}

	@Override
	public List<Tuple> zRangeByScoreWithScores(final byte[] key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min, max).add(Keyword.Common.WITHSCORES);
		return zRangeByScoreWithScores(rawKey(key), min, max, args);
	}

	@Override
	public List<Tuple> zRangeByScoreWithScores(final String key, final double min, final double max, final int offset,
	                                           final int count) {
		final CommandArguments args = CommandArguments.create(key).add(min, max).add(Keyword.Common.WITHSCORES)
				.add(Keyword.Common.LIMIT).add(offset, count);
		return zRangeByScoreWithScores(rawBinaryKey(key), min, max, offset, count, args);
	}

	@Override
	public List<Tuple> zRangeByScoreWithScores(final byte[] key, final double min, final double max, final int offset,
	                                           final int count) {
		final CommandArguments args = CommandArguments.create(key).add(min, max).add(Keyword.Common.WITHSCORES)
				.add(Keyword.Common.LIMIT).add(offset, count);
		return zRangeByScoreWithScores(rawKey(key), min, max, offset, count, args);
	}

	@Override
	public Long zRangeStore(final String destKey, final String key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(destKey, key).add(start, end);
		return zRangeStore(rawBinaryKey(destKey), rawBinaryKey(key), start, end, Limit.unlimited(), args);
	}

	@Override
	public Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(destKey, key).add(start, end);
		return zRangeStore(rawKey(destKey), rawKey(key), start, end, Limit.unlimited(), args);
	}

	@Override
	public Long zRangeStore(final String destKey, final String key, final long start, final long end,
	                        final ZRangeType type) {
		final CommandArguments args = CommandArguments.create(destKey, key).add(start, end).add(type);
		return zRangeStore(rawBinaryKey(destKey), rawBinaryKey(key), start, end, type, Limit.unlimited(), args);
	}

	@Override
	public Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end,
	                        final ZRangeType type) {
		final CommandArguments args = CommandArguments.create(destKey, key).add(start, end).add(type);
		return zRangeStore(rawKey(destKey), rawKey(key), start, end, type, Limit.unlimited(), args);
	}

	@Override
	public Long zRangeStore(final String destKey, final String key, final long start, final long end,
	                        final ZRangeType type, final boolean rev) {
		final CommandArguments args = CommandArguments.create(destKey, key).add(start, end).add(type)
				.add(rev ? "REV" : null);
		return zRangeStore(rawBinaryKey(destKey), rawBinaryKey(key), start, end, type, Limit.unlimited(), args);
	}

	@Override
	public Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end,
	                        final ZRangeType type, final boolean rev) {
		final CommandArguments args = CommandArguments.create(destKey, key).add(start, end).add(type)
				.add(rev ? "REV" : null);
		return zRangeStore(rawKey(destKey), rawKey(key), start, end, type, Limit.unlimited(), args);
	}

	@Override
	public Long zRangeStore(final String destKey, final String key, final long start, final long end,
	                        final boolean rev) {
		final CommandArguments args = CommandArguments.create(destKey, key).add(start, end).add(rev ? "REV" : null);
		return zRangeStore(rawBinaryKey(destKey), rawBinaryKey(key), start, end, Limit.unlimited(), args);
	}

	@Override
	public Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end,
	                        final boolean rev) {
		final CommandArguments args = CommandArguments.create(destKey, key).add(start, end).add(rev ? "REV" : null);
		return zRangeStore(rawKey(destKey), rawKey(key), start, end, Limit.unlimited(), args);
	}

	@Override
	public Long zRangeStore(final String destKey, final String key, final long start, final long end, final int offset,
	                        final int count) {
		final CommandArguments args = CommandArguments.create(destKey, key).add(start, end).add(Keyword.Common.LIMIT)
				.add(offset, count);
		return zRangeStore(rawBinaryKey(destKey), rawBinaryKey(key), start, end, Limit.unlimited(), args);
	}

	@Override
	public Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end, final int offset,
	                        final int count) {
		final CommandArguments args = CommandArguments.create(destKey, key).add(start, end).add(Keyword.Common.LIMIT)
				.add(offset, count);
		return zRangeStore(rawKey(destKey), rawKey(key), start, end, Limit.unlimited(), args);
	}

	@Override
	public Long zRangeStore(final String destKey, final String key, final long start, final long end,
	                        final ZRangeType type, final int offset, final int count) {
		final CommandArguments args = CommandArguments.create(destKey, key).add(start, end).add(type)
				.add(Keyword.Common.LIMIT).add(offset, count);
		return zRangeStore(rawBinaryKey(destKey), rawBinaryKey(key), start, end, type, Limit.create(offset, count),
				args);
	}

	@Override
	public Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end,
	                        final ZRangeType type, final int offset, final int count) {
		final CommandArguments args = CommandArguments.create(destKey).add(key).add(start, end).add(type)
				.add(Keyword.Common.LIMIT).add(offset, count);
		return zRangeStore(rawKey(destKey), rawKey(key), start, end, type, Limit.create(offset, count), args);
	}

	@Override
	public Long zRangeStore(final String destKey, final String key, final long start, final long end,
	                        final ZRangeType type, final boolean rev, final int offset, final int count) {
		final CommandArguments args = CommandArguments.create(destKey, key).add(start, end).add(type)
				.add(rev ? "REV" : null).add(Keyword.Common.LIMIT).add(offset, count);
		return zRangeStore(rawBinaryKey(destKey), rawBinaryKey(key), start, end, type, Limit.create(offset, count),
				args);
	}

	@Override
	public Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end,
	                        final ZRangeType type, final boolean rev, final int offset, final int count) {
		final CommandArguments args = CommandArguments.create(destKey).add(key).add(start, end).add(type)
				.add(rev ? "REV" : null).add(Keyword.Common.LIMIT).add(offset, count);
		return zRangeStore(rawKey(destKey), rawKey(key), start, end, type, Limit.create(offset, count), args);
	}

	@Override
	public Long zRangeStore(final String destKey, final String key, final long start, final long end, final boolean rev,
	                        final int offset, final int count) {
		final CommandArguments args = CommandArguments.create(destKey, key).add(start, end).add(rev ? "REV" : null)
				.add(Keyword.Common.LIMIT).add(offset, count);
		return zRangeStore(rawBinaryKey(destKey), rawBinaryKey(key), start, end, Limit.create(offset, count), args);
	}

	@Override
	public Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end, final boolean rev,
	                        final int offset, final int count) {
		final CommandArguments args = CommandArguments.create(destKey).add(key).add(start, end).add(rev ? "REV" : null)
				.add(Keyword.Common.LIMIT).add(offset, count);
		return zRangeStore(rawKey(destKey), rawKey(key), start, end, Limit.create(offset, count), args);
	}

	@Override
	public Long zRank(final String key, final String member) {
		final CommandArguments args = CommandArguments.create(key, member);
		return executeCommand(RedisCommand.ZRANK, args,
				(cmd)->cmd.zrank(rawBinaryKey(key), SafeEncoder.encode(member)));
	}

	@Override
	public Long zRank(final byte[] key, final byte[] member) {
		final CommandArguments args = CommandArguments.create(key).add(member);
		return executeCommand(RedisCommand.ZRANK, args, (cmd)->cmd.zrank(rawKey(key), member));
	}

	@Override
	public KeyValue<Long, Double> zRankWithScores(final String key, final String member) {
		final CommandArguments args = CommandArguments.create(key, member).add(Keyword.Common.WITHSCORE);
		return zRankWithScores(rawBinaryKey(key), SafeEncoder.encode(member), args);
	}

	@Override
	public KeyValue<Long, Double> zRankWithScores(final byte[] key, final byte[] member) {
		final CommandArguments args = CommandArguments.create(key).add(member).add(Keyword.Common.WITHSCORE);
		return zRankWithScores(rawKey(key), member, args);
	}

	@Override
	public Long zRem(final String key, final String... members) {
		final CommandArguments args = CommandArguments.create(key).add(members);
		return executeCommand(RedisCommand.ZREM, args, (cmd)->cmd.zrem(rawBinaryKey(key), SafeEncoder.encode(members)));
	}

	@Override
	public Long zRem(final byte[] key, final byte[]... members) {
		final CommandArguments args = CommandArguments.create(key).add(members);
		return executeCommand(RedisCommand.ZREM, args, (cmd)->cmd.zrem(rawKey(key), members));
	}

	@Override
	public Long zRemRangeByLex(final String key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min, max);
		return zRemRangeByLex(rawBinaryKey(key), min, max, args);
	}

	@Override
	public Long zRemRangeByLex(final byte[] key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min, max);
		return zRemRangeByLex(rawKey(key), min, max, args);
	}

	@Override
	public Long zRemRangeByRank(final String key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start, end);
		return executeCommand(
				RedisCommand.ZREMRANGEBYRANK, args, (cmd)->cmd.zremrangebyrank(rawBinaryKey(key), start, end));
	}

	@Override
	public Long zRemRangeByRank(final byte[] key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start, end);
		return executeCommand(RedisCommand.ZREMRANGEBYRANK, args, (cmd)->cmd.zremrangebyrank(rawKey(key), start, end));
	}

	@Override
	public Long zRemRangeByScore(final String key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min, max);
		return executeCommand(RedisCommand.ZREMRANGEBYSCORE, args,
				(cmd)->cmd.zremrangebyscore(rawBinaryKey(key), Range.create(min, max)));
	}

	@Override
	public Long zRemRangeByScore(final byte[] key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min, max);
		return executeCommand(RedisCommand.ZREMRANGEBYSCORE, args,
				(cmd)->cmd.zremrangebyscore(rawKey(key), Range.create(min, max)));
	}

	@Override
	public List<String> zRevRange(final String key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start, end);
		return executeCommand(RedisCommand.ZREVRANGE, args, (cmd)->cmd.zrevrange(rawBinaryKey(key), start, end),
				new BinaryListStringListConverter());
	}

	@Override
	public List<byte[]> zRevRange(final byte[] key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start, end);
		return executeCommand(RedisCommand.ZREVRANGE, args, (cmd)->cmd.zrevrange(rawKey(key), start, end));
	}

	@Override
	public List<Tuple> zRevRangeWithScores(final String key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start, end);
		return zRevRangeWithScores(rawBinaryKey(key), start, end, args);
	}

	@Override
	public List<Tuple> zRevRangeWithScores(final byte[] key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start, end);
		return zRevRangeWithScores(rawKey(key), start, end, args);
	}

	@Override
	public List<String> zRevRangeByLex(final String key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min, max);
		return executeCommand(RedisCommand.ZREVRANGEBYLEX, args, (cmd)->cmd.zrevrangebylex(rawBinaryKey(key),
						Range.create(NumberUtils.double2bytes(min), NumberUtils.double2bytes(max))),
				new BinaryListStringListConverter());
	}

	@Override
	public List<byte[]> zRevRangeByLex(final byte[] key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min, max);
		return executeCommand(RedisCommand.ZREVRANGEBYLEX, args, (cmd)->cmd.zrevrangebylex(rawKey(key),
				Range.create(NumberUtils.double2bytes(min), NumberUtils.double2bytes(max))));
	}

	@Override
	public List<String> zRevRangeByLex(final String key, final double min, final double max, final int offset,
	                                   final int count) {
		final CommandArguments args = CommandArguments.create(key).add(min, max).add(Keyword.Common.LIMIT)
				.add(offset, count);
		return executeCommand(RedisCommand.ZREVRANGEBYLEX, args, (cmd)->cmd.zrevrangebylex(rawBinaryKey(key),
				Range.create(NumberUtils.double2bytes(min), NumberUtils.double2bytes(max)),
				Limit.create(offset, count)), new BinaryListStringListConverter());
	}

	@Override
	public List<byte[]> zRevRangeByLex(final byte[] key, final double min, final double max, final int offset,
	                                   final int count) {
		final CommandArguments args = CommandArguments.create(key).add(min, max).add(Keyword.Common.LIMIT)
				.add(offset, count);
		return executeCommand(RedisCommand.ZREVRANGEBYLEX, args, (cmd)->cmd.zrevrangebylex(rawKey(key),
				Range.create(NumberUtils.double2bytes(min), NumberUtils.double2bytes(max)),
				Limit.create(offset, count)));
	}

	@Override
	public List<String> zRevRangeByScore(final String key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min, max);
		return executeCommand(RedisCommand.ZREVRANGEBYSCORE, args,
				(cmd)->cmd.zrevrangebyscore(rawBinaryKey(key), Range.create(min, max)),
				new BinaryListStringListConverter());
	}

	@Override
	public List<byte[]> zRevRangeByScore(final byte[] key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min, max);
		return executeCommand(RedisCommand.ZREVRANGEBYSCORE, args,
				(cmd)->cmd.zrevrangebyscore(rawKey(key), Range.create(min, max)));
	}

	@Override
	public List<String> zRevRangeByScore(final String key, final double min, final double max, final int offset,
	                                     final int count) {
		final CommandArguments args = CommandArguments.create(key).add(min, max).add(Keyword.Common.LIMIT)
				.add(offset, count);
		return executeCommand(RedisCommand.ZREVRANGEBYSCORE, args,
				(cmd)->cmd.zrevrangebyscore(rawBinaryKey(key), Range.create(min, max), Limit.create(offset, count)),
				new BinaryListStringListConverter());
	}

	@Override
	public List<byte[]> zRevRangeByScore(final byte[] key, final double min, final double max, final int offset,
	                                     final int count) {
		final CommandArguments args = CommandArguments.create(key).add(min, max).add(Keyword.Common.LIMIT)
				.add(offset, count);
		return executeCommand(RedisCommand.ZREVRANGEBYSCORE, args,
				(cmd)->cmd.zrevrangebyscore(rawKey(key), Range.create(min, max), Limit.create(offset, count)));
	}

	@Override
	public List<Tuple> zRevRangeByScoreWithScores(final String key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min, max);
		return zRevRangeByScoreWithScores(rawBinaryKey(key), min, max, args);
	}

	@Override
	public List<Tuple> zRevRangeByScoreWithScores(final byte[] key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min, max);
		return zRevRangeByScoreWithScores(rawKey(key), min, max, args);
	}

	@Override
	public List<Tuple> zRevRangeByScoreWithScores(final String key, final double min, final double max,
	                                              final int offset, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(min, max).add(Keyword.Common.LIMIT)
				.add(offset, count);
		return zRevRangeByScoreWithScores(rawBinaryKey(key), min, max, offset, count, args);
	}

	@Override
	public List<Tuple> zRevRangeByScoreWithScores(final byte[] key, final double min, final double max,
	                                              final int offset, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(min, max).add(Keyword.Common.LIMIT)
				.add(offset, count);
		return zRevRangeByScoreWithScores(rawKey(key), min, max, offset, count, args);
	}

	@Override
	public Long zRevRank(final String key, final String member) {
		final CommandArguments args = CommandArguments.create(key, member);
		return executeCommand(RedisCommand.ZREVRANK, args,
				(cmd)->cmd.zrevrank(rawBinaryKey(key), SafeEncoder.encode(member)));
	}

	@Override
	public Long zRevRank(final byte[] key, final byte[] member) {
		final CommandArguments args = CommandArguments.create(key, member);
		return executeCommand(RedisCommand.ZREVRANK, args, (cmd)->cmd.zrevrank(rawKey(key), member));
	}

	@Override
	public KeyValue<Long, Double> zRevRankWithScore(final String key, final String member) {
		final CommandArguments args = CommandArguments.create(key, member).add(Keyword.Common.WITHSCORE);
		return executeCommand(RedisCommand.ZREVRANK, args,
				(cmd)->cmd.zrankWithScore(rawBinaryKey(key), SafeEncoder.encode(member)),
				new ScoredValueKeyValueConverter<>((v)->v));
	}

	@Override
	public KeyValue<Long, Double> zRevRankWithScore(final byte[] key, final byte[] member) {
		final CommandArguments args = CommandArguments.create(key, member).add(Keyword.Common.WITHSCORE);
		return executeCommand(RedisCommand.ZREVRANK, args, (cmd)->cmd.zrankWithScore(rawKey(key), member),
				new ScoredValueKeyValueConverter<>((v)->v));
	}

	@Override
	public ScanResult<Tuple> zScan(final String key, final String cursor) {
		final CommandArguments args = CommandArguments.create(key).add(cursor);
		return executeCommand(
				RedisCommand.ZSCAN, args, (cmd)->cmd.zscan(rawBinaryKey(key), new LettuceScanCursor(cursor)),
				new ScanCursorConverter.ScoredValueScanCursorConverter());
	}

	@Override
	public ScanResult<Tuple> zScan(final byte[] key, final byte[] cursor) {
		final CommandArguments args = CommandArguments.create(key).add(cursor);
		return executeCommand(RedisCommand.ZSCAN, args, (cmd)->cmd.zscan(rawKey(key), new LettuceScanCursor(cursor)),
				new ScanCursorConverter.ScoredValueScanCursorConverter());
	}

	@Override
	public ScanResult<Tuple> zScan(final String key, final String cursor, final String pattern) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(Keyword.Scan.MATCH, pattern);
		return zScan(rawBinaryKey(key), new LettuceScanCursor(cursor), new LettuceScanArgs(pattern), args);
	}

	@Override
	public ScanResult<Tuple> zScan(final byte[] key, final byte[] cursor, final byte[] pattern) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(Keyword.Scan.MATCH, pattern);
		return zScan(rawKey(key), new LettuceScanCursor(cursor), new LettuceScanArgs(pattern), args);
	}

	@Override
	public ScanResult<Tuple> zScan(final String key, final String cursor, final String pattern, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(Keyword.Scan.MATCH, pattern)
				.add(Keyword.Common.COUNT, count);
		return zScan(rawBinaryKey(key), new LettuceScanCursor(cursor), new LettuceScanArgs(pattern, count), args);
	}

	@Override
	public ScanResult<Tuple> zScan(final byte[] key, final byte[] cursor, final byte[] pattern, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(Keyword.Scan.MATCH, pattern)
				.add(Keyword.Common.COUNT, count);
		return zScan(rawKey(key), new LettuceScanCursor(cursor), new LettuceScanArgs(pattern, count), args);
	}

	@Override
	public ScanResult<Tuple> zScan(final String key, final String cursor, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(Keyword.Common.COUNT, count);
		return zScan(rawBinaryKey(key), new LettuceScanCursor(cursor), new LettuceScanArgs(count), args);
	}

	@Override
	public ScanResult<Tuple> zScan(final byte[] key, final byte[] cursor, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(Keyword.Common.COUNT, count);
		return zScan(rawKey(key), new LettuceScanCursor(cursor), new LettuceScanArgs(count), args);
	}

	@Override
	public Double zScore(final String key, final String member) {
		final CommandArguments args = CommandArguments.create(key, member);
		return executeCommand(
				RedisCommand.ZSCORE, args, (cmd)->cmd.zscore(rawBinaryKey(key), SafeEncoder.encode(member)));
	}

	@Override
	public Double zScore(final byte[] key, final byte[] member) {
		final CommandArguments args = CommandArguments.create(key, member);
		return executeCommand(RedisCommand.ZSCORE, args, (cmd)->cmd.zscore(rawKey(key), member));
	}

	@Override
	public List<String> zUnion(final String... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return executeCommand(RedisCommand.ZUNION, args, (cmd)->cmd.zunion(rawBinaryKeys(keys)),
				new BinaryListStringListConverter());
	}

	@Override
	public List<byte[]> zUnion(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return executeCommand(RedisCommand.ZUNION, args, (cmd)->cmd.zunion(rawKeys(keys)));
	}

	@Override
	public List<String> zUnion(final String[] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create(keys).add("AGGREGATE", aggregate);
		return stringZUnion(rawBinaryKeys(keys), new LettuceZAggregateArgs(aggregate), args);
	}

	@Override
	public List<byte[]> zUnion(final byte[][] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create(keys).add("AGGREGATE", aggregate);
		return binaryZUnion(rawKeys(keys), new LettuceZAggregateArgs(aggregate), args);
	}

	@Override
	public List<String> zUnion(final String[] keys, final Aggregate aggregate, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add("AGGREGATE", aggregate).add("WEIGHTS", weights);
		return stringZUnion(rawBinaryKeys(keys), new LettuceZAggregateArgs(aggregate, weights), args);
	}

	@Override
	public List<byte[]> zUnion(final byte[][] keys, final Aggregate aggregate, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add("AGGREGATE", aggregate).add("WEIGHTS", weights);
		return binaryZUnion(rawKeys(keys), new LettuceZAggregateArgs(aggregate, weights), args);
	}

	@Override
	public List<String> zUnion(final String[] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add("WEIGHTS", weights);
		return stringZUnion(rawBinaryKeys(keys), new LettuceZAggregateArgs(weights), args);
	}

	@Override
	public List<byte[]> zUnion(final byte[][] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add("WEIGHTS", weights);
		return binaryZUnion(rawKeys(keys), new LettuceZAggregateArgs(weights), args);
	}

	@Override
	public List<Tuple> zUnionWithScores(final String... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return executeCommand(RedisCommand.ZUNION, args, (cmd)->cmd.zunionWithScores(rawBinaryKeys(keys)),
				new ListConverter<>(new ScoredValueTupleConverter()));
	}

	@Override
	public List<Tuple> zUnionWithScores(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return executeCommand(RedisCommand.ZUNION, args, (cmd)->cmd.zunionWithScores(rawKeys(keys)),
				new ListConverter<>(new ScoredValueTupleConverter()));
	}

	@Override
	public List<Tuple> zUnionWithScores(final String[] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create(keys).add("AGGREGATE", aggregate);
		return zUnionWithScores(rawBinaryKeys(keys), new LettuceZAggregateArgs(aggregate), args);
	}

	@Override
	public List<Tuple> zUnionWithScores(final byte[][] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create(keys).add("AGGREGATE", aggregate);
		return zUnionWithScores(rawKeys(keys), new LettuceZAggregateArgs(aggregate), args);
	}

	@Override
	public List<Tuple> zUnionWithScores(final String[] keys, final Aggregate aggregate, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add("AGGREGATE", aggregate).add("WEIGHTS", weights);
		return zUnionWithScores(rawBinaryKeys(keys), new LettuceZAggregateArgs(aggregate, weights), args);
	}

	@Override
	public List<Tuple> zUnionWithScores(final byte[][] keys, final Aggregate aggregate, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add("AGGREGATE", aggregate).add("WEIGHTS", weights);
		return zUnionWithScores(rawKeys(keys), new LettuceZAggregateArgs(aggregate, weights), args);
	}

	@Override
	public List<Tuple> zUnionWithScores(final String[] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add("WEIGHTS", weights);
		return zUnionWithScores(rawBinaryKeys(keys), new LettuceZAggregateArgs(weights), args);
	}

	@Override
	public List<Tuple> zUnionWithScores(final byte[][] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add("WEIGHTS", weights);
		return zUnionWithScores(rawKeys(keys), new LettuceZAggregateArgs(weights), args);
	}

	@Override
	public Long zUnionStore(final String destKey, final String... keys) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys);
		return executeCommand(RedisCommand.ZUNIONSTORE, args,
				(cmd)->cmd.zunionstore(rawBinaryKey(destKey), rawBinaryKeys(keys)));
	}

	@Override
	public Long zUnionStore(final byte[] destKey, final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys);
		return executeCommand(RedisCommand.ZUNIONSTORE, args, (cmd)->cmd.zunionstore(rawKey(destKey), rawKeys(keys)));
	}

	@Override
	public Long zUnionStore(final String destKey, final String[] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys).add("AGGREGATE", aggregate);
		return zUnionStore(rawBinaryKey(destKey), rawBinaryKeys(keys), new LettuceZStoreArgs(aggregate), args);
	}

	@Override
	public Long zUnionStore(final byte[] destKey, final byte[][] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys).add("AGGREGATE", aggregate);
		return zUnionStore(rawKey(destKey), rawKeys(keys), new LettuceZStoreArgs(aggregate), args);
	}

	@Override
	public Long zUnionStore(final String destKey, final String[] keys, final Aggregate aggregate,
	                        final double... weights) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys).add("AGGREGATE", aggregate)
				.add("WEIGHTS", weights);
		return zUnionStore(rawBinaryKey(destKey), rawBinaryKeys(keys), new LettuceZStoreArgs(aggregate, weights), args);
	}

	@Override
	public Long zUnionStore(final byte[] destKey, final byte[][] keys, final Aggregate aggregate,
	                        final double... weights) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys).add("AGGREGATE", aggregate)
				.add("WEIGHTS", weights);
		return zUnionStore(rawKey(destKey), rawKeys(keys), new LettuceZStoreArgs(aggregate, weights), args);
	}

	@Override
	public Long zUnionStore(final String destKey, final String[] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys).add("WEIGHTS", weights);
		return zUnionStore(rawBinaryKey(destKey), rawBinaryKeys(keys), new LettuceZStoreArgs(weights), args);
	}

	@Override
	public Long zUnionStore(final byte[] destKey, final byte[][] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys).add("WEIGHTS", weights);
		return zUnionStore(rawKey(destKey), rawKeys(keys), new LettuceZStoreArgs(weights), args);
	}

	private <K> KeyValue<K, List<Tuple>> bzMPop(final byte[][] keys, final int timeout, final MinMax minMax,
	                                            final int count, final Converter<byte[], K> keyConverter,
	                                            final CommandArguments args) {
		return executeCommand(RedisCommand.BZMPOP, args,
				(cmd)->cmd.bzmpop(timeout, (long) count, new LettuceZPopArgs(minMax), rawKeys(keys)),
				new KeyValueConverter<>(keyConverter, new ListConverter<>(new ScoredValueTupleConverter())));
	}


	private Long zAdd(final byte[] key, final Tuple[] members, final CommandArguments args) {
		final ScoredValue<byte[]>[] scoredValues = new ScoredValue[members.length];

		for(int i = 0; i < members.length; i++){
			scoredValues[i] = ScoredValue.just(members[i].getScore(), members[i].getBinaryElement());
		}

		return executeCommand(RedisCommand.ZADD, args, (cmd)->cmd.zadd(key, scoredValues));
	}

	private Long zAdd(final byte[] key, final Tuple[] members, final ZAddArgument argument,
	                  final CommandArguments args) {
		final ScoredValue<byte[]>[] scoredValues = new ScoredValue[members.length];

		for(int i = 0; i < members.length; i++){
			scoredValues[i] = ScoredValue.just(members[i].getScore(), members[i].getBinaryElement());
		}

		return executeCommand(RedisCommand.ZADD, args,
				(cmd)->cmd.zadd(rawKey(key), new LettuceZAddArgs(argument), scoredValues));
	}

	private List<Tuple> zDiffWithScores(final byte[][] keys, final CommandArguments args) {
		return executeCommand(RedisCommand.ZDIFF, args, (cmd)->cmd.zdiffWithScores(keys),
				new ListConverter<>(new ScoredValueTupleConverter()));
	}

	private List<String> stringZInter(final byte[][] keys, final LettuceZAggregateArgs zAggregateArgs,
	                                  final CommandArguments args) {
		return executeCommand(RedisCommand.ZINTER, args, (cmd)->cmd.zinter(zAggregateArgs, keys),
				new BinaryListStringListConverter());
	}

	private List<byte[]> binaryZInter(final byte[][] keys, final LettuceZAggregateArgs zAggregateArgs,
	                                  final CommandArguments args) {
		return executeCommand(RedisCommand.ZINTER, args, (cmd)->cmd.zinter(zAggregateArgs, keys));
	}

	private List<Tuple> zInterWithScores(final byte[][] keys, final LettuceZAggregateArgs zAggregateArgs,
	                                     final CommandArguments args) {
		return executeCommand(RedisCommand.ZINTER, args, (cmd)->cmd.zinterWithScores(zAggregateArgs, keys),
				new ListConverter<>(new ScoredValueTupleConverter()));
	}

	private Long zInterStore(final byte[] destKey, final byte[][] keys, final LettuceZStoreArgs zStoreArgs,
	                         final CommandArguments args) {
		return executeCommand(RedisCommand.ZINTERSTORE, args, (cmd)->cmd.zinterstore(destKey, zStoreArgs, keys));
	}

	private Long zLexCount(final byte[] key, final double min, final double max, final CommandArguments args) {
		return executeCommand(RedisCommand.ZLEXCOUNT, args,
				(cmd)->cmd.zlexcount(key, Range.create(NumberUtils.double2bytes(min), NumberUtils.double2bytes(max))));
	}

	private <K> KeyValue<K, List<Tuple>> zMPop(final byte[][] keys, final MinMax minMax, final int count,
	                                           final Converter<byte[], K> keyConverter, final CommandArguments args) {
		return executeCommand(RedisCommand.ZMPOP, args, (cmd)->cmd.zmpop(count, new LettuceZPopArgs(minMax), keys),
				new KeyValueConverter<>(keyConverter, new ListConverter<>(new ScoredValueTupleConverter())));
	}

	private <V> List<V> zRange(final byte[] key, final long start, final long end, final ZRangeType type,
	                           final Converter<List<byte[]>, List<V>> converter, final CommandArguments args) {
		if(type == ZRangeType.BYLEX){
			return executeCommand(RedisCommand.ZRANGE, args, (cmd)->cmd.zrangebylex(key,
					Range.create(NumberUtils.long2bytes(start), NumberUtils.long2bytes(end))), converter);
		}else if(type == ZRangeType.BYSCORE){
			return executeCommand(RedisCommand.ZRANGE, args, (cmd)->cmd.zrangebyscore(key, Range.create(start, end)),
					converter);
		}else{
			return executeCommand(RedisCommand.ZRANGE, args, (cmd)->cmd.zrange(key, start, end), converter);
		}
	}

	private <V> List<V> zRange(final byte[] key, final long start, final long end, final ZRangeType type,
	                           final int offset, final int count, final Converter<List<byte[]>, List<V>> converter,
	                           final CommandArguments args) {
		if(type == ZRangeType.BYLEX){
			return executeCommand(RedisCommand.ZRANGE, args, (cmd)->cmd.zrangebylex(key,
					Range.create(NumberUtils.long2bytes(start), NumberUtils.long2bytes(end)),
					Limit.create(offset, count)), converter);
		}else if(type == ZRangeType.BYSCORE){
			return executeCommand(RedisCommand.ZRANGE, args,
					(cmd)->cmd.zrangebyscore(key, Range.create(start, end), Limit.create(offset, count)), converter);
		}else{
			return executeCommand(RedisCommand.ZRANGE, args, (cmd)->cmd.zrange(key, start, end), converter);
		}
	}

	private List<Tuple> zRangeWithScores(final byte[] key, final long start, final long end,
	                                     final CommandArguments args) {
		return executeCommand(RedisCommand.ZRANGE, args, (cmd)->cmd.zrangeWithScores(rawKey(key), start, end),
				new ListConverter<>(new ScoredValueTupleConverter()));
	}

	private <T> List<T> zRangeByLex(final byte[] key, final double min, final double max,
	                                final Converter<List<byte[]>, List<T>> converter, final CommandArguments args) {
		return executeCommand(RedisCommand.ZRANGEBYLEX, args, (cmd)->cmd.zrangebylex(rawKey(key),
				Range.create(NumberUtils.double2bytes(min), NumberUtils.double2bytes(max))), converter);
	}

	private <T> List<T> zRangeByLex(final byte[] key, final double min, final double max, final int offset,
	                                final int count, final Converter<List<byte[]>, List<T>> converter,
	                                final CommandArguments args) {
		return executeCommand(RedisCommand.ZRANGEBYLEX, args,
				(cmd)->cmd.zrangebylex(key, Range.create(NumberUtils.double2bytes(min), NumberUtils.double2bytes(max)),
						Limit.create(offset, count)), converter);
	}

	private List<Tuple> zRangeByScoreWithScores(final byte[] key, final double min, final double max,
	                                            final CommandArguments args) {
		return executeCommand(RedisCommand.ZRANGEBYSCORE, args,
				(cmd)->cmd.zrangebyscoreWithScores(key, Range.create(min, max)),
				new ListConverter<>(new ScoredValueTupleConverter()));
	}

	private List<Tuple> zRangeByScoreWithScores(final byte[] key, final double min, final double max, final int offset,
	                                            final int count, final CommandArguments args) {
		return executeCommand(RedisCommand.ZRANGEBYSCORE, args,
				(cmd)->cmd.zrangebyscoreWithScores(key, Range.create(min, max), Limit.create(offset, count)),
				new ListConverter<>(new ScoredValueTupleConverter()));
	}

	private Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end,
	                         final Limit limit, final CommandArguments args) {
		return executeCommand(RedisCommand.ZRANGESTORE, args,
				(cmd)->cmd.zrangestore(destKey, key, Range.create(start, end)));
	}

	private Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end,
	                         final ZRangeType type, final Limit limit, final CommandArguments args) {
		if(type == ZRangeType.BYLEX){
			return executeCommand(RedisCommand.ZRANGESTORE, args, (cmd)->cmd.zrangestorebylex(destKey, key,
					Range.create(NumberUtils.long2bytes(start), NumberUtils.long2bytes(end)), limit));
		}else if(type == ZRangeType.BYSCORE){
			return executeCommand(RedisCommand.ZRANGESTORE, args,
					(cmd)->cmd.zrangestorebyscore(destKey, key, Range.create(start, end), limit));
		}else{
			return executeCommand(RedisCommand.ZRANGESTORE, args,
					(cmd)->cmd.zrangestore(destKey, key, Range.create(start, end)));
		}
	}

	private KeyValue<Long, Double> zRankWithScores(final byte[] key, final byte[] member, final CommandArguments args) {
		return executeCommand(RedisCommand.ZRANK, args, (cmd)->cmd.zrankWithScore(key, member),
				new ScoredValueKeyValueConverter<>((v)->v));
	}

	private Long zRemRangeByLex(final byte[] key, final double min, final double max, final CommandArguments args) {
		return executeCommand(RedisCommand.ZREMRANGEBYLEX, args, (cmd)->cmd.zremrangebylex(key,
				Range.create(NumberUtils.double2bytes(min), NumberUtils.double2bytes(max))));
	}

	private List<Tuple> zRevRangeWithScores(final byte[] key, final long start, final long end,
	                                        final CommandArguments args) {
		return executeCommand(RedisCommand.ZREVRANGE, args, (cmd)->cmd.zrevrangeWithScores(key, start, end),
				new ListConverter<>(new ScoredValueTupleConverter()));
	}

	private List<Tuple> zRevRangeByScoreWithScores(final byte[] key, final double min, final double max,
	                                               final CommandArguments args) {
		return executeCommand(RedisCommand.ZREVRANGEBYSCORE, args,
				(cmd)->cmd.zrevrangebyscoreWithScores(key, Range.create(min, max)),
				new ListConverter<>(new ScoredValueTupleConverter()));
	}

	private List<Tuple> zRevRangeByScoreWithScores(final byte[] key, final double min, final double max,
	                                               final int offset, final int count, final CommandArguments args) {
		return executeCommand(RedisCommand.ZREVRANGEBYSCORE, args,
				(cmd)->cmd.zrevrangebyscoreWithScores(key, Range.create(min, max), Limit.create(offset, count)),
				new ListConverter<>(new ScoredValueTupleConverter()));
	}

	private ScanResult<Tuple> zScan(final byte[] key, final LettuceScanCursor cursor, final LettuceScanArgs scanArgs,
	                                final CommandArguments args) {
		return executeCommand(RedisCommand.ZSCAN, args, (cmd)->cmd.zscan(key, cursor, scanArgs),
				new ScanCursorConverter.ScoredValueScanCursorConverter());
	}

	private List<String> stringZUnion(final byte[][] keys, final LettuceZAggregateArgs aggregateArgs,
	                                  final CommandArguments args) {
		return executeCommand(RedisCommand.ZUNION, args, (cmd)->cmd.zunion(aggregateArgs, keys),
				new BinaryListStringListConverter());
	}

	private List<byte[]> binaryZUnion(final byte[][] keys, final LettuceZAggregateArgs aggregateArgs,
	                                  final CommandArguments args) {
		return executeCommand(RedisCommand.ZUNION, args, (cmd)->cmd.zunion(aggregateArgs, rawKeys(keys)));
	}

	private List<Tuple> zUnionWithScores(final byte[][] keys, final LettuceZAggregateArgs aggregateArgs,
	                                     final CommandArguments args) {
		return executeCommand(RedisCommand.ZUNION, args, (cmd)->cmd.zunionWithScores(aggregateArgs, rawKeys(keys)),
				new ListConverter<>(new ScoredValueTupleConverter()));
	}

	private Long zUnionStore(final byte[] destKey, final byte[][] keys, final LettuceZStoreArgs zStoreArgs,
	                         final CommandArguments args) {
		return executeCommand(RedisCommand.ZUNIONSTORE, args, (cmd)->cmd.zunionstore(destKey, zStoreArgs, keys));
	}

}
