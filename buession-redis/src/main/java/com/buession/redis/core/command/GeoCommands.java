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
 * | Copyright @ 2013-2023 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.core.command;

import com.buession.lang.Geo;
import com.buession.lang.KeyValue;
import com.buession.redis.core.GeoRadius;
import com.buession.redis.core.command.args.geo.GeoStoreOption;
import com.buession.redis.core.command.args.geo.GeoUnit;
import com.buession.redis.core.command.args.NxXx;
import com.buession.redis.core.command.args.geo.GeoRadiusArgument;
import com.buession.redis.core.command.args.geo.GeoSearchArgument;

import java.util.List;

/**
 * 地理位置命令
 *
 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/?group=geo" target="_blank">https://redis.io/docs/latest/commands/?group=geo</a></p>
 *
 * @author Yong.Teng
 */
public interface GeoCommands extends RedisCommands {

	/**
	 * 批量将给定的空间元素（经度、纬度、名字）添加到指定的键里面
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geoadd/" target="_blank">https://redis.io/docs/latest/commands/geoadd/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param memberCoordinates
	 * 		空间元素（纬度、经度、名字）集合
	 *
	 * @return 新添加到键里面的空间元素数量，不包括那些已经存在但是被更新的元素
	 */
	@SuppressWarnings({"unchecked"})
	Long geoAdd(final String key, final KeyValue<String, Geo>... memberCoordinates);

	/**
	 * 批量将给定的空间元素（经度、纬度、名字）添加到指定的键里面
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geoadd/" target="_blank">https://redis.io/docs/latest/commands/geoadd/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param memberCoordinates
	 * 		空间元素（纬度、经度、名字）集合
	 *
	 * @return 新添加到键里面的空间元素数量，不包括那些已经存在但是被更新的元素
	 */
	@SuppressWarnings({"unchecked"})
	Long geoAdd(final byte[] key, final KeyValue<byte[], Geo>... memberCoordinates);

	/**
	 * 批量将给定的空间元素（经度、纬度、名字）添加到指定的键里面
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/geo/geoadd.html" target="_blank">http://redisdoc.com/geo/geoadd.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param nxXx
	 *        {@link NxXx}
	 * @param memberCoordinates
	 * 		空间元素（纬度、经度、名字）集合
	 *
	 * @return 新添加到键里面的空间元素数量，不包括那些已经存在但是被更新的元素
	 */
	@SuppressWarnings({"unchecked"})
	Long geoAdd(final String key, final NxXx nxXx, final KeyValue<String, Geo>... memberCoordinates);

	/**
	 * 批量将给定的空间元素（经度、纬度、名字）添加到指定的键里面
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/geo/geoadd.html" target="_blank">http://redisdoc.com/geo/geoadd.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param nxXx
	 *        {@link NxXx}
	 * @param memberCoordinates
	 * 		空间元素（纬度、经度、名字）集合
	 *
	 * @return 新添加到键里面的空间元素数量，不包括那些已经存在但是被更新的元素
	 */
	@SuppressWarnings({"unchecked"})
	Long geoAdd(final byte[] key, final NxXx nxXx, final KeyValue<byte[], Geo>... memberCoordinates);

	/**
	 * 批量将给定的空间元素（经度、纬度、名字）添加到指定的键里面
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/geo/geoadd.html" target="_blank">http://redisdoc.com/geo/geoadd.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param nxXx
	 *        {@link NxXx}
	 * @param ch
	 * 		CH
	 * @param memberCoordinates
	 * 		空间元素（纬度、经度、名字）集合
	 *
	 * @return 新添加到键里面的空间元素数量，不包括那些已经存在但是被更新的元素
	 */
	@SuppressWarnings({"unchecked"})
	Long geoAdd(final String key, final NxXx nxXx, final boolean ch, final KeyValue<String, Geo>... memberCoordinates);

	/**
	 * 批量将给定的空间元素（经度、纬度、名字）添加到指定的键里面
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/geo/geoadd.html" target="_blank">http://redisdoc.com/geo/geoadd.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param nxXx
	 *        {@link NxXx}
	 * @param ch
	 * 		CH
	 * @param memberCoordinates
	 * 		空间元素（纬度、经度、名字）集合
	 *
	 * @return 新添加到键里面的空间元素数量，不包括那些已经存在但是被更新的元素
	 */
	@SuppressWarnings({"unchecked"})
	Long geoAdd(final byte[] key, final NxXx nxXx, final boolean ch, final KeyValue<byte[], Geo>... memberCoordinates);

	/**
	 * 批量将给定的空间元素（经度、纬度、名字）添加到指定的键里面
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/geo/geoadd.html" target="_blank">http://redisdoc.com/geo/geoadd.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param ch
	 * 		CH
	 * @param memberCoordinates
	 * 		空间元素（纬度、经度、名字）集合
	 *
	 * @return 新添加到键里面的空间元素数量，不包括那些已经存在但是被更新的元素
	 */
	@SuppressWarnings({"unchecked"})
	Long geoAdd(final String key, final boolean ch, final KeyValue<String, Geo>... memberCoordinates);

	/**
	 * 批量将给定的空间元素（经度、纬度、名字）添加到指定的键里面
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/geo/geoadd.html" target="_blank">http://redisdoc.com/geo/geoadd.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param ch
	 * 		CH
	 * @param memberCoordinates
	 * 		空间元素（纬度、经度、名字）集合
	 *
	 * @return 新添加到键里面的空间元素数量，不包括那些已经存在但是被更新的元素
	 */
	@SuppressWarnings({"unchecked"})
	Long geoAdd(final byte[] key, final boolean ch, final KeyValue<byte[], Geo>... memberCoordinates);

	/**
	 * 计算两个给定位置之间的距离
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/geo/geodist.html" target="_blank">http://redisdoc.com/geo/geodist.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param member1
	 * 		位置一
	 * @param member2
	 * 		位置一
	 *
	 * @return 计算出的距离会以双精度浮点数的形式被返回（单位：米），如果给定的位置元素不存在，那么返回空值
	 */
	Double geoDist(final String key, final String member1, final String member2);

	/**
	 * 计算两个给定位置之间的距离
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/geo/geodist.html" target="_blank">http://redisdoc.com/geo/geodist.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param member1
	 * 		位置一
	 * @param member2
	 * 		位置一
	 *
	 * @return 计算出的距离会以双精度浮点数的形式被返回（单位：米），如果给定的位置元素不存在，那么返回空值
	 */
	Double geoDist(final byte[] key, final byte[] member1, final byte[] member2);

	/**
	 * 计算两个给定位置之间的距离
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/geo/geodist.html" target="_blank">http://redisdoc.com/geo/geodist.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param member1
	 * 		位置一
	 * @param member2
	 * 		位置一
	 * @param unit
	 * 		距离单位
	 *
	 * @return 计算出的距离会以双精度浮点数的形式被返回，如果给定的位置元素不存在，那么返回空值
	 */
	Double geoDist(final String key, final String member1, final String member2, final GeoUnit unit);

	/**
	 * 计算两个给定位置之间的距离
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/geo/geodist.html" target="_blank">http://redisdoc.com/geo/geodist.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param member1
	 * 		位置一
	 * @param member2
	 * 		位置一
	 * @param unit
	 * 		距离单位
	 *
	 * @return 计算出的距离会以双精度浮点数的形式被返回，如果给定的位置元素不存在，那么返回空值
	 */
	Double geoDist(final byte[] key, final byte[] member1, final byte[] member2, final GeoUnit unit);

	/**
	 * 获取一个或多个位置元素的 <a href="https://en.wikipedia.org/wiki/Geohashh" target="_blank">Geohash</a> 表示
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/geo/geohash.html" target="_blank">http://redisdoc.com/geo/geohash.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param members
	 * 		一个或多个位置元素
	 *
	 * @return Geohash 数组
	 */
	List<String> geoHash(final String key, final String... members);

	/**
	 * 获取一个或多个位置元素的 <a href="https://en.wikipedia.org/wiki/Geohashh" target="_blank">Geohash</a> 表示
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/geo/geohash.html" target="_blank">http://redisdoc.com/geo/geohash.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param members
	 * 		一个或多个位置元素
	 *
	 * @return Geohash 数组
	 */
	List<byte[]> geoHash(final byte[] key, final byte[]... members);

	/**
	 * 批量从键里面返回所有给定位置元素的位置（经度和纬度）
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/geo/geopos.html" target="_blank">http://redisdoc.com/geo/geopos.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param members
	 * 		名字
	 *
	 * @return 经纬度列表
	 */
	List<Geo> geoPos(final String key, final String... members);

