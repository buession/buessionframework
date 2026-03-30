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

import com.buession.core.converter.Converter;
import com.buession.core.converter.ListConverter;
import com.buession.core.converter.MapConverter;
import com.buession.lang.Status;
import com.buession.redis.client.jedis.JedisRedisClient;
import com.buession.redis.core.Keyword;
import com.buession.redis.core.TimeSeriesElement;
import com.buession.redis.core.TimeSeriesInfo;
import com.buession.redis.core.TimeSeriesMGetElement;
import com.buession.redis.core.AggregationType;
import com.buession.redis.core.TimeSeriesMRangeElement;
import com.buession.redis.core.command.RedisCommand;
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
import com.buession.redis.core.internal.convert.StringListBinaryListConverter;
import com.buession.redis.core.internal.convert.jedis.params.AggregationTypeConverter;
import com.buession.redis.core.internal.jedis.args.JedisTSGetParams;
import com.buession.redis.core.internal.convert.jedis.response.TSElementConverter;
import com.buession.redis.core.internal.convert.jedis.response.TSInfoConverter;
import com.buession.redis.core.internal.convert.jedis.response.TSMGetElementConveter;
import com.buession.redis.core.internal.convert.jedis.response.TSMRangeElementsConverter;
import com.buession.redis.core.internal.convert.response.OkStatusConverter;
import com.buession.redis.core.internal.jedis.args.JedisTSAddParams;
import com.buession.redis.core.internal.jedis.args.JedisTSAlterParams;
import com.buession.redis.core.internal.jedis.args.JedisTSCreateParams;
import com.buession.redis.core.internal.jedis.args.JedisTSDecrByParams;
import com.buession.redis.core.internal.jedis.args.JedisTSIncrByParams;
import com.buession.redis.core.internal.jedis.args.JedisTSMGetParams;
import com.buession.redis.core.internal.jedis.args.JedisTSMRangeParams;
import com.buession.redis.core.internal.jedis.args.JedisTSRangeParams;
import com.buession.redis.utils.SafeEncoder;
import redis.clients.jedis.timeseries.TSMGetParams;
import redis.clients.jedis.timeseries.TSMRangeParams;
import redis.clients.jedis.util.KeyValue;

