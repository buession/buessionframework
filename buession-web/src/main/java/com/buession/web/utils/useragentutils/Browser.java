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

import com.buession.core.utils.StringUtils;
import com.buession.web.utils.useragentutils.browsertypefetcher.BrowserTypeFetcher;
import com.buession.web.utils.useragentutils.browsertypefetcher.ContainsBrowserTypeFetcher;
import com.buession.web.utils.useragentutils.versionfetcher.MapVersionFetcher;
import com.buession.web.utils.useragentutils.versionfetcher.PatternVersionFetcher;
import com.buession.web.utils.useragentutils.versionfetcher.SequentialVersionFetcher;
import com.buession.web.utils.useragentutils.versionfetcher.VersionFetcher;

/**
 * 浏览器
 *
 * @author Yong.Teng
 * @since 2.2.1
 */
public enum Browser {
	IE(
			"Internet Explorer",
			new String[]{"MSIE", "Trident", "IE "},
			null,
			BrowserType.WEB_BROWSER,
			new BrowserTypeFetcher[]{
					new ContainsBrowserTypeFetcher("IEMobile", BrowserType.MOBILE_BROWSER)
			},
			RenderingEngine.TRIDENT,
			Manufacturer.MICROSOFT,
			new VersionMapping[]{
					new VersionMapping(new String[]{"IEMobile/11", "Trident/7"}, "11"),
					new VersionMapping(new String[]{"IEMobile/10"}, "10"),
					new VersionMapping(new String[]{"IEMobile/9"}, "9"),
					new VersionMapping(new String[]{"IEMobile/8"}, "8"),
					new VersionMapping(new String[]{"IEMobile/7"}, "7"),
					new VersionMapping(new String[]{"IEMobile/6"}, "6"),
					new VersionMapping(new String[]{"IEMobile/5"}, "5")
			}, new PatternVersionFetcher("MSIE (([\\d]+)\\.([\\w]+))")
	),

	EDGE(
			"Microsoft Edge",
			new String[]{"Edge"},
			null,
			BrowserType.WEB_BROWSER,
			new BrowserTypeFetcher[]{
					new ContainsBrowserTypeFetcher("Mobile Safari", BrowserType.MOBILE_BROWSER)
			},
			RenderingEngine.EDGE_HTML,
			Manufacturer.MICROSOFT,
			null,
			new PatternVersionFetcher("(?:Edge\\/(([0-9]+)\\.([0-9]*)))")
	),

	CHROME(
			"Chrome",
			new String[]{"Chrome", "CrMo", "CriOS"},
			null,
			BrowserType.WEB_BROWSER,
			new BrowserTypeFetcher[]{
					new ContainsBrowserTypeFetcher("CrMo", BrowserType.MOBILE_BROWSER),
					new ContainsBrowserTypeFetcher("CriOS", BrowserType.MOBILE_BROWSER),
					new ContainsBrowserTypeFetcher("Mobile", BrowserType.MOBILE_BROWSER)
			},
			RenderingEngine.WEBKIT,
			Manufacturer.GOOGLE,
			null,
			new PatternVersionFetcher("(?:CriOS|CrMo|Chrome)\\/(([0-9]+)\\.?([\\w]+)?(\\.[\\w]+)?(\\.[\\w]+)?)")
	),

	FIREFOX(
			"Firefox",
			new String[]{"Firefox", "FxiOS"},
			null,
			BrowserType.WEB_BROWSER,
			new BrowserTypeFetcher[]{
					new ContainsBrowserTypeFetcher("Firefox/3.5 Maemo", BrowserType.MOBILE_BROWSER),
					new ContainsBrowserTypeFetcher("Mobile", BrowserType.MOBILE_BROWSER)
			},
			RenderingEngine.GECKO,
			Manufacturer.MOZILLA,
			null,
			new PatternVersionFetcher("Firefox\\/(([0-9]+)\\.?([\\w]+)?(\\.[\\w]+)?(\\.[\\w]+)?)")
	),

	SAFARI(
			"Safari",
			new String[]{"Safari"},
			new String[]{"bot", "preview", "OPR/", "Coast/", "Vivaldi", "CFNetwork", "Phantom"},
			BrowserType.WEB_BROWSER,
			new BrowserTypeFetcher[]{
					new ContainsBrowserTypeFetcher("Mobile Safari", BrowserType.MOBILE_BROWSER),
					new ContainsBrowserTypeFetcher("Mobile/", BrowserType.MOBILE_BROWSER)
			},
			RenderingEngine.WEBKIT,
			Manufacturer.APPLE,
			null,
			new SequentialVersionFetcher(
					new PatternVersionFetcher("Version\\/(([0-9]+)\\.?([\\w]+)?(\\.[\\w]+)?)"),
					new MapVersionFetcher("AppleWebKit/(\\d+(?:.\\d+){1,2})", SafariUtils.getWebKitToSafariVersion())
			)
	),