	/**
	 * 批量从键里面返回所有给定位置元素的位置（经度和纬度）
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/geo/geopos.html" target="_blank">http://redisdoc.com/geo/geopos.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param members
	 * 		名字
	 *
	 * @return 经纬度列表
	 */
	List<Geo> geoPos(final byte[] key, final byte[]... members);

	/**
	 * 以给定的经纬度为中心，返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/georadius/" target="_blank">https://redis.io/docs/latest/commands/georadius/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param longitude
	 * 		经度
	 * @param latitude
	 * 		纬度
	 * @param radius
	 * 		范围
	 * @param unit
	 * 		距离单位
	 *
	 * @return 位置元素
	 */
	List<GeoRadius> geoRadius(final String key, final double longitude, final double latitude, final double radius,
							  final GeoUnit unit);

	/**
	 * 以给定的经纬度为中心，返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/georadius/" target="_blank">https://redis.io/docs/latest/commands/georadius/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param longitude
	 * 		经度
	 * @param latitude
	 * 		纬度
	 * @param radius
	 * 		范围
	 * @param unit
	 * 		距离单位
	 *
	 * @return 位置元素
	 */
	List<GeoRadius> geoRadius(final byte[] key, final double longitude, final double latitude, final double radius,
							  final GeoUnit unit);

	/**
	 * 以给定的经纬度为中心，返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/georadius/" target="_blank">https://redis.io/docs/latest/commands/georadius/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param longitude
	 * 		经度
	 * @param latitude
	 * 		纬度
	 * @param radius
	 * 		范围
	 * @param unit
	 * 		距离单位
	 * @param argument
	 * 		GEO Radius 参数
	 *
	 * @return 位置元素
	 */
	List<GeoRadius> geoRadius(final String key, final double longitude, final double latitude, final double radius,
							  final GeoUnit unit, final GeoRadiusArgument argument);

	/**
	 * 以给定的经纬度为中心，返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/georadius/" target="_blank">https://redis.io/docs/latest/commands/georadius/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param longitude
	 * 		经度
	 * @param latitude
	 * 		纬度
	 * @param radius
	 * 		范围
	 * @param unit
	 * 		距离单位
	 * @param argument
	 * 		参数
	 *
	 * @return 位置元素
	 */
	List<GeoRadius> geoRadius(final byte[] key, final double longitude, final double latitude, final double radius,
							  final GeoUnit unit, final GeoRadiusArgument argument);

	/**
	 * 以给定的经纬度为中心，返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/georadius/" target="_blank">https://redis.io/docs/latest/commands/georadius/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param longitude
	 * 		经度
	 * @param latitude
	 * 		纬度
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
	List<GeoRadius> geoRadius(final String key, final double longitude, final double latitude, final double radius,
							  final GeoUnit unit, final GeoRadiusArgument argument, final int count);

	/**
	 * 以给定的经纬度为中心，返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/georadius/" target="_blank">https://redis.io/docs/latest/commands/georadius/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param longitude
	 * 		经度
	 * @param latitude
	 * 		纬度
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
	List<GeoRadius> geoRadius(final byte[] key, final double longitude, final double latitude, final double radius,
							  final GeoUnit unit, final GeoRadiusArgument argument, final int count);

	/**
	 * 以给定的经纬度为中心，返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/georadius/" target="_blank">https://redis.io/docs/latest/commands/georadius/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param longitude
	 * 		经度
	 * @param latitude
	 * 		纬度
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
	List<GeoRadius> geoRadius(final String key, final double longitude, final double latitude, final double radius,
							  final GeoUnit unit, final GeoRadiusArgument argument, final int count, final boolean any);

	/**
	 * 以给定的经纬度为中心，返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/georadius/" target="_blank">https://redis.io/docs/latest/commands/georadius/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param longitude
	 * 		经度
	 * @param latitude
	 * 		纬度
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
	List<GeoRadius> geoRadius(final byte[] key, final double longitude, final double latitude, final double radius,
							  final GeoUnit unit, final GeoRadiusArgument argument, final int count, final boolean any);

	/**
	 * 以给定的经纬度为中心，返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/georadius/" target="_blank">https://redis.io/docs/latest/commands/georadius/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param longitude
	 * 		经度
	 * @param latitude
	 * 		纬度
	 * @param radius
	 * 		范围
	 * @param unit
	 * 		距离单位
	 * @param count
	 * 		返回数量
	 *
	 * @return 位置元素
	 */
	List<GeoRadius> geoRadius(final String key, final double longitude, final double latitude, final double radius,
							  final GeoUnit unit, final int count);

	/**
	 * 以给定的经纬度为中心，返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/georadius/" target="_blank">https://redis.io/docs/latest/commands/georadius/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param longitude
	 * 		经度
	 * @param latitude
	 * 		纬度
	 * @param radius
	 * 		范围
	 * @param unit
	 * 		距离单位
	 * @param count
	 * 		返回数量
	 *
	 * @return 位置元素
	 */
	List<GeoRadius> geoRadius(final byte[] key, final double longitude, final double latitude, final double radius,
							  final GeoUnit unit, final int count);

	/**
	 * 以给定的经纬度为中心，返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/georadius/" target="_blank">https://redis.io/docs/latest/commands/georadius/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param longitude
	 * 		经度
	 * @param latitude
	 * 		纬度
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
	List<GeoRadius> geoRadius(final String key, final double longitude, final double latitude, final double radius,
							  final GeoUnit unit, final int count, final boolean any);

	/**
	 * 以给定的经纬度为中心，返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/georadius/" target="_blank">https://redis.io/docs/latest/commands/georadius/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param longitude
	 * 		经度
	 * @param latitude
	 * 		纬度
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
	List<GeoRadius> geoRadius(final byte[] key, final double longitude, final double latitude, final double radius,
							  final GeoUnit unit, final int count, final boolean any);

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
	 * 		存储方式
	 * @param longitude
	 * 		经度
	 * @param latitude
	 * 		纬度
	 * @param radius
	 * 		范围
	 * @param unit
	 * 		距离单位
	 *
	 * @return 位置元素
	 */
	Long geoRadius(final String key, final String destKey, final GeoStoreOption storeOption, final double longitude,
				   final double latitude, final double radius, final GeoUnit unit);

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
	 * 		存储方式
	 * @param longitude
	 * 		经度
	 * @param latitude
	 * 		纬度
	 * @param radius
	 * 		范围
	 * @param unit
	 * 		距离单位
	 *
	 * @return 位置元素
	 */
	Long geoRadius(final byte[] key, final byte[] destKey, final GeoStoreOption storeOption, final double longitude,
				   final double latitude, final double radius, final GeoUnit unit);

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
	 * 		存储方式
	 * @param longitude
	 * 		经度
	 * @param latitude
	 * 		纬度
	 * @param radius
	 * 		范围
	 * @param unit
	 * 		距离单位
	 * @param argument
	 * 		GEO Radius 参数
	 *
	 * @return 位置元素
	 */
	Long geoRadius(final String key, final String destKey, final GeoStoreOption storeOption, final double longitude,
				   final double latitude, final double radius, final GeoUnit unit, final GeoRadiusArgument argument);

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
	 * 		存储方式
	 * @param longitude
	 * 		经度
	 * @param latitude
	 * 		纬度
	 * @param radius
	 * 		范围
	 * @param unit
	 * 		距离单位
	 * @param argument
	 * 		参数
	 *
	 * @return 位置元素
	 */
	Long geoRadius(final byte[] key, final byte[] destKey, final GeoStoreOption storeOption, final double longitude,
				   final double latitude, final double radius, final GeoUnit unit, final GeoRadiusArgument argument);

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
	 * 		存储方式
	 * @param longitude
	 * 		经度
	 * @param latitude
	 * 		纬度
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
	Long geoRadius(final String key, final String destKey, final GeoStoreOption storeOption, final double longitude,
				   final double latitude, final double radius, final GeoUnit unit, final GeoRadiusArgument argument,
				   final int count);

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
	 * 		存储方式
	 * @param longitude
	 * 		经度
	 * @param latitude
	 * 		纬度
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
	Long geoRadius(final byte[] key, final byte[] destKey, final GeoStoreOption storeOption, final double longitude,
				   final double latitude, final double radius, final GeoUnit unit, final GeoRadiusArgument argument,
				   final int count);

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
	 * 		存储方式
	 * @param longitude
	 * 		经度
	 * @param latitude
	 * 		纬度
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
	Long geoRadius(final String key, final String destKey, final GeoStoreOption storeOption, final double longitude,
				   final double latitude, final double radius, final GeoUnit unit, final GeoRadiusArgument argument,
				   final int count, final boolean any);

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
	 * 		存储方式
	 * @param longitude
	 * 		经度
	 * @param latitude
	 * 		纬度
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
	Long geoRadius(final byte[] key, final byte[] destKey, final GeoStoreOption storeOption, final double longitude,
				   final double latitude, final double radius, final GeoUnit unit, final GeoRadiusArgument argument,
				   final int count, final boolean any);

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
	 * 		存储方式
	 * @param longitude
	 * 		经度
	 * @param latitude
	 * 		纬度
	 * @param radius
	 * 		范围
	 * @param unit
	 * 		距离单位
	 * @param count
	 * 		返回数量
	 *
	 * @return 位置元素
	 */
	Long geoRadius(final String key, final String destKey, final GeoStoreOption storeOption, final double longitude,
				   final double latitude, final double radius, final GeoUnit unit, final int count);

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
	 * 		存储方式
	 * @param longitude
	 * 		经度
	 * @param latitude
	 * 		纬度
	 * @param radius
	 * 		范围
	 * @param unit
	 * 		距离单位
	 * @param count
	 * 		返回数量
	 *
	 * @return 位置元素
	 */
	Long geoRadius(final byte[] key, final byte[] destKey, final GeoStoreOption storeOption, final double longitude,
				   final double latitude, final double radius, final GeoUnit unit, final int count);

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
	 * 		存储方式
	 * @param longitude
	 * 		经度
	 * @param latitude
	 * 		纬度
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
	Long geoRadius(final String key, final String destKey, final GeoStoreOption storeOption, final double longitude,
				   final double latitude, final double radius, final GeoUnit unit, final int count, final boolean any);

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
	 * 		存储方式
	 * @param longitude
	 * 		经度
	 * @param latitude
	 * 		纬度
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
	Long geoRadius(final byte[] key, final byte[] destKey, final GeoStoreOption storeOption, final double longitude,
				   final double latitude, final double radius, final GeoUnit unit, final int count, final boolean any);

