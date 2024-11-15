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
package com.buession.redis;

import com.buession.core.collect.Maps;
import com.buession.core.type.TypeReference;
import com.buession.redis.client.connection.RedisConnection;
import com.buession.redis.core.ScanResult;

import java.util.LinkedHashSet;
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
interface Converter<SV, TV> {

	TV convert(final RedisConnection connection, final SV value);

	abstract class AbstractConverter<SV, TV> implements Converter<SV, TV> {

		protected final ThreadLocal<Integer> index;

		public AbstractConverter() {
			this.index = RedisAccessor.index;
		}

		protected static <V> V addConverter(ThreadLocal<Integer> index, Function<String, V> function) {
			RedisAccessor.getTxConverters().put(index.get(), function);
			return null;
		}

		protected static <V> V addBinaryConverter(ThreadLocal<Integer> index, Function<byte[], V> function) {
			RedisAccessor.getTxConverters().put(index.get(), function);
			return null;
		}

		protected static <V> List<V> addListConverter(ThreadLocal<Integer> index,
													  Function<List<String>, List<V>> function) {
			RedisAccessor.getTxConverters().put(index.get(), function);
			return null;
		}

		protected static <V> List<V> addListBinaryConverter(ThreadLocal<Integer> index,
															Function<List<byte[]>, List<V>> function) {
			RedisAccessor.getTxConverters().put(index.get(), function);
			return null;
		}

		protected static <V> Set<V> addSetConverter(ThreadLocal<Integer> index,
													Function<Set<String>, Set<V>> function) {
			RedisAccessor.getTxConverters().put(index.get(), function);
			return null;
		}

		protected static <V> Set<V> addSetBinaryConverter(ThreadLocal<Integer> index,
														  Function<Set<byte[]>, Set<V>> function) {
			RedisAccessor.getTxConverters().put(index.get(), function);
			return null;
		}

		protected static <V> Map<String, V> addMapConverter(ThreadLocal<Integer> index,
															Function<Map<String, String>, Map<String, V>> function) {
			RedisAccessor.getTxConverters().put(index.get(), function);
			return null;
		}

		protected static <V> Map<byte[], V> addMapBinaryConverter(ThreadLocal<Integer> index,
																  Function<Map<byte[], byte[]>, Map<byte[], V>> function) {
			RedisAccessor.getTxConverters().put(index.get(), function);
			return null;
		}

		protected static <T, V> ScanResult<V> addScanResultConverter(ThreadLocal<Integer> index,
																	 Function<ScanResult<T>, ScanResult<V>> function) {
			RedisAccessor.getTxConverters().put(index.get(), function);
			return null;
		}

		protected boolean isTransaction(final RedisConnection connection) {
			return connection.isTransaction();
		}

		protected boolean isPipeline(final RedisConnection connection) {
			return connection.isPipeline();
		}

		protected boolean isTransactionOrPipeline(final RedisConnection connection) {
			return isTransaction(connection) || isPipeline(connection);
		}

	}

	abstract class AbstractStringConverter<V> extends AbstractConverter<String, V> {

		private final Function<String, V> call;

		public AbstractStringConverter(final Function<String, V> call) {
			super();
			this.call = call;
		}

		@Override
		public V convert(final RedisConnection connection, final String value) {
			if(isTransactionOrPipeline(connection)){
				return addConverter(index, call);
			}else{
				return call.apply(value);
			}
		}

	}

	abstract class AbstractBinaryConverter<V> extends AbstractConverter<byte[], V> {

		private final Function<byte[], V> call;

		public AbstractBinaryConverter(final Function<byte[], V> call) {
			super();
			this.call = call;
		}

		@Override
		public V convert(final RedisConnection connection, final byte[] value) {
			if(isTransactionOrPipeline(connection)){
				return addBinaryConverter(index, call);
			}else{
				return call.apply(value);
			}
		}

	}

