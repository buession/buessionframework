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
 * | Copyright @ 2013-2020 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.client.jedis.operations;

import com.buession.lang.Geo;
import com.buession.redis.client.jedis.JedisClientUtils;
import com.buession.redis.client.jedis.JedisRedisClient;
import com.buession.redis.core.GeoRadius;
import com.buession.redis.core.GeoUnit;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.operations.OperationsCommandArguments;
import redis.clients.jedis.commands.JedisCommands;

import java.util.List;
import java.util.Map;

/**
 * @author Yong.Teng
 */
public class DefaultJedisGeoRedisOperations<C extends JedisCommands> extends AbstractJedisRedisOperations implements JedisGeoRedisOperations {

	public DefaultJedisGeoRedisOperations(final JedisRedisClient client){
		super(client);
	}

	@Override
	public Long geoAdd(final String key, final String member, final double longitude, final double latitude){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("member",
				member).put("longitude", longitude).put("latitude", latitude);

		if(isTransaction()){
			return execute((C cmd)->getTransaction().geoadd(key, longitude, latitude, member).get(),
					ProtocolCommand.GEOADD, args);
		}else{
			return execute((C cmd)->cmd.geoadd(key, longitude, latitude, member), ProtocolCommand.GEOADD, args);
		}
	}

	@Override
	public Long geoAdd(final String key, final Map<String, Geo> memberCoordinates){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put(
				"memberCoordinates", memberCoordinates);

		if(isTransaction()){
			return execute((C cmd)->getTransaction().geoadd(key, JedisClientUtils.geoMapConvert(memberCoordinates)).get(), ProtocolCommand.GEOADD, args);
		}else{
			return execute((C cmd)->cmd.geoadd(key, JedisClientUtils.geoMapConvert(memberCoordinates)),
					ProtocolCommand.GEOADD, args);
		}
	}

	@Override
	public List<Geo> geoPos(final String key, final String... members){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("members"
				, members);

		if(isTransaction()){
			return execute((C cmd)->JedisClientUtils.geoListDeconvert(getTransaction().geopos(key, members).get()),
					ProtocolCommand.GEOPOS, args);
		}else{
			return execute((C cmd)->JedisClientUtils.geoListDeconvert(cmd.geopos(key, members)),
					ProtocolCommand.GEOPOS, args);
		}
	}

	@Override
	public Double geoDist(final String key, final String member1, final String member2){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("member1"
				, member1).put("member2", member2);

		if(isTransaction()){
			return execute((C cmd)->getTransaction().geodist(key, member1, member2).get(), ProtocolCommand.GEODIST,
					args);
		}else{
			return execute((C cmd)->cmd.geodist(key, member1, member2), ProtocolCommand.GEODIST, args);
		}
	}

	@Override
	public Double geoDist(final String key, final String member1, final String member2, final GeoUnit unit){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("member1"
				, member1).put("member2", member2).put("unit", unit);

		if(isTransaction()){
			return execute((C cmd)->getTransaction().geodist(key, member1, member2,
					JedisClientUtils.geoUnitConvert(unit)).get(), ProtocolCommand.GEODIST, args);
		}else{
			return execute((C cmd)->cmd.geodist(key, member1, member2, JedisClientUtils.geoUnitConvert(unit)),
					ProtocolCommand.GEODIST, args);
		}
	}

	@Override
	public List<GeoRadius> geoRadius(final String key, final double longitude, final double latitude,
			final double radius, final GeoUnit unit){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put(
				"longitude", longitude).put("latitude", latitude).put("radius", radius).put("unit", unit);

		if(isTransaction()){
			return execute((C cmd)->JedisClientUtils.listGeoRadiusDeconvert(getTransaction().georadius(key, longitude,
					latitude, radius, JedisClientUtils.geoUnitConvert(unit)).get()), ProtocolCommand.GEORADIUS, args);
		}else{
			return execute((C cmd)->JedisClientUtils.listGeoRadiusDeconvert(cmd.georadius(key, longitude, latitude,
					radius, JedisClientUtils.geoUnitConvert(unit))), ProtocolCommand.GEORADIUS, args);
		}
	}

	@Override
	public List<GeoRadius> geoRadius(final String key, final double longitude, final double latitude,
			final double radius, final GeoUnit unit, final GeoArgument geoArgument){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put(
				"longitude", longitude).put("latitude", latitude).put("radius", radius).put("unit", unit).put(
						"geoArgument", geoArgument);

		if(isTransaction()){
			return execute((C cmd)->JedisClientUtils.listGeoRadiusDeconvert(getTransaction().georadius(key, longitude,
					latitude, radius, JedisClientUtils.geoUnitConvert(unit),
					JedisClientUtils.geoArgumentConvert(geoArgument)).get()), ProtocolCommand.GEORADIUS, args);
		}else{
			return execute((C cmd)->JedisClientUtils.listGeoRadiusDeconvert(cmd.georadius(key, longitude, latitude,
					radius, JedisClientUtils.geoUnitConvert(unit), JedisClientUtils.geoArgumentConvert(geoArgument))),
					ProtocolCommand.GEORADIUS, args);
		}
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final String key, final String member, final double radius,
			final GeoUnit unit){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("member",
				member).put("radius", radius).put("unit", unit);

		if(isTransaction()){
			return execute((C cmd)->JedisClientUtils.listGeoRadiusDeconvert(getTransaction().georadiusByMember(key,
					member, radius, JedisClientUtils.geoUnitConvert(unit)).get()), ProtocolCommand.GEORADIUSBYMEMBER,
					args);
		}else{
			return execute((C cmd)->JedisClientUtils.listGeoRadiusDeconvert(cmd.georadiusByMember(key, member, radius,
					JedisClientUtils.geoUnitConvert(unit))), ProtocolCommand.GEORADIUSBYMEMBER, args);
		}
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final String key, final String member, final double radius,
			final GeoUnit unit, final GeoArgument geoArgument){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("member",
				member).put("radius", radius).put("unit", unit).put("geoArgument", geoArgument);

		if(isTransaction()){
			return execute((C cmd)->JedisClientUtils.listGeoRadiusDeconvert(getTransaction().georadiusByMember(key,
					member, radius, JedisClientUtils.geoUnitConvert(unit),
					JedisClientUtils.geoArgumentConvert(geoArgument)).get()), ProtocolCommand.GEORADIUSBYMEMBER, args);
		}else{
			return execute((C cmd)->JedisClientUtils.listGeoRadiusDeconvert(cmd.georadiusByMember(key, member, radius,
					JedisClientUtils.geoUnitConvert(unit), JedisClientUtils.geoArgumentConvert(geoArgument))),
					ProtocolCommand.GEORADIUSBYMEMBER, args);
		}
	}

	@Override
	public List<String> geoHash(final String key, final String... members){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("members"
				, members);

		if(isTransaction()){
			return execute((C cmd)->getTransaction().geohash(key, members).get(), ProtocolCommand.GEOHASH, args);
		}else{
			return execute((C cmd)->cmd.geohash(key, members), ProtocolCommand.GEOHASH, args);
		}
	}

}