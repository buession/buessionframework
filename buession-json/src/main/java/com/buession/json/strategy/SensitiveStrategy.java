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
package com.buession.json.strategy;

import java.util.function.Function;

/**
 * 脱敏策略
 *
 * @author Yong.Teng
 * @since 1.3.1
 */
public enum SensitiveStrategy {

	/**
	 * 无脱敏策略
	 */
	NONE(str->str),

	/**
	 * 用户名脱敏策略
	 */
	USERNAME(str->str.replaceAll("(\\S)\\S(\\S*)", "$1*$2")),

	/**
	 * 身份证号码脱敏策略
	 */
	ID_CARD(str->str.replaceAll("(\\d{5})\\d{10}(\\d{4}[\\dX])", "$1****$2")),

	/**
	 * 座机号码脱敏策略
	 */
	TEL(str->str.replaceAll("(\\d{3})\\d{2,3}(\\d{2})", "$1****$2")),

	/**
	 * 手机号码脱敏策略
	 */
	MOBILE(str->str.replaceAll("(1\\d{2})\\d{4}(\\d{4})", "$1****$2")),

	/**
	 * QQ 号码脱敏策略
	 */
	QQ(str->str.replaceAll("(([1-9]\\d{2})\\d{2,5}(\\d{2}))|(([1-9]\\d)\\d{2,3}(\\d))", "$2$5****$3$6")),

	/**
	 * IP 地址脱敏策略
	 */
	IP(str->str.replaceAll("((2(5[0-5]|[0-4]\\d))|[0-1]?\\d{1,2})(\\.((2(5[0-5]|[0-4]\\d))|[0-1]?\\d{1,2})){3}",
			"$1.****.$2")),
	/**
	 * 地址脱敏策略
	 */
	ADDRESS(str->str.replaceAll("(\\S{3})\\S{2}(\\S*)\\S{2}", "$1****$2****"));

	private final Function<String, String> function;

	SensitiveStrategy(final Function<String, String> function){
		this.function = function;
	}

	public Function<String, String> getFunction(){
		return function;
	}

}
