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
package com.buession.redis.core.command.args;

import com.buession.lang.Order;
import com.buession.redis.core.Keyword;
import com.buession.redis.utils.ArgStringBuilder;

/**
 * GEO RADIUS 参数
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public class GeoRadiusArgument extends BaseGeoDistanceArgument {

	/**
	 * 构造函数
	 */
	public GeoRadiusArgument() {
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param withCoord
	 * 		是否将位置元素的经度和维度也一并返回
	 * @param withDist
	 * 		是否在返回位置元素的同时，将位置元素与中心之间的距离也一并返回
	 * @param withHash
	 * 		是否返回位置元素经过原始 geohash 编码的有序集合分值
	 */
	public GeoRadiusArgument(boolean withCoord, boolean withDist, boolean withHash) {
		super(withCoord, withDist, withHash);
	}

	/**
	 * 构造函数
	 *
	 * @param withCoord
	 * 		是否将位置元素的经度和维度也一并返回
	 * @param withDist
	 * 		是否在返回位置元素的同时，将位置元素与中心之间的距离也一并返回
	 * @param withHash
	 * 		是否返回位置元素经过原始 geohash 编码的有序集合分值
	 * @param order
	 * 		排序方式
	 */
	public GeoRadiusArgument(boolean withCoord, boolean withDist, boolean withHash, Order order) {
		super(withCoord, withDist, withHash, order);
	}

	/**
	 * 构造函数
	 *
	 * @param withCoord
	 * 		是否将位置元素的经度和维度也一并返回
	 * @param withDist
	 * 		是否在返回位置元素的同时，将位置元素与中心之间的距离也一并返回
	 * @param withHash
	 * 		是否返回位置元素经过原始 geohash 编码的有序集合分值
	 * @param count
	 * 		数量
	 * @param any
	 * 		-
	 */
	public GeoRadiusArgument(boolean withCoord, boolean withDist, boolean withHash, int count, boolean any) {
		super(withCoord, withDist, withHash, count, any);
	}

	/**
	 * 构造函数
	 *
	 * @param withCoord
	 * 		是否将位置元素的经度和维度也一并返回
	 * @param withDist
	 * 		是否在返回位置元素的同时，将位置元素与中心之间的距离也一并返回
	 * @param withHash
	 * 		是否返回位置元素经过原始 geohash 编码的有序集合分值
	 * @param order
	 * 		排序方式
	 * @param count
	 * 		数量
	 * @param any
	 * 		-
	 */
	public GeoRadiusArgument(boolean withCoord, boolean withDist, boolean withHash, Order order, int count,
							 boolean any) {
		super(withCoord, withDist, withHash, order, count, any);
	}

	/**
	 * 构造函数
	 *
	 * @param order
	 * 		排序方式
	 */
	public GeoRadiusArgument(Order order) {
		super(order);
	}

	/**
	 * 构造函数
	 *
	 * @param count
	 * 		数量
	 * @param any
	 * 		-
	 */
	public GeoRadiusArgument(int count, boolean any) {
		super(count, any);
	}

	/**
	 * 构造函数
	 *
	 * @param order
	 * 		排序方式
	 * @param count
	 * 		数量
	 * @param any
	 * 		-
	 */
	public GeoRadiusArgument(Order order, int count, boolean any) {
		super(order, count, any);
	}

	@Override
	public String toString() {
		return ArgStringBuilder.create()
				.append(Boolean.TRUE.equals(getWithCoord()) ? "WITHCOORD" : null)
				.append(Boolean.TRUE.equals(getWithDist()) ? "WITHDIST" : null)
				.append(Boolean.TRUE.equals(getWithHash()) ? "WITHHASH" : null)
				.add(Keyword.Common.COUNT.name(), getCount() == null ? null : getCount() + " " + Keyword.Common.ANY)
				.append(getOrder())
				.build();
	}

}
