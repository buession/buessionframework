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
import com.buession.redis.client.jedis.JedisRedisClient;
import com.buession.redis.core.GeoRadius;
import com.buession.redis.core.GeoUnit;
import redis.clients.jedis.GeoCoordinate;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.params.GeoRadiusParam;

import java.util.List;
import java.util.Map;

/**
 * @author Yong.Teng
 */
public class JedisGeoOperations extends AbstractGeoOperations<Jedis, Pipeline> {

	public JedisGeoOperations(final JedisRedisClient<Jedis> client){
		super(client);
	}

	@Override
	public Long geoAdd(final byte[] key, final byte[] member, final double longitude, final double latitude){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().geoadd(key, longitude, latitude, member)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().geoadd(key, longitude, latitude,
					member)));
		}else{
			return execute((cmd)->cmd.geoadd(key, longitude, latitude, member));
		}
	}

	@Override
	public Long geoAdd(final byte[] key, final Map<byte[], Geo> memberCoordinates){
		final Map<byte[], GeoCoordinate> memberCoordinateMap =
				BINARY_MAP_GEOMAP_JEDIS_CONVERTER.convert(memberCoordinates);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().geoadd(key, memberCoordinateMap)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().geoadd(key, memberCoordinateMap)));
		}else{
			return execute((cmd)->cmd.geoadd(key, memberCoordinateMap));
		}
	}

	@Override
	public List<byte[]> geoHash(final byte[] key, final byte[]... members){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().geohash(key, members)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().geohash(key, members)));
		}else{
			return execute((cmd)->cmd.geohash(key, members));
		}
	}

	@Override
	public List<Geo> geoPos(final byte[] key, final byte[]... members){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().geopos(key, members),
					LIST_GEO_EXPOSE_CONVERTER));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().geopos(key, members),
					LIST_GEO_EXPOSE_CONVERTER));
		}else{
			return execute((cmd)->LIST_GEO_EXPOSE_CONVERTER.convert(cmd.geopos(key, members)));
		}
	}

	@Override
	public Double geoDist(final byte[] key, final byte[] member1, final byte[] member2){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().geodist(key, member1, member2)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().geodist(key, member1, member2)));
		}else{
			return execute((cmd)->cmd.geodist(key, member1, member2));
		}
	}

	@Override
	public Double geoDist(final byte[] key, final byte[] member1, final byte[] member2, final GeoUnit unit){
		final redis.clients.jedis.GeoUnit geoUnit = GEO_UNIT_JEDIS_CONVERTER.convert(unit);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().geodist(key, member1, member2, geoUnit)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().geodist(key, member1, member2, geoUnit)));
		}else{
			return execute((cmd)->cmd.geodist(key, member1, member2, geoUnit));
		}
	}

	@Override
	public List<GeoRadius> geoRadius(final byte[] key, final double longitude, final double latitude,
			final double radius, final GeoUnit unit){
		final redis.clients.jedis.GeoUnit geoUnit = GEO_UNIT_JEDIS_CONVERTER.convert(unit);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().georadius(key, longitude, latitude, radius,
					geoUnit), LIST_GEO_RADIUS_EXPOSE_CONVERTER));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().georadius(key, longitude, latitude,
					radius, geoUnit), LIST_GEO_RADIUS_EXPOSE_CONVERTER));
		}else{
			return execute((cmd)->LIST_GEO_RADIUS_EXPOSE_CONVERTER.convert(cmd.georadius(key, longitude, latitude,
					radius, geoUnit)));
		}
	}

	@Override
	public List<GeoRadius> geoRadius(final byte[] key, final double longitude, final double latitude,
			final double radius, final GeoUnit unit, final GeoRadiusArgument geoRadiusArgument){
		final redis.clients.jedis.GeoUnit geoUnit = GEO_UNIT_JEDIS_CONVERTER.convert(unit);
		final GeoRadiusParam geoRadiusParam = GEO_RADIUS_ARGUMENT_JEDIS_CONVERTER.convert(geoRadiusArgument);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().georadius(key, longitude, latitude, radius,
					geoUnit, geoRadiusParam), LIST_GEO_RADIUS_EXPOSE_CONVERTER));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().georadius(key, longitude, latitude,
					radius, geoUnit, geoRadiusParam), LIST_GEO_RADIUS_EXPOSE_CONVERTER));
		}else{
			return execute((cmd)->LIST_GEO_RADIUS_EXPOSE_CONVERTER.convert(cmd.georadius(key, longitude, latitude,
					radius, geoUnit, geoRadiusParam)));
		}
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final byte[] key, final byte[] member, final double radius,
			final GeoUnit unit){
		final redis.clients.jedis.GeoUnit geoUnit = GEO_UNIT_JEDIS_CONVERTER.convert(unit);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().georadiusByMember(key, member, radius, geoUnit)
					, LIST_GEO_RADIUS_EXPOSE_CONVERTER));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().georadiusByMember(key, member, radius,
					geoUnit), LIST_GEO_RADIUS_EXPOSE_CONVERTER));
		}else{
			return execute((cmd)->LIST_GEO_RADIUS_EXPOSE_CONVERTER.convert(cmd.georadiusByMember(key, member, radius,
					geoUnit)));
		}
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final byte[] key, final byte[] member, final double radius,
			final GeoUnit unit, final GeoRadiusArgument geoRadiusArgument){
		final redis.clients.jedis.GeoUnit geoUnit = GEO_UNIT_JEDIS_CONVERTER.convert(unit);
		final GeoRadiusParam geoRadiusParam = GEO_RADIUS_ARGUMENT_JEDIS_CONVERTER.convert(geoRadiusArgument);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().georadiusByMember(key, member, radius, geoUnit,
					geoRadiusParam), LIST_GEO_RADIUS_EXPOSE_CONVERTER));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().georadiusByMember(key, member, radius,
					geoUnit, geoRadiusParam), LIST_GEO_RADIUS_EXPOSE_CONVERTER));
		}else{
			return execute((cmd)->LIST_GEO_RADIUS_EXPOSE_CONVERTER.convert(cmd.georadiusByMember(key, member, radius,
					geoUnit, geoRadiusParam)));
		}
	}

}
