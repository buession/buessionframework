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
package com.buession.redis.core.command.args.geo;

import com.buession.lang.Order;
import com.buession.redis.utils.ArgStringBuilder;

/**
 *
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public abstract class BaseGeoDistanceArgument {

	/**
	 * 是否将位置元素的经度和维度也一并返回
	 */
	protected Boolean withCoord;

	/**
	 * 是否在返回位置元素的同时，将位置元素与中心之间的距离也一并返回
	 */
	protected Boolean withDist;

	/**
	 * 是否返回位置元素经过原始 geohash 编码的有序集合分值
	 */
	protected Boolean withHash;

	/**
	 * 排序方式
	 */
	protected Order order;

	/**
	 * 构造函数
	 */
	protected BaseGeoDistanceArgument() {

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
	protected BaseGeoDistanceArgument(final boolean withCoord, final boolean withDist, final boolean withHash) {
		this.withCoord = withCoord;
		this.withDist = withDist;
		this.withHash = withHash;
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
	protected BaseGeoDistanceArgument(final boolean withCoord, final boolean withDist, final boolean withHash,
									  final Order order) {
		this(withCoord, withDist, withHash);
		this.order = order;
	}

	/**
	 * 构造函数
	 *
	 * @param order
	 * 		排序方式
	 */
	protected BaseGeoDistanceArgument(final Order order) {
		this.order = order;
	}

	/**
	 * 获取是否将位置元素的经度和维度也一并返回
	 *
	 * @return 是否将位置元素的经度和维度也一并返回
	 */
	public Boolean isWithCoord() {
		return getWithCoord();
	}

	/**
	 * 获取是否将位置元素的经度和维度也一并返回
	 *
	 * @return 是否将位置元素的经度和维度也一并返回
	 */
	public Boolean getWithCoord() {
		return withCoord;
	}

	/**
	 * 设置将位置元素的经度和维度也一并返回
	 *
	 * @return {@link BaseGeoDistanceArgument}
	 */
	public BaseGeoDistanceArgument withCoord() {
		return setWithCoord(true);
	}

	/**
	 * 设置将位置元素的经度和维度也一并返回
	 *
	 * @param withCoord
	 * 		是否将位置元素的经度和维度也一并返回
	 *
	 * @return {@link BaseGeoDistanceArgument}
	 */
	public BaseGeoDistanceArgument setWithCoord(boolean withCoord) {
		this.withCoord = withCoord;
		return this;
	}

	/**
	 * 获取是否在返回位置元素的同时，将位置元素与中心之间的距离也一并返回
	 *
	 * @return 是否在返回位置元素的同时，将位置元素与中心之间的距离也一并返回
	 */
	public Boolean isWithDist() {
		return getWithDist();
	}

	/**
	 * 获取是否在返回位置元素的同时，将位置元素与中心之间的距离也一并返回
	 *
	 * @return 是否在返回位置元素的同时，将位置元素与中心之间的距离也一并返回
	 */
	public Boolean getWithDist() {
		return withDist;
	}

	/**
	 * 设置在返回位置元素的同时，将位置元素与中心之间的距离也一并返回
	 *
	 * @return {@link BaseGeoDistanceArgument}
	 */
	public BaseGeoDistanceArgument withDist() {
		return setWithDist(true);
	}

	/**
	 * 设置在返回位置元素的同时，将位置元素与中心之间的距离也一并返回
	 *
	 * @param withDist
	 * 		是否在返回位置元素的同时，将位置元素与中心之间的距离也一并返回
	 *
	 * @return {@link BaseGeoDistanceArgument}
	 */
	public BaseGeoDistanceArgument setWithDist(boolean withDist) {
		this.withDist = withDist;
		return this;
	}

	/**
	 * 获取是否返回位置元素经过原始 geohash 编码的有序集合分值
	 *
	 * @return 是否返回位置元素经过原始 geohash 编码的有序集合分值
	 */
	public Boolean isWithHash() {
		return getWithHash();
	}

	/**
	 * 获取是否返回位置元素经过原始 geohash 编码的有序集合分值
	 *
	 * @return 是否返回位置元素经过原始 geohash 编码的有序集合分值
	 */
	public Boolean getWithHash() {
		return withHash;
	}

	/**
	 * 设置返回位置元素经过原始 geohash 编码的有序集合分值
	 *
	 * @return {@link BaseGeoDistanceArgument}
	 */
	public BaseGeoDistanceArgument withHash() {
		return setWithHash(true);
	}

	/**
	 * 设置返回位置元素经过原始 geohash 编码的有序集合分值
	 *
	 * @param withHash
	 * 		是否返回位置元素经过原始 geohash 编码的有序集合分值
	 *
	 * @return {@link BaseGeoDistanceArgument}
	 */
	public BaseGeoDistanceArgument setWithHash(boolean withHash) {
		this.withHash = withHash;
		return this;
	}

	/**
	 * 排序方式
	 *
	 * @return 排序方式
	 */
	public Order getOrder() {
		return order;
	}

	/**
	 * 设置排序方式
	 *
	 * @param order
	 * 		排序方式
	 *
	 * @return {@link BaseGeoDistanceArgument}
	 */
	public BaseGeoDistanceArgument setOrder(Order order) {
		this.order = order;
		return this;
	}

	@Override
	public String toString() {
		return ArgStringBuilder.create()
				.append(Boolean.TRUE.equals(getWithCoord()) ? "WITHCOORD" : null)
				.append(Boolean.TRUE.equals(getWithDist()) ? "WITHDIST" : null)
				.append(Boolean.TRUE.equals(getWithHash()) ? "WITHHASH" : null)
				.append(getOrder())
				.build();
	}

}
