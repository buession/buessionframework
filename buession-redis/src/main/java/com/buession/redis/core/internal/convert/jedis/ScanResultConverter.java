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
 * | Copyright @ 2013-2022 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.core.internal.convert.jedis;

import com.buession.core.converter.Converter;
import com.buession.core.converter.ListConverter;
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.Tuple;
import com.buession.redis.utils.SafeEncoder;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * {@link ScanResult} 和 jedis {@link redis.clients.jedis.ScanResult} 互转
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public interface ScanResultConverter<S, T> extends Converter<S, T> {

	/**
	 * {@link redis.clients.jedis.ScanResult}&lt;S&gt; 转换为 {@link ScanResult}&lt;T&gt;
	 *
	 * @param <S>
	 * 		原始类型，{@link redis.clients.jedis.ScanResult} 泛型参数
	 * @param <T>
	 * 		目标类型，{@link ScanResult} 泛型参数
	 *
	 * @author Yong.Teng
	 * @since 1.2.1
	 */
	interface ScanResultExposeConverter<S, T>
			extends ScanResultConverter<redis.clients.jedis.ScanResult<S>, ScanResult<T>> {

	}

	/**
	 * jedis {@link redis.clients.jedis.ScanResult} 转换为 {@link java.util.List}&lt;ScanResult&gt;
	 *
	 * @author Yong.Teng
	 * @since 2.0.0
	 */
	final class ListScanResultExposeConverter<S> implements ScanResultExposeConverter<S, List<S>> {

		@Override
		public ScanResult<List<S>> convert(final redis.clients.jedis.ScanResult<S> source){
			return new ScanResult<>(source.getCursor(), source.getResult());
		}

	}

	/**
	 * jedis {@link redis.clients.jedis.ScanResult} 转换为 {@link java.util.List}&lt;ScanResult&gt;
	 *
	 * @author Yong.Teng
	 * @since 2.0.0
	 */
	final class BinaryToStringListScanResultExposeConverter implements ScanResultExposeConverter<byte[], List<String>> {

		@Override
		public ScanResult<List<String>> convert(final redis.clients.jedis.ScanResult<byte[]> source){
			final List<String> results = source.getResult().stream().map(SafeEncoder::encode).collect(
					Collectors.toList());
			return new ScanResult<>(source.getCursor(), results);
		}

	}

	/**
	 * jedis {@link redis.clients.jedis.Tuple} 转换为 {@link java.util.List}&lt;T&gt;
	 *
	 * @author Yong.Teng
	 * @since 2.0.0
	 */
	final class ListTupleScanResultExposeConverter
			implements ScanResultExposeConverter<redis.clients.jedis.Tuple, List<Tuple>> {

		@Override
		public ScanResult<List<Tuple>> convert(final redis.clients.jedis.ScanResult<redis.clients.jedis.Tuple> source){
			final ListConverter<redis.clients.jedis.Tuple, Tuple> converter =
					new ListConverter<>((item)->new Tuple(item.getBinaryElement(), item.getScore()));
			return new com.buession.redis.core.ScanResult<>(source.getCursor(), converter.convert(source.getResult()));
		}

	}

	/**
	 * jedis {@link redis.clients.jedis.ScanResult}&lt;Map.Entry&lt;K&gt;, &lt;K&gt;&gt; 转换为 {@link ScanResult}
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
	final class MapScanResultExposeConverter<K, V> implements ScanResultExposeConverter<Map.Entry<K, V>, Map<K, V>> {

		@Override
		public ScanResult<Map<K, V>> convert(redis.clients.jedis.ScanResult<Map.Entry<K, V>> source){
			Map<K, V> data = source.getResult().stream().collect(Collectors.toMap(Map.Entry::getKey,
					Map.Entry::getValue, (a, b)->a, LinkedHashMap::new));
			return new ScanResult<>(source.getCursorAsBytes(), data);
		}

	}


}
