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
package com.buession.redis.core.command.args;

import com.buession.redis.core.Keyword;
import com.buession.redis.utils.ArgStringBuilder;

/**
 * {@code XCLAIM} 命令参数
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public class XClaimArgument {

	private IdleType idleType;

	private Long idleTime;

	private Integer retryCount;

	private Boolean force;

	public XClaimArgument(final IdleType idleType, final long idleTime) {
		this.idleType = idleType;
		this.idleTime = idleTime;
	}

	public XClaimArgument(final IdleType idleType, final long idleTime, final int retryCount) {
		this(idleType, idleTime);
		this.retryCount = retryCount;
	}

	public XClaimArgument(final IdleType idleType, final long idleTime, final boolean force) {
		this(idleType, idleTime);
		this.force = force;
	}

	public XClaimArgument(final IdleType idleType, final long idleTime, final int retryCount, final boolean force) {
		this(idleType, idleTime, retryCount);
		this.force = force;
	}

	public XClaimArgument(final int retryCount) {
		this.retryCount = retryCount;
	}

	public XClaimArgument(final boolean force) {
		this.force = force;
	}

	public XClaimArgument idelTime(final long idleTime) {
		this.idleType = IdleType.IDLE;
		this.idleTime = idleTime;
		return this;
	}

	public XClaimArgument unixTime(final long unixTime) {
		this.idleType = IdleType.UNIX_TIME;
		this.idleTime = unixTime;
		return this;
	}

	public IdleType getIdleType() {
		return idleType;
	}

	public XClaimArgument setIdleType(final IdleType idleType) {
		this.idleType = idleType;
		return this;
	}

	public Long getIdleTime() {
		return idleTime;
	}

	public XClaimArgument setIdleTime(final long idleTime) {
		this.idleTime = idleTime;
		return this;
	}

	public Integer getRetryCount() {
		return retryCount;
	}

	public XClaimArgument setRetryCount(final Integer retryCount) {
		this.retryCount = retryCount;
		return this;
	}

	public Boolean isForce() {
		return getForce();
	}

	public Boolean getForce() {
		return force;
	}

	public XClaimArgument force() {
		return setForce(true);
	}

	public XClaimArgument setForce(final boolean force) {
		this.force = force;
		return this;
	}

	@Override
	public String toString() {
		final ArgStringBuilder builder = ArgStringBuilder.create();

		if(getIdleType() == IdleType.IDLE){
			builder.add("IDLE", getIdleTime());
		}else if(getIdleType() == IdleType.UNIX_TIME){
			builder.add("TIME", getIdleTime());
		}

		builder.add("RETRYCOUNT", getRetryCount());

		if(Boolean.TRUE.equals(getForce())){
			builder.append(Keyword.Common.FORCE);
		}

		return builder.build();
	}

	/**
	 * 设置过期时间方式
	 */
	public enum IdleType {

		IDLE,

		UNIX_TIME

	}

}