	/**
	 * 以给定的经纬度为中心，返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/georadius_ro/" target="_blank">https://redis.io/docs/latest/commands/georadius_ro/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param longitude
	 * 		经度
	 * @param latitude
	 * 		纬度
	 * @param radius
	 * 		范围
	 * @param unit
	 * 		距离单位
	 *
	 * @return 位置元素
	 */
	List<GeoRadius> geoRadiusRo(final String key, final double longitude, final double latitude, final double radius,
								final GeoUnit unit);

	/**
	 * 以给定的经纬度为中心，返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/georadius_ro/" target="_blank">https://redis.io/docs/latest/commands/georadius_ro/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param longitude
	 * 		经度
	 * @param latitude
	 * 		纬度
	 * @param radius
	 * 		范围
	 * @param unit
	 * 		距离单位
	 *
	 * @return 位置元素
	 */
	List<GeoRadius> geoRadiusRo(final byte[] key, final double longitude, final double latitude, final double radius,
								final GeoUnit unit);

	/**
	 * 以给定的经纬度为中心，返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/georadius_ro/" target="_blank">https://redis.io/docs/latest/commands/georadius_ro/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param longitude
	 * 		经度
	 * @param latitude
	 * 		纬度
	 * @param radius
	 * 		范围
	 * @param unit
	 * 		距离单位
	 * @param argument
	 * 		GEO Radius 参数
	 *
	 * @return 位置元素
	 */
	List<GeoRadius> geoRadiusRo(final String key, final double longitude, final double latitude, final double radius,
								final GeoUnit unit, final GeoRadiusArgument argument);

	/**
	 * 以给定的经纬度为中心，返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/georadius_ro/" target="_blank">https://redis.io/docs/latest/commands/georadius_ro/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param longitude
	 * 		经度
	 * @param latitude
	 * 		纬度
	 * @param radius
	 * 		范围
	 * @param unit
	 * 		距离单位
	 * @param argument
	 * 		参数
	 *
	 * @return 位置元素
	 */
	List<GeoRadius> geoRadiusRo(final byte[] key, final double longitude, final double latitude, final double radius,
								final GeoUnit unit, final GeoRadiusArgument argument);

	/**
	 * 以给定的经纬度为中心，返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/georadius_ro/" target="_blank">https://redis.io/docs/latest/commands/georadius_ro/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param longitude
	 * 		经度
	 * @param latitude
	 * 		纬度
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
	List<GeoRadius> geoRadiusRo(final String key, final double longitude, final double latitude, final double radius,
								final GeoUnit unit, final GeoRadiusArgument argument, final int count);

	/**
	 * 以给定的经纬度为中心，返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/georadius_ro/" target="_blank">https://redis.io/docs/latest/commands/georadius_ro/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param longitude
	 * 		经度
	 * @param latitude
	 * 		纬度
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
	List<GeoRadius> geoRadiusRo(final byte[] key, final double longitude, final double latitude, final double radius,
								final GeoUnit unit, final GeoRadiusArgument argument, final int count);

	/**
	 * 以给定的经纬度为中心，返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/georadius_ro/" target="_blank">https://redis.io/docs/latest/commands/georadius_ro/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param longitude
	 * 		经度
	 * @param latitude
	 * 		纬度
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
	List<GeoRadius> geoRadiusRo(final String key, final double longitude, final double latitude, final double radius,
								final GeoUnit unit, final GeoRadiusArgument argument, final int count,
								final boolean any);

	/**
	 * 以给定的经纬度为中心，返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/georadius_ro/" target="_blank">https://redis.io/docs/latest/commands/georadius_ro/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param longitude
	 * 		经度
	 * @param latitude
	 * 		纬度
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
	List<GeoRadius> geoRadiusRo(final byte[] key, final double longitude, final double latitude, final double radius,
								final GeoUnit unit, final GeoRadiusArgument argument, final int count,
								final boolean any);

	/**
	 * 以给定的经纬度为中心，返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/georadius_ro/" target="_blank">https://redis.io/docs/latest/commands/georadius_ro/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param longitude
	 * 		经度
	 * @param latitude
	 * 		纬度
	 * @param radius
	 * 		范围
	 * @param unit
	 * 		距离单位
	 * @param count
	 * 		返回数量
	 *
	 * @return 位置元素
	 */
	List<GeoRadius> geoRadiusRo(final String key, final double longitude, final double latitude, final double radius,
								final GeoUnit unit, final int count);

	/**
	 * 以给定的经纬度为中心，返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/georadius_ro/" target="_blank">https://redis.io/docs/latest/commands/georadius_ro/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param longitude
	 * 		经度
	 * @param latitude
	 * 		纬度
	 * @param radius
	 * 		范围
	 * @param unit
	 * 		距离单位
	 * @param count
	 * 		返回数量
	 *
	 * @return 位置元素
	 */
	List<GeoRadius> geoRadiusRo(final byte[] key, final double longitude, final double latitude, final double radius,
								final GeoUnit unit, final int count);

	/**
	 * 以给定的经纬度为中心，返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/georadius_ro/" target="_blank">https://redis.io/docs/latest/commands/georadius_ro/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param longitude
	 * 		经度
	 * @param latitude
	 * 		纬度
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
	List<GeoRadius> geoRadiusRo(final String key, final double longitude, final double latitude, final double radius,
								final GeoUnit unit, final int count, final boolean any);

	/**
	 * 以给定的经纬度为中心，返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/georadius_ro/" target="_blank">https://redis.io/docs/latest/commands/georadius_ro/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param longitude
	 * 		经度
	 * @param latitude
	 * 		纬度
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
	List<GeoRadius> geoRadiusRo(final byte[] key, final double longitude, final double latitude, final double radius,
								final GeoUnit unit, final int count, final boolean any);

	/**
	 * 基于指定成员，返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/georadiusbymember/" target="_blank">https://redis.io/docs/latest/commands/georadiusbymember/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param member
	 * 		成员
	 * @param radius
	 * 		范围
	 * @param unit
	 * 		距离单位
	 *
	 * @return 位置元素
	 */
	List<GeoRadius> geoRadiusByMember(final String key, final String member, final double radius, final GeoUnit unit);

	/**
	 * 基于指定成员，返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/georadiusbymember/" target="_blank">https://redis.io/docs/latest/commands/georadiusbymember/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param member
	 * 		成员
	 * @param radius
	 * 		范围
	 * @param unit
	 * 		距离单位
	 *
	 * @return 位置元素
	 */
	List<GeoRadius> geoRadiusByMember(final byte[] key, final byte[] member, final double radius, final GeoUnit unit);

