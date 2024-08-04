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
package com.buession.redis.core.command;

import com.buession.lang.Status;
import com.buession.redis.core.Stream;
import com.buession.redis.core.StreamConsumer;
import com.buession.redis.core.StreamEntry;
import com.buession.redis.core.StreamEntryId;
import com.buession.redis.core.StreamFull;
import com.buession.redis.core.StreamGroup;
import com.buession.redis.core.StreamPending;
import com.buession.redis.core.StreamPendingSummary;
import com.buession.redis.utils.ObjectStringBuilder;

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
public interface StreamCommands extends RedisCommands {

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
	Long xAck(final String key, final String groupName, final StreamEntryId... ids);

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
	Long xAck(final byte[] key, final byte[] groupName, final StreamEntryId... ids);

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
	StreamEntryId xAdd(final String key, final StreamEntryId id, final Map<String, String> hash);

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
	StreamEntryId xAdd(final byte[] key, final StreamEntryId id, final Map<byte[], byte[]> hash);

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
	StreamEntryId xAdd(final String key, final StreamEntryId id, final Map<String, String> hash,
					   final XAddArgument xAddArgument);

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
	StreamEntryId xAdd(final byte[] key, final StreamEntryId id, final Map<byte[], byte[]> hash,
					   final XAddArgument xAddArgument);

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
	Map<StreamEntryId, List<StreamEntry>> xAutoClaim(final String key, final String groupName,
													 final String consumerName, final int minIdleTime,
													 final StreamEntryId start);

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
	Map<StreamEntryId, List<StreamEntry>> xAutoClaim(final byte[] key, final byte[] groupName,
													 final byte[] consumerName, final int minIdleTime,
													 final StreamEntryId start);

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
	Map<StreamEntryId, List<StreamEntry>> xAutoClaim(final String key, final String groupName,
													 final String consumerName, final int minIdleTime,
													 final StreamEntryId start, final long count);

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
	Map<StreamEntryId, List<StreamEntry>> xAutoClaim(final byte[] key, final byte[] groupName,
													 final byte[] consumerName, final int minIdleTime,
													 final StreamEntryId start,
													 final long count);

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
	Map<StreamEntryId, List<StreamEntryId>> xAutoClaimJustId(final String key, final String groupName,
															 final String consumerName, final int minIdleTime,
															 final StreamEntryId start);

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
	Map<StreamEntryId, List<StreamEntryId>> xAutoClaimJustId(final byte[] key, final byte[] groupName,
															 final byte[] consumerName, final int minIdleTime,
															 final StreamEntryId start);

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
	Map<StreamEntryId, List<StreamEntryId>> xAutoClaimJustId(final String key, final String groupName,
															 final String consumerName, final int minIdleTime,
															 final StreamEntryId start, final long count);

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
	Map<StreamEntryId, List<StreamEntryId>> xAutoClaimJustId(final byte[] key, final byte[] groupName,
															 final byte[] consumerName, final int minIdleTime,
															 final StreamEntryId start, final long count);

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
	List<StreamEntry> xClaim(final String key, final String groupName, final String consumerName, final int minIdleTime,
							 final StreamEntryId... ids);

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
	List<StreamEntry> xClaim(final byte[] key, final byte[] groupName, final byte[] consumerName, final int minIdleTime,
							 final StreamEntryId... ids);

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
	List<StreamEntry> xClaim(final String key, final String groupName, final String consumerName, final int minIdleTime,
							 final StreamEntryId[] ids, final XClaimArgument xClaimArgument);

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
	List<StreamEntry> xClaim(final byte[] key, final byte[] groupName, final byte[] consumerName, final int minIdleTime,
							 final StreamEntryId[] ids, final XClaimArgument xClaimArgument);

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
	List<StreamEntryId> xClaimJustId(final String key, final String groupName, final String consumerName,
									 final int minIdleTime, final StreamEntryId... ids);

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
	List<StreamEntryId> xClaimJustId(final byte[] key, final byte[] groupName, final byte[] consumerName,
									 final int minIdleTime, final StreamEntryId... ids);

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
	 * @return {@link StreamEntryId} 列表
	 */
	List<StreamEntryId> xClaimJustId(final String key, final String groupName, final String consumerName,
									 final int minIdleTime, final StreamEntryId[] ids,
									 final XClaimArgument xClaimArgument);

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
	 * @return {@link StreamEntryId} 列表
	 */
	List<StreamEntryId> xClaimJustId(final byte[] key, final byte[] groupName, final byte[] consumerName,
									 final int minIdleTime, final StreamEntryId[] ids,
									 final XClaimArgument xClaimArgument);

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
	Long xDel(final String key, final StreamEntryId... ids);

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
	Long xDel(final byte[] key, final StreamEntryId... ids);

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
	Status xGroupCreate(final String key, final String groupName, final StreamEntryId id, final boolean makeStream);

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
	Status xGroupCreate(final byte[] key, final byte[] groupName, final StreamEntryId id, final boolean makeStream);

