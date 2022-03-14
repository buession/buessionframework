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
package com.buession.httpclient.core;

import com.buession.core.converter.Converter;
import com.buession.httpclient.core.JsonRawRequestBody;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Function;

/**
 * @author Yong.Teng
 * @since 2.0.0
 */
public class RequestBodyConverters {

	public final static class JsonRawRequestBodyConverter<R> implements Converter<JsonRawRequestBody<?>, R> {

		private final Function<String, R> function;

		private final static Logger logger = LoggerFactory.getLogger(JsonRawRequestBodyConverter.class);

		public JsonRawRequestBodyConverter(final Function<String, R> function){
			this.function = function;
		}

		@Override
		public R convert(JsonRawRequestBody<?> requestBody){
			if(requestBody == null || requestBody.getContent() == null){
				return null;
			}

			ObjectMapper objectMapper = new ObjectMapper();
			try{
				String str = objectMapper.writeValueAsString(requestBody.getContent());
				return function.apply(str);
			}catch(JsonProcessingException e){
				if(logger.isErrorEnabled()){
					logger.error("{} convert to JSON String error.", requestBody.getClass().getName(), e);
				}
			}

			return null;
		}

	}

}
