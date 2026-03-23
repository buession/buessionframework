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
import com.buession.redis.core.command.args.timeseries.AggregationType;
import com.buession.redis.core.command.args.timeseries.AddArgument;
import com.buession.redis.core.command.args.timeseries.AlertArgument;
import com.buession.redis.core.command.args.timeseries.CreateArgument;

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
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/ts.createrule/" target="_blank">hhttps://redis.io/docs/latest/commands/ts.createrule/</a></p>
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
	Long tsDecrBy(final String key, final double value, final AddArgument argument);

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
	Long tsDecrBy(final byte[] key, final double value, final AddArgument argument);

}