	final class SimpleStringConverter<V> extends AbstractStringConverter<V> {

		public SimpleStringConverter(final RedisAccessor accessor) {
			super(accessor.serializer::deserialize);
		}

	}

	final class SimpleBinaryConverter<V> extends AbstractBinaryConverter<V> {

		public SimpleBinaryConverter(final RedisAccessor accessor) {
			super(accessor.serializer::deserializeBytes);
		}

	}

	final class ClazzStringConverter<V> extends AbstractStringConverter<V> {

		public ClazzStringConverter(final RedisAccessor accessor, final Class<V> clazz) {
			super((val)->accessor.serializer.deserialize(val, clazz));
		}

	}

	final class ClazzBinaryConverter<V> extends AbstractBinaryConverter<V> {

		public ClazzBinaryConverter(final RedisAccessor accessor, final Class<V> clazz) {
			super((val)->accessor.serializer.deserializeBytes(val, clazz));
		}

	}

	final class TypeStringConverter<V> extends AbstractStringConverter<V> {

		public TypeStringConverter(final RedisAccessor accessor, final TypeReference<V> typeReference) {
			super((val)->accessor.serializer.deserialize(val, typeReference));
		}

	}

	final class TypeBinaryConverter<V> extends AbstractBinaryConverter<V> {

		public TypeBinaryConverter(final RedisAccessor accessor, final TypeReference<V> typeReference) {
			super((val)->accessor.serializer.deserializeBytes(val, typeReference));
		}

	}

	abstract class AbstractListStringConverter<V> extends AbstractConverter<List<String>, List<V>> {

		private final Function<String, V> call;

		public AbstractListStringConverter(final Function<String, V> call) {
			super();
			this.call = call;
		}

		@Override
		public List<V> convert(final RedisConnection connection, final List<String> value) {
			final Function<List<String>, List<V>> function = (data)->data == null ? null :
					data.stream().map(call).collect(Collectors.toList());

			if(isTransactionOrPipeline(connection)){
				return addListConverter(index, function);
			}else{
				return function.apply(value);
			}
		}

	}

	abstract class AbstractListBinaryConverter<V> extends AbstractConverter<List<byte[]>, List<V>> {

		private final Function<byte[], V> call;

		public AbstractListBinaryConverter(final Function<byte[], V> call) {
			super();
			this.call = call;
		}

		@Override
		public List<V> convert(final RedisConnection connection, final List<byte[]> value) {
			final Function<List<byte[]>, List<V>> function = (data)->data == null ? null :
					data.stream().map(call).collect(Collectors.toList());

			if(isTransactionOrPipeline(connection)){
				return addListBinaryConverter(index, function);
			}else{
				return function.apply(value);
			}
		}

	}

	final class SimpleListStringConverter<V> extends AbstractListStringConverter<V> {

		public SimpleListStringConverter(final RedisAccessor accessor) {
			super(accessor.serializer::deserialize);
		}

	}

	final class SimpleListBinaryConverter<V> extends AbstractListBinaryConverter<V> {

		public SimpleListBinaryConverter(final RedisAccessor accessor) {
			super(accessor.serializer::deserializeBytes);
		}

	}

	final class ClazzListStringConverter<V> extends AbstractListStringConverter<V> {

		public ClazzListStringConverter(final RedisAccessor accessor, final Class<V> clazz) {
			super((val)->accessor.serializer.deserialize(val, clazz));
		}

	}

	final class ClazzListBinaryConverter<V> extends AbstractListBinaryConverter<V> {

		public ClazzListBinaryConverter(final RedisAccessor accessor, final Class<V> clazz) {
			super((val)->accessor.serializer.deserializeBytes(val, clazz));
		}

	}

	final class TypeListStringConverter<V> extends AbstractListStringConverter<V> {

		public TypeListStringConverter(final RedisAccessor accessor, final TypeReference<V> typeReference) {
			super((val)->accessor.serializer.deserialize(val, typeReference));
		}

	}

