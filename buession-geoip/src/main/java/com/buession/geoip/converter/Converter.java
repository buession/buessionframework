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
 * | Copyright @ 2013-2026 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.geoip.converter;

import java.util.Locale;

/**
 * 将 maxmind GeoIP Record 转换为模型实体类接口
 *
 * @param <M>
 * 		模型实体类
 * @param <R>
 * 		maxmind GeoIP Record
 * @param <RES>
 * 		maxmind Response
 *
 * @author Yong.Teng
 */
@FunctionalInterface
public interface Converter<M, R, RES> {

	/**
	 * 将 maxmind GeoIP Record 转换为模型实体类
	 *
	 * @param record
	 * 		maxmind GeoIP Record
	 *
	 * @return 模型实体类
	 */
	default M converter(R record) {
		return converter(record, null, null);
	}

	/**
	 * 将 maxmind GeoIP Record 转换为模型实体类
	 *
	 * @param record
	 * 		maxmind GeoIP Record
	 * @param locale
	 *        {@link Locale} 实例
	 *
	 * @return 模型实体类
	 */
	default M converter(R record, Locale locale) {
		return converter(record, null, locale);
	}

	/**
	 * 将 maxmind GeoIP Record 转换为模型实体类
	 *
	 * @param record
	 * 		maxmind GeoIP Record
	 * @param response
	 * 		maxmind Response
	 * @param locale
	 *        {@link Locale} 实例
	 *
	 * @return 模型实体类
	 */
	M converter(R record, RES response, Locale locale);

}