	/**
	 * 基于指定成员，返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/georadiusbymember/" target="_blank">https://redis.io/docs/latest/commands/georadiusbymember/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param member
	 * 		成员
	 * @param radius
	 * 		范围
	 * @param unit
	 * 		距离单位
	 * @param argument
	 * 		GEO Radius 参数
	 *
	 * @return 位置元素
	 */
	List<GeoRadius> geoRadiusByMember(final String key, final String member, final double radius, final GeoUnit unit,
									  final GeoRadiusArgument argument);

	/**
	 * 基于指定成员，返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/georadiusbymember/" target="_blank">https://redis.io/docs/latest/commands/georadiusbymember/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param member
	 * 		成员
	 * @param radius
	 * 		范围
	 * @param unit
	 * 		距离单位
	 * @param argument
	 * 		参数
	 *
	 * @return 位置元素
	 */
	List<GeoRadius> geoRadiusByMember(final byte[] key, final byte[] member, final double radius, final GeoUnit unit,
									  final GeoRadiusArgument argument);

	/**
	 * 基于指定成员，返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/georadiusbymember/" target="_blank">https://redis.io/docs/latest/commands/georadiusbymember/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param member
	 * 		成员
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
	List<GeoRadius> geoRadiusByMember(final String key, final String member, final double radius, final GeoUnit unit,
									  final GeoRadiusArgument argument, final int count);

	/**
	 * 基于指定成员，返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/georadiusbymember/" target="_blank">https://redis.io/docs/latest/commands/georadiusbymember/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param member
	 * 		成员
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
	List<GeoRadius> geoRadiusByMember(final byte[] key, final byte[] member, final double radius, final GeoUnit unit,
									  final GeoRadiusArgument argument, final int count);

	/**
	 * 基于指定成员，返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/georadiusbymember/" target="_blank">https://redis.io/docs/latest/commands/georadiusbymember/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param member
	 * 		成员
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
	List<GeoRadius> geoRadiusByMember(final String key, final String member, final double radius, final GeoUnit unit,
									  final GeoRadiusArgument argument, final int count, final boolean any);

	/**
	 * 基于指定成员，返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/georadiusbymember/" target="_blank">https://redis.io/docs/latest/commands/georadiusbymember/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param member
	 * 		成员
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
	List<GeoRadius> geoRadiusByMember(final byte[] key, final byte[] member, final double radius, final GeoUnit unit,
									  final GeoRadiusArgument argument, final int count, final boolean any);

	/**
	 * 基于指定成员，返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/georadiusbymember/" target="_blank">https://redis.io/docs/latest/commands/georadiusbymember/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param member
	 * 		成员
	 * @param radius
	 * 		范围
	 * @param unit
	 * 		距离单位
	 * @param count
	 * 		返回数量
	 *
	 * @return 位置元素
	 */
	List<GeoRadius> geoRadiusByMember(final String key, final String member, final double radius, final GeoUnit unit,
									  final int count);

	/**
	 * 基于指定成员，返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/georadiusbymember/" target="_blank">https://redis.io/docs/latest/commands/georadiusbymember/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param member
	 * 		成员
	 * @param radius
	 * 		范围
	 * @param unit
	 * 		距离单位
	 * @param count
	 * 		返回数量
	 *
	 * @return 位置元素
	 */
	List<GeoRadius> geoRadiusByMember(final byte[] key, final byte[] member, final double radius, final GeoUnit unit,
									  final int count);

	/**
	 * 基于指定成员，返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/georadiusbymember/" target="_blank">https://redis.io/docs/latest/commands/georadiusbymember/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param member
	 * 		成员
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
	List<GeoRadius> geoRadiusByMember(final String key, final String member, final double radius, final GeoUnit unit,
									  final int count, final boolean any);

	/**
	 * 基于指定成员，返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/georadiusbymember/" target="_blank">https://redis.io/docs/latest/commands/georadiusbymember/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param member
	 * 		成员
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
	List<GeoRadius> geoRadiusByMember(final byte[] key, final byte[] member, final double radius, final GeoUnit unit,
									  final int count, final boolean any);

	/**
	 * 基于指定成员，返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素，并存储到目标 destKey 中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/georadiusbymember/" target="_blank">https://redis.io/docs/latest/commands/georadiusbymember/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param destKey
	 * 		目标 Key
	 * @param storeOption
	 * 		存储方式
	 * @param member
	 * 		成员
	 * @param radius
	 * 		范围
	 * @param unit
	 * 		距离单位
	 *
	 * @return 位置元素
	 */
	Long geoRadiusByMember(final String key, final String destKey, final GeoStoreOption storeOption,
						   final String member, final double radius, final GeoUnit unit);

	/**
	 * 基于指定成员，返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素，并存储到目标 destKey 中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/georadiusbymember/" target="_blank">https://redis.io/docs/latest/commands/georadiusbymember/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param destKey
	 * 		目标 Key
	 * @param storeOption
	 * 		存储方式
	 * @param member
	 * 		成员
	 * @param radius
	 * 		范围
	 * @param unit
	 * 		距离单位
	 *
	 * @return 位置元素
	 */
	Long geoRadiusByMember(final byte[] key, final byte[] destKey, final GeoStoreOption storeOption,
						   final byte[] member, final double radius, final GeoUnit unit);

	/**
	 * 基于指定成员，返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素，并存储到目标 destKey 中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/georadiusbymember/" target="_blank">https://redis.io/docs/latest/commands/georadiusbymember/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param destKey
	 * 		目标 Key
	 * @param storeOption
	 * 		存储方式
	 * @param member
	 * 		成员
	 * @param radius
	 * 		范围
	 * @param unit
	 * 		距离单位
	 * @param argument
	 * 		GEO Radius 参数
	 *
	 * @return 位置元素
	 */
	Long geoRadiusByMember(final String key, final String destKey, final GeoStoreOption storeOption,
						   final String member, final double radius, final GeoUnit unit,
						   final GeoRadiusArgument argument);

	/**
	 * 基于指定成员，返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素，并存储到目标 destKey 中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/georadiusbymember/" target="_blank">https://redis.io/docs/latest/commands/georadiusbymember/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param destKey
	 * 		目标 Key
	 * @param storeOption
	 * 		存储方式
	 * @param member
	 * 		成员
	 * @param radius
	 * 		范围
	 * @param unit
	 * 		距离单位
	 * @param argument
	 * 		参数
	 *
	 * @return 位置元素
	 */
	Long geoRadiusByMember(final byte[] key, final byte[] destKey, final GeoStoreOption storeOption,
						   final byte[] member, final double radius, final GeoUnit unit,
						   final GeoRadiusArgument argument);

	/**
	 * 基于指定成员，返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素，并存储到目标 destKey 中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/georadiusbymember/" target="_blank">https://redis.io/docs/latest/commands/georadiusbymember/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param destKey
	 * 		目标 Key
	 * @param storeOption
	 * 		存储方式
	 * @param member
	 * 		成员
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
	Long geoRadiusByMember(final String key, final String destKey, final GeoStoreOption storeOption,
						   final String member, final double radius, final GeoUnit unit,
						   final GeoRadiusArgument argument, final int count);

	/**
	 * 基于指定成员，返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素，并存储到目标 destKey 中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/georadiusbymember/" target="_blank">https://redis.io/docs/latest/commands/georadiusbymember/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param destKey
	 * 		目标 Key
	 * @param storeOption
	 * 		存储方式
	 * @param member
	 * 		成员
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
	Long geoRadiusByMember(final byte[] key, final byte[] destKey, final GeoStoreOption storeOption,
						   final byte[] member, final double radius, final GeoUnit unit,
						   final GeoRadiusArgument argument, final int count);

	/**
	 * 基于指定成员，返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素，并存储到目标 destKey 中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/georadiusbymember/" target="_blank">https://redis.io/docs/latest/commands/georadiusbymember/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param destKey
	 * 		目标 Key
	 * @param storeOption
	 * 		存储方式
	 * @param member
	 * 		成员
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
	Long geoRadiusByMember(final String key, final String destKey, final GeoStoreOption storeOption,
						   final String member, final double radius, final GeoUnit unit,
						   final GeoRadiusArgument argument, final int count, final boolean any);

	/**
	 * 基于指定成员，返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素，并存储到目标 destKey 中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/georadiusbymember/" target="_blank">https://redis.io/docs/latest/commands/georadiusbymember/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param destKey
	 * 		目标 Key
	 * @param storeOption
	 * 		存储方式
	 * @param member
	 * 		成员
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
	Long geoRadiusByMember(final byte[] key, final byte[] destKey, final GeoStoreOption storeOption,
						   final byte[] member, final double radius, final GeoUnit unit,
						   final GeoRadiusArgument argument, final int count, final boolean any);

	/**
	 * 基于指定成员，返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素，并存储到目标 destKey 中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/georadiusbymember/" target="_blank">https://redis.io/docs/latest/commands/georadiusbymember/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param destKey
	 * 		目标 Key
	 * @param storeOption
	 * 		存储方式
	 * @param member
	 * 		成员
	 * @param radius
	 * 		范围
	 * @param unit
	 * 		距离单位
	 * @param count
	 * 		返回数量
	 *
	 * @return 位置元素
	 */
	Long geoRadiusByMember(final String key, final String destKey, final GeoStoreOption storeOption,
						   final String member, final double radius, final GeoUnit unit, final int count);

