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
 * | Copyright @ 2013-2026 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.client.lettuce.operations;

import com.buession.core.converter.Converter;
import com.buession.redis.client.lettuce.LettuceRedisClient;
import com.buession.redis.client.operations.AbstractRedisOperations;
import com.buession.redis.utils.SafeEncoder;
import io.lettuce.core.KeyValue;
import io.lettuce.core.Value;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Lettuce Redis 命令操作抽象类
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public abstract class AbstractLettuceRedisOperations extends AbstractRedisOperations<LettuceRedisClient>
		implements LettuceRedisOperations {

	protected final Converter<KeyValue<byte[], byte[]>, byte[]> binaryKeyValueToBinaryValueConverter =
			Value::getValue;

	protected final Converter<KeyValue<byte[], byte[]>, String> binaryKeyValueToStringValueConverter =
			(kv)->SafeEncoder.encode(kv.getValue());

	protected final Converter<List<KeyValue<byte[], byte[]>>, Map<byte[], byte[]>> binaryListKeyValueToBinaryMapConverter =
			(list)->{
				final Map<byte[], byte[]> map = new HashMap<>(list.size());

				for(KeyValue<byte[], byte[]> kv : list){
					map.put(kv.getKey(), kv.getValue());
				}

				return map;
			};

	protected final Converter<List<KeyValue<byte[], byte[]>>, Map<String, String>> binaryListKeyValueToStringMapConverter =
			(list)->{
				final Map<String, String> map = new HashMap<>(list.size());

				for(KeyValue<byte[], byte[]> kv : list){
					map.put(SafeEncoder.encode(kv.getKey()), SafeEncoder.encode(kv.getValue()));
				}

				return map;
			};

	public AbstractLettuceRedisOperations(final LettuceRedisClient client) {
		super(client);
	}

}
