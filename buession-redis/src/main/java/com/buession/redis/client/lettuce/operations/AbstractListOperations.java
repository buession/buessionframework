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
import com.buession.redis.client.operations.ListOperations;
import com.buession.redis.core.ListPosition;
import com.buession.redis.utils.SafeEncoder;

import java.util.List;

/**
 * Lettuce 列表命令操作抽象类
 *
 * @param <C>
 * 		Redis Client {@link LettuceRedisClient}
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public abstract class AbstractListOperations<C extends LettuceRedisClient> extends AbstractLettuceRedisOperations<C>
		implements ListOperations {

	public AbstractListOperations(final C client) {
		super(client);
	}

	@Override
	public Long lInsert(final String key, final ListPosition position, final String pivot, final String value) {
		return lInsert(SafeEncoder.encode(key), position, SafeEncoder.encode(pivot), SafeEncoder.encode(value));
	}

	@Override
	public Status lSet(final String key, final long index, final String value) {
		return lSet(SafeEncoder.encode(key), index, SafeEncoder.encode(value));
	}

	@Override
	public Long lLen(final String key) {
		return lLen(SafeEncoder.encode(key));
	}

	@Override
	public Long lPos(final String key, final String element) {
		return lPos(SafeEncoder.encode(key), SafeEncoder.encode(element));
	}

	@Override
	public Long lPos(final String key, final String element, final LPosArgument lPosArgument) {
		return lPos(SafeEncoder.encode(key), SafeEncoder.encode(element), lPosArgument);
	}

	@Override
	public List<Long> lPos(final String key, final String element, final LPosArgument lPosArgument, final long count) {
		return lPos(SafeEncoder.encode(key), SafeEncoder.encode(element), lPosArgument, count);
	}

	@Override
	public Long lRem(final String key, final String value, final long count) {
		return lRem(SafeEncoder.encode(key), SafeEncoder.encode(value), count);
	}

	@Override
	public Status lTrim(final String key, final long start, final long end) {
		return lTrim(SafeEncoder.encode(key), start, end);
	}

	@Override
	public Long lPush(final String key, final String... values) {
		return lPush(SafeEncoder.encode(key), SafeEncoder.encode(values));
	}

	@Override
	public Long lPushX(final String key, final String... values) {
		return lPushX(SafeEncoder.encode(key), SafeEncoder.encode(values));
	}

	@Override
	public Long rPush(final String key, final String... values) {
		return rPush(SafeEncoder.encode(key), SafeEncoder.encode(values));
	}

	@Override
	public Long rPushX(final String key, final String... values) {
		return rPushX(SafeEncoder.encode(key), SafeEncoder.encode(values));
	}

}
