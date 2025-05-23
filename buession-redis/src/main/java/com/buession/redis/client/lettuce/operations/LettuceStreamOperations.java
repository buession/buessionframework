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
import com.buession.redis.core.internal.convert.lettuce.response.PendingMessageConverter;
import com.buession.redis.core.internal.convert.lettuce.response.PendingMessagesConverter;
import com.buession.redis.core.internal.convert.lettuce.response.StreamConsumersInfoConverter;
import com.buession.redis.core.internal.convert.lettuce.response.StreamEntryIDConverter;
import com.buession.redis.core.internal.convert.lettuce.response.StreamFullInfoConverter;
import com.buession.redis.core.internal.convert.lettuce.response.StreamGroupInfoConverter;
import com.buession.redis.core.internal.convert.lettuce.response.StreamInfoConverter;
import com.buession.redis.core.internal.convert.lettuce.response.StreamMessageConverter;
import com.buession.redis.core.internal.convert.lettuce.response.StreamMessageMapConverter;
import com.buession.redis.core.internal.lettuce.LettuceXAddArgs;
import com.buession.redis.core.internal.lettuce.LettuceXClaimArgs;
import com.buession.redis.core.internal.lettuce.LettuceXGroupCreateArgs;
import com.buession.redis.core.internal.lettuce.LettuceXPendingArgs;
import com.buession.redis.core.internal.lettuce.LettuceXReadArgs;
import com.buession.redis.utils.SafeEncoder;
import io.lettuce.core.Consumer;
import io.lettuce.core.Limit;
import io.lettuce.core.Range;
import io.lettuce.core.StreamMessage;
import io.lettuce.core.XAddArgs;
import io.lettuce.core.XClaimArgs;
import io.lettuce.core.XGroupCreateArgs;
import io.lettuce.core.XReadArgs;
import io.lettuce.core.models.stream.PendingMessage;

import java.util.List;
import java.util.Map;

