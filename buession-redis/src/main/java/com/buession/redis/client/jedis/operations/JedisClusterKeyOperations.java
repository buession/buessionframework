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
 * | Copyright @ 2013-2023 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.client.jedis.operations;

import com.buession.lang.Status;
import com.buession.redis.client.jedis.JedisClusterClient;
import com.buession.redis.core.ExpireOption;
import com.buession.redis.core.MigrateOperation;
import com.buession.redis.core.ObjectEncoding;
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.Type;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.internal.convert.Converters;
import com.buession.redis.core.internal.convert.jedis.params.ExpireOptionConverter;
import com.buession.redis.core.internal.convert.jedis.params.RestoreArgumentConverter;
import com.buession.redis.core.internal.convert.jedis.params.SortArgumentConverter;
import com.buession.redis.core.internal.convert.response.OkStatusConverter;
import com.buession.redis.core.internal.convert.jedis.response.ScanResultConverter;
import com.buession.redis.core.internal.jedis.JedisScanParams;
import redis.clients.jedis.args.ExpiryOption;
import redis.clients.jedis.params.RestoreParams;
import redis.clients.jedis.params.SortingParams;

import java.util.List;
import java.util.Set;

/**
 * Jedis 集群模式 Key 命令操作
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public final class JedisClusterKeyOperations extends AbstractKeyOperations<JedisClusterClient> {

	public JedisClusterKeyOperations(final JedisClusterClient client){
		super(client);
	}

	@Override
	public Long del(final String... keys){
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys);
		return new JedisClusterCommand<Long>(client, ProtocolCommand.DEL)
				.general((cmd)->cmd.del(keys))
				.pipeline((cmd)->cmd.del(keys))
				.transaction((cmd)->cmd.del(keys))
				.run(args);
	}

	@Override
	public Long del(final byte[]... keys){
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys);
		return new JedisClusterCommand<Long>(client, ProtocolCommand.DEL)
				.general((cmd)->cmd.del(keys))
				.pipeline((cmd)->cmd.del(keys))
				.transaction((cmd)->cmd.del(keys))
				.run(args);
	}

	@Override
	public String dump(final String key){
		final CommandArguments args = CommandArguments.create("key", key);
		return new JedisClusterCommand<String>(client, ProtocolCommand.DUMP)
				.general((cmd)->cmd.dump(key), Converters.BINARY_TO_STRING_CONVERTER)
				.pipeline((cmd)->cmd.dump(key), Converters.BINARY_TO_STRING_CONVERTER)
				.transaction((cmd)->cmd.dump(key), Converters.BINARY_TO_STRING_CONVERTER)
				.run(args);
	}

	@Override
	public byte[] dump(final byte[] key){
		final CommandArguments args = CommandArguments.create("key", key);
		return new JedisClusterCommand<byte[]>(client, ProtocolCommand.DUMP)
				.general((cmd)->cmd.dump(key))
				.pipeline((cmd)->cmd.dump(key))
				.transaction((cmd)->cmd.dump(key))
				.run(args);
	}

	@Override
	public Boolean exists(final String key){
		final CommandArguments args = CommandArguments.create("key", key);
		return new JedisClusterCommand<Boolean>(client, ProtocolCommand.EXISTS)
				.general((cmd)->cmd.exists(key))
				.pipeline((cmd)->cmd.exists(key))
				.transaction((cmd)->cmd.exists(key))
				.run(args);
	}

	@Override
	public Boolean exists(final byte[] key){
		final CommandArguments args = CommandArguments.create("key", key);
		return new JedisClusterCommand<Boolean>(client, ProtocolCommand.EXISTS)
				.general((cmd)->cmd.exists(key))
				.pipeline((cmd)->cmd.exists(key))
				.transaction((cmd)->cmd.exists(key))
				.run(args);
	}

	@Override
	public Long exists(final String... keys){
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys);
		return new JedisClusterCommand<Long>(client, ProtocolCommand.EXISTS)
				.general((cmd)->cmd.exists(keys))
				.pipeline((cmd)->cmd.exists(keys))
				.transaction((cmd)->cmd.exists(keys))
				.run(args);
	}

	@Override
	public Long exists(final byte[]... keys){
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys);
		return new JedisClusterCommand<Long>(client, ProtocolCommand.EXISTS)
				.general((cmd)->cmd.exists(keys))
				.pipeline((cmd)->cmd.exists(keys))
				.transaction((cmd)->cmd.exists(keys))
				.run(args);
	}

	@Override
	public Status expire(final String key, final int lifetime){
		final CommandArguments args = CommandArguments.create("key", key).put("lifetime", lifetime);
		return new JedisClusterCommand<Status>(client, ProtocolCommand.EXPIRE)
				.general((cmd)->cmd.expire(key, lifetime), Converters.ONE_STATUS_CONVERTER)
				.pipeline((cmd)->cmd.expire(key, lifetime), Converters.ONE_STATUS_CONVERTER)
				.transaction((cmd)->cmd.expire(key, lifetime), Converters.ONE_STATUS_CONVERTER)
				.run(args);
	}

	@Override
	public Status expire(final byte[] key, final int lifetime){
		final CommandArguments args = CommandArguments.create("key", key).put("lifetime", lifetime);
		return new JedisClusterCommand<Status>(client, ProtocolCommand.EXPIRE)
				.general((cmd)->cmd.expire(key, lifetime), Converters.ONE_STATUS_CONVERTER)
				.pipeline((cmd)->cmd.expire(key, lifetime), Converters.ONE_STATUS_CONVERTER)
				.transaction((cmd)->cmd.expire(key, lifetime), Converters.ONE_STATUS_CONVERTER)
				.run(args);
	}

	@Override
	public Status expire(final String key, final int lifetime, final ExpireOption expireOption){
		final CommandArguments args = CommandArguments.create("key", key).put("lifetime", lifetime)
				.put("expireOption", expireOption);
		final ExpiryOption expiryOption = ExpireOptionConverter.INSTANCE.convert(expireOption);
		return new JedisClusterCommand<Status>(client, ProtocolCommand.EXPIRE)
				.general((cmd)->cmd.expire(key, lifetime, expiryOption), Converters.ONE_STATUS_CONVERTER)
				.pipeline((cmd)->cmd.expire(key, lifetime, expiryOption), Converters.ONE_STATUS_CONVERTER)
				.transaction((cmd)->cmd.expire(key, lifetime, expiryOption), Converters.ONE_STATUS_CONVERTER)
				.run(args);
	}

	@Override
	public Status expire(final byte[] key, final int lifetime, final ExpireOption expireOption){
		final CommandArguments args = CommandArguments.create("key", key).put("lifetime", lifetime)
				.put("expireOption", expireOption);
		final ExpiryOption expiryOption = ExpireOptionConverter.INSTANCE.convert(expireOption);
		return new JedisClusterCommand<Status>(client, ProtocolCommand.EXPIRE)
				.general((cmd)->cmd.expire(key, lifetime, expiryOption), Converters.ONE_STATUS_CONVERTER)
				.pipeline((cmd)->cmd.expire(key, lifetime, expiryOption), Converters.ONE_STATUS_CONVERTER)
				.transaction((cmd)->cmd.expire(key, lifetime, expiryOption), Converters.ONE_STATUS_CONVERTER)
				.run(args);
	}

	@Override
	public Status expireAt(final String key, final long unixTimestamp){
		final CommandArguments args = CommandArguments.create("key", key).put("unixTimestamp", unixTimestamp);
		return new JedisClusterCommand<Status>(client, ProtocolCommand.EXPIREAT)
				.general((cmd)->cmd.expireAt(key, unixTimestamp), Converters.ONE_STATUS_CONVERTER)
				.pipeline((cmd)->cmd.expireAt(key, unixTimestamp), Converters.ONE_STATUS_CONVERTER)
				.transaction((cmd)->cmd.expireAt(key, unixTimestamp), Converters.ONE_STATUS_CONVERTER)
				.run(args);
	}

	@Override
	public Status expireAt(final byte[] key, final long unixTimestamp){
		final CommandArguments args = CommandArguments.create("key", key).put("unixTimestamp", unixTimestamp);
		return new JedisClusterCommand<Status>(client, ProtocolCommand.EXPIREAT)
				.general((cmd)->cmd.expireAt(key, unixTimestamp), Converters.ONE_STATUS_CONVERTER)
				.pipeline((cmd)->cmd.expireAt(key, unixTimestamp), Converters.ONE_STATUS_CONVERTER)
				.transaction((cmd)->cmd.expireAt(key, unixTimestamp), Converters.ONE_STATUS_CONVERTER)
				.run(args);
	}

	@Override
	public Status pExpire(final String key, final int lifetime){
		final CommandArguments args = CommandArguments.create("key", key).put("lifetime", lifetime);
		return new JedisClusterCommand<Status>(client, ProtocolCommand.PEXPIRE)
				.general((cmd)->cmd.pexpire(key, lifetime), Converters.ONE_STATUS_CONVERTER)
				.pipeline((cmd)->cmd.pexpire(key, lifetime), Converters.ONE_STATUS_CONVERTER)
				.transaction((cmd)->cmd.pexpire(key, lifetime), Converters.ONE_STATUS_CONVERTER)
				.run(args);
	}

	@Override
	public Status pExpire(final byte[] key, final int lifetime){
		final CommandArguments args = CommandArguments.create("key", key).put("lifetime", lifetime);
		return new JedisClusterCommand<Status>(client, ProtocolCommand.PEXPIRE)
				.general((cmd)->cmd.pexpire(key, lifetime), Converters.ONE_STATUS_CONVERTER)
				.pipeline((cmd)->cmd.pexpire(key, lifetime), Converters.ONE_STATUS_CONVERTER)
				.transaction((cmd)->cmd.pexpire(key, lifetime), Converters.ONE_STATUS_CONVERTER)
				.run(args);
	}

	@Override
	public Status pExpireAt(final String key, final long unixTimestamp){
		final CommandArguments args = CommandArguments.create("key", key).put("unixTimestamp", unixTimestamp);
		return new JedisClusterCommand<Status>(client, ProtocolCommand.PEXPIREAT)
				.general((cmd)->cmd.pexpireAt(key, unixTimestamp), Converters.ONE_STATUS_CONVERTER)
				.pipeline((cmd)->cmd.pexpireAt(key, unixTimestamp), Converters.ONE_STATUS_CONVERTER)
				.transaction((cmd)->cmd.pexpireAt(key, unixTimestamp), Converters.ONE_STATUS_CONVERTER)
				.run(args);
	}

	@Override
	public Status pExpireAt(final byte[] key, final long unixTimestamp){
		final CommandArguments args = CommandArguments.create("key", key).put("unixTimestamp", unixTimestamp);
		return new JedisClusterCommand<Status>(client, ProtocolCommand.PEXPIREAT)
				.general((cmd)->cmd.pexpireAt(key, unixTimestamp), Converters.ONE_STATUS_CONVERTER)
				.pipeline((cmd)->cmd.pexpireAt(key, unixTimestamp), Converters.ONE_STATUS_CONVERTER)
				.transaction((cmd)->cmd.pexpireAt(key, unixTimestamp), Converters.ONE_STATUS_CONVERTER)
				.run(args);
	}

	@Override
	public Status persist(final String key){
		final CommandArguments args = CommandArguments.create("key", key);
		return new JedisClusterCommand<Status>(client, ProtocolCommand.PERSIST)
				.general((cmd)->cmd.persist(key), Converters.ONE_STATUS_CONVERTER)
				.pipeline((cmd)->cmd.persist(key), Converters.ONE_STATUS_CONVERTER)
				.transaction((cmd)->cmd.persist(key), Converters.ONE_STATUS_CONVERTER)
				.run(args);
	}

	@Override
	public Status persist(final byte[] key){
		final CommandArguments args = CommandArguments.create("key", key);
		return new JedisClusterCommand<Status>(client, ProtocolCommand.PERSIST)
				.general((cmd)->cmd.persist(key), Converters.ONE_STATUS_CONVERTER)
				.pipeline((cmd)->cmd.persist(key), Converters.ONE_STATUS_CONVERTER)
				.transaction((cmd)->cmd.persist(key), Converters.ONE_STATUS_CONVERTER)
				.run(args);
	}

	@Override
	public Long ttl(final String key){
		final CommandArguments args = CommandArguments.create("key", key);
		return new JedisClusterCommand<Long>(client, ProtocolCommand.TTL)
				.general((cmd)->cmd.ttl(key))
				.pipeline((cmd)->cmd.ttl(key))
				.transaction((cmd)->cmd.ttl(key))
				.run(args);
	}

	@Override
	public Long ttl(final byte[] key){
		final CommandArguments args = CommandArguments.create("key", key);
		return new JedisClusterCommand<Long>(client, ProtocolCommand.TTL)
				.general((cmd)->cmd.ttl(key))
				.pipeline((cmd)->cmd.ttl(key))
				.transaction((cmd)->cmd.ttl(key))
				.run(args);
	}

	@Override
	public Long pTtl(final String key){
		final CommandArguments args = CommandArguments.create("key", key);
		return new JedisClusterCommand<Long>(client, ProtocolCommand.PTTL)
				.general((cmd)->cmd.pttl(key))
				.pipeline((cmd)->cmd.pttl(key))
				.transaction((cmd)->cmd.pttl(key))
				.run(args);
	}

	@Override
	public Long pTtl(final byte[] key){
		final CommandArguments args = CommandArguments.create("key", key);
		return new JedisClusterCommand<Long>(client, ProtocolCommand.PTTL)
				.general((cmd)->cmd.pttl(key))
				.pipeline((cmd)->cmd.pttl(key))
				.transaction((cmd)->cmd.pttl(key))
				.run(args);
	}

	@Override
	public Status copy(final String key, final String destKey){
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey);
		return new JedisClusterCommand<Status>(client, ProtocolCommand.COPY)
				.general((cmd)->cmd.copy(key, destKey, false), Converters.BOOLEAN_STATUS_CONVERTER)
				.pipeline((cmd)->cmd.copy(key, destKey, false), Converters.BOOLEAN_STATUS_CONVERTER)
				.transaction((cmd)->cmd.copy(key, destKey, false), Converters.BOOLEAN_STATUS_CONVERTER)
				.run(args);
	}

	@Override
	public Status copy(final byte[] key, final byte[] destKey){
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey);
		return new JedisClusterCommand<Status>(client, ProtocolCommand.COPY)
				.general((cmd)->cmd.copy(key, destKey, false), Converters.BOOLEAN_STATUS_CONVERTER)
				.pipeline((cmd)->cmd.copy(key, destKey, false), Converters.BOOLEAN_STATUS_CONVERTER)
				.transaction((cmd)->cmd.copy(key, destKey, false), Converters.BOOLEAN_STATUS_CONVERTER)
				.run(args);
	}

	@Override
	public Status copy(final String key, final String destKey, final int db){
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey).put("db", db);
		return new JedisClusterCommand<Status>(client, ProtocolCommand.COPY)
				.run(args);
	}

	@Override
	public Status copy(final byte[] key, final byte[] destKey, final int db){
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey)
				.put("db", db);
		return new JedisClusterCommand<Status>(client, ProtocolCommand.COPY)
				.run(args);
	}

	@Override
	public Status copy(final String key, final String destKey, final boolean replace){
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey)
				.put("replace", replace);
		return new JedisClusterCommand<Status>(client, ProtocolCommand.COPY)
				.general((cmd)->cmd.copy(key, destKey, replace), Converters.BOOLEAN_STATUS_CONVERTER)
				.pipeline((cmd)->cmd.copy(key, destKey, replace), Converters.BOOLEAN_STATUS_CONVERTER)
				.transaction((cmd)->cmd.copy(key, destKey, replace), Converters.BOOLEAN_STATUS_CONVERTER)
				.run(args);
	}

	@Override
	public Status copy(final byte[] key, final byte[] destKey, final boolean replace){
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey)
				.put("replace", replace);
		return new JedisClusterCommand<Status>(client, ProtocolCommand.COPY)
				.general((cmd)->cmd.copy(key, destKey, replace), Converters.BOOLEAN_STATUS_CONVERTER)
				.pipeline((cmd)->cmd.copy(key, destKey, replace), Converters.BOOLEAN_STATUS_CONVERTER)
				.transaction((cmd)->cmd.copy(key, destKey, replace), Converters.BOOLEAN_STATUS_CONVERTER)
				.run(args);
	}

	@Override
	public Status copy(final String key, final String destKey, final int db, final boolean replace){
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey).put("db", db)
				.put("replace", replace);
		return new JedisClusterCommand<Status>(client, ProtocolCommand.COPY)
				.run(args);
	}

	@Override
	public Status copy(final byte[] key, final byte[] destKey, final int db, final boolean replace){
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey).put("db", db)
				.put("replace", replace);
		return new JedisClusterCommand<Status>(client, ProtocolCommand.COPY)
				.run(args);
	}

	@Override
	public Status move(final String key, final int db){
		final CommandArguments args = CommandArguments.create("key", key).put("db", db);
		return new JedisClusterCommand<Status>(client, ProtocolCommand.MOVE)
				.run(args);
	}

	@Override
	public Status move(final byte[] key, final int db){
		final CommandArguments args = CommandArguments.create("key", key).put("db", db);
		return new JedisClusterCommand<Status>(client, ProtocolCommand.MOVE)
				.run(args);
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final int timeout, final String... keys){
		final CommandArguments args = CommandArguments.create("host", host).put("port", port).put("db", db)
				.put("timeout", timeout).put("keys", (Object[]) keys);
		return new JedisClusterCommand<Status>(client, ProtocolCommand.MIGRATE)
				.run(args);
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final int timeout, final byte[]... keys){
		final CommandArguments args = CommandArguments.create("host", host).put("port", port).put("db", db)
				.put("timeout", timeout).put("keys", (Object[]) keys);
		return new JedisClusterCommand<Status>(client, ProtocolCommand.MIGRATE)
				.run(args);
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final int timeout,
						  final MigrateOperation operation, final String... keys){
		final CommandArguments args = CommandArguments.create("host", host).put("port", port).put("db", db)
				.put("timeout", timeout).put("operation", operation).put("keys", (Object[]) keys);
		return new JedisClusterCommand<Status>(client, ProtocolCommand.MIGRATE)
				.run(args);
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final int timeout,
						  final MigrateOperation operation, final byte[]... keys){
		final CommandArguments args = CommandArguments.create("host", host).put("port", port).put("db", db)
				.put("timeout", timeout).put("operation", operation).put("keys", (Object[]) keys);
		return new JedisClusterCommand<Status>(client, ProtocolCommand.MIGRATE)
				.run(args);
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final String password, final int timeout,
						  final String... keys){
		final CommandArguments args = CommandArguments.create("host", host).put("port", port).put("db", db)
				.put("password", password).put("timeout", timeout).put("keys", (Object[]) keys);
		return new JedisClusterCommand<Status>(client, ProtocolCommand.MIGRATE)
				.run(args);
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final byte[] password, final int timeout,
						  final byte[]... keys){
		final CommandArguments args = CommandArguments.create("host", host).put("port", port).put("db", db)
				.put("password", password).put("timeout", timeout).put("keys", (Object[]) keys);
		return new JedisClusterCommand<Status>(client, ProtocolCommand.MIGRATE)
				.run(args);
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final String password, final int timeout,
						  final MigrateOperation operation, final String... keys){
		final CommandArguments args = CommandArguments.create("host", host).put("port", port).put("db", db)
				.put("password", password).put("timeout", timeout).put("operation", operation)
				.put("keys", (Object[]) keys);
		return new JedisClusterCommand<Status>(client, ProtocolCommand.MIGRATE)
				.run(args);
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final byte[] password, final int timeout,
						  final MigrateOperation operation, final byte[]... keys){
		final CommandArguments args = CommandArguments.create("host", host).put("port", port).put("db", db)
				.put("password", password).put("timeout", timeout).put("operation", operation)
				.put("keys", (Object[]) keys);
		return new JedisClusterCommand<Status>(client, ProtocolCommand.MIGRATE)
				.run(args);
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final String user, final String password,
						  final int timeout, final String... keys){
		final CommandArguments args = CommandArguments.create("host", host).put("port", port).put("db", db)
				.put("user", user).put("password", password).put("timeout", timeout).put("keys", (Object[]) keys);
		return new JedisClusterCommand<Status>(client, ProtocolCommand.MIGRATE)
				.run(args);
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final byte[] user, final byte[] password,
						  final int timeout, final byte[]... keys){
		final CommandArguments args = CommandArguments.create("host", host).put("port", port).put("db", db)
				.put("user", user).put("password", password).put("timeout", timeout).put("keys", (Object[]) keys);
		return new JedisClusterCommand<Status>(client, ProtocolCommand.MIGRATE)
				.run(args);
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final String user, final String password,
						  final int timeout, final MigrateOperation operation, final String... keys){
		final CommandArguments args = CommandArguments.create("host", host).put("port", port).put("db", db)
				.put("user", user).put("password", password).put("timeout", timeout).put("operation", operation)
				.put("keys", (Object[]) keys);
		return new JedisClusterCommand<Status>(client, ProtocolCommand.MIGRATE)
				.run(args);
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final byte[] user, final byte[] password,
						  final int timeout, final MigrateOperation operation, final byte[]... keys){
		final CommandArguments args = CommandArguments.create("host", host).put("port", port).put("db", db)
				.put("user", user).put("password", password).put("timeout", timeout).put("operation", operation)
				.put("keys", (Object[]) keys);
		return new JedisClusterCommand<Status>(client, ProtocolCommand.MIGRATE)
				.run(args);
	}

	@Override
	public Set<String> keys(final String pattern){
		final CommandArguments args = CommandArguments.create("pattern", pattern);
		return new JedisClusterCommand<Set<String>>(client, ProtocolCommand.KEYS)
				.general((cmd)->cmd.keys(pattern))
				.pipeline((cmd)->cmd.keys(pattern))
				.transaction((cmd)->cmd.keys(pattern))
				.run(args);
	}

	@Override
	public Set<byte[]> keys(final byte[] pattern){
		final CommandArguments args = CommandArguments.create("pattern", pattern);
		return new JedisClusterCommand<Set<byte[]>>(client, ProtocolCommand.KEYS)
				.general((cmd)->cmd.keys(pattern))
				.pipeline((cmd)->cmd.keys(pattern))
				.transaction((cmd)->cmd.keys(pattern))
				.run(args);
	}

	@Override
	public String randomKey(){
		return new JedisClusterCommand<String>(client, ProtocolCommand.RANDOMKEY)
				.general((cmd)->cmd.randomKey())
				.pipeline((cmd)->cmd.randomKey())
				.transaction((cmd)->cmd.randomKey())
				.run();
	}

	@Override
	public Status rename(final String key, final String newKey){
		final CommandArguments args = CommandArguments.create("key", key).put("newKey", newKey);
		return new JedisClusterCommand<Status>(client, ProtocolCommand.RENAME)
				.general((cmd)->cmd.rename(key, newKey), OkStatusConverter.INSTANCE)
				.pipeline((cmd)->cmd.rename(key, newKey), OkStatusConverter.INSTANCE)
				.transaction((cmd)->cmd.rename(key, newKey), OkStatusConverter.INSTANCE)
				.run(args);
	}

	@Override
	public Status rename(final byte[] key, final byte[] newKey){
		final CommandArguments args = CommandArguments.create("key", key).put("newKey", newKey);
		return new JedisClusterCommand<Status>(client, ProtocolCommand.RENAME)
				.general((cmd)->cmd.rename(key, newKey), OkStatusConverter.INSTANCE)
				.pipeline((cmd)->cmd.rename(key, newKey), OkStatusConverter.INSTANCE)
				.transaction((cmd)->cmd.rename(key, newKey), OkStatusConverter.INSTANCE)
				.run(args);
	}

	@Override
	public Status renameNx(final String key, final String newKey){
		final CommandArguments args = CommandArguments.create("key", key).put("newKey", newKey);
		return new JedisClusterCommand<Status>(client, ProtocolCommand.RENAME)
				.general((cmd)->cmd.renamenx(key, newKey), Converters.ONE_STATUS_CONVERTER)
				.pipeline((cmd)->cmd.renamenx(key, newKey), Converters.ONE_STATUS_CONVERTER)
				.transaction((cmd)->cmd.renamenx(key, newKey), Converters.ONE_STATUS_CONVERTER)
				.run(args);
	}

	@Override
	public Status renameNx(final byte[] key, final byte[] newKey){
		final CommandArguments args = CommandArguments.create("key", key).put("newKey", newKey);
		return new JedisClusterCommand<Status>(client, ProtocolCommand.RENAME)
				.general((cmd)->cmd.renamenx(key, newKey), Converters.ONE_STATUS_CONVERTER)
				.pipeline((cmd)->cmd.renamenx(key, newKey), Converters.ONE_STATUS_CONVERTER)
				.transaction((cmd)->cmd.renamenx(key, newKey), Converters.ONE_STATUS_CONVERTER)
				.run(args);
	}

	@Override
	public Status restore(final String key, final byte[] serializedValue, final int ttl){
		final CommandArguments args = CommandArguments.create("key", key).put("serializedValue", serializedValue)
				.put("ttl", ttl);
		return new JedisClusterCommand<Status>(client, ProtocolCommand.RESTORE)
				.general((cmd)->cmd.restore(key, ttl, serializedValue), OkStatusConverter.INSTANCE)
				.pipeline((cmd)->cmd.restore(key, ttl, serializedValue), OkStatusConverter.INSTANCE)
				.transaction((cmd)->cmd.restore(key, ttl, serializedValue), OkStatusConverter.INSTANCE)
				.run(args);
	}

	@Override
	public Status restore(final byte[] key, final byte[] serializedValue, final int ttl){
		final CommandArguments args = CommandArguments.create("key", key).put("serializedValue", serializedValue)
				.put("ttl", ttl);
		return new JedisClusterCommand<Status>(client, ProtocolCommand.RESTORE)
				.general((cmd)->cmd.restore(key, ttl, serializedValue), OkStatusConverter.INSTANCE)
				.pipeline((cmd)->cmd.restore(key, ttl, serializedValue), OkStatusConverter.INSTANCE)
				.transaction((cmd)->cmd.restore(key, ttl, serializedValue), OkStatusConverter.INSTANCE)
				.run(args);
	}

	@Override
	public Status restore(final String key, final byte[] serializedValue, final int ttl,
						  final RestoreArgument argument){
		final CommandArguments args = CommandArguments.create("key", key).put("serializedValue", serializedValue)
				.put("ttl", ttl).put("argument", argument);
		final RestoreParams params = RestoreArgumentConverter.INSTANCE.convert(argument);
		return new JedisClusterCommand<Status>(client, ProtocolCommand.RESTORE)
				.general((cmd)->cmd.restore(key, ttl, serializedValue, params), OkStatusConverter.INSTANCE)
				.pipeline((cmd)->cmd.restore(key, ttl, serializedValue, params), OkStatusConverter.INSTANCE)
				.transaction((cmd)->cmd.restore(key, ttl, serializedValue, params), OkStatusConverter.INSTANCE)
				.run(args);
	}

	@Override
	public Status restore(final byte[] key, final byte[] serializedValue, final int ttl,
						  final RestoreArgument argument){
		final CommandArguments args = CommandArguments.create("key", key).put("serializedValue", serializedValue)
				.put("ttl", ttl).put("argument", argument);
		final RestoreParams params = RestoreArgumentConverter.INSTANCE.convert(argument);
		return new JedisClusterCommand<Status>(client, ProtocolCommand.RESTORE)
				.general((cmd)->cmd.restore(key, ttl, serializedValue, params), OkStatusConverter.INSTANCE)
				.pipeline((cmd)->cmd.restore(key, ttl, serializedValue, params), OkStatusConverter.INSTANCE)
				.transaction((cmd)->cmd.restore(key, ttl, serializedValue, params), OkStatusConverter.INSTANCE)
				.run(args);
	}

	@Override
	public ScanResult<List<String>> scan(final String cursor){
		final CommandArguments args = CommandArguments.create("cursor", cursor);
		return new JedisClusterCommand<ScanResult<List<String>>>(client, ProtocolCommand.SCAN)
				.general((cmd)->cmd.scan(cursor), ScanResultConverter.ListScanResultConverter.STRING_LIST_CONVERTER)
				.pipeline((cmd)->cmd.scan(cursor), ScanResultConverter.ListScanResultConverter.STRING_LIST_CONVERTER)
				.transaction((cmd)->cmd.scan(cursor),
						ScanResultConverter.ListScanResultConverter.STRING_LIST_CONVERTER)
				.run(args);
	}

	@Override
	public ScanResult<List<byte[]>> scan(final byte[] cursor){
		final CommandArguments args = CommandArguments.create("cursor", cursor);
		return new JedisClusterCommand<ScanResult<List<byte[]>>>(client, ProtocolCommand.SCAN)
				.general((cmd)->cmd.scan(cursor), ScanResultConverter.ListScanResultConverter.BINARY_LIST_CONVERTER)
				.pipeline((cmd)->cmd.scan(cursor), ScanResultConverter.ListScanResultConverter.BINARY_LIST_CONVERTER)
				.transaction((cmd)->cmd.scan(cursor),
						ScanResultConverter.ListScanResultConverter.BINARY_LIST_CONVERTER)
				.run(args);
	}

	@Override
	public ScanResult<List<String>> scan(final String cursor, final String pattern){
		final CommandArguments args = CommandArguments.create("cursor", cursor).put("pattern", pattern);
		final JedisScanParams params = new JedisScanParams(pattern);
		return new JedisClusterCommand<ScanResult<List<String>>>(client, ProtocolCommand.SCAN)
				.general((cmd)->cmd.scan(cursor, params),
						ScanResultConverter.ListScanResultConverter.STRING_LIST_CONVERTER)
				.pipeline((cmd)->cmd.scan(cursor, params),
						ScanResultConverter.ListScanResultConverter.STRING_LIST_CONVERTER)
				.transaction((cmd)->cmd.scan(cursor, params),
						ScanResultConverter.ListScanResultConverter.STRING_LIST_CONVERTER)
				.run(args);
	}

	@Override
	public ScanResult<List<byte[]>> scan(final byte[] cursor, final byte[] pattern){
		final CommandArguments args = CommandArguments.create("cursor", cursor).put("pattern", pattern);
		final JedisScanParams params = new JedisScanParams(pattern);
		return new JedisClusterCommand<ScanResult<List<byte[]>>>(client, ProtocolCommand.SCAN)
				.general((cmd)->cmd.scan(cursor, params),
						ScanResultConverter.ListScanResultConverter.BINARY_LIST_CONVERTER)
				.pipeline((cmd)->cmd.scan(cursor, params),
						ScanResultConverter.ListScanResultConverter.BINARY_LIST_CONVERTER)
				.transaction((cmd)->cmd.scan(cursor, params),
						ScanResultConverter.ListScanResultConverter.BINARY_LIST_CONVERTER)
				.run(args);
	}

	@Override
	public ScanResult<List<String>> scan(final String cursor, final long count){
		final CommandArguments args = CommandArguments.create("cursor", cursor).put("count", count);
		final JedisScanParams params = new JedisScanParams(count);
		return new JedisClusterCommand<ScanResult<List<String>>>(client, ProtocolCommand.SCAN)
				.general((cmd)->cmd.scan(cursor, params),
						ScanResultConverter.ListScanResultConverter.STRING_LIST_CONVERTER)
				.pipeline((cmd)->cmd.scan(cursor, params),
						ScanResultConverter.ListScanResultConverter.STRING_LIST_CONVERTER)
				.transaction((cmd)->cmd.scan(cursor, params),
						ScanResultConverter.ListScanResultConverter.STRING_LIST_CONVERTER)
				.run(args);
	}

	@Override
	public ScanResult<List<byte[]>> scan(final byte[] cursor, final long count){
		final CommandArguments args = CommandArguments.create("cursor", cursor).put("count", count);
		final JedisScanParams params = new JedisScanParams(count);
		return new JedisClusterCommand<ScanResult<List<byte[]>>>(client, ProtocolCommand.SCAN)
				.general((cmd)->cmd.scan(cursor, params),
						ScanResultConverter.ListScanResultConverter.BINARY_LIST_CONVERTER)
				.pipeline((cmd)->cmd.scan(cursor, params),
						ScanResultConverter.ListScanResultConverter.BINARY_LIST_CONVERTER)
				.transaction((cmd)->cmd.scan(cursor, params),
						ScanResultConverter.ListScanResultConverter.BINARY_LIST_CONVERTER)
				.run(args);
	}

	@Override
	public ScanResult<List<String>> scan(final String cursor, final String pattern, final long count){
		final CommandArguments args = CommandArguments.create("cursor", cursor).put("pattern", pattern)
				.put("count", count);
		final JedisScanParams params = new JedisScanParams(pattern, count);
		return new JedisClusterCommand<ScanResult<List<String>>>(client, ProtocolCommand.SCAN)
				.general((cmd)->cmd.scan(cursor, params),
						ScanResultConverter.ListScanResultConverter.STRING_LIST_CONVERTER)
				.pipeline((cmd)->cmd.scan(cursor, params),
						ScanResultConverter.ListScanResultConverter.STRING_LIST_CONVERTER)
				.transaction((cmd)->cmd.scan(cursor, params),
						ScanResultConverter.ListScanResultConverter.STRING_LIST_CONVERTER)
				.run(args);
	}

	@Override
	public ScanResult<List<byte[]>> scan(final byte[] cursor, final byte[] pattern, final long count){
		final CommandArguments args = CommandArguments.create("cursor", cursor).put("pattern", pattern)
				.put("count", count);
		final JedisScanParams params = new JedisScanParams(pattern, count);
		return new JedisClusterCommand<ScanResult<List<byte[]>>>(client, ProtocolCommand.SCAN)
				.general((cmd)->cmd.scan(cursor, params),
						ScanResultConverter.ListScanResultConverter.BINARY_LIST_CONVERTER)
				.pipeline((cmd)->cmd.scan(cursor, params),
						ScanResultConverter.ListScanResultConverter.BINARY_LIST_CONVERTER)
				.transaction((cmd)->cmd.scan(cursor, params),
						ScanResultConverter.ListScanResultConverter.BINARY_LIST_CONVERTER)
				.run(args);
	}

	@Override
	public List<String> sort(final String key){
		final CommandArguments args = CommandArguments.create("key", key);
		return new JedisClusterCommand<List<String>>(client, ProtocolCommand.SORT)
				.general((cmd)->cmd.sort(key))
				.pipeline((cmd)->cmd.sort(key))
				.transaction((cmd)->cmd.sort(key))
				.run(args);
	}

	@Override
	public List<byte[]> sort(final byte[] key){
		final CommandArguments args = CommandArguments.create("key", key);
		return new JedisClusterCommand<List<byte[]>>(client, ProtocolCommand.SORT)
				.general((cmd)->cmd.sort(key))
				.pipeline((cmd)->cmd.sort(key))
				.transaction((cmd)->cmd.sort(key))
				.run(args);
	}

	@Override
	public List<String> sort(final String key, final SortArgument sortArgument){
		final CommandArguments args = CommandArguments.create("key", key).put("sortArgument", sortArgument);
		final SortingParams params = SortArgumentConverter.INSTANCE.convert(sortArgument);
		return new JedisClusterCommand<List<String>>(client, ProtocolCommand.SORT)
				.general((cmd)->cmd.sort(key, params))
				.pipeline((cmd)->cmd.sort(key, params))
				.transaction((cmd)->cmd.sort(key, params))
				.run(args);
	}

	@Override
	public List<byte[]> sort(final byte[] key, final SortArgument sortArgument){
		final CommandArguments args = CommandArguments.create("key", key).put("sortArgument", sortArgument);
		final SortingParams params = SortArgumentConverter.INSTANCE.convert(sortArgument);
		return new JedisClusterCommand<List<byte[]>>(client, ProtocolCommand.SORT)
				.general((cmd)->cmd.sort(key, params))
				.pipeline((cmd)->cmd.sort(key, params))
				.transaction((cmd)->cmd.sort(key, params))
				.run(args);
	}

	@Override
	public Long sort(final String key, final String destKey){
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey);
		return new JedisClusterCommand<Long>(client, ProtocolCommand.SORT)
				.general((cmd)->cmd.sort(key, destKey))
				.pipeline((cmd)->cmd.sort(key, destKey))
				.transaction((cmd)->cmd.sort(key, destKey))
				.run(args);
	}

	@Override
	public Long sort(final byte[] key, final byte[] destKey){
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey);
		return new JedisClusterCommand<Long>(client, ProtocolCommand.SORT)
				.general((cmd)->cmd.sort(key, destKey))
				.pipeline((cmd)->cmd.sort(key, destKey))
				.transaction((cmd)->cmd.sort(key, destKey))
				.run(args);
	}

	@Override
	public Long sort(final String key, final String destKey, final SortArgument sortArgument){
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey)
				.put("sortArgument", sortArgument);
		final SortingParams params = SortArgumentConverter.INSTANCE.convert(sortArgument);
		return new JedisClusterCommand<Long>(client, ProtocolCommand.SORT)
				.general((cmd)->cmd.sort(key, params, destKey))
				.pipeline((cmd)->cmd.sort(key, params, destKey))
				.transaction((cmd)->cmd.sort(key, params, destKey))
				.run(args);
	}

	@Override
	public Long sort(final byte[] key, final byte[] destKey, final SortArgument sortArgument){
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey)
				.put("sortArgument", sortArgument);
		final SortingParams params = SortArgumentConverter.INSTANCE.convert(sortArgument);
		return new JedisClusterCommand<Long>(client, ProtocolCommand.SORT)
				.general((cmd)->cmd.sort(key, params, destKey))
				.pipeline((cmd)->cmd.sort(key, params, destKey))
				.transaction((cmd)->cmd.sort(key, params, destKey))
				.run(args);
	}

	@Override
	public Long touch(final String... keys){
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys);
		return new JedisClusterCommand<Long>(client, ProtocolCommand.TOUCH)
				.general((cmd)->cmd.touch(keys))
				.pipeline((cmd)->cmd.touch(keys))
				.transaction((cmd)->cmd.touch(keys))
				.run(args);
	}

	@Override
	public Long touch(final byte[]... keys){
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys);
		return new JedisClusterCommand<Long>(client, ProtocolCommand.TOUCH)
				.general((cmd)->cmd.touch(keys))
				.pipeline((cmd)->cmd.touch(keys))
				.transaction((cmd)->cmd.touch(keys))
				.run(args);
	}

	@Override
	public Type type(final String key){
		final CommandArguments args = CommandArguments.create("key", key);
		return new JedisClusterCommand<Type>(client, ProtocolCommand.TYPE)
				.general((cmd)->cmd.type(key), Converters.TYPE_RESULT_CONVERTER)
				.pipeline((cmd)->cmd.type(key), Converters.TYPE_RESULT_CONVERTER)
				.transaction((cmd)->cmd.type(key), Converters.TYPE_RESULT_CONVERTER)
				.run(args);
	}

	@Override
	public Type type(final byte[] key){
		final CommandArguments args = CommandArguments.create("key", key);
		return new JedisClusterCommand<Type>(client, ProtocolCommand.TYPE)
				.general((cmd)->cmd.type(key), Converters.TYPE_RESULT_CONVERTER)
				.pipeline((cmd)->cmd.type(key), Converters.TYPE_RESULT_CONVERTER)
				.transaction((cmd)->cmd.type(key), Converters.TYPE_RESULT_CONVERTER)
				.run(args);
	}

	@Override
	public Long unlink(final String... keys){
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys);
		return new JedisClusterCommand<Long>(client, ProtocolCommand.UNLINK)
				.general((cmd)->cmd.unlink(keys))
				.pipeline((cmd)->cmd.unlink(keys))
				.transaction((cmd)->cmd.unlink(keys))
				.run(args);
	}

	@Override
	public Long unlink(final byte[]... keys){
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys);
		return new JedisClusterCommand<Long>(client, ProtocolCommand.UNLINK)
				.general((cmd)->cmd.unlink(keys))
				.pipeline((cmd)->cmd.unlink(keys))
				.transaction((cmd)->cmd.unlink(keys))
				.run(args);
	}

	@Override
	public Long wait(final int replicas, final int timeout){
		final CommandArguments args = CommandArguments.create("replicas", replicas).put("timeout", timeout);
		return new JedisClusterCommand<Long>(client, ProtocolCommand.WAIT)
				.general((cmd)->cmd.waitReplicas((String) null, replicas, timeout))
				.pipeline((cmd)->cmd.waitReplicas(replicas, timeout))
				.transaction((cmd)->cmd.waitReplicas(replicas, timeout))
				.run(args);
	}

	@Override
	public ObjectEncoding objectEncoding(final String key){
		final CommandArguments args = CommandArguments.create("key", key);
		return new JedisClusterCommand<ObjectEncoding>(client, ProtocolCommand.OBJECT_ENCODING)
				.general((cmd)->cmd.objectEncoding(key), Converters.STRING_OBJECT_ENCODING_RESULT_CONVERTER)
				.pipeline((cmd)->cmd.objectEncoding(key), Converters.STRING_OBJECT_ENCODING_RESULT_CONVERTER)
				.transaction((cmd)->cmd.objectEncoding(key), Converters.STRING_OBJECT_ENCODING_RESULT_CONVERTER)
				.run(args);
	}

	@Override
	public ObjectEncoding objectEncoding(final byte[] key){
		final CommandArguments args = CommandArguments.create("key", key);
		return new JedisClusterCommand<ObjectEncoding>(client, ProtocolCommand.OBJECT_ENCODING)
				.general((cmd)->cmd.objectEncoding(key), Converters.BINARY_OBJECT_ENCODING_RESULT_CONVERTER)
				.pipeline((cmd)->cmd.objectEncoding(key), Converters.BINARY_OBJECT_ENCODING_RESULT_CONVERTER)
				.transaction((cmd)->cmd.objectEncoding(key), Converters.BINARY_OBJECT_ENCODING_RESULT_CONVERTER)
				.run(args);
	}

	@Override
	public Long objectFreq(final String key){
		final CommandArguments args = CommandArguments.create("key", key);
		return new JedisClusterCommand<Long>(client, ProtocolCommand.OBJECT_REFQ)
				.general((cmd)->cmd.objectFreq(key))
				.pipeline((cmd)->cmd.objectFreq(key))
				.transaction((cmd)->cmd.objectFreq(key))
				.run(args);
	}

	@Override
	public Long objectFreq(final byte[] key){
		final CommandArguments args = CommandArguments.create("key", key);
		return new JedisClusterCommand<Long>(client, ProtocolCommand.OBJECT_REFQ)
				.general((cmd)->cmd.objectFreq(key))
				.pipeline((cmd)->cmd.objectFreq(key))
				.transaction((cmd)->cmd.objectFreq(key))
				.run(args);
	}

	@Override
	public Long objectIdleTime(final String key){
		final CommandArguments args = CommandArguments.create("key", key);
		return new JedisClusterCommand<Long>(client, ProtocolCommand.OBJECT_IDLETIME)
				.general((cmd)->cmd.objectIdletime(key))
				.pipeline((cmd)->cmd.objectIdletime(key))
				.transaction((cmd)->cmd.objectIdletime(key))
				.run(args);
	}

	@Override
	public Long objectIdleTime(final byte[] key){
		final CommandArguments args = CommandArguments.create("key", key);
		return new JedisClusterCommand<Long>(client, ProtocolCommand.OBJECT_IDLETIME)
				.general((cmd)->cmd.objectIdletime(key))
				.pipeline((cmd)->cmd.objectIdletime(key))
				.transaction((cmd)->cmd.objectIdletime(key))
				.run(args);
	}

	@Override
	public Long objectRefcount(final String key){
		final CommandArguments args = CommandArguments.create("key", key);
		return new JedisClusterCommand<Long>(client, ProtocolCommand.OBJECT_REFCOUNT)
				.general((cmd)->cmd.objectRefcount(key))
				.pipeline((cmd)->cmd.objectRefcount(key))
				.transaction((cmd)->cmd.objectRefcount(key))
				.run(args);
	}

	@Override
	public Long objectRefcount(final byte[] key){
		final CommandArguments args = CommandArguments.create("key", key);
		return new JedisClusterCommand<Long>(client, ProtocolCommand.OBJECT_REFCOUNT)
				.general((cmd)->cmd.objectRefcount(key))
				.pipeline((cmd)->cmd.objectRefcount(key))
				.transaction((cmd)->cmd.objectRefcount(key))
				.run(args);
	}

}
