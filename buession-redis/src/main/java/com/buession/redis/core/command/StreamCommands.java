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
package com.buession.redis.core.command;

import com.buession.redis.core.StreamEntry;
import com.buession.redis.core.StreamEntryId;

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
	 * @param group
	 * 		Group
	 * @param ids
	 * 		一个或多个 ID
	 *
	 * @return The command returns the number of messages successfully acknowledged
	 */
	long xAck(final String key, final String group, final StreamEntryId... ids);

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
	long xAck(final byte[] key, final byte[] group, final StreamEntryId... ids);

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
	StreamEntryId xAdd(final String key, final String id, final Map<String, String> hash);

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
	StreamEntryId xAdd(final byte[] key, final byte[] id, final Map<byte[], byte[]> hash);

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
	StreamEntryId xAdd(final String key, final String id, final Map<String, String> hash,
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
	StreamEntryId xAdd(final byte[] key, final byte[] id, final Map<byte[], byte[]> hash,
					   final XAddArgument xAddArgument);

	/**
	 * This command transfers ownership of pending stream entries that match the specified criteria
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
	 * @param start
	 * 		greater ID than
	 *
	 * @return {@link StreamEntryId} 和对应的 {@link StreamEntry}
	 */
	Map<StreamEntryId, List<StreamEntry>> xAutoClaim(final String key, final String group, final String consumerName,
													 final int minIdleTime, final String start);

	/**
	 * This command transfers ownership of pending stream entries that match the specified criteria
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
	 * @param start
	 * 		greater ID than
	 *
	 * @return {@link StreamEntryId} 和对应的 {@link StreamEntry}
	 */
	Map<StreamEntryId, List<StreamEntry>> xAutoClaim(final byte[] key, final byte[] group, final byte[] consumerName,
													 final int minIdleTime, final byte[] start);

	/**
	 * This command transfers ownership of pending stream entries that match the specified criteria
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
	 * @param start
	 * 		greater ID than
	 * @param count
	 * 		数量
	 *
	 * @return {@link StreamEntryId} 和对应的 {@link StreamEntry}
	 */
	Map<StreamEntryId, List<StreamEntry>> xAutoClaim(final String key, final String group, final String consumerName,
													 final int minIdleTime, final String start, final long count);

	/**
	 * This command transfers ownership of pending stream entries that match the specified criteria
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
	 * @param start
	 * 		greater ID than
	 * @param count
	 * 		数量
	 *
	 * @return {@link StreamEntryId} 和对应的 {@link StreamEntry}
	 */
	Map<StreamEntryId, List<StreamEntry>> xAutoClaim(final byte[] key, final byte[] group, final byte[] consumerName,
													 final int minIdleTime, final byte[] start, final long count);

	/**
	 * This command transfers ownership of pending stream entries that match the specified criteria
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
	 * @param start
	 * 		greater ID than
	 *
	 * @return {@link StreamEntryId} 和对应的 {@link StreamEntryId}
	 */
	Map<StreamEntryId, List<StreamEntryId>> xAutoClaimJustId(final String key, final String group,
															 final String consumerName,
															 final int minIdleTime, final String start);

	/**
	 * This command transfers ownership of pending stream entries that match the specified criteria
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
	 * @param start
	 * 		greater ID than
	 *
	 * @return {@link StreamEntryId} 和对应的 {@link StreamEntryId}
	 */
	Map<StreamEntryId, List<StreamEntryId>> xAutoClaimJustId(final byte[] key, final byte[] group,
															 final byte[] consumerName, final int minIdleTime,
															 final byte[] start);

	/**
	 * This command transfers ownership of pending stream entries that match the specified criteria
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
	 * @param start
	 * 		greater ID than
	 * @param count
	 * 		数量
	 *
	 * @return {@link StreamEntryId} 和对应的 {@link StreamEntryId}
	 */
	Map<StreamEntryId, List<StreamEntryId>> xAutoClaimJustId(final String key, final String group,
															 final String consumerName, final int minIdleTime,
															 final String start, final long count);

	/**
	 * This command transfers ownership of pending stream entries that match the specified criteria
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
	 * @param start
	 * 		greater ID than
	 * @param count
	 * 		数量
	 *
	 * @return {@link StreamEntryId} 和对应的 {@link StreamEntryId}
	 */
	Map<StreamEntryId, List<StreamEntryId>> xAutoClaimJustId(final byte[] key, final byte[] group,
															 final byte[] consumerName, final int minIdleTime,
															 final byte[] start, final long count);

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
	List<StreamEntry> xClaim(final String key, final String group, final String consumerName, final int minIdleTime,
							 final String... ids);

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
	List<StreamEntry> xClaim(final byte[] key, final byte[] group, final byte[] consumerName, final int minIdleTime,
							 final byte[]... ids);

	class XAddArgument {

		private Long maxLen;

		private Boolean approximateTrimming;

		private Boolean exactTrimming;

		private Boolean noMkStream;

		private String minId;

		private Long limit;

		public Long getMaxLen(){
			return maxLen;
		}

		public Boolean getApproximateTrimming(){
			return approximateTrimming;
		}

		public Boolean getExactTrimming(){
			return exactTrimming;
		}

		public Boolean getNoMkStream(){
			return noMkStream;
		}

		public String getMinId(){
			return minId;
		}

		public Long getLimit(){
			return limit;
		}

		@Override
		public String toString(){
			return ArgumentStringBuilder.create().
					add("maxLen", maxLen).
					add("approximateTrimming", approximateTrimming).
					add("exactTrimming", exactTrimming).
					add("noMkStream", noMkStream).
					add("minId", minId).
					add("limit", limit).build();
		}

		public static class Builder {

			private final XAddArgument xAddArgument = new XAddArgument();

			private Builder(){

			}

			public static Builder create(){
				return new Builder();
			}

			public Builder maxLen(long maxLen){
				xAddArgument.maxLen = maxLen;
				return this;
			}

			public Builder approximateTrimming(Boolean approximateTrimming){
				xAddArgument.approximateTrimming = approximateTrimming;
				return this;
			}

			public Builder exactTrimming(boolean exactTrimming){
				xAddArgument.exactTrimming = exactTrimming;
				return this;
			}

			public Builder noMkStream(boolean noMkStream){
				xAddArgument.noMkStream = noMkStream;
				return this;
			}

			public Builder minId(String minId){
				xAddArgument.minId = minId;
				return this;
			}

			public Builder limit(Long limit){
				xAddArgument.limit = limit;
				return this;
			}

			public XAddArgument build(){
				return xAddArgument;
			}

		}

	}

	class XClaimArgument {

		private Long idleTime;

		private Long idleUnixTime;

		private Integer retryCount;

		private Boolean force;

		public Long getIdleTime(){
			return idleTime;
		}

		public Long getIdleUnixTime(){
			return idleUnixTime;
		}

		public Integer getRetryCount(){
			return retryCount;
		}

		public Boolean getForce(){
			return force;
		}

		@Override
		public String toString(){
			return ArgumentStringBuilder.create().
					add("idleTime", idleTime).
					add("idleUnixTime", idleUnixTime).
					add("retryCount", retryCount).
					add("force", force).build();
		}

		public final static class Builder {

			private final XClaimArgument xClaimArgument = new XClaimArgument();

			private Builder(){

			}

			public static Builder create(){
				return new Builder();
			}

			public Builder idleTime(long idleTime){
				xClaimArgument.idleTime = idleTime;
				return this;
			}

			public Builder idleUnixTime(long idleUnixTime){
				xClaimArgument.idleUnixTime = idleUnixTime;
				return this;
			}

			public Builder retryCount(int retryCount){
				xClaimArgument.retryCount = retryCount;
				return this;
			}

			public Builder force(boolean force){
				xClaimArgument.force = force;
				return this;
			}

			public XClaimArgument build(){
				return xClaimArgument;
			}

		}

	}

}
