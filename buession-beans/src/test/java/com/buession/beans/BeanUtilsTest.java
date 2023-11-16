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
 * | Copyright @ 2013-2021 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.beans;

import org.junit.Test;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.cglib.core.Converter;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Yong.Teng
 * @since 2.0.0
 */
public class BeanUtilsTest {

	@Test
	public void populate() {
		Map<String, Object> data = new HashMap<>();
		data.put("last_login_time", "2023-11-11 00:23:12");

		User user = new User();
		BeanUtils.populate(user, data);

		System.out.println(user);
	}

	@Test
	public void populate1() {
		Map<String, Object> data = new HashMap<>();

		User user = new User();

		user.setAge(100);
		user.setUsername("username");
		user.setLastLoginTime(new Date());

		BeanMap beanMap = BeanMap.create(user);
		beanMap.forEach((key, value)->{
			data.put(String.valueOf(key), value);
		});
		data.forEach((key, value)->{
			System.out.println(key + ": " + value);
		});
	}

	@Test
	public void populate2() {
		Map<String, Object> data = new HashMap<>();
		data.put("last_login_time", "2023-11-11 00:23:12");

		Map<String, Object> data2 = new HashMap<>();
		BeanUtils.populate(data2, data);

		data2.forEach((key, value)->{
			System.out.println(key + ": " + value);
		});
	}

	@Test
	public void copy() {
		Map<String, Object> data = new HashMap<>();
		data.put("age", null);
		data.put("username", "username");
		data.put("height", 3L);
		data.put("enable", "on");
		data.put("last_login_time", new Date());

		User user = new User();
		BeanUtils.copyProperties(user, data);

		System.out.println(user);
	}

	@Test
	public void copy1() {
		Map<String, Object> data = new HashMap<>();

		User user = new User();

		user.setAge(100);
		user.setUsername("username");

		BeanUtils.copyProperties(data, user);
		data.forEach((key, value)->{
			System.out.println(key + ": " + value);
		});
	}

	@Test
	public void copy2() {
		User user = new User();

		user.setId(100);
		user.setUsername("username");

		Person person = new Person();
		BeanUtils.copyProperties(person, user);
		System.out.println(person);
	}

	@Test
	public void copy3() {
		User user = new User();

		user.setId(100);
		user.setUsername("username");

		Person person = new Person();
		BeanUtils.copyProperties(person, user, new Converter() {

			@Override
			public Object convert(Object sourceFieldValue, Class targetFieldType, Object targetSetter) {
				if(sourceFieldValue instanceof Short || sourceFieldValue instanceof Integer){
					if(targetFieldType.isAssignableFrom(Long.class) || targetFieldType.isAssignableFrom(long.class)){
						return sourceFieldValue;
					}
				}else if(sourceFieldValue.getClass().isAssignableFrom(targetFieldType)){
					return sourceFieldValue;
				}

				return null;
			}

		});
		System.out.println(person);
	}

	@Test
	public void toMap() {
		User user = new User();

		user.setAge(100);
		user.setUsername("username");

		Map<String, Object> data = BeanUtils.toMap(user);
		data.forEach((key, value)->{
			System.out.println(key + ": " + value);
		});
	}

	@Test
	public void toMap1() {
		Map<String, Object> data = new HashMap<>();
		data.put("age", null);
		data.put("username", "username");
		data.put("height", 3L);
		//data.put("enable", "on");
		data.put("lastLoginTime1", new Date());
		//data.put("lastLoginTime", "2022-11-12 12:23:00");

		User user = new User();

		BeanMap beanMap = BeanMap.create(user);
		beanMap.putAll(data);

		System.out.println(user);
	}

}
