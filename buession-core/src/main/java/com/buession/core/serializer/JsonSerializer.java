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
package com.buession.core.serializer;

import com.buession.core.Customizer;

/**
 * JSON 序列化
 *
 * @param <T>
 * 		JSON 序列化原生类型
 *
 * @author Yong.Teng
 */
public interface JsonSerializer<T> extends Serializer {

	/**
	 * 序列化配置
	 *
	 * @param object
	 * 		JSON 原生对象
	 *
	 * @since 3.0.0
	 */
	default void configure(T object) {

	}

	/**
	 * 序列化配置
	 *
	 * @param object
	 * 		JSON 原生对象
	 * @param customizer
	 * 		配置定制
	 *
	 * @since 3.0.0
	 */
	default void configure(T object, Customizer<T> customizer) {
		if(customizer != null){
			customizer.customize(object);
		}
	}

}
