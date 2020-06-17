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
import com.buession.lang.Geo;
import com.buession.lang.Status;
import com.buession.redis.client.ShardedRedisClient;
import com.buession.redis.client.connection.RedisConnection;
import com.buession.redis.core.GeoRadius;
import com.buession.redis.core.GeoUnit;
import com.buession.redis.core.JedisScanParams;
import com.buession.redis.core.ListPosition;
import com.buession.redis.core.MigrateOperation;
import com.buession.redis.core.ObjectCommand;
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.SortArgument;
import com.buession.redis.core.Tuple;
import com.buession.redis.core.Type;
import com.buession.redis.core.command.DebugCommands;
import com.buession.redis.core.command.KeyCommands;
import com.buession.redis.core.command.ListCommands;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.command.StringCommands;
import com.buession.redis.exception.NotSupportedTransactionCommandException;
import com.buession.redis.utils.ReturnUtils;
import com.buession.redis.utils.SafeEncoder;
import redis.clients.jedis.BitPosParams;
import redis.clients.jedis.GeoCoordinate;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.SortingParams;
import redis.clients.jedis.params.GeoRadiusParam;
import redis.clients.jedis.params.MigrateParams;
import redis.clients.jedis.params.SetParams;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Yong.Teng
 */
public class ShardedJedisClient extends AbstractJedisRedisClient<ShardedJedis> implements ShardedRedisClient {

	public ShardedJedisClient(){
		super();
	}

	public ShardedJedisClient(RedisConnection connection){
		super(connection);
	}

	@Override
	public byte[] echo(final byte[] str){
		if(isTransaction()){
			return execute((cmd)->getTransaction().echo(str).get());
		}else{
			return execute((cmd)->cmd.echo(str));
		}
	}

	@Override
	public Long geoAdd(final byte[] key, final byte[] member, final double longitude, final double latitude){
		if(isTransaction()){
			return execute((cmd)->getTransaction().geoadd(key, longitude, latitude, member).get());
		}else{
			return execute((cmd)->cmd.geoadd(key, longitude, latitude, member));
		}
	}

	@Override
	public Long geoAdd(final byte[] key, final Map<byte[], Geo> memberCoordinates){
		final Map<byte[], GeoCoordinate> memberCoordinateMap = JedisClientUtils.geoMapConvert(memberCoordinates);

		if(isTransaction()){
			return execute((cmd)->getTransaction().geoadd(key, memberCoordinateMap).get());
		}else{
			return execute((cmd)->cmd.geoadd(key, memberCoordinateMap));
		}
	}

	@Override
	public List<byte[]> geoHash(final byte[] key, final byte[]... members){
		if(isTransaction()){
			return execute((cmd)->getTransaction().geohash(key, members).get());
		}else{
			return execute((cmd)->cmd.geohash(key, members));
		}
	}

	@Override
	public List<Geo> geoPos(final byte[] key, final byte[]... members){
		if(isTransaction()){
			return execute((cmd)->JedisClientUtils.geoListDeconvert(getTransaction().geopos(key, members).get()));
		}else{
			return execute((cmd)->JedisClientUtils.geoListDeconvert(cmd.geopos(key, members)));
		}
	}

	@Override
	public Double geoDist(final byte[] key, final byte[] member1, final byte[] member2){
		if(isTransaction()){
			return execute((cmd)->getTransaction().geodist(key, member1, member2).get());
		}else{
			return execute((cmd)->cmd.geodist(key, member1, member2));
		}
	}

	@Override
	public Double geoDist(final byte[] key, final byte[] member1, final byte[] member2, final GeoUnit unit){
		final redis.clients.jedis.GeoUnit geoUnit = JedisClientUtils.geoUnitConvert(unit);

		if(isTransaction()){
			return execute((cmd)->getTransaction().geodist(key, member1, member2, geoUnit).get());
		}else{
			return execute((cmd)->cmd.geodist(key, member1, member2, geoUnit));
		}
	}

	@Override
	public List<GeoRadius> geoRadius(final byte[] key, final double longitude, final double latitude,
			final double radius, final GeoUnit unit){
		final redis.clients.jedis.GeoUnit geoUnit = JedisClientUtils.geoUnitConvert(unit);

		if(isTransaction()){
			return execute((cmd)->JedisClientUtils.listGeoRadiusDeconvert(getTransaction().georadius(key, longitude,
					latitude, radius, geoUnit).get()));
		}else{
			return execute((cmd)->JedisClientUtils.listGeoRadiusDeconvert(cmd.georadius(key, longitude, latitude,
					radius, geoUnit)));
		}
	}

	@Override
	public List<GeoRadius> geoRadius(final byte[] key, final double longitude, final double latitude,
			final double radius, final GeoUnit unit, final GeoArgument geoArgument){
		final redis.clients.jedis.GeoUnit geoUnit = JedisClientUtils.geoUnitConvert(unit);
		final GeoRadiusParam geoRadiusParam = JedisClientUtils.geoArgumentConvert(geoArgument);

		if(isTransaction()){
			return execute((cmd)->JedisClientUtils.listGeoRadiusDeconvert(getTransaction().georadius(key, longitude,
					latitude, radius, geoUnit, geoRadiusParam).get()));
		}else{
			return execute((cmd)->JedisClientUtils.listGeoRadiusDeconvert(cmd.georadius(key, longitude, latitude,
					radius, geoUnit, geoRadiusParam)));
		}
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final byte[] key, final byte[] member, final double radius,
			final GeoUnit unit){
		final redis.clients.jedis.GeoUnit geoUnit = JedisClientUtils.geoUnitConvert(unit);

		if(isTransaction()){
			return execute((cmd)->JedisClientUtils.listGeoRadiusDeconvert(getTransaction().georadiusByMember(key,
					member, radius, geoUnit).get()));
		}else{
			return execute((cmd)->JedisClientUtils.listGeoRadiusDeconvert(cmd.georadiusByMember(key, member, radius,
					geoUnit)));
		}
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final byte[] key, final byte[] member, final double radius,
			final GeoUnit unit, final GeoArgument geoArgument){
		final redis.clients.jedis.GeoUnit geoUnit = JedisClientUtils.geoUnitConvert(unit);
		final GeoRadiusParam geoRadiusParam = JedisClientUtils.geoArgumentConvert(geoArgument);

		if(isTransaction()){
			return execute((cmd)->JedisClientUtils.listGeoRadiusDeconvert(getTransaction().georadiusByMember(key,
					member, radius, geoUnit, geoRadiusParam).get()));
		}else{
			return execute((cmd)->JedisClientUtils.listGeoRadiusDeconvert(cmd.georadiusByMember(key, member, radius,
					geoUnit, geoRadiusParam)));
		}
	}

