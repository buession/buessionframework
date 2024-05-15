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
package com.buession.redis.client.lettuce.operations;

import com.buession.core.utils.NumberUtils;
import com.buession.lang.Status;
import com.buession.redis.client.lettuce.LettuceRedisClient;
import com.buession.redis.client.operations.KeyOperations;
import com.buession.redis.core.ExpireOption;
import com.buession.redis.core.MigrateOperation;
import com.buession.redis.core.ObjectEncoding;
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.Type;
import com.buession.redis.utils.SafeEncoder;

import java.util.List;

/**
 * Lettuce Key 命令操作抽象类
 *
 * @param <C>
 * 		Redis Client {@link LettuceRedisClient}
 *
 * @author Yong.Teng
 * @since 2.4.0
 */
public abstract class AbstractKeyOperations<C extends LettuceRedisClient> extends AbstractLettuceRedisOperations<C>
		implements KeyOperations {

	public AbstractKeyOperations(final C client) {
		super(client);
	}

	@Override
	public Long del(final String... keys) {
		return del(SafeEncoder.encode(keys));
	}

	@Override
	public String dump(final String key) {
		return SafeEncoder.encode(dump(SafeEncoder.encode(key)));
	}

	@Override
	public Boolean exists(final String key) {
		return exists(SafeEncoder.encode(key));
	}

	@Override
	public Long exists(final String... keys) {
		return exists(SafeEncoder.encode(keys));
	}

	@Override
	public Status expire(final String key, final int lifetime) {
		return expire(SafeEncoder.encode(key), lifetime);
	}

	@Override
	public Status expire(final String key, final int lifetime, final ExpireOption expireOption) {
		return expire(SafeEncoder.encode(key), lifetime, expireOption);
	}

	@Override
	public Status expireAt(final String key, final long unixTimestamp) {
		return expireAt(SafeEncoder.encode(key), unixTimestamp);
	}

	@Override
	public Status pExpire(final String key, final int lifetime) {
		return pExpire(SafeEncoder.encode(key), lifetime);
	}

	@Override
	public Status pExpireAt(final String key, final long unixTimestamp) {
		return pExpireAt(SafeEncoder.encode(key), unixTimestamp);
	}

	@Override
	public Status persist(final String key) {
		return persist(SafeEncoder.encode(key));
	}

	@Override
	public Long ttl(final String key) {
		return ttl(SafeEncoder.encode(key));
	}

	@Override
	public Long pTtl(final String key) {
		return pTtl(SafeEncoder.encode(key));
	}

	@Override
	public Status move(final String key, final int db) {
		return move(SafeEncoder.encode(key), db);
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final int timeout, final String... keys) {
		return migrate(host, port, db, timeout, SafeEncoder.encode(keys));
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final int timeout,
						  final MigrateOperation operation, final String... keys) {
		return migrate(host, port, db, timeout, operation, SafeEncoder.encode(keys));
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final String password, final int timeout,
						  final String... keys) {
		return migrate(host, port, db, SafeEncoder.encode(password), timeout, SafeEncoder.encode(keys));
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final String password, final int timeout,
						  final MigrateOperation operation, final String... keys) {
		return migrate(host, port, db, SafeEncoder.encode(password), timeout, operation, SafeEncoder.encode(keys));
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final String user, final String password,
						  final int timeout, final String... keys) {
		return migrate(host, port, db, SafeEncoder.encode(user), SafeEncoder.encode(password), timeout,
				SafeEncoder.encode(keys));
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final String user, final String password,
						  final int timeout, final MigrateOperation operation, final String... keys) {
		return migrate(host, port, db, SafeEncoder.encode(user), SafeEncoder.encode(password), timeout, operation,
				SafeEncoder.encode(keys));
	}

	@Override
	public Status rename(final String key, final String newKey) {
		return rename(SafeEncoder.encode(key), SafeEncoder.encode(newKey));
	}

	@Override
	public Status renameNx(final String key, final String newKey) {
		return renameNx(SafeEncoder.encode(key), SafeEncoder.encode(newKey));
	}

	@Override
	public Status restore(final String key, final byte[] serializedValue, final int ttl) {
		return restore(SafeEncoder.encode(key), serializedValue, ttl);
	}

	@Override
	public Status restore(final String key, final byte[] serializedValue, final int ttl,
						  final RestoreArgument argument) {
		return restore(SafeEncoder.encode(key), serializedValue, ttl, argument);
	}

	@Override
	public ScanResult<List<String>> scan(final long cursor) {
		return scan(Long.toString(cursor));
	}

	@Override
	public ScanResult<List<String>> scan(final long cursor, final String pattern) {
		return scan(Long.toString(cursor), pattern);
	}

	@Override
	public ScanResult<List<byte[]>> scan(final long cursor, final byte[] pattern) {
		return scan(NumberUtils.long2bytes(cursor), pattern);
	}

	@Override
	public ScanResult<List<String>> scan(final long cursor, final long count) {
		return scan(Long.toString(cursor), count);
	}

	@Override
	public ScanResult<List<String>> scan(final long cursor, final String pattern, final long count) {
		return scan(Long.toString(cursor), pattern, count);
	}

	@Override
	public ScanResult<List<byte[]>> scan(final long cursor, final byte[] pattern, final long count) {
		return scan(NumberUtils.long2bytes(cursor), pattern, count);
	}

	@Override
	public Long sort(final String key, final String destKey) {
		return sort(SafeEncoder.encode(key), SafeEncoder.encode(destKey));
	}

	@Override
	public Long sort(final String key, final String destKey, final SortArgument sortArgument) {
		return sort(SafeEncoder.encode(key), SafeEncoder.encode(destKey), sortArgument);
	}

	@Override
	public Long touch(final String... keys) {
		return touch(SafeEncoder.encode(keys));
	}

	@Override
	public Type type(final String key) {
		return type(SafeEncoder.encode(key));
	}

	@Override
	public Long unlink(final String... keys) {
		return unlink(SafeEncoder.encode(keys));
	}

	@Override
	public ObjectEncoding objectEncoding(final String key) {
		return objectEncoding(SafeEncoder.encode(key));
	}

	@Override
	public Long objectFreq(final String key) {
		return objectFreq(SafeEncoder.encode(key));
	}

	@Override
	public Long objectIdleTime(final String key) {
		return objectIdleTime(SafeEncoder.encode(key));
	}

	@Override
	public Long objectRefcount(final String key) {
		return objectRefcount(SafeEncoder.encode(key));
	}

}
