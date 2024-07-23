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
package com.buession.redis.core.internal.jedis;

import com.buession.redis.core.StreamEntryId;
import redis.clients.jedis.StreamEntryID;

import java.util.Objects;

/**
 * Jedis {@link StreamEntryID} 扩展
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public class JedisStreamEntryID extends StreamEntryID {

	/**
	 * 构造函数
	 */
	public JedisStreamEntryID() {
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param id
	 * 		Stream Entry ID
	 */
	public JedisStreamEntryID(final String id) {
		super(id);
	}

	/**
	 * 构造函数
	 *
	 * @param id
	 * 		Stream Entry ID
	 */
	public JedisStreamEntryID(final byte[] id) {
		super(id);
	}

	/**
	 * 构造函数
	 *
	 * @param time
	 * 		Stream Entry ID 生成时间戳
	 */
	public JedisStreamEntryID(final long time) {
		super(time);
	}

	/**
	 * 构造函数
	 *
	 * @param time
	 * 		Stream Entry ID 生成时间戳
	 * @param sequence
	 * 		序号
	 */
	public JedisStreamEntryID(final long time, final long sequence) {
		super(time, sequence);
	}

	/**
	 * 从 {@link StreamEntryId} 创建 {@link StreamEntryID} 实例
	 *
	 * @param streamEntryId
	 *        {@link StreamEntryId}
	 *
	 * @return {@link JedisStreamEntryID} 实例
	 */
	public static JedisStreamEntryID from(final StreamEntryId streamEntryId) {
		final String str = streamEntryId.toString();

		if(Objects.equals(str, NEW_ENTRY.toString())
				|| Objects.equals(str, UNRECEIVED_ENTRY.toString())
				|| Objects.equals(str, LAST_ENTRY.toString())
				|| Objects.equals(str, MINIMUM_ID.toString())
				|| Objects.equals(str, MAXIMUM_ID.toString())){
			return new JedisStreamEntryID() {

				@Override
				public String toString() {
					return str;
				}

			};
		}else{
			return new JedisStreamEntryID(streamEntryId.getTime(), streamEntryId.getSequence());
		}
	}

}
