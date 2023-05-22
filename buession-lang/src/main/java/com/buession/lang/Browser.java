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
	IE("Internet Explorer", BrowserType.WEB_BROWSER, RenderingEngine.TRIDENT, Manufacturer.MICROSOFT),

	EDGE("Microsoft Edge", BrowserType.WEB_BROWSER, RenderingEngine.EDGE_HTML, Manufacturer.MICROSOFT),

	CHROME("Chrome", BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, Manufacturer.GOOGLE),

	FIREFOX("Firefox", BrowserType.WEB_BROWSER, RenderingEngine.GECKO, Manufacturer.MOZILLA),

	SAFARI("Safari", BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, Manufacturer.APPLE),

	OPERA_COAST("Opera Coast", BrowserType.MOBILE_BROWSER, RenderingEngine.WEBKIT, Manufacturer.OPERA),

	OPERA("Opera", BrowserType.WEB_BROWSER, RenderingEngine.PRESTO, Manufacturer.OPERA),

	MOZILLA("Mozilla", BrowserType.WEB_BROWSER, RenderingEngine.OTHER, Manufacturer.MOZILLA),

	DOLFIN2("Samsung Dolphin 2", BrowserType.MOBILE_BROWSER, RenderingEngine.WEBKIT, Manufacturer.SAMSUNG),

	CAMINO("Camino", BrowserType.WEB_BROWSER, RenderingEngine.GECKO, Manufacturer.OTHER),

	LOTUS_NOTES("Lotus Notes", BrowserType.EMAIL_CLIENT, RenderingEngine.OTHER, Manufacturer.OTHER),

	OMNIWEB("Omniweb", BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, Manufacturer.OTHER),

	FLOCK("Flock", BrowserType.WEB_BROWSER, RenderingEngine.GECKO, Manufacturer.OTHER),

	VIVALDI("Vivaldi", BrowserType.WEB_BROWSER, RenderingEngine.BLINK, Manufacturer.OTHER),

	SEAMONKEY("SeaMonkey", BrowserType.WEB_BROWSER, RenderingEngine.GECKO, Manufacturer.OTHER),

	BOT("Robot/Spider", BrowserType.ROBOT, RenderingEngine.OTHER, Manufacturer.OTHER),

	CFNETWORK("CFNetwork", BrowserType.UNKNOWN, RenderingEngine.OTHER, Manufacturer.OTHER),

	EUDORA("Eudora", BrowserType.EMAIL_CLIENT, RenderingEngine.OTHER, Manufacturer.OTHER),

	POCOMAIL("PocoMail", BrowserType.EMAIL_CLIENT, RenderingEngine.OTHER, Manufacturer.OTHER),

	THEBAT("The Bat!", BrowserType.EMAIL_CLIENT, RenderingEngine.OTHER, Manufacturer.OTHER),

	SILK("Silk", BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, Manufacturer.AMAZON),

	BLACKBERRY("BlackBerry", BrowserType.MOBILE_BROWSER, RenderingEngine.WEBKIT, Manufacturer.BLACKBERRY),

	NETFRONT("NetFront", BrowserType.MOBILE_BROWSER, RenderingEngine.OTHER, Manufacturer.OTHER),

	EVOLUTION("Evolution", BrowserType.EMAIL_CLIENT, RenderingEngine.OTHER, Manufacturer.OTHER),

	LYNX("Lynx", BrowserType.TEXT_BROWSER, RenderingEngine.OTHER, Manufacturer.OTHER),

	KONQUEROR("Konqueror", BrowserType.TEXT_BROWSER, RenderingEngine.KHTML, Manufacturer.OTHER),

	XBOX("Xbox", BrowserType.WEB_BROWSER, RenderingEngine.TRIDENT, Manufacturer.MICROSOFT),

	THUNDERBIRD("Thunderbird", BrowserType.EMAIL_CLIENT, RenderingEngine.GECKO, Manufacturer.MOZILLA),

	UNKNOWN("Unknown", BrowserType.UNKNOWN, RenderingEngine.OTHER, Manufacturer.OTHER);

	private final String name;

	private BrowserType browserType;

	private final RenderingEngine renderingEngine;

	private final Manufacturer manufacturer;

	Browser(final String name, final BrowserType browserType, final RenderingEngine renderingEngine,
			final Manufacturer manufacturer){
		this.name = name;
		this.browserType = browserType;
		this.renderingEngine = renderingEngine;
		this.manufacturer = manufacturer;
	}

	public String getName(){
		return name;
	}

	public BrowserType getBrowserType(){
		return browserType;
	}

	public RenderingEngine getRenderingEngine(){
		return renderingEngine;
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

		OPERA("Opera Software ASA"),

		MOZILLA("Mozilla Foundation"),

		GOOGLE("Google Inc."),

		COMPUSERVE("CompuServe Interactive Services, Inc."),

		YAHOO("Yahoo Inc."),

		AOL("AOL LLC."),

		MMC("Mail.com Media Corporation"),

		AMAZON("Amazon.com, Inc."),

		ROKU("Roku, Inc."),

		ADOBE("Adobe Systems Inc."),

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
