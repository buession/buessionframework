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
package com.buession.redis.serializer;

import com.buession.core.serializer.type.TypeReference;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Yong.Teng
 */
public interface Serializer {

	<V> String serialize(final V object);

	<V> String[] serialize(final V[] objects);

	<V> byte[] serializeAsBytes(final V object);

	<V> byte[][] serializeAsBytes(final V[] objects);

	<V> V deserialize(final String str);

	<V> V deserializeBytes(final byte[] bytes);

	<V> List<V> deserialize(final List<String> str);

	<V> List<V> deserializeBytes(final List<byte[]> bytes);

	<V> Set<V> deserialize(final Set<String> str);

	<V> Set<V> deserializeBytes(final Set<byte[]> bytes);

	<V> Map<String, V> deserialize(final Map<String, String> str);

	<V> Map<byte[], V> deserializeBytes(final Map<byte[], byte[]> bytes);

	<V> V deserialize(final String str, final Class<V> clazz);

	<V> V deserializeBytes(final byte[] bytes, final Class<V> clazz);

	<V> List<V> deserialize(final List<String> str, final Class<V> clazz);

	<V> List<V> deserializeBytes(final List<byte[]> bytes, final Class<V> clazz);

	<V> Set<V> deserialize(final Set<String> str, final Class<V> clazz);

	<V> Set<V> deserializeBytes(final Set<byte[]> bytes, final Class<V> clazz);

	<V> Map<String, V> deserialize(final Map<String, String> str, final Class<V> clazz);

	<V> Map<byte[], V> deserializeBytes(final Map<byte[], byte[]> bytes, final Class<V> clazz);

	<V> V deserialize(final String str, final TypeReference<V> type);

	<V> V deserializeBytes(final byte[] bytes, final TypeReference<V> type);

	<V> List<V> deserialize(final List<String> str, final TypeReference<V> type);

	<V> List<V> deserializeBytes(final List<byte[]> bytes, final TypeReference<V> type);

	<V> Set<V> deserialize(final Set<String> str, final TypeReference<V> type);

	<V> Set<V> deserializeBytes(final Set<byte[]> bytes, final TypeReference<V> type);

	<V> Map<String, V> deserialize(final Map<String, String> str, final TypeReference<V> type);

	<V> Map<byte[], V> deserializeBytes(final Map<byte[], byte[]> bytes, final TypeReference<V> type);

}