	final class TypeListBinaryConverter<V> extends AbstractListBinaryConverter<V> {

		public TypeListBinaryConverter(final RedisAccessor accessor, final TypeReference<V> typeReference) {
			super((val)->accessor.serializer.deserializeBytes(val, typeReference));
		}

	}

	abstract class AbstractSetStringConverter<V> extends AbstractConverter<Set<String>, Set<V>> {

		private final Function<String, V> call;

		public AbstractSetStringConverter(final Function<String, V> call) {
			super();
			this.call = call;
		}

		@Override
		public Set<V> convert(final RedisConnection connection, final Set<String> value) {
			final Function<Set<String>, Set<V>> function = (data)->data == null ? null :
					data.stream().map(call).collect(Collectors.toCollection(LinkedHashSet::new));

			if(isTransactionOrPipeline(connection)){
				return addSetConverter(index, function);
			}else{
				return function.apply(value);
			}
		}

	}

	abstract class AbstractSetBinaryConverter<V> extends AbstractConverter<Set<byte[]>, Set<V>> {

		private final Function<byte[], V> call;

		public AbstractSetBinaryConverter(final Function<byte[], V> call) {
			super();
			this.call = call;
		}

		@Override
		public Set<V> convert(final RedisConnection connection, final Set<byte[]> value) {
			final Function<Set<byte[]>, Set<V>> function = (data)->data == null ? null :
					data.stream().map(call).collect(Collectors.toCollection(LinkedHashSet::new));

			if(isTransactionOrPipeline(connection)){
				return addSetBinaryConverter(index, function);
			}else{
				return function.apply(value);
			}
		}

	}

	final class SimpleSetStringConverter<V> extends AbstractSetStringConverter<V> {

		public SimpleSetStringConverter(final RedisAccessor accessor) {
			super(accessor.serializer::deserialize);
		}

	}

	final class SimpleSetBinaryConverter<V> extends AbstractSetBinaryConverter<V> {

		public SimpleSetBinaryConverter(final RedisAccessor accessor) {
			super(accessor.serializer::deserializeBytes);
		}

	}

	final class ClazzSetStringConverter<V> extends AbstractSetStringConverter<V> {

		public ClazzSetStringConverter(final RedisAccessor accessor, final Class<V> clazz) {
			super((val)->accessor.serializer.deserialize(val, clazz));
		}

	}

	final class ClazzSetBinaryConverter<V> extends AbstractSetBinaryConverter<V> {

		public ClazzSetBinaryConverter(final RedisAccessor accessor, final Class<V> clazz) {
			super((val)->accessor.serializer.deserializeBytes(val, clazz));
		}

	}

	final class TypeSetStringConverter<V> extends AbstractSetStringConverter<V> {

		public TypeSetStringConverter(final RedisAccessor accessor, final TypeReference<V> typeReference) {
			super((val)->accessor.serializer.deserialize(val, typeReference));
		}

	}

	final class TypeSetBinaryConverter<V> extends AbstractSetBinaryConverter<V> {

		public TypeSetBinaryConverter(final RedisAccessor accessor, final TypeReference<V> typeReference) {
			super((val)->accessor.serializer.deserializeBytes(val, typeReference));
		}

	}

	abstract class AbstractMapStringConverter<V> extends AbstractConverter<Map<String, String>, Map<String, V>> {

		private final Function<String, V> call;

		public AbstractMapStringConverter(final Function<String, V> call) {
			super();
			this.call = call;
		}

		@Override
		public Map<String, V> convert(final RedisConnection connection, final Map<String, String> value) {
			final Function<Map<String, String>, Map<String, V>> function = (data)->data == null ? null :
					Maps.map(data, (key)->key, call);

			if(isTransactionOrPipeline(connection)){
				return addMapConverter(index, function);
			}else{
				return function.apply(value);
			}
		}

	}

