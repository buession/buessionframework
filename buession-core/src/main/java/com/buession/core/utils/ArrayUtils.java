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
 * | Copyright @ 2013-2020 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.core.utils;

import com.buession.lang.Constants;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 数组工具类
 *
 * @author Yong.Teng
 */
public class ArrayUtils {

	public final static String DEFAULT_GLUE = ", ";

	public final static int INDEX_NOT_FOUND = -1;

	private ArrayUtils(){

	}

	/**
	 * 检查数组中是否存在某个值
	 *
	 * @param a
	 * 		待搜索的数组
	 * @param value
	 * 		待搜索的值
	 * @param <O>
	 * 		数组类型
	 *
	 * @return 数组中否存在该值，则返回 true；否则，返回 false
	 */
	public static <O> boolean contains(final O[] a, final Object value){
		return indexOf(a, value) != INDEX_NOT_FOUND;
	}

	/**
	 * 获取指定值在数组中第一次出现处的索引，如果此数组中没有该值，则返回 -1
	 *
	 * @param a
	 * 		待搜索的数组
	 * @param value
	 * 		待搜索的值
	 * @param <O>
	 * 		数组类型
	 *
	 * @return 指定值在数组中第一次出现处的索引，未找到则返回 -1
	 */
	public static <O> int indexOf(final O[] a, final Object value){
		return indexOf(a, value, 0);
	}

	/**
	 * 获取指定值在数组中从 startIndex 位置开始，第一次出现处的索引，如果此数组中没有该值，则返回 -1
	 *
	 * @param a
	 * 		待搜索的数组
	 * @param value
	 * 		待搜索的值
	 * @param startIndex
	 * 		开始位置
	 * @param <O>
	 * 		数组类型
	 *
	 * @return 指定值在数组中第一次出现处的索引，未找到则返回 -1
	 */
	public static <O> int indexOf(final O[] a, final Object value, int startIndex){
		if(a == null){
			return INDEX_NOT_FOUND;
		}

		if(startIndex < 0){
			startIndex = 0;
		}

		if(value == null){
			for(int i = startIndex; i < a.length; i++){
				if(a[i] == null){
					return i;
				}
			}
		}else{
			for(int i = startIndex; i < a.length; i++){
				if(value.equals(a[i])){
					return i;
				}
			}
		}

		return INDEX_NOT_FOUND;
	}

	/**
	 * 获取指定值在数组中最后一次出现处的索引，如果此数组中没有该值，则返回 -1
	 *
	 * @param a
	 * 		待搜索的数组
	 * @param value
	 * 		待搜索的值
	 * @param <O>
	 * 		数组类型
	 *
	 * @return 指定值在数组中最后一次出现处的索引，未找到则返回 -1
	 */
	public static <O> int lastIndexOf(final O[] a, final Object value){
		return lastIndexOf(a, value, 0);
	}

	/**
	 * 获取指定值在数组中从 startIndex 位置开始，最后一次出现处的索引，如果此数组中没有该值，则返回 -1
	 *
	 * @param a
	 * 		待搜索的数组
	 * @param value
	 * 		待搜索的值
	 * @param startIndex
	 * 		开始位置
	 * @param <O>
	 * 		数组类型
	 *
	 * @return 指定值在数组中最后一次出现处的索引，未找到则返回 -1
	 */
	public static <O> int lastIndexOf(final O[] a, final Object value, int startIndex){
		if(a == null){
			return INDEX_NOT_FOUND;
		}

		if(startIndex < 0){
			startIndex = 0;
		}

		if(value == null){
			for(int i = a.length - 1; i >= startIndex; i--){
				if(a[i] == null){
					return i;
				}
			}
		}else{
			for(int i = a.length - 1; i >= startIndex; i--){
				if(value.equals(a[i])){
					return i;
				}
			}
		}

		return INDEX_NOT_FOUND;
	}

	/**
	 * 将 O 型数组拼接成字符串
	 *
	 * @param a
	 * 		需要拼接的数组
	 * @param <O>
	 * 		类
	 *
	 * @return 拼接后的字符串
	 */
	public static <O> String toString(final O[] a){
		return toString(a, DEFAULT_GLUE);
	}

	/**
	 * 将 O 型数组拼接成字符串
	 *
	 * @param a
	 * 		需要拼接的数组
	 * @param glue
	 * 		拼接字符串
	 * @param <O>
	 * 		类
	 *
	 * @return 拼接后的字符串
	 */
	public static <O> String toString(final O[] a, final String glue){
		if(a == null){
			return null;
		}

		return a.length == 0 ? Constants.EMPTY_STRING : Arrays.asList(a).stream().map(v->v.toString()).collect
				(Collectors.joining(glue));
	}

	/**
	 * 将 O 型 List 拼接成字符串
	 *
	 * @param data
	 * 		需要拼接的 List
	 * @param <O>
	 * 		类
	 *
	 * @return 拼接后的字符串
	 */
	public static <O> String toString(final List<O> data){
		return toString(data, DEFAULT_GLUE);
	}

	/**
	 * 将 O 型 List 拼接成字符串
	 *
	 * @param data
	 * 		需要拼接的 List
	 * @param glue
	 * 		拼接字符串
	 * @param <O>
	 * 		类
	 *
	 * @return 拼接后的字符串
	 */
	public static <O> String toString(final List<O> data, final String glue){
		if(data == null){
			return null;
		}

		return data.size() == 0 ? Constants.EMPTY_STRING : data.stream().map(v->v.toString()).collect(Collectors
				.joining(glue));
	}

	/**
	 * 将 O 型 Set 拼接成字符串
	 *
	 * @param data
	 * 		需要拼接的 Set
	 * @param <O>
	 * 		类
	 *
	 * @return 拼接后的字符串
	 */
	public static <O> String toString(final Set<O> data){
		return toString(data, DEFAULT_GLUE);
	}

	/**
	 * 将 O 型 Set 拼接成字符串
	 *
	 * @param data
	 * 		需要拼接的 Set
	 * @param glue
	 * 		拼接字符串
	 * @param <O>
	 * 		类
	 *
	 * @return 拼接后的字符串
	 */
	public static <O> String toString(final Set<O> data, final String glue){
		if(data == null){
			return null;
		}

		return data.size() == 0 ? Constants.EMPTY_STRING : data.stream().map(v->v.toString()).collect(Collectors
				.joining(glue));
	}

	/**
	 * 将数组转换为 List
	 *
	 * @param a
	 * 		需要转换的数组
	 * @param <O>
	 * 		类
	 *
	 * @return 转换结果
	 */
	public final static <O> List<O> toList(final O[] a){
		return a == null ? null : Arrays.asList(a);
	}

	/**
	 * 将数组转换为 Set
	 *
	 * @param a
	 * 		需要转换的数组
	 * @param <O>
	 * 		类
	 *
	 * @return 转换结果
	 */
	public final static <O> Set<O> toSet(final O[] a){
		return a == null ? null : Arrays.asList(a).stream().collect(Collectors.toCollection(LinkedHashSet::new));
	}

}