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
import com.buession.redis.core.TdigestInfo;

import java.util.List;

/**
 * T-Digest 命令
 *
 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/?group=tdigest" target="_blank">https://redis.io/docs/latest/commands/?group=tdigest</a></p>
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public interface TDigestCommands extends RedisCommands {

	/**
	 * 向 t-digest 数据结构中添加一个或多个观测值
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/tdigest.add/" target="_blank">https://redis.io/docs/latest/commands/tdigest.add/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param values
	 * 		值
	 *
	 * @return 操作结果
	 */
	Status tdigestAdd(final String key, final double... values);

	/**
	 * 向 t-digest 数据结构中添加一个或多个观测值
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/tdigest.add/" target="_blank">https://redis.io/docs/latest/commands/tdigest.add/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param values
	 * 		值
	 *
	 * @return 操作结果
	 */
	Status tdigestAdd(final byte[] key, final double... values);

	/**
	 * 根据排名来估算对应的数值
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/tdigest.byrank/" target="_blank">https://redis.io/docs/latest/commands/tdigest.byrank/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param ranks
	 * 		排名
	 *
	 * @return 操作结果
	 */
	List<Double> tdigestByRank(final String key, final long... ranks);

	/**
	 * 根据排名来估算对应的数值
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/tdigest.byrank/" target="_blank">https://redis.io/docs/latest/commands/tdigest.byrank/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param ranks
	 * 		排名
	 *
	 * @return 操作结果
	 */
	List<Double> tdigestByRank(final byte[] key, final long... ranks);

	/**
	 * 根据逆向排名来估算对应的数值
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/tdigest.byrevrank/" target="_blank">https://redis.io/docs/latest/commands/tdigest.byrevrank/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param ranks
	 * 		排名
	 *
	 * @return 操作结果
	 */
	List<Double> tdigestByRevRank(final String key, final long... ranks);

	/**
	 * 根据逆向排名来估算对应的数值
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/tdigest.byrevrank/" target="_blank">https://redis.io/docs/latest/commands/tdigest.byrevrank/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param ranks
	 * 		排名
	 *
	 * @return 操作结果
	 */
	List<Double> tdigestByRevRank(final byte[] key, final long... ranks);

	/**
	 * 计算累积分布函数
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/tdigest.cdf/" target="_blank">https://redis.io/docs/latest/commands/tdigest.cdf/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param values
	 * 		值
	 *
	 * @return 返回数据集中小于或等于这些数值的观测值所占的比例（0.0 到 1.0 之间）
	 */
	List<Double> tdigestCdf(final String key, final double... values);

	/**
	 * 计算累积分布函数
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/tdigest.cdf/" target="_blank">https://redis.io/docs/latest/commands/tdigest.cdf/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param values
	 * 		值
	 *
	 * @return 返回数据集中小于或等于这些数值的观测值所占的比例（0.0 到 1.0 之间）
	 */
	List<Double> tdigestCdf(final byte[] key, final double... values);

	/**
	 * 创建一个新的 t-digest 数据结构
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/tdigest.create/" target="_blank">https://redis.io/docs/latest/commands/tdigest.create/</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return 操作结果
	 */
	Status tdigestCreate(final String key);

	/**
	 * 向 创建一个新的 t-digest 数据结构
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/tdigest.create/" target="_blank">https://redis.io/docs/latest/commands/tdigest.create/</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return 操作结果
	 */
	Status tdigestCreate(final byte[] key);

	/**
	 * 创建一个新的 t-digest 数据结构
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/tdigest.create/" target="_blank">https://redis.io/docs/latest/commands/tdigest.create/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param compression
	 * 		压缩因子
	 *
	 * @return 操作结果
	 */
	Status tdigestCreate(final String key, final int compression);

	/**
	 * 向 创建一个新的 t-digest 数据结构
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/tdigest.create/" target="_blank">https://redis.io/docs/latest/commands/tdigest.create/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param compression
	 * 		压缩因子
	 *
	 * @return 操作结果
	 */
	Status tdigestCreate(final byte[] key, final int compression);

	/**
	 * 查询 t-digest 数据结构详细信息
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/tdigest.info/" target="_blank">https://redis.io/docs/latest/commands/tdigest.info/</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return {@link TdigestInfo}
	 */
	TdigestInfo tdigestInfo(final String key);

	/**
	 * 查询 t-digest 数据结构详细信息
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/tdigest.info/" target="_blank">https://redis.io/docs/latest/commands/tdigest.info/</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return {@link TdigestInfo}
	 */
	TdigestInfo tdigestInfo(final byte[] key);

	/**
	 * 获取 t-digest 数据结构中已记录数据的最大值
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/tdigest.max/" target="_blank">https://redis.io/docs/latest/commands/tdigest.max/</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return 当前 t-digest 中记录的最大数值
	 */
	Double tdigestMax(final String key);

	/**
	 * 获取 t-digest 数据结构中已记录数据的最大值
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/tdigest.max/" target="_blank">https://redis.io/docs/latest/commands/tdigest.max/</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return 当前 t-digest 中记录的最大数值
	 */
	Double tdigestMax(final byte[] key);

	/**
	 * 将多个源 t-digest 合并到一个目标 t-digest 中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/tdigest.merge/" target="_blank">https://redis.io/docs/latest/commands/tdigest.merge/</a></p>
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param keys
	 * 		原始 Key
	 *
	 * @return 合并结果
	 */
	Status tdigestMerge(final String destKey, final String... keys);

	/**
	 * 将多个源 t-digest 合并到一个目标 t-digest 中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/tdigest.merge/" target="_blank">https://redis.io/docs/latest/commands/tdigest.merge/</a></p>
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param keys
	 * 		原始 Key
	 *
	 * @return 合并结果
	 */
	Status tdigestMerge(final byte[] destKey, final byte[]... keys);

	/**
	 * 将多个源 t-digest 合并到一个目标 t-digest 中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/tdigest.merge/" target="_blank">https://redis.io/docs/latest/commands/tdigest.merge/</a></p>
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param keys
	 * 		原始 Key
	 * @param compression
	 * 		压缩因子
	 *
	 * @return 合并结果
	 */
	Status tdigestMerge(final String destKey, final String[] keys, final int compression);

	/**
	 * 将多个源 t-digest 合并到一个目标 t-digest 中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/tdigest.merge/" target="_blank">https://redis.io/docs/latest/commands/tdigest.merge/</a></p>
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param keys
	 * 		原始 Key
	 * @param compression
	 * 		压缩因子
	 *
	 * @return 合并结果
	 */
	Status tdigestMerge(final byte[] destKey, final byte[][] keys, final int compression);

	/**
	 * 将多个源 t-digest 合并到一个目标 t-digest 中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/tdigest.merge/" target="_blank">https://redis.io/docs/latest/commands/tdigest.merge/</a></p>
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param keys
	 * 		原始 Key
	 * @param compression
	 * 		压缩因子
	 * @param override
	 * 		是否覆盖原有数据
	 *
	 * @return 合并结果
	 */
	Status tdigestMerge(final String destKey, final String[] keys, final int compression, final boolean override);

	/**
	 * 将多个源 t-digest 合并到一个目标 t-digest 中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/tdigest.merge/" target="_blank">https://redis.io/docs/latest/commands/tdigest.merge/</a></p>
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param keys
	 * 		原始 Key
	 * @param compression
	 * 		压缩因子
	 * @param override
	 * 		是否覆盖原有数据
	 *
	 * @return 合并结果
	 */
	Status tdigestMerge(final byte[] destKey, final byte[][] keys, final int compression, final boolean override);

	/**
	 * 将多个源 t-digest 合并到一个目标 t-digest 中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/tdigest.merge/" target="_blank">https://redis.io/docs/latest/commands/tdigest.merge/</a></p>
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param keys
	 * 		原始 Key
	 * @param override
	 * 		是否覆盖原有数据
	 *
	 * @return 合并结果
	 */
	Status tdigestMerge(final String destKey, final String[] keys, final boolean override);

	/**
	 * 将多个源 t-digest 合并到一个目标 t-digest 中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/tdigest.merge/" target="_blank">https://redis.io/docs/latest/commands/tdigest.merge/</a></p>
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param keys
	 * 		原始 Key
	 * @param override
	 * 		是否覆盖原有数据
	 *
	 * @return 合并结果
	 */
	Status tdigestMerge(final byte[] destKey, final byte[][] keys, final boolean override);

	/**
	 * 获取 t-digest 数据结构中已记录数据的最小值
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/tdigest.min/" target="_blank">https://redis.io/docs/latest/commands/tdigest.min/</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return 当前 t-digest 中记录的最小数值
	 */
	Double tdigestMin(final String key);

	/**
	 * 获取 t-digest 数据结构中已记录数据的最小值
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/tdigest.min/" target="_blank">https://redis.io/docs/latest/commands/tdigest.min/</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return 当前 t-digest 中记录的最小数值
	 */
	Double tdigestMin(final byte[] key);

	/**
	 * 估算 t-digest 数据结构中指定分位数对应的值
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/tdigest.quantile/" target="_blank">https://redis.io/docs/latest/commands/tdigest.quantile/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param quantiles
	 * 		一个或多个介于 0.0 到 1.0 之间的浮点数
	 *
	 * @return 合并结果
	 */
	List<Double> tdigestQuantile(final String key, final double... quantiles);

	/**
	 * 估算 t-digest 数据结构中指定分位数对应的值
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/tdigest.quantile/" target="_blank">https://redis.io/docs/latest/commands/tdigest.quantile/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param quantiles
	 * 		一个或多个介于 0.0 到 1.0 之间的浮点数
	 *
	 * @return 合并结果
	 */
	List<Double> tdigestQuantile(final byte[] key, final double... quantiles);

	/**
	 * 估算 小于或等于 指定值的观测数据点数量
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/tdigest.rank/" target="_blank">https://redis.io/docs/latest/commands/tdigest.rank/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param values
	 * 		值
	 *
	 * @return 输入值一一对应的排名
	 */
	List<Long> tdigestRank(final String key, final double... values);

	/**
	 * 估算 小于或等于 指定值的观测数据点数量
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/tdigest.rank/" target="_blank">https://redis.io/docs/latest/commands/tdigest.rank/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param values
	 * 		值
	 *
	 * @return 输入值一一对应的排名
	 */
	List<Long> tdigestRank(final byte[] key, final double... values);

	/**
	 * 清空指定 t-digest 键内所有数据
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/tdigest.reset/" target="_blank">https://redis.io/docs/latest/commands/tdigest.reset/</a></p>
	 *
	 * @param key
	 * 		目标 Key
	 *
	 * @return 合并结果
	 */
	Status tdigestReset(final String key);

	/**
	 * 清空指定 t-digest 键内所有数据
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/tdigest.reset/" target="_blank">https://redis.io/docs/latest/commands/tdigest.reset/</a></p>
	 *
	 * @param key
	 * 		目标 Key
	 *
	 * @return 合并结果
	 */
	Status tdigestReset(final byte[] key);

	/**
	 * 估算 大于或等于 指定值的观测数据点数量（即反向累积权重）
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/tdigest.revrank/" target="_blank">https://redis.io/docs/latest/commands/tdigest.revrank/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param values
	 * 		值
	 *
	 * @return 输入值一一对应的排名
	 */
	List<Long> tdigestRevRank(final String key, final double... values);

	/**
	 * 估算 大于或等于 指定值的观测数据点数量（即反向累积权重）
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/tdigest.revrank/" target="_blank">https://redis.io/docs/latest/commands/tdigest.revrank/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param values
	 * 		值
	 *
	 * @return 输入值一一对应的排名
	 */
	List<Long> tdigestRevRank(final byte[] key, final double... values);

	/**
	 * 估算 t-digest 数据结构中指定分位数对应的值
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/tdigest.quantile/" target="_blank">https://redis.io/docs/latest/commands/tdigest.quantile/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param lowCutQuantile
	 * 		低端截断比例（0.0 到 1.0）
	 * @param highCutQuantile
	 * 		高端截断比例（0.0 到 1.0）
	 *
	 * @return 计算出的截断平均值
	 */
	Double tdigestTrimmedMean(final String key, final double lowCutQuantile, final double highCutQuantile);

	/**
	 * 估算 t-digest 数据结构中指定分位数对应的值
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/tdigest.quantile/" target="_blank">https://redis.io/docs/latest/commands/tdigest.quantile/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param lowCutQuantile
	 * 		低端截断比例（0.0 到 1.0）
	 * @param highCutQuantile
	 * 		高端截断比例（0.0 到 1.0）
	 *
	 * @return 计算出的截断平均值
	 */
	Double tdigestTrimmedMean(final byte[] key, final double lowCutQuantile, final double highCutQuantile);

}
