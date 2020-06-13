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
 * | Copyright @ 2013-2020 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.core.command;

import com.buession.lang.Geo;
import com.buession.lang.Order;
import com.buession.redis.core.GeoRadius;
import com.buession.redis.core.GeoUnit;

import java.util.List;
import java.util.Map;

/**
 * 地理位置命令
 *
 * <p>详情说明 <a href="http://redisdoc.com/geo/index.html" target="_blank">http://redisdoc.com/geo/index.html</a></p>
 *
 * @author Yong.Teng
 */
public interface GeoCommands extends RedisCommands {

	/**
	 * 将给定的空间元素（经度、纬度、名字）添加到指定的键里面
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/geo/geoadd.html" target="_blank">http://redisdoc.com/geo/geoadd.html</a>
	 * </p>
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
	 * <p>详情说明 <a href="http://redisdoc.com/geo/geoadd.html" target="_blank">http://redisdoc.com/geo/geoadd.html</a>
	 * </p>
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
	 * <p>详情说明 <a href="http://redisdoc.com/geo/geoadd.html" target="_blank">http://redisdoc.com/geo/geoadd.html</a>
	 * </p>
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
	 * <p>详情说明 <a href="http://redisdoc.com/geo/geoadd.html" target="_blank">http://redisdoc.com/geo/geoadd
	 * .html</a></p>
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
	 * 批量从键里面返回所有给定位置元素的位置（经度和纬度）
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/geo/geopos.html" target="_blank">http://redisdoc.com/geo/geopos.html</a>
	 * </p>
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
	 * <p>详情说明 <a href="http://redisdoc.com/geo/geopos.html" target="_blank">http://redisdoc.com/geo/geopos.html</a>
	 * </p>
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
	 * 计算两个给定位置之间的距离
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/geo/geodist.html" target="_blank">http://redisdoc.com/geo/geodist.html</a>
	 * </p>
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
	 * <p>详情说明 <a href="http://redisdoc.com/geo/geodist.html" target="_blank">http://redisdoc.com/geo/geodist.html</a>
	 * </p>
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
	 * <p>详情说明 <a href="http://redisdoc.com/geo/geodist.html" target="_blank">http://redisdoc.com/geo/geodist.html</a>
	 * </p>
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
	 * <p>详情说明 <a href="http://redisdoc.com/geo/geodist.html" target="_blank">http://redisdoc.com/geo/geodist.html</a>
	 * </p>
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
	 * 以给定的经纬度为中心，返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素
	 *
	 * <p>详情说明
	 * <a href="http://redisdoc.com/geo/georadius.html" target="_blank">http://redisdoc.com/geo/georadius.html</a></p>
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
	List<GeoRadius> geoRadius(final String key, final double longitude, final double latitude, final double radius);

	/**
	 * 以给定的经纬度为中心，返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素
	 *
	 * <p>详情说明
	 * <a href="http://redisdoc.com/geo/georadius.html" target="_blank">http://redisdoc.com/geo/georadius.html</a></p>
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
	List<GeoRadius> geoRadius(final byte[] key, final double longitude, final double latitude, final double radius);

	/**
	 * 以给定的经纬度为中心，返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素
	 *
	 * <p>详情说明
	 * <a href="http://redisdoc.com/geo/georadius.html" target="_blank">http://redisdoc.com/geo/georadius.html</a></p>
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
	 * <p>详情说明
	 * <a href="http://redisdoc.com/geo/georadius.html" target="_blank">http://redisdoc.com/geo/georadius.html</a></p>
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
	 * <p>详情说明
	 * <a href="http://redisdoc.com/geo/georadius.html" target="_blank">http://redisdoc.com/geo/georadius.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param longitude
	 * 		经度
	 * @param latitude
	 * 		纬度
	 * @param radius
	 * 		范围（单位：米）
	 * @param geoArgument
	 * 		GEO 参数
	 *
	 * @return 位置元素
	 */
	List<GeoRadius> geoRadius(final String key, final double longitude, final double latitude, final double radius,
			final GeoArgument geoArgument);

	/**
	 * 以给定的经纬度为中心，返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素
	 *
	 * <p>详情说明
	 * <a href="http://redisdoc.com/geo/georadius.html" target="_blank">http://redisdoc.com/geo/georadius.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param longitude
	 * 		经度
	 * @param latitude
	 * 		纬度
	 * @param radius
	 * 		范围（单位：米）
	 * @param geoArgument
	 * 		GEO 参数
	 *
	 * @return 位置元素
	 */
	List<GeoRadius> geoRadius(final byte[] key, final double longitude, final double latitude, final double radius,
			final GeoArgument geoArgument);

