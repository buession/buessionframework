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
 * | Copyright @ 2013-2026 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.core.internal.jedis.args;

import com.buession.core.utils.NumberUtils;
import com.buession.redis.core.command.args.sortedset.ZRangeType;
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
	 * @param type
	 *        {@link ZRangeType}
	 * @param min
	 * 		最小值
	 * @param max
	 * 		最大值
	 */
	public JedisZRangeParams(final ZRangeType type, final long min, final long max) {
		super(byKeyword(type), NumberUtils.long2bytes(min), NumberUtils.long2bytes(max));
	}

	/**
	 * 构造函数
	 *
	 * @param type
	 *        {@link ZRangeType}
	 * @param min
	 * 		最小值
	 * @param max
	 * 		最大值
	 * @param rev
	 * 		REV
	 */
	public JedisZRangeParams(final ZRangeType type, final long min, final long max, final boolean rev) {
		this(type, min, max);
		if(rev){
			rev();
		}
	}

	/**
	 * 构造函数
	 *
	 * @param type
	 *        {@link ZRangeType}
	 * @param min
	 * 		最小值
	 * @param max
	 * 		最大值
	 * @param offset
	 * 		偏移量
	 * @param count
	 * 		返回数量
	 */
	public JedisZRangeParams(final ZRangeType type, final long min, final long max, final int offset, final int count) {
		this(type, min, max);
		limit(offset, count);
	}

	/**
	 * 构造函数
	 *
	 * @param type
	 *        {@link ZRangeType}
	 * @param min
	 * 		最小值
	 * @param max
	 * 		最大值
	 * @param rev
	 * 		REV
	 * @param offset
	 * 		偏移量
	 * @param count
	 * 		返回数量
	 */
	public JedisZRangeParams(final ZRangeType type, final long min, final long max, final boolean rev, final int offset,
							 final int count) {
		this(type, min, max, offset, count);
		if(rev){
			rev();
		}
	}

	/**
	 * 构造函数
	 *
	 * @param min
	 * 		最小值
	 * @param max
	 * 		最大值
	 */
	public JedisZRangeParams(final long min, final long max) {
		super(null, NumberUtils.long2bytes(min), NumberUtils.long2bytes(max));
	}

	/**
	 * 构造函数
	 *
	 * @param min
	 * 		最小值
	 * @param max
	 * 		最大值
	 * @param rev
	 * 		REV
	 */
	public JedisZRangeParams(final long min, final long max, final boolean rev) {
		this(null, min, max, rev);
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
	public JedisZRangeParams(final long min, final long max, final int offset, final int count) {
		this(null, min, max, offset, count);
	}

	/**
	 * 构造函数
	 *
	 * @param min
	 * 		最小值
	 * @param max
	 * 		最大值
	 * @param rev
	 * 		REV
	 * @param offset
	 * 		偏移量
	 * @param count
	 * 		返回数量
	 */
	public JedisZRangeParams(final long min, final long max, final boolean rev, final int offset, final int count) {
		this(null, min, max, rev, offset, count);
	}

	private static Protocol.Keyword byKeyword(final ZRangeType type) {
		if(type == ZRangeType.BYLEX){
			return Protocol.Keyword.BYLEX;
		}else if(type == ZRangeType.BYSCORE){
			return Protocol.Keyword.BYSCORE;
		}

		return null;
	}

}
