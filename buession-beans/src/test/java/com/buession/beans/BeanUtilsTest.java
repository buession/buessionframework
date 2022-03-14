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
	public void populate(){
		Map<String, Object> data = new HashMap<>();
		data.put("age", null);
		data.put("username", "username");
		data.put("height", 3L);
		data.put("enable", "on");
		data.put("last_login_time", new Date());

		User user = new User();
		BeanUtils.populate(user, data);

		System.out.println(user);
	}

	@Test
	public void copy(){
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
	public void copy1(){
		Map<String, Object> data = new HashMap<>();

		User user = new User();

		user.setAge(100);
		user.setUsername("username");

		try{
			System.out.println(org.apache.commons.beanutils.BeanUtils.describe(user));
		}catch(IllegalAccessException e){
			e.printStackTrace();
		}catch(InvocationTargetException e){
			e.printStackTrace();
		}catch(NoSuchMethodException e){
			e.printStackTrace();
		}
	}

}
