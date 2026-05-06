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

import com.buession.core.converter.ArrayKeyValueMapConverter;
import com.buession.core.converter.ListConverter;
import com.buession.lang.Geo;
import com.buession.lang.KeyValue;
import com.buession.redis.client.jedis.JedisRedisClient;
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
import com.buession.redis.core.internal.convert.jedis.params.GeoUnitConverter;
import com.buession.redis.core.internal.convert.jedis.response.GeoCoordinateConverter;
import com.buession.redis.core.internal.convert.jedis.response.GeoRadiusResponseConverter;
import com.buession.redis.core.internal.jedis.args.JedisGeoAddParams;
import com.buession.redis.core.internal.jedis.args.JedisGeoRadiusParam;
import com.buession.redis.core.internal.jedis.args.JedisGeoRadiusStoreParam;
import com.buession.redis.core.internal.jedis.args.JedisGeoSearchParam;
import com.buession.redis.utils.SafeEncoder;
import redis.clients.jedis.GeoCoordinate;
import redis.clients.jedis.params.GeoAddParams;
import redis.clients.jedis.params.GeoRadiusParam;
import redis.clients.jedis.params.GeoSearchParam;

import java.util.List;
import java.util.Map;

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

	@SuppressWarnings({"unchecked"})
	@Override
	public Long geoAdd(final String key, final KeyValue<String, Geo>... memberCoordinates) {
		final CommandArguments args = CommandArguments.create(key).add(memberCoordinates);
		return executeCommand(RedisCommand.GEOADD, args,
				(cmd)->cmd.geoadd(key, createMemberCoordinates(memberCoordinates)),
				(cmd)->cmd.geoadd(key, createMemberCoordinates(memberCoordinates)),
				(cmd)->cmd.geoadd(key, createMemberCoordinates(memberCoordinates)));
	}

	@SuppressWarnings({"unchecked"})
	@Override
	public Long geoAdd(final byte[] key, final KeyValue<byte[], Geo>... memberCoordinates) {
		final CommandArguments args = CommandArguments.create(key).add(memberCoordinates);
		return executeCommand(RedisCommand.GEOADD, args,
				(cmd)->cmd.geoadd(key, createMemberCoordinates(memberCoordinates)),
				(cmd)->cmd.geoadd(key, createMemberCoordinates(memberCoordinates)),
				(cmd)->cmd.geoadd(key, createMemberCoordinates(memberCoordinates)));
	}

	@SuppressWarnings({"unchecked"})
	@Override
	public Long geoAdd(final String key, final NxXx nxXx, final KeyValue<String, Geo>... memberCoordinates) {
		final CommandArguments args = CommandArguments.create(key).add(nxXx).add(memberCoordinates);
		return geoAdd(key, new JedisGeoAddParams(nxXx), memberCoordinates, args);
	}

	@SuppressWarnings({"unchecked"})
	@Override
	public Long geoAdd(final byte[] key, final NxXx nxXx, final KeyValue<byte[], Geo>... memberCoordinates) {
		final CommandArguments args = CommandArguments.create(key).add(nxXx).add(memberCoordinates);
		return geoAdd(key, new JedisGeoAddParams(nxXx), memberCoordinates, args);
	}

	@SuppressWarnings({"unchecked"})
	@Override
	public Long geoAdd(final String key, final NxXx nxXx, final boolean ch,
	                   final KeyValue<String, Geo>... memberCoordinates) {
		final CommandArguments args =
				CommandArguments.create(key).add(nxXx).add(ch ? "CH" : null).add(memberCoordinates);
		return geoAdd(key, new JedisGeoAddParams(nxXx, ch), memberCoordinates, args);
	}

	@SuppressWarnings({"unchecked"})
	@Override
	public Long geoAdd(final byte[] key, final NxXx nxXx, final boolean ch,
	                   final KeyValue<byte[], Geo>... memberCoordinates) {
		final CommandArguments args =
				CommandArguments.create(key).add(nxXx).add(ch ? "CH" : null).add(memberCoordinates);
		return geoAdd(key, new JedisGeoAddParams(nxXx, ch), memberCoordinates, args);
	}

	@SuppressWarnings({"unchecked"})
	@Override
	public Long geoAdd(final String key, final boolean ch, final KeyValue<String, Geo>... memberCoordinates) {
		final CommandArguments args = CommandArguments.create(key).add(ch ? "CH" : null).add(memberCoordinates);
		return geoAdd(key, new JedisGeoAddParams(ch), memberCoordinates, args);
	}

	@SuppressWarnings({"unchecked"})
	@Override
	public Long geoAdd(final byte[] key, final boolean ch, final KeyValue<byte[], Geo>... memberCoordinates) {
		final CommandArguments args = CommandArguments.create(key).add(ch ? "CH" : null).add(memberCoordinates);
		return geoAdd(key, new JedisGeoAddParams(ch), memberCoordinates, args);
	}

	@Override
	public Double geoDist(final String key, final String member1, final String member2) {
		final CommandArguments args = CommandArguments.create(key).add(member1, member2);
		return executeCommand(RedisCommand.GEODIST, args, (cmd)->cmd.geodist(key, member1, member2),
				(cmd)->cmd.geodist(key, member1, member2), (cmd)->cmd.geodist(key, member1, member2));
	}

	@Override
	public Double geoDist(final byte[] key, final byte[] member1, final byte[] member2) {
		final CommandArguments args = CommandArguments.create(key).add(member1, member2);
		return executeCommand(RedisCommand.GEODIST, args, (cmd)->cmd.geodist(key, member1, member2),
				(cmd)->cmd.geodist(key, member1, member2), (cmd)->cmd.geodist(key, member1, member2));
	}

	@Override
	public Double geoDist(final String key, final String member1, final String member2, final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(key).add(member1, member2).add(unit);
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		return executeCommand(RedisCommand.GEODIST, args,
				(cmd)->cmd.geodist(key, member1, member2, geoUnitConverter.convert(unit)),
				(cmd)->cmd.geodist(key, member1, member2, geoUnitConverter.convert(unit)),
				(cmd)->cmd.geodist(key, member1, member2, geoUnitConverter.convert(unit)));
	}

	@Override
	public Double geoDist(final byte[] key, final byte[] member1, final byte[] member2, final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(key).add(member1, member2).add(unit);
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		return executeCommand(RedisCommand.GEODIST, args,
				(cmd)->cmd.geodist(key, member1, member2, geoUnitConverter.convert(unit)),
				(cmd)->cmd.geodist(key, member1, member2, geoUnitConverter.convert(unit)),
				(cmd)->cmd.geodist(key, member1, member2, geoUnitConverter.convert(unit)));
	}

	@Override
	public List<String> geoHash(final String key, final String... members) {
		final CommandArguments args = CommandArguments.create(key).add(members);
		return executeCommand(RedisCommand.GEOHASH, args, (cmd)->cmd.geohash(key, members),
				(cmd)->cmd.geohash(key, members), (cmd)->cmd.geohash(key, members));
	}

	@Override
	public List<byte[]> geoHash(final byte[] key, final byte[]... members) {
		final CommandArguments args = CommandArguments.create(key).add(members);
		return executeCommand(RedisCommand.GEOHASH, args, (cmd)->cmd.geohash(key, members),
				(cmd)->cmd.geohash(key, members), (cmd)->cmd.geohash(key, members));
	}

	@Override
	public List<Geo> geoPos(final String key, final String... members) {
		final CommandArguments args = CommandArguments.create(key).add(members);
		return executeCommand(RedisCommand.GEOPOS, args, (cmd)->cmd.geopos(key, members),
				(cmd)->cmd.geopos(key, members), (cmd)->cmd.geopos(key, members),
				new ListConverter<>(new GeoCoordinateConverter()));
	}

	@Override
	public List<Geo> geoPos(final byte[] key, final byte[]... members) {
		final CommandArguments args = CommandArguments.create(key).add(members);
		return executeCommand(RedisCommand.GEOPOS, args, (cmd)->cmd.geopos(key, members),
				(cmd)->cmd.geopos(key, members), (cmd)->cmd.geopos(key, members),
				new ListConverter<>(new GeoCoordinateConverter()));
	}

	@Override
	public List<GeoRadius> geoRadius(final String key, final double longitude, final double latitude,
	                                 final double radius, final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(key).add(longitude, latitude).add(radius, unit);
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		return executeCommand(RedisCommand.GEORADIUS, args,
				(cmd)->cmd.georadius(key, longitude, latitude, radius, geoUnitConverter.convert(unit)),
				(cmd)->cmd.georadius(key, longitude, latitude, radius, geoUnitConverter.convert(unit)),
				(cmd)->cmd.georadius(key, longitude, latitude, radius, geoUnitConverter.convert(unit)),
				new ListConverter<>(new GeoRadiusResponseConverter()));
	}

	@Override
	public List<GeoRadius> geoRadius(final byte[] key, final double longitude, final double latitude,
	                                 final double radius, final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(key).add(longitude, latitude).add(radius, unit);
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		return executeCommand(RedisCommand.GEORADIUS, args,
				(cmd)->cmd.georadius(key, longitude, latitude, radius, geoUnitConverter.convert(unit)),
				(cmd)->cmd.georadius(key, longitude, latitude, radius, geoUnitConverter.convert(unit)),
				(cmd)->cmd.georadius(key, longitude, latitude, radius, geoUnitConverter.convert(unit)),
				new ListConverter<>(new GeoRadiusResponseConverter()));
	}

	@Override
	public List<GeoRadius> geoRadius(final String key, final double longitude, final double latitude,
	                                 final double radius, final GeoUnit unit, final GeoRadiusArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(longitude, latitude).add(radius, unit)
				.add(argument);
		return geoRadius(key, longitude, latitude, radius, unit, new JedisGeoRadiusParam(argument), args);
	}

	@Override
	public List<GeoRadius> geoRadius(final byte[] key, final double longitude, final double latitude,
	                                 final double radius, final GeoUnit unit, final GeoRadiusArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(longitude, latitude).add(radius, unit)
				.add(argument);
		return geoRadius(key, longitude, latitude, radius, unit, new JedisGeoRadiusParam(argument), args);
	}

	@Override
	public List<GeoRadius> geoRadius(final String key, final double longitude, final double latitude,
	                                 final double radius, final GeoUnit unit, final GeoRadiusArgument argument,
	                                 final int count) {
		final CommandArguments args = CommandArguments.create(key).add(longitude, latitude).add(radius, unit)
				.add(argument).add(Keyword.Common.COUNT, count);
		return geoRadius(key, longitude, latitude, radius, unit, new JedisGeoRadiusParam(argument, count),
				args);
	}

	@Override
	public List<GeoRadius> geoRadius(final byte[] key, final double longitude, final double latitude,
	                                 final double radius, final GeoUnit unit, final GeoRadiusArgument argument,
	                                 final int count) {
		final CommandArguments args = CommandArguments.create(key).add(longitude, latitude).add(radius, unit)
				.add(argument).add(Keyword.Common.COUNT, count);
		return geoRadius(key, longitude, latitude, radius, unit, new JedisGeoRadiusParam(argument, count),
				args);
	}

	@Override
	public List<GeoRadius> geoRadius(final String key, final double longitude, final double latitude,
	                                 final double radius, final GeoUnit unit, final GeoRadiusArgument argument,
	                                 final int count, final boolean any) {
		final CommandArguments args = CommandArguments.create(key).add(longitude, latitude).add(radius, unit)
				.add(argument).add(Keyword.Common.COUNT, count).add(any ? Keyword.Common.ANY : null);
		return geoRadius(key, longitude, latitude, radius, unit, new JedisGeoRadiusParam(argument, count, any),
				args);
	}

	@Override
	public List<GeoRadius> geoRadius(final byte[] key, final double longitude, final double latitude,
	                                 final double radius, final GeoUnit unit, final GeoRadiusArgument argument,
	                                 final int count, final boolean any) {
		final CommandArguments args = CommandArguments.create(key).add(longitude, latitude).add(radius, unit)
				.add(argument).add(Keyword.Common.COUNT, count).add(any ? Keyword.Common.ANY : null);
		return geoRadius(key, longitude, latitude, radius, unit, new JedisGeoRadiusParam(argument, count, any),
				args);
	}

	@Override
	public List<GeoRadius> geoRadius(final String key, final double longitude, final double latitude,
	                                 final double radius, final GeoUnit unit, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(longitude, latitude).add(radius, unit)
				.add(Keyword.Common.COUNT, count);
		return geoRadius(key, longitude, latitude, radius, unit, new JedisGeoRadiusParam(count), args);
	}

	@Override
	public List<GeoRadius> geoRadius(final byte[] key, final double longitude, final double latitude,
	                                 final double radius, final GeoUnit unit, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(longitude, latitude).add(radius, unit)
				.add(Keyword.Common.COUNT, count);
		return geoRadius(key, longitude, latitude, radius, unit, new JedisGeoRadiusParam(count), args);
	}

	@Override
	public List<GeoRadius> geoRadius(final String key, final double longitude, final double latitude,
	                                 final double radius, final GeoUnit unit, final int count, final boolean any) {
		final CommandArguments args = CommandArguments.create(key).add(longitude, latitude).add(radius, unit)
				.add(Keyword.Common.COUNT, count).add(any ? Keyword.Common.ANY : null);
		return geoRadius(key, longitude, latitude, radius, unit, new JedisGeoRadiusParam(count, any), args);
	}

	@Override
	public List<GeoRadius> geoRadius(final byte[] key, final double longitude, final double latitude,
	                                 final double radius, final GeoUnit unit, final int count, final boolean any) {
		final CommandArguments args = CommandArguments.create(key).add(longitude, latitude).add(radius, unit)
				.add(Keyword.Common.COUNT, count).add(any ? Keyword.Common.ANY : null);
		return geoRadius(key, longitude, latitude, radius, unit, new JedisGeoRadiusParam(count, any), args);
	}

	@Override
	public Long geoRadius(final String key, final String destKey, final GeoStoreOption storeOption,
	                      final double longitude, final double latitude, final double radius, final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(key).add(longitude, latitude).add(radius, unit)
				.add(storeOption, destKey);
		return geoRadius(key, destKey, storeOption, longitude, latitude, radius, unit,
				new JedisGeoRadiusParam(), args);
	}

	@Override
	public Long geoRadius(final byte[] key, final byte[] destKey, final GeoStoreOption storeOption,
	                      final double longitude, final double latitude, final double radius, final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(key).add(longitude, latitude).add(radius, unit)
				.add(storeOption, destKey);
		return geoRadius(key, destKey, storeOption, longitude, latitude, radius, unit,
				new JedisGeoRadiusParam(), args);
	}

	@Override
	public Long geoRadius(final String key, final String destKey, final GeoStoreOption storeOption,
	                      final double longitude, final double latitude, final double radius, final GeoUnit unit,
	                      final GeoRadiusArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(longitude, latitude).add(radius, unit)
				.add(argument).add(storeOption, destKey);
		return geoRadius(key, destKey, storeOption, longitude, latitude, radius, unit,
				new JedisGeoRadiusParam(argument), args);
	}

	@Override
	public Long geoRadius(final byte[] key, final byte[] destKey, final GeoStoreOption storeOption,
	                      final double longitude, final double latitude, final double radius, final GeoUnit unit,
	                      final GeoRadiusArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(longitude, latitude).add(radius, unit)
				.add(argument).add(storeOption, destKey);
		return geoRadius(key, destKey, storeOption, longitude, latitude, radius, unit,
				new JedisGeoRadiusParam(argument), args);
	}

	@Override
	public Long geoRadius(final String key, final String destKey, final GeoStoreOption storeOption,
	                      final double longitude, final double latitude, final double radius, final GeoUnit unit,
	                      final GeoRadiusArgument argument, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(longitude, latitude).add(radius, unit)
				.add(argument).add(Keyword.Common.COUNT, count).add(storeOption, destKey);
		return geoRadius(key, destKey, storeOption, longitude, latitude, radius, unit,
				new JedisGeoRadiusParam(argument, count), args);
	}

	@Override
	public Long geoRadius(final byte[] key, final byte[] destKey, final GeoStoreOption storeOption,
	                      final double longitude, final double latitude, final double radius, final GeoUnit unit,
	                      final GeoRadiusArgument argument, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(longitude, latitude).add(radius, unit)
				.add(argument).add(Keyword.Common.COUNT, count).add(storeOption, destKey);
		return geoRadius(key, destKey, storeOption, longitude, latitude, radius, unit,
				new JedisGeoRadiusParam(argument, count), args);
	}

	@Override
	public Long geoRadius(final String key, final String destKey, final GeoStoreOption storeOption,
	                      final double longitude, final double latitude, final double radius, final GeoUnit unit,
	                      final GeoRadiusArgument argument, final int count, final boolean any) {
		final CommandArguments args = CommandArguments.create(key).add(longitude, latitude).add(radius, unit)
				.add(argument).add(Keyword.Common.COUNT, count).add(any ? Keyword.Common.ANY : null)
				.add(storeOption, destKey);
		return geoRadius(key, destKey, storeOption, longitude, latitude, radius, unit,
				new JedisGeoRadiusParam(argument, count, any), args);
	}

	@Override
	public Long geoRadius(final byte[] key, final byte[] destKey, final GeoStoreOption storeOption,
	                      final double longitude, final double latitude, final double radius, final GeoUnit unit,
	                      final GeoRadiusArgument argument, final int count, final boolean any) {
		final CommandArguments args = CommandArguments.create(key).add(longitude, latitude).add(radius, unit)
				.add(argument).add(Keyword.Common.COUNT, count).add(any ? Keyword.Common.ANY : null)
				.add(storeOption, destKey);
		return geoRadius(key, destKey, storeOption, longitude, latitude, radius, unit,
				new JedisGeoRadiusParam(argument, count, any), args);
	}

	@Override
	public Long geoRadius(final String key, final String destKey, final GeoStoreOption storeOption,
	                      final double longitude, final double latitude, final double radius, final GeoUnit unit,
	                      final int count) {
		final CommandArguments args = CommandArguments.create(key).add(longitude, latitude).add(radius, unit)
				.add(Keyword.Common.COUNT, count).add(storeOption, destKey);
		return geoRadius(key, destKey, storeOption, longitude, latitude, radius, unit,
				new JedisGeoRadiusParam(count), args);
	}

	@Override
	public Long geoRadius(final byte[] key, final byte[] destKey, final GeoStoreOption storeOption,
	                      final double longitude, final double latitude, final double radius, final GeoUnit unit,
	                      final int count) {
		final CommandArguments args = CommandArguments.create(key).add(longitude, latitude).add(radius, unit)
				.add(Keyword.Common.COUNT, count).add(storeOption, destKey);
		return geoRadius(key, destKey, storeOption, longitude, latitude, radius, unit,
				new JedisGeoRadiusParam(count), args);
	}

	@Override
	public Long geoRadius(final String key, final String destKey, final GeoStoreOption storeOption,
	                      final double longitude, final double latitude, final double radius, final GeoUnit unit,
	                      final int count, final boolean any) {
		final CommandArguments args = CommandArguments.create(key).add(longitude, latitude).add(radius, unit)
				.add(Keyword.Common.COUNT, count).add(any ? Keyword.Common.ANY : null).add(storeOption, destKey);
		return geoRadius(key, destKey, storeOption, longitude, latitude, radius, unit,
				new JedisGeoRadiusParam(count, any), args);
	}

	@Override
	public Long geoRadius(final byte[] key, final byte[] destKey, final GeoStoreOption storeOption,
	                      final double longitude, final double latitude, final double radius, final GeoUnit unit,
	                      final int count, final boolean any) {
		final CommandArguments args = CommandArguments.create(key).add(longitude, latitude).add(radius, unit)
				.add(Keyword.Common.COUNT, count).add(any ? Keyword.Common.ANY : null).add(storeOption, destKey);
		return geoRadius(key, destKey, storeOption, longitude, latitude, radius, unit,
				new JedisGeoRadiusParam(count, any), args);
	}

	@Override
	public List<GeoRadius> geoRadiusRo(final String key, final double longitude, final double latitude,
	                                   final double radius, final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(key).add(longitude, latitude).add(radius, unit);
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		return executeCommand(RedisCommand.GEORADIUS_RO, args,
				(cmd)->cmd.georadiusReadonly(key, longitude, latitude, radius, geoUnitConverter.convert(unit)),
				(cmd)->cmd.georadiusReadonly(key, longitude, latitude, radius, geoUnitConverter.convert(unit)),
				(cmd)->cmd.georadiusReadonly(key, longitude, latitude, radius, geoUnitConverter.convert(unit)),
				new ListConverter<>(new GeoRadiusResponseConverter()));
	}

	@Override
	public List<GeoRadius> geoRadiusRo(final byte[] key, final double longitude, final double latitude,
	                                   final double radius, final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(key).add(longitude, latitude).add(radius, unit);
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		return executeCommand(RedisCommand.GEORADIUS_RO, args,
				(cmd)->cmd.georadiusReadonly(key, longitude, latitude, radius, geoUnitConverter.convert(unit)),
				(cmd)->cmd.georadiusReadonly(key, longitude, latitude, radius, geoUnitConverter.convert(unit)),
				(cmd)->cmd.georadiusReadonly(key, longitude, latitude, radius, geoUnitConverter.convert(unit)),
				new ListConverter<>(new GeoRadiusResponseConverter()));
	}

	@Override
	public List<GeoRadius> geoRadiusRo(final String key, final double longitude, final double latitude,
	                                   final double radius, final GeoUnit unit, final GeoRadiusArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(longitude, latitude).add(radius, unit)
				.add(argument);
		return geoRadiusRo(key, longitude, latitude, radius, unit, new JedisGeoRadiusParam(argument), args);
	}

	@Override
	public List<GeoRadius> geoRadiusRo(final byte[] key, final double longitude, final double latitude,
	                                   final double radius, final GeoUnit unit, final GeoRadiusArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(longitude, latitude).add(radius, unit)
				.add(argument);
		return geoRadiusRo(key, longitude, latitude, radius, unit, new JedisGeoRadiusParam(argument), args);
	}

	@Override
	public List<GeoRadius> geoRadiusRo(final String key, final double longitude, final double latitude,
	                                   final double radius, final GeoUnit unit, final GeoRadiusArgument argument,
	                                   final int count) {
		final CommandArguments args = CommandArguments.create(key).add(longitude, latitude).add(radius, unit)
				.add(argument).add(Keyword.Common.COUNT, count);
		return geoRadiusRo(key, longitude, latitude, radius, unit, new JedisGeoRadiusParam(argument, count),
				args);
	}

	@Override
	public List<GeoRadius> geoRadiusRo(final byte[] key, final double longitude, final double latitude,
	                                   final double radius, final GeoUnit unit, final GeoRadiusArgument argument,
	                                   final int count) {
		final CommandArguments args = CommandArguments.create(key).add(longitude, latitude).add(radius, unit)
				.add(argument).add(Keyword.Common.COUNT, count);
		return geoRadiusRo(key, longitude, latitude, radius, unit, new JedisGeoRadiusParam(argument, count),
				args);
	}

	@Override
	public List<GeoRadius> geoRadiusRo(final String key, final double longitude, final double latitude,
	                                   final double radius, final GeoUnit unit, final GeoRadiusArgument argument,
	                                   final int count, final boolean any) {
		final CommandArguments args = CommandArguments.create(key).add(longitude, latitude).add(radius, unit)
				.add(argument).add(Keyword.Common.COUNT, count).add(any ? Keyword.Common.ANY : null);
		return geoRadiusRo(key, longitude, latitude, radius, unit,
				new JedisGeoRadiusParam(argument, count, any), args);
	}

	@Override
	public List<GeoRadius> geoRadiusRo(final byte[] key, final double longitude, final double latitude,
	                                   final double radius, final GeoUnit unit, final GeoRadiusArgument argument,
	                                   final int count, final boolean any) {
		final CommandArguments args = CommandArguments.create(key).add(longitude, latitude).add(radius, unit)
				.add(argument).add(Keyword.Common.COUNT, count).add(any ? Keyword.Common.ANY : null);
		return geoRadiusRo(key, longitude, latitude, radius, unit,
				new JedisGeoRadiusParam(argument, count, any), args);
	}

	@Override
	public List<GeoRadius> geoRadiusRo(final String key, final double longitude, final double latitude,
	                                   final double radius, final GeoUnit unit, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(longitude, latitude).add(radius, unit)
				.add(Keyword.Common.COUNT, count);
		return geoRadiusRo(key, longitude, latitude, radius, unit, new JedisGeoRadiusParam(count), args);
	}

	@Override
	public List<GeoRadius> geoRadiusRo(final byte[] key, final double longitude, final double latitude,
	                                   final double radius, final GeoUnit unit, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(longitude, latitude).add(radius, unit)
				.add(Keyword.Common.COUNT, count);
		return geoRadiusRo(key, longitude, latitude, radius, unit, new JedisGeoRadiusParam(count), args);
	}

	@Override
	public List<GeoRadius> geoRadiusRo(final String key, final double longitude, final double latitude,
	                                   final double radius, final GeoUnit unit, final int count, final boolean any) {
		final CommandArguments args = CommandArguments.create(key).add(longitude, latitude).add(radius, unit)
				.add(Keyword.Common.COUNT, count).add(any ? Keyword.Common.ANY : null);
		return geoRadiusRo(key, longitude, latitude, radius, unit, new JedisGeoRadiusParam(count, any), args);
	}

	@Override
	public List<GeoRadius> geoRadiusRo(final byte[] key, final double longitude, final double latitude,
	                                   final double radius, final GeoUnit unit, final int count, final boolean any) {
		final CommandArguments args = CommandArguments.create(key).add(longitude, latitude).add(radius, unit)
				.add(Keyword.Common.COUNT, count).add(any ? Keyword.Common.ANY : null);
		return geoRadiusRo(key, longitude, latitude, radius, unit, new JedisGeoRadiusParam(count, any), args);
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final String key, final String member, final double radius,
	                                         final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(key, member).add(radius, unit);
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		return executeCommand(RedisCommand.GEORADIUSBYMEMBER, args,
				(cmd)->cmd.georadiusByMember(key, member, radius, geoUnitConverter.convert(unit)),
				(cmd)->cmd.georadiusByMember(key, member, radius, geoUnitConverter.convert(unit)),
				(cmd)->cmd.georadiusByMember(key, member, radius, geoUnitConverter.convert(unit)),
				new ListConverter<>(new GeoRadiusResponseConverter()));
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final byte[] key, final byte[] member, final double radius,
	                                         final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(key, member).add(radius, unit);
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		return executeCommand(RedisCommand.GEORADIUSBYMEMBER, args,
				(cmd)->cmd.georadiusByMember(key, member, radius, geoUnitConverter.convert(unit)),
				(cmd)->cmd.georadiusByMember(key, member, radius, geoUnitConverter.convert(unit)),
				(cmd)->cmd.georadiusByMember(key, member, radius, geoUnitConverter.convert(unit)),
				new ListConverter<>(new GeoRadiusResponseConverter()));
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final String key, final String member, final double radius,
	                                         final GeoUnit unit, final GeoRadiusArgument argument) {
		final CommandArguments args = CommandArguments.create(key, member).add(radius, unit).add(argument);
		return geoRadiusByMember(key, member, radius, unit, new JedisGeoRadiusParam(argument), args);
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final byte[] key, final byte[] member, final double radius,
	                                         final GeoUnit unit, final GeoRadiusArgument argument) {
		final CommandArguments args = CommandArguments.create(key, member).add(radius, unit).add(argument);
		return geoRadiusByMember(key, member, radius, unit, new JedisGeoRadiusParam(argument), args);
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final String key, final String member, final double radius,
	                                         final GeoUnit unit, final GeoRadiusArgument argument, final int count) {
		final CommandArguments args = CommandArguments.create(key, member).add(radius, unit).add(argument)
				.add(Keyword.Common.COUNT, count);
		return geoRadiusByMember(key, member, radius, unit, new JedisGeoRadiusParam(argument, count), args);
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final byte[] key, final byte[] member, final double radius,
	                                         final GeoUnit unit, final GeoRadiusArgument argument, final int count) {
		final CommandArguments args = CommandArguments.create(key, member).add(radius, unit).add(argument)
				.add(Keyword.Common.COUNT, count);
		return geoRadiusByMember(key, member, radius, unit, new JedisGeoRadiusParam(argument, count), args);
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final String key, final String member, final double radius,
	                                         final GeoUnit unit, final GeoRadiusArgument argument, final int count,
	                                         final boolean any) {
		final CommandArguments args = CommandArguments.create(key, member).add(radius, unit).add(argument)
				.add(Keyword.Common.COUNT, count).add(any ? Keyword.Common.ANY : null);
		return geoRadiusByMember(key, member, radius, unit, new JedisGeoRadiusParam(argument, count, any),
				args);
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final byte[] key, final byte[] member, final double radius,
	                                         final GeoUnit unit, final GeoRadiusArgument argument, final int count,
	                                         final boolean any) {
		final CommandArguments args = CommandArguments.create(key, member).add(radius, unit).add(argument)
				.add(Keyword.Common.COUNT, count).add(any ? Keyword.Common.ANY : null);
		return geoRadiusByMember(key, member, radius, unit, new JedisGeoRadiusParam(argument, count, any),
				args);
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final String key, final String member, final double radius,
	                                         final GeoUnit unit, final int count) {
		final CommandArguments args = CommandArguments.create(key, member).add(radius, unit)
				.add(Keyword.Common.COUNT, count);
		return geoRadiusByMember(key, member, radius, unit, new JedisGeoRadiusParam(count), args);
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final byte[] key, final byte[] member, final double radius,
	                                         final GeoUnit unit, final int count) {
		final CommandArguments args = CommandArguments.create(key, member).add(radius, unit)
				.add(Keyword.Common.COUNT, count);
		return geoRadiusByMember(key, member, radius, unit, new JedisGeoRadiusParam(count), args);
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final String key, final String member, final double radius,
	                                         final GeoUnit unit, final int count, final boolean any) {
		final CommandArguments args = CommandArguments.create(key, member).add(radius, unit)
				.add(Keyword.Common.COUNT, count).add(any ? Keyword.Common.ANY : null);
		return geoRadiusByMember(key, member, radius, unit, new JedisGeoRadiusParam(count, any), args);
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final byte[] key, final byte[] member, final double radius,
	                                         final GeoUnit unit, final int count, final boolean any) {
		final CommandArguments args = CommandArguments.create(key, member).add(radius, unit)
				.add(Keyword.Common.COUNT, count).add(any ? Keyword.Common.ANY : null);
		return geoRadiusByMember(key, member, radius, unit, new JedisGeoRadiusParam(count, any), args);
	}

	@Override
	public Long geoRadiusByMember(final String key, final String destKey, final GeoStoreOption storeOption,
	                              final String member, final double radius, final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(key, member).add(radius, unit).add(storeOption, destKey);
		return geoRadiusByMember(key, destKey, storeOption, member, radius, unit,
				new JedisGeoRadiusParam(), args);
	}

	@Override
	public Long geoRadiusByMember(final byte[] key, final byte[] destKey, final GeoStoreOption storeOption,
	                              final byte[] member, final double radius, final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(key, member).add(radius, unit).add(storeOption, destKey);
		return geoRadiusByMember(key, destKey, storeOption, member, radius, unit,
				new JedisGeoRadiusParam(), args);
	}

	@Override
	public Long geoRadiusByMember(final String key, final String destKey, final GeoStoreOption storeOption,
	                              final String member, final double radius, final GeoUnit unit,
	                              final GeoRadiusArgument argument) {
		final CommandArguments args = CommandArguments.create(key, member).add(radius, unit).add(argument)
				.add(storeOption, destKey);
		return geoRadiusByMember(key, destKey, storeOption, member, radius, unit,
				new JedisGeoRadiusParam(argument), args);
	}

	@Override
	public Long geoRadiusByMember(final byte[] key, final byte[] destKey, final GeoStoreOption storeOption,
	                              final byte[] member, final double radius, final GeoUnit unit,
	                              final GeoRadiusArgument argument) {
		final CommandArguments args = CommandArguments.create(key, member).add(radius, unit).add(argument)
				.add(storeOption, destKey);
		return geoRadiusByMember(key, destKey, storeOption, member, radius, unit,
				new JedisGeoRadiusParam(argument), args);
	}

	@Override
	public Long geoRadiusByMember(final String key, final String destKey, final GeoStoreOption storeOption,
	                              final String member, final double radius, final GeoUnit unit,
	                              final GeoRadiusArgument argument, final int count) {
		final CommandArguments args = CommandArguments.create(key, member).add(radius, unit).add(argument)
				.add(Keyword.Common.COUNT, count).add(storeOption, destKey);
		return geoRadiusByMember(key, destKey, storeOption, member, radius, unit,
				new JedisGeoRadiusParam(argument, count), args);
	}

	@Override
	public Long geoRadiusByMember(final byte[] key, final byte[] destKey, final GeoStoreOption storeOption,
	                              final byte[] member, final double radius, final GeoUnit unit,
	                              final GeoRadiusArgument argument, final int count) {
		final CommandArguments args = CommandArguments.create(key, member).add(radius, unit).add(argument)
				.add(Keyword.Common.COUNT, count).add(storeOption, destKey);
		return geoRadiusByMember(key, destKey, storeOption, member, radius, unit,
				new JedisGeoRadiusParam(argument, count), args);
	}

	@Override
	public Long geoRadiusByMember(final String key, final String destKey, final GeoStoreOption storeOption,
	                              final String member, final double radius, final GeoUnit unit,
	                              final GeoRadiusArgument argument, final int count, final boolean any) {
		final CommandArguments args = CommandArguments.create(key, member).add(radius, unit).add(argument)
				.add(Keyword.Common.COUNT, count).add(any ? Keyword.Common.ANY : null).add(storeOption, destKey);
		return geoRadiusByMember(key, destKey, storeOption, member, radius, unit,
				new JedisGeoRadiusParam(argument, count, any), args);
	}

	@Override
	public Long geoRadiusByMember(final byte[] key, final byte[] destKey, final GeoStoreOption storeOption,
	                              final byte[] member, final double radius, final GeoUnit unit,
	                              final GeoRadiusArgument argument, final int count, final boolean any) {
		final CommandArguments args = CommandArguments.create(key, member).add(radius, unit).add(argument)
				.add(Keyword.Common.COUNT, count).add(any ? Keyword.Common.ANY : null).add(storeOption, destKey);
		return geoRadiusByMember(key, destKey, storeOption, member, radius, unit,
				new JedisGeoRadiusParam(argument, count, any), args);
	}

	@Override
	public Long geoRadiusByMember(final String key, final String destKey, final GeoStoreOption storeOption,
	                              final String member, final double radius, final GeoUnit unit, final int count) {
		final CommandArguments args = CommandArguments.create(key, member).add(radius, unit)
				.add(Keyword.Common.COUNT, count).add(storeOption, destKey);
		return geoRadiusByMember(key, destKey, storeOption, member, radius, unit,
				new JedisGeoRadiusParam(count), args);
	}

	@Override
	public Long geoRadiusByMember(final byte[] key, final byte[] destKey, final GeoStoreOption storeOption,
	                              final byte[] member, final double radius, final GeoUnit unit, final int count) {
		final CommandArguments args = CommandArguments.create(key, member).add(radius, unit)
				.add(Keyword.Common.COUNT, count).add(storeOption, destKey);
		return geoRadiusByMember(key, destKey, storeOption, member, radius, unit,
				new JedisGeoRadiusParam(count), args);
	}

	@Override
	public Long geoRadiusByMember(final String key, final String destKey, final GeoStoreOption storeOption,
	                              final String member, final double radius, final GeoUnit unit, final int count,
	                              final boolean any) {
		final CommandArguments args = CommandArguments.create(key, member).add(radius, unit)
				.add(Keyword.Common.COUNT, count).add(any ? Keyword.Common.ANY : null).add(storeOption, destKey);
		return geoRadiusByMember(key, destKey, storeOption, member, radius, unit,
				new JedisGeoRadiusParam(count, any), args);
	}

	@Override
	public Long geoRadiusByMember(final byte[] key, final byte[] destKey, final GeoStoreOption storeOption,
	                              final byte[] member, final double radius, final GeoUnit unit, final int count,
	                              final boolean any) {
		final CommandArguments args = CommandArguments.create(key, member).add(radius, unit)
				.add(Keyword.Common.COUNT, count).add(any ? Keyword.Common.ANY : null).add(storeOption, destKey);
		return geoRadiusByMember(key, destKey, storeOption, member, radius, unit,
				new JedisGeoRadiusParam(count, any), args);
	}

	@Override
	public List<GeoRadius> geoRadiusByMemberRo(final String key, final String member, final double radius,
	                                           final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(key, member).add(radius, unit);
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		return executeCommand(RedisCommand.GEORADIUSBYMEMBER_RO, args,
				(cmd)->cmd.georadiusByMemberReadonly(key, member, radius, geoUnitConverter.convert(unit)),
				(cmd)->cmd.georadiusByMemberReadonly(key, member, radius, geoUnitConverter.convert(unit)),
				(cmd)->cmd.georadiusByMemberReadonly(key, member, radius, geoUnitConverter.convert(unit)),
				new ListConverter<>(new GeoRadiusResponseConverter()));
	}

	@Override
	public List<GeoRadius> geoRadiusByMemberRo(final byte[] key, final byte[] member, final double radius,
	                                           final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(key, member).add(radius, unit);
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		return executeCommand(RedisCommand.GEORADIUSBYMEMBER_RO, args,
				(cmd)->cmd.georadiusByMemberReadonly(key, member, radius, geoUnitConverter.convert(unit)),
				(cmd)->cmd.georadiusByMemberReadonly(key, member, radius, geoUnitConverter.convert(unit)),
				(cmd)->cmd.georadiusByMemberReadonly(key, member, radius, geoUnitConverter.convert(unit)),
				new ListConverter<>(new GeoRadiusResponseConverter()));
	}

	@Override
	public List<GeoRadius> geoRadiusByMemberRo(final String key, final String member, final double radius,
	                                           final GeoUnit unit, final GeoRadiusArgument argument) {
		final CommandArguments args = CommandArguments.create(key, member).add(radius, unit).add(argument);
		return geoRadiusByMemberRo(key, member, radius, unit, new JedisGeoRadiusParam(argument), args);
	}

	@Override
	public List<GeoRadius> geoRadiusByMemberRo(final byte[] key, final byte[] member, final double radius,
	                                           final GeoUnit unit, final GeoRadiusArgument argument) {
		final CommandArguments args = CommandArguments.create(key, member).add(radius, unit).add(argument);
		return geoRadiusByMemberRo(key, member, radius, unit, new JedisGeoRadiusParam(argument), args);
	}

	@Override
	public List<GeoRadius> geoRadiusByMemberRo(final String key, final String member, final double radius,
	                                           final GeoUnit unit, final GeoRadiusArgument argument, final int count) {
		final CommandArguments args = CommandArguments.create(key, member).add(radius, unit).add(argument)
				.add(Keyword.Common.COUNT, count);
		return geoRadiusByMemberRo(key, member, radius, unit, new JedisGeoRadiusParam(argument, count), args);
	}

	@Override
	public List<GeoRadius> geoRadiusByMemberRo(final byte[] key, final byte[] member, final double radius,
	                                           final GeoUnit unit, final GeoRadiusArgument argument, final int count) {
		final CommandArguments args = CommandArguments.create(key, member).add(radius, unit).add(argument)
				.add(Keyword.Common.COUNT, count);
		return geoRadiusByMemberRo(key, member, radius, unit, new JedisGeoRadiusParam(argument, count), args);
	}

	@Override
	public List<GeoRadius> geoRadiusByMemberRo(final String key, final String member, final double radius,
	                                           final GeoUnit unit, final GeoRadiusArgument argument, final int count,
	                                           final boolean any) {
		final CommandArguments args = CommandArguments.create(key, member).add(radius, unit).add(argument)
				.add(Keyword.Common.COUNT, count).add(any ? Keyword.Common.ANY : null);
		return geoRadiusByMemberRo(key, member, radius, unit, new JedisGeoRadiusParam(argument, count, any),
				args);
	}

	@Override
	public List<GeoRadius> geoRadiusByMemberRo(final byte[] key, final byte[] member, final double radius,
	                                           final GeoUnit unit, final GeoRadiusArgument argument, final int count,
	                                           final boolean any) {
		final CommandArguments args = CommandArguments.create(key, member).add(radius, unit).add(argument)
				.add(Keyword.Common.COUNT, count).add(any ? Keyword.Common.ANY : null);
		return geoRadiusByMemberRo(key, member, radius, unit, new JedisGeoRadiusParam(argument, count, any),
				args);
	}

	@Override
	public List<GeoRadius> geoRadiusByMemberRo(final String key, final String member, final double radius,
	                                           final GeoUnit unit, final int count) {
		final CommandArguments args = CommandArguments.create(key, member).add(radius, unit)
				.add(Keyword.Common.COUNT, count);
		return geoRadiusByMemberRo(key, member, radius, unit, new JedisGeoRadiusParam(count), args);
	}

	@Override
	public List<GeoRadius> geoRadiusByMemberRo(final byte[] key, final byte[] member, final double radius,
	                                           final GeoUnit unit, final int count) {
		final CommandArguments args = CommandArguments.create(key, member).add(radius, unit)
				.add(Keyword.Common.COUNT, count);
		return geoRadiusByMemberRo(key, member, radius, unit, new JedisGeoRadiusParam(count), args);
	}

	@Override
	public List<GeoRadius> geoRadiusByMemberRo(final String key, final String member, final double radius,
	                                           final GeoUnit unit, final int count, final boolean any) {
		final CommandArguments args = CommandArguments.create(key, member).add(radius, unit)
				.add(Keyword.Common.COUNT, count).add(any ? Keyword.Common.ANY : null);
		return geoRadiusByMemberRo(key, member, radius, unit, new JedisGeoRadiusParam(count, any), args);
	}

	@Override
	public List<GeoRadius> geoRadiusByMemberRo(final byte[] key, final byte[] member, final double radius,
	                                           final GeoUnit unit, final int count, final boolean any) {
		final CommandArguments args = CommandArguments.create(key, member).add(radius, unit)
				.add(Keyword.Common.COUNT, count).add(any ? Keyword.Common.ANY : null);
		return geoRadiusByMemberRo(key, member, radius, unit, new JedisGeoRadiusParam(count, any), args);
	}

	@Override
	public List<GeoRadius> geoSearch(final String key, final double longitude, final double latitude,
	                                 final double radius, final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(key).add("FROMLONLAT").add(longitude, latitude)
				.add("BYRADIUS").add(radius, unit);
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		return executeCommand(RedisCommand.GEOSEARCH, args,
				(cmd)->cmd.geosearch(key, new GeoCoordinate(longitude, latitude), radius,
						geoUnitConverter.convert(unit)),
				(cmd)->cmd.geosearch(key, new GeoCoordinate(longitude, latitude), radius,
						geoUnitConverter.convert(unit)),
				(cmd)->cmd.geosearch(key, new GeoCoordinate(longitude, latitude), radius,
						geoUnitConverter.convert(unit)), new ListConverter<>(new GeoRadiusResponseConverter()));
	}

	@Override
	public List<GeoRadius> geoSearch(final byte[] key, final double longitude, final double latitude,
	                                 final double radius, final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(key).add("FROMLONLAT").add(longitude, latitude)
				.add("BYRADIUS").add(radius, unit);
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		return executeCommand(RedisCommand.GEOSEARCH, args,
				(cmd)->cmd.geosearch(key, new GeoCoordinate(longitude, latitude), radius,
						geoUnitConverter.convert(unit)),
				(cmd)->cmd.geosearch(key, new GeoCoordinate(longitude, latitude), radius,
						geoUnitConverter.convert(unit)),
				(cmd)->cmd.geosearch(key, new GeoCoordinate(longitude, latitude), radius,
						geoUnitConverter.convert(unit)), new ListConverter<>(new GeoRadiusResponseConverter()));
	}

	@Override
	public List<GeoRadius> geoSearch(final String key, final double longitude, final double latitude,
	                                 final double radius, final GeoUnit unit, final GeoSearchArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add("FROMLONLAT").add(longitude, latitude)
				.add("BYRADIUS").add(radius, unit).add(argument);
		return geoSearch(key, longitude, latitude, radius, unit, new JedisGeoSearchParam(argument), args);
	}

	@Override
	public List<GeoRadius> geoSearch(final byte[] key, final double longitude, final double latitude,
	                                 final double radius, final GeoUnit unit, final GeoSearchArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add("FROMLONLAT").add(longitude, latitude)
				.add("BYRADIUS").add(radius, unit).add(argument);
		return geoSearch(key, longitude, latitude, radius, unit, new JedisGeoSearchParam(argument), args);
	}

	@Override
	public List<GeoRadius> geoSearch(final String key, final double longitude, final double latitude,
	                                 final double radius, final GeoUnit unit, final GeoSearchArgument argument,
	                                 final int count) {
		final CommandArguments args = CommandArguments.create(key).add("FROMLONLAT").add(longitude, latitude)
				.add("BYRADIUS").add(radius, unit).add(argument).add(Keyword.Common.COUNT, count);
		return geoSearch(key, longitude, latitude, radius, unit, new JedisGeoSearchParam(argument, count),
				args);
	}

	@Override
	public List<GeoRadius> geoSearch(final byte[] key, final double longitude, final double latitude,
	                                 final double radius, final GeoUnit unit, final GeoSearchArgument argument,
	                                 final int count) {
		final CommandArguments args = CommandArguments.create(key).add("FROMLONLAT").add(longitude, latitude)
				.add("BYRADIUS").add(radius, unit).add(argument).add(Keyword.Common.COUNT, count);
		return geoSearch(key, longitude, latitude, radius, unit, new JedisGeoSearchParam(argument, count),
				args);
	}

	@Override
	public List<GeoRadius> geoSearch(final String key, final double longitude, final double latitude,
	                                 final double radius, final GeoUnit unit, final GeoSearchArgument argument,
	                                 final int count, final boolean any) {
		final CommandArguments args = CommandArguments.create(key).add("FROMLONLAT").add(longitude, latitude)
				.add("BYRADIUS").add(radius, unit).add(argument).add(Keyword.Common.COUNT, count).add(any ?
						Keyword.Common.ANY : null);
		return geoSearch(key, longitude, latitude, radius, unit, new JedisGeoSearchParam(argument, count, any),
				args);
	}

	@Override
	public List<GeoRadius> geoSearch(final byte[] key, final double longitude, final double latitude,
	                                 final double radius, final GeoUnit unit, final GeoSearchArgument argument,
	                                 final int count, final boolean any) {
		final CommandArguments args = CommandArguments.create(key).add("FROMLONLAT").add(longitude, latitude)
				.add("BYRADIUS").add(radius, unit).add(argument).add(Keyword.Common.COUNT, count).add(any ?
						Keyword.Common.ANY : null);
		return geoSearch(key, longitude, latitude, radius, unit, new JedisGeoSearchParam(argument, count, any),
				args);
	}

	@Override
	public List<GeoRadius> geoSearch(final String key, final double longitude, final double latitude,
	                                 final double width, final double height, final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(key).add("FROMLONLAT").add(longitude, latitude)
				.add("BYBOX").add(width, height).add(unit);
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		return executeCommand(RedisCommand.GEOSEARCH, args,
				(cmd)->cmd.geosearch(key, new GeoCoordinate(longitude, latitude), width, height,
						geoUnitConverter.convert(unit)),
				(cmd)->cmd.geosearch(key, new GeoCoordinate(longitude, latitude), width, height,
						geoUnitConverter.convert(unit)),
				(cmd)->cmd.geosearch(key, new GeoCoordinate(longitude, latitude), width, height,
						geoUnitConverter.convert(unit)), new ListConverter<>(new GeoRadiusResponseConverter()));
	}

	@Override
	public List<GeoRadius> geoSearch(final byte[] key, final double longitude, final double latitude,
	                                 final double width, final double height, final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(key).add("FROMLONLAT").add(longitude, latitude)
				.add("BYBOX").add(width, height).add(unit);
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		return executeCommand(RedisCommand.GEOSEARCH, args,
				(cmd)->cmd.geosearch(key, new GeoCoordinate(longitude, latitude), width, height,
						geoUnitConverter.convert(unit)),
				(cmd)->cmd.geosearch(key, new GeoCoordinate(longitude, latitude), width, height,
						geoUnitConverter.convert(unit)),
				(cmd)->cmd.geosearch(key, new GeoCoordinate(longitude, latitude), width, height,
						geoUnitConverter.convert(unit)), new ListConverter<>(new GeoRadiusResponseConverter()));
	}

	@Override
	public List<GeoRadius> geoSearch(final String key, final double longitude, final double latitude,
	                                 final double width, final double height, final GeoUnit unit,
	                                 final GeoSearchArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add("FROMLONLAT").add(longitude, latitude)
				.add("BYBOX").add(width, height).add(unit).add(argument);
		return geoSearch(key, longitude, latitude, width, height, unit, new JedisGeoSearchParam(argument),
				args);
	}

	@Override
	public List<GeoRadius> geoSearch(final byte[] key, final double longitude, final double latitude,
	                                 final double width, final double height, final GeoUnit unit,
	                                 final GeoSearchArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add("FROMLONLAT").add(longitude, latitude)
				.add("BYBOX").add(width, height).add(unit).add(argument);
		return geoSearch(key, longitude, latitude, width, height, unit, new JedisGeoSearchParam(argument),
				args);
	}

	@Override
	public List<GeoRadius> geoSearch(final String key, final double longitude, final double latitude,
	                                 final double width, final double height, final GeoUnit unit,
	                                 final GeoSearchArgument argument, final int count) {
		final CommandArguments args = CommandArguments.create(key).add("FROMLONLAT").add(longitude, latitude)
				.add("BYBOX").add(width, height).add(unit).add(argument).add(Keyword.Common.COUNT, count);
		return geoSearch(key, longitude, latitude, width, height, unit,
				new JedisGeoSearchParam(argument, count), args);
	}

	@Override
	public List<GeoRadius> geoSearch(final byte[] key, final double longitude, final double latitude,
	                                 final double width, final double height, final GeoUnit unit,
	                                 final GeoSearchArgument argument, final int count) {
		final CommandArguments args = CommandArguments.create(key).add("FROMLONLAT").add(longitude, latitude)
				.add("BYBOX").add(width, height).add(unit).add(argument).add(Keyword.Common.COUNT, count);
		return geoSearch(key, longitude, latitude, width, height, unit,
				new JedisGeoSearchParam(argument, count), args);
	}

	@Override
	public List<GeoRadius> geoSearch(final String key, final double longitude, final double latitude,
	                                 final double width, final double height, final GeoUnit unit,
	                                 final GeoSearchArgument argument, final int count, final boolean any) {
		final CommandArguments args = CommandArguments.create(key).add("FROMLONLAT").add(longitude, latitude)
				.add("BYBOX").add(width, height).add(unit).add(argument).add(Keyword.Common.COUNT, count)
				.add(any ? Keyword.Common.ANY : null);
		return geoSearch(key, longitude, latitude, width, height, unit,
				new JedisGeoSearchParam(argument, count, any), args);
	}

	@Override
	public List<GeoRadius> geoSearch(final byte[] key, final double longitude, final double latitude,
	                                 final double width, final double height, final GeoUnit unit,
	                                 final GeoSearchArgument argument, final int count, final boolean any) {
		final CommandArguments args = CommandArguments.create(key).add("FROMLONLAT").add(longitude, latitude)
				.add("BYBOX").add(width, height).add(unit).add(argument).add(Keyword.Common.COUNT, count)
				.add(any ? Keyword.Common.ANY : null);
		return geoSearch(key, longitude, latitude, width, height, unit,
				new JedisGeoSearchParam(argument, count, any), args);
	}

	@Override
	public List<GeoRadius> geoSearch(final String key, final String member, final double radius, final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(key).add("FROMMEMBER", member).add("BYRADIUS", radius)
				.add(unit);
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		return executeCommand(RedisCommand.GEOSEARCH, args, (cmd)->cmd.geosearch(key, member, radius,
				geoUnitConverter.convert(unit)), (cmd)->cmd.geosearch(key, member, radius,
				geoUnitConverter.convert(unit)), (cmd)->cmd.geosearch(key, member, radius,
				geoUnitConverter.convert(unit)), new ListConverter<>(new GeoRadiusResponseConverter()));
	}

	@Override
	public List<GeoRadius> geoSearch(final byte[] key, final byte[] member, final double radius, final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(key).add("FROMMEMBER", member).add("BYRADIUS", radius)
				.add(unit);
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		return executeCommand(RedisCommand.GEOSEARCH, args, (cmd)->cmd.geosearch(key, member, radius,
				geoUnitConverter.convert(unit)), (cmd)->cmd.geosearch(key, member, radius,
				geoUnitConverter.convert(unit)), (cmd)->cmd.geosearch(key, member, radius,
				geoUnitConverter.convert(unit)), new ListConverter<>(new GeoRadiusResponseConverter()));
	}

	@Override
	public List<GeoRadius> geoSearch(final String key, final String member, final double radius, final GeoUnit unit,
	                                 final GeoSearchArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add("FROMMEMBER", member).add("BYRADIUS", radius)
				.add(unit).add(argument);
		return geoSearch(key, member, radius, unit, new JedisGeoSearchParam(argument), args);
	}

	@Override
	public List<GeoRadius> geoSearch(final byte[] key, final byte[] member, final double radius, final GeoUnit unit,
	                                 final GeoSearchArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add("FROMMEMBER", member).add("BYRADIUS", radius)
				.add(unit).add(argument);
		return geoSearch(key, member, radius, unit, new JedisGeoSearchParam(argument), args);
	}

	@Override
	public List<GeoRadius> geoSearch(final String key, final String member, final double radius, final GeoUnit unit,
	                                 final GeoSearchArgument argument, final int count) {
		final CommandArguments args = CommandArguments.create(key).add("FROMMEMBER", member).add("BYRADIUS", radius)
				.add(unit).add(argument).add(Keyword.Common.COUNT, count);
		return geoSearch(key, member, radius, unit, new JedisGeoSearchParam(argument, count), args);
	}

	@Override
	public List<GeoRadius> geoSearch(final byte[] key, final byte[] member, final double radius, final GeoUnit unit,
	                                 final GeoSearchArgument argument, final int count) {
		final CommandArguments args = CommandArguments.create(key).add("FROMMEMBER", member).add("BYRADIUS", radius)
				.add(unit).add(argument).add(Keyword.Common.COUNT, count);
		return geoSearch(key, member, radius, unit, new JedisGeoSearchParam(argument, count), args);
	}

	@Override
	public List<GeoRadius> geoSearch(final String key, final String member, final double radius, final GeoUnit unit,
	                                 final GeoSearchArgument argument, final int count, final boolean any) {
		final CommandArguments args = CommandArguments.create(key).add("FROMMEMBER", member).add("BYRADIUS", radius)
				.add(unit).add(argument).add(Keyword.Common.COUNT, count).add(any ? Keyword.Common.ANY : null);
		return geoSearch(key, member, radius, unit, new JedisGeoSearchParam(argument, count, any), args);
	}

	@Override
	public List<GeoRadius> geoSearch(final byte[] key, final byte[] member, final double radius, final GeoUnit unit,
	                                 final GeoSearchArgument argument, final int count, final boolean any) {
		final CommandArguments args = CommandArguments.create(key).add("FROMMEMBER", member).add("BYRADIUS", radius)
				.add(unit).add(argument).add(Keyword.Common.COUNT, count).add(any ? Keyword.Common.ANY : null);
		return geoSearch(key, member, radius, unit, new JedisGeoSearchParam(argument, count, any), args);
	}

	@Override
	public List<GeoRadius> geoSearch(final String key, final String member, final double width, final double height,
	                                 final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(key).add("FROMMEMBER", member).add("BYBOX")
				.add(width, height).add(unit);
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		return executeCommand(RedisCommand.GEOSEARCH, args, (cmd)->cmd.geosearch(key, member, width, height,
				geoUnitConverter.convert(unit)), (cmd)->cmd.geosearch(key, member, width, height,
				geoUnitConverter.convert(unit)), (cmd)->cmd.geosearch(key, member, width, height,
				geoUnitConverter.convert(unit)), new ListConverter<>(new GeoRadiusResponseConverter()));
	}

	@Override
	public List<GeoRadius> geoSearch(final byte[] key, final byte[] member, final double width, final double height,
	                                 final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(key).add("FROMMEMBER", member).add("BYBOX")
				.add(width, height).add(unit);
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		return executeCommand(RedisCommand.GEOSEARCH, args, (cmd)->cmd.geosearch(key, member, width, height,
				geoUnitConverter.convert(unit)), (cmd)->cmd.geosearch(key, member, width, height,
				geoUnitConverter.convert(unit)), (cmd)->cmd.geosearch(key, member, width, height,
				geoUnitConverter.convert(unit)), new ListConverter<>(new GeoRadiusResponseConverter()));
	}

	@Override
	public List<GeoRadius> geoSearch(final String key, final String member, final double width, final double height,
	                                 final GeoUnit unit, final GeoSearchArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add("FROMMEMBER", member).add("BYBOX")
				.add(width, height).add(unit).add(argument);
		return geoSearch(key, member, width, height, unit, new JedisGeoSearchParam(argument), args);
	}

	@Override
	public List<GeoRadius> geoSearch(final byte[] key, final byte[] member, final double width, final double height,
	                                 final GeoUnit unit, final GeoSearchArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add("FROMMEMBER", member).add("BYBOX")
				.add(width, height).add(unit).add(argument);
		return geoSearch(key, member, width, height, unit, new JedisGeoSearchParam(argument), args);
	}

	@Override
	public List<GeoRadius> geoSearch(final String key, final String member, final double width, final double height,
	                                 final GeoUnit unit, final GeoSearchArgument argument, final int count) {
		final CommandArguments args = CommandArguments.create(key).add("FROMMEMBER", member).add("BYBOX")
				.add(width, height).add(unit).add(argument).add(Keyword.Common.COUNT, count);
		return geoSearch(key, member, width, height, unit, new JedisGeoSearchParam(argument, count), args);
	}

	@Override
	public List<GeoRadius> geoSearch(final byte[] key, final byte[] member, final double width, final double height,
	                                 final GeoUnit unit, final GeoSearchArgument argument, final int count) {
		final CommandArguments args = CommandArguments.create(key).add("FROMMEMBER", member).add("BYBOX")
				.add(width, height).add(unit).add(argument).add(Keyword.Common.COUNT, count);
		return geoSearch(key, member, width, height, unit, new JedisGeoSearchParam(argument, count), args);
	}

	@Override
	public List<GeoRadius> geoSearch(final String key, final String member, final double width, final double height,
	                                 final GeoUnit unit, final GeoSearchArgument argument, final int count,
	                                 final boolean any) {
		final CommandArguments args = CommandArguments.create(key).add("FROMMEMBER", member).add("BYBOX")
				.add(width, height).add(unit).add(argument).add(Keyword.Common.COUNT, count)
				.add(any ? Keyword.Common.ANY : null);
		return geoSearch(key, member, width, height, unit, new JedisGeoSearchParam(argument, count, any), args);
	}

	@Override
	public List<GeoRadius> geoSearch(final byte[] key, final byte[] member, final double width, final double height,
	                                 final GeoUnit unit, final GeoSearchArgument argument, final int count,
	                                 final boolean any) {
		final CommandArguments args = CommandArguments.create(key).add("FROMMEMBER", member).add("BYBOX")
				.add(width, height).add(unit).add(argument).add(Keyword.Common.COUNT, count)
				.add(any ? Keyword.Common.ANY : null);
		return geoSearch(key, member, width, height, unit, new JedisGeoSearchParam(argument, count, any), args);
	}

	@Override
	public Long geoSearchStore(final String key, final String destKey, final double longitude, final double latitude,
	                           final double radius, final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(destKey, key).add("FROMLONLAT").add(longitude, latitude)
				.add("BYRADIUS").add(radius).add(unit);
		return geoSearchStore(key, destKey, longitude, latitude, radius, unit, new JedisGeoSearchParam(), false,
				args);
	}

	@Override
	public Long geoSearchStore(final byte[] key, final byte[] destKey, final double longitude, final double latitude,
	                           final double radius, final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(destKey, key).add("FROMLONLAT").add(longitude, latitude)
				.add("BYRADIUS").add(radius).add(unit);
		return geoSearchStore(key, destKey, longitude, latitude, radius, unit, new JedisGeoSearchParam(), false,
				args);
	}

	@Override
	public Long geoSearchStore(final String key, final String destKey, final double longitude, final double latitude,
	                           final double radius, final GeoUnit unit, final boolean storeDist) {
		final CommandArguments args = CommandArguments.create(destKey, key).add("FROMLONLAT").add(longitude, latitude)
				.add("BYRADIUS").add(radius).add(unit).add(storeDist ? "STOREDIST" : null);
		return geoSearchStore(key, destKey, longitude, latitude, radius, unit, new JedisGeoSearchParam(), storeDist,
				args);
	}

	@Override
	public Long geoSearchStore(final byte[] key, final byte[] destKey, final double longitude, final double latitude,
	                           final double radius, final GeoUnit unit, final boolean storeDist) {
		final CommandArguments args = CommandArguments.create(destKey, key).add("FROMLONLAT").add(longitude, latitude)
				.add("BYRADIUS").add(radius).add(unit).add(storeDist ? "STOREDIST" : null);
		return geoSearchStore(key, destKey, longitude, latitude, radius, unit, new JedisGeoSearchParam(), storeDist,
				args);
	}

	@Override
	public Long geoSearchStore(final String key, final String destKey, final double longitude, final double latitude,
	                           final double radius, final GeoUnit unit, final boolean storeDist, final int count) {
		final CommandArguments args = CommandArguments.create(destKey, key).add("FROMLONLAT").add(longitude, latitude)
				.add("BYRADIUS").add(radius).add(unit).add(Keyword.Common.COUNT, count)
				.add(storeDist ? "STOREDIST" : null);
		return geoSearchStore(key, destKey, longitude, latitude, radius, unit,
				new JedisGeoSearchParam(count), storeDist, args);
	}

	@Override
	public Long geoSearchStore(final byte[] key, final byte[] destKey, final double longitude, final double latitude,
	                           final double radius, final GeoUnit unit, final boolean storeDist, final int count) {
		final CommandArguments args = CommandArguments.create(destKey, key).add("FROMLONLAT").add(longitude, latitude)
				.add("BYRADIUS").add(radius).add(unit).add(Keyword.Common.COUNT, count)
				.add(storeDist ? "STOREDIST" : null);
		return geoSearchStore(key, destKey, longitude, latitude, radius, unit,
				new JedisGeoSearchParam(count), storeDist, args);
	}

	@Override
	public Long geoSearchStore(final String key, final String destKey, final double longitude, final double latitude,
	                           final double radius, final GeoUnit unit, final boolean storeDist, final int count,
	                           final boolean any) {
		final CommandArguments args = CommandArguments.create(destKey, key).add("FROMLONLAT").add(longitude, latitude)
				.add("BYRADIUS").add(radius).add(unit).add(Keyword.Common.COUNT, count)
				.add(any ? Keyword.Common.ANY : null).add(storeDist ? "STOREDIST" : null);
		return geoSearchStore(key, destKey, longitude, latitude, radius, unit,
				new JedisGeoSearchParam(count, any), storeDist, args);
	}

	@Override
	public Long geoSearchStore(final byte[] key, final byte[] destKey, final double longitude, final double latitude,
	                           final double radius, final GeoUnit unit, final boolean storeDist, final int count,
	                           final boolean any) {
		final CommandArguments args = CommandArguments.create(destKey, key).add("FROMLONLAT").add(longitude, latitude)
				.add("BYRADIUS").add(radius).add(unit).add(Keyword.Common.COUNT, count)
				.add(any ? Keyword.Common.ANY : null).add(storeDist ? "STOREDIST" : null);
		return geoSearchStore(key, destKey, longitude, latitude, radius, unit,
				new JedisGeoSearchParam(count, any), storeDist, args);
	}

	@Override
	public Long geoSearchStore(final String key, final String destKey, final double longitude, final double latitude,
	                           final double width, final double height, final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(destKey, key).add("FROMLONLAT").add(longitude, latitude)
				.add("BYBOX").add(width, height);
		return geoSearchStore(key, destKey, longitude, latitude, width, height, unit, new JedisGeoSearchParam(),
				false, args);
	}

	@Override
	public Long geoSearchStore(final byte[] key, final byte[] destKey, final double longitude, final double latitude,
	                           final double width, final double height, final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(destKey, key).add("FROMLONLAT").add(longitude, latitude)
				.add("BYBOX").add(width, height);
		return geoSearchStore(key, destKey, longitude, latitude, width, height, unit, new JedisGeoSearchParam(),
				false, args);
	}

	@Override
	public Long geoSearchStore(final String key, final String destKey, final double longitude, final double latitude,
	                           final double width, final double height, final GeoUnit unit, final boolean storeDist) {
		final CommandArguments args = CommandArguments.create(destKey, key).add("FROMLONLAT").add(longitude, latitude)
				.add("BYBOX").add(width, height).add(storeDist ? "STOREDIST" : null);
		return geoSearchStore(key, destKey, longitude, latitude, width, height, unit, new JedisGeoSearchParam(),
				storeDist, args);
	}

	@Override
	public Long geoSearchStore(final byte[] key, final byte[] destKey, final double longitude, final double latitude,
	                           final double width, final double height, final GeoUnit unit, final boolean storeDist) {
		final CommandArguments args = CommandArguments.create(destKey, key).add("FROMLONLAT").add(longitude, latitude)
				.add("BYBOX").add(width, height).add(storeDist ? "STOREDIST" : null);
		return geoSearchStore(key, destKey, longitude, latitude, width, height, unit, new JedisGeoSearchParam(),
				storeDist, args);
	}

	@Override
	public Long geoSearchStore(final String key, final String destKey, final double longitude, final double latitude,
	                           final double width, final double height, final GeoUnit unit, final boolean storeDist,
	                           final int count) {
		final CommandArguments args = CommandArguments.create(destKey, key).add("FROMLONLAT").add(longitude, latitude)
				.add("BYBOX").add(width, height).add(Keyword.Common.COUNT, count).add(storeDist ? "STOREDIST" : null);
		return geoSearchStore(key, destKey, longitude, latitude, width, height, unit,
				new JedisGeoSearchParam(count), storeDist, args);
	}

	@Override
	public Long geoSearchStore(final byte[] key, final byte[] destKey, final double longitude, final double latitude,
	                           final double width, final double height, final GeoUnit unit, final boolean storeDist,
	                           final int count) {
		final CommandArguments args = CommandArguments.create(destKey, key).add("FROMLONLAT").add(longitude, latitude)
				.add("BYBOX").add(width, height).add(Keyword.Common.COUNT, count).add(storeDist ? "STOREDIST" : null);
		return geoSearchStore(key, destKey, longitude, latitude, width, height, unit,
				new JedisGeoSearchParam(count), storeDist, args);
	}

	@Override
	public Long geoSearchStore(final String key, final String destKey, final double longitude, final double latitude,
	                           final double width, final double height, final GeoUnit unit, final boolean storeDist,
	                           final int count, final boolean any) {
		final CommandArguments args = CommandArguments.create(destKey, key).add("FROMLONLAT").add(longitude, latitude)
				.add("BYBOX").add(width, height).add(Keyword.Common.COUNT, count).add(any ? Keyword.Common.ANY : null)
				.add(storeDist ? "STOREDIST" : null);
		return geoSearchStore(key, destKey, longitude, latitude, width, height, unit,
				new JedisGeoSearchParam(count), storeDist, args);
	}

	@Override
	public Long geoSearchStore(final byte[] key, final byte[] destKey, final double longitude, final double latitude,
	                           final double width, final double height, final GeoUnit unit, final boolean storeDist,
	                           final int count, final boolean any) {
		final CommandArguments args = CommandArguments.create(destKey, key).add("FROMLONLAT").add(longitude, latitude)
				.add("BYBOX").add(width, height).add(Keyword.Common.COUNT, count).add(any ? Keyword.Common.ANY : null)
				.add(storeDist ? "STOREDIST" : null);
		return geoSearchStore(key, destKey, longitude, latitude, width, height, unit,
				new JedisGeoSearchParam(count), storeDist, args);
	}

	@Override
	public Long geoSearchStore(final String key, final String destKey, final String member, final double radius,
	                           final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(destKey, key).add("FROMMEMBER").add(member)
				.add("BYRADIUS").add(radius).add(unit);
		return geoSearchStore(key, destKey, member, radius, unit, new JedisGeoSearchParam(), false, args);
	}

	@Override
	public Long geoSearchStore(final byte[] key, final byte[] destKey, final byte[] member, final double radius,
	                           final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(destKey, key).add("FROMMEMBER").add(member)
				.add("BYRADIUS").add(radius).add(unit);
		return geoSearchStore(key, destKey, member, radius, unit, new JedisGeoSearchParam(), false, args);
	}

	@Override
	public Long geoSearchStore(final String key, final String destKey, final String member, final double radius,
	                           final GeoUnit unit, final boolean storeDist) {
		final CommandArguments args = CommandArguments.create(destKey, key).add("FROMMEMBER").add(member)
				.add("BYRADIUS").add(radius).add(unit).add(storeDist ? "STOREDIST" : null);
		return geoSearchStore(key, destKey, member, radius, unit, new JedisGeoSearchParam(), storeDist, args);
	}

	@Override
	public Long geoSearchStore(final byte[] key, final byte[] destKey, final byte[] member, final double radius,
	                           final GeoUnit unit, final boolean storeDist) {
		final CommandArguments args = CommandArguments.create(destKey, key).add("FROMMEMBER").add(member)
				.add("BYRADIUS").add(radius).add(unit).add(storeDist ? "STOREDIST" : null);
		return geoSearchStore(key, destKey, member, radius, unit, new JedisGeoSearchParam(), storeDist, args);
	}

	@Override
	public Long geoSearchStore(final String key, final String destKey, final String member, final double radius,
	                           final GeoUnit unit, final boolean storeDist, final int count) {
		final CommandArguments args = CommandArguments.create(destKey, key).add("FROMMEMBER").add(member)
				.add("BYRADIUS").add(radius).add(unit).add(Keyword.Common.COUNT, count)
				.add(storeDist ? "STOREDIST" : null);
		return geoSearchStore(key, destKey, member, radius, unit, new JedisGeoSearchParam(count),
				storeDist, args);
	}

	@Override
	public Long geoSearchStore(final byte[] key, final byte[] destKey, final byte[] member, final double radius,
	                           final GeoUnit unit, final boolean storeDist, final int count) {
		final CommandArguments args = CommandArguments.create(destKey, key).add("FROMMEMBER").add(member)
				.add("BYRADIUS").add(radius).add(unit).add(Keyword.Common.COUNT, count)
				.add(storeDist ? "STOREDIST" : null);
		return geoSearchStore(key, destKey, member, radius, unit, new JedisGeoSearchParam(count),
				storeDist, args);
	}

	@Override
	public Long geoSearchStore(final String key, final String destKey, final String member, final double radius,
	                           final GeoUnit unit, final boolean storeDist, final int count, final boolean any) {
		final CommandArguments args = CommandArguments.create(destKey, key).add("FROMMEMBER").add(member)
				.add("BYRADIUS").add(radius).add(unit).add(Keyword.Common.COUNT, count)
				.add(any ? Keyword.Common.ANY : null).add(storeDist ? "STOREDIST" : null);
		return geoSearchStore(key, destKey, member, radius, unit, new JedisGeoSearchParam(count, any),
				storeDist, args);
	}

	@Override
	public Long geoSearchStore(final byte[] key, final byte[] destKey, final byte[] member, final double radius,
	                           final GeoUnit unit, final boolean storeDist, final int count, final boolean any) {
		final CommandArguments args = CommandArguments.create(destKey, key).add("FROMMEMBER").add(member)
				.add("BYRADIUS").add(radius).add(unit).add(Keyword.Common.COUNT, count)
				.add(any ? Keyword.Common.ANY : null).add(storeDist ? "STOREDIST" : null);
		return geoSearchStore(key, destKey, member, radius, unit, new JedisGeoSearchParam(count, any),
				storeDist, args);
	}

	@Override
	public Long geoSearchStore(final String key, final String destKey, final String member, final double width,
	                           final double height, final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(destKey, key).add("FROMMEMBER").add(member)
				.add("BYBOX").add(width, height).add(unit);
		return geoSearchStore(key, destKey, member, width, height, unit, new JedisGeoSearchParam(), false, args);
	}

	@Override
	public Long geoSearchStore(final byte[] key, final byte[] destKey, final byte[] member, final double width,
	                           final double height, final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(destKey, key).add("FROMMEMBER").add(member)
				.add("BYBOX").add(width, height).add(unit);
		return geoSearchStore(key, destKey, member, width, height, unit, new JedisGeoSearchParam(), false, args);
	}

	@Override
	public Long geoSearchStore(final String key, final String destKey, final String member, final double width,
	                           final double height, final GeoUnit unit, final boolean storeDist) {
		final CommandArguments args = CommandArguments.create(destKey, key).add("FROMMEMBER").add(member)
				.add("BYBOX").add(width, height).add(unit).add(storeDist ? "STOREDIST" : null);
		return geoSearchStore(key, destKey, member, width, height, unit, new JedisGeoSearchParam(), storeDist, args);
	}

	@Override
	public Long geoSearchStore(final byte[] key, final byte[] destKey, final byte[] member, final double width,
	                           final double height, final GeoUnit unit, final boolean storeDist) {
		final CommandArguments args = CommandArguments.create(destKey, key).add("FROMMEMBER").add(member)
				.add("BYBOX").add(width, height).add(unit).add(storeDist ? "STOREDIST" : null);
		return geoSearchStore(key, destKey, member, width, height, unit, new JedisGeoSearchParam(), storeDist, args);
	}

	@Override
	public Long geoSearchStore(final String key, final String destKey, final String member, final double width,
	                           final double height, final GeoUnit unit, final boolean storeDist, final int count) {
		final CommandArguments args = CommandArguments.create(destKey, key).add("FROMMEMBER").add(member)
				.add("BYBOX").add(width, height).add(unit).add(Keyword.Common.COUNT, count)
				.add(storeDist ? "STOREDIST" : null);
		return geoSearchStore(key, destKey, member, width, height, unit,
				new JedisGeoSearchParam(count), storeDist, args);
	}

	@Override
	public Long geoSearchStore(final byte[] key, final byte[] destKey, final byte[] member, final double width,
	                           final double height, final GeoUnit unit, final boolean storeDist, final int count) {
		final CommandArguments args = CommandArguments.create(destKey, key).add("FROMMEMBER").add(member)
				.add("BYBOX").add(width, height).add(unit).add(Keyword.Common.COUNT, count)
				.add(storeDist ? "STOREDIST" : null);
		return geoSearchStore(key, destKey, member, width, height, unit,
				new JedisGeoSearchParam(count), storeDist, args);
	}

	@Override
	public Long geoSearchStore(final String key, final String destKey, final String member, final double width,
	                           final double height, final GeoUnit unit, final boolean storeDist, final int count,
	                           final boolean any) {
		final CommandArguments args = CommandArguments.create(destKey, key).add("FROMMEMBER").add(member)
				.add("BYBOX").add(width, height).add(unit).add(Keyword.Common.COUNT, count)
				.add(any ? Keyword.Common.ANY : null).add(storeDist ? "STOREDIST" : null);
		return geoSearchStore(key, destKey, member, width, height, unit,
				new JedisGeoSearchParam(count, any), storeDist, args);
	}

	@Override
	public Long geoSearchStore(final byte[] key, final byte[] destKey, final byte[] member, final double width,
	                           final double height, final GeoUnit unit, final boolean storeDist, final int count,
	                           final boolean any) {
		final CommandArguments args = CommandArguments.create(destKey, key).add("FROMMEMBER").add(member)
				.add("BYBOX").add(width, height).add(unit).add(Keyword.Common.COUNT, count)
				.add(any ? Keyword.Common.ANY : null).add(storeDist ? "STOREDIST" : null);
		return geoSearchStore(key, destKey, member, width, height, unit,
				new JedisGeoSearchParam(count, any), storeDist, args);
	}

	@SuppressWarnings({"unchecked"})
	public <K> Map<K, GeoCoordinate> createMemberCoordinates(final KeyValue<K, Geo>... memberCoordinates) {
		final ArrayKeyValueMapConverter<K, Geo, K, GeoCoordinate> arrayKeyValueMapConverter =
				new ArrayKeyValueMapConverter<>((k)->k, (v)->v == null ? null : new GeoCoordinate(v.getLongitude(),
						v.getLatitude()));
		return arrayKeyValueMapConverter.convert(memberCoordinates);
	}

	private Long geoAdd(final String key, final GeoAddParams geoAddParams,
	                    final KeyValue<String, Geo>[] memberCoordinates, final CommandArguments args) {
		return executeCommand(RedisCommand.GEOADD, args,
				(cmd)->cmd.geoadd(key, geoAddParams, createMemberCoordinates(memberCoordinates)),
				(cmd)->cmd.geoadd(key, geoAddParams, createMemberCoordinates(memberCoordinates)),
				(cmd)->cmd.geoadd(key, geoAddParams, createMemberCoordinates(memberCoordinates)));
	}

	private Long geoAdd(final byte[] key, final GeoAddParams geoAddParams,
	                    final KeyValue<byte[], Geo>[] memberCoordinates, final CommandArguments args) {
		return executeCommand(RedisCommand.GEOADD, args,
				(cmd)->cmd.geoadd(key, geoAddParams, createMemberCoordinates(memberCoordinates)),
				(cmd)->cmd.geoadd(key, geoAddParams, createMemberCoordinates(memberCoordinates)),
				(cmd)->cmd.geoadd(key, geoAddParams, createMemberCoordinates(memberCoordinates)));
	}

	private List<GeoRadius> geoRadius(final String key, final double longitude, final double latitude,
	                                  final double radius, final GeoUnit unit, final GeoRadiusParam geoRadiusParam,
	                                  final CommandArguments args) {
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		return executeCommand(RedisCommand.GEORADIUS, args,
				(cmd)->cmd.georadius(key, longitude, latitude, radius, geoUnitConverter.convert(unit),
						geoRadiusParam),
				(cmd)->cmd.georadius(key, longitude, latitude, radius, geoUnitConverter.convert(unit),
						geoRadiusParam),
				(cmd)->cmd.georadius(key, longitude, latitude, radius, geoUnitConverter.convert(unit),
						geoRadiusParam), new ListConverter<>(new GeoRadiusResponseConverter()));
	}

	private List<GeoRadius> geoRadius(final byte[] key, final double longitude, final double latitude,
	                                  final double radius, final GeoUnit unit, final GeoRadiusParam geoRadiusParam,
	                                  final CommandArguments args) {
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		return executeCommand(RedisCommand.GEORADIUS, args,
				(cmd)->cmd.georadius(key, longitude, latitude, radius, geoUnitConverter.convert(unit),
						geoRadiusParam),
				(cmd)->cmd.georadius(key, longitude, latitude, radius, geoUnitConverter.convert(unit),
						geoRadiusParam),
				(cmd)->cmd.georadius(key, longitude, latitude, radius, geoUnitConverter.convert(unit),
						geoRadiusParam), new ListConverter<>(new GeoRadiusResponseConverter()));
	}

	private Long geoRadius(final String key, final String destKey, final GeoStoreOption storeOption,
	                       final double longitude, final double latitude, final double radius, final GeoUnit unit,
	                       final GeoRadiusParam geoRadiusParam, final CommandArguments args) {
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		return executeCommand(RedisCommand.GEORADIUS, args, (cmd)->cmd.georadiusStore(key, longitude, latitude, radius,
						geoUnitConverter.convert(unit), geoRadiusParam, new JedisGeoRadiusStoreParam(destKey, storeOption)),
				(cmd)->cmd.georadiusStore(key, longitude, latitude, radius, geoUnitConverter.convert(unit),
						geoRadiusParam, new JedisGeoRadiusStoreParam(destKey, storeOption)),
				(cmd)->cmd.georadiusStore(key, longitude, latitude, radius, geoUnitConverter.convert(unit),
						geoRadiusParam, new JedisGeoRadiusStoreParam(destKey, storeOption)));
	}

	private Long geoRadius(final byte[] key, final byte[] destKey, final GeoStoreOption storeOption,
	                       final double longitude, final double latitude, final double radius, final GeoUnit unit,
	                       final GeoRadiusParam geoRadiusParam, final CommandArguments args) {
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		return executeCommand(RedisCommand.GEORADIUS, args, (cmd)->cmd.georadiusStore(key, longitude, latitude, radius,
						geoUnitConverter.convert(unit), geoRadiusParam, new JedisGeoRadiusStoreParam(destKey, storeOption)),
				(cmd)->cmd.georadiusStore(key, longitude, latitude, radius, geoUnitConverter.convert(unit),
						geoRadiusParam, new JedisGeoRadiusStoreParam(destKey, storeOption)),
				(cmd)->cmd.georadiusStore(key, longitude, latitude, radius, geoUnitConverter.convert(unit),
						geoRadiusParam, new JedisGeoRadiusStoreParam(destKey, storeOption)));
	}

	private List<GeoRadius> geoRadiusRo(final String key, final double longitude, final double latitude,
	                                    final double radius, final GeoUnit unit, final GeoRadiusParam geoRadiusParam,
	                                    final CommandArguments args) {
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		return executeCommand(RedisCommand.GEORADIUS_RO, args,
				(cmd)->cmd.georadiusReadonly(key, longitude, latitude, radius, geoUnitConverter.convert(unit),
						geoRadiusParam),
				(cmd)->cmd.georadiusReadonly(key, longitude, latitude, radius, geoUnitConverter.convert(unit),
						geoRadiusParam),
				(cmd)->cmd.georadiusReadonly(key, longitude, latitude, radius, geoUnitConverter.convert(unit),
						geoRadiusParam), new ListConverter<>(new GeoRadiusResponseConverter()));
	}

	private List<GeoRadius> geoRadiusRo(final byte[] key, final double longitude, final double latitude,
	                                    final double radius, final GeoUnit unit, final GeoRadiusParam geoRadiusParam,
	                                    final CommandArguments args) {
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		return executeCommand(RedisCommand.GEORADIUS_RO, args,
				(cmd)->cmd.georadiusReadonly(key, longitude, latitude, radius, geoUnitConverter.convert(unit),
						geoRadiusParam),
				(cmd)->cmd.georadiusReadonly(key, longitude, latitude, radius, geoUnitConverter.convert(unit),
						geoRadiusParam),
				(cmd)->cmd.georadiusReadonly(key, longitude, latitude, radius, geoUnitConverter.convert(unit),
						geoRadiusParam), new ListConverter<>(new GeoRadiusResponseConverter()));
	}

	private List<GeoRadius> geoRadiusByMember(final String key, final String member, final double radius,
	                                          final GeoUnit unit, final GeoRadiusParam geoRadiusParam,
	                                          final CommandArguments args) {
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		return executeCommand(RedisCommand.GEORADIUSBYMEMBER, args,
				(cmd)->cmd.georadiusByMember(key, member, radius, geoUnitConverter.convert(unit),
						geoRadiusParam),
				(cmd)->cmd.georadiusByMember(key, member, radius, geoUnitConverter.convert(unit),
						geoRadiusParam),
				(cmd)->cmd.georadiusByMember(key, member, radius, geoUnitConverter.convert(unit),
						geoRadiusParam), new ListConverter<>(new GeoRadiusResponseConverter()));
	}

	private List<GeoRadius> geoRadiusByMember(final byte[] key, final byte[] member, final double radius,
	                                          final GeoUnit unit, final GeoRadiusParam geoRadiusParam,
	                                          final CommandArguments args) {
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		return executeCommand(RedisCommand.GEORADIUSBYMEMBER, args,
				(cmd)->cmd.georadiusByMember(key, member, radius, geoUnitConverter.convert(unit),
						geoRadiusParam),
				(cmd)->cmd.georadiusByMember(key, member, radius, geoUnitConverter.convert(unit),
						geoRadiusParam),
				(cmd)->cmd.georadiusByMember(key, member, radius, geoUnitConverter.convert(unit),
						geoRadiusParam), new ListConverter<>(new GeoRadiusResponseConverter()));
	}

	private Long geoRadiusByMember(final String key, final String destKey, final GeoStoreOption storeOption,
	                               final String member, final double radius, final GeoUnit unit,
	                               final GeoRadiusParam geoRadiusParam, final CommandArguments args) {
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		return executeCommand(RedisCommand.GEORADIUSBYMEMBER, args,
				(cmd)->cmd.georadiusByMemberStore(key, member, radius, geoUnitConverter.convert(unit),
						geoRadiusParam, new JedisGeoRadiusStoreParam(destKey, storeOption)),
				(cmd)->cmd.georadiusByMemberStore(key, member, radius, geoUnitConverter.convert(unit),
						geoRadiusParam, new JedisGeoRadiusStoreParam(destKey, storeOption)),
				(cmd)->cmd.georadiusByMemberStore(key, member, radius, geoUnitConverter.convert(unit),
						geoRadiusParam, new JedisGeoRadiusStoreParam(destKey, storeOption)));
	}

	private Long geoRadiusByMember(final byte[] key, final byte[] destKey, final GeoStoreOption storeOption,
	                               final byte[] member, final double radius, final GeoUnit unit,
	                               final GeoRadiusParam geoRadiusParam, final CommandArguments args) {
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		return executeCommand(RedisCommand.GEORADIUSBYMEMBER, args,
				(cmd)->cmd.georadiusByMemberStore(key, member, radius, geoUnitConverter.convert(unit),
						geoRadiusParam, new JedisGeoRadiusStoreParam(destKey, storeOption)),
				(cmd)->cmd.georadiusByMemberStore(key, member, radius, geoUnitConverter.convert(unit),
						geoRadiusParam, new JedisGeoRadiusStoreParam(destKey, storeOption)),
				(cmd)->cmd.georadiusByMemberStore(key, member, radius, geoUnitConverter.convert(unit),
						geoRadiusParam, new JedisGeoRadiusStoreParam(destKey, storeOption)));
	}

	private List<GeoRadius> geoRadiusByMemberRo(final String key, final String member, final double radius,
	                                            final GeoUnit unit, final GeoRadiusParam geoRadiusParam,
	                                            final CommandArguments args) {
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		return executeCommand(RedisCommand.GEORADIUSBYMEMBER_RO, args,
				(cmd)->cmd.georadiusByMemberReadonly(key, member, radius, geoUnitConverter.convert(unit),
						geoRadiusParam),
				(cmd)->cmd.georadiusByMemberReadonly(key, member, radius, geoUnitConverter.convert(unit),
						geoRadiusParam),
				(cmd)->cmd.georadiusByMemberReadonly(key, member, radius, geoUnitConverter.convert(unit),
						geoRadiusParam), new ListConverter<>(new GeoRadiusResponseConverter()));
	}

	private List<GeoRadius> geoRadiusByMemberRo(final byte[] key, final byte[] member, final double radius,
	                                            final GeoUnit unit, final GeoRadiusParam geoRadiusParam,
	                                            final CommandArguments args) {
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		return executeCommand(RedisCommand.GEORADIUSBYMEMBER_RO, args,
				(cmd)->cmd.georadiusByMemberReadonly(key, member, radius, geoUnitConverter.convert(unit),
						geoRadiusParam),
				(cmd)->cmd.georadiusByMemberReadonly(key, member, radius, geoUnitConverter.convert(unit),
						geoRadiusParam),
				(cmd)->cmd.georadiusByMemberReadonly(key, member, radius, geoUnitConverter.convert(unit),
						geoRadiusParam), new ListConverter<>(new GeoRadiusResponseConverter()));
	}

	private List<GeoRadius> geoSearch(final String key, final double longitude, final double latitude,
	                                  final double radius, final GeoUnit unit, final GeoSearchParam geoSearchParam,
	                                  final CommandArguments args) {
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		geoSearchParam.fromLonLat(longitude, latitude).byRadius(radius, geoUnitConverter.convert(unit));
		return geoSearch(key, geoSearchParam, args);
	}

	private List<GeoRadius> geoSearch(final byte[] key, final double longitude, final double latitude,
	                                  final double radius, final GeoUnit unit, final GeoSearchParam geoSearchParam,
	                                  final CommandArguments args) {
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		geoSearchParam.fromLonLat(longitude, latitude).byRadius(radius, geoUnitConverter.convert(unit));
		return geoSearch(key, geoSearchParam, args);
	}

	private List<GeoRadius> geoSearch(final String key, final double longitude, final double latitude,
	                                  final double width, final double height, final GeoUnit unit,
	                                  final GeoSearchParam geoSearchParam, final CommandArguments args) {
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		geoSearchParam.fromLonLat(longitude, latitude).byBox(width, height, geoUnitConverter.convert(unit));
		return geoSearch(key, geoSearchParam, args);
	}

	private List<GeoRadius> geoSearch(final byte[] key, final double longitude, final double latitude,
	                                  final double width, final double height, final GeoUnit unit,
	                                  final GeoSearchParam geoSearchParam, final CommandArguments args) {
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		geoSearchParam.fromLonLat(longitude, latitude).byBox(width, height, geoUnitConverter.convert(unit));
		return geoSearch(key, geoSearchParam, args);
	}

	private List<GeoRadius> geoSearch(final String key, final String member, final double radius, final GeoUnit unit,
	                                  final GeoSearchParam geoSearchParam, final CommandArguments args) {
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		geoSearchParam.fromMember(member).byRadius(radius, geoUnitConverter.convert(unit));
		return geoSearch(key, geoSearchParam, args);
	}

	private List<GeoRadius> geoSearch(final byte[] key, final byte[] member, final double radius, final GeoUnit unit,
	                                  final GeoSearchParam geoSearchParam, final CommandArguments args) {
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		geoSearchParam.fromMember(SafeEncoder.encode(member)).byRadius(radius, geoUnitConverter.convert(unit));
		return geoSearch(key, geoSearchParam, args);
	}

	private List<GeoRadius> geoSearch(final String key, final String member, final double width, final double height,
	                                  final GeoUnit unit, final GeoSearchParam geoSearchParam,
	                                  final CommandArguments args) {
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		geoSearchParam.fromMember(member).byBox(width, height, geoUnitConverter.convert(unit));
		return geoSearch(key, geoSearchParam, args);
	}

	private List<GeoRadius> geoSearch(final byte[] key, final byte[] member, final double width, final double height,
	                                  final GeoUnit unit, final GeoSearchParam geoSearchParam,
	                                  final CommandArguments args) {
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		geoSearchParam.fromMember(SafeEncoder.encode(member)).byBox(width, height, geoUnitConverter.convert(unit));
		return geoSearch(key, geoSearchParam, args);
	}

	private List<GeoRadius> geoSearch(final String key, final GeoSearchParam geoSearchParam,
	                                  final CommandArguments args) {
		return executeCommand(RedisCommand.GEOSEARCH, args, (cmd)->cmd.geosearch(key, geoSearchParam),
				(cmd)->cmd.geosearch(key, geoSearchParam), (cmd)->cmd.geosearch(key, geoSearchParam),
				new ListConverter<>(new GeoRadiusResponseConverter()));
	}

	private List<GeoRadius> geoSearch(final byte[] key, final GeoSearchParam geoSearchParam,
	                                  final CommandArguments args) {
		return executeCommand(RedisCommand.GEOSEARCH, args, (cmd)->cmd.geosearch(key, geoSearchParam),
				(cmd)->cmd.geosearch(key, geoSearchParam), (cmd)->cmd.geosearch(key, geoSearchParam),
				new ListConverter<>(new GeoRadiusResponseConverter()));
	}

	private Long geoSearchStore(final String key, final String destKey, final double longitude, final double latitude,
	                            final double radius, final GeoUnit unit, final GeoSearchParam geoSearchParam,
	                            final boolean storeDist, final CommandArguments args) {
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		geoSearchParam.fromLonLat(longitude, latitude).byRadius(radius, geoUnitConverter.convert(unit));
		return geoSearchStore(key, destKey, geoSearchParam, storeDist, args);
	}

	private Long geoSearchStore(final byte[] key, final byte[] destKey, final double longitude, final double latitude,
	                            final double radius, final GeoUnit unit, final GeoSearchParam geoSearchParam,
	                            final boolean storeDist, final CommandArguments args) {
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		geoSearchParam.fromLonLat(longitude, latitude).byRadius(radius, geoUnitConverter.convert(unit));
		return geoSearchStore(key, destKey, geoSearchParam, storeDist, args);
	}

	private Long geoSearchStore(final String key, final String destKey, final double longitude, final double latitude,
	                            final double width, final double height, final GeoUnit unit,
	                            final GeoSearchParam geoSearchParam, final boolean storeDist,
	                            final CommandArguments args) {
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		geoSearchParam.fromLonLat(longitude, latitude).byBox(width, height, geoUnitConverter.convert(unit));
		return geoSearchStore(key, destKey, geoSearchParam, storeDist, args);
	}

	private Long geoSearchStore(final byte[] key, final byte[] destKey, final double longitude, final double latitude,
	                            final double width, final double height, final GeoUnit unit,
	                            final GeoSearchParam geoSearchParam, final boolean storeDist,
	                            final CommandArguments args) {
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		geoSearchParam.fromLonLat(longitude, latitude).byBox(width, height, geoUnitConverter.convert(unit));
		return geoSearchStore(key, destKey, geoSearchParam, storeDist, args);
	}

	private Long geoSearchStore(final String key, final String destKey, final String member, final double radius,
	                            final GeoUnit unit, final GeoSearchParam geoSearchParam, final boolean storeDist,
	                            final CommandArguments args) {
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		geoSearchParam.fromMember(member).byRadius(radius, geoUnitConverter.convert(unit));
		return geoSearchStore(key, destKey, geoSearchParam, storeDist, args);
	}

	private Long geoSearchStore(final byte[] key, final byte[] destKey, final byte[] member, final double radius,
	                            final GeoUnit unit, final GeoSearchParam geoSearchParam, final boolean storeDist,
	                            final CommandArguments args) {
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		geoSearchParam.fromMember(SafeEncoder.encode(member)).byRadius(radius, geoUnitConverter.convert(unit));
		return geoSearchStore(key, destKey, geoSearchParam, storeDist, args);
	}

	private Long geoSearchStore(final String key, final String destKey, final String member, final double width,
	                            final double height, final GeoUnit unit, final GeoSearchParam geoSearchParam,
	                            final boolean storeDist, final CommandArguments args) {
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		geoSearchParam.fromMember(member).byBox(width, height, geoUnitConverter.convert(unit));
		return geoSearchStore(key, destKey, geoSearchParam, storeDist, args);
	}

	private Long geoSearchStore(final byte[] key, final byte[] destKey, final byte[] member, final double width,
	                            final double height, final GeoUnit unit, final GeoSearchParam geoSearchParam,
	                            final boolean storeDist, final CommandArguments args) {
		final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();
		geoSearchParam.fromMember(SafeEncoder.encode(member)).byBox(width, height, geoUnitConverter.convert(unit));
		return geoSearchStore(key, destKey, geoSearchParam, storeDist, args);
	}

	private Long geoSearchStore(final String key, final String destKey, final GeoSearchParam geoSearchParam,
	                            final boolean storeDist, final CommandArguments args) {
		if(storeDist){
			return executeCommand(RedisCommand.GEOSEARCHSTORE, args,
					(cmd)->cmd.geosearchStoreStoreDist(key, destKey, geoSearchParam),
					(cmd)->cmd.geosearchStoreStoreDist(key, destKey, geoSearchParam),
					(cmd)->cmd.geosearchStoreStoreDist(key, destKey, geoSearchParam));
		}else{
			return executeCommand(RedisCommand.GEOSEARCHSTORE, args,
					(cmd)->cmd.geosearchStore(key, destKey, geoSearchParam),
					(cmd)->cmd.geosearchStore(key, destKey, geoSearchParam),
					(cmd)->cmd.geosearchStore(key, destKey, geoSearchParam));
		}
	}

	private Long geoSearchStore(final byte[] key, final byte[] destKey, final GeoSearchParam geoSearchParam,
	                            final boolean storeDist, final CommandArguments args) {
		if(storeDist){
			return executeCommand(RedisCommand.GEOSEARCHSTORE, args,
					(cmd)->cmd.geosearchStoreStoreDist(key, destKey, geoSearchParam),
					(cmd)->cmd.geosearchStoreStoreDist(key, destKey, geoSearchParam),
					(cmd)->cmd.geosearchStoreStoreDist(key, destKey, geoSearchParam));
		}else{
			return executeCommand(RedisCommand.GEOSEARCHSTORE, args,
					(cmd)->cmd.geosearchStore(key, destKey, geoSearchParam),
					(cmd)->cmd.geosearchStore(key, destKey, geoSearchParam),
					(cmd)->cmd.geosearchStore(key, destKey, geoSearchParam));
		}
	}

}
