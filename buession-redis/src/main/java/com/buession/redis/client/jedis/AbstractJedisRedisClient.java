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
package com.buession.redis.client.jedis;

import com.buession.core.Executor;
import com.buession.core.converter.HashConverter;
import com.buession.core.converter.ListConverter;
import com.buession.core.converter.SetConverter;
import com.buession.lang.Geo;
import com.buession.lang.Status;
import com.buession.redis.client.AbstractRedisClient;
import com.buession.redis.client.connection.RedisConnection;
import com.buession.redis.core.Aggregate;
import com.buession.redis.core.BitOperation;
import com.buession.redis.core.GeoRadius;
import com.buession.redis.core.GeoUnit;
import com.buession.redis.core.JedisScanParams;
import com.buession.redis.core.ListPosition;
import com.buession.redis.core.MigrateOperation;
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.Tuple;
import com.buession.redis.core.Type;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.convert.JedisConverters;
import com.buession.redis.exception.NotSupportedTransactionCommandException;
import com.buession.redis.exception.RedisException;
import com.buession.redis.transaction.jedis.JedisTransaction;
import com.buession.redis.utils.ReturnUtils;
import com.buession.redis.utils.SafeEncoder;
import org.springframework.core.convert.converter.Converter;
import redis.clients.jedis.BitOP;
import redis.clients.jedis.BitPosParams;
import redis.clients.jedis.GeoCoordinate;
import redis.clients.jedis.GeoRadiusResponse;
import redis.clients.jedis.SortingParams;
import redis.clients.jedis.ZParams;
import redis.clients.jedis.commands.JedisCommands;
import redis.clients.jedis.params.GeoRadiusParam;
import redis.clients.jedis.params.MigrateParams;
import redis.clients.jedis.params.SetParams;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Yong.Teng
 */
public abstract class AbstractJedisRedisClient<C extends JedisCommands> extends AbstractRedisClient implements JedisRedisClient<C> {

	protected final static Converter<Aggregate, ZParams.Aggregate> AGGREGATE_JEDIS_CONVERTER =
			JedisConverters.aggregateJedisConverter();

	protected final static Converter<BitOperation, BitOP> BIT_OPERATION_JEDIS_CONVERTER =
			JedisConverters.bitOperationJedisConverter();

	protected final static Converter<ListPosition, redis.clients.jedis.ListPosition> LISTPOSITION_JEDIS_CONVERTER =
			JedisConverters.listPositionJedisConverter();

	protected final static Converter<MigrateOperation, MigrateParams> MIGRATE_OPERATION_JEDIS_CONVERTER =
			JedisConverters.migrateOperationJedisConverter();

	protected final static Converter<GeoUnit, redis.clients.jedis.GeoUnit> GEO_UNIT_JEDIS_CONVERTER =
			JedisConverters.geoUnitJedisConverter();

	protected final static Converter<GeoRadiusArgument, GeoRadiusParam> GEO_RADIUS_ARGUMENT_JEDIS_CONVERTER =
			JedisConverters.geoRadiusArgumentJedisConverter();

	protected final static ListConverter<GeoRadiusResponse, GeoRadius> LIST_GEO_RADIUS_EXPOSE_CONVERTER =
			JedisConverters.listGeoRadiusExposeConverter();

	protected final static ListConverter<GeoCoordinate, Geo> LIST_GEO_EXPOSE_CONVERTER =
			JedisConverters.listGeoExposeConverter();

	protected final static HashConverter<String, Geo, String, GeoCoordinate> STRING_MAP_GEOMAP_JEDIS_CONVERTER =
			JedisConverters.mapGeoMapJedisConverter();

	protected final static HashConverter<byte[], Geo, byte[], GeoCoordinate> BINARY_MAP_GEOMAP_JEDIS_CONVERTER =
			JedisConverters.mapGeoMapJedisConverter();

	protected final static Converter<redis.clients.jedis.Tuple, Tuple> TUPLE_EXPOSE_CONVERTER =
			JedisConverters.tupleExposeConverter();

	protected final static SetConverter<redis.clients.jedis.Tuple, Tuple> SET_TUPLE_EXPOSE_CONVERTER =
			JedisConverters.setTupleExposeConverter();

	protected final static Converter<SortArgument, SortingParams> SORT_ARGUMENT_JEDIS_CONVERTER =
			JedisConverters.sortArgumentJedisConverter();

	protected final static Converter<SetArgument, SetParams> SET_ARGUMENT_JEDIS_CONVERTER =
			JedisConverters.setArgumentJedisConverter();

	protected final static Converter<redis.clients.jedis.ScanResult<Map.Entry<String, String>>, ScanResult<Map<String,
			String>>> STRING_MAP_SCANRESULT_EXPOSE_CONVERTER = JedisConverters.mapScanResultExposeConverter();

	protected final static Converter<redis.clients.jedis.ScanResult<Map.Entry<byte[], byte[]>>, ScanResult<Map<byte[],
			byte[]>>> BINARY_MAP_SCANRESULT_EXPOSE_CONVERTER = JedisConverters.mapScanResultExposeConverter();

	protected final static Converter<redis.clients.jedis.ScanResult<String>, ScanResult<List<String>>> STRING_LIST_SCANRESULT_EXPOSE_CONVERTER = JedisConverters.listScanResultExposeConverter();

	protected final static Converter<redis.clients.jedis.ScanResult<byte[]>, ScanResult<List<byte[]>>> BINARY_LIST_SCANRESULT_EXPOSE_CONVERTER = JedisConverters.listScanResultExposeConverter();

	protected final static Converter<redis.clients.jedis.ScanResult<redis.clients.jedis.Tuple>,
			ScanResult<List<Tuple>>> LIST_TUPLE_SCANRESULT_EXPOSE_CONVERTER =
			JedisConverters.listTupleScanResultExposeConverter();

	public AbstractJedisRedisClient(){
		super();
	}

	public AbstractJedisRedisClient(RedisConnection connection){
		super(connection);
	}

