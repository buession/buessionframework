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
package com.buession.redis.core.internal.convert.jedis.response;

import com.buession.core.converter.Converter;
import com.buession.redis.core.JsonType;

import java.math.BigDecimal;

/**
 * Jedis {@link Class} 转换为 {@link JsonType}
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public final class JsonTypeConverter implements Converter<Class<?>, JsonType> {

	@Override
	public JsonType convert(final Class<?> source) {
		if(source == null){
			return null;
		}

		if(source.isArray()){
			return JsonType.ARRAY;
		}else if(source.isAssignableFrom(boolean.class) || source.isAssignableFrom(Boolean.class) ||
				source.isAssignableFrom(Boolean.TYPE)){
			return JsonType.BOOLEAN;
		}else if(source.isAssignableFrom(long.class) || source.isAssignableFrom(Long.class) ||
				source.isAssignableFrom(Long.TYPE)){
			return JsonType.INTEGER;
		}else if(source.isAssignableFrom(int.class) || source.isAssignableFrom(Integer.class) ||
				source.isAssignableFrom(Integer.TYPE)){
			return JsonType.INTEGER;
		}else if(source.isAssignableFrom(short.class) || source.isAssignableFrom(Short.class) ||
				source.isAssignableFrom(Short.TYPE)){
			return JsonType.INTEGER;
		}else if(source.isAssignableFrom(float.class) || source.isAssignableFrom(Float.class) ||
				source.isAssignableFrom(Float.TYPE)){
			return JsonType.NUMBER;
		}else if(source.isAssignableFrom(double.class) || source.isAssignableFrom(Double.class) ||
				source.isAssignableFrom(Double.TYPE)){
			return JsonType.NUMBER;
		}else if(source.isAssignableFrom(Number.class) || source.isAssignableFrom(BigDecimal.class)){
			return JsonType.NUMBER;
		}else if(source.isAssignableFrom(String.class) || source.isAssignableFrom(CharSequence.class)){
			return JsonType.STRING;
		}else if(source.isAssignableFrom(Object.class)){
			return JsonType.OBJECT;
		}else{
			return JsonType.UNKNOWN;
		}
	}

}