	/**
	 * 基于指定成员，返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素，并存储到目标 destKey 中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/georadiusbymember/" target="_blank">https://redis.io/docs/latest/commands/georadiusbymember/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param destKey
	 * 		目标 Key
	 * @param storeOption
	 * 		存储方式
	 * @param member
	 * 		成员
	 * @param radius
	 * 		范围
	 * @param unit
	 * 		距离单位
	 * @param count
	 * 		返回数量
	 *
	 * @return 位置元素
	 */
	Long geoRadiusByMember(final byte[] key, final byte[] destKey, final GeoStoreOption storeOption,
						   final byte[] member, final double radius, final GeoUnit unit, final int count);

	/**
	 * 基于指定成员，返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素，并存储到目标 destKey 中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/georadiusbymember/" target="_blank">https://redis.io/docs/latest/commands/georadiusbymember/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param destKey
	 * 		目标 Key
	 * @param storeOption
	 * 		存储方式
	 * @param member
	 * 		成员
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
	Long geoRadiusByMember(final String key, final String destKey, final GeoStoreOption storeOption,
						   final String member, final double radius, final GeoUnit unit, final int count,
						   final boolean any);

	/**
	 * 基于指定成员，返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素，并存储到目标 destKey 中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/georadiusbymember/" target="_blank">https://redis.io/docs/latest/commands/georadiusbymember/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param destKey
	 * 		目标 Key
	 * @param storeOption
	 * 		存储方式
	 * @param member
	 * 		成员
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
	Long geoRadiusByMember(final byte[] key, final byte[] destKey, final GeoStoreOption storeOption,
						   final byte[] member, final double radius, final GeoUnit unit, final int count,
						   final boolean any);

	/**
	 * 基于指定成员，返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/georadiusbymember_ro/" target="_blank">https://redis.io/docs/latest/commands/georadiusbymember_ro/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param member
	 * 		成员
	 * @param radius
	 * 		范围
	 * @param unit
	 * 		距离单位
	 *
	 * @return 位置元素
	 */
	List<GeoRadius> geoRadiusByMemberRo(final String key, final String member, final double radius, final GeoUnit unit);

	/**
	 * 基于指定成员，返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/georadiusbymember_ro/" target="_blank">https://redis.io/docs/latest/commands/georadiusbymember_ro/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param member
	 * 		成员
	 * @param radius
	 * 		范围
	 * @param unit
	 * 		距离单位
	 *
	 * @return 位置元素
	 */
	List<GeoRadius> geoRadiusByMemberRo(final byte[] key, final byte[] member, final double radius, final GeoUnit unit);

	/**
	 * 基于指定成员，返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/georadiusbymember_ro/" target="_blank">https://redis.io/docs/latest/commands/georadiusbymember_ro/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param member
	 * 		成员
	 * @param radius
	 * 		范围
	 * @param unit
	 * 		距离单位
	 * @param argument
	 * 		GEO Radius 参数
	 *
	 * @return 位置元素
	 */
	List<GeoRadius> geoRadiusByMemberRo(final String key, final String member, final double radius, final GeoUnit unit,
										final GeoRadiusArgument argument);

	/**
	 * 基于指定成员，返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/georadiusbymember_ro/" target="_blank">https://redis.io/docs/latest/commands/georadiusbymember_ro/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param member
	 * 		成员
	 * @param radius
	 * 		范围
	 * @param unit
	 * 		距离单位
	 * @param argument
	 * 		参数
	 *
	 * @return 位置元素
	 */
	List<GeoRadius> geoRadiusByMemberRo(final byte[] key, final byte[] member, final double radius, final GeoUnit unit,
										final GeoRadiusArgument argument);

	/**
	 * 基于指定成员，返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/georadiusbymember_ro/" target="_blank">https://redis.io/docs/latest/commands/georadiusbymember_ro/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param member
	 * 		成员
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
	List<GeoRadius> geoRadiusByMemberRo(final String key, final String member, final double radius, final GeoUnit unit,
										final GeoRadiusArgument argument, final int count);

	/**
	 * 基于指定成员，返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/georadiusbymember_ro/" target="_blank">https://redis.io/docs/latest/commands/georadiusbymember_ro/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param member
	 * 		成员
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
	List<GeoRadius> geoRadiusByMemberRo(final byte[] key, final byte[] member, final double radius, final GeoUnit unit,
										final GeoRadiusArgument argument, final int count);

	/**
	 * 基于指定成员，返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/georadiusbymember_ro/" target="_blank">https://redis.io/docs/latest/commands/georadiusbymember_ro/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param member
	 * 		成员
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
	List<GeoRadius> geoRadiusByMemberRo(final String key, final String member, final double radius, final GeoUnit unit,
										final GeoRadiusArgument argument, final int count, final boolean any);

	/**
	 * 基于指定成员，返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/georadiusbymember_ro/" target="_blank">https://redis.io/docs/latest/commands/georadiusbymember_ro/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param member
	 * 		成员
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
	List<GeoRadius> geoRadiusByMemberRo(final byte[] key, final byte[] member, final double radius, final GeoUnit unit,
										final GeoRadiusArgument argument, final int count, final boolean any);

	/**
	 * 基于指定成员，返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/georadiusbymember_ro/" target="_blank">https://redis.io/docs/latest/commands/georadiusbymember_ro/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param member
	 * 		成员
	 * @param radius
	 * 		范围
	 * @param unit
	 * 		距离单位
	 * @param count
	 * 		返回数量
	 *
	 * @return 位置元素
	 */
	List<GeoRadius> geoRadiusByMemberRo(final String key, final String member, final double radius, final GeoUnit unit,
										final int count);

	/**
	 * 基于指定成员，返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/georadiusbymember_ro/" target="_blank">https://redis.io/docs/latest/commands/georadiusbymember_ro/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param member
	 * 		成员
	 * @param radius
	 * 		范围
	 * @param unit
	 * 		距离单位
	 * @param count
	 * 		返回数量
	 *
	 * @return 位置元素
	 */
	List<GeoRadius> geoRadiusByMemberRo(final byte[] key, final byte[] member, final double radius, final GeoUnit unit,
										final int count);

