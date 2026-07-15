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
package com.buession.redis;

import com.buession.core.collect.Maps;
import com.buession.core.converter.ListConverter;
import com.buession.core.type.TypeReference;
import com.buession.lang.KeyValue;
import com.buession.redis.client.connection.RedisConnection;
import com.buession.redis.core.ScanResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Yong.Teng
 * @since 2.0.0
 */
@FunctionalInterface
public interface Converter<SV, TV> {

	TV convert(final RedisConnection connection, final SV value);

	abstract class AbstractConverter<SV, TV> implements Converter<SV, TV> {

		private final Function<SV, TV> call;

		AbstractConverter(final Function<SV, TV> call) {
			this.call = call;
		}

		@Override
		public TV convert(final RedisConnection connection, final SV value) {
			return call.apply(value);
		}

	}

	/**
	 * 简单对象转换开始
	 ***/
	abstract class AbstractStringConverter<V> extends AbstractConverter<String, V> {

		AbstractStringConverter(final Function<String, V> call) {
			super(call);
		}

	}

	abstract class AbstractBinaryConverter<V> extends AbstractConverter<byte[], V> {

		AbstractBinaryConverter(final Function<byte[], V> call) {
			super(call);
		}

	}

	final class ClazzStringConverter<V> extends AbstractStringConverter<V> {

		ClazzStringConverter(final RedisAccessor accessor, final Class<V> clazz) {
			super((val)->accessor.serializer.deserialize(val, clazz));
		}

	}

	final class ClazzBinaryConverter<V> extends AbstractBinaryConverter<V> {

		ClazzBinaryConverter(final RedisAccessor accessor, final Class<V> clazz) {
			super((val)->accessor.serializer.deserializeBytes(val, clazz));
		}

	}

	final class TypeStringConverter<V> extends AbstractStringConverter<V> {

		TypeStringConverter(final RedisAccessor accessor, final TypeReference<V> typeReference) {
			super((val)->accessor.serializer.deserialize(val, typeReference));
		}

	}

	final class TypeBinaryConverter<V> extends AbstractBinaryConverter<V> {

		TypeBinaryConverter(final RedisAccessor accessor, final TypeReference<V> typeReference) {
			super((val)->accessor.serializer.deserializeBytes(val, typeReference));
		}

	}

	/**
	 * 简单对象转换结束
	 ***/

	abstract class AbstractListStringConverter<V> extends AbstractConverter<List<String>, List<V>> {

		AbstractListStringConverter(final Function<String, V> call) {
			super((v)->v == null ? null : v.stream().map(call).collect(Collectors.toList()));
		}

	}

	abstract class AbstractListBinaryConverter<V> extends AbstractConverter<List<byte[]>, List<V>> {

		AbstractListBinaryConverter(final Function<byte[], V> call) {
			super((v)->v == null ? null : v.stream().map(call).collect(Collectors.toList()));
		}

	}

	final class ClazzListStringConverter<V> extends AbstractListStringConverter<V> {

		ClazzListStringConverter(final RedisAccessor accessor, final Class<V> clazz) {
			super((val)->accessor.serializer.deserialize(val, clazz));
		}

	}

	final class ClazzListBinaryConverter<V> extends AbstractListBinaryConverter<V> {

		ClazzListBinaryConverter(final RedisAccessor accessor, final Class<V> clazz) {
			super((val)->accessor.serializer.deserializeBytes(val, clazz));
		}

	}

	final class TypeListStringConverter<V> extends AbstractListStringConverter<V> {

		TypeListStringConverter(final RedisAccessor accessor, final TypeReference<V> typeReference) {
			super((val)->accessor.serializer.deserialize(val, typeReference));
		}

	}

	final class TypeListBinaryConverter<V> extends AbstractListBinaryConverter<V> {

		TypeListBinaryConverter(final RedisAccessor accessor, final TypeReference<V> typeReference) {
			super((val)->accessor.serializer.deserializeBytes(val, typeReference));
		}

	}

	abstract class AbstractSetStringConverter<V> extends AbstractConverter<Set<String>, Set<V>> {

		AbstractSetStringConverter(final Function<String, V> call) {
			super((v)->v == null ? null : v.stream().map(call).collect(Collectors.toSet()));
		}

	}

	abstract class AbstractSetBinaryConverter<V> extends AbstractConverter<Set<byte[]>, Set<V>> {

		AbstractSetBinaryConverter(final Function<byte[], V> call) {
			super((v)->v == null ? null : v.stream().map(call).collect(Collectors.toSet()));
		}

	}

