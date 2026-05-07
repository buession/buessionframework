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
package com.buession.redis.client.jedis.command;

import com.buession.core.collect.Arrays;
import com.buession.core.converter.ArrayConverter;
import com.buession.core.converter.BooleanStatusConverter;
import com.buession.core.converter.Converter;
import com.buession.core.converter.ListConverter;
import com.buession.core.converter.MapConverter;
import com.buession.lang.Status;
import com.buession.redis.client.jedis.JedisRedisClient;
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
import com.buession.redis.core.internal.convert.jedis.params.StreamDeletionPolicyConverter;
import com.buession.redis.core.internal.convert.jedis.params.StreamEntryIdConverter;
import com.buession.redis.core.internal.convert.jedis.response.MapEntryStreamEntryAutoClaimIdConverter;
import com.buession.redis.core.internal.convert.jedis.response.MapEntryStreamEntryAutoClaimInfoConverter;
import com.buession.redis.core.internal.convert.jedis.response.MapEntryStreamEntryXReadGroupInfoConverter;
import com.buession.redis.core.internal.convert.jedis.response.MapEntryStreamEntryXReadInfoConverter;
import com.buession.redis.core.internal.convert.jedis.response.StreamConsumersInfoConverter;
import com.buession.redis.core.internal.convert.jedis.response.StreamEntryConverter;
import com.buession.redis.core.internal.convert.jedis.response.StreamEntryDeletionResultConverter;
import com.buession.redis.core.internal.convert.jedis.response.StreamEntryIDConverter;
import com.buession.redis.core.internal.convert.jedis.response.StreamFullInfoConverter;
import com.buession.redis.core.internal.convert.jedis.response.StreamGroupInfoConverter;
import com.buession.redis.core.internal.convert.jedis.response.StreamInfoConverter;
import com.buession.redis.core.internal.convert.jedis.response.StreamPendingEntryConverter;
import com.buession.redis.core.internal.convert.jedis.response.StreamPendingSummaryConverter;
import com.buession.redis.core.internal.convert.response.OkStatusConverter;
import com.buession.redis.core.internal.convert.response.OneStatusConverter;
import com.buession.redis.core.internal.jedis.args.JedisStreamEntryID;
import com.buession.redis.core.internal.jedis.args.JedisXAddParams;
import com.buession.redis.core.internal.jedis.args.JedisXAutoClaimParams;
import com.buession.redis.core.internal.jedis.args.JedisXClaimParams;
import com.buession.redis.core.internal.jedis.args.JedisXPendingParams;
import com.buession.redis.core.internal.jedis.args.JedisXReadGroupParams;
import com.buession.redis.core.internal.jedis.args.JedisXReadParams;
import com.buession.redis.core.internal.jedis.args.JedisXTrimParams;
import com.buession.redis.utils.SafeEncoder;
import redis.clients.jedis.StreamEntryID;
import redis.clients.jedis.params.XAutoClaimParams;
import redis.clients.jedis.params.XClaimParams;
import redis.clients.jedis.params.XPendingParams;
import redis.clients.jedis.params.XReadGroupParams;
import redis.clients.jedis.params.XReadParams;
import redis.clients.jedis.params.XTrimParams;

import java.util.List;
import java.util.Map;

