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
package com.buession.redis.core.operations;

import com.buession.lang.Status;
import com.buession.redis.core.TimeSeriesElement;
import com.buession.redis.core.TimeSeriesInfo;
import com.buession.redis.core.TimeSeriesMGetElement;
import com.buession.redis.core.TimeSeriesMRangeElement;
import com.buession.redis.core.command.TimeSeriesCommands;
import com.buession.redis.core.command.args.timeseries.AddArgument;
import com.buession.redis.core.AggregationType;
import com.buession.redis.core.command.args.timeseries.AlertArgument;
import com.buession.redis.core.command.args.timeseries.CreateArgument;
import com.buession.redis.core.command.args.timeseries.DecrByArgument;
import com.buession.redis.core.command.args.timeseries.IncrByArgument;
import com.buession.redis.core.command.args.timeseries.TSElement;
import com.buession.redis.core.command.args.timeseries.TSMGetAegument;
import com.buession.redis.core.command.args.timeseries.TSMRangeArgument;
import com.buession.redis.core.command.args.timeseries.TSRangeArgument;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * T-Digest 命令
 *
 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/?group=tdigest" target="_blank">https://redis.io/docs/latest/commands/?group=tdigest</a></p>
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public interface TimeSeriesOperations extends TimeSeriesCommands, RedisOperations {

	@Override
	default Long tsAdd(final String key, final long timestamp, final double value) {
		return execute((client)->client.timeSeriesCommands().tsAdd(key, timestamp, value));
	}

	@Override
	default Long tsAdd(final byte[] key, final long timestamp, final double value) {
		return execute((client)->client.timeSeriesCommands().tsAdd(key, timestamp, value));
	}

	@Override
	default Long tsAdd(final String key, final long timestamp, final double value, final AddArgument argument) {
		return execute((client)->client.timeSeriesCommands().tsAdd(key, timestamp, value, argument));
	}

	@Override
	default Long tsAdd(final byte[] key, final long timestamp, final double value, final AddArgument argument) {
		return execute((client)->client.timeSeriesCommands().tsAdd(key, timestamp, value, argument));
	}

	/**
	 * 向指定的时间序列 Key 中添加一个新的数据点
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.add/" target="_blank">https://redis.io/docs/latest/commands/ts.add/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param date
	 * 		时间
	 * @param value
	 * 		值
	 *
	 * @return 写入数据点的时间戳（单位：毫秒）
	 */
	default Long tsAdd(final String key, final Date date, final double value) {
		return tsAdd(key, date.getTime(), value);
	}

	/**
	 * 向指定的时间序列 Key 中添加一个新的数据点
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.add/" target="_blank">https://redis.io/docs/latest/commands/ts.add/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param date
	 * 		时间
	 * @param value
	 * 		值
	 *
	 * @return 写入数据点的时间戳（单位：毫秒）
	 */
	default Long tsAdd(final byte[] key, final Date date, final double value) {
		return tsAdd(key, date.getTime(), value);
	}

	/**
	 * 向指定的时间序列 Key 中添加一个新的数据点
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.add/" target="_blank">https://redis.io/docs/latest/commands/ts.add/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param dateTime
	 * 		时间
	 * @param value
	 * 		值
	 *
	 * @return 写入数据点的时间戳（单位：毫秒）
	 */
	default Long tsAdd(final String key, final LocalDateTime dateTime, final double value) {
		return tsAdd(key, dateTime.atZone(ZoneId.systemDefault()).toInstant(), value);
	}

	/**
	 * 向指定的时间序列 Key 中添加一个新的数据点
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.add/" target="_blank">https://redis.io/docs/latest/commands/ts.add/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param dateTime
	 * 		时间
	 * @param value
	 * 		值
	 *
	 * @return 写入数据点的时间戳（单位：毫秒）
	 */
	default Long tsAdd(final byte[] key, final LocalDateTime dateTime, final double value) {
		return tsAdd(key, dateTime.atZone(ZoneId.systemDefault()).toInstant(), value);
	}

	/**
	 * 向指定的时间序列 Key 中添加一个新的数据点
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.add/" target="_blank">https://redis.io/docs/latest/commands/ts.add/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param dateTime
	 * 		时间
	 * @param value
	 * 		值
	 *
	 * @return 写入数据点的时间戳（单位：毫秒）
	 */
	default Long tsAdd(final String key, final ZonedDateTime dateTime, final double value) {
		return tsAdd(key, dateTime.toInstant(), value);
	}

	/**
	 * 向指定的时间序列 Key 中添加一个新的数据点
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.add/" target="_blank">https://redis.io/docs/latest/commands/ts.add/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param dateTime
	 * 		时间
	 * @param value
	 * 		值
	 *
	 * @return 写入数据点的时间戳（单位：毫秒）
	 */
	default Long tsAdd(final byte[] key, final ZonedDateTime dateTime, final double value) {
		return tsAdd(key, dateTime.toInstant(), value);
	}

	/**
	 * 向指定的时间序列 Key 中添加一个新的数据点
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.add/" target="_blank">https://redis.io/docs/latest/commands/ts.add/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param instant
	 * 		时间
	 * @param value
	 * 		值
	 *
	 * @return 写入数据点的时间戳（单位：毫秒）
	 */
	default Long tsAdd(final String key, final Instant instant, final double value) {
		return tsAdd(key, instant.toEpochMilli(), value);
	}

	/**
	 * 向指定的时间序列 Key 中添加一个新的数据点
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.add/" target="_blank">https://redis.io/docs/latest/commands/ts.add/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param instant
	 * 		时间
	 * @param value
	 * 		值
	 *
	 * @return 写入数据点的时间戳（单位：毫秒）
	 */
	default Long tsAdd(final byte[] key, final Instant instant, final double value) {
		return tsAdd(key, instant.toEpochMilli(), value);
	}

	/**
	 * 向指定的时间序列 Key 中添加一个新的数据点
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.add/" target="_blank">https://redis.io/docs/latest/commands/ts.add/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param date
	 * 		时间
	 * @param value
	 * 		值
	 * @param argument
	 * 		参数
	 *
	 * @return 写入数据点的时间戳（单位：毫秒）
	 */
	default Long tsAdd(final String key, final Date date, final double value, final AddArgument argument) {
		return tsAdd(key, date.getTime(), value, argument);
	}

	/**
	 * 向指定的时间序列 Key 中添加一个新的数据点
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.add/" target="_blank">https://redis.io/docs/latest/commands/ts.add/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param date
	 * 		时间
	 * @param value
	 * 		值
	 * @param argument
	 * 		参数
	 *
	 * @return 写入数据点的时间戳（单位：毫秒）
	 */
	default Long tsAdd(final byte[] key, final Date date, final double value, final AddArgument argument) {
		return tsAdd(key, date.getTime(), value, argument);
	}

	/**
	 * 向指定的时间序列 Key 中添加一个新的数据点
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.add/" target="_blank">https://redis.io/docs/latest/commands/ts.add/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param dateTime
	 * 		时间
	 * @param value
	 * 		值
	 * @param argument
	 * 		参数
	 *
	 * @return 写入数据点的时间戳（单位：毫秒）
	 */
	default Long tsAdd(final String key, final LocalDateTime dateTime, final double value, final AddArgument argument) {
		return tsAdd(key, dateTime.atZone(ZoneId.systemDefault()).toInstant(), value, argument);
	}

	/**
	 * 向指定的时间序列 Key 中添加一个新的数据点
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.add/" target="_blank">https://redis.io/docs/latest/commands/ts.add/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param dateTime
	 * 		时间
	 * @param value
	 * 		值
	 * @param argument
	 * 		参数
	 *
	 * @return 写入数据点的时间戳（单位：毫秒）
	 */
	default Long tsAdd(final byte[] key, final LocalDateTime dateTime, final double value, final AddArgument argument) {
		return tsAdd(key, dateTime.atZone(ZoneId.systemDefault()).toInstant(), value, argument);
	}

	/**
	 * 向指定的时间序列 Key 中添加一个新的数据点
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.add/" target="_blank">https://redis.io/docs/latest/commands/ts.add/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param dateTime
	 * 		时间
	 * @param value
	 * 		值
	 * @param argument
	 * 		参数
	 *
	 * @return 写入数据点的时间戳（单位：毫秒）
	 */
	default Long tsAdd(final String key, final ZonedDateTime dateTime, final double value, final AddArgument argument) {
		return tsAdd(key, dateTime.toInstant(), value, argument);
	}

	/**
	 * 向指定的时间序列 Key 中添加一个新的数据点
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.add/" target="_blank">https://redis.io/docs/latest/commands/ts.add/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param dateTime
	 * 		时间
	 * @param value
	 * 		值
	 * @param argument
	 * 		参数
	 *
	 * @return 写入数据点的时间戳（单位：毫秒）
	 */
	default Long tsAdd(final byte[] key, final ZonedDateTime dateTime, final double value, final AddArgument argument) {
		return tsAdd(key, dateTime.toInstant(), value, argument);
	}

	/**
	 * 向指定的时间序列 Key 中添加一个新的数据点
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.add/" target="_blank">https://redis.io/docs/latest/commands/ts.add/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param instant
	 * 		时间
	 * @param value
	 * 		值
	 * @param argument
	 * 		参数
	 *
	 * @return 写入数据点的时间戳（单位：毫秒）
	 */
	default Long tsAdd(final String key, final Instant instant, final double value, final AddArgument argument) {
		return tsAdd(key, instant.toEpochMilli(), value, argument);
	}

	/**
	 * 向指定的时间序列 Key 中添加一个新的数据点
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.add/" target="_blank">https://redis.io/docs/latest/commands/ts.add/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param instant
	 * 		时间
	 * @param value
	 * 		值
	 * @param argument
	 * 		参数
	 *
	 * @return 写入数据点的时间戳（单位：毫秒）
	 */
	default Long tsAdd(final byte[] key, final Instant instant, final double value, final AddArgument argument) {
		return tsAdd(key, instant.toEpochMilli(), value, argument);
	}

	@Override
	default Status tsAlert(final String key, final AlertArgument argument) {
		return execute((client)->client.timeSeriesCommands().tsAlert(key, argument));
	}

	@Override
	default Status tsAlert(final byte[] key, final AlertArgument argument) {
		return execute((client)->client.timeSeriesCommands().tsAlert(key, argument));
	}

	@Override
	default Status tsCreate(final String key) {
		return execute((client)->client.timeSeriesCommands().tsCreate(key));
	}

	@Override
	default Status tsCreate(final byte[] key) {
		return execute((client)->client.timeSeriesCommands().tsCreate(key));
	}

	@Override
	default Status tsCreate(final String key, final CreateArgument argument) {
		return execute((client)->client.timeSeriesCommands().tsCreate(key, argument));
	}

	@Override
	default Status tsCreate(final byte[] key, final CreateArgument argument) {
		return execute((client)->client.timeSeriesCommands().tsCreate(key, argument));
	}

	@Override
	default Status tsCreateRule(final String sourceKey, final String destKey, final AggregationType aggregationType,
								final long timeBucket) {
		return execute(
				(client)->client.timeSeriesCommands().tsCreateRule(sourceKey, destKey, aggregationType, timeBucket));
	}

	@Override
	default Status tsCreateRule(final byte[] sourceKey, final byte[] destKey, final AggregationType aggregationType,
								final long timeBucket) {
		return execute(
				(client)->client.timeSeriesCommands().tsCreateRule(sourceKey, destKey, aggregationType, timeBucket));
	}

	@Override
	default Status tsCreateRule(final String sourceKey, final String destKey, final AggregationType aggregationType,
								final long timeBucket, final long alignTimestamp) {
		return execute((client)->client.timeSeriesCommands()
				.tsCreateRule(sourceKey, destKey, aggregationType, timeBucket, alignTimestamp));
	}

	@Override
	default Status tsCreateRule(final byte[] sourceKey, final byte[] destKey, final AggregationType aggregationType,
								final long timeBucket, final long alignTimestamp) {
		return execute((client)->client.timeSeriesCommands()
				.tsCreateRule(sourceKey, destKey, aggregationType, timeBucket, alignTimestamp));
	}

	/**
	 * 时间序列 Key 中减少值 1
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.add/" target="_blank">https://redis.io/docs/latest/commands/ts.add/</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return 写入数据点的时间戳（单位：毫秒）
	 */
	default Long tsDecr(final String key) {
		return tsDecrBy(key, 1.0);
	}

	/**
	 * 时间序列 Key 中减少值 1
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.add/" target="_blank">https://redis.io/docs/latest/commands/ts.add/</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return 写入数据点的时间戳（单位：毫秒）
	 */
	default Long tsDecrBy(final byte[] key) {
		return tsDecrBy(key, 1.0);
	}

	/**
	 * 时间序列 Key 中减少值 1
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.decrby/" target="_blank">https://redis.io/docs/latest/commands/ts.decrby/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param argument
	 * 		参数
	 *
	 * @return 写入数据点的时间戳（单位：毫秒）
	 */
	default Long tsDecrBy(final String key, final DecrByArgument argument) {
		return tsDecrBy(key, 1, argument);
	}

	/**
	 * 时间序列 Key 中减少值 1
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.decrby/" target="_blank">https://redis.io/docs/latest/commands/ts.decrby/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param argument
	 * 		参数
	 *
	 * @return 写入数据点的时间戳（单位：毫秒）
	 */
	default Long tsDecrBy(final byte[] key, final DecrByArgument argument) {
		return tsDecrBy(key, 1, argument);
	}

	@Override
	default Long tsDecrBy(final String key, final double value) {
		return execute((client)->client.timeSeriesCommands().tsDecrBy(key, value));
	}

	@Override
	default Long tsDecrBy(final byte[] key, final double value) {
		return execute((client)->client.timeSeriesCommands().tsDecrBy(key, value));
	}

	@Override
	default Long tsDecrBy(final String key, final double value, final DecrByArgument argument) {
		return execute((client)->client.timeSeriesCommands().tsDecrBy(key, value, argument));
	}

	@Override
	default Long tsDecrBy(final byte[] key, final double value, final DecrByArgument argument) {
		return execute((client)->client.timeSeriesCommands().tsDecrBy(key, value, argument));
	}

	@Override
	default Long tsDel(final String key, final long fromTimestamp, final long toTimestamp) {
		return execute((client)->client.timeSeriesCommands().tsDel(key, fromTimestamp, toTimestamp));
	}

	@Override
	default Long tsDel(final byte[] key, final long fromTimestamp, final long toTimestamp) {
		return execute((client)->client.timeSeriesCommands().tsDel(key, fromTimestamp, toTimestamp));
	}

	/**
	 * 删除指定时间序列中某个时间范围内的所有数据点
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.del/" target="_blank">https://redis.io/docs/latest/commands/ts.del/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fromDate
	 * 		删除范围的起始时间
	 * @param toDate
	 * 		删除范围的结束时间
	 *
	 * @return 写入数据点的时间戳（单位：毫秒）
	 */
	default Long tsDel(final String key, final Date fromDate, final Date toDate) {
		return tsDel(key, fromDate.getTime(), toDate.getTime());
	}

	/**
	 * 删除指定时间序列中某个时间范围内的所有数据点
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.del/" target="_blank">https://redis.io/docs/latest/commands/ts.del/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fromDate
	 * 		删除范围的起始时间
	 * @param toDate
	 * 		删除范围的结束时间
	 *
	 * @return 写入数据点的时间戳（单位：毫秒）
	 */
	default Long tsDel(final byte[] key, final Date fromDate, final Date toDate) {
		return tsDel(key, fromDate.getTime(), toDate.getTime());
	}

	/**
	 * 删除指定时间序列中某个时间范围内的所有数据点
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.del/" target="_blank">https://redis.io/docs/latest/commands/ts.del/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fromDateTime
	 * 		删除范围的起始时间
	 * @param toDateTime
	 * 		删除范围的结束时间
	 *
	 * @return 写入数据点的时间戳（单位：毫秒）
	 */
	default Long tsDel(final String key, final LocalDateTime fromDateTime, final LocalDateTime toDateTime) {
		return tsDel(key, fromDateTime.atZone(ZoneId.systemDefault()).toInstant(),
				toDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}

	/**
	 * 删除指定时间序列中某个时间范围内的所有数据点
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.del/" target="_blank">https://redis.io/docs/latest/commands/ts.del/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fromDateTime
	 * 		删除范围的起始时间
	 * @param toDateTime
	 * 		删除范围的结束时间
	 *
	 * @return 写入数据点的时间戳（单位：毫秒）
	 */
	default Long tsDel(final byte[] key, final LocalDateTime fromDateTime, final LocalDateTime toDateTime) {
		return tsDel(key, fromDateTime.atZone(ZoneId.systemDefault()).toInstant(),
				toDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}

	/**
	 * 删除指定时间序列中某个时间范围内的所有数据点
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.del/" target="_blank">https://redis.io/docs/latest/commands/ts.del/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fromDateTime
	 * 		删除范围的起始时间
	 * @param toDateTime
	 * 		删除范围的结束时间
	 *
	 * @return 写入数据点的时间戳（单位：毫秒）
	 */
	default Long tsDel(final String key, final ZonedDateTime fromDateTime, final ZonedDateTime toDateTime) {
		return tsDel(key, fromDateTime.toInstant(), toDateTime.toInstant());
	}

	/**
	 * 删除指定时间序列中某个时间范围内的所有数据点
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.del/" target="_blank">https://redis.io/docs/latest/commands/ts.del/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fromDateTime
	 * 		删除范围的起始时间
	 * @param toDateTime
	 * 		删除范围的结束时间
	 *
	 * @return 写入数据点的时间戳（单位：毫秒）
	 */
	default Long tsDel(final byte[] key, final ZonedDateTime fromDateTime, final ZonedDateTime toDateTime) {
		return tsDel(key, fromDateTime.toInstant(), toDateTime.toInstant());
	}

	/**
	 * 删除指定时间序列中某个时间范围内的所有数据点
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.del/" target="_blank">https://redis.io/docs/latest/commands/ts.del/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fromInstant
	 * 		删除范围的起始时间
	 * @param toInstant
	 * 		删除范围的结束时间
	 *
	 * @return 写入数据点的时间戳（单位：毫秒）
	 */
	default Long tsDel(final String key, final Instant fromInstant, final Instant toInstant) {
		return tsDel(key, fromInstant.toEpochMilli(), toInstant.toEpochMilli());
	}

	/**
	 * 删除指定时间序列中某个时间范围内的所有数据点
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.del/" target="_blank">https://redis.io/docs/latest/commands/ts.del/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fromInstant
	 * 		删除范围的起始时间
	 * @param toInstant
	 * 		删除范围的结束时间
	 *
	 * @return 写入数据点的时间戳（单位：毫秒）
	 */
	default Long tsDel(final byte[] key, final Instant fromInstant, final Instant toInstant) {
		return tsDel(key, fromInstant.toEpochMilli(), toInstant.toEpochMilli());
	}

	@Override
	default Status tsDeleteRule(final String sourceKey, final String destKey) {
		return execute((client)->client.timeSeriesCommands().tsDeleteRule(sourceKey, destKey));
	}

	@Override
	default Status tsDeleteRule(final byte[] sourceKey, final byte[] destKey) {
		return execute((client)->client.timeSeriesCommands().tsDeleteRule(sourceKey, destKey));
	}

	@Override
	default TimeSeriesElement tsGet(final String key) {
		return execute((client)->client.timeSeriesCommands().tsGet(key));
	}

	@Override
	default TimeSeriesElement tsGet(final byte[] key) {
		return execute((client)->client.timeSeriesCommands().tsGet(key));
	}

	@Override
	default TimeSeriesElement tsGet(final String key, final boolean latest) {
		return execute((client)->client.timeSeriesCommands().tsGet(key, latest));
	}

	@Override
	default TimeSeriesElement tsGet(final byte[] key, final boolean latest) {
		return execute((client)->client.timeSeriesCommands().tsGet(key, latest));
	}

	/**
	 * 时间序列 Key 中减增加 1
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.incrby/" target="_blank">https://redis.io/docs/latest/commands/ts.incrby/</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return 写入数据点的时间戳（单位：毫秒）
	 */
	default Long tsIncr(final String key) {
		return tsIncrBy(key, 1.0);
	}

	/**
	 * 时间序列 Key 中增加值 1
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.incrby/" target="_blank">https://redis.io/docs/latest/commands/ts.incrby/</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return 写入数据点的时间戳（单位：毫秒）
	 */
	default Long tsIncr(final byte[] key) {
		return tsIncrBy(key, 1.0);
	}

	/**
	 * 时间序列 Key 中增加值 value
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.incrby/" target="_blank">https://redis.io/docs/latest/commands/ts.incrby/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param argument
	 * 		参数
	 *
	 * @return 写入数据点的时间戳（单位：毫秒）
	 */
	default Long tsIncr(final String key, final IncrByArgument argument) {
		return tsIncrBy(key, 1.0, argument);
	}

	/**
	 * 时间序列 Key 中增加值 value
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.incrby/" target="_blank">https://redis.io/docs/latest/commands/ts.incrby/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param argument
	 * 		参数
	 *
	 * @return 写入数据点的时间戳（单位：毫秒）
	 */
	default Long tsIncr(final byte[] key, final IncrByArgument argument) {
		return tsIncrBy(key, 1.0, argument);
	}

	@Override
	default Long tsIncrBy(final String key, final double value) {
		return execute((client)->client.timeSeriesCommands().tsIncrBy(key, value));
	}

	@Override
	default Long tsIncrBy(final byte[] key, final double value) {
		return execute((client)->client.timeSeriesCommands().tsIncrBy(key, value));
	}

	@Override
	default Long tsIncrBy(final String key, final double value, final IncrByArgument argument) {
		return execute((client)->client.timeSeriesCommands().tsIncrBy(key, value, argument));
	}

	@Override
	default Long tsIncrBy(final byte[] key, final double value, final IncrByArgument argument) {
		return execute((client)->client.timeSeriesCommands().tsIncrBy(key, value, argument));
	}

	@Override
	default TimeSeriesInfo tsInfo(final String key) {
		return execute((client)->client.timeSeriesCommands().tsInfo(key));
	}

	@Override
	default TimeSeriesInfo tsInfo(final byte[] key) {
		return execute((client)->client.timeSeriesCommands().tsInfo(key));
	}

	@Override
	default TimeSeriesInfo tsInfo(final String key, final boolean debug) {
		return execute((client)->client.timeSeriesCommands().tsInfo(key, debug));
	}

	@Override
	default TimeSeriesInfo tsInfo(final byte[] key, final boolean debug) {
		return execute((client)->client.timeSeriesCommands().tsInfo(key, debug));
	}

	@Override
	default List<Long> tsMAdd(final TSElement... values) {
		return execute((client)->client.timeSeriesCommands().tsMAdd(values));
	}

	@Override
	default Map<String, TimeSeriesMGetElement> tsMGet(final String... filters) {
		return execute((client)->client.timeSeriesCommands().tsMGet(filters));
	}

	@Override
	default Map<byte[], TimeSeriesMGetElement> tsMGet(final byte[]... filters) {
		return execute((client)->client.timeSeriesCommands().tsMGet(filters));
	}

	@Override
	default Map<String, TimeSeriesMGetElement> tsMGet(final TSMGetAegument argument, final String... filters) {
		return execute((client)->client.timeSeriesCommands().tsMGet(argument, filters));
	}

	@Override
	default Map<byte[], TimeSeriesMGetElement> tsMGet(final TSMGetAegument argument, final byte[]... filters) {
		return execute((client)->client.timeSeriesCommands().tsMGet(argument, filters));
	}

	@Override
	default Map<String, TimeSeriesMRangeElement> tsMRange(final long fromTimestamp, final long toTimestamp,
														  final String... filters) {
		return execute((client)->client.timeSeriesCommands().tsMRange(fromTimestamp, toTimestamp, filters));
	}

	@Override
	default Map<byte[], TimeSeriesMRangeElement> tsMRange(final long fromTimestamp, final long toTimestamp,
														  final byte[]... filters) {
		return execute((client)->client.timeSeriesCommands().tsMRange(fromTimestamp, toTimestamp, filters));
	}

	@Override
	default Map<String, TimeSeriesMRangeElement> tsMRange(final long fromTimestamp, final long toTimestamp,
														  final TSMRangeArgument argument, final String... filters) {
		return execute((client)->client.timeSeriesCommands().tsMRange(fromTimestamp, toTimestamp, argument, filters));
	}

	@Override
	default Map<byte[], TimeSeriesMRangeElement> tsMRange(final long fromTimestamp, final long toTimestamp,
														  final TSMRangeArgument argument, final byte[]... filters) {
		return execute((client)->client.timeSeriesCommands().tsMRange(fromTimestamp, toTimestamp, argument, filters));
	}

	@Override
	default Map<String, TimeSeriesMRangeElement> tsMRange(final long fromTimestamp, final long toTimestamp,
														  final TSMRangeArgument argument, final int count,
														  final String... filters) {
		return execute(
				(client)->client.timeSeriesCommands().tsMRange(fromTimestamp, toTimestamp, argument, count, filters));
	}

	@Override
	default Map<byte[], TimeSeriesMRangeElement> tsMRange(final long fromTimestamp, final long toTimestamp,
														  final TSMRangeArgument argument, final int count,
														  final byte[]... filters) {
		return execute(
				(client)->client.timeSeriesCommands().tsMRange(fromTimestamp, toTimestamp, argument, count, filters));
	}

	@Override
	default Map<String, TimeSeriesMRangeElement> tsMRange(final long fromTimestamp, final long toTimestamp,
														  final int count, final String... filters) {
		return execute((client)->client.timeSeriesCommands().tsMRange(fromTimestamp, toTimestamp, count, filters));
	}

	@Override
	default Map<byte[], TimeSeriesMRangeElement> tsMRange(final long fromTimestamp, final long toTimestamp,
														  final int count, final byte[]... filters) {
		return execute((client)->client.timeSeriesCommands().tsMRange(fromTimestamp, toTimestamp, count, filters));
	}

	/**
	 * 批量获取多个时间序列在指定时间范围内的历史数据
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.mrange/" target="_blank">https://redis.io/docs/latest/commands/ts.mrange/</a></p>
	 *
	 * @param fromDate
	 * 		起始时间
	 * @param toDate
	 * 		结束时间
	 * @param filters
	 * 		一个或多个过滤表达式
	 *
	 * @return 多个时间序列的最新数据点
	 */
	default Map<String, TimeSeriesMRangeElement> tsMRange(final Date fromDate, final Date toDate,
														  final String... filters) {
		return tsMRange(fromDate.getTime(), toDate.getTime(), filters);
	}

	/**
	 * 批量获取多个时间序列在指定时间范围内的历史数据
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.mrange/" target="_blank">https://redis.io/docs/latest/commands/ts.mrange/</a></p>
	 *
	 * @param fromDate
	 * 		起始时间
	 * @param toDate
	 * 		结束时间
	 * @param filters
	 * 		一个或多个过滤表达式
	 *
	 * @return 多个时间序列的最新数据点
	 */
	default Map<byte[], TimeSeriesMRangeElement> tsMRange(final Date fromDate, final Date toDate,
														  final byte[]... filters) {
		return tsMRange(fromDate.getTime(), toDate.getTime(), filters);
	}

	/**
	 * 批量获取多个时间序列在指定时间范围内的历史数据
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.mrange/" target="_blank">https://redis.io/docs/latest/commands/ts.mrange/</a></p>
	 *
	 * @param fromDateTime
	 * 		起始时间
	 * @param toDateTime
	 * 		结束时间
	 * @param filters
	 * 		一个或多个过滤表达式
	 *
	 * @return 多个时间序列的最新数据点
	 */
	default Map<String, TimeSeriesMRangeElement> tsMRange(final LocalDateTime fromDateTime,
														  final LocalDateTime toDateTime, final String... filters) {
		return tsMRange(fromDateTime.atZone(ZoneId.systemDefault()).toInstant(),
				toDateTime.atZone(ZoneId.systemDefault()).toInstant(), filters);
	}

	/**
	 * 批量获取多个时间序列在指定时间范围内的历史数据
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.mrange/" target="_blank">https://redis.io/docs/latest/commands/ts.mrange/</a></p>
	 *
	 * @param fromDateTime
	 * 		起始时间
	 * @param toDateTime
	 * 		结束时间
	 * @param filters
	 * 		一个或多个过滤表达式
	 *
	 * @return 多个时间序列的最新数据点
	 */
	default Map<byte[], TimeSeriesMRangeElement> tsMRange(final LocalDateTime fromDateTime,
														  final LocalDateTime toDateTime, final byte[]... filters) {
		return tsMRange(fromDateTime.atZone(ZoneId.systemDefault()).toInstant(),
				toDateTime.atZone(ZoneId.systemDefault()).toInstant(), filters);
	}

	/**
	 * 批量获取多个时间序列在指定时间范围内的历史数据
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.mrange/" target="_blank">https://redis.io/docs/latest/commands/ts.mrange/</a></p>
	 *
	 * @param fromDateTime
	 * 		起始时间
	 * @param toDateTime
	 * 		结束时间
	 * @param filters
	 * 		一个或多个过滤表达式
	 *
	 * @return 多个时间序列的最新数据点
	 */
	default Map<String, TimeSeriesMRangeElement> tsMRange(final ZonedDateTime fromDateTime,
														  final ZonedDateTime toDateTime, final String... filters) {
		return tsMRange(fromDateTime.toInstant(), toDateTime.toInstant(), filters);
	}

	/**
	 * 批量获取多个时间序列在指定时间范围内的历史数据
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.mrange/" target="_blank">https://redis.io/docs/latest/commands/ts.mrange/</a></p>
	 *
	 * @param fromDateTime
	 * 		起始时间
	 * @param toDateTime
	 * 		结束时间
	 * @param filters
	 * 		一个或多个过滤表达式
	 *
	 * @return 多个时间序列的最新数据点
	 */
	default Map<byte[], TimeSeriesMRangeElement> tsMRange(final ZonedDateTime fromDateTime,
														  final ZonedDateTime toDateTime, final byte[]... filters) {
		return tsMRange(fromDateTime.toInstant(), toDateTime.toInstant(), filters);
	}

	/**
	 * 批量获取多个时间序列在指定时间范围内的历史数据
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.mrange/" target="_blank">https://redis.io/docs/latest/commands/ts.mrange/</a></p>
	 *
	 * @param fromInstant
	 * 		起始时间
	 * @param toInstant
	 * 		结束时间
	 * @param filters
	 * 		一个或多个过滤表达式
	 *
	 * @return 多个时间序列的最新数据点
	 */
	default Map<String, TimeSeriesMRangeElement> tsMRange(final Instant fromInstant, final Instant toInstant,
														  final String... filters) {
		return tsMRange(fromInstant.toEpochMilli(), toInstant.toEpochMilli(), filters);
	}

	/**
	 * 批量获取多个时间序列在指定时间范围内的历史数据
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.mrange/" target="_blank">https://redis.io/docs/latest/commands/ts.mrange/</a></p>
	 *
	 * @param fromInstant
	 * 		起始时间
	 * @param toInstant
	 * 		结束时间
	 * @param filters
	 * 		一个或多个过滤表达式
	 *
	 * @return 多个时间序列的最新数据点
	 */
	default Map<byte[], TimeSeriesMRangeElement> tsMRange(final Instant fromInstant, final Instant toInstant,
														  final byte[]... filters) {
		return tsMRange(fromInstant.toEpochMilli(), toInstant.toEpochMilli(), filters);
	}

	/**
	 * 批量获取多个时间序列在指定时间范围内的历史数据
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.mrange/" target="_blank">https://redis.io/docs/latest/commands/ts.mrange/</a></p>
	 *
	 * @param fromDate
	 * 		起始时间
	 * @param toDate
	 * 		结束时间
	 * @param argument
	 * 		参数
	 * @param filters
	 * 		一个或多个过滤表达式
	 *
	 * @return 多个时间序列的最新数据点
	 */
	default Map<String, TimeSeriesMRangeElement> tsMRange(final Date fromDate, final Date toDate,
														  final TSMRangeArgument argument, final String... filters) {
		return tsMRange(fromDate.getTime(), toDate.getTime(), argument, filters);
	}

	/**
	 * 批量获取多个时间序列在指定时间范围内的历史数据
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.mrange/" target="_blank">https://redis.io/docs/latest/commands/ts.mrange/</a></p>
	 *
	 * @param fromDate
	 * 		起始时间
	 * @param toDate
	 * 		结束时间
	 * @param argument
	 * 		参数
	 * @param filters
	 * 		一个或多个过滤表达式
	 *
	 * @return 多个时间序列的最新数据点
	 */
	default Map<byte[], TimeSeriesMRangeElement> tsMRange(final Date fromDate, final Date toDate,
														  final TSMRangeArgument argument, final byte[]... filters) {
		return tsMRange(fromDate.getTime(), toDate.getTime(), argument, filters);
	}

	/**
	 * 批量获取多个时间序列在指定时间范围内的历史数据
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.mrange/" target="_blank">https://redis.io/docs/latest/commands/ts.mrange/</a></p>
	 *
	 * @param fromDateTime
	 * 		起始时间
	 * @param toDateTime
	 * 		结束时间
	 * @param argument
	 * 		参数
	 * @param filters
	 * 		一个或多个过滤表达式
	 *
	 * @return 多个时间序列的最新数据点
	 */
	default Map<String, TimeSeriesMRangeElement> tsMRange(final LocalDateTime fromDateTime,
														  final LocalDateTime toDateTime,
														  final TSMRangeArgument argument, final String... filters) {
		return tsMRange(fromDateTime.atZone(ZoneId.systemDefault()).toInstant(),
				toDateTime.atZone(ZoneId.systemDefault()).toInstant(), argument, filters);
	}

	/**
	 * 批量获取多个时间序列在指定时间范围内的历史数据
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.mrange/" target="_blank">https://redis.io/docs/latest/commands/ts.mrange/</a></p>
	 *
	 * @param fromDateTime
	 * 		起始时间
	 * @param toDateTime
	 * 		结束时间
	 * @param argument
	 * 		参数
	 * @param filters
	 * 		一个或多个过滤表达式
	 *
	 * @return 多个时间序列的最新数据点
	 */
	default Map<byte[], TimeSeriesMRangeElement> tsMRange(final LocalDateTime fromDateTime,
														  final LocalDateTime toDateTime,
														  final TSMRangeArgument argument, final byte[]... filters) {
		return tsMRange(fromDateTime.atZone(ZoneId.systemDefault()).toInstant(),
				toDateTime.atZone(ZoneId.systemDefault()).toInstant(), argument, filters);
	}

	/**
	 * 批量获取多个时间序列在指定时间范围内的历史数据
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.mrange/" target="_blank">https://redis.io/docs/latest/commands/ts.mrange/</a></p>
	 *
	 * @param fromDateTime
	 * 		起始时间
	 * @param toDateTime
	 * 		结束时间
	 * @param argument
	 * 		参数
	 * @param filters
	 * 		一个或多个过滤表达式
	 *
	 * @return 多个时间序列的最新数据点
	 */
	default Map<String, TimeSeriesMRangeElement> tsMRange(final ZonedDateTime fromDateTime,
														  final ZonedDateTime toDateTime,
														  final TSMRangeArgument argument, final String... filters) {
		return tsMRange(fromDateTime.toInstant(), toDateTime.toInstant(), argument, filters);
	}

	/**
	 * 批量获取多个时间序列在指定时间范围内的历史数据
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.mrange/" target="_blank">https://redis.io/docs/latest/commands/ts.mrange/</a></p>
	 *
	 * @param fromDateTime
	 * 		起始时间
	 * @param toDateTime
	 * 		结束时间
	 * @param argument
	 * 		参数
	 * @param filters
	 * 		一个或多个过滤表达式
	 *
	 * @return 多个时间序列的最新数据点
	 */
	default Map<byte[], TimeSeriesMRangeElement> tsMRange(final ZonedDateTime fromDateTime,
														  final ZonedDateTime toDateTime,
														  final TSMRangeArgument argument, final byte[]... filters) {
		return tsMRange(fromDateTime.toInstant(), toDateTime.toInstant(), argument, filters);
	}

	/**
	 * 批量获取多个时间序列在指定时间范围内的历史数据
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.mrange/" target="_blank">https://redis.io/docs/latest/commands/ts.mrange/</a></p>
	 *
	 * @param fromInstant
	 * 		起始时间
	 * @param toInstant
	 * 		结束时间
	 * @param argument
	 * 		参数
	 * @param filters
	 * 		一个或多个过滤表达式
	 *
	 * @return 多个时间序列的最新数据点
	 */
	default Map<String, TimeSeriesMRangeElement> tsMRange(final Instant fromInstant, final Instant toInstant,
														  final TSMRangeArgument argument, final String... filters) {
		return tsMRange(fromInstant.toEpochMilli(), toInstant.toEpochMilli(), argument, filters);
	}

	/**
	 * 批量获取多个时间序列在指定时间范围内的历史数据
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.mrange/" target="_blank">https://redis.io/docs/latest/commands/ts.mrange/</a></p>
	 *
	 * @param fromInstant
	 * 		起始时间
	 * @param toInstant
	 * 		结束时间
	 * @param argument
	 * 		参数
	 * @param filters
	 * 		一个或多个过滤表达式
	 *
	 * @return 多个时间序列的最新数据点
	 */
	default Map<byte[], TimeSeriesMRangeElement> tsMRange(final Instant fromInstant, final Instant toInstant,
														  final TSMRangeArgument argument, final byte[]... filters) {
		return tsMRange(fromInstant.toEpochMilli(), toInstant.toEpochMilli(), argument, filters);
	}

	/**
	 * 批量获取多个时间序列在指定时间范围内的历史数据
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.mrange/" target="_blank">https://redis.io/docs/latest/commands/ts.mrange/</a></p>
	 *
	 * @param fromDate
	 * 		起始时间
	 * @param toDate
	 * 		结束时间
	 * @param argument
	 * 		参数
	 * @param count
	 * 		返回数量
	 * @param filters
	 * 		一个或多个过滤表达式
	 *
	 * @return 多个时间序列的最新数据点
	 */
	default Map<String, TimeSeriesMRangeElement> tsMRange(final Date fromDate, final Date toDate,
														  final TSMRangeArgument argument, final int count,
														  final String... filters) {
		return tsMRange(fromDate.getTime(), toDate.getTime(), argument, count, filters);
	}

	/**
	 * 批量获取多个时间序列在指定时间范围内的历史数据
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.mrange/" target="_blank">https://redis.io/docs/latest/commands/ts.mrange/</a></p>
	 *
	 * @param fromDate
	 * 		起始时间
	 * @param toDate
	 * 		结束时间
	 * @param argument
	 * 		参数
	 * @param count
	 * 		返回数量
	 * @param filters
	 * 		一个或多个过滤表达式
	 *
	 * @return 多个时间序列的最新数据点
	 */
	default Map<byte[], TimeSeriesMRangeElement> tsMRange(final Date fromDate, final Date toDate,
														  final TSMRangeArgument argument, final int count,
														  final byte[]... filters) {
		return tsMRange(fromDate.getTime(), toDate.getTime(), argument, count, filters);
	}

	/**
	 * 批量获取多个时间序列在指定时间范围内的历史数据
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.mrange/" target="_blank">https://redis.io/docs/latest/commands/ts.mrange/</a></p>
	 *
	 * @param fromDateTime
	 * 		起始时间
	 * @param toDateTime
	 * 		结束时间
	 * @param argument
	 * 		参数
	 * @param count
	 * 		返回数量
	 * @param filters
	 * 		一个或多个过滤表达式
	 *
	 * @return 多个时间序列的最新数据点
	 */
	default Map<String, TimeSeriesMRangeElement> tsMRange(final LocalDateTime fromDateTime,
														  final LocalDateTime toDateTime,
														  final TSMRangeArgument argument, final int count,
														  final String... filters) {
		return tsMRange(fromDateTime.atZone(ZoneId.systemDefault()).toInstant(),
				toDateTime.atZone(ZoneId.systemDefault()).toInstant(), argument, count, filters);
	}

	/**
	 * 批量获取多个时间序列在指定时间范围内的历史数据
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.mrange/" target="_blank">https://redis.io/docs/latest/commands/ts.mrange/</a></p>
	 *
	 * @param fromDateTime
	 * 		起始时间
	 * @param toDateTime
	 * 		结束时间
	 * @param argument
	 * 		参数
	 * @param count
	 * 		返回数量
	 * @param filters
	 * 		一个或多个过滤表达式
	 *
	 * @return 多个时间序列的最新数据点
	 */
	default Map<byte[], TimeSeriesMRangeElement> tsMRange(final LocalDateTime fromDateTime,
														  final LocalDateTime toDateTime,
														  final TSMRangeArgument argument, final int count,
														  final byte[]... filters) {
		return tsMRange(fromDateTime.atZone(ZoneId.systemDefault()).toInstant(),
				toDateTime.atZone(ZoneId.systemDefault()).toInstant(), argument, count, filters);
	}

	/**
	 * 批量获取多个时间序列在指定时间范围内的历史数据
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.mrange/" target="_blank">https://redis.io/docs/latest/commands/ts.mrange/</a></p>
	 *
	 * @param fromDateTime
	 * 		起始时间
	 * @param toDateTime
	 * 		结束时间
	 * @param argument
	 * 		参数
	 * @param count
	 * 		返回数量
	 * @param filters
	 * 		一个或多个过滤表达式
	 *
	 * @return 多个时间序列的最新数据点
	 */
	default Map<String, TimeSeriesMRangeElement> tsMRange(final ZonedDateTime fromDateTime,
														  final ZonedDateTime toDateTime,
														  final TSMRangeArgument argument, final int count,
														  final String... filters) {
		return tsMRange(fromDateTime.toInstant(), toDateTime.toInstant(), argument, count, filters);
	}

	/**
	 * 批量获取多个时间序列在指定时间范围内的历史数据
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.mrange/" target="_blank">https://redis.io/docs/latest/commands/ts.mrange/</a></p>
	 *
	 * @param fromDateTime
	 * 		起始时间
	 * @param toDateTime
	 * 		结束时间
	 * @param argument
	 * 		参数
	 * @param count
	 * 		返回数量
	 * @param filters
	 * 		一个或多个过滤表达式
	 *
	 * @return 多个时间序列的最新数据点
	 */
	default Map<byte[], TimeSeriesMRangeElement> tsMRange(final ZonedDateTime fromDateTime,
														  final ZonedDateTime toDateTime,
														  final TSMRangeArgument argument, final int count,
														  final byte[]... filters) {
		return tsMRange(fromDateTime.toInstant(), toDateTime.toInstant(), argument, count, filters);
	}

	/**
	 * 批量获取多个时间序列在指定时间范围内的历史数据
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.mrange/" target="_blank">https://redis.io/docs/latest/commands/ts.mrange/</a></p>
	 *
	 * @param fromInstant
	 * 		起始时间
	 * @param toInstant
	 * 		结束时间
	 * @param argument
	 * 		参数
	 * @param count
	 * 		返回数量
	 * @param filters
	 * 		一个或多个过滤表达式
	 *
	 * @return 多个时间序列的最新数据点
	 */
	default Map<String, TimeSeriesMRangeElement> tsMRange(final Instant fromInstant, final Instant toInstant,
														  final TSMRangeArgument argument, final int count,
														  final String... filters) {
		return tsMRange(fromInstant.toEpochMilli(), toInstant.toEpochMilli(), argument, count, filters);
	}

	/**
	 * 批量获取多个时间序列在指定时间范围内的历史数据
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.mrange/" target="_blank">https://redis.io/docs/latest/commands/ts.mrange/</a></p>
	 *
	 * @param fromInstant
	 * 		起始时间
	 * @param toInstant
	 * 		结束时间
	 * @param argument
	 * 		参数
	 * @param count
	 * 		返回数量
	 * @param filters
	 * 		一个或多个过滤表达式
	 *
	 * @return 多个时间序列的最新数据点
	 */
	default Map<byte[], TimeSeriesMRangeElement> tsMRange(final Instant fromInstant, final Instant toInstant,
														  final TSMRangeArgument argument, final int count,
														  final byte[]... filters) {
		return tsMRange(fromInstant.toEpochMilli(), toInstant.toEpochMilli(), argument, count, filters);
	}

	/**
	 * 批量获取多个时间序列在指定时间范围内的历史数据
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.mrange/" target="_blank">https://redis.io/docs/latest/commands/ts.mrange/</a></p>
	 *
	 * @param fromDate
	 * 		起始时间
	 * @param toDate
	 * 		结束时间
	 * @param count
	 * 		返回数量
	 * @param filters
	 * 		一个或多个过滤表达式
	 *
	 * @return 多个时间序列的最新数据点
	 */
	default Map<String, TimeSeriesMRangeElement> tsMRange(final Date fromDate, final Date toDate, final int count,
														  final String... filters) {
		return tsMRange(fromDate.getTime(), toDate.getTime(), count, filters);
	}

	/**
	 * 批量获取多个时间序列在指定时间范围内的历史数据
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.mrange/" target="_blank">https://redis.io/docs/latest/commands/ts.mrange/</a></p>
	 *
	 * @param fromDate
	 * 		起始时间
	 * @param toDate
	 * 		结束时间
	 * @param count
	 * 		返回数量
	 * @param filters
	 * 		一个或多个过滤表达式
	 *
	 * @return 多个时间序列的最新数据点
	 */
	default Map<byte[], TimeSeriesMRangeElement> tsMRange(final Date fromDate, final Date toDate, final int count,
														  final byte[]... filters) {
		return tsMRange(fromDate.getTime(), toDate.getTime(), count, filters);
	}

	/**
	 * 批量获取多个时间序列在指定时间范围内的历史数据
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.mrange/" target="_blank">https://redis.io/docs/latest/commands/ts.mrange/</a></p>
	 *
	 * @param fromDateTime
	 * 		起始时间
	 * @param toDateTime
	 * 		结束时间
	 * @param count
	 * 		返回数量
	 * @param filters
	 * 		一个或多个过滤表达式
	 *
	 * @return 多个时间序列的最新数据点
	 */
	default Map<String, TimeSeriesMRangeElement> tsMRange(final LocalDateTime fromDateTime,
														  final LocalDateTime toDateTime, final int count,
														  final String... filters) {
		return tsMRange(fromDateTime.atZone(ZoneId.systemDefault()).toInstant(),
				toDateTime.atZone(ZoneId.systemDefault()).toInstant(), count, filters);
	}

	/**
	 * 批量获取多个时间序列在指定时间范围内的历史数据
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.mrange/" target="_blank">https://redis.io/docs/latest/commands/ts.mrange/</a></p>
	 *
	 * @param fromDateTime
	 * 		起始时间
	 * @param toDateTime
	 * 		结束时间
	 * @param count
	 * 		返回数量
	 * @param filters
	 * 		一个或多个过滤表达式
	 *
	 * @return 多个时间序列的最新数据点
	 */
	default Map<byte[], TimeSeriesMRangeElement> tsMRange(final LocalDateTime fromDateTime,
														  final LocalDateTime toDateTime, final int count,
														  final byte[]... filters) {
		return tsMRange(fromDateTime.atZone(ZoneId.systemDefault()).toInstant(),
				toDateTime.atZone(ZoneId.systemDefault()).toInstant(), count, filters);
	}

	/**
	 * 批量获取多个时间序列在指定时间范围内的历史数据
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.mrange/" target="_blank">https://redis.io/docs/latest/commands/ts.mrange/</a></p>
	 *
	 * @param fromDateTime
	 * 		起始时间
	 * @param toDateTime
	 * 		结束时间
	 * @param count
	 * 		返回数量
	 * @param filters
	 * 		一个或多个过滤表达式
	 *
	 * @return 多个时间序列的最新数据点
	 */
	default Map<String, TimeSeriesMRangeElement> tsMRange(final ZonedDateTime fromDateTime,
														  final ZonedDateTime toDateTime, final int count,
														  final String... filters) {
		return tsMRange(fromDateTime.toInstant(), toDateTime.toInstant(), count, filters);
	}

	/**
	 * 批量获取多个时间序列在指定时间范围内的历史数据
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.mrange/" target="_blank">https://redis.io/docs/latest/commands/ts.mrange/</a></p>
	 *
	 * @param fromDateTime
	 * 		起始时间
	 * @param toDateTime
	 * 		结束时间
	 * @param count
	 * 		返回数量
	 * @param filters
	 * 		一个或多个过滤表达式
	 *
	 * @return 多个时间序列的最新数据点
	 */
	default Map<byte[], TimeSeriesMRangeElement> tsMRange(final ZonedDateTime fromDateTime,
														  final ZonedDateTime toDateTime, final int count,
														  final byte[]... filters) {
		return tsMRange(fromDateTime.toInstant(), toDateTime.toInstant(), count, filters);
	}

	/**
	 * 批量获取多个时间序列在指定时间范围内的历史数据
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.mrange/" target="_blank">https://redis.io/docs/latest/commands/ts.mrange/</a></p>
	 *
	 * @param fromInstant
	 * 		起始时间
	 * @param toInstant
	 * 		结束时间
	 * @param count
	 * 		返回数量
	 * @param filters
	 * 		一个或多个过滤表达式
	 *
	 * @return 多个时间序列的最新数据点
	 */
	default Map<String, TimeSeriesMRangeElement> tsMRange(final Instant fromInstant, final Instant toInstant,
														  final int count, final String... filters) {
		return tsMRange(fromInstant.toEpochMilli(), toInstant.toEpochMilli(), count, filters);
	}

	/**
	 * 批量获取多个时间序列在指定时间范围内的历史数据
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.mrange/" target="_blank">https://redis.io/docs/latest/commands/ts.mrange/</a></p>
	 *
	 * @param fromInstant
	 * 		起始时间
	 * @param toInstant
	 * 		结束时间
	 * @param count
	 * 		返回数量
	 * @param filters
	 * 		一个或多个过滤表达式
	 *
	 * @return 多个时间序列的最新数据点
	 */
	default Map<byte[], TimeSeriesMRangeElement> tsMRange(final Instant fromInstant, final Instant toInstant,
														  final int count, final byte[]... filters) {
		return tsMRange(fromInstant.toEpochMilli(), toInstant.toEpochMilli(), count, filters);
	}

	@Override
	default Map<String, TimeSeriesMRangeElement> tsMRevRange(final long fromTimestamp, final long toTimestamp,
															 final String... filters) {
		return execute((client)->client.timeSeriesCommands().tsMRevRange(fromTimestamp, toTimestamp, filters));
	}

	@Override
	default Map<byte[], TimeSeriesMRangeElement> tsMRevRange(final long fromTimestamp, final long toTimestamp,
															 final byte[]... filters) {
		return execute((client)->client.timeSeriesCommands().tsMRevRange(fromTimestamp, toTimestamp, filters));
	}

	@Override
	default Map<String, TimeSeriesMRangeElement> tsMRevRange(final long fromTimestamp, final long toTimestamp,
															 final TSMRangeArgument argument, final String... filters) {
		return execute(
				(client)->client.timeSeriesCommands().tsMRevRange(fromTimestamp, toTimestamp, argument, filters));
	}

	@Override
	default Map<byte[], TimeSeriesMRangeElement> tsMRevRange(final long fromTimestamp, final long toTimestamp,
															 final TSMRangeArgument argument, final byte[]... filters) {
		return execute(
				(client)->client.timeSeriesCommands().tsMRevRange(fromTimestamp, toTimestamp, argument, filters));
	}

	@Override
	default Map<String, TimeSeriesMRangeElement> tsMRevRange(final long fromTimestamp, final long toTimestamp,
															 final TSMRangeArgument argument, final int count,
															 final String... filters) {
		return execute((client)->client.timeSeriesCommands()
				.tsMRevRange(fromTimestamp, toTimestamp, argument, count, filters));
	}

	@Override
	default Map<byte[], TimeSeriesMRangeElement> tsMRevRange(final long fromTimestamp, final long toTimestamp,
															 final TSMRangeArgument argument, final int count,
															 final byte[]... filters) {
		return execute((client)->client.timeSeriesCommands()
				.tsMRevRange(fromTimestamp, toTimestamp, argument, count, filters));
	}

	@Override
	default Map<String, TimeSeriesMRangeElement> tsMRevRange(final long fromTimestamp, final long toTimestamp,
															 final int count, final String... filters) {
		return execute((client)->client.timeSeriesCommands().tsMRevRange(fromTimestamp, toTimestamp, count, filters));
	}

	@Override
	default Map<byte[], TimeSeriesMRangeElement> tsMRevRange(final long fromTimestamp, final long toTimestamp,
															 final int count, final byte[]... filters) {
		return execute((client)->client.timeSeriesCommands().tsMRevRange(fromTimestamp, toTimestamp, count, filters));
	}

	/**
	 * 批量获取多个时间序列在指定时间范围内的历史数据，并按时间戳降序（从新到旧）排列
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.mrevrange/" target="_blank">https://redis.io/docs/latest/commands/ts.mrevrange/</a></p>
	 *
	 * @param fromDate
	 * 		起始时间
	 * @param toDate
	 * 		结束时间
	 * @param filters
	 * 		一个或多个过滤表达式
	 *
	 * @return 多个时间序列的最新数据点
	 */
	default Map<String, TimeSeriesMRangeElement> tsMRevRange(final Date fromDate, final Date toDate,
															 final String... filters) {
		return tsMRevRange(fromDate.getTime(), toDate.getTime(), filters);
	}

	/**
	 * 批量获取多个时间序列在指定时间范围内的历史数据，并按时间戳降序（从新到旧）排列
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.mrevrange/" target="_blank">https://redis.io/docs/latest/commands/ts.mrevrange/</a></p>
	 *
	 * @param fromDate
	 * 		起始时间
	 * @param toDate
	 * 		结束时间
	 * @param filters
	 * 		一个或多个过滤表达式
	 *
	 * @return 多个时间序列的最新数据点
	 */
	default Map<byte[], TimeSeriesMRangeElement> tsMRevRange(final Date fromDate, final Date toDate,
															 final byte[]... filters) {
		return tsMRevRange(fromDate.getTime(), toDate.getTime(), filters);
	}

	/**
	 * 批量获取多个时间序列在指定时间范围内的历史数据，并按时间戳降序（从新到旧）排列
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.mrevrange/" target="_blank">https://redis.io/docs/latest/commands/ts.mrevrange/</a></p>
	 *
	 * @param fromDateTime
	 * 		起始时间
	 * @param toDateTime
	 * 		结束时间
	 * @param filters
	 * 		一个或多个过滤表达式
	 *
	 * @return 多个时间序列的最新数据点
	 */
	default Map<String, TimeSeriesMRangeElement> tsMRevRange(final LocalDateTime fromDateTime,
															 final LocalDateTime toDateTime, final String... filters) {
		return tsMRevRange(fromDateTime.atZone(ZoneId.systemDefault()).toInstant(),
				toDateTime.atZone(ZoneId.systemDefault()).toInstant(), filters);
	}

	/**
	 * 批量获取多个时间序列在指定时间范围内的历史数据，并按时间戳降序（从新到旧）排列
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.mrevrange/" target="_blank">https://redis.io/docs/latest/commands/ts.mrevrange/</a></p>
	 *
	 * @param fromDateTime
	 * 		起始时间
	 * @param toDateTime
	 * 		结束时间
	 * @param filters
	 * 		一个或多个过滤表达式
	 *
	 * @return 多个时间序列的最新数据点
	 */
	default Map<byte[], TimeSeriesMRangeElement> tsMRevRange(final LocalDateTime fromDateTime,
															 final LocalDateTime toDateTime, final byte[]... filters) {
		return tsMRevRange(fromDateTime.atZone(ZoneId.systemDefault()).toInstant(),
				toDateTime.atZone(ZoneId.systemDefault()).toInstant(), filters);
	}

	/**
	 * 批量获取多个时间序列在指定时间范围内的历史数据，并按时间戳降序（从新到旧）排列
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.mrevrange/" target="_blank">https://redis.io/docs/latest/commands/ts.mrevrange/</a></p>
	 *
	 * @param fromDateTime
	 * 		起始时间
	 * @param toDateTime
	 * 		结束时间
	 * @param filters
	 * 		一个或多个过滤表达式
	 *
	 * @return 多个时间序列的最新数据点
	 */
	default Map<String, TimeSeriesMRangeElement> tsMRevRange(final ZonedDateTime fromDateTime,
															 final ZonedDateTime toDateTime, final String... filters) {
		return tsMRevRange(fromDateTime.toInstant(), toDateTime.toInstant(), filters);
	}

	/**
	 * 批量获取多个时间序列在指定时间范围内的历史数据，并按时间戳降序（从新到旧）排列
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.mrevrange/" target="_blank">https://redis.io/docs/latest/commands/ts.mrevrange/</a></p>
	 *
	 * @param fromDateTime
	 * 		起始时间
	 * @param toDateTime
	 * 		结束时间
	 * @param filters
	 * 		一个或多个过滤表达式
	 *
	 * @return 多个时间序列的最新数据点
	 */
	default Map<byte[], TimeSeriesMRangeElement> tsMRevRange(final ZonedDateTime fromDateTime,
															 final ZonedDateTime toDateTime, final byte[]... filters) {
		return tsMRevRange(fromDateTime.toInstant(), toDateTime.toInstant(), filters);
	}

	/**
	 * 批量获取多个时间序列在指定时间范围内的历史数据，并按时间戳降序（从新到旧）排列
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.mrevrange/" target="_blank">https://redis.io/docs/latest/commands/ts.mrevrange/</a></p>
	 *
	 * @param fromInstant
	 * 		起始时间
	 * @param toInstant
	 * 		结束时间
	 * @param filters
	 * 		一个或多个过滤表达式
	 *
	 * @return 多个时间序列的最新数据点
	 */
	default Map<String, TimeSeriesMRangeElement> tsMRevRange(final Instant fromInstant, final Instant toInstant,
															 final String... filters) {
		return tsMRevRange(fromInstant.toEpochMilli(), toInstant.toEpochMilli(), filters);
	}

	/**
	 * 批量获取多个时间序列在指定时间范围内的历史数据，并按时间戳降序（从新到旧）排列
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.mrevrange/" target="_blank">https://redis.io/docs/latest/commands/ts.mrevrange/</a></p>
	 *
	 * @param fromInstant
	 * 		起始时间
	 * @param toInstant
	 * 		结束时间
	 * @param filters
	 * 		一个或多个过滤表达式
	 *
	 * @return 多个时间序列的最新数据点
	 */
	default Map<byte[], TimeSeriesMRangeElement> tsMRevRange(final Instant fromInstant, final Instant toInstant,
															 final byte[]... filters) {
		return tsMRevRange(fromInstant.toEpochMilli(), toInstant.toEpochMilli(), filters);
	}

	/**
	 * 批量获取多个时间序列在指定时间范围内的历史数据，并按时间戳降序（从新到旧）排列
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.mrevrange/" target="_blank">https://redis.io/docs/latest/commands/ts.mrevrange/</a></p>
	 *
	 * @param fromDate
	 * 		起始时间
	 * @param toDate
	 * 		结束时间
	 * @param argument
	 * 		参数
	 * @param filters
	 * 		一个或多个过滤表达式
	 *
	 * @return 多个时间序列的最新数据点
	 */
	default Map<String, TimeSeriesMRangeElement> tsMRevRange(final Date fromDate, final Date toDate,
															 final TSMRangeArgument argument, final String... filters) {
		return tsMRevRange(fromDate.getTime(), toDate.getTime(), argument, filters);
	}

	/**
	 * 批量获取多个时间序列在指定时间范围内的历史数据，并按时间戳降序（从新到旧）排列
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.mrevrange/" target="_blank">https://redis.io/docs/latest/commands/ts.mrevrange/</a></p>
	 *
	 * @param fromDate
	 * 		起始时间
	 * @param toDate
	 * 		结束时间
	 * @param argument
	 * 		参数
	 * @param filters
	 * 		一个或多个过滤表达式
	 *
	 * @return 多个时间序列的最新数据点
	 */
	default Map<byte[], TimeSeriesMRangeElement> tsMRevRange(final Date fromDate, final Date toDate,
															 final TSMRangeArgument argument, final byte[]... filters) {
		return tsMRevRange(fromDate.getTime(), toDate.getTime(), argument, filters);
	}

	/**
	 * 批量获取多个时间序列在指定时间范围内的历史数据，并按时间戳降序（从新到旧）排列
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.mrevrange/" target="_blank">https://redis.io/docs/latest/commands/ts.mrevrange/</a></p>
	 *
	 * @param fromDateTime
	 * 		起始时间
	 * @param toDateTime
	 * 		结束时间
	 * @param argument
	 * 		参数
	 * @param filters
	 * 		一个或多个过滤表达式
	 *
	 * @return 多个时间序列的最新数据点
	 */
	default Map<String, TimeSeriesMRangeElement> tsMRevRange(final LocalDateTime fromDateTime,
															 final LocalDateTime toDateTime,
															 final TSMRangeArgument argument, final String... filters) {
		return tsMRevRange(fromDateTime.atZone(ZoneId.systemDefault()).toInstant(),
				toDateTime.atZone(ZoneId.systemDefault()).toInstant(), argument, filters);
	}

	/**
	 * 批量获取多个时间序列在指定时间范围内的历史数据，并按时间戳降序（从新到旧）排列
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.mrevrange/" target="_blank">https://redis.io/docs/latest/commands/ts.mrevrange/</a></p>
	 *
	 * @param fromDateTime
	 * 		起始时间
	 * @param toDateTime
	 * 		结束时间
	 * @param argument
	 * 		参数
	 * @param filters
	 * 		一个或多个过滤表达式
	 *
	 * @return 多个时间序列的最新数据点
	 */
	default Map<byte[], TimeSeriesMRangeElement> tsMRevRange(final LocalDateTime fromDateTime,
															 final LocalDateTime toDateTime,
															 final TSMRangeArgument argument, final byte[]... filters) {
		return tsMRevRange(fromDateTime.atZone(ZoneId.systemDefault()).toInstant(),
				toDateTime.atZone(ZoneId.systemDefault()).toInstant(), argument, filters);
	}

	/**
	 * 批量获取多个时间序列在指定时间范围内的历史数据，并按时间戳降序（从新到旧）排列
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.mrevrange/" target="_blank">https://redis.io/docs/latest/commands/ts.mrevrange/</a></p>
	 *
	 * @param fromDateTime
	 * 		起始时间
	 * @param toDateTime
	 * 		结束时间
	 * @param argument
	 * 		参数
	 * @param filters
	 * 		一个或多个过滤表达式
	 *
	 * @return 多个时间序列的最新数据点
	 */
	default Map<String, TimeSeriesMRangeElement> tsMRevRange(final ZonedDateTime fromDateTime,
															 final ZonedDateTime toDateTime,
															 final TSMRangeArgument argument, final String... filters) {
		return tsMRevRange(fromDateTime.toInstant(), toDateTime.toInstant(), argument, filters);
	}

	/**
	 * 批量获取多个时间序列在指定时间范围内的历史数据，并按时间戳降序（从新到旧）排列
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.mrevrange/" target="_blank">https://redis.io/docs/latest/commands/ts.mrevrange/</a></p>
	 *
	 * @param fromDateTime
	 * 		起始时间
	 * @param toDateTime
	 * 		结束时间
	 * @param argument
	 * 		参数
	 * @param filters
	 * 		一个或多个过滤表达式
	 *
	 * @return 多个时间序列的最新数据点
	 */
	default Map<byte[], TimeSeriesMRangeElement> tsMRevRange(final ZonedDateTime fromDateTime,
															 final ZonedDateTime toDateTime,
															 final TSMRangeArgument argument, final byte[]... filters) {
		return tsMRevRange(fromDateTime.toInstant(), toDateTime.toInstant(), argument, filters);
	}

	/**
	 * 批量获取多个时间序列在指定时间范围内的历史数据，并按时间戳降序（从新到旧）排列
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.mrevrange/" target="_blank">https://redis.io/docs/latest/commands/ts.mrevrange/</a></p>
	 *
	 * @param fromInstant
	 * 		起始时间
	 * @param toInstant
	 * 		结束时间
	 * @param argument
	 * 		参数
	 * @param filters
	 * 		一个或多个过滤表达式
	 *
	 * @return 多个时间序列的最新数据点
	 */
	default Map<String, TimeSeriesMRangeElement> tsMRevRange(final Instant fromInstant, final Instant toInstant,
															 final TSMRangeArgument argument, final String... filters) {
		return tsMRevRange(fromInstant.toEpochMilli(), toInstant.toEpochMilli(), argument, filters);
	}

	/**
	 * 批量获取多个时间序列在指定时间范围内的历史数据，并按时间戳降序（从新到旧）排列
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.mrevrange/" target="_blank">https://redis.io/docs/latest/commands/ts.mrevrange/</a></p>
	 *
	 * @param fromInstant
	 * 		起始时间
	 * @param toInstant
	 * 		结束时间
	 * @param argument
	 * 		参数
	 * @param filters
	 * 		一个或多个过滤表达式
	 *
	 * @return 多个时间序列的最新数据点
	 */
	default Map<byte[], TimeSeriesMRangeElement> tsMRevRange(final Instant fromInstant, final Instant toInstant,
															 final TSMRangeArgument argument, final byte[]... filters) {
		return tsMRevRange(fromInstant.toEpochMilli(), toInstant.toEpochMilli(), argument, filters);
	}

	/**
	 * 批量获取多个时间序列在指定时间范围内的历史数据，并按时间戳降序（从新到旧）排列
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.mrevrange/" target="_blank">https://redis.io/docs/latest/commands/ts.mrevrange/</a></p>
	 *
	 * @param fromDate
	 * 		起始时间
	 * @param toDate
	 * 		结束时间
	 * @param argument
	 * 		参数
	 * @param count
	 * 		返回数量
	 * @param filters
	 * 		一个或多个过滤表达式
	 *
	 * @return 多个时间序列的最新数据点
	 */
	default Map<String, TimeSeriesMRangeElement> tsMRevRange(final Date fromDate, final Date toDate,
															 final TSMRangeArgument argument, final int count,
															 final String... filters) {
		return tsMRevRange(fromDate.getTime(), toDate.getTime(), argument, count, filters);
	}

	/**
	 * 批量获取多个时间序列在指定时间范围内的历史数据，并按时间戳降序（从新到旧）排列
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.mrevrange/" target="_blank">https://redis.io/docs/latest/commands/ts.mrevrange/</a></p>
	 *
	 * @param fromDate
	 * 		起始时间
	 * @param toDate
	 * 		结束时间
	 * @param argument
	 * 		参数
	 * @param count
	 * 		返回数量
	 * @param filters
	 * 		一个或多个过滤表达式
	 *
	 * @return 多个时间序列的最新数据点
	 */
	default Map<byte[], TimeSeriesMRangeElement> tsMRevRange(final Date fromDate, final Date toDate,
															 final TSMRangeArgument argument, final int count,
															 final byte[]... filters) {
		return tsMRevRange(fromDate.getTime(), toDate.getTime(), argument, count, filters);
	}

	/**
	 * 批量获取多个时间序列在指定时间范围内的历史数据，并按时间戳降序（从新到旧）排列
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.mrevrange/" target="_blank">https://redis.io/docs/latest/commands/ts.mrevrange/</a></p>
	 *
	 * @param fromDateTime
	 * 		起始时间
	 * @param toDateTime
	 * 		结束时间
	 * @param argument
	 * 		参数
	 * @param count
	 * 		返回数量
	 * @param filters
	 * 		一个或多个过滤表达式
	 *
	 * @return 多个时间序列的最新数据点
	 */
	default Map<String, TimeSeriesMRangeElement> tsMRevRange(final LocalDateTime fromDateTime,
															 final LocalDateTime toDateTime,
															 final TSMRangeArgument argument, final int count,
															 final String... filters) {
		return tsMRevRange(fromDateTime.atZone(ZoneId.systemDefault()).toInstant(),
				toDateTime.atZone(ZoneId.systemDefault()).toInstant(), argument, count, filters);
	}

	/**
	 * 批量获取多个时间序列在指定时间范围内的历史数据，并按时间戳降序（从新到旧）排列
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.mrevrange/" target="_blank">https://redis.io/docs/latest/commands/ts.mrevrange/</a></p>
	 *
	 * @param fromDateTime
	 * 		起始时间
	 * @param toDateTime
	 * 		结束时间
	 * @param argument
	 * 		参数
	 * @param count
	 * 		返回数量
	 * @param filters
	 * 		一个或多个过滤表达式
	 *
	 * @return 多个时间序列的最新数据点
	 */
	default Map<byte[], TimeSeriesMRangeElement> tsMRevRange(final LocalDateTime fromDateTime,
															 final LocalDateTime toDateTime,
															 final TSMRangeArgument argument, final int count,
															 final byte[]... filters) {
		return tsMRevRange(fromDateTime.atZone(ZoneId.systemDefault()).toInstant(),
				toDateTime.atZone(ZoneId.systemDefault()).toInstant(), argument, count, filters);
	}

	/**
	 * 批量获取多个时间序列在指定时间范围内的历史数据，并按时间戳降序（从新到旧）排列
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.mrevrange/" target="_blank">https://redis.io/docs/latest/commands/ts.mrevrange/</a></p>
	 *
	 * @param fromDateTime
	 * 		起始时间
	 * @param toDateTime
	 * 		结束时间
	 * @param argument
	 * 		参数
	 * @param count
	 * 		返回数量
	 * @param filters
	 * 		一个或多个过滤表达式
	 *
	 * @return 多个时间序列的最新数据点
	 */
	default Map<String, TimeSeriesMRangeElement> tsMRevRange(final ZonedDateTime fromDateTime,
															 final ZonedDateTime toDateTime,
															 final TSMRangeArgument argument, final int count,
															 final String... filters) {
		return tsMRevRange(fromDateTime.toInstant(), toDateTime.toInstant(), argument, count, filters);
	}

	/**
	 * 批量获取多个时间序列在指定时间范围内的历史数据，并按时间戳降序（从新到旧）排列
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.mrevrange/" target="_blank">https://redis.io/docs/latest/commands/ts.mrevrange/</a></p>
	 *
	 * @param fromDateTime
	 * 		起始时间
	 * @param toDateTime
	 * 		结束时间
	 * @param argument
	 * 		参数
	 * @param count
	 * 		返回数量
	 * @param filters
	 * 		一个或多个过滤表达式
	 *
	 * @return 多个时间序列的最新数据点
	 */
	default Map<byte[], TimeSeriesMRangeElement> tsMRevRange(final ZonedDateTime fromDateTime,
															 final ZonedDateTime toDateTime,
															 final TSMRangeArgument argument, final int count,
															 final byte[]... filters) {
		return tsMRevRange(fromDateTime.toInstant(), toDateTime.toInstant(), argument, count, filters);
	}

	/**
	 * 批量获取多个时间序列在指定时间范围内的历史数据，并按时间戳降序（从新到旧）排列
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.mrevrange/" target="_blank">https://redis.io/docs/latest/commands/ts.mrevrange/</a></p>
	 *
	 * @param fromInstant
	 * 		起始时间
	 * @param toInstant
	 * 		结束时间
	 * @param argument
	 * 		参数
	 * @param count
	 * 		返回数量
	 * @param filters
	 * 		一个或多个过滤表达式
	 *
	 * @return 多个时间序列的最新数据点
	 */
	default Map<String, TimeSeriesMRangeElement> tsMRevRange(final Instant fromInstant, final Instant toInstant,
															 final TSMRangeArgument argument, final int count,
															 final String... filters) {
		return tsMRevRange(fromInstant.toEpochMilli(), toInstant.toEpochMilli(), argument, count, filters);
	}

	/**
	 * 批量获取多个时间序列在指定时间范围内的历史数据，并按时间戳降序（从新到旧）排列
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.mrevrange/" target="_blank">https://redis.io/docs/latest/commands/ts.mrevrange/</a></p>
	 *
	 * @param fromInstant
	 * 		起始时间
	 * @param toInstant
	 * 		结束时间
	 * @param argument
	 * 		参数
	 * @param count
	 * 		返回数量
	 * @param filters
	 * 		一个或多个过滤表达式
	 *
	 * @return 多个时间序列的最新数据点
	 */
	default Map<byte[], TimeSeriesMRangeElement> tsMRevRange(final Instant fromInstant, final Instant toInstant,
															 final TSMRangeArgument argument, final int count,
															 final byte[]... filters) {
		return tsMRevRange(fromInstant.toEpochMilli(), toInstant.toEpochMilli(), argument, count, filters);
	}

	/**
	 * 批量获取多个时间序列在指定时间范围内的历史数据，并按时间戳降序（从新到旧）排列
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.mrevrange/" target="_blank">https://redis.io/docs/latest/commands/ts.mrevrange/</a></p>
	 *
	 * @param fromDate
	 * 		起始时间
	 * @param toDate
	 * 		结束时间
	 * @param count
	 * 		返回数量
	 * @param filters
	 * 		一个或多个过滤表达式
	 *
	 * @return 多个时间序列的最新数据点
	 */
	default Map<String, TimeSeriesMRangeElement> tsMRevRange(final Date fromDate, final Date toDate, final int count,
															 final String... filters) {
		return tsMRevRange(fromDate.getTime(), toDate.getTime(), count, filters);
	}

	/**
	 * 批量获取多个时间序列在指定时间范围内的历史数据，并按时间戳降序（从新到旧）排列
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.mrevrange/" target="_blank">https://redis.io/docs/latest/commands/ts.mrevrange/</a></p>
	 *
	 * @param fromDate
	 * 		起始时间
	 * @param toDate
	 * 		结束时间
	 * @param count
	 * 		返回数量
	 * @param filters
	 * 		一个或多个过滤表达式
	 *
	 * @return 多个时间序列的最新数据点
	 */
	default Map<byte[], TimeSeriesMRangeElement> tsMRevRange(final Date fromDate, final Date toDate, final int count,
															 final byte[]... filters) {
		return tsMRevRange(fromDate.getTime(), toDate.getTime(), count, filters);
	}

	/**
	 * 批量获取多个时间序列在指定时间范围内的历史数据，并按时间戳降序（从新到旧）排列
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.mrevrange/" target="_blank">https://redis.io/docs/latest/commands/ts.mrevrange/</a></p>
	 *
	 * @param fromDateTime
	 * 		起始时间
	 * @param toDateTime
	 * 		结束时间
	 * @param count
	 * 		返回数量
	 * @param filters
	 * 		一个或多个过滤表达式
	 *
	 * @return 多个时间序列的最新数据点
	 */
	default Map<String, TimeSeriesMRangeElement> tsMRevRange(final LocalDateTime fromDateTime,
															 final LocalDateTime toDateTime, final int count,
															 final String... filters) {
		return tsMRevRange(fromDateTime.atZone(ZoneId.systemDefault()).toInstant(),
				toDateTime.atZone(ZoneId.systemDefault()).toInstant(), count, filters);
	}

	/**
	 * 批量获取多个时间序列在指定时间范围内的历史数据，并按时间戳降序（从新到旧）排列
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.mrevrange/" target="_blank">https://redis.io/docs/latest/commands/ts.mrevrange/</a></p>
	 *
	 * @param fromDateTime
	 * 		起始时间
	 * @param toDateTime
	 * 		结束时间
	 * @param count
	 * 		返回数量
	 * @param filters
	 * 		一个或多个过滤表达式
	 *
	 * @return 多个时间序列的最新数据点
	 */
	default Map<byte[], TimeSeriesMRangeElement> tsMRevRange(final LocalDateTime fromDateTime,
															 final LocalDateTime toDateTime, final int count,
															 final byte[]... filters) {
		return tsMRevRange(fromDateTime.atZone(ZoneId.systemDefault()).toInstant(),
				toDateTime.atZone(ZoneId.systemDefault()).toInstant(), count, filters);
	}

	/**
	 * 批量获取多个时间序列在指定时间范围内的历史数据，并按时间戳降序（从新到旧）排列
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.mrevrange/" target="_blank">https://redis.io/docs/latest/commands/ts.mrevrange/</a></p>
	 *
	 * @param fromDateTime
	 * 		起始时间
	 * @param toDateTime
	 * 		结束时间
	 * @param count
	 * 		返回数量
	 * @param filters
	 * 		一个或多个过滤表达式
	 *
	 * @return 多个时间序列的最新数据点
	 */
	default Map<String, TimeSeriesMRangeElement> tsMRevRange(final ZonedDateTime fromDateTime,
															 final ZonedDateTime toDateTime, final int count,
															 final String... filters) {
		return tsMRevRange(fromDateTime.toInstant(), toDateTime.toInstant(), count, filters);
	}

	/**
	 * 批量获取多个时间序列在指定时间范围内的历史数据，并按时间戳降序（从新到旧）排列
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.mrevrange/" target="_blank">https://redis.io/docs/latest/commands/ts.mrevrange/</a></p>
	 *
	 * @param fromDateTime
	 * 		起始时间
	 * @param toDateTime
	 * 		结束时间
	 * @param count
	 * 		返回数量
	 * @param filters
	 * 		一个或多个过滤表达式
	 *
	 * @return 多个时间序列的最新数据点
	 */
	default Map<byte[], TimeSeriesMRangeElement> tsMRevRange(final ZonedDateTime fromDateTime,
															 final ZonedDateTime toDateTime, final int count,
															 final byte[]... filters) {
		return tsMRevRange(fromDateTime.toInstant(), toDateTime.toInstant(), count, filters);
	}

	/**
	 * 批量获取多个时间序列在指定时间范围内的历史数据，并按时间戳降序（从新到旧）排列
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.mrevrange/" target="_blank">https://redis.io/docs/latest/commands/ts.mrevrange/</a></p>
	 *
	 * @param fromInstant
	 * 		起始时间
	 * @param toInstant
	 * 		结束时间
	 * @param count
	 * 		返回数量
	 * @param filters
	 * 		一个或多个过滤表达式
	 *
	 * @return 多个时间序列的最新数据点
	 */
	default Map<String, TimeSeriesMRangeElement> tsMRevRange(final Instant fromInstant, final Instant toInstant,
															 final int count, final String... filters) {
		return tsMRevRange(fromInstant.toEpochMilli(), toInstant.toEpochMilli(), count, filters);
	}

	/**
	 * 批量获取多个时间序列在指定时间范围内的历史数据，并按时间戳降序（从新到旧）排列
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.mrevrange/" target="_blank">https://redis.io/docs/latest/commands/ts.mrevrange/</a></p>
	 *
	 * @param fromInstant
	 * 		起始时间
	 * @param toInstant
	 * 		结束时间
	 * @param count
	 * 		返回数量
	 * @param filters
	 * 		一个或多个过滤表达式
	 *
	 * @return 多个时间序列的最新数据点
	 */
	default Map<byte[], TimeSeriesMRangeElement> tsMRevRange(final Instant fromInstant, final Instant toInstant,
															 final int count, final byte[]... filters) {
		return tsMRevRange(fromInstant.toEpochMilli(), toInstant.toEpochMilli(), count, filters);
	}

	@Override
	default List<String> tsQueryIndex(final String... filters) {
		return execute((client)->client.timeSeriesCommands().tsQueryIndex(filters));
	}

	@Override
	default List<byte[]> tsQueryIndex(final byte[]... filters) {
		return execute((client)->client.timeSeriesCommands().tsQueryIndex(filters));
	}

	@Override
	default List<TimeSeriesElement> tsRange(final String key, final long fromTimestamp, final long toTimestamp) {
		return execute((client)->client.timeSeriesCommands().tsRange(key, fromTimestamp, toTimestamp));
	}

	@Override
	default List<TimeSeriesElement> tsRange(final byte[] key, final long fromTimestamp, final long toTimestamp) {
		return execute((client)->client.timeSeriesCommands().tsRange(key, fromTimestamp, toTimestamp));
	}

	@Override
	default List<TimeSeriesElement> tsRange(final String key, final long fromTimestamp, final long toTimestamp,
											final TSRangeArgument argument) {
		return execute((client)->client.timeSeriesCommands().tsRange(key, fromTimestamp, toTimestamp, argument));
	}

	@Override
	default List<TimeSeriesElement> tsRange(final byte[] key, final long fromTimestamp, final long toTimestamp,
											final TSRangeArgument argument) {
		return execute((client)->client.timeSeriesCommands().tsRange(key, fromTimestamp, toTimestamp, argument));
	}

	@Override
	default List<TimeSeriesElement> tsRange(final String key, final long fromTimestamp, final long toTimestamp,
											final TSRangeArgument argument, final int count) {
		return execute((client)->client.timeSeriesCommands().tsRange(key, fromTimestamp, toTimestamp, argument, count));
	}

	@Override
	default List<TimeSeriesElement> tsRange(final byte[] key, final long fromTimestamp, final long toTimestamp,
											final TSRangeArgument argument, final int count) {
		return execute((client)->client.timeSeriesCommands().tsRange(key, fromTimestamp, toTimestamp, argument, count));
	}

	@Override
	default List<TimeSeriesElement> tsRange(final String key, final long fromTimestamp, final long toTimestamp,
											final int count) {
		return execute((client)->client.timeSeriesCommands().tsRange(key, fromTimestamp, toTimestamp, count));
	}

	@Override
	default List<TimeSeriesElement> tsRange(final byte[] key, final long fromTimestamp, final long toTimestamp,
											final int count) {
		return execute((client)->client.timeSeriesCommands().tsRange(key, fromTimestamp, toTimestamp, count));
	}

	/**
	 * 获取单个指定时间序列（Key）在特定时间范围内的历史数据点
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.range/" target="_blank">https://redis.io/docs/latest/commands/ts.range/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fromDate
	 * 		起始时间
	 * @param toDate
	 * 		结束时间
	 *
	 * @return 指定时间序列（Key）在特定时间范围内的历史数据点
	 */
	default List<TimeSeriesElement> tsRange(final String key, final Date fromDate, final Date toDate) {
		return tsRange(key, fromDate.getTime(), toDate.getTime());
	}

	/**
	 * 获取单个指定时间序列（Key）在特定时间范围内的历史数据点
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.range/" target="_blank">https://redis.io/docs/latest/commands/ts.range/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fromDate
	 * 		起始时间
	 * @param toDate
	 * 		结束时间
	 *
	 * @return 指定时间序列（Key）在特定时间范围内的历史数据点
	 */
	default List<TimeSeriesElement> tsRange(final byte[] key, final Date fromDate, final Date toDate) {
		return tsRange(key, fromDate.getTime(), toDate.getTime());
	}

	/**
	 * 获取单个指定时间序列（Key）在特定时间范围内的历史数据点
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.range/" target="_blank">https://redis.io/docs/latest/commands/ts.range/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fromDateTime
	 * 		起始时间
	 * @param toDateTime
	 * 		结束时间
	 *
	 * @return 指定时间序列（Key）在特定时间范围内的历史数据点
	 */
	default List<TimeSeriesElement> tsRange(final String key, final LocalDateTime fromDateTime,
											final LocalDateTime toDateTime) {
		return tsRange(key, fromDateTime.atZone(ZoneId.systemDefault()).toInstant(),
				toDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}

	/**
	 * 获取单个指定时间序列（Key）在特定时间范围内的历史数据点
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.range/" target="_blank">https://redis.io/docs/latest/commands/ts.range/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fromDateTime
	 * 		起始时间
	 * @param toDateTime
	 * 		结束时间
	 *
	 * @return 指定时间序列（Key）在特定时间范围内的历史数据点
	 */
	default List<TimeSeriesElement> tsRange(final byte[] key, final LocalDateTime fromDateTime,
											final LocalDateTime toDateTime) {
		return tsRange(key, fromDateTime.atZone(ZoneId.systemDefault()).toInstant(),
				toDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}

	/**
	 * 获取单个指定时间序列（Key）在特定时间范围内的历史数据点
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.range/" target="_blank">https://redis.io/docs/latest/commands/ts.range/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fromDateTime
	 * 		起始时间
	 * @param toDateTime
	 * 		结束时间
	 *
	 * @return 指定时间序列（Key）在特定时间范围内的历史数据点
	 */
	default List<TimeSeriesElement> tsRange(final String key, final ZonedDateTime fromDateTime,
											final ZonedDateTime toDateTime) {
		return tsRange(key, fromDateTime.toInstant(), toDateTime.toInstant());
	}

	/**
	 * 获取单个指定时间序列（Key）在特定时间范围内的历史数据点
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.range/" target="_blank">https://redis.io/docs/latest/commands/ts.range/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fromDateTime
	 * 		起始时间
	 * @param toDateTime
	 * 		结束时间
	 *
	 * @return 指定时间序列（Key）在特定时间范围内的历史数据点
	 */
	default List<TimeSeriesElement> tsRange(final byte[] key, final ZonedDateTime fromDateTime,
											final ZonedDateTime toDateTime) {
		return tsRange(key, fromDateTime.toInstant(), toDateTime.toInstant());
	}

	/**
	 * 获取单个指定时间序列（Key）在特定时间范围内的历史数据点
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.range/" target="_blank">https://redis.io/docs/latest/commands/ts.range/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fromInstant
	 * 		起始时间
	 * @param toInstant
	 * 		结束时间
	 *
	 * @return 指定时间序列（Key）在特定时间范围内的历史数据点
	 */
	default List<TimeSeriesElement> tsRange(final String key, final Instant fromInstant, final Instant toInstant) {
		return tsRange(key, fromInstant.toEpochMilli(), toInstant.toEpochMilli());
	}

	/**
	 * 获取单个指定时间序列（Key）在特定时间范围内的历史数据点
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.range/" target="_blank">https://redis.io/docs/latest/commands/ts.range/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fromInstant
	 * 		起始时间
	 * @param toInstant
	 * 		结束时间
	 *
	 * @return 指定时间序列（Key）在特定时间范围内的历史数据点
	 */
	default List<TimeSeriesElement> tsRange(final byte[] key, final Instant fromInstant, final Instant toInstant) {
		return tsRange(key, fromInstant.toEpochMilli(), toInstant.toEpochMilli());
	}

	/**
	 * 获取单个指定时间序列（Key）在特定时间范围内的历史数据点
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.range/" target="_blank">https://redis.io/docs/latest/commands/ts.range/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fromDate
	 * 		起始时间
	 * @param toDate
	 * 		结束时间
	 * @param argument
	 * 		参数
	 *
	 * @return 指定时间序列（Key）在特定时间范围内的历史数据点
	 */
	default List<TimeSeriesElement> tsRange(final String key, final Date fromDate, final Date toDate,
											final TSRangeArgument argument) {
		return tsRange(key, fromDate.getTime(), toDate.getTime(), argument);
	}

	/**
	 * 获取单个指定时间序列（Key）在特定时间范围内的历史数据点
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.range/" target="_blank">https://redis.io/docs/latest/commands/ts.range/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fromDate
	 * 		起始时间
	 * @param toDate
	 * 		结束时间
	 * @param argument
	 * 		参数
	 *
	 * @return 指定时间序列（Key）在特定时间范围内的历史数据点
	 */
	default List<TimeSeriesElement> tsRange(final byte[] key, final Date fromDate, final Date toDate,
											final TSRangeArgument argument) {
		return tsRange(key, fromDate.getTime(), toDate.getTime(), argument);
	}

	/**
	 * 获取单个指定时间序列（Key）在特定时间范围内的历史数据点
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.range/" target="_blank">https://redis.io/docs/latest/commands/ts.range/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fromDateTime
	 * 		起始时间
	 * @param toDateTime
	 * 		结束时间
	 * @param argument
	 * 		参数
	 *
	 * @return 指定时间序列（Key）在特定时间范围内的历史数据点
	 */
	default List<TimeSeriesElement> tsRange(final String key, final LocalDateTime fromDateTime,
											final LocalDateTime toDateTime, final TSRangeArgument argument) {
		return tsRange(key, fromDateTime.atZone(ZoneId.systemDefault()).toInstant(),
				toDateTime.atZone(ZoneId.systemDefault()).toInstant(), argument);
	}

	/**
	 * 获取单个指定时间序列（Key）在特定时间范围内的历史数据点
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.range/" target="_blank">https://redis.io/docs/latest/commands/ts.range/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fromDateTime
	 * 		起始时间
	 * @param toDateTime
	 * 		结束时间
	 * @param argument
	 * 		参数
	 *
	 * @return 指定时间序列（Key）在特定时间范围内的历史数据点
	 */
	default List<TimeSeriesElement> tsRange(final byte[] key, final LocalDateTime fromDateTime,
											final LocalDateTime toDateTime, final TSRangeArgument argument) {
		return tsRange(key, fromDateTime.atZone(ZoneId.systemDefault()).toInstant(),
				toDateTime.atZone(ZoneId.systemDefault()).toInstant(), argument);
	}

	/**
	 * 获取单个指定时间序列（Key）在特定时间范围内的历史数据点
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.range/" target="_blank">https://redis.io/docs/latest/commands/ts.range/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fromDateTime
	 * 		起始时间
	 * @param toDateTime
	 * 		结束时间
	 * @param argument
	 * 		参数
	 *
	 * @return 指定时间序列（Key）在特定时间范围内的历史数据点
	 */
	default List<TimeSeriesElement> tsRange(final String key, final ZonedDateTime fromDateTime,
											final ZonedDateTime toDateTime, final TSRangeArgument argument) {
		return tsRange(key, fromDateTime.toInstant(), toDateTime.toInstant(), argument);
	}

	/**
	 * 获取单个指定时间序列（Key）在特定时间范围内的历史数据点
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.range/" target="_blank">https://redis.io/docs/latest/commands/ts.range/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fromDateTime
	 * 		起始时间
	 * @param toDateTime
	 * 		结束时间
	 * @param argument
	 * 		参数
	 *
	 * @return 指定时间序列（Key）在特定时间范围内的历史数据点
	 */
	default List<TimeSeriesElement> tsRange(final byte[] key, final ZonedDateTime fromDateTime,
											final ZonedDateTime toDateTime, final TSRangeArgument argument) {
		return tsRange(key, fromDateTime.toInstant(), toDateTime.toInstant(), argument);
	}

	/**
	 * 获取单个指定时间序列（Key）在特定时间范围内的历史数据点
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.range/" target="_blank">https://redis.io/docs/latest/commands/ts.range/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fromInstant
	 * 		起始时间
	 * @param toInstant
	 * 		结束时间
	 * @param argument
	 * 		参数
	 *
	 * @return 指定时间序列（Key）在特定时间范围内的历史数据点
	 */
	default List<TimeSeriesElement> tsRange(final String key, final Instant fromInstant, final Instant toInstant,
											final TSRangeArgument argument) {
		return tsRange(key, fromInstant.toEpochMilli(), toInstant.toEpochMilli(), argument);
	}

	/**
	 * 获取单个指定时间序列（Key）在特定时间范围内的历史数据点
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.range/" target="_blank">https://redis.io/docs/latest/commands/ts.range/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fromInstant
	 * 		起始时间
	 * @param toInstant
	 * 		结束时间
	 * @param argument
	 * 		参数
	 *
	 * @return 指定时间序列（Key）在特定时间范围内的历史数据点
	 */
	default List<TimeSeriesElement> tsRange(final byte[] key, final Instant fromInstant, final Instant toInstant,
											final TSRangeArgument argument) {
		return tsRange(key, fromInstant.toEpochMilli(), toInstant.toEpochMilli(), argument);
	}

	/**
	 * 获取单个指定时间序列（Key）在特定时间范围内的历史数据点
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.range/" target="_blank">https://redis.io/docs/latest/commands/ts.range/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fromDate
	 * 		起始时间
	 * @param toDate
	 * 		结束时间
	 * @param argument
	 * 		参数
	 * @param count
	 * 		返回数量
	 *
	 * @return 指定时间序列（Key）在特定时间范围内的历史数据点
	 */
	default List<TimeSeriesElement> tsRange(final String key, final Date fromDate, final Date toDate,
											final TSRangeArgument argument, final int count) {
		return tsRange(key, fromDate.getTime(), toDate.getTime(), argument, count);
	}

	/**
	 * 获取单个指定时间序列（Key）在特定时间范围内的历史数据点
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.range/" target="_blank">https://redis.io/docs/latest/commands/ts.range/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fromDate
	 * 		起始时间
	 * @param toDate
	 * 		结束时间
	 * @param argument
	 * 		参数
	 * @param count
	 * 		返回数量
	 *
	 * @return 指定时间序列（Key）在特定时间范围内的历史数据点
	 */
	default List<TimeSeriesElement> tsRange(final byte[] key, final Date fromDate, final Date toDate,
											final TSRangeArgument argument, final int count) {
		return tsRange(key, fromDate.getTime(), toDate.getTime(), argument, count);
	}

	/**
	 * 获取单个指定时间序列（Key）在特定时间范围内的历史数据点
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.range/" target="_blank">https://redis.io/docs/latest/commands/ts.range/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fromDateTime
	 * 		起始时间
	 * @param toDateTime
	 * 		结束时间
	 * @param argument
	 * 		参数
	 * @param count
	 * 		返回数量
	 *
	 * @return 指定时间序列（Key）在特定时间范围内的历史数据点
	 */
	default List<TimeSeriesElement> tsRange(final String key, final LocalDateTime fromDateTime,
											final LocalDateTime toDateTime, final TSRangeArgument argument,
											final int count) {
		return tsRange(key, fromDateTime.atZone(ZoneId.systemDefault()).toInstant(),
				toDateTime.atZone(ZoneId.systemDefault()).toInstant(), argument, count);
	}

	/**
	 * 获取单个指定时间序列（Key）在特定时间范围内的历史数据点
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.range/" target="_blank">https://redis.io/docs/latest/commands/ts.range/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fromDateTime
	 * 		起始时间
	 * @param toDateTime
	 * 		结束时间
	 * @param argument
	 * 		参数
	 * @param count
	 * 		返回数量
	 *
	 * @return 指定时间序列（Key）在特定时间范围内的历史数据点
	 */
	default List<TimeSeriesElement> tsRange(final byte[] key, final LocalDateTime fromDateTime,
											final LocalDateTime toDateTime, final TSRangeArgument argument,
											final int count) {
		return tsRange(key, fromDateTime.atZone(ZoneId.systemDefault()).toInstant(),
				toDateTime.atZone(ZoneId.systemDefault()).toInstant(), argument, count);
	}

	/**
	 * 获取单个指定时间序列（Key）在特定时间范围内的历史数据点
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.range/" target="_blank">https://redis.io/docs/latest/commands/ts.range/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fromDateTime
	 * 		起始时间
	 * @param toDateTime
	 * 		结束时间
	 * @param argument
	 * 		参数
	 * @param count
	 * 		返回数量
	 *
	 * @return 指定时间序列（Key）在特定时间范围内的历史数据点
	 */
	default List<TimeSeriesElement> tsRange(final String key, final ZonedDateTime fromDateTime,
											final ZonedDateTime toDateTime, final TSRangeArgument argument,
											final int count) {
		return tsRange(key, fromDateTime.toInstant(), toDateTime.toInstant(), argument, count);
	}

	/**
	 * 获取单个指定时间序列（Key）在特定时间范围内的历史数据点
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.range/" target="_blank">https://redis.io/docs/latest/commands/ts.range/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fromDateTime
	 * 		起始时间
	 * @param toDateTime
	 * 		结束时间
	 * @param argument
	 * 		参数
	 * @param count
	 * 		返回数量
	 *
	 * @return 指定时间序列（Key）在特定时间范围内的历史数据点
	 */
	default List<TimeSeriesElement> tsRange(final byte[] key, final ZonedDateTime fromDateTime,
											final ZonedDateTime toDateTime, final TSRangeArgument argument,
											final int count) {
		return tsRange(key, fromDateTime.toInstant(), toDateTime.toInstant(), argument, count);
	}

	/**
	 * 获取单个指定时间序列（Key）在特定时间范围内的历史数据点
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.range/" target="_blank">https://redis.io/docs/latest/commands/ts.range/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fromInstant
	 * 		起始时间
	 * @param toInstant
	 * 		结束时间
	 * @param argument
	 * 		参数
	 * @param count
	 * 		返回数量
	 *
	 * @return 指定时间序列（Key）在特定时间范围内的历史数据点
	 */
	default List<TimeSeriesElement> tsRange(final String key, final Instant fromInstant, final Instant toInstant,
											final TSRangeArgument argument, final int count) {
		return tsRange(key, fromInstant.toEpochMilli(), toInstant.toEpochMilli(), argument, count);
	}

	/**
	 * 获取单个指定时间序列（Key）在特定时间范围内的历史数据点
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.range/" target="_blank">https://redis.io/docs/latest/commands/ts.range/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fromInstant
	 * 		起始时间
	 * @param toInstant
	 * 		结束时间
	 * @param argument
	 * 		参数
	 * @param count
	 * 		返回数量
	 *
	 * @return 指定时间序列（Key）在特定时间范围内的历史数据点
	 */
	default List<TimeSeriesElement> tsRange(final byte[] key, final Instant fromInstant, final Instant toInstant,
											final TSRangeArgument argument, final int count) {
		return tsRange(key, fromInstant.toEpochMilli(), toInstant.toEpochMilli(), argument, count);
	}

	/**
	 * 获取单个指定时间序列（Key）在特定时间范围内的历史数据点
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.range/" target="_blank">https://redis.io/docs/latest/commands/ts.range/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fromDate
	 * 		起始时间
	 * @param toDate
	 * 		结束时间
	 * @param count
	 * 		返回数量
	 *
	 * @return 指定时间序列（Key）在特定时间范围内的历史数据点
	 */
	default List<TimeSeriesElement> tsRange(final String key, final Date fromDate, final Date toDate, final int count) {
		return tsRange(key, fromDate.getTime(), toDate.getTime(), count);
	}

	/**
	 * 获取单个指定时间序列（Key）在特定时间范围内的历史数据点
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.range/" target="_blank">https://redis.io/docs/latest/commands/ts.range/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fromDate
	 * 		起始时间
	 * @param toDate
	 * 		结束时间
	 * @param count
	 * 		返回数量
	 *
	 * @return 指定时间序列（Key）在特定时间范围内的历史数据点
	 */
	default List<TimeSeriesElement> tsRange(final byte[] key, final Date fromDate, final Date toDate, final int count) {
		return tsRange(key, fromDate.getTime(), toDate.getTime(), count);
	}

	/**
	 * 获取单个指定时间序列（Key）在特定时间范围内的历史数据点
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.range/" target="_blank">https://redis.io/docs/latest/commands/ts.range/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fromDateTime
	 * 		起始时间
	 * @param toDateTime
	 * 		结束时间
	 * @param count
	 * 		返回数量
	 *
	 * @return 指定时间序列（Key）在特定时间范围内的历史数据点
	 */
	default List<TimeSeriesElement> tsRange(final String key, final LocalDateTime fromDateTime,
											final LocalDateTime toDateTime, final int count) {
		return tsRange(key, fromDateTime.atZone(ZoneId.systemDefault()).toInstant(),
				toDateTime.atZone(ZoneId.systemDefault()).toInstant(), count);
	}

	/**
	 * 获取单个指定时间序列（Key）在特定时间范围内的历史数据点
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.range/" target="_blank">https://redis.io/docs/latest/commands/ts.range/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fromDateTime
	 * 		起始时间
	 * @param toDateTime
	 * 		结束时间
	 * @param count
	 * 		返回数量
	 *
	 * @return 指定时间序列（Key）在特定时间范围内的历史数据点
	 */
	default List<TimeSeriesElement> tsRange(final byte[] key, final LocalDateTime fromDateTime,
											final LocalDateTime toDateTime, final int count) {
		return tsRange(key, fromDateTime.atZone(ZoneId.systemDefault()).toInstant(),
				toDateTime.atZone(ZoneId.systemDefault()).toInstant(), count);
	}

	/**
	 * 获取单个指定时间序列（Key）在特定时间范围内的历史数据点
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.range/" target="_blank">https://redis.io/docs/latest/commands/ts.range/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fromDateTime
	 * 		起始时间
	 * @param toDateTime
	 * 		结束时间
	 * @param count
	 * 		返回数量
	 *
	 * @return 指定时间序列（Key）在特定时间范围内的历史数据点
	 */
	default List<TimeSeriesElement> tsRange(final String key, final ZonedDateTime fromDateTime,
											final ZonedDateTime toDateTime, final int count) {
		return tsRange(key, fromDateTime.toInstant(), toDateTime.toInstant(), count);
	}

	/**
	 * 获取单个指定时间序列（Key）在特定时间范围内的历史数据点
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.range/" target="_blank">https://redis.io/docs/latest/commands/ts.range/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fromDateTime
	 * 		起始时间
	 * @param toDateTime
	 * 		结束时间
	 * @param count
	 * 		返回数量
	 *
	 * @return 指定时间序列（Key）在特定时间范围内的历史数据点
	 */
	default List<TimeSeriesElement> tsRange(final byte[] key, final ZonedDateTime fromDateTime,
											final ZonedDateTime toDateTime, final int count) {
		return tsRange(key, fromDateTime.toInstant(), toDateTime.toInstant(), count);
	}

	/**
	 * 获取单个指定时间序列（Key）在特定时间范围内的历史数据点
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.range/" target="_blank">https://redis.io/docs/latest/commands/ts.range/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fromInstant
	 * 		起始时间
	 * @param toInstant
	 * 		结束时间
	 * @param count
	 * 		返回数量
	 *
	 * @return 指定时间序列（Key）在特定时间范围内的历史数据点
	 */
	default List<TimeSeriesElement> tsRange(final String key, final Instant fromInstant, final Instant toInstant,
											final int count) {
		return tsRange(key, fromInstant.toEpochMilli(), toInstant.toEpochMilli(), count);
	}

	/**
	 * 获取单个指定时间序列（Key）在特定时间范围内的历史数据点
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.range/" target="_blank">https://redis.io/docs/latest/commands/ts.range/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fromInstant
	 * 		起始时间
	 * @param toInstant
	 * 		结束时间
	 * @param count
	 * 		返回数量
	 *
	 * @return 指定时间序列（Key）在特定时间范围内的历史数据点
	 */
	default List<TimeSeriesElement> tsRange(final byte[] key, final Instant fromInstant, final Instant toInstant,
											final int count) {
		return tsRange(key, fromInstant.toEpochMilli(), toInstant.toEpochMilli(), count);
	}

	@Override
	default List<TimeSeriesElement> tsRevRange(final String key, final long fromTimestamp, final long toTimestamp) {
		return execute((client)->client.timeSeriesCommands().tsRevRange(key, fromTimestamp, toTimestamp));
	}

	@Override
	default List<TimeSeriesElement> tsRevRange(final byte[] key, final long fromTimestamp, final long toTimestamp) {
		return execute((client)->client.timeSeriesCommands().tsRevRange(key, fromTimestamp, toTimestamp));
	}

	@Override
	default List<TimeSeriesElement> tsRevRange(final String key, final long fromTimestamp, final long toTimestamp,
											   final TSRangeArgument argument) {
		return execute((client)->client.timeSeriesCommands().tsRevRange(key, fromTimestamp, toTimestamp, argument));
	}

	@Override
	default List<TimeSeriesElement> tsRevRange(final byte[] key, final long fromTimestamp, final long toTimestamp,
											   final TSRangeArgument argument) {
		return execute((client)->client.timeSeriesCommands().tsRevRange(key, fromTimestamp, toTimestamp, argument));
	}

	@Override
	default List<TimeSeriesElement> tsRevRange(final String key, final long fromTimestamp, final long toTimestamp,
											   final TSRangeArgument argument, final int count) {
		return execute(
				(client)->client.timeSeriesCommands().tsRevRange(key, fromTimestamp, toTimestamp, argument, count));
	}

	@Override
	default List<TimeSeriesElement> tsRevRange(final byte[] key, final long fromTimestamp, final long toTimestamp,
											   final TSRangeArgument argument, final int count) {
		return execute(
				(client)->client.timeSeriesCommands().tsRevRange(key, fromTimestamp, toTimestamp, argument, count));
	}

	@Override
	default List<TimeSeriesElement> tsRevRange(final String key, final long fromTimestamp, final long toTimestamp,
											   final int count) {
		return execute((client)->client.timeSeriesCommands().tsRevRange(key, fromTimestamp, toTimestamp, count));
	}

	@Override
	default List<TimeSeriesElement> tsRevRange(final byte[] key, final long fromTimestamp, final long toTimestamp,
											   final int count) {
		return execute((client)->client.timeSeriesCommands().tsRevRange(key, fromTimestamp, toTimestamp, count));
	}

	/**
	 * 获取单个指定时间序列（Key）在特定时间范围内的历史数据点，并按时间戳降序（从新到旧）排列
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.revrange/" target="_blank">https://redis.io/docs/latest/commands/ts.revrange/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fromDate
	 * 		起始时间
	 * @param toDate
	 * 		结束时间
	 *
	 * @return 指定时间序列（Key）在特定时间范围内的历史数据点
	 */
	default List<TimeSeriesElement> tsRevRange(final String key, final Date fromDate, final Date toDate) {
		return tsRevRange(key, fromDate.getTime(), toDate.getTime());
	}

	/**
	 * 获取单个指定时间序列（Key）在特定时间范围内的历史数据点，并按时间戳降序（从新到旧）排列
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.revrange/" target="_blank">https://redis.io/docs/latest/commands/ts.revrange/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fromDate
	 * 		起始时间
	 * @param toDate
	 * 		结束时间
	 *
	 * @return 指定时间序列（Key）在特定时间范围内的历史数据点
	 */
	default List<TimeSeriesElement> tsRevRange(final byte[] key, final Date fromDate, final Date toDate) {
		return tsRevRange(key, fromDate.getTime(), toDate.getTime());
	}

	/**
	 * 获取单个指定时间序列（Key）在特定时间范围内的历史数据点，并按时间戳降序（从新到旧）排列
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.revrange/" target="_blank">https://redis.io/docs/latest/commands/ts.revrange/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fromDateTime
	 * 		起始时间
	 * @param toDateTime
	 * 		结束时间
	 *
	 * @return 指定时间序列（Key）在特定时间范围内的历史数据点
	 */
	default List<TimeSeriesElement> tsRevRange(final String key, final LocalDateTime fromDateTime,
											   final LocalDateTime toDateTime) {
		return tsRevRange(key, fromDateTime.atZone(ZoneId.systemDefault()).toInstant(),
				toDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}

	/**
	 * 获取单个指定时间序列（Key）在特定时间范围内的历史数据点，并按时间戳降序（从新到旧）排列
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.revrange/" target="_blank">https://redis.io/docs/latest/commands/ts.revrange/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fromDateTime
	 * 		起始时间
	 * @param toDateTime
	 * 		结束时间
	 *
	 * @return 指定时间序列（Key）在特定时间范围内的历史数据点
	 */
	default List<TimeSeriesElement> tsRevRange(final byte[] key, final LocalDateTime fromDateTime,
											   final LocalDateTime toDateTime) {
		return tsRevRange(key, fromDateTime.atZone(ZoneId.systemDefault()).toInstant(),
				toDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}

	/**
	 * 获取单个指定时间序列（Key）在特定时间范围内的历史数据点，并按时间戳降序（从新到旧）排列
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.revrange/" target="_blank">https://redis.io/docs/latest/commands/ts.revrange/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fromDateTime
	 * 		起始时间
	 * @param toDateTime
	 * 		结束时间
	 *
	 * @return 指定时间序列（Key）在特定时间范围内的历史数据点
	 */
	default List<TimeSeriesElement> tsRevRange(final String key, final ZonedDateTime fromDateTime,
											   final ZonedDateTime toDateTime) {
		return tsRevRange(key, fromDateTime.toInstant(), toDateTime.toInstant());
	}

	/**
	 * 获取单个指定时间序列（Key）在特定时间范围内的历史数据点，并按时间戳降序（从新到旧）排列
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.revrange/" target="_blank">https://redis.io/docs/latest/commands/ts.revrange/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fromDateTime
	 * 		起始时间
	 * @param toDateTime
	 * 		结束时间
	 *
	 * @return 指定时间序列（Key）在特定时间范围内的历史数据点
	 */
	default List<TimeSeriesElement> tsRevRange(final byte[] key, final ZonedDateTime fromDateTime,
											   final ZonedDateTime toDateTime) {
		return tsRevRange(key, fromDateTime.toInstant(), toDateTime.toInstant());
	}

	/**
	 * 获取单个指定时间序列（Key）在特定时间范围内的历史数据点，并按时间戳降序（从新到旧）排列
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.revrange/" target="_blank">https://redis.io/docs/latest/commands/ts.revrange/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fromInstant
	 * 		起始时间
	 * @param toInstant
	 * 		结束时间
	 *
	 * @return 指定时间序列（Key）在特定时间范围内的历史数据点
	 */
	default List<TimeSeriesElement> tsRevRange(final String key, final Instant fromInstant, final Instant toInstant) {
		return tsRevRange(key, fromInstant.toEpochMilli(), toInstant.toEpochMilli());
	}

	/**
	 * 获取单个指定时间序列（Key）在特定时间范围内的历史数据点，并按时间戳降序（从新到旧）排列
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.revrange/" target="_blank">https://redis.io/docs/latest/commands/ts.revrange/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fromInstant
	 * 		起始时间
	 * @param toInstant
	 * 		结束时间
	 *
	 * @return 指定时间序列（Key）在特定时间范围内的历史数据点
	 */
	default List<TimeSeriesElement> tsRevRange(final byte[] key, final Instant fromInstant, final Instant toInstant) {
		return tsRevRange(key, fromInstant.toEpochMilli(), toInstant.toEpochMilli());
	}

	/**
	 * 获取单个指定时间序列（Key）在特定时间范围内的历史数据点，并按时间戳降序（从新到旧）排列
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.revrange/" target="_blank">https://redis.io/docs/latest/commands/ts.revrange/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fromDate
	 * 		起始时间
	 * @param toDate
	 * 		结束时间
	 * @param argument
	 * 		参数
	 *
	 * @return 指定时间序列（Key）在特定时间范围内的历史数据点
	 */
	default List<TimeSeriesElement> tsRevRange(final String key, final Date fromDate, final Date toDate,
											   final TSRangeArgument argument) {
		return tsRevRange(key, fromDate.getTime(), toDate.getTime(), argument);
	}

	/**
	 * 获取单个指定时间序列（Key）在特定时间范围内的历史数据点，并按时间戳降序（从新到旧）排列
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.revrange/" target="_blank">https://redis.io/docs/latest/commands/ts.revrange/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fromDate
	 * 		起始时间
	 * @param toDate
	 * 		结束时间
	 * @param argument
	 * 		参数
	 *
	 * @return 指定时间序列（Key）在特定时间范围内的历史数据点
	 */
	default List<TimeSeriesElement> tsRevRange(final byte[] key, final Date fromDate, final Date toDate,
											   final TSRangeArgument argument) {
		return tsRevRange(key, fromDate.getTime(), toDate.getTime(), argument);
	}

	/**
	 * 获取单个指定时间序列（Key）在特定时间范围内的历史数据点，并按时间戳降序（从新到旧）排列
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.revrange/" target="_blank">https://redis.io/docs/latest/commands/ts.revrange/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fromDateTime
	 * 		起始时间
	 * @param toDateTime
	 * 		结束时间
	 * @param argument
	 * 		参数
	 *
	 * @return 指定时间序列（Key）在特定时间范围内的历史数据点
	 */
	default List<TimeSeriesElement> tsRevRange(final String key, final LocalDateTime fromDateTime,
											   final LocalDateTime toDateTime, final TSRangeArgument argument) {
		return tsRevRange(key, fromDateTime.atZone(ZoneId.systemDefault()).toInstant(),
				toDateTime.atZone(ZoneId.systemDefault()).toInstant(), argument);
	}

	/**
	 * 获取单个指定时间序列（Key）在特定时间范围内的历史数据点，并按时间戳降序（从新到旧）排列
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.revrange/" target="_blank">https://redis.io/docs/latest/commands/ts.revrange/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fromDateTime
	 * 		起始时间
	 * @param toDateTime
	 * 		结束时间
	 * @param argument
	 * 		参数
	 *
	 * @return 指定时间序列（Key）在特定时间范围内的历史数据点
	 */
	default List<TimeSeriesElement> tsRevRange(final byte[] key, final LocalDateTime fromDateTime,
											   final LocalDateTime toDateTime, final TSRangeArgument argument) {
		return tsRevRange(key, fromDateTime.atZone(ZoneId.systemDefault()).toInstant(),
				toDateTime.atZone(ZoneId.systemDefault()).toInstant(), argument);
	}

	/**
	 * 获取单个指定时间序列（Key）在特定时间范围内的历史数据点，并按时间戳降序（从新到旧）排列
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.revrange/" target="_blank">https://redis.io/docs/latest/commands/ts.revrange/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fromDateTime
	 * 		起始时间
	 * @param toDateTime
	 * 		结束时间
	 * @param argument
	 * 		参数
	 *
	 * @return 指定时间序列（Key）在特定时间范围内的历史数据点
	 */
	default List<TimeSeriesElement> tsRevRange(final String key, final ZonedDateTime fromDateTime,
											   final ZonedDateTime toDateTime, final TSRangeArgument argument) {
		return tsRevRange(key, fromDateTime.toInstant(), toDateTime.toInstant(), argument);
	}

	/**
	 * 获取单个指定时间序列（Key）在特定时间范围内的历史数据点，并按时间戳降序（从新到旧）排列
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.revrange/" target="_blank">https://redis.io/docs/latest/commands/ts.revrange/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fromDateTime
	 * 		起始时间
	 * @param toDateTime
	 * 		结束时间
	 * @param argument
	 * 		参数
	 *
	 * @return 指定时间序列（Key）在特定时间范围内的历史数据点
	 */
	default List<TimeSeriesElement> tsRevRange(final byte[] key, final ZonedDateTime fromDateTime,
											   final ZonedDateTime toDateTime, final TSRangeArgument argument) {
		return tsRevRange(key, fromDateTime.toInstant(), toDateTime.toInstant(), argument);
	}

	/**
	 * 获取单个指定时间序列（Key）在特定时间范围内的历史数据点，并按时间戳降序（从新到旧）排列
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.revrange/" target="_blank">https://redis.io/docs/latest/commands/ts.revrange/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fromInstant
	 * 		起始时间
	 * @param toInstant
	 * 		结束时间
	 * @param argument
	 * 		参数
	 *
	 * @return 指定时间序列（Key）在特定时间范围内的历史数据点
	 */
	default List<TimeSeriesElement> tsRevRange(final String key, final Instant fromInstant, final Instant toInstant,
											   final TSRangeArgument argument) {
		return tsRevRange(key, fromInstant.toEpochMilli(), toInstant.toEpochMilli(), argument);
	}

	/**
	 * 获取单个指定时间序列（Key）在特定时间范围内的历史数据点，并按时间戳降序（从新到旧）排列
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.revrange/" target="_blank">https://redis.io/docs/latest/commands/ts.revrange/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fromInstant
	 * 		起始时间
	 * @param toInstant
	 * 		结束时间
	 * @param argument
	 * 		参数
	 *
	 * @return 指定时间序列（Key）在特定时间范围内的历史数据点
	 */
	default List<TimeSeriesElement> tsRevRange(final byte[] key, final Instant fromInstant, final Instant toInstant,
											   final TSRangeArgument argument) {
		return tsRevRange(key, fromInstant.toEpochMilli(), toInstant.toEpochMilli(), argument);
	}

	/**
	 * 获取单个指定时间序列（Key）在特定时间范围内的历史数据点，并按时间戳降序（从新到旧）排列
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.revrange/" target="_blank">https://redis.io/docs/latest/commands/ts.revrange/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fromDate
	 * 		起始时间
	 * @param toDate
	 * 		结束时间
	 * @param argument
	 * 		参数
	 * @param count
	 * 		返回数量
	 *
	 * @return 指定时间序列（Key）在特定时间范围内的历史数据点
	 */
	default List<TimeSeriesElement> tsRevRange(final String key, final Date fromDate, final Date toDate,
											   final TSRangeArgument argument, final int count) {
		return tsRevRange(key, fromDate.getTime(), toDate.getTime(), argument, count);
	}

	/**
	 * 获取单个指定时间序列（Key）在特定时间范围内的历史数据点，并按时间戳降序（从新到旧）排列
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.revrange/" target="_blank">https://redis.io/docs/latest/commands/ts.revrange/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fromDate
	 * 		起始时间
	 * @param toDate
	 * 		结束时间
	 * @param argument
	 * 		参数
	 * @param count
	 * 		返回数量
	 *
	 * @return 指定时间序列（Key）在特定时间范围内的历史数据点
	 */
	default List<TimeSeriesElement> tsRevRange(final byte[] key, final Date fromDate, final Date toDate,
											   final TSRangeArgument argument, final int count) {
		return tsRevRange(key, fromDate.getTime(), toDate.getTime(), argument, count);
	}

	/**
	 * 获取单个指定时间序列（Key）在特定时间范围内的历史数据点，并按时间戳降序（从新到旧）排列
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.revrange/" target="_blank">https://redis.io/docs/latest/commands/ts.revrange/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fromDateTime
	 * 		起始时间
	 * @param toDateTime
	 * 		结束时间
	 * @param argument
	 * 		参数
	 * @param count
	 * 		返回数量
	 *
	 * @return 指定时间序列（Key）在特定时间范围内的历史数据点
	 */
	default List<TimeSeriesElement> tsRevRange(final String key, final LocalDateTime fromDateTime,
											   final LocalDateTime toDateTime, final TSRangeArgument argument,
											   final int count) {
		return tsRevRange(key, fromDateTime.atZone(ZoneId.systemDefault()).toInstant(),
				toDateTime.atZone(ZoneId.systemDefault()).toInstant(), argument, count);
	}

	/**
	 * 获取单个指定时间序列（Key）在特定时间范围内的历史数据点，并按时间戳降序（从新到旧）排列
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.revrange/" target="_blank">https://redis.io/docs/latest/commands/ts.revrange/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fromDateTime
	 * 		起始时间
	 * @param toDateTime
	 * 		结束时间
	 * @param argument
	 * 		参数
	 * @param count
	 * 		返回数量
	 *
	 * @return 指定时间序列（Key）在特定时间范围内的历史数据点
	 */
	default List<TimeSeriesElement> tsRevRange(final byte[] key, final LocalDateTime fromDateTime,
											   final LocalDateTime toDateTime, final TSRangeArgument argument,
											   final int count) {
		return tsRevRange(key, fromDateTime.atZone(ZoneId.systemDefault()).toInstant(),
				toDateTime.atZone(ZoneId.systemDefault()).toInstant(), argument, count);
	}

	/**
	 * 获取单个指定时间序列（Key）在特定时间范围内的历史数据点，并按时间戳降序（从新到旧）排列
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.revrange/" target="_blank">https://redis.io/docs/latest/commands/ts.revrange/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fromDateTime
	 * 		起始时间
	 * @param toDateTime
	 * 		结束时间
	 * @param argument
	 * 		参数
	 * @param count
	 * 		返回数量
	 *
	 * @return 指定时间序列（Key）在特定时间范围内的历史数据点
	 */
	default List<TimeSeriesElement> tsRevRange(final String key, final ZonedDateTime fromDateTime,
											   final ZonedDateTime toDateTime, final TSRangeArgument argument,
											   final int count) {
		return tsRevRange(key, fromDateTime.toInstant(), toDateTime.toInstant(), argument, count);
	}

	/**
	 * 获取单个指定时间序列（Key）在特定时间范围内的历史数据点，并按时间戳降序（从新到旧）排列
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.revrange/" target="_blank">https://redis.io/docs/latest/commands/ts.revrange/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fromDateTime
	 * 		起始时间
	 * @param toDateTime
	 * 		结束时间
	 * @param argument
	 * 		参数
	 * @param count
	 * 		返回数量
	 *
	 * @return 指定时间序列（Key）在特定时间范围内的历史数据点
	 */
	default List<TimeSeriesElement> tsRevRange(final byte[] key, final ZonedDateTime fromDateTime,
											   final ZonedDateTime toDateTime, final TSRangeArgument argument,
											   final int count) {
		return tsRevRange(key, fromDateTime.toInstant(), toDateTime.toInstant(), argument, count);
	}

	/**
	 * 获取单个指定时间序列（Key）在特定时间范围内的历史数据点，并按时间戳降序（从新到旧）排列
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.revrange/" target="_blank">https://redis.io/docs/latest/commands/ts.revrange/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fromInstant
	 * 		起始时间
	 * @param toInstant
	 * 		结束时间
	 * @param argument
	 * 		参数
	 * @param count
	 * 		返回数量
	 *
	 * @return 指定时间序列（Key）在特定时间范围内的历史数据点
	 */
	default List<TimeSeriesElement> tsRevRange(final String key, final Instant fromInstant, final Instant toInstant,
											   final TSRangeArgument argument, final int count) {
		return tsRevRange(key, fromInstant.toEpochMilli(), toInstant.toEpochMilli(), argument, count);
	}

	/**
	 * 获取单个指定时间序列（Key）在特定时间范围内的历史数据点，并按时间戳降序（从新到旧）排列
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.revrange/" target="_blank">https://redis.io/docs/latest/commands/ts.revrange/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fromInstant
	 * 		起始时间
	 * @param toInstant
	 * 		结束时间
	 * @param argument
	 * 		参数
	 * @param count
	 * 		返回数量
	 *
	 * @return 指定时间序列（Key）在特定时间范围内的历史数据点
	 */
	default List<TimeSeriesElement> tsRevRange(final byte[] key, final Instant fromInstant, final Instant toInstant,
											   final TSRangeArgument argument, final int count) {
		return tsRevRange(key, fromInstant.toEpochMilli(), toInstant.toEpochMilli(), argument, count);
	}

	/**
	 * 获取单个指定时间序列（Key）在特定时间范围内的历史数据点，并按时间戳降序（从新到旧）排列
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.revrange/" target="_blank">https://redis.io/docs/latest/commands/ts.revrange/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fromDate
	 * 		起始时间
	 * @param toDate
	 * 		结束时间
	 * @param count
	 * 		返回数量
	 *
	 * @return 指定时间序列（Key）在特定时间范围内的历史数据点
	 */
	default List<TimeSeriesElement> tsRevRange(final String key, final Date fromDate, final Date toDate,
											   final int count) {
		return tsRevRange(key, fromDate.getTime(), toDate.getTime(), count);
	}

	/**
	 * 获取单个指定时间序列（Key）在特定时间范围内的历史数据点，并按时间戳降序（从新到旧）排列
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.revrange/" target="_blank">https://redis.io/docs/latest/commands/ts.revrange/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fromDate
	 * 		起始时间
	 * @param toDate
	 * 		结束时间
	 * @param count
	 * 		返回数量
	 *
	 * @return 指定时间序列（Key）在特定时间范围内的历史数据点
	 */
	default List<TimeSeriesElement> tsRevRange(final byte[] key, final Date fromDate, final Date toDate,
											   final int count) {
		return tsRevRange(key, fromDate.getTime(), toDate.getTime(), count);
	}

	/**
	 * 获取单个指定时间序列（Key）在特定时间范围内的历史数据点，并按时间戳降序（从新到旧）排列
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.revrange/" target="_blank">https://redis.io/docs/latest/commands/ts.revrange/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fromDateTime
	 * 		起始时间
	 * @param toDateTime
	 * 		结束时间
	 * @param count
	 * 		返回数量
	 *
	 * @return 指定时间序列（Key）在特定时间范围内的历史数据点
	 */
	default List<TimeSeriesElement> tsRevRange(final String key, final LocalDateTime fromDateTime,
											   final LocalDateTime toDateTime, final int count) {
		return tsRevRange(key, fromDateTime.atZone(ZoneId.systemDefault()).toInstant(),
				toDateTime.atZone(ZoneId.systemDefault()).toInstant(), count);
	}

	/**
	 * 获取单个指定时间序列（Key）在特定时间范围内的历史数据点，并按时间戳降序（从新到旧）排列
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.revrange/" target="_blank">https://redis.io/docs/latest/commands/ts.revrange/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fromDateTime
	 * 		起始时间
	 * @param toDateTime
	 * 		结束时间
	 * @param count
	 * 		返回数量
	 *
	 * @return 指定时间序列（Key）在特定时间范围内的历史数据点
	 */
	default List<TimeSeriesElement> tsRevRange(final byte[] key, final LocalDateTime fromDateTime,
											   final LocalDateTime toDateTime, final int count) {
		return tsRevRange(key, fromDateTime.atZone(ZoneId.systemDefault()).toInstant(),
				toDateTime.atZone(ZoneId.systemDefault()).toInstant(), count);
	}

	/**
	 * 获取单个指定时间序列（Key）在特定时间范围内的历史数据点，并按时间戳降序（从新到旧）排列
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.revrange/" target="_blank">https://redis.io/docs/latest/commands/ts.revrange/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fromDateTime
	 * 		起始时间
	 * @param toDateTime
	 * 		结束时间
	 * @param count
	 * 		返回数量
	 *
	 * @return 指定时间序列（Key）在特定时间范围内的历史数据点
	 */
	default List<TimeSeriesElement> tsRevRange(final String key, final ZonedDateTime fromDateTime,
											   final ZonedDateTime toDateTime, final int count) {
		return tsRevRange(key, fromDateTime.toInstant(), toDateTime.toInstant(), count);
	}

	/**
	 * 获取单个指定时间序列（Key）在特定时间范围内的历史数据点，并按时间戳降序（从新到旧）排列
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.revrange/" target="_blank">https://redis.io/docs/latest/commands/ts.revrange/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fromDateTime
	 * 		起始时间
	 * @param toDateTime
	 * 		结束时间
	 * @param count
	 * 		返回数量
	 *
	 * @return 指定时间序列（Key）在特定时间范围内的历史数据点
	 */
	default List<TimeSeriesElement> tsRevRange(final byte[] key, final ZonedDateTime fromDateTime,
											   final ZonedDateTime toDateTime, final int count) {
		return tsRevRange(key, fromDateTime.toInstant(), toDateTime.toInstant(), count);
	}

	/**
	 * 获取单个指定时间序列（Key）在特定时间范围内的历史数据点，并按时间戳降序（从新到旧）排列
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.revrange/" target="_blank">https://redis.io/docs/latest/commands/ts.revrange/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fromInstant
	 * 		起始时间
	 * @param toInstant
	 * 		结束时间
	 * @param count
	 * 		返回数量
	 *
	 * @return 指定时间序列（Key）在特定时间范围内的历史数据点
	 */
	default List<TimeSeriesElement> tsRevRange(final String key, final Instant fromInstant, final Instant toInstant,
											   final int count) {
		return tsRevRange(key, fromInstant.toEpochMilli(), toInstant.toEpochMilli(), count);
	}

	/**
	 * 获取单个指定时间序列（Key）在特定时间范围内的历史数据点，并按时间戳降序（从新到旧）排列
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.revrange/" target="_blank">https://redis.io/docs/latest/commands/ts.revrange/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fromInstant
	 * 		起始时间
	 * @param toInstant
	 * 		结束时间
	 * @param count
	 * 		返回数量
	 *
	 * @return 指定时间序列（Key）在特定时间范围内的历史数据点
	 */
	default List<TimeSeriesElement> tsRevRange(final byte[] key, final Instant fromInstant, final Instant toInstant,
											   final int count) {
		return tsRevRange(key, fromInstant.toEpochMilli(), toInstant.toEpochMilli(), count);
	}

}
