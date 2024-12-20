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
package com.buession.redis;

import com.buession.core.collect.Maps;
import com.buession.lang.Geo;
import com.buession.lang.KeyValue;
import com.buession.lang.Status;
import com.buession.redis.client.connection.datasource.DataSource;
import com.buession.redis.core.AclCategory;
import com.buession.redis.core.AclLog;
import com.buession.redis.core.Aggregate;
import com.buession.redis.core.BitType;
import com.buession.redis.core.BitOperation;
import com.buession.redis.core.Direction;
import com.buession.redis.core.ExpireOption;
import com.buession.redis.core.GeoRadius;
import com.buession.redis.core.GeoUnit;
import com.buession.redis.core.GtLt;
import com.buession.redis.core.Info;
import com.buession.redis.core.ListPosition;
import com.buession.redis.core.MemoryStats;
import com.buession.redis.core.Module;
import com.buession.redis.core.NxXx;
import com.buession.redis.core.ObjectEncoding;
import com.buession.redis.core.PubSubListener;
import com.buession.redis.core.RedisMonitor;
import com.buession.redis.core.RedisServerTime;
import com.buession.redis.core.Role;
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.FlushMode;
import com.buession.redis.core.SlowLog;
import com.buession.redis.core.Stream;
import com.buession.redis.core.StreamConsumer;
import com.buession.redis.core.StreamEntry;
import com.buession.redis.core.StreamEntryId;
import com.buession.redis.core.StreamFull;
import com.buession.redis.core.StreamGroup;
import com.buession.redis.core.StreamPending;
import com.buession.redis.core.StreamPendingSummary;
import com.buession.redis.core.Tuple;
import com.buession.redis.core.Type;
import com.buession.redis.core.AclUser;
import com.buession.redis.core.ZRangeBy;
import com.buession.redis.core.command.Command;
import com.buession.redis.core.command.args.*;
import com.buession.redis.transaction.Transaction;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Redis 基本操作 Template
 *
 * @author Yong.Teng
 */
public abstract class BaseRedisTemplate extends AbstractRedisTemplate {

