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
import com.buession.redis.client.lettuce.LettuceStandaloneClient;
import com.buession.redis.core.FlushMode;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.utils.SafeEncoder;

import java.util.List;

/**
 * Lettuce 单机模式 Script 命令操作
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public final class LettuceScriptingOperations extends AbstractScriptingOperations<LettuceStandaloneClient> {

	public LettuceScriptingOperations(final LettuceStandaloneClient client) {
		super(client);
	}

	@Override
	public Object eval(final String script) {
		final CommandArguments args = CommandArguments.create("script", script);

		if(isMulti()){
			return new LettuceAsyncCommand<>(client, ProtocolCommand.EVAL, (cmd)->cmd.eval(script, null), (v)->v)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.EVAL, (cmd)->cmd.eval(script, null), (v)->v)
					.run(args);
		}
	}

	@Override
	public Object eval(final String script, final String... params) {
		final CommandArguments args = CommandArguments.create("script", script).put("params", (Object[]) params);
		final byte[][] bParams = SafeEncoder.encode(params);

		if(isMulti()){
			return new LettuceAsyncCommand<>(client, ProtocolCommand.EVAL,
					(cmd)->cmd.eval(script, null, null, bParams), (v)->v)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.EVAL,
					(cmd)->cmd.eval(script, null, null, bParams), (v)->v)
					.run(args);
		}
	}

	@Override
	public Object eval(final String script, final String[] keys, final String[] arguments) {
		final CommandArguments args = CommandArguments.create("script", script).put("keys", (Object[]) keys)
				.put("arguments", (Object[]) arguments);
		final byte[][] bKeys = SafeEncoder.encode(keys);
		final byte[][] bArguments = SafeEncoder.encode(arguments);

		if(isMulti()){
			return new LettuceAsyncCommand<>(client, ProtocolCommand.EVAL,
					(cmd)->cmd.eval(script, null, bKeys, bArguments), (v)->v)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.EVAL,
					(cmd)->cmd.eval(script, null, bKeys, bArguments), (v)->v)
					.run(args);
		}
	}

	@Override
	public Object evalSha(final String digest) {
		final CommandArguments args = CommandArguments.create("digest", digest);

		if(isMulti()){
			return new LettuceAsyncCommand<>(client, ProtocolCommand.EVALSHA, (cmd)->cmd.evalsha(digest, null), (v)->v)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.EVALSHA, (cmd)->cmd.evalsha(digest, null), (v)->v)
					.run(args);
		}
	}

	@Override
	public Object evalSha(final String digest, final String... params) {
		final CommandArguments args = CommandArguments.create("digest", digest).put("params", (Object[]) params);
		final byte[][] bParams = SafeEncoder.encode(params);

		if(isMulti()){
			return new LettuceAsyncCommand<>(client, ProtocolCommand.EVALSHA,
					(cmd)->cmd.evalsha(digest, null, null, bParams), (v)->v)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.EVALSHA,
					(cmd)->cmd.evalsha(digest, null, null, bParams), (v)->v)
					.run(args);
		}
	}

	@Override
	public Object evalSha(final String digest, final String[] keys, final String[] arguments) {
		final CommandArguments args = CommandArguments.create("digest", digest).put("keys", (Object[]) keys)
				.put("arguments", (Object[]) arguments);
		final byte[][] bKeys = SafeEncoder.encode(keys);
		final byte[][] bArguments = SafeEncoder.encode(arguments);

		if(isMulti()){
			return new LettuceAsyncCommand<>(client, ProtocolCommand.EVALSHA,
					(cmd)->cmd.evalsha(digest, null, bKeys, bArguments), (v)->v)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.EVALSHA,
					(cmd)->cmd.evalsha(digest, null, bKeys, bArguments), (v)->v)
					.run(args);
		}
	}

	@Override
	public List<Boolean> scriptExists(final String... sha1) {
		final CommandArguments args = CommandArguments.create("sha1", (Object[]) sha1);

		if(isMulti()){
			return new LettuceAsyncCommand<>(client, ProtocolCommand.SCRIPT_EXISTS, (cmd)->cmd.scriptExists(sha1),
					(v)->v)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.SCRIPT_EXISTS, (cmd)->cmd.scriptExists(sha1), (v)->v)
					.run(args);
		}
	}

	@Override
	public Status scriptFlush() {
		if(isMulti()){
			return new LettuceAsyncCommand<>(client, ProtocolCommand.SCRIPT_FLUSH, (cmd)->cmd.scriptFlush(),
					okStatusConverter)
					.run();
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.SCRIPT_FLUSH, (cmd)->cmd.scriptFlush(),
					okStatusConverter)
					.run();
		}
	}

	@Override
	public Status scriptFlush(final FlushMode mode) {
		final CommandArguments args = CommandArguments.create("mode", mode);

		if(isMulti()){
			return new LettuceAsyncCommand<>(client, ProtocolCommand.SCRIPT_FLUSH, (cmd)->cmd.scriptFlush(),
					okStatusConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.SCRIPT_FLUSH, (cmd)->cmd.scriptFlush(),
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
		if(isMulti()){
			return new LettuceAsyncCommand<>(client, ProtocolCommand.SCRIPT_KILL, (cmd)->cmd.scriptKill(),
					okStatusConverter)
					.run();
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.SCRIPT_KILL, (cmd)->cmd.scriptKill(),
					okStatusConverter)
					.run();
		}
	}

	private <V> V scriptLoad(final byte[] script, final Converter<String, V> converter,
							 final CommandArguments args) {
		if(isMulti()){
			return new LettuceAsyncCommand<>(client, ProtocolCommand.SCRIPT_LOAD, (cmd)->cmd.scriptLoad(script),
					converter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.SCRIPT_LOAD, (cmd)->cmd.scriptLoad(script), converter)
					.run(args);
		}
	}

}
