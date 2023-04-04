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
package com.buession.core.deserializer;

import com.buession.core.type.TypeReference;

/**
 * JSON 反序列化
 *
 * @author Yong.Teng
 * @since 2.3.0
 */
public interface JsonDeserializer {

	/**
	 * 字符串反序列化
	 *
	 * @param str
	 * 		待反序列化字符串
	 * @param clazz
	 * 		待反序列化对象类
	 * @param <V>
	 * 		待反序列化对象类型
	 *
	 * @return 反序列化后对象
	 *
	 * @throws DeserializerException
	 * 		反序列化异常
	 */
	<V> V deserialize(final String str, final Class<V> clazz) throws DeserializerException;

	/**
	 * 字符串反序列化
	 *
	 * @param str
	 * 		待反序列化字符串
	 * @param type
	 * 		待反序列化对象类引用
	 * @param <V>
	 * 		待反序列化对象类型
	 *
	 * @return 反序列化后对象
	 *
	 * @throws DeserializerException
	 * 		反序列化异常
	 */
	<V> V deserialize(final String str, final TypeReference<V> type) throws DeserializerException;

	/**
	 * 字节反序列化
	 *
	 * @param bytes
	 * 		待反序列化的字节
	 * @param clazz
	 * 		待反序列化对象类
	 * @param <V>
	 * 		待反序列化对象类型
	 *
	 * @return 反序列化后的对象
	 *
	 * @throws DeserializerException
	 * 		反序列化异常
	 */
	<V> V deserialize(final byte[] bytes, final Class<V> clazz) throws DeserializerException;

	/**
	 * 字节反序列化
	 *
	 * @param bytes
	 * 		待反序列化的字节
	 * @param type
	 * 		待反序列化对象类引用
	 * @param <V>
	 * 		待反序列化对象类型
	 *
	 * @return 反序列化后的对象
	 *
	 * @throws DeserializerException
	 * 		反序列化异常
	 */
	<V> V deserialize(final byte[] bytes, final TypeReference<V> type) throws DeserializerException;

	/**
	 * 字符串反序列化
	 *
	 * @param str
	 * 		待反序列化字符串
	 * @param clazz
	 * 		待反序列化对象类
	 * @param <V>
	 * 		待反序列化对象类型
	 *
	 * @return 反序列化后对象
	 *
	 * @throws DeserializerException
	 * 		反序列化异常
	 */
	default <V> V unserialize(final String str, final Class<V> clazz) throws DeserializerException{
		return deserialize(str, clazz);
	}

	/**
	 * 字符串反序列化
	 *
	 * @param str
	 * 		待反序列化字符串
	 * @param type
	 * 		待反序列化对象类引用
	 * @param <V>
	 * 		待反序列化对象类型
	 *
	 * @return 反序列化后对象
	 *
	 * @throws DeserializerException
	 * 		反序列化异常
	 */
	default <V> V unserialize(final String str, final TypeReference<V> type) throws DeserializerException{
		return deserialize(str, type);
	}

	/**
	 * 字节反序列化
	 *
	 * @param bytes
	 * 		待反序列化的字节
	 * @param clazz
	 * 		待反序列化对象类
	 * @param <V>
	 * 		待反序列化对象类型
	 *
	 * @return 反序列化后的对象
	 *
	 * @throws DeserializerException
	 * 		反序列化异常
	 */
	default <V> V unserialize(final byte[] bytes, final Class<V> clazz) throws DeserializerException{
		return deserialize(bytes, clazz);
	}

	/**
	 * 字节反序列化
	 *
	 * @param bytes
	 * 		待反序列化的字节
	 * @param type
	 * 		待反序列化对象类引用
	 * @param <V>
	 * 		待反序列化对象类型
	 *
	 * @return 反序列化后的对象
	 *
	 * @throws DeserializerException
	 * 		反序列化异常
	 */
	default <V> V unserialize(final byte[] bytes, final TypeReference<V> type) throws DeserializerException{
		return deserialize(bytes, type);
	}

}
