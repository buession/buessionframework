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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 版本
 *
 * @author Yong.Teng
 * @since 2.3.0
 */
public final class Version implements Comparable<Version>, Serializable {

	private final static long serialVersionUID = 1136250398395643115L;

	/**
	 * 版本
	 */
	private final String version;

	/**
	 * 主版本号
	 */
	private final int majorVersion;

	/**
	 * 次版本号
	 */
	private final int minorVersion;

	/**
	 * 修订版本号
	 */
	private final int revisionVersion;

	/**
	 * Build 版本号
	 */
	private final int buildVersion;

	/**
	 * 特殊版本
	 */
	private final String special;

	/**
	 * 构造函数
	 *
	 * @param version
	 * 		版本
	 */
	public Version(final String version) {
		String[] versionParts = splitVersion(version, true);
		int majorVersion = -1;
		int minorVersion = -1;
		int revisionVersion = -1;
		int buildVersion = -1;
		String special = null;

		try{
			if(versionParts != null && versionParts.length >= 1){
				char first1 = versionParts[0].charAt(0);
				majorVersion = first1 == 'v' || first1 == 'V' ? Integer.parseInt(
						versionParts[0].substring(1)) : Integer.parseInt(versionParts[0]);
			}

			if(versionParts != null && versionParts.length >= 2){
				int j = -1;

				for(int i = 0, l = versionParts[1].length(); i < l; i++){
					char c = versionParts[1].charAt(i);

					if(VersionUtils.isAlpha(c) || c == ' '){
						j = i;
						break;
					}
				}

				if(j >= 0){
					minorVersion = Integer.parseInt(versionParts[1].substring(0, j));
					special = versionParts[1].substring(j);
					if(special.charAt(0) == ' '){
						special = special.substring(1);
					}
				}else{
					minorVersion = Integer.parseInt(versionParts[1]);
				}
			}

			if(versionParts != null && versionParts.length >= 3){
				int j = -1;

				for(int i = 0, l = versionParts[2].length(); i < l; i++){
					char c = versionParts[2].charAt(i);

					if(VersionUtils.isAlpha(c) || c == ' '){
						j = i;
						break;
					}
				}

				if(j >= 0){
					if(special != null){
						throw new IllegalArgumentException("Illegal version format.");
					}
					revisionVersion = Integer.parseInt(versionParts[2].substring(0, j));
					special = versionParts[2].substring(j);
					if(special.charAt(0) == ' '){
						special = special.substring(1);
					}
				}else{
					revisionVersion = Integer.parseInt(versionParts[2]);
				}
			}

			if(versionParts != null && versionParts.length >= 4){
				int j = -1;

				for(int i = 0, l = versionParts[3].length(); i < l; i++){
					char c = versionParts[3].charAt(i);

					if(VersionUtils.isAlpha(c) || c == ' '){
						j = i;
						break;
					}
				}

				if(j >= 0){
					if(special != null){
						throw new IllegalArgumentException("Illegal version format.");
					}
					buildVersion = Integer.parseInt(versionParts[3].substring(0, j));
					special = versionParts[3].substring(j);
					if(special.charAt(0) == ' '){
						special = special.substring(1);
					}
				}else{
					buildVersion = Integer.parseInt(versionParts[3]);
				}
			}
		}catch(Exception e){
			throw new IllegalArgumentException("Illegal version format.");
		}

		if(special != null){
			boolean specialIllegal = false;

			for(Map.Entry<String, Integer> e : VersionUtils.SPECIAL_VERSIONS.entrySet()){
				if(e.getKey().equalsIgnoreCase(special)){
					specialIllegal = true;
					break;
				}
			}

			if(specialIllegal == false){
				throw new IllegalArgumentException("Illegal version format: " + version + ".");
			}
		}

		this.version = version;
		this.majorVersion = majorVersion;
		this.minorVersion = minorVersion;
		this.revisionVersion = revisionVersion;
		this.buildVersion = buildVersion;
		this.special = special;

		if(this.majorVersion == -1){
			throw new IllegalArgumentException("Illegal version format: " + version + ".");
		}
	}

	/**
	 * 构造函数
	 *
	 * @param majorVersion
	 * 		主版本号
	 * @param minorVersion
	 * 		次版本号
	 */
	public Version(final int majorVersion, final int minorVersion) {
		this(majorVersion, minorVersion, -1, -1, null);
	}

