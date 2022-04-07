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
import com.buession.redis.client.jedis.JedisStandaloneClient;
import com.buession.redis.core.GeoRadius;
import com.buession.redis.core.GeoUnit;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.ProtocolCommand;
import redis.clients.jedis.GeoCoordinate;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.GeoRadiusParam;

import java.util.List;
import java.util.Map;

/**
 * Jedis 单机模式地理位置命令操作
 *
 * @author Yong.Teng
 */
public final class JedisGeoOperations extends AbstractGeoOperations<Jedis> {

	public JedisGeoOperations(final JedisStandaloneClient client){
		super(client);
	}

	@Override
	public Long geoAdd(final String key, final String member, final double longitude, final double latitude){
		final CommandArguments args = CommandArguments.create("key", key).put("member", member)
				.put("longitude", longitude).put("latitude", latitude);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().geoadd(key, longitude, latitude, member)),
					ProtocolCommand.GEOADD, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().geoadd(key, longitude, latitude, member)),
					ProtocolCommand.GEOADD, args);
		}else{
			return execute((cmd)->cmd.geoadd(key, longitude, latitude, member), ProtocolCommand.GEOADD, args);
		}
	}

	@Override
	public Long geoAdd(final byte[] key, final byte[] member, final double longitude, final double latitude){
		final CommandArguments args = CommandArguments.create("key", key).put("member", member)
				.put("longitude", longitude).put("latitude", latitude);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().geoadd(key, longitude, latitude, member)),
					ProtocolCommand.GEOADD, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().geoadd(key, longitude, latitude, member)),
					ProtocolCommand.GEOADD, args);
		}else{
			return execute((cmd)->cmd.geoadd(key, longitude, latitude, member), ProtocolCommand.GEOADD, args);
		}
	}

	@Override
	public Long geoAdd(final String key, final Map<String, Geo> memberCoordinates){
		final CommandArguments args = CommandArguments.create("key", key).put("memberCoordinates", memberCoordinates);
		final Map<String, GeoCoordinate> memberCoordinateMap = STRING_MAP_GEO_MAP_JEDIS_CONVERTER.convert(
				memberCoordinates);

		if(isPipeline()){
			return pipelineExecute(
					(cmd)->newJedisResult(getPipeline().geoadd(key, memberCoordinateMap)), ProtocolCommand.GEOADD,
					args);
		}else if(isTransaction()){
			return transactionExecute(
					(cmd)->newJedisResult(getTransaction().geoadd(key, memberCoordinateMap)), ProtocolCommand.GEOADD,
					args);
		}else{
			return execute((cmd)->cmd.geoadd(key, memberCoordinateMap), ProtocolCommand.GEOADD, args);
		}
	}

	@Override
	public Long geoAdd(final byte[] key, final Map<byte[], Geo> memberCoordinates){
		final CommandArguments args = CommandArguments.create("key", key).put("memberCoordinates", memberCoordinates);
		final Map<byte[], GeoCoordinate> memberCoordinateMap = BINARY_MAP_GEO_MAP_JEDIS_CONVERTER.convert(
				memberCoordinates);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().geoadd(key, memberCoordinateMap)),
					ProtocolCommand.GEOADD, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().geoadd(key, memberCoordinateMap)),
					ProtocolCommand.GEOADD, args);
		}else{
			return execute((cmd)->cmd.geoadd(key, memberCoordinateMap), ProtocolCommand.GEOADD, args);
		}
	}

	@Override
	public List<String> geoHash(final String key, final String... members){
		final CommandArguments args = CommandArguments.create("key", key).put("members", members);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().geohash(key, members)), ProtocolCommand.GEOHASH,
					args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().geohash(key, members)),
					ProtocolCommand.GEOHASH, args);
		}else{
			return execute((cmd)->cmd.geohash(key, members), ProtocolCommand.GEOHASH, args);
		}
	}

	@Override
	public List<byte[]> geoHash(final byte[] key, final byte[]... members){
		final CommandArguments args = CommandArguments.create("key", key).put("members", members);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().geohash(key, members)), ProtocolCommand.GEOHASH,
					args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().geohash(key, members)),
					ProtocolCommand.GEOHASH, args);
		}else{
			return execute((cmd)->cmd.geohash(key, members), ProtocolCommand.GEOHASH, args);
		}
	}

	@Override
	public List<Geo> geoPos(final String key, final String... members){
		final CommandArguments args = CommandArguments.create("key", key).put("members", members);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().geopos(key, members), LIST_GEO_EXPOSE_CONVERTER),
					ProtocolCommand.GEOPOS, args);
		}else if(isTransaction()){
			return transactionExecute(
					(cmd)->newJedisResult(getTransaction().geopos(key, members), LIST_GEO_EXPOSE_CONVERTER),
					ProtocolCommand.GEOPOS, args);
		}else{
			return execute((cmd)->cmd.geopos(key, members), LIST_GEO_EXPOSE_CONVERTER, ProtocolCommand.GEOPOS, args);
		}
	}

	@Override
	public List<Geo> geoPos(final byte[] key, final byte[]... members){
		final CommandArguments args = CommandArguments.create("key", key).put("members", members);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().geopos(key, members), LIST_GEO_EXPOSE_CONVERTER),
					ProtocolCommand.GEOPOS, args);
		}else if(isTransaction()){
			return transactionExecute(
					(cmd)->newJedisResult(getTransaction().geopos(key, members), LIST_GEO_EXPOSE_CONVERTER),
					ProtocolCommand.GEOPOS, args);
		}else{
			return execute((cmd)->cmd.geopos(key, members), LIST_GEO_EXPOSE_CONVERTER, ProtocolCommand.GEOPOS, args);
		}
	}

	@Override
	public Double geoDist(final String key, final String member1, final String member2){
		final CommandArguments args = CommandArguments.create("key", key).put("member1", member1)
				.put("member2", member2);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().geodist(key, member1, member2)),
					ProtocolCommand.GEODIST, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().geodist(key, member1, member2)),
					ProtocolCommand.GEODIST, args);
		}else{
			return execute((cmd)->cmd.geodist(key, member1, member2), ProtocolCommand.GEODIST, args);
		}
	}

	@Override
	public Double geoDist(final byte[] key, final byte[] member1, final byte[] member2){
		final CommandArguments args = CommandArguments.create("key", key).put("member1", member1)
				.put("member2", member2);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().geodist(key, member1, member2)),
					ProtocolCommand.GEODIST, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().geodist(key, member1, member2)),
					ProtocolCommand.GEODIST, args);
		}else{
			return execute((cmd)->cmd.geodist(key, member1, member2), ProtocolCommand.GEODIST, args);
		}
	}

	@Override
	public Double geoDist(final String key, final String member1, final String member2, final GeoUnit unit){
		final CommandArguments args = CommandArguments.create("key", key).put("member1", member1)
				.put("member2", member2).put("unit", unit);
		final redis.clients.jedis.args.GeoUnit geoUnit = GEO_UNIT_JEDIS_CONVERTER.convert(unit);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().geodist(key, member1, member2, geoUnit)),
					ProtocolCommand.GEODIST, args);
		}else if(isTransaction()){
			return transactionExecute(
					(cmd)->newJedisResult(getTransaction().geodist(key, member1, member2, geoUnit)),
					ProtocolCommand.GEODIST, args);
		}else{
			return execute((cmd)->cmd.geodist(key, member1, member2, geoUnit), ProtocolCommand.GEODIST, args);
		}
	}

	@Override
	public Double geoDist(final byte[] key, final byte[] member1, final byte[] member2, final GeoUnit unit){
		final CommandArguments args = CommandArguments.create("key", key).put("member1", member1)
				.put("member2", member2).put("unit", unit);
		final redis.clients.jedis.args.GeoUnit geoUnit = GEO_UNIT_JEDIS_CONVERTER.convert(unit);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().geodist(key, member1, member2, geoUnit)),
					ProtocolCommand.GEODIST, args);
		}else if(isTransaction()){
			return transactionExecute(
					(cmd)->newJedisResult(getTransaction().geodist(key, member1, member2, geoUnit)),
					ProtocolCommand.GEODIST, args);
		}else{
			return execute((cmd)->cmd.geodist(key, member1, member2, geoUnit), ProtocolCommand.GEODIST, args);
		}
	}

	@Override
	public List<GeoRadius> geoRadius(final String key, final double longitude, final double latitude,
									 final double radius, final GeoUnit unit){
		final CommandArguments args = CommandArguments.create("key", key).put("longitude", longitude)
				.put("latitude", latitude).put("radius", radius).put("unit", unit);
		final redis.clients.jedis.args.GeoUnit geoUnit = GEO_UNIT_JEDIS_CONVERTER.convert(unit);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().georadius(key, longitude, latitude, radius,
					geoUnit), LIST_GEO_RADIUS_EXPOSE_CONVERTER), ProtocolCommand.GEORADIUS, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().georadius(key, longitude, latitude,
							radius, geoUnit), LIST_GEO_RADIUS_EXPOSE_CONVERTER), ProtocolCommand.GEORADIUS,
					args);
		}else{
			return execute((cmd)->cmd.georadius(key, longitude, latitude, radius, geoUnit),
					LIST_GEO_RADIUS_EXPOSE_CONVERTER, ProtocolCommand.GEORADIUS, args);
		}
	}

	@Override
	public List<GeoRadius> geoRadius(final byte[] key, final double longitude, final double latitude,
									 final double radius, final GeoUnit unit){
		final CommandArguments args = CommandArguments.create("key", key).put("longitude", longitude)
				.put("latitude", latitude).put("radius", radius).put("unit", unit);
		final redis.clients.jedis.args.GeoUnit geoUnit = GEO_UNIT_JEDIS_CONVERTER.convert(unit);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().georadius(key, longitude, latitude, radius,
					geoUnit), LIST_GEO_RADIUS_EXPOSE_CONVERTER), ProtocolCommand.GEORADIUS, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().georadius(key, longitude, latitude,
							radius, geoUnit), LIST_GEO_RADIUS_EXPOSE_CONVERTER), ProtocolCommand.GEORADIUS,
					args);
		}else{
			return execute((cmd)->cmd.georadius(key, longitude, latitude, radius, geoUnit),
					LIST_GEO_RADIUS_EXPOSE_CONVERTER, ProtocolCommand.GEORADIUS, args);
		}
	}

	@Override
	public List<GeoRadius> geoRadius(final String key, final double longitude, final double latitude,
									 final double radius, final GeoUnit unit,
									 final GeoRadiusArgument geoRadiusArgument){
		final CommandArguments args = CommandArguments.create("key", key).put("longitude", longitude)
				.put("latitude", latitude).put("radius", radius).put("unit", unit)
				.put("geoRadiusArgument", geoRadiusArgument);
		final redis.clients.jedis.args.GeoUnit geoUnit = GEO_UNIT_JEDIS_CONVERTER.convert(unit);
		final GeoRadiusParam geoRadiusParam = GEO_RADIUS_ARGUMENT_JEDIS_CONVERTER.convert(geoRadiusArgument);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().georadius(key, longitude, latitude, radius,
					geoUnit, geoRadiusParam), LIST_GEO_RADIUS_EXPOSE_CONVERTER), ProtocolCommand.GEORADIUS, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().georadius(key, longitude, latitude,
							radius, geoUnit, geoRadiusParam), LIST_GEO_RADIUS_EXPOSE_CONVERTER), ProtocolCommand.GEORADIUS,
					args);
		}else{
			return execute((cmd)->cmd.georadius(key, longitude, latitude, radius, geoUnit, geoRadiusParam),
					LIST_GEO_RADIUS_EXPOSE_CONVERTER, ProtocolCommand.GEORADIUS, args);
		}
	}

	@Override
	public List<GeoRadius> geoRadius(final byte[] key, final double longitude, final double latitude,
									 final double radius, final GeoUnit unit,
									 final GeoRadiusArgument geoRadiusArgument){
		final CommandArguments args = CommandArguments.create("key", key).put("longitude", longitude)
				.put("latitude", latitude).put("radius", radius).put("unit", unit)
				.put("geoRadiusArgument", geoRadiusArgument);
		final redis.clients.jedis.args.GeoUnit geoUnit = GEO_UNIT_JEDIS_CONVERTER.convert(unit);
		final GeoRadiusParam geoRadiusParam = GEO_RADIUS_ARGUMENT_JEDIS_CONVERTER.convert(geoRadiusArgument);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().georadius(key, longitude, latitude, radius,
					geoUnit, geoRadiusParam), LIST_GEO_RADIUS_EXPOSE_CONVERTER), ProtocolCommand.GEORADIUS, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().georadius(key, longitude, latitude,
							radius, geoUnit, geoRadiusParam), LIST_GEO_RADIUS_EXPOSE_CONVERTER), ProtocolCommand.GEORADIUS,
					args);
		}else{
			return execute((cmd)->cmd.georadius(key, longitude, latitude, radius, geoUnit, geoRadiusParam),
					LIST_GEO_RADIUS_EXPOSE_CONVERTER, ProtocolCommand.GEORADIUS, args);
		}
	}

	@Override
	public List<GeoRadius> geoRadiusRo(final String key, final double longitude, final double latitude,
									   final double radius, final GeoUnit unit){
		final CommandArguments args = CommandArguments.create("key", key).put("longitude", longitude)
				.put("latitude", latitude).put("radius", radius).put("unit", unit);
		final redis.clients.jedis.args.GeoUnit geoUnit = GEO_UNIT_JEDIS_CONVERTER.convert(unit);

		if(isPipeline()){
			return pipelineExecute(
					(cmd)->newJedisResult(getPipeline().georadiusReadonly(key, longitude, latitude, radius,
							geoUnit), LIST_GEO_RADIUS_EXPOSE_CONVERTER), ProtocolCommand.GEORADIUS_RO, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().georadiusReadonly(key, longitude, latitude,
							radius, geoUnit), LIST_GEO_RADIUS_EXPOSE_CONVERTER), ProtocolCommand.GEORADIUS_RO,
					args);
		}else{
			return execute((cmd)->cmd.georadiusReadonly(key, longitude, latitude, radius, geoUnit),
					LIST_GEO_RADIUS_EXPOSE_CONVERTER, ProtocolCommand.GEORADIUS_RO, args);
		}
	}

	@Override
	public List<GeoRadius> geoRadiusRo(final byte[] key, final double longitude, final double latitude,
									   final double radius, final GeoUnit unit){
		final CommandArguments args = CommandArguments.create("key", key).put("longitude", longitude)
				.put("latitude", latitude).put("radius", radius).put("unit", unit);
		final redis.clients.jedis.args.GeoUnit geoUnit = GEO_UNIT_JEDIS_CONVERTER.convert(unit);

		if(isPipeline()){
			return pipelineExecute(
					(cmd)->newJedisResult(getPipeline().georadiusReadonly(key, longitude, latitude, radius,
							geoUnit), LIST_GEO_RADIUS_EXPOSE_CONVERTER), ProtocolCommand.GEORADIUS_RO, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().georadiusReadonly(key, longitude, latitude,
							radius, geoUnit), LIST_GEO_RADIUS_EXPOSE_CONVERTER), ProtocolCommand.GEORADIUS_RO,
					args);
		}else{
			return execute((cmd)->cmd.georadiusReadonly(key, longitude, latitude, radius, geoUnit),
					LIST_GEO_RADIUS_EXPOSE_CONVERTER, ProtocolCommand.GEORADIUS_RO, args);
		}
	}

	@Override
	public List<GeoRadius> geoRadiusRo(final String key, final double longitude, final double latitude,
									   final double radius, final GeoUnit unit,
									   final GeoRadiusArgument geoRadiusArgument){
		final CommandArguments args = CommandArguments.create("key", key).put("longitude", longitude)
				.put("latitude", latitude).put("radius", radius).put("unit", unit)
				.put("geoRadiusArgument", geoRadiusArgument);
		final redis.clients.jedis.args.GeoUnit geoUnit = GEO_UNIT_JEDIS_CONVERTER.convert(unit);
		final GeoRadiusParam geoRadiusParam = GEO_RADIUS_ARGUMENT_JEDIS_CONVERTER.convert(geoRadiusArgument);

		if(isPipeline()){
			return pipelineExecute(
					(cmd)->newJedisResult(getPipeline().georadiusReadonly(key, longitude, latitude, radius,
							geoUnit, geoRadiusParam), LIST_GEO_RADIUS_EXPOSE_CONVERTER), ProtocolCommand.GEORADIUS_RO,
					args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().georadiusReadonly(key, longitude, latitude,
							radius, geoUnit, geoRadiusParam), LIST_GEO_RADIUS_EXPOSE_CONVERTER), ProtocolCommand.GEORADIUS_RO,
					args);
		}else{
			return execute((cmd)->cmd.georadiusReadonly(key, longitude, latitude, radius, geoUnit, geoRadiusParam),
					LIST_GEO_RADIUS_EXPOSE_CONVERTER, ProtocolCommand.GEORADIUS_RO, args);
		}
	}

	@Override
	public List<GeoRadius> geoRadiusRo(final byte[] key, final double longitude, final double latitude,
									   final double radius, final GeoUnit unit,
									   final GeoRadiusArgument geoRadiusArgument){
		final CommandArguments args = CommandArguments.create("key", key).put("longitude", longitude)
				.put("latitude", latitude).put("radius", radius).put("unit", unit)
				.put("geoRadiusArgument", geoRadiusArgument);
		final redis.clients.jedis.args.GeoUnit geoUnit = GEO_UNIT_JEDIS_CONVERTER.convert(unit);
		final GeoRadiusParam geoRadiusParam = GEO_RADIUS_ARGUMENT_JEDIS_CONVERTER.convert(geoRadiusArgument);

		if(isPipeline()){
			return pipelineExecute(
					(cmd)->newJedisResult(getPipeline().georadiusReadonly(key, longitude, latitude, radius,
							geoUnit, geoRadiusParam), LIST_GEO_RADIUS_EXPOSE_CONVERTER), ProtocolCommand.GEORADIUS_RO,
					args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().georadiusReadonly(key, longitude, latitude,
							radius, geoUnit, geoRadiusParam), LIST_GEO_RADIUS_EXPOSE_CONVERTER), ProtocolCommand.GEORADIUS_RO,
					args);
		}else{
			return execute((cmd)->cmd.georadiusReadonly(key, longitude, latitude, radius, geoUnit, geoRadiusParam),
					LIST_GEO_RADIUS_EXPOSE_CONVERTER, ProtocolCommand.GEORADIUS_RO, args);
		}
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final String key, final String member, final double radius,
											 final GeoUnit unit){
		final CommandArguments args = CommandArguments.create("key", key).put("member", member).put("radius", radius)
				.put("unit", unit);
		final redis.clients.jedis.args.GeoUnit geoUnit = GEO_UNIT_JEDIS_CONVERTER.convert(unit);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().georadiusByMember(key, member, radius, geoUnit)
					, LIST_GEO_RADIUS_EXPOSE_CONVERTER), ProtocolCommand.GEORADIUSBYMEMBER, args);
		}else if(isTransaction()){
			return transactionExecute(
					(cmd)->newJedisResult(getTransaction().georadiusByMember(key, member, radius, geoUnit)
							, LIST_GEO_RADIUS_EXPOSE_CONVERTER), ProtocolCommand.GEORADIUSBYMEMBER, args);
		}else{
			return execute((cmd)->cmd.georadiusByMember(key, member, radius, geoUnit), LIST_GEO_RADIUS_EXPOSE_CONVERTER,
					ProtocolCommand.GEORADIUSBYMEMBER, args);
		}
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final byte[] key, final byte[] member, final double radius,
											 final GeoUnit unit){
		final CommandArguments args = CommandArguments.create("key", key).put("member", member).put("radius", radius)
				.put("unit", unit);
		final redis.clients.jedis.args.GeoUnit geoUnit = GEO_UNIT_JEDIS_CONVERTER.convert(unit);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().georadiusByMember(key, member, radius, geoUnit)
					, LIST_GEO_RADIUS_EXPOSE_CONVERTER), ProtocolCommand.GEORADIUSBYMEMBER, args);
		}else if(isTransaction()){
			return transactionExecute(
					(cmd)->newJedisResult(getTransaction().georadiusByMember(key, member, radius, geoUnit)
							, LIST_GEO_RADIUS_EXPOSE_CONVERTER), ProtocolCommand.GEORADIUSBYMEMBER, args);
		}else{
			return execute((cmd)->cmd.georadiusByMember(key, member, radius, geoUnit), LIST_GEO_RADIUS_EXPOSE_CONVERTER,
					ProtocolCommand.GEORADIUSBYMEMBER, args);
		}
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final String key, final String member, final double radius,
											 final GeoUnit unit, final GeoRadiusArgument geoRadiusArgument){
		final CommandArguments args = CommandArguments.create("key", key).put("member", member).put("radius", radius)
				.put("unit", unit).put("geoRadiusArgument", geoRadiusArgument);
		final redis.clients.jedis.args.GeoUnit geoUnit = GEO_UNIT_JEDIS_CONVERTER.convert(unit);
		final GeoRadiusParam geoRadiusParam = GEO_RADIUS_ARGUMENT_JEDIS_CONVERTER.convert(geoRadiusArgument);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().georadiusByMember(key, member, radius, geoUnit,
					geoRadiusParam), LIST_GEO_RADIUS_EXPOSE_CONVERTER), ProtocolCommand.GEORADIUSBYMEMBER, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().georadiusByMember(key, member, radius,
							geoUnit, geoRadiusParam), LIST_GEO_RADIUS_EXPOSE_CONVERTER), ProtocolCommand.GEORADIUSBYMEMBER,
					args);
		}else{
			return execute((cmd)->cmd.georadiusByMember(key, member, radius, geoUnit, geoRadiusParam),
					LIST_GEO_RADIUS_EXPOSE_CONVERTER, ProtocolCommand.GEORADIUSBYMEMBER, args);
		}
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final byte[] key, final byte[] member, final double radius,
											 final GeoUnit unit, final GeoRadiusArgument geoRadiusArgument){
		final CommandArguments args = CommandArguments.create("key", key).put("member", member).put("radius", radius)
				.put("unit", unit).put("geoRadiusArgument", geoRadiusArgument);
		final redis.clients.jedis.args.GeoUnit geoUnit = GEO_UNIT_JEDIS_CONVERTER.convert(unit);
		final GeoRadiusParam geoRadiusParam = GEO_RADIUS_ARGUMENT_JEDIS_CONVERTER.convert(geoRadiusArgument);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().georadiusByMember(key, member, radius, geoUnit,
					geoRadiusParam), LIST_GEO_RADIUS_EXPOSE_CONVERTER), ProtocolCommand.GEORADIUSBYMEMBER, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().georadiusByMember(key, member, radius,
							geoUnit, geoRadiusParam), LIST_GEO_RADIUS_EXPOSE_CONVERTER), ProtocolCommand.GEORADIUSBYMEMBER,
					args);
		}else{
			return execute((cmd)->cmd.georadiusByMember(key, member, radius, geoUnit, geoRadiusParam),
					LIST_GEO_RADIUS_EXPOSE_CONVERTER, ProtocolCommand.GEORADIUSBYMEMBER, args);
		}
	}

	@Override
	public List<GeoRadius> geoRadiusByMemberRo(final String key, final String member, final double radius,
											   final GeoUnit unit){
		final CommandArguments args = CommandArguments.create("key", key).put("member", member).put("radius", radius)
				.put("unit", unit);
		final redis.clients.jedis.args.GeoUnit geoUnit = GEO_UNIT_JEDIS_CONVERTER.convert(unit);

		if(isPipeline()){
			return pipelineExecute(
					(cmd)->newJedisResult(getPipeline().georadiusByMemberReadonly(key, member, radius, geoUnit)
							, LIST_GEO_RADIUS_EXPOSE_CONVERTER), ProtocolCommand.GEORADIUSBYMEMBER_RO, args);
		}else if(isTransaction()){
			return transactionExecute(
					(cmd)->newJedisResult(getTransaction().georadiusByMemberReadonly(key, member, radius, geoUnit)
							, LIST_GEO_RADIUS_EXPOSE_CONVERTER), ProtocolCommand.GEORADIUSBYMEMBER_RO, args);
		}else{
			return execute((cmd)->cmd.georadiusByMemberReadonly(key, member, radius, geoUnit),
					LIST_GEO_RADIUS_EXPOSE_CONVERTER, ProtocolCommand.GEORADIUSBYMEMBER_RO, args);
		}
	}

	@Override
	public List<GeoRadius> geoRadiusByMemberRo(final byte[] key, final byte[] member, final double radius,
											   final GeoUnit unit){
		final CommandArguments args = CommandArguments.create("key", key).put("member", member).put("radius", radius)
				.put("unit", unit);
		final redis.clients.jedis.args.GeoUnit geoUnit = GEO_UNIT_JEDIS_CONVERTER.convert(unit);

		if(isPipeline()){
			return pipelineExecute(
					(cmd)->newJedisResult(getPipeline().georadiusByMemberReadonly(key, member, radius, geoUnit)
							, LIST_GEO_RADIUS_EXPOSE_CONVERTER), ProtocolCommand.GEORADIUSBYMEMBER_RO, args);
		}else if(isTransaction()){
			return transactionExecute(
					(cmd)->newJedisResult(getTransaction().georadiusByMemberReadonly(key, member, radius, geoUnit)
							, LIST_GEO_RADIUS_EXPOSE_CONVERTER), ProtocolCommand.GEORADIUSBYMEMBER_RO, args);
		}else{
			return execute((cmd)->cmd.georadiusByMemberReadonly(key, member, radius, geoUnit),
					LIST_GEO_RADIUS_EXPOSE_CONVERTER, ProtocolCommand.GEORADIUSBYMEMBER_RO, args);
		}
	}

	@Override
	public List<GeoRadius> geoRadiusByMemberRo(final String key, final String member, final double radius,
											   final GeoUnit unit, final GeoRadiusArgument geoRadiusArgument){
		final CommandArguments args = CommandArguments.create("key", key).put("member", member).put("radius", radius)
				.put("unit", unit).put("geoRadiusArgument", geoRadiusArgument);
		final redis.clients.jedis.args.GeoUnit geoUnit = GEO_UNIT_JEDIS_CONVERTER.convert(unit);
		final GeoRadiusParam geoRadiusParam = GEO_RADIUS_ARGUMENT_JEDIS_CONVERTER.convert(geoRadiusArgument);

		if(isPipeline()){
			return pipelineExecute(
					(cmd)->newJedisResult(getPipeline().georadiusByMemberReadonly(key, member, radius, geoUnit,
							geoRadiusParam), LIST_GEO_RADIUS_EXPOSE_CONVERTER), ProtocolCommand.GEORADIUSBYMEMBER_RO,
					args);
		}else if(isTransaction()){
			return transactionExecute(
					(cmd)->newJedisResult(getTransaction().georadiusByMemberReadonly(key, member, radius,
							geoUnit, geoRadiusParam), LIST_GEO_RADIUS_EXPOSE_CONVERTER),
					ProtocolCommand.GEORADIUSBYMEMBER_RO, args);
		}else{
			return execute((cmd)->cmd.georadiusByMemberReadonly(key, member, radius, geoUnit, geoRadiusParam),
					LIST_GEO_RADIUS_EXPOSE_CONVERTER, ProtocolCommand.GEORADIUSBYMEMBER_RO, args);
		}
	}

	@Override
	public List<GeoRadius> geoRadiusByMemberRo(final byte[] key, final byte[] member, final double radius,
											   final GeoUnit unit, final GeoRadiusArgument geoRadiusArgument){
		final CommandArguments args = CommandArguments.create("key", key).put("member", member).put("radius", radius)
				.put("unit", unit).put("geoRadiusArgument", geoRadiusArgument);
		final redis.clients.jedis.args.GeoUnit geoUnit = GEO_UNIT_JEDIS_CONVERTER.convert(unit);
		final GeoRadiusParam geoRadiusParam = GEO_RADIUS_ARGUMENT_JEDIS_CONVERTER.convert(geoRadiusArgument);

		if(isPipeline()){
			return pipelineExecute(
					(cmd)->newJedisResult(getPipeline().georadiusByMemberReadonly(key, member, radius, geoUnit,
							geoRadiusParam), LIST_GEO_RADIUS_EXPOSE_CONVERTER), ProtocolCommand.GEORADIUSBYMEMBER_RO,
					args);
		}else if(isTransaction()){
			return transactionExecute(
					(cmd)->newJedisResult(getTransaction().georadiusByMemberReadonly(key, member, radius,
							geoUnit, geoRadiusParam), LIST_GEO_RADIUS_EXPOSE_CONVERTER),
					ProtocolCommand.GEORADIUSBYMEMBER_RO, args);
		}else{
			return execute((cmd)->cmd.georadiusByMemberReadonly(key, member, radius, geoUnit, geoRadiusParam),
					LIST_GEO_RADIUS_EXPOSE_CONVERTER, ProtocolCommand.GEORADIUSBYMEMBER_RO, args);
		}
	}

}
