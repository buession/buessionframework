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

import com.buession.core.converter.Converter;
import com.buession.core.converter.ListConverter;
import com.buession.lang.Status;
import com.buession.redis.client.lettuce.LettuceSentinelClient;
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
import com.buession.redis.core.internal.convert.lettuce.response.StreamEntryIDConverter;
import com.buession.redis.core.internal.convert.lettuce.response.StreamMessageConverter;
import com.buession.redis.core.internal.convert.lettuce.response.StreamMessageMapConverter;
import com.buession.redis.core.internal.lettuce.LettuceXAddArgs;
import com.buession.redis.core.internal.lettuce.LettuceXClaimArgs;
import com.buession.redis.core.internal.lettuce.LettuceXReadArgs;
import com.buession.redis.utils.SafeEncoder;
import io.lettuce.core.Consumer;
import io.lettuce.core.Limit;
import io.lettuce.core.Range;
import io.lettuce.core.StreamMessage;
import io.lettuce.core.XAddArgs;
import io.lettuce.core.XClaimArgs;
import io.lettuce.core.XReadArgs;

import java.util.List;
import java.util.Map;

/**
 * Lettuce 哨兵模式 Stream 命令操作
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public final class LettuceSentinelStreamOperations extends AbstractStreamOperations<LettuceSentinelClient> {

	public LettuceSentinelStreamOperations(final LettuceSentinelClient client) {
		super(client);
	}

	@Override
	public Long xAck(final byte[] key, final byte[] groupName, final StreamEntryId... ids) {
		final CommandArguments args = CommandArguments.create(key).put("groupName", groupName)
				.put("ids", (Object[]) ids);

		if(isPipeline()){
			return new LettuceSentinelPipelineCommand<Long, Long>(client, ProtocolCommand.XACK)
					.run(args);
		}else if(isTransaction()){
			return new LettuceSentinelTransactionCommand<Long, Long>(client, ProtocolCommand.XACK)
					.run(args);
		}else{
			return new LettuceSentinelCommand<Long, Long>(client, ProtocolCommand.XACK)
					.run(args);
		}
	}

	@Override
	public StreamEntryId xAdd(final byte[] key, final StreamEntryId id, final Map<byte[], byte[]> hash) {
		final CommandArguments args = CommandArguments.create(key).put("id", id).put("hash", hash);
		final XAddArgs xAddArgs = new LettuceXAddArgs(id);

		return xAdd(key, hash, xAddArgs, args);
	}

	@Override
	public StreamEntryId xAdd(final byte[] key, final StreamEntryId id, final Map<byte[], byte[]> hash,
							  final XAddArgument xAddArgument) {
		final CommandArguments args = CommandArguments.create(key).put("id", id).put("hash", hash)
				.put("xAddArgument", xAddArgument);
		final StreamEntryIdConverter streamEntryIdConverter = new StreamEntryIdConverter();
		final XAddArgs xAddArgs = LettuceXAddArgs.from(xAddArgument).id(streamEntryIdConverter.convert(id));

		return xAdd(key, hash, xAddArgs, args);
	}

	@Override
	public Map<StreamEntryId, List<StreamEntry>> xAutoClaim(final String key, final String groupName,
															final String consumerName, final int minIdleTime,
															final StreamEntryId start) {
		final CommandArguments args = CommandArguments.create(key).put("groupName", groupName)
				.put("consumerName", consumerName).put("minIdleTime", minIdleTime).put("start", start);
		return xAutoClaim(args);
	}

	@Override
	public Map<StreamEntryId, List<StreamEntry>> xAutoClaim(final byte[] key, final byte[] groupName,
															final byte[] consumerName, final int minIdleTime,
															final StreamEntryId start) {
		final CommandArguments args = CommandArguments.create(key).put("groupName", groupName)
				.put("consumerName", consumerName).put("minIdleTime", minIdleTime).put("start", start);
		return xAutoClaim(args);
	}

	@Override
	public Map<StreamEntryId, List<StreamEntry>> xAutoClaim(final String key, final String groupName,
															final String consumerName, final int minIdleTime,
															final StreamEntryId start, final long count) {
		final CommandArguments args = CommandArguments.create(key).put("groupName", groupName)
				.put("consumerName", consumerName).put("minIdleTime", minIdleTime).put("start", start)
				.put("count", count);
		return xAutoClaim(args);
	}

	@Override
	public Map<StreamEntryId, List<StreamEntry>> xAutoClaim(final byte[] key, final byte[] groupName,
															final byte[] consumerName, final int minIdleTime,
															final StreamEntryId start, final long count) {
		final CommandArguments args = CommandArguments.create(key).put("groupName", groupName)
				.put("consumerName", consumerName).put("minIdleTime", minIdleTime).put("start", start)
				.put("count", count);
		return xAutoClaim(args);
	}

	@Override
	public Map<StreamEntryId, List<StreamEntryId>> xAutoClaimJustId(final String key, final String groupName,
																	final String consumerName, final int minIdleTime,
																	final StreamEntryId start) {
		final CommandArguments args = CommandArguments.create(key).put("groupName", groupName)
				.put("consumerName", consumerName).put("minIdleTime", minIdleTime).put("start", start)
				.put("count");
		return xAutoClaimJustId(args);
	}

	@Override
	public Map<StreamEntryId, List<StreamEntryId>> xAutoClaimJustId(final byte[] key, final byte[] groupName,
																	final byte[] consumerName, final int minIdleTime,
																	final StreamEntryId start) {
		final CommandArguments args = CommandArguments.create(key).put("groupName", groupName)
				.put("consumerName", consumerName).put("minIdleTime", minIdleTime).put("start", start)
				.put("count");
		return xAutoClaimJustId(args);
	}

	@Override
	public Map<StreamEntryId, List<StreamEntryId>> xAutoClaimJustId(final byte[] key, final byte[] groupName,
																	final byte[] consumerName, final int minIdleTime,
																	final StreamEntryId start, final long count) {
		final CommandArguments args = CommandArguments.create(key).put("groupName", groupName)
				.put("consumerName", consumerName).put("minIdleTime", minIdleTime).put("start", start)
				.put("count", count);
		return xAutoClaimJustId(args);
	}

	@Override
	public Map<StreamEntryId, List<StreamEntryId>> xAutoClaimJustId(final String key, final String groupName,
																	final String consumerName, final int minIdleTime,
																	final StreamEntryId start, final long count) {
		final CommandArguments args = CommandArguments.create(key).put("groupName", groupName)
				.put("consumerName", consumerName).put("minIdleTime", minIdleTime).put("start", start)
				.put("count", count);
		return xAutoClaimJustId(args);
	}

	@Override
	public List<StreamEntry> xClaim(final byte[] key, final byte[] groupName, final byte[] consumerName,
									final int minIdleTime, final StreamEntryId... ids) {
		final CommandArguments args = CommandArguments.create(key).put("groupName", groupName)
				.put("consumerName", consumerName).put("minIdleTime", minIdleTime).put("ids", (Object[]) ids);
		final Consumer<byte[]> consumer = Consumer.from(groupName, consumerName);
		final XClaimArgs xClaimArgs = new LettuceXClaimArgs(minIdleTime);

		return xClaim(key, ids, consumer, xClaimArgs, args);
	}

	@Override
	public List<StreamEntry> xClaim(final byte[] key, final byte[] groupName, final byte[] consumerName,
									final int minIdleTime, final StreamEntryId[] ids,
									final XClaimArgument xClaimArgument) {
		final CommandArguments args = CommandArguments.create(key).put("groupName", groupName)
				.put("consumerName", consumerName).put("minIdleTime", minIdleTime).put("ids", (Object[]) ids)
				.put("xClaimArgument", xClaimArgument);
		final Consumer<byte[]> consumer = Consumer.from(groupName, consumerName);
		final XClaimArgs xClaimArgs = LettuceXClaimArgs.from(xClaimArgument).minIdleTime(minIdleTime);

		return xClaim(key, ids, consumer, xClaimArgs, args);
	}

	@Override
	public List<StreamEntryId> xClaimJustId(final byte[] key, final byte[] groupName, final byte[] consumerName,
											final int minIdleTime, final StreamEntryId... ids) {
		final CommandArguments args = CommandArguments.create(key).put("groupName", groupName)
				.put("consumerName", consumerName).put("minIdleTime", minIdleTime).put("ids", (Object[]) ids);
		final Consumer<byte[]> consumer = Consumer.from(groupName, consumerName);
		final XClaimArgs xClaimArgs = new LettuceXClaimArgs(minIdleTime, true);

		return xClaimJustId(key, ids, consumer, xClaimArgs, args);
	}

	@Override
	public List<StreamEntryId> xClaimJustId(final byte[] key, final byte[] groupName, final byte[] consumerName,
											final int minIdleTime, final StreamEntryId[] ids,
											final XClaimArgument xClaimArgument) {
		final CommandArguments args = CommandArguments.create(key).put("groupName", groupName)
				.put("consumerName", consumerName).put("minIdleTime", minIdleTime).put("ids", (Object[]) ids)
				.put("xClaimArgument", xClaimArgument);
		final Consumer<byte[]> consumer = Consumer.from(groupName, consumerName);
		final XClaimArgs xClaimArgs = LettuceXClaimArgs.from(xClaimArgument).minIdleTime(minIdleTime).justid();

		return xClaimJustId(key, ids, consumer, xClaimArgs, args);
	}

	@Override
	public Long xDel(final byte[] key, final StreamEntryId... ids) {
		final CommandArguments args = CommandArguments.create(key).put("ids", (Object[]) ids);

		if(isPipeline()){
			return new LettuceSentinelPipelineCommand<Long, Long>(client, ProtocolCommand.XDEL)
					.run(args);
		}else if(isTransaction()){
			return new LettuceSentinelTransactionCommand<Long, Long>(client, ProtocolCommand.XDEL)
					.run(args);
		}else{
			return new LettuceSentinelCommand<Long, Long>(client, ProtocolCommand.XDEL)
					.run(args);
		}
	}

	@Override
	public Status xGroupCreate(final byte[] key, final byte[] groupName, final StreamEntryId id,
							   final boolean makeStream) {
		final CommandArguments args = CommandArguments.create(key).put("groupName", groupName)
				.put("id", id);

		if(isPipeline()){
			return new LettuceSentinelPipelineCommand<Status, Status>(client, ProtocolCommand.XGROUP_CREATE)
					.run(args);
		}else if(isTransaction()){
			return new LettuceSentinelTransactionCommand<Status, Status>(client, ProtocolCommand.XGROUP_CREATE)
					.run(args);
		}else{
			return new LettuceSentinelCommand<Status, Status>(client, ProtocolCommand.XGROUP_CREATE)
					.run(args);
		}
	}

	@Override
	public Status xGroupCreateConsumer(final String key, final String groupName, final String consumerName) {
		final CommandArguments args = CommandArguments.create(key).put("groupName", groupName)
				.put("consumerName", consumerName);
		return xGroupCreateConsumer(args);
	}

	@Override
	public Status xGroupCreateConsumer(final byte[] key, final byte[] groupName, final byte[] consumerName) {
		final CommandArguments args = CommandArguments.create(key).put("groupName", groupName)
				.put("consumerName", consumerName);
		return xGroupCreateConsumer(args);
	}

	@Override
	public Long xGroupDelConsumer(final byte[] key, final byte[] groupName, final byte[] consumerName) {
		final CommandArguments args = CommandArguments.create(key).put("groupName", groupName)
				.put("consumerName", consumerName);
		final Consumer<byte[]> consumer = Consumer.from(groupName, consumerName);
		final Converter<Boolean, Long> converter = (v)->v ? 1L : 0L;

		if(isPipeline()){
			return new LettuceSentinelPipelineCommand<Long, Long>(client, ProtocolCommand.XGROUP_DELCONSUMER)
					.run(args);
		}else if(isTransaction()){
			return new LettuceSentinelTransactionCommand<Long, Long>(client, ProtocolCommand.XGROUP_DELCONSUMER)
					.run(args);
		}else{
			return new LettuceSentinelCommand<Long, Long>(client, ProtocolCommand.XGROUP_DELCONSUMER)
					.run(args);
		}
	}

	@Override
	public Status xGroupDestroy(final byte[] key, final byte[] groupName) {
		final CommandArguments args = CommandArguments.create(key).put("groupName", groupName);

		if(isPipeline()){
			return new LettuceSentinelPipelineCommand<Status, Status>(client, ProtocolCommand.XGROUP_DESTROY)
					.run(args);
		}else if(isTransaction()){
			return new LettuceSentinelTransactionCommand<Status, Status>(client, ProtocolCommand.XGROUP_DESTROY)
					.run(args);
		}else{
			return new LettuceSentinelCommand<Status, Status>(client, ProtocolCommand.XGROUP_DESTROY)
					.run(args);
		}
	}

	@Override
	public Status xGroupSetId(final byte[] key, final byte[] groupName, final StreamEntryId id) {
		final CommandArguments args = CommandArguments.create(key).put("groupName", groupName).put("id", id);

		if(isPipeline()){
			return new LettuceSentinelPipelineCommand<Status, Status>(client, ProtocolCommand.XGROUP_SETID)
					.run(args);
		}else if(isTransaction()){
			return new LettuceSentinelTransactionCommand<Status, Status>(client, ProtocolCommand.XGROUP_SETID)
					.run(args);
		}else{
			return new LettuceSentinelCommand<Status, Status>(client, ProtocolCommand.XGROUP_SETID)
					.run(args);
		}
	}

	@Override
	public List<StreamConsumer> xInfoConsumers(final byte[] key, final byte[] groupName) {
		final CommandArguments args = CommandArguments.create(key).put("groupName", groupName);

		if(isPipeline()){
			return new LettuceSentinelPipelineCommand<List<StreamConsumer>, List<StreamConsumer>>(client,
					ProtocolCommand.XINFO_CONSUMERS)
					.run(args);
		}else if(isTransaction()){
			return new LettuceSentinelTransactionCommand<List<StreamConsumer>, List<StreamConsumer>>(client,
					ProtocolCommand.XINFO_CONSUMERS)
					.run(args);
		}else{
			return new LettuceSentinelCommand<List<StreamConsumer>, List<StreamConsumer>>(client,
					ProtocolCommand.XINFO_CONSUMERS)
					.run(args);
		}
	}

	@Override
	public List<StreamGroup> xInfoGroups(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);

		if(isPipeline()){
			return new LettuceSentinelPipelineCommand<List<StreamGroup>, List<StreamGroup>>(client,
					ProtocolCommand.XINFO_GROUPS)
					.run(args);
		}else if(isTransaction()){
			return new LettuceSentinelTransactionCommand<List<StreamGroup>, List<StreamGroup>>(client,
					ProtocolCommand.XINFO_GROUPS)
					.run(args);
		}else{
			return new LettuceSentinelCommand<List<StreamGroup>, List<StreamGroup>>(client,
					ProtocolCommand.XINFO_GROUPS)
					.run(args);
		}
	}

	@Override
	public Stream xInfoStream(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);

		if(isPipeline()){
			return new LettuceSentinelPipelineCommand<Stream, Stream>(client, ProtocolCommand.XINFO_STREAM)
					.run(args);
		}else if(isTransaction()){
			return new LettuceSentinelTransactionCommand<Stream, Stream>(client, ProtocolCommand.XINFO_STREAM)
					.run(args);
		}else{
			return new LettuceSentinelCommand<Stream, Stream>(client, ProtocolCommand.XINFO_STREAM)
					.run(args);
		}
	}

	@Override
	public StreamFull xInfoStream(final byte[] key, final boolean full) {
		final CommandArguments args = CommandArguments.create(key).put("full", full);

		if(isPipeline()){
			return new LettuceSentinelPipelineCommand<StreamFull, StreamFull>(client, ProtocolCommand.XINFO_STREAM)
					.run(args);
		}else if(isTransaction()){
			return new LettuceSentinelTransactionCommand<StreamFull, StreamFull>(client, ProtocolCommand.XINFO_STREAM)
					.run(args);
		}else{
			return new LettuceSentinelCommand<StreamFull, StreamFull>(client, ProtocolCommand.XINFO_STREAM)
					.run(args);
		}
	}

	@Override
	public StreamFull xInfoStream(final byte[] key, final boolean full, final long count) {
		final CommandArguments args = CommandArguments.create(key).put("full", full).put("count", count);

		if(isPipeline()){
			return new LettuceSentinelPipelineCommand<StreamFull, StreamFull>(client, ProtocolCommand.XINFO_STREAM)
					.run(args);
		}else if(isTransaction()){
			return new LettuceSentinelTransactionCommand<StreamFull, StreamFull>(client, ProtocolCommand.XINFO_STREAM)
					.run(args);
		}else{
			return new LettuceSentinelCommand<StreamFull, StreamFull>(client, ProtocolCommand.XINFO_STREAM)
					.run(args);
		}
	}

	@Override
	public Long xLen(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);

		if(isPipeline()){
			return new LettuceSentinelPipelineCommand<Long, Long>(client, ProtocolCommand.XLEN)
					.run(args);
		}else if(isTransaction()){
			return new LettuceSentinelTransactionCommand<Long, Long>(client, ProtocolCommand.XLEN)
					.run(args);
		}else{
			return new LettuceSentinelCommand<Long, Long>(client, ProtocolCommand.XLEN)
					.run(args);
		}
	}

	@Override
	public StreamPendingSummary xPending(final byte[] key, final byte[] groupName) {
		final CommandArguments args = CommandArguments.create(key).put("groupName", groupName);

		if(isPipeline()){
			return new LettuceSentinelPipelineCommand<StreamPendingSummary, StreamPendingSummary>(client,
					ProtocolCommand.XPENDING)
					.run(args);
		}else if(isTransaction()){
			return new LettuceSentinelTransactionCommand<StreamPendingSummary, StreamPendingSummary>(client,
					ProtocolCommand.XPENDING)
					.run(args);
		}else{
			return new LettuceSentinelCommand<StreamPendingSummary, StreamPendingSummary>(client,
					ProtocolCommand.XPENDING)
					.run(args);
		}
	}

	@Override
	public List<StreamPending> xPending(final byte[] key, final byte[] groupName, final long minIdleTime) {
		final CommandArguments args = CommandArguments.create(key).put("groupName", groupName)
				.put("minIdleTime", minIdleTime);

		if(isPipeline()){
			return new LettuceSentinelPipelineCommand<List<StreamPending>, List<StreamPending>>(client,
					ProtocolCommand.XPENDING)
					.run(args);
		}else if(isTransaction()){
			return new LettuceSentinelTransactionCommand<List<StreamPending>, List<StreamPending>>(client,
					ProtocolCommand.XPENDING)
					.run(args);
		}else{
			return new LettuceSentinelCommand<List<StreamPending>, List<StreamPending>>(client,
					ProtocolCommand.XPENDING)
					.run(args);
		}
	}

	@Override
	public List<StreamPending> xPending(final byte[] key, final byte[] groupName, final StreamEntryId start,
										final StreamEntryId end, final long count) {
		final CommandArguments args = CommandArguments.create(key).put("groupName", groupName)
				.put("start", start).put("end", end).put("count", count);
		final Range<String> range = Range.create(start.toString(), end.toString());
		final Limit limit = Limit.from(count);

		return xPending(key, groupName, range, limit, args);
	}

	@Override
	public List<StreamPending> xPending(final byte[] key, final byte[] groupName, final byte[] consumerName) {
		final CommandArguments args = CommandArguments.create(key).put("groupName", groupName)
				.put("consumerName", consumerName);
		final Consumer<byte[]> consumer = Consumer.from(groupName, consumerName);
		final Range<String> range = Range.unbounded();
		final Limit limit = Limit.unlimited();

		return xPending(key, consumer, range, limit, args);
	}

	@Override
	public List<StreamPending> xPending(final byte[] key, final byte[] groupName, final long minIdleTime,
										final StreamEntryId start, final StreamEntryId end, final long count) {
		final CommandArguments args = CommandArguments.create(key).put("groupName", groupName)
				.put("minIdleTime", minIdleTime).put("start", start).put("end", end).put("count", count);
		final Range<String> range = Range.create(start.toString(), end.toString());
		final Limit limit = Limit.from(count);

		return xPending(key, groupName, range, limit, args);
	}

	@Override
	public List<StreamPending> xPending(final byte[] key, final byte[] groupName, final long minIdleTime,
										final byte[] consumerName) {
		final CommandArguments args = CommandArguments.create(key).put("groupName", groupName)
				.put("minIdleTime", minIdleTime).put("consumerName", consumerName);
		final Consumer<byte[]> consumer = Consumer.from(groupName, consumerName);
		final Range<String> range = Range.unbounded();
		final Limit limit = Limit.unlimited();

		return xPending(key, consumer, range, limit, args);
	}

	@Override
	public List<StreamPending> xPending(final byte[] key, final byte[] groupName, final StreamEntryId start,
										final StreamEntryId end, final long count, final byte[] consumerName) {
		final CommandArguments args = CommandArguments.create(key).put("groupName", groupName)
				.put("consumerName", consumerName);
		final Consumer<byte[]> consumer = Consumer.from(groupName, consumerName);
		final Range<String> range = Range.create(start.toString(), end.toString());
		final Limit limit = Limit.from(count);

		return xPending(key, consumer, range, limit, args);
	}

	@Override
	public List<StreamPending> xPending(final byte[] key, final byte[] groupName, final long minIdleTime,
										final StreamEntryId start, final StreamEntryId end, final long count,
										final byte[] consumerName) {
		final CommandArguments args = CommandArguments.create(key).put("groupName", groupName)
				.put("minIdleTime", minIdleTime).put("consumerName", consumerName);
		final Consumer<byte[]> consumer = Consumer.from(groupName, consumerName);
		final Range<String> range = Range.create(start.toString(), end.toString());
		final Limit limit = Limit.from(count);

		return xPending(key, consumer, range, limit, args);
	}

	@Override
	public List<StreamEntry> xRange(final byte[] key, final StreamEntryId start, final StreamEntryId end) {
		final CommandArguments args = CommandArguments.create(key).put("start", start).put("end", end);

		if(isPipeline()){
			return new LettuceSentinelPipelineCommand<List<StreamEntry>, List<StreamEntry>>(client,
					ProtocolCommand.XRANGE)
					.run(args);
		}else if(isTransaction()){
			return new LettuceSentinelTransactionCommand<List<StreamEntry>, List<StreamEntry>>(client,
					ProtocolCommand.XRANGE)
					.run(args);
		}else{
			return new LettuceSentinelCommand<List<StreamEntry>, List<StreamEntry>>(client, ProtocolCommand.XRANGE)
					.run(args);
		}
	}

	@Override
	public List<StreamEntry> xRange(final byte[] key, final StreamEntryId start, final StreamEntryId end,
									final long count) {
		final CommandArguments args = CommandArguments.create(key).put("start", start).put("end", end);

		if(isPipeline()){
			return new LettuceSentinelPipelineCommand<List<StreamEntry>, List<StreamEntry>>(client,
					ProtocolCommand.XRANGE)
					.run(args);
		}else if(isTransaction()){
			return new LettuceSentinelTransactionCommand<List<StreamEntry>, List<StreamEntry>>(client,
					ProtocolCommand.XRANGE)
					.run(args);
		}else{
			return new LettuceSentinelCommand<List<StreamEntry>, List<StreamEntry>>(client, ProtocolCommand.XRANGE)
					.run(args);
		}
	}

	@Override
	public List<Map<String, List<StreamEntry>>> xRead(final Map<String, StreamEntryId> streams) {
		final CommandArguments args = CommandArguments.create(streams);
		return xRead(streams, args);
	}

	@Override
	public List<Map<String, List<StreamEntry>>> xRead(final long count, final Map<String, StreamEntryId> streams) {
		final CommandArguments args = CommandArguments.create(count).put("streams", streams);
		final XReadArgs xReadArgs = new LettuceXReadArgs(count);

		return xRead(xReadArgs, streams, args);
	}

	@Override
	public List<Map<String, List<StreamEntry>>> xRead(final int block, final Map<String, StreamEntryId> streams) {
		final CommandArguments args = CommandArguments.create(block).put("streams", streams);
		final XReadArgs xReadArgs = new LettuceXReadArgs(block);

		return xRead(xReadArgs, streams, args);
	}

	@Override
	public List<Map<String, List<StreamEntry>>> xRead(final long count, final int block,
													  final Map<String, StreamEntryId> streams) {
		final CommandArguments args = CommandArguments.create(count).put("block", block)
				.put("streams", streams);
		final XReadArgs xReadArgs = new LettuceXReadArgs(block, count);

		return xRead(xReadArgs, streams, args);
	}

	@Override
	public List<Map<String, List<StreamEntry>>> xReadGroup(final String groupName, final String consumerName,
														   final Map<String, StreamEntryId> streams) {
		final CommandArguments args = CommandArguments.create(groupName).put("consumerName", consumerName)
				.put("streams", streams);
		return xReadGroup(groupName, consumerName, streams, args);
	}

	@Override
	public List<Map<byte[], List<StreamEntry>>> xReadGroup(final byte[] groupName, final byte[] consumerName,
														   final Map<byte[], StreamEntryId> streams) {
		final CommandArguments args = CommandArguments.create(groupName).put("consumerName", consumerName)
				.put("streams", streams);
		return xReadGroup(groupName, consumerName, streams, args);
	}

	@Override
	public List<Map<String, List<StreamEntry>>> xReadGroup(final String groupName, final String consumerName,
														   final long count, final Map<String, StreamEntryId> streams) {
		final CommandArguments args = CommandArguments.create(groupName).put("consumerName", consumerName)
				.put("count", count).put("streams", streams);
		final XReadArgs xReadArgs = new LettuceXReadArgs(count);

		return xReadGroup(groupName, consumerName, streams, xReadArgs, args);
	}

	@Override
	public List<Map<byte[], List<StreamEntry>>> xReadGroup(final byte[] groupName, final byte[] consumerName,
														   final long count, final Map<byte[], StreamEntryId> streams) {
		final CommandArguments args = CommandArguments.create(groupName).put("consumerName", consumerName)
				.put("count", count).put("streams", streams);
		final XReadArgs xReadArgs = new LettuceXReadArgs(count);

		return xReadGroup(groupName, consumerName, streams, xReadArgs, args);
	}

	@Override
	public List<Map<String, List<StreamEntry>>> xReadGroup(final String groupName, final String consumerName,
														   final int block, final Map<String, StreamEntryId> streams) {
		final CommandArguments args = CommandArguments.create(groupName).put("consumerName", consumerName)
				.put("block", block).put("streams", streams);
		final XReadArgs xReadArgs = new LettuceXReadArgs(block);

		return xReadGroup(groupName, consumerName, streams, xReadArgs, args);
	}

	@Override
	public List<Map<byte[], List<StreamEntry>>> xReadGroup(final byte[] groupName, final byte[] consumerName,
														   final int block, final Map<byte[], StreamEntryId> streams) {
		final CommandArguments args = CommandArguments.create(groupName).put("consumerName", consumerName)
				.put("block", block).put("streams", streams);
		final XReadArgs xReadArgs = new LettuceXReadArgs(block);

		return xReadGroup(groupName, consumerName, streams, xReadArgs, args);
	}

	@Override
	public List<Map<String, List<StreamEntry>>> xReadGroup(final String groupName, final String consumerName,
														   final boolean isNoAck,
														   final Map<String, StreamEntryId> streams) {
		final CommandArguments args = CommandArguments.create(groupName).put("consumerName", consumerName)
				.put("isNoAck", isNoAck).put("streams", streams);
		final XReadArgs xReadArgs = (new LettuceXReadArgs()).noack(isNoAck);

		return xReadGroup(groupName, consumerName, streams, xReadArgs, args);
	}

	@Override
	public List<Map<byte[], List<StreamEntry>>> xReadGroup(final byte[] groupName, final byte[] consumerName,
														   final boolean isNoAck,
														   final Map<byte[], StreamEntryId> streams) {
		final CommandArguments args = CommandArguments.create(groupName).put("consumerName", consumerName)
				.put("isNoAck", isNoAck).put("streams", streams);
		final XReadArgs xReadArgs = (new LettuceXReadArgs()).noack(isNoAck);

		return xReadGroup(groupName, consumerName, streams, xReadArgs, args);
	}

	@Override
	public List<Map<String, List<StreamEntry>>> xReadGroup(final String groupName, final String consumerName,
														   final long count, final int block,
														   final Map<String, StreamEntryId> streams) {
		final CommandArguments args = CommandArguments.create(groupName).put("consumerName", consumerName)
				.put("count", count).put("block", block).put("streams", streams);
		final XReadArgs xReadArgs = new LettuceXReadArgs(block, count);

		return xReadGroup(groupName, consumerName, streams, xReadArgs, args);
	}

	@Override
	public List<Map<byte[], List<StreamEntry>>> xReadGroup(final byte[] groupName, final byte[] consumerName,
														   final long count, final int block,
														   final Map<byte[], StreamEntryId> streams) {
		final CommandArguments args = CommandArguments.create(groupName).put("consumerName", consumerName)
				.put("count", count).put("block", block).put("streams", streams);
		final XReadArgs xReadArgs = new LettuceXReadArgs(block, count);

		return xReadGroup(groupName, consumerName, streams, xReadArgs, args);
	}

	@Override
	public List<Map<String, List<StreamEntry>>> xReadGroup(final String groupName, final String consumerName,
														   final long count, final boolean isNoAck,
														   final Map<String, StreamEntryId> streams) {
		final CommandArguments args = CommandArguments.create(groupName).put("consumerName", consumerName)
				.put("count", count).put("isNoAck", isNoAck).put("streams", streams);
		final XReadArgs xReadArgs = new LettuceXReadArgs(isNoAck, count);

		return xReadGroup(groupName, consumerName, streams, xReadArgs, args);
	}

	@Override
	public List<Map<byte[], List<StreamEntry>>> xReadGroup(final byte[] groupName, final byte[] consumerName,
														   final long count, final boolean isNoAck,
														   final Map<byte[], StreamEntryId> streams) {
		final CommandArguments args = CommandArguments.create(groupName).put("consumerName", consumerName)
				.put("count", count).put("isNoAck", isNoAck).put("streams", streams);
		final XReadArgs xReadArgs = new LettuceXReadArgs(isNoAck, count);

		return xReadGroup(groupName, consumerName, streams, xReadArgs, args);
	}

	@Override
	public List<Map<String, List<StreamEntry>>> xReadGroup(final String groupName, final String consumerName,
														   final int block, final boolean isNoAck,
														   final Map<String, StreamEntryId> streams) {
		final CommandArguments args = CommandArguments.create(groupName).put("consumerName", consumerName)
				.put("block", block).put("isNoAck", isNoAck).put("streams", streams);
		final XReadArgs xReadArgs = (new LettuceXReadArgs()).noack(isNoAck);

		return xReadGroup(groupName, consumerName, streams, xReadArgs, args);
	}

	@Override
	public List<Map<byte[], List<StreamEntry>>> xReadGroup(final byte[] groupName, final byte[] consumerName,
														   final int block, final boolean isNoAck,
														   final Map<byte[], StreamEntryId> streams) {
		final CommandArguments args = CommandArguments.create(groupName).put("consumerName", consumerName)
				.put("block", block).put("isNoAck", isNoAck).put("streams", streams);
		final XReadArgs xReadArgs = (new LettuceXReadArgs()).noack(isNoAck);

		return xReadGroup(groupName, consumerName, streams, xReadArgs, args);
	}

	@Override
	public List<Map<String, List<StreamEntry>>> xReadGroup(final String groupName, final String consumerName,
														   final long count, final int block, final boolean isNoAck,
														   final Map<String, StreamEntryId> streams) {
		final CommandArguments args = CommandArguments.create(groupName).put("consumerName", consumerName)
				.put("count", count).put("block", block).put("isNoAck", isNoAck).put("streams", streams);
		final XReadArgs xReadArgs = new LettuceXReadArgs(block, isNoAck, count);

		return xReadGroup(groupName, consumerName, streams, xReadArgs, args);
	}

	@Override
	public List<Map<byte[], List<StreamEntry>>> xReadGroup(final byte[] groupName, final byte[] consumerName,
														   final long count, final int block, final boolean isNoAck,
														   final Map<byte[], StreamEntryId> streams) {
		final CommandArguments args = CommandArguments.create(groupName).put("consumerName", consumerName)
				.put("count", count).put("block", block).put("isNoAck", isNoAck).put("streams", streams);
		final XReadArgs xReadArgs = new LettuceXReadArgs(block, isNoAck, count);

		return xReadGroup(groupName, consumerName, streams, xReadArgs, args);
	}

	@Override
	public List<StreamEntry> xRevRange(final byte[] key, final StreamEntryId end, final StreamEntryId start) {
		final CommandArguments args = CommandArguments.create(key).put("end", end).put("start", start);
		final Range<String> range = Range.create(start.toString(), end.toString());
		final ListConverter<StreamMessage<byte[], byte[]>, StreamEntry> listStreamMessageConverter =
				StreamMessageConverter.listConverter();

		if(isPipeline()){
			return new LettuceSentinelPipelineCommand<List<StreamEntry>, List<StreamEntry>>(client,
					ProtocolCommand.XREVRANGE)
					.run(args);
		}else if(isTransaction()){
			return new LettuceSentinelTransactionCommand<List<StreamEntry>, List<StreamEntry>>(client,
					ProtocolCommand.XREVRANGE)
					.run(args);
		}else{
			return new LettuceSentinelCommand<List<StreamEntry>, List<StreamEntry>>(client, ProtocolCommand.XREVRANGE)
					.run(args);
		}
	}

	@Override
	public List<StreamEntry> xRevRange(final byte[] key, final StreamEntryId end, final StreamEntryId start,
									   final long count) {
		final CommandArguments args = CommandArguments.create(key).put("end", end).put("start", start)
				.put("count", count);
		final Range<String> range = Range.create(start.toString(), end.toString());
		final Limit limit = Limit.from(count);
		final ListConverter<StreamMessage<byte[], byte[]>, StreamEntry> listStreamMessageConverter =
				StreamMessageConverter.listConverter();

		if(isPipeline()){
			return new LettuceSentinelPipelineCommand<List<StreamEntry>, List<StreamEntry>>(client,
					ProtocolCommand.XREVRANGE)
					.run(args);
		}else if(isTransaction()){
			return new LettuceSentinelTransactionCommand<List<StreamEntry>, List<StreamEntry>>(client,
					ProtocolCommand.XREVRANGE)
					.run(args);
		}else{
			return new LettuceSentinelCommand<List<StreamEntry>, List<StreamEntry>>(client, ProtocolCommand.XREVRANGE)
					.run(args);
		}
	}

	@Override
	public Long xTrim(final byte[] key, final XTrimArgument xTrimArgument) {
		final CommandArguments args = CommandArguments.create(key).put("xTrimArgument", xTrimArgument);
		return xTrim(key, xTrimArgument.isApproximateTrimming(), Long.MAX_VALUE, args);
	}

	@Override
	public Long xTrim(final byte[] key, final XTrimArgument xTrimArgument, final long limit) {
		final CommandArguments args = CommandArguments.create(key).put("xTrimArgument", xTrimArgument)
				.put("limit", limit);
		return xTrim(key, xTrimArgument.isApproximateTrimming(), limit, args);
	}

	private StreamEntryId xAdd(final byte[] key, final Map<byte[], byte[]> hash, final XAddArgs xAddArgs,
							   final CommandArguments args) {
		final StreamEntryIDConverter streamEntryIDConverter = new StreamEntryIDConverter();

		if(isPipeline()){
			return new LettuceSentinelPipelineCommand<StreamEntryId, StreamEntryId>(client, ProtocolCommand.XADD)
					.run(args);
		}else if(isTransaction()){
			return new LettuceSentinelTransactionCommand<StreamEntryId, StreamEntryId>(client, ProtocolCommand.XADD)
					.run(args);
		}else{
			return new LettuceSentinelCommand<StreamEntryId, StreamEntryId>(client, ProtocolCommand.XADD)
					.run(args);
		}
	}

	private Map<StreamEntryId, List<StreamEntry>> xAutoClaim(final CommandArguments args) {

		if(isPipeline()){
			return new LettuceSentinelPipelineCommand<Map<StreamEntryId, List<StreamEntry>>, Map<StreamEntryId, List<StreamEntry>>>(
					client, ProtocolCommand.XAUTOCLAIM)
					.run(args);
		}else if(isTransaction()){
			return new LettuceSentinelTransactionCommand<Map<StreamEntryId, List<StreamEntry>>, Map<StreamEntryId, List<StreamEntry>>>(
					client, ProtocolCommand.XAUTOCLAIM)
					.run(args);
		}else{
			return new LettuceSentinelCommand<Map<StreamEntryId, List<StreamEntry>>, Map<StreamEntryId, List<StreamEntry>>>(
					client, ProtocolCommand.XAUTOCLAIM)
					.run(args);
		}
	}

	private Map<StreamEntryId, List<StreamEntryId>> xAutoClaimJustId(final CommandArguments args) {

		if(isPipeline()){
			return new LettuceSentinelPipelineCommand<Map<StreamEntryId, List<StreamEntryId>>, Map<StreamEntryId, List<StreamEntryId>>>(
					client, ProtocolCommand.XAUTOCLAIM)
					.run(args);
		}else if(isTransaction()){
			return new LettuceSentinelTransactionCommand<Map<StreamEntryId, List<StreamEntryId>>, Map<StreamEntryId, List<StreamEntryId>>>(
					client, ProtocolCommand.XAUTOCLAIM)
					.run(args);
		}else{
			return new LettuceSentinelCommand<Map<StreamEntryId, List<StreamEntryId>>, Map<StreamEntryId, List<StreamEntryId>>>(
					client, ProtocolCommand.XAUTOCLAIM)
					.run(args);
		}
	}

	private List<StreamEntry> xClaim(final byte[] key, final StreamEntryId[] ids, final Consumer<byte[]> consumer,
									 final XClaimArgs xClaimArgs, final CommandArguments args) {
		final String[] messageIds = StreamEntryIdConverter.arrayConverter().convert(ids);
		final ListConverter<StreamMessage<byte[], byte[]>, StreamEntry> listStreamMessageConverter =
				StreamMessageConverter.listConverter();

		if(isPipeline()){
			return new LettuceSentinelPipelineCommand<List<StreamEntry>, List<StreamEntry>>(client,
					ProtocolCommand.XCLAIM)
					.run(args);
		}else if(isTransaction()){
			return new LettuceSentinelTransactionCommand<List<StreamEntry>, List<StreamEntry>>(client,
					ProtocolCommand.XCLAIM)
					.run(args);
		}else{
			return new LettuceSentinelCommand<List<StreamEntry>, List<StreamEntry>>(client, ProtocolCommand.XCLAIM)
					.run(args);
		}
	}

	private List<StreamEntryId> xClaimJustId(final byte[] key, final StreamEntryId[] ids,
											 final Consumer<byte[]> consumer, final XClaimArgs xClaimArgs,
											 final CommandArguments args) {
		final String[] messageIds = StreamEntryIdConverter.arrayConverter().convert(ids);
		final ListConverter<StreamMessage<byte[], byte[]>, StreamEntryId> listStreamMessageStreamEntryIdConverter
				= StreamMessageConverter.StreamMessageStreamEntryIdConverter.listConverter();

		if(isPipeline()){
			return new LettuceSentinelPipelineCommand<List<StreamEntryId>, List<StreamEntryId>>(client,
					ProtocolCommand.XCLAIM)
					.run(args);
		}else if(isTransaction()){
			return new LettuceSentinelTransactionCommand<List<StreamEntryId>, List<StreamEntryId>>(client,
					ProtocolCommand.XCLAIM)
					.run(args);
		}else{
			return new LettuceSentinelCommand<List<StreamEntryId>, List<StreamEntryId>>(client,
					ProtocolCommand.XCLAIM)
					.run(args);
		}
	}

	private Status xGroupCreateConsumer(final CommandArguments args) {
		if(isPipeline()){
			return new LettuceSentinelPipelineCommand<Status, Status>(client, ProtocolCommand.XGROUP_CREATECONSUMER)
					.run(args);
		}else if(isTransaction()){
			return new LettuceSentinelTransactionCommand<Status, Status>(client, ProtocolCommand.XGROUP_CREATECONSUMER)
					.run(args);
		}else{
			return new LettuceSentinelCommand<Status, Status>(client, ProtocolCommand.XGROUP_CREATECONSUMER)
					.run(args);
		}
	}

	private List<StreamPending> xPending(final byte[] key, final byte[] groupName, Range<String> range,
										 final Limit limit, final CommandArguments args) {
		if(isPipeline()){
			return new LettuceSentinelPipelineCommand<List<StreamPending>, List<StreamPending>>(client,
					ProtocolCommand.XPENDING)
					.run(args);
		}else if(isTransaction()){
			return new LettuceSentinelTransactionCommand<List<StreamPending>, List<StreamPending>>(client,
					ProtocolCommand.XPENDING)
					.run(args);
		}else{
			return new LettuceSentinelCommand<List<StreamPending>, List<StreamPending>>(client,
					ProtocolCommand.XPENDING)
					.run(args);
		}
	}

	private List<StreamPending> xPending(final byte[] key, final Consumer<byte[]> consumer, Range<String> range,
										 final Limit limit, final CommandArguments args) {
		if(isPipeline()){
			return new LettuceSentinelPipelineCommand<List<StreamPending>, List<StreamPending>>(client,
					ProtocolCommand.XPENDING)
					.run(args);
		}else if(isTransaction()){
			return new LettuceSentinelTransactionCommand<List<StreamPending>, List<StreamPending>>(client,
					ProtocolCommand.XPENDING)
					.run(args);
		}else{
			return new LettuceSentinelCommand<List<StreamPending>, List<StreamPending>>(client,
					ProtocolCommand.XPENDING)
					.run(args);
		}
	}

	private List<Map<String, List<StreamEntry>>> xRead(final Map<String, StreamEntryId> streams,
													   final CommandArguments args) {
		final XReadArgs.StreamOffset<byte[]>[] streamOffsets = createStreamOffsetFromStreamEntryIdMap(streams);
		final ListConverter<StreamMessage<byte[], byte[]>, Map<String, List<StreamEntry>>> listStreamMessageMapConverter =
				StreamMessageMapConverter.listConverter();

		if(isPipeline()){
			return new LettuceSentinelPipelineCommand<List<Map<String, List<StreamEntry>>>, List<Map<String, List<StreamEntry>>>>(
					client, ProtocolCommand.XREAD)
					.run(args);
		}else if(isTransaction()){
			return new LettuceSentinelTransactionCommand<List<Map<String, List<StreamEntry>>>, List<Map<String, List<StreamEntry>>>>(
					client, ProtocolCommand.XREAD)
					.run(args);
		}else{
			return new LettuceSentinelCommand<List<Map<String, List<StreamEntry>>>, List<Map<String, List<StreamEntry>>>>(
					client, ProtocolCommand.XREAD)
					.run(args);
		}
	}

	private List<Map<String, List<StreamEntry>>> xRead(final XReadArgs xReadArgs, final Map<String,
			StreamEntryId> streams, final CommandArguments args) {
		final XReadArgs.StreamOffset<byte[]>[] streamOffsets = createStreamOffsetFromStreamEntryIdMap(streams);
		final ListConverter<StreamMessage<byte[], byte[]>, Map<String, List<StreamEntry>>> listStreamMessageMapConverter =
				StreamMessageMapConverter.listConverter();

		if(isPipeline()){
			return new LettuceSentinelPipelineCommand<List<Map<String, List<StreamEntry>>>, List<Map<String, List<StreamEntry>>>>(
					client, ProtocolCommand.XREAD)
					.run(args);
		}else if(isTransaction()){
			return new LettuceSentinelTransactionCommand<List<Map<String, List<StreamEntry>>>, List<Map<String, List<StreamEntry>>>>(
					client, ProtocolCommand.XREAD)
					.run(args);
		}else{
			return new LettuceSentinelCommand<List<Map<String, List<StreamEntry>>>, List<Map<String, List<StreamEntry>>>>(
					client, ProtocolCommand.XREAD)
					.run(args);
		}
	}

	private List<Map<String, List<StreamEntry>>> xReadGroup(final String groupName, final String consumerName,
															final Map<String, StreamEntryId> streams,
															final CommandArguments args) {
		final XReadArgs.StreamOffset<byte[]>[] streamOffsets = createStreamOffsetFromStreamEntryIdMap(streams);
		final ListConverter<StreamMessage<byte[], byte[]>, Map<String, List<StreamEntry>>> listStreamMessageMapConverter =
				StreamMessageMapConverter.listConverter();

		return xReadGroup(SafeEncoder.encode(groupName), SafeEncoder.encode(consumerName), streamOffsets,
				listStreamMessageMapConverter, args);
	}

	private List<Map<byte[], List<StreamEntry>>> xReadGroup(final byte[] groupName, final byte[] consumerName,
															final Map<byte[], StreamEntryId> streams,
															final CommandArguments args) {
		final XReadArgs.StreamOffset<byte[]>[] streamOffsets = createStreamOffsetFromBinaryStreamEntryIdMap(streams);
		final ListConverter<StreamMessage<byte[], byte[]>, Map<byte[], List<StreamEntry>>> listStreamMessageMapConverter =
				StreamMessageMapConverter.listConverter();

		return xReadGroup(groupName, consumerName, streamOffsets, listStreamMessageMapConverter, args);
	}

	private <K> List<Map<K, List<StreamEntry>>> xReadGroup(final byte[] groupName, final byte[] consumerName,
														   final XReadArgs.StreamOffset<byte[]>[] streamOffsets,
														   final Converter<List<StreamMessage<byte[], byte[]>>,
																   List<Map<K, List<StreamEntry>>>> converter,
														   final CommandArguments args) {
		final Consumer<byte[]> consumer = Consumer.from(groupName, consumerName);

		if(isPipeline()){
			return new LettuceSentinelPipelineCommand<List<Map<K, List<StreamEntry>>>, List<Map<K, List<StreamEntry>>>>(
					client, ProtocolCommand.XREADGROUP)
					.run(args);
		}else if(isTransaction()){
			return new LettuceSentinelTransactionCommand<List<Map<K, List<StreamEntry>>>, List<Map<K, List<StreamEntry>>>>(
					client, ProtocolCommand.XREADGROUP)
					.run(args);
		}else{
			return new LettuceSentinelCommand<List<Map<K, List<StreamEntry>>>, List<Map<K, List<StreamEntry>>>>(
					client, ProtocolCommand.XREADGROUP)
					.run(args);
		}
	}

	private List<Map<String, List<StreamEntry>>> xReadGroup(final String groupName, final String consumerName,
															final Map<String, StreamEntryId> streams,
															final XReadArgs xReadArgs, final CommandArguments args) {
		final XReadArgs.StreamOffset<byte[]>[] streamOffsets = new XReadArgs.StreamOffset[streams.size()];
		final ListConverter<StreamMessage<byte[], byte[]>, Map<String, List<StreamEntry>>> listStreamMessageMapConverter =
				StreamMessageMapConverter.listConverter();
		int i = 0;

		for(Map.Entry<String, StreamEntryId> e : streams.entrySet()){
			streamOffsets[i++] = XReadArgs.StreamOffset.from(SafeEncoder.encode(e.getKey()), e.getValue().toString());
		}

		return xReadGroup(SafeEncoder.encode(groupName), SafeEncoder.encode(consumerName), streamOffsets, xReadArgs,
				listStreamMessageMapConverter, args);
	}

	private List<Map<byte[], List<StreamEntry>>> xReadGroup(final byte[] groupName, final byte[] consumerName,
															final Map<byte[], StreamEntryId> streams,
															final XReadArgs xReadArgs, final CommandArguments args) {
		final XReadArgs.StreamOffset<byte[]>[] streamOffsets = new XReadArgs.StreamOffset[streams.size()];
		final ListConverter<StreamMessage<byte[], byte[]>, Map<byte[], List<StreamEntry>>> listStreamMessageMapConverter =
				StreamMessageMapConverter.listConverter();
		int i = 0;

		for(Map.Entry<byte[], StreamEntryId> e : streams.entrySet()){
			streamOffsets[i++] = XReadArgs.StreamOffset.from(e.getKey(), e.getValue().toString());
		}

		return xReadGroup(groupName, consumerName, streamOffsets, xReadArgs, listStreamMessageMapConverter, args);
	}

	private <K> List<Map<K, List<StreamEntry>>> xReadGroup(final byte[] groupName, final byte[] consumerName,
														   final XReadArgs.StreamOffset<byte[]>[] streamOffsets,
														   final XReadArgs xReadArgs,
														   final Converter<List<StreamMessage<byte[], byte[]>>,
																   List<Map<K, List<StreamEntry>>>> converter,
														   final CommandArguments args) {
		final Consumer<byte[]> consumer = Consumer.from(groupName, consumerName);

		if(isPipeline()){
			return new LettuceSentinelPipelineCommand<List<Map<K, List<StreamEntry>>>, List<Map<K, List<StreamEntry>>>>(
					client, ProtocolCommand.XREADGROUP)
					.run(args);
		}else if(isTransaction()){
			return new LettuceSentinelTransactionCommand<List<Map<K, List<StreamEntry>>>, List<Map<K, List<StreamEntry>>>>(
					client, ProtocolCommand.XREADGROUP)
					.run(args);
		}else{
			return new LettuceSentinelCommand<List<Map<K, List<StreamEntry>>>, List<Map<K, List<StreamEntry>>>>(
					client, ProtocolCommand.XREADGROUP)
					.run(args);
		}
	}

	private Long xTrim(final byte[] key, final boolean approximateTrimming, final long limit,
					   final CommandArguments args) {

		if(isPipeline()){
			return new LettuceSentinelPipelineCommand<Long, Long>(client, ProtocolCommand.XTRIM)
					.run(args);
		}else if(isTransaction()){
			return new LettuceSentinelTransactionCommand<Long, Long>(client, ProtocolCommand.XTRIM)
					.run(args);
		}else{
			return new LettuceSentinelCommand<Long, Long>(client, ProtocolCommand.XTRIM)
					.run(args);
		}
	}

}
