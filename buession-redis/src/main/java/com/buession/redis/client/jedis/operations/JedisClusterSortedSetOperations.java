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
 * | Copyright @ 2013-2022 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.client.jedis.operations;

import com.buession.core.utils.NumberUtils;
import com.buession.redis.client.jedis.JedisClusterClient;
import com.buession.redis.core.Aggregate;
import com.buession.redis.core.GtLt;
import com.buession.redis.core.KeyedZSetElement;
import com.buession.redis.core.NxXx;
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.Tuple;
import com.buession.redis.core.ZRangeBy;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.internal.convert.jedis.response.KeyedZSetElementConverter;
import com.buession.redis.core.internal.convert.jedis.response.ScanResultConverter;
import com.buession.redis.core.internal.convert.jedis.response.TupleConverter;
import com.buession.redis.core.internal.jedis.JedisScanParams;
import com.buession.redis.core.internal.jedis.JedisZAddParams;
import com.buession.redis.core.internal.jedis.JedisZParams;
import com.buession.redis.core.internal.jedis.JedisZRangeParams;
import redis.clients.jedis.params.ZParams;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Jedis 集群模式模式模式有序集合命令操作
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public final class JedisClusterSortedSetOperations extends AbstractSortedSetOperations<JedisClusterClient> {

	public JedisClusterSortedSetOperations(final JedisClusterClient client){
		super(client);
	}

	@Override
	public Tuple zPopMin(final String key){
		final CommandArguments args = CommandArguments.create("key", key);
		final JedisClusterCommand<Tuple> command = new JedisClusterCommand<Tuple>(client, ProtocolCommand.ZPOPMIN)
				.general((cmd)->cmd.zpopmin(key), TupleConverter.INSTANCE)
				.pipeline((cmd)->cmd.zpopmin(key), TupleConverter.INSTANCE)
				.transaction((cmd)->cmd.zpopmin(key), TupleConverter.INSTANCE);
		return execute(command, args);
	}

	@Override
	public Tuple zPopMin(final byte[] key){
		final CommandArguments args = CommandArguments.create("key", key);
		final JedisClusterCommand<Tuple> command = new JedisClusterCommand<Tuple>(client, ProtocolCommand.ZPOPMIN)
				.general((cmd)->cmd.zpopmin(key), TupleConverter.INSTANCE)
				.pipeline((cmd)->cmd.zpopmin(key), TupleConverter.INSTANCE)
				.transaction((cmd)->cmd.zpopmin(key), TupleConverter.INSTANCE);
		return execute(command, args);
	}

	@Override
	public List<Tuple> zPopMin(final String key, final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("count", count);
		final JedisClusterCommand<List<Tuple>> command = new JedisClusterCommand<List<Tuple>>(client,
				ProtocolCommand.ZPOPMIN)
				.general((cmd)->cmd.zpopmin(key, (int) count), TupleConverter.LIST_CONVERTER)
				.pipeline((cmd)->cmd.zpopmin(key, (int) count), TupleConverter.LIST_CONVERTER)
				.transaction((cmd)->cmd.zpopmin(key, (int) count), TupleConverter.LIST_CONVERTER);
		return execute(command, args);
	}

	@Override
	public List<Tuple> zPopMin(final byte[] key, final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("count", count);
		final JedisClusterCommand<List<Tuple>> command = new JedisClusterCommand<List<Tuple>>(client,
				ProtocolCommand.ZPOPMIN)
				.general((cmd)->cmd.zpopmin(key, (int) count), TupleConverter.LIST_CONVERTER)
				.pipeline((cmd)->cmd.zpopmin(key, (int) count), TupleConverter.LIST_CONVERTER)
				.transaction((cmd)->cmd.zpopmin(key, (int) count), TupleConverter.LIST_CONVERTER);
		return execute(command, args);
	}

	@Override
	public Tuple zPopMax(final String key){
		final CommandArguments args = CommandArguments.create("key", key);
		final JedisClusterCommand<Tuple> command = new JedisClusterCommand<Tuple>(client, ProtocolCommand.ZPOPMAX)
				.general((cmd)->cmd.zpopmax(key), TupleConverter.INSTANCE)
				.pipeline((cmd)->cmd.zpopmax(key), TupleConverter.INSTANCE)
				.transaction((cmd)->cmd.zpopmax(key), TupleConverter.INSTANCE);
		return execute(command, args);
	}

	@Override
	public Tuple zPopMax(final byte[] key){
		final CommandArguments args = CommandArguments.create("key", key);
		final JedisClusterCommand<Tuple> command = new JedisClusterCommand<Tuple>(client, ProtocolCommand.ZPOPMAX)
				.general((cmd)->cmd.zpopmax(key), TupleConverter.INSTANCE)
				.pipeline((cmd)->cmd.zpopmax(key), TupleConverter.INSTANCE)
				.transaction((cmd)->cmd.zpopmax(key), TupleConverter.INSTANCE);
		return execute(command, args);
	}

	@Override
	public List<Tuple> zPopMax(final String key, final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("count", count);
		final JedisClusterCommand<List<Tuple>> command = new JedisClusterCommand<List<Tuple>>(client,
				ProtocolCommand.ZPOPMAX)
				.general((cmd)->cmd.zpopmax(key, (int) count), TupleConverter.LIST_CONVERTER)
				.pipeline((cmd)->cmd.zpopmax(key, (int) count), TupleConverter.LIST_CONVERTER)
				.transaction((cmd)->cmd.zpopmax(key, (int) count), TupleConverter.LIST_CONVERTER);
		return execute(command, args);
	}

	@Override
	public List<Tuple> zPopMax(final byte[] key, final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("count", count);
		final JedisClusterCommand<List<Tuple>> command = new JedisClusterCommand<List<Tuple>>(client,
				ProtocolCommand.ZPOPMAX)
				.general((cmd)->cmd.zpopmax(key, (int) count), TupleConverter.LIST_CONVERTER)
				.pipeline((cmd)->cmd.zpopmax(key, (int) count), TupleConverter.LIST_CONVERTER)
				.transaction((cmd)->cmd.zpopmax(key, (int) count), TupleConverter.LIST_CONVERTER);
		return execute(command, args);
	}

	@Override
	public KeyedZSetElement bzPopMin(final String[] keys, final int timeout){
		final CommandArguments args = CommandArguments.create("keys", keys).put("timeout", timeout);
		final JedisClusterCommand<KeyedZSetElement> command = new JedisClusterCommand<KeyedZSetElement>(client,
				ProtocolCommand.BZPOPMIN)
				.general((cmd)->cmd.bzpopmin(timeout, keys), KeyedZSetElementConverter.INSTANCE)
				.pipeline((cmd)->cmd.bzpopmin(timeout, keys), KeyedZSetElementConverter.INSTANCE)
				.transaction((cmd)->cmd.bzpopmin(timeout, keys), KeyedZSetElementConverter.INSTANCE);
		return execute(command, args);
	}

	@Override
	public KeyedZSetElement bzPopMin(final byte[][] keys, final int timeout){
		final CommandArguments args = CommandArguments.create("keys", keys).put("timeout", timeout);
		final JedisClusterCommand<KeyedZSetElement> command = new JedisClusterCommand<KeyedZSetElement>(client,
				ProtocolCommand.BZPOPMIN)
				.general((cmd)->cmd.bzpopmin(timeout, keys),
						KeyedZSetElementConverter.BinaryDataKeyedZSetElementConverter.INSTANCE)
				.pipeline((cmd)->cmd.bzpopmin(timeout, keys),
						KeyedZSetElementConverter.BinaryDataKeyedZSetElementConverter.INSTANCE)
				.transaction((cmd)->cmd.bzpopmin(timeout, keys),
						KeyedZSetElementConverter.BinaryDataKeyedZSetElementConverter.INSTANCE);
		return execute(command, args);
	}

	@Override
	public KeyedZSetElement bzPopMax(final String[] keys, final int timeout){
		final CommandArguments args = CommandArguments.create("keys", keys).put("timeout", timeout);
		final JedisClusterCommand<KeyedZSetElement> command = new JedisClusterCommand<KeyedZSetElement>(client,
				ProtocolCommand.BZPOPMAX)
				.general((cmd)->cmd.bzpopmax(timeout, keys), KeyedZSetElementConverter.INSTANCE)
				.pipeline((cmd)->cmd.bzpopmax(timeout, keys), KeyedZSetElementConverter.INSTANCE)
				.transaction((cmd)->cmd.bzpopmax(timeout, keys), KeyedZSetElementConverter.INSTANCE);
		return execute(command, args);
	}

	@Override
	public KeyedZSetElement bzPopMax(final byte[][] keys, final int timeout){
		final CommandArguments args = CommandArguments.create("keys", keys).put("timeout", timeout);
		final JedisClusterCommand<KeyedZSetElement> command = new JedisClusterCommand<KeyedZSetElement>(client,
				ProtocolCommand.BZPOPMIN)
				.general((cmd)->cmd.bzpopmax(timeout, keys),
						KeyedZSetElementConverter.BinaryDataKeyedZSetElementConverter.INSTANCE)
				.pipeline((cmd)->cmd.bzpopmax(timeout, keys),
						KeyedZSetElementConverter.BinaryDataKeyedZSetElementConverter.INSTANCE)
				.transaction((cmd)->cmd.bzpopmax(timeout, keys),
						KeyedZSetElementConverter.BinaryDataKeyedZSetElementConverter.INSTANCE);
		return execute(command, args);
	}

	@Override
	public long zAdd(final String key, final Map<String, Double> members){
		final CommandArguments args = CommandArguments.create("keys", key).put("members", members);
		final JedisClusterCommand<Long> command = new JedisClusterCommand<Long>(client, ProtocolCommand.ZADD)
				.general((cmd)->cmd.zadd(key, members))
				.pipeline((cmd)->cmd.zadd(key, members))
				.transaction((cmd)->cmd.zadd(key, members));
		return execute(command, args);
	}

	@Override
	public long zAdd(final byte[] key, final Map<byte[], Double> members){
		final CommandArguments args = CommandArguments.create("keys", key).put("members", members);
		final JedisClusterCommand<Long> command = new JedisClusterCommand<Long>(client, ProtocolCommand.ZADD)
				.general((cmd)->cmd.zadd(key, members))
				.pipeline((cmd)->cmd.zadd(key, members))
				.transaction((cmd)->cmd.zadd(key, members));
		return execute(command, args);
	}

	@Override
	public long zAdd(final String key, final Map<String, Double> members, final NxXx nxXx){
		final CommandArguments args = CommandArguments.create("keys", key).put("members", members).put("nxXx", nxXx);
		final JedisZAddParams params = new JedisZAddParams(nxXx);
		final JedisClusterCommand<Long> command = new JedisClusterCommand<Long>(client, ProtocolCommand.ZADD)
				.general((cmd)->cmd.zadd(key, members, params))
				.pipeline((cmd)->cmd.zadd(key, members, params))
				.transaction((cmd)->cmd.zadd(key, members, params));
		return execute(command, args);
	}

	@Override
	public long zAdd(final byte[] key, final Map<byte[], Double> members, final NxXx nxXx){
		final CommandArguments args = CommandArguments.create("keys", key).put("members", members).put("nxXx", nxXx);
		final JedisZAddParams params = new JedisZAddParams(nxXx);
		final JedisClusterCommand<Long> command = new JedisClusterCommand<Long>(client, ProtocolCommand.ZADD)
				.general((cmd)->cmd.zadd(key, members, params))
				.pipeline((cmd)->cmd.zadd(key, members, params))
				.transaction((cmd)->cmd.zadd(key, members, params));
		return execute(command, args);
	}

	@Override
	public long zAdd(final String key, final Map<String, Double> members, final GtLt gtLt){
		final CommandArguments args = CommandArguments.create("keys", key).put("members", members).put("gtLt", gtLt);
		final JedisZAddParams params = new JedisZAddParams(gtLt);
		final JedisClusterCommand<Long> command = new JedisClusterCommand<Long>(client, ProtocolCommand.ZADD)
				.general((cmd)->cmd.zadd(key, members, params))
				.pipeline((cmd)->cmd.zadd(key, members, params))
				.transaction((cmd)->cmd.zadd(key, members, params));
		return execute(command, args);
	}

	@Override
	public long zAdd(final byte[] key, final Map<byte[], Double> members, final GtLt gtLt){
		final CommandArguments args = CommandArguments.create("keys", key).put("members", members).put("gtLt", gtLt);
		final JedisZAddParams params = new JedisZAddParams(gtLt);
		final JedisClusterCommand<Long> command = new JedisClusterCommand<Long>(client, ProtocolCommand.ZADD)
				.general((cmd)->cmd.zadd(key, members, params))
				.pipeline((cmd)->cmd.zadd(key, members, params))
				.transaction((cmd)->cmd.zadd(key, members, params));
		return execute(command, args);
	}

	@Override
	public long zAdd(final String key, final Map<String, Double> members, final boolean ch){
		final CommandArguments args = CommandArguments.create("keys", key).put("members", members).put("ch", ch);
		final JedisZAddParams params = new JedisZAddParams(ch);
		final JedisClusterCommand<Long> command = new JedisClusterCommand<Long>(client, ProtocolCommand.ZADD)
				.general((cmd)->cmd.zadd(key, members, params))
				.pipeline((cmd)->cmd.zadd(key, members, params))
				.transaction((cmd)->cmd.zadd(key, members, params));
		return execute(command, args);
	}

	@Override
	public long zAdd(final byte[] key, final Map<byte[], Double> members, final boolean ch){
		final CommandArguments args = CommandArguments.create("keys", key).put("members", members).put("ch", ch);
		final JedisZAddParams params = new JedisZAddParams(ch);
		final JedisClusterCommand<Long> command = new JedisClusterCommand<Long>(client, ProtocolCommand.ZADD)
				.general((cmd)->cmd.zadd(key, members, params))
				.pipeline((cmd)->cmd.zadd(key, members, params))
				.transaction((cmd)->cmd.zadd(key, members, params));
		return execute(command, args);
	}

	@Override
	public long zAdd(final String key, final Map<String, Double> members, final NxXx nxXx, final GtLt gtLt){
		final CommandArguments args = CommandArguments.create("keys", key).put("members", members).put("nxXx", nxXx)
				.put("gtLt", gtLt);
		final JedisZAddParams params = new JedisZAddParams(nxXx, gtLt);
		final JedisClusterCommand<Long> command = new JedisClusterCommand<Long>(client, ProtocolCommand.ZADD)
				.general((cmd)->cmd.zadd(key, members, params))
				.pipeline((cmd)->cmd.zadd(key, members, params))
				.transaction((cmd)->cmd.zadd(key, members, params));
		return execute(command, args);
	}

	@Override
	public long zAdd(final byte[] key, final Map<byte[], Double> members, final NxXx nxXx, final GtLt gtLt){
		final CommandArguments args = CommandArguments.create("keys", key).put("members", members).put("nxXx", nxXx)
				.put("gtLt", gtLt);
		final JedisZAddParams params = new JedisZAddParams(nxXx, gtLt);
		final JedisClusterCommand<Long> command = new JedisClusterCommand<Long>(client, ProtocolCommand.ZADD)
				.general((cmd)->cmd.zadd(key, members, params))
				.pipeline((cmd)->cmd.zadd(key, members, params))
				.transaction((cmd)->cmd.zadd(key, members, params));
		return execute(command, args);
	}

	@Override
	public long zAdd(final String key, final Map<String, Double> members, final NxXx nxXx, final boolean ch){
		final CommandArguments args = CommandArguments.create("keys", key).put("members", members).put("nxXx", nxXx)
				.put("ch", ch);
		final JedisZAddParams params = new JedisZAddParams(nxXx, ch);
		final JedisClusterCommand<Long> command = new JedisClusterCommand<Long>(client, ProtocolCommand.ZADD)
				.general((cmd)->cmd.zadd(key, members, params))
				.pipeline((cmd)->cmd.zadd(key, members, params))
				.transaction((cmd)->cmd.zadd(key, members, params));
		return execute(command, args);
	}

	@Override
	public long zAdd(final byte[] key, final Map<byte[], Double> members, final NxXx nxXx, final boolean ch){
		final CommandArguments args = CommandArguments.create("keys", key).put("members", members).put("nxXx", nxXx)
				.put("ch", ch);
		final JedisZAddParams params = new JedisZAddParams(nxXx, ch);
		final JedisClusterCommand<Long> command = new JedisClusterCommand<Long>(client, ProtocolCommand.ZADD)
				.general((cmd)->cmd.zadd(key, members, params))
				.pipeline((cmd)->cmd.zadd(key, members, params))
				.transaction((cmd)->cmd.zadd(key, members, params));
		return execute(command, args);
	}

	@Override
	public long zAdd(final String key, final Map<String, Double> members, final GtLt gtLt, final boolean ch){
		final CommandArguments args = CommandArguments.create("keys", key).put("members", members).put("gtLt", gtLt)
				.put("ch", ch);
		final JedisZAddParams params = new JedisZAddParams(gtLt, ch);
		final JedisClusterCommand<Long> command = new JedisClusterCommand<Long>(client, ProtocolCommand.ZADD)
				.general((cmd)->cmd.zadd(key, members, params))
				.pipeline((cmd)->cmd.zadd(key, members, params))
				.transaction((cmd)->cmd.zadd(key, members, params));
		return execute(command, args);
	}

	@Override
	public long zAdd(final byte[] key, final Map<byte[], Double> members, final GtLt gtLt, final boolean ch){
		final CommandArguments args = CommandArguments.create("keys", key).put("members", members).put("gtLt", gtLt)
				.put("ch", ch);
		final JedisZAddParams params = new JedisZAddParams(gtLt, ch);
		final JedisClusterCommand<Long> command = new JedisClusterCommand<Long>(client, ProtocolCommand.ZADD)
				.general((cmd)->cmd.zadd(key, members, params))
				.pipeline((cmd)->cmd.zadd(key, members, params))
				.transaction((cmd)->cmd.zadd(key, members, params));
		return execute(command, args);
	}

	@Override
	public long zAdd(final String key, final Map<String, Double> members, final NxXx nxXx, final GtLt gtLt,
					 final boolean ch){
		final CommandArguments args = CommandArguments.create("keys", key).put("members", members).put("nxXx", nxXx)
				.put("gtLt", gtLt).put("ch", ch);
		final JedisZAddParams params = new JedisZAddParams(nxXx, gtLt, ch);
		final JedisClusterCommand<Long> command = new JedisClusterCommand<Long>(client, ProtocolCommand.ZADD)
				.general((cmd)->cmd.zadd(key, members, params))
				.pipeline((cmd)->cmd.zadd(key, members, params))
				.transaction((cmd)->cmd.zadd(key, members, params));
		return execute(command, args);
	}

	@Override
	public long zAdd(final byte[] key, final Map<byte[], Double> members, final NxXx nxXx, final GtLt gtLt,
					 final boolean ch){
		final CommandArguments args = CommandArguments.create("keys", key).put("members", members).put("nxXx", nxXx)
				.put("gtLt", gtLt).put("ch", ch);
		final JedisZAddParams params = new JedisZAddParams(nxXx, gtLt, ch);
		final JedisClusterCommand<Long> command = new JedisClusterCommand<Long>(client, ProtocolCommand.ZADD)
				.general((cmd)->cmd.zadd(key, members, params))
				.pipeline((cmd)->cmd.zadd(key, members, params))
				.transaction((cmd)->cmd.zadd(key, members, params));
		return execute(command, args);
	}

	@Override
	public long zCard(final String key){
		final CommandArguments args = CommandArguments.create("key", key);
		final JedisClusterCommand<Long> command = new JedisClusterCommand<Long>(client, ProtocolCommand.ZCARD)
				.general((cmd)->cmd.zcard(key))
				.pipeline((cmd)->cmd.zcard(key))
				.transaction((cmd)->cmd.zcard(key));
		return execute(command, args);
	}

	@Override
	public long zCard(final byte[] key){
		final CommandArguments args = CommandArguments.create("key", key);
		final JedisClusterCommand<Long> command = new JedisClusterCommand<Long>(client, ProtocolCommand.ZCARD)
				.general((cmd)->cmd.zcard(key)).
				pipeline((cmd)->cmd.zcard(key))
				.transaction((cmd)->cmd.zcard(key));
		return execute(command, args);
	}

	@Override
	public long zCount(final String key, final double min, final double max){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		final JedisClusterCommand<Long> command = new JedisClusterCommand<Long>(client, ProtocolCommand.ZCOUNT)
				.general((cmd)->cmd.zcount(key, min, max))
				.pipeline((cmd)->cmd.zcount(key, min, max))
				.transaction((cmd)->cmd.zcount(key, min, max));
		return execute(command, args);
	}

	@Override
	public long zCount(final byte[] key, final double min, final double max){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		final JedisClusterCommand<Long> command = new JedisClusterCommand<Long>(client, ProtocolCommand.ZCOUNT)
				.general((cmd)->cmd.zcount(key, min, max))
				.pipeline((cmd)->cmd.zcount(key, min, max))
				.transaction((cmd)->cmd.zcount(key, min, max));
		return execute(command, args);
	}

	@Override
	public long zCount(final String key, final String min, final String max){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		final JedisClusterCommand<Long> command = new JedisClusterCommand<Long>(client, ProtocolCommand.ZCOUNT)
				.general((cmd)->cmd.zcount(key, min, max))
				.pipeline((cmd)->cmd.zcount(key, min, max))
				.transaction((cmd)->cmd.zcount(key, min, max));
		return execute(command, args);
	}

	@Override
	public long zCount(final byte[] key, final byte[] min, final byte[] max){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		final JedisClusterCommand<Long> command = new JedisClusterCommand<Long>(client, ProtocolCommand.ZCOUNT)
				.general((cmd)->cmd.zcount(key, min, max))
				.pipeline((cmd)->cmd.zcount(key, min, max))
				.transaction((cmd)->cmd.zcount(key, min, max));
		return execute(command, args);
	}

	@Override
	public Set<String> zDiff(final String... keys){
		final CommandArguments args = CommandArguments.create("keys", keys);
		final JedisClusterCommand<Set<String>> command = new JedisClusterCommand<Set<String>>(client,
				ProtocolCommand.ZDIFF)
				.general((cmd)->cmd.zdiff(keys))
				.pipeline((cmd)->cmd.zdiff(keys))
				.transaction((cmd)->cmd.zdiff(keys));
		return execute(command, args);
	}

	@Override
	public Set<byte[]> zDiff(final byte[]... keys){
		final CommandArguments args = CommandArguments.create("keys", keys);
		final JedisClusterCommand<Set<byte[]>> command = new JedisClusterCommand<Set<byte[]>>(client,
				ProtocolCommand.ZDIFF)
				.general((cmd)->cmd.zdiff(keys))
				.pipeline((cmd)->cmd.zdiff(keys))
				.transaction((cmd)->cmd.zdiff(keys));
		return execute(command, args);
	}

	@Override
	public Set<Tuple> zDiffWithScores(final String... keys){
		final CommandArguments args = CommandArguments.create("keys", keys);
		final JedisClusterCommand<Set<Tuple>> command = new JedisClusterCommand<Set<Tuple>>(client,
				ProtocolCommand.ZDIFF)
				.general((cmd)->cmd.zdiffWithScores(keys), TupleConverter.SET_CONVERTER)
				.pipeline((cmd)->cmd.zdiffWithScores(keys), TupleConverter.SET_CONVERTER)
				.transaction((cmd)->cmd.zdiffWithScores(keys), TupleConverter.SET_CONVERTER);
		return execute(command, args);
	}

	@Override
	public Set<Tuple> zDiffWithScores(final byte[]... keys){
		final CommandArguments args = CommandArguments.create("keys", keys);
		final JedisClusterCommand<Set<Tuple>> command = new JedisClusterCommand<Set<Tuple>>(client,
				ProtocolCommand.ZDIFF)
				.general((cmd)->cmd.zdiffWithScores(keys), TupleConverter.SET_CONVERTER)
				.pipeline((cmd)->cmd.zdiffWithScores(keys), TupleConverter.SET_CONVERTER)
				.transaction((cmd)->cmd.zdiffWithScores(keys), TupleConverter.SET_CONVERTER);
		return execute(command, args);
	}

	@Override
	public long zDiffStore(final String destKey, final String... keys){
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("keys", keys);
		final JedisClusterCommand<Long> command = new JedisClusterCommand<Long>(client, ProtocolCommand.ZDIFFSTORE)
				.general((cmd)->cmd.zdiffStore(destKey, keys))
				.pipeline((cmd)->cmd.zdiffStore(destKey, keys))
				.transaction((cmd)->cmd.zdiffStore(destKey, keys));
		return execute(command, args);
	}

	@Override
	public long zDiffStore(final byte[] destKey, final byte[]... keys){
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("keys", keys);
		final JedisClusterCommand<Long> command = new JedisClusterCommand<Long>(client, ProtocolCommand.ZDIFFSTORE)
				.general((cmd)->cmd.zdiffStore(destKey, keys))
				.pipeline((cmd)->cmd.zdiffStore(destKey, keys))
				.transaction((cmd)->cmd.zdiffStore(destKey, keys));
		return execute(command, args);
	}

	@Override
	public double zIncrBy(final String key, final double increment, final String member){
		final CommandArguments args = CommandArguments.create("key", key).put("increment", increment)
				.put("member", member);
		final JedisClusterCommand<Double> command = new JedisClusterCommand<Double>(client, ProtocolCommand.ZINCRBY)
				.general((cmd)->cmd.zincrby(key, increment, member))
				.pipeline((cmd)->cmd.zincrby(key, increment, member))
				.transaction((cmd)->cmd.zincrby(key, increment, member));
		return execute(command, args);
	}

	@Override
	public double zIncrBy(final byte[] key, final double increment, final byte[] member){
		final CommandArguments args = CommandArguments.create("key", key).put("increment", increment)
				.put("member", member);
		final JedisClusterCommand<Double> command = new JedisClusterCommand<Double>(client, ProtocolCommand.ZINCRBY)
				.general((cmd)->cmd.zincrby(key, increment, member))
				.pipeline((cmd)->cmd.zincrby(key, increment, member))
				.transaction((cmd)->cmd.zincrby(key, increment, member));
		return execute(command, args);
	}

	@Override
	public Set<String> zInter(final String... keys){
		final CommandArguments args = CommandArguments.create("keys", keys);
		final ZParams params = new JedisZParams();
		final JedisClusterCommand<Set<String>> command = new JedisClusterCommand<Set<String>>(client,
				ProtocolCommand.ZINTER)
				.general((cmd)->cmd.zinter(params, keys))
				.pipeline((cmd)->cmd.zinter(params, keys))
				.transaction((cmd)->cmd.zinter(params, keys));
		return execute(command, args);
	}

	@Override
	public Set<byte[]> zInter(final byte[]... keys){
		final CommandArguments args = CommandArguments.create("keys", keys);
		final ZParams params = new JedisZParams();
		final JedisClusterCommand<Set<byte[]>> command = new JedisClusterCommand<Set<byte[]>>(client,
				ProtocolCommand.ZINTER)
				.general((cmd)->cmd.zinter(params, keys))
				.pipeline((cmd)->cmd.zinter(params, keys))
				.transaction((cmd)->cmd.zinter(params, keys));
		return execute(command, args);
	}

	@Override
	public Set<String> zInter(final String[] keys, final Aggregate aggregate){
		final CommandArguments args = CommandArguments.create("keys", keys).put("aggregate", aggregate);
		final ZParams params = new JedisZParams(aggregate);
		final JedisClusterCommand<Set<String>> command = new JedisClusterCommand<Set<String>>(client,
				ProtocolCommand.ZINTER)
				.general((cmd)->cmd.zinter(params, keys))
				.pipeline((cmd)->cmd.zinter(params, keys))
				.transaction((cmd)->cmd.zinter(params, keys));
		return execute(command, args);
	}

	@Override
	public Set<byte[]> zInter(final byte[][] keys, final Aggregate aggregate){
		final CommandArguments args = CommandArguments.create("keys", keys).put("aggregate", aggregate);
		final ZParams params = new JedisZParams(aggregate);
		final JedisClusterCommand<Set<byte[]>> command = new JedisClusterCommand<Set<byte[]>>(client,
				ProtocolCommand.ZINTER)
				.general((cmd)->cmd.zinter(params, keys))
				.pipeline((cmd)->cmd.zinter(params, keys))
				.transaction((cmd)->cmd.zinter(params, keys));
		return execute(command, args);
	}

	@Override
	public Set<String> zInter(final String[] keys, final double... weights){
		final CommandArguments args = CommandArguments.create("keys", keys).put("weights", weights);
		final ZParams params = new JedisZParams(weights);
		final JedisClusterCommand<Set<String>> command = new JedisClusterCommand<Set<String>>(client,
				ProtocolCommand.ZINTER)
				.general((cmd)->cmd.zinter(params, keys))
				.pipeline((cmd)->cmd.zinter(params, keys))
				.transaction((cmd)->cmd.zinter(params, keys));
		return execute(command, args);
	}

	@Override
	public Set<byte[]> zInter(final byte[][] keys, final double... weights){
		final CommandArguments args = CommandArguments.create("keys", keys).put("weights", weights);
		final ZParams params = new JedisZParams(weights);
		final JedisClusterCommand<Set<byte[]>> command = new JedisClusterCommand<Set<byte[]>>(client,
				ProtocolCommand.ZINTER)
				.general((cmd)->cmd.zinter(params, keys))
				.pipeline((cmd)->cmd.zinter(params, keys))
				.transaction((cmd)->cmd.zinter(params, keys));
		return execute(command, args);
	}

	@Override
	public Set<String> zInter(final String[] keys, final Aggregate aggregate, final double... weights){
		final CommandArguments args = CommandArguments.create("keys", keys).put("aggregate", aggregate)
				.put("weights", weights);
		final ZParams params = new JedisZParams(aggregate, weights);
		final JedisClusterCommand<Set<String>> command = new JedisClusterCommand<Set<String>>(client,
				ProtocolCommand.ZINTER)
				.general((cmd)->cmd.zinter(params, keys))
				.pipeline((cmd)->cmd.zinter(params, keys))
				.transaction((cmd)->cmd.zinter(params, keys));
		return execute(command, args);
	}

	@Override
	public Set<byte[]> zInter(final byte[][] keys, final Aggregate aggregate, final double... weights){
		final CommandArguments args = CommandArguments.create("keys", keys).put("aggregate", aggregate)
				.put("weights", weights);
		final ZParams params = new JedisZParams(aggregate, weights);
		final JedisClusterCommand<Set<byte[]>> command = new JedisClusterCommand<Set<byte[]>>(client,
				ProtocolCommand.ZINTER)
				.general((cmd)->cmd.zinter(params, keys))
				.pipeline((cmd)->cmd.zinter(params, keys))
				.transaction((cmd)->cmd.zinter(params, keys));
		return execute(command, args);
	}

	@Override
	public Set<Tuple> zInterWithScores(final String... keys){
		final CommandArguments args = CommandArguments.create("keys", keys);
		final ZParams params = new JedisZParams();
		final JedisClusterCommand<Set<Tuple>> command = new JedisClusterCommand<Set<Tuple>>(client,
				ProtocolCommand.ZINTER)
				.general((cmd)->cmd.zinterWithScores(params, keys), TupleConverter.SET_CONVERTER)
				.pipeline((cmd)->cmd.zinterWithScores(params, keys), TupleConverter.SET_CONVERTER)
				.transaction((cmd)->cmd.zinterWithScores(params, keys), TupleConverter.SET_CONVERTER);
		return execute(command, args);
	}

	@Override
	public Set<Tuple> zInterWithScores(final byte[]... keys){
		final CommandArguments args = CommandArguments.create("keys", keys);
		final ZParams params = new JedisZParams();
		final JedisClusterCommand<Set<Tuple>> command = new JedisClusterCommand<Set<Tuple>>(client,
				ProtocolCommand.ZINTER)
				.general((cmd)->cmd.zinterWithScores(params, keys), TupleConverter.SET_CONVERTER)
				.pipeline((cmd)->cmd.zinterWithScores(params, keys), TupleConverter.SET_CONVERTER)
				.transaction((cmd)->cmd.zinterWithScores(params, keys), TupleConverter.SET_CONVERTER);
		return execute(command, args);
	}

	@Override
	public Set<Tuple> zInterWithScores(final String[] keys, final Aggregate aggregate){
		final CommandArguments args = CommandArguments.create("keys", keys).put("aggregate", aggregate);
		final ZParams params = new JedisZParams(aggregate);
		final JedisClusterCommand<Set<Tuple>> command = new JedisClusterCommand<Set<Tuple>>(client,
				ProtocolCommand.ZINTER)
				.general((cmd)->cmd.zinterWithScores(params, keys), TupleConverter.SET_CONVERTER)
				.pipeline((cmd)->cmd.zinterWithScores(params, keys), TupleConverter.SET_CONVERTER)
				.transaction((cmd)->cmd.zinterWithScores(params, keys), TupleConverter.SET_CONVERTER);
		return execute(command, args);
	}

	@Override
	public Set<Tuple> zInterWithScores(final byte[][] keys, final Aggregate aggregate){
		final CommandArguments args = CommandArguments.create("keys", keys).put("aggregate", aggregate);
		final ZParams params = new JedisZParams(aggregate);
		final JedisClusterCommand<Set<Tuple>> command = new JedisClusterCommand<Set<Tuple>>(client,
				ProtocolCommand.ZINTER)
				.general((cmd)->cmd.zinterWithScores(params, keys), TupleConverter.SET_CONVERTER)
				.pipeline((cmd)->cmd.zinterWithScores(params, keys), TupleConverter.SET_CONVERTER)
				.transaction((cmd)->cmd.zinterWithScores(params, keys), TupleConverter.SET_CONVERTER);
		return execute(command, args);
	}

	@Override
	public Set<Tuple> zInterWithScores(final String[] keys, final double... weights){
		final CommandArguments args = CommandArguments.create("keys", keys).put("weights", weights);
		final ZParams params = new JedisZParams(weights);
		final JedisClusterCommand<Set<Tuple>> command = new JedisClusterCommand<Set<Tuple>>(client,
				ProtocolCommand.ZINTER)
				.general((cmd)->cmd.zinterWithScores(params, keys), TupleConverter.SET_CONVERTER)
				.pipeline((cmd)->cmd.zinterWithScores(params, keys), TupleConverter.SET_CONVERTER)
				.transaction((cmd)->cmd.zinterWithScores(params, keys), TupleConverter.SET_CONVERTER);
		return execute(command, args);
	}

	@Override
	public Set<Tuple> zInterWithScores(final byte[][] keys, final double... weights){
		final CommandArguments args = CommandArguments.create("keys", keys).put("weights", weights);
		final ZParams params = new JedisZParams(weights);
		final JedisClusterCommand<Set<Tuple>> command = new JedisClusterCommand<Set<Tuple>>(client,
				ProtocolCommand.ZINTER)
				.general((cmd)->cmd.zinterWithScores(params, keys), TupleConverter.SET_CONVERTER)
				.pipeline((cmd)->cmd.zinterWithScores(params, keys), TupleConverter.SET_CONVERTER)
				.transaction((cmd)->cmd.zinterWithScores(params, keys), TupleConverter.SET_CONVERTER);
		return execute(command, args);
	}

	@Override
	public Set<Tuple> zInterWithScores(final String[] keys, final Aggregate aggregate, final double... weights){
		final CommandArguments args = CommandArguments.create("keys", keys).put("aggregate", aggregate)
				.put("weights", weights);
		final ZParams params = new JedisZParams(aggregate, weights);
		final JedisClusterCommand<Set<Tuple>> command = new JedisClusterCommand<Set<Tuple>>(client,
				ProtocolCommand.ZINTER)
				.general((cmd)->cmd.zinterWithScores(params, keys), TupleConverter.SET_CONVERTER)
				.pipeline((cmd)->cmd.zinterWithScores(params, keys), TupleConverter.SET_CONVERTER)
				.transaction((cmd)->cmd.zinterWithScores(params, keys), TupleConverter.SET_CONVERTER);
		return execute(command, args);
	}

	@Override
	public Set<Tuple> zInterWithScores(final byte[][] keys, final Aggregate aggregate, final double... weights){
		final CommandArguments args = CommandArguments.create("keys", keys).put("aggregate", aggregate)
				.put("weights", weights);
		final ZParams params = new JedisZParams(aggregate, weights);
		final JedisClusterCommand<Set<Tuple>> command = new JedisClusterCommand<Set<Tuple>>(client,
				ProtocolCommand.ZINTER)
				.general((cmd)->cmd.zinterWithScores(params, keys), TupleConverter.SET_CONVERTER)
				.pipeline((cmd)->cmd.zinterWithScores(params, keys), TupleConverter.SET_CONVERTER)
				.transaction((cmd)->cmd.zinterWithScores(params, keys), TupleConverter.SET_CONVERTER);
		return execute(command, args);
	}

	@Override
	public long zInterStore(final String destKey, final String... keys){
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("keys", keys);
		final JedisClusterCommand<Long> command = new JedisClusterCommand<Long>(client, ProtocolCommand.ZINTERSTORE)
				.general((cmd)->cmd.zinterstore(destKey, keys))
				.pipeline((cmd)->cmd.zinterstore(destKey, keys))
				.transaction((cmd)->cmd.zinterstore(destKey, keys));
		return execute(command, args);
	}

	@Override
	public long zInterStore(final byte[] destKey, final byte[]... keys){
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("keys", keys);
		final JedisClusterCommand<Long> command = new JedisClusterCommand<Long>(client, ProtocolCommand.ZINTERSTORE)
				.general((cmd)->cmd.zinterstore(destKey, keys))
				.pipeline((cmd)->cmd.zinterstore(destKey, keys))
				.transaction((cmd)->cmd.zinterstore(destKey, keys));
		return execute(command, args);
	}

	@Override
	public long zInterStore(final String destKey, final String[] keys, final Aggregate aggregate){
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("keys", keys)
				.put("aggregate", aggregate);
		final JedisZParams params = new JedisZParams(aggregate);
		final JedisClusterCommand<Long> command = new JedisClusterCommand<Long>(client, ProtocolCommand.ZINTERSTORE)
				.general((cmd)->cmd.zinterstore(destKey, params, keys))
				.pipeline((cmd)->cmd.zinterstore(destKey, params, keys))
				.transaction((cmd)->cmd.zinterstore(destKey, params, keys));
		return execute(command, args);
	}

	@Override
	public long zInterStore(final byte[] destKey, final byte[][] keys, final Aggregate aggregate){
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("keys", keys)
				.put("aggregate", aggregate);
		final JedisZParams params = new JedisZParams(aggregate);
		final JedisClusterCommand<Long> command = new JedisClusterCommand<Long>(client, ProtocolCommand.ZINTERSTORE)
				.general((cmd)->cmd.zinterstore(destKey, params, keys))
				.pipeline((cmd)->cmd.zinterstore(destKey, params, keys))
				.transaction((cmd)->cmd.zinterstore(destKey, params, keys));
		return execute(command, args);
	}

	@Override
	public long zInterStore(final String destKey, final String[] keys, final double... weights){
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("keys", keys)
				.put("weights", weights);
		final JedisZParams params = new JedisZParams(weights);
		final JedisClusterCommand<Long> command = new JedisClusterCommand<Long>(client, ProtocolCommand.ZINTERSTORE)
				.general((cmd)->cmd.zinterstore(destKey, params, keys))
				.pipeline((cmd)->cmd.zinterstore(destKey, params, keys))
				.transaction((cmd)->cmd.zinterstore(destKey, params, keys));
		return execute(command, args);
	}

	@Override
	public long zInterStore(final byte[] destKey, final byte[][] keys, final double... weights){
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("keys", keys)
				.put("weights", weights);
		final JedisZParams params = new JedisZParams(weights);
		final JedisClusterCommand<Long> command = new JedisClusterCommand<Long>(client, ProtocolCommand.ZINTERSTORE)
				.general((cmd)->cmd.zinterstore(destKey, params, keys))
				.pipeline((cmd)->cmd.zinterstore(destKey, params, keys))
				.transaction((cmd)->cmd.zinterstore(destKey, params, keys));
		return execute(command, args);
	}

	@Override
	public long zInterStore(final String destKey, final String[] keys, final Aggregate aggregate,
							final double... weights){
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("keys", keys)
				.put("aggregate", aggregate).put("weights", weights);
		final JedisZParams params = new JedisZParams(aggregate, weights);
		final JedisClusterCommand<Long> command = new JedisClusterCommand<Long>(client, ProtocolCommand.ZINTERSTORE)
				.general((cmd)->cmd.zinterstore(destKey, params, keys))
				.pipeline((cmd)->cmd.zinterstore(destKey, params, keys))
				.transaction((cmd)->cmd.zinterstore(destKey, params, keys));
		return execute(command, args);
	}

	@Override
	public long zInterStore(final byte[] destKey, final byte[][] keys, final Aggregate aggregate,
							final double... weights){
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("keys", keys)
				.put("aggregate", aggregate).put("weights", weights);
		final JedisZParams params = new JedisZParams(aggregate, weights);
		final JedisClusterCommand<Long> command = new JedisClusterCommand<Long>(client, ProtocolCommand.ZINTERSTORE)
				.general((cmd)->cmd.zinterstore(destKey, params, keys))
				.pipeline((cmd)->cmd.zinterstore(destKey, params, keys))
				.transaction((cmd)->cmd.zinterstore(destKey, params, keys));
		return execute(command, args);
	}

	@Override
	public long zLexCount(final String key, final double min, final double max){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		final JedisClusterCommand<Long> command = new JedisClusterCommand<Long>(client, ProtocolCommand.ZLEXCOUNT)
				.general((cmd)->cmd.zlexcount(key, Double.toString(min), Double.toString(max)))
				.pipeline((cmd)->cmd.zlexcount(key, Double.toString(min), Double.toString(max)))
				.transaction((cmd)->cmd.zlexcount(key, Double.toString(min), Double.toString(max)));
		return execute(command, args);
	}

	@Override
	public long zLexCount(final byte[] key, final double min, final double max){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		final JedisClusterCommand<Long> command = new JedisClusterCommand<Long>(client, ProtocolCommand.ZLEXCOUNT)
				.general((cmd)->cmd.zlexcount(key, NumberUtils.double2bytes(min), NumberUtils.double2bytes(max)))
				.pipeline((cmd)->cmd.zlexcount(key, NumberUtils.double2bytes(min), NumberUtils.double2bytes(max)))
				.transaction((cmd)->cmd.zlexcount(key, NumberUtils.double2bytes(min), NumberUtils.double2bytes(max)));
		return execute(command, args);
	}

	@Override
	public long zLexCount(final String key, final String min, final String max){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		final JedisClusterCommand<Long> command = new JedisClusterCommand<Long>(client, ProtocolCommand.ZLEXCOUNT)
				.general((cmd)->cmd.zlexcount(key, min, max))
				.pipeline((cmd)->cmd.zlexcount(key, min, max))
				.transaction((cmd)->cmd.zlexcount(key, min, max));
		return execute(command, args);
	}

	@Override
	public long zLexCount(final byte[] key, final byte[] min, final byte[] max){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		final JedisClusterCommand<Long> command = new JedisClusterCommand<Long>(client, ProtocolCommand.ZLEXCOUNT)
				.general((cmd)->cmd.zlexcount(key, min, max))
				.pipeline((cmd)->cmd.zlexcount(key, min, max))
				.transaction((cmd)->cmd.zlexcount(key, min, max));
		return execute(command, args);
	}

	@Override
	public List<Double> zMScore(final String key, final String... members){
		final CommandArguments args = CommandArguments.create("key", key).put("members", members);
		final JedisClusterCommand<List<Double>> command = new JedisClusterCommand<List<Double>>(client,
				ProtocolCommand.ZMSCORE)
				.general((cmd)->cmd.zmscore(key, members))
				.pipeline((cmd)->cmd.zmscore(key, members))
				.transaction((cmd)->cmd.zmscore(key, members));
		return execute(command, args);
	}

	@Override
	public List<Double> zMScore(final byte[] key, final byte[]... members){
		final CommandArguments args = CommandArguments.create("key", key).put("members", members);
		final JedisClusterCommand<List<Double>> command = new JedisClusterCommand<List<Double>>(client,
				ProtocolCommand.ZMSCORE)
				.general((cmd)->cmd.zmscore(key, members))
				.pipeline((cmd)->cmd.zmscore(key, members))
				.transaction((cmd)->cmd.zmscore(key, members));
		return execute(command, args);
	}

	@Override
	public String zRandMember(final String key){
		final CommandArguments args = CommandArguments.create("key", key);
		final JedisClusterCommand<String> command = new JedisClusterCommand<String>(client, ProtocolCommand.ZRANDMEMBER)
				.general((cmd)->cmd.zrandmember(key))
				.pipeline((cmd)->cmd.zrandmember(key))
				.transaction((cmd)->cmd.zrandmember(key));
		return execute(command, args);
	}

	@Override
	public byte[] zRandMember(final byte[] key){
		final CommandArguments args = CommandArguments.create("key", key);
		final JedisClusterCommand<byte[]> command = new JedisClusterCommand<byte[]>(client, ProtocolCommand.ZRANDMEMBER)
				.general((cmd)->cmd.zrandmember(key))
				.pipeline((cmd)->cmd.zrandmember(key))
				.transaction((cmd)->cmd.zrandmember(key));
		return execute(command, args);
	}

	@Override
	public List<String> zRandMember(final String key, final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("count", count);
		final JedisClusterCommand<List<String>> command = new JedisClusterCommand<List<String>>(client,
				ProtocolCommand.ZRANDMEMBER)
				.general((cmd)->cmd.zrandmember(key, count))
				.pipeline((cmd)->cmd.zrandmember(key, count))
				.transaction((cmd)->cmd.zrandmember(key, count));
		return execute(command, args);
	}

	@Override
	public List<byte[]> zRandMember(final byte[] key, final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("count", count);
		final JedisClusterCommand<List<byte[]>> command = new JedisClusterCommand<List<byte[]>>(client,
				ProtocolCommand.ZRANDMEMBER)
				.general((cmd)->cmd.zrandmember(key, count))
				.pipeline((cmd)->cmd.zrandmember(key, count))
				.transaction((cmd)->cmd.zrandmember(key, count));
		return execute(command, args);
	}

	@Override
	public List<Tuple> zRandMemberWithScores(final String key, final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("count", count);
		final JedisClusterCommand<List<Tuple>> command = new JedisClusterCommand<List<Tuple>>(client,
				ProtocolCommand.ZRANDMEMBER)
				.general((cmd)->cmd.zrandmemberWithScores(key, count), TupleConverter.LIST_CONVERTER)
				.pipeline((cmd)->cmd.zrandmemberWithScores(key, count), TupleConverter.LIST_CONVERTER)
				.transaction((cmd)->cmd.zrandmemberWithScores(key, count), TupleConverter.LIST_CONVERTER);
		return execute(command, args);
	}

	@Override
	public List<Tuple> zRandMemberWithScores(final byte[] key, final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("count", count);
		final JedisClusterCommand<List<Tuple>> command = new JedisClusterCommand<List<Tuple>>(client,
				ProtocolCommand.ZRANDMEMBER)
				.general((cmd)->cmd.zrandmemberWithScores(key, count), TupleConverter.LIST_CONVERTER)
				.pipeline((cmd)->cmd.zrandmemberWithScores(key, count), TupleConverter.LIST_CONVERTER)
				.transaction((cmd)->cmd.zrandmemberWithScores(key, count), TupleConverter.LIST_CONVERTER);
		return execute(command, args);
	}

	@Override
	public List<String> zRange(final String key, final long start, final long end){
		final CommandArguments args = CommandArguments.create("key", key).put("start", start).put("end", end);
		final JedisClusterCommand<List<String>> command = new JedisClusterCommand<List<String>>(client,
				ProtocolCommand.ZRANGE)
				.general((cmd)->cmd.zrange(key, start, end))
				.pipeline((cmd)->cmd.zrange(key, start, end))
				.transaction((cmd)->cmd.zrange(key, start, end));
		return execute(command, args);
	}

	@Override
	public List<byte[]> zRange(final byte[] key, final long start, final long end){
		final CommandArguments args = CommandArguments.create("key", key).put("start", start).put("end", end);
		final JedisClusterCommand<List<byte[]>> command = new JedisClusterCommand<List<byte[]>>(client,
				ProtocolCommand.ZRANGE)
				.general((cmd)->cmd.zrange(key, start, end))
				.pipeline((cmd)->cmd.zrange(key, start, end))
				.transaction((cmd)->cmd.zrange(key, start, end));
		return execute(command, args);
	}

	@Override
	public List<Tuple> zRangeWithScores(final String key, final long start, final long end){
		final CommandArguments args = CommandArguments.create("key", key).put("start", start).put("end", end);
		final JedisClusterCommand<List<Tuple>> command = new JedisClusterCommand<List<Tuple>>(client,
				ProtocolCommand.ZRANGE)
				.general((cmd)->cmd.zrangeWithScores(key, start, end), TupleConverter.LIST_CONVERTER)
				.pipeline((cmd)->cmd.zrangeWithScores(key, start, end), TupleConverter.LIST_CONVERTER)
				.transaction((cmd)->cmd.zrangeWithScores(key, start, end), TupleConverter.LIST_CONVERTER);
		return execute(command, args);
	}

	@Override
	public List<Tuple> zRangeWithScores(final byte[] key, final long start, final long end){
		final CommandArguments args = CommandArguments.create("key", key).put("start", start).put("end", end);
		final JedisClusterCommand<List<Tuple>> command = new JedisClusterCommand<List<Tuple>>(client,
				ProtocolCommand.ZRANGE)
				.general((cmd)->cmd.zrangeWithScores(key, start, end), TupleConverter.LIST_CONVERTER)
				.pipeline((cmd)->cmd.zrangeWithScores(key, start, end), TupleConverter.LIST_CONVERTER)
				.transaction((cmd)->cmd.zrangeWithScores(key, start, end), TupleConverter.LIST_CONVERTER);
		return execute(command, args);
	}

	@Override
	public List<String> zRangeByLex(final String key, final double min, final double max){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		final JedisClusterCommand<List<String>> command = new JedisClusterCommand<List<String>>(client,
				ProtocolCommand.ZRANGEBYLEX)
				.general((cmd)->cmd.zrangeByLex(key, Double.toString(min), Double.toString(max)))
				.pipeline((cmd)->cmd.zrangeByLex(key, Double.toString(min), Double.toString(max)))
				.transaction((cmd)->cmd.zrangeByLex(key, Double.toString(min), Double.toString(max)));
		return execute(command, args);
	}

	@Override
	public List<byte[]> zRangeByLex(final byte[] key, final double min, final double max){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		final JedisClusterCommand<List<byte[]>> command = new JedisClusterCommand<List<byte[]>>(client,
				ProtocolCommand.ZRANGEBYLEX)
				.general((cmd)->cmd.zrangeByLex(key, NumberUtils.double2bytes(min), NumberUtils.double2bytes(max)))
				.pipeline((cmd)->cmd.zrangeByLex(key, NumberUtils.double2bytes(min), NumberUtils.double2bytes(max)))
				.transaction((cmd)->cmd.zrangeByLex(key, NumberUtils.double2bytes(min), NumberUtils.double2bytes(max)));
		return execute(command, args);
	}

	@Override
	public List<String> zRangeByLex(final String key, final String min, final String max){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		final JedisClusterCommand<List<String>> command = new JedisClusterCommand<List<String>>(client,
				ProtocolCommand.ZRANGEBYLEX).general((cmd)->cmd.zrangeByLex(key, min, max))
				.pipeline((cmd)->cmd.zrangeByLex(key, min, max)).transaction((cmd)->cmd.zrangeByLex(key, min, max));
		return execute(command, args);
	}

	@Override
	public List<byte[]> zRangeByLex(final byte[] key, final byte[] min, final byte[] max){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		final JedisClusterCommand<List<byte[]>> command = new JedisClusterCommand<List<byte[]>>(client,
				ProtocolCommand.ZRANGEBYLEX)
				.general((cmd)->cmd.zrangeByLex(key, min, max))
				.pipeline((cmd)->cmd.zrangeByLex(key, min, max))
				.transaction((cmd)->cmd.zrangeByLex(key, min, max));
		return execute(command, args);
	}

	@Override
	public List<String> zRangeByLex(final String key, final double min, final double max, final long offset,
									final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max)
				.put("offset", offset).put("count", count);
		final JedisClusterCommand<List<String>> command = new JedisClusterCommand<List<String>>(client,
				ProtocolCommand.ZRANGEBYLEX)
				.general((cmd)->cmd.zrangeByLex(key, Double.toString(min), Double.toString(max), (int) offset,
						(int) count))
				.pipeline((cmd)->cmd.zrangeByLex(key, Double.toString(min), Double.toString(max), (int) offset,
						(int) count))
				.transaction((cmd)->cmd.zrangeByLex(key, Double.toString(min), Double.toString(max), (int) offset,
						(int) count));
		return execute(command, args);
	}

	@Override
	public List<byte[]> zRangeByLex(final byte[] key, final double min, final double max, final long offset,
									final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max)
				.put("offset", offset).put("count", count);
		final JedisClusterCommand<List<byte[]>> command = new JedisClusterCommand<List<byte[]>>(client,
				ProtocolCommand.ZRANGEBYLEX)
				.general((cmd)->cmd.zrangeByLex(key, NumberUtils.double2bytes(min), NumberUtils.double2bytes(max),
						(int) offset, (int) count))
				.pipeline((cmd)->cmd.zrangeByLex(key, NumberUtils.double2bytes(min), NumberUtils.double2bytes(max),
						(int) offset, (int) count))
				.transaction((cmd)->cmd.zrangeByLex(key, NumberUtils.double2bytes(min), NumberUtils.double2bytes(max),
						(int) offset, (int) count));
		return execute(command, args);
	}

	@Override
	public List<String> zRangeByLex(final String key, final String min, final String max, final long offset,
									final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max)
				.put("offset", offset).put("count", count);
		final JedisClusterCommand<List<String>> command = new JedisClusterCommand<List<String>>(client,
				ProtocolCommand.ZRANGEBYLEX)
				.general((cmd)->cmd.zrangeByLex(key, min, max, (int) offset, (int) count))
				.pipeline((cmd)->cmd.zrangeByLex(key, min, max, (int) offset, (int) count))
				.transaction((cmd)->cmd.zrangeByLex(key, min, max, (int) offset, (int) count));
		return execute(command, args);
	}

	@Override
	public List<byte[]> zRangeByLex(final byte[] key, final byte[] min, final byte[] max, final long offset,
									final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max)
				.put("offset", offset).put("count", count);
		final JedisClusterCommand<List<byte[]>> command = new JedisClusterCommand<List<byte[]>>(client,
				ProtocolCommand.ZRANGEBYLEX)
				.general((cmd)->cmd.zrangeByLex(key, min, max, (int) offset, (int) count))
				.pipeline((cmd)->cmd.zrangeByLex(key, min, max, (int) offset, (int) count))
				.transaction((cmd)->cmd.zrangeByLex(key, min, max, (int) offset, (int) count));
		return execute(command, args);
	}

	@Override
	public List<String> zRangeByScore(final String key, final double min, final double max){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		final JedisClusterCommand<List<String>> command = new JedisClusterCommand<List<String>>(client,
				ProtocolCommand.ZRANGEBYSCORE)
				.general((cmd)->cmd.zrangeByScore(key, min, max))
				.pipeline((cmd)->cmd.zrangeByScore(key, min, max))
				.transaction((cmd)->cmd.zrangeByScore(key, min, max));
		return execute(command, args);
	}

	@Override
	public List<byte[]> zRangeByScore(final byte[] key, final double min, final double max){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		final JedisClusterCommand<List<byte[]>> command = new JedisClusterCommand<List<byte[]>>(client,
				ProtocolCommand.ZRANGEBYSCORE)
				.general((cmd)->cmd.zrangeByScore(key, min, max))
				.pipeline((cmd)->cmd.zrangeByScore(key, min, max))
				.transaction((cmd)->cmd.zrangeByScore(key, min, max));
		return execute(command, args);
	}

	@Override
	public List<String> zRangeByScore(final String key, final String min, final String max){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		final JedisClusterCommand<List<String>> command = new JedisClusterCommand<List<String>>(client,
				ProtocolCommand.ZRANGEBYSCORE)
				.general((cmd)->cmd.zrangeByScore(key, min, max))
				.pipeline((cmd)->cmd.zrangeByScore(key, min, max))
				.transaction((cmd)->cmd.zrangeByScore(key, min, max));
		return execute(command, args);
	}

	@Override
	public List<byte[]> zRangeByScore(final byte[] key, final byte[] min, final byte[] max){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		final JedisClusterCommand<List<byte[]>> command = new JedisClusterCommand<List<byte[]>>(client,
				ProtocolCommand.ZRANGEBYSCORE)
				.general((cmd)->cmd.zrangeByScore(key, min, max))
				.pipeline((cmd)->cmd.zrangeByScore(key, min, max))
				.transaction((cmd)->cmd.zrangeByScore(key, min, max));
		return execute(command, args);
	}

	@Override
	public List<String> zRangeByScore(final String key, final double min, final double max, final long offset,
									  final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max)
				.put("offset", offset).put("count", count);
		final JedisClusterCommand<List<String>> command = new JedisClusterCommand<List<String>>(client,
				ProtocolCommand.ZRANGEBYSCORE)
				.general((cmd)->cmd.zrangeByScore(key, min, max, (int) offset, (int) count))
				.pipeline((cmd)->cmd.zrangeByScore(key, min, max, (int) offset, (int) count))
				.transaction((cmd)->cmd.zrangeByScore(key, min, max, (int) offset, (int) count));
		return execute(command, args);
	}

	@Override
	public List<byte[]> zRangeByScore(final byte[] key, final double min, final double max, final long offset,
									  final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max)
				.put("offset", offset).put("count", count);
		final JedisClusterCommand<List<byte[]>> command = new JedisClusterCommand<List<byte[]>>(client,
				ProtocolCommand.ZRANGEBYSCORE)
				.general((cmd)->cmd.zrangeByScore(key, min, max, (int) offset, (int) count))
				.pipeline((cmd)->cmd.zrangeByScore(key, min, max, (int) offset, (int) count))
				.transaction((cmd)->cmd.zrangeByScore(key, min, max, (int) offset, (int) count));
		return execute(command, args);
	}

	@Override
	public List<String> zRangeByScore(final String key, final String min, final String max, final long offset,
									  final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max)
				.put("offset", offset).put("count", count);
		final JedisClusterCommand<List<String>> command = new JedisClusterCommand<List<String>>(client,
				ProtocolCommand.ZRANGEBYSCORE)
				.general((cmd)->cmd.zrangeByScore(key, min, max, (int) offset, (int) count))
				.pipeline((cmd)->cmd.zrangeByScore(key, min, max, (int) offset, (int) count))
				.transaction((cmd)->cmd.zrangeByScore(key, min, max, (int) offset, (int) count));
		return execute(command, args);
	}

	@Override
	public List<byte[]> zRangeByScore(final byte[] key, final byte[] min, final byte[] max, final long offset,
									  final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max)
				.put("offset", offset).put("count", count);
		final JedisClusterCommand<List<byte[]>> command = new JedisClusterCommand<List<byte[]>>(client,
				ProtocolCommand.ZRANGEBYSCORE)
				.general((cmd)->cmd.zrangeByScore(key, min, max, (int) offset, (int) count))
				.pipeline((cmd)->cmd.zrangeByScore(key, min, max, (int) offset, (int) count))
				.transaction((cmd)->cmd.zrangeByScore(key, min, max, (int) offset, (int) count));
		return execute(command, args);
	}

	@Override
	public List<Tuple> zRangeByScoreWithScores(final String key, final double min, final double max){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		final JedisClusterCommand<List<Tuple>> command = new JedisClusterCommand<List<Tuple>>(client,
				ProtocolCommand.ZRANGEBYSCORE)
				.general((cmd)->cmd.zrangeByScoreWithScores(key, min, max), TupleConverter.LIST_CONVERTER)
				.pipeline((cmd)->cmd.zrangeByScoreWithScores(key, min, max), TupleConverter.LIST_CONVERTER)
				.transaction((cmd)->cmd.zrangeByScoreWithScores(key, min, max), TupleConverter.LIST_CONVERTER);
		return execute(command, args);
	}

	@Override
	public List<Tuple> zRangeByScoreWithScores(final byte[] key, final double min, final double max){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		final JedisClusterCommand<List<Tuple>> command = new JedisClusterCommand<List<Tuple>>(client,
				ProtocolCommand.ZRANGEBYSCORE)
				.general((cmd)->cmd.zrangeByScoreWithScores(key, min, max), TupleConverter.LIST_CONVERTER)
				.pipeline((cmd)->cmd.zrangeByScoreWithScores(key, min, max), TupleConverter.LIST_CONVERTER)
				.transaction((cmd)->cmd.zrangeByScoreWithScores(key, min, max), TupleConverter.LIST_CONVERTER);
		return execute(command, args);
	}

	@Override
	public List<Tuple> zRangeByScoreWithScores(final String key, final String min, final String max){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		final JedisClusterCommand<List<Tuple>> command = new JedisClusterCommand<List<Tuple>>(client,
				ProtocolCommand.ZRANGEBYSCORE)
				.general((cmd)->cmd.zrangeByScoreWithScores(key, min, max), TupleConverter.LIST_CONVERTER)
				.pipeline((cmd)->cmd.zrangeByScoreWithScores(key, min, max), TupleConverter.LIST_CONVERTER)
				.transaction((cmd)->cmd.zrangeByScoreWithScores(key, min, max), TupleConverter.LIST_CONVERTER);
		return execute(command, args);
	}

	@Override
	public List<Tuple> zRangeByScoreWithScores(final byte[] key, final byte[] min, final byte[] max){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		final JedisClusterCommand<List<Tuple>> command = new JedisClusterCommand<List<Tuple>>(client,
				ProtocolCommand.ZRANGEBYSCORE)
				.general((cmd)->cmd.zrangeByScoreWithScores(key, min, max), TupleConverter.LIST_CONVERTER)
				.pipeline((cmd)->cmd.zrangeByScoreWithScores(key, min, max), TupleConverter.LIST_CONVERTER)
				.transaction((cmd)->cmd.zrangeByScoreWithScores(key, min, max), TupleConverter.LIST_CONVERTER);
		return execute(command, args);
	}

	@Override
	public List<Tuple> zRangeByScoreWithScores(final String key, final double min, final double max, final long offset,
											   final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max)
				.put("offset", offset).put("count", count);
		final JedisClusterCommand<List<Tuple>> command = new JedisClusterCommand<List<Tuple>>(client,
				ProtocolCommand.ZRANGEBYSCORE)
				.general((cmd)->cmd.zrangeByScoreWithScores(key, min, max, (int) offset, (int) count),
						TupleConverter.LIST_CONVERTER)
				.pipeline((cmd)->cmd.zrangeByScoreWithScores(key, min, max, (int) offset, (int) count),
						TupleConverter.LIST_CONVERTER)
				.transaction((cmd)->cmd.zrangeByScoreWithScores(key, min, max, (int) offset, (int) count),
						TupleConverter.LIST_CONVERTER);
		return execute(command, args);
	}

	@Override
	public List<Tuple> zRangeByScoreWithScores(final byte[] key, final double min, final double max, final long offset,
											   final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max)
				.put("offset", offset).put("count", count);
		final JedisClusterCommand<List<Tuple>> command = new JedisClusterCommand<List<Tuple>>(client,
				ProtocolCommand.ZRANGEBYSCORE)
				.general((cmd)->cmd.zrangeByScoreWithScores(key, min, max, (int) offset, (int) count),
						TupleConverter.LIST_CONVERTER)
				.pipeline((cmd)->cmd.zrangeByScoreWithScores(key, min, max, (int) offset, (int) count),
						TupleConverter.LIST_CONVERTER)
				.transaction((cmd)->cmd.zrangeByScoreWithScores(key, min, max, (int) offset, (int) count),
						TupleConverter.LIST_CONVERTER);
		return execute(command, args);
	}

	@Override
	public List<Tuple> zRangeByScoreWithScores(final String key, final String min, final String max, final long offset,
											   final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max)
				.put("offset", offset).put("count", count);
		final JedisClusterCommand<List<Tuple>> command = new JedisClusterCommand<List<Tuple>>(client,
				ProtocolCommand.ZRANGEBYSCORE)
				.general((cmd)->cmd.zrangeByScoreWithScores(key, min, max, (int) offset, (int) count),
						TupleConverter.LIST_CONVERTER)
				.pipeline((cmd)->cmd.zrangeByScoreWithScores(key, min, max, (int) offset, (int) count),
						TupleConverter.LIST_CONVERTER)
				.transaction((cmd)->cmd.zrangeByScoreWithScores(key, min, max, (int) offset, (int) count),
						TupleConverter.LIST_CONVERTER);
		return execute(command, args);
	}

	@Override
	public List<Tuple> zRangeByScoreWithScores(final byte[] key, final byte[] min, final byte[] max, final long offset,
											   final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max)
				.put("offset", offset).put("count", count);
		final JedisClusterCommand<List<Tuple>> command = new JedisClusterCommand<List<Tuple>>(client,
				ProtocolCommand.ZRANGEBYSCORE)
				.general((cmd)->cmd.zrangeByScoreWithScores(key, min, max, (int) offset, (int) count),
						TupleConverter.LIST_CONVERTER)
				.pipeline((cmd)->cmd.zrangeByScoreWithScores(key, min, max, (int) offset, (int) count),
						TupleConverter.LIST_CONVERTER)
				.transaction((cmd)->cmd.zrangeByScoreWithScores(key, min, max, (int) offset, (int) count),
						TupleConverter.LIST_CONVERTER);
		return execute(command, args);
	}

	@Override
	public long zRangeStore(final String destKey, final String key, final long start, final long end){
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("key", key).put("start", start)
				.put("end", end);
		final JedisZRangeParams params = new JedisZRangeParams(start, end);
		final JedisClusterCommand<Long> command = new JedisClusterCommand<Long>(client, ProtocolCommand.ZRANGESTORE)
				.general((cmd)->cmd.zrangestore(destKey, key, params))
				.pipeline((cmd)->cmd.zrangestore(destKey, key, params))
				.transaction((cmd)->cmd.zrangestore(destKey, key, params));
		return execute(command, args);
	}

	@Override
	public long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end){
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("key", key).put("start", start)
				.put("end", end);
		final JedisZRangeParams params = new JedisZRangeParams(start, end);
		final JedisClusterCommand<Long> command = new JedisClusterCommand<Long>(client, ProtocolCommand.ZRANGESTORE)
				.general((cmd)->cmd.zrangestore(destKey, key, params))
				.pipeline((cmd)->cmd.zrangestore(destKey, key, params))
				.transaction((cmd)->cmd.zrangestore(destKey, key, params));
		return execute(command, args);
	}

	@Override
	public long zRangeStore(final String destKey, final String key, final long start, final long end,
							final ZRangeBy by){
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("key", key).put("start", start)
				.put("end", end).put("by", by);
		final JedisZRangeParams params = new JedisZRangeParams(by, start, end);
		final JedisClusterCommand<Long> command = new JedisClusterCommand<Long>(client, ProtocolCommand.ZRANGESTORE)
				.general((cmd)->cmd.zrangestore(destKey, key, params))
				.pipeline((cmd)->cmd.zrangestore(destKey, key, params))
				.transaction((cmd)->cmd.zrangestore(destKey, key, params));
		return execute(command, args);
	}

	@Override
	public long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end,
							final ZRangeBy by){
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("key", key).put("start", start)
				.put("end", end).put("by", by);
		final JedisZRangeParams params = new JedisZRangeParams(by, start, end);
		final JedisClusterCommand<Long> command = new JedisClusterCommand<Long>(client, ProtocolCommand.ZRANGESTORE)
				.general((cmd)->cmd.zrangestore(destKey, key, params))
				.pipeline((cmd)->cmd.zrangestore(destKey, key, params))
				.transaction((cmd)->cmd.zrangestore(destKey, key, params));
		return execute(command, args);
	}

	@Override
	public long zRangeStore(final String destKey, final String key, final long start, final long end,
							final boolean rev){
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("key", key).put("start", start)
				.put("end", end).put("rev", rev);
		final JedisZRangeParams params = new JedisZRangeParams(start, end, rev);
		final JedisClusterCommand<Long> command = new JedisClusterCommand<Long>(client, ProtocolCommand.ZRANGESTORE)
				.general((cmd)->cmd.zrangestore(destKey, key, params))
				.pipeline((cmd)->cmd.zrangestore(destKey, key, params))
				.transaction((cmd)->cmd.zrangestore(destKey, key, params));
		return execute(command, args);
	}

	@Override
	public long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end,
							final boolean rev){
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("key", key).put("start", start)
				.put("end", end).put("rev", rev);
		final JedisZRangeParams params = new JedisZRangeParams(start, end, rev);
		final JedisClusterCommand<Long> command = new JedisClusterCommand<Long>(client, ProtocolCommand.ZRANGESTORE)
				.general((cmd)->cmd.zrangestore(destKey, key, params))
				.pipeline((cmd)->cmd.zrangestore(destKey, key, params))
				.transaction((cmd)->cmd.zrangestore(destKey, key, params));
		return execute(command, args);
	}

	@Override
	public long zRangeStore(final String destKey, final String key, final long start, final long end, final long offset,
							final long count){
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("key", key).put("start", start)
				.put("end", end).put("offset", offset).put("count", count);
		final JedisZRangeParams params = new JedisZRangeParams(start, end, offset, count);
		final JedisClusterCommand<Long> command = new JedisClusterCommand<Long>(client, ProtocolCommand.ZRANGESTORE)
				.general((cmd)->cmd.zrangestore(destKey, key, params))
				.pipeline((cmd)->cmd.zrangestore(destKey, key, params))
				.transaction((cmd)->cmd.zrangestore(destKey, key, params));
		return execute(command, args);
	}

	@Override
	public long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end, final long offset,
							final long count){
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("key", key).put("start", start)
				.put("end", end).put("offset", offset).put("count", count);
		final JedisZRangeParams params = new JedisZRangeParams(start, end, offset, count);
		final JedisClusterCommand<Long> command = new JedisClusterCommand<Long>(client, ProtocolCommand.ZRANGESTORE)
				.general((cmd)->cmd.zrangestore(destKey, key, params))
				.pipeline((cmd)->cmd.zrangestore(destKey, key, params))
				.transaction((cmd)->cmd.zrangestore(destKey, key, params));
		return execute(command, args);
	}

	@Override
	public long zRangeStore(final String destKey, final String key, final long start, final long end,
							final ZRangeBy by, final boolean rev){
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("key", key).put("start", start)
				.put("end", end).put("by", by).put("rev", rev);
		final JedisZRangeParams params = new JedisZRangeParams(by, start, end, rev);
		final JedisClusterCommand<Long> command = new JedisClusterCommand<Long>(client, ProtocolCommand.ZRANGESTORE)
				.general((cmd)->cmd.zrangestore(destKey, key, params))
				.pipeline((cmd)->cmd.zrangestore(destKey, key, params))
				.transaction((cmd)->cmd.zrangestore(destKey, key, params));
		return execute(command, args);
	}

	@Override
	public long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end,
							final ZRangeBy by, final boolean rev){
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("key", key).put("start", start)
				.put("end", end).put("by", by).put("rev", rev);
		final JedisZRangeParams params = new JedisZRangeParams(by, start, end, rev);
		final JedisClusterCommand<Long> command = new JedisClusterCommand<Long>(client, ProtocolCommand.ZRANGESTORE)
				.general((cmd)->cmd.zrangestore(destKey, key, params))
				.pipeline((cmd)->cmd.zrangestore(destKey, key, params))
				.transaction((cmd)->cmd.zrangestore(destKey, key, params));
		return execute(command, args);
	}

	@Override
	public long zRangeStore(final String destKey, final String key, final long start, final long end,
							final ZRangeBy by, final long offset, final long count){
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("key", key).put("start", start)
				.put("end", end).put("by", by).put("offset", offset).put("count", count);
		final JedisZRangeParams params = new JedisZRangeParams(by, start, end, offset, count);
		final JedisClusterCommand<Long> command = new JedisClusterCommand<Long>(client, ProtocolCommand.ZRANGESTORE)
				.general((cmd)->cmd.zrangestore(destKey, key, params))
				.pipeline((cmd)->cmd.zrangestore(destKey, key, params))
				.transaction((cmd)->cmd.zrangestore(destKey, key, params));
		return execute(command, args);
	}

	@Override
	public long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end,
							final ZRangeBy by, final long offset, final long count){
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("key", key).put("start", start)
				.put("end", end).put("by", by).put("offset", offset).put("count", count);
		final JedisZRangeParams params = new JedisZRangeParams(by, start, end, offset, count);
		final JedisClusterCommand<Long> command = new JedisClusterCommand<Long>(client, ProtocolCommand.ZRANGESTORE)
				.general((cmd)->cmd.zrangestore(destKey, key, params))
				.pipeline((cmd)->cmd.zrangestore(destKey, key, params))
				.transaction((cmd)->cmd.zrangestore(destKey, key, params));
		return execute(command, args);
	}

	@Override
	public long zRangeStore(final String destKey, final String key, final long start, final long end, final boolean rev,
							final long offset, final long count){
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("key", key).put("start", start)
				.put("end", end).put("rev", rev).put("offset", offset).put("count", count);
		final JedisZRangeParams params = new JedisZRangeParams(start, end, rev, offset, count);
		final JedisClusterCommand<Long> command = new JedisClusterCommand<Long>(client, ProtocolCommand.ZRANGESTORE)
				.general((cmd)->cmd.zrangestore(destKey, key, params))
				.pipeline((cmd)->cmd.zrangestore(destKey, key, params))
				.transaction((cmd)->cmd.zrangestore(destKey, key, params));
		return execute(command, args);
	}

	@Override
	public long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end, final boolean rev,
							final long offset, final long count){
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("key", key).put("start", start)
				.put("end", end).put("rev", rev).put("offset", offset).put("count", count);
		final JedisZRangeParams params = new JedisZRangeParams(start, end, rev, offset, count);
		final JedisClusterCommand<Long> command = new JedisClusterCommand<Long>(client, ProtocolCommand.ZRANGESTORE)
				.general((cmd)->cmd.zrangestore(destKey, key, params))
				.pipeline((cmd)->cmd.zrangestore(destKey, key, params))
				.transaction((cmd)->cmd.zrangestore(destKey, key, params));
		return execute(command, args);
	}

	@Override
	public long zRangeStore(final String destKey, final String key, final long start, final long end, final ZRangeBy by,
							final boolean rev, final long offset, final long count){
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("key", key).put("start", start)
				.put("end", end).put("by", by).put("rev", rev).put("offset", offset).put("count", count);
		final JedisZRangeParams params = new JedisZRangeParams(by, start, end, rev, offset, count);
		final JedisClusterCommand<Long> command = new JedisClusterCommand<Long>(client, ProtocolCommand.ZRANGESTORE)
				.general((cmd)->cmd.zrangestore(destKey, key, params))
				.pipeline((cmd)->cmd.zrangestore(destKey, key, params))
				.transaction((cmd)->cmd.zrangestore(destKey, key, params));
		return execute(command, args);
	}

	@Override
	public long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end, final ZRangeBy by,
							final boolean rev, final long offset, final long count){
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("key", key).put("start", start)
				.put("end", end).put("by", by).put("rev", rev).put("offset", offset).put("count", count);
		final JedisZRangeParams params = new JedisZRangeParams(by, start, end, rev, offset, count);
		final JedisClusterCommand<Long> command = new JedisClusterCommand<Long>(client, ProtocolCommand.ZRANGESTORE)
				.general((cmd)->cmd.zrangestore(destKey, key, params))
				.pipeline((cmd)->cmd.zrangestore(destKey, key, params))
				.transaction((cmd)->cmd.zrangestore(destKey, key, params));
		return execute(command, args);
	}

	@Override
	public Long zRank(final String key, final String member){
		final CommandArguments args = CommandArguments.create("key", key).put("member", member);
		final JedisClusterCommand<Long> command = new JedisClusterCommand<Long>(client, ProtocolCommand.ZRANK)
				.general((cmd)->cmd.zrank(key, member))
				.pipeline((cmd)->cmd.zrank(key, member))
				.transaction((cmd)->cmd.zrank(key, member));
		return execute(command, args);
	}

	@Override
	public Long zRank(final byte[] key, final byte[] member){
		final CommandArguments args = CommandArguments.create("key", key).put("member", member);
		final JedisClusterCommand<Long> command = new JedisClusterCommand<Long>(client, ProtocolCommand.ZRANK)
				.general((cmd)->cmd.zrank(key, member))
				.pipeline((cmd)->cmd.zrank(key, member))
				.transaction((cmd)->cmd.zrank(key, member));
		return execute(command, args);
	}

	@Override
	public long zRem(final String key, final String... members){
		final CommandArguments args = CommandArguments.create("key", key).put("members", members);
		final JedisClusterCommand<Long> command = new JedisClusterCommand<Long>(client, ProtocolCommand.ZREM)
				.general((cmd)->cmd.zrem(key, members))
				.pipeline((cmd)->cmd.zrem(key, members))
				.transaction((cmd)->cmd.zrem(key, members));
		return execute(command, args);
	}

	@Override
	public long zRem(final byte[] key, final byte[]... members){
		final CommandArguments args = CommandArguments.create("key", key).put("members", members);
		final JedisClusterCommand<Long> command = new JedisClusterCommand<Long>(client, ProtocolCommand.ZREM)
				.general((cmd)->cmd.zrem(key, members))
				.pipeline((cmd)->cmd.zrem(key, members))
				.transaction((cmd)->cmd.zrem(key, members));
		return execute(command, args);
	}

	@Override
	public long zRemRangeByLex(final String key, final String min, final String max){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		final JedisClusterCommand<Long> command = new JedisClusterCommand<Long>(client, ProtocolCommand.ZREMRANGEBYLEX)
				.general((cmd)->cmd.zremrangeByLex(key, min, max))
				.pipeline((cmd)->cmd.zremrangeByLex(key, min, max))
				.transaction((cmd)->cmd.zremrangeByLex(key, min, max));
		return execute(command, args);
	}

	@Override
	public long zRemRangeByLex(final byte[] key, final byte[] min, final byte[] max){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		final JedisClusterCommand<Long> command = new JedisClusterCommand<Long>(client, ProtocolCommand.ZREMRANGEBYLEX)
				.general((cmd)->cmd.zremrangeByLex(key, min, max))
				.pipeline((cmd)->cmd.zremrangeByLex(key, min, max))
				.transaction((cmd)->cmd.zremrangeByLex(key, min, max));
		return execute(command, args);
	}

	@Override
	public long zRemRangeByScore(final String key, final double min, final double max){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		final JedisClusterCommand<Long> command = new JedisClusterCommand<Long>(client,
				ProtocolCommand.ZREMRANGEBYSCORE)
				.general((cmd)->cmd.zremrangeByScore(key, min, max))
				.pipeline((cmd)->cmd.zremrangeByScore(key, min, max))
				.transaction((cmd)->cmd.zremrangeByScore(key, min, max));
		return execute(command, args);
	}

	@Override
	public long zRemRangeByScore(final byte[] key, final double min, final double max){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		final JedisClusterCommand<Long> command = new JedisClusterCommand<Long>(client,
				ProtocolCommand.ZREMRANGEBYSCORE)
				.general((cmd)->cmd.zremrangeByScore(key, min, max))
				.pipeline((cmd)->cmd.zremrangeByScore(key, min, max))
				.transaction((cmd)->cmd.zremrangeByScore(key, min, max));
		return execute(command, args);
	}

	@Override
	public long zRemRangeByScore(final String key, final String min, final String max){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		final JedisClusterCommand<Long> command = new JedisClusterCommand<Long>(client,
				ProtocolCommand.ZREMRANGEBYSCORE)
				.general((cmd)->cmd.zremrangeByScore(key, min, max))
				.pipeline((cmd)->cmd.zremrangeByScore(key, min, max))
				.transaction((cmd)->cmd.zremrangeByScore(key, min, max));
		return execute(command, args);
	}

	@Override
	public long zRemRangeByScore(final byte[] key, final byte[] min, final byte[] max){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		final JedisClusterCommand<Long> command = new JedisClusterCommand<Long>(client,
				ProtocolCommand.ZREMRANGEBYSCORE)
				.general((cmd)->cmd.zremrangeByScore(key, min, max))
				.pipeline((cmd)->cmd.zremrangeByScore(key, min, max))
				.transaction((cmd)->cmd.zremrangeByScore(key, min, max));
		return execute(command, args);
	}

	@Override
	public long zRemRangeByRank(final String key, final long start, final long end){
		final CommandArguments args = CommandArguments.create("key", key).put("start", start).put("end", end);
		final JedisClusterCommand<Long> command = new JedisClusterCommand<Long>(client, ProtocolCommand.ZREMRANGEBYRANK)
				.general((cmd)->cmd.zremrangeByRank(key, start, end))
				.pipeline((cmd)->cmd.zremrangeByRank(key, start, end))
				.transaction((cmd)->cmd.zremrangeByRank(key, start, end));
		return execute(command, args);
	}

	@Override
	public long zRemRangeByRank(final byte[] key, final long start, final long end){
		final CommandArguments args = CommandArguments.create("key", key).put("start", start).put("end", end);
		final JedisClusterCommand<Long> command = new JedisClusterCommand<Long>(client, ProtocolCommand.ZREMRANGEBYRANK)
				.general((cmd)->cmd.zremrangeByRank(key, start, end))
				.pipeline((cmd)->cmd.zremrangeByRank(key, start, end))
				.transaction((cmd)->cmd.zremrangeByRank(key, start, end));
		return execute(command, args);
	}

	@Override
	public List<String> zRevRange(final String key, final long start, final long end){
		final CommandArguments args = CommandArguments.create("key", key).put("start", start).put("end", end);
		final JedisClusterCommand<List<String>> command = new JedisClusterCommand<List<String>>(client,
				ProtocolCommand.ZREVRANGE)
				.general((cmd)->cmd.zrevrange(key, start, end))
				.pipeline((cmd)->cmd.zrevrange(key, start, end))
				.transaction((cmd)->cmd.zrevrange(key, start, end));
		return execute(command, args);
	}

	@Override
	public List<byte[]> zRevRange(final byte[] key, final long start, final long end){
		final CommandArguments args = CommandArguments.create("key", key).put("start", start).put("end", end);
		final JedisClusterCommand<List<byte[]>> command = new JedisClusterCommand<List<byte[]>>(client,
				ProtocolCommand.ZREVRANGE)
				.general((cmd)->cmd.zrevrange(key, start, end))
				.pipeline((cmd)->cmd.zrevrange(key, start, end))
				.transaction((cmd)->cmd.zrevrange(key, start, end));
		return execute(command, args);
	}

	@Override
	public List<Tuple> zRevRangeWithScores(final String key, final long start, final long end){
		final CommandArguments args = CommandArguments.create("key", key).put("start", start).put("end", end);
		final JedisClusterCommand<List<Tuple>> command = new JedisClusterCommand<List<Tuple>>(client,
				ProtocolCommand.ZREVRANGE)
				.general((cmd)->cmd.zrevrangeWithScores(key, start, end), TupleConverter.LIST_CONVERTER)
				.pipeline((cmd)->cmd.zrevrangeWithScores(key, start, end), TupleConverter.LIST_CONVERTER)
				.transaction((cmd)->cmd.zrevrangeWithScores(key, start, end), TupleConverter.LIST_CONVERTER);
		return execute(command, args);
	}

	@Override
	public List<Tuple> zRevRangeWithScores(final byte[] key, final long start, final long end){
		final CommandArguments args = CommandArguments.create("key", key).put("start", start).put("end", end);
		final JedisClusterCommand<List<Tuple>> command = new JedisClusterCommand<List<Tuple>>(client,
				ProtocolCommand.ZREVRANGE)
				.general((cmd)->cmd.zrevrangeWithScores(key, start, end), TupleConverter.LIST_CONVERTER)
				.pipeline((cmd)->cmd.zrevrangeWithScores(key, start, end), TupleConverter.LIST_CONVERTER)
				.transaction((cmd)->cmd.zrevrangeWithScores(key, start, end), TupleConverter.LIST_CONVERTER);
		return execute(command, args);
	}

	@Override
	public List<String> zRevRangeByLex(final String key, final String min, final String max){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		final JedisClusterCommand<List<String>> command = new JedisClusterCommand<List<String>>(client,
				ProtocolCommand.ZREVRANGEBYLEX)
				.general((cmd)->cmd.zrevrangeByLex(key, min, max)).pipeline((cmd)->cmd.zrevrangeByLex(key, min, max))
				.transaction((cmd)->cmd.zrevrangeByLex(key, min, max));
		return execute(command, args);
	}

	@Override
	public List<byte[]> zRevRangeByLex(final byte[] key, final byte[] min, final byte[] max){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		final JedisClusterCommand<List<byte[]>> command = new JedisClusterCommand<List<byte[]>>(client,
				ProtocolCommand.ZREVRANGEBYLEX)
				.general((cmd)->cmd.zrevrangeByLex(key, min, max))
				.pipeline((cmd)->cmd.zrevrangeByLex(key, min, max))
				.transaction((cmd)->cmd.zrevrangeByLex(key, min, max));
		return execute(command, args);
	}

	@Override
	public List<String> zRevRangeByLex(final String key, final String min, final String max, final long offset,
									   final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		final JedisClusterCommand<List<String>> command = new JedisClusterCommand<List<String>>(client,
				ProtocolCommand.ZREVRANGEBYLEX)
				.general((cmd)->cmd.zrevrangeByLex(key, min, max, (int) offset, (int) count))
				.pipeline((cmd)->cmd.zrevrangeByLex(key, min, max, (int) offset, (int) count))
				.transaction((cmd)->cmd.zrevrangeByLex(key, min, max, (int) offset, (int) count));
		return execute(command, args);
	}

	@Override
	public List<byte[]> zRevRangeByLex(final byte[] key, final byte[] min, final byte[] max, final long offset,
									   final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		final JedisClusterCommand<List<byte[]>> command = new JedisClusterCommand<List<byte[]>>(client,
				ProtocolCommand.ZREVRANGEBYLEX)
				.general((cmd)->cmd.zrevrangeByLex(key, min, max, (int) offset, (int) count))
				.pipeline((cmd)->cmd.zrevrangeByLex(key, min, max, (int) offset, (int) count))
				.transaction((cmd)->cmd.zrevrangeByLex(key, min, max, (int) offset, (int) count));
		return execute(command, args);
	}

	@Override
	public List<String> zRevRangeByScore(final String key, final double min, final double max){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		final JedisClusterCommand<List<String>> command = new JedisClusterCommand<List<String>>(client,
				ProtocolCommand.ZREVRANGEBYSCORE)
				.general((cmd)->cmd.zrevrangeByScore(key, min, max))
				.pipeline((cmd)->cmd.zrevrangeByScore(key, min, max))
				.transaction((cmd)->cmd.zrevrangeByScore(key, min, max));
		return execute(command, args);
	}

	@Override
	public List<byte[]> zRevRangeByScore(final byte[] key, final double min, final double max){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		final JedisClusterCommand<List<byte[]>> command = new JedisClusterCommand<List<byte[]>>(client,
				ProtocolCommand.ZREVRANGEBYSCORE)
				.general((cmd)->cmd.zrevrangeByScore(key, min, max))
				.pipeline((cmd)->cmd.zrevrangeByScore(key, min, max))
				.transaction((cmd)->cmd.zrevrangeByScore(key, min, max));
		return execute(command, args);
	}

	@Override
	public List<String> zRevRangeByScore(final String key, final String min, final String max){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		final JedisClusterCommand<List<String>> command = new JedisClusterCommand<List<String>>(client,
				ProtocolCommand.ZREVRANGEBYSCORE)
				.general((cmd)->cmd.zrevrangeByScore(key, min, max))
				.pipeline((cmd)->cmd.zrevrangeByScore(key, min, max))
				.transaction((cmd)->cmd.zrevrangeByScore(key, min, max));
		return execute(command, args);
	}

	@Override
	public List<byte[]> zRevRangeByScore(final byte[] key, final byte[] min, final byte[] max){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		final JedisClusterCommand<List<byte[]>> command = new JedisClusterCommand<List<byte[]>>(client,
				ProtocolCommand.ZREVRANGEBYSCORE)
				.general((cmd)->cmd.zrevrangeByScore(key, min, max))
				.pipeline((cmd)->cmd.zrevrangeByScore(key, min, max))
				.transaction((cmd)->cmd.zrevrangeByScore(key, min, max));
		return execute(command, args);
	}

	@Override
	public List<String> zRevRangeByScore(final String key, final double min, final double max, final long offset,
										 final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max)
				.put("offset", offset).put("count", count);
		final JedisClusterCommand<List<String>> command = new JedisClusterCommand<List<String>>(client,
				ProtocolCommand.ZREVRANGEBYSCORE)
				.general((cmd)->cmd.zrevrangeByScore(key, min, max, (int) offset, (int) count))
				.pipeline((cmd)->cmd.zrevrangeByScore(key, min, max, (int) offset, (int) count))
				.transaction((cmd)->cmd.zrevrangeByScore(key, min, max, (int) offset, (int) count));
		return execute(command, args);
	}

	@Override
	public List<byte[]> zRevRangeByScore(final byte[] key, final double min, final double max, final long offset,
										 final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max)
				.put("offset", offset).put("count", count);
		final JedisClusterCommand<List<byte[]>> command = new JedisClusterCommand<List<byte[]>>(client,
				ProtocolCommand.ZREVRANGEBYSCORE)
				.general((cmd)->cmd.zrevrangeByScore(key, min, max, (int) offset, (int) count))
				.pipeline((cmd)->cmd.zrevrangeByScore(key, min, max, (int) offset, (int) count))
				.transaction((cmd)->cmd.zrevrangeByScore(key, min, max, (int) offset, (int) count));
		return execute(command, args);
	}

	@Override
	public List<String> zRevRangeByScore(final String key, final String min, final String max, final long offset,
										 final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max)
				.put("offset", offset).put("count", count);
		final JedisClusterCommand<List<String>> command = new JedisClusterCommand<List<String>>(client,
				ProtocolCommand.ZREVRANGEBYSCORE)
				.general((cmd)->cmd.zrevrangeByScore(key, min, max, (int) offset, (int) count))
				.pipeline((cmd)->cmd.zrevrangeByScore(key, min, max, (int) offset, (int) count))
				.transaction((cmd)->cmd.zrevrangeByScore(key, min, max, (int) offset, (int) count));
		return execute(command, args);
	}

	@Override
	public List<byte[]> zRevRangeByScore(final byte[] key, final byte[] min, final byte[] max, final long offset,
										 final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max)
				.put("offset", offset).put("count", count);
		final JedisClusterCommand<List<byte[]>> command = new JedisClusterCommand<List<byte[]>>(client,
				ProtocolCommand.ZREVRANGEBYSCORE)
				.general((cmd)->cmd.zrevrangeByScore(key, min, max, (int) offset, (int) count))
				.pipeline((cmd)->cmd.zrevrangeByScore(key, min, max, (int) offset, (int) count))
				.transaction((cmd)->cmd.zrevrangeByScore(key, min, max, (int) offset, (int) count));
		return execute(command, args);
	}

	@Override
	public List<Tuple> zRevRangeByScoreWithScores(final String key, final double min, final double max){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		final JedisClusterCommand<List<Tuple>> command = new JedisClusterCommand<List<Tuple>>(client,
				ProtocolCommand.ZREVRANGEBYSCORE)
				.general((cmd)->cmd.zrevrangeByScoreWithScores(key, min, max), TupleConverter.LIST_CONVERTER)
				.pipeline((cmd)->cmd.zrevrangeByScoreWithScores(key, min, max), TupleConverter.LIST_CONVERTER)
				.transaction((cmd)->cmd.zrevrangeByScoreWithScores(key, min, max), TupleConverter.LIST_CONVERTER);
		return execute(command, args);
	}

	@Override
	public List<Tuple> zRevRangeByScoreWithScores(final byte[] key, final double min, final double max){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		final JedisClusterCommand<List<Tuple>> command = new JedisClusterCommand<List<Tuple>>(client,
				ProtocolCommand.ZREVRANGEBYSCORE)
				.general((cmd)->cmd.zrevrangeByScoreWithScores(key, min, max), TupleConverter.LIST_CONVERTER)
				.pipeline((cmd)->cmd.zrevrangeByScoreWithScores(key, min, max), TupleConverter.LIST_CONVERTER)
				.transaction((cmd)->cmd.zrevrangeByScoreWithScores(key, min, max), TupleConverter.LIST_CONVERTER);
		return execute(command, args);
	}

	@Override
	public List<Tuple> zRevRangeByScoreWithScores(final String key, final String min, final String max){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		final JedisClusterCommand<List<Tuple>> command = new JedisClusterCommand<List<Tuple>>(client,
				ProtocolCommand.ZREVRANGEBYSCORE)
				.general((cmd)->cmd.zrevrangeByScoreWithScores(key, min, max), TupleConverter.LIST_CONVERTER)
				.pipeline((cmd)->cmd.zrevrangeByScoreWithScores(key, min, max), TupleConverter.LIST_CONVERTER)
				.transaction((cmd)->cmd.zrevrangeByScoreWithScores(key, min, max), TupleConverter.LIST_CONVERTER);
		return execute(command, args);
	}

	@Override
	public List<Tuple> zRevRangeByScoreWithScores(final byte[] key, final byte[] min, final byte[] max){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		final JedisClusterCommand<List<Tuple>> command = new JedisClusterCommand<List<Tuple>>(client,
				ProtocolCommand.ZREVRANGEBYSCORE)
				.general((cmd)->cmd.zrevrangeByScoreWithScores(key, min, max), TupleConverter.LIST_CONVERTER)
				.pipeline((cmd)->cmd.zrevrangeByScoreWithScores(key, min, max), TupleConverter.LIST_CONVERTER)
				.transaction((cmd)->cmd.zrevrangeByScoreWithScores(key, min, max), TupleConverter.LIST_CONVERTER);
		return execute(command, args);
	}

	@Override
	public List<Tuple> zRevRangeByScoreWithScores(final String key, final double min, final double max,
												  final long offset, final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max)
				.put("offset", offset).put("count", count);
		final JedisClusterCommand<List<Tuple>> command = new JedisClusterCommand<List<Tuple>>(client,
				ProtocolCommand.ZREVRANGEBYSCORE)
				.general((cmd)->cmd.zrevrangeByScoreWithScores(key, min, max, (int) offset, (int) count),
						TupleConverter.LIST_CONVERTER)
				.pipeline((cmd)->cmd.zrevrangeByScoreWithScores(key, min, max, (int) offset, (int) count),
						TupleConverter.LIST_CONVERTER)
				.transaction((cmd)->cmd.zrevrangeByScoreWithScores(key, min, max, (int) offset, (int) count),
						TupleConverter.LIST_CONVERTER);
		return execute(command, args);
	}

	@Override
	public List<Tuple> zRevRangeByScoreWithScores(final byte[] key, final double min, final double max,
												  final long offset, final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max)
				.put("offset", offset).put("count", count);
		final JedisClusterCommand<List<Tuple>> command = new JedisClusterCommand<List<Tuple>>(client,
				ProtocolCommand.ZREVRANGEBYSCORE)
				.general((cmd)->cmd.zrevrangeByScoreWithScores(key, min, max, (int) offset, (int) count),
						TupleConverter.LIST_CONVERTER)
				.pipeline((cmd)->cmd.zrevrangeByScoreWithScores(key, min, max, (int) offset, (int) count),
						TupleConverter.LIST_CONVERTER)
				.transaction((cmd)->cmd.zrevrangeByScoreWithScores(key, min, max, (int) offset, (int) count),
						TupleConverter.LIST_CONVERTER);
		return execute(command, args);
	}

	@Override
	public List<Tuple> zRevRangeByScoreWithScores(final String key, final String min, final String max,
												  final long offset, final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max)
				.put("offset", offset).put("count", count);
		final JedisClusterCommand<List<Tuple>> command = new JedisClusterCommand<List<Tuple>>(client,
				ProtocolCommand.ZREVRANGEBYSCORE)
				.general((cmd)->cmd.zrevrangeByScoreWithScores(key, min, max, (int) offset, (int) count),
						TupleConverter.LIST_CONVERTER)
				.pipeline((cmd)->cmd.zrevrangeByScoreWithScores(key, min, max, (int) offset, (int) count),
						TupleConverter.LIST_CONVERTER)
				.transaction((cmd)->cmd.zrevrangeByScoreWithScores(key, min, max, (int) offset, (int) count),
						TupleConverter.LIST_CONVERTER);
		return execute(command, args);
	}

	@Override
	public List<Tuple> zRevRangeByScoreWithScores(final byte[] key, final byte[] min, final byte[] max,
												  final long offset, final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max)
				.put("offset", offset).put("count", count);
		final JedisClusterCommand<List<Tuple>> command = new JedisClusterCommand<List<Tuple>>(client,
				ProtocolCommand.ZREVRANGEBYSCORE)
				.general((cmd)->cmd.zrevrangeByScoreWithScores(key, min, max, (int) offset, (int) count),
						TupleConverter.LIST_CONVERTER)
				.pipeline((cmd)->cmd.zrevrangeByScoreWithScores(key, min, max, (int) offset, (int) count),
						TupleConverter.LIST_CONVERTER)
				.transaction((cmd)->cmd.zrevrangeByScoreWithScores(key, min, max, (int) offset, (int) count),
						TupleConverter.LIST_CONVERTER);
		return execute(command, args);
	}

	@Override
	public long zRevRank(final String key, final String member){
		final CommandArguments args = CommandArguments.create("key", key).put("member", member);
		final JedisClusterCommand<Long> command = new JedisClusterCommand<Long>(client, ProtocolCommand.ZREVRANK)
				.general((cmd)->cmd.zrevrank(key, member))
				.pipeline((cmd)->cmd.zrevrank(key, member))
				.transaction((cmd)->cmd.zrevrank(key, member));
		return execute(command, args);
	}

	@Override
	public long zRevRank(final byte[] key, final byte[] member){
		final CommandArguments args = CommandArguments.create("key", key).put("member", member);
		final JedisClusterCommand<Long> command = new JedisClusterCommand<Long>(client, ProtocolCommand.ZREVRANK)
				.general((cmd)->cmd.zrevrank(key, member))
				.pipeline((cmd)->cmd.zrevrank(key, member))
				.transaction((cmd)->cmd.zrevrank(key, member));
		return execute(command, args);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final String cursor){
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor);
		final JedisClusterCommand<ScanResult<List<Tuple>>> command = new JedisClusterCommand<ScanResult<List<Tuple>>>(
				client, ProtocolCommand.ZSCAN)
				.general((cmd)->cmd.zscan(key, cursor), ScanResultConverter.ListTupleScanResultConverter.INSTANCE)
				.pipeline((cmd)->cmd.zscan(key, cursor), ScanResultConverter.ListTupleScanResultConverter.INSTANCE)
				.transaction((cmd)->cmd.zscan(key, cursor), ScanResultConverter.ListTupleScanResultConverter.INSTANCE);
		return execute(command, args);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor){
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor);
		final JedisClusterCommand<ScanResult<List<Tuple>>> command = new JedisClusterCommand<ScanResult<List<Tuple>>>(
				client, ProtocolCommand.ZSCAN)
				.general((cmd)->cmd.zscan(key, cursor), ScanResultConverter.ListTupleScanResultConverter.INSTANCE)
				.pipeline((cmd)->cmd.zscan(key, cursor), ScanResultConverter.ListTupleScanResultConverter.INSTANCE)
				.transaction((cmd)->cmd.zscan(key, cursor), ScanResultConverter.ListTupleScanResultConverter.INSTANCE);
		return execute(command, args);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final String cursor, final String pattern){
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor).put("pattern", pattern);
		final JedisScanParams params = new JedisScanParams(pattern);
		final JedisClusterCommand<ScanResult<List<Tuple>>> command = new JedisClusterCommand<ScanResult<List<Tuple>>>(
				client, ProtocolCommand.ZSCAN)
				.general((cmd)->cmd.zscan(key, cursor, params),
						ScanResultConverter.ListTupleScanResultConverter.INSTANCE)
				.pipeline((cmd)->cmd.zscan(key, cursor, params),
						ScanResultConverter.ListTupleScanResultConverter.INSTANCE)
				.transaction((cmd)->cmd.zscan(key, cursor, params),
						ScanResultConverter.ListTupleScanResultConverter.INSTANCE);
		return execute(command, args);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor, final byte[] pattern){
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor).put("pattern", pattern);
		final JedisScanParams params = new JedisScanParams(pattern);
		final JedisClusterCommand<ScanResult<List<Tuple>>> command = new JedisClusterCommand<ScanResult<List<Tuple>>>(
				client, ProtocolCommand.ZSCAN)
				.general((cmd)->cmd.zscan(key, cursor, params),
						ScanResultConverter.ListTupleScanResultConverter.INSTANCE)
				.pipeline((cmd)->cmd.zscan(key, cursor, params),
						ScanResultConverter.ListTupleScanResultConverter.INSTANCE)
				.transaction((cmd)->cmd.zscan(key, cursor, params),
						ScanResultConverter.ListTupleScanResultConverter.INSTANCE);
		return execute(command, args);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final String cursor, final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor).put("count", count);
		final JedisScanParams params = new JedisScanParams(count);
		final JedisClusterCommand<ScanResult<List<Tuple>>> command = new JedisClusterCommand<ScanResult<List<Tuple>>>(
				client, ProtocolCommand.ZSCAN)
				.general((cmd)->cmd.zscan(key, cursor, params),
						ScanResultConverter.ListTupleScanResultConverter.INSTANCE)
				.pipeline((cmd)->cmd.zscan(key, cursor, params),
						ScanResultConverter.ListTupleScanResultConverter.INSTANCE)
				.transaction((cmd)->cmd.zscan(key, cursor, params),
						ScanResultConverter.ListTupleScanResultConverter.INSTANCE);
		return execute(command, args);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor, final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor).put("count", count);
		final JedisScanParams params = new JedisScanParams(count);
		final JedisClusterCommand<ScanResult<List<Tuple>>> command = new JedisClusterCommand<ScanResult<List<Tuple>>>(
				client, ProtocolCommand.ZSCAN)
				.general((cmd)->cmd.zscan(key, cursor, params),
						ScanResultConverter.ListTupleScanResultConverter.INSTANCE)
				.pipeline((cmd)->cmd.zscan(key, cursor, params),
						ScanResultConverter.ListTupleScanResultConverter.INSTANCE)
				.transaction((cmd)->cmd.zscan(key, cursor, params),
						ScanResultConverter.ListTupleScanResultConverter.INSTANCE);
		return execute(command, args);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final String cursor, final String pattern, final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor).put("pattern", pattern)
				.put("count", count);
		final JedisScanParams params = new JedisScanParams(pattern, count);
		final JedisClusterCommand<ScanResult<List<Tuple>>> command = new JedisClusterCommand<ScanResult<List<Tuple>>>(
				client, ProtocolCommand.ZSCAN)
				.general((cmd)->cmd.zscan(key, cursor, params),
						ScanResultConverter.ListTupleScanResultConverter.INSTANCE)
				.pipeline((cmd)->cmd.zscan(key, cursor, params),
						ScanResultConverter.ListTupleScanResultConverter.INSTANCE)
				.transaction((cmd)->cmd.zscan(key, cursor, params),
						ScanResultConverter.ListTupleScanResultConverter.INSTANCE);
		return execute(command, args);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor, final byte[] pattern, final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor).put("pattern", pattern)
				.put("count", count);
		final JedisScanParams params = new JedisScanParams(pattern, count);
		final JedisClusterCommand<ScanResult<List<Tuple>>> command = new JedisClusterCommand<ScanResult<List<Tuple>>>(
				client, ProtocolCommand.ZSCAN)
				.general((cmd)->cmd.zscan(key, cursor, params),
						ScanResultConverter.ListTupleScanResultConverter.INSTANCE)
				.pipeline((cmd)->cmd.zscan(key, cursor, params),
						ScanResultConverter.ListTupleScanResultConverter.INSTANCE)
				.transaction((cmd)->cmd.zscan(key, cursor, params),
						ScanResultConverter.ListTupleScanResultConverter.INSTANCE);
		return execute(command, args);
	}

	@Override
	public Double zScore(final String key, final String member){
		final CommandArguments args = CommandArguments.create("key", key).put("member", member);
		final JedisClusterCommand<Double> command = new JedisClusterCommand<Double>(client, ProtocolCommand.ZSCORE)
				.general((cmd)->cmd.zscore(key, member))
				.pipeline((cmd)->cmd.zscore(key, member))
				.transaction((cmd)->cmd.zscore(key, member));
		return execute(command, args);
	}

	@Override
	public Double zScore(final byte[] key, final byte[] member){
		final CommandArguments args = CommandArguments.create("key", key).put("member", member);
		final JedisClusterCommand<Double> command = new JedisClusterCommand<Double>(client, ProtocolCommand.ZSCORE)
				.general((cmd)->cmd.zscore(key, member))
				.pipeline((cmd)->cmd.zscore(key, member))
				.transaction((cmd)->cmd.zscore(key, member));
		return execute(command, args);
	}

	@Override
	public Set<String> zUnion(final String... keys){
		final CommandArguments args = CommandArguments.create("keys", keys);
		final JedisZParams params = new JedisZParams();
		final JedisClusterCommand<Set<String>> command = new JedisClusterCommand<Set<String>>(client,
				ProtocolCommand.ZUNION)
				.general((cmd)->cmd.zunion(params, keys))
				.pipeline((cmd)->cmd.zunion(params, keys))
				.transaction((cmd)->cmd.zunion(params, keys));
		return execute(command, args);
	}

	@Override
	public Set<byte[]> zUnion(final byte[]... keys){
		final CommandArguments args = CommandArguments.create("keys", keys);
		final JedisZParams params = new JedisZParams();
		final JedisClusterCommand<Set<byte[]>> command = new JedisClusterCommand<Set<byte[]>>(client,
				ProtocolCommand.ZUNION)
				.general((cmd)->cmd.zunion(params, keys))
				.pipeline((cmd)->cmd.zunion(params, keys))
				.transaction((cmd)->cmd.zunion(params, keys));
		return execute(command, args);
	}

	@Override
	public Set<String> zUnion(final String[] keys, final Aggregate aggregate){
		final CommandArguments args = CommandArguments.create("keys", keys).put("aggregate", aggregate);
		final JedisZParams params = new JedisZParams(aggregate);
		final JedisClusterCommand<Set<String>> command = new JedisClusterCommand<Set<String>>(client,
				ProtocolCommand.ZUNION)
				.general((cmd)->cmd.zunion(params, keys))
				.pipeline((cmd)->cmd.zunion(params, keys))
				.transaction((cmd)->cmd.zunion(params, keys));
		return execute(command, args);
	}

	@Override
	public Set<byte[]> zUnion(final byte[][] keys, final Aggregate aggregate){
		final CommandArguments args = CommandArguments.create("keys", keys).put("aggregate", aggregate);
		final JedisZParams params = new JedisZParams(aggregate);
		final JedisClusterCommand<Set<byte[]>> command = new JedisClusterCommand<Set<byte[]>>(client,
				ProtocolCommand.ZUNION)
				.general((cmd)->cmd.zunion(params, keys))
				.pipeline((cmd)->cmd.zunion(params, keys))
				.transaction((cmd)->cmd.zunion(params, keys));
		return execute(command, args);
	}

	@Override
	public Set<String> zUnion(final String[] keys, final double... weights){
		final CommandArguments args = CommandArguments.create("keys", keys).put("weights", weights);
		final JedisZParams params = new JedisZParams(weights);
		final JedisClusterCommand<Set<String>> command = new JedisClusterCommand<Set<String>>(client,
				ProtocolCommand.ZUNION)
				.general((cmd)->cmd.zunion(params, keys))
				.pipeline((cmd)->cmd.zunion(params, keys))
				.transaction((cmd)->cmd.zunion(params, keys));
		return execute(command, args);
	}

	@Override
	public Set<byte[]> zUnion(final byte[][] keys, final double... weights){
		final CommandArguments args = CommandArguments.create("keys", keys).put("weights", weights);
		final JedisZParams params = new JedisZParams(weights);
		final JedisClusterCommand<Set<byte[]>> command = new JedisClusterCommand<Set<byte[]>>(client,
				ProtocolCommand.ZUNION)
				.general((cmd)->cmd.zunion(params, keys))
				.pipeline((cmd)->cmd.zunion(params, keys))
				.transaction((cmd)->cmd.zunion(params, keys));
		return execute(command, args);
	}

	@Override
	public Set<String> zUnion(final String[] keys, final Aggregate aggregate, final double... weights){
		final CommandArguments args = CommandArguments.create("keys", keys).put("aggregate", aggregate)
				.put("weights", weights);
		final JedisZParams params = new JedisZParams(aggregate, weights);
		final JedisClusterCommand<Set<String>> command = new JedisClusterCommand<Set<String>>(client,
				ProtocolCommand.ZUNION)
				.general((cmd)->cmd.zunion(params, keys))
				.pipeline((cmd)->cmd.zunion(params, keys))
				.transaction((cmd)->cmd.zunion(params, keys));
		return execute(command, args);
	}

	@Override
	public Set<byte[]> zUnion(final byte[][] keys, final Aggregate aggregate, final double... weights){
		final CommandArguments args = CommandArguments.create("keys", keys).put("aggregate", aggregate)
				.put("weights", weights);
		final JedisZParams params = new JedisZParams(aggregate, weights);
		final JedisClusterCommand<Set<byte[]>> command = new JedisClusterCommand<Set<byte[]>>(client,
				ProtocolCommand.ZUNION)
				.general((cmd)->cmd.zunion(params, keys))
				.pipeline((cmd)->cmd.zunion(params, keys))
				.transaction((cmd)->cmd.zunion(params, keys));
		return execute(command, args);
	}

	@Override
	public Set<Tuple> zUnionWithScores(final String... keys){
		final CommandArguments args = CommandArguments.create("keys", keys);
		final JedisZParams params = new JedisZParams();
		final JedisClusterCommand<Set<Tuple>> command = new JedisClusterCommand<Set<Tuple>>(client,
				ProtocolCommand.ZUNION)
				.general((cmd)->cmd.zunionWithScores(params, keys), TupleConverter.SET_CONVERTER)
				.pipeline((cmd)->cmd.zunionWithScores(params, keys), TupleConverter.SET_CONVERTER)
				.transaction((cmd)->cmd.zunionWithScores(params, keys), TupleConverter.SET_CONVERTER);
		return execute(command, args);
	}

	@Override
	public Set<Tuple> zUnionWithScores(final byte[]... keys){
		final CommandArguments args = CommandArguments.create("keys", keys);
		final JedisZParams params = new JedisZParams();
		final JedisClusterCommand<Set<Tuple>> command = new JedisClusterCommand<Set<Tuple>>(client,
				ProtocolCommand.ZUNION)
				.general((cmd)->cmd.zunionWithScores(params, keys), TupleConverter.SET_CONVERTER)
				.pipeline((cmd)->cmd.zunionWithScores(params, keys), TupleConverter.SET_CONVERTER)
				.transaction((cmd)->cmd.zunionWithScores(params, keys), TupleConverter.SET_CONVERTER);
		return execute(command, args);
	}

	@Override
	public Set<Tuple> zUnionWithScores(final String[] keys, final Aggregate aggregate){
		final CommandArguments args = CommandArguments.create("keys", keys).put("aggregate", aggregate);
		final JedisZParams params = new JedisZParams(aggregate);
		final JedisClusterCommand<Set<Tuple>> command = new JedisClusterCommand<Set<Tuple>>(client,
				ProtocolCommand.ZUNION)
				.general((cmd)->cmd.zunionWithScores(params, keys), TupleConverter.SET_CONVERTER)
				.pipeline((cmd)->cmd.zunionWithScores(params, keys), TupleConverter.SET_CONVERTER)
				.transaction((cmd)->cmd.zunionWithScores(params, keys), TupleConverter.SET_CONVERTER);
		return execute(command, args);
	}

	@Override
	public Set<Tuple> zUnionWithScores(final byte[][] keys, final Aggregate aggregate){
		final CommandArguments args = CommandArguments.create("keys", keys).put("aggregate", aggregate);
		final JedisZParams params = new JedisZParams(aggregate);
		final JedisClusterCommand<Set<Tuple>> command = new JedisClusterCommand<Set<Tuple>>(client,
				ProtocolCommand.ZUNION)
				.general((cmd)->cmd.zunionWithScores(params, keys), TupleConverter.SET_CONVERTER)
				.pipeline((cmd)->cmd.zunionWithScores(params, keys), TupleConverter.SET_CONVERTER)
				.transaction((cmd)->cmd.zunionWithScores(params, keys), TupleConverter.SET_CONVERTER);
		return execute(command, args);
	}

	@Override
	public Set<Tuple> zUnionWithScores(final String[] keys, final double... weights){
		final CommandArguments args = CommandArguments.create("keys", keys).put("weights", weights);
		final JedisZParams params = new JedisZParams(weights);
		final JedisClusterCommand<Set<Tuple>> command = new JedisClusterCommand<Set<Tuple>>(client,
				ProtocolCommand.ZUNION)
				.general((cmd)->cmd.zunionWithScores(params, keys), TupleConverter.SET_CONVERTER)
				.pipeline((cmd)->cmd.zunionWithScores(params, keys), TupleConverter.SET_CONVERTER)
				.transaction((cmd)->cmd.zunionWithScores(params, keys), TupleConverter.SET_CONVERTER);
		return execute(command, args);
	}

	@Override
	public Set<Tuple> zUnionWithScores(final byte[][] keys, final double... weights){
		final CommandArguments args = CommandArguments.create("keys", keys).put("weights", weights);
		final JedisZParams params = new JedisZParams(weights);
		final JedisClusterCommand<Set<Tuple>> command = new JedisClusterCommand<Set<Tuple>>(client,
				ProtocolCommand.ZUNION)
				.general((cmd)->cmd.zunionWithScores(params, keys), TupleConverter.SET_CONVERTER)
				.pipeline((cmd)->cmd.zunionWithScores(params, keys), TupleConverter.SET_CONVERTER)
				.transaction((cmd)->cmd.zunionWithScores(params, keys), TupleConverter.SET_CONVERTER);
		return execute(command, args);
	}

	@Override
	public Set<Tuple> zUnionWithScores(final String[] keys, final Aggregate aggregate, final double... weights){
		final CommandArguments args = CommandArguments.create("keys", keys).put("aggregate", aggregate)
				.put("weights", weights);
		final JedisZParams params = new JedisZParams(aggregate, weights);
		final JedisClusterCommand<Set<Tuple>> command = new JedisClusterCommand<Set<Tuple>>(client,
				ProtocolCommand.ZUNION)
				.general((cmd)->cmd.zunionWithScores(params, keys), TupleConverter.SET_CONVERTER)
				.pipeline((cmd)->cmd.zunionWithScores(params, keys), TupleConverter.SET_CONVERTER)
				.transaction((cmd)->cmd.zunionWithScores(params, keys), TupleConverter.SET_CONVERTER);
		return execute(command, args);
	}

	@Override
	public Set<Tuple> zUnionWithScores(final byte[][] keys, final Aggregate aggregate, final double... weights){
		final CommandArguments args = CommandArguments.create("keys", keys).put("aggregate", aggregate)
				.put("weights", weights);
		final JedisZParams params = new JedisZParams(aggregate, weights);
		final JedisClusterCommand<Set<Tuple>> command = new JedisClusterCommand<Set<Tuple>>(client,
				ProtocolCommand.ZUNION)
				.general((cmd)->cmd.zunionWithScores(params, keys), TupleConverter.SET_CONVERTER)
				.pipeline((cmd)->cmd.zunionWithScores(params, keys), TupleConverter.SET_CONVERTER)
				.transaction((cmd)->cmd.zunionWithScores(params, keys), TupleConverter.SET_CONVERTER);
		return execute(command, args);
	}

	@Override
	public long zUnionStore(final String destKey, final String... keys){
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("keys", keys);
		final JedisClusterCommand<Long> command = new JedisClusterCommand<Long>(client, ProtocolCommand.ZUNIONSTORE)
				.general((cmd)->cmd.zunionstore(destKey, keys))
				.pipeline((cmd)->cmd.zunionstore(destKey, keys))
				.transaction((cmd)->cmd.zunionstore(destKey, keys));
		return execute(command, args);
	}

	@Override
	public long zUnionStore(final byte[] destKey, final byte[]... keys){
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("keys", keys);
		final JedisClusterCommand<Long> command = new JedisClusterCommand<Long>(client, ProtocolCommand.ZUNIONSTORE)
				.general((cmd)->cmd.zunionstore(destKey, keys))
				.pipeline((cmd)->cmd.zunionstore(destKey, keys))
				.transaction((cmd)->cmd.zunionstore(destKey, keys));
		return execute(command, args);
	}

	@Override
	public long zUnionStore(final String destKey, final String[] keys, final Aggregate aggregate){
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("keys", keys)
				.put("aggregate", aggregate);
		final JedisZParams params = new JedisZParams(aggregate);
		final JedisClusterCommand<Long> command = new JedisClusterCommand<Long>(client, ProtocolCommand.ZUNIONSTORE)
				.general((cmd)->cmd.zunionstore(destKey, params, keys))
				.pipeline((cmd)->cmd.zunionstore(destKey, params, keys))
				.transaction((cmd)->cmd.zunionstore(destKey, params, keys));
		return execute(command, args);
	}

	@Override
	public long zUnionStore(final byte[] destKey, final byte[][] keys, final Aggregate aggregate){
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("keys", keys)
				.put("aggregate", aggregate);
		final JedisZParams params = new JedisZParams(aggregate);
		final JedisClusterCommand<Long> command = new JedisClusterCommand<Long>(client, ProtocolCommand.ZUNIONSTORE)
				.general((cmd)->cmd.zunionstore(destKey, params, keys))
				.pipeline((cmd)->cmd.zunionstore(destKey, params, keys))
				.transaction((cmd)->cmd.zunionstore(destKey, params, keys));
		return execute(command, args);
	}

	@Override
	public long zUnionStore(final String destKey, final String[] keys, final double... weights){
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("keys", keys)
				.put("weights", weights);
		final JedisZParams params = new JedisZParams(weights);
		final JedisClusterCommand<Long> command = new JedisClusterCommand<Long>(client, ProtocolCommand.ZUNIONSTORE)
				.general((cmd)->cmd.zunionstore(destKey, params, keys))
				.pipeline((cmd)->cmd.zunionstore(destKey, params, keys))
				.transaction((cmd)->cmd.zunionstore(destKey, params, keys));
		return execute(command, args);
	}

	@Override
	public long zUnionStore(final byte[] destKey, final byte[][] keys, final double... weights){
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("keys", keys)
				.put("weights", weights);
		final JedisZParams params = new JedisZParams(weights);
		final JedisClusterCommand<Long> command = new JedisClusterCommand<Long>(client, ProtocolCommand.ZUNIONSTORE)
				.general((cmd)->cmd.zunionstore(destKey, params, keys))
				.pipeline((cmd)->cmd.zunionstore(destKey, params, keys))
				.transaction((cmd)->cmd.zunionstore(destKey, params, keys));
		return execute(command, args);
	}

	@Override
	public long zUnionStore(final String destKey, final String[] keys, final Aggregate aggregate,
							final double... weights){
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("keys", keys)
				.put("aggregate", aggregate).put("weights", weights);
		final JedisZParams params = new JedisZParams(aggregate, weights);
		final JedisClusterCommand<Long> command = new JedisClusterCommand<Long>(client, ProtocolCommand.ZUNIONSTORE)
				.general((cmd)->cmd.zunionstore(destKey, params, keys))
				.pipeline((cmd)->cmd.zunionstore(destKey, params, keys))
				.transaction((cmd)->cmd.zunionstore(destKey, params, keys));
		return execute(command, args);
	}

	@Override
	public long zUnionStore(final byte[] destKey, final byte[][] keys, final Aggregate aggregate,
							final double... weights){
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("keys", keys)
				.put("aggregate", aggregate).put("weights", weights);
		final JedisZParams params = new JedisZParams(aggregate, weights);
		final JedisClusterCommand<Long> command = new JedisClusterCommand<Long>(client, ProtocolCommand.ZUNIONSTORE)
				.general((cmd)->cmd.zunionstore(destKey, params, keys))
				.pipeline((cmd)->cmd.zunionstore(destKey, params, keys))
				.transaction((cmd)->cmd.zunionstore(destKey, params, keys));
		return execute(command, args);
	}

}