	@Override
	public Long hDel(final byte[] key, final byte[]... fields){
		if(isTransaction()){
			return execute((cmd)->getTransaction().hdel(key, fields).get());
		}else{
			return execute((cmd)->cmd.hdel(key, fields));
		}
	}

	@Override
	public boolean hExists(final byte[] key, final byte[] field){
		if(isTransaction()){
			return execute((cmd)->getTransaction().hexists(key, field).get());
		}else{
			return execute((cmd)->cmd.hexists(key, field));
		}
	}

	@Override
	public byte[] hGet(final byte[] key, final byte[] field){
		if(isTransaction()){
			return execute((cmd)->getTransaction().hget(key, field).get());
		}else{
			return execute((cmd)->cmd.hget(key, field));
		}
	}

	@Override
	public Map<byte[], byte[]> hGetAll(final byte[] key){
		if(isTransaction()){
			return execute((cmd)->getTransaction().hgetAll(key).get());
		}else{
			return execute((cmd)->cmd.hgetAll(key));
		}
	}

	@Override
	public Long hIncrBy(final byte[] key, final byte[] field, final long value){
		if(isTransaction()){
			return execute((cmd)->getTransaction().hincrBy(key, field, value).get());
		}else{
			return execute((cmd)->cmd.hincrBy(key, field, value));
		}
	}

	@Override
	public Double hIncrByFloat(final byte[] key, final byte[] field, final double value){
		if(isTransaction()){
			return execute((cmd)->getTransaction().hincrByFloat(key, field, value).get());
		}else{
			return execute((cmd)->cmd.hincrByFloat(key, field, value));
		}
	}

	@Override
	public Set<byte[]> hKeys(final byte[] key){
		if(isTransaction()){
			return execute((cmd)->getTransaction().hkeys(key).get());
		}else{
			return execute((cmd)->cmd.hkeys(key));
		}
	}

	@Override
	public Long hLen(final byte[] key){
		if(isTransaction()){
			return execute((cmd)->getTransaction().hlen(key).get());
		}else{
			return execute((cmd)->cmd.hlen(key));
		}
	}

	@Override
	public List<byte[]> hMGet(final byte[] key, final byte[]... fields){
		if(isTransaction()){
			return execute((cmd)->getTransaction().hmget(key, fields).get());
		}else{
			return execute((cmd)->cmd.hmget(key, fields));
		}
	}

