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

import com.buession.core.converter.BooleanStatusConverter;
import com.buession.lang.Status;
import com.buession.redis.client.jedis.operations.JedisRedisOperations;
import com.buession.redis.client.lettuce.LettuceStandaloneClient;
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
import com.buession.redis.core.internal.convert.jedis.params.XClaimArgumentConverter;
import com.buession.redis.core.internal.convert.jedis.response.StreamConsumersInfoConverter;
import com.buession.redis.core.internal.convert.jedis.response.StreamEntryConverter;
import com.buession.redis.core.internal.convert.jedis.response.StreamFullInfoConverter;
import com.buession.redis.core.internal.convert.jedis.response.StreamGroupInfoConverter;
import com.buession.redis.core.internal.convert.jedis.response.StreamInfoConverter;
import com.buession.redis.core.internal.convert.jedis.response.StreamPendingEntryConverter;
import com.buession.redis.core.internal.convert.jedis.response.StreamPendingSummaryConverter;
import com.buession.redis.core.internal.convert.lettuce.params.StreamEntryIdConverter;
import com.buession.redis.core.internal.convert.lettuce.response.StreamEntryIDConverter;
import com.buession.redis.core.internal.convert.lettuce.response.StreamMessageConverter;
import com.buession.redis.core.internal.convert.response.OkStatusConverter;
import com.buession.redis.core.internal.convert.response.OneStatusConverter;
import com.buession.redis.core.internal.jedis.JedisXPendingParams;
import com.buession.redis.core.internal.jedis.JedisXReadGroupParams;
import com.buession.redis.core.internal.jedis.JedisXReadParams;
import com.buession.redis.core.internal.lettuce.LettuceXAddArgs;
import com.buession.redis.core.internal.lettuce.LettuceXClaimArgs;
import io.lettuce.core.Consumer;
import redis.clients.jedis.StreamEntryID;
import redis.clients.jedis.params.XClaimParams;

import java.util.List;
import java.util.Map;

