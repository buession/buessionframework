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
package com.buession.redis.client.jedis.operations;

import com.buession.lang.Status;
import com.buession.redis.client.jedis.JedisSentinelClient;
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
import com.buession.redis.core.internal.convert.jedis.params.ExpireOptionConverter;
import com.buession.redis.core.internal.convert.response.BinaryObjectEncodingConverter;
import com.buession.redis.core.internal.convert.response.ObjectEncodingConverter;
import com.buession.redis.core.internal.convert.jedis.response.ScanResultConverter;
import com.buession.redis.core.internal.convert.response.TypeConverter;
import com.buession.redis.core.internal.jedis.JedisMigrateParams;
import com.buession.redis.core.internal.jedis.JedisRestoreParams;
import com.buession.redis.core.internal.jedis.JedisScanParams;
import com.buession.redis.core.internal.jedis.JedisSortingParams;
import com.buession.redis.utils.SafeEncoder;
import redis.clients.jedis.args.ExpiryOption;
import redis.clients.jedis.params.MigrateParams;
import redis.clients.jedis.params.RestoreParams;
import redis.clients.jedis.params.ScanParams;
import redis.clients.jedis.params.SortingParams;

import java.util.List;
import java.util.Set;

/**
 * Jedis 哨兵模式 Key 命令操作
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public final class JedisSentinelKeyOperations extends AbstractKeyOperations<JedisSentinelClient> {

	public JedisSentinelKeyOperations(final JedisSentinelClient client) {
		super(client);
	}

	@Override
	public Status copy(final String key, final String destKey) {
		final CommandArguments args = CommandArguments.create(key).add(destKey);
		return copy(key, destKey, false, args);
	}

	@Override
	public Status copy(final byte[] key, final byte[] destKey) {
		final CommandArguments args = CommandArguments.create(key).add(destKey);
		return copy(key, destKey, false, args);
	}

	@Override
	public Status copy(final String key, final String destKey, final int db) {
		final CommandArguments args = CommandArguments.create(key).add(destKey).add(db);
		return copy(key, destKey, db, false, args);
	}

	@Override
	public Status copy(final byte[] key, final byte[] destKey, final int db) {
		final CommandArguments args = CommandArguments.create(key).add(destKey).add(db);
		return copy(key, destKey, db, false, args);
	}

	@Override
	public Status copy(final String key, final String destKey, final boolean replace) {
		final CommandArguments args = CommandArguments.create(key).add(destKey);

		if(replace){
			args.add(Keyword.Common.REPLACE);
		}

		return copy(key, destKey, replace, args);
	}

	@Override
	public Status copy(final byte[] key, final byte[] destKey, final boolean replace) {
		final CommandArguments args = CommandArguments.create(key).add(destKey);

		if(replace){
			args.add(Keyword.Common.REPLACE);
		}

		return copy(key, destKey, replace, args);
	}

	@Override
	public Status copy(final String key, final String destKey, final int db, final boolean replace) {
		final CommandArguments args = CommandArguments.create(key).add(destKey).add(db);

		if(replace){
			args.add(Keyword.Common.REPLACE);
		}

		return copy(key, destKey, db, replace, args);
	}

	@Override
	public Status copy(final byte[] key, final byte[] destKey, final int db, final boolean replace) {
		final CommandArguments args = CommandArguments.create(key).add(destKey).add(db);

		if(replace){
			args.add(Keyword.Common.REPLACE);
		}

		return copy(key, destKey, db, replace, args);
	}

	@Override
	public Long del(final String... keys) {
		final CommandArguments args = CommandArguments.create(keys);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.DEL, (cmd)->cmd.del(keys), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.DEL, (cmd)->cmd.del(keys), (v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.DEL, (cmd)->cmd.del(keys), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long del(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(keys);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.DEL, (cmd)->cmd.del(keys), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.DEL, (cmd)->cmd.del(keys), (v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.DEL, (cmd)->cmd.del(keys), (v)->v)
					.run(args);
		}
	}

	@Override
	public String dump(final String key) {
		final CommandArguments args = CommandArguments.create(key);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.DUMP, (cmd)->cmd.dump(key), SafeEncoder::encode)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.DUMP, (cmd)->cmd.dump(key),
					SafeEncoder::encode)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.DUMP, (cmd)->cmd.dump(key), SafeEncoder::encode)
					.run(args);
		}
	}

	@Override
	public byte[] dump(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.DUMP, (cmd)->cmd.dump(key), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.DUMP, (cmd)->cmd.dump(key), (v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.DUMP, (cmd)->cmd.dump(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public Boolean exists(final String key) {
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.EXISTS, (cmd)->cmd.exists(key), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.EXISTS, (cmd)->cmd.exists(key), (v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.EXISTS, (cmd)->cmd.exists(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public Boolean exists(final byte[] key) {
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.EXISTS, (cmd)->cmd.exists(key), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.EXISTS, (cmd)->cmd.exists(key), (v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.EXISTS, (cmd)->cmd.exists(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long exists(final String... keys) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.EXISTS, (cmd)->cmd.exists(keys), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.EXISTS, (cmd)->cmd.exists(keys),
					(v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.EXISTS, (cmd)->cmd.exists(keys), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long exists(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.EXISTS, (cmd)->cmd.exists(keys), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.EXISTS, (cmd)->cmd.exists(keys),
					(v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.EXISTS, (cmd)->cmd.exists(keys), (v)->v)
					.run(args);
		}
	}

	@Override
	public Status expire(final String key, final int lifetime) {
		final CommandArguments args = CommandArguments.create(key).add(lifetime);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.EXPIRE, (cmd)->cmd.expire(key, lifetime),
					oneStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.EXPIRE, (cmd)->cmd.expire(key, lifetime),
					oneStatusConverter)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.EXPIRE, (cmd)->cmd.expire(key, lifetime),
					oneStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status expire(final byte[] key, final int lifetime) {
		final CommandArguments args = CommandArguments.create(key).add(lifetime);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.EXPIRE, (cmd)->cmd.expire(key, lifetime),
					oneStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.EXPIRE, (cmd)->cmd.expire(key, lifetime),
					oneStatusConverter)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.EXPIRE, (cmd)->cmd.expire(key, lifetime),
					oneStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status expire(final String key, final int lifetime, final ExpireOption expireOption) {
		final CommandArguments args = CommandArguments.create(key).add(lifetime).add(expireOption);
		final ExpiryOption expiryOption = (new ExpireOptionConverter()).convert(expireOption);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.EXPIRE,
					(cmd)->cmd.expire(key, lifetime, expiryOption), oneStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.EXPIRE,
					(cmd)->cmd.expire(key, lifetime, expiryOption), oneStatusConverter)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.EXPIRE, (cmd)->cmd.expire(key, lifetime, expiryOption),
					oneStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status expire(final byte[] key, final int lifetime, final ExpireOption expireOption) {
		final CommandArguments args = CommandArguments.create(key).add(lifetime).add(expireOption);
		final ExpiryOption expiryOption = (new ExpireOptionConverter()).convert(expireOption);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.EXPIRE,
					(cmd)->cmd.expire(key, lifetime, expiryOption), oneStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.EXPIRE,
					(cmd)->cmd.expire(key, lifetime, expiryOption), oneStatusConverter)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.EXPIRE,
					(cmd)->cmd.expire(key, lifetime, expiryOption), oneStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status expireAt(final String key, final long unixTimestamp) {
		final CommandArguments args = CommandArguments.create(key).add(unixTimestamp);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.EXPIREAT, (cmd)->cmd.expireAt(key, unixTimestamp),
					oneStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.EXPIREAT,
					(cmd)->cmd.expireAt(key, unixTimestamp), oneStatusConverter)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.EXPIREAT, (cmd)->cmd.expireAt(key, unixTimestamp),
					oneStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status expireAt(final byte[] key, final long unixTimestamp) {
		final CommandArguments args = CommandArguments.create(key).add(unixTimestamp);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.EXPIREAT, (cmd)->cmd.expireAt(key, unixTimestamp),
					oneStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.EXPIREAT,
					(cmd)->cmd.expireAt(key, unixTimestamp), oneStatusConverter)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.EXPIREAT, (cmd)->cmd.expireAt(key, unixTimestamp),
					oneStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status expireAt(final String key, final long unixTimestamp, final ExpireOption expireOption) {
		final CommandArguments args = CommandArguments.create(key).add(unixTimestamp).add(expireOption);
		final ExpiryOption expiryOption = (new ExpireOptionConverter()).convert(expireOption);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.EXPIREAT,
					(cmd)->cmd.expireAt(key, unixTimestamp, expiryOption), oneStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.EXPIREAT,
					(cmd)->cmd.expireAt(key, unixTimestamp, expiryOption), oneStatusConverter)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.EXPIREAT,
					(cmd)->cmd.expireAt(key, unixTimestamp, expiryOption), oneStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status expireAt(final byte[] key, final long unixTimestamp, final ExpireOption expireOption) {
		final CommandArguments args = CommandArguments.create(key).add(unixTimestamp).add(expireOption);
		final ExpiryOption expiryOption = (new ExpireOptionConverter()).convert(expireOption);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.EXPIREAT,
					(cmd)->cmd.expireAt(key, unixTimestamp, expiryOption), oneStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.EXPIREAT,
					(cmd)->cmd.expireAt(key, unixTimestamp, expiryOption), oneStatusConverter)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.EXPIREAT,
					(cmd)->cmd.expireAt(key, unixTimestamp, expiryOption), oneStatusConverter)
					.run(args);
		}
	}

	@Override
	public Long expireTime(final String key) {
		final CommandArguments args = CommandArguments.create(key);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.EXPIRETIME, (cmd)->cmd.expireTime(key), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.EXPIRETIME, (cmd)->cmd.expireTime(key), (v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.EXPIRETIME, (cmd)->cmd.expireTime(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long expireTime(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.EXPIRETIME, (cmd)->cmd.expireTime(key), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.EXPIRETIME, (cmd)->cmd.expireTime(key), (v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.EXPIRETIME, (cmd)->cmd.expireTime(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public Set<String> keys(final String pattern) {
		final CommandArguments args = CommandArguments.create(pattern);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.KEYS, (cmd)->cmd.keys(pattern), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.KEYS, (cmd)->cmd.keys(pattern), (v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.KEYS, (cmd)->cmd.keys(pattern), (v)->v)
					.run(args);
		}
	}

	@Override
	public Set<byte[]> keys(final byte[] pattern) {
		final CommandArguments args = CommandArguments.create(pattern);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.KEYS, (cmd)->cmd.keys(pattern), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.KEYS, (cmd)->cmd.keys(pattern), (v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.KEYS, (cmd)->cmd.keys(pattern), (v)->v)
					.run(args);
		}
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final int timeout, final String... keys) {
		final CommandArguments args = CommandArguments.create(host).add(port).add(db).add(timeout).add(keys);
		final MigrateParams migrateParams = new JedisMigrateParams();

		return migrate(host, port, db, timeout, keys, migrateParams, args);
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final int timeout, final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(host).add(port).add(db).add(timeout).add(keys);
		final MigrateParams migrateParams = new JedisMigrateParams();

		return migrate(host, port, db, timeout, keys, migrateParams, args);
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final int timeout,
						  final MigrateArgument migrateArgument, final String... keys) {
		final CommandArguments args = CommandArguments.create(host).add(port).add(db).add(timeout).add(migrateArgument)
				.add(keys);
		final MigrateParams migrateParams = JedisMigrateParams.from(migrateArgument);

		return migrate(host, port, db, timeout, keys, migrateParams, args);
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final int timeout,
						  final MigrateArgument migrateArgument, final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(host).add(port).add(db).add(timeout).add(migrateArgument)
				.add(keys);
		final MigrateParams migrateParams = JedisMigrateParams.from(migrateArgument);

		return migrate(host, port, db, timeout, keys, migrateParams, args);
	}

	@Override
	public Status move(final String key, final int db) {
		final CommandArguments args = CommandArguments.create(key).add(db);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.MOVE, (cmd)->cmd.move(key, db),
					oneStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Status, Status>(client, Command.MOVE)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.MOVE, (cmd)->cmd.move(key, db),
					oneStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status move(final byte[] key, final int db) {
		final CommandArguments args = CommandArguments.create(key).add(db);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.MOVE, (cmd)->cmd.move(key, db),
					oneStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<Status, Status>(client, Command.MOVE)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.MOVE, (cmd)->cmd.move(key, db),
					oneStatusConverter)
					.run(args);
		}
	}

	@Override
	public ObjectEncoding objectEncoding(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		final ObjectEncodingConverter objectEncodingConverter = new ObjectEncodingConverter();

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.OBJECT, SubCommand.OBJECT_ENCODING,
					(cmd)->cmd.objectEncoding(key), objectEncodingConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.OBJECT, SubCommand.OBJECT_ENCODING,
					(cmd)->cmd.objectEncoding(key), objectEncodingConverter)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.OBJECT, SubCommand.OBJECT_ENCODING,
					(cmd)->cmd.objectEncoding(key), objectEncodingConverter)
					.run(args);
		}
	}

	@Override
	public ObjectEncoding objectEncoding(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		final BinaryObjectEncodingConverter binaryObjectEncodingConverter = new BinaryObjectEncodingConverter();

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.OBJECT, SubCommand.OBJECT_ENCODING,
					(cmd)->cmd.objectEncoding(key), binaryObjectEncodingConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.OBJECT, SubCommand.OBJECT_ENCODING,
					(cmd)->cmd.objectEncoding(key), binaryObjectEncodingConverter)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.OBJECT, SubCommand.OBJECT_ENCODING,
					(cmd)->cmd.objectEncoding(key), binaryObjectEncodingConverter)
					.run(args);
		}
	}

	@Override
	public Long objectFreq(final String key) {
		final CommandArguments args = CommandArguments.create(key);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.OBJECT, SubCommand.OBJECT_REFQ,
					(cmd)->cmd.objectFreq(key), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.OBJECT, SubCommand.OBJECT_REFQ,
					(cmd)->cmd.objectFreq(key), (v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.OBJECT, SubCommand.OBJECT_REFQ,
					(cmd)->cmd.objectFreq(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long objectFreq(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.OBJECT, SubCommand.OBJECT_REFQ,
					(cmd)->cmd.objectFreq(key), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.OBJECT, SubCommand.OBJECT_REFQ,
					(cmd)->cmd.objectFreq(key), (v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.OBJECT, SubCommand.OBJECT_REFQ,
					(cmd)->cmd.objectFreq(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long objectIdleTime(final String key) {
		final CommandArguments args = CommandArguments.create(key);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.OBJECT, SubCommand.OBJECT_IDLETIME,
					(cmd)->cmd.objectIdletime(key), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.OBJECT, SubCommand.OBJECT_IDLETIME,
					(cmd)->cmd.objectIdletime(key), (v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.OBJECT, SubCommand.OBJECT_IDLETIME,
					(cmd)->cmd.objectIdletime(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long objectIdleTime(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.OBJECT, SubCommand.OBJECT_IDLETIME,
					(cmd)->cmd.objectIdletime(key), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.OBJECT, SubCommand.OBJECT_IDLETIME,
					(cmd)->cmd.objectIdletime(key), (v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.OBJECT, SubCommand.OBJECT_IDLETIME,
					(cmd)->cmd.objectIdletime(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long objectRefcount(final String key) {
		final CommandArguments args = CommandArguments.create(key);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.OBJECT, SubCommand.OBJECT_REFCOUNT,
					(cmd)->cmd.objectRefcount(key), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.OBJECT, SubCommand.OBJECT_REFCOUNT,
					(cmd)->cmd.objectRefcount(key), (v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.OBJECT, SubCommand.OBJECT_REFCOUNT,
					(cmd)->cmd.objectRefcount(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long objectRefcount(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.OBJECT, SubCommand.OBJECT_REFCOUNT,
					(cmd)->cmd.objectRefcount(key), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.OBJECT, SubCommand.OBJECT_REFCOUNT,
					(cmd)->cmd.objectRefcount(key), (v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.OBJECT, SubCommand.OBJECT_REFCOUNT,
					(cmd)->cmd.objectRefcount(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public Status persist(final String key) {
		final CommandArguments args = CommandArguments.create(key);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.PERSIST, (cmd)->cmd.persist(key),
					oneStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.PERSIST, (cmd)->cmd.persist(key),
					oneStatusConverter)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.PERSIST, (cmd)->cmd.persist(key), oneStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status persist(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.PERSIST, (cmd)->cmd.persist(key),
					oneStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.PERSIST, (cmd)->cmd.persist(key),
					oneStatusConverter)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.PERSIST, (cmd)->cmd.persist(key), oneStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status pExpire(final String key, final int lifetime) {
		final CommandArguments args = CommandArguments.create(key).add(lifetime);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.PEXPIRE, (cmd)->cmd.pexpire(key, lifetime),
					oneStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.PEXPIRE, (cmd)->cmd.pexpire(key, lifetime),
					oneStatusConverter)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.PEXPIRE, (cmd)->cmd.pexpire(key, lifetime),
					oneStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status pExpire(final byte[] key, final int lifetime) {
		final CommandArguments args = CommandArguments.create(key).add(lifetime);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.PEXPIRE, (cmd)->cmd.pexpire(key, lifetime),
					oneStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.PEXPIRE, (cmd)->cmd.pexpire(key, lifetime),
					oneStatusConverter)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.PEXPIRE, (cmd)->cmd.pexpire(key, lifetime),
					oneStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status pExpire(final String key, final int lifetime, final ExpireOption expireOption) {
		final CommandArguments args = CommandArguments.create(key).add(lifetime).add(expireOption);
		final ExpiryOption expiryOption = (new ExpireOptionConverter()).convert(expireOption);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.PEXPIRE,
					(cmd)->cmd.pexpire(key, lifetime, expiryOption), oneStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.PEXPIRE,
					(cmd)->cmd.pexpire(key, lifetime, expiryOption), oneStatusConverter)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.PEXPIRE, (cmd)->cmd.pexpire(key, lifetime, expiryOption),
					oneStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status pExpire(final byte[] key, final int lifetime, final ExpireOption expireOption) {
		final CommandArguments args = CommandArguments.create(key).add(lifetime).add(expireOption);
		final ExpiryOption expiryOption = (new ExpireOptionConverter()).convert(expireOption);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.PEXPIRE,
					(cmd)->cmd.pexpire(key, lifetime, expiryOption), oneStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.PEXPIRE,
					(cmd)->cmd.pexpire(key, lifetime, expiryOption), oneStatusConverter)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.PEXPIRE, (cmd)->cmd.pexpire(key, lifetime, expiryOption),
					oneStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status pExpireAt(final String key, final long unixTimestamp) {
		final CommandArguments args = CommandArguments.create(key).add(unixTimestamp);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.PEXPIREAT,
					(cmd)->cmd.pexpireAt(key, unixTimestamp), oneStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.PEXPIREAT,
					(cmd)->cmd.pexpireAt(key, unixTimestamp), oneStatusConverter)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.PEXPIREAT, (cmd)->cmd.pexpireAt(key, unixTimestamp),
					oneStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status pExpireAt(final byte[] key, final long unixTimestamp) {
		final CommandArguments args = CommandArguments.create(key).add(unixTimestamp);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.PEXPIREAT,
					(cmd)->cmd.pexpireAt(key, unixTimestamp), oneStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.PEXPIREAT,
					(cmd)->cmd.pexpireAt(key, unixTimestamp), oneStatusConverter)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.PEXPIREAT, (cmd)->cmd.pexpireAt(key, unixTimestamp),
					oneStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status pExpireAt(final String key, final long unixTimestamp, final ExpireOption expireOption) {
		final CommandArguments args = CommandArguments.create(key).add(unixTimestamp).add(expireOption);
		final ExpiryOption expiryOption = (new ExpireOptionConverter()).convert(expireOption);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.PEXPIREAT,
					(cmd)->cmd.pexpireAt(key, unixTimestamp, expiryOption), oneStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.PEXPIREAT,
					(cmd)->cmd.pexpireAt(key, unixTimestamp, expiryOption), oneStatusConverter)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.PEXPIREAT,
					(cmd)->cmd.pexpireAt(key, unixTimestamp, expiryOption), oneStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status pExpireAt(final byte[] key, final long unixTimestamp, final ExpireOption expireOption) {
		final CommandArguments args = CommandArguments.create(key).add(unixTimestamp).add(expireOption);
		final ExpiryOption expiryOption = (new ExpireOptionConverter()).convert(expireOption);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.PEXPIREAT,
					(cmd)->cmd.pexpireAt(key, unixTimestamp, expiryOption), oneStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.PEXPIREAT,
					(cmd)->cmd.pexpireAt(key, unixTimestamp, expiryOption), oneStatusConverter)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.PEXPIREAT,
					(cmd)->cmd.pexpireAt(key, unixTimestamp, expiryOption), oneStatusConverter)
					.run(args);
		}
	}

	@Override
	public Long pExpireTime(final String key) {
		final CommandArguments args = CommandArguments.create(key);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.PEXPIRETIME, (cmd)->cmd.expireTime(key), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.PEXPIRETIME, (cmd)->cmd.expireTime(key),
					(v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.PEXPIRETIME, (cmd)->cmd.expireTime(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long pExpireTime(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.PEXPIRETIME, (cmd)->cmd.expireTime(key), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.PEXPIRETIME, (cmd)->cmd.expireTime(key),
					(v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.PEXPIRETIME, (cmd)->cmd.expireTime(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long pTtl(final String key) {
		final CommandArguments args = CommandArguments.create(key);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.PTTL, (cmd)->cmd.pttl(key), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.PTTL, (cmd)->cmd.pttl(key), (v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.PTTL, (cmd)->cmd.pttl(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long pTtl(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.PTTL, (cmd)->cmd.pttl(key), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.PTTL, (cmd)->cmd.pttl(key), (v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.PTTL, (cmd)->cmd.pttl(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public String randomKey() {
		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.RANDOMKEY, (cmd)->cmd.randomKey(), (v)->v)
					.run();
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.RANDOMKEY, (cmd)->cmd.randomKey(),
					(v)->v)
					.run();
		}else{
			return new JedisSentinelCommand<>(client, Command.RANDOMKEY, (cmd)->cmd.randomKey(), (v)->v)
					.run();
		}
	}

	@Override
	public Status rename(final String key, final String newKey) {
		final CommandArguments args = CommandArguments.create(key).add(newKey);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.RENAME, (cmd)->cmd.rename(key, newKey),
					okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.RENAME, (cmd)->cmd.rename(key, newKey),
					okStatusConverter)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.RENAME, (cmd)->cmd.rename(key, newKey),
					okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status rename(final byte[] key, final byte[] newKey) {
		final CommandArguments args = CommandArguments.create(key).add(newKey);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.RENAME, (cmd)->cmd.rename(key, newKey),
					okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.RENAME, (cmd)->cmd.rename(key, newKey),
					okStatusConverter)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.RENAME, (cmd)->cmd.rename(key, newKey),
					okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status renameNx(final String key, final String newKey) {
		final CommandArguments args = CommandArguments.create(key).add(newKey);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.RENAMENX, (cmd)->cmd.renamenx(key, newKey),
					oneStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.RENAMENX, (cmd)->cmd.renamenx(key, newKey),
					oneStatusConverter)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.RENAMENX, (cmd)->cmd.renamenx(key, newKey),
					oneStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status renameNx(final byte[] key, final byte[] newKey) {
		final CommandArguments args = CommandArguments.create(key).add(newKey);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.RENAMENX, (cmd)->cmd.renamenx(key, newKey),
					oneStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.RENAMENX, (cmd)->cmd.renamenx(key, newKey),
					oneStatusConverter)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.RENAMENX, (cmd)->cmd.renamenx(key, newKey),
					oneStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status restore(final String key, final byte[] serializedValue, final int ttl) {
		final CommandArguments args = CommandArguments.create(key).add(serializedValue).add(ttl);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.RESTORE,
					(cmd)->cmd.restore(key, ttl, serializedValue), okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.RESTORE,
					(cmd)->cmd.restore(key, ttl, serializedValue), okStatusConverter)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.RESTORE, (cmd)->cmd.restore(key, ttl, serializedValue),
					okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status restore(final byte[] key, final byte[] serializedValue, final int ttl) {
		final CommandArguments args = CommandArguments.create(key).add(serializedValue).add(ttl);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.RESTORE,
					(cmd)->cmd.restore(key, ttl, serializedValue), okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.RESTORE,
					(cmd)->cmd.restore(key, ttl, serializedValue), okStatusConverter)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.RESTORE, (cmd)->cmd.restore(key, ttl, serializedValue),
					okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status restore(final String key, final byte[] serializedValue, final int ttl,
						  final RestoreArgument restoreArgument) {
		final CommandArguments args = CommandArguments.create(key).add(serializedValue).add(ttl).add(restoreArgument);
		final RestoreParams restoreParams = JedisRestoreParams.from(restoreArgument);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.RESTORE,
					(cmd)->cmd.restore(key, ttl, serializedValue, restoreParams), okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.RESTORE,
					(cmd)->cmd.restore(key, ttl, serializedValue, restoreParams), okStatusConverter)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.RESTORE,
					(cmd)->cmd.restore(key, ttl, serializedValue, restoreParams), okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status restore(final byte[] key, final byte[] serializedValue, final int ttl,
						  final RestoreArgument restoreArgument) {
		final CommandArguments args = CommandArguments.create(key).add(serializedValue).add(ttl).add(restoreArgument);
		final RestoreParams restoreParams = JedisRestoreParams.from(restoreArgument);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.RESTORE,
					(cmd)->cmd.restore(key, ttl, serializedValue, restoreParams), okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.RESTORE,
					(cmd)->cmd.restore(key, ttl, serializedValue, restoreParams), okStatusConverter)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.RESTORE,
					(cmd)->cmd.restore(key, ttl, serializedValue, restoreParams), okStatusConverter)
					.run(args);
		}
	}

	@Override
	public ScanResult<List<String>> scan(final String cursor) {
		final CommandArguments args = CommandArguments.create(cursor);
		final ScanResultConverter.ListScanResultConverter<String> listScanResultConverter =
				new ScanResultConverter.ListScanResultConverter<>();

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.SCAN, (cmd)->cmd.scan(cursor),
					listScanResultConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.SCAN, (cmd)->cmd.scan(cursor),
					listScanResultConverter)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.SCAN, (cmd)->cmd.scan(cursor), listScanResultConverter)
					.run(args);
		}
	}

	@Override
	public ScanResult<List<byte[]>> scan(final byte[] cursor) {
		final CommandArguments args = CommandArguments.create(cursor);
		final ScanResultConverter.ListScanResultConverter<byte[]> listScanResultConverter =
				new ScanResultConverter.ListScanResultConverter<>();

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.SCAN, (cmd)->cmd.scan(cursor),
					listScanResultConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.SCAN, (cmd)->cmd.scan(cursor),
					listScanResultConverter)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.SCAN, (cmd)->cmd.scan(cursor), listScanResultConverter)
					.run(args);
		}
	}

	@Override
	public ScanResult<List<String>> scan(final String cursor, final String pattern) {
		final CommandArguments args = CommandArguments.create(cursor).add(pattern);
		final ScanParams scanParams = new JedisScanParams(pattern);

		return scan(cursor, scanParams, args);
	}

	@Override
	public ScanResult<List<byte[]>> scan(final byte[] cursor, final byte[] pattern) {
		final CommandArguments args = CommandArguments.create(cursor).add(pattern);
		final ScanParams scanParams = new JedisScanParams(pattern);

		return scan(cursor, scanParams, args);
	}

	@Override
	public ScanResult<List<String>> scan(final String cursor, final int count) {
		final CommandArguments args = CommandArguments.create(cursor).add(count);
		final ScanParams scanParams = new JedisScanParams(count);

		return scan(cursor, scanParams, args);
	}

	@Override
	public ScanResult<List<byte[]>> scan(final byte[] cursor, final int count) {
		final CommandArguments args = CommandArguments.create(cursor).add(count);
		final ScanParams scanParams = new JedisScanParams(count);

		return scan(cursor, scanParams, args);
	}

	@Override
	public ScanResult<List<String>> scan(final String cursor, final String pattern, final int count) {
		final CommandArguments args = CommandArguments.create(cursor).add(pattern).add(count);
		final ScanParams scanParams = new JedisScanParams(pattern, count);

		return scan(cursor, scanParams, args);
	}

	@Override
	public ScanResult<List<byte[]>> scan(final byte[] cursor, final byte[] pattern, final int count) {
		final CommandArguments args = CommandArguments.create(cursor).add(pattern).add(count);
		final ScanParams scanParams = new JedisScanParams(pattern, count);

		return scan(cursor, scanParams, args);
	}

	@Override
	public List<String> sort(final String key) {
		final CommandArguments args = CommandArguments.create(key);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.SORT, (cmd)->cmd.sort(key), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.SORT, (cmd)->cmd.sort(key), (v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.SORT, (cmd)->cmd.sort(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public List<byte[]> sort(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.SORT, (cmd)->cmd.sort(key), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.SORT, (cmd)->cmd.sort(key), (v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.SORT, (cmd)->cmd.sort(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public List<String> sort(final String key, final SortArgument sortArgument) {
		final CommandArguments args = CommandArguments.create(key).add(sortArgument);
		final SortingParams sortingParams = JedisSortingParams.from(sortArgument);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.SORT, (cmd)->cmd.sort(key, sortingParams), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.SORT, (cmd)->cmd.sort(key, sortingParams),
					(v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.SORT, (cmd)->cmd.sort(key, sortingParams), (v)->v)
					.run(args);
		}
	}

	@Override
	public List<byte[]> sort(final byte[] key, final SortArgument sortArgument) {
		final CommandArguments args = CommandArguments.create(key).add(sortArgument);
		final SortingParams sortingParams = JedisSortingParams.from(sortArgument);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.SORT, (cmd)->cmd.sort(key, sortingParams), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.SORT, (cmd)->cmd.sort(key, sortingParams),
					(v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.SORT, (cmd)->cmd.sort(key, sortingParams), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long sort(final String key, final String destKey) {
		final CommandArguments args = CommandArguments.create(key).add(destKey);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.SORT, (cmd)->cmd.sort(key, destKey), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.SORT, (cmd)->cmd.sort(key, destKey), (v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.SORT, (cmd)->cmd.sort(key, destKey), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long sort(final byte[] key, final byte[] destKey) {
		final CommandArguments args = CommandArguments.create(key).add(destKey);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.SORT, (cmd)->cmd.sort(key, destKey), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.SORT, (cmd)->cmd.sort(key, destKey), (v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.SORT, (cmd)->cmd.sort(key, destKey), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long sort(final String key, final String destKey, final SortArgument sortArgument) {
		final CommandArguments args = CommandArguments.create(key).add(destKey).add(sortArgument);
		final SortingParams sortingParams = JedisSortingParams.from(sortArgument);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.SORT,
					(cmd)->cmd.sort(key, sortingParams, destKey), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.SORT,
					(cmd)->cmd.sort(key, sortingParams, destKey), (v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.SORT, (cmd)->cmd.sort(key, sortingParams, destKey),
					(v)->v)
					.run(args);
		}
	}

	@Override
	public Long sort(final byte[] key, final byte[] destKey, final SortArgument sortArgument) {
		final CommandArguments args = CommandArguments.create(key).add(destKey).add(sortArgument);
		final SortingParams sortingParams = JedisSortingParams.from(sortArgument);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.SORT,
					(cmd)->cmd.sort(key, sortingParams, destKey), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.SORT,
					(cmd)->cmd.sort(key, sortingParams, destKey), (v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.SORT, (cmd)->cmd.sort(key, sortingParams, destKey),
					(v)->v)
					.run(args);
		}
	}

	@Override
	public List<String> sortRo(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		final SortingParams sortingParams = new JedisSortingParams();

		return sortRo(key, sortingParams, args);
	}

	@Override
	public List<byte[]> sortRo(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		final SortingParams sortingParams = new JedisSortingParams();

		return sortRo(key, sortingParams, args);
	}

	@Override
	public List<String> sortRo(final String key, final SortArgument sortArgument) {
		final CommandArguments args = CommandArguments.create(key).add(sortArgument);
		final SortingParams sortingParams = JedisSortingParams.from(sortArgument);

		return sortRo(key, sortingParams, args);
	}

	@Override
	public List<byte[]> sortRo(final byte[] key, final SortArgument sortArgument) {
		final CommandArguments args = CommandArguments.create(key).add(sortArgument);
		final SortingParams sortingParams = JedisSortingParams.from(sortArgument);

		return sortRo(key, sortingParams, args);
	}

	@Override
	public Long touch(final String... keys) {
		final CommandArguments args = CommandArguments.create(keys);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.TOUCH, (cmd)->cmd.touch(keys), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.TOUCH, (cmd)->cmd.touch(keys), (v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.TOUCH, (cmd)->cmd.touch(keys), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long touch(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(keys);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.TOUCH, (cmd)->cmd.touch(keys), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.TOUCH, (cmd)->cmd.touch(keys), (v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.TOUCH, (cmd)->cmd.touch(keys), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long ttl(final String key) {
		final CommandArguments args = CommandArguments.create(key);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.TTL, (cmd)->cmd.ttl(key), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.TTL, (cmd)->cmd.ttl(key), (v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.TTL, (cmd)->cmd.ttl(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long ttl(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.TTL, (cmd)->cmd.ttl(key), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.TTL, (cmd)->cmd.ttl(key), (v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.TTL, (cmd)->cmd.ttl(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public Type type(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		final TypeConverter typeConverter = new TypeConverter();

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.TYPE, (cmd)->cmd.type(key), typeConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.TYPE, (cmd)->cmd.type(key), typeConverter)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.TYPE, (cmd)->cmd.type(key), typeConverter)
					.run(args);
		}
	}

	@Override
	public Type type(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		final TypeConverter typeConverter = new TypeConverter();

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.TYPE, (cmd)->cmd.type(key), typeConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.TYPE, (cmd)->cmd.type(key), typeConverter)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.TYPE, (cmd)->cmd.type(key), typeConverter)
					.run(args);
		}
	}

	@Override
	public Long unlink(final String... keys) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.UNLINK, (cmd)->cmd.unlink(keys), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.UNLINK, (cmd)->cmd.unlink(keys),
					(v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.UNLINK, (cmd)->cmd.unlink(keys), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long unlink(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.UNLINK, (cmd)->cmd.unlink(keys), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.UNLINK, (cmd)->cmd.unlink(keys),
					(v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.UNLINK, (cmd)->cmd.unlink(keys), (v)->v)
					.run(args);
		}
	}

	private Status copy(final String key, final String destKey, final boolean replace, final CommandArguments args) {
		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.COPY,
					(cmd)->cmd.copy(key, destKey, replace), booleanStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.COPY,
					(cmd)->cmd.copy(key, destKey, replace), booleanStatusConverter)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.COPY, (cmd)->cmd.copy(key, destKey, replace),
					booleanStatusConverter)
					.run(args);
		}
	}

	private Status copy(final byte[] key, final byte[] destKey, final boolean replace, final CommandArguments args) {
		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.COPY,
					(cmd)->cmd.copy(key, destKey, replace), booleanStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.COPY,
					(cmd)->cmd.copy(key, destKey, replace), booleanStatusConverter)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.COPY, (cmd)->cmd.copy(key, destKey, replace),
					booleanStatusConverter)
					.run(args);
		}
	}

	private Status copy(final String key, final String destKey, final int db, final boolean replace,
						final CommandArguments args) {
		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.COPY,
					(cmd)->cmd.copy(key, destKey, db, replace), booleanStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.COPY,
					(cmd)->cmd.copy(key, destKey, replace), booleanStatusConverter)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.COPY, (cmd)->cmd.copy(key, destKey, db, replace),
					booleanStatusConverter)
					.run(args);
		}
	}

	private Status copy(final byte[] key, final byte[] destKey, final int db, final boolean replace,
						final CommandArguments args) {
		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.COPY,
					(cmd)->cmd.copy(key, destKey, db, replace), booleanStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.COPY,
					(cmd)->cmd.copy(key, destKey, replace), booleanStatusConverter)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.COPY, (cmd)->cmd.copy(key, destKey, db, replace),
					booleanStatusConverter)
					.run(args);
		}
	}

	private Status migrate(final String host, final int port, final int db, final int timeout, final String[] keys,
						   final MigrateParams migrateParams, final CommandArguments args) {
		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.MIGRATE,
					(cmd)->cmd.migrate(host, port, db, timeout, migrateParams, keys), okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.MIGRATE,
					(cmd)->cmd.migrate(host, port, timeout, migrateParams, keys), okStatusConverter)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.MIGRATE,
					(cmd)->cmd.migrate(host, port, db, timeout, migrateParams, keys), okStatusConverter)
					.run(args);
		}
	}

	private Status migrate(final String host, final int port, final int db, final int timeout, final byte[][] keys,
						   final MigrateParams migrateParams, final CommandArguments args) {
		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.MIGRATE,
					(cmd)->cmd.migrate(host, port, db, timeout, migrateParams, keys), okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.MIGRATE,
					(cmd)->cmd.migrate(host, port, timeout, migrateParams, keys), okStatusConverter)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.MIGRATE,
					(cmd)->cmd.migrate(host, port, db, timeout, migrateParams, keys), okStatusConverter)
					.run(args);
		}
	}

	private List<String> sortRo(final String key, final SortingParams sortingParams, final CommandArguments args) {
		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.SORT_RO,
					(cmd)->cmd.sortReadonly(key, sortingParams), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.SORT_RO,
					(cmd)->cmd.sortReadonly(key, sortingParams), (v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.SORT_RO, (cmd)->cmd.sortReadonly(key, sortingParams),
					(v)->v)
					.run(args);
		}
	}

	private List<byte[]> sortRo(final byte[] key, final SortingParams sortingParams, final CommandArguments args) {
		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.SORT_RO,
					(cmd)->cmd.sortReadonly(key, sortingParams), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.SORT_RO,
					(cmd)->cmd.sortReadonly(key, sortingParams), (v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.SORT_RO, (cmd)->cmd.sortReadonly(key, sortingParams),
					(v)->v)
					.run(args);
		}
	}

	private ScanResult<List<String>> scan(final String cursor, final ScanParams scanParams,
										  final CommandArguments args) {
		final ScanResultConverter.ListScanResultConverter<String> listScanResultConverter =
				new ScanResultConverter.ListScanResultConverter<>();

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.SCAN, (cmd)->cmd.scan(cursor, scanParams),
					listScanResultConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.SCAN, (cmd)->cmd.scan(cursor, scanParams),
					listScanResultConverter)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.SCAN, (cmd)->cmd.scan(cursor, scanParams),
					listScanResultConverter)
					.run(args);
		}
	}

	private ScanResult<List<byte[]>> scan(final byte[] cursor, final ScanParams scanParams,
										  final CommandArguments args) {
		final ScanResultConverter.ListScanResultConverter<byte[]> listScanResultConverter =
				new ScanResultConverter.ListScanResultConverter<>();

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, Command.SCAN, (cmd)->cmd.scan(cursor, scanParams),
					listScanResultConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, Command.SCAN, (cmd)->cmd.scan(cursor, scanParams),
					listScanResultConverter)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, Command.SCAN, (cmd)->cmd.scan(cursor, scanParams),
					listScanResultConverter)
					.run(args);
		}
	}

}
