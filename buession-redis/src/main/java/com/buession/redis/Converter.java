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
package com.buession.redis;

import com.buession.core.collect.Maps;
import com.buession.core.serializer.type.TypeReference;
import com.buession.redis.client.connection.RedisConnection;
import com.buession.redis.core.ScanResult;
import com.buession.redis.serializer.Serializer;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Yong.Teng
 * @since 2.0.0
 */
interface Converter<SV, TV> {

	TV convert(final RedisConnection connection, final SV value);

	abstract class AbstractConverter<SV, TV> implements Converter<SV, TV> {

		protected final AtomicInteger index;

		protected final Serializer serializer;

		public AbstractConverter(final RedisAccessor accessor){
			this.index = accessor.index;
			this.serializer = accessor.serializer;
		}

		protected static <V> V addConverter(AtomicInteger index, Function<String, V> function){
			RedisAccessor.getTxConverters().put(index.get(), function);
			return null;
		}

		protected static <V> V addBinaryConverter(AtomicInteger index, Function<byte[], V> function){
			RedisAccessor.getTxConverters().put(index.get(), function);
			return null;
		}

		protected static <V> List<V> addListConverter(AtomicInteger index, Function<List<String>, List<V>> function){
			RedisAccessor.getTxConverters().put(index.get(), function);
			return null;
		}

		protected static <V> List<V> addListBinaryConverter(AtomicInteger index,
															Function<List<byte[]>, List<V>> function){
			RedisAccessor.getTxConverters().put(index.get(), function);
			return null;
		}

		protected static <V> Set<V> addSetConverter(AtomicInteger index, Function<Set<String>, Set<V>> function){
			RedisAccessor.getTxConverters().put(index.get(), function);
			return null;
		}

		protected static <V> Set<V> addSetBinaryConverter(AtomicInteger index, Function<Set<byte[]>, Set<V>> function){
			RedisAccessor.getTxConverters().put(index.get(), function);
			return null;
		}

		protected static <V> Map<String, V> addMapConverter(AtomicInteger index,
															Function<Map<String, String>, Map<String, V>> function){
			RedisAccessor.getTxConverters().put(index.get(), function);
			return null;
		}

		protected static <V> Map<byte[], V> addMapBinaryConverter(AtomicInteger index,
																  Function<Map<byte[], byte[]>, Map<byte[], V>> function){
			RedisAccessor.getTxConverters().put(index.get(), function);
			return null;
		}

		protected static <T, V> ScanResult<V> addScanResultConverter(AtomicInteger index,
																	 Function<ScanResult<T>, ScanResult<V>> function){
			RedisAccessor.getTxConverters().put(index.get(), function);
			return null;
		}

		protected boolean isTransaction(final RedisConnection connection){
			return connection.isTransaction();
		}

		protected boolean isPipeline(final RedisConnection connection){
			return connection.isPipeline();
		}

		protected boolean isTransactionOrPipeline(final RedisConnection connection){
			return isTransaction(connection) || isPipeline(connection);
		}

	}

	abstract class AbstractStringConverter<V> extends AbstractConverter<String, V> {

		public AbstractStringConverter(final RedisAccessor accessor){
			super(accessor);
		}

	}

	abstract class AbstractBinaryConverter<V> extends AbstractConverter<byte[], V> {

		public AbstractBinaryConverter(final RedisAccessor accessor){
			super(accessor);
		}

	}

	final class SimpleStringConverter<V> extends AbstractStringConverter<V> {

		public SimpleStringConverter(final RedisAccessor accessor){
			super(accessor);
		}

		@Override
		public V convert(final RedisConnection connection, final String value){
			if(isTransactionOrPipeline(connection)){
				return addConverter(index, serializer::deserialize);
			}else{
				return serializer.deserialize(value);
			}
		}

	}

	final class SimpleBinaryConverter<V> extends AbstractBinaryConverter<V> {

		public SimpleBinaryConverter(final RedisAccessor accessor){
			super(accessor);
		}

		@Override
		public V convert(final RedisConnection connection, final byte[] value){
			if(isTransactionOrPipeline(connection)){
				return addBinaryConverter(index, serializer::deserializeBytes);
			}else{
				return serializer.deserializeBytes(value);
			}
		}

	}

