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
package com.buession.redis.core.internal.convert;

import com.buession.core.converter.Converter;
import com.buession.core.converter.ListConverter;
import com.buession.core.converter.ListSetConverter;
import com.buession.core.converter.MapConverter;
import com.buession.core.converter.SetConverter;
import com.buession.redis.utils.SafeEncoder;

/**
 * @author Yong.Teng
 * @since 2.3.0
 */
public interface Converters {

	static ListConverter<String, byte[]> listStringToBinary() {
		return new ListConverter<>(SafeEncoder::encode);
	}

	static ListConverter<byte[], String> listBinaryToString() {
		return new ListConverter<>(SafeEncoder::encode);
	}

	static SetConverter<String, byte[]> setStringToBinary() {
		return new SetConverter<>(SafeEncoder::encode);
	}

	static SetConverter<byte[], String> setBinaryToString() {
		return new SetConverter<>(SafeEncoder::encode);
	}

	static ListSetConverter<byte[], String> listSetBinaryToString() {
		return new ListSetConverter<>(SafeEncoder::encode);
	}

	static ListSetConverter<byte[], String> setListBinaryToString() {
		return new ListSetConverter<>(SafeEncoder::encode);
	}

	static MapConverter<String, String, byte[], byte[]> mapStringToBinary() {
		return new MapConverter<>(SafeEncoder::encode, SafeEncoder::encode);
	}

	static MapConverter<byte[], byte[], String, String> mapBinaryToString() {
		return new MapConverter<>(SafeEncoder::encode, SafeEncoder::encode);
	}

	@SuppressWarnings({"unchecked"})
	static <S, T> Converter<S, T> always() {
		return (value)->(T) value;
	}

}
