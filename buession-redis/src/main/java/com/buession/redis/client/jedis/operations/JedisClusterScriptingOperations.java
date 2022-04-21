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

	public JedisClusterScriptingOperations(final JedisClusterClient client){
		super(client);
	}

	@Override
	public Object eval(final String script){
		final CommandArguments args = CommandArguments.create("script", script);
		final JedisClusterCommand<Object> command = new JedisClusterCommand<>(client, ProtocolCommand.EVAL)
				.general((cmd)->cmd.eval(script))
				.pipeline((cmd)->cmd.eval(script))
				.transaction((cmd)->cmd.eval(script));
		return execute(command, args);
	}

	@Override
	public Object eval(final byte[] script){
		final CommandArguments args = CommandArguments.create("script", script);
		final JedisClusterCommand<Object> command = new JedisClusterCommand<>(client, ProtocolCommand.EVAL)
				.general((cmd)->cmd.eval(script))
				.pipeline((cmd)->cmd.eval(script))
				.transaction((cmd)->cmd.eval(script));
		return execute(command, args);
	}

	@Override
	public Object eval(final String script, final String... params){
		final CommandArguments args = CommandArguments.create("script", script).put("params", params);
		final int paramsSize = params == null ? 0 : params.length;
		final JedisClusterCommand<Object> command = new JedisClusterCommand<>(client, ProtocolCommand.EVAL)
				.general((cmd)->cmd.eval(script, paramsSize, params))
				.pipeline((cmd)->cmd.eval(script, paramsSize, params))
				.transaction((cmd)->cmd.eval(script, paramsSize, params));
		return execute(command, args);
	}

	@Override
	public Object eval(final byte[] script, final byte[]... params){
		final CommandArguments args = CommandArguments.create("script", script).put("params", params);
		final int paramsSize = params == null ? 0 : params.length;
		final JedisClusterCommand<Object> command = new JedisClusterCommand<>(client, ProtocolCommand.EVAL)
				.general((cmd)->cmd.eval(script, paramsSize, params))
				.pipeline((cmd)->cmd.eval(script, paramsSize, params))
				.transaction((cmd)->cmd.eval(script, paramsSize, params));
		return execute(command, args);
	}

	@Override
	public Object eval(final String script, final String[] keys, final String[] arguments){
		final CommandArguments args = CommandArguments.create("script", script).put("keys", keys)
				.put("arguments", arguments);
		final JedisClusterCommand<Object> command = new JedisClusterCommand<>(client, ProtocolCommand.EVAL)
				.general((cmd)->cmd.eval(script, Arrays.asList(keys), Arrays.asList(arguments)))
				.pipeline((cmd)->cmd.eval(script, Arrays.asList(keys), Arrays.asList(arguments)))
				.transaction((cmd)->cmd.eval(script, Arrays.asList(keys), Arrays.asList(arguments)));
		return execute(command, args);
	}

	@Override
	public Object eval(final byte[] script, final byte[][] keys, final byte[][] arguments){
		final CommandArguments args = CommandArguments.create("script", script).put("keys", keys)
				.put("arguments", arguments);
		final JedisClusterCommand<Object> command = new JedisClusterCommand<>(client, ProtocolCommand.EVAL)
				.general((cmd)->cmd.eval(script, Arrays.asList(keys), Arrays.asList(arguments)))
				.pipeline((cmd)->cmd.eval(script, Arrays.asList(keys), Arrays.asList(arguments)))
				.transaction((cmd)->cmd.eval(script, Arrays.asList(keys), Arrays.asList(arguments)));
		return execute(command, args);
	}

	@Override
	public Object evalSha(final String digest){
		final CommandArguments args = CommandArguments.create("digest", digest);
		final JedisClusterCommand<Object> command = new JedisClusterCommand<>(client, ProtocolCommand.EVALSHA)
				.general((cmd)->cmd.evalsha(digest))
				.pipeline((cmd)->cmd.evalsha(digest))
				.transaction((cmd)->cmd.evalsha(digest));
		return execute(command, args);
	}

	@Override
	public Object evalSha(final byte[] digest){
		final CommandArguments args = CommandArguments.create("digest", digest);
		final JedisClusterCommand<Object> command = new JedisClusterCommand<>(client, ProtocolCommand.EVALSHA)
				.general((cmd)->cmd.evalsha(digest))
				.pipeline((cmd)->cmd.evalsha(digest))
				.transaction((cmd)->cmd.evalsha(digest));
		return execute(command, args);
	}

	@Override
	public Object evalSha(final String digest, final String... params){
		final CommandArguments args = CommandArguments.create("digest", digest).put("params", params);
		final int paramsSize = params == null ? 0 : params.length;
		final JedisClusterCommand<Object> command = new JedisClusterCommand<>(client, ProtocolCommand.EVALSHA)
				.general((cmd)->cmd.evalsha(digest, paramsSize, params))
				.pipeline((cmd)->cmd.evalsha(digest, paramsSize, params))
				.transaction((cmd)->cmd.evalsha(digest, paramsSize, params));
		return execute(command, args);
	}

	@Override
	public Object evalSha(final byte[] digest, final byte[]... params){
		final CommandArguments args = CommandArguments.create("digest", digest).put("params", params);
		final int paramsSize = params == null ? 0 : params.length;
		final JedisClusterCommand<Object> command = new JedisClusterCommand<>(client, ProtocolCommand.EVALSHA)
				.general((cmd)->cmd.evalsha(digest, paramsSize, params))
				.pipeline((cmd)->cmd.evalsha(digest, paramsSize, params))
				.transaction((cmd)->cmd.evalsha(digest, paramsSize, params));
		return execute(command, args);
	}

	@Override
	public Object evalSha(final String digest, final String[] keys, final String[] arguments){
		final CommandArguments args = CommandArguments.create("digest", digest).put("keys", keys)
				.put("arguments", arguments);
		final JedisClusterCommand<Object> command = new JedisClusterCommand<>(client, ProtocolCommand.EVALSHA)
				.general((cmd)->cmd.evalsha(digest, Arrays.asList(keys), Arrays.asList(arguments)))
				.pipeline((cmd)->cmd.evalsha(digest, Arrays.asList(keys), Arrays.asList(arguments)))
				.transaction((cmd)->cmd.evalsha(digest, Arrays.asList(keys), Arrays.asList(arguments)));
		return execute(command, args);
	}

	@Override
	public Object evalSha(final byte[] digest, final byte[][] keys, final byte[][] arguments){
		final CommandArguments args = CommandArguments.create("digest", digest).put("keys", keys)
				.put("arguments", arguments);
		final JedisClusterCommand<Object> command = new JedisClusterCommand<>(client, ProtocolCommand.EVALSHA)
				.general((cmd)->cmd.evalsha(digest, Arrays.asList(keys), Arrays.asList(arguments)))
				.pipeline((cmd)->cmd.evalsha(digest, Arrays.asList(keys), Arrays.asList(arguments)))
				.transaction((cmd)->cmd.evalsha(digest, Arrays.asList(keys), Arrays.asList(arguments)));
		return execute(command, args);
	}

	@Override
	public List<Boolean> scriptExists(final String... sha1){
		final CommandArguments args = CommandArguments.create("sha1", sha1);
		final JedisClusterCommand<List<Boolean>> command = new JedisClusterCommand<List<Boolean>>(client,
				ProtocolCommand.SCRIPT_EXISTS)
				.general((cmd)->cmd.scriptExists(null, sha1))
				.pipeline((cmd)->cmd.scriptExists(null, sha1))
				.transaction((cmd)->cmd.scriptExists(null, sha1));
		return execute(command, args);
	}

	@Override
	public List<Boolean> scriptExists(final byte[]... sha1){
		final CommandArguments args = CommandArguments.create("sha1", sha1);
		final JedisClusterCommand<List<Boolean>> command = new JedisClusterCommand<List<Boolean>>(client,
				ProtocolCommand.SCRIPT_EXISTS)
				.general((cmd)->cmd.scriptExists(null, sha1))
				.pipeline((cmd)->cmd.scriptExists(null, sha1))
				.transaction((cmd)->cmd.scriptExists(null, sha1));
		return execute(command, args);
	}

	@Override
	public Status scriptFlush(){
		final JedisClusterCommand<Status> command = new JedisClusterCommand<Status>(client,
				ProtocolCommand.SCRIPT_FLUSH)
				.general((cmd)->cmd.scriptFlush((String) null), OkStatusConverter.INSTANCE)
				.pipeline((cmd)->cmd.scriptFlush((String) null), OkStatusConverter.INSTANCE)
				.transaction((cmd)->cmd.scriptFlush((String) null), OkStatusConverter.INSTANCE);
		return execute(command);
	}

	@Override
	public Status scriptFlush(final FlushMode mode){
		final CommandArguments args = CommandArguments.create("mode", mode);
		final redis.clients.jedis.args.FlushMode flushMode = FlushModeConverter.INSTANCE.convert(mode);
		final JedisClusterCommand<Status> command = new JedisClusterCommand<Status>(client,
				ProtocolCommand.SCRIPT_FLUSH)
				.general((cmd)->cmd.scriptFlush((String) null, flushMode), OkStatusConverter.INSTANCE)
				.pipeline((cmd)->cmd.scriptFlush((String) null, flushMode), OkStatusConverter.INSTANCE)
				.transaction((cmd)->cmd.scriptFlush((String) null, flushMode), OkStatusConverter.INSTANCE);
		return execute(command, args);
	}

	@Override
	public String scriptLoad(final String script){
		final CommandArguments args = CommandArguments.create("script", script);
		final JedisClusterCommand<String> command = new JedisClusterCommand<String>(client, ProtocolCommand.SCRIPT_LOAD)
				.general((cmd)->cmd.scriptLoad(script, null))
				.pipeline((cmd)->cmd.scriptLoad(script, null))
				.transaction((cmd)->cmd.scriptLoad(script, null));
		return execute(command, args);
	}

	@Override
	public byte[] scriptLoad(final byte[] script){
		final CommandArguments args = CommandArguments.create("script", script);
		final JedisClusterCommand<byte[]> command = new JedisClusterCommand<byte[]>(client, ProtocolCommand.SCRIPT_LOAD)
				.general((cmd)->cmd.scriptLoad(script, null))
				.pipeline((cmd)->cmd.scriptLoad(script, null))
				.transaction((cmd)->cmd.scriptLoad(script, null));
		return execute(command, args);
	}

	@Override
	public Status scriptKill(){
		final JedisClusterCommand<Status> command = new JedisClusterCommand<Status>(client, ProtocolCommand.SCRIPT_KILL)
				.general((cmd)->cmd.scriptKill((String) null), OkStatusConverter.INSTANCE)
				.pipeline((cmd)->cmd.scriptKill((String) null), OkStatusConverter.INSTANCE)
				.transaction((cmd)->cmd.scriptKill((String) null), OkStatusConverter.INSTANCE);
		return execute(command);
	}

}