	final class ClazzStringConverter<V> extends AbstractStringConverter<V> {

		private final Class<V> clazz;

		public ClazzStringConverter(final RedisAccessor accessor, final Class<V> clazz){
			super(accessor);
			this.clazz = clazz;
		}

		@Override
		public V convert(final RedisConnection connection, final String value){
			if(isTransactionOrPipeline(connection)){
				return addConverter(index, (val)->serializer.deserialize(val, clazz));
			}else{
				return serializer.deserialize(value, clazz);
			}
		}

	}

	final class ClazzBinaryConverter<V> extends AbstractBinaryConverter<V> {

		private final Class<V> clazz;

		public ClazzBinaryConverter(final RedisAccessor accessor, final Class<V> clazz){
			super(accessor);
			this.clazz = clazz;
		}

		@Override
		public V convert(final RedisConnection connection, final byte[] value){
			if(isTransactionOrPipeline(connection)){
				return addBinaryConverter(index, (val)->serializer.deserializeBytes(val, clazz));
			}else{
				return serializer.deserializeBytes(value, clazz);
			}
		}

	}

	final class TypeStringConverter<V> extends AbstractStringConverter<V> {

		private final TypeReference<V> typeReference;

		public TypeStringConverter(final RedisAccessor accessor, final TypeReference<V> typeReference){
			super(accessor);
			this.typeReference = typeReference;
		}

		@Override
		public V convert(final RedisConnection connection, final String value){
			if(isTransactionOrPipeline(connection)){
				return addConverter(index, (val)->serializer.deserialize(val, typeReference));
			}else{
				return serializer.deserialize(value, typeReference);
			}
		}

	}

	final class TypeBinaryConverter<V> extends AbstractBinaryConverter<V> {

		private final TypeReference<V> typeReference;

		public TypeBinaryConverter(final RedisAccessor accessor, final TypeReference<V> typeReference){
			super(accessor);
			this.typeReference = typeReference;
		}

		@Override
		public V convert(final RedisConnection connection, final byte[] value){
			if(isTransactionOrPipeline(connection)){
				return addBinaryConverter(index, (val)->serializer.deserializeBytes(val, typeReference));
			}else{
				return serializer.deserializeBytes(value, typeReference);
			}
		}

	}

	abstract class AbstractListStringConverter<V> extends AbstractConverter<List<String>, List<V>> {

		public AbstractListStringConverter(final RedisAccessor accessor){
			super(accessor);
		}

	}

	abstract class AbstractListBinaryConverter<V> extends AbstractConverter<List<byte[]>, List<V>> {

		public AbstractListBinaryConverter(final RedisAccessor accessor){
			super(accessor);
		}

	}

	final class SimpleListStringConverter<V> extends AbstractListStringConverter<V> {

		public SimpleListStringConverter(final RedisAccessor accessor){
			super(accessor);
		}

		@Override
		public List<V> convert(final RedisConnection connection, final List<String> value){
			final Function<List<String>, List<V>> function = (data)->{
				if(data != null){
					final List<V> result = new ArrayList<>(data.size());

					for(String s : data){
						result.add(serializer.deserialize(s));
					}

					return result;
				}

				return null;
			};

			if(isTransactionOrPipeline(connection)){
				return addListConverter(index, function);
			}else{
				return function.apply(value);
			}
		}

	}

	final class SimpleListBinaryConverter<V> extends AbstractListBinaryConverter<V> {

		public SimpleListBinaryConverter(final RedisAccessor accessor){
			super(accessor);
		}

		@Override
		public List<V> convert(final RedisConnection connection, final List<byte[]> value){
			final Function<List<byte[]>, List<V>> function = (data)->{
				if(data != null){
					final List<V> result = new ArrayList<>(data.size());

					for(byte[] b : data){
						result.add(serializer.deserializeBytes(b));
					}

					return result;
				}

				return null;
			};

			if(isTransactionOrPipeline(connection)){
				return addListBinaryConverter(index, function);
			}else{
				return function.apply(value);
			}
		}

	}

	final class ClazzListStringConverter<V> extends AbstractListStringConverter<V> {

		private final Class<V> clazz;

