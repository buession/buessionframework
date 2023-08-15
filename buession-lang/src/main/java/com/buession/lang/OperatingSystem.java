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
package com.buession.lang;

/**
 * 操作系统
 *
 * @author Yong.Teng
 * @since 2.3.0
 */
public enum OperatingSystem {
	WINDOWS("Windows", DeviceType.COMPUTER, Manufacturer.MICROSOFT),

	WINDOWS_PHONE("Windows Phone", DeviceType.MOBILE, Manufacturer.MICROSOFT),

	WINDOWS_MOBILE("Windows Mobile", DeviceType.MOBILE, Manufacturer.MICROSOFT),

	XBOX("Xbox OS", DeviceType.GAME_CONSOLE, Manufacturer.MICROSOFT),

	ANDROID("Android", DeviceType.MOBILE, Manufacturer.GOOGLE),

	IOS("iOS", DeviceType.MOBILE, Manufacturer.APPLE),

	CHROME_OS("Chrome OS", DeviceType.COMPUTER, Manufacturer.GOOGLE),

	MAC_OS_X("Mac OS X", DeviceType.COMPUTER, Manufacturer.APPLE),

	MAC_OS("Mac OS", DeviceType.COMPUTER, Manufacturer.APPLE),

	WEBOS("WebOS", DeviceType.MOBILE, Manufacturer.HP),

	PALM("PalmOS", DeviceType.MOBILE, Manufacturer.HP),

	MEEGO("MeeGo", DeviceType.COMPUTER, Manufacturer.NOKIA),

	MAEMO("Maemo", DeviceType.MOBILE, Manufacturer.NOKIA),

	BADA("Bada", DeviceType.MOBILE, Manufacturer.SAMSUNG),

	TIZEN("Tizen", DeviceType.COMPUTER, Manufacturer.LINUX_FOUNDATION),

	KINDLE("Kindle", DeviceType.TABLET, Manufacturer.AMAZON),

	UBUNTU("Ubuntu", DeviceType.COMPUTER, Manufacturer.CONONICAL),

	LINUX("Linux", DeviceType.COMPUTER, Manufacturer.OTHER),

	SUN_OS("SunOS", DeviceType.COMPUTER, Manufacturer.SUN),

	BLACKBERRY("BlackBerryOS", DeviceType.MOBILE, Manufacturer.BLACKBERRY),

	SYMBIAN("Symbian OS", DeviceType.MOBILE, Manufacturer.SYMBIAN),

	SERIES40("Series 40", DeviceType.MOBILE, Manufacturer.NOKIA),

	SONY_ERICSSON("Sony Ericsson", DeviceType.MOBILE, Manufacturer.SONY_ERICSSON),

	PSP("Sony Playstation", DeviceType.GAME_CONSOLE, Manufacturer.SONY),

	WII("Nintendo Wii", DeviceType.GAME_CONSOLE, Manufacturer.NINTENDO),

	ROKU("Roku OS", DeviceType.DMR, Manufacturer.ROKU),

	UNKNOWN("Unknown", DeviceType.COMPUTER, Manufacturer.OTHER);

	private final String name;

	private final DeviceType deviceType;

	private final Manufacturer manufacturer;

	OperatingSystem(final String name, final DeviceType deviceType, final Manufacturer manufacturer){
		this.name = name;
		this.deviceType = deviceType;
		this.manufacturer = manufacturer;
	}

	public String getName(){
		return name;
	}

	public DeviceType getDeviceType(){
		return deviceType;
	}

	public Manufacturer getManufacturer(){
		return manufacturer;
	}

	@Override
	public String toString(){
		return name;
	}

	public enum Manufacturer {

		MICROSOFT("Microsoft Corporation"),

		APPLE("Apple Inc."),

		SUN("Sun Microsystems, Inc."),

		SYMBIAN("Symbian Ltd."),

		NOKIA("Nokia Corporation"),

		BLACKBERRY("Research In Motion Limited"),

		HP("Hewlett Packard"),

		SONY_ERICSSON("Sony Ericsson Mobile Communications AB"),

		SAMSUNG("Samsung Electronics"),

		SONY("Sony Computer Entertainment, Inc."),

		NINTENDO("Nintendo"),

		GOOGLE("Google Inc."),

		AMAZON("Amazon.com, Inc."),

		ROKU("Roku, Inc."),

		CONONICAL("Canonical Ltd."),

		LINUX_FOUNDATION("Linux Foundation"),

		OTHER("Other");

		private final String name;

		Manufacturer(final String name){
			this.name = name;
		}

		public String getName(){
			return name;
		}

	}

}
