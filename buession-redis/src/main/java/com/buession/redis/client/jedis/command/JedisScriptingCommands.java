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
 * | Copyright @ 2013-2026 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.client.jedis.command;

import com.buession.core.builder.ListBuilder;
import com.buession.core.converter.ListConverter;
import com.buession.lang.Status;
import com.buession.redis.client.jedis.JedisRedisClient;
import com.buession.redis.core.FlushMode;
import com.buession.redis.core.FunctionRestoreMode;
import com.buession.redis.core.FunctionStats;
import com.buession.redis.core.LibraryInfo;
import com.buession.redis.core.ScriptDebugMode;
import com.buession.redis.core.command.Command;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.ScriptingCommands;
import com.buession.redis.core.command.SubCommand;
import com.buession.redis.core.internal.convert.jedis.params.FlushModeConverter;
import com.buession.redis.core.internal.convert.jedis.params.FunctionRestoreModeFunctionRestorePolicyConverter;
import com.buession.redis.core.internal.convert.jedis.response.FunctionStatsConverter;
import com.buession.redis.core.internal.convert.jedis.response.LibraryInfoConverter;
import com.buession.redis.core.internal.convert.response.OkStatusConverter;
import com.buession.redis.utils.SafeEncoder;
import redis.clients.jedis.UnifiedJedis;

import java.util.Arrays;
import java.util.List;

