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

import com.buession.lang.Order;
import com.buession.redis.utils.ArgStringBuilder;
import com.buession.redis.utils.SafeEncoder;

/**
 * {@code SORT} 命令参数
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public class SortArgument {

	private String by;

	/**
	 * 排序方式
	 */
	private Order order;

	private String[] getPatterns;

	private Boolean alpha;

	/**
	 * 构造函数
	 */
	public SortArgument() {
	}

	/**
	 * 构造函数
	 *
	 * @param by
	 * 		-
	 */
	public SortArgument(final String by) {
		this.by = by;
	}

	/**
	 * 构造函数
	 *
	 * @param by
	 * 		-
	 */
	public SortArgument(final byte[] by) {
		setBy(by);
	}

	/**
	 * 构造函数
	 *
	 * @param by
	 * 		-
	 * @param gets
	 * 		-
	 */
	public SortArgument(final String by, final String[] gets) {
		this(by);
		this.getPatterns = gets;
	}

	/**
	 * 构造函数
	 *
	 * @param by
	 * 		-
	 * @param gets
	 * 		-
	 */
	public SortArgument(final byte[] by, final byte[][] gets) {
		this(by);
		setGetPatterns(gets);
	}

	/**
	 * 构造函数
	 *
	 * @param by
	 * 		-
	 * @param order
	 * 		排序方式
	 */
	public SortArgument(final String by, final Order order) {
		this(by);
		this.order = order;
	}

	/**
	 * 构造函数
	 *
	 * @param by
	 * 		-
	 * @param order
	 * 		排序方式
	 */
	public SortArgument(final byte[] by, final Order order) {
		this(by);
		this.order = order;
	}

	/**
	 * 构造函数
	 *
	 * @param by
	 * 		-
	 * @param gets
	 * 		-
	 * @param order
	 * 		排序方式
	 */
	public SortArgument(final String by, final String[] gets, final Order order) {
		this(by, gets);
		this.order = order;
	}

	/**
	 * 构造函数
	 *
	 * @param by
	 * 		-
	 * @param gets
	 * 		-
	 * @param order
	 * 		排序方式
	 */
	public SortArgument(final byte[] by, final byte[][] gets, final Order order) {
		this(by, gets);
		this.order = order;
	}

	public String getBy() {
		return by;
	}

	public byte[] getByAsBytes() {
		return SafeEncoder.encode(by);
	}

	public SortArgument setBy(final String by) {
		this.by = by;
		return this;
	}

	public SortArgument setBy(final byte[] by) {
		this.by = SafeEncoder.encode(by);
		return this;
	}

	/**
	 * 获取排序方式
	 *
	 * @return 排序方式
	 */
	public Order getOrder() {
		return order;
	}

	/**
	 * 设置排序方式
	 *
	 * @param order
	 * 		排序方式
	 */
	public SortArgument setOrder(Order order) {
		this.order = order;
		return this;
	}

	public String[] getGetPatterns() {
		return getPatterns;
	}

	public byte[][] getGetPatternsAsBytes() {
		return SafeEncoder.encode(getPatterns);
	}

	public SortArgument setGetPatterns(String[] getPatterns) {
		this.getPatterns = getPatterns;
		return this;
	}

	public SortArgument setGetPatterns(byte[][] getPatterns) {
		return setGetPatterns(SafeEncoder.encode(getPatterns));
	}

	public Boolean isAlpha() {
		return getAlpha();
	}

	public Boolean getAlpha() {
		return alpha;
	}

	public SortArgument alpha() {
		return setAlpha(true);
	}

	public SortArgument setAlpha(boolean alpha) {
		this.alpha = alpha;
		return this;
	}

	@Override
	public String toString() {
		final ArgStringBuilder builder = ArgStringBuilder.create().add("BY", getBy());

		if(getGetPatterns() != null){
			for(final String pattern : getGetPatterns()){
				builder.add("GET", pattern);
			}
		}

		builder.append(getOrder());

		if(Boolean.TRUE.equals(getAlpha())){
			builder.append("ALPHA");
		}

		return builder.build();
	}

}
