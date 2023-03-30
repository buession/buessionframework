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

import com.buession.core.utils.StringUtils;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.stream.Collectors;

/**
 * @author Yong.Teng
 * @since 2.2.1
 */
public class BrowserTest {

	@Test
	public void parse(){
		Browser browser = Browser.parse(
				"Mozilla/5.0 (Linux; Android 11; V2054A Build/RP1A.200720.012; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/107.0.5304.141 Mobile Safari/537.36 XWEB/5023 MMWEBSDK/20230202 MMWEBID/2072 MicroMessenger/8.0.33.2320(0x28002151) WeChat/arm64 Weixin NetType/5G Language/zh_CN ABI/arm64");
		System.out.println(browser + " " + browser.getBrowserType());
	}

	@Test
	public void toEnum(){
		for(Browser browser : Browser.values()){
			System.out.println(browser.name() + "(\"" + browser.getName() + "\"),");
			System.out.println("");
		}
	}

	@Test
	public void join(){
		System.out.println("'" +
				StringUtils.join(Arrays.stream(Browser.values()).map(Enum::name).toArray(), "', '") + "'");
	}

}
