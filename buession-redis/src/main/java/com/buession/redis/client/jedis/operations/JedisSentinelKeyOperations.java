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

import com.buession.core.converter.PredicateStatusConverter;
import com.buession.lang.Status;
import com.buession.redis.client.jedis.JedisSentinelClient;
import com.buession.redis.core.ExpireOption;
import com.buession.redis.core.MigrateOperation;
import com.buession.redis.core.ObjectEncoding;
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.Type;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.CommandNotSupported;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.internal.convert.Converters;
import com.buession.redis.core.internal.jedis.JedisScanParams;
import com.buession.redis.utils.SafeEncoder;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.SortingParams;
import redis.clients.jedis.params.MigrateParams;
import redis.clients.jedis.params.RestoreParams;

import java.util.List;
import java.util.Set;

/**
 * Jedis 哨兵模式 Key 命令操作
 *
 * @author Yong.Teng
 */
public final class JedisSentinelKeyOperations extends AbstractKeyOperations<Jedis> {

	public JedisSentinelKeyOperations(final JedisSentinelClient client){
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
	public Status pExpire(final String key, final int lifetime){
		final CommandArguments args = CommandArguments.create("key", key).put("lifetime", lifetime);
		final PredicateStatusConverter<Long> converter = new PredicateStatusConverter<>((val)->val == 1);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().pexpire(key, lifetime), converter),
					ProtocolCommand.PEXPIRE, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().pexpire(key, lifetime), converter),
					ProtocolCommand.PEXPIRE, args);
		}else{
			return execute((cmd)->cmd.pexpire(key, lifetime), converter, ProtocolCommand.PEXPIRE, args);
		}
	}

	@Override
	public Status pExpire(final byte[] key, final int lifetime){
		final CommandArguments args = CommandArguments.create("key", key).put("lifetime", lifetime);
		final PredicateStatusConverter<Long> converter = new PredicateStatusConverter<>((val)->val == 1);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().pexpire(key, lifetime), converter),
					ProtocolCommand.PEXPIRE, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().pexpire(key, lifetime), converter),
					ProtocolCommand.PEXPIRE, args);
		}else{
			return execute((cmd)->cmd.pexpire(key, lifetime), converter, ProtocolCommand.PEXPIRE, args);
		}
	}

	@Override
	public Status pExpireAt(final String key, final long unixTimestamp){
		final CommandArguments args = CommandArguments.create("key", key).put("unixTimestamp", unixTimestamp);
		final PredicateStatusConverter<Long> converter = new PredicateStatusConverter<>((val)->val == 1);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().pexpireAt(key, unixTimestamp), converter),
					ProtocolCommand.PEXPIREAT, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().pexpireAt(key, unixTimestamp),
					converter), ProtocolCommand.PEXPIREAT, args);
		}else{
			return execute((cmd)->cmd.pexpireAt(key, unixTimestamp), converter, ProtocolCommand.PEXPIREAT, args);
		}
	}

	@Override
	public Status pExpireAt(final byte[] key, final long unixTimestamp){
		final CommandArguments args = CommandArguments.create("key", key).put("unixTimestamp", unixTimestamp);
		final PredicateStatusConverter<Long> converter = new PredicateStatusConverter<>((val)->val == 1);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().pexpireAt(key, unixTimestamp), converter),
					ProtocolCommand.PEXPIREAT, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().pexpireAt(key, unixTimestamp),
					converter), ProtocolCommand.PEXPIREAT, args);
		}else{
			return execute((cmd)->cmd.pexpireAt(key, unixTimestamp), converter, ProtocolCommand.PEXPIREAT, args);
		}
	}

	@Override
	public Status persist(final String key){
		final CommandArguments args = CommandArguments.create("key", key);
		final PredicateStatusConverter<Long> converter = new PredicateStatusConverter<>((val)->val > 0);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().persist(key), converter),
					ProtocolCommand.PERSIST, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().persist(key), converter),
					ProtocolCommand.PERSIST, args);
		}else{
			return execute((cmd)->cmd.persist(key), converter, ProtocolCommand.PERSIST, args);
		}
	}

	@Override
	public Status persist(final byte[] key){
		final CommandArguments args = CommandArguments.create("key", key);
		final PredicateStatusConverter<Long> converter = new PredicateStatusConverter<>((val)->val > 0);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().persist(key), converter),
					ProtocolCommand.PERSIST, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().persist(key), converter),
					ProtocolCommand.PERSIST, args);
		}else{
			return execute((cmd)->cmd.persist(key), converter, ProtocolCommand.PERSIST, args);
		}
	}

	@Override
	public Long ttl(final String key){
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().ttl(key)), ProtocolCommand.TTL, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().ttl(key)), ProtocolCommand.TTL, args);
		}else{
			return execute((cmd)->cmd.ttl(key), ProtocolCommand.TTL, args);
		}
	}

	@Override
	public Long ttl(final byte[] key){
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().ttl(key)), ProtocolCommand.TTL, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().ttl(key)), ProtocolCommand.TTL, args);
		}else{
			return execute((cmd)->cmd.ttl(key), ProtocolCommand.TTL, args);
		}
	}

	@Override
	public Long pTtl(final String key){
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().pttl(key)), ProtocolCommand.PTTL, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().pttl(key)), ProtocolCommand.PTTL, args);
		}else{
			return execute((cmd)->cmd.pttl(key), ProtocolCommand.PTTL, args);
		}
	}

	@Override
	public Long pTtl(final byte[] key){
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().pttl(key)), ProtocolCommand.PTTL, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().pttl(key)), ProtocolCommand.PTTL, args);
		}else{
			return execute((cmd)->cmd.pttl(key), ProtocolCommand.PTTL, args);
		}
	}

	@Override
	public Status copy(final String key, final String destKey){
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().copy(key, destKey, cmd.getDB(), true),
					Converters.BOOLEAN_STATUS_CONVERTER), ProtocolCommand.COPY, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().copy(key, destKey, cmd.getDB(), true),
					Converters.BOOLEAN_STATUS_CONVERTER), ProtocolCommand.COPY, args);
		}else{
			return execute((cmd)->cmd.copy(key, destKey, cmd.getDB(), true), Converters.BOOLEAN_STATUS_CONVERTER,
					ProtocolCommand.COPY, args);
		}
	}

	@Override
	public Status copy(final byte[] key, final byte[] destKey){
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().copy(key, destKey, cmd.getDB(), true),
					Converters.BOOLEAN_STATUS_CONVERTER), ProtocolCommand.COPY, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().copy(key, destKey, cmd.getDB(), true),
					Converters.BOOLEAN_STATUS_CONVERTER), ProtocolCommand.COPY, args);
		}else{
			return execute((cmd)->cmd.copy(key, destKey, cmd.getDB(), true), Converters.BOOLEAN_STATUS_CONVERTER,
					ProtocolCommand.COPY, args);
		}
	}

	@Override
	public Status copy(final String key, final String destKey, final int db){
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey)
				.put("db", db);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().copy(key, destKey, db, true),
					Converters.BOOLEAN_STATUS_CONVERTER), ProtocolCommand.COPY, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().copy(key, destKey, db, true),
					Converters.BOOLEAN_STATUS_CONVERTER), ProtocolCommand.COPY, args);
		}else{
			return execute((cmd)->cmd.copy(key, destKey, db, true), Converters.BOOLEAN_STATUS_CONVERTER,
					ProtocolCommand.COPY, args);
		}
	}

	@Override
	public Status copy(final byte[] key, final byte[] destKey, final int db){
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey)
				.put("db", db);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().copy(key, destKey, db, true),
					Converters.BOOLEAN_STATUS_CONVERTER), ProtocolCommand.COPY, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().copy(key, destKey, db, true),
					Converters.BOOLEAN_STATUS_CONVERTER), ProtocolCommand.COPY, args);
		}else{
			return execute((cmd)->cmd.copy(key, destKey, db, true), Converters.BOOLEAN_STATUS_CONVERTER,
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
			return pipelineExecute((cmd)->newJedisResult(getPipeline().copy(key, destKey, db, replace),
					Converters.BOOLEAN_STATUS_CONVERTER), ProtocolCommand.COPY, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().copy(key, destKey, db, replace),
					Converters.BOOLEAN_STATUS_CONVERTER), ProtocolCommand.COPY, args);
		}else{
			return execute((cmd)->cmd.copy(key, destKey, db, replace), Converters.BOOLEAN_STATUS_CONVERTER,
					ProtocolCommand.COPY, args);
		}
	}

	@Override
	public Status copy(final byte[] key, final byte[] destKey, final int db, final boolean replace){
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey).put("db", db)
				.put("replace", replace);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().copy(key, destKey, db, replace),
					Converters.BOOLEAN_STATUS_CONVERTER), ProtocolCommand.COPY, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().copy(key, destKey, db, replace),
					Converters.BOOLEAN_STATUS_CONVERTER), ProtocolCommand.COPY, args);
		}else{
			return execute((cmd)->cmd.copy(key, destKey, db, replace), Converters.BOOLEAN_STATUS_CONVERTER,
					ProtocolCommand.COPY, args);
		}
	}

	@Override
	public Status move(final String key, final int db){
		final CommandArguments args = CommandArguments.create("key", key).put("db", db);
		final PredicateStatusConverter<Long> converter = new PredicateStatusConverter<>((val)->val > 0);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().move(key, db), converter), ProtocolCommand.MOVE,
					args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().move(key, db), converter),
					ProtocolCommand.MOVE, args);
		}else{
			return execute((cmd)->cmd.move(key, db), converter, ProtocolCommand.MOVE, args);
		}
	}

	@Override
	public Status move(final byte[] key, final int db){
		final CommandArguments args = CommandArguments.create("key", key).put("db", db);
		final PredicateStatusConverter<Long> converter = new PredicateStatusConverter<>((val)->val > 0);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().move(key, db), converter), ProtocolCommand.MOVE,
					args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().move(key, db), converter),
					ProtocolCommand.MOVE, args);
		}else{
			return execute((cmd)->cmd.move(key, db), converter, ProtocolCommand.MOVE, args);
		}
	}

	@Override
	public Status migrate(final String key, final String host, final int port, final int db, final int timeout){
		final CommandArguments args = CommandArguments.create("key", key).put("host", host).put("port", port)
				.put("db", db).put("timeout", timeout);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().migrate(host, port, key, db, timeout),
					Converters.OK_STATUS_CONVERTER), ProtocolCommand.MIGRATE, args);
		}else if(isTransaction()){
			return transactionExecute(
					(cmd)->newJedisResult(getTransaction().migrate(host, port, key, db, timeout),
							Converters.OK_STATUS_CONVERTER), ProtocolCommand.MIGRATE, args);
		}else{
			return execute((cmd)->cmd.migrate(host, port, key, db, timeout), Converters.OK_STATUS_CONVERTER,
					ProtocolCommand.MIGRATE, args);
		}
	}

	@Override
	public Status migrate(final byte[] key, final String host, final int port, final int db, final int timeout){
		final CommandArguments args = CommandArguments.create("key", key).put("host", host).put("port", port)
				.put("db", db).put("timeout", timeout);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().migrate(host, port, key, db, timeout),
					Converters.OK_STATUS_CONVERTER), ProtocolCommand.MIGRATE, args);
		}else if(isTransaction()){
			return transactionExecute(
					(cmd)->newJedisResult(getTransaction().migrate(host, port, key, db, timeout),
							Converters.OK_STATUS_CONVERTER), ProtocolCommand.MIGRATE, args);
		}else{
			return execute((cmd)->cmd.migrate(host, port, key, db, timeout), Converters.OK_STATUS_CONVERTER,
					ProtocolCommand.MIGRATE, args);
		}
	}

	@Override
	public Status migrate(final String key, final String host, final int port, final int db, final int timeout,
						  final MigrateOperation operation){
		final CommandArguments args = CommandArguments.create("key", key).put("host", host).put("port", port)
				.put("db", db).put("timeout", timeout).put("operation", operation);
		final MigrateParams migrateParams = MIGRATE_OPERATION_JEDIS_CONVERTER.convert(operation);

		if(isPipeline()){
			return pipelineExecute(
					(cmd)->newJedisResult(getPipeline().migrate(host, port, db, timeout, migrateParams, key),
							Converters.OK_STATUS_CONVERTER), ProtocolCommand.MIGRATE, args);
		}else if(isTransaction()){
			return transactionExecute(
					(cmd)->newJedisResult(getTransaction().migrate(host, port, db, timeout, migrateParams, key),
							Converters.OK_STATUS_CONVERTER), ProtocolCommand.MIGRATE, args);
		}else{
			return execute((cmd)->cmd.migrate(host, port, db, timeout, migrateParams, key),
					Converters.OK_STATUS_CONVERTER, ProtocolCommand.MIGRATE, args);
		}
	}

	@Override
	public Status migrate(final byte[] key, final String host, final int port, final int db, final int timeout,
						  final MigrateOperation operation){
		final CommandArguments args = CommandArguments.create("key", key).put("host", host).put("port", port)
				.put("db", db).put("timeout", timeout).put("operation", operation);
		final MigrateParams migrateParams = MIGRATE_OPERATION_JEDIS_CONVERTER.convert(operation);

		if(isPipeline()){
			return pipelineExecute(
					(cmd)->newJedisResult(getPipeline().migrate(host, port, db, timeout, migrateParams, key),
							Converters.OK_STATUS_CONVERTER), ProtocolCommand.MIGRATE, args);
		}else if(isTransaction()){
			return transactionExecute(
					(cmd)->newJedisResult(getTransaction().migrate(host, port, db, timeout, migrateParams, key),
							Converters.OK_STATUS_CONVERTER), ProtocolCommand.MIGRATE, args);
		}else{
			return execute((cmd)->cmd.migrate(host, port, db, timeout, migrateParams, key),
					Converters.OK_STATUS_CONVERTER,
					ProtocolCommand.MIGRATE, args);
		}
	}

	@Override
	public Status migrate(final String key, final String host, final int port, final int db, final String password,
						  final int timeout){
		final CommandArguments args = CommandArguments.create("key", key).put("host", host).put("port", port)
				.put("db", db).put("password", password).put("timeout", timeout);
		final MigrateParams migrateParams = MIGRATE_OPERATION_JEDIS_CONVERTER.convert(null).auth(password);

		if(isPipeline()){
			return pipelineExecute(
					(cmd)->newJedisResult(getPipeline().migrate(host, port, db, timeout, migrateParams, key),
							Converters.OK_STATUS_CONVERTER), ProtocolCommand.MIGRATE, args);
		}else if(isTransaction()){
			return transactionExecute(
					(cmd)->newJedisResult(getTransaction().migrate(host, port, db, timeout, migrateParams, key),
							Converters.OK_STATUS_CONVERTER), ProtocolCommand.MIGRATE, args);
		}else{
			return execute((cmd)->cmd.migrate(host, port, db, timeout, migrateParams, key),
					Converters.OK_STATUS_CONVERTER, ProtocolCommand.MIGRATE, args);
		}
	}

	@Override
	public Status migrate(final byte[] key, final String host, final int port, final int db, final byte[] password,
						  final int timeout){
		final CommandArguments args = CommandArguments.create("key", key).put("host", host).put("port", port)
				.put("db", db).put("password", password).put("timeout", timeout);
		final MigrateParams migrateParams = MIGRATE_OPERATION_JEDIS_CONVERTER.convert(null)
				.auth(SafeEncoder.encode(password));

		if(isPipeline()){
			return pipelineExecute(
					(cmd)->newJedisResult(getPipeline().migrate(host, port, db, timeout, migrateParams, key),
							Converters.OK_STATUS_CONVERTER), ProtocolCommand.MIGRATE, args);
		}else if(isTransaction()){
			return transactionExecute(
					(cmd)->newJedisResult(getTransaction().migrate(host, port, db, timeout, migrateParams, key),
							Converters.OK_STATUS_CONVERTER), ProtocolCommand.MIGRATE, args);
		}else{
			return execute((cmd)->cmd.migrate(host, port, db, timeout, migrateParams, key),
					Converters.OK_STATUS_CONVERTER, ProtocolCommand.MIGRATE, args);
		}
	}

	@Override
	public Status migrate(final String key, final String host, final int port, final int db, final String password,
						  final int timeout, final MigrateOperation operation){
		final CommandArguments args = CommandArguments.create("key", key).put("host", host).put("port", port)
				.put("db", db).put("password", password).put("timeout", timeout).put("operation", operation);
		final MigrateParams migrateParams = MIGRATE_OPERATION_JEDIS_CONVERTER.convert(operation).auth(password);

		if(isPipeline()){
			return pipelineExecute(
					(cmd)->newJedisResult(getPipeline().migrate(host, port, db, timeout, migrateParams, key),
							Converters.OK_STATUS_CONVERTER), ProtocolCommand.MIGRATE, args);
		}else if(isTransaction()){
			return transactionExecute(
					(cmd)->newJedisResult(getTransaction().migrate(host, port, db, timeout, migrateParams, key),
							Converters.OK_STATUS_CONVERTER), ProtocolCommand.MIGRATE, args);
		}else{
			return execute((cmd)->cmd.migrate(host, port, db, timeout, migrateParams, key),
					Converters.OK_STATUS_CONVERTER, ProtocolCommand.MIGRATE, args);
		}
	}

	@Override
	public Status migrate(final byte[] key, final String host, final int port, final int db, final byte[] password,
						  final int timeout, final MigrateOperation operation){
		final CommandArguments args = CommandArguments.create("key", key).put("host", host).put("port", port)
				.put("db", db).put("password", password).put("timeout", timeout).put("operation", operation);
		final MigrateParams migrateParams = MIGRATE_OPERATION_JEDIS_CONVERTER.convert(operation)
				.auth(SafeEncoder.encode(password));

		if(isPipeline()){
			return pipelineExecute(
					(cmd)->newJedisResult(getPipeline().migrate(host, port, db, timeout, migrateParams, key),
							Converters.OK_STATUS_CONVERTER), ProtocolCommand.MIGRATE, args);
		}else if(isTransaction()){
			return transactionExecute(
					(cmd)->newJedisResult(getTransaction().migrate(host, port, db, timeout, migrateParams, key),
							Converters.OK_STATUS_CONVERTER), ProtocolCommand.MIGRATE, args);
		}else{
			return execute((cmd)->cmd.migrate(host, port, db, timeout, migrateParams, key),
					Converters.OK_STATUS_CONVERTER, ProtocolCommand.MIGRATE, args);
		}
	}

	@Override
	public Status migrate(final String key, final String host, final int port, final int db, final String user,
						  final String password, final int timeout){
		final CommandArguments args = CommandArguments.create("key", key).put("host", host).put("port", port)
				.put("db", db).put("user", user).put("password", password).put("timeout", timeout);
		final MigrateParams migrateParams = MIGRATE_OPERATION_JEDIS_CONVERTER.convert(null).auth2(user, password);

		if(isPipeline()){
			return pipelineExecute(
					(cmd)->newJedisResult(getPipeline().migrate(host, port, db, timeout, migrateParams, key),
							Converters.OK_STATUS_CONVERTER), ProtocolCommand.MIGRATE, args);
		}else if(isTransaction()){
			return transactionExecute(
					(cmd)->newJedisResult(getTransaction().migrate(host, port, db, timeout, migrateParams, key),
							Converters.OK_STATUS_CONVERTER), ProtocolCommand.MIGRATE, args);
		}else{
			return execute((cmd)->cmd.migrate(host, port, db, timeout, migrateParams, key),
					Converters.OK_STATUS_CONVERTER, ProtocolCommand.MIGRATE, args);
		}
	}

	@Override
	public Status migrate(final byte[] key, final String host, final int port, final int db, final byte[] user,
						  final byte[] password, final int timeout){
		final CommandArguments args = CommandArguments.create("key", key).put("host", host).put("port", port)
				.put("db", db).put("user", user).put("password", password).put("timeout", timeout);
		final MigrateParams migrateParams = MIGRATE_OPERATION_JEDIS_CONVERTER.convert(null)
				.auth2(SafeEncoder.encode(user), SafeEncoder.encode(password));

		if(isPipeline()){
			return pipelineExecute(
					(cmd)->newJedisResult(getPipeline().migrate(host, port, db, timeout, migrateParams, key),
							Converters.OK_STATUS_CONVERTER), ProtocolCommand.MIGRATE, args);
		}else if(isTransaction()){
			return transactionExecute(
					(cmd)->newJedisResult(getTransaction().migrate(host, port, db, timeout, migrateParams, key),
							Converters.OK_STATUS_CONVERTER), ProtocolCommand.MIGRATE, args);
		}else{
			return execute((cmd)->cmd.migrate(host, port, db, timeout, migrateParams, key),
					Converters.OK_STATUS_CONVERTER, ProtocolCommand.MIGRATE, args);
		}
	}

	@Override
	public Status migrate(final String key, final String host, final int port, final int db, final String user,
						  final String password, final int timeout, final MigrateOperation operation){
		final CommandArguments args = CommandArguments.create("key", key).put("host", host).put("port", port)
				.put("db", db).put("user", user).put("password", password).put("timeout", timeout)
				.put("operation", operation);
		final MigrateParams migrateParams = MIGRATE_OPERATION_JEDIS_CONVERTER.convert(operation).auth2(user, password);

		if(isPipeline()){
			return pipelineExecute(
					(cmd)->newJedisResult(getPipeline().migrate(host, port, db, timeout, migrateParams, key),
							Converters.OK_STATUS_CONVERTER), ProtocolCommand.MIGRATE, args);
		}else if(isTransaction()){
			return transactionExecute(
					(cmd)->newJedisResult(getTransaction().migrate(host, port, db, timeout, migrateParams, key),
							Converters.OK_STATUS_CONVERTER), ProtocolCommand.MIGRATE, args);
		}else{
			return execute((cmd)->cmd.migrate(host, port, db, timeout, migrateParams, key),
					Converters.OK_STATUS_CONVERTER, ProtocolCommand.MIGRATE, args);
		}
	}

	@Override
	public Status migrate(final byte[] key, final String host, final int port, final int db, final byte[] user,
						  final byte[] password, final int timeout, final MigrateOperation operation){
		final CommandArguments args = CommandArguments.create("key", key).put("host", host).put("port", port)
				.put("db", db).put("user", user).put("password", password).put("timeout", timeout)
				.put("operation", operation);
		final MigrateParams migrateParams = MIGRATE_OPERATION_JEDIS_CONVERTER.convert(operation)
				.auth2(SafeEncoder.encode(user), SafeEncoder.encode(password));

		if(isPipeline()){
			return pipelineExecute(
					(cmd)->newJedisResult(getPipeline().migrate(host, port, db, timeout, migrateParams, key),
							Converters.OK_STATUS_CONVERTER), ProtocolCommand.MIGRATE, args);
		}else if(isTransaction()){
			return transactionExecute(
					(cmd)->newJedisResult(getTransaction().migrate(host, port, db, timeout, migrateParams, key),
							Converters.OK_STATUS_CONVERTER), ProtocolCommand.MIGRATE, args);
		}else{
			return execute((cmd)->cmd.migrate(host, port, db, timeout, migrateParams, key),
					Converters.OK_STATUS_CONVERTER, ProtocolCommand.MIGRATE, args);
		}
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final int timeout, final String... keys){
		final CommandArguments args = CommandArguments.create("host", host).put("port", port).put("db", db)
				.put("timeout", timeout).put("keys", keys);
		final MigrateParams migrateParams = MIGRATE_OPERATION_JEDIS_CONVERTER.convert(null);

		if(isPipeline()){
			return pipelineExecute(
					(cmd)->newJedisResult(getPipeline().migrate(host, port, db, timeout, migrateParams, keys),
							Converters.OK_STATUS_CONVERTER), ProtocolCommand.MIGRATE, args);
		}else if(isTransaction()){
			return transactionExecute(
					(cmd)->newJedisResult(getTransaction().migrate(host, port, db, timeout, migrateParams, keys),
							Converters.OK_STATUS_CONVERTER), ProtocolCommand.MIGRATE, args);
		}else{
			return execute((cmd)->cmd.migrate(host, port, db, timeout, migrateParams, keys),
					Converters.OK_STATUS_CONVERTER, ProtocolCommand.MIGRATE, args);
		}
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final int timeout, final byte[]... keys){
		final CommandArguments args = CommandArguments.create("host", host).put("port", port).put("db", db)
				.put("timeout", timeout).put("keys", keys);
		final MigrateParams migrateParams = MIGRATE_OPERATION_JEDIS_CONVERTER.convert(null);

		if(isPipeline()){
			return pipelineExecute(
					(cmd)->newJedisResult(getPipeline().migrate(host, port, db, timeout, migrateParams, keys),
							Converters.OK_STATUS_CONVERTER), ProtocolCommand.MIGRATE, args);
		}else if(isTransaction()){
			return transactionExecute(
					(cmd)->newJedisResult(getTransaction().migrate(host, port, db, timeout, migrateParams, keys),
							Converters.OK_STATUS_CONVERTER), ProtocolCommand.MIGRATE, args);
		}else{
			return execute((cmd)->cmd.migrate(host, port, db, timeout, migrateParams, keys),
					Converters.OK_STATUS_CONVERTER, ProtocolCommand.MIGRATE, args);
		}
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final int timeout,
						  final MigrateOperation operation, final String... keys){
		final CommandArguments args = CommandArguments.create("host", host).put("port", port).put("db", db)
				.put("timeout", timeout).put("operation", operation).put("keys", keys);
		final MigrateParams migrateParams = MIGRATE_OPERATION_JEDIS_CONVERTER.convert(operation);

		if(isPipeline()){
			return pipelineExecute(
					(cmd)->newJedisResult(getPipeline().migrate(host, port, db, timeout, migrateParams, keys),
							Converters.OK_STATUS_CONVERTER), ProtocolCommand.MIGRATE, args);
		}else if(isTransaction()){
			return transactionExecute(
					(cmd)->newJedisResult(getTransaction().migrate(host, port, db, timeout, migrateParams, keys),
							Converters.OK_STATUS_CONVERTER), ProtocolCommand.MIGRATE, args);
		}else{
			return execute((cmd)->cmd.migrate(host, port, db, timeout, migrateParams, keys),
					Converters.OK_STATUS_CONVERTER, ProtocolCommand.MIGRATE, args);
		}
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final int timeout,
						  final MigrateOperation operation, final byte[]... keys){
		final CommandArguments args = CommandArguments.create("host", host).put("port", port).put("db", db)
				.put("timeout", timeout).put("operation", operation).put("keys", keys);
		final MigrateParams migrateParams = MIGRATE_OPERATION_JEDIS_CONVERTER.convert(operation);

		if(isPipeline()){
			return pipelineExecute(
					(cmd)->newJedisResult(getPipeline().migrate(host, port, db, timeout, migrateParams, keys),
							Converters.OK_STATUS_CONVERTER), ProtocolCommand.MIGRATE, args);
		}else if(isTransaction()){
			return transactionExecute(
					(cmd)->newJedisResult(getTransaction().migrate(host, port, db, timeout, migrateParams, keys),
							Converters.OK_STATUS_CONVERTER), ProtocolCommand.MIGRATE, args);
		}else{
			return execute((cmd)->cmd.migrate(host, port, db, timeout, migrateParams, keys),
					Converters.OK_STATUS_CONVERTER, ProtocolCommand.MIGRATE, args);
		}
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final String password, final int timeout,
						  final String... keys){
		final CommandArguments args = CommandArguments.create("host", host).put("port", port).put("db", db)
				.put("password", password).put("timeout", timeout).put("keys", keys);
		final MigrateParams migrateParams = MIGRATE_OPERATION_JEDIS_CONVERTER.convert(null).auth(password);

		if(isPipeline()){
			return pipelineExecute(
					(cmd)->newJedisResult(getPipeline().migrate(host, port, db, timeout, migrateParams, keys),
							Converters.OK_STATUS_CONVERTER), ProtocolCommand.MIGRATE, args);
		}else if(isTransaction()){
			return transactionExecute(
					(cmd)->newJedisResult(getTransaction().migrate(host, port, db, timeout, migrateParams, keys),
							Converters.OK_STATUS_CONVERTER), ProtocolCommand.MIGRATE, args);
		}else{
			return execute((cmd)->cmd.migrate(host, port, db, timeout, migrateParams, keys),
					Converters.OK_STATUS_CONVERTER, ProtocolCommand.MIGRATE, args);
		}
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final byte[] password, final int timeout,
						  final byte[]... keys){
		final CommandArguments args = CommandArguments.create("host", host).put("port", port).put("db", db)
				.put("password", password).put("timeout", timeout).put("keys", keys);
		final MigrateParams migrateParams = MIGRATE_OPERATION_JEDIS_CONVERTER.convert(null)
				.auth(SafeEncoder.encode(password));

		if(isPipeline()){
			return pipelineExecute(
					(cmd)->newJedisResult(getPipeline().migrate(host, port, db, timeout, migrateParams, keys),
							Converters.OK_STATUS_CONVERTER), ProtocolCommand.MIGRATE, args);
		}else if(isTransaction()){
			return transactionExecute(
					(cmd)->newJedisResult(getTransaction().migrate(host, port, db, timeout, migrateParams, keys),
							Converters.OK_STATUS_CONVERTER), ProtocolCommand.MIGRATE, args);
		}else{
			return execute((cmd)->cmd.migrate(host, port, db, timeout, migrateParams, keys),
					Converters.OK_STATUS_CONVERTER, ProtocolCommand.MIGRATE, args);
		}
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final String password, final int timeout,
						  final MigrateOperation operation, final String... keys){
		final CommandArguments args = CommandArguments.create("host", host).put("port", port).put("db", db)
				.put("password", password).put("timeout", timeout).put("operation", operation).put("keys", keys);
		final MigrateParams migrateParams = MIGRATE_OPERATION_JEDIS_CONVERTER.convert(operation).auth(password);

		if(isPipeline()){
			return pipelineExecute(
					(cmd)->newJedisResult(getPipeline().migrate(host, port, db, timeout, migrateParams, keys),
							Converters.OK_STATUS_CONVERTER), ProtocolCommand.MIGRATE, args);
		}else if(isTransaction()){
			return transactionExecute(
					(cmd)->newJedisResult(getTransaction().migrate(host, port, db, timeout, migrateParams, keys),
							Converters.OK_STATUS_CONVERTER), ProtocolCommand.MIGRATE, args);
		}else{
			return execute((cmd)->cmd.migrate(host, port, db, timeout, migrateParams, keys),
					Converters.OK_STATUS_CONVERTER, ProtocolCommand.MIGRATE, args);
		}
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final byte[] password, final int timeout,
						  final MigrateOperation operation, final byte[]... keys){
		final CommandArguments args = CommandArguments.create("host", host).put("port", port).put("db", db)
				.put("password", password).put("timeout", timeout).put("operation", operation).put("keys", keys);
		final MigrateParams migrateParams = MIGRATE_OPERATION_JEDIS_CONVERTER.convert(operation)
				.auth(SafeEncoder.encode(password));

		if(isPipeline()){
			return pipelineExecute(
					(cmd)->newJedisResult(getPipeline().migrate(host, port, db, timeout, migrateParams, keys),
							Converters.OK_STATUS_CONVERTER), ProtocolCommand.MIGRATE, args);
		}else if(isTransaction()){
			return transactionExecute(
					(cmd)->newJedisResult(getTransaction().migrate(host, port, db, timeout, migrateParams, keys),
							Converters.OK_STATUS_CONVERTER), ProtocolCommand.MIGRATE, args);
		}else{
			return execute((cmd)->cmd.migrate(host, port, db, timeout, migrateParams, keys),
					Converters.OK_STATUS_CONVERTER, ProtocolCommand.MIGRATE, args);
		}
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final String user, final String password,
						  final int timeout, final String... keys){
		final CommandArguments args = CommandArguments.create("host", host).put("port", port).put("db", db)
				.put("user", user).put("password", password).put("timeout", timeout).put("keys", keys);
		final MigrateParams migrateParams = MIGRATE_OPERATION_JEDIS_CONVERTER.convert(null).auth2(user, password);

		if(isPipeline()){
			return pipelineExecute(
					(cmd)->newJedisResult(getPipeline().migrate(host, port, db, timeout, migrateParams, keys),
							Converters.OK_STATUS_CONVERTER), ProtocolCommand.MIGRATE, args);
		}else if(isTransaction()){
			return transactionExecute(
					(cmd)->newJedisResult(getTransaction().migrate(host, port, db, timeout, migrateParams, keys),
							Converters.OK_STATUS_CONVERTER), ProtocolCommand.MIGRATE, args);
		}else{
			return execute((cmd)->cmd.migrate(host, port, db, timeout, migrateParams, keys),
					Converters.OK_STATUS_CONVERTER, ProtocolCommand.MIGRATE, args);
		}
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final byte[] user, final byte[] password,
						  final int timeout, final byte[]... keys){
		final CommandArguments args = CommandArguments.create("host", host).put("port", port).put("db", db)
				.put("user", user).put("password", password).put("timeout", timeout).put("keys", keys);
		final MigrateParams migrateParams = MIGRATE_OPERATION_JEDIS_CONVERTER.convert(null)
				.auth2(SafeEncoder.encode(user), SafeEncoder.encode(password));

		if(isPipeline()){
			return pipelineExecute(
					(cmd)->newJedisResult(getPipeline().migrate(host, port, db, timeout, migrateParams, keys),
							Converters.OK_STATUS_CONVERTER), ProtocolCommand.MIGRATE, args);
		}else if(isTransaction()){
			return transactionExecute(
					(cmd)->newJedisResult(getTransaction().migrate(host, port, db, timeout, migrateParams, keys),
							Converters.OK_STATUS_CONVERTER), ProtocolCommand.MIGRATE, args);
		}else{
			return execute((cmd)->cmd.migrate(host, port, db, timeout, migrateParams, keys),
					Converters.OK_STATUS_CONVERTER, ProtocolCommand.MIGRATE, args);
		}
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final String user, final String password,
						  final int timeout, final MigrateOperation operation, final String... keys){
		final CommandArguments args = CommandArguments.create("host", host).put("port", port).put("db", db)
				.put("user", user).put("password", password).put("timeout", timeout).put("operation", operation)
				.put("keys", keys);
		final MigrateParams migrateParams = MIGRATE_OPERATION_JEDIS_CONVERTER.convert(operation).auth2(user, password);

		if(isPipeline()){
			return pipelineExecute(
					(cmd)->newJedisResult(getPipeline().migrate(host, port, db, timeout, migrateParams, keys),
							Converters.OK_STATUS_CONVERTER), ProtocolCommand.MIGRATE, args);
		}else if(isTransaction()){
			return transactionExecute(
					(cmd)->newJedisResult(getTransaction().migrate(host, port, db, timeout, migrateParams, keys),
							Converters.OK_STATUS_CONVERTER), ProtocolCommand.MIGRATE, args);
		}else{
			return execute((cmd)->cmd.migrate(host, port, db, timeout, migrateParams, keys),
					Converters.OK_STATUS_CONVERTER, ProtocolCommand.MIGRATE, args);
		}
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final byte[] user, final byte[] password,
						  final int timeout, final MigrateOperation operation, final byte[]... keys){
		final CommandArguments args = CommandArguments.create("host", host).put("port", port).put("db", db)
				.put("user", user).put("password", password).put("timeout", timeout).put("operation", operation)
				.put("keys", keys);
		final MigrateParams migrateParams = MIGRATE_OPERATION_JEDIS_CONVERTER.convert(operation)
				.auth2(SafeEncoder.encode(user), SafeEncoder.encode(password));

		if(isPipeline()){
			return pipelineExecute(
					(cmd)->newJedisResult(getPipeline().migrate(host, port, db, timeout, migrateParams, keys),
							Converters.OK_STATUS_CONVERTER), ProtocolCommand.MIGRATE, args);
		}else if(isTransaction()){
			return transactionExecute(
					(cmd)->newJedisResult(getTransaction().migrate(host, port, db, timeout, migrateParams, keys),
							Converters.OK_STATUS_CONVERTER), ProtocolCommand.MIGRATE, args);
		}else{
			return execute((cmd)->cmd.migrate(host, port, db, timeout, migrateParams, keys),
					Converters.OK_STATUS_CONVERTER, ProtocolCommand.MIGRATE, args);
		}
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
	public String randomKey(){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().randomKey()), ProtocolCommand.RANDOMKEY);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().randomKey()), ProtocolCommand.RANDOMKEY);
		}else{
			return execute((cmd)->cmd.randomKey(), ProtocolCommand.RANDOMKEY);
		}
	}

	@Override
	public Status rename(final String key, final String newKey){
		final CommandArguments args = CommandArguments.create("key", key).put("newKey", newKey);

		if(isPipeline()){
			return pipelineExecute(
					(cmd)->newJedisResult(getPipeline().rename(key, newKey), Converters.OK_STATUS_CONVERTER),
					ProtocolCommand.RENAME, args);
		}else if(isTransaction()){
			return transactionExecute(
					(cmd)->newJedisResult(getTransaction().rename(key, newKey), Converters.OK_STATUS_CONVERTER),
					ProtocolCommand.RENAME, args);
		}else{
			return execute((cmd)->cmd.rename(key, newKey), Converters.OK_STATUS_CONVERTER, ProtocolCommand.RENAME,
					args);
		}
	}

	@Override
	public Status rename(final byte[] key, final byte[] newKey){
		final CommandArguments args = CommandArguments.create("key", key).put("newKey", newKey);

		if(isPipeline()){
			return pipelineExecute(
					(cmd)->newJedisResult(getPipeline().rename(key, newKey), Converters.OK_STATUS_CONVERTER),
					ProtocolCommand.RENAME, args);
		}else if(isTransaction()){
			return transactionExecute(
					(cmd)->newJedisResult(getTransaction().rename(key, newKey), Converters.OK_STATUS_CONVERTER),
					ProtocolCommand.RENAME, args);
		}else{
			return execute((cmd)->cmd.rename(key, newKey), Converters.OK_STATUS_CONVERTER, ProtocolCommand.RENAME,
					args);
		}
	}

	@Override
	public Status renameNx(final String key, final String newKey){
		final CommandArguments args = CommandArguments.create("key", key).put("newKey", newKey);
		final PredicateStatusConverter<Long> converter = new PredicateStatusConverter<>((val)->val == 1);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().renamenx(key, newKey), converter),
					ProtocolCommand.RENAMENX, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().renamenx(key, newKey), converter),
					ProtocolCommand.RENAMENX, args);
		}else{
			return execute((cmd)->cmd.renamenx(key, newKey), converter, ProtocolCommand.RENAMENX, args);
		}
	}

	@Override
	public Status renameNx(final byte[] key, final byte[] newKey){
		final CommandArguments args = CommandArguments.create("key", key).put("newKey", newKey);
		final PredicateStatusConverter<Long> converter = new PredicateStatusConverter<>((val)->val == 1);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().renamenx(key, newKey), converter),
					ProtocolCommand.RENAMENX, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().renamenx(key, newKey), converter),
					ProtocolCommand.RENAMENX, args);
		}else{
			return execute((cmd)->cmd.renamenx(key, newKey), converter, ProtocolCommand.RENAMENX, args);
		}
	}

	@Override
	public Status restore(final String key, final byte[] serializedValue, final int ttl){
		final CommandArguments args = CommandArguments.create("key", key).put("serializedValue", serializedValue)
				.put("ttl", ttl);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().restore(key, ttl, serializedValue),
					Converters.OK_STATUS_CONVERTER), ProtocolCommand.RESTORE, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().restore(key, ttl, serializedValue),
					Converters.OK_STATUS_CONVERTER), ProtocolCommand.RESTORE, args);
		}else{
			return execute((cmd)->cmd.restore(key, ttl, serializedValue), Converters.OK_STATUS_CONVERTER,
					ProtocolCommand.RESTORE, args);
		}
	}

	@Override
	public Status restore(final byte[] key, final byte[] serializedValue, final int ttl){
		final CommandArguments args = CommandArguments.create("key", key).put("serializedValue", serializedValue)
				.put("ttl", ttl);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().restore(key, ttl, serializedValue),
					Converters.OK_STATUS_CONVERTER), ProtocolCommand.RESTORE, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().restore(key, ttl, serializedValue),
					Converters.OK_STATUS_CONVERTER), ProtocolCommand.RESTORE, args);
		}else{
			return execute((cmd)->cmd.restore(key, ttl, serializedValue), Converters.OK_STATUS_CONVERTER,
					ProtocolCommand.RESTORE, args);
		}
	}

	@Override
	public Status restore(final String key, final byte[] serializedValue, final int ttl,
						  final RestoreArgument argument){
		final CommandArguments args = CommandArguments.create("key", key).put("serializedValue", serializedValue)
				.put("ttl", ttl).put("argument", argument);
		final RestoreParams restoreParams = RESTORE_ARGUMENT_JEDIS_CONVERTER.convert(argument);

		if(isPipeline()){
			return pipelineExecute(
					(cmd)->newJedisResult(getPipeline().restore(key, ttl, serializedValue, restoreParams),
							Converters.OK_STATUS_CONVERTER), ProtocolCommand.RESTORE, args);
		}else if(isTransaction()){
			return transactionExecute(
					(cmd)->newJedisResult(getTransaction().restore(key, ttl, serializedValue, restoreParams),
							Converters.OK_STATUS_CONVERTER), ProtocolCommand.RESTORE, args);
		}else{
			return execute((cmd)->cmd.restore(key, ttl, serializedValue, restoreParams), Converters.OK_STATUS_CONVERTER,
					ProtocolCommand.RESTORE, args);
		}
	}

	@Override
	public Status restore(final byte[] key, final byte[] serializedValue, final int ttl,
						  final RestoreArgument argument){
		final CommandArguments args = CommandArguments.create("key", key).put("serializedValue", serializedValue)
				.put("ttl", ttl).put("argument", argument);
		final RestoreParams restoreParams = RESTORE_ARGUMENT_JEDIS_CONVERTER.convert(argument);

		if(isPipeline()){
			return pipelineExecute(
					(cmd)->newJedisResult(getPipeline().restore(key, ttl, serializedValue, restoreParams),
							Converters.OK_STATUS_CONVERTER), ProtocolCommand.RESTORE, args);
		}else if(isTransaction()){
			return transactionExecute(
					(cmd)->newJedisResult(getTransaction().restore(key, ttl, serializedValue, restoreParams),
							Converters.OK_STATUS_CONVERTER), ProtocolCommand.RESTORE, args);
		}else{
			return execute((cmd)->cmd.restore(key, ttl, serializedValue, restoreParams), Converters.OK_STATUS_CONVERTER,
					ProtocolCommand.RESTORE, args);
		}
	}

	@Override
	public ScanResult<List<String>> scan(final String cursor){
		final CommandArguments args = CommandArguments.create("cursor", cursor);

		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.SCAN, args);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.SCAN, args);
		}else{
			return execute((cmd)->cmd.scan(cursor), STRING_LIST_SCAN_RESULT_EXPOSE_CONVERTER, ProtocolCommand.SCAN,
					args);
		}
	}

	@Override
	public ScanResult<List<byte[]>> scan(final byte[] cursor){
		final CommandArguments args = CommandArguments.create("cursor", cursor);

		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.SCAN, args);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.SCAN, args);
		}else{
			return execute((cmd)->cmd.scan(cursor), BINARY_LIST_SCAN_RESULT_EXPOSE_CONVERTER, ProtocolCommand.SCAN,
					args);
		}
	}

	@Override
	public ScanResult<List<String>> scan(final String cursor, final String pattern){
		final CommandArguments args = CommandArguments.create("cursor", cursor).put("pattern", pattern);

		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.SCAN, args);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.SCAN, args);
		}else{
			return execute((cmd)->cmd.scan(cursor, new JedisScanParams(pattern)),
					STRING_LIST_SCAN_RESULT_EXPOSE_CONVERTER, ProtocolCommand.SCAN, args);
		}
	}

	@Override
	public ScanResult<List<byte[]>> scan(final byte[] cursor, final byte[] pattern){
		final CommandArguments args = CommandArguments.create("cursor", cursor).put("pattern", pattern);

		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.SCAN, args);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.SCAN, args);
		}else{
			return execute((cmd)->cmd.scan(cursor, new JedisScanParams(pattern)),
					BINARY_LIST_SCAN_RESULT_EXPOSE_CONVERTER, ProtocolCommand.SCAN, args);
		}
	}

	@Override
	public ScanResult<List<String>> scan(final String cursor, final long count){
		final CommandArguments args = CommandArguments.create("cursor", cursor).put("count", count);

		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.SCAN, args);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.SCAN, args);
		}else{
			return execute((cmd)->cmd.scan(cursor, new JedisScanParams(count)),
					STRING_LIST_SCAN_RESULT_EXPOSE_CONVERTER, ProtocolCommand.SCAN, args);
		}
	}

	@Override
	public ScanResult<List<byte[]>> scan(final byte[] cursor, final long count){
		final CommandArguments args = CommandArguments.create("cursor", cursor).put("count", count);

		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.SCAN, args);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.SCAN, args);
		}else{
			return execute((cmd)->cmd.scan(cursor, new JedisScanParams(count)),
					BINARY_LIST_SCAN_RESULT_EXPOSE_CONVERTER, ProtocolCommand.SCAN, args);
		}
	}

	@Override
	public ScanResult<List<String>> scan(final String cursor, final String pattern, final long count){
		final CommandArguments args = CommandArguments.create("cursor", cursor).put("pattern", pattern)
				.put("count", count);

		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.SCAN, args);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.SCAN, args);
		}else{
			return execute((cmd)->cmd.scan(cursor, new JedisScanParams(pattern, count)),
					STRING_LIST_SCAN_RESULT_EXPOSE_CONVERTER, ProtocolCommand.SCAN, args);
		}
	}

	@Override
	public ScanResult<List<byte[]>> scan(final byte[] cursor, final byte[] pattern, final long count){
		final CommandArguments args = CommandArguments.create("cursor", cursor).put("pattern", pattern)
				.put("count", count);

		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.SCAN, args);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.SCAN, args);
		}else{
			return execute((cmd)->cmd.scan(cursor, new JedisScanParams(pattern, count)),
					BINARY_LIST_SCAN_RESULT_EXPOSE_CONVERTER, ProtocolCommand.SCAN, args);
		}
	}

	@Override
	public List<String> sort(final String key){
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().sort(key)), ProtocolCommand.SORT, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().sort(key)), ProtocolCommand.SORT, args);
		}else{
			return execute((cmd)->cmd.sort(key), ProtocolCommand.SORT, args);
		}
	}

	@Override
	public List<byte[]> sort(final byte[] key){
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().sort(key)), ProtocolCommand.SORT, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().sort(key)), ProtocolCommand.SORT, args);
		}else{
			return execute((cmd)->cmd.sort(key), ProtocolCommand.SORT, args);
		}
	}

	@Override
	public List<String> sort(final String key, final SortArgument sortArgument){
		final CommandArguments args = CommandArguments.create("key", key).put("sortArgument", sortArgument);
		final SortingParams soringParams = SORT_ARGUMENT_JEDIS_CONVERTER.convert(sortArgument);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().sort(key, soringParams)), ProtocolCommand.SORT,
					args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().sort(key, soringParams)),
					ProtocolCommand.SORT, args);
		}else{
			return execute((cmd)->cmd.sort(key, soringParams), ProtocolCommand.SORT, args);
		}
	}

	@Override
	public List<byte[]> sort(final byte[] key, final SortArgument sortArgument){
		final CommandArguments args = CommandArguments.create("key", key).put("sortArgument", sortArgument);
		final SortingParams soringParams = SORT_ARGUMENT_JEDIS_CONVERTER.convert(sortArgument);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().sort(key, soringParams)), ProtocolCommand.SORT,
					args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().sort(key, soringParams)),
					ProtocolCommand.SORT, args);
		}else{
			return execute((cmd)->cmd.sort(key, soringParams), ProtocolCommand.SORT, args);
		}
	}

	@Override
	public Long sort(final String key, final String destKey){
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().sort(key, destKey)), ProtocolCommand.SORT,
					args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().sort(key, destKey)), ProtocolCommand.SORT,
					args);
		}else{
			return execute((cmd)->cmd.sort(key, destKey), ProtocolCommand.SORT, args);
		}
	}

	@Override
	public Long sort(final byte[] key, final byte[] destKey){
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().sort(key, destKey)), ProtocolCommand.SORT,
					args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().sort(key, destKey)), ProtocolCommand.SORT,
					args);
		}else{
			return execute((cmd)->cmd.sort(key, destKey), ProtocolCommand.SORT, args);
		}
	}

	@Override
	public Long sort(final String key, final String destKey, final SortArgument sortArgument){
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey)
				.put("sortArgument", sortArgument);
		final SortingParams soringParams = SORT_ARGUMENT_JEDIS_CONVERTER.convert(sortArgument);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().sort(key, soringParams, destKey)),
					ProtocolCommand.SORT, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().sort(key, soringParams, destKey)),
					ProtocolCommand.SORT, args);
		}else{
			return execute((cmd)->cmd.sort(key, soringParams, destKey), ProtocolCommand.SORT, args);
		}
	}

	@Override
	public Long sort(final byte[] key, final byte[] destKey, final SortArgument sortArgument){
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey)
				.put("sortArgument", sortArgument);
		final SortingParams soringParams = SORT_ARGUMENT_JEDIS_CONVERTER.convert(sortArgument);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().sort(key, soringParams, destKey)),
					ProtocolCommand.SORT,
					args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().sort(key, soringParams, destKey)),
					ProtocolCommand.SORT,
					args);
		}else{
			return execute((cmd)->cmd.sort(key, soringParams, destKey), ProtocolCommand.SORT, args);
		}
	}

	@Override
	public Long touch(final String... keys){
		final CommandArguments args = CommandArguments.create("keys", keys);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().touch(keys)), ProtocolCommand.TOUCH, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().touch(keys)), ProtocolCommand.TOUCH, args);
		}else{
			return execute((cmd)->cmd.touch(keys), ProtocolCommand.TOUCH, args);
		}
	}

	@Override
	public Long touch(final byte[]... keys){
		final CommandArguments args = CommandArguments.create("keys", keys);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().touch(keys)), ProtocolCommand.TOUCH, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().touch(keys)), ProtocolCommand.TOUCH, args);
		}else{
			return execute((cmd)->cmd.touch(keys), ProtocolCommand.TOUCH, args);
		}
	}

	@Override
	public Type type(final String key){
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().type(key), TYPE_CONVERTER), ProtocolCommand.TYPE,
					args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().type(key), TYPE_CONVERTER),
					ProtocolCommand.TYPE,
					args);
		}else{
			return execute((cmd)->cmd.type(key), TYPE_CONVERTER, ProtocolCommand.TYPE, args);
		}
	}

	@Override
	public Type type(final byte[] key){
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().type(key), TYPE_CONVERTER), ProtocolCommand.TYPE,
					args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().type(key), TYPE_CONVERTER),
					ProtocolCommand.TYPE,
					args);
		}else{
			return execute((cmd)->cmd.type(key), TYPE_CONVERTER, ProtocolCommand.TYPE, args);
		}
	}

	@Override
	public Long unlink(final String... keys){
		final CommandArguments args = CommandArguments.create("keys", keys);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().unlink(keys)), ProtocolCommand.UNLINK, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().unlink(keys)), ProtocolCommand.UNLINK,
					args);
		}else{
			return execute((cmd)->cmd.unlink(keys), ProtocolCommand.UNLINK, args);
		}
	}

	@Override
	public Long unlink(final byte[]... keys){
		final CommandArguments args = CommandArguments.create("keys", keys);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().unlink(keys)), ProtocolCommand.UNLINK, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().unlink(keys)), ProtocolCommand.UNLINK,
					args);
		}else{
			return execute((cmd)->cmd.unlink(keys), ProtocolCommand.UNLINK, args);
		}
	}

	@Override
	public Long wait(final int replicas, final int timeout){
		final CommandArguments args = CommandArguments.create("replicas", replicas).put("timeout", timeout);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().waitReplicas(replicas, timeout)),
					ProtocolCommand.WAIT, args);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.WAIT, args);
		}else{
			return execute((cmd)->cmd.waitReplicas(replicas, timeout), ProtocolCommand.WAIT, args);
		}
	}

	@Override
	public ObjectEncoding objectEncoding(final String key){
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return pipelineExecute(
					(cmd)->newJedisResult(getPipeline().objectEncoding(key), STRING_OBJECT_ENCODING_CONVERTER),
					ProtocolCommand.OBJECT, args);
		}else if(isTransaction()){
			return transactionExecute(
					(cmd)->newJedisResult(getTransaction().objectEncoding(key), STRING_OBJECT_ENCODING_CONVERTER),
					ProtocolCommand.OBJECT, args);
		}else{
			return execute((cmd)->cmd.objectEncoding(key), STRING_OBJECT_ENCODING_CONVERTER, ProtocolCommand.OBJECT,
					args);
		}
	}

	@Override
	public ObjectEncoding objectEncoding(final byte[] key){
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return pipelineExecute(
					(cmd)->newJedisResult(getPipeline().objectEncoding(key), BINARY_OBJECT_ENCODING_CONVERTER),
					ProtocolCommand.OBJECT, args);
		}else if(isTransaction()){
			return transactionExecute(
					(cmd)->newJedisResult(getTransaction().objectEncoding(key), BINARY_OBJECT_ENCODING_CONVERTER),
					ProtocolCommand.OBJECT, args);
		}else{
			return execute((cmd)->cmd.objectEncoding(key), BINARY_OBJECT_ENCODING_CONVERTER, ProtocolCommand.OBJECT,
					args);
		}
	}

	@Override
	public Long objectFreq(final String key){
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return pipelineExecute(
					(cmd)->newJedisResult(getPipeline().objectFreq(key)), ProtocolCommand.OBJECT, args);
		}else if(isTransaction()){
			return transactionExecute(
					(cmd)->newJedisResult(getTransaction().objectFreq(key)), ProtocolCommand.OBJECT, args);
		}else{
			return execute((cmd)->cmd.objectFreq(key), ProtocolCommand.OBJECT, args);
		}
	}

	@Override
	public Long objectFreq(final byte[] key){
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return pipelineExecute(
					(cmd)->newJedisResult(getPipeline().objectFreq(key)), ProtocolCommand.OBJECT, args);
		}else if(isTransaction()){
			return transactionExecute(
					(cmd)->newJedisResult(getTransaction().objectFreq(key)), ProtocolCommand.OBJECT, args);
		}else{
			return execute((cmd)->cmd.objectFreq(key), ProtocolCommand.OBJECT, args);
		}
	}

	@Override
	public Long objectIdleTime(final String key){
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return pipelineExecute(
					(cmd)->newJedisResult(getPipeline().objectIdletime(key)), ProtocolCommand.OBJECT, args);
		}else if(isTransaction()){
			return transactionExecute(
					(cmd)->newJedisResult(getTransaction().objectIdletime(key)), ProtocolCommand.OBJECT, args);
		}else{
			return execute((cmd)->cmd.objectIdletime(key), ProtocolCommand.OBJECT, args);
		}
	}

	@Override
	public Long objectIdleTime(final byte[] key){
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return pipelineExecute(
					(cmd)->newJedisResult(getPipeline().objectIdletime(key)), ProtocolCommand.OBJECT, args);
		}else if(isTransaction()){
			return transactionExecute(
					(cmd)->newJedisResult(getTransaction().objectIdletime(key)), ProtocolCommand.OBJECT, args);
		}else{
			return execute((cmd)->cmd.objectIdletime(key), ProtocolCommand.OBJECT, args);
		}
	}

	@Override
	public Long objectRefcount(final String key){
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return pipelineExecute(
					(cmd)->newJedisResult(getPipeline().objectRefcount(key)), ProtocolCommand.OBJECT, args);
		}else if(isTransaction()){
			return transactionExecute(
					(cmd)->newJedisResult(getTransaction().objectRefcount(key)), ProtocolCommand.OBJECT, args);
		}else{
			return execute((cmd)->cmd.objectRefcount(key), ProtocolCommand.OBJECT, args);
		}
	}

	@Override
	public Long objectRefcount(final byte[] key){
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return pipelineExecute(
					(cmd)->newJedisResult(getPipeline().objectRefcount(key)), ProtocolCommand.OBJECT, args);
		}else if(isTransaction()){
			return transactionExecute(
					(cmd)->newJedisResult(getTransaction().objectRefcount(key)), ProtocolCommand.OBJECT, args);
		}else{
			return execute((cmd)->cmd.objectRefcount(key), ProtocolCommand.OBJECT, args);
		}
	}

}
