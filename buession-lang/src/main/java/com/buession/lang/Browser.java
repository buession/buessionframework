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
 * 浏览器
 *
 * @author Yong.Teng
 * @since 2.3.0
 */
public enum Browser {
	IE("Internet Explorer", Manufacturer.MICROSOFT),

	EDGE("Microsoft Edge", Manufacturer.MICROSOFT),

	CHROME("Chrome", Manufacturer.GOOGLE),

	FIREFOX("Firefox", Manufacturer.MOZILLA),

	SAFARI("Safari", Manufacturer.APPLE),

	OPERA_COAST("Opera Coast", Manufacturer.OPERA),

	OPERA("Opera", Manufacturer.OPERA),

	MOZILLA("Mozilla", Manufacturer.MOZILLA),

	DOLFIN2("Samsung Dolphin 2", Manufacturer.SAMSUNG),

	CAMINO("Camino", Manufacturer.OTHER),

	LOTUS_NOTES("Lotus Notes", Manufacturer.OTHER),

	OMNIWEB("Omniweb", Manufacturer.OTHER),

	FLOCK("Flock", Manufacturer.OTHER),

	VIVALDI("Vivaldi", Manufacturer.OTHER),

	SEAMONKEY("SeaMonkey", Manufacturer.OTHER),

	BOT("Robot/Spider", Manufacturer.OTHER),

	CFNETWORK("CFNetwork", Manufacturer.OTHER),

	EUDORA("Eudora", Manufacturer.OTHER),

	POCOMAIL("PocoMail", Manufacturer.OTHER),

	THEBAT("The Bat!", Manufacturer.OTHER),

	SILK("Silk", Manufacturer.AMAZON),

	BLACKBERRY("BlackBerry", Manufacturer.BLACKBERRY),

	NETFRONT("NetFront", Manufacturer.OTHER),

	EVOLUTION("Evolution", Manufacturer.OTHER),

	LYNX("Lynx", Manufacturer.OTHER),

	KONQUEROR("Konqueror", Manufacturer.OTHER),

	XBOX("Xbox", Manufacturer.MICROSOFT),

	THUNDERBIRD("Thunderbird", Manufacturer.MOZILLA),

	UNKNOWN("Unknown", Manufacturer.OTHER);

	private final String name;

	private final Manufacturer manufacturer;

	Browser(final String name, final Manufacturer manufacturer){
		this.name = name;
		this.manufacturer = manufacturer;
	}

	public String getName(){
		return name;
	}

	public Manufacturer getManufacturer(){
		return manufacturer;
	}

	@Override
	public String toString(){
		return name;
	}

	/**
	 * 浏览器类型
	 *
	 * @author Yong.Teng
	 * @since 2.3.0
	 */
	public enum Type {

		/**
		 * Standard web-browser
		 */
		WEB_BROWSER("Browser"),

		/**
		 * Special web-browser for mobile devices
		 */
		MOBILE_BROWSER("Browser (mobile)"),

		/**
		 * Text only browser like the good old Lynx
		 */
		TEXT_BROWSER("Browser (text only)"),

		/**
		 * Email client like Thunderbird
		 */
		EMAIL_CLIENT("Email Client"),

		/**
		 * Search robot, spider, crawler,...
		 */
		ROBOT("Robot"),

		/**
		 * Application
		 */
		APPLICATION("Application"),

		UNKNOWN("unknown");

		private final String name;

		Type(final String name){
			this.name = name;
		}

		public String getName(){
			return name;
		}

	}

	/**
	 * 浏览器厂商
	 *
	 * @author Yong.Teng
	 * @since 2.3.0
	 */
	public enum Manufacturer {

		MICROSOFT("Microsoft Corporation"),

		APPLE("Apple Inc."),

		BLACKBERRY("Research In Motion Limited"),

		SAMSUNG("Samsung Electronics"),

		OPERA("Opera Software ASA"),

		MOZILLA("Mozilla Foundation"),

		GOOGLE("Google Inc."),

		MMC("Mail.com Media Corporation"),

		AMAZON("Amazon.com, Inc."),

		ROKU("Roku, Inc."),

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