	OPERA_COAST(
			"Opera Coast",
			new String[]{"Coast/"},
			null,
			BrowserType.MOBILE_BROWSER,
			null,
			RenderingEngine.WEBKIT,
			Manufacturer.OPERA,
			null,
			new PatternVersionFetcher("Coast\\/(([\\d]+)\\.([\\w]+)?(\\.[\\w]+)?(\\.[\\w]+)?)")
	),

	OPERA(
			"Opera",
			new String[]{" OPR/", "Opera"},
			null,
			BrowserType.WEB_BROWSER,
			new BrowserTypeFetcher[]{
					new ContainsBrowserTypeFetcher("Opera Mini", BrowserType.MOBILE_BROWSER),
					new ContainsBrowserTypeFetcher("Mobile", BrowserType.MOBILE_BROWSER)
			},
			RenderingEngine.PRESTO,
			Manufacturer.OPERA,
			new VersionMapping[]{
					new VersionMapping(new String[]{"Opera/12", "Version/12."}, "12"),
					new VersionMapping(new String[]{"Opera/11", "Version/11."}, "11"),
					new VersionMapping(new String[]{"Opera/10", "Version/10."}, "10"),
					new VersionMapping(new String[]{"Opera/9", "Version/9."}, "9")
			},
			new PatternVersionFetcher("OPR\\/(([\\d]+)\\.([\\w]+)?(\\.[\\w]+)?(\\.[\\w]+)?)")
	),

	MOZILLA(
			"Mozilla",
			new String[]{"Mozilla", "Moozilla"},
			null,
			BrowserType.WEB_BROWSER,
			new BrowserTypeFetcher[]{
					new ContainsBrowserTypeFetcher("Tablet", BrowserType.MOBILE_BROWSER),
					new ContainsBrowserTypeFetcher("iPhone", BrowserType.MOBILE_BROWSER),
					new ContainsBrowserTypeFetcher("Pad", BrowserType.MOBILE_BROWSER),
					new ContainsBrowserTypeFetcher("Pod", BrowserType.MOBILE_BROWSER),
					new ContainsBrowserTypeFetcher("Mobile", BrowserType.MOBILE_BROWSER)
			},
			RenderingEngine.OTHER,
			Manufacturer.MOZILLA,
			null,
			null
	),

	DOLFIN2(
			"Samsung Dolphin 2",
			new String[]{"Dolfin/2"},
			null,
			BrowserType.MOBILE_BROWSER,
			null,
			RenderingEngine.WEBKIT,
			Manufacturer.SAMSUNG,
			null,
			null
	),

	CAMINO(
			"Camino",
			new String[]{"Camino"},
			null,
			BrowserType.WEB_BROWSER,
			null,
			RenderingEngine.GECKO,
			Manufacturer.OTHER,
			null,
			new PatternVersionFetcher("Camino\\/(([0-9]+)\\.?([\\w]+)?(\\.[\\w]+)?)")
	),

	LOTUS_NOTES(
			"Lotus Notes",
			new String[]{"Lotus-Notes"},
			null,
			BrowserType.EMAIL_CLIENT,
			null,
			RenderingEngine.OTHER,
			Manufacturer.OTHER,
			null,
			new PatternVersionFetcher("Lotus-Notes\\/(([\\d]+)\\.([\\w]+))")
	),

	OMNIWEB(
			"Omniweb",
			new String[]{"OmniWeb"}, null,
			BrowserType.WEB_BROWSER,
			null,
			RenderingEngine.WEBKIT,
			Manufacturer.OTHER,
			null,
			null
	),

	FLOCK(
			"Flock",
			new String[]{"Flock"},
			null,
			BrowserType.WEB_BROWSER,
			null,
			RenderingEngine.GECKO,
			Manufacturer.OTHER,
			null,
			new PatternVersionFetcher("Flock\\/(([0-9]+)\\.?([\\w]+)?(\\.[\\w]+)?)")
	),

