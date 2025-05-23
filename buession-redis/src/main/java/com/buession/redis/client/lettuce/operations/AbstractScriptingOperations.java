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

import com.buession.redis.client.lettuce.LettuceRedisClient;
import com.buession.redis.client.operations.ScriptingOperations;
import com.buession.redis.utils.SafeEncoder;

import java.util.List;

/**
 * Lettuce Script 命令操作抽象类
 *
 * @param <C>
 * 		Redis Client {@link LettuceRedisClient}
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public abstract class AbstractScriptingOperations<C extends LettuceRedisClient>
		extends AbstractLettuceRedisOperations<C>
		implements ScriptingOperations {

	public AbstractScriptingOperations(final C client) {
		super(client);
	}

	@Override
	public Object eval(final byte[] script) {
		return eval(SafeEncoder.encode(script));
	}

	@Override
	public Object eval(final byte[] script, final byte[]... params) {
		return eval(SafeEncoder.encode(script), SafeEncoder.encode(params));
	}

	@Override
	public Object eval(final byte[] script, final byte[][] keys, final byte[][] arguments) {
		return eval(SafeEncoder.encode(script), SafeEncoder.encode(keys), SafeEncoder.encode(arguments));
	}

	@Override
	public Object evalSha(final byte[] digest) {
		return evalSha(SafeEncoder.encode(digest));
	}

	@Override
	public Object evalSha(final byte[] digest, final byte[]... params) {
		return evalSha(SafeEncoder.encode(digest), SafeEncoder.encode(params));
	}

	@Override
	public Object evalSha(final byte[] digest, final byte[][] keys, final byte[][] arguments) {
		return evalSha(SafeEncoder.encode(digest), SafeEncoder.encode(keys), SafeEncoder.encode(arguments));
	}

	@Override
	public List<Boolean> scriptExists(final byte[]... sha1) {
		return scriptExists(SafeEncoder.encode(sha1));
	}

}
