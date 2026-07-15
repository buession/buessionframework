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

import com.buession.lang.Status;
import com.buession.redis.client.lettuce.LettuceRedisClient;
import com.buession.redis.core.TdigestInfo;
import com.buession.redis.core.command.RedisCommand;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.TDigestCommands;

import java.util.List;

/**
 * Lettuce T-Digest 命令
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public final class LettuceTDigestCommands extends AbstractLettuceRedisCommands implements TDigestCommands {

	public LettuceTDigestCommands(final LettuceRedisClient client) {
		super(client);
	}

	@Override
	public Status tdigestAdd(final String key, final double... values) {
		final CommandArguments args = CommandArguments.create(key).add(values);
		return executeCommand(RedisCommand.TDIGEST_ADD, args);
	}

	@Override
	public Status tdigestAdd(final byte[] key, final double... values) {
		final CommandArguments args = CommandArguments.create(key).add(values);
		return executeCommand(RedisCommand.TDIGEST_ADD, args);
	}

	@Override
	public List<Double> tdigestByRank(final String key, final long... ranks) {
		final CommandArguments args = CommandArguments.create(key).add(ranks);
		return executeCommand(RedisCommand.TDIGEST_BYRANK, args);
	}

	@Override
	public List<Double> tdigestByRank(final byte[] key, final long... ranks) {
		final CommandArguments args = CommandArguments.create(key).add(ranks);
		return executeCommand(RedisCommand.TDIGEST_BYRANK, args);
	}

	@Override
	public List<Double> tdigestByRevRank(final String key, final long... ranks) {
		final CommandArguments args = CommandArguments.create(key).add(ranks);
		return executeCommand(RedisCommand.TDIGEST_BYREVRANK, args);
	}

	@Override
	public List<Double> tdigestByRevRank(final byte[] key, final long... ranks) {
		final CommandArguments args = CommandArguments.create(key).add(ranks);
		return executeCommand(RedisCommand.TDIGEST_BYREVRANK, args);
	}

	@Override
	public List<Double> tdigestCdf(final String key, final double... values) {
		final CommandArguments args = CommandArguments.create(key).add(values);
		return executeCommand(RedisCommand.TDIGEST_CDF, args);
	}

	@Override
	public List<Double> tdigestCdf(final byte[] key, final double... values) {
		final CommandArguments args = CommandArguments.create(key).add(values);
		return executeCommand(RedisCommand.TDIGEST_CDF, args);
	}

	@Override
	public Status tdigestCreate(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.TDIGEST_CREATE, args);
	}

	@Override
	public Status tdigestCreate(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.TDIGEST_CREATE, args);
	}

	@Override
	public Status tdigestCreate(final String key, final int compression) {
		final CommandArguments args = CommandArguments.create(key).add("COMPRESSION").add(compression);
		return executeCommand(RedisCommand.TDIGEST_CREATE, args);
	}

	@Override
	public Status tdigestCreate(final byte[] key, final int compression) {
		final CommandArguments args = CommandArguments.create(key).add("COMPRESSION").add(compression);
		return executeCommand(RedisCommand.TDIGEST_CREATE, args);
	}

	@Override
	public TdigestInfo tdigestInfo(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.TDIGEST_INFO, args);
	}

	@Override
	public TdigestInfo tdigestInfo(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.TDIGEST_INFO, args);
	}

	@Override
	public Double tdigestMax(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.TDIGEST_MAX, args);
	}

	@Override
	public Double tdigestMax(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.TDIGEST_MAX, args);
	}

	@Override
	public Status tdigestMerge(final String destKey, final String... keys) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys);
		return executeCommand(RedisCommand.TDIGEST_MERGE, args);
	}

	@Override
	public Status tdigestMerge(final byte[] destKey, final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys);
		return executeCommand(RedisCommand.TDIGEST_MERGE, args);
	}

	@Override
	public Status tdigestMerge(final String destKey, final String[] keys, final int compression) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys.length).add(keys)
				.add("COMPRESSION").add(compression);
		return executeCommand(RedisCommand.TDIGEST_MERGE, args);
	}

	@Override
	public Status tdigestMerge(final byte[] destKey, final byte[][] keys, final int compression) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys.length).add(keys)
				.add("COMPRESSION").add(compression);
		return executeCommand(RedisCommand.TDIGEST_MERGE, args);
	}

	@Override
	public Status tdigestMerge(final String destKey, final String[] keys, final int compression,
	                           final boolean override) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys.length).add(keys)
				.add("COMPRESSION").add(compression).add(override ? "OVERRIDE" : null);
		return executeCommand(RedisCommand.TDIGEST_MERGE, args);
	}

	@Override
	public Status tdigestMerge(final byte[] destKey, final byte[][] keys, final int compression,
	                           final boolean override) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys.length).add(keys)
				.add("COMPRESSION").add(compression).add(override ? "OVERRIDE" : null);
		return executeCommand(RedisCommand.TDIGEST_MERGE, args);
	}

	@Override
	public Status tdigestMerge(final String destKey, final String[] keys, final boolean override) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys.length).add(keys)
				.add(override ? "OVERRIDE" : null);
		return executeCommand(RedisCommand.TDIGEST_MERGE, args);
	}

	@Override
	public Status tdigestMerge(final byte[] destKey, final byte[][] keys, final boolean override) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys.length).add(keys)
				.add(override ? "OVERRIDE" : null);
		return executeCommand(RedisCommand.TDIGEST_MERGE, args);
	}

	@Override
	public Double tdigestMin(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.TDIGEST_MIN, args);
	}

	@Override
	public Double tdigestMin(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.TDIGEST_MIN, args);
	}

	@Override
	public List<Double> tdigestQuantile(final String key, final double... quantiles) {
		final CommandArguments args = CommandArguments.create(key).add(quantiles);
		return executeCommand(RedisCommand.TDIGEST_QUANTILE, args);
	}

	@Override
	public List<Double> tdigestQuantile(final byte[] key, final double... quantiles) {
		final CommandArguments args = CommandArguments.create(key).add(quantiles);
		return executeCommand(RedisCommand.TDIGEST_QUANTILE, args);
	}

	@Override
	public List<Long> tdigestRank(final String key, final double... values) {
		final CommandArguments args = CommandArguments.create(key).add(values);
		return executeCommand(RedisCommand.TDIGEST_RANK, args);
	}

	@Override
	public List<Long> tdigestRank(final byte[] key, final double... values) {
		final CommandArguments args = CommandArguments.create(key).add(values);
		return executeCommand(RedisCommand.TDIGEST_RANK, args);
	}

	@Override
	public Status tdigestReset(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.TDIGEST_RESET, args);
	}

	@Override
	public Status tdigestReset(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.TDIGEST_RESET, args);
	}

	@Override
	public List<Long> tdigestRevRank(final String key, final double... values) {
		final CommandArguments args = CommandArguments.create(key).add(values);
		return executeCommand(RedisCommand.TDIGEST_REVRANK, args);
	}

	@Override
	public List<Long> tdigestRevRank(final byte[] key, final double... values) {
		final CommandArguments args = CommandArguments.create(key).add(values);
		return executeCommand(RedisCommand.TDIGEST_REVRANK, args);
	}

	@Override
	public Double tdigestTrimmedMean(final String key, final double lowCutQuantile,
	                                 final double highCutQuantile) {
		final CommandArguments args = CommandArguments.create(key).add(lowCutQuantile).add(highCutQuantile);
		return executeCommand(RedisCommand.TDIGEST_TRIMMED_MEAN, args);
	}

	@Override
	public Double tdigestTrimmedMean(final byte[] key, final double lowCutQuantile,
	                                 final double highCutQuantile) {
		final CommandArguments args = CommandArguments.create(key).add(lowCutQuantile).add(highCutQuantile);
		return executeCommand(RedisCommand.TDIGEST_TRIMMED_MEAN, args);
	}

}
