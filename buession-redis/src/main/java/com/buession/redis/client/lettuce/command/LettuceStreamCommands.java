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
package com.buession.redis.client.lettuce.command;

import com.buession.core.converter.ArrayConverter;
import com.buession.core.converter.BooleanStatusConverter;
import com.buession.core.converter.Converter;
import com.buession.core.converter.ListConverter;
import com.buession.core.converter.MapConverter;
import com.buession.lang.KeyValue;
import com.buession.lang.Status;
import com.buession.redis.client.lettuce.LettuceRedisClient;
import com.buession.redis.core.Keyword;
import com.buession.redis.core.Stream;
import com.buession.redis.core.StreamConsumer;
import com.buession.redis.core.StreamDeletionPolicy;
import com.buession.redis.core.StreamEntry;
import com.buession.redis.core.StreamEntryDeletionResult;
import com.buession.redis.core.StreamEntryId;
import com.buession.redis.core.StreamFull;
import com.buession.redis.core.StreamGroup;
import com.buession.redis.core.StreamPending;
import com.buession.redis.core.StreamPendingSummary;
import com.buession.redis.core.XReadGroupInfo;
import com.buession.redis.core.XReadInfo;
import com.buession.redis.core.command.Command;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.command.StreamCommands;
import com.buession.redis.core.command.SubCommand;
import com.buession.redis.core.command.args.XAddArgument;
import com.buession.redis.core.command.args.XClaimArgument;
import com.buession.redis.core.command.args.XReadArgument;
import com.buession.redis.core.command.args.XReadGroupArgument;
import com.buession.redis.core.internal.convert.jedis.params.XReadGroupArgumentConverter;
import com.buession.redis.core.internal.convert.lettuce.params.StreamDeletionPolicyConverter;
import com.buession.redis.core.internal.convert.lettuce.params.XAddArgumentConverter;
import com.buession.redis.core.internal.convert.lettuce.params.XClaimArgumentConverter;
import com.buession.redis.core.internal.convert.lettuce.params.XReadArgumentConverter;
import com.buession.redis.core.internal.convert.lettuce.response.ClaimedMessagesKeyValueConverter;
import com.buession.redis.core.internal.convert.lettuce.response.PendingMessageConverter;
import com.buession.redis.core.internal.convert.lettuce.response.PendingMessagesConverter;
import com.buession.redis.core.internal.convert.lettuce.response.StreamConsumersInfoConverter;
import com.buession.redis.core.internal.convert.lettuce.response.StreamEntryDeletionResultConverter;
import com.buession.redis.core.internal.convert.lettuce.response.StreamEntryIDConverter;
import com.buession.redis.core.internal.convert.lettuce.response.StreamFullInfoConverter;
import com.buession.redis.core.internal.convert.lettuce.response.StreamGroupInfoConverter;
import com.buession.redis.core.internal.convert.lettuce.response.StreamInfoConverter;
import com.buession.redis.core.internal.convert.lettuce.response.StreamMessageConverter;
import com.buession.redis.core.internal.convert.lettuce.response.StreamMessageKeyValueConverter;
import com.buession.redis.core.internal.convert.lettuce.response.StreamMessageStreamEntryXReadInfoConverter;
import com.buession.redis.core.internal.convert.response.OkStatusConverter;
import com.buession.redis.core.internal.jedis.args.JedisXReadGroupParams;
import com.buession.redis.core.internal.lettuce.CompositeArgumentUtils;
import com.buession.redis.core.internal.lettuce.args.LettuceXAddArgs;
import com.buession.redis.core.internal.lettuce.args.LettuceXAutoClaimArgs;
import com.buession.redis.core.internal.lettuce.args.LettuceXGroupCreateArgs;
import com.buession.redis.core.internal.lettuce.args.LettuceXReadArgs;
import com.buession.redis.utils.SafeEncoder;
import io.lettuce.core.Consumer;
import io.lettuce.core.Limit;
import io.lettuce.core.Range;
import io.lettuce.core.StreamMessage;
import io.lettuce.core.XAddArgs;
import io.lettuce.core.XClaimArgs;
import io.lettuce.core.XReadArgs;
import redis.clients.jedis.params.XReadGroupParams;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Lettuce Stream 命令
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public final class LettuceStreamCommands extends AbstractLettuceRedisCommands implements StreamCommands {

	public LettuceStreamCommands(final LettuceRedisClient client) {
		super(client);
	}

	@Override
	public Long xAck(final String key, final String groupName, final StreamEntryId... ids) {
		final CommandArguments args = CommandArguments.create(key).add(groupName).add(ids);
		return executeCommand(Command.XACK, args, (cmd)->cmd.xack(rawBinaryKey(key), SafeEncoder.encode(groupName),
				CompositeArgumentUtils.messageIds(ids)), (v)->v);
	}

	@Override
	public Long xAck(final byte[] key, final byte[] groupName, final StreamEntryId... ids) {
		final CommandArguments args = CommandArguments.create(key).add(groupName).add(ids);
		return executeCommand(Command.XACK, args, (cmd)->cmd.xack(rawKey(key), groupName,
				CompositeArgumentUtils.messageIds(ids)), (v)->v);
	}

	@Override
	public List<StreamEntryDeletionResult> xAckDel(final String key, final String groupName,
												   final StreamEntryId... ids) {
		return xAckDel(SafeEncoder.encode(key), SafeEncoder.encode(groupName), ids);
	}

	@Override
	public List<StreamEntryDeletionResult> xAckDel(final byte[] key, final byte[] groupName,
												   final StreamEntryId... ids) {
		final CommandArguments args = CommandArguments.create(key).add(groupName).add("IDS", ids.length).add(ids);
		return executeCommand(Command.XACKDEL, args,
				(cmd)->cmd.xackdel(rawKey(key), groupName, CompositeArgumentUtils.messageIds(ids)),
				new ListConverter<>(new StreamEntryDeletionResultConverter()));
	}

	@Override
	public List<StreamEntryDeletionResult> xAckDel(final String key, final String groupName,
												   final StreamDeletionPolicy deletionPolicy,
												   final StreamEntryId... ids) {
		return xAckDel(SafeEncoder.encode(key), SafeEncoder.encode(groupName), deletionPolicy, ids);
	}

	@Override
	public List<StreamEntryDeletionResult> xAckDel(final byte[] key, final byte[] groupName,
												   final StreamDeletionPolicy deletionPolicy,
												   final StreamEntryId... ids) {
		final CommandArguments args = CommandArguments.create(key).add(groupName).add(deletionPolicy)
				.add("IDS", ids.length).add(ids);
		final StreamDeletionPolicyConverter streamDeletionPolicyConverter = new StreamDeletionPolicyConverter();
		return executeCommand(Command.XACKDEL, args,
				(cmd)->cmd.xackdel(key, groupName, streamDeletionPolicyConverter.convert(deletionPolicy),
						CompositeArgumentUtils.messageIds(ids)),
				new ListConverter<>(new StreamEntryDeletionResultConverter()));
	}

	@Override
	public StreamEntryId xAdd(final String key, final StreamEntryId id, final Map<String, String> hash) {
		final CommandArguments args = CommandArguments.create(key).add(id).add(hash);
		return xAdd(key, hash, new LettuceXAddArgs(id), args);
	}

	@Override
	public StreamEntryId xAdd(final byte[] key, final StreamEntryId id, final Map<byte[], byte[]> hash) {
		final CommandArguments args = CommandArguments.create(key).add(id).add(hash);
		return xAdd(key, hash, new LettuceXAddArgs(id), args);
	}

	@Override
	public StreamEntryId xAdd(final String key, final StreamEntryId id, final Map<String, String> hash,
							  final XAddArgument xAddArgument) {
		final MapConverter<String, String, byte[], byte[]> mapConverter = new MapConverter<>(SafeEncoder::encode,
				SafeEncoder::encode);
		return xAdd(SafeEncoder.encode(key), id, mapConverter.convert(hash), xAddArgument);
	}

	@Override
	public StreamEntryId xAdd(final byte[] key, final StreamEntryId id, final Map<byte[], byte[]> hash,
							  final XAddArgument xAddArgument) {
		final CommandArguments args = CommandArguments.create(key).add(id).add(hash).add(xAddArgument);
		final XAddArgumentConverter xAddArgumentConverter = new XAddArgumentConverter();
		final XAddArgs xAddArgs = Optional.ofNullable(xAddArgumentConverter.convert(xAddArgument))
				.orElse(new XAddArgs());

		xAddArgs.id(id.toString());

		return xAdd(key, hash, xAddArgs, args);
	}

	@Override
	public KeyValue<StreamEntryId, List<StreamEntry<String, String>>> xAutoClaim(final String key,
																				 final String groupName,
																				 final String consumerName,
																				 final int minIdleTime,
																				 final StreamEntryId start) {
		final CommandArguments args = CommandArguments.create(key).add(groupName).add(consumerName).add(minIdleTime)
				.add(start);
		return xAutoClaim(key,
				new LettuceXAutoClaimArgs<>(SafeEncoder.encode(groupName), SafeEncoder.encode(consumerName),
						minIdleTime, start), args);
	}

	@Override
	public KeyValue<StreamEntryId, List<StreamEntry<byte[], byte[]>>> xAutoClaim(final byte[] key,
																				 final byte[] groupName,
																				 final byte[] consumerName,
																				 final int minIdleTime,
																				 final StreamEntryId start) {
		final CommandArguments args = CommandArguments.create(key).add(groupName).add(consumerName).add(minIdleTime)
				.add(start);
		return xAutoClaim(key, new LettuceXAutoClaimArgs<>(groupName, consumerName, minIdleTime, start), args);
	}

	@Override
	public KeyValue<StreamEntryId, List<StreamEntry<String, String>>> xAutoClaim(final String key,
																				 final String groupName,
																				 final String consumerName,
																				 final int minIdleTime,
																				 final StreamEntryId start,
																				 final int count) {
		final CommandArguments args = CommandArguments.create(key).add(groupName).add(consumerName).add(minIdleTime)
				.add(start).add(Keyword.Common.COUNT).add(count);
		return xAutoClaim(key,
				new LettuceXAutoClaimArgs<>(SafeEncoder.encode(groupName), SafeEncoder.encode(consumerName),
						minIdleTime, start, count), args);
	}

	@Override
	public KeyValue<StreamEntryId, List<StreamEntry<byte[], byte[]>>> xAutoClaim(final byte[] key,
																				 final byte[] groupName,
																				 final byte[] consumerName,
																				 final int minIdleTime,
																				 final StreamEntryId start,
																				 final int count) {
		final CommandArguments args = CommandArguments.create(key).add(groupName).add(consumerName).add(minIdleTime)
				.add(start).add(Keyword.Common.COUNT).add(count);
		return xAutoClaim(key, new LettuceXAutoClaimArgs<>(groupName, consumerName, minIdleTime, start, count), args);
	}

	@Override
	public KeyValue<StreamEntryId, List<StreamEntryId>> xAutoClaimJustId(final String key, final String groupName,
																		 final String consumerName,
																		 final int minIdleTime,
																		 final StreamEntryId start) {
		final CommandArguments args = CommandArguments.create(key).add(groupName).add(consumerName).add(minIdleTime)
				.add(start).add("JUSTID");
		return xAutoClaimJustId(SafeEncoder.encode(key),
				new LettuceXAutoClaimArgs<>(SafeEncoder.encode(groupName), SafeEncoder.encode(consumerName),
						minIdleTime, start), args);
	}

	@Override
	public KeyValue<StreamEntryId, List<StreamEntryId>> xAutoClaimJustId(final byte[] key, final byte[] groupName,
																		 final byte[] consumerName,
																		 final int minIdleTime,
																		 final StreamEntryId start) {
		final CommandArguments args = CommandArguments.create(key).add(groupName).add(consumerName).add(minIdleTime)
				.add(start).add("JUSTID");
		return xAutoClaimJustId(key, new LettuceXAutoClaimArgs<>(groupName, consumerName, minIdleTime, start), args);
	}

	@Override
	public KeyValue<StreamEntryId, List<StreamEntryId>> xAutoClaimJustId(final String key, final String groupName,
																		 final String consumerName,
																		 final int minIdleTime,
																		 final StreamEntryId start, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(groupName).add(consumerName).add(minIdleTime)
				.add(start).add(Keyword.Common.COUNT).add(count).add("JUSTID");
		return xAutoClaimJustId(SafeEncoder.encode(key),
				new LettuceXAutoClaimArgs<>(SafeEncoder.encode(groupName), SafeEncoder.encode(consumerName),
						minIdleTime, start, count), args);
	}

	@Override
	public KeyValue<StreamEntryId, List<StreamEntryId>> xAutoClaimJustId(final byte[] key, final byte[] groupName,
																		 final byte[] consumerName,
																		 final int minIdleTime,
																		 final StreamEntryId start, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(groupName).add(consumerName).add(minIdleTime)
				.add(start).add(Keyword.Common.COUNT).add(count).add("JUSTID");
		return xAutoClaimJustId(key, new LettuceXAutoClaimArgs<>(groupName, consumerName, minIdleTime, start, count),
				args);
	}

	@Override
	public Status xCfgSet(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.XCFGSET, args);
	}

	@Override
	public Status xCfgSet(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.XCFGSET, args);
	}

	@Override
	public Status xCfgSet(final String key, final long duration) {
		final CommandArguments args = CommandArguments.create(key).add("IDMP-DURATION", duration);
		return executeCommand(Command.XCFGSET, args);
	}

	@Override
	public Status xCfgSet(final byte[] key, final long duration) {
		final CommandArguments args = CommandArguments.create(key).add("IDMP-DURATION", duration);
		return executeCommand(Command.XCFGSET, args);
	}

	@Override
	public Status xCfgSet(final String key, final int maxsize) {
		final CommandArguments args = CommandArguments.create(key).add("IDMP-MAXSIZE", maxsize);
		return executeCommand(Command.XCFGSET, args);
	}

	@Override
	public Status xCfgSet(final byte[] key, final int maxsize) {
		final CommandArguments args = CommandArguments.create(key).add("IDMP-MAXSIZE", maxsize);
		return executeCommand(Command.XCFGSET, args);
	}

	@Override
	public Status xCfgSet(final String key, final long duration, final int maxsize) {
		final CommandArguments args = CommandArguments.create(key).add("IDMP-DURATION", duration)
				.add("IDMP-MAXSIZE", maxsize);
		return executeCommand(Command.XCFGSET, args);
	}

	@Override
	public Status xCfgSet(final byte[] key, final long duration, final int maxsize) {
		final CommandArguments args = CommandArguments.create(key).add("IDMP-DURATION", duration)
				.add("IDMP-MAXSIZE", maxsize);
		return executeCommand(Command.XCFGSET, args);
	}

	@Override
	public List<StreamEntry<String, String>> xClaim(final String key, final String groupName, final String consumerName,
													final int minIdleTime, final StreamEntryId... ids) {
		final CommandArguments args = CommandArguments.create(key).add(groupName).add(consumerName).add(minIdleTime)
				.add(ids);
		return xClaim(key, ids, Consumer.from(SafeEncoder.encode(groupName), SafeEncoder.encode(consumerName)),
				minIdleTime, new XClaimArgs(), args);
	}

	@Override
	public List<StreamEntry<byte[], byte[]>> xClaim(final byte[] key, final byte[] groupName, final byte[] consumerName,
													final int minIdleTime, final StreamEntryId... ids) {
		final CommandArguments args = CommandArguments.create(key).add(groupName).add(consumerName).add(minIdleTime)
				.add(ids);
		return xClaim(key, ids, Consumer.from(groupName, consumerName), minIdleTime, new XClaimArgs(), args);
	}

	@Override
	public List<StreamEntry<String, String>> xClaim(final String key, final String groupName, final String consumerName,
													final int minIdleTime, final StreamEntryId[] ids,
													final XClaimArgument xClaimArgument) {
		final CommandArguments args = CommandArguments.create(key).add(groupName).add(consumerName).add(minIdleTime)
				.add(ids).add(xClaimArgument);
		final XClaimArgumentConverter xClaimArgumentConverter = new XClaimArgumentConverter();

		return xClaim(key, ids, Consumer.from(SafeEncoder.encode(groupName), SafeEncoder.encode(consumerName)),
				minIdleTime, xClaimArgumentConverter.convert(xClaimArgument), args);
	}

	@Override
	public List<StreamEntry<byte[], byte[]>> xClaim(final byte[] key, final byte[] groupName, final byte[] consumerName,
													final int minIdleTime, final StreamEntryId[] ids,
													final XClaimArgument xClaimArgument) {
		final CommandArguments args = CommandArguments.create(key).add(groupName).add(consumerName).add(minIdleTime)
				.add(ids).add(xClaimArgument);
		final XClaimArgumentConverter xClaimArgumentConverter = new XClaimArgumentConverter();

		return xClaim(key, ids, Consumer.from(groupName, consumerName), minIdleTime,
				xClaimArgumentConverter.convert(xClaimArgument), args);
	}

	@Override
	public List<StreamEntryId> xClaimJustId(final String key, final String groupName, final String consumerName,
											final int minIdleTime, final StreamEntryId... ids) {
		return xClaimJustId(SafeEncoder.encode(key), SafeEncoder.encode(groupName), SafeEncoder.encode(consumerName),
				minIdleTime, ids);
	}

	@Override
	public List<StreamEntryId> xClaimJustId(final byte[] key, final byte[] groupName, final byte[] consumerName,
											final int minIdleTime, final StreamEntryId... ids) {
		final CommandArguments args = CommandArguments.create(key).add(groupName).add(consumerName).add(minIdleTime)
				.add(ids).add("JUSTID");
		return xClaimJustId(key, ids, Consumer.from(groupName, consumerName), minIdleTime, new XClaimArgs(), args);
	}

	@Override
	public List<StreamEntryId> xClaimJustId(final String key, final String groupName, final String consumerName,
											final int minIdleTime, final StreamEntryId[] ids,
											final XClaimArgument xClaimArgument) {
		return xClaimJustId(SafeEncoder.encode(key), SafeEncoder.encode(groupName), SafeEncoder.encode(consumerName),
				minIdleTime, ids, xClaimArgument);
	}

	@Override
	public List<StreamEntryId> xClaimJustId(final byte[] key, final byte[] groupName, final byte[] consumerName,
											final int minIdleTime, final StreamEntryId[] ids,
											final XClaimArgument xClaimArgument) {
		final CommandArguments args = CommandArguments.create(key).add(groupName).add(consumerName).add(minIdleTime)
				.add(ids).add(xClaimArgument).add("JUSTID");
		final XClaimArgumentConverter xClaimArgumentConverter = new XClaimArgumentConverter();

		return xClaimJustId(key, ids, Consumer.from(groupName, consumerName), minIdleTime,
				xClaimArgumentConverter.convert(xClaimArgument), args);
	}

	@Override
	public Long xDel(final String key, final StreamEntryId... ids) {
		return xDel(SafeEncoder.encode(key), ids);
	}

	@Override
	public Long xDel(final byte[] key, final StreamEntryId... ids) {
		final CommandArguments args = CommandArguments.create(key).add(ids);
		return executeCommand(Command.XDEL, args, (cmd)->cmd.xdel(key, CompositeArgumentUtils.messageIds(ids)), (v)->v);
	}

	@Override
	public List<StreamEntryDeletionResult> xDelEx(final String key, final StreamDeletionPolicy deletionPolicy,
												  final StreamEntryId... ids) {
		return xDelEx(SafeEncoder.encode(key), deletionPolicy, ids);
	}

	@Override
	public List<StreamEntryDeletionResult> xDelEx(final byte[] key, final StreamDeletionPolicy deletionPolicy,
												  final StreamEntryId... ids) {
		final CommandArguments args = CommandArguments.create(key).add(deletionPolicy).add("IDS", ids.length).add(ids);
		final StreamDeletionPolicyConverter streamDeletionPolicyConverter = new StreamDeletionPolicyConverter();
		return executeCommand(Command.XDELEX, args,
				(cmd)->cmd.xdelex(key, streamDeletionPolicyConverter.convert(deletionPolicy),
						CompositeArgumentUtils.messageIds(ids)),
				new ListConverter<>(new StreamEntryDeletionResultConverter()));
	}

	@Override
	public Status xGroupCreate(final String key, final String groupName, final StreamEntryId id) {
		return xGroupCreate(SafeEncoder.encode(key), SafeEncoder.encode(groupName), id);
	}

	@Override
	public Status xGroupCreate(final byte[] key, final byte[] groupName, final StreamEntryId id) {
		final CommandArguments args = CommandArguments.create(key).add(groupName).add(id);
		return executeCommand(Command.XGROUP, SubCommand.XGROUP_CREATE, args,
				(cmd)->cmd.xgroupCreate(XReadArgs.StreamOffset.from(key, id.toString()), groupName),
				new OkStatusConverter());
	}

	@Override
	public Status xGroupCreate(final String key, final String groupName, final StreamEntryId id,
							   final boolean makeStream) {
		return xGroupCreate(SafeEncoder.encode(key), SafeEncoder.encode(groupName), id, makeStream);
	}

	@Override
	public Status xGroupCreate(final byte[] key, final byte[] groupName, final StreamEntryId id,
							   final boolean makeStream) {
		final CommandArguments args = CommandArguments.create(key).add(groupName).add(id)
				.add(makeStream ? "MKSTREAM" : null);
		return executeCommand(Command.XGROUP, SubCommand.XGROUP_CREATE, args,
				(cmd)->cmd.xgroupCreate(XReadArgs.StreamOffset.from(key, id.toString()), groupName,
						new LettuceXGroupCreateArgs(makeStream)), new OkStatusConverter());
	}

	@Override
	public Status xGroupCreate(final String key, final String groupName, final StreamEntryId id,
							   final long entriesRead) {
		return xGroupCreate(SafeEncoder.encode(key), SafeEncoder.encode(groupName), id, entriesRead);
	}

	@Override
	public Status xGroupCreate(final byte[] key, final byte[] groupName, final StreamEntryId id,
							   final long entriesRead) {
		final CommandArguments args = CommandArguments.create(key).add(groupName).add(id)
				.add("ENTRIESREAD", entriesRead);
		return xGroupCreate(key, groupName, id, new LettuceXGroupCreateArgs(entriesRead), args);
	}

	@Override
	public Status xGroupCreate(final String key, final String groupName, final StreamEntryId id,
							   final boolean makeStream, final long entriesRead) {
		return xGroupCreate(SafeEncoder.encode(key), SafeEncoder.encode(groupName), id, makeStream, entriesRead);
	}

	@Override
	public Status xGroupCreate(final byte[] key, final byte[] groupName, final StreamEntryId id,
							   final boolean makeStream, final long entriesRead) {
		final CommandArguments args = CommandArguments.create(key).add(groupName).add(id)
				.add(makeStream ? "MKSTREAM" : null).add("ENTRIESREAD", entriesRead);
		return xGroupCreate(key, groupName, id, new LettuceXGroupCreateArgs(makeStream, entriesRead), args);
	}

	@Override
	public Status xGroupCreateConsumer(final String key, final String groupName, final String consumerName) {
		final CommandArguments args = CommandArguments.create(key).add(groupName).add(consumerName);
		return executeCommand(Command.XGROUP, SubCommand.XGROUP_CREATECONSUMER, args,
				(cmd)->cmd.xgroupCreateconsumer(SafeEncoder.encode(key),
						Consumer.from(SafeEncoder.encode(groupName), SafeEncoder.encode(consumerName))),
				new BooleanStatusConverter());
	}

	@Override
	public Status xGroupCreateConsumer(final byte[] key, final byte[] groupName, final byte[] consumerName) {
		final CommandArguments args = CommandArguments.create(key).add(groupName).add(consumerName);
		return executeCommand(Command.XGROUP, SubCommand.XGROUP_CREATECONSUMER, args,
				(cmd)->cmd.xgroupCreateconsumer(key, Consumer.from(groupName, consumerName)),
				new BooleanStatusConverter());
	}

	@Override
	public Long xGroupDelConsumer(final String key, final String groupName, final String consumerName) {
		final CommandArguments args = CommandArguments.create(key).add(groupName).add(consumerName);
		return executeCommand(Command.XGROUP, SubCommand.XGROUP_DELCONSUMER, args,
				(cmd)->cmd.xgroupDelconsumer(SafeEncoder.encode(key),
						Consumer.from(SafeEncoder.encode(groupName), SafeEncoder.encode(consumerName))), (v)->v);
	}

	@Override
	public Long xGroupDelConsumer(final byte[] key, final byte[] groupName, final byte[] consumerName) {
		final CommandArguments args = CommandArguments.create(key).add(groupName).add(consumerName);
		return executeCommand(Command.XGROUP, SubCommand.XGROUP_DELCONSUMER, args,
				(cmd)->cmd.xgroupDelconsumer(key, Consumer.from(groupName, consumerName)), (v)->v);
	}

	@Override
	public Status xGroupDestroy(final String key, final String groupName) {
		final CommandArguments args = CommandArguments.create(key).add(groupName);
		return executeCommand(Command.XGROUP, SubCommand.XGROUP_DESTROY, args,
				(cmd)->cmd.xgroupDestroy(SafeEncoder.encode(key), SafeEncoder.encode(groupName)),
				new BooleanStatusConverter());
	}

	@Override
	public Status xGroupDestroy(final byte[] key, final byte[] groupName) {
		final CommandArguments args = CommandArguments.create(key).add(groupName);
		return executeCommand(Command.XGROUP, SubCommand.XGROUP_DESTROY, args, (cmd)->cmd.xgroupDestroy(key, groupName),
				new BooleanStatusConverter());
	}

	@Override
	public Status xGroupSetId(final String key, final String groupName, final StreamEntryId id) {
		final CommandArguments args = CommandArguments.create(key).add(groupName).add(id);
		return executeCommand(Command.XGROUP, SubCommand.XGROUP_SETID, args,
				(cmd)->cmd.xgroupSetid(XReadArgs.StreamOffset.from(SafeEncoder.encode(key), id.toString()),
						SafeEncoder.encode(groupName)), new OkStatusConverter());
	}

	@Override
	public Status xGroupSetId(final byte[] key, final byte[] groupName, final StreamEntryId id) {
		final CommandArguments args = CommandArguments.create(key).add(groupName).add(id);
		return executeCommand(Command.XGROUP, SubCommand.XGROUP_SETID, args,
				(cmd)->cmd.xgroupSetid(XReadArgs.StreamOffset.from(key, id.toString()), groupName),
				new OkStatusConverter());
	}

	@Override
	public Status xGroupSetId(final String key, final String groupName, final StreamEntryId id,
							  final long entriesRead) {
		final CommandArguments args = CommandArguments.create(key).add(groupName).add(id)
				.add("ENTRIESREAD", entriesRead);
		return executeCommand(Command.XGROUP, SubCommand.XGROUP_SETID, args,
				(cmd)->cmd.xgroupSetid(XReadArgs.StreamOffset.from(SafeEncoder.encode(key), id.toString()),
						SafeEncoder.encode(groupName)), new OkStatusConverter());
	}

	@Override
	public Status xGroupSetId(final byte[] key, final byte[] groupName, final StreamEntryId id,
							  final long entriesRead) {
		final CommandArguments args = CommandArguments.create(key).add(groupName).add(id)
				.add("ENTRIESREAD", entriesRead);
		return executeCommand(Command.XGROUP, SubCommand.XGROUP_SETID, args,
				(cmd)->cmd.xgroupSetid(XReadArgs.StreamOffset.from(key, id.toString()), groupName),
				new OkStatusConverter());
	}

	@Override
	public List<StreamConsumer> xInfoConsumers(final String key, final String groupName) {
		final CommandArguments args = CommandArguments.create(key).add(groupName);
		return executeCommand(Command.XINFO, SubCommand.XINFO_CONSUMERS, args,
				(cmd)->cmd.xinfoConsumers(SafeEncoder.encode(key), SafeEncoder.encode(groupName)),
				new ListConverter<>(new StreamConsumersInfoConverter()));
	}

	@Override
	public List<StreamConsumer> xInfoConsumers(final byte[] key, final byte[] groupName) {
		final CommandArguments args = CommandArguments.create(key).add(groupName);
		return executeCommand(Command.XINFO, SubCommand.XINFO_CONSUMERS, args,
				(cmd)->cmd.xinfoConsumers(key, groupName), new ListConverter<>(new StreamConsumersInfoConverter()));
	}

	@Override
	public List<StreamGroup> xInfoGroups(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.XINFO, SubCommand.XINFO_GROUPS, args,
				(cmd)->cmd.xinfoGroups(SafeEncoder.encode(key)), new ListConverter<>(new StreamGroupInfoConverter()));
	}

	@Override
	public List<StreamGroup> xInfoGroups(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.XINFO, SubCommand.XINFO_GROUPS, args, (cmd)->cmd.xinfoGroups(key),
				new ListConverter<>(new StreamGroupInfoConverter()));
	}

	@Override
	public Stream<String, String> xInfoStream(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.XINFO, SubCommand.XINFO_STREAM, args,
				(cmd)->cmd.xinfoStream(SafeEncoder.encode(key)), new StreamInfoConverter<>());
	}

	@Override
	public Stream<byte[], byte[]> xInfoStream(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.XINFO, SubCommand.XINFO_STREAM, args, (cmd)->cmd.xinfoStream(key),
				new StreamInfoConverter<>());
	}

	@Override
	public StreamFull xInfoStream(final String key, final boolean full) {
		return xInfoStream(SafeEncoder.encode(key), full);
	}

	@Override
	public StreamFull xInfoStream(final byte[] key, final boolean full) {
		final CommandArguments args = CommandArguments.create(key).add(full ? "FULL" : null);
		return executeCommand(Command.XINFO, SubCommand.XINFO_STREAM, args, (cmd)->cmd.xinfoStream(key),
				new StreamFullInfoConverter());
	}

	@Override
	public StreamFull xInfoStream(final String key, final boolean full, final int count) {
		return xInfoStream(SafeEncoder.encode(key), full, count);
	}

	@Override
	public StreamFull xInfoStream(final byte[] key, final boolean full, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(full ? "FULL" : null)
				.add(Keyword.Common.COUNT, count);
		return executeCommand(Command.XINFO, SubCommand.XINFO_STREAM, args, (cmd)->cmd.xinfoStream(key),
				new StreamFullInfoConverter());
	}

	@Override
	public Long xLen(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.XLEN, args, (cmd)->cmd.xlen(SafeEncoder.encode(key)), (v)->v);
	}

	@Override
	public Long xLen(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.XLEN, args, (cmd)->cmd.xlen(key), (v)->v);
	}

	@Override
	public StreamPendingSummary xPending(final String key, final String groupName) {
		final CommandArguments args = CommandArguments.create(key).add(groupName);
		return executeCommand(Command.XPENDING, args,
				(cmd)->cmd.xpending(SafeEncoder.encode(key), SafeEncoder.encode(groupName)),
				new PendingMessagesConverter());
	}

	@Override
	public StreamPendingSummary xPending(final byte[] key, final byte[] groupName) {
		final CommandArguments args = CommandArguments.create(key).add(groupName);
		return executeCommand(Command.XPENDING, args, (cmd)->cmd.xpending(key, groupName),
				new PendingMessagesConverter());
	}

	@Override
	public List<StreamPending> xPending(final String key, final String groupName, final StreamEntryId start,
										final StreamEntryId end, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(groupName).add(start).add(end).add(count);
		return xPending(SafeEncoder.encode(key), SafeEncoder.encode(groupName),
				Range.create(start.toString(), end.toString()), Limit.from(count), args);
	}

	@Override
	public List<StreamPending> xPending(final byte[] key, final byte[] groupName, final StreamEntryId start,
										final StreamEntryId end, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(groupName).add(start).add(end).add(count);
		return xPending(key, groupName, Range.create(start.toString(), end.toString()), Limit.from(count), args);
	}

	@Override
	public List<StreamPending> xPending(final String key, final String groupName, final long minIdleTime,
										final StreamEntryId start, final StreamEntryId end, final int count) {
		return xPending(SafeEncoder.encode(key), SafeEncoder.encode(groupName), minIdleTime, start, end, count);
	}

	@Override
	public List<StreamPending> xPending(final byte[] key, final byte[] groupName, final long minIdleTime,
										final StreamEntryId start, final StreamEntryId end, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(groupName).add(Keyword.Time.IDLE, minIdleTime)
				.add(start).add(end).add(count);
		return xPending(key, groupName, Range.create(start.toString(), end.toString()), Limit.from(count), args);
	}

	@Override
	public List<StreamPending> xPending(final String key, final String groupName, final StreamEntryId start,
										final StreamEntryId end, final int count, final String consumerName) {
		return xPending(SafeEncoder.encode(key), SafeEncoder.encode(groupName), start, end, count,
				SafeEncoder.encode(consumerName));
	}

	@Override
	public List<StreamPending> xPending(final byte[] key, final byte[] groupName, final StreamEntryId start,
										final StreamEntryId end, final int count, final byte[] consumerName) {
		final CommandArguments args = CommandArguments.create(key).add(groupName).add(start).add(end).add(count)
				.add(consumerName);
		return xPending(key, Consumer.from(groupName, consumerName), Range.create(start.toString(), end.toString()),
				Limit.from(count), args);
	}

	@Override
	public List<StreamPending> xPending(final String key, final String groupName, final long minIdleTime,
										final StreamEntryId start, final StreamEntryId end, final int count,
										final String consumerName) {
		return xPending(SafeEncoder.encode(key), SafeEncoder.encode(groupName), minIdleTime, start, end, count,
				SafeEncoder.encode(consumerName));
	}

	@Override
	public List<StreamPending> xPending(final byte[] key, final byte[] groupName, final long minIdleTime,
										final StreamEntryId start, final StreamEntryId end, final int count,
										final byte[] consumerName) {
		final CommandArguments args = CommandArguments.create(key).add(groupName).add(Keyword.Time.IDLE, minIdleTime)
				.add(start).add(end).add(count).add(consumerName);
		return xPending(key, Consumer.from(groupName, consumerName), Range.create(start.toString(), end.toString()),
				Limit.from(count), args);
	}

	@Override
	public List<StreamEntry<String, String>> xRange(final String key, final StreamEntryId start,
													final StreamEntryId end) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end);
		return executeCommand(Command.XRANGE, args,
				(cmd)->cmd.xrange(SafeEncoder.encode(key), Range.create(start.toString(), end.toString())),
				new ListConverter<>(new StreamMessageConverter<>(SafeEncoder::encode, SafeEncoder::encode)));
	}

	@Override
	public List<StreamEntry<byte[], byte[]>> xRange(final byte[] key, final StreamEntryId start,
													final StreamEntryId end) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end);
		return executeCommand(Command.XRANGE, args,
				(cmd)->cmd.xrange(key, Range.create(start.toString(), end.toString())),
				new ListConverter<>(new StreamMessageConverter<>((k)->k, (v)->v)));
	}

	@Override
	public List<StreamEntry<String, String>> xRange(final String key, final StreamEntryId start,
													final StreamEntryId end, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end);
		return executeCommand(Command.XRANGE, args,
				(cmd)->cmd.xrange(SafeEncoder.encode(key), Range.create(start.toString(), end.toString()),
						Limit.from(count)),
				new ListConverter<>(new StreamMessageConverter<>(SafeEncoder::encode, SafeEncoder::encode)));
	}

	@Override
	public List<StreamEntry<byte[], byte[]>> xRange(final byte[] key, final StreamEntryId start,
													final StreamEntryId end, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end).add(Keyword.Common.COUNT, count);
		return executeCommand(Command.XRANGE, args,
				(cmd)->cmd.xrange(key, Range.create(start.toString(), end.toString()), Limit.from(count)),
				new ListConverter<>(new StreamMessageConverter<>((k)->k, (v)->v)));
	}

	@Override
	public List<XReadInfo<String, String>> xRead(final Map<String, StreamEntryId> streams) {
		final CommandArguments args = CommandArguments.create().add("STREAMS", streams);
		final XReadArgs.StreamOffset<byte[]>[] streamOffsets = new XReadArgs.StreamOffset[streams.size()];
		int i = 0;

		for(Map.Entry<String, StreamEntryId> entry : streams.entrySet()){
			streamOffsets[i++] = XReadArgs.StreamOffset.from(SafeEncoder.encode(entry.getKey()),
					entry.getValue().toString());
		}

		return executeCommand(Command.XREAD, args, (cmd)->cmd.xread(streamOffsets),
				new ListConverter<>(new StreamMessageKeyValueConverter<>()));
	}

	@Override
	public List<XReadInfo<String, String>> xRead(final Map<String, StreamEntryId> streams, final int count) {
		final CommandArguments args = CommandArguments.create(Keyword.Common.COUNT, count).add("STREAMS", streams);
		return xRead(streams, new LettuceXReadArgs(count), args);
	}

	@Override
	public List<XReadInfo<String, String>> xRead(final Map<String, StreamEntryId> streams,
												 final XReadArgument xReadArgument) {
		final CommandArguments args = CommandArguments.create(xReadArgument).add("STREAMS", streams);
		final XReadArgumentConverter xReadArgumentConverter = new XReadArgumentConverter();
		return xRead(streams, xReadArgumentConverter.convert(xReadArgument), args);
	}

	@Override
	public List<XReadInfo<String, String>> xRead(final Map<String, StreamEntryId> streams,
												 final XReadArgument xReadArgument, final int count) {
		final CommandArguments args = CommandArguments.create(Keyword.Common.COUNT, count).add(xReadArgument)
				.add("STREAMS", streams);
		final XReadArgumentConverter xReadArgumentConverter = new XReadArgumentConverter();
		final XReadArgs xReadArgs = Optional.ofNullable(xReadArgumentConverter.convert(xReadArgument))
				.orElse(new XReadArgs());

		xReadArgs.count(count);

		return xRead(streams, xReadArgs, args);
	}

	@Override
	public List<XReadGroupInfo<String, String>> xReadGroup(final String groupName, final String consumerName,
														   final Map<String, StreamEntryId> streams) {
		final CommandArguments args = CommandArguments.create(groupName).add(consumerName).add("STREAMS", streams);
		return xReadGroup(groupName, consumerName, streams, args);
	}

	@Override
	public List<XReadGroupInfo<byte[], byte[]>> xReadGroup(final byte[] groupName, final byte[] consumerName,
														   final Map<byte[], StreamEntryId> streams) {
		final CommandArguments args = CommandArguments.create(groupName).add(consumerName).add("STREAMS", streams);
		return xReadGroup(groupName, consumerName, streams, args);
	}

	@Override
	public List<XReadGroupInfo<String, String>> xReadGroup(final String groupName, final String consumerName,
														   final Map<String, StreamEntryId> streams, final int count) {
		final CommandArguments args = CommandArguments.create(groupName).add(consumerName)
				.add(Keyword.Common.COUNT, count).add("STREAMS", streams);
		return xReadGroup(groupName, consumerName, streams, new JedisXReadGroupParams(count), args);
	}

	@Override
	public List<XReadGroupInfo<byte[], byte[]>> xReadGroup(final byte[] groupName, final byte[] consumerName,
														   final Map<byte[], StreamEntryId> streams, final int count) {
		final CommandArguments args = CommandArguments.create(groupName).add(consumerName)
				.add(Keyword.Common.COUNT, count).add("STREAMS", streams);
		return xReadGroup(groupName, consumerName, streams, new JedisXReadGroupParams(count), args);
	}

	@Override
	public List<XReadGroupInfo<String, String>> xReadGroup(final String groupName, final String consumerName,
														   final XReadGroupArgument xReadGroupArgument,
														   final Map<String, StreamEntryId> streams) {
		final CommandArguments args = CommandArguments.create(groupName).add(consumerName)
				.add(xReadGroupArgument).add("STREAMS", streams);
		final XReadGroupArgumentConverter xReadGroupArgumentConverter = new XReadGroupArgumentConverter();
		return xReadGroup(groupName, consumerName, streams, xReadGroupArgumentConverter.convert(xReadGroupArgument),
				args);
	}

	@Override
	public List<XReadGroupInfo<byte[], byte[]>> xReadGroup(final byte[] groupName, final byte[] consumerName,
														   final XReadGroupArgument xReadGroupArgument,
														   final Map<byte[], StreamEntryId> streams) {
		final CommandArguments args = CommandArguments.create(groupName).add(consumerName)
				.add(xReadGroupArgument).add("STREAMS", streams);
		final XReadGroupArgumentConverter xReadGroupArgumentConverter = new XReadGroupArgumentConverter();
		return xReadGroup(groupName, consumerName, streams, xReadGroupArgumentConverter.convert(xReadGroupArgument),
				args);
	}

	@Override
	public List<XReadGroupInfo<String, String>> xReadGroup(final String groupName, final String consumerName,
														   final XReadGroupArgument xReadGroupArgument,
														   final Map<String, StreamEntryId> streams, final int count) {
		final CommandArguments args = CommandArguments.create(groupName).add(consumerName)
				.add(Keyword.Common.COUNT, count).add(xReadGroupArgument).add("STREAMS", streams);
		final XReadGroupArgumentConverter xReadGroupArgumentConverter = new XReadGroupArgumentConverter();
		final XReadGroupParams xReadGroupParams =
				Optional.ofNullable(xReadGroupArgumentConverter.convert(xReadGroupArgument))
						.orElse(new XReadGroupParams());

		xReadGroupParams.count(count);

		return xReadGroup(groupName, consumerName, streams, xReadGroupParams, args);
	}

	@Override
	public List<XReadGroupInfo<byte[], byte[]>> xReadGroup(final byte[] groupName, final byte[] consumerName,
														   final XReadGroupArgument xReadGroupArgument,
														   final Map<byte[], StreamEntryId> streams, final int count) {
		final CommandArguments args = CommandArguments.create(groupName).add(consumerName)
				.add(Keyword.Common.COUNT, count).add(xReadGroupArgument).add("STREAMS", streams);
		final XReadGroupArgumentConverter xReadGroupArgumentConverter = new XReadGroupArgumentConverter();
		final XReadGroupParams xReadGroupParams =
				Optional.ofNullable(xReadGroupArgumentConverter.convert(xReadGroupArgument))
						.orElse(new XReadGroupParams());

		xReadGroupParams.count(count);

		return xReadGroup(groupName, consumerName, streams, xReadGroupParams, args);
	}

	@Override
	public List<StreamEntry> xRevRange(final String key, final StreamEntryId end, final StreamEntryId start) {
		return xRevRange(SafeEncoder.encode(key), end, start);
	}

	@Override
	public List<StreamEntry> xRevRange(final byte[] key, final StreamEntryId end, final StreamEntryId start) {
		final CommandArguments args = CommandArguments.create(key).add(end).add(start);
		final Range<String> range = Range.create(start.toString(), end.toString());
		final ListConverter<StreamMessage<byte[], byte[]>, StreamEntry> listStreamMessageConverter = StreamMessageConverter.listConverter();

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.XREVRANGE, (cmd)->cmd.xrevrange(key, range),
					listStreamMessageConverter).run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.XREVRANGE, (cmd)->cmd.xrevrange(key, range),
					listStreamMessageConverter).run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.XREVRANGE, (cmd)->cmd.xrevrange(key, range),
					listStreamMessageConverter).run(args);
		}
	}

	@Override
	public List<StreamEntry> xRevRange(final byte[] key, final StreamEntryId end, final StreamEntryId start,
									   final long count) {
		final CommandArguments args = CommandArguments.create(key).add(end).add(start).add(count);
		final Range<String> range = Range.create(start.toString(), end.toString());
		final Limit limit = Limit.from(count);
		final ListConverter<StreamMessage<byte[], byte[]>, StreamEntry> listStreamMessageConverter = StreamMessageConverter.listConverter();

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.XREVRANGE,
					(cmd)->cmd.xrevrange(key, range, limit), listStreamMessageConverter).run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.XREVRANGE,
					(cmd)->cmd.xrevrange(key, range, limit), listStreamMessageConverter).run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.XREVRANGE, (cmd)->cmd.xrevrange(key, range, limit),
					listStreamMessageConverter).run(args);
		}
	}

	@Override
	public Long xTrim(final byte[] key, final XTrimArgument xTrimArgument) {
		final CommandArguments args = CommandArguments.create(key).add(xTrimArgument);
		return xTrim(key, xTrimArgument.isApproximateTrimming(), Long.MAX_VALUE, args);
	}

	@Override
	public Long xTrim(final byte[] key, final XTrimArgument xTrimArgument, final long limit) {
		final CommandArguments args = CommandArguments.create(key).add(xTrimArgument).add(limit);
		return xTrim(key, xTrimArgument.isApproximateTrimming(), limit, args);
	}

	private StreamEntryId xAdd(final String key, final Map<String, String> hash, final XAddArgs xAddArgs,
							   final CommandArguments args) {
		final MapConverter<String, String, byte[], byte[]> mapConverter = new MapConverter<>(SafeEncoder::encode,
				SafeEncoder::encode);
		return executeCommand(Command.XADD, args,
				(cmd)->cmd.xadd(SafeEncoder.encode(key), xAddArgs, mapConverter.convert(hash)),
				new StreamEntryIDConverter());
	}

	private StreamEntryId xAdd(final byte[] key, final Map<byte[], byte[]> hash, final XAddArgs xAddArgs,
							   final CommandArguments args) {
		return executeCommand(Command.XADD, args, (cmd)->cmd.xadd(key, xAddArgs, hash), new StreamEntryIDConverter());
	}

	private KeyValue<StreamEntryId, List<StreamEntry<String, String>>> xAutoClaim(final String key,
																				  final LettuceXAutoClaimArgs<byte[]> xAutoClaimArgs,
																				  final CommandArguments args) {
		return executeCommand(Command.XAUTOCLAIM, args, (cmd)->cmd.xautoclaim(SafeEncoder.encode(key), xAutoClaimArgs),
				new ClaimedMessagesKeyValueConverter<>(
						new StreamMessageConverter<>(SafeEncoder::encode, SafeEncoder::encode)));
	}

	private KeyValue<StreamEntryId, List<StreamEntry<byte[], byte[]>>> xAutoClaim(final byte[] key,
																				  final LettuceXAutoClaimArgs<byte[]> xAutoClaimArgs,
																				  final CommandArguments args) {
		return executeCommand(Command.XAUTOCLAIM, args, (cmd)->cmd.xautoclaim(key, xAutoClaimArgs),
				new ClaimedMessagesKeyValueConverter<>(new StreamMessageConverter<>((k)->k, (v)->v)));
	}

	private KeyValue<StreamEntryId, List<StreamEntryId>> xAutoClaimJustId(final byte[] key,
																		  final LettuceXAutoClaimArgs<byte[]> xAutoClaimArgs,
																		  final CommandArguments args) {
		xAutoClaimArgs.justid();
		return executeCommand(Command.XAUTOCLAIM, args, (cmd)->cmd.xautoclaim(key, xAutoClaimArgs),
				new ClaimedMessagesKeyValueConverter<>(
						new StreamMessageConverter.StreamMessageStreamEntryIdConverter()));
	}

	private List<StreamEntry<String, String>> xClaim(final String key, final StreamEntryId[] ids,
													 final Consumer<byte[]> consumer, final int minIdleTime,
													 final XClaimArgs xClaimArgs, final CommandArguments args) {
		final ArrayConverter<StreamEntryId, String> arrayConverter = new ArrayConverter<>(StreamEntryId::toString,
				String.class);
		xClaimArgs.minIdleTime(minIdleTime);
		return executeCommand(Command.XCLAIM, args,
				(cmd)->cmd.xclaim(SafeEncoder.encode(key), consumer, xClaimArgs, arrayConverter.convert(ids)),
				new ListConverter<>(new StreamMessageConverter<>(SafeEncoder::encode, SafeEncoder::encode)));
	}

	private List<StreamEntry<byte[], byte[]>> xClaim(final byte[] key, final StreamEntryId[] ids,
													 final Consumer<byte[]> consumer, final int minIdleTime,
													 final XClaimArgs xClaimArgs, final CommandArguments args) {
		final ArrayConverter<StreamEntryId, String> arrayConverter = new ArrayConverter<>(StreamEntryId::toString,
				String.class);
		xClaimArgs.minIdleTime(minIdleTime);
		return executeCommand(Command.XCLAIM, args,
				(cmd)->cmd.xclaim(key, consumer, xClaimArgs, arrayConverter.convert(ids)),
				new ListConverter<>(new StreamMessageConverter<>((k)->k, (v)->v)));
	}

	private List<StreamEntryId> xClaimJustId(final byte[] key, final StreamEntryId[] ids,
											 final Consumer<byte[]> consumer, final int minIdleTime,
											 final XClaimArgs xClaimArgs, final CommandArguments args) {
		final ArrayConverter<StreamEntryId, String> arrayConverter = new ArrayConverter<>(StreamEntryId::toString,
				String.class);
		xClaimArgs.minIdleTime(minIdleTime).justid();
		return executeCommand(Command.XCLAIM, args,
				(cmd)->cmd.xclaim(key, consumer, xClaimArgs, arrayConverter.convert(ids)),
				new ListConverter<>(new StreamMessageConverter.StreamMessageStreamEntryIdConverter()));
	}

	private Status xGroupCreate(final byte[] key, final byte[] groupName, final StreamEntryId id,
								final LettuceXGroupCreateArgs xGroupCreateArgs, final CommandArguments args) {
		return executeCommand(Command.XGROUP, SubCommand.XGROUP_CREATE, args,
				(cmd)->cmd.xgroupCreate(XReadArgs.StreamOffset.from(key, id.toString()), groupName, xGroupCreateArgs),
				new OkStatusConverter());
	}

	private List<StreamPending> xPending(final byte[] key, final byte[] groupName, Range<String> range,
										 final Limit limit, final CommandArguments args) {
		return executeCommand(Command.XPENDING, args, (cmd)->cmd.xpending(key, groupName, range, limit),
				new ListConverter<>(new PendingMessageConverter()));
	}

	private List<StreamPending> xPending(final byte[] key, final Consumer<byte[]> consumer, Range<String> range,
										 final Limit limit, final CommandArguments args) {
		return executeCommand(Command.XPENDING, args, (cmd)->cmd.xpending(key, consumer, range, limit),
				new ListConverter<>(new PendingMessageConverter()));
	}

	private List<XReadInfo<String, String>> xRead(final Map<String, StreamEntryId> streams, final XReadArgs xReadArgs,
												  final CommandArguments args) {
		final XReadArgs.StreamOffset<byte[]>[] streamOffsets = new XReadArgs.StreamOffset[streams.size()];
		int i = 0;

		for(Map.Entry<String, StreamEntryId> entry : streams.entrySet()){
			streamOffsets[i++] = XReadArgs.StreamOffset.from(SafeEncoder.encode(entry.getKey()),
					entry.getValue().toString());
		}

		return executeCommand(Command.XREAD, args, (cmd)->cmd.xread(xReadArgs, streamOffsets),
				new ListConverter<>(new StreamMessageStreamEntryXReadInfoConverter<>()));
	}

	private List<KeyValue<String, List<StreamEntry<String, String>>>> xReadGroup(final String groupName,
																				 final String consumerName,
																				 final Map<String, StreamEntryId> streams,
																				 final CommandArguments args) {
		final XReadArgs.StreamOffset<byte[]>[] streamOffsets = new XReadArgs.StreamOffset[streams.size()];
		int i = 0;

		for(Map.Entry<String, StreamEntryId> entry : streams.entrySet()){
			streamOffsets[i++] = XReadArgs.StreamOffset.from(SafeEncoder.encode(entry.getKey()),
					entry.getValue().toString());
		}

		final ListConverter<StreamMessage<byte[], byte[]>, Map<String, List<StreamEntry>>> listStreamMessageMapConverter = StreamMessageKeyValueConverter.listConverter();

		return xReadGroup(SafeEncoder.encode(groupName), SafeEncoder.encode(consumerName), streamOffsets,
				listStreamMessageMapConverter, args);
	}

	private List<KeyValue<byte[], List<StreamEntry<byte[], byte[]>>>> xReadGroup(final byte[] groupName,
																				 final byte[] consumerName,
																				 final Map<byte[], StreamEntryId> streams,
																				 final CommandArguments args) {
		final XReadArgs.StreamOffset<byte[]>[] streamOffsets = new XReadArgs.StreamOffset[streams.size()];
		int i = 0;

		for(Map.Entry<byte[], StreamEntryId> entry : streams.entrySet()){
			streamOffsets[i++] = XReadArgs.StreamOffset.from(entry.getKey(), entry.getValue().toString());
		}

		return executeCommand(Command.XREADGROUP, args,
				(cmd)->cmd.xreadgroup(Consumer.from(groupName, consumerName), streamOffsets),
				new ListConverter<>(new StreamMessageKeyValueConverter<>()));
	}

	private <K> List<Map<K, List<StreamEntry>>> xReadGroup(final byte[] groupName, final byte[] consumerName,
														   final XReadArgs.StreamOffset<byte[]>[] streamOffsets,
														   final Converter<List<StreamMessage<byte[], byte[]>>, List<Map<K, List<StreamEntry>>>> converter,
														   final CommandArguments args) {
		final Consumer<byte[]> consumer = Consumer.from(groupName, consumerName);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.XREADGROUP,
					(cmd)->cmd.xreadgroup(consumer, streamOffsets), converter).run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.XREADGROUP,
					(cmd)->cmd.xreadgroup(consumer, streamOffsets), converter).run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.XREADGROUP,
					(cmd)->cmd.xreadgroup(consumer, streamOffsets), converter).run(args);
		}
	}

	private List<Map<String, List<StreamEntry>>> xReadGroup(final String groupName, final String consumerName,
															final Map<String, StreamEntryId> streams,
															final XReadArgs xReadArgs, final CommandArguments args) {
		final XReadArgs.StreamOffset<byte[]>[] streamOffsets = createStreamOffsetFromStreamEntryIdMap(streams);
		final ListConverter<StreamMessage<byte[], byte[]>, Map<String, List<StreamEntry>>> listStreamMessageMapConverter = StreamMessageKeyValueConverter.listConverter();

		return xReadGroup(SafeEncoder.encode(groupName), SafeEncoder.encode(consumerName), streamOffsets, xReadArgs,
				listStreamMessageMapConverter, args);
	}

	private List<Map<byte[], List<StreamEntry>>> xReadGroup(final byte[] groupName, final byte[] consumerName,
															final Map<byte[], StreamEntryId> streams,
															final XReadArgs xReadArgs, final CommandArguments args) {
		final XReadArgs.StreamOffset<byte[]>[] streamOffsets = createStreamOffsetFromBinaryStreamEntryIdMap(streams);
		final ListConverter<StreamMessage<byte[], byte[]>, Map<byte[], List<StreamEntry>>> listStreamMessageMapConverter = StreamMessageKeyValueConverter.listConverter();

		return xReadGroup(groupName, consumerName, streamOffsets, xReadArgs, listStreamMessageMapConverter, args);
	}

	private <K> List<Map<K, List<StreamEntry>>> xReadGroup(final byte[] groupName, final byte[] consumerName,
														   final XReadArgs.StreamOffset<byte[]>[] streamOffsets,
														   final XReadArgs xReadArgs,
														   final Converter<List<StreamMessage<byte[], byte[]>>, List<Map<K, List<StreamEntry>>>> converter,
														   final CommandArguments args) {
		final Consumer<byte[]> consumer = Consumer.from(groupName, consumerName);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.XREADGROUP,
					(cmd)->cmd.xreadgroup(consumer, xReadArgs, streamOffsets), converter).run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.XREADGROUP,
					(cmd)->cmd.xreadgroup(consumer, xReadArgs, streamOffsets), converter).run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.XREADGROUP,
					(cmd)->cmd.xreadgroup(consumer, xReadArgs, streamOffsets), converter).run(args);
		}
	}

	private Long xTrim(final byte[] key, final boolean approximateTrimming, final long limit,
					   final CommandArguments args) {

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.XTRIM,
					(cmd)->cmd.xtrim(key, approximateTrimming, limit), (v)->v).run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.XTRIM,
					(cmd)->cmd.xtrim(key, approximateTrimming, limit), (v)->v).run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.XTRIM,
					(cmd)->cmd.xtrim(key, approximateTrimming, limit), (v)->v).run(args);
		}
	}

}
