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
import com.buession.redis.core.FlushMode;
import com.buession.redis.core.FunctionRestoreMode;
import com.buession.redis.core.FunctionStats;
import com.buession.redis.core.LibraryInfo;
import com.buession.redis.core.ScriptDebugMode;
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
		return execute((client)->client.scriptingOperations().eval(script));
	}

	@Override
	default Object eval(final byte[] script) {
		return execute((client)->client.scriptingOperations().eval(script));
	}

	@Override
	default Object eval(final String script, final String... keys) {
		return execute((client)->client.scriptingOperations().eval(script, keys));
	}

	@Override
	default Object eval(final byte[] script, final byte[]... keys) {
		return execute((client)->client.scriptingOperations().eval(script, keys));
	}

	@Override
	default Object eval(final String script, final String[] keys, final String[] arguments) {
		return execute((client)->client.scriptingOperations().eval(script, keys, arguments));
	}

	@Override
	default Object eval(final byte[] script, final byte[][] keys, final byte[][] arguments) {
		return execute((client)->client.scriptingOperations().eval(script, keys, arguments));
	}

	@Override
	default Object evalRo(final String script) {
		return execute((client)->client.scriptingOperations().evalRo(script));
	}

	@Override
	default Object evalRo(final byte[] script) {
		return execute((client)->client.scriptingOperations().evalRo(script));
	}

	@Override
	default Object evalRo(final String script, final String... keys) {
		return execute((client)->client.scriptingOperations().evalRo(script, keys));
	}

	@Override
	default Object evalRo(final byte[] script, final byte[]... keys) {
		return execute((client)->client.scriptingOperations().evalRo(script, keys));
	}

	@Override
	default Object evalRo(final String script, final String[] keys, final String[] arguments) {
		return execute((client)->client.scriptingOperations().evalRo(script, keys, arguments));
	}

	@Override
	default Object evalRo(final byte[] script, final byte[][] keys, final byte[][] arguments) {
		return execute((client)->client.scriptingOperations().evalRo(script, keys, arguments));
	}

	@Override
	default Object evalSha(final String digest) {
		return execute((client)->client.scriptingOperations().evalSha(digest));
	}

	@Override
	default Object evalSha(final byte[] digest) {
		return execute((client)->client.scriptingOperations().evalSha(digest));
	}

	@Override
	default Object evalSha(final String digest, final String... keys) {
		return execute((client)->client.scriptingOperations().evalSha(digest, keys));
	}

	@Override
	default Object evalSha(final byte[] digest, final byte[]... keys) {
		return execute((client)->client.scriptingOperations().evalSha(digest, keys));
	}

	@Override
	default Object evalSha(final String digest, final String[] keys, final String[] arguments) {
		return execute((client)->client.scriptingOperations().evalSha(digest, keys, arguments));
	}

	@Override
	default Object evalSha(final byte[] digest, final byte[][] keys, final byte[][] arguments) {
		return execute((client)->client.scriptingOperations().evalSha(digest, keys, arguments));
	}

	@Override
	default Object evalShaRo(final String digest) {
		return execute((client)->client.scriptingOperations().evalShaRo(digest));
	}

	@Override
	default Object evalShaRo(final byte[] digest) {
		return execute((client)->client.scriptingOperations().evalShaRo(digest));
	}

	@Override
	default Object evalShaRo(final String digest, final String... keys) {
		return execute((client)->client.scriptingOperations().evalShaRo(digest, keys));
	}

	@Override
	default Object evalShaRo(final byte[] digest, final byte[]... keys) {
		return execute((client)->client.scriptingOperations().evalShaRo(digest, keys));
	}

	@Override
	default Object evalShaRo(final String digest, final String[] keys, final String[] arguments) {
		return execute((client)->client.scriptingOperations().evalShaRo(digest, keys, arguments));
	}

	@Override
	default Object evalShaRo(final byte[] digest, final byte[][] keys, final byte[][] arguments) {
		return execute((client)->client.scriptingOperations().evalShaRo(digest, keys, arguments));
	}

	@Override
	default Object fCall(final String function) {
		return execute((client)->client.scriptingOperations().fCall(function));
	}

	@Override
	default Object fCall(final byte[] function) {
		return execute((client)->client.scriptingOperations().fCall(function));
	}

	@Override
	default Object fCall(final String function, final String... keys) {
		return execute((client)->client.scriptingOperations().fCall(function, keys));
	}

	@Override
	default Object fCall(final byte[] function, final byte[]... keys) {
		return execute((client)->client.scriptingOperations().fCall(function, keys));
	}

	@Override
	default Object fCall(final String function, final String[] keys, final String[] arguments) {
		return execute((client)->client.scriptingOperations().fCall(function, keys, arguments));
	}

	@Override
	default Object fCall(final byte[] function, final byte[][] keys, final byte[][] arguments) {
		return execute((client)->client.scriptingOperations().fCall(function, keys, arguments));
	}

	@Override
	default Object fCallRo(final String function) {
		return execute((client)->client.scriptingOperations().fCallRo(function));
	}

	@Override
	default Object fCallRo(final byte[] function) {
		return execute((client)->client.scriptingOperations().fCallRo(function));
	}

	@Override
	default Object fCallRo(final String function, final String... keys) {
		return execute((client)->client.scriptingOperations().fCallRo(function, keys));
	}

	@Override
	default Object fCallRo(final byte[] function, final byte[]... keys) {
		return execute((client)->client.scriptingOperations().fCallRo(function, keys));
	}

	@Override
	default Object fCallRo(final String function, final String[] keys, final String[] arguments) {
		return execute((client)->client.scriptingOperations().fCallRo(function, keys, arguments));
	}

	@Override
	default Object fCallRo(final byte[] function, final byte[][] keys, final byte[][] arguments) {
		return execute((client)->client.scriptingOperations().fCallRo(function, keys, arguments));
	}

	@Override
	default Status functionDelete(final String libraryName) {
		return execute((client)->client.scriptingOperations().functionDelete(libraryName));
	}

	@Override
	default byte[] functionDump() {
		return execute((client)->client.scriptingOperations().functionDump());
	}

	@Override
	default Status functionFlush() {
		return execute((client)->client.scriptingOperations().functionFlush());
	}

	@Override
	default Status functionFlush(final FlushMode flushMode) {
		return execute((client)->client.scriptingOperations().functionFlush(flushMode));
	}

	@Override
	default Status functionKill() {
		return execute((client)->client.scriptingOperations().functionKill());
	}

	@Override
	default List<LibraryInfo> functionList() {
		return execute((client)->client.scriptingOperations().functionList());
	}

	@Override
	default List<LibraryInfo> functionList(final String pattern) {
		return execute((client)->client.scriptingOperations().functionList(pattern));
	}

	@Override
	default List<LibraryInfo> functionList(final byte[] pattern) {
		return execute((client)->client.scriptingOperations().functionList(pattern));
	}

	@Override
	default List<LibraryInfo> functionList(final boolean withCode) {
		return execute((client)->client.scriptingOperations().functionList(withCode));
	}

	@Override
	default List<LibraryInfo> functionList(final String pattern, final boolean withCode) {
		return execute((client)->client.scriptingOperations().functionList(pattern, withCode));
	}

	@Override
	default List<LibraryInfo> functionList(final byte[] pattern, final boolean withCode) {
		return execute((client)->client.scriptingOperations().functionList(pattern, withCode));
	}

	@Override
	default String functionLoad(final String functionCode) {
		return execute((client)->client.scriptingOperations().functionLoad(functionCode));
	}

	@Override
	default String functionLoad(final String functionCode, final boolean replace) {
		return execute((client)->client.scriptingOperations().functionLoad(functionCode, replace));
	}

	@Override
	default Status functionRestore(final byte[] value) {
		return execute((client)->client.scriptingOperations().functionRestore(value));
	}

	@Override
	default Status functionRestore(final byte[] value, final FunctionRestoreMode mode) {
		return execute((client)->client.scriptingOperations().functionRestore(value, mode));
	}

	@Override
	default FunctionStats functionStats() {
		return execute((client)->client.scriptingOperations().functionStats());
	}

	@Override
	default Object scriptDebug() {
		return execute((client)->client.scriptingOperations().scriptDebug());
	}

	@Override
	default Object scriptDebug(final ScriptDebugMode mode) {
		return execute((client)->client.scriptingOperations().scriptDebug(mode));
	}

	@Override
	default List<Boolean> scriptExists(final String... sha1) {
		return execute((client)->client.scriptingOperations().scriptExists(sha1));
	}

	@Override
	default List<Boolean> scriptExists(final byte[]... sha1) {
		return execute((client)->client.scriptingOperations().scriptExists(sha1));
	}

	@Override
	default Status scriptFlush() {
		return execute((client)->client.scriptingOperations().scriptFlush());
	}

	@Override
	default Status scriptFlush(final FlushMode mode) {
		return execute((client)->client.scriptingOperations().scriptFlush(mode));
	}

	@Override
	default Status scriptKill() {
		return execute((client)->client.scriptingOperations().scriptKill());
	}

	@Override
	default String scriptLoad(final String script) {
		return execute((client)->client.scriptingOperations().scriptLoad(script));
	}

	@Override
	default byte[] scriptLoad(final byte[] script) {
		return execute((client)->client.scriptingOperations().scriptLoad(script));
	}

}
