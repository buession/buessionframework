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

import com.buession.core.converter.Converter;
import com.buession.core.converter.ListSetConverter;
import com.buession.lang.Status;
import com.buession.redis.client.lettuce.LettuceStandaloneClient;
import com.buession.redis.core.ExpireOption;
import com.buession.redis.core.MigrateOperation;
import com.buession.redis.core.ObjectEncoding;
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.Type;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.internal.convert.Converters;
import com.buession.redis.core.internal.convert.lettuce.response.ScanCursorConverter;
import com.buession.redis.core.internal.convert.response.ObjectEncodingConverter;
import com.buession.redis.core.internal.convert.response.TypeConverter;
import com.buession.redis.core.internal.lettuce.LettuceMigrateArgs;
import com.buession.redis.core.internal.lettuce.LettuceRestoreArgs;
import com.buession.redis.core.internal.lettuce.LettuceScanArgs;
import com.buession.redis.core.internal.lettuce.LettuceScanCursor;
import com.buession.redis.core.internal.lettuce.LettuceSortArgs;
import com.buession.redis.utils.SafeEncoder;
import io.lettuce.core.KeyScanCursor;
import io.lettuce.core.MigrateArgs;
import io.lettuce.core.RestoreArgs;
import io.lettuce.core.ScanArgs;
import io.lettuce.core.ScanCursor;
import io.lettuce.core.SortArgs;

import java.util.List;
import java.util.Set;

