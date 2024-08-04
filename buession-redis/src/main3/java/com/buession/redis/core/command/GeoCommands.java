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
package com.buession.redis.core.command;

import com.buession.lang.Geo;
import com.buession.redis.core.GeoRadius;
import com.buession.redis.core.GeoUnit;
import com.buession.redis.core.command.args.GeoAddArgument;
import com.buession.redis.core.command.args.GeoRadiusArgument;
import com.buession.redis.core.command.args.GeoSearchArgument;
import com.buession.redis.core.command.args.GeoSearchStoreArgument;

import java.util.List;
import java.util.Map;

/**
 * 地理位置命令
 *
 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/?group=geo" target="_blank">https://redis.io/docs/latest/commands/?group=geo</a></p>
 *
 * @author Yong.Teng
 */
public interface GeoCommands extends RedisCommands {

	/**
	 * 将给定的空间元素（经度、纬度、名字）添加到指定的键里面
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/geo/geoadd.html" target="_blank">http://redisdoc.com/geo/geoadd.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param member
	 * 		名字
	 * @param longitude
	 * 		经度
	 * @param latitude
	 * 		纬度
	 *
	 * @return 新添加到键里面的空间元素数量，不包括那些已经存在但是被更新的元素
	 */
	Long geoAdd(final String key, final String member, final double longitude, final double latitude);

	/**
	 * 将给定的空间元素（经度、纬度、名字）添加到指定的键里面
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/geo/geoadd.html" target="_blank">http://redisdoc.com/geo/geoadd.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param member
	 * 		名字
	 * @param longitude
	 * 		经度
	 * @param latitude
	 * 		纬度
	 *
	 * @return 新添加到键里面的空间元素数量，不包括那些已经存在但是被更新的元素
	 */
	Long geoAdd(final byte[] key, final byte[] member, final double longitude, final double latitude);

	/**
	 * 批量将给定的空间元素（经度、纬度、名字）添加到指定的键里面
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/geo/geoadd.html" target="_blank">http://redisdoc.com/geo/geoadd.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param memberCoordinates
	 * 		空间元素（纬度、经度、名字）集合
	 *
	 * @return 新添加到键里面的空间元素数量，不包括那些已经存在但是被更新的元素
	 */
	Long geoAdd(final String key, final Map<String, Geo> memberCoordinates);

	/**
	 * 批量将给定的空间元素（经度、纬度、名字）添加到指定的键里面
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/geo/geoadd.html" target="_blank">http://redisdoc.com/geo/geoadd.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param memberCoordinates
	 * 		空间元素（纬度、经度、名字）集合
	 *
	 * @return 新添加到键里面的空间元素数量，不包括那些已经存在但是被更新的元素
	 */
	Long geoAdd(final byte[] key, final Map<byte[], Geo> memberCoordinates);

	/**
	 * 将给定的空间元素（经度、纬度、名字）添加到指定的键里面
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/geo/geoadd.html" target="_blank">http://redisdoc.com/geo/geoadd.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param member
	 * 		名字
	 * @param longitude
	 * 		经度
	 * @param latitude
	 * 		纬度
	 * @param geoAddArgument
	 * 		添加参数
	 *
	 * @return 新添加到键里面的空间元素数量，不包括那些已经存在但是被更新的元素
	 */
	Long geoAdd(final String key, final String member, final double longitude, final double latitude,
				final GeoAddArgument geoAddArgument);

	/**
	 * 将给定的空间元素（经度、纬度、名字）添加到指定的键里面
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/geo/geoadd.html" target="_blank">http://redisdoc.com/geo/geoadd.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param member
	 * 		名字
	 * @param longitude
	 * 		经度
	 * @param latitude
	 * 		纬度
	 * @param geoAddArgument
	 * 		添加参数
	 *
	 * @return 新添加到键里面的空间元素数量，不包括那些已经存在但是被更新的元素
	 *
	 * @since 3.0.0
	 */
	Long geoAdd(final byte[] key, final byte[] member, final double longitude, final double latitude,
				final GeoAddArgument geoAddArgument);

