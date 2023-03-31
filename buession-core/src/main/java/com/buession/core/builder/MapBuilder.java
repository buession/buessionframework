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
 * | Copyright @ 2013-2023 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.core.builder;

import com.buession.core.utils.Assert;
import com.buession.core.validator.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Map 构建器
 *
 * @param <K>
 * 		Key 类型
 * @param <V>
 * 		Value 类型
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public class MapBuilder<K, V> {

	private final Map<K, V> data;

	private final static Logger logger = LoggerFactory.getLogger(MapBuilder.class);

	/**
	 * 构造函数
	 *
	 * @param data
	 * 		Map 数据
	 */
	private MapBuilder(final Map<K, V> data){
		this.data = data;
	}

	/**
	 * 创建默认为 {@link HashMap} 类型的 {@link MapBuilder} 实例
	 *
	 * @param <K>
	 * 		Key 类型
	 * @param <V>
	 * 		Value 类型
	 *
	 * @return {@link MapBuilder} 实例
	 */
	public static <K, V> MapBuilder<K, V> create(){
		return new MapBuilder<>(new HashMap<>());
	}

	/**
	 * 创建默认为 {@link HashMap} 类型的 {@link MapBuilder} 实例
	 *
	 * @param initialCapacity
	 * 		the initial capacity
	 * @param <K>
	 * 		Key 类型
	 * @param <V>
	 * 		Value 类型
	 *
	 * @return {@link MapBuilder} 实例
	 *
	 * @since 2.1.2
	 */
	public static <K, V> MapBuilder<K, V> create(final int initialCapacity){
		return new MapBuilder<>(new HashMap<>(initialCapacity));
	}

	/**
	 * 创建默认为 {@link HashMap} 类型的 {@link MapBuilder} 实例
	 *
	 * @param initialCapacity
	 * 		the initial capacity
	 * @param loadFactor
	 * 		the load factor
	 * @param <K>
	 * 		Key 类型
	 * @param <V>
	 * 		Value 类型
	 *
	 * @return {@link MapBuilder} 实例
	 *
	 * @since 2.1.2
	 */
	public static <K, V> MapBuilder<K, V> create(final int initialCapacity, final float loadFactor){
		return new MapBuilder<>(new HashMap<>(initialCapacity, loadFactor));
	}

	/**
	 * 创建默认为 {@link HashMap} 类型的 {@link MapBuilder} 实例
	 *
	 * @param m
	 * 		the map whose mappings are to be placed in this map
	 * @param <K>
	 * 		Key 类型
	 * @param <V>
	 * 		Value 类型
	 *
	 * @return {@link MapBuilder} 实例
	 *
	 * @since 2.1.2
	 */
	public static <K, V> MapBuilder<K, V> create(final Map<K, V> m){
		return new MapBuilder<>(m);
	}

	/**
	 * 创建实例
	 *
	 * @param clazz
	 * 		Map Class
	 * @param <K>
	 * 		Key 类型
	 * @param <V>
	 * 		Value 类型
	 * @param <M>
	 * 		Map 类型
	 *
	 * @return {@link MapBuilder} 实例
	 */
	public static <K, V, M extends Map<K, V>> MapBuilder<K, V> create(final Class<M> clazz){
		Assert.isNull(clazz, "java.util.Map class cloud not be null.");

		Map<K, V> data;
		try{
			data = clazz.newInstance();
		}catch(Exception e){
			logger.error("Create {} instance error: {}", clazz.getName(), e.getMessage());
			data = new HashMap<>();
		}

		return new MapBuilder<>(data);
	}

	/**
	 * 添加元素
	 *
	 * @param key
	 * 		Key
	 * @param value
	 * 		值
	 *
	 * @return {@link MapBuilder} 实例
	 */
	public MapBuilder<K, V> put(final K key, final V value){
		data.put(key, value);
		return this;
	}

	/**
	 * 批量添加元素
	 *
	 * @param data
	 * 		Map
	 *
	 * @return {@link MapBuilder} 实例
	 */
	public MapBuilder<K, V> putAll(final Map<K, V> data){
		if(Validate.isNotEmpty(data)){
			this.data.putAll(data);
		}
		return this;
	}

	/**
	 * 移除元素
	 *
	 * @param key
	 * 		Key
	 *
	 * @return {@link MapBuilder} 实例
	 */
	public MapBuilder<K, V> remove(final K key){
		data.remove(key);
		return this;
	}

	/**
	 * 清空元素
	 *
	 * @return {@link MapBuilder} 实例
	 */
	public MapBuilder<K, V> clear(){
		data.clear();
		return this;
	}

	/**
	 * 构建 Map 数据
	 *
	 * @return Map 数据
	 */
	public Map<K, V> build(){
		return data;
	}

	/**
	 * 创建空 Map
	 *
	 * @param <K>
	 * 		Key 类型
	 * @param <V>
	 * 		Value 类型
	 *
	 * @return 空 Map
	 */
	public static <K, V> Map<K, V> empty(){
		return MapBuilder.<K, V>create().build();
	}

	/**
	 * 创建空 Map
	 *
	 * @param <K>
	 * 		Key 类型
	 * @param <V>
	 * 		Value 类型
	 *
	 * @return 空 Map
	 */
	public static <K, V> Map<K, V> of(){
		return empty();
	}

	/**
	 * 创建单一元素 Map
	 *
	 * @param key
	 * 		Key
	 * @param value
	 * 		值
	 * @param <K>
	 * 		Key 类型
	 * @param <V>
	 * 		Value 类型
	 *
	 * @return 单一元素 Map
	 */
	public static <K, V> Map<K, V> of(final K key, final V value){
		return MapBuilder.<K, V>create(1).put(key, value).build();
	}

}

