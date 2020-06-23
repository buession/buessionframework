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
import com.buession.redis.client.jedis.JedisRedisClient;
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.jedis.JedisScanParams;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPipeline;

import java.util.List;
import java.util.Set;

/**
 * @author Yong.Teng
 */
public class ShardedJedisSetOperations extends AbstractSetOperations<ShardedJedis, ShardedJedisPipeline> {

	public ShardedJedisSetOperations(final JedisRedisClient<ShardedJedis> client){
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
		commandAllNotSupportedException(ProtocolCommand.SDIFF);
		return null;
	}

	@Override
	public Set<byte[]> sDiff(final byte[]... keys){
		commandAllNotSupportedException(ProtocolCommand.SDIFF);
		return null;
	}

	@Override
	public Long sDiffStore(final String destKey, final String... keys){
		commandAllNotSupportedException(ProtocolCommand.SDIFFSTORE);
		return null;
	}

	@Override
	public Long sDiffStore(final byte[] destKey, final byte[]... keys){
		commandAllNotSupportedException(ProtocolCommand.SDIFFSTORE);
		return null;
	}

	@Override
	public Set<String> sInter(final String... keys){
		commandAllNotSupportedException(ProtocolCommand.SINTER);
		return null;
	}

	@Override
	public Set<byte[]> sInter(final byte[]... keys){
		commandAllNotSupportedException(ProtocolCommand.SINTER);
		return null;
	}

	@Override
	public Long sInterStore(final String destKey, final String... keys){
		commandAllNotSupportedException(ProtocolCommand.SINTERSTORE);
		return null;
	}

	@Override
	public Long sInterStore(final byte[] destKey, final byte[]... keys){
		commandAllNotSupportedException(ProtocolCommand.SINTERSTORE);
		return null;
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
	public Status sMove(final String source, final String destKey, final String member){
		commandAllNotSupportedException(ProtocolCommand.SMOVE);
		return null;
	}

	@Override
	public Status sMove(final byte[] source, final byte[] destKey, final byte[] member){
		commandAllNotSupportedException(ProtocolCommand.SMOVE);
		return null;
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
		pipelineAndTransactionNotSupportedException(ProtocolCommand.SSCAN);
		return execute((cmd)->BINARY_LIST_SCANRESULT_EXPOSE_CONVERTER.convert(cmd.sscan(key, cursor)));
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor, final byte[] pattern){
		pipelineAndTransactionNotSupportedException(ProtocolCommand.SSCAN);
		return execute((cmd)->BINARY_LIST_SCANRESULT_EXPOSE_CONVERTER.convert(cmd.sscan(key, cursor,
				new JedisScanParams(pattern))));
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor, final int count){
		pipelineAndTransactionNotSupportedException(ProtocolCommand.SSCAN);
		return execute((cmd)->BINARY_LIST_SCANRESULT_EXPOSE_CONVERTER.convert(cmd.sscan(key, cursor,
				new JedisScanParams(count))));
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor, final byte[] pattern,
			final int count){
		pipelineAndTransactionNotSupportedException(ProtocolCommand.SSCAN);
		return execute((cmd)->BINARY_LIST_SCANRESULT_EXPOSE_CONVERTER.convert(cmd.sscan(key, cursor,
				new JedisScanParams(pattern, count))));
	}

	@Override
	public Set<String> sUnion(final String... keys){
		commandAllNotSupportedException(ProtocolCommand.SUNION);
		return null;
	}

	@Override
	public Set<byte[]> sUnion(final byte[]... keys){
		commandAllNotSupportedException(ProtocolCommand.SUNION);
		return null;
	}

	@Override
	public Long sUnionStore(final String destKey, final String... keys){
		commandAllNotSupportedException(ProtocolCommand.SUNIONSTORE);
		return null;
	}

	@Override
	public Long sUnionStore(final byte[] destKey, final byte[]... keys){
		commandAllNotSupportedException(ProtocolCommand.SUNIONSTORE);
		return null;
	}

}
