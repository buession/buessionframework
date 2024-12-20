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
package com.buession.web.http.request.annotation;

import com.buession.core.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.lang.Nullable;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.function.Function;

/**
 * @author Yong.Teng
 * @since 2.3.0
 */
public class RequestClientIpAnnotationUtils {

	private final static Logger logger = LoggerFactory.getLogger(RequestClientIpAnnotationUtils.class);

	private RequestClientIpAnnotationUtils() {

	}

	public static boolean checkSupports(final MethodParameter parameter) {
		if(parameter.hasParameterAnnotation(RequestClientIp.class) == false){
			return false;
		}

		Class<?> clazz = parameter.nestedIfOptional().getNestedParameterType();
		return CharSequence.class.isAssignableFrom(clazz) || Long.class.isAssignableFrom(clazz) ||
				InetAddress.class.isAssignableFrom(clazz);
	}

	@Nullable
	public static Object resolveNamedValue(final MethodParameter parameter,
										   final Function<MethodParameter, String> fn) {
		Class<?> clazz = parameter.nestedIfOptional().getNestedParameterType();

		if(Long.class.isAssignableFrom(clazz)){
			String[] numbers = StringUtils.splitByWholeSeparatorPreserveAllTokens(fn.apply(parameter), ".");
			long result = 0L;

			for(int i = 0; i < 4; ++i){
				result = result << 8 | Integer.parseInt(numbers[i]);
			}

			return result;
		}else if(CharSequence.class.isAssignableFrom(clazz)){
			return fn.apply(parameter);
		}else if(InetAddress.class.isAssignableFrom(clazz)){
			final String ip = fn.apply(parameter);
			try{
				return InetAddress.getByName(ip);
			}catch(UnknownHostException e){
				logger.error("IP: <{}> convert to InetAddress error: {}", ip, e.getMessage());
			}
		}

		return null;
	}

}
