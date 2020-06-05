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
package com.buession.redis.client.jedis.operations;

import com.buession.redis.client.operations.BinaryGeoRedisOperations;
import com.buession.redis.core.GeoRadius;
import com.buession.redis.core.GeoUnit;
import com.buession.redis.core.command.GeoCommands;

import java.util.List;

/**
 * @author Yong.Teng
 */
public interface JedisBinaryGeoRedisOperations extends BinaryGeoRedisOperations {

	@Override
	default List<GeoRadius> geoRadius(final byte[] key, final double longitude, final double latitude,
			final double radius){
		return geoRadius(key, longitude, longitude, radius, GeoUnit.M);
	}

	@Override
	default List<GeoRadius> geoRadius(final byte[] key, final double longitude, final double latitude,
			final double radius, final GeoCommands.GeoArgument geoArgument){
		return geoRadius(key, longitude, latitude, radius, GeoUnit.M, geoArgument);
	}

	@Override
	default List<GeoRadius> geoRadiusByMember(final byte[] key, final byte[] member, final double radius){
		return geoRadiusByMember(key, member, radius, GeoUnit.M);
	}

	@Override
	default List<GeoRadius> geoRadiusByMember(final byte[] key, final byte[] member, final double radius,
			final GeoCommands.GeoArgument geoArgument){
		return geoRadiusByMember(key, member, radius, GeoUnit.M, geoArgument);
	}

}
