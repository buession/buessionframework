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

import com.buession.redis.client.connection.jedis.JedisConnection;
import com.buession.redis.client.jedis.JedisStandaloneClient;
import com.buession.redis.core.StreamEntry;
import com.buession.redis.core.StreamEntryId;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.internal.convert.jedis.JedisConverters;
import com.buession.redis.core.internal.convert.jedis.StreamEntryIdConverter;
import com.buession.redis.core.internal.convert.jedis.params.XAddArgumentConverter;
import com.buession.redis.core.internal.jedis.JedisXAutoClaimParams;
import com.buession.redis.utils.SafeEncoder;
import redis.clients.jedis.StreamEntryID;
import redis.clients.jedis.params.XAddParams;

import java.util.List;
import java.util.Map;

/**
 * Jedis 单机模式 Stream 命令操作抽象类
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public final class JedisStreamOperations extends AbstractStreamOperations<JedisConnection> {

	public JedisStreamOperations(final JedisStandaloneClient client){
		super(client);
	}

	@Override
	public long xAck(final String key, final String group, final StreamEntryId... ids){
		final CommandArguments args = CommandArguments.create("key", key).put("group", group).put("ids", ids);
		final StreamEntryID[] streamEntryIDS = ids == null ? null : new StreamEntryID[ids.length];

		if(ids != null){
			for(int i = 0; i < ids.length; i++){
				streamEntryIDS[i] = new StreamEntryID(ids[i]);
			}
		}

		final JedisCommand<Long> command = JedisCommand.<Long>create(ProtocolCommand.XACK)
				.general((cmd)->cmd.xack(key, group, streamEntryIDS))
				.pipeline((cmd)->cmd.xack(key, group, streamEntryIDS))
				.transaction((cmd)->cmd.xack(key, group, streamEntryIDS));
		return execute(command, args);
	}

	@Override
	public long xAck(final byte[] key, final byte[] group, final StreamEntryId... ids){
		final CommandArguments args = CommandArguments.create("key", key).put("group", group).put("ids", ids);
		final JedisCommand<Long> command = JedisCommand.<Long>create(ProtocolCommand.XACK)
				.general((cmd)->cmd.xack(key, group, ids)).pipeline((cmd)->cmd.xack(key, group, ids))
				.transaction((cmd)->cmd.xack(key, group, ids));
		return execute(command, args);
	}

	@Override
	public StreamEntryId xAdd(final String key, final String id, final Map<String, String> hash){
		final CommandArguments args = CommandArguments.create("key", key).put("id", id).put("hash", hash);
		final StreamEntryID streamEntryID = new StreamEntryID(id);
		final JedisCommand<StreamEntryId> command = JedisCommand.<StreamEntryId>create(ProtocolCommand.XADD)
				.general((cmd)->cmd.xadd(key, streamEntryID, hash), StreamEntryIdConverter.INSTANCE)
				.pipeline((cmd)->cmd.xadd(key, streamEntryID, hash), StreamEntryIdConverter.INSTANCE)
				.transaction((cmd)->cmd.xadd(key, streamEntryID, hash), StreamEntryIdConverter.INSTANCE);
		return execute(command, args);
	}

	@Override
	public StreamEntryId xAdd(final byte[] key, final byte[] id, final Map<byte[], byte[]> hash){
		return xAdd(SafeEncoder.encode(key), SafeEncoder.encode(id),
				JedisConverters.BINARY_MAP_TO_STRING_MAP_CONVERTER.convert(hash));
	}

	@Override
	public StreamEntryId xAdd(final String key, final String id, final Map<String, String> hash,
							  final XAddArgument xAddArgument){
		final CommandArguments args = CommandArguments.create("key", key).put("id", id).put("hash", hash)
				.put("xAddArgument", xAddArgument);
		final XAddParams params = XAddArgumentConverter.INSTANCE.convert(xAddArgument).id(id);
		final JedisCommand<StreamEntryId> command = JedisCommand.<StreamEntryId>create(ProtocolCommand.XADD)
				.general((cmd)->cmd.xadd(key, hash, params), StreamEntryIdConverter.INSTANCE)
				.pipeline((cmd)->cmd.xadd(key, hash, params), StreamEntryIdConverter.INSTANCE)
				.transaction((cmd)->cmd.xadd(key, hash, params), StreamEntryIdConverter.INSTANCE);
		return execute(command, args);
	}

	@Override
	public StreamEntryId xAdd(final byte[] key, final byte[] id, final Map<byte[], byte[]> hash,
							  final XAddArgument xAddArgument){
		final CommandArguments args = CommandArguments.create("key", key).put("id", id).put("hash", hash)
				.put("xAddArgument", xAddArgument);
		final XAddParams params = XAddArgumentConverter.INSTANCE.convert(xAddArgument).id(id);
		final JedisCommand<StreamEntryId> command = JedisCommand.<StreamEntryId>create(ProtocolCommand.XADD)
				.general((cmd)->cmd.xadd(key, hash, params), JedisConverters.BINARY_STREAM_ENTRY_RESULT_CONVERTER)
				.pipeline((cmd)->cmd.xadd(key, hash, params), JedisConverters.BINARY_STREAM_ENTRY_RESULT_CONVERTER)
				.transaction((cmd)->cmd.xadd(key, hash, params), JedisConverters.BINARY_STREAM_ENTRY_RESULT_CONVERTER);
		return execute(command, args);
	}

	@Override
	public Map<StreamEntryId, List<StreamEntry>> xAutoClaim(final String key, final String group,
															final String consumerName, final int minIdleTime,
															final String start){
		final CommandArguments args = CommandArguments.create("key", key).put("group", group)
				.put("consumerName", consumerName).put("minIdleTime", minIdleTime).put("start", start);
		final StreamEntryID streamEntryID = new StreamEntryID(start);
		final JedisXAutoClaimParams params = new JedisXAutoClaimParams();
		final JedisCommand<Map<StreamEntryId, List<StreamEntry>>> command = JedisCommand.<Map<StreamEntryId, List<StreamEntry>>>create(
						ProtocolCommand.XAUTOCLAIM)
				.general((cmd)->cmd.xautoclaim(key, group, consumerName, minIdleTime, streamEntryID, params),
						JedisConverters.MAP_STREAM_ENTRY_RESULT_CONVERTER)
				.pipeline((cmd)->cmd.xautoclaim(key, group, consumerName, minIdleTime, streamEntryID, params),
						JedisConverters.MAP_STREAM_ENTRY_RESULT_CONVERTER)
				.transaction(
						(cmd)->cmd.xautoclaim(key, group, consumerName, minIdleTime, streamEntryID, params),
						JedisConverters.MAP_STREAM_ENTRY_RESULT_CONVERTER);
		return execute(command, args);
	}

	@Override
	public Map<StreamEntryId, List<StreamEntry>> xAutoClaim(final byte[] key, final byte[] group,
															final byte[] consumerName, final int minIdleTime,
															final byte[] start){
		return xAutoClaim(SafeEncoder.encode(key), SafeEncoder.encode(group), SafeEncoder.encode(consumerName),
				minIdleTime, SafeEncoder.encode(start));
	}

	@Override
	public Map<StreamEntryId, List<StreamEntry>> xAutoClaim(final String key, final String group,
															final String consumerName, final int minIdleTime,
															final String start, final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("group", group)
				.put("consumerName", consumerName).put("minIdleTime", minIdleTime).put("start", start)
				.put("count", count);
		final StreamEntryID streamEntryID = new StreamEntryID(start);
		final JedisXAutoClaimParams params = new JedisXAutoClaimParams(count);
		final JedisCommand<Map<StreamEntryId, List<StreamEntry>>> command = JedisCommand.<Map<StreamEntryId, List<StreamEntry>>>create(
						ProtocolCommand.XAUTOCLAIM)
				.general((cmd)->cmd.xautoclaim(key, group, consumerName, minIdleTime, streamEntryID, params),
						JedisConverters.MAP_STREAM_ENTRY_RESULT_CONVERTER)
				.pipeline((cmd)->cmd.xautoclaim(key, group, consumerName, minIdleTime, streamEntryID, params),
						JedisConverters.MAP_STREAM_ENTRY_RESULT_CONVERTER)
				.transaction(
						(cmd)->cmd.xautoclaim(key, group, consumerName, minIdleTime, streamEntryID, params),
						JedisConverters.MAP_STREAM_ENTRY_RESULT_CONVERTER);
		return execute(command, args);
	}

	@Override
	public Map<StreamEntryId, List<StreamEntry>> xAutoClaim(final byte[] key, final byte[] group,
															final byte[] consumerName, final int minIdleTime,
															final byte[] start, final long count){
		return xAutoClaim(SafeEncoder.encode(key), SafeEncoder.encode(group), SafeEncoder.encode(consumerName),
				minIdleTime, SafeEncoder.encode(start), count);
	}

	@Override
	public Map<StreamEntryId, List<StreamEntryId>> xAutoClaimJustId(final String key, final String group,
																	final String consumerName, final int minIdleTime,
																	final String start){
		final CommandArguments args = CommandArguments.create("key", key).put("group", group)
				.put("consumerName", consumerName).put("minIdleTime", minIdleTime).put("start", start)
				.put("count");
		final StreamEntryID streamEntryID = new StreamEntryID(start);
		final JedisXAutoClaimParams params = new JedisXAutoClaimParams();
		final JedisCommand<Map<StreamEntryId, List<StreamEntryId>>> command = JedisCommand.<Map<StreamEntryId, List<StreamEntryId>>>create(
						ProtocolCommand.XAUTOCLAIM)
				.general((cmd)->cmd.xautoclaimJustId(key, group, consumerName, minIdleTime, streamEntryID, params),
						JedisConverters.MAP_STREAM_ENTRY_ID_RESULT_CONVERTER)
				.pipeline((cmd)->cmd.xautoclaimJustId(key, group, consumerName, minIdleTime, streamEntryID, params),
						JedisConverters.MAP_STREAM_ENTRY_ID_RESULT_CONVERTER)
				.transaction(
						(cmd)->cmd.xautoclaimJustId(key, group, consumerName, minIdleTime, streamEntryID, params),
						JedisConverters.MAP_STREAM_ENTRY_ID_RESULT_CONVERTER);
		return execute(command, args);
	}

	@Override
	public Map<StreamEntryId, List<StreamEntryId>> xAutoClaimJustId(final byte[] key, final byte[] group,
																	final byte[] consumerName, final int minIdleTime,
																	final byte[] start){
		return xAutoClaimJustId(SafeEncoder.encode(key), SafeEncoder.encode(group), SafeEncoder.encode(consumerName),
				minIdleTime, SafeEncoder.encode(start));
	}

	@Override
	public Map<StreamEntryId, List<StreamEntryId>> xAutoClaimJustId(final String key, final String group,
																	final String consumerName, final int minIdleTime,
																	final String start, final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("group", group)
				.put("consumerName", consumerName).put("minIdleTime", minIdleTime).put("start", start)
				.put("count", count);
		final StreamEntryID streamEntryID = new StreamEntryID(start);
		final JedisXAutoClaimParams params = new JedisXAutoClaimParams(count);
		final JedisCommand<Map<StreamEntryId, List<StreamEntryId>>> command = JedisCommand.<Map<StreamEntryId, List<StreamEntryId>>>create(
						ProtocolCommand.XAUTOCLAIM)
				.general((cmd)->cmd.xautoclaimJustId(key, group, consumerName, minIdleTime, streamEntryID, params),
						JedisConverters.MAP_STREAM_ENTRY_ID_RESULT_CONVERTER)
				.pipeline((cmd)->cmd.xautoclaimJustId(key, group, consumerName, minIdleTime, streamEntryID, params),
						JedisConverters.MAP_STREAM_ENTRY_ID_RESULT_CONVERTER)
				.transaction(
						(cmd)->cmd.xautoclaimJustId(key, group, consumerName, minIdleTime, streamEntryID, params),
						JedisConverters.MAP_STREAM_ENTRY_ID_RESULT_CONVERTER);
		return execute(command, args);
	}

	@Override
	public Map<StreamEntryId, List<StreamEntryId>> xAutoClaimJustId(final byte[] key, final byte[] group,
																	final byte[] consumerName, final int minIdleTime,
																	final byte[] start, final long count){
		return xAutoClaimJustId(SafeEncoder.encode(key), SafeEncoder.encode(group), SafeEncoder.encode(consumerName),
				minIdleTime, SafeEncoder.encode(start), count);
	}

	@Override
	public List<StreamEntry> xClaim(final String key, final String group, final String consumerName,
									final int minIdleTime, final String... ids){
		final CommandArguments args = CommandArguments.create("key", key).put("group", group)
				.put("consumerName", consumerName).put("minIdleTime", minIdleTime).put("ids", ids);
		final StreamEntryID streamEntryID = new StreamEntryID(start);
		final JedisXAutoClaimParams params = new JedisXAutoClaimParams(count);
		final JedisCommand<Map<StreamEntryId, List<StreamEntryId>>> command = JedisCommand.<Map<StreamEntryId, List<StreamEntryId>>>create(
						ProtocolCommand.XAUTOCLAIM)
				.general((cmd)->cmd.xclaim(key, group, consumerName, minIdleTime, params, streamEntryID),
						JedisConverters.MAP_STREAM_ENTRY_ID_RESULT_CONVERTER)
				.pipeline((cmd)->cmd.xclaim(key, group, consumerName, minIdleTime, params, streamEntryID),
						JedisConverters.MAP_STREAM_ENTRY_ID_RESULT_CONVERTER)
				.transaction(
						(cmd)->cmd.xclaim(key, group, consumerName, minIdleTime, params, streamEntryID),
						JedisConverters.MAP_STREAM_ENTRY_ID_RESULT_CONVERTER);
		return execute(command, args);
	}

	@Override
	public List<StreamEntry> xClaim(final byte[] key, final byte[] group, final byte[] consumerName,
									final int minIdleTime, final byte[]... ids){
		return null;
	}
}
