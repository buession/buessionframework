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
package com.buession.redis.client.jedis.command;

import com.buession.core.converter.ListConverter;
import com.buession.core.utils.NumberUtils;
import com.buession.core.validator.Validate;
import com.buession.lang.KeyValue;
import com.buession.redis.client.jedis.JedisRedisClient;
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
import com.buession.redis.core.internal.convert.jedis.params.MinMaxSortedSetOptionConverter;
import com.buession.redis.core.internal.convert.jedis.response.KeyValueConverter;
import com.buession.redis.core.internal.convert.jedis.response.ScanResultConverter;
import com.buession.redis.core.internal.convert.jedis.response.TupleConverter;
import com.buession.redis.core.internal.jedis.args.JedisScanParams;
import com.buession.redis.core.internal.jedis.args.JedisZAddParams;
import com.buession.redis.core.internal.jedis.args.JedisZParams;
import com.buession.redis.core.internal.jedis.args.JedisZRangeParams;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Jedis 有序集合命令
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public final class JedisSortedSetCommands extends AbstractJedisRedisCommands implements SortedSetCommands {

	public JedisSortedSetCommands(final JedisRedisClient client) {
		super(client);
	}

	@Override
	public KeyValue<String, List<Tuple>> bzMPop(final String[] keys, final int timeout, final MinMax minMax) {
		final CommandArguments args = CommandArguments.create(timeout).add(keys.length).add(keys).add(minMax);
		final MinMaxSortedSetOptionConverter minMaxSortedSetOptionConverter = new MinMaxSortedSetOptionConverter();
		return executeCommand(RedisCommand.BZMPOP, args,
				(cmd)->cmd.bzmpop(timeout, minMaxSortedSetOptionConverter.convert(minMax), keys),
				(cmd)->cmd.bzmpop(timeout, minMaxSortedSetOptionConverter.convert(minMax), keys),
				(cmd)->cmd.bzmpop(timeout, minMaxSortedSetOptionConverter.convert(minMax), keys),
				new KeyValueConverter<>((k)->k, new ListConverter<>(new TupleConverter())));
	}

	@Override
	public KeyValue<byte[], List<Tuple>> bzMPop(final byte[][] keys, final int timeout, final MinMax minMax) {
		final CommandArguments args = CommandArguments.create(timeout).add(keys.length).add(keys).add(minMax);
		final MinMaxSortedSetOptionConverter minMaxSortedSetOptionConverter = new MinMaxSortedSetOptionConverter();
		return executeCommand(RedisCommand.BZMPOP, args,
				(cmd)->cmd.bzmpop(timeout, minMaxSortedSetOptionConverter.convert(minMax), keys),
				(cmd)->cmd.bzmpop(timeout, minMaxSortedSetOptionConverter.convert(minMax), keys),
				(cmd)->cmd.bzmpop(timeout, minMaxSortedSetOptionConverter.convert(minMax), keys),
				new KeyValueConverter<>((k)->k, new ListConverter<>(new TupleConverter())));
	}

	@Override
	public KeyValue<String, List<Tuple>> bzMPop(final String[] keys, final int timeout, final MinMax minMax,
	                                            final int count) {
		final CommandArguments args = CommandArguments.create(timeout).add(keys.length).add(keys).add(minMax)
				.add(Keyword.Common.COUNT).add(count);
		final MinMaxSortedSetOptionConverter minMaxSortedSetOptionConverter = new MinMaxSortedSetOptionConverter();
		return executeCommand(RedisCommand.BZMPOP, args,
				(cmd)->cmd.bzmpop(timeout, minMaxSortedSetOptionConverter.convert(minMax), count, keys),
				(cmd)->cmd.bzmpop(timeout, minMaxSortedSetOptionConverter.convert(minMax), count, keys),
				(cmd)->cmd.bzmpop(timeout, minMaxSortedSetOptionConverter.convert(minMax), count, keys),
				new KeyValueConverter<>((k)->k, new ListConverter<>(new TupleConverter())));
	}

	@Override
	public KeyValue<byte[], List<Tuple>> bzMPop(final byte[][] keys, final int timeout, final MinMax minMax,
	                                            final int count) {
		final CommandArguments args = CommandArguments.create(timeout).add(keys.length).add(keys).add(minMax)
				.add(Keyword.Common.COUNT).add(count);
		final MinMaxSortedSetOptionConverter minMaxSortedSetOptionConverter = new MinMaxSortedSetOptionConverter();
		return executeCommand(RedisCommand.BZMPOP, args,
				(cmd)->cmd.bzmpop(timeout, minMaxSortedSetOptionConverter.convert(minMax), count, keys),
				(cmd)->cmd.bzmpop(timeout, minMaxSortedSetOptionConverter.convert(minMax), count, keys),
				(cmd)->cmd.bzmpop(timeout, minMaxSortedSetOptionConverter.convert(minMax), count, keys),
				new KeyValueConverter<>((k)->k, new ListConverter<>(new TupleConverter())));
	}

	@Override
	public KeyValue<String, Tuple> bzPopMax(final String[] keys, final int timeout) {
		final CommandArguments args = CommandArguments.create(keys).add(timeout);
		return executeCommand(RedisCommand.BZPOPMAX, args, (cmd)->cmd.bzpopmax(timeout, keys),
				(cmd)->cmd.bzpopmax(timeout, keys), (cmd)->cmd.bzpopmax(timeout, keys),
				new KeyValueConverter<>((k)->k, new TupleConverter()));
	}

	@Override
	public KeyValue<byte[], Tuple> bzPopMax(final byte[][] keys, final int timeout) {
		final CommandArguments args = CommandArguments.create(keys).add(timeout);
		return executeCommand(RedisCommand.BZPOPMAX, args, (cmd)->cmd.bzpopmax(timeout, keys),
				(cmd)->cmd.bzpopmax(timeout, keys), (cmd)->cmd.bzpopmax(timeout, keys),
				new KeyValueConverter<>((k)->k, new TupleConverter()));
	}

	@Override
	public KeyValue<String, Tuple> bzPopMin(final String[] keys, final int timeout) {
		final CommandArguments args = CommandArguments.create(keys).add(timeout);
		return executeCommand(RedisCommand.BZPOPMIN, args, (cmd)->cmd.bzpopmin(timeout, keys),
				(cmd)->cmd.bzpopmin(timeout, keys), (cmd)->cmd.bzpopmin(timeout, keys),
				new KeyValueConverter<>((k)->k, new TupleConverter()));
	}

	@Override
	public KeyValue<byte[], Tuple> bzPopMin(final byte[][] keys, final int timeout) {
		final CommandArguments args = CommandArguments.create(keys).add(timeout);
		return executeCommand(RedisCommand.BZPOPMIN, args, (cmd)->cmd.bzpopmin(timeout, keys),
				(cmd)->cmd.bzpopmin(timeout, keys), (cmd)->cmd.bzpopmin(timeout, keys),
				new KeyValueConverter<>((k)->k, new TupleConverter()));
	}

	@Override
	public Long zAdd(final String key, final Tuple... members) {
		final CommandArguments args = CommandArguments.create(key).add(members);
		final Map<String, Double> membersMap = new LinkedHashMap<>(members.length);

		for(final Tuple element : members){
			membersMap.put(element.getElement(), element.getScore());
		}

		return executeCommand(RedisCommand.ZADD, args, (cmd)->cmd.zadd(key, membersMap),
				(cmd)->cmd.zadd(key, membersMap), (cmd)->cmd.zadd(key, membersMap));
	}

	@Override
	public Long zAdd(final byte[] key, final Tuple... members) {
		final CommandArguments args = CommandArguments.create(key).add(members);
		final Map<byte[], Double> membersMap = new LinkedHashMap<>(members.length);

		for(final Tuple element : members){
			membersMap.put(element.getBinaryElement(), element.getScore());
		}

		return executeCommand(RedisCommand.ZADD, args, (cmd)->cmd.zadd(key, membersMap),
				(cmd)->cmd.zadd(key, membersMap), (cmd)->cmd.zadd(key, membersMap));
	}

	@Override
	public Long zAdd(final String key, final Tuple[] members, final ZAddArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(argument).add(members);
		final Map<String, Double> membersMap = new LinkedHashMap<>(members.length);

		for(final Tuple element : members){
			membersMap.put(element.getElement(), element.getScore());
		}

		return executeCommand(RedisCommand.ZADD, args, (cmd)->cmd.zadd(key, membersMap, new JedisZAddParams(argument)),
				(cmd)->cmd.zadd(key, membersMap, new JedisZAddParams(argument)),
				(cmd)->cmd.zadd(key, membersMap, new JedisZAddParams(argument)));
	}

	@Override
	public Long zAdd(final byte[] key, final Tuple[] members, final ZAddArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(argument).add(members);
		final Map<byte[], Double> membersMap = new LinkedHashMap<>(members.length);

		for(final Tuple element : members){
			membersMap.put(element.getBinaryElement(), element.getScore());
		}

		return executeCommand(RedisCommand.ZADD, args, (cmd)->cmd.zadd(key, membersMap, new JedisZAddParams(argument)),
				(cmd)->cmd.zadd(key, membersMap, new JedisZAddParams(argument)),
				(cmd)->cmd.zadd(key, membersMap, new JedisZAddParams(argument)));
	}

	@Override
	public Long zCard(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.ZCARD, args, (cmd)->cmd.zcard(key), (cmd)->cmd.zcard(key),
				(cmd)->cmd.zcard(key));
	}

	@Override
	public Long zCard(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.ZCARD, args, (cmd)->cmd.zcard(key), (cmd)->cmd.zcard(key),
				(cmd)->cmd.zcard(key));
	}

	@Override
	public Long zCount(final String key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);
		return executeCommand(RedisCommand.ZCOUNT, args, (cmd)->cmd.zcount(key, min, max),
				(cmd)->cmd.zcount(key, min, max), (cmd)->cmd.zcount(key, min, max));
	}

	@Override
	public Long zCount(final byte[] key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);
		return executeCommand(RedisCommand.ZCOUNT, args, (cmd)->cmd.zcount(key, min, max),
				(cmd)->cmd.zcount(key, min, max), (cmd)->cmd.zcount(key, min, max));
	}

	@Override
	public List<String> zDiff(final String... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return executeCommand(RedisCommand.ZDIFF, args, (cmd)->cmd.zdiff(keys),
				(cmd)->cmd.zdiff(keys), (cmd)->cmd.zdiff(keys));
	}

	@Override
	public List<byte[]> zDiff(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return executeCommand(RedisCommand.ZDIFF, args, (cmd)->cmd.zdiff(keys),
				(cmd)->cmd.zdiff(keys), (cmd)->cmd.zdiff(keys));
	}

	@Override
	public List<Tuple> zDiffWithScores(final String... keys) {
		final CommandArguments args = CommandArguments.create(keys).add(Keyword.Common.WITHSCORES);
		return executeCommand(RedisCommand.ZDIFF, args, (cmd)->cmd.zdiffWithScores(keys),
				(cmd)->cmd.zdiffWithScores(keys), (cmd)->cmd.zdiffWithScores(keys),
				new ListConverter<>(new TupleConverter()));
	}

	@Override
	public List<Tuple> zDiffWithScores(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(keys).add(Keyword.Common.WITHSCORES);
		return executeCommand(RedisCommand.ZDIFF, args, (cmd)->cmd.zdiffWithScores(keys),
				(cmd)->cmd.zdiffWithScores(keys), (cmd)->cmd.zdiffWithScores(keys),
				new ListConverter<>(new TupleConverter()));
	}

	@Override
	public Long zDiffStore(final String destKey, final String... keys) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys);
		return executeCommand(RedisCommand.ZDIFFSTORE, args, (cmd)->cmd.zdiffStore(destKey, keys),
				(cmd)->cmd.zdiffStore(destKey, keys), (cmd)->cmd.zdiffStore(destKey, keys));
	}

	@Override
	public Long zDiffStore(final byte[] destKey, final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys);
		return executeCommand(RedisCommand.ZDIFFSTORE, args, (cmd)->cmd.zdiffStore(destKey, keys),
				(cmd)->cmd.zdiffStore(destKey, keys), (cmd)->cmd.zdiffStore(destKey, keys));
	}

	@Override
	public Double zIncrBy(final String key, final double increment, final String member) {
		final CommandArguments args = CommandArguments.create(key).add(increment).add(member);
		return executeCommand(RedisCommand.ZINCRBY, args, (cmd)->cmd.zincrby(key, increment, member),
				(cmd)->cmd.zincrby(key, increment, member), (cmd)->cmd.zincrby(key, increment, member));
	}

	@Override
	public Double zIncrBy(final byte[] key, final double increment, final byte[] member) {
		final CommandArguments args = CommandArguments.create(key).add(increment).add(member);
		return executeCommand(RedisCommand.ZINCRBY, args, (cmd)->cmd.zincrby(key, increment, member),
				(cmd)->cmd.zincrby(key, increment, member), (cmd)->cmd.zincrby(key, increment, member));
	}

	@Override
	public List<String> zInter(final String... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return zInter(keys, new JedisZParams(), args);
	}

	@Override
	public List<byte[]> zInter(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return zInter(keys, new JedisZParams(), args);
	}

	@Override
	public List<String> zInter(final String[] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create(keys).add("AGGREGATE").add(aggregate);
		return zInter(keys, new JedisZParams(aggregate), args);
	}

	@Override
	public List<byte[]> zInter(final byte[][] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create(keys).add("AGGREGATE").add(aggregate);
		return zInter(keys, new JedisZParams(aggregate), args);
	}

	@Override
	public List<String> zInter(final String[] keys, final Aggregate aggregate, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add("AGGREGATE").add(aggregate).add("WEIGHTS")
				.add(weights);
		return zInter(keys, new JedisZParams(aggregate, weights), args);
	}

	@Override
	public List<byte[]> zInter(final byte[][] keys, final Aggregate aggregate, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add("AGGREGATE").add(aggregate).add("WEIGHTS")
				.add(weights);
		return zInter(keys, new JedisZParams(aggregate, weights), args);
	}

	@Override
	public List<String> zInter(final String[] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add("WEIGHTS").add(weights);
		return zInter(keys, new JedisZParams(weights), args);
	}

	@Override
	public List<byte[]> zInter(final byte[][] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add("WEIGHTS").add(weights);
		return zInter(keys, new JedisZParams(weights), args);
	}

	@Override
	public List<Tuple> zInterWithScores(final String... keys) {
		final CommandArguments args = CommandArguments.create(keys).add(Keyword.Common.WITHSCORES);
		return zInterWithScores(keys, new JedisZParams(), args);
	}

	@Override
	public List<Tuple> zInterWithScores(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(keys).add(Keyword.Common.WITHSCORES);
		return zInterWithScores(keys, new JedisZParams(), args);
	}

	@Override
	public List<Tuple> zInterWithScores(final String[] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create(keys).add("AGGREGATE").add(aggregate)
				.add(Keyword.Common.WITHSCORES);
		return zInterWithScores(keys, new JedisZParams(aggregate), args);
	}

	@Override
	public List<Tuple> zInterWithScores(final byte[][] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create(keys).add("AGGREGATE").add(aggregate)
				.add(Keyword.Common.WITHSCORES);
		return zInterWithScores(keys, new JedisZParams(aggregate), args);
	}

	@Override
	public List<Tuple> zInterWithScores(final String[] keys, final Aggregate aggregate, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add("AGGREGATE").add(aggregate).add("WEIGHTS")
				.add(weights)
				.add(Keyword.Common.WITHSCORES);
		return zInterWithScores(keys, new JedisZParams(aggregate), args);
	}

	@Override
	public List<Tuple> zInterWithScores(final byte[][] keys, final Aggregate aggregate, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add("AGGREGATE").add(aggregate).add("WEIGHTS")
				.add(weights)
				.add(Keyword.Common.WITHSCORES);
		return zInterWithScores(keys, new JedisZParams(aggregate), args);
	}

	@Override
	public List<Tuple> zInterWithScores(final String[] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add("WEIGHTS").add(weights)
				.add(Keyword.Common.WITHSCORES);
		return zInterWithScores(keys, new JedisZParams(weights), args);
	}

	@Override
	public List<Tuple> zInterWithScores(final byte[][] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add("WEIGHTS").add(weights)
				.add(Keyword.Common.WITHSCORES);
		return zInterWithScores(keys, new JedisZParams(weights), args);
	}

	@Override
	public long zInterCard(final String... keys) {
		final CommandArguments args = CommandArguments.create(keys.length).add(keys);
		return executeCommand(RedisCommand.ZINTERCARD, args, (cmd)->cmd.zintercard(keys),
				(cmd)->cmd.zintercard(keys), (cmd)->cmd.zintercard(keys));
	}

	@Override
	public long zInterCard(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(keys.length).add(keys);
		return executeCommand(RedisCommand.ZINTERCARD, args, (cmd)->cmd.zintercard(keys),
				(cmd)->cmd.zintercard(keys), (cmd)->cmd.zintercard(keys));
	}

	@Override
	public long zInterCard(final String[] keys, final int limit) {
		final CommandArguments args = CommandArguments.create(keys.length).add(keys).add(Keyword.Common.LIMIT)
				.add(limit);
		return executeCommand(RedisCommand.ZINTERCARD, args, (cmd)->cmd.zintercard(limit, keys),
				(cmd)->cmd.zintercard(limit, keys), (cmd)->cmd.zintercard(limit, keys));
	}

	@Override
	public long zInterCard(final byte[][] keys, final int limit) {
		final CommandArguments args = CommandArguments.create(keys.length).add(keys).add(Keyword.Common.LIMIT)
				.add(limit);
		return executeCommand(RedisCommand.ZINTERCARD, args, (cmd)->cmd.zintercard(limit, keys),
				(cmd)->cmd.zintercard(limit, keys), (cmd)->cmd.zintercard(limit, keys));
	}

	@Override
	public Long zInterStore(final String destKey, final String... keys) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys.length).add(keys);
		return executeCommand(RedisCommand.ZINTERSTORE, args, (cmd)->cmd.zinterstore(destKey, keys),
				(cmd)->cmd.zinterstore(destKey, keys), (cmd)->cmd.zinterstore(destKey, keys));
	}

	@Override
	public Long zInterStore(final byte[] destKey, final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys.length).add(keys);
		return executeCommand(RedisCommand.ZINTERSTORE, args, (cmd)->cmd.zinterstore(destKey, keys),
				(cmd)->cmd.zinterstore(destKey, keys), (cmd)->cmd.zinterstore(destKey, keys));
	}

	@Override
	public Long zInterStore(final String destKey, final String[] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys.length).add(keys)
				.add("AGGREGATE").add(aggregate);
		return zInterStore(destKey, keys, new JedisZParams(aggregate), args);
	}

	@Override
	public Long zInterStore(final byte[] destKey, final byte[][] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys.length).add(keys)
				.add("AGGREGATE").add(aggregate);
		return zInterStore(destKey, keys, new JedisZParams(aggregate), args);
	}

	@Override
	public Long zInterStore(final String destKey, final String[] keys, final Aggregate aggregate,
	                        final double... weights) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys.length).add(keys)
				.add("AGGREGATE").add(aggregate).add("WEIGHTS").add(weights);
		return zInterStore(destKey, keys, new JedisZParams(aggregate, weights), args);
	}

	@Override
	public Long zInterStore(final byte[] destKey, final byte[][] keys, final Aggregate aggregate,
	                        final double... weights) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys.length).add(keys)
				.add("AGGREGATE").add(aggregate).add("WEIGHTS").add(weights);
		return zInterStore(destKey, keys, new JedisZParams(aggregate, weights), args);
	}

	@Override
	public Long zInterStore(final String destKey, final String[] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys.length).add(keys)
				.add("WEIGHTS").add(weights);
		return zInterStore(destKey, keys, new JedisZParams(weights), args);
	}

	@Override
	public Long zInterStore(final byte[] destKey, final byte[][] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys.length).add(keys)
				.add("WEIGHTS").add(weights);
		return zInterStore(destKey, keys, new JedisZParams(weights), args);
	}

	@Override
	public Long zLexCount(final String key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);
		return executeCommand(RedisCommand.ZLEXCOUNT, args,
				(cmd)->cmd.zlexcount(key, Double.toString(min), Double.toString(max)),
				(cmd)->cmd.zlexcount(key, Double.toString(min), Double.toString(max)),
				(cmd)->cmd.zlexcount(key, Double.toString(min), Double.toString(max)));
	}

	@Override
	public Long zLexCount(final byte[] key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);
		return executeCommand(RedisCommand.ZLEXCOUNT, args,
				(cmd)->cmd.zlexcount(key, NumberUtils.double2bytes(min), NumberUtils.double2bytes(max)),
				(cmd)->cmd.zlexcount(key, NumberUtils.double2bytes(min), NumberUtils.double2bytes(max)),
				(cmd)->cmd.zlexcount(key, NumberUtils.double2bytes(min), NumberUtils.double2bytes(max)));
	}

	@Override
	public KeyValue<String, List<Tuple>> zMPop(final String[] keys, final MinMax minMax) {
		final CommandArguments args = CommandArguments.create(keys.length).add(keys).add(minMax);
		final MinMaxSortedSetOptionConverter minMaxSortedSetOptionConverter = new MinMaxSortedSetOptionConverter();
		return executeCommand(RedisCommand.ZMPOP, args,
				(cmd)->cmd.zmpop(minMaxSortedSetOptionConverter.convert(minMax), keys),
				(cmd)->cmd.zmpop(minMaxSortedSetOptionConverter.convert(minMax), keys),
				(cmd)->cmd.zmpop(minMaxSortedSetOptionConverter.convert(minMax), keys),
				new KeyValueConverter<>((k)->k, new ListConverter<>(new TupleConverter())));
	}

	@Override
	public KeyValue<byte[], List<Tuple>> zMPop(final byte[][] keys, final MinMax minMax) {
		final CommandArguments args = CommandArguments.create(keys.length).add(keys).add(minMax);
		final MinMaxSortedSetOptionConverter minMaxSortedSetOptionConverter = new MinMaxSortedSetOptionConverter();
		return executeCommand(RedisCommand.ZMPOP, args,
				(cmd)->cmd.zmpop(minMaxSortedSetOptionConverter.convert(minMax), keys),
				(cmd)->cmd.zmpop(minMaxSortedSetOptionConverter.convert(minMax), keys),
				(cmd)->cmd.zmpop(minMaxSortedSetOptionConverter.convert(minMax), keys),
				new KeyValueConverter<>((k)->k, new ListConverter<>(new TupleConverter())));
	}

	@Override
	public KeyValue<String, List<Tuple>> zMPop(final String[] keys, final MinMax minMax, final int count) {
		final CommandArguments args = CommandArguments.create(keys.length).add(keys).add(minMax)
				.add(Keyword.Common.COUNT).add(count);
		final MinMaxSortedSetOptionConverter minMaxSortedSetOptionConverter = new MinMaxSortedSetOptionConverter();
		return executeCommand(RedisCommand.ZMPOP, args,
				(cmd)->cmd.zmpop(minMaxSortedSetOptionConverter.convert(minMax), count, keys),
				(cmd)->cmd.zmpop(minMaxSortedSetOptionConverter.convert(minMax), count, keys),
				(cmd)->cmd.zmpop(minMaxSortedSetOptionConverter.convert(minMax), count, keys),
				new KeyValueConverter<>((k)->k, new ListConverter<>(new TupleConverter())));
	}

	@Override
	public KeyValue<byte[], List<Tuple>> zMPop(final byte[][] keys, final MinMax minMax, final int count) {
		final CommandArguments args = CommandArguments.create(keys.length).add(keys).add(minMax)
				.add(Keyword.Common.COUNT).add(count);
		final MinMaxSortedSetOptionConverter minMaxSortedSetOptionConverter = new MinMaxSortedSetOptionConverter();
		return executeCommand(RedisCommand.ZMPOP, args,
				(cmd)->cmd.zmpop(minMaxSortedSetOptionConverter.convert(minMax), count, keys),
				(cmd)->cmd.zmpop(minMaxSortedSetOptionConverter.convert(minMax), count, keys),
				(cmd)->cmd.zmpop(minMaxSortedSetOptionConverter.convert(minMax), count, keys),
				new KeyValueConverter<>((k)->k, new ListConverter<>(new TupleConverter())));
	}

	@Override
	public List<Double> zMScore(final String key, final String... members) {
		final CommandArguments args = CommandArguments.create(key).add(members);
		return executeCommand(RedisCommand.ZMSCORE, args, (cmd)->cmd.zmscore(key, members),
				(cmd)->cmd.zmscore(key, members), (cmd)->cmd.zmscore(key, members));
	}

	@Override
	public List<Double> zMScore(final byte[] key, final byte[]... members) {
		final CommandArguments args = CommandArguments.create(key).add(members);
		return executeCommand(RedisCommand.ZMSCORE, args, (cmd)->cmd.zmscore(key, members),
				(cmd)->cmd.zmscore(key, members), (cmd)->cmd.zmscore(key, members));
	}

	@Override
	public Tuple zPopMax(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.ZPOPMAX, args, (cmd)->cmd.zpopmax(key),
				(cmd)->cmd.zpopmax(key), (cmd)->cmd.zpopmax(key), new TupleConverter());
	}

	@Override
	public Tuple zPopMax(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.ZPOPMAX, args, (cmd)->cmd.zpopmax(key),
				(cmd)->cmd.zpopmax(key), (cmd)->cmd.zpopmax(key), new TupleConverter());
	}

	@Override
	public List<Tuple> zPopMax(final String key, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(count);
		return executeCommand(RedisCommand.ZPOPMAX, args, (cmd)->cmd.zpopmax(key, count),
				(cmd)->cmd.zpopmax(key, count), (cmd)->cmd.zpopmax(key, count),
				new ListConverter<>(new TupleConverter()));
	}

	@Override
	public List<Tuple> zPopMax(final byte[] key, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(count);
		return executeCommand(RedisCommand.ZPOPMAX, args, (cmd)->cmd.zpopmax(key, count),
				(cmd)->cmd.zpopmax(key, count), (cmd)->cmd.zpopmax(key, count),
				new ListConverter<>(new TupleConverter()));
	}

	@Override
	public Tuple zPopMin(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.ZPOPMIN, args, (cmd)->cmd.zpopmin(key),
				(cmd)->cmd.zpopmin(key), (cmd)->cmd.zpopmin(key), new TupleConverter());
	}

	@Override
	public Tuple zPopMin(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.ZPOPMIN, args, (cmd)->cmd.zpopmin(key),
				(cmd)->cmd.zpopmin(key), (cmd)->cmd.zpopmin(key), new TupleConverter());
	}

	@Override
	public List<Tuple> zPopMin(final String key, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(count);
		return executeCommand(RedisCommand.ZPOPMIN, args, (cmd)->cmd.zpopmin(key, count),
				(cmd)->cmd.zpopmin(key, count), (cmd)->cmd.zpopmin(key, count),
				new ListConverter<>(new TupleConverter()));
	}

	@Override
	public List<Tuple> zPopMin(final byte[] key, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(count);
		return executeCommand(RedisCommand.ZPOPMIN, args, (cmd)->cmd.zpopmin(key, count),
				(cmd)->cmd.zpopmin(key, count), (cmd)->cmd.zpopmin(key, count),
				new ListConverter<>(new TupleConverter()));
	}

	@Override
	public String zRandMember(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.ZRANDMEMBER, args, (cmd)->cmd.zrandmember(key),
				(cmd)->cmd.zrandmember(key), (cmd)->cmd.zrandmember(key));
	}

	@Override
	public byte[] zRandMember(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.ZRANDMEMBER, args, (cmd)->cmd.zrandmember(key),
				(cmd)->cmd.zrandmember(key), (cmd)->cmd.zrandmember(key));
	}

	@Override
	public List<String> zRandMember(final String key, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(count);
		return executeCommand(RedisCommand.ZRANDMEMBER, args, (cmd)->cmd.zrandmember(key, count),
				(cmd)->cmd.zrandmember(key, count), (cmd)->cmd.zrandmember(key, count));
	}

	@Override
	public List<byte[]> zRandMember(final byte[] key, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(count);
		return executeCommand(RedisCommand.ZRANDMEMBER, args, (cmd)->cmd.zrandmember(key, count),
				(cmd)->cmd.zrandmember(key, count), (cmd)->cmd.zrandmember(key, count));
	}

	@Override
	public Tuple zRandMemberWithScores(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.ZRANDMEMBER, args, (cmd)->cmd.zrandmemberWithScores(key, 1),
				(cmd)->cmd.zrandmemberWithScores(key, 1), (cmd)->cmd.zrandmemberWithScores(key, 1),
				(v)->{
					if(Validate.isEmpty(v)){
						return null;
					}else{
						final TupleConverter tupleConverter = new TupleConverter();
						return tupleConverter.convert(v.get(0));
					}
				});
	}

	@Override
	public Tuple zRandMemberWithScores(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.ZRANDMEMBER, args, (cmd)->cmd.zrandmemberWithScores(key, 1),
				(cmd)->cmd.zrandmemberWithScores(key, 1), (cmd)->cmd.zrandmemberWithScores(key, 1),
				(v)->{
					if(Validate.isEmpty(v)){
						return null;
					}else{
						final TupleConverter tupleConverter = new TupleConverter();
						return tupleConverter.convert(v.get(0));
					}
				});
	}

	@Override
	public List<Tuple> zRandMemberWithScores(final String key, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(count);
		return executeCommand(RedisCommand.ZRANDMEMBER, args, (cmd)->cmd.zrandmemberWithScores(key, count),
				(cmd)->cmd.zrandmemberWithScores(key, count), (cmd)->cmd.zrandmemberWithScores(key, count),
				new ListConverter<>(new TupleConverter()));
	}

	@Override
	public List<Tuple> zRandMemberWithScores(final byte[] key, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(count);
		return executeCommand(RedisCommand.ZRANDMEMBER, args, (cmd)->cmd.zrandmemberWithScores(key, count),
				(cmd)->cmd.zrandmemberWithScores(key, count), (cmd)->cmd.zrandmemberWithScores(key, count),
				new ListConverter<>(new TupleConverter()));
	}

	@Override
	public List<String> zRange(final String key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end);
		return zRange(key, new JedisZRangeParams(start, end), args);
	}

	@Override
	public List<byte[]> zRange(final byte[] key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end);
		return zRange(key, new JedisZRangeParams(start, end), args);
	}

	@Override
	public List<String> zRange(final String key, final long start, final long end, final ZRangeType type) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end).add(type);
		return zRange(key, new JedisZRangeParams(type, start, end), args);
	}

	@Override
	public List<byte[]> zRange(final byte[] key, final long start, final long end, final ZRangeType type) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end).add(type);
		return zRange(key, new JedisZRangeParams(type, start, end), args);
	}

	@Override
	public List<String> zRange(final String key, final long start, final long end, final ZRangeType type,
	                           final boolean rev) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end).add(type)
				.add(rev ? "REV" : null);
		return zRange(key, new JedisZRangeParams(type, start, end, rev), args);
	}

	@Override
	public List<byte[]> zRange(final byte[] key, final long start, final long end, final ZRangeType type,
	                           final boolean rev) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end).add(type)
				.add(rev ? "REV" : null);
		return zRange(key, new JedisZRangeParams(type, start, end, rev), args);
	}

	@Override
	public List<String> zRange(final String key, final long start, final long end, final boolean rev) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end).add(rev ? "REV" : null);
		return zRange(key, new JedisZRangeParams(start, end, rev), args);
	}

	@Override
	public List<byte[]> zRange(final byte[] key, final long start, final long end, final boolean rev) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end).add(rev ? "REV" : null);
		return zRange(key, new JedisZRangeParams(start, end, rev), args);
	}

	@Override
	public List<String> zRange(final String key, final long start, final long end, final int offset, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end).add(Keyword.Common.LIMIT)
				.add(offset).add(count);
		return zRange(key, new JedisZRangeParams(start, end, offset, count), args);
	}

	@Override
	public List<byte[]> zRange(final byte[] key, final long start, final long end, final int offset, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end).add(Keyword.Common.LIMIT)
				.add(offset).add(count);
		return zRange(key, new JedisZRangeParams(start, end, offset, count), args);
	}

	@Override
	public List<String> zRange(final String key, final long start, final long end, final ZRangeType type,
	                           final int offset, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end).add(type)
				.add(Keyword.Common.LIMIT)
				.add(offset).add(count);
		return zRange(key, new JedisZRangeParams(type, start, end, offset, count), args);
	}

	@Override
	public List<byte[]> zRange(final byte[] key, final long start, final long end, final ZRangeType type,
	                           final int offset, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end).add(type)
				.add(Keyword.Common.LIMIT)
				.add(offset).add(count);
		return zRange(key, new JedisZRangeParams(type, start, end, offset, count), args);
	}

	@Override
	public List<String> zRange(final String key, final long start, final long end, final ZRangeType type,
	                           final boolean rev, final int offset, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end).add(type).add(rev ? "REV" : null)
				.add(Keyword.Common.LIMIT).add(offset).add(count);
		return zRange(key, new JedisZRangeParams(type, start, end, rev, offset, count), args);
	}

	@Override
	public List<byte[]> zRange(final byte[] key, final long start, final long end, final ZRangeType type,
	                           final boolean rev, final int offset, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end).add(type).add(rev ? "REV" : null)
				.add(Keyword.Common.LIMIT).add(offset).add(count);
		return zRange(key, new JedisZRangeParams(type, start, end, rev, offset, count), args);
	}

	@Override
	public List<String> zRange(final String key, final long start, final long end, final boolean rev, final int offset,
	                           final int count) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end).add(rev ? "REV" : null)
				.add(Keyword.Common.LIMIT).add(offset).add(count);
		return zRange(key, new JedisZRangeParams(start, end, rev, offset, count), args);
	}

	@Override
	public List<byte[]> zRange(final byte[] key, final long start, final long end, final boolean rev, final int offset,
	                           final int count) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end).add(rev ? "REV" : null)
				.add(Keyword.Common.LIMIT).add(offset).add(count);
		return zRange(key, new JedisZRangeParams(start, end, rev, offset, count), args);
	}

	@Override
	public List<Tuple> zRangeWithScores(final String key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end);
		return executeCommand(RedisCommand.ZRANGE, args, (cmd)->cmd.zrangeWithScores(key, start, end),
				(cmd)->cmd.zrangeWithScores(key, start, end), (cmd)->cmd.zrangeWithScores(key, start, end),
				new ListConverter<>(new TupleConverter()));
	}

	@Override
	public List<Tuple> zRangeWithScores(final byte[] key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end);
		return executeCommand(RedisCommand.ZRANGE, args, (cmd)->cmd.zrangeWithScores(key, start, end),
				(cmd)->cmd.zrangeWithScores(key, start, end), (cmd)->cmd.zrangeWithScores(key, start, end),
				new ListConverter<>(new TupleConverter()));
	}

	@Override
	public List<Tuple> zRangeWithScores(final String key, final long start, final long end, final ZRangeType type) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end).add(type);
		return zRangeWithScores(key, new JedisZRangeParams(type, start, end), args);
	}

	@Override
	public List<Tuple> zRangeWithScores(final byte[] key, final long start, final long end, final ZRangeType type) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end).add(type);
		return zRangeWithScores(key, new JedisZRangeParams(type, start, end), args);
	}

	@Override
	public List<Tuple> zRangeWithScores(final String key, final long start, final long end, final ZRangeType type,
	                                    final boolean rev) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end).add(type)
				.add(rev ? "REV" : null);
		return zRangeWithScores(key, new JedisZRangeParams(type, start, end), args);
	}

	@Override
	public List<Tuple> zRangeWithScores(final byte[] key, final long start, final long end, final ZRangeType type,
	                                    final boolean rev) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end).add(type)
				.add(rev ? "REV" : null);
		return zRangeWithScores(key, new JedisZRangeParams(type, start, end), args);
	}

	@Override
	public List<Tuple> zRangeWithScores(final String key, final long start, final long end, final boolean rev) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end).add(rev ? "REV" : null);
		return zRangeWithScores(key, new JedisZRangeParams(start, end, rev), args);
	}

	@Override
	public List<Tuple> zRangeWithScores(final byte[] key, final long start, final long end, final boolean rev) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end).add(rev ? "REV" : null);
		return zRangeWithScores(key, new JedisZRangeParams(start, end, rev), args);
	}

	@Override
	public List<Tuple> zRangeWithScores(final String key, final long start, final long end, final int offset,
	                                    final int count) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end).add(Keyword.Common.LIMIT)
				.add(offset).add(count);
		return zRangeWithScores(key, new JedisZRangeParams(start, end, offset, count), args);
	}

	@Override
	public List<Tuple> zRangeWithScores(final byte[] key, final long start, final long end, final int offset,
	                                    final int count) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end).add(Keyword.Common.LIMIT)
				.add(offset).add(count);
		return zRangeWithScores(key, new JedisZRangeParams(start, end, offset, count), args);
	}

	@Override
	public List<Tuple> zRangeWithScores(final String key, final long start, final long end, final ZRangeType type,
	                                    final int offset, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end).add(type)
				.add(Keyword.Common.LIMIT)
				.add(offset).add(count);
		return zRangeWithScores(key, new JedisZRangeParams(type, start, end, offset, count), args);
	}

	@Override
	public List<Tuple> zRangeWithScores(final byte[] key, final long start, final long end, final ZRangeType type,
	                                    final int offset, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end).add(type)
				.add(Keyword.Common.LIMIT)
				.add(offset).add(count);
		return zRangeWithScores(key, new JedisZRangeParams(type, start, end, offset, count), args);
	}

	@Override
	public List<Tuple> zRangeWithScores(final String key, final long start, final long end, final ZRangeType type,
	                                    final boolean rev, final int offset, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end).add(type).add(rev ? "REV" : null)
				.add(Keyword.Common.LIMIT).add(offset).add(count);
		return zRangeWithScores(key, new JedisZRangeParams(type, start, end, rev, offset, count), args);
	}

	@Override
	public List<Tuple> zRangeWithScores(final byte[] key, final long start, final long end, final ZRangeType type,
	                                    final boolean rev, final int offset, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end).add(type).add(rev ? "REV" : null)
				.add(Keyword.Common.LIMIT).add(offset).add(count);
		return zRangeWithScores(key, new JedisZRangeParams(type, start, end, rev, offset, count), args);
	}

	@Override
	public List<Tuple> zRangeWithScores(final String key, final long start, final long end, final boolean rev,
	                                    final int offset, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end).add(rev ? "REV" : null)
				.add(Keyword.Common.LIMIT).add(offset).add(count);
		return zRangeWithScores(key, new JedisZRangeParams(start, end, rev, offset, count), args);
	}

	@Override
	public List<Tuple> zRangeWithScores(final byte[] key, final long start, final long end, final boolean rev,
	                                    final int offset, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end).add(rev ? "REV" : null)
				.add(Keyword.Common.LIMIT).add(offset).add(count);
		return zRangeWithScores(key, new JedisZRangeParams(start, end, rev, offset, count), args);
	}

	@Override
	public List<String> zRangeByLex(final String key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);
		return executeCommand(RedisCommand.ZRANGEBYLEX, args,
				(cmd)->cmd.zrangeByLex(key, Double.toString(min), Double.toString(max)),
				(cmd)->cmd.zrangeByLex(key, Double.toString(min), Double.toString(max)),
				(cmd)->cmd.zrangeByLex(key, Double.toString(min), Double.toString(max)));
	}

	@Override
	public List<byte[]> zRangeByLex(final byte[] key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);
		return executeCommand(RedisCommand.ZRANGEBYLEX, args,
				(cmd)->cmd.zrangeByLex(key, NumberUtils.double2bytes(min), NumberUtils.double2bytes(max)),
				(cmd)->cmd.zrangeByLex(key, NumberUtils.double2bytes(min), NumberUtils.double2bytes(max)),
				(cmd)->cmd.zrangeByLex(key, NumberUtils.double2bytes(min), NumberUtils.double2bytes(max)));
	}

	@Override
	public List<String> zRangeByLex(final String key, final double min, final double max, final int offset,
	                                final int count) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max).add(Keyword.Common.LIMIT)
				.add(offset).add(count);
		return executeCommand(RedisCommand.ZRANGEBYLEX, args,
				(cmd)->cmd.zrangeByLex(key, Double.toString(min), Double.toString(max), offset, count),
				(cmd)->cmd.zrangeByLex(key, Double.toString(min), Double.toString(max), offset, count),
				(cmd)->cmd.zrangeByLex(key, Double.toString(min), Double.toString(max), offset, count));
	}

	@Override
	public List<byte[]> zRangeByLex(final byte[] key, final double min, final double max, final int offset,
	                                final int count) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max).add(Keyword.Common.LIMIT)
				.add(offset).add(count);
		return executeCommand(RedisCommand.ZRANGEBYLEX, args,
				(cmd)->cmd.zrangeByLex(key, NumberUtils.double2bytes(min), NumberUtils.double2bytes(max),
						offset, count),
				(cmd)->cmd.zrangeByLex(key, NumberUtils.double2bytes(min), NumberUtils.double2bytes(max),
						offset, count),
				(cmd)->cmd.zrangeByLex(key, NumberUtils.double2bytes(min), NumberUtils.double2bytes(max),
						offset, count));
	}

	@Override
	public List<String> zRangeByScore(final String key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);
		return executeCommand(RedisCommand.ZRANGEBYSCORE, args,
				(cmd)->cmd.zrangeByScore(key, Double.toString(min), Double.toString(max)),
				(cmd)->cmd.zrangeByScore(key, Double.toString(min), Double.toString(max)),
				(cmd)->cmd.zrangeByScore(key, Double.toString(min), Double.toString(max)));
	}

	@Override
	public List<byte[]> zRangeByScore(final byte[] key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);
		return executeCommand(RedisCommand.ZRANGEBYSCORE, args, (cmd)->cmd.zrangeByScore(key, min, max),
				(cmd)->cmd.zrangeByScore(key, min, max), (cmd)->cmd.zrangeByScore(key, min, max));
	}

	@Override
	public List<String> zRangeByScore(final String key, final double min, final double max, final int offset,
	                                  final int count) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max).add(Keyword.Common.LIMIT)
				.add(offset).add(count);
		return executeCommand(RedisCommand.ZRANGEBYSCORE, args,
				(cmd)->cmd.zrangeByScore(key, Double.toString(min), Double.toString(max), offset, count),
				(cmd)->cmd.zrangeByScore(key, Double.toString(min), Double.toString(max), offset, count),
				(cmd)->cmd.zrangeByScore(key, Double.toString(min), Double.toString(max), offset, count));
	}

	@Override
	public List<byte[]> zRangeByScore(final byte[] key, final double min, final double max, final int offset,
	                                  final int count) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max).add(Keyword.Common.LIMIT)
				.add(offset).add(count);
		return executeCommand(RedisCommand.ZRANGEBYSCORE, args,
				(cmd)->cmd.zrangeByScore(key, min, max, offset, count),
				(cmd)->cmd.zrangeByScore(key, min, max, offset, count),
				(cmd)->cmd.zrangeByScore(key, min, max, offset, count));
	}

	@Override
	public List<Tuple> zRangeByScoreWithScores(final String key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max).add(Keyword.Common.WITHSCORES);
		return executeCommand(RedisCommand.ZRANGEBYSCORE, args,
				(cmd)->cmd.zrangeByScoreWithScores(key, Double.toString(min), Double.toString(max)),
				(cmd)->cmd.zrangeByScoreWithScores(key, Double.toString(min), Double.toString(max)),
				(cmd)->cmd.zrangeByScoreWithScores(key, Double.toString(min), Double.toString(max)),
				new ListConverter<>(new TupleConverter()));
	}

	@Override
	public List<Tuple> zRangeByScoreWithScores(final byte[] key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max).add(Keyword.Common.WITHSCORES);
		return executeCommand(RedisCommand.ZRANGEBYSCORE, args,
				(cmd)->cmd.zrangeByScoreWithScores(key, min, max),
				(cmd)->cmd.zrangeByScoreWithScores(key, min, max),
				(cmd)->cmd.zrangeByScoreWithScores(key, min, max), new ListConverter<>(new TupleConverter()));
	}

	@Override
	public List<Tuple> zRangeByScoreWithScores(final String key, final double min, final double max, final int offset,
	                                           final int count) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max).add(Keyword.Common.WITHSCORES)
				.add(Keyword.Common.LIMIT).add(offset).add(count);
		return executeCommand(RedisCommand.ZRANGEBYSCORE, args,
				(cmd)->cmd.zrangeByScoreWithScores(key, Double.toString(min), Double.toString(max), offset,
						count),
				(cmd)->cmd.zrangeByScoreWithScores(key, Double.toString(min), Double.toString(max), offset,
						count),
				(cmd)->cmd.zrangeByScoreWithScores(key, Double.toString(min), Double.toString(max), offset,
						count), new ListConverter<>(new TupleConverter()));
	}

	@Override
	public List<Tuple> zRangeByScoreWithScores(final byte[] key, final double min, final double max, final int offset,
	                                           final int count) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max).add(Keyword.Common.WITHSCORES)
				.add(Keyword.Common.LIMIT).add(offset).add(count);
		return executeCommand(RedisCommand.ZRANGEBYSCORE, args,
				(cmd)->cmd.zrangeByScoreWithScores(key, min, max, offset, count),
				(cmd)->cmd.zrangeByScoreWithScores(key, min, max, offset, count),
				(cmd)->cmd.zrangeByScoreWithScores(key, min, max, offset, count),
				new ListConverter<>(new TupleConverter()));
	}

	@Override
	public Long zRangeStore(final String destKey, final String key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(destKey, key).add(start).add(end);
		return zRangeStore(destKey, key, new JedisZRangeParams(start, end), args);
	}

	@Override
	public Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(destKey, key).add(start).add(end);
		return zRangeStore(destKey, key, new JedisZRangeParams(start, end), args);
	}

	@Override
	public Long zRangeStore(final String destKey, final String key, final long start, final long end,
	                        final ZRangeType type) {
		final CommandArguments args = CommandArguments.create(destKey, key).add(start).add(end).add(type);
		return zRangeStore(destKey, key, new JedisZRangeParams(type, start, end), args);
	}

	@Override
	public Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end,
	                        final ZRangeType type) {
		final CommandArguments args = CommandArguments.create(destKey, key).add(start).add(end).add(type);
		return zRangeStore(destKey, key, new JedisZRangeParams(type, start, end), args);
	}

	@Override
	public Long zRangeStore(final String destKey, final String key, final long start, final long end,
	                        final ZRangeType type, final boolean rev) {
		final CommandArguments args = CommandArguments.create(destKey, key).add(start).add(end).add(type)
				.add(rev ? "REV" : null);
		return zRangeStore(destKey, key, new JedisZRangeParams(type, start, end, rev), args);
	}

	@Override
	public Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end,
	                        final ZRangeType type, final boolean rev) {
		final CommandArguments args = CommandArguments.create(destKey, key).add(start).add(end).add(type)
				.add(rev ? "REV" : null);
		return zRangeStore(destKey, key, new JedisZRangeParams(type, start, end, rev), args);
	}

	@Override
	public Long zRangeStore(final String destKey, final String key, final long start, final long end,
	                        final boolean rev) {
		final CommandArguments args = CommandArguments.create(destKey, key).add(start).add(end).add(rev ? "REV" : null);
		return zRangeStore(destKey, key, new JedisZRangeParams(start, end, rev), args);
	}

	@Override
	public Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end,
	                        final boolean rev) {
		final CommandArguments args = CommandArguments.create(destKey, key).add(start).add(end).add(rev ? "REV" : null);
		return zRangeStore(destKey, key, new JedisZRangeParams(start, end, rev), args);
	}

	@Override
	public Long zRangeStore(final String destKey, final String key, final long start, final long end, final int offset,
	                        final int count) {
		final CommandArguments args = CommandArguments.create(destKey).add(key).add(start).add(end)
				.add(Keyword.Common.LIMIT).add(offset).add(count);
		return zRangeStore(destKey, key, new JedisZRangeParams(start, end, offset, count), args);
	}

	@Override
	public Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end, final int offset,
	                        final int count) {
		final CommandArguments args = CommandArguments.create(destKey).add(key).add(start).add(end)
				.add(Keyword.Common.LIMIT).add(offset).add(count);
		return zRangeStore(destKey, key, new JedisZRangeParams(start, end, offset, count), args);
	}

	@Override
	public Long zRangeStore(final String destKey, final String key, final long start, final long end,
	                        final ZRangeType type, final int offset, final int count) {
		final CommandArguments args = CommandArguments.create(destKey).add(key).add(start).add(end).add(type)
				.add(Keyword.Common.LIMIT).add(offset).add(count);
		return zRangeStore(destKey, key, new JedisZRangeParams(type, start, end, offset, count), args);
	}

	@Override
	public Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end,
	                        final ZRangeType type, final int offset, final int count) {
		final CommandArguments args = CommandArguments.create(destKey).add(key).add(start).add(end).add(type)
				.add(Keyword.Common.LIMIT).add(offset).add(count);
		return zRangeStore(destKey, key, new JedisZRangeParams(type, start, end, offset, count), args);
	}

	@Override
	public Long zRangeStore(final String destKey, final String key, final long start, final long end,
	                        final ZRangeType type, final boolean rev, final int offset, final int count) {
		final CommandArguments args = CommandArguments.create(destKey).add(key).add(start).add(end).add(type)
				.add(rev ? "REV" : null).add(Keyword.Common.LIMIT).add(offset).add(count);
		return zRangeStore(destKey, key, new JedisZRangeParams(type, start, end, rev, offset, count), args);
	}

	@Override
	public Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end,
	                        final ZRangeType type, final boolean rev, final int offset, final int count) {
		final CommandArguments args = CommandArguments.create(destKey).add(key).add(start).add(end).add(type)
				.add(rev ? "REV" : null).add(Keyword.Common.LIMIT).add(offset).add(count);
		return zRangeStore(destKey, key, new JedisZRangeParams(type, start, end, rev, offset, count), args);
	}

	@Override
	public Long zRangeStore(final String destKey, final String key, final long start, final long end, final boolean rev,
	                        final int offset, final int count) {
		final CommandArguments args = CommandArguments.create(destKey).add(key).add(start).add(end)
				.add(rev ? "REV" : null)
				.add(Keyword.Common.LIMIT).add(offset).add(count);
		return zRangeStore(destKey, key, new JedisZRangeParams(start, end, rev, offset, count), args);
	}

	@Override
	public Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end, final boolean rev,
	                        final int offset, final int count) {
		final CommandArguments args = CommandArguments.create(destKey).add(key).add(start).add(end)
				.add(rev ? "REV" : null)
				.add(Keyword.Common.LIMIT).add(offset).add(count);
		return zRangeStore(destKey, key, new JedisZRangeParams(start, end, rev, offset, count), args);
	}

	@Override
	public Long zRank(final String key, final String member) {
		final CommandArguments args = CommandArguments.create(key, member);
		return executeCommand(RedisCommand.ZRANK, args, (cmd)->cmd.zrank(key, member),
				(cmd)->cmd.zrank(key, member), (cmd)->cmd.zrank(key, member));
	}

	@Override
	public Long zRank(final byte[] key, final byte[] member) {
		final CommandArguments args = CommandArguments.create(key, member);
		return executeCommand(RedisCommand.ZRANK, args, (cmd)->cmd.zrank(key, member),
				(cmd)->cmd.zrank(key, member), (cmd)->cmd.zrank(key, member));
	}

	@Override
	public KeyValue<Long, Double> zRankWithScores(final String key, final String member) {
		final CommandArguments args = CommandArguments.create(key, member).add(Keyword.Common.WITHSCORE);
		return executeCommand(RedisCommand.ZRANK, args, (cmd)->cmd.zrankWithScore(key, member),
				(cmd)->cmd.zrankWithScore(key, member), (cmd)->cmd.zrankWithScore(key, member),
				new KeyValueConverter<>((k)->k, (v)->v));
	}

	@Override
	public KeyValue<Long, Double> zRankWithScores(final byte[] key, final byte[] member) {
		final CommandArguments args = CommandArguments.create(key, member).add(Keyword.Common.WITHSCORE);
		return executeCommand(RedisCommand.ZRANK, args, (cmd)->cmd.zrankWithScore(key, member),
				(cmd)->cmd.zrankWithScore(key, member), (cmd)->cmd.zrankWithScore(key, member),
				new KeyValueConverter<>((k)->k, (v)->v));
	}

	@Override
	public Long zRem(final String key, final String... members) {
		final CommandArguments args = CommandArguments.create(key).add(members);
		return executeCommand(RedisCommand.ZREM, args, (cmd)->cmd.zrem(key, members),
				(cmd)->cmd.zrem(key, members), (cmd)->cmd.zrem(key, members));
	}

	@Override
	public Long zRem(final byte[] key, final byte[]... members) {
		final CommandArguments args = CommandArguments.create(key).add(members);
		return executeCommand(RedisCommand.ZREM, args, (cmd)->cmd.zrem(key, members),
				(cmd)->cmd.zrem(key, members), (cmd)->cmd.zrem(key, members));
	}

	@Override
	public Long zRemRangeByLex(final String key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);
		return executeCommand(RedisCommand.ZREMRANGEBYLEX, args,
				(cmd)->cmd.zremrangeByLex(key, Double.toString(min), Double.toString(max)),
				(cmd)->cmd.zremrangeByLex(key, Double.toString(min), Double.toString(max)),
				(cmd)->cmd.zremrangeByLex(key, Double.toString(min), Double.toString(max)));
	}

	@Override
	public Long zRemRangeByLex(final byte[] key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);
		return executeCommand(RedisCommand.ZREMRANGEBYLEX, args,
				(cmd)->cmd.zremrangeByLex(key, NumberUtils.double2bytes(min), NumberUtils.double2bytes(max)),
				(cmd)->cmd.zremrangeByLex(key, NumberUtils.double2bytes(min), NumberUtils.double2bytes(max)),
				(cmd)->cmd.zremrangeByLex(key, NumberUtils.double2bytes(min), NumberUtils.double2bytes(max)));
	}

	@Override
	public Long zRemRangeByRank(final String key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end);
		return executeCommand(RedisCommand.ZREMRANGEBYRANK, args, (cmd)->cmd.zremrangeByRank(key, start, end),
				(cmd)->cmd.zremrangeByRank(key, start, end), (cmd)->cmd.zremrangeByRank(key, start, end));
	}

	@Override
	public Long zRemRangeByRank(final byte[] key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end);
		return executeCommand(RedisCommand.ZREMRANGEBYRANK, args, (cmd)->cmd.zremrangeByRank(key, start, end),
				(cmd)->cmd.zremrangeByRank(key, start, end), (cmd)->cmd.zremrangeByRank(key, start, end));
	}

	@Override
	public Long zRemRangeByScore(final String key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);
		return executeCommand(RedisCommand.ZREMRANGEBYSCORE, args,
				(cmd)->cmd.zremrangeByScore(key, Double.toString(min), Double.toString(max)),
				(cmd)->cmd.zremrangeByScore(key, Double.toString(min), Double.toString(max)),
				(cmd)->cmd.zremrangeByScore(key, Double.toString(min), Double.toString(max)));
	}

	@Override
	public Long zRemRangeByScore(final byte[] key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);
		return executeCommand(RedisCommand.ZREMRANGEBYSCORE, args,
				(cmd)->cmd.zremrangeByScore(key, NumberUtils.double2bytes(min), NumberUtils.double2bytes(max)),
				(cmd)->cmd.zremrangeByScore(key, NumberUtils.double2bytes(min), NumberUtils.double2bytes(max)),
				(cmd)->cmd.zremrangeByScore(key, NumberUtils.double2bytes(min), NumberUtils.double2bytes(max)));
	}

	@Override
	public List<String> zRevRange(final String key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end);
		return executeCommand(RedisCommand.ZREVRANGE, args, (cmd)->cmd.zrevrange(key, start, end),
				(cmd)->cmd.zrevrange(key, start, end), (cmd)->cmd.zrevrange(key, start, end));
	}

	@Override
	public List<byte[]> zRevRange(final byte[] key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end);
		return executeCommand(RedisCommand.ZREVRANGE, args, (cmd)->cmd.zrevrange(key, start, end),
				(cmd)->cmd.zrevrange(key, start, end), (cmd)->cmd.zrevrange(key, start, end));
	}

	@Override
	public List<Tuple> zRevRangeWithScores(final String key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end);
		return executeCommand(RedisCommand.ZREVRANGE, args, (cmd)->cmd.zrevrangeWithScores(key, start, end),
				(cmd)->cmd.zrevrangeWithScores(key, start, end), (cmd)->cmd.zrevrangeWithScores(key, start, end),
				new ListConverter<>(new TupleConverter()));
	}

	@Override
	public List<Tuple> zRevRangeWithScores(final byte[] key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end);
		return executeCommand(RedisCommand.ZREVRANGE, args, (cmd)->cmd.zrevrangeWithScores(key, start, end),
				(cmd)->cmd.zrevrangeWithScores(key, start, end), (cmd)->cmd.zrevrangeWithScores(key, start, end),
				new ListConverter<>(new TupleConverter()));
	}

	@Override
	public List<String> zRevRangeByLex(final String key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);
		return executeCommand(RedisCommand.ZREVRANGEBYLEX, args,
				(cmd)->cmd.zrevrangeByLex(key, Double.toString(min), Double.toString(max)),
				(cmd)->cmd.zrevrangeByLex(key, Double.toString(min), Double.toString(max)),
				(cmd)->cmd.zrevrangeByLex(key, Double.toString(min), Double.toString(max)));
	}

	@Override
	public List<byte[]> zRevRangeByLex(final byte[] key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);
		return executeCommand(RedisCommand.ZREVRANGEBYLEX, args,
				(cmd)->cmd.zrevrangeByLex(key, NumberUtils.double2bytes(min), NumberUtils.double2bytes(max)),
				(cmd)->cmd.zrevrangeByLex(key, NumberUtils.double2bytes(min), NumberUtils.double2bytes(max)),
				(cmd)->cmd.zrevrangeByLex(key, NumberUtils.double2bytes(min), NumberUtils.double2bytes(max)));
	}

	@Override
	public List<String> zRevRangeByLex(final String key, final double min, final double max, final int offset,
	                                   final int count) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max).add(Keyword.Common.LIMIT)
				.add(offset).add(count);
		return executeCommand(RedisCommand.ZREVRANGEBYLEX, args,
				(cmd)->cmd.zrevrangeByLex(key, Double.toString(min), Double.toString(max), offset, count),
				(cmd)->cmd.zrevrangeByLex(key, Double.toString(min), Double.toString(max), offset, count),
				(cmd)->cmd.zrevrangeByLex(key, Double.toString(min), Double.toString(max), offset, count));
	}

	@Override
	public List<byte[]> zRevRangeByLex(final byte[] key, final double min, final double max, final int offset,
	                                   final int count) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max).add(Keyword.Common.LIMIT)
				.add(offset).add(count);
		return executeCommand(RedisCommand.ZREVRANGEBYLEX, args,
				(cmd)->cmd.zrevrangeByLex(key, NumberUtils.double2bytes(min), NumberUtils.double2bytes(max),
						offset, count),
				(cmd)->cmd.zrevrangeByLex(key, NumberUtils.double2bytes(min), NumberUtils.double2bytes(max),
						offset, count),
				(cmd)->cmd.zrevrangeByLex(key, NumberUtils.double2bytes(min), NumberUtils.double2bytes(max),
						offset, count));
	}

	@Override
	public List<String> zRevRangeByScore(final String key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);
		return executeCommand(RedisCommand.ZREVRANGEBYSCORE, args,
				(cmd)->cmd.zrevrangeByScore(key, Double.toString(min), Double.toString(max)),
				(cmd)->cmd.zrevrangeByScore(key, Double.toString(min), Double.toString(max)),
				(cmd)->cmd.zrevrangeByScore(key, Double.toString(min), Double.toString(max)));
	}

	@Override
	public List<byte[]> zRevRangeByScore(final byte[] key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);
		return executeCommand(RedisCommand.ZREVRANGEBYSCORE, args,
				(cmd)->cmd.zrevrangeByLex(key, NumberUtils.double2bytes(min), NumberUtils.double2bytes(max)),
				(cmd)->cmd.zrevrangeByLex(key, NumberUtils.double2bytes(min), NumberUtils.double2bytes(max)),
				(cmd)->cmd.zrevrangeByLex(key, NumberUtils.double2bytes(min), NumberUtils.double2bytes(max)));
	}

	@Override
	public List<String> zRevRangeByScore(final String key, final double min, final double max, final int offset,
	                                     final int count) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max).add(Keyword.Common.LIMIT)
				.add(offset).add(count);
		return executeCommand(RedisCommand.ZREVRANGEBYSCORE, args,
				(cmd)->cmd.zrevrangeByScore(key, Double.toString(min), Double.toString(max), offset, count),
				(cmd)->cmd.zrevrangeByScore(key, Double.toString(min), Double.toString(max), offset, count),
				(cmd)->cmd.zrevrangeByScore(key, Double.toString(min), Double.toString(max), offset, count));
	}

	@Override
	public List<byte[]> zRevRangeByScore(final byte[] key, final double min, final double max, final int offset,
	                                     final int count) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max).add(Keyword.Common.LIMIT)
				.add(offset).add(count);
		return executeCommand(RedisCommand.ZREVRANGEBYSCORE, args,
				(cmd)->cmd.zrevrangeByLex(key, NumberUtils.double2bytes(min), NumberUtils.double2bytes(max),
						offset, count),
				(cmd)->cmd.zrevrangeByLex(key, NumberUtils.double2bytes(min), NumberUtils.double2bytes(max),
						offset, count),
				(cmd)->cmd.zrevrangeByLex(key, NumberUtils.double2bytes(min), NumberUtils.double2bytes(max),
						offset, count));
	}

	@Override
	public List<Tuple> zRevRangeByScoreWithScores(final String key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);
		return executeCommand(RedisCommand.ZREVRANGEBYSCORE, args,
				(cmd)->cmd.zrangeByScoreWithScores(key, Double.toString(min), Double.toString(max)),
				(cmd)->cmd.zrangeByScoreWithScores(key, Double.toString(min), Double.toString(max)),
				(cmd)->cmd.zrangeByScoreWithScores(key, Double.toString(min), Double.toString(max)),
				new ListConverter<>(new TupleConverter()));
	}

	@Override
	public List<Tuple> zRevRangeByScoreWithScores(final byte[] key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);
		return executeCommand(RedisCommand.ZREVRANGEBYSCORE, args,
				(cmd)->cmd.zrangeByScoreWithScores(key, min, max), (cmd)->cmd.zrangeByScoreWithScores(key, min, max),
				(cmd)->cmd.zrangeByScoreWithScores(key, min, max), new ListConverter<>(new TupleConverter()));
	}

	@Override
	public List<Tuple> zRevRangeByScoreWithScores(final String key, final double min, final double max,
	                                              final int offset, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max).add(Keyword.Common.LIMIT)
				.add(offset).add(count);
		return executeCommand(RedisCommand.ZREVRANGEBYSCORE, args,
				(cmd)->cmd.zrangeByScoreWithScores(key, Double.toString(min), Double.toString(max), offset,
						count),
				(cmd)->cmd.zrangeByScoreWithScores(key, Double.toString(min), Double.toString(max), offset,
						count),
				(cmd)->cmd.zrangeByScoreWithScores(key, Double.toString(min), Double.toString(max), offset,
						count), new ListConverter<>(new TupleConverter()));
	}

	@Override
	public List<Tuple> zRevRangeByScoreWithScores(final byte[] key, final double min, final double max,
	                                              final int offset, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max).add(Keyword.Common.LIMIT)
				.add(offset).add(count);
		return executeCommand(RedisCommand.ZREVRANGEBYSCORE, args,
				(cmd)->cmd.zrangeByScoreWithScores(key, min, max, offset, count),
				(cmd)->cmd.zrangeByScoreWithScores(key, min, max, offset, count),
				(cmd)->cmd.zrangeByScoreWithScores(key, min, max, offset, count),
				new ListConverter<>(new TupleConverter()));
	}

	@Override
	public Long zRevRank(final String key, final String member) {
		final CommandArguments args = CommandArguments.create(key, member);
		return executeCommand(RedisCommand.ZREVRANK, args, (cmd)->cmd.zrevrank(key, member),
				(cmd)->cmd.zrevrank(key, member), (cmd)->cmd.zrevrank(key, member));
	}

	@Override
	public Long zRevRank(final byte[] key, final byte[] member) {
		final CommandArguments args = CommandArguments.create(key).add(member);
		return executeCommand(RedisCommand.ZREVRANK, args, (cmd)->cmd.zrevrank(key, member),
				(cmd)->cmd.zrevrank(key, member), (cmd)->cmd.zrevrank(key, member));
	}

	@Override
	public KeyValue<Long, Double> zRevRankWithScore(final String key, final String member) {
		final CommandArguments args = CommandArguments.create(key, member).add(Keyword.Common.WITHSCORE);
		return executeCommand(RedisCommand.ZREVRANK, args, (cmd)->cmd.zrankWithScore(key, member),
				(cmd)->cmd.zrankWithScore(key, member), (cmd)->cmd.zrankWithScore(key, member),
				new KeyValueConverter<>((k)->k, (v)->v));
	}

	@Override
	public KeyValue<Long, Double> zRevRankWithScore(final byte[] key, final byte[] member) {
		final CommandArguments args = CommandArguments.create(key, member).add(Keyword.Common.WITHSCORE);
		return executeCommand(RedisCommand.ZREVRANK, args, (cmd)->cmd.zrankWithScore(key, member),
				(cmd)->cmd.zrankWithScore(key, member), (cmd)->cmd.zrankWithScore(key, member),
				new KeyValueConverter<>((k)->k, (v)->v));
	}

	@Override
	public ScanResult<Tuple> zScan(final String key, final String cursor) {
		final CommandArguments args = CommandArguments.create(key).add(cursor);
		return executeCommand(RedisCommand.ZSCAN, args, (cmd)->cmd.zscan(key, cursor),
				(cmd)->cmd.zscan(key, cursor), (cmd)->cmd.zscan(key, cursor),
				new ScanResultConverter<>(new TupleConverter()));
	}

	@Override
	public ScanResult<Tuple> zScan(final byte[] key, final byte[] cursor) {
		final CommandArguments args = CommandArguments.create(key).add(cursor);
		return executeCommand(RedisCommand.ZSCAN, args, (cmd)->cmd.zscan(key, cursor),
				(cmd)->cmd.zscan(key, cursor), (cmd)->cmd.zscan(key, cursor),
				new ScanResultConverter<>(new TupleConverter()));
	}

	@Override
	public ScanResult<Tuple> zScan(final String key, final String cursor, final String pattern) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(Keyword.Scan.MATCH).add(pattern);
		return zScan(key, cursor, new JedisScanParams(pattern), args);
	}

	@Override
	public ScanResult<Tuple> zScan(final byte[] key, final byte[] cursor, final byte[] pattern) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(Keyword.Scan.MATCH).add(pattern);
		return zScan(key, cursor, new JedisScanParams(pattern), args);
	}

	@Override
	public ScanResult<Tuple> zScan(final String key, final String cursor, final String pattern, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(Keyword.Scan.MATCH).add(pattern)
				.add(Keyword.Common.COUNT).add(count);
		return zScan(key, cursor, new JedisScanParams(pattern, count), args);
	}

	@Override
	public ScanResult<Tuple> zScan(final byte[] key, final byte[] cursor, final byte[] pattern, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(Keyword.Scan.MATCH).add(pattern)
				.add(Keyword.Common.COUNT).add(count);
		return zScan(key, cursor, new JedisScanParams(pattern, count), args);
	}

	@Override
	public ScanResult<Tuple> zScan(final String key, final String cursor, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(Keyword.Common.COUNT).add(count);
		return zScan(key, cursor, new JedisScanParams(count), args);
	}

	@Override
	public ScanResult<Tuple> zScan(final byte[] key, final byte[] cursor, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(Keyword.Common.COUNT).add(count);
		return zScan(key, cursor, new JedisScanParams(count), args);
	}

	@Override
	public Double zScore(final String key, final String member) {
		final CommandArguments args = CommandArguments.create(key).add(member);
		return executeCommand(RedisCommand.ZSCORE, args, (cmd)->cmd.zscore(key, member),
				(cmd)->cmd.zscore(key, member), (cmd)->cmd.zscore(key, member));
	}

	@Override
	public Double zScore(final byte[] key, final byte[] member) {
		final CommandArguments args = CommandArguments.create(key).add(member);
		return executeCommand(RedisCommand.ZSCORE, args, (cmd)->cmd.zscore(key, member),
				(cmd)->cmd.zscore(key, member), (cmd)->cmd.zscore(key, member));
	}

	@Override
	public List<String> zUnion(final String... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return zUnion(keys, new JedisZParams(), args);
	}

	@Override
	public List<byte[]> zUnion(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return zUnion(keys, new JedisZParams(), args);
	}

	@Override
	public List<String> zUnion(final String[] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create(keys).add("AGGREGATE").add(aggregate);
		return zUnion(keys, new JedisZParams(aggregate), args);
	}

	@Override
	public List<byte[]> zUnion(final byte[][] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create(keys).add("AGGREGATE").add(aggregate);
		return zUnion(keys, new JedisZParams(aggregate), args);
	}

	@Override
	public List<String> zUnion(final String[] keys, final Aggregate aggregate, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add("AGGREGATE").add(aggregate).add("WEIGHTS")
				.add(weights);
		return zUnion(keys, new JedisZParams(aggregate, weights), args);
	}

	@Override
	public List<byte[]> zUnion(final byte[][] keys, final Aggregate aggregate, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add("AGGREGATE").add(aggregate).add("WEIGHTS")
				.add(weights);
		return zUnion(keys, new JedisZParams(aggregate, weights), args);
	}

	@Override
	public List<String> zUnion(final String[] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add("WEIGHTS").add(weights);
		return zUnion(keys, new JedisZParams(weights), args);
	}

	@Override
	public List<byte[]> zUnion(final byte[][] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add("WEIGHTS").add(weights);
		return zUnion(keys, new JedisZParams(weights), args);
	}

	@Override
	public List<Tuple> zUnionWithScores(final String... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return zUnionWithScores(keys, new JedisZParams(), args);
	}

	@Override
	public List<Tuple> zUnionWithScores(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return zUnionWithScores(keys, new JedisZParams(), args);
	}

	@Override
	public List<Tuple> zUnionWithScores(final String[] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create(keys).add("AGGREGATE").add(aggregate);
		return zUnionWithScores(keys, new JedisZParams(aggregate), args);
	}

	@Override
	public List<Tuple> zUnionWithScores(final byte[][] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create(keys).add("AGGREGATE").add(aggregate);
		return zUnionWithScores(keys, new JedisZParams(aggregate), args);
	}

	@Override
	public List<Tuple> zUnionWithScores(final String[] keys, final Aggregate aggregate, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add("AGGREGATE").add(aggregate).add("WEIGHTS")
				.add(weights);
		return zUnionWithScores(keys, new JedisZParams(aggregate, weights), args);
	}

	@Override
	public List<Tuple> zUnionWithScores(final byte[][] keys, final Aggregate aggregate, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add("AGGREGATE").add(aggregate).add("WEIGHTS")
				.add(weights);
		return zUnionWithScores(keys, new JedisZParams(aggregate, weights), args);
	}

	@Override
	public List<Tuple> zUnionWithScores(final String[] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add("WEIGHTS").add(weights);
		return zUnionWithScores(keys, new JedisZParams(weights), args);
	}

	@Override
	public List<Tuple> zUnionWithScores(final byte[][] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add("WEIGHTS").add(weights);
		return zUnionWithScores(keys, new JedisZParams(weights), args);
	}

	@Override
	public Long zUnionStore(final String destKey, final String... keys) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys);
		return executeCommand(RedisCommand.ZUNIONSTORE, args, (cmd)->cmd.zunionstore(destKey, keys),
				(cmd)->cmd.zunionstore(destKey, keys), (cmd)->cmd.zunionstore(destKey, keys));
	}

	@Override
	public Long zUnionStore(final byte[] destKey, final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys);
		return executeCommand(RedisCommand.ZUNIONSTORE, args, (cmd)->cmd.zunionstore(destKey, keys),
				(cmd)->cmd.zunionstore(destKey, keys), (cmd)->cmd.zunionstore(destKey, keys));
	}

	@Override
	public Long zUnionStore(final String destKey, final String[] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys).add("AGGREGATE").add(aggregate);
		return zUnionStore(destKey, keys, new JedisZParams(aggregate), args);
	}

	@Override
	public Long zUnionStore(final byte[] destKey, final byte[][] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys).add("AGGREGATE").add(aggregate);
		return zUnionStore(destKey, keys, new JedisZParams(aggregate), args);
	}

	@Override
	public Long zUnionStore(final String destKey, final String[] keys, final Aggregate aggregate,
	                        final double... weights) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys).add("AGGREGATE").add(aggregate)
				.add("WEIGHTS").add(weights);
		return zUnionStore(destKey, keys, new JedisZParams(aggregate, weights), args);
	}

	@Override
	public Long zUnionStore(final byte[] destKey, final byte[][] keys, final Aggregate aggregate,
	                        final double... weights) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys).add("AGGREGATE").add(aggregate)
				.add("WEIGHTS").add(weights);
		return zUnionStore(destKey, keys, new JedisZParams(aggregate, weights), args);
	}

	@Override
	public Long zUnionStore(final String destKey, final String[] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys).add("WEIGHTS").add(weights);
		return zUnionStore(destKey, keys, new JedisZParams(weights), args);
	}

	@Override
	public Long zUnionStore(final byte[] destKey, final byte[][] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys).add("WEIGHTS").add(weights);
		return zUnionStore(destKey, keys, new JedisZParams(weights), args);
	}

	private List<String> zInter(final String[] keys, final JedisZParams zParams, final CommandArguments args) {
		return executeCommand(RedisCommand.ZINTER, args, (cmd)->cmd.zinter(zParams, keys),
				(cmd)->cmd.zinter(zParams, keys), (cmd)->cmd.zinter(zParams, keys));
	}

	private List<byte[]> zInter(final byte[][] keys, final JedisZParams zParams, final CommandArguments args) {
		return executeCommand(RedisCommand.ZINTER, args, (cmd)->cmd.zinter(zParams, keys),
				(cmd)->cmd.zinter(zParams, keys), (cmd)->cmd.zinter(zParams, keys));
	}

	private List<Tuple> zInterWithScores(final String[] keys, final JedisZParams zParams, final CommandArguments args) {
		return executeCommand(RedisCommand.ZINTER, args, (cmd)->cmd.zinterWithScores(zParams, keys),
				(cmd)->cmd.zinterWithScores(zParams, keys), (cmd)->cmd.zinterWithScores(zParams, keys),
				new ListConverter<>(new TupleConverter()));
	}

	private List<Tuple> zInterWithScores(final byte[][] keys, final JedisZParams zParams, final CommandArguments args) {
		return executeCommand(RedisCommand.ZINTER, args, (cmd)->cmd.zinterWithScores(zParams, keys),
				(cmd)->cmd.zinterWithScores(zParams, keys), (cmd)->cmd.zinterWithScores(zParams, keys),
				new ListConverter<>(new TupleConverter()));
	}

	private Long zInterStore(final String destKey, final String[] keys, final JedisZParams zParams,
	                         final CommandArguments args) {
		return executeCommand(RedisCommand.ZINTERSTORE, args, (cmd)->cmd.zinterstore(destKey, zParams, keys),
				(cmd)->cmd.zinterstore(destKey, zParams, keys), (cmd)->cmd.zinterstore(destKey, zParams, keys));
	}

	private Long zInterStore(final byte[] destKey, final byte[][] keys, final JedisZParams zParams,
	                         final CommandArguments args) {
		return executeCommand(RedisCommand.ZINTERSTORE, args, (cmd)->cmd.zinterstore(destKey, zParams, keys),
				(cmd)->cmd.zinterstore(destKey, zParams, keys), (cmd)->cmd.zinterstore(destKey, zParams, keys));
	}

	private List<String> zRange(final String key, final JedisZRangeParams zRangeParams, final CommandArguments args) {
		return executeCommand(RedisCommand.ZRANGE, args, (cmd)->cmd.zrange(key, zRangeParams),
				(cmd)->cmd.zrange(key, zRangeParams), (cmd)->cmd.zrange(key, zRangeParams));
	}

	private List<byte[]> zRange(final byte[] key, final JedisZRangeParams zRangeParams, final CommandArguments args) {
		return executeCommand(RedisCommand.ZRANGE, args, (cmd)->cmd.zrange(key, zRangeParams),
				(cmd)->cmd.zrange(key, zRangeParams), (cmd)->cmd.zrange(key, zRangeParams));
	}

	private List<Tuple> zRangeWithScores(final String key, final JedisZRangeParams zRangeParams,
	                                     final CommandArguments args) {
		return executeCommand(RedisCommand.ZRANGE, args, (cmd)->cmd.zrangeWithScores(key, zRangeParams),
				(cmd)->cmd.zrangeWithScores(key, zRangeParams), (cmd)->cmd.zrangeWithScores(key, zRangeParams),
				new ListConverter<>(new TupleConverter()));
	}

	private List<Tuple> zRangeWithScores(final byte[] key, final JedisZRangeParams zRangeParams,
	                                     final CommandArguments args) {
		return executeCommand(RedisCommand.ZRANGE, args, (cmd)->cmd.zrangeWithScores(key, zRangeParams),
				(cmd)->cmd.zrangeWithScores(key, zRangeParams), (cmd)->cmd.zrangeWithScores(key, zRangeParams),
				new ListConverter<>(new TupleConverter()));
	}

	private Long zRangeStore(final String destKey, final String key, final JedisZRangeParams zRangeParams,
	                         final CommandArguments args) {
		return executeCommand(RedisCommand.ZRANGESTORE, args, (cmd)->cmd.zrangestore(destKey, key, zRangeParams),
				(cmd)->cmd.zrangestore(destKey, key, zRangeParams), (cmd)->cmd.zrangestore(destKey, key, zRangeParams));
	}

	private Long zRangeStore(final byte[] destKey, final byte[] key, final JedisZRangeParams zRangeParams,
	                         final CommandArguments args) {
		return executeCommand(RedisCommand.ZRANGESTORE, args, (cmd)->cmd.zrangestore(destKey, key, zRangeParams),
				(cmd)->cmd.zrangestore(destKey, key, zRangeParams), (cmd)->cmd.zrangestore(destKey, key, zRangeParams));
	}

	private ScanResult<Tuple> zScan(final String key, final String cursor, final JedisScanParams scanParams,
	                                final CommandArguments args) {
		return executeCommand(RedisCommand.ZSCAN, args, (cmd)->cmd.zscan(key, cursor, scanParams),
				(cmd)->cmd.zscan(key, cursor, scanParams), (cmd)->cmd.zscan(key, cursor, scanParams),
				new ScanResultConverter<>(new TupleConverter()));
	}

	private ScanResult<Tuple> zScan(final byte[] key, final byte[] cursor, final JedisScanParams scanParams,
	                                final CommandArguments args) {
		return executeCommand(RedisCommand.ZSCAN, args, (cmd)->cmd.zscan(key, cursor, scanParams),
				(cmd)->cmd.zscan(key, cursor, scanParams), (cmd)->cmd.zscan(key, cursor, scanParams),
				new ScanResultConverter<>(new TupleConverter()));
	}

	private List<String> zUnion(final String[] keys, final JedisZParams zParams, final CommandArguments args) {
		return executeCommand(RedisCommand.ZUNION, args, (cmd)->cmd.zunion(zParams, keys),
				(cmd)->cmd.zunion(zParams, keys), (cmd)->cmd.zunion(zParams, keys));
	}

	private List<byte[]> zUnion(final byte[][] keys, final JedisZParams zParams, final CommandArguments args) {
		return executeCommand(RedisCommand.ZUNION, args, (cmd)->cmd.zunion(zParams, keys),
				(cmd)->cmd.zunion(zParams, keys), (cmd)->cmd.zunion(zParams, keys));
	}

	private List<Tuple> zUnionWithScores(final String[] keys, final JedisZParams zParams, final CommandArguments args) {
		return executeCommand(RedisCommand.ZUNION, args, (cmd)->cmd.zunionWithScores(zParams, keys),
				(cmd)->cmd.zunionWithScores(zParams, keys), (cmd)->cmd.zunionWithScores(zParams, keys),
				new ListConverter<>(new TupleConverter()));
	}

	private List<Tuple> zUnionWithScores(final byte[][] keys, final JedisZParams zParams, final CommandArguments args) {
		return executeCommand(RedisCommand.ZUNION, args, (cmd)->cmd.zunionWithScores(zParams, keys),
				(cmd)->cmd.zunionWithScores(zParams, keys), (cmd)->cmd.zunionWithScores(zParams, keys),
				new ListConverter<>(new TupleConverter()));
	}

	private Long zUnionStore(final String dstkey, final String[] keys, final JedisZParams zParams,
	                         final CommandArguments args) {
		return executeCommand(RedisCommand.ZUNIONSTORE, args, (cmd)->cmd.zunionstore(dstkey, zParams, keys),
				(cmd)->cmd.zunionstore(dstkey, zParams, keys), (cmd)->cmd.zunionstore(dstkey, zParams, keys));
	}

	private Long zUnionStore(final byte[] dstkey, final byte[][] keys, final JedisZParams zParams,
	                         final CommandArguments args) {
		return executeCommand(RedisCommand.ZUNIONSTORE, args, (cmd)->cmd.zunionstore(dstkey, zParams, keys),
				(cmd)->cmd.zunionstore(dstkey, zParams, keys), (cmd)->cmd.zunionstore(dstkey, zParams, keys));
	}

}
