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
import com.buession.redis.client.jedis.JedisStandaloneClient;
import com.buession.redis.core.ExpireOption;
import com.buession.redis.core.MigrateOperation;
import com.buession.redis.core.ObjectEncoding;
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.Type;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.ProtocolCommand;
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
 * Jedis 单机模式 Key 命令操作
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public final class JedisKeyOperations extends AbstractKeyOperations<JedisStandaloneClient> {

	public JedisKeyOperations(final JedisStandaloneClient client) {
		super(client);
	}

	@Override
	public Long del(final String... keys) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.DEL, (cmd)->cmd.del(keys), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.DEL, (cmd)->cmd.del(keys), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.DEL, (cmd)->cmd.del(keys), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long del(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.DEL, (cmd)->cmd.del(keys), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.DEL, (cmd)->cmd.del(keys), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.DEL, (cmd)->cmd.del(keys), (v)->v)
					.run(args);
		}
	}

	@Override
	public String dump(final String key) {
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.DUMP, (cmd)->cmd.dump(key), SafeEncoder::encode)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.DUMP, (cmd)->cmd.dump(key),
					SafeEncoder::encode)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.DUMP, (cmd)->cmd.dump(key), SafeEncoder::encode)
					.run(args);
		}
	}

	@Override
	public byte[] dump(final byte[] key) {
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.DUMP, (cmd)->cmd.dump(key), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.DUMP, (cmd)->cmd.dump(key), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.DUMP, (cmd)->cmd.dump(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public Boolean exists(final String key) {
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.EXISTS, (cmd)->cmd.exists(key), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.EXISTS, (cmd)->cmd.exists(key), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.EXISTS, (cmd)->cmd.exists(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public Boolean exists(final byte[] key) {
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.EXISTS, (cmd)->cmd.exists(key), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.EXISTS, (cmd)->cmd.exists(key), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.EXISTS, (cmd)->cmd.exists(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long exists(final String... keys) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.EXISTS, (cmd)->cmd.exists(keys), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.EXISTS, (cmd)->cmd.exists(keys), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.EXISTS, (cmd)->cmd.exists(keys), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long exists(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.EXISTS, (cmd)->cmd.exists(keys), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.EXISTS, (cmd)->cmd.exists(keys), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.EXISTS, (cmd)->cmd.exists(keys), (v)->v)
					.run(args);
		}
	}

	@Override
	public Status expire(final String key, final int lifetime) {
		final CommandArguments args = CommandArguments.create("key", key).put("lifetime", lifetime);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.EXPIRE, (cmd)->cmd.expire(key, lifetime),
					oneStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.EXPIRE, (cmd)->cmd.expire(key, lifetime),
					oneStatusConverter)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.EXPIRE, (cmd)->cmd.expire(key, lifetime),
					oneStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status expire(final byte[] key, final int lifetime) {
		final CommandArguments args = CommandArguments.create("key", key).put("lifetime", lifetime);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.EXPIRE, (cmd)->cmd.expire(key, lifetime),
					oneStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.EXPIRE, (cmd)->cmd.expire(key, lifetime),
					oneStatusConverter)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.EXPIRE, (cmd)->cmd.expire(key, lifetime),
					oneStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status expire(final String key, final int lifetime, final ExpireOption expireOption) {
		final CommandArguments args = CommandArguments.create("key", key).put("lifetime", lifetime)
				.put("expireOption", expireOption);
		final ExpiryOption expiryOption = (new ExpireOptionConverter()).convert(expireOption);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.EXPIRE,
					(cmd)->cmd.expire(key, lifetime, expiryOption), oneStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.EXPIRE,
					(cmd)->cmd.expire(key, lifetime, expiryOption), oneStatusConverter)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.EXPIRE, (cmd)->cmd.expire(key, lifetime, expiryOption),
					oneStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status expire(final byte[] key, final int lifetime, final ExpireOption expireOption) {
		final CommandArguments args = CommandArguments.create("key", key).put("lifetime", lifetime)
				.put("expireOption", expireOption);
		final ExpiryOption expiryOption = (new ExpireOptionConverter()).convert(expireOption);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.EXPIRE,
					(cmd)->cmd.expire(key, lifetime, expiryOption), oneStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.EXPIRE,
					(cmd)->cmd.expire(key, lifetime, expiryOption), oneStatusConverter)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.EXPIRE, (cmd)->cmd.expire(key, lifetime, expiryOption),
					oneStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status expireAt(final String key, final long unixTimestamp) {
		final CommandArguments args = CommandArguments.create("key", key).put("unixTimestamp", unixTimestamp);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.EXPIREAT, (cmd)->cmd.expireAt(key, unixTimestamp),
					oneStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.EXPIREAT,
					(cmd)->cmd.expireAt(key, unixTimestamp), oneStatusConverter)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.EXPIREAT, (cmd)->cmd.expireAt(key, unixTimestamp),
					oneStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status expireAt(final byte[] key, final long unixTimestamp) {
		final CommandArguments args = CommandArguments.create("key", key).put("unixTimestamp", unixTimestamp);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.EXPIREAT, (cmd)->cmd.expireAt(key, unixTimestamp),
					oneStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.EXPIREAT,
					(cmd)->cmd.expireAt(key, unixTimestamp), oneStatusConverter)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.EXPIREAT, (cmd)->cmd.expireAt(key, unixTimestamp),
					oneStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status pExpire(final String key, final int lifetime) {
		final CommandArguments args = CommandArguments.create("key", key).put("lifetime", lifetime);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.PEXPIRE, (cmd)->cmd.pexpire(key, lifetime),
					oneStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.PEXPIRE, (cmd)->cmd.pexpire(key, lifetime),
					oneStatusConverter)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.PEXPIRE, (cmd)->cmd.pexpire(key, lifetime),
					oneStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status pExpire(final byte[] key, final int lifetime) {
		final CommandArguments args = CommandArguments.create("key", key).put("lifetime", lifetime);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.PEXPIRE, (cmd)->cmd.pexpire(key, lifetime),
					oneStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.PEXPIRE, (cmd)->cmd.pexpire(key, lifetime),
					oneStatusConverter)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.PEXPIRE, (cmd)->cmd.pexpire(key, lifetime),
					oneStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status pExpireAt(final String key, final long unixTimestamp) {
		final CommandArguments args = CommandArguments.create("key", key).put("unixTimestamp", unixTimestamp);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.PEXPIREAT,
					(cmd)->cmd.pexpireAt(key, unixTimestamp), oneStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.PEXPIREAT,
					(cmd)->cmd.pexpireAt(key, unixTimestamp), oneStatusConverter)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.PEXPIREAT, (cmd)->cmd.pexpireAt(key, unixTimestamp),
					oneStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status pExpireAt(final byte[] key, final long unixTimestamp) {
		final CommandArguments args = CommandArguments.create("key", key).put("unixTimestamp", unixTimestamp);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.PEXPIREAT,
					(cmd)->cmd.pexpireAt(key, unixTimestamp), oneStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.PEXPIREAT,
					(cmd)->cmd.pexpireAt(key, unixTimestamp), oneStatusConverter)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.PEXPIREAT, (cmd)->cmd.pexpireAt(key, unixTimestamp),
					oneStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status persist(final String key) {
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.PERSIST, (cmd)->cmd.persist(key),
					oneStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.PERSIST, (cmd)->cmd.persist(key),
					oneStatusConverter)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.PERSIST, (cmd)->cmd.persist(key), oneStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status persist(final byte[] key) {
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.PERSIST, (cmd)->cmd.persist(key),
					oneStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.PERSIST, (cmd)->cmd.persist(key),
					oneStatusConverter)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.PERSIST, (cmd)->cmd.persist(key), oneStatusConverter)
					.run(args);
		}
	}

	@Override
	public Long ttl(final String key) {
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.TTL, (cmd)->cmd.ttl(key), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.TTL, (cmd)->cmd.ttl(key), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.TTL, (cmd)->cmd.ttl(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long ttl(final byte[] key) {
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.TTL, (cmd)->cmd.ttl(key), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.TTL, (cmd)->cmd.ttl(key), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.TTL, (cmd)->cmd.ttl(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long pTtl(final String key) {
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.PTTL, (cmd)->cmd.pttl(key), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.PTTL, (cmd)->cmd.pttl(key), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.PTTL, (cmd)->cmd.pttl(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long pTtl(final byte[] key) {
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.PTTL, (cmd)->cmd.pttl(key), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.PTTL, (cmd)->cmd.pttl(key), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.PTTL, (cmd)->cmd.pttl(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public Status copy(final String key, final String destKey) {
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey);
		return copy(key, destKey, false, args);
	}

	@Override
	public Status copy(final byte[] key, final byte[] destKey) {
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey);
		return copy(key, destKey, false, args);
	}

	@Override
	public Status copy(final String key, final String destKey, final int db) {
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey).put("db", db);
		return copy(key, destKey, db, false, args);
	}

	@Override
	public Status copy(final byte[] key, final byte[] destKey, final int db) {
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey).put("db", db);
		return copy(key, destKey, db, false, args);
	}

	@Override
	public Status copy(final String key, final String destKey, final boolean replace) {
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey)
				.put("replace", replace);
		return copy(key, destKey, replace, args);
	}

	@Override
	public Status copy(final byte[] key, final byte[] destKey, final boolean replace) {
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey)
				.put("replace", replace);
		return copy(key, destKey, replace, args);
	}

	@Override
	public Status copy(final String key, final String destKey, final int db, final boolean replace) {
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey).put("db", db)
				.put("replace", replace);
		return copy(key, destKey, db, replace, args);
	}

	@Override
	public Status copy(final byte[] key, final byte[] destKey, final int db, final boolean replace) {
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey).put("db", db)
				.put("replace", replace);
		return copy(key, destKey, db, replace, args);
	}

	@Override
	public Status move(final String key, final int db) {
		final CommandArguments args = CommandArguments.create("key", key).put("db", db);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.MOVE, (cmd)->cmd.move(key, db),
					oneStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<Status, Status>(client, ProtocolCommand.MOVE)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.MOVE, (cmd)->cmd.move(key, db), oneStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status move(final byte[] key, final int db) {
		final CommandArguments args = CommandArguments.create("key", key).put("db", db);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.MOVE, (cmd)->cmd.move(key, db),
					oneStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<Status, Status>(client, ProtocolCommand.MOVE)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.MOVE, (cmd)->cmd.move(key, db), oneStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final int timeout, final String... keys) {
		final CommandArguments args = CommandArguments.create("host", host).put("port", port).put("db", db)
				.put("timeout", timeout).put("keys", (Object[]) keys);
		final MigrateParams migrateParams = new JedisMigrateParams();

		return migrate(host, port, db, timeout, keys, migrateParams, args);
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final int timeout, final byte[]... keys) {
		final CommandArguments args = CommandArguments.create("host", host).put("port", port).put("db", db)
				.put("timeout", timeout).put("keys", (Object[]) keys);
		final MigrateParams migrateParams = new JedisMigrateParams();

		return migrate(host, port, db, timeout, keys, migrateParams, args);
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final int timeout,
						  final MigrateOperation operation, final String... keys) {
		final CommandArguments args = CommandArguments.create("host", host).put("port", port).put("db", db)
				.put("timeout", timeout).put("operation", operation).put("keys", (Object[]) keys);
		final MigrateParams migrateParams = new JedisMigrateParams(operation);

		return migrate(host, port, db, timeout, keys, migrateParams, args);
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final int timeout,
						  final MigrateOperation operation, final byte[]... keys) {
		final CommandArguments args = CommandArguments.create("host", host).put("port", port).put("db", db)
				.put("timeout", timeout).put("operation", operation).put("keys", (Object[]) keys);
		final MigrateParams migrateParams = new JedisMigrateParams(operation);

		return migrate(host, port, db, timeout, keys, migrateParams, args);
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final String password, final int timeout,
						  final String... keys) {
		final CommandArguments args = CommandArguments.create("host", host).put("port", port).put("db", db)
				.put("password", password).put("timeout", timeout).put("keys", (Object[]) keys);
		final MigrateParams migrateParams = new JedisMigrateParams(password);

		return migrate(host, port, db, timeout, keys, migrateParams, args);
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final byte[] password, final int timeout,
						  final byte[]... keys) {
		final CommandArguments args = CommandArguments.create("host", host).put("port", port).put("db", db)
				.put("password", password).put("timeout", timeout).put("keys", (Object[]) keys);
		final MigrateParams migrateParams = new JedisMigrateParams(password);

		return migrate(host, port, db, timeout, keys, migrateParams, args);
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final String password, final int timeout,
						  final MigrateOperation operation, final String... keys) {
		final CommandArguments args = CommandArguments.create("host", host).put("port", port).put("db", db)
				.put("password", password).put("timeout", timeout).put("operation", operation)
				.put("keys", (Object[]) keys);
		final MigrateParams migrateParams = new JedisMigrateParams(operation, password);

		return migrate(host, port, db, timeout, keys, migrateParams, args);
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final byte[] password, final int timeout,
						  final MigrateOperation operation, final byte[]... keys) {
		final CommandArguments args = CommandArguments.create("host", host).put("port", port).put("db", db)
				.put("password", password).put("timeout", timeout).put("operation", operation)
				.put("keys", (Object[]) keys);
		final MigrateParams migrateParams = new JedisMigrateParams(operation, password);

		return migrate(host, port, db, timeout, keys, migrateParams, args);
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final String user, final String password,
						  final int timeout, final String... keys) {
		final CommandArguments args = CommandArguments.create("host", host).put("port", port).put("db", db)
				.put("user", user).put("password", password).put("timeout", timeout).put("keys", (Object[]) keys);
		final MigrateParams migrateParams = new JedisMigrateParams(user, password);

		return migrate(host, port, db, timeout, keys, migrateParams, args);
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final byte[] user, final byte[] password,
						  final int timeout, final byte[]... keys) {
		final CommandArguments args = CommandArguments.create("host", host).put("port", port).put("db", db)
				.put("user", user).put("password", password).put("timeout", timeout).put("keys", (Object[]) keys);
		final MigrateParams migrateParams = new JedisMigrateParams(user, password);

		return migrate(host, port, db, timeout, keys, migrateParams, args);
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final String user, final String password,
						  final int timeout, final MigrateOperation operation, final String... keys) {
		final CommandArguments args = CommandArguments.create("host", host).put("port", port).put("db", db)
				.put("user", user).put("password", password).put("timeout", timeout).put("operation", operation)
				.put("keys", (Object[]) keys);
		final MigrateParams migrateParams = new JedisMigrateParams(operation, user, password);

		return migrate(host, port, db, timeout, keys, migrateParams, args);
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final byte[] user, final byte[] password,
						  final int timeout, final MigrateOperation operation, final byte[]... keys) {
		final CommandArguments args = CommandArguments.create("host", host).put("port", port).put("db", db)
				.put("user", user).put("password", password).put("timeout", timeout).put("operation", operation)
				.put("keys", (Object[]) keys);
		final MigrateParams migrateParams = new JedisMigrateParams(operation, user, password);

		return migrate(host, port, db, timeout, keys, migrateParams, args);
	}

	@Override
	public Set<String> keys(final String pattern) {
		final CommandArguments args = CommandArguments.create("pattern", pattern);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.KEYS, (cmd)->cmd.keys(pattern), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.KEYS, (cmd)->cmd.keys(pattern), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.KEYS, (cmd)->cmd.keys(pattern), (v)->v)
					.run(args);
		}
	}

	@Override
	public Set<byte[]> keys(final byte[] pattern) {
		final CommandArguments args = CommandArguments.create("pattern", pattern);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.KEYS, (cmd)->cmd.keys(pattern), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.KEYS, (cmd)->cmd.keys(pattern), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.KEYS, (cmd)->cmd.keys(pattern), (v)->v)
					.run(args);
		}
	}

	@Override
	public String randomKey() {
		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.RANDOMKEY, (cmd)->cmd.randomKey(), (v)->v)
					.run();
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.RANDOMKEY, (cmd)->cmd.randomKey(), (v)->v)
					.run();
		}else{
			return new JedisCommand<>(client, ProtocolCommand.RANDOMKEY, (cmd)->cmd.randomKey(), (v)->v)
					.run();
		}
	}

	@Override
	public Status rename(final String key, final String newKey) {
		final CommandArguments args = CommandArguments.create("key", key).put("newKey", newKey);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.RENAME, (cmd)->cmd.rename(key, newKey),
					okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.RENAME, (cmd)->cmd.rename(key, newKey),
					okStatusConverter)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.RENAME, (cmd)->cmd.rename(key, newKey), okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status rename(final byte[] key, final byte[] newKey) {
		final CommandArguments args = CommandArguments.create("key", key).put("newKey", newKey);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.RENAME, (cmd)->cmd.rename(key, newKey),
					okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.RENAME, (cmd)->cmd.rename(key, newKey),
					okStatusConverter)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.RENAME, (cmd)->cmd.rename(key, newKey), okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status renameNx(final String key, final String newKey) {
		final CommandArguments args = CommandArguments.create("key", key).put("newKey", newKey);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.RENAMENX, (cmd)->cmd.renamenx(key, newKey),
					oneStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.RENAMENX, (cmd)->cmd.renamenx(key, newKey),
					oneStatusConverter)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.RENAMENX, (cmd)->cmd.renamenx(key, newKey),
					oneStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status renameNx(final byte[] key, final byte[] newKey) {
		final CommandArguments args = CommandArguments.create("key", key).put("newKey", newKey);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.RENAMENX, (cmd)->cmd.renamenx(key, newKey),
					oneStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.RENAMENX, (cmd)->cmd.renamenx(key, newKey),
					oneStatusConverter)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.RENAMENX, (cmd)->cmd.renamenx(key, newKey),
					oneStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status restore(final String key, final byte[] serializedValue, final int ttl) {
		final CommandArguments args = CommandArguments.create("key", key).put("serializedValue", serializedValue)
				.put("ttl", ttl);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.RESTORE,
					(cmd)->cmd.restore(key, ttl, serializedValue), okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.RESTORE,
					(cmd)->cmd.restore(key, ttl, serializedValue), okStatusConverter)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.RESTORE, (cmd)->cmd.restore(key, ttl, serializedValue),
					okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status restore(final byte[] key, final byte[] serializedValue, final int ttl) {
		final CommandArguments args = CommandArguments.create("key", key).put("serializedValue", serializedValue)
				.put("ttl", ttl);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.RESTORE,
					(cmd)->cmd.restore(key, ttl, serializedValue), okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.RESTORE,
					(cmd)->cmd.restore(key, ttl, serializedValue), okStatusConverter)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.RESTORE, (cmd)->cmd.restore(key, ttl, serializedValue),
					okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status restore(final String key, final byte[] serializedValue, final int ttl,
						  final RestoreArgument argument) {
		final CommandArguments args = CommandArguments.create("key", key).put("serializedValue", serializedValue)
				.put("ttl", ttl).put("argument", argument);
		final RestoreParams restoreParams = JedisRestoreParams.from(argument);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.RESTORE,
					(cmd)->cmd.restore(key, ttl, serializedValue, restoreParams), okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.RESTORE,
					(cmd)->cmd.restore(key, ttl, serializedValue, restoreParams), okStatusConverter)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.RESTORE,
					(cmd)->cmd.restore(key, ttl, serializedValue, restoreParams), okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status restore(final byte[] key, final byte[] serializedValue, final int ttl,
						  final RestoreArgument argument) {
		final CommandArguments args = CommandArguments.create("key", key).put("serializedValue", serializedValue)
				.put("ttl", ttl).put("argument", argument);
		final RestoreParams restoreParams = JedisRestoreParams.from(argument);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.RESTORE,
					(cmd)->cmd.restore(key, ttl, serializedValue, restoreParams), okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.RESTORE,
					(cmd)->cmd.restore(key, ttl, serializedValue, restoreParams), okStatusConverter)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.RESTORE,
					(cmd)->cmd.restore(key, ttl, serializedValue, restoreParams), okStatusConverter)
					.run(args);
		}
	}

	@Override
	public ScanResult<List<String>> scan(final String cursor) {
		final CommandArguments args = CommandArguments.create("cursor", cursor);
		final ScanResultConverter.ListScanResultConverter<String> listScanResultConverter =
				new ScanResultConverter.ListScanResultConverter<>();

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.SCAN, (cmd)->cmd.scan(cursor),
					listScanResultConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.SCAN, (cmd)->cmd.scan(cursor),
					listScanResultConverter)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.SCAN, (cmd)->cmd.scan(cursor), listScanResultConverter)
					.run(args);
		}
	}

	@Override
	public ScanResult<List<byte[]>> scan(final byte[] cursor) {
		final CommandArguments args = CommandArguments.create("cursor", cursor);
		final ScanResultConverter.ListScanResultConverter<byte[]> listScanResultConverter =
				new ScanResultConverter.ListScanResultConverter<>();

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.SCAN, (cmd)->cmd.scan(cursor),
					listScanResultConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.SCAN, (cmd)->cmd.scan(cursor),
					listScanResultConverter)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.SCAN, (cmd)->cmd.scan(cursor), listScanResultConverter)
					.run(args);
		}
	}

	@Override
	public ScanResult<List<String>> scan(final String cursor, final String pattern) {
		final CommandArguments args = CommandArguments.create("cursor", cursor).put("pattern", pattern);
		final ScanParams scanParams = new JedisScanParams(pattern);

		return scan(cursor, scanParams, args);
	}

	@Override
	public ScanResult<List<byte[]>> scan(final byte[] cursor, final byte[] pattern) {
		final CommandArguments args = CommandArguments.create("cursor", cursor).put("pattern", pattern);
		final ScanParams scanParams = new JedisScanParams(pattern);

		return scan(cursor, scanParams, args);
	}

	@Override
	public ScanResult<List<String>> scan(final String cursor, final int count) {
		final CommandArguments args = CommandArguments.create("cursor", cursor).put("count", count);
		final ScanParams scanParams = new JedisScanParams(count);

		return scan(cursor, scanParams, args);
	}

	@Override
	public ScanResult<List<byte[]>> scan(final byte[] cursor, final int count) {
		final CommandArguments args = CommandArguments.create("cursor", cursor).put("count", count);
		final ScanParams scanParams = new JedisScanParams(count);

		return scan(cursor, scanParams, args);
	}

	@Override
	public ScanResult<List<String>> scan(final String cursor, final String pattern, final int count) {
		final CommandArguments args = CommandArguments.create("cursor", cursor).put("pattern", pattern)
				.put("count", count);
		final ScanParams scanParams = new JedisScanParams(pattern, count);

		return scan(cursor, scanParams, args);
	}

	@Override
	public ScanResult<List<byte[]>> scan(final byte[] cursor, final byte[] pattern, final int count) {
		final CommandArguments args = CommandArguments.create("cursor", cursor).put("pattern", pattern)
				.put("count", count);
		final ScanParams scanParams = new JedisScanParams(pattern, count);

		return scan(cursor, scanParams, args);
	}

	@Override
	public List<String> sort(final String key) {
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.SORT, (cmd)->cmd.sort(key), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.SORT, (cmd)->cmd.sort(key), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.SORT, (cmd)->cmd.sort(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public List<byte[]> sort(final byte[] key) {
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.SORT, (cmd)->cmd.sort(key), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.SORT, (cmd)->cmd.sort(key), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.SORT, (cmd)->cmd.sort(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public List<String> sort(final String key, final SortArgument sortArgument) {
		final CommandArguments args = CommandArguments.create("key", key).put("sortArgument", sortArgument);
		final SortingParams sortingParams = JedisSortingParams.from(sortArgument);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.SORT, (cmd)->cmd.sort(key, sortingParams), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.SORT, (cmd)->cmd.sort(key, sortingParams),
					(v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.SORT, (cmd)->cmd.sort(key, sortingParams), (v)->v)
					.run(args);
		}
	}

	@Override
	public List<byte[]> sort(final byte[] key, final SortArgument sortArgument) {
		final CommandArguments args = CommandArguments.create("key", key).put("sortArgument", sortArgument);
		final SortingParams sortingParams = JedisSortingParams.from(sortArgument);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.SORT, (cmd)->cmd.sort(key, sortingParams), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.SORT, (cmd)->cmd.sort(key, sortingParams),
					(v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.SORT, (cmd)->cmd.sort(key, sortingParams), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long sort(final String key, final String destKey) {
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.SORT, (cmd)->cmd.sort(key, destKey), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.SORT, (cmd)->cmd.sort(key, destKey), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.SORT, (cmd)->cmd.sort(key, destKey), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long sort(final byte[] key, final byte[] destKey) {
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.SORT, (cmd)->cmd.sort(key, destKey), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.SORT, (cmd)->cmd.sort(key, destKey), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.SORT, (cmd)->cmd.sort(key, destKey), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long sort(final String key, final String destKey, final SortArgument sortArgument) {
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey)
				.put("sortArgument", sortArgument);
		final SortingParams sortingParams = JedisSortingParams.from(sortArgument);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.SORT,
					(cmd)->cmd.sort(key, sortingParams, destKey), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.SORT,
					(cmd)->cmd.sort(key, sortingParams, destKey), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.SORT, (cmd)->cmd.sort(key, sortingParams, destKey),
					(v)->v)
					.run(args);
		}
	}

	@Override
	public Long sort(final byte[] key, final byte[] destKey, final SortArgument sortArgument) {
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey)
				.put("sortArgument", sortArgument);
		final SortingParams sortingParams = JedisSortingParams.from(sortArgument);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.SORT,
					(cmd)->cmd.sort(key, sortingParams, destKey), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.SORT,
					(cmd)->cmd.sort(key, sortingParams, destKey), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.SORT, (cmd)->cmd.sort(key, sortingParams, destKey),
					(v)->v)
					.run(args);
		}
	}

	@Override
	public Long touch(final String... keys) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.TOUCH, (cmd)->cmd.touch(keys), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.TOUCH, (cmd)->cmd.touch(keys), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.TOUCH, (cmd)->cmd.touch(keys), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long touch(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.TOUCH, (cmd)->cmd.touch(keys), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.TOUCH, (cmd)->cmd.touch(keys), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.TOUCH, (cmd)->cmd.touch(keys), (v)->v)
					.run(args);
		}
	}

	@Override
	public Type type(final String key) {
		final CommandArguments args = CommandArguments.create("key", key);
		final TypeConverter typeConverter = new TypeConverter();

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.TYPE, (cmd)->cmd.type(key), typeConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.TYPE, (cmd)->cmd.type(key), typeConverter)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.TYPE, (cmd)->cmd.type(key), typeConverter)
					.run(args);
		}
	}

	@Override
	public Type type(final byte[] key) {
		final CommandArguments args = CommandArguments.create("key", key);
		final TypeConverter typeConverter = new TypeConverter();

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.TYPE, (cmd)->cmd.type(key), typeConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.TYPE, (cmd)->cmd.type(key), typeConverter)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.TYPE, (cmd)->cmd.type(key), typeConverter)
					.run(args);
		}
	}

	@Override
	public Long unlink(final String... keys) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.UNLINK, (cmd)->cmd.unlink(keys), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.UNLINK, (cmd)->cmd.unlink(keys), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.UNLINK, (cmd)->cmd.unlink(keys), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long unlink(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.UNLINK, (cmd)->cmd.unlink(keys), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.UNLINK, (cmd)->cmd.unlink(keys), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.UNLINK, (cmd)->cmd.unlink(keys), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long wait(final int replicas, final int timeout) {
		final CommandArguments args = CommandArguments.create("replicas", replicas).put("timeout", timeout);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.WAIT, (cmd)->cmd.waitReplicas(replicas, timeout),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.WAIT,
					(cmd)->cmd.waitReplicas(replicas, timeout), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.WAIT, (cmd)->cmd.waitReplicas(replicas, timeout), (v)->v)
					.run(args);
		}
	}

	@Override
	public ObjectEncoding objectEncoding(final String key) {
		final CommandArguments args = CommandArguments.create("key", key);
		final ObjectEncodingConverter objectEncodingConverter = new ObjectEncodingConverter();

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.OBJECT_ENCODING, (cmd)->cmd.objectEncoding(key),
					objectEncodingConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.OBJECT_ENCODING,
					(cmd)->cmd.objectEncoding(key),
					objectEncodingConverter)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.OBJECT_ENCODING, (cmd)->cmd.objectEncoding(key),
					objectEncodingConverter)
					.run(args);
		}
	}

	@Override
	public ObjectEncoding objectEncoding(final byte[] key) {
		final CommandArguments args = CommandArguments.create("key", key);
		final BinaryObjectEncodingConverter binaryObjectEncodingConverter = new BinaryObjectEncodingConverter();

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.OBJECT_ENCODING, (cmd)->cmd.objectEncoding(key),
					binaryObjectEncodingConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.OBJECT_ENCODING,
					(cmd)->cmd.objectEncoding(key), binaryObjectEncodingConverter)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.OBJECT_ENCODING, (cmd)->cmd.objectEncoding(key),
					binaryObjectEncodingConverter)
					.run(args);
		}
	}

	@Override
	public Long objectFreq(final String key) {
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.OBJECT_REFQ, (cmd)->cmd.objectFreq(key), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.OBJECT_REFQ, (cmd)->cmd.objectFreq(key),
					(v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.OBJECT_REFQ, (cmd)->cmd.objectFreq(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long objectFreq(final byte[] key) {
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.OBJECT_REFQ, (cmd)->cmd.objectFreq(key), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.OBJECT_REFQ, (cmd)->cmd.objectFreq(key),
					(v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.OBJECT_REFQ, (cmd)->cmd.objectFreq(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long objectIdleTime(final String key) {
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.OBJECT_IDLETIME, (cmd)->cmd.objectIdletime(key),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.OBJECT_IDLETIME,
					(cmd)->cmd.objectIdletime(key), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.OBJECT_IDLETIME, (cmd)->cmd.objectIdletime(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long objectIdleTime(final byte[] key) {
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.OBJECT_IDLETIME, (cmd)->cmd.objectIdletime(key),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.OBJECT_IDLETIME,
					(cmd)->cmd.objectIdletime(key), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.OBJECT_IDLETIME, (cmd)->cmd.objectIdletime(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long objectRefcount(final String key) {
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.OBJECT_REFCOUNT, (cmd)->cmd.objectRefcount(key),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.OBJECT_REFCOUNT,
					(cmd)->cmd.objectRefcount(key), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.OBJECT_REFCOUNT, (cmd)->cmd.objectRefcount(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long objectRefcount(final byte[] key) {
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.OBJECT_REFCOUNT, (cmd)->cmd.objectRefcount(key),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.OBJECT_REFCOUNT,
					(cmd)->cmd.objectRefcount(key), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.OBJECT_REFCOUNT, (cmd)->cmd.objectRefcount(key), (v)->v)
					.run(args);
		}
	}

	private Status copy(final String key, final String destKey, final boolean replace, final CommandArguments args) {
		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.COPY, (cmd)->cmd.copy(key, destKey, replace),
					booleanStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.COPY, (cmd)->cmd.copy(key, destKey, replace),
					booleanStatusConverter)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.COPY, (cmd)->cmd.copy(key, destKey, replace),
					booleanStatusConverter)
					.run(args);
		}
	}

	private Status copy(final byte[] key, final byte[] destKey, final boolean replace, final CommandArguments args) {
		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.COPY, (cmd)->cmd.copy(key, destKey, replace),
					booleanStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.COPY, (cmd)->cmd.copy(key, destKey, replace),
					booleanStatusConverter)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.COPY, (cmd)->cmd.copy(key, destKey, replace),
					booleanStatusConverter)
					.run(args);
		}
	}

	private Status copy(final String key, final String destKey, final int db, final boolean replace,
						final CommandArguments args) {
		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.COPY, (cmd)->cmd.copy(key, destKey, db, replace),
					booleanStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.COPY,
					(cmd)->cmd.copy(key, destKey, replace), booleanStatusConverter)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.COPY, (cmd)->cmd.copy(key, destKey, db, replace),
					booleanStatusConverter)
					.run(args);
		}
	}

	private Status copy(final byte[] key, final byte[] destKey, final int db, final boolean replace,
						final CommandArguments args) {
		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.COPY, (cmd)->cmd.copy(key, destKey, db, replace),
					booleanStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.COPY,
					(cmd)->cmd.copy(key, destKey, replace), booleanStatusConverter)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.COPY, (cmd)->cmd.copy(key, destKey, db, replace),
					booleanStatusConverter)
					.run(args);
		}
	}

	private Status migrate(final String host, final int port, final int db, final int timeout, final String[] keys,
						   final MigrateParams migrateParams, final CommandArguments args) {
		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.MIGRATE,
					(cmd)->cmd.migrate(host, port, db, timeout, migrateParams, keys), okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.MIGRATE,
					(cmd)->cmd.migrate(host, port, timeout, migrateParams, keys), okStatusConverter)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.MIGRATE,
					(cmd)->cmd.migrate(host, port, db, timeout, migrateParams, keys), okStatusConverter)
					.run(args);
		}
	}

	private Status migrate(final String host, final int port, final int db, final int timeout, final byte[][] keys,
						   final MigrateParams migrateParams, final CommandArguments args) {
		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.MIGRATE,
					(cmd)->cmd.migrate(host, port, db, timeout, migrateParams, keys), okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.MIGRATE,
					(cmd)->cmd.migrate(host, port, timeout, migrateParams, keys), okStatusConverter)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.MIGRATE,
					(cmd)->cmd.migrate(host, port, db, timeout, migrateParams, keys), okStatusConverter)
					.run(args);
		}
	}

	private ScanResult<List<String>> scan(final String cursor, final ScanParams scanParams,
										  final CommandArguments args) {
		final ScanResultConverter.ListScanResultConverter<String> listScanResultConverter =
				new ScanResultConverter.ListScanResultConverter<>();

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.SCAN, (cmd)->cmd.scan(cursor, scanParams),
					listScanResultConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.SCAN, (cmd)->cmd.scan(cursor, scanParams),
					listScanResultConverter)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.SCAN, (cmd)->cmd.scan(cursor, scanParams),
					listScanResultConverter)
					.run(args);
		}
	}

	private ScanResult<List<byte[]>> scan(final byte[] cursor, final ScanParams scanParams,
										  final CommandArguments args) {
		final ScanResultConverter.ListScanResultConverter<byte[]> listScanResultConverter =
				new ScanResultConverter.ListScanResultConverter<>();

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.SCAN, (cmd)->cmd.scan(cursor, scanParams),
					listScanResultConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.SCAN, (cmd)->cmd.scan(cursor, scanParams),
					listScanResultConverter)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.SCAN, (cmd)->cmd.scan(cursor, scanParams),
					listScanResultConverter)
					.run(args);
		}
	}

}
