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
package com.buession.redis.core.internal.convert;

import com.buession.core.converter.ArrayConverter;
import com.buession.core.converter.BinaryEnumConverter;
import com.buession.core.converter.BooleanStatusConverter;
import com.buession.core.converter.Converter;
import com.buession.core.converter.EnumConverter;
import com.buession.core.converter.ListConverter;
import com.buession.core.converter.MapConverter;
import com.buession.core.converter.PredicateStatusConverter;
import com.buession.redis.core.ObjectEncoding;
import com.buession.redis.core.Type;
import com.buession.redis.utils.SafeEncoder;

/**
 * @author Yong.Teng
 * @since 2.0.0
 */
public interface Converters {

	Converter<String, byte[]> STRING_TO_BINARY_CONVERTER = SafeEncoder::encode;

	Converter<byte[], String> BINARY_TO_STRING_CONVERTER = SafeEncoder::encode;

	PredicateStatusConverter<Long> ONE_STATUS_CONVERTER = new PredicateStatusConverter<>((val)->val == 1L);

	Converter<Long, Boolean> LONG_BOOLEAN_CONVERTER = (val)->val == 1L;

	BooleanStatusConverter BOOLEAN_STATUS_CONVERTER = new BooleanStatusConverter();

	ListConverter<String, byte[]> STRING_LIST_TO_BINARY_LIST_CONVERTER = new ListConverter<>(SafeEncoder::encode);

	ListConverter<byte[], String> BINARY_LIST_TO_STRING_LIST_CONVERTER = new ListConverter<>(SafeEncoder::encode);

	MapConverter<String, String, byte[], byte[]> STRING_MAP_TO_BINARY_MAP_CONVERTER = new MapConverter<>(
			SafeEncoder::encode, SafeEncoder::encode);

	MapConverter<byte[], byte[], String, String> BINARY_MAP_TO_STRING_MAP_CONVERTER = new MapConverter<>(
			SafeEncoder::encode, SafeEncoder::encode);

	ArrayConverter<String, byte[]> STRING_ARRAY_TO_BINARY_ARRAY_CONVERTER = new ArrayConverter<>(
			SafeEncoder::encode, byte[].class);

	ArrayConverter<byte[], String> BINARY_ARRAY_TO_STRING_ARRAY_CONVERTER = new ArrayConverter<>(
			SafeEncoder::encode, String.class);

	EnumConverter<Type> TYPE_RESULT_CONVERTER = new EnumConverter<>(Type.class);

	EnumConverter<ObjectEncoding> STRING_OBJECT_ENCODING_RESULT_CONVERTER = new EnumConverter<>(ObjectEncoding.class);

	Converter<byte[], ObjectEncoding> BINARY_OBJECT_ENCODING_RESULT_CONVERTER = new BinaryEnumConverter<>(
			ObjectEncoding.class);

}