	/**
	 * Create a consumer named consumername in the consumer group groupname of the stream that's stored at key
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/xgroup-createconsumer/" target="_blank">https://redis.io/commands/xgroup-createconsumer/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param groupName
	 * 		Group Name
	 * @param consumerName
	 * 		Consumer Name
	 *
	 * @return 创建成功，返回 Status.SUCCESS；否则，返回 Status.FAILURE
	 */
	Status xGroupCreateConsumer(final String key, final String groupName, final String consumerName);

	/**
	 * Create a consumer named consumername in the consumer group groupname of the stream that's stored at key
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/xgroup-createconsumer/" target="_blank">https://redis.io/commands/xgroup-createconsumer/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param groupName
	 * 		Group Name
	 * @param consumerName
	 * 		Consumer Name
	 *
	 * @return 创建成功，返回 Status.SUCCESS；否则，返回 Status.FAILURE
	 */
	Status xGroupCreateConsumer(final byte[] key, final byte[] groupName, final byte[] consumerName);

	/**
	 * The XGROUP DELCONSUMER command deletes a consumer from the consumer group
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/xgroup-delconsumer/" target="_blank">https://redis.io/commands/xgroup-delconsumer/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param groupName
	 * 		Group Name
	 * @param consumerName
	 * 		Consumer Name
	 *
	 * @return 删除数量
	 */
	Long xGroupDelConsumer(final String key, final String groupName, final String consumerName);

	/**
	 * The XGROUP DELCONSUMER command deletes a consumer from the consumer group
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/xgroup-delconsumer/" target="_blank">https://redis.io/commands/xgroup-delconsumer/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param groupName
	 * 		Group Name
	 * @param consumerName
	 * 		Consumer Name
	 *
	 * @return 删除数量
	 */
	Long xGroupDelConsumer(final byte[] key, final byte[] groupName, final byte[] consumerName);

	/**
	 * The XGROUP DESTROY command completely destroys a consumer group
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/xgroup-destroy/" target="_blank">https://redis.io/commands/xgroup-destroy/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param groupName
	 * 		Group Name
	 *
	 * @return 销毁成功，返回 Status.SUCCESS；否则，返回 Status.FAILURE
	 */
	Status xGroupDestroy(final String key, final String groupName);

	/**
	 * The XGROUP DESTROY command completely destroys a consumer group
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/xgroup-destroy/" target="_blank">https://redis.io/commands/xgroup-destroy/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param groupName
	 * 		Group Name
	 *
	 * @return 销毁成功，返回 Status.SUCCESS；否则，返回 Status.FAILURE
	 */
	Status xGroupDestroy(final byte[] key, final byte[] groupName);

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
	Status xGroupSetId(final String key, final String groupName, final StreamEntryId id);

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
	Status xGroupSetId(final byte[] key, final byte[] groupName, final StreamEntryId id);

	/**
	 * This command returns the list of consumers that belong to the groupname consumer group of the stream stored at key
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/xinfo-consumers/" target="_blank">https://redis.io/commands/xinfo-consumers/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param groupName
	 * 		Group Name
	 *
	 * @return {@link StreamConsumer} 列表
	 */
	List<StreamConsumer> xInfoConsumers(final String key, final String groupName);

	/**
	 * This command returns the list of consumers that belong to the groupname consumer group of the stream stored at key
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/xinfo-consumers/" target="_blank">https://redis.io/commands/xinfo-consumers/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param groupName
	 * 		Group Name
	 *
	 * @return {@link StreamConsumer} 列表
	 */
	List<StreamConsumer> xInfoConsumers(final byte[] key, final byte[] groupName);

	/**
	 * This command returns the list of all consumers groups of the stream stored at key
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/xinfo-groups/" target="_blank">https://redis.io/commands/xinfo-groups/</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return {@link StreamGroup} 列表
	 */
	List<StreamGroup> xInfoGroups(final String key);

