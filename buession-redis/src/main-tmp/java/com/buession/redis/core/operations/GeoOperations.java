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
package com.buession.redis.core.operations;

import com.buession.lang.Geo;
import com.buession.redis.core.GeoRadius;
import com.buession.redis.core.GeoUnit;
import com.buession.redis.core.command.GeoCommands;
import com.buession.redis.core.command.args.GeoRadiusArgument;

import java.util.List;

/**
 * 地理位置运算
 *
 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/?group=geo" target="_blank">https://redis.io/docs/latest/commands/?group=geo</a></p>
 *
 * @author Yong.Teng
 */
public interface GeoOperations extends GeoCommands, RedisOperations {

	/**
	 * 将给定的空间元素（经度、纬度、名字）添加到指定的键里面
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/geo/geoadd.html" target="_blank">http://redisdoc.com/geo/geoadd.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param member
	 * 		名字
	 * @param geo
	 * 		经纬度对象
	 *
	 * @return 新添加到键里面的空间元素数量，不包括那些已经存在但是被更新的元素
	 */
	default Long geoAdd(final String key, final String member, final Geo geo) {
		return geoAdd(key, member, geo.getLongitude(), geo.getLatitude());
	}

	/**
	 * 将给定的空间元素（经度、纬度、名字）添加到指定的键里面
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/geo/geoadd.html" target="_blank">http://redisdoc.com/geo/geoadd.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param member
	 * 		名字
	 * @param geo
	 * 		经纬度对象
	 *
	 * @return 新添加到键里面的空间元素数量，不包括那些已经存在但是被更新的元素
	 */
	default Long geoAdd(final byte[] key, final byte[] member, final Geo geo) {
		return geoAdd(key, member, geo.getLongitude(), geo.getLatitude());
	}

	/**
	 * 以给定的经纬度为中心，返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/geo/georadius.html" target="_blank">http://redisdoc.com/geo/georadius.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param geo
	 * 		经纬度对象
	 * 		纬度
	 * @param radius
	 * 		范围
	 * @param unit
	 * 		距离单位
	 *
	 * @return 位置元素
	 */
	default List<GeoRadius> geoRadius(final String key, final Geo geo, final double radius, final GeoUnit unit) {
		return geoRadius(key, geo.getLongitude(), geo.getLatitude(), radius, unit);
	}

	/**
	 * 以给定的经纬度为中心，返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/geo/georadius.html" target="_blank">http://redisdoc.com/geo/georadius.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param geo
	 * 		经纬度对象
	 * @param radius
	 * 		范围
	 * @param unit
	 * 		距离单位
	 *
	 * @return 位置元素
	 */
	default List<GeoRadius> geoRadius(final byte[] key, final Geo geo, final double radius, final GeoUnit unit) {
		return geoRadius(key, geo.getLongitude(), geo.getLatitude(), radius, unit);
	}

	/**
	 * 以给定的经纬度为中心，返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/geo/georadius.html" target="_blank">http://redisdoc.com/geo/georadius.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param geo
	 * 		经纬度对象
	 * @param radius
	 * 		范围
	 * @param unit
	 * 		距离单位
	 * @param geoRadiusArgument
	 * 		GEO 参数
	 *
	 * @return 位置元素
	 */
	default List<GeoRadius> geoRadius(final String key, final Geo geo, final double radius, final GeoUnit unit,
									  final GeoRadiusArgument geoRadiusArgument) {
		return geoRadius(key, geo.getLongitude(), geo.getLatitude(), radius, unit, geoRadiusArgument);
	}

	/**
	 * 以给定的经纬度为中心，返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/geo/georadius.html" target="_blank">http://redisdoc.com/geo/georadius.html</a></p>
	 *
	 * @param key
	 * 		KeyG
	 * @param geo
	 * 		经纬度对象
	 * @param radius
	 * 		范围
	 * @param unit
	 * 		距离单位
	 * @param geoRadiusArgument
	 * 		GEO 参数
	 *
	 * @return 位置元素
	 */
	default List<GeoRadius> geoRadius(final byte[] key, final Geo geo, final double radius, final GeoUnit unit,
									  final GeoRadiusArgument geoRadiusArgument) {
		return geoRadius(key, geo.getLongitude(), geo.getLatitude(), radius, unit, geoRadiusArgument);
	}

	/**
	 * Read-only variant of the GEORADIUS command
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/georadius_ro/" target="_blank">https://redis.io/commands/georadius_ro/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param geo
	 * 		经纬度对象
	 * 		纬度
	 * @param radius
	 * 		范围
	 * @param unit
	 * 		距离单位
	 *
	 * @return 位置元素
	 */
	default List<GeoRadius> geoRadiusRo(final String key, final Geo geo, final double radius, final GeoUnit unit) {
		return geoRadiusRo(key, geo.getLongitude(), geo.getLatitude(), radius, unit);
	}

	/**
	 * Read-only variant of the GEORADIUS command
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/georadius_ro/" target="_blank">https://redis.io/commands/georadius_ro/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param geo
	 * 		经纬度对象
	 * @param radius
	 * 		范围
	 * @param unit
	 * 		距离单位
	 *
	 * @return 位置元素
	 */
	default List<GeoRadius> geoRadiusRo(final byte[] key, final Geo geo, final double radius, final GeoUnit unit) {
		return geoRadiusRo(key, geo.getLongitude(), geo.getLatitude(), radius, unit);
	}

	/**
	 * Read-only variant of the GEORADIUS command
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/georadius_ro/" target="_blank">https://redis.io/commands/georadius_ro/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param geo
	 * 		经纬度对象
	 * @param radius
	 * 		范围
	 * @param unit
	 * 		距离单位
	 * @param geoRadiusArgument
	 * 		GEO 参数
	 *
	 * @return 位置元素
	 */
	default List<GeoRadius> geoRadiusRo(final String key, final Geo geo, final double radius, final GeoUnit unit,
										final GeoRadiusArgument geoRadiusArgument) {
		return geoRadiusRo(key, geo.getLongitude(), geo.getLatitude(), radius, unit, geoRadiusArgument);
	}

	/**
	 * Read-only variant of the GEORADIUS command
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/georadius_ro/" target="_blank">https://redis.io/commands/georadius_ro/</a></p>
	 *
	 * @param key
	 * 		KeyG
	 * @param geo
	 * 		经纬度对象
	 * @param radius
	 * 		范围
	 * @param unit
	 * 		距离单位
	 * @param geoRadiusArgument
	 * 		GEO 参数
	 *
	 * @return 位置元素
	 */
	default List<GeoRadius> geoRadiusRo(final byte[] key, final Geo geo, final double radius, final GeoUnit unit,
										final GeoRadiusArgument geoRadiusArgument) {
		return geoRadiusRo(key, geo.getLongitude(), geo.getLatitude(), radius, unit, geoRadiusArgument);
	}

}