		public ClazzListStringConverter(final RedisAccessor accessor, final Class<V> clazz){
			super(accessor);
			this.clazz = clazz;
		}

		@Override
		public List<V> convert(final RedisConnection connection, final List<String> value){
			final Function<List<String>, List<V>> function = (data)->
					data == null ? null : data.stream().map((val)->serializer.deserialize(val, clazz))
							.collect(Collectors.toCollection(ArrayList::new));

			if(isTransactionOrPipeline(connection)){
				return addListConverter(index, function);
			}else{
				return function.apply(value);
			}
		}

	}

	final class ClazzListBinaryConverter<V> extends AbstractListBinaryConverter<V> {

		private final Class<V> clazz;

		public ClazzListBinaryConverter(final RedisAccessor accessor, final Class<V> clazz){
			super(accessor);
			this.clazz = clazz;
		}

		@Override
		public List<V> convert(final RedisConnection connection, final List<byte[]> value){
			final Function<List<byte[]>, List<V>> function = (data)->
					data == null ? null : data.stream().map((val)->serializer.deserializeBytes(val, clazz))
							.collect(Collectors.toCollection(ArrayList::new));

			if(isTransactionOrPipeline(connection)){
				return addListBinaryConverter(index, function);
			}else{
				return function.apply(value);
			}
		}

	}

	final class TypeListStringConverter<V> extends AbstractListStringConverter<V> {

		private final TypeReference<V> typeReference;

		public TypeListStringConverter(final RedisAccessor accessor, final TypeReference<V> typeReference){
			super(accessor);
			this.typeReference = typeReference;
		}

		@Override
		public List<V> convert(final RedisConnection connection, final List<String> value){
			final Function<List<String>, List<V>> function = (data)->
					data == null ? null : data.stream().map((val)->serializer.deserialize(val, typeReference))
							.collect(Collectors.toCollection(ArrayList::new));

			if(isTransactionOrPipeline(connection)){
				return addListConverter(index, function);
			}else{
				return function.apply(value);
			}
		}

	}

	final class TypeListBinaryConverter<V> extends AbstractListBinaryConverter<V> {

		private final TypeReference<V> typeReference;

		public TypeListBinaryConverter(final RedisAccessor accessor, final TypeReference<V> typeReference){
			super(accessor);
			this.typeReference = typeReference;
		}

		@Override
		public List<V> convert(final RedisConnection connection, final List<byte[]> value){
			final Function<List<byte[]>, List<V>> function = (data)->
					data == null ? null : data.stream().map((val)->serializer.deserializeBytes(val, typeReference))
							.collect(Collectors.toCollection(ArrayList::new));

			if(isTransactionOrPipeline(connection)){
				return addListBinaryConverter(index, function);
			}else{
				return function.apply(value);
			}
		}

	}

	abstract class AbstractSetStringConverter<V> extends AbstractConverter<Set<String>, Set<V>> {

		public AbstractSetStringConverter(final RedisAccessor accessor){
			super(accessor);
		}

	}

	abstract class AbstractSetBinaryConverter<V> extends AbstractConverter<Set<byte[]>, Set<V>> {

		public AbstractSetBinaryConverter(final RedisAccessor accessor){
			super(accessor);
		}

	}

	final class SimpleSetStringConverter<V> extends AbstractSetStringConverter<V> {

		public SimpleSetStringConverter(final RedisAccessor accessor){
			super(accessor);
		}

		@Override
		public Set<V> convert(final RedisConnection connection, final Set<String> value){
			final Function<Set<String>, Set<V>> function = (data)->{
				if(data != null){
					final Set<V> result = new LinkedHashSet<>(data.size());

					for(String s : data){
						result.add(serializer.deserialize(s));
					}

					return result;
				}

				return null;
			};

			if(isTransactionOrPipeline(connection)){
				return addSetConverter(index, function);
			}else{
				return function.apply(value);
			}
		}

	}

	final class SimpleSetBinaryConverter<V> extends AbstractSetBinaryConverter<V> {

		public SimpleSetBinaryConverter(final RedisAccessor accessor){
			super(accessor);
		}

