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

import com.buession.core.converter.Converter;
import com.buession.core.converter.MapConverter;
import com.buession.core.converter.ListConverter;
import com.buession.lang.Geo;
import com.buession.redis.client.jedis.JedisRedisClient;
import com.buession.redis.client.operations.GeoOperations;
import com.buession.redis.core.RedisMode;
import com.buession.redis.core.GeoRadius;
import com.buession.redis.core.GeoUnit;
import com.buession.redis.core.command.GeoCommands;
import com.buession.redis.core.convert.JedisConverters;
import redis.clients.jedis.GeoCoordinate;
import redis.clients.jedis.GeoRadiusResponse;
import redis.clients.jedis.PipelineBase;
import redis.clients.jedis.commands.JedisCommands;
import redis.clients.jedis.params.GeoRadiusParam;

import java.util.List;
import java.util.Map;

/**
 * @author Yong.Teng
 */
public abstract class AbstractGeoOperations<C extends JedisCommands, P extends PipelineBase> extends AbstractJedisRedisClientOperations<C, P> implements GeoOperations<C> {

	protected final static MapConverter<String, Geo, String, GeoCoordinate> STRING_MAP_GEOMAP_JEDIS_CONVERTER =
			JedisConverters.mapGeoMapJedisConverter();

	protected final static MapConverter<byte[], Geo, byte[], GeoCoordinate> BINARY_MAP_GEOMAP_JEDIS_CONVERTER =
			JedisConverters.mapGeoMapJedisConverter();

	protected final static Converter<GeoRadiusArgument, GeoRadiusParam> GEO_RADIUS_ARGUMENT_JEDIS_CONVERTER =
			JedisConverters.geoRadiusArgumentJedisConverter();

	protected final static ListConverter<GeoRadiusResponse, GeoRadius> LIST_GEO_RADIUS_EXPOSE_CONVERTER =
			JedisConverters.listGeoRadiusExposeConverter();

	protected final static ListConverter<GeoCoordinate, Geo> LIST_GEO_EXPOSE_CONVERTER =
			JedisConverters.listGeoExposeConverter();

	protected final static Converter<GeoUnit, redis.clients.jedis.GeoUnit> GEO_UNIT_JEDIS_CONVERTER =
			JedisConverters.geoUnitJedisConverter();

	public AbstractGeoOperations(final JedisRedisClient<C> client, final RedisMode redisMode){
		super(client, redisMode);
	}

	@Override
	public Long geoAdd(final String key, final String member, final double longitude, final double latitude){
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
	public Long geoAdd(final String key, final Map<String, Geo> memberCoordinates){
		final Map<String, GeoCoordinate> memberCoordinateMap =
				STRING_MAP_GEOMAP_JEDIS_CONVERTER.convert(memberCoordinates);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().geoadd(key, memberCoordinateMap)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().geoadd(key, memberCoordinateMap)));
		}else{
			return execute((cmd)->cmd.geoadd(key, memberCoordinateMap));
		}
	}

	@Override
	public List<String> geoHash(final String key, final String... members){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().geohash(key, members)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().geohash(key, members)));
		}else{
			return execute((cmd)->cmd.geohash(key, members));
		}
	}

	@Override
	public List<Geo> geoPos(final String key, final String... members){
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
	public Double geoDist(final String key, final String member1, final String member2){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().geodist(key, member1, member2)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().geodist(key, member1, member2)));
		}else{
			return execute((cmd)->cmd.geodist(key, member1, member2));
		}
	}

	@Override
	public Double geoDist(final String key, final String member1, final String member2, final GeoUnit unit){
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
	public List<GeoRadius> geoRadius(final String key, final double longitude, final double latitude,
			final double radius){
		return geoRadius(key, longitude, longitude, radius, GeoUnit.M);
	}

	@Override
	public List<GeoRadius> geoRadius(final byte[] key, final double longitude, final double latitude,
			final double radius){
		return geoRadius(key, longitude, longitude, radius, GeoUnit.M);
	}

	@Override
	public List<GeoRadius> geoRadius(final String key, final double longitude, final double latitude,
			final double radius, final GeoRadiusArgument geoRadiusArgument){
		return geoRadius(key, longitude, latitude, radius, GeoUnit.M, geoRadiusArgument);
	}

	@Override
	public List<GeoRadius> geoRadius(final byte[] key, final double longitude, final double latitude,
			final double radius, final GeoRadiusArgument geoRadiusArgument){
		return geoRadius(key, longitude, latitude, radius, GeoUnit.M, geoRadiusArgument);
	}

	@Override
	public List<GeoRadius> geoRadius(final String key, final double longitude, final double latitude,
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
	public List<GeoRadius> geoRadius(final String key, final double longitude, final double latitude,
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
	public List<GeoRadius> geoRadiusByMember(final String key, final String member, final double radius){
		return geoRadiusByMember(key, member, radius, GeoUnit.M);
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final byte[] key, final byte[] member, final double radius){
		return geoRadiusByMember(key, member, radius, GeoUnit.M);
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final String key, final String member, final double radius,
			final GeoRadiusArgument geoRadiusArgument){
		return geoRadiusByMember(key, member, radius, GeoUnit.M, geoRadiusArgument);
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final byte[] key, final byte[] member, final double radius,
			final GeoRadiusArgument geoRadiusArgument){
		return geoRadiusByMember(key, member, radius, GeoUnit.M, geoRadiusArgument);
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final String key, final String member, final double radius,
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
	public List<GeoRadius> geoRadiusByMember(final String key, final String member, final double radius,
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
