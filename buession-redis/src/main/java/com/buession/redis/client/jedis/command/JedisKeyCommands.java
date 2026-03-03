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
package com.buession.redis.client.jedis.command;

import com.buession.core.converter.BooleanStatusConverter;
import com.buession.lang.Status;
import com.buession.redis.client.jedis.JedisRedisClient;
import com.buession.redis.core.ExpireOption;
import com.buession.redis.core.Keyword;
import com.buession.redis.core.ObjectEncoding;
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.Type;
import com.buession.redis.core.command.Command;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.KeyCommands;
import com.buession.redis.core.command.SubCommand;
import com.buession.redis.core.command.args.MigrateArgument;
import com.buession.redis.core.command.args.RestoreArgument;
import com.buession.redis.core.command.args.SortArgument;
import com.buession.redis.core.internal.convert.jedis.params.ExpireOptionConverter;
import com.buession.redis.core.internal.convert.jedis.params.MigrateArgumentConverter;
import com.buession.redis.core.internal.convert.jedis.params.RestoreArgumentConverter;
import com.buession.redis.core.internal.convert.jedis.params.SortArgumentConverter;
import com.buession.redis.core.internal.convert.response.BinaryObjectEncodingConverter;
import com.buession.redis.core.internal.convert.response.ObjectEncodingConverter;
import com.buession.redis.core.internal.convert.jedis.response.ScanResultConverter;
import com.buession.redis.core.internal.convert.response.OkStatusConverter;
import com.buession.redis.core.internal.convert.response.OneStatusConverter;
import com.buession.redis.core.internal.convert.response.TypeConverter;
import com.buession.redis.core.internal.jedis.JedisScanParams;
import com.buession.redis.utils.SafeEncoder;
import redis.clients.jedis.params.MigrateParams;
import redis.clients.jedis.params.ScanParams;
import redis.clients.jedis.params.SortingParams;

import java.util.List;
import java.util.Set;

