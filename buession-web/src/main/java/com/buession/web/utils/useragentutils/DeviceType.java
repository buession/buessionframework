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
 * | Copyright @ 2013-2023 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.web.utils.useragentutils;

/**
 * 设备类型
 *
 * @author Yong.Teng
 * @since 2.2.1
 */
public enum DeviceType {
	/**
	 * 电脑
	 */
	COMPUTER("Computer"),

	/**
	 * 手机
	 */
	MOBILE("Mobile"),

	/**
	 * 平板
	 */
	TABLET("Tablet"),

	/**
	 * Digital media receiver like the Google TV.
	 */
	DMR("Digital media receiver"),

	/**
	 * Game console
	 */
	GAME_CONSOLE("Game console"),

	/**
	 * 未知
	 */
	UNKNOWN("Unknown");

	private final String name;

	DeviceType(final String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

}
