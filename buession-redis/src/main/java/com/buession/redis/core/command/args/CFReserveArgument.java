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
 * CF.RESERVE 参数
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public class CFReserveArgument {

	/**
	 * 每个桶能存储的指纹数量
	 */
	private Long bucketSize;

	/**
	 * 插入时最大“踢出”尝试次数
	 */
	private Integer maxIterations;

	/**
	 * 扩容倍数
	 */
	private Integer expansion;

	/**
	 * 构造函数
	 */
	public CFReserveArgument() {
	}

	/**
	 * 构造函数
	 *
	 * @param bucketSize
	 * 		每个桶能存储的指纹数量
	 */
	public CFReserveArgument(final Long bucketSize) {
		this.bucketSize = bucketSize;
	}

	/**
	 * 构造函数
	 *
	 * @param bucketSize
	 * 		每个桶能存储的指纹数量
	 * @param maxIterations
	 * 		插入时最大“踢出”尝试次数
	 * @param expansion
	 * 		扩容倍数
	 */
	public CFReserveArgument(final Long bucketSize, final Integer maxIterations, final Integer expansion) {
		this(maxIterations, expansion);
		this.bucketSize = bucketSize;
	}

	/**
	 * 构造函数
	 *
	 * @param maxIterations
	 * 		插入时最大“踢出”尝试次数
	 * @param expansion
	 * 		扩容倍数
	 */
	public CFReserveArgument(final Integer maxIterations, final Integer expansion) {
		this.maxIterations = maxIterations;
		this.expansion = expansion;
	}

	/**
	 * 获取每个桶能存储的指纹数量
	 *
	 * @return 每个桶能存储的指纹数量
	 */
	public Long getBucketSize() {
		return bucketSize;
	}

	/**
	 * 设置每个桶能存储的指纹数量
	 *
	 * @param bucketSize
	 * 		每个桶能存储的指纹数量
	 *
	 * @return {@link CFReserveArgument}
	 */
	public CFReserveArgument setBucketSize(final Long bucketSize) {
		this.bucketSize = bucketSize;
		return this;
	}

	/**
	 * 获取插入时最大“踢出”尝试次数
	 *
	 * @return 插入时最大“踢出”尝试次数
	 */
	public Integer getMaxIterations() {
		return maxIterations;
	}

	/**
	 * 设置插入时最大“踢出”尝试次数
	 *
	 * @param maxIterations
	 * 		插入时最大“踢出”尝试次数
	 *
	 * @return {@link CFReserveArgument}
	 */
	public CFReserveArgument setMaxIterations(final Integer maxIterations) {
		this.maxIterations = maxIterations;
		return this;
	}

	/**
	 * 获取扩容倍数
	 *
	 * @return 扩容倍数
	 */
	public Integer getExpansion() {
		return expansion;
	}

	/**
	 * 设置扩容倍数
	 *
	 * @param expansion
	 * 		扩容倍数
	 *
	 * @return {@link CFReserveArgument}
	 */
	public CFReserveArgument setExpansion(final Integer expansion) {
		this.expansion = expansion;
		return this;
	}

	@Override
	public String toString() {
		return ArgStringBuilder.create()
				.add("BUCKETSIZE", getBucketSize())
				.add("MAXITERATIONS", getMaxIterations())
				.add("EXPANSION", getExpansion())
				.build();
	}

}