	/**
	 * 批量将给定的空间元素（经度、纬度、名字）添加到指定的键里面
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/geo/geoadd.html" target="_blank">http://redisdoc.com/geo/geoadd.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param memberCoordinates
	 * 		空间元素（纬度、经度、名字）集合
	 * @param geoAddArgument
	 * 		添加参数
	 *
	 * @return 新添加到键里面的空间元素数量，不包括那些已经存在但是被更新的元素
	 *
	 * @since 3.0.0
	 */
	Long geoAdd(final String key, final Map<String, Geo> memberCoordinates, final GeoAddArgument geoAddArgument);

	/**
	 * 批量将给定的空间元素（经度、纬度、名字）添加到指定的键里面
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/geo/geoadd.html" target="_blank">http://redisdoc.com/geo/geoadd.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param memberCoordinates
	 * 		空间元素（纬度、经度、名字）集合
	 * @param geoAddArgument
	 * 		添加参数
	 *
	 * @return 新添加到键里面的空间元素数量，不包括那些已经存在但是被更新的元素
	 *
	 * @since 3.0.0
	 */
	Long geoAdd(final byte[] key, final Map<byte[], Geo> memberCoordinates, final GeoAddArgument geoAddArgument);

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
	 * <p>详情说明 <a href="http://redisdoc.com/geo/georadius.html" target="_blank">http://redisdoc.com/geo/georadius.html</a></p>
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
	 * <p>详情说明 <a href="http://redisdoc.com/geo/georadius.html" target="_blank">http://redisdoc.com/geo/georadius.html</a></p>
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
	 * <p>详情说明 <a href="http://redisdoc.com/geo/georadius.html" target="_blank">http://redisdoc.com/geo/georadius.html</a></p>
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
	 * @param geoRadiusArgument
	 * 		GEO 参数
	 *
	 * @return 位置元素
	 */
	List<GeoRadius> geoRadius(final String key, final double longitude, final double latitude, final double radius,
							  final GeoUnit unit, final GeoRadiusArgument geoRadiusArgument);

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
	 * 		范围
	 * @param unit
	 * 		距离单位
	 * @param geoRadiusArgument
	 * 		GEO 参数
	 *
	 * @return 位置元素
	 */
	List<GeoRadius> geoRadius(final byte[] key, final double longitude, final double latitude, final double radius,
							  final GeoUnit unit, final GeoRadiusArgument geoRadiusArgument);

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
	 * 		范围
	 * @param unit
	 * 		距离单位
	 *
	 * @return 位置元素
	 */
	List<GeoRadius> geoRadiusRo(final String key, final double longitude, final double latitude, final double radius,
								final GeoUnit unit);

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
	 * 		范围
	 * @param unit
	 * 		距离单位
	 *
	 * @return 位置元素
	 */
	List<GeoRadius> geoRadiusRo(final byte[] key, final double longitude, final double latitude, final double radius,
								final GeoUnit unit);

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
	 * 		范围
	 * @param unit
	 * 		距离单位
	 * @param geoRadiusArgument
	 * 		GEO 参数
	 *
	 * @return 位置元素
	 */
	List<GeoRadius> geoRadiusRo(final String key, final double longitude, final double latitude, final double radius,
								final GeoUnit unit, final GeoRadiusArgument geoRadiusArgument);

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
	 * 		范围
	 * @param unit
	 * 		距离单位
	 * @param geoRadiusArgument
	 * 		GEO 参数
	 *
	 * @return 位置元素
	 */
	List<GeoRadius> geoRadiusRo(final byte[] key, final double longitude, final double latitude, final double radius,
								final GeoUnit unit, final GeoRadiusArgument geoRadiusArgument);

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
	 * 		范围
	 * @param unit
	 * 		距离单位
	 *
	 * @return 位置元素
	 */
	List<GeoRadius> geoRadiusByMember(final String key, final String member, final double radius, final GeoUnit unit);

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
	 * 		范围
	 * @param unit
	 * 		距离单位
	 *
	 * @return 位置元素
	 */
	List<GeoRadius> geoRadiusByMember(final byte[] key, final byte[] member, final double radius, final GeoUnit unit);

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
	 * 		范围
	 * @param unit
	 * 		距离单位
	 * @param geoRadiusArgument
	 * 		GEO 参数
	 *
	 * @return 位置元素
	 */
	List<GeoRadius> geoRadiusByMember(final String key, final String member, final double radius, final GeoUnit unit,
									  final GeoRadiusArgument geoRadiusArgument);

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
	 * 		范围
	 * @param unit
	 * 		距离单位
	 * @param geoRadiusArgument
	 * 		GEO 参数
	 *
	 * @return 位置元素
	 */
	List<GeoRadius> geoRadiusByMember(final byte[] key, final byte[] member, final double radius, final GeoUnit unit,
									  final GeoRadiusArgument geoRadiusArgument);

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
	 * @param unit
	 * 		距离单位
	 *
	 * @return 位置元素
	 */
	List<GeoRadius> geoRadiusByMemberRo(final String key, final String member, final double radius, final GeoUnit unit);

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
	 * @param unit
	 * 		距离单位
	 *
	 * @return 位置元素
	 */
	List<GeoRadius> geoRadiusByMemberRo(final byte[] key, final byte[] member, final double radius, final GeoUnit unit);

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
	 * @param unit
	 * 		距离单位
	 * @param geoRadiusArgument
	 * 		GEO 参数
	 *
	 * @return 位置元素
	 */
	List<GeoRadius> geoRadiusByMemberRo(final String key, final String member, final double radius, final GeoUnit unit,
										final GeoRadiusArgument geoRadiusArgument);

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
	 * @param unit
	 * 		距离单位
	 * @param geoRadiusArgument
	 * 		GEO 参数
	 *
	 * @return 位置元素
	 */
	List<GeoRadius> geoRadiusByMemberRo(final byte[] key, final byte[] member, final double radius, final GeoUnit unit,
										final GeoRadiusArgument geoRadiusArgument);

