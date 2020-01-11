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
 * | Copyright @ 2013-2020 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.utils;

import com.buession.core.serializer.type.TypeReference;
import com.buession.core.validator.Validate;
import com.buession.redis.serializer.Serializer;

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
public class ReturnUtils {

	private ReturnUtils(){

	}

	public final static <V> List<V> returnObjectValueFromListString(final Serializer serializer, final List<String>
			data){
		if(data == null){
			return null;
		}

		final List<V> result = new ArrayList<>(data.size());

		for(String value : data){
			result.add(serializer.deserialize(value));
		}

		return result;
	}

	public final static <V> List<V> returnObjectValueFromListByte(final Serializer serializer, final List<byte[]>
			data){
		if(data == null){
			return null;
		}

		final List<V> result = new ArrayList<>(data.size());

		for(byte[] value : data){
			result.add(serializer.deserialize(value));
		}

		return result;
	}

	public final static <V> List<V> returnObjectValueFromListString(final Serializer serializer, final List<String>
			data, final Class<V> clazz){
		if(data == null){
			return null;
		}

		return data.stream().map((value)->serializer.deserialize(value, clazz)).collect(Collectors.toList());
	}

	public final static <V> List<V> returnObjectValueFromListByte(final Serializer serializer, final List<byte[]>
			data, final Class<V> clazz){
		if(data == null){
			return null;
		}

		return data.stream().map((value)->serializer.deserialize(value, clazz)).collect(Collectors.toList());
	}

	public final static <V> List<V> returnObjectValueFromListString(final Serializer serializer, final List<String>
			data, final TypeReference<V> type){
		if(data == null){
			return null;
		}

		return data.stream().map((value)->serializer.deserialize(value, type)).collect(Collectors.toList());
	}

	public final static <V> List<V> returnObjectValueFromListByte(final Serializer serializer, final List<byte[]>
			data, final TypeReference<V> type){
		if(data == null){
			return null;
		}

		return data.stream().map((value)->serializer.deserialize(value, type)).collect(Collectors.toList());
	}

	public final static <V> Set<V> returnObjectValueFromSetString(final Serializer serializer, final Set<String> data){
		if(data == null){
			return null;
		}

		final Set<V> result = new LinkedHashSet<>(data.size());

		for(String value : data){
			result.add(serializer.deserialize(value));
		}

		return result;
	}

	public final static <V> Set<V> returnObjectValueFromSetByte(final Serializer serializer, final Set<byte[]> data){
		if(data == null){
			return null;
		}

		final Set<V> result = new LinkedHashSet<>(data.size());

		for(byte[] value : data){
			result.add(serializer.deserialize(value));
		}

		return result;
	}

	public final static <V> Set<V> returnObjectValueFromSetString(final Serializer serializer, final Set<String> data,
																  final Class<V> clazz){
		if(data == null){
			return null;
		}

		return data.stream().map((value)->serializer.deserialize(value, clazz)).collect(Collectors.toCollection
				(LinkedHashSet::new));
	}

	public final static <V> Set<V> returnObjectValueFromSetByte(final Serializer serializer, final Set<byte[]> data,
																final Class<V> clazz){
		if(data == null){
			return null;
		}

		return data.stream().map((value)->serializer.deserialize(value, clazz)).collect(Collectors.toCollection
				(LinkedHashSet::new));
	}

	public final static <V> Set<V> returnObjectValueFromSetString(final Serializer serializer, final Set<String> data,
																  final TypeReference<V> type){
		if(data == null){
			return null;
		}

		return data.stream().map((value)->serializer.deserialize(value, type)).collect(Collectors.toCollection
				(LinkedHashSet::new));
	}

	public final static <V> Set<V> returnObjectValueFromSetByte(final Serializer serializer, final Set<byte[]> data,
																final TypeReference<V> type){
		if(data == null){
			return null;
		}

		return data.stream().map((value)->serializer.deserialize(value, type)).collect(Collectors.toCollection
				(LinkedHashSet::new));
	}

	public final static <V> Map<String, V> returnObjectValueFromMapString(final Serializer serializer, final
	Map<String, String> data){
		if(data == null){
			return null;
		}

		final Map<String, V> result = new LinkedHashMap<>(data.size());

		data.forEach((key, value)->result.put(key, serializer.deserialize(value)));

		return result;
	}

	public final static <V> Map<byte[], V> returnObjectValueFromMapByte(final Serializer serializer, final Map<byte[],
			byte[]> data){
		if(data == null){
			return null;
		}

		final Map<byte[], V> result = new LinkedHashMap<>(data.size());

		data.forEach((key, value)->result.put(key, serializer.deserialize(value)));

		return result;
	}

	public final static <V> Map<String, V> returnObjectValueFromMapString(final Serializer serializer, final
	Map<String, String> data, final Class<V> clazz){
		if(data == null){
			return null;
		}

		final Map<String, V> result = new LinkedHashMap<>(data.size());

		data.forEach((key, value)->result.put(key, serializer.deserialize(value, clazz)));

		return result;
	}

	public final static <V> Map<byte[], V> returnObjectValueFromMapByte(final Serializer serializer, final Map<byte[],
			byte[]> data, final Class<V> clazz){
		if(data == null){
			return null;
		}

		final Map<byte[], V> result = new LinkedHashMap<>(data.size());

		data.forEach((key, value)->result.put(key, serializer.deserialize(value, clazz)));

		return result;
	}

	public final static <V> Map<String, V> returnObjectValueFromMapString(final Serializer serializer, final
	Map<String, String> data, final TypeReference<V> type){
		if(data == null){
			return null;
		}

		final Map<String, V> result = new LinkedHashMap<>(data.size());

		data.forEach((key, value)->result.put(key, serializer.deserialize(value, type)));

		return result;
	}

	public final static <V> Map<byte[], V> returnObjectValueFromMapByte(final Serializer serializer, final Map<byte[],
			byte[]> data, final TypeReference<V> type){
		if(data == null){
			return null;
		}

		final Map<byte[], V> result = new LinkedHashMap<>(data.size());

		data.forEach((key, value)->result.put(key, serializer.deserialize(value, type)));

		return result;
	}

	public final static <V> V returnFirst(final List<V> data){
		return returnFirst(data, null);
	}

	public final static <V> V returnFirst(final List<V> data, final V defaultValue){
		return Validate.isEmpty(data) ? defaultValue : data.get(0);
	}

}
