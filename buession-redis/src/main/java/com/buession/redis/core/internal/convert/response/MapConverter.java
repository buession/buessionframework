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

import java.util.Map;

/**
 * {@link Map} 转换器
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public interface MapConverter<SK, SV, TK, TV> extends Converter<Map<SK, SV>, Map<TK, TV>> {

	class StringToBinaryMapConverter extends com.buession.core.converter.MapConverter<String, String, byte[], byte[]>
			implements MapConverter<String, String, byte[], byte[]> {

		public StringToBinaryMapConverter() {
			super(SafeEncoder::encode, SafeEncoder::encode);
		}

	}

	class BinaryToStringMapConverter extends com.buession.core.converter.MapConverter<byte[], byte[], String, String>
			implements MapConverter<byte[], byte[], String, String> {

		public BinaryToStringMapConverter() {
			super(SafeEncoder::encode, SafeEncoder::encode);
		}

	}

	class StringToBinaryKeyPrimitiveValueMapConverter<V> extends com.buession.core.converter.MapConverter<String, V,
			byte[], V> implements MapConverter<String, V, byte[], V> {

		public StringToBinaryKeyPrimitiveValueMapConverter() {
			super(SafeEncoder::encode, (v)->v);
		}

	}

	class BinaryToStringKeyPrimitiveValueMapConverter<V>
			extends com.buession.core.converter.MapConverter<byte[], V, String, V>
			implements MapConverter<byte[], V, String, V> {

		public BinaryToStringKeyPrimitiveValueMapConverter() {
			super(SafeEncoder::encode, (v)->v);
		}

	}

}
