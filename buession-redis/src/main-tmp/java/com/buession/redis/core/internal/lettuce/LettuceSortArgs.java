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

import com.buession.lang.Order;
import com.buession.redis.core.Limit;
import com.buession.redis.core.command.args.SortArgument;
import com.buession.redis.utils.SafeEncoder;
import io.lettuce.core.SortArgs;

import java.util.Arrays;
import java.util.Optional;

/**
 * Lettuce {@link SortArgs} 扩展
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public final class LettuceSortArgs extends SortArgs {

	/**
	 * 构造函数
	 */
	public LettuceSortArgs() {
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param by
	 * 		-
	 */
	public LettuceSortArgs(final String by) {
		super();
		by(by);
	}

	/**
	 * 构造函数
	 *
	 * @param by
	 * 		-
	 */
	public LettuceSortArgs(final byte[] by) {
		super();
		by(by);
	}

	/**
	 * 构造函数
	 *
	 * @param by
	 * 		-
	 * @param gets
	 * 		-
	 */
	public LettuceSortArgs(final String by, final String[] gets) {
		this(by);
		if(gets != null){
			Arrays.stream(gets).forEach(this::get);
		}
	}

	/**
	 * 构造函数
	 *
	 * @param by
	 * 		-
	 * @param gets
	 * 		-
	 */
	public LettuceSortArgs(final byte[] by, final byte[][] gets) {
		this(by);
		if(gets != null){
			Arrays.stream(gets).forEach(this::get);
		}
	}

	/**
	 * 构造函数
	 *
	 * @param by
	 * 		-
	 * @param order
	 * 		排序方式
	 */
	public LettuceSortArgs(final String by, final Order order) {
		this(by);
		order(this, order);
	}

	/**
	 * 构造函数
	 *
	 * @param by
	 * 		-
	 * @param order
	 * 		排序方式
	 */
	public LettuceSortArgs(final byte[] by, final Order order) {
		this(by);
		order(this, order);
	}

	/**
	 * 构造函数
	 *
	 * @param by
	 * 		-
	 * @param gets
	 * 		-
	 * @param order
	 * 		排序方式
	 */
	public LettuceSortArgs(final String by, final String[] gets, final Order order) {
		this(by, gets);
		order(this, order);
	}

	/**
	 * 构造函数
	 *
	 * @param by
	 * 		-
	 * @param gets
	 * 		-
	 * @param order
	 * 		排序方式
	 */
	public LettuceSortArgs(final byte[] by, final byte[][] gets, final Order order) {
		this(by, gets);
		order(this, order);
	}

	/**
	 * 构造函数
	 *
	 * @param by
	 * 		-
	 * @param gets
	 * 		-
	 * @param limit
	 * 		结果限制
	 */
	public LettuceSortArgs(final String by, final String[] gets, final Limit limit) {
		this(by, gets);
		limit(this, limit);
	}

	/**
	 * 构造函数
	 *
	 * @param by
	 * 		-
	 * @param gets
	 * 		-
	 * @param limit
	 * 		结果限制
	 */
	public LettuceSortArgs(final byte[] by, final byte[][] gets, final Limit limit) {
		this(by, gets);
		limit(this, limit);
	}

	/**
	 * 构造函数
	 *
	 * @param by
	 * 		-
	 * @param gets
	 * 		-
	 * @param order
	 * 		排序方式
	 * @param limit
	 * 		结果限制
	 */
	public LettuceSortArgs(final String by, final String[] gets, final Order order, final Limit limit) {
		this(by, gets, order);
		limit(this, limit);
	}

	/**
	 * 构造函数
	 *
	 * @param by
	 * 		-
	 * @param gets
	 * 		-
	 * @param order
	 * 		排序方式
	 * @param limit
	 * 		结果限制
	 */
	public LettuceSortArgs(final byte[] by, final byte[][] gets, final Order order, final Limit limit) {
		this(by, gets, order);
		limit(this, limit);
	}

	/**
	 * 构造函数
	 *
	 * @param by
	 * 		-
	 * @param gets
	 * 		-
	 * @param order
	 * 		排序方式
	 * @param limit
	 * 		结果限制
	 * @param alpha
	 * 		-
	 */
	public LettuceSortArgs(final String by, final String[] gets, final Order order, final Limit limit,
						   final boolean alpha) {
		this(by, gets, order, limit);
		alpha(this, alpha);
	}

	/**
	 * 构造函数
	 *
	 * @param by
	 * 		-
	 * @param gets
	 * 		-
	 * @param order
	 * 		排序方式
	 * @param limit
	 * 		结果限制
	 * @param alpha
	 * 		-
	 */
	public LettuceSortArgs(final byte[] by, final byte[][] gets, final Order order, final Limit limit,
						   final boolean alpha) {
		this(by, gets, order, limit);
		alpha(this, alpha);
	}

	public LettuceSortArgs by(byte[] pattern) {
		if(pattern != null){
			by(SafeEncoder.encode(pattern));
		}

		return this;
	}

	public LettuceSortArgs get(byte[] pattern) {
		if(pattern != null){
			get(SafeEncoder.encode(pattern));
		}

		return this;
	}

	/**
	 * 从 {@link SortArgument} 创建 {@link SortArgs} 实例
	 *
	 * @param sortArgument
	 *        {@link SortArgument}
	 *
	 * @return {@link LettuceSortArgs} 实例
	 */
	public static LettuceSortArgs from(final SortArgument sortArgument) {
		final LettuceSortArgs sortArgs = new LettuceSortArgs();

		if(sortArgument != null){
			Optional.ofNullable(sortArgument.getBy()).ifPresent(sortArgs::by);
			if(sortArgument.getGetPatterns() != null){
				Arrays.stream(sortArgument.getGetPatterns()).forEach(sortArgs::get);
			}
			order(sortArgs, sortArgument.getOrder());
			limit(sortArgs, sortArgument.getLimit());
			alpha(sortArgs, sortArgument.isAlpha());
		}

		return sortArgs;
	}

	private static void alpha(final LettuceSortArgs sortArgs, final Boolean alpha) {
		if(Boolean.TRUE.equals(alpha)){
			sortArgs.alpha();
		}
	}

	private static void limit(final LettuceSortArgs sortArgs, final Limit limit) {
		if(limit != null){
			sortArgs.limit((int) limit.getOffset(), (int) limit.getCount());
		}
	}

	private static void order(final LettuceSortArgs sortArgs, final Order order) {
		if(order == Order.ASC){
			sortArgs.asc();
		}else if(order == Order.DESC){
			sortArgs.desc();
		}
	}

}
