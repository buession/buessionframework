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

import com.buession.core.converter.EnumConverter;
import com.buession.core.converter.PredicateStatusConverter;
import com.buession.core.utils.NumberUtils;
import com.buession.lang.Status;
import com.buession.redis.client.jedis.JedisClusterClient;
import com.buession.redis.core.ExpireOption;
import com.buession.redis.core.MigrateOperation;
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.Type;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.CommandNotSupported;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.convert.Converters;
import com.buession.redis.core.convert.OkStatusConverter;
import com.buession.redis.core.convert.jedis.MigrateOperationConverter;
import com.buession.redis.core.convert.jedis.ScanResultConverter;
import com.buession.redis.core.convert.jedis.SortArgumentConverter;
import com.buession.redis.core.internal.jedis.JedisScanParams;
import com.buession.redis.exception.RedisExceptionUtils;
import com.buession.redis.utils.SafeEncoder;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.SortingParams;
import redis.clients.jedis.params.MigrateParams;

import java.util.List;
import java.util.Set;

/**
 * Jedis 集群模式 Key 命令操作抽象类
 *
 * @author Yong.Teng
 */
public final class JedisClusterKeyOperations extends AbstractKeyOperations<JedisCluster> {

	public JedisClusterKeyOperations(final JedisClusterClient client){
		super(client);
	}

