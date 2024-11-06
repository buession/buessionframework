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
package com.buession.redis.core.operations;

import com.buession.lang.Status;
import com.buession.redis.core.FlushMode;
import com.buession.redis.core.command.ScriptingCommands;

import java.util.List;

/**
 * LUA 脚本运算
 *
 * <p>详情说明 <a href="http://redisdoc.com/script/index.html" target="_blank">http://redisdoc.com/script/index.html</a></p>
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
	default Object eval(final String script, final String... params) {
		return execute((client)->client.scriptingOperations().eval(script, params));
	}

	@Override
	default Object eval(final byte[] script, final byte[]... params) {
		return execute((client)->client.scriptingOperations().eval(script, params));
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
	default Object evalSha(final String digest, final String... params) {
		return execute((client)->client.scriptingOperations().evalSha(digest, params));
	}

	@Override
	default Object evalSha(final byte[] digest, final byte[]... params) {
		return execute((client)->client.scriptingOperations().evalSha(digest, params));
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
	default String scriptLoad(final String script) {
		return execute((client)->client.scriptingOperations().scriptLoad(script));
	}

	@Override
	default byte[] scriptLoad(final byte[] script) {
		return execute((client)->client.scriptingOperations().scriptLoad(script));
	}

	@Override
	default Status scriptKill() {
		return execute((client)->client.scriptingOperations().scriptKill());
	}

}
