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
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.command.CommandNotSupported;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.convert.OkStatusConverter;
import com.buession.redis.core.convert.jedis.ScanResultConverter;
import com.buession.redis.core.internal.jedis.JedisScanParams;
import com.buession.redis.exception.RedisExceptionUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Yong.Teng
 */
public class JedisSentinelHashOperations extends AbstractHashOperations {

	public JedisSentinelHashOperations(final JedisSentinelClient client){
		super(client);
	}

	@Override
	public Long hDel(final byte[] key, final byte[]... fields){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().hdel(key, fields)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().hdel(key, fields)));
		}else{
			return execute((cmd)->cmd.hdel(key, fields));
		}
	}

	@Override
	public boolean hExists(final byte[] key, final byte[] field){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().hexists(key, field)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().hexists(key, field)));
		}else{
			return execute((cmd)->cmd.hexists(key, field));
		}
	}

	@Override
	public byte[] hGet(final byte[] key, final byte[] field){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().hget(key, field)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().hget(key, field)));
		}else{
			return execute((cmd)->cmd.hget(key, field));
		}
	}

	@Override
	public Map<byte[], byte[]> hGetAll(final byte[] key){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().hgetAll(key)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().hgetAll(key)));
		}else{
			return execute((cmd)->cmd.hgetAll(key));
		}
	}

	@Override
	public Long hIncrBy(final byte[] key, final byte[] field, final long value){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().hincrBy(key, field, value)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().hincrBy(key, field, value)));
		}else{
			return execute((cmd)->cmd.hincrBy(key, field, value));
		}
	}

	@Override
	public Double hIncrByFloat(final byte[] key, final byte[] field, final double value){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().hincrByFloat(key, field, value)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().hincrByFloat(key, field, value)));
		}else{
			return execute((cmd)->cmd.hincrByFloat(key, field, value));
		}
	}

	@Override
	public Set<byte[]> hKeys(final byte[] key){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().hkeys(key)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().hkeys(key)));
		}else{
			return execute((cmd)->cmd.hkeys(key));
		}
	}

	@Override
	public Long hLen(final byte[] key){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().hlen(key)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().hlen(key)));
		}else{
			return execute((cmd)->cmd.hlen(key));
		}
	}

	@Override
	public List<byte[]> hMGet(final byte[] key, final byte[]... fields){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().hmget(key, fields)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().hmget(key, fields)));
		}else{
			return execute((cmd)->cmd.hmget(key, fields));
		}
	}

	@Override
	public Status hMSet(final byte[] key, final Map<byte[], byte[]> data){
		final OkStatusConverter converter = new OkStatusConverter();

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().hmset(key, data), converter));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().hmset(key, data), converter));
		}else{
			return execute((cmd)->cmd.hmset(key, data), converter);
		}
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor){
		RedisExceptionUtils.commandNotSupportedException(ProtocolCommand.HSCAN,
				CommandNotSupported.PIPELINE | CommandNotSupported.TRANSACTION, client.getConnection());
		return execute((cmd)->new ScanResultConverter.MapScanResultExposeConverter<byte[], byte[]>().convert(
				cmd.hscan(key, cursor)));
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor, final byte[] pattern){
		RedisExceptionUtils.commandNotSupportedException(ProtocolCommand.HSCAN,
				CommandNotSupported.PIPELINE | CommandNotSupported.TRANSACTION, client.getConnection());
		return execute((cmd)->new ScanResultConverter.MapScanResultExposeConverter<byte[], byte[]>().convert(
				cmd.hscan(key, cursor, new JedisScanParams(pattern))));
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor, final long count){
		RedisExceptionUtils.commandNotSupportedException(ProtocolCommand.HSCAN,
				CommandNotSupported.PIPELINE | CommandNotSupported.TRANSACTION, client.getConnection());
		return execute((cmd)->new ScanResultConverter.MapScanResultExposeConverter<byte[], byte[]>().convert(
				cmd.hscan(key, cursor, new JedisScanParams((int) count))));
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor, final byte[] pattern,
												 final long count){
		RedisExceptionUtils.commandNotSupportedException(ProtocolCommand.HSCAN,
				CommandNotSupported.PIPELINE | CommandNotSupported.TRANSACTION, client.getConnection());
		return execute((cmd)->new ScanResultConverter.MapScanResultExposeConverter<byte[], byte[]>().convert(
				cmd.hscan(key, cursor, new JedisScanParams(pattern, (int) count))));
	}

	@Override
	public Status hSet(final byte[] key, final byte[] field, final byte[] value){
		final PredicateStatusConverter<Long> converter = new PredicateStatusConverter<>((val)->val > 0);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().hset(key, field, value), converter));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().hset(key, field, value), converter));
		}else{
			return execute((cmd)->cmd.hset(key, field, value), converter);
		}
	}

	@Override
	public Status hSetNx(final byte[] key, final byte[] field, final byte[] value){
		final PredicateStatusConverter<Long> converter = new PredicateStatusConverter<>((val)->val > 0);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().hsetnx(key, field, value), converter));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().hsetnx(key, field, value), converter));
		}else{
			return execute((cmd)->cmd.hsetnx(key, field, value), converter);
		}
	}

	@Override
	public Long hStrLen(final byte[] key, final byte[] field){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().hstrlen(key, field)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().hstrlen(key, field)));
		}else{
			return execute((cmd)->cmd.hstrlen(key, field));
		}
	}

	@Override
	public List<byte[]> hVals(final byte[] key){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().hvals(key)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().hvals(key)));
		}else{
			return execute((cmd)->cmd.hvals(key));
		}
	}

	@Override
	public byte[] hrandfield(final byte[] key){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().hrandfield(key)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().hrandfield(key)));
		}else{
			return execute((cmd)->cmd.hrandfield(key));
		}
	}

	@Override
	public List<byte[]> hrandfield(final byte[] key, final long count){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().hrandfield(key, count)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().hrandfield(key, count)));
		}else{
			return execute((cmd)->cmd.hrandfield(key, count));
		}
	}

	@Override
	public Map<byte[], byte[]> hrandfieldWithValues(final byte[] key, final long count){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().hrandfieldWithValues(key, count)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().hrandfieldWithValues(key, count)));
		}else{
			return execute((cmd)->cmd.hrandfieldWithValues(key, count));
		}
	}

}
