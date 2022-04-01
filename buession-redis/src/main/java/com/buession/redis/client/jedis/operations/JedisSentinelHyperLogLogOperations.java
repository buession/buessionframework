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
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.convert.Converters;
import redis.clients.jedis.Jedis;

/**
 * Jedis 哨兵模式 HyperLogLog 命令操作
 *
 * @author Yong.Teng
 */
public final class JedisSentinelHyperLogLogOperations extends AbstractHyperLogLogOperations<Jedis> {

	public JedisSentinelHyperLogLogOperations(final JedisSentinelClient client){
		super(client);
	}

	@Override
	public Status pfAdd(final String key, final String... elements){
		final CommandArguments args = CommandArguments.create("key", key).put("elements", elements);
		final PredicateStatusConverter<Long> converter = new PredicateStatusConverter<>((val)->val > 0);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().pfadd(key, elements), converter),
					ProtocolCommand.PFADD, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().pfadd(key, elements), converter),
					ProtocolCommand.PFADD, args);
		}else{
			return execute((cmd)->cmd.pfadd(key, elements), converter, ProtocolCommand.PFADD, args);
		}
	}

	@Override
	public Status pfAdd(final byte[] key, final byte[]... elements){
		final CommandArguments args = CommandArguments.create("key", key).put("elements", elements);
		final PredicateStatusConverter<Long> converter = new PredicateStatusConverter<>((val)->val > 0);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().pfadd(key, elements), converter),
					ProtocolCommand.PFADD, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().pfadd(key, elements), converter),
					ProtocolCommand.PFADD, args);
		}else{
			return execute((cmd)->cmd.pfadd(key, elements), converter, ProtocolCommand.PFADD, args);
		}
	}

	@Override
	public Status pfMerge(final String destKey, final String... keys){
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("keys", keys);

		if(isPipeline()){
			return pipelineExecute(
					(cmd)->newJedisResult(getPipeline().pfmerge(destKey, keys), Converters.OK_STATUS_CONVERTER),
					ProtocolCommand.PFMERGE, args);
		}else if(isTransaction()){
			return transactionExecute(
					(cmd)->newJedisResult(getTransaction().pfmerge(destKey, keys), Converters.OK_STATUS_CONVERTER),
					ProtocolCommand.PFMERGE, args);
		}else{
			return execute((cmd)->cmd.pfmerge(destKey, keys), Converters.OK_STATUS_CONVERTER,
					ProtocolCommand.PFMERGE, args);
		}
	}

	@Override
	public Status pfMerge(final byte[] destKey, final byte[]... keys){
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("keys", keys);

		if(isPipeline()){
			return pipelineExecute(
					(cmd)->newJedisResult(getPipeline().pfmerge(destKey, keys), Converters.OK_STATUS_CONVERTER),
					ProtocolCommand.PFMERGE, args);
		}else if(isTransaction()){
			return transactionExecute(
					(cmd)->newJedisResult(getTransaction().pfmerge(destKey, keys), Converters.OK_STATUS_CONVERTER),
					ProtocolCommand.PFMERGE, args);
		}else{
			return execute((cmd)->cmd.pfmerge(destKey, keys), Converters.OK_STATUS_CONVERTER,
					ProtocolCommand.PFMERGE, args);
		}
	}

	@Override
	public Long pfCount(final String... keys){
		final CommandArguments args = CommandArguments.create("keys", keys);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().pfcount(keys)), ProtocolCommand.PFCOUNT, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().pfcount(keys)), ProtocolCommand.PFCOUNT,
					args);
		}else{
			return execute((cmd)->cmd.pfcount(keys), ProtocolCommand.PFCOUNT, args);
		}
	}

	@Override
	public Long pfCount(final byte[]... keys){
		final CommandArguments args = CommandArguments.create("keys", keys);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().pfcount(keys)), ProtocolCommand.PFCOUNT, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().pfcount(keys)), ProtocolCommand.PFCOUNT,
					args);
		}else{
			return execute((cmd)->cmd.pfcount(keys), ProtocolCommand.PFCOUNT, args);
		}
	}

}
