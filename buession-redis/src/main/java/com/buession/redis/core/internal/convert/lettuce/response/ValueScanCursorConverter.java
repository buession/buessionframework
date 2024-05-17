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
import com.buession.redis.core.Tuple;
import com.buession.redis.core.internal.convert.response.ListConverter;
import io.lettuce.core.ScoredValueScanCursor;
import io.lettuce.core.ValueScanCursor;

import java.util.List;

/**
 * Lettuce {@link ValueScanCursor} 转换为 {@link ScanResult}
 *
 * @param <K>
 * 		Key 类型
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public final class ValueScanCursorConverter<K> implements Converter<ValueScanCursor<K>, ScanResult<List<K>>> {

	@Override
	public ScanResult<List<K>> convert(final ValueScanCursor<K> source) {
		return new ScanResult<>(source.getCursor(), source.getValues());
	}

	/**
	 * Lettuce {@link ValueScanCursor} 转换为 {@link ScanResult}
	 *
	 * @author Yong.Teng
	 * @since 3.0.0
	 */
	public final static class BSKeyScanCursorConverter
			implements Converter<ValueScanCursor<byte[]>, ScanResult<List<String>>> {

		private final ListConverter.BinaryToStringListConverter binaryToStringListConverter =
				new ListConverter.BinaryToStringListConverter();

		@Override
		public ScanResult<List<String>> convert(final ValueScanCursor<byte[]> source) {
			return new ScanResult<>(source.getCursor(), binaryToStringListConverter.convert(source.getValues()));
		}

	}

	/**
	 * Lettuce {@link ScoredValueScanCursor} 转换为 {@link ScanResult}
	 *
	 * @author Yong.Teng
	 * @since 3.0.0
	 */
	public final static class ScoredValueScanCursorConverter
			implements Converter<ScoredValueScanCursor<byte[]>, ScanResult<List<Tuple>>> {

		private final ScoredValueConverter.ListScoredValueConverter listScoredValueConverter =
				new ScoredValueConverter.ListScoredValueConverter();

		@Override
		public ScanResult<List<Tuple>> convert(final ScoredValueScanCursor<byte[]> source) {
			return new ScanResult<>(source.getCursor(), listScoredValueConverter.convert(source.getValues()));
		}

	}

}
