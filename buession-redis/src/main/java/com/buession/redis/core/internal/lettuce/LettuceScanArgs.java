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

import io.lettuce.core.ScanArgs;

/**
 * Lettuce {@link ScanArgs} 扩展
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public final class LettuceScanArgs extends ScanArgs {

	/**
	 * 构造函数
	 */
	public LettuceScanArgs() {
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param pattern
	 * 		匹配模式
	 */
	public LettuceScanArgs(final String pattern) {
		super();
		match(pattern);
	}

	/**
	 * 构造函数
	 *
	 * @param pattern
	 * 		匹配模式
	 */
	public LettuceScanArgs(final byte[] pattern) {
		super();
		match(pattern);
	}

	/**
	 * 构造函数
	 *
	 * @param count
	 * 		返回数量
	 */
	public LettuceScanArgs(final int count) {
		super();
		limit(count);
	}

	/**
	 * 构造函数
	 *
	 * @param pattern
	 * 		匹配模式
	 * @param count
	 * 		返回数量
	 */
	public LettuceScanArgs(final String pattern, final int count) {
		this(pattern);
		limit(count);
	}

	/**
	 * 构造函数
	 *
	 * @param pattern
	 * 		匹配模式
	 * @param count
	 * 		返回数量
	 */
	public LettuceScanArgs(final byte[] pattern, final int count) {
		this(pattern);
		limit(count);
	}

}
