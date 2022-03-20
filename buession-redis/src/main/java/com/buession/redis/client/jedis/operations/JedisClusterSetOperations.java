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
import com.buession.redis.client.jedis.JedisClient;
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.convert.jedis.ScanResultConverter;
import com.buession.redis.core.jedis.JedisScanParams;
import com.buession.redis.exception.RedisExceptionUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import java.util.List;
import java.util.Set;

/**
 * @author Yong.Teng
 */
public class JedisSetOperations extends AbstractSetOperations<Jedis, Pipeline> {

	public JedisSetOperations(final JedisClient client){
		super(client);
	}

	@Override
	public Long sAdd(final byte[] key, final byte[]... members){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().sadd(key, members)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().sadd(key, members)));
		}else{
			return execute((cmd)->cmd.sadd(key, members));
		}
	}

	@Override
	public Long sCard(final byte[] key){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().scard(key)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().scard(key)));
		}else{
			return execute((cmd)->cmd.scard(key));
		}
	}

	@Override
	public Set<String> sDiff(final String... keys){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().sdiff(keys)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().sdiff(keys)));
		}else{
			return execute((cmd)->cmd.sdiff(keys));
		}
	}

	@Override
	public Set<byte[]> sDiff(final byte[]... keys){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().sdiff(keys)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().sdiff(keys)));
		}else{
			return execute((cmd)->cmd.sdiff(keys));
		}
	}

	@Override
	public Long sDiffStore(final String destKey, final String... keys){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().sdiffstore(destKey, keys)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().sdiffstore(destKey, keys)));
		}else{
			return execute((cmd)->cmd.sdiffstore(destKey, keys));
		}
	}

	@Override
	public Long sDiffStore(final byte[] destKey, final byte[]... keys){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().sdiffstore(destKey, keys)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().sdiffstore(destKey, keys)));
		}else{
			return execute((cmd)->cmd.sdiffstore(destKey, keys));
		}
	}

	@Override
	public Set<String> sInter(final String... keys){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().sinter(keys)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().sinter(keys)));
		}else{
			return execute((cmd)->cmd.sinter(keys));
		}
	}

	@Override
	public Set<byte[]> sInter(final byte[]... keys){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().sinter(keys)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().sinter(keys)));
		}else{
			return execute((cmd)->cmd.sinter(keys));
		}
	}

	@Override
	public Long sInterStore(final String destKey, final String... keys){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().sinterstore(destKey, keys)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().sinterstore(destKey, keys)));
		}else{
			return execute((cmd)->cmd.sinterstore(destKey, keys));
		}
	}

	@Override
	public Long sInterStore(final byte[] destKey, final byte[]... keys){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().sinterstore(destKey, keys)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().sinterstore(destKey, keys)));
		}else{
			return execute((cmd)->cmd.sinterstore(destKey, keys));
		}
	}

	@Override
	public boolean sisMember(final byte[] key, final byte[] member){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().sismember(key, member)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().sismember(key, member)));
		}else{
			return execute((cmd)->cmd.sismember(key, member));
		}
	}

	@Override
	public Set<byte[]> sMembers(final byte[] key){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().smembers(key)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().smembers(key)));
		}else{
			return execute((cmd)->cmd.smembers(key));
		}
	}

	@Override
	public Status sMove(final String key, final String destKey, final String member){
		final PredicateStatusConverter<Long> converter = new PredicateStatusConverter<>((val)->val > 0);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().smove(key, destKey, member), converter));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().smove(key, destKey, member), converter));
		}else{
			return execute((cmd)->cmd.smove(key, destKey, member), converter);
		}
	}

	@Override
	public Status sMove(final byte[] key, final byte[] destKey, final byte[] member){
		final PredicateStatusConverter<Long> converter = new PredicateStatusConverter<>((val)->val > 0);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().smove(key, destKey, member), converter));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().smove(key, destKey, member), converter));
		}else{
			return execute((cmd)->cmd.smove(key, destKey, member), converter);
		}
	}

	@Override
	public byte[] sPop(final byte[] key){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().spop(key)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().spop(key)));
		}else{
			return execute((cmd)->cmd.spop(key));
		}
	}

	@Override
	public byte[] sRandMember(final byte[] key){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().srandmember(key)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().srandmember(key)));
		}else{
			return execute((cmd)->cmd.srandmember(key));
		}
	}

	@Override
	public List<byte[]> sRandMember(final byte[] key, final int count){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().srandmember(key, count)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().srandmember(key, count)));
		}else{
			return execute((cmd)->cmd.srandmember(key, count));
		}
	}

	@Override
	public Long sRem(final byte[] key, final byte[]... members){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().srem(key, members)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().srem(key, members)));
		}else{
			return execute((cmd)->cmd.srem(key, members));
		}
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor){
		RedisExceptionUtils.pipelineAndTransactionCommandNotSupportedException(ProtocolCommand.SSCAN,
				client.getConnection());
		return execute(
				(cmd)->new ScanResultConverter.ListScanResultExposeConverter<byte[]>().convert(cmd.sscan(key, cursor)));
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor, final byte[] pattern){
		RedisExceptionUtils.pipelineAndTransactionCommandNotSupportedException(ProtocolCommand.SSCAN,
				client.getConnection());
		return execute(
				(cmd)->new ScanResultConverter.ListScanResultExposeConverter<byte[]>().convert(cmd.sscan(key, cursor,
						new JedisScanParams(pattern))));
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor, final int count){
		RedisExceptionUtils.pipelineAndTransactionCommandNotSupportedException(ProtocolCommand.SSCAN,
				client.getConnection());
		return execute(
				(cmd)->new ScanResultConverter.ListScanResultExposeConverter<byte[]>().convert(cmd.sscan(key, cursor,
						new JedisScanParams(count))));
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor, final byte[] pattern,
										  final int count){
		RedisExceptionUtils.pipelineAndTransactionCommandNotSupportedException(ProtocolCommand.SSCAN,
				client.getConnection());
		return execute(
				(cmd)->new ScanResultConverter.ListScanResultExposeConverter<byte[]>().convert(cmd.sscan(key, cursor,
						new JedisScanParams(pattern, count))));
	}

	@Override
	public Set<String> sUnion(final String... keys){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().sunion(keys)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().sunion(keys)));
		}else{
			return execute((cmd)->cmd.sunion(keys));
		}
	}

	@Override
	public Set<byte[]> sUnion(final byte[]... keys){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().sunion(keys)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().sunion(keys)));
		}else{
			return execute((cmd)->cmd.sunion(keys));
		}
	}

	@Override
	public Long sUnionStore(final String destKey, final String... keys){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().sunionstore(destKey, keys)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().sunionstore(destKey, keys)));
		}else{
			return execute((cmd)->cmd.sunionstore(destKey, keys));
		}
	}

	@Override
	public Long sUnionStore(final byte[] destKey, final byte[]... keys){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().sunionstore(destKey, keys)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().sunionstore(destKey, keys)));
		}else{
			return execute((cmd)->cmd.sunionstore(destKey, keys));
		}
	}

}
