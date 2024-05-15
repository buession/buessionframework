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

import com.buession.core.builder.ListBuilder;
import com.buession.core.converter.ListConverter;
import com.buession.lang.Geo;
import com.buession.redis.client.lettuce.LettuceStandaloneClient;
import com.buession.redis.core.GeoRadius;
import com.buession.redis.core.GeoUnit;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.internal.convert.lettuce.params.GeoUnitConverter;
import com.buession.redis.core.internal.convert.lettuce.response.GeoCoordinateConverter;
import com.buession.redis.core.internal.convert.lettuce.response.GeoRadiusGeneralResultConverter;
import com.buession.redis.core.internal.convert.lettuce.response.GeoRadiusResponseConverter;
import com.buession.redis.core.internal.lettuce.LettuceGeoArgs;
import com.buession.redis.utils.SafeEncoder;
import io.lettuce.core.GeoArgs;
import io.lettuce.core.Value;

import java.util.List;
import java.util.Map;

/**
 * Lettuce 单机模式地理位置命令操作
 *
 * @author Yong.Teng
 * @since 2.4.0
 */
public final class LettuceGeoOperations extends AbstractGeoOperations<LettuceStandaloneClient> {

	public LettuceGeoOperations(final LettuceStandaloneClient client) {
		super(client);
	}

	@Override
	public Long geoAdd(final byte[] key, final byte[] member, final double longitude, final double latitude) {
		final CommandArguments args = CommandArguments.create("key", key).put("member", member)
				.put("longitude", longitude).put("latitude", latitude);
		return new LettuceCommand<>(client, ProtocolCommand.GEOADD, (cmd)->cmd.geoadd(key, longitude, latitude,
				member), (value)->value)
				.run(args);
	}

	@Override
	public Long geoAdd(final String key, final Map<String, Geo> memberCoordinates) {
		final CommandArguments args = CommandArguments.create("key", key).put("memberCoordinates", memberCoordinates);
		final ListBuilder<Object> lngLatMemberBuilder = ListBuilder.create(memberCoordinates == null ? 0 :
				memberCoordinates.size() * 3);

		if(memberCoordinates != null){
			memberCoordinates.forEach((k, v)->{
				lngLatMemberBuilder.
						add(v.getLongitude()).
						add(v.getLatitude()).
						add(SafeEncoder.encode(k));
			});
		}

		return new LettuceCommand<>(client, ProtocolCommand.GEOADD,
				(cmd)->cmd.geoadd(SafeEncoder.encode(key), lngLatMemberBuilder.build().toArray(new Object[]{})), (v)->v)
				.run(args);
	}

	@Override
	public Long geoAdd(final byte[] key, final Map<byte[], Geo> memberCoordinates) {
		final CommandArguments args = CommandArguments.create("key", key).put("memberCoordinates", memberCoordinates);
		final ListBuilder<Object> lngLatMemberBuilder = ListBuilder.create(memberCoordinates == null ? 0 :
				memberCoordinates.size() * 3);

		if(memberCoordinates != null){
			memberCoordinates.forEach((k, v)->{
				lngLatMemberBuilder.
						add(v.getLongitude()).
						add(v.getLatitude()).
						add(k);
			});
		}

		return new LettuceCommand<>(client, ProtocolCommand.GEOADD,
				(cmd)->cmd.geoadd(key, lngLatMemberBuilder.build().toArray(new Object[]{})), (v)->v)
				.run(args);
	}

	@Override
	public List<String> geoHash(final String key, final String... members) {
		final CommandArguments args = CommandArguments.create("key", key).put("members", (Object[]) members);
		return new LettuceCommand<>(client, ProtocolCommand.GEOHASH, (cmd)->cmd.geohash(SafeEncoder.encode(key),
				SafeEncoder.encode(members)), new ListConverter<>(Value::getValue))
				.run(args);
	}

	@Override
	public List<byte[]> geoHash(final byte[] key, final byte[]... members) {
		final CommandArguments args = CommandArguments.create("key", key).put("members", (Object[]) members);
		return new LettuceCommand<>(client, ProtocolCommand.GEOHASH, (cmd)->cmd.geohash(key, members),
				new ListConverter<>((v)->SafeEncoder.encode(v.getValue())))
				.run(args);
	}

	@Override
	public List<Geo> geoPos(final byte[] key, final byte[]... members) {
		final CommandArguments args = CommandArguments.create("key", key).put("members", (Object[]) members);
		return new LettuceCommand<>(client, ProtocolCommand.GEOPOS, (cmd)->cmd.geopos(key, members),
				GeoCoordinateConverter.LIST_CONVERTER)
				.run(args);
	}

	@Override
	public Double geoDist(final byte[] key, final byte[] member1, final byte[] member2) {
		final CommandArguments args = CommandArguments.create("key", key).put("member1", member1)
				.put("member2", member2);
		return new LettuceCommand<>(client, ProtocolCommand.GEODIST, (cmd)->cmd.geodist(key, member1, member2,
				GeoArgs.Unit.m), (value)->value)
				.run(args);
	}