	/**
	 * 构造函数
	 *
	 * @param majorVersion
	 * 		主版本号
	 * @param minorVersion
	 * 		次版本号
	 */
	public Version(final String majorVersion, final String minorVersion) {
		this(Integer.parseInt(majorVersion), Integer.parseInt(minorVersion));
	}

	/**
	 * 构造函数
	 *
	 * @param majorVersion
	 * 		主版本号
	 * @param minorVersion
	 * 		次版本号
	 * @param revisionVersion
	 * 		修订版本号
	 */
	public Version(final int majorVersion, final int minorVersion, final int revisionVersion) {
		this(majorVersion, minorVersion, revisionVersion, -1, null);
	}

	/**
	 * 构造函数
	 *
	 * @param majorVersion
	 * 		主版本号
	 * @param minorVersion
	 * 		次版本号
	 * @param revisionVersion
	 * 		修订版本号
	 */
	public Version(final String majorVersion, final String minorVersion, final String revisionVersion) {
		this(Integer.parseInt(majorVersion), Integer.parseInt(minorVersion), Integer.parseInt(revisionVersion));
	}

	/**
	 * 构造函数
	 *
	 * @param majorVersion
	 * 		主版本号
	 * @param minorVersion
	 * 		次版本号
	 * @param revisionVersion
	 * 		修订版本号
	 * @param buildVersion
	 * 		build 版本号
	 */
	public Version(final int majorVersion, final int minorVersion, final int revisionVersion, final int buildVersion) {
		this(majorVersion, minorVersion, revisionVersion, buildVersion, null);
	}

	/**
	 * 构造函数
	 *
	 * @param majorVersion
	 * 		主版本号
	 * @param minorVersion
	 * 		次版本号
	 * @param revisionVersion
	 * 		修订版本号
	 * @param buildVersion
	 * 		build 版本号
	 */
	public Version(final String majorVersion, final String minorVersion, final String revisionVersion,
				   final String buildVersion) {
		this(Integer.parseInt(majorVersion), Integer.parseInt(minorVersion), Integer.parseInt(revisionVersion),
				Integer.parseInt(buildVersion));
	}

	/**
	 * 构造函数
	 *
	 * @param majorVersion
	 * 		主版本号
	 * @param minorVersion
	 * 		次版本号
	 * @param revisionVersion
	 * 		修订版本号
	 * @param special
	 * 		特殊版本
	 */
	public Version(final int majorVersion, final int minorVersion, final int revisionVersion, final String special) {
		this(majorVersion, minorVersion, revisionVersion, -1, special);
	}

	/**
	 * 构造函数
	 *
	 * @param majorVersion
	 * 		主版本号
	 * @param minorVersion
	 * 		次版本号
	 * @param revisionVersion
	 * 		修订版本号
	 * @param buildVersion
	 * 		build 版本号
	 * @param special
	 * 		特殊版本
	 */
	public Version(final int majorVersion, final int minorVersion, final int revisionVersion, final int buildVersion,
				   final String special) {
		this.majorVersion = majorVersion;
		this.minorVersion = minorVersion;
		this.revisionVersion = revisionVersion;
		this.buildVersion = buildVersion;
		this.special = special;

		StringBuilder sb = new StringBuilder();
		sb.append(majorVersion);

		if(this.minorVersion > -1){
			sb.append('.').append(minorVersion);
		}

		if(this.revisionVersion > -1){
			sb.append('.').append(revisionVersion);
		}

		if(this.buildVersion > -1){
			sb.append('.').append(buildVersion);
		}

		if(special != null){
			sb.append(' ').append(special);
		}

		this.version = sb.toString();
	}

	/**
	 * 构造函数
	 *
	 * @param majorVersion
	 * 		主版本号
	 * @param minorVersion
	 * 		次版本号
	 * @param revisionVersion
	 * 		修订版本号
	 * @param buildVersion
	 * 		build 版本号
	 * @param special
	 * 		特殊版本
	 */
	public Version(final String majorVersion, final String minorVersion, final String revisionVersion,
				   final String buildVersion, final String special) {
		this(Integer.parseInt(majorVersion), Integer.parseInt(minorVersion), Integer.parseInt(revisionVersion),
				Integer.parseInt(buildVersion), special);
	}

	/**
	 * 返回版本
	 *
	 * @return 版本
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * 返回主版本号
	 *
	 * @return 主版本号
	 */
	public int getMajorVersion() {
		return majorVersion;
	}

	/**
	 * 返回次版本号
	 *
	 * @return 次版本号
	 */
	public int getMinorVersion() {
		return minorVersion;
	}

