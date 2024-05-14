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
package com.buession.core.collect;

import com.buession.core.utils.StringUtils;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * List 工具类
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public class Lists {

	public final static String DEFAULT_GLUE = ", ";

	/**
	 * 将 O 型 List 拼接成字符串
	 *
	 * @param data
	 * 		需要拼接的 List
	 * @param <O>
	 * 		List 类型
	 *
	 * @return 拼接后的字符串
	 */
	public static <O> String toString(final List<O> data) {
		return StringUtils.join(data, DEFAULT_GLUE);
	}

	/**
	 * 将 O 型 List 拼接成字符串
	 *
	 * @param data
	 * 		需要拼接的 List
	 * @param glue
	 * 		拼接字符串
	 * @param <O>
	 * 		List 类型
	 *
	 * @return 拼接后的字符串
	 */
	public static <O> String toString(final List<O> data, final String glue) {
		return StringUtils.join(data, glue);
	}

	/**
	 * 将 O 型 List 转换成 {@link Set}
	 *
	 * @param data
	 * 		待转换的 List
	 * @param <O>
	 * 		List 类型
	 *
	 * @return 当 data 为 null 时，返回 null；否则，返回 O 类型的 {@link Set}
	 */
	public static <O> Set<O> toSet(final List<O> data) {
		return data == null ? null : (data instanceof LinkedList ? new LinkedHashSet<>(data) : new HashSet<>(data));
	}

}
