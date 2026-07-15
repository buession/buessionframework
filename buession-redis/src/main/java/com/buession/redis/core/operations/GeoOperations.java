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
import com.buession.lang.KeyValue;
import com.buession.redis.core.GeoRadius;
import com.buession.redis.core.command.Command;
import com.buession.redis.core.command.args.geo.GeoStoreOption;
import com.buession.redis.core.command.args.geo.GeoUnit;
import com.buession.redis.core.command.args.NxXx;
import com.buession.redis.core.command.GeoCommands;
import com.buession.redis.core.command.args.geo.GeoRadiusArgument;
import com.buession.redis.core.command.args.geo.GeoSearchArgument;
import com.buession.redis.utils.KeyUtils;

import java.util.List;

/**
 * 地理位置运算
 *
 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/?group=geo" target="_blank">https://redis.io/docs/latest/commands/?group=geo</a></p>
 *
 * @author Yong.Teng
 */
public interface GeoOperations extends GeoCommands, RedisOperations {

	@SuppressWarnings({"unchecked"})
	@Override
	default Long geoAdd(final String key, final KeyValue<String, Geo>... memberCoordinates) {
		return doExecute((cmd)->cmd.geoAdd(KeyUtils.rawKey(this, key), memberCoordinates));
	}

	@SuppressWarnings({"unchecked"})
	@Override
	default Long geoAdd(final byte[] key, final KeyValue<byte[], Geo>... memberCoordinates) {
		return doExecute((cmd)->cmd.geoAdd(KeyUtils.rawKey(this, key), memberCoordinates));
	}

	/**
	 * 批量将给定的空间元素（经度、纬度、名字）添加到指定的键里面
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geoadd/" target="_blank">https://redis.io/docs/latest/commands/geoadd/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param member
	 * 		成员
	 * @param longitude
	 * 		精度
	 * @param latitude
	 * 		纬度
	 *
	 * @return 新添加到键里面的空间元素数量，不包括那些已经存在但是被更新的元素
	 */
	default Long geoAdd(final String key, final String member, final double longitude, final double latitude) {
		return geoAdd(key, member, new Geo(longitude, latitude));
	}