import java.util.List;
import java.util.Map;

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
		return executeCommand(RedisCommand.TS_ADD, args, (cmd)->cmd.tsAdd(rawKey(key), timestamp, value));
	}

	@Override
	public Long tsAdd(final byte[] key, final long timestamp, final double value) {
		return tsAdd(SafeEncoder.encode(key), timestamp, value);
	}

	@Override
	public Long tsAdd(final String key, final long timestamp, final double value, final AddArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(timestamp).add(value).add(argument);
		return executeCommand(RedisCommand.TS_ADD, args,
				(cmd)->cmd.tsAdd(rawKey(key), timestamp, value, new JedisTSAddParams(argument)));
	}

	@Override
	public Long tsAdd(final byte[] key, final long timestamp, final double value, final AddArgument argument) {
		return tsAdd(SafeEncoder.encode(key), timestamp, value, argument);
	}

	@Override
	public Status tsAlert(final String key, final AlertArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(argument);
		return executeCommand(
				RedisCommand.TS_ALTER, args, (cmd)->cmd.tsAlter(rawKey(key), new JedisTSAlterParams(argument)),
				new OkStatusConverter());
	}

	@Override
	public Status tsAlert(final byte[] key, final AlertArgument argument) {
		return tsAlert(SafeEncoder.encode(key), argument);
	}

	@Override
	public Status tsCreate(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.TS_CREATE, args, (cmd)->cmd.tsCreate(rawKey(key)), new OkStatusConverter());
	}

	@Override
	public Status tsCreate(final byte[] key) {
		return tsCreate(SafeEncoder.encode(key));
	}

	@Override
	public Status tsCreate(final String key, final CreateArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(argument);
		return executeCommand(RedisCommand.TS_CREATE, args,
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
		return executeCommand(
				RedisCommand.TS_CREATERULE, args, (cmd)->cmd.tsCreateRule(rawKey(sourceKey), rawKey(destKey),
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
		return executeCommand(
				RedisCommand.TS_CREATERULE, args, (cmd)->cmd.tsCreateRule(rawKey(sourceKey), rawKey(destKey),
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
		return executeCommand(RedisCommand.TS_DECRBY, args, (cmd)->cmd.tsDecrBy(rawKey(key), value));
	}

	@Override
	public Long tsDecrBy(final byte[] key, final double value) {
		return tsDecrBy(SafeEncoder.encode(key), value);
	}

	@Override
	public Long tsDecrBy(final String key, final double value, final DecrByArgument argument) {
		final CommandArguments args = CommandArguments.create(key, value).add(argument);
		return executeCommand(RedisCommand.TS_DECRBY, args,
				(cmd)->cmd.tsDecrBy(rawKey(key), value, new JedisTSDecrByParams(argument)));
	}

	@Override
	public Long tsDecrBy(final byte[] key, final double value, final DecrByArgument argument) {
		return tsDecrBy(SafeEncoder.encode(key), value, argument);
	}

	@Override
	public Long tsDel(final String key, final long fromTimestamp, final long toTimestamp) {
		final CommandArguments args = CommandArguments.create(key).add(fromTimestamp, toTimestamp);
		return executeCommand(RedisCommand.TS_DEL, args, (cmd)->cmd.tsDel(rawKey(key), fromTimestamp, toTimestamp));
	}

	@Override
	public Long tsDel(final byte[] key, final long fromTimestamp, final long toTimestamp) {
		return tsDel(SafeEncoder.encode(key), fromTimestamp, toTimestamp);
	}

	@Override
	public Status tsDeleteRule(final String sourceKey, final String destKey) {
		final CommandArguments args = CommandArguments.create(sourceKey, destKey);
		return executeCommand(
				RedisCommand.TS_DELETERULE, args, (cmd)->cmd.tsDeleteRule(rawKey(sourceKey), rawKey(destKey)),
				new OkStatusConverter());
	}

	@Override
	public Status tsDeleteRule(final byte[] sourceKey, final byte[] destKey) {
		return tsDeleteRule(SafeEncoder.encode(sourceKey), SafeEncoder.encode(destKey));
	}

	@Override
	public TimeSeriesElement tsGet(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.TS_GET, args, (cmd)->cmd.tsGet(rawKey(key)), new TSElementConverter());
	}

	@Override
	public TimeSeriesElement tsGet(final byte[] key) {
		return tsGet(SafeEncoder.encode(key));
	}

	@Override
	public TimeSeriesElement tsGet(final String key, final boolean latest) {
		final CommandArguments args = CommandArguments.create(key).add(latest ? "LATEST" : null);
		return executeCommand(RedisCommand.TS_GET, args, (cmd)->cmd.tsGet(rawKey(key), new JedisTSGetParams(latest)),
				new TSElementConverter());
	}

	@Override
	public TimeSeriesElement tsGet(final byte[] key, final boolean latest) {
		return tsGet(SafeEncoder.encode(key), latest);
	}

	@Override
	public Long tsIncrBy(final String key, final double value) {
		final CommandArguments args = CommandArguments.create(key, value);
		return executeCommand(RedisCommand.TS_INCRBY, args, (cmd)->cmd.tsIncrBy(rawKey(key), value));
	}

	@Override
	public Long tsIncrBy(final byte[] key, final double value) {
		return tsDecrBy(SafeEncoder.encode(key), value);
	}

	@Override
	public Long tsIncrBy(final String key, final double value, final IncrByArgument argument) {
		final CommandArguments args = CommandArguments.create(key, value).add(argument);
		return executeCommand(RedisCommand.TS_INCRBY, args,
				(cmd)->cmd.tsIncrBy(rawKey(key), value, new JedisTSIncrByParams(argument)));
	}

	@Override
	public Long tsIncrBy(final byte[] key, final double value, final IncrByArgument argument) {
		return tsIncrBy(SafeEncoder.encode(key), value, argument);
	}

	@Override
	public TimeSeriesInfo tsInfo(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.TS_INFO, args, (cmd)->cmd.tsInfo(rawKey(key)), new TSInfoConverter());
	}

	@Override
	public TimeSeriesInfo tsInfo(final byte[] key) {
		return tsInfo(SafeEncoder.encode(key));
	}

	@Override
	public TimeSeriesInfo tsInfo(final String key, final boolean debug) {
		final CommandArguments args = CommandArguments.create(key).add(debug ? "DEBUG" : null);
		return executeCommand(RedisCommand.TS_INFO, args, (cmd)->cmd.tsInfoDebug(rawKey(key)), new TSInfoConverter());
	}

	@Override
	public TimeSeriesInfo tsInfo(final byte[] key, final boolean debug) {
		return tsInfo(SafeEncoder.encode(key), debug);
	}

	@Override
	public List<Long> tsMAdd(final TSElement... values) {
		final CommandArguments args = CommandArguments.create().add(values);
		final KeyValue<String, redis.clients.jedis.timeseries.TSElement>[] entries = new KeyValue[values.length];

		for(int i = 0; i < values.length; i++){
			entries[i] = new KeyValue<>(rawKey(values[i].getKey()),
					new redis.clients.jedis.timeseries.TSElement(values[i].getTimestamp(), values[i].getValue()));
		}

		return executeCommand(RedisCommand.TS_MADD, args, (cmd)->cmd.tsMAdd(entries));
	}

	@Override
	public Map<String, TimeSeriesMGetElement> tsMGet(final String... filters) {
		final CommandArguments args = CommandArguments.create("FILTER").add(filters);
		return tsMGet(filters, new JedisTSMGetParams(), (k)->k, args);
	}

	@Override
	public Map<byte[], TimeSeriesMGetElement> tsMGet(final byte[]... filters) {
		final CommandArguments args = CommandArguments.create("FILTER").add(filters);
		return tsMGet(SafeEncoder.encode(filters), new JedisTSMGetParams(), SafeEncoder::encode, args);
	}

	@Override
	public Map<String, TimeSeriesMGetElement> tsMGet(final TSMGetAegument argument, final String... filters) {
		final CommandArguments args = CommandArguments.create(argument).add("FILTER").add(filters);
		return tsMGet(filters, new JedisTSMGetParams(argument), (k)->k, args);
	}

	@Override
	public Map<byte[], TimeSeriesMGetElement> tsMGet(final TSMGetAegument argument, final byte[]... filters) {
		final CommandArguments args = CommandArguments.create(argument).add("FILTER").add(filters);
		return tsMGet(SafeEncoder.encode(filters), new JedisTSMGetParams(argument), SafeEncoder::encode, args);
	}

	@Override
	public Map<String, TimeSeriesMRangeElement> tsMRange(final long fromTimestamp, final long toTimestamp,
	                                                     final String... filters) {
		final CommandArguments args = CommandArguments.create().add(fromTimestamp, toTimestamp).add("FILTER")
				.add(filters);
		return tsMRange(fromTimestamp, toTimestamp, filters, (k)->k, args);
	}

	@Override
	public Map<byte[], TimeSeriesMRangeElement> tsMRange(final long fromTimestamp, final long toTimestamp,
	                                                     final byte[]... filters) {
		final CommandArguments args = CommandArguments.create().add(fromTimestamp, toTimestamp).add("FILTER")
				.add(filters);
		return tsMRange(fromTimestamp, toTimestamp, SafeEncoder.encode(filters), SafeEncoder::encode, args);
	}

	@Override
	public Map<String, TimeSeriesMRangeElement> tsMRange(final long fromTimestamp, final long toTimestamp,
	                                                     final TSMRangeArgument argument, final String... filters) {
		final CommandArguments args = CommandArguments.create().add(fromTimestamp, toTimestamp).add("FILTER")
				.add(filters).add(argument);
		return tsMRange(new JedisTSMRangeParams(fromTimestamp, toTimestamp, filters), (k)->k, args);
	}

	@Override
	public Map<byte[], TimeSeriesMRangeElement> tsMRange(final long fromTimestamp, final long toTimestamp,
	                                                     final TSMRangeArgument argument, final byte[]... filters) {
		final CommandArguments args = CommandArguments.create().add(fromTimestamp, toTimestamp).add("FILTER")
				.add(filters).add(argument);
		return tsMRange(new JedisTSMRangeParams(fromTimestamp, toTimestamp, filters), SafeEncoder::encode, args);
	}

	@Override
	public Map<String, TimeSeriesMRangeElement> tsMRange(final long fromTimestamp, final long toTimestamp,
	                                                     final TSMRangeArgument argument, final int count,
	                                                     final String... filters) {
		final CommandArguments args = CommandArguments.create().add(fromTimestamp, toTimestamp).add("FILTER")
				.add(filters).add(argument).add(Keyword.Common.COUNT, count);
		return tsMRange(new JedisTSMRangeParams(fromTimestamp, toTimestamp, filters, count), (k)->k, args);
	}

	@Override
	public Map<byte[], TimeSeriesMRangeElement> tsMRange(final long fromTimestamp, final long toTimestamp,
	                                                     final TSMRangeArgument argument, final int count,
	                                                     final byte[]... filters) {
		final CommandArguments args = CommandArguments.create().add(fromTimestamp, toTimestamp).add("FILTER")
				.add(filters).add(argument).add(Keyword.Common.COUNT, count);
		return tsMRange(new JedisTSMRangeParams(fromTimestamp, toTimestamp, filters, count), SafeEncoder::encode, args);
	}

	@Override
	public Map<String, TimeSeriesMRangeElement> tsMRange(final long fromTimestamp, final long toTimestamp,
	                                                     final int count, final String... filters) {
		final CommandArguments args = CommandArguments.create().add(fromTimestamp, toTimestamp).add("FILTER")
				.add(filters).add(Keyword.Common.COUNT, count);
		return tsMRange(new JedisTSMRangeParams(fromTimestamp, toTimestamp, filters, count), (k)->k, args);
	}

	@Override
	public Map<byte[], TimeSeriesMRangeElement> tsMRange(final long fromTimestamp, final long toTimestamp,
	                                                     final int count, final byte[]... filters) {
		final CommandArguments args = CommandArguments.create().add(fromTimestamp, toTimestamp).add("FILTER")
				.add(filters).add(Keyword.Common.COUNT, count);
		return tsMRange(new JedisTSMRangeParams(fromTimestamp, toTimestamp, filters, count), SafeEncoder::encode, args);
	}

	@Override
	public Map<String, TimeSeriesMRangeElement> tsMRevRange(final long fromTimestamp, final long toTimestamp,
	                                                        final String... filters) {
		final CommandArguments args = CommandArguments.create().add(fromTimestamp, toTimestamp).add("FILTER")
				.add(filters);
		return tsMRevRange(fromTimestamp, toTimestamp, filters, (k)->k, args);
	}

	@Override
	public Map<byte[], TimeSeriesMRangeElement> tsMRevRange(final long fromTimestamp, final long toTimestamp,
	                                                        final byte[]... filters) {
		final CommandArguments args = CommandArguments.create().add(fromTimestamp, toTimestamp).add("FILTER")
				.add(filters);
		return tsMRevRange(fromTimestamp, toTimestamp, SafeEncoder.encode(filters), SafeEncoder::encode, args);
	}

	@Override
	public Map<String, TimeSeriesMRangeElement> tsMRevRange(final long fromTimestamp, final long toTimestamp,
	                                                        final TSMRangeArgument argument, final String... filters) {
		final CommandArguments args = CommandArguments.create().add(fromTimestamp, toTimestamp).add("FILTER")
				.add(filters).add(argument);
		return tsMRevRange(new JedisTSMRangeParams(fromTimestamp, toTimestamp, filters), (k)->k, args);
	}

	@Override
	public Map<byte[], TimeSeriesMRangeElement> tsMRevRange(final long fromTimestamp, final long toTimestamp,
	                                                        final TSMRangeArgument argument, final byte[]... filters) {
		final CommandArguments args = CommandArguments.create().add(fromTimestamp, toTimestamp).add("FILTER")
				.add(filters).add(argument);
		return tsMRevRange(new JedisTSMRangeParams(fromTimestamp, toTimestamp, filters), SafeEncoder::encode, args);
	}

	@Override
	public Map<String, TimeSeriesMRangeElement> tsMRevRange(final long fromTimestamp, final long toTimestamp,
	                                                        final TSMRangeArgument argument, final int count,
	                                                        final String... filters) {
		final CommandArguments args = CommandArguments.create().add(fromTimestamp, toTimestamp).add("FILTER")
				.add(filters).add(argument).add(Keyword.Common.COUNT, count);
		return tsMRevRange(new JedisTSMRangeParams(fromTimestamp, toTimestamp, filters, count), (k)->k, args);
	}

	@Override
	public Map<byte[], TimeSeriesMRangeElement> tsMRevRange(final long fromTimestamp, final long toTimestamp,
	                                                        final TSMRangeArgument argument, final int count,
	                                                        final byte[]... filters) {
		final CommandArguments args = CommandArguments.create().add(fromTimestamp, toTimestamp).add("FILTER")
				.add(filters).add(argument).add(Keyword.Common.COUNT, count);
		return tsMRevRange(new JedisTSMRangeParams(fromTimestamp, toTimestamp, filters, count), SafeEncoder::encode,
				args);
	}

	@Override
	public Map<String, TimeSeriesMRangeElement> tsMRevRange(final long fromTimestamp, final long toTimestamp,
	                                                        final int count, final String... filters) {
		final CommandArguments args = CommandArguments.create().add(fromTimestamp, toTimestamp).add("FILTER")
				.add(filters).add(Keyword.Common.COUNT, count);
		return tsMRevRange(new JedisTSMRangeParams(fromTimestamp, toTimestamp, filters, count), (k)->k, args);
	}

	@Override
	public Map<byte[], TimeSeriesMRangeElement> tsMRevRange(final long fromTimestamp, final long toTimestamp,
	                                                        final int count, final byte[]... filters) {
		final CommandArguments args = CommandArguments.create().add(fromTimestamp, toTimestamp).add("FILTER")
				.add(filters).add(Keyword.Common.COUNT, count);
		return tsMRevRange(new JedisTSMRangeParams(fromTimestamp, toTimestamp, filters, count), SafeEncoder::encode,
				args);
	}

	@Override
	public List<String> tsQueryIndex(final String... filters) {
		final CommandArguments args = CommandArguments.create(filters);
		return executeCommand(RedisCommand.TS_QUERYINDEX, args, (cmd)->cmd.tsQueryIndex(filters));
	}

	@Override
	public List<byte[]> tsQueryIndex(final byte[]... filters) {
		final CommandArguments args = CommandArguments.create(filters);
		return executeCommand(RedisCommand.TS_QUERYINDEX, args, (cmd)->cmd.tsQueryIndex(SafeEncoder.encode(filters)),
				new StringListBinaryListConverter());
	}

	@Override
	public List<TimeSeriesElement> tsRange(final String key, final long fromTimestamp, final long toTimestamp) {
		final CommandArguments args = CommandArguments.create(key).add(fromTimestamp, toTimestamp);
		return executeCommand(RedisCommand.TS_RANGE, args, (cmd)->cmd.tsRange(rawKey(key), fromTimestamp, toTimestamp),
				new ListConverter<>(new TSElementConverter()));
	}

	@Override
	public List<TimeSeriesElement> tsRange(final byte[] key, final long fromTimestamp, final long toTimestamp) {
		return tsRange(SafeEncoder.encode(key), fromTimestamp, toTimestamp);
	}

	@Override
	public List<TimeSeriesElement> tsRange(final String key, final long fromTimestamp, final long toTimestamp,
	                                       final TSRangeArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(fromTimestamp, toTimestamp).add(argument);
		return executeCommand(RedisCommand.TS_RANGE, args, (cmd)->cmd.tsRange(rawKey(key),
						new JedisTSRangeParams(fromTimestamp, toTimestamp, argument)),
				new ListConverter<>(new TSElementConverter()));
	}

	@Override
	public List<TimeSeriesElement> tsRange(final byte[] key, final long fromTimestamp, final long toTimestamp,
	                                       final TSRangeArgument argument) {
		return tsRange(SafeEncoder.encode(key), fromTimestamp, toTimestamp, argument);
	}

	@Override
	public List<TimeSeriesElement> tsRange(final String key, final long fromTimestamp, final long toTimestamp,
	                                       final TSRangeArgument argument, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(fromTimestamp, toTimestamp).add(argument)
				.add(Keyword.Common.COUNT, count);
		return executeCommand(RedisCommand.TS_RANGE, args, (cmd)->cmd.tsRange(rawKey(key),
						new JedisTSRangeParams(fromTimestamp, toTimestamp, argument, count)),
				new ListConverter<>(new TSElementConverter()));
	}

	@Override
	public List<TimeSeriesElement> tsRange(final byte[] key, final long fromTimestamp, final long toTimestamp,
	                                       final TSRangeArgument argument, final int count) {
		return tsRange(SafeEncoder.encode(key), fromTimestamp, toTimestamp, argument, count);
	}

	@Override
	public List<TimeSeriesElement> tsRange(final String key, final long fromTimestamp, final long toTimestamp,
	                                       final int count) {
		final CommandArguments args = CommandArguments.create(key).add(fromTimestamp, toTimestamp)
				.add(Keyword.Common.COUNT, count);
		return executeCommand(RedisCommand.TS_RANGE, args, (cmd)->cmd.tsRange(rawKey(key),
						new JedisTSRangeParams(fromTimestamp, toTimestamp, count)),
				new ListConverter<>(new TSElementConverter()));
	}

	@Override
	public List<TimeSeriesElement> tsRange(final byte[] key, final long fromTimestamp, final long toTimestamp,
	                                       final int count) {
		return tsRange(SafeEncoder.encode(key), fromTimestamp, toTimestamp, count);
	}

	@Override
	public List<TimeSeriesElement> tsRevRange(final String key, final long fromTimestamp, final long toTimestamp) {
		final CommandArguments args = CommandArguments.create(key).add(fromTimestamp, toTimestamp);
		return executeCommand(
				RedisCommand.TS_REVRANGE, args, (cmd)->cmd.tsRevRange(rawKey(key), fromTimestamp, toTimestamp),
				new ListConverter<>(new TSElementConverter()));
	}

	@Override
	public List<TimeSeriesElement> tsRevRange(final byte[] key, final long fromTimestamp, final long toTimestamp) {
		return tsRevRange(SafeEncoder.encode(key), fromTimestamp, toTimestamp);
	}

	@Override
	public List<TimeSeriesElement> tsRevRange(final String key, final long fromTimestamp, final long toTimestamp,
	                                          final TSRangeArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(fromTimestamp, toTimestamp).add(argument);
		return executeCommand(RedisCommand.TS_REVRANGE, args, (cmd)->cmd.tsRevRange(rawKey(key),
						new JedisTSRangeParams(fromTimestamp, toTimestamp, argument)),
				new ListConverter<>(new TSElementConverter()));
	}

	@Override
	public List<TimeSeriesElement> tsRevRange(final byte[] key, final long fromTimestamp, final long toTimestamp,
	                                          final TSRangeArgument argument) {
		return tsRevRange(SafeEncoder.encode(key), fromTimestamp, toTimestamp, argument);
	}

	@Override
	public List<TimeSeriesElement> tsRevRange(final String key, final long fromTimestamp, final long toTimestamp,
	                                          final TSRangeArgument argument, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(fromTimestamp, toTimestamp).add(argument)
				.add(Keyword.Common.COUNT, count);
		return executeCommand(RedisCommand.TS_REVRANGE, args, (cmd)->cmd.tsRevRange(rawKey(key),
						new JedisTSRangeParams(fromTimestamp, toTimestamp, argument, count)),
				new ListConverter<>(new TSElementConverter()));
	}

	@Override
	public List<TimeSeriesElement> tsRevRange(final byte[] key, final long fromTimestamp, final long toTimestamp,
	                                          final TSRangeArgument argument, final int count) {
		return tsRevRange(SafeEncoder.encode(key), fromTimestamp, toTimestamp, argument, count);
	}

	@Override
	public List<TimeSeriesElement> tsRevRange(final String key, final long fromTimestamp, final long toTimestamp,
	                                          final int count) {
		final CommandArguments args = CommandArguments.create(key).add(fromTimestamp, toTimestamp)
				.add(Keyword.Common.COUNT, count);
		return executeCommand(RedisCommand.TS_REVRANGE, args, (cmd)->cmd.tsRevRange(rawKey(key),
						new JedisTSRangeParams(fromTimestamp, toTimestamp, count)),
				new ListConverter<>(new TSElementConverter()));
	}

	@Override
	public List<TimeSeriesElement> tsRevRange(final byte[] key, final long fromTimestamp, final long toTimestamp,
	                                          final int count) {
		return tsRevRange(SafeEncoder.encode(key), fromTimestamp, toTimestamp, count);
	}

	private <K> Map<K, TimeSeriesMGetElement> tsMGet(final String[] filters, final TSMGetParams tsmGetParams,
	                                                 final Converter<String, K> keyConveter,
	                                                 final CommandArguments args) {
		return executeCommand(RedisCommand.TS_MGET, args, (cmd)->cmd.tsMGet(tsmGetParams, filters),
				new MapConverter<>(keyConveter, new TSMGetElementConveter()));
	}

	private <K> Map<K, TimeSeriesMRangeElement> tsMRange(final long fromTimestamp, final long toTimestamp,
	                                                     final String[] filters, final Converter<String, K> keyConveter,
	                                                     final CommandArguments args) {
		return executeCommand(RedisCommand.TS_MRANGE, args, (cmd)->cmd.tsMRange(fromTimestamp, toTimestamp, filters),
				new MapConverter<>(keyConveter, new TSMRangeElementsConverter()));
	}

	private <K> Map<K, TimeSeriesMRangeElement> tsMRange(final TSMRangeParams tsmRangeParams,
	                                                     final Converter<String, K> keyConveter,
	                                                     final CommandArguments args) {
		return executeCommand(RedisCommand.TS_MRANGE, args, (cmd)->cmd.tsMRange(tsmRangeParams),
				new MapConverter<>(keyConveter, new TSMRangeElementsConverter()));
	}

	private <K> Map<K, TimeSeriesMRangeElement> tsMRevRange(final long fromTimestamp, final long toTimestamp,
	                                                        final String[] filters,
	                                                        final Converter<String, K> keyConveter,
	                                                        final CommandArguments args) {
		return executeCommand(
				RedisCommand.TS_MREVRANGE, args, (cmd)->cmd.tsMRevRange(fromTimestamp, toTimestamp, filters),
				new MapConverter<>(keyConveter, new TSMRangeElementsConverter()));
	}

	private <K> Map<K, TimeSeriesMRangeElement> tsMRevRange(final TSMRangeParams tsmRangeParams,
	                                                        final Converter<String, K> keyConveter,
	                                                        final CommandArguments args) {
		return executeCommand(RedisCommand.TS_MREVRANGE, args, (cmd)->cmd.tsMRevRange(tsmRangeParams),
				new MapConverter<>(keyConveter, new TSMRangeElementsConverter()));
	}

}