/**
 * Lettuce 单机模式 Key 命令操作
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public final class LettuceKeyOperations extends AbstractKeyOperations<LettuceStandaloneClient> {

	public LettuceKeyOperations(final LettuceStandaloneClient client) {
		super(client);
	}

	@Override
	public Long del(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(keys);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.DEL, (cmd)->cmd.del(keys), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.DEL, (cmd)->cmd.del(keys), (v)->v)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.DEL, (cmd)->cmd.del(keys), (v)->v)
					.run(args);
		}
	}

	@Override
	public byte[] dump(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.DUMP, (cmd)->cmd.dump(key), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.DUMP, (cmd)->cmd.dump(key), (v)->v)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.DUMP, (cmd)->cmd.dump(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public Boolean exists(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.EXISTS, (cmd)->cmd.exists(key), (v)->v == 1L)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.EXISTS, (cmd)->cmd.exists(key), (v)->v == 1L)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.EXISTS, (cmd)->cmd.exists(key), (v)->v == 1L)
					.run(args);
		}
	}

	@Override
	public Long exists(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(keys);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.EXISTS, (cmd)->cmd.exists(keys), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.EXISTS, (cmd)->cmd.exists(keys), (v)->v)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.EXISTS, (cmd)->cmd.exists(keys), (v)->v)
					.run(args);
		}
	}

	@Override
	public Status expire(final byte[] key, final int lifetime) {
		final CommandArguments args = CommandArguments.create(key).add(lifetime);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.EXPIRE, (cmd)->cmd.expire(key, lifetime),
					booleanStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.EXPIRE, (cmd)->cmd.expire(key, lifetime),
					booleanStatusConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.EXPIRE,
					(cmd)->cmd.expire(key, lifetime), booleanStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status expire(final byte[] key, final int lifetime, final ExpireOption expireOption) {
		final CommandArguments args = CommandArguments.create(key).add(lifetime)
				.add(expireOption);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.EXPIRE, (cmd)->cmd.expire(key, lifetime),
					booleanStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.EXPIRE, (cmd)->cmd.expire(key, lifetime),
					booleanStatusConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.EXPIRE, (cmd)->cmd.expire(key, lifetime),
					booleanStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status expireAt(final byte[] key, final long unixTimestamp) {
		final CommandArguments args = CommandArguments.create(key).add(unixTimestamp);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.EXPIREAT,
					(cmd)->cmd.expireat(key, unixTimestamp), booleanStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.EXPIREAT,
					(cmd)->cmd.expireat(key, unixTimestamp), booleanStatusConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.EXPIREAT, (cmd)->cmd.expireat(key, unixTimestamp),
					booleanStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status pExpire(final byte[] key, final int lifetime) {
		final CommandArguments args = CommandArguments.create(key).add(lifetime);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.PEXPIRE, (cmd)->cmd.pexpire(key, lifetime),
					booleanStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.PEXPIRE, (cmd)->cmd.pexpire(key, lifetime),
					booleanStatusConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.PEXPIRE, (cmd)->cmd.pexpire(key, lifetime),
					booleanStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status pExpireAt(final byte[] key, final long unixTimestamp) {
		final CommandArguments args = CommandArguments.create(key).add(unixTimestamp);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.EXPIREAT,
					(cmd)->cmd.pexpireat(key, unixTimestamp), booleanStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.EXPIREAT,
					(cmd)->cmd.pexpireat(key, unixTimestamp), booleanStatusConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.EXPIREAT,
					(cmd)->cmd.pexpireat(key, unixTimestamp), booleanStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status persist(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.PERSIST, (cmd)->cmd.persist(key),
					booleanStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.PERSIST, (cmd)->cmd.persist(key),
					booleanStatusConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.PERSIST, (cmd)->cmd.persist(key),
					booleanStatusConverter)
					.run(args);
		}
	}

	@Override
	public Long ttl(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.TTL, (cmd)->cmd.ttl(key), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.TTL, (cmd)->cmd.ttl(key), (v)->v)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.TTL, (cmd)->cmd.ttl(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long pTtl(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.PTTL, (cmd)->cmd.pttl(key), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.PTTL, (cmd)->cmd.pttl(key), (v)->v)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.PTTL, (cmd)->cmd.pttl(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public Status copy(final String key, final String destKey) {
		final CommandArguments args = CommandArguments.create(key).add(destKey);
		return copy(args);
	}

	@Override
	public Status copy(final byte[] key, final byte[] destKey) {
		final CommandArguments args = CommandArguments.create(key).add(destKey);
		return copy(args);
	}

	@Override
	public Status copy(final String key, final String destKey, final int db) {
		final CommandArguments args = CommandArguments.create(key).add(destKey)
				.add(db);
		return copy(args);
	}

	@Override
	public Status copy(final byte[] key, final byte[] destKey, final int db) {
		final CommandArguments args = CommandArguments.create(key).add(destKey)
				.add(db);
		return copy(args);
	}

	@Override
	public Status copy(final String key, final String destKey, final boolean replace) {
		final CommandArguments args = CommandArguments.create(key).add(destKey)
				.add(replace);
		return copy(args);
	}

	@Override
	public Status copy(final byte[] key, final byte[] destKey, final boolean replace) {
		final CommandArguments args = CommandArguments.create(key).add(destKey)
				.add(replace);
		return copy(args);
	}

	@Override
	public Status copy(final String key, final String destKey, final int db, final boolean replace) {
		final CommandArguments args = CommandArguments.create(key).add(destKey)
				.add(db)
				.add(replace);
		return copy(args);
	}

	@Override
	public Status copy(final byte[] key, final byte[] destKey, final int db, final boolean replace) {
		final CommandArguments args = CommandArguments.create(key).add(destKey)
				.add(db).add(replace);
		return copy(args);
	}

	@Override
	public Status move(final byte[] key, final int db) {
		final CommandArguments args = CommandArguments.create(key).add(db);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.MOVE, (cmd)->cmd.move(key, db),
					booleanStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.MOVE, (cmd)->cmd.move(key, db),
					booleanStatusConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.MOVE, (cmd)->cmd.move(key, db),
					booleanStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final int timeout, final byte[]...
			keys) {
		final CommandArguments args = CommandArguments.create(host).add(port).add(db)
				.add(timeout).add(keys);
		final MigrateArgs<byte[]> migrateArgs = new LettuceMigrateArgs<>(keys);

		return migrate(host, port, db, timeout, migrateArgs, args);
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final int timeout,
						  final MigrateOperation operation, final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(host).add(port).add(db)
				.add(timeout).add(operation).add(keys);
		final MigrateArgs<byte[]> migrateArgs = new LettuceMigrateArgs<>(operation, keys);

		return migrate(host, port, db, timeout, migrateArgs, args);
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final byte[] password,
						  final int timeout,
						  final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(host).add(port).add(db)
				.add(password).add(timeout).add(keys);
		final MigrateArgs<byte[]> migrateArgs = new LettuceMigrateArgs<>(keys, password);

		return migrate(host, port, db, timeout, migrateArgs, args);
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final byte[] password,
						  final int timeout,
						  final MigrateOperation operation, final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(host).add(port).add(db)
				.add(password).add(timeout).add(operation)
				.add(keys);
		final MigrateArgs<byte[]> migrateArgs = new LettuceMigrateArgs<>(operation, keys, password);

		return migrate(host, port, db, timeout, migrateArgs, args);
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final byte[] user,
						  final byte[] password,
						  final int timeout, final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(host).add(port).add(db)
				.add(user).add(password).add(timeout)
				.add(keys);
		final MigrateArgs<byte[]> migrateArgs = new LettuceMigrateArgs<>(keys, user, password);

		return migrate(host, port, db, timeout, migrateArgs, args);
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final byte[] user,
						  final byte[] password,
						  final int timeout, final MigrateOperation operation, final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(host).add(port).add(db)
				.add(user).add(password).add(timeout)
				.add(operation)
				.add(keys);
		final MigrateArgs<byte[]> migrateArgs = new LettuceMigrateArgs<>(keys, user, password);

		return migrate(host, port, db, timeout, migrateArgs, args);
	}

	@Override
	public Set<String> keys(final String pattern) {
		final CommandArguments args = CommandArguments.create(pattern);
		final byte[] bPattern = SafeEncoder.encode(pattern);
		final ListSetConverter<byte[], String> binaryToStringListSetConverter =
				Converters.listSetBinaryToString();

		return keys(bPattern, binaryToStringListSetConverter, args);
	}

	@Override
	public Set<byte[]> keys(final byte[] pattern) {
		final CommandArguments args = CommandArguments.create(pattern);
		final ListSetConverter<byte[], byte[]> converter = new ListSetConverter<>((v)->v);

		return keys(pattern, converter, args);
	}

	@Override
	public String randomKey() {
		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.RANDOMKEY, (cmd)->cmd.randomkey(),
					SafeEncoder::encode)
					.run();
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.RANDOMKEY, (cmd)->cmd.randomkey(),
					SafeEncoder::encode)
					.run();
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.RANDOMKEY, (cmd)->cmd.randomkey(),
					SafeEncoder::encode)
					.run();
		}
	}

	@Override
	public Status rename(final byte[] key, final byte[] newKey) {
		final CommandArguments args = CommandArguments.create(key).add(newKey);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.RENAME, (cmd)->cmd.rename(key, newKey),
					okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.RENAME, (cmd)->cmd.rename(key, newKey),
					okStatusConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.RENAME, (cmd)->cmd.rename(key, newKey),
					okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status renameNx(final byte[] key, final byte[] newKey) {
		final CommandArguments args = CommandArguments.create(key).add(newKey);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.RENAMENX, (cmd)->cmd.renamenx(key, newKey),
					booleanStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.RENAMENX, (cmd)->cmd.renamenx(key, newKey),
					booleanStatusConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.RENAMENX, (cmd)->cmd.renamenx(key, newKey),
					booleanStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status restore(final byte[] key, final byte[] serializedValue, final int ttl) {
		final CommandArguments args = CommandArguments.create(key).add(serializedValue)
				.add(ttl);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.RESTORE,
					(cmd)->cmd.restore(key, ttl, serializedValue), okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.RESTORE,
					(cmd)->cmd.restore(key, ttl, serializedValue), okStatusConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.RESTORE,
					(cmd)->cmd.restore(key, ttl, serializedValue), okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status restore(final byte[] key, final byte[] serializedValue, final int ttl,
						  final RestoreArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(serializedValue)
				.add(ttl).add(argument);
		final RestoreArgs restoreArgs = LettuceRestoreArgs.from(argument).ttl(ttl);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.RESTORE,
					(cmd)->cmd.restore(key, serializedValue, restoreArgs), okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.RESTORE,
					(cmd)->cmd.restore(key, serializedValue, restoreArgs), okStatusConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.RESTORE,
					(cmd)->cmd.restore(key, serializedValue, restoreArgs), okStatusConverter)
					.run(args);
		}
	}

	@Override
	public ScanResult<List<String>> scan(final String cursor) {
		final CommandArguments args = CommandArguments.create(cursor);
		final ScanCursor scanCursor = new LettuceScanCursor(cursor);
		final ScanCursorConverter.KeyScanCursorConverter.BSKeyScanCursorConverter bsKeyScanCursorConverter =
				new ScanCursorConverter.KeyScanCursorConverter.BSKeyScanCursorConverter();

		return scan(scanCursor, bsKeyScanCursorConverter, args);
	}

	@Override
	public ScanResult<List<byte[]>> scan(final byte[] cursor) {
		final CommandArguments args = CommandArguments.create(cursor);
		final ScanCursor scanCursor = new LettuceScanCursor(cursor);
		final ScanCursorConverter.KeyScanCursorConverter<byte[]> keyScanCursorConverter = new ScanCursorConverter.KeyScanCursorConverter<>();

		return scan(scanCursor, keyScanCursorConverter, args);
	}

	@Override
	public ScanResult<List<String>> scan(final String cursor, final String pattern) {
		final CommandArguments args = CommandArguments.create(cursor).add(pattern);
		final ScanCursor scanCursor = new LettuceScanCursor(cursor);
		final ScanArgs scanArgs = new LettuceScanArgs(pattern);
		final ScanCursorConverter.KeyScanCursorConverter.BSKeyScanCursorConverter bsKeyScanCursorConverter =
				new ScanCursorConverter.KeyScanCursorConverter.BSKeyScanCursorConverter();

		return scan(scanCursor, scanArgs, bsKeyScanCursorConverter, args);
	}

	@Override
	public ScanResult<List<byte[]>> scan(final byte[] cursor, final byte[] pattern) {
		final CommandArguments args = CommandArguments.create(cursor).add(pattern);
		final ScanCursor scanCursor = new LettuceScanCursor(cursor);
		final ScanArgs scanArgs = new LettuceScanArgs(pattern);
		final ScanCursorConverter.KeyScanCursorConverter<byte[]> keyScanCursorConverter = new ScanCursorConverter.KeyScanCursorConverter<>();

		return scan(scanCursor, scanArgs, keyScanCursorConverter, args);
	}

	@Override
	public ScanResult<List<String>> scan(final String cursor, final long count) {
		final CommandArguments args = CommandArguments.create(cursor).add(count);
		final ScanCursor scanCursor = new LettuceScanCursor(cursor);
		final ScanArgs scanArgs = new LettuceScanArgs(count);
		final ScanCursorConverter.KeyScanCursorConverter.BSKeyScanCursorConverter bsKeyScanCursorConverter =
				new ScanCursorConverter.KeyScanCursorConverter.BSKeyScanCursorConverter();

		return scan(scanCursor, scanArgs, bsKeyScanCursorConverter, args);
	}

	@Override
	public ScanResult<List<byte[]>> scan(final byte[] cursor, final long count) {
		final CommandArguments args = CommandArguments.create(cursor).add(count);
		final ScanCursor scanCursor = new LettuceScanCursor(cursor);
		final ScanArgs scanArgs = new LettuceScanArgs(count);
		final ScanCursorConverter.KeyScanCursorConverter<byte[]> keyScanCursorConverter = new ScanCursorConverter.KeyScanCursorConverter<>();

		return scan(scanCursor, scanArgs, keyScanCursorConverter, args);
	}

	@Override
	public ScanResult<List<String>> scan(final String cursor, final String pattern, final long count) {
		final CommandArguments args = CommandArguments.create(cursor).add(pattern)
				.add(count);
		final ScanCursor scanCursor = new LettuceScanCursor(cursor);
		final ScanArgs scanArgs = new LettuceScanArgs(pattern, count);
		final ScanCursorConverter.KeyScanCursorConverter.BSKeyScanCursorConverter bsKeyScanCursorConverter =
				new ScanCursorConverter.KeyScanCursorConverter.BSKeyScanCursorConverter();

		return scan(scanCursor, scanArgs, bsKeyScanCursorConverter, args);
	}

	@Override
	public ScanResult<List<byte[]>> scan(final byte[] cursor, final byte[] pattern, final long count) {
		final CommandArguments args = CommandArguments.create(cursor).add(pattern)
				.add(count);
		final ScanCursor scanCursor = new LettuceScanCursor(cursor);
		final ScanArgs scanArgs = new LettuceScanArgs(pattern, count);
		final ScanCursorConverter.KeyScanCursorConverter<byte[]> keyScanCursorConverter = new ScanCursorConverter.KeyScanCursorConverter<>();

		return scan(scanCursor, scanArgs, keyScanCursorConverter, args);
	}

	@Override
	public List<String> sort(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		final byte[] bKey = SafeEncoder.encode(key);

		return sort(bKey, binaryToStringListConverter, args);
	}

	@Override
	public List<byte[]> sort(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return sort(key, (v)->v, args);
	}

	@Override
	public List<String> sort(final String key, final SortArgument sortArgument) {
		final CommandArguments args = CommandArguments.create(key).add(sortArgument);
		final byte[] bKey = SafeEncoder.encode(key);
		final SortArgs sortArgs = LettuceSortArgs.from(sortArgument);

		return sort(bKey, sortArgs, binaryToStringListConverter, args);
	}

	@Override
	public List<byte[]> sort(final byte[] key, final SortArgument sortArgument) {
		final CommandArguments args = CommandArguments.create(key).add(sortArgument);
		final SortArgs sortArgs = LettuceSortArgs.from(sortArgument);

		return sort(key, sortArgs, (v)->v, args);
	}

	@Override
	public Long sort(final byte[] key, final byte[] destKey) {
		final CommandArguments args = CommandArguments.create(key).add(destKey);
		final SortArgs sortArgs = new LettuceSortArgs();

		return sortStore(key, destKey, sortArgs, args);
	}

	@Override
	public Long sort(final byte[] key, final byte[] destKey, final SortArgument sortArgument) {
		final CommandArguments args = CommandArguments.create(key).add(destKey)
				.add(sortArgument);
		final SortArgs sortArgs = LettuceSortArgs.from(sortArgument);

		return sortStore(key, destKey, sortArgs, args);
	}

	@Override
	public Long touch(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(keys);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.TOUCH, (cmd)->cmd.touch(keys), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.TOUCH, (cmd)->cmd.touch(keys), (v)->v)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.TOUCH, (cmd)->cmd.touch(keys), (v)->v)
					.run(args);
		}
	}

	@Override
	public Type type(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		final TypeConverter typeConverter = new TypeConverter();

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.TYPE, (cmd)->cmd.type(key), typeConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.TYPE, (cmd)->cmd.type(key), typeConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.TYPE, (cmd)->cmd.type(key), typeConverter)
					.run(args);
		}
	}

	@Override
	public Long unlink(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(keys);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.UNLINK, (cmd)->cmd.unlink(keys), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.UNLINK, (cmd)->cmd.unlink(keys), (v)->v)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.UNLINK, (cmd)->cmd.unlink(keys), (v)->v)
					.run(args);
		}
	}

	@Override
	public ObjectEncoding objectEncoding(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		final ObjectEncodingConverter objectEncodingConverter = new ObjectEncodingConverter();

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.OBJECT_ENCODING,
					(cmd)->cmd.objectEncoding(key), objectEncodingConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.OBJECT_ENCODING,
					(cmd)->cmd.objectEncoding(key), objectEncodingConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.OBJECT_ENCODING,
					(cmd)->cmd.objectEncoding(key), objectEncodingConverter)
					.run(args);
		}
	}

	@Override
	public Long objectFreq(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);

		if(isPipeline()){
			return new LettucePipelineCommand<Long, Long>(client, ProtocolCommand.OBJECT_REFQ)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<Long, Long>(client, ProtocolCommand.OBJECT_REFQ)
					.run(args);
		}else{
			return new LettuceCommand<Long, Long>(client, ProtocolCommand.OBJECT_REFQ)
					.run(args);
		}
	}

	@Override
	public Long objectIdleTime(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.OBJECT_IDLETIME,
					(cmd)->cmd.objectIdletime(key), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.OBJECT_IDLETIME,
					(cmd)->cmd.objectIdletime(key), (v)->v)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.OBJECT_IDLETIME,
					(cmd)->cmd.objectIdletime(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long objectRefcount(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.OBJECT_REFCOUNT,
					(cmd)->cmd.objectRefcount(key), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.OBJECT_REFCOUNT,
					(cmd)->cmd.objectRefcount(key), (v)->v)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.OBJECT_REFCOUNT,
					(cmd)->cmd.objectRefcount(key), (v)->v)
					.run(args);
		}
	}

	private Status copy(final CommandArguments args) {
		if(isPipeline()){
			return new LettucePipelineCommand<Status, Status>(client, ProtocolCommand.COPY)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<Status, Status>(client, ProtocolCommand.COPY)
					.run(args);
		}else{
			return new LettuceCommand<Status, Status>(client, ProtocolCommand.COPY)
					.run(args);
		}
	}

	private Status migrate(final String host, final int port, final int db, final int timeout,
						   final MigrateArgs<byte[]> migrateArgs, final CommandArguments args) {
		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.MIGRATE,
					(cmd)->cmd.migrate(host, port, db, timeout, migrateArgs), okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.MIGRATE,
					(cmd)->cmd.migrate(host, port, db, timeout, migrateArgs), okStatusConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.MIGRATE,
					(cmd)->cmd.migrate(host, port, db, timeout, migrateArgs), okStatusConverter)
					.run(args);
		}
	}

	private <V> Set<V> keys(final byte[] pattern, final ListSetConverter<byte[], V> converter,
							final CommandArguments args) {
		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.KEYS, (cmd)->cmd.keys(pattern), converter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.KEYS, (cmd)->cmd.keys(pattern), converter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.KEYS, (cmd)->cmd.keys(pattern), converter)
					.run(args);
		}
	}

	private <V> ScanResult<List<V>> scan(final ScanCursor cursor,
										 final Converter<KeyScanCursor<byte[]>, ScanResult<List<V>>> converter,
										 final CommandArguments args) {
		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.SCAN, (cmd)->cmd.scan(cursor), converter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.SCAN, (cmd)->cmd.scan(cursor), converter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.SCAN, (cmd)->cmd.scan(cursor), converter)
					.run(args);
		}
	}

	private <V> ScanResult<List<V>> scan(final ScanCursor cursor, final ScanArgs scanArgs,
										 final Converter<KeyScanCursor<byte[]>, ScanResult<List<V>>> converter,
										 final CommandArguments args) {
		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.SCAN, (cmd)->cmd.scan(cursor, scanArgs),
					converter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.SCAN, (cmd)->cmd.scan(cursor, scanArgs),
					converter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.SCAN, (cmd)->cmd.scan(cursor, scanArgs),
					converter)
					.run(args);
		}
	}

	private <V> List<V> sort(final byte[] key,
							 final Converter<List<byte[]>, List<V>> converter, final CommandArguments args) {
		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.SORT, (cmd)->cmd.sort(key), converter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.SORT, (cmd)->cmd.sort(key), converter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.SORT, (cmd)->cmd.sort(key), converter)
					.run(args);
		}
	}

	private <V> List<V> sort(final byte[] key, final SortArgs sortArgs,
							 final Converter<List<byte[]>, List<V>> converter, final CommandArguments args) {
		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.SORT, (cmd)->cmd.sort(key, sortArgs),
					converter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.SORT, (cmd)->cmd.sort(key, sortArgs),
					converter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.SORT, (cmd)->cmd.sort(key, sortArgs),
					converter)
					.run(args);
		}
	}

	private Long sortStore(final byte[] key, final byte[] destKey, final SortArgs sortArgs,
						   final CommandArguments args) {
		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.SORT,
					(cmd)->cmd.sortStore(key, sortArgs, destKey), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.SORT,
					(cmd)->cmd.sortStore(key, sortArgs, destKey), (v)->v)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.SORT, (cmd)->cmd.sortStore(key, sortArgs, destKey),
					(v)->v)
					.run(args);
		}
	}

}
