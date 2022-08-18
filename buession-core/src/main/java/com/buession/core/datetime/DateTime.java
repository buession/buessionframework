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
 * | Copyright @ 2013-2022 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.core.datetime;

/**
 * 日期时间
 *
 * @author Yong.Teng
 */
public class DateTime {

	/**
	 * 当前 Unix 时间戳的微秒数
	 *
	 * @return 以 "msec sec" 的格式返回一个字符串
	 */
	public static String microtime(){
		long timestamp = System.currentTimeMillis();

		final StringBuilder sb = new StringBuilder(24);

		sb.append(timestamp / 1000L).append(" ").append(timestamp % 1000L * 1000L);

		return sb.toString();
	}

	/**
	 * 获取当前 Unix Timestamp
	 *
	 * @return 当前 Unix Timestamp
	 *
	 * @since 1.3.1
	 */
	public static long unixtime(){
		return System.currentTimeMillis() / 1000L;
	}

	/**
	 * 获取指定年的天数
	 *
	 * @param year
	 * 		年份
	 *
	 * @return 指定年月份的天数；如果不是一个合法的年份，则返回 -1
	 *
	 * @since 2.1.1
	 */
	public static int getDays(final int year){
		if(year <= 0){
			return -1;
		}

		int days = 0;

		for(int i = 1; i <= 12; i++){
			days += getDays(year, i);
		}

		return days;
	}

	/**
	 * 获取指定年月份的天数
	 *
	 * @param year
	 * 		年份
	 * @param month
	 * 		月份
	 *
	 * @return 指定年月份的天数；如果不是一个合法的年份或月份，则返回 -1
	 *
	 * @since 2.1.1
	 */
	public static int getDays(final int year, final int month){
		if(year <= 0){
			return -1;
		}

		switch(month){
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
				return 31;
			case 4:
			case 6:
			case 9:
			case 11:
				return 30;
			case 2:
				return isLeapYear(year) ? 29 : 28;
			default:
				return -1;
		}
	}

	/**
	 * 判断指定年份是否为闰年
	 *
	 * @param year
	 * 		年份
	 *
	 * @return 指定年份为闰年，返回 true；否则，返回 false
	 *
	 * @since 2.1.1
	 */
	public static boolean isLeapYear(final int year){
		if(year <= 0){
			return false;
		}

		if(year % 4 == 0){
			if(year % 100 == 0){
				return year % 400 == 0;
			}
		}

		return false;
	}

}
