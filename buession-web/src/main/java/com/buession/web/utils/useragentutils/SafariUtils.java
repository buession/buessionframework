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

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Yong.Teng
 * @since 2.2.1
 */
public class SafariUtils {

	private final static String[][] webKitToSafariVersion = new String[][]{
			{"48", "0.8"},
			{"73", "0.9"},
			{"85", "1.0"},
			{"85.8.5", "1.0.3"},
			{"100", "1.1"},
			{"125", "1.2"},
			{"312", "1.3"},
			{"312.3", "1.3.1"},
			{"312.5", "1.3.2"},
			{"312.6", "1.3.2"},
			{"412", "2.0"},
			{"416.11", "2.0.2"},
			{"419.3", "2.0.4"},
			{"522.11", "3.0"},
			{"522.12", "3.0.2"},
			{"522.12.1", "3.0.3"},
			{"523.10", "3.0.4"},
			{"525.20", "3.1.1"},
			{"525.21", "3.1.2"},
			{"525.26", "3.2"},
			{"525.27", "3.2.1"},
			{"525.28", "3.2.3"},
			// {"530.17", "4.0.1"}, // ambiguity: Safari 4.0 for Mac 10.4-10.5 and Safari 4.0.1 for Windows vista and XP
			{"530.18", "4.0.1"},
			{"530.19", "4.0.2"},
			{"531.9", "4.0.3"},
			{"531.21.10", "4.0.4"},
			{"531.22.7", "4.0.5"},

			// {"533.16", "4.1"}, // ambiguity: Safari 4.1  on MacOsX 10.4, Safari 5.0 on MacOsX 10.5-10.6 and Windows XP, Vista and 7
			// {"533.17.8", "4.1.1"}, // ambiguity: Safari 4.1.1  on MacOsX 10.4, Safari 5.0.1 on MacOsX 10.5-10.6 and Windows XP, Vista and 7
			//{"533.18.5", "4.1.2"}, // ambiguity: Safari 4.1.2  on MacOsX 10.4, Safari 5.0.2 on MacOsX 10.5-10.6 and Windows XP, Vista and 7
			//{"533.19.4", "4.1.3"},// ambiguity: Safari 4.1.3  on MacOsX 10.4, Safari 5.0.3 on MacOsX 10.5-10.6 and Windows XP, Vista and 7

			{"533.20.27", "5.0.4"},
			{"533.21.1", "5.0.5"},
			{"533.22.3", "5.0.6"},
			{"534.48.3", "5.1"},
			{"534.51.22", "5.1.1"},
			{"534.52.7", "5.1.2"},
			{"534.53.10", "5.1.3"},
			{"534.54.16", "5.1.4"},
			{"534.55.3", "5.1.5"},
			{"534.56.5", "5.1.6"},
			{"534.57.2", "5.1.7"},
			{"534.58.2", "5.1.8"},
			{"534.59.8", "5.1.9"},
			{"534.59.10", "5.1.10"},
			{"536.25", "6.0"},
			{"536.26", "6.0.1"},
			{"536.26.17", "6.0.2"},
			{"536.28.10", "6.0.3"},
			{"536.29.13", "6.0.4"},
			{"536.30.1", "6.0.5"},
			{"537.43.58", "6.1"},
			//{"537.73.11", "6.1.1"}, // ambiguity: Safari 6.1.1 on OsX 10.7-10.8 and 7.0.1 on OsX 10.9


			// data is absent in wikipedia
			//		{"", "6.1.2"},
			//		{"", "6.1.3"},
			//		{"", "6.1.4"},
			//		{"", "6.1.5"},
			// {"537.78.2", "6.1.6"}, // ambiguity: Safari 6.1.6 on OsX 10.7-10.8 and 7.0.6 on OsX 10.9
			// data is absent in wikipedia
			//		{"", "6.2"},
			//		{"", "6.2.1"},
			//		{"", "6.2.2"},
			//		{"", "6.2.3"},
			//		{"", "6.2.4"},
			//		{"", "6.2.5"},
			//		{"", "6.2.6"},
			//		{"", "6.2.7"},


			// {"537.85.17", "6.2.8"}, // ambiguity: Safari 6.2.8 on OsX 10.8 and 7.1.8 on OsX 10.9

			{"537.71", "7.0"},
			// data is absent in wikipedia
			//		{"", "7.0.2"},
			{"537.75.14", "7.0.3"},
			{"537.76.4", "7.0.4"},
			{"537.77.4", "7.0.5"},

			////{"537.78.2", "7.0.6"}, //TODO ambiguity

			// data is absent in wikipedia
			//		{"", "7.1"},
			//		{"", "7.1.1"},
			//		{"", "7.1.2"},
			//		{"", "7.1.3"},
			//		{"", "7.1.4"},
			//		{"", "7.1.5"},
			//		{"", "7.1.6"},
			//		{"", "7.1.7"},
			{"538.35.8", "8.0"},

			// data is absent in wikipedia
			//		{"", "8.0.1"},
			//		{"", "8.0.2"},
			//		{"", "8.0.3"},
			//		{"", "8.0.4"},
			//		{"", "8.0.5"},

			{"600.6.3", "8.0.6"},
			{"600.7.12", "8.0.7"},
			// data is absent in wikipedia
			//		{"", "8.0.8"},
			//		{"", "9.0"},

			{"601.2.7", "9.0.1"},
			{"601.3.9", "9.0.2"},
			{"601.4.4", "9.0.3"},
			{"601.5.17", "9.1"},
			{"601.6.17", "9.1.1"},
			{"601.7.1", "9.1.2"},
			{"601.7.8", "9.1.3"},

			// Safari 10.x
			{"602.1.50", "10"},
			{"602.2.14", "10.0.1"},
			{"602.3.12", "10.0.2"},
			{"602.4.8", "10.0.3"},
			{"603.1.30", "10.1"}, // New web technology additions and improvements.
			{"603.2.4", "10.1.1"},
			{"603.3.8", "10.1.2"},

			//Safari 11.x ... TODO

			{"522.11.3", "3.0"},
			{"522.13.1", "3.0.2"},
			{"522.12.2", "3.0.1"},
			{"522.15.5", "3.0.3"},
			{"523.12.9", "3.0.4"},
			{"523.13", "3.0.4"},
			{"523.15", "3.0.4"},
			// mac os 10.4 - 10.5 and Windows XP, Vista
			{"525.13", "3.1"},
			{"525.17", "3.1.1"},
			{"525.21", "3.1.2"},

			{"525.26.13", "3.2"},
			{"525.27.1", "3.2.1"},
			{"525.28.1", "3.2.2"},
			{"525.29.1", "3.2.3"},

			{"526.12.2", "4.0"},
			{"528.1.1", "4.0"},

			{"526.11.2", "4.0"}, // actually 4.0 beta
			// 4.0 and 4.0 beta but since it is the same version we do not distinguish between 4.0 and 4.0 beta
			{"528.16", "4.0"},
			{"528.17", "4.0"},
			// end of 4.0 and 4.0 beta

			{"530.19.1", "4.0.2"},
			{"531.9.1", "4.0.3"},
			{"531.22.7", "4.0.5"},
			{"534.50", "5.1"},
	};

	private final static Map<String, Version> safariVersions;

	static{
		Map<String, Version> versions = new HashMap<>(webKitToSafariVersion.length);

		for(String[] pair : webKitToSafariVersion){
			String webKitVersion = pair[0];
			String browserVersion = pair[1];
			String[] parts = StringUtils.split(browserVersion, '.');
			String majorVersion = parts[0];
			String minorVersion = parts.length > 1 ? parts[1] : null;
			Version version = new Version(browserVersion, majorVersion, minorVersion);
			versions.put(webKitVersion, version);
		}

		safariVersions = Collections.unmodifiableMap(versions);
	}

	public static Map<String, Version> getWebKitToSafariVersion(){
		return safariVersions;
	}

}
