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
package com.buession.redis.client.jedis.operations;

import com.buession.core.converter.ListConverter;
import com.buession.core.converter.MapEntryMapConverter;
import com.buession.lang.Status;
import com.buession.redis.client.jedis.JedisSentinelClient;
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
import com.buession.redis.core.internal.convert.jedis.params.StreamEntryIdConverter;
import com.buession.redis.core.internal.convert.jedis.response.StreamConsumersInfoConverter;
import com.buession.redis.core.internal.convert.jedis.response.StreamEntryConverter;
import com.buession.redis.core.internal.convert.jedis.response.StreamEntryIDConverter;
import com.buession.redis.core.internal.convert.jedis.response.StreamFullInfoConverter;
import com.buession.redis.core.internal.convert.jedis.response.StreamGroupInfoConverter;
import com.buession.redis.core.internal.convert.jedis.response.StreamInfoConverter;
import com.buession.redis.core.internal.convert.jedis.response.StreamPendingEntryConverter;
import com.buession.redis.core.internal.convert.jedis.response.StreamPendingSummaryConverter;
import com.buession.redis.core.internal.jedis.JedisStreamEntryID;
import com.buession.redis.core.internal.jedis.JedisXAddParams;
import com.buession.redis.core.internal.jedis.JedisXAutoClaimParams;
import com.buession.redis.core.internal.jedis.JedisXClaimParams;
import com.buession.redis.core.internal.jedis.JedisXPendingParams;
import com.buession.redis.core.internal.jedis.JedisXReadGroupParams;
import com.buession.redis.core.internal.jedis.JedisXReadParams;
import com.buession.redis.core.internal.jedis.JedisXTrimParams;
import redis.clients.jedis.StreamEntryID;
import redis.clients.jedis.params.XAddParams;
import redis.clients.jedis.params.XAutoClaimParams;
import redis.clients.jedis.params.XClaimParams;
import redis.clients.jedis.params.XPendingParams;
import redis.clients.jedis.params.XReadGroupParams;
import redis.clients.jedis.params.XReadParams;
import redis.clients.jedis.params.XTrimParams;
import redis.clients.jedis.resps.StreamConsumersInfo;
import redis.clients.jedis.resps.StreamGroupInfo;
import redis.clients.jedis.resps.StreamPendingEntry;

import java.util.List;
import java.util.Map;

