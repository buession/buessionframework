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
package com.buession.redis.client.lettuce.operations;

import com.buession.lang.Geo;
import com.buession.redis.client.lettuce.LettuceRedisClient;
import com.buession.redis.client.operations.GeoOperations;
import com.buession.redis.core.GeoRadius;
import com.buession.redis.core.GeoUnit;
import com.buession.redis.utils.SafeEncoder;

import java.util.List;

/**
 * Lettuce 地理位置命令操作抽象类
 *
 * @param <C>
 * 		Redis Client {@link LettuceRedisClient}
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public abstract class AbstractGeoOperations<C extends LettuceRedisClient> extends AbstractLettuceRedisOperations<C>
		implements GeoOperations {

	public AbstractGeoOperations(final C client) {
		super(client);
	}

	@Override
	public Long geoAdd(final String key, final String member, final double longitude, final double latitude) {
		return geoAdd(SafeEncoder.encode(key), SafeEncoder.encode(member), longitude, latitude);
	}

	@Override
	public List<Geo> geoPos(final String key, final String... members) {
		return geoPos(SafeEncoder.encode(key), SafeEncoder.encode(members));
	}

	@Override
	public Double geoDist(final String key, final String member1, final String member2) {
		return geoDist(SafeEncoder.encode(key), SafeEncoder.encode(member1), SafeEncoder.encode(member2));
	}

	@Override
	public Double geoDist(final String key, final String member1, final String member2, final GeoUnit unit) {
		return geoDist(SafeEncoder.encode(key), SafeEncoder.encode(member1), SafeEncoder.encode(member2), unit);
	}

	@Override
	public List<GeoRadius> geoRadius(final String key, final double longitude, final double latitude,
									 final double radius, final GeoUnit unit) {
		return geoRadius(SafeEncoder.encode(key), longitude, latitude, radius, unit);
	}

	@Override
	public List<GeoRadius> geoRadius(final String key, final double longitude, final double latitude,
									 final double radius, final GeoUnit unit,
									 final GeoRadiusArgument geoRadiusArgument) {
		return geoRadius(SafeEncoder.encode(key), longitude, latitude, radius, unit, geoRadiusArgument);
	}

	@Override
	public List<GeoRadius> geoRadiusRo(final String key, final double longitude, final double latitude,
									   final double radius, final GeoUnit unit) {
		return geoRadiusRo(SafeEncoder.encode(key), longitude, latitude, radius, unit);
	}

	@Override
	public List<GeoRadius> geoRadiusRo(final String key, final double longitude, final double latitude,
									   final double radius, final GeoUnit unit,
									   final GeoRadiusArgument geoRadiusArgument) {
		return geoRadiusRo(SafeEncoder.encode(key), longitude, latitude, radius, unit, geoRadiusArgument);
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final String key, final String member, final double radius,
											 final GeoUnit unit) {
		return geoRadiusByMember(SafeEncoder.encode(key), SafeEncoder.encode(member), radius, unit);
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final String key, final String member, final double radius,
											 final GeoUnit unit, final GeoRadiusArgument geoRadiusArgument) {
		return geoRadiusByMember(SafeEncoder.encode(key), SafeEncoder.encode(member), radius, unit, geoRadiusArgument);
	}

	@Override
	public List<GeoRadius> geoRadiusByMemberRo(final String key, final String member, final double radius,
											   final GeoUnit unit) {
		return geoRadiusByMemberRo(SafeEncoder.encode(key), SafeEncoder.encode(member), radius, unit);
	}

	@Override
	public List<GeoRadius> geoRadiusByMemberRo(final String key, final String member, final double radius,
											   final GeoUnit unit, final GeoRadiusArgument geoRadiusArgument) {
		return geoRadiusByMemberRo(SafeEncoder.encode(key), SafeEncoder.encode(member), radius, unit,
				geoRadiusArgument);
	}

}
