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
package com.buession.redis.client.lettuce.operations;

import com.buession.core.converter.BooleanStatusConverter;
import com.buession.core.converter.Converter;
import com.buession.core.converter.ListConverter;
import com.buession.core.converter.ListSetConverter;
import com.buession.lang.Status;
import com.buession.redis.client.lettuce.LettuceRedisClient;
import com.buession.redis.client.operations.KeyOperations;
import com.buession.redis.core.ExpireOption;
import com.buession.redis.core.Keyword;
import com.buession.redis.core.ObjectEncoding;
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.Type;
import com.buession.redis.core.command.Command;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.SubCommand;
import com.buession.redis.core.command.args.MigrateArgument;
import com.buession.redis.core.command.args.RestoreArgument;
import com.buession.redis.core.command.args.SortArgument;
import com.buession.redis.core.internal.convert.lettuce.params.ExpireOptionConverter;
import com.buession.redis.core.internal.convert.lettuce.params.MigrateArgumentConverter;
import com.buession.redis.core.internal.convert.lettuce.params.RestoreArgumentConverter;
import com.buession.redis.core.internal.convert.lettuce.params.SortArgumentConverter;
import com.buession.redis.core.internal.convert.lettuce.response.ScanCursorConverter;
import com.buession.redis.core.internal.convert.response.ObjectEncodingConverter;
import com.buession.redis.core.internal.convert.response.OkStatusConverter;
import com.buession.redis.core.internal.convert.response.TypeConverter;
import com.buession.redis.core.internal.lettuce.LettuceScanArgs;
import com.buession.redis.core.internal.lettuce.LettuceScanCursor;
import com.buession.redis.core.internal.lettuce.LettuceSortArgs;
import com.buession.redis.utils.SafeEncoder;
import io.lettuce.core.CopyArgs;
import io.lettuce.core.MigrateArgs;
import io.lettuce.core.RestoreArgs;
import io.lettuce.core.ScanArgs;
import io.lettuce.core.SortArgs;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Lettuce Key 命令操作
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public final class LettuceKeyOperations extends AbstractLettuceRedisOperations implements KeyOperations {

	public LettuceKeyOperations(final LettuceRedisClient client) {
		super(client);
	}

	@Override
	public Status copy(final String key, final String destKey) {
		final CommandArguments args = CommandArguments.create(key).add(destKey);
		return copy(key, destKey, null, false, args);
	}

	@Override
	public Status copy(final byte[] key, final byte[] destKey) {
		final CommandArguments args = CommandArguments.create(key).add(destKey);
		return copy(key, destKey, null, false, args);
	}

	@Override
	public Status copy(final String key, final String destKey, final int db) {
		final CommandArguments args = CommandArguments.create(key).add(destKey).add(Keyword.Database.DB, db);
		return copy(key, destKey, db, false, args);
	}

	@Override
	public Status copy(final byte[] key, final byte[] destKey, final int db) {
		final CommandArguments args = CommandArguments.create(key).add(destKey).add(Keyword.Database.DB, db);
		return copy(key, destKey, db, false, args);
	}

	@Override
	public Status copy(final String key, final String destKey, final boolean replace) {
		final CommandArguments args = CommandArguments.create(key).add(destKey)
				.add(replace ? Keyword.Common.REPLACE : null);
		return copy(key, destKey, null, replace, args);
	}

	@Override
	public Status copy(final byte[] key, final byte[] destKey, final boolean replace) {
		final CommandArguments args = CommandArguments.create(key).add(destKey)
				.add(replace ? Keyword.Common.REPLACE : null);
		return copy(key, destKey, null, replace, args);
	}

	@Override
	public Status copy(final String key, final String destKey, final int db, final boolean replace) {
		final CommandArguments args = CommandArguments.create(key).add(destKey).add(Keyword.Database.DB, db)
				.add(replace ? Keyword.Common.REPLACE : null);
		return copy(key, destKey, db, replace, args);
	}

	@Override
	public Status copy(final byte[] key, final byte[] destKey, final int db, final boolean replace) {
		final CommandArguments args = CommandArguments.create(key).add(destKey).add(Keyword.Database.DB, db)
				.add(replace ? Keyword.Common.REPLACE : null);
		return copy(key, destKey, db, replace, args);
	}

	@Override
	public Long del(final String... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return executeCommand(Command.DEL, args, (cmd)->cmd.del(SafeEncoder.encode(keys)), (v)->v);
	}

	@Override
	public Long del(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return executeCommand(Command.DEL, args, (cmd)->cmd.del(keys), (v)->v);
	}

	@Override
	public String dump(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.DUMP, args, (cmd)->cmd.dump(SafeEncoder.encode(key)), SafeEncoder::encode);
	}

	@Override
	public byte[] dump(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.DUMP, args, (cmd)->cmd.dump(key), (v)->v);
	}

	@Override
	public Boolean exists(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.EXISTS, args, (cmd)->cmd.exists(SafeEncoder.encode(key)), (v)->v == 1L);
	}

	@Override
	public Boolean exists(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.EXISTS, args, (cmd)->cmd.exists(key), (v)->v == 1L);
	}

	@Override
	public Long exists(final String... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return executeCommand(Command.EXISTS, args, (cmd)->cmd.exists(SafeEncoder.encode(keys)), (v)->v);
	}

	@Override
	public Long exists(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return executeCommand(Command.EXISTS, args, (cmd)->cmd.exists(keys), (v)->v);
	}

	@Override
	public Status expire(final String key, final int lifetime) {
		final CommandArguments args = CommandArguments.create(key).add(lifetime);
		return executeCommand(Command.EXPIRE, args, (cmd)->cmd.expire(SafeEncoder.encode(key), lifetime),
				new BooleanStatusConverter());
	}

	@Override
	public Status expire(final byte[] key, final int lifetime) {
		final CommandArguments args = CommandArguments.create(key).add(lifetime);
		return executeCommand(Command.EXPIRE, args, (cmd)->cmd.expire(key, lifetime), new BooleanStatusConverter());
	}

	@Override
	public Status expire(final String key, final int lifetime, final ExpireOption expireOption) {
		final CommandArguments args = CommandArguments.create(key).add(lifetime).add(expireOption);
		final ExpireOptionConverter expireOptionConverter = new ExpireOptionConverter();
		return executeCommand(Command.EXPIRE, args,
				(cmd)->cmd.expire(SafeEncoder.encode(key), lifetime, expireOptionConverter.convert(expireOption)),
				new BooleanStatusConverter());
	}

	@Override
	public Status expire(final byte[] key, final int lifetime, final ExpireOption expireOption) {
		final CommandArguments args = CommandArguments.create(key).add(lifetime).add(expireOption);
		final ExpireOptionConverter expireOptionConverter = new ExpireOptionConverter();
		return executeCommand(Command.EXPIRE, args,
				(cmd)->cmd.expire(key, lifetime, expireOptionConverter.convert(expireOption)),
				new BooleanStatusConverter());
	}

	@Override
	public Status expireAt(final String key, final long unixTimestamp) {
		final CommandArguments args = CommandArguments.create(key).add(unixTimestamp);
		return executeCommand(Command.EXPIREAT, args, (cmd)->cmd.expireat(SafeEncoder.encode(key), unixTimestamp),
				new BooleanStatusConverter());
	}

	@Override
	public Status expireAt(final byte[] key, final long unixTimestamp) {
		final CommandArguments args = CommandArguments.create(key).add(unixTimestamp);
		return executeCommand(Command.EXPIREAT, args, (cmd)->cmd.expireat(key, unixTimestamp),
				new BooleanStatusConverter());
	}

	@Override
	public Status expireAt(final String key, final long unixTimestamp, final ExpireOption expireOption) {
		final CommandArguments args = CommandArguments.create(key).add(unixTimestamp);
		final ExpireOptionConverter expireOptionConverter = new ExpireOptionConverter();
		return executeCommand(Command.EXPIREAT, args, (cmd)->cmd.expireat(SafeEncoder.encode(key), unixTimestamp,
				expireOptionConverter.convert(expireOption)), new BooleanStatusConverter());
	}

	@Override
	public Status expireAt(final byte[] key, final long unixTimestamp, final ExpireOption expireOption) {
		final CommandArguments args = CommandArguments.create(key).add(unixTimestamp);
		final ExpireOptionConverter expireOptionConverter = new ExpireOptionConverter();
		return executeCommand(Command.EXPIREAT, args,
				(cmd)->cmd.expireat(key, unixTimestamp, expireOptionConverter.convert(expireOption)),
				new BooleanStatusConverter());
	}

	@Override
	public Long expireTime(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.EXPIRETIME, args, (cmd)->cmd.expiretime(SafeEncoder.encode(key)), (v)->v);
	}

	@Override
	public Long expireTime(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.EXPIRETIME, args, (cmd)->cmd.expiretime(key), (v)->v);
	}

	@Override
	public Set<String> keys(final String pattern) {
		final CommandArguments args = CommandArguments.create(pattern);
		return executeCommand(Command.KEYS, args, (cmd)->cmd.keys(pattern),
				new ListSetConverter<>(SafeEncoder::encode));
	}

	@Override
	public Set<byte[]> keys(final byte[] pattern) {
		final CommandArguments args = CommandArguments.create(pattern);
		return executeCommand(Command.KEYS, args, (cmd)->cmd.keys(SafeEncoder.encode(pattern)),
				new ListSetConverter<>((v)->v));
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final int timeout, final String... keys) {
		final CommandArguments args = CommandArguments.create(host).add(port).add("", db).add(timeout)
				.add(Keyword.Key.KEYS, keys);
		return migrate(host, port, db, timeout, MigrateArgs.Builder.keys(SafeEncoder.encode(keys)), args);
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final int timeout, final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(host).add(port).add("", db).add(timeout)
				.add(Keyword.Key.KEYS, keys);
		return migrate(host, port, db, timeout, MigrateArgs.Builder.keys(keys), args);
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final int timeout,
						  final MigrateArgument argument, final String... keys) {
		final CommandArguments args = CommandArguments.create(host).add(port).add("", db).add(timeout).add(argument)
				.add(keys);
		final MigrateArgumentConverter<byte[]> migrateArgumentConverter = new MigrateArgumentConverter<>();
		final MigrateArgs<byte[]> migrateArgs = Optional.ofNullable(migrateArgumentConverter.convert(argument))
				.orElse(new MigrateArgs<>());

		migrateArgs.keys(SafeEncoder.encode(keys));

		return migrate(host, port, db, timeout, migrateArgs, args);
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final int timeout,
						  final MigrateArgument argument, final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(host).add(port).add("", db).add(timeout).add(argument)
				.add(keys);
		final MigrateArgumentConverter<byte[]> migrateArgumentConverter = new MigrateArgumentConverter<>();
		final MigrateArgs<byte[]> migrateArgs = Optional.ofNullable(migrateArgumentConverter.convert(argument))
				.orElse(new MigrateArgs<>());

		migrateArgs.keys(keys);

		return migrate(host, port, db, timeout, migrateArgs, args);
	}

	@Override
	public Status move(final String key, final int db) {
		final CommandArguments args = CommandArguments.create(key).add(db);
		return executeCommand(Command.MOVE, args, (cmd)->cmd.move(SafeEncoder.encode(key), db),
				new BooleanStatusConverter());
	}

	@Override
	public Status move(final byte[] key, final int db) {
		final CommandArguments args = CommandArguments.create(key).add(db);
		return executeCommand(Command.MOVE, args, (cmd)->cmd.move(key, db), new BooleanStatusConverter());
	}

	@Override
	public ObjectEncoding objectEncoding(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.OBJECT, SubCommand.OBJECT_ENCODING, args,
				(cmd)->cmd.objectEncoding(SafeEncoder.encode(key)), new ObjectEncodingConverter());
	}

	@Override
	public ObjectEncoding objectEncoding(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.OBJECT, SubCommand.OBJECT_ENCODING, args, (cmd)->cmd.objectEncoding(key),
				new ObjectEncodingConverter());
	}

	@Override
	public Long objectFreq(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.OBJECT, SubCommand.OBJECT_FREQ, args,
				(cmd)->cmd.objectFreq(SafeEncoder.encode(key)), (v)->v);
	}

	@Override
	public Long objectFreq(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.OBJECT, SubCommand.OBJECT_FREQ, args, (cmd)->cmd.objectFreq(key), (v)->v);
	}

	@Override
	public Long objectIdleTime(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.OBJECT, SubCommand.OBJECT_IDLETIME, args,
				(cmd)->cmd.objectIdletime(SafeEncoder.encode(key)), (v)->v);
	}

	@Override
	public Long objectIdleTime(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.OBJECT, SubCommand.OBJECT_IDLETIME, args, (cmd)->cmd.objectIdletime(key), (v)->v);
	}

	@Override
	public Long objectRefcount(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.OBJECT, SubCommand.OBJECT_REFCOUNT, args,
				(cmd)->cmd.objectRefcount(SafeEncoder.encode(key)), (v)->v);
	}

	@Override
	public Long objectRefcount(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.OBJECT, SubCommand.OBJECT_REFCOUNT, args, (cmd)->cmd.objectRefcount(key), (v)->v);
	}

	@Override
	public Status persist(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.PERSIST, args, (cmd)->cmd.persist(SafeEncoder.encode(key)),
				new BooleanStatusConverter());
	}

	@Override
	public Status persist(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.PERSIST, args, (cmd)->cmd.persist(key), new BooleanStatusConverter());
	}

	@Override
	public Status pExpire(final String key, final int lifetime) {
		final CommandArguments args = CommandArguments.create(key).add(lifetime);
		return executeCommand(Command.PEXPIRE, args, (cmd)->cmd.pexpire(SafeEncoder.encode(key), lifetime),
				new BooleanStatusConverter());
	}

	@Override
	public Status pExpire(final byte[] key, final int lifetime) {
		final CommandArguments args = CommandArguments.create(key).add(lifetime);
		return executeCommand(Command.PEXPIRE, args, (cmd)->cmd.pexpire(key, lifetime), new BooleanStatusConverter());
	}

	@Override
	public Status pExpire(final String key, final int lifetime, final ExpireOption expireOption) {
		final CommandArguments args = CommandArguments.create(key).add(lifetime).add(expireOption);
		return pExpire(SafeEncoder.encode(key), lifetime, expireOption, args);
	}

	@Override
	public Status pExpire(final byte[] key, final int lifetime, final ExpireOption expireOption) {
		final CommandArguments args = CommandArguments.create(key).add(lifetime).add(expireOption);
		return pExpire(key, lifetime, expireOption, args);
	}

	@Override
	public Status pExpireAt(final String key, final long unixTimestamp) {
		final CommandArguments args = CommandArguments.create(key).add(unixTimestamp);
		return executeCommand(Command.EXPIREAT, args, (cmd)->cmd.pexpireat(SafeEncoder.encode(key), unixTimestamp),
				new BooleanStatusConverter());
	}

	@Override
	public Status pExpireAt(final byte[] key, final long unixTimestamp) {
		final CommandArguments args = CommandArguments.create(key).add(unixTimestamp);
		return executeCommand(Command.EXPIREAT, args, (cmd)->cmd.pexpireat(key, unixTimestamp),
				new BooleanStatusConverter());
	}

	@Override
	public Status pExpireAt(final String key, final long unixTimestamp, final ExpireOption expireOption) {
		final CommandArguments args = CommandArguments.create(key).add(unixTimestamp).add(expireOption);
		return pExpireAt(SafeEncoder.encode(key), unixTimestamp, expireOption, args);
	}

	@Override
	public Status pExpireAt(final byte[] key, final long unixTimestamp, final ExpireOption expireOption) {
		final CommandArguments args = CommandArguments.create(key).add(unixTimestamp).add(expireOption);
		return pExpireAt(key, unixTimestamp, expireOption, args);
	}

	@Override
	public Long pExpireTime(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.PEXPIRETIME, args, (cmd)->cmd.pexpiretime(SafeEncoder.encode(key)), (v)->v);
	}

	@Override
	public Long pExpireTime(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.PEXPIRETIME, args, (cmd)->cmd.pexpiretime(key), (v)->v);
	}

	@Override
	public Long pTtl(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.PTTL, args, (cmd)->cmd.pttl(SafeEncoder.encode(key)), (v)->v);
	}

	@Override
	public Long pTtl(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.PTTL, args, (cmd)->cmd.pttl(key), (v)->v);
	}

	@Override
	public String randomKey() {
		return executeCommand(Command.RANDOMKEY, (cmd)->cmd.randomkey(), SafeEncoder::encode);
	}

	@Override
	public Status rename(final String key, final String newKey) {
		final CommandArguments args = CommandArguments.create(key).add(newKey);
		return executeCommand(Command.RENAME, args,
				(cmd)->cmd.rename(SafeEncoder.encode(key), SafeEncoder.encode(newKey)), new OkStatusConverter());
	}

	@Override
	public Status rename(final byte[] key, final byte[] newKey) {
		final CommandArguments args = CommandArguments.create(key).add(newKey);
		return executeCommand(Command.RENAME, args, (cmd)->cmd.rename(key, newKey), new OkStatusConverter());
	}

	@Override
	public Status renameNx(final String key, final String newKey) {
		final CommandArguments args = CommandArguments.create(key).add(newKey);
		return executeCommand(Command.RENAMENX, args,
				(cmd)->cmd.renamenx(SafeEncoder.encode(key), SafeEncoder.encode(newKey)), new BooleanStatusConverter());
	}

	@Override
	public Status renameNx(final byte[] key, final byte[] newKey) {
		final CommandArguments args = CommandArguments.create(key).add(newKey);
		return executeCommand(Command.RENAMENX, args, (cmd)->cmd.renamenx(key, newKey), new BooleanStatusConverter());
	}

	@Override
	public Status restore(final String key, final byte[] serializedValue, final int ttl) {
		final CommandArguments args = CommandArguments.create(key).add(serializedValue).add(ttl);
		return executeCommand(Command.RESTORE, args, (cmd)->cmd.restore(SafeEncoder.encode(key), ttl, serializedValue),
				new OkStatusConverter());
	}

	@Override
	public Status restore(final byte[] key, final byte[] serializedValue, final int ttl) {
		final CommandArguments args = CommandArguments.create(key).add(serializedValue).add(ttl);
		return executeCommand(Command.RESTORE, args, (cmd)->cmd.restore(key, ttl, serializedValue),
				new OkStatusConverter());
	}

	@Override
	public Status restore(final String key, final byte[] serializedValue, final int ttl,
						  final RestoreArgument argument) {
		return restore(SafeEncoder.encode(key), serializedValue, ttl, argument);
	}

	@Override
	public Status restore(final byte[] key, final byte[] serializedValue, final int ttl,
						  final RestoreArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(serializedValue).add(ttl).add(argument);
		final RestoreArgumentConverter restoreArgumentConverter = new RestoreArgumentConverter();
		final RestoreArgs restoreArgs = Optional.ofNullable(restoreArgumentConverter.convert(argument))
				.orElse(new RestoreArgs());

		restoreArgs.ttl(ttl);

		return executeCommand(Command.RESTORE, args, (cmd)->cmd.restore(key, serializedValue, restoreArgs),
				new OkStatusConverter());
	}

	@Override
	public ScanResult<String> scan(final String cursor) {
		final CommandArguments args = CommandArguments.create(cursor);
		return executeCommand(Command.SCAN, args, (cmd)->cmd.scan(new LettuceScanCursor(cursor)),
				new ScanCursorConverter.KeyScanCursorConverter<>(SafeEncoder::encode));
	}

	@Override
	public ScanResult<byte[]> scan(final byte[] cursor) {
		final CommandArguments args = CommandArguments.create(cursor);
		return executeCommand(Command.SCAN, args, (cmd)->cmd.scan(new LettuceScanCursor(cursor)),
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
	public List<String> sort(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.SORT, args, (cmd)->cmd.sort(SafeEncoder.encode(key)),
				new ListConverter<>(SafeEncoder::encode));
	}

	@Override
	public List<byte[]> sort(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.SORT, args, (cmd)->cmd.sort(key), (v)->v);
	}

	@Override
	public List<String> sort(final String key, final SortArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(argument);
		final SortArgumentConverter sortArgumentConverter = new SortArgumentConverter();
		return sort(SafeEncoder.encode(key), sortArgumentConverter.convert(argument), SafeEncoder::encode, args);
	}

	@Override
	public List<byte[]> sort(final byte[] key, final SortArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(argument);
		final SortArgumentConverter sortArgumentConverter = new SortArgumentConverter();
		return sort(key, sortArgumentConverter.convert(argument), (v)->v, args);
	}

	@Override
	public Long sort(final String key, final String destKey) {
		final CommandArguments args = CommandArguments.create(key).add("STORE", destKey);
		return sortStore(SafeEncoder.encode(key), SafeEncoder.encode(destKey), new LettuceSortArgs(), args);
	}

	@Override
	public Long sort(final byte[] key, final byte[] destKey) {
		final CommandArguments args = CommandArguments.create(key).add("STORE", destKey);
		return sortStore(key, destKey, new LettuceSortArgs(), args);
	}

	@Override
	public Long sort(final String key, final String destKey, final SortArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(argument).add("STORE", destKey);
		final SortArgumentConverter sortArgumentConverter = new SortArgumentConverter();
		return sortStore(SafeEncoder.encode(key), SafeEncoder.encode(destKey), sortArgumentConverter.convert(argument),
				args);
	}

	@Override
	public Long sort(final byte[] key, final byte[] destKey, final SortArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(argument).add("STORE", destKey);
		final SortArgumentConverter sortArgumentConverter = new SortArgumentConverter();
		return sortStore(key, destKey, sortArgumentConverter.convert(argument), args);
	}

	@Override
	public List<String> sortRo(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.SORT_RO, args, (cmd)->cmd.sortReadOnly(SafeEncoder.encode(key)),
				new ListConverter<>(SafeEncoder::encode));
	}

	@Override
	public List<byte[]> sortRo(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.SORT_RO, args, (cmd)->cmd.sortReadOnly(key), (v)->v);
	}

	@Override
	public List<String> sortRo(final String key, final SortArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(argument);
		final SortArgumentConverter sortArgumentConverter = new SortArgumentConverter();
		return sortRo(SafeEncoder.encode(key), sortArgumentConverter.convert(argument), SafeEncoder::encode, args);
	}

	@Override
	public List<byte[]> sortRo(final byte[] key, final SortArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(argument);
		final SortArgumentConverter sortArgumentConverter = new SortArgumentConverter();
		return sortRo(key, sortArgumentConverter.convert(argument), (v)->v, args);
	}

	@Override
	public Long touch(final String... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return executeCommand(Command.TOUCH, args, (cmd)->cmd.touch(SafeEncoder.encode(keys)), (v)->v);
	}

	@Override
	public Long touch(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return executeCommand(Command.TOUCH, args, (cmd)->cmd.touch(keys), (v)->v);
	}

	@Override
	public Long ttl(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.TTL, args, (cmd)->cmd.ttl(SafeEncoder.encode(key)), (v)->v);
	}

	@Override
	public Long ttl(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.TTL, args, (cmd)->cmd.ttl(key), (v)->v);
	}

	@Override
	public Type type(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.TYPE, args, (cmd)->cmd.type(SafeEncoder.encode(key)), new TypeConverter());
	}

	@Override
	public Type type(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.TYPE, args, (cmd)->cmd.type(key), new TypeConverter());
	}

	@Override
	public Long unlink(final String... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return executeCommand(Command.UNLINK, args, (cmd)->cmd.unlink(SafeEncoder.encode(keys)), (v)->v);
	}

	@Override
	public Long unlink(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return executeCommand(Command.UNLINK, args, (cmd)->cmd.unlink(keys), (v)->v);
	}

	private Status copy(final String key, final String destKey, final Integer destinationDb, final boolean replace,
						final CommandArguments args) {
		return copy(SafeEncoder.encode(key), SafeEncoder.encode(destKey), destinationDb, replace, args);
	}

	private Status copy(final byte[] key, final byte[] destKey, final Integer destinationDb, final boolean replace,
						final CommandArguments args) {
		final CopyArgs copyArgs = CopyArgs.Builder.replace(replace);

		Optional.ofNullable(destinationDb).ifPresent(copyArgs::destinationDb);

		return executeCommand(Command.COPY, args, (cmd)->cmd.copy(key, destKey, copyArgs),
				new BooleanStatusConverter());
	}

	private Status migrate(final String host, final int port, final int db, final int timeout,
						   final MigrateArgs<byte[]> migrateArgs, final CommandArguments args) {
		return executeCommand(Command.MIGRATE, args, (cmd)->cmd.migrate(host, port, db, timeout, migrateArgs),
				new OkStatusConverter());
	}

	private Status pExpire(final byte[] key, final int lifetime, final ExpireOption expireOption,
						   final CommandArguments args) {
		final ExpireOptionConverter expireOptionConverter = new ExpireOptionConverter();
		return executeCommand(Command.PEXPIRE, args,
				(cmd)->cmd.pexpire(key, lifetime, expireOptionConverter.convert(expireOption)),
				new BooleanStatusConverter());
	}

	private Status pExpireAt(final byte[] key, final long unixTimestamp, final ExpireOption expireOption,
							 final CommandArguments args) {
		final ExpireOptionConverter expireOptionConverter = new ExpireOptionConverter();
		return executeCommand(Command.EXPIREAT, args,
				(cmd)->cmd.pexpireat(key, unixTimestamp, expireOptionConverter.convert(expireOption)),
				new BooleanStatusConverter());
	}

	private ScanResult<String> scan(final String cursor, final ScanArgs scanArgs, final CommandArguments args) {
		return executeCommand(Command.SCAN, args, (cmd)->cmd.scan(new LettuceScanCursor(cursor), scanArgs),
				new ScanCursorConverter.KeyScanCursorConverter<>(SafeEncoder::encode));
	}

	private ScanResult<byte[]> scan(final byte[] cursor, final ScanArgs scanArgs, final CommandArguments args) {
		return executeCommand(Command.SCAN, args, (cmd)->cmd.scan(new LettuceScanCursor(cursor), scanArgs),
				new ScanCursorConverter.KeyScanCursorConverter<>((v)->v));
	}

	private <V> List<V> sort(final byte[] key, final SortArgs sortArgs, final Converter<byte[], V> converter,
							 final CommandArguments args) {
		return executeCommand(Command.SORT, args, (cmd)->cmd.sort(key, sortArgs), new ListConverter<>(converter));
	}

	private Long sortStore(final byte[] key, final byte[] destKey, final SortArgs sortArgs,
						   final CommandArguments args) {
		return executeCommand(Command.SORT, args, (cmd)->cmd.sortStore(key, sortArgs, destKey), (v)->v);
	}

	private <V> List<V> sortRo(final byte[] key, final SortArgs sortArgs, final Converter<byte[], V> converter,
							   final CommandArguments args) {
		return executeCommand(Command.SORT_RO, args, (cmd)->cmd.sortReadOnly(key, sortArgs),
				new ListConverter<>(converter));
	}

}
