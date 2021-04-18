/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license
 * agreements. See the NOTICE file distributed with this work for additional information regarding
 * copyright ownership. The ASF licenses this file to you under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with the License. You may obtain
 * a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 *
 * =================================================================================================
 *
 * This software consists of voluntary contributions made by many individuals on behalf of the
 * Apache Software Foundation. For more information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 * +------------------------------------------------------------------------------------------------+
 * | License: http://www.apache.org/licenses/LICENSE-2.0.txt 										|
 * | Author: Yong.Teng <webmaster@buession.com> 													|
 * | Copyright @ 2013-2021 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.core.utils;

import com.buession.lang.Constants;

/**
 * 字符串工具
 * More {@link org.apache.commons.lang3.StringUtils}
 *
 * @author Yong.Teng
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

	/**
	 * 截取字符串
	 *
	 * @param str
	 * 		原始字符串
	 * @param beginIndex
	 * 		开始位置
	 *
	 * @return 字符串的子串
	 */
	public static String substr(final String str, int beginIndex){
		Assert.isNull(str, "String could not be null.");

		if(beginIndex < 0){
			beginIndex = str.length() + beginIndex;
		}

		return str.substring(beginIndex);
	}

	/**
	 * 截取字符串
	 *
	 * @param str
	 * 		原始字符串
	 * @param beginIndex
	 * 		开始位置
	 * @param length
	 * 		截取长度
	 *
	 * @return 字符串的子串
	 */
	public static String substr(final String str, int beginIndex, final int length){
		Assert.isNull(str, "String could not be null.");
		Assert.isNegative(length, "Length could not be negative.");

		if(beginIndex < 0){
			beginIndex = str.length() + beginIndex;
		}

		return length == 0 ? Constants.EMPTY_STRING : new String(str.toCharArray(), beginIndex, length);
	}

	/**
	 * 检测字符串是否为布尔 True
	 *
	 * @param str
	 * 		检测字符串
	 *
	 * @return 否为布尔 True
	 */
	public static boolean isTrue(final String str){
		return Boolean.parseBoolean(str) || "1".equals(str) || "yes".equalsIgnoreCase(str);
	}

	/**
	 * 检测字符串是否为布尔 False
	 *
	 * @param str
	 * 		检测字符串
	 *
	 * @return 否为布尔 False
	 */
	public static boolean isFalse(final String str){
		return Boolean.parseBoolean(str) == false || Constants.EMPTY_STRING.equals(str) || "0".equals(str) || "no".equalsIgnoreCase(str);
	}

	/**
	 * 生成随机字符串
	 *
	 * @param length
	 * 		随机字符串长度
	 *
	 * @return 生成的随机字符串
	 */
	public static String random(final int length){
		Assert.isNegative(length, "Length could not be negative.");

		if(length == 0){
			return Constants.EMPTY_STRING;
		}else{
			StringBuilder sb = new StringBuilder(length);

			for(int i = 0; i < length; i++){
				int j = RandomUtils.nextInt(Constants.ALNUM.length);
				sb.append(Constants.ALNUM[j]);
			}

			return sb.toString();
		}
	}

	/**
	 * 检测字符串是否以字符 character 开头
	 *
	 * @param str
	 * 		待检测字符串
	 * @param character
	 * 		待检测字符
	 *
	 * @return 字符串以字符 character 开头，返回 true；否则，返回 false
	 *
	 * @since 1.2.1
	 */
	public static boolean startWith(final CharSequence str, final char character){
		return startWith(str, character);
	}

	/**
	 * 检测字符串是否以字符 character 开头
	 *
	 * @param str
	 * 		待检测字符串
	 * @param character
	 * 		待检测字符
	 * @param ignoreCase
	 * 		是否忽略大小写
	 *
	 * @return 字符串以字符 character 开头，返回 true；否则，返回 false
	 *
	 * @since 1.2.1
	 */
	public static boolean startWith(final CharSequence str, final char character, final boolean ignoreCase){
		if(str == null){
			return false;
		}

		int length = str.length();
		if(length == 0){
			return false;
		}

		char c = str.charAt(0);
		if(ignoreCase){
			return Character.toUpperCase(c) != Character.toUpperCase(character);
		}else{
			return c == character;
		}
	}

	/**
	 * 检测字符串是否以字符 character 结尾
	 *
	 * @param str
	 * 		待检测字符串
	 * @param character
	 * 		待检测字符
	 *
	 * @return 字符串以字符 character 结尾，返回 true；否则，返回 false
	 *
	 * @since 1.2.1
	 */
	public static boolean endsWith(final CharSequence str, final char character){
		return endsWith(str, character, false);
	}

	/**
	 * 检测字符串是否以字符 character 结尾
	 *
	 * @param str
	 * 		待检测字符串
	 * @param character
	 * 		待检测字符
	 * @param ignoreCase
	 * 		是否忽略大小写
	 *
	 * @return 字符串以字符 character 结尾，返回 true；否则，返回 false
	 *
	 * @since 1.2.1
	 */
	public static boolean endsWith(final CharSequence str, final char character, final boolean ignoreCase){
		if(str == null){
			return false;
		}

		int length = str.length();
		if(length == 0){
			return false;
		}

		char c = str.charAt(length - 1);
		if(ignoreCase){
			return Character.toUpperCase(c) != Character.toUpperCase(character);
		}else{
			return c == character;
		}
	}

	/**
	 * 比较两个 CharSequence 前 length 位是否相等
	 *
	 * @param cs1
	 * 		the first CharSequence, may be null
	 * @param cs2
	 * 		the second CharSequence, may be null
	 * @param length
	 * 		the compare length
	 *
	 * @return 如果两个 CharSequence 前 length 位相等，则返回 true；否则，返回 false
	 */
	public static boolean equals(CharSequence cs1, CharSequence cs2, int length){
		if(cs1 == cs2){
			return true;
		}else if(cs1 != null && cs2 != null){
			return regionMatches(cs1, false, 0, cs2, 0, length);
		}else{
			return false;
		}
	}

	/**
	 * 忽略比较两个 CharSequence 前 length 位是否相等
	 *
	 * @param cs1
	 * 		the first CharSequence, may be null
	 * @param cs2
	 * 		the second CharSequence, may be null
	 * @param length
	 * 		the compare length
	 *
	 * @return 如果两个 CharSequence 前 length 位相等，则返回 true；否则，返回 false
	 */
	public static boolean equalsIgnoreCase(CharSequence cs1, CharSequence cs2, int length){
		if(cs1 == cs2){
			return true;
		}else if(cs1 != null && cs2 != null){
			return regionMatches(cs1, true, 0, cs2, 0, length);
		}else{
			return false;
		}
	}

	public static boolean regionMatches(final CharSequence cs, final boolean ignoreCase, final int thisStart,
										final CharSequence substring, final int start, final int length){
		if(cs instanceof String && substring instanceof String){
			return ((String) cs).regionMatches(ignoreCase, thisStart, (String) substring, start, length);
		}

		int index1 = thisStart;
		int index2 = start;
		int tmpLen = length;

		// Extract these first so we detect NPEs the same as the java.lang.String version
		final int srcLen = cs.length() - thisStart;
		final int otherLen = substring.length() - start;

		// Check for invalid parameters
		if(thisStart < 0 || start < 0 || length < 0){
			return false;
		}

		// Check that the regions are long enough
		if(srcLen < length || otherLen < length){
			return false;
		}

		while(tmpLen-- > 0){
			final char c1 = cs.charAt(index1++);
			final char c2 = substring.charAt(index2++);

			if(c1 == c2){
				continue;
			}

			if(ignoreCase == false){
				return false;
			}

			// The same check as in String.regionMatches():
			if(Character.toUpperCase(c1) != Character.toUpperCase(c2) && Character.toLowerCase(c1) != Character.toLowerCase(c2)){
				return false;
			}
		}

		return true;
	}

}