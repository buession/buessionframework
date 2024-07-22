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
 * | Copyright @ 2013-2022 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.core.operations;

import com.buession.core.collect.Arrays;
import com.buession.lang.Status;
import com.buession.redis.core.StreamEntry;
import com.buession.redis.core.StreamEntryId;
import com.buession.redis.core.StreamPending;
import com.buession.redis.core.command.StreamCommands;
import com.buession.redis.core.command.args.XAddArgument;

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
															 final String start, final long count) {
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
															 final byte[] start, final long count) {
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
																	 final String start, final long count) {
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
																	 final byte[] start, final long count) {
		return xAutoClaimJustId(key, groupName, consumerName, minIdleTime, new StreamEntryId(start), count);
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
										 final long count) {
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
										 final long count) {
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
										 final String start, final String end, final long count) {
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
										 final byte[] start, final byte[] end, final long count) {
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
										 final String end, final long count, final String consumerName) {
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
										 final byte[] end, final long count, final byte[] consumerName) {
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
										 final String start, final String end, final long count,
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
										 final byte[] start, final byte[] end, final long count,
										 final byte[] consumerName) {
		return xPending(key, groupName, minIdleTime, new StreamEntryId(start), new StreamEntryId(end), count,
				consumerName);
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
	default List<StreamEntry> xRange(final String key, final String start, final String end, final long count) {
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
	default List<StreamEntry> xRange(final byte[] key, final byte[] start, final byte[] end, final long count) {
		return xRange(key, new StreamEntryId(start), new StreamEntryId(end), count);
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

}
