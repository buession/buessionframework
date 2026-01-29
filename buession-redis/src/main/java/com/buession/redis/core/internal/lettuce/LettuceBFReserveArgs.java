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
package com.buession.redis.core.internal.lettuce;

import com.buession.redis.utils.ArgStringBuilder;

import java.util.Objects;

/**
 * Lettuce BFReserveArgs 扩展
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public final class LettuceBFReserveArgs {

	private Integer expansion;

	private boolean nonScaling = false;

	/**
	 * 构造函数
	 */
	public LettuceBFReserveArgs() {
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param expansion
	 * 		-
	 */
	public LettuceBFReserveArgs(final Integer expansion) {
		super();
		this.expansion = expansion;
	}

	/**
	 * 构造函数
	 *
	 * @param nonScaling
	 * 		是否禁用自动扩容机制
	 */
	public LettuceBFReserveArgs(final Boolean nonScaling) {
		super();
		if(Boolean.TRUE.equals(nonScaling)){
			this.nonScaling = true;
		}
	}

	/**
	 * 构造函数
	 *
	 * @param expansion
	 * 		-
	 * @param nonScaling
	 * 		是否禁用自动扩容机制
	 */
	public LettuceBFReserveArgs(final Integer expansion, final Boolean nonScaling) {
		this(nonScaling);
		this.expansion = expansion;
	}

	@Override
	public String toString() {
		return ArgStringBuilder.create()
				.add("EXPANSION", expansion)
				.append(Objects.equals(nonScaling, true) ? "NONSCALING" : null)
				.toString();
	}

}
