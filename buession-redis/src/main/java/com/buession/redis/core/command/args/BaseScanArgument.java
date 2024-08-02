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
 * {@code SCAN}、{@code SSCAN}、{@code HSCAN}、{@code ZSCAN} 参数基类
 *
 * @param <T>
 * 		模式值类型
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
abstract class BaseScanArgument<T> {

	/**
	 * 模式
	 */
	private T pattern;

	/**
	 * 返回个数
	 */
	private Integer count;

	/**
	 * 构造函数
	 */
	public BaseScanArgument() {
	}

	/**
	 * 构造函数
	 *
	 * @param pattern
	 * 		模式
	 */
	public BaseScanArgument(final T pattern) {
		this.pattern = pattern;
	}

	/**
	 * 构造函数
	 *
	 * @param count
	 * 		返回个数
	 */
	public BaseScanArgument(final int count) {
		this.count = count;
	}

	/**
	 * 构造函数
	 *
	 * @param pattern
	 * 		模式
	 * @param count
	 * 		返回个数
	 */
	public BaseScanArgument(final T pattern, final int count) {
		this(pattern);
		this.count = count;
	}

	/**
	 * 返回模式
	 *
	 * @return 模式
	 */
	public T getPattern() {
		return pattern;
	}

	/**
	 * 设置模式
	 *
	 * @param pattern
	 * 		模式
	 */
	public void setPattern(final T pattern) {
		this.pattern = pattern;
	}

	/**
	 * 返回个数
	 *
	 * @return 返回个数
	 */
	public Integer getCount() {
		return count;
	}

	/**
	 * 设置返回个数
	 *
	 * @param count
	 * 		返回个数
	 */
	public void setCount(final Integer count) {
		this.count = count;
	}

}