/**
 * Jedis Script 命令
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public final class JedisScriptingCommands extends AbstractJedisRedisCommands implements ScriptingCommands {

	public JedisScriptingCommands(final JedisRedisClient client) {
		super(client);
	}

	@Override
	public Object eval(final String script) {
		final CommandArguments args = CommandArguments.create(script);
		return eval(args, (cmd)->cmd.eval(script));
	}

	@Override
	public Object eval(final byte[] script) {
		final CommandArguments args = CommandArguments.create(script);
		return eval(args, (cmd)->cmd.eval(script));
	}

	@Override
	public Object eval(final String script, final String... keys) {
		final CommandArguments args = CommandArguments.create(script).add(keys.length).add(keys);
		return eval(args, (cmd)->cmd.eval(script, keys.length, keys));
	}

	@Override
	public Object eval(final byte[] script, final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(script).add(keys.length).add(keys);
		return eval(args, (cmd)->cmd.eval(script, keys.length, keys));
	}

	@Override
	public Object eval(final String script, final String[] keys, final String[] arguments) {
		final CommandArguments args = CommandArguments.create(script).add(keys.length).add(keys).add(arguments);
		return eval(args, (cmd)->cmd.eval(script, Arrays.asList(keys), Arrays.asList(arguments)));
	}

	@Override
	public Object eval(final byte[] script, final byte[][] keys, final byte[][] arguments) {
		final CommandArguments args = CommandArguments.create(script).add(keys.length).add(keys).add(arguments);
		return eval(args, (cmd)->cmd.eval(script, Arrays.asList(keys), Arrays.asList(arguments)));
	}

	@Override
	public Object evalRo(final String script) {
		final CommandArguments args = CommandArguments.create(script);
		return evalRo(args, (cmd)->cmd.evalReadonly(script, ListBuilder.of(), ListBuilder.of()));
	}

	@Override
	public Object evalRo(final byte[] script) {
		final CommandArguments args = CommandArguments.create(script);
		return evalRo(args, (cmd)->cmd.evalReadonly(script, ListBuilder.of(), ListBuilder.of()));
	}

	@Override
	public Object evalRo(final String script, final String... keys) {
		final CommandArguments args = CommandArguments.create(script).add(keys.length).add(keys);
		return evalRo(args, (cmd)->cmd.evalReadonly(script, Arrays.asList(keys), ListBuilder.of()));
	}

	@Override
	public Object evalRo(final byte[] script, final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(script).add(keys.length).add(keys);
		return evalRo(args, (cmd)->cmd.evalReadonly(script, Arrays.asList(keys), ListBuilder.of()));
	}

	@Override
	public Object evalRo(final String script, final String[] keys, final String[] arguments) {
		final CommandArguments args = CommandArguments.create(script).add(keys).add(arguments);
		return evalRo(args, (cmd)->cmd.evalReadonly(script, Arrays.asList(keys), Arrays.asList(arguments)));
	}

	@Override
	public Object evalRo(final byte[] script, final byte[][] keys, final byte[][] arguments) {
		final CommandArguments args = CommandArguments.create(script).add(keys).add(arguments);
		return evalRo(args, (cmd)->cmd.evalReadonly(script, Arrays.asList(keys), Arrays.asList(arguments)));
	}

	@Override
	public Object evalSha(final String digest) {
		final CommandArguments args = CommandArguments.create(digest);
		return evalSha(args, (cmd)->cmd.evalsha(digest));
	}

	@Override
	public Object evalSha(final byte[] digest) {
		final CommandArguments args = CommandArguments.create(digest);
		return evalSha(args, (cmd)->cmd.evalsha(digest));
	}

	@Override
	public Object evalSha(final String digest, final String... keys) {
		final CommandArguments args = CommandArguments.create(digest).add(keys.length).add(keys);
		return evalSha(args, (cmd)->cmd.evalsha(digest, keys.length, keys));
	}

	@Override
	public Object evalSha(final byte[] digest, final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(digest).add(keys.length).add(keys);
		return evalSha(args, (cmd)->cmd.evalsha(digest, keys.length, keys));
	}

	@Override
	public Object evalSha(final String digest, final String[] keys, final String[] arguments) {
		final CommandArguments args = CommandArguments.create(digest).add(keys.length).add(keys).add(arguments);
		return evalSha(args, (cmd)->cmd.evalsha(digest, Arrays.asList(keys), Arrays.asList(arguments)));
	}

	@Override
	public Object evalSha(final byte[] digest, final byte[][] keys, final byte[][] arguments) {
		final CommandArguments args = CommandArguments.create(digest).add(keys.length).add(keys).add(arguments);
		return evalSha(args, (cmd)->cmd.evalsha(digest, Arrays.asList(keys), Arrays.asList(arguments)));
	}

	@Override
	public Object evalShaRo(final String digest) {
		final CommandArguments args = CommandArguments.create(digest);
		return evalShaRo(digest, ListBuilder.of(), ListBuilder.of(), args);
	}

	@Override
	public Object evalShaRo(final byte[] digest) {
		final CommandArguments args = CommandArguments.create(digest);
		return evalShaRo(digest, ListBuilder.of(), ListBuilder.of(), args);
	}

	@Override
	public Object evalShaRo(final String digest, final String... keys) {
		final CommandArguments args = CommandArguments.create(digest).add(keys.length).add(keys);
		return evalShaRo(digest, Arrays.asList(keys), ListBuilder.of(), args);
	}

	@Override
	public Object evalShaRo(final byte[] digest, final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(digest).add(keys.length).add(keys);
		return evalShaRo(digest, Arrays.asList(keys), ListBuilder.of(), args);
	}

	@Override
	public Object evalShaRo(final String digest, final String[] keys, final String[] arguments) {
		final CommandArguments args = CommandArguments.create(digest).add(keys.length).add(keys).add(arguments);
		return evalShaRo(digest, Arrays.asList(keys), Arrays.asList(arguments), args);
	}

	@Override
	public Object evalShaRo(final byte[] digest, final byte[][] keys, final byte[][] arguments) {
		final CommandArguments args = CommandArguments.create(digest).add(keys.length).add(keys).add(arguments);
		return evalShaRo(digest, Arrays.asList(keys), Arrays.asList(arguments), args);
	}

	@Override
	public Object fCall(final String function) {
		final CommandArguments args = CommandArguments.create(function);
		return fCall(function, ListBuilder.of(), ListBuilder.of(), args);
	}

	@Override
	public Object fCall(final byte[] function) {
		final CommandArguments args = CommandArguments.create(function);
		return fCall(function, ListBuilder.of(), ListBuilder.of(), args);
	}

	@Override
	public Object fCall(final String function, final String... keys) {
		final CommandArguments args = CommandArguments.create(function).add(keys.length).add(keys);
		return fCall(function, Arrays.asList(keys), ListBuilder.of(), args);
	}

	@Override
	public Object fCall(final byte[] function, final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(function).add(keys.length).add(keys);
		return fCall(function, Arrays.asList(keys), ListBuilder.of(), args);
	}

	@Override
	public Object fCall(final String function, final String[] keys, final String[] arguments) {
		final CommandArguments args = CommandArguments.create(function).add(keys.length).add(keys).add(arguments);
		return fCall(function, Arrays.asList(keys), Arrays.asList(arguments), args);
	}

	@Override
	public Object fCall(final byte[] function, final byte[][] keys, final byte[][] arguments) {
		final CommandArguments args = CommandArguments.create(function).add(keys.length).add(keys).add(arguments);
		return fCall(function, Arrays.asList(keys), Arrays.asList(arguments), args);
	}

	@Override
	public Object fCallRo(final String function) {
		final CommandArguments args = CommandArguments.create(function);
		return fCallRo(function, ListBuilder.of(), ListBuilder.of(), args);
	}

	@Override
	public Object fCallRo(final byte[] function) {
		final CommandArguments args = CommandArguments.create(function);
		return fCallRo(function, ListBuilder.of(), ListBuilder.of(), args);
	}

	@Override
	public Object fCallRo(final String function, final String... keys) {
		final CommandArguments args = CommandArguments.create(function).add(keys.length).add(keys);
		return fCallRo(function, Arrays.asList(keys), ListBuilder.of(), args);
	}

	@Override
	public Object fCallRo(final byte[] function, final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(function).add(keys.length).add(keys);
		return fCallRo(function, Arrays.asList(keys), ListBuilder.of(), args);
	}

	@Override
	public Object fCallRo(final String function, final String[] keys, final String[] arguments) {
		final CommandArguments args = CommandArguments.create(function).add(keys.length).add(keys).add(arguments);
		return fCallRo(function, Arrays.asList(keys), Arrays.asList(arguments), args);
	}

	@Override
	public Object fCallRo(final byte[] function, final byte[][] keys, final byte[][] arguments) {
		final CommandArguments args = CommandArguments.create(function).add(keys.length).add(keys).add(arguments);
		return fCallRo(function, Arrays.asList(keys), Arrays.asList(arguments), args);
	}

	@Override
	public Status functionDelete(final String libraryName) {
		final CommandArguments args = CommandArguments.create(libraryName);
		return executeCommand(Command.FUNCTION, SubCommand.FUNCTION_DELETE, args,
				(cmd)->cmd.functionDelete(libraryName), new OkStatusConverter());
	}

	@Override
	public byte[] functionDump() {
		return executeCommand(Command.FUNCTION, SubCommand.FUNCTION_DUMP, (cmd)->cmd.functionDump(), (v)->v);
	}

	@Override
	public Status functionFlush() {
		return executeCommand(Command.FUNCTION, SubCommand.FUNCTION_FLUSH, (cmd)->cmd.functionFlush(),
				new OkStatusConverter());
	}

	@Override
	public Status functionFlush(final FlushMode flushMode) {
		final CommandArguments args = CommandArguments.create(flushMode);
		final FlushModeConverter flushModeConverter = new FlushModeConverter();
		return executeCommand(Command.FUNCTION, SubCommand.FUNCTION_FLUSH, args,
				(cmd)->cmd.functionFlush(flushModeConverter.convert(flushMode)), new OkStatusConverter());
	}

	@Override
	public Status functionKill() {
		return executeCommand(Command.FUNCTION, SubCommand.FUNCTION_KILL, (cmd)->cmd.functionKill(),
				new OkStatusConverter());
	}

	@Override
	public List<LibraryInfo> functionList() {
		return executeCommand(Command.FUNCTION, SubCommand.FUNCTION_LIST, (cmd)->cmd.functionList(),
				new ListConverter<>(new LibraryInfoConverter()));
	}

	@Override
	public List<LibraryInfo> functionList(final String pattern) {
		final CommandArguments args = CommandArguments.create(pattern);
		return functionList(args, (cmd)->cmd.functionList(pattern));
	}

	@Override
	public List<LibraryInfo> functionList(final byte[] pattern) {
		return functionList(SafeEncoder.encode(pattern));
	}

	@Override
	public List<LibraryInfo> functionList(final boolean withCode) {
		final CommandArguments args = CommandArguments.create(withCode ? "WITHCODE" : null);
		return functionList(args, (cmd)->withCode ? cmd.functionListWithCode() : cmd.functionList());
	}

	@Override
	public List<LibraryInfo> functionList(final String pattern, final boolean withCode) {
		final CommandArguments args = CommandArguments.create(pattern).add(withCode ? "WITHCODE" : null);
		return functionList(args, (cmd)->withCode ? cmd.functionListWithCode(pattern) : cmd.functionList(pattern));
	}

	@Override
	public List<LibraryInfo> functionList(final byte[] pattern, final boolean withCode) {
		return functionList(SafeEncoder.encode(pattern), withCode);
	}

	@Override
	public String functionLoad(final String functionCode) {
		final CommandArguments args = CommandArguments.create(functionCode);
		return executeCommand(Command.FUNCTION, SubCommand.FUNCTION_LOAD, args, (cmd)->cmd.functionLoad(functionCode),
				(v)->v);
	}

	@Override
	public String functionLoad(final String functionCode, final boolean replace) {
		final CommandArguments args = CommandArguments.create(functionCode).add(replace ? "REPLACE" : null);
		return executeCommand(Command.FUNCTION, SubCommand.FUNCTION_LOAD, args,
				(cmd)->cmd.functionLoadReplace(functionCode), (v)->v);
	}

	@Override
	public Status functionRestore(final byte[] value) {
		final CommandArguments args = CommandArguments.create(value);
		return executeCommand(Command.FUNCTION, SubCommand.FUNCTION_RESTORE, args, (cmd)->cmd.functionRestore(value),
				new OkStatusConverter());
	}

	@Override
	public Status functionRestore(final byte[] value, final FunctionRestoreMode mode) {
		final CommandArguments args = CommandArguments.create(value).add(mode);
		final FunctionRestoreModeFunctionRestorePolicyConverter functionRestoreModeFunctionRestorePolicyConverter = new FunctionRestoreModeFunctionRestorePolicyConverter();
		return executeCommand(Command.FUNCTION, SubCommand.FUNCTION_RESTORE, args,
				(cmd)->cmd.functionRestore(value, functionRestoreModeFunctionRestorePolicyConverter.convert(mode)),
				new OkStatusConverter());
	}

	@Override
	public FunctionStats functionStats() {
		return executeCommand(Command.FUNCTION, SubCommand.FUNCTION_STATS, (cmd)->cmd.functionStats(),
				new FunctionStatsConverter());
	}

	@Override
	public Object scriptDebug() {
		return executeCommand(Command.SCRIPT_DEBUG);
	}

	@Override
	public Object scriptDebug(final ScriptDebugMode mode) {
		final CommandArguments args = CommandArguments.create(mode);
		return executeCommand(Command.SCRIPT_DEBUG, args);
	}

	@Override
	public List<Boolean> scriptExists(final String... sha1) {
		final CommandArguments args = CommandArguments.create(sha1);
		return executeCommand(Command.SCRIPT_EXISTS, args, (cmd)->cmd.scriptExists(null, sha1), (v)->v);
	}

	@Override
	public List<Boolean> scriptExists(final byte[]... sha1) {
		final CommandArguments args = CommandArguments.create(sha1);
		return executeCommand(Command.SCRIPT_EXISTS, args, (cmd)->cmd.scriptExists(null, sha1), (v)->v);
	}

	@Override
	public Status scriptFlush() {
		return executeCommand(Command.SCRIPT_FLUSH, (cmd)->cmd.scriptFlush(), new OkStatusConverter());
	}

	@Override
	public Status scriptFlush(final FlushMode mode) {
		final CommandArguments args = CommandArguments.create(mode);
		final FlushModeConverter flushModeConverter = new FlushModeConverter();
		return executeCommand(Command.SCRIPT_FLUSH, args, (cmd)->cmd.scriptFlush((String) null,
				flushModeConverter.convert(mode)), new OkStatusConverter());
	}

	@Override
	public Status scriptKill() {
		return executeCommand(Command.SCRIPT_KILL, (cmd)->cmd.scriptKill(), new OkStatusConverter());
	}

	@Override
	public String scriptLoad(final String script) {
		final CommandArguments args = CommandArguments.create(script);
		return executeCommand(Command.SCRIPT_LOAD, args, (cmd)->cmd.scriptLoad(script), (v)->v);
	}

	@Override
	public byte[] scriptLoad(final byte[] script) {
		final CommandArguments args = CommandArguments.create(script);
		return executeCommand(Command.SCRIPT_LOAD, args, (cmd)->cmd.scriptLoad(script, null), (v)->v);
	}

	private Object eval(final CommandArguments args,
						final com.buession.redis.core.Command.Executor<UnifiedJedis, Object> executor) {
		return executeCommand(Command.EVAL, args, executor, (v)->v);
	}

	private Object evalRo(final CommandArguments args,
						  final com.buession.redis.core.Command.Executor<UnifiedJedis, Object> executor) {
		return executeCommand(Command.EVAL_RO, args, executor, (v)->v);
	}

	private Object evalSha(final CommandArguments args,
						   final com.buession.redis.core.Command.Executor<UnifiedJedis, Object> executor) {
		return executeCommand(Command.EVALSHA, args, executor, (v)->v);
	}

	private Object evalShaRo(final String digest, final List<String> keys, final List<String> arguments,
							 final CommandArguments args) {
		return executeCommand(Command.EVALSHA_RO, args, (cmd)->cmd.evalshaReadonly(digest, keys, arguments), (v)->v);
	}

	private Object evalShaRo(final byte[] digest, final List<byte[]> keys, final List<byte[]> arguments,
							 final CommandArguments args) {
		return executeCommand(Command.EVALSHA_RO, args, (cmd)->cmd.evalshaReadonly(digest, keys, arguments), (v)->v);
	}

	private Object fCall(final String function, final List<String> keys, final List<String> arguments,
						 final CommandArguments args) {
		return executeCommand(Command.FCALL, args, (cmd)->cmd.fcall(function, keys, arguments), (v)->v);
	}

	private Object fCall(final byte[] function, final List<byte[]> keys, final List<byte[]> arguments,
						 final CommandArguments args) {
		return executeCommand(Command.FCALL, args, (cmd)->cmd.fcall(function, keys, arguments), (v)->v);
	}

	private Object fCallRo(final String function, final List<String> keys, final List<String> arguments,
						   final CommandArguments args) {
		return executeCommand(Command.FCALL_RO, args, (cmd)->cmd.fcallReadonly(function, keys, arguments), (v)->v);
	}

	private Object fCallRo(final byte[] function, final List<byte[]> keys, final List<byte[]> arguments,
						   final CommandArguments args) {
		return executeCommand(Command.FCALL_RO, args, (cmd)->cmd.fcallReadonly(function, keys, arguments), (v)->v);
	}

	private List<LibraryInfo> functionList(final CommandArguments args,
										   final com.buession.redis.core.Command.Executor<UnifiedJedis, List<redis.clients.jedis.resps.LibraryInfo>> executor) {
		return executeCommand(Command.FUNCTION, SubCommand.FUNCTION_LIST, executor,
				new ListConverter<>(new LibraryInfoConverter()));
	}

}
