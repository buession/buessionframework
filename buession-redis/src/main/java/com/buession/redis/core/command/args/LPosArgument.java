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
 * {@code LPOS} 命令参数
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public class LPosArgument {

	/**
	 * 返回第几个匹配的元素
	 */
	private Integer rank;

	/**
	 * 查找成员个数
	 */
	private Integer maxLen;

	/**
	 * 构造函数
	 */
	public LPosArgument() {
	}

	/**
	 * 构造函数
	 *
	 * @param rank
	 * 		返回第几个匹配的元素
	 */
	public LPosArgument(final Integer rank) {
		this.rank = rank;
	}

	/**
	 * 构造函数
	 *
	 * @param rank
	 * 		返回第几个匹配的元素
	 * @param maxLen
	 * 		查找成员个数
	 */
	public LPosArgument(final Integer rank, final Integer maxLen) {
		this(rank);
		this.maxLen = maxLen;
	}

	/**
	 * 返回第几个匹配的元素
	 *
	 * @return 第几个匹配的元素
	 */
	public Integer getRank() {
		return rank;
	}

	/**
	 * 设置 返回第几个匹配的元素
	 *
	 * @param rank
	 * 		返回第几个匹配的元素
	 */
	public void setRank(final Integer rank) {
		this.rank = rank;
	}

	/**
	 * 返回查找成员个数
	 *
	 * @return 查找成员个数
	 */
	public Integer getMaxLen() {
		return maxLen;
	}

	/**
	 * 设置查找成员个数
	 *
	 * @param maxLen
	 * 		查找成员个数
	 */
	public void setMaxLen(final Integer maxLen) {
		this.maxLen = maxLen;
	}

	@Override
	public String toString() {
		return ArgumentStringBuilder.create()
				.add("RANK", rank)
				.add("MAXLEN", maxLen)
				.build();
	}

}
