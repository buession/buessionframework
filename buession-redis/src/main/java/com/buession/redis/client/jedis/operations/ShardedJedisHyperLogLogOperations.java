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
 * | Copyright @ 2013-2021 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.client.jedis.operations;

import com.buession.core.converter.PredicateStatusConverter;
import com.buession.lang.Status;
import com.buession.redis.client.jedis.ShardedJedisClient;
import com.buession.redis.core.RedisMode;
import com.buession.redis.core.command.ProtocolCommand;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPipeline;

/**
 * @author Yong.Teng
 */
public class ShardedJedisHyperLogLogOperations extends AbstractHyperLogLogOperations<ShardedJedis,
		ShardedJedisPipeline> {

	public ShardedJedisHyperLogLogOperations(final ShardedJedisClient client){
		super(client, RedisMode.SHARDED);
	}

	@Override
	public Status pfAdd(final byte[] key, final byte[]... elements){
		final PredicateStatusConverter<Long> converter = new PredicateStatusConverter<>((val)->val > 0);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().pfadd(key, elements), converter));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().pfadd(key, elements), converter));
		}else{
			return execute((cmd)->cmd.pfadd(key, elements), converter);
		}
	}

	@Override
	public Status pfMerge(final String destKey, final String... keys){
		commandAllNotSupportedException(ProtocolCommand.PFMERGE);
		return null;
	}

	@Override
	public Status pfMerge(final byte[] destKey, final byte[]... keys){
		commandAllNotSupportedException(ProtocolCommand.PFMERGE);
		return null;
	}

	@Override
	public Long pfCount(final String... keys){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().pfcount(keys[0])));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().pfcount(keys)));
		}else{
			return execute((cmd)->{
				long result = 0;

				for(String key : keys){
					result += cmd.pfcount(key);
				}

				return result;
			});
		}
	}

	@Override
	public Long pfCount(final byte[]... keys){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().pfcount(keys[0])));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().pfcount(keys)));
		}else{
			return execute((cmd)->{
				long result = 0;

				for(byte[] key : keys){
					result += cmd.pfcount(key);
				}

				return result;
			});
		}
	}

}