		@Override
		public Set<V> convert(final RedisConnection connection, final Set<byte[]> value){
			final Function<Set<byte[]>, Set<V>> function = (data)->{
				if(data != null){
					final Set<V> result = new LinkedHashSet<>(data.size());

					for(byte[] b : data){
						result.add(serializer.deserializeBytes(b));
					}

					return result;
				}

				return null;
			};

			if(isTransactionOrPipeline(connection)){
				return addSetBinaryConverter(index, function);
			}else{
				return function.apply(value);
			}
		}

	}

	final class ClazzSetStringConverter<V> extends AbstractSetStringConverter<V> {

		private final Class<V> clazz;

		public ClazzSetStringConverter(final RedisAccessor accessor, final Class<V> clazz){
			super(accessor);
			this.clazz = clazz;
		}

		@Override
		public Set<V> convert(final RedisConnection connection, final Set<String> value){
			final Function<Set<String>, Set<V>> function = (data)->
					data == null ? null : data.stream().map((val)->serializer.deserialize(val, clazz))
							.collect(Collectors.toCollection(LinkedHashSet::new));

			if(isTransactionOrPipeline(connection)){
				return addSetConverter(index, function);
			}else{
				return function.apply(value);
			}
		}

	}

	final class ClazzSetBinaryConverter<V> extends AbstractSetBinaryConverter<V> {

		private final Class<V> clazz;

		public ClazzSetBinaryConverter(final RedisAccessor accessor, final Class<V> clazz){
			super(accessor);
			this.clazz = clazz;
		}

		@Override
		public Set<V> convert(final RedisConnection connection, final Set<byte[]> value){
			final Function<Set<byte[]>, Set<V>> function = (data)->
					data == null ? null : data.stream().map((val)->serializer.deserializeBytes(val, clazz))
							.collect(Collectors.toCollection(LinkedHashSet::new));

			if(isTransactionOrPipeline(connection)){
				return addSetBinaryConverter(index, function);
			}else{
				return function.apply(value);
			}
		}

	}

	final class TypeSetStringConverter<V> extends AbstractSetStringConverter<V> {

		private final TypeReference<V> typeReference;

		public TypeSetStringConverter(final RedisAccessor accessor, final TypeReference<V> typeReference){
			super(accessor);
			this.typeReference = typeReference;
		}

		@Override
		public Set<V> convert(final RedisConnection connection, final Set<String> value){
			final Function<Set<String>, Set<V>> function = (data)->
					data == null ? null : data.stream().map((val)->serializer.deserialize(val, typeReference))
							.collect(Collectors.toCollection(LinkedHashSet::new));

			if(isTransactionOrPipeline(connection)){
				return addSetConverter(index, function);
			}else{
				return function.apply(value);
			}
		}

	}

	final class TypeSetBinaryConverter<V> extends AbstractSetBinaryConverter<V> {

		private final TypeReference<V> typeReference;

		public TypeSetBinaryConverter(final RedisAccessor accessor, final TypeReference<V> typeReference){
			super(accessor);
			this.typeReference = typeReference;
		}

		@Override
		public Set<V> convert(final RedisConnection connection, final Set<byte[]> value){
			final Function<Set<byte[]>, Set<V>> function = (data)->
					data == null ? null : data.stream().map((val)->serializer.deserializeBytes(val, typeReference))
							.collect(Collectors.toCollection(LinkedHashSet::new));

			if(isTransactionOrPipeline(connection)){
				return addSetBinaryConverter(index, function);
			}else{
				return function.apply(value);
			}
		}

	}

	abstract class AbstractMapStringConverter<V> extends AbstractConverter<Map<String, String>, Map<String, V>> {

		public AbstractMapStringConverter(final RedisAccessor accessor){
			super(accessor);
		}

	}

	abstract class AbstractMapBinaryConverter<V> extends AbstractConverter<Map<byte[], byte[]>, Map<byte[], V>> {

		public AbstractMapBinaryConverter(final RedisAccessor accessor){
			super(accessor);
		}

	}

	final class SimpleMapStringConverter<V> extends AbstractMapStringConverter<V> {

		public SimpleMapStringConverter(final RedisAccessor accessor){
			super(accessor);
		}

