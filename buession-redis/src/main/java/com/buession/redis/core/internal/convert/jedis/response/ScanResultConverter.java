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
package com.buession.redis.core.internal.convert.jedis.response;

import com.buession.core.converter.Converter;
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.Tuple;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * {@link ScanResult} 和 jedis {@link redis.clients.jedis.resps.ScanResult} 互转
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
@FunctionalInterface
public interface ScanResultConverter<S, T> extends Converter<redis.clients.jedis.resps.ScanResult<S>, ScanResult<T>> {

	/**
	 * jedis {@link redis.clients.jedis.resps.ScanResult} 转换为 {@link java.util.List}&lt;ScanResult&gt;
	 *
	 * @author Yong.Teng
	 * @since 2.0.0
	 */
	final class ListScanResultConverter<S> implements ScanResultConverter<S, List<S>> {

		@Override
		public ScanResult<List<S>> convert(final redis.clients.jedis.resps.ScanResult<S> source) {
			return new ScanResult<>(source.getCursor(), source.getResult());
		}

	}

	/**
	 * jedis {@link redis.clients.jedis.resps.Tuple} 转换为 {@link java.util.List}&lt;T&gt;
	 *
	 * @author Yong.Teng
	 * @since 2.0.0
	 */
	final class ListTupleScanResultConverter
			implements ScanResultConverter<redis.clients.jedis.resps.Tuple, List<Tuple>> {

		private final TupleConverter.ListTupleConverter listTupleConverter = new TupleConverter.ListTupleConverter();

		@Override
		public ScanResult<List<Tuple>> convert(
				final redis.clients.jedis.resps.ScanResult<redis.clients.jedis.resps.Tuple> source) {
			return new com.buession.redis.core.ScanResult<>(source.getCursor(),
					listTupleConverter.convert(source.getResult()));
		}

	}

	/**
	 * jedis {@link redis.clients.jedis.resps.ScanResult}&lt;Map.Entry&lt;K&gt;, &lt;K&gt;&gt; 转换为 {@link ScanResult}
	 * &lt;Map&lt;K&gt;, &lt;K&gt;&gt;
	 *
	 * @param <K>
	 * 		Map Key 类型
	 * @param <V>
	 * 		Map 值类型
	 *
	 * @author Yong.Teng
	 * @since 2.0.0
	 */
	final class MapScanResultConverter<K, V> implements ScanResultConverter<Map.Entry<K, V>, Map<K, V>> {

		@Override
		public ScanResult<Map<K, V>> convert(final redis.clients.jedis.resps.ScanResult<Map.Entry<K, V>> source) {
			final Map<K, V> data = source.getResult().stream().collect(Collectors.toMap(Map.Entry::getKey,
					Map.Entry::getValue, (a, b)->a, LinkedHashMap::new));
			return new ScanResult<>(source.getCursorAsBytes(), data);
		}

	}

}
