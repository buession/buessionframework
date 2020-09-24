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
 * | Copyright @ 2013-2020 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.beans;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Yong.Teng
 */
public class BeanResolverTest {

	@Test
	public void describe() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException{
		User user = new User();

		user.setHeight(1);

		BeanResolver beanResolver = new DefaultBeanResolver();
		Map<String, Object> result = beanResolver.describe(user);

		if(result != null){
			result.forEach((key, value)->{
				System.out.println(key + ": " + value);
			});
		}
	}

	@Test
	public void populate() throws InvocationTargetException, IllegalAccessException{
		User user = new User();

		Map<String, Object> data = new HashMap<>(16);
		data.put("a", "A");

		Map<String, Object> map = new HashMap<>();

		//map.put("id", 1);
		//map.put("username", 1);
		//map.put("age", 11.1F);
		//map.put("enable", Boolean.TRUE);
		//map.put("disable", true);
		//map.put("last_login_time", new Date());
		//map.put("last_login_time", "Fri Aug 28 13:47:53 CST 2020");
		//map.put("lastLoginTime", "00:23:45, 2020-11-12");
		//map.put("map", data);
		map.put("status", 1L);

		map.put("arr", new String[]{"A", "B"});

		BeanResolver beanResolver = new DefaultBeanResolver();
		beanResolver.populate(user, map);
		System.out.println(user);
	}

}
