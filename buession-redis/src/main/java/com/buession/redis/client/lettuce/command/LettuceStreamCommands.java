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

import com.buession.core.collect.Arrays;
import com.buession.core.converter.BooleanStatusConverter;
import com.buession.core.converter.ListConverter;
import com.buession.core.converter.MapConverter;
import com.buession.lang.Status;
import com.buession.redis.client.lettuce.LettuceRedisClient;
import com.buession.redis.core.ApproximateExactTrimming;
import com.buession.redis.core.AutoClaimId;
import com.buession.redis.core.AutoClaimInfo;
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
import com.buession.redis.core.command.StreamCommands;
import com.buession.redis.core.command.SubCommand;
import com.buession.redis.core.command.args.MaxLenMinId;
import com.buession.redis.core.command.args.XAddArgument;
import com.buession.redis.core.command.args.XClaimArgument;
import com.buession.redis.core.command.args.XReadGroupArgument;
import com.buession.redis.core.internal.convert.StringMapBinaryMapConverter;
import com.buession.redis.core.internal.convert.lettuce.params.StreamDeletionPolicyConverter;
import com.buession.redis.core.internal.convert.lettuce.response.ClaimedMessagesAutoClaimIdConverter;
import com.buession.redis.core.internal.convert.lettuce.response.ClaimedMessagesAutoClaimInfoConverter;
import com.buession.redis.core.internal.convert.lettuce.response.PendingMessageConverter;
import com.buession.redis.core.internal.convert.lettuce.response.PendingMessagesConverter;
import com.buession.redis.core.internal.convert.lettuce.response.StreamConsumersInfoConverter;
import com.buession.redis.core.internal.convert.lettuce.response.StreamEntryDeletionResultConverter;
import com.buession.redis.core.internal.convert.lettuce.response.StreamEntryIDConverter;
import com.buession.redis.core.internal.convert.lettuce.response.StreamFullInfoConverter;
import com.buession.redis.core.internal.convert.lettuce.response.StreamGroupInfoConverter;
import com.buession.redis.core.internal.convert.lettuce.response.StreamInfoConverter;
import com.buession.redis.core.internal.convert.lettuce.response.StreamMessageConverter;
import com.buession.redis.core.internal.convert.lettuce.response.StreamMessageXReadGroupInfoConverter;
import com.buession.redis.core.internal.convert.lettuce.response.StreamMessageXReadInfoConverter;
import com.buession.redis.core.internal.convert.response.OkStatusConverter;
import com.buession.redis.core.internal.lettuce.CompositeArgumentUtils;
import com.buession.redis.core.internal.lettuce.args.LettuceXAddArgs;
import com.buession.redis.core.internal.lettuce.args.LettuceXAutoClaimArgs;
import com.buession.redis.core.internal.lettuce.args.LettuceXClaimArgs;
import com.buession.redis.core.internal.lettuce.args.LettuceXGroupCreateArgs;
import com.buession.redis.core.internal.lettuce.args.LettuceXPendingArgs;
import com.buession.redis.core.internal.lettuce.args.LettuceXReadArgs;
import com.buession.redis.core.internal.lettuce.args.LettuceXReadGroupArgs;
import com.buession.redis.core.internal.lettuce.args.LettuceXTrimArgs;
import com.buession.redis.utils.SafeEncoder;
import io.lettuce.core.Consumer;
import io.lettuce.core.Limit;
import io.lettuce.core.Range;
import io.lettuce.core.XAddArgs;
import io.lettuce.core.XAutoClaimArgs;
import io.lettuce.core.XClaimArgs;
import io.lettuce.core.XPendingArgs;
import io.lettuce.core.XReadArgs;
import io.lettuce.core.XTrimArgs;

