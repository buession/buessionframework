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
package com.buession.redis.core.command;

import com.buession.lang.Status;
import com.buession.redis.core.TimeSeriesElement;
import com.buession.redis.core.TimeSeriesInfo;
import com.buession.redis.core.TimeSeriesMGetElement;
import com.buession.redis.core.AggregationType;
import com.buession.redis.core.TimeSeriesMRangeElement;
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
 * Time Series 命令
 *
 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/?group=timeseries" target="_blank">https://redis.io/docs/latest/commands/?group=timeseries</a></p>
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public interface TimeSeriesCommands extends RedisCommands {

	/**
	 * 向指定的时间序列 Key 中添加一个新的数据点
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.add/" target="_blank">https://redis.io/docs/latest/commands/ts.add/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param timestamp
	 * 		时间戳
	 * @param value
	 * 		值
	 *
	 * @return 写入数据点的时间戳（单位：毫秒）
	 */
	Long tsAdd(final String key, final long timestamp, final double value);

	/**
	 * 向指定的时间序列 Key 中添加一个新的数据点
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.add/" target="_blank">https://redis.io/docs/latest/commands/ts.add/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param timestamp
	 * 		时间戳
	 * @param value
	 * 		值
	 *
	 * @return 写入数据点的时间戳（单位：毫秒）
	 */
	Long tsAdd(final byte[] key, final long timestamp, final double value);

	/**
	 * 向指定的时间序列 Key 中添加一个新的数据点
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.add/" target="_blank">https://redis.io/docs/latest/commands/ts.add/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param timestamp
	 * 		时间戳
	 * @param value
	 * 		值
	 * @param argument
	 * 		参数
	 *
	 * @return 写入数据点的时间戳（单位：毫秒）
	 */
	Long tsAdd(final String key, final long timestamp, final double value, final AddArgument argument);

	/**
	 * 向指定的时间序列 Key 中添加一个新的数据点
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.add/" target="_blank">https://redis.io/docs/latest/commands/ts.add/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param timestamp
	 * 		时间戳
	 * @param value
	 * 		值
	 * @param argument
	 * 		参数
	 *
	 * @return 写入数据点的时间戳（单位：毫秒）
	 */
	Long tsAdd(final byte[] key, final long timestamp, final double value, final AddArgument argument);

	/**
	 * 修改现有时间序列的元数据（标签）或保留策略
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.alter/" target="_blank">https://redis.io/docs/latest/commands/ts.alter/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param argument
	 * 		参数
	 *
	 * @return 修改结果
	 */
	Status tsAlert(final String key, final AlertArgument argument);

	/**
	 * 修改现有时间序列的元数据（标签）或保留策略
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.alter/" target="_blank">https://redis.io/docs/latest/commands/ts.alter/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param argument
	 * 		参数
	 *
	 * @return 修改结果
	 */
	Status tsAlert(final byte[] key, final AlertArgument argument);

	/**
	 * 创建新时间序列
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.add/" target="_blank">https://redis.io/docs/latest/commands/ts.add/</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return 创建结果
	 */
	Status tsCreate(final String key);

	/**
	 * 创建新时间序列
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.create/" target="_blank">https://redis.io/docs/latest/commands/ts.create/</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return 创建结果
	 */
	Status tsCreate(final byte[] key);

	/**
	 * 创建新时间序列
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.create/" target="_blank">https://redis.io/docs/latest/commands/ts.create/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param argument
	 * 		参数
	 *
	 * @return 创建结果
	 */
	Status tsCreate(final String key, final CreateArgument argument);

	/**
	 * 创建新时间序列
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.create/" target="_blank">https://redis.io/docs/latest/commands/ts.create/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param argument
	 * 		参数
	 *
	 * @return 创建结果
	 */
	Status tsCreate(final byte[] key, final CreateArgument argument);

	/**
	 * 在两个时间序列 Key 之间创建下采样（Downsampling）或聚合规则
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.createrule/" target="_blank">https://redis.io/docs/latest/commands/ts.createrule/</a></p>
	 *
	 * @param sourceKey
	 * 		源 Key
	 * @param destKey
	 * 		目标 Key
	 * @param aggregationType
	 *        {@link AggregationType}
	 * @param timeBucket
	 * 		时间窗口大小(单位：毫秒)
	 *
	 * @return 创建结果
	 */
	Status tsCreateRule(final String sourceKey, final String destKey, final AggregationType aggregationType,
						final long timeBucket);

	/**
	 * 在两个时间序列 Key 之间创建下采样（Downsampling）或聚合规则
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.createrule/" target="_blank">https://redis.io/docs/latest/commands/ts.createrule/</a></p>
	 *
	 * @param sourceKey
	 * 		源 Key
	 * @param destKey
	 * 		目标 Key
	 * @param aggregationType
	 *        {@link AggregationType}
	 * @param timeBucket
	 * 		时间窗口大小(单位：毫秒)
	 *
	 * @return 创建结果
	 */
	Status tsCreateRule(final byte[] sourceKey, final byte[] destKey, final AggregationType aggregationType,
						final long timeBucket);

	/**
	 * 在两个时间序列 Key 之间创建下采样（Downsampling）或聚合规则
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.createrule/" target="_blank">https://redis.io/docs/latest/commands/ts.createrule/</a></p>
	 *
	 * @param sourceKey
	 * 		源 Key
	 * @param destKey
	 * 		目标 Key
	 * @param aggregationType
	 *        {@link AggregationType}
	 * @param timeBucket
	 * 		时间窗口大小(单位：毫秒)
	 * @param alignTimestamp
	 * 		时间对齐基准(单位：毫秒)
	 *
	 * @return 创建结果
	 */
	Status tsCreateRule(final String sourceKey, final String destKey, final AggregationType aggregationType,
						final long timeBucket, final long alignTimestamp);

	/**
	 * 在两个时间序列 Key 之间创建下采样（Downsampling）或聚合规则
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.createrule/" target="_blank">https://redis.io/docs/latest/commands/ts.createrule/</a></p>
	 *
	 * @param sourceKey
	 * 		源 Key
	 * @param destKey
	 * 		目标 Key
	 * @param aggregationType
	 *        {@link AggregationType}
	 * @param timeBucket
	 * 		时间窗口大小(单位：毫秒)
	 * @param alignTimestamp
	 * 		时间对齐基准(单位：毫秒)
	 *
	 * @return 创建结果
	 */
	Status tsCreateRule(final byte[] sourceKey, final byte[] destKey, final AggregationType aggregationType,
						final long timeBucket, final long alignTimestamp);

	/**
	 * 时间序列 Key 中减少值 value
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.add/" target="_blank">https://redis.io/docs/latest/commands/ts.add/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param value
	 * 		值
	 *
	 * @return 写入数据点的时间戳（单位：毫秒）
	 */
	Long tsDecrBy(final String key, final double value);

	/**
	 * 时间序列 Key 中减少值 value
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.add/" target="_blank">https://redis.io/docs/latest/commands/ts.add/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param value
	 * 		值
	 *
	 * @return 写入数据点的时间戳（单位：毫秒）
	 */
	Long tsDecrBy(final byte[] key, final double value);

	/**
	 * 时间序列 Key 中减少值 value
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.decrby/" target="_blank">https://redis.io/docs/latest/commands/ts.decrby/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param value
	 * 		值
	 * @param argument
	 * 		参数
	 *
	 * @return 写入数据点的时间戳（单位：毫秒）
	 */
	Long tsDecrBy(final String key, final double value, final DecrByArgument argument);

	/**
	 * 时间序列 Key 中减少值 value
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.decrby/" target="_blank">https://redis.io/docs/latest/commands/ts.decrby/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param value
	 * 		值
	 * @param argument
	 * 		参数
	 *
	 * @return 写入数据点的时间戳（单位：毫秒）
	 */
	Long tsDecrBy(final byte[] key, final double value, final DecrByArgument argument);

	/**
	 * 删除指定时间序列中某个时间范围内的所有数据点
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.del/" target="_blank">https://redis.io/docs/latest/commands/ts.del/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fromTimestamp
	 * 		删除范围的起始时间戳
	 * @param toTimestamp
	 * 		删除范围的结束时间戳
	 *
	 * @return 写入数据点的时间戳（单位：毫秒）
	 */
	Long tsDel(final String key, final long fromTimestamp, final long toTimestamp);

	/**
	 * 删除指定时间序列中某个时间范围内的所有数据点
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.del/" target="_blank">https://redis.io/docs/latest/commands/ts.del/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fromTimestamp
	 * 		删除范围的起始时间戳
	 * @param toTimestamp
	 * 		删除范围的结束时间戳
	 *
	 * @return 写入数据点的时间戳（单位：毫秒）
	 */
	Long tsDel(final byte[] key, final long fromTimestamp, final long toTimestamp);

	/**
	 * 删除两个时间序列之间的聚合规则
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.deleterule/" target="_blank">https://redis.io/docs/latest/commands/ts.deleterule/</a></p>
	 *
	 * @param sourceKey
	 * 		源 Key
	 * @param destKey
	 * 		目标 Key
	 *
	 * @return 删除结果
	 */
	Status tsDeleteRule(final String sourceKey, final String destKey);

	/**
	 * 删除两个时间序列之间的聚合规则
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.deleterule/" target="_blank">https://redis.io/docs/latest/commands/ts.deleterule/</a></p>
	 *
	 * @param sourceKey
	 * 		源 Key
	 * @param destKey
	 * 		目标 Key
	 *
	 * @return 删除结果
	 */
	Status tsDeleteRule(final byte[] sourceKey, final byte[] destKey);

	/**
	 * 获取指定时间序列的最后一个（最新）数据点
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.get/" target="_blank">https://redis.io/docs/latest/commands/ts.get/</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return 最后一个（最新）数据点
	 */
	TimeSeriesElement tsGet(final String key);

	/**
	 * 获取指定时间序列的最后一个（最新）数据点
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.get/" target="_blank">https://redis.io/docs/latest/commands/ts.get/</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return 最后一个（最新）数据点
	 */
	TimeSeriesElement tsGet(final byte[] key);

	/**
	 * 获取指定时间序列的最后一个（最新）数据点
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.get/" target="_blank">https://redis.io/docs/latest/commands/ts.get/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param latest
	 * 		is used when a time series is a compaction
	 *
	 * @return 最后一个（最新）数据点
	 */
	TimeSeriesElement tsGet(final String key, final boolean latest);

	/**
	 * 获取指定时间序列的最后一个（最新）数据点
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.get/" target="_blank">https://redis.io/docs/latest/commands/ts.get/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param latest
	 * 		is used when a time series is a compaction
	 *
	 * @return 最后一个（最新）数据点
	 */
	TimeSeriesElement tsGet(final byte[] key, final boolean latest);

	/**
	 * 时间序列 Key 中增加 value
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.incrby/" target="_blank">https://redis.io/docs/latest/commands/ts.incrby/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param value
	 * 		值
	 *
	 * @return 写入数据点的时间戳（单位：毫秒）
	 */
	Long tsIncrBy(final String key, final double value);

	/**
	 * 时间序列 Key 中增加值 value
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.incrby/" target="_blank">https://redis.io/docs/latest/commands/ts.incrby/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param value
	 * 		值
	 *
	 * @return 写入数据点的时间戳（单位：毫秒）
	 */
	Long tsIncrBy(final byte[] key, final double value);

	/**
	 * 时间序列 Key 中增加值 value
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.incrby/" target="_blank">https://redis.io/docs/latest/commands/ts.incrby/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param value
	 * 		值
	 * @param argument
	 * 		参数
	 *
	 * @return 写入数据点的时间戳（单位：毫秒）
	 */
	Long tsIncrBy(final String key, final double value, final IncrByArgument argument);

	/**
	 * 时间序列 Key 中增加值 value
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.incrby/" target="_blank">https://redis.io/docs/latest/commands/ts.incrby/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param value
	 * 		值
	 * @param argument
	 * 		参数
	 *
	 * @return 写入数据点的时间戳（单位：毫秒）
	 */
	Long tsIncrBy(final byte[] key, final double value, final IncrByArgument argument);

	/**
	 * 获取指定时间序列的元数据、配置信息和统计摘要
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.info/" target="_blank">https://redis.io/docs/latest/commands/ts.info/</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return 时间序列的元数据、配置信息和统计摘要
	 */
	TimeSeriesInfo tsInfo(final String key);

	/**
	 * 获取指定时间序列的元数据、配置信息和统计摘要
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.info/" target="_blank">https://redis.io/docs/latest/commands/ts.info/</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return 时间序列的元数据、配置信息和统计摘要
	 */
	TimeSeriesInfo tsInfo(final byte[] key);

	/**
	 * 获取指定时间序列的元数据、配置信息和统计摘要
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.info/" target="_blank">https://redis.io/docs/latest/commands/ts.info/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param debug
	 * 		DEBUG
	 *
	 * @return 时间序列的元数据、配置信息和统计摘要
	 */
	TimeSeriesInfo tsInfo(final String key, final boolean debug);

	/**
	 * 获取指定时间序列的元数据、配置信息和统计摘要
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.info/" target="_blank">https://redis.io/docs/latest/commands/ts.info/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param debug
	 * 		DEBUG
	 *
	 * @return 时间序列的元数据、配置信息和统计摘要
	 */
	TimeSeriesInfo tsInfo(final byte[] key, final boolean debug);

	/**
	 * 向指定的时间序列 Key 中批量添加数据点
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.madd/" target="_blank">https://redis.io/docs/latest/commands/ts.madd/</a></p>
	 *
	 * @param values
	 * 		数据
	 *
	 * @return 写入数据点的时间戳（单位：毫秒）
	 */
	List<Long> tsMAdd(final TSElement... values);

	/**
	 * 批量获取多个时间序列的最新数据点
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.mget/" target="_blank">https://redis.io/docs/latest/commands/ts.mget/</a></p>
	 *
	 * @param filters
	 * 		一个或多个过滤表达式
	 *
	 * @return 多个时间序列的最新数据点
	 */
	Map<String, TimeSeriesMGetElement> tsMGet(final String... filters);

	/**
	 * 批量获取多个时间序列的最新数据点
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.mget/" target="_blank">https://redis.io/docs/latest/commands/ts.mget/</a></p>
	 *
	 * @param filters
	 * 		一个或多个过滤表达式
	 *
	 * @return 多个时间序列的最新数据点
	 */
	Map<byte[], TimeSeriesMGetElement> tsMGet(final byte[]... filters);

	/**
	 * 批量获取多个时间序列的最新数据点
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.mget/" target="_blank">https://redis.io/docs/latest/commands/ts.mget/</a></p>
	 *
	 * @param argument
	 * 		参数
	 * @param filters
	 * 		一个或多个过滤表达式
	 *
	 * @return 多个时间序列的最新数据点
	 */
	Map<String, TimeSeriesMGetElement> tsMGet(final TSMGetAegument argument, final String... filters);

	/**
	 * 批量获取多个时间序列的最新数据点
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.mget/" target="_blank">https://redis.io/docs/latest/commands/ts.mget/</a></p>
	 *
	 * @param argument
	 * 		参数
	 * @param filters
	 * 		一个或多个过滤表达式
	 *
	 * @return 多个时间序列的最新数据点
	 */
	Map<byte[], TimeSeriesMGetElement> tsMGet(final TSMGetAegument argument, final byte[]... filters);

	/**
	 * 批量获取多个时间序列在指定时间范围内的历史数据
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.mrange/" target="_blank">https://redis.io/docs/latest/commands/ts.mrange/</a></p>
	 *
	 * @param fromTimestamp
	 * 		起始时间戳
	 * @param toTimestamp
	 * 		结束时间戳
	 * @param filters
	 * 		一个或多个过滤表达式
	 *
	 * @return 多个时间序列的最新数据点
	 */
	Map<String, TimeSeriesMRangeElement> tsMRange(final long fromTimestamp, final long toTimestamp,
												  final String... filters);

	/**
	 * 批量获取多个时间序列在指定时间范围内的历史数据
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.mrange/" target="_blank">https://redis.io/docs/latest/commands/ts.mrange/</a></p>
	 *
	 * @param fromTimestamp
	 * 		起始时间戳
	 * @param toTimestamp
	 * 		结束时间戳
	 * @param filters
	 * 		一个或多个过滤表达式
	 *
	 * @return 多个时间序列的最新数据点
	 */
	Map<byte[], TimeSeriesMRangeElement> tsMRange(final long fromTimestamp, final long toTimestamp,
												  final byte[]... filters);

	/**
	 * 批量获取多个时间序列在指定时间范围内的历史数据
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.mrange/" target="_blank">https://redis.io/docs/latest/commands/ts.mrange/</a></p>
	 *
	 * @param fromTimestamp
	 * 		起始时间戳
	 * @param toTimestamp
	 * 		结束时间戳
	 * @param argument
	 * 		参数
	 * @param filters
	 * 		一个或多个过滤表达式
	 *
	 * @return 多个时间序列的最新数据点
	 */
	Map<String, TimeSeriesMRangeElement> tsMRange(final long fromTimestamp, final long toTimestamp,
												  final TSMRangeArgument argument, final String... filters);

	/**
	 * 批量获取多个时间序列在指定时间范围内的历史数据
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.mrange/" target="_blank">https://redis.io/docs/latest/commands/ts.mrange/</a></p>
	 *
	 * @param fromTimestamp
	 * 		起始时间戳
	 * @param toTimestamp
	 * 		结束时间戳
	 * @param argument
	 * 		参数
	 * @param filters
	 * 		一个或多个过滤表达式
	 *
	 * @return 多个时间序列的最新数据点
	 */
	Map<byte[], TimeSeriesMRangeElement> tsMRange(final long fromTimestamp, final long toTimestamp,
												  final TSMRangeArgument argument, final byte[]... filters);

	/**
	 * 批量获取多个时间序列在指定时间范围内的历史数据
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.mrange/" target="_blank">https://redis.io/docs/latest/commands/ts.mrange/</a></p>
	 *
	 * @param fromTimestamp
	 * 		起始时间戳
	 * @param toTimestamp
	 * 		结束时间戳
	 * @param argument
	 * 		参数
	 * @param count
	 * 		返回数量
	 * @param filters
	 * 		一个或多个过滤表达式
	 *
	 * @return 多个时间序列的最新数据点
	 */
	Map<String, TimeSeriesMRangeElement> tsMRange(final long fromTimestamp, final long toTimestamp,
												  final TSMRangeArgument argument, final int count,
												  final String... filters);

	/**
	 * 批量获取多个时间序列在指定时间范围内的历史数据
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.mrange/" target="_blank">https://redis.io/docs/latest/commands/ts.mrange/</a></p>
	 *
	 * @param fromTimestamp
	 * 		起始时间戳
	 * @param toTimestamp
	 * 		结束时间戳
	 * @param argument
	 * 		参数
	 * @param count
	 * 		返回数量
	 * @param filters
	 * 		一个或多个过滤表达式
	 *
	 * @return 多个时间序列的最新数据点
	 */
	Map<byte[], TimeSeriesMRangeElement> tsMRange(final long fromTimestamp, final long toTimestamp,
												  final TSMRangeArgument argument, final int count,
												  final byte[]... filters);

	/**
	 * 批量获取多个时间序列在指定时间范围内的历史数据
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.mrange/" target="_blank">https://redis.io/docs/latest/commands/ts.mrange/</a></p>
	 *
	 * @param fromTimestamp
	 * 		起始时间戳
	 * @param toTimestamp
	 * 		结束时间戳
	 * @param count
	 * 		返回数量
	 * @param filters
	 * 		一个或多个过滤表达式
	 *
	 * @return 多个时间序列的最新数据点
	 */
	Map<String, TimeSeriesMRangeElement> tsMRange(final long fromTimestamp, final long toTimestamp,
												  final int count, final String... filters);

	/**
	 * 批量获取多个时间序列在指定时间范围内的历史数据，并按时间戳降序（从新到旧）排列
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.mrange/" target="_blank">https://redis.io/docs/latest/commands/ts.mrange/</a></p>
	 *
	 * @param fromTimestamp
	 * 		起始时间戳
	 * @param toTimestamp
	 * 		结束时间戳
	 * @param count
	 * 		返回数量
	 * @param filters
	 * 		一个或多个过滤表达式
	 *
	 * @return 多个时间序列的最新数据点
	 */
	Map<byte[], TimeSeriesMRangeElement> tsMRange(final long fromTimestamp, final long toTimestamp,
												  final int count, final byte[]... filters);

	/**
	 * 批量获取多个时间序列在指定时间范围内的历史数据，并按时间戳降序（从新到旧）排列
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.mrevrange/" target="_blank">https://redis.io/docs/latest/commands/ts.mrevrange/</a></p>
	 *
	 * @param fromTimestamp
	 * 		起始时间戳
	 * @param toTimestamp
	 * 		结束时间戳
	 * @param filters
	 * 		一个或多个过滤表达式
	 *
	 * @return 多个时间序列的最新数据点
	 */
	Map<String, TimeSeriesMRangeElement> tsMRevRange(final long fromTimestamp, final long toTimestamp,
													 final String... filters);

	/**
	 * 批量获取多个时间序列在指定时间范围内的历史数据，并按时间戳降序（从新到旧）排列
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.mrevrange/" target="_blank">https://redis.io/docs/latest/commands/ts.mrevrange/</a></p>
	 *
	 * @param fromTimestamp
	 * 		起始时间戳
	 * @param toTimestamp
	 * 		结束时间戳
	 * @param filters
	 * 		一个或多个过滤表达式
	 *
	 * @return 多个时间序列的最新数据点
	 */
	Map<byte[], TimeSeriesMRangeElement> tsMRevRange(final long fromTimestamp, final long toTimestamp,
													 final byte[]... filters);

	/**
	 * 批量获取多个时间序列在指定时间范围内的历史数据，并按时间戳降序（从新到旧）排列
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.mrevrange/" target="_blank">https://redis.io/docs/latest/commands/ts.mrevrange/</a></p>
	 *
	 * @param fromTimestamp
	 * 		起始时间戳
	 * @param toTimestamp
	 * 		结束时间戳
	 * @param argument
	 * 		参数
	 * @param filters
	 * 		一个或多个过滤表达式
	 *
	 * @return 多个时间序列的最新数据点
	 */
	Map<String, TimeSeriesMRangeElement> tsMRevRange(final long fromTimestamp, final long toTimestamp,
													 final TSMRangeArgument argument, final String... filters);

	/**
	 * 批量获取多个时间序列在指定时间范围内的历史数据，并按时间戳降序（从新到旧）排列
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.mrevrange/" target="_blank">https://redis.io/docs/latest/commands/ts.mrevrange/</a></p>
	 *
	 * @param fromTimestamp
	 * 		起始时间戳
	 * @param toTimestamp
	 * 		结束时间戳
	 * @param argument
	 * 		参数
	 * @param filters
	 * 		一个或多个过滤表达式
	 *
	 * @return 多个时间序列的最新数据点
	 */
	Map<byte[], TimeSeriesMRangeElement> tsMRevRange(final long fromTimestamp, final long toTimestamp,
													 final TSMRangeArgument argument, final byte[]... filters);

	/**
	 * 批量获取多个时间序列在指定时间范围内的历史数据，并按时间戳降序（从新到旧）排列
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.mrevrange/" target="_blank">https://redis.io/docs/latest/commands/ts.mrevrange/</a></p>
	 *
	 * @param fromTimestamp
	 * 		起始时间戳
	 * @param toTimestamp
	 * 		结束时间戳
	 * @param argument
	 * 		参数
	 * @param count
	 * 		返回数量
	 * @param filters
	 * 		一个或多个过滤表达式
	 *
	 * @return 多个时间序列的最新数据点
	 */
	Map<String, TimeSeriesMRangeElement> tsMRevRange(final long fromTimestamp, final long toTimestamp,
													 final TSMRangeArgument argument, final int count,
													 final String... filters);

	/**
	 * 批量获取多个时间序列在指定时间范围内的历史数据，并按时间戳降序（从新到旧）排列
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.mrevrange/" target="_blank">https://redis.io/docs/latest/commands/ts.mrevrange/</a></p>
	 *
	 * @param fromTimestamp
	 * 		起始时间戳
	 * @param toTimestamp
	 * 		结束时间戳
	 * @param argument
	 * 		参数
	 * @param count
	 * 		返回数量
	 * @param filters
	 * 		一个或多个过滤表达式
	 *
	 * @return 多个时间序列的最新数据点
	 */
	Map<byte[], TimeSeriesMRangeElement> tsMRevRange(final long fromTimestamp, final long toTimestamp,
													 final TSMRangeArgument argument, final int count,
													 final byte[]... filters);

	/**
	 * 批量获取多个时间序列在指定时间范围内的历史数据，并按时间戳降序（从新到旧）排列
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.mrevrange/" target="_blank">https://redis.io/docs/latest/commands/ts.mrevrange/</a></p>
	 *
	 * @param fromTimestamp
	 * 		起始时间戳
	 * @param toTimestamp
	 * 		结束时间戳
	 * @param count
	 * 		返回数量
	 * @param filters
	 * 		一个或多个过滤表达式
	 *
	 * @return 多个时间序列的最新数据点
	 */
	Map<String, TimeSeriesMRangeElement> tsMRevRange(final long fromTimestamp, final long toTimestamp,
													 final int count, final String... filters);

	/**
	 * 批量获取多个时间序列在指定时间范围内的历史数据，并按时间戳降序（从新到旧）排列
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.mrevrange/" target="_blank">https://redis.io/docs/latest/commands/ts.mrevrange/</a></p>
	 *
	 * @param fromTimestamp
	 * 		起始时间戳
	 * @param toTimestamp
	 * 		结束时间戳
	 * @param count
	 * 		返回数量
	 * @param filters
	 * 		一个或多个过滤表达式
	 *
	 * @return 多个时间序列的最新数据点
	 */
	Map<byte[], TimeSeriesMRangeElement> tsMRevRange(final long fromTimestamp, final long toTimestamp,
													 final int count, final byte[]... filters);

	/**
	 * 根据标签过滤器查找匹配的时间序列键名
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.queryindex/" target="_blank">https://redis.io/docs/latest/commands/ts.queryindex/</a></p>
	 *
	 * @param filters
	 * 		一个或多个过滤表达式
	 *
	 * @return 多个时间序列的最新数据点
	 */
	List<String> tsQueryIndex(final String... filters);

	/**
	 * 根据标签过滤器查找匹配的时间序列键名
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.queryindex/" target="_blank">https://redis.io/docs/latest/commands/ts.queryindex/</a></p>
	 *
	 * @param filters
	 * 		一个或多个过滤表达式
	 *
	 * @return 多个时间序列的最新数据点
	 */
	List<byte[]> tsQueryIndex(final byte[]... filters);

	/**
	 * 获取单个指定时间序列（Key）在特定时间范围内的历史数据点
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.range/" target="_blank">https://redis.io/docs/latest/commands/ts.range/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fromTimestamp
	 * 		起始时间戳
	 * @param toTimestamp
	 * 		结束时间戳
	 *
	 * @return 指定时间序列（Key）在特定时间范围内的历史数据点
	 */
	List<TimeSeriesElement> tsRange(final String key, final long fromTimestamp, final long toTimestamp);

	/**
	 * 获取单个指定时间序列（Key）在特定时间范围内的历史数据点
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.range/" target="_blank">https://redis.io/docs/latest/commands/ts.range/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fromTimestamp
	 * 		起始时间戳
	 * @param toTimestamp
	 * 		结束时间戳
	 *
	 * @return 指定时间序列（Key）在特定时间范围内的历史数据点
	 */
	List<TimeSeriesElement> tsRange(final byte[] key, final long fromTimestamp, final long toTimestamp);

	/**
	 * 获取单个指定时间序列（Key）在特定时间范围内的历史数据点
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.range/" target="_blank">https://redis.io/docs/latest/commands/ts.range/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fromTimestamp
	 * 		起始时间戳
	 * @param toTimestamp
	 * 		结束时间戳
	 * @param argument
	 * 		参数
	 *
	 * @return 指定时间序列（Key）在特定时间范围内的历史数据点
	 */
	List<TimeSeriesElement> tsRange(final String key, final long fromTimestamp, final long toTimestamp,
									final TSRangeArgument argument);

	/**
	 * 获取单个指定时间序列（Key）在特定时间范围内的历史数据点
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.range/" target="_blank">https://redis.io/docs/latest/commands/ts.range/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fromTimestamp
	 * 		起始时间戳
	 * @param toTimestamp
	 * 		结束时间戳
	 * @param argument
	 * 		参数
	 *
	 * @return 指定时间序列（Key）在特定时间范围内的历史数据点
	 */
	List<TimeSeriesElement> tsRange(final byte[] key, final long fromTimestamp, final long toTimestamp,
									final TSRangeArgument argument);

	/**
	 * 获取单个指定时间序列（Key）在特定时间范围内的历史数据点
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.range/" target="_blank">https://redis.io/docs/latest/commands/ts.range/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fromTimestamp
	 * 		起始时间戳
	 * @param toTimestamp
	 * 		结束时间戳
	 * @param argument
	 * 		参数
	 * @param count
	 * 		返回数量
	 *
	 * @return 指定时间序列（Key）在特定时间范围内的历史数据点
	 */
	List<TimeSeriesElement> tsRange(final String key, final long fromTimestamp, final long toTimestamp,
									final TSRangeArgument argument, final int count);

	/**
	 * 获取单个指定时间序列（Key）在特定时间范围内的历史数据点
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.range/" target="_blank">https://redis.io/docs/latest/commands/ts.range/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fromTimestamp
	 * 		起始时间戳
	 * @param toTimestamp
	 * 		结束时间戳
	 * @param argument
	 * 		参数
	 * @param count
	 * 		返回数量
	 *
	 * @return 指定时间序列（Key）在特定时间范围内的历史数据点
	 */
	List<TimeSeriesElement> tsRange(final byte[] key, final long fromTimestamp, final long toTimestamp,
									final TSRangeArgument argument, final int count);

	/**
	 * 获取单个指定时间序列（Key）在特定时间范围内的历史数据点
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.range/" target="_blank">https://redis.io/docs/latest/commands/ts.range/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fromTimestamp
	 * 		起始时间戳
	 * @param toTimestamp
	 * 		结束时间戳
	 * @param count
	 * 		返回数量
	 *
	 * @return 指定时间序列（Key）在特定时间范围内的历史数据点
	 */
	List<TimeSeriesElement> tsRange(final String key, final long fromTimestamp, final long toTimestamp,
									final int count);

	/**
	 * 获取单个指定时间序列（Key）在特定时间范围内的历史数据点
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.range/" target="_blank">https://redis.io/docs/latest/commands/ts.range/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fromTimestamp
	 * 		起始时间戳
	 * @param toTimestamp
	 * 		结束时间戳
	 * @param count
	 * 		返回数量
	 *
	 * @return 指定时间序列（Key）在特定时间范围内的历史数据点
	 */
	List<TimeSeriesElement> tsRange(final byte[] key, final long fromTimestamp, final long toTimestamp,
									final int count);

	/**
	 * 获取单个指定时间序列（Key）在特定时间范围内的历史数据点，并按时间戳降序（从新到旧）排列
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.revrange/" target="_blank">https://redis.io/docs/latest/commands/ts.revrange/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fromTimestamp
	 * 		起始时间戳
	 * @param toTimestamp
	 * 		结束时间戳
	 *
	 * @return 指定时间序列（Key）在特定时间范围内的历史数据点
	 */
	List<TimeSeriesElement> tsRevRange(final String key, final long fromTimestamp, final long toTimestamp);

	/**
	 * 获取单个指定时间序列（Key）在特定时间范围内的历史数据点，并按时间戳降序（从新到旧）排列
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.revrange/" target="_blank">https://redis.io/docs/latest/commands/ts.revrange/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fromTimestamp
	 * 		起始时间戳
	 * @param toTimestamp
	 * 		结束时间戳
	 *
	 * @return 指定时间序列（Key）在特定时间范围内的历史数据点
	 */
	List<TimeSeriesElement> tsRevRange(final byte[] key, final long fromTimestamp, final long toTimestamp);

	/**
	 * 获取单个指定时间序列（Key）在特定时间范围内的历史数据点，并按时间戳降序（从新到旧）排列
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.revrange/" target="_blank">https://redis.io/docs/latest/commands/ts.revrange/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fromTimestamp
	 * 		起始时间戳
	 * @param toTimestamp
	 * 		结束时间戳
	 * @param argument
	 * 		参数
	 *
	 * @return 指定时间序列（Key）在特定时间范围内的历史数据点
	 */
	List<TimeSeriesElement> tsRevRange(final String key, final long fromTimestamp, final long toTimestamp,
									   final TSRangeArgument argument);

	/**
	 * 获取单个指定时间序列（Key）在特定时间范围内的历史数据点，并按时间戳降序（从新到旧）排列
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.revrange/" target="_blank">https://redis.io/docs/latest/commands/ts.revrange/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fromTimestamp
	 * 		起始时间戳
	 * @param toTimestamp
	 * 		结束时间戳
	 * @param argument
	 * 		参数
	 *
	 * @return 指定时间序列（Key）在特定时间范围内的历史数据点
	 */
	List<TimeSeriesElement> tsRevRange(final byte[] key, final long fromTimestamp, final long toTimestamp,
									   final TSRangeArgument argument);

	/**
	 * 获取单个指定时间序列（Key）在特定时间范围内的历史数据点，并按时间戳降序（从新到旧）排列
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.revrange/" target="_blank">https://redis.io/docs/latest/commands/ts.revrange/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fromTimestamp
	 * 		起始时间戳
	 * @param toTimestamp
	 * 		结束时间戳
	 * @param argument
	 * 		参数
	 * @param count
	 * 		返回数量
	 *
	 * @return 指定时间序列（Key）在特定时间范围内的历史数据点
	 */
	List<TimeSeriesElement> tsRevRange(final String key, final long fromTimestamp, final long toTimestamp,
									   final TSRangeArgument argument, final int count);

	/**
	 * 获取单个指定时间序列（Key）在特定时间范围内的历史数据点，并按时间戳降序（从新到旧）排列
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.revrange/" target="_blank">https://redis.io/docs/latest/commands/ts.revrange/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fromTimestamp
	 * 		起始时间戳
	 * @param toTimestamp
	 * 		结束时间戳
	 * @param argument
	 * 		参数
	 * @param count
	 * 		返回数量
	 *
	 * @return 指定时间序列（Key）在特定时间范围内的历史数据点
	 */
	List<TimeSeriesElement> tsRevRange(final byte[] key, final long fromTimestamp, final long toTimestamp,
									   final TSRangeArgument argument, final int count);

	/**
	 * 获取单个指定时间序列（Key）在特定时间范围内的历史数据点，并按时间戳降序（从新到旧）排列
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.revrange/" target="_blank">https://redis.io/docs/latest/commands/ts.revrange/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fromTimestamp
	 * 		起始时间戳
	 * @param toTimestamp
	 * 		结束时间戳
	 * @param count
	 * 		返回数量
	 *
	 * @return 指定时间序列（Key）在特定时间范围内的历史数据点
	 */
	List<TimeSeriesElement> tsRevRange(final String key, final long fromTimestamp, final long toTimestamp,
									   final int count);

	/**
	 * 获取单个指定时间序列（Key）在特定时间范围内的历史数据点，并按时间戳降序（从新到旧）排列
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.revrange/" target="_blank">https://redis.io/docs/latest/commands/ts.revrange/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fromTimestamp
	 * 		起始时间戳
	 * @param toTimestamp
	 * 		结束时间戳
	 * @param count
	 * 		返回数量
	 *
	 * @return 指定时间序列（Key）在特定时间范围内的历史数据点
	 */
	List<TimeSeriesElement> tsRevRange(final byte[] key, final long fromTimestamp, final long toTimestamp,
									   final int count);
}
