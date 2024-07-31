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
package com.buession.redis.core.internal.lettuce;

import com.buession.lang.Order;
import com.buession.redis.core.command.args.GeoRadiusArgument;
import com.buession.redis.core.command.args.GeoSearchArgument;
import com.buession.redis.core.command.args.GeoSearchStoreArgument;
import io.lettuce.core.GeoArgs;

/**
 * Lettuce {@link GeoArgs} 扩展
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public final class LettuceGeoArgs extends GeoArgs {

	/**
	 * 构造函数
	 */
	public LettuceGeoArgs() {
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
	public LettuceGeoArgs(final boolean withCoord, final boolean withDist, final boolean withHash) {
		super();
		withCoordinates(this, withCoord);
		withDistance(this, withDist);
		withHash(this, withHash);
	}

	/**
	 * 构造函数
	 *
	 * @param order
	 * 		排序
	 */
	public LettuceGeoArgs(final Order order) {
		super();
		sort(this, order);
	}

	/**
	 * 构造函数
	 *
	 * @param count
	 * 		返回数量
	 */
	public LettuceGeoArgs(final int count) {
		super();
		withCount(count);
	}

	/**
	 * 构造函数
	 *
	 * @param count
	 * 		返回数量
	 * @param any
	 * 		-
	 */
	public LettuceGeoArgs(final int count, final boolean any) {
		super();
		withCount(count, any);
	}

	/**
	 * 构造函数
	 *
	 * @param order
	 * 		排序
	 * @param count
	 * 		返回数量
	 */
	public LettuceGeoArgs(final Order order, final int count) {
		this(order);
		withCount(count);
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
	public LettuceGeoArgs(final Order order, final int count, final boolean any) {
		this(order);
		withCount(count, any);
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
	public LettuceGeoArgs(final boolean withCoord, final boolean withDist, final boolean withHash,
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
	public LettuceGeoArgs(final boolean withCoord, final boolean withDist, final boolean withHash,
						  final int count) {
		this(withCoord, withDist, withHash);
		withCount(count);
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
	public LettuceGeoArgs(final boolean withCoord, final boolean withDist, final boolean withHash,
						  final int count, final boolean any) {
		this(withCoord, withDist, withHash);
		withCount(count, any);
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
	public LettuceGeoArgs(final boolean withCoord, final boolean withDist, final boolean withHash,
						  final Order order, final int count) {
		this(withCoord, withDist, withHash, order);
		withCount(count);
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
	 * @param any
	 * 		-
	 */
	public LettuceGeoArgs(final boolean withCoord, final boolean withDist, final boolean withHash,
						  final Order order, final int count, final boolean any) {
		this(withCoord, withDist, withHash, order);
		withCount(count, any);
	}

	/**
	 * 从 {@link GeoRadiusArgument} 创建 {@link GeoArgs} 实例
	 *
	 * @param geoRadiusArgument
	 *        {@link GeoRadiusArgument}
	 *
	 * @return {@link LettuceGeoArgs} 实例
	 */
	public static LettuceGeoArgs from(final GeoRadiusArgument geoRadiusArgument) {
		final LettuceGeoArgs geoArgs = new LettuceGeoArgs();

		if(geoRadiusArgument != null){
			withCoordinates(geoArgs, geoRadiusArgument.isWithCoord());
			withDistance(geoArgs, geoRadiusArgument.isWithDist());
			withHash(geoArgs, geoRadiusArgument.isWithHash());
			sort(geoArgs, geoRadiusArgument.getOrder());

			if(geoRadiusArgument.getCount() != null){
				if(geoRadiusArgument.isAny() != null){
					geoArgs.withCount(geoRadiusArgument.getCount(), geoRadiusArgument.isAny());
				}else{
					geoArgs.withCount(geoRadiusArgument.getCount());
				}
			}
		}

		return geoArgs;
	}

	/**
	 * 从 {@link GeoSearchArgument} 创建 {@link GeoArgs} 实例
	 *
	 * @param geoSearchArgument
	 *        {@link GeoRadiusArgument}
	 *
	 * @return {@link LettuceGeoArgs} 实例
	 */
	public static LettuceGeoArgs from(final GeoSearchArgument geoSearchArgument) {
		final LettuceGeoArgs geoArgs = new LettuceGeoArgs();

		withCoordinates(geoArgs, geoSearchArgument.isWithCoord());
		withDistance(geoArgs, geoSearchArgument.isWithDist());
		withHash(geoArgs, geoSearchArgument.isWithHash());
		sort(geoArgs, geoSearchArgument.getOrder());

		if(geoSearchArgument.getCount() != null){
			if(geoSearchArgument.isAny() != null){
				geoArgs.withCount(geoSearchArgument.getCount(), geoSearchArgument.isAny());
			}else{
				geoArgs.withCount(geoSearchArgument.getCount());
			}
		}

		return geoArgs;
	}

	/**
	 * 从 {@link GeoSearchStoreArgument} 创建 {@link GeoArgs} 实例
	 *
	 * @param geoSearchStoreArgument
	 *        {@link GeoSearchStoreArgument}
	 *
	 * @return {@link LettuceGeoArgs} 实例
	 */
	public static LettuceGeoArgs from(final GeoSearchStoreArgument geoSearchStoreArgument) {
		final LettuceGeoArgs geoArgs = new LettuceGeoArgs();
		
		sort(geoArgs, geoSearchStoreArgument.getOrder());

		if(geoSearchStoreArgument.getCount() != null){
			if(geoSearchStoreArgument.isAny() != null){
				geoArgs.withCount(geoSearchStoreArgument.getCount(), geoSearchStoreArgument.isAny());
			}else{
				geoArgs.withCount(geoSearchStoreArgument.getCount());
			}
		}

		return geoArgs;
	}

	private static void withCoordinates(final LettuceGeoArgs geoArgs, final Boolean withCoord) {
		if(Boolean.TRUE.equals(withCoord)){
			geoArgs.withCoordinates();
		}
	}

	private static void withDistance(final LettuceGeoArgs geoArgs, final Boolean withDist) {
		if(Boolean.TRUE.equals(withDist)){
			geoArgs.withDistance();
		}
	}

	private static void withHash(final LettuceGeoArgs geoArgs, final Boolean withHash) {
		if(Boolean.TRUE.equals(withHash)){
			geoArgs.withHash();
		}
	}

	private static void sort(final LettuceGeoArgs geoArgs, final Order order) {
		if(order == Order.ASC){
			geoArgs.sort(Sort.asc);
		}else if(order == Order.DESC){
			geoArgs.sort(Sort.desc);
		}
	}

}