		@Override
		public Map<String, V> convert(final RedisConnection connection, final Map<String, String> value){
			final Function<Map<String, String>, Map<String, V>> function = (data)->
					data == null ? null : Maps.map(data, (key)->key, serializer::deserialize);

			if(isTransactionOrPipeline(connection)){
				return addMapConverter(index, function);
			}else{
				return function.apply(value);
			}
		}

	}

	final class SimpleMapBinaryConverter<V> extends AbstractMapBinaryConverter<V> {

		public SimpleMapBinaryConverter(final RedisAccessor accessor){
			super(accessor);
		}

		@Override
		public Map<byte[], V> convert(final RedisConnection connection, final Map<byte[], byte[]> value){
			final Function<Map<byte[], byte[]>, Map<byte[], V>> function = (data)->
					data == null ? null : Maps.map(data, (key)->key, serializer::deserializeBytes);

			if(isTransactionOrPipeline(connection)){
				return addMapBinaryConverter(index, function);
			}else{
				return function.apply(value);
			}
		}

	}

	final class ClazzMapStringConverter<V> extends AbstractMapStringConverter<V> {

		private final Class<V> clazz;

		public ClazzMapStringConverter(final RedisAccessor accessor, final Class<V> clazz){
			super(accessor);
			this.clazz = clazz;
		}

		@Override
		public Map<String, V> convert(final RedisConnection connection, final Map<String, String> value){
			final Function<Map<String, String>, Map<String, V>> function = (data)->
					data == null ? null : Maps.map(data, (key)->key, (val)->serializer.deserialize(val, clazz));

			if(isTransactionOrPipeline(connection)){
				return addMapConverter(index, function);
			}else{
				return function.apply(value);
			}
		}

	}

	final class ClazzMapBinaryConverter<V> extends AbstractMapBinaryConverter<V> {

		private final Class<V> clazz;

		public ClazzMapBinaryConverter(final RedisAccessor accessor, final Class<V> clazz){
			super(accessor);
			this.clazz = clazz;
		}

		@Override
		public Map<byte[], V> convert(final RedisConnection connection, final Map<byte[], byte[]> value){
			final Function<Map<byte[], byte[]>, Map<byte[], V>> function = (data)->
					data == null ? null : Maps.map(data, (key)->key, (val)->serializer.deserializeBytes(val, clazz));

			if(isTransactionOrPipeline(connection)){
				return addMapBinaryConverter(index, function);
			}else{
				return function.apply(value);
			}
		}

	}

	final class TypeMapStringConverter<V> extends AbstractMapStringConverter<V> {

		private final TypeReference<V> typeReference;

		public TypeMapStringConverter(final RedisAccessor accessor, final TypeReference<V> typeReference){
			super(accessor);
			this.typeReference = typeReference;
		}

		@Override
		public Map<String, V> convert(final RedisConnection connection, final Map<String, String> value){
			final Function<Map<String, String>, Map<String, V>> function = (data)->
					data == null ? null : Maps.map(data, (key)->key, (val)->serializer.deserialize(val, typeReference));

			if(isTransactionOrPipeline(connection)){
				return addMapConverter(index, function);
			}else{
				return function.apply(value);
			}
		}

	}

	final class TypeMapBinaryConverter<V> extends AbstractMapBinaryConverter<V> {

		private final TypeReference<V> typeReference;

		public TypeMapBinaryConverter(final RedisAccessor accessor, final TypeReference<V> typeReference){
			super(accessor);
			this.typeReference = typeReference;
		}

		@Override
		public Map<byte[], V> convert(final RedisConnection connection, final Map<byte[], byte[]> value){
			final Function<Map<byte[], byte[]>, Map<byte[], V>> function = (data)->
					data == null ? null : Maps.map(data, (key)->key,
							(val)->serializer.deserializeBytes(val, typeReference));

			if(isTransactionOrPipeline(connection)){
				return addMapBinaryConverter(index, function);
			}else{
				return function.apply(value);
			}
		}

	}

	abstract class AbstractScanResultConverter<SV, TV> extends AbstractConverter<ScanResult<SV>, ScanResult<TV>> {

		public AbstractScanResultConverter(final RedisAccessor accessor){
			super(accessor);
		}

	}

	abstract class AbstractScanResultMapStringConverter<V>
			extends AbstractScanResultConverter<Map<String, String>, Map<String, V>> {

		public AbstractScanResultMapStringConverter(final RedisAccessor accessor){
			super(accessor);
		}

	}

