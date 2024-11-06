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
package com.buession.redis.client.lettuce.operations;

import com.buession.core.utils.NumberUtils;
import com.buession.lang.KeyValue;
import com.buession.lang.Status;
import com.buession.redis.client.lettuce.LettuceRedisClient;
import com.buession.redis.client.operations.HashOperations;
import com.buession.redis.core.ExpireOption;
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.command.args.HScanArgument;
import com.buession.redis.core.internal.convert.Converters;
import com.buession.redis.utils.SafeEncoder;

import java.util.List;
import java.util.Map;

/**
 * Lettuce 哈希表命令操作抽象类
 *
 * @param <C>
 * 		Redis Client {@link LettuceRedisClient}
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public abstract class AbstractHashOperations<C extends LettuceRedisClient> extends AbstractLettuceRedisOperations<C>
		implements HashOperations {

	public AbstractHashOperations(final C client) {
		super(client);
	}

	@Override
	public Long hDel(final String key, final String... fields) {
		return hDel(SafeEncoder.encode(key), SafeEncoder.encode(fields));
	}

	@Override
	public Boolean hExists(final String key, final String field) {
		return hExists(SafeEncoder.encode(key), SafeEncoder.encode(field));
	}

	@Override
	public List<Long> hExpire(final String key, final int lifetime, final String... fields) {
		return hExpire(SafeEncoder.encode(key), lifetime, SafeEncoder.encode(fields));
	}

	@Override
	public List<Long> hExpire(final String key, final int lifetime, final ExpireOption expireOption,
							  final String... fields) {
		return hExpire(SafeEncoder.encode(key), lifetime, expireOption, SafeEncoder.encode(fields));
	}

	@Override
	public List<Long> hExpireAt(final String key, final long unixTimestamp, final String... fields) {
		return hExpireAt(SafeEncoder.encode(key), unixTimestamp, SafeEncoder.encode(fields));
	}

	@Override
	public List<Long> hExpireAt(final String key, final long unixTimestamp, final ExpireOption expireOption,
								final String... fields) {
		return hExpireAt(SafeEncoder.encode(key), unixTimestamp, expireOption, SafeEncoder.encode(fields));
	}

	@Override
	public List<Long> hExpireTime(final String key, final String... fields) {
		return hExpireTime(SafeEncoder.encode(key), SafeEncoder.encode(fields));
	}

	@Override
	public Long hIncrBy(final String key, final String field, final long value) {
		return hIncrBy(SafeEncoder.encode(key), SafeEncoder.encode(field), value);
	}

	@Override
	public Double hIncrByFloat(final String key, final String field, final double value) {
		return hIncrByFloat(SafeEncoder.encode(key), SafeEncoder.encode(field), value);
	}

	@Override
	public Long hLen(final String key) {
		return hLen(SafeEncoder.encode(key));
	}

	@Override
	public Status hMSet(final String key, final Map<String, String> data) {
		return hMSet(SafeEncoder.encode(key), Converters.mapStringToBinary().convert(data));
	}

	public List<Long> hPersist(final String key, final String... fields) {
		return hPersist(SafeEncoder.encode(key), SafeEncoder.encode(fields));
	}

	@Override
	public List<Long> hpExpire(final String key, final int lifetime, final String... fields) {
		return hpExpire(SafeEncoder.encode(key), lifetime, SafeEncoder.encode(fields));
	}

	@Override
	public List<Long> hpExpire(final String key, final int lifetime, final ExpireOption expireOption,
							   final String... fields) {
		return hpExpire(SafeEncoder.encode(key), lifetime, expireOption, SafeEncoder.encode(fields));
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final long cursor) {
		return hScan(key, Long.toString(cursor));
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final long cursor) {
		return hScan(key, NumberUtils.long2bytes(cursor));
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final long cursor,
												 final HScanArgument<String> scanArgument) {
		return hScan(key, Long.toString(cursor), scanArgument);
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final long cursor,
												 final HScanArgument<byte[]> scanArgument) {
		return hScan(key, NumberUtils.long2bytes(cursor), scanArgument);
	}

	@Override
	public Long hSet(final String key, final KeyValue<String, String>... data) {
		return hSet(SafeEncoder.encode(key), data);
	}

	@Override
	public Status hSetNx(final String key, final String field, final String value) {
		return hSetNx(SafeEncoder.encode(key), SafeEncoder.encode(field), SafeEncoder.encode(value));
	}

	@Override
	public List<Long> hTtl(final String key, final String... fields) {
		return hTtl(SafeEncoder.encode(key), SafeEncoder.encode(fields));
	}

	@Override
	public Long hStrLen(final String key, final String field) {
		return hStrLen(SafeEncoder.encode(key), SafeEncoder.encode(field));
	}

}
