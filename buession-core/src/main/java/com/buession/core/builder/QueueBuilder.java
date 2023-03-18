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

import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Queue 构建器
 *
 * @param <V>
 * 		Value 类型
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public class QueueBuilder<V> {

	private final Queue<V> data;

	private final static Logger logger = LoggerFactory.getLogger(QueueBuilder.class);

	/**
	 * 构造函数
	 *
	 * @param data
	 * 		Queue 数据
	 */
	private QueueBuilder(final Queue<V> data){
		this.data = data;
	}

	/**
	 * 创建默认为 {@link LinkedList} 类型的 {@link QueueBuilder} 实例
	 *
	 * @param <V>
	 * 		Value 类型
	 *
	 * @return {@link QueueBuilder} 实例
	 */
	public static <V> QueueBuilder<V> create(){
		return new QueueBuilder<>(new LinkedList<>());
	}

	/**
	 * 创建默认为 {@link LinkedList} 类型的 {@link QueueBuilder} 实例
	 *
	 * @param queue
	 * 		the queue whose elements are to be placed into this queue
	 * @param <V>
	 * 		Value 类型
	 *
	 * @return {@link QueueBuilder} 实例
	 *
	 * @since 2.2.0
	 */
	public static <V> QueueBuilder<V> create(final Queue<V> queue){
		return new QueueBuilder<>(queue);
	}

	/**
	 * 创建默认为 {@link LinkedList} 类型的 {@link QueueBuilder} 实例
	 *
	 * @param c
	 * 		the collection whose elements are to be placed into this queue
	 * @param <V>
	 * 		Value 类型
	 *
	 * @return {@link QueueBuilder} 实例
	 *
	 * @since 2.1.2
	 */
	public static <V> QueueBuilder<V> create(final Collection<? extends V> c){
		return new QueueBuilder<>(new LinkedList<>(c));
	}

	/**
	 * 创建实例
	 *
	 * @param clazz
	 * 		Queue Class
	 * @param <V>
	 * 		Value 类型
	 * @param <S>
	 * 		Queue 类型
	 *
	 * @return {@link QueueBuilder} 实例
	 */
	public static <V, S extends Queue<V>> QueueBuilder<V> create(final Class<S> clazz){
		Assert.isNull(clazz, "java.util.Queue class cloud not be null.");

		Queue<V> data;
		try{
			data = clazz.newInstance();
		}catch(Exception e){
			logger.error("Create {} instance error: {}", clazz.getName(), e.getMessage());
			data = new LinkedList<>();
		}

		return new QueueBuilder<>(data);
	}

	/**
	 * 添加元素
	 *
	 * @param value
	 * 		值
	 *
	 * @return {@link QueueBuilder} 实例
	 */
	public QueueBuilder<V> add(final V value){
		data.add(value);
		return this;
	}

	/**
	 * 添加元素，仅当 value 不为 null 时
	 *
	 * @param value
	 * 		值
	 *
	 * @return {@link QueueBuilder} 实例
	 */
	public QueueBuilder<V> addIfPresent(final V value){
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
	 * @return {@link QueueBuilder} 实例
	 */
	public QueueBuilder<V> addAll(final Collection<V> data){
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
	 * @return {@link QueueBuilder} 实例
	 */
	public QueueBuilder<V> remove(final V value){
		data.remove(value);
		return this;
	}

	/**
	 * 清空元素
	 *
	 * @return {@link QueueBuilder} 实例
	 */
	public QueueBuilder<V> clear(){
		data.clear();
		return this;
	}

	/**
	 * 构建 Queue 数据
	 *
	 * @return Queue 数据
	 */
	public Queue<V> build(){
		return data;
	}

	/**
	 * 创建空 Queue
	 *
	 * @param <V>
	 * 		Value 类型
	 *
	 * @return 空 Set
	 */
	public static <V> Queue<V> empty(){
		return QueueBuilder.<V>create().build();
	}

	/**
	 * 创建空 Queue
	 *
	 * @param <V>
	 * 		Value 类型
	 *
	 * @return 空 Set
	 */
	public static <V> Queue<V> of(){
		return empty();
	}

	/**
	 * 创建单一元素 Queue
	 *
	 * @param value
	 * 		值
	 * @param <V>
	 * 		Value 类型
	 *
	 * @return 单一元素 Queue
	 */
	public static <V> Queue<V> of(final V value){
		return QueueBuilder.<V>create().add(value).build();
	}

}