	/**
	 * 返回修订版本号
	 *
	 * @return 修订版本号
	 */
	public int getRevisionVersion() {
		return revisionVersion;
	}

	/**
	 * 返回 Build 版本号
	 *
	 * @return Build 版本号
	 */
	public int getBuildVersion() {
		return buildVersion;
	}

	/**
	 * 返回特殊版本
	 *
	 * @return 特殊版本
	 */
	public String getSpecial() {
		return special;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * (result + majorVersion);
		result = prime * (result + minorVersion);
		result = prime * (result + revisionVersion);
		result = prime * (result + buildVersion);
		result = prime * (result + Objects.hashCode(special));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj){
			return true;
		}

		if(obj instanceof Version){
			Version that = (Version) obj;

			if(majorVersion == -1 || that.majorVersion == -1){
				return false;
			}

			return compareTo(that) == 0;
		}

		return false;
	}

	/**
	 * 版本号比较
	 *
	 * @param other
	 * 		待比较版本号
	 *
	 * @return 当 &lt; other 时，返回 -1；当 = other 时，返回 0；当 &gt; other 时返回 1
	 */
	@Override
	public int compareTo(Version other) {
		return other == null ? 1 : VersionUtils.compare(this, other);
	}

	@Override
	public String toString() {
		return version;
	}

	private static String[] splitVersion(final String str, final boolean preserveAllTokens) {
		if(str == null){
			return null;
		}

		final int len = str.length();
		if(len == 0){
			return new String[0];
		}

		final List<String> list = new ArrayList<>();
		int i = 0;
		int start = 0;
		boolean match = false;
		boolean lastMatch = false;
		while(i < len){
			if(str.charAt(i) == '.'){
				if(match || preserveAllTokens){
					list.add(str.substring(start, i));
					match = false;
					lastMatch = true;
				}
				start = ++i;
				continue;
			}
			lastMatch = false;
			match = true;
			i++;
		}
		if(match || preserveAllTokens && lastMatch){
			list.add(str.substring(start, i));
		}

		return list.toArray(new String[]{});
	}

	private final static class VersionUtils {

		private final static String NAN = "#N#";

		private final static Map<String, Integer> SPECIAL_VERSIONS = new LinkedHashMap<>(10);

		static {
			SPECIAL_VERSIONS.put("dev", 0);
			SPECIAL_VERSIONS.put("alpha", 1);
			SPECIAL_VERSIONS.put("a", 1);
			SPECIAL_VERSIONS.put("beta", 2);
			SPECIAL_VERSIONS.put("b", 2);
			SPECIAL_VERSIONS.put("rc", 3);
			SPECIAL_VERSIONS.put("#", 4);
			SPECIAL_VERSIONS.put("release", 4);
			SPECIAL_VERSIONS.put("pl", 5);
			SPECIAL_VERSIONS.put("p", 5);
		}

		public static int compare(final Version version1, final Version version2) {
			int result = normalize(version1.getMajorVersion() - version2.getMajorVersion());
			if(result != 0){
				return result;
			}

			result = normalize(version1.getMinorVersion() - version2.getMinorVersion());
			if(result != 0){
				return result;
			}

			result = normalize(version1.getRevisionVersion() - version2.getRevisionVersion());
			if(result != 0){
				return result;
			}

			result = normalize(version1.getBuildVersion() - version2.getBuildVersion());
			if(result != 0){
				return result;
			}

			result = compareSpecialVersion(version1.getSpecial() == null ? NAN : version1.getSpecial().toLowerCase(),
					version2.getSpecial() == null ? NAN : version2.getSpecial().toLowerCase());

			return result;
		}

		private static int compareSpecialVersion(final String version1, final String version2) {
			int found1 = -1;
			int found2 = -1;

			for(Map.Entry<String, Integer> e : SPECIAL_VERSIONS.entrySet()){
				String ver = e.getKey();
				int order = e.getValue();

				if(version1.startsWith(ver)){
					found1 = order;
				}

				if(version2.startsWith(ver)){
					found2 = order;
				}
			}

			return normalize(found1 - found2);
		}

		private static boolean isSpecialVer(final char c) {
			return c == '-' || c == '_' || c == '+';
		}

		private static boolean isAlpha(final char ch) {
			return (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z');
		}

		private static boolean isDigit(final char ch) {
			return ch >= '0' && ch <= '9';
		}

		private static boolean isAlnum(final char ch) {
			return isAlpha(ch) || isDigit(ch);
		}

		private static int normalize(final int value) {
			return Integer.compare(value, 0);
		}

	}

}
