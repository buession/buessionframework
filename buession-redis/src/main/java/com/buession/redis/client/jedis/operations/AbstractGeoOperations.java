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
 * | Copyright @ 2013-2022 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.client.jedis.operations;

import com.buession.core.converter.ListConverter;
import com.buession.core.converter.MapConverter;
import com.buession.lang.Geo;
import com.buession.redis.client.jedis.JedisRedisClient;
import com.buession.redis.client.operations.GeoOperations;
import com.buession.redis.core.GeoRadius;
import com.buession.redis.core.internal.convert.jedis.GeoConverter;
import com.buession.redis.core.internal.convert.jedis.GeoRadiusArgumentConverter;
import com.buession.redis.core.internal.convert.jedis.GeoUnitConverter;
import redis.clients.jedis.GeoCoordinate;
import redis.clients.jedis.GeoRadiusResponse;

/**
 * Jedis 地理位置命令操作抽象类
 *
 * @param <CMD>
 * 		Jedis 原始命令对象
 *
 * @author Yong.Teng
 */
public abstract class AbstractGeoOperations<CMD> extends AbstractJedisRedisOperations<CMD>
		implements GeoOperations<CMD> {

	protected final static MapConverter<String, Geo, String, GeoCoordinate> STRING_MAP_GEO_MAP_JEDIS_CONVERTER = new MapConverter<>(
			(source)->source, (value)->new GeoCoordinate(value.getLongitude(), value.getLatitude()));

	protected final static MapConverter<byte[], Geo, byte[], GeoCoordinate> BINARY_MAP_GEO_MAP_JEDIS_CONVERTER = new MapConverter<>(
			(source)->source, (value)->new GeoCoordinate(value.getLongitude(), value.getLatitude()));

	protected final static GeoUnitConverter.GeoUnitJedisConverter GEO_UNIT_JEDIS_CONVERTER = new GeoUnitConverter.GeoUnitJedisConverter();

	protected final static ListConverter<GeoCoordinate, Geo> LIST_GEO_EXPOSE_CONVERTER = new ListConverter<>(
			(source)->new Geo(source.getLongitude(), source.getLatitude()));

	protected final static ListConverter<GeoRadiusResponse, GeoRadius> LIST_GEO_RADIUS_EXPOSE_CONVERTER = new ListConverter<GeoRadiusResponse, GeoRadius>(
			(source)->{
				GeoConverter.GeoExposeConverter converter = new GeoConverter.GeoExposeConverter();
				return new GeoRadius(source.getMember(), source.getDistance(),
						converter.convert(source.getCoordinate()));
			});

	protected final static GeoRadiusArgumentConverter.GeoRadiusArgumentJedisConverter GEO_RADIUS_ARGUMENT_JEDIS_CONVERTER = new GeoRadiusArgumentConverter.GeoRadiusArgumentJedisConverter();

	public AbstractGeoOperations(final JedisRedisClient client){
		super(client);
	}

}
