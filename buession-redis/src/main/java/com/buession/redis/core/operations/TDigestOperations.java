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
import com.buession.redis.core.TdigestInfo;
import com.buession.redis.core.command.Command;
import com.buession.redis.core.command.TDigestCommands;
import com.buession.redis.utils.KeyUtils;

import java.util.List;

/**
 * T-Digest 命令
 *
 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/?group=tdigest" target="_blank">https://redis.io/docs/latest/commands/?group=tdigest</a></p>
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public interface TDigestOperations extends TDigestCommands, RedisOperations {

	@Override
	default Status tdigestAdd(final String key, final double... values) {
		return doExecute((cmd)->cmd.tdigestAdd(KeyUtils.rawKey(this, key), values));
	}

	@Override
	default Status tdigestAdd(final byte[] key, final double... values) {
		return doExecute((cmd)->cmd.tdigestAdd(KeyUtils.rawKey(this, key), values));
	}

	@Override
	default List<Double> tdigestByRank(final String key, final long... ranks) {
		return doExecute((cmd)->cmd.tdigestByRank(KeyUtils.rawKey(this, key), ranks));
	}

	@Override
	default List<Double> tdigestByRank(final byte[] key, final long... ranks) {
		return doExecute((cmd)->cmd.tdigestByRank(KeyUtils.rawKey(this, key), ranks));
	}

	@Override
	default List<Double> tdigestByRevRank(final String key, final long... ranks) {
		return doExecute((cmd)->cmd.tdigestByRevRank(KeyUtils.rawKey(this, key), ranks));
	}

	@Override
	default List<Double> tdigestByRevRank(final byte[] key, final long... ranks) {
		return doExecute((cmd)->cmd.tdigestByRevRank(KeyUtils.rawKey(this, key), ranks));
	}

	@Override
	default List<Double> tdigestCdf(final String key, final double... values) {
		return doExecute((cmd)->cmd.tdigestCdf(KeyUtils.rawKey(this, key), values));
	}

	@Override
	default List<Double> tdigestCdf(final byte[] key, final double... values) {
		return doExecute((cmd)->cmd.tdigestCdf(KeyUtils.rawKey(this, key), values));
	}

	@Override
	default Status tdigestCreate(final String key) {
		return doExecute((cmd)->cmd.tdigestCreate(KeyUtils.rawKey(this, key)));
	}

	@Override
	default Status tdigestCreate(final byte[] key) {
		return doExecute((cmd)->cmd.tdigestCreate(KeyUtils.rawKey(this, key)));
	}

	@Override
	default Status tdigestCreate(final String key, final int compression) {
		return doExecute((cmd)->cmd.tdigestCreate(KeyUtils.rawKey(this, key), compression));
	}

	@Override
	default Status tdigestCreate(final byte[] key, final int compression) {
		return doExecute((cmd)->cmd.tdigestCreate(KeyUtils.rawKey(this, key), compression));
	}

	@Override
	default TdigestInfo tdigestInfo(final String key) {
		return doExecute((cmd)->cmd.tdigestInfo(KeyUtils.rawKey(this, key)));
	}

	@Override
	default TdigestInfo tdigestInfo(final byte[] key) {
		return doExecute((cmd)->cmd.tdigestInfo(KeyUtils.rawKey(this, key)));
	}

	@Override
	default Double tdigestMax(final String key) {
		return doExecute((cmd)->cmd.tdigestMax(KeyUtils.rawKey(this, key)));
	}

	@Override
	default Double tdigestMax(final byte[] key) {
		return doExecute((cmd)->cmd.tdigestMax(KeyUtils.rawKey(this, key)));
	}

	@Override
	default Status tdigestMerge(final String destKey, final String... keys) {
		return doExecute((cmd)->cmd
				.tdigestMerge(KeyUtils.rawKey(this, destKey), KeyUtils.rawKeys(this, keys)));
	}

	@Override
	default Status tdigestMerge(final byte[] destKey, final byte[]... keys) {
		return doExecute((cmd)->cmd
				.tdigestMerge(KeyUtils.rawKey(this, destKey), KeyUtils.rawKeys(this, keys)));
	}

	@Override
	default Status tdigestMerge(final String destKey, final String[] keys, final int compression) {
		return doExecute((cmd)->cmd
				.tdigestMerge(KeyUtils.rawKey(this, destKey), KeyUtils.rawKeys(this, keys), compression));
	}

	@Override
	default Status tdigestMerge(final byte[] destKey, final byte[][] keys, final int compression) {
		return doExecute((cmd)->cmd
				.tdigestMerge(KeyUtils.rawKey(this, destKey), KeyUtils.rawKeys(this, keys), compression));
	}

	@Override
	default Status tdigestMerge(final String destKey, final String[] keys, final int compression,
	                            final boolean override) {
		return doExecute((cmd)->cmd
				.tdigestMerge(KeyUtils.rawKey(this, destKey), KeyUtils.rawKeys(this, keys), compression, override));
	}

	@Override
	default Status tdigestMerge(final byte[] destKey, final byte[][] keys, final int compression,
	                            final boolean override) {
		return doExecute((cmd)->cmd
				.tdigestMerge(KeyUtils.rawKey(this, destKey), KeyUtils.rawKeys(this, keys), compression, override));
	}

	@Override
	default Status tdigestMerge(final String destKey, final String[] keys, final boolean override) {
		return doExecute((cmd)->cmd
				.tdigestMerge(KeyUtils.rawKey(this, destKey), KeyUtils.rawKeys(this, keys), override));
	}

	@Override
	default Status tdigestMerge(final byte[] destKey, final byte[][] keys, final boolean override) {
		return doExecute((cmd)->cmd
				.tdigestMerge(KeyUtils.rawKey(this, destKey), KeyUtils.rawKeys(this, keys), override));
	}

	@Override
	default Double tdigestMin(final String key) {
		return doExecute((cmd)->cmd.tdigestMin(KeyUtils.rawKey(this, key)));
	}

	@Override
	default Double tdigestMin(final byte[] key) {
		return doExecute((cmd)->cmd.tdigestMin(KeyUtils.rawKey(this, key)));
	}

	@Override
	default List<Double> tdigestQuantile(final String key, final double... quantiles) {
		return doExecute((cmd)->cmd.tdigestQuantile(KeyUtils.rawKey(this, key), quantiles));
	}

	@Override
	default List<Double> tdigestQuantile(final byte[] key, final double... quantiles) {
		return doExecute((cmd)->cmd.tdigestQuantile(KeyUtils.rawKey(this, key), quantiles));
	}

	@Override
	default List<Long> tdigestRank(final String key, final double... values) {
		return doExecute((cmd)->cmd.tdigestRank(KeyUtils.rawKey(this, key), values));
	}

	@Override
	default List<Long> tdigestRank(final byte[] key, final double... values) {
		return doExecute((cmd)->cmd.tdigestRank(KeyUtils.rawKey(this, key), values));
	}

	@Override
	default Status tdigestReset(final String key) {
		return doExecute((cmd)->cmd.tdigestReset(KeyUtils.rawKey(this, key)));
	}

	@Override
	default Status tdigestReset(final byte[] key) {
		return doExecute((cmd)->cmd.tdigestReset(KeyUtils.rawKey(this, key)));
	}

	@Override
	default List<Long> tdigestRevRank(final String key, final double... values) {
		return doExecute((cmd)->cmd.tdigestRevRank(KeyUtils.rawKey(this, key), values));
	}

	@Override
	default List<Long> tdigestRevRank(final byte[] key, final double... values) {
		return doExecute((cmd)->cmd.tdigestRevRank(KeyUtils.rawKey(this, key), values));
	}

	@Override
	default Double tdigestTrimmedMean(final String key, final double lowCutQuantile, final double highCutQuantile) {
		return doExecute((cmd)->cmd.tdigestTrimmedMean(KeyUtils.rawKey(this, key), lowCutQuantile, highCutQuantile));
	}

	@Override
	default Double tdigestTrimmedMean(final byte[] key, final double lowCutQuantile, final double highCutQuantile) {
		return doExecute((cmd)->cmd.tdigestTrimmedMean(KeyUtils.rawKey(this, key), lowCutQuantile, highCutQuantile));
	}

	private <R> R doExecute(final Command.Executor<TDigestCommands, R> executor) {
		return execute((client)->executor.execute(client.tDigestCommands()));
	}

}