	VIVALDI(
			"Vivaldi",
			new String[]{"Vivaldi"},
			null,
			BrowserType.WEB_BROWSER,
			null,
			RenderingEngine.BLINK,
			Manufacturer.OTHER,
			null,
			new PatternVersionFetcher("Vivaldi/(([\\d]+).([\\d]+).([\\d]+).([\\d]+))")
	),

	SEAMONKEY(
			"SeaMonkey",
			new String[]{"SeaMonkey"},
			null,
			BrowserType.WEB_BROWSER,
			null,
			RenderingEngine.GECKO,
			Manufacturer.OTHER,
			null,
			new PatternVersionFetcher("SeaMonkey\\/(([0-9]+)\\.?([\\w]+)?(\\.[\\w]+)?)")
	),

	BOT(
			"Robot/Spider",
			new String[]{"Googlebot", "Googlebot-Mobile", "Mediapartners-Google", "Web Preview", "bot",
					"Applebot", "spider", "crawler", "Feedfetcher", "Slurp", "Twiceler", "Nutch", "BecomeBot",
					"bingbot", "BingPreview", "Google Web Preview", "WordPress.com mShots", "Seznam",
					"facebookexternalhit", "YandexMarket", "Teoma", "ThumbSniper", "Phantom", "Go-http-client",
					"Java/", "python-requests", "YandexBot", "AdsBot-Google", "AhrefsBot"},
			null,
			BrowserType.ROBOT,
			null,
			RenderingEngine.OTHER,
			Manufacturer.OTHER,
			null,
			null
	),

	CFNETWORK(
			"CFNetwork",
			new String[]{"CFNetwork"},
			null,
			BrowserType.UNKNOWN,
			null,
			RenderingEngine.OTHER,
			Manufacturer.OTHER,
			null,
			new PatternVersionFetcher("CFNetwork/(([\\d]+)(?:\\.([\\d]))?(?:\\.([\\d]+))?)")
	),

	EUDORA(
			"Eudora",
			new String[]{"Eudora", "EUDORA"},
			null,
			BrowserType.EMAIL_CLIENT,
			null,
			RenderingEngine.OTHER,
			Manufacturer.OTHER,
			null,
			null
	),

	POCOMAIL(
			"PocoMail",
			new String[]{"PocoMail"},
			null,
			BrowserType.EMAIL_CLIENT,
			null,
			RenderingEngine.OTHER,
			Manufacturer.OTHER,
			null,
			null
	),

	THEBAT(
			"The Bat!",
			new String[]{"The Bat"},
			null,
			BrowserType.EMAIL_CLIENT,
			null,
			RenderingEngine.OTHER,
			Manufacturer.OTHER,
			null,
			null
	),

	SILK(
			"Silk",
			new String[]{"Silk/"},
			null,
			BrowserType.WEB_BROWSER,
			null,
			RenderingEngine.WEBKIT,
			Manufacturer.AMAZON,
			null,
			new PatternVersionFetcher("Silk\\/(([0-9]+)\\.?([\\w]+)?(\\.[\\w]+)?(\\-[\\w]+)?)")
	),

	BLACKBERRY(
			"BlackBerry",
			new String[]{"BB10"},
			null,
			BrowserType.MOBILE_BROWSER,
			null,
			RenderingEngine.WEBKIT,
			Manufacturer.BLACKBERRY,
			null,
			null
	),

	NETFRONT(
			"NetFront",
			new String[]{"NetFront"},
			null,
			BrowserType.MOBILE_BROWSER,
			null,
			RenderingEngine.OTHER,
			Manufacturer.OTHER,
			null,
			null
	),

	EVOLUTION(
			"Evolution",
			new String[]{"CamelHttpStream"},
			null,
			BrowserType.EMAIL_CLIENT,
			null,
			RenderingEngine.OTHER,
			Manufacturer.OTHER,
			null,
			null
	),

	LYNX(
			"Lynx",
			new String[]{"Lynx"},
			null,
			BrowserType.TEXT_BROWSER,
			null,
			RenderingEngine.OTHER,
			Manufacturer.OTHER,
			null,
			new PatternVersionFetcher("Lynx\\/(([0-9]+)\\.([\\d]+)\\.?([\\w-+]+)?\\.?([\\w-+]+)?)")
	),

	KONQUEROR(
			"Konqueror",
			new String[]{"Konqueror"},
			new String[]{"Exabot"},
			BrowserType.TEXT_BROWSER,
			null,
			RenderingEngine.KHTML,
			Manufacturer.OTHER,
			null,
			new PatternVersionFetcher("Konqueror\\/(([0-9]+)\\.?([\\w]+)?(-[\\w]+)?)")
	),

