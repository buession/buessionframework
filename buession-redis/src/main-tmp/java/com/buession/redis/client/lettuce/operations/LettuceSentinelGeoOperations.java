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
package com.buession.redis.client.lettuce.operations;

import com.buession.lang.Geo;
import com.buession.redis.client.lettuce.LettuceSentinelClient;
import com.buession.redis.core.GeoRadius;
import com.buession.redis.core.GeoUnit;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.Command;
import com.buession.redis.core.command.args.GeoAddArgument;
import com.buession.redis.core.command.args.GeoRadiusArgument;
import com.buession.redis.core.command.args.GeoSearchArgument;
import com.buession.redis.core.command.args.GeoSearchStoreArgument;

import java.util.List;
import java.util.Map;

/**
 * Lettuce 哨兵模式地理位置命令操作
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public final class LettuceSentinelGeoOperations extends AbstractGeoOperations<LettuceSentinelClient> {

	public LettuceSentinelGeoOperations(final LettuceSentinelClient client) {
		super(client);
	}

	@Override
	public Long geoAdd(final String key, final String member, final double longitude, final double latitude) {
		final CommandArguments args = CommandArguments.create(key).add(longitude).add(latitude).add(member);
		return notCommand(client, Command.GEOADD, args);
	}

	@Override
	public Long geoAdd(final byte[] key, final byte[] member, final double longitude, final double latitude) {
		final CommandArguments args = CommandArguments.create(key).add(longitude).add(latitude).add(member);
		return notCommand(client, Command.GEOADD, args);
	}

	@Override
	public Long geoAdd(final String key, final Map<String, Geo> memberCoordinates) {
		final CommandArguments args = CommandArguments.create(key).add(memberCoordinates);
		return notCommand(client, Command.GEOADD, args);
	}

	@Override
	public Long geoAdd(final byte[] key, final Map<byte[], Geo> memberCoordinates) {
		final CommandArguments args = CommandArguments.create(key).add(memberCoordinates);
		return notCommand(client, Command.GEOADD, args);
	}

	@Override
	public Long geoAdd(final String key, final String member, final double longitude, final double latitude,
					   final GeoAddArgument geoAddArgument) {
		final CommandArguments args = CommandArguments.create(key).add(geoAddArgument).add(longitude).add(latitude)
				.add(member);
		return notCommand(client, Command.GEOADD, args);
	}

	@Override
	public Long geoAdd(final byte[] key, final byte[] member, final double longitude, final double latitude,
					   final GeoAddArgument geoAddArgument) {
		final CommandArguments args = CommandArguments.create(key).add(geoAddArgument).add(longitude).add(latitude)
				.add(member);
		return notCommand(client, Command.GEOADD, args);
	}

	@Override
	public Long geoAdd(final String key, final Map<String, Geo> memberCoordinates,
					   final GeoAddArgument geoAddArgument) {
		final CommandArguments args = CommandArguments.create(key).add(memberCoordinates);
		return notCommand(client, Command.GEOADD, args);
	}

	@Override
	public Long geoAdd(final byte[] key, final Map<byte[], Geo> memberCoordinates,
					   final GeoAddArgument geoAddArgument) {
		final CommandArguments args = CommandArguments.create(key).add(memberCoordinates);
		return notCommand(client, Command.GEOADD, args);
	}

	@Override
	public Double geoDist(final String key, final String member1, final String member2) {
		final CommandArguments args = CommandArguments.create(key).add(member1).add(member2);
		return notCommand(client, Command.GEODIST, args);
	}

	@Override
	public Double geoDist(final byte[] key, final byte[] member1, final byte[] member2) {
		final CommandArguments args = CommandArguments.create(key).add(member1).add(member2);
		return notCommand(client, Command.GEODIST, args);
	}

	@Override
	public Double geoDist(final String key, final String member1, final String member2, final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(key).add(member1).add(member2).add(unit);
		return notCommand(client, Command.GEODIST, args);
	}

	@Override
	public Double geoDist(final byte[] key, final byte[] member1, final byte[] member2, final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(key).add(member1).add(member2).add(unit);
		return notCommand(client, Command.GEODIST, args);
	}

	@Override
	public List<String> geoHash(final String key, final String... members) {
		final CommandArguments args = CommandArguments.create(key).add(members);
		return notCommand(client, Command.GEOHASH, args);
	}

	@Override
	public List<byte[]> geoHash(final byte[] key, final byte[]... members) {
		final CommandArguments args = CommandArguments.create(key).add(members);
		return notCommand(client, Command.GEOHASH, args);
	}

	@Override
	public List<Geo> geoPos(final String key, final String... members) {
		final CommandArguments args = CommandArguments.create(key).add(members);
		return notCommand(client, Command.GEOPOS, args);
	}

	@Override
	public List<Geo> geoPos(final byte[] key, final byte[]... members) {
		final CommandArguments args = CommandArguments.create(key).add(members);
		return notCommand(client, Command.GEOPOS, args);
	}

	@Override
	public List<GeoRadius> geoRadius(final String key, final double longitude, final double latitude,
									 final double radius, final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(key).add(longitude).add(latitude).add(radius).add(unit);
		return notCommand(client, Command.GEORADIUS, args);
	}

	@Override
	public List<GeoRadius> geoRadius(final byte[] key, final double longitude, final double latitude,
									 final double radius, final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(key).add(longitude).add(latitude).add(radius).add(unit);
		return notCommand(client, Command.GEORADIUS, args);
	}

	@Override
	public List<GeoRadius> geoRadius(final String key, final double longitude, final double latitude,
									 final double radius, final GeoUnit unit,
									 final GeoRadiusArgument geoRadiusArgument) {
		final CommandArguments args =
				CommandArguments.create(key).add(longitude).add(latitude).add(radius).add(unit).add(geoRadiusArgument);
		return notCommand(client, Command.GEORADIUS, args);
	}

	@Override
	public List<GeoRadius> geoRadius(final byte[] key, final double longitude, final double latitude,
									 final double radius, final GeoUnit unit,
									 final GeoRadiusArgument geoRadiusArgument) {
		final CommandArguments args =
				CommandArguments.create(key).add(longitude).add(latitude).add(radius).add(unit).add(geoRadiusArgument);
		return notCommand(client, Command.GEORADIUS, args);
	}

	@Override
	public List<GeoRadius> geoRadiusRo(final String key, final double longitude, final double latitude,
									   final double radius, final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(key).add(longitude).add(latitude).add(radius).add(unit);
		return notCommand(client, Command.GEORADIUS_RO, args);
	}

	@Override
	public List<GeoRadius> geoRadiusRo(final byte[] key, final double longitude, final double latitude,
									   final double radius, final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(key).add(longitude).add(latitude).add(radius).add(unit);
		return notCommand(client, Command.GEORADIUS_RO, args);
	}

	@Override
	public List<GeoRadius> geoRadiusRo(final String key, final double longitude, final double latitude,
									   final double radius, final GeoUnit unit,
									   final GeoRadiusArgument geoRadiusArgument) {
		final CommandArguments args =
				CommandArguments.create(key).add(longitude).add(latitude).add(radius).add(unit).add(geoRadiusArgument);
		return notCommand(client, Command.GEORADIUS_RO, args);
	}

	@Override
	public List<GeoRadius> geoRadiusRo(final byte[] key, final double longitude, final double latitude,
									   final double radius, final GeoUnit unit,
									   final GeoRadiusArgument geoRadiusArgument) {
		final CommandArguments args =
				CommandArguments.create(key).add(longitude).add(latitude).add(radius).add(unit).add(geoRadiusArgument);
		return notCommand(client, Command.GEORADIUS_RO, args);
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final String key, final String member, final double radius,
											 final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(key).add(member).add(radius).add(unit);
		return notCommand(client, Command.GEORADIUSBYMEMBER, args);
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final byte[] key, final byte[] member, final double radius,
											 final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(key).add(member).add(radius).add(unit);
		return notCommand(client, Command.GEORADIUSBYMEMBER, args);
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final String key, final String member, final double radius,
											 final GeoUnit unit, final GeoRadiusArgument geoRadiusArgument) {
		final CommandArguments args = CommandArguments.create(key).add(member).add(radius).add(unit)
				.add(geoRadiusArgument);
		return notCommand(client, Command.GEORADIUSBYMEMBER, args);
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final byte[] key, final byte[] member, final double radius,
											 final GeoUnit unit, final GeoRadiusArgument geoRadiusArgument) {
		final CommandArguments args = CommandArguments.create(key).add(member).add(radius).add(unit)
				.add(geoRadiusArgument);
		return notCommand(client, Command.GEORADIUSBYMEMBER, args);
	}

	@Override
	public List<GeoRadius> geoRadiusByMemberRo(final String key, final String member, final double radius,
											   final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(key).add(member).add(radius).add(unit);
		return notCommand(client, Command.GEORADIUSBYMEMBER_RO, args);
	}

	@Override
	public List<GeoRadius> geoRadiusByMemberRo(final byte[] key, final byte[] member, final double radius,
											   final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(key).add(member).add(radius).add(unit);
		return notCommand(client, Command.GEORADIUSBYMEMBER_RO, args);
	}

	@Override
	public List<GeoRadius> geoRadiusByMemberRo(final String key, final String member, final double radius,
											   final GeoUnit unit, final GeoRadiusArgument geoRadiusArgument) {
		final CommandArguments args = CommandArguments.create(key).add(member).add(radius).add(unit)
				.add(geoRadiusArgument);
		return notCommand(client, Command.GEORADIUSBYMEMBER_RO, args);
	}

	@Override
	public List<GeoRadius> geoRadiusByMemberRo(final byte[] key, final byte[] member, final double radius,
											   final GeoUnit unit, final GeoRadiusArgument geoRadiusArgument) {
		final CommandArguments args = CommandArguments.create(key).add(member).add(radius).add(unit)
				.add(geoRadiusArgument);
		return notCommand(client, Command.GEORADIUSBYMEMBER_RO, args);
	}

	@Override
	public List<GeoRadius> geoSearch(final String key, final GeoSearchArgument geoSearchArgument) {
		final CommandArguments args = CommandArguments.create(key).add(geoSearchArgument);
		return notCommand(client, Command.GEOSEARCH, args);
	}

	@Override
	public List<GeoRadius> geoSearch(final byte[] key, final GeoSearchArgument geoSearchArgument) {
		final CommandArguments args = CommandArguments.create(key).add(geoSearchArgument);
		return notCommand(client, Command.GEOSEARCH, args);
	}

	@Override
	public Long geoSearchStore(final String destKey, final String key,
							   final GeoSearchStoreArgument geoSearchStoreArgument) {
		final CommandArguments args = CommandArguments.create(destKey).add(key).add(geoSearchStoreArgument);
		return notCommand(client, Command.GEOSEARCHSTORE, args);
	}

	@Override
	public Long geoSearchStore(final byte[] destKey, final byte[] key,
							   final GeoSearchStoreArgument geoSearchStoreArgument) {
		final CommandArguments args = CommandArguments.create(destKey).add(key).add(geoSearchStoreArgument);
		return notCommand(client, Command.GEOSEARCHSTORE, args);
	}

}
