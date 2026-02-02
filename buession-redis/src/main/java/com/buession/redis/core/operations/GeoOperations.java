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
package com.buession.redis.core.operations;

import com.buession.lang.Geo;
import com.buession.redis.core.GeoRadius;
import com.buession.redis.core.GeoUnit;
import com.buession.redis.core.command.GeoCommands;

import java.util.List;
import java.util.Map;

/**
 * 地理位置运算
 *
 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/?group=geo" target="_blank">https://redis.io/docs/latest/commands/?group=geo</a></p>
 *
 * @author Yong.Teng
 */
public interface GeoOperations extends GeoCommands, RedisOperations {

	@Override
	default Long geoAdd(final String key, final String member, final double longitude, final double latitude) {
		return execute((client)->client.geoOperations().geoAdd(key, member, longitude, latitude));
	}

	@Override
	default Long geoAdd(final byte[] key, final byte[] member, final double longitude, final double latitude) {
		return execute((client)->client.geoOperations().geoAdd(key, member, longitude, latitude));
	}

	@Override
	default Long geoAdd(final String key, final Map<String, Geo> memberCoordinates) {
		return execute((client)->client.geoOperations().geoAdd(key, memberCoordinates));
	}

	@Override
	default Long geoAdd(final byte[] key, final Map<byte[], Geo> memberCoordinates) {
		return execute((client)->client.geoOperations().geoAdd(key, memberCoordinates));
	}

	@Override
	default Long geoAdd(final String key, final GeoAddArgument argument, final String member, final double longitude,
						final double latitude) {
		return execute((client)->client.geoOperations().geoAdd(key, argument, member, longitude, latitude));
	}

	@Override
	default Long geoAdd(final byte[] key, final GeoAddArgument argument, final byte[] member, final double longitude,
						final double latitude) {
		return execute((client)->client.geoOperations().geoAdd(key, argument, member, longitude, latitude));
	}

	@Override
	default Long geoAdd(final String key, final GeoAddArgument argument, final Map<String, Geo> memberCoordinates) {
		return execute((client)->client.geoOperations().geoAdd(key, argument, memberCoordinates));
	}

