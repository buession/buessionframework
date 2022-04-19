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

import com.buession.lang.Status;
import com.buession.redis.client.connection.jedis.JedisConnection;
import com.buession.redis.client.jedis.JedisStandaloneClient;
import com.buession.redis.core.StreamConsumer;
import com.buession.redis.core.StreamEntry;
import com.buession.redis.core.StreamEntryId;
import com.buession.redis.core.StreamGroup;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.internal.convert.Converters;
import com.buession.redis.core.internal.convert.jedis.params.StreamEntryIdConverter;
import com.buession.redis.core.internal.convert.jedis.params.XClaimArgumentConverter;
import com.buession.redis.core.internal.convert.jedis.response.StreamConsumersInfoConverter;
import com.buession.redis.core.internal.convert.jedis.response.StreamEntryConverter;
import com.buession.redis.core.internal.convert.jedis.response.StreamEntryIDConverter;
import com.buession.redis.core.internal.convert.jedis.params.XAddArgumentConverter;
import com.buession.redis.core.internal.convert.jedis.response.StreamGroupInfoConverter;
import com.buession.redis.core.internal.convert.response.OkStatusConverter;
import com.buession.redis.core.internal.jedis.JedisXAutoClaimParams;
import com.buession.redis.utils.SafeEncoder;
import redis.clients.jedis.StreamEntryID;
import redis.clients.jedis.params.XAddParams;
import redis.clients.jedis.params.XClaimParams;

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
		final StreamEntryID[] streamEntryIDS = StreamEntryIdConverter.ARRAY_CONVERTER.convert(ids);

		final JedisCommand<Long> command = JedisCommand.<Long>create(ProtocolCommand.XACK)
				.general((cmd)->cmd.xack(key, group, streamEntryIDS))
				.pipeline((cmd)->cmd.xack(key, group, streamEntryIDS))
				.transaction((cmd)->cmd.xack(key, group, streamEntryIDS));
		return execute(command, args);
	}

	@Override
	public long xAck(final byte[] key, final byte[] group, final StreamEntryId... ids){
		return xAck(SafeEncoder.encode(key), SafeEncoder.encode(group), ids);
	}

	@Override
	public StreamEntryId xAdd(final String key, final String id, final Map<String, String> hash){
		final CommandArguments args = CommandArguments.create("key", key).put("id", id).put("hash", hash);
		final StreamEntryID streamEntryID = new StreamEntryID(id);
		final JedisCommand<StreamEntryId> command = JedisCommand.<StreamEntryId>create(ProtocolCommand.XADD)
				.general((cmd)->cmd.xadd(key, streamEntryID, hash), StreamEntryIDConverter.INSTANCE)
				.pipeline((cmd)->cmd.xadd(key, streamEntryID, hash), StreamEntryIDConverter.INSTANCE)
				.transaction((cmd)->cmd.xadd(key, streamEntryID, hash), StreamEntryIDConverter.INSTANCE);
		return execute(command, args);
	}

	@Override
	public StreamEntryId xAdd(final byte[] key, final byte[] id, final Map<byte[], byte[]> hash){
		return xAdd(SafeEncoder.encode(key), SafeEncoder.encode(id),
				Converters.BINARY_MAP_TO_STRING_MAP_CONVERTER.convert(hash));
	}

	@Override
	public StreamEntryId xAdd(final String key, final String id, final Map<String, String> hash,
							  final XAddArgument xAddArgument){
		final CommandArguments args = CommandArguments.create("key", key).put("id", id).put("hash", hash)
				.put("xAddArgument", xAddArgument);
		final XAddParams params = XAddArgumentConverter.INSTANCE.convert(xAddArgument).id(id);
		final JedisCommand<StreamEntryId> command = JedisCommand.<StreamEntryId>create(ProtocolCommand.XADD)
				.general((cmd)->cmd.xadd(key, hash, params), StreamEntryIDConverter.INSTANCE)
				.pipeline((cmd)->cmd.xadd(key, hash, params), StreamEntryIDConverter.INSTANCE)
				.transaction((cmd)->cmd.xadd(key, hash, params), StreamEntryIDConverter.INSTANCE);
		return execute(command, args);
	}

	@Override
	public StreamEntryId xAdd(final byte[] key, final byte[] id, final Map<byte[], byte[]> hash,
							  final XAddArgument xAddArgument){
		final CommandArguments args = CommandArguments.create("key", key).put("id", id).put("hash", hash)
				.put("xAddArgument", xAddArgument);
		final XAddParams params = XAddArgumentConverter.INSTANCE.convert(xAddArgument).id(id);
		final JedisCommand<StreamEntryId> command = JedisCommand.<StreamEntryId>create(ProtocolCommand.XADD)
				.general((cmd)->cmd.xadd(key, hash, params),
						StreamEntryIDConverter.BinaryStreamEntryIdConverter.INSTANCE)
				.pipeline((cmd)->cmd.xadd(key, hash, params),
						StreamEntryIDConverter.BinaryStreamEntryIdConverter.INSTANCE)
				.transaction((cmd)->cmd.xadd(key, hash, params),
						StreamEntryIDConverter.BinaryStreamEntryIdConverter.INSTANCE);
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
						StreamEntryConverter.MapStreamEntryConverter.INSTANCE)
				.pipeline((cmd)->cmd.xautoclaim(key, group, consumerName, minIdleTime, streamEntryID, params),
						StreamEntryConverter.MapStreamEntryConverter.INSTANCE)
				.transaction(
						(cmd)->cmd.xautoclaim(key, group, consumerName, minIdleTime, streamEntryID, params),
						StreamEntryConverter.MapStreamEntryConverter.INSTANCE);
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
						StreamEntryConverter.MapStreamEntryConverter.INSTANCE)
				.pipeline((cmd)->cmd.xautoclaim(key, group, consumerName, minIdleTime, streamEntryID, params),
						StreamEntryConverter.MapStreamEntryConverter.INSTANCE)
				.transaction(
						(cmd)->cmd.xautoclaim(key, group, consumerName, minIdleTime, streamEntryID, params),
						StreamEntryConverter.MapStreamEntryConverter.INSTANCE);
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
						StreamEntryIDConverter.MapStreamEntryIdConverter.INSTANCE)
				.pipeline((cmd)->cmd.xautoclaimJustId(key, group, consumerName, minIdleTime, streamEntryID, params),
						StreamEntryIDConverter.MapStreamEntryIdConverter.INSTANCE)
				.transaction(
						(cmd)->cmd.xautoclaimJustId(key, group, consumerName, minIdleTime, streamEntryID, params),
						StreamEntryIDConverter.MapStreamEntryIdConverter.INSTANCE);
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
						StreamEntryIDConverter.MapStreamEntryIdConverter.INSTANCE)
				.pipeline((cmd)->cmd.xautoclaimJustId(key, group, consumerName, minIdleTime, streamEntryID, params),
						StreamEntryIDConverter.MapStreamEntryIdConverter.INSTANCE)
				.transaction(
						(cmd)->cmd.xautoclaimJustId(key, group, consumerName, minIdleTime, streamEntryID, params),
						StreamEntryIDConverter.MapStreamEntryIdConverter.INSTANCE);
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
									final int minIdleTime, final StreamEntryId... ids){
		final CommandArguments args = CommandArguments.create("key", key).put("group", group)
				.put("consumerName", consumerName).put("minIdleTime", minIdleTime).put("ids", ids);
		final XClaimParams params = new XClaimParams();
		final StreamEntryID[] streamEntryIDs = StreamEntryIdConverter.ARRAY_CONVERTER.convert(ids);
		final JedisCommand<List<StreamEntry>> command = JedisCommand.<List<StreamEntry>>create(
						ProtocolCommand.XCLAIM)
				.general((cmd)->cmd.xclaim(key, group, consumerName, minIdleTime, params, streamEntryIDs),
						StreamEntryConverter.LIST_CONVERTER)
				.pipeline((cmd)->cmd.xclaim(key, group, consumerName, minIdleTime, params, streamEntryIDs),
						StreamEntryConverter.LIST_CONVERTER)
				.transaction(
						(cmd)->cmd.xclaim(key, group, consumerName, minIdleTime, params, streamEntryIDs),
						StreamEntryConverter.LIST_CONVERTER);
		return execute(command, args);
	}

	@Override
	public List<StreamEntry> xClaim(final byte[] key, final byte[] group, final byte[] consumerName,
									final int minIdleTime, final StreamEntryId... ids){
		return xClaim(SafeEncoder.encode(key), SafeEncoder.encode(group), SafeEncoder.encode(consumerName), minIdleTime,
				ids);
	}

	@Override
	public List<StreamEntry> xClaim(final String key, final String group, final String consumerName,
									final int minIdleTime, final StreamEntryId[] ids,
									final XClaimArgument xClaimArgument){
		final CommandArguments args = CommandArguments.create("key", key).put("group", group)
				.put("consumerName", consumerName).put("minIdleTime", minIdleTime).put("ids", ids)
				.put("xClaimArgument", xClaimArgument);
		final XClaimParams params = XClaimArgumentConverter.INSTANCE.convert(xClaimArgument);
		final StreamEntryID[] streamEntryIDs = StreamEntryIdConverter.ARRAY_CONVERTER.convert(ids);
		final JedisCommand<List<StreamEntry>> command = JedisCommand.<List<StreamEntry>>create(
						ProtocolCommand.XCLAIM)
				.general((cmd)->cmd.xclaim(key, group, consumerName, minIdleTime, params, streamEntryIDs),
						StreamEntryConverter.LIST_CONVERTER)
				.pipeline((cmd)->cmd.xclaim(key, group, consumerName, minIdleTime, params, streamEntryIDs),
						StreamEntryConverter.LIST_CONVERTER)
				.transaction(
						(cmd)->cmd.xclaim(key, group, consumerName, minIdleTime, params, streamEntryIDs),
						StreamEntryConverter.LIST_CONVERTER);
		return execute(command, args);
	}

	@Override
	public List<StreamEntry> xClaim(final byte[] key, final byte[] group, final byte[] consumerName,
									final int minIdleTime, final StreamEntryId[] ids,
									final XClaimArgument xClaimArgument){
		return xClaim(SafeEncoder.encode(key), SafeEncoder.encode(group), SafeEncoder.encode(consumerName), minIdleTime,
				ids, xClaimArgument);
	}

	@Override
	public List<StreamEntryId> xClaimJustId(final String key, final String group, final String consumerName,
											final int minIdleTime, final StreamEntryId... ids){
		final CommandArguments args = CommandArguments.create("key", key).put("group", group)
				.put("consumerName", consumerName).put("minIdleTime", minIdleTime).put("ids", ids);
		final XClaimParams params = new XClaimParams();
		final StreamEntryID[] streamEntryIDs = StreamEntryIdConverter.ARRAY_CONVERTER.convert(ids);
		final JedisCommand<List<StreamEntryId>> command = JedisCommand.<List<StreamEntryId>>create(
						ProtocolCommand.XCLAIM)
				.general((cmd)->cmd.xclaimJustId(key, group, consumerName, minIdleTime, params, streamEntryIDs),
						StreamEntryIDConverter.LIST_CONVERTER)
				.pipeline((cmd)->cmd.xclaimJustId(key, group, consumerName, minIdleTime, params, streamEntryIDs),
						StreamEntryIDConverter.LIST_CONVERTER)
				.transaction(
						(cmd)->cmd.xclaimJustId(key, group, consumerName, minIdleTime, params, streamEntryIDs),
						StreamEntryIDConverter.LIST_CONVERTER);
		return execute(command, args);
	}

	@Override
	public List<StreamEntryId> xClaimJustId(final byte[] key, final byte[] group, final byte[] consumerName,
											final int minIdleTime, final StreamEntryId... ids){
		return xClaimJustId(SafeEncoder.encode(key), SafeEncoder.encode(group), SafeEncoder.encode(consumerName),
				minIdleTime,
				ids);
	}

	@Override
	public List<StreamEntryId> xClaimJustId(final String key, final String group, final String consumerName,
											final int minIdleTime, final StreamEntryId[] ids,
											final XClaimArgument xClaimArgument){
		final CommandArguments args = CommandArguments.create("key", key).put("group", group)
				.put("consumerName", consumerName).put("minIdleTime", minIdleTime).put("ids", ids)
				.put("xClaimArgument", xClaimArgument);
		final XClaimParams params = XClaimArgumentConverter.INSTANCE.convert(xClaimArgument);
		final StreamEntryID[] streamEntryIDs = StreamEntryIdConverter.ARRAY_CONVERTER.convert(ids);
		final JedisCommand<List<StreamEntryId>> command = JedisCommand.<List<StreamEntryId>>create(
						ProtocolCommand.XCLAIM)
				.general((cmd)->cmd.xclaimJustId(key, group, consumerName, minIdleTime, params, streamEntryIDs),
						StreamEntryIDConverter.LIST_CONVERTER)
				.pipeline((cmd)->cmd.xclaimJustId(key, group, consumerName, minIdleTime, params, streamEntryIDs),
						StreamEntryIDConverter.LIST_CONVERTER)
				.transaction(
						(cmd)->cmd.xclaimJustId(key, group, consumerName, minIdleTime, params, streamEntryIDs),
						StreamEntryIDConverter.LIST_CONVERTER);
		return execute(command, args);
	}

	@Override
	public List<StreamEntryId> xClaimJustId(final byte[] key, final byte[] group, final byte[] consumerName,
											final int minIdleTime, final StreamEntryId[] ids,
											final XClaimArgument xClaimArgument){
		return xClaimJustId(SafeEncoder.encode(key), SafeEncoder.encode(group), SafeEncoder.encode(consumerName),
				minIdleTime, ids, xClaimArgument);
	}

	@Override
	public long xDel(final String key, final StreamEntryId... ids){
		final CommandArguments args = CommandArguments.create("key", key).put("ids", ids);
		final StreamEntryID[] streamEntryIDs = StreamEntryIdConverter.ARRAY_CONVERTER.convert(ids);
		final JedisCommand<Long> command = JedisCommand.<Long>create(ProtocolCommand.XDEL)
				.general((cmd)->cmd.xdel(key, streamEntryIDs)).pipeline((cmd)->cmd.xdel(key, streamEntryIDs))
				.transaction((cmd)->cmd.xdel(key, streamEntryIDs));
		return execute(command, args);
	}

	@Override
	public long xDel(final byte[] key, final StreamEntryId... ids){
		return xDel(SafeEncoder.encode(key), ids);
	}

	@Override
	public Status xGroupCreate(final String key, final String groupName, final StreamEntryId id,
							   final boolean makeStream){
		final CommandArguments args = CommandArguments.create("key", key).put("groupName", groupName)
				.put("id", id);
		final StreamEntryID streamEntryID = StreamEntryIdConverter.INSTANCE.convert(id);
		final JedisCommand<Status> command = JedisCommand.<Status>create(ProtocolCommand.XGROUP_CREATE)
				.general((cmd)->cmd.xgroupCreate(key, groupName, streamEntryID, makeStream), OkStatusConverter.INSTANCE)
				.pipeline((cmd)->cmd.xgroupCreate(key, groupName, streamEntryID, makeStream),
						OkStatusConverter.INSTANCE)
				.transaction((cmd)->cmd.xgroupCreate(key, groupName, streamEntryID, makeStream),
						OkStatusConverter.INSTANCE);
		return execute(command, args);
	}

	@Override
	public Status xGroupCreate(final byte[] key, final byte[] groupName, final StreamEntryId id,
							   final boolean makeStream){
		return xGroupCreate(SafeEncoder.encode(key), SafeEncoder.encode(groupName), id, makeStream);
	}

	@Override
	public Status xGroupCreateConsumer(final String key, final String groupName, final String consumerName){
		final CommandArguments args = CommandArguments.create("key", key).put("groupName", groupName)
				.put("consumerName", consumerName);
		final JedisCommand<Status> command = JedisCommand.<Status>create(ProtocolCommand.XGROUP_CREATECONSUMER)
				.general((cmd)->cmd.xgroupCreateConsumer(key, groupName, consumerName),
						Converters.BOOLEAN_STATUS_CONVERTER)
				.pipeline((cmd)->cmd.xgroupCreateConsumer(key, groupName, consumerName),
						Converters.BOOLEAN_STATUS_CONVERTER)
				.transaction((cmd)->cmd.xgroupCreateConsumer(key, groupName, consumerName),
						Converters.BOOLEAN_STATUS_CONVERTER);
		return execute(command, args);
	}

	@Override
	public Status xGroupCreateConsumer(final byte[] key, final byte[] groupName, final byte[] consumerName){
		final CommandArguments args = CommandArguments.create("key", key).put("groupName", groupName)
				.put("consumerName", consumerName);
		final JedisCommand<Status> command = JedisCommand.<Status>create(ProtocolCommand.XGROUP_CREATECONSUMER)
				.general((cmd)->cmd.xgroupCreateConsumer(key, groupName, consumerName),
						Converters.BOOLEAN_STATUS_CONVERTER)
				.pipeline((cmd)->cmd.xgroupCreateConsumer(key, groupName, consumerName),
						Converters.BOOLEAN_STATUS_CONVERTER)
				.transaction((cmd)->cmd.xgroupCreateConsumer(key, groupName, consumerName),
						Converters.BOOLEAN_STATUS_CONVERTER);
		return execute(command, args);
	}

	@Override
	public long xGroupDelConsumer(final String key, final String groupName, final String consumerName){
		final CommandArguments args = CommandArguments.create("key", key).put("groupName", groupName)
				.put("consumerName", consumerName);
		final JedisCommand<Long> command = JedisCommand.<Long>create(ProtocolCommand.XGROUP_DELCONSUMER)
				.general((cmd)->cmd.xgroupDelConsumer(key, groupName, consumerName))
				.pipeline((cmd)->cmd.xgroupDelConsumer(key, groupName, consumerName))
				.transaction((cmd)->cmd.xgroupDelConsumer(key, groupName, consumerName));
		return execute(command, args);
	}

	@Override
	public long xGroupDelConsumer(final byte[] key, final byte[] groupName, final byte[] consumerName){
		final CommandArguments args = CommandArguments.create("key", key).put("groupName", groupName)
				.put("consumerName", consumerName);
		final JedisCommand<Long> command = JedisCommand.<Long>create(ProtocolCommand.XGROUP_DELCONSUMER)
				.general((cmd)->cmd.xgroupDelConsumer(key, groupName, consumerName))
				.pipeline((cmd)->cmd.xgroupDelConsumer(key, groupName, consumerName))
				.transaction((cmd)->cmd.xgroupDelConsumer(key, groupName, consumerName));
		return execute(command, args);
	}

	@Override
	public Status xGroupDestroy(final String key, final String groupName){
		final CommandArguments args = CommandArguments.create("key", key).put("groupName", groupName);
		final JedisCommand<Status> command = JedisCommand.<Status>create(ProtocolCommand.XGROUP_DESTROY)
				.general((cmd)->cmd.xgroupDestroy(key, groupName), Converters.ONE_STATUS_CONVERTER)
				.pipeline((cmd)->cmd.xgroupDestroy(key, groupName), Converters.ONE_STATUS_CONVERTER)
				.transaction((cmd)->cmd.xgroupDestroy(key, groupName), Converters.ONE_STATUS_CONVERTER);
		return execute(command, args);
	}

	@Override
	public Status xGroupDestroy(final byte[] key, final byte[] groupName){
		final CommandArguments args = CommandArguments.create("key", key).put("groupName", groupName);
		final JedisCommand<Status> command = JedisCommand.<Status>create(ProtocolCommand.XGROUP_DESTROY)
				.general((cmd)->cmd.xgroupDestroy(key, groupName), Converters.ONE_STATUS_CONVERTER)
				.pipeline((cmd)->cmd.xgroupDestroy(key, groupName), Converters.ONE_STATUS_CONVERTER)
				.transaction((cmd)->cmd.xgroupDestroy(key, groupName), Converters.ONE_STATUS_CONVERTER);
		return execute(command, args);
	}

	@Override
	public Status xGroupSetId(final String key, final String groupName, final StreamEntryId id){
		final CommandArguments args = CommandArguments.create("key", key).put("groupName", groupName).put("id", id);
		final StreamEntryID streamEntryID = StreamEntryIdConverter.INSTANCE.convert(id);
		final JedisCommand<Status> command = JedisCommand.<Status>create(ProtocolCommand.XGROUP_SETID)
				.general((cmd)->cmd.xgroupSetID(key, groupName, streamEntryID), OkStatusConverter.INSTANCE)
				.pipeline((cmd)->cmd.xgroupSetID(key, groupName, streamEntryID), OkStatusConverter.INSTANCE)
				.transaction((cmd)->cmd.xgroupSetID(key, groupName, streamEntryID), OkStatusConverter.INSTANCE);
		return execute(command, args);
	}

	@Override
	public Status xGroupSetId(final byte[] key, final byte[] groupName, final StreamEntryId id){
		return xGroupSetId(SafeEncoder.encode(key), SafeEncoder.encode(groupName), id);
	}

	@Override
	public List<StreamConsumer> xInfoConsumers(final String key, final String groupName){
		final CommandArguments args = CommandArguments.create("key", key).put("groupName", groupName);
		final JedisCommand<List<StreamConsumer>> command = JedisCommand.<List<StreamConsumer>>create(
						ProtocolCommand.XINFO_CONSUMERS)
				.general((cmd)->cmd.xinfoConsumers(key, groupName), StreamConsumersInfoConverter.LIST_CONVERTER)
				.pipeline((cmd)->cmd.xinfoConsumers(key, groupName), StreamConsumersInfoConverter.LIST_CONVERTER)
				.transaction((cmd)->cmd.xinfoConsumers(key, groupName), StreamConsumersInfoConverter.LIST_CONVERTER);
		return execute(command, args);
	}

	@Override
	public List<StreamConsumer> xInfoConsumers(final byte[] key, final byte[] groupName){
		return xInfoConsumers(SafeEncoder.encode(key), SafeEncoder.encode(groupName));
	}

	@Override
	public List<StreamGroup> xInfoGroups(final String key){
		final CommandArguments args = CommandArguments.create("key", key);
		final JedisCommand<List<StreamGroup>> command = JedisCommand.<List<StreamGroup>>create(
						ProtocolCommand.XINFO_GROUPS)
				.general((cmd)->cmd.xinfoGroups(key), StreamGroupInfoConverter.LIST_CONVERTER)
				.pipeline((cmd)->cmd.xinfoGroups(key), StreamGroupInfoConverter.LIST_CONVERTER)
				.transaction((cmd)->cmd.xinfoGroups(key), StreamGroupInfoConverter.LIST_CONVERTER);
		return execute(command, args);
	}

	@Override
	public List<StreamGroup> xInfoGroups(final byte[] key){
		return xInfoGroups(SafeEncoder.encode(key));
	}
	
}