	@Override
	public Status hMSet(final byte[] key, final Map<byte[], byte[]> data){
		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForOK(getTransaction().hmset(key, data).get()));
		}else{
			return execute((cmd)->ReturnUtils.statusForOK(cmd.hmset(key, data)));
		}
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor){
		if(isTransaction()){
			throw new NotSupportedTransactionCommandException(ProtocolCommand.HSCAN);
		}else{
			return execute((cmd)->JedisClientUtils.mapScanResultConvert(cmd.hscan(key, cursor)));
		}
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor, final byte[] pattern){
		if(isTransaction()){
			throw new NotSupportedTransactionCommandException(ProtocolCommand.HSCAN);
		}else{
			return execute((cmd)->JedisClientUtils.mapScanResultConvert(cmd.hscan(key, cursor,
					new JedisScanParams(pattern))));
		}
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor, final int count){
		if(isTransaction()){
			throw new NotSupportedTransactionCommandException(ProtocolCommand.HSCAN);
		}else{
			return execute((cmd)->JedisClientUtils.mapScanResultConvert(cmd.hscan(key, cursor,
					new JedisScanParams(count))));
		}
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor, final byte[] pattern,
			final int count){
		if(isTransaction()){
			throw new NotSupportedTransactionCommandException(ProtocolCommand.HSCAN);
		}else{
			return execute((cmd)->JedisClientUtils.mapScanResultConvert(cmd.hscan(key, cursor,
					new JedisScanParams(pattern, count))));
		}
	}

	@Override
	public Status hSet(final byte[] key, final byte[] field, final byte[] value){
		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForBool(getTransaction().hset(key, field, value).get() > 0));
		}else{
			return execute((cmd)->ReturnUtils.statusForBool(cmd.hset(key, field, value) > 0));
		}
	}

	@Override
	public Status hSetNx(final byte[] key, final byte[] field, final byte[] value){
		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForBool(getTransaction().hsetnx(key, field, value).get() > 0));
		}else{
			return execute((cmd)->ReturnUtils.statusForBool(cmd.hsetnx(key, field, value) > 0));
		}
	}

	@Override
	public Long hStrLen(final byte[] key, final byte[] field){
		if(isTransaction()){
			return execute((cmd)->getTransaction().hstrlen(key, field).get());
		}else{
			return execute((cmd)->cmd.hstrlen(key, field));
		}
	}

	@Override
	public List<byte[]> hVals(final byte[] key){
		return execute(new Executor<ShardedJedis, List<byte[]>>() {

			@Override
			public List<byte[]> execute(ShardedJedis cmd){
				if(isTransaction()){
					return getTransaction().hvals(key).get();
				}else{
					Collection<byte[]> result = cmd.hvals(key);
					return result == null ? null : new ArrayList<>(result);
				}
			}

		});
	}

	@Override
	public Status pfAdd(final byte[] key, final byte[]... elements){
		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForBool(getTransaction().pfadd(key, elements).get() > 0));
		}else{
			return execute((cmd)->ReturnUtils.statusForBool(cmd.pfadd(key, elements) > 0));
		}
	}

	@Override
	public Long pfCount(final String... keys){
		return execute(new Executor<ShardedJedis, Long>() {

			@Override
			public Long execute(ShardedJedis cmd){
				if(isTransaction()){
					return getTransaction().pfcount(keys).get();
				}else{
					long result = 0;

					for(String key : keys){
						result += cmd.pfcount(key);
					}

					return result;
				}
			}

		});
	}

	@Override
	public Long pfCount(final byte[]... keys){
		return execute(new Executor<ShardedJedis, Long>() {

			@Override
			public Long execute(ShardedJedis cmd){
				if(isTransaction()){
					return getTransaction().pfcount(keys).get();
				}else{
					long result = 0;

					for(byte[] key : keys){
						result += cmd.pfcount(key);
					}

					return result;
				}
			}

		});
	}

	@Override
	public Long del(final String... keys){
		return execute(new Executor<ShardedJedis, Long>() {

			@Override
			public Long execute(ShardedJedis cmd){
				if(isTransaction()){
					return getTransaction().del(keys).get();
				}else{
					if(keys != null){
						long result = 0;

						for(String key : keys){
							result += cmd.del(key);
						}

						return result;
					}

					return 0L;
				}
			}

		});
	}

	@Override
	public Long del(final byte[]... keys){
		return execute(new Executor<ShardedJedis, Long>() {

			@Override
			public Long execute(ShardedJedis cmd){
				if(isTransaction()){
					return getTransaction().del(keys).get();
				}else{
					if(keys != null){
						long result = 0;

						for(byte[] key : keys){
							result += cmd.del(key);
						}

						return result;
					}

					return 0L;
				}
			}

		});
	}

	@Override
	public byte[] dump(final byte[] key){
		if(isTransaction()){
			return execute((cmd)->getTransaction().dump(key).get());
		}else{
			return execute((cmd)->cmd.dump(key));
		}
	}

	@Override
	public boolean exists(final byte[] key){
		if(isTransaction()){
			return execute((ShardedJedis cmd)->getTransaction().exists(key).get());
		}else{
			return execute((ShardedJedis cmd)->cmd.exists(key));
		}
	}

	@Override
	public Status expire(final byte[] key, final int lifetime){
		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForBool(getTransaction().expire(key, lifetime).get() == 1));
		}else{
			return execute((cmd)->ReturnUtils.statusForBool(cmd.expire(key, lifetime) == 1));
		}
	}

	@Override
	public Status expireAt(final byte[] key, final long unixTimestamp){
		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForBool(getTransaction().expireAt(key, unixTimestamp).get() == 1));
		}else{
			return execute((cmd)->ReturnUtils.statusForBool(cmd.expireAt(key, unixTimestamp) == 1));
		}
	}

	@Override
	public Status migrate(final String key, final String host, final int port, final int db, final int timeout){
		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForOK(getTransaction().migrate(host, port, key, db, timeout).get()));
		}else{
			return execute((cmd)->ReturnUtils.statusForOK(getShard(cmd, key).migrate(host, port, key, db, timeout)));
		}
	}

	@Override
	public Status migrate(final byte[] key, final String host, final int port, final int db, final int timeout){
		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForOK(getTransaction().migrate(host, port, key, db, timeout).get()));
		}else{
			return execute((cmd)->ReturnUtils.statusForOK(getShard(cmd, key).migrate(host, port, key, db, timeout)));
		}
	}

	@Override
	public Status migrate(final String key, final String host, final int port, final int db, final int timeout,
			final MigrateOperation operation){
		final MigrateParams migrateParams = JedisClientUtils.migrateOperationConvert(operation);

		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForOK(getTransaction().migrate(host, port, db, timeout,
					migrateParams, key).get()));
		}else{
			return execute((cmd)->ReturnUtils.statusForOK(getShard(cmd, key).migrate(host, port, db, timeout,
					migrateParams, key)));
		}
	}

	@Override
	public Status migrate(final byte[] key, final String host, final int port, final int db, final int timeout,
			final MigrateOperation operation){
		final MigrateParams migrateParams = JedisClientUtils.migrateOperationConvert(operation);

		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForOK(getTransaction().migrate(host, port, db, timeout,
					migrateParams, key).get()));
		}else{
			return execute((cmd)->ReturnUtils.statusForOK(getShard(cmd, key).migrate(host, port, db, timeout,
					migrateParams, key)));
		}
	}

	@Override
	public Status move(final byte[] key, final int db){
		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForBool(getTransaction().move(key, db).get() > 0));
		}else{
			return execute((cmd)->ReturnUtils.statusForBool(cmd.move(key, db) > 0));
		}
	}

	@Override
	public Status persist(final byte[] key){
		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForBool(getTransaction().persist(key).get() > 0));
		}else{
			return execute((cmd)->ReturnUtils.statusForBool(cmd.persist(key) > 0));
		}
	}

	@Override
	public Status pExpire(final byte[] key, final int lifetime){
		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForBool(getTransaction().pexpire(key, lifetime).get() == 1));
		}else{
			return execute((cmd)->ReturnUtils.statusForBool(cmd.pexpire(key, lifetime) == 1));
		}
	}

	@Override
	public Status pExpireAt(final byte[] key, final long unixTimestamp){
		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForBool(getTransaction().pexpireAt(key, unixTimestamp).get() == 1));
		}else{
			return execute((cmd)->ReturnUtils.statusForBool(cmd.pexpireAt(key, unixTimestamp) == 1));
		}
	}

	@Override
	public Long pTtl(final byte[] key){
		if(isTransaction()){
			return execute((cmd)->getTransaction().pttl(key).get());
		}else{
			return execute((cmd)->cmd.pttl(key));
		}
	}

	@Override
	public Status restore(final byte[] key, final byte[] serializedValue, final int ttl){
		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForOK(getTransaction().restore(key, ttl, serializedValue).get()));
		}else{
			return execute((cmd)->ReturnUtils.statusForOK(cmd.restore(key, ttl, serializedValue)));
		}
	}

	@Override
	public List<byte[]> sort(final byte[] key){
		if(isTransaction()){
			return execute((cmd)->getTransaction().sort(key).get());
		}else{
			return execute((cmd)->cmd.sort(key));
		}
	}

	@Override
	public List<byte[]> sort(final byte[] key, final SortArgument sortArgument){
		final SortingParams sortingParams = JedisClientUtils.sortArgumentConvert(sortArgument);

		if(isTransaction()){
			return execute((cmd)->getTransaction().sort(key, sortingParams).get());
		}else{
			return execute((cmd)->cmd.sort(key, sortingParams));
		}
	}

	@Override
	public Long ttl(final byte[] key){
		if(isTransaction()){
			return execute((cmd)->getTransaction().ttl(key).get());
		}else{
			return execute((cmd)->cmd.ttl(key));
		}
	}

	@Override
	public Type type(final byte[] key){
		if(isTransaction()){
			return execute((ShardedJedis cmd)->ReturnUtils.enumValueOf(getTransaction().type(key).get(), Type.class));
		}else{
			return execute((cmd)->ReturnUtils.enumValueOf(cmd.type(key), Type.class));
		}
	}

	@Override
	public Long touch(final String... keys){
		return execute(new Executor<ShardedJedis, Long>() {

			@Override
			public Long execute(ShardedJedis cmd){
				if(isTransaction()){
					return getTransaction().touch(keys).get();
				}else{
					if(keys != null){
						long result = 0;

						for(String key : keys){
							result += cmd.touch(key);
						}

						return result;
					}

					return 0L;
				}
			}

		});
	}

	@Override
	public Long touch(final byte[]... keys){
		return execute(new Executor<ShardedJedis, Long>() {

			@Override
			public Long execute(ShardedJedis cmd){
				if(isTransaction()){
					return getTransaction().touch(keys).get();
				}else{
					if(keys != null){
						long result = 0;

						for(byte[] key : keys){
							result += cmd.touch(key);
						}

						return result;
					}

					return 0L;
				}
			}

		});
	}

	@Override
	public Long unlink(final String... keys){
		return execute(new Executor<ShardedJedis, Long>() {

			@Override
			public Long execute(ShardedJedis cmd){
				if(isTransaction()){
					return getTransaction().unlink(keys).get();
				}else{
					if(keys != null){
						long result = 0;

						for(String key : keys){
							result += cmd.unlink(key);
						}

						return result;
					}

					return 0L;
				}
			}

		});
	}

	@Override
	public Long unlink(final byte[]... keys){
		return execute(new Executor<ShardedJedis, Long>() {

			@Override
			public Long execute(ShardedJedis cmd){
				if(isTransaction()){
					return getTransaction().unlink(keys).get();
				}else{
					if(keys != null){
						long result = 0;

						for(byte[] key : keys){
							result += cmd.unlink(key);
						}

						return result;
					}

					return 0L;
				}
			}

		});
	}

	@Override
	public byte[] lIndex(final byte[] key, final long index){
		if(isTransaction()){
			return execute((cmd)->getTransaction().lindex(key, index).get());
		}else{
			return execute((cmd)->cmd.lindex(key, index));
		}
	}

	@Override
	public Long lInsert(final byte[] key, final byte[] value, final ListPosition position, final byte[] pivot){
		final redis.clients.jedis.ListPosition pos = JedisClientUtils.listPositionConvert(position);

		if(isTransaction()){
			return execute((cmd)->cmd.linsert(key, pos, pivot, value));
		}else{
			return execute((cmd)->cmd.linsert(key, pos, pivot, value));
		}
	}

	@Override
	public Long lLen(final byte[] key){
		if(isTransaction()){
			return execute((cmd)->getTransaction().llen(key).get());
		}else{
			return execute((cmd)->cmd.llen(key));
		}
	}

	@Override
	public byte[] lPop(final byte[] key){
		if(isTransaction()){
			return execute((cmd)->getTransaction().lpop(key).get());
		}else{
			return execute((cmd)->cmd.lpop(key));
		}
	}

	@Override
	public Long lPush(final byte[] key, final byte[]... values){
		if(isTransaction()){
			return execute((cmd)->getTransaction().lpush(key, values).get());
		}else{
			return execute((cmd)->cmd.lpush(key, values));
		}
	}

	@Override
	public Long lPushX(final byte[] key, final byte[]... values){
		if(isTransaction()){
			return execute((cmd)->getTransaction().lpushx(key, values).get());
		}else{
			return execute((cmd)->cmd.lpushx(key, values));
		}
	}

	@Override
	public List<byte[]> lRange(final byte[] key, final long start, final long end){
		if(isTransaction()){
			return execute((cmd)->getTransaction().lrange(key, start, end).get());
		}else{
			return execute((cmd)->cmd.lrange(key, start, end));
		}
	}

	@Override
	public Long lRem(final byte[] key, final byte[] value, final long count){
		if(isTransaction()){
			return execute((cmd)->getTransaction().lrem(key, count, value).get());
		}else{
			return execute((cmd)->cmd.lrem(key, count, value));
		}
	}

	@Override
	public Status lSet(final byte[] key, final long index, final byte[] value){
		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForOK(getTransaction().lset(key, index, value).get()));
		}else{
			return execute((cmd)->ReturnUtils.statusForOK(cmd.lset(key, index, value)));
		}
	}

	@Override
	public Status lTrim(final byte[] key, final long start, final long end){
		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForOK(getTransaction().ltrim(key, start, end).get()));
		}else{
			return execute((cmd)->ReturnUtils.statusForOK(cmd.ltrim(key, start, end)));
		}
	}

	@Override
	public byte[] rPop(final byte[] key){
		if(isTransaction()){
			return execute((cmd)->getTransaction().rpop(key).get());
		}else{
			return execute((cmd)->cmd.rpop(key));
		}
	}

	@Override
	public Long rPush(final byte[] key, final byte[]... values){
		if(isTransaction()){
			return execute((cmd)->getTransaction().rpush(key, values).get());
		}else{
			return execute((cmd)->cmd.rpush(key, values));
		}
	}

	@Override
	public Long rPushX(final byte[] key, final byte[]... values){
		if(isTransaction()){
			return execute((cmd)->getTransaction().rpushx(key, values).get());
		}else{
			return execute((cmd)->cmd.rpushx(key, values));
		}
	}

	@Override
	public Object object(final ObjectCommand command, final String key){
		return execute(new Executor<ShardedJedis, Object>() {

			@Override
			public Object execute(ShardedJedis cmd){
				if(isTransaction()){
					switch(command){
						case ENCODING:
							return getTransaction().objectEncoding(key).get();
						case IDLETIME:
							return getTransaction().objectIdletime(key).get();
						case REFCOUNT:
							return getTransaction().objectRefcount(key).get();
						default:
							return null;
					}
				}else{
					return JedisClientUtils.objectDebug(command, getShard(cmd, key), key);
				}
			}

		});
	}

	@Override
	public Object object(final ObjectCommand command, final byte[] key){
		return execute(new Executor<ShardedJedis, Object>() {

			@Override
			public Object execute(ShardedJedis cmd){
				if(isTransaction()){
					switch(command){
						case ENCODING:
							return getTransaction().objectEncoding(key).get();
						case IDLETIME:
							return getTransaction().objectIdletime(key).get();
						case REFCOUNT:
							return getTransaction().objectRefcount(key).get();
						default:
							return null;
					}
				}else{
					return JedisClientUtils.objectDebug(command, getShard(cmd, key), key);
				}
			}

		});
	}

	@Override
	public boolean sisMember(final byte[] key, final byte[] member){
		if(isTransaction()){
			return execute((cmd)->getTransaction().sismember(key, member).get());
		}else{
			return execute((cmd)->cmd.sismember(key, member));
		}
	}

	@Override
	public Set<byte[]> sMembers(final byte[] key){
		if(isTransaction()){
			return execute((cmd)->getTransaction().smembers(key).get());
		}else{
			return execute((cmd)->cmd.smembers(key));
		}
	}

	@Override
	public byte[] sPop(final byte[] key){
		if(isTransaction()){
			return execute((cmd)->getTransaction().spop(key).get());
		}else{
			return execute((cmd)->cmd.spop(key));
		}
	}

	@Override
	public byte[] sRandMember(final byte[] key){
		if(isTransaction()){
			return execute((cmd)->getTransaction().srandmember(key).get());
		}else{
			return execute((cmd)->cmd.srandmember(key));
		}
	}

	@Override
	public List<byte[]> sRandMember(final byte[] key, final int count){
		if(isTransaction()){
			return execute((cmd)->getTransaction().srandmember(key, count).get());
		}else{
			return execute((cmd)->cmd.srandmember(key, count));
		}
	}

	@Override
	public Long sRem(final byte[] key, final byte[]... members){
		if(isTransaction()){
			return execute((cmd)->getTransaction().srem(key, members).get());
		}else{
			return execute((cmd)->cmd.srem(key, members));
		}
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor){
		if(isTransaction()){
			throw new NotSupportedTransactionCommandException(ProtocolCommand.SSCAN);
		}else{
			return execute((cmd)->JedisClientUtils.listScanResultDeconvert(cmd.sscan(key, cursor)));
		}
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor, final byte[] pattern){
		if(isTransaction()){
			throw new NotSupportedTransactionCommandException(ProtocolCommand.SSCAN);
		}else{
			return execute((cmd)->JedisClientUtils.listScanResultDeconvert(cmd.sscan(key, cursor,
					new JedisScanParams(pattern))));
		}
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor, final int count){
		if(isTransaction()){
			throw new NotSupportedTransactionCommandException(ProtocolCommand.SSCAN);
		}else{
			return execute((cmd)->JedisClientUtils.listScanResultDeconvert(cmd.sscan(key, cursor,
					new JedisScanParams(count))));
		}
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor, final byte[] pattern,
			final int count){
		if(isTransaction()){
			throw new NotSupportedTransactionCommandException(ProtocolCommand.SSCAN);
		}else{
			return execute((cmd)->JedisClientUtils.listScanResultDeconvert(cmd.sscan(key, cursor,
					new JedisScanParams(pattern, count))));
		}
	}

	@Override
	public Long zAdd(final byte[] key, final Map<byte[], Number> members){
		final Map<byte[], Double> data = new LinkedHashMap<>(members.size());

		members.forEach((k, v)->data.put(k, v.doubleValue()));

		if(isTransaction()){
			return execute((cmd)->getTransaction().zadd(key, data).get());
		}else{
			return execute((cmd)->cmd.zadd(key, data));
		}
	}

	@Override
	public Long zCard(final byte[] key){
		if(isTransaction()){
			return execute((cmd)->getTransaction().zcard(key).get());
		}else{
			return execute((cmd)->cmd.zcard(key));
		}
	}

	@Override
	public Long zCount(final byte[] key, final double min, final double max){
		if(isTransaction()){
			return execute((cmd)->getTransaction().zcount(key, min, max).get());
		}else{
			return execute((cmd)->cmd.zcount(key, min, max));
		}
	}

	@Override
	public Long zCount(final byte[] key, final byte[] min, final byte[] max){
		if(isTransaction()){
			return execute((cmd)->getTransaction().zcount(key, min, max).get());
		}else{
			return execute((cmd)->cmd.zcount(key, min, max));
		}
	}

	@Override
	public Double zIncrBy(final byte[] key, final byte[] member, final double increment){
		if(isTransaction()){
			return execute((cmd)->getTransaction().zincrby(key, increment, member).get());
		}else{
			return execute((cmd)->cmd.zincrby(key, increment, member));
		}
	}

	@Override
	public Long zLexCount(final byte[] key, final byte[] min, final byte[] max){
		if(isTransaction()){
			return execute((cmd)->getTransaction().zlexcount(key, min, max).get());
		}else{
			return execute((cmd)->cmd.zlexcount(key, min, max));
		}
	}

	@Override
	public Set<byte[]> zRange(final byte[] key, final long start, final long end){
		if(isTransaction()){
			return execute((cmd)->getTransaction().zrange(key, start, end).get());
		}else{
			return execute((cmd)->cmd.zrange(key, start, end));
		}
	}

	@Override
	public Set<Tuple> zRangeWithScores(final byte[] key, final long start, final long end){
		if(isTransaction()){
			return execute((cmd)->JedisClientUtils.setTupleDeconvert(getTransaction().zrangeWithScores(key, start,
					end).get()));
		}else{
			return execute((cmd)->JedisClientUtils.setTupleDeconvert(cmd.zrangeWithScores(key, start, end)));
		}
	}

	@Override
	public Set<byte[]> zRangeByLex(final byte[] key, final byte[] min, final byte[] max){
		if(isTransaction()){
			return execute((cmd)->getTransaction().zrangeByLex(key, min, max).get());
		}else{
			return execute((cmd)->cmd.zrangeByLex(key, min, max));
		}
	}

	@Override
	public Set<byte[]> zRangeByLex(final byte[] key, final byte[] min, final byte[] max, final int offset,
			final int count){
		if(isTransaction()){
			return execute((cmd)->getTransaction().zrangeByLex(key, min, max, offset, count).get());
		}else{
			return execute((cmd)->cmd.zrangeByLex(key, min, max, offset, count));
		}
	}

	@Override
	public Set<byte[]> zRangeByScore(final byte[] key, final double min, final double max){
		if(isTransaction()){
			return execute((cmd)->getTransaction().zrangeByScore(key, min, max).get());
		}else{
			return execute((cmd)->cmd.zrangeByScore(key, min, max));
		}
	}

	@Override
	public Set<byte[]> zRangeByScore(final byte[] key, final byte[] min, final byte[] max){
		if(isTransaction()){
			return execute((cmd)->getTransaction().zrangeByScore(key, min, max).get());
		}else{
			return execute((cmd)->cmd.zrangeByScore(key, min, max));
		}
	}

	@Override
	public Set<byte[]> zRangeByScore(final byte[] key, final double min, final double max, final int offset,
			final int count){
		if(isTransaction()){
			return execute((cmd)->getTransaction().zrangeByScore(key, min, max).get());
		}else{
			return execute((cmd)->cmd.zrangeByScore(key, min, max));
		}
	}

	@Override
	public Set<byte[]> zRangeByScore(final byte[] key, final byte[] min, final byte[] max, final int offset,
			final int count){
		if(isTransaction()){
			return execute((cmd)->getTransaction().zrangeByScore(key, min, max, offset, count).get());
		}else{
			return execute((cmd)->cmd.zrangeByScore(key, min, max, offset, count));
		}
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final byte[] key, final double min, final double max){
		if(isTransaction()){
			return execute((cmd)->JedisClientUtils.setTupleDeconvert(getTransaction().zrangeByScoreWithScores(key, min
					, max).get()));
		}else{
			return execute((cmd)->JedisClientUtils.setTupleDeconvert(cmd.zrangeByScoreWithScores(key, min, max)));
		}
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final byte[] key, final byte[] min, final byte[] max){
		if(isTransaction()){
			return execute((cmd)->JedisClientUtils.setTupleDeconvert(getTransaction().zrangeByScoreWithScores(key, min
					, max).get()));
		}else{
			return execute((cmd)->JedisClientUtils.setTupleDeconvert(cmd.zrangeByScoreWithScores(key, min, max)));
		}
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final byte[] key, final double min, final double max, final int offset,
			final int count){
		if(isTransaction()){
			return execute((cmd)->JedisClientUtils.setTupleDeconvert(getTransaction().zrangeByScoreWithScores(key, min
					, max, offset, count).get()));
		}else{
			return execute((cmd)->JedisClientUtils.setTupleDeconvert(cmd.zrangeByScoreWithScores(key, min, max, offset
					, count)));
		}
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final byte[] key, final byte[] min, final byte[] max, final int offset,
			final int count){
		if(isTransaction()){
			return execute((cmd)->JedisClientUtils.setTupleDeconvert(getTransaction().zrangeByScoreWithScores(key, min
					, max, offset, count).get()));
		}else{
			return execute((cmd)->JedisClientUtils.setTupleDeconvert(cmd.zrangeByScoreWithScores(key, min, max, offset
					, count)));
		}
	}

	@Override
	public Long zRank(final byte[] key, final byte[] member){
		if(isTransaction()){
			return execute((cmd)->getTransaction().zrank(key, member).get());
		}else{
			return execute((cmd)->cmd.zrank(key, member));
		}
	}

	@Override
	public Tuple zPopMax(final byte[] key){
		if(isTransaction()){
			return execute((cmd)->JedisClientUtils.tupleDeconvert(getTransaction().zpopmax(key).get()));
		}else{
			return execute((cmd)->JedisClientUtils.tupleDeconvert(cmd.zpopmax(key)));
		}
	}

	@Override
	public Set<Tuple> zPopMax(final byte[] key, final int count){
		if(isTransaction()){
			return execute((cmd)->JedisClientUtils.setTupleDeconvert(getTransaction().zpopmax(key, count).get()));
		}else{
			return execute((cmd)->JedisClientUtils.setTupleDeconvert(cmd.zpopmax(key, count)));
		}
	}

	@Override
	public Tuple zPopMin(final byte[] key){
		if(isTransaction()){
			return execute((cmd)->JedisClientUtils.tupleDeconvert(getTransaction().zpopmin(key).get()));
		}else{
			return execute((cmd)->JedisClientUtils.tupleDeconvert(cmd.zpopmin(key)));
		}
	}

	@Override
	public Set<Tuple> zPopMin(final byte[] key, final int count){
		if(isTransaction()){
			return execute((cmd)->JedisClientUtils.setTupleDeconvert(getTransaction().zpopmin(key, count).get()));
		}else{
			return execute((cmd)->JedisClientUtils.setTupleDeconvert(cmd.zpopmin(key, count)));
		}
	}

	@Override
	public Long zRem(final byte[] key, final byte[]... members){
		if(isTransaction()){
			return execute((cmd)->getTransaction().zrem(key, members).get());
		}else{
			return execute((cmd)->cmd.zrem(key, members));
		}
	}

	@Override
	public Long zRemRangeByLex(final byte[] key, final byte[] min, final byte[] max){
		if(isTransaction()){
			return execute((cmd)->getTransaction().zremrangeByLex(key, min, max).get());
		}else{
			return execute((cmd)->getTransaction().zremrangeByLex(key, min, max).get());
		}
	}

	@Override
	public Long zRemRangeByScore(final byte[] key, final double min, final double max){
		if(isTransaction()){
			return execute((cmd)->getTransaction().zremrangeByScore(key, min, max).get());
		}else{
			return execute((cmd)->cmd.zremrangeByScore(key, min, max));
		}
	}

	@Override
	public Long zRemRangeByScore(final byte[] key, final byte[] min, final byte[] max){
		if(isTransaction()){
			return execute((cmd)->getTransaction().zremrangeByScore(key, min, max).get());
		}else{
			return execute((cmd)->cmd.zremrangeByScore(key, min, max));
		}
	}

	@Override
	public Long zRemRangeByRank(final byte[] key, final long start, final long end){
		if(isTransaction()){
			return execute((cmd)->getTransaction().zremrangeByRank(key, start, end).get());
		}else{
			return execute((cmd)->cmd.zremrangeByRank(key, start, end));
		}
	}

	@Override
	public Set<byte[]> zRevRange(final byte[] key, final long start, final long end){
		if(isTransaction()){
			return execute((cmd)->getTransaction().zrevrange(key, start, end).get());
		}else{
			return execute((cmd)->cmd.zrevrange(key, start, end));
		}
	}

	@Override
	public Set<Tuple> zRevRangeWithScores(final byte[] key, final long start, final long end){
		if(isTransaction()){
			return execute((cmd)->JedisClientUtils.setTupleDeconvert(getTransaction().zrevrangeWithScores(key, start,
					end).get()));
		}else{
			return execute((cmd)->JedisClientUtils.setTupleDeconvert(cmd.zrevrangeWithScores(key, start, end)));
		}
	}

	@Override
	public Set<byte[]> zRevRangeByLex(final byte[] key, final byte[] min, final byte[] max){
		if(isTransaction()){
			return execute((cmd)->getTransaction().zrevrangeByLex(key, min, max).get());
		}else{
			return execute((cmd)->cmd.zrevrangeByLex(key, min, max));
		}
	}

	@Override
	public Set<byte[]> zRevRangeByLex(final byte[] key, final byte[] min, final byte[] max, final int offset,
			final int count){
		if(isTransaction()){
			return execute((cmd)->getTransaction().zrevrangeByLex(key, min, max, offset, count).get());
		}else{
			return execute((cmd)->cmd.zrevrangeByLex(key, min, max, offset, count));
		}
	}

	@Override
	public Set<byte[]> zRevRangeByScore(final byte[] key, final double min, final double max){
		if(isTransaction()){
			return execute((cmd)->getTransaction().zrevrangeByScore(key, min, max).get());
		}else{
			return execute((cmd)->cmd.zrevrangeByScore(key, min, max));
		}
	}

	@Override
	public Set<byte[]> zRevRangeByScore(final byte[] key, final byte[] min, final byte[] max){
		if(isTransaction()){
			return execute((cmd)->getTransaction().zrevrangeByScore(key, min, max).get());
		}else{
			return execute((cmd)->cmd.zrevrangeByScore(key, min, max));
		}
	}

	@Override
	public Set<byte[]> zRevRangeByScore(final byte[] key, final double min, final double max, final int offset,
			final int count){
		if(isTransaction()){
			return execute((cmd)->getTransaction().zrevrangeByScore(key, min, max, offset, count).get());
		}else{
			return execute((cmd)->cmd.zrevrangeByScore(key, min, max, offset, count));
		}
	}

	@Override
	public Set<byte[]> zRevRangeByScore(final byte[] key, final byte[] min, final byte[] max, final int offset,
			final int count){
		if(isTransaction()){
			return execute((cmd)->getTransaction().zrevrangeByScore(key, min, max, offset, count).get());
		}else{
			return execute((cmd)->cmd.zrevrangeByScore(key, min, max, offset, count));
		}
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final double min, final double max){
		if(isTransaction()){
			return execute((cmd)->JedisClientUtils.setTupleDeconvert(getTransaction().zrevrangeByScoreWithScores(key,
					min, max).get()));
		}else{
			return execute((cmd)->JedisClientUtils.setTupleDeconvert(cmd.zrevrangeByScoreWithScores(key, min, max)));
		}
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final byte[] min, final byte[] max){
		if(isTransaction()){
			return execute((cmd)->JedisClientUtils.setTupleDeconvert(getTransaction().zrevrangeByScoreWithScores(key,
					min, max).get()));
		}else{
			return execute((cmd)->JedisClientUtils.setTupleDeconvert(cmd.zrevrangeByScoreWithScores(key, min, max)));
		}
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final double min, final double max,
			final int offset, final int count){
		if(isTransaction()){
			return execute((cmd)->JedisClientUtils.setTupleDeconvert(getTransaction().zrevrangeByScoreWithScores(key,
					min, max, offset, count).get()));
		}else{
			return execute((cmd)->JedisClientUtils.setTupleDeconvert(cmd.zrevrangeByScoreWithScores(key, min, max,
					offset, count)));
		}
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final byte[] min, final byte[] max,
			final int offset, final int count){
		if(isTransaction()){
			return execute((cmd)->JedisClientUtils.setTupleDeconvert(getTransaction().zrevrangeByScoreWithScores(key,
					min, max, offset, count).get()));
		}else{
			return execute((cmd)->JedisClientUtils.setTupleDeconvert(cmd.zrevrangeByScoreWithScores(key, min, max,
					offset, count)));
		}
	}

	@Override
	public Long zRevRank(final byte[] key, final byte[] member){
		if(isTransaction()){
			return execute((cmd)->getTransaction().zrevrank(key, member).get());
		}else{
			return execute((cmd)->cmd.zrevrank(key, member));
		}
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor){
		if(isTransaction()){
			throw new NotSupportedTransactionCommandException(ProtocolCommand.ZSCAN);
		}else{
			return execute((cmd)->JedisClientUtils.listTupleScanResultDeconvert(cmd.zscan(key, cursor)));
		}
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor, final byte[] pattern){
		if(isTransaction()){
			throw new NotSupportedTransactionCommandException(ProtocolCommand.ZSCAN);
		}else{
			return execute((cmd)->JedisClientUtils.listTupleScanResultDeconvert(cmd.zscan(key, cursor,
					new JedisScanParams(pattern))));
		}
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor, final int count){
		if(isTransaction()){
			throw new NotSupportedTransactionCommandException(ProtocolCommand.ZSCAN);
		}else{
			return execute((cmd)->JedisClientUtils.listTupleScanResultDeconvert(cmd.zscan(key, cursor,
					new JedisScanParams(count))));
		}
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor, final byte[] pattern, final int count){
		if(isTransaction()){
			throw new NotSupportedTransactionCommandException(ProtocolCommand.ZSCAN);
		}else{
			return execute((cmd)->JedisClientUtils.listTupleScanResultDeconvert(cmd.zscan(key, cursor,
					new JedisScanParams(pattern, count))));
		}
	}

	@Override
	public Double zScore(final byte[] key, final byte[] member){
		if(isTransaction()){
			return execute((cmd)->getTransaction().zscore(key, member).get());
		}else{
			return execute((cmd)->cmd.zscore(key, member));
		}
	}