	@Override
	default Long geoAdd(final byte[] key, final GeoAddArgument argument, final Map<byte[], Geo> memberCoordinates) {
		return execute((client)->client.geoOperations().geoAdd(key, argument, memberCoordinates));
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
	 * 将给定的空间元素（经度、纬度、名字）添加到指定的键里面
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/geo/geoadd.html" target="_blank">http://redisdoc.com/geo/geoadd.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param member
	 * 		名字
	 * @param argument
	 * 		参数
	 * @param geo
	 * 		经纬度对象
	 *
	 * @return 新添加到键里面的空间元素数量，不包括那些已经存在但是被更新的元素
	 */
	default Long geoAdd(final String key, final GeoAddArgument argument, final String member, final Geo geo) {
		return geoAdd(key, argument, member, geo.getLongitude(), geo.getLatitude());
	}

	/**
	 * 将给定的空间元素（经度、纬度、名字）添加到指定的键里面
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/geo/geoadd.html" target="_blank">http://redisdoc.com/geo/geoadd.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param argument
	 * 		参数
	 * @param member
	 * 		名字
	 * @param geo
	 * 		经纬度对象
	 *
	 * @return 新添加到键里面的空间元素数量，不包括那些已经存在但是被更新的元素
	 */
	default Long geoAdd(final byte[] key, final GeoAddArgument argument, final byte[] member, final Geo geo) {
		return geoAdd(key, argument, member, geo.getLongitude(), geo.getLatitude());
	}

	@Override
	default Double geoDist(final String key, final String member1, final String member2) {
		return execute((client)->client.geoOperations().geoDist(key, member1, member2));
	}

	@Override
	default Double geoDist(final byte[] key, final byte[] member1, final byte[] member2) {
		return execute((client)->client.geoOperations().geoDist(key, member1, member2));
	}

	@Override
	default Double geoDist(final String key, final String member1, final String member2, final GeoUnit unit) {
		return execute((client)->client.geoOperations().geoDist(key, member1, member2, unit));
	}

	@Override
	default Double geoDist(final byte[] key, final byte[] member1, final byte[] member2, final GeoUnit unit) {
		return execute((client)->client.geoOperations().geoDist(key, member1, member2, unit));
	}

	@Override
	default List<String> geoHash(final String key, final String... members) {
		return execute((client)->client.geoOperations().geoHash(key, members));
	}

	@Override
	default List<byte[]> geoHash(final byte[] key, final byte[]... members) {
		return execute((client)->client.geoOperations().geoHash(key, members));
	}

	@Override
	default List<Geo> geoPos(final String key, final String... members) {
		return execute((client)->client.geoOperations().geoPos(key, members));
	}

	@Override
	default List<Geo> geoPos(final byte[] key, final byte[]... members) {
		return execute((client)->client.geoOperations().geoPos(key, members));
	}

	@Override
	default List<GeoRadius> geoRadius(final String key, final double longitude, final double latitude,
									  final double radius, final GeoUnit unit) {
		return execute((client)->client.geoOperations().geoRadius(key, longitude, latitude, radius, unit));
	}

	@Override
	default List<GeoRadius> geoRadius(final byte[] key, final double longitude, final double latitude,
									  final double radius, final GeoUnit unit) {
		return execute((client)->client.geoOperations().geoRadius(key, longitude, latitude, radius, unit));
	}

	@Override
	default List<GeoRadius> geoRadius(final String key, final double longitude, final double latitude,
									  final double radius, final GeoUnit unit, final GeoRadiusArgument argument) {
		return execute((client)->client.geoOperations().geoRadius(key, longitude, latitude, radius, unit, argument));
	}

	@Override
	default List<GeoRadius> geoRadius(final byte[] key, final double longitude, final double latitude,
									  final double radius, final GeoUnit unit, final GeoRadiusArgument argument) {
		return execute((client)->client.geoOperations().geoRadius(key, longitude, latitude, radius, unit, argument));
	}

	/**
	 * 以给定的经纬度为中心，返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/geo/georadius.html" target="_blank">http://redisdoc.com/geo/georadius.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param longitude
	 * 		经度
	 * @param latitude
	 * 		纬度
	 * @param radius
	 * 		范围（单位：米）
	 *
	 * @return 位置元素
	 */
	default List<GeoRadius> geoRadius(final String key, final double longitude, final double latitude,
									  final double radius) {
		return geoRadius(key, longitude, latitude, radius, GeoUnit.M);
	}

	/**
	 * 以给定的经纬度为中心，返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/geo/georadius.html" target="_blank">http://redisdoc.com/geo/georadius.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param longitude
	 * 		经度
	 * @param latitude
	 * 		纬度
	 * @param radius
	 * 		范围（单位：米）
	 *
	 * @return 位置元素
	 */
	default List<GeoRadius> geoRadius(final byte[] key, final double longitude, final double latitude,
									  final double radius) {
		return geoRadius(key, longitude, latitude, radius, GeoUnit.M);
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
	 * 		范围（单位：米）
	 *
	 * @return 位置元素
	 */
	default List<GeoRadius> geoRadius(final String key, final Geo geo, final double radius) {
		return geoRadius(key, geo.getLongitude(), geo.getLatitude(), radius);
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
	 * 		范围（单位：米）
	 *
	 * @return 位置元素
	 */
	default List<GeoRadius> geoRadius(final byte[] key, final Geo geo, final double radius) {
		return geoRadius(key, geo.getLongitude(), geo.getLatitude(), radius);
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
	 * @param longitude
	 * 		经度
	 * @param latitude
	 * 		纬度
	 * @param radius
	 * 		范围（单位：米）
	 * @param argument
	 * 		GEO Radius 参数
	 *
	 * @return 位置元素
	 */
	default List<GeoRadius> geoRadius(final String key, final double longitude, final double latitude,
									  final double radius, final GeoRadiusArgument argument) {
		return geoRadius(key, longitude, latitude, radius, GeoUnit.M, argument);
	}

	/**
	 * 以给定的经纬度为中心，返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/geo/georadius.html" target="_blank">http://redisdoc.com/geo/georadius.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param longitude
	 * 		经度
	 * @param latitude
	 * 		纬度
	 * @param radius
	 * 		范围（单位：米）
	 * @param argument
	 * 		GEO Radius 参数
	 *
	 * @return 位置元素
	 */
	default List<GeoRadius> geoRadius(final byte[] key, final double longitude, final double latitude,
									  final double radius, final GeoRadiusArgument argument) {
		return geoRadius(key, longitude, latitude, radius, GeoUnit.M, argument);
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
	 * 		范围（单位：米）
	 * @param argument
	 * 		GEO Radius 参数
	 *
	 * @return 位置元素
	 */
	default List<GeoRadius> geoRadius(final String key, final Geo geo, final double radius,
									  final GeoRadiusArgument argument) {
		return geoRadius(key, geo.getLongitude(), geo.getLatitude(), radius, argument);
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
	 * 		范围（单位：米）
	 * @param argument
	 * 		GEO Radius 参数
	 *
	 * @return 位置元素
	 */
	default List<GeoRadius> geoRadius(final byte[] key, final Geo geo, final double radius,
									  final GeoRadiusArgument argument) {
		return geoRadius(key, geo.getLongitude(), geo.getLatitude(), radius, argument);
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
	 * @param argument
	 * 		GEO Radius 参数
	 *
	 * @return 位置元素
	 */
	default List<GeoRadius> geoRadius(final String key, final Geo geo, final double radius, final GeoUnit unit,
									  final GeoRadiusArgument argument) {
		return geoRadius(key, geo.getLongitude(), geo.getLatitude(), radius, unit, argument);
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
	 * @param argument
	 * 		GEO Radius 参数
	 *
	 * @return 位置元素
	 */
	default List<GeoRadius> geoRadius(final byte[] key, final Geo geo, final double radius, final GeoUnit unit,
									  final GeoRadiusArgument argument) {
		return geoRadius(key, geo.getLongitude(), geo.getLatitude(), radius, unit, argument);
	}

	@Override
	default List<GeoRadius> geoRadiusRo(final String key, final double longitude, final double latitude,
										final double radius, final GeoUnit unit) {
		return execute((client)->client.geoOperations().geoRadiusRo(key, longitude, latitude, radius, unit));
	}

	@Override
	default List<GeoRadius> geoRadiusRo(final byte[] key, final double longitude, final double latitude,
										final double radius, final GeoUnit unit) {
		return execute((client)->client.geoOperations().geoRadiusRo(key, longitude, latitude, radius, unit));
	}

	@Override
	default List<GeoRadius> geoRadiusRo(final String key, final double longitude, final double latitude,
										final double radius, final GeoUnit unit, final GeoRadiusArgument argument) {
		return execute((client)->client.geoOperations().geoRadiusRo(key, longitude, latitude, radius, unit, argument));
	}

	@Override
	default List<GeoRadius> geoRadiusRo(final byte[] key, final double longitude, final double latitude,
										final double radius, final GeoUnit unit, final GeoRadiusArgument argument) {
		return execute((client)->client.geoOperations().geoRadiusRo(key, longitude, latitude, radius, unit, argument));
	}

	/**
	 * Read-only variant of the GEORADIUS command
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/georadius_ro/" target="_blank">https://redis.io/commands/georadius_ro/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param longitude
	 * 		经度
	 * @param latitude
	 * 		纬度
	 * @param radius
	 * 		范围（单位：米）
	 *
	 * @return 位置元素
	 */
	default List<GeoRadius> geoRadiusRo(final String key, final double longitude, final double latitude,
										final double radius) {
		return geoRadiusRo(key, longitude, latitude, radius, GeoUnit.M);
	}

	/**
	 * Read-only variant of the GEORADIUS command
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/georadius_ro/" target="_blank">https://redis.io/commands/georadius_ro/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param longitude
	 * 		经度
	 * @param latitude
	 * 		纬度
	 * @param radius
	 * 		范围（单位：米）
	 *
	 * @return 位置元素
	 */
	default List<GeoRadius> geoRadiusRo(final byte[] key, final double longitude, final double latitude,
										final double radius) {
		return geoRadiusRo(key, longitude, latitude, radius, GeoUnit.M);
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
	 * 		范围（单位：米）
	 *
	 * @return 位置元素
	 */
	default List<GeoRadius> geoRadiusRo(final String key, final Geo geo, final double radius) {
		return geoRadiusRo(key, geo.getLongitude(), geo.getLatitude(), radius);
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
	 * 		范围（单位：米）
	 *
	 * @return 位置元素
	 */
	default List<GeoRadius> geoRadiusRo(final byte[] key, final Geo geo, final double radius) {
		return geoRadiusRo(key, geo.getLongitude(), geo.getLatitude(), radius);
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
	 * @param longitude
	 * 		经度
	 * @param latitude
	 * 		纬度
	 * @param radius
	 * 		范围（单位：米）
	 * @param argument
	 * 		GEO Radius 参数
	 *
	 * @return 位置元素
	 */
	default List<GeoRadius> geoRadiusRo(final String key, final double longitude, final double latitude,
										final double radius, final GeoRadiusArgument argument) {
		return geoRadiusRo(key, longitude, latitude, radius, GeoUnit.M, argument);
	}

	/**
	 * Read-only variant of the GEORADIUS command
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/georadius_ro/" target="_blank">https://redis.io/commands/georadius_ro/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param longitude
	 * 		经度
	 * @param latitude
	 * 		纬度
	 * @param radius
	 * 		范围（单位：米）
	 * @param argument
	 * 		GEO Radius 参数
	 *
	 * @return 位置元素
	 */
	default List<GeoRadius> geoRadiusRo(final byte[] key, final double longitude, final double latitude,
										final double radius, final GeoRadiusArgument argument) {
		return geoRadiusRo(key, longitude, latitude, radius, GeoUnit.M, argument);
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
	 * 		范围（单位：米）
	 * @param argument
	 * 		GEO Radius 参数
	 *
	 * @return 位置元素
	 */
	default List<GeoRadius> geoRadiusRo(final String key, final Geo geo, final double radius,
										final GeoRadiusArgument argument) {
		return geoRadiusRo(key, geo.getLongitude(), geo.getLatitude(), radius, argument);
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
	 * 		范围（单位：米）
	 * @param argument
	 * 		GEO Radius 参数
	 *
	 * @return 位置元素
	 */
	default List<GeoRadius> geoRadiusRo(final byte[] key, final Geo geo, final double radius,
										final GeoRadiusArgument argument) {
		return geoRadiusRo(key, geo.getLongitude(), geo.getLatitude(), radius, argument);
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
	 * @param argument
	 * 		GEO Radius 参数
	 *
	 * @return 位置元素
	 */
	default List<GeoRadius> geoRadiusRo(final String key, final Geo geo, final double radius, final GeoUnit unit,
										final GeoRadiusArgument argument) {
		return geoRadiusRo(key, geo.getLongitude(), geo.getLatitude(), radius, unit, argument);
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
	 * @param argument
	 * 		GEO Radius 参数
	 *
	 * @return 位置元素
	 */
	default List<GeoRadius> geoRadiusRo(final byte[] key, final Geo geo, final double radius, final GeoUnit unit,
										final GeoRadiusArgument argument) {
		return geoRadiusRo(key, geo.getLongitude(), geo.getLatitude(), radius, unit, argument);
	}

	@Override
	default List<GeoRadius> geoRadiusByMember(final String key, final String member, final double radius,
											  final GeoUnit unit) {
		return execute((client)->client.geoOperations().geoRadiusByMember(key, member, radius, unit));
	}

	@Override
	default List<GeoRadius> geoRadiusByMember(final byte[] key, final byte[] member, final double radius,
											  final GeoUnit unit) {
		return execute((client)->client.geoOperations().geoRadiusByMember(key, member, radius, unit));
	}

	@Override
	default List<GeoRadius> geoRadiusByMember(final String key, final String member, final double radius,
											  final GeoUnit unit, final GeoRadiusArgument argument) {
		return execute((client)->client.geoOperations().geoRadiusByMember(key, member, radius, unit, argument));
	}

	@Override
	default List<GeoRadius> geoRadiusByMember(final byte[] key, final byte[] member, final double radius,
											  final GeoUnit unit, final GeoRadiusArgument argument) {
		return execute((client)->client.geoOperations().geoRadiusByMember(key, member, radius, unit, argument));
	}

	/**
	 * 找出位于指定范围内的元素，
	 * 但是 GEORADIUSBYMEMBER 的中心点是由给定的位置元素决定的，而不是像 GEORADIUS 那样，使用输入的经度和纬度来决定中心点
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/geo/georadiusbymember.html" target="_blank">http://redisdoc.com/geo/georadiusbymember.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param member
	 * 		位置元素
	 * @param radius
	 * 		范围（单位：米）
	 *
	 * @return 位置元素
	 */
	default List<GeoRadius> geoRadiusByMember(final String key, final String member, final double radius) {
		return geoRadiusByMember(key, member, radius, GeoUnit.M);
	}

	/**
	 * 找出位于指定范围内的元素，
	 * 但是 GEORADIUSBYMEMBER 的中心点是由给定的位置元素决定的，而不是像 GEORADIUS 那样，使用输入的经度和纬度来决定中心点
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/geo/georadiusbymember.html" target="_blank">http://redisdoc.com/geo/georadiusbymember.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param member
	 * 		位置元素
	 * @param radius
	 * 		范围（单位：米）
	 *
	 * @return 位置元素
	 */
	default List<GeoRadius> geoRadiusByMember(final byte[] key, final byte[] member, final double radius) {
		return geoRadiusByMember(key, member, radius, GeoUnit.M);
	}

	/**
	 * 找出位于指定范围内的元素，
	 * 但是 GEORADIUSBYMEMBER 的中心点是由给定的位置元素决定的，而不是像 GEORADIUS 那样，使用输入的经度和纬度来决定中心点
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/geo/georadiusbymember.html" target="_blank">http://redisdoc.com/geo/georadiusbymember.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param member
	 * 		位置元素
	 * @param radius
	 * 		范围（单位：米）
	 * @param argument
	 * 		GEO Radius 参数
	 *
	 * @return 位置元素
	 */
	default List<GeoRadius> geoRadiusByMember(final String key, final String member, final double radius,
											  final GeoRadiusArgument argument) {
		return geoRadiusByMember(key, member, radius, GeoUnit.M, argument);
	}

	/**
	 * 找出位于指定范围内的元素，
	 * 但是 GEORADIUSBYMEMBER 的中心点是由给定的位置元素决定的，而不是像 GEORADIUS 那样，使用输入的经度和纬度来决定中心点
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/geo/georadiusbymember.html" target="_blank">http://redisdoc.com/geo/georadiusbymember.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param member
	 * 		位置元素
	 * @param radius
	 * 		范围（单位：米）
	 * @param argument
	 * 		GEO Radius 参数
	 *
	 * @return 位置元素
	 */
	default List<GeoRadius> geoRadiusByMember(final byte[] key, final byte[] member, final double radius,
											  final GeoRadiusArgument argument) {
		return geoRadiusByMember(key, member, radius, GeoUnit.M, argument);
	}

	@Override
	default List<GeoRadius> geoRadiusByMemberRo(final String key, final String member, final double radius,
												final GeoUnit unit) {
		return execute((client)->client.geoOperations().geoRadiusByMemberRo(key, member, radius, unit));
	}

	@Override
	default List<GeoRadius> geoRadiusByMemberRo(final byte[] key, final byte[] member, final double radius,
												final GeoUnit unit) {
		return execute((client)->client.geoOperations().geoRadiusByMemberRo(key, member, radius, unit));
	}

	@Override
	default List<GeoRadius> geoRadiusByMemberRo(final String key, final String member, final double radius,
												final GeoUnit unit, final GeoRadiusArgument argument) {
		return execute((client)->client.geoOperations().geoRadiusByMemberRo(key, member, radius, unit, argument));
	}

	@Override
	default List<GeoRadius> geoRadiusByMemberRo(final byte[] key, final byte[] member, final double radius,
												final GeoUnit unit, final GeoRadiusArgument argument) {
		return execute((client)->client.geoOperations().geoRadiusByMemberRo(key, member, radius, unit, argument));
	}

	/**
	 * Read-only variant of the GEORADIUSBYMEMBER command
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/georadiusbymember_ro/" target="_blank">https://redis.io/commands/georadiusbymember_ro/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param member
	 * 		位置元素
	 * @param radius
	 * 		范围
	 *
	 * @return 位置元素
	 */
	default List<GeoRadius> geoRadiusByMemberRo(final String key, final String member, final double radius) {
		return geoRadiusByMemberRo(key, member, radius, GeoUnit.M);
	}

	/**
	 * Read-only variant of the GEORADIUSBYMEMBER command
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/georadiusbymember_ro/" target="_blank">https://redis.io/commands/georadiusbymember_ro/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param member
	 * 		位置元素
	 * @param radius
	 * 		范围
	 *
	 * @return 位置元素
	 */
	default List<GeoRadius> geoRadiusByMemberRo(final byte[] key, final byte[] member, final double radius) {
		return geoRadiusByMemberRo(key, member, radius, GeoUnit.M);
	}

	/**
	 * Read-only variant of the GEORADIUSBYMEMBER command
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/georadiusbymember_ro/" target="_blank">https://redis.io/commands/georadiusbymember_ro/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param member
	 * 		位置元素
	 * @param radius
	 * 		范围
	 * @param argument
	 * 		GEO 参数
	 *
	 * @return 位置元素
	 */
	default List<GeoRadius> geoRadiusByMemberRo(final String key, final String member, final double radius,
												final GeoRadiusArgument argument) {
		return geoRadiusByMemberRo(key, member, radius, GeoUnit.M, argument);
	}

	/**
	 * Read-only variant of the GEORADIUSBYMEMBER command
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/georadiusbymember_ro/" target="_blank">https://redis.io/commands/georadiusbymember_ro/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param member
	 * 		位置元素
	 * @param radius
	 * 		范围
	 * @param argument
	 * 		GEO 参数
	 *
	 * @return 位置元素
	 */
	default List<GeoRadius> geoRadiusByMemberRo(final byte[] key, final byte[] member, final double radius,
												final GeoRadiusArgument argument) {
		return geoRadiusByMemberRo(key, member, radius, GeoUnit.M, argument);
	}

	@Override
	default List<GeoRadius> geoSearch(final String key, final String member, final double radius, final GeoUnit unit) {
		return execute((client)->client.geoOperations().geoSearch(key, member, radius, unit));
	}

	@Override
	default List<GeoRadius> geoSearch(final byte[] key, final byte[] member, final double radius, final GeoUnit unit) {
		return execute((client)->client.geoOperations().geoSearch(key, member, radius, unit));
	}

	@Override
	default List<GeoRadius> geoSearch(final String key, final double longitude, final double latitude,
									  final double radius, final GeoUnit unit) {
		return execute((client)->client.geoOperations().geoSearch(key, longitude, latitude, radius, unit));
	}

	@Override
	default List<GeoRadius> geoSearch(final byte[] key, final double longitude, final double latitude,
									  final double radius, final GeoUnit unit) {
		return execute((client)->client.geoOperations().geoSearch(key, longitude, latitude, radius, unit));
	}

	@Override
	default List<GeoRadius> geoSearch(final String key, final String member, final double width, final double height,
									  final GeoUnit unit) {
		return execute((client)->client.geoOperations().geoSearch(key, member, width, height, unit));
	}

	@Override
	default List<GeoRadius> geoSearch(final byte[] key, final byte[] member, final double width, final double height,
									  final GeoUnit unit) {
		return execute((client)->client.geoOperations().geoSearch(key, member, width, height, unit));
	}

	@Override
	default List<GeoRadius> geoSearch(final String key, final double longitude, final double latitude,
									  final double width, final double height, final GeoUnit unit) {
		return execute((client)->client.geoOperations().geoSearch(key, longitude, latitude, width, height, unit));
	}

	@Override
	default List<GeoRadius> geoSearch(final byte[] key, final double longitude, final double latitude,
									  final double width, final double height, final GeoUnit unit) {
		return execute((client)->client.geoOperations().geoSearch(key, longitude, latitude, width, height, unit));
	}

	@Override
	default List<GeoRadius> geoSearch(final String key, final String member, final double radius, final GeoUnit unit,
									  final GeoSearchArgument argument) {
		return execute((client)->client.geoOperations().geoSearch(key, member, radius, unit, argument));
	}

	@Override
	default List<GeoRadius> geoSearch(final byte[] key, final byte[] member, final double radius, final GeoUnit unit,
									  final GeoSearchArgument argument) {
		return execute((client)->client.geoOperations().geoSearch(key, member, radius, unit, argument));
	}

	@Override
	default List<GeoRadius> geoSearch(final String key, final double longitude, final double latitude,
									  final double radius, final GeoUnit unit, final GeoSearchArgument argument) {
		return execute((client)->client.geoOperations().geoSearch(key, longitude, latitude, radius, unit, argument));
	}

	@Override
	default List<GeoRadius> geoSearch(final byte[] key, final double longitude, final double latitude,
									  final double radius, final GeoUnit unit, final GeoSearchArgument argument) {
		return execute((client)->client.geoOperations().geoSearch(key, longitude, latitude, radius, unit, argument));
	}

	@Override
	default List<GeoRadius> geoSearch(final String key, final String member, final double width, final double height,
									  final GeoUnit unit, final GeoSearchArgument argument) {
		return execute((client)->client.geoOperations().geoSearch(key, member, width, height, unit, argument));
	}

	@Override
	default List<GeoRadius> geoSearch(final byte[] key, final byte[] member, final double width, final double height,
									  final GeoUnit unit, final GeoSearchArgument argument) {
		return execute((client)->client.geoOperations().geoSearch(key, member, width, height, unit, argument));
	}

	@Override
	default List<GeoRadius> geoSearch(final String key, final double longitude, final double latitude,
									  final double width, final double height, final GeoUnit unit,
									  final GeoSearchArgument argument) {
		return execute(
				(client)->client.geoOperations().geoSearch(key, longitude, latitude, width, height, unit, argument));
	}

	@Override
	default List<GeoRadius> geoSearch(final byte[] key, final double longitude, final double latitude,
									  final double width, final double height, final GeoUnit unit,
									  final GeoSearchArgument argument) {
		return execute(
				(client)->client.geoOperations().geoSearch(key, longitude, latitude, width, height, unit, argument));
	}

	/**
	 * 地理位置搜索
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearch/" target="_blank">https://redis.io/docs/latest/commands/geosearch/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param member
	 * 		位置元素
	 * @param radius
	 * 		范围
	 *
	 * @return 地理位置搜索结果
	 */
	default List<GeoRadius> geoSearch(final String key, final String member, final double radius) {
		return geoSearch(key, member, radius, GeoUnit.M);
	}

	/**
	 * 地理位置搜索
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearch/" target="_blank">https://redis.io/docs/latest/commands/geosearch/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param member
	 * 		位置元素
	 * @param radius
	 * 		范围
	 *
	 * @return 地理位置搜索结果
	 */
	default List<GeoRadius> geoSearch(final byte[] key, final byte[] member, final double radius) {
		return geoSearch(key, member, radius, GeoUnit.M);
	}

	/**
	 * 地理位置搜索
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearch/" target="_blank">https://redis.io/docs/latest/commands/geosearch/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param longitude
	 * 		经度
	 * @param latitude
	 * 		纬度
	 * @param radius
	 * 		范围
	 *
	 * @return 地理位置搜索结果
	 */
	default List<GeoRadius> geoSearch(final String key, final double longitude, final double latitude,
									  final double radius) {
		return geoSearch(key, longitude, latitude, radius, GeoUnit.M);
	}

	/**
	 * 地理位置搜索
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearch/" target="_blank">https://redis.io/docs/latest/commands/geosearch/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param longitude
	 * 		经度
	 * @param latitude
	 * 		纬度
	 * @param radius
	 * 		范围
	 *
	 * @return 地理位置搜索结果
	 */
	default List<GeoRadius> geoSearch(final byte[] key, final double longitude, final double latitude,
									  final double radius) {
		return geoSearch(key, longitude, latitude, radius, GeoUnit.M);
	}

	/**
	 * 地理位置搜索
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearch/" target="_blank">https://redis.io/docs/latest/commands/geosearch/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param geo
	 * 		经纬度
	 * @param radius
	 * 		范围
	 * @param unit
	 * 		距离单位
	 *
	 * @return 地理位置搜索结果
	 */
	default List<GeoRadius> geoSearch(final String key, final Geo geo, final double radius, final GeoUnit unit) {
		return geoSearch(key, geo.getLongitude(), geo.getLatitude(), radius, unit);
	}

	/**
	 * 地理位置搜索
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearch/" target="_blank">https://redis.io/docs/latest/commands/geosearch/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param geo
	 * 		经纬度
	 * @param radius
	 * 		范围
	 * @param unit
	 * 		距离单位
	 *
	 * @return 地理位置搜索结果
	 */
	default List<GeoRadius> geoSearch(final byte[] key, final Geo geo, final double radius, final GeoUnit unit) {
		return geoSearch(key, geo.getLongitude(), geo.getLatitude(), radius, unit);
	}

	/**
	 * 地理位置搜索
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearch/" target="_blank">https://redis.io/docs/latest/commands/geosearch/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param geo
	 * 		经纬度
	 * @param radius
	 * 		范围
	 *
	 * @return 地理位置搜索结果
	 */
	default List<GeoRadius> geoSearch(final String key, final Geo geo, final double radius) {
		return geoSearch(key, geo, radius, GeoUnit.M);
	}

	/**
	 * 地理位置搜索
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearch/" target="_blank">https://redis.io/docs/latest/commands/geosearch/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param geo
	 * 		经纬度
	 * @param radius
	 * 		范围
	 *
	 * @return 地理位置搜索结果
	 */
	default List<GeoRadius> geoSearch(final byte[] key, final Geo geo, final double radius) {
		return geoSearch(key, geo, radius, GeoUnit.M);
	}

	/**
	 * 地理位置搜索
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearch/" target="_blank">https://redis.io/docs/latest/commands/geosearch/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param member
	 * 		位置元素
	 * @param width
	 * 		宽度
	 * @param height
	 * 		高度
	 *
	 * @return 地理位置搜索结果
	 */
	default List<GeoRadius> geoSearch(final String key, final String member, final double width, final double height) {
		return geoSearch(key, member, width, height, GeoUnit.M);
	}

	/**
	 * 地理位置搜索
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearch/" target="_blank">https://redis.io/docs/latest/commands/geosearch/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param member
	 * 		位置元素
	 * @param width
	 * 		宽度
	 * @param height
	 * 		高度
	 *
	 * @return 地理位置搜索结果
	 */
	default List<GeoRadius> geoSearch(final byte[] key, final byte[] member, final double width, final double height) {
		return geoSearch(key, member, width, height, GeoUnit.M);
	}

	/**
	 * 地理位置搜索
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearch/" target="_blank">https://redis.io/docs/latest/commands/geosearch/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param longitude
	 * 		经度
	 * @param latitude
	 * 		纬度
	 * @param width
	 * 		宽度
	 * @param height
	 * 		高度
	 *
	 * @return 地理位置搜索结果
	 */
	default List<GeoRadius> geoSearch(final String key, final double longitude, final double latitude,
									  final double width, final double height) {
		return geoSearch(key, longitude, latitude, width, height, GeoUnit.M);
	}

	/**
	 * 地理位置搜索
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearch/" target="_blank">https://redis.io/docs/latest/commands/geosearch/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param longitude
	 * 		经度
	 * @param latitude
	 * 		纬度
	 * @param width
	 * 		宽度
	 * @param height
	 * 		高度
	 *
	 * @return 地理位置搜索结果
	 */
	default List<GeoRadius> geoSearch(final byte[] key, final double longitude, final double latitude,
									  final double width, final double height) {
		return geoSearch(key, longitude, latitude, width, height, GeoUnit.M);
	}

	/**
	 * 地理位置搜索
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearch/" target="_blank">https://redis.io/docs/latest/commands/geosearch/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param geo
	 * 		经纬度
	 * @param width
	 * 		宽度
	 * @param height
	 * 		高度
	 * @param unit
	 * 		距离单位
	 *
	 * @return 地理位置搜索结果
	 */
	default List<GeoRadius> geoSearch(final String key, final Geo geo, final double width, final double height,
									  final GeoUnit unit) {
		return geoSearch(key, geo.getLongitude(), geo.getLatitude(), width, height, unit);
	}

	/**
	 * 地理位置搜索
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearch/" target="_blank">https://redis.io/docs/latest/commands/geosearch/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param geo
	 * 		经纬度
	 * @param width
	 * 		宽度
	 * @param height
	 * 		高度
	 * @param unit
	 * 		距离单位
	 *
	 * @return 地理位置搜索结果
	 */
	default List<GeoRadius> geoSearch(final byte[] key, final Geo geo, final double width, final double height,
									  final GeoUnit unit) {
		return geoSearch(key, geo.getLongitude(), geo.getLatitude(), width, height, unit);
	}

	/**
	 * 地理位置搜索
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearch/" target="_blank">https://redis.io/docs/latest/commands/geosearch/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param geo
	 * 		经纬度
	 * @param width
	 * 		宽度
	 * @param height
	 * 		高度
	 *
	 * @return 地理位置搜索结果
	 */
	default List<GeoRadius> geoSearch(final String key, final Geo geo, final double width, final double height) {
		return geoSearch(key, geo.getLongitude(), geo.getLatitude(), width, height, GeoUnit.M);
	}

	/**
	 * 地理位置搜索
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearch/" target="_blank">https://redis.io/docs/latest/commands/geosearch/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param geo
	 * 		经纬度
	 * @param width
	 * 		宽度
	 * @param height
	 * 		高度
	 *
	 * @return 地理位置搜索结果
	 */
	default List<GeoRadius> geoSearch(final byte[] key, final Geo geo, final double width, final double height) {
		return geoSearch(key, geo.getLongitude(), geo.getLatitude(), width, height, GeoUnit.M);
	}

	/**
	 * 地理位置搜索
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearch/" target="_blank">https://redis.io/docs/latest/commands/geosearch/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param member
	 * 		位置元素
	 * @param radius
	 * 		范围
	 * @param argument
	 * 		搜索参数
	 *
	 * @return 地理位置搜索结果
	 */
	default List<GeoRadius> geoSearch(final String key, final String member, final double radius,
									  final GeoSearchArgument argument) {
		return geoSearch(key, member, radius, GeoUnit.M, argument);
	}

	/**
	 * 地理位置搜索
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearch/" target="_blank">https://redis.io/docs/latest/commands/geosearch/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param member
	 * 		位置元素
	 * @param radius
	 * 		范围
	 * @param argument
	 * 		搜索参数
	 *
	 * @return 地理位置搜索结果
	 */
	default List<GeoRadius> geoSearch(final byte[] key, final byte[] member, final double radius,
									  final GeoSearchArgument argument) {
		return geoSearch(key, member, radius, GeoUnit.M, argument);
	}

	/**
	 * 地理位置搜索
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearch/" target="_blank">https://redis.io/docs/latest/commands/geosearch/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param longitude
	 * 		经度
	 * @param latitude
	 * 		纬度
	 * @param radius
	 * 		范围
	 *
	 * @return 地理位置搜索结果
	 */
	default List<GeoRadius> geoSearch(final String key, final double longitude, final double latitude,
									  final double radius, final GeoSearchArgument argument) {
		return geoSearch(key, longitude, latitude, radius, GeoUnit.M, argument);
	}

	/**
	 * 地理位置搜索
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearch/" target="_blank">https://redis.io/docs/latest/commands/geosearch/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param longitude
	 * 		经度
	 * @param latitude
	 * 		纬度
	 * @param radius
	 * 		范围
	 * @param argument
	 * 		搜索参数
	 *
	 * @return 地理位置搜索结果
	 */
	default List<GeoRadius> geoSearch(final byte[] key, final double longitude, final double latitude,
									  final double radius, final GeoSearchArgument argument) {
		return geoSearch(key, longitude, latitude, radius, GeoUnit.M, argument);
	}

	/**
	 * 地理位置搜索
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearch/" target="_blank">https://redis.io/docs/latest/commands/geosearch/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param geo
	 * 		经纬度
	 * @param radius
	 * 		范围
	 * @param unit
	 * 		距离单位
	 * @param argument
	 * 		搜索参数
	 *
	 * @return 地理位置搜索结果
	 */
	default List<GeoRadius> geoSearch(final String key, final Geo geo, final double radius, final GeoUnit unit,
									  final GeoSearchArgument argument) {
		return geoSearch(key, geo.getLongitude(), geo.getLatitude(), radius, unit, argument);
	}

	/**
	 * 地理位置搜索
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearch/" target="_blank">https://redis.io/docs/latest/commands/geosearch/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param geo
	 * 		经纬度
	 * @param radius
	 * 		范围
	 * @param unit
	 * 		距离单位
	 * @param argument
	 * 		搜索参数
	 *
	 * @return 地理位置搜索结果
	 */
	default List<GeoRadius> geoSearch(final byte[] key, final Geo geo, final double radius, final GeoUnit unit,
									  final GeoSearchArgument argument) {
		return geoSearch(key, geo.getLongitude(), geo.getLatitude(), radius, unit, argument);
	}

	/**
	 * 地理位置搜索
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearch/" target="_blank">https://redis.io/docs/latest/commands/geosearch/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param geo
	 * 		经纬度
	 * @param radius
	 * 		范围
	 * @param argument
	 * 		搜索参数
	 *
	 * @return 地理位置搜索结果
	 */
	default List<GeoRadius> geoSearch(final String key, final Geo geo, final double radius,
									  final GeoSearchArgument argument) {
		return geoSearch(key, geo, radius, GeoUnit.M, argument);
	}

	/**
	 * 地理位置搜索
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearch/" target="_blank">https://redis.io/docs/latest/commands/geosearch/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param geo
	 * 		经纬度
	 * @param radius
	 * 		范围
	 * @param argument
	 * 		搜索参数
	 *
	 * @return 地理位置搜索结果
	 */
	default List<GeoRadius> geoSearch(final byte[] key, final Geo geo, final double radius,
									  final GeoSearchArgument argument) {
		return geoSearch(key, geo, radius, GeoUnit.M, argument);
	}

	/**
	 * 地理位置搜索
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearch/" target="_blank">https://redis.io/docs/latest/commands/geosearch/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param member
	 * 		位置元素
	 * @param width
	 * 		宽度
	 * @param height
	 * 		高度
	 * @param argument
	 * 		搜索参数
	 *
	 * @return 地理位置搜索结果
	 */
	default List<GeoRadius> geoSearch(final String key, final String member, final double width, final double height,
									  final GeoSearchArgument argument) {
		return geoSearch(key, member, width, height, GeoUnit.M, argument);
	}

	/**
	 * 地理位置搜索
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearch/" target="_blank">https://redis.io/docs/latest/commands/geosearch/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param member
	 * 		位置元素
	 * @param width
	 * 		宽度
	 * @param height
	 * 		高度
	 * @param argument
	 * 		搜索参数
	 *
	 * @return 地理位置搜索结果
	 */
	default List<GeoRadius> geoSearch(final byte[] key, final byte[] member, final double width, final double height,
									  final GeoSearchArgument argument) {
		return geoSearch(key, member, width, height, GeoUnit.M, argument);
	}

	/**
	 * 地理位置搜索
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearch/" target="_blank">https://redis.io/docs/latest/commands/geosearch/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param longitude
	 * 		经度
	 * @param latitude
	 * 		纬度
	 * @param width
	 * 		宽度
	 * @param height
	 * 		高度
	 * @param argument
	 * 		搜索参数
	 *
	 * @return 地理位置搜索结果
	 */
	default List<GeoRadius> geoSearch(final String key, final double longitude, final double latitude,
									  final double width, final double height, final GeoSearchArgument argument) {
		return geoSearch(key, longitude, latitude, width, height, GeoUnit.M, argument);
	}

	/**
	 * 地理位置搜索
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearch/" target="_blank">https://redis.io/docs/latest/commands/geosearch/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param longitude
	 * 		经度
	 * @param latitude
	 * 		纬度
	 * @param width
	 * 		宽度
	 * @param height
	 * 		高度
	 * @param argument
	 * 		搜索参数
	 *
	 * @return 地理位置搜索结果
	 */
	default List<GeoRadius> geoSearch(final byte[] key, final double longitude, final double latitude,
									  final double width, final double height, final GeoSearchArgument argument) {
		return geoSearch(key, longitude, latitude, width, height, GeoUnit.M, argument);
	}

	/**
	 * 地理位置搜索
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearch/" target="_blank">https://redis.io/docs/latest/commands/geosearch/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param geo
	 * 		经纬度
	 * @param width
	 * 		宽度
	 * @param height
	 * 		高度
	 * @param unit
	 * 		距离单位
	 * @param argument
	 * 		搜索参数
	 *
	 * @return 地理位置搜索结果
	 */
	default List<GeoRadius> geoSearch(final String key, final Geo geo, final double width, final double height,
									  final GeoUnit unit, final GeoSearchArgument argument) {
		return geoSearch(key, geo.getLongitude(), geo.getLatitude(), width, height, unit, argument);
	}

	/**
	 * 地理位置搜索
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearch/" target="_blank">https://redis.io/docs/latest/commands/geosearch/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param geo
	 * 		经纬度
	 * @param width
	 * 		宽度
	 * @param height
	 * 		高度
	 * @param unit
	 * 		距离单位
	 * @param argument
	 * 		搜索参数
	 *
	 * @return 地理位置搜索结果
	 */
	default List<GeoRadius> geoSearch(final byte[] key, final Geo geo, final double width, final double height,
									  final GeoUnit unit, final GeoSearchArgument argument) {
		return geoSearch(key, geo.getLongitude(), geo.getLatitude(), width, height, unit, argument);
	}

	/**
	 * 地理位置搜索
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearch/" target="_blank">https://redis.io/docs/latest/commands/geosearch/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param geo
	 * 		经纬度
	 * @param width
	 * 		宽度
	 * @param height
	 * 		高度
	 * @param argument
	 * 		搜索参数
	 *
	 * @return 地理位置搜索结果
	 */
	default List<GeoRadius> geoSearch(final String key, final Geo geo, final double width, final double height,
									  final GeoSearchArgument argument) {
		return geoSearch(key, geo.getLongitude(), geo.getLatitude(), width, height, GeoUnit.M, argument);
	}

	/**
	 * 地理位置搜索
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearch/" target="_blank">https://redis.io/docs/latest/commands/geosearch/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param geo
	 * 		经纬度
	 * @param width
	 * 		宽度
	 * @param height
	 * 		高度
	 * @param argument
	 * 		搜索参数
	 *
	 * @return 地理位置搜索结果
	 */
	default List<GeoRadius> geoSearch(final byte[] key, final Geo geo, final double width, final double height,
									  final GeoSearchArgument argument) {
		return geoSearch(key, geo.getLongitude(), geo.getLatitude(), width, height, GeoUnit.M, argument);
	}

	@Override
	default Long geoSearchStore(final String destKey, final String key, final String member, final double radius,
								final GeoUnit unit) {
		return execute((client)->client.geoOperations().geoSearchStore(destKey, key, member, radius, unit));
	}

	@Override
	default Long geoSearchStore(final byte[] destKey, final byte[] key, final byte[] member, final double radius,
								final GeoUnit unit) {
		return execute((client)->client.geoOperations().geoSearchStore(destKey, key, member, radius, unit));
	}

	@Override
	default Long geoSearchStore(final String destKey, final String key, final double longitude, final double latitude,
								final double radius, final GeoUnit unit) {
		return execute(
				(client)->client.geoOperations().geoSearchStore(destKey, key, longitude, latitude, radius, unit));
	}

	@Override
	default Long geoSearchStore(final byte[] destKey, final byte[] key, final double longitude, final double latitude,
								final double radius, final GeoUnit unit) {
		return execute(
				(client)->client.geoOperations().geoSearchStore(destKey, key, longitude, latitude, radius, unit));
	}

	@Override
	default Long geoSearchStore(final String destKey, final String key, final String member, final double width,
								final double height, final GeoUnit unit) {
		return execute((client)->client.geoOperations().geoSearchStore(destKey, key, member, width, height, unit));
	}

	@Override
	default Long geoSearchStore(final byte[] destKey, final byte[] key, final byte[] member, final double width,
								final double height, final GeoUnit unit) {
		return execute((client)->client.geoOperations().geoSearchStore(destKey, key, member, width, height, unit));
	}

	@Override
	default Long geoSearchStore(final String destKey, final String key, final double longitude, final double latitude,
								final double width, final double height, final GeoUnit unit) {
		return execute(
				(client)->client.geoOperations()
						.geoSearchStore(destKey, key, longitude, latitude, width, height, unit));
	}

	@Override
	default Long geoSearchStore(final byte[] destKey, final byte[] key, final double longitude, final double latitude,
								final double width, final double height, final GeoUnit unit) {
		return execute(
				(client)->client.geoOperations()
						.geoSearchStore(destKey, key, longitude, latitude, width, height, unit));
	}

	@Override
	default Long geoSearchStore(final String destKey, final String key, final String member, final double radius,
								final GeoUnit unit, final GeoSearchArgument argument) {
		return execute((client)->client.geoOperations().geoSearchStore(destKey, key, member, radius, unit, argument));
	}

	@Override
	default Long geoSearchStore(final byte[] destKey, final byte[] key, final byte[] member, final double radius,
								final GeoUnit unit, final GeoSearchArgument argument) {
		return execute((client)->client.geoOperations().geoSearchStore(destKey, key, member, radius, unit, argument));
	}

	@Override
	default Long geoSearchStore(final String destKey, final String key, final double longitude, final double latitude,
								final double radius, final GeoUnit unit, final GeoSearchArgument argument) {
		return execute((client)->client.geoOperations()
				.geoSearchStore(destKey, key, longitude, latitude, radius, unit, argument));
	}

	@Override
	default Long geoSearchStore(final byte[] destKey, final byte[] key, final double longitude, final double latitude,
								final double radius, final GeoUnit unit, final GeoSearchArgument argument) {
		return execute((client)->client.geoOperations()
				.geoSearchStore(destKey, key, longitude, latitude, radius, unit, argument));
	}

	@Override
	default Long geoSearchStore(final String destKey, final String key, final String member, final double width,
								final double height,
								final GeoUnit unit, final GeoSearchArgument argument) {
		return execute(
				(client)->client.geoOperations().geoSearchStore(destKey, key, member, width, height, unit, argument));
	}

	@Override
	default Long geoSearchStore(final byte[] destKey, final byte[] key, final byte[] member, final double width,
								final double height, final GeoUnit unit, final GeoSearchArgument argument) {
		return execute(
				(client)->client.geoOperations().geoSearchStore(destKey, key, member, width, height, unit, argument));
	}

	@Override
	default Long geoSearchStore(final String destKey, final String key, final double longitude, final double latitude,
								final double width, final double height, final GeoUnit unit,
								final GeoSearchArgument argument) {
		return execute(
				(client)->client.geoOperations()
						.geoSearchStore(destKey, key, longitude, latitude, width, height, unit, argument));
	}

	@Override
	default Long geoSearchStore(final byte[] destKey, final byte[] key, final double longitude, final double latitude,
								final double width, final double height, final GeoUnit unit,
								final GeoSearchArgument argument) {
		return execute(
				(client)->client.geoOperations()
						.geoSearchStore(destKey, key, longitude, latitude, width, height, unit, argument));
	}

	/**
	 * GEOSEARCH 的结果存储到一个指定的有序集合中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearchstore/" target="_blank">https://redis.io/docs/latest/commands/geosearchstore/</a></p>
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param key
	 * 		Key
	 * @param member
	 * 		位置元素
	 * @param radius
	 * 		范围
	 *
	 * @return 存储的元素数量
	 */
	default Long geoSearchStore(final String destKey, final String key, final String member, final double radius) {
		return geoSearchStore(destKey, key, member, radius, GeoUnit.M);
	}

	/**
	 * GEOSEARCH 的结果存储到一个指定的有序集合中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearchstore/" target="_blank">https://redis.io/docs/latest/commands/geosearchstore/</a></p>
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param key
	 * 		Key
	 * @param member
	 * 		位置元素
	 * @param radius
	 * 		范围
	 *
	 * @return 存储的元素数量
	 */
	default Long geoSearchStore(final byte[] destKey, final byte[] key, final byte[] member, final double radius) {
		return geoSearchStore(destKey, key, member, radius, GeoUnit.M);
	}

	/**
	 * GEOSEARCH 的结果存储到一个指定的有序集合中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearchstore/" target="_blank">https://redis.io/docs/latest/commands/geosearchstore/</a></p>
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param key
	 * 		Key
	 * @param longitude
	 * 		经度
	 * @param latitude
	 * 		纬度
	 * @param radius
	 * 		范围
	 *
	 * @return 存储的元素数量
	 */
	default Long geoSearchStore(final String destKey, final String key, final double longitude, final double latitude,
								final double radius) {
		return geoSearchStore(destKey, key, longitude, latitude, radius, GeoUnit.M);
	}

	/**
	 * GEOSEARCH 的结果存储到一个指定的有序集合中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearchstore/" target="_blank">https://redis.io/docs/latest/commands/geosearchstore/</a></p>
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param key
	 * 		Key
	 * @param longitude
	 * 		经度
	 * @param latitude
	 * 		纬度
	 * @param radius
	 * 		范围
	 *
	 * @return 存储的元素数量
	 */
	default Long geoSearchStore(final byte[] destKey, final byte[] key, final double longitude, final double latitude,
								final double radius) {
		return geoSearchStore(destKey, key, longitude, latitude, radius, GeoUnit.M);
	}

	/**
	 * GEOSEARCH 的结果存储到一个指定的有序集合中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearchstore/" target="_blank">https://redis.io/docs/latest/commands/geosearchstore/</a></p>
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param key
	 * 		Key
	 * @param geo
	 * 		经纬度
	 * @param radius
	 * 		范围
	 * @param unit
	 * 		距离单位
	 *
	 * @return 存储的元素数量
	 */
	default Long geoSearchStore(final String destKey, final String key, final Geo geo, final double radius,
								final GeoUnit unit) {
		return geoSearchStore(destKey, key, geo.getLongitude(), geo.getLatitude(), radius, unit);
	}

	/**
	 * GEOSEARCH 的结果存储到一个指定的有序集合中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearchstore/" target="_blank">https://redis.io/docs/latest/commands/geosearchstore/</a></p>
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param key
	 * 		Key
	 * @param geo
	 * 		经纬度
	 * @param radius
	 * 		范围
	 * @param unit
	 * 		距离单位
	 *
	 * @return 存储的元素数量
	 */
	default Long geoSearchStore(final byte[] destKey, final byte[] key, final Geo geo, final double radius,
								final GeoUnit unit) {
		return geoSearchStore(destKey, key, geo.getLongitude(), geo.getLatitude(), radius, unit);
	}

	/**
	 * GEOSEARCH 的结果存储到一个指定的有序集合中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearchstore/" target="_blank">https://redis.io/docs/latest/commands/geosearchstore/</a></p>
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param key
	 * 		Key
	 * @param geo
	 * 		经纬度
	 * @param radius
	 * 		范围
	 *
	 * @return 存储的元素数量
	 */
	default Long geoSearchStore(final String destKey, final String key, final Geo geo, final double radius) {
		return geoSearchStore(destKey, key, geo, radius, GeoUnit.M);
	}

	/**
	 * GEOSEARCH 的结果存储到一个指定的有序集合中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearchstore/" target="_blank">https://redis.io/docs/latest/commands/geosearchstore/</a></p>
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param key
	 * 		Key
	 * @param geo
	 * 		经纬度
	 * @param radius
	 * 		范围
	 *
	 * @return 存储的元素数量
	 */
	default Long geoSearchStore(final byte[] destKey, final byte[] key, final Geo geo, final double radius) {
		return geoSearchStore(destKey, key, geo, radius, GeoUnit.M);
	}

	/**
	 * GEOSEARCH 的结果存储到一个指定的有序集合中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearchstore/" target="_blank">https://redis.io/docs/latest/commands/geosearchstore/</a></p>
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param key
	 * 		Key
	 * @param member
	 * 		位置元素
	 * @param width
	 * 		宽度
	 * @param height
	 * 		高度
	 *
	 * @return 存储的元素数量
	 */
	default Long geoSearchStore(final String destKey, final String key, final String member, final double width,
								final double height) {
		return geoSearchStore(destKey, key, member, width, height, GeoUnit.M);
	}

	/**
	 * GEOSEARCH 的结果存储到一个指定的有序集合中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearchstore/" target="_blank">https://redis.io/docs/latest/commands/geosearchstore/</a></p>
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param key
	 * 		Key
	 * @param member
	 * 		位置元素
	 * @param width
	 * 		宽度
	 * @param height
	 * 		高度
	 *
	 * @return 存储的元素数量
	 */
	default Long geoSearchStore(final byte[] destKey, final byte[] key, final byte[] member, final double width,
								final double height) {
		return geoSearchStore(destKey, key, member, width, height, GeoUnit.M);
	}

	/**
	 * GEOSEARCH 的结果存储到一个指定的有序集合中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearchstore/" target="_blank">https://redis.io/docs/latest/commands/geosearchstore/</a></p>
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param key
	 * 		Key
	 * @param longitude
	 * 		经度
	 * @param latitude
	 * 		纬度
	 * @param width
	 * 		宽度
	 * @param height
	 * 		高度
	 *
	 * @return 存储的元素数量
	 */
	default Long geoSearchStore(final String destKey, final String key, final double longitude, final double latitude,
								final double width, final double height) {
		return geoSearchStore(destKey, key, longitude, latitude, width, height, GeoUnit.M);
	}

	/**
	 * GEOSEARCH 的结果存储到一个指定的有序集合中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearchstore/" target="_blank">https://redis.io/docs/latest/commands/geosearchstore/</a></p>
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param key
	 * 		Key
	 * @param longitude
	 * 		经度
	 * @param latitude
	 * 		纬度
	 * @param width
	 * 		宽度
	 * @param height
	 * 		高度
	 *
	 * @return 存储的元素数量
	 */
	default Long geoSearchStore(final byte[] destKey, final byte[] key, final double longitude, final double latitude,
								final double width, final double height) {
		return geoSearchStore(destKey, key, longitude, latitude, width, height, GeoUnit.M);
	}

	/**
	 * GEOSEARCH 的结果存储到一个指定的有序集合中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearchstore/" target="_blank">https://redis.io/docs/latest/commands/geosearchstore/</a></p>
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param key
	 * 		Key
	 * @param geo
	 * 		经纬度
	 * @param width
	 * 		宽度
	 * @param height
	 * 		高度
	 * @param unit
	 * 		距离单位
	 *
	 * @return 存储的元素数量
	 */
	default Long geoSearchStore(final String destKey, final String key, final Geo geo, final double width,
								final double height, final GeoUnit unit) {
		return geoSearchStore(destKey, key, geo.getLongitude(), geo.getLatitude(), width, height, unit);
	}

	/**
	 * GEOSEARCH 的结果存储到一个指定的有序集合中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearchstore/" target="_blank">https://redis.io/docs/latest/commands/geosearchstore/</a></p>
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param key
	 * 		Key
	 * @param geo
	 * 		经纬度
	 * @param width
	 * 		宽度
	 * @param height
	 * 		高度
	 * @param unit
	 * 		距离单位
	 *
	 * @return 存储的元素数量
	 */
	default Long geoSearchStore(final byte[] destKey, final byte[] key, final Geo geo, final double width,
								final double height, final GeoUnit unit) {
		return geoSearchStore(destKey, key, geo.getLongitude(), geo.getLatitude(), width, height, unit);
	}

	/**
	 * GEOSEARCH 的结果存储到一个指定的有序集合中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearchstore/" target="_blank">https://redis.io/docs/latest/commands/geosearchstore/</a></p>
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param key
	 * 		Key
	 * @param geo
	 * 		经纬度
	 * @param width
	 * 		宽度
	 * @param height
	 * 		高度
	 *
	 * @return 存储的元素数量
	 */
	default Long geoSearchStore(final String destKey, final String key, final Geo geo, final double width,
								final double height) {
		return geoSearchStore(destKey, key, geo.getLongitude(), geo.getLatitude(), width, height, GeoUnit.M);
	}

	/**
	 * GEOSEARCH 的结果存储到一个指定的有序集合中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearchstore/" target="_blank">https://redis.io/docs/latest/commands/geosearchstore/</a></p>
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param key
	 * 		Key
	 * @param geo
	 * 		经纬度
	 * @param width
	 * 		宽度
	 * @param height
	 * 		高度
	 *
	 * @return 存储的元素数量
	 */
	default Long geoSearchStore(final byte[] destKey, final byte[] key, final Geo geo, final double width,
								final double height) {
		return geoSearchStore(destKey, key, geo.getLongitude(), geo.getLatitude(), width, height, GeoUnit.M);
	}

	/**
	 * GEOSEARCH 的结果存储到一个指定的有序集合中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearchstore/" target="_blank">https://redis.io/docs/latest/commands/geosearchstore/</a></p>
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param key
	 * 		Key
	 * @param member
	 * 		位置元素
	 * @param radius
	 * 		范围
	 * @param argument
	 * 		搜索参数
	 *
	 * @return 存储的元素数量
	 */
	default Long geoSearchStore(final String destKey, final String key, final String member, final double radius,
								final GeoSearchArgument argument) {
		return geoSearchStore(destKey, key, member, radius, GeoUnit.M, argument);
	}

	/**
	 * GEOSEARCH 的结果存储到一个指定的有序集合中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearchstore/" target="_blank">https://redis.io/docs/latest/commands/geosearchstore/</a></p>
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param key
	 * 		Key
	 * @param member
	 * 		位置元素
	 * @param radius
	 * 		范围
	 * @param argument
	 * 		搜索参数
	 *
	 * @return 存储的元素数量
	 */
	default Long geoSearchStore(final byte[] destKey, final byte[] key, final byte[] member, final double radius,
								final GeoSearchArgument argument) {
		return geoSearchStore(destKey, key, member, radius, GeoUnit.M, argument);
	}

	/**
	 * GEOSEARCH 的结果存储到一个指定的有序集合中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearchstore/" target="_blank">https://redis.io/docs/latest/commands/geosearchstore/</a></p>
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param key
	 * 		Key
	 * @param longitude
	 * 		经度
	 * @param latitude
	 * 		纬度
	 * @param radius
	 * 		范围
	 * @param argument
	 * 		搜索参数
	 *
	 * @return 存储的元素数量
	 */
	default Long geoSearchStore(final String destKey, final String key, final double longitude, final double latitude,
								final double radius, final GeoSearchArgument argument) {
		return geoSearchStore(destKey, key, longitude, latitude, radius, GeoUnit.M, argument);
	}

	/**
	 * GEOSEARCH 的结果存储到一个指定的有序集合中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearchstore/" target="_blank">https://redis.io/docs/latest/commands/geosearchstore/</a></p>
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param key
	 * 		Key
	 * @param longitude
	 * 		经度
	 * @param latitude
	 * 		纬度
	 * @param radius
	 * 		范围
	 * @param argument
	 * 		搜索参数
	 *
	 * @return 存储的元素数量
	 */
	default Long geoSearchStore(final byte[] destKey, final byte[] key, final double longitude, final double latitude,
								final double radius, final GeoSearchArgument argument) {
		return geoSearchStore(destKey, key, longitude, latitude, radius, GeoUnit.M, argument);
	}

	/**
	 * GEOSEARCH 的结果存储到一个指定的有序集合中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearchstore/" target="_blank">https://redis.io/docs/latest/commands/geosearchstore/</a></p>
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param key
	 * 		Key
	 * @param geo
	 * 		经纬度
	 * @param radius
	 * 		范围
	 * @param unit
	 * 		距离单位
	 * @param argument
	 * 		搜索参数
	 *
	 * @return 存储的元素数量
	 */
	default Long geoSearchStore(final String destKey, final String key, final Geo geo, final double radius,
								final GeoUnit unit,
								final GeoSearchArgument argument) {
		return geoSearchStore(destKey, key, geo.getLongitude(), geo.getLatitude(), radius, unit, argument);
	}

	/**
	 * GEOSEARCH 的结果存储到一个指定的有序集合中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearchstore/" target="_blank">https://redis.io/docs/latest/commands/geosearchstore/</a></p>
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param key
	 * 		Key
	 * @param geo
	 * 		经纬度
	 * @param radius
	 * 		范围
	 * @param unit
	 * 		距离单位
	 * @param argument
	 * 		搜索参数
	 *
	 * @return 存储的元素数量
	 */
	default Long geoSearchStore(final byte[] destKey, final byte[] key, final Geo geo, final double radius,
								final GeoUnit unit,
								final GeoSearchArgument argument) {
		return geoSearchStore(destKey, key, geo.getLongitude(), geo.getLatitude(), radius, unit, argument);
	}

	/**
	 * GEOSEARCH 的结果存储到一个指定的有序集合中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearchstore/" target="_blank">https://redis.io/docs/latest/commands/geosearchstore/</a></p>
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param key
	 * 		Key
	 * @param geo
	 * 		经纬度
	 * @param radius
	 * 		范围
	 * @param argument
	 * 		搜索参数
	 *
	 * @return 存储的元素数量
	 */
	default Long geoSearchStore(final String destKey, final String key, final Geo geo, final double radius,
								final GeoSearchArgument argument) {
		return geoSearchStore(destKey, key, geo, radius, GeoUnit.M, argument);
	}

	/**
	 * GEOSEARCH 的结果存储到一个指定的有序集合中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearchstore/" target="_blank">https://redis.io/docs/latest/commands/geosearchstore/</a></p>
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param key
	 * 		Key
	 * @param geo
	 * 		经纬度
	 * @param radius
	 * 		范围
	 * @param argument
	 * 		搜索参数
	 *
	 * @return 存储的元素数量
	 */
	default Long geoSearchStore(final byte[] destKey, final byte[] key, final Geo geo, final double radius,
								final GeoSearchArgument argument) {
		return geoSearchStore(destKey, key, geo, radius, GeoUnit.M, argument);
	}

	/**
	 * GEOSEARCH 的结果存储到一个指定的有序集合中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearchstore/" target="_blank">https://redis.io/docs/latest/commands/geosearchstore/</a></p>
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param key
	 * 		Key
	 * @param member
	 * 		位置元素
	 * @param argument
	 * 		搜索参数
	 *
	 * @return 存储的元素数量
	 */
	default Long geoSearchStore(final String destKey, final String key, final String member, final double width,
								final double height,
								final GeoSearchArgument argument) {
		return geoSearchStore(destKey, key, member, width, height, GeoUnit.M, argument);
	}

	/**
	 * GEOSEARCH 的结果存储到一个指定的有序集合中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearchstore/" target="_blank">https://redis.io/docs/latest/commands/geosearchstore/</a></p>
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param key
	 * 		Key
	 * @param member
	 * 		位置元素
	 * @param argument
	 * 		搜索参数
	 *
	 * @return 存储的元素数量
	 */
	default Long geoSearchStore(final byte[] destKey, final byte[] key, final byte[] member, final double width,
								final double height,
								final GeoSearchArgument argument) {
		return geoSearchStore(destKey, key, member, width, height, GeoUnit.M, argument);
	}

	/**
	 * GEOSEARCH 的结果存储到一个指定的有序集合中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearchstore/" target="_blank">https://redis.io/docs/latest/commands/geosearchstore/</a></p>
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param key
	 * 		Key
	 * @param longitude
	 * 		经度
	 * @param latitude
	 * 		纬度
	 * @param width
	 * 		宽度
	 * @param height
	 * 		高度
	 * @param argument
	 * 		搜索参数
	 *
	 * @return 存储的元素数量
	 */
	default Long geoSearchStore(final String destKey, final String key, final double longitude, final double latitude,
								final double width, final double height, final GeoSearchArgument argument) {
		return geoSearchStore(destKey, key, longitude, latitude, width, height, GeoUnit.M, argument);
	}

	/**
	 * GEOSEARCH 的结果存储到一个指定的有序集合中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearchstore/" target="_blank">https://redis.io/docs/latest/commands/geosearchstore/</a></p>
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param key
	 * 		Key
	 * @param longitude
	 * 		经度
	 * @param latitude
	 * 		纬度
	 * @param width
	 * 		宽度
	 * @param height
	 * 		高度
	 * @param argument
	 * 		搜索参数
	 *
	 * @return 存储的元素数量
	 */
	default Long geoSearchStore(final byte[] destKey, final byte[] key, final double longitude, final double latitude,
								final double width, final double height, final GeoSearchArgument argument) {
		return geoSearchStore(destKey, key, longitude, latitude, width, height, GeoUnit.M, argument);
	}

	/**
	 * GEOSEARCH 的结果存储到一个指定的有序集合中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearchstore/" target="_blank">https://redis.io/docs/latest/commands/geosearchstore/</a></p>
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param key
	 * 		Key
	 * @param geo
	 * 		经纬度
	 * @param width
	 * 		宽度
	 * @param height
	 * 		高度
	 * @param unit
	 * 		距离单位
	 * @param argument
	 * 		搜索参数
	 *
	 * @return 存储的元素数量
	 */
	default Long geoSearchStore(final String destKey, final String key, final Geo geo, final double width,
								final double height, final GeoUnit unit, final GeoSearchArgument argument) {
		return geoSearchStore(destKey, key, geo.getLongitude(), geo.getLatitude(), width, height, unit, argument);
	}

	/**
	 * GEOSEARCH 的结果存储到一个指定的有序集合中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearchstore/" target="_blank">https://redis.io/docs/latest/commands/geosearchstore/</a></p>
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param key
	 * 		Key
	 * @param geo
	 * 		经纬度
	 * @param width
	 * 		宽度
	 * @param height
	 * 		高度
	 * @param unit
	 * 		距离单位
	 * @param argument
	 * 		搜索参数
	 *
	 * @return 存储的元素数量
	 */
	default Long geoSearchStore(final byte[] destKey, final byte[] key, final Geo geo, final double width,
								final double height, final GeoUnit unit, final GeoSearchArgument argument) {
		return geoSearchStore(destKey, key, geo.getLongitude(), geo.getLatitude(), width, height, unit, argument);
	}

	/**
	 * GEOSEARCH 的结果存储到一个指定的有序集合中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearchstore/" target="_blank">https://redis.io/docs/latest/commands/geosearchstore/</a></p>
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param key
	 * 		Key
	 * @param geo
	 * 		经纬度
	 * @param width
	 * 		宽度
	 * @param height
	 * 		高度
	 * @param argument
	 * 		搜索参数
	 *
	 * @return 存储的元素数量
	 */
	default Long geoSearchStore(final String destKey, final String key, final Geo geo, final double width,
								final double height, final GeoSearchArgument argument) {
		return geoSearchStore(destKey, key, geo.getLongitude(), geo.getLatitude(), width, height, GeoUnit.M, argument);
	}

	/**
	 * GEOSEARCH 的结果存储到一个指定的有序集合中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearchstore/" target="_blank">https://redis.io/docs/latest/commands/geosearchstore/</a></p>
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param key
	 * 		Key
	 * @param geo
	 * 		经纬度
	 * @param width
	 * 		宽度
	 * @param height
	 * 		高度
	 * @param argument
	 * 		搜索参数
	 *
	 * @return 存储的元素数量
	 */
	default Long geoSearchStore(final byte[] destKey, final byte[] key, final Geo geo, final double width,
								final double height, final GeoSearchArgument argument) {
		return geoSearchStore(destKey, key, geo.getLongitude(), geo.getLatitude(), width, height, GeoUnit.M, argument);
	}

}
