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
import com.buession.core.converter.ListConverter;
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
import com.buession.redis.core.StreamEntryId;
import com.buession.redis.core.Tuple;
import com.buession.redis.core.internal.convert.Converters;
import com.buession.redis.core.internal.convert.jedis.params.AclLogConverter;
import com.buession.redis.core.internal.convert.jedis.params.TupleConverter;
import com.buession.redis.core.internal.convert.jedis.response.ClusterSlaveConverter;
import com.buession.redis.core.internal.convert.jedis.response.ClusterSlotConverter;
import com.buession.redis.core.internal.convert.jedis.response.GeoConverter;
import com.buession.redis.core.internal.convert.jedis.response.ModuleConverter;
import com.buession.redis.core.internal.convert.jedis.response.SlowLogConverter;
import com.buession.redis.core.internal.convert.response.RoleConverter;
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
	 * start result converter
	 */

	ClientConverter.ClientListExposeConverter CLIENT_LIST_RESULT_CONVERTER = new ClientConverter.ClientListExposeConverter();

	ListConverter<GeoCoordinate, Geo> LIST_GEO_RESULT_CONVERTER = new ListConverter<>(
			GeoConverter.INSTANCE);

	ListConverter<GeoRadiusResponse, GeoRadius> LIST_GEO_RADIUS_RESULT_CONVERTER = new ListConverter<>(
			(source)->{
				return new GeoRadius(source.getMember(), source.getDistance(),
						GeoConverter.INSTANCE.convert(source.getCoordinate()));
			});

	ScanResultConverter.MapScanResultExposeConverter<String, String> STRING_MAP_SCAN_RESULT_RESULT_CONVERTER = new ScanResultConverter.MapScanResultExposeConverter<>();

	ScanResultConverter.MapScanResultExposeConverter<byte[], byte[]> BINARY_MAP_SCAN_RESULT_RESULT_CONVERTER = new ScanResultConverter.MapScanResultExposeConverter<>();

	ScanResultConverter.ListScanResultExposeConverter<String> STRING_LIST_SCAN_RESULT_RESULT_CONVERTER = new ScanResultConverter.ListScanResultExposeConverter<>();

	ScanResultConverter.ListScanResultExposeConverter<byte[]> BINARY_LIST_SCAN_RESULT_RESULT_CONVERTER = new ScanResultConverter.ListScanResultExposeConverter<>();

	ScanResultConverter.ListTupleScanResultExposeConverter LIST_TUPLE_SCAN_RESULT_RESULT_CONVERTER = new ScanResultConverter.ListTupleScanResultExposeConverter();

	Converter<byte[], ObjectEncoding> BINARY_OBJECT_ENCODING_RESULT_CONVERTER = (value)->EnumUtils.getEnumIgnoreCase(
			ObjectEncoding.class, SafeEncoder.encode(value));

	ListConverter<AccessControlLogEntry, AclLog> LIST_ACL_LOG_RESULT_CONVERTER = new ListConverter<>(
			AclLogConverter.INSTANCE);

	ListConverter<redis.clients.jedis.Module, Module> LIST_MODULE_RESULT_CONVERTER = new ListConverter<>(
			ModuleConverter.INSTANCE);

	ListConverter<Object, Role> LIST_ROLE_RESULT_CONVERTER = new ListConverter<>(RoleConverter.INSTANCE);

	ListConverter<Slowlog, SlowLog> LIST_SLOW_LOG_RESULT_CONVERTER = new ListConverter<>(SlowLogConverter.INSTANCE);

	KeyedZSetElementConverter.BinaryDataKeyedZSetElementExposeConverter BINARY_DATA_KEYED_Z_SET_ELEMENT_RESULT_CONVERTER = new KeyedZSetElementConverter.BinaryDataKeyedZSetElementExposeConverter();

	TupleConverter TUPLE_RESULT_CONVERTER = new TupleConverter();

	SetConverter<redis.clients.jedis.resps.Tuple, Tuple> SET_TUPLE_RESULT_CONVERTER = new SetConverter<>(
			TUPLE_RESULT_CONVERTER);

	ListConverter<redis.clients.jedis.resps.Tuple, Tuple> LIST_TUPLE_RESULT_CONVERTER = new ListConverter<>(
			TUPLE_RESULT_CONVERTER);

	Converter<byte[], StreamEntryId> BINARY_STREAM_ENTRY_RESULT_CONVERTER = (source)->{
		String id = SafeEncoder.encode(source);
		return new StreamEntryId(id);
	};

	StreamEntryIdConverter.MapStreamEntryIdConverter MAP_STREAM_ENTRY_ID_RESULT_CONVERTER = new StreamEntryIdConverter.MapStreamEntryIdConverter();

	StreamEntryConverter.MapStreamEntryConverter MAP_STREAM_ENTRY_RESULT_CONVERTER = new StreamEntryConverter.MapStreamEntryConverter();
	/**
	 * end result converter
	 */

}
