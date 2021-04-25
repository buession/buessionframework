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
 * | Copyright @ 2013-2021 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.core.jedis;

import com.buession.core.converter.Converter;
import com.buession.redis.core.FutureResult;
import redis.clients.jedis.Response;

import java.util.function.Supplier;

/**
 * @author Yong.Teng
 */
public class JedisResult<V, R> extends FutureResult<Response<V>, V, R> {

	public JedisResult(final Response<V> resultHolder){
		super(resultHolder);
	}

	public JedisResult(final Response<V> resultHolder, final Converter<V, R> converter){
		super(resultHolder, converter);
	}

	public JedisResult(final Response<V> resultHolder, final Converter<V, R> converter,
					   final Supplier<R> defaultConversionResult){
		super(resultHolder, converter, defaultConversionResult);
	}

	@Override
	public V get(){
		return getResultHolder().get();
	}

	public final static class JedisResultBuilder<T, R> {

		private final Response<T> response;

		private Converter<T, R> converter;

		@SuppressWarnings("unchecked")
		JedisResultBuilder(final Response<T> response){
			this.response = response;
			this.converter = (source)->(R) source;
		}

		public final static <T, R> JedisResultBuilder<T, R> forResponse(final Response<T> response){
			return new JedisResultBuilder<>(response);
		}

		public JedisResultBuilder<T, R> mappedWith(Converter<T, R> converter){
			this.converter = converter;
			return this;
		}

		public JedisResult<T, R> build(){
			return new JedisResult<>(response, converter);
		}

	}

}
