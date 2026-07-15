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
package com.buession.redis.utils;

import com.buession.core.collect.Arrays;
import com.buession.core.utils.ByteUtils;
import com.buession.core.validator.Validate;
import com.buession.redis.RedisAccessor;
import com.buession.redis.core.operations.RedisOperations;

/**
 *
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public class KeyUtils {

	private KeyUtils() {

	}

	public static String rawKey(final RedisOperations operations, final String key) {
		if(operations instanceof RedisAccessor redisAccessor){
			String prefix = redisAccessor.getOptions().getPrefix();
			return Validate.isEmpty(prefix) ? key : prefix.concat(key);
		}else{
			return key;
		}
	}

	public static byte[] rawKey(final RedisOperations operations, final byte[] key) {
		if(operations instanceof RedisAccessor redisAccessor){
			byte[] prefix = redisAccessor.getOptions().getPrefixRaw();
			return Validate.isEmpty(prefix) ? key : ByteUtils.concat(prefix, key);
		}else{
			return key;
		}
	}

	public static String[] rawKeys(final RedisOperations operations, final String[] keys) {
		if(Validate.isNotEmpty(keys)){
			return Arrays.map(keys, String.class, (value)->rawKey(operations, value));
		}else{
			return keys;
		}
	}

	public static byte[][] rawKeys(final RedisOperations operations, final byte[][] keys) {
		if(operations instanceof RedisAccessor redisAccessor && Validate.isNotEmpty(keys)){
			byte[] prefix = redisAccessor.getOptions().getPrefixRaw();
			return Arrays.map(keys, byte[].class, (value)->ByteUtils.concat(prefix, value));
		}else{
			return keys;
		}
	}

}
