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

import com.buession.core.converter.ArrayConverter;
import com.buession.core.converter.BooleanStatusConverter;
import com.buession.core.converter.ListConverter;
import com.buession.core.converter.MapConverter;
import com.buession.core.converter.MapEntryKeyValueConverter;
import com.buession.lang.KeyValue;
import com.buession.lang.Status;
import com.buession.redis.client.jedis.JedisRedisClient;
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
import com.buession.redis.core.internal.convert.BinaryMapStringMapConverter;
import com.buession.redis.core.internal.convert.jedis.params.StreamDeletionPolicyConverter;
import com.buession.redis.core.internal.convert.jedis.params.StreamEntryIdConverter;
import com.buession.redis.core.internal.convert.jedis.params.XAddArgumentConverter;
import com.buession.redis.core.internal.convert.jedis.params.XClaimArgumentConverter;
import com.buession.redis.core.internal.convert.jedis.params.XReadGroupArgumentConverter;
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
import com.buession.redis.core.internal.jedis.args.JedisXAutoClaimParams;
import com.buession.redis.core.internal.jedis.args.JedisXPendingParams;
import com.buession.redis.core.internal.jedis.args.JedisXReadGroupParams;
import com.buession.redis.core.internal.jedis.args.JedisXReadParams;
import com.buession.redis.core.internal.jedis.args.JedisXTrimParams;
import com.buession.redis.utils.SafeEncoder;
import redis.clients.jedis.StreamEntryID;
import redis.clients.jedis.UnifiedJedis;
import redis.clients.jedis.params.XAddParams;
import redis.clients.jedis.params.XAutoClaimParams;
import redis.clients.jedis.params.XClaimParams;
import redis.clients.jedis.params.XPendingParams;
import redis.clients.jedis.params.XReadGroupParams;
import redis.clients.jedis.params.XReadParams;
import redis.clients.jedis.params.XTrimParams;

