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

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * List 工具类
 * More {@link org.apache.commons.collections4.ListUtils}
 *
 * @author Yong.Teng
 */
public class ListUtils {

	/**
	 * 将 List 转换为数组
	 *
	 * @param list
	 * 		需要转换的 List
	 * @param arr
	 * 		the array into which the elements of this list are to
	 * 		be stored, if it is big enough; otherwise, a new array of the
	 * 		same runtime type is allocated for this purpose.
	 * @param <V>
	 * 		元素
	 *
	 * @return 转换结果
	 */
	public static <V> V[] toArray(final List<V> list, final V[] arr){
		return list == null ? null : list.toArray(arr);
	}

	/**
	 * 将 List 转换为 Set
	 *
	 * @param list
	 * 		需要转换的 List
	 * @param <V>
	 * 		元素
	 *
	 * @return 转换结果
	 */
	public static <V> Set<V> toSet(final List<V> list){
		return list == null ? null : new LinkedHashSet<>(list.size());
	}

	/**
	 * 将 List 转换为 Map
	 *
	 * @param list
	 * 		需要转换的 List
	 * @param <V>
	 * 		元素
	 *
	 * @return 转换结果
	 */
	public static <V> Map<Integer, V> toMap(final List<V> list){
		if(list == null){
			return null;
		}

		int size = list.size();
		Map<Integer, V> result = new LinkedHashMap<>(size);

		for(int i = 0; i < size; i++){
			result.put(i, list.get(i));
		}

		return result;
	}

}
