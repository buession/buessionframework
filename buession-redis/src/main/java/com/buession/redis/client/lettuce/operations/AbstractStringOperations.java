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

import com.buession.lang.Status;
import com.buession.redis.client.lettuce.LettuceRedisClient;
import com.buession.redis.client.operations.StringOperations;
import com.buession.redis.utils.SafeEncoder;

/**
 * Lettuce 字符串命令操作抽象类
 *
 * @param <C>
 * 		Redis Client {@link LettuceRedisClient}
 *
 * @author Yong.Teng
 * @since 2.4.0
 */
public abstract class AbstractStringOperations<C extends LettuceRedisClient> extends AbstractLettuceRedisOperations<C>
		implements StringOperations {

	public AbstractStringOperations(final C client) {
		super(client);
	}

	@Override
	public Long append(final String key, final String value) {
		return append(SafeEncoder.encode(key), SafeEncoder.encode(value));
	}

	@Override
	public Long incr(final String key) {
		return incr(SafeEncoder.encode(key));
	}

	@Override
	public Long incrBy(final String key, final long value) {
		return incrBy(SafeEncoder.encode(key), value);
	}

	@Override
	public Double incrByFloat(final String key, final double value) {
		return incrByFloat(SafeEncoder.encode(key), value);
	}

	@Override
	public Long decr(final String key) {
		return decr(SafeEncoder.encode(key));
	}

	@Override
	public Long decrBy(final String key, final long value) {
		return decrBy(SafeEncoder.encode(key), value);
	}

	@Override
	public Status pSetEx(final String key, final String value, final int lifetime) {
		return pSetEx(SafeEncoder.encode(key), SafeEncoder.encode(value), lifetime);
	}

	@Override
	public Status set(final String key, final String value) {
		return set(SafeEncoder.encode(key), SafeEncoder.encode(value));
	}

	@Override
	public Status set(final String key, final String value, final SetArgument setArgument) {
		return set(SafeEncoder.encode(key), SafeEncoder.encode(value), setArgument);
	}

	@Override
	public Status setEx(final String key, final String value, final int lifetime) {
		return setEx(SafeEncoder.encode(key), SafeEncoder.encode(value), lifetime);
	}

	@Override
	public Status setNx(final String key, final String value) {
		return setNx(SafeEncoder.encode(key), SafeEncoder.encode(value));
	}

	@Override
	public Long setRange(final String key, final long offset, final String value) {
		return setRange(SafeEncoder.encode(key), offset, SafeEncoder.encode(value));
	}

	@Override
	public String getRange(final String key, final long start, final long end) {
		return SafeEncoder.encode(getRange(SafeEncoder.encode(key), start, end));
	}

	@Override
	public Long strlen(final String key) {
		return strlen(SafeEncoder.encode(key));
	}

}
