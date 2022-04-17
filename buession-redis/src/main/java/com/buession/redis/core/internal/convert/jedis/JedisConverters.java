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
import com.buession.redis.core.StreamEntry;
import com.buession.redis.core.Tuple;
import com.buession.redis.core.Type;
import com.buession.redis.core.internal.convert.Converters;
import com.buession.redis.core.internal.convert.RedisServerTimeConverter;
import com.buession.redis.utils.SafeEncoder;
import org.springframework.lang.Nullable;
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

	ClusterResetOptionConverter CLUSTER_RESET_OPTION_CONVERTER = new ClusterResetOptionConverter();

	ClusterFailoverOptionConverter CLUSTER_FAILOVER_OPTION_CONVERTER = new ClusterFailoverOptionConverter();

	ClientTypeConverter CLIENT_TYPE_CONVERTER = new ClientTypeConverter();

	ClientUnblockTypeConverter CLIENT_UNBLOCK_CONVERTER = new ClientUnblockTypeConverter();

	MapConverter<String, Geo, String, GeoCoordinate> STRING_MAP_GEO_CONVERTER = new MapConverter<>(
			(source)->source, (value)->new GeoCoordinate(value.getLongitude(), value.getLatitude()));

	MapConverter<byte[], Geo, byte[], GeoCoordinate> BINARY_MAP_GEO_CONVERTER = new MapConverter<>(
			(source)->source, (value)->new GeoCoordinate(value.getLongitude(), value.getLatitude()));

	GeoUnitConverter GEO_UNIT_CONVERTER = new GeoUnitConverter();

	GeoRadiusArgumentConverter GEO_RADIUS_ARGUMENT_CONVERTER = new GeoRadiusArgumentConverter();

	ExpireOptionConverter EXPIRE_OPTION_CONVERTER = new ExpireOptionConverter();

	RestoreArgumentConverter RESTORE_ARGUMENT_CONVERTER = new RestoreArgumentConverter();

	SortArgumentConverter SORT_ARGUMENT_CONVERTER = new SortArgumentConverter();

	ListPositionConverter LIST_POSITION_CONVERTER = new ListPositionConverter();

	LPosArgumentConverter L_POS_ARGUMENT_CONVERTER = new LPosArgumentConverter();

	DirectionConverter DIRECTION_CONVERTER = new DirectionConverter();

	FlushModeConverter FLUSH_MODE_CONVERTER = new FlushModeConverter();

	GetExArgumentConverter GET_EX_ARGUMENT_CONVERTER = new GetExArgumentConverter();

	SetArgumentConverter SET_ARGUMENT_CONVERTER = new SetArgumentConverter();

	XAddArgumentConverter X_ADD_ARGUMENT_CONVERTER = new XAddArgumentConverter();
	/**
	 * end param converter
	 */

	/**
	 * start result converter
	 */
	ListConverter<Object, ClusterSlot> CLUSTER_SLOTS_CONVERTER = new ListConverter<>(new ClusterSlotConverter());

	ClusterInfoConverter CLUSTER_INFO_RESULT_CONVERTER = new ClusterInfoConverter();

	ClusterNodesConverter CLUSTER_NODES_RESULT_CONVERTER = new ClusterNodesConverter();

	ListConverter<String, RedisClusterServer> CLUSTER_SLAVES_RESULT_CONVERTER = new ListConverter<>(
			new ClusterSlaveConverter());

	ClusterReplicasConverter CLUSTER_REPLICAS_RESULT_CONVERTER = new ClusterReplicasConverter();

	BumpEpochConverter BUMP_EPOCH_RESULT_CONVERTER = new BumpEpochConverter();

	ClientConverter.ClientListExposeConverter CLIENT_LIST_RESULT_CONVERTER = new ClientConverter.ClientListExposeConverter();

	ClientConverter.ClientExposeConverter CLIENT_RESULT_CONVERTER = new ClientConverter.ClientExposeConverter();

	ListConverter<GeoCoordinate, Geo> LIST_GEO_RESULT_CONVERTER = new ListConverter<>(
			(source)->new Geo(source.getLongitude(), source.getLatitude()));

	ListConverter<GeoRadiusResponse, GeoRadius> LIST_GEO_RADIUS_RESULT_CONVERTER = new ListConverter<>(
			(source)->{
				final GeoConverter geoExposeConverter = new GeoConverter();
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
			new ModuleConverter());

	ListConverter<Object, Role> LIST_ROLE_RESULT_CONVERTER = new ListConverter<>(new RoleConverter());

	ListConverter<Slowlog, SlowLog> LIST_SLOW_LOG_RESULT_CONVERTER = new ListConverter<>(new SlowLogConverter());

	RedisServerTimeConverter REDIS_SERVER_TIME_RESULT_CONVERTER = new RedisServerTimeConverter();

	KeyedZSetElementConverter.KeyedZSetElementExposeConverter KEYED_Z_SET_ELEMENT_RESULT_CONVERTER = new KeyedZSetElementConverter.KeyedZSetElementExposeConverter();

	KeyedZSetElementConverter.BinaryDataKeyedZSetElementExposeConverter BINARY_DATA_KEYED_Z_SET_ELEMENT_RESULT_CONVERTER = new KeyedZSetElementConverter.BinaryDataKeyedZSetElementExposeConverter();

	TupleConverter TUPLE_RESULT_CONVERTER = new TupleConverter();

	SetConverter<redis.clients.jedis.resps.Tuple, Tuple> SET_TUPLE_RESULT_CONVERTER = new SetConverter<>(
			TUPLE_RESULT_CONVERTER);

	ListConverter<redis.clients.jedis.resps.Tuple, Tuple> LIST_TUPLE_RESULT_CONVERTER = new ListConverter<>(
			TUPLE_RESULT_CONVERTER);

	StreamEntryConverter STREAM_ENTRY_RESULT_CONVERTER = new StreamEntryConverter();

	Converter<byte[], StreamEntry> BINARY_STREAM_ENTRY_RESULT_CONVERTER = (source)->{
		String id = SafeEncoder.encode(source);
		return new StreamEntry(id);
	};
	/**
	 * end result converter
	 */

}
