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

import com.buession.lang.Status;
import com.buession.redis.client.jedis.JedisRedisClient;
import com.buession.redis.core.TdigestInfo;
import com.buession.redis.core.command.RedisCommand;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.TDigestCommands;
import com.buession.redis.core.internal.convert.jedis.response.TdigestInfoConverter;
import com.buession.redis.core.internal.convert.response.OkStatusConverter;
import com.buession.redis.core.internal.jedis.args.JedisTDigestMergeParams;
import com.buession.redis.utils.SafeEncoder;

import java.util.List;

/**
 * Jedis T-Digest 命令
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public final class JedisTDigestCommands extends AbstractJedisRedisCommands implements TDigestCommands {

	public JedisTDigestCommands(final JedisRedisClient client) {
		super(client);
	}

	@Override
	public Status tdigestAdd(final String key, final double... values) {
		final CommandArguments args = CommandArguments.create(key).add(values);
		return executeCommand(RedisCommand.TDIGEST_ADD, args, (cmd)->cmd.tdigestAdd(key, values),
				(cmd)->cmd.tdigestAdd(key, values), (cmd)->cmd.tdigestAdd(key, values),
				new OkStatusConverter());
	}

	@Override
	public Status tdigestAdd(final byte[] key, final double... values) {
		return tdigestAdd(SafeEncoder.encode(key), values);
	}

	@Override
	public List<Double> tdigestByRank(final String key, final long... ranks) {
		final CommandArguments args = CommandArguments.create(key).add(ranks);
		return executeCommand(RedisCommand.TDIGEST_BYRANK, args, (cmd)->cmd.tdigestByRank(key, ranks),
				(cmd)->cmd.tdigestByRank(key, ranks), (cmd)->cmd.tdigestByRank(key, ranks));
	}

	@Override
	public List<Double> tdigestByRank(final byte[] key, final long... ranks) {
		return tdigestByRank(SafeEncoder.encode(key), ranks);
	}

	@Override
	public List<Double> tdigestByRevRank(final String key, final long... ranks) {
		final CommandArguments args = CommandArguments.create(key).add(ranks);
		return executeCommand(RedisCommand.TDIGEST_BYREVRANK, args, (cmd)->cmd.tdigestByRevRank(key, ranks),
				(cmd)->cmd.tdigestByRevRank(key, ranks), (cmd)->cmd.tdigestByRevRank(key, ranks));
	}

	@Override
	public List<Double> tdigestByRevRank(final byte[] key, final long... ranks) {
		return tdigestByRevRank(SafeEncoder.encode(key), ranks);
	}

	@Override
	public List<Double> tdigestCdf(final String key, final double... values) {
		final CommandArguments args = CommandArguments.create(key).add(values);
		return executeCommand(RedisCommand.TDIGEST_CDF, args, (cmd)->cmd.tdigestCDF(key, values),
				(cmd)->cmd.tdigestCDF(key, values), (cmd)->cmd.tdigestCDF(key, values));
	}

	@Override
	public List<Double> tdigestCdf(final byte[] key, final double... values) {
		return tdigestCdf(SafeEncoder.encode(key), values);
	}

	@Override
	public Status tdigestCreate(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.TDIGEST_CREATE, args, (cmd)->cmd.tdigestCreate(key),
				(cmd)->cmd.tdigestCreate(key), (cmd)->cmd.tdigestCreate(key), new OkStatusConverter());
	}

	@Override
	public Status tdigestCreate(final byte[] key) {
		return tdigestCreate(SafeEncoder.encode(key));
	}

	@Override
	public Status tdigestCreate(final String key, final int compression) {
		final CommandArguments args = CommandArguments.create(key).add("COMPRESSION", compression);
		return executeCommand(RedisCommand.TDIGEST_CREATE, args, (cmd)->cmd.tdigestCreate(key, compression),
				(cmd)->cmd.tdigestCreate(key, compression), (cmd)->cmd.tdigestCreate(key, compression),
				new OkStatusConverter());
	}

	@Override
	public Status tdigestCreate(final byte[] key, final int compression) {
		return tdigestCreate(SafeEncoder.encode(key), compression);
	}

	@Override
	public TdigestInfo tdigestInfo(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.TDIGEST_INFO, args, (cmd)->cmd.tdigestInfo(key),
				(cmd)->cmd.tdigestInfo(key), (cmd)->cmd.tdigestInfo(key), new TdigestInfoConverter());
	}

	@Override
	public TdigestInfo tdigestInfo(final byte[] key) {
		return tdigestInfo(SafeEncoder.encode(key));
	}

	@Override
	public Double tdigestMax(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.TDIGEST_MAX, args, (cmd)->cmd.tdigestMax(key),
				(cmd)->cmd.tdigestMax(key), (cmd)->cmd.tdigestMax(key));
	}

	@Override
	public Double tdigestMax(final byte[] key) {
		return tdigestMax(SafeEncoder.encode(key));
	}

	@Override
	public Status tdigestMerge(final String destKey, final String... keys) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys.length).add(keys);
		return executeCommand(RedisCommand.TDIGEST_MERGE, args, (cmd)->cmd.tdigestMerge(destKey, keys),
				(cmd)->cmd.tdigestMerge(destKey, keys),
				(cmd)->cmd.tdigestMerge(destKey, keys),
				new OkStatusConverter());
	}

	@Override
	public Status tdigestMerge(final byte[] destKey, final byte[]... keys) {
		return tdigestMerge(SafeEncoder.encode(destKey), SafeEncoder.encode(keys));
	}

	@Override
	public Status tdigestMerge(final String destKey, final String[] keys, final int compression) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys.length).add(keys)
				.add("COMPRESSION", compression);
		return executeCommand(RedisCommand.TDIGEST_MERGE, args,
				(cmd)->cmd.tdigestMerge(new JedisTDigestMergeParams(compression), destKey, keys),
				(cmd)->cmd.tdigestMerge(new JedisTDigestMergeParams(compression), destKey, keys),
				(cmd)->cmd.tdigestMerge(new JedisTDigestMergeParams(compression), destKey, keys),
				new OkStatusConverter());
	}

	@Override
	public Status tdigestMerge(final byte[] destKey, final byte[][] keys, final int compression) {
		return tdigestMerge(SafeEncoder.encode(destKey), SafeEncoder.encode(keys), compression);
	}

	@Override
	public Status tdigestMerge(final String destKey, final String[] keys, final int compression,
	                           final boolean override) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys.length).add(keys)
				.add("COMPRESSION", compression).add(override ? "OVERRIDE" : null);
		return executeCommand(RedisCommand.TDIGEST_MERGE, args,
				(cmd)->cmd.tdigestMerge(new JedisTDigestMergeParams(compression, override), destKey,
						keys),
				(cmd)->cmd.tdigestMerge(new JedisTDigestMergeParams(compression, override), destKey,
						keys),
				(cmd)->cmd.tdigestMerge(new JedisTDigestMergeParams(compression, override), destKey,
						keys), new OkStatusConverter());
	}

	@Override
	public Status tdigestMerge(final byte[] destKey, final byte[][] keys, final int compression,
	                           final boolean override) {
		return tdigestMerge(SafeEncoder.encode(destKey), SafeEncoder.encode(keys), compression, override);
	}

	@Override
	public Status tdigestMerge(final String destKey, final String[] keys, final boolean override) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys.length).add(keys)
				.add(override ? "OVERRIDE" : null);
		return executeCommand(RedisCommand.TDIGEST_MERGE, args,
				(cmd)->cmd.tdigestMerge(new JedisTDigestMergeParams(override), destKey, keys),
				(cmd)->cmd.tdigestMerge(new JedisTDigestMergeParams(override), destKey, keys),
				(cmd)->cmd.tdigestMerge(new JedisTDigestMergeParams(override), destKey, keys),
				new OkStatusConverter());
	}

	@Override
	public Status tdigestMerge(final byte[] destKey, final byte[][] keys, final boolean override) {
		return tdigestMerge(SafeEncoder.encode(destKey), SafeEncoder.encode(keys), override);
	}

	@Override
	public Double tdigestMin(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.TDIGEST_MIN, args, (cmd)->cmd.tdigestMin(key),
				(cmd)->cmd.tdigestMin(key), (cmd)->cmd.tdigestMin(key));
	}

	@Override
	public Double tdigestMin(final byte[] key) {
		return tdigestMin(SafeEncoder.encode(key));
	}

	@Override
	public List<Double> tdigestQuantile(final String key, final double... quantiles) {
		final CommandArguments args = CommandArguments.create(key).add(quantiles);
		return executeCommand(RedisCommand.TDIGEST_QUANTILE, args, (cmd)->cmd.tdigestQuantile(key, quantiles),
				(cmd)->cmd.tdigestQuantile(key, quantiles), (cmd)->cmd.tdigestQuantile(key, quantiles));
	}

	@Override
	public List<Double> tdigestQuantile(final byte[] key, final double... quantiles) {
		return tdigestQuantile(SafeEncoder.encode(key), quantiles);
	}

	@Override
	public List<Long> tdigestRank(final String key, final double... values) {
		final CommandArguments args = CommandArguments.create(key).add(values);
		return executeCommand(RedisCommand.TDIGEST_RANK, args, (cmd)->cmd.tdigestRank(key, values),
				(cmd)->cmd.tdigestRank(key, values), (cmd)->cmd.tdigestRank(key, values));
	}

	@Override
	public List<Long> tdigestRank(final byte[] key, final double... values) {
		return tdigestRank(SafeEncoder.encode(key), values);
	}

	@Override
	public Status tdigestReset(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.TDIGEST_RESET, args, (cmd)->cmd.tdigestReset(key),
				(cmd)->cmd.tdigestReset(key), (cmd)->cmd.tdigestReset(key),
				new OkStatusConverter());
	}

	@Override
	public Status tdigestReset(final byte[] key) {
		return tdigestReset(SafeEncoder.encode(key));
	}

	@Override
	public List<Long> tdigestRevRank(final String key, final double... values) {
		final CommandArguments args = CommandArguments.create(key).add(values);
		return executeCommand(RedisCommand.TDIGEST_REVRANK, args, (cmd)->cmd.tdigestRevRank(key, values),
				(cmd)->cmd.tdigestRevRank(key, values), (cmd)->cmd.tdigestRevRank(key, values));
	}

	@Override
	public List<Long> tdigestRevRank(final byte[] key, final double... values) {
		return tdigestRevRank(SafeEncoder.encode(key), values);
	}

	@Override
	public Double tdigestTrimmedMean(final String key, final double lowCutQuantile, final double highCutQuantile) {
		final CommandArguments args = CommandArguments.create(key).add(lowCutQuantile).add(highCutQuantile);
		return executeCommand(RedisCommand.TDIGEST_TRIMMED_MEAN, args,
				(cmd)->cmd.tdigestTrimmedMean(key, lowCutQuantile, highCutQuantile),
				(cmd)->cmd.tdigestTrimmedMean(key, lowCutQuantile, highCutQuantile),
				(cmd)->cmd.tdigestTrimmedMean(key, lowCutQuantile, highCutQuantile));
	}

	@Override
	public Double tdigestTrimmedMean(final byte[] key, final double lowCutQuantile, final double highCutQuantile) {
		return tdigestTrimmedMean(SafeEncoder.encode(key), lowCutQuantile, highCutQuantile);
	}

}
