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
package com.buession.redis.core.internal.lettuce.args;

import com.buession.lang.Order;
import com.buession.redis.core.command.args.key.SortArgument;
import io.lettuce.core.SortArgs;

import java.util.Optional;

/**
 * Lettuce {@link SortArgs} 扩展
 *
 * @author Yong.Teng
 * @since 4.0.0
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
	 * @param sortArgument
	 *        {@link SortArgument}
	 */
	public LettuceSortArgs(final SortArgument sortArgument) {
		super();

		if(sortArgument != null){
			Optional.ofNullable(sortArgument.getBy()).ifPresent(this::by);
			if(sortArgument.getOrder() == Order.ASC){
				asc();
			}else if(sortArgument.getOrder() == Order.DESC){
				desc();
			}

			if(sortArgument.getGetPatterns() != null){
				for(String pattern : sortArgument.getGetPatterns()){
					get(pattern);
				}
			}

			if(Boolean.TRUE.equals(sortArgument.getAlpha())){
				sortArgument.alpha();
			}
		}
	}

	/**
	 * 构造函数
	 *
	 * @param sortArgument
	 *        {@link SortArgument}
	 * @param limit
	 * 		偏移量
	 * @param count
	 * 		返回数量
	 */
	public LettuceSortArgs(final SortArgument sortArgument, final int limit, final int count) {
		this(sortArgument);
		limit(limit, count);
	}

	/**
	 * 构造函数
	 * <p>
	 * 偏移量
	 *
	 * @param count
	 * 		返回数量
	 */
	public LettuceSortArgs(final int limit, final int count) {
		super();
		limit(limit, count);
	}

}