	/**
	 * This command returns the list of all consumers groups of the stream stored at key
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/xinfo-groups/" target="_blank">https://redis.io/commands/xinfo-groups/</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return {@link StreamGroup} 列表
	 */
	List<StreamGroup> xInfoGroups(final byte[] key);

	/**
	 * This command returns information about the stream stored at key
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/xinfo-stream/" target="_blank">https://redis.io/commands/xinfo-stream/</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return {@link Stream}
	 */
	Stream xInfoStream(final String key);

	/**
	 * This command returns information about the stream stored at key
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/xinfo-stream/" target="_blank">https://redis.io/commands/xinfo-stream/</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return {@link Stream}
	 */
	Stream xInfoStream(final byte[] key);

	/**
	 * This command returns information about the stream stored at key
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/xinfo-stream/" target="_blank">https://redis.io/commands/xinfo-stream/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param full
	 * 		Full
	 *
	 * @return {@link StreamFull}
	 */
	StreamFull xInfoStream(final String key, final boolean full);

	/**
	 * This command returns information about the stream stored at key
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/xinfo-stream/" target="_blank">https://redis.io/commands/xinfo-stream/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param full
	 * 		Full
	 *
	 * @return {@link StreamFull}
	 */
	StreamFull xInfoStream(final byte[] key, final boolean full);

	/**
	 * This command returns information about the stream stored at key
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/xinfo-stream/" target="_blank">https://redis.io/commands/xinfo-stream/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param full
	 * 		Full
	 * @param count
	 * 		返回数量
	 *
	 * @return {@link StreamFull}
	 */
	StreamFull xInfoStream(final String key, final boolean full, final long count);

	/**
	 * This command returns information about the stream stored at key
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/xinfo-stream/" target="_blank">https://redis.io/commands/xinfo-stream/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param full
	 * 		Full
	 * @param count
	 * 		返回数量
	 *
	 * @return {@link StreamFull}
	 */
	StreamFull xInfoStream(final byte[] key, final boolean full, final long count);

	/**
	 * Returns the number of entries inside a stream
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/xlen/" target="_blank">https://redis.io/commands/xlen/</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return The number of entries of the stream at key
	 */
	Long xLen(final String key);

	/**
	 * Returns the number of entries inside a stream
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/xlen/" target="_blank">https://redis.io/commands/xlen/</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return The number of entries of the stream at key
	 */
	Long xLen(final byte[] key);

	/**
	 * Fetching data from a stream via a consumer group, and not acknowledging such data, has the effect of creating pending entries
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/xpending/" target="_blank">https://redis.io/commands/xpending/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param groupName
	 * 		Group Name
	 *
	 * @return {@link StreamPendingSummary}
	 */
	StreamPendingSummary xPending(final String key, final String groupName);

	/**
	 * Fetching data from a stream via a consumer group, and not acknowledging such data, has the effect of creating pending entries
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/xpending/" target="_blank">https://redis.io/commands/xpending/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param groupName
	 * 		Group Name
	 *
	 * @return {@link StreamPendingSummary}
	 */
	StreamPendingSummary xPending(final byte[] key, final byte[] groupName);

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
	 *
	 * @return {@link StreamPending} 列表
	 */
	List<StreamPending> xPending(final String key, final String groupName, final long minIdleTime);

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
	 *
	 * @return {@link StreamPending} 列表
	 */
	List<StreamPending> xPending(final byte[] key, final byte[] groupName, final long minIdleTime);

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
	List<StreamPending> xPending(final String key, final String groupName, final StreamEntryId start,
								 final StreamEntryId end, final long count);

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
	List<StreamPending> xPending(final byte[] key, final byte[] groupName, final StreamEntryId start,
								 final StreamEntryId end, final long count);

	/**
	 * Fetching data from a stream via a consumer group, and not acknowledging such data, has the effect of creating pending entries
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/xpending/" target="_blank">https://redis.io/commands/xpending/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param groupName
	 * 		Group Name
	 * @param consumerName
	 * 		Consumer Name
	 *
	 * @return {@link StreamPending} 列表
	 */
	List<StreamPending> xPending(final String key, final String groupName, final String consumerName);

