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

import org.junit.Test;

/**
 * @author Yong.Teng
 * @since 2.2.1
 */
public class OperatingSystemTest {

	@Test
	public void parse(){
		OperatingSystem operatingSystem = OperatingSystem.parse(
				"Mozilla/5.0 (Linux; Android 12; DBY-W09 Build/HUAWEIDBY-W09; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/86.0.4240.99 XWEB/4435 MMWEBSDK/20221206 Safari/537.36 MMWEBID/8494 MicroMessenger/8.0.32.2300(0x2800205D) WeChat/arm64 Weixin Android Tablet NetType/WIFI Language/zh_CN ABI/arm64");
		System.out.println(operatingSystem + " " + operatingSystem.getDeviceType());
	}

}