/*

	@Override
	public Status set(final byte[] key, final byte[] value){
		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForOK(getTransaction().set(key, value).get()));
		}else{
			return execute((cmd)->ReturnUtils.statusForOK(cmd.set(key, value)));
		}
	}

	@Override
	public Status set(final byte[] key, final byte[] value, final StringCommands.SetArgument setArgument){
		final SetParams setParams = JedisClientUtils.setArgumentConvert(setArgument);

		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForOK(getTransaction().set(key, value, setParams).get()));
		}else{
			return execute((cmd)->ReturnUtils.statusForOK(cmd.set(key, value, setParams)));
		}
	}

	@Override
	public Status setEx(final byte[] key, final byte[] value, final int lifetime){
		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForOK(getTransaction().setex(key, lifetime, value).get()));
		}else{
			return execute((cmd)->ReturnUtils.statusForOK(cmd.setex(key, lifetime, value)));
		}
	}

	@Override
	public Status pSetEx(final byte[] key, final byte[] value, final int lifetime){
		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForOK(getTransaction().psetex(key, lifetime, value).get()));
		}else{
			return execute((cmd)->ReturnUtils.statusForOK(cmd.psetex(key, lifetime, value)));
		}
	}

	@Override
	public Status setNx(final byte[] key, final byte[] value){
		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForBool(getTransaction().setnx(key, value).get() > 0));
		}else{
			return execute((cmd)->ReturnUtils.statusForBool(cmd.setnx(key, value) > 0));
		}
	}

	@Override
	public Long append(final byte[] key, final byte[] value){
		if(isTransaction()){
			return execute((cmd)->getTransaction().append(key, value).get());
		}else{
			return execute((cmd)->cmd.append(key, value));
		}
	}

	@Override
	public byte[] get(final byte[] key){
		if(isTransaction()){
			return execute((cmd)->getTransaction().get(key).get());
		}else{
			return execute((cmd)->cmd.get(key));
		}
	}

	@Override
	public byte[] getSet(final byte[] key, final byte[] value){
		if(isTransaction()){
			return execute((cmd)->getTransaction().getSet(key, value).get());
		}else{
			return execute((cmd)->cmd.getSet(key, value));
		}
	}

	@Override
	public Long incr(final byte[] key){
		if(isTransaction()){
			return execute((cmd)->getTransaction().incr(key).get());
		}else{
			return execute((cmd)->cmd.incr(key));
		}
	}

	@Override
	public Long incrBy(final byte[] key, final long value){
		if(isTransaction()){
			return execute((cmd)->getTransaction().incrBy(key, value).get());
		}else{
			return execute((cmd)->cmd.incrBy(key, value));
		}
	}

	@Override
	public Double incrByFloat(final byte[] key, final double value){
		if(isTransaction()){
			return execute((cmd)->getTransaction().incrByFloat(key, value).get());
		}else{
			return execute((cmd)->cmd.incrByFloat(key, value));
		}
	}

	@Override
	public Long decr(final byte[] key){
		if(isTransaction()){
			return execute((cmd)->getTransaction().decr(key).get());
		}else{
			return execute((cmd)->cmd.decr(key));
		}
	}

	@Override
	public Long decrBy(final byte[] key, final long value){
		if(isTransaction()){
			return execute((cmd)->getTransaction().decrBy(key, value).get());
		}else{
			return execute((cmd)->cmd.decrBy(key, value));
		}
	}

	@Override
	public Long setRange(final byte[] key, final long offset, final byte[] value){
		if(isTransaction()){
			return execute((cmd)->getTransaction().setrange(key, offset, value).get());
		}else{
			return execute((cmd)->cmd.setrange(key, offset, value));
		}
	}

	@Override
	public byte[] getRange(final byte[] key, final long start, final long end){
		if(isTransaction()){
			return execute((cmd)->getTransaction().getrange(key, start, end).get());
		}else{
			return execute((cmd)->cmd.getrange(key, start, end));
		}
	}

	@Override
	public byte[] substr(final byte[] key, final int start, final int end){
		if(isTransaction()){
			return execute((cmd)->SafeEncoder.encode(getTransaction().substr(key, start, end).get()));
		}else{
			return execute((cmd)->cmd.substr(key, start, end));
		}
	}

	@Override
	public Long strlen(final byte[] key){
		if(isTransaction()){
			return execute((cmd)->getTransaction().strlen(key).get());
		}else{
			return execute((cmd)->cmd.strlen(key));
		}
	}

	@Override
	public Long sAdd(final byte[] key, final byte[]... members){
		if(isTransaction()){
			return execute((cmd)->getTransaction().sadd(key, members).get());
		}else{
			return execute((cmd)->cmd.sadd(key, members));
		}
	}

	@Override
	public Long sCard(final byte[] key){
		if(isTransaction()){
			return execute((cmd)->getTransaction().scard(key).get());
		}else{
			return execute((cmd)->cmd.scard(key));
		}
	}

	@Override
	public Status setBit(final byte[] key, final long offset, final byte[] value){
		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForBool(getTransaction().setbit(key, offset, value).get()));
		}else{
			return execute((cmd)->ReturnUtils.statusForBool(cmd.setbit(key, offset, value)));
		}
	}

	@Override
	public Status setBit(final byte[] key, final long offset, final boolean value){
		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForBool(getTransaction().setbit(SafeEncoder.encode(key), offset,
					value).get()));
		}else{
			return execute((cmd)->ReturnUtils.statusForBool(cmd.setbit(key, offset, value)));
		}
	}

	@Override
	public Status getBit(final byte[] key, final long offset){
		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForBool(getTransaction().getbit(key, offset).get()));
		}else{
			return execute((cmd)->ReturnUtils.statusForBool(cmd.getbit(key, offset)));
		}
	}

	@Override
	public Long bitPos(final byte[] key, final boolean value){
		if(isTransaction()){
			return execute((cmd)->getTransaction().bitpos(key, value).get());
		}else{
			return execute((cmd)->cmd.bitpos(SafeEncoder.encode(key), value));
		}
	}

	@Override
	public Long bitPos(final byte[] key, final boolean value, final int start, final int end){
		if(isTransaction()){
			return execute((cmd)->getTransaction().bitpos(key, value, new BitPosParams(start, end)).get());
		}else{
			return execute((cmd)->cmd.bitpos(SafeEncoder.encode(key), value, new BitPosParams(start, end)));
		}
	}

	@Override
	public List<Long> bitField(final byte[] key, final byte[]... arguments){
		if(isTransaction()){
			return execute((cmd)->getTransaction().bitfield(key, arguments).get());
		}else{
			return execute((cmd)->cmd.bitfield(key, arguments));
		}
	}

	@Override
	public Long bitCount(final byte[] key){
		if(isTransaction()){
			return execute((cmd)->getTransaction().bitcount(key).get());
		}else{
			return execute((cmd)->cmd.bitcount(key));
		}
	}

	@Override
	public Long bitCount(final byte[] key, final long start, final long end){
		if(isTransaction()){
			return execute((cmd)->getTransaction().bitcount(key, start, end).get());
		}else{
			return execute((cmd)->cmd.bitcount(key, start, end));
		}
	}*/

	protected final static Jedis getShard(final ShardedJedis shardedJedis, final String key){
		return shardedJedis.getShard(key);
	}

	protected final Jedis getShard(final ShardedJedis shardedJedis, final byte[] key){
		return shardedJedis.getShard(key);
	}

}