	abstract class AbstractScanResultListStringConverter<V> extends AbstractScanResultConverter<List<String>, List<V>> {

		public AbstractScanResultListStringConverter(final RedisAccessor accessor){
			super(accessor);
		}

	}

	abstract class AbstractScanResultListBinaryConverter<V> extends AbstractScanResultConverter<List<byte[]>, List<V>> {

		public AbstractScanResultListBinaryConverter(final RedisAccessor accessor){
			super(accessor);
		}

	}

	final class SimpleScanResultListStringConverter<V> extends AbstractScanResultListStringConverter<V> {

		public SimpleScanResultListStringConverter(final RedisAccessor accessor){
			super(accessor);
		}

		@Override
		public ScanResult<List<V>> convert(final RedisConnection connection, final ScanResult<List<String>> value){
			final Function<ScanResult<List<String>>, ScanResult<List<V>>> function = (scanResult)->{
				if(scanResult.getResults() == null){
					return new ScanResult<>(scanResult.getCursor(), null);
				}else{
					final List<V> result = new ArrayList<>(scanResult.getResults().size());

					for(String str : scanResult.getResults()){
						result.add(serializer.deserialize(str));
					}

					return new ScanResult<>(scanResult.getCursor(), result);
				}
			};

			if(isTransactionOrPipeline(connection)){
				return addScanResultConverter(index, function);
			}else{
				return function.apply(value);
			}
		}

	}

	final class SimpleScanResultListBinaryConverter<V> extends AbstractScanResultListBinaryConverter<V> {

		public SimpleScanResultListBinaryConverter(final RedisAccessor accessor){
			super(accessor);
		}

		@Override
		public ScanResult<List<V>> convert(final RedisConnection connection, final ScanResult<List<byte[]>> value){
			final Function<ScanResult<List<byte[]>>, ScanResult<List<V>>> function = (scanResult)->{
				if(scanResult.getResults() == null){
					return new ScanResult<>(scanResult.getCursor(), null);
				}else{
					final List<V> result = new ArrayList<>(scanResult.getResults().size());

					for(byte[] b : scanResult.getResults()){
						result.add(serializer.deserializeBytes(b));
					}

					return new ScanResult<>(scanResult.getCursor(), result);
				}
			};

			if(isTransactionOrPipeline(connection)){
				return addScanResultConverter(index, function);
			}else{
				return function.apply(value);
			}
		}

	}

	final class ClazzScanResultListStringConverter<V> extends AbstractScanResultListStringConverter<V> {

		private final Class<V> clazz;

		public ClazzScanResultListStringConverter(final RedisAccessor accessor, final Class<V> clazz){
			super(accessor);
			this.clazz = clazz;
		}

		@Override
		public ScanResult<List<V>> convert(final RedisConnection connection, final ScanResult<List<String>> value){
			final Function<ScanResult<List<String>>, ScanResult<List<V>>> function = (scanResult)->{
				final List<V> result = value.getResults() == null ? null : value.getResults().stream()
						.map((val)->serializer.deserialize(val, clazz))
						.collect(Collectors.toCollection(ArrayList::new));
				return new ScanResult<>(scanResult.getCursor(), result);
			};

			if(isTransactionOrPipeline(connection)){
				return addScanResultConverter(index, function);
			}else{
				return function.apply(value);
			}
		}

	}

	final class ClazzScanResultListBinaryConverter<V> extends AbstractScanResultListBinaryConverter<V> {

		private final Class<V> clazz;

		public ClazzScanResultListBinaryConverter(final RedisAccessor accessor, final Class<V> clazz){
			super(accessor);
			this.clazz = clazz;
		}

		@Override
		public ScanResult<List<V>> convert(final RedisConnection connection, final ScanResult<List<byte[]>> value){
			final Function<ScanResult<List<byte[]>>, ScanResult<List<V>>> function = (scanResult)->{
				final List<V> result = value.getResults() == null ? null : value.getResults().stream()
						.map((val)->serializer.deserializeBytes(val, clazz))
						.collect(Collectors.toCollection(ArrayList::new));
				return new ScanResult<>(scanResult.getCursor(), result);
			};

			if(isTransactionOrPipeline(connection)){
				return addScanResultConverter(index, function);
			}else{
				return function.apply(value);
			}
		}

	}