	abstract class AbstractMapBinaryConverter<V> extends AbstractConverter<Map<byte[], byte[]>, Map<byte[], V>> {

		private final Function<byte[], V> call;

		public AbstractMapBinaryConverter(final Function<byte[], V> call) {
			super();
			this.call = call;
		}

		@Override
		public Map<byte[], V> convert(final RedisConnection connection, final Map<byte[], byte[]> value) {
			final Function<Map<byte[], byte[]>, Map<byte[], V>> function = (data)->data == null ? null :
					Maps.map(data, (key)->key, call);

			if(isTransactionOrPipeline(connection)){
				return addMapBinaryConverter(index, function);
			}else{
				return function.apply(value);
			}
		}

	}

	final class SimpleMapStringConverter<V> extends AbstractMapStringConverter<V> {

		public SimpleMapStringConverter(final RedisAccessor accessor) {
			super(accessor.serializer::deserialize);
		}

	}

	final class SimpleMapBinaryConverter<V> extends AbstractMapBinaryConverter<V> {

		public SimpleMapBinaryConverter(final RedisAccessor accessor) {
			super(accessor.serializer::deserializeBytes);
		}

	}

	final class ClazzMapStringConverter<V> extends AbstractMapStringConverter<V> {

		public ClazzMapStringConverter(final RedisAccessor accessor, final Class<V> clazz) {
			super((val)->accessor.serializer.deserialize(val, clazz));
		}

	}

	final class ClazzMapBinaryConverter<V> extends AbstractMapBinaryConverter<V> {

		public ClazzMapBinaryConverter(final RedisAccessor accessor, final Class<V> clazz) {
			super((val)->accessor.serializer.deserializeBytes(val, clazz));
		}

	}

	final class TypeMapStringConverter<V> extends AbstractMapStringConverter<V> {

		public TypeMapStringConverter(final RedisAccessor accessor, final TypeReference<V> typeReference) {
			super((val)->accessor.serializer.deserialize(val, typeReference));
		}

	}

	final class TypeMapBinaryConverter<V> extends AbstractMapBinaryConverter<V> {

		public TypeMapBinaryConverter(final RedisAccessor accessor, final TypeReference<V> typeReference) {
			super((val)->accessor.serializer.deserializeBytes(val, typeReference));
		}

	}

	abstract class AbstractScanResultConverter<SV, TV> extends AbstractConverter<ScanResult<SV>, ScanResult<TV>> {

		private final Function<SV, TV> call;

		public AbstractScanResultConverter(final Function<SV, TV> call) {
			super();
			this.call = call;
		}

		@Override
		public ScanResult<TV> convert(final RedisConnection connection, final ScanResult<SV> value) {
			final Function<ScanResult<SV>, ScanResult<TV>> function = (scanResult)->new ScanResult<>(
					scanResult.getCursor(), scanResult.getResults() == null ? null :
					call.apply(scanResult.getResults()));

			if(isTransactionOrPipeline(connection)){
				return addScanResultConverter(index, function);
			}else{
				return function.apply(value);
			}
		}

	}

	abstract class AbstractScanResultMapStringConverter<V>
			extends AbstractScanResultConverter<Map<String, String>, Map<String, V>> {

		public AbstractScanResultMapStringConverter(final Function<String, V> call) {
			super((value)->Maps.map(value, (key)->key, call));
		}

	}

	abstract class AbstractScanResultMapBinaryConverter<V>
			extends AbstractScanResultConverter<Map<byte[], byte[]>, Map<byte[], V>> {

		public AbstractScanResultMapBinaryConverter(final Function<byte[], V> call) {
			super((value)->Maps.map(value, (key)->key, call));
		}

	}

	abstract class AbstractScanResultListStringConverter<V> extends AbstractScanResultConverter<List<String>, List<V>> {

