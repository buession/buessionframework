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
package com.buession.redis.core.operations;

import com.buession.core.collect.Arrays;
import com.buession.lang.Status;
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
import com.buession.redis.core.command.StreamCommands;
import com.buession.redis.core.command.args.XAddArgument;
import com.buession.redis.core.command.args.XClaimArgument;

import java.util.List;
import java.util.Map;

/**
 * Stream 命令
 *
 * <p>详情说明 <a href="https://redis.io/commands/?group=stream" target="_blank">https://redis.io/commands/?group=stream</a></p>
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public interface StreamOperations extends StreamCommands, RedisOperations {

	@Override
	default Long xAck(final String key, final String groupName, final StreamEntryId... ids) {
		return execute((client)->client.streamCommands().xAck(key, groupName, ids));
	}

	@Override
	default Long xAck(final byte[] key, final byte[] groupName, final StreamEntryId... ids) {
		return execute((client)->client.streamCommands().xAck(key, groupName, ids));
	}

	/**
	 * The XACK command removes one or multiple messages from the Pending Entries List (PEL) of a stream consumer group
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/xack/" target="_blank">https://redis.io/commands/xack/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param groupName
	 * 		Group Name
	 * @param ids
	 * 		一个或多个 ID
	 *
	 * @return The command returns the number of messages successfully acknowledged
	 */
	default Long xAck(final String key, final String groupName, final String[] ids) {
		final StreamEntryId[] streamEntryIds = Arrays.map(ids, StreamEntryId.class, StreamEntryId::new);
		return xAck(key, groupName, streamEntryIds);
	}

	/**
	 * The XACK command removes one or multiple messages from the Pending Entries List (PEL) of a stream consumer group
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/xack/" target="_blank">https://redis.io/commands/xack/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param groupName
	 * 		Group Name
	 * @param ids
	 * 		一个或多个 ID
	 *
	 * @return The command returns the number of messages successfully acknowledged
	 */
	default Long xAck(final byte[] key, final byte[] groupName, final byte[][] ids) {
		final StreamEntryId[] streamEntryIds = Arrays.map(ids, StreamEntryId.class, StreamEntryId::new);
		return xAck(key, groupName, streamEntryIds);
	}

	@Override
	default List<StreamEntryDeletionResult> xAckDel(final String key, final String groupName,
													final StreamEntryId... ids) {
		return execute((client)->client.streamCommands().xAckDel(key, groupName, ids));
	}

	@Override
	default List<StreamEntryDeletionResult> xAckDel(final byte[] key, final byte[] groupName,
													final StreamEntryId... ids) {
		return execute((client)->client.streamCommands().xAckDel(key, groupName, ids));
	}

	@Override
	default List<StreamEntryDeletionResult> xAckDel(final String key, final String groupName,
													final StreamDeletionPolicy deletionPolicy,
													final StreamEntryId... ids) {
		return execute((client)->client.streamCommands().xAckDel(key, groupName, deletionPolicy, ids));
	}

	@Override
	default List<StreamEntryDeletionResult> xAckDel(final byte[] key, final byte[] groupName,
													final StreamDeletionPolicy deletionPolicy,
													final StreamEntryId... ids) {
		return execute((client)->client.streamCommands().xAckDel(key, groupName, deletionPolicy, ids));
	}

	/**
	 * Acknowledges and conditionally deletes one or multiple entries (messages) for a stream consumer group at the specified key.
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/xackdel/" target="_blank">https://redis.io/docs/latest/commands/xackdel/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param groupName
	 * 		Group Name
	 * @param ids
	 * 		一个或多个 ID
	 *
	 * @return The command returns the number of messages successfully acknowledged
	 */
	default List<StreamEntryDeletionResult> xAckDel(final String key, final String groupName, final String... ids) {
		final StreamEntryId[] streamEntryIds = Arrays.map(ids, StreamEntryId.class, StreamEntryId::new);
		return execute((client)->client.streamCommands().xAckDel(key, groupName, streamEntryIds));
	}

	/**
	 * Acknowledges and conditionally deletes one or multiple entries (messages) for a stream consumer group at the specified key.
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/xackdel/" target="_blank">https://redis.io/docs/latest/commands/xackdel/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param groupName
	 * 		Group Name
	 * @param ids
	 * 		一个或多个 ID
	 *
	 * @return The command returns the number of messages successfully acknowledged
	 */
	default List<StreamEntryDeletionResult> xAckDel(final byte[] key, final byte[] groupName, final byte[]... ids) {
		final StreamEntryId[] streamEntryIds = Arrays.map(ids, StreamEntryId.class, StreamEntryId::new);
		return execute((client)->client.streamCommands().xAckDel(key, groupName, streamEntryIds));
	}

	/**
	 * Acknowledges and conditionally deletes one or multiple entries (messages) for a stream consumer group at the specified key.
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/xackdel/" target="_blank">https://redis.io/docs/latest/commands/xackdel/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param groupName
	 * 		Group Name
	 * @param deletionPolicy
	 * 		删除策略
	 * @param ids
	 * 		一个或多个 ID
	 *
	 * @return The command returns the number of messages successfully acknowledged
	 */
	default List<StreamEntryDeletionResult> xAckDel(final String key, final String groupName,
													final StreamDeletionPolicy deletionPolicy, final String... ids) {
		final StreamEntryId[] streamEntryIds = Arrays.map(ids, StreamEntryId.class, StreamEntryId::new);
		return execute((client)->client.streamCommands().xAckDel(key, groupName, deletionPolicy, streamEntryIds));
	}

	/**
	 * Acknowledges and conditionally deletes one or multiple entries (messages) for a stream consumer group at the specified key.
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/xackdel/" target="_blank">https://redis.io/docs/latest/commands/xackdel/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param groupName
	 * 		Group Name
	 * @param deletionPolicy
	 * 		删除策略
	 * @param ids
	 * 		一个或多个 ID
	 *
	 * @return The command returns the number of messages successfully acknowledged
	 */
	default List<StreamEntryDeletionResult> xAckDel(final byte[] key, final byte[] groupName,
													final StreamDeletionPolicy deletionPolicy, final byte[]... ids) {
		final StreamEntryId[] streamEntryIds = Arrays.map(ids, StreamEntryId.class, StreamEntryId::new);
		return execute((client)->client.streamCommands().xAckDel(key, groupName, deletionPolicy, streamEntryIds));
	}

	@Override
	default StreamEntryId xAdd(final String key, final StreamEntryId id, final Map<String, String> hash) {
		return execute((client)->client.streamCommands().xAdd(key, id, hash));
	}

	@Override
	default StreamEntryId xAdd(final byte[] key, final StreamEntryId id, Map<byte[], byte[]> hash) {
		return execute((client)->client.streamCommands().xAdd(key, id, hash));
	}

	@Override
	default StreamEntryId xAdd(final String key, final StreamEntryId id, final Map<String, String> hash,
							   final XAddArgument xAddArgument) {
		return execute((client)->client.streamCommands().xAdd(key, id, hash, xAddArgument));
	}

	@Override
	default StreamEntryId xAdd(final byte[] key, final StreamEntryId id, final Map<byte[], byte[]> hash,
							   final XAddArgument xAddArgument) {
		return execute((client)->client.streamCommands().xAdd(key, id, hash, xAddArgument));
	}

	/**
	 * Appends the specified stream entry to the stream at the specified key.
	 * If the key does not exist, as a side effect of running this command the key is created with a stream value
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/xadd/" target="_blank">https://redis.io/commands/xadd/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param id
	 * 		Id
	 * @param hash
	 * 		Hash
	 *
	 * @return {@link StreamEntryId}
	 */
	default StreamEntryId xAdd(final String key, final String id, final Map<String, String> hash) {
		return xAdd(key, new StreamEntryId(id), hash);
	}

	/**
	 * Appends the specified stream entry to the stream at the specified key.
	 * If the key does not exist, as a side effect of running this command the key is created with a stream value
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/xadd/" target="_blank">https://redis.io/commands/xadd/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param id
	 * 		Id
	 * @param hash
	 * 		Hash
	 *
	 * @return {@link StreamEntryId}
	 */
	default StreamEntryId xAdd(final byte[] key, byte[] id, final Map<byte[], byte[]> hash) {
		return xAdd(key, new StreamEntryId(id), hash);
	}

	/**
	 * Appends the specified stream entry to the stream at the specified key.
	 * If the key does not exist, as a side effect of running this command the key is created with a stream value
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/xadd/" target="_blank">https://redis.io/commands/xadd/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param id
	 * 		Id
	 * @param hash
	 * 		Hash
	 * @param xAddArgument
	 *        {@link XAddArgument}
	 *
	 * @return {@link StreamEntryId}
	 */
	default StreamEntryId xAdd(final String key, final String id, final Map<String, String> hash,
							   final XAddArgument xAddArgument) {
		return xAdd(key, new StreamEntryId(id), hash, xAddArgument);
	}

	/**
	 * Appends the specified stream entry to the stream at the specified key.
	 * If the key does not exist, as a side effect of running this command the key is created with a stream value
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/xadd/" target="_blank">https://redis.io/commands/xadd/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param id
	 * 		Id
	 * @param hash
	 * 		Hash
	 * @param xAddArgument
	 *        {@link XAddArgument}
	 *
	 * @return {@link StreamEntryId}
	 */
	default StreamEntryId xAdd(final byte[] key, final byte[] id, final Map<byte[], byte[]> hash,
							   final XAddArgument xAddArgument) {
		return xAdd(key, new StreamEntryId(id), hash, xAddArgument);
	}

	@Override
	default Map<StreamEntryId, List<StreamEntry>> xAutoClaim(final String key, final String groupName,
															 final String consumerName, final int minIdleTime,
															 final StreamEntryId start) {
		return execute((client)->client.streamCommands()
				.xAutoClaim(key, groupName, consumerName, minIdleTime, start));
	}

	@Override
	default Map<StreamEntryId, List<StreamEntry>> xAutoClaim(final byte[] key, final byte[] groupName,
															 final byte[] consumerName, final int minIdleTime,
															 final StreamEntryId start) {
		return execute((client)->client.streamCommands()
				.xAutoClaim(key, groupName, consumerName, minIdleTime, start));
	}

	@Override
	default Map<StreamEntryId, List<StreamEntry>> xAutoClaim(final String key, final String groupName,
															 final String consumerName, final int minIdleTime,
															 final StreamEntryId start, final int count) {
		return execute((client)->client.streamCommands()
				.xAutoClaim(key, groupName, consumerName, minIdleTime, start, count));
	}

	@Override
	default Map<StreamEntryId, List<StreamEntry>> xAutoClaim(final byte[] key, final byte[] groupName,
															 final byte[] consumerName, final int minIdleTime,
															 final StreamEntryId start, final int count) {
		return execute((client)->client.streamCommands()
				.xAutoClaim(key, groupName, consumerName, minIdleTime, start, count));
	}

	@Override
	default Map<StreamEntryId, List<StreamEntryId>> xAutoClaimJustId(final String key, final String groupName,
																	 final String consumerName, final int minIdleTime,
																	 final StreamEntryId start) {
		return execute((client)->client.streamCommands()
				.xAutoClaimJustId(key, groupName, consumerName, minIdleTime, start));
	}

	@Override
	default Map<StreamEntryId, List<StreamEntryId>> xAutoClaimJustId(final byte[] key, final byte[] groupName,
																	 final byte[] consumerName, final int minIdleTime,
																	 final StreamEntryId start) {
		return execute((client)->client.streamCommands()
				.xAutoClaimJustId(key, groupName, consumerName, minIdleTime, start));
	}

	@Override
	default Map<StreamEntryId, List<StreamEntryId>> xAutoClaimJustId(final String key, final String groupName,
																	 final String consumerName, final int minIdleTime,
																	 final StreamEntryId start, final int count) {
		return execute((client)->client.streamCommands()
				.xAutoClaimJustId(key, groupName, consumerName, minIdleTime, start, count));
	}

	@Override
	default Map<StreamEntryId, List<StreamEntryId>> xAutoClaimJustId(final byte[] key, final byte[] groupName,
																	 final byte[] consumerName, final int minIdleTime,
																	 final StreamEntryId start, final int count) {
		return execute((client)->client.streamCommands()
				.xAutoClaimJustId(key, groupName, consumerName, minIdleTime, start, count));
	}

	/**
	 * This command transfers ownership of pending stream entries that match the specified criteria
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/xautoclaim/" target="_blank">https://redis.io/commands/xautoclaim/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param groupName
	 * 		Group Name
	 * @param consumerName
	 * 		Consumer Name
	 * @param minIdleTime
	 * 		Min Idle Time
	 * @param start
	 * 		greater ID than
	 *
	 * @return {@link StreamEntryId} 和对应的 {@link StreamEntry}
	 */
	default Map<StreamEntryId, List<StreamEntry>> xAutoClaim(final String key, final String groupName,
															 final String consumerName,
															 final int minIdleTime, final String start) {
		return xAutoClaim(key, groupName, consumerName, minIdleTime, new StreamEntryId(start));
	}

	/**
	 * This command transfers ownership of pending stream entries that match the specified criteria
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/xautoclaim/" target="_blank">https://redis.io/commands/xautoclaim/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param groupName
	 * 		Group Name
	 * @param consumerName
	 * 		Consumer Name
	 * @param minIdleTime
	 * 		Min Idle Time
	 * @param start
	 * 		greater ID than
	 *
	 * @return {@link StreamEntryId} 和对应的 {@link StreamEntry}
	 */
	default Map<StreamEntryId, List<StreamEntry>> xAutoClaim(final byte[] key, final byte[] groupName,
															 final byte[] consumerName,
															 final int minIdleTime, final byte[] start) {
		return xAutoClaim(key, groupName, consumerName, minIdleTime, new StreamEntryId(start));
	}

	/**
	 * This command transfers ownership of pending stream entries that match the specified criteria
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/xautoclaim/" target="_blank">https://redis.io/commands/xautoclaim/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param groupName
	 * 		Group Name
	 * @param consumerName
	 * 		Consumer Name
	 * @param minIdleTime
	 * 		Min Idle Time
	 * @param start
	 * 		greater ID than
	 * @param count
	 * 		数量
	 *
	 * @return {@link StreamEntryId} 和对应的 {@link StreamEntry}
	 */
	default Map<StreamEntryId, List<StreamEntry>> xAutoClaim(final String key, final String groupName,
															 final String consumerName, final int minIdleTime,
															 final String start, final int count) {
		return xAutoClaim(key, groupName, consumerName, minIdleTime, new StreamEntryId(start), count);
	}

	/**
	 * This command transfers ownership of pending stream entries that match the specified criteria
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/xautoclaim/" target="_blank">https://redis.io/commands/xautoclaim/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param groupName
	 * 		Group Name
	 * @param consumerName
	 * 		Consumer Name
	 * @param minIdleTime
	 * 		Min Idle Time
	 * @param start
	 * 		greater ID than
	 * @param count
	 * 		数量
	 *
	 * @return {@link StreamEntryId} 和对应的 {@link StreamEntry}
	 */
	default Map<StreamEntryId, List<StreamEntry>> xAutoClaim(final byte[] key, final byte[] groupName,
															 final byte[] consumerName, final int minIdleTime,
															 final byte[] start, final int count) {
		return xAutoClaim(key, groupName, consumerName, minIdleTime, new StreamEntryId(start), count);
	}

	/**
	 * This command transfers ownership of pending stream entries that match the specified criteria
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/xautoclaim/" target="_blank">https://redis.io/commands/xautoclaim/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param groupName
	 * 		Group Name
	 * @param consumerName
	 * 		Consumer Name
	 * @param minIdleTime
	 * 		Min Idle Time
	 * @param start
	 * 		greater ID than
	 *
	 * @return {@link StreamEntryId} 和对应的 {@link StreamEntryId}
	 */
	default Map<StreamEntryId, List<StreamEntryId>> xAutoClaimJustId(final String key, final String groupName,
																	 final String consumerName, final int minIdleTime,
																	 final String start) {
		return xAutoClaimJustId(key, groupName, consumerName, minIdleTime, new StreamEntryId(start));
	}

	/**
	 * This command transfers ownership of pending stream entries that match the specified criteria
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/xautoclaim/" target="_blank">https://redis.io/commands/xautoclaim/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param groupName
	 * 		Group Name
	 * @param consumerName
	 * 		Consumer Name
	 * @param minIdleTime
	 * 		Min Idle Time
	 * @param start
	 * 		greater ID than
	 *
	 * @return {@link StreamEntryId} 和对应的 {@link StreamEntryId}
	 */
	default Map<StreamEntryId, List<StreamEntryId>> xAutoClaimJustId(final byte[] key, final byte[] groupName,
																	 final byte[] consumerName, final int minIdleTime,
																	 final byte[] start) {
		return xAutoClaimJustId(key, groupName, consumerName, minIdleTime, new StreamEntryId(start));
	}

	/**
	 * This command transfers ownership of pending stream entries that match the specified criteria
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/xautoclaim/" target="_blank">https://redis.io/commands/xautoclaim/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param groupName
	 * 		Group Name
	 * @param consumerName
	 * 		Consumer Name
	 * @param minIdleTime
	 * 		Min Idle Time
	 * @param start
	 * 		greater ID than
	 * @param count
	 * 		数量
	 *
	 * @return {@link StreamEntryId} 和对应的 {@link StreamEntryId}
	 */
	default Map<StreamEntryId, List<StreamEntryId>> xAutoClaimJustId(final String key, final String groupName,
																	 final String consumerName, final int minIdleTime,
																	 final String start, final int count) {
		return xAutoClaimJustId(key, groupName, consumerName, minIdleTime, new StreamEntryId(start), count);
	}

	/**
	 * This command transfers ownership of pending stream entries that match the specified criteria
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/xautoclaim/" target="_blank">https://redis.io/commands/xautoclaim/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param groupName
	 * 		Group Name
	 * @param consumerName
	 * 		Consumer Name
	 * @param minIdleTime
	 * 		Min Idle Time
	 * @param start
	 * 		greater ID than
	 * @param count
	 * 		数量
	 *
	 * @return {@link StreamEntryId} 和对应的 {@link StreamEntryId}
	 */
	default Map<StreamEntryId, List<StreamEntryId>> xAutoClaimJustId(final byte[] key, final byte[] groupName,
																	 final byte[] consumerName, final int minIdleTime,
																	 final byte[] start, final int count) {
		return xAutoClaimJustId(key, groupName, consumerName, minIdleTime, new StreamEntryId(start), count);
	}

	@Override
	default Status xCfgSet(final String key) {
		return execute((client)->client.streamCommands().xCfgSet(key));
	}

	@Override
	default Status xCfgSet(final byte[] key) {
		return execute((client)->client.streamCommands().xCfgSet(key));
	}

	@Override
	default Status xCfgSet(final String key, final long duration) {
		return execute((client)->client.streamCommands().xCfgSet(key, duration));
	}

	@Override
	default Status xCfgSet(final byte[] key, final long duration) {
		return execute((client)->client.streamCommands().xCfgSet(key, duration));
	}

	@Override
	default Status xCfgSet(final String key, final int maxsize) {
		return execute((client)->client.streamCommands().xCfgSet(key, maxsize));
	}

	@Override
	default Status xCfgSet(final byte[] key, final int maxsize) {
		return execute((client)->client.streamCommands().xCfgSet(key, maxsize));
	}

	@Override
	default Status xCfgSet(final String key, final long duration, final int maxsize) {
		return execute((client)->client.streamCommands().xCfgSet(key, duration, maxsize));
	}

	@Override
	default Status xCfgSet(final byte[] key, final long duration, final int maxsize) {
		return execute((client)->client.streamCommands().xCfgSet(key, duration, maxsize));
	}

	@Override
	default List<StreamEntry> xClaim(final String key, final String groupName, final String consumerName,
									 final int minIdleTime, final StreamEntryId... ids) {
		return execute(
				(client)->client.streamCommands().xClaim(key, groupName, consumerName, minIdleTime, ids));
	}

	@Override
	default List<StreamEntry> xClaim(final byte[] key, final byte[] groupName, final byte[] consumerName,
									 final int minIdleTime, final StreamEntryId... ids) {
		return execute(
				(client)->client.streamCommands().xClaim(key, groupName, consumerName, minIdleTime, ids));
	}

	@Override
	default List<StreamEntry> xClaim(final String key, final String groupName, final String consumerName,
									 final int minIdleTime, final StreamEntryId[] ids,
									 final XClaimArgument xClaimArgument) {
		return execute((client)->client.streamCommands()
				.xClaim(key, groupName, consumerName, minIdleTime, ids, xClaimArgument));
	}

	@Override
	default List<StreamEntry> xClaim(final byte[] key, final byte[] groupName, final byte[] consumerName,
									 final int minIdleTime, final StreamEntryId[] ids,
									 final XClaimArgument xClaimArgument) {
		return execute((client)->client.streamCommands()
				.xClaim(key, groupName, consumerName, minIdleTime, ids, xClaimArgument));
	}

	/**
	 * In the context of a stream consumer group, this command changes the ownership of a pending message,
	 * so that the new owner is the consumer specified as the command argument
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/xclaim/" target="_blank">https://redis.io/commands/xclaim/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param groupName
	 * 		Group Name
	 * @param consumerName
	 * 		Consumer Name
	 * @param minIdleTime
	 * 		Min Idle Time
	 * @param ids
	 * 		一个或多个 ID
	 *
	 * @return {@link StreamEntry} 列表
	 */
	default List<StreamEntry> xClaim(final String key, final String groupName, final String consumerName,
									 final int minIdleTime, final String[] ids) {
		final StreamEntryId[] streamEntryIds = Arrays.map(ids, StreamEntryId.class, StreamEntryId::new);
		return xClaim(key, groupName, consumerName, minIdleTime, streamEntryIds);
	}

	/**
	 * In the context of a stream consumer group, this command changes the ownership of a pending message,
	 * so that the new owner is the consumer specified as the command argument
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/xautoclaim/" target="_blank">https://redis.io/commands/xautoclaim/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param groupName
	 * 		Group Name
	 * @param consumerName
	 * 		Consumer Name
	 * @param minIdleTime
	 * 		Min Idle Time
	 * @param ids
	 * 		一个或多个 ID
	 *
	 * @return {@link StreamEntry} 列表
	 */
	default List<StreamEntry> xClaim(final byte[] key, final byte[] groupName, final byte[] consumerName,
									 final int minIdleTime, final byte[][] ids) {
		final StreamEntryId[] streamEntryIds = Arrays.map(ids, StreamEntryId.class, StreamEntryId::new);
		return xClaim(key, groupName, consumerName, minIdleTime, streamEntryIds);
	}


	/**
	 * In the context of a stream consumer group, this command changes the ownership of a pending message,
	 * so that the new owner is the consumer specified as the command argument
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/xclaim/" target="_blank">https://redis.io/commands/xclaim/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param groupName
	 * 		Group Name
	 * @param consumerName
	 * 		Consumer Name
	 * @param minIdleTime
	 * 		Min Idle Time
	 * @param ids
	 * 		一个或多个 ID
	 * @param xClaimArgument
	 *        {@link XClaimArgument}
	 *
	 * @return {@link StreamEntry} 列表
	 */
	default List<StreamEntry> xClaim(final String key, final String groupName, final String consumerName,
									 final int minIdleTime, final String[] ids, final XClaimArgument xClaimArgument) {
		final StreamEntryId[] streamEntryIds = Arrays.map(ids, StreamEntryId.class, StreamEntryId::new);
		return xClaim(key, groupName, consumerName, minIdleTime, streamEntryIds, xClaimArgument);
	}

	/**
	 * In the context of a stream consumer group, this command changes the ownership of a pending message,
	 * so that the new owner is the consumer specified as the command argument
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/xautoclaim/" target="_blank">https://redis.io/commands/xautoclaim/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param groupName
	 * 		Group Name
	 * @param consumerName
	 * 		Consumer Name
	 * @param minIdleTime
	 * 		Min Idle Time
	 * @param ids
	 * 		一个或多个 ID
	 * @param xClaimArgument
	 *        {@link XClaimArgument}
	 *
	 * @return {@link StreamEntry} 列表
	 */
	default List<StreamEntry> xClaim(final byte[] key, final byte[] groupName, final byte[] consumerName,
									 final int minIdleTime, final byte[][] ids, final XClaimArgument xClaimArgument) {
		final StreamEntryId[] streamEntryIds = Arrays.map(ids, StreamEntryId.class, StreamEntryId::new);
		return xClaim(key, groupName, consumerName, minIdleTime, streamEntryIds, xClaimArgument);
	}

	@Override
	default List<StreamEntryId> xClaimJustId(final String key, final String groupName, final String consumerName,
											 final int minIdleTime, final StreamEntryId... ids) {
		return execute((client)->client.streamCommands()
				.xClaimJustId(key, groupName, consumerName, minIdleTime, ids));
	}

	@Override
	default List<StreamEntryId> xClaimJustId(final byte[] key, final byte[] groupName, final byte[] consumerName,
											 final int minIdleTime, final StreamEntryId... ids) {
		return execute((client)->client.streamCommands()
				.xClaimJustId(key, groupName, consumerName, minIdleTime, ids));
	}

	@Override
	default List<StreamEntryId> xClaimJustId(final String key, final String groupName, final String consumerName,
											 final int minIdleTime, final StreamEntryId[] ids,
											 final XClaimArgument xClaimArgument) {
		return execute((client)->client.streamCommands()
				.xClaimJustId(key, groupName, consumerName, minIdleTime, ids, xClaimArgument));
	}

	@Override
	default List<StreamEntryId> xClaimJustId(final byte[] key, final byte[] groupName, final byte[] consumerName,
											 final int minIdleTime, final StreamEntryId[] ids,
											 final XClaimArgument xClaimArgument) {
		return execute((client)->client.streamCommands()
				.xClaimJustId(key, groupName, consumerName, minIdleTime, ids, xClaimArgument));
	}

	/**
	 * In the context of a stream consumer group, this command changes the ownership of a pending message,
	 * so that the new owner is the consumer specified as the command argument
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/xclaim/" target="_blank">https://redis.io/commands/xclaim/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param groupName
	 * 		Group Name
	 * @param consumerName
	 * 		Consumer Name
	 * @param minIdleTime
	 * 		Min Idle Time
	 * @param ids
	 * 		一个或多个 ID
	 *
	 * @return {@link StreamEntryId} 列表
	 */
	default List<StreamEntryId> xClaimJustId(final String key, final String groupName, final String consumerName,
											 final int minIdleTime, final String[] ids) {
		final StreamEntryId[] streamEntryIds = Arrays.map(ids, StreamEntryId.class, StreamEntryId::new);
		return xClaimJustId(key, groupName, consumerName, minIdleTime, streamEntryIds);
	}

	/**
	 * In the context of a stream consumer group, this command changes the ownership of a pending message,
	 * so that the new owner is the consumer specified as the command argument
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/xautoclaim/" target="_blank">https://redis.io/commands/xautoclaim/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param groupName
	 * 		Group Name
	 * @param consumerName
	 * 		Consumer Name
	 * @param minIdleTime
	 * 		Min Idle Time
	 * @param ids
	 * 		一个或多个 ID
	 *
	 * @return {@link StreamEntryId} 列表
	 */
	default List<StreamEntryId> xClaimJustId(final byte[] key, final byte[] groupName, final byte[] consumerName,
											 final int minIdleTime, final byte[][] ids) {
		final StreamEntryId[] streamEntryIds = Arrays.map(ids, StreamEntryId.class, StreamEntryId::new);
		return xClaimJustId(key, groupName, consumerName, minIdleTime, streamEntryIds);
	}

	@Override
	default Long xDel(final String key, final StreamEntryId... ids) {
		return execute((client)->client.streamCommands().xDel(key, ids));
	}

	@Override
	default Long xDel(final byte[] key, final StreamEntryId... ids) {
		return execute((client)->client.streamCommands().xDel(key, ids));
	}

	/**
	 * Removes the specified entries from a stream, and returns the number of entries deleted
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/xdel/" target="_blank">https://redis.io/commands/xdel/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param ids
	 * 		一个或多个 ID
	 *
	 * @return The number of entries actually deleted
	 */
	default Long xDel(final String key, final String... ids) {
		final StreamEntryId[] streamEntryIds = Arrays.map(ids, StreamEntryId.class, StreamEntryId::new);
		return xDel(key, streamEntryIds);
	}

	/**
	 * Removes the specified entries from a stream, and returns the number of entries deleted
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/xdel/" target="_blank">https://redis.io/commands/xdel/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param ids
	 * 		一个或多个 ID
	 *
	 * @return The number of entries actually deleted
	 */
	default Long xDel(final byte[] key, final byte[]... ids) {
		final StreamEntryId[] streamEntryIds = Arrays.map(ids, StreamEntryId.class, StreamEntryId::new);
		return xDel(key, streamEntryIds);
	}

	@Override
	default List<StreamEntryDeletionResult> xDelEx(final String key, final StreamDeletionPolicy deletionPolicy,
												   final StreamEntryId... ids) {
		return execute((client)->client.streamCommands().xDelEx(key, deletionPolicy, ids));
	}

	@Override
	default List<StreamEntryDeletionResult> xDelEx(final byte[] key, final StreamDeletionPolicy deletionPolicy,
												   final StreamEntryId... ids) {
		return execute((client)->client.streamCommands().xDelEx(key, deletionPolicy, ids));
	}

	@Override
	default Status xGroupCreate(final String key, final String groupName, final StreamEntryId id) {
		return execute((client)->client.streamCommands().xGroupCreate(key, groupName, id));
	}

	@Override
	default Status xGroupCreate(final byte[] key, final byte[] groupName, final StreamEntryId id) {
		return execute((client)->client.streamCommands().xGroupCreate(key, groupName, id));
	}

	@Override
	default Status xGroupCreate(final String key, final String groupName, final StreamEntryId id,
								final boolean makeStream) {
		return execute((client)->client.streamCommands().xGroupCreate(key, groupName, id, makeStream));
	}

	@Override
	default Status xGroupCreate(final byte[] key, final byte[] groupName, final StreamEntryId id,
								final boolean makeStream) {
		return execute((client)->client.streamCommands().xGroupCreate(key, groupName, id, makeStream));
	}

	@Override
	default Status xGroupCreate(final String key, final String groupName, final StreamEntryId id,
								final long entriesRead) {
		return execute((client)->client.streamCommands().xGroupCreate(key, groupName, id, entriesRead));
	}

	@Override
	default Status xGroupCreate(final byte[] key, final byte[] groupName, final StreamEntryId id,
								final long entriesRead) {
		return execute((client)->client.streamCommands().xGroupCreate(key, groupName, id, entriesRead));
	}

	@Override
	default Status xGroupCreate(final String key, final String groupName, final StreamEntryId id,
								final boolean makeStream, final long entriesRead) {
		return execute((client)->client.streamCommands().xGroupCreate(key, groupName, id, makeStream, entriesRead));
	}

	@Override
	default Status xGroupCreate(final byte[] key, final byte[] groupName, final StreamEntryId id,
								final boolean makeStream, final long entriesRead) {
		return execute((client)->client.streamCommands().xGroupCreate(key, groupName, id, makeStream, entriesRead));
	}

	/**
	 * This command creates a new consumer group uniquely identified by groupname for the stream stored at key
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/xgroup-create/" target="_blank">https://redis.io/commands/xgroup-create/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param groupName
	 * 		Group Name
	 * @param id
	 * 		ID
	 *
	 * @return 创建成功，返回 Status.SUCCESS；否则，返回 Status.FAILURE
	 */
	default Status xGroupCreate(final String key, final String groupName, final String id) {
		return xGroupCreate(key, groupName, new StreamEntryId(id));
	}

	/**
	 * This command creates a new consumer group uniquely identified by groupname for the stream stored at key
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/xgroup-create/" target="_blank">https://redis.io/commands/xgroup-create/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param groupName
	 * 		Group Name
	 * @param id
	 * 		ID
	 *
	 * @return 创建成功，返回 Status.SUCCESS；否则，返回 Status.FAILURE
	 */
	default Status xGroupCreate(final byte[] key, final byte[] groupName, final byte[] id) {
		return xGroupCreate(key, groupName, new StreamEntryId(id));
	}

	/**
	 * This command creates a new consumer group uniquely identified by groupname for the stream stored at key
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/xgroup-create/" target="_blank">https://redis.io/commands/xgroup-create/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param groupName
	 * 		Group Name
	 * @param id
	 * 		ID
	 * @param makeStream
	 * 		Can use the optional MKSTREAM subcommand as the last argument after the id to automatically create the stream (with length of 0)
	 *
	 * @return 创建成功，返回 Status.SUCCESS；否则，返回 Status.FAILURE
	 */
	default Status xGroupCreate(final String key, final String groupName, final String id, final boolean makeStream) {
		return xGroupCreate(key, groupName, new StreamEntryId(id), makeStream);
	}

	/**
	 * This command creates a new consumer group uniquely identified by groupname for the stream stored at key
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/xgroup-create/" target="_blank">https://redis.io/commands/xgroup-create/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param groupName
	 * 		Group Name
	 * @param id
	 * 		ID
	 * @param makeStream
	 * 		Can use the optional MKSTREAM subcommand as the last argument after the id to automatically create the stream (with length of 0)
	 *
	 * @return 创建成功，返回 Status.SUCCESS；否则，返回 Status.FAILURE
	 */
	default Status xGroupCreate(final byte[] key, final byte[] groupName, final byte[] id, final boolean makeStream) {
		return xGroupCreate(key, groupName, new StreamEntryId(id), makeStream);
	}

	/**
	 * This command creates a new consumer group uniquely identified by groupname for the stream stored at key
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/xgroup-create/" target="_blank">https://redis.io/commands/xgroup-create/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param groupName
	 * 		Group Name
	 * @param id
	 * 		ID
	 * @param entriesRead
	 * 		To enable consumer group lag tracking, specify the optional entries_read named argument with an arbitrary ID
	 *
	 * @return 创建成功，返回 Status.SUCCESS；否则，返回 Status.FAILURE
	 */
	default Status xGroupCreate(final String key, final String groupName, final String id, final long entriesRead) {
		return xGroupCreate(key, groupName, new StreamEntryId(id), entriesRead);
	}

	/**
	 * This command creates a new consumer group uniquely identified by groupname for the stream stored at key
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/xgroup-create/" target="_blank">https://redis.io/commands/xgroup-create/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param groupName
	 * 		Group Name
	 * @param id
	 * 		ID
	 * @param entriesRead
	 * 		To enable consumer group lag tracking, specify the optional entries_read named argument with an arbitrary ID
	 *
	 * @return 创建成功，返回 Status.SUCCESS；否则，返回 Status.FAILURE
	 */
	default Status xGroupCreate(final byte[] key, final byte[] groupName, final byte[] id, final long entriesRead) {
		return xGroupCreate(key, groupName, new StreamEntryId(id), entriesRead);
	}

	/**
	 * This command creates a new consumer group uniquely identified by groupname for the stream stored at key
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/xgroup-create/" target="_blank">https://redis.io/commands/xgroup-create/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param groupName
	 * 		Group Name
	 * @param id
	 * 		ID
	 * @param makeStream
	 * 		Can use the optional MKSTREAM subcommand as the last argument after the id to automatically create the stream (with length of 0)
	 * @param entriesRead
	 * 		To enable consumer group lag tracking, specify the optional entries_read named argument with an arbitrary ID
	 *
	 * @return 创建成功，返回 Status.SUCCESS；否则，返回 Status.FAILURE
	 */
	default Status xGroupCreate(final String key, final String groupName, final String id, final boolean makeStream,
								final long entriesRead) {
		return xGroupCreate(key, groupName, new StreamEntryId(id), makeStream, entriesRead);
	}

	/**
	 * This command creates a new consumer group uniquely identified by groupname for the stream stored at key
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/xgroup-create/" target="_blank">https://redis.io/commands/xgroup-create/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param groupName
	 * 		Group Name
	 * @param id
	 * 		ID
	 * @param makeStream
	 * 		Can use the optional MKSTREAM subcommand as the last argument after the id to automatically create the stream (with length of 0)
	 * @param entriesRead
	 * 		To enable consumer group lag tracking, specify the optional entries_read named argument with an arbitrary ID
	 *
	 * @return 创建成功，返回 Status.SUCCESS；否则，返回 Status.FAILURE
	 */
	default Status xGroupCreate(final byte[] key, final byte[] groupName, final byte[] id, final boolean makeStream,
								final long entriesRead) {
		return xGroupCreate(key, groupName, new StreamEntryId(id), makeStream, entriesRead);
	}

	@Override
	default Status xGroupCreateConsumer(final String key, final String groupName, final String consumerName) {
		return execute((client)->client.streamCommands().xGroupCreateConsumer(key, groupName, consumerName));
	}

	@Override
	default Status xGroupCreateConsumer(final byte[] key, final byte[] groupName, final byte[] consumerName) {
		return execute((client)->client.streamCommands().xGroupCreateConsumer(key, groupName, consumerName));
	}

	@Override
	default Long xGroupDelConsumer(final String key, final String groupName, final String consumerName) {
		return execute((client)->client.streamCommands().xGroupDelConsumer(key, groupName, consumerName));
	}

	@Override
	default Long xGroupDelConsumer(final byte[] key, final byte[] groupName, final byte[] consumerName) {
		return execute((client)->client.streamCommands().xGroupDelConsumer(key, groupName, consumerName));
	}

	@Override
	default Status xGroupDestroy(final String key, final String groupName) {
		return execute((client)->client.streamCommands().xGroupDestroy(key, groupName));
	}

	@Override
	default Status xGroupDestroy(final byte[] key, final byte[] groupName) {
		return execute((client)->client.streamCommands().xGroupDestroy(key, groupName));
	}

	@Override
	default Status xGroupSetId(final String key, final String groupName, final StreamEntryId id) {
		return execute((client)->client.streamCommands().xGroupSetId(key, groupName, id));
	}

	@Override
	default Status xGroupSetId(final byte[] key, final byte[] groupName, final StreamEntryId id) {
		return execute((client)->client.streamCommands().xGroupSetId(key, groupName, id));
	}

	@Override
	default Status xGroupSetId(final String key, final String groupName, final StreamEntryId id,
							   final long entriesRead) {
		return execute((client)->client.streamCommands().xGroupSetId(key, groupName, id, entriesRead));
	}

	@Override
	default Status xGroupSetId(final byte[] key, final byte[] groupName, final StreamEntryId id,
							   final long entriesRead) {
		return execute((client)->client.streamCommands().xGroupSetId(key, groupName, id, entriesRead));
	}

	/**
	 * Normally, a consumer group's last delivered ID is set when the group is created with XGROUP CREATE
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/xgroup-setid/" target="_blank">https://redis.io/commands/xgroup-setid/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param groupName
	 * 		Group Name
	 * @param id
	 * 		ID
	 *
	 * @return 销毁成功，返回 Status.SUCCESS；否则，返回 Status.FAILURE
	 */
	default Status xGroupSetId(final String key, final String groupName, final String id) {
		return xGroupSetId(key, groupName, new StreamEntryId(id));
	}

	/**
	 * Normally, a consumer group's last delivered ID is set when the group is created with XGROUP CREATE
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/xgroup-setid/" target="_blank">https://redis.io/commands/xgroup-setid/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param groupName
	 * 		Group Name
	 * @param id
	 * 		ID
	 *
	 * @return 销毁成功，返回 Status.SUCCESS；否则，返回 Status.FAILURE
	 */
	default Status xGroupSetId(final byte[] key, final byte[] groupName, final byte[] id) {
		return xGroupSetId(key, groupName, new StreamEntryId(id));
	}

	/**
	 * Normally, a consumer group's last delivered ID is set when the group is created with XGROUP CREATE
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/xgroup-setid/" target="_blank">https://redis.io/commands/xgroup-setid/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param groupName
	 * 		Group Name
	 * @param id
	 * 		ID
	 * @param entriesRead
	 * 		The optional entries_read argument can be specified to enable consumer group lag tracking for an arbitrary ID.
	 *
	 * @return 销毁成功，返回 Status.SUCCESS；否则，返回 Status.FAILURE
	 */
	default Status xGroupSetId(final String key, final String groupName, final String id, final long entriesRead) {
		return xGroupSetId(key, groupName, new StreamEntryId(id), entriesRead);
	}

	/**
	 * Normally, a consumer group's last delivered ID is set when the group is created with XGROUP CREATE
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/xgroup-setid/" target="_blank">https://redis.io/commands/xgroup-setid/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param groupName
	 * 		Group Name
	 * @param id
	 * 		ID
	 * @param entriesRead
	 * 		The optional entries_read argument can be specified to enable consumer group lag tracking for an arbitrary ID.
	 *
	 * @return 销毁成功，返回 Status.SUCCESS；否则，返回 Status.FAILURE
	 */
	default Status xGroupSetId(final byte[] key, final byte[] groupName, final byte[] id, final long entriesRead) {
		return xGroupSetId(key, groupName, new StreamEntryId(id), entriesRead);
	}

	@Override
	default List<StreamConsumer> xInfoConsumers(final String key, final String groupName) {
		return execute((client)->client.streamCommands().xInfoConsumers(key, groupName));
	}

	@Override
	default List<StreamConsumer> xInfoConsumers(final byte[] key, final byte[] groupName) {
		return execute((client)->client.streamCommands().xInfoConsumers(key, groupName));
	}

	@Override
	default List<StreamGroup> xInfoGroups(final String key) {
		return execute((client)->client.streamCommands().xInfoGroups(key));
	}

	@Override
	default List<StreamGroup> xInfoGroups(final byte[] key) {
		return execute((client)->client.streamCommands().xInfoGroups(key));
	}

	@Override
	default Stream<String, String> xInfoStream(final String key) {
		return execute((client)->client.streamCommands().xInfoStream(key));
	}

	@Override
	default Stream<byte[], byte[]> xInfoStream(final byte[] key) {
		return execute((client)->client.streamCommands().xInfoStream(key));
	}

	@Override
	default StreamFull xInfoStream(final String key, final boolean full) {
		return execute((client)->client.streamCommands().xInfoStream(key, full));
	}

	@Override
	default StreamFull xInfoStream(final byte[] key, final boolean full) {
		return execute((client)->client.streamCommands().xInfoStream(key, full));
	}

	@Override
	default StreamFull xInfoStream(final String key, final boolean full, final int count) {
		return execute((client)->client.streamCommands().xInfoStream(key, full, count));
	}

	@Override
	default StreamFull xInfoStream(final byte[] key, final boolean full, final int count) {
		return execute((client)->client.streamCommands().xInfoStream(key, full, count));
	}

	@Override
	default Long xLen(final String key) {
		return execute((client)->client.streamCommands().xLen(key));
	}

	@Override
	default Long xLen(final byte[] key) {
		return execute((client)->client.streamCommands().xLen(key));
	}

	@Override
	default StreamPendingSummary xPending(final String key, final String groupName) {
		return execute((client)->client.streamCommands().xPending(key, groupName));
	}

	@Override
	default StreamPendingSummary xPending(final byte[] key, final byte[] groupName) {
		return execute((client)->client.streamCommands().xPending(key, groupName));
	}

	@Override
	default List<StreamPending> xPending(final String key, final String groupName, final StreamEntryId start,
										 final StreamEntryId end, final int count) {
		return execute((client)->client.streamCommands().xPending(key, groupName, start, end, count));
	}

	@Override
	default List<StreamPending> xPending(final byte[] key, final byte[] groupName, final StreamEntryId start,
										 final StreamEntryId end, final int count) {
		return execute((client)->client.streamCommands().xPending(key, groupName, start, end, count));
	}

	@Override
	default List<StreamPending> xPending(final String key, final String groupName, final long minIdleTime,
										 final StreamEntryId start, final StreamEntryId end, final int count) {
		return execute(
				(client)->client.streamCommands().xPending(key, groupName, minIdleTime, start, end, count));
	}

	@Override
	default List<StreamPending> xPending(final byte[] key, final byte[] groupName, final long minIdleTime,
										 final StreamEntryId start, final StreamEntryId end, final int count) {
		return execute(
				(client)->client.streamCommands().xPending(key, groupName, minIdleTime, start, end, count));
	}

	@Override
	default List<StreamPending> xPending(final String key, final String groupName, final StreamEntryId start,
										 final StreamEntryId end, final int count, final String consumerName) {
		return execute(
				(client)->client.streamCommands().xPending(key, groupName, start, end, count, consumerName));
	}

	@Override
	default List<StreamPending> xPending(final byte[] key, final byte[] groupName, final StreamEntryId start,
										 final StreamEntryId end, final int count, final byte[] consumerName) {
		return execute(
				(client)->client.streamCommands().xPending(key, groupName, start, end, count, consumerName));
	}

	@Override
	default List<StreamPending> xPending(final String key, final String groupName, final long minIdleTime,
										 final StreamEntryId start, final StreamEntryId end, final int count,
										 final String consumerName) {
		return execute((client)->client.streamCommands()
				.xPending(key, groupName, minIdleTime, start, end, count, consumerName));
	}

	@Override
	default List<StreamPending> xPending(final byte[] key, final byte[] groupName, final long minIdleTime,
										 final StreamEntryId start, final StreamEntryId end, final int count,
										 final byte[] consumerName) {
		return execute((client)->client.streamCommands()
				.xPending(key, groupName, minIdleTime, start, end, count, consumerName));
	}

	/**
	 * Fetching data from a stream via a consumer group, and not acknowledging such data, has the effect of creating pending entries
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/xpending/" target="_blank">https://redis.io/commands/xpending/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param groupName
	 * 		Group Name
	 * @param start
	 * 		Start Id
	 * @param end
	 * 		End Id
	 * @param count
	 * 		返回数量
	 *
	 * @return {@link StreamPending} 列表
	 */
	default List<StreamPending> xPending(final String key, final String groupName, final String start, final String end,
										 final int count) {
		return xPending(key, groupName, new StreamEntryId(start), new StreamEntryId(end), count);
	}

	/**
	 * Fetching data from a stream via a consumer group, and not acknowledging such data, has the effect of creating pending entries
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/xpending/" target="_blank">https://redis.io/commands/xpending/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param groupName
	 * 		Group Name
	 * @param start
	 * 		Start Id
	 * @param end
	 * 		End Id
	 * @param count
	 * 		返回数量
	 *
	 * @return {@link StreamPending} 列表
	 */
	default List<StreamPending> xPending(final byte[] key, final byte[] groupName, final byte[] start, final byte[] end,
										 final int count) {
		return xPending(key, groupName, new StreamEntryId(start), new StreamEntryId(end), count);
	}

	/**
	 * Fetching data from a stream via a consumer group, and not acknowledging such data, has the effect of creating pending entries
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/xpending/" target="_blank">https://redis.io/commands/xpending/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param groupName
	 * 		Group Name
	 * @param minIdleTime
	 * 		Min Idle Time
	 * @param start
	 * 		Start Id
	 * @param end
	 * 		End Id
	 * @param count
	 * 		返回数量
	 *
	 * @return {@link StreamPending} 列表
	 */
	default List<StreamPending> xPending(final String key, final String groupName, final long minIdleTime,
										 final String start, final String end, final int count) {
		return xPending(key, groupName, minIdleTime, new StreamEntryId(start), new StreamEntryId(end), count);
	}

	/**
	 * Fetching data from a stream via a consumer group, and not acknowledging such data, has the effect of creating pending entries
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/xpending/" target="_blank">https://redis.io/commands/xpending/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param groupName
	 * 		Group Name
	 * @param minIdleTime
	 * 		Min Idle Time
	 * @param start
	 * 		Start Id
	 * @param end
	 * 		End Id
	 * @param count
	 * 		返回数量
	 *
	 * @return {@link StreamPending} 列表
	 */
	default List<StreamPending> xPending(final byte[] key, final byte[] groupName, final long minIdleTime,
										 final byte[] start, final byte[] end, final int count) {
		return xPending(key, groupName, minIdleTime, new StreamEntryId(start), new StreamEntryId(end), count);
	}

	/**
	 * Fetching data from a stream via a consumer group, and not acknowledging such data, has the effect of creating pending entries
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/xpending/" target="_blank">https://redis.io/commands/xpending/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param groupName
	 * 		Group Name
	 * @param start
	 * 		Start Id
	 * @param end
	 * 		End Id
	 * @param count
	 * 		返回数量
	 * @param consumerName
	 * 		Consumer Name
	 *
	 * @return {@link StreamPending} 列表
	 */
	default List<StreamPending> xPending(final String key, final String groupName, final String start,
										 final String end, final int count, final String consumerName) {
		return xPending(key, groupName, new StreamEntryId(start), new StreamEntryId(end), count, consumerName);
	}

	/**
	 * Fetching data from a stream via a consumer group, and not acknowledging such data, has the effect of creating pending entries
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/xpending/" target="_blank">https://redis.io/commands/xpending/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param groupName
	 * 		Group Name
	 * @param start
	 * 		Start Id
	 * @param end
	 * 		End Id
	 * @param count
	 * 		返回数量
	 * @param consumerName
	 * 		Consumer Name
	 *
	 * @return {@link StreamPending} 列表
	 */
	default List<StreamPending> xPending(final byte[] key, final byte[] groupName, final byte[] start,
										 final byte[] end, final int count, final byte[] consumerName) {
		return xPending(key, groupName, new StreamEntryId(start), new StreamEntryId(end), count, consumerName);
	}

	/**
	 * Fetching data from a stream via a consumer group, and not acknowledging such data, has the effect of creating pending entries
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/xpending/" target="_blank">https://redis.io/commands/xpending/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param groupName
	 * 		Group Name
	 * @param minIdleTime
	 * 		Min Idle Time
	 * @param start
	 * 		Start Id
	 * @param end
	 * 		End Id
	 * @param count
	 * 		返回数量
	 * @param consumerName
	 * 		Consumer Name
	 *
	 * @return {@link StreamPending} 列表
	 */
	default List<StreamPending> xPending(final String key, final String groupName, final long minIdleTime,
										 final String start, final String end, final int count,
										 final String consumerName) {
		return xPending(key, groupName, minIdleTime, new StreamEntryId(start), new StreamEntryId(end), count,
				consumerName);
	}

	/**
	 * Fetching data from a stream via a consumer group, and not acknowledging such data, has the effect of creating pending entries
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/xpending/" target="_blank">https://redis.io/commands/xpending/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param groupName
	 * 		Group Name
	 * @param minIdleTime
	 * 		Min Idle Time
	 * @param start
	 * 		Start Id
	 * @param end
	 * 		End Id
	 * @param count
	 * 		返回数量
	 * @param consumerName
	 * 		Consumer Name
	 *
	 * @return {@link StreamPending} 列表
	 */
	default List<StreamPending> xPending(final byte[] key, final byte[] groupName, final long minIdleTime,
										 final byte[] start, final byte[] end, final int count,
										 final byte[] consumerName) {
		return xPending(key, groupName, minIdleTime, new StreamEntryId(start), new StreamEntryId(end), count,
				consumerName);
	}

	@Override
	default List<StreamEntry> xRange(final String key, final StreamEntryId start, final StreamEntryId end) {
		return execute((client)->client.streamCommands().xRange(key, start, end));
	}

	@Override
	default List<StreamEntry> xRange(final byte[] key, final StreamEntryId start, final StreamEntryId end) {
		return execute((client)->client.streamCommands().xRange(key, start, end));
	}

	@Override
	default List<StreamEntry> xRange(final String key, final StreamEntryId start, final StreamEntryId end,
									 final int count) {
		return execute((client)->client.streamCommands().xRange(key, start, end));
	}

	@Override
	default List<StreamEntry> xRange(final byte[] key, final StreamEntryId start, final StreamEntryId end,
									 final int count) {
		return execute((client)->client.streamCommands().xRange(key, start, end));
	}

	/**
	 * The command returns the stream entries matching a given range of IDs
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/xrange/" target="_blank">https://redis.io/commands/xrange/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param start
	 * 		Start Id
	 * @param end
	 * 		End Id
	 *
	 * @return {@link StreamEntry} 列表
	 */
	default List<StreamEntry> xRange(final String key, final String start, final String end) {
		return xRange(key, new StreamEntryId(start), new StreamEntryId(end));
	}

	/**
	 * The command returns the stream entries matching a given range of IDs
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/xrange/" target="_blank">https://redis.io/commands/xrange/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param start
	 * 		Start Id
	 * @param end
	 * 		End Id
	 *
	 * @return {@link StreamEntry} 列表
	 */
	default List<StreamEntry> xRange(final byte[] key, final byte[] start, final byte[] end) {
		return xRange(key, new StreamEntryId(start), new StreamEntryId(end));
	}

	/**
	 * The command returns the stream entries matching a given range of IDs
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/xrange/" target="_blank">https://redis.io/commands/xrange/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param start
	 * 		Start Id
	 * @param end
	 * 		End Id
	 * @param count
	 * 		返回数量
	 *
	 * @return {@link StreamEntry} 列表
	 */
	default List<StreamEntry> xRange(final String key, final String start, final String end, final int count) {
		return xRange(key, new StreamEntryId(start), new StreamEntryId(end), count);
	}

	/**
	 * The command returns the stream entries matching a given range of IDs
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/xrange/" target="_blank">https://redis.io/commands/xrange/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param start
	 * 		Start Id
	 * @param end
	 * 		End Id
	 * @param count
	 * 		返回数量
	 *
	 * @return {@link StreamEntry} 列表
	 */
	default List<StreamEntry> xRange(final byte[] key, final byte[] start, final byte[] end, final int count) {
		return xRange(key, new StreamEntryId(start), new StreamEntryId(end), count);
	}

	@Override
	default List<Map<String, List<StreamEntry>>> xRead(final Map<String, StreamEntryId> streams) {
		return execute((client)->client.streamCommands().xRead(streams));
	}

	@Override
	default List<Map<String, List<StreamEntry>>> xRead(final long count, final Map<String, StreamEntryId> streams) {
		return execute((client)->client.streamCommands().xRead(count, streams));
	}

	@Override
	default List<Map<String, List<StreamEntry>>> xRead(final int block, final Map<String, StreamEntryId> streams) {
		return execute((client)->client.streamCommands().xRead(block, streams));
	}

	@Override
	default List<Map<String, List<StreamEntry>>> xRead(final long count, final int block,
													   final Map<String, StreamEntryId> streams) {
		return execute((client)->client.streamCommands().xRead(count, block, streams));
	}

	@Override
	default List<Map<String, List<StreamEntry>>> xReadGroup(final String groupName, final String consumerName,
															final Map<String, StreamEntryId> streams) {
		return execute((client)->client.streamCommands().xReadGroup(groupName, consumerName, streams));
	}

	@Override
	default List<Map<byte[], List<StreamEntry>>> xReadGroup(final byte[] groupName, final byte[] consumerName,
															final Map<byte[], StreamEntryId> streams) {
		return execute((client)->client.streamCommands().xReadGroup(groupName, consumerName, streams));
	}

	@Override
	default List<Map<String, List<StreamEntry>>> xReadGroup(final String groupName, final String consumerName,
															final int block, final Map<String, StreamEntryId> streams) {
		return execute((client)->client.streamCommands().xReadGroup(groupName, consumerName, block, streams));
	}

	@Override
	default List<Map<byte[], List<StreamEntry>>> xReadGroup(final byte[] groupName, final byte[] consumerName,
															final int block, final Map<byte[], StreamEntryId> streams) {
		return execute((client)->client.streamCommands().xReadGroup(groupName, consumerName, block, streams));
	}

	@Override
	default List<Map<String, List<StreamEntry>>> xReadGroup(final String groupName, final String consumerName,
															final boolean isNoAck,
															final Map<String, StreamEntryId> streams) {
		return execute((client)->client.streamCommands().xReadGroup(groupName, consumerName, isNoAck, streams));
	}

	@Override
	default List<Map<byte[], List<StreamEntry>>> xReadGroup(final byte[] groupName, final byte[] consumerName,
															final boolean isNoAck,
															final Map<byte[], StreamEntryId> streams) {
		return execute((client)->client.streamCommands().xReadGroup(groupName, consumerName, isNoAck, streams));
	}

	@Override
	default List<Map<String, List<StreamEntry>>> xReadGroup(final String groupName, final String consumerName,
															final long count, final int block,
															final Map<String, StreamEntryId> streams) {
		return execute((client)->client.streamCommands().xReadGroup(groupName, consumerName, count, block, streams));
	}

	@Override
	default List<Map<byte[], List<StreamEntry>>> xReadGroup(final byte[] groupName, final byte[] consumerName,
															final long count, final int block,
															final Map<byte[], StreamEntryId> streams) {
		return execute((client)->client.streamCommands().xReadGroup(groupName, consumerName, count, block, streams));
	}

	@Override
	default List<Map<String, List<StreamEntry>>> xReadGroup(final String groupName, final String consumerName,
															final int block, final boolean isNoAck,
															final Map<String, StreamEntryId> streams) {
		return execute(
				(client)->client.streamCommands().xReadGroup(groupName, consumerName, block, isNoAck, streams));
	}

	@Override
	default List<Map<byte[], List<StreamEntry>>> xReadGroup(final byte[] groupName, final byte[] consumerName,
															final int block, final boolean isNoAck,
															final Map<byte[], StreamEntryId> streams) {
		return execute(
				(client)->client.streamCommands().xReadGroup(groupName, consumerName, block, isNoAck, streams));
	}

	@Override
	default List<Map<String, List<StreamEntry>>> xReadGroup(final String groupName, final String consumerName,
															final long count, final int block, final boolean isNoAck,
															final Map<String, StreamEntryId> streams) {
		return execute((client)->client.streamCommands()
				.xReadGroup(groupName, consumerName, count, block, isNoAck, streams));
	}

	@Override
	default List<Map<byte[], List<StreamEntry>>> xReadGroup(final byte[] groupName, final byte[] consumerName,
															final long count, final int block, final boolean isNoAck,
															final Map<byte[], StreamEntryId> streams) {
		return execute((client)->client.streamCommands()
				.xReadGroup(groupName, consumerName, count, block, isNoAck, streams));
	}

	@Override
	default List<StreamEntry> xRevRange(final String key, final StreamEntryId end, final StreamEntryId start) {
		return execute((client)->client.streamCommands().xRevRange(key, end, start));
	}

	@Override
	default List<StreamEntry> xRevRange(final byte[] key, final StreamEntryId end, final StreamEntryId start) {
		return execute((client)->client.streamCommands().xRevRange(key, end, start));
	}

	@Override
	default List<StreamEntry> xRevRange(final String key, final StreamEntryId end, final StreamEntryId start,
										final long count) {
		return execute((client)->client.streamCommands().xRevRange(key, end, start, count));
	}

	@Override
	default List<StreamEntry> xRevRange(final byte[] key, final StreamEntryId end, final StreamEntryId start,
										final long count) {
		return execute((client)->client.streamCommands().xRevRange(key, end, start, count));
	}

	/**
	 * This command is exactly like XRANGE, but with the notable difference of returning the entries in reverse order,
	 * and also taking the start-end range in reverse order: in XREVRANGE you need to state the end ID and later the start ID,
	 * and the command will produce all the element between (or exactly like) the two IDs, starting from the end side
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/xrevrange/" target="_blank">https://redis.io/commands/xrevrange/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param end
	 * 		End Id
	 * @param start
	 * 		Start Id
	 *
	 * @return {@link StreamEntry} 列表
	 */
	default List<StreamEntry> xRevRange(final String key, final String end, final String start) {
		return xRevRange(key, new StreamEntryId(end), new StreamEntryId(start));
	}

	/**
	 * This command is exactly like XRANGE, but with the notable difference of returning the entries in reverse order,
	 * and also taking the start-end range in reverse order: in XREVRANGE you need to state the end ID and later the start ID,
	 * and the command will produce all the element between (or exactly like) the two IDs, starting from the end side
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/xrevrange/" target="_blank">https://redis.io/commands/xrevrange/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param end
	 * 		End Id
	 * @param start
	 * 		Start Id
	 *
	 * @return {@link StreamEntry} 列表
	 */
	default List<StreamEntry> xRevRange(final byte[] key, final byte[] end, final byte[] start) {
		return xRevRange(key, new StreamEntryId(end), new StreamEntryId(start));
	}

	/**
	 * This command is exactly like XRANGE, but with the notable difference of returning the entries in reverse order,
	 * and also taking the start-end range in reverse order: in XREVRANGE you need to state the end ID and later the start ID,
	 * and the command will produce all the element between (or exactly like) the two IDs, starting from the end side
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/xrevrange/" target="_blank">https://redis.io/commands/xrevrange/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param end
	 * 		End Id
	 * @param start
	 * 		Start Id
	 * @param count
	 * 		返回数量
	 *
	 * @return {@link StreamEntry} 列表
	 */
	default List<StreamEntry> xRevRange(final String key, final String end, final String start, final long count) {
		return xRevRange(key, new StreamEntryId(end), new StreamEntryId(start), count);
	}

	/**
	 * This command is exactly like XRANGE, but with the notable difference of returning the entries in reverse order,
	 * and also taking the start-end range in reverse order: in XREVRANGE you need to state the end ID and later the start ID,
	 * and the command will produce all the element between (or exactly like) the two IDs, starting from the end side
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/xrevrange/" target="_blank">https://redis.io/commands/xrevrange/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param end
	 * 		End Id
	 * @param start
	 * 		Start Id
	 * @param count
	 * 		返回数量
	 *
	 * @return {@link StreamEntry} 列表
	 */
	default List<StreamEntry> xRevRange(final byte[] key, final byte[] end, final byte[] start, final long count) {
		return xRevRange(key, new StreamEntryId(end), new StreamEntryId(start), count);
	}

	@Override
	default Long xTrim(final String key, final XTrimArgument xTrimArgument) {
		return execute((client)->client.streamCommands().xTrim(key, xTrimArgument));
	}

	@Override
	default Long xTrim(final byte[] key, final XTrimArgument xTrimArgument) {
		return execute((client)->client.streamCommands().xTrim(key, xTrimArgument));
	}

	@Override
	default Long xTrim(final String key, final XTrimArgument xTrimArgument, final long limit) {
		return execute((client)->client.streamCommands().xTrim(key, xTrimArgument, limit));
	}

	@Override
	default Long xTrim(final byte[] key, final XTrimArgument xTrimArgument, final long limit) {
		return execute((client)->client.streamCommands().xTrim(key, xTrimArgument, limit));
	}

}