import java.util.List;
import java.util.Map;

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
		final CommandArguments args = CommandArguments.create(key, groupName).add(ids);
		return xAck(rawBinaryKey(key), SafeEncoder.encode(groupName), ids, args);
	}

	@Override
	public Long xAck(final byte[] key, final byte[] groupName, final StreamEntryId... ids) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(ids);
		return xAck(rawKey(key), groupName, ids, args);
	}

	@Override
	public List<StreamEntryDeletionResult> xAckDel(final String key, final String groupName,
												   final StreamEntryId... ids) {
		final CommandArguments args = CommandArguments.create(key, groupName).add("IDS", ids.length).add(ids);
		return xAckDel(rawBinaryKey(key), SafeEncoder.encode(groupName), ids, args);
	}

	@Override
	public List<StreamEntryDeletionResult> xAckDel(final byte[] key, final byte[] groupName,
												   final StreamEntryId... ids) {
		final CommandArguments args = CommandArguments.create(key, groupName).add("IDS", ids.length).add(ids);
		return xAckDel(rawKey(key), groupName, ids, args);
	}

	@Override
	public List<StreamEntryDeletionResult> xAckDel(final String key, final String groupName,
												   final StreamDeletionPolicy deletionPolicy,
												   final StreamEntryId... ids) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(deletionPolicy).add("IDS", ids.length)
				.add(ids);
		return xAckDel(rawBinaryKey(key), SafeEncoder.encode(groupName), deletionPolicy, ids, args);
	}

	@Override
	public List<StreamEntryDeletionResult> xAckDel(final byte[] key, final byte[] groupName,
												   final StreamDeletionPolicy deletionPolicy,
												   final StreamEntryId... ids) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(deletionPolicy).add("IDS", ids.length)
				.add(ids);
		return xAckDel(rawKey(key), groupName, deletionPolicy, ids, args);
	}

	@Override
	public StreamEntryId xAdd(final String key, final StreamEntryId id, final Map<String, String> hash) {
		final CommandArguments args = CommandArguments.create(key).add(id).add(hash);
		return stringXAdd(rawBinaryKey(key), hash, new LettuceXAddArgs(id), args);
	}

	@Override
	public StreamEntryId xAdd(final byte[] key, final StreamEntryId id, final Map<byte[], byte[]> hash) {
		final CommandArguments args = CommandArguments.create(key).add(id).add(hash);
		return binaryXAdd(rawKey(key), hash, new LettuceXAddArgs(id), args);
	}

	@Override
	public StreamEntryId xAdd(final String key, final StreamEntryId id, final Map<String, String> hash,
							  final XAddArgument xAddArgument) {
		final CommandArguments args = CommandArguments.create(key).add(xAddArgument).add(id).add(hash);
		return stringXAdd(rawBinaryKey(key), hash, new LettuceXAddArgs(xAddArgument, id), args);
	}

	@Override
	public StreamEntryId xAdd(final byte[] key, final StreamEntryId id, final Map<byte[], byte[]> hash,
							  final XAddArgument xAddArgument) {
		final CommandArguments args = CommandArguments.create(key).add(xAddArgument).add(id).add(hash);
		return binaryXAdd(rawKey(key), hash, new LettuceXAddArgs(xAddArgument, id), args);
	}

	@Override
	public AutoClaimInfo<String, String> xAutoClaim(final String key, final String groupName, final String consumerName,
													final int minIdleTime, final StreamEntryId start) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(consumerName).add(minIdleTime)
				.add(start);
		return stringXAutoClaim(rawBinaryKey(key),
				new LettuceXAutoClaimArgs<>(SafeEncoder.encode(groupName), SafeEncoder.encode(consumerName),
						minIdleTime, start), args);
	}

	@Override
	public AutoClaimInfo<byte[], byte[]> xAutoClaim(final byte[] key, final byte[] groupName, final byte[] consumerName,
													final int minIdleTime, final StreamEntryId start) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(consumerName).add(minIdleTime)
				.add(start);
		return binaryXAutoClaim(rawKey(key), new LettuceXAutoClaimArgs<>(groupName, consumerName, minIdleTime, start),
				args);
	}

	@Override
	public AutoClaimInfo<String, String> xAutoClaim(final String key, final String groupName, final String consumerName,
													final int minIdleTime, final StreamEntryId start, final int count) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(consumerName).add(minIdleTime)
				.add(start).add(Keyword.Common.COUNT, count);
		return stringXAutoClaim(rawBinaryKey(key),
				new LettuceXAutoClaimArgs<>(SafeEncoder.encode(groupName), SafeEncoder.encode(consumerName),
						minIdleTime, start, count), args);
	}

	@Override
	public AutoClaimInfo<byte[], byte[]> xAutoClaim(final byte[] key, final byte[] groupName, final byte[] consumerName,
													final int minIdleTime, final StreamEntryId start, final int count) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(consumerName).add(minIdleTime)
				.add(start).add(Keyword.Common.COUNT, count);
		return binaryXAutoClaim(rawKey(key),
				new LettuceXAutoClaimArgs<>(groupName, consumerName, minIdleTime, start, count), args);
	}

	@Override
	public AutoClaimId xAutoClaimJustId(final String key, final String groupName, final String consumerName,
										final int minIdleTime, final StreamEntryId start) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(consumerName).add(minIdleTime)
				.add(start).add("JUSTID");
		return xAutoClaimJustId(rawBinaryKey(key),
				new LettuceXAutoClaimArgs<>(SafeEncoder.encode(groupName), SafeEncoder.encode(consumerName),
						minIdleTime, start), args);
	}

	@Override
	public AutoClaimId xAutoClaimJustId(final byte[] key, final byte[] groupName, final byte[] consumerName,
										final int minIdleTime, final StreamEntryId start) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(consumerName).add(minIdleTime)
				.add(start).add("JUSTID");
		return xAutoClaimJustId(rawKey(key), new LettuceXAutoClaimArgs<>(groupName, consumerName, minIdleTime, start),
				args);
	}

	@Override
	public AutoClaimId xAutoClaimJustId(final String key, final String groupName, final String consumerName,
										final int minIdleTime, final StreamEntryId start, final int count) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(consumerName).add(minIdleTime)
				.add(start).add(Keyword.Common.COUNT, count).add("JUSTID");
		return xAutoClaimJustId(rawBinaryKey(key),
				new LettuceXAutoClaimArgs<>(SafeEncoder.encode(groupName), SafeEncoder.encode(consumerName),
						minIdleTime, start, count), args);
	}

	@Override
	public AutoClaimId xAutoClaimJustId(final byte[] key, final byte[] groupName, final byte[] consumerName,
										final int minIdleTime, final StreamEntryId start, final int count) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(consumerName).add(minIdleTime)
				.add(start).add(Keyword.Common.COUNT, count).add("JUSTID");
		return xAutoClaimJustId(rawKey(key),
				new LettuceXAutoClaimArgs<>(groupName, consumerName, minIdleTime, start, count), args);
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
	public List<StreamEntry<String, String>> xClaim(final String key, final String groupName, final String consumerName,
													final int minIdleTime, final StreamEntryId... ids) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(consumerName).add(minIdleTime)
				.add(ids);
		return xClaim(rawBinaryKey(key), groupName, consumerName, ids, minIdleTime, new XClaimArgs(), args);
	}

	@Override
	public List<StreamEntry<byte[], byte[]>> xClaim(final byte[] key, final byte[] groupName, final byte[] consumerName,
													final int minIdleTime, final StreamEntryId... ids) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(consumerName).add(minIdleTime)
				.add(ids);
		return xClaim(rawKey(key), groupName, consumerName, ids, minIdleTime, new XClaimArgs(), args);
	}

	@Override
	public List<StreamEntry<String, String>> xClaim(final String key, final String groupName, final String consumerName,
													final int minIdleTime, final StreamEntryId[] ids,
													final XClaimArgument xClaimArgument) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(consumerName).add(minIdleTime)
				.add(ids).add(xClaimArgument);
		return xClaim(rawBinaryKey(key), groupName, consumerName, ids, minIdleTime,
				new LettuceXClaimArgs(xClaimArgument), args);
	}

	@Override
	public List<StreamEntry<byte[], byte[]>> xClaim(final byte[] key, final byte[] groupName, final byte[] consumerName,
													final int minIdleTime, final StreamEntryId[] ids,
													final XClaimArgument xClaimArgument) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(consumerName).add(minIdleTime)
				.add(ids).add(xClaimArgument);
		return xClaim(rawKey(key), groupName, consumerName, ids, minIdleTime, new LettuceXClaimArgs(xClaimArgument),
				args);
	}

	@Override
	public List<StreamEntryId> xClaimJustId(final String key, final String groupName, final String consumerName,
											final int minIdleTime, final StreamEntryId... ids) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(consumerName).add(minIdleTime)
				.add(ids).add("JUSTID");
		return xClaimJustId(rawBinaryKey(key), groupName, consumerName, ids, minIdleTime, new XClaimArgs(), args);
	}

	@Override
	public List<StreamEntryId> xClaimJustId(final byte[] key, final byte[] groupName, final byte[] consumerName,
											final int minIdleTime, final StreamEntryId... ids) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(consumerName).add(minIdleTime)
				.add(ids).add("JUSTID");
		return xClaimJustId(rawKey(key), groupName, consumerName, ids, minIdleTime, new XClaimArgs(), args);
	}

	@Override
	public List<StreamEntryId> xClaimJustId(final String key, final String groupName, final String consumerName,
											final int minIdleTime, final StreamEntryId[] ids,
											final XClaimArgument xClaimArgument) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(consumerName).add(minIdleTime)
				.add(ids).add(xClaimArgument).add("JUSTID");
		return xClaimJustId(rawBinaryKey(key), groupName, consumerName, ids, minIdleTime,
				new LettuceXClaimArgs(xClaimArgument), args);
	}

	@Override
	public List<StreamEntryId> xClaimJustId(final byte[] key, final byte[] groupName, final byte[] consumerName,
											final int minIdleTime, final StreamEntryId[] ids,
											final XClaimArgument xClaimArgument) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(consumerName).add(minIdleTime)
				.add(ids).add(xClaimArgument).add("JUSTID");
		return xClaimJustId(rawKey(key), groupName, consumerName, ids, minIdleTime,
				new LettuceXClaimArgs(xClaimArgument), args);
	}

	@Override
	public Long xDel(final String key, final StreamEntryId... ids) {
		final CommandArguments args = CommandArguments.create(key).add(ids);
		return executeCommand(Command.XDEL, args, (cmd)->cmd.xdel(rawBinaryKey(key), messageIds(ids)));
	}

	@Override
	public Long xDel(final byte[] key, final StreamEntryId... ids) {
		final CommandArguments args = CommandArguments.create(key).add(ids);
		return executeCommand(Command.XDEL, args, (cmd)->cmd.xdel(rawKey(key), messageIds(ids)));
	}

	@Override
	public List<StreamEntryDeletionResult> xDelEx(final String key, final StreamDeletionPolicy deletionPolicy,
												  final StreamEntryId... ids) {
		final CommandArguments args = CommandArguments.create(key).add(deletionPolicy).add("IDS", ids.length).add(ids);
		return xDelEx(rawBinaryKey(key), ids, deletionPolicy, args);
	}

	@Override
	public List<StreamEntryDeletionResult> xDelEx(final byte[] key, final StreamDeletionPolicy deletionPolicy,
												  final StreamEntryId... ids) {
		final CommandArguments args = CommandArguments.create(key).add(deletionPolicy).add("IDS", ids.length).add(ids);
		return xDelEx(rawKey(key), ids, deletionPolicy, args);
	}

	@Override
	public Status xGroupCreate(final String key, final String groupName, final StreamEntryId id) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(id);
		return xGroupCreate(rawBinaryKey(key), SafeEncoder.encode(groupName), id, args);
	}

	@Override
	public Status xGroupCreate(final byte[] key, final byte[] groupName, final StreamEntryId id) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(id);
		return xGroupCreate(rawKey(key), groupName, id, args);
	}

	@Override
	public Status xGroupCreate(final String key, final String groupName, final StreamEntryId id,
							   final boolean makeStream) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(id)
				.add(makeStream ? "MKSTREAM" : null);
		return xGroupCreate(rawBinaryKey(key), SafeEncoder.encode(groupName), id,
				new LettuceXGroupCreateArgs(makeStream), args);
	}

	@Override
	public Status xGroupCreate(final byte[] key, final byte[] groupName, final StreamEntryId id,
							   final boolean makeStream) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(id)
				.add(makeStream ? "MKSTREAM" : null);
		return xGroupCreate(rawKey(key), groupName, id, new LettuceXGroupCreateArgs(makeStream), args);
	}

	@Override
	public Status xGroupCreate(final String key, final String groupName, final StreamEntryId id,
							   final boolean makeStream, final long entriesRead) {
		final CommandArguments args = CommandArguments.create(key).add(groupName).add(id)
				.add(makeStream ? "MKSTREAM" : null).add("ENTRIESREAD", entriesRead);
		return xGroupCreate(rawBinaryKey(key), SafeEncoder.encode(groupName), id,
				new LettuceXGroupCreateArgs(makeStream, entriesRead), args);
	}

	@Override
	public Status xGroupCreate(final byte[] key, final byte[] groupName, final StreamEntryId id,
							   final boolean makeStream, final long entriesRead) {
		final CommandArguments args = CommandArguments.create(key).add(groupName).add(id)
				.add(makeStream ? "MKSTREAM" : null).add("ENTRIESREAD", entriesRead);
		return xGroupCreate(rawKey(key), groupName, id, new LettuceXGroupCreateArgs(makeStream, entriesRead), args);
	}

	@Override
	public Status xGroupCreate(final String key, final String groupName, final StreamEntryId id,
							   final long entriesRead) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(id).add("ENTRIESREAD", entriesRead);
		return xGroupCreate(rawBinaryKey(key), SafeEncoder.encode(groupName), id,
				new LettuceXGroupCreateArgs(entriesRead), args);
	}

	@Override
	public Status xGroupCreate(final byte[] key, final byte[] groupName, final StreamEntryId id,
							   final long entriesRead) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(id).add("ENTRIESREAD", entriesRead);
		return xGroupCreate(rawKey(key), groupName, id, new LettuceXGroupCreateArgs(entriesRead), args);
	}

	@Override
	public Status xGroupCreateConsumer(final String key, final String groupName, final String consumerName) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(consumerName);
		return xGroupCreateConsumer(rawBinaryKey(key), SafeEncoder.encode(groupName), SafeEncoder.encode(consumerName),
				args);
	}

	@Override
	public Status xGroupCreateConsumer(final byte[] key, final byte[] groupName, final byte[] consumerName) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(consumerName);
		return xGroupCreateConsumer(rawKey(key), groupName, consumerName, args);
	}

	@Override
	public Long xGroupDelConsumer(final String key, final String groupName, final String consumerName) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(consumerName);
		return xGroupDelConsumer(rawBinaryKey(key), SafeEncoder.encode(groupName), SafeEncoder.encode(consumerName),
				args);
	}

	@Override
	public Long xGroupDelConsumer(final byte[] key, final byte[] groupName, final byte[] consumerName) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(consumerName);
		return xGroupDelConsumer(rawKey(key), groupName, consumerName, args);
	}

	@Override
	public Status xGroupDestroy(final String key, final String groupName) {
		final CommandArguments args = CommandArguments.create(key, groupName);
		return executeCommand(Command.XGROUP, SubCommand.XGROUP_DESTROY, args,
				(cmd)->cmd.xgroupDestroy(rawBinaryKey(key), SafeEncoder.encode(groupName)),
				new BooleanStatusConverter());
	}

	@Override
	public Status xGroupDestroy(final byte[] key, final byte[] groupName) {
		final CommandArguments args = CommandArguments.create(key, groupName);
		return executeCommand(Command.XGROUP, SubCommand.XGROUP_DESTROY, args,
				(cmd)->cmd.xgroupDestroy(rawKey(key), groupName), new BooleanStatusConverter());
	}

	@Override
	public Status xGroupSetId(final String key, final String groupName, final StreamEntryId id) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(id);
		return xGroupSetId(rawBinaryKey(key), SafeEncoder.encode(groupName), id, args);
	}

	@Override
	public Status xGroupSetId(final byte[] key, final byte[] groupName, final StreamEntryId id) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(id);
		return xGroupSetId(rawKey(key), groupName, id, args);
	}

	@Override
	public Status xGroupSetId(final String key, final String groupName, final StreamEntryId id,
							  final long entriesRead) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(id).add("ENTRIESREAD", entriesRead);
		return xGroupSetId(rawBinaryKey(key), SafeEncoder.encode(groupName), id, args);
	}

	@Override
	public Status xGroupSetId(final byte[] key, final byte[] groupName, final StreamEntryId id,
							  final long entriesRead) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(id).add("ENTRIESREAD", entriesRead);
		return xGroupSetId(rawKey(key), groupName, id, args);
	}

	@Override
	public List<StreamConsumer> xInfoConsumers(final String key, final String groupName) {
		final CommandArguments args = CommandArguments.create(key, groupName);
		return xInfoConsumers(rawBinaryKey(key), SafeEncoder.encode(groupName), args);
	}

	@Override
	public List<StreamConsumer> xInfoConsumers(final byte[] key, final byte[] groupName) {
		final CommandArguments args = CommandArguments.create(key, groupName);
		return xInfoConsumers(rawKey(key), groupName, args);
	}

	@Override
	public List<StreamGroup> xInfoGroups(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return xInfoGroups(rawBinaryKey(key), args);
	}

	@Override
	public List<StreamGroup> xInfoGroups(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return xInfoGroups(rawKey(key), args);
	}

	@Override
	public Stream<String, String> xInfoStream(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.XINFO, SubCommand.XINFO_STREAM, args, (cmd)->cmd.xinfoStream(rawBinaryKey(key)),
				new StreamInfoConverter<>());
	}

	@Override
	public Stream<byte[], byte[]> xInfoStream(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.XINFO, SubCommand.XINFO_STREAM, args, (cmd)->cmd.xinfoStream(rawKey(key)),
				new StreamInfoConverter<>());
	}

	@Override
	public StreamFull<String, String> xInfoStream(final String key, final boolean full) {
		final CommandArguments args = CommandArguments.create(key).add(full ? "FULL" : null);
		return executeCommand(Command.XINFO, SubCommand.XINFO_STREAM, args, (cmd)->cmd.xinfoStream(rawBinaryKey(key)),
				new StreamFullInfoConverter());
	}

	@Override
	public StreamFull<byte[], byte[]> xInfoStream(final byte[] key, final boolean full) {
		final CommandArguments args = CommandArguments.create(key).add(full ? "FULL" : null);
		return executeCommand(Command.XINFO, SubCommand.XINFO_STREAM, args, (cmd)->cmd.xinfoStream(rawKey(key)),
				new StreamFullInfoConverter());
	}

	@Override
	public StreamFull<String, String> xInfoStream(final String key, final boolean full, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(full ? "FULL" : null)
				.add(Keyword.Common.COUNT, count);
		return executeCommand(Command.XINFO, SubCommand.XINFO_STREAM, args, (cmd)->cmd.xinfoStream(rawBinaryKey(key)),
				new StreamFullInfoConverter());
	}

	@Override
	public StreamFull<byte[], byte[]> xInfoStream(final byte[] key, final boolean full, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(full ? "FULL" : null)
				.add(Keyword.Common.COUNT, count);
		return executeCommand(Command.XINFO, SubCommand.XINFO_STREAM, args, (cmd)->cmd.xinfoStream(rawKey(key)),
				new StreamFullInfoConverter());
	}

	@Override
	public Long xLen(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.XLEN, args, (cmd)->cmd.xlen(rawBinaryKey(key)));
	}

	@Override
	public Long xLen(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.XLEN, args, (cmd)->cmd.xlen(rawKey(key)));
	}

	@Override
	public StreamPendingSummary xPending(final String key, final String groupName) {
		final CommandArguments args = CommandArguments.create(key, groupName);
		return executeCommand(Command.XPENDING, args,
				(cmd)->cmd.xpending(rawBinaryKey(key), SafeEncoder.encode(groupName)), new PendingMessagesConverter());
	}

	@Override
	public StreamPendingSummary xPending(final byte[] key, final byte[] groupName) {
		final CommandArguments args = CommandArguments.create(key, groupName);
		return executeCommand(Command.XPENDING, args, (cmd)->cmd.xpending(rawKey(key), groupName),
				new PendingMessagesConverter());
	}

	@Override
	public List<StreamPending> xPending(final String key, final String groupName, final StreamEntryId start,
										final StreamEntryId end, final int count) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(start, end).add(count);
		return xPending(rawBinaryKey(key), new LettuceXPendingArgs<>(start, end, count, SafeEncoder.encode(groupName)),
				args);
	}

	@Override
	public List<StreamPending> xPending(final byte[] key, final byte[] groupName, final StreamEntryId start,
										final StreamEntryId end, final int count) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(start, end).add(count);
		return xPending(rawKey(key), new LettuceXPendingArgs<>(start, end, count, groupName), args);
	}

	@Override
	public List<StreamPending> xPending(final String key, final String groupName, final long minIdleTime,
										final StreamEntryId start, final StreamEntryId end, final int count) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(Keyword.Time.IDLE, minIdleTime)
				.add(start, end).add(count);
		return xPending(rawBinaryKey(key),
				new LettuceXPendingArgs<>(start, end, count, minIdleTime, SafeEncoder.encode(groupName)), args);
	}

	@Override
	public List<StreamPending> xPending(final byte[] key, final byte[] groupName, final long minIdleTime,
										final StreamEntryId start, final StreamEntryId end, final int count) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(Keyword.Time.IDLE, minIdleTime)
				.add(start, end).add(count);
		return xPending(rawKey(key), new LettuceXPendingArgs<>(start, end, count, minIdleTime, groupName), args);
	}

	@Override
	public List<StreamPending> xPending(final String key, final String groupName, final StreamEntryId start,
										final StreamEntryId end, final int count, final String consumerName) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(start, end).add(count)
				.add(consumerName);
		return xPending(rawBinaryKey(key), new LettuceXPendingArgs<>(start, end, count, SafeEncoder.encode(groupName),
				SafeEncoder.encode(consumerName)), args);
	}

	@Override
	public List<StreamPending> xPending(final byte[] key, final byte[] groupName, final StreamEntryId start,
										final StreamEntryId end, final int count, final byte[] consumerName) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(start, end).add(count)
				.add(consumerName);
		return xPending(rawKey(key), new LettuceXPendingArgs<>(start, end, count, groupName, consumerName), args);
	}

	@Override
	public List<StreamPending> xPending(final String key, final String groupName, final long minIdleTime,
										final StreamEntryId start, final StreamEntryId end, final int count,
										final String consumerName) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(Keyword.Time.IDLE, minIdleTime)
				.add(start, end).add(count).add(consumerName);
		return xPending(rawBinaryKey(key),
				new LettuceXPendingArgs<>(start, end, count, minIdleTime, SafeEncoder.encode(groupName),
						SafeEncoder.encode(consumerName)), args);
	}

	@Override
	public List<StreamPending> xPending(final byte[] key, final byte[] groupName, final long minIdleTime,
										final StreamEntryId start, final StreamEntryId end, final int count,
										final byte[] consumerName) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(Keyword.Time.IDLE, minIdleTime)
				.add(start, end).add(count).add(consumerName);
		return xPending(rawKey(key), new LettuceXPendingArgs<>(start, end, count, minIdleTime, groupName, consumerName),
				args);
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
			streamOffsets[i++] = XReadArgs.StreamOffset.from(rawBinaryKey(entry.getKey()), entry.getValue().toString());
		}

		return executeCommand(Command.XREAD, args, (cmd)->cmd.xread(streamOffsets),
				new ListConverter<>(new StreamMessageXReadInfoConverter<>(SafeEncoder::encode, SafeEncoder::encode)));
	}

	@Override
	public List<XReadInfo<String, String>> xRead(final Map<String, StreamEntryId> streams, final int count) {
		final CommandArguments args = CommandArguments.create(Keyword.Common.COUNT, count).add("STREAMS", streams);
		return xRead(streams, new LettuceXReadArgs(count), args);
	}

	@Override
	public List<XReadInfo<String, String>> xRead(final Map<String, StreamEntryId> streams, final long block) {
		final CommandArguments args = CommandArguments.create("BLOCK", block).add("STREAMS", streams);
		return xRead(streams, new LettuceXReadArgs(block), args);
	}

	@Override
	public List<XReadInfo<String, String>> xRead(final Map<String, StreamEntryId> streams, final long block,
												 final int count) {
		final CommandArguments args = CommandArguments.create(Keyword.Common.COUNT, count).add("BLOCK", block)
				.add("STREAMS", streams);
		return xRead(streams, new LettuceXReadArgs(block, count), args);
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
														   final XReadGroupArgument xReadGroupArgument,
														   final Map<String, StreamEntryId> streams) {
		final CommandArguments args = CommandArguments.create(groupName).add(consumerName).add(xReadGroupArgument)
				.add("STREAMS", streams);
		return xReadGroup(groupName, consumerName, streams, new LettuceXReadGroupArgs(xReadGroupArgument), args);
	}

	@Override
	public List<XReadGroupInfo<byte[], byte[]>> xReadGroup(final byte[] groupName, final byte[] consumerName,
														   final XReadGroupArgument xReadGroupArgument,
														   final Map<byte[], StreamEntryId> streams) {
		final CommandArguments args = CommandArguments.create(groupName).add(consumerName).add(xReadGroupArgument)
				.add("STREAMS", streams);
		return xReadGroup(groupName, consumerName, streams, new LettuceXReadGroupArgs(xReadGroupArgument), args);
	}

	@Override
	public List<XReadGroupInfo<String, String>> xReadGroup(final String groupName, final String consumerName,
														   final XReadGroupArgument xReadGroupArgument,
														   final Map<String, StreamEntryId> streams, final int count) {
		final CommandArguments args = CommandArguments.create(groupName).add(consumerName)
				.add(Keyword.Common.COUNT, count).add(xReadGroupArgument).add("STREAMS", streams);
		return xReadGroup(groupName, consumerName, streams, new LettuceXReadGroupArgs(xReadGroupArgument, count), args);
	}

	@Override
	public List<XReadGroupInfo<byte[], byte[]>> xReadGroup(final byte[] groupName, final byte[] consumerName,
														   final XReadGroupArgument xReadGroupArgument,
														   final Map<byte[], StreamEntryId> streams, final int count) {
		final CommandArguments args = CommandArguments.create(groupName).add(consumerName)
				.add(Keyword.Common.COUNT, count).add(xReadGroupArgument).add("STREAMS", streams);
		return xReadGroup(groupName, consumerName, streams, new LettuceXReadGroupArgs(xReadGroupArgument, count), args);
	}

	@Override
	public List<XReadGroupInfo<String, String>> xReadGroup(final String groupName, final String consumerName,
														   final Map<String, StreamEntryId> streams, final int count) {
		final CommandArguments args = CommandArguments.create(groupName).add(consumerName)
				.add(Keyword.Common.COUNT, count).add("STREAMS", streams);
		return xReadGroup(groupName, consumerName, streams, new LettuceXReadGroupArgs(count), args);
	}

	@Override
	public List<XReadGroupInfo<byte[], byte[]>> xReadGroup(final byte[] groupName, final byte[] consumerName,
														   final Map<byte[], StreamEntryId> streams, final int count) {
		final CommandArguments args = CommandArguments.create(groupName).add(consumerName)
				.add(Keyword.Common.COUNT, count).add("STREAMS", streams);
		return xReadGroup(groupName, consumerName, streams, new LettuceXReadGroupArgs(count), args);
	}

	@Override
	public List<StreamEntry<String, String>> xRevRange(final String key, final StreamEntryId end,
													   final StreamEntryId start) {
		final CommandArguments args = CommandArguments.create(key).add(end, start);
		return executeCommand(Command.XREVRANGE, args,
				(cmd)->cmd.xrevrange(rawBinaryKey(key), Range.create(start.toString(), end.toString())),
				new ListConverter<>(new StreamMessageConverter<>(SafeEncoder::encode, SafeEncoder::encode)));
	}

	@Override
	public List<StreamEntry<byte[], byte[]>> xRevRange(final byte[] key, final StreamEntryId end,
													   final StreamEntryId start) {
		final CommandArguments args = CommandArguments.create(key).add(end, start);
		return executeCommand(Command.XREVRANGE, args,
				(cmd)->cmd.xrevrange(rawKey(key), Range.create(start.toString(), end.toString())),
				new ListConverter<>(new StreamMessageConverter<>((k)->k, (v)->v)));
	}

	@Override
	public List<StreamEntry<String, String>> xRevRange(final String key, final StreamEntryId end,
													   final StreamEntryId start, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(end, start).add(Keyword.Common.COUNT, count);
		return executeCommand(Command.XREVRANGE, args,
				(cmd)->cmd.xrevrange(rawBinaryKey(key), Range.create(start.toString(), end.toString()),
						Limit.from(count)),
				new ListConverter<>(new StreamMessageConverter<>(SafeEncoder::encode, SafeEncoder::encode)));
	}

	@Override
	public List<StreamEntry<byte[], byte[]>> xRevRange(final byte[] key, final StreamEntryId end,
													   final StreamEntryId start, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(end, start).add(Keyword.Common.COUNT, count);
		return executeCommand(Command.XREVRANGE, args,
				(cmd)->cmd.xrevrange(rawKey(key), Range.create(start.toString(), end.toString()), Limit.from(count)),
				new ListConverter<>(new StreamMessageConverter<>((k)->k, (v)->v)));
	}

	@Override
	public Status xSetId(final String key, final StreamEntryId lastId) {
		final CommandArguments args = CommandArguments.create(key, lastId);
		return executeCommand(Command.XSETID, args);
	}

	@Override
	public Status xSetId(final byte[] key, final StreamEntryId lastId) {
		final CommandArguments args = CommandArguments.create(key, lastId);
		return executeCommand(Command.XSETID, args);
	}

	@Override
	public Status xSetId(final String key, final StreamEntryId lastId, final long entriesAdded) {
		final CommandArguments args = CommandArguments.create(key, lastId).add("ENTRIESADDED", entriesAdded);
		return executeCommand(Command.XSETID, args);
	}

	@Override
	public Status xSetId(final byte[] key, final StreamEntryId lastId, final long entriesAdded) {
		final CommandArguments args = CommandArguments.create(key, lastId).add("ENTRIESADDED", entriesAdded);
		return executeCommand(Command.XSETID, args);
	}

	@Override
	public Status xSetId(final String key, final StreamEntryId lastId, final long entriesAdded,
						 final StreamEntryId maxDeletedId) {
		final CommandArguments args = CommandArguments.create(key, lastId).add("ENTRIESADDED", entriesAdded)
				.add("MAXDELETEDID", maxDeletedId);
		return executeCommand(Command.XSETID, args);
	}

	@Override
	public Status xSetId(final byte[] key, final StreamEntryId lastId, final long entriesAdded,
						 final StreamEntryId maxDeletedId) {
		final CommandArguments args = CommandArguments.create(key, lastId).add("ENTRIESADDED", entriesAdded)
				.add("MAXDELETEDID", maxDeletedId);
		return executeCommand(Command.XSETID, args);
	}

	@Override
	public Status xSetId(final String key, final StreamEntryId lastId, final StreamEntryId maxDeletedId) {
		final CommandArguments args = CommandArguments.create(key, lastId).add("MAXDELETEDID", maxDeletedId);
		return executeCommand(Command.XSETID, args);
	}

	@Override
	public Status xSetId(final byte[] key, final StreamEntryId lastId, final StreamEntryId maxDeletedId) {
		final CommandArguments args = CommandArguments.create(key, lastId).add("MAXDELETEDID", maxDeletedId);
		return executeCommand(Command.XSETID, args);
	}

	@Override
	public Long xTrim(final String key, final MaxLenMinId<?> maxLenMinId) {
		final CommandArguments args = CommandArguments.create(key).add(maxLenMinId);
		return xTrim(rawBinaryKey(key), new LettuceXTrimArgs(maxLenMinId), args);
	}

	@Override
	public Long xTrim(final byte[] key, final MaxLenMinId<?> maxLenMinId) {
		final CommandArguments args = CommandArguments.create(key).add(maxLenMinId);
		return xTrim(rawKey(key), new LettuceXTrimArgs(maxLenMinId), args);
	}

	@Override
	public Long xTrim(final String key, final ApproximateExactTrimming approximateExactTrimming,
					  final MaxLenMinId<?> maxLenMinId) {
		final CommandArguments args = CommandArguments.create(key).add(maxLenMinId.getType())
				.add(approximateExactTrimming).add(maxLenMinId.getThreshold());
		return xTrim(rawBinaryKey(key), new LettuceXTrimArgs(maxLenMinId, approximateExactTrimming), args);
	}

	@Override
	public Long xTrim(final byte[] key, final ApproximateExactTrimming approximateExactTrimming,
					  final MaxLenMinId<?> maxLenMinId) {
		final CommandArguments args = CommandArguments.create(key).add(maxLenMinId.getType())
				.add(approximateExactTrimming).add(maxLenMinId.getThreshold());
		return xTrim(rawKey(key), new LettuceXTrimArgs(maxLenMinId, approximateExactTrimming), args);
	}

	@Override
	public Long xTrim(final String key, final ApproximateExactTrimming approximateExactTrimming,
					  final MaxLenMinId<?> maxLenMinId, final StreamDeletionPolicy deletionPolicy) {
		final CommandArguments args = CommandArguments.create(key).add(maxLenMinId.getType())
				.add(approximateExactTrimming).add(maxLenMinId.getThreshold()).add(deletionPolicy);
		return xTrim(rawBinaryKey(key),
				new LettuceXTrimArgs(maxLenMinId, approximateExactTrimming).deletionPolicy(deletionPolicy), args);
	}

	@Override
	public Long xTrim(final byte[] key, final ApproximateExactTrimming approximateExactTrimming,
					  final MaxLenMinId<?> maxLenMinId, final StreamDeletionPolicy deletionPolicy) {
		final CommandArguments args = CommandArguments.create(key).add(maxLenMinId.getType())
				.add(approximateExactTrimming).add(maxLenMinId.getThreshold()).add(deletionPolicy);
		return xTrim(rawKey(key),
				new LettuceXTrimArgs(maxLenMinId, approximateExactTrimming).deletionPolicy(deletionPolicy), args);
	}

	@Override
	public Long xTrim(final String key, final MaxLenMinId<?> maxLenMinId, final StreamDeletionPolicy deletionPolicy) {
		final CommandArguments args = CommandArguments.create(key).add(maxLenMinId).add(deletionPolicy);
		return xTrim(rawBinaryKey(key), new LettuceXTrimArgs(maxLenMinId).deletionPolicy(deletionPolicy), args);
	}

	@Override
	public Long xTrim(final byte[] key, final MaxLenMinId<?> maxLenMinId, final StreamDeletionPolicy deletionPolicy) {
		final CommandArguments args = CommandArguments.create(key).add(maxLenMinId).add(deletionPolicy);
		return xTrim(rawKey(key), new LettuceXTrimArgs(maxLenMinId).deletionPolicy(deletionPolicy), args);
	}

	@Override
	public Long xTrim(final String key, final MaxLenMinId<?> maxLenMinId, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(maxLenMinId).add(Keyword.Common.LIMIT, count);
		return xTrim(rawBinaryKey(key), new LettuceXTrimArgs(maxLenMinId, count), args);
	}

	@Override
	public Long xTrim(final byte[] key, final MaxLenMinId<?> maxLenMinId, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(maxLenMinId).add(Keyword.Common.LIMIT, count);
		return xTrim(rawKey(key), new LettuceXTrimArgs(maxLenMinId, count), args);
	}

	@Override
	public Long xTrim(final String key, final ApproximateExactTrimming approximateExactTrimming,
					  final MaxLenMinId<?> maxLenMinId, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(maxLenMinId.getType())
				.add(approximateExactTrimming).add(maxLenMinId.getThreshold()).add(Keyword.Common.LIMIT, count);
		return xTrim(rawBinaryKey(key), new LettuceXTrimArgs(maxLenMinId, approximateExactTrimming), args);
	}

	@Override
	public Long xTrim(final byte[] key, final ApproximateExactTrimming approximateExactTrimming,
					  final MaxLenMinId<?> maxLenMinId, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(maxLenMinId.getType())
				.add(approximateExactTrimming).add(maxLenMinId.getThreshold()).add(Keyword.Common.LIMIT, count);
		return xTrim(rawKey(key), new LettuceXTrimArgs(maxLenMinId, approximateExactTrimming), args);
	}

	@Override
	public Long xTrim(final String key, final ApproximateExactTrimming approximateExactTrimming,
					  final MaxLenMinId<?> maxLenMinId, final StreamDeletionPolicy deletionPolicy, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(maxLenMinId.getType())
				.add(approximateExactTrimming).add(maxLenMinId.getThreshold()).add(deletionPolicy)
				.add(Keyword.Common.LIMIT, count);
		return xTrim(rawBinaryKey(key), new LettuceXTrimArgs(maxLenMinId, count), args);
	}

	@Override
	public Long xTrim(final byte[] key, final ApproximateExactTrimming approximateExactTrimming,
					  final MaxLenMinId<?> maxLenMinId, final StreamDeletionPolicy deletionPolicy, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(maxLenMinId.getType())
				.add(approximateExactTrimming).add(maxLenMinId.getThreshold()).add(deletionPolicy)
				.add(Keyword.Common.LIMIT, count);
		return xTrim(rawKey(key), new LettuceXTrimArgs(maxLenMinId, count), args);
	}

	@Override
	public Long xTrim(final String key, final MaxLenMinId<?> maxLenMinId, final StreamDeletionPolicy deletionPolicy,
					  final int count) {
		final CommandArguments args = CommandArguments.create(key).add(maxLenMinId).add(deletionPolicy)
				.add(Keyword.Common.LIMIT, count);
		return xTrim(rawBinaryKey(key), new LettuceXTrimArgs(maxLenMinId, count).deletionPolicy(deletionPolicy), args);
	}

	@Override
	public Long xTrim(final byte[] key, final MaxLenMinId<?> maxLenMinId, final StreamDeletionPolicy deletionPolicy,
					  final int count) {
		final CommandArguments args = CommandArguments.create(key).add(maxLenMinId).add(deletionPolicy)
				.add(Keyword.Common.LIMIT, count);
		return xTrim(rawKey(key), new LettuceXTrimArgs(maxLenMinId, count).deletionPolicy(deletionPolicy), args);
	}

	private static String[] messageIds(final StreamEntryId... ids) {
		return Arrays.map(ids, String.class, StreamEntryId::toString);
	}

	private Long xAck(final byte[] key, final byte[] groupName, final StreamEntryId[] ids,
					  final CommandArguments args) {
		return executeCommand(Command.XACK, args, (cmd)->cmd.xack(key, groupName, messageIds(ids)));
	}

	private List<StreamEntryDeletionResult> xAckDel(final byte[] key, final byte[] groupName, final StreamEntryId[] ids,
													final CommandArguments args) {
		return executeCommand(Command.XACKDEL, args, (cmd)->cmd.xackdel(key, groupName, messageIds(ids)),
				new ListConverter<>(new StreamEntryDeletionResultConverter()));
	}

	private List<StreamEntryDeletionResult> xAckDel(final byte[] key, final byte[] groupName,
													final StreamDeletionPolicy deletionPolicy,
													final StreamEntryId[] ids, final CommandArguments args) {
		final StreamDeletionPolicyConverter streamDeletionPolicyConverter = new StreamDeletionPolicyConverter();
		return executeCommand(Command.XACKDEL, args,
				(cmd)->cmd.xackdel(key, groupName, streamDeletionPolicyConverter.convert(deletionPolicy),
						CompositeArgumentUtils.messageIds(ids)),
				new ListConverter<>(new StreamEntryDeletionResultConverter()));
	}

	private StreamEntryId stringXAdd(final byte[] key, final Map<String, String> hash, final XAddArgs xAddArgs,
									 final CommandArguments args) {
		final MapConverter<String, String, byte[], byte[]> mapConverter = new StringMapBinaryMapConverter();
		return executeCommand(Command.XADD, args, (cmd)->cmd.xadd(key, xAddArgs, mapConverter.convert(hash)),
				new StreamEntryIDConverter());
	}

	private StreamEntryId binaryXAdd(final byte[] key, final Map<byte[], byte[]> hash, final XAddArgs xAddArgs,
									 final CommandArguments args) {
		return executeCommand(Command.XADD, args, (cmd)->cmd.xadd(key, xAddArgs, hash), new StreamEntryIDConverter());
	}

	private AutoClaimInfo<String, String> stringXAutoClaim(final byte[] key,
														   final XAutoClaimArgs<byte[]> xAutoClaimArgs,
														   final CommandArguments args) {
		return executeCommand(Command.XAUTOCLAIM, args, (cmd)->cmd.xautoclaim(key, xAutoClaimArgs),
				new ClaimedMessagesAutoClaimInfoConverter<>(SafeEncoder::encode, SafeEncoder::encode));
	}

	private AutoClaimInfo<byte[], byte[]> binaryXAutoClaim(final byte[] key,
														   final XAutoClaimArgs<byte[]> xAutoClaimArgs,
														   final CommandArguments args) {
		return executeCommand(Command.XAUTOCLAIM, args, (cmd)->cmd.xautoclaim(key, xAutoClaimArgs),
				new ClaimedMessagesAutoClaimInfoConverter<>((k)->k, (v)->v));
	}

	private AutoClaimId xAutoClaimJustId(final byte[] key, final XAutoClaimArgs<byte[]> xAutoClaimArgs,
										 final CommandArguments args) {
		xAutoClaimArgs.justid();
		return executeCommand(Command.XAUTOCLAIM, args, (cmd)->cmd.xautoclaim(key, xAutoClaimArgs),
				new ClaimedMessagesAutoClaimIdConverter<>());
	}

	private List<StreamEntry<String, String>> xClaim(final byte[] key, final String groupName,
													 final String consumerName, final StreamEntryId[] ids,
													 final int minIdleTime, final XClaimArgs xClaimArgs,
													 final CommandArguments args) {
		xClaimArgs.minIdleTime(minIdleTime);
		return executeCommand(Command.XCLAIM, args,
				(cmd)->cmd.xclaim(key, Consumer.from(SafeEncoder.encode(groupName), SafeEncoder.encode(consumerName)),
						xClaimArgs, messageIds(ids)),
				new ListConverter<>(new StreamMessageConverter<>(SafeEncoder::encode, SafeEncoder::encode)));
	}

	private List<StreamEntry<byte[], byte[]>> xClaim(final byte[] key, final byte[] groupName,
													 final byte[] consumerName, final StreamEntryId[] ids,
													 final int minIdleTime, final XClaimArgs xClaimArgs,
													 final CommandArguments args) {
		xClaimArgs.minIdleTime(minIdleTime);
		return executeCommand(Command.XCLAIM, args,
				(cmd)->cmd.xclaim(key, Consumer.from(groupName, consumerName), xClaimArgs, messageIds(ids)),
				new ListConverter<>(new StreamMessageConverter<>((k)->k, (v)->v)));
	}

	private List<StreamEntryId> xClaimJustId(final byte[] key, final String groupName, final String consumerName,
											 final StreamEntryId[] ids, final int minIdleTime,
											 final XClaimArgs xClaimArgs, final CommandArguments args) {
		return xClaimJustId(key, SafeEncoder.encode(groupName), SafeEncoder.encode(consumerName), ids, minIdleTime,
				xClaimArgs, args);
	}

	private List<StreamEntryId> xClaimJustId(final byte[] key, final byte[] groupName, final byte[] consumerName,
											 final StreamEntryId[] ids, final int minIdleTime,
											 final XClaimArgs xClaimArgs, final CommandArguments args) {
		xClaimArgs.minIdleTime(minIdleTime).justid();
		return executeCommand(Command.XCLAIM, args,
				(cmd)->cmd.xclaim(key, Consumer.from(groupName, consumerName), xClaimArgs, messageIds(ids)),
				new ListConverter<>(new StreamMessageConverter.StreamMessageStreamEntryIdConverter()));
	}

	private List<StreamEntryDeletionResult> xDelEx(final byte[] key, final StreamEntryId[] ids,
												   final StreamDeletionPolicy deletionPolicy,
												   final CommandArguments args) {
		final StreamDeletionPolicyConverter streamDeletionPolicyConverter = new StreamDeletionPolicyConverter();
		return executeCommand(Command.XDELEX, args,
				(cmd)->cmd.xdelex(key, streamDeletionPolicyConverter.convert(deletionPolicy),
						CompositeArgumentUtils.messageIds(ids)),
				new ListConverter<>(new StreamEntryDeletionResultConverter()));
	}

	private Status xGroupCreate(final byte[] key, final byte[] groupName, final StreamEntryId id,
								final CommandArguments args) {
		return executeCommand(Command.XGROUP, SubCommand.XGROUP_CREATE, args,
				(cmd)->cmd.xgroupCreate(XReadArgs.StreamOffset.from(key, id.toString()), groupName),
				new OkStatusConverter());
	}

	private Status xGroupCreate(final byte[] key, final byte[] groupName, final StreamEntryId id,
								final LettuceXGroupCreateArgs xGroupCreateArgs, final CommandArguments args) {
		return executeCommand(Command.XGROUP, SubCommand.XGROUP_CREATE, args,
				(cmd)->cmd.xgroupCreate(XReadArgs.StreamOffset.from(key, id.toString()), groupName, xGroupCreateArgs),
				new OkStatusConverter());
	}

	private Status xGroupCreateConsumer(final byte[] key, final byte[] groupName, final byte[] consumerName,
										final CommandArguments args) {
		return executeCommand(Command.XGROUP, SubCommand.XGROUP_CREATECONSUMER, args,
				(cmd)->cmd.xgroupCreateconsumer(key, Consumer.from(groupName, consumerName)),
				new BooleanStatusConverter());
	}

	private Long xGroupDelConsumer(final byte[] key, final byte[] groupName, final byte[] consumerName,
								   final CommandArguments args) {
		return executeCommand(Command.XGROUP, SubCommand.XGROUP_DELCONSUMER, args,
				(cmd)->cmd.xgroupDelconsumer(key, Consumer.from(groupName, consumerName)));
	}

	private Status xGroupSetId(final byte[] key, final byte[] groupName, final StreamEntryId id,
							   final CommandArguments args) {
		return executeCommand(Command.XGROUP, SubCommand.XGROUP_SETID, args,
				(cmd)->cmd.xgroupSetid(XReadArgs.StreamOffset.from(key, id.toString()), groupName),
				new OkStatusConverter());
	}

	private List<StreamConsumer> xInfoConsumers(final byte[] key, final byte[] groupName, final CommandArguments args) {
		return executeCommand(Command.XINFO, SubCommand.XINFO_CONSUMERS, args,
				(cmd)->cmd.xinfoConsumers(key, groupName), new ListConverter<>(new StreamConsumersInfoConverter()));
	}

	private List<StreamGroup> xInfoGroups(final byte[] key, final CommandArguments args) {
		return executeCommand(Command.XINFO, SubCommand.XINFO_GROUPS, args, (cmd)->cmd.xinfoGroups(key),
				new ListConverter<>(new StreamGroupInfoConverter()));
	}

	private List<StreamPending> xPending(final byte[] key, final XPendingArgs<byte[]> xPendingArgs,
										 final CommandArguments args) {
		return executeCommand(Command.XPENDING, args, (cmd)->cmd.xpending(key, xPendingArgs),
				new ListConverter<>(new PendingMessageConverter()));
	}

	private List<XReadInfo<String, String>> xRead(final Map<String, StreamEntryId> streams, final XReadArgs xReadArgs,
												  final CommandArguments args) {
		final XReadArgs.StreamOffset<byte[]>[] streamOffsets = new XReadArgs.StreamOffset[streams.size()];
		int i = 0;

		for(Map.Entry<String, StreamEntryId> entry : streams.entrySet()){
			streamOffsets[i++] = XReadArgs.StreamOffset.from(rawBinaryKey(entry.getKey()), entry.getValue().toString());
		}

		return executeCommand(Command.XREAD, args, (cmd)->cmd.xread(xReadArgs, streamOffsets), new ListConverter<>(
				new StreamMessageXReadInfoConverter<>(SafeEncoder::encode, SafeEncoder::encode)));
	}

	private List<XReadGroupInfo<String, String>> xReadGroup(final String groupName, final String consumerName,
															final Map<String, StreamEntryId> streams,
															final CommandArguments args) {
		final XReadArgs.StreamOffset<byte[]>[] streamOffsets = new XReadArgs.StreamOffset[streams.size()];
		int i = 0;

		for(Map.Entry<String, StreamEntryId> entry : streams.entrySet()){
			streamOffsets[i++] = XReadArgs.StreamOffset.from(rawBinaryKey(entry.getKey()), entry.getValue().toString());
		}

		return executeCommand(Command.XREADGROUP, args,
				(cmd)->cmd.xreadgroup(Consumer.from(SafeEncoder.encode(groupName), SafeEncoder.encode(consumerName)),
						streamOffsets), new ListConverter<>(
						new StreamMessageXReadGroupInfoConverter<>(SafeEncoder::encode, SafeEncoder::encode)));
	}

	private List<XReadGroupInfo<byte[], byte[]>> xReadGroup(final byte[] groupName, final byte[] consumerName,
															final Map<byte[], StreamEntryId> streams,
															final CommandArguments args) {
		final XReadArgs.StreamOffset<byte[]>[] streamOffsets = new XReadArgs.StreamOffset[streams.size()];
		int i = 0;

		for(Map.Entry<byte[], StreamEntryId> entry : streams.entrySet()){
			streamOffsets[i++] = XReadArgs.StreamOffset.from(rawKey(entry.getKey()), entry.getValue().toString());
		}

		return executeCommand(Command.XREADGROUP, args,
				(cmd)->cmd.xreadgroup(Consumer.from(groupName, consumerName), streamOffsets),
				new ListConverter<>(new StreamMessageXReadGroupInfoConverter<>((k)->k, (v)->v)));
	}

	private List<XReadGroupInfo<String, String>> xReadGroup(final String groupName, final String consumerName,
															final Map<String, StreamEntryId> streams,
															final XReadArgs xReadArgs, final CommandArguments args) {
		final XReadArgs.StreamOffset<byte[]>[] streamOffsets = new XReadArgs.StreamOffset[streams.size()];
		int i = 0;

		for(Map.Entry<String, StreamEntryId> entry : streams.entrySet()){
			streamOffsets[i++] = XReadArgs.StreamOffset.from(rawBinaryKey(entry.getKey()), entry.getValue().toString());
		}

		return executeCommand(Command.XREADGROUP, args,
				(cmd)->cmd.xreadgroup(Consumer.from(SafeEncoder.encode(groupName), SafeEncoder.encode(consumerName)),
						xReadArgs, streamOffsets),
				new ListConverter<>(
						new StreamMessageXReadGroupInfoConverter<>(SafeEncoder::encode, SafeEncoder::encode)));
	}

	private List<XReadGroupInfo<byte[], byte[]>> xReadGroup(final byte[] groupName, final byte[] consumerName,
															final Map<byte[], StreamEntryId> streams,
															final XReadArgs xReadArgs, final CommandArguments args) {
		final XReadArgs.StreamOffset<byte[]>[] streamOffsets = new XReadArgs.StreamOffset[streams.size()];
		int i = 0;

		for(Map.Entry<byte[], StreamEntryId> entry : streams.entrySet()){
			streamOffsets[i++] = XReadArgs.StreamOffset.from(rawKey(entry.getKey()), entry.getValue().toString());
		}

		return executeCommand(Command.XREADGROUP, args,
				(cmd)->cmd.xreadgroup(Consumer.from(groupName, consumerName), xReadArgs, streamOffsets),
				new ListConverter<>(new StreamMessageXReadGroupInfoConverter<>((k)->k, (v)->v)));
	}

	private Long xTrim(final byte[] key, final XTrimArgs xTrimArgs,
					   final CommandArguments args) {
		return executeCommand(Command.XTRIM, args, (cmd)->cmd.xtrim(key, xTrimArgs));
	}

}