	/**
	 * Return the members of a sorted set populated with geospatial information using {@code GEOADD}, which are within
	 * the borders of the area specified by a given shape. This command extends the {@code GEORADIUS} command, so in
	 * addition to searching within circular areas, it supports searching within rectangular areas.
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearch/" target="_blank">https://redis.io/docs/latest/commands/geosearch/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param geoSearchArgument
	 * 		搜索参数
	 *
	 * @return The members of a sorted set populated with geospatial information using {@code GEOADD}
	 *
	 * @since 3.0.0
	 */
	List<GeoRadius> geoSearch(final String key, final GeoSearchArgument geoSearchArgument);

	/**
	 * Return the members of a sorted set populated with geospatial information using {@code GEOADD}, which are within
	 * the borders of the area specified by a given shape. This command extends the {@code GEORADIUS} command, so in
	 * addition to searching within circular areas, it supports searching within rectangular areas.
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearch/" target="_blank">https://redis.io/docs/latest/commands/geosearch/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param geoSearchArgument
	 * 		搜索参数
	 *
	 * @return The members of a sorted set populated with geospatial information using {@code GEOADD}
	 *
	 * @since 3.0.0
	 */
	List<GeoRadius> geoSearch(final byte[] key, final GeoSearchArgument geoSearchArgument);

	/**
	 * This command is like {@code GEOSEARCH}, but stores the result in destination key.
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearchstore/" target="_blank">https://redis.io/docs/latest/commands/geosearchstore/</a></p>
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param key
	 * 		Key
	 * @param geoSearchStoreArgument
	 * 		搜索及存储参数
	 *
	 * @return The members of a sorted set populated with geospatial information using {@code GEOADD}
	 *
	 * @since 3.0.0
	 */
	Long geoSearchStore(final String destKey, final String key, final GeoSearchStoreArgument geoSearchStoreArgument);

	/**
	 * RThis command is like {@code GEOSEARCH}, but stores the result in destination key.
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/geosearchstore/" target="_blank">https://redis.io/docs/latest/commands/geosearchstore/</a></p>
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param key
	 * 		Key
	 * @param geoSearchStoreArgument
	 * 		搜索及存储参数
	 *
	 * @return The members of a sorted set populated with geospatial information using {@code GEOADD}
	 *
	 * @since 3.0.0
	 */
	Long geoSearchStore(final byte[] destKey, final byte[] key, final GeoSearchStoreArgument geoSearchStoreArgument);

}
