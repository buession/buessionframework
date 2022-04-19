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

import com.buession.lang.Status;
import com.buession.redis.core.StreamEntry;
import com.buession.redis.core.StreamEntryId;
import com.buession.redis.core.command.StreamCommands;
import com.buession.redis.utils.SafeEncoder;

import java.util.List;

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
	 * @param group
	 * 		Group
	 * @param ids
	 * 		一个或多个 ID
	 *
	 * @return The command returns the number of messages successfully acknowledged
	 */
	default long xAck(final String key, final String group, final String[] ids){
		final StreamEntryId[] streamEntryIds = ids == null ? null : new StreamEntryId[ids.length];

		if(ids != null){
			for(int i = 0; i < ids.length; i++){
				streamEntryIds[i] = new StreamEntryId(ids[i]);
			}
		}

		return xAck(key, group, streamEntryIds);
	}

	/**
	 * The XACK command removes one or multiple messages from the Pending Entries List (PEL) of a stream consumer group
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/xack/" target="_blank">https://redis.io/commands/xack/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param group
	 * 		Group
	 * @param ids
	 * 		一个或多个 ID
	 *
	 * @return The command returns the number of messages successfully acknowledged
	 */
	default long xAck(final byte[] key, final byte[] group, final byte[][] ids){
		final StreamEntryId[] streamEntryIds = ids == null ? null : new StreamEntryId[ids.length];

		if(ids != null){
			for(int i = 0; i < ids.length; i++){
				streamEntryIds[i] = new StreamEntryId(SafeEncoder.encode(ids[i]));
			}
		}

		return xAck(key, group, streamEntryIds);
	}

	/**
	 * In the context of a stream consumer group, this command changes the ownership of a pending message,
	 * so that the new owner is the consumer specified as the command argument
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/xclaim/" target="_blank">https://redis.io/commands/xclaim/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param group
	 * 		Group
	 * @param consumerName
	 * 		Consumer Name
	 * @param minIdleTime
	 * 		Min Idle Time
	 * @param ids
	 * 		一个或多个 ID
	 *
	 * @return {@link StreamEntry} 列表
	 */
	default List<StreamEntry> xClaim(final String key, final String group, final String consumerName,
									 final int minIdleTime, final String[] ids){
		final StreamEntryId[] streamEntryIds = ids == null ? null : new StreamEntryId[ids.length];

		if(ids != null){
			for(int i = 0; i < ids.length; i++){
				streamEntryIds[i] = new StreamEntryId(ids[i]);
			}
		}

		return xClaim(key, group, consumerName, minIdleTime, streamEntryIds);
	}

	/**
	 * In the context of a stream consumer group, this command changes the ownership of a pending message,
	 * so that the new owner is the consumer specified as the command argument
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/xautoclaim/" target="_blank">https://redis.io/commands/xautoclaim/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param group
	 * 		Group
	 * @param consumerName
	 * 		Consumer Name
	 * @param minIdleTime
	 * 		Min Idle Time
	 * @param ids
	 * 		一个或多个 ID
	 *
	 * @return {@link StreamEntry} 列表
	 */
	default List<StreamEntry> xClaim(final byte[] key, final byte[] group, final byte[] consumerName,
									 final int minIdleTime, final byte[][] ids){
		final StreamEntryId[] streamEntryIds = ids == null ? null : new StreamEntryId[ids.length];

		if(ids != null){
			for(int i = 0; i < ids.length; i++){
				streamEntryIds[i] = new StreamEntryId(SafeEncoder.encode(ids[i]));
			}
		}

		return xClaim(key, group, consumerName, minIdleTime, streamEntryIds);
	}


	/**
	 * In the context of a stream consumer group, this command changes the ownership of a pending message,
	 * so that the new owner is the consumer specified as the command argument
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/xclaim/" target="_blank">https://redis.io/commands/xclaim/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param group
	 * 		Group
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
	default List<StreamEntry> xClaim(final String key, final String group, final String consumerName,
									 final int minIdleTime, final String[] ids, final XClaimArgument xClaimArgument){
		final StreamEntryId[] streamEntryIds = ids == null ? null : new StreamEntryId[ids.length];

		if(ids != null){
			for(int i = 0; i < ids.length; i++){
				streamEntryIds[i] = new StreamEntryId(ids[i]);
			}
		}

		return xClaim(key, group, consumerName, minIdleTime, streamEntryIds, xClaimArgument);
	}

	/**
	 * In the context of a stream consumer group, this command changes the ownership of a pending message,
	 * so that the new owner is the consumer specified as the command argument
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/xautoclaim/" target="_blank">https://redis.io/commands/xautoclaim/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param group
	 * 		Group
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
	default List<StreamEntry> xClaim(final byte[] key, final byte[] group, final byte[] consumerName,
									 final int minIdleTime, final byte[][] ids, final XClaimArgument xClaimArgument){
		final StreamEntryId[] streamEntryIds = ids == null ? null : new StreamEntryId[ids.length];

		if(ids != null){
			for(int i = 0; i < ids.length; i++){
				streamEntryIds[i] = new StreamEntryId(SafeEncoder.encode(ids[i]));
			}
		}

		return xClaim(key, group, consumerName, minIdleTime, streamEntryIds, xClaimArgument);
	}

	/**
	 * In the context of a stream consumer group, this command changes the ownership of a pending message,
	 * so that the new owner is the consumer specified as the command argument
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/xclaim/" target="_blank">https://redis.io/commands/xclaim/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param group
	 * 		Group
	 * @param consumerName
	 * 		Consumer Name
	 * @param minIdleTime
	 * 		Min Idle Time
	 * @param ids
	 * 		一个或多个 ID
	 *
	 * @return {@link StreamEntryId} 列表
	 */
	default List<StreamEntryId> xClaimJustId(final String key, final String group, final String consumerName,
											 final int minIdleTime, final String[] ids){
		final StreamEntryId[] streamEntryIds = ids == null ? null : new StreamEntryId[ids.length];

		if(ids != null){
			for(int i = 0; i < ids.length; i++){
				streamEntryIds[i] = new StreamEntryId(ids[i]);
			}
		}

		return xClaimJustId(key, group, consumerName, minIdleTime, streamEntryIds);
	}

	/**
	 * In the context of a stream consumer group, this command changes the ownership of a pending message,
	 * so that the new owner is the consumer specified as the command argument
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/xautoclaim/" target="_blank">https://redis.io/commands/xautoclaim/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param group
	 * 		Group
	 * @param consumerName
	 * 		Consumer Name
	 * @param minIdleTime
	 * 		Min Idle Time
	 * @param ids
	 * 		一个或多个 ID
	 *
	 * @return {@link StreamEntryId} 列表
	 */
	default List<StreamEntryId> xClaimJustId(final byte[] key, final byte[] group, final byte[] consumerName,
											 final int minIdleTime, final byte[][] ids){
		final StreamEntryId[] streamEntryIds = ids == null ? null : new StreamEntryId[ids.length];

		if(ids != null){
			for(int i = 0; i < ids.length; i++){
				streamEntryIds[i] = new StreamEntryId(SafeEncoder.encode(ids[i]));
			}
		}

		return xClaimJustId(key, group, consumerName, minIdleTime, streamEntryIds);
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
	default long xDel(final String key, final String... ids){
		final StreamEntryId[] streamEntryIds = ids == null ? null : new StreamEntryId[ids.length];

		if(ids != null){
			for(int i = 0; i < ids.length; i++){
				streamEntryIds[i] = new StreamEntryId(ids[i]);
			}
		}

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
	default long xDel(final byte[] key, final byte[]... ids){
		final StreamEntryId[] streamEntryIds = ids == null ? null : new StreamEntryId[ids.length];

		if(ids != null){
			for(int i = 0; i < ids.length; i++){
				streamEntryIds[i] = new StreamEntryId(SafeEncoder.encode(ids[i]));
			}
		}

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
	default Status xGroupCreate(final String key, final String groupName, final String id, final boolean makeStream){
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
	default Status xGroupCreate(final byte[] key, final byte[] groupName, final byte[] id, final boolean makeStream){
		return xGroupCreate(key, groupName, new StreamEntryId(SafeEncoder.encode(id)), makeStream);
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
	default Status xGroupSetId(final String key, final String groupName, final String id){
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
	default Status xGroupSetId(final byte[] key, final byte[] groupName, final byte[] id){
		return xGroupSetId(key, groupName, new StreamEntryId(SafeEncoder.encode(id)));
	}

}
