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

import com.buession.redis.core.Keyword;

/**
 * {@code GEOSEARCH} 命令参数
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public class GeoSearchArgument extends BaseGeoSearchArgument {

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
	 * 构造函数
	 */
	public GeoSearchArgument() {
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

	@Override
	public String toString() {
		final ArgumentStringBuilder builder = ArgumentStringBuilder.create();

		if(getFromMode() instanceof BaseGeoSearchArgument.FromMember){
			builder.add(Keyword.Geo.FROMMEMBER.name(), getFromMode().toString());
		}else if(getFromMode() instanceof BaseGeoSearchArgument.FromLonLat){
			builder.add(Keyword.Geo.FROMLONLAT.name(), getFromMode().toString());
		}

		if(getPredicate() instanceof BaseGeoSearchArgument.RadiusPredicate){
			builder.add(Keyword.Geo.FROMLONLAT.BYRADIUS.name(), getPredicate().toString());
		}else if(getPredicate() instanceof BaseGeoSearchArgument.BoxPredicate){
			builder.add(Keyword.Geo.FROMLONLAT.BYBOX.name(), getPredicate().toString());
		}

		if(getOrder() != null){
			builder.append(getOrder());
		}

		if(getCount() != null){
			builder.add(Keyword.Common.COUNT, getCount());
			if(Boolean.TRUE.equals(isAny())){
				builder.append(Keyword.Common.ANY);
			}
		}

		if(Boolean.TRUE.equals(isWithCoord())){
			builder.append(Keyword.Geo.WITHCOORD);
		}

		if(Boolean.TRUE.equals(isWithDist())){
			builder.append(Keyword.Geo.WITHDIST);
		}

		if(Boolean.TRUE.equals(isWithHash())){
			builder.append(Keyword.Geo.WITHHASH);
		}

		return builder.toString();
	}

}
