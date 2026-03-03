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

import com.buession.lang.KeyValue;
import com.buession.lang.Status;
import com.buession.redis.core.CmsInfo;

import java.util.List;
import java.util.Map;

/**
 * 计数最小草图命令
 *
 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/?group=cms" target="_blank">https://redis.io/docs/latest/commands/?group=cms</a></p>
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public interface CountMinSketchCommands extends RedisCommands {

	/**
	 * 对 Count-Min Sketch（CMS）一个或多个元素执行频次增量更新
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/cms.incrby/" target="_blank">https://redis.io/docs/latest/commands/cms.incrby/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param items
	 * 		要增加的元素及其增加值
	 *
	 * @return 返回结果列表
	 */
	List<Long> cmsIncrby(final String key, final KeyValue<String, Long>... items);

	/**
	 * 对 Count-Min Sketch（CMS） 一个或多个元素执行频次增量更新
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/cms.incrby/" target="_blank">https://redis.io/docs/latest/commands/cms.incrby/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param items
	 * 		要增加的元素及其增加值
	 *
	 * @return 返回结果列表
	 */
	List<Long> cmsIncrby(final byte[] key, final KeyValue<byte[], Long>... items);

	/**
	 * 获取指定 Count-Min Sketch（CMS）数据结构的元信息
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/cms.info/" target="_blank">https://redis.io/docs/latest/commands/cms.info/</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return 返回 指定 Count-Min Sketch（CMS）数据结构的元信息
	 */
	CmsInfo cmsInfo(final String key);

	/**
	 * 获取指定 Count-Min Sketch（CMS）数据结构的元信息
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/cms.info/" target="_blank">https://redis.io/docs/latest/commands/cms.info/</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return 返回 指定 Count-Min Sketch（CMS）数据结构的元信息
	 */
	CmsInfo cmsInfo(final byte[] key);

	/**
	 * 创建一个 Count-Min Sketch（CMS）数据结构
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/cms.initbydim/" target="_blank">https://redis.io/docs/latest/commands/cms.initbydim/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param width
	 * 		每行的桶数量
	 * @param depth
	 * 		哈希函数的数量
	 *
	 * @return 操作结果
	 */
	Status cmsInitByDim(final String key, final int width, final int depth);

	/**
	 * 创建一个 Count-Min Sketch（CMS）数据结构
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/cms.initbydim/" target="_blank">https://redis.io/docs/latest/commands/cms.initbydim/</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return 操作结果
	 */
	Status cmsInitByDim(final byte[] key, final int width, final int depth);

	/**
	 * 根据期望的误差率和失败概率创建一个 Count-Min Sketch（CMS）数据结构
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/cms.initbyprob/" target="_blank">https://redis.io/docs/latest/commands/cms.initbyprob/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param error
	 * 		允许的最大相对误差比例
	 * @param probability
	 * 		估计值超出误差范围的概率上限
	 *
	 * @return 操作结果
	 */
	Status cmsInitByProb(final String key, final double error, final double probability);

	/**
	 * 根据期望的误差率和失败概率创建一个 Count-Min Sketch（CMS）数据结构
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/cms.initbyprob/" target="_blank">https://redis.io/docs/latest/commands/cms.initbyprob/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param error
	 * 		允许的最大相对误差比例
	 * @param probability
	 * 		估计值超出误差范围的概率上限
	 *
	 * @return 操作结果
	 */
	Status cmsInitByProb(final byte[] key, final double error, final double probability);

	/**
	 * 将多个 Count-Min Sketch（CMS）对象合并成一个新的 CMS
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/cms.merge/" target="_blank">https://redis.io/docs/latest/commands/cms.merge/</a></p>
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param keysAndWeights
	 * 		原始 Key 及其权重
	 *
	 * @return 操作结果
	 */
	Status cmsMerge(final String destKey, final Map<String, Long> keysAndWeights);

	/**
	 * 将多个 Count-Min Sketch（CMS）对象合并成一个新的 CMS
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/cms.merge/" target="_blank">https://redis.io/docs/latest/commands/cms.merge/</a></p>
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param keysAndWeights
	 * 		原始 Key 及其权重
	 *
	 * @return 操作结果
	 */
	Status cmsMerge(final byte[] destKey, final Map<byte[], Long> keysAndWeights);

	/**
	 * 从 Count-Min Sketch（CMS） 数据结构中查询一个或多个元素的频次估计值
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/cms.query/" target="_blank">https://redis.io/docs/latest/commands/cms.query/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param items
	 * 		要查询频次的一个或多个元素
	 *
	 * @return 真实频次的上界估计
	 */
	List<Long> cmsQuery(final String key, final String... items);

	/**
	 * 从 Count-Min Sketch（CMS） 数据结构中查询一个或多个元素的频次估计值
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/cms.query/" target="_blank">https://redis.io/docs/latest/commands/cms.query/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param items
	 * 		要查询频次的一个或多个元素
	 *
	 * @return 真实频次的上界估计
	 */
	List<Long> cmsQuery(final byte[] key, final byte[]... items);

}
