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
package com.buession.json.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Yong.Teng
 */
public class DateDeserializers extends com.fasterxml.jackson.databind.deser.std.DateDeserializers {

	protected static Date operatorTime(Date date){
		if(date != null){
			date.setTime(date.getTime() * 1000L);
		}

		return date;
	}

	@JacksonStdImpl
	public static class DateUnixTimestampDeserializer extends DateDeserializer {

		private final static long serialVersionUID = -6750692952981588777L;

		public DateUnixTimestampDeserializer(){
			super();
		}

		public DateUnixTimestampDeserializer(DateUnixTimestampDeserializer base, DateFormat df, String formatString){
			super(base, df, formatString);
		}

		@Override
		protected DateUnixTimestampDeserializer withDateFormat(DateFormat df, String formatString){
			return new DateUnixTimestampDeserializer(this, df, formatString);
		}

		@Override
		protected Date _parseDate(JsonParser parser, DeserializationContext context) throws IOException{
			return operatorTime(super._parseDate(parser, context));
		}

	}

	@JacksonStdImpl
	public static class SqlDateUnixTimestampDeserializer extends SqlDateDeserializer {

		private final static long serialVersionUID = 4255599559201979615L;

		public SqlDateUnixTimestampDeserializer(){
			super();
		}

		public SqlDateUnixTimestampDeserializer(SqlDateUnixTimestampDeserializer base, DateFormat df,
												String formatString){
			super(base, df, formatString);
		}

		@Override
		protected SqlDateUnixTimestampDeserializer withDateFormat(DateFormat df, String formatString){
			return new SqlDateUnixTimestampDeserializer(this, df, formatString);
		}

		@Override
		protected Date _parseDate(JsonParser parser, DeserializationContext context) throws IOException{
			return operatorTime(super._parseDate(parser, context));
		}

	}

	@JacksonStdImpl
	public static class CalendarUnixTimestampDeserializer extends CalendarDeserializer {

		private final static long serialVersionUID = 4711172144147773042L;

		public CalendarUnixTimestampDeserializer(){
			super();
		}

		public CalendarUnixTimestampDeserializer(Class<? extends Calendar> clazz){
			super(clazz);
		}

		public CalendarUnixTimestampDeserializer(CalendarDeserializer src, DateFormat df, String formatString){
			super(src, df, formatString);
		}

		@Override
		protected CalendarUnixTimestampDeserializer withDateFormat(DateFormat df, String formatString){
			return new CalendarUnixTimestampDeserializer(this, df, formatString);
		}

		@Override
		protected Date _parseDate(JsonParser parser, DeserializationContext context) throws IOException{
			return operatorTime(super._parseDate(parser, context));
		}

	}

	@JacksonStdImpl
	public static class TimestampUnixTimestampDeserializer extends TimestampDeserializer {

		private final static long serialVersionUID = 5780827423514223079L;

		public TimestampUnixTimestampDeserializer(){
			super();
		}

		public TimestampUnixTimestampDeserializer(TimestampUnixTimestampDeserializer src, DateFormat df,
												  String formatString){
			super(src, df, formatString);
		}

		@Override
		protected TimestampUnixTimestampDeserializer withDateFormat(DateFormat df, String formatString){
			return new TimestampUnixTimestampDeserializer(this, df, formatString);
		}

		@Override
		protected Date _parseDate(JsonParser parser, DeserializationContext context) throws IOException{
			return operatorTime(super._parseDate(parser, context));
		}

	}

}
