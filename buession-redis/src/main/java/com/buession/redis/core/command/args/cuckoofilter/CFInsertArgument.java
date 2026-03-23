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
package com.buession.redis.core.command.args.cuckoofilter;

import com.buession.redis.utils.ArgStringBuilder;

/**
 * CF.INSERT 参数
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public class CFInsertArgument {

	/**
	 * 初始容量
	 */
	private Long capacity;

	/**
	 * 是否当 key 不存在时不自动创建
	 */
	private Boolean noCreate;

	/**
	 * 构造函数
	 */
	public CFInsertArgument() {
	}

	/**
	 * 构造函数
	 *
	 * @param capacity
	 * 		初始容量
	 */
	public CFInsertArgument(final long capacity) {
		this.capacity = capacity;
	}

	/**
	 * 构造函数
	 *
	 * @param noCreate
	 * 		是否当 key 不存在时不自动创建
	 */
	public CFInsertArgument(final boolean noCreate) {
		this.noCreate = noCreate;
	}

	/**
	 * 构造函数
	 *
	 * @param capacity
	 * 		初始容量
	 * @param noCreate
	 * 		是否当 key 不存在时不自动创建
	 */
	public CFInsertArgument(final long capacity, final boolean noCreate) {
		this(capacity);
		this.noCreate = noCreate;
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
	 * @return {@link CFInsertArgument}
	 */
	public CFInsertArgument capacity(long capacity) {
		this.capacity = capacity;
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
	 * @return {@link CFInsertArgument}
	 */
	public CFInsertArgument noCreate() {
		return setNoCreate(true);
	}

	/**
	 * 设置当 key 不存在时不自动创建
	 *
	 * @param noCreate
	 * 		是否当 key 不存在时不自动创建
	 *
	 * @return {@link CFInsertArgument}
	 */
	public CFInsertArgument setNoCreate(boolean noCreate) {
		this.noCreate = noCreate;
		return this;
	}

	@Override
	public String toString() {
		return ArgStringBuilder.create()
				.add("CAPACITY", getCapacity())
				.append(Boolean.TRUE.equals(getNoCreate()) ? "NOCREATE" : null)
				.build();
	}

}