import java.util.List;
import java.util.Map;
import java.util.Optional;

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
		final CommandArguments args = CommandArguments.create(key).add(groupName).add(ids);
		final ArrayConverter<StreamEntryId, StreamEntryID> arrayConverter = new ArrayConverter<>(
				new StreamEntryIdConverter(), StreamEntryID.class);
		return executeCommand(Command.XACK, args, (cmd)->cmd.xack(key, groupName, arrayConverter.convert(ids)), (v)->v);
	}

	@Override
	public Long xAck(final byte[] key, final byte[] groupName, final StreamEntryId... ids) {
		return xAck(SafeEncoder.encode(key), SafeEncoder.encode(groupName), ids);
	}

	@Override
	public List<StreamEntryDeletionResult> xAckDel(final String key, final String groupName,
												   final StreamEntryId... ids) {
		final CommandArguments args = CommandArguments.create(key).add(groupName).add("IDS", ids.length).add(ids);
		final ArrayConverter<StreamEntryId, StreamEntryID> arrayConverter = new ArrayConverter<>(
				new StreamEntryIdConverter(), StreamEntryID.class);
		return xAckDel((cmd)->cmd.xackdel(key, groupName, arrayConverter.convert(ids)), args);
	}

	@Override
	public List<StreamEntryDeletionResult> xAckDel(final byte[] key, final byte[] groupName,
												   final StreamEntryId... ids) {
		return xAckDel(SafeEncoder.encode(key), SafeEncoder.encode(groupName), ids);
	}

	@Override
	public List<StreamEntryDeletionResult> xAckDel(final String key, final String groupName,
												   final StreamDeletionPolicy deletionPolicy,
												   final StreamEntryId... ids) {
		final CommandArguments args = CommandArguments.create(key).add(groupName).add(deletionPolicy)
				.add("IDS", ids.length).add(ids);
		final ArrayConverter<StreamEntryId, StreamEntryID> arrayConverter = new ArrayConverter<>(
				new StreamEntryIdConverter(), StreamEntryID.class);
		final StreamDeletionPolicyConverter streamDeletionPolicyConverter = new StreamDeletionPolicyConverter();
		return xAckDel((cmd)->cmd.xackdel(key, groupName, streamDeletionPolicyConverter.convert(deletionPolicy),
				arrayConverter.convert(ids)), args);
	}

	@Override
	public List<StreamEntryDeletionResult> xAckDel(final byte[] key, final byte[] groupName,
												   final StreamDeletionPolicy deletionPolicy,
												   final StreamEntryId... ids) {
		return xAckDel(SafeEncoder.encode(key), SafeEncoder.encode(groupName), deletionPolicy, ids);
	}

	@Override
	public StreamEntryId xAdd(final String key, final StreamEntryId id, final Map<String, String> hash) {
		final CommandArguments args = CommandArguments.create(key).add(id).add(hash);
		return executeCommand(Command.XADD, args, (cmd)->cmd.xadd(key, new JedisStreamEntryID(id), hash),
				new StreamEntryIDConverter());
	}

	@Override
	public StreamEntryId xAdd(final byte[] key, final StreamEntryId id, final Map<byte[], byte[]> hash) {
		final MapConverter<byte[], byte[], String, String> mapConverter = new MapConverter<>(SafeEncoder::encode,
				SafeEncoder::encode);
		return xAdd(SafeEncoder.encode(key), id, mapConverter.convert(hash));
	}

	@Override
	public StreamEntryId xAdd(final String key, final StreamEntryId id, final Map<String, String> hash,
							  final XAddArgument xAddArgument) {
		final CommandArguments args = CommandArguments.create(key).add(xAddArgument).add(id).add(hash);
		final XAddArgumentConverter xAddArgumentConverter = new XAddArgumentConverter();
		final XAddParams xAddParams = Optional.ofNullable(xAddArgumentConverter.convert(xAddArgument))
				.orElse(new XAddParams());

		xAddParams.id(new JedisStreamEntryID(id));

		return executeCommand(Command.XADD, args, (cmd)->cmd.xadd(key, xAddParams, hash), new StreamEntryIDConverter());
	}

	@Override
	public StreamEntryId xAdd(final byte[] key, final StreamEntryId id, final Map<byte[], byte[]> hash,
							  final XAddArgument xAddArgument) {
		final BinaryMapStringMapConverter mapConverter = new BinaryMapStringMapConverter();
		return xAdd(SafeEncoder.encode(key), id, mapConverter.convert(hash), xAddArgument);
	}

	@Override
	public AutoClaimInfo<String, String> xAutoClaim(final String key, final String groupName, final String consumerName,
													final int minIdleTime, final StreamEntryId start) {
		final CommandArguments args = CommandArguments.create(key).add(groupName, consumerName).add(minIdleTime)
				.add(start);
		return xAutoClaim(key, groupName, consumerName, minIdleTime, start, new JedisXAutoClaimParams(), args);
	}

	@Override
	public AutoClaimInfo<byte[], byte[]> xAutoClaim(final byte[] key, final byte[] groupName, final byte[] consumerName,
													final int minIdleTime, final StreamEntryId start) {
		final CommandArguments args = CommandArguments.create(key).add(groupName, consumerName).add(minIdleTime)
				.add(start);
		return xAutoClaim(key, groupName, consumerName, minIdleTime, start, new JedisXAutoClaimParams(), args);
	}

	@Override
	public AutoClaimInfo<String, String> xAutoClaim(final String key, final String groupName, final String consumerName,
													final int minIdleTime, final StreamEntryId start, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(groupName, consumerName).add(minIdleTime)
				.add(start).add(Keyword.Common.COUNT, count);
		return xAutoClaim(key, groupName, consumerName, minIdleTime, start, new JedisXAutoClaimParams(count), args);
	}

	@Override
	public AutoClaimInfo<byte[], byte[]> xAutoClaim(final byte[] key, final byte[] groupName, final byte[] consumerName,
													final int minIdleTime, final StreamEntryId start, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(groupName, consumerName).add(minIdleTime)
				.add(start).add(Keyword.Common.COUNT, count);
		return xAutoClaim(key, groupName, consumerName, minIdleTime, start, new JedisXAutoClaimParams(count), args);
	}

	@Override
	public KeyValue<StreamEntryId, List<StreamEntryId>> xAutoClaimJustId(final String key, final String groupName,
																		 final String consumerName,
																		 final int minIdleTime,
																		 final StreamEntryId start) {
		final CommandArguments args = CommandArguments.create(key).add(groupName, consumerName).add(minIdleTime)
				.add(start).add("JUSTID");
		return xAutoClaimJustId(key, groupName, consumerName, minIdleTime, start, new JedisXAutoClaimParams(), args);
	}

	@Override
	public KeyValue<StreamEntryId, List<StreamEntryId>> xAutoClaimJustId(final byte[] key, final byte[] groupName,
																		 final byte[] consumerName,
																		 final int minIdleTime,
																		 final StreamEntryId start) {
		return xAutoClaimJustId(SafeEncoder.encode(key), SafeEncoder.encode(groupName),
				SafeEncoder.encode(consumerName), minIdleTime, start);
	}

	@Override
	public KeyValue<StreamEntryId, List<StreamEntryId>> xAutoClaimJustId(final String key, final String groupName,
																		 final String consumerName,
																		 final int minIdleTime,
																		 final StreamEntryId start, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(groupName, consumerName).add(minIdleTime)
				.add(start).add(Keyword.Common.COUNT).add(count).add("JUSTID");
		return xAutoClaimJustId(key, groupName, consumerName, minIdleTime, start, new JedisXAutoClaimParams(count),
				args);
	}

	@Override
	public KeyValue<StreamEntryId, List<StreamEntryId>> xAutoClaimJustId(final byte[] key, final byte[] groupName,
																		 final byte[] consumerName,
																		 final int minIdleTime,
																		 final StreamEntryId start, final int count) {
		return xAutoClaimJustId(SafeEncoder.encode(key), SafeEncoder.encode(groupName),
				SafeEncoder.encode(consumerName), minIdleTime, start, count);
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
		final CommandArguments args = CommandArguments.create(key).add(groupName, consumerName).add(minIdleTime)
				.add(ids);
		return xClaim(key, groupName, consumerName, minIdleTime, ids, new XClaimParams(), args);
	}

	@Override
	public List<StreamEntry<byte[], byte[]>> xClaim(final byte[] key, final byte[] groupName, final byte[] consumerName,
													final int minIdleTime, final StreamEntryId... ids) {
		final CommandArguments args = CommandArguments.create(key).add(groupName, consumerName).add(minIdleTime)
				.add(ids);
		return xClaim(key, groupName, consumerName, minIdleTime, ids, new XClaimParams(), args);
	}

	@Override
	public List<StreamEntry<String, String>> xClaim(final String key, final String groupName, final String consumerName,
													final int minIdleTime, final StreamEntryId[] ids,
													final XClaimArgument xClaimArgument) {
		final CommandArguments args = CommandArguments.create(key).add(groupName, consumerName).add(minIdleTime)
				.add(ids).add(xClaimArgument);
		final XClaimArgumentConverter xClaimArgumentConverter = new XClaimArgumentConverter();

		return xClaim(key, groupName, consumerName, minIdleTime, ids, xClaimArgumentConverter.convert(xClaimArgument),
				args);
	}

	@Override
	public List<StreamEntry<byte[], byte[]>> xClaim(final byte[] key, final byte[] groupName, final byte[] consumerName,
													final int minIdleTime, final StreamEntryId[] ids,
													final XClaimArgument xClaimArgument) {
		final CommandArguments args = CommandArguments.create(key).add(groupName, consumerName).add(minIdleTime)
				.add(ids).add(xClaimArgument);
		final XClaimArgumentConverter xClaimArgumentConverter = new XClaimArgumentConverter();

		return xClaim(key, groupName, consumerName, minIdleTime, ids, xClaimArgumentConverter.convert(xClaimArgument),
				args);
	}

	@Override
	public List<StreamEntryId> xClaimJustId(final String key, final String groupName, final String consumerName,
											final int minIdleTime, final StreamEntryId... ids) {
		final CommandArguments args = CommandArguments.create(key).add(groupName, consumerName).add(minIdleTime)
				.add(ids).add("JUSTID");
		return xClaimJustId(key, groupName, consumerName, minIdleTime, ids, new XClaimParams(), args);
	}

	@Override
	public List<StreamEntryId> xClaimJustId(final byte[] key, final byte[] groupName, final byte[] consumerName,
											final int minIdleTime, final StreamEntryId... ids) {
		return xClaimJustId(SafeEncoder.encode(key), SafeEncoder.encode(groupName), SafeEncoder.encode(consumerName),
				minIdleTime, ids);
	}

	@Override
	public List<StreamEntryId> xClaimJustId(final String key, final String groupName, final String consumerName,
											final int minIdleTime, final StreamEntryId[] ids,
											final XClaimArgument xClaimArgument) {
		final CommandArguments args = CommandArguments.create(key).add(groupName, consumerName).add(minIdleTime)
				.add(ids).add(xClaimArgument).add("JUSTID");
		final XClaimArgumentConverter xClaimArgumentConverter = new XClaimArgumentConverter();
		return xClaimJustId(key, groupName, consumerName, minIdleTime, ids,
				xClaimArgumentConverter.convert(xClaimArgument), args);
	}

	@Override
	public List<StreamEntryId> xClaimJustId(final byte[] key, final byte[] groupName, final byte[] consumerName,
											final int minIdleTime, final StreamEntryId[] ids,
											final XClaimArgument xClaimArgument) {
		return xClaimJustId(SafeEncoder.encode(key), SafeEncoder.encode(groupName), SafeEncoder.encode(consumerName),
				minIdleTime, ids, xClaimArgument);
	}

	@Override
	public Long xDel(final String key, final StreamEntryId... ids) {
		final CommandArguments args = CommandArguments.create(key).add(ids);
		final ArrayConverter<StreamEntryId, StreamEntryID> arrayConverter = new ArrayConverter<>(
				new StreamEntryIdConverter(), StreamEntryID.class);
		return executeCommand(Command.XDEL, args, (cmd)->cmd.xdel(key, arrayConverter.convert(ids)), (v)->v);
	}

	@Override
	public Long xDel(final byte[] key, final StreamEntryId... ids) {
		return xDel(SafeEncoder.encode(key), ids);
	}

	@Override
	public List<StreamEntryDeletionResult> xDelEx(final String key, final StreamDeletionPolicy deletionPolicy,
												  final StreamEntryId... ids) {
		final CommandArguments args = CommandArguments.create(key).add(deletionPolicy).add("IDS", ids.length).add(ids);
		final StreamDeletionPolicyConverter streamDeletionPolicyConverter = new StreamDeletionPolicyConverter();
		final ArrayConverter<StreamEntryId, StreamEntryID> arrayConverter = new ArrayConverter<>(
				new StreamEntryIdConverter(), StreamEntryID.class);
		return executeCommand(Command.XDELEX, args,
				(cmd)->cmd.xdelex(key, streamDeletionPolicyConverter.convert(deletionPolicy),
						arrayConverter.convert(ids)), new ListConverter<>(new StreamEntryDeletionResultConverter()));
	}

	@Override
	public List<StreamEntryDeletionResult> xDelEx(final byte[] key, final StreamDeletionPolicy deletionPolicy,
												  final StreamEntryId... ids) {
		return xDelEx(SafeEncoder.encode(key), deletionPolicy, ids);
	}

	@Override
	public Status xGroupCreate(final String key, final String groupName, final StreamEntryId id) {
		final CommandArguments args = CommandArguments.create(key).add(groupName).add(id);
		return xGroupCreate(key, groupName, id, false, args);
	}

	@Override
	public Status xGroupCreate(final byte[] key, final byte[] groupName, final StreamEntryId id) {
		return xGroupCreate(SafeEncoder.encode(key), SafeEncoder.encode(groupName), id);
	}

	@Override
	public Status xGroupCreate(final String key, final String groupName, final StreamEntryId id,
							   final boolean makeStream) {
		final CommandArguments args = CommandArguments.create(key).add(groupName).add(id)
				.add(makeStream ? "MKSTREAM" : null);
		return xGroupCreate(key, groupName, id, makeStream, args);
	}

	@Override
	public Status xGroupCreate(final byte[] key, final byte[] groupName, final StreamEntryId id,
							   final boolean makeStream) {
		return xGroupCreate(SafeEncoder.encode(key), SafeEncoder.encode(groupName), id, makeStream);
	}

	@Override
	public Status xGroupCreate(final String key, final String groupName, final StreamEntryId id,
							   final long entriesRead) {
		final CommandArguments args = CommandArguments.create(key).add(groupName).add(id)
				.add("ENTRIESREAD", entriesRead);
		return xGroupCreate(key, groupName, id, false, args);
	}

	@Override
	public Status xGroupCreate(final byte[] key, final byte[] groupName, final StreamEntryId id,
							   final long entriesRead) {
		return xGroupCreate(SafeEncoder.encode(key), SafeEncoder.encode(groupName), id, entriesRead);
	}

	@Override
	public Status xGroupCreate(final String key, final String groupName, final StreamEntryId id,
							   final boolean makeStream, final long entriesRead) {
		final CommandArguments args = CommandArguments.create(key).add(groupName).add(id)
				.add(makeStream ? "MKSTREAM" : null).add("ENTRIESREAD", entriesRead);
		return xGroupCreate(key, groupName, id, makeStream, args);
	}

	@Override
	public Status xGroupCreate(final byte[] key, final byte[] groupName, final StreamEntryId id,
							   final boolean makeStream, final long entriesRead) {
		return xGroupCreate(SafeEncoder.encode(key), SafeEncoder.encode(groupName), id, makeStream);
	}

	@Override
	public Status xGroupCreateConsumer(final String key, final String groupName, final String consumerName) {
		final CommandArguments args = CommandArguments.create(key).add(groupName, consumerName);
		return executeCommand(Command.XGROUP, SubCommand.XGROUP_CREATECONSUMER, args,
				(cmd)->cmd.xgroupCreateConsumer(key, groupName, consumerName), new BooleanStatusConverter());
	}

	@Override
	public Status xGroupCreateConsumer(final byte[] key, final byte[] groupName, final byte[] consumerName) {
		final CommandArguments args = CommandArguments.create(key).add(groupName, consumerName);
		return executeCommand(Command.XGROUP, SubCommand.XGROUP_CREATECONSUMER, args,
				(cmd)->cmd.xgroupCreateConsumer(key, groupName, consumerName), new BooleanStatusConverter());
	}

	@Override
	public Long xGroupDelConsumer(final String key, final String groupName, final String consumerName) {
		final CommandArguments args = CommandArguments.create(key).add(groupName, consumerName);
		return executeCommand(Command.XGROUP, SubCommand.XGROUP_DELCONSUMER, args,
				(cmd)->cmd.xgroupDelConsumer(key, groupName, consumerName), (v)->v);
	}

	@Override
	public Long xGroupDelConsumer(final byte[] key, final byte[] groupName, final byte[] consumerName) {
		final CommandArguments args = CommandArguments.create(key).add(groupName, consumerName);
		return executeCommand(Command.XGROUP, SubCommand.XGROUP_DELCONSUMER, args,
				(cmd)->cmd.xgroupDelConsumer(key, groupName, consumerName), (v)->v);
	}

	@Override
	public Status xGroupDestroy(final String key, final String groupName) {
		final CommandArguments args = CommandArguments.create(key).add(groupName);
		return executeCommand(Command.XGROUP, SubCommand.XGROUP_DESTROY, args, (cmd)->cmd.xgroupDestroy(key, groupName),
				new OneStatusConverter());
	}

	@Override
	public Status xGroupDestroy(final byte[] key, final byte[] groupName) {
		final CommandArguments args = CommandArguments.create(key).add(groupName);
		return executeCommand(Command.XGROUP, SubCommand.XGROUP_DESTROY, args, (cmd)->cmd.xgroupDestroy(key, groupName),
				new OneStatusConverter());
	}

	@Override
	public Status xGroupSetId(final String key, final String groupName, final StreamEntryId id) {
		final CommandArguments args = CommandArguments.create(key).add(groupName).add(id);
		return executeCommand(Command.XGROUP, SubCommand.XGROUP_SETID, args,
				(cmd)->cmd.xgroupSetID(key, groupName, new JedisStreamEntryID(id)), new OkStatusConverter());
	}

	@Override
	public Status xGroupSetId(final byte[] key, final byte[] groupName, final StreamEntryId id) {
		return xGroupSetId(SafeEncoder.encode(key), SafeEncoder.encode(groupName), id);
	}

	@Override
	public Status xGroupSetId(final String key, final String groupName, final StreamEntryId id,
							  final long entriesRead) {
		final CommandArguments args = CommandArguments.create(key).add(groupName).add(id)
				.add("ENTRIESREAD", entriesRead);
		return executeCommand(Command.XGROUP, SubCommand.XGROUP_SETID, args,
				(cmd)->cmd.xgroupSetID(key, groupName, new JedisStreamEntryID(id)), new OkStatusConverter());
	}

	@Override
	public Status xGroupSetId(final byte[] key, final byte[] groupName, final StreamEntryId id,
							  final long entriesRead) {
		return xGroupSetId(SafeEncoder.encode(key), SafeEncoder.encode(groupName), id, entriesRead);
	}

	@Override
	public List<StreamConsumer> xInfoConsumers(final String key, final String groupName) {
		final CommandArguments args = CommandArguments.create(key).add(groupName);
		return executeCommand(Command.XINFO, SubCommand.XINFO_CONSUMERS, args,
				(cmd)->cmd.xinfoConsumers2(key, groupName), new ListConverter<>(new StreamConsumersInfoConverter()));
	}

	@Override
	public List<StreamConsumer> xInfoConsumers(final byte[] key, final byte[] groupName) {
		return xInfoConsumers(SafeEncoder.encode(key), SafeEncoder.encode(groupName));
	}

	@Override
	public List<StreamGroup> xInfoGroups(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.XINFO, SubCommand.XINFO_GROUPS, args, (cmd)->cmd.xinfoGroups(key),
				new ListConverter<>(new StreamGroupInfoConverter()));
	}

	@Override
	public List<StreamGroup> xInfoGroups(final byte[] key) {
		return xInfoGroups(SafeEncoder.encode(key));
	}

	@Override
	public Stream<String, String> xInfoStream(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.XINFO, SubCommand.XINFO_STREAM, args, (cmd)->cmd.xinfoStream(key),
				new StreamInfoConverter<>());
	}

	@Override
	public Stream<byte[], byte[]> xInfoStream(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.XINFO, SubCommand.XINFO_STREAM, args,
				(cmd)->cmd.xinfoStream(SafeEncoder.encode(key)),
				new StreamInfoConverter<>());
	}

	@Override
	public StreamFull xInfoStream(final String key, final boolean full) {
		final CommandArguments args = CommandArguments.create(key).add(full ? "FULL" : null);
		return executeCommand(Command.XINFO, SubCommand.XINFO_STREAM, args, (cmd)->cmd.xinfoStreamFull(key),
				new StreamFullInfoConverter());
	}

	@Override
	public StreamFull xInfoStream(final byte[] key, final boolean full) {
		return xInfoStream(SafeEncoder.encode(key), full);
	}

	@Override
	public StreamFull xInfoStream(final String key, final boolean full, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(full ? "FULL" : null)
				.add(Keyword.Common.COUNT, count);
		return executeCommand(Command.XINFO, SubCommand.XINFO_STREAM, args, (cmd)->cmd.xinfoStreamFull(key, count),
				new StreamFullInfoConverter());
	}

	@Override
	public StreamFull xInfoStream(final byte[] key, final boolean full, final int count) {
		return xInfoStream(SafeEncoder.encode(key), full, count);
	}

	@Override
	public Long xLen(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.XLEN, args, (cmd)->cmd.xlen(key), (v)->v);
	}

	@Override
	public Long xLen(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.XLEN, args, (cmd)->cmd.xlen(key), (v)->v);
	}

	@Override
	public StreamPendingSummary xPending(final String key, final String groupName) {
		final CommandArguments args = CommandArguments.create(key).add(groupName);
		return executeCommand(Command.XPENDING, args, (cmd)->cmd.xpending(key, groupName),
				new StreamPendingSummaryConverter());
	}

	@Override
	public StreamPendingSummary xPending(final byte[] key, final byte[] groupName) {
		return xPending(SafeEncoder.encode(key), SafeEncoder.encode(groupName));
	}

	@Override
	public List<StreamPending> xPending(final String key, final String groupName, final StreamEntryId start,
										final StreamEntryId end, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(groupName).add(start, end).add(count);
		return xPending(key, groupName, new JedisXPendingParams(start, end, count), args);
	}

	@Override
	public List<StreamPending> xPending(final byte[] key, final byte[] groupName, final StreamEntryId start,
										final StreamEntryId end, final int count) {
		return xPending(SafeEncoder.encode(key), SafeEncoder.encode(groupName), start, end, count);
	}

	@Override
	public List<StreamPending> xPending(final String key, final String groupName, final long minIdleTime,
										final StreamEntryId start, final StreamEntryId end, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(groupName).add(Keyword.Time.IDLE, minIdleTime)
				.add(start).add(end).add(count);
		return xPending(key, groupName, new JedisXPendingParams(minIdleTime, start, end, count), args);
	}

	@Override
	public List<StreamPending> xPending(final byte[] key, final byte[] groupName, final long minIdleTime,
										final StreamEntryId start, final StreamEntryId end, final int count) {
		return xPending(SafeEncoder.encode(key), SafeEncoder.encode(groupName), minIdleTime, start, end, count);
	}

	@Override
	public List<StreamPending> xPending(final String key, final String groupName, final StreamEntryId start,
										final StreamEntryId end, final int count, final String consumerName) {
		final CommandArguments args = CommandArguments.create(key).add(groupName).add(start, end).add(count)
				.add(consumerName);
		return xPending(key, groupName, new JedisXPendingParams(start, end, count, consumerName), args);
	}

	@Override
	public List<StreamPending> xPending(final byte[] key, final byte[] groupName, final StreamEntryId start,
										final StreamEntryId end, final int count, final byte[] consumerName) {
		return xPending(SafeEncoder.encode(key), SafeEncoder.encode(groupName), start, end, count,
				SafeEncoder.encode(consumerName));
	}

	@Override
	public List<StreamPending> xPending(final String key, final String groupName, final long minIdleTime,
										final StreamEntryId start, final StreamEntryId end, final int count,
										final String consumerName) {
		final CommandArguments args = CommandArguments.create(key).add(groupName).add(Keyword.Time.IDLE, minIdleTime)
				.add(start, end).add(count).add(consumerName);
		return xPending(key, groupName, new JedisXPendingParams(minIdleTime, start, end, count, consumerName), args);
	}

	@Override
	public List<StreamPending> xPending(final byte[] key, final byte[] groupName, final long minIdleTime,
										final StreamEntryId start, final StreamEntryId end, final int count,
										final byte[] consumerName) {
		return xPending(SafeEncoder.encode(key), SafeEncoder.encode(groupName), minIdleTime, start, end, count,
				SafeEncoder.encode(consumerName));
	}

	@Override
	public List<StreamEntry<String, String>> xRange(final String key, final StreamEntryId start,
													final StreamEntryId end) {
		final CommandArguments args = CommandArguments.create(key).add(start, end);
		return executeCommand(Command.XRANGE, args,
				(cmd)->cmd.xrange(key, new JedisStreamEntryID(start), new JedisStreamEntryID(end)),
				new ListConverter<>(new StreamEntryConverter<>((k)->k, (v)->v)));
	}

	@Override
	public List<StreamEntry<byte[], byte[]>> xRange(final byte[] key, final StreamEntryId start,
													final StreamEntryId end) {
		final CommandArguments args = CommandArguments.create(key).add(start, end);
		return executeCommand(Command.XRANGE, args,
				(cmd)->cmd.xrange(SafeEncoder.encode(key), new JedisStreamEntryID(start), new JedisStreamEntryID(end)),
				new ListConverter<>(new StreamEntryConverter<>(SafeEncoder::encode, SafeEncoder::encode)));
	}

	@Override
	public List<StreamEntry<String, String>> xRange(final String key, final StreamEntryId start,
													final StreamEntryId end, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(start, end).add(Keyword.Common.COUNT, count);
		return executeCommand(Command.XRANGE, args,
				(cmd)->cmd.xrange(key, new JedisStreamEntryID(start), new JedisStreamEntryID(end), count),
				new ListConverter<>(new StreamEntryConverter<>((k)->k, (v)->v)));
	}

	@Override
	public List<StreamEntry<byte[], byte[]>> xRange(final byte[] key, final StreamEntryId start,
													final StreamEntryId end, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(start, end).add(Keyword.Common.COUNT, count);
		return executeCommand(Command.XRANGE, args,
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
		return xRead(streams, new JedisXReadParams((int) block), args);
	}

	@Override
	public List<XReadInfo<String, String>> xRead(final Map<String, StreamEntryId> streams, final long block,
												 final int count) {
		final CommandArguments args = CommandArguments.create(Keyword.Common.COUNT, count).add("BLOCK", block)
				.add("STREAMS", streams);
		return xRead(streams, new JedisXReadParams((int) block, count), args);
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
	public List<XReadGroupInfo<String, String>> xReadGroup(final String groupName, final String consumerName,
														   final XReadGroupArgument xReadGroupArgument,
														   final Map<String, StreamEntryId> streams) {
		final CommandArguments args = CommandArguments.create(groupName, consumerName).add(xReadGroupArgument)
				.add("STREAMS", streams);
		final XReadGroupArgumentConverter xReadGroupArgumentConverter = new XReadGroupArgumentConverter();
		return xReadGroup(groupName, consumerName, streams, xReadGroupArgumentConverter.convert(xReadGroupArgument),
				args);
	}

	@Override
	public List<XReadGroupInfo<byte[], byte[]>> xReadGroup(final byte[] groupName, final byte[] consumerName,
														   final XReadGroupArgument xReadGroupArgument,
														   final Map<byte[], StreamEntryId> streams) {
		final CommandArguments args = CommandArguments.create(groupName, consumerName).add(xReadGroupArgument)
				.add("STREAMS", streams);
		final XReadGroupArgumentConverter xReadGroupArgumentConverter = new XReadGroupArgumentConverter();
		return xReadGroup(groupName, consumerName, streams, xReadGroupArgumentConverter.convert(xReadGroupArgument),
				args);
	}

	@Override
	public List<XReadGroupInfo<String, String>> xReadGroup(final String groupName, final String consumerName,
														   final XReadGroupArgument xReadGroupArgument,
														   final Map<String, StreamEntryId> streams, final int count) {
		final CommandArguments args = CommandArguments.create(groupName, consumerName)
				.add(Keyword.Common.COUNT, count).add(xReadGroupArgument).add("STREAMS", streams);
		final XReadGroupArgumentConverter xReadGroupArgumentConverter = new XReadGroupArgumentConverter();
		final XReadGroupParams xReadGroupParams = Optional.ofNullable(
				xReadGroupArgumentConverter.convert(xReadGroupArgument)).orElse(new XReadGroupParams());

		xReadGroupParams.count(count);

		return xReadGroup(groupName, consumerName, streams, xReadGroupParams, args);
	}

	@Override
	public List<XReadGroupInfo<byte[], byte[]>> xReadGroup(final byte[] groupName, final byte[] consumerName,
														   final XReadGroupArgument xReadGroupArgument,
														   final Map<byte[], StreamEntryId> streams, final int count) {
		final CommandArguments args = CommandArguments.create(groupName, consumerName)
				.add(Keyword.Common.COUNT, count).add(xReadGroupArgument).add("STREAMS", streams);
		final XReadGroupArgumentConverter xReadGroupArgumentConverter = new XReadGroupArgumentConverter();
		final XReadGroupParams xReadGroupParams = Optional.ofNullable(
				xReadGroupArgumentConverter.convert(xReadGroupArgument)).orElse(new XReadGroupParams());

		xReadGroupParams.count(count);

		return xReadGroup(groupName, consumerName, streams, xReadGroupParams, args);
	}

	@Override
	public List<StreamEntry<String, String>> xRevRange(final String key, final StreamEntryId end,
													   final StreamEntryId start) {
		final CommandArguments args = CommandArguments.create(key).add(end, start);
		return executeCommand(Command.XREVRANGE, args,
				(cmd)->cmd.xrevrange(key, new JedisStreamEntryID(end), new JedisStreamEntryID(start)),
				new ListConverter<>(new StreamEntryConverter<>((k)->k, (v)->v)));
	}

	@Override
	public List<StreamEntry<byte[], byte[]>> xRevRange(final byte[] key, final StreamEntryId end,
													   final StreamEntryId start) {
		final CommandArguments args = CommandArguments.create(key).add(end, start);
		return executeCommand(Command.XREVRANGE, args,
				(cmd)->cmd.xrevrange(SafeEncoder.encode(key), new JedisStreamEntryID(end),
						new JedisStreamEntryID(start)),
				new ListConverter<>(new StreamEntryConverter<>(SafeEncoder::encode, SafeEncoder::encode)));
	}

	@Override
	public List<StreamEntry<String, String>> xRevRange(final String key, final StreamEntryId end,
													   final StreamEntryId start, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(end, start).add(Keyword.Common.COUNT, count);
		return executeCommand(Command.XREVRANGE, args,
				(cmd)->cmd.xrevrange(key, new JedisStreamEntryID(end), new JedisStreamEntryID(start), count),
				new ListConverter<>(new StreamEntryConverter<>((k)->k, (v)->v)));
	}

	@Override
	public List<StreamEntry<byte[], byte[]>> xRevRange(final byte[] key, final StreamEntryId end,
													   final StreamEntryId start, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(end, start).add(Keyword.Common.COUNT, count);
		return executeCommand(Command.XREVRANGE, args,
				(cmd)->cmd.xrevrange(SafeEncoder.encode(key), new JedisStreamEntryID(end),
						new JedisStreamEntryID(start), count),
				new ListConverter<>(new StreamEntryConverter<>(SafeEncoder::encode, SafeEncoder::encode)));
	}

	@Override
	public Status xSetId(final String key, final StreamEntryId lastId) {
		final CommandArguments args = CommandArguments.create(key).add(lastId);
		return executeCommand(Command.XSETID, args);
	}

	@Override
	public Status xSetId(final byte[] key, final StreamEntryId lastId) {
		final CommandArguments args = CommandArguments.create(key).add(lastId);
		return executeCommand(Command.XSETID, args);
	}

	@Override
	public Status xSetId(final String key, final StreamEntryId lastId, final long entriesAdded) {
		final CommandArguments args = CommandArguments.create(key).add(lastId).add("ENTRIESADDED", entriesAdded);
		return executeCommand(Command.XSETID, args);
	}

	@Override
	public Status xSetId(final byte[] key, final StreamEntryId lastId, final long entriesAdded) {
		final CommandArguments args = CommandArguments.create(key).add(lastId).add("ENTRIESADDED", entriesAdded);
		return executeCommand(Command.XSETID, args);
	}

	@Override
	public Status xSetId(final String key, final StreamEntryId lastId, final StreamEntryId maxDeletedId) {
		final CommandArguments args = CommandArguments.create(key).add(lastId).add("MAXDELETEDID", maxDeletedId);
		return executeCommand(Command.XSETID, args);
	}

	@Override
	public Status xSetId(final byte[] key, final StreamEntryId lastId, final StreamEntryId maxDeletedId) {
		final CommandArguments args = CommandArguments.create(key).add(lastId).add("MAXDELETEDID", maxDeletedId);
		return executeCommand(Command.XSETID, args);
	}

	@Override
	public Status xSetId(final String key, final StreamEntryId lastId, final long entriesAdded,
						 final StreamEntryId maxDeletedId) {
		final CommandArguments args = CommandArguments.create(key).add(lastId).add("ENTRIESADDED", entriesAdded)
				.add("MAXDELETEDID", maxDeletedId);
		return executeCommand(Command.XSETID, args);
	}

	@Override
	public Status xSetId(final byte[] key, final StreamEntryId lastId, final long entriesAdded,
						 final StreamEntryId maxDeletedId) {
		final CommandArguments args = CommandArguments.create(key).add(lastId).add("ENTRIESADDED", entriesAdded)
				.add("MAXDELETEDID", maxDeletedId);
		return executeCommand(Command.XSETID, args);
	}

	@Override
	public Long xTrim(final String key, final MaxLenMinId<?> maxLenMinId) {
		final CommandArguments args = CommandArguments.create(key).add(maxLenMinId);
		return xTrim(key, maxLenMinId, new JedisXTrimParams(), args);
	}

	@Override
	public Long xTrim(final byte[] key, final MaxLenMinId<?> maxLenMinId) {
		final CommandArguments args = CommandArguments.create(key).add(maxLenMinId);
		return xTrim(key, maxLenMinId, new JedisXTrimParams(), args);
	}

	@Override
	public Long xTrim(final String key, final MaxLenMinId<?> maxLenMinId, final StreamDeletionPolicy deletionPolicy) {
		final CommandArguments args = CommandArguments.create(key).add(maxLenMinId).add(deletionPolicy);
		return xTrim(key, maxLenMinId, JedisXTrimParams.xTrimParams().deletionPolicy(deletionPolicy), args);
	}

	@Override
	public Long xTrim(final byte[] key, final MaxLenMinId<?> maxLenMinId, final StreamDeletionPolicy deletionPolicy) {
		final CommandArguments args = CommandArguments.create(key).add(maxLenMinId).add(deletionPolicy);
		return xTrim(key, maxLenMinId, JedisXTrimParams.xTrimParams().deletionPolicy(deletionPolicy), args);
	}

	@Override
	public Long xTrim(final String key, final MaxLenMinId<?> maxLenMinId, final StreamDeletionPolicy deletionPolicy,
					  final int limit) {
		final CommandArguments args = CommandArguments.create(key).add(maxLenMinId).add(Keyword.Common.LIMIT, limit)
				.add(deletionPolicy);
		return xTrim(key, maxLenMinId, JedisXTrimParams.xTrimParams().deletionPolicy(deletionPolicy).limit(limit),
				args);
	}

	@Override
	public Long xTrim(final byte[] key, final MaxLenMinId<?> maxLenMinId, final StreamDeletionPolicy deletionPolicy,
					  final int limit) {
		final CommandArguments args = CommandArguments.create(key).add(maxLenMinId).add(Keyword.Common.LIMIT, limit)
				.add(deletionPolicy);
		return xTrim(key, maxLenMinId, JedisXTrimParams.xTrimParams().deletionPolicy(deletionPolicy).limit(limit),
				args);
	}

	@Override
	public Long xTrim(final String key, final MaxLenMinId<?> maxLenMinId, final int limit) {
		final CommandArguments args = CommandArguments.create(key).add(maxLenMinId).add(Keyword.Common.LIMIT, limit);
		return xTrim(key, maxLenMinId, JedisXTrimParams.xTrimParams().limit(limit), args);
	}

	@Override
	public Long xTrim(final byte[] key, final MaxLenMinId<?> maxLenMinId, final int limit) {
		final CommandArguments args = CommandArguments.create(key).add(maxLenMinId).add(Keyword.Common.LIMIT, limit);
		return xTrim(key, maxLenMinId, JedisXTrimParams.xTrimParams().limit(limit), args);
	}

	private List<StreamEntryDeletionResult> xAckDel(
			final com.buession.redis.core.Command.Executor<UnifiedJedis, List<redis.clients.jedis.resps.StreamEntryDeletionResult>> executor,
			final CommandArguments args) {
		return executeCommand(Command.XACKDEL, args, executor,
				new ListConverter<>(new StreamEntryDeletionResultConverter()));
	}

	private AutoClaimInfo<String, String> xAutoClaim(final String key, final String groupName,
													 final String consumerName, final int minIdleTime,
													 final StreamEntryId start, final XAutoClaimParams xAutoClaimParams,
													 final CommandArguments args) {
		return executeCommand(Command.XAUTOCLAIM, args,
				(cmd)->cmd.xautoclaim(key, groupName, consumerName, minIdleTime, new JedisStreamEntryID(start),
						xAutoClaimParams), new MapEntryStreamEntryAutoClaimInfoConverter<>((k)->k, (v)->v));
	}

	private AutoClaimInfo<byte[], byte[]> xAutoClaim(final byte[] key, final byte[] groupName,
													 final byte[] consumerName, final int minIdleTime,
													 final StreamEntryId start, final XAutoClaimParams xAutoClaimParams,
													 final CommandArguments args) {
		return executeCommand(Command.XAUTOCLAIM, args,
				(cmd)->cmd.xautoclaim(SafeEncoder.encode(key), SafeEncoder.encode(groupName),
						SafeEncoder.encode(consumerName), minIdleTime, new JedisStreamEntryID(start), xAutoClaimParams),
				new MapEntryStreamEntryAutoClaimInfoConverter<>(SafeEncoder::encode, SafeEncoder::encode));
	}

	private KeyValue<StreamEntryId, List<StreamEntryId>> xAutoClaimJustId(final String key, final String groupName,
																		  final String consumerName,
																		  final int minIdleTime,
																		  final StreamEntryId start,
																		  final XAutoClaimParams xAutoClaimParams,
																		  final CommandArguments args) {
		return executeCommand(Command.XAUTOCLAIM, args,
				(cmd)->cmd.xautoclaimJustId(key, groupName, consumerName, minIdleTime, new JedisStreamEntryID(start),
						xAutoClaimParams), new MapEntryKeyValueConverter<>(new StreamEntryIDConverter(),
						new ListConverter<>(new StreamEntryIDConverter())));
	}

	private List<StreamEntry<String, String>> xClaim(final String key, final String groupName,
													 final String consumerName, final int minIdleTime,
													 final StreamEntryId[] ids, final XClaimParams xClaimParams,
													 final CommandArguments args) {
		final ArrayConverter<StreamEntryId, StreamEntryID> arrayConverter = new ArrayConverter<>(
				new StreamEntryIdConverter(), StreamEntryID.class);
		return executeCommand(Command.XCLAIM, args,
				(cmd)->cmd.xclaim(key, groupName, consumerName, minIdleTime, xClaimParams, arrayConverter.convert(ids)),
				new ListConverter<>(new StreamEntryConverter<>((k)->k, (v)->v)));
	}

	private List<StreamEntry<byte[], byte[]>> xClaim(final byte[] key, final byte[] groupName,
													 final byte[] consumerName, final int minIdleTime,
													 final StreamEntryId[] ids, final XClaimParams xClaimParams,
													 final CommandArguments args) {
		final ArrayConverter<StreamEntryId, StreamEntryID> arrayConverter = new ArrayConverter<>(
				new StreamEntryIdConverter(), StreamEntryID.class);
		return executeCommand(Command.XCLAIM, args,
				(cmd)->cmd.xclaim(SafeEncoder.encode(key), SafeEncoder.encode(groupName),
						SafeEncoder.encode(consumerName), minIdleTime, xClaimParams, arrayConverter.convert(ids)),
				new ListConverter<>(new StreamEntryConverter<>(SafeEncoder::encode, SafeEncoder::encode)));
	}

	private List<StreamEntryId> xClaimJustId(final String key, final String groupName, final String consumerName,
											 final int minIdleTime, final StreamEntryId[] ids,
											 final XClaimParams xClaimParams, final CommandArguments args) {
		final ArrayConverter<StreamEntryId, StreamEntryID> arrayConverter = new ArrayConverter<>(
				new StreamEntryIdConverter(), StreamEntryID.class);
		return executeCommand(Command.XCLAIM, args,
				(cmd)->cmd.xclaimJustId(key, groupName, consumerName, minIdleTime, xClaimParams,
						arrayConverter.convert(ids)), new ListConverter<>(new StreamEntryIDConverter()));
	}

	private Status xGroupCreate(final String key, final String groupName, final StreamEntryId id,
								final boolean makeStream, final CommandArguments args) {
		return executeCommand(Command.XGROUP, SubCommand.XGROUP_CREATE, args,
				(cmd)->cmd.xgroupCreate(key, groupName, new JedisStreamEntryID(id), makeStream),
				new OkStatusConverter());
	}

	private List<StreamPending> xPending(final String key, final String groupName, final XPendingParams xPendingParams,
										 final CommandArguments args) {
		return executeCommand(Command.XPENDING, args, (cmd)->cmd.xpending(key, groupName, xPendingParams),
				new ListConverter<>(new StreamPendingEntryConverter()));
	}

	private List<XReadInfo<String, String>> xRead(final Map<String, StreamEntryId> streams,
												  final XReadParams xReadParams, final CommandArguments args) {
		final MapConverter<String, StreamEntryId, String, StreamEntryID> mapConverter = new MapConverter<>((k)->k,
				new StreamEntryIdConverter());
		return executeCommand(Command.XREAD, args, (cmd)->cmd.xread(xReadParams, mapConverter.convert(streams)),
				new ListConverter<>(new MapEntryStreamEntryXReadInfoConverter<>((k)->k, (k)->k, (v)->v)));
	}

	private List<XReadGroupInfo<String, String>> xReadGroup(final String groupName, final String consumerName,
															final Map<String, StreamEntryId> streams,
															final XReadGroupParams xReadGroupParams,
															final CommandArguments args) {
		final MapConverter<String, StreamEntryId, String, StreamEntryID> mapConverter = new MapConverter<>((k)->k,
				new StreamEntryIdConverter());
		return executeCommand(Command.XREADGROUP, args,
				(cmd)->cmd.xreadGroup(groupName, consumerName, xReadGroupParams, mapConverter.convert(streams)),
				new ListConverter<>(new MapEntryStreamEntryXReadGroupInfoConverter<>((k)->k, (k)->k, (v)->v)));
	}

	private List<XReadGroupInfo<byte[], byte[]>> xReadGroup(final byte[] groupName, final byte[] consumerName,
															final Map<byte[], StreamEntryId> streams,
															final XReadGroupParams xReadGroupParams,
															final CommandArguments args) {
		final MapConverter<byte[], StreamEntryId, String, StreamEntryID> mapConverter = new MapConverter<>(
				SafeEncoder::encode, new StreamEntryIdConverter());
		return executeCommand(Command.XREADGROUP, args,
				(cmd)->cmd.xreadGroup(SafeEncoder.encode(groupName), SafeEncoder.encode(consumerName), xReadGroupParams,
						mapConverter.convert(streams)), new ListConverter<>(
						new MapEntryStreamEntryXReadGroupInfoConverter<>(SafeEncoder::encode, SafeEncoder::encode,
								SafeEncoder::encode)));
	}

	private Long xTrim(final String key, final MaxLenMinId<?> maxLenMinId, final XTrimParams xTrimParams,
					   final CommandArguments args) {
		return executeCommand(Command.XTRIM, args, (cmd)->cmd.xtrim(key, buildXTrimParams(xTrimParams, maxLenMinId)),
				(v)->v);
	}

	private Long xTrim(final byte[] key, final MaxLenMinId<?> maxLenMinId, final XTrimParams xTrimParams,
					   final CommandArguments args) {
		return executeCommand(Command.XTRIM, args, (cmd)->cmd.xtrim(key, buildXTrimParams(xTrimParams, maxLenMinId)),
				(v)->v);
	}

	private XTrimParams buildXTrimParams(final XTrimParams xTrimParams, final MaxLenMinId<?> maxLenMinId) {
		if(maxLenMinId instanceof MaxLenMinId.MaxLen){
			xTrimParams.maxLen(((MaxLenMinId.MaxLen) maxLenMinId).getThreshold());
		}else if(maxLenMinId instanceof MaxLenMinId.MinId){
			final StreamEntryId id = ((MaxLenMinId.MinId) maxLenMinId).getThreshold();

			if(id != null){
				xTrimParams.minId(id.toString());
			}
		}

		if(maxLenMinId != null && maxLenMinId.getApproximateExactTrimming() != null){
			switch(maxLenMinId.getApproximateExactTrimming()){
				case APPROXIMATE -> xTrimParams.approximateTrimming();
				case EXACT -> xTrimParams.exactTrimming();
			}
		}

		return xTrimParams;
	}

}
