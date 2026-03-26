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
import com.buession.redis.core.Keyword;
import com.buession.redis.core.TimeSeriesElement;
import com.buession.redis.core.TimeSeriesInfo;
import com.buession.redis.core.TimeSeriesMGetElement;
import com.buession.redis.core.AggregationType;
import com.buession.redis.core.TimeSeriesMRangeElement;
import com.buession.redis.core.command.Command;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.TimeSeriesCommands;
import com.buession.redis.core.command.args.timeseries.AddArgument;
import com.buession.redis.core.command.args.timeseries.AlertArgument;
import com.buession.redis.core.command.args.timeseries.CreateArgument;
import com.buession.redis.core.command.args.timeseries.DecrByArgument;
import com.buession.redis.core.command.args.timeseries.IncrByArgument;
import com.buession.redis.core.command.args.timeseries.TSElement;
import com.buession.redis.core.command.args.timeseries.TSMGetAegument;
import com.buession.redis.core.command.args.timeseries.TSMRangeArgument;
import com.buession.redis.core.command.args.timeseries.TSRangeArgument;

import java.util.List;
import java.util.Map;

/**
 * Lettuce TimeSeries 命令
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public final class LettuceTimeSeriesCommands extends AbstractLettuceRedisCommands implements TimeSeriesCommands {

	public LettuceTimeSeriesCommands(final LettuceRedisClient client) {
		super(client);
	}

	@Override
	public Long tsAdd(final String key, final long timestamp, final double value) {
		final CommandArguments args = CommandArguments.create(key).add(timestamp).add(value);
		return executeCommand(Command.TS_ADD, args);
	}

	@Override
	public Long tsAdd(final byte[] key, final long timestamp, final double value) {
		final CommandArguments args = CommandArguments.create(key).add(timestamp).add(value);
		return executeCommand(Command.TS_ADD, args);
	}

	@Override
	public Long tsAdd(final String key, final long timestamp, final double value, final AddArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(timestamp).add(value).add(argument);
		return executeCommand(Command.TS_ADD, args);
	}

	@Override
	public Long tsAdd(final byte[] key, final long timestamp, final double value, final AddArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(timestamp).add(value).add(argument);
		return executeCommand(Command.TS_ADD, args);
	}

	@Override
	public Status tsAlert(final String key, final AlertArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(argument);
		return executeCommand(Command.TS_ALTER, args);
	}

	@Override
	public Status tsAlert(final byte[] key, final AlertArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(argument);
		return executeCommand(Command.TS_ALTER, args);
	}

	@Override
	public Status tsCreate(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.TS_CREATE, args);
	}

	@Override
	public Status tsCreate(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.TS_CREATE, args);
	}

	@Override
	public Status tsCreate(final String key, final CreateArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(argument);
		return executeCommand(Command.TS_CREATE, args);
	}

	@Override
	public Status tsCreate(final byte[] key, final CreateArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(argument);
		return executeCommand(Command.TS_CREATE, args);
	}

	@Override
	public Status tsCreateRule(final String sourceKey, final String destKey, final AggregationType aggregationType,
							   final long timeBucket) {
		final CommandArguments args = CommandArguments.create(destKey, sourceKey).add("AGGREGATION", aggregationType)
				.add(timeBucket);
		return executeCommand(Command.TS_CREATERULE, args);
	}

	@Override
	public Status tsCreateRule(final byte[] sourceKey, final byte[] destKey, final AggregationType aggregationType,
							   final long timeBucket) {
		final CommandArguments args = CommandArguments.create(destKey, sourceKey).add("AGGREGATION", aggregationType)
				.add(timeBucket);
		return executeCommand(Command.TS_CREATERULE, args);
	}

	@Override
	public Status tsCreateRule(final String sourceKey, final String destKey, final AggregationType aggregationType,
							   final long timeBucket, final long alignTimestamp) {
		final CommandArguments args = CommandArguments.create(destKey, sourceKey).add("AGGREGATION", aggregationType)
				.add(timeBucket, alignTimestamp);
		return executeCommand(Command.TS_CREATERULE, args);
	}

	@Override
	public Status tsCreateRule(final byte[] sourceKey, final byte[] destKey, final AggregationType aggregationType,
							   final long timeBucket, final long alignTimestamp) {
		final CommandArguments args = CommandArguments.create(destKey, sourceKey).add("AGGREGATION", aggregationType)
				.add(timeBucket, alignTimestamp);
		return executeCommand(Command.TS_CREATERULE, args);
	}

	@Override
	public Long tsDecrBy(final String key, final double value) {
		final CommandArguments args = CommandArguments.create(key, value);
		return executeCommand(Command.TS_DECRBY, args);
	}

	@Override
	public Long tsDecrBy(final byte[] key, final double value) {
		final CommandArguments args = CommandArguments.create(key, value);
		return executeCommand(Command.TS_DECRBY, args);
	}

	@Override
	public Long tsDecrBy(final String key, final double value, final DecrByArgument argument) {
		final CommandArguments args = CommandArguments.create(key, value).add(argument);
		return executeCommand(Command.TS_DECRBY, args);
	}

	@Override
	public Long tsDecrBy(final byte[] key, final double value, final DecrByArgument argument) {
		final CommandArguments args = CommandArguments.create(key, value).add(argument);
		return executeCommand(Command.TS_DECRBY, args);
	}

	@Override
	public Long tsDel(final String key, final long fromTimestamp, final long toTimestamp) {
		final CommandArguments args = CommandArguments.create(key).add(fromTimestamp, toTimestamp);
		return executeCommand(Command.TS_DEL, args);
	}

	@Override
	public Long tsDel(final byte[] key, final long fromTimestamp, final long toTimestamp) {
		final CommandArguments args = CommandArguments.create(key).add(fromTimestamp, toTimestamp);
		return executeCommand(Command.TS_DEL, args);
	}

	@Override
	public Status tsDeleteRule(final String sourceKey, final String destKey) {
		final CommandArguments args = CommandArguments.create(sourceKey, destKey);
		return executeCommand(Command.TS_DELETERULE, args);
	}

	@Override
	public Status tsDeleteRule(final byte[] sourceKey, final byte[] destKey) {
		final CommandArguments args = CommandArguments.create(sourceKey, destKey);
		return executeCommand(Command.TS_DELETERULE, args);
	}

	@Override
	public TimeSeriesElement tsGet(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.TS_GET, args);
	}

	@Override
	public TimeSeriesElement tsGet(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.TS_GET, args);
	}

	@Override
	public TimeSeriesElement tsGet(final String key, final boolean latest) {
		final CommandArguments args = CommandArguments.create(key).add(latest ? "LATEST" : null);
		return executeCommand(Command.TS_GET, args);
	}

	@Override
	public TimeSeriesElement tsGet(final byte[] key, final boolean latest) {
		final CommandArguments args = CommandArguments.create(key).add(latest ? "LATEST" : null);
		return executeCommand(Command.TS_GET, args);
	}

	@Override
	public Long tsIncrBy(final String key, final double value) {
		final CommandArguments args = CommandArguments.create(key, value);
		return executeCommand(Command.TS_INCRBY, args);
	}

	@Override
	public Long tsIncrBy(final byte[] key, final double value) {
		final CommandArguments args = CommandArguments.create(key, value);
		return executeCommand(Command.TS_INCRBY, args);
	}

	@Override
	public Long tsIncrBy(final String key, final double value, final IncrByArgument argument) {
		final CommandArguments args = CommandArguments.create(key, value).add(argument);
		return executeCommand(Command.TS_INCRBY, args);
	}

	@Override
	public Long tsIncrBy(final byte[] key, final double value, final IncrByArgument argument) {
		final CommandArguments args = CommandArguments.create(key, value).add(argument);
		return executeCommand(Command.TS_INCRBY, args);
	}

	@Override
	public TimeSeriesInfo tsInfo(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.TS_INFO, args);
	}

	@Override
	public TimeSeriesInfo tsInfo(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.TS_INFO, args);
	}

	@Override
	public TimeSeriesInfo tsInfo(final String key, final boolean debug) {
		final CommandArguments args = CommandArguments.create(key).add(debug ? "DEBUG" : null);
		return executeCommand(Command.TS_INFO, args);
	}

	@Override
	public TimeSeriesInfo tsInfo(final byte[] key, final boolean debug) {
		final CommandArguments args = CommandArguments.create(key).add(debug ? "DEBUG" : null);
		return executeCommand(Command.TS_INFO, args);
	}

	@Override
	public List<Long> tsMAdd(final TSElement... values) {
		final CommandArguments args = CommandArguments.create().add(values);
		return executeCommand(Command.TS_MADD, args);
	}

	@Override
	public Map<String, TimeSeriesMGetElement> tsMGet(final String... filters) {
		final CommandArguments args = CommandArguments.create("FILTER").add(filters);
		return executeCommand(Command.TS_MGET, args);
	}

	@Override
	public Map<byte[], TimeSeriesMGetElement> tsMGet(final byte[]... filters) {
		final CommandArguments args = CommandArguments.create("FILTER").add(filters);
		return executeCommand(Command.TS_MGET, args);
	}

	@Override
	public Map<String, TimeSeriesMGetElement> tsMGet(final TSMGetAegument argument, final String... filters) {
		final CommandArguments args = CommandArguments.create(argument).add("FILTER").add(filters);
		return executeCommand(Command.TS_MGET, args);
	}

	@Override
	public Map<byte[], TimeSeriesMGetElement> tsMGet(final TSMGetAegument argument, final byte[]... filters) {
		final CommandArguments args = CommandArguments.create(argument).add("FILTER").add(filters);
		return executeCommand(Command.TS_MGET, args);
	}

	@Override
	public Map<String, TimeSeriesMRangeElement> tsMRange(final long fromTimestamp, final long toTimestamp,
														 final String... filters) {
		final CommandArguments args = CommandArguments.create(fromTimestamp, toTimestamp).add("FILTER").add(filters);
		return executeCommand(Command.TS_MRANGE, args);
	}

	@Override
	public Map<byte[], TimeSeriesMRangeElement> tsMRange(final long fromTimestamp, final long toTimestamp,
														 final byte[]... filters) {
		final CommandArguments args = CommandArguments.create(fromTimestamp, toTimestamp).add("FILTER").add(filters);
		return executeCommand(Command.TS_MRANGE, args);
	}

	@Override
	public Map<String, TimeSeriesMRangeElement> tsMRange(final long fromTimestamp, final long toTimestamp,
														 final TSMRangeArgument argument, final String... filters) {
		final CommandArguments args =
				CommandArguments.create(fromTimestamp, toTimestamp).add("FILTER").add(filters).add(argument);
		return executeCommand(Command.TS_MRANGE, args);
	}

	@Override
	public Map<byte[], TimeSeriesMRangeElement> tsMRange(final long fromTimestamp, final long toTimestamp,
														 final TSMRangeArgument argument, final byte[]... filters) {
		final CommandArguments args =
				CommandArguments.create(fromTimestamp, toTimestamp).add("FILTER").add(filters).add(argument);
		return executeCommand(Command.TS_MRANGE, args);
	}

	@Override
	public Map<String, TimeSeriesMRangeElement> tsMRange(final long fromTimestamp, final long toTimestamp,
														 final TSMRangeArgument argument, final int count,
														 final String... filters) {
		final CommandArguments args = CommandArguments.create(fromTimestamp, toTimestamp).add("FILTER").add(filters)
				.add(argument).add(Keyword.Common.COUNT, count);
		return executeCommand(Command.TS_MRANGE, args);
	}

	@Override
	public Map<byte[], TimeSeriesMRangeElement> tsMRange(final long fromTimestamp, final long toTimestamp,
														 final TSMRangeArgument argument, final int count,
														 final byte[]... filters) {
		final CommandArguments args = CommandArguments.create(fromTimestamp, toTimestamp).add("FILTER").add(filters)
				.add(argument).add(Keyword.Common.COUNT, count);
		return executeCommand(Command.TS_MRANGE, args);
	}

	@Override
	public Map<String, TimeSeriesMRangeElement> tsMRange(final long fromTimestamp, final long toTimestamp,
														 final int count, final String... filters) {
		final CommandArguments args = CommandArguments.create(fromTimestamp, toTimestamp).add("FILTER").add(filters)
				.add(Keyword.Common.COUNT, count);
		return executeCommand(Command.TS_MRANGE, args);
	}

	@Override
	public Map<byte[], TimeSeriesMRangeElement> tsMRange(final long fromTimestamp, final long toTimestamp,
														 final int count, final byte[]... filters) {
		final CommandArguments args = CommandArguments.create(fromTimestamp, toTimestamp).add("FILTER").add(filters)
				.add(Keyword.Common.COUNT, count);
		return executeCommand(Command.TS_MRANGE, args);
	}

	@Override
	public Map<String, TimeSeriesMRangeElement> tsMRevRange(final long fromTimestamp, final long toTimestamp,
															final String... filters) {
		final CommandArguments args = CommandArguments.create(fromTimestamp, toTimestamp).add("FILTER").add(filters);
		return executeCommand(Command.TS_MREVRANGE, args);
	}

	@Override
	public Map<byte[], TimeSeriesMRangeElement> tsMRevRange(final long fromTimestamp, final long toTimestamp,
															final byte[]... filters) {
		final CommandArguments args = CommandArguments.create(fromTimestamp, toTimestamp).add("FILTER").add(filters);
		return executeCommand(Command.TS_MREVRANGE, args);
	}

	@Override
	public Map<String, TimeSeriesMRangeElement> tsMRevRange(final long fromTimestamp, final long toTimestamp,
															final TSMRangeArgument argument, final String... filters) {
		final CommandArguments args =
				CommandArguments.create(fromTimestamp, toTimestamp).add("FILTER").add(filters).add(argument);
		return executeCommand(Command.TS_MREVRANGE, args);
	}

	@Override
	public Map<byte[], TimeSeriesMRangeElement> tsMRevRange(final long fromTimestamp, final long toTimestamp,
															final TSMRangeArgument argument, final byte[]... filters) {
		final CommandArguments args =
				CommandArguments.create(fromTimestamp, toTimestamp).add("FILTER").add(filters).add(argument);
		return executeCommand(Command.TS_MREVRANGE, args);
	}

	@Override
	public Map<String, TimeSeriesMRangeElement> tsMRevRange(final long fromTimestamp, final long toTimestamp,
															final TSMRangeArgument argument, final int count,
															final String... filters) {
		final CommandArguments args = CommandArguments.create(fromTimestamp, toTimestamp).add("FILTER").add(filters)
				.add(argument).add(Keyword.Common.COUNT, count);
		return executeCommand(Command.TS_MREVRANGE, args);
	}

	@Override
	public Map<byte[], TimeSeriesMRangeElement> tsMRevRange(final long fromTimestamp, final long toTimestamp,
															final TSMRangeArgument argument, final int count,
															final byte[]... filters) {
		final CommandArguments args = CommandArguments.create(fromTimestamp, toTimestamp).add("FILTER").add(filters)
				.add(argument).add(Keyword.Common.COUNT, count);
		return executeCommand(Command.TS_MREVRANGE, args);
	}

	@Override
	public Map<String, TimeSeriesMRangeElement> tsMRevRange(final long fromTimestamp, final long toTimestamp,
															final int count, final String... filters) {
		final CommandArguments args = CommandArguments.create(fromTimestamp, toTimestamp).add("FILTER").add(filters)
				.add(Keyword.Common.COUNT, count);
		return executeCommand(Command.TS_MREVRANGE, args);
	}

	@Override
	public Map<byte[], TimeSeriesMRangeElement> tsMRevRange(final long fromTimestamp, final long toTimestamp,
															final int count, final byte[]... filters) {
		final CommandArguments args = CommandArguments.create(fromTimestamp, toTimestamp).add("FILTER").add(filters)
				.add(Keyword.Common.COUNT, count);
		return executeCommand(Command.TS_MREVRANGE, args);
	}

	@Override
	public List<String> tsQueryIndex(final String... filters) {
		final CommandArguments args = CommandArguments.create(filters);
		return executeCommand(Command.TS_QUERYINDEX, args);
	}

	@Override
	public List<byte[]> tsQueryIndex(final byte[]... filters) {
		final CommandArguments args = CommandArguments.create(filters);
		return executeCommand(Command.TS_QUERYINDEX, args);
	}

	@Override
	public List<TimeSeriesElement> tsRange(final String key, final long fromTimestamp, final long toTimestamp) {
		final CommandArguments args = CommandArguments.create(key).add(fromTimestamp, toTimestamp);
		return executeCommand(Command.TS_RANGE, args);
	}

	@Override
	public List<TimeSeriesElement> tsRange(final byte[] key, final long fromTimestamp, final long toTimestamp) {
		final CommandArguments args = CommandArguments.create(key).add(fromTimestamp, toTimestamp);
		return executeCommand(Command.TS_RANGE, args);
	}

	@Override
	public List<TimeSeriesElement> tsRange(final String key, final long fromTimestamp, final long toTimestamp,
										   final TSRangeArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(fromTimestamp, toTimestamp).add(argument);
		return executeCommand(Command.TS_RANGE, args);
	}

	@Override
	public List<TimeSeriesElement> tsRange(final byte[] key, final long fromTimestamp, final long toTimestamp,
										   final TSRangeArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(fromTimestamp, toTimestamp).add(argument);
		return executeCommand(Command.TS_RANGE, args);
	}

	@Override
	public List<TimeSeriesElement> tsRange(final String key, final long fromTimestamp, final long toTimestamp,
										   final TSRangeArgument argument, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(fromTimestamp, toTimestamp).add(argument)
				.add(Keyword.Common.COUNT, count);
		return executeCommand(Command.TS_RANGE, args);
	}

	@Override
	public List<TimeSeriesElement> tsRange(final byte[] key, final long fromTimestamp, final long toTimestamp,
										   final TSRangeArgument argument, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(fromTimestamp, toTimestamp).add(argument)
				.add(Keyword.Common.COUNT, count);
		return executeCommand(Command.TS_RANGE, args);
	}

	@Override
	public List<TimeSeriesElement> tsRange(final String key, final long fromTimestamp, final long toTimestamp,
										   final int count) {
		final CommandArguments args = CommandArguments.create(key).add(fromTimestamp, toTimestamp)
				.add(Keyword.Common.COUNT, count);
		return executeCommand(Command.TS_RANGE, args);
	}

	@Override
	public List<TimeSeriesElement> tsRange(final byte[] key, final long fromTimestamp, final long toTimestamp,
										   final int count) {
		final CommandArguments args = CommandArguments.create(key).add(fromTimestamp, toTimestamp)
				.add(Keyword.Common.COUNT, count);
		return executeCommand(Command.TS_RANGE, args);
	}

	@Override
	public List<TimeSeriesElement> tsRevRange(final String key, final long fromTimestamp, final long toTimestamp) {
		final CommandArguments args = CommandArguments.create(key).add(fromTimestamp, toTimestamp);
		return executeCommand(Command.TS_REVRANGE, args);
	}

	@Override
	public List<TimeSeriesElement> tsRevRange(final byte[] key, final long fromTimestamp, final long toTimestamp) {
		final CommandArguments args = CommandArguments.create(key).add(fromTimestamp, toTimestamp);
		return executeCommand(Command.TS_REVRANGE, args);
	}

	@Override
	public List<TimeSeriesElement> tsRevRange(final String key, final long fromTimestamp, final long toTimestamp,
											  final TSRangeArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(fromTimestamp, toTimestamp).add(argument);
		return executeCommand(Command.TS_REVRANGE, args);
	}

	@Override
	public List<TimeSeriesElement> tsRevRange(final byte[] key, final long fromTimestamp, final long toTimestamp,
											  final TSRangeArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(fromTimestamp, toTimestamp).add(argument);
		return executeCommand(Command.TS_REVRANGE, args);
	}

	@Override
	public List<TimeSeriesElement> tsRevRange(final String key, final long fromTimestamp, final long toTimestamp,
											  final TSRangeArgument argument, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(fromTimestamp, toTimestamp).add(argument)
				.add(Keyword.Common.COUNT, count);
		return executeCommand(Command.TS_REVRANGE, args);
	}

	@Override
	public List<TimeSeriesElement> tsRevRange(final byte[] key, final long fromTimestamp, final long toTimestamp,
											  final TSRangeArgument argument, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(fromTimestamp, toTimestamp).add(argument)
				.add(Keyword.Common.COUNT, count);
		return executeCommand(Command.TS_REVRANGE, args);
	}

	@Override
	public List<TimeSeriesElement> tsRevRange(final String key, final long fromTimestamp, final long toTimestamp,
											  final int count) {
		final CommandArguments args = CommandArguments.create(key).add(fromTimestamp, toTimestamp)
				.add(Keyword.Common.COUNT, count);
		return executeCommand(Command.TS_REVRANGE, args);
	}

	@Override
	public List<TimeSeriesElement> tsRevRange(final byte[] key, final long fromTimestamp, final long toTimestamp,
											  final int count) {
		final CommandArguments args = CommandArguments.create(key).add(fromTimestamp, toTimestamp)
				.add(Keyword.Common.COUNT, count);
		return executeCommand(Command.TS_REVRANGE, args);
	}

}
