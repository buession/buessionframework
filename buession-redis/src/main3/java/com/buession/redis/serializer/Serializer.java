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
package com.buession.redis.serializer;

import com.buession.core.type.TypeReference;

/**
 * 序列化和反序列化
 *
 * @author Yong.Teng
 */
public interface Serializer {

	/**
	 * 将任意对象序列化为字符串
	 *
	 * @param object
	 * 		待序列化对象
	 * @param <V>
	 * 		对象类型
	 *
	 * @return 序列化后的字符串
	 */
	<V> String serialize(final V object);

	/**
	 * 将任意对象数组序列化为字符串
	 *
	 * @param objects
	 * 		待序列化对象数组
	 * @param <V>
	 * 		对象类型
	 *
	 * @return 序列化后的字符串
	 */
	<V> String[] serialize(final V[] objects);

	/**
	 * 将任意对象序列化为字节
	 *
	 * @param object
	 * 		待序列化对象
	 * @param <V>
	 * 		对象类型
	 *
	 * @return 序列化后的字节
	 */
	<V> byte[] serializeAsBytes(final V object);

	/**
	 * 将任意对象数组序列化为字节
	 *
	 * @param objects
	 * 		待序列化对象数组
	 * @param <V>
	 * 		对象类型
	 *
	 * @return 序列化后的字节
	 */
	<V> byte[][] serializeAsBytes(final V[] objects);

	/**
	 * 将字符串反序列化为对象
	 *
	 * @param str
	 * 		待反序列化的字符串
	 * @param <V>
	 * 		对象类型
	 *
	 * @return 反序列化后的对象
	 */
	<V> V deserialize(final String str);

	/**
	 * 将字符串反序列化为 clazz 指定的对象
	 *
	 * @param str
	 * 		待反序列化的字符串
	 * @param clazz
	 * 		对象类
	 * @param <V>
	 * 		对象类型
	 *
	 * @return 反序列化后的对象
	 */
	<V> V deserialize(final String str, final Class<V> clazz);

	/**
	 * 将字符串反序列化为 type 指定的对象
	 *
	 * @param str
	 * 		待反序列化的字符串
	 * @param type
	 * 		对象引用
	 * @param <V>
	 * 		对象类型
	 *
	 * @return 反序列化后的对象
	 */
	<V> V deserialize(final String str, final TypeReference<V> type);

	/**
	 * 将字节反序列化为对象
	 *
	 * @param bytes
	 * 		待反序列化的字节
	 * @param <V>
	 * 		对象类型
	 *
	 * @return 反序列化后的对象
	 */
	<V> V deserializeBytes(final byte[] bytes);

	/**
	 * 将字节反序列化为 clazz 指定的对象
	 *
	 * @param bytes
	 * 		待反序列化的字节
	 * @param clazz
	 * 		对象类
	 * @param <V>
	 * 		对象类型
	 *
	 * @return 反序列化后的对象
	 */
	<V> V deserializeBytes(final byte[] bytes, final Class<V> clazz);

	/**
	 * 将字符串反序列化为 type 指定的对象
	 *
	 * @param bytes
	 * 		待反序列化的字节
	 * @param type
	 * 		对象引用
	 * @param <V>
	 * 		对象类型
	 *
	 * @return 反序列化后的对象
	 */
	<V> V deserializeBytes(final byte[] bytes, final TypeReference<V> type);

}
