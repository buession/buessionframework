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
 * | Copyright @ 2013-2020 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.core.utils;

import org.junit.Test;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Yong.Teng
 */
public class ReflectionUtilsTest {

	private final static User user = new User();

	{
		user.setId(10);
		user.setUserName("username");
		user.setTimestamp(System.currentTimeMillis());
	}

	@Test
	public void classConvertMap(){
		System.out.println(ReflectUtils.classConvertMap(user));
	}

	@Test
	public void setter(){
		Map<String, Object> data = new LinkedHashMap<>();

		data.put("id", 1);
		data.put("user_name", "test");
		data.put("username", "buession");
		data.put("password", "pass");
		data.put("timestamp", 1000);
		data.put("loginTimes", 1L);
		data.put("todayLoginTimes", 3);
		data.put("isLock", '1');
		data.put("type", (byte) 0);
		data.put("b1", true);
		data.put("b2", Boolean.TRUE);
		data.put("b3", Boolean.TRUE);
		data.put("b4", true);
		data.put("date", System.currentTimeMillis());

		User user = ReflectUtils.setter(data, User.class);
		System.out.println(user);
	}

	public final static class User {

		private int id;

		private String username;

		private String userName;

		private String password;

		private long timestamp;

		private short loginTimes;

		private Short todayLoginTimes;

		private char isLock;

		private byte type;

		private Boolean b1;

		private boolean b2;

		private Boolean b3;

		private boolean b4;

		private Date date;

		public int getId(){
			return id;
		}

		public void setId(int id){
			this.id = id;
		}

		public String getUsername(){
			return username;
		}

		public void setUsername(String username){
			this.username = username;
		}

		public String getUserName(){
			return userName;
		}

		public void setUserName(String userName){
			this.userName = userName;
		}

		public String getPassword(){
			return password;
		}

		public void setPassword(String password){
			this.password = password;
		}

		public long getTimestamp(){
			return timestamp;
		}

		public void setTimestamp(long timestamp){
			this.timestamp = timestamp;
		}

		public short getLoginTimes(){
			return loginTimes;
		}

		public void setLoginTimes(short loginTimes){
			this.loginTimes = loginTimes;
		}

		public Short getTodayLoginTimes(){
			return todayLoginTimes;
		}

		public void setTodayLoginTimes(Short todayLoginTimes){
			this.todayLoginTimes = todayLoginTimes;
		}

		public char getIsLock(){
			return isLock;
		}

		public void setIsLock(char isLock){
			this.isLock = isLock;
		}

		public byte getType(){
			return type;
		}

		public void setType(byte type){
			this.type = type;
		}

		public Boolean getB1(){
			return b1;
		}

		public void setB1(Boolean b1){
			this.b1 = b1;
		}

		public boolean isB2(){
			return b2;
		}

		public void setB2(boolean b2){
			this.b2 = b2;
		}

		public Boolean getB3(){
			return b3;
		}

		public void setB3(Boolean b3){
			this.b3 = b3;
		}

		public boolean isB4(){
			return b4;
		}

		public void setB4(boolean b4){
			this.b4 = b4;
		}

		public Date getDate(){
			return date;
		}

		public void setDate(Date date){
			this.date = date;
		}

		@Override
		public String toString(){
			return "User{" + "id=" + id + ", username='" + username + '\'' + ", userName='" + userName + '\'' + ", " + "password='" + password + '\'' + ", timestamp=" + timestamp + ", loginTimes=" + loginTimes + ", " + "todayLoginTimes=" + todayLoginTimes + ", isLock=" + isLock + ", type=" + type + ", b1=" + b1 + "," + " b2=" + b2 + ", b3=" + b3 + ", b4=" + b4 + ", date=" + date + '}';
		}

	}

}
