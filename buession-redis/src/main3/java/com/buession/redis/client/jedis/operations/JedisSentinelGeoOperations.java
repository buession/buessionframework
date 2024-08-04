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
package com.buession.redis.client.jedis.operations;

import com.buession.core.builder.MapBuilder;
import com.buession.core.converter.ListConverter;
import com.buession.lang.Geo;
import com.buession.redis.client.jedis.JedisSentinelClient;
import com.buession.redis.core.GeoRadius;
import com.buession.redis.core.GeoUnit;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.Command;
import com.buession.redis.core.command.args.GeoAddArgument;
import com.buession.redis.core.command.args.GeoRadiusArgument;
import com.buession.redis.core.command.args.GeoSearchArgument;
import com.buession.redis.core.command.args.GeoSearchStoreArgument;
import com.buession.redis.core.internal.convert.jedis.params.GeoUnitConverter;
import com.buession.redis.core.internal.convert.jedis.response.GeoCoordinateConverter;
import com.buession.redis.core.internal.convert.jedis.response.GeoRadiusResponseConverter;
import com.buession.redis.core.internal.jedis.JedisGeoAddParam;
import com.buession.redis.core.internal.jedis.JedisGeoRadiusParam;
import com.buession.redis.core.internal.jedis.JedisGeoSearchParam;
import com.buession.redis.core.internal.jedis.JedisGeoSearchStoreParam;
import redis.clients.jedis.GeoCoordinate;
import redis.clients.jedis.params.GeoAddParams;
import redis.clients.jedis.params.GeoRadiusParam;
import redis.clients.jedis.params.GeoSearchParam;
import redis.clients.jedis.resps.GeoRadiusResponse;

import java.util.List;
import java.util.Map;