/**
 * Jedis 哨兵模式 Stream 命令操作抽象类
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public final class JedisSentinelStreamOperations extends AbstractStreamOperations<JedisSentinelClient> {

	public JedisSentinelStreamOperations(final JedisSentinelClient client) {
		super(client);
	}

	@Override
	public Long xAck(final String key, final String groupName, final StreamEntryId... ids) {
		final CommandArguments args = CommandArguments.create(key).add(groupName)
				.add(ids);
		final StreamEntryID[] streamEntryIDS = StreamEntryIdConverter.arrayConverter().convert(ids);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, ProtocolCommand.XACK,
					(cmd)->cmd.xack(key, groupName, streamEntryIDS), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, ProtocolCommand.XACK,
					(cmd)->cmd.xack(key, groupName, streamEntryIDS), (v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.XACK,
					(cmd)->cmd.xack(key, groupName, streamEntryIDS), (v)->v)
					.run(args);
		}
	}

	@Override
	public StreamEntryId xAdd(final String key, final StreamEntryId id, final Map<String, String> hash) {
		final CommandArguments args = CommandArguments.create(key).add(id).add(hash);
		final StreamEntryID streamEntryID = JedisStreamEntryID.from(id);
		final StreamEntryIDConverter streamEntryIDConverter = new StreamEntryIDConverter();

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, ProtocolCommand.XADD,
					(cmd)->cmd.xadd(key, streamEntryID, hash), streamEntryIDConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, ProtocolCommand.XADD,
					(cmd)->cmd.xadd(key, streamEntryID, hash), streamEntryIDConverter)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.XADD, (cmd)->cmd.xadd(key, streamEntryID, hash),
					streamEntryIDConverter)
					.run(args);
		}
	}

	@Override
	public StreamEntryId xAdd(final String key, final StreamEntryId id, final Map<String, String> hash,
							  final XAddArgument xAddArgument) {
		final CommandArguments args = CommandArguments.create(key).add(id).add(hash)
				.add(xAddArgument);
		final StreamEntryID streamEntryID = JedisStreamEntryID.from(id);
		final XAddParams xAddParams = JedisXAddParams.from(xAddArgument).id(streamEntryID);
		final StreamEntryIDConverter streamEntryIDConverter = new StreamEntryIDConverter();

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, ProtocolCommand.XADD,
					(cmd)->cmd.xadd(key, hash, xAddParams), streamEntryIDConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, ProtocolCommand.XADD,
					(cmd)->cmd.xadd(key, hash, xAddParams), streamEntryIDConverter)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.XADD, (cmd)->cmd.xadd(key, hash, xAddParams),
					streamEntryIDConverter)
					.run(args);
		}
	}

	@Override
	public StreamEntryId xAdd(final byte[] key, final StreamEntryId id, final Map<byte[], byte[]> hash,
							  final XAddArgument xAddArgument) {
		final CommandArguments args = CommandArguments.create(key).add(id).add(hash)
				.add(xAddArgument);
		final StreamEntryID streamEntryID = JedisStreamEntryID.from(id);
		final XAddParams xAddParams = JedisXAddParams.from(xAddArgument).id(streamEntryID);
		final StreamEntryIDConverter.BinaryStreamEntryIdConverter binaryStreamEntryIdConverter =
				new StreamEntryIDConverter.BinaryStreamEntryIdConverter();

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, ProtocolCommand.XADD,
					(cmd)->cmd.xadd(key, hash, xAddParams), binaryStreamEntryIdConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, ProtocolCommand.XADD,
					(cmd)->cmd.xadd(key, hash, xAddParams), binaryStreamEntryIdConverter)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.XADD, (cmd)->cmd.xadd(key, hash, xAddParams),
					binaryStreamEntryIdConverter)
					.run(args);
		}
	}

	@Override
	public Map<StreamEntryId, List<StreamEntry>> xAutoClaim(final String key, final String groupName,
															final String consumerName, final int minIdleTime,
															final StreamEntryId start) {
		final CommandArguments args = CommandArguments.create(key).add(groupName)
				.add(consumerName).add(minIdleTime).add(start);
		final XAutoClaimParams xAutoClaimParams = new JedisXAutoClaimParams();

		return xAutoClaim(key, groupName, consumerName, minIdleTime, start, xAutoClaimParams, args);
	}

	@Override
	public Map<StreamEntryId, List<StreamEntry>> xAutoClaim(final String key, final String groupName,
															final String consumerName, final int minIdleTime,
															final StreamEntryId start, final long count) {
		final CommandArguments args = CommandArguments.create(key).add(groupName)
				.add(consumerName).add(minIdleTime).add(start)
				.add(count);
		final XAutoClaimParams xAutoClaimParams = new JedisXAutoClaimParams(count);

		return xAutoClaim(key, groupName, consumerName, minIdleTime, start, xAutoClaimParams, args);
	}

	@Override
	public Map<StreamEntryId, List<StreamEntryId>> xAutoClaimJustId(final String key, final String groupName,
																	final String consumerName, final int minIdleTime,
																	final StreamEntryId start) {
		final CommandArguments args = CommandArguments.create(key).add(groupName)
				.add(consumerName).add(minIdleTime).add(start);
		final XAutoClaimParams xAutoClaimParams = new JedisXAutoClaimParams();

		return xAutoClaimJustId(key, groupName, consumerName, minIdleTime, start, xAutoClaimParams, args);
	}

	@Override
	public Map<StreamEntryId, List<StreamEntryId>> xAutoClaimJustId(final String key, final String groupName,
																	final String consumerName, final int minIdleTime,
																	final StreamEntryId start, final long count) {
		final CommandArguments args = CommandArguments.create(key).add(groupName)
				.add(consumerName).add(minIdleTime).add(start)
				.add(count);
		final XAutoClaimParams xAutoClaimParams = new JedisXAutoClaimParams();

		return xAutoClaimJustId(key, groupName, consumerName, minIdleTime, start, xAutoClaimParams, args);
	}

	@Override
	public List<StreamEntry> xClaim(final String key, final String groupName, final String consumerName,
									final int minIdleTime, final StreamEntryId... ids) {
		final CommandArguments args = CommandArguments.create(key).add(groupName)
				.add(consumerName).add(minIdleTime).add(ids);
		final XClaimParams xClaimParams = new JedisXClaimParams();

		return xClaim(key, groupName, consumerName, minIdleTime, ids, xClaimParams, args);
	}

	@Override
	public List<StreamEntry> xClaim(final String key, final String groupName, final String consumerName,
									final int minIdleTime, final StreamEntryId[] ids,
									final XClaimArgument xClaimArgument) {
		final CommandArguments args = CommandArguments.create(key).add(groupName)
				.add(consumerName).add(minIdleTime).add(ids)
				.add(xClaimArgument);
		final XClaimParams xClaimParams = JedisXClaimParams.from(xClaimArgument);

		return xClaim(key, groupName, consumerName, minIdleTime, ids, xClaimParams, args);
	}

	@Override
	public List<StreamEntryId> xClaimJustId(final String key, final String groupName, final String consumerName,
											final int minIdleTime, final StreamEntryId... ids) {
		final CommandArguments args = CommandArguments.create(key).add(groupName)
				.add(consumerName).add(minIdleTime).add(ids);
		final XClaimParams xClaimParams = new JedisXClaimParams();

		return xClaimJustId(key, groupName, consumerName, minIdleTime, ids, xClaimParams, args);
	}

	@Override
	public List<StreamEntryId> xClaimJustId(final String key, final String groupName, final String consumerName,
											final int minIdleTime, final StreamEntryId[] ids,
											final XClaimArgument xClaimArgument) {
		final CommandArguments args = CommandArguments.create(key).add(groupName)
				.add(consumerName).add(minIdleTime).add(ids)
				.add(xClaimArgument);
		final XClaimParams xClaimParams = JedisXClaimParams.from(xClaimArgument);

		return xClaimJustId(key, groupName, consumerName, minIdleTime, ids, xClaimParams, args);
	}

	@Override
	public Long xDel(final String key, final StreamEntryId... ids) {
		final CommandArguments args = CommandArguments.create(key).add(ids);
		final StreamEntryID[] streamEntryIDs = StreamEntryIdConverter.arrayConverter().convert(ids);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, ProtocolCommand.XDEL,
					(cmd)->cmd.xdel(key, streamEntryIDs), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, ProtocolCommand.XDEL,
					(cmd)->cmd.xdel(key, streamEntryIDs), (v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.XDEL, (cmd)->cmd.xdel(key, streamEntryIDs),
					(v)->v)
					.run(args);
		}
	}

	@Override
	public Status xGroupCreate(final String key, final String groupName, final StreamEntryId id,
							   final boolean makeStream) {
		final CommandArguments args = CommandArguments.create(key).add(groupName)
				.add(id);
		final StreamEntryID streamEntryID = JedisStreamEntryID.from(id);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, ProtocolCommand.XGROUP_CREATE,
					(cmd)->cmd.xgroupCreate(key, groupName, streamEntryID, makeStream), okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, ProtocolCommand.XGROUP_CREATE,
					(cmd)->cmd.xgroupCreate(key, groupName, streamEntryID, makeStream), okStatusConverter)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.XGROUP_CREATE,
					(cmd)->cmd.xgroupCreate(key, groupName, streamEntryID, makeStream), okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status xGroupCreateConsumer(final String key, final String groupName, final String consumerName) {
		final CommandArguments args = CommandArguments.create(key).add(groupName)
				.add(consumerName);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, ProtocolCommand.XGROUP_CREATECONSUMER,
					(cmd)->cmd.xgroupCreateConsumer(key, groupName, consumerName), booleanStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, ProtocolCommand.XGROUP_CREATECONSUMER,
					(cmd)->cmd.xgroupCreateConsumer(key, groupName, consumerName), booleanStatusConverter)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.XGROUP_CREATECONSUMER,
					(cmd)->cmd.xgroupCreateConsumer(key, groupName, consumerName), booleanStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status xGroupCreateConsumer(final byte[] key, final byte[] groupName, final byte[] consumerName) {
		final CommandArguments args = CommandArguments.create(key).add(groupName)
				.add(consumerName);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, ProtocolCommand.XGROUP_CREATECONSUMER,
					(cmd)->cmd.xgroupCreateConsumer(key, groupName, consumerName), booleanStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, ProtocolCommand.XGROUP_CREATECONSUMER,
					(cmd)->cmd.xgroupCreateConsumer(key, groupName, consumerName), booleanStatusConverter)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.XGROUP_CREATECONSUMER,
					(cmd)->cmd.xgroupCreateConsumer(key, groupName, consumerName), booleanStatusConverter)
					.run(args);
		}
	}

	@Override
	public Long xGroupDelConsumer(final String key, final String groupName, final String consumerName) {
		final CommandArguments args = CommandArguments.create(key).add(groupName)
				.add(consumerName);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, ProtocolCommand.XGROUP_DELCONSUMER,
					(cmd)->cmd.xgroupDelConsumer(key, groupName, consumerName), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, ProtocolCommand.XGROUP_DELCONSUMER,
					(cmd)->cmd.xgroupDelConsumer(key, groupName, consumerName), (v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.XGROUP_DELCONSUMER,
					(cmd)->cmd.xgroupDelConsumer(key, groupName, consumerName), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long xGroupDelConsumer(final byte[] key, final byte[] groupName, final byte[] consumerName) {
		final CommandArguments args = CommandArguments.create(key).add(groupName)
				.add(consumerName);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, ProtocolCommand.XGROUP_DELCONSUMER,
					(cmd)->cmd.xgroupDelConsumer(key, groupName, consumerName), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, ProtocolCommand.XGROUP_DELCONSUMER,
					(cmd)->cmd.xgroupDelConsumer(key, groupName, consumerName), (v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.XGROUP_DELCONSUMER,
					(cmd)->cmd.xgroupDelConsumer(key, groupName, consumerName), (v)->v)
					.run(args);
		}
	}

	@Override
	public Status xGroupDestroy(final String key, final String groupName) {
		final CommandArguments args = CommandArguments.create(key).add(groupName);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, ProtocolCommand.XGROUP_DESTROY,
					(cmd)->cmd.xgroupDestroy(key, groupName), oneStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, ProtocolCommand.XGROUP_DESTROY,
					(cmd)->cmd.xgroupDestroy(key, groupName), oneStatusConverter)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.XGROUP_DESTROY,
					(cmd)->cmd.xgroupDestroy(key, groupName), oneStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status xGroupDestroy(final byte[] key, final byte[] groupName) {
		final CommandArguments args = CommandArguments.create(key).add(groupName);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, ProtocolCommand.XGROUP_DESTROY,
					(cmd)->cmd.xgroupDestroy(key, groupName), oneStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, ProtocolCommand.XGROUP_DESTROY,
					(cmd)->cmd.xgroupDestroy(key, groupName), oneStatusConverter)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.XGROUP_DESTROY,
					(cmd)->cmd.xgroupDestroy(key, groupName), oneStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status xGroupSetId(final String key, final String groupName, final StreamEntryId id) {
		final CommandArguments args = CommandArguments.create(key).add(groupName).add(id);
		final StreamEntryID streamEntryID = JedisStreamEntryID.from(id);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, ProtocolCommand.XGROUP_SETID,
					(cmd)->cmd.xgroupSetID(key, groupName, streamEntryID), okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, ProtocolCommand.XGROUP_SETID,
					(cmd)->cmd.xgroupSetID(key, groupName, streamEntryID), okStatusConverter)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.XGROUP_SETID,
					(cmd)->cmd.xgroupSetID(key, groupName, streamEntryID), okStatusConverter)
					.run(args);
		}
	}

	@Override
	public List<StreamConsumer> xInfoConsumers(final String key, final String groupName) {
		final CommandArguments args = CommandArguments.create(key).add(groupName);
		final ListConverter<StreamConsumersInfo, StreamConsumer> listStreamConsumersInfoConverter =
				StreamConsumersInfoConverter.listConverter();

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, ProtocolCommand.XINFO_CONSUMERS,
					(cmd)->cmd.xinfoConsumers(key, groupName), listStreamConsumersInfoConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, ProtocolCommand.XINFO_CONSUMERS,
					(cmd)->cmd.xinfoConsumers(key, groupName), listStreamConsumersInfoConverter)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.XINFO_CONSUMERS,
					(cmd)->cmd.xinfoConsumers(key, groupName), listStreamConsumersInfoConverter)
					.run(args);
		}
	}

	@Override
	public List<StreamGroup> xInfoGroups(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		final ListConverter<StreamGroupInfo, StreamGroup> listStreamGroupInfoConverter =
				StreamGroupInfoConverter.listConverter();

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, ProtocolCommand.XINFO_GROUPS, (cmd)->cmd.xinfoGroups(key),
					listStreamGroupInfoConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, ProtocolCommand.XINFO_GROUPS,
					(cmd)->cmd.xinfoGroups(key), listStreamGroupInfoConverter)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.XINFO_GROUPS, (cmd)->cmd.xinfoGroups(key),
					listStreamGroupInfoConverter)
					.run(args);
		}
	}

	@Override
	public Stream xInfoStream(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		final StreamInfoConverter streamInfoConverter = new StreamInfoConverter();

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, ProtocolCommand.XINFO_STREAM, (cmd)->cmd.xinfoStream(key),
					streamInfoConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, ProtocolCommand.XINFO_STREAM,
					(cmd)->cmd.xinfoStream(key), streamInfoConverter)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.XINFO_STREAM, (cmd)->cmd.xinfoStream(key),
					streamInfoConverter)
					.run(args);
		}
	}

	@Override
	public StreamFull xInfoStream(final String key, final boolean full) {
		final CommandArguments args = CommandArguments.create(key).add(full);
		final StreamFullInfoConverter streamFullInfoConverter = new StreamFullInfoConverter();

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, ProtocolCommand.XINFO_STREAM,
					(cmd)->cmd.xinfoStreamFull(key), streamFullInfoConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, ProtocolCommand.XINFO_STREAM,
					(cmd)->cmd.xinfoStreamFull(key), streamFullInfoConverter)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.XINFO_STREAM, (cmd)->cmd.xinfoStreamFull(key),
					streamFullInfoConverter)
					.run(args);
		}
	}

	@Override
	public StreamFull xInfoStream(final String key, final boolean full, final long count) {
		final CommandArguments args = CommandArguments.create(key).add(full).add(count);
		final StreamFullInfoConverter streamFullInfoConverter = new StreamFullInfoConverter();

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, ProtocolCommand.XINFO_STREAM,
					(cmd)->cmd.xinfoStreamFull(key, (int) count), streamFullInfoConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, ProtocolCommand.XINFO_STREAM,
					(cmd)->cmd.xinfoStreamFull(key, (int) count), streamFullInfoConverter)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.XINFO_STREAM,
					(cmd)->cmd.xinfoStreamFull(key, (int) count), streamFullInfoConverter)
					.run(args);
		}
	}

	@Override
	public Long xLen(final String key) {
		final CommandArguments args = CommandArguments.create(key);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, ProtocolCommand.XLEN, (cmd)->cmd.xlen(key), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, ProtocolCommand.XLEN, (cmd)->cmd.xlen(key), (v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.XLEN, (cmd)->cmd.xlen(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long xLen(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, ProtocolCommand.XLEN, (cmd)->cmd.xlen(key), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, ProtocolCommand.XLEN, (cmd)->cmd.xlen(key), (v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.XLEN, (cmd)->cmd.xlen(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public StreamPendingSummary xPending(final String key, final String groupName) {
		final CommandArguments args = CommandArguments.create(key).add(groupName);
		final StreamPendingSummaryConverter streamPendingSummaryConverter = new StreamPendingSummaryConverter();

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, ProtocolCommand.XPENDING,
					(cmd)->cmd.xpending(key, groupName), streamPendingSummaryConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, ProtocolCommand.XPENDING,
					(cmd)->cmd.xpending(key, groupName), streamPendingSummaryConverter)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.XPENDING, (cmd)->cmd.xpending(key, groupName),
					streamPendingSummaryConverter)
					.run(args);
		}
	}

	@Override
	public List<StreamPending> xPending(final String key, final String groupName, final long minIdleTime) {
		final CommandArguments args = CommandArguments.create(key).add(groupName)
				.add(minIdleTime);
		final XPendingParams xPendingParams = new JedisXPendingParams(minIdleTime);

		return xPending(key, groupName, xPendingParams, args);
	}

	@Override
	public List<StreamPending> xPending(final String key, final String groupName, final StreamEntryId start,
										final StreamEntryId end, final long count) {
		final CommandArguments args = CommandArguments.create(key).add(groupName)
				.add(start).add(end).add(count);
		final XPendingParams xPendingParams = new JedisXPendingParams(start, end, count);

		return xPending(key, groupName, xPendingParams, args);
	}

	@Override
	public List<StreamPending> xPending(final String key, final String groupName, final String consumerName) {
		final CommandArguments args = CommandArguments.create(key).add(groupName)
				.add(consumerName);
		final XPendingParams xPendingParams = new JedisXPendingParams(consumerName);

		return xPending(key, groupName, xPendingParams, args);
	}

	@Override
	public List<StreamPending> xPending(final String key, final String groupName, final long minIdleTime,
										final StreamEntryId start, final StreamEntryId end, final long count) {
		final CommandArguments args = CommandArguments.create(key).add(groupName)
				.add(minIdleTime).add(start).add(end).add(count);
		final XPendingParams xPendingParams = new JedisXPendingParams(minIdleTime, start, end, count);

		return xPending(key, groupName, xPendingParams, args);
	}

	@Override
	public List<StreamPending> xPending(final String key, final String groupName, final long minIdleTime,
										final String consumerName) {
		final CommandArguments args = CommandArguments.create(key).add(groupName)
				.add(minIdleTime).add(consumerName);
		final XPendingParams xPendingParams = new JedisXPendingParams(minIdleTime, consumerName);

		return xPending(key, groupName, xPendingParams, args);
	}

	@Override
	public List<StreamPending> xPending(final String key, final String groupName, final StreamEntryId start,
										final StreamEntryId end, final long count, final String consumerName) {
		final CommandArguments args = CommandArguments.create(key).add(groupName)
				.add(consumerName);
		final XPendingParams xPendingParams = new JedisXPendingParams(start, end, count, consumerName);

		return xPending(key, groupName, xPendingParams, args);
	}

	@Override
	public List<StreamPending> xPending(final String key, final String groupName, final long minIdleTime,
										final StreamEntryId start, final StreamEntryId end, final long count,
										final String consumerName) {
		final CommandArguments args = CommandArguments.create(key).add(groupName)
				.add(minIdleTime).add(consumerName);
		final XPendingParams xPendingParams = new JedisXPendingParams(minIdleTime, start, end, count, consumerName);

		return xPending(key, groupName, xPendingParams, args);
	}

	@Override
	public List<StreamEntry> xRange(final String key, final StreamEntryId start, final StreamEntryId end) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end);
		final StreamEntryID startStreamEntryID = JedisStreamEntryID.from(start);
		final StreamEntryID endStreamEntryID = JedisStreamEntryID.from(end);
		final ListConverter<redis.clients.jedis.resps.StreamEntry, StreamEntry> listStreamEntryConverter =
				StreamEntryConverter.listConverter();

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, ProtocolCommand.XRANGE,
					(cmd)->cmd.xrange(key, startStreamEntryID, endStreamEntryID), listStreamEntryConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, ProtocolCommand.XRANGE,
					(cmd)->cmd.xrange(key, startStreamEntryID, endStreamEntryID), listStreamEntryConverter)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.XRANGE,
					(cmd)->cmd.xrange(key, startStreamEntryID, endStreamEntryID), listStreamEntryConverter)
					.run(args);
		}
	}

	@Override
	public List<StreamEntry> xRange(final String key, final StreamEntryId start, final StreamEntryId end,
									final long count) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end);
		final StreamEntryID startStreamEntryID = JedisStreamEntryID.from(start);
		final StreamEntryID endStreamEntryID = JedisStreamEntryID.from(end);
		final ListConverter<redis.clients.jedis.resps.StreamEntry, StreamEntry> listStreamEntryConverter =
				StreamEntryConverter.listConverter();

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, ProtocolCommand.XRANGE,
					(cmd)->cmd.xrange(key, startStreamEntryID, endStreamEntryID, (int) count), listStreamEntryConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, ProtocolCommand.XRANGE,
					(cmd)->cmd.xrange(key, startStreamEntryID, endStreamEntryID, (int) count), listStreamEntryConverter)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.XRANGE,
					(cmd)->cmd.xrange(key, startStreamEntryID, endStreamEntryID, (int) count), listStreamEntryConverter)
					.run(args);
		}
	}

	@Override
	public List<Map<String, List<StreamEntry>>> xRead(final Map<String, StreamEntryId> streams) {
		final CommandArguments args = CommandArguments.create(streams);
		final XReadParams xReadParams = new JedisXReadParams();

		return xRead(streams, xReadParams, args);
	}

	@Override
	public List<Map<String, List<StreamEntry>>> xRead(final long count, final Map<String, StreamEntryId> streams) {
		final CommandArguments args = CommandArguments.create(count).add(streams);
		final XReadParams xReadParams = new JedisXReadParams(count);

		return xRead(streams, xReadParams, args);
	}

	@Override
	public List<Map<String, List<StreamEntry>>> xRead(final int block, final Map<String, StreamEntryId> streams) {
		final CommandArguments args = CommandArguments.create(block).add(streams);
		final XReadParams xReadParams = new JedisXReadParams(block);

		return xRead(streams, xReadParams, args);
	}

	@Override
	public List<Map<String, List<StreamEntry>>> xRead(final long count, final int block,
													  final Map<String, StreamEntryId> streams) {
		final CommandArguments args = CommandArguments.create(count).add(block)
				.add(streams);
		final XReadParams xReadParams = new JedisXReadParams(count, block);

		return xRead(streams, xReadParams, args);
	}

	@Override
	public List<Map<String, List<StreamEntry>>> xReadGroup(final String groupName, final String consumerName,
														   final Map<String, StreamEntryId> streams) {
		final CommandArguments args = CommandArguments.create(groupName).add(consumerName)
				.add(streams);
		final XReadGroupParams xReadGroupParams = new JedisXReadGroupParams();

		return xReadGroup(groupName, consumerName, streams, xReadGroupParams, args);
	}

	@Override
	public List<Map<String, List<StreamEntry>>> xReadGroup(final String groupName, final String consumerName,
														   final long count, final Map<String, StreamEntryId> streams) {
		final CommandArguments args = CommandArguments.create(groupName).add(consumerName)
				.add(count).add(streams);
		final XReadGroupParams xReadGroupParams = new JedisXReadGroupParams(count);

		return xReadGroup(groupName, consumerName, streams, xReadGroupParams, args);
	}

	@Override
	public List<Map<String, List<StreamEntry>>> xReadGroup(final String groupName, final String consumerName,
														   final int block, final Map<String, StreamEntryId> streams) {
		final CommandArguments args = CommandArguments.create(groupName).add(consumerName)
				.add(block).add(streams);
		final XReadGroupParams xReadGroupParams = new JedisXReadGroupParams(block);

		return xReadGroup(groupName, consumerName, streams, xReadGroupParams, args);
	}

	@Override
	public List<Map<String, List<StreamEntry>>> xReadGroup(final String groupName, final String consumerName,
														   final boolean isNoAck,
														   final Map<String, StreamEntryId> streams) {
		final CommandArguments args = CommandArguments.create(groupName).add(consumerName)
				.add(isNoAck).add(streams);
		final XReadGroupParams xReadGroupParams = new JedisXReadGroupParams(isNoAck);

		return xReadGroup(groupName, consumerName, streams, xReadGroupParams, args);
	}

	@Override
	public List<Map<String, List<StreamEntry>>> xReadGroup(final String groupName, final String consumerName,
														   final long count, final int block,
														   final Map<String, StreamEntryId> streams) {
		final CommandArguments args = CommandArguments.create(groupName).add(consumerName)
				.add(count).add(block).add(streams);
		final XReadGroupParams xReadGroupParams = new JedisXReadGroupParams(count, block);

		return xReadGroup(groupName, consumerName, streams, xReadGroupParams, args);
	}

	@Override
	public List<Map<String, List<StreamEntry>>> xReadGroup(final String groupName, final String consumerName,
														   final long count, final boolean isNoAck,
														   final Map<String, StreamEntryId> streams) {
		final CommandArguments args = CommandArguments.create(groupName).add(consumerName)
				.add(count).add(isNoAck).add(streams);
		final XReadGroupParams xReadGroupParams = new JedisXReadGroupParams(count, isNoAck);

		return xReadGroup(groupName, consumerName, streams, xReadGroupParams, args);
	}

	@Override
	public List<Map<String, List<StreamEntry>>> xReadGroup(final String groupName, final String consumerName,
														   final int block, final boolean isNoAck,
														   final Map<String, StreamEntryId> streams) {
		final CommandArguments args = CommandArguments.create(groupName).add(consumerName)
				.add(block).add(isNoAck).add(streams);
		final XReadGroupParams xReadGroupParams = new JedisXReadGroupParams(block, isNoAck);

		return xReadGroup(groupName, consumerName, streams, xReadGroupParams, args);
	}

	@Override
	public List<Map<String, List<StreamEntry>>> xReadGroup(final String groupName, final String consumerName,
														   final long count, final int block, final boolean isNoAck,
														   final Map<String, StreamEntryId> streams) {
		final CommandArguments args = CommandArguments.create(groupName).add(consumerName)
				.add(count).add(block).add(isNoAck).add(streams);
		final XReadGroupParams xReadGroupParams = new JedisXReadGroupParams(count, block, isNoAck);

		return xReadGroup(groupName, consumerName, streams, xReadGroupParams, args);
	}

	@Override
	public List<StreamEntry> xRevRange(final String key, final StreamEntryId end, final StreamEntryId start) {
		final CommandArguments args = CommandArguments.create(key).add(end).add(start);
		final StreamEntryID endID = JedisStreamEntryID.from(end);
		final StreamEntryID startID = JedisStreamEntryID.from(start);
		final ListConverter<redis.clients.jedis.resps.StreamEntry, StreamEntry> listStreamEntryConverter =
				StreamEntryConverter.listConverter();

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, ProtocolCommand.XREVRANGE,
					(cmd)->cmd.xrevrange(key, endID, startID), listStreamEntryConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, ProtocolCommand.XREVRANGE,
					(cmd)->cmd.xrevrange(key, endID, startID), listStreamEntryConverter)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.XREVRANGE,
					(cmd)->cmd.xrevrange(key, endID, startID), listStreamEntryConverter)
					.run(args);
		}
	}

	@Override
	public List<StreamEntry> xRevRange(final String key, final StreamEntryId end, final StreamEntryId start,
									   final long count) {
		final CommandArguments args = CommandArguments.create(key).add(end).add(start)
				.add(count);
		final StreamEntryID endID = JedisStreamEntryID.from(end);
		final StreamEntryID startID = JedisStreamEntryID.from(start);
		final ListConverter<redis.clients.jedis.resps.StreamEntry, StreamEntry> listStreamEntryConverter =
				StreamEntryConverter.listConverter();

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, ProtocolCommand.XREVRANGE,
					(cmd)->cmd.xrevrange(key, endID, startID, (int) count), listStreamEntryConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, ProtocolCommand.XREVRANGE,
					(cmd)->cmd.xrevrange(key, endID, startID, (int) count), listStreamEntryConverter)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.XREVRANGE,
					(cmd)->cmd.xrevrange(key, endID, startID, (int) count), listStreamEntryConverter)
					.run(args);
		}
	}

	@Override
	public Long xTrim(final String key, final XTrimArgument xTrimArgument) {
		final CommandArguments args = CommandArguments.create(key).add(xTrimArgument);
		final XTrimParams xTrimParams = JedisXTrimParams.from(xTrimArgument);

		return xTrim(key, xTrimParams, args);
	}

	@Override
	public Long xTrim(final byte[] key, final XTrimArgument xTrimArgument) {
		final CommandArguments args = CommandArguments.create(key).add(xTrimArgument);
		final XTrimParams xTrimParams = JedisXTrimParams.from(xTrimArgument);

		return xTrim(key, xTrimParams, args);
	}

	@Override
	public Long xTrim(final String key, final XTrimArgument xTrimArgument, final long limit) {
		final CommandArguments args = CommandArguments.create(key).add(xTrimArgument)
				.add(limit);
		final XTrimParams xTrimParams = JedisXTrimParams.from(xTrimArgument).limit(limit);

		return xTrim(key, xTrimParams, args);
	}

	@Override
	public Long xTrim(final byte[] key, final XTrimArgument xTrimArgument, final long limit) {
		final CommandArguments args = CommandArguments.create(key).add(xTrimArgument)
				.add(limit);
		final XTrimParams xTrimParams = JedisXTrimParams.from(xTrimArgument).limit(limit);

		return xTrim(key, xTrimParams, args);
	}

	private Map<StreamEntryId, List<StreamEntry>> xAutoClaim(final String key, final String groupName,
															 final String consumerName, final int minIdleTime,
															 final StreamEntryId start,
															 final XAutoClaimParams xAutoClaimParams,
															 final CommandArguments args) {
		final StreamEntryID startStreamEntryID = JedisStreamEntryID.from(start);
		final StreamEntryConverter.MapEntryStreamEntryConverter<StreamEntryID, StreamEntryId> mapEntryStreamEntryConverter =
				new StreamEntryConverter.MapEntryStreamEntryConverter<>(new StreamEntryIDConverter());

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, ProtocolCommand.XAUTOCLAIM,
					(cmd)->cmd.xautoclaim(key, groupName, consumerName, minIdleTime, startStreamEntryID,
							xAutoClaimParams), mapEntryStreamEntryConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, ProtocolCommand.XAUTOCLAIM,
					(cmd)->cmd.xautoclaim(key, groupName, consumerName, minIdleTime, startStreamEntryID,
							xAutoClaimParams), mapEntryStreamEntryConverter)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.XAUTOCLAIM,
					(cmd)->cmd.xautoclaim(key, groupName, consumerName, minIdleTime, startStreamEntryID,
							xAutoClaimParams), mapEntryStreamEntryConverter)
					.run(args);
		}
	}

	private Map<StreamEntryId, List<StreamEntryId>> xAutoClaimJustId(final String key, final String groupName,
																	 final String consumerName, final int minIdleTime,
																	 final StreamEntryId start,
																	 final XAutoClaimParams xAutoClaimParams,
																	 final CommandArguments args) {
		final StreamEntryID startStreamEntryID = JedisStreamEntryID.from(start);
		final MapEntryMapConverter<StreamEntryID, List<StreamEntryID>, StreamEntryId, List<StreamEntryId>> mapEntryStreamEntryIdConverter = StreamEntryIDConverter.mapEntryMapConverter();

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, ProtocolCommand.XAUTOCLAIM,
					(cmd)->cmd.xautoclaimJustId(key, groupName, consumerName, minIdleTime, startStreamEntryID,
							xAutoClaimParams), mapEntryStreamEntryIdConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, ProtocolCommand.XAUTOCLAIM,
					(cmd)->cmd.xautoclaimJustId(key, groupName, consumerName, minIdleTime, startStreamEntryID,
							xAutoClaimParams), mapEntryStreamEntryIdConverter)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.XAUTOCLAIM,
					(cmd)->cmd.xautoclaimJustId(key, groupName, consumerName, minIdleTime, startStreamEntryID,
							xAutoClaimParams), mapEntryStreamEntryIdConverter)
					.run(args);
		}
	}

	private List<StreamEntry> xClaim(final String key, final String groupName, final String consumerName,
									 final int minIdleTime, final StreamEntryId[] ids, final XClaimParams xClaimParams,
									 final CommandArguments args) {
		final StreamEntryID[] streamEntryIDs = StreamEntryIdConverter.arrayConverter().convert(ids);
		final ListConverter<redis.clients.jedis.resps.StreamEntry, StreamEntry> listStreamEntryConverter =
				StreamEntryConverter.listConverter();

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, ProtocolCommand.XCLAIM,
					(cmd)->cmd.xclaim(key, groupName, consumerName, minIdleTime, xClaimParams, streamEntryIDs),
					listStreamEntryConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, ProtocolCommand.XCLAIM,
					(cmd)->cmd.xclaim(key, groupName, consumerName, minIdleTime, xClaimParams, streamEntryIDs),
					listStreamEntryConverter)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.XCLAIM,
					(cmd)->cmd.xclaim(key, groupName, consumerName, minIdleTime, xClaimParams, streamEntryIDs),
					listStreamEntryConverter)
					.run(args);
		}
	}

	private List<StreamEntryId> xClaimJustId(final String key, final String groupName, final String consumerName,
											 final int minIdleTime, final StreamEntryId[] ids,
											 final XClaimParams xClaimParams, final CommandArguments args) {
		final StreamEntryID[] streamEntryIDs = StreamEntryIdConverter.arrayConverter().convert(ids);
		final ListConverter<StreamEntryID, StreamEntryId> listStreamEntryIDConverter = StreamEntryIDConverter.listConverter();

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, ProtocolCommand.XCLAIM,
					(cmd)->cmd.xclaimJustId(key, groupName, consumerName, minIdleTime, xClaimParams, streamEntryIDs),
					listStreamEntryIDConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, ProtocolCommand.XCLAIM,
					(cmd)->cmd.xclaimJustId(key, groupName, consumerName, minIdleTime, xClaimParams, streamEntryIDs),
					listStreamEntryIDConverter)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.XCLAIM,
					(cmd)->cmd.xclaimJustId(key, groupName, consumerName, minIdleTime, xClaimParams, streamEntryIDs),
					listStreamEntryIDConverter)
					.run(args);
		}
	}

	private List<StreamPending> xPending(final String key, final String groupName,
										 final XPendingParams xPendingParams, final CommandArguments args) {
		final ListConverter<StreamPendingEntry, StreamPending> listStreamPendingEntryConverter =
				StreamPendingEntryConverter.listConverter();

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, ProtocolCommand.XPENDING,
					(cmd)->cmd.xpending(key, groupName, xPendingParams), listStreamPendingEntryConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, ProtocolCommand.XPENDING,
					(cmd)->cmd.xpending(key, groupName, xPendingParams), listStreamPendingEntryConverter)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.XPENDING,
					(cmd)->cmd.xpending(key, groupName, xPendingParams), listStreamPendingEntryConverter)
					.run(args);
		}
	}

	private List<Map<String, List<StreamEntry>>> xRead(final Map<String, StreamEntryId> streams,
													   final XReadParams xReadParams, final CommandArguments args) {
		final Map<String, StreamEntryID> stringStreamEntryIDMap = StreamEntryIdConverter.<String>mapConverter().convert(
				streams);
		final StreamEntryConverter.ListMapEntryStreamEntryConverter<String, String> listMapEntryStreamEntryConverter =
				new StreamEntryConverter.ListMapEntryStreamEntryConverter<>((k)->k);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, ProtocolCommand.XREAD,
					(cmd)->cmd.xread(xReadParams, stringStreamEntryIDMap), listMapEntryStreamEntryConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, ProtocolCommand.XREAD,
					(cmd)->cmd.xread(xReadParams, stringStreamEntryIDMap), listMapEntryStreamEntryConverter)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.XREAD,
					(cmd)->cmd.xread(xReadParams, stringStreamEntryIDMap), listMapEntryStreamEntryConverter)
					.run(args);
		}
	}

	private List<Map<String, List<StreamEntry>>> xReadGroup(final String groupName, final String consumerName,
															final Map<String, StreamEntryId> streams,
															final XReadGroupParams xReadGroupParams,
															final CommandArguments args) {
		final Map<String, StreamEntryID> stringStreamEntryIDMap = StreamEntryIdConverter.<String>mapConverter().convert(
				streams);
		final StreamEntryConverter.ListMapEntryStreamEntryConverter<String, String> listMapEntryStreamEntryConverter =
				new StreamEntryConverter.ListMapEntryStreamEntryConverter<>((k)->k);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, ProtocolCommand.XREADGROUP,
					(cmd)->cmd.xreadGroup(groupName, consumerName, xReadGroupParams, stringStreamEntryIDMap),
					listMapEntryStreamEntryConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, ProtocolCommand.XREADGROUP,
					(cmd)->cmd.xreadGroup(groupName, consumerName, xReadGroupParams, stringStreamEntryIDMap),
					listMapEntryStreamEntryConverter)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.XREADGROUP,
					(cmd)->cmd.xreadGroup(groupName, consumerName, xReadGroupParams, stringStreamEntryIDMap),
					listMapEntryStreamEntryConverter)
					.run(args);
		}
	}

	private Long xTrim(final String key, final XTrimParams xTrimParams, final CommandArguments args) {
		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, ProtocolCommand.XTRIM, (cmd)->cmd.xtrim(key, xTrimParams),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, ProtocolCommand.XTRIM,
					(cmd)->cmd.xtrim(key, xTrimParams), (v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.XTRIM, (cmd)->cmd.xtrim(key, xTrimParams), (v)->v)
					.run(args);
		}
	}

	private Long xTrim(final byte[] key, final XTrimParams xTrimParams, final CommandArguments args) {
		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, ProtocolCommand.XTRIM, (cmd)->cmd.xtrim(key, xTrimParams),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, ProtocolCommand.XTRIM,
					(cmd)->cmd.xtrim(key, xTrimParams), (v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.XTRIM, (cmd)->cmd.xtrim(key, xTrimParams), (v)->v)
					.run(args);
		}
	}

}
