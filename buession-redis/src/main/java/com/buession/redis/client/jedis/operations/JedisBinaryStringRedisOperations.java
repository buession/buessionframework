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
package com.buession.redis.client.jedis.operations;

import com.buession.redis.client.operations.BinaryStringRedisOperations;

/**
 * @author Yong.Teng
 */
public interface JedisBinaryStringRedisOperations extends BinaryStringRedisOperations {

	@Override
	default Long incrBy(final byte[] key, final int value){
		return incrBy(key, (long) value);
	}

	@Override
	default Double incrByFloat(final byte[] key, final float value){
		return incrByFloat(key, (double) value);
	}

	@Override
	default Long decrBy(final byte[] key, final int value){
		return decrBy(key, (long) value);
	}

	@Override
	default Long setRange(final byte[] key, final int offset, final byte[] value){
		return setRange(key, (long) offset, value);
	}

	@Override
	default byte[] getRange(final byte[] key, final int start, final int end){
		return getRange(key, (long) start, (long) end);
	}

	@Override
	default byte[] substr(final byte[] key, final long start, final long end){
		return substr(key, (int) start, (int) end);
	}

}