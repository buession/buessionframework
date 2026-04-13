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
import com.buession.redis.core.command.args.geo.GeoStoreOption;
import com.buession.redis.core.command.args.geo.GeoUnit;
import com.buession.redis.core.Keyword;
import com.buession.redis.core.command.args.NxXx;
import com.buession.redis.core.command.RedisCommand;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.GeoCommands;
import com.buession.redis.core.command.args.geo.GeoRadiusArgument;
import com.buession.redis.core.command.args.geo.GeoSearchArgument;
import com.buession.redis.core.internal.convert.lettuce.params.GeoUnitConverter;
import com.buession.redis.core.internal.convert.lettuce.response.GeoCoordinateConverter;
import com.buession.redis.core.internal.convert.lettuce.response.GeoRadiusGeneralResultConverter;
import com.buession.redis.core.internal.convert.lettuce.response.GeoRadiusResponseConverter;
import com.buession.redis.core.internal.lettuce.args.LettuceGeoAddArgs;
import com.buession.redis.core.internal.lettuce.args.LettuceGeoArgs;
import com.buession.redis.core.internal.lettuce.args.LettuceGeoRadiusStoreArgs;
import com.buession.redis.utils.SafeEncoder;
import io.lettuce.core.GeoAddArgs;
import io.lettuce.core.GeoArgs;
import io.lettuce.core.GeoRadiusStoreArgs;
import io.lettuce.core.GeoSearch;
import io.lettuce.core.GeoValue;
import io.lettuce.core.Value;

