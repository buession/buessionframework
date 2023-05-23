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
import com.buession.lang.DeviceType;
import com.buession.lang.Version;
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
			com.buession.lang.OperatingSystem.WINDOWS,
			new String[]{"Windows"},
			null,
			DeviceType.COMPUTER,
			new DeviceTypeFetcher[]{
					new ContainsDeviceTypeFetcher("Tablet", DeviceType.TABLET)
			},
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
			com.buession.lang.OperatingSystem.WINDOWS_PHONE,
			new String[]{"Windows Phone", "Windows Mobile"},
			null,
			DeviceType.MOBILE,
			null,
			new VersionMapping[]{
					new VersionMapping(new String[]{"Windows Phone 10"}, "10"),
					new VersionMapping(new String[]{"Windows Phone 8"}, "8"),
					new VersionMapping(new String[]{"Windows Phone OS 7"}, "7"),
					new VersionMapping(new String[]{"Windows Phone"}, null)
			},
			null
	),

	WINDOWS_MOBILE(
			com.buession.lang.OperatingSystem.WINDOWS_MOBILE,
			new String[]{"Windows CE"},
			null,
			DeviceType.MOBILE,
			null,
			null,
			null
	),

	XBOX(
			com.buession.lang.OperatingSystem.XBOX,
			new String[]{"xbox"},
			null,
			DeviceType.GAME_CONSOLE,
			null,
			null,
			null
	),

	ANDROID(
			com.buession.lang.OperatingSystem.ANDROID,
			new String[]{"Android"},
			null,
			DeviceType.MOBILE,
			new DeviceTypeFetcher[]{
					new ContainsDeviceTypeFetcher("Tablet", DeviceType.TABLET),
					new ContainsDeviceTypeFetcher("GoogleTV", DeviceType.DMR),
			},
			new VersionMapping[]{
					new VersionMapping(new String[]{"Kindle Fire", "GT-P1000", "SCH-I800"}, "2"),
			},
			new PatternVersionFetcher("Android[ -]((\\d+))")
	),

	IOS(
			com.buession.lang.OperatingSystem.IOS,
			new String[]{"iPhone", "iPad", "iPod", "iOS"},
			null,
			DeviceType.MOBILE,
			new DeviceTypeFetcher[]{
					new ContainsDeviceTypeFetcher("iPad", DeviceType.TABLET)
			},
			null,
			new PatternVersionFetcher("OS ((\\d+)_(\\d+)_(\\d+))")
	),

	CHROME_OS(
			com.buession.lang.OperatingSystem.CHROME_OS,
			new String[]{"CrOS"},
			null,
			DeviceType.COMPUTER,
			null,
			null,
			null
	),

	MAC_OS_X(
			com.buession.lang.OperatingSystem.MAC_OS_X,
			new String[]{"Mac OS X"},
			null,
			DeviceType.COMPUTER,
			null,
			null,
			new PatternVersionFetcher("Mac OS X ((\\d+)[_\\.]?(\\d+)?([_\\.]\\d+)?)")
	),

	MAC_OS(
			com.buession.lang.OperatingSystem.MAC_OS,
			new String[]{"Mac OS"},
			null,
			DeviceType.COMPUTER,
			null,
			null,
			new PatternVersionFetcher("Mac OS ((\\d+)[_\\.]?(\\d+)?([_\\.]\\d+)?)")
	),

	WEBOS(
			com.buession.lang.OperatingSystem.WEBOS,
			new String[]{"webOS"},
			null,
			DeviceType.MOBILE,
			null,
			null,
			null
	),

	PALM(
			com.buession.lang.OperatingSystem.PALM,
			new String[]{"Palm"},
			null,
			DeviceType.MOBILE,
			null,
			null,
			null
	),

	MEEGO(
			com.buession.lang.OperatingSystem.MEEGO,
			new String[]{"MeeGo"},
			null,
			DeviceType.COMPUTER,
			null,
			null,
			null
	),

	MAEMO(
			com.buession.lang.OperatingSystem.MAEMO,
			new String[]{"Maemo"},
			null,
			DeviceType.MOBILE,
			null,
			null,
			null
	),

	BADA(
			com.buession.lang.OperatingSystem.BADA,
			new String[]{"Bada"},
			null,
			DeviceType.MOBILE,
			null,
			null,
			null
	),

	TIZEN(
			com.buession.lang.OperatingSystem.TIZEN,
			new String[]{"Tizen"},
			null,
			DeviceType.COMPUTER,
			new DeviceTypeFetcher[]{
					new ContainsDeviceTypeFetcher("Mobile", DeviceType.MOBILE),
					new ContainsDeviceTypeFetcher("Smart-TV", DeviceType.DMR),
					new ContainsDeviceTypeFetcher("TV ", DeviceType.DMR)
			},
			null,
			new PatternVersionFetcher("Tizen \\/((\\d+))\\.")
	),

	KINDLE(
			com.buession.lang.OperatingSystem.KINDLE,
			new String[]{"Kindle"},
			null,
			DeviceType.TABLET,
			null,
			new VersionMapping[]{
					new VersionMapping(new String[]{"Kindle"}, null)
			},
			new PatternVersionFetcher("Kindle\\/((\\d+))")
	),

	UBUNTU(
			com.buession.lang.OperatingSystem.UBUNTU,
			new String[]{"Ubuntu"},
			null,
			DeviceType.COMPUTER,
			new DeviceTypeFetcher[]{
					new ContainsDeviceTypeFetcher("mobile", DeviceType.MOBILE)
			},
			null,
			null
	),

	LINUX(
			com.buession.lang.OperatingSystem.LINUX,
			new String[]{"Linux"},
			null,
			DeviceType.COMPUTER,
			new DeviceTypeFetcher[]{
					new ContainsDeviceTypeFetcher("SmartTv ", DeviceType.DMR)
			},
			null,
			null
	),

	SUN_OS(
			com.buession.lang.OperatingSystem.SUN_OS,
			new String[]{"SunOS"},
			null,
			DeviceType.COMPUTER,
			null,
			null,
			null
	),

	BLACKBERRY(
			com.buession.lang.OperatingSystem.BLACKBERRY,
			new String[]{"BlackBerry"},
			null,
			DeviceType.MOBILE,
			new DeviceTypeFetcher[]{
					new ContainsDeviceTypeFetcher("RIM Tablet OS", DeviceType.TABLET)
			},
			null,
			new PatternVersionFetcher("Version\\/((\\d+))")
	),

	SYMBIAN(
			com.buession.lang.OperatingSystem.SYMBIAN,
			new String[]{"Symbian", "Series60"},
			null,
			DeviceType.MOBILE,
			new DeviceTypeFetcher[]{
					new ContainsDeviceTypeFetcher("Mobile", DeviceType.MOBILE),
					new ContainsDeviceTypeFetcher("Smart-TV", DeviceType.DMR),
					new ContainsDeviceTypeFetcher("TV ", DeviceType.DMR)
			},
			new VersionMapping[]{
					new VersionMapping(new String[]{"Series60/3"}, "9"),
					new VersionMapping(new String[]{"Series60/2.6", "Series60/2.8"}, "8")
			},
			new PatternVersionFetcher("Symbian\\/((\\d+))")
	),

	SERIES40(
			com.buession.lang.OperatingSystem.SERIES40,
			new String[]{"Nokia6300"},
			null,
			DeviceType.MOBILE,
			null,
			null,
			null
	),

	SONY_ERICSSON(
			com.buession.lang.OperatingSystem.SONY_ERICSSON,
			new String[]{"SonyEricsson"},
			null,
			DeviceType.MOBILE,
			null,
			null,
			null
	),

	PSP(
			com.buession.lang.OperatingSystem.PSP,
			new String[]{"Playstation"},
			null,
			DeviceType.GAME_CONSOLE,
			null,
			null,
			null
	),

	WII(
			com.buession.lang.OperatingSystem.WII,
			new String[]{"Wii"},
			null,
			DeviceType.GAME_CONSOLE,
			null,
			null,
			null
	),

	ROKU(
			com.buession.lang.OperatingSystem.ROKU,
			new String[]{"Roku"},
			null,
			DeviceType.DMR,
			null,
			null,
			null
	),

	UNKNOWN(
			com.buession.lang.OperatingSystem.UNKNOWN,
			null,
			null,
			DeviceType.COMPUTER,
			new DeviceTypeFetcher[]{
					new ContainsDeviceTypeFetcher("Mobile", DeviceType.MOBILE),
					new ContainsDeviceTypeFetcher("Tablet", DeviceType.TABLET)
			},
			null,
			null
	);

	private final com.buession.lang.OperatingSystem operatingSystem;

	private final String[] patterns;

	private final String[] excludePatterns;

	private DeviceType deviceType;

	private final DeviceTypeFetcher[] deviceTypeFetchers;

	private final VersionMapping[] versionMappings;

	private final VersionFetcher versionFetcher;

	private String version;

	OperatingSystem(final com.buession.lang.OperatingSystem operatingSystem, final String[] patterns,
					final String[] excludePatterns, final DeviceType deviceType,
					final DeviceTypeFetcher[] deviceTypeFetchers, final VersionMapping[] versionMappings,
					final VersionFetcher versionFetcher){
		this.operatingSystem = operatingSystem;
		this.patterns = patterns;
		this.excludePatterns = excludePatterns;
		this.deviceType = deviceType;
		this.deviceTypeFetchers = deviceTypeFetchers;
		this.versionMappings = versionMappings;
		this.versionFetcher = versionFetcher;
	}

	public String getName(){
		return operatingSystem.getName();
	}

	public DeviceType getDeviceType(){
		return deviceType;
	}

	public com.buession.lang.OperatingSystem.Manufacturer getManufacturer(){
		return operatingSystem.getManufacturer();
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
		return getName() + " " + version;
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
				operatingSystem.version = version.toString();
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