	/**
	 * Fetching data from a stream via a consumer group, and not acknowledging such data, has the effect of creating pending entries
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/xpending/" target="_blank">https://redis.io/commands/xpending/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param groupName
	 * 		Group Name
	 * @param consumerName
	 * 		Consumer Name
	 *
	 * @return {@link StreamPending} 列表
	 */
	List<StreamPending> xPending(final byte[] key, final byte[] groupName, final byte[] consumerName);

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
	List<StreamPending> xPending(final String key, final String groupName, final long minIdleTime,
								 final StreamEntryId start, final StreamEntryId end, final long count);

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
	List<StreamPending> xPending(final byte[] key, final byte[] groupName, final long minIdleTime,
								 final StreamEntryId start, final StreamEntryId end, final long count);

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
	 * @param consumerName
	 * 		Consumer Name
	 *
	 * @return {@link StreamPending} 列表
	 */
	List<StreamPending> xPending(final String key, final String groupName, final long minIdleTime,
								 final String consumerName);

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
	 * @param consumerName
	 * 		Consumer Name
	 *
	 * @return {@link StreamPending} 列表
	 */
	List<StreamPending> xPending(final byte[] key, final byte[] groupName, final long minIdleTime,
								 final byte[] consumerName);

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
	List<StreamPending> xPending(final String key, final String groupName, final StreamEntryId start,
								 final StreamEntryId end, final long count, final String consumerName);

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
	List<StreamPending> xPending(final byte[] key, final byte[] groupName, final StreamEntryId start,
								 final StreamEntryId end, final long count, final byte[] consumerName);

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
	List<StreamPending> xPending(final String key, final String groupName, final long minIdleTime,
								 final StreamEntryId start, final StreamEntryId end, final long count,
								 final String consumerName);

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
	List<StreamPending> xPending(final byte[] key, final byte[] groupName, final long minIdleTime,
								 final StreamEntryId start, final StreamEntryId end, final long count,
								 final byte[] consumerName);

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
	List<StreamEntry> xRange(final String key, final StreamEntryId start, final StreamEntryId end);

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
	List<StreamEntry> xRange(final byte[] key, final StreamEntryId start, final StreamEntryId end);

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
	List<StreamEntry> xRange(final String key, final StreamEntryId start, final StreamEntryId end, final long count);

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
	List<StreamEntry> xRange(final byte[] key, final StreamEntryId start, final StreamEntryId end, final long count);

	/**
	 * Read data from one or multiple streams, only returning entries with an ID greater than the last received ID reported by the caller
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/xread/" target="_blank">https://redis.io/commands/xread/</a></p>
	 *
	 * @param streams
	 * 		key =&gt; StreamEntryId Streams
	 *
	 * @return {@link StreamEntry} 列表
	 */
	List<Map<String, List<StreamEntry>>> xRead(final Map<String, StreamEntryId> streams);

	/**
	 * Read data from one or multiple streams, only returning entries with an ID greater than the last received ID reported by the caller
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/xread/" target="_blank">https://redis.io/commands/xread/</a></p>
	 *
	 * @param count
	 * 		返回数量
	 * @param streams
	 * 		key =&gt; StreamEntryId Streams
	 *
	 * @return {@link StreamEntry} 列表
	 */
	List<Map<String, List<StreamEntry>>> xRead(final long count, final Map<String, StreamEntryId> streams);

	/**
	 * Read data from one or multiple streams, only returning entries with an ID greater than the last received ID reported by the caller
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/xread/" target="_blank">https://redis.io/commands/xread/</a></p>
	 *
	 * @param block
	 * 		阻塞时间（单位：毫秒）
	 * @param streams
	 * 		key =&gt; StreamEntryId Streams
	 *
	 * @return {@link StreamEntry} 列表
	 */
	List<Map<String, List<StreamEntry>>> xRead(final int block, final Map<String, StreamEntryId> streams);

	/**
	 * Read data from one or multiple streams, only returning entries with an ID greater than the last received ID reported by the caller
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/xread/" target="_blank">https://redis.io/commands/xread/</a></p>
	 *
	 * @param count
	 * 		返回数量
	 * @param block
	 * 		阻塞时间（单位：毫秒）
	 * @param streams
	 * 		key =&gt; StreamEntryId Streams
	 *
	 * @return {@link StreamEntry} 列表
	 */
	List<Map<String, List<StreamEntry>>> xRead(final long count, final int block,
											   final Map<String, StreamEntryId> streams);

	/**
	 * The XREADGROUP command is a special version of the XREAD command with support for consumer groups
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/xreadgroup/" target="_blank">https://redis.io/commands/xreadgroup/</a></p>
	 *
	 * @param groupName
	 * 		Group Name
	 * @param consumerName
	 * 		Consumer Name
	 * @param streams
	 * 		key =&gt; StreamEntryId Streams
	 *
	 * @return {@link StreamEntry} 列表
	 */
	List<Map<String, List<StreamEntry>>> xReadGroup(final String groupName, final String consumerName,
													final Map<String, StreamEntryId> streams);

