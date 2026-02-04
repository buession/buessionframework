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
import com.buession.redis.core.command.HyperLogLogCommands;

/**
 * HyperLogLog 运算
 *
 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/?group=hyperloglog" target="_blank">https://redis.io/docs/latest/commands/?group=hyperloglog</a></p>
 *
 * @author Yong.Teng
 */
public interface HyperLogLogOperations extends HyperLogLogCommands, RedisOperations {

	@Override
	default Status pfAdd(final String key, final String... elements) {
		return execute((client)->client.hyperLogLogOperations().pfAdd(key, elements));
	}

	@Override
	default Status pfAdd(final byte[] key, final byte[]... elements) {
		return execute((client)->client.hyperLogLogOperations().pfAdd(key, elements));
	}

	@Override
	default Long pfCount(final String... keys) {
		return execute((client)->client.hyperLogLogOperations().pfCount(keys));
	}

	@Override
	default Long pfCount(final byte[]... keys) {
		return execute((client)->client.hyperLogLogOperations().pfCount(keys));
	}

	@Override
	default Status pfMerge(final String destKey, final String... keys) {
		return execute((client)->client.hyperLogLogOperations().pfMerge(destKey, keys));
	}

	@Override
	default Status pfMerge(final byte[] destKey, final byte[]... keys) {
		return execute((client)->client.hyperLogLogOperations().pfMerge(destKey, keys));
	}

	@Override
	default Status pfSelftest() {
		return execute((client)->client.hyperLogLogOperations().pfSelftest());
	}

}