	/**
	 * 基于指定成员，返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/georadiusbymember_ro/" target="_blank">https://redis.io/docs/latest/commands/georadiusbymember_ro/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param member
	 * 		成员
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
	List<GeoRadius> geoRadiusByMemberRo(final String key, final String member, final double radius, final GeoUnit unit,
										final int count, final boolean any);

	/**
	 * 基于指定成员，返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/georadiusbymember_ro/" target="_blank">https://redis.io/docs/latest/commands/georadiusbymember_ro/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param member
	 * 		成员
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
	List<GeoRadius> geoRadiusByMemberRo(final byte[] key, final byte[] member, final double radius, final GeoUnit unit,
										final int count, final boolean any);

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
	 * @param unit
	 * 		距离单位
	 *
	 * @return 地理位置搜索结果
	 */
	List<GeoRadius> geoSearch(final String key, final double longitude, final double latitude, final double radius,
							  final GeoUnit unit);

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
	 * @param unit
	 * 		距离单位
	 *
	 * @return 地理位置搜索结果
	 */
	List<GeoRadius> geoSearch(final byte[] key, final double longitude, final double latitude, final double radius,
							  final GeoUnit unit);

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
	 * @param unit
	 * 		距离单位
	 * @param argument
	 * 		搜索参数
	 *
	 * @return 地理位置搜索结果
	 */
	List<GeoRadius> geoSearch(final String key, final double longitude, final double latitude, final double radius,
							  final GeoUnit unit, final GeoSearchArgument argument);

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
	 * @param unit
	 * 		距离单位
	 * @param argument
	 * 		搜索参数
	 *
	 * @return 地理位置搜索结果
	 */
	List<GeoRadius> geoSearch(final byte[] key, final double longitude, final double latitude, final double radius,
							  final GeoUnit unit, final GeoSearchArgument argument);

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
	 * @param unit
	 * 		距离单位
	 * @param argument
	 * 		搜索参数
	 * @param count
	 * 		返回数量
	 *
	 * @return 地理位置搜索结果
	 */
	List<GeoRadius> geoSearch(final String key, final double longitude, final double latitude, final double radius,
							  final GeoUnit unit, final GeoSearchArgument argument, final int count);

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
	 * @param unit
	 * 		距离单位
	 * @param argument
	 * 		搜索参数
	 * @param count
	 * 		返回数量
	 *
	 * @return 地理位置搜索结果
	 */
	List<GeoRadius> geoSearch(final byte[] key, final double longitude, final double latitude, final double radius,
							  final GeoUnit unit, final GeoSearchArgument argument, final int count);

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
	List<GeoRadius> geoSearch(final String key, final double longitude, final double latitude, final double radius,
							  final GeoUnit unit, final GeoSearchArgument argument, final int count, final boolean any);

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
	List<GeoRadius> geoSearch(final byte[] key, final double longitude, final double latitude, final double radius,
							  final GeoUnit unit, final GeoSearchArgument argument, final int count, final boolean any);

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
	 * @param unit
	 * 		距离单位
	 *
	 * @return 地理位置搜索结果
	 */
	List<GeoRadius> geoSearch(final String key, final double longitude, final double latitude, final double width,
							  final double height, final GeoUnit unit);

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
	 * @param unit
	 * 		距离单位
	 *
	 * @return 地理位置搜索结果
	 */
	List<GeoRadius> geoSearch(final byte[] key, final double longitude, final double latitude, final double width,
							  final double height, final GeoUnit unit);

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
	 * @param unit
	 * 		距离单位
	 * @param argument
	 * 		搜索参数
	 *
	 * @return 地理位置搜索结果
	 */
	List<GeoRadius> geoSearch(final String key, final double longitude, final double latitude, final double width,
							  final double height, final GeoUnit unit, final GeoSearchArgument argument);

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
	 * @param unit
	 * 		距离单位
	 * @param argument
	 * 		搜索参数
	 *
	 * @return 地理位置搜索结果
	 */
	List<GeoRadius> geoSearch(final byte[] key, final double longitude, final double latitude, final double width,
							  final double height, final GeoUnit unit, final GeoSearchArgument argument);

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
	 * @param unit
	 * 		距离单位
	 * @param argument
	 * 		搜索参数
	 * @param count
	 * 		返回数量
	 *
	 * @return 地理位置搜索结果
	 */
	List<GeoRadius> geoSearch(final String key, final double longitude, final double latitude, final double width,
							  final double height, final GeoUnit unit, final GeoSearchArgument argument,
							  final int count);

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
	 * @param unit
	 * 		距离单位
	 * @param argument
	 * 		搜索参数
	 * @param count
	 * 		返回数量
	 *
	 * @return 地理位置搜索结果
	 */
	List<GeoRadius> geoSearch(final byte[] key, final double longitude, final double latitude, final double width,
							  final double height, final GeoUnit unit, final GeoSearchArgument argument,
							  final int count);

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
	List<GeoRadius> geoSearch(final String key, final double longitude, final double latitude, final double width,
							  final double height, final GeoUnit unit, final GeoSearchArgument argument,
							  final int count, final boolean any);

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
	List<GeoRadius> geoSearch(final byte[] key, final double longitude, final double latitude, final double width,
							  final double height, final GeoUnit unit, final GeoSearchArgument argument,
							  final int count, final boolean any);

	/**
	 * 地理位置搜索
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearch/" target="_blank">https://redis.io/docs/latest/commands/geosearch/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param member
	 * 		成员
	 * @param radius
	 * 		范围
	 * @param unit
	 * 		距离单位
	 *
	 * @return 地理位置搜索结果
	 */
	List<GeoRadius> geoSearch(final String key, final String member, final double radius, final GeoUnit unit);

	/**
	 * 地理位置搜索
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearch/" target="_blank">https://redis.io/docs/latest/commands/geosearch/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param member
	 * 		成员
	 * @param radius
	 * 		范围
	 * @param unit
	 * 		距离单位
	 *
	 * @return 地理位置搜索结果
	 */
	List<GeoRadius> geoSearch(final byte[] key, final byte[] member, final double radius, final GeoUnit unit);

	/**
	 * 地理位置搜索
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearch/" target="_blank">https://redis.io/docs/latest/commands/geosearch/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param member
	 * 		成员
	 * @param radius
	 * 		范围
	 * @param unit
	 * 		距离单位
	 * @param argument
	 * 		搜索参数
	 *
	 * @return 地理位置搜索结果
	 */
	List<GeoRadius> geoSearch(final String key, final String member, final double radius, final GeoUnit unit,
							  final GeoSearchArgument argument);

	/**
	 * 地理位置搜索
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearch/" target="_blank">https://redis.io/docs/latest/commands/geosearch/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param member
	 * 		成员
	 * @param radius
	 * 		范围
	 * @param unit
	 * 		距离单位
	 * @param argument
	 * 		搜索参数
	 *
	 * @return 地理位置搜索结果
	 */
	List<GeoRadius> geoSearch(final byte[] key, final byte[] member, final double radius, final GeoUnit unit,
							  final GeoSearchArgument argument);

	/**
	 * 地理位置搜索
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearch/" target="_blank">https://redis.io/docs/latest/commands/geosearch/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param member
	 * 		成员
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
	List<GeoRadius> geoSearch(final String key, final String member, final double radius, final GeoUnit unit,
							  final GeoSearchArgument argument, final int count);

	/**
	 * 地理位置搜索
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearch/" target="_blank">https://redis.io/docs/latest/commands/geosearch/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param member
	 * 		成员
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
	List<GeoRadius> geoSearch(final byte[] key, final byte[] member, final double radius, final GeoUnit unit,
							  final GeoSearchArgument argument, final int count);

	/**
	 * 地理位置搜索
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearch/" target="_blank">https://redis.io/docs/latest/commands/geosearch/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param member
	 * 		成员
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
	List<GeoRadius> geoSearch(final String key, final String member, final double radius, final GeoUnit unit,
							  final GeoSearchArgument argument, final int count, final boolean any);

	/**
	 * 地理位置搜索
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearch/" target="_blank">https://redis.io/docs/latest/commands/geosearch/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param member
	 * 		成员
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
	List<GeoRadius> geoSearch(final byte[] key, final byte[] member, final double radius, final GeoUnit unit,
							  final GeoSearchArgument argument, final int count, final boolean any);

	/**
	 * 地理位置搜索
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearch/" target="_blank">https://redis.io/docs/latest/commands/geosearch/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param member
	 * 		成员
	 * @param width
	 * 		宽度
	 * @param height
	 * 		高度
	 * @param unit
	 * 		距离单位
	 *
	 * @return 地理位置搜索结果
	 */
	List<GeoRadius> geoSearch(final String key, final String member, final double width, final double height,
							  final GeoUnit unit);

	/**
	 * 地理位置搜索
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearch/" target="_blank">https://redis.io/docs/latest/commands/geosearch/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param member
	 * 		成员
	 * @param width
	 * 		宽度
	 * @param height
	 * 		高度
	 * @param unit
	 * 		距离单位
	 *
	 * @return 地理位置搜索结果
	 */
	List<GeoRadius> geoSearch(final byte[] key, final byte[] member, final double width, final double height,
							  final GeoUnit unit);

