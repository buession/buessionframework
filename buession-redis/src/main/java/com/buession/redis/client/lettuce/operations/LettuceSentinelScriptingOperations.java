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

import com.buession.lang.Status;
import com.buession.redis.client.lettuce.LettuceSentinelClient;
import com.buession.redis.core.FlushMode;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.ProtocolCommand;

import java.util.List;

/**
 * Lettuce 哨兵模式 Script 命令操作
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public final class LettuceSentinelScriptingOperations extends AbstractScriptingOperations<LettuceSentinelClient> {

	public LettuceSentinelScriptingOperations(final LettuceSentinelClient client) {
		super(client);
	}

	@Override
	public Object eval(final String script) {
		final CommandArguments args = CommandArguments.create("script", script);
		return notCommand(client, ProtocolCommand.EVAL, args);
	}

	@Override
	public Object eval(final byte[] script) {
		final CommandArguments args = CommandArguments.create("script", script);
		return notCommand(client, ProtocolCommand.EVAL, args);
	}

	@Override
	public Object eval(final String script, final String... params) {
		final CommandArguments args = CommandArguments.create("script", script).put("params", (Object[]) params);
		return notCommand(client, ProtocolCommand.EVAL, args);
	}

	@Override
	public Object eval(final byte[] script, final byte[]... params) {
		final CommandArguments args = CommandArguments.create("script", script).put("params", (Object[]) params);
		return notCommand(client, ProtocolCommand.EVAL, args);
	}

	@Override
	public Object eval(final String script, final String[] keys, final String[] arguments) {
		final CommandArguments args = CommandArguments.create("script", script).put("keys", (Object[]) keys)
				.put("arguments", (Object[]) arguments);
		return notCommand(client, ProtocolCommand.EVAL, args);
	}

	@Override
	public Object eval(final byte[] script, final byte[][] keys, final byte[][] arguments) {
		final CommandArguments args = CommandArguments.create("script", script).put("keys", (Object[]) keys)
				.put("arguments", (Object[]) arguments);
		return notCommand(client, ProtocolCommand.EVAL, args);
	}

	@Override
	public Object evalSha(final String digest) {
		final CommandArguments args = CommandArguments.create("digest", digest);
		return notCommand(client, ProtocolCommand.EVALSHA, args);
	}

	@Override
	public Object evalSha(final String digest, final String... params) {
		final CommandArguments args = CommandArguments.create("digest", digest).put("params", (Object[]) params);
		return notCommand(client, ProtocolCommand.EVALSHA, args);
	}

	@Override
	public Object evalSha(final String digest, final String[] keys, final String[] arguments) {
		final CommandArguments args = CommandArguments.create("digest", digest).put("keys", (Object[]) keys)
				.put("arguments", (Object[]) arguments);
		return notCommand(client, ProtocolCommand.EVALSHA, args);
	}

	@Override
	public List<Boolean> scriptExists(final String... sha1) {
		final CommandArguments args = CommandArguments.create("sha1", (Object[]) sha1);
		return notCommand(client, ProtocolCommand.SCRIPT_EXISTS, args);
	}

	@Override
	public List<Boolean> scriptExists(final byte[]... sha1) {
		final CommandArguments args = CommandArguments.create("sha1", (Object[]) sha1);
		return notCommand(client, ProtocolCommand.SCRIPT_EXISTS, args);
	}

	@Override
	public Status scriptFlush() {
		return notCommand(client, ProtocolCommand.SCRIPT_FLUSH);
	}

	@Override
	public Status scriptFlush(final FlushMode mode) {
		final CommandArguments args = CommandArguments.create("mode", mode);
		return notCommand(client, ProtocolCommand.SCRIPT_FLUSH, args);
	}

	@Override
	public String scriptLoad(final String script) {
		final CommandArguments args = CommandArguments.create("script", script);
		return notCommand(client, ProtocolCommand.SCRIPT_LOAD, args);
	}

	@Override
	public byte[] scriptLoad(final byte[] script) {
		final CommandArguments args = CommandArguments.create("script", script);
		return notCommand(client, ProtocolCommand.SCRIPT_LOAD, args);
	}

	@Override
	public Status scriptKill() {
		return notCommand(client, ProtocolCommand.SCRIPT_KILL);
	}

}