/**
 * Jedis Stream 命令
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public final class JedisStreamCommands extends AbstractJedisRedisCommands implements StreamCommands {

	public JedisStreamCommands(final JedisRedisClient client) {
		super(client);
	}

	@Override
	public Long xAck(final String key, final String groupName, final StreamEntryId... ids) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(ids);
		return executeCommand(RedisCommand.XACK, args, (cmd)->cmd.xack(key, groupName, streamEntryIds(ids)),
				(cmd)->cmd.xack(key, groupName, streamEntryIds(ids)),
				(cmd)->cmd.xack(key, groupName, streamEntryIds(ids)));
	}

	@Override
	public Long xAck(final byte[] key, final byte[] groupName, final StreamEntryId... ids) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(ids);
		return executeCommand(RedisCommand.XACK, args, (cmd)->cmd.xack(key, groupName, binaryStreamEntryIds(ids)),
				(cmd)->cmd.xack(key, groupName, binaryStreamEntryIds(ids)),
				(cmd)->cmd.xack(key, groupName, binaryStreamEntryIds(ids)));
	}

	@Override
	public List<StreamEntryDeletionResult> xAckDel(final String key, final String groupName,
	                                               final StreamEntryId... ids) {
		final CommandArguments args = CommandArguments.create(key, groupName).add("IDS", ids.length).add(ids);
		return executeCommand(RedisCommand.XACKDEL, args, (cmd)->cmd.xackdel(key, groupName, streamEntryIds(ids)),
				(cmd)->cmd.xackdel(key, groupName, streamEntryIds(ids)),
				(cmd)->cmd.xackdel(key, groupName, streamEntryIds(ids)),
				new ListConverter<>(new StreamEntryDeletionResultConverter()));
	}

	@Override
	public List<StreamEntryDeletionResult> xAckDel(final byte[] key, final byte[] groupName,
	                                               final StreamEntryId... ids) {
		final CommandArguments args = CommandArguments.create(key, groupName).add("IDS", ids.length).add(ids);
		return executeCommand(RedisCommand.XACKDEL, args, (cmd)->cmd.xackdel(key, groupName, binaryStreamEntryIds(ids)),
				(cmd)->cmd.xackdel(key, groupName, binaryStreamEntryIds(ids)),
				(cmd)->cmd.xackdel(key, groupName, binaryStreamEntryIds(ids)),
				new ListConverter<>(new StreamEntryDeletionResultConverter()));
	}

	@Override
	public List<StreamEntryDeletionResult> xAckDel(final String key, final String groupName,
	                                               final StreamDeletionPolicy deletionPolicy,
	                                               final StreamEntryId... ids) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(deletionPolicy).add("IDS", ids.length)
				.add(ids);
		final StreamDeletionPolicyConverter streamDeletionPolicyConverter = new StreamDeletionPolicyConverter();
		return executeCommand(RedisCommand.XACKDEL, args,
				(cmd)->cmd.xackdel(key, groupName, streamDeletionPolicyConverter.convert(deletionPolicy),
						streamEntryIds(ids)),
				(cmd)->cmd.xackdel(key, groupName, streamDeletionPolicyConverter.convert(deletionPolicy),
						streamEntryIds(ids)),
				(cmd)->cmd.xackdel(key, groupName, streamDeletionPolicyConverter.convert(deletionPolicy),
						streamEntryIds(ids)), new ListConverter<>(new StreamEntryDeletionResultConverter()));
	}

	@Override
	public List<StreamEntryDeletionResult> xAckDel(final byte[] key, final byte[] groupName,
	                                               final StreamDeletionPolicy deletionPolicy,
	                                               final StreamEntryId... ids) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(deletionPolicy).add("IDS", ids.length)
				.add(ids);
		final StreamDeletionPolicyConverter streamDeletionPolicyConverter = new StreamDeletionPolicyConverter();
		return executeCommand(RedisCommand.XACKDEL, args,
				(cmd)->cmd.xackdel(key, groupName, streamDeletionPolicyConverter.convert(deletionPolicy),
						binaryStreamEntryIds(ids)),
				(cmd)->cmd.xackdel(key, groupName, streamDeletionPolicyConverter.convert(deletionPolicy),
						binaryStreamEntryIds(ids)),
				(cmd)->cmd.xackdel(key, groupName, streamDeletionPolicyConverter.convert(deletionPolicy),
						binaryStreamEntryIds(ids)), new ListConverter<>(new StreamEntryDeletionResultConverter()));
	}

	@Override
	public StreamEntryId xAdd(final String key, final StreamEntryId id, final Map<String, String> hash) {
		final CommandArguments args = CommandArguments.create(key).add(id).add(hash);
		return xAdd(key, id, hash, args);
	}

	@Override
	public StreamEntryId xAdd(final byte[] key, final StreamEntryId id, final Map<byte[], byte[]> hash) {
		final CommandArguments args = CommandArguments.create(key).add(id).add(hash);
		final MapConverter<byte[], byte[], String, String> mapConverter = Converters.binaryMapStringMapConverter();
		return xAdd(SafeEncoder.encode(key), id, mapConverter.convert(hash), args);
	}

	@Override
	public StreamEntryId xAdd(final String key, final StreamEntryId id, final Map<String, String> hash,
	                          final XAddArgument xAddArgument) {
		final CommandArguments args = CommandArguments.create(key).add(xAddArgument).add(id).add(hash);
		return xAdd(key, id, hash, xAddArgument, args);
	}

	@Override
	public StreamEntryId xAdd(final byte[] key, final StreamEntryId id, final Map<byte[], byte[]> hash,
	                          final XAddArgument xAddArgument) {
		final CommandArguments args = CommandArguments.create(key).add(xAddArgument).add(id).add(hash);
		final MapConverter<byte[], byte[], String, String> mapConverter = Converters.binaryMapStringMapConverter();
		return xAdd(SafeEncoder.encode(key), id, mapConverter.convert(hash), xAddArgument, args);
	}

	@Override
	public AutoClaimInfo<String, String> xAutoClaim(final String key, final String groupName, final String consumerName,
	                                                final int minIdleTime, final StreamEntryId start) {
		final CommandArguments args = CommandArguments.create(key).add(groupName, consumerName).add(minIdleTime)
				.add(start);
		return xAutoClaim(key, groupName, consumerName, minIdleTime, start, new JedisXAutoClaimParams(), (v)->v, args);
	}

	@Override
	public AutoClaimInfo<byte[], byte[]> xAutoClaim(final byte[] key, final byte[] groupName, final byte[] consumerName,
	                                                final int minIdleTime, final StreamEntryId start) {
		final CommandArguments args = CommandArguments.create(key).add(groupName, consumerName).add(minIdleTime)
				.add(start);
		return xAutoClaim(SafeEncoder.encode(key), SafeEncoder.encode(groupName), SafeEncoder.encode(consumerName),
				minIdleTime, start, new JedisXAutoClaimParams(), SafeEncoder::encode, args);
	}

	@Override
	public AutoClaimInfo<String, String> xAutoClaim(final String key, final String groupName, final String consumerName,
	                                                final int minIdleTime, final StreamEntryId start, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(groupName, consumerName).add(minIdleTime)
				.add(start).add(Keyword.Common.COUNT, count);
		return xAutoClaim(key, groupName, consumerName, minIdleTime, start, new JedisXAutoClaimParams(count), (v)->v,
				args);
	}

	@Override
	public AutoClaimInfo<byte[], byte[]> xAutoClaim(final byte[] key, final byte[] groupName, final byte[] consumerName,
	                                                final int minIdleTime, final StreamEntryId start, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(groupName, consumerName).add(minIdleTime)
				.add(start).add(Keyword.Common.COUNT, count);
		return xAutoClaim(SafeEncoder.encode(key), SafeEncoder.encode(groupName), SafeEncoder.encode(consumerName),
				minIdleTime, start, new JedisXAutoClaimParams(count), SafeEncoder::encode, args);
	}

	@Override
	public AutoClaimId xAutoClaimJustId(final String key, final String groupName, final String consumerName,
	                                    final int minIdleTime, final StreamEntryId start) {
		final CommandArguments args = CommandArguments.create(key).add(groupName, consumerName).add(minIdleTime)
				.add(start).add("JUSTID");
		return xAutoClaimJustId(key, groupName, consumerName, minIdleTime, start, new JedisXAutoClaimParams(), args);
	}

	@Override
	public AutoClaimId xAutoClaimJustId(final byte[] key, final byte[] groupName, final byte[] consumerName,
	                                    final int minIdleTime, final StreamEntryId start) {
		final CommandArguments args = CommandArguments.create(key).add(groupName, consumerName).add(minIdleTime)
				.add(start).add("JUSTID");
		return xAutoClaimJustId(SafeEncoder.encode(key), groupName, consumerName, minIdleTime, start,
				new JedisXAutoClaimParams(), args);
	}

	@Override
	public AutoClaimId xAutoClaimJustId(final String key, final String groupName, final String consumerName,
	                                    final int minIdleTime, final StreamEntryId start, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(groupName, consumerName).add(minIdleTime)
				.add(start).add(Keyword.Common.COUNT, count).add("JUSTID");
		return xAutoClaimJustId(key, groupName, consumerName, minIdleTime, start, new JedisXAutoClaimParams(count),
				args);
	}

	@Override
	public AutoClaimId xAutoClaimJustId(final byte[] key, final byte[] groupName, final byte[] consumerName,
	                                    final int minIdleTime, final StreamEntryId start, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(groupName, consumerName).add(minIdleTime)
				.add(start).add(Keyword.Common.COUNT, count).add("JUSTID");
		return xAutoClaimJustId(SafeEncoder.encode(key), groupName, consumerName, minIdleTime, start,
				new JedisXAutoClaimParams(count), args);
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
		return xClaim(key, groupName, consumerName, minIdleTime, ids, new XClaimParams(), args);
	}

	@Override
	public List<StreamEntry<byte[], byte[]>> xClaim(final byte[] key, final byte[] groupName, final byte[] consumerName,
	                                                final int minIdleTime, final StreamEntryId... ids) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(consumerName).add(minIdleTime)
				.add(ids);
		return xClaim(SafeEncoder.encode(key), groupName, consumerName, minIdleTime, ids, new XClaimParams(), args);
	}

	@Override
	public List<StreamEntry<String, String>> xClaim(final String key, final String groupName, final String consumerName,
	                                                final int minIdleTime, final StreamEntryId[] ids,
	                                                final XClaimArgument xClaimArgument) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(consumerName).add(minIdleTime)
				.add(ids).add(xClaimArgument);
		return xClaim(key, groupName, consumerName, minIdleTime, ids, new JedisXClaimParams(xClaimArgument), args);
	}

	@Override
	public List<StreamEntry<byte[], byte[]>> xClaim(final byte[] key, final byte[] groupName, final byte[] consumerName,
	                                                final int minIdleTime, final StreamEntryId[] ids,
	                                                final XClaimArgument xClaimArgument) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(consumerName).add(minIdleTime)
				.add(ids).add(xClaimArgument);
		return xClaim(SafeEncoder.encode(key), groupName, consumerName, minIdleTime, ids,
				new JedisXClaimParams(xClaimArgument), args);
	}

	@Override
	public List<StreamEntryId> xClaimJustId(final String key, final String groupName, final String consumerName,
	                                        final int minIdleTime, final StreamEntryId... ids) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(consumerName).add(minIdleTime)
				.add(ids).add("JUSTID");
		return xClaimJustId(key, groupName, consumerName, minIdleTime, ids, new XClaimParams(), args);
	}

	@Override
	public List<StreamEntryId> xClaimJustId(final byte[] key, final byte[] groupName, final byte[] consumerName,
	                                        final int minIdleTime, final StreamEntryId... ids) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(consumerName).add(minIdleTime)
				.add(ids).add("JUSTID");
		return xClaimJustId(SafeEncoder.encode(key), groupName, consumerName, minIdleTime, ids, new XClaimParams(),
				args);
	}

	@Override
	public List<StreamEntryId> xClaimJustId(final String key, final String groupName, final String consumerName,
	                                        final int minIdleTime, final StreamEntryId[] ids,
	                                        final XClaimArgument xClaimArgument) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(consumerName).add(minIdleTime)
				.add(ids).add(xClaimArgument).add("JUSTID");
		return xClaimJustId(key, groupName, consumerName, minIdleTime, ids, new JedisXClaimParams(xClaimArgument),
				args);
	}

	@Override
	public List<StreamEntryId> xClaimJustId(final byte[] key, final byte[] groupName, final byte[] consumerName,
	                                        final int minIdleTime, final StreamEntryId[] ids,
	                                        final XClaimArgument xClaimArgument) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(consumerName).add(minIdleTime)
				.add(ids).add(xClaimArgument).add("JUSTID");
		return xClaimJustId(SafeEncoder.encode(key), groupName, consumerName, minIdleTime, ids,
				new JedisXClaimParams(xClaimArgument), args);
	}

	@Override
	public Long xDel(final String key, final StreamEntryId... ids) {
		final CommandArguments args = CommandArguments.create(key).add(ids);
		return executeCommand(RedisCommand.XDEL, args, (cmd)->cmd.xdel(key, streamEntryIds(ids)),
				(cmd)->cmd.xdel(key, streamEntryIds(ids)), (cmd)->cmd.xdel(key, streamEntryIds(ids)));
	}

	@Override
	public Long xDel(final byte[] key, final StreamEntryId... ids) {
		final CommandArguments args = CommandArguments.create(key).add(ids);
		return executeCommand(RedisCommand.XDEL, args, (cmd)->cmd.xdel(SafeEncoder.encode(key), streamEntryIds(ids)),
				(cmd)->cmd.xdel(SafeEncoder.encode(key), streamEntryIds(ids)),
				(cmd)->cmd.xdel(SafeEncoder.encode(key), streamEntryIds(ids)));
	}

	@Override
	public List<StreamEntryDeletionResult> xDelEx(final String key, final StreamDeletionPolicy deletionPolicy,
	                                              final StreamEntryId... ids) {
		final CommandArguments args = CommandArguments.create(key).add(deletionPolicy).add("IDS", ids.length).add(ids);
		return xDelEx(key, ids, deletionPolicy, args);
	}

	@Override
	public List<StreamEntryDeletionResult> xDelEx(final byte[] key, final StreamDeletionPolicy deletionPolicy,
	                                              final StreamEntryId... ids) {
		final CommandArguments args = CommandArguments.create(key).add(deletionPolicy).add("IDS", ids.length).add(ids);
		return xDelEx(SafeEncoder.encode(key), ids, deletionPolicy, args);
	}

	@Override
	public Status xGroupCreate(final String key, final String groupName, final StreamEntryId id) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(id);
		return xGroupCreate(key, groupName, id, false, args);
	}

	@Override
	public Status xGroupCreate(final byte[] key, final byte[] groupName, final StreamEntryId id) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(id);
		return xGroupCreate(key, groupName, id, false, args);
	}

	@Override
	public Status xGroupCreate(final String key, final String groupName, final StreamEntryId id,
	                           final boolean makeStream) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(id)
				.add(makeStream ? "MKSTREAM" : null);
		return xGroupCreate(key, groupName, id, makeStream, args);
	}

	@Override
	public Status xGroupCreate(final byte[] key, final byte[] groupName, final StreamEntryId id,
	                           final boolean makeStream) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(id)
				.add(makeStream ? "MKSTREAM" : null);
		return xGroupCreate(key, groupName, id, makeStream, args);
	}

	@Override
	public Status xGroupCreate(final String key, final String groupName, final StreamEntryId id,
	                           final boolean makeStream, final long entriesRead) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(id)
				.add(makeStream ? "MKSTREAM" : null).add("ENTRIESREAD", entriesRead);
		return xGroupCreate(key, groupName, id, makeStream, args);
	}

	@Override
	public Status xGroupCreate(final byte[] key, final byte[] groupName, final StreamEntryId id,
	                           final boolean makeStream, final long entriesRead) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(id)
				.add(makeStream ? "MKSTREAM" : null).add("ENTRIESREAD", entriesRead);
		return xGroupCreate(key, groupName, id, makeStream, args);
	}

	@Override
	public Status xGroupCreate(final String key, final String groupName, final StreamEntryId id,
	                           final long entriesRead) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(id).add("ENTRIESREAD", entriesRead);
		return xGroupCreate(key, groupName, id, false, args);
	}

	@Override
	public Status xGroupCreate(final byte[] key, final byte[] groupName, final StreamEntryId id,
	                           final long entriesRead) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(id).add("ENTRIESREAD", entriesRead);
		return xGroupCreate(key, groupName, id, false, args);
	}

	@Override
	public Status xGroupCreateConsumer(final String key, final String groupName, final String consumerName) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(consumerName);
		return executeCommand(RedisCommand.XGROUP, RedisSubCommand.XGROUP_CREATECONSUMER, args,
				(cmd)->cmd.xgroupCreateConsumer(key, groupName, consumerName),
				(cmd)->cmd.xgroupCreateConsumer(key, groupName, consumerName),
				(cmd)->cmd.xgroupCreateConsumer(key, groupName, consumerName), new BooleanStatusConverter());
	}

	@Override
	public Status xGroupCreateConsumer(final byte[] key, final byte[] groupName, final byte[] consumerName) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(consumerName);
		return executeCommand(RedisCommand.XGROUP, RedisSubCommand.XGROUP_CREATECONSUMER, args,
				(cmd)->cmd.xgroupCreateConsumer(key, groupName, consumerName),
				(cmd)->cmd.xgroupCreateConsumer(key, groupName, consumerName),
				(cmd)->cmd.xgroupCreateConsumer(key, groupName, consumerName), new BooleanStatusConverter());
	}

	@Override
	public Long xGroupDelConsumer(final String key, final String groupName, final String consumerName) {
		final CommandArguments args = CommandArguments.create(key).add(groupName, consumerName);
		return executeCommand(RedisCommand.XGROUP, RedisSubCommand.XGROUP_DELCONSUMER, args,
				(cmd)->cmd.xgroupDelConsumer(key, groupName, consumerName),
				(cmd)->cmd.xgroupDelConsumer(key, groupName, consumerName),
				(cmd)->cmd.xgroupDelConsumer(key, groupName, consumerName));
	}

	@Override
	public Long xGroupDelConsumer(final byte[] key, final byte[] groupName, final byte[] consumerName) {
		final CommandArguments args = CommandArguments.create(key).add(groupName, consumerName);
		return executeCommand(RedisCommand.XGROUP, RedisSubCommand.XGROUP_DELCONSUMER, args,
				(cmd)->cmd.xgroupDelConsumer(key, groupName, consumerName),
				(cmd)->cmd.xgroupDelConsumer(key, groupName, consumerName),
				(cmd)->cmd.xgroupDelConsumer(key, groupName, consumerName));
	}

	@Override
	public Status xGroupDestroy(final String key, final String groupName) {
		final CommandArguments args = CommandArguments.create(key, groupName);
		return executeCommand(RedisCommand.XGROUP, RedisSubCommand.XGROUP_DESTROY, args,
				(cmd)->cmd.xgroupDestroy(key, groupName), (cmd)->cmd.xgroupDestroy(key, groupName),
				(cmd)->cmd.xgroupDestroy(key, groupName), new OneStatusConverter());
	}

	@Override
	public Status xGroupDestroy(final byte[] key, final byte[] groupName) {
		final CommandArguments args = CommandArguments.create(key, groupName);
		return executeCommand(RedisCommand.XGROUP, RedisSubCommand.XGROUP_DESTROY, args,
				(cmd)->cmd.xgroupDestroy(key, groupName), (cmd)->cmd.xgroupDestroy(key, groupName),
				(cmd)->cmd.xgroupDestroy(key, groupName), new OneStatusConverter());
	}

	@Override
	public Status xGroupSetId(final String key, final String groupName, final StreamEntryId id) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(id);
		return executeCommand(RedisCommand.XGROUP, RedisSubCommand.XGROUP_SETID, args,
				(cmd)->cmd.xgroupSetID(key, groupName, new JedisStreamEntryID(id)),
				(cmd)->cmd.xgroupSetID(key, groupName, new JedisStreamEntryID(id)),
				(cmd)->cmd.xgroupSetID(key, groupName, new JedisStreamEntryID(id)), new OkStatusConverter());
	}

	@Override
	public Status xGroupSetId(final byte[] key, final byte[] groupName, final StreamEntryId id) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(id);
		return executeCommand(RedisCommand.XGROUP, RedisSubCommand.XGROUP_SETID, args,
				(cmd)->cmd.xgroupSetID(key, groupName, id.getRaw()),
				(cmd)->cmd.xgroupSetID(key, groupName, id.getRaw()),
				(cmd)->cmd.xgroupSetID(key, groupName, id.getRaw()), new OkStatusConverter());
	}

	@Override
	public Status xGroupSetId(final String key, final String groupName, final StreamEntryId id,
	                          final long entriesRead) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(id).add("ENTRIESREAD", entriesRead);
		return executeCommand(RedisCommand.XGROUP, RedisSubCommand.XGROUP_SETID, args,
				(cmd)->cmd.xgroupSetID(key, groupName, new JedisStreamEntryID(id)),
				(cmd)->cmd.xgroupSetID(key, groupName, new JedisStreamEntryID(id)),
				(cmd)->cmd.xgroupSetID(key, groupName, new JedisStreamEntryID(id)), new OkStatusConverter());
	}

	@Override
	public Status xGroupSetId(final byte[] key, final byte[] groupName, final StreamEntryId id,
	                          final long entriesRead) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(id).add("ENTRIESREAD", entriesRead);
		return executeCommand(RedisCommand.XGROUP, RedisSubCommand.XGROUP_SETID, args,
				(cmd)->cmd.xgroupSetID(key, groupName, id.getRaw()),
				(cmd)->cmd.xgroupSetID(key, groupName, id.getRaw()),
				(cmd)->cmd.xgroupSetID(key, groupName, id.getRaw()), new OkStatusConverter());
	}

	@Override
	public List<StreamConsumer> xInfoConsumers(final String key, final String groupName) {
		final CommandArguments args = CommandArguments.create(key, groupName);
		return xInfoConsumers(key, groupName, args);
	}

	@Override
	public List<StreamConsumer> xInfoConsumers(final byte[] key, final byte[] groupName) {
		final CommandArguments args = CommandArguments.create(key, groupName);
		return xInfoConsumers(SafeEncoder.encode(key), SafeEncoder.encode(groupName), args);
	}

	@Override
	public List<StreamGroup> xInfoGroups(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return xInfoGroups(key, args);
	}

	@Override
	public List<StreamGroup> xInfoGroups(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return xInfoGroups(SafeEncoder.encode(key), args);
	}

	@Override
	public Stream<String, String> xInfoStream(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.XINFO, RedisSubCommand.XINFO_STREAM, args,
				(cmd)->cmd.xinfoStream(key), (cmd)->cmd.xinfoStream(key),
				(cmd)->cmd.xinfoStream(key), new StreamInfoConverter<>((k)->k, (v)->v));
	}

	@Override
	public Stream<byte[], byte[]> xInfoStream(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.XINFO, RedisSubCommand.XINFO_STREAM, args,
				(cmd)->cmd.xinfoStream(SafeEncoder.encode(key)), (cmd)->cmd.xinfoStream(SafeEncoder.encode(key)),
				(cmd)->cmd.xinfoStream(SafeEncoder.encode(key)),
				new StreamInfoConverter<>(SafeEncoder::encode, SafeEncoder::encode));
	}

	@Override
	public StreamFull<String, String> xInfoStreamFull(final String key) {
		final CommandArguments args = CommandArguments.create(key, "FULL");
		return executeCommand(RedisCommand.XINFO, RedisSubCommand.XINFO_STREAM, args,
				(cmd)->cmd.xinfoStreamFull(key), (cmd)->cmd.xinfoStreamFull(key),
				(cmd)->cmd.xinfoStreamFull(key), new StreamFullInfoConverter<>((k)->k, (v)->v));
	}

	@Override
	public StreamFull<byte[], byte[]> xInfoStreamFull(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key, "FULL");
		return executeCommand(RedisCommand.XINFO, RedisSubCommand.XINFO_STREAM, args,
				(cmd)->cmd.xinfoStreamFull(SafeEncoder.encode(key)),
				(cmd)->cmd.xinfoStreamFull(SafeEncoder.encode(key)),
				(cmd)->cmd.xinfoStreamFull(SafeEncoder.encode(key)),
				new StreamFullInfoConverter<>(SafeEncoder::encode, SafeEncoder::encode));
	}

	@Override
	public StreamFull<String, String> xInfoStreamFull(final String key, final int count) {
		final CommandArguments args = CommandArguments.create(key, "FULL").add(Keyword.Common.COUNT, count);
		return executeCommand(RedisCommand.XINFO, RedisSubCommand.XINFO_STREAM, args,
				(cmd)->cmd.xinfoStreamFull(key, count), (cmd)->cmd.xinfoStreamFull(key, count),
				(cmd)->cmd.xinfoStreamFull(key, count), new StreamFullInfoConverter<>((k)->k, (v)->v));
	}

	@Override
	public StreamFull<byte[], byte[]> xInfoStreamFull(final byte[] key, final int count) {
		final CommandArguments args = CommandArguments.create(key, "FULL").add(Keyword.Common.COUNT, count);
		return executeCommand(RedisCommand.XINFO, RedisSubCommand.XINFO_STREAM, args,
				(cmd)->cmd.xinfoStreamFull(SafeEncoder.encode(key), count),
				(cmd)->cmd.xinfoStreamFull(SafeEncoder.encode(key), count),
				(cmd)->cmd.xinfoStreamFull(SafeEncoder.encode(key), count),
				new StreamFullInfoConverter<>(SafeEncoder::encode, SafeEncoder::encode));
	}

	@Override
	public Long xLen(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.XLEN, args, (cmd)->cmd.xlen(key), (cmd)->cmd.xlen(key),
				(cmd)->cmd.xlen(key));
	}

	@Override
	public Long xLen(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.XLEN, args, (cmd)->cmd.xlen(key), (cmd)->cmd.xlen(key),
				(cmd)->cmd.xlen(key));
	}

	@Override
	public StreamPendingSummary xPending(final String key, final String groupName) {
		final CommandArguments args = CommandArguments.create(key, groupName);
		return executeCommand(RedisCommand.XPENDING, args, (cmd)->cmd.xpending(key, groupName),
				(cmd)->cmd.xpending(key, groupName), (cmd)->cmd.xpending(key, groupName),
				new StreamPendingSummaryConverter());
	}

	@Override
	public StreamPendingSummary xPending(final byte[] key, final byte[] groupName) {
		final CommandArguments args = CommandArguments.create(key, groupName);
		return executeCommand(RedisCommand.XPENDING, args,
				(cmd)->cmd.xpending(SafeEncoder.encode(key), SafeEncoder.encode(groupName)),
				(cmd)->cmd.xpending(SafeEncoder.encode(key), SafeEncoder.encode(groupName)),
				(cmd)->cmd.xpending(SafeEncoder.encode(key), SafeEncoder.encode(groupName)),
				new StreamPendingSummaryConverter());
	}

	@Override
	public List<StreamPending> xPending(final String key, final String groupName, final StreamEntryId start,
	                                    final StreamEntryId end, final int count) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(start, end).add(count);
		return xPending(key, groupName, new JedisXPendingParams(start, end, count), args);
	}

	@Override
	public List<StreamPending> xPending(final byte[] key, final byte[] groupName, final StreamEntryId start,
	                                    final StreamEntryId end, final int count) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(start, end).add(count);
		return xPending(SafeEncoder.encode(key), SafeEncoder.encode(groupName),
				new JedisXPendingParams(start, end, count), args);
	}

	@Override
	public List<StreamPending> xPending(final String key, final String groupName, final long minIdleTime,
	                                    final StreamEntryId start, final StreamEntryId end, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(groupName).add(Keyword.Time.IDLE, minIdleTime)
				.add(start, end).add(count);
		return xPending(key, groupName, new JedisXPendingParams(start, end, count, minIdleTime), args);
	}

	@Override
	public List<StreamPending> xPending(final byte[] key, final byte[] groupName, final long minIdleTime,
	                                    final StreamEntryId start, final StreamEntryId end, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(groupName).add(Keyword.Time.IDLE, minIdleTime)
				.add(start, end).add(count);
		return xPending(SafeEncoder.encode(key), SafeEncoder.encode(groupName),
				new JedisXPendingParams(start, end, count, minIdleTime), args);
	}

	@Override
	public List<StreamPending> xPending(final String key, final String groupName, final StreamEntryId start,
	                                    final StreamEntryId end, final int count, final String consumerName) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(start, end).add(count)
				.add(consumerName);
		return xPending(key, groupName, new JedisXPendingParams(start, end, count, consumerName), args);
	}

	@Override
	public List<StreamPending> xPending(final byte[] key, final byte[] groupName, final StreamEntryId start,
	                                    final StreamEntryId end, final int count, final byte[] consumerName) {
		final CommandArguments args = CommandArguments.create(key, groupName).add(start, end).add(count)
				.add(consumerName);
		return xPending(SafeEncoder.encode(key), SafeEncoder.encode(groupName),
				new JedisXPendingParams(start, end, count, consumerName), args);
	}

	@Override
	public List<StreamPending> xPending(final String key, final String groupName, final long minIdleTime,
	                                    final StreamEntryId start, final StreamEntryId end, final int count,
	                                    final String consumerName) {
		final CommandArguments args = CommandArguments.create(key).add(groupName).add(Keyword.Time.IDLE, minIdleTime)
				.add(start, end).add(count).add(consumerName);
		return xPending(key, groupName, new JedisXPendingParams(start, end, count, minIdleTime, consumerName), args);
	}

	@Override
	public List<StreamPending> xPending(final byte[] key, final byte[] groupName, final long minIdleTime,
	                                    final StreamEntryId start, final StreamEntryId end, final int count,
	                                    final byte[] consumerName) {
		final CommandArguments args = CommandArguments.create(key).add(groupName).add(Keyword.Time.IDLE, minIdleTime)
				.add(start, end).add(count).add(consumerName);
		return xPending(SafeEncoder.encode(key), SafeEncoder.encode(groupName),
				new JedisXPendingParams(start, end, count, minIdleTime, consumerName), args);
	}

	@Override
	public List<StreamEntry<String, String>> xRange(final String key, final StreamEntryId start,
	                                                final StreamEntryId end) {
		final CommandArguments args = CommandArguments.create(key).add(start, end);
		return executeCommand(RedisCommand.XRANGE, args,
				(cmd)->cmd.xrange(key, new JedisStreamEntryID(start), new JedisStreamEntryID(end)),
				(cmd)->cmd.xrange(key, new JedisStreamEntryID(start), new JedisStreamEntryID(end)),
				(cmd)->cmd.xrange(key, new JedisStreamEntryID(start), new JedisStreamEntryID(end)),
				new ListConverter<>(new StreamEntryConverter<>((k)->k, (v)->v)));
	}

	@Override
	public List<StreamEntry<byte[], byte[]>> xRange(final byte[] key, final StreamEntryId start,
	                                                final StreamEntryId end) {
		final CommandArguments args = CommandArguments.create(key).add(start, end);
		return executeCommand(RedisCommand.XRANGE, args,
				(cmd)->cmd.xrange(SafeEncoder.encode(key), new JedisStreamEntryID(start), new JedisStreamEntryID(end)),
				(cmd)->cmd.xrange(SafeEncoder.encode(key), new JedisStreamEntryID(start), new JedisStreamEntryID(end)),
				(cmd)->cmd.xrange(SafeEncoder.encode(key), new JedisStreamEntryID(start), new JedisStreamEntryID(end)),
				new ListConverter<>(new StreamEntryConverter<>(SafeEncoder::encode, SafeEncoder::encode)));
	}

	@Override
	public List<StreamEntry<String, String>> xRange(final String key, final StreamEntryId start,
	                                                final StreamEntryId end, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(start, end).add(Keyword.Common.COUNT, count);
		return executeCommand(RedisCommand.XRANGE, args,
				(cmd)->cmd.xrange(key, new JedisStreamEntryID(start), new JedisStreamEntryID(end), count),
				(cmd)->cmd.xrange(key, new JedisStreamEntryID(start), new JedisStreamEntryID(end), count),
				(cmd)->cmd.xrange(key, new JedisStreamEntryID(start), new JedisStreamEntryID(end), count),
				new ListConverter<>(new StreamEntryConverter<>((k)->k, (v)->v)));
	}

	@Override
	public List<StreamEntry<byte[], byte[]>> xRange(final byte[] key, final StreamEntryId start,
	                                                final StreamEntryId end, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(start, end).add(Keyword.Common.COUNT, count);
		return executeCommand(RedisCommand.XRANGE, args,
				(cmd)->cmd.xrange(SafeEncoder.encode(key), new JedisStreamEntryID(start), new JedisStreamEntryID(end),
						count),
				(cmd)->cmd.xrange(SafeEncoder.encode(key), new JedisStreamEntryID(start), new JedisStreamEntryID(end),
						count),
				(cmd)->cmd.xrange(SafeEncoder.encode(key), new JedisStreamEntryID(start), new JedisStreamEntryID(end),
						count),
				new ListConverter<>(new StreamEntryConverter<>(SafeEncoder::encode, SafeEncoder::encode)));
	}

	@Override
	public List<XReadInfo<String, String>> xRead(final Map<String, StreamEntryId> streams) {
		final CommandArguments args = CommandArguments.create().add("STREAMS", streams);
		return xRead(streams, new JedisXReadParams(), args);
	}

	@Override
	public List<XReadInfo<String, String>> xRead(final Map<String, StreamEntryId> streams, final int count) {
		final CommandArguments args = CommandArguments.create(Keyword.Common.COUNT, count).add("STREAMS", streams);
		return xRead(streams, new JedisXReadParams(count), args);
	}

	@Override
	public List<XReadInfo<String, String>> xRead(final Map<String, StreamEntryId> streams, final long block) {
		final CommandArguments args = CommandArguments.create("BLOCK", block).add("STREAMS", streams);
		return xRead(streams, new JedisXReadParams(block), args);
	}

	@Override
	public List<XReadInfo<String, String>> xRead(final Map<String, StreamEntryId> streams, final long block,
	                                             final int count) {
		final CommandArguments args = CommandArguments.create(Keyword.Common.COUNT, count).add("BLOCK", block)
				.add("STREAMS", streams);
		return xRead(streams, new JedisXReadParams(block, count), args);
	}

	@Override
	public List<XReadGroupInfo<String, String>> xReadGroup(final String groupName, final String consumerName,
	                                                       final Map<String, StreamEntryId> streams) {
		final CommandArguments args = CommandArguments.create(groupName, consumerName).add("STREAMS", streams);
		return xReadGroup(groupName, consumerName, streams, new JedisXReadGroupParams(), args);
	}

	@Override
	public List<XReadGroupInfo<byte[], byte[]>> xReadGroup(final byte[] groupName, final byte[] consumerName,
	                                                       final Map<byte[], StreamEntryId> streams) {
		final CommandArguments args = CommandArguments.create(groupName, consumerName).add("STREAMS", streams);
		return xReadGroup(groupName, consumerName, streams, new JedisXReadGroupParams(), args);
	}

	@Override
	public List<XReadGroupInfo<String, String>> xReadGroup(final String groupName, final String consumerName,
	                                                       final XReadGroupArgument xReadGroupArgument,
	                                                       final Map<String, StreamEntryId> streams) {
		final CommandArguments args = CommandArguments.create(groupName, consumerName).add(xReadGroupArgument)
				.add("STREAMS", streams);
		return xReadGroup(groupName, consumerName, streams, new JedisXReadGroupParams(xReadGroupArgument), args);
	}

	@Override
	public List<XReadGroupInfo<byte[], byte[]>> xReadGroup(final byte[] groupName, final byte[] consumerName,
	                                                       final XReadGroupArgument xReadGroupArgument,
	                                                       final Map<byte[], StreamEntryId> streams) {
		final CommandArguments args = CommandArguments.create(groupName, consumerName).add(xReadGroupArgument)
				.add("STREAMS", streams);
		return xReadGroup(groupName, consumerName, streams, new JedisXReadGroupParams(xReadGroupArgument), args);
	}

	@Override
	public List<XReadGroupInfo<String, String>> xReadGroup(final String groupName, final String consumerName,
	                                                       final XReadGroupArgument xReadGroupArgument,
	                                                       final Map<String, StreamEntryId> streams, final int count) {
		final CommandArguments args = CommandArguments.create(groupName, consumerName).add(Keyword.Common.COUNT, count)
				.add(xReadGroupArgument).add("STREAMS", streams);
		return xReadGroup(groupName, consumerName, streams, new JedisXReadGroupParams(xReadGroupArgument, count), args);
	}

	@Override
	public List<XReadGroupInfo<byte[], byte[]>> xReadGroup(final byte[] groupName, final byte[] consumerName,
	                                                       final XReadGroupArgument xReadGroupArgument,
	                                                       final Map<byte[], StreamEntryId> streams, final int count) {
		final CommandArguments args = CommandArguments.create(groupName, consumerName).add(Keyword.Common.COUNT, count)
				.add(xReadGroupArgument).add("STREAMS", streams);
		return xReadGroup(groupName, consumerName, streams, new JedisXReadGroupParams(xReadGroupArgument, count), args);
	}

	@Override
	public List<XReadGroupInfo<String, String>> xReadGroup(final String groupName, final String consumerName,
	                                                       final Map<String, StreamEntryId> streams, final int count) {
		final CommandArguments args = CommandArguments.create(groupName, consumerName).add(Keyword.Common.COUNT, count)
				.add("STREAMS", streams);
		return xReadGroup(groupName, consumerName, streams, new JedisXReadGroupParams(count), args);
	}

	@Override
	public List<XReadGroupInfo<byte[], byte[]>> xReadGroup(final byte[] groupName, final byte[] consumerName,
	                                                       final Map<byte[], StreamEntryId> streams, final int count) {
		final CommandArguments args = CommandArguments.create(groupName, consumerName).add(Keyword.Common.COUNT, count)
				.add("STREAMS", streams);
		return xReadGroup(groupName, consumerName, streams, new JedisXReadGroupParams(count), args);
	}

	@Override
	public List<StreamEntry<String, String>> xRevRange(final String key, final StreamEntryId end,
	                                                   final StreamEntryId start) {
		final CommandArguments args = CommandArguments.create(key).add(end, start);
		return executeCommand(RedisCommand.XREVRANGE, args,
				(cmd)->cmd.xrevrange(key, new JedisStreamEntryID(end), new JedisStreamEntryID(start)),
				(cmd)->cmd.xrevrange(key, new JedisStreamEntryID(end), new JedisStreamEntryID(start)),
				(cmd)->cmd.xrevrange(key, new JedisStreamEntryID(end), new JedisStreamEntryID(start)),
				new ListConverter<>(new StreamEntryConverter<>((k)->k, (v)->v)));
	}

	@Override
	public List<StreamEntry<byte[], byte[]>> xRevRange(final byte[] key, final StreamEntryId end,
	                                                   final StreamEntryId start) {
		final CommandArguments args = CommandArguments.create(key).add(end, start);
		return executeCommand(RedisCommand.XREVRANGE, args,
				(cmd)->cmd.xrevrange(SafeEncoder.encode(key), new JedisStreamEntryID(end),
						new JedisStreamEntryID(start)),
				(cmd)->cmd.xrevrange(SafeEncoder.encode(key), new JedisStreamEntryID(end),
						new JedisStreamEntryID(start)),
				(cmd)->cmd.xrevrange(SafeEncoder.encode(key), new JedisStreamEntryID(end),
						new JedisStreamEntryID(start)),
				new ListConverter<>(new StreamEntryConverter<>(SafeEncoder::encode, SafeEncoder::encode)));
	}

	@Override
	public List<StreamEntry<String, String>> xRevRange(final String key, final StreamEntryId end,
	                                                   final StreamEntryId start, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(end, start).add(Keyword.Common.COUNT, count);
		return executeCommand(RedisCommand.XREVRANGE, args,
				(cmd)->cmd.xrevrange(key, new JedisStreamEntryID(end), new JedisStreamEntryID(start), count),
				(cmd)->cmd.xrevrange(key, new JedisStreamEntryID(end), new JedisStreamEntryID(start), count),
				(cmd)->cmd.xrevrange(key, new JedisStreamEntryID(end), new JedisStreamEntryID(start), count),
				new ListConverter<>(new StreamEntryConverter<>((k)->k, (v)->v)));
	}

	@Override
	public List<StreamEntry<byte[], byte[]>> xRevRange(final byte[] key, final StreamEntryId end,
	                                                   final StreamEntryId start, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(end, start).add(Keyword.Common.COUNT, count);
		return executeCommand(RedisCommand.XREVRANGE, args,
				(cmd)->cmd.xrevrange(SafeEncoder.encode(key), new JedisStreamEntryID(end),
						new JedisStreamEntryID(start), count),
				(cmd)->cmd.xrevrange(SafeEncoder.encode(key), new JedisStreamEntryID(end),
						new JedisStreamEntryID(start), count),
				(cmd)->cmd.xrevrange(SafeEncoder.encode(key), new JedisStreamEntryID(end),
						new JedisStreamEntryID(start), count),
				new ListConverter<>(new StreamEntryConverter<>(SafeEncoder::encode, SafeEncoder::encode)));
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
		return xTrim(key, new JedisXTrimParams(maxLenMinId), args);
	}

	@Override
	public Long xTrim(final byte[] key, final MaxLenMinId<?> maxLenMinId) {
		final CommandArguments args = CommandArguments.create(key).add(maxLenMinId);
		return xTrim(key, new JedisXTrimParams(), args);
	}

	@Override
	public Long xTrim(final String key, final ApproximateExactTrimming approximateExactTrimming,
	                  final MaxLenMinId<?> maxLenMinId) {
		final CommandArguments args = CommandArguments.create(key).add(maxLenMinId.getType())
				.add(approximateExactTrimming).add(maxLenMinId.getThreshold());
		return xTrim(key, new JedisXTrimParams(maxLenMinId, approximateExactTrimming), args);
	}

	@Override
	public Long xTrim(final byte[] key, final ApproximateExactTrimming approximateExactTrimming,
	                  final MaxLenMinId<?> maxLenMinId) {
		final CommandArguments args = CommandArguments.create(key).add(maxLenMinId.getType())
				.add(approximateExactTrimming).add(maxLenMinId.getThreshold());
		return xTrim(key, new JedisXTrimParams(maxLenMinId, approximateExactTrimming), args);
	}

	@Override
	public Long xTrim(final String key, final ApproximateExactTrimming approximateExactTrimming,
	                  final MaxLenMinId<?> maxLenMinId, final StreamDeletionPolicy deletionPolicy) {
		final CommandArguments args = CommandArguments.create(key).add(maxLenMinId.getType())
				.add(approximateExactTrimming).add(maxLenMinId.getThreshold()).add(deletionPolicy);
		return xTrim(key,
				new JedisXTrimParams(maxLenMinId, approximateExactTrimming).deletionPolicy(deletionPolicy), args);
	}

	@Override
	public Long xTrim(final byte[] key, final ApproximateExactTrimming approximateExactTrimming,
	                  final MaxLenMinId<?> maxLenMinId, final StreamDeletionPolicy deletionPolicy) {
		final CommandArguments args = CommandArguments.create(key).add(maxLenMinId.getType())
				.add(approximateExactTrimming).add(maxLenMinId.getThreshold()).add(deletionPolicy);
		return xTrim(key,
				new JedisXTrimParams(maxLenMinId, approximateExactTrimming).deletionPolicy(deletionPolicy), args);
	}

	@Override
	public Long xTrim(final String key, final MaxLenMinId<?> maxLenMinId, final StreamDeletionPolicy deletionPolicy) {
		final CommandArguments args = CommandArguments.create(key).add(maxLenMinId).add(deletionPolicy);
		return xTrim(key, new JedisXTrimParams(maxLenMinId).deletionPolicy(deletionPolicy), args);
	}

	@Override
	public Long xTrim(final byte[] key, final MaxLenMinId<?> maxLenMinId, final StreamDeletionPolicy deletionPolicy) {
		final CommandArguments args = CommandArguments.create(key).add(maxLenMinId).add(deletionPolicy);
		return xTrim(key, new JedisXTrimParams(maxLenMinId).deletionPolicy(deletionPolicy), args);
	}

	@Override
	public Long xTrim(final String key, final MaxLenMinId<?> maxLenMinId, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(maxLenMinId).add(Keyword.Common.LIMIT, count);
		return xTrim(key, new JedisXTrimParams(maxLenMinId, count), args);
	}

	@Override
	public Long xTrim(final byte[] key, final MaxLenMinId<?> maxLenMinId, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(maxLenMinId).add(Keyword.Common.LIMIT, count);
		return xTrim(key, new JedisXTrimParams(maxLenMinId, count), args);
	}

	@Override
	public Long xTrim(final String key, final ApproximateExactTrimming approximateExactTrimming,
	                  final MaxLenMinId<?> maxLenMinId, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(maxLenMinId.getType())
				.add(approximateExactTrimming).add(maxLenMinId.getThreshold()).add(Keyword.Common.LIMIT, count);
		return xTrim(key, new JedisXTrimParams(maxLenMinId, approximateExactTrimming), args);
	}

	@Override
	public Long xTrim(final byte[] key, final ApproximateExactTrimming approximateExactTrimming,
	                  final MaxLenMinId<?> maxLenMinId, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(maxLenMinId.getType())
				.add(approximateExactTrimming).add(maxLenMinId.getThreshold()).add(Keyword.Common.LIMIT, count);
		return xTrim(key, new JedisXTrimParams(maxLenMinId, approximateExactTrimming), args);
	}

	@Override
	public Long xTrim(final String key, final ApproximateExactTrimming approximateExactTrimming,
	                  final MaxLenMinId<?> maxLenMinId, final StreamDeletionPolicy deletionPolicy, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(maxLenMinId.getType())
				.add(approximateExactTrimming).add(maxLenMinId.getThreshold()).add(deletionPolicy)
				.add(Keyword.Common.LIMIT, count);
		return xTrim(key, new JedisXTrimParams(maxLenMinId, count), args);
	}

	@Override
	public Long xTrim(final byte[] key, final ApproximateExactTrimming approximateExactTrimming,
	                  final MaxLenMinId<?> maxLenMinId, final StreamDeletionPolicy deletionPolicy, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(maxLenMinId.getType())
				.add(approximateExactTrimming).add(maxLenMinId.getThreshold()).add(deletionPolicy)
				.add(Keyword.Common.LIMIT, count);
		return xTrim(key, new JedisXTrimParams(maxLenMinId, count), args);
	}

	@Override
	public Long xTrim(final String key, final MaxLenMinId<?> maxLenMinId, final StreamDeletionPolicy deletionPolicy,
	                  final int count) {
		final CommandArguments args = CommandArguments.create(key).add(maxLenMinId).add(deletionPolicy)
				.add(Keyword.Common.LIMIT, count);
		return xTrim(key, new JedisXTrimParams(maxLenMinId, count).deletionPolicy(deletionPolicy), args);
	}

	@Override
	public Long xTrim(final byte[] key, final MaxLenMinId<?> maxLenMinId, final StreamDeletionPolicy deletionPolicy,
	                  final int count) {
		final CommandArguments args = CommandArguments.create(key).add(maxLenMinId).add(deletionPolicy)
				.add(Keyword.Common.LIMIT, count);
		return xTrim(key, new JedisXTrimParams(maxLenMinId, count).deletionPolicy(deletionPolicy), args);
	}

	private StreamEntryID[] streamEntryIds(final StreamEntryId... ids) {
		final ArrayConverter<StreamEntryId, StreamEntryID> arrayConverter = new ArrayConverter<>(
				new StreamEntryIdConverter(), StreamEntryID.class);
		return arrayConverter.convert(ids);
	}

	private byte[][] binaryStreamEntryIds(final StreamEntryId... ids) {
		return Arrays.map(ids, byte[].class, StreamEntryId::getRaw);
	}

	private StreamEntryId xAdd(final String key, final StreamEntryId id, final Map<String, String> hash,
	                           final CommandArguments args) {
		return executeCommand(RedisCommand.XADD, args, (cmd)->cmd.xadd(key, new JedisStreamEntryID(id), hash),
				(cmd)->cmd.xadd(key, new JedisStreamEntryID(id), hash),
				(cmd)->cmd.xadd(key, new JedisStreamEntryID(id), hash), new StreamEntryIDConverter());
	}

	private StreamEntryId xAdd(final String key, final StreamEntryId id, final Map<String, String> hash,
	                           final XAddArgument xAddArgument, final CommandArguments args) {
		return executeCommand(RedisCommand.XADD, args,
				(cmd)->cmd.xadd(key, new JedisXAddParams(xAddArgument, id), hash),
				(cmd)->cmd.xadd(key, new JedisXAddParams(xAddArgument, id), hash),
				(cmd)->cmd.xadd(key, new JedisXAddParams(xAddArgument, id), hash), new StreamEntryIDConverter());
	}

	private <T> AutoClaimInfo<T, T> xAutoClaim(final String key, final String groupName, final String consumerName,
	                                           final int minIdleTime, final StreamEntryId start,
	                                           final XAutoClaimParams xAutoClaimParams,
	                                           final Converter<String, T> converter, final CommandArguments args) {
		return executeCommand(RedisCommand.XAUTOCLAIM, args,
				(cmd)->cmd.xautoclaim(key, groupName, consumerName, minIdleTime, new JedisStreamEntryID(start),
						xAutoClaimParams),
				(cmd)->cmd.xautoclaim(key, groupName, consumerName, minIdleTime, new JedisStreamEntryID(start),
						xAutoClaimParams),
				(cmd)->cmd.xautoclaim(key, groupName, consumerName, minIdleTime, new JedisStreamEntryID(start),
						xAutoClaimParams), new MapEntryStreamEntryAutoClaimInfoConverter<>(converter, converter));
	}

	private AutoClaimId xAutoClaimJustId(final String key, final String groupName, final String consumerName,
	                                     final int minIdleTime, final StreamEntryId start,
	                                     final XAutoClaimParams xAutoClaimParams, final CommandArguments args) {
		return executeCommand(RedisCommand.XAUTOCLAIM, args,
				(cmd)->cmd.xautoclaimJustId(key, groupName, consumerName, minIdleTime, new JedisStreamEntryID(start),
						xAutoClaimParams),
				(cmd)->cmd.xautoclaimJustId(key, groupName, consumerName, minIdleTime, new JedisStreamEntryID(start),
						xAutoClaimParams),
				(cmd)->cmd.xautoclaimJustId(key, groupName, consumerName, minIdleTime, new JedisStreamEntryID(start),
						xAutoClaimParams), new MapEntryStreamEntryAutoClaimIdConverter());
	}

	private AutoClaimId xAutoClaimJustId(final String key, final byte[] groupName, final byte[] consumerName,
	                                     final int minIdleTime, final StreamEntryId start,
	                                     final XAutoClaimParams xAutoClaimParams, final CommandArguments args) {
		return xAutoClaimJustId(key, SafeEncoder.encode(groupName), SafeEncoder.encode(consumerName), minIdleTime,
				start, xAutoClaimParams, args);
	}

	private List<StreamEntry<String, String>> xClaim(final String key, final String groupName,
	                                                 final String consumerName, final int minIdleTime,
	                                                 final StreamEntryId[] ids, final XClaimParams xClaimParams,
	                                                 final CommandArguments args) {
		return executeCommand(RedisCommand.XCLAIM, args,
				(cmd)->cmd.xclaim(key, groupName, consumerName, minIdleTime, xClaimParams, streamEntryIds(ids)),
				(cmd)->cmd.xclaim(key, groupName, consumerName, minIdleTime, xClaimParams, streamEntryIds(ids)),
				(cmd)->cmd.xclaim(key, groupName, consumerName, minIdleTime, xClaimParams, streamEntryIds(ids)),
				new ListConverter<>(new StreamEntryConverter<>((k)->k, (v)->v)));
	}

	private List<StreamEntry<byte[], byte[]>> xClaim(final String key, final byte[] groupName,
	                                                 final byte[] consumerName, final int minIdleTime,
	                                                 final StreamEntryId[] ids, final XClaimParams xClaimParams,
	                                                 final CommandArguments args) {
		return executeCommand(RedisCommand.XCLAIM, args,
				(cmd)->cmd.xclaim(key, SafeEncoder.encode(groupName), SafeEncoder.encode(consumerName), minIdleTime,
						xClaimParams, streamEntryIds(ids)),
				(cmd)->cmd.xclaim(key, SafeEncoder.encode(groupName), SafeEncoder.encode(consumerName), minIdleTime,
						xClaimParams, streamEntryIds(ids)),
				(cmd)->cmd.xclaim(key, SafeEncoder.encode(groupName), SafeEncoder.encode(consumerName), minIdleTime,
						xClaimParams, streamEntryIds(ids)),
				new ListConverter<>(new StreamEntryConverter<>(SafeEncoder::encode, SafeEncoder::encode)));
	}

	private List<StreamEntryId> xClaimJustId(final String key, final String groupName, final String consumerName,
	                                         final int minIdleTime, final StreamEntryId[] ids,
	                                         final XClaimParams xClaimParams, final CommandArguments args) {
		return executeCommand(RedisCommand.XCLAIM, args,
				(cmd)->cmd.xclaimJustId(key, groupName, consumerName, minIdleTime, xClaimParams, streamEntryIds(ids)),
				(cmd)->cmd.xclaimJustId(key, groupName, consumerName, minIdleTime, xClaimParams, streamEntryIds(ids)),
				(cmd)->cmd.xclaimJustId(key, groupName, consumerName, minIdleTime, xClaimParams, streamEntryIds(ids)),
				new ListConverter<>(new StreamEntryIDConverter()));
	}

	private List<StreamEntryId> xClaimJustId(final String key, final byte[] groupName, final byte[] consumerName,
	                                         final int minIdleTime, final StreamEntryId[] ids,
	                                         final XClaimParams xClaimParams, final CommandArguments args) {
		return xClaimJustId(key, SafeEncoder.encode(groupName), SafeEncoder.encode(consumerName), minIdleTime, ids,
				xClaimParams, args);
	}

	private List<StreamEntryDeletionResult> xDelEx(final String key, final StreamEntryId[] ids,
	                                               final StreamDeletionPolicy deletionPolicy,
	                                               final CommandArguments args) {
		final StreamDeletionPolicyConverter streamDeletionPolicyConverter = new StreamDeletionPolicyConverter();
		return executeCommand(RedisCommand.XDELEX, args,
				(cmd)->cmd.xdelex(key, streamDeletionPolicyConverter.convert(deletionPolicy), streamEntryIds(ids)),
				(cmd)->cmd.xdelex(key, streamDeletionPolicyConverter.convert(deletionPolicy), streamEntryIds(ids)),
				(cmd)->cmd.xdelex(key, streamDeletionPolicyConverter.convert(deletionPolicy), streamEntryIds(ids)),
				new ListConverter<>(new StreamEntryDeletionResultConverter()));
	}

	private Status xGroupCreate(final String key, final String groupName, final StreamEntryId id,
	                            final boolean makeStream, final CommandArguments args) {
		return executeCommand(RedisCommand.XGROUP, RedisSubCommand.XGROUP_CREATE, args,
				(cmd)->cmd.xgroupCreate(key, groupName, new JedisStreamEntryID(id), makeStream),
				(cmd)->cmd.xgroupCreate(key, groupName, new JedisStreamEntryID(id), makeStream),
				(cmd)->cmd.xgroupCreate(key, groupName, new JedisStreamEntryID(id), makeStream),
				new OkStatusConverter());
	}

	private Status xGroupCreate(final byte[] key, final byte[] groupName, final StreamEntryId id,
	                            final boolean makeStream, final CommandArguments args) {
		return executeCommand(RedisCommand.XGROUP, RedisSubCommand.XGROUP_CREATE, args,
				(cmd)->cmd.xgroupCreate(key, groupName, id.getRaw(), makeStream),
				(cmd)->cmd.xgroupCreate(key, groupName, id.getRaw(), makeStream),
				(cmd)->cmd.xgroupCreate(key, groupName, id.getRaw(), makeStream), new OkStatusConverter());
	}

	private List<StreamConsumer> xInfoConsumers(final String key, final String groupName, final CommandArguments args) {
		return executeCommand(RedisCommand.XINFO, RedisSubCommand.XINFO_CONSUMERS, args,
				(cmd)->cmd.xinfoConsumers2(key, groupName), (cmd)->cmd.xinfoConsumers2(key, groupName),
				(cmd)->cmd.xinfoConsumers2(key, groupName), new ListConverter<>(new StreamConsumersInfoConverter()));
	}

	private List<StreamGroup> xInfoGroups(final String key, final CommandArguments args) {
		return executeCommand(RedisCommand.XINFO, RedisSubCommand.XINFO_GROUPS, args, (cmd)->cmd.xinfoGroups(key),
				(cmd)->cmd.xinfoGroups(key), (cmd)->cmd.xinfoGroups(key),
				new ListConverter<>(new StreamGroupInfoConverter()));
	}

	private List<StreamPending> xPending(final String key, final String groupName, final XPendingParams xPendingParams,
	                                     final CommandArguments args) {
		return executeCommand(RedisCommand.XPENDING, args, (cmd)->cmd.xpending(key, groupName, xPendingParams),
				(cmd)->cmd.xpending(key, groupName, xPendingParams),
				(cmd)->cmd.xpending(key, groupName, xPendingParams),
				new ListConverter<>(new StreamPendingEntryConverter()));
	}

	private List<XReadInfo<String, String>> xRead(final Map<String, StreamEntryId> streams,
	                                              final XReadParams xReadParams, final CommandArguments args) {
		final MapConverter<String, StreamEntryId, String, StreamEntryID> mapConverter = new MapConverter<>((k)->k,
				new StreamEntryIdConverter());
		return executeCommand(RedisCommand.XREAD, args, (cmd)->cmd.xread(xReadParams, mapConverter.convert(streams)),
				(cmd)->cmd.xread(xReadParams, mapConverter.convert(streams)),
				(cmd)->cmd.xread(xReadParams, mapConverter.convert(streams)),
				new ListConverter<>(new MapEntryStreamEntryXReadInfoConverter<>((k)->k, (k)->k, (v)->v)));
	}

	private List<XReadGroupInfo<String, String>> xReadGroup(final String groupName, final String consumerName,
	                                                        final Map<String, StreamEntryId> streams,
	                                                        final XReadGroupParams xReadGroupParams,
	                                                        final CommandArguments args) {
		final MapConverter<String, StreamEntryId, String, StreamEntryID> mapConverter = new MapConverter<>((k)->k,
				new StreamEntryIdConverter());
		return xReadGroup(groupName, consumerName, mapConverter.convert(streams), xReadGroupParams, (k)->k, (v)->v,
				args);
	}

	private List<XReadGroupInfo<byte[], byte[]>> xReadGroup(final byte[] groupName, final byte[] consumerName,
	                                                        final Map<byte[], StreamEntryId> streams,
	                                                        final XReadGroupParams xReadGroupParams,
	                                                        final CommandArguments args) {
		final MapConverter<byte[], StreamEntryId, String, StreamEntryID> mapConverter = new MapConverter<>(
				SafeEncoder::encode, new StreamEntryIdConverter());
		return xReadGroup(SafeEncoder.encode(groupName), SafeEncoder.encode(consumerName),
				mapConverter.convert(streams), xReadGroupParams, SafeEncoder::encode, SafeEncoder::encode, args);
	}

	private <K, V> List<XReadGroupInfo<K, V>> xReadGroup(final String groupName, final String consumerName,
	                                                     final Map<String, StreamEntryID> streams,
	                                                     final XReadGroupParams xReadGroupParams,
	                                                     final Converter<String, K> keyConverter,
	                                                     final Converter<String, V> valueConverter,
	                                                     final CommandArguments args) {
		return executeCommand(RedisCommand.XREADGROUP, args,
				(cmd)->cmd.xreadGroup(groupName, consumerName, xReadGroupParams, streams),
				(cmd)->cmd.xreadGroup(groupName, consumerName, xReadGroupParams, streams),
				(cmd)->cmd.xreadGroup(groupName, consumerName, xReadGroupParams, streams),
				new ListConverter<>(
						new MapEntryStreamEntryXReadGroupInfoConverter<>(keyConverter, keyConverter, valueConverter)));
	}

	private Long xTrim(final String key, final XTrimParams xTrimParams, final CommandArguments args) {
		return executeCommand(RedisCommand.XTRIM, args, (cmd)->cmd.xtrim(key, xTrimParams),
				(cmd)->cmd.xtrim(key, xTrimParams), (cmd)->cmd.xtrim(key, xTrimParams));
	}

	private Long xTrim(final byte[] key, final XTrimParams xTrimParams, final CommandArguments args) {
		return executeCommand(RedisCommand.XTRIM, args, (cmd)->cmd.xtrim(key, xTrimParams),
				(cmd)->cmd.xtrim(key, xTrimParams), (cmd)->cmd.xtrim(key, xTrimParams));
	}

}
