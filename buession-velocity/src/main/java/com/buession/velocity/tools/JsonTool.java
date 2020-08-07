/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license
 * agreements. See the NOTICE file distributed with this work for additional information regarding
 * copyright ownership. The ASF licenses this file to you under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with the License. You may obtain
 * a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 *
 * =================================================================================================
 *
 * This software consists of voluntary contributions made by many individuals on behalf of the
 * Apache Software Foundation. For more information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 * +------------------------------------------------------------------------------------------------+
 * | License: http://www.apache.org/licenses/LICENSE-2.0.txt 										|
 * | Author: Yong.Teng <webmaster@buession.com> 													|
 * | Copyright @ 2013-2020 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.velocity.tools;

import com.buession.core.validator.Validate;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.velocity.tools.config.DefaultKey;
import org.apache.velocity.tools.generic.SafeConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Objects;
import java.util.TimeZone;

/**
 * @author Yong.Teng
 */
@DefaultKey("json")
public class JsonTool extends SafeConfig {

	private final static Logger logger = LoggerFactory.getLogger(JsonTool.class);

	public String encode(Object object){
		return encode(object, null, null, null);
	}

	public String encode(Object object, Boolean ignoreNullValue){
		return encode(object, ignoreNullValue, null);
	}

	public String encode(Object object, String dateFormat){
		return encode(object, null, dateFormat);
	}

	public String encode(Object object, String dateFormat, TimeZone timeZone){
		return encode(object, null, dateFormat, timeZone);
	}

	public String encode(Object object, Boolean ignoreNullValue, String dateFormat){
		return encode(object, ignoreNullValue, dateFormat, null);
	}

	public String encode(Object object, Boolean ignoreNullValue, String dateFormat, TimeZone timeZone){
		ObjectMapper objectMapper = new ObjectMapper();

		if(Boolean.TRUE.equals(ignoreNullValue)){
			objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		}

		if(Validate.hasText(dateFormat)){
			SimpleDateFormat smt = new SimpleDateFormat(dateFormat);
			objectMapper.setDateFormat(smt);

			if(timeZone != null){
				objectMapper.setTimeZone(timeZone);
			}
		}

		try{
			return objectMapper.writeValueAsString(object);
		}catch(JsonProcessingException e){
			logger.warn("{} convert to string error.", Objects.toString(object, "<null>"), e);
		}

		return null;
	}

}