import java.util.List;

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

	@SuppressWarnings({"unchecked"})
	@Override
	public Long geoAdd(final String key, final KeyValue<String, Geo>... memberCoordinates) {
		final CommandArguments args = CommandArguments.create(key).add(memberCoordinates);
		return executeCommand(RedisCommand.GEOADD, args,
				(cmd)->cmd.geoadd(SafeEncoder.encode(key), stringMemberCoordinatesToGeoValue(memberCoordinates)),
				(cmd)->cmd.geoadd(SafeEncoder.encode(key), stringMemberCoordinatesToGeoValue(memberCoordinates)));
	}

	@SuppressWarnings({"unchecked"})
	@Override
	public Long geoAdd(final byte[] key, final KeyValue<byte[], Geo>... memberCoordinates) {
		final CommandArguments args = CommandArguments.create(key).add(memberCoordinates);
		return executeCommand(RedisCommand.GEOADD, args,
				(cmd)->cmd.geoadd(key, byteMemberCoordinatesToGeoValue(memberCoordinates)),
				(cmd)->cmd.geoadd(key, byteMemberCoordinatesToGeoValue(memberCoordinates)));
	}

	@SuppressWarnings({"unchecked"})
	@Override
	public Long geoAdd(final String key, final NxXx nxXx, final KeyValue<String, Geo>... memberCoordinates) {
		final CommandArguments args = CommandArguments.create(key).add(nxXx).add(memberCoordinates);
		return geoAdd(rawKey(key), new LettuceGeoAddArgs(nxXx), memberCoordinates, args);
	}

	@SuppressWarnings({"unchecked"})
	@Override
	public Long geoAdd(final byte[] key, final NxXx nxXx, final KeyValue<byte[], Geo>... memberCoordinates) {
		final CommandArguments args = CommandArguments.create(key).add(nxXx).add(memberCoordinates);
		return geoAdd(rawKey(key), new LettuceGeoAddArgs(nxXx), memberCoordinates, args);
	}

	@SuppressWarnings({"unchecked"})
	@Override
	public Long geoAdd(final String key, final NxXx nxXx, final boolean ch,
	                   final KeyValue<String, Geo>... memberCoordinates) {
		final CommandArguments args = CommandArguments.create(key).add(nxXx).add(ch ? "CH" : null)
				.add(memberCoordinates);
		return geoAdd(rawKey(key), new LettuceGeoAddArgs(nxXx, ch), memberCoordinates, args);
	}

	@SuppressWarnings({"unchecked"})
	@Override
	public Long geoAdd(final byte[] key, final NxXx nxXx, final boolean ch,
	                   final KeyValue<byte[], Geo>... memberCoordinates) {
		final CommandArguments args = CommandArguments.create(key).add(nxXx).add(ch ? "CH" : null)
				.add(memberCoordinates);
		return geoAdd(rawKey(key), new LettuceGeoAddArgs(nxXx, ch), memberCoordinates, args);
	}

	@SuppressWarnings({"unchecked"})
	@Override
	public Long geoAdd(final String key, final boolean ch, final KeyValue<String, Geo>... memberCoordinates) {
		final CommandArguments args = CommandArguments.create(key).add(ch ? "CH" : null).add(memberCoordinates);
		return geoAdd(rawKey(key), new LettuceGeoAddArgs(ch), memberCoordinates, args);
	}

	@SuppressWarnings({"unchecked"})
	@Override
	public Long geoAdd(final byte[] key, final boolean ch, final KeyValue<byte[], Geo>... memberCoordinates) {
		final CommandArguments args = CommandArguments.create(key).add(ch ? "CH" : null).add(memberCoordinates);
		return geoAdd(rawKey(key), new LettuceGeoAddArgs(ch), memberCoordinates, args);
	}

	@Override
	public Double geoDist(final String key, final String member1, final String member2) {
		final CommandArguments args = CommandArguments.create(key).add(member1, member2);
		return executeCommand(RedisCommand.GEODIST, args,
				(cmd)->cmd.geodist(rawBinaryKey(key), SafeEncoder.encode(member1), SafeEncoder.encode(member2),
						GeoArgs.Unit.m),
				(cmd)->cmd.geodist(rawBinaryKey(key), SafeEncoder.encode(member1), SafeEncoder.encode(member2),
						GeoArgs.Unit.m));
	}

	@Override
	public Double geoDist(final byte[] key, final byte[] member1, final byte[] member2) {
		final CommandArguments args = CommandArguments.create(key).add(member1, member2);
		return executeCommand(RedisCommand.GEODIST, args,
				(cmd)->cmd.geodist(rawKey(key), member1, member2, GeoArgs.Unit.m),
				(cmd)->cmd.geodist(rawKey(key), member1, member2, GeoArgs.Unit.m));
	}

	@Override
	public Double geoDist(final String key, final String member1, final String member2, final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(key).add(member1, member2).add(unit);
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		return executeCommand(RedisCommand.GEODIST, args,
				(cmd)->cmd.geodist(rawBinaryKey(key), SafeEncoder.encode(member1), SafeEncoder.encode(member2),
						geoUnitConverter.convert(unit)),
				(cmd)->cmd.geodist(rawBinaryKey(key), SafeEncoder.encode(member1), SafeEncoder.encode(member2),
						geoUnitConverter.convert(unit)));
	}

	@Override
	public Double geoDist(final byte[] key, final byte[] member1, final byte[] member2, final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(key).add(member1, member2).add(unit);
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		return executeCommand(RedisCommand.GEODIST, args,
				(cmd)->cmd.geodist(rawKey(key), member1, member2, geoUnitConverter.convert(unit)),
				(cmd)->cmd.geodist(rawKey(key), member1, member2, geoUnitConverter.convert(unit)));
	}

	@Override
	public List<String> geoHash(final String key, final String... members) {
		final CommandArguments args = CommandArguments.create(key).add(members);
		return executeCommand(RedisCommand.GEOHASH, args,
				(cmd)->cmd.geohash(rawBinaryKey(key), SafeEncoder.encode(members)),
				(cmd)->cmd.geohash(rawBinaryKey(key), SafeEncoder.encode(members)),
				new ListConverter<>(Value::getValue));
	}

	@Override
	public List<byte[]> geoHash(final byte[] key, final byte[]... members) {
		final CommandArguments args = CommandArguments.create(key).add(members);
		return executeCommand(RedisCommand.GEOHASH, args, (cmd)->cmd.geohash(rawKey(key), members),
				(cmd)->cmd.geohash(rawKey(key), members),
				new ListConverter<>((v)->SafeEncoder.encode(v.getValue())));
	}

	@Override
	public List<Geo> geoPos(final String key, final String... members) {
		final CommandArguments args = CommandArguments.create(key).add(SafeEncoder.encode(members));
		return executeCommand(RedisCommand.GEOPOS, args,
				(cmd)->cmd.geopos(rawBinaryKey(key), SafeEncoder.encode(members)),
				(cmd)->cmd.geopos(rawBinaryKey(key), SafeEncoder.encode(members)),
				new ListConverter<>(new GeoCoordinateConverter()));
	}

	@Override
	public List<Geo> geoPos(final byte[] key, final byte[]... members) {
		final CommandArguments args = CommandArguments.create(key).add(members);
		return executeCommand(RedisCommand.GEOPOS, args, (cmd)->cmd.geopos(rawKey(key), members),
				(cmd)->cmd.geopos(rawKey(key), members),
				new ListConverter<>(new GeoCoordinateConverter()));
	}

	@Override
	public List<GeoRadius> geoRadius(final String key, final double longitude, final double latitude,
	                                 final double radius, final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(key).add(longitude, latitude).add(radius, unit);
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		return executeCommand(RedisCommand.GEORADIUS, args,
				(cmd)->cmd.georadius(rawBinaryKey(key), longitude, latitude, radius, geoUnitConverter.convert(unit)),
				(cmd)->cmd.georadius(rawBinaryKey(key), longitude, latitude, radius, geoUnitConverter.convert(unit)),
				new SetListConverter<>(new GeoRadiusGeneralResultConverter()));
	}

	@Override
	public List<GeoRadius> geoRadius(final byte[] key, final double longitude, final double latitude,
	                                 final double radius, final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(key).add(longitude, latitude).add(radius, unit);
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		return executeCommand(RedisCommand.GEORADIUS, args,
				(cmd)->cmd.georadius(rawKey(key), longitude, latitude, radius, geoUnitConverter.convert(unit)),
				(cmd)->cmd.georadius(rawKey(key), longitude, latitude, radius, geoUnitConverter.convert(unit)),
				new SetListConverter<>(new GeoRadiusGeneralResultConverter()));
	}

	@Override
	public List<GeoRadius> geoRadius(final String key, final double longitude, final double latitude,
	                                 final double radius, final GeoUnit unit, final GeoRadiusArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(longitude, latitude).add(radius, unit)
				.add(argument);
		return geoRadius(rawBinaryKey(key), longitude, latitude, radius, unit, new LettuceGeoArgs(argument), args);
	}

	@Override
	public List<GeoRadius> geoRadius(final byte[] key, final double longitude, final double latitude,
	                                 final double radius, final GeoUnit unit, final GeoRadiusArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(longitude, latitude).add(radius, unit)
				.add(argument);
		return geoRadius(rawKey(key), longitude, latitude, radius, unit, new LettuceGeoArgs(argument), args);
	}

	@Override
	public List<GeoRadius> geoRadius(final String key, final double longitude, final double latitude,
	                                 final double radius, final GeoUnit unit, final GeoRadiusArgument argument,
	                                 final int count) {
		final CommandArguments args = CommandArguments.create(key).add(longitude, latitude).add(radius, unit)
				.add(argument).add(Keyword.Common.COUNT, count);
		return geoRadius(rawBinaryKey(key), longitude, latitude, radius, unit, new LettuceGeoArgs(argument, count),
				args);
	}

	@Override
	public List<GeoRadius> geoRadius(final byte[] key, final double longitude, final double latitude,
	                                 final double radius, final GeoUnit unit, final GeoRadiusArgument argument,
	                                 final int count) {
		final CommandArguments args = CommandArguments.create(key).add(longitude, latitude).add(radius, unit)
				.add(argument).add(Keyword.Common.COUNT, count);
		return geoRadius(rawKey(key), longitude, latitude, radius, unit, new LettuceGeoArgs(argument, count), args);
	}

	@Override
	public List<GeoRadius> geoRadius(final String key, final double longitude, final double latitude,
	                                 final double radius, final GeoUnit unit, final GeoRadiusArgument argument,
	                                 final int count, final boolean any) {
		final CommandArguments args = CommandArguments.create(key).add(longitude, latitude).add(radius, unit)
				.add(argument).add(Keyword.Common.COUNT, count).add(any ? Keyword.Common.ANY : null);
		return geoRadius(rawBinaryKey(key), longitude, latitude, radius, unit, new LettuceGeoArgs(argument, count, any),
				args);
	}

	@Override
	public List<GeoRadius> geoRadius(final byte[] key, final double longitude, final double latitude,
	                                 final double radius, final GeoUnit unit, final GeoRadiusArgument argument,
	                                 final int count, final boolean any) {
		final CommandArguments args = CommandArguments.create(key).add(longitude, latitude).add(radius, unit)
				.add(argument).add(Keyword.Common.COUNT, count).add(any ? Keyword.Common.ANY : null);
		return geoRadius(rawKey(key), longitude, latitude, radius, unit, new LettuceGeoArgs(argument, count, any),
				args);
	}

	@Override
	public List<GeoRadius> geoRadius(final String key, final double longitude, final double latitude,
	                                 final double radius, final GeoUnit unit, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(longitude, latitude).add(radius, unit)
				.add(Keyword.Common.COUNT, count);
		return geoRadius(rawBinaryKey(key), longitude, latitude, radius, unit, new LettuceGeoArgs(count), args);
	}

	@Override
	public List<GeoRadius> geoRadius(final byte[] key, final double longitude, final double latitude,
	                                 final double radius, final GeoUnit unit, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(longitude, latitude).add(radius, unit)
				.add(Keyword.Common.COUNT, count);
		return geoRadius(rawKey(key), longitude, latitude, radius, unit, new LettuceGeoArgs(count), args);
	}

	@Override
	public List<GeoRadius> geoRadius(final String key, final double longitude, final double latitude,
	                                 final double radius, final GeoUnit unit, final int count, final boolean any) {
		final CommandArguments args = CommandArguments.create(key).add(longitude, latitude).add(radius, unit)
				.add(Keyword.Common.COUNT, count).add(any ? Keyword.Common.ANY : null);
		return geoRadius(rawBinaryKey(key), longitude, latitude, radius, unit, new LettuceGeoArgs(count, any), args);
	}

	@Override
	public List<GeoRadius> geoRadius(final byte[] key, final double longitude, final double latitude,
	                                 final double radius, final GeoUnit unit, final int count, final boolean any) {
		final CommandArguments args = CommandArguments.create(key).add(longitude, latitude).add(radius, unit)
				.add(Keyword.Common.COUNT, count).add(any ? Keyword.Common.ANY : null);
		return geoRadius(rawKey(key), longitude, latitude, radius, unit, new LettuceGeoArgs(count, any), args);
	}

	@Override
	public Long geoRadius(final String key, final String destKey, final GeoStoreOption storeOption,
	                      final double longitude, final double latitude, final double radius, final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(key).add(longitude, latitude).add(radius, unit)
				.add(storeOption, destKey);
		return geoRadius(rawBinaryKey(key), longitude, latitude, radius, unit,
				new LettuceGeoRadiusStoreArgs<>(rawBinaryKey(key), storeOption), args);
	}

	@Override
	public Long geoRadius(final byte[] key, final byte[] destKey, final GeoStoreOption storeOption,
	                      final double longitude, final double latitude, final double radius, final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(key).add(longitude, latitude).add(radius, unit)
				.add(storeOption, destKey);
		return geoRadius(rawKey(key), longitude, latitude, radius, unit,
				new LettuceGeoRadiusStoreArgs<>(rawKey(key), storeOption), args);
	}

	@Override
	public Long geoRadius(final String key, final String destKey, final GeoStoreOption storeOption,
	                      final double longitude, final double latitude, final double radius, final GeoUnit unit,
	                      final GeoRadiusArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(longitude, latitude).add(radius, unit)
				.add(argument).add(storeOption, destKey);
		return geoRadius(rawBinaryKey(key), longitude, latitude, radius, unit,
				new LettuceGeoRadiusStoreArgs<>(rawBinaryKey(key), storeOption, argument), args);
	}

	@Override
	public Long geoRadius(final byte[] key, final byte[] destKey, final GeoStoreOption storeOption,
	                      final double longitude, final double latitude, final double radius, final GeoUnit unit,
	                      final GeoRadiusArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(longitude, latitude).add(radius, unit)
				.add(argument).add(storeOption, destKey);
		return geoRadius(rawKey(key), longitude, latitude, radius, unit,
				new LettuceGeoRadiusStoreArgs<>(rawKey(key), storeOption, argument), args);
	}

	@Override
	public Long geoRadius(final String key, final String destKey, final GeoStoreOption storeOption,
	                      final double longitude, final double latitude, final double radius, final GeoUnit unit,
	                      final GeoRadiusArgument argument, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(longitude, latitude).add(radius, unit)
				.add(argument).add(Keyword.Common.COUNT, count).add(storeOption, destKey);
		return geoRadius(rawBinaryKey(key), longitude, latitude, radius, unit,
				new LettuceGeoRadiusStoreArgs<>(rawBinaryKey(key), storeOption, argument, count), args);
	}

	@Override
	public Long geoRadius(final byte[] key, final byte[] destKey, final GeoStoreOption storeOption,
	                      final double longitude, final double latitude, final double radius, final GeoUnit unit,
	                      final GeoRadiusArgument argument, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(longitude, latitude).add(radius, unit)
				.add(argument).add(Keyword.Common.COUNT, count).add(storeOption, destKey);
		return geoRadius(rawKey(key), longitude, latitude, radius, unit,
				new LettuceGeoRadiusStoreArgs<>(rawKey(key), storeOption, argument, count), args);
	}

	@Override
	public Long geoRadius(final String key, final String destKey, final GeoStoreOption storeOption,
	                      final double longitude, final double latitude, final double radius, final GeoUnit unit,
	                      final GeoRadiusArgument argument, final int count, final boolean any) {
		final CommandArguments args = CommandArguments.create(key).add(longitude, latitude).add(radius, unit)
				.add(argument).add(Keyword.Common.COUNT, count).add(any ? Keyword.Common.ANY : null)
				.add(storeOption, destKey);
		return geoRadius(rawBinaryKey(key), longitude, latitude, radius, unit,
				new LettuceGeoRadiusStoreArgs<>(rawBinaryKey(key), storeOption, argument, count), args);
	}

	@Override
	public Long geoRadius(final byte[] key, final byte[] destKey, final GeoStoreOption storeOption,
	                      final double longitude, final double latitude, final double radius, final GeoUnit unit,
	                      final GeoRadiusArgument argument, final int count, final boolean any) {
		final CommandArguments args = CommandArguments.create(key).add(longitude, latitude).add(radius, unit)
				.add(argument).add(Keyword.Common.COUNT, count).add(any ? Keyword.Common.ANY : null)
				.add(storeOption, destKey);
		return geoRadius(rawKey(key), longitude, latitude, radius, unit,
				new LettuceGeoRadiusStoreArgs<>(rawKey(key), storeOption, argument, count), args);
	}

	@Override
	public Long geoRadius(final String key, final String destKey, final GeoStoreOption storeOption,
	                      final double longitude, final double latitude, final double radius, final GeoUnit unit,
	                      final int count) {
		final CommandArguments args = CommandArguments.create(key).add(longitude, latitude).add(radius, unit)
				.add(Keyword.Common.COUNT, count).add(storeOption, destKey);
		return geoRadius(rawBinaryKey(key), longitude, latitude, radius, unit,
				new LettuceGeoRadiusStoreArgs<>(rawBinaryKey(key), storeOption, count), args);
	}

	@Override
	public Long geoRadius(final byte[] key, final byte[] destKey, final GeoStoreOption storeOption,
	                      final double longitude, final double latitude, final double radius, final GeoUnit unit,
	                      final int count) {
		final CommandArguments args = CommandArguments.create(key).add(longitude, latitude).add(radius, unit)
				.add(Keyword.Common.COUNT, count).add(storeOption, destKey);
		return geoRadius(rawKey(key), longitude, latitude, radius, unit,
				new LettuceGeoRadiusStoreArgs<>(rawKey(key), storeOption, count), args);
	}

	@Override
	public Long geoRadius(final String key, final String destKey, final GeoStoreOption storeOption,
	                      final double longitude, final double latitude, final double radius, final GeoUnit unit,
	                      final int count, final boolean any) {
		final CommandArguments args = CommandArguments.create(key).add(longitude, latitude).add(radius, unit)
				.add(Keyword.Common.COUNT, count).add(any ? Keyword.Common.ANY : null).add(storeOption, destKey);
		return geoRadius(rawBinaryKey(key), longitude, latitude, radius, unit,
				new LettuceGeoRadiusStoreArgs<>(rawBinaryKey(key), storeOption, count, any), args);
	}

	@Override
	public Long geoRadius(final byte[] key, final byte[] destKey, final GeoStoreOption storeOption,
	                      final double longitude, final double latitude, final double radius, final GeoUnit unit,
	                      final int count, final boolean any) {
		final CommandArguments args = CommandArguments.create(key).add(longitude, latitude).add(radius, unit)
				.add(Keyword.Common.COUNT, count).add(any ? Keyword.Common.ANY : null).add(storeOption, destKey);
		return geoRadius(rawKey(key), longitude, latitude, radius, unit,
				new LettuceGeoRadiusStoreArgs<>(rawKey(key), storeOption, count, any), args);
	}

	@Override
	public List<GeoRadius> geoRadiusRo(final String key, final double longitude, final double latitude,
	                                   final double radius, final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(key).add(longitude, latitude).add(radius, unit);
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		return executeCommand(RedisCommand.GEORADIUS_RO, args,
				(cmd)->cmd.georadius(rawBinaryKey(key), longitude, latitude, radius, geoUnitConverter.convert(unit)),
				(cmd)->cmd.georadius(rawBinaryKey(key), longitude, latitude, radius, geoUnitConverter.convert(unit)),
				new SetListConverter<>(new GeoRadiusGeneralResultConverter()));
	}

	@Override
	public List<GeoRadius> geoRadiusRo(final byte[] key, final double longitude, final double latitude,
	                                   final double radius, final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(key).add(longitude, latitude).add(radius, unit);
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		return executeCommand(RedisCommand.GEORADIUS_RO, args,
				(cmd)->cmd.georadius(rawKey(key), longitude, latitude, radius, geoUnitConverter.convert(unit)),
				(cmd)->cmd.georadius(rawKey(key), longitude, latitude, radius, geoUnitConverter.convert(unit)),
				new SetListConverter<>(new GeoRadiusGeneralResultConverter()));
	}

	@Override
	public List<GeoRadius> geoRadiusRo(final String key, final double longitude, final double latitude,
	                                   final double radius, final GeoUnit unit, final GeoRadiusArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(longitude, latitude).add(radius, unit)
				.add(argument);
		return geoRadiusRo(rawBinaryKey(key), longitude, latitude, radius, unit, new LettuceGeoArgs(argument), args);
	}

	@Override
	public List<GeoRadius> geoRadiusRo(final byte[] key, final double longitude, final double latitude,
	                                   final double radius, final GeoUnit unit, final GeoRadiusArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(longitude, latitude).add(radius, unit)
				.add(argument);
		return geoRadiusRo(rawKey(key), longitude, latitude, radius, unit, new LettuceGeoArgs(argument), args);
	}

	@Override
	public List<GeoRadius> geoRadiusRo(final String key, final double longitude, final double latitude,
	                                   final double radius, final GeoUnit unit, final GeoRadiusArgument argument,
	                                   final int count) {
		final CommandArguments args = CommandArguments.create(key).add(longitude, latitude).add(radius, unit)
				.add(argument).add(Keyword.Common.COUNT, count);
		return geoRadiusRo(rawBinaryKey(key), longitude, latitude, radius, unit, new LettuceGeoArgs(argument, count),
				args);
	}

	@Override
	public List<GeoRadius> geoRadiusRo(final byte[] key, final double longitude, final double latitude,
	                                   final double radius, final GeoUnit unit, final GeoRadiusArgument argument,
	                                   final int count) {
		final CommandArguments args = CommandArguments.create(key).add(longitude, latitude).add(radius, unit)
				.add(argument).add(Keyword.Common.COUNT, count);
		return geoRadiusRo(rawKey(key), longitude, latitude, radius, unit, new LettuceGeoArgs(argument, count), args);
	}

	@Override
	public List<GeoRadius> geoRadiusRo(final String key, final double longitude, final double latitude,
	                                   final double radius, final GeoUnit unit, final GeoRadiusArgument argument,
	                                   final int count, final boolean any) {
		final CommandArguments args = CommandArguments.create(key).add(longitude, latitude).add(radius, unit)
				.add(argument).add(Keyword.Common.COUNT, count).add(any ? Keyword.Common.ANY : null);
		return geoRadiusRo(rawBinaryKey(key), longitude, latitude, radius, unit,
				new LettuceGeoArgs(argument, count, any), args);
	}

	@Override
	public List<GeoRadius> geoRadiusRo(final byte[] key, final double longitude, final double latitude,
	                                   final double radius, final GeoUnit unit, final GeoRadiusArgument argument,
	                                   final int count, final boolean any) {
		final CommandArguments args = CommandArguments.create(key).add(longitude, latitude).add(radius, unit)
				.add(argument).add(Keyword.Common.COUNT, count).add(any ? Keyword.Common.ANY : null);
		return geoRadiusRo(rawKey(key), longitude, latitude, radius, unit, new LettuceGeoArgs(argument, count, any),
				args);
	}

	@Override
	public List<GeoRadius> geoRadiusRo(final String key, final double longitude, final double latitude,
	                                   final double radius, final GeoUnit unit, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(longitude, latitude).add(radius, unit)
				.add(Keyword.Common.COUNT, count);
		return geoRadiusRo(rawBinaryKey(key), longitude, latitude, radius, unit, new LettuceGeoArgs(count), args);
	}

	@Override
	public List<GeoRadius> geoRadiusRo(final byte[] key, final double longitude, final double latitude,
	                                   final double radius, final GeoUnit unit, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(longitude, latitude).add(radius, unit)
				.add(Keyword.Common.COUNT, count);
		return geoRadiusRo(rawKey(key), longitude, latitude, radius, unit, new LettuceGeoArgs(count), args);
	}

	@Override
	public List<GeoRadius> geoRadiusRo(final String key, final double longitude, final double latitude,
	                                   final double radius, final GeoUnit unit, final int count, final boolean any) {
		final CommandArguments args = CommandArguments.create(key).add(longitude, latitude).add(radius, unit)
				.add(Keyword.Common.COUNT, count).add(any ? Keyword.Common.ANY : null);
		return geoRadiusRo(rawBinaryKey(key), longitude, latitude, radius, unit, new LettuceGeoArgs(count, any), args);
	}

	@Override
	public List<GeoRadius> geoRadiusRo(final byte[] key, final double longitude, final double latitude,
	                                   final double radius, final GeoUnit unit, final int count, final boolean any) {
		final CommandArguments args = CommandArguments.create(key).add(longitude, latitude).add(radius, unit)
				.add(Keyword.Common.COUNT, count).add(any ? Keyword.Common.ANY : null);
		return geoRadiusRo(rawKey(key), longitude, latitude, radius, unit, new LettuceGeoArgs(count, any), args);
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final String key, final String member, final double radius,
	                                         final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(key, member).add(radius, unit);
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		return executeCommand(RedisCommand.GEORADIUS, args,
				(cmd)->cmd.georadiusbymember(rawBinaryKey(key), SafeEncoder.encode(member), radius,
						geoUnitConverter.convert(unit)),
				(cmd)->cmd.georadiusbymember(rawBinaryKey(key), SafeEncoder.encode(member), radius,
						geoUnitConverter.convert(unit)), new SetListConverter<>(new GeoRadiusGeneralResultConverter()));
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final byte[] key, final byte[] member, final double radius,
	                                         final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(key, member).add(radius, unit);
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		return executeCommand(RedisCommand.GEORADIUS, args,
				(cmd)->cmd.georadiusbymember(rawKey(key), member, radius, geoUnitConverter.convert(unit)),
				(cmd)->cmd.georadiusbymember(rawKey(key), member, radius, geoUnitConverter.convert(unit)),
				new SetListConverter<>(new GeoRadiusGeneralResultConverter()));
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final String key, final String member, final double radius,
	                                         final GeoUnit unit, final GeoRadiusArgument argument) {
		final CommandArguments args = CommandArguments.create(key, member).add(radius, unit).add(argument);
		return geoRadiusByMember(rawBinaryKey(key), SafeEncoder.encode(member), radius, unit,
				new LettuceGeoArgs(argument), args);
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final byte[] key, final byte[] member, final double radius,
	                                         final GeoUnit unit, final GeoRadiusArgument argument) {
		final CommandArguments args = CommandArguments.create(key, member).add(radius, unit).add(argument);
		return geoRadiusByMember(rawKey(key), member, radius, unit, new LettuceGeoArgs(argument), args);
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final String key, final String member, final double radius,
	                                         final GeoUnit unit, final GeoRadiusArgument argument, final int count) {
		final CommandArguments args = CommandArguments.create(key, member).add(radius, unit).add(argument)
				.add(Keyword.Common.COUNT, count);
		return geoRadiusByMember(rawBinaryKey(key), SafeEncoder.encode(member), radius, unit,
				new LettuceGeoArgs(argument, count), args);
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final byte[] key, final byte[] member, final double radius,
	                                         final GeoUnit unit, final GeoRadiusArgument argument, final int count) {
		final CommandArguments args = CommandArguments.create(key, member).add(radius, unit).add(argument)
				.add(Keyword.Common.COUNT, count);
		return geoRadiusByMember(rawKey(key), member, radius, unit, new LettuceGeoArgs(argument, count), args);
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final String key, final String member, final double radius,
	                                         final GeoUnit unit, final GeoRadiusArgument argument, final int count,
	                                         final boolean any) {
		final CommandArguments args = CommandArguments.create(key, member).add(radius, unit).add(argument)
				.add(Keyword.Common.COUNT, count).add(any ? Keyword.Common.ANY : null);
		return geoRadiusByMember(rawBinaryKey(key), SafeEncoder.encode(member), radius, unit,
				new LettuceGeoArgs(argument, count, any), args);
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final byte[] key, final byte[] member, final double radius,
	                                         final GeoUnit unit, final GeoRadiusArgument argument, final int count,
	                                         final boolean any) {
		final CommandArguments args = CommandArguments.create(key, member).add(radius, unit).add(argument)
				.add(Keyword.Common.COUNT, count).add(any ? Keyword.Common.ANY : null);
		return geoRadiusByMember(rawKey(key), member, radius, unit, new LettuceGeoArgs(argument, count, any), args);
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final String key, final String member, final double radius,
	                                         final GeoUnit unit, final int count) {
		final CommandArguments args = CommandArguments.create(key, member).add(radius, unit)
				.add(Keyword.Common.COUNT, count);
		return geoRadiusByMember(rawBinaryKey(key), SafeEncoder.encode(member), radius, unit, new LettuceGeoArgs(count),
				args);
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final byte[] key, final byte[] member, final double radius,
	                                         final GeoUnit unit, final int count) {
		final CommandArguments args = CommandArguments.create(key, member).add(radius, unit)
				.add(Keyword.Common.COUNT, count);
		return geoRadiusByMember(rawKey(key), member, radius, unit, new LettuceGeoArgs(count), args);
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final String key, final String member, final double radius,
	                                         final GeoUnit unit, final int count, final boolean any) {
		final CommandArguments args = CommandArguments.create(key, member).add(radius, unit)
				.add(Keyword.Common.COUNT, count).add(any ? Keyword.Common.ANY : null);
		return geoRadiusByMember(rawBinaryKey(key), SafeEncoder.encode(member), radius, unit,
				new LettuceGeoArgs(count, any), args);
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final byte[] key, final byte[] member, final double radius,
	                                         final GeoUnit unit, final int count, final boolean any) {
		final CommandArguments args = CommandArguments.create(key, member).add(radius, unit)
				.add(Keyword.Common.COUNT, count).add(any ? Keyword.Common.ANY : null);
		return geoRadiusByMember(rawKey(key), member, radius, unit, new LettuceGeoArgs(count, any), args);
	}

	@Override
	public Long geoRadiusByMember(final String key, final String destKey, final GeoStoreOption storeOption,
	                              final String member, final double radius, final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(key, member).add(radius, unit)
				.add(storeOption, destKey);
		return geoRadiusByMember(rawBinaryKey(key), SafeEncoder.encode(member), radius, unit,
				new LettuceGeoRadiusStoreArgs<>(rawBinaryKey(destKey), storeOption), args);
	}

	@Override
	public Long geoRadiusByMember(final byte[] key, final byte[] destKey, final GeoStoreOption storeOption,
	                              final byte[] member, final double radius, final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(key, member).add(radius, unit)
				.add(storeOption, destKey);
		return geoRadiusByMember(rawKey(key), member, radius, unit,
				new LettuceGeoRadiusStoreArgs<>(rawKey(destKey), storeOption), args);
	}

	@Override
	public Long geoRadiusByMember(final String key, final String destKey, final GeoStoreOption storeOption,
	                              final String member, final double radius, final GeoUnit unit,
	                              final GeoRadiusArgument argument) {
		final CommandArguments args = CommandArguments.create(key, member).add(radius, unit).add(argument)
				.add(storeOption, destKey);
		return geoRadiusByMember(rawBinaryKey(key), SafeEncoder.encode(member), radius, unit,
				new LettuceGeoRadiusStoreArgs<>(rawBinaryKey(destKey), storeOption, argument), args);
	}

	@Override
	public Long geoRadiusByMember(final byte[] key, final byte[] destKey, final GeoStoreOption storeOption,
	                              final byte[] member, final double radius, final GeoUnit unit,
	                              final GeoRadiusArgument argument) {
		final CommandArguments args = CommandArguments.create(key, member).add(radius, unit).add(argument)
				.add(storeOption, destKey);
		return geoRadiusByMember(rawKey(key), member, radius, unit,
				new LettuceGeoRadiusStoreArgs<>(rawKey(destKey), storeOption, argument), args);
	}

	@Override
	public Long geoRadiusByMember(final String key, final String destKey, final GeoStoreOption storeOption,
	                              final String member, final double radius, final GeoUnit unit,
	                              final GeoRadiusArgument argument, final int count) {
		final CommandArguments args = CommandArguments.create(key, member).add(radius, unit).add(argument)
				.add(Keyword.Common.COUNT, count).add(storeOption, destKey);
		return geoRadiusByMember(rawBinaryKey(key), SafeEncoder.encode(member), radius, unit,
				new LettuceGeoRadiusStoreArgs<>(rawBinaryKey(destKey), storeOption, argument, count), args);
	}

	@Override
	public Long geoRadiusByMember(final byte[] key, final byte[] destKey, final GeoStoreOption storeOption,
	                              final byte[] member, final double radius, final GeoUnit unit,
	                              final GeoRadiusArgument argument, final int count) {
		final CommandArguments args = CommandArguments.create(key, member).add(radius, unit).add(argument)
				.add(Keyword.Common.COUNT, count).add(storeOption, destKey);
		return geoRadiusByMember(rawKey(key), member, radius, unit,
				new LettuceGeoRadiusStoreArgs<>(rawKey(destKey), storeOption, argument, count), args);
	}

	@Override
	public Long geoRadiusByMember(final String key, final String destKey, final GeoStoreOption storeOption,
	                              final String member, final double radius, final GeoUnit unit,
	                              final GeoRadiusArgument argument, final int count, final boolean any) {
		final CommandArguments args = CommandArguments.create(key, member).add(radius, unit).add(argument)
				.add(Keyword.Common.COUNT, count).add(any ? Keyword.Common.ANY : null).add(storeOption, destKey);
		return geoRadiusByMember(rawBinaryKey(key), SafeEncoder.encode(member), radius, unit,
				new LettuceGeoRadiusStoreArgs<>(rawBinaryKey(destKey), storeOption, argument, count, any), args);
	}

	@Override
	public Long geoRadiusByMember(final byte[] key, final byte[] destKey, final GeoStoreOption storeOption,
	                              final byte[] member, final double radius, final GeoUnit unit,
	                              final GeoRadiusArgument argument, final int count, final boolean any) {
		final CommandArguments args = CommandArguments.create(key, member).add(radius, unit).add(argument)
				.add(Keyword.Common.COUNT, count).add(any ? Keyword.Common.ANY : null).add(storeOption, destKey);
		return geoRadiusByMember(rawKey(key), member, radius, unit,
				new LettuceGeoRadiusStoreArgs<>(rawKey(destKey), storeOption, argument, count, any), args);
	}

	@Override
	public Long geoRadiusByMember(final String key, final String destKey, final GeoStoreOption storeOption,
	                              final String member, final double radius, final GeoUnit unit, final int count) {
		final CommandArguments args = CommandArguments.create(key, member).add(radius, unit)
				.add(Keyword.Common.COUNT, count).add(storeOption, destKey);
		return geoRadiusByMember(rawBinaryKey(key), SafeEncoder.encode(member), radius, unit,
				new LettuceGeoRadiusStoreArgs<>(rawBinaryKey(destKey), storeOption, count), args);
	}

	@Override
	public Long geoRadiusByMember(final byte[] key, final byte[] destKey, final GeoStoreOption storeOption,
	                              final byte[] member, final double radius, final GeoUnit unit, final int count) {
		final CommandArguments args = CommandArguments.create(key, member).add(radius, unit)
				.add(Keyword.Common.COUNT, count).add(storeOption, destKey);
		return geoRadiusByMember(rawKey(key), member, radius, unit,
				new LettuceGeoRadiusStoreArgs<>(rawKey(destKey), storeOption, count), args);
	}

	@Override
	public Long geoRadiusByMember(final String key, final String destKey, final GeoStoreOption storeOption,
	                              final String member, final double radius, final GeoUnit unit, final int count,
	                              final boolean any) {
		final CommandArguments args = CommandArguments.create(key, member).add(radius, unit)
				.add(Keyword.Common.COUNT, count).add(any ? Keyword.Common.ANY : null).add(storeOption, destKey);
		return geoRadiusByMember(rawBinaryKey(key), SafeEncoder.encode(member), radius, unit,
				new LettuceGeoRadiusStoreArgs<>(rawBinaryKey(destKey), storeOption, count, any), args);
	}

	@Override
	public Long geoRadiusByMember(final byte[] key, final byte[] destKey, final GeoStoreOption storeOption,
	                              final byte[] member, final double radius, final GeoUnit unit, final int count,
	                              final boolean any) {
		final CommandArguments args = CommandArguments.create(key, member).add(radius, unit)
				.add(Keyword.Common.COUNT, count).add(any ? Keyword.Common.ANY : null).add(storeOption, destKey);
		return geoRadiusByMember(rawKey(key), member, radius, unit,
				new LettuceGeoRadiusStoreArgs<>(rawKey(destKey), storeOption, count, any), args);
	}

	@Override
	public List<GeoRadius> geoRadiusByMemberRo(final String key, final String member, final double radius,
	                                           final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(key, member).add(radius, unit);
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		return executeCommand(RedisCommand.GEORADIUS_RO, args,
				(cmd)->cmd.georadiusbymember(rawBinaryKey(key), SafeEncoder.encode(member), radius,
						geoUnitConverter.convert(unit)),
				(cmd)->cmd.georadiusbymember(rawBinaryKey(key), SafeEncoder.encode(member), radius,
						geoUnitConverter.convert(unit)), new SetListConverter<>(new GeoRadiusGeneralResultConverter()));
	}

	@Override
	public List<GeoRadius> geoRadiusByMemberRo(final byte[] key, final byte[] member, final double radius,
	                                           final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(key, member).add(radius, unit);
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		return executeCommand(RedisCommand.GEORADIUS_RO, args,
				(cmd)->cmd.georadiusbymember(rawKey(key), member, radius, geoUnitConverter.convert(unit)),
				(cmd)->cmd.georadiusbymember(rawKey(key), member, radius, geoUnitConverter.convert(unit)),
				new SetListConverter<>(new GeoRadiusGeneralResultConverter()));
	}

	@Override
	public List<GeoRadius> geoRadiusByMemberRo(final String key, final String member, final double radius,
	                                           final GeoUnit unit, final GeoRadiusArgument argument) {
		final CommandArguments args = CommandArguments.create(key, member).add(radius, unit).add(argument);
		return geoRadiusByMemberRo(rawBinaryKey(key), SafeEncoder.encode(member), radius, unit,
				new LettuceGeoArgs(argument), args);
	}

	@Override
	public List<GeoRadius> geoRadiusByMemberRo(final byte[] key, final byte[] member, final double radius,
	                                           final GeoUnit unit, final GeoRadiusArgument argument) {
		final CommandArguments args = CommandArguments.create(key, member).add(radius, unit).add(argument);
		return geoRadiusByMemberRo(rawKey(key), member, radius, unit, new LettuceGeoArgs(argument), args);
	}

	@Override
	public List<GeoRadius> geoRadiusByMemberRo(final String key, final String member, final double radius,
	                                           final GeoUnit unit, final GeoRadiusArgument argument, final int count) {
		final CommandArguments args = CommandArguments.create(key, member).add(radius, unit).add(argument)
				.add(Keyword.Common.COUNT, count);
		return geoRadiusByMemberRo(rawBinaryKey(key), SafeEncoder.encode(member), radius, unit,
				new LettuceGeoArgs(argument, count), args);
	}

	@Override
	public List<GeoRadius> geoRadiusByMemberRo(final byte[] key, final byte[] member, final double radius,
	                                           final GeoUnit unit, final GeoRadiusArgument argument, final int count) {
		final CommandArguments args = CommandArguments.create(key, member).add(radius, unit).add(argument)
				.add(Keyword.Common.COUNT, count);
		return geoRadiusByMemberRo(rawKey(key), member, radius, unit, new LettuceGeoArgs(argument, count), args);
	}

	@Override
	public List<GeoRadius> geoRadiusByMemberRo(final String key, final String member, final double radius,
	                                           final GeoUnit unit, final GeoRadiusArgument argument, final int count,
	                                           final boolean any) {
		final CommandArguments args = CommandArguments.create(key, member).add(radius, unit).add(argument)
				.add(Keyword.Common.COUNT, count).add(any ? Keyword.Common.ANY : null);
		return geoRadiusByMemberRo(rawBinaryKey(key), SafeEncoder.encode(member), radius, unit,
				new LettuceGeoArgs(argument, count, any), args);
	}

	@Override
	public List<GeoRadius> geoRadiusByMemberRo(final byte[] key, final byte[] member, final double radius,
	                                           final GeoUnit unit, final GeoRadiusArgument argument, final int count,
	                                           final boolean any) {
		final CommandArguments args = CommandArguments.create(key, member).add(radius, unit).add(argument)
				.add(Keyword.Common.COUNT, count).add(any ? Keyword.Common.ANY : null);
		return geoRadiusByMemberRo(rawKey(key), member, radius, unit, new LettuceGeoArgs(argument, count, any), args);
	}

	@Override
	public List<GeoRadius> geoRadiusByMemberRo(final String key, final String member, final double radius,
	                                           final GeoUnit unit, final int count) {
		final CommandArguments args = CommandArguments.create(key, member).add(radius, unit)
				.add(Keyword.Common.COUNT, count);
		return geoRadiusByMemberRo(rawBinaryKey(key), SafeEncoder.encode(member), radius, unit,
				new LettuceGeoArgs(count), args);
	}

	@Override
	public List<GeoRadius> geoRadiusByMemberRo(final byte[] key, final byte[] member, final double radius,
	                                           final GeoUnit unit, final int count) {
		final CommandArguments args = CommandArguments.create(key, member).add(radius, unit)
				.add(Keyword.Common.COUNT, count);
		return geoRadiusByMemberRo(rawKey(key), member, radius, unit, new LettuceGeoArgs(count), args);
	}

	@Override
	public List<GeoRadius> geoRadiusByMemberRo(final String key, final String member, final double radius,
	                                           final GeoUnit unit, final int count, final boolean any) {
		final CommandArguments args = CommandArguments.create(key, member).add(radius, unit)
				.add(Keyword.Common.COUNT, count).add(any ? Keyword.Common.ANY : null);
		return geoRadiusByMemberRo(rawBinaryKey(key), SafeEncoder.encode(member), radius, unit,
				new LettuceGeoArgs(count, any), args);
	}

	@Override
	public List<GeoRadius> geoRadiusByMemberRo(final byte[] key, final byte[] member, final double radius,
	                                           final GeoUnit unit, final int count, final boolean any) {
		final CommandArguments args = CommandArguments.create(key, member).add(radius, unit)
				.add(Keyword.Common.COUNT, count).add(any ? Keyword.Common.ANY : null);
		return geoRadiusByMemberRo(rawKey(key), member, radius, unit, new LettuceGeoArgs(count, any), args);
	}

	@Override
	public List<GeoRadius> geoSearch(final String key, final double longitude, final double latitude,
	                                 final double radius, final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(key).add("FROMLONLAT").add(longitude, latitude)
				.add("BYRADIUS").add(radius, unit);
		return geoSearch(rawBinaryKey(key), longitude, latitude, radius, unit, args);
	}

	@Override
	public List<GeoRadius> geoSearch(final byte[] key, final double longitude, final double latitude,
	                                 final double radius, final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(key).add("FROMLONLAT").add(longitude, latitude)
				.add("BYRADIUS").add(radius, unit);
		return geoSearch(rawKey(key), longitude, latitude, radius, unit, args);
	}

	@Override
	public List<GeoRadius> geoSearch(final String key, final double longitude, final double latitude,
	                                 final double radius, final GeoUnit unit, final GeoSearchArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add("FROMLONLAT").add(longitude, latitude)
				.add("BYRADIUS").add(radius, unit).add(argument);
		return geoSearch(rawBinaryKey(key), longitude, latitude, radius, unit, new LettuceGeoArgs(argument), args);
	}

	@Override
	public List<GeoRadius> geoSearch(final byte[] key, final double longitude, final double latitude,
	                                 final double radius, final GeoUnit unit, final GeoSearchArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add("FROMLONLAT").add(longitude, latitude)
				.add("BYRADIUS").add(radius, unit).add(argument);
		return geoSearch(rawKey(key), longitude, latitude, radius, unit, new LettuceGeoArgs(argument), args);
	}

	@Override
	public List<GeoRadius> geoSearch(final String key, final double longitude, final double latitude,
	                                 final double radius, final GeoUnit unit, final GeoSearchArgument argument,
	                                 final int count) {
		final CommandArguments args = CommandArguments.create(key).add("FROMLONLAT").add(longitude, latitude)
				.add("BYRADIUS").add(radius, unit).add(argument).add(Keyword.Common.COUNT, count);
		return geoSearch(rawBinaryKey(key), longitude, latitude, radius, unit, new LettuceGeoArgs(argument, count),
				args);
	}

	@Override
	public List<GeoRadius> geoSearch(final byte[] key, final double longitude, final double latitude,
	                                 final double radius, final GeoUnit unit, final GeoSearchArgument argument,
	                                 final int count) {
		final CommandArguments args = CommandArguments.create(key).add("FROMLONLAT").add(longitude, latitude)
				.add("BYRADIUS").add(radius, unit).add(argument).add(Keyword.Common.COUNT, count);
		return geoSearch(rawKey(key), longitude, latitude, radius, unit, new LettuceGeoArgs(argument, count), args);
	}

	@Override
	public List<GeoRadius> geoSearch(final String key, final double longitude, final double latitude,
	                                 final double radius, final GeoUnit unit, final GeoSearchArgument argument,
	                                 final int count, final boolean any) {
		final CommandArguments args = CommandArguments.create(key).add("FROMLONLAT").add(longitude, latitude)
				.add("BYRADIUS").add(radius, unit).add(argument).add(Keyword.Common.COUNT, count)
				.add(any ? Keyword.Common.ANY : null);
		return geoSearch(rawBinaryKey(key), longitude, latitude, radius, unit, new LettuceGeoArgs(argument, count, any),
				args);
	}

	@Override
	public List<GeoRadius> geoSearch(final byte[] key, final double longitude, final double latitude,
	                                 final double radius, final GeoUnit unit, final GeoSearchArgument argument,
	                                 final int count, final boolean any) {
		final CommandArguments args = CommandArguments.create(key).add("FROMLONLAT").add(longitude, latitude)
				.add("BYRADIUS").add(radius, unit).add(argument).add(Keyword.Common.COUNT, count)
				.add(any ? Keyword.Common.ANY : null);
		return geoSearch(rawKey(key), longitude, latitude, radius, unit, new LettuceGeoArgs(argument, count, any),
				args);
	}

	@Override
	public List<GeoRadius> geoSearch(final String key, final double longitude, final double latitude,
	                                 final double width, final double height, final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(key).add("FROMLONLAT").add(longitude, latitude)
				.add("BYBOX").add(width, height).add(unit);
		return geoSearch(rawBinaryKey(key), longitude, latitude, width, height, unit, args);
	}

	@Override
	public List<GeoRadius> geoSearch(final byte[] key, final double longitude, final double latitude,
	                                 final double width, final double height, final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(key).add("FROMLONLAT").add(longitude, latitude)
				.add("BYBOX").add(width, height).add(unit);
		return geoSearch(rawKey(key), longitude, latitude, width, height, unit, args);
	}

	@Override
	public List<GeoRadius> geoSearch(final String key, final double longitude, final double latitude,
	                                 final double width, final double height, final GeoUnit unit,
	                                 final GeoSearchArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add("FROMLONLAT").add(longitude, latitude)
				.add("BYBOX").add(width, height).add(unit).add(argument);
		return geoSearch(rawBinaryKey(key), longitude, latitude, width, height, unit, new LettuceGeoArgs(argument),
				args);
	}

	@Override
	public List<GeoRadius> geoSearch(final byte[] key, final double longitude, final double latitude,
	                                 final double width, final double height, final GeoUnit unit,
	                                 final GeoSearchArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add("FROMLONLAT").add(longitude, latitude)
				.add("BYBOX").add(width, height).add(unit).add(argument);
		return geoSearch(rawKey(key), longitude, latitude, width, height, unit, new LettuceGeoArgs(argument), args);
	}

	@Override
	public List<GeoRadius> geoSearch(final String key, final double longitude, final double latitude,
	                                 final double width, final double height, final GeoUnit unit,
	                                 final GeoSearchArgument argument, final int count) {
		final CommandArguments args = CommandArguments.create(key).add("FROMLONLAT").add(longitude, latitude)
				.add("BYBOX").add(width, height).add(unit).add(argument).add(Keyword.Common.COUNT, count);
		return geoSearch(rawBinaryKey(key), longitude, latitude, width, height, unit,
				new LettuceGeoArgs(argument, count), args);
	}

	@Override
	public List<GeoRadius> geoSearch(final byte[] key, final double longitude, final double latitude,
	                                 final double width, final double height, final GeoUnit unit,
	                                 final GeoSearchArgument argument, final int count) {
		final CommandArguments args = CommandArguments.create(key).add("FROMLONLAT").add(longitude, latitude)
				.add("BYBOX").add(width, height).add(unit).add(argument).add(Keyword.Common.COUNT, count);
		return geoSearch(rawKey(key), longitude, latitude, width, height, unit, new LettuceGeoArgs(argument, count),
				args);
	}

	@Override
	public List<GeoRadius> geoSearch(final String key, final double longitude, final double latitude,
	                                 final double width, final double height, final GeoUnit unit,
	                                 final GeoSearchArgument argument, final int count, final boolean any) {
		final CommandArguments args = CommandArguments.create(key).add("FROMLONLAT").add(longitude, latitude)
				.add("BYBOX").add(width, height).add(unit).add(argument).add(Keyword.Common.COUNT, count)
				.add(any ? Keyword.Common.ANY : null);
		return geoSearch(rawBinaryKey(key), longitude, latitude, width, height, unit,
				new LettuceGeoArgs(argument, count, any), args);
	}

	@Override
	public List<GeoRadius> geoSearch(final byte[] key, final double longitude, final double latitude,
	                                 final double width, final double height, final GeoUnit unit,
	                                 final GeoSearchArgument argument, final int count, final boolean any) {
		final CommandArguments args = CommandArguments.create(key).add("FROMLONLAT").add(longitude, latitude)
				.add("BYBOX").add(width, height).add(unit).add(argument).add(Keyword.Common.COUNT, count)
				.add(any ? Keyword.Common.ANY : null);
		return geoSearch(rawKey(key), longitude, latitude, width, height, unit,
				new LettuceGeoArgs(argument, count, any), args);
	}

	@Override
	public List<GeoRadius> geoSearch(final String key, final String member, final double radius, final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(key).add("FROMMEMBER", member).add("BYRADIUS")
				.add(radius, unit);
		return geoSearch(rawBinaryKey(key), SafeEncoder.encode(member), radius, unit, args);
	}

	@Override
	public List<GeoRadius> geoSearch(final byte[] key, final byte[] member, final double radius, final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(key).add("FROMMEMBER", member).add("BYRADIUS")
				.add(radius, unit);
		return geoSearch(rawKey(key), member, radius, unit, args);
	}

	@Override
	public List<GeoRadius> geoSearch(final String key, final String member, final double radius, final GeoUnit unit,
	                                 final GeoSearchArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add("FROMMEMBER", member).add("BYRADIUS")
				.add(radius, unit).add(argument);
		return geoSearch(rawBinaryKey(key), SafeEncoder.encode(member), radius, unit, new LettuceGeoArgs(argument),
				args);
	}

	@Override
	public List<GeoRadius> geoSearch(final byte[] key, final byte[] member, final double radius, final GeoUnit unit,
	                                 final GeoSearchArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add("FROMMEMBER", member).add("BYRADIUS")
				.add(radius, unit).add(argument);
		return geoSearch(rawKey(key), member, radius, unit, new LettuceGeoArgs(argument), args);
	}

	@Override
	public List<GeoRadius> geoSearch(final String key, final String member, final double radius, final GeoUnit unit,
	                                 final GeoSearchArgument argument, final int count) {
		final CommandArguments args = CommandArguments.create(key).add("FROMMEMBER", member).add("BYRADIUS")
				.add(radius, unit).add(argument).add(Keyword.Common.COUNT, count);
		return geoSearch(rawBinaryKey(key), SafeEncoder.encode(member), radius, unit,
				new LettuceGeoArgs(argument, count), args);
	}

	@Override
	public List<GeoRadius> geoSearch(final byte[] key, final byte[] member, final double radius, final GeoUnit unit,
	                                 final GeoSearchArgument argument, final int count) {
		final CommandArguments args = CommandArguments.create(key).add("FROMMEMBER", member).add("BYRADIUS")
				.add(radius, unit).add(argument).add(Keyword.Common.COUNT, count);
		return geoSearch(rawKey(key), member, radius, unit, new LettuceGeoArgs(argument, count), args);
	}

	@Override
	public List<GeoRadius> geoSearch(final String key, final String member, final double radius, final GeoUnit unit,
	                                 final GeoSearchArgument argument, final int count, final boolean any) {
		final CommandArguments args = CommandArguments.create(key).add("FROMMEMBER", member).add("BYRADIUS", radius)
				.add(unit).add(argument).add(Keyword.Common.COUNT, count).add(any ? Keyword.Common.ANY : null);
		return geoSearch(rawBinaryKey(key), SafeEncoder.encode(member), radius, unit,
				new LettuceGeoArgs(argument, count, any), args);
	}

	@Override
	public List<GeoRadius> geoSearch(final byte[] key, final byte[] member, final double radius, final GeoUnit unit,
	                                 final GeoSearchArgument argument, final int count, final boolean any) {
		final CommandArguments args = CommandArguments.create(key).add("FROMMEMBER", member).add("BYRADIUS")
				.add(radius, unit).add(argument).add(Keyword.Common.COUNT, count).add(any ? Keyword.Common.ANY : null);
		return geoSearch(rawKey(key), member, radius, unit, new LettuceGeoArgs(argument, count, any), args);
	}

	@Override
	public List<GeoRadius> geoSearch(final String key, final String member, final double width, final double height,
	                                 final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(key).add("FROMMEMBER", member).add("BYBOX")
				.add(width, height).add(unit);
		return geoSearch(rawBinaryKey(key), SafeEncoder.encode(member), width, height, unit, args);
	}

	@Override
	public List<GeoRadius> geoSearch(final byte[] key, final byte[] member, final double width, final double height,
	                                 final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(key).add("FROMMEMBER", member).add("BYBOX")
				.add(width, height).add(unit);
		return geoSearch(rawKey(key), member, width, height, unit, args);
	}

	@Override
	public List<GeoRadius> geoSearch(final String key, final String member, final double width, final double height,
	                                 final GeoUnit unit, final GeoSearchArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add("FROMMEMBER", member).add("BYBOX")
				.add(width, height).add(unit).add(argument);
		return geoSearch(rawBinaryKey(key), SafeEncoder.encode(member), width, height, unit,
				new LettuceGeoArgs(argument), args);
	}

	@Override
	public List<GeoRadius> geoSearch(final byte[] key, final byte[] member, final double width, final double height,
	                                 final GeoUnit unit, final GeoSearchArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add("FROMMEMBER", member).add("BYBOX")
				.add(width, height).add(unit).add(argument);
		return geoSearch(rawKey(key), member, width, height, unit, new LettuceGeoArgs(argument), args);
	}

	@Override
	public List<GeoRadius> geoSearch(final String key, final String member, final double width, final double height,
	                                 final GeoUnit unit, final GeoSearchArgument argument, final int count) {
		final CommandArguments args = CommandArguments.create(key).add("FROMMEMBER", member).add("BYBOX")
				.add(width, height).add(unit).add(argument).add(Keyword.Common.COUNT, count);
		return geoSearch(rawBinaryKey(key), SafeEncoder.encode(member), width, height, unit,
				new LettuceGeoArgs(argument, count), args);
	}

	@Override
	public List<GeoRadius> geoSearch(final byte[] key, final byte[] member, final double width, final double height,
	                                 final GeoUnit unit, final GeoSearchArgument argument, final int count) {
		final CommandArguments args = CommandArguments.create(key).add("FROMMEMBER", member).add("BYBOX")
				.add(width, height).add(unit).add(argument).add(Keyword.Common.COUNT, count);
		return geoSearch(rawKey(key), member, width, height, unit, new LettuceGeoArgs(argument, count), args);
	}

	@Override
	public List<GeoRadius> geoSearch(final String key, final String member, final double width, final double height,
	                                 final GeoUnit unit, final GeoSearchArgument argument, final int count,
	                                 final boolean any) {
		final CommandArguments args = CommandArguments.create(key).add("FROMMEMBER", member).add("BYBOX")
				.add(width, height).add(unit).add(argument).add(Keyword.Common.COUNT, count)
				.add(any ? Keyword.Common.ANY : null);
		return geoSearch(rawBinaryKey(key), SafeEncoder.encode(member), width, height, unit,
				new LettuceGeoArgs(argument, count, any), args);
	}

	@Override
	public List<GeoRadius> geoSearch(final byte[] key, final byte[] member, final double width, final double height,
	                                 final GeoUnit unit, final GeoSearchArgument argument, final int count,
	                                 final boolean any) {
		final CommandArguments args = CommandArguments.create(key).add("FROMMEMBER", member).add("BYBOX")
				.add(width, height).add(unit).add(argument).add(Keyword.Common.COUNT, count)
				.add(any ? Keyword.Common.ANY : null);
		return geoSearch(rawKey(key), member, width, height, unit, new LettuceGeoArgs(argument, count, any), args);
	}

	@Override
	public Long geoSearchStore(final String key, final String destKey, final double longitude, final double latitude,
	                           final double radius, final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(destKey, key).add("FROMLONLAT").add(longitude, latitude)
				.add("BYRADIUS").add(radius, unit);
		return geoSearchStore(rawBinaryKey(key), rawBinaryKey(destKey), longitude, latitude, radius, unit, false, args);
	}

	@Override
	public Long geoSearchStore(final byte[] key, final byte[] destKey, final double longitude, final double latitude,
	                           final double radius, final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(destKey, key).add("FROMLONLAT").add(longitude, latitude)
				.add("BYRADIUS").add(radius, unit);
		return geoSearchStore(rawKey(key), rawKey(destKey), longitude, latitude, radius, unit, false, args);
	}

	@Override
	public Long geoSearchStore(final String key, final String destKey, final double longitude, final double latitude,
	                           final double radius, final GeoUnit unit, final boolean storeDist) {
		final CommandArguments args = CommandArguments.create(destKey, key).add("FROMLONLAT").add(longitude, latitude)
				.add("BYRADIUS").add(radius, unit).add(storeDist ? "STOREDIST" : null);
		return geoSearchStore(rawBinaryKey(key), rawBinaryKey(destKey), longitude, latitude, radius, unit, storeDist,
				args);
	}

	@Override
	public Long geoSearchStore(final byte[] key, final byte[] destKey, final double longitude, final double latitude,
	                           final double radius, final GeoUnit unit, final boolean storeDist) {
		final CommandArguments args = CommandArguments.create(destKey, key).add("FROMLONLAT").add(longitude, latitude)
				.add("BYRADIUS").add(radius, unit).add(storeDist ? "STOREDIST" : null);
		return geoSearchStore(rawKey(key), rawKey(destKey), longitude, latitude, radius, unit, storeDist, args);
	}

	@Override
	public Long geoSearchStore(final String key, final String destKey, final double longitude, final double latitude,
	                           final double radius, final GeoUnit unit, final boolean storeDist, final int count) {
		final CommandArguments args = CommandArguments.create(destKey, key).add("FROMLONLAT").add(longitude, latitude)
				.add("BYRADIUS").add(radius, unit).add(Keyword.Common.COUNT, count)
				.add(storeDist ? "STOREDIST" : null);
		return geoSearchStore(rawBinaryKey(key), rawBinaryKey(destKey), longitude, latitude, radius, unit,
				new LettuceGeoArgs(count), storeDist, args);
	}

	@Override
	public Long geoSearchStore(final byte[] key, final byte[] destKey, final double longitude, final double latitude,
	                           final double radius, final GeoUnit unit, final boolean storeDist, final int count) {
		final CommandArguments args = CommandArguments.create(destKey, key).add("FROMLONLAT").add(longitude, latitude)
				.add("BYRADIUS").add(radius, unit).add(Keyword.Common.COUNT, count)
				.add(storeDist ? "STOREDIST" : null);
		return geoSearchStore(rawKey(key), rawKey(destKey), longitude, latitude, radius, unit,
				new LettuceGeoArgs(count), storeDist, args);
	}

	@Override
	public Long geoSearchStore(final String key, final String destKey, final double longitude, final double latitude,
	                           final double radius, final GeoUnit unit, final boolean storeDist, final int count,
	                           final boolean any) {
		final CommandArguments args = CommandArguments.create(destKey, key).add("FROMLONLAT").add(longitude, latitude)
				.add("BYRADIUS").add(radius, unit).add(Keyword.Common.COUNT, count)
				.add(any ? Keyword.Common.ANY : null).add(storeDist ? "STOREDIST" : null);
		return geoSearchStore(rawBinaryKey(key), rawBinaryKey(destKey), longitude, latitude, radius, unit,
				new LettuceGeoArgs(count, any), storeDist, args);
	}

	@Override
	public Long geoSearchStore(final byte[] key, final byte[] destKey, final double longitude, final double latitude,
	                           final double radius, final GeoUnit unit, final boolean storeDist, final int count,
	                           final boolean any) {
		final CommandArguments args = CommandArguments.create(destKey, key).add("FROMLONLAT").add(longitude, latitude)
				.add("BYRADIUS").add(radius, unit).add(Keyword.Common.COUNT, count)
				.add(any ? Keyword.Common.ANY : null).add(storeDist ? "STOREDIST" : null);
		return geoSearchStore(rawKey(key), rawKey(destKey), longitude, latitude, radius, unit,
				new LettuceGeoArgs(count, any), storeDist, args);
	}

	@Override
	public Long geoSearchStore(final String key, final String destKey, final double longitude, final double latitude,
	                           final double width, final double height, final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(destKey, key).add("FROMLONLAT").add(longitude, latitude)
				.add("BYBOX").add(width, height);
		return geoSearchStore(rawBinaryKey(key), rawBinaryKey(destKey), longitude, latitude, width, height, unit, false,
				args);
	}

	@Override
	public Long geoSearchStore(final byte[] key, final byte[] destKey, final double longitude, final double latitude,
	                           final double width, final double height, final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(destKey, key).add("FROMLONLAT").add(longitude, latitude)
				.add("BYBOX").add(width, height);
		return geoSearchStore(rawKey(key), rawKey(destKey), longitude, latitude, width, height, unit, false, args);
	}

	@Override
	public Long geoSearchStore(final String key, final String destKey, final double longitude, final double latitude,
	                           final double width, final double height, final GeoUnit unit, final boolean storeDist) {
		final CommandArguments args = CommandArguments.create(destKey, key).add("FROMLONLAT").add(longitude, latitude)
				.add("BYBOX").add(width, height).add(storeDist ? "STOREDIST" : null);
		return geoSearchStore(rawBinaryKey(key), rawBinaryKey(destKey), longitude, latitude, width, height, unit,
				storeDist, args);
	}

	@Override
	public Long geoSearchStore(final byte[] key, final byte[] destKey, final double longitude, final double latitude,
	                           final double width, final double height, final GeoUnit unit, final boolean storeDist) {
		final CommandArguments args = CommandArguments.create(destKey, key).add("FROMLONLAT").add(longitude, latitude)
				.add("BYBOX").add(width, height).add(storeDist ? "STOREDIST" : null);
		return geoSearchStore(rawKey(key), rawKey(destKey), longitude, latitude, width, height, unit, false, args);
	}

	@Override
	public Long geoSearchStore(final String key, final String destKey, final double longitude, final double latitude,
	                           final double width, final double height, final GeoUnit unit, final boolean storeDist,
	                           final int count) {
		final CommandArguments args = CommandArguments.create(destKey, key).add("FROMLONLAT").add(longitude, latitude)
				.add("BYBOX").add(width, height).add(Keyword.Common.COUNT, count).add(storeDist ? "STOREDIST" : null);
		return geoSearchStore(rawBinaryKey(key), rawBinaryKey(destKey), longitude, latitude, width, unit,
				new LettuceGeoArgs(count), storeDist, args);
	}

	@Override
	public Long geoSearchStore(final byte[] key, final byte[] destKey, final double longitude, final double latitude,
	                           final double width, final double height, final GeoUnit unit, final boolean storeDist,
	                           final int count) {
		final CommandArguments args = CommandArguments.create(destKey, key).add("FROMLONLAT").add(longitude, latitude)
				.add("BYBOX").add(width, height).add(Keyword.Common.COUNT, count).add(storeDist ? "STOREDIST" : null);
		return geoSearchStore(rawKey(key), rawKey(destKey), longitude, latitude, width, height, unit,
				new LettuceGeoArgs(count), storeDist, args);
	}

	@Override
	public Long geoSearchStore(final String key, final String destKey, final double longitude, final double latitude,
	                           final double width, final double height, final GeoUnit unit, final boolean storeDist,
	                           final int count, final boolean any) {
		final CommandArguments args = CommandArguments.create(destKey, key).add("FROMLONLAT").add(longitude, latitude)
				.add("BYBOX").add(width, height).add(Keyword.Common.COUNT, count).add(any ? Keyword.Common.ANY : null)
				.add(storeDist ? "STOREDIST" : null);
		return geoSearchStore(rawBinaryKey(key), rawBinaryKey(destKey), longitude, latitude, width, unit,
				new LettuceGeoArgs(count, any), storeDist, args);
	}

	@Override
	public Long geoSearchStore(final byte[] key, final byte[] destKey, final double longitude, final double latitude,
	                           final double width, final double height, final GeoUnit unit, final boolean storeDist,
	                           final int count, final boolean any) {
		final CommandArguments args = CommandArguments.create(destKey, key).add("FROMLONLAT").add(longitude, latitude)
				.add("BYBOX").add(width, height).add(Keyword.Common.COUNT, count).add(any ? Keyword.Common.ANY : null)
				.add(storeDist ? "STOREDIST" : null);
		return geoSearchStore(rawKey(key), rawKey(destKey), longitude, latitude, width, height, unit,
				new LettuceGeoArgs(count, any), storeDist, args);
	}

	@Override
	public Long geoSearchStore(final String key, final String destKey, final String member, final double radius,
	                           final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(destKey, key).add("FROMMEMBER").add(member)
				.add("BYRADIUS").add(radius, unit);
		return geoSearchStore(rawBinaryKey(key), rawBinaryKey(destKey), SafeEncoder.encode(member), radius, unit, false,
				args);
	}

	@Override
	public Long geoSearchStore(final byte[] key, final byte[] destKey, final byte[] member, final double radius,
	                           final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(destKey, key).add("FROMMEMBER").add(member)
				.add("BYRADIUS").add(radius, unit);
		return geoSearchStore(rawKey(key), rawKey(destKey), member, radius, unit, false, args);
	}

	@Override
	public Long geoSearchStore(final String key, final String destKey, final String member, final double radius,
	                           final GeoUnit unit, final boolean storeDist) {
		final CommandArguments args = CommandArguments.create(destKey, key).add("FROMMEMBER").add(member)
				.add("BYRADIUS").add(radius, unit).add(storeDist ? "STOREDIST" : null);
		return geoSearchStore(rawBinaryKey(key), rawBinaryKey(destKey), SafeEncoder.encode(member), radius, unit,
				storeDist, args);
	}

	@Override
	public Long geoSearchStore(final byte[] key, final byte[] destKey, final byte[] member, final double radius,
	                           final GeoUnit unit, final boolean storeDist) {
		final CommandArguments args = CommandArguments.create(destKey, key).add("FROMMEMBER").add(member)
				.add("BYRADIUS").add(radius, unit).add(storeDist ? "STOREDIST" : null);
		return geoSearchStore(rawKey(key), rawKey(destKey), member, radius, unit, storeDist, args);
	}

	@Override
	public Long geoSearchStore(final String key, final String destKey, final String member, final double radius,
	                           final GeoUnit unit, final boolean storeDist, final int count) {
		final CommandArguments args = CommandArguments.create(destKey, key).add("FROMMEMBER").add(member)
				.add("BYRADIUS").add(radius, unit).add(Keyword.Common.COUNT, count)
				.add(storeDist ? "STOREDIST" : null);
		return geoSearchStore(rawBinaryKey(key), rawBinaryKey(destKey), SafeEncoder.encode(member), radius, unit,
				new LettuceGeoArgs(count), storeDist, args);
	}

	@Override
	public Long geoSearchStore(final byte[] key, final byte[] destKey, final byte[] member, final double radius,
	                           final GeoUnit unit, final boolean storeDist, final int count) {
		final CommandArguments args = CommandArguments.create(destKey, key).add("FROMMEMBER").add(member)
				.add("BYRADIUS").add(radius, unit).add(Keyword.Common.COUNT, count)
				.add(storeDist ? "STOREDIST" : null);
		return geoSearchStore(rawKey(key), rawKey(destKey), member, radius, unit, new LettuceGeoArgs(count),
				storeDist, args);
	}

	@Override
	public Long geoSearchStore(final String key, final String destKey, final String member, final double radius,
	                           final GeoUnit unit, final boolean storeDist, final int count, final boolean any) {
		final CommandArguments args = CommandArguments.create(destKey, key).add("FROMMEMBER").add(member)
				.add("BYRADIUS").add(radius, unit).add(Keyword.Common.COUNT, count)
				.add(any ? Keyword.Common.ANY : null).add(storeDist ? "STOREDIST" : null);
		return geoSearchStore(rawBinaryKey(key), rawBinaryKey(destKey), SafeEncoder.encode(member), radius, unit,
				new LettuceGeoArgs(count, any), storeDist, args);
	}

	@Override
	public Long geoSearchStore(final byte[] key, final byte[] destKey, final byte[] member, final double radius,
	                           final GeoUnit unit, final boolean storeDist, final int count, final boolean any) {
		final CommandArguments args = CommandArguments.create(destKey, key).add("FROMMEMBER").add(member)
				.add("BYRADIUS").add(radius, unit).add(Keyword.Common.COUNT, count)
				.add(any ? Keyword.Common.ANY : null).add(storeDist ? "STOREDIST" : null);
		return geoSearchStore(rawKey(key), rawKey(destKey), member, radius, unit, new LettuceGeoArgs(count, any),
				storeDist, args);
	}

	@Override
	public Long geoSearchStore(final String key, final String destKey, final String member, final double width,
	                           final double height, final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(destKey, key).add("FROMMEMBER").add(member).add("BYBOX")
				.add(width, height).add(unit);
		return geoSearchStore(rawBinaryKey(key), rawBinaryKey(destKey), SafeEncoder.encode(member), width, height, unit,
				false, args);
	}

	@Override
	public Long geoSearchStore(final byte[] key, final byte[] destKey, final byte[] member, final double width,
	                           final double height, final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(destKey, key).add("FROMMEMBER").add(member).add("BYBOX")
				.add(width, height).add(unit);
		return geoSearchStore(rawKey(key), rawKey(destKey), member, width, height, unit, false, args);
	}

	@Override
	public Long geoSearchStore(final String key, final String destKey, final String member, final double width,
	                           final double height, final GeoUnit unit, final boolean storeDist) {
		final CommandArguments args = CommandArguments.create(destKey, key).add("FROMMEMBER").add(member).add("BYBOX")
				.add(width, height).add(unit).add(storeDist ? "STOREDIST" : null);
		return geoSearchStore(rawBinaryKey(key), rawBinaryKey(destKey), SafeEncoder.encode(member), width, height, unit,
				storeDist, args);
	}

	@Override
	public Long geoSearchStore(final byte[] key, final byte[] destKey, final byte[] member, final double width,
	                           final double height, final GeoUnit unit, final boolean storeDist) {
		final CommandArguments args = CommandArguments.create(destKey, key).add("FROMMEMBER").add(member).add("BYBOX")
				.add(width, height).add(unit).add(storeDist ? "STOREDIST" : null);
		return geoSearchStore(rawKey(key), rawKey(destKey), member, width, height, unit, storeDist, args);
	}

	@Override
	public Long geoSearchStore(final String key, final String destKey, final String member, final double width,
	                           final double height, final GeoUnit unit, final boolean storeDist, final int count) {
		final CommandArguments args = CommandArguments.create(destKey, key).add("FROMMEMBER").add(member).add("BYBOX")
				.add(width, height).add(unit).add(Keyword.Common.COUNT, count).add(storeDist ? "STOREDIST" : null);
		return geoSearchStore(rawBinaryKey(key), rawBinaryKey(destKey), SafeEncoder.encode(member), width, height, unit,
				new LettuceGeoArgs(count), storeDist, args);
	}

	@Override
	public Long geoSearchStore(final byte[] key, final byte[] destKey, final byte[] member, final double width,
	                           final double height, final GeoUnit unit, final boolean storeDist, final int count) {
		final CommandArguments args = CommandArguments.create(destKey, key).add("FROMMEMBER").add(member).add("BYBOX")
				.add(width, height).add(unit).add(Keyword.Common.COUNT, count).add(storeDist ? "STOREDIST" : null);
		return geoSearchStore(rawKey(key), rawKey(destKey), member, width, height, unit, new LettuceGeoArgs(count),
				storeDist, args);
	}

	@Override
	public Long geoSearchStore(final String key, final String destKey, final String member, final double width,
	                           final double height, final GeoUnit unit, final boolean storeDist, final int count,
	                           final boolean any) {
		final CommandArguments args = CommandArguments.create(destKey, key).add("FROMMEMBER").add(member).add("BYBOX")
				.add(width, height).add(unit).add(Keyword.Common.COUNT, count).add(any ? Keyword.Common.ANY : null)
				.add(storeDist ? "STOREDIST" : null);
		return geoSearchStore(rawBinaryKey(key), rawBinaryKey(destKey), SafeEncoder.encode(member), width, height, unit,
				new LettuceGeoArgs(count, any), storeDist, args);
	}

	@Override
	public Long geoSearchStore(final byte[] key, final byte[] destKey, final byte[] member, final double width,
	                           final double height, final GeoUnit unit, final boolean storeDist, final int count,
	                           final boolean any) {
		final CommandArguments args = CommandArguments.create(destKey, key).add("FROMMEMBER").add(member).add("BYBOX")
				.add(width, height).add(unit).add(Keyword.Common.COUNT, count).add(any ? Keyword.Common.ANY : null)
				.add(storeDist ? "STOREDIST" : null);
		return geoSearchStore(rawKey(key), rawKey(destKey), member, width, height, unit, new LettuceGeoArgs(count, any),
				storeDist, args);
	}

	private Long geoAdd(final String key, final GeoAddArgs geoAddArgs, final KeyValue<String, Geo>[] memberCoordinates,
	                    final CommandArguments args) {
		return executeCommand(RedisCommand.GEOADD, args, (cmd)->cmd.geoadd(SafeEncoder.encode(key), geoAddArgs,
						stringMemberCoordinatesToGeoValue(memberCoordinates)),
				(cmd)->cmd.geoadd(SafeEncoder.encode(key), geoAddArgs,
						stringMemberCoordinatesToGeoValue(memberCoordinates)));
	}

	private Long geoAdd(final byte[] key, final GeoAddArgs geoAddArgs, final KeyValue<byte[], Geo>[] memberCoordinates,
	                    final CommandArguments args) {
		return executeCommand(RedisCommand.GEOADD, args,
				(cmd)->cmd.geoadd(key, geoAddArgs, byteMemberCoordinatesToGeoValue(memberCoordinates)),
				(cmd)->cmd.geoadd(key, geoAddArgs, byteMemberCoordinatesToGeoValue(memberCoordinates)));
	}

	private List<GeoRadius> geoRadius(final byte[] key, final double longitude, final double latitude,
	                                  final double radius, final GeoUnit unit, final GeoArgs geoArgs,
	                                  final CommandArguments args) {
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		return executeCommand(RedisCommand.GEORADIUS, args,
				(cmd)->cmd.georadius(key, longitude, latitude, radius, geoUnitConverter.convert(unit), geoArgs),
				(cmd)->cmd.georadius(key, longitude, latitude, radius, geoUnitConverter.convert(unit), geoArgs),
				new ListConverter<>(new GeoRadiusResponseConverter()));
	}

	private Long geoRadius(final byte[] key, final double longitude, final double latitude, final double radius,
	                       final GeoUnit unit, final GeoRadiusStoreArgs<byte[]> geoRadiusStoreArgs,
	                       final CommandArguments args) {
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		return executeCommand(RedisCommand.GEORADIUS, args,
				(cmd)->cmd.georadius(key, longitude, latitude, radius, geoUnitConverter.convert(unit),
						geoRadiusStoreArgs),
				(cmd)->cmd.georadius(key, longitude, latitude, radius, geoUnitConverter.convert(unit),
						geoRadiusStoreArgs));
	}

	private List<GeoRadius> geoRadiusRo(final byte[] key, final double longitude, final double latitude,
	                                    final double radius, final GeoUnit unit, final GeoArgs geoArgs,
	                                    final CommandArguments args) {
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		return executeCommand(RedisCommand.GEORADIUS_RO, args,
				(cmd)->cmd.georadius(key, longitude, latitude, radius, geoUnitConverter.convert(unit), geoArgs),
				(cmd)->cmd.georadius(key, longitude, latitude, radius, geoUnitConverter.convert(unit), geoArgs),
				new ListConverter<>(new GeoRadiusResponseConverter()));
	}

	private List<GeoRadius> geoRadiusByMember(final byte[] key, final byte[] member, final double radius,
	                                          final GeoUnit unit, final GeoArgs geoArgs, final CommandArguments args) {
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		return executeCommand(RedisCommand.GEORADIUSBYMEMBER, args,
				(cmd)->cmd.georadiusbymember(key, member, radius, geoUnitConverter.convert(unit), geoArgs),
				(cmd)->cmd.georadiusbymember(key, member, radius, geoUnitConverter.convert(unit), geoArgs),
				new ListConverter<>(new GeoRadiusResponseConverter()));
	}

	private Long geoRadiusByMember(final byte[] key, final byte[] member, final double radius, final GeoUnit unit,
	                               final GeoRadiusStoreArgs<byte[]> geoRadiusStoreArgs, final CommandArguments args) {
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		return executeCommand(RedisCommand.GEORADIUSBYMEMBER, args,
				(cmd)->cmd.georadiusbymember(key, member, radius, geoUnitConverter.convert(unit), geoRadiusStoreArgs),
				(cmd)->cmd.georadiusbymember(key, member, radius, geoUnitConverter.convert(unit), geoRadiusStoreArgs));
	}

	private List<GeoRadius> geoRadiusByMemberRo(final byte[] key, final byte[] member, final double radius,
	                                            final GeoUnit unit, final GeoArgs geoArgs,
	                                            final CommandArguments args) {
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		return executeCommand(RedisCommand.GEORADIUSBYMEMBER_RO, args,
				(cmd)->cmd.georadiusbymember(key, member, radius, geoUnitConverter.convert(unit), geoArgs),
				(cmd)->cmd.georadiusbymember(key, member, radius, geoUnitConverter.convert(unit), geoArgs),
				new ListConverter<>(new GeoRadiusResponseConverter()));
	}

	private List<GeoRadius> geoSearch(final byte[] key, final double longitude, final double latitude,
	                                  final double radius, final GeoUnit unit, final CommandArguments args) {
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		return geoSearch(key, longitude, latitude, GeoSearch.byRadius(radius, geoUnitConverter.convert(unit)), args);
	}

	private List<GeoRadius> geoSearch(final byte[] key, final double longitude, final double latitude,
	                                  final double radius, final GeoUnit unit, final GeoArgs geoArgs,
	                                  final CommandArguments args) {
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		return geoSearch(key, longitude, latitude, GeoSearch.byRadius(radius, geoUnitConverter.convert(unit)), geoArgs,
				args);
	}

	private List<GeoRadius> geoSearch(final byte[] key, final double longitude, final double latitude,
	                                  final double width, final double height, final GeoUnit unit,
	                                  final CommandArguments args) {
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		return geoSearch(key, longitude, latitude, GeoSearch.byBox(width, height, geoUnitConverter.convert(unit)),
				args);
	}

	private List<GeoRadius> geoSearch(final byte[] key, final double longitude, final double latitude,
	                                  final double width, final double height, final GeoUnit unit,
	                                  final GeoArgs geoArgs, final CommandArguments args) {
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		return geoSearch(key, longitude, latitude, GeoSearch.byBox(width, height, geoUnitConverter.convert(unit)),
				geoArgs, args);
	}

	private List<GeoRadius> geoSearch(final byte[] key, final double longitude, final double latitude,
	                                  final GeoSearch.GeoPredicate predicate, final CommandArguments args) {
		return executeCommand(RedisCommand.GEOSEARCH, args,
				(cmd)->cmd.geosearch(key, GeoSearch.fromCoordinates(longitude, latitude), predicate),
				(cmd)->cmd.geosearch(key, GeoSearch.fromCoordinates(longitude, latitude), predicate),
				new SetListConverter<>(new GeoRadiusGeneralResultConverter()));
	}

	private List<GeoRadius> geoSearch(final byte[] key, final double longitude, final double latitude,
	                                  final GeoSearch.GeoPredicate predicate, final GeoArgs geoArgs,
	                                  final CommandArguments args) {
		return executeCommand(RedisCommand.GEOSEARCH, args,
				(cmd)->cmd.geosearch(key, GeoSearch.fromCoordinates(longitude, latitude), predicate, geoArgs),
				(cmd)->cmd.geosearch(key, GeoSearch.fromCoordinates(longitude, latitude), predicate, geoArgs),
				new ListConverter<>(new GeoRadiusResponseConverter()));
	}

	private List<GeoRadius> geoSearch(final byte[] key, final byte[] member, final double radius, final GeoUnit unit,
	                                  final CommandArguments args) {
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		return geoSearch(key, member, GeoSearch.byRadius(radius, geoUnitConverter.convert(unit)), args);
	}

	private List<GeoRadius> geoSearch(final byte[] key, final byte[] member, final double radius, final GeoUnit unit,
	                                  final GeoArgs geoArgs, final CommandArguments args) {
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		return geoSearch(key, member, GeoSearch.byRadius(radius, geoUnitConverter.convert(unit)), geoArgs, args);
	}

	private List<GeoRadius> geoSearch(final byte[] key, final byte[] member, final double width, final double height,
	                                  final GeoUnit unit, final CommandArguments args) {
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		return geoSearch(key, member, GeoSearch.byBox(width, height, geoUnitConverter.convert(unit)), args);
	}

	private List<GeoRadius> geoSearch(final byte[] key, final byte[] member, final double width, final double height,
	                                  final GeoUnit unit, final GeoArgs geoArgs, final CommandArguments args) {
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		return geoSearch(key, member, GeoSearch.byBox(width, height, geoUnitConverter.convert(unit)), geoArgs, args);
	}

	private List<GeoRadius> geoSearch(final byte[] key, final byte[] member, final GeoSearch.GeoPredicate predicate,
	                                  final CommandArguments args) {
		return executeCommand(RedisCommand.GEOSEARCH, args,
				(cmd)->cmd.geosearch(key, GeoSearch.fromMember(member), predicate),
				(cmd)->cmd.geosearch(key, GeoSearch.fromMember(member), predicate),
				new SetListConverter<>(new GeoRadiusGeneralResultConverter()));
	}

	private List<GeoRadius> geoSearch(final byte[] key, final byte[] member, final GeoSearch.GeoPredicate predicate,
	                                  final GeoArgs geoArgs, final CommandArguments args) {
		return executeCommand(RedisCommand.GEOSEARCH, args,
				(cmd)->cmd.geosearch(key, GeoSearch.fromMember(member), predicate, geoArgs),
				(cmd)->cmd.geosearch(key, GeoSearch.fromMember(member), predicate, geoArgs),
				new ListConverter<>(new GeoRadiusResponseConverter()));
	}

	private Long geoSearchStore(final byte[] key, final byte[] destKey, final double longitude, final double latitude,
	                            final double radius, final GeoUnit unit, final boolean storeDist,
	                            final CommandArguments args) {
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		return geoSearchStore(key, destKey, longitude, latitude,
				GeoSearch.byRadius(radius, geoUnitConverter.convert(unit)), new LettuceGeoArgs(), storeDist, args);
	}

	private Long geoSearchStore(final byte[] key, final byte[] destKey, final double longitude, final double latitude,
	                            final double radius, final GeoUnit unit, final GeoArgs geoArgs, final boolean storeDist,
	                            final CommandArguments args) {
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		return geoSearchStore(key, destKey, longitude, latitude,
				GeoSearch.byRadius(radius, geoUnitConverter.convert(unit)), geoArgs, storeDist, args);
	}

	private Long geoSearchStore(final byte[] key, final byte[] destKey, final double longitude, final double latitude,
	                            final double width, final double height, final GeoUnit unit, final boolean storeDist,
	                            final CommandArguments args) {
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		return geoSearchStore(key, destKey, longitude, latitude,
				GeoSearch.byBox(width, height, geoUnitConverter.convert(unit)), new LettuceGeoArgs(), storeDist, args);
	}

	private Long geoSearchStore(final byte[] key, final byte[] destKey, final double longitude, final double latitude,
	                            final double width, final double height, final GeoUnit unit, final GeoArgs geoArgs,
	                            final boolean storeDist, final CommandArguments args) {
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		return geoSearchStore(key, destKey, longitude, latitude,
				GeoSearch.byBox(width, height, geoUnitConverter.convert(unit)), geoArgs, storeDist, args);
	}

	private Long geoSearchStore(final byte[] key, final byte[] destKey, final double longitude, final double latitude,
	                            final GeoSearch.GeoPredicate predicate, final GeoArgs geoArgs, final boolean storeDist,
	                            final CommandArguments args) {
		return executeCommand(RedisCommand.GEOSEARCHSTORE, args,
				(cmd)->cmd.geosearchstore(key, destKey, GeoSearch.fromCoordinates(longitude, latitude), predicate,
						geoArgs, storeDist),
				(cmd)->cmd.geosearchstore(key, destKey, GeoSearch.fromCoordinates(longitude, latitude), predicate,
						geoArgs, storeDist));
	}

	private Long geoSearchStore(final byte[] key, final byte[] destKey, final byte[] member, final double radius,
	                            final GeoUnit unit, final boolean storeDist, final CommandArguments args) {
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		return geoSearchStore(key, destKey, member, GeoSearch.byRadius(radius, geoUnitConverter.convert(unit)),
				new LettuceGeoArgs(), storeDist, args);
	}

	private Long geoSearchStore(final byte[] key, final byte[] destKey, final byte[] member, final double radius,
	                            final GeoUnit unit, final GeoArgs geoArgs, final boolean storeDist,
	                            final CommandArguments args) {
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		return geoSearchStore(key, destKey, member, GeoSearch.byRadius(radius, geoUnitConverter.convert(unit)), geoArgs,
				storeDist, args);
	}

	private Long geoSearchStore(final byte[] key, final byte[] destKey, byte[] member, final double width,
	                            final double height, final GeoUnit unit, final boolean storeDist,
	                            final CommandArguments args) {
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		return geoSearchStore(key, destKey, member, GeoSearch.byBox(width, height, geoUnitConverter.convert(unit)),
				new LettuceGeoArgs(), storeDist, args);
	}

	private Long geoSearchStore(final byte[] key, final byte[] destKey, final byte[] member, final double width,
	                            final double height, final GeoUnit unit, final GeoArgs geoArgs, final boolean storeDist,
	                            final CommandArguments args) {
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		return geoSearchStore(key, destKey, member, GeoSearch.byBox(width, height, geoUnitConverter.convert(unit)),
				geoArgs, storeDist, args);
	}

	private Long geoSearchStore(final byte[] key, final byte[] destKey, final byte[] member,
	                            final GeoSearch.GeoPredicate predicate, final GeoArgs geoArgs, final boolean storeDist,
	                            final CommandArguments args) {
		return executeCommand(RedisCommand.GEOSEARCHSTORE, args,
				(cmd)->cmd.geosearchstore(key, destKey, GeoSearch.fromMember(member), predicate, geoArgs, storeDist),
				(cmd)->cmd.geosearchstore(key, destKey, GeoSearch.fromMember(member), predicate, geoArgs, storeDist));
	}

	@SuppressWarnings("unchecked")
	private static GeoValue<byte[]>[] stringMemberCoordinatesToGeoValue(
			final KeyValue<String, Geo>... memberCoordinates) {
		final GeoValue<byte[]>[] result = new GeoValue[memberCoordinates.length];
		Geo geo;

		for(int i = 0; i < memberCoordinates.length; i++){
			geo = memberCoordinates[i].getValue();
			result[i++] = GeoValue.just(geo.getLongitude(), geo.getLatitude(),
					SafeEncoder.encode(memberCoordinates[i].getKey()));
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	private static GeoValue<byte[]>[] byteMemberCoordinatesToGeoValue(
			final KeyValue<byte[], Geo>... memberCoordinates) {
		final GeoValue<byte[]>[] result = new GeoValue[memberCoordinates.length];
		Geo geo;

		for(int i = 0; i < memberCoordinates.length; i++){
			geo = memberCoordinates[i].getValue();
			result[i++] = GeoValue.just(geo.getLongitude(), geo.getLatitude(), memberCoordinates[i].getKey());
		}

		return result;
	}

}