	/**
	 * 构造函数
	 */
	public BaseRedisTemplate() {
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		数据源
	 */
	public BaseRedisTemplate(DataSource dataSource) {
		super(dataSource);
	}

	@Override
	public Long bitCount(final String key) {
		return execute((client)->client.bitMapOperations().bitCount(rawKey(key)));
	}

	@Override
	public Long bitCount(final byte[] key) {
		return execute((client)->client.bitMapOperations().bitCount(rawKey(key)));
	}

	@Override
	public Long bitCount(final String key, final long start, final long end) {
		return execute((client)->client.bitMapOperations().bitCount(rawKey(key), start, end));
	}

	@Override
	public Long bitCount(final byte[] key, final long start, final long end) {
		return execute((client)->client.bitMapOperations().bitCount(rawKey(key), start, end));
	}

	@Override
	public Long bitCount(final String key, final long start, final long end, final BitType bitType) {
		return execute((client)->client.bitMapOperations().bitCount(rawKey(key), start, end, bitType));
	}

	@Override
	public Long bitCount(final byte[] key, final long start, final long end, final BitType bitType) {
		return execute((client)->client.bitMapOperations().bitCount(rawKey(key), start, end, bitType));
	}

	@Override
	public List<Long> bitField(final String key) {
		return execute((client)->client.bitMapOperations().bitField(rawKey(key)));
	}

	@Override
	public List<Long> bitField(final byte[] key) {
		return execute((client)->client.bitMapOperations().bitField(rawKey(key)));
	}

	@Override
	public List<Long> bitField(final String key, final BitFieldArgument argument) {
		return execute((client)->client.bitMapOperations().bitField(rawKey(key), argument));
	}

	@Override
	public List<Long> bitField(final byte[] key, final BitFieldArgument argument) {
		return execute((client)->client.bitMapOperations().bitField(rawKey(key), argument));
	}

	@Override
	public List<Long> bitFieldRo(final String key) {
		return execute((client)->client.bitMapOperations().bitFieldRo(rawKey(key)));
	}

	@Override
	public List<Long> bitFieldRo(final byte[] key) {
		return execute((client)->client.bitMapOperations().bitFieldRo(rawKey(key)));
	}

	@Override
	public List<Long> bitFieldRo(final String key, final BitFieldRoArgument argument) {
		return execute((client)->client.bitMapOperations().bitFieldRo(rawKey(key), argument));
	}

	@Override
	public List<Long> bitFieldRo(final byte[] key, final BitFieldRoArgument argument) {
		return execute((client)->client.bitMapOperations().bitFieldRo(rawKey(key), argument));
	}

	@Override
	public Long bitOp(final BitOperation operation, final String destKey, final String... keys) {
		return execute((client)->client.bitMapOperations().bitOp(operation, rawKey(destKey), rawKeys(keys)));
	}

	@Override
	public Long bitOp(final BitOperation operation, final byte[] destKey, final byte[]... keys) {
		return execute((client)->client.bitMapOperations().bitOp(operation, rawKey(destKey), rawKeys(keys)));
	}

	@Override
	public Long bitPos(final String key, final boolean value) {
		return execute((client)->client.bitMapOperations().bitPos(rawKey(key), value));
	}

	@Override
	public Long bitPos(final byte[] key, final boolean value) {
		return execute((client)->client.bitMapOperations().bitPos(rawKey(key), value));
	}

	@Override
	public Long bitPos(final String key, final boolean value, final long start) {
		return execute((client)->client.bitMapOperations().bitPos(rawKey(key), value, start));
	}

	@Override
	public Long bitPos(final byte[] key, final boolean value, final long start) {
		return execute((client)->client.bitMapOperations().bitPos(rawKey(key), value, start));
	}

	@Override
	public Long bitPos(final String key, final boolean value, final long start, final BitType bitType) {
		return execute((client)->client.bitMapOperations().bitPos(rawKey(key), value, start, bitType));
	}

	@Override
	public Long bitPos(final byte[] key, final boolean value, final long start, final BitType bitType) {
		return execute((client)->client.bitMapOperations().bitPos(rawKey(key), value, start, bitType));
	}

	@Override
	public Long bitPos(final String key, final boolean value, final long start, final long end) {
		return execute((client)->client.bitMapOperations().bitPos(rawKey(key), value, start, end));
	}

	@Override
	public Long bitPos(final byte[] key, final boolean value, final long start, final long end) {
		return execute((client)->client.bitMapOperations().bitPos(rawKey(key), value, start, end));
	}

	@Override
	public Long bitPos(final String key, final boolean value, final long start, final long end,
					   final BitType bitType) {
		return execute((client)->client.bitMapOperations().bitPos(rawKey(key), value, start, end, bitType));
	}

	@Override
	public Long bitPos(final byte[] key, final boolean value, final long start, final long end,
					   final BitType bitType) {
		return execute((client)->client.bitMapOperations().bitPos(rawKey(key), value, start, end, bitType));
	}

	@Override
	public Boolean getBit(final String key, final long offset) {
		return execute((client)->client.bitMapOperations().getBit(rawKey(key), offset));
	}

	@Override
	public Boolean getBit(final byte[] key, final long offset) {
		return execute((client)->client.bitMapOperations().getBit(rawKey(key), offset));
	}

	@Override
	public Boolean setBit(final String key, final long offset, final boolean value) {
		return execute((client)->client.bitMapOperations().setBit(rawKey(key), offset, value));
	}

	@Override
	public Boolean setBit(final byte[] key, final long offset, final boolean value) {
		return execute((client)->client.bitMapOperations().setBit(rawKey(key), offset, value));
	}

	@Override
	public Long clusterKeySlot(final String key) {
		return execute((client)->client.clusterOperations().clusterKeySlot(rawKey(key)));
	}

	@Override
	public Long clusterKeySlot(final byte[] key) {
		return execute((client)->client.clusterOperations().clusterKeySlot(rawKey(key)));
	}

	@Override
	public Status copy(final String key, final String destKey) {
		return execute((client)->client.keyOperations().copy(rawKey(key), rawKey(destKey)));
	}

	@Override
	public Status copy(final byte[] key, final byte[] destKey) {
		return execute((client)->client.keyOperations().copy(rawKey(key), rawKey(destKey)));
	}

	@Override
	public Status copy(final String key, final String destKey, final int db) {
		return execute((client)->client.keyOperations().copy(rawKey(key), rawKey(destKey), db));
	}

	@Override
	public Status copy(final byte[] key, final byte[] destKey, final int db) {
		return execute((client)->client.keyOperations().copy(rawKey(key), rawKey(destKey), db));
	}

	@Override
	public Status copy(final String key, final String destKey, final boolean replace) {
		return execute((client)->client.keyOperations().copy(rawKey(key), rawKey(destKey), replace));
	}

	@Override
	public Status copy(final byte[] key, final byte[] destKey, final boolean replace) {
		return execute((client)->client.keyOperations().copy(rawKey(key), rawKey(destKey), replace));
	}

	@Override
	public Status copy(final String key, final String destKey, final int db, final boolean replace) {
		return execute((client)->client.keyOperations().copy(rawKey(key), rawKey(destKey), db, replace));
	}

	@Override
	public Status copy(final byte[] key, final byte[] destKey, final int db, final boolean replace) {
		return execute((client)->client.keyOperations().copy(rawKey(key), rawKey(destKey), db, replace));
	}

	@Override
	public Long del(final String... keys) {
		return execute((client)->client.keyOperations().del(rawKeys(keys)));
	}

	@Override
	public Long del(final byte[]... keys) {
		return execute((client)->client.keyOperations().del(rawKeys(keys)));
	}

	@Override
	public String dump(final String key) {
		return execute((client)->client.keyOperations().dump(rawKey(key)));
	}

	@Override
	public byte[] dump(final byte[] key) {
		return execute((client)->client.keyOperations().dump(rawKey(key)));
	}

	@Override
	public Boolean exists(final String key) {
		return execute((client)->client.keyOperations().exists(rawKey(key)));
	}

	@Override
	public Boolean exists(final byte[] key) {
		return execute((client)->client.keyOperations().exists(rawKey(key)));
	}

	@Override
	public Long exists(final String... keys) {
		return execute((client)->client.keyOperations().exists(rawKeys(keys)));
	}

	@Override
	public Long exists(final byte[]... keys) {
		return execute((client)->client.keyOperations().exists(rawKeys(keys)));
	}

	@Override
	public Status expire(final String key, final int lifetime) {
		return execute((client)->client.keyOperations().expire(rawKey(key), lifetime));
	}

	@Override
	public Status expire(final byte[] key, final int lifetime) {
		return execute((client)->client.keyOperations().expire(rawKey(key), lifetime));
	}

	@Override
	public Status expire(final String key, final int lifetime, final ExpireOption expireOption) {
		return execute((client)->client.keyOperations().expire(rawKey(key), lifetime, expireOption));
	}

	@Override
	public Status expire(final byte[] key, final int lifetime, final ExpireOption expireOption) {
		return execute((client)->client.keyOperations().expire(rawKey(key), lifetime, expireOption));
	}

	@Override
	public Status expireAt(final String key, final long unixTimestamp) {
		return execute((client)->client.keyOperations().expireAt(rawKey(key), unixTimestamp));
	}

	@Override
	public Status expireAt(final byte[] key, final long unixTimestamp) {
		return execute((client)->client.keyOperations().expireAt(rawKey(key), unixTimestamp));
	}

	@Override
	public Status expireAt(final String key, final long unixTimestamp, final ExpireOption expireOption) {
		return execute((client)->client.keyOperations().expireAt(rawKey(key), unixTimestamp, expireOption));
	}

	@Override
	public Status expireAt(final byte[] key, final long unixTimestamp, final ExpireOption expireOption) {
		return execute((client)->client.keyOperations().expireAt(rawKey(key), unixTimestamp, expireOption));
	}

	@Override
	public Long expireTime(final String key) {
		return execute((client)->client.keyOperations().expireTime(rawKey(key)));
	}

	@Override
	public Long expireTime(final byte[] key) {
		return execute((client)->client.keyOperations().expireTime(rawKey(key)));
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final int timeout, final String... keys) {
		return execute((client)->client.keyOperations().migrate(host, port, db, timeout, rawKeys(keys)));
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final int timeout, final byte[]... keys) {
		return execute((client)->client.keyOperations().migrate(host, port, db, timeout, rawKeys(keys)));
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final int timeout,
						  final MigrateArgument migrateArgument, final String... keys) {
		return execute(
				(client)->client.keyOperations().migrate(host, port, db, timeout, migrateArgument, rawKeys(keys)));
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final int timeout,
						  final MigrateArgument migrateArgument, final byte[]... keys) {
		return execute(
				(client)->client.keyOperations().migrate(host, port, db, timeout, migrateArgument, rawKeys(keys)));
	}

	@Override
	public Status move(final String key, final int db) {
		return execute((client)->client.keyOperations().move(rawKey(key), db));
	}

	@Override
	public Status move(final byte[] key, final int db) {
		return execute((client)->client.keyOperations().move(rawKey(key), db));
	}

	@Override
	public ObjectEncoding objectEncoding(final String key) {
		return execute((client)->client.keyOperations().objectEncoding(rawKey(key)));
	}

	@Override
	public ObjectEncoding objectEncoding(final byte[] key) {
		return execute((client)->client.keyOperations().objectEncoding(rawKey(key)));
	}

	@Override
	public Long objectFreq(final String key) {
		return execute((client)->client.keyOperations().objectFreq(rawKey(key)));
	}

	@Override
	public Long objectFreq(final byte[] key) {
		return execute((client)->client.keyOperations().objectFreq(rawKey(key)));
	}

	@Override
	public Long objectIdleTime(final String key) {
		return execute((client)->client.keyOperations().objectIdleTime(rawKey(key)));
	}

	@Override
	public Long objectIdleTime(final byte[] key) {
		return execute((client)->client.keyOperations().objectIdleTime(rawKey(key)));
	}

	@Override
	public Long objectRefcount(final String key) {
		return execute((client)->client.keyOperations().objectRefcount(rawKey(key)));
	}

	@Override
	public Long objectRefcount(final byte[] key) {
		return execute((client)->client.keyOperations().objectRefcount(rawKey(key)));
	}

	@Override
	public Status persist(final String key) {
		return execute((client)->client.keyOperations().persist(rawKey(key)));
	}

	@Override
	public Status persist(final byte[] key) {
		return execute((client)->client.keyOperations().persist(rawKey(key)));
	}

	@Override
	public Status pExpire(final String key, final int lifetime) {
		return execute((client)->client.keyOperations().pExpire(rawKey(key), lifetime));
	}

	@Override
	public Status pExpire(final byte[] key, final int lifetime) {
		return execute((client)->client.keyOperations().pExpire(rawKey(key), lifetime));
	}

	@Override
	public Status pExpire(final String key, final int lifetime, final ExpireOption expireOption) {
		return execute((client)->client.keyOperations().pExpire(rawKey(key), lifetime, expireOption));
	}

	@Override
	public Status pExpire(final byte[] key, final int lifetime, final ExpireOption expireOption) {
		return execute((client)->client.keyOperations().pExpire(rawKey(key), lifetime, expireOption));
	}

	@Override
	public Status pExpireAt(final String key, final long unixTimestamp) {
		return execute((client)->client.keyOperations().pExpireAt(rawKey(key), unixTimestamp));
	}

	@Override
	public Status pExpireAt(final byte[] key, final long unixTimestamp) {
		return execute((client)->client.keyOperations().pExpireAt(rawKey(key), unixTimestamp));
	}

	@Override
	public Status pExpireAt(final String key, final long unixTimestamp, final ExpireOption expireOption) {
		return execute((client)->client.keyOperations().pExpireAt(rawKey(key), unixTimestamp, expireOption));
	}

	@Override
	public Status pExpireAt(final byte[] key, final long unixTimestamp, final ExpireOption expireOption) {
		return execute((client)->client.keyOperations().pExpireAt(rawKey(key), unixTimestamp, expireOption));
	}

	@Override
	public Long pExpireTime(final String key) {
		return execute((client)->client.keyOperations().pExpireTime(rawKey(key)));
	}

	@Override
	public Long pExpireTime(final byte[] key) {
		return execute((client)->client.keyOperations().pExpireTime(rawKey(key)));
	}

	@Override
	public Long pTtl(final String key) {
		return execute((client)->client.keyOperations().pTtl(rawKey(key)));
	}

	@Override
	public Long pTtl(final byte[] key) {
		return execute((client)->client.keyOperations().pTtl(rawKey(key)));
	}

	@Override
	public Status rename(final String key, final String newKey) {
		return execute((client)->client.keyOperations().rename(rawKey(key), rawKey(newKey)));
	}

	@Override
	public Status rename(final byte[] key, final byte[] newKey) {
		return execute((client)->client.keyOperations().rename(rawKey(key), rawKey(newKey)));
	}

	@Override
	public Status renameNx(final String key, final String newKey) {
		return execute((client)->client.keyOperations().renameNx(rawKey(key), rawKey(newKey)));
	}

	@Override
	public Status renameNx(final byte[] key, final byte[] newKey) {
		return execute((client)->client.keyOperations().renameNx(rawKey(key), rawKey(newKey)));
	}

	@Override
	public Status restore(final String key, final byte[] serializedValue, final int ttl) {
		return execute((client)->client.keyOperations().restore(rawKey(key), serializedValue, ttl));
	}

	@Override
	public Status restore(final byte[] key, final byte[] serializedValue, final int ttl) {
		return execute((client)->client.keyOperations().restore(rawKey(key), serializedValue, ttl));
	}

	@Override
	public Status restore(final String key, final byte[] serializedValue, final int ttl,
						  final RestoreArgument restoreArgument) {
		return execute((client)->client.keyOperations().restore(rawKey(key), serializedValue, ttl, restoreArgument));
	}

	@Override
	public Status restore(final byte[] key, final byte[] serializedValue, final int ttl,
						  final RestoreArgument restoreArgument) {
		return execute((client)->client.keyOperations().restore(rawKey(key), serializedValue, ttl, restoreArgument));
	}

	@Override
	public ScanResult<List<String>> scan(final long cursor) {
		return execute((client)->client.keyOperations().scan(cursor));
	}

	@Override
	public ScanResult<List<String>> scan(final String cursor) {
		return execute((client)->client.keyOperations().scan(cursor));
	}

	@Override
	public ScanResult<List<byte[]>> scan(final byte[] cursor) {
		return execute((client)->client.keyOperations().scan(cursor));
	}

	@Override
	public ScanResult<List<String>> scan(final long cursor, final String pattern) {
		return execute((client)->client.keyOperations().scan(cursor, pattern));
	}

	@Override
	public ScanResult<List<byte[]>> scan(final long cursor, final byte[] pattern) {
		return execute((client)->client.keyOperations().scan(cursor, pattern));
	}

	@Override
	public ScanResult<List<String>> scan(final String cursor, final String pattern) {
		return execute((client)->client.keyOperations().scan(cursor, pattern));
	}

	@Override
	public ScanResult<List<byte[]>> scan(final byte[] cursor, final byte[] pattern) {
		return execute((client)->client.keyOperations().scan(cursor, pattern));
	}

	@Override
	public ScanResult<List<String>> scan(final long cursor, final int count) {
		return execute((client)->client.keyOperations().scan(cursor, count));
	}

	@Override
	public ScanResult<List<String>> scan(final String cursor, final int count) {
		return execute((client)->client.keyOperations().scan(cursor, count));
	}

	@Override
	public ScanResult<List<byte[]>> scan(final byte[] cursor, final int count) {
		return execute((client)->client.keyOperations().scan(cursor, count));
	}

	@Override
	public ScanResult<List<String>> scan(final long cursor, final String pattern, final int count) {
		return execute((client)->client.keyOperations().scan(cursor, pattern, count));
	}

	@Override
	public ScanResult<List<byte[]>> scan(final long cursor, final byte[] pattern, final int count) {
		return execute((client)->client.keyOperations().scan(cursor, pattern, count));
	}

	@Override
	public ScanResult<List<String>> scan(final String cursor, final String pattern, final int count) {
		return execute((client)->client.keyOperations().scan(cursor, pattern, count));
	}

	@Override
	public ScanResult<List<byte[]>> scan(final byte[] cursor, final byte[] pattern, final int count) {
		return execute((client)->client.keyOperations().scan(cursor, pattern, count));
	}

	@Override
	public List<String> sort(final String key) {
		return execute((client)->client.keyOperations().sort(rawKey(key)));
	}

	@Override
	public List<byte[]> sort(final byte[] key) {
		return execute((client)->client.keyOperations().sort(rawKey(key)));
	}

	@Override
	public List<String> sort(final String key, final SortArgument sortArgument) {
		return execute((client)->client.keyOperations().sort(rawKey(key), sortArgument));
	}

	@Override
	public List<byte[]> sort(final byte[] key, final SortArgument sortArgument) {
		return execute((client)->client.keyOperations().sort(rawKey(key), sortArgument));
	}

	@Override
	public Long sort(final String key, final String destKey) {
		return execute((client)->client.keyOperations().sort(rawKey(key), rawKey(destKey)));
	}

	@Override
	public Long sort(final byte[] key, final byte[] destKey) {
		return execute((client)->client.keyOperations().sort(rawKey(key), rawKey(destKey)));
	}

	@Override
	public Long sort(final String key, final String destKey, final SortArgument sortArgument) {
		return execute((client)->client.keyOperations().sort(rawKey(key), rawKey(destKey), sortArgument));
	}

	@Override
	public Long sort(final byte[] key, final byte[] destKey, final SortArgument sortArgument) {
		return execute((client)->client.keyOperations().sort(rawKey(key), rawKey(destKey), sortArgument));
	}

	@Override
	public List<String> sortRo(final String key) {
		return execute((client)->client.keyOperations().sortRo(rawKey(key)));
	}

	@Override
	public List<byte[]> sortRo(final byte[] key) {
		return execute((client)->client.keyOperations().sortRo(rawKey(key)));
	}

	@Override
	public List<String> sortRo(final String key, final SortArgument sortArgument) {
		return execute((client)->client.keyOperations().sortRo(rawKey(key), sortArgument));
	}

	@Override
	public List<byte[]> sortRo(final byte[] key, final SortArgument sortArgument) {
		return execute((client)->client.keyOperations().sortRo(rawKey(key), sortArgument));
	}

	@Override
	public Long touch(final String... keys) {
		return execute((client)->client.keyOperations().touch(rawKeys(keys)));
	}

	@Override
	public Long touch(final byte[]... keys) {
		return execute((client)->client.keyOperations().touch(rawKeys(keys)));
	}

	@Override
	public Long ttl(final String key) {
		return execute((client)->client.keyOperations().ttl(rawKey(key)));
	}

	@Override
	public Long ttl(final byte[] key) {
		return execute((client)->client.keyOperations().ttl(rawKey(key)));
	}

	@Override
	public Type type(final String key) {
		return execute((client)->client.keyOperations().type(rawKey(key)));
	}

	@Override
	public Type type(final byte[] key) {
		return execute((client)->client.keyOperations().type(rawKey(key)));
	}

	@Override
	public Long unlink(final String... keys) {
		return execute((client)->client.keyOperations().unlink(rawKeys(keys)));
	}

	@Override
	public Long unlink(final byte[]... keys) {
		return execute((client)->client.keyOperations().unlink(rawKeys(keys)));
	}

	@Override
	public Long geoAdd(final String key, final String member, final double longitude, final double latitude) {
		return execute((client)->client.geoOperations().geoAdd(rawKey(key), member, longitude, latitude));
	}

	@Override
	public Long geoAdd(final byte[] key, final byte[] member, final double longitude, final double latitude) {
		return execute((client)->client.geoOperations().geoAdd(rawKey(key), member, longitude, latitude));
	}

	@Override
	public Long geoAdd(final String key, final Map<String, Geo> memberCoordinates) {
		return execute((client)->client.geoOperations().geoAdd(rawKey(key), memberCoordinates));
	}

	@Override
	public Long geoAdd(final byte[] key, final Map<byte[], Geo> memberCoordinates) {
		return execute((client)->client.geoOperations().geoAdd(rawKey(key), memberCoordinates));
	}

	@Override
	public Long geoAdd(final String key, final String member, final double longitude, final double latitude,
					   final GeoAddArgument geoAddArgument) {
		return execute(
				(client)->client.geoOperations().geoAdd(rawKey(key), member, longitude, latitude, geoAddArgument));
	}

	@Override
	public Long geoAdd(final byte[] key, final byte[] member, final double longitude, final double latitude,
					   final GeoAddArgument geoAddArgument) {
		return execute(
				(client)->client.geoOperations().geoAdd(rawKey(key), member, longitude, latitude, geoAddArgument));
	}

	@Override
	public Long geoAdd(final String key, final Map<String, Geo> memberCoordinates,
					   final GeoAddArgument geoAddArgument) {
		return execute((client)->client.geoOperations().geoAdd(rawKey(key), memberCoordinates, geoAddArgument));
	}

	@Override
	public Long geoAdd(final byte[] key, final Map<byte[], Geo> memberCoordinates,
					   final GeoAddArgument geoAddArgument) {
		return execute((client)->client.geoOperations().geoAdd(rawKey(key), memberCoordinates, geoAddArgument));
	}

	@Override
	public Double geoDist(final String key, final String member1, final String member2) {
		return execute((client)->client.geoOperations().geoDist(rawKey(key), member1, member2));
	}

	@Override
	public Double geoDist(final byte[] key, final byte[] member1, final byte[] member2) {
		return execute((client)->client.geoOperations().geoDist(rawKey(key), member1, member2));
	}

	@Override
	public Double geoDist(final String key, final String member1, final String member2, final GeoUnit unit) {
		return execute((client)->client.geoOperations().geoDist(rawKey(key), member1, member2, unit));
	}

	@Override
	public Double geoDist(final byte[] key, final byte[] member1, final byte[] member2, final GeoUnit unit) {
		return execute((client)->client.geoOperations().geoDist(rawKey(key), member1, member2, unit));
	}

	@Override
	public List<String> geoHash(final String key, final String... members) {
		return execute((client)->client.geoOperations().geoHash(rawKey(key), members));
	}

	@Override
	public List<byte[]> geoHash(final byte[] key, final byte[]... members) {
		return execute((client)->client.geoOperations().geoHash(rawKey(key), members));
	}

	@Override
	public List<Geo> geoPos(final String key, final String... members) {
		return execute((client)->client.geoOperations().geoPos(rawKey(key), members));
	}

	@Override
	public List<Geo> geoPos(final byte[] key, final byte[]... members) {
		return execute((client)->client.geoOperations().geoPos(rawKey(key), members));
	}

	@Override
	public List<GeoRadius> geoRadius(final String key, final double longitude, final double latitude,
									 final double radius, final GeoUnit unit) {
		return execute((client)->client.geoOperations().geoRadius(rawKey(key), longitude, latitude, radius, unit));
	}

	@Override
	public List<GeoRadius> geoRadius(final byte[] key, final double longitude, final double latitude,
									 final double radius, final GeoUnit unit) {
		return execute((client)->client.geoOperations().geoRadius(rawKey(key), longitude, latitude, radius, unit));
	}

	@Override
	public List<GeoRadius> geoRadius(final String key, final double longitude, final double latitude,
									 final double radius, final GeoUnit unit,
									 final GeoRadiusArgument geoRadiusArgument) {
		return execute((client)->client.geoOperations()
				.geoRadius(rawKey(key), longitude, latitude, radius, unit, geoRadiusArgument));
	}

	@Override
	public List<GeoRadius> geoRadius(final byte[] key, final double longitude, final double latitude,
									 final double radius, final GeoUnit unit,
									 final GeoRadiusArgument geoRadiusArgument) {
		return execute((client)->client.geoOperations()
				.geoRadius(rawKey(key), longitude, latitude, radius, unit, geoRadiusArgument));
	}

	@Override
	public List<GeoRadius> geoRadiusRo(final String key, final double longitude, final double latitude,
									   final double radius, final GeoUnit unit) {
		return execute((client)->client.geoOperations().geoRadiusRo(rawKey(key), longitude, latitude, radius, unit));
	}

	@Override
	public List<GeoRadius> geoRadiusRo(final byte[] key, final double longitude, final double latitude,
									   final double radius, final GeoUnit unit) {
		return execute((client)->client.geoOperations().geoRadiusRo(rawKey(key), longitude, latitude, radius, unit));
	}

	@Override
	public List<GeoRadius> geoRadiusRo(final String key, final double longitude, final double latitude,
									   final double radius, final GeoUnit unit,
									   final GeoRadiusArgument geoRadiusArgument) {
		return execute((client)->client.geoOperations()
				.geoRadiusRo(rawKey(key), longitude, latitude, radius, unit, geoRadiusArgument));
	}

	@Override
	public List<GeoRadius> geoRadiusRo(final byte[] key, final double longitude, final double latitude,
									   final double radius, final GeoUnit unit,
									   final GeoRadiusArgument geoRadiusArgument) {
		return execute((client)->client.geoOperations()
				.geoRadiusRo(rawKey(key), longitude, latitude, radius, unit, geoRadiusArgument));
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final String key, final String member, final double radius,
											 final GeoUnit unit) {
		return execute((client)->client.geoOperations().geoRadiusByMember(rawKey(key), member, radius, unit));
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final byte[] key, final byte[] member, final double radius,
											 final GeoUnit unit) {
		return execute((client)->client.geoOperations().geoRadiusByMember(rawKey(key), member, radius, unit));
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final String key, final String member, final double radius,
											 final GeoUnit unit, final GeoRadiusArgument geoRadiusArgument) {
		return execute((client)->client.geoOperations()
				.geoRadiusByMember(rawKey(key), member, radius, unit, geoRadiusArgument));
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final byte[] key, final byte[] member, final double radius,
											 final GeoUnit unit, final GeoRadiusArgument geoRadiusArgument) {
		return execute((client)->client.geoOperations()
				.geoRadiusByMember(rawKey(key), member, radius, unit, geoRadiusArgument));
	}

	@Override
	public List<GeoRadius> geoRadiusByMemberRo(final String key, final String member, final double radius,
											   final GeoUnit unit) {
		return execute((client)->client.geoOperations().geoRadiusByMemberRo(rawKey(key), member, radius, unit));
	}

	@Override
	public List<GeoRadius> geoRadiusByMemberRo(final byte[] key, final byte[] member, final double radius,
											   final GeoUnit unit) {
		return execute((client)->client.geoOperations().geoRadiusByMemberRo(rawKey(key), member, radius, unit));
	}

	@Override
	public List<GeoRadius> geoRadiusByMemberRo(final String key, final String member, final double radius,
											   final GeoUnit unit, final GeoRadiusArgument geoRadiusArgument) {
		return execute((client)->client.geoOperations()
				.geoRadiusByMemberRo(rawKey(key), member, radius, unit, geoRadiusArgument));
	}

	@Override
	public List<GeoRadius> geoRadiusByMemberRo(final byte[] key, final byte[] member, final double radius,
											   final GeoUnit unit, final GeoRadiusArgument geoRadiusArgument) {
		return execute((client)->client.geoOperations()
				.geoRadiusByMemberRo(rawKey(key), member, radius, unit, geoRadiusArgument));
	}

	@Override
	public List<GeoRadius> geoSearch(final String key, final GeoSearchArgument geoSearchArgument) {
		return execute((client)->client.geoOperations().geoSearch(rawKey(key), geoSearchArgument));
	}

	@Override
	public List<GeoRadius> geoSearch(final byte[] key, final GeoSearchArgument geoSearchArgument) {
		return execute((client)->client.geoOperations().geoSearch(rawKey(key), geoSearchArgument));
	}

	@Override
	public Long geoSearchStore(final String destKey, final String key,
							   final GeoSearchStoreArgument geoSearchStoreArgument) {
		return execute(
				(client)->client.geoOperations().geoSearchStore(rawKey(destKey), rawKey(key), geoSearchStoreArgument));
	}

	@Override
	public Long geoSearchStore(final byte[] destKey, final byte[] key,
							   final GeoSearchStoreArgument geoSearchStoreArgument) {
		return execute(
				(client)->client.geoOperations().geoSearchStore(rawKey(destKey), rawKey(key), geoSearchStoreArgument));
	}

	@Override
	public Long hDel(final String key, final String... fields) {
		return execute((client)->client.hashOperations().hDel(rawKey(key), fields));
	}

	@Override
	public Long hDel(final byte[] key, final byte[]... fields) {
		return execute((client)->client.hashOperations().hDel(rawKey(key), fields));
	}

	@Override
	public Boolean hExists(final String key, final String field) {
		return execute((client)->client.hashOperations().hExists(rawKey(key), field));
	}

	@Override
	public Boolean hExists(final byte[] key, final byte[] field) {
		return execute((client)->client.hashOperations().hExists(rawKey(key), field));
	}

	@Override
	public List<Long> hExpire(final String key, final int lifetime, final String... fields) {
		return execute((client)->client.hashOperations().hExpire(rawKey(key), lifetime, fields));
	}

	@Override
	public List<Long> hExpire(final byte[] key, final int lifetime, final byte[]... fields) {
		return execute((client)->client.hashOperations().hExpire(rawKey(key), lifetime, fields));
	}

	@Override
	public List<Long> hExpire(final String key, final int lifetime, final ExpireOption expireOption,
							  final String... fields) {
		return execute((client)->client.hashOperations().hExpire(rawKey(key), lifetime, expireOption, fields));
	}

	@Override
	public List<Long> hExpire(final byte[] key, final int lifetime, final ExpireOption expireOption,
							  final byte[]... fields) {
		return execute((client)->client.hashOperations().hExpire(rawKey(key), lifetime, expireOption, fields));
	}

	@Override
	public List<Long> hExpireAt(final String key, final long unixTimestamp, final String... fields) {
		return execute((client)->client.hashOperations().hExpireAt(rawKey(key), unixTimestamp, fields));
	}

	@Override
	public List<Long> hExpireAt(final byte[] key, final long unixTimestamp, final byte[]... fields) {
		return execute((client)->client.hashOperations().hExpireAt(rawKey(key), unixTimestamp, fields));
	}

	@Override
	public List<Long> hExpireAt(final String key, final long unixTimestamp, final ExpireOption expireOption,
								final String... fields) {
		return execute((client)->client.hashOperations().hExpireAt(rawKey(key), unixTimestamp, expireOption, fields));
	}

	@Override
	public List<Long> hExpireAt(final byte[] key, final long unixTimestamp, final ExpireOption expireOption,
								final byte[]... fields) {
		return execute((client)->client.hashOperations().hExpireAt(rawKey(key), unixTimestamp, expireOption, fields));
	}

	@Override
	public List<Long> hExpireTime(final String key, final String... fields) {
		return execute((client)->client.hashOperations().hExpireTime(rawKey(key), fields));
	}

	@Override
	public List<Long> hExpireTime(final byte[] key, final byte[]... fields) {
		return execute((client)->client.hashOperations().hExpireTime(rawKey(key), fields));
	}

	@Override
	public String hGet(final String key, final String field) {
		return execute((client)->client.hashOperations().hGet(rawKey(key), field));
	}

	@Override
	public byte[] hGet(final byte[] key, final byte[] field) {
		return execute((client)->client.hashOperations().hGet(rawKey(key), field));
	}

	@Override
	public Map<String, String> hGetAll(final String key) {
		return execute((client)->client.hashOperations().hGetAll(rawKey(key)));
	}

	@Override
	public Map<byte[], byte[]> hGetAll(final byte[] key) {
		return execute((client)->client.hashOperations().hGetAll(rawKey(key)));
	}

	@Override
	public Long hIncrBy(final String key, final String field, final long value) {
		return execute((client)->client.hashOperations().hIncrBy(rawKey(key), field, value));
	}

	@Override
	public Long hIncrBy(final byte[] key, final byte[] field, final long value) {
		return execute((client)->client.hashOperations().hIncrBy(rawKey(key), field, value));
	}

	@Override
	public Double hIncrByFloat(final String key, final String field, final double value) {
		return execute((client)->client.hashOperations().hIncrByFloat(rawKey(key), field, value));
	}

	@Override
	public Double hIncrByFloat(final byte[] key, final byte[] field, final double value) {
		return execute((client)->client.hashOperations().hIncrByFloat(rawKey(key), field, value));
	}

	@Override
	public Set<String> hKeys(final String key) {
		return execute((client)->client.hashOperations().hKeys(rawKey(key)));
	}

	@Override
	public Set<byte[]> hKeys(final byte[] key) {
		return execute((client)->client.hashOperations().hKeys(rawKey(key)));
	}

	@Override
	public Long hLen(final String key) {
		return execute((client)->client.hashOperations().hLen(rawKey(key)));
	}

	@Override
	public Long hLen(final byte[] key) {
		return execute((client)->client.hashOperations().hLen(rawKey(key)));
	}

	@Override
	public List<String> hMGet(final String key, final String... fields) {
		return execute((client)->client.hashOperations().hMGet(rawKey(key), fields));
	}

	@Override
	public List<byte[]> hMGet(final byte[] key, final byte[]... fields) {
		return execute((client)->client.hashOperations().hMGet(rawKey(key), fields));
	}

	@Override
	public Status hMSet(final String key, final Map<String, String> data) {
		return execute((client)->client.hashOperations().hMSet(rawKey(key), data));
	}

	@Override
	public Status hMSet(final byte[] key, final Map<byte[], byte[]> data) {
		return execute((client)->client.hashOperations().hMSet(rawKey(key), data));
	}

	@Override
	public List<Long> hPersist(final String key, final String... fields) {
		return execute((client)->client.hashOperations().hPersist(rawKey(key), fields));
	}

	@Override
	public List<Long> hPersist(final byte[] key, final byte[]... fields) {
		return execute((client)->client.hashOperations().hPersist(rawKey(key), fields));
	}

	@Override
	public List<Long> hpExpire(final String key, final int lifetime, final String... fields) {
		return execute((client)->client.hashOperations().hpExpire(rawKey(key), lifetime, fields));
	}

	@Override
	public List<Long> hpExpire(final byte[] key, final int lifetime, final byte[]... fields) {
		return execute((client)->client.hashOperations().hpExpire(rawKey(key), lifetime, fields));
	}

	@Override
	public List<Long> hpExpire(final String key, final int lifetime, final ExpireOption expireOption,
							   final String... fields) {
		return execute((client)->client.hashOperations().hpExpire(rawKey(key), lifetime, expireOption, fields));
	}

	@Override
	public List<Long> hpExpire(final byte[] key, final int lifetime, final ExpireOption expireOption,
							   final byte[]... fields) {
		return execute((client)->client.hashOperations().hpExpire(rawKey(key), lifetime, expireOption, fields));
	}

	@Override
	public List<Long> hpExpireAt(final String key, final long unixTimestamp, final String... fields) {
		return execute((client)->client.hashOperations().hpExpireAt(rawKey(key), unixTimestamp, fields));
	}

	@Override
	public List<Long> hpExpireAt(final byte[] key, final long unixTimestamp, final byte[]... fields) {
		return execute((client)->client.hashOperations().hpExpireAt(rawKey(key), unixTimestamp, fields));
	}

	@Override
	public List<Long> hpExpireAt(final String key, final long unixTimestamp, final ExpireOption expireOption,
								 final String... fields) {
		return execute((client)->client.hashOperations().hpExpireAt(rawKey(key), unixTimestamp, expireOption, fields));
	}

	@Override
	public List<Long> hpExpireAt(final byte[] key, final long unixTimestamp, final ExpireOption expireOption,
								 final byte[]... fields) {
		return execute((client)->client.hashOperations().hpExpireAt(rawKey(key), unixTimestamp, expireOption, fields));
	}

	@Override
	public List<Long> hpExpireTime(final String key, final String... fields) {
		return execute((client)->client.hashOperations().hpExpireTime(rawKey(key), fields));
	}

	@Override
	public List<Long> hpExpireTime(final byte[] key, final byte[]... fields) {
		return execute((client)->client.hashOperations().hpExpireTime(rawKey(key), fields));
	}

	@Override
	public List<Long> hpTtl(final String key, final String... fields) {
		return execute((client)->client.hashOperations().hpTtl(rawKey(key), fields));
	}

	@Override
	public List<Long> hpTtl(final byte[] key, final byte[]... fields) {
		return execute((client)->client.hashOperations().hpTtl(rawKey(key), fields));
	}

	@Override
	public String hRandField(final String key) {
		return execute((client)->client.hashOperations().hRandField(rawKey(key)));
	}

	@Override
	public byte[] hRandField(final byte[] key) {
		return execute((client)->client.hashOperations().hRandField(rawKey(key)));
	}

	@Override
	public List<String> hRandField(final String key, final int count) {
		return execute((client)->client.hashOperations().hRandField(rawKey(key), count));
	}

	@Override
	public List<byte[]> hRandField(final byte[] key, final int count) {
		return execute((client)->client.hashOperations().hRandField(rawKey(key), count));
	}

	@Override
	public List<KeyValue<String, String>> hRandFieldWithValues(final String key, final int count) {
		return execute((client)->client.hashOperations().hRandFieldWithValues(rawKey(key), count));
	}

	@Override
	public List<KeyValue<byte[], byte[]>> hRandFieldWithValues(final byte[] key, final int count) {
		return execute((client)->client.hashOperations().hRandFieldWithValues(rawKey(key), count));
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final long cursor) {
		return execute((client)->client.hashOperations().hScan(rawKey(key), cursor));
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final long cursor) {
		return execute((client)->client.hashOperations().hScan(rawKey(key), cursor));
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final String cursor) {
		return execute((client)->client.hashOperations().hScan(rawKey(key), cursor));
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor) {
		return execute((client)->client.hashOperations().hScan(rawKey(key), cursor));
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final long cursor,
												 final HScanArgument<String> scanArgument) {
		return execute((client)->client.hashOperations().hScan(rawKey(key), cursor, scanArgument));
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final long cursor,
												 final HScanArgument<byte[]> scanArgument) {
		return execute((client)->client.hashOperations().hScan(rawKey(key), cursor, scanArgument));
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final String cursor,
												 final HScanArgument<String> scanArgument) {
		return execute((client)->client.hashOperations().hScan(rawKey(key), cursor, scanArgument));
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor,
												 final HScanArgument<byte[]> scanArgument) {
		return execute((client)->client.hashOperations().hScan(rawKey(key), cursor, scanArgument));
	}

	@Override
	public Long hSet(final String key, final KeyValue<String, String>... data) {
		return execute((client)->client.hashOperations().hSet(rawKey(key), data));
	}

	@Override
	public Long hSet(final byte[] key, final KeyValue<byte[], byte[]>... data) {
		return execute((client)->client.hashOperations().hSet(rawKey(key), data));
	}

	@Override
	public Status hSetNx(final String key, final String field, final String value) {
		return execute((client)->client.hashOperations().hSetNx(rawKey(key), field, value));
	}

	@Override
	public Status hSetNx(final byte[] key, final byte[] field, final byte[] value) {
		return execute((client)->client.hashOperations().hSetNx(rawKey(key), field, value));
	}

	@Override
	public Long hStrLen(final String key, final String field) {
		return execute((client)->client.hashOperations().hStrLen(rawKey(key), field));
	}

	@Override
	public Long hStrLen(final byte[] key, final byte[] field) {
		return execute((client)->client.hashOperations().hStrLen(rawKey(key), field));
	}

	@Override
	public List<Long> hTtl(final String key, final String... fields) {
		return execute((client)->client.hashOperations().hTtl(rawKey(key), fields));
	}

	@Override
	public List<Long> hTtl(final byte[] key, final byte[]... fields) {
		return execute((client)->client.hashOperations().hTtl(rawKey(key), fields));
	}

	@Override
	public List<String> hVals(final String key) {
		return execute((client)->client.hashOperations().hVals(rawKey(key)));
	}

	@Override
	public List<byte[]> hVals(final byte[] key) {
		return execute((client)->client.hashOperations().hVals(rawKey(key)));
	}

	@Override
	public Status pfAdd(final String key, final String... elements) {
		return execute((client)->client.hyperLogLogOperations().pfAdd(rawKey(key), elements));
	}

	@Override
	public Status pfAdd(final byte[] key, final byte[]... elements) {
		return execute((client)->client.hyperLogLogOperations().pfAdd(rawKey(key), elements));
	}

	@Override
	public Long pfCount(final String... keys) {
		return execute((client)->client.hyperLogLogOperations().pfCount(rawKeys(keys)));
	}

	@Override
	public Long pfCount(final byte[]... keys) {
		return execute((client)->client.hyperLogLogOperations().pfCount(rawKeys(keys)));
	}

	@Override
	public Status pfMerge(final String destKey, final String... keys) {
		return execute((client)->client.hyperLogLogOperations().pfMerge(rawKey(destKey), rawKeys(keys)));
	}

	@Override
	public Status pfMerge(final byte[] destKey, final byte[]... keys) {
		return execute((client)->client.hyperLogLogOperations().pfMerge(rawKey(destKey), rawKeys(keys)));
	}

	@Override
	public List<AclCategory> aclCat() {
		return execute((client)->client.aclOperations().aclCat());
	}

	@Override
	public List<Command> aclCat(final AclCategory aclCategory) {
		return execute((client)->client.aclOperations().aclCat(aclCategory));
	}

	@Override
	public Long aclDelUser(final String... usernames) {
		return execute((client)->client.aclOperations().aclDelUser(usernames));
	}

	@Override
	public Long aclDelUser(final byte[]... username) {
		return execute((client)->client.aclOperations().aclDelUser(username));
	}

	@Override
	public Status aclDryRun(final String username, final Command command) {
		return execute((client)->client.aclOperations().aclDryRun(username, command));
	}

	@Override
	public Status aclDryRun(final byte[] username, final Command command) {
		return execute((client)->client.aclOperations().aclDryRun(username, command));
	}

	@Override
	public Status aclDryRun(final String username, final Command command, final String... arguments) {
		return execute((client)->client.aclOperations().aclDryRun(username, command, arguments));
	}

	@Override
	public Status aclDryRun(final byte[] username, final Command command, final byte[]... arguments) {
		return execute((client)->client.aclOperations().aclDryRun(username, command, arguments));
	}

	@Override
	public String aclGenPass() {
		return execute((client)->client.aclOperations().aclGenPass());
	}

	@Override
	public String aclGenPass(final int bits) {
		return execute((client)->client.aclOperations().aclGenPass(bits));
	}

	@Override
	public AclUser aclGetUser(final String username) {
		return execute((client)->client.aclOperations().aclGetUser(username));
	}

	@Override
	public AclUser aclGetUser(final byte[] username) {
		return execute((client)->client.aclOperations().aclGetUser(username));
	}

	@Override
	public List<String> aclList() {
		return execute((client)->client.aclOperations().aclList());
	}

	@Override
	public Status aclLoad() {
		return execute((client)->client.aclOperations().aclLoad());
	}

	@Override
	public List<AclLog> aclLog() {
		return execute((client)->client.aclOperations().aclLog());
	}

	@Override
	public List<AclLog> aclLog(final int count) {
		return execute((client)->client.aclOperations().aclLog(count));
	}

	@Override
	public Status aclLogReset() {
		return execute((client)->client.aclOperations().aclLogReset());
	}

	@Override
	public Status aclSave() {
		return execute((client)->client.aclOperations().aclSave());
	}

	@Override
	public Status aclSetUser(final String username, final AclSetUserArgument rules) {
		return execute((client)->client.aclOperations().aclSetUser(username, rules));
	}

	@Override
	public Status aclSetUser(final byte[] username, final AclSetUserArgument rules) {
		return execute((client)->client.aclOperations().aclSetUser(username, rules));
	}

	@Override
	public List<String> aclUsers() {
		return execute((client)->client.aclOperations().aclUsers());
	}

	@Override
	public String aclWhoAmI() {
		return execute((client)->client.aclOperations().aclWhoAmI());
	}

	@Override
	public String lIndex(final String key, final long index) {
		return execute((client)->client.listOperations().lIndex(rawKey(key), index));
	}

	@Override
	public byte[] lIndex(final byte[] key, final long index) {
		return execute((client)->client.listOperations().lIndex(rawKey(key), index));
	}

	@Override
	public Long lInsert(final String key, final ListPosition position, final String pivot, final String value) {
		return execute((client)->client.listOperations().lInsert(rawKey(key), position, pivot, value));
	}

	@Override
	public Long lInsert(final byte[] key, final ListPosition position, final byte[] pivot, final byte[] value) {
		return execute((client)->client.listOperations().lInsert(rawKey(key), position, pivot, value));
	}

	@Override
	public Status lSet(final String key, final long index, final String value) {
		return execute((client)->client.listOperations().lSet(rawKey(key), index, value));
	}

	@Override
	public Status lSet(final byte[] key, final long index, final byte[] value) {
		return execute((client)->client.listOperations().lSet(rawKey(key), index, value));
	}

	@Override
	public Long lLen(final String key) {
		return execute((client)->client.listOperations().lLen(rawKey(key)));
	}

	@Override
	public Long lLen(final byte[] key) {
		return execute((client)->client.listOperations().lLen(rawKey(key)));
	}

	@Override
	public List<String> lRange(final String key, final long start, final long end) {
		return execute((client)->client.listOperations().lRange(rawKey(key), start, end));
	}

	@Override
	public List<byte[]> lRange(final byte[] key, final long start, final long end) {
		return execute((client)->client.listOperations().lRange(rawKey(key), start, end));
	}

	@Override
	public Long lPos(final String key, final String element) {
		return execute((client)->client.listOperations().lPos(rawKey(key), element));
	}

	@Override
	public Long lPos(final byte[] key, final byte[] element) {
		return execute((client)->client.listOperations().lPos(rawKey(key), element));
	}

	@Override
	public Long lPos(final String key, final String element, final LPosArgument lPosArgument) {
		return execute((client)->client.listOperations().lPos(rawKey(key), element, lPosArgument));
	}

	@Override
	public Long lPos(final byte[] key, final byte[] element, final LPosArgument lPosArgument) {
		return execute((client)->client.listOperations().lPos(rawKey(key), element, lPosArgument));
	}

	@Override
	public List<Long> lPos(final String key, String element, final LPosArgument lPosArgument, final int count) {
		return execute((client)->client.listOperations().lPos(rawKey(key), element, lPosArgument, count));
	}

	@Override
	public List<Long> lPos(final byte[] key, final byte[] element, final LPosArgument lPosArgument, final int count) {
		return execute((client)->client.listOperations().lPos(rawKey(key), element, lPosArgument, count));
	}

	@Override
	public Long lRem(final String key, final String value, final int count) {
		return execute((client)->client.listOperations().lRem(rawKey(key), value, count));
	}

	@Override
	public Long lRem(final byte[] key, final byte[] value, final int count) {
		return execute((client)->client.listOperations().lRem(rawKey(key), value, count));
	}

	@Override
	public Status lTrim(final String key, final long start, final long end) {
		return execute((client)->client.listOperations().lTrim(rawKey(key), start, end));
	}

	@Override
	public Status lTrim(final byte[] key, final long start, final long end) {
		return execute((client)->client.listOperations().lTrim(rawKey(key), start, end));
	}

	@Override
	public String lMove(final String key, final String destKey, final Direction from, final Direction to) {
		return execute((client)->client.listOperations().lMove(rawKey(destKey), rawKey(destKey), from, to));
	}

	@Override
	public byte[] lMove(final byte[] key, final byte[] destKey, final Direction from, final Direction to) {
		return execute((client)->client.listOperations().lMove(rawKey(key), rawKey(destKey), from, to));
	}

	@Override
	public String blMove(final String key, final String destKey, final Direction from, final Direction to,
						 final int timeout) {
		return execute((client)->client.listOperations().blMove(rawKey(destKey), rawKey(destKey), from, to, timeout));
	}

	@Override
	public byte[] blMove(final byte[] key, final byte[] destKey, final Direction from, final Direction to,
						 final int timeout) {
		return execute((client)->client.listOperations().blMove(rawKey(key), rawKey(destKey), from, to, timeout));
	}

	@Override
	public List<String> blPop(final String[] keys, final int timeout) {
		return execute((client)->client.listOperations().blPop(rawKeys(keys), timeout));
	}

	@Override
	public List<byte[]> blPop(final byte[][] keys, final int timeout) {
		return execute((client)->client.listOperations().blPop(rawKeys(keys), timeout));
	}

	@Override
	public List<String> brPop(final String[] keys, final int timeout) {
		return execute((client)->client.listOperations().brPop(rawKeys(keys), timeout));
	}

	@Override
	public List<byte[]> brPop(final byte[][] keys, final int timeout) {
		return execute((client)->client.listOperations().brPop(rawKeys(keys), timeout));
	}

	@Override
	public String brPoplPush(final String key, final String destKey, final int timeout) {
		return execute((client)->client.listOperations().brPoplPush(rawKey(key), rawKey(destKey), timeout));
	}

	@Override
	public byte[] brPoplPush(final byte[] key, final byte[] destKey, final int timeout) {
		return execute((client)->client.listOperations().brPoplPush(rawKey(key), rawKey(destKey), timeout));
	}

	@Override
	public String lPop(final String key) {
		return execute((client)->client.listOperations().lPop(rawKey(key)));
	}

	@Override
	public byte[] lPop(final byte[] key) {
		return execute((client)->client.listOperations().lPop(rawKey(key)));
	}

	@Override
	public Long lPush(final String key, final String... values) {
		return execute((client)->client.listOperations().lPush(rawKey(key), values));
	}

	@Override
	public Long lPush(final byte[] key, final byte[]... values) {
		return execute((client)->client.listOperations().lPush(rawKey(key), values));
	}

	@Override
	public Long lPushX(final String key, final String... values) {
		return execute((client)->client.listOperations().lPushX(rawKey(key), values));
	}

	@Override
	public Long lPushX(final byte[] key, final byte[]... values) {
		return execute((client)->client.listOperations().lPushX(rawKey(key), values));
	}

	@Override
	public String rPop(final String key) {
		return execute((client)->client.listOperations().rPop(rawKey(key)));
	}

	@Override
	public byte[] rPop(final byte[] key) {
		return execute((client)->client.listOperations().rPop(rawKey(key)));
	}

	@Override
	public String rPoplPush(final String key, final String destKey) {
		return execute((client)->client.listOperations().rPoplPush(rawKey(key), rawKey(destKey)));
	}

	@Override
	public byte[] rPoplPush(final byte[] key, final byte[] destKey) {
		return execute((client)->client.listOperations().rPoplPush(rawKey(key), rawKey(destKey)));
	}

	@Override
	public Long rPush(final String key, final String... values) {
		return execute((client)->client.listOperations().rPush(rawKey(key), values));
	}

	@Override
	public Long rPush(final byte[] key, final byte[]... values) {
		return execute((client)->client.listOperations().rPush(rawKey(key), values));
	}

	@Override
	public Long rPushX(final String key, final String... values) {
		return execute((client)->client.listOperations().rPushX(rawKey(key), values));
	}

	@Override
	public Long rPushX(final byte[] key, final byte[]... values) {
		return execute((client)->client.listOperations().rPushX(rawKey(key), values));
	}

	@Override
	public void pSubscribe(final String[] patterns, final PubSubListener<String> pubSubListener) {
		execute((client)->{
			client.pubSubOperations().pSubscribe(patterns, pubSubListener);
			return null;
		});
	}

	@Override
	public void pSubscribe(final byte[][] patterns, final PubSubListener<byte[]> pubSubListener) {
		execute((client)->{
			client.pubSubOperations().pSubscribe(patterns, pubSubListener);
			return null;
		});
	}

	@Override
	public Long publish(final String channel, final String message) {
		return execute((client)->client.pubSubOperations().publish(channel, message));
	}

	@Override
	public Long publish(final byte[] channel, final byte[] message) {
		return execute((client)->client.pubSubOperations().publish(channel, message));
	}

	@Override
	public List<String> pubsubChannels() {
		return execute((client)->client.pubSubOperations().pubsubChannels());
	}

	@Override
	public List<String> pubsubChannels(final String pattern) {
		return execute((client)->client.pubSubOperations().pubsubChannels(pattern));
	}

	@Override
	public List<byte[]> pubsubChannels(final byte[] pattern) {
		return execute((client)->client.pubSubOperations().pubsubChannels(pattern));
	}

	@Override
	public List<String> pubsubShardChannels() {
		return execute((client)->client.pubSubOperations().pubsubShardChannels());
	}

	@Override
	public List<String> pubsubShardChannels(final String pattern) {
		return execute((client)->client.pubSubOperations().pubsubShardChannels(pattern));
	}

	@Override
	public List<byte[]> pubsubShardChannels(final byte[] pattern) {
		return execute((client)->client.pubSubOperations().pubsubShardChannels(pattern));
	}

	@Override
	public Long pubsubNumPat() {
		return execute((client)->client.pubSubOperations().pubsubNumPat());
	}

	@Override
	public Map<String, Long> pubsubNumSub() {
		return execute((client)->client.pubSubOperations().pubsubNumSub());
	}

	@Override
	public Map<String, Long> pubsubNumSub(final String... channels) {
		return execute((client)->client.pubSubOperations().pubsubNumSub(channels));
	}

	@Override
	public Map<byte[], Long> pubsubNumSub(final byte[]... channels) {
		return execute((client)->client.pubSubOperations().pubsubNumSub(channels));
	}

	@Override
	public Map<String, Long> pubsubShardNumSub() {
		return execute((client)->client.pubSubOperations().pubsubShardNumSub());
	}

	@Override
	public Map<String, Long> pubsubShardNumSub(final String... shardChannels) {
		return execute((client)->client.pubSubOperations().pubsubShardNumSub(shardChannels));
	}

	@Override
	public Map<byte[], Long> pubsubShardNumSub(final byte[]... shardChannels) {
		return execute((client)->client.pubSubOperations().pubsubShardNumSub(shardChannels));
	}

	@Override
	public Object pUnSubscribe() {
		return execute((client)->client.pubSubOperations().pUnSubscribe());
	}

	@Override
	public Object pUnSubscribe(final String... patterns) {
		return execute((client)->client.pubSubOperations().pUnSubscribe(patterns));
	}

	@Override
	public Object pUnSubscribe(final byte[]... patterns) {
		return execute((client)->client.pubSubOperations().pUnSubscribe(patterns));
	}

	@Override
	public void subscribe(final String[] channels, final PubSubListener<String> pubSubListener) {
		execute((client)->{
			client.pubSubOperations().subscribe(channels, pubSubListener);
			return null;
		});
	}

	@Override
	public void subscribe(final byte[][] channels, final PubSubListener<byte[]> pubSubListener) {
		execute((client)->{
			client.pubSubOperations().subscribe(channels, pubSubListener);
			return null;
		});
	}

	@Override
	public Object unSubscribe() {
		return execute((client)->client.pubSubOperations().unSubscribe());
	}

	@Override
	public Object unSubscribe(final String... channels) {
		return execute((client)->client.pubSubOperations().unSubscribe(channels));
	}

	@Override
	public Object unSubscribe(final byte[]... channels) {
		return execute((client)->client.pubSubOperations().unSubscribe(channels));
	}

	@Override
	public Object eval(final String script) {
		return execute((client)->client.scriptingOperations().eval(script));
	}

	@Override
	public Object eval(final byte[] script) {
		return execute((client)->client.scriptingOperations().eval(script));
	}

	@Override
	public Object eval(final String script, final String... params) {
		return execute((client)->client.scriptingOperations().eval(script, params));
	}

	@Override
	public Object eval(final byte[] script, final byte[]... params) {
		return execute((client)->client.scriptingOperations().eval(script, params));
	}

	@Override
	public Object eval(final String script, final String[] keys, final String[] arguments) {
		return execute((client)->client.scriptingOperations().eval(script, rawKeys(keys), arguments));
	}

	@Override
	public Object eval(final byte[] script, final byte[][] keys, final byte[][] arguments) {
		return execute((client)->client.scriptingOperations().eval(script, rawKeys(keys), arguments));
	}

	@Override
	public Object evalSha(final String digest) {
		return execute((client)->client.scriptingOperations().evalSha(digest));
	}

	@Override
	public Object evalSha(final byte[] digest) {
		return execute((client)->client.scriptingOperations().evalSha(digest));
	}

	@Override
	public Object evalSha(final String digest, final String... params) {
		return execute((client)->client.scriptingOperations().evalSha(digest, params));
	}

	@Override
	public Object evalSha(final byte[] digest, final byte[]... params) {
		return execute((client)->client.scriptingOperations().evalSha(digest, params));
	}

	@Override
	public Object evalSha(final String digest, final String[] keys, final String[] arguments) {
		return execute((client)->client.scriptingOperations().evalSha(digest, rawKeys(keys), arguments));
	}

	@Override
	public Object evalSha(final byte[] digest, final byte[][] keys, final byte[][] arguments) {
		return execute((client)->client.scriptingOperations().evalSha(digest, rawKeys(keys), arguments));
	}

	@Override
	public List<Boolean> scriptExists(final String... sha1) {
		return execute((client)->client.scriptingOperations().scriptExists(sha1));
	}

	@Override
	public List<Boolean> scriptExists(final byte[]... sha1) {
		return execute((client)->client.scriptingOperations().scriptExists(sha1));
	}

	@Override
	public Status scriptFlush() {
		return execute((client)->client.scriptingOperations().scriptFlush());
	}

	@Override
	public Status scriptFlush(final FlushMode mode) {
		return execute((client)->client.scriptingOperations().scriptFlush(mode));
	}

	@Override
	public String scriptLoad(final String script) {
		return execute((client)->client.scriptingOperations().scriptLoad(script));
	}

	@Override
	public byte[] scriptLoad(final byte[] script) {
		return execute((client)->client.scriptingOperations().scriptLoad(script));
	}

	@Override
	public Status scriptKill() {
		return execute((client)->client.scriptingOperations().scriptKill());
	}

	@Override
	public String bgRewriteAof() {
		return execute((client)->client.serverOperations().bgRewriteAof());
	}

	@Override
	public String bgSave() {
		return execute((client)->client.serverOperations().bgSave());
	}

	@Override
	public Status configSet(final String parameter, final String value) {
		return execute((client)->client.serverOperations().configSet(parameter, value));
	}

	@Override
	public Status configSet(final byte[] parameter, final byte[] value) {
		return execute((client)->client.serverOperations().configSet(parameter, value));
	}

	@Override
	public Status configSet(final Map<String, String> configs) {
		return execute((client)->client.serverOperations().configSet(configs));
	}

	@Override
	public Map<String, String> configGet(final String pattern) {
		return execute((client)->client.serverOperations().configGet(pattern));
	}

	@Override
	public Map<byte[], byte[]> configGet(final byte[] pattern) {
		return execute((client)->client.serverOperations().configGet(pattern));
	}

	@Override
	public Status configResetStat() {
		return execute((client)->client.serverOperations().configResetStat());
	}

	@Override
	public Status configRewrite() {
		return execute((client)->client.serverOperations().configRewrite());
	}

	@Override
	public Long dbSize() {
		return execute((client)->client.serverOperations().dbSize());
	}

	@Override
	public Status failover() {
		return execute((client)->client.serverOperations().failover());
	}

	@Override
	public Status failover(final String host, final int port) {
		return execute((client)->client.serverOperations().failover(host, port));
	}

	@Override
	public Status failover(final String host, final int port, final int timeout) {
		return execute((client)->client.serverOperations().failover(host, port, timeout));
	}

	@Override
	public Status failover(final String host, final int port, final boolean isForce, final int timeout) {
		return execute((client)->client.serverOperations().failover(host, port, isForce, timeout));
	}

	@Override
	public Status failover(final int timeout) {
		return execute((client)->client.serverOperations().failover(timeout));
	}

	@Override
	public Status flushAll() {
		return execute((client)->client.serverOperations().flushAll());
	}

	@Override
	public Status flushAll(final FlushMode mode) {
		return execute((client)->client.serverOperations().flushAll(mode));
	}

	@Override
	public Status flushDb() {
		return execute((client)->client.serverOperations().flushDb());
	}

	@Override
	public Status flushDb(final FlushMode mode) {
		return execute((client)->client.serverOperations().flushDb(mode));
	}

	@Override
	public Info info() {
		return execute((client)->client.serverOperations().info());
	}

	@Override
	public Info info(final Info.Section section) {
		return execute((client)->client.serverOperations().info(section));
	}

	@Override
	public Long lastSave() {
		return execute((client)->client.serverOperations().lastSave());
	}

	@Override
	public String memoryDoctor() {
		return execute((client)->client.serverOperations().memoryDoctor());
	}

	@Override
	public Status memoryPurge() {
		return execute((client)->client.serverOperations().memoryPurge());
	}

	@Override
	public MemoryStats memoryStats() {
		return execute((client)->client.serverOperations().memoryStats());
	}

	@Override
	public Long memoryUsage(final String key) {
		return execute((client)->client.serverOperations().memoryUsage(key));
	}

	@Override
	public Long memoryUsage(final byte[] key) {
		return execute((client)->client.serverOperations().memoryUsage(key));
	}

	@Override
	public Long memoryUsage(final String key, final int samples) {
		return execute((client)->client.serverOperations().memoryUsage(rawKey(key), samples));
	}

	@Override
	public Long memoryUsage(final byte[] key, final int samples) {
		return execute((client)->client.serverOperations().memoryUsage(rawKey(key), samples));
	}

	@Override
	public List<Module> moduleList() {
		return execute((client)->client.serverOperations().moduleList());
	}

	@Override
	public Status moduleLoad(final String path) {
		return execute((client)->client.serverOperations().moduleLoad(path));
	}

	@Override
	public Status moduleLoad(final byte[] path) {
		return execute((client)->client.serverOperations().moduleLoad(path));
	}

	@Override
	public Status moduleLoad(final String path, final String... arguments) {
		return execute((client)->client.serverOperations().moduleLoad(path, arguments));
	}

	@Override
	public Status moduleLoad(final byte[] path, final byte[]... arguments) {
		return execute((client)->client.serverOperations().moduleLoad(path, arguments));
	}

	@Override
	public Status moduleUnLoad(final String name) {
		return execute((client)->client.serverOperations().moduleUnLoad(name));
	}

	@Override
	public Status moduleUnLoad(final byte[] name) {
		return execute((client)->client.serverOperations().moduleUnLoad(name));
	}

	@Override
	public void monitor(final RedisMonitor redisMonitor) {
		execute((client)->{
			client.serverOperations().monitor(redisMonitor);
			return null;
		});
	}

	@Override
	public Object pSync(final String replicationId, final long offset) {
		return execute((client)->client.serverOperations().pSync(replicationId, offset));
	}

	@Override
	public Object pSync(final byte[] replicationId, final long offset) {
		return execute((client)->client.serverOperations().pSync(replicationId, offset));
	}

	@Override
	public void sync() {
		execute((client)->{
			client.serverOperations().sync();
			return null;
		});
	}

	@Override
	public Status replicaOf(final String host, final int port) {
		return execute((client)->client.serverOperations().replicaOf(host, port));
	}

	@Override
	public Status slaveOf(final String host, final int port) {
		return execute((client)->client.serverOperations().slaveOf(host, port));
	}

	@Override
	public Role role() {
		return execute((client)->client.serverOperations().role());
	}

	@Override
	public Status save() {
		return execute((client)->client.serverOperations().save());
	}

	@Override
	public void shutdown() {
		execute((client)->{
			client.serverOperations().shutdown();
			return null;
		});
	}

	@Override
	public void shutdown(final boolean save) {
		execute((client)->{
			client.serverOperations().shutdown(save);
			return null;
		});
	}

	@Override
	public List<SlowLog> slowLogGet() {
		return execute((client)->client.serverOperations().slowLogGet());
	}

	@Override
	public List<SlowLog> slowLogGet(final int count) {
		return execute((client)->client.serverOperations().slowLogGet(count));
	}

	@Override
	public Long slowLogLen() {
		return execute((client)->client.serverOperations().slowLogLen());
	}

	@Override
	public Status slowLogReset() {
		return execute((client)->client.serverOperations().slowLogReset());
	}

	@Override
	public Status swapdb(final int db1, final int db2) {
		return execute((client)->client.serverOperations().swapdb(db1, db2));
	}

	@Override
	public RedisServerTime time() {
		return execute((client)->client.serverOperations().time());
	}

	@Override
	public Long sAdd(final String key, final String... members) {
		return execute((client)->client.setOperations().sAdd(rawKey(key), members));
	}

	@Override
	public Long sAdd(final byte[] key, final byte[]... members) {
		return execute((client)->client.setOperations().sAdd(rawKey(key), members));
	}

	@Override
	public Long sCard(final String key) {
		return execute((client)->client.setOperations().sCard(rawKey(key)));
	}

	@Override
	public Long sCard(final byte[] key) {
		return execute((client)->client.setOperations().sCard(rawKey(key)));
	}

	@Override
	public Set<String> sDiff(final String... keys) {
		return execute((client)->client.setOperations().sDiff(rawKeys(keys)));
	}

	@Override
	public Set<byte[]> sDiff(final byte[]... keys) {
		return execute((client)->client.setOperations().sDiff(rawKeys(keys)));
	}

	@Override
	public Long sDiffStore(final String destKey, final String... keys) {
		return execute((client)->client.setOperations().sDiffStore(rawKey(destKey), rawKeys(keys)));
	}

	@Override
	public Long sDiffStore(final byte[] destKey, final byte[]... keys) {
		return execute((client)->client.setOperations().sDiffStore(rawKey(destKey), rawKeys(keys)));
	}

	@Override
	public Set<String> sInter(final String... keys) {
		return execute((client)->client.setOperations().sInter(rawKeys(keys)));
	}

	@Override
	public Set<byte[]> sInter(final byte[]... keys) {
		return execute((client)->client.setOperations().sInter(rawKeys(keys)));
	}

	@Override
	public Long sInterStore(final String destKey, final String... keys) {
		return execute((client)->client.setOperations().sDiffStore(rawKey(destKey), rawKeys(keys)));
	}

	@Override
	public Long sInterStore(final byte[] destKey, final byte[]... keys) {
		return execute((client)->client.setOperations().sDiffStore(rawKey(destKey), rawKeys(keys)));
	}

	@Override
	public Boolean sIsMember(final String key, final String member) {
		return execute((client)->client.setOperations().sIsMember(rawKey(key), member));
	}

	@Override
	public Boolean sIsMember(final byte[] key, final byte[] member) {
		return execute((client)->client.setOperations().sIsMember(rawKey(key), member));
	}

	@Override
	public List<Boolean> smIsMember(final String key, final String... members) {
		return execute((client)->client.setOperations().smIsMember(rawKey(key), members));
	}

	@Override
	public List<Boolean> smIsMember(final byte[] key, final byte[]... members) {
		return execute((client)->client.setOperations().smIsMember(rawKey(key), members));
	}

	@Override
	public Set<String> sMembers(final String key) {
		return execute((client)->client.setOperations().sMembers(rawKey(key)));
	}

	@Override
	public Set<byte[]> sMembers(final byte[] key) {
		return execute((client)->client.setOperations().sMembers(rawKey(key)));
	}

	@Override
	public Status sMove(final String key, final String destKey, final String member) {
		return execute((client)->client.setOperations().sMove(rawKey(key), rawKey(destKey), member));
	}

	@Override
	public Status sMove(final byte[] key, final byte[] destKey, final byte[] member) {
		return execute((client)->client.setOperations().sMove(rawKey(key), rawKey(destKey), member));
	}

	@Override
	public String sPop(final String key) {
		return execute((client)->client.setOperations().sPop(rawKey(key)));
	}

	@Override
	public byte[] sPop(final byte[] key) {
		return execute((client)->client.setOperations().sPop(rawKey(key)));
	}

	@Override
	public Set<String> sPop(final String key, final int count) {
		return execute((client)->client.setOperations().sPop(rawKey(key), count));
	}

	@Override
	public Set<byte[]> sPop(final byte[] key, final int count) {
		return execute((client)->client.setOperations().sPop(rawKey(key), count));
	}

	@Override
	public String sRandMember(final String key) {
		return execute((client)->client.setOperations().sRandMember(rawKey(key)));
	}

	@Override
	public byte[] sRandMember(final byte[] key) {
		return execute((client)->client.setOperations().sRandMember(rawKey(key)));
	}

	@Override
	public List<String> sRandMember(final String key, final int count) {
		return execute((client)->client.setOperations().sRandMember(rawKey(key), count));
	}

	@Override
	public List<byte[]> sRandMember(final byte[] key, final int count) {
		return execute((client)->client.setOperations().sRandMember(rawKey(key), count));
	}

	@Override
	public Long sRem(final String key, final String... members) {
		return execute((client)->client.setOperations().sRem(rawKey(key), members));
	}

	@Override
	public Long sRem(final byte[] key, final byte[]... members) {
		return execute((client)->client.setOperations().sRem(rawKey(key), members));
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final long cursor) {
		return execute((client)->client.setOperations().sScan(rawKey(key), cursor));
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final long cursor) {
		return execute((client)->client.setOperations().sScan(rawKey(key), cursor));
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final String cursor) {
		return execute((client)->client.setOperations().sScan(rawKey(key), cursor));
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor) {
		return execute((client)->client.setOperations().sScan(rawKey(key), cursor));
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final long cursor, final String pattern) {
		return execute((client)->client.setOperations().sScan(rawKey(key), cursor, pattern));
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final long cursor, final byte[] pattern) {
		return execute((client)->client.setOperations().sScan(rawKey(key), cursor, pattern));
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final String cursor, final String pattern) {
		return execute((client)->client.setOperations().sScan(rawKey(key), cursor, pattern));
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor, final byte[] pattern) {
		return execute((client)->client.setOperations().sScan(rawKey(key), cursor, pattern));
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final long cursor, final int count) {
		return execute((client)->client.setOperations().sScan(rawKey(key), cursor, count));
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final long cursor, final int count) {
		return execute((client)->client.setOperations().sScan(rawKey(key), cursor, count));
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final String cursor, final int count) {
		return execute((client)->client.setOperations().sScan(rawKey(key), cursor, count));
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor, final int count) {
		return execute((client)->client.setOperations().sScan(rawKey(key), cursor, count));
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final long cursor, final String pattern, final int count) {
		return execute((client)->client.setOperations().sScan(rawKey(key), cursor, pattern, count));
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final long cursor, final byte[] pattern, final int count) {
		return execute((client)->client.setOperations().sScan(rawKey(key), cursor, pattern, count));
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final String cursor, final String pattern,
										  final int count) {
		return execute((client)->client.setOperations().sScan(rawKey(key), cursor, pattern, count));
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor, final byte[] pattern,
										  final int count) {
		return execute((client)->client.setOperations().sScan(rawKey(key), cursor, pattern, count));
	}

	@Override
	public Set<String> sUnion(final String... keys) {
		return execute((client)->client.setOperations().sUnion(rawKeys(keys)));
	}

	@Override
	public Set<byte[]> sUnion(final byte[]... keys) {
		return execute((client)->client.setOperations().sUnion(rawKeys(keys)));
	}

	@Override
	public Long sUnionStore(final String destKey, final String... keys) {
		return execute((client)->client.setOperations().sUnionStore(rawKey(destKey), rawKeys(keys)));
	}

	@Override
	public Long sUnionStore(final byte[] destKey, final byte[]... keys) {
		return execute((client)->client.setOperations().sUnionStore(rawKey(destKey), rawKeys(keys)));
	}

	@Override
	public Tuple zPopMin(final String key) {
		return execute((client)->client.sortedSetOperations().zPopMin(rawKey(key)));
	}

	@Override
	public Tuple zPopMin(final byte[] key) {
		return execute((client)->client.sortedSetOperations().zPopMin(rawKey(key)));
	}

	@Override
	public List<Tuple> zPopMin(final String key, int count) {
		return execute((client)->client.sortedSetOperations().zPopMin(rawKey(key), count));
	}

	@Override
	public List<Tuple> zPopMin(final byte[] key, int count) {
		return execute((client)->client.sortedSetOperations().zPopMin(rawKey(key), count));
	}

	@Override
	public Tuple zPopMax(final String key) {
		return execute((client)->client.sortedSetOperations().zPopMax(rawKey(key)));
	}

	@Override
	public Tuple zPopMax(final byte[] key) {
		return execute((client)->client.sortedSetOperations().zPopMax(rawKey(key)));
	}

	@Override
	public List<Tuple> zPopMax(final String key, final int count) {
		return execute((client)->client.sortedSetOperations().zPopMax(rawKey(key), count));
	}

	@Override
	public List<Tuple> zPopMax(final byte[] key, final int count) {
		return execute((client)->client.sortedSetOperations().zPopMax(rawKey(key), count));
	}

	@Override
	public KeyValue<String, Tuple> bzPopMin(final String[] keys, final int timeout) {
		return execute((client)->client.sortedSetOperations().bzPopMin(rawKeys(keys), timeout));
	}

	@Override
	public KeyValue<byte[], Tuple> bzPopMin(final byte[][] keys, final int timeout) {
		return execute((client)->client.sortedSetOperations().bzPopMin(rawKeys(keys), timeout));
	}

	@Override
	public KeyValue<String, Tuple> bzPopMax(final String[] keys, final int timeout) {
		return execute((client)->client.sortedSetOperations().bzPopMax(rawKeys(keys), timeout));
	}

	@Override
	public KeyValue<byte[], Tuple> bzPopMax(final byte[][] keys, final int timeout) {
		return execute((client)->client.sortedSetOperations().bzPopMax(rawKeys(keys), timeout));
	}

	@Override
	public Long zAdd(final String key, final Map<String, Double> members) {
		return execute((client)->client.sortedSetOperations().zAdd(rawKey(key), members));
	}

	@Override
	public Long zAdd(final byte[] key, final Map<byte[], Double> members) {
		return execute((client)->client.sortedSetOperations().zAdd(rawKey(key), members));
	}

	@Override
	public Long zAdd(final String key, final Map<String, Double> members, final NxXx nxXx) {
		return execute((client)->client.sortedSetOperations().zAdd(rawKey(key), members, nxXx));
	}

	@Override
	public Long zAdd(final byte[] key, final Map<byte[], Double> members, final NxXx nxXx) {
		return execute((client)->client.sortedSetOperations().zAdd(rawKey(key), members, nxXx));
	}

	@Override
	public Long zAdd(final String key, final Map<String, Double> members, final GtLt gtLt) {
		return execute((client)->client.sortedSetOperations().zAdd(rawKey(key), members, gtLt));
	}

	@Override
	public Long zAdd(final byte[] key, final Map<byte[], Double> members, final GtLt gtLt) {
		return execute((client)->client.sortedSetOperations().zAdd(rawKey(key), members, gtLt));
	}

	@Override
	public Long zAdd(final String key, final Map<String, Double> members, final boolean ch) {
		return execute((client)->client.sortedSetOperations().zAdd(rawKey(key), members, ch));
	}

	@Override
	public Long zAdd(final byte[] key, final Map<byte[], Double> members, final boolean ch) {
		return execute((client)->client.sortedSetOperations().zAdd(rawKey(key), members, ch));
	}

	@Override
	public Long zAdd(final String key, final Map<String, Double> members, final NxXx nxXx, final GtLt gtLt) {
		return execute((client)->client.sortedSetOperations().zAdd(rawKey(key), members, nxXx, gtLt));
	}

	@Override
	public Long zAdd(final byte[] key, final Map<byte[], Double> members, final NxXx nxXx, final GtLt gtLt) {
		return execute((client)->client.sortedSetOperations().zAdd(rawKey(key), members, nxXx, gtLt));
	}

	@Override
	public Long zAdd(final String key, final Map<String, Double> members, final NxXx nxXx, final boolean ch) {
		return execute((client)->client.sortedSetOperations().zAdd(rawKey(key), members, nxXx, ch));
	}

	@Override
	public Long zAdd(final byte[] key, final Map<byte[], Double> members, final NxXx nxXx, final boolean ch) {
		return execute((client)->client.sortedSetOperations().zAdd(rawKey(key), members, nxXx, ch));
	}

	@Override
	public Long zAdd(final String key, final Map<String, Double> members, final GtLt gtLt, final boolean ch) {
		return execute((client)->client.sortedSetOperations().zAdd(rawKey(key), members, gtLt, ch));
	}

	@Override
	public Long zAdd(final byte[] key, final Map<byte[], Double> members, final GtLt gtLt, final boolean ch) {
		return execute((client)->client.sortedSetOperations().zAdd(rawKey(key), members, gtLt, ch));
	}

	@Override
	public Long zAdd(final String key, final Map<String, Double> members, final NxXx nxXx, final GtLt gtLt,
					 final boolean ch) {
		return execute((client)->client.sortedSetOperations().zAdd(rawKey(key), members, nxXx, gtLt, ch));
	}

	@Override
	public Long zAdd(final byte[] key, final Map<byte[], Double> members, final NxXx nxXx, final GtLt gtLt,
					 final boolean ch) {
		return execute((client)->client.sortedSetOperations().zAdd(rawKey(key), members, nxXx, gtLt, ch));
	}

	@Override
	public Long zCard(final String key) {
		return execute((client)->client.sortedSetOperations().zCard(rawKey(key)));
	}

	@Override
	public Long zCard(final byte[] key) {
		return execute((client)->client.sortedSetOperations().zCard(rawKey(key)));
	}

	@Override
	public Long zCount(final String key, final double min, final double max) {
		return execute((client)->client.sortedSetOperations().zCount(rawKey(key), min, max));
	}

	@Override
	public Long zCount(final byte[] key, final double min, final double max) {
		return execute((client)->client.sortedSetOperations().zCount(rawKey(key), min, max));
	}

	@Override
	public List<String> zDiff(final String... keys) {
		return execute((client)->client.sortedSetOperations().zDiff(rawKeys(keys)));
	}

	@Override
	public List<byte[]> zDiff(final byte[]... keys) {
		return execute((client)->client.sortedSetOperations().zDiff(rawKeys(keys)));
	}

	@Override
	public List<Tuple> zDiffWithScores(final String... keys) {
		return execute((client)->client.sortedSetOperations().zDiffWithScores(rawKeys(keys)));
	}

	@Override
	public List<Tuple> zDiffWithScores(final byte[]... keys) {
		return execute((client)->client.sortedSetOperations().zDiffWithScores(rawKeys(keys)));
	}

	@Override
	public Long zDiffStore(final String destKey, final String... keys) {
		return execute((client)->client.sortedSetOperations().zDiffStore(rawKey(destKey), rawKeys(keys)));
	}

	@Override
	public Long zDiffStore(final byte[] destKey, final byte[]... keys) {
		return execute((client)->client.sortedSetOperations().zDiffStore(rawKey(destKey), rawKeys(keys)));
	}

	@Override
	public Double zIncrBy(final String key, final double increment, final String member) {
		return execute((client)->client.sortedSetOperations().zIncrBy(rawKey(key), increment, member));
	}

	@Override
	public Double zIncrBy(final byte[] key, final double increment, final byte[] member) {
		return execute((client)->client.sortedSetOperations().zIncrBy(rawKey(key), increment, member));
	}

	@Override
	public List<String> zInter(final String... keys) {
		return execute((client)->client.sortedSetOperations().zInter(rawKeys(keys)));
	}

	@Override
	public List<byte[]> zInter(final byte[]... keys) {
		return execute((client)->client.sortedSetOperations().zInter(rawKeys(keys)));
	}

	@Override
	public List<String> zInter(final String[] keys, final Aggregate aggregate) {
		return execute((client)->client.sortedSetOperations().zInter(rawKeys(keys), aggregate));
	}

	@Override
	public List<byte[]> zInter(final byte[][] keys, final Aggregate aggregate) {
		return execute((client)->client.sortedSetOperations().zInter(rawKeys(keys), aggregate));
	}

	@Override
	public List<String> zInter(final String[] keys, final double... weights) {
		return execute((client)->client.sortedSetOperations().zInter(rawKeys(keys), weights));
	}

	@Override
	public List<byte[]> zInter(final byte[][] keys, final double... weights) {
		return execute((client)->client.sortedSetOperations().zInter(rawKeys(keys), weights));
	}

	@Override
	public List<String> zInter(final String[] keys, final Aggregate aggregate, final double... weights) {
		return execute((client)->client.sortedSetOperations().zInter(rawKeys(keys), aggregate, weights));
	}

	@Override
	public List<byte[]> zInter(final byte[][] keys, final Aggregate aggregate, final double... weights) {
		return execute((client)->client.sortedSetOperations().zInter(rawKeys(keys), aggregate, weights));
	}

	@Override
	public List<Tuple> zInterWithScores(final String... keys) {
		return execute((client)->client.sortedSetOperations().zInterWithScores(rawKeys(keys)));
	}

	@Override
	public List<Tuple> zInterWithScores(final byte[]... keys) {
		return execute((client)->client.sortedSetOperations().zInterWithScores(rawKeys(keys)));
	}

	@Override
	public List<Tuple> zInterWithScores(final String[] keys, final Aggregate aggregate) {
		return execute((client)->client.sortedSetOperations().zInterWithScores(rawKeys(keys), aggregate));
	}

	@Override
	public List<Tuple> zInterWithScores(final byte[][] keys, final Aggregate aggregate) {
		return execute((client)->client.sortedSetOperations().zInterWithScores(rawKeys(keys), aggregate));
	}

	@Override
	public List<Tuple> zInterWithScores(final String[] keys, final double... weights) {
		return execute((client)->client.sortedSetOperations().zInterWithScores(rawKeys(keys), weights));
	}

	@Override
	public List<Tuple> zInterWithScores(final byte[][] keys, final double... weights) {
		return execute((client)->client.sortedSetOperations().zInterWithScores(rawKeys(keys), weights));
	}

	@Override
	public List<Tuple> zInterWithScores(final String[] keys, final Aggregate aggregate, final double... weights) {
		return execute((client)->client.sortedSetOperations().zInterWithScores(rawKeys(keys), aggregate, weights));
	}

	@Override
	public List<Tuple> zInterWithScores(final byte[][] keys, final Aggregate aggregate, final double... weights) {
		return execute((client)->client.sortedSetOperations().zInterWithScores(rawKeys(keys), aggregate, weights));
	}

	@Override
	public Long zInterStore(final String destKey, final String... keys) {
		return execute((client)->client.sortedSetOperations().zInterStore(rawKey(destKey), rawKeys(keys)));
	}

	@Override
	public Long zInterStore(final byte[] destKey, final byte[]... keys) {
		return execute((client)->client.sortedSetOperations().zInterStore(rawKey(destKey), rawKeys(keys)));
	}

	@Override
	public Long zInterStore(final String destKey, final String[] keys, final Aggregate aggregate) {
		return execute((client)->client.sortedSetOperations().zInterStore(rawKey(destKey), rawKeys(keys), aggregate));
	}

	@Override
	public Long zInterStore(final byte[] destKey, final byte[][] keys, final Aggregate aggregate) {
		return execute((client)->client.sortedSetOperations().zInterStore(rawKey(destKey), rawKeys(keys), aggregate));
	}

	@Override
	public Long zInterStore(final String destKey, final String[] keys, final double... weights) {
		return execute((client)->client.sortedSetOperations().zInterStore(rawKey(destKey), rawKeys(keys), weights));
	}

	@Override
	public Long zInterStore(final byte[] destKey, final byte[][] keys, final double... weights) {
		return execute((client)->client.sortedSetOperations().zInterStore(rawKey(destKey), rawKeys(keys), weights));
	}

	@Override
	public Long zInterStore(final String destKey, final String[] keys, final Aggregate aggregate,
							final double... weights) {
		return execute(
				(client)->client.sortedSetOperations().zInterStore(rawKey(destKey), rawKeys(keys), aggregate, weights));
	}

	@Override
	public Long zInterStore(final byte[] destKey, final byte[][] keys, final Aggregate aggregate,
							final double... weights) {
		return execute(
				(client)->client.sortedSetOperations().zInterStore(rawKey(destKey), rawKeys(keys), aggregate, weights));
	}

	@Override
	public Long zLexCount(final String key, final double min, final double max) {
		return execute((client)->client.sortedSetOperations().zLexCount(rawKey(key), min, max));
	}

	@Override
	public Long zLexCount(final byte[] key, final double min, final double max) {
		return execute((client)->client.sortedSetOperations().zLexCount(rawKey(key), min, max));
	}

	@Override
	public List<Double> zMScore(final String key, final String... members) {
		return execute((client)->client.sortedSetOperations().zMScore(rawKey(key), members));
	}

	@Override
	public List<Double> zMScore(final byte[] key, final byte[]... members) {
		return execute((client)->client.sortedSetOperations().zMScore(rawKey(key), members));
	}

	@Override
	public String zRandMember(final String key) {
		return execute((client)->client.sortedSetOperations().zRandMember(rawKey(key)));
	}

	@Override
	public byte[] zRandMember(final byte[] key) {
		return execute((client)->client.sortedSetOperations().zRandMember(rawKey(key)));
	}

	@Override
	public List<String> zRandMember(final String key, final int count) {
		return execute((client)->client.sortedSetOperations().zRandMember(rawKey(key), count));
	}

	@Override
	public List<byte[]> zRandMember(final byte[] key, final int count) {
		return execute((client)->client.sortedSetOperations().zRandMember(rawKey(key), count));
	}

	@Override
	public List<Tuple> zRandMemberWithScores(final String key, final int count) {
		return execute((client)->client.sortedSetOperations().zRandMemberWithScores(rawKey(key), count));
	}

	@Override
	public List<Tuple> zRandMemberWithScores(final byte[] key, final int count) {
		return execute((client)->client.sortedSetOperations().zRandMemberWithScores(rawKey(key), count));
	}

	@Override
	public List<String> zRange(final String key, final long start, final long end) {
		return execute((client)->client.sortedSetOperations().zRange(rawKey(key), start, end));
	}

	@Override
	public List<byte[]> zRange(final byte[] key, final long start, final long end) {
		return execute((client)->client.sortedSetOperations().zRange(rawKey(key), start, end));
	}

	@Override
	public List<Tuple> zRangeWithScores(final String key, final long start, final long end) {
		return execute((client)->client.sortedSetOperations().zRangeWithScores(rawKey(key), start, end));
	}

	@Override
	public List<Tuple> zRangeWithScores(final byte[] key, final long start, final long end) {
		return execute((client)->client.sortedSetOperations().zRangeWithScores(rawKey(key), start, end));
	}

	@Override
	public List<String> zRangeByLex(final String key, final double min, final double max) {
		return execute((client)->client.sortedSetOperations().zRangeByLex(rawKey(key), min, max));
	}

	@Override
	public List<byte[]> zRangeByLex(final byte[] key, final double min, final double max) {
		return execute((client)->client.sortedSetOperations().zRangeByLex(rawKey(key), min, max));
	}

	@Override
	public List<String> zRangeByLex(final String key, final double min, final double max, final long offset,
									final int count) {
		return execute((client)->client.sortedSetOperations().zRangeByLex(rawKey(key), min, max, offset, count));
	}

	@Override
	public List<byte[]> zRangeByLex(final byte[] key, final double min, final double max, final long offset,
									final int count) {
		return execute((client)->client.sortedSetOperations().zRangeByLex(rawKey(key), min, max, offset, count));
	}

	@Override
	public List<String> zRangeByScore(final String key, final double min, final double max) {
		return execute((client)->client.sortedSetOperations().zRangeByLex(rawKey(key), min, max));
	}

	@Override
	public List<byte[]> zRangeByScore(final byte[] key, final double min, final double max) {
		return execute((client)->client.sortedSetOperations().zRangeByScore(rawKey(key), min, max));
	}

	@Override
	public List<String> zRangeByScore(final String key, final double min, final double max, final long offset,
									  final int count) {
		return execute((client)->client.sortedSetOperations().zRangeByScore(rawKey(key), min, max, offset, count));
	}

	@Override
	public List<byte[]> zRangeByScore(final byte[] key, final double min, final double max, final long offset,
									  final int count) {
		return execute((client)->client.sortedSetOperations().zRangeByScore(rawKey(key), min, max, offset, count));
	}

	@Override
	public List<Tuple> zRangeByScoreWithScores(final String key, final double min, final double max) {
		return execute((client)->client.sortedSetOperations().zRangeByScoreWithScores(rawKey(key), min, max));
	}

	@Override
	public List<Tuple> zRangeByScoreWithScores(final byte[] key, final double min, final double max) {
		return execute((client)->client.sortedSetOperations().zRangeByScoreWithScores(rawKey(key), min, max));
	}

	@Override
	public List<Tuple> zRangeByScoreWithScores(final String key, final double min, final double max, final long offset,
											   final int count) {
		return execute(
				(client)->client.sortedSetOperations().zRangeByScoreWithScores(rawKey(key), min, max, offset, count));
	}

	@Override
	public List<Tuple> zRangeByScoreWithScores(final byte[] key, final double min, final double max, final long offset,
											   final int count) {
		return execute(
				(client)->client.sortedSetOperations().zRangeByScoreWithScores(rawKey(key), min, max, offset, count));
	}

	@Override
	public Long zRangeStore(final String destKey, final String key, final long start, final long end) {
		return execute((client)->client.sortedSetOperations().zRangeStore(rawKey(destKey), rawKey(key), start, end));
	}

	@Override
	public Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end) {
		return execute((client)->client.sortedSetOperations().zRangeStore(rawKey(destKey), rawKey(key), start, end));
	}

