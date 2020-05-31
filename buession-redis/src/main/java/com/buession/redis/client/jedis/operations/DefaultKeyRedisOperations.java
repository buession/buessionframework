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
 * | Copyright @ 2013-2020 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.client.jedis.operations;

import com.buession.core.Executor;
import com.buession.lang.Status;
import com.buession.redis.client.KeyRedisOperations;
import com.buession.redis.client.jedis.JedisClientUtils;
import com.buession.redis.client.jedis.JedisRedisClient;
import com.buession.redis.core.JedisScanParams;
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.Type;
import com.buession.redis.core.command.KeyCommands;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.operations.OperationsCommandArguments;
import com.buession.redis.exception.NotSupportedCommandException;
import com.buession.redis.exception.RenameException;
import com.buession.redis.utils.ReturnUtils;
import com.buession.redis.utils.SafeEncoder;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.commands.JedisCommands;

import java.util.List;
import java.util.Set;

/**
 * @author Yong.Teng
 */
public class DefaultKeyRedisOperations<C extends JedisCommands> extends AbstractJedisRedisOperations implements KeyRedisOperations {

	public DefaultKeyRedisOperations(final JedisRedisClient client){
		super(client);
	}

	@Override
	public boolean exists(final String key){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key);

		return execute((C jc)->isTransaction() ? getTransaction().exists(key).get() : jc.exists(key),
				ProtocolCommand.EXISTS, arguments);
	}

	@Override
	public Type type(final String key){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key);

		return execute((C jc)->ReturnUtils.enumValueOf(isTransaction() ? getTransaction().type(key).get() :
				jc.type(key), Type.class), ProtocolCommand.TYPE, arguments);
	}

	@Override
	public Status rename(final String key, final String newKey){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"newKey", newKey);

		return execute(new Executor<C, Status>() {

			@Override
			public Status execute(C jc){
				String ret;

				if(isTransaction()){
					ret = getTransaction().rename(key, newKey).get();
				}else{
					if(jc instanceof Jedis){
						try{
							ret = ((Jedis) jc).rename(key, newKey);
						}catch(Exception e){
							throw new RenameException(e.getMessage(), e);
						}
					}else{
						throw new NotSupportedCommandException(ProtocolCommand.RENAME);
					}
				}

				return ReturnUtils.statusForOK(ret);
			}

		}, ProtocolCommand.RENAME, arguments);
	}

	@Override
	public Status renameNx(final String key, final String newKey){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"newKey", newKey);

		return execute(new Executor<C, Status>() {

			@Override
			public Status execute(C jc){
				Long ret;

				if(isTransaction()){
					ret = getTransaction().renamenx(key, newKey).get();
				}else{
					if(jc instanceof Jedis){
						ret = ((Jedis) jc).renamenx(key, newKey);
					}else{
						throw new NotSupportedCommandException(ProtocolCommand.RENAMENX);
					}
				}

				return ReturnUtils.statusForBool(ret > 0);
			}

		}, ProtocolCommand.RENAMENX, arguments);
	}

	@Override
	public String randomKey(){
		return execute(new Executor<C, String>() {

			@Override
			public String execute(C jc){
				if(isTransaction()){
					return getTransaction().randomKey().get();
				}else{
					if(jc instanceof Jedis){
						return ((Jedis) jc).randomKey();
					}else{
						throw new NotSupportedCommandException(ProtocolCommand.RANDOMKEY);
					}
				}
			}

		}, ProtocolCommand.RANDOMKEY);
	}

	@Override
	public Set<String> keys(final String pattern){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("pattern", pattern);

		return execute(new Executor<C, Set<String>>() {

			@Override
			public Set<String> execute(C jc){
				if(isTransaction()){
					return getTransaction().keys(pattern).get();
				}else{
					if(jc instanceof Jedis){
						return ((Jedis) jc).keys(pattern);
					}else{
						throw new NotSupportedCommandException(ProtocolCommand.KEYS);
					}
				}
			}

		}, ProtocolCommand.KEYS, arguments);
	}

	@Override
	public Status expire(final String key, final int lifetime){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"lifetime", lifetime);

		return execute((C jc)->ReturnUtils.statusForBool(isTransaction() ?
				getTransaction().expire(key, lifetime).get() == 1 : jc.expire(key, lifetime) == 1),
				ProtocolCommand.EXPIRE, arguments);
	}

	@Override
	public Status expireAt(final String key, final long unixTimestamp){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"unixTimestamp", unixTimestamp);

		return execute((C jc)->ReturnUtils.statusForBool(isTransaction() ? getTransaction().expireAt(key,
				unixTimestamp).get() == 1 : jc.expireAt(key, unixTimestamp) == 1), ProtocolCommand.EXPIREAT,
				arguments);
	}

	@Override
	public Status pExpire(final String key, final int lifetime){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"lifetime", lifetime);

		return execute((C jc)->ReturnUtils.statusForBool(isTransaction() ?
				getTransaction().pexpire(key, lifetime).get() == 1 : jc.pexpire(key, lifetime) == 1),
				ProtocolCommand.PEXPIRE, arguments);
	}

	@Override
	public Status pExpireAt(final String key, final long unixTimestamp){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"unixTimestamp", unixTimestamp);

		return execute((C jc)->ReturnUtils.statusForBool(isTransaction() ? getTransaction().pexpireAt(key,
				unixTimestamp).get() == 1 : jc.pexpireAt(key, unixTimestamp) == 1), ProtocolCommand.PEXPIREAT,
				arguments);
	}

	@Override
	public Long ttl(final String key){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key);

		return execute((C jc)->isTransaction() ? getTransaction().ttl(key).get() : jc.ttl(key), ProtocolCommand.TTL,
				arguments);
	}

	@Override
	public Long pTtl(final String key){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key);

		return execute((C jc)->isTransaction() ? getTransaction().pttl(key).get() : jc.pttl(key), ProtocolCommand.PTTL
				, arguments);
	}

	@Override
	public Status persist(final String key){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key);

		return execute((C jc)->ReturnUtils.statusForBool(isTransaction() ? getTransaction().persist(key).get() > 0 :
				jc.persist(key) > 0), ProtocolCommand.PERSIST, arguments);
	}

	@Override
	public ScanResult<List<String>> scan(final String cursor){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("cursor", cursor);

		return execute(new Executor<C, ScanResult<List<String>>>() {

			@Override
			public ScanResult<List<String>> execute(C jc){
				if(isTransaction()){
				}else{
					if(jc instanceof Jedis){
						return JedisClientUtils.listScanResultDeconvert(((Jedis) jc).scan(cursor));
					}
				}

				throw new NotSupportedCommandException(ProtocolCommand.SCAN);
			}

		}, ProtocolCommand.SCAN, arguments);
	}

	@Override
	public ScanResult<List<String>> scan(final String cursor, final String pattern){
		final OperationsCommandArguments arguments =
				OperationsCommandArguments.getInstance().put("cursor", cursor).put("pattern", pattern);

		return execute(new Executor<C, ScanResult<List<String>>>() {

			@Override
			public ScanResult<List<String>> execute(C jc){
				if(isTransaction()){
				}else{
					if(jc instanceof Jedis){
						return JedisClientUtils.listScanResultDeconvert(((Jedis) jc).scan(cursor,
								new JedisScanParams(pattern)));
					}
				}

				throw new NotSupportedCommandException(ProtocolCommand.SCAN);
			}

		}, ProtocolCommand.SCAN, arguments);
	}

	@Override
	public ScanResult<List<String>> scan(final String cursor, final int count){
		final OperationsCommandArguments arguments =
				OperationsCommandArguments.getInstance().put("cursor", cursor).put("count", count);

		return execute(new Executor<C, ScanResult<List<String>>>() {

			@Override
			public ScanResult<List<String>> execute(C jc){
				if(isTransaction()){
				}else{
					if(jc instanceof Jedis){
						return JedisClientUtils.listScanResultDeconvert(((Jedis) jc).scan(cursor,
								new JedisScanParams(count)));
					}
				}

				throw new NotSupportedCommandException(ProtocolCommand.SCAN);
			}

		}, ProtocolCommand.SCAN, arguments);
	}

	@Override
	public ScanResult<List<String>> scan(final String cursor, final String pattern, final int count){
		final OperationsCommandArguments arguments =
				OperationsCommandArguments.getInstance().put("cursor", cursor).put("pattern", pattern).put("count",
						count);

		return execute(new Executor<C, ScanResult<List<String>>>() {

			@Override
			public ScanResult<List<String>> execute(C jc){
				if(isTransaction()){
				}else{
					if(jc instanceof Jedis){
						return JedisClientUtils.listScanResultDeconvert(((Jedis) jc).scan(cursor,
								new JedisScanParams(pattern, count)));
					}
				}

				throw new NotSupportedCommandException(ProtocolCommand.SCAN);
			}

		}, ProtocolCommand.SCAN, arguments);
	}

	@Override
	public List<String> sort(final String key){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key);

		return execute((C jc)->isTransaction() ? getTransaction().sort(key).get() : jc.sort(key), ProtocolCommand.SORT
				, arguments);
	}

	@Override
	public List<String> sort(final String key, final KeyCommands.SortArgument sortArgument){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"sortArgument", sortArgument);

		return execute((C jc)->isTransaction() ? getTransaction().sort(key,
				JedisClientUtils.sortArgumentConvert(sortArgument)).get() : jc.sort(key,
				JedisClientUtils.sortArgumentConvert(sortArgument)), ProtocolCommand.SORT, arguments);
	}

	@Override
	public Long sort(final String key, final String destKey){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"destKey", destKey);

		return execute(new Executor<C, Long>() {

			@Override
			public Long execute(C jc){
				if(isTransaction()){
					return getTransaction().sort(key, destKey).get();
				}else{
					if(jc instanceof Jedis){
						return ((Jedis) jc).sort(key, destKey);
					}else{
						throw new NotSupportedCommandException(ProtocolCommand.SORT);
					}
				}
			}

		}, ProtocolCommand.SORT, arguments);
	}

	@Override
	public Long sort(final String key, final String destKey, final SortArgument sortArgument){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"destKey", destKey).put("sortArgument", sortArgument);

		return execute(new Executor<C, Long>() {

			@Override
			public Long execute(C jc){
				if(isTransaction()){
					return getTransaction().sort(key, JedisClientUtils.sortArgumentConvert(sortArgument), destKey).get();
				}else{
					if(jc instanceof Jedis){
						return ((Jedis) jc).sort(key, JedisClientUtils.sortArgumentConvert(sortArgument), destKey);
					}else{
						throw new NotSupportedCommandException(ProtocolCommand.SORT);
					}
				}
			}

		}, ProtocolCommand.SORT, arguments);
	}

	@Override
	public byte[] dump(final String key){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key);

		return execute((C jc)->isTransaction() ? getTransaction().dump(key).get() : jc.dump(key), ProtocolCommand.DUMP
				, arguments);
	}

	@Override
	public Status restore(final String key, final String serializedValue, final int ttl){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"serializedValue", serializedValue).put("ttl", ttl);

		return execute((C jc)->ReturnUtils.statusForOK(isTransaction() ? getTransaction().restore(key, ttl,
				SafeEncoder.encode(serializedValue)).get() : jc.restore(key, ttl, SafeEncoder.encode(serializedValue))), ProtocolCommand.RESTORE, arguments);
	}

	@Override
	public Status migrate(final String key, final String host, final int port, final int db, final int timeout){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"host", host).put("port", port).put("key", key).put("db", db).put("timeout", timeout);

		return execute(new Executor<C, Status>() {

			@Override
			public Status execute(C jc){
				String ret;

				if(isTransaction()){
					ret = getTransaction().migrate(host, port, key, db, timeout).get();
				}else{
					if(jc instanceof Jedis){
						ret = ((Jedis) jc).migrate(host, port, key, db, timeout);
					}else if(jc instanceof ShardedJedis){
						ret = getShard((ShardedJedis) jc, key).migrate(host, port, key, db, timeout);
					}else{
						throw new NotSupportedCommandException(ProtocolCommand.MIGRATE);
					}
				}

				return ReturnUtils.statusForOK(ret);
			}

		}, ProtocolCommand.MIGRATE, arguments);
	}

	@Override
	public Status migrate(final String key, final String host, final int port, final int db, final int timeout,
			final MigrateOperation migrateOperation){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"host", host).put("port", port).put("key", key).put("db", db).put("timeout", timeout).put("migrate",
				migrateOperation);

		return execute(new Executor<C, Status>() {

			@Override
			public Status execute(C jc){
				String ret;

				if(isTransaction()){
					ret = getTransaction().migrate(host, port, db, timeout,
							JedisClientUtils.migrateOperationConvert(migrateOperation), key).get();
				}else{
					if(jc instanceof Jedis){
						ret = ((Jedis) jc).migrate(host, port, db, timeout,
								JedisClientUtils.migrateOperationConvert(migrateOperation), key);
					}else if(jc instanceof ShardedJedis){
						ret = getShard((ShardedJedis) jc, key).migrate(host, port, db, timeout,
								JedisClientUtils.migrateOperationConvert(migrateOperation), key);
					}else{
						throw new NotSupportedCommandException(ProtocolCommand.MIGRATE);
					}
				}

				return ReturnUtils.statusForOK(ret);
			}

		}, ProtocolCommand.MIGRATE, arguments);
	}

	@Override
	public Long del(final String... keys){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("keys", keys);

		return execute(new Executor<C, Long>() {

			@Override
			public Long execute(C jc){
				if(isTransaction()){
					return getTransaction().del(keys).get();
				}else{
					if(jc instanceof Jedis){
						return ((Jedis) jc).del(keys);
					}else if(jc instanceof ShardedJedis){
						if(keys != null){
							long result = 0;

							for(String key : keys){
								result += jc.del(key);
							}

							return result;
						}

						return 0L;
					}else{
						throw new NotSupportedCommandException(ProtocolCommand.DEL);
					}
				}
			}

		}, ProtocolCommand.DEL, arguments);
	}

	@Override
	public Status move(final String key, final int db){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put("db"
				, db);

		return execute((C jc)->ReturnUtils.statusForBool(isTransaction() ? getTransaction().move(key, db).get() > 0 :
				jc.move(key, db) > 0), ProtocolCommand.MOVE, arguments);
	}

}
