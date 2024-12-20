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
package com.buession.redis;

import com.buession.core.collect.Arrays;
import com.buession.core.utils.ByteUtils;
import com.buession.core.validator.Validate;
import com.buession.redis.client.connection.datasource.DataSource;
import com.buession.redis.core.command.*;
import com.buession.redis.utils.SafeEncoder;

/**
 * @author Yong.Teng
 */
public abstract class AbstractRedisTemplate extends RedisAccessor implements BitMapCommands, ClusterCommands,
		ConnectionCommands, GenericCommand, GeoCommands, HashCommands, HyperLogLogCommands, KeyCommands, ListCommands,
		PubSubCommands, ScriptingCommands, ServerCommands, SetCommands, SortedSetCommands, StreamCommands,
		StringCommands, TransactionCommands {

	/**
	 * 构造函数
	 */
	public AbstractRedisTemplate() {
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		数据源
	 */
	public AbstractRedisTemplate(DataSource dataSource) {
		super(dataSource);
	}

	protected final String rawKey(final String key) {
		String prefix = getOptions().getPrefix();
		return Validate.isEmpty(prefix) ? key : prefix.concat(key);
	}

	protected final byte[] rawKey(byte[] key) {
		String prefix = getOptions().getPrefix();
		return Validate.isEmpty(prefix) ? key : ByteUtils.concat(SafeEncoder.encode(prefix), key);
	}

	protected final String[] rawKeys(final String[] keys) {
		String prefix = getOptions().getPrefix();
		return Validate.isEmpty(prefix) || Validate.isEmpty(keys) ? keys : Arrays.map(keys, String.class, this::rawKey);
	}

	protected final byte[][] rawKeys(final byte[][] keys) {
		String prefix = getOptions().getPrefix();

		if(Validate.isEmpty(prefix) || Validate.isEmpty(keys)){
			return keys;
		}

		byte[] prefixByte = SafeEncoder.encode(prefix);
		return Arrays.map(keys, byte[].class, (value)->ByteUtils.concat(prefixByte, value));
	}

}
