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
import com.buession.web.utils.useragentutils.devicetypefetcher.ContainsDeviceTypeFetcher;
import com.buession.web.utils.useragentutils.devicetypefetcher.DeviceTypeFetcher;
import com.buession.web.utils.useragentutils.versionfetcher.PatternVersionFetcher;
import com.buession.web.utils.useragentutils.versionfetcher.VersionFetcher;

/**
 * 操作系统
 *
 * @author Yong.Teng
 * @since 2.2.1
 */
public enum OperatingSystem {
	WINDOWS(
			"Windows",
			new String[]{"Windows"},
			null,
			DeviceType.COMPUTER,
			new DeviceTypeFetcher[]{
					new ContainsDeviceTypeFetcher("Tablet", DeviceType.TABLET)
			},
			Manufacturer.MICROSOFT,
			new VersionMapping[]{
					new VersionMapping(new String[]{"Windows NT 6.4", "Windows NT 10"}, "10"),
					new VersionMapping(new String[]{"Windows NT 6.2", "Windows NT 6.3"}, "8"),
					new VersionMapping(new String[]{"Windows NT 6.1"}, "7"),
					new VersionMapping(new String[]{"Windows NT 6"}, "Vista"),
					new VersionMapping(new String[]{"Windows NT 5.0"}, "2000"),
					new VersionMapping(new String[]{"Windows NT 5"}, "XP"),
					new VersionMapping(new String[]{"Windows 98", "Win98"}, "98"),
					new VersionMapping(new String[]{"Windows NT"}, null)
			},
			null
	),

	WINDOWS_PHONE(
			"Windows Phone",
			new String[]{"Windows Phone", "Windows Mobile"},
			null,
			DeviceType.MOBILE,
			null,
			Manufacturer.MICROSOFT,
			new VersionMapping[]{
					new VersionMapping(new String[]{"Windows Phone 10"}, "10"),
					new VersionMapping(new String[]{"Windows Phone 8"}, "8"),
					new VersionMapping(new String[]{"Windows Phone OS 7"}, "7"),
					new VersionMapping(new String[]{"Windows Phone"}, null)
			},
			null
	),

	WINDOWS_MOBILE(
			"Windows Mobile",
			new String[]{"Windows CE"},
			null,
			DeviceType.MOBILE,
			null,
			Manufacturer.MICROSOFT,
			null,
			null
	),

	XBOX(
			"Xbox OS",
			new String[]{"xbox"},
			null,
			DeviceType.GAME_CONSOLE,
			null,
			Manufacturer.MICROSOFT,
			null,
			null
	),

	ANDROID(
			"Android",
			new String[]{"Android"},
			null,
			DeviceType.MOBILE,
			new DeviceTypeFetcher[]{
					new ContainsDeviceTypeFetcher("Tablet", DeviceType.TABLET),
					new ContainsDeviceTypeFetcher("GoogleTV", DeviceType.DMR),
			},
			Manufacturer.GOOGLE,
			new VersionMapping[]{
					new VersionMapping(new String[]{"Kindle Fire", "GT-P1000", "SCH-I800"}, "2"),
			},
			new PatternVersionFetcher("Android[ -]((\\d+))")
	),

	IOS(
			"iOS",
			new String[]{"iPhone", "iPad", "iPod", "iOS"},
			null,
			DeviceType.MOBILE,
			new DeviceTypeFetcher[]{
					new ContainsDeviceTypeFetcher("iPad", DeviceType.TABLET)
			},
			Manufacturer.APPLE,
			null,
			new PatternVersionFetcher("OS ((\\d+)_(\\d+)_(\\d+))")
	),

	CHROME_OS(
			"Chrome OS",
			new String[]{"CrOS"},
			null,
			DeviceType.COMPUTER,
			null,
			Manufacturer.GOOGLE,
			null,
			null
	),

	MAC_OS_X(
			"Mac OS X",
			new String[]{"Mac OS X"},
			null,
			DeviceType.COMPUTER,
			null,
			Manufacturer.APPLE,
			null,
			new PatternVersionFetcher("Mac OS X ((\\d+)[_\\.]?(\\d+)?([_\\.]\\d+)?)")
	),

	MAC_OS(
			"Mac OS",
			new String[]{"Mac OS"},
			null,
			DeviceType.COMPUTER,
			null,
			Manufacturer.APPLE,
			null,
			new PatternVersionFetcher("Mac OS ((\\d+)[_\\.]?(\\d+)?([_\\.]\\d+)?)")
	),