	final class TypeScanResultListStringConverter<V> extends AbstractScanResultListStringConverter<V> {

		private final TypeReference<V> typeReference;

		public TypeScanResultListStringConverter(final RedisAccessor accessor, final TypeReference<V> typeReference){
			super(accessor);
			this.typeReference = typeReference;
		}

		@Override
		public ScanResult<List<V>> convert(final RedisConnection connection, final ScanResult<List<String>> value){
			final Function<ScanResult<List<String>>, ScanResult<List<V>>> function = (scanResult)->{
				final List<V> result = value.getResults() == null ? null : value.getResults().stream()
						.map((val)->serializer.deserialize(val, typeReference))
						.collect(Collectors.toCollection(ArrayList::new));
				return new ScanResult<>(scanResult.getCursor(), result);
			};

			if(isTransactionOrPipeline(connection)){
				return addScanResultConverter(index, function);
			}else{
				return function.apply(value);
			}
		}

	}

	final class TypeScanResultListBinaryConverter<V> extends AbstractScanResultListBinaryConverter<V> {

		private final TypeReference<V> typeReference;

		public TypeScanResultListBinaryConverter(final RedisAccessor accessor, final TypeReference<V> typeReference){
			super(accessor);
			this.typeReference = typeReference;
		}

		@Override
		public ScanResult<List<V>> convert(final RedisConnection connection, final ScanResult<List<byte[]>> value){
			final Function<ScanResult<List<byte[]>>, ScanResult<List<V>>> function = (scanResult)->{
				final List<V> result = value.getResults() == null ? null : value.getResults().stream()
						.map((val)->serializer.deserializeBytes(val, typeReference))
						.collect(Collectors.toCollection(ArrayList::new));
				return new ScanResult<>(scanResult.getCursor(), result);
			};

			if(isTransactionOrPipeline(connection)){
				return addScanResultConverter(index, function);
			}else{
				return function.apply(value);
			}
		}

	}

	abstract class AbstractScanResultMapBinaryConverter<V>
			extends AbstractScanResultConverter<Map<byte[], byte[]>, Map<byte[], V>> {

		public AbstractScanResultMapBinaryConverter(final RedisAccessor accessor){
			super(accessor);
		}

	}

	final class SimpleScanResultMapStringConverter<V> extends AbstractScanResultMapStringConverter<V> {

		public SimpleScanResultMapStringConverter(final RedisAccessor accessor){
			super(accessor);
		}

		@Override
		public ScanResult<Map<String, V>> convert(final RedisConnection connection,
												  final ScanResult<Map<String, String>> value){
			final Function<ScanResult<Map<String, String>>, ScanResult<Map<String, V>>> function = (scanResult)->{
				if(scanResult.getResults() == null){
					return new ScanResult<>(scanResult.getCursor(), null);
				}else{
					final Map<String, V> result = new LinkedHashMap<>(scanResult.getResults().size());

					scanResult.getResults().forEach((k, v)->{
						result.put(k, serializer.deserialize(v));
					});

					return new ScanResult<>(scanResult.getCursor(), result);
				}
			};

			if(isTransactionOrPipeline(connection)){
				return addScanResultConverter(index, function);
			}else{
				return function.apply(value);
			}
		}

	}

	final class SimpleScanResultMapBinaryConverter<V> extends AbstractScanResultMapBinaryConverter<V> {

		public SimpleScanResultMapBinaryConverter(final RedisAccessor accessor){
			super(accessor);
		}

		@Override
		public ScanResult<Map<byte[], V>> convert(final RedisConnection connection,
												  final ScanResult<Map<byte[], byte[]>> value){
			final Function<ScanResult<Map<byte[], byte[]>>, ScanResult<Map<byte[], V>>> function = (scanResult)->{
				if(scanResult.getResults() == null){
					return new ScanResult<>(scanResult.getCursor(), null);
				}else{
					final Map<byte[], V> result = new LinkedHashMap<>(scanResult.getResults().size());

					scanResult.getResults().forEach((k, v)->{
						result.put(k, serializer.deserializeBytes(v));
					});

					return new ScanResult<>(scanResult.getCursor(), result);
				}
			};

			if(isTransactionOrPipeline(connection)){
				return addScanResultConverter(index, function);
			}else{
				return function.apply(value);
			}
		}

	}

