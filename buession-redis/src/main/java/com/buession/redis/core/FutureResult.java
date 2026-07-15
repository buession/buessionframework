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
package com.buession.redis.core;

import com.buession.core.converter.Converter;
import com.buession.redis.core.internal.convert.Converters;
import org.springframework.lang.Nullable;

/**
 * 事务、管道异步结果
 *
 * @param <SV>
 * 		Redis 命令返回原生结果类型
 * @param <T>
 * 		Response
 *
 * @author Yong.Teng
 */
public abstract class FutureResult<SV, T> {

	private final T holder;

	private final Converter<SV, ?> converter;

	public FutureResult(final T holder) {
		this(holder, Converters.always());
	}

	public FutureResult(final T holder, final Converter<SV, ?> converter) {
		this.holder = holder;
		this.converter = converter;
	}

	public final T getHolder() {
		return holder;
	}

	@Nullable
	public abstract Object get();

	/**
	 * 将 事务、管道
	 *
	 * @param result
	 * 		事务、管道原始返回结果
	 *
	 * @return 转换结果
	 */
	@Nullable
	public Object convert(@Nullable SV result) {
		return result == null ? null : converter.convert(result);
	}

	protected abstract static class BaseBuilder<SV, TV, REP, T, FR extends FutureResult<SV, T>> {

		protected final REP response;

		protected Converter<SV, TV> converter;

		protected BaseBuilder(final REP response, final Converter<SV, TV> converter) {
			this.response = response;
			this.converter = converter;
		}

		public abstract FR build();

	}

}
