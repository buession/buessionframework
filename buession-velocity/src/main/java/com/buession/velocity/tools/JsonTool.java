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
 * | Copyright @ 2013-2021 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.velocity.tools;

import com.buession.core.validator.Validate;
import com.buession.lang.Constants;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.PrettyPrinter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.core.util.Separators;
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

	private static PrettyPrinter prettyPrinter;

	private final static Logger logger = LoggerFactory.getLogger(JsonTool.class);

	/**
	 * 将对象编码成字符串
	 *
	 * @param object
	 * 		对象
	 *
	 * @return 对象编码后字符串
	 */
	public String encode(Object object){
		return encode(object, null, null, (TimeZone) null);
	}

	/**
	 * 将对象编码成字符串
	 *
	 * @param object
	 * 		对象
	 * @param ignoreNullValue
	 * 		是否忽略 null 值
	 *
	 * @return 对象编码后字符串
	 */
	public String encode(Object object, Boolean ignoreNullValue){
		return encode(object, ignoreNullValue, (String) null);
	}

	/**
	 * 将对象编码成字符串
	 *
	 * @param object
	 * 		对象
	 * @param dateFormat
	 * 		日期时间格式
	 *
	 * @return 对象编码后字符串
	 */
	public String encode(Object object, String dateFormat){
		return encode(object, null, dateFormat);
	}

	/**
	 * 将对象编码成字符串
	 *
	 * @param object
	 * 		对象
	 * @param dateFormat
	 * 		日期时间格式
	 * @param timeZone
	 * 		时区
	 *
	 * @return 对象编码后字符串
	 */
	public String encode(Object object, String dateFormat, TimeZone timeZone){
		return encode(object, null, dateFormat, timeZone);
	}

	/**
	 * 将对象编码成字符串
	 *
	 * @param object
	 * 		对象
	 * @param ignoreNullValue
	 * 		是否忽略 null 值
	 * @param dateFormat
	 * 		日期时间格式
	 *
	 * @return 对象编码后字符串
	 */
	public String encode(Object object, Boolean ignoreNullValue, String dateFormat){
		return encode(object, ignoreNullValue, dateFormat, (TimeZone) null);
	}

	/**
	 * 将对象编码成字符串
	 *
	 * @param object
	 * 		对象
	 * @param ignoreNullValue
	 * 		是否忽略 null 值
	 * @param dateFormat
	 * 		日期时间格式
	 * @param timeZone
	 * 		时区
	 *
	 * @return 对象编码后字符串
	 */
	public String encode(Object object, Boolean ignoreNullValue, String dateFormat, TimeZone timeZone){
		return encode(object, ignoreNullValue, dateFormat, timeZone, false);
	}

	/**
	 * 将对象编码成字符串
	 *
	 * @param object
	 * 		对象
	 * @param ignoreNullValue
	 * 		是否忽略 null 值
	 * @param pretty
	 * 		是否格式化
	 *
	 * @return 对象编码后字符串
	 *
	 * @since 1.2.2
	 */
	public String encode(Object object, Boolean ignoreNullValue, Boolean pretty){
		return encode(object, ignoreNullValue, null, pretty);
	}

	/**
	 * 将对象编码成字符串
	 *
	 * @param object
	 * 		对象
	 * @param dateFormat
	 * 		日期时间格式
	 * @param pretty
	 * 		是否格式化
	 *
	 * @return 对象编码后字符串
	 *
	 * @since 1.2.2
	 */
	public String encode(Object object, String dateFormat, Boolean pretty){
		return encode(object, null, dateFormat, pretty);
	}

	/**
	 * 将对象编码成字符串
	 *
	 * @param object
	 * 		对象
	 * @param dateFormat
	 * 		日期时间格式
	 * @param timeZone
	 * 		时区
	 * @param pretty
	 * 		是否格式化
	 *
	 * @return 对象编码后字符串
	 *
	 * @since 1.2.2
	 */
	public String encode(Object object, String dateFormat, TimeZone timeZone, Boolean pretty){
		return encode(object, null, dateFormat, timeZone, pretty);
	}

	/**
	 * 将对象编码成字符串
	 *
	 * @param object
	 * 		对象
	 * @param ignoreNullValue
	 * 		是否忽略 null 值
	 * @param dateFormat
	 * 		日期时间格式
	 * @param pretty
	 * 		是否格式化
	 *
	 * @return 对象编码后字符串
	 *
	 * @since 1.2.2
	 */
	public String encode(Object object, Boolean ignoreNullValue, String dateFormat, Boolean pretty){
		return encode(object, ignoreNullValue, dateFormat, null, pretty);
	}

	/**
	 * 将对象编码成字符串
	 *
	 * @param object
	 * 		对象
	 * @param ignoreNullValue
	 * 		是否忽略 null 值
	 * @param dateFormat
	 * 		日期时间格式
	 * @param timeZone
	 * 		时区
	 * @param pretty
	 * 		是否格式化
	 *
	 * @return 对象编码后字符串
	 *
	 * @since 1.2.2
	 */
	public String encode(Object object, Boolean ignoreNullValue, String dateFormat, TimeZone timeZone, Boolean pretty){
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
			if(Boolean.TRUE.equals(pretty)){
				return objectMapper.writer(getPrettyPrinter()).writeValueAsString(object);
			}else{
				return objectMapper.writeValueAsString(object);
			}
		}catch(JsonProcessingException e){
			if(logger.isErrorEnabled()){
				logger.warn("{} convert to string error.", Objects.toString(object, "<null>"), e);
			}
		}

		return null;
	}

	private static PrettyPrinter getPrettyPrinter(){
		if(prettyPrinter == null){
			prettyPrinter = new VelocityPrettyPrinter();
		}

		return prettyPrinter;
	}

	private final static class VelocityPrettyPrinter extends DefaultPrettyPrinter {

		private final static long serialVersionUID = -2266232157759904063L;

		public VelocityPrettyPrinter(){
			super(Constants.EMPTY_STRING);
		}

		public VelocityPrettyPrinter(DefaultPrettyPrinter base){
			super(base);
		}

		@Override
		public DefaultPrettyPrinter withSeparators(Separators separators){
			_separators = separators;
			_objectFieldValueSeparatorWithSpaces =
					separators.getObjectFieldValueSeparator() + Constants.SPACING_STRING;
			return this;
		}

		@Override
		public DefaultPrettyPrinter createInstance(){
			return new VelocityPrettyPrinter(this);
		}

	}

}
