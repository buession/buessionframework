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
 * | Copyright @ 2013-2025 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.core.command.args;

import com.buession.redis.utils.ArgStringBuilder;

/**
 * {@code XREADGROUP} 命令参数
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public class XReadGroupArgument {

	/**
	 * 阻塞时间（单位：毫秒）
	 */
	private Long block;

	private Long claim;

	private Boolean noAck;

	/**
	 * 构造函数
	 */
	public XReadGroupArgument() {
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param block
	 * 		阻塞时间（单位：毫秒）
	 */
	public XReadGroupArgument(final long block) {
		super();
		this.block = block;
	}

	/**
	 * 构造函数
	 *
	 * @param block
	 * 		阻塞时间（单位：毫秒）
	 * @param noAck
	 * 		-
	 */
	public XReadGroupArgument(final long block, final boolean noAck) {
		this(block);
		setNoAck(noAck);
	}

	/**
	 * 构造函数
	 *
	 * @param block
	 * 		阻塞时间（单位：毫秒）
	 * @param claim
	 * 		-
	 */
	public XReadGroupArgument(final long block, final long claim) {
		this(block);
		this.claim = claim;
	}

	/**
	 * 构造函数
	 *
	 * @param block
	 * 		阻塞时间（单位：毫秒）
	 * @param noAck
	 * 		-
	 */
	public XReadGroupArgument(final long block, final long claim, final boolean noAck) {
		this(block, claim);
		setNoAck(noAck);
	}

	/**
	 * 构造函数
	 *
	 * @param noAck
	 * 		-
	 */
	public XReadGroupArgument(final boolean noAck) {
		setNoAck(noAck);
	}

	/**
	 * 返回阻塞时间（单位：毫秒）
	 *
	 * @return 阻塞时间
	 */
	public Long getBlock() {
		return block;
	}

	/**
	 * 设置阻塞时间
	 *
	 * @param block
	 * 		阻塞时间（单位：毫秒）
	 */
	public XReadGroupArgument setBlock(long block) {
		this.block = block;
		return this;
	}

	public Long getClaim() {
		return claim;
	}

	public XReadGroupArgument setClaim(long claim) {
		this.claim = claim;
		return this;
	}

	public Boolean isNoAck() {
		return getNoAck();
	}

	public Boolean getNoAck() {
		return noAck;
	}

	public XReadGroupArgument noAck() {
		return setNoAck(true);
	}

	public XReadGroupArgument setNoAck(boolean noAck) {
		this.noAck = noAck;
		return this;
	}

	@Override
	public String toString() {
		return ArgStringBuilder.create()
				.add("BLOCK", getBlock())
				.add("CLAIM", getClaim())
				.append(Boolean.TRUE.equals(getNoAck()) ? "NOACK" : null)
				.build();
	}

}
