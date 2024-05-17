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
import com.buession.redis.core.internal.convert.lettuce.params.StreamEntryIdConverter;
import com.buession.redis.core.internal.convert.lettuce.response.StreamConsumersInfoConverter;
import com.buession.redis.core.internal.convert.lettuce.response.StreamEntryIDConverter;
import com.buession.redis.core.internal.convert.lettuce.response.StreamFullInfoConverter;
import com.buession.redis.core.internal.convert.lettuce.response.StreamGroupInfoConverter;
import com.buession.redis.core.internal.convert.lettuce.response.StreamInfoConverter;
import com.buession.redis.core.internal.convert.lettuce.response.StreamMessageConverter;
import com.buession.redis.core.internal.convert.lettuce.response.StreamPendingConverter;
import com.buession.redis.core.internal.convert.lettuce.response.StreamPendingSummaryConverter;
import com.buession.redis.core.internal.convert.response.OkStatusConverter;
import com.buession.redis.core.internal.lettuce.LettuceXAddArgs;
import com.buession.redis.core.internal.lettuce.LettuceXClaimArgs;
import com.buession.redis.core.internal.lettuce.LettuceXGroupCreateArgs;
import com.buession.redis.core.internal.lettuce.LettuceXReadArgs;
import io.lettuce.core.Consumer;
import io.lettuce.core.Limit;
import io.lettuce.core.Range;
import io.lettuce.core.XReadArgs;

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
	public List<StreamEntryId> xClaimJustId(final byte[] key, final byte[] groupName, final byte[] consumerName,
											final int minIdleTime, final StreamEntryId... ids) {
		final CommandArguments args = CommandArguments.create("key", key).put("groupName", groupName)
				.put("consumerName", consumerName).put("minIdleTime", minIdleTime).put("ids", (Object[]) ids);
		return new LettuceCommand<>(client, ProtocolCommand.XCLAIM, (cmd)->cmd.xclaim(key,
				Consumer.from(groupName, consumerName), new LettuceXClaimArgs(minIdleTime, true),
				(new StreamEntryIdConverter.ArrayStreamEntryIdConverter()).convert(ids)),
				new StreamMessageConverter.ListStreamMessageStreamEntryIdConverter())
				.run(args);
	}

	@Override
	public List<StreamEntryId> xClaimJustId(final byte[] key, final byte[] groupName, final byte[] consumerName,
											final int minIdleTime, final StreamEntryId[] ids,
											final XClaimArgument xClaimArgument) {
		final CommandArguments args = CommandArguments.create("key", key).put("groupName", groupName)
				.put("consumerName", consumerName).put("minIdleTime", minIdleTime).put("ids", (Object[]) ids)
				.put("xClaimArgument", xClaimArgument);
		return new LettuceCommand<>(client, ProtocolCommand.XCLAIM, (cmd)->cmd.xclaim(key, Consumer.from(groupName,
						consumerName), LettuceXClaimArgs.from(xClaimArgument).minIdleTime(minIdleTime).justid(),
				(new StreamEntryIdConverter.ArrayStreamEntryIdConverter()).convert(ids)),
				new StreamMessageConverter.ListStreamMessageStreamEntryIdConverter())
				.run(args);
	}

	@Override
	public Long xDel(final byte[] key, final StreamEntryId... ids) {
		final CommandArguments args = CommandArguments.create("key", key).put("ids", (Object[]) ids);
		return new LettuceCommand<>(client, ProtocolCommand.XDEL, (cmd)->cmd.xdel(key,
				(new StreamEntryIdConverter.ArrayStreamEntryIdConverter()).convert(ids)), (v)->v)
				.run(args);
	}

	@Override
	public Status xGroupCreate(final byte[] key, final byte[] groupName, final StreamEntryId id,
							   final boolean makeStream) {
		final CommandArguments args = CommandArguments.create("key", key).put("groupName", groupName)
				.put("id", id);
		return new LettuceCommand<>(client, ProtocolCommand.XGROUP_CREATE, (cmd)->cmd.xgroupCreate(
				XReadArgs.StreamOffset.latest(key), groupName, new LettuceXGroupCreateArgs(makeStream)),
				new OkStatusConverter())
				.run(args);
	}

	@Override
	public Status xGroupCreateConsumer(final String key, final String groupName, final String consumerName) {
		final CommandArguments args = CommandArguments.create("key", key).put("groupName", groupName)
				.put("consumerName", consumerName);
		return new LettuceCommand<Status, Status>(client, ProtocolCommand.XGROUP_CREATECONSUMER)
				.run(args);
	}

	@Override
	public Status xGroupCreateConsumer(final byte[] key, final byte[] groupName, final byte[] consumerName) {
		final CommandArguments args = CommandArguments.create("key", key).put("groupName", groupName)
				.put("consumerName", consumerName);
		return new LettuceCommand<Status, Status>(client, ProtocolCommand.XGROUP_CREATECONSUMER)
				.run(args);
	}

	@Override
	public Long xGroupDelConsumer(final byte[] key, final byte[] groupName, final byte[] consumerName) {
		final CommandArguments args = CommandArguments.create("key", key).put("groupName", groupName)
				.put("consumerName", consumerName);
		return new LettuceCommand<>(client, ProtocolCommand.XGROUP_DELCONSUMER, (cmd)->cmd.xgroupDelconsumer(
				key, Consumer.from(groupName, consumerName)), (v)->v ? 1L : 0L)
				.run(args);
	}

	@Override
	public Status xGroupDestroy(final byte[] key, final byte[] groupName) {
		final CommandArguments args = CommandArguments.create("key", key).put("groupName", groupName);
		return new LettuceCommand<>(client, ProtocolCommand.XGROUP_DESTROY, (cmd)->cmd.xgroupDestroy(
				key, groupName), new BooleanStatusConverter())
				.run(args);
	}

	@Override
	public Status xGroupSetId(final byte[] key, final byte[] groupName, final StreamEntryId id) {
		final CommandArguments args = CommandArguments.create("key", key).put("groupName", groupName).put("id", id);
		return new LettuceCommand<>(client, ProtocolCommand.XGROUP_SETID, (cmd)->cmd.xgroupSetid(
				XReadArgs.StreamOffset.from(key, id.toString()), groupName), new OkStatusConverter())
				.run(args);
	}

	@Override
	public List<StreamConsumer> xInfoConsumers(final byte[] key, final byte[] groupName) {
		final CommandArguments args = CommandArguments.create("key", key).put("groupName", groupName);
		return new LettuceCommand<>(client, ProtocolCommand.XINFO_CONSUMERS, (cmd)->cmd.xinfoConsumers(key, groupName),
				new StreamConsumersInfoConverter.ListStreamConsumersInfoConverter())
				.run(args);
	}

	@Override
	public List<StreamGroup> xInfoGroups(final byte[] key) {
		final CommandArguments args = CommandArguments.create("key", key);
		return new LettuceCommand<>(client, ProtocolCommand.XINFO_GROUPS, (cmd)->cmd.xinfoGroups(key),
				new StreamGroupInfoConverter.ListStreamGroupInfoConverter())
				.run(args);
	}

	@Override
	public Stream xInfoStream(final byte[] key) {
		final CommandArguments args = CommandArguments.create("key", key);
		return new LettuceCommand<>(client, ProtocolCommand.XINFO_STREAM, (cmd)->cmd.xinfoStream(
				key), new StreamInfoConverter())
				.run(args);
	}

	@Override
	public StreamFull xInfoStream(final byte[] key, final boolean full) {
		final CommandArguments args = CommandArguments.create("key", key).put("full", full);
		return new LettuceCommand<>(client, ProtocolCommand.XINFO_STREAM, (cmd)->cmd.xinfoStream(key),
				new StreamFullInfoConverter())
				.run(args);
	}

	@Override
	public StreamFull xInfoStream(final byte[] key, final boolean full, final long count) {
		final CommandArguments args = CommandArguments.create("key", key).put("full", full).put("count", count);
		return new LettuceCommand<>(client, ProtocolCommand.XINFO_STREAM, (cmd)->cmd.xinfoStream(key),
				new StreamFullInfoConverter())
				.run(args);
	}

	@Override
	public Long xLen(final byte[] key) {
		final CommandArguments args = CommandArguments.create("key", key);
		return new LettuceCommand<>(client, ProtocolCommand.XLEN, (cmd)->cmd.xlen(key), (v)->v)
				.run(args);
	}

	@Override
	public StreamPendingSummary xPending(final byte[] key, final byte[] groupName) {
		final CommandArguments args = CommandArguments.create("key", key).put("groupName", groupName);
		return new LettuceCommand<>(client, ProtocolCommand.XPENDING, (cmd)->cmd.xpending(key, groupName),
				new StreamPendingSummaryConverter())
				.run(args);
	}

	@Override
	public List<StreamPending> xPending(final byte[] key, final byte[] groupName, final long minIdleTime) {
		final CommandArguments args = CommandArguments.create("key", key).put("groupName", groupName)
				.put("minIdleTime", minIdleTime);
		return new LettuceCommand<>(client, ProtocolCommand.XPENDING, (cmd)->cmd.xpending(key, groupName),
				new StreamPendingConverter.ListStreamPendingConverter())
				.run(args);
	}

	@Override
	public List<StreamPending> xPending(final byte[] key, final byte[] groupName, final StreamEntryId start,
										final StreamEntryId end, final long count) {
		final CommandArguments args = CommandArguments.create("key", key).put("groupName", groupName)
				.put("start", start).put("end", end).put("count", count);
		return new LettuceCommand<>(client, ProtocolCommand.XPENDING, (cmd)->cmd.xpending(key, groupName,
				Range.create(start.toString(), end.toString()), Limit.from(count)),
				new StreamPendingConverter.ListStreamPendingConverter())
				.run(args);
	}

	@Override
	public List<StreamPending> xPending(final byte[] key, final byte[] groupName, final byte[] consumerName) {
		final CommandArguments args = CommandArguments.create("key", key).put("groupName", groupName)
				.put("consumerName", consumerName);
		return new LettuceCommand<>(client, ProtocolCommand.XPENDING, (cmd)->cmd.xpending(key,
				Consumer.from(groupName, consumerName), Range.unbounded(), Limit.unlimited()),
				new StreamPendingConverter.ListStreamPendingConverter())
				.run(args);
	}

	@Override
	public List<StreamPending> xPending(final byte[] key, final byte[] groupName, final long minIdleTime,
										final StreamEntryId start, final StreamEntryId end, final long count) {
		final CommandArguments args = CommandArguments.create("key", key).put("groupName", groupName)
				.put("minIdleTime", minIdleTime).put("start", start).put("end", end).put("count", count);
		return new LettuceCommand<>(client, ProtocolCommand.XPENDING, (cmd)->cmd.xpending(key, groupName,
				Range.create(start.toString(), end.toString()), Limit.from(count)),
				new StreamPendingConverter.ListStreamPendingConverter())
				.run(args);
	}

	@Override
	public List<StreamPending> xPending(final byte[] key, final byte[] groupName, final long minIdleTime,
										final byte[] consumerName) {
		final CommandArguments args = CommandArguments.create("key", key).put("groupName", groupName)
				.put("minIdleTime", minIdleTime).put("consumerName", consumerName);
		return new LettuceCommand<>(client, ProtocolCommand.XPENDING,
				(cmd)->cmd.xpending(key, Consumer.from(groupName, consumerName), Range.unbounded(), Limit.unlimited()),
				new StreamPendingConverter.ListStreamPendingConverter())
				.run(args);
	}

	@Override
	public List<StreamPending> xPending(final byte[] key, final byte[] groupName, final StreamEntryId start,
										final StreamEntryId end, final long count, final byte[] consumerName) {
		final CommandArguments args = CommandArguments.create("key", key).put("groupName", groupName)
				.put("consumerName", consumerName);
		return new LettuceCommand<>(client, ProtocolCommand.XPENDING, (cmd)->cmd.xpending(key,
				Consumer.from(groupName, consumerName), Range.create(start.toString(), end.toString()),
				Limit.from(count)), new StreamPendingConverter.ListStreamPendingConverter())
				.run(args);
	}

	@Override
	public List<StreamPending> xPending(final byte[] key, final byte[] groupName, final long minIdleTime,
										final StreamEntryId start, final StreamEntryId end, final long count,
										final byte[] consumerName) {
		final CommandArguments args = CommandArguments.create("key", key).put("groupName", groupName)
				.put("minIdleTime", minIdleTime).put("consumerName", consumerName);
		return new LettuceCommand<>(client, ProtocolCommand.XPENDING, (cmd)->cmd.xpending(key,
				Consumer.from(groupName, consumerName), Range.create(start.toString(), end.toString()),
				Limit.from(count)), new StreamPendingConverter.ListStreamPendingConverter())
				.run(args);
	}

	@Override
	public List<StreamEntry> xRange(final byte[] key, final StreamEntryId start, final StreamEntryId end) {
		final CommandArguments args = CommandArguments.create("key", key).put("start", start).put("end", end);
		return new LettuceCommand<>(client, ProtocolCommand.XRANGE, (cmd)->cmd.xrange(key,
				Range.create(start.toString(), end.toString())),
				new StreamMessageConverter.ListStreamMessageConverter())
				.run(args);
	}

	@Override
	public List<StreamEntry> xRange(final byte[] key, final StreamEntryId start, final StreamEntryId end,
									final long count) {
		final CommandArguments args = CommandArguments.create("key", key).put("start", start).put("end", end);
		return new LettuceCommand<>(client, ProtocolCommand.XRANGE, (cmd)->cmd.xrange(key,
				Range.create(start.toString(), end.toString()), Limit.from(count)),
				new StreamMessageConverter.ListStreamMessageConverter())
				.run(args);
	}

	@Override
	public List<Map<String, List<StreamEntry>>> xRead(final Map<String, StreamEntryId> streams) {
		final CommandArguments args = CommandArguments.create("streams", streams);
		return xRead(null, streams, args);
	}

	@Override
	public List<Map<String, List<StreamEntry>>> xRead(final long count, final Map<String, StreamEntryId> streams) {
		final CommandArguments args = CommandArguments.create("count", count).put("streams", streams);
		return xRead(new LettuceXReadArgs(count), streams, args);
	}

	@Override
	public List<Map<String, List<StreamEntry>>> xRead(final int block, final Map<String, StreamEntryId> streams) {
		final CommandArguments args = CommandArguments.create("block", block).put("streams", streams);
		return xRead(new LettuceXReadArgs(block), streams, args);
	}

	@Override
	public List<Map<String, List<StreamEntry>>> xRead(final long count, final int block,
													  final Map<String, StreamEntryId> streams) {
		final CommandArguments args = CommandArguments.create("count", count).put("block", block)
				.put("streams", streams);
		return xRead(new LettuceXReadArgs(block, count), streams, args);
	}

	@Override
	public List<Map<String, List<StreamEntry>>> xReadGroup(final String groupName, final String consumerName,
														   final Map<String, StreamEntryId> streams) {
		final CommandArguments args = CommandArguments.create("groupName", groupName).put("consumerName", consumerName)
				.put("streams", streams);
		return xReadGroup(null, groupName, consumerName, streams, args);
	}

	@Override
	public List<Map<byte[], List<StreamEntry>>> xReadGroup(final byte[] groupName, final byte[] consumerName,
														   final Map<byte[], StreamEntryId> streams) {
		final CommandArguments args = CommandArguments.create("groupName", groupName).put("consumerName", consumerName)
				.put("streams", streams);
		return xReadGroup(null, groupName, consumerName, streams, args);
	}

	@Override
	public List<Map<String, List<StreamEntry>>> xReadGroup(final String groupName, final String consumerName,
														   final long count, final Map<String, StreamEntryId> streams) {
		final CommandArguments args = CommandArguments.create("groupName", groupName).put("consumerName", consumerName)
				.put("count", count).put("streams", streams);
		return xReadGroup(new LettuceXReadArgs(count), groupName, consumerName, streams, args);
	}

	@Override
	public List<Map<byte[], List<StreamEntry>>> xReadGroup(final byte[] groupName, final byte[] consumerName,
														   final long count, final Map<byte[], StreamEntryId> streams) {
		final CommandArguments args = CommandArguments.create("groupName", groupName).put("consumerName", consumerName)
				.put("count", count).put("streams", streams);
		return xReadGroup(new LettuceXReadArgs(count), groupName, consumerName, streams, args);
	}

	@Override
	public List<Map<String, List<StreamEntry>>> xReadGroup(final String groupName, final String consumerName,
														   final int block, final Map<String, StreamEntryId> streams) {
		final CommandArguments args = CommandArguments.create("groupName", groupName).put("consumerName", consumerName)
				.put("block", block).put("streams", streams);
		return xReadGroup(new LettuceXReadArgs(block), groupName, consumerName, streams, args);
	}

	@Override
	public List<Map<byte[], List<StreamEntry>>> xReadGroup(final byte[] groupName, final byte[] consumerName,
														   final int block, final Map<byte[], StreamEntryId> streams) {
		final CommandArguments args = CommandArguments.create("groupName", groupName).put("consumerName", consumerName)
				.put("block", block).put("streams", streams);
		return xReadGroup(new LettuceXReadArgs(block), groupName, consumerName, streams, args);
	}

	@Override
	public List<Map<String, List<StreamEntry>>> xReadGroup(final String groupName, final String consumerName,
														   final boolean isNoAck,
														   final Map<String, StreamEntryId> streams) {
		final CommandArguments args = CommandArguments.create("groupName", groupName).put("consumerName", consumerName)
				.put("isNoAck", isNoAck).put("streams", streams);
		return xReadGroup((new LettuceXReadArgs()).noack(isNoAck), groupName, consumerName, streams, args);
	}

	@Override
	public List<Map<byte[], List<StreamEntry>>> xReadGroup(final byte[] groupName, final byte[] consumerName,
														   final boolean isNoAck,
														   final Map<byte[], StreamEntryId> streams) {
		final CommandArguments args = CommandArguments.create("groupName", groupName).put("consumerName", consumerName)
				.put("isNoAck", isNoAck).put("streams", streams);
		return xReadGroup((new LettuceXReadArgs()).noack(isNoAck), groupName, consumerName, streams, args);
	}

	@Override
	public List<Map<String, List<StreamEntry>>> xReadGroup(final String groupName, final String consumerName,
														   final long count, final int block,
														   final Map<String, StreamEntryId> streams) {
		final CommandArguments args = CommandArguments.create("groupName", groupName).put("consumerName", consumerName)
				.put("count", count).put("block", block).put("streams", streams);
		return xReadGroup(new LettuceXReadArgs(block, count), groupName, consumerName, streams, args);
	}

	@Override
	public List<Map<byte[], List<StreamEntry>>> xReadGroup(final byte[] groupName, final byte[] consumerName,
														   final long count, final int block,
														   final Map<byte[], StreamEntryId> streams) {
		final CommandArguments args = CommandArguments.create("groupName", groupName).put("consumerName", consumerName)
				.put("count", count).put("block", block).put("streams", streams);
		return xReadGroup(new LettuceXReadArgs(block, count), groupName, consumerName, streams, args);
	}

	@Override
	public List<Map<String, List<StreamEntry>>> xReadGroup(final String groupName, final String consumerName,
														   final long count, final boolean isNoAck,
														   final Map<String, StreamEntryId> streams) {
		final CommandArguments args = CommandArguments.create("groupName", groupName).put("consumerName", consumerName)
				.put("count", count).put("isNoAck", isNoAck).put("streams", streams);
		return xReadGroup(new LettuceXReadArgs(isNoAck, count), groupName, consumerName, streams, args);
	}

	@Override
	public List<Map<byte[], List<StreamEntry>>> xReadGroup(final byte[] groupName, final byte[] consumerName,
														   final long count, final boolean isNoAck,
														   final Map<byte[], StreamEntryId> streams) {
		final CommandArguments args = CommandArguments.create("groupName", groupName).put("consumerName", consumerName)
				.put("count", count).put("isNoAck", isNoAck).put("streams", streams);
		return xReadGroup(new LettuceXReadArgs(isNoAck, count), groupName, consumerName, streams, args);
	}

	@Override
	public List<Map<String, List<StreamEntry>>> xReadGroup(final String groupName, final String consumerName,
														   final int block, final boolean isNoAck,
														   final Map<String, StreamEntryId> streams) {
		final CommandArguments args = CommandArguments.create("groupName", groupName).put("consumerName", consumerName)
				.put("block", block).put("isNoAck", isNoAck).put("streams", streams);
		return xReadGroup((new LettuceXReadArgs(block)).noack(isNoAck), groupName, consumerName, streams, args);
	}

	@Override
	public List<Map<byte[], List<StreamEntry>>> xReadGroup(final byte[] groupName, final byte[] consumerName,
														   final int block, final boolean isNoAck,
														   final Map<byte[], StreamEntryId> streams) {
		final CommandArguments args = CommandArguments.create("groupName", groupName).put("consumerName", consumerName)
				.put("block", block).put("isNoAck", isNoAck).put("streams", streams);
		return xReadGroup((new LettuceXReadArgs(block)).noack(isNoAck), groupName, consumerName, streams, args);
	}

	@Override
	public List<Map<String, List<StreamEntry>>> xReadGroup(final String groupName, final String consumerName,
														   final long count, final int block, final boolean isNoAck,
														   final Map<String, StreamEntryId> streams) {
		final CommandArguments args = CommandArguments.create("groupName", groupName).put("consumerName", consumerName)
				.put("count", count).put("block", block).put("isNoAck", isNoAck).put("streams", streams);
		return xReadGroup(new LettuceXReadArgs(block, isNoAck, count), groupName, consumerName, streams, args);
	}

	@Override
	public List<Map<byte[], List<StreamEntry>>> xReadGroup(final byte[] groupName, final byte[] consumerName,
														   final long count, final int block, final boolean isNoAck,
														   final Map<byte[], StreamEntryId> streams) {
		final CommandArguments args = CommandArguments.create("groupName", groupName).put("consumerName", consumerName)
				.put("count", count).put("block", block).put("isNoAck", isNoAck).put("streams", streams);
		return xReadGroup(new LettuceXReadArgs(block, isNoAck, count), groupName, consumerName, streams, args);
	}

	@Override
	public List<StreamEntry> xRevRange(final byte[] key, final StreamEntryId end, final StreamEntryId start) {
		final CommandArguments args = CommandArguments.create("key", key).put("end", end).put("start", start);
		return new LettuceCommand<>(client, ProtocolCommand.XREVRANGE, (cmd)->cmd.xrevrange(key,
				Range.create(start.toString(), end.toString())),
				new StreamMessageConverter.ListStreamMessageConverter())
				.run(args);
	}

	@Override
	public List<StreamEntry> xRevRange(final byte[] key, final StreamEntryId end, final StreamEntryId start,
									   final long count) {
		final CommandArguments args = CommandArguments.create("key", key).put("end", end).put("start", start)
				.put("count", count);
		return new LettuceCommand<>(client, ProtocolCommand.XREVRANGE, (cmd)->cmd.xrevrange(key,
				Range.create(start.toString(), end.toString()), Limit.from(count)),
				new StreamMessageConverter.ListStreamMessageConverter())
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