	final class ClazzScanResultMapStringConverter<V> extends AbstractScanResultMapStringConverter<V> {

		private final Class<V> clazz;

		public ClazzScanResultMapStringConverter(final RedisAccessor accessor, final Class<V> clazz){
			super(accessor);
			this.clazz = clazz;
		}

		@Override
		public ScanResult<Map<String, V>> convert(final RedisConnection connection,
												  final ScanResult<Map<String, String>> value){
			final Function<ScanResult<Map<String, String>>, ScanResult<Map<String, V>>> function = (scanResult)->{
				final Map<String, V> result =
						scanResult.getResults() == null ? null : Maps.map(value.getResults(), (key)->key,
								(val)->serializer.deserialize(val, clazz));
				return new ScanResult<>(scanResult.getCursor(), result);
			};

			if(isTransactionOrPipeline(connection)){
				return addScanResultConverter(index, function);
			}else{
				return function.apply(value);
			}
		}

	}

	final class ClazzScanResultMapBinaryConverter<V> extends AbstractScanResultMapBinaryConverter<V> {

		private final Class<V> clazz;

		public ClazzScanResultMapBinaryConverter(final RedisAccessor accessor, final Class<V> clazz){
			super(accessor);
			this.clazz = clazz;
		}

		@Override
		public ScanResult<Map<byte[], V>> convert(final RedisConnection connection,
												  final ScanResult<Map<byte[], byte[]>> value){
			final Function<ScanResult<Map<byte[], byte[]>>, ScanResult<Map<byte[], V>>> function = (scanResult)->{
				final Map<byte[], V> result =
						scanResult.getResults() == null ? null : Maps.map(value.getResults(), (key)->key,
								(val)->serializer.deserializeBytes(val, clazz));
				return new ScanResult<>(scanResult.getCursor(), result);
			};

			if(isTransactionOrPipeline(connection)){
				return addScanResultConverter(index, function);
			}else{
				return function.apply(value);
			}
		}

	}

	final class TypeScanResultMapStringConverter<V> extends AbstractScanResultMapStringConverter<V> {

		private final TypeReference<V> typeReference;

		public TypeScanResultMapStringConverter(final RedisAccessor accessor, final TypeReference<V> typeReference){
			super(accessor);
			this.typeReference = typeReference;
		}

		@Override
		public ScanResult<Map<String, V>> convert(final RedisConnection connection,
												  final ScanResult<Map<String, String>> value){
			final Function<ScanResult<Map<String, String>>, ScanResult<Map<String, V>>> function = (scanResult)->{
				final Map<String, V> result =
						scanResult.getResults() == null ? null : Maps.map(value.getResults(), (key)->key,
								(val)->serializer.deserialize(val, typeReference));
				return new ScanResult<>(scanResult.getCursor(), result);
			};

			if(isTransactionOrPipeline(connection)){
				return addScanResultConverter(index, function);
			}else{
				return function.apply(value);
			}
		}

	}

	final class TypeScanResultMapBinaryConverter<V> extends AbstractScanResultMapBinaryConverter<V> {

		private final TypeReference<V> typeReference;

		public TypeScanResultMapBinaryConverter(final RedisAccessor accessor, final TypeReference<V> typeReference){
			super(accessor);
			this.typeReference = typeReference;
		}

		@Override
		public ScanResult<Map<byte[], V>> convert(final RedisConnection connection,
												  final ScanResult<Map<byte[], byte[]>> value){
			final Function<ScanResult<Map<byte[], byte[]>>, ScanResult<Map<byte[], V>>> function = (scanResult)->{
				final Map<byte[], V> result =
						scanResult.getResults() == null ? null : Maps.map(value.getResults(), (key)->key,
								(val)->serializer.deserializeBytes(val, typeReference));
				return new ScanResult<>(scanResult.getCursor(), result);
			};

			if(isTransactionOrPipeline(connection)){
				return addScanResultConverter(index, function);
			}else{
				return function.apply(value);
			}
		}

	}

}
