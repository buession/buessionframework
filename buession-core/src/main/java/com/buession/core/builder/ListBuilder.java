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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * List 构建器
 *
 * @param <V>
 * 		Value 类型
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public class ListBuilder<V> {

	private final List<V> data;

	private final static Logger logger = LoggerFactory.getLogger(ListBuilder.class);

	/**
	 * 构造函数
	 *
	 * @param data
	 * 		List 数据
	 */
	private ListBuilder(final List<V> data){
		this.data = data;
	}

	/**
	 * 创建默认为 {@link ArrayList} 类型的 {@link ListBuilder} 实例
	 *
	 * @param <V>
	 * 		Value 类型
	 *
	 * @return {@link ListBuilder} 实例
	 */
	public static <V> ListBuilder<V> create(){
		return new ListBuilder<>(new ArrayList<>());
	}

	/**
	 * 创建默认为 {@link ArrayList} 类型的 {@link ListBuilder} 实例
	 *
	 * @param initialCapacity
	 * 		the initial capacity of the list
	 * @param <V>
	 * 		Value 类型
	 *
	 * @return {@link ListBuilder} 实例
	 *
	 * @since 2.1.2
	 */
	public static <V> ListBuilder<V> create(final int initialCapacity){
		return new ListBuilder<>(new ArrayList<>(initialCapacity));
	}

	/**
	 * 创建默认为 {@link ArrayList} 类型的 {@link ListBuilder} 实例
	 *
	 * @param list
	 * 		the list whose elements are to be placed into this list
	 * @param <V>
	 * 		Value 类型
	 *
	 * @return {@link ListBuilder} 实例
	 *
	 * @since 2.2.0
	 */
	public static <V> ListBuilder<V> create(final List<V> list){
		return new ListBuilder<>(list);
	}

	/**
	 * 创建默认为 {@link ArrayList} 类型的 {@link ListBuilder} 实例
	 *
	 * @param c
	 * 		the collection whose elements are to be placed into this list
	 * @param <V>
	 * 		Value 类型
	 *
	 * @return {@link ListBuilder} 实例
	 *
	 * @since 2.1.2
	 */
	public static <V> ListBuilder<V> create(final Collection<? extends V> c){
		return new ListBuilder<>(new ArrayList<>(c));
	}

	/**
	 * 创建实例
	 *
	 * @param clazz
	 * 		List Class
	 * @param <V>
	 * 		Value 类型
	 * @param <S>
	 * 		List 类型
	 *
	 * @return {@link ListBuilder} 实例
	 */
	public static <V, S extends List<V>> ListBuilder<V> create(final Class<S> clazz){
		Assert.isNull(clazz, "java.util.List class cloud not be null.");

		List<V> data;
		try{
			data = clazz.newInstance();
		}catch(Exception e){
			logger.error("Create {} instance error: {}", clazz.getName(), e.getMessage());
			data = new ArrayList<>();
		}

		return new ListBuilder<>(data);
	}

	/**
	 * 添加元素
	 *
	 * @param value
	 * 		值
	 *
	 * @return {@link ListBuilder} 实例
	 */
	public ListBuilder<V> add(final V value){
		data.add(value);
		return this;
	}

	/**
	 * 添加元素，仅当 value 不为 null 时
	 *
	 * @param value
	 * 		值
	 *
	 * @return {@link ListBuilder} 实例
	 *
	 * @since 2.0.0
	 */
	public ListBuilder<V> addIfPresent(final V value){
		if(value != null){
			data.add(value);
		}
		return this;
	}

	/**
	 * 批量添加元素
	 *
	 * @param data
	 * 		Collection
	 *
	 * @return {@link ListBuilder} 实例
	 */
	public ListBuilder<V> addAll(final Collection<V> data){
		if(Validate.isNotEmpty(data)){
			this.data.addAll(data);
		}
		return this;
	}

	/**
	 * 移除元素
	 *
	 * @param value
	 * 		Value
	 *
	 * @return {@link ListBuilder} 实例
	 */
	public ListBuilder<V> remove(final V value){
		data.remove(value);
		return this;
	}

	/**
	 * 清空元素
	 *
	 * @return {@link ListBuilder} 实例
	 */
	public ListBuilder<V> clear(){
		data.clear();
		return this;
	}

	/**
	 * 构建 List 数据
	 *
	 * @return List 数据
	 */
	public List<V> build(){
		return data;
	}

	/**
	 * 创建空 List
	 *
	 * @param <V>
	 * 		Value 类型
	 *
	 * @return 空 Set
	 */
	public static <V> List<V> empty(){
		return ListBuilder.<V>create().build();
	}

	/**
	 * 创建空 List
	 *
	 * @param <V>
	 * 		Value 类型
	 *
	 * @return 空 Set
	 */
	public static <V> List<V> of(){
		return empty();
	}

	/**
	 * 创建单一元素 List
	 *
	 * @param value
	 * 		值
	 * @param <V>
	 * 		Value 类型
	 *
	 * @return 单一元素 List
	 */
	public static <V> List<V> of(final V value){
		return ListBuilder.<V>create(1).add(value).build();
	}

}

