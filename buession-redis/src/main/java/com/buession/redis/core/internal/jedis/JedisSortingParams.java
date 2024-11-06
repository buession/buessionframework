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

import com.buession.lang.Order;
import com.buession.redis.core.Limit;
import com.buession.redis.core.command.KeyCommands;
import redis.clients.jedis.params.SortingParams;

import java.util.Optional;

/**
 * Jedis {@link SortingParams} 扩展
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public final class JedisSortingParams extends SortingParams {

	public JedisSortingParams() {
		super();
	}

	public JedisSortingParams(final String by) {
		super();
		by(by);
	}

	public JedisSortingParams(final byte[] by) {
		super();
		by(by);
	}

	public JedisSortingParams(final String by, final String[] gets) {
		super();
		by(by);
		get(gets);
	}

	public JedisSortingParams(final byte[] by, final byte[][] gets) {
		super();
		by(by);
		get(gets);
	}

	public JedisSortingParams(final String by, final Order order) {
		this(by);
		order(this, order);
	}

	public JedisSortingParams(final byte[] by, final Order order) {
		this(by);
		order(this, order);
	}

	public JedisSortingParams(final String by, final String[] gets, final Order order) {
		this(by, gets);
		order(this, order);
	}

	public JedisSortingParams(final byte[] by, final byte[][] gets, final Order order) {
		this(by, gets);
		order(this, order);
	}

	public JedisSortingParams(final String by, final String[] gets, final Limit limit) {
		this(by, gets);
		limit(this, limit);
	}

	public JedisSortingParams(final byte[] by, final byte[][] gets, final Limit limit) {
		this(by, gets);
		limit(this, limit);
	}

	public JedisSortingParams(final String by, final String[] gets, final Order order, final Limit limit) {
		this(by, gets, order);
		limit(this, limit);
	}

	public JedisSortingParams(final byte[] by, final byte[][] gets, final Order order, final Limit limit) {
		this(by, gets, order);
		limit(this, limit);
	}

	public JedisSortingParams(final String by, final String[] gets, final Order order, final Limit limit,
							  final boolean alpha) {
		this(by, gets, order, limit);
		alpha(this, alpha);
	}

	public JedisSortingParams(final byte[] by, final byte[][] gets, final Order order, final Limit limit,
							  final boolean alpha) {
		this(by, gets, order, limit);
		alpha(this, alpha);
	}

	public static JedisSortingParams from(final KeyCommands.SortArgument sortArgument) {
		final JedisSortingParams sortingParams = new JedisSortingParams();

		if(sortArgument != null){
			Optional.ofNullable(sortArgument.getBy()).ifPresent(sortingParams::by);
			Optional.ofNullable(sortArgument.getGetPatterns()).ifPresent(sortingParams::get);
			order(sortingParams, sortArgument.getOrder());
			limit(sortingParams, sortArgument.getLimit());
			alpha(sortingParams, sortArgument.isAlpha());
		}

		return sortingParams;
	}

	private static void alpha(final JedisSortingParams sortingParams, final Boolean alpha) {
		if(Boolean.TRUE.equals(alpha)){
			sortingParams.alpha();
		}
	}

	private static void limit(final JedisSortingParams sortingParams, final Limit limit) {
		if(limit != null){
			sortingParams.limit((int) limit.getOffset(), (int) limit.getCount());
		}
	}

	private static void order(final JedisSortingParams sortingParams, final Order order) {
		if(order == Order.ASC){
			sortingParams.asc();
		}else if(order == Order.DESC){
			sortingParams.desc();
		}
	}

}
