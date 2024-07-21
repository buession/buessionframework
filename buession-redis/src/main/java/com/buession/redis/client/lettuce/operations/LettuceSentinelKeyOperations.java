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

import com.buession.lang.Status;
import com.buession.redis.client.lettuce.LettuceSentinelClient;
import com.buession.redis.core.ExpireOption;
import com.buession.redis.core.MigrateOperation;
import com.buession.redis.core.ObjectEncoding;
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.Type;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.command.args.RestoreArgument;

import java.util.List;
import java.util.Set;

/**
 * Lettuce 哨兵模式 Key 命令操作
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public final class LettuceSentinelKeyOperations extends AbstractKeyOperations<LettuceSentinelClient> {

	public LettuceSentinelKeyOperations(final LettuceSentinelClient client) {
		super(client);
	}

	@Override
	public Long del(final String... keys) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys);
		return notCommand(client, ProtocolCommand.DEL, args);
	}

	@Override
	public Long del(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys);
		return notCommand(client, ProtocolCommand.DEL, args);
	}

	@Override
	public String dump(final String key) {
		final CommandArguments args = CommandArguments.create("key", key);
		return notCommand(client, ProtocolCommand.DUMP, args);
	}

	@Override
	public byte[] dump(final byte[] key) {
		final CommandArguments args = CommandArguments.create("key", key);
		return notCommand(client, ProtocolCommand.DUMP, args);
	}

	@Override
	public Boolean exists(final String key) {
		final CommandArguments args = CommandArguments.create("key", key);
		return notCommand(client, ProtocolCommand.EXISTS, args);
	}

	@Override
	public Boolean exists(final byte[] key) {
		final CommandArguments args = CommandArguments.create("key", key);
		return notCommand(client, ProtocolCommand.EXISTS, args);
	}

	@Override
	public Long exists(final String... keys) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys);
		return notCommand(client, ProtocolCommand.EXISTS, args);
	}

	@Override
	public Long exists(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys);
		return notCommand(client, ProtocolCommand.EXISTS, args);
	}

	@Override
	public Status expire(final String key, final int lifetime) {
		final CommandArguments args = CommandArguments.create("key", key).put("lifetime", lifetime);
		return notCommand(client, ProtocolCommand.EXPIRE, args);
	}

	@Override
	public Status expire(final byte[] key, final int lifetime) {
		final CommandArguments args = CommandArguments.create("key", key).put("lifetime", lifetime);
		return notCommand(client, ProtocolCommand.EXPIRE, args);
	}

	@Override
	public Status expire(final String key, final int lifetime, final ExpireOption expireOption) {
		final CommandArguments args = CommandArguments.create("key", key).put("lifetime", lifetime)
				.put("expireOption", expireOption);
		return notCommand(client, ProtocolCommand.EXPIRE, args);
	}

	@Override
	public Status expire(final byte[] key, final int lifetime, final ExpireOption expireOption) {
		final CommandArguments args = CommandArguments.create("key", key).put("lifetime", lifetime)
				.put("expireOption", expireOption);
		return notCommand(client, ProtocolCommand.EXPIRE, args);
	}

	@Override
	public Status expireAt(final String key, final long unixTimestamp) {
		final CommandArguments args = CommandArguments.create("key", key).put("unixTimestamp", unixTimestamp);
		return notCommand(client, ProtocolCommand.EXPIREAT, args);
	}

	@Override
	public Status expireAt(final byte[] key, final long unixTimestamp) {
		final CommandArguments args = CommandArguments.create("key", key).put("unixTimestamp", unixTimestamp);
		return notCommand(client, ProtocolCommand.EXPIREAT, args);
	}

	@Override
	public Status pExpire(final String key, final int lifetime) {
		final CommandArguments args = CommandArguments.create("key", key).put("lifetime", lifetime);
		return notCommand(client, ProtocolCommand.PEXPIRE, args);
	}

	@Override
	public Status pExpire(final byte[] key, final int lifetime) {
		final CommandArguments args = CommandArguments.create("key", key).put("lifetime", lifetime);
		return notCommand(client, ProtocolCommand.PEXPIRE, args);
	}

	@Override
	public Status pExpireAt(final String key, final long unixTimestamp) {
		final CommandArguments args = CommandArguments.create("key", key).put("unixTimestamp", unixTimestamp);
		return notCommand(client, ProtocolCommand.EXPIREAT, args);
	}

	@Override
	public Status pExpireAt(final byte[] key, final long unixTimestamp) {
		final CommandArguments args = CommandArguments.create("key", key).put("unixTimestamp", unixTimestamp);
		return notCommand(client, ProtocolCommand.EXPIREAT, args);
	}

	@Override
	public Status persist(final String key) {
		final CommandArguments args = CommandArguments.create("key", key);
		return notCommand(client, ProtocolCommand.PERSIST, args);
	}

	@Override
	public Status persist(final byte[] key) {
		final CommandArguments args = CommandArguments.create("key", key);
		return notCommand(client, ProtocolCommand.PERSIST, args);
	}

	@Override
	public Long ttl(final String key) {
		final CommandArguments args = CommandArguments.create("key", key);
		return notCommand(client, ProtocolCommand.TTL, args);
	}

	@Override
	public Long ttl(final byte[] key) {
		final CommandArguments args = CommandArguments.create("key", key);
		return notCommand(client, ProtocolCommand.TTL, args);
	}

	@Override
	public Long pTtl(final String key) {
		final CommandArguments args = CommandArguments.create("key", key);
		return notCommand(client, ProtocolCommand.PTTL, args);
	}

	@Override
	public Long pTtl(final byte[] key) {
		final CommandArguments args = CommandArguments.create("key", key);
		return notCommand(client, ProtocolCommand.PTTL, args);
	}

	@Override
	public Status copy(final String key, final String destKey) {
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey);
		return notCommand(client, ProtocolCommand.COPY, args);
	}

	@Override
	public Status copy(final byte[] key, final byte[] destKey) {
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey);
		return notCommand(client, ProtocolCommand.COPY, args);
	}

	@Override
	public Status copy(final String key, final String destKey, final int db) {
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey)
				.put("db", db);
		return notCommand(client, ProtocolCommand.COPY, args);
	}

	@Override
	public Status copy(final byte[] key, final byte[] destKey, final int db) {
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey)
				.put("db", db);
		return notCommand(client, ProtocolCommand.COPY, args);
	}

	@Override
	public Status copy(final String key, final String destKey, final boolean replace) {
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey)
				.put("replace", replace);
		return notCommand(client, ProtocolCommand.COPY, args);
	}

	@Override
	public Status copy(final byte[] key, final byte[] destKey, final boolean replace) {
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey)
				.put("replace", replace);
		return notCommand(client, ProtocolCommand.COPY, args);
	}

	@Override
	public Status copy(final String key, final String destKey, final int db, final boolean replace) {
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey)
				.put("db", db).put("replace", replace);
		return notCommand(client, ProtocolCommand.COPY, args);
	}

	@Override
	public Status copy(final byte[] key, final byte[] destKey, final int db, final boolean replace) {
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey)
				.put("db", db).put("replace", replace);
		return notCommand(client, ProtocolCommand.COPY, args);
	}

	@Override
	public Status move(final byte[] key, final int db) {
		final CommandArguments args = CommandArguments.create("key", key).put("db", db);
		return notCommand(client, ProtocolCommand.MOVE, args);
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final int timeout, final byte[]... keys) {
		final CommandArguments args = CommandArguments.create("host", host).put("port", port).put("db", db)
				.put("timeout", timeout).put("keys", (Object[]) keys);
		return notCommand(client, ProtocolCommand.MIGRATE, args);
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final int timeout,
						  final MigrateOperation operation, final byte[]... keys) {
		final CommandArguments args = CommandArguments.create("host", host).put("port", port).put("db", db)
				.put("timeout", timeout).put("operation", operation).put("keys", (Object[]) keys);
		return notCommand(client, ProtocolCommand.MIGRATE, args);
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final byte[] password, final int timeout,
						  final byte[]... keys) {
		final CommandArguments args = CommandArguments.create("host", host).put("port", port).put("db", db)
				.put("password", password).put("timeout", timeout).put("keys", (Object[]) keys);
		return notCommand(client, ProtocolCommand.MIGRATE, args);
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final byte[] password, final int timeout,
						  final MigrateOperation operation, final byte[]... keys) {
		final CommandArguments args = CommandArguments.create("host", host).put("port", port).put("db", db)
				.put("password", password).put("timeout", timeout).put("operation", operation)
				.put("keys", (Object[]) keys);
		return notCommand(client, ProtocolCommand.MIGRATE, args);
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final byte[] user, final byte[] password,
						  final int timeout, final byte[]... keys) {
		final CommandArguments args = CommandArguments.create("host", host).put("port", port).put("db", db)
				.put("user", user).put("password", password).put("timeout", timeout).put("keys", (Object[]) keys);
		return notCommand(client, ProtocolCommand.MIGRATE, args);
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final byte[] user, final byte[] password,
						  final int timeout, final MigrateOperation operation, final byte[]... keys) {
		final CommandArguments args = CommandArguments.create("host", host).put("port", port).put("db", db)
				.put("user", user).put("password", password).put("timeout", timeout).put("operation", operation)
				.put("keys", (Object[]) keys);
		return notCommand(client, ProtocolCommand.MIGRATE, args);
	}

	@Override
	public Set<String> keys(final String pattern) {
		final CommandArguments args = CommandArguments.create("pattern", pattern);
		return notCommand(client, ProtocolCommand.KEYS, args);
	}

	@Override
	public Set<byte[]> keys(final byte[] pattern) {
		final CommandArguments args = CommandArguments.create("pattern", pattern);
		return notCommand(client, ProtocolCommand.KEYS, args);
	}

	@Override
	public String randomKey() {
		return notCommand(client, ProtocolCommand.RANDOMKEY);
	}

	@Override
	public Status rename(final byte[] key, final byte[] newKey) {
		final CommandArguments args = CommandArguments.create("key", key).put("newKey", newKey);
		return notCommand(client, ProtocolCommand.RENAME, args);
	}

	@Override
	public Status renameNx(final byte[] key, final byte[] newKey) {
		final CommandArguments args = CommandArguments.create("key", key).put("newKey", newKey);
		return notCommand(client, ProtocolCommand.RENAMENX, args);
	}

	@Override
	public Status restore(final byte[] key, final byte[] serializedValue, final int ttl) {
		final CommandArguments args = CommandArguments.create("key", key).put("serializedValue", serializedValue)
				.put("ttl", ttl);
		return notCommand(client, ProtocolCommand.RESTORE, args);
	}

	@Override
	public Status restore(final byte[] key, final byte[] serializedValue, final int ttl,
						  final RestoreArgument argument) {
		final CommandArguments args = CommandArguments.create("key", key).put("serializedValue", serializedValue)
				.put("ttl", ttl).put("argument", argument);
		return notCommand(client, ProtocolCommand.RESTORE, args);
	}

	@Override
	public ScanResult<List<String>> scan(final String cursor) {
		final CommandArguments args = CommandArguments.create("cursor", cursor);
		return notCommand(client, ProtocolCommand.SCAN, args);
	}

	@Override
	public ScanResult<List<byte[]>> scan(final byte[] cursor) {
		final CommandArguments args = CommandArguments.create("cursor", cursor);
		return notCommand(client, ProtocolCommand.SCAN, args);
	}

	@Override
	public ScanResult<List<String>> scan(final String cursor, final String pattern) {
		final CommandArguments args = CommandArguments.create("cursor", cursor).put("pattern", pattern);
		return notCommand(client, ProtocolCommand.SCAN, args);
	}

	@Override
	public ScanResult<List<byte[]>> scan(final byte[] cursor, final byte[] pattern) {
		final CommandArguments args = CommandArguments.create("cursor", cursor).put("pattern", pattern);
		return notCommand(client, ProtocolCommand.SCAN, args);
	}

	@Override
	public ScanResult<List<String>> scan(final String cursor, final long count) {
		final CommandArguments args = CommandArguments.create("cursor", cursor).put("count", count);
		return notCommand(client, ProtocolCommand.SCAN, args);
	}

	@Override
	public ScanResult<List<byte[]>> scan(final byte[] cursor, final long count) {
		final CommandArguments args = CommandArguments.create("cursor", cursor).put("count", count);
		return notCommand(client, ProtocolCommand.SCAN, args);
	}

	@Override
	public ScanResult<List<String>> scan(final String cursor, final String pattern, final long count) {
		final CommandArguments args = CommandArguments.create("cursor", cursor).put("pattern", pattern)
				.put("count", count);
		return notCommand(client, ProtocolCommand.SCAN, args);
	}

	@Override
	public ScanResult<List<byte[]>> scan(final byte[] cursor, final byte[] pattern, final long count) {
		final CommandArguments args = CommandArguments.create("cursor", cursor).put("pattern", pattern)
				.put("count", count);
		return notCommand(client, ProtocolCommand.SCAN, args);
	}

	@Override
	public List<String> sort(final String key) {
		final CommandArguments args = CommandArguments.create("key", key);
		return notCommand(client, ProtocolCommand.SORT, args);
	}

	@Override
	public List<byte[]> sort(final byte[] key) {
		final CommandArguments args = CommandArguments.create("key", key);
		return notCommand(client, ProtocolCommand.SORT, args);
	}

	@Override
	public List<String> sort(final String key, final SortArgument sortArgument) {
		final CommandArguments args = CommandArguments.create("key", key).put("sortArgument", sortArgument);
		return notCommand(client, ProtocolCommand.SORT, args);
	}

	@Override
	public List<byte[]> sort(final byte[] key, final SortArgument sortArgument) {
		final CommandArguments args = CommandArguments.create("key", key).put("sortArgument", sortArgument);
		return notCommand(client, ProtocolCommand.SORT, args);
	}

	@Override
	public Long sort(final byte[] key, final byte[] destKey) {
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey);
		return notCommand(client, ProtocolCommand.SORT, args);
	}

	@Override
	public Long sort(final byte[] key, final byte[] destKey, final SortArgument sortArgument) {
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey)
				.put("sortArgument", sortArgument);
		return notCommand(client, ProtocolCommand.SORT, args);
	}

	@Override
	public Long touch(final String... keys) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys);
		return notCommand(client, ProtocolCommand.TOUCH, args);
	}

	@Override
	public Long touch(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys);
		return notCommand(client, ProtocolCommand.TOUCH, args);
	}

	@Override
	public Type type(final String key) {
		final CommandArguments args = CommandArguments.create("key", key);
		return notCommand(client, ProtocolCommand.TYPE, args);
	}

	@Override
	public Type type(final byte[] key) {
		final CommandArguments args = CommandArguments.create("key", key);
		return notCommand(client, ProtocolCommand.TYPE, args);
	}

	@Override
	public Long unlink(final String... keys) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys);
		return notCommand(client, ProtocolCommand.UNLINK, args);
	}

	@Override
	public Long unlink(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys);
		return notCommand(client, ProtocolCommand.UNLINK, args);
	}

	@Override
	public Long wait(final int replicas, final int timeout) {
		final CommandArguments args = CommandArguments.create("replicas", replicas).put("timeout", timeout);
		return notCommand(client, ProtocolCommand.WAIT, args);
	}

	@Override
	public ObjectEncoding objectEncoding(final String key) {
		final CommandArguments args = CommandArguments.create("key", key);
		return notCommand(client, ProtocolCommand.OBJECT_ENCODING, args);
	}

	@Override
	public ObjectEncoding objectEncoding(final byte[] key) {
		final CommandArguments args = CommandArguments.create("key", key);
		return notCommand(client, ProtocolCommand.OBJECT_ENCODING, args);
	}

	@Override
	public Long objectFreq(final String key) {
		final CommandArguments args = CommandArguments.create("key", key);
		return notCommand(client, ProtocolCommand.OBJECT_REFQ, args);
	}

	@Override
	public Long objectFreq(final byte[] key) {
		final CommandArguments args = CommandArguments.create("key", key);
		return notCommand(client, ProtocolCommand.OBJECT_REFQ, args);
	}

	@Override
	public Long objectIdleTime(final String key) {
		final CommandArguments args = CommandArguments.create("key", key);
		return notCommand(client, ProtocolCommand.OBJECT_IDLETIME, args);
	}

	@Override
	public Long objectIdleTime(final byte[] key) {
		final CommandArguments args = CommandArguments.create("key", key);
		return notCommand(client, ProtocolCommand.OBJECT_IDLETIME, args);
	}

	@Override
	public Long objectRefcount(final String key) {
		final CommandArguments args = CommandArguments.create("key", key);
		return notCommand(client, ProtocolCommand.OBJECT_REFCOUNT, args);
	}

	@Override
	public Long objectRefcount(final byte[] key) {
		final CommandArguments args = CommandArguments.create("key", key);
		return notCommand(client, ProtocolCommand.OBJECT_REFCOUNT, args);
	}

}
