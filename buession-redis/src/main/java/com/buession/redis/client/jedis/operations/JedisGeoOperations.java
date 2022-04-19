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

import com.buession.lang.Geo;
import com.buession.redis.client.connection.jedis.JedisConnection;
import com.buession.redis.client.jedis.JedisStandaloneClient;
import com.buession.redis.core.GeoRadius;
import com.buession.redis.core.GeoUnit;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.internal.convert.jedis.params.GeoRadiusArgumentConverter;
import com.buession.redis.core.internal.convert.jedis.params.GeoUnitConverter;
import com.buession.redis.core.internal.convert.jedis.response.GeoCoordinateConverter;
import com.buession.redis.core.internal.convert.jedis.response.GeoRadiusResponseConverter;
import redis.clients.jedis.GeoCoordinate;
import redis.clients.jedis.params.GeoRadiusParam;

import java.util.List;
import java.util.Map;

/**
 * Jedis 单机模式地理位置命令操作
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public final class JedisGeoOperations extends AbstractGeoOperations<JedisConnection> {

	public JedisGeoOperations(final JedisStandaloneClient client){
		super(client);
	}

	@Override
	public long geoAdd(final String key, final String member, final double longitude, final double latitude){
		final CommandArguments args = CommandArguments.create("key", key).put("member", member)
				.put("longitude", longitude).put("latitude", latitude);
		final JedisCommand<Long> command = JedisCommand.<Long>create(ProtocolCommand.GEOADD)
				.general((cmd)->cmd.geoadd(key, longitude, latitude, member))
				.pipeline((cmd)->cmd.geoadd(key, longitude, latitude, member))
				.transaction((cmd)->cmd.geoadd(key, longitude, latitude, member));
		return execute(command, args);
	}

	@Override
	public long geoAdd(final byte[] key, final byte[] member, final double longitude, final double latitude){
		final CommandArguments args = CommandArguments.create("key", key).put("member", member)
				.put("longitude", longitude).put("latitude", latitude);
		final JedisCommand<Long> command = JedisCommand.<Long>create(ProtocolCommand.GEOADD)
				.general((cmd)->cmd.geoadd(key, longitude, latitude, member))
				.pipeline((cmd)->cmd.geoadd(key, longitude, latitude, member))
				.transaction((cmd)->cmd.geoadd(key, longitude, latitude, member));
		return execute(command, args);
	}

	@Override
	public long geoAdd(final String key, final Map<String, Geo> memberCoordinates){
		final CommandArguments args = CommandArguments.create("key", key).put("memberCoordinates", memberCoordinates);
		final Map<String, GeoCoordinate> geoCoordinates = com.buession.redis.core.internal.convert.jedis.params.GeoConverter.STRING_MAP_CONVERTER.convert(
				memberCoordinates);
		final JedisCommand<Long> command = JedisCommand.<Long>create(ProtocolCommand.GEOADD)
				.general((cmd)->cmd.geoadd(key, geoCoordinates)).pipeline((cmd)->cmd.geoadd(key, geoCoordinates))
				.transaction((cmd)->cmd.geoadd(key, geoCoordinates));
		return execute(command, args);
	}

	@Override
	public long geoAdd(final byte[] key, final Map<byte[], Geo> memberCoordinates){
		final CommandArguments args = CommandArguments.create("key", key).put("memberCoordinates", memberCoordinates);
		final Map<byte[], GeoCoordinate> geoCoordinates = com.buession.redis.core.internal.convert.jedis.params.GeoConverter.BINARY_MAP_CONVERTER.convert(
				memberCoordinates);
		final JedisCommand<Long> command = JedisCommand.<Long>create(ProtocolCommand.GEOADD)
				.general((cmd)->cmd.geoadd(key, geoCoordinates)).pipeline((cmd)->cmd.geoadd(key, geoCoordinates))
				.transaction((cmd)->cmd.geoadd(key, geoCoordinates));
		return execute(command, args);
	}

	@Override
	public List<String> geoHash(final String key, final String... members){
		final CommandArguments args = CommandArguments.create("key", key).put("members", members);
		final JedisCommand<List<String>> command = JedisCommand.<List<String>>create(ProtocolCommand.GEOHASH)
				.general((cmd)->cmd.geohash(key, members)).pipeline((cmd)->cmd.geohash(key, members))
				.transaction((cmd)->cmd.geohash(key, members));
		return execute(command, args);
	}

	@Override
	public List<byte[]> geoHash(final byte[] key, final byte[]... members){
		final CommandArguments args = CommandArguments.create("key", key).put("members", members);
		final JedisCommand<List<byte[]>> command = JedisCommand.<List<byte[]>>create(ProtocolCommand.GEOHASH)
				.general((cmd)->cmd.geohash(key, members)).pipeline((cmd)->cmd.geohash(key, members))
				.transaction((cmd)->cmd.geohash(key, members));
		return execute(command, args);
	}

	@Override
	public List<Geo> geoPos(final String key, final String... members){
		final CommandArguments args = CommandArguments.create("key", key).put("members", members);
		final JedisCommand<List<Geo>> command = JedisCommand.<List<Geo>>create(ProtocolCommand.GEOPOS)
				.general((cmd)->cmd.geopos(key, members), GeoCoordinateConverter.LIST_CONVERTER)
				.pipeline((cmd)->cmd.geopos(key, members), GeoCoordinateConverter.LIST_CONVERTER)
				.transaction((cmd)->cmd.geopos(key, members), GeoCoordinateConverter.LIST_CONVERTER);
		return execute(command, args);
	}

	@Override
	public List<Geo> geoPos(final byte[] key, final byte[]... members){
		final CommandArguments args = CommandArguments.create("key", key).put("members", members);
		final JedisCommand<List<Geo>> command = JedisCommand.<List<Geo>>create(ProtocolCommand.GEOPOS)
				.general((cmd)->cmd.geopos(key, members), GeoCoordinateConverter.LIST_CONVERTER)
				.pipeline((cmd)->cmd.geopos(key, members), GeoCoordinateConverter.LIST_CONVERTER)
				.transaction((cmd)->cmd.geopos(key, members), GeoCoordinateConverter.LIST_CONVERTER);
		return execute(command, args);
	}

	@Override
	public double geoDist(final String key, final String member1, final String member2){
		final CommandArguments args = CommandArguments.create("key", key).put("member1", member1)
				.put("member2", member2);
		final JedisCommand<Double> command = JedisCommand.<Double>create(
						ProtocolCommand.GEODIST).general((cmd)->cmd.geodist(key, member1, member2))
				.pipeline((cmd)->cmd.geodist(key, member1, member2))
				.transaction((cmd)->cmd.geodist(key, member1, member2));
		return execute(command, args);
	}

	@Override
	public double geoDist(final byte[] key, final byte[] member1, final byte[] member2){
		final CommandArguments args = CommandArguments.create("key", key).put("member1", member1)
				.put("member2", member2);
		final JedisCommand<Double> command = JedisCommand.<Double>create(
						ProtocolCommand.GEODIST).general((cmd)->cmd.geodist(key, member1, member2))
				.pipeline((cmd)->cmd.geodist(key, member1, member2))
				.transaction((cmd)->cmd.geodist(key, member1, member2));
		return execute(command, args);
	}

	@Override
	public double geoDist(final String key, final String member1, final String member2, final GeoUnit unit){
		final CommandArguments args = CommandArguments.create("key", key).put("member1", member1)
				.put("member2", member2).put("unit", unit);
		final redis.clients.jedis.args.GeoUnit geoUnit = GeoUnitConverter.INSTANCE.convert(unit);
		final JedisCommand<Double> command = JedisCommand.<Double>create(
						ProtocolCommand.GEODIST).general((cmd)->cmd.geodist(key, member1, member2, geoUnit))
				.pipeline((cmd)->cmd.geodist(key, member1, member2, geoUnit))
				.transaction((cmd)->cmd.geodist(key, member1, member2, geoUnit));
		return execute(command, args);
	}

	@Override
	public double geoDist(final byte[] key, final byte[] member1, final byte[] member2, final GeoUnit unit){
		final CommandArguments args = CommandArguments.create("key", key).put("member1", member1)
				.put("member2", member2).put("unit", unit);
		final redis.clients.jedis.args.GeoUnit geoUnit = GeoUnitConverter.INSTANCE.convert(unit);
		final JedisCommand<Double> command = JedisCommand.<Double>create(
						ProtocolCommand.GEODIST).general((cmd)->cmd.geodist(key, member1, member2, geoUnit))
				.pipeline((cmd)->cmd.geodist(key, member1, member2, geoUnit))
				.transaction((cmd)->cmd.geodist(key, member1, member2, geoUnit));
		return execute(command, args);
	}

	@Override
	public List<GeoRadius> geoRadius(final String key, final double longitude, final double latitude,
									 final double radius, final GeoUnit unit){
		final CommandArguments args = CommandArguments.create("key", key).put("longitude", longitude)
				.put("latitude", latitude).put("radius", radius).put("unit", unit);
		final redis.clients.jedis.args.GeoUnit geoUnit = GeoUnitConverter.INSTANCE.convert(unit);
		final JedisCommand<List<GeoRadius>> command = JedisCommand.<List<GeoRadius>>create(
						ProtocolCommand.GEORADIUS)
				.general((cmd)->cmd.georadius(key, longitude, latitude, radius, geoUnit),
						GeoRadiusResponseConverter.LIST_CONVERTER)
				.pipeline((cmd)->cmd.georadius(key, longitude, latitude, radius, geoUnit),
						GeoRadiusResponseConverter.LIST_CONVERTER)
				.transaction((cmd)->cmd.georadius(key, longitude, latitude, radius, geoUnit),
						GeoRadiusResponseConverter.LIST_CONVERTER);
		return execute(command, args);
	}

	@Override
	public List<GeoRadius> geoRadius(final byte[] key, final double longitude, final double latitude,
									 final double radius, final GeoUnit unit){
		final CommandArguments args = CommandArguments.create("key", key).put("longitude", longitude)
				.put("latitude", latitude).put("radius", radius).put("unit", unit);
		final redis.clients.jedis.args.GeoUnit geoUnit = GeoUnitConverter.INSTANCE.convert(unit);
		final JedisCommand<List<GeoRadius>> command = JedisCommand.<List<GeoRadius>>create(
						ProtocolCommand.GEORADIUS)
				.general((cmd)->cmd.georadius(key, longitude, latitude, radius, geoUnit),
						GeoRadiusResponseConverter.LIST_CONVERTER)
				.pipeline((cmd)->cmd.georadius(key, longitude, latitude, radius, geoUnit),
						GeoRadiusResponseConverter.LIST_CONVERTER)
				.transaction((cmd)->cmd.georadius(key, longitude, latitude, radius, geoUnit),
						GeoRadiusResponseConverter.LIST_CONVERTER);
		return execute(command, args);
	}

	@Override
	public List<GeoRadius> geoRadius(final String key, final double longitude, final double latitude,
									 final double radius, final GeoUnit unit,
									 final GeoRadiusArgument geoRadiusArgument){
		final CommandArguments args = CommandArguments.create("key", key).put("longitude", longitude)
				.put("latitude", latitude).put("radius", radius).put("unit", unit)
				.put("geoRadiusArgument", geoRadiusArgument);
		final redis.clients.jedis.args.GeoUnit geoUnit = GeoUnitConverter.INSTANCE.convert(unit);
		final GeoRadiusParam param = GeoRadiusArgumentConverter.INSTANCE.convert(geoRadiusArgument);
		final JedisCommand<List<GeoRadius>> command = JedisCommand.<List<GeoRadius>>create(
						ProtocolCommand.GEORADIUS)
				.general((cmd)->cmd.georadius(key, longitude, latitude, radius, geoUnit, param),
						GeoRadiusResponseConverter.LIST_CONVERTER)
				.pipeline((cmd)->cmd.georadius(key, longitude, latitude, radius, geoUnit, param),
						GeoRadiusResponseConverter.LIST_CONVERTER)
				.transaction((cmd)->cmd.georadius(key, longitude, latitude, radius, geoUnit, param),
						GeoRadiusResponseConverter.LIST_CONVERTER);
		return execute(command, args);
	}

	@Override
	public List<GeoRadius> geoRadius(final byte[] key, final double longitude, final double latitude,
									 final double radius, final GeoUnit unit,
									 final GeoRadiusArgument geoRadiusArgument){
		final CommandArguments args = CommandArguments.create("key", key).put("longitude", longitude)
				.put("latitude", latitude).put("radius", radius).put("unit", unit)
				.put("geoRadiusArgument", geoRadiusArgument);
		final redis.clients.jedis.args.GeoUnit geoUnit = GeoUnitConverter.INSTANCE.convert(unit);
		final GeoRadiusParam param = GeoRadiusArgumentConverter.INSTANCE.convert(geoRadiusArgument);
		final JedisCommand<List<GeoRadius>> command = JedisCommand.<List<GeoRadius>>create(
						ProtocolCommand.GEORADIUS)
				.general((cmd)->cmd.georadius(key, longitude, latitude, radius, geoUnit, param),
						GeoRadiusResponseConverter.LIST_CONVERTER)
				.pipeline((cmd)->cmd.georadius(key, longitude, latitude, radius, geoUnit, param),
						GeoRadiusResponseConverter.LIST_CONVERTER)
				.transaction((cmd)->cmd.georadius(key, longitude, latitude, radius, geoUnit, param),
						GeoRadiusResponseConverter.LIST_CONVERTER);
		return execute(command, args);
	}

	@Override
	public List<GeoRadius> geoRadiusRo(final String key, final double longitude, final double latitude,
									   final double radius, final GeoUnit unit){
		final CommandArguments args = CommandArguments.create("key", key).put("longitude", longitude)
				.put("latitude", latitude).put("radius", radius).put("unit", unit);
		final redis.clients.jedis.args.GeoUnit geoUnit = GeoUnitConverter.INSTANCE.convert(unit);
		final JedisCommand<List<GeoRadius>> command = JedisCommand.<List<GeoRadius>>create(
						ProtocolCommand.GEORADIUS_RO)
				.general((cmd)->cmd.georadiusReadonly(key, longitude, latitude, radius, geoUnit),
						GeoRadiusResponseConverter.LIST_CONVERTER)
				.pipeline((cmd)->cmd.georadiusReadonly(key, longitude, latitude, radius, geoUnit),
						GeoRadiusResponseConverter.LIST_CONVERTER)
				.transaction((cmd)->cmd.georadiusReadonly(key, longitude, latitude, radius, geoUnit),
						GeoRadiusResponseConverter.LIST_CONVERTER);
		return execute(command, args);
	}

	@Override
	public List<GeoRadius> geoRadiusRo(final byte[] key, final double longitude, final double latitude,
									   final double radius, final GeoUnit unit){
		final CommandArguments args = CommandArguments.create("key", key).put("longitude", longitude)
				.put("latitude", latitude).put("radius", radius).put("unit", unit);
		final redis.clients.jedis.args.GeoUnit geoUnit = GeoUnitConverter.INSTANCE.convert(unit);
		final JedisCommand<List<GeoRadius>> command = JedisCommand.<List<GeoRadius>>create(
						ProtocolCommand.GEORADIUS_RO)
				.general((cmd)->cmd.georadiusReadonly(key, longitude, latitude, radius, geoUnit),
						GeoRadiusResponseConverter.LIST_CONVERTER)
				.pipeline((cmd)->cmd.georadiusReadonly(key, longitude, latitude, radius, geoUnit),
						GeoRadiusResponseConverter.LIST_CONVERTER)
				.transaction((cmd)->cmd.georadiusReadonly(key, longitude, latitude, radius, geoUnit),
						GeoRadiusResponseConverter.LIST_CONVERTER);
		return execute(command, args);
	}

	@Override
	public List<GeoRadius> geoRadiusRo(final String key, final double longitude, final double latitude,
									   final double radius, final GeoUnit unit,
									   final GeoRadiusArgument geoRadiusArgument){
		final CommandArguments args = CommandArguments.create("key", key).put("longitude", longitude)
				.put("latitude", latitude).put("radius", radius).put("unit", unit)
				.put("geoRadiusArgument", geoRadiusArgument);
		final redis.clients.jedis.args.GeoUnit geoUnit = GeoUnitConverter.INSTANCE.convert(unit);
		final GeoRadiusParam param = GeoRadiusArgumentConverter.INSTANCE.convert(geoRadiusArgument);
		final JedisCommand<List<GeoRadius>> command = JedisCommand.<List<GeoRadius>>create(
						ProtocolCommand.GEORADIUS_RO)
				.general((cmd)->cmd.georadiusReadonly(key, longitude, latitude, radius, geoUnit, param),
						GeoRadiusResponseConverter.LIST_CONVERTER)
				.pipeline((cmd)->cmd.georadiusReadonly(key, longitude, latitude, radius, geoUnit, param),
						GeoRadiusResponseConverter.LIST_CONVERTER)
				.transaction((cmd)->cmd.georadiusReadonly(key, longitude, latitude, radius, geoUnit, param),
						GeoRadiusResponseConverter.LIST_CONVERTER);
		return execute(command, args);
	}

	@Override
	public List<GeoRadius> geoRadiusRo(final byte[] key, final double longitude, final double latitude,
									   final double radius, final GeoUnit unit,
									   final GeoRadiusArgument geoRadiusArgument){
		final CommandArguments args = CommandArguments.create("key", key).put("longitude", longitude)
				.put("latitude", latitude).put("radius", radius).put("unit", unit)
				.put("geoRadiusArgument", geoRadiusArgument);
		final redis.clients.jedis.args.GeoUnit geoUnit = GeoUnitConverter.INSTANCE.convert(unit);
		final GeoRadiusParam param = GeoRadiusArgumentConverter.INSTANCE.convert(geoRadiusArgument);
		final JedisCommand<List<GeoRadius>> command = JedisCommand.<List<GeoRadius>>create(
						ProtocolCommand.GEORADIUS_RO)
				.general((cmd)->cmd.georadiusReadonly(key, longitude, latitude, radius, geoUnit, param),
						GeoRadiusResponseConverter.LIST_CONVERTER)
				.pipeline((cmd)->cmd.georadiusReadonly(key, longitude, latitude, radius, geoUnit, param),
						GeoRadiusResponseConverter.LIST_CONVERTER)
				.transaction((cmd)->cmd.georadiusReadonly(key, longitude, latitude, radius, geoUnit, param),
						GeoRadiusResponseConverter.LIST_CONVERTER);
		return execute(command, args);
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final String key, final String member, final double radius,
											 final GeoUnit unit){
		final CommandArguments args = CommandArguments.create("key", key).put("member", member).put("radius", radius)
				.put("unit", unit);
		final redis.clients.jedis.args.GeoUnit geoUnit = GeoUnitConverter.INSTANCE.convert(unit);
		final JedisCommand<List<GeoRadius>> command = JedisCommand.<List<GeoRadius>>create(
						ProtocolCommand.GEORADIUSBYMEMBER)
				.general((cmd)->cmd.georadiusByMember(key, member, radius, geoUnit),
						GeoRadiusResponseConverter.LIST_CONVERTER)
				.pipeline((cmd)->cmd.georadiusByMember(key, member, radius, geoUnit),
						GeoRadiusResponseConverter.LIST_CONVERTER)
				.transaction((cmd)->cmd.georadiusByMember(key, member, radius, geoUnit),
						GeoRadiusResponseConverter.LIST_CONVERTER);
		return execute(command, args);
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final byte[] key, final byte[] member, final double radius,
											 final GeoUnit unit){
		final CommandArguments args = CommandArguments.create("key", key).put("member", member).put("radius", radius)
				.put("unit", unit);
		final redis.clients.jedis.args.GeoUnit geoUnit = GeoUnitConverter.INSTANCE.convert(unit);
		final JedisCommand<List<GeoRadius>> command = JedisCommand.<List<GeoRadius>>create(
						ProtocolCommand.GEORADIUSBYMEMBER)
				.general((cmd)->cmd.georadiusByMember(key, member, radius, geoUnit),
						GeoRadiusResponseConverter.LIST_CONVERTER)
				.pipeline((cmd)->cmd.georadiusByMember(key, member, radius, geoUnit),
						GeoRadiusResponseConverter.LIST_CONVERTER)
				.transaction((cmd)->cmd.georadiusByMember(key, member, radius, geoUnit),
						GeoRadiusResponseConverter.LIST_CONVERTER);
		return execute(command, args);
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final String key, final String member, final double radius,
											 final GeoUnit unit, final GeoRadiusArgument geoRadiusArgument){
		final CommandArguments args = CommandArguments.create("key", key).put("member", member).put("radius", radius)
				.put("unit", unit).put("geoRadiusArgument", geoRadiusArgument);
		final redis.clients.jedis.args.GeoUnit geoUnit = GeoUnitConverter.INSTANCE.convert(unit);
		final GeoRadiusParam param = GeoRadiusArgumentConverter.INSTANCE.convert(geoRadiusArgument);
		final JedisCommand<List<GeoRadius>> command = JedisCommand.<List<GeoRadius>>create(
						ProtocolCommand.GEORADIUSBYMEMBER)
				.general((cmd)->cmd.georadiusByMember(key, member, radius, geoUnit, param),
						GeoRadiusResponseConverter.LIST_CONVERTER)
				.pipeline((cmd)->cmd.georadiusByMember(key, member, radius, geoUnit, param),
						GeoRadiusResponseConverter.LIST_CONVERTER)
				.transaction((cmd)->cmd.georadiusByMember(key, member, radius, geoUnit, param),
						GeoRadiusResponseConverter.LIST_CONVERTER);
		return execute(command, args);
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final byte[] key, final byte[] member, final double radius,
											 final GeoUnit unit, final GeoRadiusArgument geoRadiusArgument){
		final CommandArguments args = CommandArguments.create("key", key).put("member", member).put("radius", radius)
				.put("unit", unit).put("geoRadiusArgument", geoRadiusArgument);
		final redis.clients.jedis.args.GeoUnit geoUnit = GeoUnitConverter.INSTANCE.convert(unit);
		final GeoRadiusParam param = GeoRadiusArgumentConverter.INSTANCE.convert(geoRadiusArgument);
		final JedisCommand<List<GeoRadius>> command = JedisCommand.<List<GeoRadius>>create(
						ProtocolCommand.GEORADIUSBYMEMBER)
				.general((cmd)->cmd.georadiusByMember(key, member, radius, geoUnit, param),
						GeoRadiusResponseConverter.LIST_CONVERTER)
				.pipeline((cmd)->cmd.georadiusByMember(key, member, radius, geoUnit, param),
						GeoRadiusResponseConverter.LIST_CONVERTER)
				.transaction((cmd)->cmd.georadiusByMember(key, member, radius, geoUnit, param),
						GeoRadiusResponseConverter.LIST_CONVERTER);
		return execute(command, args);
	}

	@Override
	public List<GeoRadius> geoRadiusByMemberRo(final String key, final String member, final double radius,
											   final GeoUnit unit){
		final CommandArguments args = CommandArguments.create("key", key).put("member", member).put("radius", radius)
				.put("unit", unit);
		final redis.clients.jedis.args.GeoUnit geoUnit = GeoUnitConverter.INSTANCE.convert(unit);
		final JedisCommand<List<GeoRadius>> command = JedisCommand.<List<GeoRadius>>create(
						ProtocolCommand.GEORADIUSBYMEMBER)
				.general((cmd)->cmd.georadiusByMemberReadonly(key, member, radius, geoUnit),
						GeoRadiusResponseConverter.LIST_CONVERTER)
				.pipeline((cmd)->cmd.georadiusByMemberReadonly(key, member, radius, geoUnit),
						GeoRadiusResponseConverter.LIST_CONVERTER)
				.transaction((cmd)->cmd.georadiusByMemberReadonly(key, member, radius, geoUnit),
						GeoRadiusResponseConverter.LIST_CONVERTER);
		return execute(command, args);
	}

	@Override
	public List<GeoRadius> geoRadiusByMemberRo(final byte[] key, final byte[] member, final double radius,
											   final GeoUnit unit){
		final CommandArguments args = CommandArguments.create("key", key).put("member", member).put("radius", radius)
				.put("unit", unit);
		final redis.clients.jedis.args.GeoUnit geoUnit = GeoUnitConverter.INSTANCE.convert(unit);
		final JedisCommand<List<GeoRadius>> command = JedisCommand.<List<GeoRadius>>create(
						ProtocolCommand.GEORADIUSBYMEMBER)
				.general((cmd)->cmd.georadiusByMemberReadonly(key, member, radius, geoUnit),
						GeoRadiusResponseConverter.LIST_CONVERTER)
				.pipeline((cmd)->cmd.georadiusByMemberReadonly(key, member, radius, geoUnit),
						GeoRadiusResponseConverter.LIST_CONVERTER)
				.transaction((cmd)->cmd.georadiusByMemberReadonly(key, member, radius, geoUnit),
						GeoRadiusResponseConverter.LIST_CONVERTER);
		return execute(command, args);
	}

	@Override
	public List<GeoRadius> geoRadiusByMemberRo(final String key, final String member, final double radius,
											   final GeoUnit unit, final GeoRadiusArgument geoRadiusArgument){
		final CommandArguments args = CommandArguments.create("key", key).put("member", member).put("radius", radius)
				.put("unit", unit).put("geoRadiusArgument", geoRadiusArgument);
		final redis.clients.jedis.args.GeoUnit geoUnit = GeoUnitConverter.INSTANCE.convert(unit);
		final GeoRadiusParam param = GeoRadiusArgumentConverter.INSTANCE.convert(geoRadiusArgument);
		final JedisCommand<List<GeoRadius>> command = JedisCommand.<List<GeoRadius>>create(
						ProtocolCommand.GEORADIUSBYMEMBER)
				.general((cmd)->cmd.georadiusByMemberReadonly(key, member, radius, geoUnit, param),
						GeoRadiusResponseConverter.LIST_CONVERTER)
				.pipeline((cmd)->cmd.georadiusByMemberReadonly(key, member, radius, geoUnit, param),
						GeoRadiusResponseConverter.LIST_CONVERTER)
				.transaction((cmd)->cmd.georadiusByMemberReadonly(key, member, radius, geoUnit, param),
						GeoRadiusResponseConverter.LIST_CONVERTER);
		return execute(command, args);
	}

	@Override
	public List<GeoRadius> geoRadiusByMemberRo(final byte[] key, final byte[] member, final double radius,
											   final GeoUnit unit, final GeoRadiusArgument geoRadiusArgument){
		final CommandArguments args = CommandArguments.create("key", key).put("member", member).put("radius", radius)
				.put("unit", unit).put("geoRadiusArgument", geoRadiusArgument);
		final redis.clients.jedis.args.GeoUnit geoUnit = GeoUnitConverter.INSTANCE.convert(unit);
		final GeoRadiusParam param = GeoRadiusArgumentConverter.INSTANCE.convert(geoRadiusArgument);
		final JedisCommand<List<GeoRadius>> command = JedisCommand.<List<GeoRadius>>create(
						ProtocolCommand.GEORADIUSBYMEMBER)
				.general((cmd)->cmd.georadiusByMemberReadonly(key, member, radius, geoUnit, param),
						GeoRadiusResponseConverter.LIST_CONVERTER)
				.pipeline((cmd)->cmd.georadiusByMemberReadonly(key, member, radius, geoUnit, param),
						GeoRadiusResponseConverter.LIST_CONVERTER)
				.transaction((cmd)->cmd.georadiusByMemberReadonly(key, member, radius, geoUnit, param),
						GeoRadiusResponseConverter.LIST_CONVERTER);
		return execute(command, args);
	}

}
