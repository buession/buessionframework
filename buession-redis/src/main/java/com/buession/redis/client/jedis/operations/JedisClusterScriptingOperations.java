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
 * | Copyright @ 2013-2023 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.client.jedis.operations;

import com.buession.lang.Status;
import com.buession.redis.client.jedis.JedisClusterClient;
import com.buession.redis.core.FlushMode;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.internal.convert.jedis.params.FlushModeConverter;
import com.buession.redis.core.internal.convert.response.OkStatusConverter;

import java.util.Arrays;
import java.util.List;

/**
 * Jedis 集群模式 Script 命令操作
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public final class JedisClusterScriptingOperations extends AbstractScriptingOperations<JedisClusterClient> {

	public JedisClusterScriptingOperations(final JedisClusterClient client) {
		super(client);
	}

	@Override
	public Object eval(final String script) {
		final CommandArguments args = CommandArguments.create("script", script);
		return new JedisClusterCommand<>(client, ProtocolCommand.EVAL)
				.general((cmd)->cmd.eval(script))
				.pipeline((cmd)->cmd.eval(script))
				.transaction((cmd)->cmd.eval(script))
				.run(args);
	}

	@Override
	public Object eval(final byte[] script) {
		final CommandArguments args = CommandArguments.create("script", script);
		return new JedisClusterCommand<>(client, ProtocolCommand.EVAL)
				.general((cmd)->cmd.eval(script))
				.pipeline((cmd)->cmd.eval(script))
				.transaction((cmd)->cmd.eval(script))
				.run(args);
	}

	@Override
	public Object eval(final String script, final String... params) {
		final CommandArguments args = CommandArguments.create("script", script).put("params", (Object[]) params);
		final int paramsSize = params == null ? 0 : params.length;
		return new JedisClusterCommand<>(client, ProtocolCommand.EVAL)
				.general((cmd)->cmd.eval(script, paramsSize, params))
				.pipeline((cmd)->cmd.eval(script, paramsSize, params))
				.transaction((cmd)->cmd.eval(script, paramsSize, params))
				.run(args);
	}

	@Override
	public Object eval(final byte[] script, final byte[]... params) {
		final CommandArguments args = CommandArguments.create("script", script).put("params", (Object[]) params);
		final int paramsSize = params == null ? 0 : params.length;
		return new JedisClusterCommand<>(client, ProtocolCommand.EVAL)
				.general((cmd)->cmd.eval(script, paramsSize, params))
				.pipeline((cmd)->cmd.eval(script, paramsSize, params))
				.transaction((cmd)->cmd.eval(script, paramsSize, params))
				.run(args);
	}

	@Override
	public Object eval(final String script, final String[] keys, final String[] arguments) {
		final CommandArguments args = CommandArguments.create("script", script).put("keys", (Object[]) keys)
				.put("arguments", (Object[]) arguments);
		return new JedisClusterCommand<>(client, ProtocolCommand.EVAL)
				.general((cmd)->cmd.eval(script, Arrays.asList(keys), Arrays.asList(arguments)))
				.pipeline((cmd)->cmd.eval(script, Arrays.asList(keys), Arrays.asList(arguments)))
				.transaction((cmd)->cmd.eval(script, Arrays.asList(keys), Arrays.asList(arguments)))
				.run(args);
	}

	@Override
	public Object eval(final byte[] script, final byte[][] keys, final byte[][] arguments) {
		final CommandArguments args = CommandArguments.create("script", script).put("keys", (Object[]) keys)
				.put("arguments", (Object[]) arguments);
		return new JedisClusterCommand<>(client, ProtocolCommand.EVAL)
				.general((cmd)->cmd.eval(script, Arrays.asList(keys), Arrays.asList(arguments)))
				.pipeline((cmd)->cmd.eval(script, Arrays.asList(keys), Arrays.asList(arguments)))
				.transaction((cmd)->cmd.eval(script, Arrays.asList(keys), Arrays.asList(arguments)))
				.run(args);
	}

	@Override
	public Object evalSha(final String digest) {
		final CommandArguments args = CommandArguments.create("digest", digest);
		return new JedisClusterCommand<>(client, ProtocolCommand.EVALSHA)
				.general((cmd)->cmd.evalsha(digest))
				.pipeline((cmd)->cmd.evalsha(digest))
				.transaction((cmd)->cmd.evalsha(digest))
				.run(args);
	}

	@Override
	public Object evalSha(final byte[] digest) {
		final CommandArguments args = CommandArguments.create("digest", digest);
		return new JedisClusterCommand<>(client, ProtocolCommand.EVALSHA)
				.general((cmd)->cmd.evalsha(digest))
				.pipeline((cmd)->cmd.evalsha(digest))
				.transaction((cmd)->cmd.evalsha(digest))
				.run(args);
	}

	@Override
	public Object evalSha(final String digest, final String... params) {
		final CommandArguments args = CommandArguments.create("digest", digest).put("params", (Object[]) params);
		final int paramsSize = params == null ? 0 : params.length;
		return new JedisClusterCommand<>(client, ProtocolCommand.EVALSHA)
				.general((cmd)->cmd.evalsha(digest, paramsSize, params))
				.pipeline((cmd)->cmd.evalsha(digest, paramsSize, params))
				.transaction((cmd)->cmd.evalsha(digest, paramsSize, params))
				.run(args);
	}

	@Override
	public Object evalSha(final byte[] digest, final byte[]... params) {
		final CommandArguments args = CommandArguments.create("digest", digest).put("params", (Object[]) params);
		final int paramsSize = params == null ? 0 : params.length;
		return new JedisClusterCommand<>(client, ProtocolCommand.EVALSHA)
				.general((cmd)->cmd.evalsha(digest, paramsSize, params))
				.pipeline((cmd)->cmd.evalsha(digest, paramsSize, params))
				.transaction((cmd)->cmd.evalsha(digest, paramsSize, params))
				.run(args);
	}

	@Override
	public Object evalSha(final String digest, final String[] keys, final String[] arguments) {
		final CommandArguments args = CommandArguments.create("digest", digest).put("keys", (Object[]) keys)
				.put("arguments", (Object[]) arguments);
		return new JedisClusterCommand<>(client, ProtocolCommand.EVALSHA)
				.general((cmd)->cmd.evalsha(digest, Arrays.asList(keys), Arrays.asList(arguments)))
				.pipeline((cmd)->cmd.evalsha(digest, Arrays.asList(keys), Arrays.asList(arguments)))
				.transaction((cmd)->cmd.evalsha(digest, Arrays.asList(keys), Arrays.asList(arguments)))
				.run(args);
	}

	@Override
	public Object evalSha(final byte[] digest, final byte[][] keys, final byte[][] arguments) {
		final CommandArguments args = CommandArguments.create("digest", digest).put("keys", (Object[]) keys)
				.put("arguments", (Object[]) arguments);
		return new JedisClusterCommand<>(client, ProtocolCommand.EVALSHA)
				.general((cmd)->cmd.evalsha(digest, Arrays.asList(keys), Arrays.asList(arguments)))
				.pipeline((cmd)->cmd.evalsha(digest, Arrays.asList(keys), Arrays.asList(arguments)))
				.transaction((cmd)->cmd.evalsha(digest, Arrays.asList(keys), Arrays.asList(arguments)))
				.run(args);
	}

	@Override
	public List<Boolean> scriptExists(final String... sha1) {
		final CommandArguments args = CommandArguments.create("sha1", (Object[]) sha1);
		return new JedisClusterCommand<List<Boolean>>(client, ProtocolCommand.SCRIPT_EXISTS)
				.general((cmd)->cmd.scriptExists(null, sha1))
				.pipeline((cmd)->cmd.scriptExists(null, sha1))
				.transaction((cmd)->cmd.scriptExists(null, sha1))
				.run(args);
	}

	@Override
	public List<Boolean> scriptExists(final byte[]... sha1) {
		final CommandArguments args = CommandArguments.create("sha1", (Object[]) sha1);
		return new JedisClusterCommand<List<Boolean>>(client, ProtocolCommand.SCRIPT_EXISTS)
				.general((cmd)->cmd.scriptExists(null, sha1))
				.pipeline((cmd)->cmd.scriptExists(null, sha1))
				.transaction((cmd)->cmd.scriptExists(null, sha1))
				.run(args);
	}

	@Override
	public Status scriptFlush() {
		return new JedisClusterCommand<Status>(client, ProtocolCommand.SCRIPT_FLUSH)
				.general((cmd)->cmd.scriptFlush((String) null), new OkStatusConverter())
				.pipeline((cmd)->cmd.scriptFlush((String) null), new OkStatusConverter())
				.transaction((cmd)->cmd.scriptFlush((String) null), new OkStatusConverter())
				.run();
	}

	@Override
	public Status scriptFlush(final FlushMode mode) {
		final CommandArguments args = CommandArguments.create("mode", mode);
		final redis.clients.jedis.args.FlushMode flushMode = (new FlushModeConverter()).convert(mode);
		return new JedisClusterCommand<Status>(client, ProtocolCommand.SCRIPT_FLUSH)
				.general((cmd)->cmd.scriptFlush((String) null, flushMode), new OkStatusConverter())
				.pipeline((cmd)->cmd.scriptFlush((String) null, flushMode), new OkStatusConverter())
				.transaction((cmd)->cmd.scriptFlush((String) null, flushMode), new OkStatusConverter())
				.run(args);
	}

	@Override
	public String scriptLoad(final String script) {
		final CommandArguments args = CommandArguments.create("script", script);
		return new JedisClusterCommand<String>(client, ProtocolCommand.SCRIPT_LOAD)
				.general((cmd)->cmd.scriptLoad(script, null))
				.pipeline((cmd)->cmd.scriptLoad(script, null))
				.transaction((cmd)->cmd.scriptLoad(script, null))
				.run(args);
	}

	@Override
	public byte[] scriptLoad(final byte[] script) {
		final CommandArguments args = CommandArguments.create("script", script);
		return new JedisClusterCommand<byte[]>(client, ProtocolCommand.SCRIPT_LOAD)
				.general((cmd)->cmd.scriptLoad(script, null))
				.pipeline((cmd)->cmd.scriptLoad(script, null))
				.transaction((cmd)->cmd.scriptLoad(script, null))
				.run(args);
	}

	@Override
	public Status scriptKill() {
		return new JedisClusterCommand<Status>(client, ProtocolCommand.SCRIPT_KILL)
				.general((cmd)->cmd.scriptKill((String) null), new OkStatusConverter())
				.pipeline((cmd)->cmd.scriptKill((String) null), new OkStatusConverter())
				.transaction((cmd)->cmd.scriptKill((String) null), new OkStatusConverter())
				.run();
	}

}
