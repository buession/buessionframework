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
 * | Copyright @ 2013-2023 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.client.jedis.operations;

import com.buession.lang.Status;
import com.buession.redis.client.jedis.JedisClusterClient;
import com.buession.redis.core.Stream;
import com.buession.redis.core.StreamConsumer;
import com.buession.redis.core.StreamEntry;
import com.buession.redis.core.StreamEntryId;
import com.buession.redis.core.StreamFull;
import com.buession.redis.core.StreamGroup;
import com.buession.redis.core.StreamPending;
import com.buession.redis.core.StreamPendingSummary;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.internal.convert.Converters;
import com.buession.redis.core.internal.convert.jedis.params.StreamEntryIdConverter;
import com.buession.redis.core.internal.convert.jedis.params.XAddArgumentConverter;
import com.buession.redis.core.internal.convert.jedis.params.XClaimArgumentConverter;
import com.buession.redis.core.internal.convert.jedis.params.XTrimArgumentConverter;
import com.buession.redis.core.internal.convert.jedis.response.StreamConsumersInfoConverter;
import com.buession.redis.core.internal.convert.jedis.response.StreamEntryConverter;
import com.buession.redis.core.internal.convert.jedis.response.StreamEntryIDConverter;
import com.buession.redis.core.internal.convert.jedis.response.StreamFullInfoConverter;
import com.buession.redis.core.internal.convert.jedis.response.StreamGroupInfoConverter;
import com.buession.redis.core.internal.convert.jedis.response.StreamInfoConverter;
import com.buession.redis.core.internal.convert.jedis.response.StreamPendingEntryConverter;
import com.buession.redis.core.internal.convert.jedis.response.StreamPendingSummaryConverter;
import com.buession.redis.core.internal.convert.response.OkStatusConverter;
import com.buession.redis.core.internal.jedis.JedisXAutoClaimParams;
import com.buession.redis.core.internal.jedis.JedisXPendingParams;
import com.buession.redis.core.internal.jedis.JedisXReadGroupParams;
import com.buession.redis.core.internal.jedis.JedisXReadParams;
import redis.clients.jedis.StreamEntryID;
import redis.clients.jedis.params.XAddParams;
import redis.clients.jedis.params.XClaimParams;
import redis.clients.jedis.params.XTrimParams;

import java.util.List;
import java.util.Map;

