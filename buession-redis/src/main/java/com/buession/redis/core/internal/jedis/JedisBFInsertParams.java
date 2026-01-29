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
package com.buession.redis.core.internal.jedis;

import com.buession.redis.core.command.BloomFilterCommands;
import redis.clients.jedis.bloom.BFInsertParams;

import java.util.Optional;

/**
 * Jedis {@link BFInsertParams} 扩展
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public final class JedisBFInsertParams extends BFInsertParams {

	/**
	 * 构造函数
	 */
	public JedisBFInsertParams() {
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param capacity
	 * 		初始容量
	 */
	public JedisBFInsertParams(final long capacity) {
		super();
		capacity(capacity);
	}

	/**
	 * 构造函数
	 *
	 * @param errorRate
	 * 		期望的误判率
	 */
	public JedisBFInsertParams(final double errorRate) {
		super();
		error(errorRate);
	}

	/**
	 * 构造函数
	 *
	 * @param expansion
	 * 		-
	 */
	public JedisBFInsertParams(final int expansion) {
		super();
		expansion(expansion);
	}

	/**
	 * 构造函数
	 *
	 * @param capacity
	 * 		初始容量
	 * @param errorRate
	 * 		期望的误判率
	 */
	public JedisBFInsertParams(final long capacity, final double errorRate) {
		this(capacity);
		error(errorRate);
	}

	/**
	 * 构造函数
	 *
	 * @param capacity
	 * 		初始容量
	 * @param expansion
	 * 		-
	 */
	public JedisBFInsertParams(final long capacity, final int expansion) {
		this(capacity);
		expansion(expansion);
	}

	/**
	 * 构造函数
	 *
	 * @param errorRate
	 * 		期望的误判率
	 * @param expansion
	 * 		-
	 */
	public JedisBFInsertParams(final double errorRate, final int expansion) {
		this(errorRate);
		expansion(expansion);
	}

	/**
	 * 构造函数
	 *
	 * @param capacity
	 * 		初始容量
	 * @param errorRate
	 * 		期望的误判率
	 * @param expansion
	 * 		-
	 */
	public JedisBFInsertParams(final long capacity, final double errorRate, final int expansion) {
		this(capacity, errorRate);
		expansion(expansion);
	}

	/**
	 * 构造函数
	 *
	 * @param noCreate
	 * 		是否当 key 不存在时不自动创建
	 * @param nonScaling
	 * 		是否禁用自动扩容
	 */
	public JedisBFInsertParams(final boolean noCreate, final boolean nonScaling) {
		super();
		if(noCreate){
			noCreate();
		}
		if(nonScaling){
			nonScaling();
		}
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
	public JedisBFInsertParams(final long capacity, final boolean noCreate, final boolean nonScaling) {
		this(noCreate, nonScaling);
		capacity(capacity);
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
	public JedisBFInsertParams(final double errorRate, final boolean noCreate, final boolean nonScaling) {
		this(noCreate, nonScaling);
		error(errorRate);
	}

	/**
	 * 构造函数
	 *
	 * @param expansion
	 * 		-
	 * @param noCreate
	 * 		是否当 key 不存在时不自动创建
	 * @param nonScaling
	 * 		是否禁用自动扩容
	 */
	public JedisBFInsertParams(final int expansion, final boolean noCreate, final boolean nonScaling) {
		this(noCreate, nonScaling);
		expansion(expansion);
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
	public JedisBFInsertParams(final long capacity, final double errorRate, final boolean noCreate,
							   final boolean nonScaling) {
		this(capacity, noCreate, nonScaling);
		error(errorRate);
	}

	/**
	 * 构造函数
	 *
	 * @param capacity
	 * 		初始容量
	 * @param expansion
	 * 		-
	 * @param noCreate
	 * 		是否当 key 不存在时不自动创建
	 * @param nonScaling
	 * 		是否禁用自动扩容
	 */
	public JedisBFInsertParams(final long capacity, final int expansion, final boolean noCreate,
							   final boolean nonScaling) {
		this(capacity, noCreate, nonScaling);
		expansion(expansion);
	}

	/**
	 * 构造函数
	 *
	 * @param errorRate
	 * 		期望的误判率
	 * @param expansion
	 * 		-
	 * @param noCreate
	 * 		是否当 key 不存在时不自动创建
	 * @param nonScaling
	 * 		是否禁用自动扩容
	 */
	public JedisBFInsertParams(final double errorRate, final int expansion, final boolean noCreate,
							   final boolean nonScaling) {
		this(errorRate, noCreate, nonScaling);
		expansion(expansion);
	}

	/**
	 * 构造函数
	 *
	 * @param capacity
	 * 		初始容量
	 * @param errorRate
	 * 		期望的误判率
	 * @param expansion
	 * 		-
	 * @param noCreate
	 * 		是否当 key 不存在时不自动创建
	 * @param nonScaling
	 * 		是否禁用自动扩容
	 */
	public JedisBFInsertParams(final long capacity, final double errorRate, final int expansion, final boolean noCreate,
							   final boolean nonScaling) {
		this(capacity, errorRate, noCreate, nonScaling);
		expansion(expansion);
	}

	public static JedisBFInsertParams from(final BloomFilterCommands.BFInsertArgument bfInsertArgument) {
		final JedisBFInsertParams bfInsertParams = new JedisBFInsertParams();

		if(bfInsertArgument != null){
			Optional.ofNullable(bfInsertArgument.getCapacity()).ifPresent(bfInsertParams::capacity);
			Optional.ofNullable(bfInsertArgument.getErrorRate()).ifPresent(bfInsertParams::error);
			Optional.ofNullable(bfInsertArgument.getExpansion()).ifPresent(bfInsertParams::expansion);

			if(bfInsertArgument.isNoCreate()){
				bfInsertParams.noCreate();
			}

			if(bfInsertArgument.isNonScaling()){
				bfInsertParams.nonScaling();
			}
		}

		return bfInsertParams;
	}

}
