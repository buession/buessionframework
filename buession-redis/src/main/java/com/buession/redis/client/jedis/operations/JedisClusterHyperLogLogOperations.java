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
import com.buession.redis.client.jedis.JedisClient;
import com.buession.redis.core.convert.OkStatusConverter;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

/**
 * @author Yong.Teng
 */
public class JedisHyperLogLogOperations extends AbstractHyperLogLogOperations<Jedis, Pipeline> {

	public JedisHyperLogLogOperations(final JedisClient client){
		super(client);
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
		final OkStatusConverter converter = new OkStatusConverter();

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().pfmerge(destKey, keys), converter));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().pfmerge(destKey, keys), converter));
		}else{
			return execute((cmd)->cmd.pfmerge(destKey, keys), converter);
		}
	}

	@Override
	public Status pfMerge(final byte[] destKey, final byte[]... keys){
		final OkStatusConverter converter = new OkStatusConverter();

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().pfmerge(destKey, keys), converter));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().pfmerge(destKey, keys), converter));
		}else{
			return execute((cmd)->cmd.pfmerge(destKey, keys), converter);
		}
	}

	@Override
	public Long pfCount(final String... keys){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().pfcount(keys[0])));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().pfcount(keys)));
		}else{
			return execute((cmd)->cmd.pfcount(keys));
		}
	}

	@Override
	public Long pfCount(final byte[]... keys){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().pfcount(keys[0])));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().pfcount(keys)));
		}else{
			return execute((cmd)->cmd.pfcount(keys));
		}
	}

}