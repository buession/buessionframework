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
package com.buession.redis.core.internal.jedis;

import com.buession.lang.Order;
import com.buession.redis.core.command.args.GeoRadiusArgument;
import redis.clients.jedis.params.GeoRadiusParam;

/**
 * Jedis {@link GeoRadiusParam} 扩展
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public final class JedisGeoRadiusParam extends GeoRadiusParam {

	/**
	 * 构造函数
	 */
	public JedisGeoRadiusParam() {
		super();
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
	public JedisGeoRadiusParam(final boolean withCoord, final boolean withDist, final boolean withHash) {
		super();
		withCoord(this, withCoord);
		withDist(this, withDist);
		withHash(this, withHash);
	}

	/**
	 * 构造函数
	 *
	 * @param order
	 * 		排序
	 */
	public JedisGeoRadiusParam(final Order order) {
		super();
		sort(this, order);
	}

	/**
	 * 构造函数
	 *
	 * @param count
	 * 		返回数量
	 */
	public JedisGeoRadiusParam(final int count) {
		super();
		count(count);
	}

	/**
	 * 构造函数
	 *
	 * @param count
	 * 		返回数量
	 * @param any
	 * 		-
	 */
	public JedisGeoRadiusParam(final int count, final boolean any) {
		super();
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
	public JedisGeoRadiusParam(final Order order, final int count) {
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
	public JedisGeoRadiusParam(final Order order, final int count, final boolean any) {
		this(order);
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
	 */
	public JedisGeoRadiusParam(final boolean withCoord, final boolean withDist, final boolean withHash,
							   final Order order) {
		this(withCoord, withDist, withHash);
		sort(this, order);
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
	public JedisGeoRadiusParam(final boolean withCoord, final boolean withDist, final boolean withHash,
							   final int count) {
		this(withCoord, withDist, withHash);
		count(count);
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
	public JedisGeoRadiusParam(final boolean withCoord, final boolean withDist, final boolean withHash,
							   final int count, final boolean any) {
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
	public JedisGeoRadiusParam(final boolean withCoord, final boolean withDist, final boolean withHash,
							   final Order order, final int count) {
		this(withCoord, withDist, withHash, order);
		count(count);
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
	public JedisGeoRadiusParam(final boolean withCoord, final boolean withDist, final boolean withHash,
							   final Order order, final int count, final boolean any) {
		this(withCoord, withDist, withHash, order);
		count(count, any);
	}

	/**
	 * 从 {@link GeoRadiusArgument} 创建 {@link JedisGeoRadiusParam} 实例
	 *
	 * @param geoRadiusArgument
	 *        {@link GeoRadiusArgument}
	 *
	 * @return {@link JedisGeoRadiusParam} 实例
	 *
	 * @since 3.0.0
	 */
	public static JedisGeoRadiusParam from(final GeoRadiusArgument geoRadiusArgument) {
		final JedisGeoRadiusParam geoRadiusParam = new JedisGeoRadiusParam();

		if(geoRadiusArgument != null){
			withCoord(geoRadiusParam, geoRadiusArgument.isWithCoord());
			withDist(geoRadiusParam, geoRadiusArgument.isWithDist());
			withHash(geoRadiusParam, geoRadiusArgument.isWithHash());
			sort(geoRadiusParam, geoRadiusArgument.getOrder());

			if(geoRadiusArgument.getCount() != null){
				if(geoRadiusArgument.isAny() != null){
					geoRadiusParam.count(geoRadiusArgument.getCount(), geoRadiusArgument.isAny());
				}else{
					geoRadiusParam.count(geoRadiusArgument.getCount());
				}
			}
		}

		return geoRadiusParam;
	}

	private static void withCoord(final JedisGeoRadiusParam geoRadiusParam, final Boolean withCoord) {
		if(Boolean.TRUE.equals(withCoord)){
			geoRadiusParam.withCoord();
		}
	}

	private static void withDist(final JedisGeoRadiusParam geoRadiusParam, final Boolean withDist) {
		if(Boolean.TRUE.equals(withDist)){
			geoRadiusParam.withDist();
		}
	}

	private static void withHash(final JedisGeoRadiusParam geoRadiusParam, final Boolean withHash) {
		if(Boolean.TRUE.equals(withHash)){
			geoRadiusParam.withHash();
		}
	}

	private static void sort(final JedisGeoRadiusParam geoRadiusParam, final Order order) {
		if(order == Order.ASC){
			geoRadiusParam.sortAscending();
		}else if(order == Order.DESC){
			geoRadiusParam.sortDescending();
		}
	}

}
