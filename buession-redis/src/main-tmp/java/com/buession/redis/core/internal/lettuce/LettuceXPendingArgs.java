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
package com.buession.redis.core.internal.lettuce;

import com.buession.redis.core.StreamEntryId;
import com.buession.redis.utils.SafeEncoder;
import io.lettuce.core.Consumer;
import io.lettuce.core.Limit;
import io.lettuce.core.Range;
import io.lettuce.core.XPendingArgs;

/**
 * Lettuce {@link XPendingArgs} 扩展
 *
 * @param <T>
 * 		类型
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public class LettuceXPendingArgs<T> extends XPendingArgs<T> {

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
	public LettuceXPendingArgs(final String start, final String end, final int count) {
		super();
		range(Range.create(start, end));
		count(count);
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
	public LettuceXPendingArgs(final byte[] start, final byte[] end, final int count) {
		this(SafeEncoder.encode(start), SafeEncoder.encode(end), count);
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
	public LettuceXPendingArgs(final StreamEntryId start, final StreamEntryId end, final int count) {
		this(start.toString(), end.toString(), count);
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
	public LettuceXPendingArgs(final String start, final String end, final int count, final long idle) {
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
	public LettuceXPendingArgs(final byte[] start, final byte[] end, final int count, final long idle) {
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
	public LettuceXPendingArgs(final StreamEntryId start, final StreamEntryId end, final int count, final long idle) {
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
	 * @param groupName
	 * 		-
	 * @param consumerName
	 * 		-
	 */
	public LettuceXPendingArgs(final String start, final String end, final int count,
							   final T groupName, final T consumerName) {
		this(start, end, count);
		consumer(groupName, consumerName);
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
	 * @param groupName
	 * 		-
	 * @param consumerName
	 * 		-
	 */
	public LettuceXPendingArgs(final byte[] start, final byte[] end, final int count, final T groupName,
							   final T consumerName) {
		this(start, end, count);
		consumer(groupName, consumerName);
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
	 * @param groupName
	 * 		-
	 * @param consumerName
	 * 		-
	 */
	public LettuceXPendingArgs(final StreamEntryId start, final StreamEntryId end, final int count, final T groupName,
							   final T consumerName) {
		this(start, end, count);
		consumer(groupName, consumerName);
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
	public LettuceXPendingArgs(final String start, final String end, final int count, final Consumer<T> consumer) {
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
	public LettuceXPendingArgs(final byte[] start, final byte[] end, final int count, final Consumer<T> consumer) {
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
	public LettuceXPendingArgs(final StreamEntryId start, final StreamEntryId end, final int count,
							   final Consumer<T> consumer) {
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
	 * @param idle
	 * 		-
	 * @param groupName
	 * 		-
	 * @param consumerName
	 * 		-
	 */
	public LettuceXPendingArgs(final String start, final String end, final int count, final long idle,
							   final T groupName, final T consumerName) {
		this(start, end, count, idle);
		consumer(groupName, consumerName);
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
	 * @param groupName
	 * 		-
	 * @param consumerName
	 * 		-
	 */
	public LettuceXPendingArgs(final byte[] start, final byte[] end, final int count, final long idle,
							   final T groupName, final T consumerName) {
		this(start, end, count, idle);
		consumer(groupName, consumerName);
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
	 * @param groupName
	 * 		-
	 * @param consumerName
	 * 		-
	 */
	public LettuceXPendingArgs(final StreamEntryId start, final StreamEntryId end, final int count, final long idle,
							   final T groupName, final T consumerName) {
		this(start, end, count, idle);
		consumer(groupName, consumerName);
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
	public LettuceXPendingArgs(final String start, final String end, final int count, final long idle,
							   final Consumer<T> consumer) {
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
	public LettuceXPendingArgs(final byte[] start, final byte[] end, final int count, final long idle,
							   final Consumer<T> consumer) {
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
	public LettuceXPendingArgs(final StreamEntryId start, final StreamEntryId end, final int count, final long idle,
							   final Consumer<T> consumer) {
		this(start, end, count, idle);
		consumer(consumer);
	}

	/**
	 * 构造函数
	 *
	 * @param idle
	 * 		-
	 */
	public LettuceXPendingArgs(final long idle) {
		super();
		idle(idle);
	}

	/**
	 * 构造函数
	 *
	 * @param groupName
	 * 		-
	 * @param consumerName
	 * 		-
	 */
	public LettuceXPendingArgs(final T groupName, final T consumerName) {
		super();
		consumer(groupName, consumerName);
	}

	/**
	 * 构造函数
	 *
	 * @param consumer
	 * 		-
	 */
	public LettuceXPendingArgs(final Consumer<T> consumer) {
		super();
		consumer(consumer);
	}

	/**
	 * 构造函数
	 *
	 * @param idle
	 * 		-
	 * @param groupName
	 * 		-
	 * @param consumerName
	 * 		-
	 */
	public LettuceXPendingArgs(final long idle, final T groupName, final T consumerName) {
		this(idle);
		consumer(groupName, consumerName);
	}

	/**
	 * 构造函数
	 *
	 * @param idle
	 * 		返回数量
	 * @param consumer
	 * 		-
	 */
	public LettuceXPendingArgs(final long idle, final Consumer<T> consumer) {
		this(idle);
		consumer(consumer);
	}

	public LettuceXPendingArgs<T> consumer(final T groupName, final T consumerName) {
		consumer(Consumer.from(groupName, consumerName));
		return this;
	}

	public LettuceXPendingArgs<T> count(final int count) {
		limit(Limit.from(count));
		return this;
	}

}
