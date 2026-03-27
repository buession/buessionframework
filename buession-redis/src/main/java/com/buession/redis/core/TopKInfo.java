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
package com.buession.redis.core;

import com.buession.redis.utils.ObjectStringBuilder;

/**
 * <code>TOPK.INFO</code> 命令结果
 *
 * @param k
 * 		设置的 Top-K 容量
 * @param size
 * 		当前结构中实际存储的元素数量
 * @param width
 * 		底层 Count-Min Sketch 的宽度（列数）；该值越大，哈希冲突越少，估算越准，但占用内存越大。
 * @param depth
 * 		底层 Count-Min Sketch 的深度（行数）；该值越大，估算越准，但写入速度稍慢。
 * @param decay
 * 		衰减率：如果是 0，表示没有衰减；如果是 0 到 1 之间的数，表示旧数据会随时间衰减，榜单更偏向于近期热门的元素
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public record TopKInfo(Integer k, Integer size, Integer width, Integer depth, Double decay) {

	@Override
	public String toString() {
		return ObjectStringBuilder.create()
				.add("k", k)
				.add("size", size)
				.add("width", width)
				.add("depth", depth)
				.add("decay", decay)
				.build();
	}

}
