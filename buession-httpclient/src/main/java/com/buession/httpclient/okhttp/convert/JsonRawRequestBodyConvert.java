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
package com.buession.httpclient.okhttp.convert;

import com.buession.httpclient.core.JsonRawRequestBody;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Yong.Teng
 */
public class JsonRawRequestBodyConvert implements OkHttpRequestBodyConvert<JsonRawRequestBody> {

	private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	private final static Logger logger = LoggerFactory.getLogger(JsonRawRequestBodyConvert.class);

	@Override
	public okhttp3.RequestBody convert(JsonRawRequestBody source){
		if(source == null || source.getContent() == null){
			return null;
		}

		try{
			String str = OBJECT_MAPPER.writeValueAsString(source.getContent());
			return okhttp3.RequestBody.create(str, MediaType.parse(source.getContentType().valueOf()));
		}catch(JsonProcessingException e){
			logger.error("{} convert to JSON String error.", com.buession.httpclient.core.RequestBody.class.getName(),
					e);
		}

		return null;
	}

}
