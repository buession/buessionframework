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
package com.buession.redis.core;

import com.buession.lang.Geo;
import com.buession.redis.utils.ObjectStringBuilder;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Objects;

/**
 * 地理位置半径
 *
 * @param member
 * 		成员名
 * @param distance
 * 		搜索半径
 * @param geo
 * 		经纬度
 *
 * @author Yong.Teng
 */
public record GeoRadius(byte[] member, Double distance, Geo geo) {

	/**
	 * 构造函数
	 *
	 * @param member
	 * 		成员名
	 */
	public GeoRadius(final byte[] member) {
		this(member, null, null);
	}

	/**
	 * 构造函数
	 *
	 * @param member
	 * 		成员名
	 * @param distance
	 * 		搜索半径
	 */
	public GeoRadius(final byte[] member, final Double distance) {
		this(member, distance, null);
	}

	/**
	 * 构造函数
	 *
	 * @param member
	 * 		成员名
	 * @param geo
	 * 		经纬度
	 */
	public GeoRadius(final byte[] member, final Geo geo) {
		this(member, null, geo);
	}

	/**
	 * 返回字符串形式的成员名
	 *
	 * @return 字符串形式的成员名
	 */
	public String getMemberAsString() {
		return new String(member, StandardCharsets.UTF_8);
	}

	@Override
	public int hashCode() {
		int result = Objects.hash(distance, geo);
		result = 31 * result + Arrays.hashCode(member);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj){
			return true;
		}

		if(obj instanceof GeoRadius geoRadius){
			return Double.compare(geoRadius.distance, distance) == 0 && Arrays.equals(member, geoRadius.member) &&
					Objects.equals(geo, geoRadius.geo);
		}

		return false;
	}

	@Override
	public String toString() {
		return ObjectStringBuilder.create()
				.add("member", member)
				.addIfAbsent("distance", distance)
				.addIfAbsent("geo", geo)
				.build();
	}

}
