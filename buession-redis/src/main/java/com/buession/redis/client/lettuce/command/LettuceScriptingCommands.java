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
package com.buession.redis.client.lettuce.command;

import com.buession.core.converter.ListConverter;
import com.buession.lang.Status;
import com.buession.redis.client.lettuce.LettuceRedisClient;
import com.buession.redis.core.command.args.FlushMode;
import com.buession.redis.core.command.args.scripting.FunctionRestoreMode;
import com.buession.redis.core.FunctionStats;
import com.buession.redis.core.Keyword;
import com.buession.redis.core.LibraryInfo;
import com.buession.redis.core.command.args.scripting.DebugMode;
import com.buession.redis.core.command.RedisCommand;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.ScriptingCommands;
import com.buession.redis.core.command.RedisSubCommand;
import com.buession.redis.core.internal.convert.lettuce.params.FlushModeConverter;
import com.buession.redis.core.internal.convert.lettuce.params.FunctionRestoreModeConverter;
import com.buession.redis.core.internal.convert.lettuce.response.LibraryInfoConverter;
import com.buession.redis.core.internal.convert.response.OkStatusConverter;
import com.buession.redis.utils.SafeEncoder;
import io.lettuce.core.ScriptOutputType;

import java.util.List;

/**
 * Lettuce Script 命令
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public final class LettuceScriptingCommands extends AbstractLettuceRedisCommands implements
		ScriptingCommands {

	public LettuceScriptingCommands(final LettuceRedisClient client) {
		super(client);
	}

	@Override
	public Object eval(final String script) {
		final CommandArguments args = CommandArguments.create(script, 0);
		return executeCommand(RedisCommand.EVAL, args, (cmd)->cmd.eval(script, ScriptOutputType.OBJECT),
				(cmd)->cmd.eval(script, ScriptOutputType.OBJECT));
	}

	@Override
	public Object eval(final byte[] script) {
		final CommandArguments args = CommandArguments.create(script, 0);
		return executeCommand(RedisCommand.EVAL, args, (cmd)->cmd.eval(script, ScriptOutputType.OBJECT),
				(cmd)->cmd.eval(script, ScriptOutputType.OBJECT));
	}

	@Override
	public Object eval(final String script, final String... keys) {
		final CommandArguments args = CommandArguments.create(script).add(keys.length, keys);
		return executeCommand(RedisCommand.EVAL, args,
				(cmd)->cmd.eval(script, ScriptOutputType.OBJECT, SafeEncoder.encode(keys)),
				(cmd)->cmd.eval(script, ScriptOutputType.OBJECT, SafeEncoder.encode(keys)));
	}

	@Override
	public Object eval(final byte[] script, final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(script).add(keys.length, keys);
		return executeCommand(RedisCommand.EVAL, args, (cmd)->cmd.eval(script, ScriptOutputType.OBJECT, keys),
				(cmd)->cmd.eval(script, ScriptOutputType.OBJECT, keys));
	}

	@Override
	public Object eval(final String script, final String[] keys, final String[] arguments) {
		final CommandArguments args = CommandArguments.create(script).add(keys.length, keys).add(arguments);
		return executeCommand(RedisCommand.EVAL, args,
				(cmd)->cmd.eval(script, ScriptOutputType.OBJECT, SafeEncoder.encode(keys),
						SafeEncoder.encode(arguments)),
				(cmd)->cmd.eval(script, ScriptOutputType.OBJECT, SafeEncoder.encode(keys),
						SafeEncoder.encode(arguments)));
	}

	@Override
	public Object eval(final byte[] script, final byte[][] keys, final byte[][] arguments) {
		final CommandArguments args = CommandArguments.create(script).add(keys.length, keys).add(arguments);
		return executeCommand(RedisCommand.EVAL, args,
				(cmd)->cmd.eval(script, ScriptOutputType.OBJECT, keys, arguments),
				(cmd)->cmd.eval(script, ScriptOutputType.OBJECT, keys, arguments));
	}

	@Override
	public Object evalRo(final String script) {
		final CommandArguments args = CommandArguments.create(script, 0);
		return evalRo(script, new byte[][]{}, new String[]{}, args);
	}

	@Override
	public Object evalRo(final byte[] script) {
		final CommandArguments args = CommandArguments.create(script, 0);
		return evalRo(script, new byte[][]{}, new byte[][]{}, args);
	}

	@Override
	public Object evalRo(final String script, final String... keys) {
		final CommandArguments args = CommandArguments.create(script).add(keys.length, keys);
		return evalRo(script, SafeEncoder.encode(keys), new String[]{}, args);
	}

	@Override
	public Object evalRo(final byte[] script, final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(script).add(keys.length, keys);
		return evalRo(script, keys, new byte[][]{}, args);
	}

	@Override
	public Object evalRo(final String script, final String[] keys, final String[] arguments) {
		final CommandArguments args = CommandArguments.create(script).add(keys.length, keys).add(arguments);
		return evalRo(script, SafeEncoder.encode(keys), arguments, args);
	}

	@Override
	public Object evalRo(final byte[] script, final byte[][] keys, final byte[][] arguments) {
		final CommandArguments args = CommandArguments.create(script).add(keys.length, keys).add(arguments);
		return evalRo(script, keys, arguments, args);
	}

	@Override
	public Object evalSha(final String digest) {
		final CommandArguments args = CommandArguments.create(digest, 0);
		return executeCommand(RedisCommand.EVALSHA, args, (cmd)->cmd.evalsha(digest, ScriptOutputType.OBJECT),
				(cmd)->cmd.evalsha(digest, ScriptOutputType.OBJECT));
	}

	@Override
	public Object evalSha(final byte[] digest) {
		final CommandArguments args = CommandArguments.create(digest, 0);
		return executeCommand(RedisCommand.EVALSHA, args,
				(cmd)->cmd.evalsha(SafeEncoder.encode(digest), ScriptOutputType.OBJECT),
				(cmd)->cmd.evalsha(SafeEncoder.encode(digest), ScriptOutputType.OBJECT));
	}

	@Override
	public Object evalSha(final String digest, final String... keys) {
		final CommandArguments args = CommandArguments.create(digest).add(keys.length, keys);
		return executeCommand(RedisCommand.EVALSHA, args,
				(cmd)->cmd.evalsha(digest, ScriptOutputType.OBJECT, SafeEncoder.encode(keys)),
				(cmd)->cmd.evalsha(digest, ScriptOutputType.OBJECT, SafeEncoder.encode(keys)));
	}

	@Override
	public Object evalSha(final byte[] digest, final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(digest).add(keys.length, keys);
		return executeCommand(RedisCommand.EVALSHA, args,
				(cmd)->cmd.evalsha(SafeEncoder.encode(digest), ScriptOutputType.OBJECT, keys),
				(cmd)->cmd.evalsha(SafeEncoder.encode(digest), ScriptOutputType.OBJECT, keys));
	}

	@Override
	public Object evalSha(final String digest, final String[] keys, final String[] arguments) {
		final CommandArguments args = CommandArguments.create(digest).add(keys.length, keys);
		return executeCommand(RedisCommand.EVALSHA, args,
				(cmd)->cmd.evalsha(digest, ScriptOutputType.OBJECT, SafeEncoder.encode(keys),
						SafeEncoder.encode(arguments)),
				(cmd)->cmd.evalsha(digest, ScriptOutputType.OBJECT, SafeEncoder.encode(keys),
						SafeEncoder.encode(arguments)));
	}

	@Override
	public Object evalSha(final byte[] digest, final byte[][] keys, final byte[][] arguments) {
		final CommandArguments args = CommandArguments.create(digest).add(keys.length, keys);
		return executeCommand(RedisCommand.EVALSHA, args,
				(cmd)->cmd.evalsha(SafeEncoder.encode(digest), ScriptOutputType.OBJECT, keys,
						arguments),
				(cmd)->cmd.evalsha(SafeEncoder.encode(digest), ScriptOutputType.OBJECT, keys,
						arguments));
	}

	@Override
	public Object evalShaRo(final String digest) {
		final CommandArguments args = CommandArguments.create(digest, 0);
		return executeCommand(RedisCommand.EVALSHA_RO, args,
				(cmd)->cmd.evalshaReadOnly(digest, ScriptOutputType.OBJECT, new byte[][]{}),
				(cmd)->cmd.evalshaReadOnly(digest, ScriptOutputType.OBJECT, new byte[][]{}));
	}

	@Override
	public Object evalShaRo(final byte[] digest) {
		return evalShaRo(SafeEncoder.encode(digest));
	}

	@Override
	public Object evalShaRo(final String digest, final String... keys) {
		final CommandArguments args = CommandArguments.create(digest).add(keys.length, keys);
		return executeCommand(RedisCommand.EVALSHA_RO, args,
				(cmd)->cmd.evalshaReadOnly(digest, ScriptOutputType.OBJECT, SafeEncoder.encode(keys)),
				(cmd)->cmd.evalshaReadOnly(digest, ScriptOutputType.OBJECT, SafeEncoder.encode(keys)));
	}

	@Override
	public Object evalShaRo(final byte[] digest, final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(digest).add(keys.length, keys);
		return executeCommand(RedisCommand.EVALSHA_RO, args,
				(cmd)->cmd.evalshaReadOnly(SafeEncoder.encode(digest), ScriptOutputType.OBJECT, keys),
				(cmd)->cmd.evalshaReadOnly(SafeEncoder.encode(digest), ScriptOutputType.OBJECT, keys));
	}

	@Override
	public Object evalShaRo(final String digest, final String[] keys, final String[] arguments) {
		final CommandArguments args = CommandArguments.create(digest).add(keys.length, keys).add(arguments);
		return executeCommand(RedisCommand.EVALSHA_RO, args,
				(cmd)->cmd.evalshaReadOnly(digest, ScriptOutputType.OBJECT, SafeEncoder.encode(keys),
						SafeEncoder.encode(arguments)),
				(cmd)->cmd.evalshaReadOnly(digest, ScriptOutputType.OBJECT, SafeEncoder.encode(keys),
						SafeEncoder.encode(arguments)));
	}

	@Override
	public Object evalShaRo(final byte[] digest, final byte[][] keys, final byte[][] arguments) {
		final CommandArguments args = CommandArguments.create(digest).add(keys.length, keys).add(arguments);
		return executeCommand(RedisCommand.EVALSHA_RO, args,
				(cmd)->cmd.evalshaReadOnly(SafeEncoder.encode(digest), ScriptOutputType.OBJECT, keys,
						arguments),
				(cmd)->cmd.evalshaReadOnly(SafeEncoder.encode(digest), ScriptOutputType.OBJECT, keys,
						arguments));
	}

	@Override
	public Object fCall(final String function) {
		final CommandArguments args = CommandArguments.create(function, 0);
		return executeCommand(RedisCommand.FCALL, args, (cmd)->cmd.fcall(function, ScriptOutputType.OBJECT),
				(cmd)->cmd.fcall(function, ScriptOutputType.OBJECT));
	}

	@Override
	public Object fCall(final byte[] function) {
		return fCall(SafeEncoder.encode(function));
	}

	@Override
	public Object fCall(final String function, final String... keys) {
		final CommandArguments args = CommandArguments.create(function).add(keys.length, keys);
		return executeCommand(RedisCommand.FCALL, args,
				(cmd)->cmd.fcall(function, ScriptOutputType.OBJECT, SafeEncoder.encode(keys)),
				(cmd)->cmd.fcall(function, ScriptOutputType.OBJECT, SafeEncoder.encode(keys)));
	}

	@Override
	public Object fCall(final byte[] function, final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(function).add(keys.length, keys);
		return executeCommand(RedisCommand.FCALL, args,
				(cmd)->cmd.fcall(SafeEncoder.encode(function), ScriptOutputType.OBJECT, keys),
				(cmd)->cmd.fcall(SafeEncoder.encode(function), ScriptOutputType.OBJECT, keys));
	}

	@Override
	public Object fCall(final String function, final String[] keys, final String[] arguments) {
		final CommandArguments args = CommandArguments.create(function).add(keys.length, keys).add(arguments);
		return executeCommand(RedisCommand.FCALL, args,
				(cmd)->cmd.fcall(function, ScriptOutputType.OBJECT, SafeEncoder.encode(keys),
						SafeEncoder.encode(arguments)),
				(cmd)->cmd.fcall(function, ScriptOutputType.OBJECT, SafeEncoder.encode(keys),
						SafeEncoder.encode(arguments)));
	}

	@Override
	public Object fCall(final byte[] function, final byte[][] keys, final byte[][] arguments) {
		final CommandArguments args = CommandArguments.create(function).add(keys.length, keys).add(arguments);
		return executeCommand(RedisCommand.FCALL, args,
				(cmd)->cmd.fcall(SafeEncoder.encode(function), ScriptOutputType.OBJECT,
						keys, arguments),
				(cmd)->cmd.fcall(SafeEncoder.encode(function), ScriptOutputType.OBJECT,
						keys, arguments));
	}

	@Override
	public Object fCallRo(final String function) {
		final CommandArguments args = CommandArguments.create(function, 0);
		return executeCommand(RedisCommand.FCALL_RO, args, (cmd)->cmd.fcall(function, ScriptOutputType.OBJECT),
				(cmd)->cmd.fcall(function, ScriptOutputType.OBJECT));
	}

	@Override
	public Object fCallRo(final byte[] function) {
		return fCallRo(SafeEncoder.encode(function));
	}

	@Override
	public Object fCallRo(final String function, final String... keys) {
		final CommandArguments args = CommandArguments.create(function).add(keys.length, keys);
		return executeCommand(RedisCommand.FCALL_RO, args,
				(cmd)->cmd.fcall(function, ScriptOutputType.OBJECT, SafeEncoder.encode(keys)),
				(cmd)->cmd.fcall(function, ScriptOutputType.OBJECT, SafeEncoder.encode(keys)));
	}

	@Override
	public Object fCallRo(final byte[] function, final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(function).add(keys.length, keys);
		return executeCommand(RedisCommand.FCALL_RO, args,
				(cmd)->cmd.fcall(SafeEncoder.encode(function), ScriptOutputType.OBJECT, keys),
				(cmd)->cmd.fcall(SafeEncoder.encode(function), ScriptOutputType.OBJECT, keys));
	}

	@Override
	public Object fCallRo(final String function, final String[] keys, final String[] arguments) {
		final CommandArguments args = CommandArguments.create(function).add(keys.length, keys).add(arguments);
		return executeCommand(RedisCommand.FCALL_RO, args,
				(cmd)->cmd.fcall(function, ScriptOutputType.OBJECT, SafeEncoder.encode(keys),
						SafeEncoder.encode(arguments)),
				(cmd)->cmd.fcall(function, ScriptOutputType.OBJECT, SafeEncoder.encode(keys),
						SafeEncoder.encode(arguments)));
	}

	@Override
	public Object fCallRo(final byte[] function, final byte[][] keys, final byte[][] arguments) {
		final CommandArguments args = CommandArguments.create(function).add(keys.length, keys).add(arguments);
		return executeCommand(RedisCommand.FCALL_RO, args,
				(cmd)->cmd.fcallReadOnly(SafeEncoder.encode(function), ScriptOutputType.OBJECT, keys,
						arguments),
				(cmd)->cmd.fcallReadOnly(SafeEncoder.encode(function), ScriptOutputType.OBJECT, keys,
						arguments));
	}

	@Override
	public Status functionDelete(final String libraryName) {
		final CommandArguments args = CommandArguments.create(libraryName);
		return executeCommand(RedisCommand.FUNCTION, RedisSubCommand.FUNCTION_DELETE, args);
	}

	@Override
	public byte[] functionDump() {
		return executeCommand(RedisCommand.FUNCTION, RedisSubCommand.FUNCTION_DUMP, (cmd)->cmd.functionDump(),
				(cmd)->cmd.functionDump());
	}

	@Override
	public Status functionFlush() {
		return executeCommand(RedisCommand.FUNCTION, RedisSubCommand.FUNCTION_FLUSH,
				(cmd)->cmd.functionFlush(io.lettuce.core.FlushMode.ASYNC),
				(cmd)->cmd.functionFlush(io.lettuce.core.FlushMode.ASYNC), new OkStatusConverter());
	}

	@Override
	public Status functionFlush(final FlushMode flushMode) {
		final CommandArguments args = CommandArguments.create(flushMode);
		final FlushModeConverter flushModeConverter = new FlushModeConverter();
		return executeCommand(RedisCommand.FUNCTION, RedisSubCommand.FUNCTION_FLUSH, args,
				(cmd)->cmd.functionFlush(flushModeConverter.convert(flushMode)),
				(cmd)->cmd.functionFlush(flushModeConverter.convert(flushMode)),
				new OkStatusConverter());
	}

	@Override
	public Status functionKill() {
		return executeCommand(RedisCommand.FUNCTION, RedisSubCommand.FUNCTION_KILL, (cmd)->cmd.functionKill(),
				(cmd)->cmd.functionKill(), new OkStatusConverter());
	}

	@Override
	public List<LibraryInfo> functionList() {
		return executeCommand(RedisCommand.FUNCTION, RedisSubCommand.FUNCTION_LIST, (cmd)->cmd.functionList(),
				(cmd)->cmd.functionList(), new ListConverter<>(new LibraryInfoConverter()));
	}

	@Override
	public List<LibraryInfo> functionList(final String pattern) {
		final CommandArguments args = CommandArguments.create("LIBRARYNAME", pattern);
		return executeCommand(RedisCommand.FUNCTION, RedisSubCommand.FUNCTION_LIST, args,
				(cmd)->cmd.functionList(pattern), (cmd)->cmd.functionList(pattern),
				new ListConverter<>(new LibraryInfoConverter()));
	}

	@Override
	public List<LibraryInfo> functionList(final byte[] pattern) {
		return functionList(SafeEncoder.encode(pattern));
	}

	@Override
	public List<LibraryInfo> functionList(final String pattern, final boolean withCode) {
		final CommandArguments args = CommandArguments.create(pattern).add(withCode ? "WITHCODE" : null);
		return executeCommand(RedisCommand.FUNCTION, RedisSubCommand.FUNCTION_LIST, args,
				(cmd)->cmd.functionList(pattern), (cmd)->cmd.functionList(pattern),
				new ListConverter<>(new LibraryInfoConverter()));
	}

	@Override
	public List<LibraryInfo> functionList(final byte[] pattern, final boolean withCode) {
		return functionList(SafeEncoder.encode(pattern), withCode);
	}

	@Override
	public List<LibraryInfo> functionList(final boolean withCode) {
		final CommandArguments args = CommandArguments.create(withCode ? "WITHCODE" : null);
		return executeCommand(RedisCommand.FUNCTION, RedisSubCommand.FUNCTION_LIST, args, (cmd)->cmd.functionList(),
				(cmd)->cmd.functionList(), new ListConverter<>(new LibraryInfoConverter()));
	}

	@Override
	public String functionLoad(final String functionCode) {
		final CommandArguments args = CommandArguments.create(functionCode);
		return executeCommand(RedisCommand.FUNCTION, RedisSubCommand.FUNCTION_LOAD, args,
				(cmd)->cmd.functionLoad(functionCode), (cmd)->cmd.functionLoad(functionCode));
	}

	@Override
	public byte[] functionLoad(final byte[] functionCode) {
		final CommandArguments args = CommandArguments.create(functionCode);
		return executeCommand(RedisCommand.FUNCTION, RedisSubCommand.FUNCTION_LOAD, args,
				(cmd)->cmd.functionLoad(SafeEncoder.encode(functionCode)),
				(cmd)->cmd.functionLoad(SafeEncoder.encode(functionCode)), SafeEncoder::encode);
	}

	@Override
	public String functionLoad(final String functionCode, final boolean replace) {
		final CommandArguments args = CommandArguments.create(replace ? Keyword.Common.REPLACE : null)
				.add(functionCode);
		return executeCommand(RedisCommand.FUNCTION, RedisSubCommand.FUNCTION_LOAD, args,
				(cmd)->cmd.functionLoad(functionCode, replace), (cmd)->cmd.functionLoad(functionCode, replace));
	}

	@Override
	public byte[] functionLoad(final byte[] functionCode, final boolean replace) {
		final CommandArguments args = CommandArguments.create(replace ? Keyword.Common.REPLACE : null)
				.add(functionCode);
		return executeCommand(RedisCommand.FUNCTION, RedisSubCommand.FUNCTION_LOAD, args,
				(cmd)->cmd.functionLoad(SafeEncoder.encode(functionCode), replace),
				(cmd)->cmd.functionLoad(SafeEncoder.encode(functionCode), replace), SafeEncoder::encode);
	}

	@Override
	public Status functionRestore(final byte[] value) {
		final CommandArguments args = CommandArguments.create(value);
		return executeCommand(RedisCommand.FUNCTION, RedisSubCommand.FUNCTION_RESTORE, args,
				(cmd)->cmd.functionRestore(value), (cmd)->cmd.functionRestore(value),
				new OkStatusConverter());
	}

	@Override
	public Status functionRestore(final byte[] value, final FunctionRestoreMode mode) {
		final CommandArguments args = CommandArguments.create(value).add(mode);
		final FunctionRestoreModeConverter functionRestoreModeConverter = new FunctionRestoreModeConverter();
		return executeCommand(RedisCommand.FUNCTION, RedisSubCommand.FUNCTION_RESTORE, args,
				(cmd)->cmd.functionRestore(value, functionRestoreModeConverter.convert(mode)),
				(cmd)->cmd.functionRestore(value, functionRestoreModeConverter.convert(mode)), new OkStatusConverter());
	}

	@Override
	public FunctionStats functionStats() {
		return executeCommand(RedisCommand.FUNCTION, RedisSubCommand.FUNCTION_STATS);
	}

	@Override
	public Object scriptDebug() {
		return executeCommand(RedisCommand.SCRIPT, RedisSubCommand.SCRIPT_DEBUG);
	}

	@Override
	public Object scriptDebug(final DebugMode mode) {
		final CommandArguments args = CommandArguments.create(mode);
		return executeCommand(RedisCommand.SCRIPT, RedisSubCommand.SCRIPT_DEBUG, args);
	}

	@Override
	public List<Boolean> scriptExists(final String... sha1) {
		final CommandArguments args = CommandArguments.create(sha1);
		return executeCommand(RedisCommand.SCRIPT, RedisSubCommand.SCRIPT_EXISTS, args, (cmd)->cmd.scriptExists(sha1),
				(cmd)->cmd.scriptExists(sha1));
	}

	@Override
	public List<Boolean> scriptExists(final byte[]... sha1) {
		return scriptExists(SafeEncoder.encode(sha1));
	}

	@Override
	public Status scriptFlush() {
		return executeCommand(RedisCommand.SCRIPT, RedisSubCommand.SCRIPT_FLUSH, (cmd)->cmd.scriptFlush(),
				(cmd)->cmd.scriptFlush(), new OkStatusConverter());
	}

	@Override
	public Status scriptFlush(final FlushMode mode) {
		final CommandArguments args = CommandArguments.create(mode);
		final FlushModeConverter flushModeConverter = new FlushModeConverter();
		return executeCommand(RedisCommand.SCRIPT, RedisSubCommand.SCRIPT_FLUSH, args,
				(cmd)->cmd.scriptFlush(flushModeConverter.convert(mode)),
				(cmd)->cmd.scriptFlush(flushModeConverter.convert(mode)),
				new OkStatusConverter());
	}

	@Override
	public Status scriptKill() {
		return executeCommand(RedisCommand.SCRIPT, RedisSubCommand.SCRIPT_KILL, (cmd)->cmd.scriptKill(),
				(cmd)->cmd.scriptKill(), new OkStatusConverter());
	}

	@Override
	public String scriptLoad(final String script) {
		final CommandArguments args = CommandArguments.create(script);
		return executeCommand(RedisCommand.SCRIPT, RedisSubCommand.SCRIPT_LOAD, args, (cmd)->cmd.scriptLoad(script),
				(cmd)->cmd.scriptLoad(script));
	}

	@Override
	public byte[] scriptLoad(final byte[] script) {
		final CommandArguments args = CommandArguments.create(script);
		return executeCommand(RedisCommand.SCRIPT, RedisSubCommand.SCRIPT_LOAD, args, (cmd)->cmd.scriptLoad(script),
				(cmd)->cmd.scriptLoad(script), SafeEncoder::encode);
	}

	private Object evalRo(final String script, final byte[][] keys, final String[] arguments,
	                      final CommandArguments args) {
		return executeCommand(RedisCommand.EVAL_RO, args,
				(cmd)->cmd.evalReadOnly(script, ScriptOutputType.OBJECT, keys,
						SafeEncoder.encode(arguments)),
				(cmd)->cmd.evalReadOnly(script, ScriptOutputType.OBJECT, keys,
						SafeEncoder.encode(arguments)));
	}

	private Object evalRo(final byte[] script, final byte[][] keys, final byte[][] arguments,
	                      final CommandArguments args) {
		return executeCommand(RedisCommand.EVAL_RO, args,
				(cmd)->cmd.evalReadOnly(script, ScriptOutputType.OBJECT, keys, arguments),
				(cmd)->cmd.evalReadOnly(script, ScriptOutputType.OBJECT, keys, arguments));
	}

}
