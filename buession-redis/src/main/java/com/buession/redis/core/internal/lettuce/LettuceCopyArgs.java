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

import io.lettuce.core.CopyArgs;

/**
 * Lettuce {@link CopyArgs} 扩展
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public final class LettuceCopyArgs extends CopyArgs {

	/**
	 * 构造函数
	 */
	public LettuceCopyArgs() {
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param destinationDb
	 * 		目标 DB
	 */
	public LettuceCopyArgs(final int destinationDb) {
		super();
		destinationDb(destinationDb);
	}

	/**
	 * 构造函数
	 *
	 * @param replace
	 * 		是否替换已存在 key
	 */
	public LettuceCopyArgs(final boolean replace) {
		super();
		replace(replace);
	}

	/**
	 * 构造函数
	 *
	 * @param destinationDb
	 * 		目标 DB
	 * @param replace
	 * 		是否替换已存在 key
	 */
	public LettuceCopyArgs(final int destinationDb, final boolean replace) {
		this(destinationDb);
		replace(replace);
	}

}
