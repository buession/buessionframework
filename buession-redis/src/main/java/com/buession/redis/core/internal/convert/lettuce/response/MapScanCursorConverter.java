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
package com.buession.redis.core.internal.convert.lettuce.response;

import com.buession.core.converter.Converter;
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.internal.convert.response.MapConverter;
import io.lettuce.core.MapScanCursor;

import java.util.Map;

/**
 * Lettuce {@link MapScanCursor} 转换为 {@link ScanResult}
 *
 * @param <K>
 * 		Key 类型
 * @param <V>
 * 		值类型
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public final class MapScanCursorConverter<K, V>
		implements Converter<MapScanCursor<K, V>, ScanResult<Map<K, V>>> {

	@Override
	public ScanResult<Map<K, V>> convert(final MapScanCursor<K, V> source) {
		return new ScanResult<>(source.getCursor(), source.getMap());
	}

	/**
	 * Lettuce {@link MapScanCursor} 转换为 {@link ScanResult}
	 *
	 * @author Yong.Teng
	 * @since 3.0.0
	 */
	public final static class BvSvMapScanCursorConverter
			implements Converter<MapScanCursor<byte[], byte[]>, ScanResult<Map<String, String>>> {

		private final MapConverter.BinaryToStringMapConverter binaryToStringMapConverter =
				new MapConverter.BinaryToStringMapConverter();

		@Override
		public ScanResult<Map<String, String>> convert(final MapScanCursor<byte[], byte[]> source) {
			return new ScanResult<>(source.getCursor(), binaryToStringMapConverter.convert(source.getMap()));
		}

	}

}
