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
 * | Copyright @ 2013-2026 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.client.lettuce.command;

import com.buession.core.converter.BooleanStatusConverter;
import com.buession.core.converter.ListSetConverter;
import com.buession.lang.Status;
import com.buession.redis.client.lettuce.LettuceRedisClient;
import com.buession.redis.core.command.args.ExpireOption;
import com.buession.redis.core.Keyword;
import com.buession.redis.core.ObjectEncoding;
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.Type;
import com.buession.redis.core.command.RedisCommand;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.KeyCommands;
import com.buession.redis.core.command.RedisSubCommand;
import com.buession.redis.core.command.args.key.MigrateArgument;
import com.buession.redis.core.command.args.RestoreArgument;
import com.buession.redis.core.command.args.key.SortArgument;
import com.buession.redis.core.internal.convert.BinaryListStringListConverter;
import com.buession.redis.core.internal.lettuce.args.LettuceExpireArgs;
import com.buession.redis.core.internal.lettuce.args.LettuceRestoreArgs;
import com.buession.redis.core.internal.convert.lettuce.response.ScanCursorConverter;
import com.buession.redis.core.internal.convert.response.ObjectEncodingConverter;
import com.buession.redis.core.internal.convert.response.OkStatusConverter;
import com.buession.redis.core.internal.convert.response.OneBooleanConverter;
import com.buession.redis.core.internal.convert.response.TypeConverter;
import com.buession.redis.core.internal.lettuce.args.LettuceMigrateArgs;
import com.buession.redis.core.internal.lettuce.args.LettuceScanArgs;
import com.buession.redis.core.internal.lettuce.args.LettuceScanCursor;
import com.buession.redis.core.internal.lettuce.args.LettuceSortArgs;
import com.buession.redis.utils.SafeEncoder;
import io.lettuce.core.CopyArgs;
import io.lettuce.core.MigrateArgs;
import io.lettuce.core.ScanArgs;
import io.lettuce.core.SortArgs;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Lettuce Key 命令
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public final class LettuceKeyCommands extends AbstractLettuceRedisCommands implements KeyCommands {

	public LettuceKeyCommands(final LettuceRedisClient client) {
		super(client);
	}

	@Override
	public Status copy(final String key, final String destKey) {
		final CommandArguments args = CommandArguments.create(key).add(destKey);
		return copy(rawBinaryKey(key), rawBinaryKey(destKey), null, false, args);
	}

	@Override
	public Status copy(final byte[] key, final byte[] destKey) {
		final CommandArguments args = CommandArguments.create(key).add(destKey);
		return copy(rawKey(key), rawKey(destKey), null, false, args);
	}

	@Override
	public Status copy(final String key, final String destKey, final int db) {
		final CommandArguments args = CommandArguments.create(key).add(destKey).add(Keyword.Database.DB, db);
		return copy(rawBinaryKey(key), rawBinaryKey(destKey), db, false, args);
	}

	@Override
	public Status copy(final byte[] key, final byte[] destKey, final int db) {
		final CommandArguments args = CommandArguments.create(key).add(destKey).add(Keyword.Database.DB, db);
		return copy(rawKey(key), rawKey(destKey), db, false, args);
	}

	@Override
	public Status copy(final String key, final String destKey, final boolean replace) {
		final CommandArguments args = CommandArguments.create(key).add(destKey)
				.add(replace ? Keyword.Common.REPLACE : null);
		return copy(rawBinaryKey(key), rawBinaryKey(destKey), null, replace, args);
	}

	@Override
	public Status copy(final byte[] key, final byte[] destKey, final boolean replace) {
		final CommandArguments args = CommandArguments.create(key).add(destKey)
				.add(replace ? Keyword.Common.REPLACE : null);
		return copy(rawKey(key), rawKey(destKey), null, replace, args);
	}

	@Override
	public Status copy(final String key, final String destKey, final int db, final boolean replace) {
		final CommandArguments args = CommandArguments.create(key).add(destKey).add(Keyword.Database.DB, db)
				.add(replace ? Keyword.Common.REPLACE : null);
		return copy(rawBinaryKey(key), rawBinaryKey(destKey), db, replace, args);
	}

	@Override
	public Status copy(final byte[] key, final byte[] destKey, final int db, final boolean replace) {
		final CommandArguments args = CommandArguments.create(key).add(destKey).add(Keyword.Database.DB, db)
				.add(replace ? Keyword.Common.REPLACE : null);
		return copy(rawKey(key), rawKey(destKey), db, replace, args);
	}

	@Override
	public Long del(final String... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return executeCommand(RedisCommand.DEL, args, (cmd)->cmd.del(rawBinaryKeys(keys)));
	}

	@Override
	public Long del(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return executeCommand(RedisCommand.DEL, args, (cmd)->cmd.del(rawKeys(keys)));
	}

	@Override
	public String dump(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.DUMP, args, (cmd)->cmd.dump(rawBinaryKey(key)), SafeEncoder::encode);
	}

	@Override
	public byte[] dump(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.DUMP, args, (cmd)->cmd.dump(rawKey(key)));
	}

	@Override
	public Boolean exists(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(
				RedisCommand.EXISTS, args, (cmd)->cmd.exists(rawBinaryKey(key)), new OneBooleanConverter());
	}

	@Override
	public Boolean exists(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.EXISTS, args, (cmd)->cmd.exists(rawKey(key)), new OneBooleanConverter());
	}

	@Override
	public Long exists(final String... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return executeCommand(RedisCommand.EXISTS, args, (cmd)->cmd.exists(rawBinaryKeys(keys)));
	}

	@Override
	public Long exists(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return executeCommand(RedisCommand.EXISTS, args, (cmd)->cmd.exists(rawKeys(keys)));
	}

	@Override
	public Status expire(final String key, final int lifetime) {
		final CommandArguments args = CommandArguments.create(key).add(lifetime);
		return executeCommand(RedisCommand.EXPIRE, args, (cmd)->cmd.expire(rawBinaryKey(key), lifetime),
				new BooleanStatusConverter());
	}

	@Override
	public Status expire(final byte[] key, final int lifetime) {
		final CommandArguments args = CommandArguments.create(key).add(lifetime);
		return executeCommand(RedisCommand.EXPIRE, args, (cmd)->cmd.expire(rawKey(key), lifetime),
				new BooleanStatusConverter());
	}

	@Override
	public Status expire(final String key, final int lifetime, final ExpireOption expireOption) {
		final CommandArguments args = CommandArguments.create(key).add(lifetime).add(expireOption);
		return executeCommand(RedisCommand.EXPIRE, args,
				(cmd)->cmd.expire(rawBinaryKey(key), lifetime, new LettuceExpireArgs(expireOption)),
				new BooleanStatusConverter());
	}

	@Override
	public Status expire(final byte[] key, final int lifetime, final ExpireOption expireOption) {
		final CommandArguments args = CommandArguments.create(key).add(lifetime).add(expireOption);
		return executeCommand(RedisCommand.EXPIRE, args,
				(cmd)->cmd.expire(rawKey(key), lifetime, new LettuceExpireArgs(expireOption)),
				new BooleanStatusConverter());
	}

	@Override
	public Status expireAt(final String key, final long unixTimestamp) {
		final CommandArguments args = CommandArguments.create(key).add(unixTimestamp);
		return executeCommand(RedisCommand.EXPIREAT, args, (cmd)->cmd.expireat(rawBinaryKey(key), unixTimestamp),
				new BooleanStatusConverter());
	}

	@Override
	public Status expireAt(final byte[] key, final long unixTimestamp) {
		final CommandArguments args = CommandArguments.create(key).add(unixTimestamp);
		return executeCommand(RedisCommand.EXPIREAT, args, (cmd)->cmd.expireat(rawKey(key), unixTimestamp),
				new BooleanStatusConverter());
	}

	@Override
	public Status expireAt(final String key, final long unixTimestamp, final ExpireOption expireOption) {
		final CommandArguments args = CommandArguments.create(key).add(unixTimestamp);
		return executeCommand(RedisCommand.EXPIREAT, args,
				(cmd)->cmd.expireat(rawBinaryKey(key), unixTimestamp, new LettuceExpireArgs(expireOption)),
				new BooleanStatusConverter());
	}

	@Override
	public Status expireAt(final byte[] key, final long unixTimestamp, final ExpireOption expireOption) {
		final CommandArguments args = CommandArguments.create(key).add(unixTimestamp);
		return executeCommand(RedisCommand.EXPIREAT, args,
				(cmd)->cmd.expireat(rawKey(key), unixTimestamp, new LettuceExpireArgs(expireOption)),
				new BooleanStatusConverter());
	}

	@Override
	public Long expireTime(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.EXPIRETIME, args, (cmd)->cmd.expiretime(rawBinaryKey(key)));
	}

	@Override
	public Long expireTime(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.EXPIRETIME, args, (cmd)->cmd.expiretime(rawKey(key)));
	}

	@Override
	public Set<String> keys(final String pattern) {
		final CommandArguments args = CommandArguments.create(pattern);
		return executeCommand(RedisCommand.KEYS, args, (cmd)->cmd.keys(pattern),
				new ListSetConverter<>(SafeEncoder::encode));
	}

	@Override
	public Set<byte[]> keys(final byte[] pattern) {
		final CommandArguments args = CommandArguments.create(pattern);
		return executeCommand(RedisCommand.KEYS, args, (cmd)->cmd.keys(SafeEncoder.encode(pattern)),
				new ListSetConverter<>((v)->v));
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final int timeout, final String... keys) {
		final CommandArguments args = CommandArguments.create(host).add(port).add("", db).add(timeout)
				.add(Keyword.Key.KEYS, keys);
		return migrate(host, port, db, timeout, new LettuceMigrateArgs<>(rawBinaryKeys(keys)), args);
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final int timeout, final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(host).add(port).add("", db).add(timeout)
				.add(Keyword.Key.KEYS, keys);
		return migrate(host, port, db, timeout, new LettuceMigrateArgs<>(rawKeys(keys)), args);
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final int timeout,
	                      final MigrateArgument argument, final String... keys) {
		final CommandArguments args = CommandArguments.create(host).add(port).add("", db).add(timeout).add(argument)
				.add(keys);
		return migrate(host, port, db, timeout, new LettuceMigrateArgs<>(argument, rawBinaryKeys(keys)), args);
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final int timeout,
	                      final MigrateArgument argument, final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(host).add(port).add("", db).add(timeout).add(argument)
				.add(keys);
		return migrate(host, port, db, timeout, new LettuceMigrateArgs<>(argument, rawKeys(keys)), args);
	}

	@Override
	public Status move(final String key, final int db) {
		final CommandArguments args = CommandArguments.create(key).add(db);
		return executeCommand(
				RedisCommand.MOVE, args, (cmd)->cmd.move(rawBinaryKey(key), db), new BooleanStatusConverter());
	}

	@Override
	public Status move(final byte[] key, final int db) {
		final CommandArguments args = CommandArguments.create(key).add(db);
		return executeCommand(RedisCommand.MOVE, args, (cmd)->cmd.move(rawKey(key), db), new BooleanStatusConverter());
	}

	@Override
	public ObjectEncoding objectEncoding(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.OBJECT, RedisSubCommand.OBJECT_ENCODING, args,
				(cmd)->cmd.objectEncoding(rawBinaryKey(key)), new ObjectEncodingConverter());
	}

	@Override
	public ObjectEncoding objectEncoding(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(
				RedisCommand.OBJECT, RedisSubCommand.OBJECT_ENCODING, args, (cmd)->cmd.objectEncoding(rawKey(key)),
				new ObjectEncodingConverter());
	}

	@Override
	public Long objectFreq(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(
				RedisCommand.OBJECT, RedisSubCommand.OBJECT_FREQ, args, (cmd)->cmd.objectFreq(rawBinaryKey(key)));
	}

	@Override
	public Long objectFreq(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.OBJECT, RedisSubCommand.OBJECT_FREQ, args,
				(cmd)->cmd.objectFreq(rawKey(key)));
	}

	@Override
	public Long objectIdleTime(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.OBJECT, RedisSubCommand.OBJECT_IDLETIME, args,
				(cmd)->cmd.objectIdletime(rawBinaryKey(key)));
	}

	@Override
	public Long objectIdleTime(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(
				RedisCommand.OBJECT, RedisSubCommand.OBJECT_IDLETIME, args, (cmd)->cmd.objectIdletime(rawKey(key)));
	}

	@Override
	public Long objectRefcount(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.OBJECT, RedisSubCommand.OBJECT_REFCOUNT, args,
				(cmd)->cmd.objectRefcount(rawBinaryKey(key)));
	}

	@Override
	public Long objectRefcount(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(
				RedisCommand.OBJECT, RedisSubCommand.OBJECT_REFCOUNT, args, (cmd)->cmd.objectRefcount(rawKey(key)));
	}

	@Override
	public Status persist(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.PERSIST, args, (cmd)->cmd.persist(rawBinaryKey(key)),
				new BooleanStatusConverter());
	}

	@Override
	public Status persist(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.PERSIST, args, (cmd)->cmd.persist(rawKey(key)),
				new BooleanStatusConverter());
	}

	@Override
	public Status pExpire(final String key, final int lifetime) {
		final CommandArguments args = CommandArguments.create(key).add(lifetime);
		return executeCommand(RedisCommand.PEXPIRE, args, (cmd)->cmd.pexpire(rawBinaryKey(key), lifetime),
				new BooleanStatusConverter());
	}

	@Override
	public Status pExpire(final byte[] key, final int lifetime) {
		final CommandArguments args = CommandArguments.create(key).add(lifetime);
		return executeCommand(RedisCommand.PEXPIRE, args, (cmd)->cmd.pexpire(rawKey(key), lifetime),
				new BooleanStatusConverter());
	}

	@Override
	public Status pExpire(final String key, final int lifetime, final ExpireOption expireOption) {
		final CommandArguments args = CommandArguments.create(key).add(lifetime).add(expireOption);
		return executeCommand(RedisCommand.PEXPIRE, args,
				(cmd)->cmd.pexpire(rawBinaryKey(key), lifetime, new LettuceExpireArgs(expireOption)),
				new BooleanStatusConverter());
	}

	@Override
	public Status pExpire(final byte[] key, final int lifetime, final ExpireOption expireOption) {
		final CommandArguments args = CommandArguments.create(key).add(lifetime).add(expireOption);
		return executeCommand(RedisCommand.PEXPIRE, args,
				(cmd)->cmd.pexpire(rawKey(key), lifetime, new LettuceExpireArgs(expireOption)),
				new BooleanStatusConverter());
	}

	@Override
	public Status pExpireAt(final String key, final long unixTimestamp) {
		final CommandArguments args = CommandArguments.create(key).add(unixTimestamp);
		return executeCommand(RedisCommand.EXPIREAT, args, (cmd)->cmd.pexpireat(rawBinaryKey(key), unixTimestamp),
				new BooleanStatusConverter());
	}

	@Override
	public Status pExpireAt(final byte[] key, final long unixTimestamp) {
		final CommandArguments args = CommandArguments.create(key).add(unixTimestamp);
		return executeCommand(RedisCommand.EXPIREAT, args, (cmd)->cmd.pexpireat(rawKey(key), unixTimestamp),
				new BooleanStatusConverter());
	}

	@Override
	public Status pExpireAt(final String key, final long unixTimestamp, final ExpireOption expireOption) {
		final CommandArguments args = CommandArguments.create(key).add(unixTimestamp).add(expireOption);
		return executeCommand(RedisCommand.EXPIREAT, args,
				(cmd)->cmd.pexpireat(rawBinaryKey(key), unixTimestamp, new LettuceExpireArgs(expireOption)),
				new BooleanStatusConverter());
	}

	@Override
	public Status pExpireAt(final byte[] key, final long unixTimestamp, final ExpireOption expireOption) {
		final CommandArguments args = CommandArguments.create(key).add(unixTimestamp).add(expireOption);
		return executeCommand(RedisCommand.EXPIREAT, args,
				(cmd)->cmd.pexpireat(rawKey(key), unixTimestamp, new LettuceExpireArgs(expireOption)),
				new BooleanStatusConverter());
	}

	@Override
	public Long pExpireTime(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.PEXPIRETIME, args, (cmd)->cmd.pexpiretime(rawBinaryKey(key)));
	}

	@Override
	public Long pExpireTime(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.PEXPIRETIME, args, (cmd)->cmd.pexpiretime(rawKey(key)));
	}

	@Override
	public Long pTtl(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.PTTL, args, (cmd)->cmd.pttl(rawBinaryKey(key)));
	}

	@Override
	public Long pTtl(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.PTTL, args, (cmd)->cmd.pttl(rawKey(key)));
	}

	@Override
	public String randomKey() {
		return executeCommand(RedisCommand.RANDOMKEY, (cmd)->cmd.randomkey(), SafeEncoder::encode);
	}

	@Override
	public Status rename(final String key, final String newKey) {
		final CommandArguments args = CommandArguments.create(key).add(newKey);
		return executeCommand(RedisCommand.RENAME, args, (cmd)->cmd.rename(rawBinaryKey(key), rawBinaryKey(newKey)),
				new OkStatusConverter());
	}

	@Override
	public Status rename(final byte[] key, final byte[] newKey) {
		final CommandArguments args = CommandArguments.create(key).add(newKey);
		return executeCommand(
				RedisCommand.RENAME, args, (cmd)->cmd.rename(rawKey(key), newKey), new OkStatusConverter());
	}

	@Override
	public Status renameNx(final String key, final String newKey) {
		final CommandArguments args = CommandArguments.create(key).add(newKey);
		return executeCommand(RedisCommand.RENAMENX, args, (cmd)->cmd.renamenx(rawBinaryKey(key), rawBinaryKey(newKey)),
				new BooleanStatusConverter());
	}

	@Override
	public Status renameNx(final byte[] key, final byte[] newKey) {
		final CommandArguments args = CommandArguments.create(key).add(newKey);
		return executeCommand(RedisCommand.RENAMENX, args, (cmd)->cmd.renamenx(rawKey(key), rawKey(newKey)),
				new BooleanStatusConverter());
	}

	@Override
	public Status restore(final String key, final byte[] serializedValue, final int ttl) {
		final CommandArguments args = CommandArguments.create(key).add(serializedValue).add(ttl);
		return executeCommand(RedisCommand.RESTORE, args, (cmd)->cmd.restore(rawBinaryKey(key), ttl, serializedValue),
				new OkStatusConverter());
	}

	@Override
	public Status restore(final byte[] key, final byte[] serializedValue, final int ttl) {
		final CommandArguments args = CommandArguments.create(key).add(serializedValue).add(ttl);
		return executeCommand(RedisCommand.RESTORE, args, (cmd)->cmd.restore(rawKey(key), ttl, serializedValue),
				new OkStatusConverter());
	}

	@Override
	public Status restore(final String key, final byte[] serializedValue, final int ttl,
	                      final RestoreArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(serializedValue).add(ttl).add(argument);
		return restore(rawBinaryKey(key), serializedValue, ttl, argument, args);
	}

	@Override
	public Status restore(final byte[] key, final byte[] serializedValue, final int ttl,
	                      final RestoreArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(serializedValue).add(ttl).add(argument);
		return restore(rawKey(key), serializedValue, ttl, argument, args);
	}

	@Override
	public ScanResult<String> scan(final String cursor) {
		final CommandArguments args = CommandArguments.create(cursor);
		return executeCommand(RedisCommand.SCAN, args, (cmd)->cmd.scan(new LettuceScanCursor(cursor)),
				new ScanCursorConverter.KeyScanCursorConverter<>(SafeEncoder::encode));
	}

	@Override
	public ScanResult<byte[]> scan(final byte[] cursor) {
		final CommandArguments args = CommandArguments.create(cursor);
		return executeCommand(RedisCommand.SCAN, args, (cmd)->cmd.scan(new LettuceScanCursor(cursor)),
				new ScanCursorConverter.KeyScanCursorConverter<>((v)->v));
	}

	@Override
	public ScanResult<String> scan(final String cursor, final String pattern) {
		final CommandArguments args = CommandArguments.create(cursor).add(Keyword.Scan.MATCH, pattern);
		return scan(cursor, new LettuceScanArgs(pattern), args);
	}

	@Override
	public ScanResult<byte[]> scan(final byte[] cursor, final byte[] pattern) {
		final CommandArguments args = CommandArguments.create(cursor).add(Keyword.Scan.MATCH, pattern);
		return scan(cursor, new LettuceScanArgs(pattern), args);
	}

	@Override
	public ScanResult<String> scan(final String cursor, final String pattern, final int count) {
		final CommandArguments args = CommandArguments.create(cursor).add(Keyword.Scan.MATCH, pattern)
				.add(Keyword.Common.COUNT, count);
		return scan(cursor, new LettuceScanArgs(pattern, count), args);
	}

	@Override
	public ScanResult<byte[]> scan(final byte[] cursor, final byte[] pattern, final int count) {
		final CommandArguments args = CommandArguments.create(cursor).add(Keyword.Scan.MATCH, pattern)
				.add(Keyword.Common.COUNT, count);
		return scan(cursor, new LettuceScanArgs(pattern, count), args);
	}

	@Override
	public ScanResult<String> scan(final String cursor, final int count) {
		final CommandArguments args = CommandArguments.create(cursor).add(Keyword.Common.COUNT, count);
		return scan(cursor, new LettuceScanArgs(count), args);
	}

	@Override
	public ScanResult<byte[]> scan(final byte[] cursor, final int count) {
		final CommandArguments args = CommandArguments.create(cursor).add(Keyword.Common.COUNT, count);
		return scan(cursor, new LettuceScanArgs(count), args);
	}

	@Override
	public List<String> sort(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.SORT, args, (cmd)->cmd.sort(rawBinaryKey(key)),
				new BinaryListStringListConverter());
	}

	@Override
	public List<byte[]> sort(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.SORT, args, (cmd)->cmd.sort(rawKey(key)));
	}

	@Override
	public List<String> sort(final String key, final SortArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(argument);
		return stringSort(rawBinaryKey(key), new LettuceSortArgs(argument), args);
	}

	@Override
	public List<byte[]> sort(final byte[] key, final SortArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(argument);
		return binarySort(rawKey(key), new LettuceSortArgs(argument), args);
	}

	@Override
	public List<String> sort(final String key, final SortArgument argument, final int offset, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(argument).add(Keyword.Common.LIMIT)
				.add(offset, count);
		return stringSort(rawBinaryKey(key), new LettuceSortArgs(argument, offset, count), args);
	}

	@Override
	public List<byte[]> sort(final byte[] key, final SortArgument argument, final int offset, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(argument).add(Keyword.Common.LIMIT)
				.add(offset, count);
		return binarySort(rawKey(key), new LettuceSortArgs(argument, offset, count), args);
	}

	@Override
	public List<String> sort(final String key, final int offset, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(Keyword.Common.LIMIT).add(offset, count);
		return stringSort(rawBinaryKey(key), new LettuceSortArgs(offset, count), args);
	}

	@Override
	public List<byte[]> sort(final byte[] key, final int offset, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(Keyword.Common.LIMIT).add(offset, count);
		return binarySort(rawKey(key), new LettuceSortArgs(offset, count), args);
	}

	@Override
	public Long sort(final String key, final String destKey) {
		final CommandArguments args = CommandArguments.create(key).add("STORE", destKey);
		return sortStore(rawBinaryKey(key), rawBinaryKey(destKey), new LettuceSortArgs(), args);
	}

	@Override
	public Long sort(final byte[] key, final byte[] destKey) {
		final CommandArguments args = CommandArguments.create(key).add("STORE", destKey);
		return sortStore(rawKey(key), rawKey(destKey), new LettuceSortArgs(), args);
	}

	@Override
	public Long sort(final String key, final String destKey, final SortArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(argument).add("STORE", destKey);
		return sortStore(SafeEncoder.encode(key), rawBinaryKey(destKey), new LettuceSortArgs(argument), args);
	}

	@Override
	public Long sort(final byte[] key, final byte[] destKey, final SortArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(argument).add("STORE", destKey);
		return sortStore(rawKey(key), rawKey(destKey), new LettuceSortArgs(argument), args);
	}

	@Override
	public Long sort(final String key, final String destKey, final SortArgument argument, final int offset,
	                 final int count) {
		final CommandArguments args = CommandArguments.create(key).add(argument).add(Keyword.Common.LIMIT)
				.add(offset, count).add("STORE", destKey);
		return sortStore(SafeEncoder.encode(key), rawBinaryKey(destKey), new LettuceSortArgs(argument, offset, count),
				args);
	}

	@Override
	public Long sort(final byte[] key, final byte[] destKey, final SortArgument argument, final int offset,
	                 final int count) {
		final CommandArguments args = CommandArguments.create(key).add(argument).add(Keyword.Common.LIMIT)
				.add(offset, count).add("STORE", destKey);
		return sortStore(rawKey(key), rawKey(destKey), new LettuceSortArgs(argument, offset, count), args);
	}

	@Override
	public Long sort(final String key, final String destKey, final int offset, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(Keyword.Common.LIMIT).add(offset, count)
				.add("STORE", destKey);
		return sortStore(SafeEncoder.encode(key), rawBinaryKey(destKey), new LettuceSortArgs(offset, count), args);
	}

	@Override
	public Long sort(final byte[] key, final byte[] destKey, final int offset, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(Keyword.Common.LIMIT).add(offset, count)
				.add("STORE", destKey);
		return sortStore(rawKey(key), rawKey(destKey), new LettuceSortArgs(offset, count), args);
	}

	@Override
	public List<String> sortRo(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.SORT_RO, args, (cmd)->cmd.sortReadOnly(rawBinaryKey(key)),
				new BinaryListStringListConverter());
	}

	@Override
	public List<byte[]> sortRo(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.SORT_RO, args, (cmd)->cmd.sortReadOnly(rawKey(key)), (v)->v);
	}

	@Override
	public List<String> sortRo(final String key, final SortArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(argument);
		return stringSortRo(rawBinaryKey(key), new LettuceSortArgs(argument), args);
	}

	@Override
	public List<byte[]> sortRo(final byte[] key, final SortArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(argument);
		return binarySortRo(rawKey(key), new LettuceSortArgs(argument), args);
	}

	@Override
	public List<String> sortRo(final String key, final SortArgument argument, final int offset, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(argument).add(Keyword.Common.LIMIT)
				.add(offset, count);
		return stringSortRo(rawBinaryKey(key), new LettuceSortArgs(argument).limit(offset, count), args);
	}

	@Override
	public List<byte[]> sortRo(final byte[] key, final SortArgument argument, final int offset, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(argument).add(Keyword.Common.LIMIT)
				.add(offset, count);
		return binarySortRo(rawKey(key), new LettuceSortArgs(argument).limit(offset, count), args);
	}

	@Override
	public List<String> sortRo(final String key, final int offset, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(Keyword.Common.LIMIT).add(offset, count);
		return stringSortRo(rawBinaryKey(key), new LettuceSortArgs().limit(offset, count), args);
	}

	@Override
	public List<byte[]> sortRo(final byte[] key, final int offset, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(Keyword.Common.LIMIT).add(offset, count);
		return binarySortRo(rawKey(key), new LettuceSortArgs().limit(offset, count), args);
	}

	@Override
	public Long touch(final String... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return executeCommand(RedisCommand.TOUCH, args, (cmd)->cmd.touch(rawBinaryKeys(keys)));
	}

	@Override
	public Long touch(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return executeCommand(RedisCommand.TOUCH, args, (cmd)->cmd.touch(rawKeys(keys)));
	}

	@Override
	public Long ttl(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.TTL, args, (cmd)->cmd.ttl(rawBinaryKey(key)));
	}

	@Override
	public Long ttl(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.TTL, args, (cmd)->cmd.ttl(rawKey(key)));
	}

	@Override
	public Type type(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.TYPE, args, (cmd)->cmd.type(rawBinaryKey(key)), new TypeConverter());
	}

	@Override
	public Type type(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.TYPE, args, (cmd)->cmd.type(rawKey(key)), new TypeConverter());
	}

	@Override
	public Long unlink(final String... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return executeCommand(RedisCommand.UNLINK, args, (cmd)->cmd.unlink(rawBinaryKeys(keys)));
	}

	@Override
	public Long unlink(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return executeCommand(RedisCommand.UNLINK, args, (cmd)->cmd.unlink(rawKeys(keys)));
	}

	private Status copy(final byte[] key, final byte[] destKey, final Integer destinationDb, final boolean replace,
	                    final CommandArguments args) {
		final CopyArgs copyArgs = CopyArgs.Builder.replace(replace);

		Optional.ofNullable(destinationDb).ifPresent(copyArgs::destinationDb);

		return executeCommand(RedisCommand.COPY, args, (cmd)->cmd.copy(key, destKey, copyArgs),
				new BooleanStatusConverter());
	}

	private Status migrate(final String host, final int port, final int db, final int timeout,
	                       final MigrateArgs<byte[]> migrateArgs, final CommandArguments args) {
		return executeCommand(RedisCommand.MIGRATE, args, (cmd)->cmd.migrate(host, port, db, timeout, migrateArgs),
				new OkStatusConverter());
	}

	private Status restore(final byte[] key, final byte[] serializedValue, final int ttl,
	                       final RestoreArgument argument, final CommandArguments args) {
		return executeCommand(RedisCommand.RESTORE, args,
				(cmd)->cmd.restore(key, serializedValue, new LettuceRestoreArgs(argument).ttl(ttl)),
				new OkStatusConverter());
	}

	private ScanResult<String> scan(final String cursor, final ScanArgs scanArgs, final CommandArguments args) {
		return executeCommand(RedisCommand.SCAN, args, (cmd)->cmd.scan(new LettuceScanCursor(cursor), scanArgs),
				new ScanCursorConverter.KeyScanCursorConverter<>(SafeEncoder::encode));
	}

	private ScanResult<byte[]> scan(final byte[] cursor, final ScanArgs scanArgs, final CommandArguments args) {
		return executeCommand(RedisCommand.SCAN, args, (cmd)->cmd.scan(new LettuceScanCursor(cursor), scanArgs),
				new ScanCursorConverter.KeyScanCursorConverter<>((v)->v));
	}

	private List<String> stringSort(final byte[] key, final SortArgs sortArgs, final CommandArguments args) {
		return executeCommand(
				RedisCommand.SORT, args, (cmd)->cmd.sort(key, sortArgs), new BinaryListStringListConverter());
	}

	private List<byte[]> binarySort(final byte[] key, final SortArgs sortArgs, final CommandArguments args) {
		return executeCommand(RedisCommand.SORT, args, (cmd)->cmd.sort(key, sortArgs));
	}

	private Long sortStore(final byte[] key, final byte[] destKey, final SortArgs sortArgs,
	                       final CommandArguments args) {
		return executeCommand(RedisCommand.SORT, args, (cmd)->cmd.sortStore(key, sortArgs, destKey));
	}

	private List<String> stringSortRo(final byte[] key, final SortArgs sortArgs, final CommandArguments args) {
		return executeCommand(RedisCommand.SORT_RO, args, (cmd)->cmd.sortReadOnly(key, sortArgs),
				new BinaryListStringListConverter());
	}

	private List<byte[]> binarySortRo(final byte[] key, final SortArgs sortArgs, final CommandArguments args) {
		return executeCommand(RedisCommand.SORT_RO, args, (cmd)->cmd.sortReadOnly(key, sortArgs));
	}

}
