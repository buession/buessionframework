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
package com.buession.redis;

import com.buession.lang.Geo;
import com.buession.lang.KeyValue;
import com.buession.lang.Status;
import com.buession.redis.client.connection.RedisConnection;
import com.buession.redis.client.RedisClient;
import com.buession.redis.core.Client;
import com.buession.redis.core.Executor;
import com.buession.redis.core.GeoRadius;
import com.buession.redis.core.GeoUnit;
import com.buession.redis.core.Info;
import com.buession.redis.core.PubSubListener;
import com.buession.redis.core.RedisMonitor;
import com.buession.redis.core.RedisServerTime;
import com.buession.redis.core.Role;
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.SortArgument;
import com.buession.redis.core.Tuple;
import com.buession.redis.core.Type;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.DebugCommands;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.transaction.Transaction;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Redis 基本操作 Template
 *
 * @author Yong.Teng
 */
public class BaseRedisTemplate extends AbstractRedisTemplate {

	/**
	 * 构造函数
	 */
	public BaseRedisTemplate(){
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param connection
	 * 		Redis 连接对称
	 */
	public BaseRedisTemplate(RedisConnection connection){
		super(connection);
	}

	@Override
	public Status auth(final String password){
		final CommandArguments args = CommandArguments.getInstance().put("password", password);
		return execute((client)->client.auth(password), ProtocolCommand.AUTH, args);
	}

	@Override
	public Status auth(final byte[] password){
		final CommandArguments args = CommandArguments.getInstance().put("password", password);
		return execute((client)->client.auth(password), ProtocolCommand.AUTH, args);
	}

	@Override
	public String echo(final String str){
		final CommandArguments args = CommandArguments.getInstance().put("str", str);
		return execute((client)->client.echo(str), ProtocolCommand.ECHO, args);
	}

	@Override
	public byte[] echo(final byte[] str){
		final CommandArguments args = CommandArguments.getInstance().put("str", str);
		return execute((client)->client.echo(str), ProtocolCommand.ECHO, args);
	}

	@Override
	public Status ping(){
		return execute((client)->client.ping(), ProtocolCommand.PING);
	}

	@Override
	public Status quit(){
		return execute((client)->client.quit(), ProtocolCommand.QUIT);
	}

	@Override
	public Status select(final int db){
		final CommandArguments args = CommandArguments.getInstance().put("db", db);
		return execute((client)->client.select(db), ProtocolCommand.SELECT, args);
	}

	@Override
	public Status swapdb(final int db1, final int db2){
		final CommandArguments args = CommandArguments.getInstance().put("db1", db1).put("db2", db2);
		return execute((client)->client.swapdb(db1, db2), ProtocolCommand.SWAPDB, args);
	}

	@Override
	public Long geoAdd(final String key, final String member, final double longitude, final double latitude){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("member", member).put(
				"longitude", longitude).put("latitude", latitude);
		return execute((client)->client.geoAdd(makeRawKey(key), member, longitude, latitude), ProtocolCommand.GEOADD,
				args);
	}

	@Override
	public Long geoAdd(final byte[] key, final byte[] member, final double longitude, final double latitude){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("member", member).put(
				"longitude", longitude).put("latitude", latitude);
		return execute((client)->client.geoAdd(makeByteKey(key), member, longitude, latitude), ProtocolCommand.GEOADD,
				args);
	}

	@Override
	public Long geoAdd(final String key, final Map<String, Geo> memberCoordinates){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("memberCoordinates",
				memberCoordinates);
		return execute((client)->client.geoAdd(makeRawKey(key), memberCoordinates), ProtocolCommand.GEOADD, args);
	}

	@Override
	public Long geoAdd(final byte[] key, final Map<byte[], Geo> memberCoordinates){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("memberCoordinates",
				memberCoordinates);
		return execute((client)->client.geoAdd(makeByteKey(key), memberCoordinates), ProtocolCommand.GEOADD, args);
	}

	@Override
	public List<String> geoHash(final String key, final String... members){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("members", members);
		return execute((client)->client.geoHash(makeRawKey(key), members), ProtocolCommand.GEOHASH, args);
	}

	@Override
	public List<byte[]> geoHash(final byte[] key, final byte[]... members){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("members", members);
		return execute((client)->client.geoHash(makeByteKey(key), members), ProtocolCommand.GEOHASH, args);
	}

	@Override
	public List<Geo> geoPos(final String key, final String... members){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("members", members);
		return execute((client)->client.geoPos(makeRawKey(key), members), ProtocolCommand.GEOPOS, args);
	}

	@Override
	public List<Geo> geoPos(final byte[] key, final byte[]... members){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("members", members);
		return execute((client)->client.geoPos(makeByteKey(key), members), ProtocolCommand.GEOPOS, args);
	}

	@Override
	public Double geoDist(final String key, final String member1, final String member2){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("member1", member1).put(
				"member2", member2);
		return execute((client)->client.geoDist(makeRawKey(key), member1, member2), ProtocolCommand.GEODIST, args);
	}

	@Override
	public Double geoDist(final byte[] key, final byte[] member1, final byte[] member2){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("member1", member1).put(
				"member2", member2);
		return execute((client)->client.geoDist(makeByteKey(key), member1, member2), ProtocolCommand.GEODIST, args);
	}

	@Override
	public Double geoDist(final String key, final String member1, final String member2, final GeoUnit unit){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("member1", member1).put(
				"member2", member2).put("unit", unit);
		return execute((client)->client.geoDist(makeRawKey(key), member1, member2, unit), ProtocolCommand.GEODIST,
				args);
	}

	@Override
	public Double geoDist(final byte[] key, final byte[] member1, final byte[] member2, final GeoUnit unit){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("member1", member1).put(
				"member2", member2).put("unit", unit);
		return execute((client)->client.geoDist(makeByteKey(key), member1, member2, unit), ProtocolCommand.GEODIST,
				args);
	}

	@Override
	public List<GeoRadius> geoRadius(final String key, final double longitude, final double latitude,
			final double radius){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("longitude", longitude).put(
				"latitude", latitude).put("radius", radius);
		return execute((client)->client.geoRadius(makeRawKey(key), longitude, latitude, radius),
				ProtocolCommand.GEORADIUS, args);
	}

	@Override
	public List<GeoRadius> geoRadius(final byte[] key, final double longitude, final double latitude,
			final double radius){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("longitude", longitude).put(
				"latitude", latitude).put("radius", radius);
		return execute((client)->client.geoRadius(makeByteKey(key), longitude, latitude, radius),
				ProtocolCommand.GEORADIUS, args);
	}

	@Override
	public List<GeoRadius> geoRadius(final String key, final double longitude, final double latitude,
			final double radius, final GeoUnit unit){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("longitude", longitude).put(
				"latitude", latitude).put("radius", radius).put("unit", unit);
		return execute((client)->client.geoRadius(makeRawKey(key), longitude, latitude, radius, unit),
				ProtocolCommand.GEORADIUS, args);
	}

	@Override
	public List<GeoRadius> geoRadius(final byte[] key, final double longitude, final double latitude,
			final double radius, final GeoUnit unit){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("longitude", longitude).put(
				"latitude", latitude).put("radius", radius).put("unit", unit);
		return execute((client)->client.geoRadius(makeByteKey(key), longitude, latitude, radius, unit),
				ProtocolCommand.GEORADIUS, args);
	}

	@Override
	public List<GeoRadius> geoRadius(final String key, final double longitude, final double latitude,
			final double radius, final GeoArgument geoArgument){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("longitude", longitude).put(
				"latitude", latitude).put("radius", radius).put("geoArgument", geoArgument);
		return execute((client)->client.geoRadius(makeRawKey(key), longitude, latitude, radius, geoArgument),
				ProtocolCommand.GEORADIUS, args);
	}

	@Override
	public List<GeoRadius> geoRadius(byte[] key, final double longitude, final double latitude, final double radius,
			final GeoArgument geoArgument){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("longitude", longitude).put(
				"latitude", latitude).put("radius", radius).put("geoArgument", geoArgument);
		return execute((client)->client.geoRadius(makeByteKey(key), longitude, latitude, radius, geoArgument),
				ProtocolCommand.GEORADIUS, args);
	}

	@Override
	public List<GeoRadius> geoRadius(final String key, final double longitude, final double latitude,
			final double radius, final GeoUnit unit, final GeoArgument geoArgument){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("longitude", longitude).put(
				"latitude", latitude).put("radius", radius).put("unit", unit).put("geoArgument", geoArgument);
		return execute((client)->client.geoRadius(makeRawKey(key), longitude, latitude, radius, unit, geoArgument),
				ProtocolCommand.GEORADIUS, args);
	}

	@Override
	public List<GeoRadius> geoRadius(final byte[] key, final double longitude, final double latitude,
			final double radius, final GeoUnit unit, final GeoArgument geoArgument){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("longitude", longitude).put(
				"latitude", latitude).put("radius", radius).put("unit", unit).put("geoArgument", geoArgument);
		return execute((client)->client.geoRadius(makeByteKey(key), longitude, latitude, radius, unit, geoArgument),
				ProtocolCommand.GEORADIUS, args);
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final String key, final String member, final double radius){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("member", member).put("radius"
				, radius);
		return execute((client)->client.geoRadiusByMember(makeRawKey(key), member, radius),
				ProtocolCommand.GEORADIUSBYMEMBER, args);
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final byte[] key, final byte[] member, final double radius){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("member", member).put("radius"
				, radius);
		return execute((client)->client.geoRadiusByMember(makeByteKey(key), member, radius),
				ProtocolCommand.GEORADIUSBYMEMBER, args);
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final String key, final String member, final double radius,
			final GeoUnit unit){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("member", member).put("radius"
				, radius).put("unit", unit);
		return execute((client)->client.geoRadiusByMember(makeRawKey(key), member, radius, unit),
				ProtocolCommand.GEORADIUSBYMEMBER, args);
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final byte[] key, final byte[] member, final double radius,
			final GeoUnit unit){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("member", member).put("radius"
				, radius).put("unit", unit);
		return execute((client)->client.geoRadiusByMember(makeByteKey(key), member, radius, unit),
				ProtocolCommand.GEORADIUSBYMEMBER, args);
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final String key, final String member, final double radius,
			final GeoArgument geoArgument){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("member", member).put("radius"
				, radius).put("geoArgument", geoArgument);
		return execute((client)->client.geoRadiusByMember(makeRawKey(key), member, radius, geoArgument),
				ProtocolCommand.GEORADIUSBYMEMBER, args);
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final byte[] key, final byte[] member, final double radius,
			final GeoArgument geoArgument){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("member", member).put("radius"
				, radius).put("geoArgument", geoArgument);
		return execute((client)->client.geoRadiusByMember(makeByteKey(key), member, radius, geoArgument),
				ProtocolCommand.GEORADIUSBYMEMBER, args);
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final String key, final String member, final double radius,
			final GeoUnit unit, final GeoArgument geoArgument){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("member", member).put("radius"
				, radius).put("unit", unit).put("geoArgument", geoArgument);
		return execute((client)->client.geoRadiusByMember(makeRawKey(key), member, radius, unit, geoArgument),
				ProtocolCommand.GEORADIUSBYMEMBER, args);
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final byte[] key, final byte[] member, final double radius,
			final GeoUnit unit, final GeoArgument geoArgument){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("member", member).put("radius"
				, radius).put("unit", unit).put("geoArgument", geoArgument);
		return execute((client)->client.geoRadiusByMember(makeByteKey(key), member, radius, unit, geoArgument),
				ProtocolCommand.GEORADIUSBYMEMBER, args);
	}

    @Override
    public Long hDel(final String key, final String... fields){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("fields", fields);
        return execute((client)->client.hDel(makeRawKey(key), fields), ProtocolCommand.HDEL, args);
    }

    @Override
    public Long hDel(final byte[] key, final byte[]... fields){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("fields", fields);
        return execute((client)->client.hDel(makeByteKey(key), fields), ProtocolCommand.HDEL, args);
    }

    @Override
    public boolean hExists(final String key, final String field){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("field", field);
        return execute((client)->client.hExists(makeRawKey(key), field), ProtocolCommand.HEXISTS, args);
    }

    @Override
    public boolean hExists(final byte[] key, final byte[] field){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("field", field);
        return execute((client)->client.hExists(makeByteKey(key), field), ProtocolCommand.HEXISTS, args);
    }

    @Override
    public String hGet(final String key, final String field){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("field", field);
        return execute((client)->client.hGet(makeRawKey(key), field), ProtocolCommand.HGET, args);
    }

    @Override
    public byte[] hGet(final byte[] key, final byte[] field){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("field", field);
        return execute((client)->client.hGet(makeByteKey(key), field), ProtocolCommand.HGET, args);
    }

    @Override
    public Map<String, String> hGetAll(final String key){
        final CommandArguments args = CommandArguments.getInstance().put("key", key);
        return execute((client)->client.hGetAll(makeRawKey(key)), ProtocolCommand.HGETALL, args);
    }

    @Override
    public Map<byte[], byte[]> hGetAll(final byte[] key){
        final CommandArguments args = CommandArguments.getInstance().put("key", key);
        return execute((client)->client.hGetAll(makeByteKey(key)), ProtocolCommand.HGETALL, args);
    }

    @Override
    public Long hIncrBy(final String key, final String field, final int value){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("field", field).put("value", value);
        return execute((client)->client.hIncrBy(makeRawKey(key), field, value), ProtocolCommand.HINCRBY, args);
    }

    @Override
    public Long hIncrBy(final byte[] key, final byte[] field, final int value){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("field", field).put("value", value);
        return execute((client)->client.hIncrBy(makeByteKey(key), field, value), ProtocolCommand.HINCRBY, args);
    }

    @Override
    public Long hIncrBy(final String key, final String field, final long value){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("field", field).put("value", value);
        return execute((client)->client.hIncrBy(makeRawKey(key), field, value), ProtocolCommand.HINCRBY, args);
    }

    @Override
    public Long hIncrBy(final byte[] key, final byte[] field, final long value){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("field", field).put("value", value);
        return execute((client)->client.hIncrBy(makeByteKey(key), field, value), ProtocolCommand.HINCRBY, args);
    }

    @Override
    public Double hIncrByFloat(final String key, final String field, final float value){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("field", field).put("value", value);
        return execute((client)->client.hIncrByFloat(makeRawKey(key), field, value), ProtocolCommand.HINCRBYFLOAT, args);
    }

    @Override
    public Double hIncrByFloat(final byte[] key, final byte[] field, final float value){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("field", field).put("value", value);
        return execute((client)->client.hIncrByFloat(makeByteKey(key), field, value), ProtocolCommand.HINCRBYFLOAT, args);
    }

    @Override
    public Double hIncrByFloat(final String key, final String field, final double value){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("field", field).put("value", value);
        return execute((client)->client.hIncrByFloat(makeRawKey(key), field, value), ProtocolCommand.HINCRBYFLOAT, args);
    }

    @Override
    public Double hIncrByFloat(final byte[] key, final byte[] field, final double value){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("field", field).put("value", value);
        return execute((client)->client.hIncrByFloat(makeByteKey(key), field, value), ProtocolCommand.HINCRBYFLOAT, args);
    }

    @Override
    public Long hDecrBy(final String key, final String field, final int value){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("field", field).put("value", value);
        return execute((client)->client.hDecrBy(makeRawKey(key), field, value), ProtocolCommand.HINCRBY, args);
    }

    @Override
    public Long hDecrBy(final byte[] key, final byte[] field, final int value){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("field", field).put("value", value);
        return execute((client)->client.hDecrBy(makeByteKey(key), field, value), ProtocolCommand.HINCRBY, args);
    }

    @Override
    public Long hDecrBy(final String key, final String field, final long value){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("field", field).put("value", value);
        return execute((client)->client.hDecrBy(makeRawKey(key), field, value), ProtocolCommand.HINCRBY, args);
    }

    @Override
    public Long hDecrBy(final byte[] key, final byte[] field, final long value){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("field", field).put("value", value);
        return execute((client)->client.hDecrBy(makeByteKey(key), field, value), ProtocolCommand.HINCRBY, args);
    }

    @Override
    public Set<String> hKeys(final String key){
        final CommandArguments args = CommandArguments.getInstance().put("key", key);
        return execute((client)->client.hKeys(makeRawKey(key)), ProtocolCommand.HKEYS, args);
    }

    @Override
    public Set<byte[]> hKeys(final byte[] key){
        final CommandArguments args = CommandArguments.getInstance().put("key", key);
        return execute((client)->client.hKeys(makeByteKey(key)), ProtocolCommand.HKEYS, args);
    }

    @Override
    public Long hLen(final String key){
        final CommandArguments args = CommandArguments.getInstance().put("key", key);
        return execute((client)->client.hLen(makeRawKey(key)), ProtocolCommand.HLEN, args);
    }

    @Override
    public Long hLen(final byte[] key){
        final CommandArguments args = CommandArguments.getInstance().put("key", key);
        return execute((client)->client.hLen(makeByteKey(key)), ProtocolCommand.HLEN, args);
    }

    @Override
    public List<String> hMGet(final String key, final String... fields){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("fields", fields);
        return execute((client)->client.hMGet(makeRawKey(key), fields), ProtocolCommand.HMGET, args);
    }

    @Override
    public List<byte[]> hMGet(final byte[] key, final byte[]... fields){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("fields", fields);
        return execute((client)->client.hMGet(makeByteKey(key), fields), ProtocolCommand.HMGET, args);
    }

    @Override
    public Status hMSet(final String key, final Map<String, String> data){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("data", data);
        return execute((client)->client.hMSet(makeRawKey(key), data), ProtocolCommand.HMSET, args);
    }

    @Override
    public Status hMSet(final byte[] key, final Map<byte[], byte[]> data){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("data", data);
        return execute((client)->client.hMSet(key, data), ProtocolCommand.HMSET, args);
    }

    @Override
    public ScanResult<Map<String, String>> hScan(final String key, final int cursor){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor);
        return execute((client)->client.hScan(makeRawKey(key), cursor), ProtocolCommand.HSCAN, args);
    }

