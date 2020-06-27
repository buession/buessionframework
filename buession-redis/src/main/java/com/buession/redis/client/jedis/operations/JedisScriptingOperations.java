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
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.utils.ReturnUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Yong.Teng
 */
public class JedisScriptingOperations extends AbstractScriptingOperations<Jedis, Pipeline> {

	public JedisScriptingOperations(final JedisRedisClient<Jedis> client){
		super(client, null);
	}

	@Override
	public Object eval(final String script){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().eval(script)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().eval(script)));
		}else{
			return execute((cmd)->cmd.eval(script));
		}
	}

	@Override
	public Object eval(final byte[] script){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().eval(script)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().eval(script)));
		}else{
			return execute((cmd)->cmd.eval(script));
		}
	}

	@Override
	public Object eval(final String script, final String... params){
		final int paramsSize = params == null ? 0 : params.length;

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().eval(script, paramsSize, params)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().eval(script, paramsSize, params)));
		}else{
			return execute((cmd)->cmd.eval(script, paramsSize, params));
		}
	}

	@Override
	public Object eval(final byte[] script, final byte[]... params){
		final int paramsSize = params == null ? 0 : params.length;

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().eval(script, paramsSize, params)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().eval(script, paramsSize, params)));
		}else{
			return execute((cmd)->cmd.eval(script, paramsSize, params));
		}
	}

	@Override
	public Object eval(final String script, final String[] keys, final String[] arguments){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().eval(script, Arrays.asList(keys),
					Arrays.asList(arguments))));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().eval(script, Arrays.asList(keys),
					Arrays.asList(arguments))));
		}else{
			return execute((cmd)->cmd.eval(script, Arrays.asList(keys), Arrays.asList(arguments)));
		}
	}

	@Override
	public Object eval(final byte[] script, final byte[][] keys, final byte[][] arguments){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().eval(script, Arrays.asList(keys),
					Arrays.asList(arguments))));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().eval(script, Arrays.asList(keys),
					Arrays.asList(arguments))));
		}else{
			return execute((cmd)->cmd.eval(script, Arrays.asList(keys), Arrays.asList(arguments)));
		}
	}

	@Override
	public Object evalSha(final String digest){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().evalsha(digest)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().evalsha(digest)));
		}else{
			return execute((cmd)->cmd.evalsha(digest));
		}
	}

	@Override
	public Object evalSha(final byte[] digest){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().evalsha(digest)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().evalsha(digest)));
		}else{
			return execute((cmd)->cmd.evalsha(digest));
		}
	}

	@Override
	public Object evalSha(final String digest, final String... params){
		final int paramsSize = params == null ? 0 : params.length;

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().evalsha(digest, paramsSize, params)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().evalsha(digest, paramsSize, params)));
		}else{
			return execute((cmd)->cmd.evalsha(digest, paramsSize, params));
		}
	}

	@Override
	public Object evalSha(final byte[] digest, final byte[]... params){
		final int paramsSize = params == null ? 0 : params.length;

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().evalsha(digest, paramsSize, params)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().evalsha(digest, paramsSize, params)));
		}else{
			return execute((cmd)->cmd.evalsha(digest, paramsSize, params));
		}
	}

	@Override
	public Object evalSha(final String digest, final String[] keys, final String[] arguments){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().evalsha(digest, Arrays.asList(keys),
					Arrays.asList(arguments))));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().evalsha(digest, Arrays.asList(keys),
					Arrays.asList(arguments))));
		}else{
			return execute((cmd)->cmd.evalsha(digest, Arrays.asList(keys), Arrays.asList(arguments)));
		}
	}

	@Override
	public Object evalSha(final byte[] digest, final byte[][] keys, final byte[][] arguments){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().evalsha(digest, Arrays.asList(keys),
					Arrays.asList(arguments))));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().evalsha(digest, Arrays.asList(keys),
					Arrays.asList(arguments))));
		}else{
			return execute((cmd)->cmd.evalsha(digest, Arrays.asList(keys), Arrays.asList(arguments)));
		}
	}

	@Override
	public List<Boolean> scriptExists(final String... sha1){
		pipelineAndTransactionNotSupportedException(ProtocolCommand.SCRIPT_EXISTS);
		return execute((cmd)->cmd.scriptExists(sha1));
	}

	@Override
	public List<Boolean> scriptExists(final byte[]... sha1){
		pipelineAndTransactionNotSupportedException(ProtocolCommand.SCRIPT_EXISTS);
		return execute((cmd)->{
			List<Long> result = cmd.scriptExists(sha1);
			return result == null ? null : result.stream().map((v)->v == 1).collect(Collectors.toList());
		});
	}

	@Override
	public Status scriptFlush(){
		pipelineAndTransactionNotSupportedException(ProtocolCommand.SCRIPT_FLUSH);
		return execute((cmd)->ReturnUtils.statusForOK(cmd.scriptFlush()));
	}

	@Override
	public Status scriptKill(){
		pipelineAndTransactionNotSupportedException(ProtocolCommand.SCRIPT_KILL);
		return execute((cmd)->ReturnUtils.statusForOK(cmd.scriptKill()));
	}

	@Override
	public String scriptLoad(final String script){
		pipelineAndTransactionNotSupportedException(ProtocolCommand.SCRIPT_LOAD);
		return execute((cmd)->cmd.scriptLoad(script));
	}

	@Override
	public byte[] scriptLoad(final byte[] script){
		pipelineAndTransactionNotSupportedException(ProtocolCommand.SCRIPT_LOAD);
		return execute((cmd)->cmd.scriptLoad(script));
	}

}
