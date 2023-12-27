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
package com.buession.json;

import com.buession.json.annotation.Sensitive;
import com.buession.json.strategy.SensitiveStrategy;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

/**
 * @author Yong.Teng
 * @since 1.3.1
 */
public class SensitiveTest {

	@Test
	public void sensitive() {
		User user = new User();

		user.setUsername("github");
		user.setIdCard("11032119850112225X");
		user.setTel("88888888");
		user.setMobile("13800138000");
		user.setIpV4("255.255.255.255");
		user.setIpV6("fe80::ca7c:797e:8c6b:e927");
		user.setAddress("四川省成都市锦江区东大路");
		user.setAddress1(new StringBuilder("四川省成都市锦江区东大路"));
		user.setPrice(100);
		user.setFormat("eduosi@163.com");
		user.setEmail("ed@163.com");
		user.setQq("10000");
		user.setQq1("251329");
		user.setQq2("2513290410");

		ObjectMapper objectMapper = new ObjectMapper();
		try{
			System.out.println(objectMapper.writeValueAsString(user));
		}catch(JsonProcessingException e){
			e.printStackTrace();
		}
	}

	private final static class User {

		@Sensitive(strategy = SensitiveStrategy.USERNAME)
		private String username;

		@Sensitive(strategy = SensitiveStrategy.ID_CARD, replacement = "----")
		private String idCard;

		@Sensitive(strategy = SensitiveStrategy.TEL)
		private String tel;

		@Sensitive(strategy = SensitiveStrategy.MOBILE)
		private String mobile;

		@Sensitive(strategy = SensitiveStrategy.IP)
		private String ipV4;

		@Sensitive(strategy = SensitiveStrategy.IP)
		private String ipV6;

		@Sensitive(strategy = SensitiveStrategy.ADDRESS)
		private String address;

		@Sensitive(strategy = SensitiveStrategy.ADDRESS)
		private StringBuilder address1;

		@Sensitive(strategy = SensitiveStrategy.ADDRESS)
		private Integer price;

		@Sensitive(format = "@", replacement = "at")
		private String format;

		@Sensitive(strategy = SensitiveStrategy.EMAIL)
		private String email;

		@Sensitive(strategy = SensitiveStrategy.QQ)
		private String qq;

		@Sensitive(strategy = SensitiveStrategy.QQ)
		private String qq1;

		@Sensitive(strategy = SensitiveStrategy.QQ)
		private String qq2;

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getIdCard() {
			return idCard;
		}

		public void setIdCard(String idCard) {
			this.idCard = idCard;
		}

		public String getTel() {
			return tel;
		}

		public void setTel(String tel) {
			this.tel = tel;
		}

		public String getMobile() {
			return mobile;
		}

		public void setMobile(String mobile) {
			this.mobile = mobile;
		}

		public String getIpV4() {
			return ipV4;
		}

		public void setIpV4(String ipV4) {
			this.ipV4 = ipV4;
		}

		public String getIpV6() {
			return ipV6;
		}

		public void setIpV6(String ipV6) {
			this.ipV6 = ipV6;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public StringBuilder getAddress1() {
			return address1;
		}

		public void setAddress1(StringBuilder address1) {
			this.address1 = address1;
		}

		public Integer getPrice() {
			return price;
		}

		public void setPrice(Integer price) {
			this.price = price;
		}

		public String getFormat() {
			return format;
		}

		public void setFormat(String format) {
			this.format = format;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getQq() {
			return qq;
		}

		public void setQq(String qq) {
			this.qq = qq;
		}

		public String getQq1() {
			return qq1;
		}

		public void setQq1(String qq1) {
			this.qq1 = qq1;
		}

		public String getQq2() {
			return qq2;
		}

		public void setQq2(String qq2) {
			this.qq2 = qq2;
		}
	}

}
