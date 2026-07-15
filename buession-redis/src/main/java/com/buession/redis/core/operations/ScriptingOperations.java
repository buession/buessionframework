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
package com.buession.redis.core.operations;

import com.buession.lang.Status;
import com.buession.redis.core.command.Command;
import com.buession.redis.core.command.args.FlushMode;
import com.buession.redis.core.command.args.scripting.FunctionRestoreMode;
import com.buession.redis.core.FunctionStats;
import com.buession.redis.core.LibraryInfo;
import com.buession.redis.core.command.args.scripting.DebugMode;
import com.buession.redis.core.command.ScriptingCommands;

import java.util.List;

/**
 * LUA 脚本运算
 *
 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/?group=scripting" target="_blank">https://redis.io/docs/latest/commands/?group=scripting</a></p>
 *
 * @author Yong.Teng
 */
public interface ScriptingOperations extends ScriptingCommands, RedisOperations {

	@Override
	default Object eval(final String script) {
		return doExecute((cmd)->cmd.eval(script));
	}

	@Override
	default Object eval(final byte[] script) {
		return doExecute((cmd)->cmd.eval(script));
	}

	@Override
	default Object eval(final String script, final String... keys) {
		return doExecute((cmd)->cmd.eval(script, keys));
	}

	@Override
	default Object eval(final byte[] script, final byte[]... keys) {
		return doExecute((cmd)->cmd.eval(script, keys));
	}

	@Override
	default Object eval(final String script, final String[] keys, final String[] arguments) {
		return doExecute((cmd)->cmd.eval(script, keys, arguments));
	}

	@Override
	default Object eval(final byte[] script, final byte[][] keys, final byte[][] arguments) {
		return doExecute((cmd)->cmd.eval(script, keys, arguments));
	}

	@Override
	default Object evalRo(final String script) {
		return doExecute((cmd)->cmd.evalRo(script));
	}

	@Override
	default Object evalRo(final byte[] script) {
		return doExecute((cmd)->cmd.evalRo(script));
	}

	@Override
	default Object evalRo(final String script, final String... keys) {
		return doExecute((cmd)->cmd.evalRo(script, keys));
	}

	@Override
	default Object evalRo(final byte[] script, final byte[]... keys) {
		return doExecute((cmd)->cmd.evalRo(script, keys));
	}

	@Override
	default Object evalRo(final String script, final String[] keys, final String[] arguments) {
		return doExecute((cmd)->cmd.evalRo(script, keys, arguments));
	}

	@Override
	default Object evalRo(final byte[] script, final byte[][] keys, final byte[][] arguments) {
		return doExecute((cmd)->cmd.evalRo(script, keys, arguments));
	}

	@Override
	default Object evalSha(final String digest) {
		return doExecute((cmd)->cmd.evalSha(digest));
	}

	@Override
	default Object evalSha(final byte[] digest) {
		return doExecute((cmd)->cmd.evalSha(digest));
	}

	@Override
	default Object evalSha(final String digest, final String... keys) {
		return doExecute((cmd)->cmd.evalSha(digest, keys));
	}

	@Override
	default Object evalSha(final byte[] digest, final byte[]... keys) {
		return doExecute((cmd)->cmd.evalSha(digest, keys));
	}

	@Override
	default Object evalSha(final String digest, final String[] keys, final String[] arguments) {
		return doExecute((cmd)->cmd.evalSha(digest, keys, arguments));
	}

	@Override
	default Object evalSha(final byte[] digest, final byte[][] keys, final byte[][] arguments) {
		return doExecute((cmd)->cmd.evalSha(digest, keys, arguments));
	}

	@Override
	default Object evalShaRo(final String digest) {
		return doExecute((cmd)->cmd.evalShaRo(digest));
	}

	@Override
	default Object evalShaRo(final byte[] digest) {
		return doExecute((cmd)->cmd.evalShaRo(digest));
	}

	@Override
	default Object evalShaRo(final String digest, final String... keys) {
		return doExecute((cmd)->cmd.evalShaRo(digest, keys));
	}

	@Override
	default Object evalShaRo(final byte[] digest, final byte[]... keys) {
		return doExecute((cmd)->cmd.evalShaRo(digest, keys));
	}

	@Override
	default Object evalShaRo(final String digest, final String[] keys, final String[] arguments) {
		return doExecute((cmd)->cmd.evalShaRo(digest, keys, arguments));
	}

	@Override
	default Object evalShaRo(final byte[] digest, final byte[][] keys, final byte[][] arguments) {
		return doExecute((cmd)->cmd.evalShaRo(digest, keys, arguments));
	}

	@Override
	default Object fCall(final String function) {
		return doExecute((cmd)->cmd.fCall(function));
	}

	@Override
	default Object fCall(final byte[] function) {
		return doExecute((cmd)->cmd.fCall(function));
	}

	@Override
	default Object fCall(final String function, final String... keys) {
		return doExecute((cmd)->cmd.fCall(function, keys));
	}

	@Override
	default Object fCall(final byte[] function, final byte[]... keys) {
		return doExecute((cmd)->cmd.fCall(function, keys));
	}

