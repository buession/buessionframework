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
package com.buession.core.collect;

import com.buession.core.utils.StringUtils;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * List 工具类
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public class Lists {

	public final static String DEFAULT_GLUE = ", ";

	/**
	 * 将 O 型 {@link List} 拼接成字符串
	 *
	 * @param data
	 * 		需要拼接的 {@link List}
	 * @param <O>
	 *        {@link List} 元素类型
	 *
	 * @return 拼接后的字符串
	 */
	public static <O> String toString(final List<O> data) {
		return StringUtils.join(data, DEFAULT_GLUE);
	}

	/**
	 * 将 O 型 {@link List} 拼接成字符串
	 *
	 * @param data
	 * 		需要拼接的 {@link List}
	 * @param glue
	 * 		拼接字符串
	 * @param <O>
	 *        {@link List} 元素类型
	 *
	 * @return 拼接后的字符串
	 */
	public static <O> String toString(final List<O> data, final String glue) {
		return StringUtils.join(data, glue);
	}

	/**
	 * 将 O 型 {@link List} 转换成 {@link Set}
	 *
	 * @param data
	 * 		待转换的 {@link List}
	 * @param <O>
	 *        {@link List} 元素类型
	 *
	 * @return 当 data 为 null 时，返回 null；否则，返回 O 类型的 {@link Set}
	 */
	public static <O> Set<O> toSet(final List<O> data) {
		if(data == null){
			return null;
		}else if(data instanceof LinkedList){
			return new LinkedHashSet<>(data);
		}else{
			return new HashSet<>(data);
		}
	}

	/**
	 * {@link List} 流式操作收集
	 *
	 * @param data
	 * 		待操作的 {@link List}
	 * @param mapper
	 * 		A non-interfering, stateless function to apply to each element
	 * @param <S>
	 *        {@link List} 元素类型
	 * @param <T>
	 * 		返回的 {@link List} 元素类型
	 *
	 * @return 新的 {@link List}
	 *
	 * @since 5.0.1
	 */
	public static <S, T> List<T> streamCollect(final List<S> data, final Function<? super S, ? extends T> mapper) {
		return data.stream().map(mapper).collect(Collectors.toList());
	}

	/**
	 * {@link List} 流式操作收集
	 *
	 * @param data
	 * 		待操作的 {@link List}
	 * @param mapper
	 * 		A non-interfering, stateless function to apply to each element
	 * @param collector
	 * 		A {@code Collector} describing the kind of result that the operation should produce
	 * @param <S>
	 *        {@link List} 元素类型
	 * @param <T>
	 * 		返回的 {@link List} 元素类型
	 *
	 * @return 新的 {@link List}
	 *
	 * @since 5.0.1
	 */
	public static <S, T> List<T> streamCollect(final List<S> data, final Function<? super S, ? extends T> mapper,
											   final Collector<T, ?, List<T>> collector) {
		return data.stream().map(mapper).collect(collector);
	}

}
