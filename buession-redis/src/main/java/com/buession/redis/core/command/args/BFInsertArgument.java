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

import java.util.Objects;

/**
 * BF.INSERT 参数
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public class BFInsertArgument {

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
	public BFInsertArgument() {
	}

	/**
	 * 构造函数
	 *
	 * @param capacity
	 * 		初始容量
	 */
	public BFInsertArgument(long capacity) {
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
	public BFInsertArgument(long capacity, double errorRate) {
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
	public BFInsertArgument(long capacity, double errorRate, int expansion) {
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
	public BFInsertArgument(long capacity, double errorRate, boolean noCreate, final boolean nonScaling) {
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
	public BFInsertArgument(long capacity, double errorRate, int expansion, boolean noCreate,
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
	public BFInsertArgument(long capacity, int expansion) {
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
	public BFInsertArgument(long capacity, int expansion, boolean noCreate, final boolean nonScaling) {
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
	public BFInsertArgument(long capacity, boolean noCreate, final boolean nonScaling) {
		this(noCreate, nonScaling);
		this.capacity = capacity;
	}

	/**
	 * 构造函数
	 *
	 * @param errorRate
	 * 		期望的误判率
	 */
	public BFInsertArgument(double errorRate) {
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
	public BFInsertArgument(double errorRate, int expansion) {
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
	public BFInsertArgument(double errorRate, boolean noCreate, final boolean nonScaling) {
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
	public BFInsertArgument(double errorRate, int expansion, boolean noCreate, final boolean nonScaling) {
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
	public BFInsertArgument(int expansion) {
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
	public BFInsertArgument(int expansion, boolean noCreate, final boolean nonScaling) {
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
	public BFInsertArgument(boolean noCreate, final boolean nonScaling) {
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
	 * @return {@link BFInsertArgument}
	 */
	public BFInsertArgument setSapacity(final long capacity) {
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
	 * @return {@link BFInsertArgument}
	 */
	public BFInsertArgument setErrorRate(final double errorRate) {
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
	 * @return {@link BFInsertArgument}
	 */
	public BFInsertArgument setExpansion(final int expansion) {
		this.expansion = expansion;
		return this;
	}

	/**
	 * 获取是否当 key 不存在时不自动创建
	 *
	 * @return 是否当 key 不存在时不自动创建
	 */
	public Boolean isNoCreate() {
		return noCreate;
	}

	/**
	 * 设置当 key 不存在时不自动创建
	 *
	 * @return {@link BFInsertArgument}
	 */
	public BFInsertArgument setNoCreate() {
		this.noCreate = true;
		return this;
	}

	/**
	 * 获取是否禁用自动扩容
	 *
	 * @return 是否是否禁用自动扩容
	 */
	public Boolean isNonScaling() {
		return nonScaling;
	}

	/**
	 * 设置禁用自动扩容
	 *
	 * @return {@link BFInsertArgument}
	 */
	public BFInsertArgument setNonScaling() {
		this.nonScaling = true;
		return this;
	}

	@Override
	public String toString() {
		return ArgStringBuilder.create().add("CAPACITY", capacity).add("ERROR", errorRate)
				.append(Objects.equals(noCreate, true) ? "NOCREATE" : null)
				.append(Objects.equals(nonScaling, true) ? "NONSCALING" : null).build();
	}

}
