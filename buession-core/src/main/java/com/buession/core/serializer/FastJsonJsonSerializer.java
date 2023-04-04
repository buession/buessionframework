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
package com.buession.core.serializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.buession.core.deserializer.DeserializerException;
import com.buession.core.deserializer.FastJsonJsonDeserializer;
import com.buession.core.type.TypeReference;
import com.buession.core.utils.Assert;

import java.nio.charset.Charset;

/**
 * FastJson JSON 序列化
 *
 * @author Yong.Teng
 */
public class FastJsonJsonSerializer extends AbstractJsonSerializer {

	@Override
	public <V> String serialize(final V object) throws SerializerException{
		Assert.isNull(object, "Object cloud not be null.");
		return JSON.toJSONString(object);
	}

	@Override
	public <V> byte[] serializeAsBytes(final V object) throws SerializerException{
		Assert.isNull(object, "Object cloud not be null.");
		return JSON.toJSONBytes(object);
	}

	@Override
	public <V> byte[] serializeAsBytes(final V object, final String charsetName) throws SerializerException{
		return serializeAsBytes(object, Charset.forName(charsetName));
	}

	@Override
	public <V> byte[] serializeAsBytes(final V object, final Charset charset) throws SerializerException{
		Assert.isNull(object, "Object cloud not be null.");
		return JSON.toJSONBytes(charset, object, SerializeConfig.globalInstance, new SerializeFilter[0], null, JSON
				.DEFAULT_GENERATE_FEATURE);
	}

	@Deprecated
	@Override
	public <V> V deserialize(final String str) throws SerializerException{
		FastJsonJsonDeserializer deserializer = new FastJsonJsonDeserializer();
		try{
			return deserializer.deserialize(str);
		}catch(DeserializerException e){
			throw new SerializerException(e.getMessage(), e);
		}
	}

	@Deprecated
	@Override
	public <V> V deserialize(final String str, final Class<V> clazz) throws SerializerException{
		FastJsonJsonDeserializer deserializer = new FastJsonJsonDeserializer();
		try{
			return deserializer.deserialize(str, clazz);
		}catch(DeserializerException e){
			throw new SerializerException(e.getMessage(), e);
		}
	}

	@Deprecated
	@Override
	public <V> V deserialize(final String str, final TypeReference<V> type) throws SerializerException{
		FastJsonJsonDeserializer deserializer = new FastJsonJsonDeserializer();
		try{
			return deserializer.deserialize(str, type);
		}catch(DeserializerException e){
			throw new SerializerException(e.getMessage(), e);
		}
	}

	@Deprecated
	@Override
	public <V> V deserialize(final byte[] bytes) throws SerializerException{
		FastJsonJsonDeserializer deserializer = new FastJsonJsonDeserializer();
		try{
			return deserializer.deserialize(bytes);
		}catch(DeserializerException e){
			throw new SerializerException(e.getMessage(), e);
		}
	}

	@Deprecated
	@Override
	public <V> V deserialize(byte[] bytes, Class<V> clazz) throws SerializerException{
		FastJsonJsonDeserializer deserializer = new FastJsonJsonDeserializer();
		try{
			return deserializer.deserialize(bytes, clazz);
		}catch(DeserializerException e){
			throw new SerializerException(e.getMessage(), e);
		}
	}

	@Deprecated
	@Override
	public <V> V deserialize(final byte[] bytes, final TypeReference<V> type) throws SerializerException{
		FastJsonJsonDeserializer deserializer = new FastJsonJsonDeserializer();
		try{
			return deserializer.deserialize(bytes, type);
		}catch(DeserializerException e){
			throw new SerializerException(e.getMessage(), e);
		}
	}

}
