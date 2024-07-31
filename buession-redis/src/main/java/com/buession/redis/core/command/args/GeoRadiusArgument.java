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
 * | Copyright @ 2013-2024 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.core.command.args;

import com.buession.lang.Order;
import com.buession.redis.core.Keyword;

/**
 * {@code GEORADIUS} 命令参数
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public class GeoRadiusArgument {

	/**
	 * 将位置元素的经度和维度也一并返回
	 */
	private Boolean withCoord;

	/**
	 * 在返回位置元素的同时， 将位置元素与中心之间的距离也一并返回
	 */
	private Boolean withDist;

	/**
	 * 以 52 位有符号整数的形式， 返回位置元素经过原始 geohash 编码的有序集合分值
	 */
	private Boolean withHash;

	/**
	 * 排序
	 */
	private Order order;

	/**
	 * 返回个数
	 */
	private Integer count;

	/**
	 * -
	 */
	private Boolean any;

	/**
	 * 构造函数
	 */
	public GeoRadiusArgument() {
	}

	/**
	 * 构造函数
	 *
	 * @param withCoord
	 * 		是否将位置元素的经度和纬度也一并返回
	 * @param withDist
	 * 		在返回位置元素的同时， 将位置元素与中心之间的距离也一并返回
	 * @param withHash
	 * 		是否以 52 位有符号整数的形式， 返回位置元素经过原始 geohash 编码的有序集合分值
	 */
	public GeoRadiusArgument(final Boolean withCoord, final Boolean withDist, final Boolean withHash) {
		this.withCoord = withCoord;
		this.withDist = withDist;
		this.withHash = withHash;
	}

	/**
	 * 构造函数
	 *
	 * @param order
	 * 		排序
	 */
	public GeoRadiusArgument(final Order order) {
		this.order = order;
	}

	/**
	 * 构造函数
	 *
	 * @param count
	 * 		返回数量
	 */
	public GeoRadiusArgument(final Integer count) {
		this.count = count;
	}

	/**
	 * 构造函数
	 *
	 * @param count
	 * 		返回数量
	 * @param any
	 * 		-
	 */
	public GeoRadiusArgument(final Integer count, final Boolean any) {
		count(count, any);
	}

	/**
	 * 构造函数
	 *
	 * @param order
	 * 		排序
	 * @param count
	 * 		返回数量
	 */
	public GeoRadiusArgument(final Order order, final Integer count) {
		this(order);
		count(count);
	}

	/**
	 * 构造函数
	 *
	 * @param order
	 * 		排序
	 * @param count
	 * 		返回数量
	 * @param any
	 * 		-
	 */
	public GeoRadiusArgument(final Order order, final Integer count, final Boolean any) {
		this(order, count);
		this.any = any;
	}

	/**
	 * 构造函数
	 *
	 * @param withCoord
	 * 		是否将位置元素的经度和纬度也一并返回
	 * @param withDist
	 * 		在返回位置元素的同时， 将位置元素与中心之间的距离也一并返回
	 * @param withHash
	 * 		是否以 52 位有符号整数的形式， 返回位置元素经过原始 geohash 编码的有序集合分值
	 * @param order
	 * 		排序
	 */
	public GeoRadiusArgument(final Boolean withCoord, final Boolean withDist, final Boolean withHash,
							 final Order order) {
		this(withCoord, withDist, withHash);
		this.order = order;
	}

	/**
	 * 构造函数
	 *
	 * @param withCoord
	 * 		是否将位置元素的经度和纬度也一并返回
	 * @param withDist
	 * 		在返回位置元素的同时， 将位置元素与中心之间的距离也一并返回
	 * @param withHash
	 * 		是否以 52 位有符号整数的形式， 返回位置元素经过原始 geohash 编码的有序集合分值
	 * @param count
	 * 		返回数量
	 */
	public GeoRadiusArgument(final Boolean withCoord, final Boolean withDist, final Boolean withHash,
							 final Integer count) {
		this(withCoord, withDist, withHash);
		this.count = count;
	}

	/**
	 * 构造函数
	 *
	 * @param withCoord
	 * 		是否将位置元素的经度和纬度也一并返回
	 * @param withDist
	 * 		在返回位置元素的同时， 将位置元素与中心之间的距离也一并返回
	 * @param withHash
	 * 		是否以 52 位有符号整数的形式， 返回位置元素经过原始 geohash 编码的有序集合分值
	 * @param count
	 * 		返回数量
	 * @param any
	 * 		-
	 */
	public GeoRadiusArgument(final Boolean withCoord, final Boolean withDist, final Boolean withHash,
							 final Integer count, final Boolean any) {
		this(withCoord, withDist, withHash);
		count(count, any);
	}

	/**
	 * 构造函数
	 *
	 * @param withCoord
	 * 		是否将位置元素的经度和纬度也一并返回
	 * @param withDist
	 * 		在返回位置元素的同时， 将位置元素与中心之间的距离也一并返回
	 * @param withHash
	 * 		是否以 52 位有符号整数的形式， 返回位置元素经过原始 geohash 编码的有序集合分值
	 * @param order
	 * 		排序
	 * @param count
	 * 		返回数量
	 */
	public GeoRadiusArgument(final Boolean withCoord, final Boolean withDist, final Boolean withHash,
							 final Order order, final Integer count) {
		this(withCoord, withDist, withHash, order);
		this.count = count;
	}

	/**
	 * 构造函数
	 *
	 * @param withCoord
	 * 		是否将位置元素的经度和纬度也一并返回
	 * @param withDist
	 * 		在返回位置元素的同时， 将位置元素与中心之间的距离也一并返回
	 * @param withHash
	 * 		是否以 52 位有符号整数的形式， 返回位置元素经过原始 geohash 编码的有序集合分值
	 * @param order
	 * 		排序
	 * @param count
	 * 		返回数量
	 * 		返回数量
	 * @param any
	 * 		-
	 */
	public GeoRadiusArgument(final Boolean withCoord, final Boolean withDist, final Boolean withHash,
							 final Order order, final Integer count, final Boolean any) {
		this(withCoord, withDist, withHash, order);
		count(count, any);
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
	 */
	public void withCoord() {
		this.withCoord = true;
	}

	/**
	 * 设置是否将位置元素的经度和维度也一并返回
	 *
	 * @param withCoord
	 * 		true / false
	 */
	public void setWithCoord(final Boolean withCoord) {
		this.withCoord = withCoord;
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
	 */
	public void withDist() {
		this.withDist = true;
	}

	/**
	 * 设置是否在返回位置元素的同时，将位置元素与中心之间的距离也一并返回
	 *
	 * @param withDist
	 * 		true / false
	 */
	public void setWithDist(final Boolean withDist) {
		this.withDist = withDist;
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
	 */
	public void withHash() {
		this.withHash = true;
	}

	/**
	 * 设置返回位置元素经过原始 geohash 编码的有序集合分值
	 *
	 * @param withHash
	 * 		true / false
	 */
	public void setWithHash(final Boolean withHash) {
		this.withHash = withHash;
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
	 */
	public void order(final Order order) {
		this.order = order;
	}

	/**
	 * 获取返回数量
	 *
	 * @return 返回数量
	 */
	public Integer getCount() {
		return count;
	}

	/**
	 * 设置返回数量
	 *
	 * @param count
	 * 		返回数量
	 */
	public void count(final Integer count) {
		this.count = count;
	}

	/**
	 * 设置返回数量
	 *
	 * @param count
	 * 		返回数量
	 * @param any
	 * 		-
	 */
	public void count(final Integer count, final Boolean any) {
		this.count = count;
		this.any = any;
	}

	/**
	 * -
	 *
	 * @return true / false
	 */
	public Boolean isAny() {
		return getAny();
	}

	/**
	 * -
	 *
	 * @return true / false
	 */
	public Boolean getAny() {
		return any;
	}

	@Override
	public String toString() {
		final ArgumentStringBuilder builder = ArgumentStringBuilder.create();

		if(Boolean.TRUE.equals(withCoord)){
			builder.append(Keyword.Geo.WITHCOORD);
		}

		if(Boolean.TRUE.equals(withDist)){
			builder.append(Keyword.Geo.WITHDIST);
		}

		if(Boolean.TRUE.equals(withHash)){
			builder.append(Keyword.Geo.WITHHASH);
		}

		if(count != null){
			builder.add(Keyword.Common.COUNT, count);
			if(any != null){
				builder.append(Keyword.Common.ANY);
			}
		}

		builder.append(order);

		return builder.toString();
	}

}