    @Override
    public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final int cursor){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor);
        return execute((client)->client.hScan(makeByteKey(key), cursor), ProtocolCommand.HSCAN, args);
    }

    @Override
    public ScanResult<Map<String, String>> hScan(final String key, final long cursor){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor);
        return execute((client)->client.hScan(makeRawKey(key), cursor), ProtocolCommand.HSCAN, args);
    }

    @Override
    public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final long cursor){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor);
        return execute((client)->client.hScan(makeByteKey(key), cursor), ProtocolCommand.HSCAN, args);
    }

    @Override
    public ScanResult<Map<String, String>> hScan(final String key, final String cursor){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor);
        return execute((client)->client.hScan(makeRawKey(key), cursor), ProtocolCommand.HSCAN, args);
    }

    @Override
    public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor);
        return execute((client)->client.hScan(makeByteKey(key), cursor), ProtocolCommand.HSCAN, args);
    }

    @Override
    public ScanResult<Map<String, String>> hScan(final String key, final int cursor, final String pattern){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("pattern", pattern);
        return execute((client)->client.hScan(makeRawKey(key), cursor, pattern), ProtocolCommand.HSCAN, args);
    }

    @Override
    public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final int cursor, final byte[] pattern){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("pattern", pattern);
        return execute((client)->client.hScan(makeByteKey(key), cursor, pattern), ProtocolCommand.HSCAN, args);
    }

    @Override
    public ScanResult<Map<String, String>> hScan(final String key, final long cursor, final String pattern){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("pattern", pattern);
        return execute((client)->client.hScan(makeRawKey(key), cursor, pattern), ProtocolCommand.HSCAN, args);
    }

    @Override
    public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final long cursor, final byte[] pattern){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("pattern", pattern);
        return execute((client)->client.hScan(makeByteKey(key), cursor, pattern), ProtocolCommand.HSCAN, args);
    }

    @Override
    public ScanResult<Map<String, String>> hScan(final String key, final String cursor, final String pattern){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("pattern", pattern);
        return execute((client)->client.hScan(makeRawKey(key), cursor, pattern), ProtocolCommand.HSCAN, args);
    }

    @Override
    public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor, final byte[] pattern){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("pattern", pattern);
        return execute((client)->client.hScan(makeByteKey(key), cursor, pattern), ProtocolCommand.HSCAN, args);
    }

    @Override
    public ScanResult<Map<String, String>> hScan(final String key, final int cursor, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("count", count);
        return execute((client)->client.hScan(makeRawKey(key), cursor, count), ProtocolCommand.HSCAN, args);
    }

    @Override
    public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final int cursor, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("count", count);
        return execute((client)->client.hScan(makeByteKey(key), cursor, count), ProtocolCommand.HSCAN, args);
    }

    @Override
    public ScanResult<Map<String, String>> hScan(final String key, final long cursor, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("count", count);
        return execute((client)->client.hScan(makeRawKey(key), cursor, count), ProtocolCommand.HSCAN, args);
    }

    @Override
    public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final long cursor, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("count", count);
        return execute((client)->client.hScan(makeByteKey(key), cursor, count), ProtocolCommand.HSCAN, args);
    }

    @Override
    public ScanResult<Map<String, String>> hScan(final String key, final String cursor, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("count", count);
        return execute((client)->client.hScan(makeRawKey(key), cursor, count), ProtocolCommand.HSCAN, args);
    }

    @Override
    public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("count", count);
        return execute((client)->client.hScan(makeByteKey(key), cursor, count), ProtocolCommand.HSCAN, args);
    }

    @Override
    public ScanResult<Map<String, String>> hScan(final String key, final int cursor, final String pattern, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("pattern", pattern).put("count", count);
        return execute((client)->client.hScan(makeRawKey(key), cursor, pattern, count), ProtocolCommand.HSCAN, args);
    }

    @Override
    public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final int cursor, final byte[] pattern, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("pattern", pattern).put("count", count);
        return execute((client)->client.hScan(makeByteKey(key), cursor, pattern, count), ProtocolCommand.HSCAN, args);
    }

    @Override
    public ScanResult<Map<String, String>> hScan(final String key, final long cursor, final String pattern, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("pattern", pattern).put("count", count);
        return execute ((client)->client.hScan(makeRawKey(key), cursor, pattern, count), ProtocolCommand.HSCAN, args);
    }

    @Override
    public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final long cursor, final byte[] pattern, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("pattern", pattern).put("count", count);
        return execute((client)->client.hScan(makeByteKey(key), cursor, pattern, count), ProtocolCommand.HSCAN, args);
    }

    @Override
    public ScanResult<Map<String, String>> hScan(final String key, final String cursor, final String pattern, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("pattern", pattern).put("count", count);
        return execute((client)->client.hScan(makeRawKey(key), cursor, pattern, count), ProtocolCommand.HSCAN, args);
    }

    @Override
    public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor, final byte[] pattern, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("pattern", pattern).put("count", count);
        return execute((client)->client.hScan(makeByteKey(key), cursor, pattern, count), ProtocolCommand.HSCAN, args);
    }

    @Override
    public Status hSet(final String key, final String field, final String value){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("field", field).put("value", value);
        return execute((client)->client.hSet(makeRawKey(key), field, value), ProtocolCommand.HSET, args);
    }

    @Override
    public Status hSet(final byte[] key, final byte[] field, final byte[] value){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("field", field).put("value", value);
        return execute((client)->client.hSet(makeByteKey(key), field, value), ProtocolCommand.HSET, args);
    }

    @Override
    public Status hSetNx(final String key, final String field, final String value){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("field", field).put("value", value);
        return execute((client)->client.hSetNx(makeRawKey(key), field, value), ProtocolCommand.HSETNX, args);
    }

    @Override
    public Status hSetNx(final byte[] key, final byte[] field, final byte[] value){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("field", field).put("value", value);
        return execute((client)->client.hSetNx(makeByteKey(key), field, value), ProtocolCommand.HSETNX, args);
    }

    @Override
    public Long hStrLen(final String key, final String field){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("field", field);
        return execute((client)->client.hStrLen(makeRawKey(key), field), ProtocolCommand.HSTRLEN, args);
    }

    @Override
    public Long hStrLen(final byte[] key, final byte[] field){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("field", field);
        return execute((client)->client.hStrLen(makeByteKey(key), field), ProtocolCommand.HSTRLEN, args);
    }

    @Override
    public List<String> hVals(final String key){
        final CommandArguments args = CommandArguments.getInstance().put("key", key);
        return execute((client)->client.hVals(makeRawKey(key)), ProtocolCommand.HKEYS, args);
    }

    @Override
    public List<byte[]> hVals(final byte[] key){
        final CommandArguments args = CommandArguments.getInstance().put("key", key);
        return execute((client)->client.hVals(makeByteKey(key)), ProtocolCommand.HKEYS, args);
    }

    @Override
    public Status pfAdd(final String key, final String... elements){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("elements", elements);
        return execute((client)->client.pfAdd(makeRawKey(key), elements), ProtocolCommand.PFADD, args);
    }

    @Override
    public Status pfAdd(final byte[] key, final byte[]... elements){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("elements", elements);
        return execute((client)->client.pfAdd(makeByteKey(key), elements), ProtocolCommand.PFADD, args);
    }

    @Override
    public Status pfMerge(final String destKey, final String... keys){
        final CommandArguments args = CommandArguments.getInstance().put("destKey", destKey).put("keys", keys);
        return execute((client)->client.pfMerge(makeRawKey(destKey), makeRawKeys(keys)), ProtocolCommand.PFMERGE, args);
    }

    @Override
    public Status pfMerge(final byte[] destKey, final byte[]... keys){
        final CommandArguments args = CommandArguments.getInstance().put("destKey", destKey).put("keys", keys);
        return execute((client)->client.pfMerge(makeByteKey(destKey), makeByteKeys(keys)), ProtocolCommand.PFMERGE, args);
    }

    @Override
    public Long pfCount(final String... keys){
        final CommandArguments args = CommandArguments.getInstance().put("keys", keys);
        return execute((client)->client.pfCount(makeRawKeys(keys)), ProtocolCommand.PFCOUNT, args);
    }

    @Override
    public Long pfCount(final byte[]... keys){
        final CommandArguments args = CommandArguments.getInstance().put("keys", keys);
        return execute((client)->client.pfCount(makeByteKeys(keys)), ProtocolCommand.PFCOUNT, args);
    }

    @Override
    public Long del(final String... keys){
        final CommandArguments args = CommandArguments.getInstance().put("keys", keys);
        return execute((client)->client.del(makeRawKeys(keys)), ProtocolCommand.DEL, args);
    }

    @Override
    public Long del(final byte[]... keys){
        final CommandArguments args = CommandArguments.getInstance().put("keys", keys);
        return execute((client)->client.del(makeByteKeys(keys)), ProtocolCommand.DEL, args);
    }

    @Override
    public byte[] dump(final String key){
        final CommandArguments args = CommandArguments.getInstance().put("key", key);
        return execute((client)->client.dump(makeRawKey(key)), ProtocolCommand.DUMP, args);
    }

    @Override
    public byte[] dump(final byte[] key){
        final CommandArguments args = CommandArguments.getInstance().put("key", key);
        return execute((client)->client.dump(makeByteKey(key)), ProtocolCommand.DUMP, args);
    }

    @Override
    public boolean exists(final String key){
        final CommandArguments args = CommandArguments.getInstance().put("key", key);
        return execute((client)->client.exists(makeRawKey(key)), ProtocolCommand.EXISTS, args);
    }

    @Override
    public boolean exists(final byte[] key){
        final CommandArguments args = CommandArguments.getInstance().put("key", key);
        return execute((client)->client.exists(makeByteKey(key)), ProtocolCommand.EXISTS, args);
    }

    @Override
    public Status expire(final String key, final int lifetime){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("lifetime", lifetime);
        return execute((client)->client.expire(makeRawKey(key), lifetime), ProtocolCommand.EXPIRE, args);
    }

    @Override
    public Status expire(final byte[] key, final int lifetime){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("lifetime", lifetime);
        return execute((client)->client.expire(makeByteKey(key), lifetime), ProtocolCommand.EXPIRE, args);
    }

    @Override
    public Status expireAt(final String key, final long unixTimestamp){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("unixTimestamp", unixTimestamp);
        return execute((client)->client.expireAt(makeRawKey(key), unixTimestamp), ProtocolCommand.EXPIREAT, args);
    }

    @Override
    public Status expireAt(final byte[] key, final long unixTimestamp){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("unixTimestamp", unixTimestamp);
        return execute((client)->client.expireAt(makeByteKey(key), unixTimestamp), ProtocolCommand.EXPIREAT, args);
    }

    @Override
    public Status migrate(final String key, final String host, final int port, final int db, final int timeout){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("host", host).put("port", port).put("db", db).put("timeout", timeout);
        return execute((client)->client.migrate(makeRawKey(key), host, port, db, timeout), ProtocolCommand.MIGRATE, args);
    }

    @Override
    public Status migrate(final byte[] key, final String host, final int port, final int db, final int timeout){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("host", host).put("port", port).put("db", db).put("timeout", timeout);
        return execute((client)->client.migrate(makeByteKey(key), host, port, db, timeout), ProtocolCommand.MIGRATE, args);
    }

    @Override
    public Status migrate(final String key, final String host, final int port, final int db, final int timeout, MigrateOperation operation){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("host", host).put("port", port).put("db", db).put("timeout", timeout).put("operation", operation);
        return execute((client)->client.migrate(makeRawKey(key), host, port, db, timeout, operation), ProtocolCommand.MIGRATE, args);
    }

    @Override
    public Status migrate(final byte[] key, final String host, final int port, final int db, final int timeout, final MigrateOperation operation){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("host", host).put("port", port).put("db", db).put("timeout", timeout).put("operation", operation);
        return execute((client)->client.migrate(makeByteKey(key), host, port, db, timeout, operation), ProtocolCommand.MIGRATE, args);
    }

    @Override
    public Status move(final String key, final int db){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("db", db);
        return execute((client)->client.move(makeRawKey(key), db), ProtocolCommand.MOVE, args);
    }

    @Override
    public Status move(final byte[] key, final int db){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("db", db);
        return execute((client)->client.move(makeByteKey(key), db), ProtocolCommand.MOVE, args);
    }

    @Override
    public Set<String> keys(final String pattern){
        final CommandArguments args = CommandArguments.getInstance().put("pattern", pattern);
        return execute((client)->client.keys(pattern), ProtocolCommand.KEYS, args);
    }

    @Override
    public Set<byte[]> keys(final byte[] pattern){
        final CommandArguments args = CommandArguments.getInstance().put("pattern", pattern);
        return execute((client)->client.keys(pattern), ProtocolCommand.KEYS, args);
    }

    @Override
    public Status persist(final String key){
        final CommandArguments args = CommandArguments.getInstance().put("key", key);
        return execute((client)->client.persist(makeRawKey(key)), ProtocolCommand.PERSIST, args);
    }

    @Override
    public Status persist(final byte[] key){
        final CommandArguments args = CommandArguments.getInstance().put("key", key);
        return execute((client)->client.persist(makeByteKey(key)), ProtocolCommand.PERSIST, args);
    }

    @Override
    public Status pExpire(final String key, final int lifetime){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("lifetime", lifetime);
        return execute((client)->client.pExpire(makeRawKey(key), lifetime), ProtocolCommand.PEXPIRE, args);
    }

    @Override
    public Status pExpire(final byte[] key, final int lifetime){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("lifetime", lifetime);
        return execute((client)->client.pExpire(makeByteKey(key), lifetime), ProtocolCommand.PEXPIRE, args);
    }

    @Override
    public Status pExpireAt(final String key, final long unixTimestamp){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("unixTimestamp", unixTimestamp);
        return execute((client)->client.pExpireAt(makeRawKey(key), unixTimestamp), ProtocolCommand.PEXPIREAT, args);
    }

    @Override
    public Status pExpireAt(final byte[] key, final long unixTimestamp){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("unixTimestamp", unixTimestamp);
        return execute((client)->client.pExpireAt(makeByteKey(key), unixTimestamp), ProtocolCommand.PEXPIREAT, args);
    }

    @Override
    public Long pTtl(final String key){
        final CommandArguments args = CommandArguments.getInstance().put("key", key);
        return execute((client)->client.pTtl(makeRawKey(key)), ProtocolCommand.PTTL, args);
    }

    @Override
    public Long pTtl(final byte[] key){
        final CommandArguments args = CommandArguments.getInstance().put("key", key);
        return execute((client)->client.pTtl(makeByteKey(key)), ProtocolCommand.PTTL, args);
    }

    @Override
    public String randomKey(){
        return execute((client)->client.randomKey(), ProtocolCommand.RANDOMKEY);
    }

    @Override
    public Status rename(final String key, final String newKey){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("newKey", newKey);
        return execute((client)->client.rename(makeRawKey(key), makeRawKey(newKey)), ProtocolCommand.RENAME, args);
    }

    @Override
    public Status rename(final byte[] key, final byte[] newKey){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("newKey", newKey);
        return execute((client)->client.rename(makeByteKey(key), makeByteKey(newKey)), ProtocolCommand.RENAME, args);
    }

    @Override
    public Status renameNx(final String key, final String newKey){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("newKey", newKey);
        return execute((client)->client.renameNx(makeRawKey(key), makeRawKey(newKey)), ProtocolCommand.RENAMENX, args);
    }

    @Override
    public Status renameNx(final byte[] key, final byte[] newKey){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("newKey", newKey);
        return execute((client)->client.renameNx(makeByteKey(key), makeByteKey(newKey)), ProtocolCommand.RENAMENX, args);
    }

    @Override
    public Status restore(final String key, final String serializedValue, final int ttl){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("serializedValue", serializedValue).put("ttl", ttl);
        return execute((client)->client.restore(makeRawKey(key), serializedValue, ttl), ProtocolCommand.RESTORE, args);
    }

    @Override
    public Status restore(final byte[] key, final byte[] serializedValue, final int ttl){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("serializedValue", serializedValue).put("ttl", ttl);
        return execute((client)->client.restore(makeByteKey(key), serializedValue, ttl), ProtocolCommand.RESTORE, args);
    }

    @Override
    public ScanResult<List<String>> scan(final int cursor){
        final CommandArguments args = CommandArguments.getInstance().put("cursor", cursor);
        return execute((client)->client.scan(cursor), ProtocolCommand.SCAN, args);
    }

    @Override
    public ScanResult<List<String>> scan(final long cursor){
        final CommandArguments args = CommandArguments.getInstance().put("cursor", cursor);
        return execute((client)->client.scan(cursor), ProtocolCommand.SCAN, args);
    }

    @Override
    public ScanResult<List<String>> scan(final String cursor){
        final CommandArguments args = CommandArguments.getInstance().put("cursor", cursor);
        return execute((client)->client.scan(cursor), ProtocolCommand.SCAN, args);
    }

    @Override
    public ScanResult<List<byte[]>> scan(final byte[] cursor){
        final CommandArguments args = CommandArguments.getInstance().put("cursor", cursor);
        return execute((client)->client.scan(cursor), ProtocolCommand.SCAN, args);
    }

    @Override
    public ScanResult<List<String>> scan(final int cursor, final String pattern){
        final CommandArguments args = CommandArguments.getInstance().put("cursor", cursor).put("pattern", pattern);
        return execute((client)->client.scan(cursor, pattern), ProtocolCommand.SCAN, args);
    }

    @Override
    public ScanResult<List<byte[]>> scan(final int cursor, final byte[] pattern){
        final CommandArguments args = CommandArguments.getInstance().put("cursor", cursor).put("pattern", pattern);
        return execute((client)->client.scan(cursor, pattern), ProtocolCommand.SCAN, args);
    }

    @Override
    public ScanResult<List<String>> scan(final long cursor, final String pattern){
        final CommandArguments args = CommandArguments.getInstance().put("cursor", cursor).put("pattern", pattern);
        return execute((client)->client.scan(cursor, pattern), ProtocolCommand.SCAN, args);
    }

    @Override
    public ScanResult<List<byte[]>> scan(final long cursor, final byte[] pattern){
        final CommandArguments args = CommandArguments.getInstance().put("cursor", cursor).put("pattern", pattern);
        return execute((client)->client.scan(cursor, pattern), ProtocolCommand.SCAN, args);
    }

    @Override
    public ScanResult<List<String>> scan(final String cursor, final String pattern){
        final CommandArguments args = CommandArguments.getInstance().put("cursor", cursor).put("pattern", pattern);
        return execute((client)->client.scan(cursor, pattern), ProtocolCommand.SCAN, args);
    }

    @Override
    public ScanResult<List<byte[]>> scan(final byte[] cursor, final byte[] pattern){
        final CommandArguments args = CommandArguments.getInstance().put("cursor", cursor).put("pattern", pattern);
        return execute((client)->client.scan(cursor, pattern), ProtocolCommand.SCAN, args);
    }

    @Override
    public ScanResult<List<String>> scan(final int cursor, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("cursor", cursor).put("count", count);
        return execute((client)->client.scan(cursor, count), ProtocolCommand.SCAN, args);
    }

    @Override
    public ScanResult<List<String>> scan(final long cursor, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("cursor", cursor).put("count", count);
        return execute((client)->client.scan(cursor, count), ProtocolCommand.SCAN, args);
    }

    @Override
    public ScanResult<List<String>> scan(final String cursor, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("cursor", cursor).put("count", count);
        return execute((client)->client.scan(cursor, count), ProtocolCommand.SCAN, args);
    }

    @Override
    public ScanResult<List<byte[]>> scan(final byte[] cursor, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("cursor", cursor).put("count", count);
        return execute((client)->client.scan(cursor, count), ProtocolCommand.SCAN, args);
    }

    @Override
    public ScanResult<List<String>> scan(final int cursor, final String pattern, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("cursor", cursor).put("pattern", pattern).put("count", count);
        return execute((client)->client.scan(cursor, pattern, count), ProtocolCommand.SCAN, args);
    }

    @Override
    public ScanResult<List<byte[]>> scan(final int cursor, final byte[] pattern, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("cursor", cursor).put("pattern", pattern).put("count", count);
        return execute((client)->client.scan(cursor, pattern, count), ProtocolCommand.SCAN, args);
    }

    @Override
    public ScanResult<List<String>> scan(final long cursor, final String pattern, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("cursor", cursor).put("pattern", pattern).put("count", count);
        return execute((client)->client.scan(cursor, pattern, count), ProtocolCommand.SCAN, args);
    }

    @Override
    public ScanResult<List<byte[]>> scan(final long cursor, final byte[] pattern, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("cursor", cursor).put("pattern", pattern).put("count", count);
        return execute((client)->client.scan(cursor, pattern, count), ProtocolCommand.SCAN, args);
    }

    @Override
    public ScanResult<List<String>> scan(final String cursor, final String pattern, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("cursor", cursor).put("pattern", pattern).put("count", count);
        return execute((client)->client.scan(cursor, pattern, count), ProtocolCommand.SCAN, args);
    }

    @Override
    public ScanResult<List<byte[]>> scan(final byte[] cursor, final byte[] pattern, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("cursor", cursor).put("pattern", pattern).put("count", count);
        return execute((client)->client.scan(cursor, pattern, count), ProtocolCommand.SCAN, args);
    }

    @Override
    public List<String> sort(final String key){
        final CommandArguments args = CommandArguments.getInstance().put("key", key);
        return execute((client)->client.sort(makeRawKey(key)), ProtocolCommand.SORT, args);
    }

    @Override
    public List<byte[]> sort(final byte[] key){
        final CommandArguments args = CommandArguments.getInstance().put("key", key);
        return execute((client)->client.sort(makeByteKey(key)), ProtocolCommand.SORT, args);
    }

    @Override
    public List<String> sort(final String key, final SortArgument sortArgument){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("sortArgument", sortArgument);
        return execute((client)->client.sort(makeRawKey(key), sortArgument), ProtocolCommand.SORT, args);
    }

    @Override
    public List<byte[]> sort(final byte[] key, final SortArgument sortArgument){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("sortArgument", sortArgument);
        return execute((client)->client.sort(makeByteKey(key), sortArgument), ProtocolCommand.SORT, args);
    }

    @Override
    public Long sort(final String key, final String destKey){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("destKey", destKey);
        return execute((client)->client.sort(makeRawKey(key), makeRawKey(destKey)), ProtocolCommand.SORT, args);
    }

    @Override
    public Long sort(final byte[] key, final byte[] destKey){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("destKey", destKey);
        return execute((client)->client.sort(makeByteKey(key), makeByteKey(destKey)), ProtocolCommand.SORT, args);
    }

    @Override
    public Long sort(final String key, final String destKey, final SortArgument sortArgument){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("destKey", destKey).put("sortArgument", sortArgument);
        return execute((client)->client.sort(makeRawKey(key), makeRawKey(destKey), sortArgument), ProtocolCommand.SORT, args);
    }

    @Override
    public Long sort(final byte[] key, final byte[] destKey, final SortArgument sortArgument){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("destKey", destKey).put("sortArgument", sortArgument);
        return execute((client)->client.sort(makeByteKey(key), makeByteKey(destKey), sortArgument), ProtocolCommand.SORT, args);
    }

    @Override
    public Long ttl(final String key){
        final CommandArguments args = CommandArguments.getInstance().put("key", key);
        return execute((client)->client.ttl(makeRawKey(key)), ProtocolCommand.TTL, args);
    }

    @Override
    public Long ttl(final byte[] key){
        final CommandArguments args = CommandArguments.getInstance().put("key", key);
        return execute((client)->client.ttl(makeByteKey(key)), ProtocolCommand.TTL, args);
    }

    @Override
    public Type type(final String key){
        final CommandArguments args = CommandArguments.getInstance().put("key", key);
        return execute((client)->client.type(makeRawKey(key)), ProtocolCommand.TYPE, args);
    }

    @Override
    public Type type(final byte[] key){
        final CommandArguments args = CommandArguments.getInstance().put("key", key);
        return execute((client)->client.type(makeByteKey(key)), ProtocolCommand.TYPE, args);
    }

    @Override
    public Long touch(final String... keys){
        final CommandArguments args = CommandArguments.getInstance().put("keys", keys);
        return execute((client)->client.touch(makeRawKeys(keys)), ProtocolCommand.TOUCH, args);
    }

    @Override
    public Long touch(final byte[]... keys){
        final CommandArguments args = CommandArguments.getInstance().put("keys", keys);
        return execute((client)->client.touch(makeByteKeys(keys)), ProtocolCommand.TOUCH, args);
    }

    @Override
    public Long unlink(final String... keys){
        final CommandArguments args = CommandArguments.getInstance().put("keys", keys);
        return execute((client)->client.unlink(makeRawKeys(keys)), ProtocolCommand.UNLINK, args);
    }

    @Override
    public Long unlink(final byte[]... keys){
        final CommandArguments args = CommandArguments.getInstance().put("keys", keys);
        return execute((client)->client.unlink(makeByteKeys(keys)), ProtocolCommand.UNLINK, args);
    }

    @Override
    public List<String> blPop(final String[] keys, final int timeout){
        final CommandArguments args = CommandArguments.getInstance().put("keys", keys).put("timeout", timeout);
        return execute((client)->client.blPop(makeRawKeys(keys), timeout), ProtocolCommand.BLPOP, args);
    }

    @Override
    public List<byte[]> blPop(final byte[][] keys, final int timeout){
        final CommandArguments args = CommandArguments.getInstance().put("keys", keys).put("timeout", timeout);
        return execute((client)->client.blPop(makeByteKeys(keys), timeout), ProtocolCommand.BLPOP, args);
    }

    @Override
    public List<String> brPop(final String[] keys, final int timeout){
        final CommandArguments args = CommandArguments.getInstance().put("keys", keys).put("timeout", timeout);
        return execute((client)->client.brPop(makeRawKeys(keys), timeout), ProtocolCommand.BRPOP, args);
    }

    @Override
    public List<byte[]> brPop(final byte[][] keys, final int timeout){
        final CommandArguments args = CommandArguments.getInstance().put("keys", keys).put("timeout", timeout);
        return execute((client)->client.brPop(makeByteKeys(keys), timeout), ProtocolCommand.BRPOP, args);
    }

    @Override
    public String brPoplPush(final String key, final String destKey, final int timeout){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("destKey", destKey).put("timeout", timeout);
        return execute((client)->client.brPoplPush(makeRawKey(key), makeRawKey(destKey), timeout), ProtocolCommand.BRPOPLPUSH, args);
    }

    @Override
    public byte[] brPoplPush(final byte[] key, final byte[] destKey, final int timeout){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("destKey", destKey).put("timeout", timeout);
        return execute((client)->client.brPoplPush(makeByteKey(key), makeByteKey(destKey), timeout), ProtocolCommand.BRPOPLPUSH, args);
    }

    @Override
    public String lIndex(final String key, final int index){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("index", index);
        return execute((client)->client.lIndex(makeRawKey(key), index), ProtocolCommand.LINDEX, args);
    }

    @Override
    public byte[] lIndex(final byte[] key, final int index){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("index", index);
        return execute((client)->client.lIndex(makeByteKey(key), index), ProtocolCommand.LINDEX, args);
    }

    @Override
    public String lIndex(final String key, final long index){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("index", index);
        return execute((client)->client.lIndex(makeRawKey(key), index), ProtocolCommand.LINDEX, args);
    }

    @Override
    public byte[] lIndex(final byte[] key, final long index){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("index", index);
        return execute((client)->client.lIndex(makeByteKey(key), index), ProtocolCommand.LINDEX, args);
    }

    @Override
    public Long lInsert(final String key, final String value, final ListPosition position, final String pivot){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("value", value).put("position", position).put("pivot", pivot);
        return execute((client)->client.lInsert(makeRawKey(key), value, position, pivot), ProtocolCommand.LINSERT, args);
    }

    @Override
    public Long lInsert(final byte[] key, final byte[] value, final ListPosition position, final byte[] pivot){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("value", value).put("position", position).put("pivot", pivot);
        return execute((client)->client.lInsert(makeByteKey(key), value, position, pivot), ProtocolCommand.LINSERT, args);
    }

    @Override
    public Long lLen(final String key){
        final CommandArguments args = CommandArguments.getInstance().put("key", key);
        return execute((client)->client.lLen(makeRawKey(key)), ProtocolCommand.LLEN, args);
    }

    @Override
    public Long lLen(final byte[] key){
        final CommandArguments args = CommandArguments.getInstance().put("key", key);
        return execute((client)->client.lLen(makeByteKey(key)), ProtocolCommand.LLEN, args);
    }

    @Override
    public String lPop(final String key){
        final CommandArguments args = CommandArguments.getInstance().put("key", key);
        return execute((client)->client.lPop(makeRawKey(key)), ProtocolCommand.LPOP, args);
    }

    @Override
    public byte[] lPop(final byte[] key){
        final CommandArguments args = CommandArguments.getInstance().put("key", key);
        return execute((client)->client.lPop(makeByteKey(key)), ProtocolCommand.LPOP, args);
    }

    @Override
    public Long lPush(final String key, final String... values){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("values", values);
        return execute((client)->client.lPush(makeRawKey(key), values), ProtocolCommand.LPUSH, args);
    }

    @Override
    public Long lPush(final byte[] key, final byte[]... values){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("values", values);
        return execute((client)->client.lPush(makeByteKey(key), values), ProtocolCommand.LPUSH, args);
    }

    @Override
    public Long lPushX(final String key, final String... values){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("values", values);
        return execute((client)->client.lPushX(makeRawKey(key), values), ProtocolCommand.LPUSHX, args);
    }

    @Override
    public Long lPushX(final byte[] key, final byte[]... values){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("values", values);
        return execute((client)->client.lPushX(makeByteKey(key), values), ProtocolCommand.LPUSHX, args);
    }

    @Override
    public List<String> lRange(final String key, final int start, final int end){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("start", start).put("end", end);
        return execute((client)->client.lRange(makeRawKey(key), start, end), ProtocolCommand.LRANGE, args);
    }

    @Override
    public List<byte[]> lRange(final byte[] key, final int start, final int end){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("start", start).put("end", end);
        return execute((client)->client.lRange(makeByteKey(key), start, end), ProtocolCommand.LRANGE, args);
    }

    @Override
    public List<String> lRange(final String key, final long start, final long end){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("start", start).put("end", end);
        return execute((client)->client.lRange(makeRawKey(key), start, end), ProtocolCommand.LRANGE, args);
    }

    @Override
    public List<byte[]> lRange(final byte[] key, final long start, final long end){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("start", start).put("end", end);
        return execute((client)->client.lRange(makeByteKey(key), start, end), ProtocolCommand.LRANGE, args);
    }

    @Override
    public Long lRem(final String key, final String value, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("value", value).put("count", count);
        return execute((client)->client.lRem(makeRawKey(key), value, count), ProtocolCommand.LREM, args);
    }

    @Override
    public Long lRem(final byte[] key, final byte[] value, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("value", value).put("count", count);
        return execute((client)->client.lRem(makeByteKey(key), value, count), ProtocolCommand.LREM, args);
    }

    @Override
    public Long lRem(final String key, final String value, final long count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("value", value).put("count", count);
        return execute((client)->client.lRem(makeRawKey(key), value, count), ProtocolCommand.LREM, args);
    }

    @Override
    public Long lRem(final byte[] key, final byte[] value, final long count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("value", value).put("count", count);
        return execute((client)->client.lRem(makeByteKey(key), value, count), ProtocolCommand.LREM, args);
    }

    @Override
    public Status lSet(final String key, final int index, final String value){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("index", index).put("value", value);
        return execute((client)->client.lSet(makeRawKey(key), index, value), ProtocolCommand.LSET, args);
    }

    @Override
    public Status lSet(final byte[] key, final int index, final byte[] value){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("index", index).put("value", value);
        return execute((client)->client.lSet(makeByteKey(key), index, value), ProtocolCommand.LSET, args);
    }

    @Override
    public Status lSet(final String key, final long index, final String value){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("index", index).put("value", value);
        return execute((client)->client.lSet(makeRawKey(key), index, value), ProtocolCommand.LSET, args);
    }

    @Override
    public Status lSet(final byte[] key, final long index, final byte[] value){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("index", index).put("value", value);
        return execute((client)->client.lSet(makeByteKey(key), index, value), ProtocolCommand.LSET, args);
    }

    @Override
    public Status lTrim(final String key, final int start, final int end){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("start", start).put("end", end);
        return execute((client)->client.lTrim(makeRawKey(key), start, end), ProtocolCommand.LTRIM, args);
    }

    @Override
    public Status lTrim(final byte[] key, final int start, final int end){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("start", start).put("end", end);
        return execute((client)->client.lTrim(makeByteKey(key), start, end), ProtocolCommand.LTRIM, args);
    }

    @Override
    public Status lTrim(final String key, final long start, final long end){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("start", start).put("end", end);
        return execute((client)->client.lTrim(makeRawKey(key), start, end), ProtocolCommand.LTRIM, args);
    }

    @Override
    public Status lTrim(final byte[] key, final long start, final long end){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("start", start).put("end", end);
        return execute((client)->client.lTrim(makeByteKey(key), start, end), ProtocolCommand.LTRIM, args);
    }

    @Override
    public String rPop(final String key){
        final CommandArguments args = CommandArguments.getInstance().put("key", key);
        return execute((client)->client.rPop(makeRawKey(key)), ProtocolCommand.RPOP, args);
    }

    @Override
    public byte[] rPop(final byte[] key){
        final CommandArguments args = CommandArguments.getInstance().put("key", key);
        return execute((client)->client.rPop(makeByteKey(key)), ProtocolCommand.RPOP, args);
    }

    @Override
    public String rPoplPush(final String key, final String destKey){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("destKey", destKey);
        return execute((client)->client.rPoplPush(makeRawKey(key), makeRawKey(destKey)), ProtocolCommand.RPOPLPUSH, args);
    }

    @Override
    public byte[] rPoplPush(final byte[] key, final byte[] destKey){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("destKey", destKey);
        return execute((client)->client.rPoplPush(makeByteKey(key), makeByteKey(destKey)), ProtocolCommand.RPOPLPUSH, args);
    }

    @Override
    public Long rPush(final String key, final String... values){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("values", values);
        return execute((client)->client.rPush(makeRawKey(key), values), ProtocolCommand.RPUSH, args);
    }

    @Override
    public Long rPush(final byte[] key, final byte[]... values){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("values", values);
        return execute((client)->client.rPush(makeByteKey(key), values), ProtocolCommand.RPUSH, args);
    }

    @Override
    public Long rPushX(final String key, final String... values){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("values", values);
        return execute((client)->client.rPushX(makeRawKey(key), values), ProtocolCommand.RPUSHX, args);
    }

    @Override
    public Long rPushX(final byte[] key, final byte[]... values){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("values", values);
        return execute((client)->client.rPushX(makeByteKey(key), values), ProtocolCommand.RPUSHX, args);
    }

    @Override
    public void pSubscribe(final String[] patterns, final PubSubListener<String> pubSubListener){
        final CommandArguments args = CommandArguments.getInstance().put("patterns", patterns).put("pubSubListener", pubSubListener);

        execute(new Executor<Void>(){

            @Override
            public Void execute(RedisClient client){
                client.pSubscribe(patterns, pubSubListener);
                return null;
            }

        }, ProtocolCommand.PSUBSCRIBE, args);
    }

    @Override
    public void pSubscribe(final byte[][] patterns, final PubSubListener<byte[]> pubSubListener){
        final CommandArguments args = CommandArguments.getInstance().put("patterns", patterns).put("pubSubListener", pubSubListener);

        execute(new Executor<Void>(){

            @Override
            public Void execute(RedisClient client){
                client.pSubscribe(patterns, pubSubListener);
                return null;
            }

        }, ProtocolCommand.PSUBSCRIBE, args);
    }

	@Override
	public List<String> pubsubChannels(final String pattern){
        final CommandArguments args = CommandArguments.getInstance().put("pattern", pattern);
        return execute((client)->client.pubsubChannels(pattern), ProtocolCommand.PUBSUB, args);
	}

	@Override
	public List<byte[]> pubsubChannels(final byte[] pattern){
        final CommandArguments args = CommandArguments.getInstance().put("pattern", pattern);
        return execute((client)->client.pubsubChannels(pattern), ProtocolCommand.PUBSUB, args);
	}

	@Override
	public Long pubsubNumPat(){
		return execute((client)->client.pubsubNumPat(), ProtocolCommand.PUBSUB);
	}

	@Override
	public Map<String, String> pubsubNumSub(final String... channels){
        final CommandArguments args = CommandArguments.getInstance().put("channels", channels);
        return execute((client)->client.pubsubNumSub(channels), ProtocolCommand.PUBSUB, args);
	}

	@Override
	public Map<byte[], byte[]> pubsubNumSub(final byte[]... channels){
        final CommandArguments args = CommandArguments.getInstance().put("channels", channels);
        return execute((client)->client.pubsubNumSub(channels), ProtocolCommand.PUBSUB, args);
	}

    @Override
    public Long publish(final String channel, final String message){
        final CommandArguments args = CommandArguments.getInstance().put("channel", channel).put("message", message);
        return execute((client)->client.publish(channel, message), ProtocolCommand.PUBLISH, args);
    }

    @Override
    public Long publish(final byte[] channel, final byte[] message){
        final CommandArguments args = CommandArguments.getInstance().put("channel", channel).put("message", message);
        return execute((client)->client.publish(channel, message), ProtocolCommand.PUBLISH, args);
    }

    @Override
    public Object pUnSubscribe(){
        return execute((client)->client.pUnSubscribe(), ProtocolCommand.PUNSUBSCRIBE);
    }

    @Override
    public Object pUnSubscribe(final String... patterns){
        final CommandArguments args = CommandArguments.getInstance().put("patterns", patterns);
        return execute((client)->client.pUnSubscribe(patterns), ProtocolCommand.PUNSUBSCRIBE, args);
    }

    @Override
    public Object pUnSubscribe(final byte[]... patterns){
        final CommandArguments args = CommandArguments.getInstance().put("patterns", patterns);
        return execute((client)->client.pUnSubscribe(patterns), ProtocolCommand.PUNSUBSCRIBE, args);
    }

    @Override
    public void subscribe(final String[] channels, final PubSubListener<String> pubSubListener){
        final CommandArguments args = CommandArguments.getInstance().put("channels", channels).put("pubSubListener", pubSubListener);

        execute(new Executor<Void>(){

            @Override
            public Void execute(RedisClient client){
                client.subscribe(channels,pubSubListener);
                return null;
            }

        }, ProtocolCommand.SUBSCRIBE, args);
    }

    @Override
    public void subscribe(final byte[][] channels, final PubSubListener<byte[]> pubSubListener){
        final CommandArguments args = CommandArguments.getInstance().put("channels", channels).put("pubSubListener", pubSubListener);

        execute(new Executor<Void>(){

            @Override
            public Void execute(RedisClient client){
                client.subscribe(channels,pubSubListener);
                return null;
            }

        }, ProtocolCommand.SUBSCRIBE, args);
    }

    @Override
    public Object unSubscribe(){
        return execute((client)->client.unSubscribe(), ProtocolCommand.UNSUBSCRIBE);
    }

    @Override
    public Object unSubscribe(final String... channels){
        final CommandArguments args = CommandArguments.getInstance().put("channels", channels);
        return execute((client)->client.unSubscribe(channels), ProtocolCommand.UNSUBSCRIBE, args);
    }

    @Override
    public Object unSubscribe(final byte[]... channels){
        final CommandArguments args = CommandArguments.getInstance().put("channels", channels);
        return execute((client)->client.unSubscribe(channels), ProtocolCommand.UNSUBSCRIBE, args);
    }

    @Override
    public Object eval(final String script){
        final CommandArguments args = CommandArguments.getInstance().put("script", script);
        return execute((client)->client.eval(script), ProtocolCommand.EVAL, args);
    }

    @Override
    public Object eval(final byte[] script){
        final CommandArguments args = CommandArguments.getInstance().put("script", script);
        return execute((client)->client.eval(script), ProtocolCommand.EVAL, args);
    }

    @Override
    public Object eval(final String script,final String...params){
        final CommandArguments args = CommandArguments.getInstance().put("script", script).put("params", params);
        return execute((client)->client.eval(script, params), ProtocolCommand.EVAL, args);
    }

    @Override
    public Object eval(final byte[] script, final byte[]... params){
        final CommandArguments args = CommandArguments.getInstance().put("script", script).put("params", params);
        return execute((client)->client.eval(script, params), ProtocolCommand.EVAL, args);
    }

    @Override
    public Object eval(final String script, final String[] keys, final String[] arguments){
        final CommandArguments args = CommandArguments.getInstance().put("script", script).put("keys", keys).put("arguments", arguments);
        return execute((client)->client.eval(script, keys, arguments), ProtocolCommand.EVAL, args);
    }

    @Override
    public Object eval(final byte[] script, final byte[][] keys, final byte[][] arguments){
        final CommandArguments args = CommandArguments.getInstance().put("script", script).put("keys", keys).put("arguments", arguments);
        return execute((client)->client.eval(script, keys, arguments), ProtocolCommand.EVAL, args);
    }

    @Override
    public Object evalSha(final String digest){
        final CommandArguments args = CommandArguments.getInstance().put("digest", digest);
        return execute((client)->client.evalSha(digest), ProtocolCommand.EVALSHA, args);
    }

    @Override
    public Object evalSha(final byte[] digest){
        final CommandArguments args = CommandArguments.getInstance().put("digest", digest);
        return execute((client)->client.evalSha(digest), ProtocolCommand.EVALSHA, args);
    }

    @Override
    public Object evalSha(final String digest, final String... params){
        final CommandArguments args = CommandArguments.getInstance().put("digest", digest).put("params", params);
        return execute((client)->client.evalSha(digest, params), ProtocolCommand.EVALSHA, args);
    }

    @Override
    public Object evalSha(final byte[] digest ,final byte[]... params){
        final CommandArguments args = CommandArguments.getInstance().put("digest", digest).put("params", params);
        return execute((client)->client.evalSha(digest, params), ProtocolCommand.EVALSHA, args);
    }

    @Override
    public Object evalSha(final String digest, final String[] keys, final String[] arguments){
        final CommandArguments args = CommandArguments.getInstance().put("digest", digest).put("keys", keys).put("arguments", arguments);
        return execute((client)->client.evalSha(digest, keys, arguments), ProtocolCommand.EVALSHA, args);
    }

    @Override
    public Object evalSha(final byte[] digest, final byte[][] keys, final byte[][] arguments){
        final CommandArguments args = CommandArguments.getInstance().put("digest", digest).put("keys", keys).put("arguments", arguments);
        return execute((client)->client.evalSha(digest, keys, arguments), ProtocolCommand.EVALSHA, args);
    }

    @Override
    public List<Boolean> scriptExists(final String... sha1){
        final CommandArguments args = CommandArguments.getInstance().put("sha1", sha1);
        return execute((client)->client.scriptExists(sha1), ProtocolCommand.SCRIPT_EXISTS, args);
    }

    @Override
    public List<Boolean> scriptExists(final byte[]... sha1){
        final CommandArguments args = CommandArguments.getInstance().put("sha1", sha1);
        return execute((client)->client.scriptExists(sha1), ProtocolCommand.SCRIPT_EXISTS, args);
    }

    @Override
    public Status scriptFlush(){
        return execute((client)->client.scriptFlush(), ProtocolCommand.SCRIPT_FLUSH);
    }

    @Override
    public Status scriptKill(){
        return execute((client)->client.scriptKill(), ProtocolCommand.SCRIPT_KILL);
    }

    @Override
    public String scriptLoad(final String script){
        final CommandArguments args = CommandArguments.getInstance().put("script", script);
        return execute((client)->client.scriptLoad(script), ProtocolCommand.SCRIPT_LOAD, args);
    }

    @Override
    public byte[] scriptLoad(final byte[] script){
        final CommandArguments args = CommandArguments.getInstance().put("script", script);
        return execute((client)->client.scriptLoad(script), ProtocolCommand.SCRIPT_LOAD, args);
    }

    @Override
    public String bgRewriteAof(){
        return execute((client)->client.bgSave(), ProtocolCommand.BGREWRITEAOF);
    }

    @Override
    public String bgSave(){
        return execute((client)->client.bgSave(), ProtocolCommand.BGSAVE);
    }

    @Override
    public Status clientKill(final String host, final int port){
        final CommandArguments args = CommandArguments.getInstance().put("host", host).put("port", port);
        return execute((client)->client.clientKill(host, port), ProtocolCommand.CLIENT_KILL, args);
    }

    @Override
    public String clientGetName(){
        return execute((client)->client.clientGetName(), ProtocolCommand.CLIENT_GETNAME);
    }

    @Override
    public String clientId(){
        return execute((client)->client.clientId(), ProtocolCommand.CLIENT_ID);
    }

    @Override
    public List<Client> clientList(){
        return execute((client)->client.clientList(), ProtocolCommand.CLIENT_LIST);
    }

    @Override
    public Status clientPause(final int timeout){
        final CommandArguments args = CommandArguments.getInstance().put("timeout", timeout);
        return execute((client)->client.clientPause(timeout), ProtocolCommand.CLIENT_PAUSE, args);
    }

    @Override
    public Status clientPause(final long timeout){
        final CommandArguments args = CommandArguments.getInstance().put("timeout", timeout);
        return execute((client)->client.clientPause(timeout), ProtocolCommand.CLIENT_PAUSE, args);
    }

    @Override
    public Status clientReply(final ClientReply option){
        final CommandArguments args = CommandArguments.getInstance().put("option", option);
        return execute((client)->client.clientReply(option), ProtocolCommand.CLIENT_REPLY, args);
    }

    @Override
    public Status clientSetName(final String name){
        final CommandArguments args = CommandArguments.getInstance().put("name", name);
        return execute((client)->client.clientSetName(name), ProtocolCommand.CLIENT_SETNAME, args);
    }

    @Override
    public Status clientSetName(final byte[] name){
        final CommandArguments args = CommandArguments.getInstance().put("name", name);
        return execute((client)->client.clientSetName(name), ProtocolCommand.CLIENT_SETNAME, args);
    }

	@Override
	public Status clientUnblock(final int clientId){
	}
	final CommandArguments args = CommandArguments.getInstance().put("clientId", clientId);
	return execute((client)->client.clientUnblock(clientId, type), ProtocolCommand.CLIENT_UNBLOCK, args);

	@Override
	public Status clientUnblock(final int clientId, final ClientUnblockType type){
        final CommandArguments args = CommandArguments.getInstance().put("clientId", clientId).put("type", type);
        return execute((client)->client.clientUnblock(clientId, type), ProtocolCommand.CLIENT_UNBLOCK, args);
	}

    @Override
    public List<String> configGet(final String parameter){
        final CommandArguments args = CommandArguments.getInstance().put("parameter", parameter);
        return execute((client)->client.configGet(parameter), ProtocolCommand.CONFIG_GET, args);
    }

    @Override
    public List<byte[]> configGet(final byte[] parameter){
        final CommandArguments args = CommandArguments.getInstance().put("parameter", parameter);
        return execute((client)->client.configGet(parameter), ProtocolCommand.CONFIG_GET, args);
    }

    @Override
    public Status configResetStat(){
        return execute((client)->client.configResetStat(), ProtocolCommand.CONFIG_RESETSTAT);
    }

    @Override
    public Status configRewrite(){
        return execute((client)->client.configRewrite(), ProtocolCommand.CONFIG_REWRITE);
    }

    @Override
    public Status configSet(final String parameter, final float value){
        final CommandArguments args = CommandArguments.getInstance().put("parameter", parameter).put("value", value);
        return execute((client)->client.configSet(parameter, value), ProtocolCommand.CONFIG_SET, args);
    }

    @Override
    public Status configSet(final byte[] parameter, final float value){
        final CommandArguments args = CommandArguments.getInstance().put("parameter", parameter).put("value", value);
        return execute((client)->client.configSet(parameter, value), ProtocolCommand.CONFIG_SET, args);
    }

    @Override
    public Status configSet(final String parameter, final double value){
        final CommandArguments args = CommandArguments.getInstance().put("parameter", parameter).put("value", value);
        return execute((client)->client.configSet(parameter, value), ProtocolCommand.CONFIG_SET, args);
    }

    @Override
    public Status configSet(final byte[] parameter, final double value){
        final CommandArguments args = CommandArguments.getInstance().put("parameter", parameter).put("value", value);
        return execute((client)->client.configSet(parameter, value), ProtocolCommand.CONFIG_SET, args);
    }

    @Override
    public Status configSet(final String parameter, final int value){
        final CommandArguments args = CommandArguments.getInstance().put("parameter", parameter).put("value", value);
        return execute((client)->client.configSet(parameter, value), ProtocolCommand.CONFIG_SET, args);
    }

    @Override
    public Status configSet(final byte[] parameter, final int value){
        final CommandArguments args = CommandArguments.getInstance().put("parameter", parameter).put("value", value);
        return execute((client)->client.configSet(parameter, value), ProtocolCommand.CONFIG_SET, args);
    }

    @Override
    public Status configSet(final String parameter, final long value){
        final CommandArguments args = CommandArguments.getInstance().put("parameter", parameter).put("value", value);
        return execute((client)->client.configSet(parameter, value), ProtocolCommand.CONFIG_SET, args);
    }

    @Override
    public Status configSet(final byte[] parameter, final long value){
        final CommandArguments args = CommandArguments.getInstance().put("parameter", parameter).put("value", value);
        return execute((client)->client.configSet(parameter, value), ProtocolCommand.CONFIG_SET, args);
    }

    @Override
    public Status configSet(final String parameter, final String value){
        final CommandArguments args = CommandArguments.getInstance().put("parameter", parameter).put("value", value);
        return execute((client)->client.configSet(parameter, value), ProtocolCommand.CONFIG_SET, args);
    }

    @Override
    public Status configSet(final byte[] parameter, final byte[] value){
        final CommandArguments args = CommandArguments.getInstance().put("parameter", parameter).put("value", value);
        return execute((client)->client.configSet(parameter, value), ProtocolCommand.CONFIG_SET, args);
    }

    @Override
    public Long dbSize(){
        return execute((client)->client.dbSize(), ProtocolCommand.DBSIZE);
    }

    @Override
    public String debugObject(final String key){
        final CommandArguments args = CommandArguments.getInstance().put("key", key);
        return execute((client)->client.debugObject(makeRawKey(key)), ProtocolCommand.DEBUG_OBJECT, args);
    }

    @Override
    public String debugSegfault(){
        return execute((client)->client.debugSegfault(), ProtocolCommand.DEBUG_SEGFAULT);
    }

    @Override
    public Status flushAll(){
        return execute((client)->client.flushAll(), ProtocolCommand.FLUSHALL);
    }

    @Override
    public Status flushDb(){
        return execute((client)->client.flushDb(), ProtocolCommand.FLUSHDB);
    }

    @Override
    public Info info(final InfoSection section){
        final CommandArguments args = CommandArguments.getInstance().put("section", section);
        return execute((client)->client.info(), ProtocolCommand.INFO, args);
    }

    @Override
    public Info info(){
        return execute((client)->client.info(), ProtocolCommand.INFO);
    }

    @Override
    public Long lastSave(){
        return execute((client)->client.lastSave(), ProtocolCommand.LASTSAVE);
    }

	@Override
	public String memoryDoctor(){
        return execute((client)->client.memoryDoctor(), ProtocolCommand.MEMORY_DOCTOR);
	}

    @Override
    public void monitor(final RedisMonitor redisMonitor){
        final CommandArguments args = CommandArguments.getInstance().put("redisMonitor", redisMonitor);

        execute(new Executor<Void>() {

            @Override
            public Void execute(RedisClient client){
                client.monitor(redisMonitor);
                return null;
            }

        }, ProtocolCommand.MONITOR, args);
    }

    @Override
    public Object object(final ObjectCommand command, final String key){
        final CommandArguments args = CommandArguments.getInstance().put("command", command).put("key", key);
        return execute((client)->client.object(command, makeRawKey(key)), ProtocolCommand.OBJECT, args);
    }

    @Override
    public Object object(final DebugCommands.ObjectCommand command, final byte[] key){
        final CommandArguments args = CommandArguments.getInstance().put("command", command).put("key", key);
        return execute((client)->client.object(command, makeByteKey(key)), ProtocolCommand.OBJECT, args);
    }

    @Override
    public Status replicaOf(final String host, final int port){
        final CommandArguments args = CommandArguments.getInstance().put("host", host).put("port", port);
        return execute((client)->client.replicaOf(host, port), ProtocolCommand.REPLICAOF, args);
    }

    @Override
    public Role role(){
        return execute((client)->client.role(), ProtocolCommand.ROLE);
    }

    @Override
    public Status save(){
        return execute((client)->client.save(), ProtocolCommand.SAVE);
    }

    @Override
    public void shutdown(){
        execute(new Executor<Void>() {

            @Override
            public Void execute(RedisClient client){
                client.shutdown();
                return null;
            }

        }, ProtocolCommand.SHUTDOWN);
    }

    @Override
    public void shutdown(final boolean save){
        final CommandArguments args = CommandArguments.getInstance().put("save", save);

        execute(new Executor<Void>() {

            @Override
            public Void execute(RedisClient client){
                client.shutdown();
                return null;
            }

        }, ProtocolCommand.SHUTDOWN, args);
    }

    @Override
    public Status slaveOf(final String host, final int port){
        final CommandArguments args = CommandArguments.getInstance().put("host", host).put("port", port);
        return execute((client)->client.slaveOf(host, port), ProtocolCommand.SLAVEOF, args);
    }

    @Override
    public Object slowLog(final SlowLogCommand command, final String... arguments){
        final CommandArguments args = CommandArguments.getInstance().put("command", command).put("arguments", arguments);
        return execute((client)->client.slowLog(command, arguments), ProtocolCommand.SLOWLOG, args);
    }

    @Override
    public Object slowLog(final SlowLogCommand command, final byte[]... arguments){
        final CommandArguments args = CommandArguments.getInstance().put("command", command).put("arguments", arguments);
        return execute((client)->client.slowLog(command, arguments), ProtocolCommand.SLOWLOG, args);
    }

    @Override
    public Object sync(){
        return execute((client)->client.sync(), ProtocolCommand.SYNC);
    }

    @Override
    public Object pSync(final String masterRunId, final int offset){
        final CommandArguments args = CommandArguments.getInstance().put("masterRunId", masterRunId).put("offset", offset);
        return execute((client)->client.pSync(masterRunId, offset), ProtocolCommand.PSYNC, args);
    }

    @Override
    public Object pSync(final byte[] masterRunId, final int offset){
        final CommandArguments args = CommandArguments.getInstance().put("masterRunId", masterRunId).put("offset", offset);
        return execute((client)->client.pSync(masterRunId, offset), ProtocolCommand.PSYNC, args);
    }

    @Override
    public Object pSync(final String masterRunId, final long offset){
        final CommandArguments args = CommandArguments.getInstance().put("masterRunId", masterRunId).put("offset", offset);
        return execute((client)->client.pSync(masterRunId, offset), ProtocolCommand.PSYNC, args);
    }

    @Override
    public Object pSync(final byte[] masterRunId, final long offset){
        final CommandArguments args = CommandArguments.getInstance().put("masterRunId", masterRunId).put("offset", offset);
        return execute((client)->client.pSync(masterRunId, offset), ProtocolCommand.PSYNC, args);
    }

    @Override
    public RedisServerTime time(){
        return execute((client)->client.time(), ProtocolCommand.TIME);
    }

    @Override
    public Long sAdd(final String key, final String... members){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("members", members);
        return execute((client)->client.sAdd(makeRawKey(key), members), ProtocolCommand.SADD, args);
    }

    @Override
    public Long sAdd(final byte[] key, final byte[]... members){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("members", members);
        return execute((client)->client.sAdd(makeByteKey(key), members), ProtocolCommand.SADD, args);
    }

    @Override
    public Long sCard(final String key){
        final CommandArguments args = CommandArguments.getInstance().put("key", key);
        return execute((client)->client.sCard(makeRawKey(key)), ProtocolCommand.SCARD, args);
    }

    @Override
    public Long sCard(final byte[] key){
        final CommandArguments args = CommandArguments.getInstance().put("key", key);
        return execute((client)->client.sCard(makeByteKey(key)), ProtocolCommand.SCARD, args);
    }

    @Override
    public Set<String> sDiff(final String... keys){
        final CommandArguments args = CommandArguments.getInstance().put("keys", keys);
        return execute((client)->client.sDiff(makeRawKeys(keys)), ProtocolCommand.SDIFF, args);
    }

    @Override
    public Set<byte[]> sDiff(final byte[]... keys){
        final CommandArguments args = CommandArguments.getInstance().put("keys", keys);
        return execute((client)->client.sDiff(makeByteKeys(keys)), ProtocolCommand.SDIFF, args);
    }

    @Override
    public Long sDiffStore(final String destKey, final String... keys){
        final CommandArguments args = CommandArguments.getInstance().put("destKey", destKey).put("keys", keys);
        return execute((client)->client.sDiffStore(makeRawKey(destKey), makeRawKeys(keys)), ProtocolCommand.SDIFFSTORE, args);
    }

    @Override
    public Long sDiffStore(final byte[] destKey, final byte[]... keys){
        final CommandArguments args = CommandArguments.getInstance().put("destKey", destKey).put("keys", keys);
        return execute((client)->client.sDiffStore(makeByteKey(destKey), makeByteKeys(keys)), ProtocolCommand.SDIFFSTORE, args);
    }

    @Override
    public Set<String> sInter(final String... keys){
        final CommandArguments args = CommandArguments.getInstance().put("keys", keys);
        return execute((client)->client.sInter(makeRawKeys(keys)), ProtocolCommand.SINTER, args);
    }

    @Override
    public Set<byte[]> sInter(final byte[]... keys){
        final CommandArguments args = CommandArguments.getInstance().put("keys", keys);
        return execute((client)->client.sInter(makeByteKeys(keys)), ProtocolCommand.SINTER, args);
    }

    @Override
    public Long sInterStore(final String destKey, final String... keys){
        final CommandArguments args = CommandArguments.getInstance().put("destKey", destKey).put("keys", keys);
        return execute((client)->client.sDiffStore(makeRawKey(destKey), makeRawKeys(keys)), ProtocolCommand.SINTERSTORE, args);
    }

    @Override
    public Long sInterStore(final byte[] destKey, final byte[]... keys){
        final CommandArguments args = CommandArguments.getInstance().put("destKey", destKey).put("keys", keys);
        return execute((client)->client.sDiffStore(makeByteKey(destKey), makeByteKeys(keys)), ProtocolCommand.SINTERSTORE, args);
    }

    @Override
    public boolean sisMember(final String key, final String member){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("member", member);
        return execute((client)->client.sisMember(makeRawKey(key), member), ProtocolCommand.SISMEMBER, args);
    }

    @Override
    public boolean sisMember(final byte[] key, final byte[] member){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("member", member);
        return execute((client)->client.sisMember(makeByteKey(key), member), ProtocolCommand.SISMEMBER, args);
    }

    @Override
    public Set<String> sMembers(final String key){
        final CommandArguments args = CommandArguments.getInstance().put("key", key);
        return execute((client)->client.sMembers(makeRawKey(key)), ProtocolCommand.SMEMBERS, args);
    }

    @Override
    public Set<byte[]> sMembers(final byte[] key){
        final CommandArguments args = CommandArguments.getInstance().put("key", key);
        return execute((client)->client.sMembers(makeByteKey(key)), ProtocolCommand.SMEMBERS, args);
    }

    @Override
    public Status sMove(final String key, final String destKey, final String member){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("destKey", destKey).put("member", member);
        return execute((client)->client.sMove(makeRawKey(key), makeRawKey(destKey), member), ProtocolCommand.SMOVE, args);
    }

    @Override
    public Status sMove(final byte[] key, final byte[] destKey, final byte[] member){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("destKey", destKey).put("member", member);
        return execute((client)->client.sMove(makeByteKey(key), makeByteKey(destKey), member), ProtocolCommand.SMOVE, args);
    }

    @Override
    public String sPop(final String key){
        final CommandArguments args = CommandArguments.getInstance().put("key", key);
        return execute((client)->client.sPop(makeRawKey(key)), ProtocolCommand.SMEMBERS, args);
    }

    @Override
    public byte[] sPop(final byte[] key){
        final CommandArguments args = CommandArguments.getInstance().put("key", key);
        return execute((client)->client.sPop(makeByteKey(key)), ProtocolCommand.SMEMBERS, args);
    }

    @Override
    public String sRandMember(final String key){
        final CommandArguments args = CommandArguments.getInstance().put("key", key);
        return execute((client)->client.sRandMember(makeRawKey(key)), ProtocolCommand.SRANDMEMBER, args);
    }

    @Override
    public byte[] sRandMember(final byte[] key){
        final CommandArguments args = CommandArguments.getInstance().put("key", key);
        return execute((client)->client.sRandMember(makeByteKey(key)), ProtocolCommand.SRANDMEMBER, args);
    }

    @Override
    public List<String> sRandMember(final String key, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("count", count);
        return execute((client)->client.sRandMember(makeRawKey(key), count), ProtocolCommand.SRANDMEMBER, args);
    }

    @Override
    public List<byte[]> sRandMember(final byte[] key, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("count", count);
        return execute((client)->client.sRandMember(makeByteKey(key), count), ProtocolCommand.SRANDMEMBER, args);
    }

    @Override
    public List<String> sRandMember(final String key, final long count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("count", count);
        return execute((client)->client.sRandMember(makeRawKey(key), count), ProtocolCommand.SRANDMEMBER, args);
    }

    @Override
    public List<byte[]> sRandMember(final byte[] key, final long count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("count", count);
        return execute((client)->client.sRandMember(makeByteKey(key), count), ProtocolCommand.SRANDMEMBER, args);
    }

    @Override
    public Long sRem(final String key, final String... members){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("members", members);
        return execute((client)->client.sRem(makeRawKey(key), members), ProtocolCommand.SREM, args);
    }

    @Override
    public Long sRem(final byte[] key, final byte[]... members){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("members", members);
        return execute((client)->client.sRem(makeByteKey(key), members), ProtocolCommand.SREM, args);
    }

    @Override
    public ScanResult<List<String>> sScan(final String key, final int cursor){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor);
        return execute((client)->client.sScan(makeRawKey(key), cursor), ProtocolCommand.SSCAN, args);
    }

    @Override
    public ScanResult<List<byte[]>> sScan(final byte[] key, final int cursor){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor);
        return execute((client)->client.sScan(makeByteKey(key), cursor), ProtocolCommand.SSCAN, args);
    }

    @Override
    public ScanResult<List<String>> sScan(final String key, final long cursor){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor);
        return execute((client)->client.sScan(makeRawKey(key), cursor), ProtocolCommand.SSCAN, args);
    }

    @Override
    public ScanResult<List<byte[]>> sScan(final byte[] key, final long cursor){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor);
        return execute((client)->client.sScan(makeByteKey(key), cursor), ProtocolCommand.SSCAN, args);
    }

    @Override
    public ScanResult<List<String>> sScan(final String key, final String cursor){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor);
        return execute((client)->client.sScan(makeRawKey(key), cursor), ProtocolCommand.SSCAN, args);
    }

    @Override
    public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor);
        return execute((client)->client.sScan(makeByteKey(key), cursor), ProtocolCommand.SSCAN, args);
    }

    @Override
    public ScanResult<List<String>> sScan(final String key, final int cursor, final String pattern){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("pattern", pattern);
        return execute((client)->client.sScan(makeRawKey(key), cursor, pattern), ProtocolCommand.SSCAN, args);
    }

    @Override
    public ScanResult<List<byte[]>> sScan(final byte[] key, final int cursor, final byte[] pattern){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("pattern", pattern);
        return execute((client)->client.sScan(makeByteKey(key), cursor, pattern), ProtocolCommand.SSCAN, args);
    }

    @Override
    public ScanResult<List<String>> sScan(final String key, final long cursor, final String pattern){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("pattern", pattern);
        return execute((client)->client.sScan(makeRawKey(key), cursor, pattern), ProtocolCommand.SSCAN, args);
    }

    @Override
    public ScanResult<List<byte[]>> sScan(final byte[] key, final long cursor, final byte[] pattern){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("pattern", pattern);
        return execute((client)->client.sScan(makeByteKey(key), cursor, pattern), ProtocolCommand.SSCAN, args);
    }

    @Override
    public ScanResult<List<String>> sScan(final String key, final String cursor, final String pattern){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("pattern", pattern);
        return execute((client)->client.sScan(makeRawKey(key), cursor, pattern), ProtocolCommand.SSCAN, args);
    }

    @Override
    public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor, final byte[] pattern){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("pattern", pattern);
        return execute((client)->client.sScan(makeByteKey(key), cursor, pattern), ProtocolCommand.SSCAN, args);
    }

    @Override
    public ScanResult<List<String>> sScan(final String key, final int cursor, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("count", count);
        return execute((client)->client.sScan(makeRawKey(key), cursor, count), ProtocolCommand.SSCAN, args);
    }

    @Override
    public ScanResult<List<byte[]>> sScan(final byte[] key, final int cursor, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("count", count);
        return execute((client)->client.sScan(makeByteKey(key), cursor, count), ProtocolCommand.SSCAN, args);
    }

    @Override
    public ScanResult<List<String>> sScan(final String key, final long cursor, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("count", count);
        return execute((client)->client.sScan(makeRawKey(key), cursor, count), ProtocolCommand.SSCAN, args);
    }

    @Override
    public ScanResult<List<byte[]>> sScan(final byte[] key, final long cursor, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("count", count);
        return execute((client)->client.sScan(makeByteKey(key), cursor, count), ProtocolCommand.SSCAN, args);
    }

    @Override
    public ScanResult<List<String>> sScan(final String key, final String cursor, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("count", count);
        return execute((client)->client.sScan(makeRawKey(key), cursor, count), ProtocolCommand.SSCAN, args);
    }

    @Override
    public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("count", count);
        return execute((client)->client.sScan(makeByteKey(key), cursor, count), ProtocolCommand.SSCAN, args);
    }

    @Override
    public ScanResult<List<String>> sScan(final String key, final int cursor, final String pattern, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("pattern", pattern).put("count", count);
        return execute((client)->client.sScan(makeRawKey(key), cursor, pattern, count), ProtocolCommand.SSCAN, args);
    }

    @Override
    public ScanResult<List<byte[]>> sScan(final byte[] key, final int cursor, final byte[] pattern, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("pattern", pattern).put("count", count);
        return execute((client)->client.sScan(makeByteKey(key), cursor, pattern, count), ProtocolCommand.SSCAN, args);
    }

    @Override
    public ScanResult<List<String>> sScan(final String key, final long cursor, final String pattern, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("pattern", pattern).put("count", count);
        return execute((client)->client.sScan(makeRawKey(key), cursor, pattern, count), ProtocolCommand.SSCAN, args);
    }

    @Override
    public ScanResult<List<byte[]>> sScan(final byte[] key, final long cursor, final byte[] pattern, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("pattern", pattern).put("count", count);
        return execute((client)->client.sScan(makeByteKey(key), cursor, pattern, count), ProtocolCommand.SSCAN, args);
    }

    @Override
    public ScanResult<List<String>> sScan(final String key, final String cursor, final String pattern, final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("pattern", pattern).put("count", count);
        return execute((client)->client.sScan(makeRawKey(key), cursor, pattern, count), ProtocolCommand.SSCAN, args);
    }

    @Override
    public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor, final byte[] pattern,
     final int count){
        final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("pattern", pattern).put("count", count);
        return execute ((client)->client.sScan(makeByteKey(key), cursor, pattern, count), ProtocolCommand.SSCAN, args);
    }

}
