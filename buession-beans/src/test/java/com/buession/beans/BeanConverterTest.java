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
package com.buession.beans;

import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Yong.Teng
 * @since 2.3.1
 */
public class BeanConverterTest {

	@Test
	public void mapToMap() {
		Map<String, Object> data = new HashMap<>();
		Map<String, Object> target = new HashMap<>();

		data.put("age", null);
		data.put("username", "username");
		data.put("height", 3L);
		data.put("enable", "on");
		data.put("last_login_time", new Date());

		BeanConverter beanConverter = new DefaultBeanConverter();
		beanConverter.convert(data, target);
		target.forEach((key, value)->{
			System.out.println(key + ": " + value);
		});
	}

	@Test
	public void mapToBean() {
		Map<String, Object> data = new HashMap<>();

		data.put("age", null);
		data.put("username", "username");
		data.put("height", 3L);
		data.put("enable", "on");
		//data.put("last_login_time", new Date());
		data.put("last_login_time", "2022-11-12T12:23:00");
		data.put("arr", new int[]{1, 2});

		User user = new User();
		BeanConverter beanConverter = new DefaultBeanConverter();
		beanConverter.convert(data, user);
		System.out.println(user);
	}

	@Test
	public void beanToMap() {
		User user = new User();

		user.setAge(100);
		user.setUsername("username");
		user.setArr(new String[]{"A", "B"});

		Map<String, Object> data = new HashMap<>();
		BeanConverter beanConverter = new DefaultBeanConverter();
		beanConverter.convert(user, data);
		data.forEach((key, value)->{
			System.out.println(key + ": " + value);
		});
	}

}
