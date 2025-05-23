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
 * | Copyright @ 2013-2022 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.core.validator.annotation;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.StringJoiner;

/**
 * @author Yong.Teng
 * @since 2.0.0
 */
@Configuration
@ComponentScan(basePackages = {"com.buession"})
@ContextConfiguration(classes = {NullTest.class})
public class NullTest {

	@Test
	public void test() {
		User user = new User();

		user.setUsername(null);
		System.out.println(user.getUsername());
	}

	@Valid
	private final static class User {

		@Valid
		@Null
		private String username;

		public String getUsername() {
			return username;
		}

		public void setUsername(@Valid @Null String username) {
			this.username = username;
		}

		@Override
		public String toString() {
			return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
					.add("username='" + username + "'")
					.toString();
		}
	}

}
