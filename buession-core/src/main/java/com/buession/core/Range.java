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
package com.buession.core;

/**
 * 范围
 *
 * @param <T>
 * 		值类型
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public class Range<T> {

	/**
	 * 起始值
	 */
	private T start;

	/**
	 * 截止值
	 */
	private T end;

	/**
	 * 构造函数
	 */
	public Range() {
	}

	/**
	 * 构造函数
	 *
	 * @param start
	 * 		起始值
	 * @param end
	 * 		截止值
	 */
	public Range(final T start, final T end) {
		this.start = start;
		this.end = end;
	}

	/**
	 * 返回起始值
	 *
	 * @return 起始值
	 */
	public T getStart() {
		return start;
	}

	/**
	 * 设置起始值
	 *
	 * @param start
	 * 		起始值
	 */
	public void setStart(T start) {
		this.start = start;
	}

	/**
	 * 返回截止值
	 *
	 * @return 截止值
	 */
	public T getEnd() {
		return end;
	}

	/**
	 * 设置截止值
	 *
	 * @param end
	 * 		截止值
	 */
	public void setEnd(T end) {
		this.end = end;
	}

	@Override
	public String toString() {
		return "[" + start + ", " + end + "]";
	}

}
