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
package com.buession.redis.core.internal.convert.response;

import com.buession.core.converter.Converter;
import com.buession.redis.utils.SafeEncoder;

import java.util.List;
import java.util.Set;

/**
 * {@link Set} 到 {@link List} 转换器
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public interface SetListConverter<S, T> extends Converter<Set<S>, List<T>> {

	/**
	 * {@link String} {@link Set} 到 byte[] {@link List} 转换器
	 *
	 * @author Yong.Teng
	 * @since 3.0.0
	 */
	class StringToBinarySetListConverter extends com.buession.core.converter.SetListConverter<String, byte[]>
			implements SetListConverter<String, byte[]> {

		public StringToBinarySetListConverter() {
			super(SafeEncoder::encode);
		}

	}

	/**
	 * byte[] {@link Set} 到 {@link String} {@link List} 转换器
	 *
	 * @author Yong.Teng
	 * @since 3.0.0
	 */
	class BinaryToStringSetListConverter extends com.buession.core.converter.SetListConverter<byte[], String>
			implements SetListConverter<byte[], String> {

		public BinaryToStringSetListConverter() {
			super(SafeEncoder::encode);
		}

	}

}