/**
 * Jedis 集群模式 Stream 命令操作抽象类
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public final class JedisClusterStreamOperations extends AbstractStreamOperations<JedisClusterClient> {

	public JedisClusterStreamOperations(final JedisClusterClient client){
		super(client);
	}

	@Override
	public Long xAck(final String key, final String groupName, final StreamEntryId... ids){
		final CommandArguments args = CommandArguments.create("key", key).put("groupName", groupName)
				.put("ids", (Object[]) ids);
		final StreamEntryID[] streamEntryIDS = StreamEntryIdConverter.ARRAY_CONVERTER.convert(ids);
		return new JedisClusterCommand<Long>(client, ProtocolCommand.XACK)
				.general((cmd)->cmd.xack(key, groupName, streamEntryIDS))
				.pipeline((cmd)->cmd.xack(key, groupName, streamEntryIDS))
				.transaction((cmd)->cmd.xack(key, groupName, streamEntryIDS))
				.run(args);
	}

	@Override
	public StreamEntryId xAdd(final String key, final StreamEntryId id, final Map<String, String> hash){
		final CommandArguments args = CommandArguments.create("key", key).put("id", id).put("hash", hash);
		final StreamEntryID streamEntryID = StreamEntryIdConverter.INSTANCE.convert(id);
		return new JedisClusterCommand<StreamEntryId>(client, ProtocolCommand.XADD)
				.general((cmd)->cmd.xadd(key, streamEntryID, hash), StreamEntryIDConverter.INSTANCE)
				.pipeline((cmd)->cmd.xadd(key, streamEntryID, hash), StreamEntryIDConverter.INSTANCE)
				.transaction((cmd)->cmd.xadd(key, streamEntryID, hash), StreamEntryIDConverter.INSTANCE)
				.run(args);
	}

	@Override
	public StreamEntryId xAdd(final String key, final StreamEntryId id, final Map<String, String> hash,
							  final XAddArgument xAddArgument){
		final CommandArguments args = CommandArguments.create("key", key).put("id", id).put("hash", hash)
				.put("xAddArgument", xAddArgument);
		final StreamEntryID streamEntryID = StreamEntryIdConverter.INSTANCE.convert(id);
		final XAddParams params = XAddArgumentConverter.INSTANCE.convert(xAddArgument).id(streamEntryID);
		return new JedisClusterCommand<StreamEntryId>(client, ProtocolCommand.XADD)
				.general((cmd)->cmd.xadd(key, hash, params), StreamEntryIDConverter.INSTANCE)
				.pipeline((cmd)->cmd.xadd(key, hash, params), StreamEntryIDConverter.INSTANCE)
				.transaction((cmd)->cmd.xadd(key, hash, params), StreamEntryIDConverter.INSTANCE)
				.run(args);
	}

	@Override
	public StreamEntryId xAdd(final byte[] key, final StreamEntryId id, final Map<byte[], byte[]> hash,
							  final XAddArgument xAddArgument){
		final CommandArguments args = CommandArguments.create("key", key).put("id", id).put("hash", hash)
				.put("xAddArgument", xAddArgument);
		final StreamEntryID streamEntryID = StreamEntryIdConverter.INSTANCE.convert(id);
		final XAddParams params = XAddArgumentConverter.INSTANCE.convert(xAddArgument).id(streamEntryID);
		return new JedisClusterCommand<StreamEntryId>(client, ProtocolCommand.XADD)
				.general((cmd)->cmd.xadd(key, hash, params),
						StreamEntryIDConverter.BinaryStreamEntryIdConverter.INSTANCE)
				.pipeline((cmd)->cmd.xadd(key, hash, params),
						StreamEntryIDConverter.BinaryStreamEntryIdConverter.INSTANCE)
				.transaction((cmd)->cmd.xadd(key, hash, params),
						StreamEntryIDConverter.BinaryStreamEntryIdConverter.INSTANCE)
				.run(args);
	}

	@Override
	public Map<StreamEntryId, List<StreamEntry>> xAutoClaim(final String key, final String groupName,
															final String consumerName, final int minIdleTime,
															final StreamEntryId start){
		final CommandArguments args = CommandArguments.create("key", key).put("groupName", groupName)
				.put("consumerName", consumerName).put("minIdleTime", minIdleTime).put("start", start);
		final StreamEntryID streamEntryID = StreamEntryIdConverter.INSTANCE.convert(start);
		final JedisXAutoClaimParams params = new JedisXAutoClaimParams();
		return new JedisClusterCommand<Map<StreamEntryId, List<StreamEntry>>>(client, ProtocolCommand.XAUTOCLAIM)
				.general((cmd)->cmd.xautoclaim(key, groupName, consumerName, minIdleTime, streamEntryID, params),
						StreamEntryConverter.MapStreamEntryConverter.STREAMENTRYID_KEY_MAP_CONVERTER)
				.pipeline((cmd)->cmd.xautoclaim(key, groupName, consumerName, minIdleTime, streamEntryID, params),
						StreamEntryConverter.MapStreamEntryConverter.STREAMENTRYID_KEY_MAP_CONVERTER)
				.transaction(
						(cmd)->cmd.xautoclaim(key, groupName, consumerName, minIdleTime, streamEntryID, params),
						StreamEntryConverter.MapStreamEntryConverter.STREAMENTRYID_KEY_MAP_CONVERTER)
				.run(args);
	}

	@Override
	public Map<StreamEntryId, List<StreamEntry>> xAutoClaim(final String key, final String groupName,
															final String consumerName, final int minIdleTime,
															final StreamEntryId start, final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("groupName", groupName)
				.put("consumerName", consumerName).put("minIdleTime", minIdleTime).put("start", start)
				.put("count", count);
		final StreamEntryID streamEntryID = StreamEntryIdConverter.INSTANCE.convert(start);
		final JedisXAutoClaimParams params = new JedisXAutoClaimParams(count);
		return new JedisClusterCommand<Map<StreamEntryId, List<StreamEntry>>>(client, ProtocolCommand.XAUTOCLAIM)
				.general((cmd)->cmd.xautoclaim(key, groupName, consumerName, minIdleTime, streamEntryID, params),
						StreamEntryConverter.MapStreamEntryConverter.STREAMENTRYID_KEY_MAP_CONVERTER)
				.pipeline((cmd)->cmd.xautoclaim(key, groupName, consumerName, minIdleTime, streamEntryID, params),
						StreamEntryConverter.MapStreamEntryConverter.STREAMENTRYID_KEY_MAP_CONVERTER)
				.transaction(
						(cmd)->cmd.xautoclaim(key, groupName, consumerName, minIdleTime, streamEntryID, params),
						StreamEntryConverter.MapStreamEntryConverter.STREAMENTRYID_KEY_MAP_CONVERTER)
				.run(args);
	}

	@Override
	public Map<StreamEntryId, List<StreamEntryId>> xAutoClaimJustId(final String key, final String groupName,
																	final String consumerName, final int minIdleTime,
																	final StreamEntryId start){
		final CommandArguments args = CommandArguments.create("key", key).put("groupName", groupName)
				.put("consumerName", consumerName).put("minIdleTime", minIdleTime).put("start", start)
				.put("count");
		final StreamEntryID streamEntryID = StreamEntryIdConverter.INSTANCE.convert(start);
		final JedisXAutoClaimParams params = new JedisXAutoClaimParams();
		return new JedisClusterCommand<Map<StreamEntryId, List<StreamEntryId>>>(client, ProtocolCommand.XAUTOCLAIM)
				.general((cmd)->cmd.xautoclaimJustId(key, groupName, consumerName, minIdleTime, streamEntryID, params),
						StreamEntryIDConverter.MapStreamEntryIdConverter.INSTANCE)
				.pipeline((cmd)->cmd.xautoclaimJustId(key, groupName, consumerName, minIdleTime, streamEntryID, params),
						StreamEntryIDConverter.MapStreamEntryIdConverter.INSTANCE)
				.transaction(
						(cmd)->cmd.xautoclaimJustId(key, groupName, consumerName, minIdleTime, streamEntryID, params),
						StreamEntryIDConverter.MapStreamEntryIdConverter.INSTANCE)
				.run(args);
	}

	@Override
	public Map<StreamEntryId, List<StreamEntryId>> xAutoClaimJustId(final String key, final String groupName,
																	final String consumerName, final int minIdleTime,
																	final StreamEntryId start, final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("groupName", groupName)
				.put("consumerName", consumerName).put("minIdleTime", minIdleTime).put("start", start)
				.put("count", count);
		final StreamEntryID streamEntryID = StreamEntryIdConverter.INSTANCE.convert(start);
		final JedisXAutoClaimParams params = new JedisXAutoClaimParams(count);
		return new JedisClusterCommand<Map<StreamEntryId, List<StreamEntryId>>>(client, ProtocolCommand.XAUTOCLAIM)
				.general((cmd)->cmd.xautoclaimJustId(key, groupName, consumerName, minIdleTime, streamEntryID, params),
						StreamEntryIDConverter.MapStreamEntryIdConverter.INSTANCE)
				.pipeline((cmd)->cmd.xautoclaimJustId(key, groupName, consumerName, minIdleTime, streamEntryID, params),
						StreamEntryIDConverter.MapStreamEntryIdConverter.INSTANCE)
				.transaction(
						(cmd)->cmd.xautoclaimJustId(key, groupName, consumerName, minIdleTime, streamEntryID, params),
						StreamEntryIDConverter.MapStreamEntryIdConverter.INSTANCE)
				.run(args);
	}

	@Override
	public List<StreamEntry> xClaim(final String key, final String groupName, final String consumerName,
									final int minIdleTime, final StreamEntryId... ids){
		final CommandArguments args = CommandArguments.create("key", key).put("groupName", groupName)
				.put("consumerName", consumerName).put("minIdleTime", minIdleTime).put("ids", (Object[]) ids);
		final XClaimParams params = new XClaimParams();
		final StreamEntryID[] streamEntryIDs = StreamEntryIdConverter.ARRAY_CONVERTER.convert(ids);
		return new JedisClusterCommand<List<StreamEntry>>(client, ProtocolCommand.XCLAIM)
				.general((cmd)->cmd.xclaim(key, groupName, consumerName, minIdleTime, params, streamEntryIDs),
						StreamEntryConverter.LIST_CONVERTER)
				.pipeline((cmd)->cmd.xclaim(key, groupName, consumerName, minIdleTime, params, streamEntryIDs),
						StreamEntryConverter.LIST_CONVERTER)
				.transaction(
						(cmd)->cmd.xclaim(key, groupName, consumerName, minIdleTime, params, streamEntryIDs),
						StreamEntryConverter.LIST_CONVERTER)
				.run(args);
	}

	@Override
	public List<StreamEntry> xClaim(final String key, final String groupName, final String consumerName,
									final int minIdleTime, final StreamEntryId[] ids,
									final XClaimArgument xClaimArgument){
		final CommandArguments args = CommandArguments.create("key", key).put("groupName", groupName)
				.put("consumerName", consumerName).put("minIdleTime", minIdleTime).put("ids", (Object[]) ids)
				.put("xClaimArgument", xClaimArgument);
		final XClaimParams params = XClaimArgumentConverter.INSTANCE.convert(xClaimArgument);
		final StreamEntryID[] streamEntryIDs = StreamEntryIdConverter.ARRAY_CONVERTER.convert(ids);
		return new JedisClusterCommand<List<StreamEntry>>(client, ProtocolCommand.XCLAIM)
				.general((cmd)->cmd.xclaim(key, groupName, consumerName, minIdleTime, params, streamEntryIDs),
						StreamEntryConverter.LIST_CONVERTER)
				.pipeline((cmd)->cmd.xclaim(key, groupName, consumerName, minIdleTime, params, streamEntryIDs),
						StreamEntryConverter.LIST_CONVERTER)
				.transaction(
						(cmd)->cmd.xclaim(key, groupName, consumerName, minIdleTime, params, streamEntryIDs),
						StreamEntryConverter.LIST_CONVERTER)
				.run(args);
	}

	@Override
	public List<StreamEntryId> xClaimJustId(final String key, final String groupName, final String consumerName,
											final int minIdleTime, final StreamEntryId... ids){
		final CommandArguments args = CommandArguments.create("key", key).put("groupName", groupName)
				.put("consumerName", consumerName).put("minIdleTime", minIdleTime).put("ids", (Object[]) ids);
		final XClaimParams params = new XClaimParams();
		final StreamEntryID[] streamEntryIDs = StreamEntryIdConverter.ARRAY_CONVERTER.convert(ids);
		return new JedisClusterCommand<List<StreamEntryId>>(client, ProtocolCommand.XCLAIM)
				.general((cmd)->cmd.xclaimJustId(key, groupName, consumerName, minIdleTime, params, streamEntryIDs),
						StreamEntryIDConverter.LIST_CONVERTER)
				.pipeline((cmd)->cmd.xclaimJustId(key, groupName, consumerName, minIdleTime, params, streamEntryIDs),
						StreamEntryIDConverter.LIST_CONVERTER)
				.transaction(
						(cmd)->cmd.xclaimJustId(key, groupName, consumerName, minIdleTime, params, streamEntryIDs),
						StreamEntryIDConverter.LIST_CONVERTER)
				.run(args);
	}

	@Override
	public List<StreamEntryId> xClaimJustId(final String key, final String groupName, final String consumerName,
											final int minIdleTime, final StreamEntryId[] ids,
											final XClaimArgument xClaimArgument){
		final CommandArguments args = CommandArguments.create("key", key).put("groupName", groupName)
				.put("consumerName", consumerName).put("minIdleTime", minIdleTime).put("ids", (Object[]) ids)
				.put("xClaimArgument", xClaimArgument);
		final XClaimParams params = XClaimArgumentConverter.INSTANCE.convert(xClaimArgument);
		final StreamEntryID[] streamEntryIDs = StreamEntryIdConverter.ARRAY_CONVERTER.convert(ids);
		return new JedisClusterCommand<List<StreamEntryId>>(client, ProtocolCommand.XCLAIM)
				.general((cmd)->cmd.xclaimJustId(key, groupName, consumerName, minIdleTime, params, streamEntryIDs),
						StreamEntryIDConverter.LIST_CONVERTER)
				.pipeline((cmd)->cmd.xclaimJustId(key, groupName, consumerName, minIdleTime, params, streamEntryIDs),
						StreamEntryIDConverter.LIST_CONVERTER)
				.transaction(
						(cmd)->cmd.xclaimJustId(key, groupName, consumerName, minIdleTime, params, streamEntryIDs),
						StreamEntryIDConverter.LIST_CONVERTER)
				.run(args);
	}

	@Override
	public Long xDel(final String key, final StreamEntryId... ids){
		final CommandArguments args = CommandArguments.create("key", key).put("ids", (Object[]) ids);
		final StreamEntryID[] streamEntryIDs = StreamEntryIdConverter.ARRAY_CONVERTER.convert(ids);
		return new JedisClusterCommand<Long>(client, ProtocolCommand.XDEL)
				.general((cmd)->cmd.xdel(key, streamEntryIDs))
				.pipeline((cmd)->cmd.xdel(key, streamEntryIDs))
				.transaction((cmd)->cmd.xdel(key, streamEntryIDs))
				.run(args);
	}

	@Override
	public Status xGroupCreate(final String key, final String groupName, final StreamEntryId id,
							   final boolean makeStream){
		final CommandArguments args = CommandArguments.create("key", key).put("groupName", groupName)
				.put("id", id);
		final StreamEntryID streamEntryID = StreamEntryIdConverter.INSTANCE.convert(id);
		return new JedisClusterCommand<Status>(client, ProtocolCommand.XGROUP_CREATE)
				.general((cmd)->cmd.xgroupCreate(key, groupName, streamEntryID, makeStream), OkStatusConverter.INSTANCE)
				.pipeline((cmd)->cmd.xgroupCreate(key, groupName, streamEntryID, makeStream),
						OkStatusConverter.INSTANCE)
				.transaction((cmd)->cmd.xgroupCreate(key, groupName, streamEntryID, makeStream),
						OkStatusConverter.INSTANCE)
				.run(args);
	}

	@Override
	public Status xGroupCreateConsumer(final String key, final String groupName, final String consumerName){
		final CommandArguments args = CommandArguments.create("key", key).put("groupName", groupName)
				.put("consumerName", consumerName);
		return new JedisClusterCommand<Status>(client, ProtocolCommand.XGROUP_CREATECONSUMER)
				.general((cmd)->cmd.xgroupCreateConsumer(key, groupName, consumerName),
						Converters.BOOLEAN_STATUS_CONVERTER)
				.pipeline((cmd)->cmd.xgroupCreateConsumer(key, groupName, consumerName),
						Converters.BOOLEAN_STATUS_CONVERTER)
				.transaction((cmd)->cmd.xgroupCreateConsumer(key, groupName, consumerName),
						Converters.BOOLEAN_STATUS_CONVERTER)
				.run(args);
	}

	@Override
	public Status xGroupCreateConsumer(final byte[] key, final byte[] groupName, final byte[] consumerName){
		final CommandArguments args = CommandArguments.create("key", key).put("groupName", groupName)
				.put("consumerName", consumerName);
		return new JedisClusterCommand<Status>(client,
				ProtocolCommand.XGROUP_CREATECONSUMER)
				.general((cmd)->cmd.xgroupCreateConsumer(key, groupName, consumerName),
						Converters.BOOLEAN_STATUS_CONVERTER)
				.pipeline((cmd)->cmd.xgroupCreateConsumer(key, groupName, consumerName),
						Converters.BOOLEAN_STATUS_CONVERTER)
				.transaction((cmd)->cmd.xgroupCreateConsumer(key, groupName, consumerName),
						Converters.BOOLEAN_STATUS_CONVERTER)
				.run(args);
	}

	@Override
	public Long xGroupDelConsumer(final String key, final String groupName, final String consumerName){
		final CommandArguments args = CommandArguments.create("key", key).put("groupName", groupName)
				.put("consumerName", consumerName);
		return new JedisClusterCommand<Long>(client, ProtocolCommand.XGROUP_DELCONSUMER)
				.general((cmd)->cmd.xgroupDelConsumer(key, groupName, consumerName))
				.pipeline((cmd)->cmd.xgroupDelConsumer(key, groupName, consumerName))
				.transaction((cmd)->cmd.xgroupDelConsumer(key, groupName, consumerName))
				.run(args);
	}

	@Override
	public Long xGroupDelConsumer(final byte[] key, final byte[] groupName, final byte[] consumerName){
		final CommandArguments args = CommandArguments.create("key", key).put("groupName", groupName)
				.put("consumerName", consumerName);
		return new JedisClusterCommand<Long>(client, ProtocolCommand.XGROUP_DELCONSUMER)
				.general((cmd)->cmd.xgroupDelConsumer(key, groupName, consumerName))
				.pipeline((cmd)->cmd.xgroupDelConsumer(key, groupName, consumerName))
				.transaction((cmd)->cmd.xgroupDelConsumer(key, groupName, consumerName))
				.run(args);
	}

	@Override
	public Status xGroupDestroy(final String key, final String groupName){
		final CommandArguments args = CommandArguments.create("key", key).put("groupName", groupName);
		return new JedisClusterCommand<Status>(client, ProtocolCommand.XGROUP_DESTROY)
				.general((cmd)->cmd.xgroupDestroy(key, groupName), Converters.ONE_STATUS_CONVERTER)
				.pipeline((cmd)->cmd.xgroupDestroy(key, groupName), Converters.ONE_STATUS_CONVERTER)
				.transaction((cmd)->cmd.xgroupDestroy(key, groupName), Converters.ONE_STATUS_CONVERTER)
				.run(args);
	}

	@Override
	public Status xGroupDestroy(final byte[] key, final byte[] groupName){
		final CommandArguments args = CommandArguments.create("key", key).put("groupName", groupName);
		return new JedisClusterCommand<Status>(client, ProtocolCommand.XGROUP_DESTROY)
				.general((cmd)->cmd.xgroupDestroy(key, groupName), Converters.ONE_STATUS_CONVERTER)
				.pipeline((cmd)->cmd.xgroupDestroy(key, groupName), Converters.ONE_STATUS_CONVERTER)
				.transaction((cmd)->cmd.xgroupDestroy(key, groupName), Converters.ONE_STATUS_CONVERTER)
				.run(args);
	}

	@Override
	public Status xGroupSetId(final String key, final String groupName, final StreamEntryId id){
		final CommandArguments args = CommandArguments.create("key", key).put("groupName", groupName).put("id", id);
		final StreamEntryID streamEntryID = StreamEntryIdConverter.INSTANCE.convert(id);
		return new JedisClusterCommand<Status>(client, ProtocolCommand.XGROUP_SETID)
				.general((cmd)->cmd.xgroupSetID(key, groupName, streamEntryID), OkStatusConverter.INSTANCE)
				.pipeline((cmd)->cmd.xgroupSetID(key, groupName, streamEntryID), OkStatusConverter.INSTANCE)
				.transaction((cmd)->cmd.xgroupSetID(key, groupName, streamEntryID), OkStatusConverter.INSTANCE)
				.run(args);
	}

	@Override
	public List<StreamConsumer> xInfoConsumers(final String key, final String groupName){
		final CommandArguments args = CommandArguments.create("key", key).put("groupName", groupName);
		return new JedisClusterCommand<List<StreamConsumer>>(client, ProtocolCommand.XINFO_CONSUMERS)
				.general((cmd)->cmd.xinfoConsumers(key, groupName), StreamConsumersInfoConverter.LIST_CONVERTER)
				.pipeline((cmd)->cmd.xinfoConsumers(key, groupName), StreamConsumersInfoConverter.LIST_CONVERTER)
				.transaction((cmd)->cmd.xinfoConsumers(key, groupName), StreamConsumersInfoConverter.LIST_CONVERTER)
				.run(args);
	}

	@Override
	public List<StreamGroup> xInfoGroups(final String key){
		final CommandArguments args = CommandArguments.create("key", key);
		return new JedisClusterCommand<List<StreamGroup>>(client, ProtocolCommand.XINFO_GROUPS)
				.general((cmd)->cmd.xinfoGroups(key), StreamGroupInfoConverter.LIST_CONVERTER)
				.pipeline((cmd)->cmd.xinfoGroups(key), StreamGroupInfoConverter.LIST_CONVERTER)
				.transaction((cmd)->cmd.xinfoGroups(key), StreamGroupInfoConverter.LIST_CONVERTER)
				.run(args);
	}

	@Override
	public Stream xInfoStream(final String key){
		final CommandArguments args = CommandArguments.create("key", key);
		return new JedisClusterCommand<Stream>(client, ProtocolCommand.XINFO_STREAM)
				.general((cmd)->cmd.xinfoStream(key), StreamInfoConverter.INSTANCE)
				.pipeline((cmd)->cmd.xinfoStream(key), StreamInfoConverter.INSTANCE)
				.transaction((cmd)->cmd.xinfoStream(key), StreamInfoConverter.INSTANCE)
				.run(args);
	}

	@Override
	public StreamFull xInfoStream(final String key, final boolean full){
		final CommandArguments args = CommandArguments.create("key", key).put("full", full);
		return new JedisClusterCommand<StreamFull>(client, ProtocolCommand.XINFO_STREAM)
				.general((cmd)->cmd.xinfoStreamFull(key), StreamFullInfoConverter.INSTANCE)
				.pipeline((cmd)->cmd.xinfoStreamFull(key), StreamFullInfoConverter.INSTANCE)
				.transaction((cmd)->cmd.xinfoStreamFull(key), StreamFullInfoConverter.INSTANCE)
				.run(args);
	}

	@Override
	public StreamFull xInfoStream(final String key, final boolean full, final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("full", full).put("count", count);
		return new JedisClusterCommand<StreamFull>(client, ProtocolCommand.XINFO_STREAM)
				.general((cmd)->cmd.xinfoStreamFull(key, (int) count), StreamFullInfoConverter.INSTANCE)
				.pipeline((cmd)->cmd.xinfoStreamFull(key, (int) count), StreamFullInfoConverter.INSTANCE)
				.transaction((cmd)->cmd.xinfoStreamFull(key, (int) count), StreamFullInfoConverter.INSTANCE)
				.run(args);
	}

	@Override
	public Long xLen(final String key){
		final CommandArguments args = CommandArguments.create("key", key);
		return new JedisClusterCommand<Long>(client, ProtocolCommand.XLEN)
				.general((cmd)->cmd.xlen(key))
				.pipeline((cmd)->cmd.xlen(key))
				.transaction((cmd)->cmd.xlen(key))
				.run(args);
	}

	@Override
	public Long xLen(final byte[] key){
		final CommandArguments args = CommandArguments.create("key", key);
		return new JedisClusterCommand<Long>(client, ProtocolCommand.XLEN)
				.general((cmd)->cmd.xlen(key))
				.pipeline((cmd)->cmd.xlen(key))
				.transaction((cmd)->cmd.xlen(key))
				.run(args);
	}

	@Override
	public StreamPendingSummary xPending(final String key, final String groupName){
		final CommandArguments args = CommandArguments.create("key", key).put("groupName", groupName);
		return new JedisClusterCommand<StreamPendingSummary>(client, ProtocolCommand.XPENDING)
				.general((cmd)->cmd.xpending(key, groupName), StreamPendingSummaryConverter.INSTANCE)
				.pipeline((cmd)->cmd.xpending(key, groupName), StreamPendingSummaryConverter.INSTANCE)
				.transaction((cmd)->cmd.xpending(key, groupName), StreamPendingSummaryConverter.INSTANCE)
				.run(args);
	}

	@Override
	public List<StreamPending> xPending(final String key, final String groupName, final long minIdleTime){
		final CommandArguments args = CommandArguments.create("key", key).put("groupName", groupName)
				.put("minIdleTime", minIdleTime);
		final JedisXPendingParams params = new JedisXPendingParams(minIdleTime);
		return new JedisClusterCommand<List<StreamPending>>(client, ProtocolCommand.XPENDING)
				.general((cmd)->cmd.xpending(key, groupName, params), StreamPendingEntryConverter.LIST_CONVERTER)
				.pipeline((cmd)->cmd.xpending(key, groupName, params), StreamPendingEntryConverter.LIST_CONVERTER)
				.transaction((cmd)->cmd.xpending(key, groupName, params), StreamPendingEntryConverter.LIST_CONVERTER)
				.run(args);
	}

	@Override
	public List<StreamPending> xPending(final String key, final String groupName, final StreamEntryId start,
										final StreamEntryId end, final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("groupName", groupName)
				.put("start", start).put("end", end).put("count", count);
		final JedisXPendingParams params = new JedisXPendingParams(start, end, count);
		return new JedisClusterCommand<List<StreamPending>>(client, ProtocolCommand.XPENDING)
				.general((cmd)->cmd.xpending(key, groupName, params), StreamPendingEntryConverter.LIST_CONVERTER)
				.pipeline((cmd)->cmd.xpending(key, groupName, params), StreamPendingEntryConverter.LIST_CONVERTER)
				.transaction((cmd)->cmd.xpending(key, groupName, params), StreamPendingEntryConverter.LIST_CONVERTER)
				.run(args);
	}

	@Override
	public List<StreamPending> xPending(final String key, final String groupName, final String consumerName){
		final CommandArguments args = CommandArguments.create("key", key).put("groupName", groupName)
				.put("consumerName", consumerName);
		final JedisXPendingParams params = new JedisXPendingParams(consumerName);
		return new JedisClusterCommand<List<StreamPending>>(client, ProtocolCommand.XPENDING)
				.general((cmd)->cmd.xpending(key, groupName, params), StreamPendingEntryConverter.LIST_CONVERTER)
				.pipeline((cmd)->cmd.xpending(key, groupName, params), StreamPendingEntryConverter.LIST_CONVERTER)
				.transaction((cmd)->cmd.xpending(key, groupName, params), StreamPendingEntryConverter.LIST_CONVERTER)
				.run(args);
	}

	@Override
	public List<StreamPending> xPending(final String key, final String groupName, final long minIdleTime,
										final StreamEntryId start, final StreamEntryId end, final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("groupName", groupName)
				.put("minIdleTime", minIdleTime).put("start", start).put("end", end).put("count", count);
		final JedisXPendingParams params = new JedisXPendingParams(minIdleTime, start, end, count);
		return new JedisClusterCommand<List<StreamPending>>(client, ProtocolCommand.XPENDING)
				.general((cmd)->cmd.xpending(key, groupName, params), StreamPendingEntryConverter.LIST_CONVERTER)
				.pipeline((cmd)->cmd.xpending(key, groupName, params), StreamPendingEntryConverter.LIST_CONVERTER)
				.transaction((cmd)->cmd.xpending(key, groupName, params), StreamPendingEntryConverter.LIST_CONVERTER)
				.run(args);
	}

	@Override
	public List<StreamPending> xPending(final String key, final String groupName, final long minIdleTime,
										final String consumerName){
		final CommandArguments args = CommandArguments.create("key", key).put("groupName", groupName)
				.put("minIdleTime", minIdleTime).put("consumerName", consumerName);
		final JedisXPendingParams params = new JedisXPendingParams(minIdleTime, consumerName);
		return new JedisClusterCommand<List<StreamPending>>(client, ProtocolCommand.XPENDING)
				.general((cmd)->cmd.xpending(key, groupName, params), StreamPendingEntryConverter.LIST_CONVERTER)
				.pipeline((cmd)->cmd.xpending(key, groupName, params), StreamPendingEntryConverter.LIST_CONVERTER)
				.transaction((cmd)->cmd.xpending(key, groupName, params), StreamPendingEntryConverter.LIST_CONVERTER)
				.run(args);
	}

	@Override
	public List<StreamPending> xPending(final String key, final String groupName, final StreamEntryId start,
										final StreamEntryId end, final long count, final String consumerName){
		final CommandArguments args = CommandArguments.create("key", key).put("groupName", groupName)
				.put("consumerName", consumerName);
		final JedisXPendingParams params = new JedisXPendingParams(start, end, count, consumerName);
		return new JedisClusterCommand<List<StreamPending>>(client, ProtocolCommand.XPENDING)
				.general((cmd)->cmd.xpending(key, groupName, params), StreamPendingEntryConverter.LIST_CONVERTER)
				.pipeline((cmd)->cmd.xpending(key, groupName, params), StreamPendingEntryConverter.LIST_CONVERTER)
				.transaction((cmd)->cmd.xpending(key, groupName, params), StreamPendingEntryConverter.LIST_CONVERTER)
				.run(args);
	}

	@Override
	public List<StreamPending> xPending(final String key, final String groupName, final long minIdleTime,
										final StreamEntryId start, final StreamEntryId end, final long count,
										final String consumerName){
		final CommandArguments args = CommandArguments.create("key", key).put("groupName", groupName)
				.put("minIdleTime", minIdleTime).put("consumerName", consumerName);
		final JedisXPendingParams params = new JedisXPendingParams(minIdleTime, start, end, count, consumerName);
		return new JedisClusterCommand<List<StreamPending>>(client, ProtocolCommand.XPENDING)
				.general((cmd)->cmd.xpending(key, groupName, params), StreamPendingEntryConverter.LIST_CONVERTER)
				.pipeline((cmd)->cmd.xpending(key, groupName, params), StreamPendingEntryConverter.LIST_CONVERTER)
				.transaction((cmd)->cmd.xpending(key, groupName, params), StreamPendingEntryConverter.LIST_CONVERTER)
				.run(args);
	}

	@Override
	public List<StreamEntry> xRange(final String key, final StreamEntryId start, final StreamEntryId end){
		final CommandArguments args = CommandArguments.create("key", key).put("start", start).put("end", end);
		final StreamEntryID startStreamEntryID = StreamEntryIdConverter.INSTANCE.convert(start);
		final StreamEntryID endStreamEntryID = StreamEntryIdConverter.INSTANCE.convert(end);
		return new JedisClusterCommand<List<StreamEntry>>(client, ProtocolCommand.XRANGE)
				.general((cmd)->cmd.xrange(key, startStreamEntryID, endStreamEntryID),
						StreamEntryConverter.LIST_CONVERTER)
				.pipeline((cmd)->cmd.xrange(key, startStreamEntryID, endStreamEntryID),
						StreamEntryConverter.LIST_CONVERTER)
				.transaction((cmd)->cmd.xrange(key, startStreamEntryID, endStreamEntryID),
						StreamEntryConverter.LIST_CONVERTER)
				.run(args);
	}

	@Override
	public List<StreamEntry> xRange(final String key, final StreamEntryId start, final StreamEntryId end,
									final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("start", start).put("end", end);
		final StreamEntryID startStreamEntryID = StreamEntryIdConverter.INSTANCE.convert(start);
		final StreamEntryID endStreamEntryID = StreamEntryIdConverter.INSTANCE.convert(end);
		return new JedisClusterCommand<List<StreamEntry>>(client, ProtocolCommand.XRANGE)
				.general((cmd)->cmd.xrange(key, startStreamEntryID, endStreamEntryID, (int) count),
						StreamEntryConverter.LIST_CONVERTER)
				.pipeline((cmd)->cmd.xrange(key, startStreamEntryID, endStreamEntryID, (int) count),
						StreamEntryConverter.LIST_CONVERTER)
				.transaction((cmd)->cmd.xrange(key, startStreamEntryID, endStreamEntryID, (int) count),
						StreamEntryConverter.LIST_CONVERTER)
				.run(args);
	}

	@Override
	public List<Map<String, List<StreamEntry>>> xRead(final Map<String, StreamEntryId> streams){
		final CommandArguments args = CommandArguments.create("streams", streams);
		final JedisXReadParams params = new JedisXReadParams();
		final Map<String, StreamEntryID> stringStreamEntryIDMap = StreamEntryIdConverter.MapStreamEntryIdConverter.STRING_MAP_CONVERTER.convert(
				streams);
		return new JedisClusterCommand<List<Map<String, List<StreamEntry>>>>(client, ProtocolCommand.XREAD)
				.general((cmd)->cmd.xread(params, stringStreamEntryIDMap),
						StreamEntryConverter.ListMapStreamEntryConverter.STRING_KEY_MAP_CONVERTER)
				.pipeline((cmd)->cmd.xread(params, stringStreamEntryIDMap),
						StreamEntryConverter.ListMapStreamEntryConverter.STRING_KEY_MAP_CONVERTER)
				.transaction((cmd)->cmd.xread(params, stringStreamEntryIDMap),
						StreamEntryConverter.ListMapStreamEntryConverter.STRING_KEY_MAP_CONVERTER)
				.run(args);
	}

	@Override
	public List<Map<String, List<StreamEntry>>> xRead(final long count, final Map<String, StreamEntryId> streams){
		final CommandArguments args = CommandArguments.create("count", count).put("streams", streams);
		final JedisXReadParams params = new JedisXReadParams(count);
		final Map<String, StreamEntryID> stringStreamEntryIDMap = StreamEntryIdConverter.MapStreamEntryIdConverter.STRING_MAP_CONVERTER.convert(
				streams);
		return new JedisClusterCommand<List<Map<String, List<StreamEntry>>>>(client, ProtocolCommand.XREAD)
				.general((cmd)->cmd.xread(params, stringStreamEntryIDMap),
						StreamEntryConverter.ListMapStreamEntryConverter.STRING_KEY_MAP_CONVERTER)
				.pipeline((cmd)->cmd.xread(params, stringStreamEntryIDMap),
						StreamEntryConverter.ListMapStreamEntryConverter.STRING_KEY_MAP_CONVERTER)
				.transaction((cmd)->cmd.xread(params, stringStreamEntryIDMap),
						StreamEntryConverter.ListMapStreamEntryConverter.STRING_KEY_MAP_CONVERTER)
				.run(args);
	}

	@Override
	public List<Map<String, List<StreamEntry>>> xRead(final int block, final Map<String, StreamEntryId> streams){
		final CommandArguments args = CommandArguments.create("block", block).put("streams", streams);
		final JedisXReadParams params = new JedisXReadParams(block);
		final Map<String, StreamEntryID> stringStreamEntryIDMap = StreamEntryIdConverter.MapStreamEntryIdConverter.STRING_MAP_CONVERTER.convert(
				streams);
		return new JedisClusterCommand<List<Map<String, List<StreamEntry>>>>(
				client, ProtocolCommand.XREAD)
				.general((cmd)->cmd.xread(params, stringStreamEntryIDMap),
						StreamEntryConverter.ListMapStreamEntryConverter.STRING_KEY_MAP_CONVERTER)
				.pipeline((cmd)->cmd.xread(params, stringStreamEntryIDMap),
						StreamEntryConverter.ListMapStreamEntryConverter.STRING_KEY_MAP_CONVERTER)
				.transaction((cmd)->cmd.xread(params, stringStreamEntryIDMap),
						StreamEntryConverter.ListMapStreamEntryConverter.STRING_KEY_MAP_CONVERTER)
				.run(args);
	}

	@Override
	public List<Map<String, List<StreamEntry>>> xRead(final long count, final int block,
													  final Map<String, StreamEntryId> streams){
		final CommandArguments args = CommandArguments.create("count", count).put("block", block)
				.put("streams", streams);
		final JedisXReadParams params = new JedisXReadParams(count, block);
		final Map<String, StreamEntryID> stringStreamEntryIDMap = StreamEntryIdConverter.MapStreamEntryIdConverter.STRING_MAP_CONVERTER.convert(
				streams);
		return new JedisClusterCommand<List<Map<String, List<StreamEntry>>>>(client, ProtocolCommand.XREAD)
				.general((cmd)->cmd.xread(params, stringStreamEntryIDMap),
						StreamEntryConverter.ListMapStreamEntryConverter.STRING_KEY_MAP_CONVERTER)
				.pipeline((cmd)->cmd.xread(params, stringStreamEntryIDMap),
						StreamEntryConverter.ListMapStreamEntryConverter.STRING_KEY_MAP_CONVERTER)
				.transaction((cmd)->cmd.xread(params, stringStreamEntryIDMap),
						StreamEntryConverter.ListMapStreamEntryConverter.STRING_KEY_MAP_CONVERTER)
				.run(args);
	}

	@Override
	public List<Map<String, List<StreamEntry>>> xReadGroup(final String groupName, final String consumerName,
														   final Map<String, StreamEntryId> streams){
		final CommandArguments args = CommandArguments.create("groupName", groupName).put("consumerName", consumerName)
				.put("streams", streams);
		final JedisXReadGroupParams params = new JedisXReadGroupParams();
		final Map<String, StreamEntryID> stringStreamEntryIDMap = StreamEntryIdConverter.MapStreamEntryIdConverter.STRING_MAP_CONVERTER.convert(
				streams);
		return new JedisClusterCommand<List<Map<String, List<StreamEntry>>>>(client, ProtocolCommand.XREADGROUP)
				.general((cmd)->cmd.xreadGroup(groupName, consumerName, params, stringStreamEntryIDMap),
						StreamEntryConverter.ListMapStreamEntryConverter.STRING_KEY_MAP_CONVERTER)
				.pipeline((cmd)->cmd.xreadGroup(groupName, consumerName, params, stringStreamEntryIDMap),
						StreamEntryConverter.ListMapStreamEntryConverter.STRING_KEY_MAP_CONVERTER)
				.transaction((cmd)->cmd.xreadGroup(groupName, consumerName, params, stringStreamEntryIDMap),
						StreamEntryConverter.ListMapStreamEntryConverter.STRING_KEY_MAP_CONVERTER)
				.run(args);
	}

	@Override
	public List<Map<String, List<StreamEntry>>> xReadGroup(final String groupName, final String consumerName,
														   final long count, final Map<String, StreamEntryId> streams){
		final CommandArguments args = CommandArguments.create("groupName", groupName).put("consumerName", consumerName)
				.put("count", count).put("streams", streams);
		final JedisXReadGroupParams params = new JedisXReadGroupParams(count);
		final Map<String, StreamEntryID> stringStreamEntryIDMap = StreamEntryIdConverter.MapStreamEntryIdConverter.STRING_MAP_CONVERTER.convert(
				streams);
		return new JedisClusterCommand<List<Map<String, List<StreamEntry>>>>(client, ProtocolCommand.XREADGROUP)
				.general((cmd)->cmd.xreadGroup(groupName, consumerName, params, stringStreamEntryIDMap),
						StreamEntryConverter.ListMapStreamEntryConverter.STRING_KEY_MAP_CONVERTER)
				.pipeline((cmd)->cmd.xreadGroup(groupName, consumerName, params, stringStreamEntryIDMap),
						StreamEntryConverter.ListMapStreamEntryConverter.STRING_KEY_MAP_CONVERTER)
				.transaction((cmd)->cmd.xreadGroup(groupName, consumerName, params, stringStreamEntryIDMap),
						StreamEntryConverter.ListMapStreamEntryConverter.STRING_KEY_MAP_CONVERTER)
				.run(args);
	}

	@Override
	public List<Map<String, List<StreamEntry>>> xReadGroup(final String groupName, final String consumerName,
														   final int block, final Map<String, StreamEntryId> streams){
		final CommandArguments args = CommandArguments.create("groupName", groupName).put("consumerName", consumerName)
				.put("block", block).put("streams", streams);
		final JedisXReadGroupParams params = new JedisXReadGroupParams(block);
		final Map<String, StreamEntryID> stringStreamEntryIDMap = StreamEntryIdConverter.MapStreamEntryIdConverter.STRING_MAP_CONVERTER.convert(
				streams);
		return new JedisClusterCommand<List<Map<String, List<StreamEntry>>>>(client, ProtocolCommand.XREADGROUP)
				.general((cmd)->cmd.xreadGroup(groupName, consumerName, params, stringStreamEntryIDMap),
						StreamEntryConverter.ListMapStreamEntryConverter.STRING_KEY_MAP_CONVERTER)
				.pipeline((cmd)->cmd.xreadGroup(groupName, consumerName, params, stringStreamEntryIDMap),
						StreamEntryConverter.ListMapStreamEntryConverter.STRING_KEY_MAP_CONVERTER)
				.transaction((cmd)->cmd.xreadGroup(groupName, consumerName, params, stringStreamEntryIDMap),
						StreamEntryConverter.ListMapStreamEntryConverter.STRING_KEY_MAP_CONVERTER)
				.run(args);
	}

	@Override
	public List<Map<String, List<StreamEntry>>> xReadGroup(final String groupName, final String consumerName,
														   final boolean isNoAck,
														   final Map<String, StreamEntryId> streams){
		final CommandArguments args = CommandArguments.create("groupName", groupName).put("consumerName", consumerName)
				.put("isNoAck", isNoAck).put("streams", streams);
		final JedisXReadGroupParams params = new JedisXReadGroupParams(isNoAck);
		final Map<String, StreamEntryID> stringStreamEntryIDMap = StreamEntryIdConverter.MapStreamEntryIdConverter.STRING_MAP_CONVERTER.convert(
				streams);
		return new JedisClusterCommand<List<Map<String, List<StreamEntry>>>>(client, ProtocolCommand.XREADGROUP)
				.general((cmd)->cmd.xreadGroup(groupName, consumerName, params, stringStreamEntryIDMap),
						StreamEntryConverter.ListMapStreamEntryConverter.STRING_KEY_MAP_CONVERTER)
				.pipeline((cmd)->cmd.xreadGroup(groupName, consumerName, params, stringStreamEntryIDMap),
						StreamEntryConverter.ListMapStreamEntryConverter.STRING_KEY_MAP_CONVERTER)
				.transaction((cmd)->cmd.xreadGroup(groupName, consumerName, params, stringStreamEntryIDMap),
						StreamEntryConverter.ListMapStreamEntryConverter.STRING_KEY_MAP_CONVERTER)
				.run(args);
	}

	@Override
	public List<Map<String, List<StreamEntry>>> xReadGroup(final String groupName, final String consumerName,
														   final long count, final int block,
														   final Map<String, StreamEntryId> streams){
		final CommandArguments args = CommandArguments.create("groupName", groupName).put("consumerName", consumerName)
				.put("count", count).put("block", block).put("streams", streams);
		final JedisXReadGroupParams params = new JedisXReadGroupParams(count, block);
		final Map<String, StreamEntryID> stringStreamEntryIDMap = StreamEntryIdConverter.MapStreamEntryIdConverter.STRING_MAP_CONVERTER.convert(
				streams);
		return new JedisClusterCommand<List<Map<String, List<StreamEntry>>>>(client, ProtocolCommand.XREADGROUP)
				.general((cmd)->cmd.xreadGroup(groupName, consumerName, params, stringStreamEntryIDMap),
						StreamEntryConverter.ListMapStreamEntryConverter.STRING_KEY_MAP_CONVERTER)
				.pipeline((cmd)->cmd.xreadGroup(groupName, consumerName, params, stringStreamEntryIDMap),
						StreamEntryConverter.ListMapStreamEntryConverter.STRING_KEY_MAP_CONVERTER)
				.transaction((cmd)->cmd.xreadGroup(groupName, consumerName, params, stringStreamEntryIDMap),
						StreamEntryConverter.ListMapStreamEntryConverter.STRING_KEY_MAP_CONVERTER)
				.run(args);
	}

	@Override
	public List<Map<String, List<StreamEntry>>> xReadGroup(final String groupName, final String consumerName,
														   final long count, final boolean isNoAck,
														   final Map<String, StreamEntryId> streams){
		final CommandArguments args = CommandArguments.create("groupName", groupName).put("consumerName", consumerName)
				.put("count", count).put("isNoAck", isNoAck).put("streams", streams);
		final JedisXReadGroupParams params = new JedisXReadGroupParams(count, isNoAck);
		final Map<String, StreamEntryID> stringStreamEntryIDMap = StreamEntryIdConverter.MapStreamEntryIdConverter.STRING_MAP_CONVERTER.convert(
				streams);
		return new JedisClusterCommand<List<Map<String, List<StreamEntry>>>>(client, ProtocolCommand.XREADGROUP)
				.general((cmd)->cmd.xreadGroup(groupName, consumerName, params, stringStreamEntryIDMap),
						StreamEntryConverter.ListMapStreamEntryConverter.STRING_KEY_MAP_CONVERTER)
				.pipeline((cmd)->cmd.xreadGroup(groupName, consumerName, params, stringStreamEntryIDMap),
						StreamEntryConverter.ListMapStreamEntryConverter.STRING_KEY_MAP_CONVERTER)
				.transaction((cmd)->cmd.xreadGroup(groupName, consumerName, params, stringStreamEntryIDMap),
						StreamEntryConverter.ListMapStreamEntryConverter.STRING_KEY_MAP_CONVERTER)
				.run(args);
	}

	@Override
	public List<Map<String, List<StreamEntry>>> xReadGroup(final String groupName, final String consumerName,
														   final int block, final boolean isNoAck,
														   final Map<String, StreamEntryId> streams){
		final CommandArguments args = CommandArguments.create("groupName", groupName).put("consumerName", consumerName)
				.put("block", block).put("isNoAck", isNoAck).put("streams", streams);
		final JedisXReadGroupParams params = new JedisXReadGroupParams(block, isNoAck);
		final Map<String, StreamEntryID> stringStreamEntryIDMap = StreamEntryIdConverter.MapStreamEntryIdConverter.STRING_MAP_CONVERTER.convert(
				streams);
		return new JedisClusterCommand<List<Map<String, List<StreamEntry>>>>(client, ProtocolCommand.XREADGROUP)
				.general((cmd)->cmd.xreadGroup(groupName, consumerName, params, stringStreamEntryIDMap),
						StreamEntryConverter.ListMapStreamEntryConverter.STRING_KEY_MAP_CONVERTER)
				.pipeline((cmd)->cmd.xreadGroup(groupName, consumerName, params, stringStreamEntryIDMap),
						StreamEntryConverter.ListMapStreamEntryConverter.STRING_KEY_MAP_CONVERTER)
				.transaction((cmd)->cmd.xreadGroup(groupName, consumerName, params, stringStreamEntryIDMap),
						StreamEntryConverter.ListMapStreamEntryConverter.STRING_KEY_MAP_CONVERTER)
				.run(args);
	}

	@Override
	public List<Map<String, List<StreamEntry>>> xReadGroup(final String groupName, final String consumerName,
														   final long count, final int block, final boolean isNoAck,
														   final Map<String, StreamEntryId> streams){
		final CommandArguments args = CommandArguments.create("groupName", groupName).put("consumerName", consumerName)
				.put("count", count).put("block", block).put("isNoAck", isNoAck).put("streams", streams);
		final JedisXReadGroupParams params = new JedisXReadGroupParams(count, block, isNoAck);
		final Map<String, StreamEntryID> stringStreamEntryIDMap = StreamEntryIdConverter.MapStreamEntryIdConverter.STRING_MAP_CONVERTER.convert(
				streams);
		return new JedisClusterCommand<List<Map<String, List<StreamEntry>>>>(client, ProtocolCommand.XREADGROUP)
				.general((cmd)->cmd.xreadGroup(groupName, consumerName, params, stringStreamEntryIDMap),
						StreamEntryConverter.ListMapStreamEntryConverter.STRING_KEY_MAP_CONVERTER)
				.pipeline((cmd)->cmd.xreadGroup(groupName, consumerName, params, stringStreamEntryIDMap),
						StreamEntryConverter.ListMapStreamEntryConverter.STRING_KEY_MAP_CONVERTER)
				.transaction((cmd)->cmd.xreadGroup(groupName, consumerName, params, stringStreamEntryIDMap),
						StreamEntryConverter.ListMapStreamEntryConverter.STRING_KEY_MAP_CONVERTER)
				.run(args);
	}

	@Override
	public List<StreamEntry> xRevRange(final String key, final StreamEntryId end, final StreamEntryId start){
		final CommandArguments args = CommandArguments.create("key", key).put("end", end).put("start", start);
		final StreamEntryID endID = StreamEntryIdConverter.INSTANCE.convert(end);
		final StreamEntryID startID = StreamEntryIdConverter.INSTANCE.convert(start);
		return new JedisClusterCommand<List<StreamEntry>>(client, ProtocolCommand.XREVRANGE)
				.general((cmd)->cmd.xrevrange(key, endID, startID), StreamEntryConverter.LIST_CONVERTER)
				.pipeline((cmd)->cmd.xrevrange(key, endID, startID), StreamEntryConverter.LIST_CONVERTER)
				.transaction((cmd)->cmd.xrevrange(key, endID, startID), StreamEntryConverter.LIST_CONVERTER)
				.run(args);
	}

	@Override
	public List<StreamEntry> xRevRange(final String key, final StreamEntryId end, final StreamEntryId start,
									   final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("end", end).put("start", start)
				.put("count", count);
		final StreamEntryID endID = StreamEntryIdConverter.INSTANCE.convert(end);
		final StreamEntryID startID = StreamEntryIdConverter.INSTANCE.convert(start);
		return new JedisClusterCommand<List<StreamEntry>>(client, ProtocolCommand.XREVRANGE)
				.general((cmd)->cmd.xrevrange(key, endID, startID, (int) count), StreamEntryConverter.LIST_CONVERTER)
				.pipeline((cmd)->cmd.xrevrange(key, endID, startID, (int) count), StreamEntryConverter.LIST_CONVERTER)
				.transaction((cmd)->cmd.xrevrange(key, endID, startID, (int) count),
						StreamEntryConverter.LIST_CONVERTER)
				.run(args);
	}

	@Override
	public Long xTrim(final String key, final XTrimArgument xTrimArgument){
		final CommandArguments args = CommandArguments.create("key", key).put("xTrimArgument", xTrimArgument);
		final XTrimParams params = XTrimArgumentConverter.INSTANCE.convert(xTrimArgument);
		return new JedisClusterCommand<Long>(client, ProtocolCommand.XTRIM)
				.general((cmd)->cmd.xtrim(key, params))
				.pipeline((cmd)->cmd.xtrim(key, params))
				.transaction((cmd)->cmd.xtrim(key, params))
				.run(args);
	}

	@Override
	public Long xTrim(final byte[] key, final XTrimArgument xTrimArgument){
		final CommandArguments args = CommandArguments.create("key", key).put("xTrimArgument", xTrimArgument);
		final XTrimParams params = XTrimArgumentConverter.INSTANCE.convert(xTrimArgument);
		return new JedisClusterCommand<Long>(client, ProtocolCommand.XTRIM)
				.general((cmd)->cmd.xtrim(key, params))
				.pipeline((cmd)->cmd.xtrim(key, params))
				.transaction((cmd)->cmd.xtrim(key, params))
				.run(args);
	}

	@Override
	public Long xTrim(final String key, final XTrimArgument xTrimArgument, final long limit){
		final CommandArguments args = CommandArguments.create("key", key).put("xTrimArgument", xTrimArgument)
				.put("limit", limit);
		final XTrimParams params = XTrimArgumentConverter.INSTANCE.convert(xTrimArgument).limit(limit);
		return new JedisClusterCommand<Long>(client, ProtocolCommand.XTRIM)
				.general((cmd)->cmd.xtrim(key, params))
				.pipeline((cmd)->cmd.xtrim(key, params))
				.transaction((cmd)->cmd.xtrim(key, params))
				.run(args);
	}

	@Override
	public Long xTrim(final byte[] key, final XTrimArgument xTrimArgument, final long limit){
		final CommandArguments args = CommandArguments.create("key", key).put("xTrimArgument", xTrimArgument)
				.put("limit", limit);
		final XTrimParams params = XTrimArgumentConverter.INSTANCE.convert(xTrimArgument).limit(limit);
		return new JedisClusterCommand<Long>(client, ProtocolCommand.XTRIM)
				.general((cmd)->cmd.xtrim(key, params))
				.pipeline((cmd)->cmd.xtrim(key, params))
				.transaction((cmd)->cmd.xtrim(key, params))
				.run(args);
	}

}