/**
 * Lettuce 单机模式 Stream 命令操作
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
		final CommandArguments args = CommandArguments.create(key).add(groupName)
				.add(ids);
		final String[] messageIds = StreamEntryIdConverter.arrayConverter().convert(ids);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.XACK,
					(cmd)->cmd.xack(key, groupName, messageIds), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.XACK,
					(cmd)->cmd.xack(key, groupName, messageIds), (v)->v)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.XACK, (cmd)->cmd.xack(key, groupName, messageIds),
					(v)->v)
					.run(args);
		}
	}

	@Override
	public StreamEntryId xAdd(final byte[] key, final StreamEntryId id, final Map<byte[], byte[]> hash) {
		final CommandArguments args = CommandArguments.create(key).add(id).add(hash);
		final XAddArgs xAddArgs = new LettuceXAddArgs(id);

		return xAdd(key, hash, xAddArgs, args);
	}

	@Override
	public StreamEntryId xAdd(final byte[] key, final StreamEntryId id, final Map<byte[], byte[]> hash,
							  final XAddArgument xAddArgument) {
		final CommandArguments args = CommandArguments.create(key).add(id).add(hash)
				.add(xAddArgument);
		final StreamEntryIdConverter streamEntryIdConverter = new StreamEntryIdConverter();
		final XAddArgs xAddArgs = LettuceXAddArgs.from(xAddArgument).id(streamEntryIdConverter.convert(id));

		return xAdd(key, hash, xAddArgs, args);
	}

	@Override
	public Map<StreamEntryId, List<StreamEntry>> xAutoClaim(final String key, final String groupName,
															final String consumerName, final int minIdleTime,
															final StreamEntryId start) {
		final CommandArguments args = CommandArguments.create(key).add(groupName)
				.add(consumerName).add(minIdleTime).add(start);
		return xAutoClaim(args);
	}

	@Override
	public Map<StreamEntryId, List<StreamEntry>> xAutoClaim(final byte[] key, final byte[] groupName,
															final byte[] consumerName, final int minIdleTime,
															final StreamEntryId start) {
		final CommandArguments args = CommandArguments.create(key).add(groupName)
				.add(consumerName).add(minIdleTime).add(start);
		return xAutoClaim(args);
	}

	@Override
	public Map<StreamEntryId, List<StreamEntry>> xAutoClaim(final String key, final String groupName,
															final String consumerName, final int minIdleTime,
															final StreamEntryId start, final long count) {
		final CommandArguments args = CommandArguments.create(key).add(groupName)
				.add(consumerName).add(minIdleTime).add(start)
				.add(count);
		return xAutoClaim(args);
	}

	@Override
	public Map<StreamEntryId, List<StreamEntry>> xAutoClaim(final byte[] key, final byte[] groupName,
															final byte[] consumerName, final int minIdleTime,
															final StreamEntryId start, final long count) {
		final CommandArguments args = CommandArguments.create(key).add(groupName)
				.add(consumerName).add(minIdleTime).add(start)
				.add(count);
		return xAutoClaim(args);
	}

	@Override
	public Map<StreamEntryId, List<StreamEntryId>> xAutoClaimJustId(final String key, final String groupName,
																	final String consumerName, final int minIdleTime,
																	final StreamEntryId start) {
		final CommandArguments args = CommandArguments.create(key).add(groupName)
				.add(consumerName).add(minIdleTime).add(start);
		return xAutoClaimJustId(args);
	}

	@Override
	public Map<StreamEntryId, List<StreamEntryId>> xAutoClaimJustId(final byte[] key, final byte[] groupName,
																	final byte[] consumerName, final int minIdleTime,
																	final StreamEntryId start) {
		final CommandArguments args = CommandArguments.create(key).add(groupName)
				.add(consumerName).add(minIdleTime).add(start);
		return xAutoClaimJustId(args);
	}

	@Override
	public Map<StreamEntryId, List<StreamEntryId>> xAutoClaimJustId(final byte[] key, final byte[] groupName,
																	final byte[] consumerName, final int minIdleTime,
																	final StreamEntryId start, final long count) {
		final CommandArguments args = CommandArguments.create(key).add(groupName)
				.add(consumerName).add(minIdleTime).add(start)
				.add(count);
		return xAutoClaimJustId(args);
	}

	@Override
	public Map<StreamEntryId, List<StreamEntryId>> xAutoClaimJustId(final String key, final String groupName,
																	final String consumerName, final int minIdleTime,
																	final StreamEntryId start, final long count) {
		final CommandArguments args = CommandArguments.create(key).add(groupName)
				.add(consumerName).add(minIdleTime).add(start)
				.add(count);
		return xAutoClaimJustId(args);
	}

	@Override
	public List<StreamEntry> xClaim(final byte[] key, final byte[] groupName, final byte[] consumerName,
									final int minIdleTime, final StreamEntryId... ids) {
		final CommandArguments args = CommandArguments.create(key).add(groupName)
				.add(consumerName).add(minIdleTime).add(ids);
		final Consumer<byte[]> consumer = Consumer.from(groupName, consumerName);
		final XClaimArgs xClaimArgs = new LettuceXClaimArgs(minIdleTime);

		return xClaim(key, ids, consumer, xClaimArgs, args);
	}

	@Override
	public List<StreamEntry> xClaim(final byte[] key, final byte[] groupName, final byte[] consumerName,
									final int minIdleTime, final StreamEntryId[] ids,
									final XClaimArgument xClaimArgument) {
		final CommandArguments args = CommandArguments.create(key).add(groupName)
				.add(consumerName).add(minIdleTime).add(ids)
				.add(xClaimArgument);
		final Consumer<byte[]> consumer = Consumer.from(groupName, consumerName);
		final XClaimArgs xClaimArgs = LettuceXClaimArgs.from(xClaimArgument).minIdleTime(minIdleTime);

		return xClaim(key, ids, consumer, xClaimArgs, args);
	}

	@Override
	public List<StreamEntryId> xClaimJustId(final byte[] key, final byte[] groupName, final byte[] consumerName,
											final int minIdleTime, final StreamEntryId... ids) {
		final CommandArguments args = CommandArguments.create(key).add(groupName)
				.add(consumerName).add(minIdleTime).add(ids);
		final Consumer<byte[]> consumer = Consumer.from(groupName, consumerName);
		final XClaimArgs xClaimArgs = new LettuceXClaimArgs(minIdleTime, true);

		return xClaimJustId(key, ids, consumer, xClaimArgs, args);
	}

	@Override
	public List<StreamEntryId> xClaimJustId(final byte[] key, final byte[] groupName, final byte[] consumerName,
											final int minIdleTime, final StreamEntryId[] ids,
											final XClaimArgument xClaimArgument) {
		final CommandArguments args = CommandArguments.create(key).add(groupName)
				.add(consumerName).add(minIdleTime).add(ids)
				.add(xClaimArgument);
		final Consumer<byte[]> consumer = Consumer.from(groupName, consumerName);
		final XClaimArgs xClaimArgs = LettuceXClaimArgs.from(xClaimArgument).minIdleTime(minIdleTime).justid();

		return xClaimJustId(key, ids, consumer, xClaimArgs, args);
	}

	@Override
	public Long xDel(final byte[] key, final StreamEntryId... ids) {
		final CommandArguments args = CommandArguments.create(key).add(ids);
		final String[] messageIds = StreamEntryIdConverter.arrayConverter().convert(ids);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.XDEL, (cmd)->cmd.xdel(key, messageIds), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.XDEL, (cmd)->cmd.xdel(key, messageIds),
					(v)->v)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.XDEL, (cmd)->cmd.xdel(key, messageIds), (v)->v)
					.run(args);
		}
	}

	@Override
	public Status xGroupCreate(final byte[] key, final byte[] groupName, final StreamEntryId id,
							   final boolean makeStream) {
		final CommandArguments args = CommandArguments.create(key).add(groupName)
				.add(id);
		final XReadArgs.StreamOffset<byte[]> streamOffset = XReadArgs.StreamOffset.latest(key);
		final XGroupCreateArgs xGroupCreateArgs = new LettuceXGroupCreateArgs(makeStream);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.XGROUP_CREATE,
					(cmd)->cmd.xgroupCreate(streamOffset, groupName, xGroupCreateArgs), okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.XGROUP_CREATE,
					(cmd)->cmd.xgroupCreate(streamOffset, groupName, xGroupCreateArgs), okStatusConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.XGROUP_CREATE,
					(cmd)->cmd.xgroupCreate(streamOffset, groupName, xGroupCreateArgs), okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status xGroupCreateConsumer(final String key, final String groupName, final String consumerName) {
		final CommandArguments args = CommandArguments.create(key).add(groupName)
				.add(consumerName);
		return xGroupCreateConsumer(args);
	}

	@Override
	public Status xGroupCreateConsumer(final byte[] key, final byte[] groupName, final byte[] consumerName) {
		final CommandArguments args = CommandArguments.create(key).add(groupName)
				.add(consumerName);
		return xGroupCreateConsumer(args);
	}

	@Override
	public Long xGroupDelConsumer(final byte[] key, final byte[] groupName, final byte[] consumerName) {
		final CommandArguments args = CommandArguments.create(key).add(groupName)
				.add(consumerName);
		final Consumer<byte[]> consumer = Consumer.from(groupName, consumerName);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.XGROUP_DELCONSUMER,
					(cmd)->cmd.xgroupDelconsumer(key, consumer), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.XGROUP_DELCONSUMER,
					(cmd)->cmd.xgroupDelconsumer(key, consumer), (v)->v)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.XGROUP_DELCONSUMER,
					(cmd)->cmd.xgroupDelconsumer(key, consumer), (v)->v)
					.run(args);
		}
	}

	@Override
	public Status xGroupDestroy(final byte[] key, final byte[] groupName) {
		final CommandArguments args = CommandArguments.create(key).add(groupName);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.XGROUP_DESTROY,
					(cmd)->cmd.xgroupDestroy(key, groupName), booleanStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.XGROUP_DESTROY,
					(cmd)->cmd.xgroupDestroy(key, groupName), booleanStatusConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.XGROUP_DESTROY,
					(cmd)->cmd.xgroupDestroy(key, groupName), booleanStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status xGroupSetId(final byte[] key, final byte[] groupName, final StreamEntryId id) {
		final CommandArguments args = CommandArguments.create(key).add(groupName).add(id);
		final XReadArgs.StreamOffset<byte[]> streamOffset = XReadArgs.StreamOffset.from(key, id.toString());

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.XGROUP_SETID,
					(cmd)->cmd.xgroupSetid(streamOffset, groupName), okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.XGROUP_SETID,
					(cmd)->cmd.xgroupSetid(streamOffset, groupName), okStatusConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.XGROUP_SETID,
					(cmd)->cmd.xgroupSetid(streamOffset, groupName), okStatusConverter)
					.run(args);
		}
	}

	@Override
	public List<StreamConsumer> xInfoConsumers(final byte[] key, final byte[] groupName) {
		final CommandArguments args = CommandArguments.create(key).add(groupName);
		final ListConverter<Object, StreamConsumer> listStreamConsumersInfoConverter = StreamConsumersInfoConverter.listConverter();

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.XINFO_CONSUMERS,
					(cmd)->cmd.xinfoConsumers(key, groupName), listStreamConsumersInfoConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.XINFO_CONSUMERS,
					(cmd)->cmd.xinfoConsumers(key, groupName), listStreamConsumersInfoConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.XINFO_CONSUMERS,
					(cmd)->cmd.xinfoConsumers(key, groupName), listStreamConsumersInfoConverter)
					.run(args);
		}
	}

	@Override
	public List<StreamGroup> xInfoGroups(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		final ListConverter<Object, StreamGroup> listStreamGroupInfoConverter = StreamGroupInfoConverter.listConverter();

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.XINFO_GROUPS, (cmd)->cmd.xinfoGroups(key),
					listStreamGroupInfoConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.XINFO_GROUPS, (cmd)->cmd.xinfoGroups(key),
					listStreamGroupInfoConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.XINFO_GROUPS, (cmd)->cmd.xinfoGroups(key),
					listStreamGroupInfoConverter)
					.run(args);
		}
	}

	@Override
	public Stream xInfoStream(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		final StreamInfoConverter streamInfoConverter = new StreamInfoConverter();

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.XINFO_STREAM, (cmd)->cmd.xinfoStream(
					key), streamInfoConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.XINFO_STREAM, (cmd)->cmd.xinfoStream(
					key), streamInfoConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.XINFO_STREAM, (cmd)->cmd.xinfoStream(
					key), streamInfoConverter)
					.run(args);
		}
	}

	@Override
	public StreamFull xInfoStream(final byte[] key, final boolean full) {
		final CommandArguments args = CommandArguments.create(key).add(full);
		final StreamFullInfoConverter streamFullInfoConverter = new StreamFullInfoConverter();

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.XINFO_STREAM, (cmd)->cmd.xinfoStream(key),
					streamFullInfoConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.XINFO_STREAM, (cmd)->cmd.xinfoStream(key),
					streamFullInfoConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.XINFO_STREAM, (cmd)->cmd.xinfoStream(key),
					streamFullInfoConverter)
					.run(args);
		}
	}

	@Override
	public StreamFull xInfoStream(final byte[] key, final boolean full, final long count) {
		final CommandArguments args = CommandArguments.create(key).add(full).add(count);
		final StreamFullInfoConverter streamFullInfoConverter = new StreamFullInfoConverter();

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.XINFO_STREAM, (cmd)->cmd.xinfoStream(key),
					streamFullInfoConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.XINFO_STREAM, (cmd)->cmd.xinfoStream(key),
					streamFullInfoConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.XINFO_STREAM, (cmd)->cmd.xinfoStream(key),
					streamFullInfoConverter)
					.run(args);
		}
	}

	@Override
	public Long xLen(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.XLEN, (cmd)->cmd.xlen(key), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.XLEN, (cmd)->cmd.xlen(key), (v)->v)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.XLEN, (cmd)->cmd.xlen(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public StreamPendingSummary xPending(final byte[] key, final byte[] groupName) {
		final CommandArguments args = CommandArguments.create(key).add(groupName);
		final PendingMessagesConverter pendingMessagesConverter = new PendingMessagesConverter();

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.XPENDING, (cmd)->cmd.xpending(key, groupName),
					pendingMessagesConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.XPENDING,
					(cmd)->cmd.xpending(key, groupName), pendingMessagesConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.XPENDING, (cmd)->cmd.xpending(key, groupName),
					pendingMessagesConverter)
					.run(args);
		}
	}

	@Override
	public List<StreamPending> xPending(final byte[] key, final byte[] groupName, final long minIdleTime) {
		final CommandArguments args = CommandArguments.create(key).add(groupName)
				.add(minIdleTime);
		final LettuceXPendingArgs<byte[]> xPendingArgs = new LettuceXPendingArgs<>(minIdleTime, groupName, null);
		final ListConverter<PendingMessage, StreamPending> listStreamPendingConverter = PendingMessageConverter.listConverter();

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.XPENDING,
					(cmd)->cmd.xpending(key, xPendingArgs),
					listStreamPendingConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.XPENDING,
					(cmd)->cmd.xpending(key, xPendingArgs), listStreamPendingConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.XPENDING, (cmd)->cmd.xpending(key, xPendingArgs),
					listStreamPendingConverter)
					.run(args);
		}
	}

	@Override
	public List<StreamPending> xPending(final byte[] key, final byte[] groupName, final StreamEntryId start,
										final StreamEntryId end, final long count) {
		final CommandArguments args = CommandArguments.create(key).add(groupName)
				.add(start).add(end).add(count);
		final Range<String> range = Range.create(start.toString(), end.toString());
		final Limit limit = Limit.from(count);

		return xPending(key, groupName, range, limit, args);
	}

	@Override
	public List<StreamPending> xPending(final byte[] key, final byte[] groupName, final byte[] consumerName) {
		final CommandArguments args = CommandArguments.create(key).add(groupName)
				.add(consumerName);
		final Consumer<byte[]> consumer = Consumer.from(groupName, consumerName);
		final Range<String> range = Range.unbounded();
		final Limit limit = Limit.unlimited();

		return xPending(key, consumer, range, limit, args);
	}

	@Override
	public List<StreamPending> xPending(final byte[] key, final byte[] groupName, final long minIdleTime,
										final StreamEntryId start, final StreamEntryId end, final long count) {
		final CommandArguments args = CommandArguments.create(key).add(groupName)
				.add(minIdleTime).add(start).add(end).add(count);
		final Range<String> range = Range.create(start.toString(), end.toString());
		final Limit limit = Limit.from(count);

		return xPending(key, groupName, range, limit, args);
	}

	@Override
	public List<StreamPending> xPending(final byte[] key, final byte[] groupName, final long minIdleTime,
										final byte[] consumerName) {
		final CommandArguments args = CommandArguments.create(key).add(groupName)
				.add(minIdleTime).add(consumerName);
		final Consumer<byte[]> consumer = Consumer.from(groupName, consumerName);
		final Range<String> range = Range.unbounded();
		final Limit limit = Limit.unlimited();

		return xPending(key, consumer, range, limit, args);
	}

	@Override
	public List<StreamPending> xPending(final byte[] key, final byte[] groupName, final StreamEntryId start,
										final StreamEntryId end, final long count, final byte[] consumerName) {
		final CommandArguments args = CommandArguments.create(key).add(groupName)
				.add(consumerName);
		final Consumer<byte[]> consumer = Consumer.from(groupName, consumerName);
		final Range<String> range = Range.create(start.toString(), end.toString());
		final Limit limit = Limit.from(count);

		return xPending(key, consumer, range, limit, args);
	}

	@Override
	public List<StreamPending> xPending(final byte[] key, final byte[] groupName, final long minIdleTime,
										final StreamEntryId start, final StreamEntryId end, final long count,
										final byte[] consumerName) {
		final CommandArguments args = CommandArguments.create(key).add(groupName)
				.add(minIdleTime).add(consumerName);
		final Consumer<byte[]> consumer = Consumer.from(groupName, consumerName);
		final Range<String> range = Range.create(start.toString(), end.toString());
		final Limit limit = Limit.from(count);

		return xPending(key, consumer, range, limit, args);
	}

	@Override
	public List<StreamEntry> xRange(final byte[] key, final StreamEntryId start, final StreamEntryId end) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end);
		final Range<String> range = Range.create(start.toString(), end.toString());
		final ListConverter<StreamMessage<byte[], byte[]>, StreamEntry> listStreamMessageConverter =
				StreamMessageConverter.listConverter();

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.XRANGE, (cmd)->cmd.xrange(key, range),
					listStreamMessageConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.XRANGE, (cmd)->cmd.xrange(key, range),
					listStreamMessageConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.XRANGE, (cmd)->cmd.xrange(key, range),
					listStreamMessageConverter)
					.run(args);
		}
	}

	@Override
	public List<StreamEntry> xRange(final byte[] key, final StreamEntryId start, final StreamEntryId end,
									final long count) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end);
		final Range<String> range = Range.create(start.toString(), end.toString());
		final Limit limit = Limit.from(count);
		final ListConverter<StreamMessage<byte[], byte[]>, StreamEntry> listStreamMessageConverter =
				StreamMessageConverter.listConverter();

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.XRANGE, (cmd)->cmd.xrange(key, range, limit),
					listStreamMessageConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.XRANGE, (cmd)->cmd.xrange(key, range, limit),
					listStreamMessageConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.XRANGE, (cmd)->cmd.xrange(key, range, limit),
					listStreamMessageConverter)
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
		final CommandArguments args = CommandArguments.create(count).add(streams);
		final XReadArgs xReadArgs = new LettuceXReadArgs(count);

		return xRead(xReadArgs, streams, args);
	}

	@Override
	public List<Map<String, List<StreamEntry>>> xRead(final int block, final Map<String, StreamEntryId> streams) {
		final CommandArguments args = CommandArguments.create(block).add(streams);
		final XReadArgs xReadArgs = new LettuceXReadArgs(block);

		return xRead(xReadArgs, streams, args);
	}

	@Override
	public List<Map<String, List<StreamEntry>>> xRead(final long count, final int block,
													  final Map<String, StreamEntryId> streams) {
		final CommandArguments args = CommandArguments.create(count).add(block)
				.add(streams);
		final XReadArgs xReadArgs = new LettuceXReadArgs(block, count);

		return xRead(xReadArgs, streams, args);
	}

	@Override
	public List<Map<String, List<StreamEntry>>> xReadGroup(final String groupName, final String consumerName,
														   final Map<String, StreamEntryId> streams) {
		final CommandArguments args = CommandArguments.create(groupName).add(consumerName)
				.add(streams);
		return xReadGroup(groupName, consumerName, streams, args);
	}

	@Override
	public List<Map<byte[], List<StreamEntry>>> xReadGroup(final byte[] groupName, final byte[] consumerName,
														   final Map<byte[], StreamEntryId> streams) {
		final CommandArguments args = CommandArguments.create(groupName).add(consumerName)
				.add(streams);
		return xReadGroup(groupName, consumerName, streams, args);
	}

	@Override
	public List<Map<String, List<StreamEntry>>> xReadGroup(final String groupName, final String consumerName,
														   final long count, final Map<String, StreamEntryId> streams) {
		final CommandArguments args = CommandArguments.create(groupName).add(consumerName)
				.add(count).add(streams);
		final XReadArgs xReadArgs = new LettuceXReadArgs(count);

		return xReadGroup(groupName, consumerName, streams, xReadArgs, args);
	}

	@Override
	public List<Map<byte[], List<StreamEntry>>> xReadGroup(final byte[] groupName, final byte[] consumerName,
														   final long count, final Map<byte[], StreamEntryId> streams) {
		final CommandArguments args = CommandArguments.create(groupName).add(consumerName)
				.add(count).add(streams);
		final XReadArgs xReadArgs = new LettuceXReadArgs(count);

		return xReadGroup(groupName, consumerName, streams, xReadArgs, args);
	}

	@Override
	public List<Map<String, List<StreamEntry>>> xReadGroup(final String groupName, final String consumerName,
														   final int block, final Map<String, StreamEntryId> streams) {
		final CommandArguments args = CommandArguments.create(groupName).add(consumerName)
				.add(block).add(streams);
		final XReadArgs xReadArgs = new LettuceXReadArgs(block);

		return xReadGroup(groupName, consumerName, streams, xReadArgs, args);
	}

	@Override
	public List<Map<byte[], List<StreamEntry>>> xReadGroup(final byte[] groupName, final byte[] consumerName,
														   final int block, final Map<byte[], StreamEntryId> streams) {
		final CommandArguments args = CommandArguments.create(groupName).add(consumerName)
				.add(block).add(streams);
		final XReadArgs xReadArgs = new LettuceXReadArgs(block);

		return xReadGroup(groupName, consumerName, streams, xReadArgs, args);
	}

	@Override
	public List<Map<String, List<StreamEntry>>> xReadGroup(final String groupName, final String consumerName,
														   final boolean isNoAck,
														   final Map<String, StreamEntryId> streams) {
		final CommandArguments args = CommandArguments.create(groupName).add(consumerName)
				.add(isNoAck).add(streams);
		final XReadArgs xReadArgs = (new LettuceXReadArgs()).noack(isNoAck);

		return xReadGroup(groupName, consumerName, streams, xReadArgs, args);
	}

	@Override
	public List<Map<byte[], List<StreamEntry>>> xReadGroup(final byte[] groupName, final byte[] consumerName,
														   final boolean isNoAck,
														   final Map<byte[], StreamEntryId> streams) {
		final CommandArguments args = CommandArguments.create(groupName).add(consumerName)
				.add(isNoAck).add(streams);
		final XReadArgs xReadArgs = (new LettuceXReadArgs()).noack(isNoAck);

		return xReadGroup(groupName, consumerName, streams, xReadArgs, args);
	}

	@Override
	public List<Map<String, List<StreamEntry>>> xReadGroup(final String groupName, final String consumerName,
														   final long count, final int block,
														   final Map<String, StreamEntryId> streams) {
		final CommandArguments args = CommandArguments.create(groupName).add(consumerName)
				.add(count).add(block).add(streams);
		final XReadArgs xReadArgs = new LettuceXReadArgs(block, count);

		return xReadGroup(groupName, consumerName, streams, xReadArgs, args);
	}

	@Override
	public List<Map<byte[], List<StreamEntry>>> xReadGroup(final byte[] groupName, final byte[] consumerName,
														   final long count, final int block,
														   final Map<byte[], StreamEntryId> streams) {
		final CommandArguments args = CommandArguments.create(groupName).add(consumerName)
				.add(count).add(block).add(streams);
		final XReadArgs xReadArgs = new LettuceXReadArgs(block, count);

		return xReadGroup(groupName, consumerName, streams, xReadArgs, args);
	}

	@Override
	public List<Map<String, List<StreamEntry>>> xReadGroup(final String groupName, final String consumerName,
														   final long count, final boolean isNoAck,
														   final Map<String, StreamEntryId> streams) {
		final CommandArguments args = CommandArguments.create(groupName).add(consumerName)
				.add(count).add(isNoAck).add(streams);
		final XReadArgs xReadArgs = new LettuceXReadArgs(isNoAck, count);

		return xReadGroup(groupName, consumerName, streams, xReadArgs, args);
	}

	@Override
	public List<Map<byte[], List<StreamEntry>>> xReadGroup(final byte[] groupName, final byte[] consumerName,
														   final long count, final boolean isNoAck,
														   final Map<byte[], StreamEntryId> streams) {
		final CommandArguments args = CommandArguments.create(groupName).add(consumerName)
				.add(count).add(isNoAck).add(streams);
		final XReadArgs xReadArgs = new LettuceXReadArgs(isNoAck, count);

		return xReadGroup(groupName, consumerName, streams, xReadArgs, args);
	}

	@Override
	public List<Map<String, List<StreamEntry>>> xReadGroup(final String groupName, final String consumerName,
														   final int block, final boolean isNoAck,
														   final Map<String, StreamEntryId> streams) {
		final CommandArguments args = CommandArguments.create(groupName).add(consumerName)
				.add(block).add(isNoAck).add(streams);
		final XReadArgs xReadArgs = (new LettuceXReadArgs()).noack(isNoAck);

		return xReadGroup(groupName, consumerName, streams, xReadArgs, args);
	}

	@Override
	public List<Map<byte[], List<StreamEntry>>> xReadGroup(final byte[] groupName, final byte[] consumerName,
														   final int block, final boolean isNoAck,
														   final Map<byte[], StreamEntryId> streams) {
		final CommandArguments args = CommandArguments.create(groupName).add(consumerName)
				.add(block).add(isNoAck).add(streams);
		final XReadArgs xReadArgs = (new LettuceXReadArgs()).noack(isNoAck);

		return xReadGroup(groupName, consumerName, streams, xReadArgs, args);
	}

	@Override
	public List<Map<String, List<StreamEntry>>> xReadGroup(final String groupName, final String consumerName,
														   final long count, final int block, final boolean isNoAck,
														   final Map<String, StreamEntryId> streams) {
		final CommandArguments args = CommandArguments.create(groupName).add(consumerName)
				.add(count).add(block).add(isNoAck).add(streams);
		final XReadArgs xReadArgs = new LettuceXReadArgs(block, isNoAck, count);

		return xReadGroup(groupName, consumerName, streams, xReadArgs, args);
	}

	@Override
	public List<Map<byte[], List<StreamEntry>>> xReadGroup(final byte[] groupName, final byte[] consumerName,
														   final long count, final int block, final boolean isNoAck,
														   final Map<byte[], StreamEntryId> streams) {
		final CommandArguments args = CommandArguments.create(groupName).add(consumerName)
				.add(count).add(block).add(isNoAck).add(streams);
		final XReadArgs xReadArgs = new LettuceXReadArgs(block, isNoAck, count);

		return xReadGroup(groupName, consumerName, streams, xReadArgs, args);
	}

	@Override
	public List<StreamEntry> xRevRange(final byte[] key, final StreamEntryId end, final StreamEntryId start) {
		final CommandArguments args = CommandArguments.create(key).add(end).add(start);
		final Range<String> range = Range.create(start.toString(), end.toString());
		final ListConverter<StreamMessage<byte[], byte[]>, StreamEntry> listStreamMessageConverter =
				StreamMessageConverter.listConverter();

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.XREVRANGE, (cmd)->cmd.xrevrange(key, range),
					listStreamMessageConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.XREVRANGE, (cmd)->cmd.xrevrange(key, range),
					listStreamMessageConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.XREVRANGE, (cmd)->cmd.xrevrange(key, range),
					listStreamMessageConverter)
					.run(args);
		}
	}

	@Override
	public List<StreamEntry> xRevRange(final byte[] key, final StreamEntryId end, final StreamEntryId start,
									   final long count) {
		final CommandArguments args = CommandArguments.create(key).add(end).add(start)
				.add(count);
		final Range<String> range = Range.create(start.toString(), end.toString());
		final Limit limit = Limit.from(count);
		final ListConverter<StreamMessage<byte[], byte[]>, StreamEntry> listStreamMessageConverter =
				StreamMessageConverter.listConverter();

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.XREVRANGE,
					(cmd)->cmd.xrevrange(key, range, limit), listStreamMessageConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.XREVRANGE,
					(cmd)->cmd.xrevrange(key, range, limit), listStreamMessageConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.XREVRANGE, (cmd)->cmd.xrevrange(key, range, limit),
					listStreamMessageConverter)
					.run(args);
		}
	}

	@Override
	public Long xTrim(final byte[] key, final XTrimArgument xTrimArgument) {
		final CommandArguments args = CommandArguments.create(key).add(xTrimArgument);
		return xTrim(key, xTrimArgument.isApproximateTrimming(), Long.MAX_VALUE, args);
	}

	@Override
	public Long xTrim(final byte[] key, final XTrimArgument xTrimArgument, final long limit) {
		final CommandArguments args = CommandArguments.create(key).add(xTrimArgument)
				.add(limit);
		return xTrim(key, xTrimArgument.isApproximateTrimming(), limit, args);
	}

	private StreamEntryId xAdd(final byte[] key, final Map<byte[], byte[]> hash, final XAddArgs xAddArgs,
							   final CommandArguments args) {
		final StreamEntryIDConverter streamEntryIDConverter = new StreamEntryIDConverter();

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.XADD, (cmd)->cmd.xadd(key, xAddArgs, hash),
					streamEntryIDConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.XADD, (cmd)->cmd.xadd(key, xAddArgs, hash),
					streamEntryIDConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.XADD, (cmd)->cmd.xadd(key, xAddArgs, hash),
					streamEntryIDConverter)
					.run(args);
		}
	}

	private Map<StreamEntryId, List<StreamEntry>> xAutoClaim(final CommandArguments args) {

		if(isPipeline()){
			return new LettucePipelineCommand<Map<StreamEntryId, List<StreamEntry>>, Map<StreamEntryId, List<StreamEntry>>>(
					client, ProtocolCommand.XAUTOCLAIM)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<Map<StreamEntryId, List<StreamEntry>>, Map<StreamEntryId, List<StreamEntry>>>(
					client, ProtocolCommand.XAUTOCLAIM)
					.run(args);
		}else{
			return new LettuceCommand<Map<StreamEntryId, List<StreamEntry>>, Map<StreamEntryId, List<StreamEntry>>>(
					client, ProtocolCommand.XAUTOCLAIM)
					.run(args);
		}
	}

	private Map<StreamEntryId, List<StreamEntryId>> xAutoClaimJustId(final CommandArguments args) {

		if(isPipeline()){
			return new LettucePipelineCommand<Map<StreamEntryId, List<StreamEntryId>>, Map<StreamEntryId, List<StreamEntryId>>>(
					client, ProtocolCommand.XAUTOCLAIM)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<Map<StreamEntryId, List<StreamEntryId>>, Map<StreamEntryId, List<StreamEntryId>>>(
					client, ProtocolCommand.XAUTOCLAIM)
					.run(args);
		}else{
			return new LettuceCommand<Map<StreamEntryId, List<StreamEntryId>>, Map<StreamEntryId, List<StreamEntryId>>>(
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
			return new LettucePipelineCommand<>(client, ProtocolCommand.XCLAIM,
					(cmd)->cmd.xclaim(key, consumer, xClaimArgs, messageIds), listStreamMessageConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.XCLAIM,
					(cmd)->cmd.xclaim(key, consumer, xClaimArgs, messageIds), listStreamMessageConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.XCLAIM, (cmd)->cmd.xclaim(key, consumer, xClaimArgs,
					messageIds), listStreamMessageConverter)
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
			return new LettucePipelineCommand<>(client, ProtocolCommand.XCLAIM,
					(cmd)->cmd.xclaim(key, consumer, xClaimArgs, messageIds), listStreamMessageStreamEntryIdConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.XCLAIM,
					(cmd)->cmd.xclaim(key, consumer, xClaimArgs, messageIds), listStreamMessageStreamEntryIdConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.XCLAIM,
					(cmd)->cmd.xclaim(key, consumer, xClaimArgs, messageIds), listStreamMessageStreamEntryIdConverter)
					.run(args);
		}
	}

	private Status xGroupCreateConsumer(final CommandArguments args) {
		if(isPipeline()){
			return new LettucePipelineCommand<Status, Status>(client, ProtocolCommand.XGROUP_CREATECONSUMER)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<Status, Status>(client, ProtocolCommand.XGROUP_CREATECONSUMER)
					.run(args);
		}else{
			return new LettuceCommand<Status, Status>(client, ProtocolCommand.XGROUP_CREATECONSUMER)
					.run(args);
		}
	}

	private List<StreamPending> xPending(final byte[] key, final byte[] groupName, Range<String> range,
										 final Limit limit, final CommandArguments args) {
		final ListConverter<PendingMessage, StreamPending> listStreamPendingConverter = PendingMessageConverter.listConverter();

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.XPENDING,
					(cmd)->cmd.xpending(key, groupName, range, limit), listStreamPendingConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.XPENDING,
					(cmd)->cmd.xpending(key, groupName, range, limit), listStreamPendingConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.XPENDING,
					(cmd)->cmd.xpending(key, groupName, range, limit), listStreamPendingConverter)
					.run(args);
		}
	}

	private List<StreamPending> xPending(final byte[] key, final Consumer<byte[]> consumer, Range<String> range,
										 final Limit limit, final CommandArguments args) {
		final ListConverter<PendingMessage, StreamPending> listStreamPendingConverter = PendingMessageConverter.listConverter();

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.XPENDING,
					(cmd)->cmd.xpending(key, consumer, range, limit), listStreamPendingConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.XPENDING,
					(cmd)->cmd.xpending(key, consumer, range, limit), listStreamPendingConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.XPENDING,
					(cmd)->cmd.xpending(key, consumer, range, limit), listStreamPendingConverter)
					.run(args);
		}
	}

	private List<Map<String, List<StreamEntry>>> xRead(final Map<String, StreamEntryId> streams,
													   final CommandArguments args) {
		final XReadArgs.StreamOffset<byte[]>[] streamOffsets = createStreamOffsetFromStreamEntryIdMap(streams);
		final ListConverter<StreamMessage<byte[], byte[]>, Map<String, List<StreamEntry>>> listStreamMessageMapConverter =
				StreamMessageMapConverter.listConverter();

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.XREAD, (cmd)->cmd.xread(streamOffsets),
					listStreamMessageMapConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.XREAD, (cmd)->cmd.xread(streamOffsets),
					listStreamMessageMapConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.XREAD, (cmd)->cmd.xread(streamOffsets),
					listStreamMessageMapConverter)
					.run(args);
		}
	}

	private List<Map<String, List<StreamEntry>>> xRead(final XReadArgs xReadArgs, final Map<String,
			StreamEntryId> streams, final CommandArguments args) {
		final XReadArgs.StreamOffset<byte[]>[] streamOffsets = createStreamOffsetFromStreamEntryIdMap(streams);
		final ListConverter<StreamMessage<byte[], byte[]>, Map<String, List<StreamEntry>>> listStreamMessageMapConverter =
				StreamMessageMapConverter.listConverter();

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.XREAD,
					(cmd)->cmd.xread(xReadArgs, streamOffsets), listStreamMessageMapConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.XREAD,
					(cmd)->cmd.xread(xReadArgs, streamOffsets), listStreamMessageMapConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.XREAD, (cmd)->cmd.xread(xReadArgs, streamOffsets),
					listStreamMessageMapConverter)
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
			return new LettucePipelineCommand<>(client, ProtocolCommand.XREADGROUP,
					(cmd)->cmd.xreadgroup(consumer, streamOffsets), converter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.XREADGROUP,
					(cmd)->cmd.xreadgroup(consumer, streamOffsets), converter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.XREADGROUP,
					(cmd)->cmd.xreadgroup(consumer, streamOffsets), converter)
					.run(args);
		}
	}

	private List<Map<String, List<StreamEntry>>> xReadGroup(final String groupName, final String consumerName,
															final Map<String, StreamEntryId> streams,
															final XReadArgs xReadArgs, final CommandArguments args) {
		final XReadArgs.StreamOffset<byte[]>[] streamOffsets = createStreamOffsetFromStreamEntryIdMap(streams);
		final ListConverter<StreamMessage<byte[], byte[]>, Map<String, List<StreamEntry>>> listStreamMessageMapConverter =
				StreamMessageMapConverter.listConverter();

		return xReadGroup(SafeEncoder.encode(groupName), SafeEncoder.encode(consumerName), streamOffsets, xReadArgs,
				listStreamMessageMapConverter, args);
	}

	private List<Map<byte[], List<StreamEntry>>> xReadGroup(final byte[] groupName, final byte[] consumerName,
															final Map<byte[], StreamEntryId> streams,
															final XReadArgs xReadArgs, final CommandArguments args) {
		final XReadArgs.StreamOffset<byte[]>[] streamOffsets = createStreamOffsetFromBinaryStreamEntryIdMap(streams);
		final ListConverter<StreamMessage<byte[], byte[]>, Map<byte[], List<StreamEntry>>> listStreamMessageMapConverter =
				StreamMessageMapConverter.listConverter();

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
			return new LettucePipelineCommand<>(client, ProtocolCommand.XREADGROUP,
					(cmd)->cmd.xreadgroup(consumer, xReadArgs, streamOffsets), converter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.XREADGROUP,
					(cmd)->cmd.xreadgroup(consumer, xReadArgs, streamOffsets), converter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.XREADGROUP,
					(cmd)->cmd.xreadgroup(consumer, xReadArgs, streamOffsets), converter)
					.run(args);
		}
	}

	private Long xTrim(final byte[] key, final boolean approximateTrimming, final long limit,
					   final CommandArguments args) {

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.XTRIM,
					(cmd)->cmd.xtrim(key, approximateTrimming, limit), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.XTRIM,
					(cmd)->cmd.xtrim(key, approximateTrimming, limit), (v)->v)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.XTRIM,
					(cmd)->cmd.xtrim(key, approximateTrimming, limit), (v)->v)
					.run(args);
		}
	}

}
