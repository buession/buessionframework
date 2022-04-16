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
 * | Copyright @ 2013-2022 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.client.jedis.operations;

import com.buession.lang.Status;
import com.buession.redis.client.connection.jedis.JedisConnection;
import com.buession.redis.client.jedis.JedisStandaloneClient;
import com.buession.redis.core.ExpireOption;
import com.buession.redis.core.MigrateOperation;
import com.buession.redis.core.ObjectEncoding;
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.Type;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.internal.convert.jedis.JedisConverters;
import com.buession.redis.core.internal.jedis.JedisScanParams;
import com.buession.redis.utils.SafeEncoder;
import redis.clients.jedis.args.ExpiryOption;
import redis.clients.jedis.params.MigrateParams;
import redis.clients.jedis.params.RestoreParams;
import redis.clients.jedis.params.SortingParams;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Jedis 单机模式 Key 命令操作
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public final class JedisKeyOperations extends AbstractKeyOperations<JedisConnection> {

	public JedisKeyOperations(final JedisStandaloneClient client){
		super(client);
	}

	@Override
	public long del(final String... keys){
		final CommandArguments args = CommandArguments.create("keys", keys);
		final JedisCommand<Long> command = JedisCommand.<Long>create(ProtocolCommand.DEL).general((cmd)->cmd.del(keys))
				.pipeline((cmd)->cmd.del(keys)).transaction((cmd)->cmd.del(keys));
		return execute(command, args);
	}

	@Override
	public long del(final byte[]... keys){
		final CommandArguments args = CommandArguments.create("keys", keys);
		final JedisCommand<Long> command = JedisCommand.<Long>create(ProtocolCommand.DEL).general((cmd)->cmd.del(keys))
				.pipeline((cmd)->cmd.del(keys)).transaction((cmd)->cmd.del(keys));
		return execute(command, args);
	}

	@Override
	public String dump(final String key){
		final CommandArguments args = CommandArguments.create("key", key);
		final JedisCommand<String> command = JedisCommand.<String>create(ProtocolCommand.DUMP)
				.general((cmd)->cmd.dump(key), JedisConverters.BINARY_TO_STRING_CONVERTER)
				.pipeline((cmd)->cmd.dump(key), JedisConverters.BINARY_TO_STRING_CONVERTER)
				.transaction((cmd)->cmd.dump(key), JedisConverters.BINARY_TO_STRING_CONVERTER);
		return execute(command, args);
	}

	@Override
	public byte[] dump(final byte[] key){
		final CommandArguments args = CommandArguments.create("key", key);
		final JedisCommand<byte[]> command = JedisCommand.<byte[]>create(ProtocolCommand.DUMP)
				.general((cmd)->cmd.dump(key)).pipeline((cmd)->cmd.dump(key)).transaction((cmd)->cmd.dump(key));
		return execute(command, args);
	}

	@Override
	public boolean exists(final String key){
		final CommandArguments args = CommandArguments.create("key", key);
		final JedisCommand<Boolean> command = JedisCommand.<Boolean>create(ProtocolCommand.EXISTS)
				.general((cmd)->cmd.exists(key)).pipeline((cmd)->cmd.exists(key)).transaction((cmd)->cmd.exists(key));
		return execute(command, args);
	}

	@Override
	public boolean exists(final byte[] key){
		final CommandArguments args = CommandArguments.create("key", key);
		final JedisCommand<Boolean> command = JedisCommand.<Boolean>create(ProtocolCommand.EXISTS)
				.general((cmd)->cmd.exists(key)).pipeline((cmd)->cmd.exists(key)).transaction((cmd)->cmd.exists(key));
		return execute(command, args);
	}

	@Override
	public long exists(final String... keys){
		final CommandArguments args = CommandArguments.create("keys", keys);
		final JedisCommand<Long> command = JedisCommand.<Long>create(ProtocolCommand.EXISTS)
				.general((cmd)->cmd.exists(keys)).pipeline((cmd)->cmd.exists(keys))
				.transaction((cmd)->cmd.exists(keys));
		return execute(command, args);
	}

	@Override
	public long exists(final byte[]... keys){
		final CommandArguments args = CommandArguments.create("keys", keys);
		final JedisCommand<Long> command = JedisCommand.<Long>create(ProtocolCommand.EXISTS)
				.general((cmd)->cmd.exists(keys)).pipeline((cmd)->cmd.exists(keys))
				.transaction((cmd)->cmd.exists(keys));
		return execute(command, args);
	}

	@Override
	public Status expire(final String key, final int lifetime){
		final CommandArguments args = CommandArguments.create("key", key).put("lifetime", lifetime);
		final JedisCommand<Status> command = JedisCommand.<Status>create(ProtocolCommand.EXPIRE)
				.general((cmd)->cmd.expire(key, lifetime), JedisConverters.ONE_STATUS_CONVERTER)
				.pipeline((cmd)->cmd.expire(key, lifetime), JedisConverters.ONE_STATUS_CONVERTER)
				.transaction((cmd)->cmd.expire(key, lifetime), JedisConverters.ONE_STATUS_CONVERTER);
		return execute(command, args);
	}

	@Override
	public Status expire(final byte[] key, final int lifetime){
		final CommandArguments args = CommandArguments.create("key", key).put("lifetime", lifetime);
		final JedisCommand<Status> command = JedisCommand.<Status>create(ProtocolCommand.EXPIRE)
				.general((cmd)->cmd.expire(key, lifetime), JedisConverters.ONE_STATUS_CONVERTER)
				.pipeline((cmd)->cmd.expire(key, lifetime), JedisConverters.ONE_STATUS_CONVERTER)
				.transaction((cmd)->cmd.expire(key, lifetime), JedisConverters.ONE_STATUS_CONVERTER);
		return execute(command, args);
	}

	@Override
	public Status expire(final String key, final int lifetime, final ExpireOption expireOption){
		final CommandArguments args = CommandArguments.create("key", key).put("lifetime", lifetime)
				.put("expireOption", expireOption);
		final ExpiryOption expiryOption = JedisConverters.EXPIRE_OPTION_CONVERTER.convert(expireOption);
		final JedisCommand<Status> command = JedisCommand.<Status>create(ProtocolCommand.EXPIRE)
				.general((cmd)->cmd.expire(key, lifetime, expiryOption), JedisConverters.ONE_STATUS_CONVERTER)
				.pipeline((cmd)->cmd.expire(key, lifetime, expiryOption), JedisConverters.ONE_STATUS_CONVERTER)
				.transaction((cmd)->cmd.expire(key, lifetime, expiryOption), JedisConverters.ONE_STATUS_CONVERTER);
		return execute(command, args);
	}

	@Override
	public Status expire(final byte[] key, final int lifetime, final ExpireOption expireOption){
		final CommandArguments args = CommandArguments.create("key", key).put("lifetime", lifetime)
				.put("expireOption", expireOption);
		final ExpiryOption expiryOption = JedisConverters.EXPIRE_OPTION_CONVERTER.convert(expireOption);
		final JedisCommand<Status> command = JedisCommand.<Status>create(ProtocolCommand.EXPIRE)
				.general((cmd)->cmd.expire(key, lifetime, expiryOption), JedisConverters.ONE_STATUS_CONVERTER)
				.pipeline((cmd)->cmd.expire(key, lifetime, expiryOption), JedisConverters.ONE_STATUS_CONVERTER)
				.transaction((cmd)->cmd.expire(key, lifetime, expiryOption), JedisConverters.ONE_STATUS_CONVERTER);
		return execute(command, args);
	}

	@Override
	public Status expireAt(final String key, final long unixTimestamp){
		final CommandArguments args = CommandArguments.create("key", key).put("unixTimestamp", unixTimestamp);
		final JedisCommand<Status> command = JedisCommand.<Status>create(ProtocolCommand.EXPIREAT)
				.general((cmd)->cmd.expireAt(key, unixTimestamp), JedisConverters.ONE_STATUS_CONVERTER)
				.pipeline((cmd)->cmd.expireAt(key, unixTimestamp), JedisConverters.ONE_STATUS_CONVERTER)
				.transaction((cmd)->cmd.expireAt(key, unixTimestamp), JedisConverters.ONE_STATUS_CONVERTER);
		return execute(command, args);
	}

	@Override
	public Status expireAt(final byte[] key, final long unixTimestamp){
		final CommandArguments args = CommandArguments.create("key", key).put("unixTimestamp", unixTimestamp);
		final JedisCommand<Status> command = JedisCommand.<Status>create(ProtocolCommand.EXPIREAT)
				.general((cmd)->cmd.expireAt(key, unixTimestamp), JedisConverters.ONE_STATUS_CONVERTER)
				.pipeline((cmd)->cmd.expireAt(key, unixTimestamp), JedisConverters.ONE_STATUS_CONVERTER)
				.transaction((cmd)->cmd.expireAt(key, unixTimestamp), JedisConverters.ONE_STATUS_CONVERTER);
		return execute(command, args);
	}

	@Override
	public Status pExpire(final String key, final int lifetime){
		final CommandArguments args = CommandArguments.create("key", key).put("lifetime", lifetime);
		final JedisCommand<Status> command = JedisCommand.<Status>create(ProtocolCommand.PEXPIRE)
				.general((cmd)->cmd.pexpire(key, lifetime), JedisConverters.ONE_STATUS_CONVERTER)
				.pipeline((cmd)->cmd.pexpire(key, lifetime), JedisConverters.ONE_STATUS_CONVERTER)
				.transaction((cmd)->cmd.pexpire(key, lifetime), JedisConverters.ONE_STATUS_CONVERTER);
		return execute(command, args);
	}

	@Override
	public Status pExpire(final byte[] key, final int lifetime){
		final CommandArguments args = CommandArguments.create("key", key).put("lifetime", lifetime);
		final JedisCommand<Status> command = JedisCommand.<Status>create(ProtocolCommand.PEXPIRE)
				.general((cmd)->cmd.pexpire(key, lifetime), JedisConverters.ONE_STATUS_CONVERTER)
				.pipeline((cmd)->cmd.pexpire(key, lifetime), JedisConverters.ONE_STATUS_CONVERTER)
				.transaction((cmd)->cmd.pexpire(key, lifetime), JedisConverters.ONE_STATUS_CONVERTER);
		return execute(command, args);
	}

	@Override
	public Status pExpireAt(final String key, final long unixTimestamp){
		final CommandArguments args = CommandArguments.create("key", key).put("unixTimestamp", unixTimestamp);
		final JedisCommand<Status> command = JedisCommand.<Status>create(ProtocolCommand.PEXPIREAT)
				.general((cmd)->cmd.pexpireAt(key, unixTimestamp), JedisConverters.ONE_STATUS_CONVERTER)
				.pipeline((cmd)->cmd.pexpireAt(key, unixTimestamp), JedisConverters.ONE_STATUS_CONVERTER)
				.transaction((cmd)->cmd.pexpireAt(key, unixTimestamp), JedisConverters.ONE_STATUS_CONVERTER);
		return execute(command, args);
	}

	@Override
	public Status pExpireAt(final byte[] key, final long unixTimestamp){
		final CommandArguments args = CommandArguments.create("key", key).put("unixTimestamp", unixTimestamp);
		final JedisCommand<Status> command = JedisCommand.<Status>create(ProtocolCommand.PEXPIREAT)
				.general((cmd)->cmd.pexpireAt(key, unixTimestamp), JedisConverters.ONE_STATUS_CONVERTER)
				.pipeline((cmd)->cmd.pexpireAt(key, unixTimestamp), JedisConverters.ONE_STATUS_CONVERTER)
				.transaction((cmd)->cmd.pexpireAt(key, unixTimestamp), JedisConverters.ONE_STATUS_CONVERTER);
		return execute(command, args);
	}

	@Override
	public Status persist(final String key){
		final CommandArguments args = CommandArguments.create("key", key);
		final JedisCommand<Status> command = JedisCommand.<Status>create(ProtocolCommand.PERSIST)
				.general((cmd)->cmd.persist(key), JedisConverters.ONE_STATUS_CONVERTER)
				.pipeline((cmd)->cmd.persist(key), JedisConverters.ONE_STATUS_CONVERTER)
				.transaction((cmd)->cmd.persist(key), JedisConverters.ONE_STATUS_CONVERTER);
		return execute(command, args);
	}

	@Override
	public Status persist(final byte[] key){
		final CommandArguments args = CommandArguments.create("key", key);
		final JedisCommand<Status> command = JedisCommand.<Status>create(ProtocolCommand.PERSIST)
				.general((cmd)->cmd.persist(key), JedisConverters.ONE_STATUS_CONVERTER)
				.pipeline((cmd)->cmd.persist(key), JedisConverters.ONE_STATUS_CONVERTER)
				.transaction((cmd)->cmd.persist(key), JedisConverters.ONE_STATUS_CONVERTER);
		return execute(command, args);
	}

	@Override
	public long ttl(final String key){
		final CommandArguments args = CommandArguments.create("key", key);
		final JedisCommand<Long> command = JedisCommand.<Long>create(ProtocolCommand.TTL).general((cmd)->cmd.ttl(key))
				.pipeline((cmd)->cmd.ttl(key)).transaction((cmd)->cmd.ttl(key));
		return execute(command, args);
	}

	@Override
	public long ttl(final byte[] key){
		final CommandArguments args = CommandArguments.create("key", key);
		final JedisCommand<Long> command = JedisCommand.<Long>create(ProtocolCommand.TTL).general((cmd)->cmd.ttl(key))
				.pipeline((cmd)->cmd.ttl(key)).transaction((cmd)->cmd.ttl(key));
		return execute(command, args);
	}

	@Override
	public long pTtl(final String key){
		final CommandArguments args = CommandArguments.create("key", key);
		final JedisCommand<Long> command = JedisCommand.<Long>create(ProtocolCommand.PTTL).general((cmd)->cmd.pttl(key))
				.pipeline((cmd)->cmd.pttl(key)).transaction((cmd)->cmd.pttl(key));
		return execute(command, args);
	}

	@Override
	public long pTtl(final byte[] key){
		final CommandArguments args = CommandArguments.create("key", key);
		final JedisCommand<Long> command = JedisCommand.<Long>create(ProtocolCommand.PTTL).general((cmd)->cmd.pttl(key))
				.pipeline((cmd)->cmd.pttl(key)).transaction((cmd)->cmd.pttl(key));
		return execute(command, args);
	}

	@Override
	public Status copy(final String key, final String destKey){
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey);
		final JedisCommand<Status> command = JedisCommand.<Status>create(ProtocolCommand.COPY)
				.general((cmd)->cmd.copy(key, destKey, false), JedisConverters.BOOLEAN_STATUS_CONVERTER)
				.pipeline((cmd)->cmd.copy(key, destKey, false), JedisConverters.BOOLEAN_STATUS_CONVERTER)
				.transaction((cmd)->cmd.copy(key, destKey, false), JedisConverters.BOOLEAN_STATUS_CONVERTER);
		return execute(command, args);
	}

	@Override
	public Status copy(final byte[] key, final byte[] destKey){
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey);
		final JedisCommand<Status> command = JedisCommand.<Status>create(ProtocolCommand.COPY)
				.general((cmd)->cmd.copy(key, destKey, false), JedisConverters.BOOLEAN_STATUS_CONVERTER)
				.pipeline((cmd)->cmd.copy(key, destKey, false), JedisConverters.BOOLEAN_STATUS_CONVERTER)
				.transaction((cmd)->cmd.copy(key, destKey, false), JedisConverters.BOOLEAN_STATUS_CONVERTER);
		return execute(command, args);
	}

	@Override
	public Status copy(final String key, final String destKey, final int db){
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey).put("db", db);
		final JedisCommand<Status> command = JedisCommand.<Status>create(ProtocolCommand.COPY)
				.general((cmd)->cmd.copy(key, destKey, db, false), JedisConverters.BOOLEAN_STATUS_CONVERTER)
				.pipeline((cmd)->cmd.copy(key, destKey, db, false), JedisConverters.BOOLEAN_STATUS_CONVERTER);
		return execute(command, args);
	}

	@Override
	public Status copy(final byte[] key, final byte[] destKey, final int db){
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey)
				.put("db", db);
		final JedisCommand<Status> command = JedisCommand.<Status>create(ProtocolCommand.COPY)
				.general((cmd)->cmd.copy(key, destKey, db, false), JedisConverters.BOOLEAN_STATUS_CONVERTER)
				.pipeline((cmd)->cmd.copy(key, destKey, db, false), JedisConverters.BOOLEAN_STATUS_CONVERTER);
		return execute(command, args);
	}

	@Override
	public Status copy(final String key, final String destKey, final boolean replace){
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey)
				.put("replace", replace);
		final JedisCommand<Status> command = JedisCommand.<Status>create(ProtocolCommand.COPY)
				.general((cmd)->cmd.copy(key, destKey, replace), JedisConverters.BOOLEAN_STATUS_CONVERTER)
				.pipeline((cmd)->cmd.copy(key, destKey, replace), JedisConverters.BOOLEAN_STATUS_CONVERTER)
				.transaction((cmd)->cmd.copy(key, destKey, replace), JedisConverters.BOOLEAN_STATUS_CONVERTER);
		return execute(command, args);
	}

	@Override
	public Status copy(final byte[] key, final byte[] destKey, final boolean replace){
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey)
				.put("replace", replace);
		final JedisCommand<Status> command = JedisCommand.<Status>create(ProtocolCommand.COPY)
				.general((cmd)->cmd.copy(key, destKey, replace), JedisConverters.BOOLEAN_STATUS_CONVERTER)
				.pipeline((cmd)->cmd.copy(key, destKey, replace), JedisConverters.BOOLEAN_STATUS_CONVERTER)
				.transaction((cmd)->cmd.copy(key, destKey, replace), JedisConverters.BOOLEAN_STATUS_CONVERTER);
		return execute(command, args);
	}

	@Override
	public Status copy(final String key, final String destKey, final int db, final boolean replace){
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey).put("db", db)
				.put("replace", replace);
		final JedisCommand<Status> command = JedisCommand.<Status>create(ProtocolCommand.COPY)
				.general((cmd)->cmd.copy(key, destKey, db, replace), JedisConverters.BOOLEAN_STATUS_CONVERTER)
				.pipeline((cmd)->cmd.copy(key, destKey, db, replace), JedisConverters.BOOLEAN_STATUS_CONVERTER);
		return execute(command, args);
	}

	@Override
	public Status copy(final byte[] key, final byte[] destKey, final int db, final boolean replace){
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey).put("db", db)
				.put("replace", replace);
		final JedisCommand<Status> command = JedisCommand.<Status>create(ProtocolCommand.COPY)
				.general((cmd)->cmd.copy(key, destKey, db, replace), JedisConverters.BOOLEAN_STATUS_CONVERTER)
				.pipeline((cmd)->cmd.copy(key, destKey, db, replace), JedisConverters.BOOLEAN_STATUS_CONVERTER);
		return execute(command, args);
	}

	@Override
	public Status move(final String key, final int db){
		final CommandArguments args = CommandArguments.create("key", key).put("db", db);
		final JedisCommand<Status> command = JedisCommand.<Status>create(ProtocolCommand.MOVE)
				.general((cmd)->cmd.move(key, db), JedisConverters.ONE_STATUS_CONVERTER)
				.pipeline((cmd)->cmd.move(key, db), JedisConverters.ONE_STATUS_CONVERTER);
		return execute(command, args);
	}

	@Override
	public Status move(final byte[] key, final int db){
		final CommandArguments args = CommandArguments.create("key", key).put("db", db);
		final JedisCommand<Status> command = JedisCommand.<Status>create(ProtocolCommand.MOVE)
				.general((cmd)->cmd.move(key, db), JedisConverters.ONE_STATUS_CONVERTER)
				.pipeline((cmd)->cmd.move(key, db), JedisConverters.ONE_STATUS_CONVERTER);
		return execute(command, args);
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final int timeout, final String... keys){
		final CommandArguments args = CommandArguments.create("host", host).put("port", port).put("db", db)
				.put("timeout", timeout).put("keys", keys);
		final MigrateParams migrateParams = JedisConverters.MIGRATE_OPERATION_CONVERTER.convert(null);
		final JedisCommand<Status> command = JedisCommand.<Status>create(ProtocolCommand.MIGRATE)
				.general((cmd)->cmd.migrate(host, port, db, timeout, migrateParams, keys),
						JedisConverters.OK_STATUS_CONVERTER)
				.pipeline((cmd)->cmd.migrate(host, port, db, timeout, migrateParams, keys),
						JedisConverters.OK_STATUS_CONVERTER);
		return execute(command, args);
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final int timeout, final byte[]... keys){
		final CommandArguments args = CommandArguments.create("host", host).put("port", port).put("db", db)
				.put("timeout", timeout).put("keys", keys);
		final MigrateParams migrateParams = JedisConverters.MIGRATE_OPERATION_CONVERTER.convert(null);
		final JedisCommand<Status> command = JedisCommand.<Status>create(ProtocolCommand.MIGRATE)
				.general((cmd)->cmd.migrate(host, port, db, timeout, migrateParams, keys),
						JedisConverters.OK_STATUS_CONVERTER)
				.pipeline((cmd)->cmd.migrate(host, port, db, timeout, migrateParams, keys),
						JedisConverters.OK_STATUS_CONVERTER);
		return execute(command, args);
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final int timeout,
						  final MigrateOperation operation, final String... keys){
		final CommandArguments args = CommandArguments.create("host", host).put("port", port).put("db", db)
				.put("timeout", timeout).put("operation", operation).put("keys", keys);
		final MigrateParams migrateParams = JedisConverters.MIGRATE_OPERATION_CONVERTER.convert(operation);
		final JedisCommand<Status> command = JedisCommand.<Status>create(ProtocolCommand.MIGRATE)
				.general((cmd)->cmd.migrate(host, port, db, timeout, migrateParams, keys),
						JedisConverters.OK_STATUS_CONVERTER)
				.pipeline((cmd)->cmd.migrate(host, port, db, timeout, migrateParams, keys),
						JedisConverters.OK_STATUS_CONVERTER);
		return execute(command, args);
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final int timeout,
						  final MigrateOperation operation, final byte[]... keys){
		final CommandArguments args = CommandArguments.create("host", host).put("port", port).put("db", db)
				.put("timeout", timeout).put("operation", operation).put("keys", keys);
		final MigrateParams migrateParams = JedisConverters.MIGRATE_OPERATION_CONVERTER.convert(operation);
		final JedisCommand<Status> command = JedisCommand.<Status>create(ProtocolCommand.MIGRATE)
				.general((cmd)->cmd.migrate(host, port, db, timeout, migrateParams, keys),
						JedisConverters.OK_STATUS_CONVERTER)
				.pipeline((cmd)->cmd.migrate(host, port, db, timeout, migrateParams, keys),
						JedisConverters.OK_STATUS_CONVERTER);
		return execute(command, args);
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final String password, final int timeout,
						  final String... keys){
		final CommandArguments args = CommandArguments.create("host", host).put("port", port).put("db", db)
				.put("password", password).put("timeout", timeout).put("keys", keys);
		final MigrateParams migrateParams = JedisConverters.MIGRATE_OPERATION_CONVERTER.convert(null).auth(password);
		final JedisCommand<Status> command = JedisCommand.<Status>create(ProtocolCommand.MIGRATE)
				.general((cmd)->cmd.migrate(host, port, db, timeout, migrateParams, keys),
						JedisConverters.OK_STATUS_CONVERTER)
				.pipeline((cmd)->cmd.migrate(host, port, db, timeout, migrateParams, keys),
						JedisConverters.OK_STATUS_CONVERTER);
		return execute(command, args);
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final byte[] password, final int timeout,
						  final byte[]... keys){
		final CommandArguments args = CommandArguments.create("host", host).put("port", port).put("db", db)
				.put("password", password).put("timeout", timeout).put("keys", keys);
		final MigrateParams migrateParams = JedisConverters.MIGRATE_OPERATION_CONVERTER.convert(null)
				.auth(SafeEncoder.encode(password));
		final JedisCommand<Status> command = JedisCommand.<Status>create(ProtocolCommand.MIGRATE)
				.general((cmd)->cmd.migrate(host, port, db, timeout, migrateParams, keys),
						JedisConverters.OK_STATUS_CONVERTER)
				.pipeline((cmd)->cmd.migrate(host, port, db, timeout, migrateParams, keys),
						JedisConverters.OK_STATUS_CONVERTER);
		return execute(command, args);
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final String password, final int timeout,
						  final MigrateOperation operation, final String... keys){
		final CommandArguments args = CommandArguments.create("host", host).put("port", port).put("db", db)
				.put("password", password).put("timeout", timeout).put("operation", operation).put("keys", keys);
		final MigrateParams migrateParams = JedisConverters.MIGRATE_OPERATION_CONVERTER.convert(operation)
				.auth(password);
		final JedisCommand<Status> command = JedisCommand.<Status>create(ProtocolCommand.MIGRATE)
				.general((cmd)->cmd.migrate(host, port, db, timeout, migrateParams, keys),
						JedisConverters.OK_STATUS_CONVERTER)
				.pipeline((cmd)->cmd.migrate(host, port, db, timeout, migrateParams, keys),
						JedisConverters.OK_STATUS_CONVERTER);
		return execute(command, args);
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final byte[] password, final int timeout,
						  final MigrateOperation operation, final byte[]... keys){
		final CommandArguments args = CommandArguments.create("host", host).put("port", port).put("db", db)
				.put("password", password).put("timeout", timeout).put("operation", operation).put("keys", keys);
		final MigrateParams migrateParams = JedisConverters.MIGRATE_OPERATION_CONVERTER.convert(operation)
				.auth(SafeEncoder.encode(password));
		final JedisCommand<Status> command = JedisCommand.<Status>create(ProtocolCommand.MIGRATE)
				.general((cmd)->cmd.migrate(host, port, db, timeout, migrateParams, keys),
						JedisConverters.OK_STATUS_CONVERTER)
				.pipeline((cmd)->cmd.migrate(host, port, db, timeout, migrateParams, keys),
						JedisConverters.OK_STATUS_CONVERTER);
		return execute(command, args);
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final String user, final String password,
						  final int timeout, final String... keys){
		final CommandArguments args = CommandArguments.create("host", host).put("port", port).put("db", db)
				.put("user", user).put("password", password).put("timeout", timeout).put("keys", keys);
		final MigrateParams migrateParams = JedisConverters.MIGRATE_OPERATION_CONVERTER.convert(null)
				.auth2(user, password);
		final JedisCommand<Status> command = JedisCommand.<Status>create(ProtocolCommand.MIGRATE)
				.general((cmd)->cmd.migrate(host, port, db, timeout, migrateParams, keys),
						JedisConverters.OK_STATUS_CONVERTER)
				.pipeline((cmd)->cmd.migrate(host, port, db, timeout, migrateParams, keys),
						JedisConverters.OK_STATUS_CONVERTER);
		return execute(command, args);
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final byte[] user, final byte[] password,
						  final int timeout, final byte[]... keys){
		final CommandArguments args = CommandArguments.create("host", host).put("port", port).put("db", db)
				.put("user", user).put("password", password).put("timeout", timeout).put("keys", keys);
		final MigrateParams migrateParams = JedisConverters.MIGRATE_OPERATION_CONVERTER.convert(null)
				.auth2(SafeEncoder.encode(user), SafeEncoder.encode(password));
		final JedisCommand<Status> command = JedisCommand.<Status>create(ProtocolCommand.MIGRATE)
				.general((cmd)->cmd.migrate(host, port, db, timeout, migrateParams, keys),
						JedisConverters.OK_STATUS_CONVERTER)
				.pipeline((cmd)->cmd.migrate(host, port, db, timeout, migrateParams, keys),
						JedisConverters.OK_STATUS_CONVERTER);
		return execute(command, args);
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final String user, final String password,
						  final int timeout, final MigrateOperation operation, final String... keys){
		final CommandArguments args = CommandArguments.create("host", host).put("port", port).put("db", db)
				.put("user", user).put("password", password).put("timeout", timeout).put("operation", operation)
				.put("keys", keys);
		final MigrateParams migrateParams = JedisConverters.MIGRATE_OPERATION_CONVERTER.convert(operation)
				.auth2(user, password);
		final JedisCommand<Status> command = JedisCommand.<Status>create(ProtocolCommand.MIGRATE)
				.general((cmd)->cmd.migrate(host, port, db, timeout, migrateParams, keys),
						JedisConverters.OK_STATUS_CONVERTER)
				.pipeline((cmd)->cmd.migrate(host, port, db, timeout, migrateParams, keys),
						JedisConverters.OK_STATUS_CONVERTER);
		return execute(command, args);
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final byte[] user, final byte[] password,
						  final int timeout, final MigrateOperation operation, final byte[]... keys){
		final CommandArguments args = CommandArguments.create("host", host).put("port", port).put("db", db)
				.put("user", user).put("password", password).put("timeout", timeout).put("operation", operation)
				.put("keys", keys);
		final MigrateParams migrateParams = JedisConverters.MIGRATE_OPERATION_CONVERTER.convert(operation)
				.auth2(SafeEncoder.encode(user), SafeEncoder.encode(password));
		final JedisCommand<Status> command = JedisCommand.<Status>create(ProtocolCommand.MIGRATE)
				.general((cmd)->cmd.migrate(host, port, db, timeout, migrateParams, keys),
						JedisConverters.OK_STATUS_CONVERTER)
				.pipeline((cmd)->cmd.migrate(host, port, db, timeout, migrateParams, keys),
						JedisConverters.OK_STATUS_CONVERTER);
		return execute(command, args);
	}

	@Override
	public Set<String> keys(final String pattern){
		final CommandArguments args = CommandArguments.create("pattern", pattern);
		final JedisCommand<Set<String>> command = JedisCommand.<Set<String>>create(ProtocolCommand.KEYS)
				.general((cmd)->cmd.keys(pattern)).pipeline((cmd)->cmd.keys(pattern))
				.transaction((cmd)->cmd.keys(pattern));
		return execute(command, args);
	}

	@Override
	public Set<byte[]> keys(final byte[] pattern){
		final CommandArguments args = CommandArguments.create("pattern", pattern);
		final JedisCommand<Set<byte[]>> command = JedisCommand.<Set<byte[]>>create(ProtocolCommand.KEYS)
				.general((cmd)->cmd.keys(pattern)).pipeline((cmd)->cmd.keys(pattern))
				.transaction((cmd)->cmd.keys(pattern));
		return execute(command, args);
	}

	@Override
	public String randomKey(){
		final JedisCommand<String> command = JedisCommand.<String>create(ProtocolCommand.RANDOMKEY)
				.general((cmd)->cmd.randomKey()).pipeline((cmd)->cmd.randomKey()).transaction((cmd)->cmd.randomKey());
		return execute(command);
	}

	@Override
	public Status rename(final String key, final String newKey){
		final CommandArguments args = CommandArguments.create("key", key).put("newKey", newKey);
		final JedisCommand<Status> command = JedisCommand.<Status>create(ProtocolCommand.RENAME)
				.general((cmd)->cmd.rename(key, newKey), JedisConverters.OK_STATUS_CONVERTER)
				.pipeline((cmd)->cmd.rename(key, newKey), JedisConverters.OK_STATUS_CONVERTER)
				.transaction((cmd)->cmd.rename(key, newKey), JedisConverters.OK_STATUS_CONVERTER);
		return execute(command, args);
	}

	@Override
	public Status rename(final byte[] key, final byte[] newKey){
		final CommandArguments args = CommandArguments.create("key", key).put("newKey", newKey);
		final JedisCommand<Status> command = JedisCommand.<Status>create(ProtocolCommand.RENAME)
				.general((cmd)->cmd.rename(key, newKey), JedisConverters.OK_STATUS_CONVERTER)
				.pipeline((cmd)->cmd.rename(key, newKey), JedisConverters.OK_STATUS_CONVERTER)
				.transaction((cmd)->cmd.rename(key, newKey), JedisConverters.OK_STATUS_CONVERTER);
		return execute(command, args);
	}

	@Override
	public Status renameNx(final String key, final String newKey){
		final CommandArguments args = CommandArguments.create("key", key).put("newKey", newKey);
		final JedisCommand<Status> command = JedisCommand.<Status>create(ProtocolCommand.RENAMENX)
				.general((cmd)->cmd.renamenx(key, newKey), JedisConverters.ONE_STATUS_CONVERTER)
				.pipeline((cmd)->cmd.renamenx(key, newKey), JedisConverters.ONE_STATUS_CONVERTER)
				.transaction((cmd)->cmd.renamenx(key, newKey), JedisConverters.ONE_STATUS_CONVERTER);
		return execute(command, args);
	}

	@Override
	public Status renameNx(final byte[] key, final byte[] newKey){
		final CommandArguments args = CommandArguments.create("key", key).put("newKey", newKey);
		final JedisCommand<Status> command = JedisCommand.<Status>create(ProtocolCommand.RENAMENX)
				.general((cmd)->cmd.renamenx(key, newKey), JedisConverters.ONE_STATUS_CONVERTER)
				.pipeline((cmd)->cmd.renamenx(key, newKey), JedisConverters.ONE_STATUS_CONVERTER)
				.transaction((cmd)->cmd.renamenx(key, newKey), JedisConverters.ONE_STATUS_CONVERTER);
		return execute(command, args);
	}

	@Override
	public Status restore(final String key, final byte[] serializedValue, final int ttl){
		final CommandArguments args = CommandArguments.create("key", key).put("serializedValue", serializedValue)
				.put("ttl", ttl);
		final JedisCommand<Status> command = JedisCommand.<Status>create(ProtocolCommand.RESTORE)
				.general((cmd)->cmd.restore(key, ttl, serializedValue), JedisConverters.OK_STATUS_CONVERTER)
				.pipeline((cmd)->cmd.restore(key, ttl, serializedValue), JedisConverters.OK_STATUS_CONVERTER)
				.transaction((cmd)->cmd.restore(key, ttl, serializedValue), JedisConverters.OK_STATUS_CONVERTER);
		return execute(command, args);
	}

	@Override
	public Status restore(final byte[] key, final byte[] serializedValue, final int ttl){
		final CommandArguments args = CommandArguments.create("key", key).put("serializedValue", serializedValue)
				.put("ttl", ttl);
		final JedisCommand<Status> command = JedisCommand.<Status>create(ProtocolCommand.RESTORE)
				.general((cmd)->cmd.restore(key, ttl, serializedValue), JedisConverters.OK_STATUS_CONVERTER)
				.pipeline((cmd)->cmd.restore(key, ttl, serializedValue), JedisConverters.OK_STATUS_CONVERTER)
				.transaction((cmd)->cmd.restore(key, ttl, serializedValue), JedisConverters.OK_STATUS_CONVERTER);
		return execute(command, args);
	}

	@Override
	public Status restore(final String key, final byte[] serializedValue, final int ttl,
						  final RestoreArgument argument){
		final CommandArguments args = CommandArguments.create("key", key).put("serializedValue", serializedValue)
				.put("ttl", ttl).put("argument", argument);
		final RestoreParams restoreParams = JedisConverters.RESTORE_ARGUMENT_CONVERTER.convert(argument);
		final JedisCommand<Status> command = JedisCommand.<Status>create(ProtocolCommand.RESTORE)
				.general((cmd)->cmd.restore(key, ttl, serializedValue, restoreParams),
						JedisConverters.OK_STATUS_CONVERTER)
				.pipeline((cmd)->cmd.restore(key, ttl, serializedValue, restoreParams),
						JedisConverters.OK_STATUS_CONVERTER)
				.transaction((cmd)->cmd.restore(key, ttl, serializedValue, restoreParams),
						JedisConverters.OK_STATUS_CONVERTER);
		return execute(command, args);
	}

	@Override
	public Status restore(final byte[] key, final byte[] serializedValue, final int ttl,
						  final RestoreArgument argument){
		final CommandArguments args = CommandArguments.create("key", key).put("serializedValue", serializedValue)
				.put("ttl", ttl).put("argument", argument);
		final RestoreParams restoreParams = JedisConverters.RESTORE_ARGUMENT_CONVERTER.convert(argument);
		final JedisCommand<Status> command = JedisCommand.<Status>create(ProtocolCommand.RESTORE)
				.general((cmd)->cmd.restore(key, ttl, serializedValue, restoreParams),
						JedisConverters.OK_STATUS_CONVERTER)
				.pipeline((cmd)->cmd.restore(key, ttl, serializedValue, restoreParams),
						JedisConverters.OK_STATUS_CONVERTER)
				.transaction((cmd)->cmd.restore(key, ttl, serializedValue, restoreParams),
						JedisConverters.OK_STATUS_CONVERTER);
		return execute(command, args);
	}

	@Override
	public ScanResult<List<String>> scan(final String cursor){
		final CommandArguments args = CommandArguments.create("cursor", cursor);
		final JedisCommand<ScanResult<List<String>>> command = JedisCommand.<ScanResult<List<String>>>create(
						ProtocolCommand.SCAN)
				.general((cmd)->cmd.scan(cursor), JedisConverters.STRING_LIST_SCAN_RESULT_RESULT_CONVERTER)
				.pipeline((cmd)->cmd.scan(cursor), JedisConverters.STRING_LIST_SCAN_RESULT_RESULT_CONVERTER)
				.transaction((cmd)->cmd.scan(cursor), JedisConverters.STRING_LIST_SCAN_RESULT_RESULT_CONVERTER);
		return execute(command, args);
	}

	@Override
	public ScanResult<List<byte[]>> scan(final byte[] cursor){
		final CommandArguments args = CommandArguments.create("cursor", cursor);
		final JedisCommand<ScanResult<List<byte[]>>> command = JedisCommand.<ScanResult<List<byte[]>>>create(
						ProtocolCommand.SCAN)
				.general((cmd)->cmd.scan(cursor), JedisConverters.BINARY_LIST_SCAN_RESULT_RESULT_CONVERTER)
				.pipeline((cmd)->cmd.scan(cursor), JedisConverters.BINARY_LIST_SCAN_RESULT_RESULT_CONVERTER)
				.transaction((cmd)->cmd.scan(cursor), JedisConverters.BINARY_LIST_SCAN_RESULT_RESULT_CONVERTER);
		return execute(command, args);
	}

	@Override
	public ScanResult<List<String>> scan(final String cursor, final String pattern){
		final CommandArguments args = CommandArguments.create("cursor", cursor).put("pattern", pattern);
		final JedisScanParams params = new JedisScanParams(pattern);
		final JedisCommand<ScanResult<List<String>>> command = JedisCommand.<ScanResult<List<String>>>create(
						ProtocolCommand.SCAN)
				.general((cmd)->cmd.scan(cursor, params), JedisConverters.STRING_LIST_SCAN_RESULT_RESULT_CONVERTER)
				.pipeline((cmd)->cmd.scan(cursor, params), JedisConverters.STRING_LIST_SCAN_RESULT_RESULT_CONVERTER)
				.transaction((cmd)->cmd.scan(cursor, params), JedisConverters.STRING_LIST_SCAN_RESULT_RESULT_CONVERTER);
		return execute(command, args);
	}

	@Override
	public ScanResult<List<byte[]>> scan(final byte[] cursor, final byte[] pattern){
		final CommandArguments args = CommandArguments.create("cursor", cursor).put("pattern", pattern);
		final JedisScanParams params = new JedisScanParams(pattern);
		final JedisCommand<ScanResult<List<byte[]>>> command = JedisCommand.<ScanResult<List<byte[]>>>create(
						ProtocolCommand.SCAN)
				.general((cmd)->cmd.scan(cursor, params), JedisConverters.BINARY_LIST_SCAN_RESULT_RESULT_CONVERTER)
				.pipeline((cmd)->cmd.scan(cursor, params), JedisConverters.BINARY_LIST_SCAN_RESULT_RESULT_CONVERTER)
				.transaction((cmd)->cmd.scan(cursor, params), JedisConverters.BINARY_LIST_SCAN_RESULT_RESULT_CONVERTER);
		return execute(command, args);
	}

	@Override
	public ScanResult<List<String>> scan(final String cursor, final long count){
		final CommandArguments args = CommandArguments.create("cursor", cursor).put("count", count);
		final JedisScanParams params = new JedisScanParams(count);
		final JedisCommand<ScanResult<List<String>>> command = JedisCommand.<ScanResult<List<String>>>create(
						ProtocolCommand.SCAN)
				.general((cmd)->cmd.scan(cursor, params), JedisConverters.STRING_LIST_SCAN_RESULT_RESULT_CONVERTER)
				.pipeline((cmd)->cmd.scan(cursor, params), JedisConverters.STRING_LIST_SCAN_RESULT_RESULT_CONVERTER)
				.transaction((cmd)->cmd.scan(cursor, params), JedisConverters.STRING_LIST_SCAN_RESULT_RESULT_CONVERTER);
		return execute(command, args);
	}

	@Override
	public ScanResult<List<byte[]>> scan(final byte[] cursor, final long count){
		final CommandArguments args = CommandArguments.create("cursor", cursor).put("count", count);
		final JedisScanParams params = new JedisScanParams(count);
		final JedisCommand<ScanResult<List<byte[]>>> command = JedisCommand.<ScanResult<List<byte[]>>>create(
						ProtocolCommand.SCAN)
				.general((cmd)->cmd.scan(cursor, params), JedisConverters.BINARY_LIST_SCAN_RESULT_RESULT_CONVERTER)
				.pipeline((cmd)->cmd.scan(cursor, params), JedisConverters.BINARY_LIST_SCAN_RESULT_RESULT_CONVERTER)
				.transaction((cmd)->cmd.scan(cursor, params), JedisConverters.BINARY_LIST_SCAN_RESULT_RESULT_CONVERTER);
		return execute(command, args);
	}

	@Override
	public ScanResult<List<String>> scan(final String cursor, final String pattern, final long count){
		final CommandArguments args = CommandArguments.create("cursor", cursor).put("pattern", pattern)
				.put("count", count);
		final JedisScanParams params = new JedisScanParams(pattern, count);
		final JedisCommand<ScanResult<List<String>>> command = JedisCommand.<ScanResult<List<String>>>create(
						ProtocolCommand.SCAN)
				.general((cmd)->cmd.scan(cursor, params), JedisConverters.STRING_LIST_SCAN_RESULT_RESULT_CONVERTER)
				.pipeline((cmd)->cmd.scan(cursor, params), JedisConverters.STRING_LIST_SCAN_RESULT_RESULT_CONVERTER)
				.transaction((cmd)->cmd.scan(cursor, params), JedisConverters.STRING_LIST_SCAN_RESULT_RESULT_CONVERTER);
		return execute(command, args);
	}

	@Override
	public ScanResult<List<byte[]>> scan(final byte[] cursor, final byte[] pattern, final long count){
		final CommandArguments args = CommandArguments.create("cursor", cursor).put("pattern", pattern)
				.put("count", count);
		final JedisScanParams params = new JedisScanParams(pattern, count);
		final JedisCommand<ScanResult<List<byte[]>>> command = JedisCommand.<ScanResult<List<byte[]>>>create(
						ProtocolCommand.SCAN)
				.general((cmd)->cmd.scan(cursor, params), JedisConverters.BINARY_LIST_SCAN_RESULT_RESULT_CONVERTER)
				.pipeline((cmd)->cmd.scan(cursor, params), JedisConverters.BINARY_LIST_SCAN_RESULT_RESULT_CONVERTER)
				.transaction((cmd)->cmd.scan(cursor, params), JedisConverters.BINARY_LIST_SCAN_RESULT_RESULT_CONVERTER);
		return execute(command, args);
	}

	@Override
	public List<String> sort(final String key){
		final CommandArguments args = CommandArguments.create("key", key);
		final JedisCommand<List<String>> command = JedisCommand.<List<String>>create(ProtocolCommand.SORT)
				.general((cmd)->cmd.sort(key)).pipeline((cmd)->cmd.sort(key)).transaction((cmd)->cmd.sort(key));
		return execute(command, args);
	}

	@Override
	public List<byte[]> sort(final byte[] key){
		final CommandArguments args = CommandArguments.create("key", key);
		final JedisCommand<List<byte[]>> command = JedisCommand.<List<byte[]>>create(ProtocolCommand.SORT)
				.general((cmd)->cmd.sort(key)).pipeline((cmd)->cmd.sort(key)).transaction((cmd)->cmd.sort(key));
		return execute(command, args);
	}

	@Override
	public List<String> sort(final String key, final SortArgument sortArgument){
		final CommandArguments args = CommandArguments.create("key", key).put("sortArgument", sortArgument);
		final SortingParams soringParams = JedisConverters.SORT_ARGUMENT_CONVERTER.convert(sortArgument);
		final JedisCommand<List<String>> command = JedisCommand.<List<String>>create(ProtocolCommand.SORT)
				.general((cmd)->cmd.sort(key, soringParams)).pipeline((cmd)->cmd.sort(key, soringParams))
				.transaction((cmd)->cmd.sort(key, soringParams));
		return execute(command, args);
	}

	@Override
	public List<byte[]> sort(final byte[] key, final SortArgument sortArgument){
		final CommandArguments args = CommandArguments.create("key", key).put("sortArgument", sortArgument);
		final SortingParams soringParams = JedisConverters.SORT_ARGUMENT_CONVERTER.convert(sortArgument);
		final JedisCommand<List<byte[]>> command = JedisCommand.<List<byte[]>>create(ProtocolCommand.SORT)
				.general((cmd)->cmd.sort(key, soringParams)).pipeline((cmd)->cmd.sort(key, soringParams))
				.transaction((cmd)->cmd.sort(key, soringParams));
		return execute(command, args);
	}

	@Override
	public long sort(final String key, final String destKey){
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey);
		final JedisCommand<Long> command = JedisCommand.<Long>create(ProtocolCommand.SORT)
				.general((cmd)->cmd.sort(key, destKey)).pipeline((cmd)->cmd.sort(key, destKey))
				.transaction((cmd)->cmd.sort(key, destKey));
		return execute(command, args);
	}

	@Override
	public long sort(final byte[] key, final byte[] destKey){
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey);
		final JedisCommand<Long> command = JedisCommand.<Long>create(ProtocolCommand.SORT)
				.general((cmd)->cmd.sort(key, destKey)).pipeline((cmd)->cmd.sort(key, destKey))
				.transaction((cmd)->cmd.sort(key, destKey));
		return execute(command, args);
	}

	@Override
	public long sort(final String key, final String destKey, final SortArgument sortArgument){
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey)
				.put("sortArgument", sortArgument);
		final SortingParams soringParams = JedisConverters.SORT_ARGUMENT_CONVERTER.convert(sortArgument);
		final JedisCommand<Long> command = JedisCommand.<Long>create(ProtocolCommand.SORT)
				.general((cmd)->cmd.sort(key, soringParams, destKey))
				.pipeline((cmd)->cmd.sort(key, soringParams, destKey))
				.transaction((cmd)->cmd.sort(key, soringParams, destKey));
		return execute(command, args);
	}

	@Override
	public long sort(final byte[] key, final byte[] destKey, final SortArgument sortArgument){
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey)
				.put("sortArgument", sortArgument);
		final SortingParams soringParams = JedisConverters.SORT_ARGUMENT_CONVERTER.convert(sortArgument);
		final JedisCommand<Long> command = JedisCommand.<Long>create(ProtocolCommand.SORT)
				.general((cmd)->cmd.sort(key, soringParams, destKey))
				.pipeline((cmd)->cmd.sort(key, soringParams, destKey))
				.transaction((cmd)->cmd.sort(key, soringParams, destKey));
		return execute(command, args);
	}

	@Override
	public long touch(final String... keys){
		final CommandArguments args = CommandArguments.create("keys", keys);
		final JedisCommand<Long> command = JedisCommand.<Long>create(ProtocolCommand.TOUCH)
				.general((cmd)->cmd.touch(keys)).pipeline((cmd)->cmd.touch(keys)).transaction((cmd)->cmd.touch(keys));
		return execute(command, args);
	}

	@Override
	public long touch(final byte[]... keys){
		final CommandArguments args = CommandArguments.create("keys", keys);
		final JedisCommand<Long> command = JedisCommand.<Long>create(ProtocolCommand.TOUCH)
				.general((cmd)->cmd.touch(keys)).pipeline((cmd)->cmd.touch(keys)).transaction((cmd)->cmd.touch(keys));
		return execute(command, args);
	}

	@Override
	public Type type(final String key){
		final CommandArguments args = CommandArguments.create("key", key);
		final JedisCommand<Type> command = JedisCommand.<Type>create(ProtocolCommand.TYPE)
				.general((cmd)->cmd.type(key), JedisConverters.TYPE_RESULT_CONVERTER)
				.pipeline((cmd)->cmd.type(key), JedisConverters.TYPE_RESULT_CONVERTER)
				.transaction((cmd)->cmd.type(key), JedisConverters.TYPE_RESULT_CONVERTER);
		return execute(command, args);
	}

	@Override
	public Type type(final byte[] key){
		final CommandArguments args = CommandArguments.create("key", key);
		final JedisCommand<Type> command = JedisCommand.<Type>create(ProtocolCommand.TYPE)
				.general((cmd)->cmd.type(key), JedisConverters.TYPE_RESULT_CONVERTER)
				.pipeline((cmd)->cmd.type(key), JedisConverters.TYPE_RESULT_CONVERTER)
				.transaction((cmd)->cmd.type(key), JedisConverters.TYPE_RESULT_CONVERTER);
		return execute(command, args);
	}

	@Override
	public long unlink(final String... keys){
		final CommandArguments args = CommandArguments.create("keys", keys);
		final JedisCommand<Long> command = JedisCommand.<Long>create(ProtocolCommand.UNLINK)
				.general((cmd)->cmd.unlink(keys)).pipeline((cmd)->cmd.unlink(keys))
				.transaction((cmd)->cmd.unlink(keys));
		return execute(command, args);
	}

	@Override
	public long unlink(final byte[]... keys){
		final CommandArguments args = CommandArguments.create("keys", keys);
		final JedisCommand<Long> command = JedisCommand.<Long>create(ProtocolCommand.UNLINK)
				.general((cmd)->cmd.unlink(keys)).pipeline((cmd)->cmd.unlink(keys))
				.transaction((cmd)->cmd.unlink(keys));
		return execute(command, args);
	}

	@Override
	public long wait(final int replicas, final int timeout){
		final CommandArguments args = CommandArguments.create("replicas", replicas).put("timeout", timeout);
		final JedisCommand<Long> command = JedisCommand.<Long>create(ProtocolCommand.WAIT)
				.general((cmd)->cmd.waitReplicas(replicas, timeout))
				.pipeline((cmd)->cmd.waitReplicas(replicas, timeout))
				.transaction((cmd)->cmd.waitReplicas(replicas, timeout));
		return execute(command, args);
	}

	@Override
	public ObjectEncoding objectEncoding(final String key){
		final CommandArguments args = CommandArguments.create("key", key);
		final JedisCommand<ObjectEncoding> command = JedisCommand.<ObjectEncoding>create(
						ProtocolCommand.OBJECT_ENCODING)
				.general((cmd)->cmd.objectEncoding(key), JedisConverters.STRING_OBJECT_ENCODING_RESULT_CONVERTER)
				.pipeline((cmd)->cmd.objectEncoding(key), JedisConverters.STRING_OBJECT_ENCODING_RESULT_CONVERTER)
				.transaction((cmd)->cmd.objectEncoding(key), JedisConverters.STRING_OBJECT_ENCODING_RESULT_CONVERTER);
		return execute(command, args);
	}

	@Override
	public ObjectEncoding objectEncoding(final byte[] key){
		final CommandArguments args = CommandArguments.create("key", key);
		final JedisCommand<ObjectEncoding> command = JedisCommand.<ObjectEncoding>create(
						ProtocolCommand.OBJECT_ENCODING)
				.general((cmd)->cmd.objectEncoding(key), JedisConverters.BINARY_OBJECT_ENCODING_RESULT_CONVERTER)
				.pipeline((cmd)->cmd.objectEncoding(key), JedisConverters.BINARY_OBJECT_ENCODING_RESULT_CONVERTER)
				.transaction((cmd)->cmd.objectEncoding(key), JedisConverters.BINARY_OBJECT_ENCODING_RESULT_CONVERTER);
		return execute(command, args);
	}

	@Override
	public long objectFreq(final String key){
		final CommandArguments args = CommandArguments.create("key", key);
		final JedisCommand<Long> command = JedisCommand.<Long>create(ProtocolCommand.OBJECT_REFQ)
				.general((cmd)->cmd.objectFreq(key)).pipeline((cmd)->cmd.objectFreq(key))
				.transaction((cmd)->cmd.objectFreq(key));
		return execute(command, args);
	}

	@Override
	public long objectFreq(final byte[] key){
		final CommandArguments args = CommandArguments.create("key", key);
		final JedisCommand<Long> command = JedisCommand.<Long>create(ProtocolCommand.OBJECT_REFQ)
				.general((cmd)->cmd.objectFreq(key)).pipeline((cmd)->cmd.objectFreq(key))
				.transaction((cmd)->cmd.objectFreq(key));
		return execute(command, args);
	}

	@Override
	public long objectIdleTime(final String key){
		final CommandArguments args = CommandArguments.create("key", key);
		final JedisCommand<Long> command = JedisCommand.<Long>create(ProtocolCommand.OBJECT_IDLETIME)
				.general((cmd)->cmd.objectIdletime(key)).pipeline((cmd)->cmd.objectIdletime(key))
				.transaction((cmd)->cmd.objectIdletime(key));
		return execute(command, args);
	}

	@Override
	public long objectIdleTime(final byte[] key){
		final CommandArguments args = CommandArguments.create("key", key);
		final JedisCommand<Long> command = JedisCommand.<Long>create(ProtocolCommand.OBJECT_IDLETIME)
				.general((cmd)->cmd.objectIdletime(key)).pipeline((cmd)->cmd.objectIdletime(key))
				.transaction((cmd)->cmd.objectIdletime(key));
		return execute(command, args);
	}

	@Override
	public long objectRefcount(final String key){
		final CommandArguments args = CommandArguments.create("key", key);
		final JedisCommand<Long> command = JedisCommand.<Long>create(ProtocolCommand.OBJECT_REFCOUNT)
				.general((cmd)->cmd.objectRefcount(key)).pipeline((cmd)->cmd.objectRefcount(key))
				.transaction((cmd)->cmd.objectRefcount(key));
		return execute(command, args);
	}

	@Override
	public long objectRefcount(final byte[] key){
		final CommandArguments args = CommandArguments.create("key", key);
		final JedisCommand<Long> command = JedisCommand.<Long>create(ProtocolCommand.OBJECT_REFCOUNT)
				.general((cmd)->cmd.objectRefcount(key)).pipeline((cmd)->cmd.objectRefcount(key))
				.transaction((cmd)->cmd.objectRefcount(key));
		return execute(command, args);
	}

}
