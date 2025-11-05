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
 * | Copyright @ 2013-2025 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.serializer;

import com.buession.core.deserializer.DeserializerException;
import com.buession.core.deserializer.FastJson2JsonDeserializer;
import com.buession.core.deserializer.FastJsonJsonDeserializer;
import com.buession.core.serializer.SerializerException;
import com.buession.core.utils.ClassUtils;

import java.nio.charset.Charset;

/**
 * FastJSON 序列化和反序列化
 *
 * @author Yong.Teng
 */
public class FastJsonJsonSerializer extends AbstractSerializer<FastJsonJsonSerializer.FastJsonJsonSerializerImpl,
		FastJsonJsonSerializer.FastJsonJsonDeserializerImpl> {

	private final static boolean isFastJson2 = ClassUtils.isPresent("com.alibaba.fastjson2.JSON");

	/**
	 * 构造函数
	 */
	public FastJsonJsonSerializer() {
		super(new FastJsonJsonSerializer.FastJsonJsonSerializerImpl(isFastJson2),
				new FastJsonJsonSerializer.FastJsonJsonDeserializerImpl(isFastJson2));
	}

	/**
	 * FastJSON 序列化实现
	 */
	protected final static class FastJsonJsonSerializerImpl implements com.buession.core.serializer.Serializer {

		private final com.buession.core.serializer.Serializer serializer;

		public FastJsonJsonSerializerImpl(final boolean isFastJson2) {
			this.serializer = isFastJson2 ? new com.buession.core.serializer.FastJson2JsonSerializer() : new com.buession.core.serializer.FastJsonJsonSerializer();
		}

		@Override
		public <V> String serialize(V object) throws SerializerException {
			return serializer.serialize(object);
		}

		@Override
		public <V> String serialize(V object, String charsetName) throws SerializerException {
			return serializer.serialize(object, charsetName);
		}

		@Override
		public <V> String serialize(V object, Charset charset) throws SerializerException {
			return serializer.serialize(object, charset);
		}

		@Override
		public <V> byte[] serializeAsBytes(V object) throws SerializerException {
			return serializer.serializeAsBytes(object);
		}

		@Override
		public <V> byte[] serializeAsBytes(V object, String charsetName) throws SerializerException {
			return serializer.serializeAsBytes(object, charsetName);
		}

		@Override
		public <V> byte[] serializeAsBytes(V object, Charset charset) throws SerializerException {
			return serializer.serializeAsBytes(object, charset);
		}

	}

	/**
	 * FastJSON 反序列化实现
	 */
	protected final static class FastJsonJsonDeserializerImpl implements com.buession.core.deserializer.Deserializer {

		private final com.buession.core.deserializer.Deserializer deserializer;

		public FastJsonJsonDeserializerImpl(final boolean isFastJson2) {
			this.deserializer = isFastJson2 ? new FastJson2JsonDeserializer() : new FastJsonJsonDeserializer();
		}

		@Override
		public <V> V deserialize(String str) throws DeserializerException {
			return deserializer.deserialize(str);
		}

		@Override
		public <V> V deserialize(String str, String charsetName) throws DeserializerException {
			return deserializer.deserialize(str, charsetName);
		}

		@Override
		public <V> V deserialize(String str, Charset charset) throws DeserializerException {
			return deserializer.deserialize(str, charset);
		}

		@Override
		public <V> V deserialize(byte[] bytes) throws DeserializerException {
			return deserializer.deserialize(bytes);
		}

		@Override
		public <V> V deserialize(byte[] bytes, String charsetName) throws DeserializerException {
			return deserializer.deserialize(bytes, charsetName);
		}

		@Override
		public <V> V deserialize(byte[] bytes, Charset charset) throws DeserializerException {
			return deserializer.deserialize(bytes, charset);
		}

	}

}
