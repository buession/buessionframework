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
import com.buession.redis.core.Suggestion;
import com.buession.redis.core.command.AutoSuggestCommands;
import com.buession.redis.core.command.Command;
import com.buession.redis.core.command.args.autosuggest.SugGetArgument;
import com.buession.redis.utils.KeyUtils;

import java.util.List;

/**
 * 自动提示命令
 *
 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/?group=suggestion" target="_blank">https://redis.io/docs/latest/commands/?group=suggestion</a></p>
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public interface AutoSuggestOperations extends AutoSuggestCommands, RedisOperations {

	@Override
	default Long ftSugAdd(final String key, final String value, final double score) {
		return doExecute((cmd)->cmd.ftSugAdd(KeyUtils.rawKey(this, key), value, score));
	}

	@Override
	default Long ftSugAdd(final byte[] key, final byte[] value, final double score) {
		return doExecute((cmd)->cmd.ftSugAdd(KeyUtils.rawKey(this, key), value, score));
	}

	@Override
	default Long ftSugAdd(final String key, final String value, final double score, final boolean incr) {
		return doExecute((cmd)->cmd.ftSugAdd(KeyUtils.rawKey(this, key), value, score, incr));
	}

	@Override
	default Long ftSugAdd(final byte[] key, final byte[] value, final double score, final boolean incr) {
		return doExecute((cmd)->cmd.ftSugAdd(KeyUtils.rawKey(this, key), value, score, incr));
	}

	@Override
	default Long ftSugAdd(final String key, final String value, final double score, final boolean incr,
	                      final String payload) {
		return doExecute((cmd)->cmd.ftSugAdd(KeyUtils.rawKey(this, key), value, score, incr, payload));
	}

	@Override
	default Long ftSugAdd(final byte[] key, final byte[] value, final double score, final boolean incr,
	                      final byte[] payload) {
		return doExecute((cmd)->cmd.ftSugAdd(KeyUtils.rawKey(this, key), value, score, incr, payload));
	}

	@Override
	default Long ftSugAdd(final String key, final String value, final double score, final String payload) {
		return doExecute((cmd)->cmd.ftSugAdd(KeyUtils.rawKey(this, key), value, score, payload));
	}

	@Override
	default Long ftSugAdd(final byte[] key, final byte[] value, final double score, final byte[] payload) {
		return doExecute((cmd)->cmd.ftSugAdd(KeyUtils.rawKey(this, key), value, score, payload));
	}

	@Override
	default Status ftSugDel(final String key, final String value) {
		return doExecute((cmd)->cmd.ftSugDel(KeyUtils.rawKey(this, key), value));
	}

	@Override
	default Status ftSugDel(final byte[] key, final byte[] value) {
		return doExecute((cmd)->cmd.ftSugDel(KeyUtils.rawKey(this, key), value));
	}

	@Override
	default List<Suggestion> ftSugGet(final String key, final String prefix) {
		return doExecute((cmd)->cmd.ftSugGet(KeyUtils.rawKey(this, key), prefix));
	}

	@Override
	default List<Suggestion> ftSugGet(final byte[] key, final byte[] prefix) {
		return doExecute((cmd)->cmd.ftSugGet(KeyUtils.rawKey(this, key), prefix));
	}

	@Override
	default List<Suggestion> ftSugGet(final String key, final String prefix, final SugGetArgument argument) {
		return doExecute((cmd)->cmd.ftSugGet(KeyUtils.rawKey(this, key), prefix, argument));
	}

	@Override
	default List<Suggestion> ftSugGet(final byte[] key, final byte[] prefix, final SugGetArgument argument) {
		return doExecute((cmd)->cmd.ftSugGet(KeyUtils.rawKey(this, key), prefix, argument));
	}

	@Override
	default Long ftSugLen(final String key) {
		return doExecute((cmd)->cmd.ftSugLen(KeyUtils.rawKey(this, key)));
	}

	@Override
	default Long ftSugLen(final byte[] key) {
		return doExecute((cmd)->cmd.ftSugLen(KeyUtils.rawKey(this, key)));
	}

	private <R> R doExecute(final Command.Executor<AutoSuggestCommands, R> executor) {
		return execute((client)->executor.execute(client.autoSuggestCommands()));
	}

}