	/**
	 * 地理位置搜索
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearch/" target="_blank">https://redis.io/docs/latest/commands/geosearch/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param member
	 * 		成员
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
	List<GeoRadius> geoSearch(final String key, final String member, final double width, final double height,
							  final GeoUnit unit, final GeoSearchArgument argument);

	/**
	 * 地理位置搜索
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearch/" target="_blank">https://redis.io/docs/latest/commands/geosearch/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param member
	 * 		成员
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
	List<GeoRadius> geoSearch(final byte[] key, final byte[] member, final double width, final double height,
							  final GeoUnit unit, final GeoSearchArgument argument);

	/**
	 * 地理位置搜索
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearch/" target="_blank">https://redis.io/docs/latest/commands/geosearch/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param member
	 * 		成员
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
	List<GeoRadius> geoSearch(final String key, final String member, final double width, final double height,
							  final GeoUnit unit, final GeoSearchArgument argument, final int count);

	/**
	 * 地理位置搜索
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearch/" target="_blank">https://redis.io/docs/latest/commands/geosearch/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param member
	 * 		成员
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
	List<GeoRadius> geoSearch(final byte[] key, final byte[] member, final double width, final double height,
							  final GeoUnit unit, final GeoSearchArgument argument, final int count);

	/**
	 * 地理位置搜索
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearch/" target="_blank">https://redis.io/docs/latest/commands/geosearch/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param member
	 * 		成员
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
	List<GeoRadius> geoSearch(final String key, final String member, final double width, final double height,
							  final GeoUnit unit, final GeoSearchArgument argument, final int count, final boolean any);

	/**
	 * 地理位置搜索
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearch/" target="_blank">https://redis.io/docs/latest/commands/geosearch/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param member
	 * 		成员
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
	List<GeoRadius> geoSearch(final byte[] key, final byte[] member, final double width, final double height,
							  final GeoUnit unit, final GeoSearchArgument argument, final int count, final boolean any);

	/**
	 * 将地理位置搜索结果存储到目标 destKey 中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearchstore/" target="_blank">https://redis.io/docs/latest/commands/geosearchstore/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param destKey
	 * 		目标 Key
	 * @param longitude
	 * 		经度
	 * @param latitude
	 * 		纬度
	 * @param radius
	 * 		范围
	 * @param unit
	 * 		距离单位
	 *
	 * @return 结果数量
	 */
	Long geoSearchStore(final String key, final String destKey, final double longitude, final double latitude,
						final double radius, final GeoUnit unit);

	/**
	 * 将地理位置搜索结果存储到目标 destKey 中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearchstore/" target="_blank">https://redis.io/docs/latest/commands/geosearchstore/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param destKey
	 * 		目标 Key
	 * @param longitude
	 * 		经度
	 * @param latitude
	 * 		纬度
	 * @param radius
	 * 		范围
	 * @param unit
	 * 		距离单位
	 *
	 * @return 结果数量
	 */
	Long geoSearchStore(final byte[] key, final byte[] destKey, final double longitude, final double latitude,
						final double radius, final GeoUnit unit);

	/**
	 * 将地理位置搜索结果存储到目标 destKey 中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearchstore/" target="_blank">https://redis.io/docs/latest/commands/geosearchstore/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param destKey
	 * 		目标 Key
	 * @param longitude
	 * 		经度
	 * @param latitude
	 * 		纬度
	 * @param radius
	 * 		范围
	 * @param unit
	 * 		距离单位
	 * @param storeDist
	 * 		STOREDIST
	 *
	 * @return 将地理位置搜索结果存储到目标 destKey 中结果
	 */
	Long geoSearchStore(final String key, final String destKey, final double longitude, final double latitude,
						final double radius, final GeoUnit unit, final boolean storeDist);

	/**
	 * 将地理位置搜索结果存储到目标 destKey 中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearchstore/" target="_blank">https://redis.io/docs/latest/commands/geosearchstore/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param destKey
	 * 		目标 Key
	 * @param longitude
	 * 		经度
	 * @param latitude
	 * 		纬度
	 * @param radius
	 * 		范围
	 * @param unit
	 * 		距离单位
	 * @param storeDist
	 * 		STOREDIST
	 *
	 * @return 将地理位置搜索结果存储到目标 destKey 中结果
	 */
	Long geoSearchStore(final byte[] key, final byte[] destKey, final double longitude, final double latitude,
						final double radius, final GeoUnit unit, final boolean storeDist);

	/**
	 * 将地理位置搜索结果存储到目标 destKey 中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearchstore/" target="_blank">https://redis.io/docs/latest/commands/geosearchstore/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param destKey
	 * 		目标 Key
	 * @param longitude
	 * 		经度
	 * @param latitude
	 * 		纬度
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
	Long geoSearchStore(final String key, final String destKey, final double longitude, final double latitude,
						final double radius, final GeoUnit unit, final boolean storeDist, final int count);

	/**
	 * 将地理位置搜索结果存储到目标 destKey 中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearchstore/" target="_blank">https://redis.io/docs/latest/commands/geosearchstore/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param destKey
	 * 		目标 Key
	 * @param longitude
	 * 		经度
	 * @param latitude
	 * 		纬度
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
	Long geoSearchStore(final byte[] key, final byte[] destKey, final double longitude, final double latitude,
						final double radius, final GeoUnit unit, final boolean storeDist, final int count);

	/**
	 * 将地理位置搜索结果存储到目标 destKey 中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearchstore/" target="_blank">https://redis.io/docs/latest/commands/geosearchstore/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param destKey
	 * 		目标 Key
	 * @param longitude
	 * 		经度
	 * @param latitude
	 * 		纬度
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
	Long geoSearchStore(final String key, final String destKey, final double longitude, final double latitude,
						final double radius, final GeoUnit unit, final boolean storeDist, final int count,
						final boolean any);

	/**
	 * 将地理位置搜索结果存储到目标 destKey 中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearchstore/" target="_blank">https://redis.io/docs/latest/commands/geosearchstore/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param destKey
	 * 		目标 Key
	 * @param longitude
	 * 		经度
	 * @param latitude
	 * 		纬度
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
	Long geoSearchStore(final byte[] key, final byte[] destKey, final double longitude, final double latitude,
						final double radius, final GeoUnit unit, final boolean storeDist, final int count,
						final boolean any);

	/**
	 * 将地理位置搜索结果存储到目标 destKey 中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearchstore/" target="_blank">https://redis.io/docs/latest/commands/geosearchstore/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param destKey
	 * 		目标 Key
	 * @param longitude
	 * 		经度
	 * @param latitude
	 * 		纬度
	 * @param width
	 * 		宽度
	 * @param height
	 * 		高度
	 * @param unit
	 * 		距离单位
	 *
	 * @return 将地理位置搜索结果存储到目标 destKey 中结果
	 */
	Long geoSearchStore(final String key, final String destKey, final double longitude, final double latitude,
						final double width, final double height, final GeoUnit unit);

	/**
	 * 将地理位置搜索结果存储到目标 destKey 中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearchstore/" target="_blank">https://redis.io/docs/latest/commands/geosearchstore/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param destKey
	 * 		目标 Key
	 * @param longitude
	 * 		经度
	 * @param latitude
	 * 		纬度
	 * @param width
	 * 		宽度
	 * @param height
	 * 		高度
	 * @param unit
	 * 		距离单位
	 *
	 * @return 将地理位置搜索结果存储到目标 destKey 中结果
	 */
	Long geoSearchStore(final byte[] key, final byte[] destKey, final double longitude, final double latitude,
						final double width, final double height, final GeoUnit unit);

	/**
	 * 将地理位置搜索结果存储到目标 destKey 中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearchstore/" target="_blank">https://redis.io/docs/latest/commands/geosearchstore/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param destKey
	 * 		目标 Key
	 * @param longitude
	 * 		经度
	 * @param latitude
	 * 		纬度
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
	Long geoSearchStore(final String key, final String destKey, final double longitude, final double latitude,
						final double width, final double height, final GeoUnit unit, final boolean storeDist);

	/**
	 * 将地理位置搜索结果存储到目标 destKey 中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearchstore/" target="_blank">https://redis.io/docs/latest/commands/geosearchstore/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param destKey
	 * 		目标 Key
	 * @param longitude
	 * 		经度
	 * @param latitude
	 * 		纬度
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
	Long geoSearchStore(final byte[] key, final byte[] destKey, final double longitude, final double latitude,
						final double width, final double height, final GeoUnit unit, final boolean storeDist);

	/**
	 * 将地理位置搜索结果存储到目标 destKey 中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearchstore/" target="_blank">https://redis.io/docs/latest/commands/geosearchstore/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param destKey
	 * 		目标 Key
	 * @param longitude
	 * 		经度
	 * @param latitude
	 * 		纬度
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
	Long geoSearchStore(final String key, final String destKey, final double longitude, final double latitude,
						final double width, final double height, final GeoUnit unit, final boolean storeDist,
						final int count);

	/**
	 * 将地理位置搜索结果存储到目标 destKey 中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearchstore/" target="_blank">https://redis.io/docs/latest/commands/geosearchstore/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param destKey
	 * 		目标 Key
	 * @param longitude
	 * 		经度
	 * @param latitude
	 * 		纬度
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
	Long geoSearchStore(final byte[] key, final byte[] destKey, final double longitude, final double latitude,
						final double width, final double height, final GeoUnit unit, final boolean storeDist,
						final int count);

	/**
	 * 将地理位置搜索结果存储到目标 destKey 中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearchstore/" target="_blank">https://redis.io/docs/latest/commands/geosearchstore/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param destKey
	 * 		目标 Key
	 * @param longitude
	 * 		经度
	 * @param latitude
	 * 		纬度
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
	Long geoSearchStore(final String key, final String destKey, final double longitude, final double latitude,
						final double width, final double height, final GeoUnit unit, final boolean storeDist,
						final int count, final boolean any);

	/**
	 * 将地理位置搜索结果存储到目标 destKey 中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearchstore/" target="_blank">https://redis.io/docs/latest/commands/geosearchstore/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param destKey
	 * 		目标 Key
	 * @param longitude
	 * 		经度
	 * @param latitude
	 * 		纬度
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
	Long geoSearchStore(final byte[] key, final byte[] destKey, final double longitude, final double latitude,
						final double width, final double height, final GeoUnit unit, final boolean storeDist,
						final int count, final boolean any);

	/**
	 * 将地理位置搜索结果存储到目标 destKey 中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearchstore/" target="_blank">https://redis.io/docs/latest/commands/geosearchstore/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param destKey
	 * 		目标 Key
	 * @param member
	 * 		成员
	 * @param radius
	 * 		范围
	 * @param unit
	 * 		距离单位
	 *
	 * @return 将地理位置搜索结果存储到目标 destKey 中结果
	 */
	Long geoSearchStore(final String key, final String destKey, final String member, final double radius,
						final GeoUnit unit);

