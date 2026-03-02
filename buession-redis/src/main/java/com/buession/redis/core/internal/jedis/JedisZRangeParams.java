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
package com.buession.redis.core.internal.jedis;

import com.buession.core.utils.NumberUtils;
import com.buession.redis.core.command.args.ZRangeArgument;
import redis.clients.jedis.Protocol;
import redis.clients.jedis.params.ZRangeParams;

/**
 * Jedis {@link ZRangeParams} 扩展
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public final class JedisZRangeParams extends ZRangeParams {

	public JedisZRangeParams(final int min, final int max) {
		super(min, max);
	}

	public JedisZRangeParams(final long min, final long max) {
		super((int) min, (int) max);
	}

	public JedisZRangeParams(final ZRangeArgument.By by, final int min, final int max) {
		super(byKeyword(by), NumberUtils.int2bytes(min), NumberUtils.int2bytes(max));
	}

	public JedisZRangeParams(final ZRangeArgument.By by, final long min, final long max) {
		super(byKeyword(by), NumberUtils.long2bytes(min), NumberUtils.long2bytes(max));
	}

	public JedisZRangeParams(final ZRangeArgument.By by, final int min, final int max, final boolean rev) {
		this(by, min, max);
		rev(rev);
	}

	public JedisZRangeParams(final ZRangeArgument.By by, final long min, final long max, final boolean rev) {
		this(by, (int) min, (int) max, rev);
	}

	public JedisZRangeParams(final int min, final int max, final boolean rev) {
		this(min, max);
		rev(rev);
	}

	public JedisZRangeParams(final long min, final long max, final boolean rev) {
		this((int) min, (int) max, rev);
	}

	public JedisZRangeParams(final int min, final int max, final int offset, final int count) {
		this(min, max);
		limit(offset, count);
	}

	public JedisZRangeParams(final long min, final long max, final int offset, final int count) {
		this(min, max);
		limit(offset, count);
	}

	public JedisZRangeParams(final int min, final int max, final boolean rev, final int offset, final int count) {
		this(min, max, rev);
		limit(offset, count);
	}

	public JedisZRangeParams(final long min, final long max, final boolean rev, final int offset, final int count) {
		this(min, max, rev);
		limit(offset, count);
	}

	public JedisZRangeParams(final ZRangeArgument.By by, final int min, final int max, final boolean rev,
							 final int offset, final int count) {
		this(by, min, max, rev);
		limit(offset, count);
	}

	public JedisZRangeParams(final ZRangeArgument.By by, final long min, final long max, final boolean rev,
							 final int offset, final int count) {
		this(by, min, max, rev);
		limit(offset, count);
	}

	private static Protocol.Keyword byKeyword(final ZRangeArgument.By by) {
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
