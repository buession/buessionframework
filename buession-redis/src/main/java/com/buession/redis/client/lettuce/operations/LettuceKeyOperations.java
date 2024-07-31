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
import com.buession.core.converter.ListConverter;
import com.buession.core.converter.ListSetConverter;
import com.buession.lang.Status;
import com.buession.redis.client.lettuce.LettuceStandaloneClient;
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
import com.buession.redis.core.internal.convert.Converters;
import com.buession.redis.core.internal.convert.lettuce.response.ScanCursorConverter;
import com.buession.redis.core.internal.convert.response.ObjectEncodingConverter;
import com.buession.redis.core.internal.convert.response.TypeConverter;
import com.buession.redis.core.internal.lettuce.LettuceCopyArgs;
import com.buession.redis.core.internal.lettuce.LettuceExpireArgs;
import com.buession.redis.core.internal.lettuce.LettuceMigrateArgs;
import com.buession.redis.core.internal.lettuce.LettuceRestoreArgs;
import com.buession.redis.core.internal.lettuce.LettuceScanArgs;
import com.buession.redis.core.internal.lettuce.LettuceScanCursor;
import com.buession.redis.core.internal.lettuce.LettuceSortArgs;
import com.buession.redis.utils.SafeEncoder;
import io.lettuce.core.CopyArgs;
import io.lettuce.core.ExpireArgs;
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
	public Status copy(final byte[] key, final byte[] destKey) {
		final CommandArguments args = CommandArguments.create(key).add(destKey);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.COPY, (cmd)->cmd.copy(key, destKey),
					booleanStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.COPY, (cmd)->cmd.copy(key, destKey),
					booleanStatusConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.COPY, (cmd)->cmd.copy(key, destKey), booleanStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status copy(final byte[] key, final byte[] destKey, final int db) {
		final CommandArguments args = CommandArguments.create(key).add(destKey).add(db);
		final CopyArgs copyArgs = new LettuceCopyArgs(db);

		return copy(key, destKey, copyArgs, args);
	}

	@Override
	public Status copy(final byte[] key, final byte[] destKey, final boolean replace) {
		final CommandArguments args = CommandArguments.create(key).add(destKey);
		final CopyArgs copyArgs = new LettuceCopyArgs(replace);

		if(replace){
			args.add(Keyword.Common.REPLACE);
		}

		return copy(key, destKey, copyArgs, args);
	}

	@Override
	public Status copy(final byte[] key, final byte[] destKey, final int db, final boolean replace) {
		final CommandArguments args = CommandArguments.create(key).add(destKey).add(db);
		final CopyArgs copyArgs = new LettuceCopyArgs(db, replace);

		if(replace){
			args.add(Keyword.Common.REPLACE);
		}

		return copy(key, destKey, copyArgs, args);
	}

	@Override
	public Long del(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(keys);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.DEL, (cmd)->cmd.del(keys), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.DEL, (cmd)->cmd.del(keys), (v)->v)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.DEL, (cmd)->cmd.del(keys), (v)->v)
					.run(args);
		}
	}

	@Override
	public byte[] dump(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.DUMP, (cmd)->cmd.dump(key), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.DUMP, (cmd)->cmd.dump(key), (v)->v)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.DUMP, (cmd)->cmd.dump(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public Boolean exists(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.EXISTS, (cmd)->cmd.exists(key), (v)->v == 1L)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.EXISTS, (cmd)->cmd.exists(key), (v)->v == 1L)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.EXISTS, (cmd)->cmd.exists(key), (v)->v == 1L)
					.run(args);
		}
	}

	@Override
	public Long exists(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(keys);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.EXISTS, (cmd)->cmd.exists(keys), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.EXISTS, (cmd)->cmd.exists(keys), (v)->v)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.EXISTS, (cmd)->cmd.exists(keys), (v)->v)
					.run(args);
		}
	}

	@Override
	public Status expire(final byte[] key, final int lifetime) {
		final CommandArguments args = CommandArguments.create(key).add(lifetime);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.EXPIRE, (cmd)->cmd.expire(key, lifetime),
					booleanStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.EXPIRE, (cmd)->cmd.expire(key, lifetime),
					booleanStatusConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.EXPIRE, (cmd)->cmd.expire(key, lifetime),
					booleanStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status expire(final byte[] key, final int lifetime, final ExpireOption expireOption) {
		final CommandArguments args = CommandArguments.create(key).add(lifetime).add(expireOption);
		final ExpireArgs expireArgs = new LettuceExpireArgs(expireOption);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.EXPIRE, (cmd)->cmd.expire(key, lifetime, expireArgs),
					booleanStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.EXPIRE, (cmd)->cmd.expire(key, lifetime, expireArgs),
					booleanStatusConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.EXPIRE, (cmd)->cmd.expire(key, lifetime, expireArgs),
					booleanStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status expireAt(final byte[] key, final long unixTimestamp) {
		final CommandArguments args = CommandArguments.create(key).add(unixTimestamp);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.EXPIREAT, (cmd)->cmd.expireat(key, unixTimestamp),
					booleanStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.EXPIREAT, (cmd)->cmd.expireat(key, unixTimestamp),
					booleanStatusConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.EXPIREAT, (cmd)->cmd.expireat(key, unixTimestamp),
					booleanStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status expireAt(final byte[] key, final long unixTimestamp, final ExpireOption expireOption) {
		final CommandArguments args = CommandArguments.create(key).add(unixTimestamp).add(expireOption);
		final ExpireArgs expireArgs = new LettuceExpireArgs(expireOption);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.EXPIREAT,
					(cmd)->cmd.expireat(key, unixTimestamp, expireArgs), booleanStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.EXPIREAT,
					(cmd)->cmd.expireat(key, unixTimestamp, expireArgs), booleanStatusConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.EXPIREAT, (cmd)->cmd.expireat(key, unixTimestamp, expireArgs),
					booleanStatusConverter)
					.run(args);
		}
	}

	@Override
	public Long expireTime(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.EXPIRETIME, (cmd)->cmd.expiretime(key), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.EXPIRETIME, (cmd)->cmd.expiretime(key), (v)->v)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.EXPIRETIME, (cmd)->cmd.expiretime(key), (v)->v)
					.run(args);
		}
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
	public Status migrate(final String host, final int port, final int db, final int timeout, final byte[]...
			keys) {
		final CommandArguments args = CommandArguments.create(host).add(port).add(db).add(timeout).add(keys);
		final MigrateArgs<byte[]> migrateArgs = new LettuceMigrateArgs<>(keys);

		return migrate(host, port, db, timeout, migrateArgs, args);
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final int timeout,
						  final MigrateArgument migrateArgument, final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(host).add(port).add(db).add(timeout).add(migrateArgument)
				.add(keys);
		final MigrateArgs<byte[]> migrateArgs = LettuceMigrateArgs.<byte[]>from(migrateArgument).keys(keys);

		return migrate(host, port, db, timeout, migrateArgs, args);
	}

	@Override
	public Status move(final byte[] key, final int db) {
		final CommandArguments args = CommandArguments.create(key).add(db);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.MOVE, (cmd)->cmd.move(key, db),
					booleanStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.MOVE, (cmd)->cmd.move(key, db),
					booleanStatusConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.MOVE, (cmd)->cmd.move(key, db),
					booleanStatusConverter)
					.run(args);
		}
	}

	@Override
	public ObjectEncoding objectEncoding(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		final ObjectEncodingConverter objectEncodingConverter = new ObjectEncodingConverter();

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.OBJECT, SubCommand.OBJECT_ENCODING,
					(cmd)->cmd.objectEncoding(key), objectEncodingConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.OBJECT, SubCommand.OBJECT_ENCODING,
					(cmd)->cmd.objectEncoding(key), objectEncodingConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.OBJECT, SubCommand.OBJECT_ENCODING,
					(cmd)->cmd.objectEncoding(key), objectEncodingConverter)
					.run(args);
		}
	}

	@Override
	public Long objectFreq(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.OBJECT, SubCommand.OBJECT_REFQ,
					(cmd)->cmd.objectFreq(key), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.OBJECT, SubCommand.OBJECT_REFQ,
					(cmd)->cmd.objectFreq(key), (v)->v)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.OBJECT, SubCommand.OBJECT_REFQ, (cmd)->cmd.objectFreq(key),
					(v)->v)
					.run(args);
		}
	}

	@Override
	public Long objectIdleTime(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.OBJECT, SubCommand.OBJECT_IDLETIME,
					(cmd)->cmd.objectIdletime(key), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.OBJECT, SubCommand.OBJECT_IDLETIME,
					(cmd)->cmd.objectIdletime(key), (v)->v)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.OBJECT, SubCommand.OBJECT_IDLETIME,
					(cmd)->cmd.objectIdletime(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long objectRefcount(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.OBJECT, SubCommand.OBJECT_REFCOUNT,
					(cmd)->cmd.objectRefcount(key), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.OBJECT, SubCommand.OBJECT_REFCOUNT,
					(cmd)->cmd.objectRefcount(key), (v)->v)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.OBJECT, SubCommand.OBJECT_REFCOUNT,
					(cmd)->cmd.objectRefcount(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public Status persist(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.PERSIST, (cmd)->cmd.persist(key),
					booleanStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.PERSIST, (cmd)->cmd.persist(key),
					booleanStatusConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.PERSIST, (cmd)->cmd.persist(key), booleanStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status pExpire(final byte[] key, final int lifetime) {
		final CommandArguments args = CommandArguments.create(key).add(lifetime);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.PEXPIRE, (cmd)->cmd.pexpire(key, lifetime),
					booleanStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.PEXPIRE, (cmd)->cmd.pexpire(key, lifetime),
					booleanStatusConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.PEXPIRE, (cmd)->cmd.pexpire(key, lifetime),
					booleanStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status pExpire(final byte[] key, final int lifetime, final ExpireOption expireOption) {
		final CommandArguments args = CommandArguments.create(key).add(lifetime).add(expireOption);
		final ExpireArgs expireArgs = new LettuceExpireArgs(expireOption);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.PEXPIRE,
					(cmd)->cmd.pexpire(key, lifetime, expireArgs), booleanStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.PEXPIRE,
					(cmd)->cmd.pexpire(key, lifetime, expireArgs), booleanStatusConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.PEXPIRE, (cmd)->cmd.pexpire(key, lifetime, expireArgs),
					booleanStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status pExpireAt(final byte[] key, final long unixTimestamp) {
		final CommandArguments args = CommandArguments.create(key).add(unixTimestamp);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.EXPIREAT, (cmd)->cmd.pexpireat(key, unixTimestamp),
					booleanStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.EXPIREAT, (cmd)->cmd.pexpireat(key, unixTimestamp),
					booleanStatusConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.EXPIREAT, (cmd)->cmd.pexpireat(key, unixTimestamp),
					booleanStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status pExpireAt(final byte[] key, final long unixTimestamp, final ExpireOption expireOption) {
		final CommandArguments args = CommandArguments.create(key).add(unixTimestamp).add(expireOption);
		final ExpireArgs expireArgs = new LettuceExpireArgs(expireOption);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.PEXPIREAT,
					(cmd)->cmd.pexpireat(key, unixTimestamp, expireArgs), booleanStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.PEXPIREAT,
					(cmd)->cmd.pexpireat(key, unixTimestamp, expireArgs), booleanStatusConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.PEXPIREAT, (cmd)->cmd.pexpireat(key, unixTimestamp, expireArgs),
					booleanStatusConverter)
					.run(args);
		}
	}

	@Override
	public Long pExpireTime(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.PEXPIRETIME, (cmd)->cmd.pexpiretime(key), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.EXPIREAT, (cmd)->cmd.pexpiretime(key), (v)->v)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.EXPIREAT, (cmd)->cmd.pexpiretime(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long pTtl(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.PTTL, (cmd)->cmd.pttl(key), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.PTTL, (cmd)->cmd.pttl(key), (v)->v)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.PTTL, (cmd)->cmd.pttl(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public String randomKey() {
		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.RANDOMKEY, (cmd)->cmd.randomkey(),
					SafeEncoder::encode)
					.run();
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.RANDOMKEY, (cmd)->cmd.randomkey(),
					SafeEncoder::encode)
					.run();
		}else{
			return new LettuceCommand<>(client, Command.RANDOMKEY, (cmd)->cmd.randomkey(), SafeEncoder::encode)
					.run();
		}
	}

	@Override
	public Status rename(final byte[] key, final byte[] newKey) {
		final CommandArguments args = CommandArguments.create(key).add(newKey);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.RENAME, (cmd)->cmd.rename(key, newKey),
					okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.RENAME, (cmd)->cmd.rename(key, newKey),
					okStatusConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.RENAME, (cmd)->cmd.rename(key, newKey), okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status renameNx(final byte[] key, final byte[] newKey) {
		final CommandArguments args = CommandArguments.create(key).add(newKey);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.RENAMENX, (cmd)->cmd.renamenx(key, newKey),
					booleanStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.RENAMENX, (cmd)->cmd.renamenx(key, newKey),
					booleanStatusConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.RENAMENX, (cmd)->cmd.renamenx(key, newKey),
					booleanStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status restore(final byte[] key, final byte[] serializedValue, final int ttl) {
		final CommandArguments args = CommandArguments.create(key).add(serializedValue).add(ttl);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.RESTORE, (cmd)->cmd.restore(key, ttl, serializedValue),
					okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.RESTORE,
					(cmd)->cmd.restore(key, ttl, serializedValue), okStatusConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.RESTORE, (cmd)->cmd.restore(key, ttl, serializedValue),
					okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status restore(final byte[] key, final byte[] serializedValue, final int ttl,
						  final RestoreArgument restoreArgument) {
		final CommandArguments args = CommandArguments.create(key).add(serializedValue).add(ttl).add(restoreArgument);
		final RestoreArgs restoreArgs = LettuceRestoreArgs.from(restoreArgument).ttl(ttl);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.RESTORE,
					(cmd)->cmd.restore(key, serializedValue, restoreArgs), okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.RESTORE,
					(cmd)->cmd.restore(key, serializedValue, restoreArgs), okStatusConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.RESTORE, (cmd)->cmd.restore(key, serializedValue, restoreArgs),
					okStatusConverter)
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
	public ScanResult<List<String>> scan(final String cursor, final int count) {
		final CommandArguments args = CommandArguments.create(cursor).add(count);
		final ScanCursor scanCursor = new LettuceScanCursor(cursor);
		final ScanArgs scanArgs = new LettuceScanArgs(count);
		final ScanCursorConverter.KeyScanCursorConverter.BSKeyScanCursorConverter bsKeyScanCursorConverter =
				new ScanCursorConverter.KeyScanCursorConverter.BSKeyScanCursorConverter();

		return scan(scanCursor, scanArgs, bsKeyScanCursorConverter, args);
	}

	@Override
	public ScanResult<List<byte[]>> scan(final byte[] cursor, final int count) {
		final CommandArguments args = CommandArguments.create(cursor).add(count);
		final ScanCursor scanCursor = new LettuceScanCursor(cursor);
		final ScanArgs scanArgs = new LettuceScanArgs(count);
		final ScanCursorConverter.KeyScanCursorConverter<byte[]> keyScanCursorConverter = new ScanCursorConverter.KeyScanCursorConverter<>();

		return scan(scanCursor, scanArgs, keyScanCursorConverter, args);
	}

	@Override
	public ScanResult<List<String>> scan(final String cursor, final String pattern, final int count) {
		final CommandArguments args = CommandArguments.create(cursor).add(pattern).add(count);
		final ScanCursor scanCursor = new LettuceScanCursor(cursor);
		final ScanArgs scanArgs = new LettuceScanArgs(pattern, count);
		final ScanCursorConverter.KeyScanCursorConverter.BSKeyScanCursorConverter bsKeyScanCursorConverter =
				new ScanCursorConverter.KeyScanCursorConverter.BSKeyScanCursorConverter();

		return scan(scanCursor, scanArgs, bsKeyScanCursorConverter, args);
	}

	@Override
	public ScanResult<List<byte[]>> scan(final byte[] cursor, final byte[] pattern, final int count) {
		final CommandArguments args = CommandArguments.create(cursor).add(pattern).add(count);
		final ScanCursor scanCursor = new LettuceScanCursor(cursor);
		final ScanArgs scanArgs = new LettuceScanArgs(pattern, count);
		final ScanCursorConverter.KeyScanCursorConverter<byte[]> keyScanCursorConverter = new ScanCursorConverter.KeyScanCursorConverter<>();

		return scan(scanCursor, scanArgs, keyScanCursorConverter, args);
	}

	@Override
	public List<String> sort(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		final byte[] bKey = SafeEncoder.encode(key);
		final ListConverter<byte[], String> listConverter = Converters.listBinaryToString();

		return sort(bKey, listConverter, args);
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
		final ListConverter<byte[], String> listConverter = Converters.listBinaryToString();

		return sort(bKey, sortArgs, listConverter, args);
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
		final CommandArguments args = CommandArguments.create(key).add(destKey).add(sortArgument);
		final SortArgs sortArgs = LettuceSortArgs.from(sortArgument);

		return sortStore(key, destKey, sortArgs, args);
	}

	@Override
	public List<String> sortRo(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		final byte[] bKey = SafeEncoder.encode(key);
		final ListConverter<byte[], String> listConverter = Converters.listBinaryToString();

		return sortRo(bKey, listConverter, args);
	}

	@Override
	public List<byte[]> sortRo(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return sortRo(key, (v)->v, args);
	}

	@Override
	public List<String> sortRo(final String key, final SortArgument sortArgument) {
		final CommandArguments args = CommandArguments.create(key).add(sortArgument);
		final byte[] bKey = SafeEncoder.encode(key);
		final SortArgs sortArgs = LettuceSortArgs.from(sortArgument);
		final ListConverter<byte[], String> listConverter = Converters.listBinaryToString();

		return sortRo(bKey, sortArgs, listConverter, args);
	}

	@Override
	public List<byte[]> sortRo(final byte[] key, final SortArgument sortArgument) {
		final CommandArguments args = CommandArguments.create(key).add(sortArgument);
		final SortArgs sortArgs = LettuceSortArgs.from(sortArgument);

		return sortRo(key, sortArgs, (v)->v, args);
	}

	@Override
	public Long touch(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(keys);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.TOUCH, (cmd)->cmd.touch(keys), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.TOUCH, (cmd)->cmd.touch(keys), (v)->v)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.TOUCH, (cmd)->cmd.touch(keys), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long ttl(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.TTL, (cmd)->cmd.ttl(key), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.TTL, (cmd)->cmd.ttl(key), (v)->v)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.TTL, (cmd)->cmd.ttl(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public Type type(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		final TypeConverter typeConverter = new TypeConverter();

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.TYPE, (cmd)->cmd.type(key), typeConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.TYPE, (cmd)->cmd.type(key), typeConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.TYPE, (cmd)->cmd.type(key), typeConverter)
					.run(args);
		}
	}

	@Override
	public Long unlink(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(keys);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.UNLINK, (cmd)->cmd.unlink(keys), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.UNLINK, (cmd)->cmd.unlink(keys), (v)->v)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.UNLINK, (cmd)->cmd.unlink(keys), (v)->v)
					.run(args);
		}
	}

	private Status copy(final byte[] key, final byte[] destKey, final CopyArgs copyArgs, final CommandArguments args) {
		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.COPY, (cmd)->cmd.copy(key, destKey, copyArgs),
					booleanStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.COPY,
					(cmd)->cmd.copy(key, destKey, copyArgs), booleanStatusConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.COPY, (cmd)->cmd.copy(key, destKey, copyArgs),
					booleanStatusConverter)
					.run(args);
		}
	}

	private <V> Set<V> keys(final byte[] pattern, final ListSetConverter<byte[], V> converter,
							final CommandArguments args) {
		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.KEYS, (cmd)->cmd.keys(pattern), converter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.KEYS, (cmd)->cmd.keys(pattern), converter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.KEYS, (cmd)->cmd.keys(pattern), converter)
					.run(args);
		}
	}

	private Status migrate(final String host, final int port, final int db, final int timeout,
						   final MigrateArgs<byte[]> migrateArgs, final CommandArguments args) {
		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.MIGRATE,
					(cmd)->cmd.migrate(host, port, db, timeout, migrateArgs), okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.MIGRATE,
					(cmd)->cmd.migrate(host, port, db, timeout, migrateArgs), okStatusConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.MIGRATE,
					(cmd)->cmd.migrate(host, port, db, timeout, migrateArgs), okStatusConverter)
					.run(args);
		}
	}

	private <V> ScanResult<List<V>> scan(final ScanCursor cursor,
										 final Converter<KeyScanCursor<byte[]>, ScanResult<List<V>>> converter,
										 final CommandArguments args) {
		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.SCAN, (cmd)->cmd.scan(cursor), converter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.SCAN, (cmd)->cmd.scan(cursor), converter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.SCAN, (cmd)->cmd.scan(cursor), converter)
					.run(args);
		}
	}

	private <V> ScanResult<List<V>> scan(final ScanCursor cursor, final ScanArgs scanArgs,
										 final Converter<KeyScanCursor<byte[]>, ScanResult<List<V>>> converter,
										 final CommandArguments args) {
		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.SCAN, (cmd)->cmd.scan(cursor, scanArgs),
					converter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.SCAN, (cmd)->cmd.scan(cursor, scanArgs),
					converter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.SCAN, (cmd)->cmd.scan(cursor, scanArgs),
					converter)
					.run(args);
		}
	}

	private <V> List<V> sort(final byte[] key,
							 final Converter<List<byte[]>, List<V>> converter, final CommandArguments args) {
		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.SORT, (cmd)->cmd.sort(key), converter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.SORT, (cmd)->cmd.sort(key), converter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.SORT, (cmd)->cmd.sort(key), converter)
					.run(args);
		}
	}

	private <V> List<V> sort(final byte[] key, final SortArgs sortArgs,
							 final Converter<List<byte[]>, List<V>> converter, final CommandArguments args) {
		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.SORT, (cmd)->cmd.sort(key, sortArgs),
					converter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.SORT, (cmd)->cmd.sort(key, sortArgs),
					converter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.SORT, (cmd)->cmd.sort(key, sortArgs),
					converter)
					.run(args);
		}
	}

	private Long sortStore(final byte[] key, final byte[] destKey, final SortArgs sortArgs,
						   final CommandArguments args) {
		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.SORT, (cmd)->cmd.sortStore(key, sortArgs, destKey),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.SORT, (cmd)->cmd.sortStore(key, sortArgs, destKey),
					(v)->v)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.SORT, (cmd)->cmd.sortStore(key, sortArgs, destKey),
					(v)->v)
					.run(args);
		}
	}

	private <V> List<V> sortRo(final byte[] key,
							   final Converter<List<byte[]>, List<V>> converter, final CommandArguments args) {
		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.SORT_RO, (cmd)->cmd.sortReadOnly(key), converter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.SORT_RO, (cmd)->cmd.sortReadOnly(key), converter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.SORT_RO, (cmd)->cmd.sortReadOnly(key), converter)
					.run(args);
		}
	}

	private <V> List<V> sortRo(final byte[] key, final SortArgs sortArgs,
							   final Converter<List<byte[]>, List<V>> converter, final CommandArguments args) {
		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.SORT_RO, (cmd)->cmd.sortReadOnly(key, sortArgs),
					converter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.SORT_RO, (cmd)->cmd.sortReadOnly(key, sortArgs),
					converter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.SORT_RO, (cmd)->cmd.sortReadOnly(key, sortArgs), converter)
					.run(args);
		}
	}

}