	XBOX(
			"Xbox",
			new String[]{"xbox"},
			null,
			BrowserType.WEB_BROWSER,
			null,
			RenderingEngine.TRIDENT,
			Manufacturer.MICROSOFT,
			null,
			null
	),

	THUNDERBIRD(
			"Thunderbird",
			new String[]{"Thunderbird"},
			null,
			BrowserType.EMAIL_CLIENT,
			null,
			RenderingEngine.GECKO,
			Manufacturer.MOZILLA,
			null,
			new PatternVersionFetcher("Thunderbird\\/(([0-9]+)\\.?([\\w]+)?(\\.[\\w]+)?(\\.[\\w]+)?)")
	),

	UNKNOWN(
			"Unknown",
			null,
			null,
			BrowserType.UNKNOWN,
			null,
			RenderingEngine.OTHER,
			Manufacturer.OTHER,
			null,
			null
	);

	private final String name;

	private final String[] patterns;

	private final String[] excludePatterns;

	private BrowserType browserType;

	private final BrowserTypeFetcher[] browserTypeFetchers;

	private final RenderingEngine renderingEngine;

	private final Manufacturer manufacturer;

	private final VersionMapping[] versionMappings;

	private final VersionFetcher versionFetcher;

	private String version;

	Browser(final String name, final String[] patterns, final String[] excludePatterns, final BrowserType browserType,
			final BrowserTypeFetcher[] browserTypeFetchers, final RenderingEngine renderingEngine,
			final Manufacturer manufacturer, final VersionMapping[] versionMappings,
			final VersionFetcher versionFetcher){
		this.name = name;
		this.patterns = patterns;
		this.excludePatterns = excludePatterns;
		this.browserType = browserType;
		this.browserTypeFetchers = browserTypeFetchers;
		this.renderingEngine = renderingEngine;
		this.manufacturer = manufacturer;
		this.versionMappings = versionMappings;
		this.versionFetcher = versionFetcher;
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

	public String getVersion(){
		return version;
	}

	public static Browser parse(final String userAgent){
		Browser result = parseBrowser(userAgent);

		if(result == null){
			return Browser.UNKNOWN;
		}

		if(result.browserTypeFetchers != null){
			BrowserType browserType = parseBrowserType(userAgent, result.browserTypeFetchers);

			if(browserType != null){
				result.browserType = browserType;
			}
		}

		return result;

	}

	@Override
	public String toString(){
		return name + " " + version;
	}

	private static Browser parseBrowser(final String userAgent){
		final Parser<Browser> parser = new Parser<>(Browser.values());
		return parser.parse(userAgent, (browser)->{
			if(browser.excludePatterns != null){
				for(String excludePattern : browser.excludePatterns){
					if(StringUtils.containsIgnoreCase(userAgent, excludePattern)){
						return null;
					}
				}
			}

			if(browser.patterns != null){
				for(String pattern : browser.patterns){
					if(StringUtils.containsIgnoreCase(userAgent, pattern)){
						parseVersion(userAgent, browser);

						return browser;
					}
				}
			}

			return null;
		});
	}

	private static void parseVersion(final String userAgent, final Browser browser){
		if(browser.versionMappings != null){
			out:
			for(VersionMapping versionMapping : browser.versionMappings){
				if(versionMapping.getPatterns() == null){
					continue;
				}

				for(String pattern : versionMapping.getPatterns()){
					if(StringUtils.containsIgnoreCase(userAgent, pattern)){
						browser.version = versionMapping.getVersion();
						if(browser.version != null){
							break out;
						}
					}
				}
			}
		}

		if(browser.version == null && browser.versionFetcher != null){
			Version version = browser.versionFetcher.fetch(userAgent);
			if(version != null){
				browser.version = version.getMajorVersion();
			}
		}
	}

	private static BrowserType parseBrowserType(final String userAgent, final BrowserTypeFetcher[] browserTypeFetchers){
		final Parser<BrowserType> browserTypeParser = new Parser<>(BrowserType.values());
		return browserTypeParser.parse(userAgent, (bt)->{
			for(BrowserTypeFetcher browserTypeFetcher : browserTypeFetchers){
				BrowserType ret = browserTypeFetcher.fetch(userAgent);
				if(ret != null){
					return ret;
				}
			}

			return null;
		});
	}

}