	/**
	 * 以给定的经纬度为中心，返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素
	 *
	 * <p>详情说明
	 * <a href="http://redisdoc.com/geo/georadius.html" target="_blank">http://redisdoc.com/geo/georadius.html</a></p>
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
	 * @param geoArgument
	 * 		GEO 参数
	 *
	 * @return 位置元素
	 */
	List<GeoRadius> geoRadius(final String key, final double longitude, final double latitude, final double radius,
			final GeoUnit unit, final GeoArgument geoArgument);

	/**
	 * 以给定的经纬度为中心，返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素
	 *
	 * <p>详情说明
	 * <a href="http://redisdoc.com/geo/georadius.html" target="_blank">http://redisdoc.com/geo/georadius.html</a></p>
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
	 * @param geoArgument
	 * 		GEO 参数
	 *
	 * @return 位置元素
	 */
	List<GeoRadius> geoRadius(final byte[] key, final double longitude, final double latitude, final double radius,
			final GeoUnit unit, final GeoArgument geoArgument);

	/**
	 * 找出位于指定范围内的元素，
	 * 但是 GEORADIUSBYMEMBER 的中心点是由给定的位置元素决定的，而不是像 GEORADIUS 那样，使用输入的经度和纬度来决定中心点
	 *
	 * <p>详情说明
	 *
	 * <a href="http://redisdoc.com/geo/georadiusbymember.html" target="_blank">http://redisdoc
	 * .com/geo/georadiusbymember.html</a></p>
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
	List<GeoRadius> geoRadiusByMember(final String key, final String member, final double radius);

	/**
	 * 找出位于指定范围内的元素，
	 * 但是 GEORADIUSBYMEMBER 的中心点是由给定的位置元素决定的，而不是像 GEORADIUS 那样，使用输入的经度和纬度来决定中心点
	 *
	 * <p>详情说明
	 * <a href="http://redisdoc.com/geo/georadiusbymember.html" target="_blank">http://redisdoc.com/geo/georadiusbymember.html</a></p>
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
	List<GeoRadius> geoRadiusByMember(final byte[] key, final byte[] member, final double radius);

	/**
	 * 找出位于指定范围内的元素，
	 * 但是 GEORADIUSBYMEMBER 的中心点是由给定的位置元素决定的，而不是像 GEORADIUS 那样，使用输入的经度和纬度来决定中心点
	 *
	 * <p>详情说明
	 *
	 * <a href="http://redisdoc.com/geo/georadiusbymember.html" target="_blank">http://redisdoc.com/geo/georadiusbymember.html</a></p>
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
	 * <p>详情说明
	 * <a href="http://redisdoc.com/geo/georadiusbymember.html" target="_blank">http://redisdoc.com/geo/georadiusbymember.html</a></p>
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
	 * <p>详情说明
	 * <a href="http://redisdoc.com/geo/georadiusbymember.html" target="_blank">http://redisdoc.com/geo/georadiusbymember.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param member
	 * 		位置元素
	 * @param radius
	 * 		范围（单位：米）
	 * @param geoArgument
	 * 		GEO 参数
	 *
	 * @return 位置元素
	 */
	List<GeoRadius> geoRadiusByMember(final String key, final String member, final double radius,
			final GeoArgument geoArgument);

	/**
	 * 找出位于指定范围内的元素，
	 * 但是 GEORADIUSBYMEMBER 的中心点是由给定的位置元素决定的，而不是像 GEORADIUS 那样，使用输入的经度和纬度来决定中心点
	 *
	 * <p>详情说明
	 * <a href="http://redisdoc.com/geo/georadiusbymember.html" target="_blank">http://redisdoc.com/geo/georadiusbymember.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param member
	 * 		位置元素
	 * @param radius
	 * 		范围（单位：米）
	 * @param geoArgument
	 * 		GEO 参数
	 *
	 * @return 位置元素
	 */
	List<GeoRadius> geoRadiusByMember(final byte[] key, final byte[] member, final double radius,
			final GeoArgument geoArgument);

