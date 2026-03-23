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
import com.buession.redis.core.command.Command;
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
		final CommandArguments args = CommandArguments.create(timeout).add(keys.length, keys).add(minMax);
		final MinMaxSortedSetOptionConverter minMaxSortedSetOptionConverter = new MinMaxSortedSetOptionConverter();
		return executeCommand(Command.BZMPOP, args,
				(cmd)->cmd.bzmpop(timeout, minMaxSortedSetOptionConverter.convert(minMax), rawKeys(keys)),
				new KeyValueConverter<>((k)->k, new ListConverter<>(new TupleConverter())));
	}

	@Override
	public KeyValue<byte[], List<Tuple>> bzMPop(final byte[][] keys, final int timeout, final MinMax minMax) {
		final CommandArguments args = CommandArguments.create(timeout).add(keys.length, keys).add(minMax);
		final MinMaxSortedSetOptionConverter minMaxSortedSetOptionConverter = new MinMaxSortedSetOptionConverter();
		return executeCommand(Command.BZMPOP, args,
				(cmd)->cmd.bzmpop(timeout, minMaxSortedSetOptionConverter.convert(minMax), rawKeys(keys)),
				new KeyValueConverter<>((k)->k, new ListConverter<>(new TupleConverter())));
	}

	@Override
	public KeyValue<String, List<Tuple>> bzMPop(final String[] keys, final int timeout, final MinMax minMax,
												final int count) {
		final CommandArguments args = CommandArguments.create(timeout).add(keys.length, keys).add(minMax)
				.add(Keyword.Common.COUNT, count);
		final MinMaxSortedSetOptionConverter minMaxSortedSetOptionConverter = new MinMaxSortedSetOptionConverter();
		return executeCommand(Command.BZMPOP, args,
				(cmd)->cmd.bzmpop(timeout, minMaxSortedSetOptionConverter.convert(minMax), count, rawKeys(keys)),
				new KeyValueConverter<>((k)->k, new ListConverter<>(new TupleConverter())));
	}

	@Override
	public KeyValue<byte[], List<Tuple>> bzMPop(final byte[][] keys, final int timeout, final MinMax minMax,
												final int count) {
		final CommandArguments args = CommandArguments.create(timeout).add(keys.length, keys).add(minMax)
				.add(Keyword.Common.COUNT, count);
		final MinMaxSortedSetOptionConverter minMaxSortedSetOptionConverter = new MinMaxSortedSetOptionConverter();
		return executeCommand(Command.BZMPOP, args,
				(cmd)->cmd.bzmpop(timeout, minMaxSortedSetOptionConverter.convert(minMax), count, rawKeys(keys)),
				new KeyValueConverter<>((k)->k, new ListConverter<>(new TupleConverter())));
	}

	@Override
	public KeyValue<String, Tuple> bzPopMax(final String[] keys, final int timeout) {
		final CommandArguments args = CommandArguments.create(keys).add(timeout);
		return executeCommand(Command.BZPOPMAX, args, (cmd)->cmd.bzpopmax(timeout, rawKeys(keys)),
				new KeyValueConverter<>((k)->k, new TupleConverter()));
	}

	@Override
	public KeyValue<byte[], Tuple> bzPopMax(final byte[][] keys, final int timeout) {
		final CommandArguments args = CommandArguments.create(keys).add(timeout);
		return executeCommand(Command.BZPOPMAX, args, (cmd)->cmd.bzpopmax(timeout, rawKeys(keys)),
				new KeyValueConverter<>((k)->k, new TupleConverter()));
	}

	@Override
	public KeyValue<String, Tuple> bzPopMin(final String[] keys, final int timeout) {
		final CommandArguments args = CommandArguments.create(keys).add(timeout);
		return executeCommand(Command.BZPOPMIN, args, (cmd)->cmd.bzpopmin(timeout, rawKeys(keys)),
				new KeyValueConverter<>((k)->k, new TupleConverter()));
	}

	@Override
	public KeyValue<byte[], Tuple> bzPopMin(final byte[][] keys, final int timeout) {
		final CommandArguments args = CommandArguments.create(keys).add(timeout);
		return executeCommand(Command.BZPOPMIN, args, (cmd)->cmd.bzpopmin(timeout, rawKeys(keys)),
				new KeyValueConverter<>((k)->k, new TupleConverter()));
	}

	@Override
	public Long zAdd(final String key, final Tuple... members) {
		final CommandArguments args = CommandArguments.create(key).add(members);
		final Map<String, Double> membersMap = new LinkedHashMap<>(members.length);

		for(final Tuple element : members){
			membersMap.put(element.getElement(), element.getScore());
		}

		return executeCommand(Command.ZADD, args, (cmd)->cmd.zadd(rawKey(key), membersMap));
	}

	@Override
	public Long zAdd(final byte[] key, final Tuple... members) {
		final CommandArguments args = CommandArguments.create(key).add(members);
		final Map<byte[], Double> membersMap = new LinkedHashMap<>(members.length);

		for(final Tuple element : members){
			membersMap.put(element.getBinaryElement(), element.getScore());
		}

		return executeCommand(Command.ZADD, args, (cmd)->cmd.zadd(rawKey(key), membersMap));
	}

	@Override
	public Long zAdd(final String key, final Tuple[] members, final ZAddArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(argument).add(members);
		final Map<String, Double> membersMap = new LinkedHashMap<>(members.length);

		for(final Tuple element : members){
			membersMap.put(element.getElement(), element.getScore());
		}

		return executeCommand(Command.ZADD, args,
				(cmd)->cmd.zadd(rawKey(key), membersMap, new JedisZAddParams(argument)));
	}

	@Override
	public Long zAdd(final byte[] key, final Tuple[] members, final ZAddArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(argument).add(members);
		final Map<byte[], Double> membersMap = new LinkedHashMap<>(members.length);

		for(final Tuple element : members){
			membersMap.put(element.getBinaryElement(), element.getScore());
		}

		return executeCommand(Command.ZADD, args,
				(cmd)->cmd.zadd(rawKey(key), membersMap, new JedisZAddParams(argument)));
	}

	@Override
	public Long zCard(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.ZCARD, args, (cmd)->cmd.zcard(rawKey(key)));
	}

	@Override
	public Long zCard(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.ZCARD, args, (cmd)->cmd.zcard(rawKey(key)));
	}

	@Override
	public Long zCount(final String key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min, max);
		return executeCommand(Command.ZCOUNT, args, (cmd)->cmd.zcount(rawKey(key), min, max));
	}

	@Override
	public Long zCount(final byte[] key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min, max);
		return executeCommand(Command.ZCOUNT, args, (cmd)->cmd.zcount(rawKey(key), min, max));
	}

	@Override
	public List<String> zDiff(final String... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return executeCommand(Command.ZDIFF, args, (cmd)->cmd.zdiff(rawKeys(keys)));
	}

	@Override
	public List<byte[]> zDiff(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return executeCommand(Command.ZDIFF, args, (cmd)->cmd.zdiff(rawKeys(keys)));
	}

	@Override
	public List<Tuple> zDiffWithScores(final String... keys) {
		final CommandArguments args = CommandArguments.create(keys).add(Keyword.Common.WITHSCORES);
		return executeCommand(Command.ZDIFF, args, (cmd)->cmd.zdiffWithScores(rawKeys(keys)),
				new ListConverter<>(new TupleConverter()));
	}

	@Override
	public List<Tuple> zDiffWithScores(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(keys).add(Keyword.Common.WITHSCORES);
		return executeCommand(Command.ZDIFF, args, (cmd)->cmd.zdiffWithScores(rawKeys(keys)),
				new ListConverter<>(new TupleConverter()));
	}

	@Override
	public Long zDiffStore(final String destKey, final String... keys) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys);
		return executeCommand(Command.ZDIFFSTORE, args, (cmd)->cmd.zdiffStore(rawKey(destKey), rawKeys(keys)));
	}

	@Override
	public Long zDiffStore(final byte[] destKey, final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys);
		return executeCommand(Command.ZDIFFSTORE, args, (cmd)->cmd.zdiffStore(rawKey(destKey), rawKeys(keys)));
	}

	@Override
	public Double zIncrBy(final String key, final double increment, final String member) {
		final CommandArguments args = CommandArguments.create(key).add(increment).add(member);
		return executeCommand(Command.ZINCRBY, args, (cmd)->cmd.zincrby(rawKey(key), increment, member));
	}

	@Override
	public Double zIncrBy(final byte[] key, final double increment, final byte[] member) {
		final CommandArguments args = CommandArguments.create(key).add(increment).add(member);
		return executeCommand(Command.ZINCRBY, args, (cmd)->cmd.zincrby(rawKey(key), increment, member));
	}

	@Override
	public List<String> zInter(final String... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return zInter(rawKeys(keys), new JedisZParams(), args);
	}

	@Override
	public List<byte[]> zInter(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return zInter(rawKeys(keys), new JedisZParams(), args);
	}

	@Override
	public List<String> zInter(final String[] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create(keys).add("AGGREGATE", aggregate);
		return zInter(rawKeys(keys), new JedisZParams(aggregate), args);
	}

	@Override
	public List<byte[]> zInter(final byte[][] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create(keys).add("AGGREGATE", aggregate);
		return zInter(rawKeys(keys), new JedisZParams(aggregate), args);
	}

	@Override
	public List<String> zInter(final String[] keys, final Aggregate aggregate, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add("AGGREGATE", aggregate).add("WEIGHTS", weights);
		return zInter(rawKeys(keys), new JedisZParams(aggregate, weights), args);
	}

	@Override
	public List<byte[]> zInter(final byte[][] keys, final Aggregate aggregate, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add("AGGREGATE", aggregate).add("WEIGHTS", weights);
		return zInter(rawKeys(keys), new JedisZParams(aggregate, weights), args);
	}

	@Override
	public List<String> zInter(final String[] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add("WEIGHTS", weights);
		return zInter(rawKeys(keys), new JedisZParams(weights), args);
	}

	@Override
	public List<byte[]> zInter(final byte[][] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add("WEIGHTS", weights);
		return zInter(rawKeys(keys), new JedisZParams(weights), args);
	}

	@Override
	public List<Tuple> zInterWithScores(final String... keys) {
		final CommandArguments args = CommandArguments.create(keys).add(Keyword.Common.WITHSCORES);
		return zInterWithScores(rawKeys(keys), new JedisZParams(), args);
	}

	@Override
	public List<Tuple> zInterWithScores(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(keys).add(Keyword.Common.WITHSCORES);
		return zInterWithScores(rawKeys(keys), new JedisZParams(), args);
	}

	@Override
	public List<Tuple> zInterWithScores(final String[] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create(keys).add("AGGREGATE", aggregate)
				.add(Keyword.Common.WITHSCORES);
		return zInterWithScores(rawKeys(keys), new JedisZParams(aggregate), args);
	}

	@Override
	public List<Tuple> zInterWithScores(final byte[][] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create(keys).add("AGGREGATE", aggregate)
				.add(Keyword.Common.WITHSCORES);
		return zInterWithScores(rawKeys(keys), new JedisZParams(aggregate), args);
	}

	@Override
	public List<Tuple> zInterWithScores(final String[] keys, final Aggregate aggregate, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add("AGGREGATE", aggregate).add("WEIGHTS", weights)
				.add(Keyword.Common.WITHSCORES);
		return zInterWithScores(rawKeys(keys), new JedisZParams(aggregate), args);
	}

	@Override
	public List<Tuple> zInterWithScores(final byte[][] keys, final Aggregate aggregate, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add("AGGREGATE", aggregate).add("WEIGHTS", weights)
				.add(Keyword.Common.WITHSCORES);
		return zInterWithScores(rawKeys(keys), new JedisZParams(aggregate), args);
	}

	@Override
	public List<Tuple> zInterWithScores(final String[] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add("WEIGHTS", weights)
				.add(Keyword.Common.WITHSCORES);
		return zInterWithScores(rawKeys(keys), new JedisZParams(weights), args);
	}

	@Override
	public List<Tuple> zInterWithScores(final byte[][] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add("WEIGHTS", weights)
				.add(Keyword.Common.WITHSCORES);
		return zInterWithScores(rawKeys(keys), new JedisZParams(weights), args);
	}

	@Override
	public long zInterCard(final String... keys) {
		final CommandArguments args = CommandArguments.create(keys.length).add(keys);
		return executeCommand(Command.ZINTERCARD, args, (cmd)->cmd.zintercard(rawKeys(keys)));
	}

	@Override
	public long zInterCard(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(keys.length).add(keys);
		return executeCommand(Command.ZINTERCARD, args, (cmd)->cmd.zintercard(rawKeys(keys)));
	}

	@Override
	public long zInterCard(final String[] keys, final int limit) {
		final CommandArguments args = CommandArguments.create(keys.length).add(keys).add(Keyword.Common.LIMIT, limit);
		return executeCommand(Command.ZINTERCARD, args, (cmd)->cmd.zintercard(limit, rawKeys(keys)));
	}

	@Override
	public long zInterCard(final byte[][] keys, final int limit) {
		final CommandArguments args = CommandArguments.create(keys.length).add(keys).add(Keyword.Common.LIMIT, limit);
		return executeCommand(Command.ZINTERCARD, args, (cmd)->cmd.zintercard(limit, rawKeys(keys)));
	}

	@Override
	public Long zInterStore(final String destKey, final String... keys) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys.length).add(keys);
		return executeCommand(Command.ZINTERSTORE, args, (cmd)->cmd.zinterstore(rawKey(destKey), rawKeys(keys)));
	}

	@Override
	public Long zInterStore(final byte[] destKey, final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys.length).add(keys);
		return executeCommand(Command.ZINTERSTORE, args, (cmd)->cmd.zinterstore(rawKey(destKey), rawKeys(keys)));
	}

	@Override
	public Long zInterStore(final String destKey, final String[] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys.length).add(keys)
				.add("AGGREGATE", aggregate);
		return zInterStore(rawKey(destKey), rawKeys(keys), new JedisZParams(aggregate), args);
	}

	@Override
	public Long zInterStore(final byte[] destKey, final byte[][] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys.length).add(keys)
				.add("AGGREGATE", aggregate);
		return zInterStore(rawKey(destKey), rawKeys(keys), new JedisZParams(aggregate), args);
	}

	@Override
	public Long zInterStore(final String destKey, final String[] keys, final Aggregate aggregate,
							final double... weights) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys.length).add(keys)
				.add("AGGREGATE", aggregate).add("WEIGHTS", weights);
		return zInterStore(rawKey(destKey), rawKeys(keys), new JedisZParams(aggregate, weights), args);
	}

	@Override
	public Long zInterStore(final byte[] destKey, final byte[][] keys, final Aggregate aggregate,
							final double... weights) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys.length).add(keys)
				.add("AGGREGATE", aggregate).add("WEIGHTS", weights);
		return zInterStore(rawKey(destKey), rawKeys(keys), new JedisZParams(aggregate, weights), args);
	}

	@Override
	public Long zInterStore(final String destKey, final String[] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys.length).add(keys)
				.add("WEIGHTS", weights);
		return zInterStore(rawKey(destKey), rawKeys(keys), new JedisZParams(weights), args);
	}

	@Override
	public Long zInterStore(final byte[] destKey, final byte[][] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys.length).add(keys)
				.add("WEIGHTS", weights);
		return zInterStore(rawKey(destKey), rawKeys(keys), new JedisZParams(weights), args);
	}

	@Override
	public Long zLexCount(final String key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);
		return executeCommand(Command.ZLEXCOUNT, args,
				(cmd)->cmd.zlexcount(rawKey(key), Double.toString(min), Double.toString(max)));
	}

	@Override
	public Long zLexCount(final byte[] key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);
		return executeCommand(Command.ZLEXCOUNT, args,
				(cmd)->cmd.zlexcount(rawKey(key), NumberUtils.double2bytes(min), NumberUtils.double2bytes(max)));
	}

	@Override
	public KeyValue<String, List<Tuple>> zMPop(final String[] keys, final MinMax minMax) {
		final CommandArguments args = CommandArguments.create(keys.length).add(keys).add(minMax);
		final MinMaxSortedSetOptionConverter minMaxSortedSetOptionConverter = new MinMaxSortedSetOptionConverter();
		return executeCommand(Command.ZMPOP, args,
				(cmd)->cmd.zmpop(minMaxSortedSetOptionConverter.convert(minMax), rawKeys(keys)),
				new KeyValueConverter<>((k)->k, new ListConverter<>(new TupleConverter())));
	}

	@Override
	public KeyValue<byte[], List<Tuple>> zMPop(final byte[][] keys, final MinMax minMax) {
		final CommandArguments args = CommandArguments.create(keys.length).add(keys).add(minMax);
		final MinMaxSortedSetOptionConverter minMaxSortedSetOptionConverter = new MinMaxSortedSetOptionConverter();
		return executeCommand(Command.ZMPOP, args,
				(cmd)->cmd.zmpop(minMaxSortedSetOptionConverter.convert(minMax), rawKeys(keys)),
				new KeyValueConverter<>((k)->k, new ListConverter<>(new TupleConverter())));
	}

	@Override
	public KeyValue<String, List<Tuple>> zMPop(final String[] keys, final MinMax minMax, final int count) {
		final CommandArguments args = CommandArguments.create(keys.length).add(keys).add(minMax).add("COUNT", count);
		final MinMaxSortedSetOptionConverter minMaxSortedSetOptionConverter = new MinMaxSortedSetOptionConverter();
		return executeCommand(Command.ZMPOP, args,
				(cmd)->cmd.zmpop(minMaxSortedSetOptionConverter.convert(minMax), count, rawKeys(keys)),
				new KeyValueConverter<>((k)->k, new ListConverter<>(new TupleConverter())));
	}

	@Override
	public KeyValue<byte[], List<Tuple>> zMPop(final byte[][] keys, final MinMax minMax, final int count) {
		final CommandArguments args = CommandArguments.create(keys.length).add(keys).add(minMax).add("COUNT", count);
		final MinMaxSortedSetOptionConverter minMaxSortedSetOptionConverter = new MinMaxSortedSetOptionConverter();
		return executeCommand(Command.ZMPOP, args,
				(cmd)->cmd.zmpop(minMaxSortedSetOptionConverter.convert(minMax), count, rawKeys(keys)),
				new KeyValueConverter<>((k)->k, new ListConverter<>(new TupleConverter())));
	}

	@Override
	public List<Double> zMScore(final String key, final String... members) {
		final CommandArguments args = CommandArguments.create(key).add(members);
		return executeCommand(Command.ZMSCORE, args, (cmd)->cmd.zmscore(rawKey(key), members));
	}

	@Override
	public List<Double> zMScore(final byte[] key, final byte[]... members) {
		final CommandArguments args = CommandArguments.create(key).add(members);
		return executeCommand(Command.ZMSCORE, args, (cmd)->cmd.zmscore(rawKey(key), members));
	}

	@Override
	public Tuple zPopMax(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.ZPOPMAX, args, (cmd)->cmd.zpopmax(rawKey(key)), new TupleConverter());
	}

	@Override
	public Tuple zPopMax(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.ZPOPMAX, args, (cmd)->cmd.zpopmax(rawKey(key)), new TupleConverter());
	}

	@Override
	public List<Tuple> zPopMax(final String key, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(count);
		return executeCommand(Command.ZPOPMAX, args, (cmd)->cmd.zpopmax(rawKey(key), count),
				new ListConverter<>(new TupleConverter()));
	}

	@Override
	public List<Tuple> zPopMax(final byte[] key, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(count);
		return executeCommand(Command.ZPOPMAX, args, (cmd)->cmd.zpopmax(rawKey(key), count),
				new ListConverter<>(new TupleConverter()));
	}

	@Override
	public Tuple zPopMin(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.ZPOPMIN, args, (cmd)->cmd.zpopmin(rawKey(key)), new TupleConverter());
	}

	@Override
	public Tuple zPopMin(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.ZPOPMIN, args, (cmd)->cmd.zpopmin(rawKey(key)), new TupleConverter());
	}

	@Override
	public List<Tuple> zPopMin(final String key, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(count);
		return executeCommand(Command.ZPOPMIN, args, (cmd)->cmd.zpopmin(rawKey(key), count),
				new ListConverter<>(new TupleConverter()));
	}

	@Override
	public List<Tuple> zPopMin(final byte[] key, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(count);
		return executeCommand(Command.ZPOPMIN, args, (cmd)->cmd.zpopmin(rawKey(key), count),
				new ListConverter<>(new TupleConverter()));
	}

	@Override
	public String zRandMember(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.ZRANDMEMBER, args, (cmd)->cmd.zrandmember(rawKey(key)));
	}

	@Override
	public byte[] zRandMember(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.ZRANDMEMBER, args, (cmd)->cmd.zrandmember(rawKey(key)));
	}

	@Override
	public List<String> zRandMember(final String key, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(count);
		return executeCommand(Command.ZRANDMEMBER, args, (cmd)->cmd.zrandmember(rawKey(key), count));
	}

	@Override
	public List<byte[]> zRandMember(final byte[] key, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(count);
		return executeCommand(Command.ZRANDMEMBER, args, (cmd)->cmd.zrandmember(rawKey(key), count));
	}

	@Override
	public Tuple zRandMemberWithScores(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.ZRANDMEMBER, args, (cmd)->cmd.zrandmemberWithScores(rawKey(key), 1), (v)->{
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
		return executeCommand(Command.ZRANDMEMBER, args, (cmd)->cmd.zrandmemberWithScores(rawKey(key), 1), (v)->{
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
		return executeCommand(Command.ZRANDMEMBER, args, (cmd)->cmd.zrandmemberWithScores(rawKey(key), count),
				new ListConverter<>(new TupleConverter()));
	}

	@Override
	public List<Tuple> zRandMemberWithScores(final byte[] key, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(count);
		return executeCommand(Command.ZRANDMEMBER, args, (cmd)->cmd.zrandmemberWithScores(rawKey(key), count),
				new ListConverter<>(new TupleConverter()));
	}

	@Override
	public List<String> zRange(final String key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start, end);
		return zRange(rawKey(key), new JedisZRangeParams(start, end), args);
	}

	@Override
	public List<byte[]> zRange(final byte[] key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start, end);
		return zRange(rawKey(key), new JedisZRangeParams(start, end), args);
	}

	@Override
	public List<String> zRange(final String key, final long start, final long end, final ZRangeType type) {
		final CommandArguments args = CommandArguments.create(key).add(start, end).add(type);
		return zRange(rawKey(key), new JedisZRangeParams(type, start, end), args);
	}

	@Override
	public List<byte[]> zRange(final byte[] key, final long start, final long end, final ZRangeType type) {
		final CommandArguments args = CommandArguments.create(key).add(start, end).add(type);
		return zRange(rawKey(key), new JedisZRangeParams(type, start, end), args);
	}

	@Override
	public List<String> zRange(final String key, final long start, final long end, final ZRangeType type,
							   final boolean rev) {
		final CommandArguments args = CommandArguments.create(key).add(start, end).add(type).add(rev ? "REV" : null);
		return zRange(rawKey(key), new JedisZRangeParams(type, start, end, rev), args);
	}

	@Override
	public List<byte[]> zRange(final byte[] key, final long start, final long end, final ZRangeType type,
							   final boolean rev) {
		final CommandArguments args = CommandArguments.create(key).add(start, end).add(type).add(rev ? "REV" : null);
		return zRange(rawKey(key), new JedisZRangeParams(type, start, end, rev), args);
	}

	@Override
	public List<String> zRange(final String key, final long start, final long end, final boolean rev) {
		final CommandArguments args = CommandArguments.create(key).add(start, end).add(rev ? "REV" : null);
		return zRange(rawKey(key), new JedisZRangeParams(start, end, rev), args);
	}

	@Override
	public List<byte[]> zRange(final byte[] key, final long start, final long end, final boolean rev) {
		final CommandArguments args = CommandArguments.create(key).add(start, end).add(rev ? "REV" : null);
		return zRange(rawKey(key), new JedisZRangeParams(start, end, rev), args);
	}

	@Override
	public List<String> zRange(final String key, final long start, final long end, final int offset, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(start, end).add(Keyword.Common.LIMIT)
				.add(offset, count);
		return zRange(rawKey(key), new JedisZRangeParams(start, end, offset, count), args);
	}

	@Override
	public List<byte[]> zRange(final byte[] key, final long start, final long end, final int offset, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(start, end).add(Keyword.Common.LIMIT)
				.add(offset, count);
		return zRange(rawKey(key), new JedisZRangeParams(start, end, offset, count), args);
	}

	@Override
	public List<String> zRange(final String key, final long start, final long end, final ZRangeType type,
							   final int offset, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(start, end).add(type).add(Keyword.Common.LIMIT)
				.add(offset, count);
		return zRange(rawKey(key), new JedisZRangeParams(type, start, end, offset, count), args);
	}

	@Override
	public List<byte[]> zRange(final byte[] key, final long start, final long end, final ZRangeType type,
							   final int offset, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(start, end).add(type).add(Keyword.Common.LIMIT)
				.add(offset, count);
		return zRange(rawKey(key), new JedisZRangeParams(type, start, end, offset, count), args);
	}

	@Override
	public List<String> zRange(final String key, final long start, final long end, final ZRangeType type,
							   final boolean rev, final int offset, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(start, end).add(type).add(rev ? "REV" : null)
				.add(Keyword.Common.LIMIT).add(offset, count);
		return zRange(rawKey(key), new JedisZRangeParams(type, start, end, rev, offset, count), args);
	}

	@Override
	public List<byte[]> zRange(final byte[] key, final long start, final long end, final ZRangeType type,
							   final boolean rev, final int offset, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(start, end).add(type).add(rev ? "REV" : null)
				.add(Keyword.Common.LIMIT).add(offset, count);
		return zRange(rawKey(key), new JedisZRangeParams(type, start, end, rev, offset, count), args);
	}

	@Override
	public List<String> zRange(final String key, final long start, final long end, final boolean rev, final int offset,
							   final int count) {
		final CommandArguments args = CommandArguments.create(key).add(start, end).add(rev ? "REV" : null)
				.add(Keyword.Common.LIMIT).add(offset, count);
		return zRange(rawKey(key), new JedisZRangeParams(start, end, rev, offset, count), args);
	}

	@Override
	public List<byte[]> zRange(final byte[] key, final long start, final long end, final boolean rev, final int offset,
							   final int count) {
		final CommandArguments args = CommandArguments.create(key).add(start, end).add(rev ? "REV" : null)
				.add(Keyword.Common.LIMIT).add(offset, count);
		return zRange(rawKey(key), new JedisZRangeParams(start, end, rev, offset, count), args);
	}

	@Override
	public List<Tuple> zRangeWithScores(final String key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start, end);
		return executeCommand(Command.ZRANGE, args, (cmd)->cmd.zrangeWithScores(rawKey(key), start, end),
				new ListConverter<>(new TupleConverter()));
	}

	@Override
	public List<Tuple> zRangeWithScores(final byte[] key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start, end);
		return executeCommand(Command.ZRANGE, args, (cmd)->cmd.zrangeWithScores(rawKey(key), start, end),
				new ListConverter<>(new TupleConverter()));
	}

	@Override
	public List<Tuple> zRangeWithScores(final String key, final long start, final long end, final ZRangeType type) {
		final CommandArguments args = CommandArguments.create(key).add(start, end).add(type);
		return zRangeWithScores(rawKey(key), new JedisZRangeParams(type, start, end), args);
	}

	@Override
	public List<Tuple> zRangeWithScores(final byte[] key, final long start, final long end, final ZRangeType type) {
		final CommandArguments args = CommandArguments.create(key).add(start, end).add(type);
		return zRangeWithScores(rawKey(key), new JedisZRangeParams(type, start, end), args);
	}

	@Override
	public List<Tuple> zRangeWithScores(final String key, final long start, final long end, final ZRangeType type,
										final boolean rev) {
		final CommandArguments args = CommandArguments.create(key).add(start, end).add(type).add(rev ? "REV" : null);
		return zRangeWithScores(rawKey(key), new JedisZRangeParams(type, start, end), args);
	}

	@Override
	public List<Tuple> zRangeWithScores(final byte[] key, final long start, final long end, final ZRangeType type,
										final boolean rev) {
		final CommandArguments args = CommandArguments.create(key).add(start, end).add(type).add(rev ? "REV" : null);
		return zRangeWithScores(rawKey(key), new JedisZRangeParams(type, start, end), args);
	}

	@Override
	public List<Tuple> zRangeWithScores(final String key, final long start, final long end, final boolean rev) {
		final CommandArguments args = CommandArguments.create(key).add(start, end).add(rev ? "REV" : null);
		return zRangeWithScores(rawKey(key), new JedisZRangeParams(start, end, rev), args);
	}

	@Override
	public List<Tuple> zRangeWithScores(final byte[] key, final long start, final long end, final boolean rev) {
		final CommandArguments args = CommandArguments.create(key).add(start, end).add(rev ? "REV" : null);
		return zRangeWithScores(rawKey(key), new JedisZRangeParams(start, end, rev), args);
	}

	@Override
	public List<Tuple> zRangeWithScores(final String key, final long start, final long end, final int offset,
										final int count) {
		final CommandArguments args = CommandArguments.create(key).add(start, end).add(Keyword.Common.LIMIT)
				.add(offset, count);
		return zRangeWithScores(rawKey(key), new JedisZRangeParams(start, end, offset, count), args);
	}

	@Override
	public List<Tuple> zRangeWithScores(final byte[] key, final long start, final long end, final int offset,
										final int count) {
		final CommandArguments args = CommandArguments.create(key).add(start, end).add(Keyword.Common.LIMIT)
				.add(offset, count);
		return zRangeWithScores(rawKey(key), new JedisZRangeParams(start, end, offset, count), args);
	}

	@Override
	public List<Tuple> zRangeWithScores(final String key, final long start, final long end, final ZRangeType type,
										final int offset, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(start, end).add(type).add(Keyword.Common.LIMIT)
				.add(offset, count);
		return zRangeWithScores(rawKey(key), new JedisZRangeParams(type, start, end, offset, count), args);
	}

	@Override
	public List<Tuple> zRangeWithScores(final byte[] key, final long start, final long end, final ZRangeType type,
										final int offset, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(start, end).add(type).add(Keyword.Common.LIMIT)
				.add(offset, count);
		return zRangeWithScores(rawKey(key), new JedisZRangeParams(type, start, end, offset, count), args);
	}

	@Override
	public List<Tuple> zRangeWithScores(final String key, final long start, final long end, final ZRangeType type,
										final boolean rev, final int offset, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(start, end).add(type).add(rev ? "REV" : null)
				.add(Keyword.Common.LIMIT).add(offset, count);
		return zRangeWithScores(rawKey(key), new JedisZRangeParams(type, start, end, rev, offset, count), args);
	}

	@Override
	public List<Tuple> zRangeWithScores(final byte[] key, final long start, final long end, final ZRangeType type,
										final boolean rev, final int offset, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(start, end).add(type).add(rev ? "REV" : null)
				.add(Keyword.Common.LIMIT).add(offset, count);
		return zRangeWithScores(rawKey(key), new JedisZRangeParams(type, start, end, rev, offset, count), args);
	}

	@Override
	public List<Tuple> zRangeWithScores(final String key, final long start, final long end, final boolean rev,
										final int offset, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(start, end).add(rev ? "REV" : null)
				.add(Keyword.Common.LIMIT).add(offset, count);
		return zRangeWithScores(rawKey(key), new JedisZRangeParams(start, end, rev, offset, count), args);
	}

	@Override
	public List<Tuple> zRangeWithScores(final byte[] key, final long start, final long end, final boolean rev,
										final int offset, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(start, end).add(rev ? "REV" : null)
				.add(Keyword.Common.LIMIT).add(offset, count);
		return zRangeWithScores(rawKey(key), new JedisZRangeParams(start, end, rev, offset, count), args);
	}

	@Override
	public List<String> zRangeByLex(final String key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min, max);
		return executeCommand(Command.ZRANGEBYLEX, args,
				(cmd)->cmd.zrangeByLex(rawKey(key), Double.toString(min), Double.toString(max)));
	}

	@Override
	public List<byte[]> zRangeByLex(final byte[] key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min, max);
		return executeCommand(Command.ZRANGEBYLEX, args,
				(cmd)->cmd.zrangeByLex(rawKey(key), NumberUtils.double2bytes(min), NumberUtils.double2bytes(max)));
	}

	@Override
	public List<String> zRangeByLex(final String key, final double min, final double max, final int offset,
									final int count) {
		final CommandArguments args = CommandArguments.create(key).add(min, max).add(Keyword.Common.LIMIT)
				.add(offset, count);
		return executeCommand(Command.ZRANGEBYLEX, args,
				(cmd)->cmd.zrangeByLex(rawKey(key), Double.toString(min), Double.toString(max), offset, count));
	}

	@Override
	public List<byte[]> zRangeByLex(final byte[] key, final double min, final double max, final int offset,
									final int count) {
		final CommandArguments args = CommandArguments.create(key).add(min, max).add(Keyword.Common.LIMIT)
				.add(offset, count);
		return executeCommand(Command.ZRANGEBYLEX, args,
				(cmd)->cmd.zrangeByLex(rawKey(key), NumberUtils.double2bytes(min), NumberUtils.double2bytes(max),
						offset, count));
	}

	@Override
	public List<String> zRangeByScore(final String key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min, max);
		return executeCommand(Command.ZRANGEBYSCORE, args,
				(cmd)->cmd.zrangeByScore(rawKey(key), Double.toString(min), Double.toString(max)));
	}

	@Override
	public List<byte[]> zRangeByScore(final byte[] key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min, max);
		return executeCommand(Command.ZRANGEBYSCORE, args, (cmd)->cmd.zrangeByScore(rawKey(key), min, max));
	}

	@Override
	public List<String> zRangeByScore(final String key, final double min, final double max, final int offset,
									  final int count) {
		final CommandArguments args = CommandArguments.create(key).add(min, max).add(Keyword.Common.LIMIT)
				.add(offset, count);
		return executeCommand(Command.ZRANGEBYSCORE, args,
				(cmd)->cmd.zrangeByScore(rawKey(key), Double.toString(min), Double.toString(max), offset, count));
	}

	@Override
	public List<byte[]> zRangeByScore(final byte[] key, final double min, final double max, final int offset,
									  final int count) {
		final CommandArguments args = CommandArguments.create(key).add(min, max).add(Keyword.Common.LIMIT)
				.add(offset, count);
		return executeCommand(Command.ZRANGEBYSCORE, args,
				(cmd)->cmd.zrangeByScore(rawKey(key), min, max, offset, count));
	}

	@Override
	public List<Tuple> zRangeByScoreWithScores(final String key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min, max).add(Keyword.Common.WITHSCORES);
		return executeCommand(Command.ZRANGEBYSCORE, args,
				(cmd)->cmd.zrangeByScoreWithScores(rawKey(key), Double.toString(min), Double.toString(max)),
				new ListConverter<>(new TupleConverter()));
	}

	@Override
	public List<Tuple> zRangeByScoreWithScores(final byte[] key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min, max).add(Keyword.Common.WITHSCORES);
		return executeCommand(Command.ZRANGEBYSCORE, args, (cmd)->cmd.zrangeByScoreWithScores(rawKey(key), min, max),
				new ListConverter<>(new TupleConverter()));
	}

	@Override
	public List<Tuple> zRangeByScoreWithScores(final String key, final double min, final double max, final int offset,
											   final int count) {
		final CommandArguments args = CommandArguments.create(key).add(min, max).add(Keyword.Common.WITHSCORES)
				.add(Keyword.Common.LIMIT).add(offset, count);
		return executeCommand(Command.ZRANGEBYSCORE, args,
				(cmd)->cmd.zrangeByScoreWithScores(rawKey(key), Double.toString(min), Double.toString(max), offset,
						count), new ListConverter<>(new TupleConverter()));
	}

	@Override
	public List<Tuple> zRangeByScoreWithScores(final byte[] key, final double min, final double max, final int offset,
											   final int count) {
		final CommandArguments args = CommandArguments.create(key).add(min, max).add(Keyword.Common.WITHSCORES)
				.add(Keyword.Common.LIMIT).add(offset, count);
		return executeCommand(Command.ZRANGEBYSCORE, args,
				(cmd)->cmd.zrangeByScoreWithScores(rawKey(key), min, max, offset, count),
				new ListConverter<>(new TupleConverter()));
	}

	@Override
	public Long zRangeStore(final String destKey, final String key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(destKey, key).add(start, end);
		return zRangeStore(rawKey(destKey), rawKey(key), new JedisZRangeParams(start, end), args);
	}

	@Override
	public Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(destKey, key).add(start, end);
		return zRangeStore(rawKey(destKey), rawKey(key), new JedisZRangeParams(start, end), args);
	}

	@Override
	public Long zRangeStore(final String destKey, final String key, final long start, final long end,
							final ZRangeType type) {
		final CommandArguments args = CommandArguments.create(destKey, key).add(start, end).add(type);
		return zRangeStore(rawKey(destKey), rawKey(key), new JedisZRangeParams(type, start, end), args);
	}

	@Override
	public Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end,
							final ZRangeType type) {
		final CommandArguments args = CommandArguments.create(destKey, key).add(start, end).add(type);
		return zRangeStore(rawKey(destKey), rawKey(key), new JedisZRangeParams(type, start, end), args);
	}

	@Override
	public Long zRangeStore(final String destKey, final String key, final long start, final long end,
							final ZRangeType type, final boolean rev) {
		final CommandArguments args = CommandArguments.create(destKey, key).add(start, end).add(type)
				.add(rev ? "REV" : null);
		return zRangeStore(rawKey(destKey), rawKey(key), new JedisZRangeParams(type, start, end, rev), args);
	}

	@Override
	public Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end,
							final ZRangeType type, final boolean rev) {
		final CommandArguments args = CommandArguments.create(destKey, key).add(start, end).add(type)
				.add(rev ? "REV" : null);
		return zRangeStore(rawKey(destKey), rawKey(key), new JedisZRangeParams(type, start, end, rev), args);
	}

	@Override
	public Long zRangeStore(final String destKey, final String key, final long start, final long end,
							final boolean rev) {
		final CommandArguments args = CommandArguments.create(destKey, key).add(start, end).add(rev ? "REV" : null);
		return zRangeStore(rawKey(destKey), rawKey(key), new JedisZRangeParams(start, end, rev), args);
	}

	@Override
	public Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end,
							final boolean rev) {
		final CommandArguments args = CommandArguments.create(destKey, key).add(start, end).add(rev ? "REV" : null);
		return zRangeStore(rawKey(destKey), rawKey(key), new JedisZRangeParams(start, end, rev), args);
	}

	@Override
	public Long zRangeStore(final String destKey, final String key, final long start, final long end, final int offset,
							final int count) {
		final CommandArguments args = CommandArguments.create(destKey).add(key).add(start, end)
				.add(Keyword.Common.LIMIT).add(offset, count);
		return zRangeStore(rawKey(destKey), rawKey(key), new JedisZRangeParams(start, end, offset, count), args);
	}

	@Override
	public Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end, final int offset,
							final int count) {
		final CommandArguments args = CommandArguments.create(destKey).add(key).add(start, end)
				.add(Keyword.Common.LIMIT).add(offset, count);
		return zRangeStore(rawKey(destKey), rawKey(key), new JedisZRangeParams(start, end, offset, count), args);
	}

	@Override
	public Long zRangeStore(final String destKey, final String key, final long start, final long end,
							final ZRangeType type, final int offset, final int count) {
		final CommandArguments args = CommandArguments.create(destKey).add(key).add(start, end).add(type)
				.add(Keyword.Common.LIMIT).add(offset, count);
		return zRangeStore(rawKey(destKey), rawKey(key), new JedisZRangeParams(type, start, end, offset, count), args);
	}

	@Override
	public Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end,
							final ZRangeType type, final int offset, final int count) {
		final CommandArguments args = CommandArguments.create(destKey).add(key).add(start, end).add(type)
				.add(Keyword.Common.LIMIT).add(offset, count);
		return zRangeStore(rawKey(destKey), rawKey(key), new JedisZRangeParams(type, start, end, offset, count), args);
	}

	@Override
	public Long zRangeStore(final String destKey, final String key, final long start, final long end,
							final ZRangeType type, final boolean rev, final int offset, final int count) {
		final CommandArguments args = CommandArguments.create(destKey).add(key).add(start, end).add(type)
				.add(rev ? "REV" : null).add(Keyword.Common.LIMIT).add(offset, count);
		return zRangeStore(rawKey(destKey), rawKey(key), new JedisZRangeParams(type, start, end, rev, offset, count),
				args);
	}

	@Override
	public Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end,
							final ZRangeType type, final boolean rev, final int offset, final int count) {
		final CommandArguments args = CommandArguments.create(destKey).add(key).add(start, end).add(type)
				.add(rev ? "REV" : null).add(Keyword.Common.LIMIT).add(offset, count);
		return zRangeStore(rawKey(destKey), rawKey(key), new JedisZRangeParams(type, start, end, rev, offset, count),
				args);
	}

	@Override
	public Long zRangeStore(final String destKey, final String key, final long start, final long end,
							final boolean rev, final int offset, final int count) {
		final CommandArguments args = CommandArguments.create(destKey).add(key).add(start, end).add(rev ? "REV" : null)
				.add(Keyword.Common.LIMIT).add(offset, count);
		return zRangeStore(rawKey(destKey), rawKey(key), new JedisZRangeParams(start, end, rev, offset, count), args);
	}

	@Override
	public Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end,
							final boolean rev, final int offset, final int count) {
		final CommandArguments args = CommandArguments.create(destKey).add(key).add(start, end).add(rev ? "REV" : null)
				.add(Keyword.Common.LIMIT).add(offset, count);
		return zRangeStore(rawKey(destKey), rawKey(key), new JedisZRangeParams(start, end, rev, offset, count), args);
	}

	@Override
	public Long zRank(final String key, final String member) {
		final CommandArguments args = CommandArguments.create(key, member);
		return executeCommand(Command.ZRANK, args, (cmd)->cmd.zrank(rawKey(key), member));
	}

	@Override
	public Long zRank(final byte[] key, final byte[] member) {
		final CommandArguments args = CommandArguments.create(key, member);
		return executeCommand(Command.ZRANK, args, (cmd)->cmd.zrank(rawKey(key), member));
	}

	@Override
	public KeyValue<Long, Double> zRankWithScores(final String key, final String member) {
		final CommandArguments args = CommandArguments.create(key, member).add(Keyword.Common.WITHSCORE);
		return executeCommand(Command.ZRANK, args, (cmd)->cmd.zrankWithScore(rawKey(key), member),
				new KeyValueConverter<>((k)->k, (v)->v));
	}

	@Override
	public KeyValue<Long, Double> zRankWithScores(final byte[] key, final byte[] member) {
		final CommandArguments args = CommandArguments.create(key, member).add(Keyword.Common.WITHSCORE);
		return executeCommand(Command.ZRANK, args, (cmd)->cmd.zrankWithScore(rawKey(key), member),
				new KeyValueConverter<>((k)->k, (v)->v));
	}

	@Override
	public Long zRem(final String key, final String... members) {
		final CommandArguments args = CommandArguments.create(key).add(members);
		return executeCommand(Command.ZREM, args, (cmd)->cmd.zrem(rawKey(key), members));
	}

	@Override
	public Long zRem(final byte[] key, final byte[]... members) {
		final CommandArguments args = CommandArguments.create(key).add(members);
		return executeCommand(Command.ZREM, args, (cmd)->cmd.zrem(rawKey(key), members));
	}

	@Override
	public Long zRemRangeByLex(final String key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min, max);
		return executeCommand(Command.ZREMRANGEBYLEX, args,
				(cmd)->cmd.zremrangeByLex(rawKey(key), Double.toString(min), Double.toString(max)));
	}

	@Override
	public Long zRemRangeByLex(final byte[] key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min, max);
		return executeCommand(Command.ZREMRANGEBYLEX, args,
				(cmd)->cmd.zremrangeByLex(rawKey(key), NumberUtils.double2bytes(min), NumberUtils.double2bytes(max)));
	}

	@Override
	public Long zRemRangeByRank(final String key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start, end);
		return executeCommand(Command.ZREMRANGEBYRANK, args, (cmd)->cmd.zremrangeByRank(rawKey(key), start, end));
	}

	@Override
	public Long zRemRangeByRank(final byte[] key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start, end);
		return executeCommand(Command.ZREMRANGEBYRANK, args, (cmd)->cmd.zremrangeByRank(rawKey(key), start, end));
	}

	@Override
	public Long zRemRangeByScore(final String key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min, max);
		return executeCommand(Command.ZREMRANGEBYSCORE, args,
				(cmd)->cmd.zremrangeByScore(rawKey(key), Double.toString(min), Double.toString(max)));
	}

	@Override
	public Long zRemRangeByScore(final byte[] key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min, max);
		return executeCommand(Command.ZREMRANGEBYSCORE, args,
				(cmd)->cmd.zremrangeByScore(rawKey(key), NumberUtils.double2bytes(min), NumberUtils.double2bytes(max)));
	}

	@Override
	public List<String> zRevRange(final String key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start, end);
		return executeCommand(Command.ZREVRANGE, args, (cmd)->cmd.zrevrange(rawKey(key), start, end));
	}

	@Override
	public List<byte[]> zRevRange(final byte[] key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start, end);
		return executeCommand(Command.ZREVRANGE, args, (cmd)->cmd.zrevrange(rawKey(key), start, end));
	}

	@Override
	public List<Tuple> zRevRangeWithScores(final String key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start, end);
		return executeCommand(Command.ZREVRANGE, args, (cmd)->cmd.zrevrangeWithScores(rawKey(key), start, end),
				new ListConverter<>(new TupleConverter()));
	}

	@Override
	public List<Tuple> zRevRangeWithScores(final byte[] key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start, end);
		return executeCommand(Command.ZREVRANGE, args, (cmd)->cmd.zrevrangeWithScores(rawKey(key), start, end),
				new ListConverter<>(new TupleConverter()));
	}

	@Override
	public List<String> zRevRangeByLex(final String key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min, max);
		return executeCommand(Command.ZREVRANGEBYLEX, args,
				(cmd)->cmd.zrevrangeByLex(rawKey(key), Double.toString(min), Double.toString(max)));
	}

	@Override
	public List<byte[]> zRevRangeByLex(final byte[] key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min, max);
		return executeCommand(Command.ZREVRANGEBYLEX, args,
				(cmd)->cmd.zrevrangeByLex(rawKey(key), NumberUtils.double2bytes(min), NumberUtils.double2bytes(max)));
	}

	@Override
	public List<String> zRevRangeByLex(final String key, final double min, final double max, final int offset,
									   final int count) {
		final CommandArguments args = CommandArguments.create(key).add(min, max).add(Keyword.Common.LIMIT)
				.add(offset, count);
		return executeCommand(Command.ZREVRANGEBYLEX, args,
				(cmd)->cmd.zrevrangeByLex(rawKey(key), Double.toString(min), Double.toString(max), offset, count));
	}

	@Override
	public List<byte[]> zRevRangeByLex(final byte[] key, final double min, final double max, final int offset,
									   final int count) {
		final CommandArguments args = CommandArguments.create(key).add(min, max).add(Keyword.Common.LIMIT)
				.add(offset, count);
		return executeCommand(Command.ZREVRANGEBYLEX, args,
				(cmd)->cmd.zrevrangeByLex(rawKey(key), NumberUtils.double2bytes(min), NumberUtils.double2bytes(max),
						offset, count));
	}

	@Override
	public List<String> zRevRangeByScore(final String key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min, max);
		return executeCommand(Command.ZREVRANGEBYSCORE, args,
				(cmd)->cmd.zrevrangeByScore(rawKey(key), Double.toString(min), Double.toString(max)));
	}

	@Override
	public List<byte[]> zRevRangeByScore(final byte[] key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min, max);
		return executeCommand(Command.ZREVRANGEBYSCORE, args,
				(cmd)->cmd.zrevrangeByLex(rawKey(key), NumberUtils.double2bytes(min), NumberUtils.double2bytes(max)));
	}

	@Override
	public List<String> zRevRangeByScore(final String key, final double min, final double max, final int offset,
										 final int count) {
		final CommandArguments args = CommandArguments.create(key).add(min, max).add(Keyword.Common.LIMIT)
				.add(offset, count);
		return executeCommand(Command.ZREVRANGEBYSCORE, args,
				(cmd)->cmd.zrevrangeByScore(rawKey(key), Double.toString(min), Double.toString(max), offset, count));
	}

	@Override
	public List<byte[]> zRevRangeByScore(final byte[] key, final double min, final double max, final int offset,
										 final int count) {
		final CommandArguments args = CommandArguments.create(key).add(min, max).add(Keyword.Common.LIMIT)
				.add(offset, count);
		return executeCommand(Command.ZREVRANGEBYSCORE, args,
				(cmd)->cmd.zrevrangeByLex(rawKey(key), NumberUtils.double2bytes(min), NumberUtils.double2bytes(max),
						offset, count));
	}

	@Override
	public List<Tuple> zRevRangeByScoreWithScores(final String key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min, max);
		return executeCommand(Command.ZREVRANGEBYSCORE, args,
				(cmd)->cmd.zrangeByScoreWithScores(rawKey(key), Double.toString(min), Double.toString(max)),
				new ListConverter<>(new TupleConverter()));
	}

	@Override
	public List<Tuple> zRevRangeByScoreWithScores(final byte[] key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min, max);
		return executeCommand(Command.ZREVRANGEBYSCORE, args, (cmd)->cmd.zrangeByScoreWithScores(rawKey(key), min, max),
				new ListConverter<>(new TupleConverter()));
	}

	@Override
	public List<Tuple> zRevRangeByScoreWithScores(final String key, final double min, final double max,
												  final int offset, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(min, max).add(Keyword.Common.LIMIT)
				.add(offset, count);
		return executeCommand(Command.ZREVRANGEBYSCORE, args,
				(cmd)->cmd.zrangeByScoreWithScores(rawKey(key), Double.toString(min), Double.toString(max), offset,
						count), new ListConverter<>(new TupleConverter()));
	}

	@Override
	public List<Tuple> zRevRangeByScoreWithScores(final byte[] key, final double min, final double max,
												  final int offset, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(min, max).add(Keyword.Common.LIMIT)
				.add(offset, count);
		return executeCommand(Command.ZREVRANGEBYSCORE, args,
				(cmd)->cmd.zrangeByScoreWithScores(rawKey(key), min, max, offset, count),
				new ListConverter<>(new TupleConverter()));
	}

	@Override
	public Long zRevRank(final String key, final String member) {
		final CommandArguments args = CommandArguments.create(key, member);
		return executeCommand(Command.ZREVRANK, args, (cmd)->cmd.zrevrank(rawKey(key), member));
	}

	@Override
	public Long zRevRank(final byte[] key, final byte[] member) {
		final CommandArguments args = CommandArguments.create(key).add(member);
		return executeCommand(Command.ZREVRANK, args, (cmd)->cmd.zrevrank(rawKey(key), member));
	}

	@Override
	public KeyValue<Long, Double> zRevRankWithScore(final String key, final String member) {
		final CommandArguments args = CommandArguments.create(key, member).add(Keyword.Common.WITHSCORE);
		return executeCommand(Command.ZREVRANK, args, (cmd)->cmd.zrankWithScore(rawKey(key), member),
				new KeyValueConverter<>((k)->k, (v)->v));
	}

	@Override
	public KeyValue<Long, Double> zRevRankWithScore(final byte[] key, final byte[] member) {
		final CommandArguments args = CommandArguments.create(key, member).add(Keyword.Common.WITHSCORE);
		return executeCommand(Command.ZREVRANK, args, (cmd)->cmd.zrankWithScore(rawKey(key), member),
				new KeyValueConverter<>((k)->k, (v)->v));
	}

	@Override
	public ScanResult<Tuple> zScan(final String key, final String cursor) {
		final CommandArguments args = CommandArguments.create(key).add(cursor);
		return executeCommand(Command.ZSCAN, args, (cmd)->cmd.zscan(rawKey(key), cursor),
				new ScanResultConverter<>(new TupleConverter()));
	}

	@Override
	public ScanResult<Tuple> zScan(final byte[] key, final byte[] cursor) {
		final CommandArguments args = CommandArguments.create(key).add(cursor);
		return executeCommand(Command.ZSCAN, args, (cmd)->cmd.zscan(rawKey(key), cursor),
				new ScanResultConverter<>(new TupleConverter()));
	}

	@Override
	public ScanResult<Tuple> zScan(final String key, final String cursor, final String pattern) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(Keyword.Scan.MATCH, pattern);
		return zScan(rawKey(key), cursor, new JedisScanParams(pattern), args);
	}

	@Override
	public ScanResult<Tuple> zScan(final byte[] key, final byte[] cursor, final byte[] pattern) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(Keyword.Scan.MATCH, pattern);
		return zScan(rawKey(key), cursor, new JedisScanParams(pattern), args);
	}

	@Override
	public ScanResult<Tuple> zScan(final String key, final String cursor, final String pattern, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(Keyword.Scan.MATCH, pattern)
				.add(Keyword.Common.COUNT, count);
		return zScan(rawKey(key), cursor, new JedisScanParams(pattern, count), args);
	}

	@Override
	public ScanResult<Tuple> zScan(final byte[] key, final byte[] cursor, final byte[] pattern, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(Keyword.Scan.MATCH, pattern)
				.add(Keyword.Common.COUNT, count);
		return zScan(rawKey(key), cursor, new JedisScanParams(pattern, count), args);
	}

	@Override
	public ScanResult<Tuple> zScan(final String key, final String cursor, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(Keyword.Common.COUNT, count);
		return zScan(rawKey(key), cursor, new JedisScanParams(count), args);
	}

	@Override
	public ScanResult<Tuple> zScan(final byte[] key, final byte[] cursor, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(Keyword.Common.COUNT, count);
		return zScan(rawKey(key), cursor, new JedisScanParams(count), args);
	}

	@Override
	public Double zScore(final String key, final String member) {
		final CommandArguments args = CommandArguments.create(key).add(member);
		return executeCommand(Command.ZSCORE, args, (cmd)->cmd.zscore(rawKey(key), member));
	}

	@Override
	public Double zScore(final byte[] key, final byte[] member) {
		final CommandArguments args = CommandArguments.create(key).add(member);
		return executeCommand(Command.ZSCORE, args, (cmd)->cmd.zscore(rawKey(key), member));
	}

	@Override
	public List<String> zUnion(final String... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return zUnion(rawKeys(keys), new JedisZParams(), args);
	}

	@Override
	public List<byte[]> zUnion(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return zUnion(rawKeys(keys), new JedisZParams(), args);
	}

	@Override
	public List<String> zUnion(final String[] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create(keys).add("AGGREGATE", aggregate);
		return zUnion(rawKeys(keys), new JedisZParams(aggregate), args);
	}

	@Override
	public List<byte[]> zUnion(final byte[][] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create(keys).add("AGGREGATE", aggregate);
		return zUnion(rawKeys(keys), new JedisZParams(aggregate), args);
	}

	@Override
	public List<String> zUnion(final String[] keys, final Aggregate aggregate, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add("AGGREGATE", aggregate).add("WEIGHTS", weights);
		return zUnion(rawKeys(keys), new JedisZParams(aggregate, weights), args);
	}

	@Override
	public List<byte[]> zUnion(final byte[][] keys, final Aggregate aggregate, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add("AGGREGATE", aggregate).add("WEIGHTS", weights);
		return zUnion(rawKeys(keys), new JedisZParams(aggregate, weights), args);
	}

	@Override
	public List<String> zUnion(final String[] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add("WEIGHTS", weights);
		return zUnion(rawKeys(keys), new JedisZParams(weights), args);
	}

	@Override
	public List<byte[]> zUnion(final byte[][] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add("WEIGHTS", weights);
		return zUnion(rawKeys(keys), new JedisZParams(weights), args);
	}

	@Override
	public List<Tuple> zUnionWithScores(final String... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return zUnionWithScores(rawKeys(keys), new JedisZParams(), args);
	}

	@Override
	public List<Tuple> zUnionWithScores(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return zUnionWithScores(rawKeys(keys), new JedisZParams(), args);
	}

	@Override
	public List<Tuple> zUnionWithScores(final String[] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create(keys).add("AGGREGATE", aggregate);
		return zUnionWithScores(rawKeys(keys), new JedisZParams(aggregate), args);
	}

	@Override
	public List<Tuple> zUnionWithScores(final byte[][] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create(keys).add("AGGREGATE", aggregate);
		return zUnionWithScores(rawKeys(keys), new JedisZParams(aggregate), args);
	}

	@Override
	public List<Tuple> zUnionWithScores(final String[] keys, final Aggregate aggregate, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add("AGGREGATE", aggregate).add("WEIGHTS", weights);
		return zUnionWithScores(rawKeys(keys), new JedisZParams(aggregate, weights), args);
	}

	@Override
	public List<Tuple> zUnionWithScores(final byte[][] keys, final Aggregate aggregate, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add("AGGREGATE", aggregate).add("WEIGHTS", weights);
		return zUnionWithScores(rawKeys(keys), new JedisZParams(aggregate, weights), args);
	}

	@Override
	public List<Tuple> zUnionWithScores(final String[] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add("WEIGHTS", weights);
		return zUnionWithScores(rawKeys(keys), new JedisZParams(weights), args);
	}

	@Override
	public List<Tuple> zUnionWithScores(final byte[][] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add("WEIGHTS", weights);
		return zUnionWithScores(rawKeys(keys), new JedisZParams(weights), args);
	}

	@Override
	public Long zUnionStore(final String destKey, final String... keys) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys);
		return executeCommand(Command.ZUNIONSTORE, args, (cmd)->cmd.zunionstore(rawKey(destKey), rawKeys(keys)));
	}

	@Override
	public Long zUnionStore(final byte[] destKey, final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys);
		return executeCommand(Command.ZUNIONSTORE, args, (cmd)->cmd.zunionstore(rawKey(destKey), rawKeys(keys)));
	}

	@Override
	public Long zUnionStore(final String destKey, final String[] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys).add("AGGREGATE", aggregate);
		return zUnionStore(rawKey(destKey), rawKeys(keys), new JedisZParams(aggregate), args);
	}

	@Override
	public Long zUnionStore(final byte[] destKey, final byte[][] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys).add("AGGREGATE", aggregate);
		return zUnionStore(rawKey(destKey), rawKeys(keys), new JedisZParams(aggregate), args);
	}

	@Override
	public Long zUnionStore(final String destKey, final String[] keys, final Aggregate aggregate,
							final double... weights) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys).add("AGGREGATE", aggregate)
				.add("WEIGHTS", weights);
		return zUnionStore(rawKey(destKey), rawKeys(keys), new JedisZParams(aggregate, weights), args);
	}

	@Override
	public Long zUnionStore(final byte[] destKey, final byte[][] keys, final Aggregate aggregate,
							final double... weights) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys).add("AGGREGATE", aggregate)
				.add("WEIGHTS", weights);
		return zUnionStore(rawKey(destKey), rawKeys(keys), new JedisZParams(aggregate, weights), args);
	}

	@Override
	public Long zUnionStore(final String destKey, final String[] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys).add("WEIGHTS", weights);
		return zUnionStore(rawKey(destKey), rawKeys(keys), new JedisZParams(weights), args);
	}

	@Override
	public Long zUnionStore(final byte[] destKey, final byte[][] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys).add("WEIGHTS", weights);
		return zUnionStore(rawKey(destKey), rawKeys(keys), new JedisZParams(weights), args);
	}

	private List<String> zInter(final String[] keys, final JedisZParams zParams, final CommandArguments args) {
		return executeCommand(Command.ZINTER, args, (cmd)->cmd.zinter(zParams, keys));
	}

	private List<byte[]> zInter(final byte[][] keys, final JedisZParams zParams, final CommandArguments args) {
		return executeCommand(Command.ZINTER, args, (cmd)->cmd.zinter(zParams, keys));
	}

	private List<Tuple> zInterWithScores(final String[] keys, final JedisZParams zParams, final CommandArguments args) {
		return executeCommand(Command.ZINTER, args, (cmd)->cmd.zinterWithScores(zParams, keys),
				new ListConverter<>(new TupleConverter()));
	}

	private List<Tuple> zInterWithScores(final byte[][] keys, final JedisZParams zParams, final CommandArguments args) {
		return executeCommand(Command.ZINTER, args, (cmd)->cmd.zinterWithScores(zParams, keys),
				new ListConverter<>(new TupleConverter()));
	}

	private Long zInterStore(final String destKey, final String[] keys, final JedisZParams zParams,
							 final CommandArguments args) {
		return executeCommand(Command.ZINTERSTORE, args, (cmd)->cmd.zinterstore(destKey, zParams, keys));
	}

	private Long zInterStore(final byte[] destKey, final byte[][] keys, final JedisZParams zParams,
							 final CommandArguments args) {
		return executeCommand(Command.ZINTERSTORE, args, (cmd)->cmd.zinterstore(destKey, zParams, keys));
	}

	private List<String> zRange(final String key, final JedisZRangeParams zRangeParams, final CommandArguments args) {
		return executeCommand(Command.ZRANGE, args, (cmd)->cmd.zrange(key, zRangeParams));
	}

	private List<byte[]> zRange(final byte[] key, final JedisZRangeParams zRangeParams, final CommandArguments args) {
		return executeCommand(Command.ZRANGE, args, (cmd)->cmd.zrange(key, zRangeParams));
	}

	private List<Tuple> zRangeWithScores(final String key, final JedisZRangeParams zRangeParams,
										 final CommandArguments args) {
		return executeCommand(Command.ZRANGE, args, (cmd)->cmd.zrangeWithScores(key, zRangeParams),
				new ListConverter<>(new TupleConverter()));
	}

	private List<Tuple> zRangeWithScores(final byte[] key, final JedisZRangeParams zRangeParams,
										 final CommandArguments args) {
		return executeCommand(Command.ZRANGE, args, (cmd)->cmd.zrangeWithScores(key, zRangeParams),
				new ListConverter<>(new TupleConverter()));
	}

	private Long zRangeStore(final String destKey, final String key, final JedisZRangeParams zRangeParams,
							 final CommandArguments args) {
		return executeCommand(Command.ZRANGESTORE, args, (cmd)->cmd.zrangestore(destKey, key, zRangeParams), (v)->v);
	}

	private Long zRangeStore(final byte[] destKey, final byte[] key, final JedisZRangeParams zRangeParams,
							 final CommandArguments args) {
		return executeCommand(Command.ZRANGESTORE, args, (cmd)->cmd.zrangestore(destKey, key, zRangeParams), (v)->v);
	}

	private ScanResult<Tuple> zScan(final String key, final String cursor, final JedisScanParams scanParams,
									final CommandArguments args) {
		return executeCommand(Command.ZSCAN, args, (cmd)->cmd.zscan(key, cursor, scanParams),
				new ScanResultConverter<>(new TupleConverter()));
	}

	private ScanResult<Tuple> zScan(final byte[] key, final byte[] cursor, final JedisScanParams scanParams,
									final CommandArguments args) {
		return executeCommand(Command.ZSCAN, args, (cmd)->cmd.zscan(key, cursor, scanParams),
				new ScanResultConverter<>(new TupleConverter()));
	}

	private List<String> zUnion(final String[] keys, final JedisZParams zParams, final CommandArguments args) {
		return executeCommand(Command.ZUNION, args, (cmd)->cmd.zunion(zParams, keys));
	}

	private List<byte[]> zUnion(final byte[][] keys, final JedisZParams zParams, final CommandArguments args) {
		return executeCommand(Command.ZUNION, args, (cmd)->cmd.zunion(zParams, keys));
	}

	private List<Tuple> zUnionWithScores(final String[] keys, final JedisZParams zParams, final CommandArguments args) {
		return executeCommand(Command.ZUNION, args, (cmd)->cmd.zunionWithScores(zParams, keys),
				new ListConverter<>(new TupleConverter()));
	}

	private List<Tuple> zUnionWithScores(final byte[][] keys, final JedisZParams zParams, final CommandArguments args) {
		return executeCommand(Command.ZUNION, args, (cmd)->cmd.zunionWithScores(zParams, keys),
				new ListConverter<>(new TupleConverter()));
	}

	private Long zUnionStore(final String dstkey, final String[] keys, final JedisZParams zParams,
							 final CommandArguments args) {
		return executeCommand(Command.ZUNIONSTORE, args, (cmd)->cmd.zunionstore(dstkey, zParams, keys), (v)->v);
	}

	private Long zUnionStore(final byte[] dstkey, final byte[][] keys, final JedisZParams zParams,
							 final CommandArguments args) {
		return executeCommand(Command.ZUNIONSTORE, args, (cmd)->cmd.zunionstore(dstkey, zParams, keys), (v)->v);
	}

}
