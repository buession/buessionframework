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
import redis.clients.jedis.params.XPendingParams;

/**
 * Jedis {@link XPendingParams} 扩展
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public final class JedisXPendingParams extends XPendingParams {

	/**
	 * 构造函数
	 *
	 * @param start
	 * 		-
	 * @param end
	 * 		-
	 * @param count
	 * 		返回数量
	 */
	public JedisXPendingParams(final String start, final String end, final int count) {
		this(new StreamEntryID(start), new StreamEntryID(end), count);
	}

	/**
	 * 构造函数
	 *
	 * @param start
	 * 		-
	 * @param end
	 * 		-
	 * @param count
	 * 		返回数量
	 */
	public JedisXPendingParams(final byte[] start, final byte[] end, final int count) {
		super(new StreamEntryID(start), new StreamEntryID(end), count);
	}

	/**
	 * 构造函数
	 *
	 * @param start
	 * 		-
	 * @param end
	 * 		-
	 * @param count
	 * 		返回数量
	 */
	public JedisXPendingParams(final StreamEntryId start, final StreamEntryId end, final int count) {
		super(JedisStreamEntryID.from(start), JedisStreamEntryID.from(end), count);
	}

	/**
	 * 构造函数
	 *
	 * @param start
	 * 		-
	 * @param end
	 * 		-
	 * @param count
	 * 		返回数量
	 */
	public JedisXPendingParams(final StreamEntryID start, final StreamEntryID end, final int count) {
		super(start, end, count);
	}

	/**
	 * 构造函数
	 *
	 * @param start
	 * 		-
	 * @param end
	 * 		-
	 * @param count
	 * 		返回数量
	 * @param idle
	 * 		-
	 */
	public JedisXPendingParams(final String start, final String end, final int count, final long idle) {
		this(start, end, count);
		idle(idle);
	}

	/**
	 * 构造函数
	 *
	 * @param start
	 * 		-
	 * @param end
	 * 		-
	 * @param count
	 * 		返回数量
	 * @param idle
	 * 		-
	 */
	public JedisXPendingParams(final byte[] start, final byte[] end, final int count, final long idle) {
		this(start, end, count);
		idle(idle);
	}

	/**
	 * 构造函数
	 *
	 * @param start
	 * 		-
	 * @param end
	 * 		-
	 * @param count
	 * 		返回数量
	 * @param idle
	 * 		-
	 */
	public JedisXPendingParams(final StreamEntryId start, final StreamEntryId end, final int count, final long idle) {
		this(start, end, count);
		idle(idle);
	}

	/**
	 * 构造函数
	 *
	 * @param start
	 * 		-
	 * @param end
	 * 		-
	 * @param count
	 * 		返回数量
	 * @param idle
	 * 		-
	 */
	public JedisXPendingParams(final StreamEntryID start, final StreamEntryID end, final int count, final long idle) {
		super(start, end, count);
		idle(idle);
	}

	/**
	 * 构造函数
	 *
	 * @param start
	 * 		-
	 * @param end
	 * 		-
	 * @param count
	 * 		返回数量
	 * @param consumer
	 * 		-
	 */
	public JedisXPendingParams(final String start, final String end, final int count, final String consumer) {
		this(start, end, count);
		consumer(consumer);
	}

	/**
	 * 构造函数
	 *
	 * @param start
	 * 		-
	 * @param end
	 * 		-
	 * @param count
	 * 		返回数量
	 * @param consumer
	 * 		-
	 */
	public JedisXPendingParams(final byte[] start, final byte[] end, final int count, final byte[] consumer) {
		this(start, end, count);
		consumer(consumer);
	}

	/**
	 * 构造函数
	 *
	 * @param start
	 * 		-
	 * @param end
	 * 		-
	 * @param count
	 * 		返回数量
	 * @param consumer
	 * 		-
	 */
	public JedisXPendingParams(final StreamEntryId start, final StreamEntryId end, final int count,
							   final String consumer) {
		this(start, end, count);
		consumer(consumer);
	}

	/**
	 * 构造函数
	 *
	 * @param start
	 * 		-
	 * @param end
	 * 		-
	 * @param count
	 * 		返回数量
	 * @param consumer
	 * 		-
	 */
	public JedisXPendingParams(final StreamEntryId start, final StreamEntryId end, final int count,
							   final byte[] consumer) {
		this(start, end, count);
		consumer(consumer);
	}

	/**
	 * 构造函数
	 *
	 * @param start
	 * 		-
	 * @param end
	 * 		-
	 * @param count
	 * 		返回数量
	 * @param consumer
	 * 		-
	 */
	public JedisXPendingParams(final StreamEntryID start, final StreamEntryID end, final int count,
							   final String consumer) {
		super(start, end, count);
		consumer(consumer);
	}

	/**
	 * 构造函数
	 *
	 * @param start
	 * 		-
	 * @param end
	 * 		-
	 * @param count
	 * 		返回数量
	 * @param consumer
	 * 		-
	 */
	public JedisXPendingParams(final StreamEntryID start, final StreamEntryID end, final int count,
							   final byte[] consumer) {
		super(start, end, count);
		consumer(consumer);
	}

	/**
	 * 构造函数
	 *
	 * @param start
	 * 		-
	 * @param end
	 * 		-
	 * @param count
	 * 		返回数量
	 * @param idle
	 * 		-
	 * @param consumer
	 * 		-
	 */
	public JedisXPendingParams(final String start, final String end, final int count, final long idle,
							   final String consumer) {
		this(start, end, count, idle);
		consumer(consumer);
	}

	/**
	 * 构造函数
	 *
	 * @param start
	 * 		-
	 * @param end
	 * 		-
	 * @param count
	 * 		返回数量
	 * @param idle
	 * 		-
	 * @param consumer
	 * 		-
	 */
	public JedisXPendingParams(final byte[] start, final byte[] end, final int count, final long idle,
							   final byte[] consumer) {
		this(start, end, count, idle);
		consumer(consumer);
	}

	/**
	 * 构造函数
	 *
	 * @param start
	 * 		-
	 * @param end
	 * 		-
	 * @param count
	 * 		返回数量
	 * @param idle
	 * 		-
	 * @param consumer
	 * 		-
	 */
	public JedisXPendingParams(final StreamEntryId start, final StreamEntryId end, final int count, final long idle,
							   final String consumer) {
		this(start, end, count, idle);
		consumer(consumer);
	}

	/**
	 * 构造函数
	 *
	 * @param start
	 * 		-
	 * @param end
	 * 		-
	 * @param count
	 * 		返回数量
	 * @param idle
	 * 		-
	 * @param consumer
	 * 		-
	 */
	public JedisXPendingParams(final StreamEntryId start, final StreamEntryId end, final int count, final long idle,
							   final byte[] consumer) {
		this(start, end, count, idle);
		consumer(consumer);
	}

	/**
	 * 构造函数
	 *
	 * @param start
	 * 		-
	 * @param end
	 * 		-
	 * @param count
	 * 		返回数量
	 * @param idle
	 * 		-
	 * @param consumer
	 * 		-
	 */
	public JedisXPendingParams(final StreamEntryID start, final StreamEntryID end, final int count, final long idle,
							   final String consumer) {
		this(start, end, count, idle);
		consumer(consumer);
	}

	/**
	 * 构造函数
	 *
	 * @param start
	 * 		-
	 * @param end
	 * 		-
	 * @param count
	 * 		返回数量
	 * @param idle
	 * 		-
	 * @param consumer
	 * 		-
	 */
	public JedisXPendingParams(final StreamEntryID start, final StreamEntryID end, final int count, final long idle,
							   final byte[] consumer) {
		this(start, end, count, idle);
		consumer(consumer);
	}

	/**
	 * 构造函数
	 *
	 * @param idle
	 * 		-
	 */
	public JedisXPendingParams(final long idle) {
		super();
		idle(idle);
	}

	/**
	 * 构造函数
	 *
	 * @param consumer
	 * 		-
	 */
	public JedisXPendingParams(final String consumer) {
		super();
		consumer(consumer);
	}

	/**
	 * 构造函数
	 *
	 * @param consumer
	 * 		-
	 */
	public JedisXPendingParams(final byte[] consumer) {
		super();
		consumer(consumer);
	}

	/**
	 * 构造函数
	 *
	 * @param idle
	 * 		-
	 * @param consumer
	 * 		-
	 */
	public JedisXPendingParams(final long idle, final String consumer) {
		idle(idle);
		consumer(consumer);
	}

	/**
	 * 构造函数
	 *
	 * @param idle
	 * 		-
	 * @param consumer
	 * 		-
	 */
	public JedisXPendingParams(final long idle, final byte[] consumer) {
		idle(idle);
		consumer(consumer);
	}

}
