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
package com.buession.web.utils.useragentutils.versionfetcher;

import com.buession.lang.Version;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Yong.Teng
 * @since 2.2.1
 */
public class PatternVersionFetcher implements VersionFetcher {

	private final Pattern pattern;

	public PatternVersionFetcher(final String regex) {
		this(Pattern.compile(regex, Pattern.CASE_INSENSITIVE));
	}

	public PatternVersionFetcher(final Pattern pattern) {
		this.pattern = pattern;
	}

	@Override
	public final Version fetch(String userAgent) {
		Matcher matcher = pattern.matcher(userAgent);
		return matcher.find() ? createVersion(matcher) : null;
	}

	protected Version createVersion(Matcher matcher) {
		String majorVersion = removePeriod(matcher.group(2));
		String minorVersion = "0";
		String revisionVersion = null;
		String buildVersion = null;

		if(matcher.groupCount() > 2){ // usually but not always there is a minor version
			minorVersion = removePeriod(matcher.group(3));
		}

		if(matcher.groupCount() > 3){ // usually but not always there is a revision version
			revisionVersion = removePeriod(matcher.group(4));
		}

		if(revisionVersion == null){
			return new Version(majorVersion, minorVersion);
		}

		if(matcher.groupCount() > 4){ // usually but not always there is a build version
			buildVersion = removePeriod(matcher.group(5));
		}

		if(buildVersion == null){
			return new Version(majorVersion, minorVersion, revisionVersion);
		}

		return new Version(majorVersion, minorVersion, revisionVersion, buildVersion);
	}

	private static String removePeriod(final String s) {
		if(s == null){
			return null;
		}
		
		final char c = s.charAt(0);
		return (c == '.' || c == '_') ? s.substring(1) : s;
	}

}
