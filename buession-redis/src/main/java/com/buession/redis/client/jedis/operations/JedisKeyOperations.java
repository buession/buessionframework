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

import com.buession.core.utils.NumberUtils;
import com.buession.lang.Status;
import com.buession.redis.client.jedis.JedisRedisClient;
import com.buession.redis.core.MigrateOperation;
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.Type;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.convert.JedisConverters;
import com.buession.redis.core.jedis.JedisScanParams;
import com.buession.redis.utils.ReturnUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.SortingParams;
import redis.clients.jedis.params.MigrateParams;

import java.util.List;
import java.util.Set;

/**
 * @author Yong.Teng
 */
public class JedisKeyOperations extends AbstractKeyOperations<Jedis, Pipeline> {

	public JedisKeyOperations(final JedisRedisClient<Jedis> client){
		super(client, null);
	}

	@Override
	public Long del(final String... keys){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().del(keys[0])));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().del(keys)));
		}else{
			return execute((cmd)->cmd.del(keys));
		}
	}

	@Override
	public Long del(final byte[]... keys){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().del(keys[0])));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().del(keys)));
		}else{
			return execute((cmd)->cmd.del(keys));
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
					OK_TO_STATUS_CONVERTER));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().migrate(host, port, key, db, timeout),
					OK_TO_STATUS_CONVERTER));
		}else{
			return execute((cmd)->ReturnUtils.statusForOK(cmd.migrate(host, port, key, db, timeout)));
		}
	}

	@Override
	public Status migrate(final byte[] key, final String host, final int port, final int db, final int timeout){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().migrate(host, port, key, db, timeout),
					OK_TO_STATUS_CONVERTER));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().migrate(host, port, key, db, timeout),
					OK_TO_STATUS_CONVERTER));
		}else{
			return execute((cmd)->ReturnUtils.statusForOK(cmd.migrate(host, port, key, db, timeout)));
		}
	}

	@Override
	public Status migrate(final String key, final String host, final int port, final int db, final int timeout,
			final MigrateOperation operation){
		final MigrateParams migrateParams = MIGRATE_OPERATION_JEDIS_CONVERTER.convert(operation);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().migrate(host, port, key, db, timeout),
					OK_TO_STATUS_CONVERTER));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().migrate(host, port, db, timeout,
					migrateParams, key), OK_TO_STATUS_CONVERTER));
		}else{
			return execute((cmd)->ReturnUtils.statusForOK(cmd.migrate(host, port, db, timeout, migrateParams, key)));
		}
	}

	@Override
	public Status migrate(final byte[] key, final String host, final int port, final int db, final int timeout,
			final MigrateOperation operation){
		final MigrateParams migrateParams = MIGRATE_OPERATION_JEDIS_CONVERTER.convert(operation);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().migrate(host, port, key, db, timeout),
					OK_TO_STATUS_CONVERTER));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().migrate(host, port, db, timeout,
					migrateParams, key), OK_TO_STATUS_CONVERTER));
		}else{
			return execute((cmd)->ReturnUtils.statusForOK(cmd.migrate(host, port, db, timeout, migrateParams, key)));
		}
	}

	@Override
	public Status move(final byte[] key, final int db){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().move(key, db),
					POSITIVE_LONG_NUMBER_TO_STATUS_CONVERTER));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().move(key, db),
					POSITIVE_LONG_NUMBER_TO_STATUS_CONVERTER));
		}else{
			return execute((cmd)->ReturnUtils.statusForBool(cmd.move(key, db) > 0));
		}
	}

	@Override
	public Set<String> keys(final String pattern){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().keys(pattern)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().keys(pattern)));
		}else{
			return execute((cmd)->cmd.keys(pattern));
		}
	}

	@Override
	public Set<byte[]> keys(final byte[] pattern){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().keys(pattern)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().keys(pattern)));
		}else{
			return execute((cmd)->cmd.keys(pattern));
		}
	}

	@Override
	public Status persist(final byte[] key){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().persist(key),
					POSITIVE_LONG_NUMBER_TO_STATUS_CONVERTER));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().persist(key),
					POSITIVE_LONG_NUMBER_TO_STATUS_CONVERTER));
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
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().rename(key, newKey), OK_TO_STATUS_CONVERTER));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().rename(key, newKey),
					OK_TO_STATUS_CONVERTER));
		}else{
			return execute((cmd)->ReturnUtils.statusForOK(cmd.rename(key, newKey)));
		}
	}

	@Override
	public Status rename(final byte[] key, final byte[] newKey){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().rename(key, newKey), OK_TO_STATUS_CONVERTER));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().rename(key, newKey),
					OK_TO_STATUS_CONVERTER));
		}else{
			return execute((cmd)->ReturnUtils.statusForOK(cmd.rename(key, newKey)));
		}
	}

	@Override
	public Status renameNx(final String key, final String newKey){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().renamenx(key, newKey),
					POSITIVE_LONG_NUMBER_TO_STATUS_CONVERTER));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().renamenx(key, newKey),
					POSITIVE_LONG_NUMBER_TO_STATUS_CONVERTER));
		}else{
			return execute((cmd)->ReturnUtils.statusForBool(cmd.renamenx(key, newKey) > 0));
		}
	}

	@Override
	public Status renameNx(final byte[] key, final byte[] newKey){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().renamenx(key, newKey),
					POSITIVE_LONG_NUMBER_TO_STATUS_CONVERTER));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().renamenx(key, newKey),
					POSITIVE_LONG_NUMBER_TO_STATUS_CONVERTER));
		}else{
			return execute((cmd)->ReturnUtils.statusForBool(cmd.renamenx(key, newKey) > 0));
		}
	}

	@Override
	public Status restore(final byte[] key, final byte[] serializedValue, final int ttl){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().restore(key, ttl, serializedValue),
					OK_TO_STATUS_CONVERTER));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().restore(key, ttl, serializedValue),
					OK_TO_STATUS_CONVERTER));
		}else{
			return execute((cmd)->ReturnUtils.statusForOK(cmd.restore(key, ttl, serializedValue)));
		}
	}

	@Override
	public ScanResult<List<String>> scan(final int cursor){
		return scan(Integer.toString(cursor));
	}

	@Override
	public ScanResult<List<String>> scan(final long cursor){
		return scan(Long.toString(cursor));
	}

	@Override
	public ScanResult<List<String>> scan(final String cursor){
		pipelineAndTransactionNotSupportedException(ProtocolCommand.SCAN);
		return execute((cmd)->STRING_LIST_SCANRESULT_EXPOSE_CONVERTER.convert(cmd.scan(cursor)));
	}

	@Override
	public ScanResult<List<byte[]>> scan(final byte[] cursor){
		pipelineAndTransactionNotSupportedException(ProtocolCommand.SCAN);
		return execute((cmd)->BINARY_LIST_SCANRESULT_EXPOSE_CONVERTER.convert(cmd.scan(cursor)));
	}

	@Override
	public ScanResult<List<String>> scan(final int cursor, final String pattern){
		return scan(Integer.toString(cursor), pattern);
	}

	@Override
	public ScanResult<List<byte[]>> scan(final int cursor, final byte[] pattern){
		return scan(NumberUtils.int2bytes(cursor), pattern);
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
		pipelineAndTransactionNotSupportedException(ProtocolCommand.SCAN);
		return execute((cmd)->STRING_LIST_SCANRESULT_EXPOSE_CONVERTER.convert(cmd.scan(cursor,
				new JedisScanParams(pattern))));
	}

	@Override
	public ScanResult<List<byte[]>> scan(final byte[] cursor, final byte[] pattern){
		pipelineAndTransactionNotSupportedException(ProtocolCommand.SCAN);
		return execute((cmd)->BINARY_LIST_SCANRESULT_EXPOSE_CONVERTER.convert(cmd.scan(cursor,
				new JedisScanParams(pattern))));
	}

	@Override
	public ScanResult<List<String>> scan(final int cursor, final int count){
		return scan(Integer.toString(cursor), count);
	}

	@Override
	public ScanResult<List<String>> scan(final long cursor, final int count){
		return scan(Long.toString(cursor), count);
	}

	@Override
	public ScanResult<List<String>> scan(final String cursor, final int count){
		pipelineAndTransactionNotSupportedException(ProtocolCommand.SCAN);
		return execute((cmd)->STRING_LIST_SCANRESULT_EXPOSE_CONVERTER.convert(cmd.scan(cursor,
				new JedisScanParams(count))));
	}

	@Override
	public ScanResult<List<byte[]>> scan(final byte[] cursor, final int count){
		pipelineAndTransactionNotSupportedException(ProtocolCommand.SCAN);
		return execute((cmd)->BINARY_LIST_SCANRESULT_EXPOSE_CONVERTER.convert(cmd.scan(cursor,
				new JedisScanParams(count))));
	}

	@Override
	public ScanResult<List<String>> scan(final int cursor, final String pattern, final int count){
		return scan(Integer.toString(cursor), pattern, count);
	}

	@Override
	public ScanResult<List<byte[]>> scan(final int cursor, final byte[] pattern, final int count){
		return scan(NumberUtils.int2bytes(cursor), pattern, count);
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
		pipelineAndTransactionNotSupportedException(ProtocolCommand.SCAN);
		return execute((cmd)->STRING_LIST_SCANRESULT_EXPOSE_CONVERTER.convert(cmd.scan(cursor,
				new JedisScanParams(pattern, count))));
	}

	@Override
	public ScanResult<List<byte[]>> scan(final byte[] cursor, final byte[] pattern, final int count){
		pipelineAndTransactionNotSupportedException(ProtocolCommand.SCAN);
		return execute((cmd)->BINARY_LIST_SCANRESULT_EXPOSE_CONVERTER.convert(cmd.scan(cursor,
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
		final SortingParams soringParams = SORT_ARGUMENT_JEDIS_CONVERTER.convert(sortArgument);

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
		final SortingParams soringParams = SORT_ARGUMENT_JEDIS_CONVERTER.convert(sortArgument);

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
		final SortingParams soringParams = SORT_ARGUMENT_JEDIS_CONVERTER.convert(sortArgument);

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

}