	@Override
	public Double geoDist(final byte[] key, final byte[] member1, final byte[] member2, final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create("key", key).put("member1", member1)
				.put("member2", member2).put("unit", unit);
		return new LettuceCommand<>(client, ProtocolCommand.GEODIST, (cmd)->cmd.geodist(key, member1, member2,
				GeoUnitConverter.INSTANCE.convert(unit)), (value)->value)
				.run(args);
	}

	@Override
	public List<GeoRadius> geoRadius(final byte[] key, final double longitude, final double latitude,
									 final double radius, final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create("key", key).put("longitude", longitude)
				.put("latitude", latitude).put("radius", radius).put("unit", unit);
		return new LettuceCommand<>(client, ProtocolCommand.GEORADIUS, (cmd)->cmd.georadius(key, longitude, latitude,
				radius, GeoUnitConverter.INSTANCE.convert(unit)), GeoRadiusGeneralResultConverter.LIST_CONVERTER)
				.run(args);
	}

	@Override
	public List<GeoRadius> geoRadius(final byte[] key, final double longitude, final double latitude,
									 final double radius, final GeoUnit unit,
									 final GeoRadiusArgument geoRadiusArgument) {
		final CommandArguments args = CommandArguments.create("key", key).put("longitude", longitude)
				.put("latitude", latitude).put("radius", radius).put("unit", unit)
				.put("geoRadiusArgument", geoRadiusArgument);
		return new LettuceCommand<>(client, ProtocolCommand.GEORADIUS, (cmd)->cmd.georadius(key, longitude, latitude,
				radius, GeoUnitConverter.INSTANCE.convert(unit), LettuceGeoArgs.from(geoRadiusArgument)),
				GeoRadiusResponseConverter.LIST_CONVERTER)
				.run(args);
	}

	@Override
	public List<GeoRadius> geoRadiusRo(final byte[] key, final double longitude, final double latitude,
									   final double radius, final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create("key", key).put("longitude", longitude)
				.put("latitude", latitude).put("radius", radius).put("unit", unit);
		return new LettuceCommand<List<GeoRadius>, List<GeoRadius>>(client, ProtocolCommand.GEORADIUS_RO)
				.run(args);
	}

	@Override
	public List<GeoRadius> geoRadiusRo(final byte[] key, final double longitude, final double latitude,
									   final double radius, final GeoUnit unit,
									   final GeoRadiusArgument geoRadiusArgument) {
		final CommandArguments args = CommandArguments.create("key", key).put("longitude", longitude)
				.put("latitude", latitude).put("radius", radius).put("unit", unit)
				.put("geoRadiusArgument", geoRadiusArgument);
		return new LettuceCommand<List<GeoRadius>, List<GeoRadius>>(client, ProtocolCommand.GEORADIUS_RO)
				.run(args);
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final byte[] key, final byte[] member, final double radius,
											 final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create("key", key).put("member", member).put("radius", radius)
				.put("unit", unit);
		return new LettuceCommand<>(client, ProtocolCommand.GEORADIUSBYMEMBER,
				(cmd)->cmd.georadiusbymember(key, member, radius, GeoUnitConverter.INSTANCE.convert(unit)),
				GeoRadiusGeneralResultConverter.LIST_CONVERTER)
				.run(args);
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final byte[] key, final byte[] member, final double radius,
											 final GeoUnit unit, final GeoRadiusArgument geoRadiusArgument) {
		final CommandArguments args = CommandArguments.create("key", key).put("member", member).put("radius", radius)
				.put("unit", unit).put("geoRadiusArgument", geoRadiusArgument);
		return new LettuceCommand<>(client, ProtocolCommand.GEORADIUSBYMEMBER,
				(cmd)->cmd.georadiusbymember(key, member, radius, GeoUnitConverter.INSTANCE.convert(unit),
						LettuceGeoArgs.from(geoRadiusArgument)), GeoRadiusResponseConverter.LIST_CONVERTER)
				.run(args);
	}

	@Override
	public List<GeoRadius> geoRadiusByMemberRo(final byte[] key, final byte[] member, final double radius,
											   final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create("key", key).put("member", member).put("radius", radius)
				.put("unit", unit);
		return new LettuceCommand<List<GeoRadius>, List<GeoRadius>>(client, ProtocolCommand.GEORADIUSBYMEMBER_RO)
				.run(args);
	}

	@Override
	public List<GeoRadius> geoRadiusByMemberRo(final byte[] key, final byte[] member, final double radius,
											   final GeoUnit unit, final GeoRadiusArgument geoRadiusArgument) {
		final CommandArguments args = CommandArguments.create("key", key).put("member", member).put("radius", radius)
				.put("unit", unit).put("geoRadiusArgument", geoRadiusArgument);
		return new LettuceCommand<List<GeoRadius>, List<GeoRadius>>(client, ProtocolCommand.GEORADIUSBYMEMBER_RO)
				.run(args);
	}

}
