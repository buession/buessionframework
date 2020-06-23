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
import com.buession.redis.client.RedisClient;
import com.buession.redis.client.operations.SetOperations;
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.jedis.JedisScanParams;
import redis.clients.jedis.PipelineBase;
import redis.clients.jedis.commands.JedisCommands;

import java.util.List;
import java.util.Set;

/**
 * @author Yong.Teng
 */
public abstract class AbstractSetOperations<C extends JedisCommands, P extends PipelineBase> extends AbstractJedisRedisClientOperations<C, P> implements SetOperations {

	public AbstractSetOperations(final RedisClient client){
		super(client);
	}

	@Override
	public Long sAdd(final String key, final String... members){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().sadd(key, members)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().sadd(key, members)));
		}else{
			return execute((cmd)->cmd.sadd(key, members));
		}
	}

	@Override
	public Long sCard(final String key){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().scard(key)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().scard(key)));
		}else{
			return execute((cmd)->cmd.scard(key));
		}
	}

	@Override
	public boolean sisMember(final String key, final String member){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().sismember(key, member)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().sismember(key, member)));
		}else{
			return execute((cmd)->cmd.sismember(key, member));
		}
	}

	@Override
	public Set<String> sMembers(final String key){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().smembers(key)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().smembers(key)));
		}else{
			return execute((cmd)->cmd.smembers(key));
		}
	}

	@Override
	public String sPop(final String key){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().spop(key)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().spop(key)));
		}else{
			return execute((cmd)->cmd.spop(key));
		}
	}

	@Override
	public String sRandMember(final String key){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().srandmember(key)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().srandmember(key)));
		}else{
			return execute((cmd)->cmd.srandmember(key));
		}
	}

	@Override
	public List<String> sRandMember(final String key, final int count){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().srandmember(key, count)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().srandmember(key, count)));
		}else{
			return execute((cmd)->cmd.srandmember(key, count));
		}
	}

	@Override
	public List<String> sRandMember(final String key, final long count){
		return sRandMember(key, (int) count);
	}

	@Override
	public List<byte[]> sRandMember(final byte[] key, final long count){
		return sRandMember(key, (int) count);
	}

	@Override
	public Long sRem(final String key, final String... members){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().srem(key, members)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().srem(key, members)));
		}else{
			return execute((cmd)->cmd.srem(key, members));
		}
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final int cursor){
		return sScan(key, Integer.toString(cursor));
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final int cursor){
		return sScan(key, NumberUtils.int2bytes(cursor));
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final long cursor){
		return sScan(key, Long.toString(cursor));
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final long cursor){
		return sScan(key, NumberUtils.long2bytes(cursor));
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final String cursor){
		pipelineAndTransactionNotSupportedException(ProtocolCommand.SSCAN);
		return execute((cmd)->STRING_LIST_SCANRESULT_EXPOSE_CONVERTER.convert(cmd.sscan(key, cursor)));
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final int cursor, final String pattern){
		return sScan(key, Integer.toString(cursor), pattern);
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final int cursor, final byte[] pattern){
		return sScan(key, NumberUtils.int2bytes(cursor), pattern);
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final long cursor, final String pattern){
		return sScan(key, Long.toString(cursor), pattern);
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final long cursor, final byte[] pattern){
		return sScan(key, NumberUtils.long2bytes(cursor), pattern);
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final String cursor, final String pattern){
		pipelineAndTransactionNotSupportedException(ProtocolCommand.SSCAN);
		return execute((cmd)->STRING_LIST_SCANRESULT_EXPOSE_CONVERTER.convert(cmd.sscan(key, cursor,
				new JedisScanParams(pattern))));
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final int cursor, final int count){
		return sScan(key, Integer.toString(cursor), count);
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final int cursor, final int count){
		return sScan(key, NumberUtils.int2bytes(cursor), count);
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final long cursor, final int count){
		return sScan(key, Long.toString(cursor), count);
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final long cursor, final int count){
		return sScan(key, NumberUtils.long2bytes(cursor), count);
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final String cursor, final int count){
		pipelineAndTransactionNotSupportedException(ProtocolCommand.SSCAN);
		return execute((cmd)->STRING_LIST_SCANRESULT_EXPOSE_CONVERTER.convert(cmd.sscan(key, cursor,
				new JedisScanParams(count))));
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final int cursor, final String pattern, final int count){
		return sScan(key, Integer.toString(cursor), pattern, count);
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final int cursor, final byte[] pattern, final int count){
		return sScan(key, NumberUtils.int2bytes(cursor), pattern, count);
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final long cursor, final String pattern, final int count){
		return sScan(key, Long.toString(cursor), pattern, count);
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final long cursor, final byte[] pattern, final int count){
		return sScan(key, NumberUtils.long2bytes(cursor), pattern, count);
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final String cursor, final String pattern,
			final int count){
		pipelineAndTransactionNotSupportedException(ProtocolCommand.SSCAN);
		return execute((cmd)->STRING_LIST_SCANRESULT_EXPOSE_CONVERTER.convert(cmd.sscan(key, cursor,
				new JedisScanParams(pattern, count))));
	}

}