	/**
	 * The XREADGROUP command is a special version of the XREAD command with support for consumer groups
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/xreadgroup/" target="_blank">https://redis.io/commands/xreadgroup/</a></p>
	 *
	 * @param groupName
	 * 		Group Name
	 * @param consumerName
	 * 		Consumer Name
	 * @param streams
	 * 		key =&gt; StreamEntryId Streams
	 *
	 * @return {@link StreamEntry} 列表
	 */
	List<Map<byte[], List<StreamEntry>>> xReadGroup(final byte[] groupName, final byte[] consumerName,
													final Map<byte[], StreamEntryId> streams);

	/**
	 * The XREADGROUP command is a special version of the XREAD command with support for consumer groups
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/xreadgroup/" target="_blank">https://redis.io/commands/xreadgroup/</a></p>
	 *
	 * @param groupName
	 * 		Group Name
	 * @param consumerName
	 * 		Consumer Name
	 * @param count
	 * 		返回数量
	 * @param streams
	 * 		key =&gt; StreamEntryId Streams
	 *
	 * @return {@link StreamEntry} 列表
	 */
	List<Map<String, List<StreamEntry>>> xReadGroup(final String groupName, final String consumerName, final long count,
													final Map<String, StreamEntryId> streams);

	/**
	 * The XREADGROUP command is a special version of the XREAD command with support for consumer groups
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/xreadgroup/" target="_blank">https://redis.io/commands/xreadgroup/</a></p>
	 *
	 * @param groupName
	 * 		Group Name
	 * @param consumerName
	 * 		Consumer Name
	 * @param count
	 * 		返回数量
	 * @param streams
	 * 		key =&gt; StreamEntryId Streams
	 *
	 * @return {@link StreamEntry} 列表
	 */
	List<Map<byte[], List<StreamEntry>>> xReadGroup(final byte[] groupName, final byte[] consumerName, final long count,
													final Map<byte[], StreamEntryId> streams);

	/**
	 * The XREADGROUP command is a special version of the XREAD command with support for consumer groups
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/xreadgroup/" target="_blank">https://redis.io/commands/xreadgroup/</a></p>
	 *
	 * @param groupName
	 * 		Group Name
	 * @param consumerName
	 * 		Consumer Name
	 * @param block
	 * 		阻塞时间（单位：毫秒）
	 * @param streams
	 * 		key =&gt; StreamEntryId Streams
	 *
	 * @return {@link StreamEntry} 列表
	 */
	List<Map<String, List<StreamEntry>>> xReadGroup(final String groupName, final String consumerName, final int block,
													final Map<String, StreamEntryId> streams);

	/**
	 * The XREADGROUP command is a special version of the XREAD command with support for consumer groups
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/xreadgroup/" target="_blank">https://redis.io/commands/xreadgroup/</a></p>
	 *
	 * @param groupName
	 * 		Group Name
	 * @param consumerName
	 * 		Consumer Name
	 * @param block
	 * 		阻塞时间（单位：毫秒）
	 * @param streams
	 * 		key =&gt; StreamEntryId Streams
	 *
	 * @return {@link StreamEntry} 列表
	 */
	List<Map<byte[], List<StreamEntry>>> xReadGroup(final byte[] groupName, final byte[] consumerName, final int block,
													final Map<byte[], StreamEntryId> streams);

	/**
	 * The XREADGROUP command is a special version of the XREAD command with support for consumer groups
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/xreadgroup/" target="_blank">https://redis.io/commands/xreadgroup/</a></p>
	 *
	 * @param groupName
	 * 		Group Name
	 * @param consumerName
	 * 		Consumer Name
	 * @param isNoAck
	 * 		No Ack
	 * @param streams
	 * 		key =&gt; StreamEntryId Streams
	 *
	 * @return {@link StreamEntry} 列表
	 */
	List<Map<String, List<StreamEntry>>> xReadGroup(final String groupName, final String consumerName,
													final boolean isNoAck, final Map<String, StreamEntryId> streams);

	/**
	 * The XREADGROUP command is a special version of the XREAD command with support for consumer groups
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/xreadgroup/" target="_blank">https://redis.io/commands/xreadgroup/</a></p>
	 *
	 * @param groupName
	 * 		Group Name
	 * @param consumerName
	 * 		Consumer Name
	 * @param isNoAck
	 * 		No Ack
	 * @param streams
	 * 		key =&gt; StreamEntryId Streams
	 *
	 * @return {@link StreamEntry} 列表
	 */
	List<Map<byte[], List<StreamEntry>>> xReadGroup(final byte[] groupName, final byte[] consumerName,
													final boolean isNoAck, final Map<byte[], StreamEntryId> streams);

