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
 * | Copyright @ 2013-2024 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.json;

import com.buession.json.annotation.JsonEnum2Map;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Yong.Teng
 */
public class MapSerializerTest {

	@Test
	public void enum2MapSerializer() {
		Example example = new Example();

		example.setGender(Gender.FEMALE);

		ObjectMapper objectMapper = new ObjectMapper();
		try{
			System.out.println(objectMapper.writeValueAsString(example));
		}catch(JsonProcessingException e){
			e.printStackTrace();
		}
	}

	@Test
	public void map2EnumDeserizlizer() {
		String str = "[{\"id\":1,\"customerId\":1,\"customer\":null,\"email\":\"webmaster@liangvi.com\"," +
				"\"password\":null,\"salt\":null,\"realName\":\"腾勇\",\"mobile\":\"13438174292\"," +
				"\"createdAt\":1487421335000,\"createdIp\":\"127.0.0.1\",\"createdLocation\":null,\"loginTimes\":265," +

				"\"loginAt\":1524409881000," + "\"loginIp\":\"0:0:0:0:0:0:0:1\"," + "\"loginLocation\":null," +
				"\"lastLoginAt\":1524409086000," + "\"lastLoginIp\":\"0:0:0:0:0:0:0:1\"," +
				"\"lastLoginLocation\":null," + "\"status\":{\"value\":1," + "\"description\":\"正常\"}}]";

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		try{
			List<User> users = objectMapper.readValue(str, new TypeReference<List<User>>() {

			});

			System.out.println(users.get(0).getStatus());
		}catch(JsonProcessingException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	@Test
	public void map2EnumDeserizlizerS() {
		String str = "{\"gender\":{\"value\":1," + "\"desc\":\"女\"}}";

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		try{
			Example user = objectMapper.readValue(str, Example.class);

			System.out.println(user.getGender());
		}catch(JsonProcessingException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	private final static class Example {

		@JsonEnum2Map
		private Gender gender;

		@JsonEnum2Map
		public Gender getGender() {
			return gender;
		}

		public void setGender(Gender gender) {
			this.gender = gender;
		}
	}

	private enum Gender {

		MALE(0, "男"),

		FEMALE(1, "女");

		private int value;

		private String desc;

		Gender(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}

	}

	public final static class User implements Serializable {

		private static final long serialVersionUID = -1924992522420542301L;

		private int id;

		private short customerId;

		private String email;

		private String password;

		private String salt;

		private String realName;

		private String mobile;

		private Date createdAt;

		private String createdIp;

		private short loginTimes;

		private Date loginAt;

		private String loginIp;

		private Date lastLoginAt;

		private String lastLoginIp;

		@JsonEnum2Map
		private Status status;

		@JsonIgnore
		private boolean isDeleted;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public short getCustomerId() {
			return customerId;
		}

		public void setCustomerId(short customerId) {
			this.customerId = customerId;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getSalt() {
			return salt;
		}

		public void setSalt(String salt) {
			this.salt = salt;
		}

		public String getRealName() {
			return realName;
		}

		public void setRealName(String realName) {
			this.realName = realName;
		}

		public String getMobile() {
			return mobile;
		}

		public void setMobile(String mobile) {
			this.mobile = mobile;
		}

		public Date getCreatedAt() {
			return createdAt;
		}

		public void setCreatedAt(Date createdAt) {
			this.createdAt = createdAt;
		}

		public String getCreatedIp() {
			return createdIp;
		}

		public void setCreatedIp(String createdIp) {
			this.createdIp = createdIp;
		}

		public short getLoginTimes() {
			return loginTimes;
		}

		public void setLoginTimes(short loginTimes) {
			this.loginTimes = loginTimes;
		}

		public Date getLoginAt() {
			return loginAt;
		}

		public void setLoginAt(Date loginAt) {
			this.loginAt = loginAt;
		}

		public String getLoginIp() {
			return loginIp;
		}

		public void setLoginIp(String loginIp) {
			this.loginIp = loginIp;
		}

		public Date getLastLoginAt() {
			return lastLoginAt;
		}

		public void setLastLoginAt(Date lastLoginAt) {
			this.lastLoginAt = lastLoginAt;
		}

		public String getLastLoginIp() {
			return lastLoginIp;
		}

		public void setLastLoginIp(String lastLoginIp) {
			this.lastLoginIp = lastLoginIp;
		}

		@JsonEnum2Map
		public Status getStatus() {
			return status;
		}

		public void setStatus(Status status) {
			this.status = status;
		}

		@JsonIgnore
		public boolean isDeleted() {
			return getIsDeleted();
		}

		@JsonIgnore
		public boolean getIsDeleted() {
			return isDeleted;
		}

		public void setDeleted(boolean isDeleted) {
			setIsDeleted(isDeleted);
		}

		public void setIsDeleted(boolean isDeleted) {
			this.isDeleted = isDeleted;
		}

		public enum Status {

			// 未激活
			NOT_ACTIVATED(0, "未激活"),

			// 正常
			NORMAL(1, "正常"),

			// 冻结
			FREEZE(2, "冻结");

			private int value;

			private String description;

			Status(int value, String description) {
				this.value = value;
				this.description = description;
			}

			public int getValue() {
				return value;
			}

			public String getDescription() {
				return description;
			}

		}

	}


}
