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
package com.buession.redis.core.internal.convert.jedis;

import com.buession.core.converter.ListConverter;
import com.buession.core.converter.MapConverter;
import com.buession.lang.Geo;
import com.buession.redis.core.ClusterSlot;
import com.buession.redis.core.GeoRadius;
import com.buession.redis.core.RedisClusterServer;
import com.buession.redis.core.internal.convert.Converters;
import redis.clients.jedis.GeoCoordinate;
import redis.clients.jedis.resps.GeoRadiusResponse;

/**
 * @author Yong.Teng
 * @since 2.0.0
 */
public interface JedisConverters extends Converters {

	/**
	 * start param converter
	 */
	ClusterResetOptionConverter.ClusterResetOptionJedisConverter CLUSTER_RESET_OPTION_CONVERTER = new ClusterResetOptionConverter.ClusterResetOptionJedisConverter();

	ClusterFailoverOptionConverter.ClusterFailoverOptionJedisConverter CLUSTER_FAILOVER_OPTION_CONVERTER = new ClusterFailoverOptionConverter.ClusterFailoverOptionJedisConverter();

	ClientTypeConverter.ClientTypeJedisConverter CLIENT_TYPE_CONVERTER = new ClientTypeConverter.ClientTypeJedisConverter();

	ClientUnblockTypeConverter.ClientUnblockJedisConverter CLIENT_UNBLOCK_CONVERTER = new ClientUnblockTypeConverter.ClientUnblockJedisConverter();

	MapConverter<String, Geo, String, GeoCoordinate> STRING_MAP_GEO_CONVERTER = new MapConverter<>(
			(source)->source, (value)->new GeoCoordinate(value.getLongitude(), value.getLatitude()));

	MapConverter<byte[], Geo, byte[], GeoCoordinate> BINARY_MAP_GEO_CONVERTER = new MapConverter<>(
			(source)->source, (value)->new GeoCoordinate(value.getLongitude(), value.getLatitude()));

	GeoUnitConverter.GeoUnitJedisConverter GEO_UNIT_CONVERTER = new GeoUnitConverter.GeoUnitJedisConverter();

	GeoRadiusArgumentConverter.GeoRadiusArgumentJedisConverter GEO_RADIUS_ARGUMENT_CONVERTER = new GeoRadiusArgumentConverter.GeoRadiusArgumentJedisConverter();
	/**
	 * end param converter
	 */

	/**
	 * start result converter
	 */
	ListConverter<Object, ClusterSlot> CLUSTER_SLOTS_CONVERTER = new ListConverter<>(
			new ClusterSlotConverter.ClusterSlotExposeConverter());

	ClusterInfoConverter.ClusterInfoExposeConverter CLUSTER_INFO_RESULT_CONVERTER = new ClusterInfoConverter.ClusterInfoExposeConverter();

	ClusterNodesConverter.ClusterNodesExposeConverter CLUSTER_NODES_RESULT_CONVERTER = new ClusterNodesConverter.ClusterNodesExposeConverter();

	ListConverter<String, RedisClusterServer> CLUSTER_SLAVES_RESULT_CONVERTER = new ListConverter<>(
			new ClusterSlaveConverter.ClusterSlaveExposeConverter());

	ClusterReplicasConverter.ClusterReplicasExposeConverter CLUSTER_REPLICAS_RESULT_CONVERTER = new ClusterReplicasConverter.ClusterReplicasExposeConverter();

	BumpEpochConverter.BumpEpochExposeConverter BUMP_EPOCH_RESULT_CONVERTER = new BumpEpochConverter.BumpEpochExposeConverter();

	ClientConverter.ClientListExposeConverter CLIENT_LIST_RESULT_CONVERTER = new ClientConverter.ClientListExposeConverter();

	ClientConverter.ClientExposeConverter CLIENT_RESULT_CONVERTER = new ClientConverter.ClientExposeConverter();

	ListConverter<GeoCoordinate, Geo> LIST_GEO_RESULT_CONVERTER = new ListConverter<>(
			(source)->new Geo(source.getLongitude(), source.getLatitude()));

	ListConverter<GeoRadiusResponse, GeoRadius> LIST_GEO_RADIUS_RESULT_CONVERTER = new ListConverter<>(
			(source)->{
				final GeoConverter.GeoExposeConverter geoExposeConverter = new GeoConverter.GeoExposeConverter();
				return new GeoRadius(source.getMember(), source.getDistance(),
						geoExposeConverter.convert(source.getCoordinate()));
			});

	ScanResultConverter.MapScanResultExposeConverter<String, String> STRING_MAP_SCAN_RESULT_RESULT_CONVERTER = new ScanResultConverter.MapScanResultExposeConverter<>();

	ScanResultConverter.MapScanResultExposeConverter<byte[], byte[]> BINARY_MAP_SCAN_RESULT_RESULT_CONVERTER = new ScanResultConverter.MapScanResultExposeConverter<>();
	/**
	 * end result converter
	 */

}