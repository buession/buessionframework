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
 * | Copyright @ 2013-2024 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.serializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.buession.core.type.TypeReference;

import java.nio.charset.Charset;

/**
 * FastJSON 序列化和反序列化
 *
 * @author Yong.Teng
 */
public class FastJsonJsonSerializer extends AbstractSerializer {

	@Override
	public <V> String serialize(final V object) {
		return object == null ? null : JSON.toJSONString(object);
	}

	@Override
	public <V> byte[] serializeAsBytes(final V object) {
		if(object != null){
			return JSON.toJSONBytes(Charset.defaultCharset(), object, SerializeConfig.globalInstance,
					new SerializeFilter[0], null, JSON.DEFAULT_GENERATE_FEATURE);
		}

		return null;
	}

	@Override
	public <V> V deserialize(final String str) {
		if(str != null){
			return JSON.parseObject(str, new com.alibaba.fastjson.TypeReference<V>() {

			});
		}

		return null;
	}

	@Override
	public <V> V deserialize(final String str, final Class<V> clazz) {
		return str == null ? null : JSON.parseObject(str, clazz);
	}

	@Override
	public <V> V deserialize(final String str, final TypeReference<V> type) {
		return str == null ? null : JSON.parseObject(str, type.getType());
	}

	@Override
	public <V> V deserializeBytes(final byte[] bytes) {
		return bytes == null ? null : JSON.parseObject(new String(bytes), new com.alibaba.fastjson.TypeReference<V>() {

		});
	}

	@Override
	public <V> V deserializeBytes(final byte[] bytes, final Class<V> clazz) {
		return bytes == null ? null : JSON.parseObject(new String(bytes), clazz);
	}

	@Override
	public <V> V deserializeBytes(final byte[] bytes, final TypeReference<V> type) {
		return bytes == null ? null : JSON.parseObject(new String(bytes), type.getType());
	}

}