	@Override
	public Long zRangeStore(final String destKey, final String key, final long start, final long end,
							final ZRangeBy by) {
		return execute(
				(client)->client.sortedSetOperations().zRangeStore(rawKey(destKey), rawKey(key), start, end, by));
	}

	@Override
	public Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end,
							final ZRangeBy by) {
		return execute(
				(client)->client.sortedSetOperations().zRangeStore(rawKey(destKey), rawKey(key), start, end, by));
	}

	@Override
	public Long zRangeStore(final String destKey, final String key, final long start, final long end,
							final boolean rev) {
		return execute(
				(client)->client.sortedSetOperations().zRangeStore(rawKey(destKey), rawKey(key), start, end, rev));
	}

	@Override
	public Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end,
							final boolean rev) {
		return execute(
				(client)->client.sortedSetOperations().zRangeStore(rawKey(destKey), rawKey(key), start, end, rev));
	}

	@Override
	public Long zRangeStore(final String destKey, final String key, final long start, final long end, final long offset,
							final int count) {
		return execute((client)->client.sortedSetOperations()
				.zRangeStore(rawKey(destKey), rawKey(key), start, end, offset, count));
	}

	@Override
	public Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end, final long offset,
							final int count) {
		return execute((client)->client.sortedSetOperations()
				.zRangeStore(rawKey(destKey), rawKey(key), start, end, offset, count));
	}

	@Override
	public Long zRangeStore(final String destKey, final String key, final long start, final long end, final ZRangeBy by,
							final boolean rev) {
		return execute(
				(client)->client.sortedSetOperations().zRangeStore(rawKey(destKey), rawKey(key), start, end, by, rev));
	}

	@Override
	public Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end, final ZRangeBy by,
							final boolean rev) {
		return execute(
				(client)->client.sortedSetOperations().zRangeStore(rawKey(destKey), rawKey(key), start, end, by, rev));
	}

	@Override
	public Long zRangeStore(final String destKey, final String key, final long start, final long end, final ZRangeBy by,
							final long offset, final int count) {
		return execute((client)->client.sortedSetOperations()
				.zRangeStore(rawKey(destKey), rawKey(key), start, end, by, offset, count));
	}

	@Override
	public Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end, final ZRangeBy by,
							final long offset, final int count) {
		return execute((client)->client.sortedSetOperations()
				.zRangeStore(rawKey(destKey), rawKey(key), start, end, by, offset, count));
	}

	@Override
	public Long zRangeStore(final String destKey, final String key, final long start, final long end, final boolean rev,
							final long offset, final int count) {
		return execute((client)->client.sortedSetOperations()
				.zRangeStore(rawKey(destKey), rawKey(key), start, end, rev, offset, count));
	}

	@Override
	public Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end, final boolean rev,
							final long offset, final int count) {
		return execute((client)->client.sortedSetOperations()
				.zRangeStore(rawKey(destKey), rawKey(key), start, end, rev, offset, count));
	}

	@Override
	public Long zRangeStore(final String destKey, final String key, final long start, final long end, final ZRangeBy by,
							final boolean rev, final long offset, final int count) {
		return execute((client)->client.sortedSetOperations()
				.zRangeStore(rawKey(destKey), rawKey(key), start, end, by, rev, offset, count));
	}

	@Override
	public Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end, final ZRangeBy by,
							final boolean rev, final long offset, final int count) {
		return execute((client)->client.sortedSetOperations()
				.zRangeStore(rawKey(destKey), rawKey(key), start, end, by, rev, offset, count));
	}

	@Override
	public Long zRank(final String key, final String member) {
		return execute((client)->client.sortedSetOperations().zRank(rawKey(key), member));
	}

	@Override
	public Long zRank(final byte[] key, final byte[] member) {
		return execute((client)->client.sortedSetOperations().zRank(rawKey(key), member));
	}

	@Override
	public Long zRem(final String key, final String... members) {
		return execute((client)->client.sortedSetOperations().zRem(rawKey(key), members));
	}

	@Override
	public Long zRem(final byte[] key, final byte[]... members) {
		return execute((client)->client.sortedSetOperations().zRem(rawKey(key), members));
	}

	@Override
	public Long zRemRangeByLex(final String key, final double min, final double max) {
		return execute((client)->client.sortedSetOperations().zRemRangeByLex(rawKey(key), min, max));
	}

	@Override
	public Long zRemRangeByLex(final byte[] key, final double min, final double max) {
		return execute((client)->client.sortedSetOperations().zRemRangeByLex(rawKey(key), min, max));
	}

	@Override
	public Long zRemRangeByScore(final String key, final double min, final double max) {
		return execute((client)->client.sortedSetOperations().zRemRangeByScore(rawKey(key), min, max));
	}

	@Override
	public Long zRemRangeByScore(final byte[] key, final double min, final double max) {
		return execute((client)->client.sortedSetOperations().zRemRangeByScore(rawKey(key), min, max));
	}

	@Override
	public Long zRemRangeByRank(final String key, final long start, final long end) {
		return execute((client)->client.sortedSetOperations().zRemRangeByRank(rawKey(key), start, end));
	}

	@Override
	public Long zRemRangeByRank(final byte[] key, final long start, final long end) {
		return execute((client)->client.sortedSetOperations().zRemRangeByRank(rawKey(key), start, end));
	}

	@Override
	public List<String> zRevRange(final String key, final long start, final long end) {
		return execute((client)->client.sortedSetOperations().zRevRange(rawKey(key), start, end));
	}

	@Override
	public List<byte[]> zRevRange(final byte[] key, final long start, final long end) {
		return execute((client)->client.sortedSetOperations().zRevRange(rawKey(key), start, end));
	}

	@Override
	public List<Tuple> zRevRangeWithScores(final String key, final long start, final long end) {
		return execute((client)->client.sortedSetOperations().zRevRangeWithScores(rawKey(key), start, end));
	}

	@Override
	public List<Tuple> zRevRangeWithScores(final byte[] key, final long start, final long end) {
		return execute((client)->client.sortedSetOperations().zRevRangeWithScores(rawKey(key), start, end));
	}

	@Override
	public List<String> zRevRangeByLex(final String key, final double min, final double max) {
		return execute((client)->client.sortedSetOperations().zRevRangeByLex(rawKey(key), min, max));
	}

	@Override
	public List<byte[]> zRevRangeByLex(final byte[] key, final double min, final double max) {
		return execute((client)->client.sortedSetOperations().zRevRangeByLex(rawKey(key), min, max));
	}

	@Override
	public List<String> zRevRangeByLex(final String key, final double min, final double max, final long offset,
									   final int count) {
		return execute((client)->client.sortedSetOperations().zRevRangeByLex(rawKey(key), min, max, offset, count));
	}

	@Override
	public List<byte[]> zRevRangeByLex(final byte[] key, final double min, final double max, final long offset,
									   final int count) {
		return execute((client)->client.sortedSetOperations().zRevRangeByLex(rawKey(key), min, max, offset, count));
	}

	@Override
	public List<String> zRevRangeByScore(final String key, final double min, final double max) {
		return execute((client)->client.sortedSetOperations().zRevRangeByScore(rawKey(key), min, max));
	}

	@Override
	public List<byte[]> zRevRangeByScore(final byte[] key, final double min, final double max) {
		return execute((client)->client.sortedSetOperations().zRevRangeByScore(rawKey(key), min, max));
	}

	@Override
	public List<String> zRevRangeByScore(final String key, final double min, final double max, final long offset,
										 final int count) {
		return execute((client)->client.sortedSetOperations().zRevRangeByScore(rawKey(key), min, max, offset, count));
	}

	@Override
	public List<byte[]> zRevRangeByScore(final byte[] key, final double min, final double max, final long offset,
										 final int count) {
		return execute((client)->client.sortedSetOperations().zRevRangeByScore(rawKey(key), min, max, offset, count));
	}

	@Override
	public List<Tuple> zRevRangeByScoreWithScores(final String key, final double min, final double max) {
		return execute((client)->client.sortedSetOperations().zRevRangeByScoreWithScores(rawKey(key), min, max));
	}

	@Override
	public List<Tuple> zRevRangeByScoreWithScores(final byte[] key, final double min, final double max) {
		return execute((client)->client.sortedSetOperations().zRevRangeByScoreWithScores(rawKey(key), min, max));
	}

	@Override
	public List<Tuple> zRevRangeByScoreWithScores(final String key, final double min, final double max,
												  final long offset, final int count) {
		return execute((client)->client.sortedSetOperations()
				.zRevRangeByScoreWithScores(rawKey(key), min, max, offset, count));
	}

	@Override
	public List<Tuple> zRevRangeByScoreWithScores(final byte[] key, final double min, final double max,
												  final long offset, final int count) {
		return execute((client)->client.sortedSetOperations()
				.zRevRangeByScoreWithScores(rawKey(key), min, max, offset, count));
	}

	@Override
	public Long zRevRank(final String key, final String member) {
		return execute((client)->client.sortedSetOperations().zRevRank(rawKey(key), member));
	}

	@Override
	public Long zRevRank(final byte[] key, final byte[] member) {
		return execute((client)->client.sortedSetOperations().zRevRank(rawKey(key), member));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final long cursor) {
		return execute((client)->client.sortedSetOperations().zScan(rawKey(key), cursor));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final long cursor) {
		return execute((client)->client.sortedSetOperations().zScan(rawKey(key), cursor));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final String cursor) {
		return execute((client)->client.sortedSetOperations().zScan(rawKey(key), cursor));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor) {
		return execute((client)->client.sortedSetOperations().zScan(rawKey(key), cursor));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final long cursor, final String pattern) {
		return execute((client)->client.sortedSetOperations().zScan(rawKey(key), cursor, pattern));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final long cursor, final byte[] pattern) {
		return execute((client)->client.sortedSetOperations().zScan(rawKey(key), cursor, pattern));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final String cursor, final String pattern) {
		return execute((client)->client.sortedSetOperations().zScan(rawKey(key), cursor, pattern));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor, final byte[] pattern) {
		return execute((client)->client.sortedSetOperations().zScan(rawKey(key), cursor, pattern));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final long cursor, final int count) {
		return execute((client)->client.sortedSetOperations().zScan(rawKey(key), cursor, count));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final long cursor, final int count) {
		return execute((client)->client.sortedSetOperations().zScan(rawKey(key), cursor, count));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final String cursor, final int count) {
		return execute((client)->client.sortedSetOperations().zScan(rawKey(key), cursor, count));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor, final int count) {
		return execute((client)->client.sortedSetOperations().zScan(rawKey(key), cursor, count));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final long cursor, final String pattern, final int count) {
		return execute((client)->client.sortedSetOperations().zScan(rawKey(key), cursor, pattern, count));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final long cursor, final byte[] pattern, final int count) {
		return execute((client)->client.sortedSetOperations().zScan(rawKey(key), cursor, pattern, count));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final String cursor, final String pattern,
										 final int count) {
		return execute((client)->client.sortedSetOperations().zScan(rawKey(key), cursor, pattern, count));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor, final byte[] pattern,
										 final int count) {
		return execute((client)->client.sortedSetOperations().zScan(rawKey(key), cursor, pattern, count));
	}

	@Override
	public Double zScore(final String key, final String member) {
		return execute((client)->client.sortedSetOperations().zScore(rawKey(key), member));
	}

	@Override
	public Double zScore(final byte[] key, final byte[] member) {
		return execute((client)->client.sortedSetOperations().zScore(rawKey(key), member));
	}

	@Override
	public List<String> zUnion(final String... keys) {
		return execute((client)->client.sortedSetOperations().zUnion(rawKeys(keys)));
	}

	@Override
	public List<byte[]> zUnion(final byte[]... keys) {
		return execute((client)->client.sortedSetOperations().zUnion(rawKeys(keys)));
	}

	@Override
	public List<String> zUnion(final String[] keys, final Aggregate aggregate) {
		return execute((client)->client.sortedSetOperations().zUnion(rawKeys(keys), aggregate));
	}

	@Override
	public List<byte[]> zUnion(final byte[][] keys, final Aggregate aggregate) {
		return execute((client)->client.sortedSetOperations().zUnion(rawKeys(keys), aggregate));
	}

	@Override
	public List<String> zUnion(final String[] keys, final double... weights) {
		return execute((client)->client.sortedSetOperations().zUnion(rawKeys(keys), weights));
	}

	@Override
	public List<byte[]> zUnion(final byte[][] keys, final double... weights) {
		return execute((client)->client.sortedSetOperations().zUnion(rawKeys(keys), weights));
	}

	@Override
	public List<String> zUnion(final String[] keys, final Aggregate aggregate, final double... weights) {
		return execute((client)->client.sortedSetOperations().zUnion(rawKeys(keys), aggregate, weights));
	}

	@Override
	public List<byte[]> zUnion(final byte[][] keys, final Aggregate aggregate, final double... weights) {
		return execute((client)->client.sortedSetOperations().zUnion(rawKeys(keys), aggregate, weights));
	}

	@Override
	public List<Tuple> zUnionWithScores(final String... keys) {
		return execute((client)->client.sortedSetOperations().zUnionWithScores(rawKeys(keys)));
	}

	@Override
	public List<Tuple> zUnionWithScores(final byte[]... keys) {
		return execute((client)->client.sortedSetOperations().zUnionWithScores(rawKeys(keys)));
	}

	@Override
	public List<Tuple> zUnionWithScores(final String[] keys, final Aggregate aggregate) {
		return execute((client)->client.sortedSetOperations().zUnionWithScores(rawKeys(keys), aggregate));
	}

	@Override
	public List<Tuple> zUnionWithScores(final byte[][] keys, final Aggregate aggregate) {
		return execute((client)->client.sortedSetOperations().zUnionWithScores(rawKeys(keys), aggregate));
	}

	@Override
	public List<Tuple> zUnionWithScores(final String[] keys, final double... weights) {
		return execute((client)->client.sortedSetOperations().zUnionWithScores(rawKeys(keys), weights));
	}

	@Override
	public List<Tuple> zUnionWithScores(final byte[][] keys, final double... weights) {
		return execute((client)->client.sortedSetOperations().zUnionWithScores(rawKeys(keys), weights));
	}

	@Override
	public List<Tuple> zUnionWithScores(final String[] keys, final Aggregate aggregate, final double... weights) {
		return execute((client)->client.sortedSetOperations().zUnionWithScores(rawKeys(keys), aggregate, weights));
	}

	@Override
	public List<Tuple> zUnionWithScores(final byte[][] keys, final Aggregate aggregate, final double... weights) {
		return execute((client)->client.sortedSetOperations().zUnionWithScores(rawKeys(keys), aggregate, weights));
	}

	@Override
	public Long zUnionStore(final String destKey, final String... keys) {
		return execute((client)->client.sortedSetOperations().zUnionStore(rawKey(destKey), rawKeys(keys)));
	}

	@Override
	public Long zUnionStore(final byte[] destKey, final byte[]... keys) {
		return execute((client)->client.sortedSetOperations().zUnionStore(rawKey(destKey), rawKeys(keys)));
	}

	@Override
	public Long zUnionStore(final String destKey, final String[] keys, final Aggregate aggregate) {
		return execute((client)->client.sortedSetOperations().zUnionStore(rawKey(destKey), rawKeys(keys), aggregate));
	}

	@Override
	public Long zUnionStore(final byte[] destKey, final byte[][] keys, final Aggregate aggregate) {
		return execute((client)->client.sortedSetOperations().zUnionStore(rawKey(destKey), rawKeys(keys), aggregate));
	}

	@Override
	public Long zUnionStore(final String destKey, final String[] keys, final double... weights) {
		return execute((client)->client.sortedSetOperations().zUnionStore(rawKey(destKey), rawKeys(keys), weights));
	}

	@Override
	public Long zUnionStore(final byte[] destKey, final byte[][] keys, final double... weights) {
		return execute((client)->client.sortedSetOperations().zUnionStore(rawKey(destKey), rawKeys(keys), weights));
	}

	@Override
	public Long zUnionStore(final String destKey, final String[] keys, final Aggregate aggregate,
							final double... weights) {
		return execute(
				(client)->client.sortedSetOperations().zUnionStore(rawKey(destKey), rawKeys(keys), aggregate, weights));
	}

	@Override
	public Long zUnionStore(final byte[] destKey, final byte[][] keys, final Aggregate aggregate,
							final double... weights) {
		return execute(
				(client)->client.sortedSetOperations().zUnionStore(rawKey(destKey), rawKeys(keys), aggregate, weights));
	}

	@Override
	public Long xAck(final String key, final String groupName, final StreamEntryId... ids) {
		return execute((client)->client.streamOperations().xAck(rawKey(key), groupName, ids));
	}

	@Override
	public Long xAck(final byte[] key, final byte[] groupName, final StreamEntryId... ids) {
		return execute((client)->client.streamOperations().xAck(rawKey(key), groupName, ids));
	}

	@Override
	public StreamEntryId xAdd(final String key, final StreamEntryId id, final Map<String, String> hash) {
		return execute((client)->client.streamOperations().xAdd(rawKey(key), id, hash));
	}

	@Override
	public StreamEntryId xAdd(final byte[] key, final StreamEntryId id, Map<byte[], byte[]> hash) {
		return execute((client)->client.streamOperations().xAdd(rawKey(key), id, hash));
	}

	@Override
	public StreamEntryId xAdd(final String key, final StreamEntryId id, final Map<String, String> hash,
							  final XAddArgument xAddArgument) {
		return execute((client)->client.streamOperations().xAdd(rawKey(key), id, hash, xAddArgument));
	}

	@Override
	public StreamEntryId xAdd(final byte[] key, final StreamEntryId id, final Map<byte[], byte[]> hash,
							  final XAddArgument xAddArgument) {
		return execute((client)->client.streamOperations().xAdd(rawKey(key), id, hash, xAddArgument));
	}

	@Override
	public Map<StreamEntryId, List<StreamEntry>> xAutoClaim(final String key, final String groupName,
															final String consumerName, final int minIdleTime,
															final StreamEntryId start) {
		return execute((client)->client.streamOperations()
				.xAutoClaim(rawKey(key), groupName, consumerName, minIdleTime, start));
	}

	@Override
	public Map<StreamEntryId, List<StreamEntry>> xAutoClaim(final byte[] key, final byte[] groupName,
															final byte[] consumerName, final int minIdleTime,
															final StreamEntryId start) {
		return execute((client)->client.streamOperations()
				.xAutoClaim(rawKey(key), groupName, consumerName, minIdleTime, start));
	}

	@Override
	public Map<StreamEntryId, List<StreamEntry>> xAutoClaim(final String key, final String groupName,
															final String consumerName, final int minIdleTime,
															final StreamEntryId start, final int count) {
		return execute((client)->client.streamOperations()
				.xAutoClaim(rawKey(key), groupName, consumerName, minIdleTime, start, count));
	}

	@Override
	public Map<StreamEntryId, List<StreamEntry>> xAutoClaim(final byte[] key, final byte[] groupName,
															final byte[] consumerName, final int minIdleTime,
															final StreamEntryId start, final int count) {
		return execute((client)->client.streamOperations()
				.xAutoClaim(rawKey(key), groupName, consumerName, minIdleTime, start, count));
	}

	@Override
	public Map<StreamEntryId, List<StreamEntryId>> xAutoClaimJustId(final String key, final String groupName,
																	final String consumerName, final int minIdleTime,
																	final StreamEntryId start) {
		return execute((client)->client.streamOperations()
				.xAutoClaimJustId(rawKey(key), groupName, consumerName, minIdleTime, start));
	}

	@Override
	public Map<StreamEntryId, List<StreamEntryId>> xAutoClaimJustId(final byte[] key, final byte[] groupName,
																	final byte[] consumerName, final int minIdleTime,
																	final StreamEntryId start) {
		return execute((client)->client.streamOperations()
				.xAutoClaimJustId(rawKey(key), groupName, consumerName, minIdleTime, start));
	}

	@Override
	public Map<StreamEntryId, List<StreamEntryId>> xAutoClaimJustId(final String key, final String groupName,
																	final String consumerName, final int minIdleTime,
																	final StreamEntryId start, final int count) {
		return execute((client)->client.streamOperations()
				.xAutoClaimJustId(rawKey(key), groupName, consumerName, minIdleTime, start, count));
	}

	@Override
	public Map<StreamEntryId, List<StreamEntryId>> xAutoClaimJustId(final byte[] key, final byte[] groupName,
																	final byte[] consumerName, final int minIdleTime,
																	final StreamEntryId start, final int count) {
		return execute((client)->client.streamOperations()
				.xAutoClaimJustId(rawKey(key), groupName, consumerName, minIdleTime, start, count));
	}

	@Override
	public List<StreamEntry> xClaim(final String key, final String groupName, final String consumerName,
									final int minIdleTime, final StreamEntryId... ids) {
		return execute(
				(client)->client.streamOperations().xClaim(rawKey(key), groupName, consumerName, minIdleTime, ids));
	}

	@Override
	public List<StreamEntry> xClaim(final byte[] key, final byte[] groupName, final byte[] consumerName,
									final int minIdleTime, final StreamEntryId... ids) {
		return execute(
				(client)->client.streamOperations().xClaim(rawKey(key), groupName, consumerName, minIdleTime, ids));
	}

	@Override
	public List<StreamEntry> xClaim(final String key, final String groupName, final String consumerName,
									final int minIdleTime, final StreamEntryId[] ids,
									final XClaimArgument xClaimArgument) {
		return execute((client)->client.streamOperations()
				.xClaim(rawKey(key), groupName, consumerName, minIdleTime, ids, xClaimArgument));
	}

	@Override
	public List<StreamEntry> xClaim(final byte[] key, final byte[] groupName, final byte[] consumerName,
									final int minIdleTime, final StreamEntryId[] ids,
									final XClaimArgument xClaimArgument) {
		return execute((client)->client.streamOperations()
				.xClaim(rawKey(key), groupName, consumerName, minIdleTime, ids, xClaimArgument));
	}

	@Override
	public List<StreamEntryId> xClaimJustId(final String key, final String groupName, final String consumerName,
											final int minIdleTime, final StreamEntryId... ids) {
		return execute((client)->client.streamOperations()
				.xClaimJustId(rawKey(key), groupName, consumerName, minIdleTime, ids));
	}

	@Override
	public List<StreamEntryId> xClaimJustId(final byte[] key, final byte[] groupName, final byte[] consumerName,
											final int minIdleTime, final StreamEntryId... ids) {
		return execute((client)->client.streamOperations()
				.xClaimJustId(rawKey(key), groupName, consumerName, minIdleTime, ids));
	}

	@Override
	public List<StreamEntryId> xClaimJustId(final String key, final String groupName, final String consumerName,
											final int minIdleTime, final StreamEntryId[] ids,
											final XClaimArgument xClaimArgument) {
		return execute((client)->client.streamOperations()
				.xClaimJustId(rawKey(key), groupName, consumerName, minIdleTime, ids, xClaimArgument));
	}

	@Override
	public List<StreamEntryId> xClaimJustId(final byte[] key, final byte[] groupName, final byte[] consumerName,
											final int minIdleTime, final StreamEntryId[] ids,
											final XClaimArgument xClaimArgument) {
		return execute((client)->client.streamOperations()
				.xClaimJustId(rawKey(key), groupName, consumerName, minIdleTime, ids, xClaimArgument));
	}

	@Override
	public Long xDel(final String key, final StreamEntryId... ids) {
		return execute((client)->client.streamOperations().xDel(rawKey(key), ids));
	}

	@Override
	public Long xDel(final byte[] key, final StreamEntryId... ids) {
		return execute((client)->client.streamOperations().xDel(rawKey(key), ids));
	}

	@Override
	public Status xGroupCreate(final String key, final String groupName, final StreamEntryId id,
							   final boolean makeStream) {
		return execute((client)->client.streamOperations().xGroupCreate(rawKey(key), groupName, id, makeStream));
	}

	@Override
	public Status xGroupCreate(final byte[] key, final byte[] groupName, final StreamEntryId id,
							   final boolean makeStream) {
		return execute((client)->client.streamOperations().xGroupCreate(rawKey(key), groupName, id, makeStream));
	}

	@Override
	public Status xGroupCreateConsumer(final String key, final String groupName, final String consumerName) {
		return execute((client)->client.streamOperations().xGroupCreateConsumer(rawKey(key), groupName, consumerName));
	}

	@Override
	public Status xGroupCreateConsumer(final byte[] key, final byte[] groupName, final byte[] consumerName) {
		return execute((client)->client.streamOperations().xGroupCreateConsumer(rawKey(key), groupName, consumerName));
	}

	@Override
	public Long xGroupDelConsumer(final String key, final String groupName, final String consumerName) {
		return execute((client)->client.streamOperations().xGroupDelConsumer(rawKey(key), groupName, consumerName));
	}

	@Override
	public Long xGroupDelConsumer(final byte[] key, final byte[] groupName, final byte[] consumerName) {
		return execute((client)->client.streamOperations().xGroupDelConsumer(rawKey(key), groupName, consumerName));
	}

	@Override
	public Status xGroupDestroy(final String key, final String groupName) {
		return execute((client)->client.streamOperations().xGroupDestroy(rawKey(key), groupName));
	}

	@Override
	public Status xGroupDestroy(final byte[] key, final byte[] groupName) {
		return execute((client)->client.streamOperations().xGroupDestroy(rawKey(key), groupName));
	}

	@Override
	public Status xGroupSetId(final String key, final String groupName, final StreamEntryId id) {
		return execute((client)->client.streamOperations().xGroupSetId(rawKey(key), groupName, id));
	}

	@Override
	public Status xGroupSetId(final byte[] key, final byte[] groupName, final StreamEntryId id) {
		return execute((client)->client.streamOperations().xGroupSetId(rawKey(key), groupName, id));
	}

	@Override
	public List<StreamConsumer> xInfoConsumers(final String key, final String groupName) {
		return execute((client)->client.streamOperations().xInfoConsumers(rawKey(key), groupName));
	}

	@Override
	public List<StreamConsumer> xInfoConsumers(final byte[] key, final byte[] groupName) {
		return execute((client)->client.streamOperations().xInfoConsumers(rawKey(key), groupName));
	}

	@Override
	public List<StreamGroup> xInfoGroups(final String key) {
		return execute((client)->client.streamOperations().xInfoGroups(rawKey(key)));
	}

	@Override
	public List<StreamGroup> xInfoGroups(final byte[] key) {
		return execute((client)->client.streamOperations().xInfoGroups(rawKey(key)));
	}

	@Override
	public Stream xInfoStream(final String key) {
		return execute((client)->client.streamOperations().xInfoStream(rawKey(key)));
	}

	@Override
	public Stream xInfoStream(final byte[] key) {
		return execute((client)->client.streamOperations().xInfoStream(rawKey(key)));
	}

	@Override
	public StreamFull xInfoStream(final String key, final boolean full) {
		return execute((client)->client.streamOperations().xInfoStream(rawKey(key), full));
	}

	@Override
	public StreamFull xInfoStream(final byte[] key, final boolean full) {
		return execute((client)->client.streamOperations().xInfoStream(rawKey(key), full));
	}

	@Override
	public StreamFull xInfoStream(final String key, final boolean full, final int count) {
		return execute((client)->client.streamOperations().xInfoStream(rawKey(key), full, count));
	}

	@Override
	public StreamFull xInfoStream(final byte[] key, final boolean full, final int count) {
		return execute((client)->client.streamOperations().xInfoStream(rawKey(key), full, count));
	}

	@Override
	public Long xLen(final String key) {
		return execute((client)->client.streamOperations().xLen(rawKey(key)));
	}

	@Override
	public Long xLen(final byte[] key) {
		return execute((client)->client.streamOperations().xLen(rawKey(key)));
	}

	@Override
	public StreamPendingSummary xPending(final String key, final String groupName) {
		return execute((client)->client.streamOperations().xPending(rawKey(key), groupName));
	}

	@Override
	public StreamPendingSummary xPending(final byte[] key, final byte[] groupName) {
		return execute((client)->client.streamOperations().xPending(rawKey(key), groupName));
	}

	@Override
	public List<StreamPending> xPending(final String key, final String groupName, final long minIdleTime) {
		return execute((client)->client.streamOperations().xPending(rawKey(key), groupName, minIdleTime));
	}

	@Override
	public List<StreamPending> xPending(final byte[] key, final byte[] groupName, final long minIdleTime) {
		return execute((client)->client.streamOperations().xPending(rawKey(key), groupName, minIdleTime));
	}

	@Override
	public List<StreamPending> xPending(final String key, final String groupName, final StreamEntryId start,
										final StreamEntryId end, final int count) {
		return execute((client)->client.streamOperations().xPending(rawKey(key), groupName, start, end, count));
	}

	@Override
	public List<StreamPending> xPending(final byte[] key, final byte[] groupName, final StreamEntryId start,
										final StreamEntryId end, final int count) {
		return execute((client)->client.streamOperations().xPending(rawKey(key), groupName, start, end, count));
	}

	@Override
	public List<StreamPending> xPending(final String key, final String groupName, final String consumerName) {
		return execute((client)->client.streamOperations().xPending(rawKey(key), groupName, consumerName));
	}

	@Override
	public List<StreamPending> xPending(final byte[] key, final byte[] groupName, final byte[] consumerName) {
		return execute((client)->client.streamOperations().xPending(rawKey(key), groupName, consumerName));
	}

	@Override
	public List<StreamPending> xPending(final String key, final String groupName, final long minIdleTime,
										final StreamEntryId start, final StreamEntryId end, final int count) {
		return execute(
				(client)->client.streamOperations().xPending(rawKey(key), groupName, minIdleTime, start, end, count));
	}

	@Override
	public List<StreamPending> xPending(final byte[] key, final byte[] groupName, final long minIdleTime,
										final StreamEntryId start, final StreamEntryId end, final int count) {
		return execute(
				(client)->client.streamOperations().xPending(rawKey(key), groupName, minIdleTime, start, end, count));
	}

	@Override
	public List<StreamPending> xPending(final String key, final String groupName, final long minIdleTime,
										final String consumerName) {
		return execute((client)->client.streamOperations().xPending(rawKey(key), groupName, minIdleTime, consumerName));
	}

	@Override
	public List<StreamPending> xPending(final byte[] key, final byte[] groupName, final long minIdleTime,
										final byte[] consumerName) {
		return execute((client)->client.streamOperations().xPending(rawKey(key), groupName, minIdleTime, consumerName));
	}

	@Override
	public List<StreamPending> xPending(final String key, final String groupName, final StreamEntryId start,
										final StreamEntryId end, final int count, final String consumerName) {
		return execute(
				(client)->client.streamOperations().xPending(rawKey(key), groupName, start, end, count, consumerName));
	}

	@Override
	public List<StreamPending> xPending(final byte[] key, final byte[] groupName, final StreamEntryId start,
										final StreamEntryId end, final int count, final byte[] consumerName) {
		return execute(
				(client)->client.streamOperations().xPending(rawKey(key), groupName, start, end, count, consumerName));
	}

	@Override
	public List<StreamPending> xPending(final String key, final String groupName, final long minIdleTime,
										final StreamEntryId start, final StreamEntryId end, final int count,
										final String consumerName) {
		return execute((client)->client.streamOperations()
				.xPending(rawKey(key), groupName, minIdleTime, start, end, count, consumerName));
	}

	@Override
	public List<StreamPending> xPending(final byte[] key, final byte[] groupName, final long minIdleTime,
										final StreamEntryId start, final StreamEntryId end, final int count,
										final byte[] consumerName) {
		return execute((client)->client.streamOperations()
				.xPending(rawKey(key), groupName, minIdleTime, start, end, count, consumerName));
	}

	@Override
	public List<StreamEntry> xRange(final String key, final StreamEntryId start, final StreamEntryId end) {
		return execute((client)->client.streamOperations().xRange(rawKey(key), start, end));
	}

	@Override
	public List<StreamEntry> xRange(final byte[] key, final StreamEntryId start, final StreamEntryId end) {
		return execute((client)->client.streamOperations().xRange(rawKey(key), start, end));
	}

	@Override
	public List<StreamEntry> xRange(final String key, final StreamEntryId start, final StreamEntryId end,
									final int count) {
		return execute((client)->client.streamOperations().xRange(rawKey(key), start, end));
	}

	@Override
	public List<StreamEntry> xRange(final byte[] key, final StreamEntryId start, final StreamEntryId end,
									final int count) {
		return execute((client)->client.streamOperations().xRange(rawKey(key), start, end));
	}

	@Override
	public List<Map<String, List<StreamEntry>>> xRead(final Map<String, StreamEntryId> streams) {
		return execute((client)->client.streamOperations().xRead(streams));
	}

	@Override
	public List<Map<String, List<StreamEntry>>> xRead(final Map<String, StreamEntryId> streams,
													  final XReadArgument xReadArgument) {
		return execute((client)->client.streamOperations().xRead(streams, xReadArgument));
	}

	@Override
	public List<Map<String, List<StreamEntry>>> xReadGroup(final String groupName, final String consumerName,
														   final Map<String, StreamEntryId> streams) {
		return execute((client)->client.streamOperations().xReadGroup(groupName, consumerName, streams));
	}

	@Override
	public List<Map<byte[], List<StreamEntry>>> xReadGroup(final byte[] groupName, final byte[] consumerName,
														   final Map<byte[], StreamEntryId> streams) {
		return execute((client)->client.streamOperations().xReadGroup(groupName, consumerName, streams));
	}

	@Override
	public List<Map<String, List<StreamEntry>>> xReadGroup(final String groupName, final String consumerName,
														   final Map<String, StreamEntryId> streams,
														   final XReadGroupArgument xReadGroupArgument) {
		return execute(
				(client)->client.streamOperations().xReadGroup(groupName, consumerName, streams, xReadGroupArgument));
	}

	@Override
	public List<Map<byte[], List<StreamEntry>>> xReadGroup(final byte[] groupName, final byte[] consumerName,
														   final Map<byte[], StreamEntryId> streams,
														   final XReadGroupArgument xReadGroupArgument) {
		return execute(
				(client)->client.streamOperations().xReadGroup(groupName, consumerName, streams, xReadGroupArgument));
	}

	@Override
	public List<Map<String, List<StreamEntry>>> xReadGroup(final String groupName, final String consumerName,
														   final boolean isNoAck,
														   final Map<String, StreamEntryId> streams) {
		return execute((client)->client.streamOperations().xReadGroup(groupName, consumerName, isNoAck, streams));
	}

	@Override
	public List<Map<byte[], List<StreamEntry>>> xReadGroup(final byte[] groupName, final byte[] consumerName,
														   final boolean isNoAck,
														   final Map<byte[], StreamEntryId> streams) {
		return execute((client)->client.streamOperations().xReadGroup(groupName, consumerName, isNoAck, streams));
	}

	@Override
	public List<Map<String, List<StreamEntry>>> xReadGroup(final String groupName, final String consumerName,
														   final boolean isNoAck,
														   final Map<String, StreamEntryId> streams,
														   final XReadGroupArgument xReadGroupArgument) {
		return execute(
				(client)->client.streamOperations()
						.xReadGroup(groupName, consumerName, isNoAck, streams, xReadGroupArgument));
	}

	@Override
	public List<Map<byte[], List<StreamEntry>>> xReadGroup(final byte[] groupName, final byte[] consumerName,
														   final boolean isNoAck,
														   final Map<byte[], StreamEntryId> streams,
														   final XReadGroupArgument xReadGroupArgument) {
		return execute(
				(client)->client.streamOperations()
						.xReadGroup(groupName, consumerName, isNoAck, streams, xReadGroupArgument));
	}

	@Override
	public List<StreamEntry> xRevRange(final String key, final StreamEntryId end, final StreamEntryId start) {
		return execute((client)->client.streamOperations().xRevRange(rawKey(key), end, start));
	}

	@Override
	public List<StreamEntry> xRevRange(final byte[] key, final StreamEntryId end, final StreamEntryId start) {
		return execute((client)->client.streamOperations().xRevRange(rawKey(key), end, start));
	}

	@Override
	public List<StreamEntry> xRevRange(final String key, final StreamEntryId end, final StreamEntryId start,
									   final int count) {
		return execute((client)->client.streamOperations().xRevRange(rawKey(key), end, start, count));
	}

	@Override
	public List<StreamEntry> xRevRange(final byte[] key, final StreamEntryId end, final StreamEntryId start,
									   final int count) {
		return execute((client)->client.streamOperations().xRevRange(rawKey(key), end, start, count));
	}

	@Override
	public Long xTrim(final String key, final XTrimArgument xTrimArgument) {
		return execute((client)->client.streamOperations().xTrim(rawKey(key), xTrimArgument));
	}

	@Override
	public Long xTrim(final byte[] key, final XTrimArgument xTrimArgument) {
		return execute((client)->client.streamOperations().xTrim(rawKey(key), xTrimArgument));
	}

	@Override
	public Long xTrim(final String key, final XTrimArgument xTrimArgument, final long limit) {
		return execute((client)->client.streamOperations().xTrim(rawKey(key), xTrimArgument, limit));
	}

	@Override
	public Long xTrim(final byte[] key, final XTrimArgument xTrimArgument, final long limit) {
		return execute((client)->client.streamOperations().xTrim(rawKey(key), xTrimArgument, limit));
	}

	@Override
	public Long append(final String key, final String value) {
		return execute((client)->client.stringOperations().append(rawKey(key), value));
	}

	@Override
	public Long append(final byte[] key, final byte[] value) {
		return execute((client)->client.stringOperations().append(rawKey(key), value));
	}

	@Override
	public Long incr(final String key) {
		return execute((client)->client.stringOperations().incr(rawKey(key)));
	}

	@Override
	public Long incr(final byte[] key) {
		return execute((client)->client.stringOperations().incr(rawKey(key)));
	}

	@Override
	public Long incrBy(final String key, final long value) {
		return execute((client)->client.stringOperations().incrBy(rawKey(key), value));
	}

	@Override
	public Long incrBy(final byte[] key, final long value) {
		return execute((client)->client.stringOperations().incrBy(rawKey(key), value));
	}

	@Override
	public Double incrByFloat(final String key, final double value) {
		return execute((client)->client.stringOperations().incrByFloat(rawKey(key), value));
	}

	@Override
	public Double incrByFloat(final byte[] key, final double value) {
		return execute((client)->client.stringOperations().incrByFloat(rawKey(key), value));
	}

	@Override
	public Long decr(final String key) {
		return execute((client)->client.stringOperations().decr(rawKey(key)));
	}

	@Override
	public Long decr(final byte[] key) {
		return execute((client)->client.stringOperations().decr(rawKey(key)));
	}

	@Override
	public Long decrBy(final String key, final long value) {
		return execute((client)->client.stringOperations().decrBy(rawKey(key), value));
	}

	@Override
	public Long decrBy(final byte[] key, final long value) {
		return execute((client)->client.stringOperations().decrBy(rawKey(key), value));
	}

	@Override
	public String get(final String key) {
		return execute((client)->client.stringOperations().get(rawKey(key)));
	}

	@Override
	public byte[] get(final byte[] key) {
		return execute((client)->client.stringOperations().get(rawKey(key)));
	}

	@Override
	public String getEx(final String key, final GetExArgument getExArgument) {
		return execute((client)->client.stringOperations().getEx(rawKey(key), getExArgument));
	}

	@Override
	public byte[] getEx(final byte[] key, final GetExArgument getExArgument) {
		return execute((client)->client.stringOperations().getEx(rawKey(key), getExArgument));
	}

	@Override
	public String getSet(final String key, final String value) {
		return execute((client)->client.stringOperations().getSet(rawKey(key), value));
	}

	@Override
	public byte[] getSet(final byte[] key, final byte[] value) {
		return execute((client)->client.stringOperations().getSet(rawKey(key), value));
	}

	@Override
	public String getDel(final String key) {
		return execute((client)->client.stringOperations().getDel(rawKey(key)));
	}

	@Override
	public byte[] getDel(final byte[] key) {
		return execute((client)->client.stringOperations().getDel(rawKey(key)));
	}

	@Override
	public List<String> mGet(final String... keys) {
		return execute((client)->client.stringOperations().mGet(rawKeys(keys)));
	}

	@Override
	public List<byte[]> mGet(final byte[]... keys) {
		return execute((client)->client.stringOperations().mGet(rawKeys(keys)));
	}

	@Override
	public Status mSet(final Map<String, String> values) {
		final Map<String, String> newValues = Maps.map(values, this::rawKey, (value)->value,
				new LinkedHashMap<>(values.size()));
		return execute((client)->client.stringOperations().mSet(newValues));
	}

	@Override
	public Status mSetNx(final Map<String, String> values) {
		final Map<String, String> newValues = Maps.map(values, this::rawKey, (value)->value,
				new LinkedHashMap<>(values.size()));
		return execute((client)->client.stringOperations().mSetNx(newValues));
	}

	@Override
	public Status pSetEx(final String key, String value, int lifetime) {
		return execute((client)->client.stringOperations().pSetEx(rawKey(key), value, lifetime));
	}

	@Override
	public Status pSetEx(final byte[] key, final byte[] value, final int lifetime) {
		return execute((client)->client.stringOperations().pSetEx(rawKey(key), value, lifetime));
	}

	@Override
	public Status set(final String key, final String value) {
		return execute((client)->client.stringOperations().set(rawKey(key), value));
	}

	@Override
	public Status set(final byte[] key, final byte[] value) {
		return execute((client)->client.stringOperations().set(rawKey(key), value));
	}

	@Override
	public Status set(final String key, final String value, final SetArgument setArgument) {
		return execute((client)->client.stringOperations().set(rawKey(key), value, setArgument));
	}

	@Override
	public Status set(final byte[] key, final byte[] value, final SetArgument setArgument) {
		return execute((client)->client.stringOperations().set(rawKey(key), value, setArgument));
	}

	@Override
	public Status setEx(final String key, final String value, final int lifetime) {
		return execute((client)->client.stringOperations().setEx(rawKey(key), value, lifetime));
	}

	@Override
	public Status setEx(final byte[] key, final byte[] value, final int lifetime) {
		return execute((client)->client.stringOperations().setEx(rawKey(key), value, lifetime));
	}

	@Override
	public Status setNx(final String key, final String value) {
		return execute((client)->client.stringOperations().setNx(rawKey(key), value));
	}

	@Override
	public Status setNx(final byte[] key, final byte[] value) {
		return execute((client)->client.stringOperations().setNx(rawKey(key), value));
	}

	@Override
	public Long setRange(final String key, final long offset, final String value) {
		return execute((client)->client.stringOperations().setRange(rawKey(key), offset, value));
	}

	@Override
	public Long setRange(final byte[] key, final long offset, final byte[] value) {
		return execute((client)->client.stringOperations().setRange(rawKey(key), offset, value));
	}

	@Override
	public String getRange(final String key, final long start, final long end) {
		return execute((client)->client.stringOperations().getRange(rawKey(key), start, end));
	}

	@Override
	public byte[] getRange(final byte[] key, final long start, final long end) {
		return execute((client)->client.stringOperations().getRange(rawKey(key), start, end));
	}

	@Override
	public Long strlen(final String key) {
		return execute((client)->client.stringOperations().strlen(rawKey(key)));
	}

	@Override
	public Long strlen(final byte[] key) {
		return execute((client)->client.stringOperations().strlen(rawKey(key)));
	}

	@Override
	public String substr(final String key, final int start, final int end) {
		return execute((client)->client.stringOperations().substr(rawKey(key), start, end));
	}

	@Override
	public byte[] substr(final byte[] key, final int start, final int end) {
		return execute((client)->client.stringOperations().substr(rawKey(key), start, end));
	}

	@Override
	public Status multi() {
		return execute((client)->{
			try{
				final Transaction transaction = client.getConnection().multi();
				return transaction != null ? Status.SUCCESS : Status.FAILURE;
			}catch(Exception e){
				return Status.FAILURE;
			}
		});
	}

	@Override
	public List<Object> exec() {
		return execute((client)->client.getConnection().exec());
	}

	@Override
	public void discard() {
		execute((client)->{
			client.getConnection().discard();
			return null;
		});
	}

	@Override
	public Status watch(final String... keys) {
		return execute((client)->client.transactionOperations().watch(rawKeys(keys)));
	}

	@Override
	public Status watch(final byte[]... keys) {
		return execute((client)->client.transactionOperations().watch(rawKeys(keys)));
	}

	@Override
	public Status unwatch() {
		return execute((client)->client.transactionOperations().unwatch());
	}

}
