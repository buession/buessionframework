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

import com.buession.core.utils.NumberUtils;
import com.buession.redis.client.lettuce.LettuceStandaloneClient;
import com.buession.redis.core.Aggregate;
import com.buession.redis.core.GtLt;
import com.buession.redis.core.KeyedZSetElement;
import com.buession.redis.core.NxXx;
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.Tuple;
import com.buession.redis.core.ZRangeBy;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.internal.convert.lettuce.response.ScoredValueConverter;
import com.buession.redis.core.internal.convert.lettuce.response.ValueScanCursorConverter;
import com.buession.redis.core.internal.convert.response.ListConverter;
import com.buession.redis.core.internal.lettuce.LettuceScanArgs;
import com.buession.redis.core.internal.lettuce.LettuceScanCursor;
import com.buession.redis.core.internal.lettuce.LettuceZAddArgs;
import com.buession.redis.core.internal.lettuce.LettuceZStoreArgs;
import com.buession.redis.utils.SafeEncoder;
import io.lettuce.core.Limit;
import io.lettuce.core.Range;
import io.lettuce.core.ScoredValue;

import java.util.List;
import java.util.Map;
import java.util.Set;

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
		return new LettuceCommand<>(client, ProtocolCommand.ZPOPMIN, (cmd)->cmd.zpopmin(key),
				new ScoredValueConverter())
				.run(args);
	}

	@Override
	public List<Tuple> zPopMin(final byte[] key, final long count) {
		final CommandArguments args = CommandArguments.create("key", key).put("count", count);
		return new LettuceCommand<>(client, ProtocolCommand.ZPOPMIN, (cmd)->cmd.zpopmin(key, count),
				new ScoredValueConverter.ListScoredValueConverter())
				.run(args);
	}

	@Override
	public Tuple zPopMax(final byte[] key) {
		final CommandArguments args = CommandArguments.create("key", key);
		return new LettuceCommand<>(client, ProtocolCommand.ZPOPMAX, (cmd)->cmd.zpopmin(key),
				new ScoredValueConverter())
				.run(args);
	}

	@Override
	public List<Tuple> zPopMax(final byte[] key, final long count) {
		final CommandArguments args = CommandArguments.create("key", key).put("count", count);
		return new LettuceCommand<>(client, ProtocolCommand.ZPOPMAX, (cmd)->cmd.zpopmin(key, count),
				(new ScoredValueConverter.ListScoredValueConverter()))
				.run(args);
	}

	@Override
	public KeyedZSetElement bzPopMin(final byte[][] keys, final int timeout) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys).put("timeout", timeout);
		return new LettuceCommand<>(client, ProtocolCommand.BZPOPMIN, (cmd)->cmd.bzpopmin(timeout, keys),
				(value)->new KeyedZSetElement(value.getKey(), value.getValue().getValue(), value.getValue().getScore()))
				.run(args);
	}

	@Override
	public KeyedZSetElement bzPopMax(final byte[][] keys, final int timeout) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys).put("timeout", timeout);
		return new LettuceCommand<>(client, ProtocolCommand.BZPOPMAX, (cmd)->cmd.bzpopmax(timeout, keys),
				(value)->new KeyedZSetElement(value.getKey(), value.getValue().getValue(), value.getValue().getScore()))
				.run(args);
	}

	@Override
	public Long zAdd(final String key, final Map<String, Double> members) {
		final CommandArguments args = CommandArguments.create("keys", key).put("members", members);
		return zAdd(key, members, null, args);
	}

	@Override
	public Long zAdd(final byte[] key, final Map<byte[], Double> members) {
		final CommandArguments args = CommandArguments.create("keys", key).put("members", members);
		return zAdd(key, members, null, args);
	}

	@Override
	public Long zAdd(final String key, final Map<String, Double> members, final NxXx nxXx) {
		final CommandArguments args = CommandArguments.create("keys", key).put("members", members).put("nxXx", nxXx);
		return zAdd(key, members, new LettuceZAddArgs(nxXx), args);
	}

	@Override
	public Long zAdd(final byte[] key, final Map<byte[], Double> members, final NxXx nxXx) {
		final CommandArguments args = CommandArguments.create("keys", key).put("members", members).put("nxXx", nxXx);
		return zAdd(key, members, new LettuceZAddArgs(nxXx), args);
	}

	@Override
	public Long zAdd(final String key, final Map<String, Double> members, final GtLt gtLt) {
		final CommandArguments args = CommandArguments.create("keys", key).put("members", members).put("gtLt", gtLt);
		return zAdd(key, members, new LettuceZAddArgs(gtLt), args);
	}

	@Override
	public Long zAdd(final byte[] key, final Map<byte[], Double> members, final GtLt gtLt) {
		final CommandArguments args = CommandArguments.create("keys", key).put("members", members).put("gtLt", gtLt);
		return zAdd(key, members, new LettuceZAddArgs(gtLt), args);
	}

	@Override
	public Long zAdd(final String key, final Map<String, Double> members, final boolean ch) {
		final CommandArguments args = CommandArguments.create("keys", key).put("members", members).put("ch", ch);
		return zAdd(key, members, new LettuceZAddArgs(ch), args);
	}

	@Override
	public Long zAdd(final byte[] key, final Map<byte[], Double> members, final boolean ch) {
		final CommandArguments args = CommandArguments.create("keys", key).put("members", members).put("ch", ch);
		return zAdd(key, members, new LettuceZAddArgs(ch), args);
	}

	@Override
	public Long zAdd(final String key, final Map<String, Double> members, final NxXx nxXx, final GtLt gtLt) {
		final CommandArguments args = CommandArguments.create("keys", key).put("members", members).put("nxXx", nxXx)
				.put("gtLt", gtLt);
		return zAdd(key, members, new LettuceZAddArgs(nxXx, gtLt), args);
	}

	@Override
	public Long zAdd(final byte[] key, final Map<byte[], Double> members, final NxXx nxXx, final GtLt gtLt) {
		final CommandArguments args = CommandArguments.create("keys", key).put("members", members).put("nxXx", nxXx)
				.put("gtLt", gtLt);
		return zAdd(key, members, new LettuceZAddArgs(nxXx, gtLt), args);
	}

	@Override
	public Long zAdd(final String key, final Map<String, Double> members, final NxXx nxXx, final boolean ch) {
		final CommandArguments args = CommandArguments.create("keys", key).put("members", members).put("nxXx", nxXx)
				.put("ch", ch);
		return zAdd(key, members, new LettuceZAddArgs(nxXx, ch), args);
	}

	@Override
	public Long zAdd(final byte[] key, final Map<byte[], Double> members, final NxXx nxXx, final boolean ch) {
		final CommandArguments args = CommandArguments.create("keys", key).put("members", members).put("nxXx", nxXx)
				.put("ch", ch);
		return zAdd(key, members, new LettuceZAddArgs(nxXx, ch), args);
	}

	@Override
	public Long zAdd(final String key, final Map<String, Double> members, final GtLt gtLt, final boolean ch) {
		final CommandArguments args = CommandArguments.create("keys", key).put("members", members).put("gtLt", gtLt)
				.put("ch", ch);
		return zAdd(key, members, new LettuceZAddArgs(gtLt, ch), args);
	}

	@Override
	public Long zAdd(final byte[] key, final Map<byte[], Double> members, final GtLt gtLt, final boolean ch) {
		final CommandArguments args = CommandArguments.create("keys", key).put("members", members).put("gtLt", gtLt)
				.put("ch", ch);
		return zAdd(key, members, new LettuceZAddArgs(gtLt, ch), args);
	}

	@Override
	public Long zAdd(final String key, final Map<String, Double> members, final NxXx nxXx, final GtLt gtLt,
					 final boolean ch) {
		final CommandArguments args = CommandArguments.create("keys", key).put("members", members).put("nxXx", nxXx)
				.put("gtLt", gtLt).put("ch", ch);
		return zAdd(key, members, new LettuceZAddArgs(nxXx, gtLt, ch), args);
	}

	@Override
	public Long zAdd(final byte[] key, final Map<byte[], Double> members, final NxXx nxXx, final GtLt gtLt,
					 final boolean ch) {
		final CommandArguments args = CommandArguments.create("keys", key).put("members", members).put("nxXx", nxXx)
				.put("gtLt", gtLt).put("ch", ch);
		return zAdd(key, members, new LettuceZAddArgs(nxXx, gtLt, ch), args);
	}

	@Override
	public Long zCard(final byte[] key) {
		final CommandArguments args = CommandArguments.create("key", key);
		return new LettuceCommand<>(client, ProtocolCommand.ZCARD, (cmd)->cmd.zcard(key), (v)->v)
				.run(args);
	}

	@Override
	public Long zCount(final byte[] key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		return new LettuceCommand<>(client, ProtocolCommand.ZCOUNT, (cmd)->cmd.zcount(key, Range.create(min, max)),
				(v)->v)
				.run(args);
	}

	@Override
	public Set<String> zDiff(final String... keys) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys);
		return new LettuceCommand<Set<String>, Set<String>>(client, ProtocolCommand.ZDIFF)
				.run(args);
	}

	@Override
	public Set<byte[]> zDiff(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys);
		return new LettuceCommand<Set<byte[]>, Set<byte[]>>(client, ProtocolCommand.ZDIFF)
				.run(args);
	}

	@Override
	public Set<Tuple> zDiffWithScores(final String... keys) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys);
		return new LettuceCommand<Set<Tuple>, Set<Tuple>>(client, ProtocolCommand.ZDIFF)
				.run(args);
	}

	@Override
	public Set<Tuple> zDiffWithScores(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys);
		return new LettuceCommand<Set<Tuple>, Set<Tuple>>(client, ProtocolCommand.ZDIFF)
				.run(args);
	}

	@Override
	public Long zDiffStore(final String destKey, final String... keys) {
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("keys", (Object[]) keys);
		return new LettuceCommand<Long, Long>(client, ProtocolCommand.ZDIFFSTORE)
				.run(args);
	}

	@Override
	public Long zDiffStore(final byte[] destKey, final byte[]... keys) {
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("keys", (Object[]) keys);
		return new LettuceCommand<Long, Long>(client, ProtocolCommand.ZDIFFSTORE)
				.run(args);
	}

	@Override
	public Double zIncrBy(final byte[] key, final double increment, final byte[] member) {
		final CommandArguments args = CommandArguments.create("key", key).put("increment", increment)
				.put("member", member);
		return new LettuceCommand<>(client, ProtocolCommand.ZINCRBY, (cmd)->cmd.zincrby(key, increment, member), (v)->v)
				.run(args);
	}

	@Override
	public Set<String> zInter(final String... keys) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys);
		return new LettuceCommand<Set<String>, Set<String>>(client, ProtocolCommand.ZINTER)
				.run(args);
	}

	@Override
	public Set<byte[]> zInter(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys);
		return new LettuceCommand<Set<byte[]>, Set<byte[]>>(client, ProtocolCommand.ZINTER)
				.run(args);
	}

	@Override
	public Set<String> zInter(final String[] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys).put("aggregate", aggregate);
		return new LettuceCommand<Set<String>, Set<String>>(client, ProtocolCommand.ZINTER)
				.run(args);
	}

	@Override
	public Set<byte[]> zInter(final byte[][] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys).put("aggregate", aggregate);
		return new LettuceCommand<Set<byte[]>, Set<byte[]>>(client, ProtocolCommand.ZINTER)
				.run(args);
	}

	@Override
	public Set<String> zInter(final String[] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys).put("weights", weights);
		return new LettuceCommand<Set<String>, Set<String>>(client, ProtocolCommand.ZINTER)
				.run(args);
	}

	@Override
	public Set<byte[]> zInter(final byte[][] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys).put("weights", weights);
		return new LettuceCommand<Set<byte[]>, Set<byte[]>>(client, ProtocolCommand.ZINTER)
				.run(args);
	}

	@Override
	public Set<String> zInter(final String[] keys, final Aggregate aggregate, final double... weights) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys).put("aggregate", aggregate)
				.put("weights", weights);
		return new LettuceCommand<Set<String>, Set<String>>(client, ProtocolCommand.ZINTER)
				.run(args);
	}

	@Override
	public Set<byte[]> zInter(final byte[][] keys, final Aggregate aggregate, final double... weights) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys).put("aggregate", aggregate)
				.put("weights", weights);
		return new LettuceCommand<Set<byte[]>, Set<byte[]>>(client, ProtocolCommand.ZINTER)
				.run(args);
	}

	@Override
	public Set<Tuple> zInterWithScores(final String... keys) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys);
		return new LettuceCommand<Set<Tuple>, Set<Tuple>>(client, ProtocolCommand.ZINTER)
				.run(args);
	}

	@Override
	public Set<Tuple> zInterWithScores(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys);
		return new LettuceCommand<Set<Tuple>, Set<Tuple>>(client, ProtocolCommand.ZINTER)
				.run(args);
	}

	@Override
	public Set<Tuple> zInterWithScores(final String[] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys).put("aggregate", aggregate);
		return new LettuceCommand<Set<Tuple>, Set<Tuple>>(client, ProtocolCommand.ZINTER)
				.run(args);
	}

	@Override
	public Set<Tuple> zInterWithScores(final byte[][] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys).put("aggregate", aggregate);
		return new LettuceCommand<Set<Tuple>, Set<Tuple>>(client, ProtocolCommand.ZINTER)
				.run(args);
	}

	@Override
	public Set<Tuple> zInterWithScores(final String[] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys).put("weights", weights);
		return new LettuceCommand<Set<Tuple>, Set<Tuple>>(client, ProtocolCommand.ZINTER)
				.run(args);
	}

	@Override
	public Set<Tuple> zInterWithScores(final byte[][] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys).put("weights", weights);
		return new LettuceCommand<Set<Tuple>, Set<Tuple>>(client, ProtocolCommand.ZINTER)
				.run(args);
	}

	@Override
	public Set<Tuple> zInterWithScores(final String[] keys, final Aggregate aggregate, final double... weights) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys).put("aggregate", aggregate)
				.put("weights", weights);
		return new LettuceCommand<Set<Tuple>, Set<Tuple>>(client, ProtocolCommand.ZINTER)
				.run(args);
	}

	@Override
	public Set<Tuple> zInterWithScores(final byte[][] keys, final Aggregate aggregate, final double... weights) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys).put("aggregate", aggregate)
				.put("weights", weights);
		return new LettuceCommand<Set<Tuple>, Set<Tuple>>(client, ProtocolCommand.ZINTER)
				.run(args);
	}

	@Override
	public Long zInterStore(final byte[] destKey, final byte[]... keys) {
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("keys", (Object[]) keys);
		return new LettuceCommand<>(client, ProtocolCommand.ZINTERSTORE, (cmd)->cmd.zinterstore(destKey, keys), (v)->v)
				.run(args);
	}

	@Override
	public Long zInterStore(final byte[] destKey, final byte[][] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("keys", (Object[]) keys)
				.put("aggregate", aggregate);
		return new LettuceCommand<>(client, ProtocolCommand.ZINTERSTORE,
				(cmd)->cmd.zinterstore(destKey, new LettuceZStoreArgs(aggregate), keys), (v)->v)
				.run(args);
	}

	@Override
	public Long zInterStore(final byte[] destKey, final byte[][] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("keys", (Object[]) keys)
				.put("weights", weights);
		return new LettuceCommand<>(client, ProtocolCommand.ZINTERSTORE,
				(cmd)->cmd.zinterstore(destKey, new LettuceZStoreArgs(weights), keys), (v)->v)
				.run(args);
	}

	@Override
	public Long zInterStore(final byte[] destKey, final byte[][] keys, final Aggregate aggregate,
							final double... weights) {
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("keys", (Object[]) keys)
				.put("aggregate", aggregate).put("weights", weights);
		return new LettuceCommand<>(client, ProtocolCommand.ZINTERSTORE,
				(cmd)->cmd.zinterstore(destKey, new LettuceZStoreArgs(aggregate, weights), keys), (v)->v)
				.run(args);
	}

	@Deprecated
	@Override
	public Long zLexCount(final byte[] key, final byte[] min, final byte[] max) {
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		return new LettuceCommand<>(client, ProtocolCommand.ZLEXCOUNT,
				(cmd)->cmd.zlexcount(key, Range.create(min, max)), (v)->v)
				.run(args);
	}

	@Override
	public List<Double> zMScore(final String key, final String... members) {
		final CommandArguments args = CommandArguments.create("key", key).put("members", (Object[]) members);
		return new LettuceCommand<List<Double>, List<Double>>(client, ProtocolCommand.ZMSCORE)
				.run(args);
	}

	@Override
	public List<Double> zMScore(final byte[] key, final byte[]... members) {
		final CommandArguments args = CommandArguments.create("key", key).put("members", (Object[]) members);
		return new LettuceCommand<List<Double>, List<Double>>(client, ProtocolCommand.ZMSCORE)
				.run(args);
	}

	@Override
	public String zRandMember(final String key) {
		final CommandArguments args = CommandArguments.create("key", key);
		return new LettuceCommand<String, String>(client, ProtocolCommand.ZRANDMEMBER)
				.run(args);
	}

	@Override
	public byte[] zRandMember(final byte[] key) {
		final CommandArguments args = CommandArguments.create("key", key);
		return new LettuceCommand<byte[], byte[]>(client, ProtocolCommand.ZRANDMEMBER)
				.run(args);
	}

	@Override
	public List<String> zRandMember(final String key, final long count) {
		final CommandArguments args = CommandArguments.create("key", key).put("count", count);
		return new LettuceCommand<List<String>, List<String>>(client, ProtocolCommand.ZRANDMEMBER)
				.run(args);
	}

	@Override
	public List<byte[]> zRandMember(final byte[] key, final long count) {
		final CommandArguments args = CommandArguments.create("key", key).put("count", count);
		return new LettuceCommand<List<byte[]>, List<byte[]>>(client, ProtocolCommand.ZRANDMEMBER)
				.run(args);
	}

	@Override
	public List<Tuple> zRandMemberWithScores(final String key, final long count) {
		final CommandArguments args = CommandArguments.create("key", key).put("count", count);
		return new LettuceCommand<List<Tuple>, List<Tuple>>(client, ProtocolCommand.ZRANDMEMBER)
				.run(args);
	}

	@Override
	public List<Tuple> zRandMemberWithScores(final byte[] key, final long count) {
		final CommandArguments args = CommandArguments.create("key", key).put("count", count);
		return new LettuceCommand<List<Tuple>, List<Tuple>>(client, ProtocolCommand.ZRANDMEMBER)
				.run(args);
	}

	@Override
	public List<String> zRange(final String key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create("key", key).put("start", start).put("end", end);
		return new LettuceCommand<>(client, ProtocolCommand.ZRANGE, (cmd)->cmd.zrange(SafeEncoder.encode(key), start,
				end), new ListConverter.BinaryToStringListConverter())
				.run(args);
	}

	@Override
	public List<byte[]> zRange(final byte[] key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create("key", key).put("start", start).put("end", end);
		return new LettuceCommand<>(client, ProtocolCommand.ZRANGE, (cmd)->cmd.zrange(key, start, end), (v)->v)
				.run(args);
	}

	@Override
	public List<Tuple> zRangeWithScores(final byte[] key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create("key", key).put("start", start).put("end", end);
		return new LettuceCommand<>(client, ProtocolCommand.ZRANGE, (cmd)->cmd.zrangeWithScores(key, start, end),
				(new ScoredValueConverter.ListScoredValueConverter()))
				.run(args);
	}

	@Override
	public List<String> zRangeByLex(final String key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		return new LettuceCommand<>(client, ProtocolCommand.ZRANGEBYLEX,
				(cmd)->cmd.zrangebylex(SafeEncoder.encode(key), Range.create(NumberUtils.double2bytes(min),
						NumberUtils.double2bytes(max))), new ListConverter.BinaryToStringListConverter())
				.run(args);
	}

	@Deprecated
	@Override
	public List<String> zRangeByLex(final String key, final String min, final String max) {
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		return new LettuceCommand<>(client, ProtocolCommand.ZRANGEBYLEX,
				(cmd)->cmd.zrangebylex(SafeEncoder.encode(key), Range.create(SafeEncoder.encode(min),
						SafeEncoder.encode(max))), new ListConverter.BinaryToStringListConverter())
				.run(args);
	}

	@Deprecated
	@Override
	public List<byte[]> zRangeByLex(final byte[] key, final byte[] min, final byte[] max) {
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		return new LettuceCommand<>(client, ProtocolCommand.ZRANGEBYLEX,
				(cmd)->cmd.zrangebylex(key, Range.create(min, max)), (v)->v)
				.run(args);
	}

	@Override
	public List<String> zRangeByLex(final String key, final double min, final double max, final long offset,
									final long count) {
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max)
				.put("offset", offset).put("count", count);
		return new LettuceCommand<>(client, ProtocolCommand.ZRANGEBYLEX,
				(cmd)->cmd.zrangebylex(SafeEncoder.encode(key), Range.create(NumberUtils.double2bytes(min),
						NumberUtils.double2bytes(max)), Limit.create(offset, count)),
				new ListConverter.BinaryToStringListConverter())
				.run(args);
	}

	@Deprecated
	@Override
	public List<String> zRangeByLex(final String key, final String min, final String max, final long offset,
									final long count) {
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max)
				.put("offset", offset).put("count", count);
		return new LettuceCommand<>(client, ProtocolCommand.ZRANGEBYLEX,
				(cmd)->cmd.zrangebylex(SafeEncoder.encode(key), Range.create(SafeEncoder.encode(min),
						SafeEncoder.encode(max)), Limit.create(offset, count)),
				new ListConverter.BinaryToStringListConverter())
				.run(args);
	}

	@Deprecated
	@Override
	public List<byte[]> zRangeByLex(final byte[] key, final byte[] min, final byte[] max, final long offset,
									final long count) {
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max)
				.put("offset", offset).put("count", count);
		return new LettuceCommand<>(client, ProtocolCommand.ZRANGEBYLEX,
				(cmd)->cmd.zrangebylex(key, Range.create(min, max), Limit.create(offset, count)), (v)->v)
				.run(args);
	}

	@Override
	public List<String> zRangeByScore(final String key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		return new LettuceCommand<>(client, ProtocolCommand.ZRANGEBYSCORE,
				(cmd)->cmd.zrangebyscore(SafeEncoder.encode(key), Range.create(min, max)),
				new ListConverter.BinaryToStringListConverter())
				.run(args);
	}

	@Override
	public List<byte[]> zRangeByScore(final byte[] key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		return new LettuceCommand<>(client, ProtocolCommand.ZRANGEBYSCORE,
				(cmd)->cmd.zrangebyscore(key, Range.create(min, max)), (v)->v)
				.run(args);
	}

	@Override
	public List<String> zRangeByScore(final String key, final double min, final double max, final long offset,
									  final long count) {
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max)
				.put("offset", offset).put("count", count);
		return new LettuceCommand<>(client, ProtocolCommand.ZRANGEBYSCORE,
				(cmd)->cmd.zrangebyscore(SafeEncoder.encode(key), Range.create(min, max), Limit.create(offset, count)),
				new ListConverter.BinaryToStringListConverter())
				.run(args);
	}

	@Override
	public List<byte[]> zRangeByScore(final byte[] key, final double min, final double max, final long offset,
									  final long count) {
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max)
				.put("offset", offset).put("count", count);
		return new LettuceCommand<>(client, ProtocolCommand.ZRANGEBYSCORE,
				(cmd)->cmd.zrangebyscore(key, Range.create(min, max), Limit.create(offset, count)), (v)->v)
				.run(args);
	}

	@Override
	public List<Tuple> zRangeByScoreWithScores(final byte[] key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		return new LettuceCommand<>(client, ProtocolCommand.ZRANGEBYSCORE,
				(cmd)->cmd.zrangebyscoreWithScores(key, Range.create(min, max)),
				(new ScoredValueConverter.ListScoredValueConverter()))
				.run(args);
	}

	@Override
	public List<Tuple> zRangeByScoreWithScores(final byte[] key, final double min, final double max, final long offset,
											   final long count) {
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max)
				.put("offset", offset).put("count", count);
		return new LettuceCommand<>(client, ProtocolCommand.ZRANGEBYSCORE,
				(cmd)->cmd.zrangebyscoreWithScores(key, Range.create(min, max)),
				(new ScoredValueConverter.ListScoredValueConverter()))
				.run(args);
	}

	@Override
	public Long zRangeStore(final String destKey, final String key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("key", key).put("start", start)
				.put("end", end);
		return new LettuceCommand<Long, Long>(client, ProtocolCommand.ZRANGESTORE)
				.run(args);
	}

	@Override
	public Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("key", key).put("start", start)
				.put("end", end);
		return new LettuceCommand<Long, Long>(client, ProtocolCommand.ZRANGESTORE)
				.run(args);
	}

	@Override
	public Long zRangeStore(final String destKey, final String key, final long start, final long end,
							final ZRangeBy by) {
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("key", key).put("start", start)
				.put("end", end).put("by", by);
		return new LettuceCommand<Long, Long>(client, ProtocolCommand.ZRANGESTORE)
				.run(args);
	}

	@Override
	public Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end,
							final ZRangeBy by) {
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("key", key).put("start", start)
				.put("end", end).put("by", by);
		return new LettuceCommand<Long, Long>(client, ProtocolCommand.ZRANGESTORE)
				.run(args);
	}

	@Override
	public Long zRangeStore(final String destKey, final String key, final long start, final long end,
							final boolean rev) {
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("key", key).put("start", start)
				.put("end", end).put("rev", rev);
		return new LettuceCommand<Long, Long>(client, ProtocolCommand.ZRANGESTORE)
				.run(args);
	}

	@Override
	public Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end,
							final boolean rev) {
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("key", key).put("start", start)
				.put("end", end).put("rev", rev);
		return new LettuceCommand<Long, Long>(client, ProtocolCommand.ZRANGESTORE)
				.run(args);
	}

	@Override
	public Long zRangeStore(final String destKey, final String key, final long start, final long end, final long offset,
							final long count) {
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("key", key).put("start", start)
				.put("end", end).put("offset", offset).put("count", count);
		return new LettuceCommand<Long, Long>(client, ProtocolCommand.ZRANGESTORE)
				.run(args);
	}

	@Override
	public Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end, final long offset,
							final long count) {
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("key", key).put("start", start)
				.put("end", end).put("offset", offset).put("count", count);
		return new LettuceCommand<Long, Long>(client, ProtocolCommand.ZRANGESTORE)
				.run(args);
	}

	@Override
	public Long zRangeStore(final String destKey, final String key, final long start, final long end,
							final ZRangeBy by, final boolean rev) {
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("key", key).put("start", start)
				.put("end", end).put("by", by).put("rev", rev);
		return new LettuceCommand<Long, Long>(client, ProtocolCommand.ZRANGESTORE)
				.run(args);
	}

	@Override
	public Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end,
							final ZRangeBy by, final boolean rev) {
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("key", key).put("start", start)
				.put("end", end).put("by", by).put("rev", rev);
		return new LettuceCommand<Long, Long>(client, ProtocolCommand.ZRANGESTORE)
				.run(args);
	}

	@Override
	public Long zRangeStore(final String destKey, final String key, final long start, final long end,
							final ZRangeBy by, final long offset, final long count) {
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("key", key).put("start", start)
				.put("end", end).put("by", by).put("offset", offset).put("count", count);
		return new LettuceCommand<Long, Long>(client, ProtocolCommand.ZRANGESTORE)
				.run(args);
	}

	@Override
	public Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end,
							final ZRangeBy by, final long offset, final long count) {
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("key", key).put("start", start)
				.put("end", end).put("by", by).put("offset", offset).put("count", count);
		return new LettuceCommand<Long, Long>(client, ProtocolCommand.ZRANGESTORE)
				.run(args);
	}

	@Override
	public Long zRangeStore(final String destKey, final String key, final long start, final long end, final boolean rev,
							final long offset, final long count) {
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("key", key).put("start", start)
				.put("end", end).put("rev", rev).put("offset", offset).put("count", count);
		return new LettuceCommand<Long, Long>(client, ProtocolCommand.ZRANGESTORE)
				.run(args);
	}

	@Override
	public Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end, final boolean rev,
							final long offset, final long count) {
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("key", key).put("start", start)
				.put("end", end).put("rev", rev).put("offset", offset).put("count", count);
		return new LettuceCommand<Long, Long>(client, ProtocolCommand.ZRANGESTORE)
				.run(args);
	}

	@Override
	public Long zRangeStore(final String destKey, final String key, final long start, final long end, final ZRangeBy by,
							final boolean rev, final long offset, final long count) {
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("key", key).put("start", start)
				.put("end", end).put("by", by).put("rev", rev).put("offset", offset).put("count", count);
		return new LettuceCommand<Long, Long>(client, ProtocolCommand.ZRANGESTORE)
				.run(args);
	}

	@Override
	public Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end, final ZRangeBy by,
							final boolean rev, final long offset, final long count) {
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("key", key).put("start", start)
				.put("end", end).put("by", by).put("rev", rev).put("offset", offset).put("count", count);
		return new LettuceCommand<Long, Long>(client, ProtocolCommand.ZRANGESTORE)
				.run(args);
	}

	@Override
	public Long zRank(final byte[] key, final byte[] member) {
		final CommandArguments args = CommandArguments.create("key", key).put("member", member);
		return new LettuceCommand<>(client, ProtocolCommand.ZRANK, (cmd)->cmd.zrank(key, member), (v)->v)
				.run(args);
	}

	@Override
	public Long zRem(final byte[] key, final byte[]... members) {
		final CommandArguments args = CommandArguments.create("key", key).put("members", (Object[]) members);
		return new LettuceCommand<>(client, ProtocolCommand.ZREM, (cmd)->cmd.zrem(key, members), (v)->v)
				.run(args);
	}

	@Deprecated
	@Override
	public Long zRemRangeByLex(final byte[] key, final byte[] min, final byte[] max) {
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		return new LettuceCommand<>(client, ProtocolCommand.ZREMRANGEBYLEX,
				(cmd)->cmd.zremrangebylex(key, Range.create(min, max)), (v)->v)
				.run(args);
	}

	@Override
	public Long zRemRangeByScore(final byte[] key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		return new LettuceCommand<>(client, ProtocolCommand.ZREMRANGEBYSCORE,
				(cmd)->cmd.zremrangebyscore(key, Range.create(min, max)), (v)->v)
				.run(args);
	}

	@Override
	public Long zRemRangeByRank(final byte[] key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create("key", key).put("start", start).put("end", end);
		return new LettuceCommand<>(client, ProtocolCommand.ZREMRANGEBYRANK,
				(cmd)->cmd.zremrangebyrank(key, start, end), (v)->v)
				.run(args);
	}

	@Override
	public List<String> zRevRange(final String key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create("key", key).put("start", start).put("end", end);
		return new LettuceCommand<>(client, ProtocolCommand.ZREVRANGE,
				(cmd)->cmd.zrevrange(SafeEncoder.encode(key), start, end),
				new ListConverter.BinaryToStringListConverter())
				.run(args);
	}

	@Override
	public List<byte[]> zRevRange(final byte[] key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create("key", key).put("start", start).put("end", end);
		return new LettuceCommand<>(client, ProtocolCommand.ZREVRANGE,
				(cmd)->cmd.zrevrange(key, start, end), (v)->v)
				.run(args);
	}

	@Override
	public List<Tuple> zRevRangeWithScores(final byte[] key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create("key", key).put("start", start).put("end", end);
		return new LettuceCommand<>(client, ProtocolCommand.ZREVRANGE,
				(cmd)->cmd.zrevrangeWithScores(key, start, end), (new ScoredValueConverter.ListScoredValueConverter()))
				.run(args);
	}

	@Override
	public List<String> zRevRangeByLex(final String key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		return new LettuceCommand<>(client, ProtocolCommand.ZREVRANGEBYLEX,
				(cmd)->cmd.zrevrangebylex(SafeEncoder.encode(key), Range.create(NumberUtils.double2bytes(min),
						NumberUtils.double2bytes(max))), new ListConverter.BinaryToStringListConverter())
				.run(args);
	}

	@Deprecated
	@Override
	public List<String> zRevRangeByLex(final String key, final String min, final String max) {
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		return new LettuceCommand<>(client, ProtocolCommand.ZREVRANGEBYLEX,
				(cmd)->cmd.zrevrangebylex(SafeEncoder.encode(key), Range.create(SafeEncoder.encode(min),
						SafeEncoder.encode(max))), new ListConverter.BinaryToStringListConverter())
				.run(args);
	}

	@Deprecated
	@Override
	public List<byte[]> zRevRangeByLex(final byte[] key, final byte[] min, final byte[] max) {
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		return new LettuceCommand<>(client, ProtocolCommand.ZREVRANGEBYLEX,
				(cmd)->cmd.zrevrangebylex(key, Range.create(min, max)), (v)->v)
				.run(args);
	}

	@Override
	public List<String> zRevRangeByLex(final String key, final double min, final double max, final long offset,
									   final long count) {
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max).put("count"
				, count);
		return new LettuceCommand<>(client, ProtocolCommand.ZREVRANGEBYLEX,
				(cmd)->cmd.zrevrangebylex(SafeEncoder.encode(key), Range.create(NumberUtils.double2bytes(min),
						NumberUtils.double2bytes(max)), Limit.create(offset, count)),
				new ListConverter.BinaryToStringListConverter())
				.run(args);
	}

	@Deprecated
	@Override
	public List<String> zRevRangeByLex(final String key, final String min, final String max, final long offset,
									   final long count) {
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max).put("count"
				, count);
		return new LettuceCommand<>(client, ProtocolCommand.ZREVRANGEBYLEX,
				(cmd)->cmd.zrevrangebylex(SafeEncoder.encode(key), Range.create(SafeEncoder.encode(min),
						SafeEncoder.encode(max)), Limit.create(offset, count)),
				new ListConverter.BinaryToStringListConverter())
				.run(args);
	}

	@Deprecated
	@Override
	public List<byte[]> zRevRangeByLex(final byte[] key, final byte[] min, final byte[] max, final long offset,
									   final long count) {
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max).put("count"
				, count);
		return new LettuceCommand<>(client, ProtocolCommand.ZREVRANGEBYLEX,
				(cmd)->cmd.zrevrangebylex(key, Range.create(min, max), Limit.create(offset, count)), (v)->v)
				.run(args);
	}

	@Override
	public List<String> zRevRangeByScore(final String key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		return new LettuceCommand<>(client, ProtocolCommand.ZREVRANGEBYSCORE,
				(cmd)->cmd.zrevrangebyscore(SafeEncoder.encode(key), Range.create(min, max)),
				new ListConverter.BinaryToStringListConverter())
				.run(args);
	}

	@Override
	public List<byte[]> zRevRangeByScore(final byte[] key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		return new LettuceCommand<>(client, ProtocolCommand.ZREVRANGEBYSCORE,
				(cmd)->cmd.zrevrangebyscore(key, Range.create(min, max)), (v)->v)
				.run(args);
	}

	@Override
	public List<String> zRevRangeByScore(final String key, final double min, final double max, final long offset,
										 final long count) {
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max)
				.put("offset", offset).put("count", count);
		return new LettuceCommand<>(client, ProtocolCommand.ZREVRANGEBYSCORE,
				(cmd)->cmd.zrevrangebyscore(SafeEncoder.encode(key), Range.create(min, max),
						Limit.create(offset, count)),
				new ListConverter.BinaryToStringListConverter())
				.run(args);
	}

	@Override
	public List<byte[]> zRevRangeByScore(final byte[] key, final double min, final double max, final long offset,
										 final long count) {
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max)
				.put("offset", offset).put("count", count);
		return new LettuceCommand<>(client, ProtocolCommand.ZREVRANGEBYSCORE,
				(cmd)->cmd.zrevrangebyscore(key, Range.create(min, max),
						Limit.create(offset, count)), (v)->v)
				.run(args);
	}

	@Override
	public List<Tuple> zRevRangeByScoreWithScores(final byte[] key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		return new LettuceCommand<>(client, ProtocolCommand.ZREVRANGEBYSCORE,
				(cmd)->cmd.zrevrangebyscoreWithScores(key, Range.create(min, max)),
				(new ScoredValueConverter.ListScoredValueConverter()))
				.run(args);
	}

	@Override
	public List<Tuple> zRevRangeByScoreWithScores(final byte[] key, final double min, final double max,
												  final long offset, final long count) {
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max)
				.put("offset", offset).put("count", count);
		return new LettuceCommand<>(client, ProtocolCommand.ZREVRANGEBYSCORE,
				(cmd)->cmd.zrevrangebyscoreWithScores(key, Range.create(min, max),
						Limit.create(offset, count)), (new ScoredValueConverter.ListScoredValueConverter()))
				.run(args);
	}

	@Override
	public Long zRevRank(final byte[] key, final byte[] member) {
		final CommandArguments args = CommandArguments.create("key", key).put("member", member);
		return new LettuceCommand<>(client, ProtocolCommand.ZREVRANK, (cmd)->cmd.zrevrank(key, member), (v)->v)
				.run(args);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final String cursor) {
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor);
		return new LettuceCommand<>(client, ProtocolCommand.ZREVRANK,
				(cmd)->cmd.zscan(SafeEncoder.encode(key), new LettuceScanCursor(cursor)),
				(new ValueScanCursorConverter.ScoredValueScanCursorConverter()))
				.run(args);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor) {
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor);
		return new LettuceCommand<>(client, ProtocolCommand.ZREVRANK,
				(cmd)->cmd.zscan(key, new LettuceScanCursor(cursor)),
				(new ValueScanCursorConverter.ScoredValueScanCursorConverter()))
				.run(args);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final String cursor, final String pattern) {
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor).put("pattern", pattern);
		return new LettuceCommand<>(client, ProtocolCommand.ZREVRANK,
				(cmd)->cmd.zscan(SafeEncoder.encode(key), new LettuceScanCursor(cursor), new LettuceScanArgs(pattern)),
				(new ValueScanCursorConverter.ScoredValueScanCursorConverter()))
				.run(args);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor, final byte[] pattern) {
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor).put("pattern", pattern);
		return new LettuceCommand<>(client, ProtocolCommand.ZREVRANK,
				(cmd)->cmd.zscan(key, new LettuceScanCursor(cursor), new LettuceScanArgs(pattern)),
				(new ValueScanCursorConverter.ScoredValueScanCursorConverter()))
				.run(args);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final String cursor, final long count) {
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor).put("count", count);
		return new LettuceCommand<>(client, ProtocolCommand.ZREVRANK,
				(cmd)->cmd.zscan(SafeEncoder.encode(key), new LettuceScanCursor(cursor), new LettuceScanArgs(count)),
				(new ValueScanCursorConverter.ScoredValueScanCursorConverter()))
				.run(args);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor, final long count) {
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor).put("count", count);
		return new LettuceCommand<>(client, ProtocolCommand.ZREVRANK,
				(cmd)->cmd.zscan(key, new LettuceScanCursor(cursor), new LettuceScanArgs(count)),
				(new ValueScanCursorConverter.ScoredValueScanCursorConverter()))
				.run(args);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final String cursor, final String pattern,
										 final long count) {
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor).put("pattern", pattern)
				.put("count", count);
		return new LettuceCommand<>(client, ProtocolCommand.ZREVRANK,
				(cmd)->cmd.zscan(SafeEncoder.encode(key), new LettuceScanCursor(cursor), new LettuceScanArgs(pattern,
						count)),
				(new ValueScanCursorConverter.ScoredValueScanCursorConverter()))
				.run(args);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor, final byte[] pattern,
										 final long count) {
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor).put("pattern", pattern)
				.put("count", count);
		return new LettuceCommand<>(client, ProtocolCommand.ZREVRANK,
				(cmd)->cmd.zscan(key, new LettuceScanCursor(cursor), new LettuceScanArgs(pattern, count)),
				(new ValueScanCursorConverter.ScoredValueScanCursorConverter()))
				.run(args);
	}

	@Override
	public Double zScore(final byte[] key, final byte[] member) {
		final CommandArguments args = CommandArguments.create("key", key).put("member", member);
		return new LettuceCommand<>(client, ProtocolCommand.ZSCORE, (cmd)->cmd.zscore(key, member), (v)->v)
				.run(args);
	}

	@Override
	public Set<String> zUnion(final String... keys) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys);
		return new LettuceCommand<Set<String>, Set<String>>(client, ProtocolCommand.ZUNION)
				.run(args);
	}

	@Override
	public Set<byte[]> zUnion(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys);
		return new LettuceCommand<Set<byte[]>, Set<byte[]>>(client, ProtocolCommand.ZUNION)
				.run(args);
	}

	@Override
	public Set<String> zUnion(final String[] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys).put("aggregate", aggregate);
		return new LettuceCommand<Set<String>, Set<String>>(client, ProtocolCommand.ZUNION)
				.run(args);
	}

	@Override
	public Set<byte[]> zUnion(final byte[][] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys).put("aggregate", aggregate);
		return new LettuceCommand<Set<byte[]>, Set<byte[]>>(client, ProtocolCommand.ZUNION)
				.run(args);
	}

	@Override
	public Set<String> zUnion(final String[] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys).put("weights", weights);
		return new LettuceCommand<Set<String>, Set<String>>(client, ProtocolCommand.ZUNION)
				.run(args);
	}

	@Override
	public Set<byte[]> zUnion(final byte[][] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys).put("weights", weights);
		return new LettuceCommand<Set<byte[]>, Set<byte[]>>(client, ProtocolCommand.ZUNION)
				.run(args);
	}

	@Override
	public Set<String> zUnion(final String[] keys, final Aggregate aggregate, final double... weights) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys).put("aggregate", aggregate)
				.put("weights", weights);
		return new LettuceCommand<Set<String>, Set<String>>(client, ProtocolCommand.ZUNION)
				.run(args);
	}

	@Override
	public Set<byte[]> zUnion(final byte[][] keys, final Aggregate aggregate, final double... weights) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys).put("aggregate", aggregate)
				.put("weights", weights);
		return new LettuceCommand<Set<byte[]>, Set<byte[]>>(client, ProtocolCommand.ZUNION)
				.run(args);
	}

	@Override
	public Set<Tuple> zUnionWithScores(final String... keys) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys);
		return new LettuceCommand<Set<Tuple>, Set<Tuple>>(client, ProtocolCommand.ZUNION)
				.run(args);
	}

	@Override
	public Set<Tuple> zUnionWithScores(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys);
		return new LettuceCommand<Set<Tuple>, Set<Tuple>>(client, ProtocolCommand.ZUNION)
				.run(args);
	}

	@Override
	public Set<Tuple> zUnionWithScores(final String[] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys).put("aggregate", aggregate);
		return new LettuceCommand<Set<Tuple>, Set<Tuple>>(client, ProtocolCommand.ZUNION)
				.run(args);
	}

	@Override
	public Set<Tuple> zUnionWithScores(final byte[][] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys).put("aggregate", aggregate);
		return new LettuceCommand<Set<Tuple>, Set<Tuple>>(client, ProtocolCommand.ZUNION)
				.run(args);
	}

	@Override
	public Set<Tuple> zUnionWithScores(final String[] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys).put("weights", weights);
		return new LettuceCommand<Set<Tuple>, Set<Tuple>>(client, ProtocolCommand.ZUNION)
				.run(args);
	}

	@Override
	public Set<Tuple> zUnionWithScores(final byte[][] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys).put("weights", weights);
		return new LettuceCommand<Set<Tuple>, Set<Tuple>>(client, ProtocolCommand.ZUNION)
				.run(args);
	}

	@Override
	public Set<Tuple> zUnionWithScores(final String[] keys, final Aggregate aggregate, final double... weights) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys).put("aggregate", aggregate)
				.put("weights", weights);
		return new LettuceCommand<Set<Tuple>, Set<Tuple>>(client, ProtocolCommand.ZUNION)
				.run(args);
	}

	@Override
	public Set<Tuple> zUnionWithScores(final byte[][] keys, final Aggregate aggregate, final double... weights) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys).put("aggregate", aggregate)
				.put("weights", weights);
		return new LettuceCommand<Set<Tuple>, Set<Tuple>>(client, ProtocolCommand.ZUNION)
				.run(args);
	}

	@Override
	public Long zUnionStore(final byte[] destKey, final byte[]... keys) {
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("keys", (Object[]) keys);
		return new LettuceCommand<>(client, ProtocolCommand.ZUNIONSTORE, (cmd)->cmd.zunionstore(destKey, keys),
				(v)->v)
				.run(args);
	}

	@Override
	public Long zUnionStore(final byte[] destKey, final byte[][] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("keys", (Object[]) keys)
				.put("aggregate", aggregate);
		return new LettuceCommand<>(client, ProtocolCommand.ZUNIONSTORE, (cmd)->cmd.zunionstore(destKey,
				new LettuceZStoreArgs(aggregate), keys), (v)->v)
				.run(args);
	}

	@Override
	public Long zUnionStore(final byte[] destKey, final byte[][] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("keys", (Object[]) keys)
				.put("weights", weights);
		return new LettuceCommand<>(client, ProtocolCommand.ZUNIONSTORE, (cmd)->cmd.zunionstore(destKey,
				new LettuceZStoreArgs(weights), keys), (v)->v)
				.run(args);
	}

	@Override
	public Long zUnionStore(final byte[] destKey, final byte[][] keys, final Aggregate aggregate,
							final double... weights) {
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("keys", (Object[]) keys)
				.put("aggregate", aggregate).put("weights", weights);
		return new LettuceCommand<>(client, ProtocolCommand.ZUNIONSTORE, (cmd)->cmd.zunionstore(destKey,
				new LettuceZStoreArgs(aggregate, weights), keys), (v)->v)
				.run(args);
	}

	private Long zAdd(final String key, final Map<String, Double> members, final LettuceZAddArgs zAddArgs,
					  final CommandArguments args) {
		final ScoredValue<byte[]>[] scoredValues = new ScoredValue[members.size()];
		int i = 0;

		for(Map.Entry<String, Double> e : members.entrySet()){
			scoredValues[i++] = ScoredValue.fromNullable(e.getValue(), SafeEncoder.encode(e.getKey()));
		}

		return new LettuceCommand<>(client, ProtocolCommand.ZADD,
				(cmd)->zAddArgs == null ? cmd.zadd(SafeEncoder.encode(key), scoredValues) :
						cmd.zadd(SafeEncoder.encode(key), zAddArgs, scoredValues), (v)->v)
				.run(args);
	}

	private Long zAdd(final byte[] key, final Map<byte[], Double> members, final LettuceZAddArgs zAddArgs,
					  final CommandArguments args) {
		final ScoredValue<byte[]>[] scoredValues = new ScoredValue[members.size()];
		int i = 0;

		for(Map.Entry<byte[], Double> e : members.entrySet()){
			scoredValues[i++] = ScoredValue.fromNullable(e.getValue(), e.getKey());
		}

		return new LettuceCommand<>(client, ProtocolCommand.ZADD, (cmd)->zAddArgs == null ? cmd.zadd(key,
				scoredValues) : cmd.zadd(key, zAddArgs, scoredValues), (v)->v)
				.run(args);
	}

}
