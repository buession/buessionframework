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
 * | Copyright @ 2013-2022 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.core.jedis;

import com.buession.core.converter.Converter;
import com.buession.redis.core.FutureResult;
import redis.clients.jedis.Response;

/**
 * Jedis 事务、管道异步结果
 *
 * @author Yong.Teng
 */
public class JedisResult<SV, TV> extends FutureResult<Response<SV>, SV, TV> {

	public JedisResult(final Response<SV> resultHolder){
		super(resultHolder);
	}

	public JedisResult(final Response<SV> resultHolder, final Converter<SV, TV> converter){
		super(resultHolder, converter);
	}

	@Override
	public SV get(){
		return getHolder().get();
	}

	public final static class Builder<SV, TV> {

		private final Response<SV> response;

		private Converter<SV, TV> converter;

		private Builder(final Response<SV> response, final Converter<SV, TV> converter){
			this.response = response;
			this.converter = converter;
		}

		@SuppressWarnings("unchecked")
		public static <SV, TV> Builder<SV, TV> forResponse(Response<SV> response){
			return new Builder<>(response, (source)->(TV) source);
		}

		public Builder<SV, TV> mappedWith(Converter<SV, TV> converter){
			this.converter = converter;
			return this;
		}

		public JedisResult<SV, TV> build(){
			return new JedisResult<>(response, converter);
		}

	}

}
