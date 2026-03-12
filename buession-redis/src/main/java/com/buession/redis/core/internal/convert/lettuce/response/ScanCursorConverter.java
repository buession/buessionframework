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
 * | Copyright @ 2013-2026 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.core.internal.convert.lettuce.response;

import com.buession.core.converter.Converter;
import com.buession.core.converter.ListConverter;
import com.buession.lang.KeyValue;
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.Tuple;
import io.lettuce.core.KeyScanCursor;
import io.lettuce.core.MapScanCursor;
import io.lettuce.core.ScanCursor;
import io.lettuce.core.ScoredValue;
import io.lettuce.core.ScoredValueScanCursor;
import io.lettuce.core.ValueScanCursor;

import java.util.ArrayList;
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
@FunctionalInterface
public interface ScanCursorConverter<T extends ScanCursor, R> extends Converter<T, ScanResult<R>> {

	/**
	 * Lettuce {@link KeyScanCursor} 转换为 {@link ScanResult}
	 *
	 * @param <SK>
	 * 		原始 Key 类型
	 * @param <TK>
	 * 		目标 Key 类型
	 *
	 * @author Yong.Teng
	 */
	final class KeyScanCursorConverter<SK, TK> implements ScanCursorConverter<KeyScanCursor<SK>, TK> {

		private final Converter<SK, TK> converter;

		public KeyScanCursorConverter(final Converter<SK, TK> converter) {
			this.converter = converter;
		}

		@Override
		public ScanResult<TK> convert(final KeyScanCursor<SK> source) {
			final ListConverter<SK, TK> listConverter = new ListConverter<>(converter);
			return new ScanResult<>(source.getCursor(), listConverter.convert(source.getKeys()));
		}

	}

	/**
	 * Lettuce {@link ValueScanCursor} 转换为 {@link ScanResult}
	 *
	 * @param <SV>
	 * 		原始值类型
	 * @param <TV>
	 * 		目标值类型
	 *
	 * @author Yong.Teng
	 */
	final class ValueScanCursorConverter<SV, TV> implements ScanCursorConverter<ValueScanCursor<SV>, TV> {

		private final Converter<SV, TV> converter;

		public ValueScanCursorConverter(final Converter<SV, TV> converter) {
			this.converter = converter;
		}

		@Override
		public ScanResult<TV> convert(final ValueScanCursor<SV> source) {
			final ListConverter<SV, TV> listConverter = new ListConverter<>(converter);
			return new ScanResult<>(source.getCursor(), listConverter.convert(source.getValues()));
		}

	}

	/**
	 * Lettuce {@link ScoredValueScanCursor} 转换为 {@link ScanResult}
	 *
	 * @author Yong.Teng
	 */
	final class ScoredValueScanCursorConverter implements ScanCursorConverter<ScoredValueScanCursor<byte[]>, Tuple> {

		@Override
		public ScanResult<Tuple> convert(final ScoredValueScanCursor<byte[]> source) {
			ListConverter<ScoredValue<byte[]>, Tuple> listScoredValueConverter = new ListConverter<>(
					new ScoredValueTupleConverter());
			return new ScanResult<>(source.getCursor(), listScoredValueConverter.convert(source.getValues()));
		}

	}

	/**
	 * Lettuce {@link MapScanCursor} 转换为 {@link ScanResult}
	 *
	 * @param <SK>
	 * 		原始 Key 类型
	 * @param <SV>
	 * 		原始值类型
	 * @param <TK>
	 * 		目标 Key 类型
	 * @param <TV>
	 * 		目标 Key 类型
	 *
	 * @author Yong.Teng
	 */
	final class MapScanCursorConverter<SK, SV, TK, TV>
			implements ScanCursorConverter<MapScanCursor<SK, SV>, KeyValue<TK, TV>> {

		private final Converter<SK, TK> keyConverter;

		private final Converter<SV, TV> valueConverter;

		public MapScanCursorConverter(final Converter<SK, TK> keyConverter, final Converter<SV, TV> valueConverter) {
			this.keyConverter = keyConverter;
			this.valueConverter = valueConverter;
		}

		@Override
		public ScanResult<KeyValue<TK, TV>> convert(final MapScanCursor<SK, SV> source) {
			final Map<SK, SV> map = source.getMap();
			final List<KeyValue<TK, TV>> results = new ArrayList<>(map.size());

			for(Map.Entry<SK, SV> entry : map.entrySet()){
				results.add(
						new KeyValue<>(keyConverter.convert(entry.getKey()), valueConverter.convert(entry.getValue())));
			}

			return new ScanResult<>(source.getCursor(), results);
		}

	}


}
