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
package com.buession.json;

import com.buession.json.annotation.CalendarUnixTimestamp;
import com.buession.json.annotation.DateUnixTimestamp;
import com.buession.json.annotation.SqlDateUnixTimestamp;
import com.buession.json.annotation.TimestampUnixTimestamp;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Yong.Teng
 */
public class UnixTimestampTest {

	@Test
	public void serialize() throws JsonProcessingException {
		User user = new User();

		user.setCalendar(Calendar.getInstance());
		user.setSqlDate(new java.sql.Date(System.currentTimeMillis()));
		user.setDate(new Date());
		user.setTimestamp(new Timestamp(System.currentTimeMillis()));

		ObjectMapper objectMapper = new ObjectMapper();

		System.out.println(objectMapper.writeValueAsString(user));
	}

	@Test
	public void deserialize() throws JsonProcessingException {
		String str = "{\"date\":1588253558,\"sqlDate\":1588253558,\"calendar\":1588253558,\"timestamp\":1588253558}";

		ObjectMapper objectMapper = new ObjectMapper();

		User user = objectMapper.readValue(str, User.class);

		System.out.println(user);
	}

	private final static class User {

		@DateUnixTimestamp
		private Date date;

		@SqlDateUnixTimestamp
		private java.sql.Date sqlDate;

		@CalendarUnixTimestamp
		private Calendar calendar;

		@TimestampUnixTimestamp
		private Timestamp timestamp;

		public Date getDate() {
			return date;
		}

		public void setDate(Date date) {
			this.date = date;
		}

		public java.sql.Date getSqlDate() {
			return sqlDate;
		}

		public void setSqlDate(java.sql.Date sqlDate) {
			this.sqlDate = sqlDate;
		}

		public Calendar getCalendar() {
			return calendar;
		}

		public void setCalendar(Calendar calendar) {
			this.calendar = calendar;
		}

		public Timestamp getTimestamp() {
			return timestamp;
		}

		public void setTimestamp(Timestamp timestamp) {
			this.timestamp = timestamp;
		}

		@Override
		public String toString() {
			return "User{" + "date=" + date + ", sqlDate=" + sqlDate + ", calendar=" + calendar + ", timestamp=" +
					timestamp + '}';
		}
	}

}