	@Override
	public Long del(final String... keys){
		final CommandArguments args = CommandArguments.create("keys", keys);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().del(keys)), ProtocolCommand.DEL, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().del(keys)), ProtocolCommand.DEL, args);
		}else{
			return execute((cmd)->cmd.del(keys), ProtocolCommand.DEL, args);
		}
	}

	@Override
	public Long del(final byte[]... keys){
		final CommandArguments args = CommandArguments.create("keys", keys);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().del(keys)), ProtocolCommand.DEL, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().del(keys)), ProtocolCommand.DEL, args);
		}else{
			return execute((cmd)->cmd.del(keys), ProtocolCommand.DEL, args);
		}
	}

	@Override
	public String dump(final String key){
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().dump(key)), ProtocolCommand.DUMP, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().dump(key)), ProtocolCommand.DUMP, args);
		}else{
			return execute((cmd)->SafeEncoder.encode(cmd.dump(key)), ProtocolCommand.DUMP, args);
		}
	}

	@Override
	public byte[] dump(final byte[] key){
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().dump(key)), ProtocolCommand.DUMP, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().dump(key)), ProtocolCommand.DUMP, args);
		}else{
			return execute((cmd)->cmd.dump(key), ProtocolCommand.DUMP, args);
		}
	}

	@Override
	public boolean exists(final String key){
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().exists(key)), ProtocolCommand.EXISTS, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().exists(key)), ProtocolCommand.EXISTS,
					args);
		}else{
			return execute((cmd)->cmd.exists(key), ProtocolCommand.EXISTS, args);
		}
	}

	@Override
	public boolean exists(final byte[] key){
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().exists(key)), ProtocolCommand.EXISTS, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().exists(key)), ProtocolCommand.EXISTS,
					args);
		}else{
			return execute((cmd)->cmd.exists(key), ProtocolCommand.EXISTS, args);
		}
	}

	@Override
	public long exists(final String... keys){
		final CommandArguments args = CommandArguments.create("keys", keys);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().exists(keys)), ProtocolCommand.EXISTS, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().exists(keys)), ProtocolCommand.EXISTS,
					args);
		}else{
			return execute((cmd)->cmd.exists(keys), ProtocolCommand.EXISTS, args);
		}
	}

	@Override
	public long exists(final byte[]... keys){
		final CommandArguments args = CommandArguments.create("keys", keys);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().exists(keys)), ProtocolCommand.EXISTS, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().exists(keys)), ProtocolCommand.EXISTS,
					args);
		}else{
			return execute((cmd)->cmd.exists(keys), ProtocolCommand.EXISTS, args);
		}
	}

	@Override
	public Status expire(final String key, final int lifetime){
		final CommandArguments args = CommandArguments.create("key", key).put("lifetime", lifetime);
		final PredicateStatusConverter<Long> converter = new PredicateStatusConverter<>((val)->val == 1);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().expire(key, lifetime), converter),
					ProtocolCommand.EXPIRE, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().expire(key, lifetime), converter),
					ProtocolCommand.EXPIRE, args);
		}else{
			return execute((cmd)->cmd.expire(key, lifetime), converter, ProtocolCommand.EXPIRE, args);
		}
	}

	@Override
	public Status expire(final byte[] key, final int lifetime){
		final CommandArguments args = CommandArguments.create("key", key).put("lifetime", lifetime);
		final PredicateStatusConverter<Long> converter = new PredicateStatusConverter<>((val)->val == 1);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().expire(key, lifetime), converter),
					ProtocolCommand.EXPIRE, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().expire(key, lifetime), converter),
					ProtocolCommand.EXPIRE, args);
		}else{
			return execute((cmd)->cmd.expire(key, lifetime), converter, ProtocolCommand.EXPIRE, args);
		}
	}

	@Override
	public Status expire(final String key, final int lifetime, final ExpireOption expireOption){
		final CommandArguments args = CommandArguments.create("key", key).put("lifetime", lifetime)
				.put("expireOption", expireOption);
		final PredicateStatusConverter<Long> converter = new PredicateStatusConverter<>((val)->val == 1);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().expire(key, lifetime), converter),
					ProtocolCommand.EXPIRE, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().expire(key, lifetime), converter),
					ProtocolCommand.EXPIRE, args);
		}else{
			return execute((cmd)->cmd.expire(key, lifetime), converter, ProtocolCommand.EXPIRE, args);
		}
	}

	@Override
	public Status expire(final byte[] key, final int lifetime, final ExpireOption expireOption){
		final CommandArguments args = CommandArguments.create("key", key).put("lifetime", lifetime)
				.put("expireOption", expireOption);
		final PredicateStatusConverter<Long> converter = new PredicateStatusConverter<>((val)->val == 1);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().expire(key, lifetime), converter),
					ProtocolCommand.EXPIRE, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().expire(key, lifetime), converter),
					ProtocolCommand.EXPIRE, args);
		}else{
			return execute((cmd)->cmd.expire(key, lifetime), converter, ProtocolCommand.EXPIRE, args);
		}
	}

	@Override
	public Status expireAt(final String key, final long unixTimestamp){
		final CommandArguments args = CommandArguments.create("key", key).put("unixTimestamp", unixTimestamp);
		final PredicateStatusConverter<Long> converter = new PredicateStatusConverter<>((val)->val == 1);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().expireAt(key, unixTimestamp), converter),
					ProtocolCommand.EXPIREAT, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().expireAt(key, unixTimestamp), converter),
					ProtocolCommand.EXPIREAT, args);
		}else{
			return execute((cmd)->cmd.expireAt(key, unixTimestamp), converter, ProtocolCommand.EXPIREAT, args);
		}
	}

	@Override
	public Status expireAt(final byte[] key, final long unixTimestamp){
		final CommandArguments args = CommandArguments.create("key", key).put("unixTimestamp", unixTimestamp);
		final PredicateStatusConverter<Long> converter = new PredicateStatusConverter<>((val)->val == 1);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().expireAt(key, unixTimestamp), converter),
					ProtocolCommand.EXPIREAT, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().expireAt(key, unixTimestamp), converter),
					ProtocolCommand.EXPIREAT, args);
		}else{
			return execute((cmd)->cmd.expireAt(key, unixTimestamp), converter, ProtocolCommand.EXPIREAT, args);
		}
	}

	@Override
	public Status copy(final String key, final String destKey){
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().copy(key, destKey, true),
					Converters.BOOLEAN_STATUS_CONVERTER), ProtocolCommand.COPY, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().copy(key, destKey, true),
					Converters.BOOLEAN_STATUS_CONVERTER), ProtocolCommand.COPY, args);
		}else{
			return execute((cmd)->cmd.copy(key, destKey, true), Converters.BOOLEAN_STATUS_CONVERTER,
					ProtocolCommand.COPY, args);
		}
	}

	@Override
	public Status copy(final byte[] key, final byte[] destKey){
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().copy(key, destKey, true),
					Converters.BOOLEAN_STATUS_CONVERTER), ProtocolCommand.COPY, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().copy(key, destKey, true),
					Converters.BOOLEAN_STATUS_CONVERTER), ProtocolCommand.COPY, args);
		}else{
			return execute((cmd)->cmd.copy(key, destKey, true), Converters.BOOLEAN_STATUS_CONVERTER,
					ProtocolCommand.COPY, args);
		}
	}

	@Override
	public Status copy(final String key, final String destKey, final int db){
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey)
				.put("db", db);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().copy(key, destKey, true),
					Converters.BOOLEAN_STATUS_CONVERTER), ProtocolCommand.COPY, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().copy(key, destKey, true),
					Converters.BOOLEAN_STATUS_CONVERTER), ProtocolCommand.COPY, args);
		}else{
			return execute((cmd)->cmd.copy(key, destKey, true), Converters.BOOLEAN_STATUS_CONVERTER,
					ProtocolCommand.COPY, args);
		}
	}

	@Override
	public Status copy(final byte[] key, final byte[] destKey, final int db){
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey)
				.put("db", db);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().copy(key, destKey, true),
					Converters.BOOLEAN_STATUS_CONVERTER), ProtocolCommand.COPY, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().copy(key, destKey, true),
					Converters.BOOLEAN_STATUS_CONVERTER), ProtocolCommand.COPY, args);
		}else{
			return execute((cmd)->cmd.copy(key, destKey, true), Converters.BOOLEAN_STATUS_CONVERTER,
					ProtocolCommand.COPY, args);
		}
	}

	@Override
	public Status copy(final String key, final String destKey, final boolean replace){
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey)
				.put("replace", replace);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().copy(key, destKey, replace),
					Converters.BOOLEAN_STATUS_CONVERTER), ProtocolCommand.COPY, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().copy(key, destKey, replace),
					Converters.BOOLEAN_STATUS_CONVERTER), ProtocolCommand.COPY, args);
		}else{
			return execute((cmd)->cmd.copy(key, destKey, replace), Converters.BOOLEAN_STATUS_CONVERTER,
					ProtocolCommand.COPY, args);
		}
	}

	@Override
	public Status copy(final byte[] key, final byte[] destKey, final boolean replace){
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey)
				.put("replace", replace);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().copy(key, destKey, replace),
					Converters.BOOLEAN_STATUS_CONVERTER), ProtocolCommand.COPY, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().copy(key, destKey, replace),
					Converters.BOOLEAN_STATUS_CONVERTER), ProtocolCommand.COPY, args);
		}else{
			return execute((cmd)->cmd.copy(key, destKey, replace), Converters.BOOLEAN_STATUS_CONVERTER,
					ProtocolCommand.COPY, args);
		}
	}

	@Override
	public Status copy(final String key, final String destKey, final int db, final boolean replace){
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey).put("db", db)
				.put("replace", replace);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().copy(key, destKey, replace),
					Converters.BOOLEAN_STATUS_CONVERTER), ProtocolCommand.COPY, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().copy(key, destKey, replace),
					Converters.BOOLEAN_STATUS_CONVERTER), ProtocolCommand.COPY, args);
		}else{
			return execute((cmd)->cmd.copy(key, destKey, replace), Converters.BOOLEAN_STATUS_CONVERTER,
					ProtocolCommand.COPY, args);
		}
	}

	@Override
	public Status copy(final byte[] key, final byte[] destKey, final int db, final boolean replace){
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey).put("db", db)
				.put("replace", replace);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().copy(key, destKey, replace),
					Converters.BOOLEAN_STATUS_CONVERTER), ProtocolCommand.COPY, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().copy(key, destKey, replace),
					Converters.BOOLEAN_STATUS_CONVERTER), ProtocolCommand.COPY, args);
		}else{
			return execute((cmd)->cmd.copy(key, destKey, replace), Converters.BOOLEAN_STATUS_CONVERTER,
					ProtocolCommand.COPY, args);
		}
	}

	@Override
	public Status move(final String key, final int db){
		final CommandArguments args = CommandArguments.create("key", key).put("db", db);
		return execute(CommandNotSupported.ALL, ProtocolCommand.MOVE, args);
	}

	@Override
	public Status move(final byte[] key, final int db){
		final CommandArguments args = CommandArguments.create("key", key).put("db", db);
		return execute(CommandNotSupported.ALL, ProtocolCommand.MOVE, args);
	}

	@Override
	public Status migrate(final String key, final String host, final int port, final int db, final int timeout){
		final CommandArguments args = CommandArguments.create("key", key).put("host", host).put("port", port)
				.put("db", db).put("timeout", timeout);
		return execute(CommandNotSupported.ALL, ProtocolCommand.MIGRATE, args);
	}

	@Override
	public Status migrate(final byte[] key, final String host, final int port, final int db, final int timeout){
		final CommandArguments args = CommandArguments.create("key", key).put("host", host).put("port", port)
				.put("db", db).put("timeout", timeout);
		return execute(CommandNotSupported.ALL, ProtocolCommand.MIGRATE, args);
	}

	@Override
	public Status migrate(final String key, final String host, final int port, final int db, final int timeout,
						  final MigrateOperation operation){
		final CommandArguments args = CommandArguments.create("key", key).put("host", host).put("port", port)
				.put("db", db).put("timeout", timeout).put("operation", operation);
		return execute(CommandNotSupported.ALL, ProtocolCommand.MIGRATE, args);
	}

	@Override
	public Status migrate(final byte[] key, final String host, final int port, final int db, final int timeout,
						  final MigrateOperation operation){
		final CommandArguments args = CommandArguments.create("key", key).put("host", host).put("port", port)
				.put("db", db).put("timeout", timeout).put("operation", operation);
		return execute(CommandNotSupported.ALL, ProtocolCommand.MIGRATE, args);
	}

	@Override
	public Status migrate(final String key, final String host, final int port, final int db, final String password,
						  final int timeout){
		final CommandArguments args = CommandArguments.create("key", key).put("host", host).put("port", port)
				.put("db", db).put("password", password).put("timeout", timeout);
		return execute(CommandNotSupported.ALL, ProtocolCommand.MIGRATE, args);
	}

	@Override
	public Status migrate(final byte[] key, final String host, final int port, final int db, final byte[] password,
						  final int timeout){
		final CommandArguments args = CommandArguments.create("key", key).put("host", host).put("port", port)
				.put("db", db).put("password", password).put("timeout", timeout);
		return execute(CommandNotSupported.ALL, ProtocolCommand.MIGRATE, args);
	}

	@Override
	public Status migrate(final String key, final String host, final int port, final int db, final String password,
						  final int timeout, final MigrateOperation operation){
		final CommandArguments args = CommandArguments.create("key", key).put("host", host).put("port", port)
				.put("db", db).put("password", password).put("timeout", timeout).put("operation", operation);
		return execute(CommandNotSupported.ALL, ProtocolCommand.MIGRATE, args);
	}

	@Override
	public Status migrate(final byte[] key, final String host, final int port, final int db, final byte[] password,
						  final int timeout, final MigrateOperation operation){
		final CommandArguments args = CommandArguments.create("key", key).put("host", host).put("port", port)
				.put("db", db).put("password", password).put("timeout", timeout).put("operation", operation);
		return execute(CommandNotSupported.ALL, ProtocolCommand.MIGRATE, args);
	}

	@Override
	public Status migrate(final String key, final String host, final int port, final int db, final String user,
						  final String password, final int timeout){
		final CommandArguments args = CommandArguments.create("key", key).put("host", host).put("port", port)
				.put("db", db).put("user", user).put("password", password).put("timeout", timeout);
		return execute(CommandNotSupported.ALL, ProtocolCommand.MIGRATE, args);
	}

	@Override
	public Status migrate(final byte[] key, final String host, final int port, final int db, final byte[] user,
						  final byte[] password, final int timeout){
		final CommandArguments args = CommandArguments.create("key", key).put("host", host).put("port", port)
				.put("db", db).put("user", user).put("password", password).put("timeout", timeout);
		return execute(CommandNotSupported.ALL, ProtocolCommand.MIGRATE, args);
	}

	@Override
	public Status migrate(final String key, final String host, final int port, final int db, final String user,
						  final String password, final int timeout, final MigrateOperation operation){
		final CommandArguments args = CommandArguments.create("key", key).put("host", host).put("port", port)
				.put("db", db).put("user", user).put("password", password).put("timeout", timeout)
				.put("operation", operation);
		return execute(CommandNotSupported.ALL, ProtocolCommand.MIGRATE, args);
	}

	@Override
	public Status migrate(final byte[] key, final String host, final int port, final int db, final byte[] user,
						  final byte[] password, final int timeout, final MigrateOperation operation){
		final CommandArguments args = CommandArguments.create("key", key).put("host", host).put("port", port)
				.put("db", db).put("user", user).put("password", password).put("timeout", timeout)
				.put("operation", operation);
		return execute(CommandNotSupported.ALL, ProtocolCommand.MIGRATE, args);
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final int timeout, final String... keys){
		final CommandArguments args = CommandArguments.create("host", host).put("port", port).put("db", db)
				.put("timeout", timeout).put("keys", keys);
		return execute(CommandNotSupported.ALL, ProtocolCommand.MIGRATE, args);
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final int timeout, final byte[]... keys){
		final CommandArguments args = CommandArguments.create("host", host).put("port", port).put("db", db)
				.put("timeout", timeout).put("keys", keys);
		return execute(CommandNotSupported.ALL, ProtocolCommand.MIGRATE, args);
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final int timeout,
						  final MigrateOperation operation, final String... keys){
		final CommandArguments args = CommandArguments.create("host", host).put("port", port).put("db", db)
				.put("timeout", timeout).put("operation", operation).put("keys", keys);
		return execute(CommandNotSupported.ALL, ProtocolCommand.MIGRATE, args);
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final int timeout,
						  final MigrateOperation operation, final byte[]... keys){
		final CommandArguments args = CommandArguments.create("host", host).put("port", port).put("db", db)
				.put("timeout", timeout).put("operation", operation).put("keys", keys);
		return execute(CommandNotSupported.ALL, ProtocolCommand.MIGRATE, args);
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final String password, final int timeout,
						  final String... keys){
		final CommandArguments args = CommandArguments.create("host", host).put("port", port).put("db", db)
				.put("password", password).put("timeout", timeout).put("keys", keys);
		return execute(CommandNotSupported.ALL, ProtocolCommand.MIGRATE, args);
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final byte[] password, final int timeout,
						  final byte[]... keys){
		final CommandArguments args = CommandArguments.create("host", host).put("port", port).put("db", db)
				.put("password", password).put("timeout", timeout).put("keys", keys);
		return execute(CommandNotSupported.ALL, ProtocolCommand.MIGRATE, args);
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final String password, final int timeout,
						  final MigrateOperation operation, final String... keys){
		final CommandArguments args = CommandArguments.create("host", host).put("port", port).put("db", db)
				.put("password", password).put("timeout", timeout).put("operation", operation).put("keys", keys);
		return execute(CommandNotSupported.ALL, ProtocolCommand.MIGRATE, args);
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final byte[] password, final int timeout,
						  final MigrateOperation operation, final byte[]... keys){
		final CommandArguments args = CommandArguments.create("host", host).put("port", port).put("db", db)
				.put("password", password).put("timeout", timeout).put("operation", operation).put("keys", keys);
		return execute(CommandNotSupported.ALL, ProtocolCommand.MIGRATE, args);
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final String user, final String password,
						  final int timeout, final String... keys){
		final CommandArguments args = CommandArguments.create("host", host).put("port", port).put("db", db)
				.put("user", user).put("password", password).put("timeout", timeout).put("keys", keys);
		return execute(CommandNotSupported.ALL, ProtocolCommand.MIGRATE, args);
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final byte[] user, final byte[] password,
						  final int timeout, final byte[]... keys){
		final CommandArguments args = CommandArguments.create("host", host).put("port", port).put("db", db)
				.put("user", user).put("password", password).put("timeout", timeout).put("keys", keys);
		return execute(CommandNotSupported.ALL, ProtocolCommand.MIGRATE, args);
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final String user, final String password,
						  final int timeout, final MigrateOperation operation, final String... keys){
		final CommandArguments args = CommandArguments.create("host", host).put("port", port).put("db", db)
				.put("user", user).put("password", password).put("timeout", timeout).put("operation", operation)
				.put("keys", keys);
		return execute(CommandNotSupported.ALL, ProtocolCommand.MIGRATE, args);
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final byte[] user, final byte[] password,
						  final int timeout, final MigrateOperation operation, final byte[]... keys){
		final CommandArguments args = CommandArguments.create("host", host).put("port", port).put("db", db)
				.put("user", user).put("password", password).put("timeout", timeout).put("operation", operation)
				.put("keys", keys);
		return execute(CommandNotSupported.ALL, ProtocolCommand.MIGRATE, args);
	}

	@Override
	public Set<String> keys(final String pattern){
		final CommandArguments args = CommandArguments.create("pattern", pattern);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().keys(pattern)), ProtocolCommand.KEYS, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().keys(pattern)), ProtocolCommand.KEYS,
					args);
		}else{
			return execute((cmd)->cmd.keys(pattern), ProtocolCommand.KEYS, args);
		}
	}

	@Override
	public Set<byte[]> keys(final byte[] pattern){
		final CommandArguments args = CommandArguments.create("pattern", pattern);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().keys(pattern)), ProtocolCommand.KEYS, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().keys(pattern)), ProtocolCommand.KEYS,
					args);
		}else{
			return execute((cmd)->cmd.keys(pattern), ProtocolCommand.KEYS, args);
		}
	}

	@Override
	public Status persist(final byte[] key){
		final PredicateStatusConverter<Long> converter = new PredicateStatusConverter<>((val)->val > 1);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().persist(key), converter));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().persist(key), converter));
		}else{
			return execute((cmd)->cmd.persist(key), converter);
		}
	}

	@Override
	public Status pExpire(final byte[] key, final int lifetime){
		final PredicateStatusConverter<Long> converter = new PredicateStatusConverter<>((val)->val == 1);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().pexpire(key, lifetime), converter));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().pexpire(key, lifetime), converter));
		}else{
			return execute((cmd)->cmd.pexpire(key, lifetime), converter);
		}
	}

	@Override
	public Status pExpireAt(final byte[] key, final long unixTimestamp){
		final PredicateStatusConverter<Long> converter = new PredicateStatusConverter<>((val)->val == 1);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().pexpireAt(key, unixTimestamp), converter));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().pexpireAt(key, unixTimestamp),
					converter));
		}else{
			return execute((cmd)->cmd.pexpireAt(key, unixTimestamp), converter);
		}
	}

	@Override
	public Long pTtl(final byte[] key){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().pttl(key)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().pttl(key)));
		}else{
			return execute((cmd)->cmd.pttl(key));
		}
	}

	@Override
	public String randomKey(){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().randomKey()));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().randomKey()));
		}else{
			return execute((cmd)->cmd.randomKey());
		}
	}

	@Override
	public Status rename(final String key, final String newKey){
		final OkStatusConverter converter = new OkStatusConverter();

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().rename(key, newKey), converter));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().rename(key, newKey), converter));
		}else{
			return execute((cmd)->cmd.rename(key, newKey), converter);
		}
	}

	@Override
	public Status rename(final byte[] key, final byte[] newKey){
		final OkStatusConverter converter = new OkStatusConverter();

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().rename(key, newKey), converter));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().rename(key, newKey), converter));
		}else{
			return execute((cmd)->cmd.rename(key, newKey), converter);
		}
	}

	@Override
	public Status renameNx(final String key, final String newKey){
		final PredicateStatusConverter<Long> converter = new PredicateStatusConverter<>((val)->val == 1);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().renamenx(key, newKey), null));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().renamenx(key, newKey), converter));
		}else{
			return execute((cmd)->cmd.renamenx(key, newKey), converter);
		}
	}

	@Override
	public Status renameNx(final byte[] key, final byte[] newKey){
		final PredicateStatusConverter<Long> converter = new PredicateStatusConverter<>((val)->val == 1);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().renamenx(key, newKey), converter));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().renamenx(key, newKey), converter));
		}else{
			return execute((cmd)->cmd.renamenx(key, newKey), converter);
		}
	}

	@Override
	public Status restore(final byte[] key, final byte[] serializedValue, final int ttl){
		final OkStatusConverter converter = new OkStatusConverter();

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().restore(key, ttl, serializedValue), converter));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().restore(key, ttl, serializedValue),
					converter));
		}else{
			return execute((cmd)->cmd.restore(key, ttl, serializedValue), converter);
		}
	}

	@Override
	public ScanResult<List<String>> scan(final long cursor){
		return scan(Long.toString(cursor));
	}

	@Override
	public ScanResult<List<String>> scan(final String cursor){
		RedisExceptionUtils.commandNotSupportedException(ProtocolCommand.SCAN,
				CommandNotSupported.PIPELINE | CommandNotSupported.TRANSACTION, client.getConnection());
		return execute(
				(cmd)->new ScanResultConverter.ListScanResultExposeConverter<String>().convert(cmd.scan(cursor)));
	}

	@Override
	public ScanResult<List<byte[]>> scan(final byte[] cursor){
		RedisExceptionUtils.commandNotSupportedException(ProtocolCommand.SCAN,
				CommandNotSupported.PIPELINE | CommandNotSupported.TRANSACTION, client.getConnection());
		return execute(
				(cmd)->new ScanResultConverter.ListScanResultExposeConverter<byte[]>().convert(cmd.scan(cursor)));
	}

	@Override
	public ScanResult<List<String>> scan(final long cursor, final String pattern){
		return scan(Long.toString(cursor), pattern);
	}

	@Override
	public ScanResult<List<byte[]>> scan(final long cursor, final byte[] pattern){
		return scan(NumberUtils.long2bytes(cursor), pattern);
	}

	@Override
	public ScanResult<List<String>> scan(final String cursor, final String pattern){
		RedisExceptionUtils.commandNotSupportedException(ProtocolCommand.SCAN,
				CommandNotSupported.PIPELINE | CommandNotSupported.TRANSACTION, client.getConnection());
		return execute((cmd)->new ScanResultConverter.ListScanResultExposeConverter<String>().convert(cmd.scan(cursor,
				new JedisScanParams(pattern))));
	}

	@Override
	public ScanResult<List<byte[]>> scan(final byte[] cursor, final byte[] pattern){
		RedisExceptionUtils.commandNotSupportedException(ProtocolCommand.SCAN,
				CommandNotSupported.PIPELINE | CommandNotSupported.TRANSACTION, client.getConnection());
		return execute((cmd)->new ScanResultConverter.ListScanResultExposeConverter<byte[]>().convert(cmd.scan(cursor,
				new JedisScanParams(pattern))));
	}

	@Override
	public ScanResult<List<String>> scan(final long cursor, final int count){
		return scan(Long.toString(cursor), count);
	}

	@Override
	public ScanResult<List<String>> scan(final String cursor, final int count){
		RedisExceptionUtils.commandNotSupportedException(ProtocolCommand.SCAN,
				CommandNotSupported.PIPELINE | CommandNotSupported.TRANSACTION, client.getConnection());
		return execute((cmd)->new ScanResultConverter.ListScanResultExposeConverter<String>().convert(cmd.scan(cursor,
				new JedisScanParams(count))));
	}

	@Override
	public ScanResult<List<byte[]>> scan(final byte[] cursor, final int count){
		RedisExceptionUtils.commandNotSupportedException(ProtocolCommand.SCAN,
				CommandNotSupported.PIPELINE | CommandNotSupported.TRANSACTION, client.getConnection());
		return execute((cmd)->new ScanResultConverter.ListScanResultExposeConverter<byte[]>().convert(cmd.scan(cursor,
				new JedisScanParams(count))));
	}

	@Override
	public ScanResult<List<String>> scan(final long cursor, final String pattern, final int count){
		return scan(Long.toString(cursor), pattern, count);
	}

	@Override
	public ScanResult<List<byte[]>> scan(final long cursor, final byte[] pattern, final int count){
		return scan(NumberUtils.long2bytes(cursor), pattern, count);
	}

	@Override
	public ScanResult<List<String>> scan(final String cursor, final String pattern, final int count){
		RedisExceptionUtils.commandNotSupportedException(ProtocolCommand.SCAN,
				CommandNotSupported.PIPELINE | CommandNotSupported.TRANSACTION, client.getConnection());
		return execute((cmd)->new ScanResultConverter.ListScanResultExposeConverter<String>().convert(cmd.scan(cursor,
				new JedisScanParams(pattern, count))));
	}

	@Override
	public ScanResult<List<byte[]>> scan(final byte[] cursor, final byte[] pattern, final int count){
		RedisExceptionUtils.commandNotSupportedException(ProtocolCommand.SCAN,
				CommandNotSupported.PIPELINE | CommandNotSupported.TRANSACTION, client.getConnection());
		return execute((cmd)->new ScanResultConverter.ListScanResultExposeConverter<byte[]>().convert(cmd.scan(cursor,
				new JedisScanParams(pattern, count))));
	}

	@Override
	public List<byte[]> sort(final byte[] key){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().sort(key)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().sort(key)));
		}else{
			return execute((cmd)->cmd.sort(key));
		}
	}

	@Override
	public List<byte[]> sort(final byte[] key, final SortArgument sortArgument){
		final SortingParams soringParams = new SortArgumentConverter.SortArgumentJedisConverter().convert(sortArgument);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().sort(key, soringParams)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().sort(key, soringParams)));
		}else{
			return execute((cmd)->cmd.sort(key, soringParams));
		}
	}

	@Override
	public Long sort(final String key, final String destKey){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().sort(key, destKey)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().sort(key, destKey)));
		}else{
			return execute((cmd)->cmd.sort(key, destKey));
		}
	}

	@Override
	public Long sort(final byte[] key, final byte[] destKey){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().sort(key, destKey)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().sort(key, destKey)));
		}else{
			return execute((cmd)->cmd.sort(key, destKey));
		}
	}

	@Override
	public Long sort(final String key, final String destKey, final SortArgument sortArgument){
		final SortingParams soringParams = new SortArgumentConverter.SortArgumentJedisConverter().convert(sortArgument);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().sort(key, soringParams, destKey)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().sort(key, soringParams, destKey)));
		}else{
			return execute((cmd)->cmd.sort(key, soringParams, destKey));
		}
	}

	@Override
	public Long sort(final byte[] key, final byte[] destKey, final SortArgument sortArgument){
		final SortingParams soringParams = new SortArgumentConverter.SortArgumentJedisConverter().convert(sortArgument);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().sort(key, soringParams, destKey)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().sort(key, soringParams, destKey)));
		}else{
			return execute((cmd)->cmd.sort(key, soringParams, destKey));
		}
	}

	@Override
	public Long touch(final String... keys){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().touch(keys[0])));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().touch(keys)));
		}else{
			return execute((cmd)->cmd.touch(keys));
		}
	}

	@Override
	public Long touch(final byte[]... keys){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().touch(keys[0])));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().touch(keys)));
		}else{
			return execute((cmd)->cmd.touch(keys));
		}
	}

	@Override
	public Long ttl(final byte[] key){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().ttl(key)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().ttl(key)));
		}else{
			return execute((cmd)->cmd.ttl(key));
		}
	}

	@Override
	public Type type(final byte[] key){
		final EnumConverter<Type> converter = new EnumConverter<>(Type.class);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().type(key), converter));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().type(key), converter));
		}else{
			return execute((cmd)->cmd.type(key), converter);
		}
	}

	@Override
	public Long unlink(final String... keys){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().unlink(keys[0])));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().unlink(keys)));
		}else{
			return execute((cmd)->cmd.unlink(keys));
		}
	}

	@Override
	public Long unlink(final byte[]... keys){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().unlink(keys[0])));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().unlink(keys)));
		}else{
			return execute((cmd)->cmd.unlink(keys));
		}
	}

	@Override
	public Long wait(final int replicas, final long timeout){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().waitReplicas(replicas, timeout)));
		}else if(isTransaction()){
			RedisExceptionUtils.commandNotSupportedException(ProtocolCommand.SCAN,
					CommandNotSupported.TRANSACTION, client.getConnection());
			return null;
		}else{
			return execute((cmd)->cmd.waitReplicas(replicas, timeout));
		}
	}

}