	/**
	 * The XREADGROUP command is a special version of the XREAD command with support for consumer groups
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/xreadgroup/" target="_blank">https://redis.io/commands/xreadgroup/</a></p>
	 *
	 * @param groupName
	 * 		Group Name
	 * @param consumerName
	 * 		Consumer Name
	 * @param count
	 * 		返回数量
	 * @param block
	 * 		阻塞时间（单位：毫秒）
	 * @param streams
	 * 		key =&gt; StreamEntryId Streams
	 *
	 * @return {@link StreamEntry} 列表
	 */
	List<Map<String, List<StreamEntry>>> xReadGroup(final String groupName, final String consumerName, final long count,
													final int block, final Map<String, StreamEntryId> streams);

	/**
	 * The XREADGROUP command is a special version of the XREAD command with support for consumer groups
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/xreadgroup/" target="_blank">https://redis.io/commands/xreadgroup/</a></p>
	 *
	 * @param groupName
	 * 		Group Name
	 * @param consumerName
	 * 		Consumer Name
	 * @param count
	 * 		返回数量
	 * @param block
	 * 		阻塞时间（单位：毫秒）
	 * @param streams
	 * 		key =&gt; StreamEntryId Streams
	 *
	 * @return {@link StreamEntry} 列表
	 */
	List<Map<byte[], List<StreamEntry>>> xReadGroup(final byte[] groupName, final byte[] consumerName, final long count,
													final int block, final Map<byte[], StreamEntryId> streams);

	/**
	 * The XREADGROUP command is a special version of the XREAD command with support for consumer groups
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/xreadgroup/" target="_blank">https://redis.io/commands/xreadgroup/</a></p>
	 *
	 * @param groupName
	 * 		Group Name
	 * @param consumerName
	 * 		Consumer Name
	 * @param count
	 * 		返回数量
	 * @param isNoAck
	 * 		No Ack
	 * @param streams
	 * 		key =&gt; StreamEntryId Streams
	 *
	 * @return {@link StreamEntry} 列表
	 */
	List<Map<String, List<StreamEntry>>> xReadGroup(final String groupName, final String consumerName, final long count,
													final boolean isNoAck, final Map<String, StreamEntryId> streams);

	/**
	 * The XREADGROUP command is a special version of the XREAD command with support for consumer groups
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/xreadgroup/" target="_blank">https://redis.io/commands/xreadgroup/</a></p>
	 *
	 * @param groupName
	 * 		Group Name
	 * @param consumerName
	 * 		Consumer Name
	 * @param count
	 * 		返回数量
	 * @param isNoAck
	 * 		No Ack
	 * @param streams
	 * 		key =&gt; StreamEntryId Streams
	 *
	 * @return {@link StreamEntry} 列表
	 */
	List<Map<byte[], List<StreamEntry>>> xReadGroup(final byte[] groupName, final byte[] consumerName, final long count,
													final boolean isNoAck, final Map<byte[], StreamEntryId> streams);

	/**
	 * The XREADGROUP command is a special version of the XREAD command with support for consumer groups
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/xreadgroup/" target="_blank">https://redis.io/commands/xreadgroup/</a></p>
	 *
	 * @param groupName
	 * 		Group Name
	 * @param consumerName
	 * 		Consumer Name
	 * @param block
	 * 		阻塞时间（单位：毫秒）
	 * @param isNoAck
	 * 		No Ack
	 * @param streams
	 * 		key =&gt; StreamEntryId Streams
	 *
	 * @return {@link StreamEntry} 列表
	 */
	List<Map<String, List<StreamEntry>>> xReadGroup(final String groupName, final String consumerName, final int block,
													final boolean isNoAck, final Map<String, StreamEntryId> streams);

	/**
	 * The XREADGROUP command is a special version of the XREAD command with support for consumer groups
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/xreadgroup/" target="_blank">https://redis.io/commands/xreadgroup/</a></p>
	 *
	 * @param groupName
	 * 		Group Name
	 * @param consumerName
	 * 		Consumer Name
	 * @param block
	 * 		阻塞时间（单位：毫秒）
	 * @param isNoAck
	 * 		No Ack
	 * @param streams
	 * 		key =&gt; StreamEntryId Streams
	 *
	 * @return {@link StreamEntry} 列表
	 */
	List<Map<byte[], List<StreamEntry>>> xReadGroup(final byte[] groupName, final byte[] consumerName, final int block,
													final boolean isNoAck, final Map<byte[], StreamEntryId> streams);