	@Override
	default Object fCall(final String function, final String[] keys, final String[] arguments) {
		return doExecute((cmd)->cmd.fCall(function, keys, arguments));
	}

	@Override
	default Object fCall(final byte[] function, final byte[][] keys, final byte[][] arguments) {
		return doExecute((cmd)->cmd.fCall(function, keys, arguments));
	}

	@Override
	default Object fCallRo(final String function) {
		return doExecute((cmd)->cmd.fCallRo(function));
	}

	@Override
	default Object fCallRo(final byte[] function) {
		return doExecute((cmd)->cmd.fCallRo(function));
	}

	@Override
	default Object fCallRo(final String function, final String... keys) {
		return doExecute((cmd)->cmd.fCallRo(function, keys));
	}

	@Override
	default Object fCallRo(final byte[] function, final byte[]... keys) {
		return doExecute((cmd)->cmd.fCallRo(function, keys));
	}

	@Override
	default Object fCallRo(final String function, final String[] keys, final String[] arguments) {
		return doExecute((cmd)->cmd.fCallRo(function, keys, arguments));
	}

	@Override
	default Object fCallRo(final byte[] function, final byte[][] keys, final byte[][] arguments) {
		return doExecute((cmd)->cmd.fCallRo(function, keys, arguments));
	}

	@Override
	default Status functionDelete(final String libraryName) {
		return doExecute((cmd)->cmd.functionDelete(libraryName));
	}

	@Override
	default byte[] functionDump() {
		return doExecute((cmd)->cmd.functionDump());
	}

	@Override
	default Status functionFlush() {
		return doExecute((cmd)->cmd.functionFlush());
	}

	@Override
	default Status functionFlush(final FlushMode flushMode) {
		return doExecute((cmd)->cmd.functionFlush(flushMode));
	}

	@Override
	default Status functionKill() {
		return doExecute((cmd)->cmd.functionKill());
	}

	@Override
	default List<LibraryInfo> functionList() {
		return doExecute((cmd)->cmd.functionList());
	}

	@Override
	default List<LibraryInfo> functionList(final String pattern) {
		return doExecute((cmd)->cmd.functionList(pattern));
	}

	@Override
	default List<LibraryInfo> functionList(final byte[] pattern) {
		return doExecute((cmd)->cmd.functionList(pattern));
	}

	@Override
	default List<LibraryInfo> functionList(final String pattern, final boolean withCode) {
		return doExecute((cmd)->cmd.functionList(pattern, withCode));
	}

	@Override
	default List<LibraryInfo> functionList(final byte[] pattern, final boolean withCode) {
		return doExecute((cmd)->cmd.functionList(pattern, withCode));
	}

	@Override
	default List<LibraryInfo> functionList(final boolean withCode) {
		return doExecute((cmd)->cmd.functionList(withCode));
	}

	@Override
	default String functionLoad(final String functionCode) {
		return doExecute((cmd)->cmd.functionLoad(functionCode));
	}

	@Override
	default byte[] functionLoad(final byte[] functionCode) {
		return doExecute((cmd)->cmd.functionLoad(functionCode));
	}

	@Override
	default String functionLoad(final String functionCode, final boolean replace) {
		return doExecute((cmd)->cmd.functionLoad(functionCode, replace));
	}

	@Override
	default byte[] functionLoad(final byte[] functionCode, final boolean replace) {
		return doExecute((cmd)->cmd.functionLoad(functionCode, replace));
	}

	@Override
	default Status functionRestore(final byte[] value) {
		return doExecute((cmd)->cmd.functionRestore(value));
	}

	@Override
	default Status functionRestore(final byte[] value, final FunctionRestoreMode mode) {
		return doExecute((cmd)->cmd.functionRestore(value, mode));
	}

	@Override
	default FunctionStats functionStats() {
		return doExecute((cmd)->cmd.functionStats());
	}

	@Override
	default Object scriptDebug() {
		return doExecute((cmd)->cmd.scriptDebug());
	}

	@Override
	default Object scriptDebug(final DebugMode mode) {
		return doExecute((cmd)->cmd.scriptDebug(mode));
	}

	@Override
	default List<Boolean> scriptExists(final String... sha1) {
		return doExecute((cmd)->cmd.scriptExists(sha1));
	}

	@Override
	default List<Boolean> scriptExists(final byte[]... sha1) {
		return doExecute((cmd)->cmd.scriptExists(sha1));
	}

	@Override
	default Status scriptFlush() {
		return doExecute((cmd)->cmd.scriptFlush());
	}

	@Override
	default Status scriptFlush(final FlushMode mode) {
		return doExecute((cmd)->cmd.scriptFlush(mode));
	}

	@Override
	default Status scriptKill() {
		return doExecute((cmd)->cmd.scriptKill());
	}

	@Override
	default String scriptLoad(final String script) {
		return doExecute((cmd)->cmd.scriptLoad(script));
	}

	@Override
	default byte[] scriptLoad(final byte[] script) {
		return doExecute((cmd)->cmd.scriptLoad(script));
	}

	private <R> R doExecute(final Command.Executor<ScriptingCommands, R> executor) {
		return execute((client)->executor.execute(client.scriptingCommands()));
	}

}
