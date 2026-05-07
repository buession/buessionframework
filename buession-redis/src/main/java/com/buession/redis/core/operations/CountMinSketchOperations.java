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

import com.buession.core.collect.Arrays;
import com.buession.lang.KeyValue;
import com.buession.lang.Status;
import com.buession.redis.core.CmsInfo;
import com.buession.redis.core.command.Command;
import com.buession.redis.core.command.CountMinSketchCommands;
import com.buession.redis.utils.KeyUtils;

import java.util.List;

/**
 * 计数最小草图命令
 *
 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/?group=cms" target="_blank">https://redis.io/docs/latest/commands/?group=cms</a></p>
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public interface CountMinSketchOperations extends CountMinSketchCommands, RedisOperations {

	@SuppressWarnings({"unchecked"})
	@Override
	default List<Long> cmsIncrby(final String key, final KeyValue<String, Long>... items) {
		return doExecute((cmd)->cmd.cmsIncrby(KeyUtils.rawKey(this, key), items));
	}

	@SuppressWarnings({"unchecked"})
	@Override
	default List<Long> cmsIncrby(final byte[] key, final KeyValue<byte[], Long>... items) {
		return doExecute((cmd)->cmd.cmsIncrby(KeyUtils.rawKey(this, key), items));
	}

	@Override
	default CmsInfo cmsInfo(final String key) {
		return doExecute((cmd)->cmd.cmsInfo(KeyUtils.rawKey(this, key)));
	}

	@Override
	default CmsInfo cmsInfo(final byte[] key) {
		return doExecute((cmd)->cmd.cmsInfo(KeyUtils.rawKey(this, key)));
	}

	@Override
	default Status cmsInitByDim(final String key, final int width, final int depth) {
		return doExecute((cmd)->cmd.cmsInitByDim(KeyUtils.rawKey(this, key), width, depth));
	}

	@Override
	default Status cmsInitByDim(final byte[] key, final int width, final int depth) {
		return doExecute((cmd)->cmd.cmsInitByDim(KeyUtils.rawKey(this, key), width, depth));
	}

	@Override
	default Status cmsInitByProb(final String key, final double error, final double probability) {
		return doExecute((cmd)->cmd
				.cmsInitByProb(KeyUtils.rawKey(this, key), error, probability));
	}

	@Override
	default Status cmsInitByProb(final byte[] key, final double error, final double probability) {
		return doExecute((cmd)->cmd
				.cmsInitByProb(KeyUtils.rawKey(this, key), error, probability));
	}

	@SuppressWarnings({"unchecked"})
	@Override
	default Status cmsMerge(final String destKey, final KeyValue<String, Long>... keysAndWeights) {
		final KeyValue<String, Long>[] newKeysAndWeights = Arrays.map(keysAndWeights,
				(item)->new KeyValue<>(KeyUtils.rawKey(this, item.getKey()),
						item.getValue()));
		return doExecute((cmd)->cmd.cmsMerge(KeyUtils.rawKey(this, destKey), newKeysAndWeights));
	}

	@SuppressWarnings({"unchecked"})
	@Override
	default Status cmsMerge(final byte[] destKey, final KeyValue<byte[], Long>... keysAndWeights) {
		final KeyValue<byte[], Long>[] newKeysAndWeights = Arrays.map(keysAndWeights,
				(item)->new KeyValue<>(KeyUtils.rawKey(this, item.getKey()),
						item.getValue()));
		return doExecute((cmd)->cmd.cmsMerge(KeyUtils.rawKey(this, destKey), newKeysAndWeights));
	}

	@Override
	default List<Long> cmsQuery(final String key, final String... items) {
		return doExecute((cmd)->cmd.cmsQuery(KeyUtils.rawKey(this, key), items));
	}

	@Override
	default List<Long> cmsQuery(final byte[] key, final byte[]... items) {
		return doExecute((cmd)->cmd.cmsQuery(KeyUtils.rawKey(this, key), items));
	}

	private <R> R doExecute(final Command.Executor<CountMinSketchCommands, R> executor) {
		return execute((client)->executor.execute(client.countMinSketchCommands()));
	}

}
