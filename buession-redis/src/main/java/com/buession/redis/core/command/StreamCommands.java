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
	long xAck(final String key, final String group, final String... ids);

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
	long xAck(final byte[] key, final byte[] group, final byte[]... ids);

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
	 * @return {@link StreamEntry}
	 */
	StreamEntry xAdd(final String key, final String id, final Map<String, String> hash);

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
	 * @return {@link StreamEntry}
	 */
	StreamEntry xAdd(final byte[] key, final byte[] id, final Map<byte[], byte[]> hash);

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
	 * @return {@link StreamEntry}
	 */
	StreamEntry xAdd(final String key, final String id, final Map<String, String> hash,
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
	 * @return {@link StreamEntry}
	 */
	StreamEntry xAdd(final byte[] key, final byte[] id, final Map<byte[], byte[]> hash,
					 final XAddArgument xAddArgument);

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

			public Builder exactTrimming(Boolean exactTrimming){
				xAddArgument.exactTrimming = exactTrimming;
				return this;
			}

			public Builder noMkStream(Boolean noMkStream){
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

}
