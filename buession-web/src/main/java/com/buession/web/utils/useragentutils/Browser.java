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
import com.buession.lang.BrowserType;
import com.buession.lang.RenderingEngine;
import com.buession.lang.Version;
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
			com.buession.lang.Browser.IE,
			new String[]{"MSIE", "Trident", "IE "},
			null,
			BrowserType.WEB_BROWSER,
			new BrowserTypeFetcher[]{
					new ContainsBrowserTypeFetcher("IEMobile", BrowserType.MOBILE_BROWSER)
			},
			RenderingEngine.TRIDENT,
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
			com.buession.lang.Browser.EDGE,
			new String[]{"Edge"},
			null,
			BrowserType.WEB_BROWSER,
			new BrowserTypeFetcher[]{
					new ContainsBrowserTypeFetcher("Mobile Safari", BrowserType.MOBILE_BROWSER)
			},
			RenderingEngine.EDGE_HTML,
			null,
			new PatternVersionFetcher("(?:Edge\\/(([0-9]+)\\.([0-9]*)))")
	),

	CHROME(
			com.buession.lang.Browser.CHROME,
			new String[]{"Chrome", "CrMo", "CriOS"},
			null,
			BrowserType.WEB_BROWSER,
			new BrowserTypeFetcher[]{
					new ContainsBrowserTypeFetcher("CrMo", BrowserType.MOBILE_BROWSER),
					new ContainsBrowserTypeFetcher("CriOS", BrowserType.MOBILE_BROWSER),
					new ContainsBrowserTypeFetcher("Mobile", BrowserType.MOBILE_BROWSER)
			},
			RenderingEngine.WEBKIT,
			null,
			new PatternVersionFetcher("(?:CriOS|CrMo|Chrome)\\/(([0-9]+)\\.?([\\w]+)?(\\.[\\w]+)?(\\.[\\w]+)?)")
	),

	FIREFOX(
			com.buession.lang.Browser.FIREFOX,
			new String[]{"Firefox", "FxiOS"},
			null,
			BrowserType.WEB_BROWSER,
			new BrowserTypeFetcher[]{
					new ContainsBrowserTypeFetcher("Firefox/3.5 Maemo", BrowserType.MOBILE_BROWSER),
					new ContainsBrowserTypeFetcher("Mobile", BrowserType.MOBILE_BROWSER)
			},
			RenderingEngine.GECKO,
			null,
			new PatternVersionFetcher("Firefox\\/(([0-9]+)\\.?([\\w]+)?(\\.[\\w]+)?(\\.[\\w]+)?)")
	),

	SAFARI(
			com.buession.lang.Browser.SAFARI,
			new String[]{"Safari"},
			new String[]{"bot", "preview", "OPR/", "Coast/", "Vivaldi", "CFNetwork", "Phantom"},
			BrowserType.WEB_BROWSER,
			new BrowserTypeFetcher[]{
					new ContainsBrowserTypeFetcher("Mobile Safari", BrowserType.MOBILE_BROWSER),
					new ContainsBrowserTypeFetcher("Mobile/", BrowserType.MOBILE_BROWSER)
			},
			RenderingEngine.WEBKIT,
			null,
			new SequentialVersionFetcher(
					new PatternVersionFetcher("Version\\/(([0-9]+)\\.?([\\w]+)?(\\.[\\w]+)?)"),
					new MapVersionFetcher("AppleWebKit/(\\d+(?:.\\d+){1,2})", SafariUtils.getWebKitToSafariVersion())
			)
	),

	OPERA_COAST(
			com.buession.lang.Browser.OPERA_COAST,
			new String[]{"Coast/"},
			null,
			BrowserType.MOBILE_BROWSER,
			null,
			RenderingEngine.WEBKIT,
			null,
			new PatternVersionFetcher("Coast\\/(([\\d]+)\\.([\\w]+)?(\\.[\\w]+)?(\\.[\\w]+)?)")
	),

	OPERA(
			com.buession.lang.Browser.OPERA,
			new String[]{" OPR/", "Opera"},
			null,
			BrowserType.WEB_BROWSER,
			new BrowserTypeFetcher[]{
					new ContainsBrowserTypeFetcher("Opera Mini", BrowserType.MOBILE_BROWSER),
					new ContainsBrowserTypeFetcher("Mobile", BrowserType.MOBILE_BROWSER)
			},
			RenderingEngine.PRESTO,
			new VersionMapping[]{
					new VersionMapping(new String[]{"Opera/12", "Version/12."}, "12"),
					new VersionMapping(new String[]{"Opera/11", "Version/11."}, "11"),
					new VersionMapping(new String[]{"Opera/10", "Version/10."}, "10"),
					new VersionMapping(new String[]{"Opera/9", "Version/9."}, "9")
			},
			new PatternVersionFetcher("OPR\\/(([\\d]+)\\.([\\w]+)?(\\.[\\w]+)?(\\.[\\w]+)?)")
	),

	MOZILLA(
			com.buession.lang.Browser.MOZILLA,
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
			null,
			null
	),

	DOLFIN2(
			com.buession.lang.Browser.DOLFIN2,
			new String[]{"Dolfin/2"},
			null,
			BrowserType.MOBILE_BROWSER,
			null,
			RenderingEngine.WEBKIT,
			null,
			null
	),

	CAMINO(
			com.buession.lang.Browser.CAMINO,
			new String[]{"Camino"},
			null,
			BrowserType.WEB_BROWSER,
			null,
			RenderingEngine.GECKO,
			null,
			new PatternVersionFetcher("Camino\\/(([0-9]+)\\.?([\\w]+)?(\\.[\\w]+)?)")
	),

	LOTUS_NOTES(
			com.buession.lang.Browser.LOTUS_NOTES,
			new String[]{"Lotus-Notes"},
			null,
			BrowserType.EMAIL_CLIENT,
			null,
			RenderingEngine.OTHER,
			null,
			new PatternVersionFetcher("Lotus-Notes\\/(([\\d]+)\\.([\\w]+))")
	),

	OMNIWEB(
			com.buession.lang.Browser.OMNIWEB,
			new String[]{"OmniWeb"}, null,
			BrowserType.WEB_BROWSER,
			null,
			RenderingEngine.WEBKIT,
			null,
			null
	),

	FLOCK(
			com.buession.lang.Browser.FLOCK,
			new String[]{"Flock"},
			null,
			BrowserType.WEB_BROWSER,
			null,
			RenderingEngine.GECKO,
			null,
			new PatternVersionFetcher("Flock\\/(([0-9]+)\\.?([\\w]+)?(\\.[\\w]+)?)")
	),

	VIVALDI(
			com.buession.lang.Browser.VIVALDI,
			new String[]{"Vivaldi"},
			null,
			BrowserType.WEB_BROWSER,
			null,
			RenderingEngine.BLINK,
			null,
			new PatternVersionFetcher("Vivaldi/(([\\d]+).([\\d]+).([\\d]+).([\\d]+))")
	),

	SEAMONKEY(
			com.buession.lang.Browser.SEAMONKEY,
			new String[]{"SeaMonkey"},
			null,
			BrowserType.WEB_BROWSER,
			null,
			RenderingEngine.GECKO,
			null,
			new PatternVersionFetcher("SeaMonkey\\/(([0-9]+)\\.?([\\w]+)?(\\.[\\w]+)?)")
	),

	BOT(
			com.buession.lang.Browser.BOT,
			new String[]{"Googlebot", "Googlebot-Mobile", "Mediapartners-Google", "Web Preview", "bot",
					"Applebot", "spider", "crawler", "Feedfetcher", "Slurp", "Twiceler", "Nutch", "BecomeBot",
					"bingbot", "BingPreview", "Google Web Preview", "WordPress.com mShots", "Seznam",
					"facebookexternalhit", "YandexMarket", "Teoma", "ThumbSniper", "Phantom", "Go-http-client",
					"Java/", "python-requests", "YandexBot", "AdsBot-Google", "AhrefsBot"},
			null,
			BrowserType.ROBOT,
			null,
			RenderingEngine.OTHER,
			null,
			null
	),

	CFNETWORK(
			com.buession.lang.Browser.CFNETWORK,
			new String[]{"CFNetwork"},
			null,
			BrowserType.UNKNOWN,
			null,
			RenderingEngine.OTHER,
			null,
			new PatternVersionFetcher("CFNetwork/(([\\d]+)(?:\\.([\\d]))?(?:\\.([\\d]+))?)")
	),

	EUDORA(
			com.buession.lang.Browser.EUDORA,
			new String[]{"Eudora", "EUDORA"},
			null,
			BrowserType.EMAIL_CLIENT,
			null,
			RenderingEngine.OTHER,
			null,
			null
	),

	POCOMAIL(
			com.buession.lang.Browser.POCOMAIL,
			new String[]{"PocoMail"},
			null,
			BrowserType.EMAIL_CLIENT,
			null,
			RenderingEngine.OTHER,
			null,
			null
	),

	THEBAT(
			com.buession.lang.Browser.THEBAT,
			new String[]{"The Bat"},
			null,
			BrowserType.EMAIL_CLIENT,
			null,
			RenderingEngine.OTHER,
			null,
			null
	),

	SILK(
			com.buession.lang.Browser.SILK,
			new String[]{"Silk/"},
			null,
			BrowserType.WEB_BROWSER,
			null,
			RenderingEngine.WEBKIT,
			null,
			new PatternVersionFetcher("Silk\\/(([0-9]+)\\.?([\\w]+)?(\\.[\\w]+)?(\\-[\\w]+)?)")
	),

	BLACKBERRY(
			com.buession.lang.Browser.BLACKBERRY,
			new String[]{"BB10"},
			null,
			BrowserType.MOBILE_BROWSER,
			null,
			RenderingEngine.WEBKIT,
			null,
			null
	),

	NETFRONT(
			com.buession.lang.Browser.NETFRONT,
			new String[]{"NetFront"},
			null,
			BrowserType.MOBILE_BROWSER,
			null,
			RenderingEngine.OTHER,
			null,
			null
	),

	EVOLUTION(
			com.buession.lang.Browser.EVOLUTION,
			new String[]{"CamelHttpStream"},
			null,
			BrowserType.EMAIL_CLIENT,
			null,
			RenderingEngine.OTHER,
			null,
			null
	),

	LYNX(
			com.buession.lang.Browser.LYNX,
			new String[]{"Lynx"},
			null,
			BrowserType.TEXT_BROWSER,
			null,
			RenderingEngine.OTHER,
			null,
			new PatternVersionFetcher("Lynx\\/(([0-9]+)\\.([\\d]+)\\.?([\\w-+]+)?\\.?([\\w-+]+)?)")
	),

	KONQUEROR(
			com.buession.lang.Browser.KONQUEROR,
			new String[]{"Konqueror"},
			new String[]{"Exabot"},
			BrowserType.TEXT_BROWSER,
			null,
			RenderingEngine.KHTML,
			null,
			new PatternVersionFetcher("Konqueror\\/(([0-9]+)\\.?([\\w]+)?(-[\\w]+)?)")
	),

	XBOX(
			com.buession.lang.Browser.XBOX,
			new String[]{"xbox"},
			null,
			BrowserType.WEB_BROWSER,
			null,
			RenderingEngine.TRIDENT,
			null,
			null
	),

	THUNDERBIRD(
			com.buession.lang.Browser.THUNDERBIRD,
			new String[]{"Thunderbird"},
			null,
			BrowserType.EMAIL_CLIENT,
			null,
			RenderingEngine.GECKO,
			null,
			new PatternVersionFetcher("Thunderbird\\/(([0-9]+)\\.?([\\w]+)?(\\.[\\w]+)?(\\.[\\w]+)?)")
	),

	UNKNOWN(
			com.buession.lang.Browser.UNKNOWN,
			null,
			null,
			BrowserType.UNKNOWN,
			null,
			RenderingEngine.OTHER,
			null,
			null
	);

	private final com.buession.lang.Browser browser;

	private final String[] patterns;

	private final String[] excludePatterns;

	private BrowserType browserType;

	private final BrowserTypeFetcher[] browserTypeFetchers;

	private final RenderingEngine renderingEngine;

	private final VersionMapping[] versionMappings;

	private final VersionFetcher versionFetcher;

	private String version;

	Browser(final com.buession.lang.Browser browser, final String[] patterns, final String[] excludePatterns,
			final BrowserType browserType,
			final BrowserTypeFetcher[] browserTypeFetchers, final RenderingEngine renderingEngine,
			final VersionMapping[] versionMappings, final VersionFetcher versionFetcher){
		this.browser = browser;
		this.patterns = patterns;
		this.excludePatterns = excludePatterns;
		this.browserType = browserType;
		this.browserTypeFetchers = browserTypeFetchers;
		this.renderingEngine = renderingEngine;
		this.versionMappings = versionMappings;
		this.versionFetcher = versionFetcher;
	}

	public String getName(){
		return browser.getName();
	}

	public BrowserType getBrowserType(){
		return browserType;
	}

	public RenderingEngine getRenderingEngine(){
		return renderingEngine;
	}

	public com.buession.lang.Browser.Manufacturer getManufacturer(){
		return browser.getManufacturer();
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
		return getName() + " " + version;
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
				browser.version = version.toString();
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