	final class ClazzSetStringConverter<V> extends AbstractSetStringConverter<V> {

		ClazzSetStringConverter(final RedisAccessor accessor, final Class<V> clazz) {
			super((val)->accessor.serializer.deserialize(val, clazz));
		}

	}

	final class ClazzSetBinaryConverter<V> extends AbstractSetBinaryConverter<V> {

		ClazzSetBinaryConverter(final RedisAccessor accessor, final Class<V> clazz) {
			super((val)->accessor.serializer.deserializeBytes(val, clazz));
		}

	}

	final class TypeSetStringConverter<V> extends AbstractSetStringConverter<V> {

		TypeSetStringConverter(final RedisAccessor accessor, final TypeReference<V> typeReference) {
			super((val)->accessor.serializer.deserialize(val, typeReference));
		}

	}

	final class TypeSetBinaryConverter<V> extends AbstractSetBinaryConverter<V> {

		TypeSetBinaryConverter(final RedisAccessor accessor, final TypeReference<V> typeReference) {
			super((val)->accessor.serializer.deserializeBytes(val, typeReference));
		}

	}

	abstract class AbstractMapStringConverter<V> extends AbstractConverter<Map<String, String>, Map<String, V>> {

		AbstractMapStringConverter(final Function<String, V> call) {
			super((data)->data == null ? null : Maps.map(data, (key)->key, call));
		}

	}

	abstract class AbstractMapBinaryConverter<V> extends AbstractConverter<Map<byte[], byte[]>, Map<byte[], V>> {

		AbstractMapBinaryConverter(final Function<byte[], V> call) {
			super((data)->data == null ? null : Maps.map(data, (key)->key, call));
		}

	}

	final class ClazzMapStringConverter<V> extends AbstractMapStringConverter<V> {

		ClazzMapStringConverter(final RedisAccessor accessor, final Class<V> clazz) {
			super((val)->accessor.serializer.deserialize(val, clazz));
		}

	}

	final class ClazzMapBinaryConverter<V> extends AbstractMapBinaryConverter<V> {

		ClazzMapBinaryConverter(final RedisAccessor accessor, final Class<V> clazz) {
			super((val)->accessor.serializer.deserializeBytes(val, clazz));
		}

	}

	final class TypeMapStringConverter<V> extends AbstractMapStringConverter<V> {

		TypeMapStringConverter(final RedisAccessor accessor, final TypeReference<V> typeReference) {
			super((val)->accessor.serializer.deserialize(val, typeReference));
		}

	}

	final class TypeMapBinaryConverter<V> extends AbstractMapBinaryConverter<V> {

		TypeMapBinaryConverter(final RedisAccessor accessor, final TypeReference<V> typeReference) {
			super((val)->accessor.serializer.deserializeBytes(val, typeReference));
		}

	}

	abstract class AbstractScanResultConverter<SV, TV> extends AbstractConverter<ScanResult<SV>, ScanResult<TV>> {

		AbstractScanResultConverter(final Function<SV, TV> call) {
			super((scanResult)->new ScanResult<>(scanResult.getCursor(),
					convertResults(call, scanResult.getResults())));
		}

		protected static <SV, TV> List<TV> convertResults(final Function<SV, TV> call, final List<SV> results) {
			if(results == null){
				return null;
			}else if(results.isEmpty()){
				return new ArrayList<>();
			}else{
				final ListConverter<SV, TV> converter = new ListConverter<>(call::apply);
				return converter.convert(results);
			}
		}

	}

	abstract class AbstractScanResultMapStringConverter<V>
			extends AbstractScanResultConverter<KeyValue<String, String>, KeyValue<String, V>> {

		AbstractScanResultMapStringConverter(final Function<String, V> call) {
			super((value)->new KeyValue<>(value.getKey(), call.apply(value.getValue())));
		}

	}

	abstract class AbstractScanResultMapBinaryConverter<V>
			extends AbstractScanResultConverter<KeyValue<byte[], byte[]>, KeyValue<byte[], V>> {

		AbstractScanResultMapBinaryConverter(final Function<byte[], V> call) {
			super((value)->new KeyValue<>(value.getKey(), call.apply(value.getValue())));
		}

	}

	abstract class AbstractScanResultListStringConverter<V> extends AbstractScanResultConverter<String, V> {

		AbstractScanResultListStringConverter(final Function<String, V> call) {
			super(call);
		}

	}