	/**
	 * 批量将给定的空间元素（经度、纬度、名字）添加到指定的键里面
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geoadd/" target="_blank">https://redis.io/docs/latest/commands/geoadd/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param member
	 * 		成员
	 * @param longitude
	 * 		精度
	 * @param latitude
	 * 		纬度
	 *
	 * @return 新添加到键里面的空间元素数量，不包括那些已经存在但是被更新的元素
	 */
	default Long geoAdd(final byte[] key, final byte[] member, final double longitude, final double latitude) {
		return geoAdd(key, member, new Geo(longitude, latitude));
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
	@SuppressWarnings({"unchecked"})
	default Long geoAdd(final String key, final String member, final Geo geo) {
		return geoAdd(key, new KeyValue<>(member, geo));
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
	@SuppressWarnings({"unchecked"})
	default Long geoAdd(final byte[] key, final byte[] member, final Geo geo) {
		return geoAdd(key, new KeyValue<>(member, geo));
	}

	@SuppressWarnings({"unchecked"})
	@Override
	default Long geoAdd(final String key, final NxXx nxXx, final KeyValue<String, Geo>... memberCoordinates) {
		return doExecute((cmd)->cmd.geoAdd(KeyUtils.rawKey(this, key), nxXx, memberCoordinates));
	}

	@SuppressWarnings({"unchecked"})
	@Override
	default Long geoAdd(final byte[] key, final NxXx nxXx, final KeyValue<byte[], Geo>... memberCoordinates) {
		return doExecute((cmd)->cmd.geoAdd(KeyUtils.rawKey(this, key), nxXx, memberCoordinates));
	}

	/**
	 * 将给定的空间元素（经度、纬度、名字）添加到指定的键里面
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/geo/geoadd.html" target="_blank">http://redisdoc.com/geo/geoadd.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param nxXx
	 *        {@link NxXx}
	 * @param member
	 * 		名字
	 * @param longitude
	 * 		经度
	 * @param latitude
	 * 		纬度
	 *
	 * @return 新添加到键里面的空间元素数量，不包括那些已经存在但是被更新的元素
	 */
	default Long geoAdd(final String key, final NxXx nxXx, final String member, final double longitude,
	                    final double latitude) {
		return geoAdd(key, nxXx, member, new Geo(longitude, latitude));
	}

	/**
	 * 将给定的空间元素（经度、纬度、名字）添加到指定的键里面
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/geo/geoadd.html" target="_blank">http://redisdoc.com/geo/geoadd.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param nxXx
	 *        {@link NxXx}
	 * @param member
	 * 		名字
	 * @param longitude
	 * 		经度
	 * @param latitude
	 * 		纬度
	 *
	 * @return 新添加到键里面的空间元素数量，不包括那些已经存在但是被更新的元素
	 */
	default Long geoAdd(final byte[] key, final NxXx nxXx, final byte[] member, final double longitude,
	                    final double latitude) {
		return geoAdd(key, nxXx, member, new Geo(longitude, latitude));
	}

	/**
	 * 将给定的空间元素（经度、纬度、名字）添加到指定的键里面
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/geo/geoadd.html" target="_blank">http://redisdoc.com/geo/geoadd.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param nxXx
	 *        {@link NxXx}
	 * @param member
	 * 		名字
	 * @param geo
	 * 		经纬度对象
	 *
	 * @return 新添加到键里面的空间元素数量，不包括那些已经存在但是被更新的元素
	 */
	@SuppressWarnings({"unchecked"})
	default Long geoAdd(final String key, final NxXx nxXx, final String member, final Geo geo) {
		return geoAdd(key, nxXx, new KeyValue<>(member, geo));
	}

	/**
	 * 将给定的空间元素（经度、纬度、名字）添加到指定的键里面
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/geo/geoadd.html" target="_blank">http://redisdoc.com/geo/geoadd.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param nxXx
	 *        {@link NxXx}
	 * @param member
	 * 		名字
	 * @param geo
	 * 		经纬度对象
	 *
	 * @return 新添加到键里面的空间元素数量，不包括那些已经存在但是被更新的元素
	 */
	@SuppressWarnings({"unchecked"})
	default Long geoAdd(final byte[] key, final NxXx nxXx, final byte[] member, final Geo geo) {
		return geoAdd(key, nxXx, new KeyValue<>(member, geo));
	}

	@SuppressWarnings({"unchecked"})
	@Override
	default Long geoAdd(final String key, final NxXx nxXx, final boolean ch,
	                    final KeyValue<String, Geo>... memberCoordinates) {
		return doExecute((cmd)->cmd.geoAdd(KeyUtils.rawKey(this, key), nxXx, ch, memberCoordinates));
	}

	@SuppressWarnings({"unchecked"})
	@Override
	default Long geoAdd(final byte[] key, final NxXx nxXx, final boolean ch,
	                    final KeyValue<byte[], Geo>... memberCoordinates) {
		return doExecute((cmd)->cmd.geoAdd(KeyUtils.rawKey(this, key), nxXx, ch, memberCoordinates));
	}

	/**
	 * 将给定的空间元素（经度、纬度、名字）添加到指定的键里面
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/geo/geoadd.html" target="_blank">http://redisdoc.com/geo/geoadd.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param nxXx
	 *        {@link NxXx}
	 * @param ch
	 * 		CH
	 * @param member
	 * 		名字
	 * @param longitude
	 * 		经度
	 * @param latitude
	 * 		纬度
	 *
	 * @return 新添加到键里面的空间元素数量，不包括那些已经存在但是被更新的元素
	 */
	default Long geoAdd(final String key, final NxXx nxXx, final boolean ch, final String member,
	                    final double longitude, final double latitude) {
		return geoAdd(key, nxXx, ch, member, new Geo(longitude, latitude));
	}

	/**
	 * 将给定的空间元素（经度、纬度、名字）添加到指定的键里面
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/geo/geoadd.html" target="_blank">http://redisdoc.com/geo/geoadd.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param nxXx
	 *        {@link NxXx}
	 * @param ch
	 * 		CH
	 * @param member
	 * 		名字
	 * @param longitude
	 * 		经度
	 * @param latitude
	 * 		纬度
	 *
	 * @return 新添加到键里面的空间元素数量，不包括那些已经存在但是被更新的元素
	 */
	default Long geoAdd(final byte[] key, final NxXx nxXx, final boolean ch, final byte[] member,
	                    final double longitude, final double latitude) {
		return geoAdd(key, nxXx, ch, member, new Geo(longitude, latitude));
	}

	/**
	 * 将给定的空间元素（经度、纬度、名字）添加到指定的键里面
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/geo/geoadd.html" target="_blank">http://redisdoc.com/geo/geoadd.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param nxXx
	 *        {@link NxXx}
	 * @param ch
	 * 		CH
	 * @param member
	 * 		名字
	 * @param geo
	 * 		经纬度对象
	 *
	 * @return 新添加到键里面的空间元素数量，不包括那些已经存在但是被更新的元素
	 */
	@SuppressWarnings({"unchecked"})
	default Long geoAdd(final String key, final NxXx nxXx, final boolean ch, final String member, final Geo geo) {
		return geoAdd(key, nxXx, ch, new KeyValue<>(member, geo));
	}

	/**
	 * 将给定的空间元素（经度、纬度、名字）添加到指定的键里面
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/geo/geoadd.html" target="_blank">http://redisdoc.com/geo/geoadd.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param nxXx
	 *        {@link NxXx}
	 * @param ch
	 * 		CH
	 * @param member
	 * 		名字
	 * @param geo
	 * 		经纬度对象
	 *
	 * @return 新添加到键里面的空间元素数量，不包括那些已经存在但是被更新的元素
	 */
	@SuppressWarnings({"unchecked"})
	default Long geoAdd(final byte[] key, final NxXx nxXx, final boolean ch, final byte[] member, final Geo geo) {
		return geoAdd(key, nxXx, ch, new KeyValue<>(member, geo));
	}

	@SuppressWarnings({"unchecked"})
	@Override
	default Long geoAdd(final String key, final boolean ch, final KeyValue<String, Geo>... memberCoordinates) {
		return doExecute((cmd)->cmd.geoAdd(KeyUtils.rawKey(this, key), ch, memberCoordinates));
	}

	@SuppressWarnings({"unchecked"})
	@Override
	default Long geoAdd(final byte[] key, final boolean ch, final KeyValue<byte[], Geo>... memberCoordinates) {
		return doExecute((cmd)->cmd.geoAdd(KeyUtils.rawKey(this, key), ch, memberCoordinates));
	}

	/**
	 * 将给定的空间元素（经度、纬度、名字）添加到指定的键里面
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/geo/geoadd.html" target="_blank">http://redisdoc.com/geo/geoadd.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param ch
	 * 		CH
	 * @param member
	 * 		名字
	 * @param longitude
	 * 		经度
	 * @param latitude
	 * 		纬度
	 *
	 * @return 新添加到键里面的空间元素数量，不包括那些已经存在但是被更新的元素
	 */
	default Long geoAdd(final String key, final boolean ch, final String member, final double longitude,
	                    final double latitude) {
		return geoAdd(key, ch, member, new Geo(longitude, latitude));
	}

	/**
	 * 将给定的空间元素（经度、纬度、名字）添加到指定的键里面
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/geo/geoadd.html" target="_blank">http://redisdoc.com/geo/geoadd.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param ch
	 * 		CH
	 * @param member
	 * 		名字
	 * @param longitude
	 * 		经度
	 * @param latitude
	 * 		纬度
	 *
	 * @return 新添加到键里面的空间元素数量，不包括那些已经存在但是被更新的元素
	 */
	default Long geoAdd(final byte[] key, final boolean ch, final byte[] member, final double longitude,
	                    final double latitude) {
		return geoAdd(key, ch, member, new Geo(longitude, latitude));
	}

	/**
	 * 将给定的空间元素（经度、纬度、名字）添加到指定的键里面
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/geo/geoadd.html" target="_blank">http://redisdoc.com/geo/geoadd.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param ch
	 * 		CH
	 * @param member
	 * 		名字
	 * @param geo
	 * 		经纬度对象
	 *
	 * @return 新添加到键里面的空间元素数量，不包括那些已经存在但是被更新的元素
	 */
	@SuppressWarnings({"unchecked"})
	default Long geoAdd(final String key, final boolean ch, final String member, final Geo geo) {
		return geoAdd(key, ch, new KeyValue<>(member, geo));
	}

	/**
	 * 将给定的空间元素（经度、纬度、名字）添加到指定的键里面
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/geo/geoadd.html" target="_blank">http://redisdoc.com/geo/geoadd.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param ch
	 * 		CH
	 * @param member
	 * 		名字
	 * @param geo
	 * 		经纬度对象
	 *
	 * @return 新添加到键里面的空间元素数量，不包括那些已经存在但是被更新的元素
	 */
	@SuppressWarnings({"unchecked"})
	default Long geoAdd(final byte[] key, final boolean ch, final byte[] member, final Geo geo) {
		return geoAdd(key, ch, new KeyValue<>(member, geo));
	}

	@Override
	default Double geoDist(final String key, final String member1, final String member2) {
		return doExecute((cmd)->cmd.geoDist(KeyUtils.rawKey(this, key), member1, member2));
	}

	@Override
	default Double geoDist(final byte[] key, final byte[] member1, final byte[] member2) {
		return doExecute((cmd)->cmd.geoDist(KeyUtils.rawKey(this, key), member1, member2));
	}

	@Override
	default Double geoDist(final String key, final String member1, final String member2, final GeoUnit unit) {
		return doExecute((cmd)->cmd.geoDist(KeyUtils.rawKey(this, key), member1, member2, unit));
	}

	@Override
	default Double geoDist(final byte[] key, final byte[] member1, final byte[] member2, final GeoUnit unit) {
		return doExecute((cmd)->cmd.geoDist(KeyUtils.rawKey(this, key), member1, member2, unit));
	}

	@Override
	default List<String> geoHash(final String key, final String... members) {
		return doExecute((cmd)->cmd.geoHash(KeyUtils.rawKey(this, key), members));
	}

	@Override
	default List<byte[]> geoHash(final byte[] key, final byte[]... members) {
		return doExecute((cmd)->cmd.geoHash(KeyUtils.rawKey(this, key), members));
	}

	@Override
	default List<Geo> geoPos(final String key, final String... members) {
		return doExecute((cmd)->cmd.geoPos(KeyUtils.rawKey(this, key), members));
	}

	@Override
	default List<Geo> geoPos(final byte[] key, final byte[]... members) {
		return doExecute((cmd)->cmd.geoPos(KeyUtils.rawKey(this, key), members));
	}

	@Override
	default List<GeoRadius> geoRadius(final String key, final double longitude, final double latitude,
	                                  final double radius, final GeoUnit unit) {
		return doExecute((cmd)->cmd.geoRadius(KeyUtils.rawKey(this, key), longitude, latitude, radius, unit));
	}

	@Override
	default List<GeoRadius> geoRadius(final byte[] key, final double longitude, final double latitude,
	                                  final double radius, final GeoUnit unit) {
		return doExecute((cmd)->cmd.geoRadius(KeyUtils.rawKey(this, key), longitude, latitude, radius, unit));
	}

	/**
	 * 以给定的经纬度为中心，返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/georadius/" target="_blank">https://redis.io/docs/latest/commands/georadius/</a></p>
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
	default List<GeoRadius> geoRadius(final String key, final Geo geo, final double radius, final GeoUnit unit) {
		return geoRadius(key, geo.getLongitude(), geo.getLatitude(), radius, unit);
	}

	/**
	 * 以给定的经纬度为中心，返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/georadius/" target="_blank">https://redis.io/docs/latest/commands/georadius/</a></p>
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

	@Override
	default List<GeoRadius> geoRadius(final String key, final double longitude, final double latitude,
	                                  final double radius, final GeoUnit unit, final GeoRadiusArgument argument) {
		return doExecute((cmd)->cmd
				.geoRadius(KeyUtils.rawKey(this, key), longitude, latitude, radius, unit, argument));
	}

	@Override
	default List<GeoRadius> geoRadius(final byte[] key, final double longitude, final double latitude,
	                                  final double radius, final GeoUnit unit, final GeoRadiusArgument argument) {
		return doExecute((cmd)->cmd
				.geoRadius(KeyUtils.rawKey(this, key), longitude, latitude, radius, unit, argument));
	}

	/**
	 * 以给定的经纬度为中心，返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/georadius/" target="_blank">https://redis.io/docs/latest/commands/georadius/</a></p>
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
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/georadius/" target="_blank">https://redis.io/docs/latest/commands/georadius/</a></p>
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
	default List<GeoRadius> geoRadius(final String key, final double longitude, final double latitude,
	                                  final double radius, final GeoUnit unit, final GeoRadiusArgument argument,
	                                  final int count) {
		return doExecute((cmd)->cmd
				.geoRadius(KeyUtils.rawKey(this, key), longitude, latitude, radius, unit, argument, count));
	}

	@Override
	default List<GeoRadius> geoRadius(final byte[] key, final double longitude, final double latitude,
	                                  final double radius, final GeoUnit unit, final GeoRadiusArgument argument,
	                                  final int count) {
		return doExecute((cmd)->cmd
				.geoRadius(KeyUtils.rawKey(this, key), longitude, latitude, radius, unit, argument, count));
	}

	/**
	 * 以给定的经纬度为中心，返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/georadius/" target="_blank">https://redis.io/docs/latest/commands/georadius/</a></p>
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
	 * 		参数
	 * @param count
	 * 		返回数量
	 *
	 * @return 位置元素
	 */
	default List<GeoRadius> geoRadius(final String key, final Geo geo, final double radius, final GeoUnit unit,
	                                  final GeoRadiusArgument argument, final int count) {
		return geoRadius(key, geo.getLongitude(), geo.getLatitude(), radius, unit, argument, count);
	}

	/**
	 * 以给定的经纬度为中心，返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/georadius/" target="_blank">https://redis.io/docs/latest/commands/georadius/</a></p>
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
	 * 		参数
	 * @param count
	 * 		返回数量
	 *
	 * @return 位置元素
	 */
	default List<GeoRadius> geoRadius(final byte[] key, final Geo geo, final double radius, final GeoUnit unit,
	                                  final GeoRadiusArgument argument, final int count) {
		return geoRadius(key, geo.getLongitude(), geo.getLatitude(), radius, unit, argument, count);
	}

	@Override
	default List<GeoRadius> geoRadius(final String key, final double longitude, final double latitude,
	                                  final double radius, final GeoUnit unit, final GeoRadiusArgument argument,
	                                  final int count, final boolean any) {
		return doExecute((cmd)->cmd
				.geoRadius(KeyUtils.rawKey(this, key), longitude, latitude, radius, unit, argument, count, any));
	}

	@Override
	default List<GeoRadius> geoRadius(final byte[] key, final double longitude, final double latitude,
	                                  final double radius, final GeoUnit unit, final GeoRadiusArgument argument,
	                                  final int count, final boolean any) {
		return doExecute((cmd)->cmd
				.geoRadius(KeyUtils.rawKey(this, key), longitude, latitude, radius, unit, argument, count, any));
	}

	/**
	 * 以给定的经纬度为中心，返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/georadius/" target="_blank">https://redis.io/docs/latest/commands/georadius/</a></p>
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
	 * 		参数
	 * @param count
	 * 		返回数量
	 * @param any
	 * 		ANY
	 *
	 * @return 位置元素
	 */
	default List<GeoRadius> geoRadius(final String key, final Geo geo, final double radius, final GeoUnit unit,
	                                  final GeoRadiusArgument argument, final int count, final boolean any) {
		return geoRadius(key, geo.getLongitude(), geo.getLatitude(), radius, unit, argument, count, any);
	}

	/**
	 * 以给定的经纬度为中心，返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/georadius/" target="_blank">https://redis.io/docs/latest/commands/georadius/</a></p>
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
	 * 		参数
	 * @param count
	 * 		返回数量
	 * @param any
	 * 		ANY
	 *
	 * @return 位置元素
	 */
	default List<GeoRadius> geoRadius(final byte[] key, final Geo geo, final double radius, final GeoUnit unit,
	                                  final GeoRadiusArgument argument, final int count, final boolean any) {
		return geoRadius(key, geo.getLongitude(), geo.getLatitude(), radius, unit, argument, count, any);
	}

	@Override
	default List<GeoRadius> geoRadius(final String key, final double longitude, final double latitude,
	                                  final double radius, final GeoUnit unit, final int count) {
		return doExecute((cmd)->cmd
				.geoRadius(KeyUtils.rawKey(this, key), longitude, latitude, radius, unit, count));
	}

	@Override
	default List<GeoRadius> geoRadius(final byte[] key, final double longitude, final double latitude,
	                                  final double radius, final GeoUnit unit, final int count) {
		return doExecute((cmd)->cmd
				.geoRadius(KeyUtils.rawKey(this, key), longitude, latitude, radius, unit, count));
	}

	/**
	 * 以给定的经纬度为中心，返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/georadius/" target="_blank">https://redis.io/docs/latest/commands/georadius/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param geo
	 * 		经纬度
	 * @param radius
	 * 		范围
	 * @param unit
	 * 		距离单位
	 * @param count
	 * 		返回数量
	 *
	 * @return 位置元素
	 */
	default List<GeoRadius> geoRadius(final String key, final Geo geo, final double radius, final GeoUnit unit,
	                                  final int count) {
		return geoRadius(key, geo.getLongitude(), geo.getLatitude(), radius, unit, count);
	}

	/**
	 * 以给定的经纬度为中心，返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/georadius/" target="_blank">https://redis.io/docs/latest/commands/georadius/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param geo
	 * 		经纬度
	 * @param radius
	 * 		范围
	 * @param unit
	 * 		距离单位
	 * @param count
	 * 		返回数量
	 *
	 * @return 位置元素
	 */
	default List<GeoRadius> geoRadius(final byte[] key, final Geo geo, final double radius, final GeoUnit unit,
	                                  final int count) {
		return geoRadius(key, geo.getLongitude(), geo.getLatitude(), radius, unit, count);
	}

	@Override
	default List<GeoRadius> geoRadius(final String key, final double longitude, final double latitude,
	                                  final double radius, final GeoUnit unit, final int count, final boolean any) {
		return doExecute((cmd)->cmd
				.geoRadius(KeyUtils.rawKey(this, key), longitude, latitude, radius, unit, count, any));
	}

	@Override
	default List<GeoRadius> geoRadius(final byte[] key, final double longitude, final double latitude,
	                                  final double radius, final GeoUnit unit, final int count, final boolean any) {
		return doExecute((cmd)->cmd
				.geoRadius(KeyUtils.rawKey(this, key), longitude, latitude, radius, unit, count, any));
	}

	/**
	 * 以给定的经纬度为中心，返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/georadius/" target="_blank">https://redis.io/docs/latest/commands/georadius/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param geo
	 * 		经纬度
	 * @param radius
	 * 		范围
	 * @param unit
	 * 		距离单位
	 * @param count
	 * 		返回数量
	 * @param any
	 * 		ANY
	 *
	 * @return 位置元素
	 */
	default List<GeoRadius> geoRadius(final String key, final Geo geo, final double radius, final GeoUnit unit,
	                                  final int count, final boolean any) {
		return geoRadius(key, geo.getLongitude(), geo.getLatitude(), radius, unit, count, any);
	}

	/**
	 * 以给定的经纬度为中心，返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/georadius/" target="_blank">https://redis.io/docs/latest/commands/georadius/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param geo
	 * 		经纬度
	 * @param radius
	 * 		范围
	 * @param unit
	 * 		距离单位
	 * @param count
	 * 		返回数量
	 * @param any
	 * 		ANY
	 *
	 * @return 位置元素
	 */
	default List<GeoRadius> geoRadius(final byte[] key, final Geo geo, final double radius, final GeoUnit unit,
	                                  final int count, final boolean any) {
		return geoRadius(key, geo.getLongitude(), geo.getLatitude(), radius, unit, count, any);
	}

	@Override
	default Long geoRadius(final String key, final String destKey, final GeoStoreOption storeOption,
	                       final double longitude, final double latitude, final double radius, final GeoUnit unit) {
		return doExecute((cmd)->cmd.geoRadius(KeyUtils.rawKey(this, key),
				KeyUtils.rawKey(this, destKey), storeOption, longitude, latitude, radius, unit));
	}

	@Override
	default Long geoRadius(final byte[] key, final byte[] destKey, final GeoStoreOption storeOption,
	                       final double longitude, final double latitude, final double radius, final GeoUnit unit) {
		return doExecute((cmd)->cmd.geoRadius(KeyUtils.rawKey(this, key),
				KeyUtils.rawKey(this, destKey), storeOption, longitude, latitude, radius, unit));
	}

	/**
	 * 以给定的经纬度为中心，返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素，并存储到目标 destKey 中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/georadius/" target="_blank">https://redis.io/docs/latest/commands/georadius/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param destKey
	 * 		目标 Key
	 * @param storeOption
	 * 		存储选项
	 * @param geo
	 * 		经纬度对象
	 * @param radius
	 * 		范围
	 * @param unit
	 * 		距离单位
	 *
	 * @return 位置元素
	 */
	default Long geoRadius(final String key, final String destKey, final GeoStoreOption storeOption, final Geo geo,
	                       final double radius, final GeoUnit unit) {
		return geoRadius(key, destKey, storeOption, geo.getLongitude(), geo.getLatitude(), radius, unit);
	}

	/**
	 * 以给定的经纬度为中心，返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素，并存储到目标 destKey 中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/georadius/" target="_blank">https://redis.io/docs/latest/commands/georadius/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param destKey
	 * 		目标 Key
	 * @param storeOption
	 * 		存储选项
	 * @param geo
	 * 		经纬度对象
	 * @param radius
	 * 		范围
	 * @param unit
	 * 		距离单位
	 *
	 * @return 位置元素
	 */
	default Long geoRadius(final byte[] key, final byte[] destKey, final GeoStoreOption storeOption, final Geo geo,
	                       final double radius, final GeoUnit unit) {
		return geoRadius(key, destKey, storeOption, geo.getLongitude(), geo.getLatitude(), radius, unit);
	}

	@Override
	default Long geoRadius(final String key, final String destKey, final GeoStoreOption storeOption,
	                       final double longitude, final double latitude, final double radius, final GeoUnit unit,
	                       final GeoRadiusArgument argument) {
		return doExecute((cmd)->cmd.geoRadius(KeyUtils.rawKey(this, key),
				KeyUtils.rawKey(this, destKey), storeOption, longitude, latitude, radius, unit, argument));
	}

	@Override
	default Long geoRadius(final byte[] key, final byte[] destKey, final GeoStoreOption storeOption,
	                       final double longitude, final double latitude, final double radius, final GeoUnit unit,
	                       final GeoRadiusArgument argument) {
		return doExecute((cmd)->cmd.geoRadius(KeyUtils.rawKey(this, key),
				KeyUtils.rawKey(this, destKey), storeOption, longitude, latitude, radius, unit, argument));
	}

	/**
	 * 以给定的经纬度为中心，返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素，并存储到目标 destKey 中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/georadius/" target="_blank">https://redis.io/docs/latest/commands/georadius/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param destKey
	 * 		目标 Key
	 * @param storeOption
	 * 		存储选项
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
	default Long geoRadius(final String key, final String destKey, final GeoStoreOption storeOption, final Geo geo,
	                       final double radius, final GeoUnit unit, final GeoRadiusArgument argument) {
		return geoRadius(key, destKey, storeOption, geo.getLongitude(), geo.getLatitude(), radius, unit, argument);
	}

	/**
	 * 以给定的经纬度为中心，返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素，并存储到目标 destKey 中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/georadius/" target="_blank">https://redis.io/docs/latest/commands/georadius/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param destKey
	 * 		目标 Key
	 * @param storeOption
	 * 		存储选项
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
	default Long geoRadius(final byte[] key, final byte[] destKey, final GeoStoreOption storeOption, final Geo geo,
	                       final double radius, final GeoUnit unit, final GeoRadiusArgument argument) {
		return geoRadius(key, destKey, storeOption, geo.getLongitude(), geo.getLatitude(), radius, unit, argument);
	}

	@Override
	default Long geoRadius(final String key, final String destKey, final GeoStoreOption storeOption,
	                       final double longitude, final double latitude, final double radius, final GeoUnit unit,
	                       final GeoRadiusArgument argument, final int count) {
		return doExecute((cmd)->cmd.geoRadius(KeyUtils.rawKey(this, key), KeyUtils.rawKey(this, destKey),
				storeOption, longitude, latitude, radius, unit, argument, count));
	}

	@Override
	default Long geoRadius(final byte[] key, final byte[] destKey, final GeoStoreOption storeOption,
	                       final double longitude, final double latitude, final double radius, final GeoUnit unit,
	                       final GeoRadiusArgument argument, final int count) {
		return doExecute((cmd)->cmd.geoRadius(KeyUtils.rawKey(this, key), KeyUtils.rawKey(this, destKey),
				storeOption, longitude, latitude, radius, unit, argument, count));
	}

	/**
	 * 以给定的经纬度为中心，返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素，并存储到目标 destKey 中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/georadius/" target="_blank">https://redis.io/docs/latest/commands/georadius/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param destKey
	 * 		目标 Key
	 * @param storeOption
	 * 		存储选项
	 * @param geo
	 * 		经纬度
	 * @param radius
	 * 		范围
	 * @param unit
	 * 		距离单位
	 * @param argument
	 * 		参数
	 * @param count
	 * 		返回数量
	 *
	 * @return 位置元素
	 */
	default Long geoRadius(final String key, final String destKey, final GeoStoreOption storeOption, final Geo geo,
	                       final double radius, final GeoUnit unit, final GeoRadiusArgument argument, final int count) {
		return geoRadius(key, destKey, storeOption, geo.getLongitude(), geo.getLatitude(), radius, unit, argument,
				count);
	}

	/**
	 * 以给定的经纬度为中心，返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素，并存储到目标 destKey 中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/georadius/" target="_blank">https://redis.io/docs/latest/commands/georadius/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param destKey
	 * 		目标 Key
	 * @param storeOption
	 * 		存储选项
	 * @param geo
	 * 		经纬度
	 * @param radius
	 * 		范围
	 * @param unit
	 * 		距离单位
	 * @param argument
	 * 		参数
	 * @param count
	 * 		返回数量
	 *
	 * @return 位置元素
	 */
	default Long geoRadius(final byte[] key, final byte[] destKey, final GeoStoreOption storeOption, final Geo geo,
	                       final double radius, final GeoUnit unit, final GeoRadiusArgument argument, final int count) {
		return geoRadius(key, destKey, storeOption, geo.getLongitude(), geo.getLatitude(), radius, unit, argument,
				count);
	}

	@Override
	default Long geoRadius(final String key, final String destKey, final GeoStoreOption storeOption,
	                       final double longitude, final double latitude, final double radius, final GeoUnit unit,
	                       final GeoRadiusArgument argument, final int count, final boolean any) {
		return doExecute((cmd)->cmd.geoRadius(KeyUtils.rawKey(this, key),
				KeyUtils.rawKey(this, destKey), storeOption, longitude, latitude, radius, unit, argument, count, any));
	}

	@Override
	default Long geoRadius(final byte[] key, final byte[] destKey, final GeoStoreOption storeOption,
	                       final double longitude, final double latitude, final double radius, final GeoUnit unit,
	                       final GeoRadiusArgument argument, final int count, final boolean any) {
		return doExecute((cmd)->cmd.geoRadius(KeyUtils.rawKey(this, key),
				KeyUtils.rawKey(this, destKey), storeOption, longitude, latitude, radius, unit, argument, count, any));
	}

	/**
	 * 以给定的经纬度为中心，返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素，并存储到目标 destKey 中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/georadius/" target="_blank">https://redis.io/docs/latest/commands/georadius/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param destKey
	 * 		目标 Key
	 * @param storeOption
	 * 		存储选项
	 * @param geo
	 * 		经纬度
	 * @param radius
	 * 		范围
	 * @param unit
	 * 		距离单位
	 * @param argument
	 * 		参数
	 * @param count
	 * 		返回数量
	 * @param any
	 * 		ANY
	 *
	 * @return 位置元素
	 */
	default Long geoRadius(final String key, final String destKey, final GeoStoreOption storeOption, final Geo geo,
	                       final double radius, final GeoUnit unit, final GeoRadiusArgument argument, final int count,
	                       final boolean any) {
		return geoRadius(key, destKey, storeOption, geo.getLongitude(), geo.getLatitude(), radius, unit, argument,
				count, any);
	}

	/**
	 * 以给定的经纬度为中心，返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素，并存储到目标 destKey 中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/georadius/" target="_blank">https://redis.io/docs/latest/commands/georadius/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param destKey
	 * 		目标 Key
	 * @param storeOption
	 * 		存储选项
	 * @param geo
	 * 		经纬度
	 * @param radius
	 * 		范围
	 * @param unit
	 * 		距离单位
	 * @param argument
	 * 		参数
	 * @param count
	 * 		返回数量
	 * @param any
	 * 		ANY
	 *
	 * @return 位置元素
	 */
	default Long geoRadius(final byte[] key, final byte[] destKey, final GeoStoreOption storeOption, final Geo geo,
	                       final double radius, final GeoUnit unit, final GeoRadiusArgument argument, final int count,
	                       final boolean any) {
		return geoRadius(key, destKey, storeOption, geo.getLongitude(), geo.getLatitude(), radius, unit, argument,
				count, any);
	}

	@Override
	default Long geoRadius(final String key, final String destKey, final GeoStoreOption storeOption,
	                       final double longitude, final double latitude, final double radius, final GeoUnit unit,
	                       final int count) {
		return doExecute((cmd)->cmd.geoRadius(KeyUtils.rawKey(this, key),
				KeyUtils.rawKey(this, destKey), storeOption, longitude, latitude, radius, unit, count));
	}

	@Override
	default Long geoRadius(final byte[] key, final byte[] destKey, final GeoStoreOption storeOption,
	                       final double longitude, final double latitude, final double radius, final GeoUnit unit,
	                       final int count) {
		return doExecute((cmd)->cmd.geoRadius(KeyUtils.rawKey(this, key),
				KeyUtils.rawKey(this, destKey), storeOption, longitude, latitude, radius, unit, count));
	}

	/**
	 * 以给定的经纬度为中心，返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素，并存储到目标 destKey 中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/georadius/" target="_blank">https://redis.io/docs/latest/commands/georadius/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param destKey
	 * 		目标 Key
	 * @param storeOption
	 * 		存储选项
	 * @param geo
	 * 		经纬度
	 * @param radius
	 * 		范围
	 * @param unit
	 * 		距离单位
	 * @param count
	 * 		返回数量
	 *
	 * @return 位置元素
	 */
	default Long geoRadius(final String key, final String destKey, final GeoStoreOption storeOption, final Geo geo,
	                       final double radius, final GeoUnit unit, final int count) {
		return geoRadius(key, destKey, storeOption, geo.getLongitude(), geo.getLatitude(), radius, unit, count);
	}

	/**
	 * 以给定的经纬度为中心，返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素，并存储到目标 destKey 中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/georadius/" target="_blank">https://redis.io/docs/latest/commands/georadius/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param destKey
	 * 		目标 Key
	 * @param storeOption
	 * 		存储选项
	 * @param geo
	 * 		经纬度
	 * @param radius
	 * 		范围
	 * @param unit
	 * 		距离单位
	 * @param count
	 * 		返回数量
	 *
	 * @return 位置元素
	 */
	default Long geoRadius(final byte[] key, final byte[] destKey, final GeoStoreOption storeOption, final Geo geo,
	                       final double radius, final GeoUnit unit, final int count) {
		return geoRadius(key, destKey, storeOption, geo.getLongitude(), geo.getLatitude(), radius, unit, count);
	}

	@Override
	default Long geoRadius(final String key, final String destKey, final GeoStoreOption storeOption,
	                       final double longitude, final double latitude, final double radius, final GeoUnit unit,
	                       final int count, final boolean any) {
		return doExecute((cmd)->cmd.geoRadius(KeyUtils.rawKey(this, key),
				KeyUtils.rawKey(this, destKey), storeOption, longitude, latitude, radius, unit, count, any));
	}

	@Override
	default Long geoRadius(final byte[] key, final byte[] destKey, final GeoStoreOption storeOption,
	                       final double longitude, final double latitude, final double radius, final GeoUnit unit,
	                       final int count, final boolean any) {
		return doExecute((cmd)->cmd.geoRadius(KeyUtils.rawKey(this, key),
				KeyUtils.rawKey(this, destKey), storeOption, longitude, latitude, radius, unit, count, any));
	}

	/**
	 * 以给定的经纬度为中心，返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素，并存储到目标 destKey 中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/georadius/" target="_blank">https://redis.io/docs/latest/commands/georadius/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param destKey
	 * 		目标 Key
	 * @param storeOption
	 * 		存储选项
	 * @param geo
	 * 		经纬度
	 * @param radius
	 * 		范围
	 * @param unit
	 * 		距离单位
	 * @param count
	 * 		返回数量
	 * @param any
	 * 		ANY
	 *
	 * @return 位置元素
	 */
	default Long geoRadius(final String key, final String destKey, final GeoStoreOption storeOption, final Geo geo,
	                       final double radius, final GeoUnit unit, final int count, final boolean any) {
		return geoRadius(key, destKey, storeOption, geo.getLongitude(), geo.getLatitude(), radius, unit, count, any);
	}

	/**
	 * 以给定的经纬度为中心，返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素，并存储到目标 destKey 中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/georadius/" target="_blank">https://redis.io/docs/latest/commands/georadius/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param destKey
	 * 		目标 Key
	 * @param storeOption
	 * 		存储选项
	 * @param geo
	 * 		经纬度
	 * @param radius
	 * 		范围
	 * @param unit
	 * 		距离单位
	 * @param count
	 * 		返回数量
	 * @param any
	 * 		ANY
	 *
	 * @return 位置元素
	 */
	default Long geoRadius(final byte[] key, final byte[] destKey, final GeoStoreOption storeOption, final Geo geo,
	                       final double radius, final GeoUnit unit, final int count, final boolean any) {
		return geoRadius(key, destKey, storeOption, geo.getLongitude(), geo.getLatitude(), radius, unit, count, any);
	}

	@Override
	default List<GeoRadius> geoRadiusRo(final String key, final double longitude, final double latitude,
	                                    final double radius, final GeoUnit unit) {
		return doExecute((cmd)->cmd.geoRadiusRo(KeyUtils.rawKey(this, key), longitude, latitude, radius, unit));
	}

	@Override
	default List<GeoRadius> geoRadiusRo(final byte[] key, final double longitude, final double latitude,
	                                    final double radius, final GeoUnit unit) {
		return doExecute((cmd)->cmd.geoRadiusRo(KeyUtils.rawKey(this, key), longitude, latitude, radius, unit));
	}

	/**
	 * 以给定的经纬度为中心，返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/georadius_ro/" target="_blank">https://redis.io/docs/latest/commands/georadius_ro/</a></p>
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
	default List<GeoRadius> geoRadiusRo(final String key, final Geo geo, final double radius, final GeoUnit unit) {
		return geoRadiusRo(key, geo.getLongitude(), geo.getLatitude(), radius, unit);
	}

	/**
	 * 以给定的经纬度为中心，返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/georadius_ro/" target="_blank">https://redis.io/docs/latest/commands/georadius_ro/</a></p>
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

	@Override
	default List<GeoRadius> geoRadiusRo(final String key, final double longitude, final double latitude,
	                                    final double radius, final GeoUnit unit, final GeoRadiusArgument argument) {
		return doExecute((cmd)->cmd
				.geoRadiusRo(KeyUtils.rawKey(this, key), longitude, latitude, radius, unit, argument));
	}

	@Override
	default List<GeoRadius> geoRadiusRo(final byte[] key, final double longitude, final double latitude,
	                                    final double radius, final GeoUnit unit, final GeoRadiusArgument argument) {
		return doExecute((cmd)->cmd
				.geoRadiusRo(KeyUtils.rawKey(this, key), longitude, latitude, radius, unit, argument));
	}

	/**
	 * 以给定的经纬度为中心，返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/georadius_ro/" target="_blank">https://redis.io/docs/latest/commands/georadius_ro/</a></p>
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
	 * 以给定的经纬度为中心，返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/georadius_ro/" target="_blank">https://redis.io/docs/latest/commands/georadius_ro/</a></p>
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
	default List<GeoRadius> geoRadiusRo(final String key, final double longitude, final double latitude,
	                                    final double radius, final GeoUnit unit, final GeoRadiusArgument argument,
	                                    final int count) {
		return doExecute((cmd)->cmd
				.geoRadiusRo(KeyUtils.rawKey(this, key), longitude, latitude, radius, unit, argument, count));
	}

	@Override
	default List<GeoRadius> geoRadiusRo(final byte[] key, final double longitude, final double latitude,
	                                    final double radius, final GeoUnit unit, final GeoRadiusArgument argument,
	                                    final int count) {
		return doExecute((cmd)->cmd
				.geoRadiusRo(KeyUtils.rawKey(this, key), longitude, latitude, radius, unit, argument, count));
	}

	/**
	 * 以给定的经纬度为中心，返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/georadius_ro/" target="_blank">https://redis.io/docs/latest/commands/georadius_ro/</a></p>
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
	 * 		参数
	 * @param count
	 * 		返回数量
	 *
	 * @return 位置元素
	 */
	default List<GeoRadius> geoRadiusRo(final String key, final Geo geo, final double radius, final GeoUnit unit,
	                                    final GeoRadiusArgument argument, final int count) {
		return geoRadiusRo(key, geo.getLongitude(), geo.getLatitude(), radius, unit, argument, count);
	}

	/**
	 * 以给定的经纬度为中心，返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/georadius_ro/" target="_blank">https://redis.io/docs/latest/commands/georadius_ro/</a></p>
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
	 * 		参数
	 * @param count
	 * 		返回数量
	 *
	 * @return 位置元素
	 */
	default List<GeoRadius> geoRadiusRo(final byte[] key, final Geo geo, final double radius, final GeoUnit unit,
	                                    final GeoRadiusArgument argument, final int count) {
		return geoRadiusRo(key, geo.getLongitude(), geo.getLatitude(), radius, unit, argument, count);
	}

	@Override
	default List<GeoRadius> geoRadiusRo(final String key, final double longitude, final double latitude,
	                                    final double radius, final GeoUnit unit, final GeoRadiusArgument argument,
	                                    final int count, final boolean any) {
		return doExecute((cmd)->cmd
				.geoRadiusRo(KeyUtils.rawKey(this, key), longitude, latitude, radius, unit, argument, count, any));
	}

	@Override
	default List<GeoRadius> geoRadiusRo(final byte[] key, final double longitude, final double latitude,
	                                    final double radius, final GeoUnit unit, final GeoRadiusArgument argument,
	                                    final int count, final boolean any) {
		return doExecute((cmd)->cmd
				.geoRadiusRo(KeyUtils.rawKey(this, key), longitude, latitude, radius, unit, argument, count, any));
	}

	/**
	 * 以给定的经纬度为中心，返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/georadius_ro/" target="_blank">https://redis.io/docs/latest/commands/georadius_ro/</a></p>
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
	 * 		参数
	 * @param count
	 * 		返回数量
	 * @param any
	 * 		ANY
	 *
	 * @return 位置元素
	 */
	default List<GeoRadius> geoRadiusRo(final String key, final Geo geo, final double radius, final GeoUnit unit,
	                                    final GeoRadiusArgument argument, final int count, final boolean any) {
		return geoRadiusRo(key, geo.getLongitude(), geo.getLatitude(), radius, unit, argument, count, any);
	}

	/**
	 * 以给定的经纬度为中心，返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/georadius_ro/" target="_blank">https://redis.io/docs/latest/commands/georadius_ro/</a></p>
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
	 * 		参数
	 * @param count
	 * 		返回数量
	 * @param any
	 * 		ANY
	 *
	 * @return 位置元素
	 */
	default List<GeoRadius> geoRadiusRo(final byte[] key, final Geo geo, final double radius, final GeoUnit unit,
	                                    final GeoRadiusArgument argument, final int count, final boolean any) {
		return geoRadiusRo(key, geo.getLongitude(), geo.getLatitude(), radius, unit, argument, count, any);
	}

	@Override
	default List<GeoRadius> geoRadiusRo(final String key, final double longitude, final double latitude,
	                                    final double radius, final GeoUnit unit, final int count) {
		return doExecute((cmd)->cmd
				.geoRadiusRo(KeyUtils.rawKey(this, key), longitude, latitude, radius, unit, count));
	}

	@Override
	default List<GeoRadius> geoRadiusRo(final byte[] key, final double longitude, final double latitude,
	                                    final double radius, final GeoUnit unit, final int count) {
		return doExecute((cmd)->cmd
				.geoRadiusRo(KeyUtils.rawKey(this, key), longitude, latitude, radius, unit, count));
	}

	/**
	 * 以给定的经纬度为中心，返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/georadius_ro/" target="_blank">https://redis.io/docs/latest/commands/georadius_ro/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param geo
	 * 		经纬度
	 * @param radius
	 * 		范围
	 * @param unit
	 * 		距离单位
	 * @param count
	 * 		返回数量
	 *
	 * @return 位置元素
	 */
	default List<GeoRadius> geoRadiusRo(final String key, final Geo geo, final double radius, final GeoUnit unit,
	                                    final int count) {
		return geoRadiusRo(key, geo.getLongitude(), geo.getLatitude(), radius, unit, count);
	}

	/**
	 * 以给定的经纬度为中心，返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/georadius_ro/" target="_blank">https://redis.io/docs/latest/commands/georadius_ro/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param geo
	 * 		经纬度
	 * @param radius
	 * 		范围
	 * @param unit
	 * 		距离单位
	 * @param count
	 * 		返回数量
	 *
	 * @return 位置元素
	 */
	default List<GeoRadius> geoRadiusRo(final byte[] key, final Geo geo, final double radius, final GeoUnit unit,
	                                    final int count) {
		return geoRadiusRo(key, geo.getLongitude(), geo.getLatitude(), radius, unit, count);
	}

	@Override
	default List<GeoRadius> geoRadiusRo(final String key, final double longitude, final double latitude,
	                                    final double radius, final GeoUnit unit, final int count, final boolean any) {
		return doExecute((cmd)->cmd
				.geoRadiusRo(KeyUtils.rawKey(this, key), longitude, latitude, radius, unit, count, any));
	}

	@Override
	default List<GeoRadius> geoRadiusRo(final byte[] key, final double longitude, final double latitude,
	                                    final double radius, final GeoUnit unit, final int count, final boolean any) {
		return doExecute((cmd)->cmd
				.geoRadiusRo(KeyUtils.rawKey(this, key), longitude, latitude, radius, unit, count, any));
	}

	/**
	 * 以给定的经纬度为中心，返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/georadius_ro/" target="_blank">https://redis.io/docs/latest/commands/georadius_ro/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param geo
	 * 		经纬度
	 * @param radius
	 * 		范围
	 * @param unit
	 * 		距离单位
	 * @param count
	 * 		返回数量
	 * @param any
	 * 		ANY
	 *
	 * @return 位置元素
	 */
	default List<GeoRadius> geoRadiusRo(final String key, final Geo geo, final double radius, final GeoUnit unit,
	                                    final int count, final boolean any) {
		return geoRadiusRo(key, geo.getLongitude(), geo.getLatitude(), radius, unit, count, any);
	}

	/**
	 * 以给定的经纬度为中心，返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/georadius_ro/" target="_blank">https://redis.io/docs/latest/commands/georadius_ro/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param geo
	 * 		经纬度
	 * @param radius
	 * 		范围
	 * @param unit
	 * 		距离单位
	 * @param count
	 * 		返回数量
	 * @param any
	 * 		ANY
	 *
	 * @return 位置元素
	 */
	default List<GeoRadius> geoRadiusRo(final byte[] key, final Geo geo, final double radius, final GeoUnit unit,
	                                    final int count, final boolean any) {
		return geoRadiusRo(key, geo.getLongitude(), geo.getLatitude(), radius, unit, count, any);
	}

	@Override
	default List<GeoRadius> geoRadiusByMember(final String key, final String member, final double radius,
	                                          final GeoUnit unit) {
		return doExecute((cmd)->cmd.geoRadiusByMember(KeyUtils.rawKey(this, key), member, radius, unit));
	}

	@Override
	default List<GeoRadius> geoRadiusByMember(final byte[] key, final byte[] member, final double radius,
	                                          final GeoUnit unit) {
		return doExecute((cmd)->cmd.geoRadiusByMember(KeyUtils.rawKey(this, key), member, radius, unit));
	}

	@Override
	default List<GeoRadius> geoRadiusByMember(final String key, final String member, final double radius,
	                                          final GeoUnit unit, final GeoRadiusArgument argument) {
		return doExecute((cmd)->cmd
				.geoRadiusByMember(KeyUtils.rawKey(this, key), member, radius, unit, argument));
	}

	@Override
	default List<GeoRadius> geoRadiusByMember(final byte[] key, final byte[] member, final double radius,
	                                          final GeoUnit unit, final GeoRadiusArgument argument) {
		return doExecute((cmd)->cmd
				.geoRadiusByMember(KeyUtils.rawKey(this, key), member, radius, unit, argument));
	}

	@Override
	default List<GeoRadius> geoRadiusByMember(final String key, final String member, final double radius,
	                                          final GeoUnit unit, final GeoRadiusArgument argument, final int count) {
		return doExecute((cmd)->cmd
				.geoRadiusByMember(KeyUtils.rawKey(this, key), member, radius, unit, argument, count));
	}

	@Override
	default List<GeoRadius> geoRadiusByMember(final byte[] key, final byte[] member, final double radius,
	                                          final GeoUnit unit, final GeoRadiusArgument argument, final int count) {
		return doExecute((cmd)->cmd
				.geoRadiusByMember(KeyUtils.rawKey(this, key), member, radius, unit, argument, count));
	}

	@Override
	default List<GeoRadius> geoRadiusByMember(final String key, final String member, final double radius,
	                                          final GeoUnit unit, final GeoRadiusArgument argument, final int count,
	                                          final boolean any) {
		return doExecute((cmd)->cmd
				.geoRadiusByMember(KeyUtils.rawKey(this, key), member, radius, unit, argument, count, any));
	}

	@Override
	default List<GeoRadius> geoRadiusByMember(final byte[] key, final byte[] member, final double radius,
	                                          final GeoUnit unit, final GeoRadiusArgument argument, final int count,
	                                          final boolean any) {
		return doExecute((cmd)->cmd
				.geoRadiusByMember(KeyUtils.rawKey(this, key), member, radius, unit, argument, count, any));
	}

	@Override
	default List<GeoRadius> geoRadiusByMember(final String key, final String member, final double radius,
	                                          final GeoUnit unit, final int count) {
		return doExecute((cmd)->cmd.geoRadiusByMember(KeyUtils.rawKey(this, key), member, radius, unit, count));
	}

	@Override
	default List<GeoRadius> geoRadiusByMember(final byte[] key, final byte[] member, final double radius,
	                                          final GeoUnit unit, final int count) {
		return doExecute((cmd)->cmd.geoRadiusByMember(KeyUtils.rawKey(this, key), member, radius, unit, count));
	}

	@Override
	default List<GeoRadius> geoRadiusByMember(final String key, final String member, final double radius,
	                                          final GeoUnit unit, final int count, final boolean any) {
		return doExecute((cmd)->cmd
				.geoRadiusByMember(KeyUtils.rawKey(this, key), member, radius, unit, count, any));
	}

	@Override
	default List<GeoRadius> geoRadiusByMember(final byte[] key, final byte[] member, final double radius,
	                                          final GeoUnit unit, final int count, final boolean any) {
		return doExecute((cmd)->cmd
				.geoRadiusByMember(KeyUtils.rawKey(this, key), member, radius, unit, count, any));
	}

	@Override
	default Long geoRadiusByMember(final String key, final String destKey, final GeoStoreOption storeOption,
	                               final String member, final double radius, final GeoUnit unit) {
		return doExecute((cmd)->cmd.geoRadiusByMember(KeyUtils.rawKey(this, key),
				KeyUtils.rawKey(this, destKey), storeOption, member, radius, unit));
	}

	@Override
	default Long geoRadiusByMember(final byte[] key, final byte[] destKey, final GeoStoreOption storeOption,
	                               final byte[] member, final double radius, final GeoUnit unit) {
		return doExecute((cmd)->cmd.geoRadiusByMember(KeyUtils.rawKey(this, key),
				KeyUtils.rawKey(this, destKey), storeOption, member, radius, unit));
	}

	@Override
	default Long geoRadiusByMember(final String key, final String destKey, final GeoStoreOption storeOption,
	                               final String member, final double radius, final GeoUnit unit,
	                               final GeoRadiusArgument argument) {
		return doExecute((cmd)->cmd.geoRadiusByMember(KeyUtils.rawKey(this, key),
				KeyUtils.rawKey(this, destKey), storeOption, member, radius, unit, argument));
	}

	@Override
	default Long geoRadiusByMember(final byte[] key, final byte[] destKey, final GeoStoreOption storeOption,
	                               final byte[] member, final double radius, final GeoUnit unit,
	                               final GeoRadiusArgument argument) {
		return doExecute((cmd)->cmd.geoRadiusByMember(KeyUtils.rawKey(this, key),
				KeyUtils.rawKey(this, destKey), storeOption, member, radius, unit, argument));
	}

	@Override
	default Long geoRadiusByMember(final String key, final String destKey, final GeoStoreOption storeOption,
	                               final String member, final double radius, final GeoUnit unit,
	                               final GeoRadiusArgument argument, final int count) {
		return doExecute((cmd)->cmd.geoRadiusByMember(KeyUtils.rawKey(this, key),
				KeyUtils.rawKey(this, destKey), storeOption, member, radius, unit, argument, count));
	}

	@Override
	default Long geoRadiusByMember(final byte[] key, final byte[] destKey, final GeoStoreOption storeOption,
	                               final byte[] member, final double radius, final GeoUnit unit,
	                               final GeoRadiusArgument argument, final int count) {
		return doExecute((cmd)->cmd.geoRadiusByMember(KeyUtils.rawKey(this, key),
				KeyUtils.rawKey(this, destKey), storeOption, member, radius, unit, argument, count));
	}

	@Override
	default Long geoRadiusByMember(final String key, final String destKey, final GeoStoreOption storeOption,
	                               final String member, final double radius, final GeoUnit unit,
	                               final GeoRadiusArgument argument, final int count, final boolean any) {
		return doExecute((cmd)->cmd.geoRadiusByMember(KeyUtils.rawKey(this, key),
				KeyUtils.rawKey(this, destKey), storeOption, member, radius, unit, argument, count, any));
	}

	@Override
	default Long geoRadiusByMember(final byte[] key, final byte[] destKey, final GeoStoreOption storeOption,
	                               final byte[] member, final double radius, final GeoUnit unit,
	                               final GeoRadiusArgument argument, final int count, final boolean any) {
		return doExecute((cmd)->cmd.geoRadiusByMember(KeyUtils.rawKey(this, key),
				KeyUtils.rawKey(this, destKey), storeOption, member, radius, unit, argument, count, any));
	}

	@Override
	default Long geoRadiusByMember(final String key, final String destKey, final GeoStoreOption storeOption,
	                               final String member, final double radius, final GeoUnit unit, final int count) {
		return doExecute((cmd)->cmd.geoRadiusByMember(KeyUtils.rawKey(this, key),
				KeyUtils.rawKey(this, destKey), storeOption, member, radius, unit, count));
	}

	@Override
	default Long geoRadiusByMember(final byte[] key, final byte[] destKey, final GeoStoreOption storeOption,
	                               final byte[] member, final double radius, final GeoUnit unit, final int count) {
		return doExecute((cmd)->cmd.geoRadiusByMember(KeyUtils.rawKey(this, key),
				KeyUtils.rawKey(this, destKey), storeOption, member, radius, unit, count));
	}

	@Override
	default Long geoRadiusByMember(final String key, final String destKey, final GeoStoreOption storeOption,
	                               final String member, final double radius, final GeoUnit unit, final int count,
	                               final boolean any) {
		return doExecute((cmd)->cmd.geoRadiusByMember(KeyUtils.rawKey(this, key),
				KeyUtils.rawKey(this, destKey), storeOption, member, radius, unit, count, any));
	}

	@Override
	default Long geoRadiusByMember(final byte[] key, final byte[] destKey, final GeoStoreOption storeOption,
	                               final byte[] member, final double radius, final GeoUnit unit, final int count,
	                               final boolean any) {
		return doExecute((cmd)->cmd.geoRadiusByMember(KeyUtils.rawKey(this, key),
				KeyUtils.rawKey(this, destKey), storeOption, member, radius, unit, count, any));
	}

	@Override
	default List<GeoRadius> geoRadiusByMemberRo(final String key, final String member, final double radius,
	                                            final GeoUnit unit) {
		return doExecute((cmd)->cmd.geoRadiusByMemberRo(KeyUtils.rawKey(this, key), member, radius, unit));
	}

	@Override
	default List<GeoRadius> geoRadiusByMemberRo(final byte[] key, final byte[] member, final double radius,
	                                            final GeoUnit unit) {
		return doExecute((cmd)->cmd.geoRadiusByMemberRo(KeyUtils.rawKey(this, key), member, radius, unit));
	}

	@Override
	default List<GeoRadius> geoRadiusByMemberRo(final String key, final String member, final double radius,
	                                            final GeoUnit unit, final GeoRadiusArgument argument) {
		return doExecute((cmd)->cmd.geoRadiusByMemberRo(KeyUtils.rawKey(this, key), member, radius, unit, argument));
	}

	@Override
	default List<GeoRadius> geoRadiusByMemberRo(final byte[] key, final byte[] member, final double radius,
	                                            final GeoUnit unit, final GeoRadiusArgument argument) {
		return doExecute((cmd)->cmd.geoRadiusByMemberRo(KeyUtils.rawKey(this, key), member, radius, unit, argument));
	}

	@Override
	default List<GeoRadius> geoRadiusByMemberRo(final String key, final String member, final double radius,
	                                            final GeoUnit unit, final GeoRadiusArgument argument, final int count) {
		return doExecute((cmd)->cmd
				.geoRadiusByMemberRo(KeyUtils.rawKey(this, key), member, radius, unit, argument, count));
	}

	@Override
	default List<GeoRadius> geoRadiusByMemberRo(final byte[] key, final byte[] member, final double radius,
	                                            final GeoUnit unit, final GeoRadiusArgument argument, final int count) {
		return doExecute((cmd)->cmd
				.geoRadiusByMemberRo(KeyUtils.rawKey(this, key), member, radius, unit, argument, count));
	}

	@Override
	default List<GeoRadius> geoRadiusByMemberRo(final String key, final String member, final double radius,
	                                            final GeoUnit unit, final GeoRadiusArgument argument, final int count,
	                                            final boolean any) {
		return doExecute((cmd)->cmd
				.geoRadiusByMemberRo(KeyUtils.rawKey(this, key), member, radius, unit, argument, count, any));
	}

	@Override
	default List<GeoRadius> geoRadiusByMemberRo(final byte[] key, final byte[] member, final double radius,
	                                            final GeoUnit unit, final GeoRadiusArgument argument, final int count,
	                                            final boolean any) {
		return doExecute((cmd)->cmd
				.geoRadiusByMemberRo(KeyUtils.rawKey(this, key), member, radius, unit, argument, count, any));
	}

	@Override
	default List<GeoRadius> geoRadiusByMemberRo(final String key, final String member, final double radius,
	                                            final GeoUnit unit, final int count) {
		return doExecute((cmd)->cmd
				.geoRadiusByMemberRo(KeyUtils.rawKey(this, key), member, radius, unit, count));
	}

	@Override
	default List<GeoRadius> geoRadiusByMemberRo(final byte[] key, final byte[] member, final double radius,
	                                            final GeoUnit unit, final int count) {
		return doExecute((cmd)->cmd
				.geoRadiusByMemberRo(KeyUtils.rawKey(this, key), member, radius, unit, count));
	}

	@Override
	default List<GeoRadius> geoRadiusByMemberRo(final String key, final String member, final double radius,
	                                            final GeoUnit unit, final int count, final boolean any) {
		return doExecute((cmd)->cmd
				.geoRadiusByMemberRo(KeyUtils.rawKey(this, key), member, radius, unit, count, any));
	}

	@Override
	default List<GeoRadius> geoRadiusByMemberRo(final byte[] key, final byte[] member, final double radius,
	                                            final GeoUnit unit, final int count, final boolean any) {
		return doExecute((cmd)->cmd
				.geoRadiusByMemberRo(KeyUtils.rawKey(this, key), member, radius, unit, count, any));
	}

	@Override
	default List<GeoRadius> geoSearch(final String key, final double longitude, final double latitude,
	                                  final double radius, final GeoUnit unit) {
		return doExecute((cmd)->cmd
				.geoSearch(KeyUtils.rawKey(this, key), longitude, latitude, radius, unit));
	}

	@Override
	default List<GeoRadius> geoSearch(final byte[] key, final double longitude, final double latitude,
	                                  final double radius, final GeoUnit unit) {
		return doExecute((cmd)->cmd
				.geoSearch(KeyUtils.rawKey(this, key), longitude, latitude, radius, unit));
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

	@Override
	default List<GeoRadius> geoSearch(final String key, final double longitude, final double latitude,
	                                  final double radius, final GeoUnit unit, final GeoSearchArgument argument) {
		return doExecute((cmd)->cmd
				.geoSearch(KeyUtils.rawKey(this, key), longitude, latitude, radius, unit, argument));
	}

	@Override
	default List<GeoRadius> geoSearch(final byte[] key, final double longitude, final double latitude,
	                                  final double radius, final GeoUnit unit, final GeoSearchArgument argument) {
		return doExecute((cmd)->cmd
				.geoSearch(KeyUtils.rawKey(this, key), longitude, latitude, radius, unit, argument));
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

	@Override
	default List<GeoRadius> geoSearch(final String key, final double longitude, final double latitude,
	                                  final double radius, final GeoUnit unit, final GeoSearchArgument argument,
	                                  final int count) {
		return doExecute((cmd)->cmd
				.geoSearch(KeyUtils.rawKey(this, key), longitude, latitude, radius, unit, argument, count));
	}

	@Override
	default List<GeoRadius> geoSearch(final byte[] key, final double longitude, final double latitude,
	                                  final double radius, final GeoUnit unit, final GeoSearchArgument argument,
	                                  final int count) {
		return doExecute((cmd)->cmd
				.geoSearch(KeyUtils.rawKey(this, key), longitude, latitude, radius, unit, argument, count));
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
	 * @param count
	 * 		返回数量
	 *
	 * @return 地理位置搜索结果
	 */
	default List<GeoRadius> geoSearch(final String key, final Geo geo, final double radius, final GeoUnit unit,
	                                  final GeoSearchArgument argument, final int count) {
		return geoSearch(key, geo.getLongitude(), geo.getLatitude(), radius, unit, argument, count);
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
	 * @param count
	 * 		返回数量
	 *
	 * @return 地理位置搜索结果
	 */
	default List<GeoRadius> geoSearch(final byte[] key, final Geo geo, final double radius, final GeoUnit unit,
	                                  final GeoSearchArgument argument, final int count) {
		return geoSearch(key, geo.getLongitude(), geo.getLatitude(), radius, unit, argument, count);
	}

	@Override
	default List<GeoRadius> geoSearch(final String key, final double longitude, final double latitude,
	                                  final double radius, final GeoUnit unit, final GeoSearchArgument argument,
	                                  final int count, final boolean any) {
		return doExecute((cmd)->cmd
				.geoSearch(KeyUtils.rawKey(this, key), longitude, latitude, radius, unit, argument, count, any));
	}

	@Override
	default List<GeoRadius> geoSearch(final byte[] key, final double longitude, final double latitude,
	                                  final double radius, final GeoUnit unit, final GeoSearchArgument argument,
	                                  final int count, final boolean any) {
		return doExecute((cmd)->cmd
				.geoSearch(KeyUtils.rawKey(this, key), longitude, latitude, radius, unit, argument, count, any));
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
	 * @param count
	 * 		返回数量
	 * @param any
	 * 		ANY
	 *
	 * @return 地理位置搜索结果
	 */
	default List<GeoRadius> geoSearch(final String key, final Geo geo, final double radius, final GeoUnit unit,
	                                  final GeoSearchArgument argument, final int count, final boolean any) {
		return geoSearch(key, geo.getLongitude(), geo.getLatitude(), radius, unit, argument, count, any);
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
	 * @param count
	 * 		返回数量
	 * @param any
	 * 		ANY
	 *
	 * @return 地理位置搜索结果
	 */
	default List<GeoRadius> geoSearch(final byte[] key, final Geo geo, final double radius, final GeoUnit unit,
	                                  final GeoSearchArgument argument, final int count, final boolean any) {
		return geoSearch(key, geo.getLongitude(), geo.getLatitude(), radius, unit, argument, count, any);
	}

	@Override
	default List<GeoRadius> geoSearch(final String key, final double longitude, final double latitude,
	                                  final double width, final double height, final GeoUnit unit) {
		return doExecute((cmd)->cmd.geoSearch(KeyUtils.rawKey(this, key), longitude, latitude, width, height, unit));
	}

	@Override
	default List<GeoRadius> geoSearch(final byte[] key, final double longitude, final double latitude,
	                                  final double width, final double height, final GeoUnit unit) {
		return doExecute((cmd)->cmd.geoSearch(KeyUtils.rawKey(this, key), longitude, latitude, width, height, unit));
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

	@Override
	default List<GeoRadius> geoSearch(final String key, final double longitude, final double latitude,
	                                  final double width, final double height, final GeoUnit unit,
	                                  final GeoSearchArgument argument) {
		return doExecute((cmd)->cmd
				.geoSearch(KeyUtils.rawKey(this, key), longitude, latitude, width, height, unit, argument));
	}

	@Override
	default List<GeoRadius> geoSearch(final byte[] key, final double longitude, final double latitude,
	                                  final double width, final double height, final GeoUnit unit,
	                                  final GeoSearchArgument argument) {
		return doExecute((cmd)->cmd
				.geoSearch(KeyUtils.rawKey(this, key), longitude, latitude, width, height, unit, argument));
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

	@Override
	default List<GeoRadius> geoSearch(final String key, final double longitude, final double latitude,
	                                  final double width, final double height, final GeoUnit unit,
	                                  final GeoSearchArgument argument, final int count) {
		return doExecute((cmd)->cmd
				.geoSearch(KeyUtils.rawKey(this, key), longitude, latitude, width, height, unit, argument, count));
	}

	@Override
	default List<GeoRadius> geoSearch(final byte[] key, final double longitude, final double latitude,
	                                  final double width, final double height, final GeoUnit unit,
	                                  final GeoSearchArgument argument, final int count) {
		return doExecute((cmd)->cmd
				.geoSearch(KeyUtils.rawKey(this, key), longitude, latitude, width, height, unit, argument, count));
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
	 * @param count
	 * 		返回数量
	 *
	 * @return 地理位置搜索结果
	 */
	default List<GeoRadius> geoSearch(final String key, final Geo geo, final double width, final double height,
	                                  final GeoUnit unit, final GeoSearchArgument argument, final int count) {
		return geoSearch(key, geo.getLongitude(), geo.getLatitude(), width, height, unit, argument, count);
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
	 * @param count
	 * 		返回数量
	 *
	 * @return 地理位置搜索结果
	 */
	default List<GeoRadius> geoSearch(final byte[] key, final Geo geo, final double width, final double height,
	                                  final GeoUnit unit, final GeoSearchArgument argument, final int count) {
		return geoSearch(key, geo.getLongitude(), geo.getLatitude(), width, height, unit, argument, count);
	}

	@Override
	default List<GeoRadius> geoSearch(final String key, final double longitude, final double latitude,
	                                  final double width, final double height, final GeoUnit unit,
	                                  final GeoSearchArgument argument, final int count, final boolean any) {
		return doExecute((cmd)->cmd
				.geoSearch(KeyUtils.rawKey(this, key), longitude, latitude, width, height, unit, argument, count, any));
	}

	@Override
	default List<GeoRadius> geoSearch(final byte[] key, final double longitude, final double latitude,
	                                  final double width, final double height, final GeoUnit unit,
	                                  final GeoSearchArgument argument, final int count, final boolean any) {
		return doExecute((cmd)->cmd
				.geoSearch(KeyUtils.rawKey(this, key), longitude, latitude, width, height, unit, argument, count, any));
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
	 * @param count
	 * 		返回数量
	 * @param any
	 * 		ANY
	 *
	 * @return 地理位置搜索结果
	 */
	default List<GeoRadius> geoSearch(final String key, final Geo geo, final double width, final double height,
	                                  final GeoUnit unit, final GeoSearchArgument argument, final int count,
	                                  final boolean any) {
		return geoSearch(key, geo.getLongitude(), geo.getLatitude(), width, height, unit, argument, count, any);
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
	 * @param count
	 * 		返回数量
	 * @param any
	 * 		ANY
	 *
	 * @return 地理位置搜索结果
	 */
	default List<GeoRadius> geoSearch(final byte[] key, final Geo geo, final double width, final double height,
	                                  final GeoUnit unit, final GeoSearchArgument argument, final int count,
	                                  final boolean any) {
		return geoSearch(key, geo.getLongitude(), geo.getLatitude(), width, height, unit, argument, count, any);
	}

	@Override
	default List<GeoRadius> geoSearch(final String key, final String member, final double radius, final GeoUnit unit) {
		return doExecute((cmd)->cmd.geoSearch(KeyUtils.rawKey(this, key), member, radius, unit));
	}

	@Override
	default List<GeoRadius> geoSearch(final byte[] key, final byte[] member, final double radius, final GeoUnit unit) {
		return doExecute((cmd)->cmd.geoSearch(KeyUtils.rawKey(this, key), member, radius, unit));
	}

	@Override
	default List<GeoRadius> geoSearch(final String key, final String member, final double radius, final GeoUnit unit,
	                                  final GeoSearchArgument argument) {
		return doExecute((cmd)->cmd.geoSearch(KeyUtils.rawKey(this, key), member, radius, unit, argument));
	}

	@Override
	default List<GeoRadius> geoSearch(final byte[] key, final byte[] member, final double radius, final GeoUnit unit,
	                                  final GeoSearchArgument argument) {
		return doExecute((cmd)->cmd.geoSearch(KeyUtils.rawKey(this, key), member, radius, unit, argument));
	}

	@Override
	default List<GeoRadius> geoSearch(final String key, final String member, final double radius, final GeoUnit unit,
	                                  final GeoSearchArgument argument, final int count) {
		return doExecute((cmd)->cmd
				.geoSearch(KeyUtils.rawKey(this, key), member, radius, unit, argument, count));
	}

	@Override
	default List<GeoRadius> geoSearch(final byte[] key, final byte[] member, final double radius, final GeoUnit unit,
	                                  final GeoSearchArgument argument, final int count) {
		return doExecute((cmd)->cmd
				.geoSearch(KeyUtils.rawKey(this, key), member, radius, unit, argument, count));
	}

	@Override
	default List<GeoRadius> geoSearch(final String key, final String member, final double radius, final GeoUnit unit,
	                                  final GeoSearchArgument argument, final int count, final boolean any) {
		return doExecute((cmd)->cmd
				.geoSearch(KeyUtils.rawKey(this, key), member, radius, unit, argument, count, any));
	}

	@Override
	default List<GeoRadius> geoSearch(final byte[] key, final byte[] member, final double radius, final GeoUnit unit,
	                                  final GeoSearchArgument argument, final int count, final boolean any) {
		return doExecute((cmd)->cmd
				.geoSearch(KeyUtils.rawKey(this, key), member, radius, unit, argument, count, any));
	}

	@Override
	default List<GeoRadius> geoSearch(final String key, final String member, final double width, final double height,
	                                  final GeoUnit unit) {
		return doExecute((cmd)->cmd.geoSearch(KeyUtils.rawKey(this, key), member, width, height, unit));
	}

	@Override
	default List<GeoRadius> geoSearch(final byte[] key, final byte[] member, final double width, final double height,
	                                  final GeoUnit unit) {
		return doExecute((cmd)->cmd.geoSearch(KeyUtils.rawKey(this, key), member, width, height, unit));
	}

	@Override
	default List<GeoRadius> geoSearch(final String key, final String member, final double width, final double height,
	                                  final GeoUnit unit, final GeoSearchArgument argument) {
		return doExecute((cmd)->cmd
				.geoSearch(KeyUtils.rawKey(this, key), member, width, height, unit, argument));
	}

	@Override
	default List<GeoRadius> geoSearch(final byte[] key, final byte[] member, final double width, final double height,
	                                  final GeoUnit unit, final GeoSearchArgument argument) {
		return doExecute((cmd)->cmd
				.geoSearch(KeyUtils.rawKey(this, key), member, width, height, unit, argument));
	}

	@Override
	default List<GeoRadius> geoSearch(final String key, final String member, final double width, final double height,
	                                  final GeoUnit unit, final GeoSearchArgument argument, final int count) {
		return doExecute((cmd)->cmd
				.geoSearch(KeyUtils.rawKey(this, key), member, width, height, unit, argument, count));
	}

	@Override
	default List<GeoRadius> geoSearch(final byte[] key, final byte[] member, final double width, final double height,
	                                  final GeoUnit unit, final GeoSearchArgument argument, final int count) {
		return doExecute((cmd)->cmd
				.geoSearch(KeyUtils.rawKey(this, key), member, width, height, unit, argument, count));
	}

	@Override
	default List<GeoRadius> geoSearch(final String key, final String member, final double width, final double height,
	                                  final GeoUnit unit, final GeoSearchArgument argument, final int count,
	                                  final boolean any) {
		return doExecute((cmd)->cmd
				.geoSearch(KeyUtils.rawKey(this, key), member, width, height, unit, argument, count, any));
	}

	@Override
	default List<GeoRadius> geoSearch(final byte[] key, final byte[] member, final double width, final double height,
	                                  final GeoUnit unit, final GeoSearchArgument argument, final int count,
	                                  final boolean any) {
		return doExecute((cmd)->cmd
				.geoSearch(KeyUtils.rawKey(this, key), member, width, height, unit, argument, count, any));
	}

	@Override
	default Long geoSearchStore(final String key, final String destKey, final double longitude, final double latitude,
	                            final double radius, final GeoUnit unit) {
		return doExecute((cmd)->cmd.geoSearchStore(KeyUtils.rawKey(this, key),
				KeyUtils.rawKey(this, destKey), longitude, latitude, radius, unit));
	}

	@Override
	default Long geoSearchStore(final byte[] key, final byte[] destKey, final double longitude, final double latitude,
	                            final double radius, final GeoUnit unit) {
		return doExecute((cmd)->cmd.geoSearchStore(KeyUtils.rawKey(this, key),
				KeyUtils.rawKey(this, destKey), longitude, latitude, radius, unit));
	}

	/**
	 * 将地理位置搜索结果存储到目标 destKey 中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearchstore/" target="_blank">https://redis.io/docs/latest/commands/geosearchstore/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param destKey
	 * 		目标 Key
	 * @param geo
	 * 		经纬度
	 * @param radius
	 * 		范围
	 * @param unit
	 * 		距离单位
	 *
	 * @return 结果数量
	 */
	default Long geoSearchStore(final String key, final String destKey, final Geo geo, final double radius,
	                            final GeoUnit unit) {
		return geoSearchStore(key, destKey, geo.getLongitude(), geo.getLatitude(), radius, unit);
	}

	/**
	 * 将地理位置搜索结果存储到目标 destKey 中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearchstore/" target="_blank">https://redis.io/docs/latest/commands/geosearchstore/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param destKey
	 * 		目标 Key
	 * @param geo
	 * 		经纬度
	 * @param radius
	 * 		范围
	 * @param unit
	 * 		距离单位
	 *
	 * @return 结果数量
	 */
	default Long geoSearchStore(final byte[] key, final byte[] destKey, final Geo geo, final double radius,
	                            final GeoUnit unit) {
		return geoSearchStore(key, destKey, geo.getLongitude(), geo.getLatitude(), radius, unit);
	}

	@Override
	default Long geoSearchStore(final String key, final String destKey, final double longitude, final double latitude,
	                            final double radius, final GeoUnit unit, final boolean storeDist) {
		return doExecute((cmd)->cmd.geoSearchStore(KeyUtils.rawKey(this, key),
				KeyUtils.rawKey(this, destKey), longitude, latitude, radius, unit, storeDist));
	}

	@Override
	default Long geoSearchStore(final byte[] key, final byte[] destKey, final double longitude, final double latitude,
	                            final double radius, final GeoUnit unit, final boolean storeDist) {
		return doExecute((cmd)->cmd.geoSearchStore(KeyUtils.rawKey(this, key),
				KeyUtils.rawKey(this, destKey), longitude, latitude, radius, unit, storeDist));
	}

	/**
	 * 将地理位置搜索结果存储到目标 destKey 中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearchstore/" target="_blank">https://redis.io/docs/latest/commands/geosearchstore/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param destKey
	 * 		目标 Key
	 * @param geo
	 * 		经纬度
	 * @param radius
	 * 		范围
	 * @param unit
	 * 		距离单位
	 * @param storeDist
	 * 		STOREDIST
	 *
	 * @return 将地理位置搜索结果存储到目标 destKey 中结果
	 */
	default Long geoSearchStore(final String key, final String destKey, final Geo geo, final double radius,
	                            final GeoUnit unit, final boolean storeDist) {
		return geoSearchStore(key, destKey, geo.getLongitude(), geo.getLatitude(), radius, unit, storeDist);
	}

	/**
	 * 将地理位置搜索结果存储到目标 destKey 中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearchstore/" target="_blank">https://redis.io/docs/latest/commands/geosearchstore/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param destKey
	 * 		目标 Key
	 * @param geo
	 * 		经纬度
	 * @param radius
	 * 		范围
	 * @param unit
	 * 		距离单位
	 * @param storeDist
	 * 		STOREDIST
	 *
	 * @return 将地理位置搜索结果存储到目标 destKey 中结果
	 */
	default Long geoSearchStore(final byte[] key, final byte[] destKey, final Geo geo, final double radius,
	                            final GeoUnit unit, final boolean storeDist) {
		return geoSearchStore(key, destKey, geo.getLongitude(), geo.getLatitude(), radius, unit, storeDist);
	}

	@Override
	default Long geoSearchStore(final String key, final String destKey, final double longitude, final double latitude,
	                            final double radius, final GeoUnit unit, final boolean storeDist, final int count) {
		return doExecute((cmd)->cmd.geoSearchStore(KeyUtils.rawKey(this, key),
				KeyUtils.rawKey(this, destKey), longitude, latitude, radius, unit, storeDist, count));
	}

	@Override
	default Long geoSearchStore(final byte[] key, final byte[] destKey, final double longitude, final double latitude,
	                            final double radius, final GeoUnit unit, final boolean storeDist, final int count) {
		return doExecute((cmd)->cmd.geoSearchStore(KeyUtils.rawKey(this, key),
				KeyUtils.rawKey(this, destKey), longitude, latitude, radius, unit, storeDist, count));
	}

	/**
	 * 将地理位置搜索结果存储到目标 destKey 中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearchstore/" target="_blank">https://redis.io/docs/latest/commands/geosearchstore/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param destKey
	 * 		目标 Key
	 * @param geo
	 * 		经纬度
	 * @param radius
	 * 		范围
	 * @param unit
	 * 		距离单位
	 * @param storeDist
	 * 		STOREDIST
	 * @param count
	 * 		返回数量
	 *
	 * @return 将地理位置搜索结果存储到目标 destKey 中结果
	 */
	default Long geoSearchStore(final String key, final String destKey, final Geo geo, final double radius,
	                            final GeoUnit unit, final boolean storeDist, final int count) {
		return geoSearchStore(key, destKey, geo.getLongitude(), geo.getLatitude(), radius, unit, storeDist, count);
	}

	/**
	 * 将地理位置搜索结果存储到目标 destKey 中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearchstore/" target="_blank">https://redis.io/docs/latest/commands/geosearchstore/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param destKey
	 * 		目标 Key
	 * @param geo
	 * 		经纬度
	 * @param radius
	 * 		范围
	 * @param unit
	 * 		距离单位
	 * @param storeDist
	 * 		STOREDIST
	 * @param count
	 * 		返回数量
	 *
	 * @return 将地理位置搜索结果存储到目标 destKey 中结果
	 */
	default Long geoSearchStore(final byte[] key, final byte[] destKey, final Geo geo, final double radius,
	                            final GeoUnit unit, final boolean storeDist, final int count) {
		return geoSearchStore(key, destKey, geo.getLongitude(), geo.getLatitude(), radius, unit, storeDist, count);
	}

	@Override
	default Long geoSearchStore(final String key, final String destKey, final double longitude, final double latitude,
	                            final double radius, final GeoUnit unit, final boolean storeDist, final int count,
	                            final boolean any) {
		return doExecute((cmd)->cmd.geoSearchStore(KeyUtils.rawKey(this, key),
				KeyUtils.rawKey(this, destKey), longitude, latitude, radius, unit, storeDist, count, any));
	}

	@Override
	default Long geoSearchStore(final byte[] key, final byte[] destKey, final double longitude, final double latitude,
	                            final double radius, final GeoUnit unit, final boolean storeDist, final int count,
	                            final boolean any) {
		return doExecute((cmd)->cmd.geoSearchStore(KeyUtils.rawKey(this, key),
				KeyUtils.rawKey(this, destKey), longitude, latitude, radius, unit, storeDist, count, any));
	}

	/**
	 * 将地理位置搜索结果存储到目标 destKey 中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearchstore/" target="_blank">https://redis.io/docs/latest/commands/geosearchstore/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param destKey
	 * 		目标 Key
	 * @param geo
	 * 		经纬度
	 * @param radius
	 * 		范围
	 * @param unit
	 * 		距离单位
	 * @param storeDist
	 * 		STOREDIST
	 * @param count
	 * 		返回数量
	 * @param any
	 * 		ANY
	 *
	 * @return 将地理位置搜索结果存储到目标 destKey 中结果
	 */
	default Long geoSearchStore(final String key, final String destKey, final Geo geo, final double radius,
	                            final GeoUnit unit, final boolean storeDist, final int count, final boolean any) {
		return geoSearchStore(key, destKey, geo.getLongitude(), geo.getLatitude(), radius, unit, storeDist, count, any);
	}

	/**
	 * 将地理位置搜索结果存储到目标 destKey 中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearchstore/" target="_blank">https://redis.io/docs/latest/commands/geosearchstore/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param destKey
	 * 		目标 Key
	 * @param geo
	 * 		经纬度
	 * @param radius
	 * 		范围
	 * @param unit
	 * 		距离单位
	 * @param storeDist
	 * 		STOREDIST
	 * @param count
	 * 		返回数量
	 * @param any
	 * 		ANY
	 *
	 * @return 将地理位置搜索结果存储到目标 destKey 中结果
	 */
	default Long geoSearchStore(final byte[] key, final byte[] destKey, final Geo geo, final double radius,
	                            final GeoUnit unit, final boolean storeDist, final int count, final boolean any) {
		return geoSearchStore(key, destKey, geo.getLongitude(), geo.getLatitude(), radius, unit, storeDist, count, any);
	}

	@Override
	default Long geoSearchStore(final String key, final String destKey, final double longitude, final double latitude,
	                            final double width, final double height, final GeoUnit unit) {
		return doExecute((cmd)->cmd.geoSearchStore(KeyUtils.rawKey(this, key),
				KeyUtils.rawKey(this, destKey), longitude, latitude, width, height, unit));
	}

	@Override
	default Long geoSearchStore(final byte[] key, final byte[] destKey, final double longitude, final double latitude,
	                            final double width, final double height, final GeoUnit unit) {
		return doExecute((cmd)->cmd.geoSearchStore(KeyUtils.rawKey(this, key),
				KeyUtils.rawKey(this, destKey), longitude, latitude, width, height, unit));
	}

	/**
	 * 将地理位置搜索结果存储到目标 destKey 中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearchstore/" target="_blank">https://redis.io/docs/latest/commands/geosearchstore/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param destKey
	 * 		目标 Key
	 * @param geo
	 * 		经纬度
	 * @param width
	 * 		宽度
	 * @param height
	 * 		高度
	 * @param unit
	 * 		距离单位
	 *
	 * @return 将地理位置搜索结果存储到目标 destKey 中结果
	 */
	default Long geoSearchStore(final String key, final String destKey, final Geo geo, final double width,
	                            final double height, final GeoUnit unit) {
		return geoSearchStore(key, destKey, geo.getLongitude(), geo.getLatitude(), width, height, unit);
	}

	/**
	 * 将地理位置搜索结果存储到目标 destKey 中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearchstore/" target="_blank">https://redis.io/docs/latest/commands/geosearchstore/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param destKey
	 * 		目标 Key
	 * @param geo
	 * 		经纬度
	 * @param width
	 * 		宽度
	 * @param height
	 * 		高度
	 * @param unit
	 * 		距离单位
	 *
	 * @return 将地理位置搜索结果存储到目标 destKey 中结果
	 */
	default Long geoSearchStore(final byte[] key, final byte[] destKey, final Geo geo, final double width,
	                            final double height, final GeoUnit unit) {
		return geoSearchStore(key, destKey, geo.getLongitude(), geo.getLatitude(), width, height, unit);
	}

	@Override
	default Long geoSearchStore(final String key, final String destKey, final double longitude, final double latitude,
	                            final double width, final double height, final GeoUnit unit, final boolean storeDist) {
		return doExecute((cmd)->cmd.geoSearchStore(KeyUtils.rawKey(this, key),
				KeyUtils.rawKey(this, destKey), longitude, latitude, width, height, unit, storeDist));
	}

	@Override
	default Long geoSearchStore(final byte[] key, final byte[] destKey, final double longitude, final double latitude,
	                            final double width, final double height, final GeoUnit unit, final boolean storeDist) {
		return doExecute((cmd)->cmd.geoSearchStore(KeyUtils.rawKey(this, key),
				KeyUtils.rawKey(this, destKey), longitude, latitude, width, height, unit, storeDist));
	}

	/**
	 * 将地理位置搜索结果存储到目标 destKey 中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearchstore/" target="_blank">https://redis.io/docs/latest/commands/geosearchstore/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param destKey
	 * 		目标 Key
	 * @param geo
	 * 		经纬度
	 * @param width
	 * 		宽度
	 * @param height
	 * 		高度
	 * @param unit
	 * 		距离单位
	 * @param storeDist
	 * 		STOREDIST
	 *
	 * @return 将地理位置搜索结果存储到目标 destKey 中结果
	 */
	default Long geoSearchStore(final String key, final String destKey, final Geo geo, final double width,
	                            final double height, final GeoUnit unit, final boolean storeDist) {
		return geoSearchStore(key, destKey, geo.getLongitude(), geo.getLatitude(), width, height, unit, storeDist);
	}

	/**
	 * 将地理位置搜索结果存储到目标 destKey 中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearchstore/" target="_blank">https://redis.io/docs/latest/commands/geosearchstore/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param destKey
	 * 		目标 Key
	 * @param geo
	 * 		经纬度
	 * @param width
	 * 		宽度
	 * @param height
	 * 		高度
	 * @param unit
	 * 		距离单位
	 * @param storeDist
	 * 		STOREDIST
	 *
	 * @return 将地理位置搜索结果存储到目标 destKey 中结果
	 */
	default Long geoSearchStore(final byte[] key, final byte[] destKey, final Geo geo, final double width,
	                            final double height, final GeoUnit unit, final boolean storeDist) {
		return geoSearchStore(key, destKey, geo.getLongitude(), geo.getLatitude(), width, height, unit, storeDist);
	}

	@Override
	default Long geoSearchStore(final String key, final String destKey, final double longitude, final double latitude,
	                            final double width, final double height, final GeoUnit unit, final boolean storeDist,
	                            final int count) {
		return doExecute((cmd)->cmd.geoSearchStore(KeyUtils.rawKey(this, key),
				KeyUtils.rawKey(this, destKey), longitude, latitude, width, height, unit, storeDist, count));
	}

	@Override
	default Long geoSearchStore(final byte[] key, final byte[] destKey, final double longitude, final double latitude,
	                            final double width, final double height, final GeoUnit unit, final boolean storeDist,
	                            final int count) {
		return doExecute((cmd)->cmd.geoSearchStore(KeyUtils.rawKey(this, key),
				KeyUtils.rawKey(this, destKey), longitude, latitude, width, height, unit, storeDist, count));
	}

	/**
	 * 将地理位置搜索结果存储到目标 destKey 中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearchstore/" target="_blank">https://redis.io/docs/latest/commands/geosearchstore/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param destKey
	 * 		目标 Key
	 * @param geo
	 * 		经纬度
	 * @param width
	 * 		宽度
	 * @param height
	 * 		高度
	 * @param unit
	 * 		距离单位
	 * @param storeDist
	 * 		STOREDIST
	 * @param count
	 * 		返回数量
	 *
	 * @return 将地理位置搜索结果存储到目标 destKey 中结果
	 */
	default Long geoSearchStore(final String key, final String destKey, final Geo geo, final double width,
	                            final double height, final GeoUnit unit, final boolean storeDist, final int count) {
		return geoSearchStore(key, destKey, geo.getLongitude(), geo.getLatitude(), width, height, unit, storeDist,
				count);
	}

	/**
	 * 将地理位置搜索结果存储到目标 destKey 中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearchstore/" target="_blank">https://redis.io/docs/latest/commands/geosearchstore/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param destKey
	 * 		目标 Key
	 * @param geo
	 * 		经纬度
	 * @param width
	 * 		宽度
	 * @param height
	 * 		高度
	 * @param unit
	 * 		距离单位
	 * @param storeDist
	 * 		STOREDIST
	 * @param count
	 * 		返回数量
	 *
	 * @return 将地理位置搜索结果存储到目标 destKey 中结果
	 */
	default Long geoSearchStore(final byte[] key, final byte[] destKey, final Geo geo, final double width,
	                            final double height, final GeoUnit unit, final boolean storeDist, final int count) {
		return geoSearchStore(key, destKey, geo.getLongitude(), geo.getLatitude(), width, height, unit, storeDist,
				count);
	}

	@Override
	default Long geoSearchStore(final String key, final String destKey, final double longitude, final double latitude,
	                            final double width, final double height, final GeoUnit unit, final boolean storeDist,
	                            final int count, final boolean any) {
		return doExecute((cmd)->cmd.geoSearchStore(KeyUtils.rawKey(this, key),
				KeyUtils.rawKey(this, destKey), longitude, latitude, width, height, unit, storeDist, count, any));
	}

	@Override
	default Long geoSearchStore(final byte[] key, final byte[] destKey, final double longitude, final double latitude,
	                            final double width, final double height, final GeoUnit unit, final boolean storeDist,
	                            final int count, final boolean any) {
		return doExecute((cmd)->cmd.geoSearchStore(KeyUtils.rawKey(this, key),
				KeyUtils.rawKey(this, destKey), longitude, latitude, width, height, unit, storeDist, count, any));
	}

	/**
	 * 将地理位置搜索结果存储到目标 destKey 中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearchstore/" target="_blank">https://redis.io/docs/latest/commands/geosearchstore/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param destKey
	 * 		目标 Key
	 * @param geo
	 * 		经纬度
	 * @param width
	 * 		宽度
	 * @param height
	 * 		高度
	 * @param unit
	 * 		距离单位
	 * @param storeDist
	 * 		STOREDIST
	 * @param count
	 * 		返回数量
	 * @param any
	 * 		ANY
	 *
	 * @return 将地理位置搜索结果存储到目标 destKey 中结果
	 */
	default Long geoSearchStore(final String key, final String destKey, final Geo geo, final double width,
	                            final double height, final GeoUnit unit, final boolean storeDist, final int count,
	                            final boolean any) {
		return geoSearchStore(key, destKey, geo.getLongitude(), geo.getLatitude(), width, height, unit, storeDist,
				count, any);
	}

	/**
	 * 将地理位置搜索结果存储到目标 destKey 中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearchstore/" target="_blank">https://redis.io/docs/latest/commands/geosearchstore/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param destKey
	 * 		目标 Key
	 * @param geo
	 * 		经纬度
	 * @param width
	 * 		宽度
	 * @param height
	 * 		高度
	 * @param unit
	 * 		距离单位
	 * @param storeDist
	 * 		STOREDIST
	 * @param count
	 * 		返回数量
	 * @param any
	 * 		ANY
	 *
	 * @return 将地理位置搜索结果存储到目标 destKey 中结果
	 */
	default Long geoSearchStore(final byte[] key, final byte[] destKey, final Geo geo, final double width,
	                            final double height, final GeoUnit unit, final boolean storeDist, final int count,
	                            final boolean any) {
		return geoSearchStore(key, destKey, geo.getLongitude(), geo.getLatitude(), width, height, unit, storeDist,
				count, any);
	}

	@Override
	default Long geoSearchStore(final String key, final String destKey, final String member, final double radius,
	                            final GeoUnit unit) {
		return doExecute((cmd)->cmd.geoSearchStore(KeyUtils.rawKey(this, key),
				KeyUtils.rawKey(this, destKey), member, radius, unit));
	}

	@Override
	default Long geoSearchStore(final byte[] key, final byte[] destKey, final byte[] member, final double radius,
	                            final GeoUnit unit) {
		return doExecute((cmd)->cmd.geoSearchStore(KeyUtils.rawKey(this, key),
				KeyUtils.rawKey(this, destKey), member, radius, unit));
	}

	@Override
	default Long geoSearchStore(final String key, final String destKey, final String member, final double radius,
	                            final GeoUnit unit, final boolean storeDist) {
		return doExecute((cmd)->cmd.geoSearchStore(KeyUtils.rawKey(this, key),
				KeyUtils.rawKey(this, destKey), member, radius, unit, storeDist));
	}

	@Override
	default Long geoSearchStore(final byte[] key, final byte[] destKey, final byte[] member, final double radius,
	                            final GeoUnit unit, final boolean storeDist) {
		return doExecute((cmd)->cmd.geoSearchStore(KeyUtils.rawKey(this, key),
				KeyUtils.rawKey(this, destKey), member, radius, unit, storeDist));
	}

	@Override
	default Long geoSearchStore(final String key, final String destKey, final String member, final double radius,
	                            final GeoUnit unit, final boolean storeDist, final int count) {
		return doExecute((cmd)->cmd.geoSearchStore(KeyUtils.rawKey(this, key),
				KeyUtils.rawKey(this, destKey), member, radius, unit, storeDist, count));
	}

	@Override
	default Long geoSearchStore(final byte[] key, final byte[] destKey, final byte[] member, final double radius,
	                            final GeoUnit unit, final boolean storeDist, final int count) {
		return doExecute((cmd)->cmd.geoSearchStore(KeyUtils.rawKey(this, key),
				KeyUtils.rawKey(this, destKey), member, radius, unit, storeDist, count));
	}

	@Override
	default Long geoSearchStore(final String key, final String destKey, final String member, final double radius,
	                            final GeoUnit unit, final boolean storeDist, final int count, final boolean any) {
		return doExecute((cmd)->cmd.geoSearchStore(KeyUtils.rawKey(this, key),
				KeyUtils.rawKey(this, destKey), member, radius, unit, storeDist, count, any));
	}

	@Override
	default Long geoSearchStore(final byte[] key, final byte[] destKey, final byte[] member, final double radius,
	                            final GeoUnit unit, final boolean storeDist, final int count, final boolean any) {
		return doExecute((cmd)->cmd.geoSearchStore(KeyUtils.rawKey(this, key),
				KeyUtils.rawKey(this, destKey), member, radius, unit, storeDist, count, any));
	}

	@Override
	default Long geoSearchStore(final String key, final String destKey, final String member, final double width,
	                            final double height, final GeoUnit unit) {
		return doExecute((cmd)->cmd.geoSearchStore(KeyUtils.rawKey(this, key),
				KeyUtils.rawKey(this, destKey), member, width, height, unit));
	}

	@Override
	default Long geoSearchStore(final byte[] key, final byte[] destKey, final byte[] member, final double width,
	                            final double height, final GeoUnit unit) {
		return doExecute((cmd)->cmd.geoSearchStore(KeyUtils.rawKey(this, key),
				KeyUtils.rawKey(this, destKey), member, width, height, unit));
	}

	@Override
	default Long geoSearchStore(final String key, final String destKey, final String member, final double width,
	                            final double height, final GeoUnit unit, final boolean storeDist) {
		return doExecute((cmd)->cmd.geoSearchStore(KeyUtils.rawKey(this, key),
				KeyUtils.rawKey(this, destKey), member, width, height, unit, storeDist));
	}

	@Override
	default Long geoSearchStore(final byte[] key, final byte[] destKey, final byte[] member, final double width,
	                            final double height, final GeoUnit unit, final boolean storeDist) {
		return doExecute((cmd)->cmd.geoSearchStore(KeyUtils.rawKey(this, key),
				KeyUtils.rawKey(this, destKey), member, width, height, unit, storeDist));
	}

	@Override
	default Long geoSearchStore(final String key, final String destKey, final String member, final double width,
	                            final double height, final GeoUnit unit, final boolean storeDist, final int count) {
		return doExecute((cmd)->cmd.geoSearchStore(KeyUtils.rawKey(this, key),
				KeyUtils.rawKey(this, destKey), member, width, height, unit, storeDist, count));
	}

	@Override
	default Long geoSearchStore(final byte[] key, final byte[] destKey, final byte[] member, final double width,
	                            final double height, final GeoUnit unit, final boolean storeDist, final int count) {
		return doExecute((cmd)->cmd.geoSearchStore(KeyUtils.rawKey(this, key),
				KeyUtils.rawKey(this, destKey), member, width, height, unit, storeDist, count));
	}

	@Override
	default Long geoSearchStore(final String key, final String destKey, final String member, final double width,
	                            final double height, final GeoUnit unit, final boolean storeDist, final int count,
	                            final boolean any) {
		return doExecute((cmd)->cmd.geoSearchStore(KeyUtils.rawKey(this, key),
				KeyUtils.rawKey(this, destKey), member, width, height, unit, storeDist, count, any));
	}

	@Override
	default Long geoSearchStore(final byte[] key, final byte[] destKey, final byte[] member, final double width,
	                            final double height, final GeoUnit unit, final boolean storeDist, final int count,
	                            final boolean any) {
		return doExecute((cmd)->cmd.geoSearchStore(KeyUtils.rawKey(this, key),
				KeyUtils.rawKey(this, destKey), member, width, height, unit, storeDist, count, any));
	}

	private <R> R doExecute(final Command.Executor<GeoCommands, R> executor) {
		return execute((client)->executor.execute(client.geoCommands()));
	}

}
