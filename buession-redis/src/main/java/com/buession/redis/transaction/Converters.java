/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license
 * agreements. See the NOTICE file distributed with this work for additional information regarding
 * copyright ownership. The ASF licenses this file to you under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with the License. You may obtain
 * a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 *
 * =================================================================================================
 *
 * This software consists of voluntary contributions made by many individuals on behalf of the
 * Apache Software Foundation. For more information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 * +------------------------------------------------------------------------------------------------+
 * | License: http://www.apache.org/licenses/LICENSE-2.0.txt 										|
 * | Author: Yong.Teng <webmaster@buession.com> 													|
 * | Copyright @ 2013-2021 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.transaction;

import com.buession.core.converter.Converter;
import com.buession.core.serializer.type.TypeReference;
import com.buession.redis.serializer.Serializer;

import java.util.List;
import java.util.Map;

/**
 * @author Yong.Teng
 */
public final class Converters {

	public final static class StringDeserialize<V> implements Converter<String, V> {

		protected Serializer serializer;

		protected Class<V> clazz;

		protected TypeReference<V> type;

		public StringDeserialize(Serializer serializer){
			this.serializer = serializer;
		}

		public StringDeserialize(Serializer serializer, Class<V> clazz){
			this.serializer = serializer;
			this.clazz = clazz;
		}

		public StringDeserialize(Serializer serializer, TypeReference<V> type){
			this.serializer = serializer;
			this.type = type;
		}

		@Override
		public V convert(String source){
			if(type != null){
				return serializer.deserialize(source, type);
			}else if(clazz != null){
				return serializer.deserialize(source, clazz);
			}else{
				return serializer.deserialize(source);
			}
		}

	}

	public final static class BinaryDeserialize<V> implements Converter<byte[], V> {

		protected Serializer serializer;

		protected Class<V> clazz;

		protected TypeReference<V> type;

		public BinaryDeserialize(Serializer serializer){
			this.serializer = serializer;
		}

		public BinaryDeserialize(Serializer serializer, Class<V> clazz){
			this.serializer = serializer;
			this.clazz = clazz;
		}

		public BinaryDeserialize(Serializer serializer, TypeReference<V> type){
			this.serializer = serializer;
			this.type = type;
		}

		@Override
		public V convert(byte[] source){
			if(type != null){
				return serializer.deserializeBytes(source, type);
			}else if(clazz != null){
				return serializer.deserializeBytes(source, clazz);
			}else{
				return serializer.deserializeBytes(source);
			}
		}

	}

	public final static class StringMapDeserialize<V> implements Converter<Map<String, String>, Map<String, V>> {

		protected Serializer serializer;

		protected Class<V> clazz;

		protected TypeReference<V> type;

		public StringMapDeserialize(Serializer serializer){
			this.serializer = serializer;
		}

		public StringMapDeserialize(Serializer serializer, Class<V> clazz){
			this.serializer = serializer;
			this.clazz = clazz;
		}

		public StringMapDeserialize(Serializer serializer, TypeReference<V> type){
			this.serializer = serializer;
			this.type = type;
		}

		@Override
		public Map<String, V> convert(Map<String, String> source){
			if(type != null){
				return serializer.deserialize(source, type);
			}else if(clazz != null){
				return serializer.deserialize(source, clazz);
			}else{
				return serializer.deserialize(source);
			}
		}

	}

	public final static class BinaryMapDeserialize<V> implements Converter<Map<byte[], byte[]>, Map<byte[], V>> {

		protected Serializer serializer;

		protected Class<V> clazz;

		protected TypeReference<V> type;

		public BinaryMapDeserialize(Serializer serializer){
			this.serializer = serializer;
		}

		public BinaryMapDeserialize(Serializer serializer, Class<V> clazz){
			this.serializer = serializer;
			this.clazz = clazz;
		}

		public BinaryMapDeserialize(Serializer serializer, TypeReference<V> type){
			this.serializer = serializer;
			this.type = type;
		}

		@Override
		public Map<byte[], V> convert(Map<byte[], byte[]> source){
			if(type != null){
				return serializer.deserializeBytes(source, type);
			}else if(clazz != null){
				return serializer.deserializeBytes(source, clazz);
			}else{
				return serializer.deserializeBytes(source);
			}
		}

	}

	public final static class StringListDeserialize<V> implements Converter<List<String>, List<V>> {

		protected Serializer serializer;

		protected Class<V> clazz;

		protected TypeReference<V> type;

		public StringListDeserialize(Serializer serializer){
			this.serializer = serializer;
		}

		public StringListDeserialize(Serializer serializer, Class<V> clazz){
			this.serializer = serializer;
			this.clazz = clazz;
		}

		public StringListDeserialize(Serializer serializer, TypeReference<V> type){
			this.serializer = serializer;
			this.type = type;
		}

		@Override
		public List<V> convert(List<String> source){
			if(type != null){
				return serializer.deserialize(source, type);
			}else if(clazz != null){
				return serializer.deserialize(source, clazz);
			}else{
				return serializer.deserialize(source);
			}
		}

	}

	public final static class BinaryListDeserialize<V> implements Converter<List<byte[]>, List<V>> {

		protected Serializer serializer;

		protected Class<V> clazz;

		protected TypeReference<V> type;

		public BinaryListDeserialize(Serializer serializer){
			this.serializer = serializer;
		}

		public BinaryListDeserialize(Serializer serializer, Class<V> clazz){
			this.serializer = serializer;
			this.clazz = clazz;
		}

		public BinaryListDeserialize(Serializer serializer, TypeReference<V> type){
			this.serializer = serializer;
			this.type = type;
		}

		@Override
		public List<V> convert(List<byte[]> source){
			if(type != null){
				return serializer.deserializeBytes(source, type);
			}else if(clazz != null){
				return serializer.deserializeBytes(source, clazz);
			}else{
				return serializer.deserializeBytes(source);
			}
		}

	}

	public final static class StringSetDeserialize<V> implements Converter<List<String>, List<V>> {

		protected Serializer serializer;

		protected Class<V> clazz;

		protected TypeReference<V> type;

		public StringSetDeserialize(Serializer serializer){
			this.serializer = serializer;
		}

		public StringSetDeserialize(Serializer serializer, Class<V> clazz){
			this.serializer = serializer;
			this.clazz = clazz;
		}

		public StringSetDeserialize(Serializer serializer, TypeReference<V> type){
			this.serializer = serializer;
			this.type = type;
		}

		@Override
		public List<V> convert(List<String> source){
			if(type != null){
				return serializer.deserialize(source, type);
			}else if(clazz != null){
				return serializer.deserialize(source, clazz);
			}else{
				return serializer.deserialize(source);
			}
		}

	}

	public final static class BinarySetDeserialize<V> implements Converter<List<byte[]>, List<V>> {

		protected Serializer serializer;

		protected Class<V> clazz;

		protected TypeReference<V> type;

		public BinarySetDeserialize(Serializer serializer){
			this.serializer = serializer;
		}

		public BinarySetDeserialize(Serializer serializer, Class<V> clazz){
			this.serializer = serializer;
			this.clazz = clazz;
		}

		public BinarySetDeserialize(Serializer serializer, TypeReference<V> type){
			this.serializer = serializer;
			this.type = type;
		}

		@Override
		public List<V> convert(List<byte[]> source){
			if(type != null){
				return serializer.deserializeBytes(source, type);
			}else if(clazz != null){
				return serializer.deserializeBytes(source, clazz);
			}else{
				return serializer.deserializeBytes(source);
			}
		}

	}

}
