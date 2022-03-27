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

import com.buession.lang.Status;
import com.buession.redis.client.jedis.JedisClusterClient;
import com.buession.redis.core.command.CommandNotSupported;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.convert.OkStatusConverter;
import com.buession.redis.exception.RedisExceptionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Yong.Teng
 */
public class JedisClusterScriptingOperations extends AbstractScriptingOperations {

	public JedisClusterScriptingOperations(final JedisClusterClient client){
		super(client);
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
		RedisExceptionUtils.commandNotSupportedException(ProtocolCommand.SCRIPT_EXISTS,
				CommandNotSupported.PIPELINE | CommandNotSupported.TRANSACTION, client.getConnection());
		return execute((cmd)->cmd.scriptExists(sha1));
	}

	@Override
	public List<Boolean> scriptExists(final byte[]... sha1){
		RedisExceptionUtils.commandNotSupportedException(ProtocolCommand.SCRIPT_EXISTS,
				CommandNotSupported.PIPELINE | CommandNotSupported.TRANSACTION, client.getConnection());
		return execute((cmd)->{
			List<Long> result = cmd.scriptExists(sha1);
			return result == null ? null : result.stream().map((v)->v == 1).collect(Collectors.toList());
		});
	}

	@Override
	public Status scriptFlush(){
		RedisExceptionUtils.commandNotSupportedException(ProtocolCommand.SCRIPT_FLUSH,
				CommandNotSupported.PIPELINE | CommandNotSupported.TRANSACTION, client.getConnection());
		return execute((cmd)->cmd.scriptFlush(), new OkStatusConverter());
	}

	@Override
	public Status scriptKill(){
		RedisExceptionUtils.commandNotSupportedException(ProtocolCommand.SCRIPT_KILL,
				CommandNotSupported.PIPELINE | CommandNotSupported.TRANSACTION, client.getConnection());
		return execute((cmd)->cmd.scriptKill(), new OkStatusConverter());
	}

	@Override
	public String scriptLoad(final String script){
		RedisExceptionUtils.commandNotSupportedException(ProtocolCommand.SCRIPT_LOAD,
				CommandNotSupported.PIPELINE | CommandNotSupported.TRANSACTION, client.getConnection());
		return execute((cmd)->cmd.scriptLoad(script));
	}

	@Override
	public byte[] scriptLoad(final byte[] script){
		RedisExceptionUtils.commandNotSupportedException(ProtocolCommand.SCRIPT_LOAD,
				CommandNotSupported.PIPELINE | CommandNotSupported.TRANSACTION, client.getConnection());
		return execute((cmd)->cmd.scriptLoad(script));
	}

}
