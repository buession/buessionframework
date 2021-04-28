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
package com.buession.redis.serializer;

import com.buession.core.serializer.SerializerException;
import com.buession.core.serializer.type.TypeReference;
import com.buession.core.utils.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Yong.Teng
 */
public abstract class AbstractSerializer implements Serializer {

	private final com.buession.core.serializer.Serializer serializer;

	private final Logger logger = LoggerFactory.getLogger(getClass());

	protected AbstractSerializer(final com.buession.core.serializer.Serializer serializer){
		Assert.isNull(serializer, "original serializer object cloud not be null.");
		this.serializer = serializer;
	}

	@Override
	public <V> String serialize(final V object){
		if(object != null){
			try{
				return serializer.serialize(object);
			}catch(SerializerException e){
				logger.error("{} serializer error.", object, e);
			}
		}

		return null;
	}

	@Override
	public <V> String[] serialize(final V[] objects){
		if(objects != null){
			final String[] temp = new String[objects.length];

			for(int i = 0; i < objects.length; i++){
				temp[i] = serialize(objects[i]);
			}

			return temp;
		}

		return null;
	}

	@Override
	public <V> byte[] serializeAsBytes(final V object){
		if(object != null){
			try{
				return serializer.serializeAsBytes(object);
			}catch(SerializerException e){
				logger.error("{} serializer error.", object, e);
			}
		}

		return null;
	}

	@Override
	public <V> byte[][] serializeAsBytes(final V[] objects){
		if(objects != null){
			final byte[][] temp = new byte[objects.length][];

			for(int i = 0; i < objects.length; i++){
				temp[i] = serializeAsBytes(objects[i]);
			}

			return temp;
		}

		return null;
	}

	@Override
	public <V> V deserialize(final String str){
		if(str != null){
			try{
				return serializer.deserialize(str);
			}catch(SerializerException e){
				logger.error("{} serializer error.", str, e);
			}
		}

		return null;
	}

	@Override
	public <V> V deserializeBytes(final byte[] bytes){
		if(bytes != null){
			try{
				return serializer.deserialize(bytes);
			}catch(SerializerException e){
				logger.error("{} serializer error.", bytes, e);
			}
		}

		return null;
	}

	@Override
	public <V> List<V> deserialize(final List<String> str){
		if(str == null){
			return null;
		}

		final List<V> result = new ArrayList<>(str.size());

		for(String s : str){
			result.add(deserialize(s));
		}

		return result;
	}

	@Override
	public <V> List<V> deserializeBytes(final List<byte[]> bytes){
		if(bytes == null){
			return null;
		}

		final List<V> result = new ArrayList<>(bytes.size());

		for(byte[] b : bytes){
			result.add(deserializeBytes(b));
		}

		return result;
	}

	@Override
	public <V> Set<V> deserialize(final Set<String> str){
		if(str == null){
			return null;
		}

		final Set<V> result = new LinkedHashSet<>(str.size());

		for(String s : str){
			result.add(deserialize(s));
		}

		return result;
	}

	@Override
	public <V> Set<V> deserializeBytes(final Set<byte[]> bytes){
		if(bytes == null){
			return null;
		}

		final Set<V> result = new LinkedHashSet<>(bytes.size());

		for(byte[] b : bytes){
			result.add(deserializeBytes(b));
		}

		return result;
	}

	@Override
	public <V> Map<String, V> deserialize(final Map<String, String> str){
		if(str == null){
			return null;
		}

		final Map<String, V> result = new LinkedHashMap<>(str.size());

		str.forEach((key, value)->result.put(key, deserialize(value)));

		return result;
	}

	@Override
	public <V> Map<byte[], V> deserializeBytes(final Map<byte[], byte[]> bytes){
		if(bytes == null){
			return null;
		}

		final Map<byte[], V> result = new LinkedHashMap<>(bytes.size());

		bytes.forEach((key, value)->result.put(key, deserializeBytes(value)));

		return result;
	}

