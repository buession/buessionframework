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

import com.buession.core.converter.ListConverter;
import com.buession.lang.Status;
import com.buession.redis.client.lettuce.LettuceRedisClient;
import com.buession.redis.client.operations.ScriptingOperations;
import com.buession.redis.core.FlushMode;
import com.buession.redis.core.FunctionRestoreMode;
import com.buession.redis.core.FunctionStats;
import com.buession.redis.core.LibraryInfo;
import com.buession.redis.core.ScriptDebugMode;
import com.buession.redis.core.command.Command;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.SubCommand;
import com.buession.redis.core.internal.convert.Converters;
import com.buession.redis.core.internal.convert.lettuce.params.FlushModeConverter;
import com.buession.redis.core.internal.convert.lettuce.params.FunctionRestoreModeConverter;
import com.buession.redis.core.internal.convert.lettuce.response.LibraryInfoConverter;
import com.buession.redis.core.internal.convert.response.OkStatusConverter;
import com.buession.redis.utils.SafeEncoder;
import io.lettuce.core.RedisCommands;
import io.lettuce.core.ScriptOutputType;

import java.util.List;
import java.util.Map;

/**
 * Lettuce Script 命令操作
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public final class LettuceScriptingOperations extends AbstractLettuceRedisOperations implements
		ScriptingOperations {

	public LettuceScriptingOperations(final LettuceRedisClient client) {
		super(client);
	}

	@Override
	public Object eval(final String script) {
		final CommandArguments args = CommandArguments.create(script);
		return eval(args, (cmd)->cmd.eval(script, ScriptOutputType.OBJECT));
	}

	@Override
	public Object eval(final byte[] script) {
		final CommandArguments args = CommandArguments.create(script);
		return eval(args, (cmd)->cmd.eval(script, ScriptOutputType.OBJECT));
	}

	@Override
	public Object eval(final String script, final String... keys) {
		final CommandArguments args = CommandArguments.create(script).add(keys.length).add(keys);
		return eval(args, (cmd)->cmd.eval(script, ScriptOutputType.OBJECT, SafeEncoder.encode(keys)));
	}

	@Override
	public Object eval(final byte[] script, final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(script).add(keys.length).add(keys);
		return eval(args, (cmd)->cmd.eval(script, ScriptOutputType.OBJECT, keys));
	}

	@Override
	public Object eval(final String script, final String[] keys, final String[] arguments) {
		final CommandArguments args = CommandArguments.create(script).add(keys).add(keys.length).add(arguments);
		return eval(args, (cmd)->cmd.eval(script, ScriptOutputType.OBJECT, SafeEncoder.encode(keys),
				SafeEncoder.encode(arguments)));
	}

	@Override
	public Object eval(final byte[] script, final byte[][] keys, final byte[][] arguments) {
		final CommandArguments args = CommandArguments.create(script).add(keys).add(keys.length).add(arguments);
		return eval(args, (cmd)->cmd.eval(script, ScriptOutputType.OBJECT, keys, arguments));
	}

	@Override
	public Object evalRo(final String script) {
		final CommandArguments args = CommandArguments.create(script);
		return evalRo(script, new String[]{}, new String[]{}, args);
	}

	@Override
	public Object evalRo(final byte[] script) {
		final CommandArguments args = CommandArguments.create(script);
		return evalRo(script, new byte[][]{}, new byte[][]{}, args);
	}

	@Override
	public Object evalRo(final String script, final String... keys) {
		final CommandArguments args = CommandArguments.create(script).add(keys.length).add(keys);
		return evalRo(script, keys, new String[]{}, args);
	}

	@Override
	public Object evalRo(final byte[] script, final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(script).add(keys.length).add(keys);
		return evalRo(script, keys, new byte[][]{}, args);
	}

	@Override
	public Object evalRo(final String script, final String[] keys, final String[] arguments) {
		final CommandArguments args = CommandArguments.create(script).add(keys.length).add(keys).add(arguments);
		return evalRo(script, keys, arguments, args);
	}

	@Override
	public Object evalRo(final byte[] script, final byte[][] keys, final byte[][] arguments) {
		final CommandArguments args = CommandArguments.create(script).add(keys.length).add(keys).add(arguments);
		return evalRo(script, keys, arguments, args);
	}

	@Override
	public Object evalSha(final String digest) {
		final CommandArguments args = CommandArguments.create(digest);
		return evalSha(args, (cmd)->cmd.evalsha(digest, ScriptOutputType.OBJECT));
	}

	@Override
	public Object evalSha(final byte[] digest) {
		final CommandArguments args = CommandArguments.create(digest);
		return evalSha(args, (cmd)->cmd.evalsha(SafeEncoder.encode(digest), ScriptOutputType.OBJECT));
	}

	@Override
	public Object evalSha(final String digest, final String... keys) {
		final CommandArguments args = CommandArguments.create(digest).add(keys.length).add(keys);
		return evalSha(args, (cmd)->cmd.evalsha(digest, ScriptOutputType.OBJECT, SafeEncoder.encode(keys)));
	}

	@Override
	public Object evalSha(final byte[] digest, final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(digest).add(keys.length).add(keys);
		return evalSha(args, (cmd)->cmd.evalsha(SafeEncoder.encode(digest), ScriptOutputType.OBJECT, keys));
	}

	@Override
	public Object evalSha(final String digest, final String[] keys, final String[] arguments) {
		final CommandArguments args = CommandArguments.create(digest).add(keys.length).add(keys);
		return evalSha(args, (cmd)->cmd.evalsha(digest, ScriptOutputType.OBJECT, SafeEncoder.encode(keys),
				SafeEncoder.encode(arguments)));
	}

	@Override
	public Object evalSha(final byte[] digest, final byte[][] keys, final byte[][] arguments) {
		final CommandArguments args = CommandArguments.create(digest).add(keys.length).add(keys);
		return evalSha(args, (cmd)->cmd.evalsha(SafeEncoder.encode(digest), ScriptOutputType.OBJECT, keys,
				arguments));
	}

	@Override
	public Object evalShaRo(final String digest) {
		final CommandArguments args = CommandArguments.create(digest);
		return executeCommand(Command.EVALSHA_RO, args);
	}

	@Override
	public Object evalShaRo(final byte[] digest) {
		final CommandArguments args = CommandArguments.create(digest);
		return executeCommand(Command.EVALSHA_RO, args);
	}

	@Override
	public Object evalShaRo(final String digest, final String... keys) {
		final CommandArguments args = CommandArguments.create(digest).add(keys.length).add(keys);
		return executeCommand(Command.EVALSHA_RO, args);
	}

	@Override
	public Object evalShaRo(final byte[] digest, final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(digest).add(keys.length).add(keys);
		return executeCommand(Command.EVALSHA_RO, args);
	}

	@Override
	public Object evalShaRo(final String digest, final String[] keys, final String[] arguments) {
		final CommandArguments args = CommandArguments.create(digest).add(keys.length).add(keys).add(arguments);
		return executeCommand(Command.EVALSHA_RO, args);
	}

	@Override
	public Object evalShaRo(final byte[] digest, final byte[][] keys, final byte[][] arguments) {
		final CommandArguments args = CommandArguments.create(digest).add(keys.length).add(keys).add(arguments);
		return executeCommand(Command.EVALSHA_RO, args);
	}

	@Override
	public Object fCall(final String function) {
		final CommandArguments args = CommandArguments.create(function);
		return fCall(args, (cmd)->cmd.fcall(function, ScriptOutputType.OBJECT));
	}

	@Override
	public Object fCall(final byte[] function) {
		return fCall(SafeEncoder.encode(function));
	}

	@Override
	public Object fCall(final String function, final String... keys) {
		final CommandArguments args = CommandArguments.create(function).add(keys.length).add(keys);
		return fCall(args, (cmd)->cmd.fcall(function, ScriptOutputType.OBJECT, SafeEncoder.encode(keys)));
	}

	@Override
	public Object fCall(final byte[] function, final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(function).add(keys.length).add(keys);
		return fCall(args, (cmd)->cmd.fcall(SafeEncoder.encode(function), ScriptOutputType.OBJECT, keys));
	}

	@Override
	public Object fCall(final String function, final String[] keys, final String[] arguments) {
		final CommandArguments args = CommandArguments.create(function).add(keys.length).add(keys);
		return fCall(args, (cmd)->cmd.fcall(function, ScriptOutputType.OBJECT, SafeEncoder.encode(keys),
				SafeEncoder.encode(arguments)));
	}

	@Override
	public Object fCall(final byte[] function, final byte[][] keys, final byte[][] arguments) {
		final CommandArguments args = CommandArguments.create(function).add(keys.length).add(keys);
		return fCall(args, (cmd)->cmd.fcall(SafeEncoder.encode(function), ScriptOutputType.OBJECT, keys,
				arguments));
	}

	@Override
	public Object fCallRo(final String function) {
		final CommandArguments args = CommandArguments.create(function);
		return fCallRo(args, (cmd)->cmd.fcall(function, ScriptOutputType.OBJECT));
	}

	@Override
	public Object fCallRo(final byte[] function) {
		return fCallRo(SafeEncoder.encode(function));
	}

	@Override
	public Object fCallRo(final String function, final String... keys) {
		final CommandArguments args = CommandArguments.create(function).add(keys.length).add(keys);
		return fCallRo(args, (cmd)->cmd.fcall(function, ScriptOutputType.OBJECT, SafeEncoder.encode(keys)));
	}

	@Override
	public Object fCallRo(final byte[] function, final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(function).add(keys.length).add(keys);
		return fCallRo(args, (cmd)->cmd.fcall(SafeEncoder.encode(function), ScriptOutputType.OBJECT, keys));
	}

	@Override
	public Object fCallRo(final String function, final String[] keys, final String[] arguments) {
		final CommandArguments args = CommandArguments.create(function).add(keys.length).add(keys);
		return fCallRo(args, (cmd)->cmd.fcall(function, ScriptOutputType.OBJECT, SafeEncoder.encode(keys),
				SafeEncoder.encode(arguments)));
	}

	@Override
	public Object fCallRo(final byte[] function, final byte[][] keys, final byte[][] arguments) {
		final CommandArguments args = CommandArguments.create(function).add(keys.length).add(keys);
		return fCallRo(args, (cmd)->cmd.fcallReadOnly(SafeEncoder.encode(function), ScriptOutputType.OBJECT, keys,
				arguments));
	}

	@Override
	public Status functionDelete(final String libraryName) {
		final CommandArguments args = CommandArguments.create(libraryName);
		return executeCommand(Command.FUNCTION, SubCommand.FUNCTION_DELETE, args);
	}

	@Override
	public byte[] functionDump() {
		return executeCommand(Command.FUNCTION, SubCommand.FUNCTION_DUMP, (cmd)->cmd.functionDump(), (v)->v);
	}

	@Override
	public Status functionFlush() {
		return executeCommand(Command.FUNCTION, SubCommand.FUNCTION_FLUSH, (cmd)->cmd.functionFlush(
				io.lettuce.core.FlushMode.ASYNC), new OkStatusConverter());
	}

	@Override
	public Status functionFlush(final FlushMode flushMode) {
		final CommandArguments args = CommandArguments.create(flushMode);
		final FlushModeConverter flushModeConverter = new FlushModeConverter();
		return executeCommand(Command.FUNCTION, SubCommand.FUNCTION_FLUSH, args,
				(cmd)->cmd.functionFlush(flushModeConverter.convert(flushMode)),
				new OkStatusConverter());
	}

	@Override
	public Status functionKill() {
		return executeCommand(Command.FUNCTION, SubCommand.FUNCTION_KILL, (cmd)->cmd.functionKill(),
				new OkStatusConverter());
	}

	@Override
	public List<LibraryInfo> functionList() {
		return functionList(null, (cmd)->cmd.functionList());
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
		return functionList(args, (cmd)->cmd.functionList());
	}

	@Override
	public List<LibraryInfo> functionList(final String pattern, final boolean withCode) {
		final CommandArguments args = CommandArguments.create(pattern).add(withCode ? "WITHCODE" : null);
		return functionList(args, (cmd)->cmd.functionList(pattern));
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
				(cmd)->cmd.functionLoad(functionCode, replace), (v)->v);
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
		final FunctionRestoreModeConverter functionRestoreModeConverter = new FunctionRestoreModeConverter();
		return executeCommand(Command.FUNCTION, SubCommand.FUNCTION_RESTORE, args, (cmd)->cmd.functionRestore(value,
				functionRestoreModeConverter.convert(mode)), new OkStatusConverter());
	}

	@Override
	public FunctionStats functionStats() {
		return executeCommand(Command.FUNCTION, SubCommand.FUNCTION_STATS);
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
		return executeCommand(Command.SCRIPT_EXISTS, args, (cmd)->cmd.scriptExists(sha1), (v)->v);
	}

	@Override
	public List<Boolean> scriptExists(final byte[]... sha1) {
		return scriptExists(SafeEncoder.encode(sha1));
	}

	@Override
	public Status scriptFlush() {
		return executeCommand(Command.SCRIPT_FLUSH, (cmd)->cmd.scriptFlush(), new OkStatusConverter());
	}

	@Override
	public Status scriptFlush(final FlushMode mode) {
		final CommandArguments args = CommandArguments.create(mode);
		final FlushModeConverter flushModeConverter = new FlushModeConverter();
		return executeCommand(Command.SCRIPT_FLUSH, args, (cmd)->cmd.scriptFlush(flushModeConverter.convert(mode)),
				new OkStatusConverter());
	}

	@Override
	public Status scriptKill() {
		return executeCommand(Command.SCRIPT_KILL, (cmd)->cmd.scriptKill(), new OkStatusConverter());
	}

	@Override
	public String scriptLoad(final String script) {
		final CommandArguments args = CommandArguments.create(script);
		return executeCommand(Command.SCRIPT_LOAD, args, (cmd)->cmd.scriptLoad(script),
				(v)->v);
	}

	@Override
	public byte[] scriptLoad(final byte[] script) {
		final CommandArguments args = CommandArguments.create(script);
		return executeCommand(Command.SCRIPT_LOAD, args, (cmd)->cmd.scriptLoad(script),
				Converters.stringToBinaryConverter());
	}

	private Object eval(final CommandArguments args,
						final com.buession.redis.core.Command.Executor<RedisCommands<byte[], byte[]>, Object> executor) {
		return executeCommand(Command.EVAL, args, executor, (v)->v);
	}

	private Object evalRo(final String script, final String[] keys, final String[] arguments,
						  final CommandArguments args) {
		return executeCommand(Command.EVAL_RO, args,
				(cmd)->cmd.evalReadOnly(script, ScriptOutputType.OBJECT, SafeEncoder.encode(keys),
						SafeEncoder.encode(arguments)), (v)->v);
	}

	private Object evalRo(final byte[] script, final byte[][] keys, final byte[][] arguments,
						  final CommandArguments args) {
		return executeCommand(Command.EVAL_RO, args,
				(cmd)->cmd.evalReadOnly(script, ScriptOutputType.OBJECT, keys, arguments), (v)->v);
	}

	private Object evalSha(final CommandArguments args,
						   final com.buession.redis.core.Command.Executor<RedisCommands<byte[], byte[]>, Object> executor) {
		return executeCommand(Command.EVALSHA, args, executor, (v)->v);
	}

	private Object fCall(final CommandArguments args,
						 final com.buession.redis.core.Command.Executor<RedisCommands<byte[], byte[]>, Object> executor) {
		return executeCommand(Command.FCALL, args, executor, (v)->v);
	}

	private Object fCallRo(final CommandArguments args,
						   final com.buession.redis.core.Command.Executor<RedisCommands<byte[], byte[]>, Object> executor) {
		return executeCommand(Command.FCALL_RO, args, executor, (v)->v);
	}

	private List<LibraryInfo> functionList(final CommandArguments args,
										   final com.buession.redis.core.Command.Executor<RedisCommands<byte[], byte[]>, List<Map<String, Object>>> executor) {
		return executeCommand(Command.FUNCTION, SubCommand.FUNCTION_LIST, args, executor,
				new ListConverter<>(new LibraryInfoConverter()));
	}

}