/**
 * Jedis Key 命令
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public final class JedisKeyCommands extends AbstractJedisRedisCommands implements KeyCommands {

	public JedisKeyCommands(final JedisRedisClient client) {
		super(client);
	}

	@Override
	public Status copy(final String key, final String destKey) {
		final CommandArguments args = CommandArguments.create(key, destKey);
		return copy(key, destKey, false, args);
	}

	@Override
	public Status copy(final byte[] key, final byte[] destKey) {
		final CommandArguments args = CommandArguments.create(key, destKey);
		return copy(key, destKey, false, args);
	}

	@Override
	public Status copy(final String key, final String destKey, final int db) {
		final CommandArguments args = CommandArguments.create(key, destKey).add(Keyword.Database.DB, db);
		return copy(key, destKey, false, args);
	}

	@Override
	public Status copy(final byte[] key, final byte[] destKey, final int db) {
		final CommandArguments args = CommandArguments.create(key, destKey).add(Keyword.Database.DB, db);
		return copy(key, destKey, false, args);
	}

	@Override
	public Status copy(final String key, final String destKey, final boolean replace) {
		final CommandArguments args = CommandArguments.create(key, destKey)
				.add(replace ? Keyword.Common.REPLACE : null);
		return copy(key, destKey, replace, args);
	}

	@Override
	public Status copy(final byte[] key, final byte[] destKey, final boolean replace) {
		final CommandArguments args = CommandArguments.create(key, destKey)
				.add(replace ? Keyword.Common.REPLACE : null);
		return copy(key, destKey, replace, args);
	}

	@Override
	public Status copy(final String key, final String destKey, final int db, final boolean replace) {
		final CommandArguments args = CommandArguments.create(key, destKey).add(Keyword.Database.DB, db)
				.add(replace ? Keyword.Common.REPLACE : null);
		return copy(key, destKey, replace, args);
	}

	@Override
	public Status copy(final byte[] key, final byte[] destKey, final int db, final boolean replace) {
		final CommandArguments args = CommandArguments.create(key, destKey).add(Keyword.Database.DB, db)
				.add(replace ? Keyword.Common.REPLACE : null);
		return copy(key, destKey, replace, args);
	}

	@Override
	public Long del(final String... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return executeCommand(Command.DEL, args, (cmd)->cmd.del(rawKeys(keys)), (v)->v);
	}

	@Override
	public Long del(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return executeCommand(Command.DEL, args, (cmd)->cmd.del(rawKeys(keys)), (v)->v);
	}

	@Override
	public String dump(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.DUMP, args, (cmd)->cmd.dump(rawKey(key)), SafeEncoder::encode);
	}

	@Override
	public byte[] dump(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.DUMP, args, (cmd)->cmd.dump(rawKey(key)), (v)->v);
	}

	@Override
	public Boolean exists(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.EXISTS, args, (cmd)->cmd.exists(rawKey(key)), (v)->v);
	}

	@Override
	public Boolean exists(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.EXISTS, args, (cmd)->cmd.exists(rawKey(key)), (v)->v);
	}

	@Override
	public Long exists(final String... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return executeCommand(Command.EXISTS, args, (cmd)->cmd.exists(rawKeys(keys)), (v)->v);
	}

	@Override
	public Long exists(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return executeCommand(Command.EXISTS, args, (cmd)->cmd.exists(rawKeys(keys)), (v)->v);
	}

	@Override
	public Status expire(final String key, final int lifetime) {
		final CommandArguments args = CommandArguments.create(key).add(lifetime);
		return executeCommand(Command.EXPIRE, args, (cmd)->cmd.expire(rawKey(key), lifetime), new OneStatusConverter());
	}

	@Override
	public Status expire(final byte[] key, final int lifetime) {
		final CommandArguments args = CommandArguments.create(key).add(lifetime);
		return executeCommand(Command.EXPIRE, args, (cmd)->cmd.expire(rawKey(key), lifetime), new OneStatusConverter());
	}

	@Override
	public Status expire(final String key, final int lifetime, final ExpireOption expireOption) {
		final CommandArguments args = CommandArguments.create(key).add(lifetime).add(expireOption);
		final ExpireOptionConverter expireOptionConverter = new ExpireOptionConverter();
		return executeCommand(Command.EXPIRE, args,
				(cmd)->cmd.expire(rawKey(key), lifetime, expireOptionConverter.convert(expireOption)),
				new OneStatusConverter());
	}

	@Override
	public Status expire(final byte[] key, final int lifetime, final ExpireOption expireOption) {
		final CommandArguments args = CommandArguments.create(key).add(lifetime).add(expireOption);
		final ExpireOptionConverter expireOptionConverter = new ExpireOptionConverter();
		return executeCommand(Command.EXPIRE, args,
				(cmd)->cmd.expire(rawKey(key), lifetime, expireOptionConverter.convert(expireOption)),
				new OneStatusConverter());
	}

	@Override
	public Status expireAt(final String key, final long unixTimestamp) {
		final CommandArguments args = CommandArguments.create(key).add(unixTimestamp);
		return executeCommand(Command.EXPIREAT, args, (cmd)->cmd.expireAt(rawKey(key), unixTimestamp),
				new OneStatusConverter());
	}

	@Override
	public Status expireAt(final byte[] key, final long unixTimestamp) {
		final CommandArguments args = CommandArguments.create(key).add(unixTimestamp);
		return executeCommand(Command.EXPIREAT, args, (cmd)->cmd.expireAt(rawKey(key), unixTimestamp),
				new OneStatusConverter());
	}

	@Override
	public Status expireAt(final String key, final long unixTimestamp, final ExpireOption expireOption) {
		final CommandArguments args = CommandArguments.create(key).add(unixTimestamp).add(expireOption);
		final ExpireOptionConverter expireOptionConverter = new ExpireOptionConverter();
		return executeCommand(Command.EXPIREAT, args,
				(cmd)->cmd.expireAt(rawKey(key), unixTimestamp, expireOptionConverter.convert(expireOption)),
				new OneStatusConverter());
	}

	@Override
	public Status expireAt(final byte[] key, final long unixTimestamp, final ExpireOption expireOption) {
		final CommandArguments args = CommandArguments.create(key).add(unixTimestamp).add(expireOption);
		final ExpireOptionConverter expireOptionConverter = new ExpireOptionConverter();
		return executeCommand(Command.EXPIREAT, args,
				(cmd)->cmd.expireAt(rawKey(key), unixTimestamp, expireOptionConverter.convert(expireOption)),
				new OneStatusConverter());
	}

	@Override
	public Long expireTime(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.EXPIRETIME, args, (cmd)->cmd.expireTime(rawKey(key)), (v)->v);
	}

	@Override
	public Long expireTime(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.EXPIRETIME, args, (cmd)->cmd.expireTime(rawKey(key)), (v)->v);
	}

	@Override
	public Set<String> keys(final String pattern) {
		final CommandArguments args = CommandArguments.create(pattern);
		return executeCommand(Command.KEYS, args, (cmd)->cmd.keys(pattern), (v)->v);
	}

	@Override
	public Set<byte[]> keys(final byte[] pattern) {
		final CommandArguments args = CommandArguments.create(pattern);
		return executeCommand(Command.KEYS, args, (cmd)->cmd.keys(pattern), (v)->v);
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final int timeout, final String... keys) {
		final CommandArguments args = CommandArguments.create(host).add(port).add("", db).add(timeout)
				.add(Keyword.Key.KEYS, keys);
		return migrate(host, port, db, timeout, keys, new MigrateParams(), args);
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final int timeout, final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(host).add(port).add("", db).add(timeout)
				.add(Keyword.Key.KEYS, keys);
		return migrate(host, port, db, timeout, keys, new MigrateParams(), args);
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final int timeout,
						  final MigrateArgument argument, final String... keys) {
		final CommandArguments args = CommandArguments.create(host).add(port).add("", db).add(timeout).add(argument)
				.add(keys);
		final MigrateArgumentConverter migrateArgumentConverter = new MigrateArgumentConverter();
		return migrate(host, port, db, timeout, keys, migrateArgumentConverter.convert(argument), args);
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final int timeout,
						  final MigrateArgument argument, final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(host).add(port).add("", db).add(timeout).add(argument)
				.add(keys);
		final MigrateArgumentConverter migrateArgumentConverter = new MigrateArgumentConverter();
		return migrate(host, port, db, timeout, keys, migrateArgumentConverter.convert(argument), args);
	}

	@Override
	public Status move(final String key, final int db) {
		final CommandArguments args = CommandArguments.create(key).add(db);
		return executeCommand(Command.MOVE, args);
	}

	@Override
	public Status move(final byte[] key, final int db) {
		final CommandArguments args = CommandArguments.create(key).add(db);
		return executeCommand(Command.MOVE, args);
	}

	@Override
	public ObjectEncoding objectEncoding(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.OBJECT, SubCommand.OBJECT_ENCODING, args, (cmd)->cmd.objectEncoding(rawKey(key)),
				new ObjectEncodingConverter());
	}

	@Override
	public ObjectEncoding objectEncoding(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.OBJECT, SubCommand.OBJECT_ENCODING, args, (cmd)->cmd.objectEncoding(rawKey(key)),
				new BinaryObjectEncodingConverter());
	}

	@Override
	public Long objectFreq(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.OBJECT, SubCommand.OBJECT_FREQ, args, (cmd)->cmd.objectFreq(rawKey(key)), (v)->v);
	}

	@Override
	public Long objectFreq(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.OBJECT, SubCommand.OBJECT_FREQ, args, (cmd)->cmd.objectFreq(rawKey(key)), (v)->v);
	}

	@Override
	public Long objectIdleTime(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.OBJECT, SubCommand.OBJECT_IDLETIME, args, (cmd)->cmd.objectFreq(rawKey(key)),
				(v)->v);
	}

	@Override
	public Long objectIdleTime(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.OBJECT, SubCommand.OBJECT_IDLETIME, args, (cmd)->cmd.objectFreq(rawKey(key)),
				(v)->v);
	}

	@Override
	public Long objectRefcount(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.OBJECT, SubCommand.OBJECT_REFCOUNT, args, (cmd)->cmd.objectRefcount(rawKey(key)),
				(v)->v);
	}

	@Override
	public Long objectRefcount(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.OBJECT, SubCommand.OBJECT_REFCOUNT, args, (cmd)->cmd.objectRefcount(rawKey(key)),
				(v)->v);
	}

	@Override
	public Status persist(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.PERSIST, args, (cmd)->cmd.persist(rawKey(key)), new OneStatusConverter());
	}

	@Override
	public Status persist(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.PERSIST, args, (cmd)->cmd.persist(rawKey(key)), new OneStatusConverter());
	}

	@Override
	public Status pExpire(final String key, final int lifetime) {
		final CommandArguments args = CommandArguments.create(key).add(lifetime);
		return executeCommand(Command.PEXPIRE, args, (cmd)->cmd.pexpire(rawKey(key), lifetime),
				new OneStatusConverter());
	}

	@Override
	public Status pExpire(final byte[] key, final int lifetime) {
		final CommandArguments args = CommandArguments.create(key).add(lifetime);
		return executeCommand(Command.PEXPIRE, args, (cmd)->cmd.pexpire(rawKey(key), lifetime),
				new OneStatusConverter());
	}

	@Override
	public Status pExpire(final String key, final int lifetime, final ExpireOption expireOption) {
		final CommandArguments args = CommandArguments.create(key).add(lifetime).add(expireOption);
		final ExpireOptionConverter expireOptionConverter = new ExpireOptionConverter();
		return executeCommand(Command.PEXPIRE, args,
				(cmd)->cmd.pexpire(rawKey(key), lifetime, expireOptionConverter.convert(expireOption)),
				new OneStatusConverter());
	}

	@Override
	public Status pExpire(final byte[] key, final int lifetime, final ExpireOption expireOption) {
		final CommandArguments args = CommandArguments.create(key).add(lifetime).add(expireOption);
		final ExpireOptionConverter expireOptionConverter = new ExpireOptionConverter();
		return executeCommand(Command.PEXPIRE, args,
				(cmd)->cmd.pexpire(rawKey(key), lifetime, expireOptionConverter.convert(expireOption)),
				new OneStatusConverter());
	}

	@Override
	public Status pExpireAt(final String key, final long unixTimestamp) {
		final CommandArguments args = CommandArguments.create(key).add(unixTimestamp);
		return executeCommand(Command.PEXPIREAT, args, (cmd)->cmd.pexpireAt(rawKey(key), unixTimestamp),
				new OneStatusConverter());
	}

	@Override
	public Status pExpireAt(final byte[] key, final long unixTimestamp) {
		final CommandArguments args = CommandArguments.create(key).add(unixTimestamp);
		return executeCommand(Command.PEXPIREAT, args, (cmd)->cmd.pexpireAt(rawKey(key), unixTimestamp),
				new OneStatusConverter());
	}

	@Override
	public Status pExpireAt(final String key, final long unixTimestamp, final ExpireOption expireOption) {
		final CommandArguments args = CommandArguments.create(key).add(unixTimestamp).add(expireOption);
		final ExpireOptionConverter expireOptionConverter = new ExpireOptionConverter();
		return executeCommand(Command.PEXPIREAT, args,
				(cmd)->cmd.pexpireAt(rawKey(key), unixTimestamp, expireOptionConverter.convert(expireOption)),
				new OneStatusConverter());
	}

	@Override
	public Status pExpireAt(final byte[] key, final long unixTimestamp, final ExpireOption expireOption) {
		final CommandArguments args = CommandArguments.create(key).add(unixTimestamp).add(expireOption);
		final ExpireOptionConverter expireOptionConverter = new ExpireOptionConverter();
		return executeCommand(Command.PEXPIREAT, args,
				(cmd)->cmd.pexpireAt(rawKey(key), unixTimestamp, expireOptionConverter.convert(expireOption)),
				new OneStatusConverter());
	}

	@Override
	public Long pExpireTime(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.PEXPIRETIME, args, (cmd)->cmd.pexpireTime(rawKey(key)), (v)->v);
	}

	@Override
	public Long pExpireTime(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.PEXPIRETIME, args, (cmd)->cmd.pexpireTime(rawKey(key)), (v)->v);
	}

	@Override
	public Long pTtl(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.PTTL, args, (cmd)->cmd.pttl(rawKey(key)), (v)->v);
	}

	@Override
	public Long pTtl(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.PTTL, args, (cmd)->cmd.pttl(rawKey(key)), (v)->v);
	}

	@Override
	public String randomKey() {
		return executeCommand(Command.RANDOMKEY, (cmd)->cmd.randomKey(), (v)->v);
	}

	@Override
	public Status rename(final String key, final String newKey) {
		final CommandArguments args = CommandArguments.create(key).add(newKey);
		return executeCommand(Command.RENAME, args, (cmd)->cmd.rename(rawKey(key), newKey), new OkStatusConverter());
	}

	@Override
	public Status rename(final byte[] key, final byte[] newKey) {
		final CommandArguments args = CommandArguments.create(key).add(newKey);
		return executeCommand(Command.RENAME, args, (cmd)->cmd.rename(rawKey(key), newKey), new OkStatusConverter());
	}

	@Override
	public Status renameNx(final String key, final String newKey) {
		final CommandArguments args = CommandArguments.create(key).add(newKey);
		return executeCommand(Command.RENAMENX, args, (cmd)->cmd.renamenx(rawKey(key), newKey),
				new OneStatusConverter());
	}

	@Override
	public Status renameNx(final byte[] key, final byte[] newKey) {
		final CommandArguments args = CommandArguments.create(key).add(newKey);
		return executeCommand(Command.RENAMENX, args, (cmd)->cmd.renamenx(rawKey(key), newKey),
				new OneStatusConverter());
	}

	@Override
	public Status restore(final String key, final byte[] serializedValue, final int ttl) {
		final CommandArguments args = CommandArguments.create(key).add(serializedValue).add(ttl);
		return executeCommand(Command.RESTORE, args, (cmd)->cmd.restore(rawKey(key), ttl, serializedValue),
				new OkStatusConverter());
	}

	@Override
	public Status restore(final byte[] key, final byte[] serializedValue, final int ttl) {
		final CommandArguments args = CommandArguments.create(key).add(serializedValue).add(ttl);
		return executeCommand(Command.RESTORE, args, (cmd)->cmd.restore(rawKey(key), ttl, serializedValue),
				new OkStatusConverter());
	}

	@Override
	public Status restore(final String key, final byte[] serializedValue, final int ttl,
						  final RestoreArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(serializedValue).add(ttl).add(argument);
		final RestoreArgumentConverter restoreArgumentConverter = new RestoreArgumentConverter();
		return executeCommand(Command.RESTORE, args,
				(cmd)->cmd.restore(rawKey(key), ttl, serializedValue, restoreArgumentConverter.convert(argument)),
				new OkStatusConverter());
	}

	@Override
	public Status restore(final byte[] key, final byte[] serializedValue, final int ttl,
						  final RestoreArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(serializedValue).add(ttl).add(argument);
		final RestoreArgumentConverter restoreArgumentConverter = new RestoreArgumentConverter();
		return executeCommand(Command.RESTORE, args,
				(cmd)->cmd.restore(rawKey(key), ttl, serializedValue, restoreArgumentConverter.convert(argument)),
				new OkStatusConverter());
	}

	@Override
	public ScanResult<String> scan(final String cursor) {
		final CommandArguments args = CommandArguments.create(cursor);
		return executeCommand(Command.SCAN, args, (cmd)->cmd.scan(cursor), new ScanResultConverter<>((k)->k));
	}

	@Override
	public ScanResult<byte[]> scan(final byte[] cursor) {
		final CommandArguments args = CommandArguments.create(cursor);
		return executeCommand(Command.SCAN, args, (cmd)->cmd.scan(cursor), new ScanResultConverter<>((k)->k));
	}

	@Override
	public ScanResult<String> scan(final String cursor, final String pattern) {
		final CommandArguments args = CommandArguments.create(cursor).add(Keyword.Scan.MATCH, pattern);
		return scan(cursor, new JedisScanParams(pattern), args);
	}

	@Override
	public ScanResult<byte[]> scan(final byte[] cursor, final byte[] pattern) {
		final CommandArguments args = CommandArguments.create(cursor).add(Keyword.Scan.MATCH, pattern);
		return scan(cursor, new JedisScanParams(pattern), args);
	}

	@Override
	public ScanResult<String> scan(final String cursor, final int count) {
		final CommandArguments args = CommandArguments.create(cursor).add(Keyword.Common.COUNT, count);
		return scan(cursor, new JedisScanParams(count), args);
	}

	@Override
	public ScanResult<byte[]> scan(final byte[] cursor, final int count) {
		final CommandArguments args = CommandArguments.create(cursor).add(Keyword.Common.COUNT, count);
		return scan(cursor, new JedisScanParams(count), args);
	}

	@Override
	public ScanResult<String> scan(final String cursor, final String pattern, final int count) {
		final CommandArguments args = CommandArguments.create(cursor).add(Keyword.Scan.MATCH, pattern)
				.add(Keyword.Common.COUNT, count);
		return scan(cursor, new JedisScanParams(pattern, count), args);
	}

	@Override
	public ScanResult<byte[]> scan(final byte[] cursor, final byte[] pattern, final int count) {
		final CommandArguments args = CommandArguments.create(cursor).add(Keyword.Scan.MATCH, pattern)
				.add(Keyword.Common.COUNT, count);
		return scan(cursor, new JedisScanParams(pattern, count), args);
	}

	@Override
	public List<String> sort(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.SORT, args, (cmd)->cmd.sort(rawKey(key)), (v)->v);
	}

	@Override
	public List<byte[]> sort(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.SORT, args, (cmd)->cmd.sort(rawKey(key)), (v)->v);
	}

	@Override
	public List<String> sort(final String key, final SortArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(argument);
		final SortArgumentConverter sortArgumentConverter = new SortArgumentConverter();
		return executeCommand(Command.SORT, args, (cmd)->cmd.sort(rawKey(key), sortArgumentConverter.convert(argument)),
				(v)->v);
	}

	@Override
	public List<byte[]> sort(final byte[] key, final SortArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(argument);
		final SortArgumentConverter sortArgumentConverter = new SortArgumentConverter();
		return executeCommand(Command.SORT, args, (cmd)->cmd.sort(rawKey(key), sortArgumentConverter.convert(argument)),
				(v)->v);
	}

	@Override
	public Long sort(final String key, final String destKey) {
		final CommandArguments args = CommandArguments.create(key).add("STORE", destKey);
		return executeCommand(Command.SORT, args, (cmd)->cmd.sort(rawKey(key), rawKey(destKey)), (v)->v);
	}

	@Override
	public Long sort(final byte[] key, final byte[] destKey) {
		final CommandArguments args = CommandArguments.create(key).add("STORE", destKey);
		return executeCommand(Command.SORT, args, (cmd)->cmd.sort(rawKey(key), rawKey(destKey)), (v)->v);
	}

	@Override
	public Long sort(final String key, final String destKey, final SortArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(argument).add("STORE", destKey);
		final SortArgumentConverter sortArgumentConverter = new SortArgumentConverter();
		return executeCommand(Command.SORT, args,
				(cmd)->cmd.sort(rawKey(key), sortArgumentConverter.convert(argument), rawKey(destKey)), (v)->v);
	}

	@Override
	public Long sort(final byte[] key, final byte[] destKey, final SortArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(argument).add("STORE", destKey);
		final SortArgumentConverter sortArgumentConverter = new SortArgumentConverter();
		return executeCommand(Command.SORT, args,
				(cmd)->cmd.sort(rawKey(key), sortArgumentConverter.convert(argument), rawKey(destKey)), (v)->v);
	}

	@Override
	public List<String> sortRo(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.SORT_RO, args, (cmd)->cmd.sortReadonly(rawKey(key), new SortingParams()), (v)->v);
	}

	@Override
	public List<byte[]> sortRo(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.SORT_RO, args, (cmd)->cmd.sortReadonly(rawKey(key), new SortingParams()), (v)->v);
	}

	@Override
	public List<String> sortRo(final String key, final SortArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(argument);
		final SortArgumentConverter sortArgumentConverter = new SortArgumentConverter();
		return executeCommand(Command.SORT_RO, args,
				(cmd)->cmd.sortReadonly(rawKey(key), sortArgumentConverter.convert(argument)), (v)->v);
	}

	@Override
	public List<byte[]> sortRo(final byte[] key, final SortArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(argument);
		final SortArgumentConverter sortArgumentConverter = new SortArgumentConverter();
		return executeCommand(Command.SORT_RO, args, (cmd)->cmd.sortReadonly(rawKey(key),
				sortArgumentConverter.convert(argument)), (v)->v);
	}

	@Override
	public Long touch(final String... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return executeCommand(Command.TOUCH, args, (cmd)->cmd.touch(rawKeys(keys)), (v)->v);
	}

	@Override
	public Long touch(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return executeCommand(Command.TOUCH, args, (cmd)->cmd.touch(rawKeys(keys)), (v)->v);
	}

	@Override
	public Long ttl(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.TTL, args, (cmd)->cmd.ttl(rawKey(key)), (v)->v);
	}

	@Override
	public Long ttl(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.TTL, args, (cmd)->cmd.ttl(rawKey(key)), (v)->v);
	}

	@Override
	public Type type(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.TYPE, args, (cmd)->cmd.type(rawKey(key)), new TypeConverter());
	}

	@Override
	public Type type(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.TYPE, args, (cmd)->cmd.type(rawKey(key)), new TypeConverter());
	}

	@Override
	public Long unlink(final String... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return executeCommand(Command.UNLINK, args, (cmd)->cmd.unlink(rawKeys(keys)), (v)->v);
	}

	@Override
	public Long unlink(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return executeCommand(Command.UNLINK, args, (cmd)->cmd.unlink(rawKeys(keys)), (v)->v);
	}

	private Status copy(final String key, final String destKey, final boolean replace, final CommandArguments args) {
		return executeCommand(Command.COPY, args, (cmd)->cmd.copy(rawKey(key), rawKey(destKey), replace),
				new BooleanStatusConverter());
	}

	private Status copy(final byte[] key, final byte[] destKey, final boolean replace, final CommandArguments args) {
		return executeCommand(Command.COPY, args, (cmd)->cmd.copy(rawKey(key), rawKey(destKey), replace),
				new BooleanStatusConverter());
	}

	private Status migrate(final String host, final int port, final int db, final int timeout, final String[] keys,
						   final MigrateParams migrateParams, final CommandArguments args) {
		return executeCommand(Command.MIGRATE, args,
				(cmd)->cmd.migrate(host, port, timeout, migrateParams, rawKeys(keys)),
				new OkStatusConverter());
	}

	private Status migrate(final String host, final int port, final int db, final int timeout, final byte[][] keys,
						   final MigrateParams migrateParams, final CommandArguments args) {
		return executeCommand(Command.MIGRATE, args,
				(cmd)->cmd.migrate(host, port, timeout, migrateParams, rawKeys(keys)),
				new OkStatusConverter());
	}

	private ScanResult<String> scan(final String cursor, final ScanParams scanParams, final CommandArguments args) {
		return executeCommand(Command.SCAN, args, (cmd)->cmd.scan(cursor, scanParams),
				new ScanResultConverter<>((k)->k));
	}

	private ScanResult<byte[]> scan(final byte[] cursor, final ScanParams scanParams, final CommandArguments args) {
		return executeCommand(Command.SCAN, args, (cmd)->cmd.scan(cursor, scanParams),
				new ScanResultConverter<>((k)->k));
	}

}