	@Override
	public <V> V deserialize(final String str, final Class<V> clazz){
		return deserialize(str);
	}

	@Override
	public <V> V deserializeBytes(final byte[] bytes, final Class<V> clazz){
		return deserializeBytes(bytes);
	}

	@Override
	public <V> List<V> deserialize(final List<String> str, final Class<V> clazz){
		if(str == null){
			return null;
		}else{
			return str.stream().map((value)->deserialize(value, clazz)).collect(Collectors.toList());
		}
	}

	@Override
	public <V> List<V> deserializeBytes(final List<byte[]> bytes, final Class<V> clazz){
		if(bytes == null){
			return null;
		}else{
			return bytes.stream().map((value)->deserializeBytes(value, clazz)).collect(Collectors.toList());
		}
	}

	@Override
	public <V> Set<V> deserialize(final Set<String> str, final Class<V> clazz){
		if(str == null){
			return null;
		}else{
			return str.stream().map((value)->deserialize(value, clazz)).collect(Collectors.toCollection(LinkedHashSet::new));
		}
	}

	@Override
	public <V> Set<V> deserializeBytes(final Set<byte[]> bytes, final Class<V> clazz){
		if(bytes == null){
			return null;
		}else{
			return bytes.stream().map((value)->deserializeBytes(value, clazz)).collect(Collectors.toCollection(LinkedHashSet::new));
		}
	}

	@Override
	public <V> Map<String, V> deserialize(final Map<String, String> str, final Class<V> clazz){
		if(str == null){
			return null;
		}

		final Map<String, V> result = new LinkedHashMap<>(str.size());

		str.forEach((key, value)->result.put(key, deserialize(value, clazz)));

		return result;
	}

	@Override
	public <V> Map<byte[], V> deserializeBytes(final Map<byte[], byte[]> bytes, final Class<V> clazz){
		if(bytes == null){
			return null;
		}

		final Map<byte[], V> result = new LinkedHashMap<>(bytes.size());

		bytes.forEach((key, value)->result.put(key, deserializeBytes(value, clazz)));

		return result;
	}

	@Override
	public <V> List<V> deserialize(final List<String> str, final TypeReference<V> type){
		if(str == null){
			return null;
		}else{
			return str.stream().map((value)->deserialize(value, type)).collect(Collectors.toCollection(ArrayList::new));
		}
	}

	@Override
	public <V> List<V> deserializeBytes(final List<byte[]> bytes, final TypeReference<V> type){
		if(bytes == null){
			return null;
		}else{
			return bytes.stream().map((value)->deserializeBytes(value, type)).collect(Collectors.toCollection(ArrayList::new));
		}
	}

	@Override
	public <V> Set<V> deserialize(final Set<String> str, final TypeReference<V> type){
		if(str == null){
			return null;
		}else{
			return str.stream().map((value)->deserialize(value, type)).collect(Collectors.toCollection(LinkedHashSet::new));
		}
	}

	@Override
	public <V> Set<V> deserializeBytes(final Set<byte[]> bytes, final TypeReference<V> type){
		if(bytes == null){
			return null;
		}else{
			return bytes.stream().map((value)->deserializeBytes(value, type)).collect(Collectors.toCollection(LinkedHashSet::new));
		}
	}

	@Override
	public <V> Map<String, V> deserialize(final Map<String, String> str, final TypeReference<V> type){
		if(str == null){
			return null;
		}

		final Map<String, V> result = new LinkedHashMap<>(str.size());

		str.forEach((key, value)->result.put(key, deserialize(value, type)));

		return result;
	}

	@Override
	public <V> Map<byte[], V> deserializeBytes(final Map<byte[], byte[]> bytes, final TypeReference<V> type){
		if(bytes == null){
			return null;
		}

		final Map<byte[], V> result = new LinkedHashMap<>(bytes.size());

		bytes.forEach((key, value)->result.put(key, deserializeBytes(value, type)));

		return result;
	}

}
