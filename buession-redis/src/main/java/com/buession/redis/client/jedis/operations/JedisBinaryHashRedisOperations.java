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

import com.buession.core.utils.NumberUtils;
import com.buession.redis.client.BinaryHashRedisOperations;
import com.buession.redis.core.ScanResult;

import java.util.Map;

/**
 * @author Yong.Teng
 */
public interface JedisBinaryHashRedisOperations extends BinaryHashRedisOperations {

	@Override
	default Long hIncrBy(final byte[] key, final byte[] field, final int value){
		return hIncrBy(key, field, (long) value);
	}

	@Override
	default Double hIncrByFloat(final byte[] key, final byte[] field, final float value){
		return hIncrByFloat(key, field, (double) value);
	}

	@Override
	default Long hDecrBy(final byte[] key, final byte[] field, final int value){
		return hDecrBy(key, field, (long) value);
	}

	@Override
	default ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final int cursor){
		return hScan(key, NumberUtils.int2bytes(cursor));
	}

	@Override
	default ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final long cursor){
		return hScan(key, NumberUtils.long2bytes(cursor));
	}

	@Override
	default ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final int cursor, final byte[] pattern){
		return hScan(key, NumberUtils.int2bytes(cursor), pattern);
	}

	@Override
	default ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final long cursor, final byte[] pattern){
		return hScan(key, NumberUtils.long2bytes(cursor), pattern);
	}

	@Override
	default ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final int cursor, final int count){
		return hScan(key, NumberUtils.int2bytes(cursor), count);
	}

	@Override
	default ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final long cursor, final int count){
		return hScan(key, NumberUtils.long2bytes(cursor), count);
	}

	@Override
	default ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final int cursor, final byte[] pattern,
			final int count){
		return hScan(key, NumberUtils.int2bytes(cursor), pattern, count);
	}

	@Override
	default ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final long cursor, final byte[] pattern,
			final int count){
		return hScan(key, NumberUtils.long2bytes(cursor), pattern, count);
	}

}
