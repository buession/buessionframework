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
package com.buession.redis.core.command.args;

import com.buession.redis.core.Keyword;
import com.buession.redis.utils.ArgStringBuilder;

/**
 * {@code RESTORE} 命令参数
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public class RestoreArgument {

	/**
	 * 是否替换已存在 key
	 */
	private Boolean replace;

	/**
	 * If the ABSTTL modifier was used,
	 * ttl should represent an absolute Unix timestamp (in milliseconds) in which the key will expire
	 */
	private Boolean absTtl;

	private Long idleTime;

	private Long frequency;

	/**
	 * 构造函数
	 */
	public RestoreArgument() {
	}

	/**
	 * 构造函数
	 *
	 * @param replace
	 * 		是否替换已存在 key
	 */
	public RestoreArgument(final Boolean replace) {
		this.replace = replace;
	}

	/**
	 * 构造函数
	 *
	 * @param replace
	 * 		是否替换已存在 key
	 * @param absTtl
	 * 		-
	 */
	public RestoreArgument(final Boolean replace, final Boolean absTtl) {
		this(replace);
		this.absTtl = absTtl;
	}

	/**
	 * 构造函数
	 *
	 * @param replace
	 * 		是否替换已存在 key
	 * @param idleTime
	 * 		-
	 * @param frequency
	 * 		-
	 */
	public RestoreArgument(final Boolean replace, final Long idleTime, final Long frequency) {
		this(replace);
		this.idleTime = idleTime;
		this.frequency = frequency;
	}

	/**
	 * 构造函数
	 *
	 * @param replace
	 * 		是否替换已存在 key
	 * @param absTtl
	 * 		-
	 * @param idleTime
	 * 		-
	 * @param frequency
	 * 		-
	 */
	public RestoreArgument(final Boolean replace, final Boolean absTtl, final Long idleTime, final Long frequency) {
		this(replace, idleTime, frequency);
		this.absTtl = absTtl;
	}

	/**
	 * 获取是否替换已存在 key
	 *
	 * @return 是否替换已存在 key
	 */
	public Boolean isReplace() {
		return getReplace();
	}

	/**
	 * 获取是否替换已存在 key
	 *
	 * @return 是否替换已存在 key
	 */
	public Boolean getReplace() {
		return replace;
	}

	/**
	 * 替换已存在 key
	 */
	public RestoreArgument replace() {
		return setReplace(true);
	}

	/**
	 * 设置是否替换已存在 key
	 *
	 * @param replace
	 * 		true / false
	 */
	public RestoreArgument setReplace(final Boolean replace) {
		this.replace = replace;
		return this;
	}

	/**
	 * If the ABSTTL modifier was used,
	 * ttl should represent an absolute Unix timestamp (in milliseconds) in which the key will expire
	 *
	 * @return If the ABSTTL modifier was used,
	 * ttl should represent an absolute Unix timestamp (in milliseconds) in which the key will expire
	 */
	public Boolean isAbsTtl() {
		return getAbsTtl();
	}

	/**
	 * If the ABSTTL modifier was used,
	 * ttl should represent an absolute Unix timestamp (in milliseconds) in which the key will expire
	 *
	 * @return If the ABSTTL modifier was used,
	 * ttl should represent an absolute Unix timestamp (in milliseconds) in which the key will expire
	 */
	public Boolean getAbsTtl() {
		return absTtl;
	}

	public RestoreArgument absTtl() {
		return setAbsTtl(true);
	}

	/**
	 * f the ABSTTL modifier was used,
	 * ttl should represent an absolute Unix timestamp (in milliseconds) in which the key will expire
	 *
	 * @param absTtl
	 * 		true / false
	 */
	public RestoreArgument setAbsTtl(final Boolean absTtl) {
		this.absTtl = absTtl;
		return this;
	}

	public Long getIdleTime() {
		return idleTime;
	}

	public RestoreArgument setIdleTime(final Long idleTime) {
		this.idleTime = idleTime;
		return this;
	}

	public Long getFrequency() {
		return frequency;
	}

	public RestoreArgument setFrequency(final Long frequency) {
		this.frequency = frequency;
		return this;
	}

	@Override
	public String toString() {
		return ArgStringBuilder.create()
				.append(getReplace() ? Keyword.Common.REPLACE : null)
				.append(getAbsTtl() ? "ABSTTL" : null)
				.add("IDLETIME", getIdleTime())
				.add("FREQ", getFrequency())
				.build();
	}

}
