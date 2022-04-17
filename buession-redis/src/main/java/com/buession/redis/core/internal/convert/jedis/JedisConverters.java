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

import com.buession.core.converter.Converter;
import com.buession.core.converter.EnumConverter;
import com.buession.core.converter.ListConverter;
import com.buession.core.converter.MapConverter;
import com.buession.core.converter.SetConverter;
import com.buession.core.utils.EnumUtils;
import com.buession.lang.Geo;
import com.buession.redis.core.AclLog;
import com.buession.redis.core.ClusterSlot;
import com.buession.redis.core.GeoRadius;
import com.buession.redis.core.Module;
import com.buession.redis.core.ObjectEncoding;
import com.buession.redis.core.RedisClusterServer;
import com.buession.redis.core.Role;
import com.buession.redis.core.SlowLog;
import com.buession.redis.core.Tuple;
import com.buession.redis.core.Type;
import com.buession.redis.core.internal.convert.Converters;
import com.buession.redis.core.internal.convert.RedisServerTimeConverter;
import com.buession.redis.utils.SafeEncoder;
import redis.clients.jedis.GeoCoordinate;
import redis.clients.jedis.resps.AccessControlLogEntry;
import redis.clients.jedis.resps.GeoRadiusResponse;
import redis.clients.jedis.resps.Slowlog;

/**
 * @author Yong.Teng
 * @since 2.0.0
 */
public interface JedisConverters extends Converters {

	/**
	 * start param converter
	 */
	BitCountOptionConverter BIT_COUNT_OPTION_CONVERTER = new BitCountOptionConverter();

	BitOperationConverter BIT_OPERATION_CONVERTER = new BitOperationConverter();

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

	ExpireOptionConverter.ExpireOptionJedisConverter EXPIRE_OPTION_CONVERTER = new ExpireOptionConverter.ExpireOptionJedisConverter();

	RestoreArgumentConverter.RestoreArgumentJedisConverter RESTORE_ARGUMENT_CONVERTER = new RestoreArgumentConverter.RestoreArgumentJedisConverter();

	SortArgumentConverter.SortArgumentJedisConverter SORT_ARGUMENT_CONVERTER = new SortArgumentConverter.SortArgumentJedisConverter();

	ListPositionConverter.ListPositionJedisConverter LIST_POSITION_CONVERTER = new ListPositionConverter.ListPositionJedisConverter();

	LPosArgumentConverter.LPosArgumentJedisConverter L_POS_ARGUMENT_CONVERTER = new LPosArgumentConverter.LPosArgumentJedisConverter();

	DirectionConverter.DirectionJedisConverter DIRECTION_CONVERTER = new DirectionConverter.DirectionJedisConverter();

	FlushModeConverter.FlushModeJedisConverter FLUSH_MODE_CONVERTER = new FlushModeConverter.FlushModeJedisConverter();

	GetExArgumentConverter.GetExArgumentJedisConverter GET_EX_ARGUMENT_CONVERTER = new GetExArgumentConverter.GetExArgumentJedisConverter();

	SetArgumentConverter.SetArgumentJedisConverter SET_ARGUMENT_CONVERTER = new SetArgumentConverter.SetArgumentJedisConverter();
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

	ScanResultConverter.ListScanResultExposeConverter<String> STRING_LIST_SCAN_RESULT_RESULT_CONVERTER = new ScanResultConverter.ListScanResultExposeConverter<>();

	ScanResultConverter.ListScanResultExposeConverter<byte[]> BINARY_LIST_SCAN_RESULT_RESULT_CONVERTER = new ScanResultConverter.ListScanResultExposeConverter<>();

	ScanResultConverter.ListTupleScanResultExposeConverter LIST_TUPLE_SCAN_RESULT_RESULT_CONVERTER = new ScanResultConverter.ListTupleScanResultExposeConverter();

	EnumConverter<Type> TYPE_RESULT_CONVERTER = new EnumConverter<>(Type.class);

	EnumConverter<ObjectEncoding> STRING_OBJECT_ENCODING_RESULT_CONVERTER = new EnumConverter<>(ObjectEncoding.class);

	Converter<byte[], ObjectEncoding> BINARY_OBJECT_ENCODING_RESULT_CONVERTER = (value)->EnumUtils.getEnumIgnoreCase(
			ObjectEncoding.class, SafeEncoder.encode(value));

	AclUserConverter ACL_USER_RESULT_CONVERTER = new AclUserConverter();

	ListConverter<AccessControlLogEntry, AclLog> LIST_ACL_LOG_RESULT_CONVERTER = new ListConverter<>(
			new AclLogConverter());

	ListConverter<redis.clients.jedis.Module, Module> LIST_MODULE_RESULT_CONVERTER = new ListConverter<>(
			new ModuleConverter.ModuleExposeConverter());

	ListConverter<Object, Role> LIST_ROLE_RESULT_CONVERTER = new ListConverter<>(
			new RoleConverter.RoleExposeConverter());

	ListConverter<Slowlog, SlowLog> LIST_SLOW_LOG_RESULT_CONVERTER = new ListConverter<>(
			new SlowLogConverter());

	RedisServerTimeConverter REDIS_SERVER_TIME_RESULT_CONVERTER = new RedisServerTimeConverter();

	KeyedZSetElementConverter.KeyedZSetElementExposeConverter KEYED_Z_SET_ELEMENT_RESULT_CONVERTER = new KeyedZSetElementConverter.KeyedZSetElementExposeConverter();

	KeyedZSetElementConverter.BinaryDataKeyedZSetElementExposeConverter BINARY_DATA_KEYED_Z_SET_ELEMENT_RESULT_CONVERTER = new KeyedZSetElementConverter.BinaryDataKeyedZSetElementExposeConverter();

	TupleConverter.TupleExposeConverter TUPLE_RESULT_CONVERTER = new TupleConverter.TupleExposeConverter();

	SetConverter<redis.clients.jedis.resps.Tuple, Tuple> SET_TUPLE_RESULT_CONVERTER = new SetConverter<>(
			TUPLE_RESULT_CONVERTER);

	ListConverter<redis.clients.jedis.resps.Tuple, Tuple> LIST_TUPLE_RESULT_CONVERTER = new ListConverter<>(
			TUPLE_RESULT_CONVERTER);
	/**
	 * end result converter
	 */

}
