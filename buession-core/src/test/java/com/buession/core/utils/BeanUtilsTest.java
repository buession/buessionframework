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
package com.buession.core.utils;

import com.buession.lang.Status;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Yong.Teng
 */
public class BeanUtilsTest {

	private final static Map<String, Object> MAP = new HashMap<>();

	static{
		MAP.put("one", 1);
		MAP.put("status", Status.SUCCESS);
		MAP.put("bool", true);
	}

	@Test
	public void populate() throws InvocationTargetException, IllegalAccessException{
		BeanUtils beanUtils = new BeanUtils();

		User user = new User();
		beanUtils.populate(user, MAP);
		System.out.println(user);
	}

	public final static class User {

		private int one;

		private Status status;

		private Boolean bool;

		public int getOne(){
			return one;
		}

		public void setOne(int one){
			this.one = one;
		}

		public Status getStatus(){
			return status;
		}

		public void setStatus(Status status){
			this.status = status;
		}

		public Boolean getBool(){
			return bool;
		}

		public void setBool(Boolean bool){
			this.bool = bool;
		}

		@Override
		public String toString(){
			return "User{" + "one=" + one + ", status=" + status + ", bool=" + bool + '}';
		}

	}

}