	WEBOS(
			"WebOS",
			new String[]{"webOS"},
			null,
			DeviceType.MOBILE,
			null,
			Manufacturer.HP,
			null,
			null
	),

	PALM(
			"PalmOS",
			new String[]{"Palm"},
			null,
			DeviceType.MOBILE,
			null,
			Manufacturer.HP,
			null,
			null
	),

	MEEGO(
			"MeeGo",
			new String[]{"MeeGo"},
			null,
			DeviceType.COMPUTER,
			null,
			Manufacturer.NOKIA,
			null,
			null
	),

	MAEMO(
			"Maemo",
			new String[]{"Maemo"},
			null,
			DeviceType.MOBILE,
			null,
			Manufacturer.NOKIA,
			null,
			null
	),

	BADA(
			"Bada",
			new String[]{"Bada"},
			null,
			DeviceType.MOBILE,
			null,
			Manufacturer.SAMSUNG,
			null,
			null
	),

	TIZEN(
			"Tizen",
			new String[]{"Tizen"},
			null,
			DeviceType.COMPUTER,
			new DeviceTypeFetcher[]{
					new ContainsDeviceTypeFetcher("Mobile", DeviceType.MOBILE),
					new ContainsDeviceTypeFetcher("Smart-TV", DeviceType.DMR),
					new ContainsDeviceTypeFetcher("TV ", DeviceType.DMR)
			},
			Manufacturer.LINUX_FOUNDATION,
			null,
			new PatternVersionFetcher("Tizen \\/((\\d+))\\.")
	),

	KINDLE(
			"Kindle",
			new String[]{"Kindle"},
			null,
			DeviceType.TABLET,
			null,
			Manufacturer.AMAZON,
			new VersionMapping[]{
					new VersionMapping(new String[]{"Kindle"}, null)
			},
			new PatternVersionFetcher("Kindle\\/((\\d+))")
	),

	Ubuntu(
			"Ubuntu",
			new String[]{"Ubuntu"},
			null,
			DeviceType.COMPUTER,
			new DeviceTypeFetcher[]{
					new ContainsDeviceTypeFetcher("mobile", DeviceType.MOBILE)
			},
			Manufacturer.CONONICAL,
			null,
			null
	),

	LINUX(
			"Linux",
			new String[]{"Linux"},
			null,
			DeviceType.COMPUTER,
			new DeviceTypeFetcher[]{
					new ContainsDeviceTypeFetcher("SmartTv ", DeviceType.DMR)
			},
			Manufacturer.OTHER,
			null,
			null
	),

	SUN_OS(
			"SunOS",
			new String[]{"SunOS"},
			null,
			DeviceType.COMPUTER,
			null,
			Manufacturer.SUN,
			null,
			null
	),

	BLACKBERRY(
			"BlackBerryOS",
			new String[]{"BlackBerry"},
			null,
			DeviceType.MOBILE,
			new DeviceTypeFetcher[]{
					new ContainsDeviceTypeFetcher("RIM Tablet OS", DeviceType.TABLET)
			},
			Manufacturer.BLACKBERRY,
			null,
			new PatternVersionFetcher("Version\\/((\\d+))")
	),

	SYMBIAN(
			"Symbian OS",
			new String[]{"Symbian", "Series60"},
			null,
			DeviceType.MOBILE,
			new DeviceTypeFetcher[]{
					new ContainsDeviceTypeFetcher("Mobile", DeviceType.MOBILE),
					new ContainsDeviceTypeFetcher("Smart-TV", DeviceType.DMR),
					new ContainsDeviceTypeFetcher("TV ", DeviceType.DMR)
			},
			Manufacturer.SYMBIAN,
			new VersionMapping[]{
					new VersionMapping(new String[]{"Series60/3"}, "9"),
					new VersionMapping(new String[]{"Series60/2.6", "Series60/2.8"}, "8")
			},
			new PatternVersionFetcher("Symbian\\/((\\d+))")
	),

	SERIES40(
			"Series 40",
			new String[]{"Nokia6300"},
			null,
			DeviceType.MOBILE,
			null,
			Manufacturer.NOKIA,
			null,
			null
	),

	SONY_ERICSSON(
			"Sony Ericsson",
			new String[]{"SonyEricsson"},
			null,
			DeviceType.MOBILE,
			null,
			Manufacturer.SONY_ERICSSON,
			null,
			null
	),