	@Override
	public String echo(final String str){
		if(isTransaction()){
			return transactionExecute((cmd)->getTransaction().echo(str));
		}else{
			return execute((cmd)->cmd.echo(str));
		}
	}

	@Override
	public Long geoAdd(final String key, final String member, final double longitude, final double latitude){
		if(isTransaction()){
			return transactionExecute((cmd)->getTransaction().geoadd(key, longitude, latitude, member));
		}else{
			return execute((cmd)->cmd.geoadd(key, longitude, latitude, member));
		}
	}

	@Override
	public Long geoAdd(final String key, final Map<String, Geo> memberCoordinates){
		final Map<String, GeoCoordinate> memberCoordinateMap =
				STRING_MAP_GEOMAP_JEDIS_CONVERTER.convert(memberCoordinates);

		if(isTransaction()){
			return transactionExecute((cmd)->getTransaction().geoadd(key, memberCoordinateMap));
		}else{
			return execute((cmd)->cmd.geoadd(key, memberCoordinateMap));
		}
	}

	@Override
	public List<String> geoHash(final String key, final String... members){
		if(isTransaction()){
			return transactionExecute((cmd)->getTransaction().geohash(key, members));
		}else{
			return execute((cmd)->cmd.geohash(key, members));
		}
	}

	@Override
	public List<Geo> geoPos(final String key, final String... members){
		if(isTransaction()){
			return transactionExecute((cmd)->getTransaction().geopos(key, members));
		}else{
			return execute((cmd)->LIST_GEO_EXPOSE_CONVERTER.convert(cmd.geopos(key, members)));
		}
	}

	@Override
	public Double geoDist(final String key, final String member1, final String member2){
		if(isTransaction()){
			return transactionExecute((cmd)->getTransaction().geodist(key, member1, member2));
		}else{
			return execute((cmd)->cmd.geodist(key, member1, member2));
		}
	}

	@Override
	public Double geoDist(final String key, final String member1, final String member2, final GeoUnit unit){
		final redis.clients.jedis.GeoUnit geoUnit = GEO_UNIT_JEDIS_CONVERTER.convert(unit);

		if(isTransaction()){
			return transactionExecute((cmd)->getTransaction().geodist(key, member1, member2, geoUnit));
		}else{
			return execute((cmd)->cmd.geodist(key, member1, member2, geoUnit));
		}
	}

	@Override
	public List<GeoRadius> geoRadius(final String key, final double longitude, final double latitude,
			final double radius, final GeoUnit unit){
		final redis.clients.jedis.GeoUnit geoUnit = GEO_UNIT_JEDIS_CONVERTER.convert(unit);

		if(isTransaction()){
			return transactionExecute((cmd)->getTransaction().georadius(key, longitude, latitude, radius, geoUnit));
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

		if(isTransaction()){
			return transactionExecute((cmd)->getTransaction().georadius(key, longitude, latitude, radius, geoUnit,
					geoRadiusParam));
		}else{
			return execute((cmd)->LIST_GEO_RADIUS_EXPOSE_CONVERTER.convert(cmd.georadius(key, longitude, latitude,
					radius, geoUnit, geoRadiusParam)));
		}
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final String key, final String member, final double radius,
			final GeoUnit unit){
		final redis.clients.jedis.GeoUnit geoUnit = GEO_UNIT_JEDIS_CONVERTER.convert(unit);

		if(isTransaction()){
			return transactionExecute((cmd)->getTransaction().georadiusByMember(key, member, radius, geoUnit));
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

		if(isTransaction()){
			return transactionExecute((cmd)->getTransaction().georadiusByMember(key, member, radius, geoUnit,
					geoRadiusParam));
		}else{
			return execute((cmd)->LIST_GEO_RADIUS_EXPOSE_CONVERTER.convert(cmd.georadiusByMember(key, member, radius,
					geoUnit, geoRadiusParam)));
		}
	}

	@Override
	public Long hDel(final String key, final String... fields){
		if(isTransaction()){
			return transactionExecute((cmd)->getTransaction().hdel(key, fields));
		}else{
			return execute((cmd)->cmd.hdel(key, fields));
		}
	}

	@Override
	public boolean hExists(final String key, final String field){
		if(isTransaction()){
			return transactionExecute((cmd)->getTransaction().hexists(key, field));
		}else{
			return execute((cmd)->cmd.hexists(key, field));
		}
	}

	@Override
	public String hGet(final String key, final String field){
		if(isTransaction()){
			return transactionExecute((cmd)->getTransaction().hget(key, field));
		}else{
			return execute((cmd)->cmd.hget(key, field));
		}
	}

	@Override
	public Map<String, String> hGetAll(final String key){
		if(isTransaction()){
			return transactionExecute((cmd)->getTransaction().hgetAll(key));
		}else{
			return execute((cmd)->cmd.hgetAll(key));
		}
	}

	@Override
	public Long hIncrBy(final String key, final String field, final long value){
		if(isTransaction()){
			return transactionExecute((cmd)->getTransaction().hincrBy(key, field, value));
		}else{
			return execute((cmd)->cmd.hincrBy(key, field, value));
		}
	}

	@Override
	public Double hIncrByFloat(final String key, final String field, final double value){
		if(isTransaction()){
			return transactionExecute((cmd)->getTransaction().hincrByFloat(key, field, value));
		}else{
			return execute((cmd)->cmd.hincrByFloat(key, field, value));
		}
	}

	@Override
	public Set<String> hKeys(final String key){
		if(isTransaction()){
			return transactionExecute((cmd)->getTransaction().hkeys(key));
		}else{
			return execute((cmd)->cmd.hkeys(key));
		}
	}

	@Override
	public Long hLen(final String key){
		if(isTransaction()){
			return transactionExecute((cmd)->getTransaction().hlen(key));
		}else{
			return execute((cmd)->cmd.hlen(key));
		}
	}

	@Override
	public List<String> hMGet(final String key, final String... fields){
		if(isTransaction()){
			return transactionExecute((cmd)->getTransaction().hmget(key, fields));
		}else{
			return execute((cmd)->cmd.hmget(key, fields));
		}
	}

	@Override
	public Status hMSet(final String key, final Map<String, String> data){
		if(isTransaction()){
			return transactionExecute((cmd)->getTransaction().hmset(key, data).get());
		}else{
			return execute((cmd)->ReturnUtils.statusForOK(cmd.hmset(key, data)));
		}
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final String cursor){
		if(isTransaction()){
			throw new NotSupportedTransactionCommandException(ProtocolCommand.HSCAN);
		}else{
			return execute((cmd)->STRING_MAP_SCANRESULT_EXPOSE_CONVERTER.convert(cmd.hscan(key, cursor)));
		}
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final String cursor, final String pattern){
		if(isTransaction()){
			throw new NotSupportedTransactionCommandException(ProtocolCommand.HSCAN);
		}else{
			return execute((cmd)->STRING_MAP_SCANRESULT_EXPOSE_CONVERTER.convert(cmd.hscan(key, cursor,
					new JedisScanParams(pattern))));
		}
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final String cursor, final int count){
		if(isTransaction()){
			throw new NotSupportedTransactionCommandException(ProtocolCommand.HSCAN);
		}else{
			return execute((cmd)->STRING_MAP_SCANRESULT_EXPOSE_CONVERTER.convert(cmd.hscan(key, cursor,
					new JedisScanParams(count))));
		}
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final String cursor, final String pattern,
			final int count){
		if(isTransaction()){
			throw new NotSupportedTransactionCommandException(ProtocolCommand.HSCAN);
		}else{
			return execute((cmd)->STRING_MAP_SCANRESULT_EXPOSE_CONVERTER.convert(cmd.hscan(key, cursor,
					new JedisScanParams(pattern, count))));
		}
	}

	@Override
	public Status hSet(final String key, final String field, final String value){
		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForBool(getTransaction().hset(key, field, value).get() > 0));
		}else{
			return execute((cmd)->ReturnUtils.statusForBool(cmd.hset(key, field, value) > 0));
		}
	}