	/**
	 * The XREADGROUP command is a special version of the XREAD command with support for consumer groups
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/xreadgroup/" target="_blank">https://redis.io/commands/xreadgroup/</a></p>
	 *
	 * @param groupName
	 * 		Group Name
	 * @param consumerName
	 * 		Consumer Name
	 * @param count
	 * 		返回数量
	 * @param block
	 * 		阻塞时间（单位：毫秒）
	 * @param isNoAck
	 * 		No Ack
	 * @param streams
	 * 		key =&gt; StreamEntryId Streams
	 *
	 * @return {@link StreamEntry} 列表
	 */
	List<Map<String, List<StreamEntry>>> xReadGroup(final String groupName, final String consumerName, final long count,
													final int block, final boolean isNoAck,
													final Map<String, StreamEntryId> streams);

	/**
	 * The XREADGROUP command is a special version of the XREAD command with support for consumer groups
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/xreadgroup/" target="_blank">https://redis.io/commands/xreadgroup/</a></p>
	 *
	 * @param groupName
	 * 		Group Name
	 * @param consumerName
	 * 		Consumer Name
	 * @param count
	 * 		返回数量
	 * @param block
	 * 		阻塞时间（单位：毫秒）
	 * @param isNoAck
	 * 		No Ack
	 * @param streams
	 * 		key =&gt; StreamEntryId Streams
	 *
	 * @return {@link StreamEntry} 列表
	 */
	List<Map<byte[], List<StreamEntry>>> xReadGroup(final byte[] groupName, final byte[] consumerName, final long count,
													final int block, final boolean isNoAck,
													final Map<byte[], StreamEntryId> streams);

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
	List<StreamEntry> xRevRange(final String key, final StreamEntryId end, final StreamEntryId start);

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
	List<StreamEntry> xRevRange(final byte[] key, final StreamEntryId end, final StreamEntryId start);

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
	List<StreamEntry> xRevRange(final String key, final StreamEntryId end, final StreamEntryId start, final long count);

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
	List<StreamEntry> xRevRange(final byte[] key, final StreamEntryId end, final StreamEntryId start, final long count);

	/**
	 * XTRIM trims the stream by evicting older entries (entries with lower IDs) if needed
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/xtrim/" target="_blank">https://redis.io/commands/xtrim/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param xTrimArgument
	 *        {@link XTrimArgument}
	 *
	 * @return The number of entries deleted from the stream.
	 */
	Long xTrim(final String key, final XTrimArgument xTrimArgument);

	/**
	 * XTRIM trims the stream by evicting older entries (entries with lower IDs) if needed
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/xtrim/" target="_blank">https://redis.io/commands/xtrim/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param xTrimArgument
	 *        {@link XTrimArgument}
	 *
	 * @return The number of entries deleted from the stream.
	 */
	Long xTrim(final byte[] key, final XTrimArgument xTrimArgument);

	/**
	 * XTRIM trims the stream by evicting older entries (entries with lower IDs) if needed
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/xtrim/" target="_blank">https://redis.io/commands/xtrim/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param xTrimArgument
	 *        {@link XTrimArgument}
	 * @param limit
	 * 		Limit
	 *
	 * @return The number of entries deleted from the stream.
	 */
	Long xTrim(final String key, final XTrimArgument xTrimArgument, final long limit);

	/**
	 * XTRIM trims the stream by evicting older entries (entries with lower IDs) if needed
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/xtrim/" target="_blank">https://redis.io/commands/xtrim/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param xTrimArgument
	 *        {@link XTrimArgument}
	 * @param limit
	 * 		Limit
	 *
	 * @return The number of entries deleted from the stream.
	 */
	Long xTrim(final byte[] key, final XTrimArgument xTrimArgument, final long limit);

	final class XAddArgument {

		private Long maxLen;

		private Boolean approximateTrimming;

		private Boolean exactTrimming;

		private Boolean noMkStream;

		private String minId;

		private Long limit;

		private XAddArgument() {
		}

		public Long getMaxLen() {
			return maxLen;
		}

		public Boolean isApproximateTrimming() {
			return approximateTrimming;
		}

		public Boolean isExactTrimming() {
			return exactTrimming;
		}

