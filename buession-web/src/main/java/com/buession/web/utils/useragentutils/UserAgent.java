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
 * | Copyright @ 2013-2023 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.web.utils.useragentutils;

import com.buession.core.validator.Validate;

/**
 * User-Agent 解析操作系统和浏览器信息
 *
 * @author Yong.Teng
 * @since 2.2.1
 */
public class UserAgent {

	/**
	 * 操作系统信息
	 */
	private final OperatingSystem operatingSystem;

	/**
	 * 浏览器信息
	 */
	private final Browser browser;

	/**
	 * 构造函数
	 *
	 * @param userAgent
	 * 		User-Agent
	 */
	public UserAgent(final String userAgent){
		if(Validate.hasText(userAgent)){
			this.browser = Browser.parse(userAgent);
			this.operatingSystem = this.browser == Browser.BOT ? OperatingSystem.UNKNOWN :
					OperatingSystem.parse(userAgent);
		}else{
			this.operatingSystem = OperatingSystem.UNKNOWN;
			this.browser = Browser.UNKNOWN;
		}
	}

	/**
	 * 返回操作系统信息
	 *
	 * @return 操作系统信息
	 */
	public OperatingSystem getOperatingSystem(){
		return operatingSystem;
	}

	/**
	 * 返回浏览器信息
	 *
	 * @return 浏览器信息
	 */
	public Browser getBrowser(){
		return browser;
	}

	@Override
	public String toString(){
		return operatingSystem.toString() + '-' + browser.toString();
	}

}
