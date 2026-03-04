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
import com.buession.lang.KeyValue;
import com.buession.redis.client.jedis.JedisRedisClient;
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
import com.buession.redis.core.internal.convert.jedis.params.MinMaxSortedSetOptionConverter;
import com.buession.redis.core.internal.convert.jedis.response.KeyValueConverter;
import com.buession.redis.core.internal.convert.jedis.response.ScanResultConverter;
import com.buession.redis.core.internal.convert.jedis.response.TupleConverter;
import com.buession.redis.core.internal.jedis.args.JedisScanParams;
import com.buession.redis.core.internal.jedis.args.JedisZAddParams;
import com.buession.redis.core.internal.jedis.args.JedisZParams;
import com.buession.redis.core.internal.jedis.args.JedisZRangeParams;
import redis.clients.jedis.UnifiedJedis;

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
		return bzMPop((cmd)->cmd.bzmpop(timeout, minMaxSortedSetOptionConverter.convert(minMax), keys), args);
	}

	@Override
	public KeyValue<byte[], List<Tuple>> bzMPop(final byte[][] keys, final int timeout, final MinMax minMax) {
		final CommandArguments args = CommandArguments.create(timeout).add(keys.length, keys).add(minMax);
		final MinMaxSortedSetOptionConverter minMaxSortedSetOptionConverter = new MinMaxSortedSetOptionConverter();
		return bzMPop((cmd)->cmd.bzmpop(timeout, minMaxSortedSetOptionConverter.convert(minMax), keys), args);
	}

	@Override
	public KeyValue<String, List<Tuple>> bzMPop(final String[] keys, final int timeout, final MinMax minMax,
												final int count) {
		final CommandArguments args = CommandArguments.create(timeout).add(keys.length, keys).add(minMax)
				.add(Keyword.Common.COUNT, count);
		final MinMaxSortedSetOptionConverter minMaxSortedSetOptionConverter = new MinMaxSortedSetOptionConverter();
		return bzMPop((cmd)->cmd.bzmpop(timeout, minMaxSortedSetOptionConverter.convert(minMax), count, keys), args);
	}

	@Override
	public KeyValue<byte[], List<Tuple>> bzMPop(final byte[][] keys, final int timeout, final MinMax minMax,
												final int count) {
		final CommandArguments args = CommandArguments.create(timeout).add(keys.length, keys).add(minMax)
				.add(Keyword.Common.COUNT, count);
		final MinMaxSortedSetOptionConverter minMaxSortedSetOptionConverter = new MinMaxSortedSetOptionConverter();
		return bzMPop((cmd)->cmd.bzmpop(timeout, minMaxSortedSetOptionConverter.convert(minMax), count, keys), args);
	}

	@Override
	public KeyValue<String, Tuple> bzPopMax(final String[] keys, final int timeout) {
		final CommandArguments args = CommandArguments.create(keys).add(timeout);
		return executeCommand(Command.BZPOPMAX, args, (cmd)->cmd.bzpopmax(timeout, keys),
				new KeyValueConverter<>((k)->k, new TupleConverter()));
	}

	@Override
	public KeyValue<byte[], Tuple> bzPopMax(final byte[][] keys, final int timeout) {
		final CommandArguments args = CommandArguments.create(keys).add(timeout);
		return executeCommand(Command.BZPOPMAX, args, (cmd)->cmd.bzpopmax(timeout, keys),
				new KeyValueConverter<>((k)->k, new TupleConverter()));
	}

	@Override
	public KeyValue<String, Tuple> bzPopMin(final String[] keys, final int timeout) {
		final CommandArguments args = CommandArguments.create(keys).add(timeout);
		return executeCommand(Command.BZPOPMIN, args, (cmd)->cmd.bzpopmin(timeout, keys),
				new KeyValueConverter<>((k)->k, new TupleConverter()));
	}

	@Override
	public KeyValue<byte[], Tuple> bzPopMin(final byte[][] keys, final int timeout) {
		final CommandArguments args = CommandArguments.create(keys).add(timeout);
		return executeCommand(Command.BZPOPMIN, args, (cmd)->cmd.bzpopmin(timeout, keys),
				new KeyValueConverter<>((k)->k, new TupleConverter()));
	}

	@Override
	public Long zAdd(final String key, final Tuple... members) {
		final CommandArguments args = CommandArguments.create(key).add(members);
		final Map<String, Double> membersMap = new LinkedHashMap<>(members.length);

		for(final Tuple element : members){
			membersMap.put(element.getElement(), element.getScore());
		}

		return executeCommand(Command.ZADD, args, (cmd)->cmd.zadd(key, membersMap), (v)->v);
	}

	@Override
	public Long zAdd(final byte[] key, final Tuple... members) {
		final CommandArguments args = CommandArguments.create(key).add(members);
		final Map<byte[], Double> membersMap = new LinkedHashMap<>(members.length);

		for(final Tuple element : members){
			membersMap.put(element.getBinaryElement(), element.getScore());
		}

		return executeCommand(Command.ZADD, args, (cmd)->cmd.zadd(key, membersMap), (v)->v);
	}

	@Override
	public Long zAdd(final String key, final Tuple[] members, final ZAddArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(argument).add(members);
		final Map<String, Double> membersMap = new LinkedHashMap<>(members.length);

		for(final Tuple element : members){
			membersMap.put(element.getElement(), element.getScore());
		}

		return executeCommand(Command.ZADD, args,
				(cmd)->cmd.zadd(key, membersMap, new JedisZAddParams(argument)), (v)->v);
	}

	@Override
	public Long zAdd(final byte[] key, final Tuple[] members, final ZAddArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(argument).add(members);
		final Map<byte[], Double> membersMap = new LinkedHashMap<>(members.length);

		for(final Tuple element : members){
			membersMap.put(element.getBinaryElement(), element.getScore());
		}

		return executeCommand(Command.ZADD, args,
				(cmd)->cmd.zadd(key, membersMap, new JedisZAddParams(argument)), (v)->v);
	}

	@Override
	public Long zCard(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.ZCARD, args, (cmd)->cmd.zcard(key), (v)->v);
	}

	@Override
	public Long zCard(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.ZCARD, args, (cmd)->cmd.zcard(key), (v)->v);
	}

	@Override
	public Long zCount(final String key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);
		return executeCommand(Command.ZCOUNT, args, (cmd)->cmd.zcount(key, min, max), (v)->v);
	}

	@Override
	public Long zCount(final byte[] key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);
		return executeCommand(Command.ZCOUNT, args, (cmd)->cmd.zcount(key, min, max), (v)->v);
	}

	@Override
	public List<String> zDiff(final String... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return executeCommand(Command.ZDIFF, args, (cmd)->cmd.zdiff(keys), (v)->v);
	}

	@Override
	public List<byte[]> zDiff(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return executeCommand(Command.ZDIFF, args, (cmd)->cmd.zdiff(keys), (v)->v);
	}

	@Override
	public List<Tuple> zDiffWithScores(final String... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return executeCommand(Command.ZDIFF, args, (cmd)->cmd.zdiffWithScores(keys),
				new ListConverter<>(new TupleConverter()));
	}

	@Override
	public List<Tuple> zDiffWithScores(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return executeCommand(Command.ZDIFF, args, (cmd)->cmd.zdiffWithScores(keys),
				new ListConverter<>(new TupleConverter()));
	}

	@Override
	public Long zDiffStore(final String destKey, final String... keys) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys);
		return executeCommand(Command.ZDIFFSTORE, args, (cmd)->cmd.zdiffStore(destKey, keys), (v)->v);
	}

	@Override
	public Long zDiffStore(final byte[] destKey, final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys);
		return executeCommand(Command.ZDIFFSTORE, args, (cmd)->cmd.zdiffStore(destKey, keys), (v)->v);
	}

	@Override
	public Double zIncrBy(final String key, final double increment, final String member) {
		final CommandArguments args = CommandArguments.create(key).add(increment).add(member);
		return executeCommand(Command.ZINCRBY, args, (cmd)->cmd.zincrby(key, increment, member), (v)->v);
	}

	@Override
	public Double zIncrBy(final byte[] key, final double increment, final byte[] member) {
		final CommandArguments args = CommandArguments.create(key).add(increment).add(member);
		return executeCommand(Command.ZINCRBY, args, (cmd)->cmd.zincrby(key, increment, member), (v)->v);
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
		final CommandArguments args = CommandArguments.create(keys).add("AGGREGATE", aggregate);
		return zInter(keys, new JedisZParams(aggregate), args);
	}

	@Override
	public List<byte[]> zInter(final byte[][] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create(keys).add("AGGREGATE", aggregate);
		return zInter(keys, new JedisZParams(aggregate), args);
	}

	@Override
	public List<String> zInter(final String[] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add("WEIGHTS", weights);
		return zInter(keys, new JedisZParams(weights), args);
	}

	@Override
	public List<byte[]> zInter(final byte[][] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add("WEIGHTS", weights);
		return zInter(keys, new JedisZParams(weights), args);
	}

	@Override
	public List<String> zInter(final String[] keys, final Aggregate aggregate, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add("AGGREGATE", aggregate).add("WEIGHTS", weights);
		return zInter(keys, new JedisZParams(aggregate, weights), args);
	}

	@Override
	public List<byte[]> zInter(final byte[][] keys, final Aggregate aggregate, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add("AGGREGATE", aggregate).add("WEIGHTS", weights);
		return zInter(keys, new JedisZParams(aggregate, weights), args);
	}

	@Override
	public List<Tuple> zInterWithScores(final String... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return zInterWithScores(keys, new JedisZParams(), args);
	}

	@Override
	public List<Tuple> zInterWithScores(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return zInterWithScores(keys, new JedisZParams(), args);
	}

	@Override
	public List<Tuple> zInterWithScores(final String[] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create(keys).add("AGGREGATE", aggregate);
		return zInterWithScores(keys, new JedisZParams(aggregate), args);
	}

	@Override
	public List<Tuple> zInterWithScores(final byte[][] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create(keys).add("AGGREGATE", aggregate);
		return zInterWithScores(keys, new JedisZParams(aggregate), args);
	}

	@Override
	public List<Tuple> zInterWithScores(final String[] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add("WEIGHTS", weights);
		return zInterWithScores(keys, new JedisZParams(weights), args);
	}

	@Override
	public List<Tuple> zInterWithScores(final byte[][] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add("WEIGHTS", weights);
		return zInterWithScores(keys, new JedisZParams(weights), args);
	}

	@Override
	public List<Tuple> zInterWithScores(final String[] keys, final Aggregate aggregate, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add("AGGREGATE", aggregate).add("WEIGHTS", weights);
		return zInterWithScores(keys, new JedisZParams(aggregate), args);
	}

	@Override
	public List<Tuple> zInterWithScores(final byte[][] keys, final Aggregate aggregate, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add("AGGREGATE", aggregate).add("WEIGHTS", weights);
		return zInterWithScores(keys, new JedisZParams(aggregate), args);
	}

	@Override
	public long zInterCard(final String... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return executeCommand(Command.ZINTERCARD, args, (cmd)->cmd.zintercard(keys), (v)->v);
	}

	@Override
	public long zInterCard(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return executeCommand(Command.ZINTERCARD, args, (cmd)->cmd.zintercard(keys), (v)->v);
	}

	@Override
	public long zInterCard(final String[] keys, final int limit) {
		final CommandArguments args = CommandArguments.create(keys).add(Keyword.Common.LIMIT, limit);
		return executeCommand(Command.ZINTERCARD, args, (cmd)->cmd.zintercard(limit, keys), (v)->v);
	}

	@Override
	public long zInterCard(final byte[][] keys, final int limit) {
		final CommandArguments args = CommandArguments.create(keys).add(Keyword.Common.LIMIT, limit);
		return executeCommand(Command.ZINTERCARD, args, (cmd)->cmd.zintercard(limit, keys), (v)->v);
	}

	@Override
	public Long zInterStore(final String destKey, final String... keys) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys.length).add(keys);
		return executeCommand(Command.ZINTERSTORE, args, (cmd)->cmd.zinterstore(destKey, keys), (v)->v);
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
		return zInterStore(destKey, keys, new JedisZParams(aggregate), args);
	}

	@Override
	public Long zInterStore(final byte[] destKey, final byte[][] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys.length).add(keys)
				.add("AGGREGATE", aggregate);
		return zInterStore(destKey, keys, new JedisZParams(aggregate), args);
	}

	@Override
	public Long zInterStore(final String destKey, final String[] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys.length).add(keys)
				.add("WEIGHTS", weights);
		return zInterStore(destKey, keys, new JedisZParams(weights), args);
	}

	@Override
	public Long zInterStore(final byte[] destKey, final byte[][] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys.length).add(keys)
				.add("WEIGHTS", weights);
		return zInterStore(destKey, keys, new JedisZParams(weights), args);
	}

	@Override
	public Long zInterStore(final String destKey, final String[] keys, final Aggregate aggregate,
							final double... weights) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys.length).add(keys)
				.add("AGGREGATE", aggregate).add("WEIGHTS", weights);
		return zInterStore(destKey, keys, new JedisZParams(aggregate, weights), args);
	}

	@Override
	public Long zInterStore(final byte[] destKey, final byte[][] keys, final Aggregate aggregate,
							final double... weights) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys.length).add(keys)
				.add("AGGREGATE", aggregate).add("WEIGHTS", weights);
		return zInterStore(destKey, keys, new JedisZParams(aggregate, weights), args);
	}

	@Override
	public Long zLexCount(final String key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);
		return executeCommand(Command.ZLEXCOUNT, args,
				(cmd)->cmd.zlexcount(key, Double.toString(min), Double.toString(max)), (v)->v);
	}

	@Override
	public Long zLexCount(final byte[] key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);
		return executeCommand(Command.ZLEXCOUNT, args,
				(cmd)->cmd.zlexcount(key, NumberUtils.double2bytes(min), NumberUtils.double2bytes(max)), (v)->v);
	}

	@Override
	public KeyValue<String, List<Tuple>> zMPop(final String[] keys, final MinMax minMax) {
		final CommandArguments args = CommandArguments.create(keys.length).add(keys).add(minMax);
		final MinMaxSortedSetOptionConverter minMaxSortedSetOptionConverter = new MinMaxSortedSetOptionConverter();
		return zMPop((cmd)->cmd.zmpop(minMaxSortedSetOptionConverter.convert(minMax), keys), args);
	}

	@Override
	public KeyValue<byte[], List<Tuple>> zMPop(final byte[][] keys, final MinMax minMax) {
		final CommandArguments args = CommandArguments.create(keys.length).add(keys).add(minMax);
		final MinMaxSortedSetOptionConverter minMaxSortedSetOptionConverter = new MinMaxSortedSetOptionConverter();
		return zMPop((cmd)->cmd.zmpop(minMaxSortedSetOptionConverter.convert(minMax), keys), args);
	}

	@Override
	public KeyValue<String, List<Tuple>> zMPop(final String[] keys, final MinMax minMax, final int count) {
		final CommandArguments args = CommandArguments.create(keys.length).add(keys).add(minMax).add("COUNT", count);
		final MinMaxSortedSetOptionConverter minMaxSortedSetOptionConverter = new MinMaxSortedSetOptionConverter();
		return zMPop((cmd)->cmd.zmpop(minMaxSortedSetOptionConverter.convert(minMax), count, keys), args);
	}

	@Override
	public KeyValue<byte[], List<Tuple>> zMPop(final byte[][] keys, final MinMax minMax, final int count) {
		final CommandArguments args = CommandArguments.create(keys.length).add(keys).add(minMax).add("COUNT", count);
		final MinMaxSortedSetOptionConverter minMaxSortedSetOptionConverter = new MinMaxSortedSetOptionConverter();
		return zMPop((cmd)->cmd.zmpop(minMaxSortedSetOptionConverter.convert(minMax), count, keys), args);
	}

	@Override
	public List<Double> zMScore(final String key, final String... members) {
		final CommandArguments args = CommandArguments.create(key).add(members);
		return executeCommand(Command.ZMSCORE, args, (cmd)->cmd.zmscore(key, members), (v)->v);
	}

	@Override
	public List<Double> zMScore(final byte[] key, final byte[]... members) {
		final CommandArguments args = CommandArguments.create(key).add(members);
		return executeCommand(Command.ZMSCORE, args, (cmd)->cmd.zmscore(key, members), (v)->v);
	}

	@Override
	public Tuple zPopMax(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.ZPOPMAX, args, (cmd)->cmd.zpopmax(key), new TupleConverter());
	}

	@Override
	public Tuple zPopMax(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.ZPOPMAX, args, (cmd)->cmd.zpopmax(key), new TupleConverter());
	}

	@Override
	public List<Tuple> zPopMax(final String key, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(count);
		return executeCommand(Command.ZPOPMAX, args, (cmd)->cmd.zpopmax(key, count),
				new ListConverter<>(new TupleConverter()));
	}

	@Override
	public List<Tuple> zPopMax(final byte[] key, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(count);
		return executeCommand(Command.ZPOPMAX, args, (cmd)->cmd.zpopmax(key, count),
				new ListConverter<>(new TupleConverter()));
	}

	@Override
	public Tuple zPopMin(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.ZPOPMIN, args, (cmd)->cmd.zpopmin(key), new TupleConverter());
	}

	@Override
	public Tuple zPopMin(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.ZPOPMIN, args, (cmd)->cmd.zpopmin(key), new TupleConverter());
	}

	@Override
	public List<Tuple> zPopMin(final String key, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(count);
		return executeCommand(Command.ZPOPMIN, args, (cmd)->cmd.zpopmin(key, count),
				new ListConverter<>(new TupleConverter()));
	}

	@Override
	public List<Tuple> zPopMin(final byte[] key, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(count);
		return executeCommand(Command.ZPOPMIN, args, (cmd)->cmd.zpopmin(key, count),
				new ListConverter<>(new TupleConverter()));
	}

	@Override
	public String zRandMember(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.ZRANDMEMBER, args, (cmd)->cmd.zrandmember(key), (v)->v);
	}

	@Override
	public byte[] zRandMember(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.ZRANDMEMBER, args, (cmd)->cmd.zrandmember(key), (v)->v);
	}

	@Override
	public List<String> zRandMember(final String key, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(count);
		return executeCommand(Command.ZRANDMEMBER, args, (cmd)->cmd.zrandmember(key, count), (v)->v);
	}

	@Override
	public List<byte[]> zRandMember(final byte[] key, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(count);
		return executeCommand(Command.ZRANDMEMBER, args, (cmd)->cmd.zrandmember(key, count), (v)->v);
	}

	@Override
	public Tuple zRandMemberWithScores(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.ZRANDMEMBER, args, (cmd)->cmd.zrandmemberWithScores(key, 1), (v)->{
			final ListConverter<redis.clients.jedis.resps.Tuple, Tuple> listConverter = new ListConverter<>(
					new TupleConverter());
			final List<Tuple> result = listConverter.convert(v);
			return result == null ? null : result.get(0);
		});
	}

	@Override
	public Tuple zRandMemberWithScores(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.ZRANDMEMBER, args, (cmd)->cmd.zrandmemberWithScores(key, 1), (v)->{
			final ListConverter<redis.clients.jedis.resps.Tuple, Tuple> listConverter = new ListConverter<>(
					new TupleConverter());
			final List<Tuple> result = listConverter.convert(v);
			return result == null ? null : result.get(0);
		});
	}

	@Override
	public List<Tuple> zRandMemberWithScores(final String key, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(count);
		return executeCommand(Command.ZRANDMEMBER, args, (cmd)->cmd.zrandmemberWithScores(key, count),
				new ListConverter<>(new TupleConverter()));
	}

	@Override
	public List<Tuple> zRandMemberWithScores(final byte[] key, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(count);
		return executeCommand(Command.ZRANDMEMBER, args, (cmd)->cmd.zrandmemberWithScores(key, count),
				new ListConverter<>(new TupleConverter()));
	}

	@Override
	public List<String> zRange(final String key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start, end);
		return executeCommand(Command.ZRANGE, args, (cmd)->cmd.zrange(key, start, end), (v)->v);
	}

	@Override
	public List<byte[]> zRange(final byte[] key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start, end);
		return executeCommand(Command.ZRANGE, args, (cmd)->cmd.zrange(key, start, end), (v)->v);
	}

	@Override
	public List<String> zRange(final String key, final long start, final long end, final ZRangeArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(start, end).add(argument);
		return zRange(key, new JedisZRangeParams(argument == null ? null : argument.getBy(), start,
				end, Boolean.TRUE.equals(argument.getRev())), args);
	}

	@Override
	public List<byte[]> zRange(final byte[] key, final long start, final long end, final ZRangeArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(start, end).add(argument);
		return zRange(key, new JedisZRangeParams(argument == null ? null : argument.getBy(), start,
				end, Boolean.TRUE.equals(argument.getRev())), args);
	}

	@Override
	public List<String> zRange(final String key, final long start, final long end, final int offset, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(start, end).add(Keyword.Common.LIMIT)
				.add(offset, count);
		return zRange(key, new JedisZRangeParams(start, end, offset, count), args);
	}

	@Override
	public List<byte[]> zRange(final byte[] key, final long start, final long end, final int offset, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(start, end).add(Keyword.Common.LIMIT)
				.add(offset, count);
		return zRange(key, new JedisZRangeParams(start, end, offset, count), args);
	}

	@Override
	public List<String> zRange(final String key, final long start, final long end, final ZRangeArgument argument,
							   final int offset, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(start, end).add(argument)
				.add(Keyword.Common.LIMIT).add(offset, count);
		return zRange(key, new JedisZRangeParams(argument == null ? null : argument.getBy(), start,
				end, Boolean.TRUE.equals(argument.getRev()), offset, count), args);
	}

	@Override
	public List<byte[]> zRange(final byte[] key, final long start, final long end, final ZRangeArgument argument,
							   final int offset, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(start, end).add(argument)
				.add(Keyword.Common.LIMIT).add(offset, count);
		return zRange(key, new JedisZRangeParams(argument == null ? null : argument.getBy(), start,
				end, Boolean.TRUE.equals(argument.getRev()), offset, count), args);
	}

	@Override
	public List<Tuple> zRangeWithScores(final String key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start, end);
		return executeCommand(Command.ZRANGE, args, (cmd)->cmd.zrangeWithScores(key, start, end),
				new ListConverter<>(new TupleConverter()));
	}

	@Override
	public List<Tuple> zRangeWithScores(final byte[] key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start, end);
		return executeCommand(Command.ZRANGE, args, (cmd)->cmd.zrangeWithScores(key, start, end),
				new ListConverter<>(new TupleConverter()));
	}

	@Override
	public List<Tuple> zRangeWithScores(final String key, final long start, final long end,
										final ZRangeArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(start, end).add(argument);
		return zRangeWithScores(key, new JedisZRangeParams(argument == null ? null : argument.getBy(), start,
				end, Boolean.TRUE.equals(argument.getRev())), args);
	}

	@Override
	public List<Tuple> zRangeWithScores(final byte[] key, final long start, final long end,
										final ZRangeArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(start, end).add(argument);
		return zRangeWithScores(key, new JedisZRangeParams(argument == null ? null : argument.getBy(), start,
				end, Boolean.TRUE.equals(argument.getRev())), args);
	}

	@Override
	public List<Tuple> zRangeWithScores(final String key, final long start, final long end, final int offset,
										final int count) {
		final CommandArguments args = CommandArguments.create(key).add(start, end).add(Keyword.Common.LIMIT)
				.add(offset, count);
		return zRangeWithScores(key, new JedisZRangeParams(start, end), args);
	}

	@Override
	public List<Tuple> zRangeWithScores(final byte[] key, final long start, final long end, final int offset,
										final int count) {
		final CommandArguments args = CommandArguments.create(key).add(start, end).add(Keyword.Common.LIMIT)
				.add(offset, count);
		return zRangeWithScores(key, new JedisZRangeParams(start, end), args);
	}

	@Override
	public List<Tuple> zRangeWithScores(final String key, final long start, final long end,
										final ZRangeArgument argument, final int offset, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(start, end).add(argument)
				.add(Keyword.Common.LIMIT).add(offset, count);
		return zRangeWithScores(key, new JedisZRangeParams(argument == null ? null : argument.getBy(), start,
				end, Boolean.TRUE.equals(argument.getRev()), offset, count), args);
	}

	@Override
	public List<Tuple> zRangeWithScores(final byte[] key, final long start, final long end,
										final ZRangeArgument argument, final int offset, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(start, end).add(argument)
				.add(Keyword.Common.LIMIT).add(offset, count);
		return zRangeWithScores(key, new JedisZRangeParams(argument == null ? null : argument.getBy(), start,
				end, Boolean.TRUE.equals(argument.getRev()), offset, count), args);
	}

	@Override
	public List<String> zRangeByLex(final String key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min, max);
		return executeCommand(Command.ZRANGEBYLEX, args,
				(cmd)->cmd.zrangeByLex(key, Double.toString(min), Double.toString(max)), (v)->v);
	}

	@Override
	public List<byte[]> zRangeByLex(final byte[] key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min, max);
		return executeCommand(Command.ZRANGEBYLEX, args,
				(cmd)->cmd.zrangeByLex(key, NumberUtils.double2bytes(min), NumberUtils.double2bytes(max)), (v)->v);
	}

	@Override
	public List<String> zRangeByLex(final String key, final double min, final double max, final int offset,
									final int count) {
		final CommandArguments args = CommandArguments.create(key).add(min, max).add(Keyword.Common.LIMIT)
				.add(offset, count);
		return executeCommand(Command.ZRANGEBYLEX, args,
				(cmd)->cmd.zrangeByLex(key, Double.toString(min), Double.toString(max), offset, count), (v)->v);
	}

	@Override
	public List<byte[]> zRangeByLex(final byte[] key, final double min, final double max, final int offset,
									final int count) {
		final CommandArguments args = CommandArguments.create(key).add(min, max).add(Keyword.Common.LIMIT)
				.add(offset, count);
		return executeCommand(Command.ZRANGEBYLEX, args,
				(cmd)->cmd.zrangeByLex(key, NumberUtils.double2bytes(min), NumberUtils.double2bytes(max), offset,
						count), (v)->v);
	}

	@Override
	public List<String> zRangeByScore(final String key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min, max);
		return executeCommand(Command.ZRANGEBYSCORE, args,
				(cmd)->cmd.zrangeByScore(key, Double.toString(min), Double.toString(max)), (v)->v);
	}

	@Override
	public List<byte[]> zRangeByScore(final byte[] key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min, max);
		return executeCommand(Command.ZRANGEBYSCORE, args, (cmd)->cmd.zrangeByScore(key, min, max), (v)->v);
	}

	@Override
	public List<String> zRangeByScore(final String key, final double min, final double max, final int offset,
									  final int count) {
		final CommandArguments args = CommandArguments.create(key).add(min, max).add(Keyword.Common.LIMIT)
				.add(offset, count);
		return executeCommand(Command.ZRANGEBYSCORE, args,
				(cmd)->cmd.zrangeByScore(key, Double.toString(min), Double.toString(max), offset, count), (v)->v);
	}

	@Override
	public List<byte[]> zRangeByScore(final byte[] key, final double min, final double max, final int offset,
									  final int count) {
		final CommandArguments args = CommandArguments.create(key).add(min, max).add(Keyword.Common.LIMIT)
				.add(offset, count);
		return executeCommand(Command.ZRANGEBYSCORE, args, (cmd)->cmd.zrangeByScore(key, min, max, offset, count),
				(v)->v);
	}

	@Override
	public List<Tuple> zRangeByScoreWithScores(final String key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min, max);
		return executeCommand(Command.ZRANGEBYSCORE, args,
				(cmd)->cmd.zrangeByScoreWithScores(key, Double.toString(min), Double.toString(max)),
				new ListConverter<>(new TupleConverter()));
	}

	@Override
	public List<Tuple> zRangeByScoreWithScores(final byte[] key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min, max);
		return executeCommand(Command.ZRANGEBYSCORE, args, (cmd)->cmd.zrangeByScoreWithScores(key, min, max),
				new ListConverter<>(new TupleConverter()));
	}

	@Override
	public List<Tuple> zRangeByScoreWithScores(final String key, final double min, final double max, final int offset,
											   final int count) {
		final CommandArguments args = CommandArguments.create(key).add(min, max).add(Keyword.Common.LIMIT)
				.add(offset, count);
		return executeCommand(Command.ZRANGEBYSCORE, args,
				(cmd)->cmd.zrangeByScoreWithScores(key, Double.toString(min), Double.toString(max), offset, count),
				new ListConverter<>(new TupleConverter()));
	}

	@Override
	public List<Tuple> zRangeByScoreWithScores(final byte[] key, final double min, final double max, final int offset,
											   final int count) {
		final CommandArguments args = CommandArguments.create(key).add(min, max).add(Keyword.Common.LIMIT)
				.add(offset, count);
		return executeCommand(Command.ZRANGEBYSCORE, args,
				(cmd)->cmd.zrangeByScoreWithScores(key, min, max, offset, count),
				new ListConverter<>(new TupleConverter()));
	}

	@Override
	public Long zRangeStore(final String destKey, final String key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(destKey).add(key).add(start, end);
		return zRangeStore(destKey, key, new JedisZRangeParams(start, end), args);
	}

	@Override
	public Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(destKey).add(key).add(start, end);
		return zRangeStore(destKey, key, new JedisZRangeParams(start, end), args);
	}

	@Override
	public Long zRangeStore(final String destKey, final String key, final long start, final long end,
							final ZRangeArgument argument) {
		final CommandArguments args = CommandArguments.create(destKey).add(key).add(start, end).add(argument);
		return zRangeStore(destKey, key, new JedisZRangeParams(argument == null ? null : argument.getBy(), start,
				end, argument != null && Boolean.TRUE.equals(argument.getRev())), args);
	}

	@Override
	public Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end,
							final ZRangeArgument argument) {
		final CommandArguments args = CommandArguments.create(destKey).add(key).add(start, end).add(argument);
		return zRangeStore(destKey, key, new JedisZRangeParams(argument == null ? null : argument.getBy(), start,
				end, argument != null && Boolean.TRUE.equals(argument.getRev())), args);
	}

	@Override
	public Long zRangeStore(final String destKey, final String key, final long start, final long end, final int offset,
							final int count) {
		final CommandArguments args = CommandArguments.create(destKey).add(key).add(start, end)
				.add(Keyword.Common.LIMIT).add(offset, count);
		return zRangeStore(destKey, key, new JedisZRangeParams(start, end, offset, count), args);
	}

	@Override
	public Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end, final int offset,
							final int count) {
		final CommandArguments args = CommandArguments.create(destKey).add(key).add(start, end)
				.add(Keyword.Common.LIMIT).add(offset, count);
		return zRangeStore(destKey, key, new JedisZRangeParams(start, end, offset, count), args);
	}

	@Override
	public Long zRangeStore(final String destKey, final String key, final long start, final long end,
							final ZRangeArgument argument, final int offset, final int count) {
		final CommandArguments args = CommandArguments.create(destKey).add(key).add(start, end).add(argument)
				.add(Keyword.Common.LIMIT).add(offset, count);
		return zRangeStore(destKey, key, new JedisZRangeParams(argument == null ? null : argument.getBy(), start,
				end, argument != null && Boolean.TRUE.equals(argument.getRev()), offset, count), args);
	}

	@Override
	public Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end,
							final ZRangeArgument argument, final int offset, final int count) {
		final CommandArguments args = CommandArguments.create(destKey).add(key).add(start, end).add(argument)
				.add(Keyword.Common.LIMIT).add(offset, count);
		return zRangeStore(destKey, key, new JedisZRangeParams(argument == null ? null : argument.getBy(), start,
				end, argument != null && Boolean.TRUE.equals(argument.getRev()), offset, count), args);
	}

	@Override
	public Long zRank(final String key, final String member) {
		final CommandArguments args = CommandArguments.create(key).add(member);
		return executeCommand(Command.ZRANK, args, (cmd)->cmd.zrank(key, member), (v)->v);
	}

	@Override
	public Long zRank(final byte[] key, final byte[] member) {
		final CommandArguments args = CommandArguments.create(key).add(member);
		return executeCommand(Command.ZRANK, args, (cmd)->cmd.zrank(key, member), (v)->v);
	}

	@Override
	public KeyValue<Long, Double> zRankWithScores(final String key, final String member) {
		final CommandArguments args = CommandArguments.create(key).add(member).add(Keyword.Common.WITHSCORE);
		return executeCommand(Command.ZRANK, args, (cmd)->cmd.zrankWithScore(key, member),
				new KeyValueConverter<>((k)->k, (v)->v));
	}

	@Override
	public KeyValue<Long, Double> zRankWithScores(final byte[] key, final byte[] member) {
		final CommandArguments args = CommandArguments.create(key).add(member).add(Keyword.Common.WITHSCORE);
		return executeCommand(Command.ZRANK, args, (cmd)->cmd.zrankWithScore(key, member),
				new KeyValueConverter<>((k)->k, (v)->v));
	}

	@Override
	public Long zRem(final String key, final String... members) {
		final CommandArguments args = CommandArguments.create(key).add(members);
		return executeCommand(Command.ZREM, args, (cmd)->cmd.zrem(key, members), (v)->v);
	}

	@Override
	public Long zRem(final byte[] key, final byte[]... members) {
		final CommandArguments args = CommandArguments.create(key).add(members);
		return executeCommand(Command.ZREM, args, (cmd)->cmd.zrem(key, members), (v)->v);
	}

	@Override
	public Long zRemRangeByLex(final String key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);
		return executeCommand(Command.ZREMRANGEBYLEX, args,
				(cmd)->cmd.zremrangeByLex(key, Double.toString(min), Double.toString(max)), (v)->v);
	}

	@Override
	public Long zRemRangeByLex(final byte[] key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);
		return executeCommand(Command.ZREMRANGEBYLEX, args,
				(cmd)->cmd.zremrangeByLex(key, NumberUtils.double2bytes(min), NumberUtils.double2bytes(max)), (v)->v);
	}

	@Override
	public Long zRemRangeByRank(final String key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end);
		return executeCommand(Command.ZREMRANGEBYRANK, args, (cmd)->cmd.zremrangeByRank(key, start, end), (v)->v);
	}

	@Override
	public Long zRemRangeByRank(final byte[] key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end);
		return executeCommand(Command.ZREMRANGEBYRANK, args, (cmd)->cmd.zremrangeByRank(key, start, end), (v)->v);
	}

	@Override
	public Long zRemRangeByScore(final String key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);
		return executeCommand(Command.ZREMRANGEBYSCORE, args,
				(cmd)->cmd.zremrangeByScore(key, Double.toString(min), Double.toString(max)), (v)->v);
	}

	@Override
	public Long zRemRangeByScore(final byte[] key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);
		return executeCommand(Command.ZREMRANGEBYSCORE, args,
				(cmd)->cmd.zremrangeByScore(key, NumberUtils.double2bytes(min), NumberUtils.double2bytes(max)), (v)->v);
	}

	@Override
	public List<String> zRevRange(final String key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end);
		return executeCommand(Command.ZREVRANGE, args, (cmd)->cmd.zrevrange(key, start, end), (v)->v);
	}

	@Override
	public List<byte[]> zRevRange(final byte[] key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end);
		return executeCommand(Command.ZREVRANGE, args, (cmd)->cmd.zrevrange(key, start, end), (v)->v);
	}

	@Override
	public List<Tuple> zRevRangeWithScores(final String key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end);
		return executeCommand(Command.ZREVRANGE, args, (cmd)->cmd.zrevrangeWithScores(key, start, end),
				new ListConverter<>(new TupleConverter()));
	}

	@Override
	public List<Tuple> zRevRangeWithScores(final byte[] key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end);
		return executeCommand(Command.ZREVRANGE, args, (cmd)->cmd.zrevrangeWithScores(key, start, end),
				new ListConverter<>(new TupleConverter()));
	}

	@Override
	public List<String> zRevRangeByLex(final String key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);
		return executeCommand(Command.ZREVRANGEBYLEX, args,
				(cmd)->cmd.zrevrangeByLex(key, Double.toString(min), Double.toString(max)), (v)->v);
	}

	@Override
	public List<byte[]> zRevRangeByLex(final byte[] key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);
		return executeCommand(Command.ZREVRANGEBYLEX, args,
				(cmd)->cmd.zrevrangeByLex(key, NumberUtils.double2bytes(min), NumberUtils.double2bytes(max)), (v)->v);
	}

	@Override
	public List<String> zRevRangeByLex(final String key, final double min, final double max, final int offset,
									   final int count) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max).add(Keyword.Common.LIMIT)
				.add(offset).add(count);
		return executeCommand(Command.ZREVRANGEBYLEX, args,
				(cmd)->cmd.zrevrangeByLex(key, Double.toString(min), Double.toString(max), offset, count), (v)->v);
	}

	@Override
	public List<byte[]> zRevRangeByLex(final byte[] key, final double min, final double max, final int offset,
									   final int count) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max).add(Keyword.Common.LIMIT)
				.add(offset).add(count);
		return executeCommand(Command.ZREVRANGEBYLEX, args,
				(cmd)->cmd.zrevrangeByLex(key, NumberUtils.double2bytes(min), NumberUtils.double2bytes(max), offset,
						count), (v)->v);
	}

	@Override
	public List<String> zRevRangeByScore(final String key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);
		return executeCommand(Command.ZREVRANGEBYSCORE, args,
				(cmd)->cmd.zrevrangeByScore(key, Double.toString(min), Double.toString(max)), (v)->v);
	}

	@Override
	public List<byte[]> zRevRangeByScore(final byte[] key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);
		return executeCommand(Command.ZREVRANGEBYSCORE, args,
				(cmd)->cmd.zrevrangeByLex(key, NumberUtils.double2bytes(min), NumberUtils.double2bytes(max)), (v)->v);
	}

	@Override
	public List<String> zRevRangeByScore(final String key, final double min, final double max, final int offset,
										 final int count) {
		final CommandArguments args =
				CommandArguments.create(key).add(min).add(max).add(Keyword.Common.LIMIT).add(offset).add(count);
		return executeCommand(Command.ZREVRANGEBYSCORE, args,
				(cmd)->cmd.zrevrangeByScore(key, Double.toString(min), Double.toString(max), offset, count), (v)->v);
	}

	@Override
	public List<byte[]> zRevRangeByScore(final byte[] key, final double min, final double max, final int offset,
										 final int count) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max).add(Keyword.Common.LIMIT)
				.add(offset).add(count);
		return executeCommand(Command.ZREVRANGEBYSCORE, args,
				(cmd)->cmd.zrevrangeByLex(key, NumberUtils.double2bytes(min), NumberUtils.double2bytes(max), offset,
						count), (v)->v);
	}

	@Override
	public List<Tuple> zRevRangeByScoreWithScores(final String key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);
		return executeCommand(Command.ZREVRANGEBYSCORE, args,
				(cmd)->cmd.zrangeByScoreWithScores(key, Double.toString(min), Double.toString(max)),
				new ListConverter<>(new TupleConverter()));
	}

	@Override
	public List<Tuple> zRevRangeByScoreWithScores(final byte[] key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);
		return executeCommand(Command.ZREVRANGEBYSCORE, args, (cmd)->cmd.zrangeByScoreWithScores(key, min, max),
				new ListConverter<>(new TupleConverter()));
	}

	@Override
	public List<Tuple> zRevRangeByScoreWithScores(final String key, final double min, final double max,
												  final int offset, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max).add(Keyword.Common.LIMIT)
				.add(offset).add(count);
		return executeCommand(Command.ZREVRANGEBYSCORE, args,
				(cmd)->cmd.zrangeByScoreWithScores(key, Double.toString(min), Double.toString(max), offset, count),
				new ListConverter<>(new TupleConverter()));
	}

	@Override
	public List<Tuple> zRevRangeByScoreWithScores(final byte[] key, final double min, final double max,
												  final int offset, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max).add(Keyword.Common.LIMIT)
				.add(offset).add(count);
		return executeCommand(Command.ZREVRANGEBYSCORE, args,
				(cmd)->cmd.zrangeByScoreWithScores(key, min, max, offset, count),
				new ListConverter<>(new TupleConverter()));
	}

	@Override
	public Long zRevRank(final String key, final String member) {
		final CommandArguments args = CommandArguments.create(key).add(member);
		return executeCommand(Command.ZREVRANK, args, (cmd)->cmd.zrevrank(key, member), (v)->v);
	}

	@Override
	public Long zRevRank(final byte[] key, final byte[] member) {
		final CommandArguments args = CommandArguments.create(key).add(member);
		return executeCommand(Command.ZREVRANK, args, (cmd)->cmd.zrevrank(key, member), (v)->v);
	}

	@Override
	public KeyValue<Long, Double> zRevRankWithScore(final String key, final String member) {
		final CommandArguments args = CommandArguments.create(key).add(member).add(Keyword.Common.WITHSCORE);
		return executeCommand(Command.ZREVRANK, args, (cmd)->cmd.zrankWithScore(key, member),
				new KeyValueConverter<>((k)->k, (v)->v));
	}

	@Override
	public KeyValue<Long, Double> zRevRankWithScore(final byte[] key, final byte[] member) {
		final CommandArguments args = CommandArguments.create(key).add(member).add(Keyword.Common.WITHSCORE);
		return executeCommand(Command.ZREVRANK, args, (cmd)->cmd.zrankWithScore(key, member),
				new KeyValueConverter<>((k)->k, (v)->v));
	}

	@Override
	public ScanResult<Tuple> zScan(final String key, final String cursor) {
		final CommandArguments args = CommandArguments.create(key).add(cursor);
		return executeCommand(Command.ZSCAN, args, (cmd)->cmd.zscan(key, cursor),
				new ScanResultConverter<>(new TupleConverter()));
	}

	@Override
	public ScanResult<Tuple> zScan(final byte[] key, final byte[] cursor) {
		final CommandArguments args = CommandArguments.create(key).add(cursor);
		return executeCommand(Command.ZSCAN, args, (cmd)->cmd.zscan(key, cursor),
				new ScanResultConverter<>(new TupleConverter()));
	}

	@Override
	public ScanResult<Tuple> zScan(final String key, final String cursor, final String pattern) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(Keyword.Scan.MATCH, pattern);
		return zScan(key, cursor, new JedisScanParams(pattern), args);
	}

	@Override
	public ScanResult<Tuple> zScan(final byte[] key, final byte[] cursor, final byte[] pattern) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(Keyword.Scan.MATCH, pattern);
		return zScan(key, cursor, new JedisScanParams(pattern), args);
	}

	@Override
	public ScanResult<Tuple> zScan(final String key, final String cursor, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(Keyword.Common.COUNT, count);
		return zScan(key, cursor, new JedisScanParams(count), args);
	}

	@Override
	public ScanResult<Tuple> zScan(final byte[] key, final byte[] cursor, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(Keyword.Common.COUNT, count);
		return zScan(key, cursor, new JedisScanParams(count), args);
	}

	@Override
	public ScanResult<Tuple> zScan(final String key, final String cursor, final String pattern, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(Keyword.Scan.MATCH, pattern)
				.add(Keyword.Common.COUNT, count);
		return zScan(key, cursor, new JedisScanParams(pattern, count), args);
	}

	@Override
	public ScanResult<Tuple> zScan(final byte[] key, final byte[] cursor, final byte[] pattern, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(Keyword.Scan.MATCH, pattern)
				.add(Keyword.Common.COUNT, count);
		return zScan(key, cursor, new JedisScanParams(pattern, count), args);
	}

	@Override
	public Double zScore(final String key, final String member) {
		final CommandArguments args = CommandArguments.create(key).add(member);
		return executeCommand(Command.ZSCORE, args, (cmd)->cmd.zscore(key, member), (v)->v);
	}

	@Override
	public Double zScore(final byte[] key, final byte[] member) {
		final CommandArguments args = CommandArguments.create(key).add(member);
		return executeCommand(Command.ZSCORE, args, (cmd)->cmd.zscore(key, member), (v)->v);
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
		final CommandArguments args = CommandArguments.create(keys).add("AGGREGATE", aggregate);
		return zUnion(keys, new JedisZParams(aggregate), args);
	}

	@Override
	public List<byte[]> zUnion(final byte[][] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create(keys).add("AGGREGATE", aggregate);
		return zUnion(keys, new JedisZParams(aggregate), args);
	}

	@Override
	public List<String> zUnion(final String[] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add("WEIGHTS", weights);
		return zUnion(keys, new JedisZParams(weights), args);
	}

	@Override
	public List<byte[]> zUnion(final byte[][] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add("WEIGHTS", weights);
		return zUnion(keys, new JedisZParams(weights), args);
	}

	@Override
	public List<String> zUnion(final String[] keys, final Aggregate aggregate, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add("AGGREGATE", aggregate).add("WEIGHTS", weights);
		return zUnion(keys, new JedisZParams(aggregate, weights), args);
	}

	@Override
	public List<byte[]> zUnion(final byte[][] keys, final Aggregate aggregate, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add("AGGREGATE", aggregate).add("WEIGHTS", weights);
		return zUnion(keys, new JedisZParams(aggregate, weights), args);
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
		final CommandArguments args = CommandArguments.create(keys).add("AGGREGATE", aggregate);
		return zUnionWithScores(keys, new JedisZParams(aggregate), args);
	}

	@Override
	public List<Tuple> zUnionWithScores(final byte[][] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create(keys).add("AGGREGATE", aggregate);
		return zUnionWithScores(keys, new JedisZParams(aggregate), args);
	}

	@Override
	public List<Tuple> zUnionWithScores(final String[] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add("WEIGHTS", weights);
		return zUnionWithScores(keys, new JedisZParams(weights), args);
	}

	@Override
	public List<Tuple> zUnionWithScores(final byte[][] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add("WEIGHTS", weights);
		return zUnionWithScores(keys, new JedisZParams(weights), args);
	}

	@Override
	public List<Tuple> zUnionWithScores(final String[] keys, final Aggregate aggregate, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add("AGGREGATE", aggregate).add("WEIGHTS", weights);
		return zUnionWithScores(keys, new JedisZParams(aggregate, weights), args);
	}

	@Override
	public List<Tuple> zUnionWithScores(final byte[][] keys, final Aggregate aggregate, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add("AGGREGATE", aggregate).add("WEIGHTS", weights);
		return zUnionWithScores(keys, new JedisZParams(aggregate, weights), args);
	}

	@Override
	public Long zUnionStore(final String destKey, final String... keys) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys);
		return executeCommand(Command.ZUNIONSTORE, args, (cmd)->cmd.zunionstore(destKey, keys), (v)->v);
	}

	@Override
	public Long zUnionStore(final byte[] destKey, final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys);
		return executeCommand(Command.ZUNIONSTORE, args, (cmd)->cmd.zunionstore(destKey, keys), (v)->v);
	}

	@Override
	public Long zUnionStore(final String destKey, final String[] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys).add("AGGREGATE", aggregate);
		return zUnionStore(destKey, keys, new JedisZParams(aggregate), args);
	}

	@Override
	public Long zUnionStore(final byte[] destKey, final byte[][] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys).add("AGGREGATE", aggregate);
		return zUnionStore(destKey, keys, new JedisZParams(aggregate), args);
	}

	@Override
	public Long zUnionStore(final String destKey, final String[] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys).add("WEIGHTS", weights);
		return zUnionStore(destKey, keys, new JedisZParams(weights), args);
	}

	@Override
	public Long zUnionStore(final byte[] destKey, final byte[][] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys).add("WEIGHTS", weights);
		return zUnionStore(destKey, keys, new JedisZParams(weights), args);
	}

	@Override
	public Long zUnionStore(final String destKey, final String[] keys, final Aggregate aggregate,
							final double... weights) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys).add("AGGREGATE", aggregate)
				.add("WEIGHTS", weights);
		return zUnionStore(destKey, keys, new JedisZParams(aggregate, weights), args);
	}

	@Override
	public Long zUnionStore(final byte[] destKey, final byte[][] keys, final Aggregate aggregate,
							final double... weights) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys).add("AGGREGATE", aggregate)
				.add("WEIGHTS", weights);
		return zUnionStore(destKey, keys, new JedisZParams(aggregate, weights), args);
	}

	private <K> KeyValue<K, List<Tuple>> bzMPop(
			final com.buession.redis.core.Command.Executor<UnifiedJedis, redis.clients.jedis.util.KeyValue<K, List<redis.clients.jedis.resps.Tuple>>> executor,
			final CommandArguments args) {
		return executeCommand(Command.BZMPOP, args, executor,
				new KeyValueConverter<>((k)->k, new ListConverter<>(new TupleConverter())));
	}

	private List<String> zInter(final String[] keys, final JedisZParams zParams, final CommandArguments args) {
		return executeCommand(Command.ZINTER, args, (cmd)->cmd.zinter(zParams, keys), (v)->v);
	}

	private List<byte[]> zInter(final byte[][] keys, final JedisZParams zParams, final CommandArguments args) {
		return executeCommand(Command.ZINTER, args, (cmd)->cmd.zinter(zParams, keys), (v)->v);
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
		return executeCommand(Command.ZINTERSTORE, args, (cmd)->cmd.zinterstore(destKey, zParams, keys), (v)->v);
	}

	private Long zInterStore(final byte[] destKey, final byte[][] keys, final JedisZParams zParams,
							 final CommandArguments args) {
		return executeCommand(Command.ZINTERSTORE, args, (cmd)->cmd.zinterstore(destKey, zParams, keys), (v)->v);
	}

	private <K> KeyValue<K, List<Tuple>> zMPop(
			final com.buession.redis.core.Command.Executor<UnifiedJedis, redis.clients.jedis.util.KeyValue<K, List<redis.clients.jedis.resps.Tuple>>> executor,
			final CommandArguments args) {
		return executeCommand(Command.ZMPOP, args, executor,
				new KeyValueConverter<>((k)->k, new ListConverter<>(new TupleConverter())));
	}

	private List<String> zRange(final String key, final JedisZRangeParams zRangeParams, final CommandArguments args) {
		return executeCommand(Command.ZRANGE, args, (cmd)->cmd.zrange(key, zRangeParams), (v)->v);
	}

	private List<byte[]> zRange(final byte[] key, final JedisZRangeParams zRangeParams, final CommandArguments args) {
		return executeCommand(Command.ZRANGE, args, (cmd)->cmd.zrange(key, zRangeParams), (v)->v);
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
		return executeCommand(Command.ZUNION, args, (cmd)->cmd.zunion(zParams, keys), (v)->v);
	}

	private List<byte[]> zUnion(final byte[][] keys, final JedisZParams zParams, final CommandArguments args) {
		return executeCommand(Command.ZUNION, args, (cmd)->cmd.zunion(zParams, keys), (v)->v);
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