	@Override
	public Status hSetNx(final String key, final String field, final String value){
		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForBool(getTransaction().hsetnx(key, field, value).get() > 0));
		}else{
			return execute((cmd)->ReturnUtils.statusForBool(cmd.hsetnx(key, field, value) > 0));
		}
	}

	@Override
	public Long hStrLen(final String key, final String field){
		if(isTransaction()){
			return execute((cmd)->getTransaction().hstrlen(key, field).get());
		}else{
			return execute((cmd)->cmd.hstrlen(key, field));
		}
	}

	@Override
	public List<String> hVals(final String key){
		if(isTransaction()){
			return execute((cmd)->getTransaction().hvals(key).get());
		}else{
			return execute((cmd)->cmd.hvals(key));
		}
	}

	@Override
	public Status pfAdd(final String key, final String... elements){
		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForBool(getTransaction().pfadd(key, elements).get() > 0));
		}else{
			return execute((cmd)->ReturnUtils.statusForBool(cmd.pfadd(key, elements) > 0));
		}
	}

	@Override
	public byte[] dump(final String key){
		if(isTransaction()){
			return execute((cmd)->getTransaction().dump(key).get());
		}else{
			return execute((cmd)->cmd.dump(key));
		}
	}

	@Override
	public boolean exists(final String key){
		if(isTransaction()){
			return execute((cmd)->getTransaction().exists(key).get());
		}else{
			return execute((cmd)->cmd.exists(key));
		}
	}

	@Override
	public Status expire(final String key, final int lifetime){
		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForBool(getTransaction().expire(key, lifetime).get() == 1));
		}else{
			return execute((cmd)->ReturnUtils.statusForBool(cmd.expire(key, lifetime) == 1));
		}
	}

	@Override
	public Status expireAt(final String key, final long unixTimestamp){
		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForBool(getTransaction().expireAt(key, unixTimestamp).get() == 1));
		}else{
			return execute((cmd)->ReturnUtils.statusForBool(cmd.expireAt(key, unixTimestamp) == 1));
		}
	}

	@Override
	public Status move(final String key, final int db){
		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForBool(getTransaction().move(key, db).get() > 0));
		}else{
			return execute((cmd)->ReturnUtils.statusForBool(cmd.move(key, db) > 0));
		}
	}

	@Override
	public Status persist(final String key){
		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForBool(getTransaction().persist(key).get() > 0));
		}else{
			return execute((cmd)->ReturnUtils.statusForBool(cmd.persist(key) > 0));
		}
	}

	@Override
	public Status pExpire(final String key, final int lifetime){
		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForBool(getTransaction().pexpire(key, lifetime).get() == 1));
		}else{
			return execute((cmd)->ReturnUtils.statusForBool(cmd.pexpire(key, lifetime) == 1));
		}
	}

	@Override
	public Status pExpireAt(final String key, final long unixTimestamp){
		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForBool(getTransaction().pexpireAt(key, unixTimestamp).get() == 1));
		}else{
			return execute((cmd)->ReturnUtils.statusForBool(cmd.pexpireAt(key, unixTimestamp) == 1));
		}
	}

	@Override
	public Long pTtl(final String key){
		if(isTransaction()){
			return execute((cmd)->getTransaction().pttl(key).get());
		}else{
			return execute((cmd)->cmd.pttl(key));
		}
	}

	@Override
	public Status restore(final String key, final String serializedValue, final int ttl){
		final byte[] serializedEncodeValue = SafeEncoder.encode(serializedValue);

		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForOK(getTransaction().restore(key, ttl, serializedEncodeValue).get()));
		}else{
			return execute((cmd)->ReturnUtils.statusForOK(cmd.restore(key, ttl, serializedEncodeValue)));
		}
	}

	@Override
	public List<String> sort(final String key){
		if(isTransaction()){
			return execute((cmd)->getTransaction().sort(key).get());
		}else{
			return execute((cmd)->cmd.sort(key));
		}
	}

	@Override
	public List<String> sort(final String key, final SortArgument sortArgument){
		final SortingParams soringParams = SORT_ARGUMENT_JEDIS_CONVERTER.convert(sortArgument);

		if(isTransaction()){
			return execute((cmd)->getTransaction().sort(key, soringParams).get());
		}else{
			return execute((cmd)->cmd.sort(key, soringParams));
		}
	}

	@Override
	public Long ttl(final String key){
		if(isTransaction()){
			return execute((cmd)->getTransaction().ttl(key).get());
		}else{
			return execute((cmd)->cmd.ttl(key));
		}
	}

	@Override
	public Type type(final String key){
		if(isTransaction()){
			return execute((cmd)->ReturnUtils.enumValueOf(getTransaction().type(key).get(), Type.class));
		}else{
			return execute((cmd)->ReturnUtils.enumValueOf(cmd.type(key), Type.class));
		}
	}

	@Override
	public String lIndex(final String key, final long index){
		if(isTransaction()){
			return execute((cmd)->getTransaction().lindex(key, index).get());
		}else{
			return execute((cmd)->cmd.lindex(key, index));
		}
	}

	@Override
	public Long lInsert(final String key, final String value, final ListPosition position, final String pivot){
		final redis.clients.jedis.ListPosition pos = LISTPOSITION_JEDIS_CONVERTER.convert(position);

		if(isTransaction()){
			return execute((cmd)->getTransaction().linsert(key, pos, pivot, value).get());
		}else{
			return execute((cmd)->cmd.linsert(key, pos, pivot, value));
		}
	}

	@Override
	public Long lLen(final String key){
		if(isTransaction()){
			return execute((cmd)->getTransaction().llen(key).get());
		}else{
			return execute((cmd)->cmd.llen(key));
		}
	}

	@Override
	public String lPop(final String key){
		if(isTransaction()){
			return execute((cmd)->getTransaction().lpop(key).get());
		}else{
			return execute((cmd)->cmd.lpop(key));
		}
	}

	@Override
	public Long lPush(final String key, final String... values){
		if(isTransaction()){
			return execute((cmd)->getTransaction().lpush(key, values).get());
		}else{
			return execute((cmd)->cmd.lpush(key, values));
		}
	}

	@Override
	public Long lPushX(final String key, final String... values){
		if(isTransaction()){
			return execute((cmd)->getTransaction().lpushx(key, values).get());
		}else{
			return execute((cmd)->cmd.lpushx(key, values));
		}
	}

	@Override
	public List<String> lRange(final String key, final long start, final long end){
		if(isTransaction()){
			return execute((cmd)->getTransaction().lrange(key, start, end).get());
		}else{
			return execute((cmd)->cmd.lrange(key, start, end));
		}
	}

	@Override
	public Long lRem(final String key, final String value, final long count){
		if(isTransaction()){
			return execute((cmd)->getTransaction().lrem(key, count, value).get());
		}else{
			return execute((cmd)->cmd.lrem(key, count, value));
		}
	}

	@Override
	public Status lSet(final String key, final long index, final String value){
		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForOK(getTransaction().lset(key, index, value).get()));
		}else{
			return execute((cmd)->ReturnUtils.statusForOK(cmd.lset(key, index, value)));
		}
	}

	@Override
	public Status lTrim(final String key, final long start, final long end){
		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForOK(getTransaction().ltrim(key, start, end).get()));
		}else{
			return execute((cmd)->ReturnUtils.statusForOK(cmd.ltrim(key, start, end)));
		}
	}

	@Override
	public String rPop(final String key){
		if(isTransaction()){
			return execute((cmd)->getTransaction().rpop(key).get());
		}else{
			return execute((cmd)->cmd.rpop(key));
		}
	}

	@Override
	public Long rPush(final String key, final String... values){
		if(isTransaction()){
			return execute((cmd)->getTransaction().rpush(key, values).get());
		}else{
			return execute((cmd)->cmd.rpush(key, values));
		}
	}

	@Override
	public Long rPushX(final String key, final String... values){
		if(isTransaction()){
			return execute((cmd)->getTransaction().rpushx(key, values).get());
		}else{
			return execute((cmd)->cmd.rpushx(key, values));
		}
	}

	@Override
	public Long sAdd(final String key, final String... members){
		if(isTransaction()){
			return execute((cmd)->getTransaction().sadd(key, members).get());
		}else{
			return execute((cmd)->cmd.sadd(key, members));
		}
	}

	@Override
	public Long sCard(final String key){
		if(isTransaction()){
			return execute((cmd)->getTransaction().scard(key).get());
		}else{
			return execute((cmd)->cmd.scard(key));
		}
	}

	@Override
	public boolean sisMember(final String key, final String member){
		if(isTransaction()){
			return execute((cmd)->cmd.sismember(key, member));
		}else{
			return execute((cmd)->cmd.sismember(key, member));
		}
	}

	@Override
	public Set<String> sMembers(final String key){
		if(isTransaction()){
			return execute((cmd)->getTransaction().smembers(key).get());
		}else{
			return execute((cmd)->cmd.smembers(key));
		}
	}

	@Override
	public String sPop(final String key){
		if(isTransaction()){
			return execute((cmd)->getTransaction().spop(key).get());
		}else{
			return execute((cmd)->cmd.spop(key));
		}
	}

	@Override
	public String sRandMember(final String key){
		if(isTransaction()){
			return execute((cmd)->getTransaction().srandmember(key).get());
		}else{
			return execute((cmd)->cmd.srandmember(key));
		}
	}

	@Override
	public List<String> sRandMember(final String key, final int count){
		if(isTransaction()){
			return execute((cmd)->getTransaction().srandmember(key, count).get());
		}else{
			return execute((cmd)->cmd.srandmember(key, count));
		}
	}

	@Override
	public Long sRem(final String key, final String... members){
		if(isTransaction()){
			return execute((cmd)->getTransaction().srem(key, members).get());
		}else{
			return execute((cmd)->cmd.srem(key, members));
		}
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final String cursor){
		if(isTransaction()){
			throw new NotSupportedTransactionCommandException(ProtocolCommand.SSCAN);
		}else{
			return execute((cmd)->STRING_LIST_SCANRESULT_EXPOSE_CONVERTER.convert(cmd.sscan(key, cursor)));
		}
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final String cursor, final String pattern){
		if(isTransaction()){
			throw new NotSupportedTransactionCommandException(ProtocolCommand.SSCAN);
		}else{
			return execute((cmd)->STRING_LIST_SCANRESULT_EXPOSE_CONVERTER.convert(cmd.sscan(key, cursor,
					new JedisScanParams(pattern))));
		}
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final String cursor, final int count){
		if(isTransaction()){
			throw new NotSupportedTransactionCommandException(ProtocolCommand.SSCAN);
		}else{
			return execute((cmd)->STRING_LIST_SCANRESULT_EXPOSE_CONVERTER.convert(cmd.sscan(key, cursor,
					new JedisScanParams(count))));
		}
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final String cursor, final String pattern,
			final int count){
		if(isTransaction()){
			throw new NotSupportedTransactionCommandException(ProtocolCommand.SSCAN);
		}else{
			return execute((cmd)->STRING_LIST_SCANRESULT_EXPOSE_CONVERTER.convert(cmd.sscan(key, cursor,
					new JedisScanParams(pattern, count))));
		}
	}

	@Override
	public Long zAdd(final String key, final Map<String, Number> members){
		final Map<String, Double> data = new LinkedHashMap<>(members.size());

		members.forEach((k, v)->data.put(k, v.doubleValue()));

		if(isTransaction()){
			return execute((cmd)->getTransaction().zadd(key, data).get());
		}else{
			return execute((cmd)->cmd.zadd(key, data));
		}
	}

	@Override
	public Long zCard(final String key){
		if(isTransaction()){
			return execute((C cmd)->getTransaction().zcard(key).get());
		}else{
			return execute((C cmd)->cmd.zcard(key));
		}
	}

	@Override
	public Long zCount(final String key, final double min, final double max){
		if(isTransaction()){
			return execute((cmd)->getTransaction().zcount(key, min, max).get());
		}else{
			return execute((cmd)->cmd.zcount(key, min, max));
		}
	}

	@Override
	public Long zCount(final String key, final String min, final String max){
		if(isTransaction()){
			return execute((cmd)->getTransaction().zcount(key, min, max).get());
		}else{
			return execute((cmd)->cmd.zcount(key, min, max));
		}
	}

	@Override
	public Double zIncrBy(final String key, final String member, final double increment){
		if(isTransaction()){
			return execute((cmd)->getTransaction().zincrby(key, increment, member).get());
		}else{
			return execute((cmd)->cmd.zincrby(key, increment, member));
		}
	}

	@Override
	public Long zLexCount(final String key, final String min, final String max){
		if(isTransaction()){
			return execute((cmd)->getTransaction().zlexcount(key, min, max).get());
		}else{
			return execute((cmd)->cmd.zlexcount(key, min, max));
		}
	}

	@Override
	public Set<String> zRange(final String key, final long start, final long end){
		if(isTransaction()){
			return execute((cmd)->getTransaction().zrange(key, start, end).get());
		}else{
			return execute((cmd)->cmd.zrange(key, start, end));
		}
	}

	@Override
	public Set<Tuple> zRangeWithScores(final String key, final long start, final long end){
		if(isTransaction()){
			return execute((cmd)->SET_TUPLE_EXPOSE_CONVERTER.convert(getTransaction().zrangeWithScores(key, start,
					end).get()));
		}else{
			return execute((cmd)->SET_TUPLE_EXPOSE_CONVERTER.convert(cmd.zrangeWithScores(key, start, end)));
		}
	}

	@Override
	public Set<String> zRangeByLex(final String key, final String min, final String max){
		if(isTransaction()){
			return execute((cmd)->getTransaction().zrangeByLex(key, min, max).get());
		}else{
			return execute((cmd)->cmd.zrangeByLex(key, min, max));
		}
	}

	@Override
	public Set<String> zRangeByLex(final String key, final String min, final String max, final int offset,
			final int count){
		if(isTransaction()){
			return execute((cmd)->getTransaction().zrangeByLex(key, min, max, offset, count).get());
		}else{
			return execute((cmd)->cmd.zrangeByLex(key, min, max, offset, count));
		}
	}

	@Override
	public Set<String> zRangeByScore(final String key, final double min, final double max){
		if(isTransaction()){
			return execute((cmd)->getTransaction().zrangeByScore(key, min, max).get());
		}else{
			return execute((cmd)->cmd.zrangeByScore(key, min, max));
		}
	}

	@Override
	public Set<String> zRangeByScore(final String key, final String min, final String max){
		if(isTransaction()){
			return execute((cmd)->getTransaction().zrangeByScore(key, min, max).get());
		}else{
			return execute((cmd)->cmd.zrangeByScore(key, min, max));
		}
	}

	@Override
	public Set<String> zRangeByScore(final String key, final double min, final double max, final int offset,
			final int count){
		if(isTransaction()){
			return execute((cmd)->getTransaction().zrangeByScore(key, min, max, offset, count).get());
		}else{
			return execute((cmd)->cmd.zrangeByScore(key, min, max, offset, count));
		}
	}

	@Override
	public Set<String> zRangeByScore(final String key, final String min, final String max, final int offset,
			final int count){
		if(isTransaction()){
			return execute((cmd)->getTransaction().zrangeByScore(key, min, max, offset, count).get());
		}else{
			return execute((cmd)->cmd.zrangeByScore(key, min, max, offset, count));
		}
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final String key, final double min, final double max){
		if(isTransaction()){
			return execute((cmd)->SET_TUPLE_EXPOSE_CONVERTER.convert(getTransaction().zrangeByScoreWithScores(key, min
					, max).get()));
		}else{
			return execute((cmd)->SET_TUPLE_EXPOSE_CONVERTER.convert(cmd.zrangeByScoreWithScores(key, min, max)));
		}
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final String key, final String min, final String max){
		if(isTransaction()){
			return execute((cmd)->SET_TUPLE_EXPOSE_CONVERTER.convert(getTransaction().zrangeByScoreWithScores(key, min
					, max).get()));
		}else{
			return execute((cmd)->SET_TUPLE_EXPOSE_CONVERTER.convert(cmd.zrangeByScoreWithScores(key, min, max)));
		}
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final String key, final double min, final double max, final int offset,
			final int count){
		if(isTransaction()){
			return execute((cmd)->SET_TUPLE_EXPOSE_CONVERTER.convert(getTransaction().zrangeByScoreWithScores(key, min
					, max, offset, count).get()));
		}else{
			return execute((cmd)->SET_TUPLE_EXPOSE_CONVERTER.convert(cmd.zrangeByScoreWithScores(key, min, max, offset
					, count)));
		}
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final String key, final String min, final String max, final int offset,
			final int count){
		if(isTransaction()){
			return execute((cmd)->SET_TUPLE_EXPOSE_CONVERTER.convert(getTransaction().zrangeByScoreWithScores(key, min
					, max, offset, count).get()));
		}else{
			return execute((cmd)->SET_TUPLE_EXPOSE_CONVERTER.convert(cmd.zrangeByScoreWithScores(key, min, max, offset
					, count)));
		}
	}

	@Override
	public Long zRank(final String key, final String member){
		if(isTransaction()){
			return execute((cmd)->getTransaction().zrank(key, member).get());
		}else{
			return execute((cmd)->cmd.zrank(key, member));
		}
	}

	@Override
	public Tuple zPopMax(final String key){
		if(isTransaction()){
			return execute((cmd)->TUPLE_EXPOSE_CONVERTER.convert(getTransaction().zpopmax(key).get()));
		}else{
			return execute((cmd)->TUPLE_EXPOSE_CONVERTER.convert(cmd.zpopmax(key)));
		}
	}

	@Override
	public Set<Tuple> zPopMax(final String key, final int count){
		if(isTransaction()){
			return execute((cmd)->SET_TUPLE_EXPOSE_CONVERTER.convert(getTransaction().zpopmax(key, count).get()));
		}else{
			return execute((cmd)->SET_TUPLE_EXPOSE_CONVERTER.convert(cmd.zpopmax(key, count)));
		}
	}

	@Override
	public Tuple zPopMin(final String key){
		if(isTransaction()){
			return execute((cmd)->TUPLE_EXPOSE_CONVERTER.convert(getTransaction().zpopmin(key).get()));
		}else{
			return execute((cmd)->TUPLE_EXPOSE_CONVERTER.convert(cmd.zpopmin(key)));
		}
	}

	@Override
	public Set<Tuple> zPopMin(final String key, final int count){
		if(isTransaction()){
			return execute((cmd)->SET_TUPLE_EXPOSE_CONVERTER.convert(getTransaction().zpopmin(key, count).get()));
		}else{
			return execute((cmd)->SET_TUPLE_EXPOSE_CONVERTER.convert(cmd.zpopmin(key, count)));
		}
	}

	@Override
	public Long zRem(final String key, final String... members){
		if(isTransaction()){
			return execute((cmd)->getTransaction().zrem(key, members).get());
		}else{
			return execute((cmd)->cmd.zrem(key, members));
		}
	}

	@Override
	public Long zRemRangeByLex(final String key, final String min, final String max){
		if(isTransaction()){
			return execute((cmd)->getTransaction().zremrangeByLex(key, min, max).get());
		}else{
			return execute((cmd)->getTransaction().zremrangeByLex(key, min, max).get());
		}
	}

	@Override
	public Long zRemRangeByScore(final String key, final double min, final double max){
		if(isTransaction()){
			return execute((cmd)->getTransaction().zremrangeByScore(key, min, max).get());
		}else{
			return execute((cmd)->cmd.zremrangeByScore(key, min, max));
		}
	}

	@Override
	public Long zRemRangeByScore(final String key, final String min, final String max){
		if(isTransaction()){
			return execute((cmd)->getTransaction().zremrangeByScore(key, min, max).get());
		}else{
			return execute((cmd)->cmd.zremrangeByScore(key, min, max));
		}
	}

	@Override
	public Long zRemRangeByRank(final String key, final long start, final long end){
		if(isTransaction()){
			return execute((cmd)->getTransaction().zremrangeByRank(key, start, end).get());
		}else{
			return execute((cmd)->cmd.zremrangeByRank(key, start, end));
		}
	}

	@Override
	public Set<String> zRevRange(final String key, final long start, final long end){
		if(isTransaction()){
			return execute((cmd)->getTransaction().zrevrange(key, start, end).get());
		}else{
			return execute((cmd)->cmd.zrevrange(key, start, end));
		}
	}

	@Override
	public Set<Tuple> zRevRangeWithScores(final String key, final long start, final long end){
		if(isTransaction()){
			return execute((cmd)->SET_TUPLE_EXPOSE_CONVERTER.convert(getTransaction().zrevrangeWithScores(key, start,
					end).get()));
		}else{
			return execute((cmd)->SET_TUPLE_EXPOSE_CONVERTER.convert(cmd.zrevrangeWithScores(key, start, end)));
		}
	}

	@Override
	public Set<String> zRevRangeByLex(final String key, final String min, final String max){
		if(isTransaction()){
			return execute((cmd)->getTransaction().zrevrangeByLex(key, min, max).get());
		}else{
			return execute((cmd)->cmd.zrevrangeByLex(key, min, max));
		}
	}

	@Override
	public Set<String> zRevRangeByLex(final String key, final String min, final String max, final int offset,
			final int count){
		if(isTransaction()){
			return execute((cmd)->getTransaction().zrevrangeByLex(key, min, max, offset, count).get());
		}else{
			return execute((cmd)->cmd.zrevrangeByLex(key, min, max, offset, count));
		}
	}

	@Override
	public Set<String> zRevRangeByScore(final String key, final double min, final double max){
		if(isTransaction()){
			return execute((cmd)->getTransaction().zrevrangeByScore(key, min, max).get());
		}else{
			return execute((cmd)->cmd.zrevrangeByScore(key, min, max));
		}
	}

	@Override
	public Set<String> zRevRangeByScore(final String key, final String min, final String max){
		if(isTransaction()){
			return execute((cmd)->getTransaction().zrevrangeByScore(key, min, max).get());
		}else{
			return execute((cmd)->cmd.zrevrangeByScore(key, min, max));
		}
	}

	@Override
	public Set<String> zRevRangeByScore(final String key, final double min, final double max, final int offset,
			final int count){
		if(isTransaction()){
			return execute((cmd)->getTransaction().zrevrangeByScore(key, min, max, offset, count).get());
		}else{
			return execute((cmd)->cmd.zrevrangeByScore(key, min, max, offset, count));
		}
	}

	@Override
	public Set<String> zRevRangeByScore(final String key, final String min, final String max, final int offset,
			final int count){
		if(isTransaction()){
			return execute((cmd)->getTransaction().zrevrangeByScore(key, min, max, offset, count).get());
		}else{
			return execute((cmd)->cmd.zrevrangeByScore(key, min, max, offset, count));
		}
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final String key, final double min, final double max){
		if(isTransaction()){
			return execute((cmd)->SET_TUPLE_EXPOSE_CONVERTER.convert(getTransaction().zrevrangeByScoreWithScores(key,
					min, max).get()));
		}else{
			return execute((cmd)->SET_TUPLE_EXPOSE_CONVERTER.convert(cmd.zrevrangeByScoreWithScores(key, min, max)));
		}
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final String key, final String min, final String max){
		if(isTransaction()){
			return execute((cmd)->SET_TUPLE_EXPOSE_CONVERTER.convert(getTransaction().zrevrangeByScoreWithScores(key,
					min, max).get()));
		}else{
			return execute((cmd)->SET_TUPLE_EXPOSE_CONVERTER.convert(cmd.zrevrangeByScoreWithScores(key, min, max)));
		}
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final String key, final double min, final double max,
			final int offset, final int count){
		if(isTransaction()){
			return execute((cmd)->SET_TUPLE_EXPOSE_CONVERTER.convert(getTransaction().zrevrangeByScoreWithScores(key,
					min, max, offset, count).get()));
		}else{
			return execute((cmd)->SET_TUPLE_EXPOSE_CONVERTER.convert(cmd.zrevrangeByScoreWithScores(key, min, max,
					offset, count)));
		}
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final String key, final String min, final String max,
			final int offset, final int count){
		if(isTransaction()){
			return execute((cmd)->SET_TUPLE_EXPOSE_CONVERTER.convert(getTransaction().zrevrangeByScoreWithScores(key,
					min, max, offset, count).get()));
		}else{
			return execute((cmd)->SET_TUPLE_EXPOSE_CONVERTER.convert(cmd.zrevrangeByScoreWithScores(key, min, max,
					offset, count)));
		}
	}

	@Override
	public Long zRevRank(final String key, final String member){
		if(isTransaction()){
			return execute((cmd)->getTransaction().zrevrank(key, member).get());
		}else{
			return execute((cmd)->cmd.zrevrank(key, member));
		}
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final String cursor){
		if(isTransaction()){
			throw new NotSupportedTransactionCommandException(ProtocolCommand.ZSCAN);
		}else{
			return execute((cmd)->LIST_TUPLE_SCANRESULT_EXPOSE_CONVERTER.convert(cmd.zscan(key, cursor)));
		}
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final String cursor, final String pattern){
		if(isTransaction()){
			throw new NotSupportedTransactionCommandException(ProtocolCommand.ZSCAN);
		}else{
			return execute((cmd)->LIST_TUPLE_SCANRESULT_EXPOSE_CONVERTER.convert(cmd.zscan(key, cursor,
					new JedisScanParams(pattern))));
		}
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final String cursor, final int count){
		if(isTransaction()){
			throw new NotSupportedTransactionCommandException(ProtocolCommand.ZSCAN);
		}else{
			return execute((cmd)->LIST_TUPLE_SCANRESULT_EXPOSE_CONVERTER.convert(cmd.zscan(key, cursor,
					new JedisScanParams(count))));
		}
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final String cursor, final String pattern, final int count){
		if(isTransaction()){
			throw new NotSupportedTransactionCommandException(ProtocolCommand.ZSCAN);
		}else{
			return execute((cmd)->LIST_TUPLE_SCANRESULT_EXPOSE_CONVERTER.convert(cmd.zscan(key, cursor,
					new JedisScanParams(pattern, count))));
		}
	}

	@Override
	public Double zScore(final String key, final String member){
		if(isTransaction()){
			return execute((cmd)->getTransaction().zscore(key, member).get());
		}else{
			return execute((cmd)->cmd.zscore(key, member));
		}
	}

	@Override
	public Long append(final String key, final String value){
		if(isTransaction()){
			return execute((cmd)->getTransaction().append(key, value).get());
		}else{
			return execute((cmd)->cmd.append(key, value));
		}
	}

	@Override
	public Long bitCount(final String key){
		if(isTransaction()){
			return execute((cmd)->getTransaction().bitcount(key).get());
		}else{
			return execute((cmd)->cmd.bitcount(key));
		}
	}

	@Override
	public Long bitCount(final String key, final long start, final long end){
		if(isTransaction()){
			return execute((cmd)->getTransaction().bitcount(key, start, end).get());
		}else{
			return execute((cmd)->cmd.bitcount(key, start, end));
		}
	}

	@Override
	public List<Long> bitField(final String key, final String... arguments){
		if(isTransaction()){
			return execute((cmd)->getTransaction().bitfield(key, arguments).get());
		}else{
			return execute((cmd)->cmd.bitfield(key, arguments));
		}
	}

	@Override
	public Long bitPos(final String key, final boolean value){
		if(isTransaction()){
			return execute((cmd)->getTransaction().bitpos(key, value).get());
		}else{
			return execute((cmd)->cmd.bitpos(key, value));
		}
	}

	@Override
	public Long bitPos(final String key, final boolean value, final int start, final int end){
		if(isTransaction()){
			return execute((cmd)->getTransaction().bitpos(key, value, new BitPosParams(start, end)).get());
		}else{
			return execute((cmd)->cmd.bitpos(key, value, new BitPosParams(start, end)));
		}
	}

	@Override
	public Long decr(final String key){
		if(isTransaction()){
			return execute((cmd)->getTransaction().decr(key).get());
		}else{
			return execute((cmd)->cmd.decr(key));
		}
	}

	@Override
	public Long decrBy(final String key, final long value){
		if(isTransaction()){
			return execute((cmd)->getTransaction().decrBy(key, value).get());
		}else{
			return execute((cmd)->cmd.decrBy(key, value));
		}
	}

	@Override
	public String get(final String key){
		if(isTransaction()){
			return execute((cmd)->getTransaction().get(key).get());
		}else{
			return execute((cmd)->cmd.get(key));
		}
	}

	@Override
	public Status getBit(final String key, final long offset){
		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForBool(getTransaction().getbit(key, offset).get()));
		}else{
			return execute((cmd)->ReturnUtils.statusForBool(cmd.getbit(key, offset)));
		}
	}

	@Override
	public String getRange(final String key, final long start, final long end){
		if(isTransaction()){
			return execute((cmd)->getTransaction().getrange(key, start, end).get());
		}else{
			return execute((cmd)->cmd.getrange(key, start, end));
		}
	}

	@Override
	public String getSet(final String key, final String value){
		if(isTransaction()){
			return execute((cmd)->getTransaction().getSet(key, value).get());
		}else{
			return execute((cmd)->cmd.getSet(key, value));
		}
	}

	@Override
	public Long incr(final String key){
		if(isTransaction()){
			return execute((cmd)->getTransaction().incr(key).get());
		}else{
			return execute((cmd)->cmd.incr(key));
		}
	}

	@Override
	public Long incrBy(final String key, final long value){
		if(isTransaction()){
			return execute((cmd)->getTransaction().incrBy(key, value).get());
		}else{
			return execute((cmd)->cmd.incrBy(key, value));
		}
	}

	@Override
	public Double incrByFloat(final String key, final double value){
		if(isTransaction()){
			return execute((cmd)->getTransaction().incrByFloat(key, value).get());
		}else{
			return execute((cmd)->cmd.incrByFloat(key, value));
		}
	}

	@Override
	public Status pSetEx(final String key, final String value, final int lifetime){
		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForOK(getTransaction().psetex(key, lifetime, value).get()));
		}else{
			return execute((cmd)->ReturnUtils.statusForOK(cmd.psetex(key, lifetime, value)));
		}
	}

	@Override
	public Status set(final String key, final String value){
		if(isTransaction()){
			return transactionExecute((cmd)->getTransaction().set(key, value));
		}else{
			return execute((cmd)->ReturnUtils.statusForOK(cmd.set(key, value)));
		}
	}

	@Override
	public Status set(final String key, final String value, final SetArgument setArgument){
		final SetParams setParams = SET_ARGUMENT_JEDIS_CONVERTER.convert(setArgument);

		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForOK(getTransaction().set(key, value, setParams).get()));
		}else{
			return execute((cmd)->ReturnUtils.statusForOK(cmd.set(key, value, setParams)));
		}
	}

	@Override
	public Status setBit(final String key, final long offset, final String value){
		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForBool(getTransaction().setbit(SafeEncoder.encode(key), offset,
					SafeEncoder.encode(value)).get()));
		}else{
			return execute((cmd)->ReturnUtils.statusForBool(cmd.setbit(key, offset, value)));
		}
	}

	@Override
	public Status setBit(final String key, final long offset, final boolean value){
		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForBool(getTransaction().setbit(key, offset, value).get()));
		}else{
			return execute((cmd)->ReturnUtils.statusForBool(cmd.setbit(key, offset, value)));
		}
	}

	@Override
	public Status setEx(final String key, final String value, final int lifetime){

		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForOK(getTransaction().setex(key, lifetime, value).get()));
		}else{
			return execute((cmd)->ReturnUtils.statusForOK(cmd.setex(key, lifetime, value)));
		}
	}

	@Override
	public Status setNx(final String key, final String value){
		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForBool(getTransaction().setnx(key, value).get() > 0));
		}else{
			return execute((cmd)->ReturnUtils.statusForBool(cmd.setnx(key, value) > 0));
		}
	}

	@Override
	public Long setRange(final String key, final long offset, final String value){
		if(isTransaction()){
			return execute((cmd)->getTransaction().setrange(key, offset, value).get());
		}else{
			return execute((cmd)->cmd.setrange(key, offset, value));
		}
	}

	@Override
	public Long strlen(final String key){
		if(isTransaction()){
			return execute((cmd)->getTransaction().strlen(key).get());
		}else{
			return execute((cmd)->cmd.strlen(key));
		}
	}

	@Override
	public String substr(final String key, final int start, final int end){
		if(isTransaction()){
			return execute((cmd)->getTransaction().substr(key, start, end).get());
		}else{
			return execute((cmd)->cmd.substr(key, start, end));
		}
	}

	protected redis.clients.jedis.Transaction getTransaction(){
		JedisTransaction jedisTransaction = (JedisTransaction) getConnection().getTransaction();
		return jedisTransaction.primitive();
	}

	protected <R> R execute(final Executor<C, R> executor) throws RedisException{
		return super.doExecute(executor);
	}

	protected <R> R transactionExecute(final Executor<C, Object> executor) throws RedisException{
		super.doExecute(executor);
		return null;
	}

}