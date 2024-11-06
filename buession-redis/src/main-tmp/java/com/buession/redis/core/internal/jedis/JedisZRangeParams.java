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

import com.buession.redis.core.ZRangeBy;
import redis.clients.jedis.Protocol;
import redis.clients.jedis.params.ZRangeParams;

/**
 * Jedis {@link ZRangeParams} 扩展
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public final class JedisZRangeParams extends ZRangeParams {

	/**
	 * 构造函数
	 *
	 * @param min
	 * 		最小值
	 * @param max
	 * 		最大值
	 */
	public JedisZRangeParams(final long min, final long max) {
		super(min, max);
	}

	/**
	 * 构造函数
	 *
	 * @param by
	 *        {@link ZRangeBy}
	 * @param min
	 * 		最小值
	 * @param max
	 * 		最大值
	 */
	public JedisZRangeParams(final ZRangeBy by, final long min, final long max) {
		super(byKeyword(by), Long.toString(min), Long.toString(max));
	}

	/**
	 * 构造函数
	 *
	 * @param by
	 *        {@link ZRangeBy}
	 * @param min
	 * 		最小值
	 * @param max
	 * 		最大值
	 * @param rev
	 * 		-
	 */
	public JedisZRangeParams(final ZRangeBy by, final long min, final long max, final boolean rev) {
		this(by, min, max);
		rev(rev);
	}

	/**
	 * 构造函数
	 *
	 * @param by
	 *        {@link ZRangeBy}
	 * @param min
	 * 		最小值
	 * @param max
	 * 		最大值
	 * @param offset
	 * 		偏移量
	 * @param count
	 * 		返回数量
	 */
	public JedisZRangeParams(final ZRangeBy by, final long min, final long max, final long offset, final int count) {
		this(by, min, max);
		limit((int) offset, count);
	}

	/**
	 * 构造函数
	 *
	 * @param min
	 * 		最小值
	 * @param max
	 * 		最大值
	 * @param rev
	 * 		-
	 */
	public JedisZRangeParams(final long min, final long max, final boolean rev) {
		this(min, max);
		rev(rev);
	}

	/**
	 * 构造函数
	 *
	 * @param min
	 * 		最小值
	 * @param max
	 * 		最大值
	 * @param offset
	 * 		偏移量
	 * @param count
	 * 		返回数量
	 */
	public JedisZRangeParams(final long min, final long max, final long offset, final int count) {
		this(min, max);
		limit((int) offset, count);
	}

	/**
	 * 构造函数
	 *
	 * @param min
	 * 		最小值
	 * @param max
	 * 		最大值
	 * @param rev
	 * 		-
	 * @param offset
	 * 		偏移量
	 * @param count
	 * 		返回数量
	 */
	public JedisZRangeParams(final long min, final long max, final boolean rev, final long offset, final int count) {
		this(min, max, rev);
		limit((int) offset, count);
	}

	/**
	 * 构造函数
	 *
	 * @param by
	 *        {@link ZRangeBy}
	 * @param min
	 * 		最小值
	 * @param max
	 * 		最大值
	 * @param rev
	 * 		-
	 * @param offset
	 * 		偏移量
	 * @param count
	 * 		返回数量
	 */
	public JedisZRangeParams(final ZRangeBy by, final long min, final long max, final boolean rev, final long offset,
							 final int count) {
		this(by, min, max, rev);
		limit((int) offset, count);
	}

	private static Protocol.Keyword byKeyword(final ZRangeBy by) {
		if(by != null){
			switch(by){
				case BYLEX:
					return Protocol.Keyword.BYLEX;
				case BYSCORE:
					return Protocol.Keyword.BYSCORE;
				default:
					break;
			}
		}

		return null;
	}

	private void rev(boolean rev) {
		if(rev){
			rev();
		}
	}

}
