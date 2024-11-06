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
package com.buession.redis.core.internal.lettuce;

import com.buession.core.converter.Converter;
import com.buession.redis.core.FutureResult;
import com.buession.redis.core.internal.convert.Converters;
import io.lettuce.core.RedisFuture;
import io.lettuce.core.protocol.RedisCommand;

/**
 * Lettuce 事务、管道异步结果
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public class LettuceResult<SV, TV> extends FutureResult<RedisCommand<?, SV, ?>> {

	@SuppressWarnings({"unchecked"})
	public LettuceResult(final RedisFuture<SV> resultHolder) {
		super((RedisCommand<?, SV, ?>) resultHolder);
	}

	@SuppressWarnings({"unchecked"})
	public LettuceResult(final RedisFuture<SV> resultHolder, final Converter<SV, TV> converter) {
		super((RedisCommand<?, SV, ?>) resultHolder, converter);
	}

	@SuppressWarnings({"unchecked"})
	@Override
	public SV get() {
		return (SV) getHolder().getOutput().get();
	}

	public final static class Builder<SV, TV> {

		private final RedisFuture<SV> response;

		private Converter<SV, TV> converter;

		private Builder(final RedisFuture<SV> response, final Converter<SV, TV> converter) {
			this.response = response;
			this.converter = converter;
		}

		public static <SV, TV> Builder<SV, TV> fromRedisFuture(RedisFuture<SV> redisFuture) {
			return new LettuceResult.Builder<>(redisFuture, Converters.always());
		}

		public Builder<SV, TV> mappedWith(Converter<SV, TV> converter) {
			this.converter = converter;
			return this;
		}

		public LettuceResult<SV, TV> build() {
			return new LettuceResult<>(response, converter);
		}

	}

}
