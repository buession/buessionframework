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
package com.buession.core.serializer;

import java.nio.charset.Charset;

/**
 * 序列化和反序列化
 *
 * @author Yong.Teng
 */
public interface Serializer {

	/**
	 * 将对象序列化为字符串
	 *
	 * @param object
	 * 		待序列化对象
	 * @param <V>
	 * 		待序列化对象类型
	 *
	 * @return 序列化后的字符串
	 *
	 * @throws SerializerException
	 * 		序列化异常
	 */
	<V> String serialize(final V object) throws SerializerException;

	/**
	 * 将对象序列化为字符串
	 *
	 * @param object
	 * 		待序列化对象
	 * @param charsetName
	 * 		字符集
	 * @param <V>
	 * 		待序列化对象类型
	 *
	 * @return 序列化后的字符串
	 *
	 * @throws SerializerException
	 * 		序列化异常
	 */
	<V> String serialize(final V object, final String charsetName) throws SerializerException;

	/**
	 * 将对象序列化为字符串
	 *
	 * @param object
	 * 		待序列化对象
	 * @param charset
	 * 		字符集
	 * @param <V>
	 * 		待序列化对象类型
	 *
	 * @return 序列化后的字符串
	 *
	 * @throws SerializerException
	 * 		序列化异常
	 */
	<V> String serialize(final V object, final Charset charset) throws SerializerException;

	/**
	 * 将对象序列化为字节
	 *
	 * @param object
	 * 		待序列化对象
	 * @param <V>
	 * 		待序列化对象类型
	 *
	 * @return 序列化后的字节
	 *
	 * @throws SerializerException
	 * 		序列化异常
	 */
	<V> byte[] serializeAsBytes(final V object) throws SerializerException;

	/**
	 * 将对象序列化为字节
	 *
	 * @param object
	 * 		待序列化对象
	 * @param charsetName
	 * 		字符集
	 * @param <V>
	 * 		待序列化对象类型
	 *
	 * @return 序列化后的字节
	 *
	 * @throws SerializerException
	 * 		序列化异常
	 */
	<V> byte[] serializeAsBytes(final V object, final String charsetName) throws SerializerException;

	/**
	 * 将对象序列化为字节
	 *
	 * @param object
	 * 		待序列化对象
	 * @param charset
	 * 		字符集
	 * @param <V>
	 * 		待序列化对象类型
	 *
	 * @return 序列化后的字节
	 *
	 * @throws SerializerException
	 * 		序列化异常
	 */
	<V> byte[] serializeAsBytes(final V object, final Charset charset) throws SerializerException;

}
