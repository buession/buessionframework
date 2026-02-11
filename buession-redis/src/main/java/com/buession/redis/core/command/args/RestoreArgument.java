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

import com.buession.redis.utils.ArgStringBuilder;

/**
 *
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public class RestoreArgument {

	private Boolean replace;

	private Boolean absTtl;

	private Long idleTime;

	private Long frequency;

	/**
	 * 构造函数
	 */
	public RestoreArgument() {
	}

	public RestoreArgument(Boolean replace, Boolean absTtl, Long idleTime, Long frequency) {
		this.replace = replace;
		this.absTtl = absTtl;
		this.idleTime = idleTime;
		this.frequency = frequency;
	}

	public Boolean getReplace() {
		return replace;
	}

	public RestoreArgument setReplace(Boolean replace) {
		this.replace = replace;
		return this;
	}

	public Boolean getAbsTtl() {
		return absTtl;
	}

	public RestoreArgument setAbsTtl(Boolean absTtl) {
		this.absTtl = absTtl;
		return this;
	}

	public Long getIdleTime() {
		return idleTime;
	}

	public RestoreArgument setIdleTime(Long idleTime) {
		this.idleTime = idleTime;
		return this;
	}

	public Long getFrequency() {
		return frequency;
	}

	public RestoreArgument setFrequency(Long frequency) {
		this.frequency = frequency;
		return this;
	}

	@Override
	public String toString() {
		return ArgStringBuilder.create().append(replace ? "REPLACE" : null)
				.append(absTtl ? "ABSTTL" : null)
				.add("IDLETIME", idleTime)
				.add("FREQ", frequency)
				.build();
	}

}
