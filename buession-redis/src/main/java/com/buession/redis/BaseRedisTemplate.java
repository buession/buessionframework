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

}
