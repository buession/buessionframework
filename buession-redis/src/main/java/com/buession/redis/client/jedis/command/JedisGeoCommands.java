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
package com.buession.redis.client.jedis.command;

import com.buession.core.converter.ListConverter;
import com.buession.lang.Geo;
import com.buession.lang.KeyValue;
import com.buession.redis.client.jedis.JedisRedisClient;
import com.buession.redis.core.GeoRadius;
import com.buession.redis.core.GeoUnit;
import com.buession.redis.core.command.Command;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.GeoCommands;
import com.buession.redis.core.command.args.GeoAddArgument;
import com.buession.redis.core.command.args.GeoRadiusArgument;
import com.buession.redis.core.command.args.GeoSearchArgument;
import com.buession.redis.core.internal.convert.jedis.params.GeoAddArgumentConverter;
import com.buession.redis.core.internal.convert.jedis.params.GeoConverter;
import com.buession.redis.core.internal.convert.jedis.params.GeoRadiusArgumentConverter;
import com.buession.redis.core.internal.convert.jedis.params.GeoSearchArgumentConverter;
import com.buession.redis.core.internal.convert.jedis.params.GeoUnitConverter;
import com.buession.redis.core.internal.convert.jedis.response.GeoCoordinateConverter;
import com.buession.redis.core.internal.convert.jedis.response.GeoRadiusResponseConverter;
import com.buession.redis.utils.SafeEncoder;
import redis.clients.jedis.GeoCoordinate;
import redis.clients.jedis.UnifiedJedis;
import redis.clients.jedis.params.GeoSearchParam;
import redis.clients.jedis.resps.GeoRadiusResponse;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Jedis 地理位置命令
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public final class JedisGeoCommands extends AbstractJedisRedisCommands implements GeoCommands {

	public JedisGeoCommands(final JedisRedisClient client) {
		super(client);
	}

	@Override
	public Long geoAdd(final String key, final String member, final double longitude, final double latitude) {
		final CommandArguments args = CommandArguments.create(key).add(longitude, latitude).add(member);
		return geoAdd(args, (cmd)->cmd.geoadd(rawKey(key), longitude, latitude, member));
	}

	@Override
	public Long geoAdd(final byte[] key, final byte[] member, final double longitude, final double latitude) {
		final CommandArguments args = CommandArguments.create(key).add(longitude, latitude).add(member);
		return geoAdd(args, (cmd)->cmd.geoadd(rawKey(key), longitude, latitude, member));
	}

	@Override
	public Long geoAdd(final String key, final KeyValue<String, Geo>... memberCoordinates) {
		final CommandArguments args = CommandArguments.create(key).add(memberCoordinates);
		return geoAdd(args, (cmd)->cmd.geoadd(rawKey(key), createMemberCoordinateMap(memberCoordinates)));
	}

	@Override
	public Long geoAdd(final byte[] key, final KeyValue<byte[], Geo>... memberCoordinates) {
		final CommandArguments args = CommandArguments.create(key).add(memberCoordinates);
		return geoAdd(args, (cmd)->cmd.geoadd(rawKey(key), createBinaryMemberCoordinateMap(memberCoordinates)));
	}

	@Override
	public Long geoAdd(final String key, final GeoAddArgument argument, final String member, final double longitude,
					   final double latitude) {
		return geoAdd(key, argument, new KeyValue<>(member, new Geo(longitude, latitude)));
	}

	@Override
	public Long geoAdd(final byte[] key, final GeoAddArgument argument, final byte[] member, final double longitude,
					   final double latitude) {
		return geoAdd(key, argument, new KeyValue<>(member, new Geo(longitude, latitude)));
	}

	@Override
	public Long geoAdd(final String key, final GeoAddArgument argument,
					   final KeyValue<String, Geo>... memberCoordinates) {
		final CommandArguments args = CommandArguments.create(key).add(argument).add(memberCoordinates);
		final GeoAddArgumentConverter geoAddArgumentConverter = new GeoAddArgumentConverter();
		return geoAdd(args, (cmd)->cmd.geoadd(rawKey(key), geoAddArgumentConverter.convert(argument),
				createMemberCoordinateMap(memberCoordinates)));
	}

	@Override
	public Long geoAdd(final byte[] key, final GeoAddArgument argument,
					   final KeyValue<byte[], Geo>... memberCoordinates) {
		final CommandArguments args = CommandArguments.create(key).add(argument).add(memberCoordinates);
		final GeoAddArgumentConverter geoAddArgumentConverter = new GeoAddArgumentConverter();
		return geoAdd(args, (cmd)->cmd.geoadd(rawKey(key), geoAddArgumentConverter.convert(argument),
				createBinaryMemberCoordinateMap(memberCoordinates)));
	}

	@Override
	public Double geoDist(final String key, final String member1, final String member2) {
		final CommandArguments args = CommandArguments.create(key).add(member1, member2);
		return geoDist(args, (cmd)->cmd.geodist(rawKey(key), member1, member2));
	}

	@Override
	public Double geoDist(final byte[] key, final byte[] member1, final byte[] member2) {
		final CommandArguments args = CommandArguments.create(key).add(member1, member2);
		return geoDist(args, (cmd)->cmd.geodist(rawKey(key), member1, member2));
	}

	@Override
	public Double geoDist(final String key, final String member1, final String member2, final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(key).add(member1, member2).add(unit);
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		return geoDist(args, (cmd)->cmd.geodist(rawKey(key), member1, member2, geoUnitConverter.convert(unit)));
	}

	@Override
	public Double geoDist(final byte[] key, final byte[] member1, final byte[] member2, final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(key).add(member1, member2).add(unit);
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		return geoDist(args, (cmd)->cmd.geodist(rawKey(key), member1, member2, geoUnitConverter.convert(unit)));
	}

	@Override
	public List<String> geoHash(final String key, final String... members) {
		final CommandArguments args = CommandArguments.create(key).add(members);
		return executeCommand(Command.GEOHASH, args, (cmd)->cmd.geohash(rawKey(key), members), (v)->v);
	}

	@Override
	public List<byte[]> geoHash(final byte[] key, final byte[]... members) {
		final CommandArguments args = CommandArguments.create(key).add(members);
		return executeCommand(Command.GEOHASH, args, (cmd)->cmd.geohash(rawKey(key), members), (v)->v);
	}

	@Override
	public List<Geo> geoPos(final String key, final String... members) {
		final CommandArguments args = CommandArguments.create(key).add(members);
		return executeCommand(Command.GEOPOS, args, (cmd)->cmd.geopos(rawKey(key), members),
				new ListConverter<>(new GeoCoordinateConverter()));
	}

	@Override
	public List<Geo> geoPos(final byte[] key, final byte[]... members) {
		final CommandArguments args = CommandArguments.create(key).add(members);
		return executeCommand(Command.GEOPOS, args, (cmd)->cmd.geopos(rawKey(key), members),
				new ListConverter<>(new GeoCoordinateConverter()));
	}

	@Override
	public List<GeoRadius> geoRadius(final String key, final double longitude, final double latitude,
									 final double radius, final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(key).add(longitude, latitude).add(radius).add(unit);
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		return geoRadius(args,
				(cmd)->cmd.georadius(rawKey(key), longitude, latitude, radius, geoUnitConverter.convert(unit)));
	}

	@Override
	public List<GeoRadius> geoRadius(final byte[] key, final double longitude, final double latitude,
									 final double radius, final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(key).add(longitude, latitude).add(radius).add(unit);
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		return geoRadius(args,
				(cmd)->cmd.georadius(rawKey(key), longitude, latitude, radius, geoUnitConverter.convert(unit)));
	}

	@Override
	public List<GeoRadius> geoRadius(final String key, final double longitude, final double latitude,
									 final double radius, final GeoUnit unit, final GeoRadiusArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(longitude, latitude).add(radius).add(unit)
				.add(argument);
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		return geoRadius(args,
				(cmd)->cmd.georadius(rawKey(key), longitude, latitude, radius, geoUnitConverter.convert(unit)));
	}

	@Override
	public List<GeoRadius> geoRadius(final byte[] key, final double longitude, final double latitude,
									 final double radius, final GeoUnit unit, final GeoRadiusArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(longitude, latitude).add(radius).add(unit)
				.add(argument);
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		return geoRadius(args,
				(cmd)->cmd.georadius(rawKey(key), longitude, latitude, radius, geoUnitConverter.convert(unit)));
	}

	@Override
	public List<GeoRadius> geoRadiusRo(final String key, final double longitude, final double latitude,
									   final double radius, final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(key).add(longitude, latitude).add(radius).add(unit);
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		return geoRadiusRo(args,
				(cmd)->cmd.georadiusReadonly(rawKey(key), longitude, latitude, radius, geoUnitConverter.convert(unit)));
	}

	@Override
	public List<GeoRadius> geoRadiusRo(final byte[] key, final double longitude, final double latitude,
									   final double radius, final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(key).add(longitude, latitude).add(radius).add(unit);
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		return geoRadiusRo(args,
				(cmd)->cmd.georadiusReadonly(rawKey(key), longitude, latitude, radius, geoUnitConverter.convert(unit)));
	}

	@Override
	public List<GeoRadius> geoRadiusRo(final String key, final double longitude, final double latitude,
									   final double radius, final GeoUnit unit, final GeoRadiusArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(longitude, latitude).add(radius).add(unit)
				.add(argument);
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		final GeoRadiusArgumentConverter geoRadiusArgumentConverter = new GeoRadiusArgumentConverter();
		return geoRadiusRo(args,
				(cmd)->cmd.georadiusReadonly(rawKey(key), longitude, latitude, radius, geoUnitConverter.convert(unit),
						geoRadiusArgumentConverter.convert(argument)));
	}

	@Override
	public List<GeoRadius> geoRadiusRo(final byte[] key, final double longitude, final double latitude,
									   final double radius, final GeoUnit unit, final GeoRadiusArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(longitude, latitude).add(radius).add(unit)
				.add(argument);
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		final GeoRadiusArgumentConverter geoRadiusArgumentConverter = new GeoRadiusArgumentConverter();
		return geoRadiusRo(args,
				(cmd)->cmd.georadiusReadonly(rawKey(key), longitude, latitude, radius, geoUnitConverter.convert(unit),
						geoRadiusArgumentConverter.convert(argument)));
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final String key, final String member, final double radius,
											 final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(key).add(member).add(radius).add(unit);
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		return geoRadiusByMember(args,
				(cmd)->cmd.georadiusByMember(rawKey(key), member, radius, geoUnitConverter.convert(unit)));
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final byte[] key, final byte[] member, final double radius,
											 final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(key).add(member).add(radius).add(unit);
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		return geoRadiusByMember(args,
				(cmd)->cmd.georadiusByMember(rawKey(key), member, radius, geoUnitConverter.convert(unit)));
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final String key, final String member, final double radius,
											 final GeoUnit unit, final GeoRadiusArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(member).add(radius).add(unit).add(argument);
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		return geoRadiusByMember(args,
				(cmd)->cmd.georadiusByMember(rawKey(key), member, radius, geoUnitConverter.convert(unit)));
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final byte[] key, final byte[] member, final double radius,
											 final GeoUnit unit, final GeoRadiusArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(member).add(radius).add(unit).add(argument);
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		final GeoRadiusArgumentConverter geoRadiusArgumentConverter = new GeoRadiusArgumentConverter();
		return geoRadiusByMember(args,
				(cmd)->cmd.georadiusByMember(rawKey(key), member, radius, geoUnitConverter.convert(unit),
						geoRadiusArgumentConverter.convert(argument)));
	}

	@Override
	public List<GeoRadius> geoRadiusByMemberRo(final String key, final String member, final double radius,
											   final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(key).add(member).add(radius).add(unit);
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		return geoRadiusByMemberRo(args,
				(cmd)->cmd.georadiusByMemberReadonly(rawKey(key), member, radius, geoUnitConverter.convert(unit)));
	}

	@Override
	public List<GeoRadius> geoRadiusByMemberRo(final byte[] key, final byte[] member, final double radius,
											   final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(key).add(member).add(radius).add(unit);
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		return geoRadiusByMemberRo(args,
				(cmd)->cmd.georadiusByMemberReadonly(rawKey(key), member, radius, geoUnitConverter.convert(unit)));
	}

	@Override
	public List<GeoRadius> geoRadiusByMemberRo(final String key, final String member, final double radius,
											   final GeoUnit unit, final GeoRadiusArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(member).add(radius).add(unit).add(argument);
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		final GeoRadiusArgumentConverter geoRadiusArgumentConverter = new GeoRadiusArgumentConverter();
		return geoRadiusByMemberRo(args,
				(cmd)->cmd.georadiusByMemberReadonly(rawKey(key), member, radius, geoUnitConverter.convert(unit),
						geoRadiusArgumentConverter.convert(argument)));
	}

	@Override
	public List<GeoRadius> geoRadiusByMemberRo(final byte[] key, final byte[] member, final double radius,
											   final GeoUnit unit, final GeoRadiusArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(member).add(radius).add(unit).add(argument);
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		final GeoRadiusArgumentConverter geoRadiusArgumentConverter = new GeoRadiusArgumentConverter();
		return geoRadiusByMemberRo(args,
				(cmd)->cmd.georadiusByMemberReadonly(rawKey(key), member, radius, geoUnitConverter.convert(unit),
						geoRadiusArgumentConverter.convert(argument)));
	}

	@Override
	public List<GeoRadius> geoSearch(final String key, final String member, final double radius, final GeoUnit unit) {
		final CommandArguments args =
				CommandArguments.create(key).add("FROMMEMBER", member).add("BYRADIUS", radius).add(unit);
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		return executeCommand(Command.GEOSEARCH, args, (cmd)->cmd.geosearch(rawKey(key), member, radius,
				geoUnitConverter.convert(unit)), new ListConverter<>(new GeoRadiusResponseConverter()));
	}

	@Override
	public List<GeoRadius> geoSearch(final byte[] key, final byte[] member, final double radius, final GeoUnit unit) {
		final CommandArguments args =
				CommandArguments.create(key).add("FROMMEMBER", member).add("BYRADIUS", radius).add(unit);
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		return executeCommand(Command.GEOSEARCH, args, (cmd)->cmd.geosearch(rawKey(key), member, radius,
				geoUnitConverter.convert(unit)), new ListConverter<>(new GeoRadiusResponseConverter()));
	}

	@Override
	public List<GeoRadius> geoSearch(final String key, final double longitude, final double latitude,
									 final double radius, final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(key).add("FROMLONLAT").add(longitude, latitude)
				.add("BYRADIUS", radius).add(unit);
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		return executeCommand(Command.GEOSEARCH, args,
				(cmd)->cmd.geosearch(rawKey(key), new GeoCoordinate(longitude, latitude), radius,
						geoUnitConverter.convert(unit)), new ListConverter<>(new GeoRadiusResponseConverter()));
	}

	@Override
	public List<GeoRadius> geoSearch(final byte[] key, final double longitude, final double latitude,
									 final double radius, final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(key).add("FROMLONLAT").add(longitude).add(latitude)
				.add("BYRADIUS", radius).add(unit);
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		return executeCommand(Command.GEOSEARCH, args,
				(cmd)->cmd.geosearch(rawKey(key), new GeoCoordinate(longitude, latitude), radius,
						geoUnitConverter.convert(unit)), new ListConverter<>(new GeoRadiusResponseConverter()));
	}

	@Override
	public List<GeoRadius> geoSearch(final String key, final String member, final double width, final double height,
									 final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(key).add("FROMMEMBER", member).add("BYBOX").add(width,
				height).add(unit);
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		return executeCommand(Command.GEOSEARCH, args, (cmd)->cmd.geosearch(rawKey(key), member, width, height,
				geoUnitConverter.convert(unit)), new ListConverter<>(new GeoRadiusResponseConverter()));
	}

	@Override
	public List<GeoRadius> geoSearch(final byte[] key, final byte[] member, final double width, final double height,
									 final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(key).add("FROMMEMBER", member).add("BYBOX").add(width,
				height).add(unit);
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		return executeCommand(Command.GEOSEARCH, args, (cmd)->cmd.geosearch(rawKey(key), member, width, height,
				geoUnitConverter.convert(unit)), new ListConverter<>(new GeoRadiusResponseConverter()));
	}

	@Override
	public List<GeoRadius> geoSearch(final String key, final double longitude, final double latitude,
									 final double width, final double height, final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(key).add("FROMLONLAT").add(longitude, latitude)
				.add("BYBOX").add(width, height).add(unit);
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		return executeCommand(Command.GEOSEARCH, args,
				(cmd)->cmd.geosearch(rawKey(key), new GeoCoordinate(longitude, latitude), width, height,
						geoUnitConverter.convert(unit)), new ListConverter<>(new GeoRadiusResponseConverter()));
	}

	@Override
	public List<GeoRadius> geoSearch(final byte[] key, final double longitude, final double latitude,
									 final double width, final double height, final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(key).add("FROMLONLAT").add(longitude, latitude)
				.add("BYBOX").add(width, height).add(unit);
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		return executeCommand(Command.GEOSEARCH, args,
				(cmd)->cmd.geosearch(rawKey(key), new GeoCoordinate(longitude, latitude), width, height,
						geoUnitConverter.convert(unit)), new ListConverter<>(new GeoRadiusResponseConverter()));
	}

	@Override
	public List<GeoRadius> geoSearch(final String key, final String member, final double radius, final GeoUnit unit,
									 final GeoSearchArgument argument) {
		final CommandArguments args =
				CommandArguments.create(key).add("FROMMEMBER", member).add("BYRADIUS", radius).add(unit).add(argument);
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		final GeoSearchArgumentConverter geoSearchArgumentConverter = new GeoSearchArgumentConverter();
		final GeoSearchParam geoSearchParam =
				Optional.ofNullable(geoSearchArgumentConverter.convert(argument)).orElse(new GeoSearchParam());

		geoSearchParam.fromMember(member).byRadius(radius, geoUnitConverter.convert(unit));
		return geoSearch(key, geoSearchParam, args);
	}

	@Override
	public List<GeoRadius> geoSearch(final byte[] key, final byte[] member, final double radius, final GeoUnit unit,
									 final GeoSearchArgument argument) {
		final CommandArguments args =
				CommandArguments.create(key).add("FROMMEMBER", member).add("BYRADIUS", radius).add(unit).add(argument);
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		final GeoSearchArgumentConverter geoSearchArgumentConverter = new GeoSearchArgumentConverter();
		final GeoSearchParam geoSearchParam =
				Optional.ofNullable(geoSearchArgumentConverter.convert(argument)).orElse(new GeoSearchParam());

		geoSearchParam.fromMember(SafeEncoder.encode(member)).byRadius(radius, geoUnitConverter.convert(unit));
		return geoSearch(key, geoSearchParam, args);
	}

	@Override
	public List<GeoRadius> geoSearch(final String key, final double longitude, final double latitude,
									 final double radius, final GeoUnit unit, final GeoSearchArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add("FROMLONLAT").add(longitude, latitude)
				.add("BYRADIUS", radius).add(unit).add(argument);
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		final GeoSearchArgumentConverter geoSearchArgumentConverter = new GeoSearchArgumentConverter();
		final GeoSearchParam geoSearchParam =
				Optional.ofNullable(geoSearchArgumentConverter.convert(argument)).orElse(new GeoSearchParam());

		geoSearchParam.fromLonLat(longitude, latitude).byRadius(radius, geoUnitConverter.convert(unit));
		return geoSearch(key, geoSearchParam, args);
	}

	@Override
	public List<GeoRadius> geoSearch(final byte[] key, final double longitude, final double latitude,
									 final double radius, final GeoUnit unit, final GeoSearchArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add("FROMLONLAT").add(longitude, latitude)
				.add("BYRADIUS", radius).add(unit).add(argument);
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		final GeoSearchArgumentConverter geoSearchArgumentConverter = new GeoSearchArgumentConverter();
		final GeoSearchParam geoSearchParam =
				Optional.ofNullable(geoSearchArgumentConverter.convert(argument)).orElse(new GeoSearchParam());

		geoSearchParam.fromLonLat(longitude, latitude).byRadius(radius, geoUnitConverter.convert(unit));
		return geoSearch(key, geoSearchParam, args);
	}

	@Override
	public List<GeoRadius> geoSearch(final String key, final String member, final double width, final double height,
									 final GeoUnit unit, final GeoSearchArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add("FROMMEMBER", member).add("BYBOX").add(width,
				height).add(unit).add(argument);
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		final GeoSearchArgumentConverter geoSearchArgumentConverter = new GeoSearchArgumentConverter();
		final GeoSearchParam geoSearchParam =
				Optional.ofNullable(geoSearchArgumentConverter.convert(argument)).orElse(new GeoSearchParam());

		geoSearchParam.fromMember(member).byBox(width, height, geoUnitConverter.convert(unit));
		return geoSearch(key, geoSearchParam, args);
	}

	@Override
	public List<GeoRadius> geoSearch(final byte[] key, final byte[] member, final double width, final double height,
									 final GeoUnit unit, final GeoSearchArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add("FROMMEMBER", member).add("BYBOX").add(width,
				height).add(unit).add(argument);
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		final GeoSearchArgumentConverter geoSearchArgumentConverter = new GeoSearchArgumentConverter();
		final GeoSearchParam geoSearchParam =
				Optional.ofNullable(geoSearchArgumentConverter.convert(argument)).orElse(new GeoSearchParam());

		geoSearchParam.fromMember(SafeEncoder.encode(member)).byBox(width, height, geoUnitConverter.convert(unit));
		return geoSearch(key, geoSearchParam, args);
	}

	@Override
	public List<GeoRadius> geoSearch(final String key, final double longitude, final double latitude,
									 final double width, final double height, final GeoUnit unit,
									 final GeoSearchArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add("FROMLONLAT").add(longitude, latitude)
				.add("BYBOX").add(width, height).add(unit).add(argument);
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		final GeoSearchArgumentConverter geoSearchArgumentConverter = new GeoSearchArgumentConverter();
		final GeoSearchParam geoSearchParam =
				Optional.ofNullable(geoSearchArgumentConverter.convert(argument)).orElse(new GeoSearchParam());

		geoSearchParam.fromLonLat(longitude, latitude).byBox(width, height, geoUnitConverter.convert(unit));
		return geoSearch(key, geoSearchParam, args);
	}

	@Override
	public List<GeoRadius> geoSearch(final byte[] key, final double longitude, final double latitude,
									 final double width, final double height, final GeoUnit unit,
									 final GeoSearchArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add("FROMLONLAT").add(longitude, latitude)
				.add("BYBOX").add(width, height).add(unit).add(argument);
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		final GeoSearchArgumentConverter geoSearchArgumentConverter = new GeoSearchArgumentConverter();
		final GeoSearchParam geoSearchParam =
				Optional.ofNullable(geoSearchArgumentConverter.convert(argument)).orElse(new GeoSearchParam());

		geoSearchParam.fromLonLat(longitude, latitude).byBox(width, height, geoUnitConverter.convert(unit));
		return geoSearch(key, geoSearchParam, args);
	}

	@Override
	public Long geoSearchStore(final String destKey, final String key, final String member, final double radius,
							   final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(destKey).add(key).add("FROMMEMBER", member)
				.add("BYRADIUS", radius).add(unit);
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		return executeCommand(Command.GEOSEARCHSTORE, args,
				(cmd)->cmd.geosearchStore(rawKey(destKey), rawKey(key), member, radius, geoUnitConverter.convert(unit)),
				(v)->v);
	}

	@Override
	public Long geoSearchStore(final byte[] destKey, final byte[] key, final byte[] member, final double radius,
							   final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(destKey).add(key).add("FROMMEMBER", member)
				.add("BYRADIUS", radius).add(unit);
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		return executeCommand(Command.GEOSEARCHSTORE, args,
				(cmd)->cmd.geosearchStore(rawKey(destKey), rawKey(key), member, radius, geoUnitConverter.convert(unit)),
				(v)->v);
	}

	@Override
	public Long geoSearchStore(final String destKey, final String key, final double longitude, final double latitude,
							   final double radius, final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(destKey).add(key).add("FROMLONLAT").add(longitude,
				latitude).add("BYRADIUS", radius).add(unit);
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		return executeCommand(Command.GEOSEARCHSTORE, args,
				(cmd)->cmd.geosearchStore(rawKey(destKey), rawKey(key), new GeoCoordinate(longitude, latitude), radius,
						geoUnitConverter.convert(unit)), (v)->v);
	}

	@Override
	public Long geoSearchStore(final byte[] destKey, final byte[] key, final double longitude, final double latitude,
							   final double radius, final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(destKey).add(key).add("FROMLONLAT").add(longitude,
				latitude).add("BYRADIUS", radius).add(unit);
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		return executeCommand(Command.GEOSEARCHSTORE, args,
				(cmd)->cmd.geosearchStore(rawKey(destKey), rawKey(key), new GeoCoordinate(longitude, latitude), radius,
						geoUnitConverter.convert(unit)), (v)->v);
	}

	@Override
	public Long geoSearchStore(final String destKey, final String key, final String member, final double width,
							   final double height, final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(destKey).add(key).add("FROMMEMBER", member)
				.add("BYBOX").add(width, height).add(unit);
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		return executeCommand(Command.GEOSEARCHSTORE, args,
				(cmd)->cmd.geosearchStore(rawKey(destKey), rawKey(key), member, width, height,
						geoUnitConverter.convert(unit)), (v)->v);
	}

	@Override
	public Long geoSearchStore(final byte[] destKey, final byte[] key, final byte[] member, final double width,
							   final double height, final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(destKey).add(key).add("FROMMEMBER", member)
				.add("BYBOX").add(width, height).add(unit);
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		return executeCommand(Command.GEOSEARCHSTORE, args,
				(cmd)->cmd.geosearchStore(rawKey(destKey), rawKey(key), member, width, height,
						geoUnitConverter.convert(unit)), (v)->v);
	}

	@Override
	public Long geoSearchStore(final String destKey, final String key, final double longitude, final double latitude,
							   final double width, final double height, final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(destKey).add(key).add("FROMLONLAT").add(longitude,
				latitude).add("BYBOX").add(width, height).add(unit);
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		return executeCommand(Command.GEOSEARCHSTORE, args,
				(cmd)->cmd.geosearchStore(rawKey(destKey), rawKey(key), new GeoCoordinate(longitude, latitude), width,
						height, geoUnitConverter.convert(unit)), (v)->v);
	}

	@Override
	public Long geoSearchStore(final byte[] destKey, final byte[] key, final double longitude, final double latitude,
							   final double width, final double height, final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(destKey).add(key).add("FROMLONLAT").add(longitude,
				latitude).add("BYBOX").add(width, height).add(unit);
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		return executeCommand(Command.GEOSEARCHSTORE, args,
				(cmd)->cmd.geosearchStore(rawKey(destKey), rawKey(key), new GeoCoordinate(longitude, latitude), width,
						height,
						geoUnitConverter.convert(unit)), (v)->v);
	}

	@Override
	public Long geoSearchStore(final String destKey, final String key, final String member, final double radius,
							   final GeoUnit unit, final GeoSearchArgument argument) {
		final CommandArguments args = CommandArguments.create(destKey).add(key).add("FROMMEMBER", member)
				.add("BYRADIUS", radius).add(unit).add(argument);
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		final GeoSearchArgumentConverter geoSearchArgumentConverter = new GeoSearchArgumentConverter();
		final GeoSearchParam geoSearchParam =
				Optional.ofNullable(geoSearchArgumentConverter.convert(argument)).orElse(new GeoSearchParam());

		geoSearchParam.fromMember(member).byRadius(radius, geoUnitConverter.convert(unit));
		return geoSearchStore(destKey, key, geoSearchParam, args);
	}

	@Override
	public Long geoSearchStore(final byte[] destKey, final byte[] key, final byte[] member, final double radius,
							   final GeoUnit unit, final GeoSearchArgument argument) {
		final CommandArguments args = CommandArguments.create(destKey).add(key).add("FROMMEMBER", member)
				.add("BYRADIUS", radius).add(unit).add(argument);
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		final GeoSearchArgumentConverter geoSearchArgumentConverter = new GeoSearchArgumentConverter();
		final GeoSearchParam geoSearchParam =
				Optional.ofNullable(geoSearchArgumentConverter.convert(argument)).orElse(new GeoSearchParam());

		geoSearchParam.fromMember(SafeEncoder.encode(member)).byRadius(radius, geoUnitConverter.convert(unit));
		return geoSearchStore(destKey, key, geoSearchParam, args);
	}

	@Override
	public Long geoSearchStore(final String destKey, final String key, final double longitude, final double latitude,
							   final double radius, final GeoUnit unit, final GeoSearchArgument argument) {
		final CommandArguments args = CommandArguments.create(destKey).add(key).add("FROMLONLAT").add(longitude,
				latitude).add("BYRADIUS", radius).add(unit).add(argument);
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		final GeoSearchArgumentConverter geoSearchArgumentConverter = new GeoSearchArgumentConverter();
		final GeoSearchParam geoSearchParam =
				Optional.ofNullable(geoSearchArgumentConverter.convert(argument)).orElse(new GeoSearchParam());

		geoSearchParam.fromLonLat(longitude, latitude).byRadius(radius, geoUnitConverter.convert(unit));
		return geoSearchStore(destKey, key, geoSearchParam, args);
	}

	@Override
	public Long geoSearchStore(final byte[] destKey, final byte[] key, final double longitude, final double latitude,
							   final double radius, final GeoUnit unit, final GeoSearchArgument argument) {
		final CommandArguments args = CommandArguments.create(destKey).add(key).add("FROMLONLAT").add(longitude,
				latitude).add("BYRADIUS", radius).add(unit).add(argument);
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		final GeoSearchArgumentConverter geoSearchArgumentConverter = new GeoSearchArgumentConverter();
		final GeoSearchParam geoSearchParam =
				Optional.ofNullable(geoSearchArgumentConverter.convert(argument)).orElse(new GeoSearchParam());

		geoSearchParam.fromLonLat(longitude, latitude).byRadius(radius, geoUnitConverter.convert(unit));
		return geoSearchStore(destKey, key, geoSearchParam, args);
	}

	@Override
	public Long geoSearchStore(final String destKey, final String key, final String member, final double width,
							   final double height, final GeoUnit unit, final GeoSearchArgument argument) {
		final CommandArguments args = CommandArguments.create(destKey).add(key).add("FROMMEMBER", member)
				.add("BYBOX").add(width, height).add(unit).add(argument);
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		final GeoSearchArgumentConverter geoSearchArgumentConverter = new GeoSearchArgumentConverter();
		final GeoSearchParam geoSearchParam =
				Optional.ofNullable(geoSearchArgumentConverter.convert(argument)).orElse(new GeoSearchParam());

		geoSearchParam.fromMember(member).byBox(width, height, geoUnitConverter.convert(unit));
		return geoSearchStore(destKey, key, geoSearchParam, args);
	}

	@Override
	public Long geoSearchStore(final byte[] destKey, final byte[] key, final byte[] member, final double width,
							   final double height, final GeoUnit unit, final GeoSearchArgument argument) {
		final CommandArguments args = CommandArguments.create(destKey).add(key).add("FROMMEMBER", member)
				.add("BYBOX").add(width, height).add(unit).add(argument);
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		final GeoSearchArgumentConverter geoSearchArgumentConverter = new GeoSearchArgumentConverter();
		final GeoSearchParam geoSearchParam =
				Optional.ofNullable(geoSearchArgumentConverter.convert(argument)).orElse(new GeoSearchParam());

		geoSearchParam.fromMember(SafeEncoder.encode(member)).byBox(width, height, geoUnitConverter.convert(unit));
		return geoSearchStore(destKey, key, geoSearchParam, args);
	}

	@Override
	public Long geoSearchStore(final String destKey, final String key, final double longitude, final double latitude,
							   final double width, final double height, final GeoUnit unit,
							   final GeoSearchArgument argument) {
		final CommandArguments args = CommandArguments.create(destKey).add(key).add("FROMLONLAT").add(longitude,
				latitude).add("BYBOX").add(width, height).add(unit).add(argument);
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		final GeoSearchArgumentConverter geoSearchArgumentConverter = new GeoSearchArgumentConverter();
		final GeoSearchParam geoSearchParam =
				Optional.ofNullable(geoSearchArgumentConverter.convert(argument)).orElse(new GeoSearchParam());

		geoSearchParam.fromLonLat(longitude, latitude).byBox(width, height, geoUnitConverter.convert(unit));
		return geoSearchStore(destKey, key, geoSearchParam, args);
	}

	@Override
	public Long geoSearchStore(final byte[] destKey, final byte[] key, final double longitude, final double latitude,
							   final double width, final double height, final GeoUnit unit,
							   final GeoSearchArgument argument) {
		final CommandArguments args = CommandArguments.create(destKey).add(key).add("FROMLONLAT").add(longitude,
				latitude).add("BYBOX").add(width, height).add(unit).add(argument);
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		final GeoSearchArgumentConverter geoSearchArgumentConverter = new GeoSearchArgumentConverter();
		final GeoSearchParam geoSearchParam =
				Optional.ofNullable(geoSearchArgumentConverter.convert(argument)).orElse(new GeoSearchParam());

		geoSearchParam.fromLonLat(longitude, latitude).byBox(width, height, geoUnitConverter.convert(unit));
		return geoSearchStore(destKey, key, geoSearchParam, args);
	}

	private Long geoAdd(final CommandArguments args,
						final com.buession.redis.core.Command.Executor<UnifiedJedis, Long> executor) {
		return executeCommand(Command.GEOADD, args, executor, (v)->v);
	}

	private Double geoDist(final CommandArguments args,
						   final com.buession.redis.core.Command.Executor<UnifiedJedis, Double> executor) {
		return executeCommand(Command.GEODIST, args, executor, (v)->v);
	}

	private List<GeoRadius> geoRadius(final CommandArguments args,
									  final com.buession.redis.core.Command.Executor<UnifiedJedis, List<GeoRadiusResponse>> executor) {
		return executeCommand(Command.GEORADIUS, args, executor, new ListConverter<>(new GeoRadiusResponseConverter()));
	}

	private List<GeoRadius> geoRadiusRo(final CommandArguments args,
										final com.buession.redis.core.Command.Executor<UnifiedJedis, List<GeoRadiusResponse>> executor) {
		return executeCommand(Command.GEORADIUS_RO, args, executor,
				new ListConverter<>(new GeoRadiusResponseConverter()));
	}

	private List<GeoRadius> geoRadiusByMember(final CommandArguments args,
											  final com.buession.redis.core.Command.Executor<UnifiedJedis, List<GeoRadiusResponse>> executor) {
		return executeCommand(Command.GEORADIUSBYMEMBER, args, executor,
				new ListConverter<>(new GeoRadiusResponseConverter()));
	}

	private List<GeoRadius> geoRadiusByMemberRo(final CommandArguments args,
												final com.buession.redis.core.Command.Executor<UnifiedJedis, List<GeoRadiusResponse>> executor) {
		return executeCommand(Command.GEORADIUSBYMEMBER_RO, args, executor,
				new ListConverter<>(new GeoRadiusResponseConverter()));
	}

	private List<GeoRadius> geoSearch(String key, final GeoSearchParam geoSearchParam,
									  final CommandArguments args) {
		return executeCommand(Command.GEOSEARCH, args, (cmd)->cmd.geosearch(rawKey(key), geoSearchParam),
				new ListConverter<>(new GeoRadiusResponseConverter()));
	}

	private List<GeoRadius> geoSearch(final byte[] key, final GeoSearchParam geoSearchParam,
									  final CommandArguments args) {
		return executeCommand(Command.GEOSEARCH, args, (cmd)->cmd.geosearch(rawKey(key), geoSearchParam),
				new ListConverter<>(new GeoRadiusResponseConverter()));
	}

	private Long geoSearchStore(final String destKey, final String key, final GeoSearchParam geoSearchParam,
								final CommandArguments args) {
		return executeCommand(Command.GEOSEARCHSTORE, args,
				(cmd)->cmd.geosearchStore(rawKey(destKey), rawKey(key), geoSearchParam), (v)->v);
	}

	private Long geoSearchStore(final byte[] destKey, final byte[] key, final GeoSearchParam geoSearchParam,
								final CommandArguments args) {
		return executeCommand(Command.GEOSEARCHSTORE, args, (cmd)->cmd.geosearchStore(rawKey(destKey), rawKey(key),
				geoSearchParam), (v)->v);
	}

	@SafeVarargs
	public final Map<String, GeoCoordinate> createMemberCoordinateMap(
			final KeyValue<String, Geo>... memberCoordinates) {
		final GeoConverter geoConverter = new GeoConverter();
		final Map<String, GeoCoordinate> memberCoordinateMap = new LinkedHashMap<>(memberCoordinates.length);

		for(KeyValue<String, Geo> kv : memberCoordinates){
			memberCoordinateMap.put(rawKey(kv.getKey()), geoConverter.convert(kv.getValue()));
		}

		return memberCoordinateMap;
	}

	@SafeVarargs
	public final Map<byte[], GeoCoordinate> createBinaryMemberCoordinateMap(
			final KeyValue<byte[], Geo>... memberCoordinates) {
		final GeoConverter geoConverter = new GeoConverter();
		final Map<byte[], GeoCoordinate> memberCoordinateMap = new LinkedHashMap<>(memberCoordinates.length);

		for(KeyValue<byte[], Geo> kv : memberCoordinates){
			memberCoordinateMap.put(rawKey(kv.getKey()), geoConverter.convert(kv.getValue()));
		}

		return memberCoordinateMap;
	}

}
