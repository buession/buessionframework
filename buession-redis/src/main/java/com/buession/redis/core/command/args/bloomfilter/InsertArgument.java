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
package com.buession.redis.core.command.args.bloomfilter;

import com.buession.redis.core.command.args.Argument;
import com.buession.redis.utils.ArgStringBuilder;

/**
 * BF.INSERT 参数
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public class InsertArgument implements Argument {

	/**
	 * 初始容量
	 */
	private Long capacity;

	/**
	 * 期望的误判率
	 */
	private Double errorRate;

	/**
	 * 扩容倍数
	 */
	private Integer expansion;

	/**
	 * 是否当 key 不存在时不自动创建
	 */
	private Boolean noCreate;

	/**
	 * 是否禁用自动扩容
	 */
	private Boolean nonScaling;

	/**
	 * 构造函数
	 */
	public InsertArgument() {
	}

	/**
	 * 构造函数
	 *
	 * @param capacity
	 * 		初始容量
	 */
	public InsertArgument(final long capacity) {
		this.capacity = capacity;
	}

	/**
	 * 构造函数
	 *
	 * @param capacity
	 * 		初始容量
	 * @param errorRate
	 * 		期望的误判率
	 */
	public InsertArgument(final long capacity, final double errorRate) {
		this(capacity);
		this.errorRate = errorRate;
	}

	/**
	 * 构造函数
	 *
	 * @param capacity
	 * 		初始容量
	 * @param errorRate
	 * 		期望的误判率
	 * @param expansion
	 * 		扩容倍数
	 */
	public InsertArgument(final long capacity, final double errorRate, final int expansion) {
		this(capacity, errorRate);
		this.expansion = expansion;
	}

	/**
	 * 构造函数
	 *
	 * @param capacity
	 * 		初始容量
	 * @param errorRate
	 * 		期望的误判率
	 * @param noCreate
	 * 		是否当 key 不存在时不自动创建
	 * @param nonScaling
	 * 		是否禁用自动扩容
	 */
	public InsertArgument(final long capacity, final double errorRate, final boolean noCreate,
	                      final boolean nonScaling) {
		this(capacity, errorRate);
		this.noCreate = noCreate;
		this.nonScaling = nonScaling;
	}

	/**
	 * 构造函数
	 *
	 * @param capacity
	 * 		初始容量
	 * @param errorRate
	 * 		期望的误判率
	 * @param expansion
	 * 		扩容倍数
	 * @param noCreate
	 * 		是否当 key 不存在时不自动创建
	 * @param nonScaling
	 * 		是否禁用自动扩容
	 */
	public InsertArgument(final long capacity, final double errorRate, final int expansion, final boolean noCreate,
	                      final boolean nonScaling) {
		this(capacity, errorRate, expansion);
		this.noCreate = noCreate;
		this.nonScaling = nonScaling;
	}

	/**
	 * 构造函数
	 *
	 * @param capacity
	 * 		初始容量
	 * @param expansion
	 * 		扩容倍数
	 */
	public InsertArgument(final long capacity, final int expansion) {
		this(capacity);
		this.expansion = expansion;
	}

	/**
	 * 构造函数
	 *
	 * @param capacity
	 * 		初始容量
	 * @param expansion
	 * 		扩容倍数
	 * @param noCreate
	 * 		是否当 key 不存在时不自动创建
	 * @param nonScaling
	 * 		是否禁用自动扩容
	 */
	public InsertArgument(final long capacity, final int expansion, final boolean noCreate,
	                      final boolean nonScaling) {
		this(capacity, expansion);
		this.noCreate = noCreate;
		this.nonScaling = nonScaling;
	}

	/**
	 * 构造函数
	 *
	 * @param capacity
	 * 		初始容量
	 * @param noCreate
	 * 		是否当 key 不存在时不自动创建
	 * @param nonScaling
	 * 		是否禁用自动扩容
	 */
	public InsertArgument(final long capacity, final boolean noCreate, final boolean nonScaling) {
		this(noCreate, nonScaling);
		this.capacity = capacity;
	}

	/**
	 * 构造函数
	 *
	 * @param errorRate
	 * 		期望的误判率
	 */
	public InsertArgument(final double errorRate) {
		this.errorRate = errorRate;
	}

	/**
	 * 构造函数
	 *
	 * @param errorRate
	 * 		期望的误判率
	 * @param expansion
	 * 		扩容倍数
	 */
	public InsertArgument(final double errorRate, final int expansion) {
		this(errorRate);
		this.expansion = expansion;
	}

	/**
	 * 构造函数
	 *
	 * @param errorRate
	 * 		期望的误判率
	 * @param noCreate
	 * 		是否当 key 不存在时不自动创建
	 * @param nonScaling
	 * 		是否禁用自动扩容
	 */
	public InsertArgument(final double errorRate, final boolean noCreate, final boolean nonScaling) {
		this(errorRate);
		this.noCreate = noCreate;
		this.nonScaling = nonScaling;
	}

	/**
	 * 构造函数
	 *
	 * @param errorRate
	 * 		期望的误判率
	 * @param expansion
	 * 		扩容倍数
	 * @param noCreate
	 * 		是否当 key 不存在时不自动创建
	 * @param nonScaling
	 * 		是否禁用自动扩容
	 */
	public InsertArgument(final double errorRate, final int expansion, final boolean noCreate,
	                      final boolean nonScaling) {
		this(errorRate, expansion);
		this.noCreate = noCreate;
		this.nonScaling = nonScaling;
	}

	/**
	 * 构造函数
	 *
	 * @param expansion
	 * 		扩容倍数
	 */
	public InsertArgument(final int expansion) {
		this.expansion = expansion;
	}

	/**
	 * 构造函数
	 *
	 * @param expansion
	 * 		扩容倍数
	 * @param noCreate
	 * 		是否当 key 不存在时不自动创建
	 * @param nonScaling
	 * 		是否禁用自动扩容
	 */
	public InsertArgument(final int expansion, final boolean noCreate, final boolean nonScaling) {
		this(noCreate, nonScaling);
		this.expansion = expansion;
	}

	/**
	 * 构造函数
	 *
	 * @param noCreate
	 * 		是否当 key 不存在时不自动创建
	 * @param nonScaling
	 * 		是否禁用自动扩容
	 */
	public InsertArgument(final boolean noCreate, final boolean nonScaling) {
		this.noCreate = noCreate;
		this.nonScaling = nonScaling;
	}

	/**
	 * 获取初始容量
	 *
	 * @return 初始容量
	 */
	public Long getCapacity() {
		return capacity;
	}

	/**
	 * 设置初始容量
	 *
	 * @param capacity
	 * 		初始容量
	 *
	 * @return {@link InsertArgument}
	 */
	public InsertArgument setSapacity(long capacity) {
		this.capacity = capacity;
		return this;
	}

	/**
	 * 获取期望的误判率
	 *
	 * @return 期望的误判率
	 */
	public Double getErrorRate() {
		return errorRate;
	}

	/**
	 * 设置期望的误判率
	 *
	 * @param errorRate
	 * 		期望的误判率
	 *
	 * @return {@link InsertArgument}
	 */
	public InsertArgument setErrorRate(double errorRate) {
		this.errorRate = errorRate;
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
	 * @return {@link InsertArgument}
	 */
	public InsertArgument setExpansion(int expansion) {
		this.expansion = expansion;
		return this;
	}

	/**
	 * 获取是否当 key 不存在时不自动创建
	 *
	 * @return 是否当 key 不存在时不自动创建
	 */
	public Boolean isNoCreate() {
		return getNoCreate();
	}

	/**
	 * 获取是否当 key 不存在时不自动创建
	 *
	 * @return 是否当 key 不存在时不自动创建
	 */
	public Boolean getNoCreate() {
		return noCreate;
	}

	/**
	 * 设置当 key 不存在时不自动创建
	 *
	 * @return {@link InsertArgument}
	 */
	public InsertArgument noCreate() {
		return setNoCreate(true);
	}

	/**
	 * 设置当 key 不存在时不自动创建
	 *
	 * @param noCreate
	 * 		当 key 不存在时不自动创建
	 *
	 * @return {@link InsertArgument}
	 */
	public InsertArgument setNoCreate(boolean noCreate) {
		this.noCreate = noCreate;
		return this;
	}

	/**
	 * 获取是否禁用自动扩容
	 *
	 * @return 是否是否禁用自动扩容
	 */
	public Boolean isNonScaling() {
		return getNonScaling();
	}

	/**
	 * 获取是否禁用自动扩容
	 *
	 * @return 是否是否禁用自动扩容
	 */
	public Boolean getNonScaling() {
		return nonScaling;
	}

	/**
	 * 设置禁用自动扩容
	 *
	 * @return {@link InsertArgument}
	 */
	public InsertArgument nonScaling() {
		return setNonScaling(true);
	}

	/**
	 * 设置禁用自动扩容
	 *
	 * @param nonScaling
	 * 		是否禁用自动扩容
	 *
	 * @return {@link InsertArgument}
	 */
	public InsertArgument setNonScaling(boolean nonScaling) {
		this.nonScaling = nonScaling;
		return this;
	}

	@Override
	public String toString() {
		return ArgStringBuilder.create()
				.add("CAPACITY", getCapacity())
				.add("ERROR", getErrorRate())
				.append(Boolean.TRUE.equals(getNoCreate()) ? "NOCREATE" : null)
				.append(Boolean.TRUE.equals(getNonScaling()) ? "NONSCALING" : null)
				.build();
	}

}
