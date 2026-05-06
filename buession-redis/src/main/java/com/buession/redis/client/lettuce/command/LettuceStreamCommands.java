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
import com.buession.redis.core.command.args.stream.ApproximateExactTrimming;
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
import com.buession.redis.core.command.RedisCommand;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.StreamCommands;
import com.buession.redis.core.command.RedisSubCommand;
import com.buession.redis.core.command.args.MaxLenMinId;
import com.buession.redis.core.command.args.stream.XAddArgument;
import com.buession.redis.core.command.args.stream.XClaimArgument;
import com.buession.redis.core.command.args.stream.XReadGroupArgument;
import com.buession.redis.core.internal.convert.Converters;
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
		return xAck(SafeEncoder.encode(key), SafeEncoder.encode(groupName), ids, args);
	}

	@Override
	public Long xAck(final byte[] key, final byte[] groupName, final StreamEntryId... ids) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(ids);
		return xAck(key, groupName, ids, args);
	}

	@Override
	public List<StreamEntryDeletionResult> xAckDel(final String key, final String groupName,
	                                               final StreamEntryId... ids) {
		final CommandArguments args = CommandArguments.create(key, groupName).add("IDS", ids.length).add(ids);
		return xAckDel(SafeEncoder.encode(key), SafeEncoder.encode(groupName), ids, args);
	}

	@Override
	public List<StreamEntryDeletionResult> xAckDel(final byte[] key, final byte[] groupName,
	                                               final StreamEntryId... ids) {
		final CommandArguments args = CommandArguments.create(key, groupName).add("IDS", ids.length).add(ids);
		return xAckDel(key, groupName, ids, args);
	}

	@Override
	public List<StreamEntryDeletionResult> xAckDel(final String key, final String groupName,
	                                               final StreamDeletionPolicy deletionPolicy,
	                                               final StreamEntryId... ids) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(deletionPolicy).add("IDS", ids.length)
				.add(ids);
		return xAckDel(SafeEncoder.encode(key), SafeEncoder.encode(groupName), deletionPolicy, ids, args);
	}

	@Override
	public List<StreamEntryDeletionResult> xAckDel(final byte[] key, final byte[] groupName,
	                                               final StreamDeletionPolicy deletionPolicy,
	                                               final StreamEntryId... ids) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(deletionPolicy).add("IDS", ids.length)
				.add(ids);
		return xAckDel(key, groupName, deletionPolicy, ids, args);
	}

	@Override
	public StreamEntryId xAdd(final String key, final StreamEntryId id, final Map<String, String> hash) {
		final CommandArguments args = CommandArguments.create(key).add(id).add(hash);
		return stringXAdd(SafeEncoder.encode(key), hash, new LettuceXAddArgs(id), args);
	}

	@Override
	public StreamEntryId xAdd(final byte[] key, final StreamEntryId id, final Map<byte[], byte[]> hash) {
		final CommandArguments args = CommandArguments.create(key).add(id).add(hash);
		return binaryXAdd(key, hash, new LettuceXAddArgs(id), args);
	}

	@Override
	public StreamEntryId xAdd(final String key, final StreamEntryId id, final Map<String, String> hash,
	                          final XAddArgument xAddArgument) {
		final CommandArguments args = CommandArguments.create(key).add(xAddArgument).add(id).add(hash);
		return stringXAdd(SafeEncoder.encode(key), hash, new LettuceXAddArgs(xAddArgument, id), args);
	}

	@Override
	public StreamEntryId xAdd(final byte[] key, final StreamEntryId id, final Map<byte[], byte[]> hash,
	                          final XAddArgument xAddArgument) {
		final CommandArguments args = CommandArguments.create(key).add(xAddArgument).add(id).add(hash);
		return binaryXAdd(key, hash, new LettuceXAddArgs(xAddArgument, id), args);
	}

	@Override
	public AutoClaimInfo<String, String> xAutoClaim(final String key, final String groupName, final String consumerName,
	                                                final int minIdleTime, final StreamEntryId start) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(consumerName).add(minIdleTime)
				.add(start);
		return stringXAutoClaim(SafeEncoder.encode(key),
				new LettuceXAutoClaimArgs<>(SafeEncoder.encode(groupName), SafeEncoder.encode(consumerName),
						minIdleTime, start), args);
	}

	@Override
	public AutoClaimInfo<byte[], byte[]> xAutoClaim(final byte[] key, final byte[] groupName, final byte[] consumerName,
	                                                final int minIdleTime, final StreamEntryId start) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(consumerName).add(minIdleTime)
				.add(start);
		return binaryXAutoClaim(key, new LettuceXAutoClaimArgs<>(groupName, consumerName, minIdleTime, start),
				args);
	}

	@Override
	public AutoClaimInfo<String, String> xAutoClaim(final String key, final String groupName, final String consumerName,
	                                                final int minIdleTime, final StreamEntryId start, final int count) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(consumerName).add(minIdleTime)
				.add(start).add(Keyword.Common.COUNT, count);
		return stringXAutoClaim(SafeEncoder.encode(key),
				new LettuceXAutoClaimArgs<>(SafeEncoder.encode(groupName), SafeEncoder.encode(consumerName),
						minIdleTime, start, count), args);
	}

	@Override
	public AutoClaimInfo<byte[], byte[]> xAutoClaim(final byte[] key, final byte[] groupName, final byte[] consumerName,
	                                                final int minIdleTime, final StreamEntryId start, final int count) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(consumerName).add(minIdleTime)
				.add(start).add(Keyword.Common.COUNT, count);
		return binaryXAutoClaim(key,
				new LettuceXAutoClaimArgs<>(groupName, consumerName, minIdleTime, start, count), args);
	}

	@Override
	public AutoClaimId xAutoClaimJustId(final String key, final String groupName, final String consumerName,
	                                    final int minIdleTime, final StreamEntryId start) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(consumerName).add(minIdleTime)
				.add(start).add("JUSTID");
		return xAutoClaimJustId(SafeEncoder.encode(key),
				new LettuceXAutoClaimArgs<>(SafeEncoder.encode(groupName), SafeEncoder.encode(consumerName),
						minIdleTime, start), args);
	}

	@Override
	public AutoClaimId xAutoClaimJustId(final byte[] key, final byte[] groupName, final byte[] consumerName,
	                                    final int minIdleTime, final StreamEntryId start) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(consumerName).add(minIdleTime)
				.add(start).add("JUSTID");
		return xAutoClaimJustId(key, new LettuceXAutoClaimArgs<>(groupName, consumerName, minIdleTime, start),
				args);
	}

	@Override
	public AutoClaimId xAutoClaimJustId(final String key, final String groupName, final String consumerName,
	                                    final int minIdleTime, final StreamEntryId start, final int count) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(consumerName).add(minIdleTime)
				.add(start).add(Keyword.Common.COUNT, count).add("JUSTID");
		return xAutoClaimJustId(SafeEncoder.encode(key),
				new LettuceXAutoClaimArgs<>(SafeEncoder.encode(groupName), SafeEncoder.encode(consumerName),
						minIdleTime, start, count), args);
	}

	@Override
	public AutoClaimId xAutoClaimJustId(final byte[] key, final byte[] groupName, final byte[] consumerName,
	                                    final int minIdleTime, final StreamEntryId start, final int count) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(consumerName).add(minIdleTime)
				.add(start).add(Keyword.Common.COUNT, count).add("JUSTID");
		return xAutoClaimJustId(key,
				new LettuceXAutoClaimArgs<>(groupName, consumerName, minIdleTime, start, count), args);
	}

	@Override
	public Status xCfgSet(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.XCFGSET, args);
	}

	@Override
	public Status xCfgSet(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.XCFGSET, args);
	}

	@Override
	public Status xCfgSet(final String key, final long duration) {
		final CommandArguments args = CommandArguments.create(key).add("IDMP-DURATION", duration);
		return executeCommand(RedisCommand.XCFGSET, args);
	}

	@Override
	public Status xCfgSet(final byte[] key, final long duration) {
		final CommandArguments args = CommandArguments.create(key).add("IDMP-DURATION", duration);
		return executeCommand(RedisCommand.XCFGSET, args);
	}

	@Override
	public Status xCfgSet(final String key, final long duration, final int maxsize) {
		final CommandArguments args = CommandArguments.create(key).add("IDMP-DURATION", duration)
				.add("IDMP-MAXSIZE", maxsize);
		return executeCommand(RedisCommand.XCFGSET, args);
	}

	@Override
	public Status xCfgSet(final byte[] key, final long duration, final int maxsize) {
		final CommandArguments args = CommandArguments.create(key).add("IDMP-DURATION", duration)
				.add("IDMP-MAXSIZE", maxsize);
		return executeCommand(RedisCommand.XCFGSET, args);
	}

	@Override
	public Status xCfgSet(final String key, final int maxsize) {
		final CommandArguments args = CommandArguments.create(key).add("IDMP-MAXSIZE", maxsize);
		return executeCommand(RedisCommand.XCFGSET, args);
	}

	@Override
	public Status xCfgSet(final byte[] key, final int maxsize) {
		final CommandArguments args = CommandArguments.create(key).add("IDMP-MAXSIZE", maxsize);
		return executeCommand(RedisCommand.XCFGSET, args);
	}

	@Override
	public List<StreamEntry<String, String>> xClaim(final String key, final String groupName, final String consumerName,
	                                                final int minIdleTime, final StreamEntryId... ids) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(consumerName).add(minIdleTime)
				.add(ids);
		return xClaim(SafeEncoder.encode(key), groupName, consumerName, ids, minIdleTime, new XClaimArgs(), args);
	}

	@Override
	public List<StreamEntry<byte[], byte[]>> xClaim(final byte[] key, final byte[] groupName, final byte[] consumerName,
	                                                final int minIdleTime, final StreamEntryId... ids) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(consumerName).add(minIdleTime)
				.add(ids);
		return xClaim(key, groupName, consumerName, ids, minIdleTime, new XClaimArgs(), args);
	}

	@Override
	public List<StreamEntry<String, String>> xClaim(final String key, final String groupName, final String consumerName,
	                                                final int minIdleTime, final StreamEntryId[] ids,
	                                                final XClaimArgument xClaimArgument) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(consumerName).add(minIdleTime)
				.add(ids).add(xClaimArgument);
		return xClaim(SafeEncoder.encode(key), groupName, consumerName, ids, minIdleTime,
				new LettuceXClaimArgs(xClaimArgument), args);
	}

	@Override
	public List<StreamEntry<byte[], byte[]>> xClaim(final byte[] key, final byte[] groupName, final byte[] consumerName,
	                                                final int minIdleTime, final StreamEntryId[] ids,
	                                                final XClaimArgument xClaimArgument) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(consumerName).add(minIdleTime)
				.add(ids).add(xClaimArgument);
		return xClaim(key, groupName, consumerName, ids, minIdleTime, new LettuceXClaimArgs(xClaimArgument),
				args);
	}

	@Override
	public List<StreamEntryId> xClaimJustId(final String key, final String groupName, final String consumerName,
	                                        final int minIdleTime, final StreamEntryId... ids) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(consumerName).add(minIdleTime)
				.add(ids).add("JUSTID");
		return xClaimJustId(SafeEncoder.encode(key), groupName, consumerName, ids, minIdleTime, new XClaimArgs(), args);
	}

	@Override
	public List<StreamEntryId> xClaimJustId(final byte[] key, final byte[] groupName, final byte[] consumerName,
	                                        final int minIdleTime, final StreamEntryId... ids) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(consumerName).add(minIdleTime)
				.add(ids).add("JUSTID");
		return xClaimJustId(key, groupName, consumerName, ids, minIdleTime, new XClaimArgs(), args);
	}

	@Override
	public List<StreamEntryId> xClaimJustId(final String key, final String groupName, final String consumerName,
	                                        final int minIdleTime, final StreamEntryId[] ids,
	                                        final XClaimArgument xClaimArgument) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(consumerName).add(minIdleTime)
				.add(ids).add(xClaimArgument).add("JUSTID");
		return xClaimJustId(SafeEncoder.encode(key), groupName, consumerName, ids, minIdleTime,
				new LettuceXClaimArgs(xClaimArgument), args);
	}

	@Override
	public List<StreamEntryId> xClaimJustId(final byte[] key, final byte[] groupName, final byte[] consumerName,
	                                        final int minIdleTime, final StreamEntryId[] ids,
	                                        final XClaimArgument xClaimArgument) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(consumerName).add(minIdleTime)
				.add(ids).add(xClaimArgument).add("JUSTID");
		return xClaimJustId(key, groupName, consumerName, ids, minIdleTime,
				new LettuceXClaimArgs(xClaimArgument), args);
	}

	@Override
	public Long xDel(final String key, final StreamEntryId... ids) {
		final CommandArguments args = CommandArguments.create(key).add(ids);
		return executeCommand(RedisCommand.XDEL, args, (cmd)->cmd.xdel(SafeEncoder.encode(key), messageIds(ids)),
				(cmd)->cmd.xdel(SafeEncoder.encode(key), messageIds(ids)));
	}

	@Override
	public Long xDel(final byte[] key, final StreamEntryId... ids) {
		final CommandArguments args = CommandArguments.create(key).add(ids);
		return executeCommand(RedisCommand.XDEL, args, (cmd)->cmd.xdel(key, messageIds(ids)),
				(cmd)->cmd.xdel(key, messageIds(ids)));
	}

	@Override
	public List<StreamEntryDeletionResult> xDelEx(final String key, final StreamDeletionPolicy deletionPolicy,
	                                              final StreamEntryId... ids) {
		final CommandArguments args = CommandArguments.create(key).add(deletionPolicy).add("IDS", ids.length).add(ids);
		return xDelEx(SafeEncoder.encode(key), ids, deletionPolicy, args);
	}

	@Override
	public List<StreamEntryDeletionResult> xDelEx(final byte[] key, final StreamDeletionPolicy deletionPolicy,
	                                              final StreamEntryId... ids) {
		final CommandArguments args = CommandArguments.create(key).add(deletionPolicy).add("IDS", ids.length).add(ids);
		return xDelEx(key, ids, deletionPolicy, args);
	}

	@Override
	public Status xGroupCreate(final String key, final String groupName, final StreamEntryId id) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(id);
		return xGroupCreate(SafeEncoder.encode(key), SafeEncoder.encode(groupName), id, args);
	}

	@Override
	public Status xGroupCreate(final byte[] key, final byte[] groupName, final StreamEntryId id) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(id);
		return xGroupCreate(key, groupName, id, args);
	}

	@Override
	public Status xGroupCreate(final String key, final String groupName, final StreamEntryId id,
	                           final boolean makeStream) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(id)
				.add(makeStream ? "MKSTREAM" : null);
		return xGroupCreate(SafeEncoder.encode(key), SafeEncoder.encode(groupName), id,
				new LettuceXGroupCreateArgs(makeStream), args);
	}

	@Override
	public Status xGroupCreate(final byte[] key, final byte[] groupName, final StreamEntryId id,
	                           final boolean makeStream) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(id)
				.add(makeStream ? "MKSTREAM" : null);
		return xGroupCreate(key, groupName, id, new LettuceXGroupCreateArgs(makeStream), args);
	}

	@Override
	public Status xGroupCreate(final String key, final String groupName, final StreamEntryId id,
	                           final boolean makeStream, final long entriesRead) {
		final CommandArguments args = CommandArguments.create(key).add(groupName).add(id)
				.add(makeStream ? "MKSTREAM" : null).add("ENTRIESREAD", entriesRead);
		return xGroupCreate(SafeEncoder.encode(key), SafeEncoder.encode(groupName), id,
				new LettuceXGroupCreateArgs(makeStream, entriesRead), args);
	}

	@Override
	public Status xGroupCreate(final byte[] key, final byte[] groupName, final StreamEntryId id,
	                           final boolean makeStream, final long entriesRead) {
		final CommandArguments args = CommandArguments.create(key).add(groupName).add(id)
				.add(makeStream ? "MKSTREAM" : null).add("ENTRIESREAD", entriesRead);
		return xGroupCreate(key, groupName, id, new LettuceXGroupCreateArgs(makeStream, entriesRead), args);
	}

	@Override
	public Status xGroupCreate(final String key, final String groupName, final StreamEntryId id,
	                           final long entriesRead) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(id).add("ENTRIESREAD", entriesRead);
		return xGroupCreate(SafeEncoder.encode(key), SafeEncoder.encode(groupName), id,
				new LettuceXGroupCreateArgs(entriesRead), args);
	}

	@Override
	public Status xGroupCreate(final byte[] key, final byte[] groupName, final StreamEntryId id,
	                           final long entriesRead) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(id).add("ENTRIESREAD", entriesRead);
		return xGroupCreate(key, groupName, id, new LettuceXGroupCreateArgs(entriesRead), args);
	}

	@Override
	public Status xGroupCreateConsumer(final String key, final String groupName, final String consumerName) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(consumerName);
		return xGroupCreateConsumer(SafeEncoder.encode(key), SafeEncoder.encode(groupName),
				SafeEncoder.encode(consumerName),
				args);
	}

	@Override
	public Status xGroupCreateConsumer(final byte[] key, final byte[] groupName, final byte[] consumerName) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(consumerName);
		return xGroupCreateConsumer(key, groupName, consumerName, args);
	}

	@Override
	public Long xGroupDelConsumer(final String key, final String groupName, final String consumerName) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(consumerName);
		return xGroupDelConsumer(SafeEncoder.encode(key), SafeEncoder.encode(groupName),
				SafeEncoder.encode(consumerName),
				args);
	}

	@Override
	public Long xGroupDelConsumer(final byte[] key, final byte[] groupName, final byte[] consumerName) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(consumerName);
		return xGroupDelConsumer(key, groupName, consumerName, args);
	}

	@Override
	public Status xGroupDestroy(final String key, final String groupName) {
		final CommandArguments args = CommandArguments.create(key, groupName);
		return executeCommand(RedisCommand.XGROUP, RedisSubCommand.XGROUP_DESTROY, args,
				(cmd)->cmd.xgroupDestroy(SafeEncoder.encode(key), SafeEncoder.encode(groupName)),
				(cmd)->cmd.xgroupDestroy(SafeEncoder.encode(key), SafeEncoder.encode(groupName)),
				new BooleanStatusConverter());
	}

	@Override
	public Status xGroupDestroy(final byte[] key, final byte[] groupName) {
		final CommandArguments args = CommandArguments.create(key, groupName);
		return executeCommand(RedisCommand.XGROUP, RedisSubCommand.XGROUP_DESTROY, args,
				(cmd)->cmd.xgroupDestroy(key, groupName), (cmd)->cmd.xgroupDestroy(key, groupName),
				new BooleanStatusConverter());
	}

	@Override
	public Status xGroupSetId(final String key, final String groupName, final StreamEntryId id) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(id);
		return xGroupSetId(SafeEncoder.encode(key), SafeEncoder.encode(groupName), id, args);
	}

	@Override
	public Status xGroupSetId(final byte[] key, final byte[] groupName, final StreamEntryId id) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(id);
		return xGroupSetId(key, groupName, id, args);
	}

	@Override
	public Status xGroupSetId(final String key, final String groupName, final StreamEntryId id,
	                          final long entriesRead) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(id).add("ENTRIESREAD", entriesRead);
		return xGroupSetId(SafeEncoder.encode(key), SafeEncoder.encode(groupName), id, args);
	}

	@Override
	public Status xGroupSetId(final byte[] key, final byte[] groupName, final StreamEntryId id,
	                          final long entriesRead) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(id).add("ENTRIESREAD", entriesRead);
		return xGroupSetId(key, groupName, id, args);
	}

	@Override
	public List<StreamConsumer> xInfoConsumers(final String key, final String groupName) {
		final CommandArguments args = CommandArguments.create(key, groupName);
		return xInfoConsumers(SafeEncoder.encode(key), SafeEncoder.encode(groupName), args);
	}

	@Override
	public List<StreamConsumer> xInfoConsumers(final byte[] key, final byte[] groupName) {
		final CommandArguments args = CommandArguments.create(key, groupName);
		return xInfoConsumers(key, groupName, args);
	}

	@Override
	public List<StreamGroup> xInfoGroups(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return xInfoGroups(SafeEncoder.encode(key), args);
	}

	@Override
	public List<StreamGroup> xInfoGroups(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return xInfoGroups(key, args);
	}

	@Override
	public Stream<String, String> xInfoStream(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.XINFO, RedisSubCommand.XINFO_STREAM, args,
				(cmd)->cmd.xinfoStream(SafeEncoder.encode(key)), (cmd)->cmd.xinfoStream(SafeEncoder.encode(key)),
				new StreamInfoConverter<>());
	}

	@Override
	public Stream<byte[], byte[]> xInfoStream(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.XINFO, RedisSubCommand.XINFO_STREAM, args,
				(cmd)->cmd.xinfoStream(key), (cmd)->cmd.xinfoStream(key),
				new StreamInfoConverter<>());
	}

	@Override
	public StreamFull<String, String> xInfoStreamFull(final String key) {
		final CommandArguments args = CommandArguments.create(key, "FULL");
		return executeCommand(RedisCommand.XINFO, RedisSubCommand.XINFO_STREAM, args,
				(cmd)->cmd.xinfoStream(SafeEncoder.encode(key)), (cmd)->cmd.xinfoStream(SafeEncoder.encode(key)),
				new StreamFullInfoConverter<>());
	}

	@Override
	public StreamFull<byte[], byte[]> xInfoStreamFull(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key, "FULL");
		return executeCommand(RedisCommand.XINFO, RedisSubCommand.XINFO_STREAM, args,
				(cmd)->cmd.xinfoStream(key), (cmd)->cmd.xinfoStream(key),
				new StreamFullInfoConverter<>());
	}

	@Override
	public StreamFull<String, String> xInfoStreamFull(final String key, final int count) {
		final CommandArguments args = CommandArguments.create(key, "FULL").add(Keyword.Common.COUNT, count);
		return executeCommand(RedisCommand.XINFO, RedisSubCommand.XINFO_STREAM, args,
				(cmd)->cmd.xinfoStream(SafeEncoder.encode(key)), (cmd)->cmd.xinfoStream(SafeEncoder.encode(key)),
				new StreamFullInfoConverter<>());
	}

	@Override
	public StreamFull<byte[], byte[]> xInfoStreamFull(final byte[] key, final int count) {
		final CommandArguments args = CommandArguments.create(key, "FULL").add(Keyword.Common.COUNT, count);
		return executeCommand(RedisCommand.XINFO, RedisSubCommand.XINFO_STREAM, args,
				(cmd)->cmd.xinfoStream(key), (cmd)->cmd.xinfoStream(key),
				new StreamFullInfoConverter<>());
	}

	@Override
	public Long xLen(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.XLEN, args, (cmd)->cmd.xlen(SafeEncoder.encode(key)),
				(cmd)->cmd.xlen(SafeEncoder.encode(key)));
	}

	@Override
	public Long xLen(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.XLEN, args, (cmd)->cmd.xlen(key), (cmd)->cmd.xlen(key));
	}

	@Override
	public StreamPendingSummary xPending(final String key, final String groupName) {
		final CommandArguments args = CommandArguments.create(key, groupName);
		return executeCommand(RedisCommand.XPENDING, args,
				(cmd)->cmd.xpending(SafeEncoder.encode(key), SafeEncoder.encode(groupName)),
				(cmd)->cmd.xpending(SafeEncoder.encode(key), SafeEncoder.encode(groupName)),
				new PendingMessagesConverter());
	}

	@Override
	public StreamPendingSummary xPending(final byte[] key, final byte[] groupName) {
		final CommandArguments args = CommandArguments.create(key, groupName);
		return executeCommand(RedisCommand.XPENDING, args, (cmd)->cmd.xpending(key, groupName),
				(cmd)->cmd.xpending(key, groupName), new PendingMessagesConverter());
	}

	@Override
	public List<StreamPending> xPending(final String key, final String groupName, final StreamEntryId start,
	                                    final StreamEntryId end, final int count) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(start, end).add(count);
		return xPending(SafeEncoder.encode(key),
				new LettuceXPendingArgs<>(start, end, count, SafeEncoder.encode(groupName)),
				args);
	}

	@Override
	public List<StreamPending> xPending(final byte[] key, final byte[] groupName, final StreamEntryId start,
	                                    final StreamEntryId end, final int count) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(start, end).add(count);
		return xPending(key, new LettuceXPendingArgs<>(start, end, count, groupName), args);
	}

	@Override
	public List<StreamPending> xPending(final String key, final String groupName, final long minIdleTime,
	                                    final StreamEntryId start, final StreamEntryId end, final int count) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(Keyword.Time.IDLE, minIdleTime)
				.add(start, end).add(count);
		return xPending(SafeEncoder.encode(key),
				new LettuceXPendingArgs<>(start, end, count, minIdleTime, SafeEncoder.encode(groupName)), args);
	}

	@Override
	public List<StreamPending> xPending(final byte[] key, final byte[] groupName, final long minIdleTime,
	                                    final StreamEntryId start, final StreamEntryId end, final int count) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(Keyword.Time.IDLE, minIdleTime)
				.add(start, end).add(count);
		return xPending(key, new LettuceXPendingArgs<>(start, end, count, minIdleTime, groupName), args);
	}

	@Override
	public List<StreamPending> xPending(final String key, final String groupName, final StreamEntryId start,
	                                    final StreamEntryId end, final int count, final String consumerName) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(start, end).add(count)
				.add(consumerName);
		return xPending(SafeEncoder.encode(key),
				new LettuceXPendingArgs<>(start, end, count, SafeEncoder.encode(groupName),
						SafeEncoder.encode(consumerName)), args);
	}

	@Override
	public List<StreamPending> xPending(final byte[] key, final byte[] groupName, final StreamEntryId start,
	                                    final StreamEntryId end, final int count, final byte[] consumerName) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(start, end).add(count)
				.add(consumerName);
		return xPending(key, new LettuceXPendingArgs<>(start, end, count, groupName, consumerName), args);
	}

	@Override
	public List<StreamPending> xPending(final String key, final String groupName, final long minIdleTime,
	                                    final StreamEntryId start, final StreamEntryId end, final int count,
	                                    final String consumerName) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(Keyword.Time.IDLE, minIdleTime)
				.add(start, end).add(count).add(consumerName);
		return xPending(SafeEncoder.encode(key),
				new LettuceXPendingArgs<>(start, end, count, minIdleTime, SafeEncoder.encode(groupName),
						SafeEncoder.encode(consumerName)), args);
	}

	@Override
	public List<StreamPending> xPending(final byte[] key, final byte[] groupName, final long minIdleTime,
	                                    final StreamEntryId start, final StreamEntryId end, final int count,
	                                    final byte[] consumerName) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(Keyword.Time.IDLE, minIdleTime)
				.add(start, end).add(count).add(consumerName);
		return xPending(key, new LettuceXPendingArgs<>(start, end, count, minIdleTime, groupName, consumerName),
				args);
	}

	@Override
	public List<StreamEntry<String, String>> xRange(final String key, final StreamEntryId start,
	                                                final StreamEntryId end) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end);
		return executeCommand(RedisCommand.XRANGE, args,
				(cmd)->cmd.xrange(SafeEncoder.encode(key), Range.create(start.toString(), end.toString())),
				(cmd)->cmd.xrange(SafeEncoder.encode(key), Range.create(start.toString(), end.toString())),
				new ListConverter<>(new StreamMessageConverter<>(SafeEncoder::encode, SafeEncoder::encode)));
	}

	@Override
	public List<StreamEntry<byte[], byte[]>> xRange(final byte[] key, final StreamEntryId start,
	                                                final StreamEntryId end) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end);
		return executeCommand(RedisCommand.XRANGE, args,
				(cmd)->cmd.xrange(key, Range.create(start.toString(), end.toString())),
				(cmd)->cmd.xrange(key, Range.create(start.toString(), end.toString())),
				new ListConverter<>(new StreamMessageConverter<>((k)->k, (v)->v)));
	}

	@Override
	public List<StreamEntry<String, String>> xRange(final String key, final StreamEntryId start,
	                                                final StreamEntryId end, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end);
		return executeCommand(RedisCommand.XRANGE, args,
				(cmd)->cmd.xrange(SafeEncoder.encode(key), Range.create(start.toString(), end.toString()),
						Limit.from(count)),
				(cmd)->cmd.xrange(SafeEncoder.encode(key), Range.create(start.toString(), end.toString()),
						Limit.from(count)),
				new ListConverter<>(new StreamMessageConverter<>(SafeEncoder::encode, SafeEncoder::encode)));
	}

	@Override
	public List<StreamEntry<byte[], byte[]>> xRange(final byte[] key, final StreamEntryId start,
	                                                final StreamEntryId end, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end).add(Keyword.Common.COUNT, count);
		return executeCommand(RedisCommand.XRANGE, args,
				(cmd)->cmd.xrange(key, Range.create(start.toString(), end.toString()), Limit.from(count)),
				(cmd)->cmd.xrange(key, Range.create(start.toString(), end.toString()), Limit.from(count)),
				new ListConverter<>(new StreamMessageConverter<>((k)->k, (v)->v)));
	}

	@SuppressWarnings({"unchecked"})
	@Override
	public List<XReadInfo<String, String>> xRead(final Map<String, StreamEntryId> streams) {
		final CommandArguments args = CommandArguments.create().add("STREAMS", streams);
		final XReadArgs.StreamOffset<byte[]>[] streamOffsets = new XReadArgs.StreamOffset[streams.size()];
		int i = 0;

		for(Map.Entry<String, StreamEntryId> entry : streams.entrySet()){
			streamOffsets[i++] = XReadArgs.StreamOffset.from(SafeEncoder.encode(entry.getKey()),
					entry.getValue().toString());
		}

		return executeCommand(RedisCommand.XREAD, args, (cmd)->cmd.xread(streamOffsets),
				(cmd)->cmd.xread(streamOffsets),
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
		return executeCommand(RedisCommand.XREVRANGE, args,
				(cmd)->cmd.xrevrange(SafeEncoder.encode(key), Range.create(start.toString(), end.toString())),
				(cmd)->cmd.xrevrange(SafeEncoder.encode(key), Range.create(start.toString(), end.toString())),
				new ListConverter<>(new StreamMessageConverter<>(SafeEncoder::encode, SafeEncoder::encode)));
	}

	@Override
	public List<StreamEntry<byte[], byte[]>> xRevRange(final byte[] key, final StreamEntryId end,
	                                                   final StreamEntryId start) {
		final CommandArguments args = CommandArguments.create(key).add(end, start);
		return executeCommand(RedisCommand.XREVRANGE, args,
				(cmd)->cmd.xrevrange(key, Range.create(start.toString(), end.toString())),
				(cmd)->cmd.xrevrange(key, Range.create(start.toString(), end.toString())),
				new ListConverter<>(new StreamMessageConverter<>((k)->k, (v)->v)));
	}

	@Override
	public List<StreamEntry<String, String>> xRevRange(final String key, final StreamEntryId end,
	                                                   final StreamEntryId start, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(end, start).add(Keyword.Common.COUNT, count);
		return executeCommand(RedisCommand.XREVRANGE, args,
				(cmd)->cmd.xrevrange(SafeEncoder.encode(key), Range.create(start.toString(), end.toString()),
						Limit.from(count)),
				(cmd)->cmd.xrevrange(SafeEncoder.encode(key), Range.create(start.toString(), end.toString()),
						Limit.from(count)),
				new ListConverter<>(new StreamMessageConverter<>(SafeEncoder::encode, SafeEncoder::encode)));
	}

	@Override
	public List<StreamEntry<byte[], byte[]>> xRevRange(final byte[] key, final StreamEntryId end,
	                                                   final StreamEntryId start, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(end, start).add(Keyword.Common.COUNT, count);
		return executeCommand(RedisCommand.XREVRANGE, args,
				(cmd)->cmd.xrevrange(key, Range.create(start.toString(), end.toString()), Limit.from(count)),
				(cmd)->cmd.xrevrange(key, Range.create(start.toString(), end.toString()), Limit.from(count)),
				new ListConverter<>(new StreamMessageConverter<>((k)->k, (v)->v)));
	}

	@Override
	public Status xSetId(final String key, final StreamEntryId lastId) {
		final CommandArguments args = CommandArguments.create(key, lastId);
		return executeCommand(RedisCommand.XSETID, args);
	}

	@Override
	public Status xSetId(final byte[] key, final StreamEntryId lastId) {
		final CommandArguments args = CommandArguments.create(key, lastId);
		return executeCommand(RedisCommand.XSETID, args);
	}

	@Override
	public Status xSetId(final String key, final StreamEntryId lastId, final long entriesAdded) {
		final CommandArguments args = CommandArguments.create(key, lastId).add("ENTRIESADDED", entriesAdded);
		return executeCommand(RedisCommand.XSETID, args);
	}

	@Override
	public Status xSetId(final byte[] key, final StreamEntryId lastId, final long entriesAdded) {
		final CommandArguments args = CommandArguments.create(key, lastId).add("ENTRIESADDED", entriesAdded);
		return executeCommand(RedisCommand.XSETID, args);
	}

	@Override
	public Status xSetId(final String key, final StreamEntryId lastId, final long entriesAdded,
	                     final StreamEntryId maxDeletedId) {
		final CommandArguments args = CommandArguments.create(key, lastId).add("ENTRIESADDED", entriesAdded)
				.add("MAXDELETEDID", maxDeletedId);
		return executeCommand(RedisCommand.XSETID, args);
	}

	@Override
	public Status xSetId(final byte[] key, final StreamEntryId lastId, final long entriesAdded,
	                     final StreamEntryId maxDeletedId) {
		final CommandArguments args = CommandArguments.create(key, lastId).add("ENTRIESADDED", entriesAdded)
				.add("MAXDELETEDID", maxDeletedId);
		return executeCommand(RedisCommand.XSETID, args);
	}

	@Override
	public Status xSetId(final String key, final StreamEntryId lastId, final StreamEntryId maxDeletedId) {
		final CommandArguments args = CommandArguments.create(key, lastId).add("MAXDELETEDID", maxDeletedId);
		return executeCommand(RedisCommand.XSETID, args);
	}

	@Override
	public Status xSetId(final byte[] key, final StreamEntryId lastId, final StreamEntryId maxDeletedId) {
		final CommandArguments args = CommandArguments.create(key, lastId).add("MAXDELETEDID", maxDeletedId);
		return executeCommand(RedisCommand.XSETID, args);
	}

	@Override
	public Long xTrim(final String key, final MaxLenMinId<?> maxLenMinId) {
		final CommandArguments args = CommandArguments.create(key).add(maxLenMinId);
		return xTrim(SafeEncoder.encode(key), new LettuceXTrimArgs(maxLenMinId), args);
	}

	@Override
	public Long xTrim(final byte[] key, final MaxLenMinId<?> maxLenMinId) {
		final CommandArguments args = CommandArguments.create(key).add(maxLenMinId);
		return xTrim(key, new LettuceXTrimArgs(maxLenMinId), args);
	}

	@Override
	public Long xTrim(final String key, final ApproximateExactTrimming approximateExactTrimming,
	                  final MaxLenMinId<?> maxLenMinId) {
		final CommandArguments args = CommandArguments.create(key).add(maxLenMinId.getType())
				.add(approximateExactTrimming).add(maxLenMinId.getThreshold());
		return xTrim(SafeEncoder.encode(key), new LettuceXTrimArgs(maxLenMinId, approximateExactTrimming), args);
	}

	@Override
	public Long xTrim(final byte[] key, final ApproximateExactTrimming approximateExactTrimming,
	                  final MaxLenMinId<?> maxLenMinId) {
		final CommandArguments args = CommandArguments.create(key).add(maxLenMinId.getType())
				.add(approximateExactTrimming).add(maxLenMinId.getThreshold());
		return xTrim(key, new LettuceXTrimArgs(maxLenMinId, approximateExactTrimming), args);
	}

	@Override
	public Long xTrim(final String key, final ApproximateExactTrimming approximateExactTrimming,
	                  final MaxLenMinId<?> maxLenMinId, final StreamDeletionPolicy deletionPolicy) {
		final CommandArguments args = CommandArguments.create(key).add(maxLenMinId.getType())
				.add(approximateExactTrimming).add(maxLenMinId.getThreshold()).add(deletionPolicy);
		return xTrim(SafeEncoder.encode(key),
				new LettuceXTrimArgs(maxLenMinId, approximateExactTrimming).deletionPolicy(deletionPolicy), args);
	}

	@Override
	public Long xTrim(final byte[] key, final ApproximateExactTrimming approximateExactTrimming,
	                  final MaxLenMinId<?> maxLenMinId, final StreamDeletionPolicy deletionPolicy) {
		final CommandArguments args = CommandArguments.create(key).add(maxLenMinId.getType())
				.add(approximateExactTrimming).add(maxLenMinId.getThreshold()).add(deletionPolicy);
		return xTrim(key,
				new LettuceXTrimArgs(maxLenMinId, approximateExactTrimming).deletionPolicy(deletionPolicy), args);
	}

	@Override
	public Long xTrim(final String key, final MaxLenMinId<?> maxLenMinId, final StreamDeletionPolicy deletionPolicy) {
		final CommandArguments args = CommandArguments.create(key).add(maxLenMinId).add(deletionPolicy);
		return xTrim(SafeEncoder.encode(key), new LettuceXTrimArgs(maxLenMinId).deletionPolicy(deletionPolicy), args);
	}

	@Override
	public Long xTrim(final byte[] key, final MaxLenMinId<?> maxLenMinId, final StreamDeletionPolicy deletionPolicy) {
		final CommandArguments args = CommandArguments.create(key).add(maxLenMinId).add(deletionPolicy);
		return xTrim(key, new LettuceXTrimArgs(maxLenMinId).deletionPolicy(deletionPolicy), args);
	}

	@Override
	public Long xTrim(final String key, final MaxLenMinId<?> maxLenMinId, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(maxLenMinId).add(Keyword.Common.LIMIT, count);
		return xTrim(SafeEncoder.encode(key), new LettuceXTrimArgs(maxLenMinId, count), args);
	}

	@Override
	public Long xTrim(final byte[] key, final MaxLenMinId<?> maxLenMinId, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(maxLenMinId).add(Keyword.Common.LIMIT, count);
		return xTrim(key, new LettuceXTrimArgs(maxLenMinId, count), args);
	}

	@Override
	public Long xTrim(final String key, final ApproximateExactTrimming approximateExactTrimming,
	                  final MaxLenMinId<?> maxLenMinId, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(maxLenMinId.getType())
				.add(approximateExactTrimming).add(maxLenMinId.getThreshold()).add(Keyword.Common.LIMIT, count);
		return xTrim(SafeEncoder.encode(key), new LettuceXTrimArgs(maxLenMinId, approximateExactTrimming), args);
	}

	@Override
	public Long xTrim(final byte[] key, final ApproximateExactTrimming approximateExactTrimming,
	                  final MaxLenMinId<?> maxLenMinId, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(maxLenMinId.getType())
				.add(approximateExactTrimming).add(maxLenMinId.getThreshold()).add(Keyword.Common.LIMIT, count);
		return xTrim(key, new LettuceXTrimArgs(maxLenMinId, approximateExactTrimming), args);
	}

	@Override
	public Long xTrim(final String key, final ApproximateExactTrimming approximateExactTrimming,
	                  final MaxLenMinId<?> maxLenMinId, final StreamDeletionPolicy deletionPolicy, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(maxLenMinId.getType())
				.add(approximateExactTrimming).add(maxLenMinId.getThreshold()).add(deletionPolicy)
				.add(Keyword.Common.LIMIT, count);
		return xTrim(SafeEncoder.encode(key), new LettuceXTrimArgs(maxLenMinId, count), args);
	}

	@Override
	public Long xTrim(final byte[] key, final ApproximateExactTrimming approximateExactTrimming,
	                  final MaxLenMinId<?> maxLenMinId, final StreamDeletionPolicy deletionPolicy, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(maxLenMinId.getType())
				.add(approximateExactTrimming).add(maxLenMinId.getThreshold()).add(deletionPolicy)
				.add(Keyword.Common.LIMIT, count);
		return xTrim(key, new LettuceXTrimArgs(maxLenMinId, count), args);
	}

	@Override
	public Long xTrim(final String key, final MaxLenMinId<?> maxLenMinId, final StreamDeletionPolicy deletionPolicy,
	                  final int count) {
		final CommandArguments args = CommandArguments.create(key).add(maxLenMinId).add(deletionPolicy)
				.add(Keyword.Common.LIMIT, count);
		return xTrim(SafeEncoder.encode(key), new LettuceXTrimArgs(maxLenMinId, count).deletionPolicy(deletionPolicy),
				args);
	}

	@Override
	public Long xTrim(final byte[] key, final MaxLenMinId<?> maxLenMinId, final StreamDeletionPolicy deletionPolicy,
	                  final int count) {
		final CommandArguments args = CommandArguments.create(key).add(maxLenMinId).add(deletionPolicy)
				.add(Keyword.Common.LIMIT, count);
		return xTrim(key, new LettuceXTrimArgs(maxLenMinId, count).deletionPolicy(deletionPolicy), args);
	}

	private static String[] messageIds(final StreamEntryId... ids) {
		return Arrays.map(ids, String.class, StreamEntryId::toString);
	}

	private Long xAck(final byte[] key, final byte[] groupName, final StreamEntryId[] ids,
	                  final CommandArguments args) {
		return executeCommand(RedisCommand.XACK, args, (cmd)->cmd.xack(key, groupName, messageIds(ids)),
				(cmd)->cmd.xack(key, groupName, messageIds(ids)));
	}

	private List<StreamEntryDeletionResult> xAckDel(final byte[] key, final byte[] groupName, final StreamEntryId[] ids,
	                                                final CommandArguments args) {
		return executeCommand(RedisCommand.XACKDEL, args, (cmd)->cmd.xackdel(key, groupName, messageIds(ids)),
				(cmd)->cmd.xackdel(key, groupName, messageIds(ids)),
				new ListConverter<>(new StreamEntryDeletionResultConverter()));
	}

	private List<StreamEntryDeletionResult> xAckDel(final byte[] key, final byte[] groupName,
	                                                final StreamDeletionPolicy deletionPolicy,
	                                                final StreamEntryId[] ids, final CommandArguments args) {
		final StreamDeletionPolicyConverter streamDeletionPolicyConverter = new StreamDeletionPolicyConverter();
		final String[] messageIds = Arrays.map(ids, String.class, StreamEntryId::toString);
		return executeCommand(RedisCommand.XACKDEL, args,
				(cmd)->cmd.xackdel(key, groupName, streamDeletionPolicyConverter.convert(deletionPolicy),
						messageIds),
				(cmd)->cmd.xackdel(key, groupName, streamDeletionPolicyConverter.convert(deletionPolicy),
						messageIds),
				new ListConverter<>(new StreamEntryDeletionResultConverter()));
	}

	private StreamEntryId stringXAdd(final byte[] key, final Map<String, String> hash, final XAddArgs xAddArgs,
	                                 final CommandArguments args) {
		final MapConverter<String, String, byte[], byte[]> mapConverter = Converters.stringMapBinaryMapConverter();
		return executeCommand(RedisCommand.XADD, args, (cmd)->cmd.xadd(key, xAddArgs, mapConverter.convert(hash)),
				(cmd)->cmd.xadd(key, xAddArgs, mapConverter.convert(hash)), new StreamEntryIDConverter());
	}

	private StreamEntryId binaryXAdd(final byte[] key, final Map<byte[], byte[]> hash, final XAddArgs xAddArgs,
	                                 final CommandArguments args) {
		return executeCommand(RedisCommand.XADD, args, (cmd)->cmd.xadd(key, xAddArgs, hash),
				(cmd)->cmd.xadd(key, xAddArgs, hash), new StreamEntryIDConverter());
	}

	private AutoClaimInfo<String, String> stringXAutoClaim(final byte[] key,
	                                                       final XAutoClaimArgs<byte[]> xAutoClaimArgs,
	                                                       final CommandArguments args) {
		return executeCommand(RedisCommand.XAUTOCLAIM, args, (cmd)->cmd.xautoclaim(key, xAutoClaimArgs),
				(cmd)->cmd.xautoclaim(key, xAutoClaimArgs),
				new ClaimedMessagesAutoClaimInfoConverter<>(SafeEncoder::encode, SafeEncoder::encode));
	}

	private AutoClaimInfo<byte[], byte[]> binaryXAutoClaim(final byte[] key,
	                                                       final XAutoClaimArgs<byte[]> xAutoClaimArgs,
	                                                       final CommandArguments args) {
		return executeCommand(RedisCommand.XAUTOCLAIM, args, (cmd)->cmd.xautoclaim(key, xAutoClaimArgs),
				(cmd)->cmd.xautoclaim(key, xAutoClaimArgs),
				new ClaimedMessagesAutoClaimInfoConverter<>((k)->k, (v)->v));
	}

	private AutoClaimId xAutoClaimJustId(final byte[] key, final XAutoClaimArgs<byte[]> xAutoClaimArgs,
	                                     final CommandArguments args) {
		xAutoClaimArgs.justid();
		return executeCommand(RedisCommand.XAUTOCLAIM, args, (cmd)->cmd.xautoclaim(key, xAutoClaimArgs),
				(cmd)->cmd.xautoclaim(key, xAutoClaimArgs), new ClaimedMessagesAutoClaimIdConverter<>());
	}

	private List<StreamEntry<String, String>> xClaim(final byte[] key, final String groupName,
	                                                 final String consumerName, final StreamEntryId[] ids,
	                                                 final int minIdleTime, final XClaimArgs xClaimArgs,
	                                                 final CommandArguments args) {
		xClaimArgs.minIdleTime(minIdleTime);
		return executeCommand(RedisCommand.XCLAIM, args,
				(cmd)->cmd.xclaim(key, Consumer.from(SafeEncoder.encode(groupName), SafeEncoder.encode(consumerName)),
						xClaimArgs, messageIds(ids)),
				(cmd)->cmd.xclaim(key, Consumer.from(SafeEncoder.encode(groupName), SafeEncoder.encode(consumerName)),
						xClaimArgs, messageIds(ids)),
				new ListConverter<>(new StreamMessageConverter<>(SafeEncoder::encode, SafeEncoder::encode)));
	}

	private List<StreamEntry<byte[], byte[]>> xClaim(final byte[] key, final byte[] groupName,
	                                                 final byte[] consumerName, final StreamEntryId[] ids,
	                                                 final int minIdleTime, final XClaimArgs xClaimArgs,
	                                                 final CommandArguments args) {
		xClaimArgs.minIdleTime(minIdleTime);
		return executeCommand(RedisCommand.XCLAIM, args,
				(cmd)->cmd.xclaim(key, Consumer.from(groupName, consumerName), xClaimArgs, messageIds(ids)),
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
		return executeCommand(RedisCommand.XCLAIM, args,
				(cmd)->cmd.xclaim(key, Consumer.from(groupName, consumerName), xClaimArgs, messageIds(ids)),
				(cmd)->cmd.xclaim(key, Consumer.from(groupName, consumerName), xClaimArgs, messageIds(ids)),
				new ListConverter<>((m)->m == null ? null : new StreamEntryId(m.getId())));
	}

	private List<StreamEntryDeletionResult> xDelEx(final byte[] key, final StreamEntryId[] ids,
	                                               final StreamDeletionPolicy deletionPolicy,
	                                               final CommandArguments args) {
		final StreamDeletionPolicyConverter streamDeletionPolicyConverter = new StreamDeletionPolicyConverter();
		return executeCommand(RedisCommand.XDELEX, args,
				(cmd)->cmd.xdelex(key, streamDeletionPolicyConverter.convert(deletionPolicy),
						Arrays.map(ids, String.class, StreamEntryId::toString)),
				(cmd)->cmd.xdelex(key, streamDeletionPolicyConverter.convert(deletionPolicy),
						Arrays.map(ids, String.class, StreamEntryId::toString)),
				new ListConverter<>(new StreamEntryDeletionResultConverter()));
	}

	private Status xGroupCreate(final byte[] key, final byte[] groupName, final StreamEntryId id,
	                            final CommandArguments args) {
		final XReadArgs.StreamOffset<byte[]> streamOffset = XReadArgs.StreamOffset.from(key, id.toString());
		return executeCommand(RedisCommand.XGROUP, RedisSubCommand.XGROUP_CREATE, args,
				(cmd)->cmd.xgroupCreate(streamOffset, groupName),
				(cmd)->cmd.xgroupCreate(streamOffset, groupName),
				new OkStatusConverter());
	}

	private Status xGroupCreate(final byte[] key, final byte[] groupName, final StreamEntryId id,
	                            final LettuceXGroupCreateArgs xGroupCreateArgs, final CommandArguments args) {
		final XReadArgs.StreamOffset<byte[]> streamOffset = XReadArgs.StreamOffset.from(key, id.toString());
		return executeCommand(RedisCommand.XGROUP, RedisSubCommand.XGROUP_CREATE, args,
				(cmd)->cmd.xgroupCreate(streamOffset, groupName, xGroupCreateArgs),
				(cmd)->cmd.xgroupCreate(streamOffset, groupName, xGroupCreateArgs),
				new OkStatusConverter());
	}

	private Status xGroupCreateConsumer(final byte[] key, final byte[] groupName, final byte[] consumerName,
	                                    final CommandArguments args) {
		return executeCommand(RedisCommand.XGROUP, RedisSubCommand.XGROUP_CREATECONSUMER, args,
				(cmd)->cmd.xgroupCreateconsumer(key, Consumer.from(groupName, consumerName)),
				(cmd)->cmd.xgroupCreateconsumer(key, Consumer.from(groupName, consumerName)),
				new BooleanStatusConverter());
	}

	private Long xGroupDelConsumer(final byte[] key, final byte[] groupName, final byte[] consumerName,
	                               final CommandArguments args) {
		return executeCommand(RedisCommand.XGROUP, RedisSubCommand.XGROUP_DELCONSUMER, args,
				(cmd)->cmd.xgroupDelconsumer(key, Consumer.from(groupName, consumerName)),
				(cmd)->cmd.xgroupDelconsumer(key, Consumer.from(groupName, consumerName)));
	}

	private Status xGroupSetId(final byte[] key, final byte[] groupName, final StreamEntryId id,
	                           final CommandArguments args) {
		final XReadArgs.StreamOffset<byte[]> streamOffset = XReadArgs.StreamOffset.from(key, id.toString());
		return executeCommand(RedisCommand.XGROUP, RedisSubCommand.XGROUP_SETID, args,
				(cmd)->cmd.xgroupSetid(streamOffset, groupName),
				(cmd)->cmd.xgroupSetid(streamOffset, groupName),
				new OkStatusConverter());
	}

	private List<StreamConsumer> xInfoConsumers(final byte[] key, final byte[] groupName, final CommandArguments args) {
		return executeCommand(RedisCommand.XINFO, RedisSubCommand.XINFO_CONSUMERS, args,
				(cmd)->cmd.xinfoConsumers(key, groupName), (cmd)->cmd.xinfoConsumers(key, groupName),
				new ListConverter<>(new StreamConsumersInfoConverter()));
	}

	private List<StreamGroup> xInfoGroups(final byte[] key, final CommandArguments args) {
		return executeCommand(RedisCommand.XINFO, RedisSubCommand.XINFO_GROUPS, args, (cmd)->cmd.xinfoGroups(key),
				(cmd)->cmd.xinfoGroups(key), new ListConverter<>(new StreamGroupInfoConverter()));
	}

	private List<StreamPending> xPending(final byte[] key, final XPendingArgs<byte[]> xPendingArgs,
	                                     final CommandArguments args) {
		return executeCommand(RedisCommand.XPENDING, args, (cmd)->cmd.xpending(key, xPendingArgs),
				(cmd)->cmd.xpending(key, xPendingArgs), new ListConverter<>(new PendingMessageConverter()));
	}

	@SuppressWarnings({"unchecked"})
	private List<XReadInfo<String, String>> xRead(final Map<String, StreamEntryId> streams, final XReadArgs xReadArgs,
	                                              final CommandArguments args) {
		final XReadArgs.StreamOffset<byte[]>[] streamOffsets = new XReadArgs.StreamOffset[streams.size()];
		int i = 0;

		for(Map.Entry<String, StreamEntryId> entry : streams.entrySet()){
			streamOffsets[i++] = XReadArgs.StreamOffset.from(SafeEncoder.encode(entry.getKey()),
					entry.getValue().toString());
		}

		return executeCommand(RedisCommand.XREAD, args, (cmd)->cmd.xread(xReadArgs, streamOffsets),
				(cmd)->cmd.xread(xReadArgs, streamOffsets),
				new ListConverter<>(new StreamMessageXReadInfoConverter<>(SafeEncoder::encode, SafeEncoder::encode)));
	}

	@SuppressWarnings({"unchecked"})
	private List<XReadGroupInfo<String, String>> xReadGroup(final String groupName, final String consumerName,
	                                                        final Map<String, StreamEntryId> streams,
	                                                        final CommandArguments args) {
		final Consumer<byte[]> consumer = Consumer.from(SafeEncoder.encode(groupName),
				SafeEncoder.encode(consumerName));
		final XReadArgs.StreamOffset<byte[]>[] streamOffsets = new XReadArgs.StreamOffset[streams.size()];
		int i = 0;

		for(Map.Entry<String, StreamEntryId> entry : streams.entrySet()){
			streamOffsets[i++] = XReadArgs.StreamOffset.from(SafeEncoder.encode(entry.getKey()),
					entry.getValue().toString());
		}

		return executeCommand(RedisCommand.XREADGROUP, args,
				(cmd)->cmd.xreadgroup(consumer, streamOffsets),
				(cmd)->cmd.xreadgroup(consumer, streamOffsets),
				new ListConverter<>(
						new StreamMessageXReadGroupInfoConverter<>(SafeEncoder::encode, SafeEncoder::encode)));
	}

	@SuppressWarnings({"unchecked"})
	private List<XReadGroupInfo<byte[], byte[]>> xReadGroup(final byte[] groupName, final byte[] consumerName,
	                                                        final Map<byte[], StreamEntryId> streams,
	                                                        final CommandArguments args) {
		final XReadArgs.StreamOffset<byte[]>[] streamOffsets = new XReadArgs.StreamOffset[streams.size()];
		int i = 0;

		for(Map.Entry<byte[], StreamEntryId> entry : streams.entrySet()){
			streamOffsets[i++] = XReadArgs.StreamOffset.from(entry.getKey(), entry.getValue().toString());
		}

		return executeCommand(RedisCommand.XREADGROUP, args,
				(cmd)->cmd.xreadgroup(Consumer.from(groupName, consumerName), streamOffsets),
				(cmd)->cmd.xreadgroup(Consumer.from(groupName, consumerName), streamOffsets),
				new ListConverter<>(new StreamMessageXReadGroupInfoConverter<>((k)->k, (v)->v)));
	}

	@SuppressWarnings({"unchecked"})
	private List<XReadGroupInfo<String, String>> xReadGroup(final String groupName, final String consumerName,
	                                                        final Map<String, StreamEntryId> streams,
	                                                        final XReadArgs xReadArgs, final CommandArguments args) {
		final XReadArgs.StreamOffset<byte[]>[] streamOffsets = new XReadArgs.StreamOffset[streams.size()];
		int i = 0;

		for(Map.Entry<String, StreamEntryId> entry : streams.entrySet()){
			streamOffsets[i++] = XReadArgs.StreamOffset.from(SafeEncoder.encode(entry.getKey()),
					entry.getValue().toString());
		}

		return executeCommand(RedisCommand.XREADGROUP, args,
				(cmd)->cmd.xreadgroup(Consumer.from(SafeEncoder.encode(groupName), SafeEncoder.encode(consumerName)),
						xReadArgs, streamOffsets),
				(cmd)->cmd.xreadgroup(Consumer.from(SafeEncoder.encode(groupName), SafeEncoder.encode(consumerName)),
						xReadArgs, streamOffsets),
				new ListConverter<>(
						new StreamMessageXReadGroupInfoConverter<>(SafeEncoder::encode, SafeEncoder::encode)));
	}

	@SuppressWarnings({"unchecked"})
	private List<XReadGroupInfo<byte[], byte[]>> xReadGroup(final byte[] groupName, final byte[] consumerName,
	                                                        final Map<byte[], StreamEntryId> streams,
	                                                        final XReadArgs xReadArgs, final CommandArguments args) {
		final XReadArgs.StreamOffset<byte[]>[] streamOffsets = new XReadArgs.StreamOffset[streams.size()];
		int i = 0;

		for(Map.Entry<byte[], StreamEntryId> entry : streams.entrySet()){
			streamOffsets[i++] = XReadArgs.StreamOffset.from(entry.getKey(), entry.getValue().toString());
		}

		return executeCommand(RedisCommand.XREADGROUP, args,
				(cmd)->cmd.xreadgroup(Consumer.from(groupName, consumerName), xReadArgs, streamOffsets),
				(cmd)->cmd.xreadgroup(Consumer.from(groupName, consumerName), xReadArgs, streamOffsets),
				new ListConverter<>(new StreamMessageXReadGroupInfoConverter<>((k)->k, (v)->v)));
	}

	private Long xTrim(final byte[] key, final XTrimArgs xTrimArgs, final CommandArguments args) {
		return executeCommand(RedisCommand.XTRIM, args, (cmd)->cmd.xtrim(key, xTrimArgs),
				(cmd)->cmd.xtrim(key, xTrimArgs));
	}

}
