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
import com.buession.redis.core.Type;

/**
 * {@code HSCAN} 参数基类
 *
 * @param <T>
 * 		模式值类型
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public class HScanArgument<T> extends BaseScanArgument<T> {

	/**
	 * -
	 */
	private Boolean noValues;

	/**
	 * 构造函数
	 */
	public HScanArgument() {
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param pattern
	 * 		模式
	 */
	public HScanArgument(final T pattern) {
		super(pattern);
	}

	/**
	 * 构造函数
	 *
	 * @param count
	 * 		返回个数
	 */
	public HScanArgument(final int count) {
		super(count);
	}

	/**
	 * 构造函数
	 *
	 * @param noValues
	 * 		-
	 */
	public HScanArgument(final Boolean noValues) {
		this.noValues = noValues;
	}

	/**
	 * 构造函数
	 *
	 * @param pattern
	 * 		模式
	 * @param noValues
	 * 		-
	 */
	public HScanArgument(final T pattern, final Boolean noValues) {
		super(pattern);
		this.noValues = noValues;
	}

	/**
	 * 构造函数
	 *
	 * @param count
	 * 		返回个数
	 * @param noValues
	 * 		-
	 */
	public HScanArgument(final int count, final Boolean noValues) {
		super(count);
		this.noValues = noValues;
	}

	/**
	 * 构造函数
	 *
	 * @param pattern
	 * 		模式
	 * @param count
	 * 		返回个数
	 */
	public HScanArgument(final T pattern, final int count) {
		super(pattern, count);
	}

	/**
	 * 构造函数
	 *
	 * @param pattern
	 * 		模式
	 * @param count
	 * 		返回个数
	 * @param noValues
	 * 		-
	 */
	public HScanArgument(final T pattern, final int count, final Boolean noValues) {
		super(pattern, count);
		this.noValues = noValues;
	}

	public Boolean isNoValues() {
		return getNoValues();
	}

	public Boolean getNoValues() {
		return noValues;
	}

	public void noValues() {
		this.noValues = true;
	}

	public void setNoValues(final Boolean noValues) {
		this.noValues = noValues;
	}

	@Override
	public String toString() {
		final ArgumentStringBuilder builder = ArgumentStringBuilder.create();

		if(getPattern() != null){
			builder.add(Keyword.Key.MATCH, getPattern());
		}

		if(getCount() != null){
			builder.add(Keyword.Common.COUNT, getCount());
		}

		if(Boolean.TRUE.equals(noValues)){
			builder.append(Keyword.Key.NOVALUES);
		}

		return builder.build();
	}

}
