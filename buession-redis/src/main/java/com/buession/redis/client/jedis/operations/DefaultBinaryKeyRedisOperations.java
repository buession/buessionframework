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
import com.buession.redis.utils.ReturnUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.commands.BinaryJedisCommands;

import java.util.List;
import java.util.Set;

/**
 * @author Yong.Teng
 */
public class DefaultBinaryKeyRedisOperations<C extends BinaryJedisCommands> extends AbstractJedisBinaryRedisOperations implements JedisBinaryKeyRedisOperations {

	public DefaultBinaryKeyRedisOperations(final JedisRedisClient client){
		super(client);
	}

	@Override
	public boolean exists(final byte[] key){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key);

		if(isTransaction()){
			return execute((C jc)->getTransaction().exists(key).get(), ProtocolCommand.EXISTS, arguments);
		}else{
			return execute((C jc)->jc.exists(key), ProtocolCommand.EXISTS, arguments);
		}
	}

	@Override
	public Type type(final byte[] key){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key);

		if(isTransaction()){
			return execute((C jc)->ReturnUtils.enumValueOf(getTransaction().type(key).get(), Type.class),
					ProtocolCommand.TYPE, arguments);
		}else{
			return execute((C jc)->ReturnUtils.enumValueOf(jc.type(key), Type.class), ProtocolCommand.TYPE, arguments);
		}
	}

	@Override
	public Status rename(final byte[] key, final byte[] newKey){
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
						ret = ((Jedis) jc).rename(key, newKey);
					}else{
						throw new NotSupportedCommandException(ProtocolCommand.RENAME);
					}
				}

				return ReturnUtils.statusForOK(ret);
			}

		}, ProtocolCommand.RENAME, arguments);
	}

	@Override
	public Status renameNx(final byte[] key, final byte[] newKey){
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
	public Set<byte[]> keys(final byte[] pattern){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("pattern", pattern);

		return execute(new Executor<C, Set<byte[]>>() {

			@Override
			public Set<byte[]> execute(C jc){
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
	public Status expire(final byte[] key, final int lifetime){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"lifetime", lifetime);

		if(isTransaction()){
			return execute((C jc)->ReturnUtils.statusForBool(getTransaction().expire(key, lifetime).get() == 1),
					ProtocolCommand.EXPIRE, arguments);
		}else{
			return execute((C jc)->ReturnUtils.statusForBool(jc.expire(key, lifetime) == 1), ProtocolCommand.EXPIRE,
					arguments);
		}
	}

	@Override
	public Status expireAt(final byte[] key, final long unixTimestamp){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"unixTimestamp", unixTimestamp);

		if(isTransaction()){
			return execute((C jc)->ReturnUtils.statusForBool(getTransaction().expireAt(key, unixTimestamp).get() == 1)
					, ProtocolCommand.EXPIREAT, arguments);
		}else{
			return execute((C jc)->ReturnUtils.statusForBool(jc.expireAt(key, unixTimestamp) == 1),
					ProtocolCommand.EXPIREAT, arguments);
		}
	}

	@Override
	public Status pExpire(final byte[] key, final int lifetime){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"lifetime", lifetime);

		if(isTransaction()){
			return execute((C jc)->ReturnUtils.statusForBool(getTransaction().pexpire(key, lifetime).get() == 1),
					ProtocolCommand.PEXPIRE, arguments);
		}else{
			return execute((C jc)->ReturnUtils.statusForBool(jc.pexpire(key, lifetime) == 1), ProtocolCommand.PEXPIRE,
					arguments);
		}
	}

	@Override
	public Status pExpireAt(final byte[] key, final long unixTimestamp){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"unixTimestamp", unixTimestamp);

		if(isTransaction()){
			return execute((C jc)->ReturnUtils.statusForBool(getTransaction().pexpireAt(key, unixTimestamp).get() == 1), ProtocolCommand.PEXPIREAT, arguments);
		}else{
			return execute((C jc)->ReturnUtils.statusForBool(jc.pexpireAt(key, unixTimestamp) == 1),
					ProtocolCommand.PEXPIREAT, arguments);
		}
	}

	@Override
	public Long ttl(final byte[] key){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key);

		if(isTransaction()){
			return execute((C jc)->getTransaction().ttl(key).get(), ProtocolCommand.TTL, arguments);
		}else{
			return execute((C jc)->jc.ttl(key), ProtocolCommand.TTL, arguments);
		}
	}

	@Override
	public Long pTtl(final byte[] key){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key);

		if(isTransaction()){
			return execute((C jc)->getTransaction().pttl(key).get(), ProtocolCommand.PTTL, arguments);
		}else{
			return execute((C jc)->jc.pttl(key), ProtocolCommand.PTTL, arguments);
		}
	}

	@Override
	public Status persist(final byte[] key){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key);

		if(isTransaction()){
			return execute((C jc)->ReturnUtils.statusForBool(getTransaction().persist(key).get() > 0),
					ProtocolCommand.PERSIST, arguments);
		}else{
			return execute((C jc)->ReturnUtils.statusForBool(jc.persist(key) > 0), ProtocolCommand.PERSIST, arguments);
		}
	}

	@Override
	public ScanResult<List<byte[]>> scan(final byte[] cursor){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("cursor", cursor);

		return execute(new Executor<C, ScanResult<List<byte[]>>>() {

			@Override
			public ScanResult<List<byte[]>> execute(C jc){
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
	public ScanResult<List<byte[]>> scan(final byte[] cursor, final byte[] pattern){
		final OperationsCommandArguments arguments =
				OperationsCommandArguments.getInstance().put("cursor", cursor).put("pattern", pattern);

		return execute(new Executor<C, ScanResult<List<byte[]>>>() {

			@Override
			public ScanResult<List<byte[]>> execute(C jc){
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
	public ScanResult<List<byte[]>> scan(final byte[] cursor, final int count){
		final OperationsCommandArguments arguments =
				OperationsCommandArguments.getInstance().put("cursor", cursor).put("count", count);

		return execute(new Executor<C, ScanResult<List<byte[]>>>() {

			@Override
			public ScanResult<List<byte[]>> execute(C jc){
				if(isTransaction()){
					throw new NotSupportedCommandException(ProtocolCommand.SCAN);
				}else{
					if(jc instanceof Jedis){
						return JedisClientUtils.listScanResultDeconvert(((Jedis) jc).scan(cursor,
								new JedisScanParams(count)));
					}else{
						throw new NotSupportedCommandException(ProtocolCommand.SCAN);
					}
				}
			}

		}, ProtocolCommand.SCAN, arguments);
	}

	@Override
	public ScanResult<List<byte[]>> scan(final byte[] cursor, final byte[] pattern, final int count){
		final OperationsCommandArguments arguments =
				OperationsCommandArguments.getInstance().put("cursor", cursor).put("pattern", pattern).put("count",
						count);

		return execute(new Executor<C, ScanResult<List<byte[]>>>() {

			@Override
			public ScanResult<List<byte[]>> execute(C jc){
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
	public List<byte[]> sort(final byte[] key){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key);

		if(isTransaction()){
			return execute((C jc)->getTransaction().sort(key).get(), ProtocolCommand.SORT, arguments);
		}else{
			return execute((C jc)->jc.sort(key), ProtocolCommand.SORT, arguments);
		}
	}

	@Override
	public List<byte[]> sort(final byte[] key, final KeyCommands.SortArgument sortArgument){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key);

		if(isTransaction()){
			return execute((C jc)->getTransaction().sort(key, JedisClientUtils.sortArgumentConvert(sortArgument)).get(), ProtocolCommand.SORT, arguments);
		}else{
			return execute((C jc)->jc.sort(key, JedisClientUtils.sortArgumentConvert(sortArgument)),
					ProtocolCommand.SORT, arguments);
		}
	}

	@Override
	public Long sort(final byte[] key, final byte[] destKey){
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
	public Long sort(final byte[] key, final byte[] destKey, final KeyCommands.SortArgument sortArgument){
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
	public byte[] dump(final byte[] key){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key);

		if(isTransaction()){
			return execute((C jc)->getTransaction().dump(key).get(), ProtocolCommand.DUMP, arguments);
		}else{
			return execute((C jc)->jc.dump(key), ProtocolCommand.DUMP, arguments);
		}
	}

	@Override
	public Status restore(final byte[] key, final byte[] serializedValue, final int ttl){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"serializedValue", serializedValue).put("ttl", ttl);

		if(isTransaction()){
			return execute((C jc)->ReturnUtils.statusForOK(getTransaction().restore(key, ttl, serializedValue).get()),
					ProtocolCommand.RESTORE, arguments);
		}else{
			return execute((C jc)->ReturnUtils.statusForOK(jc.restore(key, ttl, serializedValue)),
					ProtocolCommand.RESTORE, arguments);
		}
	}

	@Override
	public Status migrate(final byte[] key, final String host, final int port, final int db, final int timeout){
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
	public Status migrate(final byte[] key, final String host, final int port, final int db, final int timeout,
			final KeyCommands.MigrateOperation migrateOperation){
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
	public Long del(final byte[]... keys){
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

							for(byte[] key : keys){
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
	public Status move(final byte[] key, final int db){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put("db"
				, db);

		if(isTransaction()){
			return execute((C jc)->ReturnUtils.statusForBool(getTransaction().move(key, db).get() > 0),
					ProtocolCommand.MOVE, arguments);
		}else{
			return execute((C jc)->ReturnUtils.statusForBool(jc.move(key, db) > 0), ProtocolCommand.MOVE, arguments);
		}
	}

}