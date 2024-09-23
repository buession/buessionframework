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
 * | Copyright @ 2013-2024 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.core.deserializer;

import com.buession.core.type.TypeReference;
import com.buession.core.utils.Assert;
import com.google.gson.Gson;

/**
 * Gson JSON 反序列化
 *
 * @author Yong.Teng
 * @since 2.3.0
 */
public class GsonJsonDeserializer extends AbstractJsonDeserializer<Gson> {

	private final Gson gson = new Gson();

	public GsonJsonDeserializer() {
		configure(gson);
	}

	@Override
	public <V> V deserialize(final String str) throws DeserializerException {
		return gson.fromJson(str, new TypeReference<V>() {

		}.getType());
	}

	@Override
	public <V> V deserialize(final String str, final Class<V> clazz) throws DeserializerException {
		Assert.isNull(str, "String cloud not be null.");

		return gson.fromJson(str, clazz);
	}

	@Override
	public <V> V deserialize(String str, TypeReference<V> type) throws DeserializerException {
		Assert.isNull(str, "String cloud not be null.");

		return gson.fromJson(str, type.getType());
	}

	@Override
	public <V> V deserialize(final byte[] bytes) throws DeserializerException {
		Assert.isNull(bytes, "Bytes cloud not be null.");
		return deserialize(new String(bytes));
	}

	@Override
	public <V> V deserialize(byte[] bytes, Class<V> clazz) throws DeserializerException {
		Assert.isNull(bytes, "Bytes cloud not be null.");
		return deserialize(new String(bytes), clazz);
	}

	@Override
	public <V> V deserialize(byte[] bytes, TypeReference<V> type) throws DeserializerException {
		Assert.isNull(bytes, "Bytes cloud not be null.");
		return deserialize(new String(bytes), type);
	}

}
