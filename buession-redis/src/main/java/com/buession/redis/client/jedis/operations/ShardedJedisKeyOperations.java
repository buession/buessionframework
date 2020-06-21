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

import com.buession.lang.Status;
import com.buession.redis.client.RedisClient;
import com.buession.redis.client.jedis.JedisClientUtils;
import com.buession.redis.core.MigrateOperation;
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.Type;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.convert.JedisConverters;
import com.buession.redis.utils.ReturnUtils;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPipeline;
import redis.clients.jedis.SortingParams;
import redis.clients.jedis.params.MigrateParams;

import java.util.List;
import java.util.Set;

/**
 * @author Yong.Teng
 */
public class ShardedJedisKeyOperations extends AbstractKeyOperations<ShardedJedis, ShardedJedisPipeline> {

	public ShardedJedisKeyOperations(final RedisClient client){
		super(client);
	}

	@Override
	public Long del(final String... keys){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().del(keys[0])));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().del(keys)));
		}else{
			return execute((cmd)->{
				long result = 0;

				for(String key : keys){
					result += cmd.del(key);
				}

				return result;
			});
		}
	}

	@Override
	public Long del(final byte[]... keys){
		if(isPipeline()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().del(keys[0])));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().del(keys)));
		}else{
			return execute((cmd)->{
				long result = 0;

				for(byte[] key : keys){
					result += cmd.del(key);
				}

				return result;
			});
		}
	}

	@Override
	public byte[] dump(final byte[] key){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().dump(key)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().dump(key)));
		}else{
			return execute((cmd)->cmd.dump(key));
		}
	}

	@Override
	public boolean exists(final byte[] key){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().exists(key)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().exists(key)));
		}else{
			return execute((cmd)->cmd.exists(key));
		}
	}

	@Override
	public Status expire(final byte[] key, final int lifetime){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().expire(key, lifetime),
					JedisConverters.equalOneToStatusConverter()));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().expire(key, lifetime),
					JedisConverters.equalOneToStatusConverter()));
		}else{
			return execute((cmd)->ReturnUtils.statusForBool(cmd.expire(key, lifetime) == 1));
		}
	}

	@Override
	public Status expireAt(final byte[] key, final long unixTimestamp){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().expireAt(key, unixTimestamp),
					JedisConverters.equalOneToStatusConverter()));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().expireAt(key, unixTimestamp),
					JedisConverters.equalOneToStatusConverter()));
		}else{
			return execute((cmd)->ReturnUtils.statusForBool(cmd.expireAt(key, unixTimestamp) == 1));
		}
	}

	@Override
	public Status migrate(final String key, final String host, final int port, final int db, final int timeout){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().migrate(host, port, key, db, timeout),
					JedisConverters.okToStatusConverter()));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().migrate(host, port, key, db, timeout),
					JedisConverters.okToStatusConverter()));
		}else{
			return execute((cmd)->ReturnUtils.statusForOK(JedisClientUtils.getShard(cmd, key).migrate(host, port, key,
					db, timeout)));
		}
	}

	@Override
	public Status migrate(final byte[] key, final String host, final int port, final int db, final int timeout){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().migrate(host, port, key, db, timeout),
					JedisConverters.okToStatusConverter()));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().migrate(host, port, key, db, timeout),
					JedisConverters.okToStatusConverter()));
		}else{
			return execute((cmd)->ReturnUtils.statusForOK(JedisClientUtils.getShard(cmd, key).migrate(host, port, key,
					db, timeout)));
		}
	}

	@Override
	public Status migrate(final String key, final String host, final int port, final int db, final int timeout,
			final MigrateOperation operation){
		final MigrateParams migrateParams = MIGRATE_OPERATION_JEDIS_CONVERTER.convert(operation);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().migrate(host, port, key, db, timeout),
					JedisConverters.okToStatusConverter()));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().migrate(host, port, db, timeout,
					migrateParams, key), JedisConverters.okToStatusConverter()));
		}else{
			return execute((cmd)->ReturnUtils.statusForOK(JedisClientUtils.getShard(cmd, key).migrate(host, port, db,
					timeout, migrateParams, key)));
		}
	}

	@Override
	public Status migrate(final byte[] key, final String host, final int port, final int db, final int timeout,
			final MigrateOperation operation){
		final MigrateParams migrateParams = MIGRATE_OPERATION_JEDIS_CONVERTER.convert(operation);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().migrate(host, port, key, db, timeout),
					JedisConverters.okToStatusConverter()));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().migrate(host, port, db, timeout,
					migrateParams, key), JedisConverters.okToStatusConverter()));
		}else{
			return execute((cmd)->ReturnUtils.statusForOK(JedisClientUtils.getShard(cmd, key).migrate(host, port, db,
					timeout, migrateParams, key)));
		}
	}

	@Override
	public Status move(final byte[] key, final int db){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().move(key, db),
					JedisConverters.positiveLongNumberToStatusConverter()));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().move(key, db),
					JedisConverters.positiveLongNumberToStatusConverter()));
		}else{
			return execute((cmd)->ReturnUtils.statusForBool(cmd.move(key, db) > 0));
		}
	}

	@Override
	public Set<String> keys(final String pattern){
		commandAllNotSupportedException(ProtocolCommand.KEYS);
		return null;
	}

	@Override
	public Set<byte[]> keys(final byte[] pattern){
		commandAllNotSupportedException(ProtocolCommand.KEYS);
		return null;
	}

	@Override
	public Status persist(final byte[] key){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().persist(key),
					JedisConverters.positiveLongNumberToStatusConverter()));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().persist(key),
					JedisConverters.positiveLongNumberToStatusConverter()));
		}else{
			return execute((cmd)->ReturnUtils.statusForBool(cmd.persist(key) > 0));
		}
	}

	@Override
	public Status pExpire(final byte[] key, final int lifetime){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().pexpire(key, lifetime),
					JedisConverters.equalOneToStatusConverter()));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().pexpire(key, lifetime),
					JedisConverters.equalOneToStatusConverter()));
		}else{
			return execute((cmd)->ReturnUtils.statusForBool(cmd.pexpire(key, lifetime) == 1));
		}
	}

	@Override
	public Status pExpireAt(final byte[] key, final long unixTimestamp){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().pexpireAt(key, unixTimestamp),
					JedisConverters.equalOneToStatusConverter()));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().pexpireAt(key, unixTimestamp),
					JedisConverters.equalOneToStatusConverter()));
		}else{
			return execute((cmd)->ReturnUtils.statusForBool(cmd.pexpireAt(key, unixTimestamp) == 1));
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
		commandAllNotSupportedException(ProtocolCommand.PTTL);
		return null;
	}

	@Override
	public Status rename(final String key, final String newKey){
		commandAllNotSupportedException(ProtocolCommand.RENAME);
		return null;
	}

	@Override
	public Status rename(final byte[] key, final byte[] newKey){
		commandAllNotSupportedException(ProtocolCommand.RENAME);
		return null;
	}

	@Override
	public Status renameNx(final String key, final String newKey){
		commandAllNotSupportedException(ProtocolCommand.RENAMENX);
		return null;
	}

	@Override
	public Status renameNx(final byte[] key, final byte[] newKey){
		commandAllNotSupportedException(ProtocolCommand.RENAMENX);
		return null;
	}

	@Override
	public Status restore(final byte[] key, final byte[] serializedValue, final int ttl){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().restore(key, ttl, serializedValue),
					JedisConverters.okToStatusConverter()));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().restore(key, ttl, serializedValue),
					JedisConverters.okToStatusConverter()));
		}else{
			return execute((cmd)->ReturnUtils.statusForOK(cmd.restore(key, ttl, serializedValue)));
		}
	}

	@Override
	public ScanResult<List<String>> scan(final int cursor){
		commandAllNotSupportedException(ProtocolCommand.SCAN);
		return null;
	}

	@Override
	public ScanResult<List<String>> scan(final long cursor){
		commandAllNotSupportedException(ProtocolCommand.SCAN);
		return null;
	}

	@Override
	public ScanResult<List<String>> scan(final String cursor){
		commandAllNotSupportedException(ProtocolCommand.SCAN);
		return null;
	}

	@Override
	public ScanResult<List<byte[]>> scan(final byte[] cursor){
		commandAllNotSupportedException(ProtocolCommand.SCAN);
		return null;
	}

	@Override
	public ScanResult<List<String>> scan(final int cursor, final String pattern){
		commandAllNotSupportedException(ProtocolCommand.SCAN);
		return null;
	}

	@Override
	public ScanResult<List<byte[]>> scan(final int cursor, final byte[] pattern){
		commandAllNotSupportedException(ProtocolCommand.SCAN);
		return null;
	}

	@Override
	public ScanResult<List<String>> scan(final long cursor, final String pattern){
		commandAllNotSupportedException(ProtocolCommand.SCAN);
		return null;
	}

	@Override
	public ScanResult<List<byte[]>> scan(final long cursor, final byte[] pattern){
		commandAllNotSupportedException(ProtocolCommand.SCAN);
		return null;
	}

	@Override
	public ScanResult<List<String>> scan(final String cursor, final String pattern){
		commandAllNotSupportedException(ProtocolCommand.SCAN);
		return null;
	}

	@Override
	public ScanResult<List<byte[]>> scan(final byte[] cursor, final byte[] pattern){
		commandAllNotSupportedException(ProtocolCommand.SCAN);
		return null;
	}

	@Override
	public ScanResult<List<String>> scan(final int cursor, final int count){
		commandAllNotSupportedException(ProtocolCommand.SCAN);
		return null;
	}

	@Override
	public ScanResult<List<String>> scan(final long cursor, final int count){
		commandAllNotSupportedException(ProtocolCommand.SCAN);
		return null;
	}

	@Override
	public ScanResult<List<String>> scan(final String cursor, final int count){
		commandAllNotSupportedException(ProtocolCommand.SCAN);
		return null;
	}

	@Override
	public ScanResult<List<byte[]>> scan(final byte[] cursor, final int count){
		commandAllNotSupportedException(ProtocolCommand.SCAN);
		return null;
	}

	@Override
	public ScanResult<List<String>> scan(final int cursor, final String pattern, final int count){
		commandAllNotSupportedException(ProtocolCommand.SCAN);
		return null;
	}

	@Override
	public ScanResult<List<byte[]>> scan(final int cursor, final byte[] pattern, final int count){
		commandAllNotSupportedException(ProtocolCommand.SCAN);
		return null;
	}

	@Override
	public ScanResult<List<String>> scan(final long cursor, final String pattern, final int count){
		commandAllNotSupportedException(ProtocolCommand.SCAN);
		return null;
	}

	@Override
	public ScanResult<List<byte[]>> scan(final long cursor, final byte[] pattern, final int count){
		commandAllNotSupportedException(ProtocolCommand.SCAN);
		return null;
	}

	@Override
	public ScanResult<List<String>> scan(final String cursor, final String pattern, final int count){
		commandAllNotSupportedException(ProtocolCommand.SCAN);
		return null;
	}

	@Override
	public ScanResult<List<byte[]>> scan(final byte[] cursor, final byte[] pattern, final int count){
		commandAllNotSupportedException(ProtocolCommand.SCAN);
		return null;
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
		final SortingParams sortingParams = SORT_ARGUMENT_JEDIS_CONVERTER.convert(sortArgument);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().sort(key, sortingParams)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().sort(key, sortingParams)));
		}else{
			return execute((cmd)->cmd.sort(key, sortingParams));
		}
	}

	@Override
	public Long sort(final String key, final String destKey){
		commandAllNotSupportedException(ProtocolCommand.SORT);
		return null;
	}

	@Override
	public Long sort(final byte[] key, final byte[] destKey){
		commandAllNotSupportedException(ProtocolCommand.SORT);
		return null;
	}

	@Override
	public Long sort(final String key, final String destKey, final SortArgument sortArgument){
		commandAllNotSupportedException(ProtocolCommand.SORT);
		return null;
	}

	@Override
	public Long sort(final byte[] key, final byte[] destKey, final SortArgument sortArgument){
		commandAllNotSupportedException(ProtocolCommand.SORT);
		return null;
	}

	@Override
	public Long touch(final String... keys){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().touch(keys[0])));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().touch(keys)));
		}else{
			return execute((cmd)->{
				long result = 0;

				for(String key : keys){
					result += cmd.touch(key);
				}

				return result;
			});
		}
	}

	@Override
	public Long touch(final byte[]... keys){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().touch(keys[0])));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().touch(keys)));
		}else{
			return execute((cmd)->{
				long result = 0;

				for(byte[] key : keys){
					result += cmd.touch(key);
				}

				return result;
			});
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
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().type(key),
					JedisConverters.enumConverter(Type.class)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().type(key),
					JedisConverters.enumConverter(Type.class)));
		}else{
			return execute((cmd)->ReturnUtils.enumValueOf(cmd.type(key), Type.class));
		}
	}

	@Override
	public Long unlink(final String... keys){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().unlink(keys[0])));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().unlink(keys)));
		}else{
			return execute((cmd)->{
				long result = 0;

				for(String key : keys){
					result += cmd.unlink(key);
				}

				return result;
			});
		}
	}

	@Override
	public Long unlink(final byte[]... keys){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().unlink(keys[0])));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().unlink(keys)));
		}else{
			return execute((cmd)->{
				long result = 0;

				for(byte[] key : keys){
					result += cmd.unlink(key);
				}

				return result;
			});
		}
	}

}
