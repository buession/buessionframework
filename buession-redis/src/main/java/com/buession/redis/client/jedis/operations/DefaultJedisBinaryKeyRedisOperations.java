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
import com.buession.redis.client.jedis.JedisClientUtils;
import com.buession.redis.client.jedis.JedisRedisClient;
import com.buession.redis.core.JedisScanParams;
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.Type;
import com.buession.redis.core.command.KeyCommands;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.operations.OperationsCommandArguments;
import com.buession.redis.exception.NotSupportedCommandException;
import com.buession.redis.exception.NotSupportedTransactionCommandException;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.commands.BinaryJedisCommands;

import java.util.List;
import java.util.Set;

/**
 * @author Yong.Teng
 */
public class DefaultJedisBinaryKeyRedisOperations<C extends BinaryJedisCommands> extends AbstractJedisBinaryRedisOperations implements JedisBinaryKeyRedisOperations {

	public DefaultJedisBinaryKeyRedisOperations(final JedisRedisClient client){
		super(client);
	}

	@Override
	public boolean exists(final byte[] key){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key);

		if(isTransaction()){
			return execute((C cmd)->getTransaction().exists(key).get(), ProtocolCommand.EXISTS, args);
		}else{
			return execute((C cmd)->cmd.exists(key), ProtocolCommand.EXISTS, args);
		}
	}

	@Override
	public Type type(final byte[] key){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key);

		if(isTransaction()){
			return execute((C cmd)->enumValueOf(getTransaction().type(key).get(), Type.class), ProtocolCommand.TYPE,
					args);
		}else{
			return execute((C cmd)->enumValueOf(cmd.type(key), Type.class), ProtocolCommand.TYPE, args);
		}
	}

	@Override
	public Status rename(final byte[] key, final byte[] newKey){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("newKey",
				newKey);

		return execute(new Executor<C, Status>() {

			@Override
			public Status execute(C cmd){
				String ret;

				if(isTransaction()){
					ret = getTransaction().rename(key, newKey).get();
				}else{
					if(cmd instanceof Jedis){
						ret = ((Jedis) cmd).rename(key, newKey);
					}else{
						throw new NotSupportedCommandException(ProtocolCommand.RENAME);
					}
				}

				return statusForOK(ret);
			}

		}, ProtocolCommand.RENAME, args);
	}

	@Override
	public Status renameNx(final byte[] key, final byte[] newKey){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("newKey",
				newKey);

		return execute(new Executor<C, Status>() {

			@Override
			public Status execute(C cmd){
				Long ret;

				if(isTransaction()){
					ret = getTransaction().renamenx(key, newKey).get();
				}else{
					if(cmd instanceof Jedis){
						ret = ((Jedis) cmd).renamenx(key, newKey);
					}else{
						throw new NotSupportedCommandException(ProtocolCommand.RENAMENX);
					}
				}

				return statusForBool(ret > 0);
			}

		}, ProtocolCommand.RENAMENX, args);
	}

	@Override
	public Set<byte[]> keys(final byte[] pattern){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("pattern", pattern);

		return execute(new Executor<C, Set<byte[]>>() {

			@Override
			public Set<byte[]> execute(C cmd){
				if(isTransaction()){
					return getTransaction().keys(pattern).get();
				}else{
					if(cmd instanceof Jedis){
						return ((Jedis) cmd).keys(pattern);
					}else{
						throw new NotSupportedCommandException(ProtocolCommand.KEYS);
					}
				}
			}

		}, ProtocolCommand.KEYS, args);
	}

	@Override
	public Status expire(final byte[] key, final int lifetime){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("lifetime"
				, lifetime);

		if(isTransaction()){
			return execute((C cmd)->statusForBool(getTransaction().expire(key, lifetime).get() == 1),
					ProtocolCommand.EXPIRE, args);
		}else{
			return execute((C cmd)->statusForBool(cmd.expire(key, lifetime) == 1), ProtocolCommand.EXPIRE, args);
		}
	}

	@Override
	public Status expireAt(final byte[] key, final long unixTimestamp){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put(
				"unixTimestamp", unixTimestamp);

		if(isTransaction()){
			return execute((C cmd)->statusForBool(getTransaction().expireAt(key, unixTimestamp).get() == 1),
					ProtocolCommand.EXPIREAT, args);
		}else{
			return execute((C cmd)->statusForBool(cmd.expireAt(key, unixTimestamp) == 1), ProtocolCommand.EXPIREAT,
					args);
		}
	}

	@Override
	public Status pExpire(final byte[] key, final int lifetime){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("lifetime"
				, lifetime);

		if(isTransaction()){
			return execute((C cmd)->statusForBool(getTransaction().pexpire(key, lifetime).get() == 1),
					ProtocolCommand.PEXPIRE, args);
		}else{
			return execute((C cmd)->statusForBool(cmd.pexpire(key, lifetime) == 1), ProtocolCommand.PEXPIRE, args);
		}
	}

	@Override
	public Status pExpireAt(final byte[] key, final long unixTimestamp){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put(
				"unixTimestamp", unixTimestamp);

		if(isTransaction()){
			return execute((C cmd)->statusForBool(getTransaction().pexpireAt(key, unixTimestamp).get() == 1),
					ProtocolCommand.PEXPIREAT, args);
		}else{
			return execute((C cmd)->statusForBool(cmd.pexpireAt(key, unixTimestamp) == 1), ProtocolCommand.PEXPIREAT,
					args);
		}
	}

	@Override
	public Long ttl(final byte[] key){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key);

		if(isTransaction()){
			return execute((C cmd)->getTransaction().ttl(key).get(), ProtocolCommand.TTL, args);
		}else{
			return execute((C cmd)->cmd.ttl(key), ProtocolCommand.TTL, args);
		}
	}

	@Override
	public Long pTtl(final byte[] key){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key);

		if(isTransaction()){
			return execute((C cmd)->getTransaction().pttl(key).get(), ProtocolCommand.PTTL, args);
		}else{
			return execute((C cmd)->cmd.pttl(key), ProtocolCommand.PTTL, args);
		}
	}

	@Override
	public Status persist(final byte[] key){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key);

		if(isTransaction()){
			return execute((C cmd)->statusForBool(getTransaction().persist(key).get() > 0), ProtocolCommand.PERSIST,
					args);
		}else{
			return execute((C cmd)->statusForBool(cmd.persist(key) > 0), ProtocolCommand.PERSIST, args);
		}
	}

	@Override
	public ScanResult<List<byte[]>> scan(final byte[] cursor){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("cursor", cursor);

		return execute(new Executor<C, ScanResult<List<byte[]>>>() {

			@Override
			public ScanResult<List<byte[]>> execute(C cmd){
				if(isTransaction()){
					throw new NotSupportedTransactionCommandException(ProtocolCommand.SCAN);
				}else{
					if(cmd instanceof Jedis){
						return JedisClientUtils.listScanResultDeconvert(((Jedis) cmd).scan(cursor));
					}else{
						throw new NotSupportedCommandException(ProtocolCommand.SCAN);
					}
				}
			}

		}, ProtocolCommand.SCAN, args);
	}

	@Override
	public ScanResult<List<byte[]>> scan(final byte[] cursor, final byte[] pattern){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("cursor", cursor).put(
				"pattern", pattern);

		return execute(new Executor<C, ScanResult<List<byte[]>>>() {

			@Override
			public ScanResult<List<byte[]>> execute(C cmd){
				if(isTransaction()){
					throw new NotSupportedTransactionCommandException(ProtocolCommand.SCAN);
				}else{
					if(cmd instanceof Jedis){
						return JedisClientUtils.listScanResultDeconvert(((Jedis) cmd).scan(cursor,
								new JedisScanParams(pattern)));
					}else{
						throw new NotSupportedCommandException(ProtocolCommand.SCAN);
					}
				}
			}

		}, ProtocolCommand.SCAN, args);
	}

	@Override
	public ScanResult<List<byte[]>> scan(final byte[] cursor, final int count){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("cursor", cursor).put(
				"count", count);

		return execute(new Executor<C, ScanResult<List<byte[]>>>() {

			@Override
			public ScanResult<List<byte[]>> execute(C cmd){
				if(isTransaction()){
					throw new NotSupportedTransactionCommandException(ProtocolCommand.SCAN);
				}else{
					if(cmd instanceof Jedis){
						return JedisClientUtils.listScanResultDeconvert(((Jedis) cmd).scan(cursor,
								new JedisScanParams(count)));
					}else{
						throw new NotSupportedCommandException(ProtocolCommand.SCAN);
					}
				}
			}

		}, ProtocolCommand.SCAN, args);
	}

	@Override
	public ScanResult<List<byte[]>> scan(final byte[] cursor, final byte[] pattern, final int count){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("cursor", cursor).put(
				"pattern", pattern).put("count", count);

		return execute(new Executor<C, ScanResult<List<byte[]>>>() {

			@Override
			public ScanResult<List<byte[]>> execute(C cmd){
				if(isTransaction()){
					throw new NotSupportedTransactionCommandException(ProtocolCommand.SCAN);
				}else{
					if(cmd instanceof Jedis){
						return JedisClientUtils.listScanResultDeconvert(((Jedis) cmd).scan(cursor,
								new JedisScanParams(pattern, count)));
					}else{
						throw new NotSupportedCommandException(ProtocolCommand.SCAN);
					}
				}
			}

		}, ProtocolCommand.SCAN, args);
	}

	@Override
	public List<byte[]> sort(final byte[] key){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key);

		if(isTransaction()){
			return execute((C cmd)->getTransaction().sort(key).get(), ProtocolCommand.SORT, args);
		}else{
			return execute((C cmd)->cmd.sort(key), ProtocolCommand.SORT, args);
		}
	}

	@Override
	public List<byte[]> sort(final byte[] key, final KeyCommands.SortArgument sortArgument){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key);

		if(isTransaction()){
			return execute((C cmd)->getTransaction().sort(key, JedisClientUtils.sortArgumentConvert(sortArgument)).get(), ProtocolCommand.SORT, args);
		}else{
			return execute((C cmd)->cmd.sort(key, JedisClientUtils.sortArgumentConvert(sortArgument)),
					ProtocolCommand.SORT, args);
		}
	}

	@Override
	public Long sort(final byte[] key, final byte[] destKey){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("destKey"
				, destKey);

		return execute(new Executor<C, Long>() {

			@Override
			public Long execute(C cmd){
				if(isTransaction()){
					return getTransaction().sort(key, destKey).get();
				}else{
					if(cmd instanceof Jedis){
						return ((Jedis) cmd).sort(key, destKey);
					}else{
						throw new NotSupportedCommandException(ProtocolCommand.SORT);
					}
				}
			}

		}, ProtocolCommand.SORT, args);
	}

	@Override
	public Long sort(final byte[] key, final byte[] destKey, final KeyCommands.SortArgument sortArgument){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("destKey"
				, destKey).put("sortArgument", sortArgument);

		return execute(new Executor<C, Long>() {

			@Override
			public Long execute(C cmd){
				if(isTransaction()){
					return getTransaction().sort(key, JedisClientUtils.sortArgumentConvert(sortArgument), destKey).get();
				}else{
					if(cmd instanceof Jedis){
						return ((Jedis) cmd).sort(key, JedisClientUtils.sortArgumentConvert(sortArgument), destKey);
					}else{
						throw new NotSupportedCommandException(ProtocolCommand.SORT);
					}
				}
			}

		}, ProtocolCommand.SORT, args);
	}

	@Override
	public byte[] dump(final byte[] key){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key);

		if(isTransaction()){
			return execute((C cmd)->getTransaction().dump(key).get(), ProtocolCommand.DUMP, args);
		}else{
			return execute((C cmd)->cmd.dump(key), ProtocolCommand.DUMP, args);
		}
	}

	@Override
	public Status restore(final byte[] key, final byte[] serializedValue, final int ttl){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put(
				"serializedValue", serializedValue).put("ttl", ttl);

		if(isTransaction()){
			return execute((C cmd)->statusForOK(getTransaction().restore(key, ttl, serializedValue).get()),
					ProtocolCommand.RESTORE, args);
		}else{
			return execute((C cmd)->statusForOK(cmd.restore(key, ttl, serializedValue)), ProtocolCommand.RESTORE,
					args);
		}
	}

	@Override
	public Status migrate(final byte[] key, final String host, final int port, final int db, final int timeout){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("host",
				host).put("port", port).put("key", key).put("db", db).put("timeout", timeout);

		return execute(new Executor<C, Status>() {

			@Override
			public Status execute(C cmd){
				String ret;

				if(isTransaction()){
					ret = getTransaction().migrate(host, port, key, db, timeout).get();
				}else{
					if(cmd instanceof Jedis){
						ret = ((Jedis) cmd).migrate(host, port, key, db, timeout);
					}else if(cmd instanceof ShardedJedis){
						ret = getShard((ShardedJedis) cmd, key).migrate(host, port, key, db, timeout);
					}else{
						throw new NotSupportedCommandException(ProtocolCommand.MIGRATE);
					}
				}

				return statusForOK(ret);
			}

		}, ProtocolCommand.MIGRATE, args);
	}

	@Override
	public Status migrate(final byte[] key, final String host, final int port, final int db, final int timeout,
			final KeyCommands.MigrateOperation migrateOperation){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("host",
				host).put("port", port).put("key", key).put("db", db).put("timeout", timeout).put("migrate",
				migrateOperation);

		return execute(new Executor<C, Status>() {

			@Override
			public Status execute(C cmd){
				String ret;

				if(isTransaction()){
					ret = getTransaction().migrate(host, port, db, timeout,
							JedisClientUtils.migrateOperationConvert(migrateOperation), key).get();
				}else{
					if(cmd instanceof Jedis){
						ret = ((Jedis) cmd).migrate(host, port, db, timeout,
								JedisClientUtils.migrateOperationConvert(migrateOperation), key);
					}else if(cmd instanceof ShardedJedis){
						ret = getShard((ShardedJedis) cmd, key).migrate(host, port, db, timeout,
								JedisClientUtils.migrateOperationConvert(migrateOperation), key);
					}else{
						throw new NotSupportedCommandException(ProtocolCommand.MIGRATE);
					}
				}

				return statusForOK(ret);
			}

		}, ProtocolCommand.MIGRATE, args);
	}

	@Override
	public Long del(final byte[]... keys){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("keys", keys);

		return execute(new Executor<C, Long>() {

			@Override
			public Long execute(C cmd){
				if(isTransaction()){
					return getTransaction().del(keys).get();
				}else{
					if(cmd instanceof Jedis){
						return ((Jedis) cmd).del(keys);
					}else if(cmd instanceof ShardedJedis){
						if(keys != null){
							long result = 0;

							for(byte[] key : keys){
								result += cmd.del(key);
							}

							return result;
						}

						return 0L;
					}else{
						throw new NotSupportedCommandException(ProtocolCommand.DEL);
					}
				}
			}

		}, ProtocolCommand.DEL, args);
	}

	@Override
	public Status move(final byte[] key, final int db){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("db", db);

		if(isTransaction()){
			return execute((C cmd)->statusForBool(getTransaction().move(key, db).get() > 0), ProtocolCommand.MOVE,
					args);
		}else{
			return execute((C cmd)->statusForBool(cmd.move(key, db) > 0), ProtocolCommand.MOVE, args);
		}
	}

}