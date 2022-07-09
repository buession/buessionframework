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
 * | Copyright @ 2013-2022 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.geoip.converter;

import com.maxmind.geoip2.model.AbstractResponse;
import com.maxmind.geoip2.record.AbstractRecord;

import java.util.Locale;

/**
 * 将 maxmind geoip Record 转换为模型实体类接口
 *
 * @param <M>
 * 		模型实体类
 * @param <S>
 * 		maxmind geoip Record {@link AbstractRecord}
 * @param <R>
 * 		maxmind Response {@link AbstractResponse}
 *
 * @author Yong.Teng
 */
@FunctionalInterface
public interface Converter<M, S extends AbstractRecord, R extends AbstractResponse> {

	/**
	 * 将 maxmind geoip Record 转换为模型实体类
	 *
	 * @param s
	 * 		maxmind geoip Record {@link AbstractRecord}
	 *
	 * @return 模型实体类
	 */
	default M converter(S s){
		return converter(s, null, null);
	}

	/**
	 * 将 maxmind geoip Record 转换为模型实体类
	 *
	 * @param s
	 * 		maxmind geoip Record {@link AbstractRecord}
	 * @param locale
	 *        {@link Locale} 实例
	 *
	 * @return 模型实体类
	 */
	default M converter(S s, Locale locale){
		return converter(s, null, locale);
	}

	/**
	 * 将 maxmind geoip Record 转换为模型实体类
	 *
	 * @param s
	 * 		maxmind geoip Record {@link AbstractRecord}
	 * @param response
	 * 		maxmind Response  {@link AbstractResponse}
	 *
	 * @return 模型实体类
	 */
	@Deprecated
	default M converter(S s, R response){
		return converter(s, response, null);
	}

	/**
	 * 将 maxmind geoip Record 转换为模型实体类
	 *
	 * @param s
	 * 		maxmind geoip Record {@link AbstractRecord}
	 * @param response
	 * 		maxmind Response  {@link AbstractResponse}
	 * @param locale
	 *        {@link Locale} 实例
	 *
	 * @return 模型实体类
	 */
	M converter(S s, R response, Locale locale);

}