		public AbstractScanResultListStringConverter(final Function<String, V> call) {
			super((value)->value.stream().map(call).collect(Collectors.toList()));
		}

	}

	abstract class AbstractScanResultListBinaryConverter<V> extends AbstractScanResultConverter<List<byte[]>, List<V>> {

		public AbstractScanResultListBinaryConverter(final Function<byte[], V> call) {
			super((value)->value.stream().map(call).collect(Collectors.toList()));
		}

	}

	final class SimpleScanResultListStringConverter<V> extends AbstractScanResultListStringConverter<V> {

		public SimpleScanResultListStringConverter(final RedisAccessor accessor) {
			super(accessor.serializer::deserialize);
		}

	}

	final class SimpleScanResultListBinaryConverter<V> extends AbstractScanResultListBinaryConverter<V> {

		public SimpleScanResultListBinaryConverter(final RedisAccessor accessor) {
			super(accessor.serializer::deserializeBytes);
		}

	}

	final class ClazzScanResultListStringConverter<V> extends AbstractScanResultListStringConverter<V> {

		public ClazzScanResultListStringConverter(final RedisAccessor accessor, final Class<V> clazz) {
			super((val)->accessor.serializer.deserialize(val, clazz));
		}

	}

	final class ClazzScanResultListBinaryConverter<V> extends AbstractScanResultListBinaryConverter<V> {

		public ClazzScanResultListBinaryConverter(final RedisAccessor accessor, final Class<V> clazz) {
			super((val)->accessor.serializer.deserializeBytes(val, clazz));
		}

	}

	final class TypeScanResultListStringConverter<V> extends AbstractScanResultListStringConverter<V> {

		public TypeScanResultListStringConverter(final RedisAccessor accessor, final TypeReference<V> typeReference) {
			super((val)->accessor.serializer.deserialize(val, typeReference));
		}

	}

	final class TypeScanResultListBinaryConverter<V> extends AbstractScanResultListBinaryConverter<V> {

		public TypeScanResultListBinaryConverter(final RedisAccessor accessor, final TypeReference<V> typeReference) {
			super((val)->accessor.serializer.deserializeBytes(val, typeReference));
		}

	}

	final class SimpleScanResultMapStringConverter<V> extends AbstractScanResultMapStringConverter<V> {

		public SimpleScanResultMapStringConverter(final RedisAccessor accessor) {
			super(accessor.serializer::deserialize);
		}

	}

	final class SimpleScanResultMapBinaryConverter<V> extends AbstractScanResultMapBinaryConverter<V> {

		public SimpleScanResultMapBinaryConverter(final RedisAccessor accessor) {
			super(accessor.serializer::deserializeBytes);
		}

	}

	final class ClazzScanResultMapStringConverter<V> extends AbstractScanResultMapStringConverter<V> {

		public ClazzScanResultMapStringConverter(final RedisAccessor accessor, final Class<V> clazz) {
			super((val)->accessor.serializer.deserialize(val, clazz));
		}

	}

	final class ClazzScanResultMapBinaryConverter<V> extends AbstractScanResultMapBinaryConverter<V> {

		public ClazzScanResultMapBinaryConverter(final RedisAccessor accessor, final Class<V> clazz) {
			super((val)->accessor.serializer.deserializeBytes(val, clazz));
		}

	}

	final class TypeScanResultMapStringConverter<V> extends AbstractScanResultMapStringConverter<V> {

		public TypeScanResultMapStringConverter(final RedisAccessor accessor, final TypeReference<V> typeReference) {
			super((val)->accessor.serializer.deserialize(val, typeReference));
		}

	}

	final class TypeScanResultMapBinaryConverter<V> extends AbstractScanResultMapBinaryConverter<V> {

		public TypeScanResultMapBinaryConverter(final RedisAccessor accessor, final TypeReference<V> typeReference) {
			super((val)->accessor.serializer.deserializeBytes(val, typeReference));
		}

	}

}
