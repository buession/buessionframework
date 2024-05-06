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
package com.buession.json.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.ser.std.CalendarSerializer;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import com.fasterxml.jackson.databind.ser.std.DateTimeSerializerBase;
import com.fasterxml.jackson.databind.ser.std.SqlDateSerializer;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Yong.Teng
 */
public abstract class DateSerializers extends DateTimeSerializerBase<Date> {

	private final static long serialVersionUID = -8938527999507846087L;

	protected DateSerializers(Class<Date> type, Boolean useTimestamp, DateFormat customFormat) {
		super(type, useTimestamp, customFormat);
	}

	protected static long toUnixTimestamp(final long milliseconds) {
		return milliseconds / 1000L;
	}

	@JacksonStdImpl
	public static class DateUnixTimestampSerializer extends DateSerializer {

		private final static long serialVersionUID = 2504203219658968035L;

		public DateUnixTimestampSerializer() {
			super();
		}

		public DateUnixTimestampSerializer(Boolean useTimestamp, DateFormat customFormat) {
			super(useTimestamp, customFormat);
		}

		@Override
		public DateUnixTimestampSerializer withFormat(Boolean timestamp, DateFormat customFormat) {
			return new DateUnixTimestampSerializer(timestamp, customFormat);
		}

		@Override
		protected long _timestamp(Date value) {
			return value == null ? 0L : toUnixTimestamp(value.getTime());
		}

	}

	@JacksonStdImpl
	public static class SqlDateUnixTimestampSerializer extends SqlDateSerializer {

		private final static long serialVersionUID = -3824050561583196936L;

		public SqlDateUnixTimestampSerializer() {
			super();
		}

		public SqlDateUnixTimestampSerializer(Boolean useTimestamp, DateFormat customFormat) {
			super(useTimestamp, customFormat);
		}

		@Override
		public SqlDateUnixTimestampSerializer withFormat(Boolean timestamp, DateFormat customFormat) {
			return new SqlDateUnixTimestampSerializer(timestamp, customFormat);
		}

		@Override
		protected long _timestamp(java.sql.Date value) {
			return value == null ? 0L : toUnixTimestamp(value.getTime());
		}

	}

	@JacksonStdImpl
	public static class CalendarUnixTimestampSerializer extends CalendarSerializer {

		private final static long serialVersionUID = -5324440667812086058L;

		public CalendarUnixTimestampSerializer() {
			super();
		}

		public CalendarUnixTimestampSerializer(Boolean useTimestamp, DateFormat customFormat) {
			super(useTimestamp, customFormat);
		}

		@Override
		public CalendarUnixTimestampSerializer withFormat(Boolean timestamp, DateFormat customFormat) {
			return new CalendarUnixTimestampSerializer(timestamp, customFormat);
		}

		@Override
		protected long _timestamp(Calendar value) {
			return value == null ? 0L : toUnixTimestamp(value.getTimeInMillis());
		}

	}

	@JacksonStdImpl
	public static class TimestampUnixTimestampSerializer extends DateTimeSerializerBase<Timestamp> {

		private final static long serialVersionUID = -7692464161183919456L;

		public TimestampUnixTimestampSerializer() {
			this(null, null);
		}

		public TimestampUnixTimestampSerializer(Boolean useTimestamp, DateFormat customFormat) {
			super(Timestamp.class, useTimestamp, customFormat);
		}

		@Override
		public TimestampUnixTimestampSerializer withFormat(Boolean timestamp, DateFormat customFormat) {
			return new TimestampUnixTimestampSerializer(timestamp, customFormat);
		}

		@Override
		public void serialize(Timestamp value, JsonGenerator generator, SerializerProvider provider) throws
				IOException {
			if(_asTimestamp(provider)){
				generator.writeNumber(_timestamp(value));
				return;
			}

			_serializeAsString(new Date(value.getTime()), generator, provider);
		}

		@Override
		protected long _timestamp(Timestamp value) {
			return value == null ? 0L : toUnixTimestamp(value.getTime());
		}

	}

}
