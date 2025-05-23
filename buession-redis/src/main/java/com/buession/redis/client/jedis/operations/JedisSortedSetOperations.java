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
package com.buession.redis.client.jedis.operations;

import com.buession.core.converter.ListConverter;
import com.buession.core.converter.SetConverter;
import com.buession.core.utils.NumberUtils;
import com.buession.lang.KeyValue;
import com.buession.redis.client.jedis.JedisStandaloneClient;
import com.buession.redis.core.Aggregate;
import com.buession.redis.core.GtLt;
import com.buession.redis.core.NxXx;
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.Tuple;
import com.buession.redis.core.ZRangeBy;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.internal.convert.jedis.response.KeyValueConverter;
import com.buession.redis.core.internal.convert.jedis.response.ScanResultConverter;
import com.buession.redis.core.internal.convert.jedis.response.TupleConverter;
import com.buession.redis.core.internal.jedis.JedisScanParams;
import com.buession.redis.core.internal.jedis.JedisZAddParams;
import com.buession.redis.core.internal.jedis.JedisZParams;
import com.buession.redis.core.internal.jedis.JedisZRangeParams;
import redis.clients.jedis.params.ScanParams;
import redis.clients.jedis.params.ZAddParams;
import redis.clients.jedis.params.ZParams;
import redis.clients.jedis.params.ZRangeParams;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Jedis 单机模式模式有序集合命令操作
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public final class JedisSortedSetOperations extends AbstractSortedSetOperations<JedisStandaloneClient> {

	public JedisSortedSetOperations(final JedisStandaloneClient client) {
		super(client);
	}

	@Override
	public Tuple zPopMin(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		final TupleConverter tupleConverter = new TupleConverter();

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZPOPMIN, (cmd)->cmd.zpopmin(key), tupleConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZPOPMIN, (cmd)->cmd.zpopmin(key),
					tupleConverter)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZPOPMIN, (cmd)->cmd.zpopmin(key), tupleConverter)
					.run(args);
		}
	}

	@Override
	public Tuple zPopMin(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		final TupleConverter tupleConverter = new TupleConverter();

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZPOPMIN, (cmd)->cmd.zpopmin(key), tupleConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZPOPMIN, (cmd)->cmd.zpopmin(key),
					tupleConverter)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZPOPMIN, (cmd)->cmd.zpopmin(key), tupleConverter)
					.run(args);
		}
	}

	@Override
	public List<Tuple> zPopMin(final String key, final long count) {
		final CommandArguments args = CommandArguments.create(key).add(count);
		final ListConverter<redis.clients.jedis.resps.Tuple, Tuple> listTupleConverter = TupleConverter.listConverter();

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZPOPMIN, (cmd)->cmd.zpopmin(key, (int) count),
					listTupleConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZPOPMIN, (cmd)->cmd.zpopmin(key, (int) count),
					listTupleConverter)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZPOPMIN, (cmd)->cmd.zpopmin(key, (int) count),
					listTupleConverter)
					.run(args);
		}
	}

	@Override
	public List<Tuple> zPopMin(final byte[] key, final long count) {
		final CommandArguments args = CommandArguments.create(key).add(count);
		final ListConverter<redis.clients.jedis.resps.Tuple, Tuple> listTupleConverter = TupleConverter.listConverter();

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZPOPMIN, (cmd)->cmd.zpopmin(key, (int) count),
					listTupleConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZPOPMIN, (cmd)->cmd.zpopmin(key, (int) count),
					listTupleConverter)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZPOPMIN, (cmd)->cmd.zpopmin(key, (int) count),
					listTupleConverter)
					.run(args);
		}
	}

	@Override
	public Tuple zPopMax(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		final TupleConverter tupleConverter = new TupleConverter();

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZPOPMAX, (cmd)->cmd.zpopmax(key), tupleConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZPOPMAX, (cmd)->cmd.zpopmax(key),
					tupleConverter)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZPOPMAX, (cmd)->cmd.zpopmax(key), tupleConverter)
					.run(args);
		}
	}

	@Override
	public Tuple zPopMax(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		final TupleConverter tupleConverter = new TupleConverter();

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZPOPMAX, (cmd)->cmd.zpopmax(key), tupleConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZPOPMAX, (cmd)->cmd.zpopmax(key),
					tupleConverter)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZPOPMAX, (cmd)->cmd.zpopmax(key), tupleConverter)
					.run(args);
		}
	}

	@Override
	public List<Tuple> zPopMax(final String key, final long count) {
		final CommandArguments args = CommandArguments.create(key).add(count);
		final ListConverter<redis.clients.jedis.resps.Tuple, Tuple> listTupleConverter = TupleConverter.listConverter();

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZPOPMAX, (cmd)->cmd.zpopmax(key, (int) count),
					listTupleConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZPOPMAX, (cmd)->cmd.zpopmax(key, (int) count),
					listTupleConverter)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZPOPMAX, (cmd)->cmd.zpopmax(key, (int) count),
					listTupleConverter)
					.run(args);
		}
	}

	@Override
	public List<Tuple> zPopMax(final byte[] key, final long count) {
		final CommandArguments args = CommandArguments.create(key).add(count);
		final ListConverter<redis.clients.jedis.resps.Tuple, Tuple> listTupleConverter = TupleConverter.listConverter();

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZPOPMAX, (cmd)->cmd.zpopmax(key, (int) count),
					listTupleConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZPOPMAX, (cmd)->cmd.zpopmax(key, (int) count),
					listTupleConverter)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZPOPMAX, (cmd)->cmd.zpopmax(key, (int) count),
					listTupleConverter)
					.run(args);
		}
	}

	@Override
	public KeyValue<String, Tuple> bzPopMin(final String[] keys, final int timeout) {
		final CommandArguments args = CommandArguments.create(keys).add(timeout);
		final KeyValueConverter<String, redis.clients.jedis.resps.Tuple, String, Tuple> keyValueConverter =
				new KeyValueConverter<>((k)->k, new TupleConverter());

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.BZPOPMIN, (cmd)->cmd.bzpopmin(timeout, keys),
					keyValueConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.BZPOPMIN, (cmd)->cmd.bzpopmin(timeout, keys),
					keyValueConverter)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.BZPOPMIN, (cmd)->cmd.bzpopmin(timeout, keys),
					keyValueConverter)
					.run(args);
		}
	}

	@Override
	public KeyValue<byte[], Tuple> bzPopMin(final byte[][] keys, final int timeout) {
		final CommandArguments args = CommandArguments.create(keys).add(timeout);
		final KeyValueConverter<byte[], redis.clients.jedis.resps.Tuple, byte[], Tuple> keyValueConverter =
				new KeyValueConverter<>((k)->k, new TupleConverter());

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.BZPOPMIN, (cmd)->cmd.bzpopmin(timeout, keys),
					keyValueConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.BZPOPMIN, (cmd)->cmd.bzpopmin(timeout, keys),
					keyValueConverter)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.BZPOPMIN, (cmd)->cmd.bzpopmin(timeout, keys),
					keyValueConverter)
					.run(args);
		}
	}

	@Override
	public KeyValue<String, Tuple> bzPopMax(final String[] keys, final int timeout) {
		final CommandArguments args = CommandArguments.create(keys).add(timeout);
		final KeyValueConverter<String, redis.clients.jedis.resps.Tuple, String, Tuple> keyValueConverter =
				new KeyValueConverter<>((k)->k, new TupleConverter());

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.BZPOPMAX, (cmd)->cmd.bzpopmax(timeout, keys),
					keyValueConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.BZPOPMAX, (cmd)->cmd.bzpopmax(timeout, keys),
					keyValueConverter)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.BZPOPMAX, (cmd)->cmd.bzpopmax(timeout, keys),
					keyValueConverter)
					.run(args);
		}
	}

	@Override
	public KeyValue<byte[], Tuple> bzPopMax(final byte[][] keys, final int timeout) {
		final CommandArguments args = CommandArguments.create(keys).add(timeout);
		final KeyValueConverter<byte[], redis.clients.jedis.resps.Tuple, byte[], Tuple> keyValueConverter =
				new KeyValueConverter<>((k)->k, new TupleConverter());

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.BZPOPMAX, (cmd)->cmd.bzpopmax(timeout, keys),
					keyValueConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.BZPOPMAX, (cmd)->cmd.bzpopmax(timeout, keys),
					keyValueConverter)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.BZPOPMAX, (cmd)->cmd.bzpopmax(timeout, keys),
					keyValueConverter)
					.run(args);
		}
	}

	@Override
	public Long zAdd(final String key, final Map<String, Double> members) {
		final CommandArguments args = CommandArguments.create(key).add(members);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZADD, (cmd)->cmd.zadd(key, members), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZADD, (cmd)->cmd.zadd(key, members), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZADD, (cmd)->cmd.zadd(key, members), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long zAdd(final byte[] key, final Map<byte[], Double> members) {
		final CommandArguments args = CommandArguments.create(key).add(members);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZADD, (cmd)->cmd.zadd(key, members), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZADD, (cmd)->cmd.zadd(key, members), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZADD, (cmd)->cmd.zadd(key, members), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long zAdd(final String key, final Map<String, Double> members, final NxXx nxXx) {
		final CommandArguments args = CommandArguments.create(key).add(members).add(nxXx);
		final ZAddParams zAddParams = new JedisZAddParams(nxXx);

		return zAdd(key, members, zAddParams, args);
	}

	@Override
	public Long zAdd(final byte[] key, final Map<byte[], Double> members, final NxXx nxXx) {
		final CommandArguments args = CommandArguments.create(key).add(members).add(nxXx);
		final ZAddParams zAddParams = new JedisZAddParams(nxXx);

		return zAdd(key, members, zAddParams, args);
	}

	@Override
	public Long zAdd(final String key, final Map<String, Double> members, final GtLt gtLt) {
		final CommandArguments args = CommandArguments.create(key).add(members).add(gtLt);
		final ZAddParams zAddParams = new JedisZAddParams(gtLt);

		return zAdd(key, members, zAddParams, args);
	}

	@Override
	public Long zAdd(final byte[] key, final Map<byte[], Double> members, final GtLt gtLt) {
		final CommandArguments args = CommandArguments.create(key).add(members).add(gtLt);
		final ZAddParams zAddParams = new JedisZAddParams(gtLt);

		return zAdd(key, members, zAddParams, args);
	}

	@Override
	public Long zAdd(final String key, final Map<String, Double> members, final boolean ch) {
		final CommandArguments args = CommandArguments.create(key).add(members).add(ch);
		final ZAddParams zAddParams = new JedisZAddParams(ch);

		return zAdd(key, members, zAddParams, args);
	}

	@Override
	public Long zAdd(final byte[] key, final Map<byte[], Double> members, final boolean ch) {
		final CommandArguments args = CommandArguments.create(key).add(members).add(ch);
		final ZAddParams zAddParams = new JedisZAddParams(ch);

		return zAdd(key, members, zAddParams, args);
	}

	@Override
	public Long zAdd(final String key, final Map<String, Double> members, final NxXx nxXx, final GtLt gtLt) {
		final CommandArguments args = CommandArguments.create(key).add(members).add(nxXx)
				.add(gtLt);
		final ZAddParams zAddParams = new JedisZAddParams(nxXx, gtLt);

		return zAdd(key, members, zAddParams, args);
	}

	@Override
	public Long zAdd(final byte[] key, final Map<byte[], Double> members, final NxXx nxXx, final GtLt gtLt) {
		final CommandArguments args = CommandArguments.create(key).add(members).add(nxXx)
				.add(gtLt);
		final ZAddParams zAddParams = new JedisZAddParams(nxXx, gtLt);

		return zAdd(key, members, zAddParams, args);
	}

	@Override
	public Long zAdd(final String key, final Map<String, Double> members, final NxXx nxXx, final boolean ch) {
		final CommandArguments args = CommandArguments.create(key).add(members).add(nxXx)
				.add(ch);
		final ZAddParams zAddParams = new JedisZAddParams(nxXx, ch);

		return zAdd(key, members, zAddParams, args);
	}

	@Override
	public Long zAdd(final byte[] key, final Map<byte[], Double> members, final NxXx nxXx, final boolean ch) {
		final CommandArguments args = CommandArguments.create(key).add(members).add(nxXx)
				.add(ch);
		final ZAddParams zAddParams = new JedisZAddParams(nxXx, ch);

		return zAdd(key, members, zAddParams, args);
	}

	@Override
	public Long zAdd(final String key, final Map<String, Double> members, final GtLt gtLt, final boolean ch) {
		final CommandArguments args = CommandArguments.create(key).add(members).add(gtLt)
				.add(ch);
		final ZAddParams zAddParams = new JedisZAddParams(gtLt, ch);

		return zAdd(key, members, zAddParams, args);
	}

	@Override
	public Long zAdd(final byte[] key, final Map<byte[], Double> members, final GtLt gtLt, final boolean ch) {
		final CommandArguments args = CommandArguments.create(key).add(members).add(gtLt)
				.add(ch);
		final ZAddParams zAddParams = new JedisZAddParams(gtLt, ch);

		return zAdd(key, members, zAddParams, args);
	}

	@Override
	public Long zAdd(final String key, final Map<String, Double> members, final NxXx nxXx, final GtLt gtLt,
					 final boolean ch) {
		final CommandArguments args = CommandArguments.create(key).add(members).add(nxXx)
				.add(gtLt).add(ch);
		final ZAddParams zAddParams = new JedisZAddParams(nxXx, gtLt, ch);

		return zAdd(key, members, zAddParams, args);
	}

	@Override
	public Long zAdd(final byte[] key, final Map<byte[], Double> members, final NxXx nxXx, final GtLt gtLt,
					 final boolean ch) {
		final CommandArguments args = CommandArguments.create(key).add(members).add(nxXx)
				.add(gtLt).add(ch);
		final ZAddParams zAddParams = new JedisZAddParams(nxXx, gtLt, ch);

		return zAdd(key, members, zAddParams, args);
	}

	@Override
	public Long zCard(final String key) {
		final CommandArguments args = CommandArguments.create(key);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZCARD, (cmd)->cmd.zcard(key), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZCARD, (cmd)->cmd.zcard(key), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZCARD, (cmd)->cmd.zcard(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long zCard(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZCARD, (cmd)->cmd.zcard(key), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZCARD, (cmd)->cmd.zcard(key), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZCARD, (cmd)->cmd.zcard(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long zCount(final String key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZCOUNT, (cmd)->cmd.zcount(key, min, max), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZCOUNT, (cmd)->cmd.zcount(key, min, max),
					(v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZCOUNT, (cmd)->cmd.zcount(key, min, max), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long zCount(final byte[] key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZCOUNT, (cmd)->cmd.zcount(key, min, max), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZCOUNT, (cmd)->cmd.zcount(key, min, max),
					(v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZCOUNT, (cmd)->cmd.zcount(key, min, max), (v)->v)
					.run(args);
		}
	}

	@Deprecated
	@Override
	public Long zCount(final String key, final String min, final String max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZCOUNT, (cmd)->cmd.zcount(key, min, max), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZCOUNT, (cmd)->cmd.zcount(key, min, max),
					(v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZCOUNT, (cmd)->cmd.zcount(key, min, max), (v)->v)
					.run(args);
		}
	}

	@Deprecated
	@Override
	public Long zCount(final byte[] key, final byte[] min, final byte[] max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZCOUNT, (cmd)->cmd.zcount(key, min, max), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZCOUNT, (cmd)->cmd.zcount(key, min, max),
					(v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZCOUNT, (cmd)->cmd.zcount(key, min, max), (v)->v)
					.run(args);
		}
	}

	@Override
	public List<String> zDiff(final String... keys) {
		final CommandArguments args = CommandArguments.create(keys);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZDIFF, (cmd)->cmd.zdiff(keys), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZDIFF, (cmd)->cmd.zdiff(keys), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZDIFF, (cmd)->cmd.zdiff(keys), (v)->v)
					.run(args);
		}
	}

	@Override
	public List<byte[]> zDiff(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(keys);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZDIFF, (cmd)->cmd.zdiff(keys), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZDIFF, (cmd)->cmd.zdiff(keys), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZDIFF, (cmd)->cmd.zdiff(keys), (v)->v)
					.run(args);
		}
	}

	@Override
	public List<Tuple> zDiffWithScores(final String... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		final ListConverter<redis.clients.jedis.resps.Tuple, Tuple> listConverter = TupleConverter.listConverter();

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZDIFF, (cmd)->cmd.zdiffWithScores(keys),
					listConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZDIFF, (cmd)->cmd.zdiffWithScores(keys),
					listConverter)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZDIFF, (cmd)->cmd.zdiffWithScores(keys),
					listConverter)
					.run(args);
		}
	}

	@Override
	public List<Tuple> zDiffWithScores(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		final ListConverter<redis.clients.jedis.resps.Tuple, Tuple> listConverter = TupleConverter.listConverter();

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZDIFF, (cmd)->cmd.zdiffWithScores(keys),
					listConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZDIFF, (cmd)->cmd.zdiffWithScores(keys),
					listConverter)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZDIFF, (cmd)->cmd.zdiffWithScores(keys),
					listConverter)
					.run(args);
		}
	}

	@Override
	public Long zDiffStore(final String destKey, final String... keys) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZDIFFSTORE, (cmd)->cmd.zdiffStore(destKey, keys),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZDIFFSTORE,
					(cmd)->cmd.zdiffStore(destKey, keys), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZDIFFSTORE, (cmd)->cmd.zdiffStore(destKey, keys), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long zDiffStore(final byte[] destKey, final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZDIFFSTORE, (cmd)->cmd.zdiffStore(destKey, keys),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZDIFFSTORE,
					(cmd)->cmd.zdiffStore(destKey, keys), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZDIFFSTORE, (cmd)->cmd.zdiffStore(destKey, keys), (v)->v)
					.run(args);
		}
	}

	@Override
	public Double zIncrBy(final String key, final double increment, final String member) {
		final CommandArguments args = CommandArguments.create(key).add(increment)
				.add(member);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZINCRBY,
					(cmd)->cmd.zincrby(key, increment, member), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZINCRBY,
					(cmd)->cmd.zincrby(key, increment, member), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZINCRBY, (cmd)->cmd.zincrby(key, increment, member),
					(v)->v)
					.run(args);
		}
	}

	@Override
	public Double zIncrBy(final byte[] key, final double increment, final byte[] member) {
		final CommandArguments args = CommandArguments.create(key).add(increment)
				.add(member);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZINCRBY,
					(cmd)->cmd.zincrby(key, increment, member), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZINCRBY,
					(cmd)->cmd.zincrby(key, increment, member), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZINCRBY, (cmd)->cmd.zincrby(key, increment, member),
					(v)->v)
					.run(args);
		}
	}

	@Override
	public List<String> zInter(final String... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		final ZParams zParams = new JedisZParams();

		return zInter(keys, zParams, args);
	}

	@Override
	public List<byte[]> zInter(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		final ZParams zParams = new JedisZParams();

		return zInter(keys, zParams, args);
	}

	@Override
	public List<String> zInter(final String[] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create(keys).add(aggregate);
		final ZParams zParams = new JedisZParams(aggregate);

		return zInter(keys, zParams, args);
	}

	@Override
	public List<byte[]> zInter(final byte[][] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create(keys).add(aggregate);
		final ZParams zParams = new JedisZParams(aggregate);

		return zInter(keys, zParams, args);
	}

	@Override
	public List<String> zInter(final String[] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add(weights);
		final ZParams zParams = new JedisZParams(weights);

		return zInter(keys, zParams, args);
	}

	@Override
	public List<byte[]> zInter(final byte[][] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add(weights);
		final ZParams zParams = new JedisZParams(weights);

		return zInter(keys, zParams, args);
	}

	@Override
	public List<String> zInter(final String[] keys, final Aggregate aggregate, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add(aggregate)
				.add(weights);
		final ZParams zParams = new JedisZParams(aggregate, weights);

		return zInter(keys, zParams, args);
	}

	@Override
	public List<byte[]> zInter(final byte[][] keys, final Aggregate aggregate, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add(aggregate)
				.add(weights);
		final ZParams zParams = new JedisZParams(aggregate, weights);

		return zInter(keys, zParams, args);
	}

	@Override
	public List<Tuple> zInterWithScores(final String... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		final ZParams zParams = new JedisZParams();

		return zInterWithScores(keys, zParams, args);
	}

	@Override
	public List<Tuple> zInterWithScores(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		final ZParams zParams = new JedisZParams();

		return zInterWithScores(keys, zParams, args);
	}

	@Override
	public List<Tuple> zInterWithScores(final String[] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create(keys).add(aggregate);
		final ZParams zParams = new JedisZParams(aggregate);

		return zInterWithScores(keys, zParams, args);
	}

	@Override
	public List<Tuple> zInterWithScores(final byte[][] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create(keys).add(aggregate);
		final ZParams zParams = new JedisZParams(aggregate);

		return zInterWithScores(keys, zParams, args);
	}

	@Override
	public List<Tuple> zInterWithScores(final String[] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add(weights);
		final ZParams zParams = new JedisZParams(weights);

		return zInterWithScores(keys, zParams, args);
	}

	@Override
	public List<Tuple> zInterWithScores(final byte[][] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add(weights);
		final ZParams zParams = new JedisZParams(weights);

		return zInterWithScores(keys, zParams, args);
	}

	@Override
	public List<Tuple> zInterWithScores(final String[] keys, final Aggregate aggregate, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add(aggregate)
				.add(weights);
		final ZParams zParams = new JedisZParams(aggregate, weights);

		return zInterWithScores(keys, zParams, args);
	}

	@Override
	public List<Tuple> zInterWithScores(final byte[][] keys, final Aggregate aggregate, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add(aggregate)
				.add(weights);
		final ZParams zParams = new JedisZParams(aggregate, weights);

		return zInterWithScores(keys, zParams, args);
	}

	@Override
	public Long zInterStore(final String destKey, final String... keys) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZINTERSTORE,
					(cmd)->cmd.zinterstore(destKey, keys), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZINTERSTORE,
					(cmd)->cmd.zinterstore(destKey, keys), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZINTERSTORE, (cmd)->cmd.zinterstore(destKey, keys),
					(v)->v)
					.run(args);
		}
	}

	@Override
	public Long zInterStore(final byte[] destKey, final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZINTERSTORE,
					(cmd)->cmd.zinterstore(destKey, keys), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZINTERSTORE,
					(cmd)->cmd.zinterstore(destKey, keys), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZINTERSTORE, (cmd)->cmd.zinterstore(destKey, keys),
					(v)->v)
					.run(args);
		}
	}

	@Override
	public Long zInterStore(final String destKey, final String[] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys)
				.add(aggregate);
		final ZParams zParams = new JedisZParams(aggregate);

		return zInterStore(destKey, keys, zParams, args);
	}

	@Override
	public Long zInterStore(final byte[] destKey, final byte[][] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys)
				.add(aggregate);
		final ZParams zParams = new JedisZParams(aggregate);

		return zInterStore(destKey, keys, zParams, args);
	}

	@Override
	public Long zInterStore(final String destKey, final String[] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys)
				.add(weights);
		final ZParams zParams = new JedisZParams(weights);

		return zInterStore(destKey, keys, zParams, args);
	}

	@Override
	public Long zInterStore(final byte[] destKey, final byte[][] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys)
				.add(weights);
		final ZParams zParams = new JedisZParams(weights);

		return zInterStore(destKey, keys, zParams, args);
	}

	@Override
	public Long zInterStore(final String destKey, final String[] keys, final Aggregate aggregate,
							final double... weights) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys)
				.add(aggregate).add(weights);
		final ZParams zParams = new JedisZParams(aggregate, weights);

		return zInterStore(destKey, keys, zParams, args);
	}

	@Override
	public Long zInterStore(final byte[] destKey, final byte[][] keys, final Aggregate aggregate,
							final double... weights) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys)
				.add(aggregate).add(weights);
		final ZParams zParams = new JedisZParams(aggregate, weights);

		return zInterStore(destKey, keys, zParams, args);
	}

	@Override
	public Long zLexCount(final String key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);
		final String sMin = Double.toString(min);
		final String sMax = Double.toString(max);

		return zLexCount(key, sMin, sMax, args);
	}

	@Override
	public Long zLexCount(final byte[] key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);
		final byte[] bMin = NumberUtils.double2bytes(min);
		final byte[] bMax = NumberUtils.double2bytes(max);

		return zLexCount(key, bMin, bMax, args);
	}

	@Deprecated
	@Override
	public Long zLexCount(final String key, final String min, final String max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);
		return zLexCount(key, min, max, args);
	}

	@Deprecated
	@Override
	public Long zLexCount(final byte[] key, final byte[] min, final byte[] max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);
		return zLexCount(key, min, max, args);
	}

	@Override
	public List<Double> zMScore(final String key, final String... members) {
		final CommandArguments args = CommandArguments.create(key).add(members);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZMSCORE, (cmd)->cmd.zmscore(key, members), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZMSCORE, (cmd)->cmd.zmscore(key, members),
					(v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZMSCORE, (cmd)->cmd.zmscore(key, members), (v)->v)
					.run(args);
		}
	}

	@Override
	public List<Double> zMScore(final byte[] key, final byte[]... members) {
		final CommandArguments args = CommandArguments.create(key).add(members);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZMSCORE, (cmd)->cmd.zmscore(key, members), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZMSCORE, (cmd)->cmd.zmscore(key, members),
					(v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZMSCORE, (cmd)->cmd.zmscore(key, members), (v)->v)
					.run(args);
		}
	}

	@Override
	public String zRandMember(final String key) {
		final CommandArguments args = CommandArguments.create(key);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZRANDMEMBER, (cmd)->cmd.zrandmember(key), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZRANDMEMBER, (cmd)->cmd.zrandmember(key),
					(v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZRANDMEMBER, (cmd)->cmd.zrandmember(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public byte[] zRandMember(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZRANDMEMBER, (cmd)->cmd.zrandmember(key), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZRANDMEMBER, (cmd)->cmd.zrandmember(key),
					(v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZRANDMEMBER, (cmd)->cmd.zrandmember(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public List<String> zRandMember(final String key, final long count) {
		final CommandArguments args = CommandArguments.create(key).add(count);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZRANDMEMBER, (cmd)->cmd.zrandmember(key, count),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZRANDMEMBER,
					(cmd)->cmd.zrandmember(key, count), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZRANDMEMBER, (cmd)->cmd.zrandmember(key, count), (v)->v)
					.run(args);
		}
	}

	@Override
	public List<byte[]> zRandMember(final byte[] key, final long count) {
		final CommandArguments args = CommandArguments.create(key).add(count);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZRANDMEMBER, (cmd)->cmd.zrandmember(key, count),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZRANDMEMBER,
					(cmd)->cmd.zrandmember(key, count), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZRANDMEMBER, (cmd)->cmd.zrandmember(key, count), (v)->v)
					.run(args);
		}
	}

	@Override
	public List<Tuple> zRandMemberWithScores(final String key, final long count) {
		final CommandArguments args = CommandArguments.create(key).add(count);
		final ListConverter<redis.clients.jedis.resps.Tuple, Tuple> listTupleConverter = TupleConverter.listConverter();

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZRANDMEMBER,
					(cmd)->cmd.zrandmemberWithScores(key, count), listTupleConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZRANDMEMBER,
					(cmd)->cmd.zrandmemberWithScores(key, count), listTupleConverter)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZRANDMEMBER, (cmd)->cmd.zrandmemberWithScores(key, count),
					listTupleConverter)
					.run(args);
		}
	}

	@Override
	public List<Tuple> zRandMemberWithScores(final byte[] key, final long count) {
		final CommandArguments args = CommandArguments.create(key).add(count);
		final ListConverter<redis.clients.jedis.resps.Tuple, Tuple> listTupleConverter = TupleConverter.listConverter();

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZRANDMEMBER,
					(cmd)->cmd.zrandmemberWithScores(key, count), listTupleConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZRANDMEMBER,
					(cmd)->cmd.zrandmemberWithScores(key, count), listTupleConverter)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZRANDMEMBER, (cmd)->cmd.zrandmemberWithScores(key, count),
					listTupleConverter)
					.run(args);
		}
	}

	@Override
	public List<String> zRange(final String key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZRANGE, (cmd)->cmd.zrange(key, start, end),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZRANGE, (cmd)->cmd.zrange(key, start, end),
					(v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZRANGE, (cmd)->cmd.zrange(key, start, end), (v)->v)
					.run(args);
		}
	}

	@Override
	public List<byte[]> zRange(final byte[] key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZRANGE, (cmd)->cmd.zrange(key, start, end),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZRANGE, (cmd)->cmd.zrange(key, start, end),
					(v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZRANGE, (cmd)->cmd.zrange(key, start, end), (v)->v)
					.run(args);
		}
	}

	@Override
	public List<Tuple> zRangeWithScores(final String key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end);
		final ListConverter<redis.clients.jedis.resps.Tuple, Tuple> listTupleConverter = TupleConverter.listConverter();

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZRANGE,
					(cmd)->cmd.zrangeWithScores(key, start, end), listTupleConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZRANGE,
					(cmd)->cmd.zrangeWithScores(key, start, end), listTupleConverter)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZRANGE, (cmd)->cmd.zrangeWithScores(key, start, end),
					listTupleConverter)
					.run(args);
		}
	}

	@Override
	public List<Tuple> zRangeWithScores(final byte[] key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end);
		final ListConverter<redis.clients.jedis.resps.Tuple, Tuple> listTupleConverter = TupleConverter.listConverter();

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZRANGE,
					(cmd)->cmd.zrangeWithScores(key, start, end), listTupleConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZRANGE,
					(cmd)->cmd.zrangeWithScores(key, start, end), listTupleConverter)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZRANGE, (cmd)->cmd.zrangeWithScores(key, start, end),
					listTupleConverter)
					.run(args);
		}
	}

	@Override
	public List<String> zRangeByLex(final String key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);
		final String sMin = Double.toString(min);
		final String sMax = Double.toString(max);

		return zRangeByLex(key, sMin, sMax, args);
	}

	@Override
	public List<byte[]> zRangeByLex(final byte[] key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);
		final byte[] bMin = NumberUtils.double2bytes(min);
		final byte[] bMax = NumberUtils.double2bytes(max);

		return zRangeByLex(key, bMin, bMax, args);
	}

	@Deprecated
	@Override
	public List<String> zRangeByLex(final String key, final String min, final String max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);
		return zRangeByLex(key, min, max, args);
	}

	@Deprecated
	@Override
	public List<byte[]> zRangeByLex(final byte[] key, final byte[] min, final byte[] max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);
		return zRangeByLex(key, min, max, args);
	}

	@Override
	public List<String> zRangeByLex(final String key, final double min, final double max, final long offset,
									final long count) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max)
				.add(offset).add(count);
		final String sMin = Double.toString(min);
		final String sMax = Double.toString(max);

		return zRangeByLex(key, sMin, sMax, offset, count, args);
	}

	@Override
	public List<byte[]> zRangeByLex(final byte[] key, final double min, final double max, final long offset,
									final long count) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max)
				.add(offset).add(count);
		final byte[] bMin = NumberUtils.double2bytes(min);
		final byte[] bMax = NumberUtils.double2bytes(max);

		return zRangeByLex(key, bMin, bMax, offset, count, args);
	}

	@Deprecated
	@Override
	public List<String> zRangeByLex(final String key, final String min, final String max, final long offset,
									final long count) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max)
				.add(offset).add(count);
		return zRangeByLex(key, min, max, offset, count, args);
	}

	@Deprecated
	@Override
	public List<byte[]> zRangeByLex(final byte[] key, final byte[] min, final byte[] max, final long offset,
									final long count) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max)
				.add(offset).add(count);
		return zRangeByLex(key, min, max, offset, count, args);
	}

	@Override
	public List<String> zRangeByScore(final String key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZRANGEBYSCORE,
					(cmd)->cmd.zrangeByScore(key, min, max), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZRANGEBYSCORE,
					(cmd)->cmd.zrangeByScore(key, min, max), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZRANGEBYSCORE,
					(cmd)->cmd.zrangeByScore(key, min, max), (v)->v)
					.run(args);
		}
	}

	@Override
	public List<byte[]> zRangeByScore(final byte[] key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZRANGEBYSCORE,
					(cmd)->cmd.zrangeByScore(key, min, max), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZRANGEBYSCORE,
					(cmd)->cmd.zrangeByScore(key, min, max), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZRANGEBYSCORE,
					(cmd)->cmd.zrangeByScore(key, min, max), (v)->v)
					.run(args);
		}
	}

	@Deprecated
	@Override
	public List<String> zRangeByScore(final String key, final String min, final String max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZRANGEBYSCORE,
					(cmd)->cmd.zrangeByScore(key, min, max), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZRANGEBYSCORE,
					(cmd)->cmd.zrangeByScore(key, min, max), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZRANGEBYSCORE,
					(cmd)->cmd.zrangeByScore(key, min, max), (v)->v)
					.run(args);
		}
	}

	@Deprecated
	@Override
	public List<byte[]> zRangeByScore(final byte[] key, final byte[] min, final byte[] max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZRANGEBYSCORE,
					(cmd)->cmd.zrangeByScore(key, min, max), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZRANGEBYSCORE,
					(cmd)->cmd.zrangeByScore(key, min, max), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZRANGEBYSCORE,
					(cmd)->cmd.zrangeByScore(key, min, max), (v)->v)
					.run(args);
		}
	}

	@Override
	public List<String> zRangeByScore(final String key, final double min, final double max, final long offset,
									  final long count) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max)
				.add(offset).add(count);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZRANGEBYSCORE,
					(cmd)->cmd.zrangeByScore(key, min, max, (int) offset, (int) count), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZRANGEBYSCORE,
					(cmd)->cmd.zrangeByScore(key, min, max, (int) offset, (int) count), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZRANGEBYSCORE,
					(cmd)->cmd.zrangeByScore(key, min, max, (int) offset, (int) count), (v)->v)
					.run(args);
		}
	}

	@Override
	public List<byte[]> zRangeByScore(final byte[] key, final double min, final double max, final long offset,
									  final long count) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max)
				.add(offset).add(count);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZRANGEBYSCORE,
					(cmd)->cmd.zrangeByScore(key, min, max, (int) offset, (int) count), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZRANGEBYSCORE,
					(cmd)->cmd.zrangeByScore(key, min, max, (int) offset, (int) count), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZRANGEBYSCORE,
					(cmd)->cmd.zrangeByScore(key, min, max, (int) offset, (int) count), (v)->v)
					.run(args);
		}
	}

	@Deprecated
	@Override
	public List<String> zRangeByScore(final String key, final String min, final String max, final long offset,
									  final long count) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max)
				.add(offset).add(count);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZRANGEBYSCORE,
					(cmd)->cmd.zrangeByScore(key, min, max, (int) offset, (int) count), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZRANGEBYSCORE,
					(cmd)->cmd.zrangeByScore(key, min, max, (int) offset, (int) count), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZRANGEBYSCORE,
					(cmd)->cmd.zrangeByScore(key, min, max, (int) offset, (int) count), (v)->v)
					.run(args);
		}
	}

	@Deprecated
	@Override
	public List<byte[]> zRangeByScore(final byte[] key, final byte[] min, final byte[] max, final long offset,
									  final long count) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max)
				.add(offset).add(count);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZRANGEBYSCORE,
					(cmd)->cmd.zrangeByScore(key, min, max, (int) offset, (int) count), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZRANGEBYSCORE,
					(cmd)->cmd.zrangeByScore(key, min, max, (int) offset, (int) count), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZRANGEBYSCORE,
					(cmd)->cmd.zrangeByScore(key, min, max, (int) offset, (int) count), (v)->v)
					.run(args);
		}
	}

	@Override
	public List<Tuple> zRangeByScoreWithScores(final String key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);
		final ListConverter<redis.clients.jedis.resps.Tuple, Tuple> listTupleConverter = TupleConverter.listConverter();

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZRANGEBYSCORE,
					(cmd)->cmd.zrangeByScoreWithScores(key, min, max), listTupleConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZRANGEBYSCORE,
					(cmd)->cmd.zrangeByScoreWithScores(key, min, max), listTupleConverter)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZRANGEBYSCORE,
					(cmd)->cmd.zrangeByScoreWithScores(key, min, max), listTupleConverter)
					.run(args);
		}
	}

	@Override
	public List<Tuple> zRangeByScoreWithScores(final byte[] key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);
		final ListConverter<redis.clients.jedis.resps.Tuple, Tuple> listTupleConverter = TupleConverter.listConverter();

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZRANGEBYSCORE,
					(cmd)->cmd.zrangeByScoreWithScores(key, min, max), listTupleConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZRANGEBYSCORE,
					(cmd)->cmd.zrangeByScoreWithScores(key, min, max), listTupleConverter)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZRANGEBYSCORE,
					(cmd)->cmd.zrangeByScoreWithScores(key, min, max), listTupleConverter)
					.run(args);
		}
	}

	@Deprecated
	@Override
	public List<Tuple> zRangeByScoreWithScores(final String key, final String min, final String max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);
		final ListConverter<redis.clients.jedis.resps.Tuple, Tuple> listTupleConverter = TupleConverter.listConverter();

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZRANGEBYSCORE,
					(cmd)->cmd.zrangeByScoreWithScores(key, min, max), listTupleConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZRANGEBYSCORE,
					(cmd)->cmd.zrangeByScoreWithScores(key, min, max), listTupleConverter)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZRANGEBYSCORE,
					(cmd)->cmd.zrangeByScoreWithScores(key, min, max), listTupleConverter)
					.run(args);
		}
	}

	@Deprecated
	@Override
	public List<Tuple> zRangeByScoreWithScores(final byte[] key, final byte[] min, final byte[] max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);
		final ListConverter<redis.clients.jedis.resps.Tuple, Tuple> listTupleConverter = TupleConverter.listConverter();

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZRANGEBYSCORE,
					(cmd)->cmd.zrangeByScoreWithScores(key, min, max), listTupleConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZRANGEBYSCORE,
					(cmd)->cmd.zrangeByScoreWithScores(key, min, max), listTupleConverter)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZRANGEBYSCORE,
					(cmd)->cmd.zrangeByScoreWithScores(key, min, max), listTupleConverter)
					.run(args);
		}
	}

	@Override
	public List<Tuple> zRangeByScoreWithScores(final String key, final double min, final double max, final long offset,
											   final long count) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max)
				.add(offset).add(count);
		final ListConverter<redis.clients.jedis.resps.Tuple, Tuple> listTupleConverter = TupleConverter.listConverter();

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZRANGEBYSCORE,
					(cmd)->cmd.zrangeByScoreWithScores(key, min, max, (int) offset, (int) count), listTupleConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZRANGEBYSCORE,
					(cmd)->cmd.zrangeByScoreWithScores(key, min, max, (int) offset, (int) count), listTupleConverter)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZRANGEBYSCORE,
					(cmd)->cmd.zrangeByScoreWithScores(key, min, max, (int) offset, (int) count), listTupleConverter)
					.run(args);
		}
	}

	@Override
	public List<Tuple> zRangeByScoreWithScores(final byte[] key, final double min, final double max, final long offset,
											   final long count) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max)
				.add(offset).add(count);
		final ListConverter<redis.clients.jedis.resps.Tuple, Tuple> listTupleConverter = TupleConverter.listConverter();

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZRANGEBYSCORE,
					(cmd)->cmd.zrangeByScoreWithScores(key, min, max, (int) offset, (int) count), listTupleConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZRANGEBYSCORE,
					(cmd)->cmd.zrangeByScoreWithScores(key, min, max, (int) offset, (int) count), listTupleConverter)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZRANGEBYSCORE,
					(cmd)->cmd.zrangeByScoreWithScores(key, min, max, (int) offset, (int) count), listTupleConverter)
					.run(args);
		}
	}

	@Deprecated
	@Override
	public List<Tuple> zRangeByScoreWithScores(final String key, final String min, final String max, final long offset,
											   final long count) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max)
				.add(offset).add(count);
		final ListConverter<redis.clients.jedis.resps.Tuple, Tuple> listTupleConverter = TupleConverter.listConverter();

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZRANGEBYSCORE,
					(cmd)->cmd.zrangeByScoreWithScores(key, min, max, (int) offset, (int) count), listTupleConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZRANGEBYSCORE,
					(cmd)->cmd.zrangeByScoreWithScores(key, min, max, (int) offset, (int) count), listTupleConverter)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZRANGEBYSCORE,
					(cmd)->cmd.zrangeByScoreWithScores(key, min, max, (int) offset, (int) count), listTupleConverter)
					.run(args);
		}
	}

	@Deprecated
	@Override
	public List<Tuple> zRangeByScoreWithScores(final byte[] key, final byte[] min, final byte[] max, final long offset,
											   final long count) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max)
				.add(offset).add(count);
		final ListConverter<redis.clients.jedis.resps.Tuple, Tuple> listTupleConverter = TupleConverter.listConverter();

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZRANGEBYSCORE,
					(cmd)->cmd.zrangeByScoreWithScores(key, min, max, (int) offset, (int) count), listTupleConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZRANGEBYSCORE,
					(cmd)->cmd.zrangeByScoreWithScores(key, min, max, (int) offset, (int) count), listTupleConverter)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZRANGEBYSCORE,
					(cmd)->cmd.zrangeByScoreWithScores(key, min, max, (int) offset, (int) count), listTupleConverter)
					.run(args);
		}
	}

	@Override
	public Long zRangeStore(final String destKey, final String key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(destKey).add(key).add(start)
				.add(end);
		final ZRangeParams zRangeParams = new JedisZRangeParams(start, end);

		return zRangeStore(destKey, key, zRangeParams, args);
	}

	@Override
	public Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(destKey).add(key).add(start)
				.add(end);
		final ZRangeParams zRangeParams = new JedisZRangeParams(start, end);

		return zRangeStore(destKey, key, zRangeParams, args);
	}

	@Override
	public Long zRangeStore(final String destKey, final String key, final long start, final long end,
							final ZRangeBy by) {
		final CommandArguments args = CommandArguments.create(destKey).add(key).add(start)
				.add(end).add(by);
		final ZRangeParams zRangeParams = new JedisZRangeParams(by, start, end);

		return zRangeStore(destKey, key, zRangeParams, args);
	}

	@Override
	public Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end,
							final ZRangeBy by) {
		final CommandArguments args = CommandArguments.create(destKey).add(key).add(start)
				.add(end).add(by);
		final ZRangeParams zRangeParams = new JedisZRangeParams(by, start, end);

		return zRangeStore(destKey, key, zRangeParams, args);
	}

	@Override
	public Long zRangeStore(final String destKey, final String key, final long start, final long end,
							final boolean rev) {
		final CommandArguments args = CommandArguments.create(destKey).add(key).add(start)
				.add(end).add(rev);
		final ZRangeParams zRangeParams = new JedisZRangeParams(start, end, rev);

		return zRangeStore(destKey, key, zRangeParams, args);
	}

	@Override
	public Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end,
							final boolean rev) {
		final CommandArguments args = CommandArguments.create(destKey).add(key).add(start)
				.add(end).add(rev);
		final ZRangeParams zRangeParams = new JedisZRangeParams(start, end, rev);

		return zRangeStore(destKey, key, zRangeParams, args);
	}

	@Override
	public Long zRangeStore(final String destKey, final String key, final long start, final long end, final long offset,
							final long count) {
		final CommandArguments args = CommandArguments.create(destKey).add(key).add(start)
				.add(end).add(offset).add(count);
		final ZRangeParams zRangeParams = new JedisZRangeParams(start, end, offset, count);

		return zRangeStore(destKey, key, zRangeParams, args);
	}

	@Override
	public Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end, final long offset,
							final long count) {
		final CommandArguments args = CommandArguments.create(destKey).add(key).add(start)
				.add(end).add(offset).add(count);
		final ZRangeParams zRangeParams = new JedisZRangeParams(start, end, offset, count);

		return zRangeStore(destKey, key, zRangeParams, args);
	}

	@Override
	public Long zRangeStore(final String destKey, final String key, final long start, final long end,
							final ZRangeBy by, final boolean rev) {
		final CommandArguments args = CommandArguments.create(destKey).add(key).add(start)
				.add(end).add(by).add(rev);
		final ZRangeParams zRangeParams = new JedisZRangeParams(by, start, end, rev);

		return zRangeStore(destKey, key, zRangeParams, args);
	}

	@Override
	public Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end,
							final ZRangeBy by, final boolean rev) {
		final CommandArguments args = CommandArguments.create(destKey).add(key).add(start)
				.add(end).add(by).add(rev);
		final ZRangeParams zRangeParams = new JedisZRangeParams(by, start, end, rev);

		return zRangeStore(destKey, key, zRangeParams, args);
	}

	@Override
	public Long zRangeStore(final String destKey, final String key, final long start, final long end,
							final ZRangeBy by, final long offset, final long count) {
		final CommandArguments args = CommandArguments.create(destKey).add(key).add(start)
				.add(end).add(by).add(offset).add(count);
		final ZRangeParams zRangeParams = new JedisZRangeParams(by, start, end, offset, count);

		return zRangeStore(destKey, key, zRangeParams, args);
	}

	@Override
	public Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end,
							final ZRangeBy by, final long offset, final long count) {
		final CommandArguments args = CommandArguments.create(destKey).add(key).add(start)
				.add(end).add(by).add(offset).add(count);
		final ZRangeParams zRangeParams = new JedisZRangeParams(by, start, end, offset, count);

		return zRangeStore(destKey, key, zRangeParams, args);
	}

	@Override
	public Long zRangeStore(final String destKey, final String key, final long start, final long end, final boolean rev,
							final long offset, final long count) {
		final CommandArguments args = CommandArguments.create(destKey).add(key).add(start)
				.add(end).add(rev).add(offset).add(count);
		final ZRangeParams zRangeParams = new JedisZRangeParams(start, end, rev, offset, count);

		return zRangeStore(destKey, key, zRangeParams, args);
	}

	@Override
	public Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end, final boolean rev,
							final long offset, final long count) {
		final CommandArguments args = CommandArguments.create(destKey).add(key).add(start)
				.add(end).add(rev).add(offset).add(count);
		final ZRangeParams zRangeParams = new JedisZRangeParams(start, end, rev, offset, count);

		return zRangeStore(destKey, key, zRangeParams, args);
	}

	@Override
	public Long zRangeStore(final String destKey, final String key, final long start, final long end, final ZRangeBy by,
							final boolean rev, final long offset, final long count) {
		final CommandArguments args = CommandArguments.create(destKey).add(key).add(start)
				.add(end).add(by).add(rev).add(offset).add(count);
		final ZRangeParams zRangeParams = new JedisZRangeParams(by, start, end, rev, offset, count);

		return zRangeStore(destKey, key, zRangeParams, args);
	}

	@Override
	public Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end, final ZRangeBy by,
							final boolean rev, final long offset, final long count) {
		final CommandArguments args = CommandArguments.create(destKey).add(key).add(start)
				.add(end).add(by).add(rev).add(offset).add(count);
		final ZRangeParams zRangeParams = new JedisZRangeParams(by, start, end, rev, offset, count);

		return zRangeStore(destKey, key, zRangeParams, args);
	}

	@Override
	public Long zRank(final String key, final String member) {
		final CommandArguments args = CommandArguments.create(key).add(member);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZRANK, (cmd)->cmd.zrank(key, member), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZRANK, (cmd)->cmd.zrank(key, member), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZRANK, (cmd)->cmd.zrank(key, member), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long zRank(final byte[] key, final byte[] member) {
		final CommandArguments args = CommandArguments.create(key).add(member);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZRANK, (cmd)->cmd.zrank(key, member), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZRANK, (cmd)->cmd.zrank(key, member), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZRANK, (cmd)->cmd.zrank(key, member), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long zRem(final String key, final String... members) {
		final CommandArguments args = CommandArguments.create(key).add(members);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZREM, (cmd)->cmd.zrem(key, members), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZREM, (cmd)->cmd.zrem(key, members), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZREM, (cmd)->cmd.zrem(key, members), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long zRem(final byte[] key, final byte[]... members) {
		final CommandArguments args = CommandArguments.create(key).add(members);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZREM, (cmd)->cmd.zrem(key, members), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZREM, (cmd)->cmd.zrem(key, members), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZREM, (cmd)->cmd.zrem(key, members), (v)->v)
					.run(args);
		}
	}

	@Deprecated
	@Override
	public Long zRemRangeByLex(final String key, final String min, final String max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZREMRANGEBYLEX,
					(cmd)->cmd.zremrangeByLex(key, min, max), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZREMRANGEBYLEX,
					(cmd)->cmd.zremrangeByLex(key, min, max), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZREMRANGEBYLEX, (cmd)->cmd.zremrangeByLex(key, min, max),
					(v)->v)
					.run(args);
		}
	}

	@Deprecated
	@Override
	public Long zRemRangeByLex(final byte[] key, final byte[] min, final byte[] max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZREMRANGEBYLEX,
					(cmd)->cmd.zremrangeByLex(key, min, max), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZREMRANGEBYLEX,
					(cmd)->cmd.zremrangeByLex(key, min, max), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZREMRANGEBYLEX, (cmd)->cmd.zremrangeByLex(key, min, max),
					(v)->v)
					.run(args);
		}
	}

	@Override
	public Long zRemRangeByScore(final String key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZREMRANGEBYSCORE,
					(cmd)->cmd.zremrangeByScore(key, min, max), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZREMRANGEBYSCORE,
					(cmd)->cmd.zremrangeByScore(key, min, max), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZREMRANGEBYSCORE,
					(cmd)->cmd.zremrangeByScore(key, min, max), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long zRemRangeByScore(final byte[] key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZREMRANGEBYSCORE,
					(cmd)->cmd.zremrangeByScore(key, min, max), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZREMRANGEBYSCORE,
					(cmd)->cmd.zremrangeByScore(key, min, max), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZREMRANGEBYSCORE,
					(cmd)->cmd.zremrangeByScore(key, min, max), (v)->v)
					.run(args);
		}
	}

	@Deprecated
	@Override
	public Long zRemRangeByScore(final String key, final String min, final String max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZREMRANGEBYSCORE,
					(cmd)->cmd.zremrangeByScore(key, min, max), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZREMRANGEBYSCORE,
					(cmd)->cmd.zremrangeByScore(key, min, max), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZREMRANGEBYSCORE,
					(cmd)->cmd.zremrangeByScore(key, min, max), (v)->v)
					.run(args);
		}
	}

	@Deprecated
	@Override
	public Long zRemRangeByScore(final byte[] key, final byte[] min, final byte[] max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZREMRANGEBYSCORE,
					(cmd)->cmd.zremrangeByScore(key, min, max), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZREMRANGEBYSCORE,
					(cmd)->cmd.zremrangeByScore(key, min, max), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZREMRANGEBYSCORE,
					(cmd)->cmd.zremrangeByScore(key, min, max), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long zRemRangeByRank(final String key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZREMRANGEBYRANK,
					(cmd)->cmd.zremrangeByRank(key, start, end), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZREMRANGEBYRANK,
					(cmd)->cmd.zremrangeByRank(key, start, end), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZREMRANGEBYRANK,
					(cmd)->cmd.zremrangeByRank(key, start, end), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long zRemRangeByRank(final byte[] key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZREMRANGEBYRANK,
					(cmd)->cmd.zremrangeByRank(key, start, end), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZREMRANGEBYRANK,
					(cmd)->cmd.zremrangeByRank(key, start, end), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZREMRANGEBYRANK,
					(cmd)->cmd.zremrangeByRank(key, start, end), (v)->v)
					.run(args);
		}
	}

	@Override
	public List<String> zRevRange(final String key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZREVRANGE,
					(cmd)->cmd.zrevrange(key, start, end), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZREVRANGE,
					(cmd)->cmd.zrevrange(key, start, end), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZREVRANGE, (cmd)->cmd.zrevrange(key, start, end), (v)->v)
					.run(args);
		}
	}

	@Override
	public List<byte[]> zRevRange(final byte[] key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZREVRANGE,
					(cmd)->cmd.zrevrange(key, start, end), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZREVRANGE,
					(cmd)->cmd.zrevrange(key, start, end), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZREVRANGE, (cmd)->cmd.zrevrange(key, start, end), (v)->v)
					.run(args);
		}
	}

	@Override
	public List<Tuple> zRevRangeWithScores(final String key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end);
		final ListConverter<redis.clients.jedis.resps.Tuple, Tuple> listTupleConverter = TupleConverter.listConverter();

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZREVRANGE,
					(cmd)->cmd.zrevrangeWithScores(key, start, end), listTupleConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZREVRANGE,
					(cmd)->cmd.zrevrangeWithScores(key, start, end), listTupleConverter)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZREVRANGE,
					(cmd)->cmd.zrevrangeWithScores(key, start, end), listTupleConverter)
					.run(args);
		}
	}

	@Override
	public List<Tuple> zRevRangeWithScores(final byte[] key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end);
		final ListConverter<redis.clients.jedis.resps.Tuple, Tuple> listTupleConverter = TupleConverter.listConverter();

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZREVRANGE,
					(cmd)->cmd.zrevrangeWithScores(key, start, end), listTupleConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZREVRANGE,
					(cmd)->cmd.zrevrangeWithScores(key, start, end), listTupleConverter)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZREVRANGE,
					(cmd)->cmd.zrevrangeWithScores(key, start, end), listTupleConverter)
					.run(args);
		}
	}

	@Deprecated
	@Override
	public List<String> zRevRangeByLex(final String key, final String min, final String max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZREVRANGEBYLEX,
					(cmd)->cmd.zrevrangeByLex(key, min, max), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZREVRANGEBYLEX,
					(cmd)->cmd.zrevrangeByLex(key, min, max), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZREVRANGEBYLEX, (cmd)->cmd.zrevrangeByLex(key, min, max),
					(v)->v)
					.run(args);
		}
	}

	@Deprecated
	@Override
	public List<byte[]> zRevRangeByLex(final byte[] key, final byte[] min, final byte[] max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZREVRANGEBYLEX,
					(cmd)->cmd.zrevrangeByLex(key, min, max), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZREVRANGEBYLEX,
					(cmd)->cmd.zrevrangeByLex(key, min, max), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZREVRANGEBYLEX, (cmd)->cmd.zrevrangeByLex(key, min, max),
					(v)->v)
					.run(args);
		}
	}

	@Deprecated
	@Override
	public List<String> zRevRangeByLex(final String key, final String min, final String max, final long offset,
									   final long count) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZREVRANGEBYLEX,
					(cmd)->cmd.zrevrangeByLex(key, min, max, (int) offset, (int) count), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZREVRANGEBYLEX,
					(cmd)->cmd.zrevrangeByLex(key, min, max, (int) offset, (int) count), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZREVRANGEBYLEX,
					(cmd)->cmd.zrevrangeByLex(key, min, max, (int) offset, (int) count), (v)->v)
					.run(args);
		}
	}

	@Deprecated
	@Override
	public List<byte[]> zRevRangeByLex(final byte[] key, final byte[] min, final byte[] max, final long offset,
									   final long count) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZREVRANGEBYLEX,
					(cmd)->cmd.zrevrangeByLex(key, min, max, (int) offset, (int) count), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZREVRANGEBYLEX,
					(cmd)->cmd.zrevrangeByLex(key, min, max, (int) offset, (int) count), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZREVRANGEBYLEX,
					(cmd)->cmd.zrevrangeByLex(key, min, max, (int) offset, (int) count), (v)->v)
					.run(args);
		}
	}

	@Override
	public List<String> zRevRangeByScore(final String key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZREVRANGEBYSCORE,
					(cmd)->cmd.zrevrangeByScore(key, min, max), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZREVRANGEBYSCORE,
					(cmd)->cmd.zrevrangeByScore(key, min, max), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZREVRANGEBYSCORE,
					(cmd)->cmd.zrevrangeByScore(key, min, max), (v)->v)
					.run(args);
		}
	}

	@Override
	public List<byte[]> zRevRangeByScore(final byte[] key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZREVRANGEBYSCORE,
					(cmd)->cmd.zrevrangeByScore(key, min, max), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZREVRANGEBYSCORE,
					(cmd)->cmd.zrevrangeByScore(key, min, max), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZREVRANGEBYSCORE,
					(cmd)->cmd.zrevrangeByScore(key, min, max), (v)->v)
					.run(args);
		}
	}

	@Deprecated
	@Override
	public List<String> zRevRangeByScore(final String key, final String min, final String max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZREVRANGEBYSCORE,
					(cmd)->cmd.zrevrangeByScore(key, min, max), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZREVRANGEBYSCORE,
					(cmd)->cmd.zrevrangeByScore(key, min, max), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZREVRANGEBYSCORE,
					(cmd)->cmd.zrevrangeByScore(key, min, max), (v)->v)
					.run(args);
		}
	}

	@Deprecated
	@Override
	public List<byte[]> zRevRangeByScore(final byte[] key, final byte[] min, final byte[] max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZREVRANGEBYSCORE,
					(cmd)->cmd.zrevrangeByScore(key, min, max), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZREVRANGEBYSCORE,
					(cmd)->cmd.zrevrangeByScore(key, min, max), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZREVRANGEBYSCORE,
					(cmd)->cmd.zrevrangeByScore(key, min, max), (v)->v)
					.run(args);
		}
	}

	@Override
	public List<String> zRevRangeByScore(final String key, final double min, final double max, final long offset,
										 final long count) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max)
				.add(offset).add(count);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZREVRANGEBYSCORE,
					(cmd)->cmd.zrevrangeByScore(key, min, max, (int) offset, (int) count), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZREVRANGEBYSCORE,
					(cmd)->cmd.zrevrangeByScore(key, min, max, (int) offset, (int) count), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZREVRANGEBYSCORE,
					(cmd)->cmd.zrevrangeByScore(key, min, max, (int) offset, (int) count), (v)->v)
					.run(args);
		}
	}

	@Override
	public List<byte[]> zRevRangeByScore(final byte[] key, final double min, final double max, final long offset,
										 final long count) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max)
				.add(offset).add(count);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZREVRANGEBYSCORE,
					(cmd)->cmd.zrevrangeByScore(key, min, max, (int) offset, (int) count), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZREVRANGEBYSCORE,
					(cmd)->cmd.zrevrangeByScore(key, min, max, (int) offset, (int) count), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZREVRANGEBYSCORE,
					(cmd)->cmd.zrevrangeByScore(key, min, max, (int) offset, (int) count), (v)->v)
					.run(args);
		}
	}

	@Deprecated
	@Override
	public List<String> zRevRangeByScore(final String key, final String min, final String max, final long offset,
										 final long count) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max)
				.add(offset).add(count);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZREVRANGEBYSCORE,
					(cmd)->cmd.zrevrangeByScore(key, min, max, (int) offset, (int) count), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZREVRANGEBYSCORE,
					(cmd)->cmd.zrevrangeByScore(key, min, max, (int) offset, (int) count), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZREVRANGEBYSCORE,
					(cmd)->cmd.zrevrangeByScore(key, min, max, (int) offset, (int) count), (v)->v)
					.run(args);
		}
	}

	@Deprecated
	@Override
	public List<byte[]> zRevRangeByScore(final byte[] key, final byte[] min, final byte[] max, final long offset,
										 final long count) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max)
				.add(offset).add(count);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZREVRANGEBYSCORE,
					(cmd)->cmd.zrevrangeByScore(key, min, max, (int) offset, (int) count), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZREVRANGEBYSCORE,
					(cmd)->cmd.zrevrangeByScore(key, min, max, (int) offset, (int) count), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZREVRANGEBYSCORE,
					(cmd)->cmd.zrevrangeByScore(key, min, max, (int) offset, (int) count), (v)->v)
					.run(args);
		}
	}

	@Override
	public List<Tuple> zRevRangeByScoreWithScores(final String key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);
		final ListConverter<redis.clients.jedis.resps.Tuple, Tuple> listTupleConverter = TupleConverter.listConverter();

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZREVRANGEBYSCORE,
					(cmd)->cmd.zrevrangeByScoreWithScores(key, min, max), listTupleConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZREVRANGEBYSCORE,
					(cmd)->cmd.zrevrangeByScoreWithScores(key, min, max), listTupleConverter)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZREVRANGEBYSCORE,
					(cmd)->cmd.zrevrangeByScoreWithScores(key, min, max), listTupleConverter)
					.run(args);
		}
	}

	@Override
	public List<Tuple> zRevRangeByScoreWithScores(final byte[] key, final double min, final double max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);
		final ListConverter<redis.clients.jedis.resps.Tuple, Tuple> listTupleConverter = TupleConverter.listConverter();

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZREVRANGEBYSCORE,
					(cmd)->cmd.zrevrangeByScoreWithScores(key, min, max), listTupleConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZREVRANGEBYSCORE,
					(cmd)->cmd.zrevrangeByScoreWithScores(key, min, max), listTupleConverter)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZREVRANGEBYSCORE,
					(cmd)->cmd.zrevrangeByScoreWithScores(key, min, max), listTupleConverter)
					.run(args);
		}
	}

	@Deprecated
	@Override
	public List<Tuple> zRevRangeByScoreWithScores(final String key, final String min, final String max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);
		final ListConverter<redis.clients.jedis.resps.Tuple, Tuple> listTupleConverter = TupleConverter.listConverter();

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZREVRANGEBYSCORE,
					(cmd)->cmd.zrevrangeByScoreWithScores(key, min, max), listTupleConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZREVRANGEBYSCORE,
					(cmd)->cmd.zrevrangeByScoreWithScores(key, min, max), listTupleConverter)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZREVRANGEBYSCORE,
					(cmd)->cmd.zrevrangeByScoreWithScores(key, min, max), listTupleConverter)
					.run(args);
		}
	}

	@Deprecated
	@Override
	public List<Tuple> zRevRangeByScoreWithScores(final byte[] key, final byte[] min, final byte[] max) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max);
		final ListConverter<redis.clients.jedis.resps.Tuple, Tuple> listTupleConverter = TupleConverter.listConverter();

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZREVRANGEBYSCORE,
					(cmd)->cmd.zrevrangeByScoreWithScores(key, min, max), listTupleConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZREVRANGEBYSCORE,
					(cmd)->cmd.zrevrangeByScoreWithScores(key, min, max), listTupleConverter)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZREVRANGEBYSCORE,
					(cmd)->cmd.zrevrangeByScoreWithScores(key, min, max), listTupleConverter)
					.run(args);
		}
	}

	@Override
	public List<Tuple> zRevRangeByScoreWithScores(final String key, final double min, final double max,
												  final long offset, final long count) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max)
				.add(offset).add(count);
		final ListConverter<redis.clients.jedis.resps.Tuple, Tuple> listTupleConverter = TupleConverter.listConverter();

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZREVRANGEBYSCORE,
					(cmd)->cmd.zrevrangeByScoreWithScores(key, min, max, (int) offset, (int) count), listTupleConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZREVRANGEBYSCORE,
					(cmd)->cmd.zrevrangeByScoreWithScores(key, min, max, (int) offset, (int) count), listTupleConverter)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZREVRANGEBYSCORE,
					(cmd)->cmd.zrevrangeByScoreWithScores(key, min, max, (int) offset, (int) count), listTupleConverter)
					.run(args);
		}
	}

	@Override
	public List<Tuple> zRevRangeByScoreWithScores(final byte[] key, final double min, final double max,
												  final long offset, final long count) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max)
				.add(offset).add(count);
		final ListConverter<redis.clients.jedis.resps.Tuple, Tuple> listTupleConverter = TupleConverter.listConverter();

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZREVRANGEBYSCORE,
					(cmd)->cmd.zrevrangeByScoreWithScores(key, min, max, (int) offset, (int) count), listTupleConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZREVRANGEBYSCORE,
					(cmd)->cmd.zrevrangeByScoreWithScores(key, min, max, (int) offset, (int) count), listTupleConverter)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZREVRANGEBYSCORE,
					(cmd)->cmd.zrevrangeByScoreWithScores(key, min, max, (int) offset, (int) count), listTupleConverter)
					.run(args);
		}
	}

	@Deprecated
	@Override
	public List<Tuple> zRevRangeByScoreWithScores(final String key, final String min, final String max,
												  final long offset, final long count) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max)
				.add(offset).add(count);
		final ListConverter<redis.clients.jedis.resps.Tuple, Tuple> listTupleConverter = TupleConverter.listConverter();

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZREVRANGEBYSCORE,
					(cmd)->cmd.zrevrangeByScoreWithScores(key, min, max, (int) offset, (int) count), listTupleConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZREVRANGEBYSCORE,
					(cmd)->cmd.zrevrangeByScoreWithScores(key, min, max, (int) offset, (int) count), listTupleConverter)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZREVRANGEBYSCORE,
					(cmd)->cmd.zrevrangeByScoreWithScores(key, min, max, (int) offset, (int) count), listTupleConverter)
					.run(args);
		}
	}

	@Deprecated
	@Override
	public List<Tuple> zRevRangeByScoreWithScores(final byte[] key, final byte[] min, final byte[] max,
												  final long offset, final long count) {
		final CommandArguments args = CommandArguments.create(key).add(min).add(max)
				.add(offset).add(count);
		final ListConverter<redis.clients.jedis.resps.Tuple, Tuple> listTupleConverter = TupleConverter.listConverter();

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZREVRANGEBYSCORE,
					(cmd)->cmd.zrevrangeByScoreWithScores(key, min, max, (int) offset, (int) count), listTupleConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZREVRANGEBYSCORE,
					(cmd)->cmd.zrevrangeByScoreWithScores(key, min, max, (int) offset, (int) count), listTupleConverter)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZREVRANGEBYSCORE,
					(cmd)->cmd.zrevrangeByScoreWithScores(key, min, max, (int) offset, (int) count), listTupleConverter)
					.run(args);
		}
	}

	@Override
	public Long zRevRank(final String key, final String member) {
		final CommandArguments args = CommandArguments.create(key).add(member);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZREVRANK, (cmd)->cmd.zrevrank(key, member),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZREVRANK, (cmd)->cmd.zrevrank(key, member),
					(v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZREVRANK, (cmd)->cmd.zrevrank(key, member), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long zRevRank(final byte[] key, final byte[] member) {
		final CommandArguments args = CommandArguments.create(key).add(member);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZREVRANK, (cmd)->cmd.zrevrank(key, member),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZREVRANK, (cmd)->cmd.zrevrank(key, member),
					(v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZREVRANK, (cmd)->cmd.zrevrank(key, member), (v)->v)
					.run(args);
		}
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final String cursor) {
		final CommandArguments args = CommandArguments.create(key).add(cursor);
		final ScanResultConverter.ListTupleScanResultConverter listTupleScanResultConverter =
				new ScanResultConverter.ListTupleScanResultConverter();

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZSCAN, (cmd)->cmd.zscan(key, cursor),
					listTupleScanResultConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZSCAN, (cmd)->cmd.zscan(key, cursor),
					listTupleScanResultConverter)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZSCAN, (cmd)->cmd.zscan(key, cursor),
					listTupleScanResultConverter)
					.run(args);
		}
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor) {
		final CommandArguments args = CommandArguments.create(key).add(cursor);
		final ScanResultConverter.ListTupleScanResultConverter listTupleScanResultConverter =
				new ScanResultConverter.ListTupleScanResultConverter();

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZSCAN, (cmd)->cmd.zscan(key, cursor),
					listTupleScanResultConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZSCAN, (cmd)->cmd.zscan(key, cursor),
					listTupleScanResultConverter)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZSCAN, (cmd)->cmd.zscan(key, cursor),
					listTupleScanResultConverter)
					.run(args);
		}
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final String cursor, final String pattern) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(pattern);
		final ScanParams scanParams = new JedisScanParams(pattern);

		return zScan(key, cursor, scanParams, args);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor, final byte[] pattern) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(pattern);
		final ScanParams scanParams = new JedisScanParams(pattern);

		return zScan(key, cursor, scanParams, args);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final String cursor, final long count) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(count);
		final ScanParams scanParams = new JedisScanParams(count);

		return zScan(key, cursor, scanParams, args);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor, final long count) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(count);
		final ScanParams scanParams = new JedisScanParams(count);

		return zScan(key, cursor, scanParams, args);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final String cursor, final String pattern,
										 final long count) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(pattern)
				.add(count);
		final ScanParams scanParams = new JedisScanParams(pattern, count);

		return zScan(key, cursor, scanParams, args);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor, final byte[] pattern,
										 final long count) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(pattern)
				.add(count);
		final ScanParams scanParams = new JedisScanParams(pattern, count);

		return zScan(key, cursor, scanParams, args);
	}

	@Override
	public Double zScore(final String key, final String member) {
		final CommandArguments args = CommandArguments.create(key).add(member);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZSCORE, (cmd)->cmd.zscore(key, member), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZSCORE, (cmd)->cmd.zscore(key, member), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZSCORE, (cmd)->cmd.zscore(key, member), (v)->v)
					.run(args);
		}
	}

	@Override
	public Double zScore(final byte[] key, final byte[] member) {
		final CommandArguments args = CommandArguments.create(key).add(member);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZSCORE, (cmd)->cmd.zscore(key, member), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZSCORE, (cmd)->cmd.zscore(key, member), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZSCORE, (cmd)->cmd.zscore(key, member), (v)->v)
					.run(args);
		}
	}

	@Override
	public List<String> zUnion(final String... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		final ZParams zParams = new JedisZParams();

		return zUnion(keys, zParams, args);
	}

	@Override
	public List<byte[]> zUnion(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		final ZParams zParams = new JedisZParams();

		return zUnion(keys, zParams, args);
	}

	@Override
	public List<String> zUnion(final String[] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create(keys).add(aggregate);
		final ZParams zParams = new JedisZParams(aggregate);

		return zUnion(keys, zParams, args);
	}

	@Override
	public List<byte[]> zUnion(final byte[][] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create(keys).add(aggregate);
		final ZParams zParams = new JedisZParams(aggregate);

		return zUnion(keys, zParams, args);
	}

	@Override
	public List<String> zUnion(final String[] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add(weights);
		final ZParams zParams = new JedisZParams(weights);

		return zUnion(keys, zParams, args);
	}

	@Override
	public List<byte[]> zUnion(final byte[][] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add(weights);
		final ZParams zParams = new JedisZParams(weights);

		return zUnion(keys, zParams, args);
	}

	@Override
	public List<String> zUnion(final String[] keys, final Aggregate aggregate, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add(aggregate)
				.add(weights);
		final ZParams zParams = new JedisZParams(aggregate, weights);

		return zUnion(keys, zParams, args);
	}

	@Override
	public List<byte[]> zUnion(final byte[][] keys, final Aggregate aggregate, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add(aggregate)
				.add(weights);
		final ZParams zParams = new JedisZParams(aggregate, weights);

		return zUnion(keys, zParams, args);
	}

	@Override
	public List<Tuple> zUnionWithScores(final String... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		final ZParams zParams = new JedisZParams();

		return zUnionWithScores(keys, zParams, args);
	}

	@Override
	public List<Tuple> zUnionWithScores(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		final ZParams zParams = new JedisZParams();

		return zUnionWithScores(keys, zParams, args);
	}

	@Override
	public List<Tuple> zUnionWithScores(final String[] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create(keys).add(aggregate);
		final ZParams zParams = new JedisZParams(aggregate);

		return zUnionWithScores(keys, zParams, args);
	}

	@Override
	public List<Tuple> zUnionWithScores(final byte[][] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create(keys).add(aggregate);
		final ZParams zParams = new JedisZParams(aggregate);

		return zUnionWithScores(keys, zParams, args);
	}

	@Override
	public List<Tuple> zUnionWithScores(final String[] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add(weights);
		final ZParams zParams = new JedisZParams(weights);

		return zUnionWithScores(keys, zParams, args);
	}

	@Override
	public List<Tuple> zUnionWithScores(final byte[][] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add(weights);
		final ZParams zParams = new JedisZParams(weights);

		return zUnionWithScores(keys, zParams, args);
	}

	@Override
	public List<Tuple> zUnionWithScores(final String[] keys, final Aggregate aggregate, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add(aggregate)
				.add(weights);
		final ZParams zParams = new JedisZParams(aggregate, weights);

		return zUnionWithScores(keys, zParams, args);
	}

	@Override
	public List<Tuple> zUnionWithScores(final byte[][] keys, final Aggregate aggregate, final double... weights) {
		final CommandArguments args = CommandArguments.create(keys).add(aggregate)
				.add(weights);
		final ZParams zParams = new JedisZParams(aggregate, weights);

		return zUnionWithScores(keys, zParams, args);
	}

	@Override
	public Long zUnionStore(final String destKey, final String... keys) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZUNIONSTORE,
					(cmd)->cmd.zunionstore(destKey, keys), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZUNIONSTORE,
					(cmd)->cmd.zunionstore(destKey, keys), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZUNIONSTORE, (cmd)->cmd.zunionstore(destKey, keys),
					(v)->v)
					.run(args);
		}
	}

	@Override
	public Long zUnionStore(final byte[] destKey, final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZUNIONSTORE,
					(cmd)->cmd.zunionstore(destKey, keys), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZUNIONSTORE,
					(cmd)->cmd.zunionstore(destKey, keys), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZUNIONSTORE, (cmd)->cmd.zunionstore(destKey, keys),
					(v)->v)
					.run(args);
		}
	}

	@Override
	public Long zUnionStore(final String destKey, final String[] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys)
				.add(aggregate);
		final ZParams zParams = new JedisZParams(aggregate);

		return zUnionStore(destKey, keys, zParams, args);
	}

	@Override
	public Long zUnionStore(final byte[] destKey, final byte[][] keys, final Aggregate aggregate) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys)
				.add(aggregate);
		final ZParams zParams = new JedisZParams(aggregate);

		return zUnionStore(destKey, keys, zParams, args);
	}

	@Override
	public Long zUnionStore(final String destKey, final String[] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys)
				.add(weights);
		final ZParams zParams = new JedisZParams(weights);

		return zUnionStore(destKey, keys, zParams, args);
	}

	@Override
	public Long zUnionStore(final byte[] destKey, final byte[][] keys, final double... weights) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys)
				.add(weights);
		final ZParams zParams = new JedisZParams(weights);

		return zUnionStore(destKey, keys, zParams, args);
	}

	@Override
	public Long zUnionStore(final String destKey, final String[] keys, final Aggregate aggregate,
							final double... weights) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys)
				.add(aggregate).add(weights);
		final ZParams zParams = new JedisZParams(aggregate, weights);

		return zUnionStore(destKey, keys, zParams, args);
	}

	@Override
	public Long zUnionStore(final byte[] destKey, final byte[][] keys, final Aggregate aggregate,
							final double... weights) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys)
				.add(aggregate).add(weights);
		final ZParams zParams = new JedisZParams(aggregate, weights);

		return zUnionStore(destKey, keys, zParams, args);
	}

	private Long zAdd(final String key, final Map<String, Double> members, final ZAddParams zAddParams,
					  final CommandArguments args) {
		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZADD, (cmd)->cmd.zadd(key, members, zAddParams),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZADD,
					(cmd)->cmd.zadd(key, members, zAddParams), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZADD, (cmd)->cmd.zadd(key, members, zAddParams), (v)->v)
					.run(args);
		}
	}

	private Long zAdd(final byte[] key, final Map<byte[], Double> members, final ZAddParams zAddParams,
					  final CommandArguments args) {
		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZADD, (cmd)->cmd.zadd(key, members, zAddParams),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZADD,
					(cmd)->cmd.zadd(key, members, zAddParams), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZADD, (cmd)->cmd.zadd(key, members, zAddParams), (v)->v)
					.run(args);
		}
	}

	private List<String> zInter(final String[] keys, final ZParams zParams, final CommandArguments args) {
		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZINTER, (cmd)->cmd.zinter(zParams, keys), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZINTER, (cmd)->cmd.zinter(zParams, keys),
					(v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZINTER, (cmd)->cmd.zinter(zParams, keys), (v)->v)
					.run(args);
		}
	}

	private List<byte[]> zInter(final byte[][] keys, final ZParams zParams, final CommandArguments args) {
		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZINTER, (cmd)->cmd.zinter(zParams, keys), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZINTER, (cmd)->cmd.zinter(zParams, keys),
					(v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZINTER, (cmd)->cmd.zinter(zParams, keys), (v)->v)
					.run(args);
		}
	}

	private List<Tuple> zInterWithScores(final String[] keys, final ZParams zParams, final CommandArguments args) {
		final ListConverter<redis.clients.jedis.resps.Tuple, Tuple> listConverter = TupleConverter.listConverter();

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZINTER,
					(cmd)->cmd.zinterWithScores(zParams, keys), listConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZINTER,
					(cmd)->cmd.zinterWithScores(zParams, keys), listConverter)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZINTER, (cmd)->cmd.zinterWithScores(zParams, keys),
					listConverter)
					.run(args);
		}
	}

	private List<Tuple> zInterWithScores(final byte[][] keys, final ZParams zParams, final CommandArguments args) {
		final ListConverter<redis.clients.jedis.resps.Tuple, Tuple> listConverter = TupleConverter.listConverter();

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZINTER,
					(cmd)->cmd.zinterWithScores(zParams, keys), listConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZINTER,
					(cmd)->cmd.zinterWithScores(zParams, keys), listConverter)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZINTER, (cmd)->cmd.zinterWithScores(zParams, keys),
					listConverter)
					.run(args);
		}
	}

	private Long zInterStore(final String destKey, final String[] keys, final ZParams zParams,
							 final CommandArguments args) {
		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZINTERSTORE,
					(cmd)->cmd.zinterstore(destKey, zParams, keys), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZINTERSTORE,
					(cmd)->cmd.zinterstore(destKey, zParams, keys), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZINTERSTORE,
					(cmd)->cmd.zinterstore(destKey, zParams, keys), (v)->v)
					.run(args);
		}
	}

	private Long zInterStore(final byte[] destKey, final byte[][] keys, final ZParams zParams,
							 final CommandArguments args) {
		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZINTERSTORE,
					(cmd)->cmd.zinterstore(destKey, zParams, keys), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZINTERSTORE,
					(cmd)->cmd.zinterstore(destKey, zParams, keys), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZINTERSTORE,
					(cmd)->cmd.zinterstore(destKey, zParams, keys), (v)->v)
					.run(args);
		}
	}

	private Long zLexCount(final String key, final String min, final String max, final CommandArguments args) {
		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZLEXCOUNT, (cmd)->cmd.zlexcount(key, min, max),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZLEXCOUNT, (cmd)->cmd.zlexcount(key, min, max),
					(v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZLEXCOUNT, (cmd)->cmd.zlexcount(key, min, max), (v)->v)
					.run(args);
		}
	}

	private Long zLexCount(final byte[] key, final byte[] min, final byte[] max, final CommandArguments args) {
		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZLEXCOUNT, (cmd)->cmd.zlexcount(key, min, max),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZLEXCOUNT, (cmd)->cmd.zlexcount(key, min, max),
					(v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZLEXCOUNT, (cmd)->cmd.zlexcount(key, min, max), (v)->v)
					.run(args);
		}
	}

	private List<String> zRangeByLex(final String key, final String min, final String max,
									 final CommandArguments args) {
		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZRANGEBYLEX,
					(cmd)->cmd.zrangeByLex(key, min, max), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZRANGEBYLEX,
					(cmd)->cmd.zrangeByLex(key, min, max), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZRANGEBYLEX, (cmd)->cmd.zrangeByLex(key, min, max),
					(v)->v)
					.run(args);
		}
	}

	private List<byte[]> zRangeByLex(final byte[] key, final byte[] min, final byte[] max,
									 final CommandArguments args) {
		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZRANGEBYLEX,
					(cmd)->cmd.zrangeByLex(key, min, max), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZRANGEBYLEX,
					(cmd)->cmd.zrangeByLex(key, min, max), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZRANGEBYLEX, (cmd)->cmd.zrangeByLex(key, min, max),
					(v)->v)
					.run(args);
		}
	}

	private List<String> zRangeByLex(final String key, final String min, final String max, final long offset,
									 final long count, final CommandArguments args) {
		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZRANGEBYLEX,
					(cmd)->cmd.zrangeByLex(key, min, max, (int) offset, (int) count), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZRANGEBYLEX,
					(cmd)->cmd.zrangeByLex(key, min, max, (int) offset, (int) count), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZRANGEBYLEX,
					(cmd)->cmd.zrangeByLex(key, min, max, (int) offset, (int) count), (v)->v)
					.run(args);
		}
	}

	private List<byte[]> zRangeByLex(final byte[] key, final byte[] min, final byte[] max, final long offset,
									 final long count, final CommandArguments args) {
		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZRANGEBYLEX,
					(cmd)->cmd.zrangeByLex(key, min, max, (int) offset, (int) count), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZRANGEBYLEX,
					(cmd)->cmd.zrangeByLex(key, min, max, (int) offset, (int) count), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZRANGEBYLEX,
					(cmd)->cmd.zrangeByLex(key, min, max, (int) offset, (int) count), (v)->v)
					.run(args);
		}
	}

	private Long zRangeStore(final String destKey, final String key, final ZRangeParams zRangeParams,
							 final CommandArguments args) {
		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZRANGESTORE,
					(cmd)->cmd.zrangestore(destKey, key, zRangeParams), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZRANGESTORE,
					(cmd)->cmd.zrangestore(destKey, key, zRangeParams), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZRANGESTORE,
					(cmd)->cmd.zrangestore(destKey, key, zRangeParams), (v)->v)
					.run(args);
		}
	}

	private Long zRangeStore(final byte[] destKey, final byte[] key, final ZRangeParams zRangeParams,
							 final CommandArguments args) {
		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZRANGESTORE,
					(cmd)->cmd.zrangestore(destKey, key, zRangeParams), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZRANGESTORE,
					(cmd)->cmd.zrangestore(destKey, key, zRangeParams), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZRANGESTORE,
					(cmd)->cmd.zrangestore(destKey, key, zRangeParams), (v)->v)
					.run(args);
		}
	}

	private ScanResult<List<Tuple>> zScan(final String key, final String cursor, final ScanParams scanParams,
										  final CommandArguments args) {
		final ScanResultConverter.ListTupleScanResultConverter listTupleScanResultConverter =
				new ScanResultConverter.ListTupleScanResultConverter();

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZSCAN, (cmd)->cmd.zscan(key, cursor, scanParams),
					listTupleScanResultConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZSCAN,
					(cmd)->cmd.zscan(key, cursor, scanParams), listTupleScanResultConverter)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZSCAN, (cmd)->cmd.zscan(key, cursor, scanParams),
					listTupleScanResultConverter)
					.run(args);
		}
	}

	private ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor, final ScanParams scanParams,
										  final CommandArguments args) {
		final ScanResultConverter.ListTupleScanResultConverter listTupleScanResultConverter =
				new ScanResultConverter.ListTupleScanResultConverter();

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZSCAN, (cmd)->cmd.zscan(key, cursor, scanParams),
					listTupleScanResultConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZSCAN,
					(cmd)->cmd.zscan(key, cursor, scanParams), listTupleScanResultConverter)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZSCAN, (cmd)->cmd.zscan(key, cursor, scanParams),
					listTupleScanResultConverter)
					.run(args);
		}
	}

	private List<String> zUnion(final String[] keys, final ZParams zParams, final CommandArguments args) {
		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZUNION, (cmd)->cmd.zunion(zParams, keys), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZUNION, (cmd)->cmd.zunion(zParams, keys),
					(v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZUNION, (cmd)->cmd.zunion(zParams, keys), (v)->v)
					.run(args);
		}
	}

	private List<byte[]> zUnion(final byte[][] keys, final ZParams zParams, final CommandArguments args) {
		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZUNION, (cmd)->cmd.zunion(zParams, keys), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZUNION, (cmd)->cmd.zunion(zParams, keys),
					(v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZUNION, (cmd)->cmd.zunion(zParams, keys), (v)->v)
					.run(args);
		}
	}

	private List<Tuple> zUnionWithScores(final String[] keys, final ZParams zParams, final CommandArguments args) {
		final ListConverter<redis.clients.jedis.resps.Tuple, Tuple> listConverter = TupleConverter.listConverter();

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZUNION,
					(cmd)->cmd.zunionWithScores(zParams, keys), listConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZUNION,
					(cmd)->cmd.zunionWithScores(zParams, keys), listConverter)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZUNION, (cmd)->cmd.zunionWithScores(zParams, keys),
					listConverter)
					.run(args);
		}
	}

	private List<Tuple> zUnionWithScores(final byte[][] keys, final ZParams zParams, final CommandArguments args) {
		final ListConverter<redis.clients.jedis.resps.Tuple, Tuple> listConverter = TupleConverter.listConverter();

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZUNION,
					(cmd)->cmd.zunionWithScores(zParams, keys), listConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZUNION,
					(cmd)->cmd.zunionWithScores(zParams, keys), listConverter)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZUNION, (cmd)->cmd.zunionWithScores(zParams, keys),
					listConverter)
					.run(args);
		}
	}

	private Long zUnionStore(final String destKey, final String[] keys, final ZParams zParams,
							 final CommandArguments args) {
		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZUNIONSTORE,
					(cmd)->cmd.zunionstore(destKey, zParams, keys), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZUNIONSTORE,
					(cmd)->cmd.zunionstore(destKey, zParams, keys), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZUNIONSTORE,
					(cmd)->cmd.zunionstore(destKey, zParams, keys), (v)->v)
					.run(args);
		}
	}

	private Long zUnionStore(final byte[] destKey, final byte[][] keys, final ZParams zParams,
							 final CommandArguments args) {
		if(isPipeline()){
			return new JedisPipelineCommand<>(client, ProtocolCommand.ZUNIONSTORE,
					(cmd)->cmd.zunionstore(destKey, zParams, keys), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, ProtocolCommand.ZUNIONSTORE,
					(cmd)->cmd.zunionstore(destKey, zParams, keys), (v)->v)
					.run(args);
		}else{
			return new JedisCommand<>(client, ProtocolCommand.ZUNIONSTORE,
					(cmd)->cmd.zunionstore(destKey, zParams, keys), (v)->v)
					.run(args);
		}
	}

}
