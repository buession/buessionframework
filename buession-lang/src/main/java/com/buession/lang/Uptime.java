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
package com.buession.lang;

import java.io.Serializable;

/**
 * 服务运行时长
 *
 * @author Yong.Teng
 */
public class Uptime implements Serializable {

	private final static long serialVersionUID = 8606322314991439595L;

	/**
	 * 自服务启动以来，经过的天数
	 */
	private int days;

	/**
	 * 自服务启动以来，经过的秒数
	 */
	private int seconds;

	/**
	 * 默认构造函数
	 */
	public Uptime(){
	}

	/**
	 * 构造函数
	 *
	 * @param days
	 * 		自服务启动以来，经过的天数
	 * @param seconds
	 * 		自服务启动以来，经过的秒数
	 */
	public Uptime(int days, int seconds){
		this.days = days;
		this.seconds = seconds;
	}

	/**
	 * 获取自服务启动以来，经过的天数
	 *
	 * @return 自服务启动以来，经过的天数
	 */
	public int getDays(){
		return days;
	}

	/**
	 * 设置自服务启动以来，经过的天数
	 *
	 * @param days
	 * 		自服务启动以来，经过的天数
	 */
	public void setDays(int days){
		this.days = days;
	}

	/**
	 * 获取自服务启动以来，经过的秒数
	 *
	 * @return 自服务启动以来，经过的秒数
	 */
	public int getSeconds(){
		return seconds;
	}

	/**
	 * 设置自服务启动以来，经过的秒数
	 *
	 * @param seconds
	 * 		自服务启动以来，经过的秒数
	 */
	public void setSeconds(int seconds){
		this.seconds = seconds;
	}

	@Override
	public String toString(){
		final StringBuilder sb = new StringBuilder(32);

		sb.append("days=").append(days).append(", ");
		sb.append("seconds=").append(seconds);

		return sb.toString();
	}

}