/**
 * Jedis 哨兵模式地理位置命令操作
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public final class JedisSentinelGeoOperations extends AbstractGeoOperations<JedisSentinelClient> {

	public JedisSentinelGeoOperations(final JedisSentinelClient client) {
		super(client);
	}

	@Override
	public Long geoAdd(final String key, final String member, final double longitude, final double latitude) {
		final CommandArguments args = CommandArguments.create(key).add(longitude).add(latitude).add(member);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.GEOADD,
					(cmd)->cmd.geoadd(key, longitude, latitude, member), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.GEOADD,
					(cmd)->cmd.geoadd(key, longitude, latitude, member), (v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.GEOADD,
					(cmd)->cmd.geoadd(key, longitude, latitude, member), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long geoAdd(final byte[] key, final byte[] member, final double longitude, final double latitude) {
		final CommandArguments args = CommandArguments.create(key).add(longitude).add(latitude).add(member);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.GEOADD,
					(cmd)->cmd.geoadd(key, longitude, latitude, member), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.GEOADD,
					(cmd)->cmd.geoadd(key, longitude, latitude, member), (v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.GEOADD,
					(cmd)->cmd.geoadd(key, longitude, latitude, member), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long geoAdd(final String key, final Map<String, Geo> memberCoordinates) {
		final CommandArguments args = CommandArguments.create(key).add(memberCoordinates);
		final Map<String, GeoCoordinate> geoCoordinates = createGeoCoordinateMapFromGeoMap(memberCoordinates);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.GEOADD, (cmd)->cmd.geoadd(key, geoCoordinates),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.GEOADD, (cmd)->cmd.geoadd(key, geoCoordinates),
					(v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.GEOADD, (cmd)->cmd.geoadd(key, geoCoordinates), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long geoAdd(final byte[] key, final Map<byte[], Geo> memberCoordinates) {
		final CommandArguments args = CommandArguments.create(key).add(memberCoordinates);
		final Map<byte[], GeoCoordinate> geoCoordinates = createGeoCoordinateMapFromGeoMap(memberCoordinates);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.GEOADD, (cmd)->cmd.geoadd(key, geoCoordinates),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.GEOADD, (cmd)->cmd.geoadd(key, geoCoordinates),
					(v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.GEOADD, (cmd)->cmd.geoadd(key, geoCoordinates), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long geoAdd(final String key, final String member, final double longitude, final double latitude,
					   final GeoAddArgument geoAddArgument) {
		final CommandArguments args = CommandArguments.create(key).add(geoAddArgument).add(longitude).add(latitude)
				.add(member);
		final Map<String, Geo> memberCoordinates =
				MapBuilder.<String, Geo>create(1).put(member, new Geo(longitude, latitude)).build();

		return geoAdd(key, memberCoordinates, geoAddArgument, args);
	}

	@Override
	public Long geoAdd(final byte[] key, final byte[] member, final double longitude, final double latitude,
					   final GeoAddArgument geoAddArgument) {
		final CommandArguments args = CommandArguments.create(key).add(geoAddArgument).add(longitude).add(latitude)
				.add(member);
		final Map<byte[], Geo> memberCoordinates =
				MapBuilder.<byte[], Geo>create(1).put(member, new Geo(longitude, latitude)).build();

		return geoAdd(key, memberCoordinates, geoAddArgument, args);
	}

	@Override
	public Long geoAdd(final String key, final Map<String, Geo> memberCoordinates,
					   final GeoAddArgument geoAddArgument) {
		final CommandArguments args = CommandArguments.create(key).add(memberCoordinates);
		return geoAdd(key, memberCoordinates, geoAddArgument, args);
	}

	@Override
	public Long geoAdd(final byte[] key, final Map<byte[], Geo> memberCoordinates,
					   final GeoAddArgument geoAddArgument) {
		final CommandArguments args = CommandArguments.create(key).add(geoAddArgument).add(memberCoordinates);
		return geoAdd(key, memberCoordinates, geoAddArgument, args);
	}

	@Override
	public Double geoDist(final String key, final String member1, final String member2) {
		final CommandArguments args = CommandArguments.create(key).add(member1).add(member2);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.GEODIST,
					(cmd)->cmd.geodist(key, member1, member2), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.GEODIST,
					(cmd)->cmd.geodist(key, member1, member2), (v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.GEODIST, (cmd)->cmd.geodist(key, member1, member2),
					(v)->v)
					.run(args);
		}
	}

	@Override
	public Double geoDist(final byte[] key, final byte[] member1, final byte[] member2) {
		final CommandArguments args = CommandArguments.create(key).add(member1).add(member2);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.GEODIST,
					(cmd)->cmd.geodist(key, member1, member2), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.GEODIST,
					(cmd)->cmd.geodist(key, member1, member2), (v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.GEODIST, (cmd)->cmd.geodist(key, member1, member2),
					(v)->v)
					.run(args);
		}
	}

	@Override
	public Double geoDist(final String key, final String member1, final String member2, final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(key).add(member1).add(member2).add(unit);
		final redis.clients.jedis.args.GeoUnit geoUnit = (new GeoUnitConverter()).convert(unit);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.GEODIST,
					(cmd)->cmd.geodist(key, member1, member2, geoUnit), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.GEODIST,
					(cmd)->cmd.geodist(key, member1, member2, geoUnit), (v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.GEODIST,
					(cmd)->cmd.geodist(key, member1, member2, geoUnit), (v)->v)
					.run(args);
		}
	}

	@Override
	public Double geoDist(final byte[] key, final byte[] member1, final byte[] member2, final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(key).add(member1).add(member2).add(unit);
		final redis.clients.jedis.args.GeoUnit geoUnit = (new GeoUnitConverter()).convert(unit);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.GEODIST,
					(cmd)->cmd.geodist(key, member1, member2, geoUnit), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.GEODIST,
					(cmd)->cmd.geodist(key, member1, member2, geoUnit), (v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.GEODIST,
					(cmd)->cmd.geodist(key, member1, member2, geoUnit), (v)->v)
					.run(args);
		}
	}

	@Override
	public List<String> geoHash(final String key, final String... members) {
		final CommandArguments args = CommandArguments.create(key).add(members);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.GEOHASH, (cmd)->cmd.geohash(key, members),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.GEOHASH, (cmd)->cmd.geohash(key, members),
					(v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.GEOHASH, (cmd)->cmd.geohash(key, members), (v)->v)
					.run(args);
		}
	}

	@Override
	public List<byte[]> geoHash(final byte[] key, final byte[]... members) {
		final CommandArguments args = CommandArguments.create(key).add(members);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.GEOHASH, (cmd)->cmd.geohash(key, members), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.GEOHASH, (cmd)->cmd.geohash(key, members),
					(v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.GEOHASH, (cmd)->cmd.geohash(key, members), (v)->v)
					.run(args);
		}
	}

	@Override
	public List<Geo> geoPos(final String key, final String... members) {
		final CommandArguments args = CommandArguments.create(key).add(members);
		final ListConverter<GeoCoordinate, Geo> listGeoCoordinateConverter = GeoCoordinateConverter.listConverter();

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.GEOPOS, (cmd)->cmd.geopos(key, members),
					listGeoCoordinateConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.GEOPOS,
					(cmd)->cmd.geopos(key, members), listGeoCoordinateConverter)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.GEOPOS, (cmd)->cmd.geopos(key, members),
					listGeoCoordinateConverter)
					.run(args);
		}
	}

	@Override
	public List<Geo> geoPos(final byte[] key, final byte[]... members) {
		final CommandArguments args = CommandArguments.create(key).add(members);
		final ListConverter<GeoCoordinate, Geo> listGeoCoordinateConverter = GeoCoordinateConverter.listConverter();

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.GEOPOS, (cmd)->cmd.geopos(key, members),
					listGeoCoordinateConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.GEOPOS,
					(cmd)->cmd.geopos(key, members), listGeoCoordinateConverter)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.GEOPOS, (cmd)->cmd.geopos(key, members),
					listGeoCoordinateConverter)
					.run(args);
		}
	}

	@Override
	public List<GeoRadius> geoRadius(final String key, final double longitude, final double latitude,
									 final double radius, final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(key).add(longitude).add(latitude).add(radius).add(unit);
		final redis.clients.jedis.args.GeoUnit geoUnit = (new GeoUnitConverter()).convert(unit);
		final ListConverter<GeoRadiusResponse, GeoRadius> listGeoRadiusResponseConverter =
				GeoRadiusResponseConverter.listConverter();

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.GEORADIUS,
					(cmd)->cmd.georadius(key, longitude, latitude, radius, geoUnit), listGeoRadiusResponseConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.GEORADIUS,
					(cmd)->cmd.georadius(key, longitude, latitude, radius, geoUnit), listGeoRadiusResponseConverter)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.GEORADIUS,
					(cmd)->cmd.georadius(key, longitude, latitude, radius, geoUnit), listGeoRadiusResponseConverter)
					.run(args);
		}
	}

	@Override
	public List<GeoRadius> geoRadius(final byte[] key, final double longitude, final double latitude,
									 final double radius, final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(key).add(longitude).add(latitude).add(radius).add(unit);
		final redis.clients.jedis.args.GeoUnit geoUnit = (new GeoUnitConverter()).convert(unit);
		final ListConverter<GeoRadiusResponse, GeoRadius> listGeoRadiusResponseConverter =
				GeoRadiusResponseConverter.listConverter();

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.GEORADIUS,
					(cmd)->cmd.georadius(key, longitude, latitude, radius, geoUnit), listGeoRadiusResponseConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.GEORADIUS,
					(cmd)->cmd.georadius(key, longitude, latitude, radius, geoUnit), listGeoRadiusResponseConverter)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.GEORADIUS,
					(cmd)->cmd.georadius(key, longitude, latitude, radius, geoUnit), listGeoRadiusResponseConverter)
					.run(args);
		}
	}

	@Override
	public List<GeoRadius> geoRadius(final String key, final double longitude, final double latitude,
									 final double radius, final GeoUnit unit,
									 final GeoRadiusArgument geoRadiusArgument) {
		final CommandArguments args = CommandArguments.create(key).add(longitude).add(latitude).add(radius).add(unit)
				.add(geoRadiusArgument);
		final redis.clients.jedis.args.GeoUnit geoUnit = (new GeoUnitConverter()).convert(unit);
		final GeoRadiusParam geoRadiusParam = JedisGeoRadiusParam.from(geoRadiusArgument);
		final ListConverter<GeoRadiusResponse, GeoRadius> listGeoRadiusResponseConverter =
				GeoRadiusResponseConverter.listConverter();

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.GEORADIUS,
					(cmd)->cmd.georadius(key, longitude, latitude, radius, geoUnit, geoRadiusParam),
					listGeoRadiusResponseConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.GEORADIUS,
					(cmd)->cmd.georadius(key, longitude, latitude, radius, geoUnit, geoRadiusParam),
					listGeoRadiusResponseConverter)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.GEORADIUS,
					(cmd)->cmd.georadius(key, longitude, latitude, radius, geoUnit, geoRadiusParam),
					listGeoRadiusResponseConverter)
					.run(args);
		}
	}

	@Override
	public List<GeoRadius> geoRadius(final byte[] key, final double longitude, final double latitude,
									 final double radius, final GeoUnit unit,
									 final GeoRadiusArgument geoRadiusArgument) {
		final CommandArguments args = CommandArguments.create(key).add(longitude).add(latitude).add(radius).add(unit)
				.add(geoRadiusArgument);
		final redis.clients.jedis.args.GeoUnit geoUnit = (new GeoUnitConverter()).convert(unit);
		final GeoRadiusParam geoRadiusParam = JedisGeoRadiusParam.from(geoRadiusArgument);
		final ListConverter<GeoRadiusResponse, GeoRadius> listGeoRadiusResponseConverter =
				GeoRadiusResponseConverter.listConverter();

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.GEORADIUS,
					(cmd)->cmd.georadius(key, longitude, latitude, radius, geoUnit, geoRadiusParam),
					listGeoRadiusResponseConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.GEORADIUS,
					(cmd)->cmd.georadius(key, longitude, latitude, radius, geoUnit, geoRadiusParam),
					listGeoRadiusResponseConverter)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.GEORADIUS,
					(cmd)->cmd.georadius(key, longitude, latitude, radius, geoUnit, geoRadiusParam),
					listGeoRadiusResponseConverter)
					.run(args);
		}
	}

	@Override
	public List<GeoRadius> geoRadiusRo(final String key, final double longitude, final double latitude,
									   final double radius, final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(key).add(longitude).add(latitude).add(radius).add(unit);
		final redis.clients.jedis.args.GeoUnit geoUnit = (new GeoUnitConverter()).convert(unit);
		final ListConverter<GeoRadiusResponse, GeoRadius> listGeoRadiusResponseConverter =
				GeoRadiusResponseConverter.listConverter();

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.GEORADIUS_RO,
					(cmd)->cmd.georadiusReadonly(key, longitude, latitude, radius, geoUnit),
					listGeoRadiusResponseConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.GEORADIUS_RO,
					(cmd)->cmd.georadiusReadonly(key, longitude, latitude, radius, geoUnit),
					listGeoRadiusResponseConverter)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.GEORADIUS_RO,
					(cmd)->cmd.georadiusReadonly(key, longitude, latitude, radius, geoUnit),
					listGeoRadiusResponseConverter)
					.run(args);
		}
	}

	@Override
	public List<GeoRadius> geoRadiusRo(final byte[] key, final double longitude, final double latitude,
									   final double radius, final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(key).add(longitude).add(latitude).add(radius).add(unit);
		final redis.clients.jedis.args.GeoUnit geoUnit = (new GeoUnitConverter()).convert(unit);
		final ListConverter<GeoRadiusResponse, GeoRadius> listGeoRadiusResponseConverter =
				GeoRadiusResponseConverter.listConverter();

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.GEORADIUS_RO,
					(cmd)->cmd.georadiusReadonly(key, longitude, latitude, radius, geoUnit),
					listGeoRadiusResponseConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.GEORADIUS_RO,
					(cmd)->cmd.georadiusReadonly(key, longitude, latitude, radius, geoUnit),
					listGeoRadiusResponseConverter)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.GEORADIUS_RO,
					(cmd)->cmd.georadiusReadonly(key, longitude, latitude, radius, geoUnit),
					listGeoRadiusResponseConverter)
					.run(args);
		}
	}

	@Override
	public List<GeoRadius> geoRadiusRo(final String key, final double longitude, final double latitude,
									   final double radius, final GeoUnit unit,
									   final GeoRadiusArgument geoRadiusArgument) {
		final CommandArguments args =
				CommandArguments.create(key).add(longitude).add(latitude).add(radius).add(unit).add(geoRadiusArgument);
		final redis.clients.jedis.args.GeoUnit geoUnit = (new GeoUnitConverter()).convert(unit);
		final GeoRadiusParam geoRadiusParam = JedisGeoRadiusParam.from(geoRadiusArgument);
		final ListConverter<GeoRadiusResponse, GeoRadius> listGeoRadiusResponseConverter =
				GeoRadiusResponseConverter.listConverter();

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.GEORADIUS_RO,
					(cmd)->cmd.georadiusReadonly(key, longitude, latitude, radius, geoUnit, geoRadiusParam),
					listGeoRadiusResponseConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.GEORADIUS_RO,
					(cmd)->cmd.georadiusReadonly(key, longitude, latitude, radius, geoUnit, geoRadiusParam),
					listGeoRadiusResponseConverter)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.GEORADIUS_RO,
					(cmd)->cmd.georadiusReadonly(key, longitude, latitude, radius, geoUnit, geoRadiusParam),
					listGeoRadiusResponseConverter)
					.run(args);
		}
	}

	@Override
	public List<GeoRadius> geoRadiusRo(final byte[] key, final double longitude, final double latitude,
									   final double radius, final GeoUnit unit,
									   final GeoRadiusArgument geoRadiusArgument) {
		final CommandArguments args =
				CommandArguments.create(key).add(longitude).add(latitude).add(radius).add(unit).add(geoRadiusArgument);
		final redis.clients.jedis.args.GeoUnit geoUnit = (new GeoUnitConverter()).convert(unit);
		final GeoRadiusParam geoRadiusParam = JedisGeoRadiusParam.from(geoRadiusArgument);
		final ListConverter<GeoRadiusResponse, GeoRadius> listGeoRadiusResponseConverter =
				GeoRadiusResponseConverter.listConverter();

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.GEORADIUS_RO,
					(cmd)->cmd.georadiusReadonly(key, longitude, latitude, radius, geoUnit, geoRadiusParam),
					listGeoRadiusResponseConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.GEORADIUS_RO,
					(cmd)->cmd.georadiusReadonly(key, longitude, latitude, radius, geoUnit, geoRadiusParam),
					listGeoRadiusResponseConverter)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.GEORADIUS_RO,
					(cmd)->cmd.georadiusReadonly(key, longitude, latitude, radius, geoUnit, geoRadiusParam),
					listGeoRadiusResponseConverter)
					.run(args);
		}
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final String key, final String member, final double radius,
											 final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(key).add(member).add(radius).add(unit);
		final redis.clients.jedis.args.GeoUnit geoUnit = (new GeoUnitConverter()).convert(unit);
		final ListConverter<GeoRadiusResponse, GeoRadius> listGeoRadiusResponseConverter =
				GeoRadiusResponseConverter.listConverter();

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.GEORADIUSBYMEMBER,
					(cmd)->cmd.georadiusByMember(key, member, radius, geoUnit), listGeoRadiusResponseConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.GEORADIUSBYMEMBER,
					(cmd)->cmd.georadiusByMember(key, member, radius, geoUnit), listGeoRadiusResponseConverter)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.GEORADIUSBYMEMBER,
					(cmd)->cmd.georadiusByMember(key, member, radius, geoUnit), listGeoRadiusResponseConverter)
					.run(args);
		}
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final byte[] key, final byte[] member, final double radius,
											 final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(key).add(member).add(radius).add(unit);
		final redis.clients.jedis.args.GeoUnit geoUnit = (new GeoUnitConverter()).convert(unit);
		final ListConverter<GeoRadiusResponse, GeoRadius> listGeoRadiusResponseConverter =
				GeoRadiusResponseConverter.listConverter();

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.GEORADIUSBYMEMBER,
					(cmd)->cmd.georadiusByMember(key, member, radius, geoUnit), listGeoRadiusResponseConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.GEORADIUSBYMEMBER,
					(cmd)->cmd.georadiusByMember(key, member, radius, geoUnit), listGeoRadiusResponseConverter)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.GEORADIUSBYMEMBER,
					(cmd)->cmd.georadiusByMember(key, member, radius, geoUnit), listGeoRadiusResponseConverter)
					.run(args);
		}
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final String key, final String member, final double radius,
											 final GeoUnit unit, final GeoRadiusArgument geoRadiusArgument) {
		final CommandArguments args = CommandArguments.create(key).add(member).add(radius).add(unit)
				.add(geoRadiusArgument);
		final redis.clients.jedis.args.GeoUnit geoUnit = (new GeoUnitConverter()).convert(unit);
		final GeoRadiusParam geoRadiusParam = JedisGeoRadiusParam.from(geoRadiusArgument);
		final ListConverter<GeoRadiusResponse, GeoRadius> listGeoRadiusResponseConverter =
				GeoRadiusResponseConverter.listConverter();

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.GEORADIUSBYMEMBER,
					(cmd)->cmd.georadiusByMember(key, member, radius, geoUnit, geoRadiusParam),
					listGeoRadiusResponseConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.GEORADIUSBYMEMBER,
					(cmd)->cmd.georadiusByMember(key, member, radius, geoUnit, geoRadiusParam),
					listGeoRadiusResponseConverter)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.GEORADIUSBYMEMBER,
					(cmd)->cmd.georadiusByMember(key, member, radius, geoUnit, geoRadiusParam),
					listGeoRadiusResponseConverter)
					.run(args);
		}
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final byte[] key, final byte[] member, final double radius,
											 final GeoUnit unit, final GeoRadiusArgument geoRadiusArgument) {
		final CommandArguments args = CommandArguments.create(key).add(member).add(radius).add(unit)
				.add(geoRadiusArgument);
		final redis.clients.jedis.args.GeoUnit geoUnit = (new GeoUnitConverter()).convert(unit);
		final GeoRadiusParam geoRadiusParam = JedisGeoRadiusParam.from(geoRadiusArgument);
		final ListConverter<GeoRadiusResponse, GeoRadius> listGeoRadiusResponseConverter =
				GeoRadiusResponseConverter.listConverter();

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.GEORADIUSBYMEMBER,
					(cmd)->cmd.georadiusByMember(key, member, radius, geoUnit, geoRadiusParam),
					listGeoRadiusResponseConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.GEORADIUSBYMEMBER,
					(cmd)->cmd.georadiusByMember(key, member, radius, geoUnit, geoRadiusParam),
					listGeoRadiusResponseConverter)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.GEORADIUSBYMEMBER,
					(cmd)->cmd.georadiusByMember(key, member, radius, geoUnit, geoRadiusParam),
					listGeoRadiusResponseConverter)
					.run(args);
		}
	}

	@Override
	public List<GeoRadius> geoRadiusByMemberRo(final String key, final String member, final double radius,
											   final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(key).add(member).add(radius).add(unit);
		final redis.clients.jedis.args.GeoUnit geoUnit = (new GeoUnitConverter()).convert(unit);
		final ListConverter<GeoRadiusResponse, GeoRadius> listGeoRadiusResponseConverter =
				GeoRadiusResponseConverter.listConverter();

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.GEORADIUSBYMEMBER_RO,
					(cmd)->cmd.georadiusByMemberReadonly(key, member, radius, geoUnit), listGeoRadiusResponseConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.GEORADIUSBYMEMBER_RO,
					(cmd)->cmd.georadiusByMemberReadonly(key, member, radius, geoUnit), listGeoRadiusResponseConverter)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.GEORADIUSBYMEMBER_RO,
					(cmd)->cmd.georadiusByMemberReadonly(key, member, radius, geoUnit), listGeoRadiusResponseConverter)
					.run(args);
		}
	}

	@Override
	public List<GeoRadius> geoRadiusByMemberRo(final byte[] key, final byte[] member, final double radius,
											   final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(key).add(member).add(radius).add(unit);
		final redis.clients.jedis.args.GeoUnit geoUnit = (new GeoUnitConverter()).convert(unit);
		final ListConverter<GeoRadiusResponse, GeoRadius> listGeoRadiusResponseConverter =
				GeoRadiusResponseConverter.listConverter();

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.GEORADIUSBYMEMBER_RO,
					(cmd)->cmd.georadiusByMemberReadonly(key, member, radius, geoUnit), listGeoRadiusResponseConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.GEORADIUSBYMEMBER_RO,
					(cmd)->cmd.georadiusByMemberReadonly(key, member, radius, geoUnit), listGeoRadiusResponseConverter)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.GEORADIUSBYMEMBER_RO,
					(cmd)->cmd.georadiusByMemberReadonly(key, member, radius, geoUnit), listGeoRadiusResponseConverter)
					.run(args);
		}
	}

	@Override
	public List<GeoRadius> geoRadiusByMemberRo(final String key, final String member, final double radius,
											   final GeoUnit unit, final GeoRadiusArgument geoRadiusArgument) {
		final CommandArguments args = CommandArguments.create(key).add(member).add(radius).add(unit)
				.add(geoRadiusArgument);
		final redis.clients.jedis.args.GeoUnit geoUnit = (new GeoUnitConverter()).convert(unit);
		final GeoRadiusParam geoRadiusParam = JedisGeoRadiusParam.from(geoRadiusArgument);
		final ListConverter<GeoRadiusResponse, GeoRadius> listGeoRadiusResponseConverter =
				GeoRadiusResponseConverter.listConverter();

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.GEORADIUSBYMEMBER_RO,
					(cmd)->cmd.georadiusByMemberReadonly(key, member, radius, geoUnit, geoRadiusParam),
					listGeoRadiusResponseConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.GEORADIUSBYMEMBER_RO,
					(cmd)->cmd.georadiusByMemberReadonly(key, member, radius, geoUnit, geoRadiusParam),
					listGeoRadiusResponseConverter)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.GEORADIUSBYMEMBER_RO,
					(cmd)->cmd.georadiusByMemberReadonly(key, member, radius, geoUnit, geoRadiusParam),
					listGeoRadiusResponseConverter)
					.run(args);
		}
	}

	@Override
	public List<GeoRadius> geoRadiusByMemberRo(final byte[] key, final byte[] member, final double radius,
											   final GeoUnit unit, final GeoRadiusArgument geoRadiusArgument) {
		final CommandArguments args = CommandArguments.create(key).add(member).add(radius).add(unit)
				.add(geoRadiusArgument);
		final redis.clients.jedis.args.GeoUnit geoUnit = (new GeoUnitConverter()).convert(unit);
		final GeoRadiusParam geoRadiusParam = JedisGeoRadiusParam.from(geoRadiusArgument);
		final ListConverter<GeoRadiusResponse, GeoRadius> listGeoRadiusResponseConverter =
				GeoRadiusResponseConverter.listConverter();

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.GEORADIUSBYMEMBER_RO,
					(cmd)->cmd.georadiusByMemberReadonly(key, member, radius, geoUnit, geoRadiusParam),
					listGeoRadiusResponseConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.GEORADIUSBYMEMBER_RO,
					(cmd)->cmd.georadiusByMemberReadonly(key, member, radius, geoUnit, geoRadiusParam),
					listGeoRadiusResponseConverter)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.GEORADIUSBYMEMBER_RO,
					(cmd)->cmd.georadiusByMemberReadonly(key, member, radius, geoUnit, geoRadiusParam),
					listGeoRadiusResponseConverter)
					.run(args);
		}
	}

	@Override
	public List<GeoRadius> geoSearch(final String key, final GeoSearchArgument geoSearchArgument) {
		final CommandArguments args = CommandArguments.create(key).add(geoSearchArgument);
		final GeoSearchParam geoSearchParam = JedisGeoSearchParam.from(geoSearchArgument);
		final ListConverter<GeoRadiusResponse, GeoRadius> listGeoRadiusResponseConverter =
				GeoRadiusResponseConverter.listConverter();

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.GEOSEARCH,
					(cmd)->cmd.geosearch(key, geoSearchParam), listGeoRadiusResponseConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.GEOSEARCH,
					(cmd)->cmd.geosearch(key, geoSearchParam), listGeoRadiusResponseConverter)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.GEOSEARCH, (cmd)->cmd.geosearch(key, geoSearchParam),
					listGeoRadiusResponseConverter)
					.run(args);
		}
	}

	@Override
	public List<GeoRadius> geoSearch(final byte[] key, final GeoSearchArgument geoSearchArgument) {
		final CommandArguments args = CommandArguments.create(key).add(geoSearchArgument);
		final GeoSearchParam geoSearchParam = JedisGeoSearchParam.from(geoSearchArgument);
		final ListConverter<GeoRadiusResponse, GeoRadius> listGeoRadiusResponseConverter =
				GeoRadiusResponseConverter.listConverter();

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.GEOSEARCH,
					(cmd)->cmd.geosearch(key, geoSearchParam), listGeoRadiusResponseConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.GEOSEARCH,
					(cmd)->cmd.geosearch(key, geoSearchParam), listGeoRadiusResponseConverter)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.GEOSEARCH, (cmd)->cmd.geosearch(key, geoSearchParam),
					listGeoRadiusResponseConverter)
					.run(args);
		}
	}

	@Override
	public Long geoSearchStore(final String destKey, final String key,
							   final GeoSearchStoreArgument geoSearchStoreArgument) {
		final CommandArguments args = CommandArguments.create(destKey).add(key).add(geoSearchStoreArgument);
		final GeoSearchParam geoSearchParam = JedisGeoSearchStoreParam.from(geoSearchStoreArgument);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.GEOSEARCHSTORE,
					(cmd)->cmd.geosearchStore(destKey, key, geoSearchParam), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.GEOSEARCHSTORE,
					(cmd)->cmd.geosearchStore(destKey, key, geoSearchParam), (v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.GEOSEARCHSTORE,
					(cmd)->cmd.geosearchStore(destKey, key, geoSearchParam), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long geoSearchStore(final byte[] destKey, final byte[] key,
							   final GeoSearchStoreArgument geoSearchStoreArgument) {
		final CommandArguments args = CommandArguments.create(destKey).add(key).add(geoSearchStoreArgument);
		final GeoSearchParam geoSearchParam = JedisGeoSearchStoreParam.from(geoSearchStoreArgument);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.GEOSEARCHSTORE,
					(cmd)->cmd.geosearchStore(destKey, key, geoSearchParam), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.GEOSEARCHSTORE,
					(cmd)->cmd.geosearchStore(destKey, key, geoSearchParam), (v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.GEOSEARCHSTORE,
					(cmd)->cmd.geosearchStore(destKey, key, geoSearchParam), (v)->v)
					.run(args);
		}
	}

	private Long geoAdd(final String key, final Map<String, Geo> memberCoordinates,
						final GeoAddArgument geoAddArgument, final CommandArguments args) {
		final Map<String, GeoCoordinate> geoCoordinates = createGeoCoordinateMapFromGeoMap(memberCoordinates);
		final GeoAddParams geoAddParams = JedisGeoAddParam.from(geoAddArgument);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.GEOADD,
					(cmd)->cmd.geoadd(key, geoAddParams, geoCoordinates), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.GEOADD,
					(cmd)->cmd.geoadd(key, geoAddParams, geoCoordinates), (v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.GEOADD,
					(cmd)->cmd.geoadd(key, geoAddParams, geoCoordinates), (v)->v)
					.run(args);
		}
	}

	private Long geoAdd(final byte[] key, final Map<byte[], Geo> memberCoordinates,
						final GeoAddArgument geoAddArgument, final CommandArguments args) {
		final Map<byte[], GeoCoordinate> geoCoordinates = createGeoCoordinateMapFromGeoMap(memberCoordinates);
		final GeoAddParams geoAddParams = JedisGeoAddParam.from(geoAddArgument);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.GEOADD,
					(cmd)->cmd.geoadd(key, geoAddParams, geoCoordinates), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.GEOADD,
					(cmd)->cmd.geoadd(key, geoAddParams, geoCoordinates), (v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.GEOADD,
					(cmd)->cmd.geoadd(key, geoAddParams, geoCoordinates), (v)->v)
					.run(args);
		}
	}

}
