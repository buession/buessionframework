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
 * | Copyright @ 2013-2024 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.client.lettuce.operations;

import com.buession.core.converter.Converter;
import com.buession.lang.Status;
import com.buession.redis.client.lettuce.LettuceClusterClient;
import com.buession.redis.core.FlushMode;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.Command;
import com.buession.redis.core.internal.convert.lettuce.params.FlushModeConverter;
import com.buession.redis.utils.SafeEncoder;
import io.lettuce.core.ScriptOutputType;

import java.util.List;

/**
 * Lettuce 集群模式 Script 命令操作
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public final class LettuceClusterScriptingOperations extends AbstractScriptingOperations<LettuceClusterClient> {

	public LettuceClusterScriptingOperations(final LettuceClusterClient client) {
		super(client);
	}

	@Override
	public Object eval(final String script) {
		final CommandArguments args = CommandArguments.create("script", script);
		final byte[][] bKeys = new byte[][]{};

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.EVAL,
					(cmd)->cmd.eval(script, ScriptOutputType.OBJECT, bKeys), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.EVAL,
					(cmd)->cmd.eval(script, ScriptOutputType.OBJECT, bKeys), (v)->v)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, Command.EVAL,
					(cmd)->cmd.eval(script, ScriptOutputType.OBJECT, bKeys), (v)->v)
					.run(args);
		}
	}

	@Override
	public Object eval(final byte[] script) {
		final CommandArguments args = CommandArguments.create("script", script);
		final byte[][] keys = new byte[][]{};

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.EVAL,
					(cmd)->cmd.eval(script, ScriptOutputType.OBJECT, keys), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.EVAL,
					(cmd)->cmd.eval(script, ScriptOutputType.OBJECT, keys), (v)->v)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, Command.EVAL,
					(cmd)->cmd.eval(script, ScriptOutputType.OBJECT, keys), (v)->v)
					.run(args);
		}
	}

	@Override
	public Object eval(final String script, final String... params) {
		final CommandArguments args = CommandArguments.create("script", script).put("params", (Object[]) params);
		final byte[][] bKeys = new byte[][]{};
		final byte[][] bParams = SafeEncoder.encode(params);

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.EVAL,
					(cmd)->cmd.eval(script, ScriptOutputType.OBJECT, bKeys, bParams), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.EVAL,
					(cmd)->cmd.eval(script, ScriptOutputType.OBJECT, bKeys, bParams), (v)->v)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, Command.EVAL,
					(cmd)->cmd.eval(script, ScriptOutputType.OBJECT, bKeys, bParams), (v)->v)
					.run(args);
		}
	}

	@Override
	public Object eval(final byte[] script, final byte[]... params) {
		final CommandArguments args = CommandArguments.create("script", script).put("params", (Object[]) params);
		final byte[][] keys = new byte[][]{};

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.EVAL,
					(cmd)->cmd.eval(script, ScriptOutputType.OBJECT, keys, params), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.EVAL,
					(cmd)->cmd.eval(script, ScriptOutputType.OBJECT, keys, params), (v)->v)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, Command.EVAL,
					(cmd)->cmd.eval(script, ScriptOutputType.OBJECT, keys, params), (v)->v)
					.run(args);
		}
	}

	@Override
	public Object eval(final String script, final String[] keys, final String[] arguments) {
		final CommandArguments args = CommandArguments.create("script", script).put("keys", (Object[]) keys)
				.put("arguments", (Object[]) arguments);
		final byte[][] bKeys = SafeEncoder.encode(keys);
		final byte[][] bArguments = SafeEncoder.encode(arguments);

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.EVAL,
					(cmd)->cmd.eval(script, ScriptOutputType.OBJECT, bKeys, bArguments), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.EVAL,
					(cmd)->cmd.eval(script, ScriptOutputType.OBJECT, bKeys, bArguments), (v)->v)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, Command.EVAL,
					(cmd)->cmd.eval(script, ScriptOutputType.OBJECT, bKeys, bArguments), (v)->v)
					.run(args);
		}
	}

	@Override
	public Object eval(final byte[] script, final byte[][] keys, final byte[][] arguments) {
		final CommandArguments args = CommandArguments.create("script", script).put("keys", (Object[]) keys)
				.put("arguments", (Object[]) arguments);

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.EVAL,
					(cmd)->cmd.eval(script, ScriptOutputType.OBJECT, keys, arguments), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.EVAL,
					(cmd)->cmd.eval(script, ScriptOutputType.OBJECT, keys, arguments), (v)->v)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, Command.EVAL,
					(cmd)->cmd.eval(script, ScriptOutputType.OBJECT, keys, arguments), (v)->v)
					.run(args);
		}
	}

	@Override
	public Object evalSha(final String digest) {
		final CommandArguments args = CommandArguments.create("digest", digest);
		final byte[][] bKeys = new byte[][]{};

		return evalSha(digest, bKeys, null, args);
	}

	@Override
	public Object evalSha(final String digest, final String... params) {
		final CommandArguments args = CommandArguments.create("digest", digest).put("params", (Object[]) params);
		final byte[][] bKeys = new byte[][]{};
		final byte[][] bParams = SafeEncoder.encode(params);

		return evalSha(digest, bKeys, bParams, args);
	}

	@Override
	public Object evalSha(final String digest, final String[] keys, final String[] arguments) {
		final CommandArguments args = CommandArguments.create("digest", digest).put("keys", (Object[]) keys)
				.put("arguments", (Object[]) arguments);
		final byte[][] bKeys = SafeEncoder.encode(keys);
		final byte[][] bArguments = SafeEncoder.encode(arguments);

		return evalSha(digest, bKeys, bArguments, args);
	}

	@Override
	public List<Boolean> scriptExists(final String... sha1) {
		final CommandArguments args = CommandArguments.create("sha1", (Object[]) sha1);

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.SCRIPT_EXISTS,
					(cmd)->cmd.scriptExists(sha1), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.SCRIPT_EXISTS,
					(cmd)->cmd.scriptExists(sha1), (v)->v)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, Command.SCRIPT_EXISTS, (cmd)->cmd.scriptExists(sha1),
					(v)->v)
					.run(args);
		}
	}

	@Override
	public Status scriptFlush() {
		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.SCRIPT_FLUSH, (cmd)->cmd.scriptFlush(),
					okStatusConverter)
					.run();
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.SCRIPT_FLUSH,
					(cmd)->cmd.scriptFlush(), okStatusConverter)
					.run();
		}else{
			return new LettuceClusterCommand<>(client, Command.SCRIPT_FLUSH, (cmd)->cmd.scriptFlush(),
					okStatusConverter)
					.run();
		}
	}

	@Override
	public Status scriptFlush(final FlushMode mode) {
		final CommandArguments args = CommandArguments.create("mode", mode);
		final io.lettuce.core.FlushMode flushMode = (new FlushModeConverter()).convert(mode);

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.SCRIPT_FLUSH,
					(cmd)->cmd.scriptFlush(flushMode), okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.SCRIPT_FLUSH,
					(cmd)->cmd.scriptFlush(flushMode), okStatusConverter)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, Command.SCRIPT_FLUSH, (cmd)->cmd.scriptFlush(flushMode),
					okStatusConverter)
					.run(args);
		}
	}

	@Override
	public String scriptLoad(final String script) {
		final CommandArguments args = CommandArguments.create("script", script);
		final byte[] bScript = SafeEncoder.encode(script);

		return scriptLoad(bScript, (v)->v, args);
	}

	@Override
	public byte[] scriptLoad(final byte[] script) {
		final CommandArguments args = CommandArguments.create("script", script);
		return scriptLoad(script, SafeEncoder::encode, args);
	}

	@Override
	public Status scriptKill() {
		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.SCRIPT_KILL, (cmd)->cmd.scriptKill(),
					okStatusConverter)
					.run();
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.SCRIPT_KILL, (cmd)->cmd.scriptKill(),
					okStatusConverter)
					.run();
		}else{
			return new LettuceClusterCommand<>(client, Command.SCRIPT_KILL, (cmd)->cmd.scriptKill(),
					okStatusConverter)
					.run();
		}
	}

	private Object evalSha(final String digest, final byte[][] keys, final byte[][] arguments,
						   final CommandArguments args) {
		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.EVALSHA,
					(cmd)->cmd.evalsha(digest, ScriptOutputType.OBJECT, keys, arguments), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.EVALSHA,
					(cmd)->cmd.evalsha(digest, ScriptOutputType.OBJECT, keys, arguments), (v)->v)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, Command.EVALSHA,
					(cmd)->cmd.evalsha(digest, ScriptOutputType.OBJECT, keys, arguments), (v)->v)
					.run(args);
		}
	}

	private <V> V scriptLoad(final byte[] script, final Converter<String, V> converter,
							 final CommandArguments args) {
		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.SCRIPT_LOAD,
					(cmd)->cmd.scriptLoad(script), converter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.SCRIPT_LOAD,
					(cmd)->cmd.scriptLoad(script), converter)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, Command.SCRIPT_LOAD, (cmd)->cmd.scriptLoad(script),
					converter)
					.run(args);
		}
	}

}
