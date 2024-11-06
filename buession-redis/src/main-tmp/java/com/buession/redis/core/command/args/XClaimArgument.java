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

	public XClaimArgument(final IdleType idleType, final Long idleTime) {
		this.idleType = idleType;
		this.idleTime = idleTime;
	}

	public XClaimArgument(final Integer retryCount) {
		this.retryCount = retryCount;
	}

	public XClaimArgument(final Boolean force) {
		this.force = force;
	}

	public XClaimArgument(final IdleType idleType, final Long idleTime, final Integer retryCount) {
		this(idleType, idleTime);
		this.retryCount = retryCount;
	}

	public XClaimArgument(final IdleType idleType, final Long idleTime, final Boolean force) {
		this(idleType, idleTime);
		this.force = force;
	}

	public XClaimArgument(final IdleType idleType, final Long idleTime, final Integer retryCount, final Boolean force) {
		this(idleType, idleTime, retryCount);
		this.force = force;
	}

	public void idelTime(final Long idleTime) {
		this.idleType = IdleType.IDLE;
		this.idleTime = idleTime;
	}

	public void unixTime(final Long unixTime) {
		this.idleType = IdleType.UNIX_TIME;
		this.idleTime = unixTime;
	}

	public IdleType getIdleType() {
		return idleType;
	}

	public void setIdleType(final IdleType idleType) {
		this.idleType = idleType;
	}

	public Long getIdleTime() {
		return idleTime;
	}

	public void setIdleTime(final Long idleTime) {
		this.idleTime = idleTime;
	}

	public Integer getRetryCount() {
		return retryCount;
	}

	public void setRetryCount(final Integer retryCount) {
		this.retryCount = retryCount;
	}

	public Boolean isForce() {
		return getForce();
	}

	public Boolean getForce() {
		return force;
	}

	public void force() {
		this.force = true;
	}

	public void setForce(final Boolean force) {
		this.force = force;
	}

	@Override
	public String toString() {
		final ArgumentStringBuilder builder = ArgumentStringBuilder.create();

		if(idleType != null){
			if(idleType == IdleType.IDLE){
				builder.add("IDLE", idleTime);
			}else if(idleType == IdleType.UNIX_TIME){
				builder.add("TIME", idleTime);
			}
		}

		if(retryCount != null){
			builder.add("RETRYCOUNT", retryCount);
		}

		if(Boolean.TRUE.equals(force)){
			builder.append("FORCE");
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