/**
 * Lettuce 单机模式 Stream 命令操作抽象类
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public final class LettuceStreamOperations extends AbstractStreamOperations<LettuceStandaloneClient> {

	public LettuceStreamOperations(final LettuceStandaloneClient client) {
		super(client);
	}

	@Override
	public Long xAck(final byte[] key, final byte[] groupName, final StreamEntryId... ids) {
		final CommandArguments args = CommandArguments.create("key", key).put("groupName", groupName)
				.put("ids", (Object[]) ids);
		return new LettuceCommand<>(client, ProtocolCommand.XACK, (cmd)->cmd.xack(key, groupName,
				(new StreamEntryIdConverter.ArrayStreamEntryIdConverter()).convert(ids)), (value)->value)
				.run(args);
	}

	@Override
	public StreamEntryId xAdd(final byte[] key, final StreamEntryId id, final Map<byte[], byte[]> hash) {
		final CommandArguments args = CommandArguments.create("key", key).put("id", id).put("hash", hash);
		return new LettuceCommand<>(client, ProtocolCommand.XADD, (cmd)->cmd.xadd(key, new LettuceXAddArgs(id),
				hash), new StreamEntryIDConverter())
				.run(args);
	}

	@Override
	public StreamEntryId xAdd(final byte[] key, final StreamEntryId id, final Map<byte[], byte[]> hash,
							  final XAddArgument xAddArgument) {
		final CommandArguments args = CommandArguments.create("key", key).put("id", id).put("hash", hash)
				.put("xAddArgument", xAddArgument);
		return new LettuceCommand<>(client, ProtocolCommand.XADD, (cmd)->cmd.xadd(key,
				LettuceXAddArgs.from(xAddArgument).id((new StreamEntryIdConverter()).convert(id)),
				hash), new StreamEntryIDConverter())
				.run(args);
	}

	@Override
	public Map<StreamEntryId, List<StreamEntry>> xAutoClaim(final String key, final String groupName,
															final String consumerName, final int minIdleTime,
															final StreamEntryId start) {
		final CommandArguments args = CommandArguments.create("key", key).put("groupName", groupName)
				.put("consumerName", consumerName).put("minIdleTime", minIdleTime).put("start", start);
		return new LettuceCommand<Map<StreamEntryId, List<StreamEntry>>, Map<StreamEntryId, List<StreamEntry>>>(client,
				ProtocolCommand.XAUTOCLAIM)
				.run(args);
	}

	@Override
	public Map<StreamEntryId, List<StreamEntry>> xAutoClaim(final byte[] key, final byte[] groupName,
															final byte[] consumerName, final int minIdleTime,
															final StreamEntryId start) {
		final CommandArguments args = CommandArguments.create("key", key).put("groupName", groupName)
				.put("consumerName", consumerName).put("minIdleTime", minIdleTime).put("start", start);
		return new LettuceCommand<Map<StreamEntryId, List<StreamEntry>>, Map<StreamEntryId, List<StreamEntry>>>(client,
				ProtocolCommand.XAUTOCLAIM)
				.run(args);
	}


	@Override
	public Map<StreamEntryId, List<StreamEntry>> xAutoClaim(final String key, final String groupName,
															final String consumerName, final int minIdleTime,
															final StreamEntryId start, final long count) {
		final CommandArguments args = CommandArguments.create("key", key).put("groupName", groupName)
				.put("consumerName", consumerName).put("minIdleTime", minIdleTime).put("start", start)
				.put("count", count);
		return new LettuceCommand<Map<StreamEntryId, List<StreamEntry>>, Map<StreamEntryId, List<StreamEntry>>>(client,
				ProtocolCommand.XAUTOCLAIM)
				.run(args);
	}

	@Override
	public Map<StreamEntryId, List<StreamEntry>> xAutoClaim(final byte[] key, final byte[] groupName,
															final byte[] consumerName, final int minIdleTime,
															final StreamEntryId start, final long count) {
		final CommandArguments args = CommandArguments.create("key", key).put("groupName", groupName)
				.put("consumerName", consumerName).put("minIdleTime", minIdleTime).put("start", start)
				.put("count", count);
		return new LettuceCommand<Map<StreamEntryId, List<StreamEntry>>, Map<StreamEntryId, List<StreamEntry>>>(client,
				ProtocolCommand.XAUTOCLAIM)
				.run(args);
	}

	@Override
	public Map<StreamEntryId, List<StreamEntryId>> xAutoClaimJustId(final String key, final String groupName,
																	final String consumerName, final int minIdleTime,
																	final StreamEntryId start) {
		final CommandArguments args = CommandArguments.create("key", key).put("groupName", groupName)
				.put("consumerName", consumerName).put("minIdleTime", minIdleTime).put("start", start)
				.put("count");
		return new LettuceCommand<Map<StreamEntryId, List<StreamEntryId>>, Map<StreamEntryId, List<StreamEntryId>>>(
				client, ProtocolCommand.XAUTOCLAIM)
				.run(args);
	}

	@Override
	public Map<StreamEntryId, List<StreamEntryId>> xAutoClaimJustId(final byte[] key, final byte[] groupName,
																	final byte[] consumerName, final int minIdleTime,
																	final StreamEntryId start) {
		final CommandArguments args = CommandArguments.create("key", key).put("groupName", groupName)
				.put("consumerName", consumerName).put("minIdleTime", minIdleTime).put("start", start)
				.put("count");
		return new LettuceCommand<Map<StreamEntryId, List<StreamEntryId>>, Map<StreamEntryId, List<StreamEntryId>>>(
				client, ProtocolCommand.XAUTOCLAIM)
				.run(args);
	}

	@Override
	public Map<StreamEntryId, List<StreamEntryId>> xAutoClaimJustId(final byte[] key, final byte[] groupName,
																	final byte[] consumerName, final int minIdleTime,
																	final StreamEntryId start, final long count) {
		final CommandArguments args = CommandArguments.create("key", key).put("groupName", groupName)
				.put("consumerName", consumerName).put("minIdleTime", minIdleTime).put("start", start)
				.put("count", count);
		return new LettuceCommand<Map<StreamEntryId, List<StreamEntryId>>, Map<StreamEntryId, List<StreamEntryId>>>(
				client, ProtocolCommand.XAUTOCLAIM)
				.run(args);
	}

	@Override
	public Map<StreamEntryId, List<StreamEntryId>> xAutoClaimJustId(final String key, final String groupName,
																	final String consumerName, final int minIdleTime,
																	final StreamEntryId start, final long count) {
		final CommandArguments args = CommandArguments.create("key", key).put("groupName", groupName)
				.put("consumerName", consumerName).put("minIdleTime", minIdleTime).put("start", start)
				.put("count", count);
		return new LettuceCommand<Map<StreamEntryId, List<StreamEntryId>>, Map<StreamEntryId, List<StreamEntryId>>>(
				client, ProtocolCommand.XAUTOCLAIM)
				.run(args);
	}

	@Override
	public List<StreamEntry> xClaim(final byte[] key, final byte[] groupName, final byte[] consumerName,
									final int minIdleTime, final StreamEntryId... ids) {
		final CommandArguments args = CommandArguments.create("key", key).put("groupName", groupName)
				.put("consumerName", consumerName).put("minIdleTime", minIdleTime).put("ids", (Object[]) ids);
		return new LettuceCommand<>(client, ProtocolCommand.XCLAIM, (cmd)->cmd.xclaim(key, Consumer.from(groupName,
						consumerName), minIdleTime,
				(new StreamEntryIdConverter.ArrayStreamEntryIdConverter()).convert(ids)),
				new StreamMessageConverter.ListStreamMessageConverter())
				.run(args);
	}

	@Override
	public List<StreamEntry> xClaim(final byte[] key, final byte[] groupName, final byte[] consumerName,
									final int minIdleTime, final StreamEntryId[] ids,
									final XClaimArgument xClaimArgument) {
		final CommandArguments args = CommandArguments.create("key", key).put("groupName", groupName)
				.put("consumerName", consumerName).put("minIdleTime", minIdleTime).put("ids", (Object[]) ids)
				.put("xClaimArgument", xClaimArgument);
		return new LettuceCommand<>(client, ProtocolCommand.XCLAIM, (cmd)->cmd.xclaim(key, Consumer.from(groupName,
						consumerName), LettuceXClaimArgs.from(xClaimArgument).minIdleTime(minIdleTime),
				(new StreamEntryIdConverter.ArrayStreamEntryIdConverter()).convert(ids)),
				new StreamMessageConverter.ListStreamMessageConverter())
				.run(args);
	}

	@Override
	public List<StreamEntryId> xClaimJustId(final String key, final String groupName, final String consumerName,
											final int minIdleTime, final StreamEntryId... ids) {
		final CommandArguments args = CommandArguments.create("key", key).put("groupName", groupName)
				.put("consumerName", consumerName).put("minIdleTime", minIdleTime).put("ids", (Object[]) ids);
		final XClaimParams params = new XClaimParams();
		final StreamEntryID[] streamEntryIDs = (new StreamEntryIdConverter.ArrayStreamEntryIdConverter()).convert(ids);
		return new JedisCommand<List<StreamEntryId>>(client, ProtocolCommand.XCLAIM)
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
	public List<StreamEntryId> xClaimJustId(final byte[] key, final byte[] groupName, final byte[] consumerName,
											final int minIdleTime, final StreamEntryId... ids) {
		final CommandArguments args = CommandArguments.create("key", key).put("groupName", groupName)
				.put("consumerName", consumerName).put("minIdleTime", minIdleTime).put("ids", (Object[]) ids);
		return new LettuceCommand<>(client, ProtocolCommand.XCLAIM, (cmd)->cmd.xclaim(key, Consumer.from(groupName,
						consumerName), (new LettuceXClaimArgs()).minIdleTime(minIdleTime),
				(new StreamEntryIdConverter.ArrayStreamEntryIdConverter()).convert(ids)),
				new StreamMessageConverter.ListStreamMessageConverter())
				.run(args);
	}

	@Override
	public List<StreamEntryId> xClaimJustId(final String key, final String groupName, final String consumerName,
											final int minIdleTime, final StreamEntryId[] ids,
											final XClaimArgument xClaimArgument) {
		final CommandArguments args = CommandArguments.create("key", key).put("groupName", groupName)
				.put("consumerName", consumerName).put("minIdleTime", minIdleTime).put("ids", (Object[]) ids)
				.put("xClaimArgument", xClaimArgument);
		final XClaimParams params = (new XClaimArgumentConverter()).convert(xClaimArgument);
		final StreamEntryID[] streamEntryIDs = (new StreamEntryIdConverter.ArrayStreamEntryIdConverter()).convert(ids);
		return new JedisCommand<List<StreamEntryId>>(client, ProtocolCommand.XCLAIM)
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
	public Long xDel(final String key, final StreamEntryId... ids) {
		final CommandArguments args = CommandArguments.create("key", key).put("ids", (Object[]) ids);
		final StreamEntryID[] streamEntryIDs = (new StreamEntryIdConverter.ArrayStreamEntryIdConverter()).convert(ids);
		return new JedisCommand<Long>(client, ProtocolCommand.XDEL)
				.general((cmd)->cmd.xdel(key, streamEntryIDs))
				.pipeline((cmd)->cmd.xdel(key, streamEntryIDs))
				.transaction((cmd)->cmd.xdel(key, streamEntryIDs))
				.run(args);
	}

	@Override
	public Status xGroupCreate(final String key, final String groupName, final StreamEntryId id,
							   final boolean makeStream) {
		final CommandArguments args = CommandArguments.create("key", key).put("groupName", groupName)
				.put("id", id);
		final StreamEntryID streamEntryID = (new StreamEntryIdConverter()).convert(id);
		return new JedisCommand<Status>(client, ProtocolCommand.XGROUP_CREATE)
				.general((cmd)->cmd.xgroupCreate(key, groupName, streamEntryID, makeStream), new OkStatusConverter())
				.pipeline((cmd)->cmd.xgroupCreate(key, groupName, streamEntryID, makeStream),
						new OkStatusConverter())
				.transaction((cmd)->cmd.xgroupCreate(key, groupName, streamEntryID, makeStream),
						new OkStatusConverter())
				.run(args);
	}

	@Override
	public Status xGroupCreateConsumer(final String key, final String groupName, final String consumerName) {
		final CommandArguments args = CommandArguments.create("key", key).put("groupName", groupName)
				.put("consumerName", consumerName);
		return new JedisCommand<Status>(client, ProtocolCommand.XGROUP_CREATECONSUMER)
				.general((cmd)->cmd.xgroupCreateConsumer(key, groupName, consumerName),
						new BooleanStatusConverter())
				.pipeline((cmd)->cmd.xgroupCreateConsumer(key, groupName, consumerName),
						new BooleanStatusConverter())
				.transaction((cmd)->cmd.xgroupCreateConsumer(key, groupName, consumerName),
						new BooleanStatusConverter())
				.run(args);
	}

	@Override
	public Status xGroupCreateConsumer(final byte[] key, final byte[] groupName, final byte[] consumerName) {
		final CommandArguments args = CommandArguments.create("key", key).put("groupName", groupName)
				.put("consumerName", consumerName);
		return new JedisCommand<Status>(client, ProtocolCommand.XGROUP_CREATECONSUMER)
				.general((cmd)->cmd.xgroupCreateConsumer(key, groupName, consumerName),
						new BooleanStatusConverter())
				.pipeline((cmd)->cmd.xgroupCreateConsumer(key, groupName, consumerName),
						new BooleanStatusConverter())
				.transaction((cmd)->cmd.xgroupCreateConsumer(key, groupName, consumerName),
						new BooleanStatusConverter())
				.run(args);
	}

	@Override
	public Long xGroupDelConsumer(final String key, final String groupName, final String consumerName) {
		final CommandArguments args = CommandArguments.create("key", key).put("groupName", groupName)
				.put("consumerName", consumerName);
		return new JedisCommand<Long>(client, ProtocolCommand.XGROUP_DELCONSUMER)
				.general((cmd)->cmd.xgroupDelConsumer(key, groupName, consumerName))
				.pipeline((cmd)->cmd.xgroupDelConsumer(key, groupName, consumerName))
				.transaction((cmd)->cmd.xgroupDelConsumer(key, groupName, consumerName))
				.run(args);
	}

	@Override
	public Long xGroupDelConsumer(final byte[] key, final byte[] groupName, final byte[] consumerName) {
		final CommandArguments args = CommandArguments.create("key", key).put("groupName", groupName)
				.put("consumerName", consumerName);
		return new JedisRedisOperations.JedisCommand<Long>(client, ProtocolCommand.XGROUP_DELCONSUMER)
				.general((cmd)->cmd.xgroupDelConsumer(key, groupName, consumerName))
				.pipeline((cmd)->cmd.xgroupDelConsumer(key, groupName, consumerName))
				.transaction((cmd)->cmd.xgroupDelConsumer(key, groupName, consumerName))
				.run(args);
	}

	@Override
	public Status xGroupDestroy(final String key, final String groupName) {
		final CommandArguments args = CommandArguments.create("key", key).put("groupName", groupName);
		return new JedisRedisOperations.JedisCommand<Status>(client, ProtocolCommand.XGROUP_DESTROY)
				.general((cmd)->cmd.xgroupDestroy(key, groupName), new OneStatusConverter())
				.pipeline((cmd)->cmd.xgroupDestroy(key, groupName), new OneStatusConverter())
				.transaction((cmd)->cmd.xgroupDestroy(key, groupName), new OneStatusConverter())
				.run(args);
	}

	@Override
	public Status xGroupDestroy(final byte[] key, final byte[] groupName) {
		final CommandArguments args = CommandArguments.create("key", key).put("groupName", groupName);
		return new JedisCommand<Status>(client, ProtocolCommand.XGROUP_DESTROY)
				.general((cmd)->cmd.xgroupDestroy(key, groupName), new OneStatusConverter())
				.pipeline((cmd)->cmd.xgroupDestroy(key, groupName), new OneStatusConverter())
				.transaction((cmd)->cmd.xgroupDestroy(key, groupName), new OneStatusConverter())
				.run(args);
	}

	@Override
	public Status xGroupSetId(final String key, final String groupName, final StreamEntryId id) {
		final CommandArguments args = CommandArguments.create("key", key).put("groupName", groupName).put("id", id);
		final StreamEntryID streamEntryID = (new StreamEntryIdConverter()).convert(id);
		return new JedisCommand<Status>(client, ProtocolCommand.XGROUP_SETID)
				.general((cmd)->cmd.xgroupSetID(key, groupName, streamEntryID), new OkStatusConverter())
				.pipeline((cmd)->cmd.xgroupSetID(key, groupName, streamEntryID), new OkStatusConverter())
				.transaction((cmd)->cmd.xgroupSetID(key, groupName, streamEntryID), new OkStatusConverter())
				.run(args);
	}

	@Override
	public List<StreamConsumer> xInfoConsumers(final String key, final String groupName) {
		final CommandArguments args = CommandArguments.create("key", key).put("groupName", groupName);
		return new JedisCommand<List<StreamConsumer>>(client,
				ProtocolCommand.XINFO_CONSUMERS)
				.general((cmd)->cmd.xinfoConsumers(key, groupName),
						new StreamConsumersInfoConverter.ListStreamConsumersInfoConverter())
				.pipeline((cmd)->cmd.xinfoConsumers(key, groupName),
						new StreamConsumersInfoConverter.ListStreamConsumersInfoConverter())
				.transaction((cmd)->cmd.xinfoConsumers(key, groupName),
						new StreamConsumersInfoConverter.ListStreamConsumersInfoConverter())
				.run(args);
	}

	@Override
	public List<StreamGroup> xInfoGroups(final String key) {
		final CommandArguments args = CommandArguments.create("key", key);
		return new JedisCommand<List<StreamGroup>>(client, ProtocolCommand.XINFO_GROUPS)
				.general((cmd)->cmd.xinfoGroups(key), new StreamGroupInfoConverter.ListStreamGroupInfoConverter())
				.pipeline((cmd)->cmd.xinfoGroups(key), new StreamGroupInfoConverter.ListStreamGroupInfoConverter())
				.transaction((cmd)->cmd.xinfoGroups(key), new StreamGroupInfoConverter.ListStreamGroupInfoConverter())
				.run(args);
	}

	@Override
	public Stream xInfoStream(final String key) {
		final CommandArguments args = CommandArguments.create("key", key);
		return new JedisCommand<Stream>(client, ProtocolCommand.XINFO_STREAM)
				.general((cmd)->cmd.xinfoStream(key), new StreamInfoConverter())
				.pipeline((cmd)->cmd.xinfoStream(key), new StreamInfoConverter())
				.transaction((cmd)->cmd.xinfoStream(key), new StreamInfoConverter())
				.run(args);
	}

	@Override
	public StreamFull xInfoStream(final String key, final boolean full) {
		final CommandArguments args = CommandArguments.create("key", key).put("full", full);
		return new JedisCommand<StreamFull>(client, ProtocolCommand.XINFO_STREAM)
				.general((cmd)->cmd.xinfoStreamFull(key), new StreamFullInfoConverter())
				.pipeline((cmd)->cmd.xinfoStreamFull(key), new StreamFullInfoConverter())
				.transaction((cmd)->cmd.xinfoStreamFull(key), new StreamFullInfoConverter())
				.run(args);
	}

	@Override
	public StreamFull xInfoStream(final String key, final boolean full, final long count) {
		final CommandArguments args = CommandArguments.create("key", key).put("full", full).put("count", count);
		return new JedisCommand<StreamFull>(client, ProtocolCommand.XINFO_STREAM)
				.general((cmd)->cmd.xinfoStreamFull(key, (int) count), new StreamFullInfoConverter())
				.pipeline((cmd)->cmd.xinfoStreamFull(key, (int) count), new StreamFullInfoConverter())
				.transaction((cmd)->cmd.xinfoStreamFull(key, (int) count), new StreamFullInfoConverter())
				.run(args);
	}

	@Override
	public Long xLen(final String key) {
		final CommandArguments args = CommandArguments.create("key", key);
		return new JedisCommand<Long>(client, ProtocolCommand.XLEN)
				.general((cmd)->cmd.xlen(key))
				.pipeline((cmd)->cmd.xlen(key))
				.transaction((cmd)->cmd.xlen(key))
				.run(args);
	}

	@Override
	public Long xLen(final byte[] key) {
		final CommandArguments args = CommandArguments.create("key", key);
		return new JedisCommand<Long>(client, ProtocolCommand.XLEN)
				.general((cmd)->cmd.xlen(key))
				.pipeline((cmd)->cmd.xlen(key))
				.transaction((cmd)->cmd.xlen(key))
				.run(args);
	}

	@Override
	public StreamPendingSummary xPending(final String key, final String groupName) {
		final CommandArguments args = CommandArguments.create("key", key).put("groupName", groupName);
		return new JedisCommand<StreamPendingSummary>(client, ProtocolCommand.XPENDING)
				.general((cmd)->cmd.xpending(key, groupName), new StreamPendingSummaryConverter())
				.pipeline((cmd)->cmd.xpending(key, groupName), new StreamPendingSummaryConverter())
				.transaction((cmd)->cmd.xpending(key, groupName), new StreamPendingSummaryConverter())
				.run(args);
	}

	@Override
	public List<StreamPending> xPending(final String key, final String groupName, final long minIdleTime) {
		final CommandArguments args = CommandArguments.create("key", key).put("groupName", groupName)
				.put("minIdleTime", minIdleTime);
		final JedisXPendingParams params = new JedisXPendingParams(minIdleTime);
		return new JedisCommand<List<StreamPending>>(client, ProtocolCommand.XPENDING)
				.general((cmd)->cmd.xpending(key, groupName, params),
						new StreamPendingEntryConverter.ListStreamPendingEntryConverter())
				.pipeline((cmd)->cmd.xpending(key, groupName, params),
						new StreamPendingEntryConverter.ListStreamPendingEntryConverter())
				.transaction((cmd)->cmd.xpending(key, groupName, params),
						new StreamPendingEntryConverter.ListStreamPendingEntryConverter())
				.run(args);
	}

	@Override
	public List<StreamPending> xPending(final String key, final String groupName, final StreamEntryId start,
										final StreamEntryId end, final long count) {
		final CommandArguments args = CommandArguments.create("key", key).put("groupName", groupName)
				.put("start", start).put("end", end).put("count", count);
		final JedisXPendingParams params = new JedisXPendingParams(start, end, count);
		return new JedisCommand<List<StreamPending>>(client, ProtocolCommand.XPENDING)
				.general((cmd)->cmd.xpending(key, groupName, params),
						new StreamPendingEntryConverter.ListStreamPendingEntryConverter())
				.pipeline((cmd)->cmd.xpending(key, groupName, params),
						new StreamPendingEntryConverter.ListStreamPendingEntryConverter())
				.transaction((cmd)->cmd.xpending(key, groupName, params),
						new StreamPendingEntryConverter.ListStreamPendingEntryConverter())
				.run(args);
	}

	@Override
	public List<StreamPending> xPending(final String key, final String groupName, final String consumerName) {
		final CommandArguments args = CommandArguments.create("key", key).put("groupName", groupName)
				.put("consumerName", consumerName);
		final JedisXPendingParams params = new JedisXPendingParams(consumerName);
		return new JedisCommand<List<StreamPending>>(client, ProtocolCommand.XPENDING)
				.general((cmd)->cmd.xpending(key, groupName, params),
						new StreamPendingEntryConverter.ListStreamPendingEntryConverter())
				.pipeline((cmd)->cmd.xpending(key, groupName, params),
						new StreamPendingEntryConverter.ListStreamPendingEntryConverter())
				.transaction((cmd)->cmd.xpending(key, groupName, params),
						new StreamPendingEntryConverter.ListStreamPendingEntryConverter())
				.run(args);
	}

	@Override
	public List<StreamPending> xPending(final String key, final String groupName, final long minIdleTime,
										final StreamEntryId start, final StreamEntryId end, final long count) {
		final CommandArguments args = CommandArguments.create("key", key).put("groupName", groupName)
				.put("minIdleTime", minIdleTime).put("start", start).put("end", end).put("count", count);
		final JedisXPendingParams params = new JedisXPendingParams(minIdleTime, start, end, count);
		return new JedisCommand<List<StreamPending>>(client, ProtocolCommand.XPENDING)
				.general((cmd)->cmd.xpending(key, groupName, params),
						new StreamPendingEntryConverter.ListStreamPendingEntryConverter())
				.pipeline((cmd)->cmd.xpending(key, groupName, params),
						new StreamPendingEntryConverter.ListStreamPendingEntryConverter())
				.transaction((cmd)->cmd.xpending(key, groupName, params),
						new StreamPendingEntryConverter.ListStreamPendingEntryConverter())
				.run(args);
	}

	@Override
	public List<StreamPending> xPending(final String key, final String groupName, final long minIdleTime,
										final String consumerName) {
		final CommandArguments args = CommandArguments.create("key", key).put("groupName", groupName)
				.put("minIdleTime", minIdleTime).put("consumerName", consumerName);
		final JedisXPendingParams params = new JedisXPendingParams(minIdleTime, consumerName);
		return new JedisCommand<List<StreamPending>>(client, ProtocolCommand.XPENDING)
				.general((cmd)->cmd.xpending(key, groupName, params),
						new StreamPendingEntryConverter.ListStreamPendingEntryConverter())
				.pipeline((cmd)->cmd.xpending(key, groupName, params),
						new StreamPendingEntryConverter.ListStreamPendingEntryConverter())
				.transaction((cmd)->cmd.xpending(key, groupName, params),
						new StreamPendingEntryConverter.ListStreamPendingEntryConverter())
				.run(args);
	}

	@Override
	public List<StreamPending> xPending(final String key, final String groupName, final StreamEntryId start,
										final StreamEntryId end, final long count, final String consumerName) {
		final CommandArguments args = CommandArguments.create("key", key).put("groupName", groupName)
				.put("consumerName", consumerName);
		final JedisXPendingParams params = new JedisXPendingParams(start, end, count, consumerName);
		return new JedisCommand<List<StreamPending>>(client, ProtocolCommand.XPENDING)
				.general((cmd)->cmd.xpending(key, groupName, params),
						new StreamPendingEntryConverter.ListStreamPendingEntryConverter())
				.pipeline((cmd)->cmd.xpending(key, groupName, params),
						new StreamPendingEntryConverter.ListStreamPendingEntryConverter())
				.transaction((cmd)->cmd.xpending(key, groupName, params),
						new StreamPendingEntryConverter.ListStreamPendingEntryConverter())
				.run(args);
	}

	@Override
	public List<StreamPending> xPending(final String key, final String groupName, final long minIdleTime,
										final StreamEntryId start, final StreamEntryId end, final long count,
										final String consumerName) {
		final CommandArguments args = CommandArguments.create("key", key).put("groupName", groupName)
				.put("minIdleTime", minIdleTime).put("consumerName", consumerName);
		final JedisXPendingParams params = new JedisXPendingParams(minIdleTime, start, end, count, consumerName);
		return new JedisCommand<List<StreamPending>>(client, ProtocolCommand.XPENDING)
				.general((cmd)->cmd.xpending(key, groupName, params),
						new StreamPendingEntryConverter.ListStreamPendingEntryConverter())
				.pipeline((cmd)->cmd.xpending(key, groupName, params),
						new StreamPendingEntryConverter.ListStreamPendingEntryConverter())
				.transaction((cmd)->cmd.xpending(key, groupName, params),
						new StreamPendingEntryConverter.ListStreamPendingEntryConverter())
				.run(args);
	}

	@Override
	public List<StreamEntry> xRange(final String key, final StreamEntryId start, final StreamEntryId end) {
		final CommandArguments args = CommandArguments.create("key", key).put("start", start).put("end", end);
		final StreamEntryID startStreamEntryID = (new StreamEntryIdConverter()).convert(start);
		final StreamEntryID endStreamEntryID = (new StreamEntryIdConverter()).convert(end);
		return new JedisCommand<List<StreamEntry>>(client, ProtocolCommand.XRANGE)
				.general((cmd)->cmd.xrange(key, startStreamEntryID, endStreamEntryID),
						new StreamEntryConverter.ListStreamEntryConverter())
				.pipeline((cmd)->cmd.xrange(key, startStreamEntryID, endStreamEntryID),
						new StreamEntryConverter.ListStreamEntryConverter())
				.transaction((cmd)->cmd.xrange(key, startStreamEntryID, endStreamEntryID),
						new StreamEntryConverter.ListStreamEntryConverter())
				.run(args);
	}

	@Override
	public List<StreamEntry> xRange(final String key, final StreamEntryId start, final StreamEntryId end,
									final long count) {
		final CommandArguments args = CommandArguments.create("key", key).put("start", start).put("end", end);
		final StreamEntryID startStreamEntryID = (new StreamEntryIdConverter()).convert(start);
		final StreamEntryID endStreamEntryID = (new StreamEntryIdConverter()).convert(end);
		return new JedisCommand<List<StreamEntry>>(client, ProtocolCommand.XRANGE)
				.general((cmd)->cmd.xrange(key, startStreamEntryID, endStreamEntryID, (int) count),
						new StreamEntryConverter.ListStreamEntryConverter())
				.pipeline((cmd)->cmd.xrange(key, startStreamEntryID, endStreamEntryID, (int) count),
						new StreamEntryConverter.ListStreamEntryConverter())
				.transaction((cmd)->cmd.xrange(key, startStreamEntryID, endStreamEntryID, (int) count),
						new StreamEntryConverter.ListStreamEntryConverter())
				.run(args);
	}

	@Override
	public List<Map<String, List<StreamEntry>>> xRead(final Map<String, StreamEntryId> streams) {
		final CommandArguments args = CommandArguments.create("streams", streams);
		final JedisXReadParams params = new JedisXReadParams();
		final Map<String, StreamEntryID> stringStreamEntryIDMap = (new StreamEntryIdConverter.MapStreamEntryIdConverter<>()).convert(
				streams);
		return new JedisCommand<List<Map<String, List<StreamEntry>>>>(client, ProtocolCommand.XREAD)
				.general((cmd)->cmd.xread(params, stringStreamEntryIDMap),
						new StreamEntryConverter.MapEntryStreamEntryConverter((k)->k)))
				.pipeline((cmd)->cmd.xread(params, stringStreamEntryIDMap),
				new StreamEntryConverter.MapEntryStreamEntryConverter((k)->k)))
				.transaction((cmd)->cmd.xread(params, stringStreamEntryIDMap),
				new StreamEntryConverter.MapEntryStreamEntryConverter((k)->k)))
				.run(args);
	}

	@Override
	public List<Map<String, List<StreamEntry>>> xRead(final long count, final Map<String, StreamEntryId> streams) {
		final CommandArguments args = CommandArguments.create("count", count).put("streams", streams);
		final JedisXReadParams params = new JedisXReadParams(count);
		final Map<String, StreamEntryID> stringStreamEntryIDMap = (new StreamEntryIdConverter.MapStreamEntryIdConverter<>()).convert(
				streams);
		return new JedisCommand<List<Map<String, List<StreamEntry>>>>(client, ProtocolCommand.XREAD)
				.general((cmd)->cmd.xread(params, stringStreamEntryIDMap),
						new StreamEntryConverter.MapEntryStreamEntryConverter((k)->k)))
				.pipeline((cmd)->cmd.xread(params, stringStreamEntryIDMap),
				new StreamEntryConverter.MapEntryStreamEntryConverter((k)->k)))
				.transaction((cmd)->cmd.xread(params, stringStreamEntryIDMap),
				new StreamEntryConverter.MapEntryStreamEntryConverter((k)->k)))
				.run(args);
	}

	@Override
	public List<Map<String, List<StreamEntry>>> xRead(final int block, final Map<String, StreamEntryId> streams) {
		final CommandArguments args = CommandArguments.create("block", block).put("streams", streams);
		final JedisXReadParams params = new JedisXReadParams(block);
		final Map<String, StreamEntryID> stringStreamEntryIDMap = (new StreamEntryIdConverter.MapStreamEntryIdConverter<>()).convert(
				streams);
		return new JedisCommand<List<Map<String, List<StreamEntry>>>>(client, ProtocolCommand.XREAD)
				.general((cmd)->cmd.xread(params, stringStreamEntryIDMap),
						new StreamEntryConverter.MapEntryStreamEntryConverter((k)->k)))
				.pipeline((cmd)->cmd.xread(params, stringStreamEntryIDMap),
				new StreamEntryConverter.MapEntryStreamEntryConverter((k)->k)))
				.transaction((cmd)->cmd.xread(params, stringStreamEntryIDMap),
				new StreamEntryConverter.MapEntryStreamEntryConverter((k)->k)))
				.run(args);
	}

	@Override
	public List<Map<String, List<StreamEntry>>> xRead(final long count, final int block,
													  final Map<String, StreamEntryId> streams) {
		final CommandArguments args = CommandArguments.create("count", count).put("block", block)
				.put("streams", streams);
		final JedisXReadParams params = new JedisXReadParams(count, block);
		final Map<String, StreamEntryID> stringStreamEntryIDMap = (new StreamEntryIdConverter.MapStreamEntryIdConverter<>()).convert(
				streams);
		return new JedisCommand<List<Map<String, List<StreamEntry>>>>(client, ProtocolCommand.XREAD)
				.general((cmd)->cmd.xread(params, stringStreamEntryIDMap),
						new StreamEntryConverter.MapEntryStreamEntryConverter((k)->k)))
				.pipeline((cmd)->cmd.xread(params, stringStreamEntryIDMap),
				new StreamEntryConverter.MapEntryStreamEntryConverter((k)->k)))
				.transaction((cmd)->cmd.xread(params, stringStreamEntryIDMap),
				new StreamEntryConverter.MapEntryStreamEntryConverter((k)->k)))
				.run(args);
	}

	@Override
	public List<Map<String, List<StreamEntry>>> xReadGroup(final String groupName, final String consumerName,
														   final Map<String, StreamEntryId> streams) {
		final CommandArguments args = CommandArguments.create("groupName", groupName).put("consumerName", consumerName)
				.put("streams", streams);
		final JedisXReadGroupParams params = new JedisXReadGroupParams();
		final Map<String, StreamEntryID> stringStreamEntryIDMap = (new StreamEntryIdConverter.MapStreamEntryIdConverter<>()).convert(
				streams);
		return new JedisCommand<List<Map<String, List<StreamEntry>>>>(client, ProtocolCommand.XREADGROUP)
				.general((cmd)->cmd.xreadGroup(groupName, consumerName, params, stringStreamEntryIDMap),
						new StreamEntryConverter.MapEntryStreamEntryConverter((k)->k)))
				.pipeline((cmd)->cmd.xreadGroup(groupName, consumerName, params, stringStreamEntryIDMap),
				new StreamEntryConverter.MapEntryStreamEntryConverter((k)->k)))
				.transaction((cmd)->cmd.xreadGroup(groupName, consumerName, params, stringStreamEntryIDMap),
				new StreamEntryConverter.MapEntryStreamEntryConverter((k)->k)))
				.run(args);
	}

	@Override
	public List<Map<String, List<StreamEntry>>> xReadGroup(final String groupName, final String consumerName,
														   final long count, final Map<String, StreamEntryId> streams) {
		final CommandArguments args = CommandArguments.create("groupName", groupName).put("consumerName", consumerName)
				.put("count", count).put("streams", streams);
		final JedisXReadGroupParams params = new JedisXReadGroupParams(count);
		final Map<String, StreamEntryID> stringStreamEntryIDMap = (new StreamEntryIdConverter.MapStreamEntryIdConverter<>()).convert(
				streams);
		return new JedisCommand<List<Map<String, List<StreamEntry>>>>(client, ProtocolCommand.XREADGROUP)
				.general((cmd)->cmd.xreadGroup(groupName, consumerName, params, stringStreamEntryIDMap),
						new StreamEntryConverter.MapEntryStreamEntryConverter((k)->k)))
				.pipeline((cmd)->cmd.xreadGroup(groupName, consumerName, params, stringStreamEntryIDMap),
				new StreamEntryConverter.MapEntryStreamEntryConverter((k)->k)))
				.transaction((cmd)->cmd.xreadGroup(groupName, consumerName, params, stringStreamEntryIDMap),
				new StreamEntryConverter.MapEntryStreamEntryConverter((k)->k)))
				.run(args);
	}

	@Override
	public List<Map<String, List<StreamEntry>>> xReadGroup(final String groupName, final String consumerName,
														   final int block, final Map<String, StreamEntryId> streams) {
		final CommandArguments args = CommandArguments.create("groupName", groupName).put("consumerName", consumerName)
				.put("block", block).put("streams", streams);
		final JedisXReadGroupParams params = new JedisXReadGroupParams(block);
		final Map<String, StreamEntryID> stringStreamEntryIDMap = (new StreamEntryIdConverter.MapStreamEntryIdConverter<>()).convert(
				streams);
		return new JedisCommand<List<Map<String, List<StreamEntry>>>>(client, ProtocolCommand.XREADGROUP)
				.general((cmd)->cmd.xreadGroup(groupName, consumerName, params, stringStreamEntryIDMap),
						new StreamEntryConverter.MapEntryStreamEntryConverter((k)->k)))
				.pipeline((cmd)->cmd.xreadGroup(groupName, consumerName, params, stringStreamEntryIDMap),
				new StreamEntryConverter.MapEntryStreamEntryConverter((k)->k)))
				.transaction((cmd)->cmd.xreadGroup(groupName, consumerName, params, stringStreamEntryIDMap),
				new StreamEntryConverter.MapEntryStreamEntryConverter((k)->k)))
				.run(args);
	}

	@Override
	public List<Map<String, List<StreamEntry>>> xReadGroup(final String groupName, final String consumerName,
														   final boolean isNoAck,
														   final Map<String, StreamEntryId> streams) {
		final CommandArguments args = CommandArguments.create("groupName", groupName).put("consumerName", consumerName)
				.put("isNoAck", isNoAck).put("streams", streams);
		final JedisXReadGroupParams params = new JedisXReadGroupParams(isNoAck);
		final Map<String, StreamEntryID> stringStreamEntryIDMap = (new StreamEntryIdConverter.MapStreamEntryIdConverter<>()).convert(
				streams);
		return new JedisCommand<List<Map<String, List<StreamEntry>>>>(client, ProtocolCommand.XREADGROUP)
				.general((cmd)->cmd.xreadGroup(groupName, consumerName, params, stringStreamEntryIDMap),
						new StreamEntryConverter.MapEntryStreamEntryConverter((k)->k)))
				.pipeline((cmd)->cmd.xreadGroup(groupName, consumerName, params, stringStreamEntryIDMap),
				new StreamEntryConverter.MapEntryStreamEntryConverter((k)->k)))
				.transaction((cmd)->cmd.xreadGroup(groupName, consumerName, params, stringStreamEntryIDMap),
				new StreamEntryConverter.MapEntryStreamEntryConverter((k)->k)))
				.run(args);
	}

	@Override
	public List<Map<String, List<StreamEntry>>> xReadGroup(final String groupName, final String consumerName,
														   final long count, final int block,
														   final Map<String, StreamEntryId> streams) {
		final CommandArguments args = CommandArguments.create("groupName", groupName).put("consumerName", consumerName)
				.put("count", count).put("block", block).put("streams", streams);
		final JedisXReadGroupParams params = new JedisXReadGroupParams(count, block);
		final Map<String, StreamEntryID> stringStreamEntryIDMap = (new StreamEntryIdConverter.MapStreamEntryIdConverter<>()).convert(
				streams);
		return new JedisCommand<List<Map<String, List<StreamEntry>>>>(client, ProtocolCommand.XREADGROUP)
				.general((cmd)->cmd.xreadGroup(groupName, consumerName, params, stringStreamEntryIDMap),
						new StreamEntryConverter.MapEntryStreamEntryConverter((k)->k)))
				.pipeline((cmd)->cmd.xreadGroup(groupName, consumerName, params, stringStreamEntryIDMap),
				new StreamEntryConverter.MapEntryStreamEntryConverter((k)->k)))
				.transaction((cmd)->cmd.xreadGroup(groupName, consumerName, params, stringStreamEntryIDMap),
				new StreamEntryConverter.MapEntryStreamEntryConverter((k)->k)))
				.run(args);
	}

	@Override
	public List<Map<String, List<StreamEntry>>> xReadGroup(final String groupName, final String consumerName,
														   final long count, final boolean isNoAck,
														   final Map<String, StreamEntryId> streams) {
		final CommandArguments args = CommandArguments.create("groupName", groupName).put("consumerName", consumerName)
				.put("count", count).put("isNoAck", isNoAck).put("streams", streams);
		final JedisXReadGroupParams params = new JedisXReadGroupParams(count, isNoAck);
		final Map<String, StreamEntryID> stringStreamEntryIDMap = (new StreamEntryIdConverter.MapStreamEntryIdConverter<>()).convert(
				streams);
		return new JedisCommand<List<Map<String, List<StreamEntry>>>>(client, ProtocolCommand.XREADGROUP)
				.general((cmd)->cmd.xreadGroup(groupName, consumerName, params, stringStreamEntryIDMap),
						new StreamEntryConverter.MapEntryStreamEntryConverter((k)->k)))
				.pipeline((cmd)->cmd.xreadGroup(groupName, consumerName, params, stringStreamEntryIDMap),
				new StreamEntryConverter.MapEntryStreamEntryConverter((k)->k)))
				.transaction((cmd)->cmd.xreadGroup(groupName, consumerName, params, stringStreamEntryIDMap),
				new StreamEntryConverter.MapEntryStreamEntryConverter((k)->k)))
				.run(args);
	}

	@Override
	public List<Map<String, List<StreamEntry>>> xReadGroup(final String groupName, final String consumerName,
														   final int block, final boolean isNoAck,
														   final Map<String, StreamEntryId> streams) {
		final CommandArguments args = CommandArguments.create("groupName", groupName).put("consumerName", consumerName)
				.put("block", block).put("isNoAck", isNoAck).put("streams", streams);
		final JedisXReadGroupParams params = new JedisXReadGroupParams(block, isNoAck);
		final Map<String, StreamEntryID> stringStreamEntryIDMap = (new StreamEntryIdConverter.MapStreamEntryIdConverter<>()).convert(
				streams);
		return new JedisCommand<List<Map<String, List<StreamEntry>>>>(client, ProtocolCommand.XREADGROUP)
				.general((cmd)->cmd.xreadGroup(groupName, consumerName, params, stringStreamEntryIDMap),
						new StreamEntryConverter.MapEntryStreamEntryConverter((k)->k)))
				.pipeline((cmd)->cmd.xreadGroup(groupName, consumerName, params, stringStreamEntryIDMap),
				new StreamEntryConverter.MapEntryStreamEntryConverter((k)->k)))
				.transaction((cmd)->cmd.xreadGroup(groupName, consumerName, params, stringStreamEntryIDMap),
				new StreamEntryConverter.MapEntryStreamEntryConverter((k)->k)))
				.run(args);
	}

	@Override
	public List<Map<String, List<StreamEntry>>> xReadGroup(final String groupName, final String consumerName,
														   final long count, final int block, final boolean isNoAck,
														   final Map<String, StreamEntryId> streams) {
		final CommandArguments args = CommandArguments.create("groupName", groupName).put("consumerName", consumerName)
				.put("count", count).put("block", block).put("isNoAck", isNoAck).put("streams", streams);
		final JedisXReadGroupParams params = new JedisXReadGroupParams(count, block, isNoAck);
		final Map<String, StreamEntryID> stringStreamEntryIDMap = (new StreamEntryIdConverter.MapStreamEntryIdConverter<>()).convert(
				streams);
		return new JedisCommand<List<Map<String, List<StreamEntry>>>>(client, ProtocolCommand.XREADGROUP)
				.general((cmd)->cmd.xreadGroup(groupName, consumerName, params, stringStreamEntryIDMap),
						new StreamEntryConverter.MapEntryStreamEntryConverter((k)->k)))
				.pipeline((cmd)->cmd.xreadGroup(groupName, consumerName, params, stringStreamEntryIDMap),
				new StreamEntryConverter.MapEntryStreamEntryConverter((k)->k)))
				.transaction((cmd)->cmd.xreadGroup(groupName, consumerName, params, stringStreamEntryIDMap),
				new StreamEntryConverter.MapEntryStreamEntryConverter((k)->k)))
				.run(args);
	}

	@Override
	public List<StreamEntry> xRevRange(final String key, final StreamEntryId end, final StreamEntryId start) {
		final CommandArguments args = CommandArguments.create("key", key).put("end", end).put("start", start);
		final StreamEntryID endID = (new StreamEntryIdConverter()).convert(end);
		final StreamEntryID startID = (new StreamEntryIdConverter()).convert(start);
		return new JedisCommand<List<StreamEntry>>(client, ProtocolCommand.XREVRANGE)
				.general((cmd)->cmd.xrevrange(key, endID, startID), new StreamEntryConverter.ListStreamEntryConverter())
				.pipeline((cmd)->cmd.xrevrange(key, endID, startID),
						new StreamEntryConverter.ListStreamEntryConverter())
				.transaction((cmd)->cmd.xrevrange(key, endID, startID),
						new StreamEntryConverter.ListStreamEntryConverter())
				.run(args);
	}

	@Override
	public List<StreamEntry> xRevRange(final String key, final StreamEntryId end, final StreamEntryId start,
									   final long count) {
		final CommandArguments args = CommandArguments.create("key", key).put("end", end).put("start", start)
				.put("count", count);
		final StreamEntryID endID = (new StreamEntryIdConverter()).convert(end);
		final StreamEntryID startID = (new StreamEntryIdConverter()).convert(start);
		return new LettuceCommand<>(client, ProtocolCommand.XREVRANGE, (cmd)->cmd.xrevrange(key,
				xTrimArgument.isApproximateTrimming(), limit), (value)->value)
				.run(args);
		return new JedisCommand<List<StreamEntry>>(client, ProtocolCommand.XREVRANGE)
				.general((cmd)->cmd.xrevrange(key, endID, startID, (int) count),
						new StreamEntryConverter.ListStreamEntryConverter())
				.pipeline((cmd)->cmd.xrevrange(key, endID, startID, (int) count),
						new StreamEntryConverter.ListStreamEntryConverter())
				.transaction((cmd)->cmd.xrevrange(key, endID, startID, (int) count),
						new StreamEntryConverter.ListStreamEntryConverter())
				.run(args);
	}

	@Override
	public Long xTrim(final byte[] key, final XTrimArgument xTrimArgument) {
		final CommandArguments args = CommandArguments.create("key", key).put("xTrimArgument", xTrimArgument);
		return new LettuceCommand<>(client, ProtocolCommand.XTRIM, (cmd)->cmd.xtrim(key,
				xTrimArgument.isApproximateTrimming(), Long.MAX_VALUE), (value)->value)
				.run(args);
	}

	@Override
	public Long xTrim(final byte[] key, final XTrimArgument xTrimArgument, final long limit) {
		final CommandArguments args = CommandArguments.create("key", key).put("xTrimArgument", xTrimArgument)
				.put("limit", limit);
		return new LettuceCommand<>(client, ProtocolCommand.XTRIM, (cmd)->cmd.xtrim(key,
				xTrimArgument.isApproximateTrimming(), limit), (value)->value)
				.run(args);
	}

}
