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
package com.buession.redis.client.jedis.operations;

import com.buession.lang.Status;
import com.buession.redis.client.jedis.JedisSentinelClient;
import com.buession.redis.core.FlushMode;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.internal.convert.jedis.params.FlushModeConverter;

import java.util.Arrays;
import java.util.List;

/**
 * Jedis 哨兵模式 Script 命令操作
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public final class JedisSentinelScriptingOperations extends AbstractScriptingOperations<JedisSentinelClient> {

	public JedisSentinelScriptingOperations(final JedisSentinelClient client) {
		super(client);
	}

	@Override
	public Object eval(final String script) {
		final CommandArguments args = CommandArguments.create(script);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, ProtocolCommand.EVAL, (cmd)->cmd.eval(script), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, ProtocolCommand.EVAL, (cmd)->cmd.eval(script), (v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.EVAL, (cmd)->cmd.eval(script), (v)->v)
					.run(args);
		}
	}

	@Override
	public Object eval(final byte[] script) {
		final CommandArguments args = CommandArguments.create(script);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, ProtocolCommand.EVAL, (cmd)->cmd.eval(script), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, ProtocolCommand.EVAL, (cmd)->cmd.eval(script), (v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.EVAL, (cmd)->cmd.eval(script), (v)->v)
					.run(args);
		}
	}

	@Override
	public Object eval(final String script, final String... params) {
		final CommandArguments args = CommandArguments.create(script).add(params);
		final int paramsSize = params == null ? 0 : params.length;

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, ProtocolCommand.EVAL,
					(cmd)->cmd.eval(script, paramsSize, params), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, ProtocolCommand.EVAL,
					(cmd)->cmd.eval(script, paramsSize, params), (v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.EVAL, (cmd)->cmd.eval(script, paramsSize, params),
					(v)->v)
					.run(args);
		}
	}

	@Override
	public Object eval(final byte[] script, final byte[]... params) {
		final CommandArguments args = CommandArguments.create(script).add(params);
		final int paramsSize = params == null ? 0 : params.length;

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, ProtocolCommand.EVAL,
					(cmd)->cmd.eval(script, paramsSize, params), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, ProtocolCommand.EVAL,
					(cmd)->cmd.eval(script, paramsSize, params), (v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.EVAL, (cmd)->cmd.eval(script, paramsSize, params),
					(v)->v)
					.run(args);
		}
	}

	@Override
	public Object eval(final String script, final String[] keys, final String[] arguments) {
		final CommandArguments args = CommandArguments.create(script).add(keys)
				.add(arguments);
		final List<String> keysList = Arrays.asList(keys);
		final List<String> argumentsList = Arrays.asList(arguments);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, ProtocolCommand.EVAL,
					(cmd)->cmd.eval(script, keysList, argumentsList), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, ProtocolCommand.EVAL,
					(cmd)->cmd.eval(script, keysList, argumentsList), (v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.EVAL,
					(cmd)->cmd.eval(script, keysList, argumentsList), (v)->v)
					.run(args);
		}
	}

	@Override
	public Object eval(final byte[] script, final byte[][] keys, final byte[][] arguments) {
		final CommandArguments args = CommandArguments.create(script).add(keys)
				.add(arguments);
		final List<byte[]> keysList = Arrays.asList(keys);
		final List<byte[]> argumentsList = Arrays.asList(arguments);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, ProtocolCommand.EVAL,
					(cmd)->cmd.eval(script, keysList, argumentsList), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, ProtocolCommand.EVAL,
					(cmd)->cmd.eval(script, keysList, argumentsList), (v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.EVAL,
					(cmd)->cmd.eval(script, keysList, argumentsList), (v)->v)
					.run(args);
		}
	}

	@Override
	public Object evalSha(final String digest) {
		final CommandArguments args = CommandArguments.create(digest);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, ProtocolCommand.EVALSHA, (cmd)->cmd.evalsha(digest),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, ProtocolCommand.EVALSHA, (cmd)->cmd.evalsha(digest),
					(v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.EVALSHA, (cmd)->cmd.evalsha(digest), (v)->v)
					.run(args);
		}
	}

	@Override
	public Object evalSha(final byte[] digest) {
		final CommandArguments args = CommandArguments.create(digest);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, ProtocolCommand.EVALSHA, (cmd)->cmd.evalsha(digest),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, ProtocolCommand.EVALSHA, (cmd)->cmd.evalsha(digest),
					(v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.EVALSHA, (cmd)->cmd.evalsha(digest), (v)->v)
					.run(args);
		}
	}

	@Override
	public Object evalSha(final String digest, final String... params) {
		final CommandArguments args = CommandArguments.create(digest).add(params);
		final int paramsSize = params == null ? 0 : params.length;

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, ProtocolCommand.EVALSHA,
					(cmd)->cmd.evalsha(digest, paramsSize, params), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, ProtocolCommand.EVALSHA,
					(cmd)->cmd.evalsha(digest, paramsSize, params), (v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.EVALSHA,
					(cmd)->cmd.evalsha(digest, paramsSize, params), (v)->v)
					.run(args);
		}
	}

	@Override
	public Object evalSha(final byte[] digest, final byte[]... params) {
		final CommandArguments args = CommandArguments.create(digest).add(params);
		final int paramsSize = params == null ? 0 : params.length;

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, ProtocolCommand.EVALSHA,
					(cmd)->cmd.evalsha(digest, paramsSize, params), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, ProtocolCommand.EVALSHA,
					(cmd)->cmd.evalsha(digest, paramsSize, params), (v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.EVALSHA,
					(cmd)->cmd.evalsha(digest, paramsSize, params), (v)->v)
					.run(args);
		}
	}

	@Override
	public Object evalSha(final String digest, final String[] keys, final String[] arguments) {
		final CommandArguments args = CommandArguments.create(digest).add(keys)
				.add(arguments);
		final List<String> keysList = Arrays.asList(keys);
		final List<String> argumentsList = Arrays.asList(arguments);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, ProtocolCommand.EVALSHA,
					(cmd)->cmd.eval(digest, keysList, argumentsList), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, ProtocolCommand.EVALSHA,
					(cmd)->cmd.eval(digest, keysList, argumentsList), (v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.EVALSHA,
					(cmd)->cmd.eval(digest, keysList, argumentsList), (v)->v)
					.run(args);
		}
	}

	@Override
	public Object evalSha(final byte[] digest, final byte[][] keys, final byte[][] arguments) {
		final CommandArguments args = CommandArguments.create(digest).add(keys)
				.add(arguments);
		final List<byte[]> keysList = Arrays.asList(keys);
		final List<byte[]> argumentsList = Arrays.asList(arguments);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, ProtocolCommand.EVALSHA,
					(cmd)->cmd.eval(digest, keysList, argumentsList), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, ProtocolCommand.EVALSHA,
					(cmd)->cmd.eval(digest, keysList, argumentsList), (v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.EVALSHA,
					(cmd)->cmd.eval(digest, keysList, argumentsList), (v)->v)
					.run(args);
		}
	}

	@Override
	public List<Boolean> scriptExists(final String... sha1) {
		final CommandArguments args = CommandArguments.create(sha1);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, ProtocolCommand.SCRIPT_EXISTS,
					(cmd)->cmd.scriptExists(null, sha1), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, ProtocolCommand.SCRIPT_EXISTS,
					(cmd)->cmd.scriptExists(null, sha1), (v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.SCRIPT_EXISTS, (cmd)->cmd.scriptExists(sha1),
					(v)->v)
					.run(args);
		}
	}

	@Override
	public List<Boolean> scriptExists(final byte[]... sha1) {
		final CommandArguments args = CommandArguments.create(sha1);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, ProtocolCommand.SCRIPT_EXISTS,
					(cmd)->cmd.scriptExists(null, sha1), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, ProtocolCommand.SCRIPT_EXISTS,
					(cmd)->cmd.scriptExists(null, sha1), (v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.SCRIPT_EXISTS, (cmd)->cmd.scriptExists(sha1),
					(v)->v)
					.run(args);
		}
	}

	@Override
	public Status scriptFlush() {
		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, ProtocolCommand.SCRIPT_FLUSH,
					(cmd)->cmd.scriptFlush((String) null), okStatusConverter)
					.run();
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, ProtocolCommand.SCRIPT_FLUSH,
					(cmd)->cmd.scriptFlush((String) null), okStatusConverter)
					.run();
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.SCRIPT_FLUSH, (cmd)->cmd.scriptFlush(),
					okStatusConverter)
					.run();
		}
	}

	@Override
	public Status scriptFlush(final FlushMode mode) {
		final CommandArguments args = CommandArguments.create(mode);
		final redis.clients.jedis.args.FlushMode flushMode = (new FlushModeConverter()).convert(mode);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, ProtocolCommand.SCRIPT_FLUSH,
					(cmd)->cmd.scriptFlush((String) null, flushMode), okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, ProtocolCommand.SCRIPT_FLUSH,
					(cmd)->cmd.scriptFlush((String) null, flushMode), okStatusConverter)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.SCRIPT_FLUSH, (cmd)->cmd.scriptFlush(flushMode),
					okStatusConverter)
					.run(args);
		}
	}

	@Override
	public String scriptLoad(final String script) {
		final CommandArguments args = CommandArguments.create(script);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, ProtocolCommand.SCRIPT_LOAD,
					(cmd)->cmd.scriptLoad(script, null), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, ProtocolCommand.SCRIPT_LOAD,
					(cmd)->cmd.scriptLoad(script, null), (v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.SCRIPT_LOAD, (cmd)->cmd.scriptLoad(script),
					(v)->v)
					.run(args);
		}
	}

	@Override
	public byte[] scriptLoad(final byte[] script) {
		final CommandArguments args = CommandArguments.create(script);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, ProtocolCommand.SCRIPT_LOAD,
					(cmd)->cmd.scriptLoad(script, null), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, ProtocolCommand.SCRIPT_LOAD,
					(cmd)->cmd.scriptLoad(script, null), (v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.SCRIPT_LOAD, (cmd)->cmd.scriptLoad(script),
					(v)->v)
					.run(args);
		}
	}

	@Override
	public Status scriptKill() {
		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, ProtocolCommand.SCRIPT_KILL,
					(cmd)->cmd.scriptKill((String) null), okStatusConverter)
					.run();
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, ProtocolCommand.SCRIPT_KILL,
					(cmd)->cmd.scriptKill((String) null), okStatusConverter)
					.run();
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.SCRIPT_KILL, (cmd)->cmd.scriptKill(),
					okStatusConverter)
					.run();
		}
	}

}