	/**
	 * 找出位于指定范围内的元素，
	 * 但是 GEORADIUSBYMEMBER 的中心点是由给定的位置元素决定的，而不是像 GEORADIUS 那样，使用输入的经度和纬度来决定中心点
	 *
	 * <p>详情说明
	 * <a href="http://redisdoc.com/geo/georadiusbymember.html" target="_blank">http://redisdoc.com/geo/georadiusbymember.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param member
	 * 		位置元素
	 * @param radius
	 * 		范围
	 * @param unit
	 * 		距离单位
	 * @param geoArgument
	 * 		GEO 参数
	 *
	 * @return 位置元素
	 */
	List<GeoRadius> geoRadiusByMember(final String key, final String member, final double radius, final GeoUnit unit,
			final GeoArgument geoArgument);

	/**
	 * 找出位于指定范围内的元素，
	 * 但是 GEORADIUSBYMEMBER 的中心点是由给定的位置元素决定的，而不是像 GEORADIUS 那样，使用输入的经度和纬度来决定中心点
	 *
	 * <p>详情说明
	 * <a href="http://redisdoc.com/geo/georadiusbymember.html" target="_blank">http://redisdoc.com/geo/georadiusbymember.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param member
	 * 		位置元素
	 * @param radius
	 * 		范围
	 * @param unit
	 * 		距离单位
	 * @param geoArgument
	 * 		GEO 参数
	 *
	 * @return 位置元素
	 */
	List<GeoRadius> geoRadiusByMember(final byte[] key, final byte[] member, final double radius, final GeoUnit unit,
			final GeoArgument geoArgument);

	/**
	 * 获取一个或多个位置元素的 <a href="https://en.wikipedia.org/wiki/Geohashh" target="_blank">Geohash</a> 表示
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/geo/geohash.html" target="_blank">http://redisdoc.com/geo/geohash.html</a>
	 * </p>
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
	 * <p>详情说明 <a href="http://redisdoc.com/geo/geohash.html" target="_blank">http://redisdoc.com/geo/geohash
	 * .html</a></p>
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
	 * GEO 参数
	 */
	class GeoArgument {

		private Boolean withCoord;

		private Boolean withDist;

		private Boolean withHash;

		private Order order;

		private Integer count;

		private GeoArgument(){

		}

		/**
		 * 获取是否将位置元素的经度和维度也一并返回
		 *
		 * @return 是否将位置元素的经度和维度也一并返回
		 */
		public Boolean isWithCoord(){
			return withCoord;
		}

		/**
		 * 获取是否在返回位置元素的同时，将位置元素与中心之间的距离也一并返回
		 *
		 * @return 是否在返回位置元素的同时，将位置元素与中心之间的距离也一并返回
		 */
		public Boolean isWithDist(){
			return withDist;
		}

		/**
		 * 获取是否返回位置元素经过原始 geohash 编码的有序集合分值
		 *
		 * @return 是否返回位置元素经过原始 geohash 编码的有序集合分值
		 */
		public Boolean isWithHash(){
			return withHash;
		}

		/**
		 * 排序方式
		 *
		 * @return 排序方式
		 */
		public Order getOrder(){
			return order;
		}

		/**
		 * 获取返回数量
		 *
		 * @return 返回数量
		 */
		public Integer getCount(){
			return count;
		}

		public static class Builder {

			private GeoArgument geoArgument = new GeoArgument();

			private Builder(){
			}

			public final static Builder create(){
				return new Builder();
			}

			/**
			 * 设置将位置元素的经度和维度也一并返回
			 *
			 * @return Builder
			 */
			public Builder withCoord(){
				geoArgument.withCoord = true;
				return this;
			}

			/**
			 * 设置在返回位置元素的同时，将位置元素与中心之间的距离也一并返回
			 *
			 * @return Builder
			 */
			public Builder withDist(){
				geoArgument.withDist = true;
				return this;
			}

			/**
			 * 设置返回位置元素经过原始 geohash 编码的有序集合分值
			 *
			 * @return Builder
			 */
			public Builder withHash(){
				geoArgument.withHash = true;
				return this;
			}

			/**
			 * 设置排序方式
			 *
			 * @param order
			 * 		排序方式
			 *
			 * @return Builder
			 */
			public Builder order(Order order){
				geoArgument.order = order;
				return this;
			}

			/**
			 * 设置返回数量
			 *
			 * @param count
			 * 		返回数量
			 *
			 * @return Builder
			 */
			public Builder count(Integer count){
				geoArgument.count = count;
				return this;
			}

			public GeoArgument build(){
				return geoArgument;
			}

		}

	}

}