		public Boolean isNoMkStream() {
			return noMkStream;
		}

		public String getMinId() {
			return minId;
		}

		public Long getLimit() {
			return limit;
		}

		@Override
		public String toString() {
			return ObjectStringBuilder.create().
					add("maxLen", maxLen).
					add("approximateTrimming", approximateTrimming).
					add("exactTrimming", exactTrimming).
					add("noMkStream", noMkStream).
					add("minId", minId).
					add("limit", limit).build();
		}

		public static class Builder {

			private final XAddArgument xAddArgument = new XAddArgument();

			private Builder() {
			}

			public static Builder create() {
				return new Builder();
			}

			public Builder maxLen(long maxLen) {
				xAddArgument.maxLen = maxLen;
				return this;
			}

			public Builder approximateTrimming(Boolean approximateTrimming) {
				xAddArgument.approximateTrimming = approximateTrimming;
				return this;
			}

			public Builder exactTrimming(boolean exactTrimming) {
				xAddArgument.exactTrimming = exactTrimming;
				return this;
			}

			public Builder noMkStream(boolean noMkStream) {
				xAddArgument.noMkStream = noMkStream;
				return this;
			}

			public Builder minId(String minId) {
				xAddArgument.minId = minId;
				return this;
			}

			public Builder limit(Long limit) {
				xAddArgument.limit = limit;
				return this;
			}

			public XAddArgument build() {
				return xAddArgument;
			}

		}

	}

	final class XClaimArgument {

		private Long idleTime;

		private Long idleUnixTime;

		private Integer retryCount;

		private Boolean force;

		private XClaimArgument() {
		}

		public Long getIdleTime() {
			return idleTime;
		}

		public Long getIdleUnixTime() {
			return idleUnixTime;
		}

		public Integer getRetryCount() {
			return retryCount;
		}

		public Boolean isForce() {
			return force;
		}

		@Override
		public String toString() {
			return ObjectStringBuilder.create().
					add("idleTime", idleTime).
					add("idleUnixTime", idleUnixTime).
					add("retryCount", retryCount).
					add("force", force).build();
		}

		public final static class Builder {

			private final XClaimArgument xClaimArgument = new XClaimArgument();

			private Builder() {
			}

			public static Builder create() {
				return new Builder();
			}

			public Builder idleTime(long idleTime) {
				xClaimArgument.idleTime = idleTime;
				return this;
			}

			public Builder idleUnixTime(long idleUnixTime) {
				xClaimArgument.idleUnixTime = idleUnixTime;
				return this;
			}

			public Builder retryCount(int retryCount) {
				xClaimArgument.retryCount = retryCount;
				return this;
			}

			public Builder force(boolean force) {
				xClaimArgument.force = force;
				return this;
			}

			public XClaimArgument build() {
				return xClaimArgument;
			}

		}

	}

	class XTrimArgument {

		private Long maxLen;

		private Boolean approximateTrimming;

		private Boolean exactTrimming;

		private String minId;

		private Long limit;

		private XTrimArgument() {
		}

		public Long getMaxLen() {
			return maxLen;
		}

		public Boolean isApproximateTrimming() {
			return approximateTrimming;
		}

		public Boolean isExactTrimming() {
			return exactTrimming;
		}

		public String getMinId() {
			return minId;
		}

		public Long getLimit() {
			return limit;
		}

		@Override
		public String toString() {
			return ObjectStringBuilder.create().
					add("maxLen", maxLen).
					add("approximateTrimming", approximateTrimming).
					add("exactTrimming", exactTrimming).
					add("minId", minId).
					add("limit", limit).build();
		}

		public final static class Builder {

			private final XTrimArgument xTrimArgument = new XTrimArgument();

			private Builder() {
			}

			public static Builder create() {
				return new Builder();
			}

			public Builder maxLen(long maxLen) {
				xTrimArgument.maxLen = maxLen;
				return this;
			}

			public Builder approximateTrimming(boolean approximateTrimming) {
				xTrimArgument.approximateTrimming = approximateTrimming;
				return this;
			}

			public Builder exactTrimming(boolean exactTrimming) {
				xTrimArgument.exactTrimming = exactTrimming;
				return this;
			}

			public Builder minId(String minId) {
				xTrimArgument.minId = minId;
				return this;
			}

			public Builder limit(long limit) {
				xTrimArgument.limit = limit;
				return this;
			}

			public XTrimArgument build() {
				return xTrimArgument;
			}

		}
	}

}
