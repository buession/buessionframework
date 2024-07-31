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

import com.buession.lang.KeyValue;
import com.buession.lang.Status;
import com.buession.redis.client.lettuce.LettuceSentinelClient;
import com.buession.redis.core.ExpireOption;
import com.buession.redis.core.Keyword;
import com.buession.redis.core.ObjectEncoding;
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.Type;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.Command;
import com.buession.redis.core.command.SubCommand;
import com.buession.redis.core.command.args.MigrateArgument;
import com.buession.redis.core.command.args.RestoreArgument;
import com.buession.redis.core.command.args.SortArgument;

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
	public Status copy(final String key, final String destKey) {
		final CommandArguments args = CommandArguments.create(key).add(destKey);
		return notCommand(client, Command.COPY, args);
	}

	@Override
	public Status copy(final byte[] key, final byte[] destKey) {
		final CommandArguments args = CommandArguments.create(key).add(destKey);
		return notCommand(client, Command.COPY, args);
	}

	@Override
	public Status copy(final String key, final String destKey, final int db) {
		final CommandArguments args = CommandArguments.create(key).add(destKey).add(db);
		return notCommand(client, Command.COPY, args);
	}

	@Override
	public Status copy(final byte[] key, final byte[] destKey, final int db) {
		final CommandArguments args = CommandArguments.create(key).add(destKey).add(db);
		return notCommand(client, Command.COPY, args);
	}

	@Override
	public Status copy(final String key, final String destKey, final boolean replace) {
		final CommandArguments args = CommandArguments.create(key).add(destKey);

		if(replace){
			args.add(Keyword.Common.REPLACE);
		}

		return notCommand(client, Command.COPY, args);
	}

	@Override
	public Status copy(final byte[] key, final byte[] destKey, final boolean replace) {
		final CommandArguments args = CommandArguments.create(key).add(destKey);

		if(replace){
			args.add(Keyword.Common.REPLACE);
		}

		return notCommand(client, Command.COPY, args);
	}

	@Override
	public Status copy(final String key, final String destKey, final int db, final boolean replace) {
		final CommandArguments args = CommandArguments.create(key).add(destKey).add(db);

		if(replace){
			args.add(Keyword.Common.REPLACE);
		}

		return notCommand(client, Command.COPY, args);
	}

	@Override
	public Status copy(final byte[] key, final byte[] destKey, final int db, final boolean replace) {
		final CommandArguments args = CommandArguments.create(key).add(destKey).add(db);

		if(replace){
			args.add(Keyword.Common.REPLACE);
		}

		return notCommand(client, Command.COPY, args);
	}

	@Override
	public Long del(final String... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return notCommand(client, Command.DEL, args);
	}

	@Override
	public Long del(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return notCommand(client, Command.DEL, args);
	}

	@Override
	public String dump(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return notCommand(client, Command.DUMP, args);
	}

	@Override
	public byte[] dump(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return notCommand(client, Command.DUMP, args);
	}

	@Override
	public Boolean exists(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return notCommand(client, Command.EXISTS, args);
	}

	@Override
	public Boolean exists(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return notCommand(client, Command.EXISTS, args);
	}

	@Override
	public Long exists(final String... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return notCommand(client, Command.EXISTS, args);
	}

	@Override
	public Long exists(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return notCommand(client, Command.EXISTS, args);
	}

	@Override
	public Status expire(final String key, final int lifetime) {
		final CommandArguments args = CommandArguments.create(key).add(lifetime);
		return notCommand(client, Command.EXPIRE, args);
	}

	@Override
	public Status expire(final byte[] key, final int lifetime) {
		final CommandArguments args = CommandArguments.create(key).add(lifetime);
		return notCommand(client, Command.EXPIRE, args);
	}

	@Override
	public Status expire(final String key, final int lifetime, final ExpireOption expireOption) {
		final CommandArguments args = CommandArguments.create(key).add(lifetime).add(expireOption);
		return notCommand(client, Command.EXPIRE, args);
	}

	@Override
	public Status expire(final byte[] key, final int lifetime, final ExpireOption expireOption) {
		final CommandArguments args = CommandArguments.create(key).add(lifetime).add(expireOption);
		return notCommand(client, Command.EXPIRE, args);
	}

	@Override
	public Status expireAt(final String key, final long unixTimestamp) {
		final CommandArguments args = CommandArguments.create(key).add(unixTimestamp);
		return notCommand(client, Command.EXPIREAT, args);
	}

	@Override
	public Status expireAt(final byte[] key, final long unixTimestamp) {
		final CommandArguments args = CommandArguments.create(key).add(unixTimestamp);
		return notCommand(client, Command.EXPIREAT, args);
	}

	@Override
	public Status expireAt(final String key, final long unixTimestamp, final ExpireOption expireOption) {
		final CommandArguments args = CommandArguments.create(key).add(unixTimestamp).add(expireOption);
		return notCommand(client, Command.EXPIREAT, args);
	}

	@Override
	public Status expireAt(final byte[] key, final long unixTimestamp, final ExpireOption expireOption) {
		final CommandArguments args = CommandArguments.create(key).add(unixTimestamp).add(expireOption);
		return notCommand(client, Command.EXPIREAT, args);
	}

	@Override
	public Long expireTime(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return notCommand(client, Command.EXPIRETIME, args);
	}

	@Override
	public Long expireTime(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return notCommand(client, Command.EXPIRETIME, args);
	}

	@Override
	public Set<String> keys(final String pattern) {
		final CommandArguments args = CommandArguments.create(pattern);
		return notCommand(client, Command.KEYS, args);
	}

	@Override
	public Set<byte[]> keys(final byte[] pattern) {
		final CommandArguments args = CommandArguments.create(pattern);
		return notCommand(client, Command.KEYS, args);
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final int timeout, final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(host).add(port).add(db).add(timeout).add(keys);
		return notCommand(client, Command.MIGRATE, args);
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final int timeout,
						  final MigrateArgument migrateArgument, final String... keys) {
		final CommandArguments args = CommandArguments.create(host).add(port).add(db).add(timeout).add(migrateArgument)
				.add(keys);
		return notCommand(client, Command.MIGRATE, args);
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final int timeout,
						  final MigrateArgument migrateArgument, final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(host).add(port).add(db).add(timeout).add(migrateArgument)
				.add(keys);
		return notCommand(client, Command.MIGRATE, args);
	}

	@Override
	public Status move(final byte[] key, final int db) {
		final CommandArguments args = CommandArguments.create(key).add(db);
		return notCommand(client, Command.MOVE, args);
	}

	@Override
	public ObjectEncoding objectEncoding(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return notCommand(client, Command.OBJECT, SubCommand.OBJECT_ENCODING, args);
	}

	@Override
	public ObjectEncoding objectEncoding(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return notCommand(client, Command.OBJECT, SubCommand.OBJECT_ENCODING, args);
	}

	@Override
	public Long objectFreq(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return notCommand(client, Command.OBJECT, SubCommand.OBJECT_REFQ, args);
	}

	@Override
	public Long objectFreq(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return notCommand(client, Command.OBJECT, SubCommand.OBJECT_REFQ, args);
	}

	@Override
	public Long objectIdleTime(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return notCommand(client, Command.OBJECT, SubCommand.OBJECT_IDLETIME, args);
	}

	@Override
	public Long objectIdleTime(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return notCommand(client, Command.OBJECT, SubCommand.OBJECT_IDLETIME, args);
	}

	@Override
	public Long objectRefcount(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return notCommand(client, Command.OBJECT, SubCommand.OBJECT_REFCOUNT, args);
	}

	@Override
	public Long objectRefcount(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return notCommand(client, Command.OBJECT, SubCommand.OBJECT_REFCOUNT, args);
	}

	@Override
	public Status persist(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return notCommand(client, Command.PERSIST, args);
	}

	@Override
	public Status persist(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return notCommand(client, Command.PERSIST, args);
	}

	@Override
	public Status pExpire(final String key, final int lifetime) {
		final CommandArguments args = CommandArguments.create(key).add(lifetime);
		return notCommand(client, Command.PEXPIRE, args);
	}

	@Override
	public Status pExpire(final byte[] key, final int lifetime, final ExpireOption expireOption) {
		final CommandArguments args = CommandArguments.create(key).add(lifetime).add(expireOption);
		return notCommand(client, Command.PEXPIRE, args);
	}

	@Override
	public Status pExpire(final String key, final int lifetime, final ExpireOption expireOption) {
		final CommandArguments args = CommandArguments.create(key).add(lifetime).add(expireOption);
		return notCommand(client, Command.PEXPIRE, args);
	}

	@Override
	public Status pExpire(final byte[] key, final int lifetime) {
		final CommandArguments args = CommandArguments.create(key).add(lifetime);
		return notCommand(client, Command.PEXPIRE, args);
	}

	@Override
	public Status pExpireAt(final String key, final long unixTimestamp) {
		final CommandArguments args = CommandArguments.create(key).add(unixTimestamp);
		return notCommand(client, Command.PEXPIREAT, args);
	}

	@Override
	public Status pExpireAt(final byte[] key, final long unixTimestamp) {
		final CommandArguments args = CommandArguments.create(key).add(unixTimestamp);
		return notCommand(client, Command.PEXPIREAT, args);
	}

	@Override
	public Status pExpireAt(final String key, final long unixTimestamp, final ExpireOption expireOption) {
		final CommandArguments args = CommandArguments.create(key).add(unixTimestamp).add(expireOption);
		return notCommand(client, Command.PEXPIREAT, args);
	}

	@Override
	public Status pExpireAt(final byte[] key, final long unixTimestamp, final ExpireOption expireOption) {
		final CommandArguments args = CommandArguments.create(key).add(unixTimestamp).add(expireOption);
		return notCommand(client, Command.PEXPIREAT, args);
	}

	@Override
	public Long pExpireTime(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return notCommand(client, Command.PEXPIRETIME, args);
	}

	@Override
	public Long pExpireTime(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return notCommand(client, Command.PEXPIRETIME, args);
	}

	@Override
	public Long pTtl(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return notCommand(client, Command.PTTL, args);
	}

	@Override
	public Long pTtl(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return notCommand(client, Command.PTTL, args);
	}

	@Override
	public String randomKey() {
		return notCommand(client, Command.RANDOMKEY);
	}

	@Override
	public Status rename(final String key, final String newKey) {
		final CommandArguments args = CommandArguments.create(key).add(newKey);
		return notCommand(client, Command.RENAME, args);
	}

	@Override
	public Status rename(final byte[] key, final byte[] newKey) {
		final CommandArguments args = CommandArguments.create(key).add(newKey);
		return notCommand(client, Command.RENAME, args);
	}

	@Override
	public Status renameNx(final String key, final String newKey) {
		final CommandArguments args = CommandArguments.create(key).add(newKey);
		return notCommand(client, Command.RENAMENX, args);
	}

	@Override
	public Status renameNx(final byte[] key, final byte[] newKey) {
		final CommandArguments args = CommandArguments.create(key).add(newKey);
		return notCommand(client, Command.RENAMENX, args);
	}

	@Override
	public Status restore(final String key, final byte[] serializedValue, final int ttl) {
		final CommandArguments args = CommandArguments.create(key).add(serializedValue).add(ttl);
		return notCommand(client, Command.RESTORE, args);
	}

	@Override
	public Status restore(final byte[] key, final byte[] serializedValue, final int ttl) {
		final CommandArguments args = CommandArguments.create(key).add(serializedValue).add(ttl);
		return notCommand(client, Command.RESTORE, args);
	}

	@Override
	public Status restore(final String key, final byte[] serializedValue, final int ttl,
						  final RestoreArgument restoreArgument) {
		final CommandArguments args = CommandArguments.create(key).add(serializedValue).add(ttl).add(restoreArgument);
		return notCommand(client, Command.RESTORE, args);
	}

	@Override
	public Status restore(final byte[] key, final byte[] serializedValue, final int ttl,
						  final RestoreArgument restoreArgument) {
		final CommandArguments args = CommandArguments.create(key).add(serializedValue).add(ttl).add(restoreArgument);
		return notCommand(client, Command.RESTORE, args);
	}

	@Override
	public ScanResult<List<String>> scan(final String cursor) {
		final CommandArguments args = CommandArguments.create(cursor);
		return notCommand(client, Command.SCAN, args);
	}

	@Override
	public ScanResult<List<byte[]>> scan(final byte[] cursor) {
		final CommandArguments args = CommandArguments.create(cursor);
		return notCommand(client, Command.SCAN, args);
	}

	@Override
	public ScanResult<List<String>> scan(final String cursor, final String pattern) {
		final CommandArguments args = CommandArguments.create(cursor).add(pattern);
		return notCommand(client, Command.SCAN, args);
	}

	@Override
	public ScanResult<List<byte[]>> scan(final byte[] cursor, final byte[] pattern) {
		final CommandArguments args = CommandArguments.create(cursor).add(pattern);
		return notCommand(client, Command.SCAN, args);
	}

	@Override
	public ScanResult<List<String>> scan(final String cursor, final int count) {
		final CommandArguments args = CommandArguments.create(cursor).add(count);
		return notCommand(client, Command.SCAN, args);
	}

	@Override
	public ScanResult<List<byte[]>> scan(final byte[] cursor, final int count) {
		final CommandArguments args = CommandArguments.create(cursor).add(count);
		return notCommand(client, Command.SCAN, args);
	}

	@Override
	public ScanResult<List<String>> scan(final String cursor, final String pattern, final int count) {
		final CommandArguments args = CommandArguments.create(cursor).add(pattern).add(count);
		return notCommand(client, Command.SCAN, args);
	}

	@Override
	public ScanResult<List<byte[]>> scan(final byte[] cursor, final byte[] pattern, final int count) {
		final CommandArguments args = CommandArguments.create(cursor).add(pattern).add(count);
		return notCommand(client, Command.SCAN, args);
	}

	@Override
	public List<String> sort(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return notCommand(client, Command.SORT, args);
	}

	@Override
	public List<byte[]> sort(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return notCommand(client, Command.SORT, args);
	}

	@Override
	public List<String> sort(final String key, final SortArgument sortArgument) {
		final CommandArguments args = CommandArguments.create(key).add(sortArgument);
		return notCommand(client, Command.SORT, args);
	}

	@Override
	public List<byte[]> sort(final byte[] key, final SortArgument sortArgument) {
		final CommandArguments args = CommandArguments.create(key).add(sortArgument);
		return notCommand(client, Command.SORT, args);
	}

	@Override
	public Long sort(final String key, final String destKey) {
		final CommandArguments args = CommandArguments.create(key).add(destKey);
		return notCommand(client, Command.SORT, args);
	}

	@Override
	public Long sort(final byte[] key, final byte[] destKey) {
		final CommandArguments args = CommandArguments.create(key).add(destKey);
		return notCommand(client, Command.SORT, args);
	}

	@Override
	public Long sort(final String key, final String destKey, final SortArgument sortArgument) {
		final CommandArguments args = CommandArguments.create(key).add(destKey).add(sortArgument);
		return notCommand(client, Command.SORT, args);
	}

	@Override
	public Long sort(final byte[] key, final byte[] destKey, final SortArgument sortArgument) {
		final CommandArguments args = CommandArguments.create(key).add(destKey).add(sortArgument);
		return notCommand(client, Command.SORT, args);
	}

	@Override
	public List<String> sortRo(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return notCommand(client, Command.SORT_RO, args);
	}

	@Override
	public List<byte[]> sortRo(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return notCommand(client, Command.SORT_RO, args);
	}

	@Override
	public List<String> sortRo(final String key, final SortArgument sortArgument) {
		final CommandArguments args = CommandArguments.create(key).add(sortArgument);
		return notCommand(client, Command.SORT_RO, args);
	}

	@Override
	public List<byte[]> sortRo(final byte[] key, final SortArgument sortArgument) {
		final CommandArguments args = CommandArguments.create(key).add(sortArgument);
		return notCommand(client, Command.SORT_RO, args);
	}

	@Override
	public Long touch(final String... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return notCommand(client, Command.TOUCH, args);
	}

	@Override
	public Long touch(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return notCommand(client, Command.TOUCH, args);
	}

	@Override
	public Long ttl(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return notCommand(client, Command.TTL, args);
	}

	@Override
	public Long ttl(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return notCommand(client, Command.TTL, args);
	}

	@Override
	public Type type(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return notCommand(client, Command.TYPE, args);
	}

	@Override
	public Type type(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return notCommand(client, Command.TYPE, args);
	}

	@Override
	public Long unlink(final String... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return notCommand(client, Command.UNLINK, args);
	}

	@Override
	public Long unlink(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return notCommand(client, Command.UNLINK, args);
	}

	@Override
	public Long wait(final int replicas, final int timeout) {
		final CommandArguments args = CommandArguments.create(replicas).add(timeout);
		return notCommand(client, Command.WAIT, args);
	}

	@Override
	public KeyValue<Long, Long> waitOf(final int locals, final int replicas, final int timeout) {
		final CommandArguments args = CommandArguments.create(locals).add(replicas).add(timeout);
		return notCommand(client, Command.WAITOF, args);
	}

}