	/**
	 * 将地理位置搜索结果存储到目标 destKey 中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearchstore/" target="_blank">https://redis.io/docs/latest/commands/geosearchstore/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param destKey
	 * 		目标 Key
	 * @param member
	 * 		成员
	 * @param radius
	 * 		范围
	 * @param unit
	 * 		距离单位
	 *
	 * @return 将地理位置搜索结果存储到目标 destKey 中结果
	 */
	Long geoSearchStore(final byte[] key, final byte[] destKey, final byte[] member, final double radius,
						final GeoUnit unit);

	/**
	 * 将地理位置搜索结果存储到目标 destKey 中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearchstore/" target="_blank">https://redis.io/docs/latest/commands/geosearchstore/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param destKey
	 * 		目标 Key
	 * @param member
	 * 		成员
	 * @param radius
	 * 		范围
	 * @param unit
	 * 		距离单位
	 * @param storeDist
	 * 		STOREDIST
	 *
	 * @return 将地理位置搜索结果存储到目标 destKey 中结果
	 */
	Long geoSearchStore(final String key, final String destKey, final String member, final double radius,
						final GeoUnit unit, final boolean storeDist);

	/**
	 * 将地理位置搜索结果存储到目标 destKey 中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearchstore/" target="_blank">https://redis.io/docs/latest/commands/geosearchstore/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param destKey
	 * 		目标 Key
	 * @param member
	 * 		成员
	 * @param radius
	 * 		范围
	 * @param unit
	 * 		距离单位
	 * @param storeDist
	 * 		STOREDIST
	 *
	 * @return 将地理位置搜索结果存储到目标 destKey 中结果
	 */
	Long geoSearchStore(final byte[] key, final byte[] destKey, final byte[] member, final double radius,
						final GeoUnit unit, final boolean storeDist);

	/**
	 * 将地理位置搜索结果存储到目标 destKey 中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearchstore/" target="_blank">https://redis.io/docs/latest/commands/geosearchstore/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param destKey
	 * 		目标 Key
	 * @param member
	 * 		成员
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
	Long geoSearchStore(final String key, final String destKey, final String member, final double radius,
						final GeoUnit unit, final boolean storeDist, final int count);

	/**
	 * 将地理位置搜索结果存储到目标 destKey 中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearchstore/" target="_blank">https://redis.io/docs/latest/commands/geosearchstore/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param destKey
	 * 		目标 Key
	 * @param member
	 * 		成员
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
	Long geoSearchStore(final byte[] key, final byte[] destKey, final byte[] member, final double radius,
						final GeoUnit unit, final boolean storeDist, final int count);

	/**
	 * 将地理位置搜索结果存储到目标 destKey 中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearchstore/" target="_blank">https://redis.io/docs/latest/commands/geosearchstore/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param destKey
	 * 		目标 Key
	 * @param member
	 * 		成员
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
	Long geoSearchStore(final String key, final String destKey, final String member, final double radius,
						final GeoUnit unit, final boolean storeDist, final int count, final boolean any);

	/**
	 * 将地理位置搜索结果存储到目标 destKey 中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearchstore/" target="_blank">https://redis.io/docs/latest/commands/geosearchstore/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param destKey
	 * 		目标 Key
	 * @param member
	 * 		成员
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
	Long geoSearchStore(final byte[] key, final byte[] destKey, final byte[] member, final double radius,
						final GeoUnit unit, final boolean storeDist, final int count, final boolean any);

	/**
	 * 将地理位置搜索结果存储到目标 destKey 中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearchstore/" target="_blank">https://redis.io/docs/latest/commands/geosearchstore/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param destKey
	 * 		目标 Key
	 * @param member
	 * 		成员
	 * @param width
	 * 		宽度
	 * @param height
	 * 		高度
	 * @param unit
	 * 		距离单位
	 *
	 * @return 将地理位置搜索结果存储到目标 destKey 中结果
	 */
	Long geoSearchStore(final String key, final String destKey, final String member, final double width,
						final double height, final GeoUnit unit);

	/**
	 * 将地理位置搜索结果存储到目标 destKey 中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearchstore/" target="_blank">https://redis.io/docs/latest/commands/geosearchstore/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param destKey
	 * 		目标 Key
	 * @param member
	 * 		成员
	 * @param width
	 * 		宽度
	 * @param height
	 * 		高度
	 * @param unit
	 * 		距离单位
	 *
	 * @return 将地理位置搜索结果存储到目标 destKey 中结果
	 */
	Long geoSearchStore(final byte[] key, final byte[] destKey, final byte[] member, final double width,
						final double height, final GeoUnit unit);

	/**
	 * 将地理位置搜索结果存储到目标 destKey 中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearchstore/" target="_blank">https://redis.io/docs/latest/commands/geosearchstore/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param destKey
	 * 		目标 Key
	 * @param member
	 * 		成员
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
	Long geoSearchStore(final String key, final String destKey, final String member, final double width,
						final double height, final GeoUnit unit, final boolean storeDist);

	/**
	 * 将地理位置搜索结果存储到目标 destKey 中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearchstore/" target="_blank">https://redis.io/docs/latest/commands/geosearchstore/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param destKey
	 * 		目标 Key
	 * @param member
	 * 		成员
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
	Long geoSearchStore(final byte[] key, final byte[] destKey, final byte[] member, final double width,
						final double height, final GeoUnit unit, final boolean storeDist);

	/**
	 * 将地理位置搜索结果存储到目标 destKey 中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearchstore/" target="_blank">https://redis.io/docs/latest/commands/geosearchstore/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param destKey
	 * 		目标 Key
	 * @param member
	 * 		成员
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
	Long geoSearchStore(final String key, final String destKey, final String member, final double width,
						final double height, final GeoUnit unit, final boolean storeDist, final int count);

	/**
	 * 将地理位置搜索结果存储到目标 destKey 中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearchstore/" target="_blank">https://redis.io/docs/latest/commands/geosearchstore/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param destKey
	 * 		目标 Key
	 * @param member
	 * 		成员
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
	Long geoSearchStore(final byte[] key, final byte[] destKey, final byte[] member, final double width,
						final double height, final GeoUnit unit, final boolean storeDist, final int count);

	/**
	 * 将地理位置搜索结果存储到目标 destKey 中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearchstore/" target="_blank">https://redis.io/docs/latest/commands/geosearchstore/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param destKey
	 * 		目标 Key
	 * @param member
	 * 		成员
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
	Long geoSearchStore(final String key, final String destKey, final String member, final double width,
						final double height, final GeoUnit unit, final boolean storeDist, final int count,
						final boolean any);

	/**
	 * 将地理位置搜索结果存储到目标 destKey 中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearchstore/" target="_blank">https://redis.io/docs/latest/commands/geosearchstore/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param destKey
	 * 		目标 Key
	 * @param member
	 * 		成员
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
	Long geoSearchStore(final byte[] key, final byte[] destKey, final byte[] member, final double width,
						final double height, final GeoUnit unit, final boolean storeDist, final int count,
						final boolean any);

}