	PSP(
			"Sony Playstation",
			new String[]{"Playstation"},
			null,
			DeviceType.GAME_CONSOLE,
			null,
			Manufacturer.SONY,
			null,
			null
	),

	WII(
			"Nintendo Wii",
			new String[]{"Wii"},
			null,
			DeviceType.GAME_CONSOLE,
			null,
			Manufacturer.NINTENDO,
			null,
			null
	),

	ROKU(
			"Roku OS",
			new String[]{"Roku"},
			null,
			DeviceType.DMR,
			null,
			Manufacturer.ROKU,
			null,
			null
	),

	UNKNOWN(
			"Unknown",
			null,
			null,
			DeviceType.COMPUTER,
			new DeviceTypeFetcher[]{
					new ContainsDeviceTypeFetcher("Mobile", DeviceType.MOBILE),
					new ContainsDeviceTypeFetcher("Tablet", DeviceType.TABLET)
			},
			Manufacturer.OTHER,
			null,
			null
	);

	private final String name;

	private final String[] patterns;

	private final String[] excludePatterns;

	private DeviceType deviceType;

	private final DeviceTypeFetcher[] deviceTypeFetchers;

	private final Manufacturer manufacturer;

	private final VersionMapping[] versionMappings;

	private final VersionFetcher versionFetcher;

	private String version;

	OperatingSystem(final String name, final String[] patterns, final String[] excludePatterns,
					final DeviceType deviceType, final DeviceTypeFetcher[] deviceTypeFetchers,
					final Manufacturer manufacturer, final VersionMapping[] versionMappings,
					final VersionFetcher versionFetcher){
		this.name = name;
		this.patterns = patterns;
		this.excludePatterns = excludePatterns;
		this.deviceType = deviceType;
		this.deviceTypeFetchers = deviceTypeFetchers;
		this.manufacturer = manufacturer;
		this.versionMappings = versionMappings;
		this.versionFetcher = versionFetcher;
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

	public String getVersion(){
		return version;
	}

	public static OperatingSystem parse(final String userAgent){
		OperatingSystem result = parseOperatingSystem(userAgent);

		if(result == null){
			return OperatingSystem.UNKNOWN;
		}

		if(result.deviceTypeFetchers != null){
			DeviceType deviceType = parseDeviceType(userAgent, result.deviceTypeFetchers);

			if(deviceType != null){
				result.deviceType = deviceType;
			}
		}

		return result;
	}

	@Override
	public String toString(){
		return name + " " + version;
	}

	private static OperatingSystem parseOperatingSystem(final String userAgent){
		final Parser<OperatingSystem> parser = new Parser<>(OperatingSystem.values());
		return parser.parse(userAgent, (operatingSystem)->{
			if(operatingSystem.excludePatterns != null){
				for(String excludePattern : operatingSystem.excludePatterns){
					if(StringUtils.containsIgnoreCase(userAgent, excludePattern)){
						return null;
					}
				}
			}

			if(operatingSystem.patterns != null){
				for(String pattern : operatingSystem.patterns){
					if(StringUtils.containsIgnoreCase(userAgent, pattern)){
						parseVersion(userAgent, operatingSystem);

						return operatingSystem;
					}
				}
			}

			return null;
		});
	}

	private static void parseVersion(final String userAgent, final OperatingSystem operatingSystem){
		if(operatingSystem.versionMappings != null){
			out:
			for(VersionMapping versionMapping : operatingSystem.versionMappings){
				if(versionMapping.getPatterns() == null){
					continue;
				}

				for(String pattern : versionMapping.getPatterns()){
					if(StringUtils.containsIgnoreCase(userAgent, pattern)){
						operatingSystem.version = versionMapping.getVersion();
						if(operatingSystem.version != null){
							break out;
						}
					}
				}
			}
		}

		if(operatingSystem.version == null && operatingSystem.versionFetcher != null){
			Version version = operatingSystem.versionFetcher.fetch(userAgent);
			if(version != null){
				operatingSystem.version = version.getMajorVersion();
			}
		}
	}

	private static DeviceType parseDeviceType(final String userAgent, final DeviceTypeFetcher[] deviceTypeFetchers){
		final Parser<DeviceType> deviceTypeParser = new Parser<>(DeviceType.values());
		return deviceTypeParser.parse(userAgent, (dt)->{
			for(DeviceTypeFetcher deviceTypeFetcher : deviceTypeFetchers){
				DeviceType ret = deviceTypeFetcher.fetch(userAgent);
				if(ret != null){
					return ret;
				}
			}

			return null;
		});
	}

}
