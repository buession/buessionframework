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
 * | License: http://buession.buession.com.cn/LICENSE 												       |
 * | Author: Yong.Teng <webmaster@buession.com> 													       |
 * | Copyright @ 2013-2019 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.json;

import com.buession.json.annotation.JsonEnum2Map;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;
import java.util.StringJoiner;

/**
 * @author Yong.Teng
 */
public class EnumSerializer {

	@Test
	public void test(){
		User user = new User();

		user.setId(100);
		user.setUsername("admin");
		user.setType(Type.ACTIVE);
		user.setTypes(new Type[]{Type.ACTIVE, Type.REGISTER});

		ObjectMapper objectMapper = new ObjectMapper();

		try{
			String str = objectMapper.writeValueAsString(user);
			System.out.println(str);

			User u2 = objectMapper.readValue(str, User.class);
			System.out.println(u2);
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	public final static class User {

		private int id;

		private String username;

		@JsonEnum2Map
		private Type type;
		
		private Type[] types;

		private int[] ivs;

		public int getId(){
			return id;
		}

		public void setId(final int id){
			this.id = id;
		}

		public String getUsername(){
			return username;
		}

		public void setUsername(final String username){
			this.username = username;
		}

		public Type getType(){
			return type;
		}

		public void setType(final Type type){
			this.type = type;
		}

		public Type[] getTypes(){
			return types;
		}

		public void setTypes(Type[] types){
			this.types = types;
		}

		public int[] getIvs(){
			return ivs;
		}

		public void setIvs(int[] ivs){
			this.ivs = ivs;
		}

		@Override
		public String toString(){
			return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
					.add("id=" + id)
					.add("username='" + username + "'")
					.add("type=" + type)
					.toString();
		}
	}

	public enum Type {

		REGISTER("register", "注册模板"),

		FIND_PASSWORD("find_password", "找回密码"),

		ACTIVE("active", "激活账号");

		private String value;

		private String description;

		Type(String value, String description){
			this.value = value;
			this.description = description;
		}

		public String getValue(){
			return value;
		}

		public String getDescription(){
			return description;
		}

	}

}
