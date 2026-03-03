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
package com.buession.redis.client.lettuce.command;

import com.buession.core.converter.ListConverter;
import com.buession.core.converter.SetListConverter;
import com.buession.lang.Geo;
import com.buession.lang.KeyValue;
import com.buession.redis.client.lettuce.LettuceRedisClient;
import com.buession.redis.core.GeoRadius;
import com.buession.redis.core.GeoUnit;
import com.buession.redis.core.command.Command;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.GeoCommands;
import com.buession.redis.core.command.args.GeoAddArgument;
import com.buession.redis.core.command.args.GeoRadiusArgument;
import com.buession.redis.core.command.args.GeoSearchArgument;
import com.buession.redis.core.internal.convert.lettuce.params.GeoAddArgumentConverter;
import com.buession.redis.core.internal.convert.lettuce.params.GeoRadiusArgumentConverter;
import com.buession.redis.core.internal.convert.lettuce.params.GeoSearchArgumentConverter;
import com.buession.redis.core.internal.convert.lettuce.params.GeoUnitConverter;
import com.buession.redis.core.internal.convert.lettuce.response.GeoCoordinateConverter;
import com.buession.redis.core.internal.convert.lettuce.response.GeoRadiusGeneralResultConverter;
import com.buession.redis.core.internal.convert.lettuce.response.GeoRadiusResponseConverter;
import com.buession.redis.utils.SafeEncoder;
import io.lettuce.core.GeoArgs;
import io.lettuce.core.GeoSearch;
import io.lettuce.core.GeoValue;
import io.lettuce.core.GeoWithin;
import io.lettuce.core.Value;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Lettuce 地理位置命令
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public final class LettuceGeoCommands extends AbstractLettuceRedisCommands implements GeoCommands {

	public LettuceGeoCommands(final LettuceRedisClient client) {
		super(client);
	}

	@Override
	public Long geoAdd(final String key, final String member, final double longitude, final double latitude) {
		return geoAdd(SafeEncoder.encode(key), SafeEncoder.encode(member), longitude, latitude);
	}

	@Override
	public Long geoAdd(final byte[] key, final byte[] member, final double longitude, final double latitude) {
		final CommandArguments args = CommandArguments.create(key).add(longitude).add(latitude).add(member);
		return geoAdd((cmd)->cmd.geoadd(key, longitude, latitude, member), args);
	}

	@Override
	public Long geoAdd(final String key, final KeyValue<String, Geo>... memberCoordinates) {
		final CommandArguments args = CommandArguments.create(key).add(memberCoordinates);
		return geoAdd((cmd)->cmd.geoadd(SafeEncoder.encode(key), stringMemberCoordinatesToGeoValue(memberCoordinates)),
				args);
	}

	@Override
	public Long geoAdd(final byte[] key, final KeyValue<byte[], Geo>... memberCoordinates) {
		final CommandArguments args = CommandArguments.create(key).add(memberCoordinates);
		return geoAdd((cmd)->cmd.geoadd(key, byteMemberCoordinatesToGeoValue(memberCoordinates)), args);
	}

	@Override
	public Long geoAdd(final String key, final GeoAddArgument argument, final String member, final double longitude,
					   final double latitude) {
		return geoAdd(SafeEncoder.encode(key), argument, SafeEncoder.encode(member), longitude, latitude);
	}

	@Override
	public Long geoAdd(final byte[] key, final GeoAddArgument argument, final byte[] member, final double longitude,
					   final double latitude) {
		final CommandArguments args = CommandArguments.create(key).add(argument).add(longitude).add(latitude)
				.add(member);
		final GeoAddArgumentConverter geoAddArgumentConverter = new GeoAddArgumentConverter();
		return geoAdd((cmd)->cmd.geoadd(key, longitude, latitude, member, geoAddArgumentConverter.convert(argument)),
				args);
	}

	@Override
	public Long geoAdd(final String key, final GeoAddArgument argument, final Map<String, Geo> memberCoordinates) {
		final CommandArguments args = CommandArguments.create(key).add(argument).add(memberCoordinates);
		final GeoAddArgumentConverter geoAddArgumentConverter = new GeoAddArgumentConverter();
		return geoAdd((cmd)->cmd.geoadd(SafeEncoder.encode(key), geoAddArgumentConverter.convert(argument),
				stringMemberCoordinatesToGeoValue(memberCoordinates)), args);
	}

	@Override
	public Long geoAdd(final byte[] key, final GeoAddArgument argument, final Map<byte[], Geo> memberCoordinates) {
		final CommandArguments args = CommandArguments.create(key).add(argument).add(memberCoordinates);
		final GeoAddArgumentConverter geoAddArgumentConverter = new GeoAddArgumentConverter();
		return geoAdd((cmd)->cmd.geoadd(key, geoAddArgumentConverter.convert(argument),
				byteMemberCoordinatesToGeoValue(memberCoordinates)), args);
	}

	@Override
	public Double geoDist(final String key, final String member1, final String member2) {
		return geoDist(SafeEncoder.encode(key), SafeEncoder.encode(member1), SafeEncoder.encode(member2));
	}

	@Override
	public Double geoDist(final byte[] key, final byte[] member1, final byte[] member2) {
		final CommandArguments args = CommandArguments.create(key).add(member1).add(member2);
		return executeCommand(Command.GEODIST, args, (cmd)->cmd.geodist(key, member1, member2, GeoArgs.Unit.m), (v)->v);
	}

	@Override
	public Double geoDist(final String key, final String member1, final String member2, final GeoUnit unit) {
		return geoDist(SafeEncoder.encode(key), SafeEncoder.encode(member1), SafeEncoder.encode(member2), unit);
	}

	@Override
	public Double geoDist(final byte[] key, final byte[] member1, final byte[] member2, final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(key).add(member1).add(member2).add(unit);
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();

		return executeCommand(Command.GEODIST, args,
				(cmd)->cmd.geodist(key, member1, member2, geoUnitConverter.convert(unit)), (v)->v);
	}

	@Override
	public List<String> geoHash(final String key, final String... members) {
		final CommandArguments args = CommandArguments.create(key).add(members);
		return executeCommand(Command.GEOHASH, args, (cmd)->cmd.geohash(SafeEncoder.encode(key),
				SafeEncoder.encode(members)), new ListConverter<>(Value::getValue));
	}

	@Override
	public List<byte[]> geoHash(final byte[] key, final byte[]... members) {
		final CommandArguments args = CommandArguments.create(key).add(members);
		return executeCommand(Command.GEOHASH, args, (cmd)->cmd.geohash(key, members),
				new ListConverter<>((v)->SafeEncoder.encode(v.getValue())));
	}

	@Override
	public List<Geo> geoPos(final String key, final String... members) {
		return geoPos(SafeEncoder.encode(key), SafeEncoder.encode(members));
	}

	@Override
	public List<Geo> geoPos(final byte[] key, final byte[]... members) {
		final CommandArguments args = CommandArguments.create(key).add(members);
		return executeCommand(Command.GEOPOS, args, (cmd)->cmd.geopos(key, members),
				new ListConverter<>(new GeoCoordinateConverter()));
	}

	@Override
	public List<GeoRadius> geoRadius(final String key, final double longitude, final double latitude,
									 final double radius, final GeoUnit unit) {
		return geoRadius(SafeEncoder.encode(key), longitude, latitude, radius, unit);
	}

	@Override
	public List<GeoRadius> geoRadius(final byte[] key, final double longitude, final double latitude,
									 final double radius, final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(key).add(longitude).add(latitude).add(radius).add(unit);
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		return executeCommand(Command.GEORADIUS, args, (cmd)->cmd.georadius(key, longitude, latitude, radius,
				geoUnitConverter.convert(unit)), new SetListConverter<>(new GeoRadiusGeneralResultConverter()));
	}

	@Override
	public List<GeoRadius> geoRadius(final String key, final double longitude, final double latitude,
									 final double radius, final GeoUnit unit, final GeoRadiusArgument argument) {
		return geoRadius(SafeEncoder.encode(key), longitude, latitude, radius, unit, argument);
	}

	@Override
	public List<GeoRadius> geoRadius(final byte[] key, final double longitude, final double latitude,
									 final double radius, final GeoUnit unit, final GeoRadiusArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(longitude).add(latitude).add(radius).add(unit)
				.add(argument);
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		final GeoRadiusArgumentConverter geoRadiusArgumentConverter = new GeoRadiusArgumentConverter();
		return executeCommand(Command.GEORADIUS, args, (cmd)->cmd.georadius(key, longitude, latitude, radius,
						geoUnitConverter.convert(unit), geoRadiusArgumentConverter.convert(argument)),
				new ListConverter<>(new GeoRadiusResponseConverter()));
	}

	@Override
	public List<GeoRadius> geoRadiusRo(final String key, final double longitude, final double latitude,
									   final double radius, final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(key).add(longitude).add(latitude).add(radius).add(unit);
		return executeCommand(Command.GEORADIUS_RO, args);
	}

	@Override
	public List<GeoRadius> geoRadiusRo(final byte[] key, final double longitude, final double latitude,
									   final double radius, final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(key).add(longitude).add(latitude).add(radius).add(unit);
		return executeCommand(Command.GEORADIUS_RO, args);
	}

	@Override
	public List<GeoRadius> geoRadiusRo(final String key, final double longitude, final double latitude,
									   final double radius, final GeoUnit unit, final GeoRadiusArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(longitude).add(latitude).add(radius).add(unit)
				.add(argument);
		return executeCommand(Command.GEORADIUS_RO, args);
	}

	@Override
	public List<GeoRadius> geoRadiusRo(final byte[] key, final double longitude, final double latitude,
									   final double radius, final GeoUnit unit, final GeoRadiusArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(longitude).add(latitude).add(radius).add(unit)
				.add(argument);
		return executeCommand(Command.GEORADIUS_RO, args);
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final String key, final String member, final double radius,
											 final GeoUnit unit) {
		return geoRadiusByMember(SafeEncoder.encode(key), SafeEncoder.encode(member), radius, unit);
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final byte[] key, final byte[] member, final double radius,
											 final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(key).add(member).add(radius).add(unit);
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		return executeCommand(Command.GEORADIUSBYMEMBER, args, (cmd)->cmd.georadiusbymember(key, member, radius,
				geoUnitConverter.convert(unit)), new SetListConverter<>(new GeoRadiusGeneralResultConverter()));
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final String key, final String member, final double radius,
											 final GeoUnit unit, final GeoRadiusArgument argument) {
		return geoRadiusByMember(SafeEncoder.encode(key), SafeEncoder.encode(member), radius, unit, argument);
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final byte[] key, final byte[] member, final double radius,
											 final GeoUnit unit, final GeoRadiusArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(member).add(radius).add(unit).add(argument);
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		final GeoRadiusArgumentConverter geoRadiusArgumentConverter = new GeoRadiusArgumentConverter();
		return executeCommand(Command.GEORADIUSBYMEMBER, args, (cmd)->cmd.georadiusbymember(key, member, radius,
						geoUnitConverter.convert(unit), geoRadiusArgumentConverter.convert(argument)),
				new ListConverter<>(new GeoRadiusResponseConverter()));
	}

	@Override
	public List<GeoRadius> geoRadiusByMemberRo(final String key, final String member, final double radius,
											   final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(key).add(member).add(radius).add(unit);
		return executeCommand(Command.GEORADIUSBYMEMBER_RO, args);
	}

	@Override
	public List<GeoRadius> geoRadiusByMemberRo(final byte[] key, final byte[] member, final double radius,
											   final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(key).add(member).add(radius).add(unit);
		return executeCommand(Command.GEORADIUSBYMEMBER_RO, args);
	}

	@Override
	public List<GeoRadius> geoRadiusByMemberRo(final String key, final String member, final double radius,
											   final GeoUnit unit, final GeoRadiusArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(member).add(radius).add(unit).add(argument);
		return executeCommand(Command.GEORADIUSBYMEMBER_RO, args);
	}

	@Override
	public List<GeoRadius> geoRadiusByMemberRo(final byte[] key, final byte[] member, final double radius,
											   final GeoUnit unit, final GeoRadiusArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(member).add(radius).add(unit).add(argument);
		return executeCommand(Command.GEORADIUSBYMEMBER_RO, args);
	}

	@Override
	public List<GeoRadius> geoSearch(final String key, final String member, final double radius, final GeoUnit unit) {
		return geoSearch(SafeEncoder.encode(key), SafeEncoder.encode(member), radius, unit);
	}

	@Override
	public List<GeoRadius> geoSearch(final byte[] key, final byte[] member, final double radius, final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(key).add("FROMMEMBER").add(member).add("BYRADIUS")
				.add(radius).add(unit);
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		return geoSearch((cmd)->cmd.geosearch(key, GeoSearch.fromMember(member),
				GeoSearch.byRadius(radius, geoUnitConverter.convert(unit))), args);
	}

	@Override
	public List<GeoRadius> geoSearch(final String key, final double longitude, final double latitude,
									 final double radius, final GeoUnit unit) {
		return geoSearch(SafeEncoder.encode(key), longitude, latitude, radius, unit);
	}

	@Override
	public List<GeoRadius> geoSearch(final byte[] key, final double longitude, final double latitude,
									 final double radius, final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(key).add("FROMLONLAT").add(longitude).add(latitude)
				.add("BYRADIUS").add(radius).add(unit);
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		return geoSearch((cmd)->cmd.geosearch(key, GeoSearch.fromCoordinates(longitude, latitude),
				GeoSearch.byRadius(radius, geoUnitConverter.convert(unit))), args);
	}

	@Override
	public List<GeoRadius> geoSearch(final String key, final String member, final double width, final double height,
									 final GeoUnit unit) {
		return geoSearch(SafeEncoder.encode(key), SafeEncoder.encode(member), width, height, unit);
	}

	@Override
	public List<GeoRadius> geoSearch(final byte[] key, final byte[] member, final double width, final double height,
									 final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(key).add("FROMMEMBER").add(member).add("BYBOX").add(width)
				.add(height).add(unit);
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		return geoSearch((cmd)->cmd.geosearch(key, GeoSearch.fromMember(member),
				GeoSearch.byBox(width, height, geoUnitConverter.convert(unit))), args);
	}

	@Override
	public List<GeoRadius> geoSearch(final String key, final double longitude, final double latitude,
									 final double width, final double height, final GeoUnit unit) {
		return geoSearch(SafeEncoder.encode(key), longitude, latitude, width, height, unit);
	}

	@Override
	public List<GeoRadius> geoSearch(final byte[] key, final double longitude, final double latitude,
									 final double width, final double height, final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(key).add("FROMLONLAT").add(longitude).add(latitude)
				.add("BYBOX").add(width).add(height).add(unit);
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		return geoSearch((cmd)->cmd.geosearch(key, GeoSearch.fromCoordinates(longitude, latitude),
				GeoSearch.byBox(width, height, geoUnitConverter.convert(unit))), args);
	}

	@Override
	public List<GeoRadius> geoSearch(final String key, final String member, final double radius, final GeoUnit unit,
									 final GeoSearchArgument argument) {
		return geoSearch(SafeEncoder.encode(key), SafeEncoder.encode(member), radius, unit, argument);
	}

	@Override
	public List<GeoRadius> geoSearch(final byte[] key, final byte[] member, final double radius, final GeoUnit unit,
									 final GeoSearchArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add("FROMMEMBER").add(member).add("BYRADIUS")
				.add(radius).add(unit).add(argument);
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		final GeoSearchArgumentConverter geoSearchArgumentConverter = new GeoSearchArgumentConverter();
		return geoSearchWithin((cmd)->cmd.geosearch(key, GeoSearch.fromMember(member),
						GeoSearch.byRadius(radius, geoUnitConverter.convert(unit)),
						geoSearchArgumentConverter.convert(argument)),
				args);
	}

	@Override
	public List<GeoRadius> geoSearch(final String key, final double longitude, final double latitude,
									 final double radius, final GeoUnit unit, final GeoSearchArgument argument) {
		return geoSearch(SafeEncoder.encode(key), longitude, latitude, radius, unit, argument);
	}

	@Override
	public List<GeoRadius> geoSearch(final byte[] key, final double longitude, final double latitude,
									 final double radius, final GeoUnit unit, final GeoSearchArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add("FROMLONLAT").add(longitude).add(latitude)
				.add("BYRADIUS").add(radius).add(unit).add(argument);
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		final GeoSearchArgumentConverter geoSearchArgumentConverter = new GeoSearchArgumentConverter();
		return geoSearchWithin((cmd)->cmd.geosearch(key, GeoSearch.fromCoordinates(longitude, latitude),
				GeoSearch.byRadius(radius, geoUnitConverter.convert(unit)),
				geoSearchArgumentConverter.convert(argument)), args);
	}

	@Override
	public List<GeoRadius> geoSearch(final String key, final String member, final double width, final double height,
									 final GeoUnit unit, final GeoSearchArgument argument) {
		return geoSearch(SafeEncoder.encode(key), SafeEncoder.encode(member), width, height, unit, argument);
	}

	@Override
	public List<GeoRadius> geoSearch(final byte[] key, final byte[] member, final double width, final double height,
									 final GeoUnit unit, final GeoSearchArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add("FROMMEMBER").add(member).add("BYBOX").add(width)
				.add(height).add(unit).add(argument);
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		final GeoSearchArgumentConverter geoSearchArgumentConverter = new GeoSearchArgumentConverter();
		return geoSearchWithin((cmd)->cmd.geosearch(key, GeoSearch.fromMember(member),
				GeoSearch.byBox(width, height, geoUnitConverter.convert(unit)),
				geoSearchArgumentConverter.convert(argument)), args);
	}

	@Override
	public List<GeoRadius> geoSearch(final String key, final double longitude, final double latitude,
									 final double width, final double height, final GeoUnit unit,
									 final GeoSearchArgument argument) {
		return geoSearch(SafeEncoder.encode(key), longitude, latitude, width, height, unit, argument);
	}

	@Override
	public List<GeoRadius> geoSearch(final byte[] key, final double longitude, final double latitude,
									 final double width, final double height, final GeoUnit unit,
									 final GeoSearchArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add("FROMLONLAT").add(longitude).add(latitude)
				.add("BYBOX").add(width).add(height).add(unit).add(argument);
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		final GeoSearchArgumentConverter geoSearchArgumentConverter = new GeoSearchArgumentConverter();
		return geoSearchWithin((cmd)->cmd.geosearch(key, GeoSearch.fromCoordinates(longitude, latitude),
				GeoSearch.byBox(width, height, geoUnitConverter.convert(unit)),
				geoSearchArgumentConverter.convert(argument)), args);
	}

	@Override
	public Long geoSearchStore(final String destKey, final String key, final String member, final double radius,
							   final GeoUnit unit) {
		return geoSearchStore(SafeEncoder.encode(destKey), SafeEncoder.encode(key), SafeEncoder.encode(member), radius,
				unit);
	}

	@Override
	public Long geoSearchStore(final byte[] destKey, final byte[] key, final byte[] member, final double radius,
							   final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(key).add("FROMMEMBER").add(member).add("BYRADIUS")
				.add(radius).add(unit);
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		return geoSearchStore((cmd)->cmd.geosearchstore(destKey, key, GeoSearch.fromMember(member),
				GeoSearch.byRadius(radius, geoUnitConverter.convert(unit)), new GeoArgs(), false), args);
	}

	@Override
	public Long geoSearchStore(final String destKey, final String key, final double longitude, final double latitude,
							   final double radius, final GeoUnit unit) {
		return geoSearchStore(SafeEncoder.encode(destKey), SafeEncoder.encode(key), longitude, latitude, radius, unit);
	}

	@Override
	public Long geoSearchStore(final byte[] destKey, final byte[] key, final double longitude, final double latitude,
							   final double radius, final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(key).add("FROMLONLAT").add(longitude).add(latitude)
				.add("BYRADIUS").add(radius).add(unit);
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		return geoSearchStore((cmd)->cmd.geosearchstore(destKey, key, GeoSearch.fromCoordinates(longitude, latitude),
				GeoSearch.byRadius(radius, geoUnitConverter.convert(unit)), new GeoArgs(), false), args);
	}

	@Override
	public Long geoSearchStore(final String destKey, final String key, final String member, final double width,
							   final double height, final GeoUnit unit) {
		return geoSearchStore(SafeEncoder.encode(destKey), SafeEncoder.encode(key), SafeEncoder.encode(member), width,
				height, unit);
	}

	@Override
	public Long geoSearchStore(final byte[] destKey, final byte[] key, final byte[] member, final double width,
							   final double height, final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(key).add("FROMMEMBER").add(member).add("BYBOX").add(width)
				.add(height).add(unit);
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		return geoSearchStore((cmd)->cmd.geosearchstore(destKey, key, GeoSearch.fromMember(member),
				GeoSearch.byBox(width, height, geoUnitConverter.convert(unit)), new GeoArgs(), false), args);
	}

	@Override
	public Long geoSearchStore(final String destKey, final String key, final double longitude, final double latitude,
							   final double width, final double height, final GeoUnit unit) {
		return geoSearchStore(SafeEncoder.encode(destKey), SafeEncoder.encode(key), longitude, latitude, width, height,
				unit);
	}

	@Override
	public Long geoSearchStore(final byte[] destKey, final byte[] key, final double longitude, final double latitude,
							   final double width, final double height, final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(key).add("FROMLONLAT").add(longitude).add(latitude)
				.add("BYBOX").add(width).add(height).add(unit);
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		return geoSearchStore((cmd)->cmd.geosearchstore(destKey, key, GeoSearch.fromCoordinates(longitude, latitude),
				GeoSearch.byBox(width, height, geoUnitConverter.convert(unit)), new GeoArgs(), false), args);
	}

	@Override
	public Long geoSearchStore(final String destKey, final String key, final String member, final double radius,
							   final GeoUnit unit, final GeoSearchArgument argument) {
		return geoSearchStore(SafeEncoder.encode(destKey), SafeEncoder.encode(key), SafeEncoder.encode(member), radius,
				unit, argument);
	}

	@Override
	public Long geoSearchStore(final byte[] destKey, final byte[] key, final byte[] member, final double radius,
							   final GeoUnit unit, final GeoSearchArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add("FROMMEMBER").add(member).add("BYRADIUS")
				.add(radius).add(unit).add(argument);
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		final GeoSearchArgumentConverter geoSearchArgumentConverter = new GeoSearchArgumentConverter();
		return geoSearchStore((cmd)->cmd.geosearchstore(destKey, key, GeoSearch.fromMember(member),
				GeoSearch.byRadius(radius, geoUnitConverter.convert(unit)),
				geoSearchArgumentConverter.convert(argument), Boolean.TRUE.equals(argument.isWithDist())), args);
	}

	@Override
	public Long geoSearchStore(final String destKey, final String key, final double longitude, final double latitude,
							   final double radius, final GeoUnit unit, final GeoSearchArgument argument) {
		return geoSearchStore(SafeEncoder.encode(destKey), SafeEncoder.encode(key), longitude, latitude, radius, unit,
				argument);
	}

	@Override
	public Long geoSearchStore(final byte[] destKey, final byte[] key, final double longitude, final double latitude,
							   final double radius, final GeoUnit unit, final GeoSearchArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add("FROMLONLAT").add(longitude).add(latitude)
				.add("BYRADIUS").add(radius).add(unit).add(argument);
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		final GeoSearchArgumentConverter geoSearchArgumentConverter = new GeoSearchArgumentConverter();
		return geoSearchStore((cmd)->cmd.geosearchstore(destKey, key, GeoSearch.fromCoordinates(longitude, latitude),
				GeoSearch.byRadius(radius, geoUnitConverter.convert(unit)),
				geoSearchArgumentConverter.convert(argument), Boolean.TRUE.equals(argument.isWithDist())), args);
	}

	@Override
	public Long geoSearchStore(final String destKey, final String key, final String member, final double width,
							   final double height, final GeoUnit unit, final GeoSearchArgument argument) {
		return geoSearchStore(SafeEncoder.encode(destKey), SafeEncoder.encode(key), SafeEncoder.encode(member), width,
				height, unit, argument);
	}

	@Override
	public Long geoSearchStore(final byte[] destKey, final byte[] key, final byte[] member, final double width,
							   final double height, final GeoUnit unit, final GeoSearchArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add("FROMMEMBER").add(member).add("BYBOX").add(width)
				.add(height).add(unit).add(argument);
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		final GeoSearchArgumentConverter geoSearchArgumentConverter = new GeoSearchArgumentConverter();
		return geoSearchStore((cmd)->cmd.geosearchstore(destKey, key, GeoSearch.fromMember(member),
				GeoSearch.byBox(width, height, geoUnitConverter.convert(unit)),
				geoSearchArgumentConverter.convert(argument), Boolean.TRUE.equals(argument.isWithDist())), args);
	}

	@Override
	public Long geoSearchStore(final String destKey, final String key, final double longitude, final double latitude,
							   final double width, final double height, final GeoUnit unit,
							   final GeoSearchArgument argument) {
		return geoSearchStore(SafeEncoder.encode(destKey), SafeEncoder.encode(key), longitude, latitude, width, height,
				unit, argument);
	}

	@Override
	public Long geoSearchStore(final byte[] destKey, final byte[] key, final double longitude, final double latitude,
							   final double width, final double height, final GeoUnit unit,
							   final GeoSearchArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add("FROMLONLAT").add(longitude).add(latitude)
				.add("BYBOX").add(width).add(height).add(unit).add(argument);
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		final GeoSearchArgumentConverter geoSearchArgumentConverter = new GeoSearchArgumentConverter();
		return geoSearchStore((cmd)->cmd.geosearchstore(destKey, key, GeoSearch.fromCoordinates(longitude, latitude),
				GeoSearch.byBox(width, height, geoUnitConverter.convert(unit)),
				geoSearchArgumentConverter.convert(argument), Boolean.TRUE.equals(argument.isWithDist())), args);
	}

	private List<GeoRadius> geoSearch(
			final com.buession.redis.core.Command.Executor<io.lettuce.core.RedisCommands<byte[], byte[]>, Set<byte[]>> executor,
			final CommandArguments args) {
		return executeCommand(Command.GEOSEARCH, args, executor,
				new SetListConverter<>(new GeoRadiusGeneralResultConverter()));
	}

	private List<GeoRadius> geoSearchWithin(
			final com.buession.redis.core.Command.Executor<io.lettuce.core.RedisCommands<byte[], byte[]>, List<GeoWithin<byte[]>>> executor,
			final CommandArguments args) {
		return executeCommand(Command.GEOSEARCH, args, executor, new ListConverter<>(new GeoRadiusResponseConverter()));
	}

	private Long geoAdd(
			final com.buession.redis.core.Command.Executor<io.lettuce.core.RedisCommands<byte[], byte[]>, Long> executor,
			final CommandArguments args) {
		return executeCommand(Command.GEOADD, args, executor, (v)->v);
	}

	private Long geoSearchStore(
			final com.buession.redis.core.Command.Executor<io.lettuce.core.RedisCommands<byte[], byte[]>, Long> executor,
			final CommandArguments args) {
		return executeCommand(Command.GEOSEARCHSTORE, args, executor, (v)->v);
	}

	@SuppressWarnings("unchecked")
	private static GeoValue<String>[] stringMemberCoordinatesToGeoValue(final Map<String, Geo> memberCoordinates) {
		return memberCoordinates.entrySet().stream()
				.map((e)->GeoValue.just(e.getValue().getLongitude(), e.getValue().getLatitude(),
						SafeEncoder.encode(e.getKey()))).toArray(GeoValue[]::new);
	}

	@SuppressWarnings("unchecked")
	private static GeoValue<byte[]>[] byteMemberCoordinatesToGeoValue(final Map<byte[], Geo> memberCoordinates) {
		return memberCoordinates.entrySet().stream()
				.map((e)->GeoValue.just(e.getValue().getLongitude(), e.getValue().getLatitude(), e.getKey()))
				.toArray(GeoValue[]::new);
	}

}
