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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.WeakHashMap;
import java.util.function.Function;

/**
 * Map 工具类
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public class Maps {

	/**
	 * 对 Map 的 Key 和 Value 进行操作，返回一个新的 Map
	 *
	 * @param map
	 * 		需操作的 Map
	 * @param keyFun
	 * 		Key 操作函数
	 * @param valueFun
	 * 		Value 操作函数
	 * @param <SK>
	 * 		源 Key 类型
	 * @param <SV>
	 * 		源 Value 类型
	 * @param <TK>
	 * 		目标 Key 类型
	 * @param <TV>
	 * 		目标 Value 类型
	 *
	 * @return 新的 Map
	 */
	public static <SK, SV, TK, TV> Map<TK, TV> map(final Map<SK, SV> map, final Function<SK, TK> keyFun,
												   final Function<SV, TV> valueFun) {
		if(map == null){
			return null;
		}

		Map<TK, TV> result;

		if(map instanceof LinkedHashMap){
			result = new LinkedHashMap<>(map.size());
		}else if(map instanceof IdentityHashMap){
			result = new IdentityHashMap<>(map.size());
		}else if(map instanceof TreeMap){
			result = new TreeMap<>();
		}else if(map instanceof WeakHashMap){
			result = new WeakHashMap<>(map.size());
		}else{
			result = new HashMap<>(map.size());
		}

		return map(map, keyFun, valueFun, result);
	}

	/**
	 * 对 Map 的 Key 和 Value 进行操作，返回一个新的 Map
	 *
	 * @param map
	 * 		需操作的 Map
	 * @param keyFun
	 * 		Key 操作函数
	 * @param valueFun
	 * 		Value 操作函数
	 * @param targetMap
	 * 		目标 Map
	 * @param <SK>
	 * 		源 Key 类型
	 * @param <SV>
	 * 		源 Value 类型
	 * @param <TK>
	 * 		目标 Key 类型
	 * @param <TV>
	 * 		目标 Value 类型
	 *
	 * @return 新的 Map
	 */
	public static <SK, SV, TK, TV> Map<TK, TV> map(final Map<SK, SV> map, final Function<SK, TK> keyFun,
												   final Function<SV, TV> valueFun, final Map<TK, TV> targetMap) {
		if(map == null){
			return null;
		}

		map.forEach((k, v)->targetMap.put(keyFun.apply(k), valueFun.apply(v)));

		return targetMap;
	}

	/**
	 * 将 Map 转换为 List
	 *
	 * @param map
	 * 		需要转换的 Map
	 * @param <K>
	 * 		Key 类型
	 * @param <V>
	 * 		Value 类型
	 *
	 * @return 当 map 为 null 时，返回 null；否则返回 V 类型的 {@link List}
	 */
	public static <K, V> List<V> toList(final Map<K, V> map) {
		if(map == null){
			return null;
		}else if(map instanceof LinkedHashMap){
			return new LinkedList<>(map.values());
		}else{
			return new ArrayList<>(map.values());
		}
	}

	/**
	 * 将 Map 转换为 Set
	 *
	 * @param map
	 * 		需要转换的 Map
	 * @param <K>
	 * 		Key 类型
	 * @param <V>
	 * 		Value 类型
	 *
	 * @return 当 map 为 null 时，返回 null；否则返回 V 类型的 {@link Set}
	 */
	public static <K, V> Set<V> toSet(final Map<K, V> map) {
		if(map == null){
			return null;
		}else if(map instanceof LinkedHashMap){
			return new LinkedHashSet<>(map.values());
		}else{
			return new HashSet<>(map.values());
		}
	}

}