	abstract class AbstractScanResultListBinaryConverter<V> extends AbstractScanResultConverter<byte[], V> {

		AbstractScanResultListBinaryConverter(final Function<byte[], V> call) {
			super(call);
		}

	}

	final class ClazzScanResultListStringConverter<V> extends AbstractScanResultListStringConverter<V> {

		ClazzScanResultListStringConverter(final RedisAccessor accessor, final Class<V> clazz) {
			super((val)->accessor.serializer.deserialize(val, clazz));
		}

	}

	final class ClazzScanResultListBinaryConverter<V> extends AbstractScanResultListBinaryConverter<V> {

		ClazzScanResultListBinaryConverter(final RedisAccessor accessor, final Class<V> clazz) {
			super((val)->accessor.serializer.deserializeBytes(val, clazz));
		}

	}

	final class TypeScanResultListStringConverter<V> extends AbstractScanResultListStringConverter<V> {

		TypeScanResultListStringConverter(final RedisAccessor accessor, final TypeReference<V> typeReference) {
			super((val)->accessor.serializer.deserialize(val, typeReference));
		}

	}

	final class TypeScanResultListBinaryConverter<V> extends AbstractScanResultListBinaryConverter<V> {

		TypeScanResultListBinaryConverter(final RedisAccessor accessor, final TypeReference<V> typeReference) {
			super((val)->accessor.serializer.deserializeBytes(val, typeReference));
		}

	}

	final class ClazzScanResultStringConverter<V> extends AbstractScanResultMapStringConverter<V> {

		ClazzScanResultStringConverter(final RedisAccessor accessor, final Class<V> clazz) {
			super((val)->accessor.serializer.deserialize(val, clazz));
		}

	}

	final class ClazzScanResultBinaryConverter<V> extends AbstractScanResultMapBinaryConverter<V> {

		ClazzScanResultBinaryConverter(final RedisAccessor accessor, final Class<V> clazz) {
			super((val)->accessor.serializer.deserializeBytes(val, clazz));
		}

	}

	final class TypeScanResultStringConverter<V> extends AbstractScanResultMapStringConverter<V> {

		TypeScanResultStringConverter(final RedisAccessor accessor, final TypeReference<V> typeReference) {
			super((val)->accessor.serializer.deserialize(val, typeReference));
		}

	}

	final class TypeScanResultBinaryConverter<V> extends AbstractScanResultMapBinaryConverter<V> {

		TypeScanResultBinaryConverter(final RedisAccessor accessor, final TypeReference<V> typeReference) {
			super((val)->accessor.serializer.deserializeBytes(val, typeReference));
		}

	}

	abstract class AbstractKeyValueConverter<K, SV, TV> extends AbstractConverter<KeyValue<K, SV>, KeyValue<K, TV>> {

		AbstractKeyValueConverter(final Function<SV, TV> call) {
			super((v)->new KeyValue<>(v.getKey(), call.apply(v.getValue())));
		}

		protected static <SSV, TTV> Function<List<SSV>, List<TTV>> initialize(final Function<SSV, TTV> mapper) {
			return (data)->data == null ? null : data.stream().map(mapper).collect(Collectors.toList());
		}

	}

	final class ClazzListKeyValueStringConverter<V> extends AbstractKeyValueConverter<String, List<String>, List<V>> {

		ClazzListKeyValueStringConverter(final RedisAccessor accessor, final Class<V> clazz) {
			super(initialize((val)->accessor.serializer.deserialize(val, clazz)));
		}

	}

	final class ClazzListKeyValueBinaryConverter<V> extends AbstractKeyValueConverter<byte[], List<byte[]>, List<V>> {

		ClazzListKeyValueBinaryConverter(final RedisAccessor accessor, final Class<V> clazz) {
			super(initialize((val)->accessor.serializer.deserializeBytes(val, clazz)));
		}

	}

	final class TypeListKeyValueStringConverter<V> extends AbstractKeyValueConverter<String, List<String>, List<V>> {

		TypeListKeyValueStringConverter(final RedisAccessor accessor, final TypeReference<V> typeReference) {
			super(initialize((val)->accessor.serializer.deserialize(val, typeReference)));
		}

	}

	final class TypeListKeyValueBinaryConverter<V> extends AbstractKeyValueConverter<byte[], List<byte[]>, List<V>> {

		TypeListKeyValueBinaryConverter(final RedisAccessor accessor, final TypeReference<V> typeReference) {
			super(initialize((val)->accessor.serializer.deserializeBytes(val, typeReference)));
		}

	}

}
