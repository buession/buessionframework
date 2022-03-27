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
import com.buession.redis.core.MigrateOperation;
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.Type;
import com.buession.redis.core.command.CommandNotSupported;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.convert.OkStatusConverter;
import com.buession.redis.core.convert.jedis.MigrateOperationConverter;
import com.buession.redis.core.convert.jedis.ScanResultConverter;
import com.buession.redis.core.convert.jedis.SortArgumentConverter;
import com.buession.redis.core.internal.jedis.JedisScanParams;
import com.buession.redis.exception.RedisExceptionUtils;
import redis.clients.jedis.SortingParams;
import redis.clients.jedis.params.MigrateParams;

import java.util.List;
import java.util.Set;

/**
 * @author Yong.Teng
 */
public class JedisClusterKeyOperations extends AbstractKeyOperations {

	public JedisClusterKeyOperations(final JedisClusterClient client){
		super(client);
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
		final PredicateStatusConverter<Long> converter = new PredicateStatusConverter<>((val)->val == 1);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().expire(key, lifetime), converter));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().expire(key, lifetime), converter));
		}else{
			return execute((cmd)->cmd.expire(key, lifetime), converter);
		}
	}

	@Override
	public Status expireAt(final byte[] key, final long unixTimestamp){
		final PredicateStatusConverter<Long> converter = new PredicateStatusConverter<>((val)->val == 1);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().expireAt(key, unixTimestamp), converter));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().expireAt(key, unixTimestamp), converter));
		}else{
			return execute((cmd)->cmd.expireAt(key, unixTimestamp), converter);
		}
	}

	@Override
	public Status migrate(final String key, final String host, final int port, final int db, final int timeout){
		final OkStatusConverter converter = new OkStatusConverter();

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().migrate(host, port, key, db, timeout),
					converter));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().migrate(host, port, key, db, timeout),
					converter));
		}else{
			return execute((cmd)->cmd.migrate(host, port, key, db, timeout), converter);
		}
	}

	@Override
	public Status migrate(final byte[] key, final String host, final int port, final int db, final int timeout){
		final OkStatusConverter converter = new OkStatusConverter();

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().migrate(host, port, key, db, timeout),
					converter));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().migrate(host, port, key, db, timeout),
					converter));
		}else{
			return execute((cmd)->cmd.migrate(host, port, key, db, timeout), converter);
		}
	}

	@Override
	public Status migrate(final String key, final String host, final int port, final int db, final int timeout,
						  final MigrateOperation operation){
		final MigrateParams migrateParams = new MigrateOperationConverter.MigrateOperationJedisConverter().convert(
				operation);
		final OkStatusConverter converter = new OkStatusConverter();

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().migrate(host, port, key, db, timeout),
					converter));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().migrate(host, port, db, timeout,
					migrateParams, key), converter));
		}else{
			return execute((cmd)->cmd.migrate(host, port, db, timeout, migrateParams, key), converter);
		}
	}

	@Override
	public Status migrate(final byte[] key, final String host, final int port, final int db, final int timeout,
						  final MigrateOperation operation){
		final MigrateParams migrateParams = new MigrateOperationConverter.MigrateOperationJedisConverter().convert(
				operation);
		final OkStatusConverter converter = new OkStatusConverter();

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().migrate(host, port, key, db, timeout),
					converter));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().migrate(host, port, db, timeout,
					migrateParams, key), converter));
		}else{
			return execute((cmd)->cmd.migrate(host, port, db, timeout, migrateParams, key), converter);
		}
	}

	@Override
	public Status move(final byte[] key, final int db){
		final PredicateStatusConverter<Long> converter = new PredicateStatusConverter<>((val)->val > 1);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().move(key, db), converter));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().move(key, db), converter));
		}else{
			return execute((cmd)->cmd.move(key, db), converter);
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
