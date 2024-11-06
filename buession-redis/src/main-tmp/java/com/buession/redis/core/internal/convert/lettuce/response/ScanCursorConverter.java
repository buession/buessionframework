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
import com.buession.core.converter.ListConverter;
import com.buession.core.converter.MapConverter;
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.Tuple;
import com.buession.redis.core.internal.convert.Converters;
import io.lettuce.core.KeyScanCursor;
import io.lettuce.core.MapScanCursor;
import io.lettuce.core.ScanCursor;
import io.lettuce.core.ScoredValue;
import io.lettuce.core.ScoredValueScanCursor;
import io.lettuce.core.ValueScanCursor;

import java.util.List;
import java.util.Map;

/**
 * Lettuce {@link ScanCursor} 转换为 {@link ScanResult}
 *
 * @param <T>
 *        {@link ScanCursor} 类型
 * @param <R>
 *        {@link ScanResult} 类型
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public interface ScanCursorConverter<T extends ScanCursor, R> extends Converter<T, ScanResult<R>> {

	/**
	 * Lettuce {@link KeyScanCursor} 转换为 {@link ScanResult}
	 *
	 * @param <K>
	 * 		Key 类型
	 *
	 * @author Yong.Teng
	 */
	final class KeyScanCursorConverter<K> implements ScanCursorConverter<KeyScanCursor<K>, List<K>> {

		@Override
		public ScanResult<List<K>> convert(final KeyScanCursor<K> source) {
			return new ScanResult<>(source.getCursor(), source.getKeys());
		}

		/**
		 * Lettuce {@link KeyScanCursor} 转换为 {@link ScanResult}
		 *
		 * @author Yong.Teng
		 */
		public final static class BSKeyScanCursorConverter implements ScanCursorConverter<KeyScanCursor<byte[]>,
				List<String>> {

			private final ListConverter<byte[], String> binaryToStringListConverter = Converters.listBinaryToString();

			@Override
			public ScanResult<List<String>> convert(final KeyScanCursor<byte[]> source) {
				return new ScanResult<>(source.getCursor(), binaryToStringListConverter.convert(source.getKeys()));
			}

		}

	}

	/**
	 * Lettuce {@link ValueScanCursor} 转换为 {@link ScanResult}
	 *
	 * @param <K>
	 * 		Key 类型
	 *
	 * @author Yong.Teng
	 */
	final class ValueScanCursorConverter<K> implements ScanCursorConverter<ValueScanCursor<K>, List<K>> {

		@Override
		public ScanResult<List<K>> convert(final ValueScanCursor<K> source) {
			return new ScanResult<>(source.getCursor(), source.getValues());
		}

		/**
		 * Lettuce {@link ValueScanCursor} 转换为 {@link ScanResult}
		 *
		 * @author Yong.Teng
		 */
		public final static class BSKeyScanCursorConverter implements ScanCursorConverter<ValueScanCursor<byte[]>,
				List<String>> {

			private final ListConverter<byte[], String> binaryToStringListConverter = Converters.listBinaryToString();

			@Override
			public ScanResult<List<String>> convert(final ValueScanCursor<byte[]> source) {
				return new ScanResult<>(source.getCursor(), binaryToStringListConverter.convert(source.getValues()));
			}

		}

		/**
		 * Lettuce {@link ScoredValueScanCursor} 转换为 {@link ScanResult}
		 *
		 * @author Yong.Teng
		 */
		public final static class ScoredValueScanCursorConverter
				implements ScanCursorConverter<ScoredValueScanCursor<byte[]>, List<Tuple>> {

			private final ListConverter<ScoredValue<byte[]>, Tuple> listScoredValueConverter
					= ScoredValueTupleConverter.BinaryScoredValueTupleConverter.listConverter();

			@Override
			public ScanResult<List<Tuple>> convert(final ScoredValueScanCursor<byte[]> source) {
				return new ScanResult<>(source.getCursor(), listScoredValueConverter.convert(source.getValues()));
			}

		}

	}

	/**
	 * Lettuce {@link MapScanCursor} 转换为 {@link ScanResult}
	 *
	 * @param <K>
	 * 		Key 类型
	 * @param <V>
	 * 		值类型
	 *
	 * @author Yong.Teng
	 */
	final class MapScanCursorConverter<K, V> implements ScanCursorConverter<MapScanCursor<K, V>, Map<K, V>> {

		@Override
		public ScanResult<Map<K, V>> convert(final MapScanCursor<K, V> source) {
			return new ScanResult<>(source.getCursor(), source.getMap());
		}

		/**
		 * Lettuce {@link MapScanCursor} 转换为 {@link ScanResult}
		 *
		 * @author Yong.Teng
		 */
		public final static class BvSvMapScanCursorConverter implements ScanCursorConverter<MapScanCursor<byte[],
				byte[]>, Map<String, String>> {

			private final MapConverter<byte[], byte[], String, String> binaryToStringMapConverter =
					Converters.mapBinaryToString();

			@Override
			public ScanResult<Map<String, String>> convert(final MapScanCursor<byte[], byte[]> source) {
				return new ScanResult<>(source.getCursor(), binaryToStringMapConverter.convert(source.getMap()));
			}

		}

	}

}
