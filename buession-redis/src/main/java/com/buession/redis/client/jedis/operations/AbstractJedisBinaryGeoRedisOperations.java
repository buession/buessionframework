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
import com.buession.redis.core.command.GeoCommands;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.operations.OperationsCommandArguments;
import redis.clients.jedis.commands.BinaryJedisCommands;

import java.util.List;
import java.util.Map;

/**
 * @author Yong.Teng
 */
public abstract class AbstractJedisBinaryGeoRedisOperations<C extends BinaryJedisCommands> extends AbstractJedisBinaryRedisOperations implements JedisBinaryGeoRedisOperations {

	public AbstractJedisBinaryGeoRedisOperations(final JedisRedisClient client){
		super(client);
	}

	@Override
	public Double geoDist(final byte[] key, final byte[] member1, final byte[] member2){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("member1"
				, member1).put("member2", member2);

		if(isTransaction()){
			return execute((cmd)->getTransaction().geodist(key, member1, member2).get(), ProtocolCommand.GEODIST,
					args);
		}else{
			return execute((cmd)->cmd.geodist(key, member1, member2), ProtocolCommand.GEODIST, args);
		}
	}

	@Override
	public Double geoDist(final byte[] key, final byte[] member1, final byte[] member2, final GeoUnit unit){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("member1"
				, member1).put("member2", member2).put("unit", unit);

		if(isTransaction()){
			return execute((cmd)->getTransaction().geodist(key, member1, member2,
					JedisClientUtils.geoUnitConvert(unit)).get(), ProtocolCommand.GEODIST, args);
		}else{
			return execute((cmd)->cmd.geodist(key, member1, member2, JedisClientUtils.geoUnitConvert(unit)),
					ProtocolCommand.GEODIST, args);
		}
	}

	@Override
	public List<GeoRadius> geoRadius(final byte[] key, final double longitude, final double latitude,
			final double radius, final GeoUnit unit){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put(
				"longitude", longitude).put("latitude", latitude).put("radius", radius).put("unit", unit);

		if(isTransaction()){
			return execute((cmd)->JedisClientUtils.listGeoRadiusDeconvert(getTransaction().georadius(key, longitude,
					latitude, radius, JedisClientUtils.geoUnitConvert(unit)).get()), ProtocolCommand.GEORADIUS, args);
		}else{
			return execute((cmd)->JedisClientUtils.listGeoRadiusDeconvert(cmd.georadius(key, longitude, latitude,
					radius, JedisClientUtils.geoUnitConvert(unit))), ProtocolCommand.GEORADIUS, args);
		}
	}

	@Override
	public List<GeoRadius> geoRadius(final byte[] key, final double longitude, final double latitude,
			final double radius, final GeoUnit unit, final GeoCommands.GeoArgument geoArgument){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put(
				"longitude", longitude).put("latitude", latitude).put("radius", radius).put("unit", unit).put(
						"geoArgument", geoArgument);

		if(isTransaction()){
			return execute((cmd)->JedisClientUtils.listGeoRadiusDeconvert(getTransaction().georadius(key, longitude,
					latitude, radius, JedisClientUtils.geoUnitConvert(unit),
					JedisClientUtils.geoArgumentConvert(geoArgument)).get()), ProtocolCommand.GEORADIUS, args);
		}else{
			return execute((cmd)->JedisClientUtils.listGeoRadiusDeconvert(cmd.georadius(key, longitude, latitude,
					radius, JedisClientUtils.geoUnitConvert(unit), JedisClientUtils.geoArgumentConvert(geoArgument))),
					ProtocolCommand.GEORADIUS, args);
		}
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final byte[] key, final byte[] member, final double radius,
			final GeoUnit unit){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("member",
				member).put("radius", radius).put("unit", unit);

		if(isTransaction()){
			return execute((cmd)->JedisClientUtils.listGeoRadiusDeconvert(getTransaction().georadiusByMember(key,
					member, radius, JedisClientUtils.geoUnitConvert(unit)).get()), ProtocolCommand.GEORADIUSBYMEMBER,
					args);
		}else{
			return execute((cmd)->JedisClientUtils.listGeoRadiusDeconvert(cmd.georadiusByMember(key, member, radius,
					JedisClientUtils.geoUnitConvert(unit))), ProtocolCommand.GEORADIUSBYMEMBER, args);
		}
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final byte[] key, final byte[] member, final double radius,
			final GeoUnit unit, final GeoCommands.GeoArgument geoArgument){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("member",
				member).put("radius", radius).put("unit", unit).put("geoArgument", geoArgument);

		if(isTransaction()){
			return execute((cmd)->JedisClientUtils.listGeoRadiusDeconvert(getTransaction().georadiusByMember(key,
					member, radius, JedisClientUtils.geoUnitConvert(unit),
					JedisClientUtils.geoArgumentConvert(geoArgument)).get()), ProtocolCommand.GEORADIUSBYMEMBER, args);
		}else{
			return execute((cmd)->JedisClientUtils.listGeoRadiusDeconvert(cmd.georadiusByMember(key, member, radius,
					JedisClientUtils.geoUnitConvert(unit), JedisClientUtils.geoArgumentConvert(geoArgument))),
					ProtocolCommand.GEORADIUSBYMEMBER, args);
		}
	}

	@Override
	public List<byte[]> geoHash(final byte[] key, final byte[]... members){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("members"
				, members);

		if(isTransaction()){
			return execute((cmd)->getTransaction().geohash(key, members).get(), ProtocolCommand.GEOHASH, args);
		}else{
			return execute((cmd)->cmd.geohash(key, members), ProtocolCommand.GEOHASH, args);
		}
	}

}
