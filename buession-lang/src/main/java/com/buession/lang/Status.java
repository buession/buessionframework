/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license
 * agreements. See the NOTICE file distributed with this work for additional information regarding
 * copyright ownership. The ASF licenses this file to you under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with the License. You may obtain
 * a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 *
 * =================================================================================================
 *
 * This software consists of voluntary contributions made by many individuals on behalf of the
 * Apache Software Foundation. For more information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 * +------------------------------------------------------------------------------------------------+
 * | License: http://www.apache.org/licenses/LICENSE-2.0.txt 										|
 * | Author: Yong.Teng <webmaster@buession.com> 													|
 * | Copyright @ 2013-2024 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.lang;

/**
 * 状态
 *
 * @author Yong.Teng
 */
public enum Status {

	SUCCESS,

	FAILURE;

	/**
	 * {@link Boolean} 转换为 {@link Status}
	 *
	 * @param v
	 *        {@link Boolean} 值
	 *
	 * @return 当 {@link Boolean} 为 true 时，返回 {@link #SUCCESS}；否则，返回 {@link #FAILURE}
	 *
	 * @since 2.3.3
	 */
	public static Status valueOf(final boolean v) {
		return v ? Status.SUCCESS : Status.FAILURE;
	}

	/**
	 * {@link Short} 转换为 {@link Status}
	 *
	 * @param v
	 *        {@link Short} 值
	 *
	 * @return 当 {@link Short} 为 0 时，返回 {@link #FAILURE}；否则，返回 {@link #SUCCESS}
	 *
	 * @since 2.3.3
	 */
	public static Status valueOf(final short v) {
		return (short) 0 == v ? Status.FAILURE : Status.SUCCESS;
	}

	/**
	 * {@link Integer} 转换为 {@link Status}
	 *
	 * @param v
	 *        {@link Integer} 值
	 *
	 * @return 当 {@link Integer} 为 0 时，返回 {@link #FAILURE}；否则，返回 {@link #SUCCESS}
	 *
	 * @since 2.3.3
	 */
	public static Status valueOf(final int v) {
		return v == 0 ? Status.FAILURE : Status.SUCCESS;
	}

	/**
	 * {@link Long} 转换为 {@link Status}
	 *
	 * @param v
	 *        {@link Long} 值
	 *
	 * @return 当 {@link Long} 为 0 时，返回 {@link #FAILURE}；否则，返回 {@link #SUCCESS}
	 *
	 * @since 2.3.3
	 */
	public static Status valueOf(final long v) {
		return v == 0 ? Status.FAILURE : Status.SUCCESS;
	}

}