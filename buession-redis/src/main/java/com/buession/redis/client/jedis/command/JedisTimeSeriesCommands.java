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
import com.buession.redis.core.command.args.timeseries.AggregationType;
import com.buession.redis.core.command.Command;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.TimeSeriesCommands;
import com.buession.redis.core.command.args.timeseries.AddArgument;
import com.buession.redis.core.command.args.timeseries.AlertArgument;
import com.buession.redis.core.command.args.timeseries.CreateArgument;
import com.buession.redis.core.internal.convert.jedis.params.AggregationTypeConverter;
import com.buession.redis.core.internal.convert.response.OkStatusConverter;
import com.buession.redis.core.internal.jedis.args.JedisTSAddParams;
import com.buession.redis.core.internal.jedis.args.JedisTSAlterParams;
import com.buession.redis.core.internal.jedis.args.JedisTSCreateParams;
import com.buession.redis.utils.SafeEncoder;

/**
 * Jedis TimeSeries 命令
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public final class JedisTimeSeriesCommands extends AbstractJedisRedisCommands implements TimeSeriesCommands {

	public JedisTimeSeriesCommands(final JedisRedisClient client) {
		super(client);
	}

	@Override
	public Long tsAdd(final String key, final long timestamp, final double value) {
		final CommandArguments args = CommandArguments.create(key).add(timestamp).add(value);
		return executeCommand(Command.TS_ADD, args, (cmd)->cmd.tsAdd(rawKey(key), timestamp, value));
	}

	@Override
	public Long tsAdd(final byte[] key, final long timestamp, final double value) {
		return tsAdd(SafeEncoder.encode(key), timestamp, value);
	}

	@Override
	public Long tsAdd(final String key, final long timestamp, final double value, final AddArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(timestamp).add(value).add(argument);
		return executeCommand(Command.TS_ADD, args,
				(cmd)->cmd.tsAdd(rawKey(key), timestamp, value, new JedisTSAddParams(argument)));
	}

	@Override
	public Long tsAdd(final byte[] key, final long timestamp, final double value, final AddArgument argument) {
		return tsAdd(SafeEncoder.encode(key), timestamp, value, argument);
	}

	@Override
	public Status tsAlert(final String key, final AlertArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(argument);
		return executeCommand(Command.TS_ALTER, args, (cmd)->cmd.tsAlter(rawKey(key), new JedisTSAlterParams(argument)),
				new OkStatusConverter());
	}

	@Override
	public Status tsAlert(final byte[] key, final AlertArgument argument) {
		return tsAlert(SafeEncoder.encode(key), argument);
	}

	@Override
	public Status tsCreate(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.TS_CREATE, args, (cmd)->cmd.tsCreate(rawKey(key)), new OkStatusConverter());
	}

	@Override
	public Status tsCreate(final byte[] key) {
		return tsCreate(SafeEncoder.encode(key));
	}

	@Override
	public Status tsCreate(final String key, final CreateArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(argument);
		return executeCommand(Command.TS_CREATE, args,
				(cmd)->cmd.tsCreate(rawKey(key), new JedisTSCreateParams(argument)), new OkStatusConverter());
	}

	@Override
	public Status tsCreate(final byte[] key, final CreateArgument argument) {
		return tsCreate(SafeEncoder.encode(key), argument);
	}

	@Override
	public Status tsCreateRule(final String sourceKey, final String destKey, final AggregationType aggregationType,
							   final long timeBucket) {
		final CommandArguments args = CommandArguments.create(destKey, sourceKey).add("AGGREGATION", aggregationType)
				.add(timeBucket);
		final AggregationTypeConverter aggregationTypeConverter = new AggregationTypeConverter();
		return executeCommand(Command.TS_CREATERULE, args, (cmd)->cmd.tsCreateRule(rawKey(sourceKey), rawKey(destKey),
				aggregationTypeConverter.convert(aggregationType), timeBucket), new OkStatusConverter());
	}

	@Override
	public Status tsCreateRule(final byte[] sourceKey, final byte[] destKey, final AggregationType aggregationType,
							   final long timeBucket) {
		return tsCreateRule(SafeEncoder.encode(sourceKey), SafeEncoder.encode(destKey), aggregationType, timeBucket);
	}

	@Override
	public Status tsCreateRule(final String sourceKey, final String destKey, final AggregationType aggregationType,
							   final long timeBucket, final long alignTimestamp) {
		final CommandArguments args = CommandArguments.create(destKey, sourceKey).add("AGGREGATION", aggregationType)
				.add(timeBucket, alignTimestamp);
		final AggregationTypeConverter aggregationTypeConverter = new AggregationTypeConverter();
		return executeCommand(Command.TS_CREATERULE, args, (cmd)->cmd.tsCreateRule(rawKey(sourceKey), rawKey(destKey),
						aggregationTypeConverter.convert(aggregationType), timeBucket, alignTimestamp),
				new OkStatusConverter());
	}

	@Override
	public Status tsCreateRule(final byte[] sourceKey, final byte[] destKey, final AggregationType aggregationType,
							   final long timeBucket, final long alignTimestamp) {
		return tsCreateRule(SafeEncoder.encode(sourceKey), SafeEncoder.encode(destKey), aggregationType, timeBucket,
				alignTimestamp);
	}

	@Override
	public Long tsDecrBy(final String key, final double value) {
		final CommandArguments args = CommandArguments.create(key, value);
		return executeCommand(Command.TS_CREATERULE, args, (cmd)->cmd.tsDecrBy(rawKey(key), value));
	}

	@Override
	public Long tsDecrBy(final byte[] key, final double value) {
		return tsDecrBy(SafeEncoder.encode(key), value);
	}

	@Override
	public Long tsDecrBy(final String key, final double value, final AddArgument argument) {
		return 0L;
	}

	@Override
	public Long tsDecrBy(final byte[] key, final double value, final AddArgument argument) {
		return 0L;
	}
}
