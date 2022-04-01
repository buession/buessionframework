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
import com.buession.redis.core.ScriptFlushMode;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.CommandNotSupported;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.convert.Converters;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.args.FlushMode;

import java.util.Arrays;
import java.util.List;

/**
 * Jedis 集群模式 Script 命令操作
 *
 * @author Yong.Teng
 */
public final class JedisClusterScriptingOperations extends AbstractScriptingOperations<JedisCluster> {

	public JedisClusterScriptingOperations(final JedisClusterClient client){
		super(client);
	}

	@Override
	public Object eval(final String script){
		final CommandArguments args = CommandArguments.create("script", script);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().eval(script)), ProtocolCommand.EVAL, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().eval(script)), ProtocolCommand.EVAL, args);
		}else{
			return execute((cmd)->cmd.eval(script, 0, null), ProtocolCommand.EVAL, args);
		}
	}

	@Override
	public Object eval(final byte[] script){
		final CommandArguments args = CommandArguments.create("script", script);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().eval(script)), ProtocolCommand.EVAL, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().eval(script)), ProtocolCommand.EVAL, args);
		}else{
			return execute((cmd)->cmd.eval(script, 0, null), ProtocolCommand.EVAL, args);
		}
	}

	@Override
	public Object eval(final String script, final String... params){
		final CommandArguments args = CommandArguments.create("script", script).put("params", params);
		final int paramsSize = params == null ? 0 : params.length;

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().eval(script, paramsSize, params)),
					ProtocolCommand.EVAL, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().eval(script, paramsSize, params)),
					ProtocolCommand.EVAL, args);
		}else{
			return execute((cmd)->cmd.eval(script, paramsSize, params), ProtocolCommand.EVAL, args);
		}
	}

	@Override
	public Object eval(final byte[] script, final byte[]... params){
		final CommandArguments args = CommandArguments.create("script", script).put("params", params);
		final int paramsSize = params == null ? 0 : params.length;

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().eval(script, paramsSize, params)),
					ProtocolCommand.EVAL, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().eval(script, paramsSize, params)),
					ProtocolCommand.EVAL, args);
		}else{
			return execute((cmd)->cmd.eval(script, paramsSize, params), ProtocolCommand.EVAL, args);
		}
	}

	@Override
	public Object eval(final String script, final String[] keys, final String[] arguments){
		final CommandArguments args = CommandArguments.create("script", script).put("keys", keys)
				.put("arguments", arguments);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().eval(script, Arrays.asList(keys),
					Arrays.asList(arguments))), ProtocolCommand.EVAL, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().eval(script, Arrays.asList(keys),
					Arrays.asList(arguments))), ProtocolCommand.EVAL, args);
		}else{
			return execute((cmd)->cmd.eval(script, Arrays.asList(keys), Arrays.asList(arguments)), ProtocolCommand.EVAL,
					args);
		}
	}

	@Override
	public Object eval(final byte[] script, final byte[][] keys, final byte[][] arguments){
		final CommandArguments args = CommandArguments.create("script", script).put("keys", keys)
				.put("arguments", arguments);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().eval(script, Arrays.asList(keys),
					Arrays.asList(arguments))), ProtocolCommand.EVAL, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().eval(script, Arrays.asList(keys),
					Arrays.asList(arguments))), ProtocolCommand.EVAL, args);
		}else{
			return execute((cmd)->cmd.eval(script, Arrays.asList(keys), Arrays.asList(arguments)), ProtocolCommand.EVAL,
					args);
		}
	}

	@Override
	public Object evalSha(final String digest){
		final CommandArguments args = CommandArguments.create("digest", digest);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().evalsha(digest)), ProtocolCommand.EVALSHA,
					args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().evalsha(digest)), ProtocolCommand.EVALSHA,
					args);
		}else{
			return execute((cmd)->cmd.evalsha(digest, null), ProtocolCommand.EVALSHA, args);
		}
	}

	@Override
	public Object evalSha(final byte[] digest){
		final CommandArguments args = CommandArguments.create("digest", digest);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().evalsha(digest)), ProtocolCommand.EVALSHA,
					args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().evalsha(digest)), ProtocolCommand.EVALSHA,
					args);
		}else{
			return execute((cmd)->cmd.evalsha(digest, null), ProtocolCommand.EVALSHA, args);
		}
	}

	@Override
	public Object evalSha(final String digest, final String... params){
		final CommandArguments args = CommandArguments.create("digest", digest).put("params", params);
		final int paramsSize = params == null ? 0 : params.length;

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().evalsha(digest, paramsSize, params)),
					ProtocolCommand.EVALSHA, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().evalsha(digest, paramsSize, params)),
					ProtocolCommand.EVALSHA, args);
		}else{
			return execute((cmd)->cmd.evalsha(digest, paramsSize, params), ProtocolCommand.EVALSHA, args);
		}
	}

	@Override
	public Object evalSha(final byte[] digest, final byte[]... params){
		final CommandArguments args = CommandArguments.create("digest", digest).put("params", params);
		final int paramsSize = params == null ? 0 : params.length;

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().evalsha(digest, paramsSize, params)),
					ProtocolCommand.EVALSHA, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().evalsha(digest, paramsSize, params)),
					ProtocolCommand.EVALSHA, args);
		}else{
			return execute((cmd)->cmd.evalsha(digest, paramsSize, params), ProtocolCommand.EVALSHA, args);
		}
	}

	@Override
	public Object evalSha(final String digest, final String[] keys, final String[] arguments){
		final CommandArguments args = CommandArguments.create("digest", digest).put("keys", keys)
				.put("arguments", arguments);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().evalsha(digest, Arrays.asList(keys),
					Arrays.asList(arguments))), ProtocolCommand.EVALSHA, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().evalsha(digest, Arrays.asList(keys),
					Arrays.asList(arguments))), ProtocolCommand.EVALSHA, args);
		}else{
			return execute((cmd)->cmd.evalsha(digest, Arrays.asList(keys), Arrays.asList(arguments)),
					ProtocolCommand.EVALSHA, args);
		}
	}

	@Override
	public Object evalSha(final byte[] digest, final byte[][] keys, final byte[][] arguments){
		final CommandArguments args = CommandArguments.create("digest", digest).put("keys", keys)
				.put("arguments", arguments);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().evalsha(digest, Arrays.asList(keys),
					Arrays.asList(arguments))), ProtocolCommand.EVALSHA, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().evalsha(digest, Arrays.asList(keys),
					Arrays.asList(arguments))), ProtocolCommand.EVALSHA, args);
		}else{
			return execute((cmd)->cmd.evalsha(digest, Arrays.asList(keys), Arrays.asList(arguments)),
					ProtocolCommand.EVALSHA, args);
		}
	}

	@Override
	public List<Boolean> scriptExists(final String... sha1){
		final CommandArguments args = CommandArguments.create("sha1", sha1);

		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.EVALSHA, args);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.EVALSHA, args);
		}else{
			return execute((cmd)->cmd.scriptExists(null, sha1), ProtocolCommand.EVALSHA, args);
		}
	}

	@Override
	public List<Boolean> scriptExists(final byte[]... sha1){
		final CommandArguments args = CommandArguments.create("sha1", sha1);

		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.EVALSHA, args);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.EVALSHA, args);
		}else{
			return execute((cmd)->cmd.scriptExists(null, sha1), LONG_LIST_TO_BOOLEAN_LIST_CONVERTER,
					ProtocolCommand.EVALSHA, args);
		}
	}

	@Override
	public Status scriptFlush(){
		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.SCRIPT_FLUSH);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.SCRIPT_FLUSH);
		}else{
			return execute((cmd)->cmd.scriptFlush((String) null), Converters.OK_STATUS_CONVERTER,
					ProtocolCommand.SCRIPT_FLUSH);
		}
	}

	@Override
	public Status scriptFlush(final ScriptFlushMode mode){
		final CommandArguments args = CommandArguments.create("mode", mode);
		final FlushMode flushMode = SCRIPT_FLUSH_MODE_JEDIS_CONVERTER.convert(mode);

		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.SCRIPT_FLUSH, args);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.SCRIPT_FLUSH, args);
		}else{
			return execute((cmd)->cmd.scriptFlush(null, flushMode), Converters.OK_STATUS_CONVERTER,
					ProtocolCommand.SCRIPT_FLUSH, args);
		}
	}

	@Override
	public String scriptLoad(final String script){
		final CommandArguments args = CommandArguments.create("script", script);

		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.SCRIPT_LOAD, args);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.SCRIPT_LOAD, args);
		}else{
			return execute((cmd)->cmd.scriptLoad(script, null), ProtocolCommand.SCRIPT_LOAD, args);
		}
	}

	@Override
	public byte[] scriptLoad(final byte[] script){
		final CommandArguments args = CommandArguments.create("script", script);

		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.SCRIPT_LOAD, args);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.SCRIPT_LOAD, args);
		}else{
			return execute((cmd)->cmd.scriptLoad(script, null), ProtocolCommand.SCRIPT_LOAD, args);
		}
	}

	@Override
	public Status scriptKill(){
		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.SCRIPT_KILL);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.SCRIPT_KILL);
		}else{
			return execute((cmd)->cmd.scriptKill((String) null), Converters.OK_STATUS_CONVERTER,
					ProtocolCommand.SCRIPT_KILL);
		}
	}

}
