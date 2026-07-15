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
 * | Copyright @ 2013-2026 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.core.utils;

import com.buession.lang.Constants;
import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.lang3.Strings;

import java.util.regex.Pattern;

/**
 * 字符串工具 {@link org.apache.commons.lang3.StringUtils}
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
	public static String substr(final String str, int beginIndex) {
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
	public static String substr(final String str, int beginIndex, final int length) {
		Assert.isNull(str, "String could not be null.");
		Assert.isNegative(length, "Length could not be negative.");

		if(beginIndex < 0){
			beginIndex = str.length() + beginIndex;
		}

		return length == 0 ? EMPTY : new String(str.toCharArray(), beginIndex, length);
	}

	/**
	 * 检测字符串是否为布尔 True
	 *
	 * @param str
	 * 		检测字符串
	 *
	 * @return 否为布尔 True
	 */
	public static boolean isTrue(final String str) {
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
	public static boolean isFalse(final String str) {
		return Boolean.parseBoolean(str) == false || EMPTY.equals(str) || "0".equals(str) || "no".equalsIgnoreCase(str);
	}

	/**
	 * 生成随机字符串
	 *
	 * @param length
	 * 		随机字符串长度
	 *
	 * @return 生成的随机字符串
	 */
	public static String random(final int length) {
		return random(length, Constants.ALNUM);
	}

	/**
	 * 生成随机字符串
	 *
	 * @param length
	 * 		随机字符串长度
	 * @param chars
	 * 		原字符
	 *
	 * @return 生成的随机字符串
	 *
	 * @since 2.2.0
	 */
	public static String random(final int length, final char[] chars) {
		Assert.isNegative(length, "Length could not be negative.");
		Assert.isEmpty(chars, "chars could not be negative.");

		if(length == 0){
			return EMPTY;
		}else{
			StringBuilder sb = new StringBuilder(length);

			for(int i = 0; i < length; i++){
				int j = RandomUtils.nextInt(chars.length);
				sb.append(chars[j]);
			}

			return sb.toString();
		}
	}

	/**
	 * 检测字符串是否以字符串 prefix 开头
	 *
	 * @param str
	 * 		待检测字符串
	 * @param prefix
	 * 		待检测字符串
	 *
	 * @return 字符串以字符串 character 开头，返回 true；否则，返回 false
	 *
	 * @since 4.0.0
	 */
	public static boolean startsWith(final CharSequence str, final CharSequence prefix) {
		return Strings.CS.startsWith(str, prefix);
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
	public static boolean startsWith(final CharSequence str, final char character) {
		return startsWith(str, character, false);
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
	public static boolean startsWith(final CharSequence str, final char character, final boolean ignoreCase) {
		return CharacterUtils.startsWith(str, character, ignoreCase);
	}

	/**
	 * Tests if a CharSequence starts with any of the provided case-sensitive prefixes.
	 *
	 * @param sequence
	 * 		the CharSequence to check, may be null
	 * @param searchStrings
	 * 		the case-sensitive CharSequence prefixes, may be empty or contain {@code null}
	 *
	 * @return {@code true} if the input {@code sequence} is {@code null} AND no {@code searchStrings} are provided, or
	 * the input {@code sequence} begins with any of the provided case-sensitive {@code searchStrings}.
	 *
	 * @since 4.0.0
	 */
	public static boolean startsWithAny(final CharSequence sequence, final CharSequence... searchStrings) {
		return Strings.CS.startsWithAny(sequence, searchStrings);
	}

	/**
	 * Tests if a CharSequence starts with any of the provided case-sensitive prefixes.
	 *
	 * @param str
	 * 		the CharSequence to check, may be null
	 * @param characters
	 * 		the case-sensitive Character prefixes, may be empty or contain {@code null}
	 *
	 * @return {@code true} if the input {@code str} is {@code null} AND no {@code searchStrings} are provided, or
	 * the input {@code str} begins with any of the provided case-sensitive {@code searchStrings}.
	 *
	 * @since 4.0.0
	 */
	public static boolean startsWithAny(final CharSequence str, final char... characters) {
		return CharacterUtils.startsWithAny(str, characters);
	}

	/**
	 * Case-insensitive check if a CharSequence starts with a specified prefix.
	 *
	 * <p>{@code null}s are handled without exceptions. Two {@code null}
	 * references are considered to be equal. The comparison is case insensitive.</p>
	 *
	 * @param str
	 * 		the CharSequence to check, may be null
	 * @param prefix
	 * 		the prefix to find, may be null
	 *
	 * @return {@code true} if the CharSequence starts with the prefix, case-insensitive, or
	 * both {@code null}
	 *
	 * @see String#startsWith(String)
	 */
	public static boolean startsWithIgnoreCase(final CharSequence str, final CharSequence prefix) {
		return Strings.CI.startsWith(str, prefix);
	}

	/**
	 * 忽略大小写检测字符串是否以字符 character 开头
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
	public static boolean startsWithIgnoreCase(final CharSequence str, final char character) {
		return startsWith(str, character, true);
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
	public static boolean endsWith(final CharSequence str, final char character) {
		return endsWith(str, character, false);
	}

	/**
	 * Tests if a CharSequence ends with a specified suffix.
	 *
	 * <p>{@code null}s are handled without exceptions. Two {@code null}
	 * references are considered to be equal. The comparison is case-sensitive.</p>
	 *
	 * @param str
	 * 		the CharSequence to check, may be null
	 * @param suffix
	 * 		the suffix to find, may be null
	 *
	 * @return {@code true} if the CharSequence ends with the suffix, case-sensitive, or both {@code null}
	 *
	 * @since 4.0.0
	 */
	public static boolean endsWith(final CharSequence str, final CharSequence suffix) {
		return Strings.CS.endsWith(str, suffix);
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
	public static boolean endsWith(final CharSequence str, final char character, final boolean ignoreCase) {
		return CharacterUtils.endsWith(str, character, ignoreCase);
	}

	/**
	 * Tests if a CharSequence ends with any of the provided case-sensitive suffixes.
	 *
	 * @param sequence
	 * 		the CharSequence to check, may be null
	 * @param searchStrings
	 * 		the case-sensitive CharSequences to find, may be empty or contain {@code null}
	 *
	 * @return {@code true} if the input {@code sequence} is {@code null} AND no {@code searchStrings} are provided, or
	 * the input {@code sequence} ends in any of the provided case-sensitive {@code searchStrings}.
	 *
	 * @since 4.0.0
	 */
	public static boolean endsWithAny(final CharSequence sequence, final CharSequence... searchStrings) {
		return Strings.CS.endsWithAny(sequence, searchStrings);
	}

	/**
	 * Case-insensitive check if a CharSequence ends with a specified suffix.
	 *
	 * <p>{@code null}s are handled without exceptions. Two {@code null}
	 * references are considered to be equal. The comparison is case insensitive.</p>
	 *
	 * @param str
	 * 		the CharSequence to check, may be null
	 * @param suffix
	 * 		the suffix to find, may be null
	 *
	 * @return {@code true} if the CharSequence ends with the suffix, case-insensitive, or both {@code null}
	 *
	 * @since 4.0.0
	 */
	public static boolean endsWithIgnoreCase(final CharSequence str, final CharSequence suffix) {
		return Strings.CI.endsWith(str, suffix);
	}

	/**
	 * 忽略大小写检测字符串是否以字符 character 结尾
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
	public static boolean endsWithIgnoreCase(final CharSequence str, final char character) {
		return endsWith(str, character, true);
	}

	/**
	 * Finds the first index within a CharSequence, handling {@code null}.
	 * This method uses {@link String#indexOf(String, int)} if possible.
	 *
	 * <p>A {@code null} CharSequence will return {@code -1}.</p>
	 *
	 * @param str
	 * 		the CharSequence to check, may be null
	 * @param searchStr
	 * 		the CharSequence to find, may be null
	 *
	 * @return the first index of the search CharSequence, -1 if no match or {@code null} string input
	 *
	 * @since 4.0.0
	 */
	public static int indexOf(final CharSequence str, final CharSequence searchStr) {
		return Strings.CS.indexOf(str, searchStr);
	}

	/**
	 * Finds the first index within a CharSequence, handling {@code null}.
	 * This method uses {@link String#indexOf(String, int)} if possible.
	 *
	 * <p>A {@code null} CharSequence will return {@code -1}.
	 * A negative start position is treated as zero.
	 * An empty ("") search CharSequence always matches.
	 * A start position greater than the string length only matches
	 * an empty search CharSequence.</p>
	 *
	 * @param str
	 * 		the CharSequence to check, may be null
	 * @param searchStr
	 * 		the CharSequence to find, may be null
	 * @param startPos
	 * 		the start position, negative treated as zero
	 *
	 * @return the first index of the search CharSequence (always &ge; startPos), -1 if no match or {@code null}
	 * string input
	 *
	 * @since 4.0.0
	 */
	public static int indexOf(final CharSequence str, final CharSequence searchStr, final int startPos) {
		return Strings.CS.indexOf(str, searchStr, startPos);
	}

	/**
	 * Case in-sensitive find of the first index within a CharSequence.
	 *
	 * <p>A {@code null} CharSequence will return {@code -1}.
	 * A negative start position is treated as zero.
	 * An empty ("") search CharSequence always matches.
	 * A start position greater than the string length only matches an empty search CharSequence.</p>
	 *
	 * @param str
	 * 		the CharSequence to check, may be null
	 * @param searchStr
	 * 		the CharSequence to find, may be null
	 *
	 * @return the first index of the search CharSequence, -1 if no match or {@code null} string input
	 *
	 * @since 4.0.0
	 */
	public static int indexOfIgnoreCase(final CharSequence str, final CharSequence searchStr) {
		return Strings.CI.indexOf(str, searchStr);
	}

	/**
	 * Case in-sensitive find of the first index within a CharSequence from the specified position.
	 *
	 * <p>A {@code null} CharSequence will return {@code -1}.
	 * A negative start position is treated as zero.
	 * An empty ("") search CharSequence always matches.
	 * A start position greater than the string length only matches an empty search CharSequence.</p>
	 *
	 * @param str
	 * 		the CharSequence to check, may be null
	 * @param searchStr
	 * 		the CharSequence to find, may be null
	 * @param startPos
	 * 		the start position, negative treated as zero
	 *
	 * @return the first index of the search CharSequence (always &ge; startPos), -1 if no match or {@code null}
	 * string input
	 *
	 * @since 4.0.0
	 */
	public static int indexOfIgnoreCase(final CharSequence str, final CharSequence searchStr, final int startPos) {
		return Strings.CI.indexOf(str, searchStr, startPos);
	}

	/**
	 * Finds the last index within a CharSequence, handling {@code null}.
	 * This method uses {@link String#lastIndexOf(String)} if possible.
	 *
	 * <p>A {@code null} CharSequence will return {@code -1}.</p>
	 *
	 * @param str
	 * 		the CharSequence to check, may be null
	 * @param searchStr
	 * 		the CharSequence to find, may be null
	 *
	 * @return the last index of the search String, -1 if no match or {@code null} string input
	 *
	 * @since 4.0.0
	 */
	public static int lastIndexOf(final CharSequence str, final CharSequence searchStr) {
		return Strings.CS.lastIndexOf(str, searchStr);
	}

	/**
	 * Finds the last index within a CharSequence, handling {@code null}.
	 * This method uses {@link String#lastIndexOf(String, int)} if possible.
	 *
	 * <p>A {@code null} CharSequence will return {@code -1}.
	 * A negative start position returns {@code -1}.
	 * An empty ("") search CharSequence always matches unless the start position is negative.
	 * A start position greater than the string length searches the whole string.
	 * The search starts at the startPos and works backwards; matches starting after the start position are ignored.</p>
	 *
	 * @param str
	 * 		the CharSequence to check, may be null
	 * @param searchStr
	 * 		the CharSequence to find, may be null
	 * @param startPos
	 * 		the start position, negative treated as zero
	 *
	 * @return the last index of the search CharSequence (always &le; startPos), -1 if no match or {@code null}
	 * string input
	 *
	 * @since 4.0.0
	 */
	public static int lastIndexOf(final CharSequence str, final CharSequence searchStr, final int startPos) {
		return Strings.CS.lastIndexOf(str, searchStr, startPos);
	}

	/**
	 * Case in-sensitive find of the last index within a CharSequence.
	 *
	 * <p>A {@code null} CharSequence will return {@code -1}.
	 * A negative start position returns {@code -1}.
	 * An empty ("") search CharSequence always matches unless the start position is negative.
	 * A start position greater than the string length searches the whole string.</p>
	 *
	 * @param str
	 * 		the CharSequence to check, may be null
	 * @param searchStr
	 * 		the CharSequence to find, may be null
	 *
	 * @return the first index of the search CharSequence, -1 if no match or {@code null} string input
	 *
	 * @since 4.0.0
	 */
	public static int lastIndexOfIgnoreCase(final CharSequence str, final CharSequence searchStr) {
		return Strings.CI.lastIndexOf(str, searchStr);
	}

	/**
	 * Case in-sensitive find of the last index within a CharSequence from the specified position.
	 *
	 * <p>A {@code null} CharSequence will return {@code -1}.
	 * A negative start position returns {@code -1}.
	 * An empty ("") search CharSequence always matches unless the start position is negative.
	 * A start position greater than the string length searches the whole string.
	 * The search starts at the startPos and works backwards; matches starting after the start position are ignored.</p>
	 *
	 * @param str
	 * 		the CharSequence to check, may be null
	 * @param searchStr
	 * 		the CharSequence to find, may be null
	 * @param startPos
	 * 		the start position
	 *
	 * @return the last index of the search CharSequence (always &le; startPos), -1 if no match or {@code null} input
	 *
	 * @since 4.0.0
	 */
	public static int lastIndexOfIgnoreCase(final CharSequence str, final CharSequence searchStr, final int startPos) {
		return Strings.CI.lastIndexOf(str, searchStr, startPos);
	}

	/**
	 * Tests if CharSequence contains a search CharSequence, handling {@code null}.
	 * This method uses {@link String#indexOf(String)} if possible.
	 *
	 * <p>A {@code null} CharSequence will return {@code false}.</p>
	 *
	 * @param seq
	 * 		the CharSequence to check, may be null
	 * @param searchStr
	 * 		the CharSequence to find, may be null
	 *
	 * @return true if the CharSequence contains the search CharSequence, false if not or {@code null} string input
	 *
	 * @since 4.0.0
	 */
	public static boolean contains(final CharSequence seq, final CharSequence searchStr) {
		return Strings.CS.contains(seq, searchStr);
	}

	/**
	 * 检测字符串是否包含字符 character
	 *
	 * @param str
	 * 		待检测字符串
	 * @param character
	 * 		待检测字符
	 *
	 * @return 字符串包含字符 character，返回 true；否则，返回 false
	 *
	 * @since 1.2.1
	 */
	public static boolean contains(final CharSequence str, final char character) {
		return contains(str, (int) character);
	}

	/**
	 * 检测字符串是否包含字符 character
	 *
	 * @param str
	 * 		待检测字符串
	 * @param character
	 * 		待检测字符
	 * @param ignoreCase
	 * 		是否忽略大小写
	 *
	 * @return 字符串包含字符 character，返回 true；否则，返回 false
	 *
	 * @since 1.2.1
	 */
	public static boolean contains(final CharSequence str, final char character, final boolean ignoreCase) {
		if(ignoreCase){
			if(isEmpty(str)){
				return false;
			}else{
				return contains(str.toString().toLowerCase(), Character.toUpperCase((int) character));
			}
		}else{
			return contains(str, (int) character);
		}
	}

	/**
	 * 忽略大小写检测字符串是否包含字符 character
	 *
	 * @param str
	 * 		待检测字符串
	 * @param character
	 * 		待检测字符
	 *
	 * @return 字符串包含字符 character，返回 true；否则，返回 false
	 *
	 * @since 1.2.1
	 */
	public static boolean containsIgnoreCase(final CharSequence str, final char character) {
		return contains(str, character, true);
	}

	/**
	 * Tests if the CharSequence contains any of the CharSequences in the given array.
	 *
	 * <p>
	 * A {@code null} {@code str} CharSequence will return {@code false}. A {@code null} or zero length search array will
	 * return {@code false}.
	 * </p>
	 *
	 * @param str
	 * 		The CharSequence to check, may be null
	 * @param searchCharSequences
	 * 		The array of CharSequences to search for, may be null. Individual CharSequences may be null as well.
	 *
	 * @return {@code true} if any of the search CharSequences are found, {@code false} otherwise
	 *
	 * @since 4.0.0
	 */
	public static boolean containsAny(final CharSequence str, final CharSequence... searchCharSequences) {
		return Strings.CS.containsAny(str, searchCharSequences);
	}

	/**
	 * Tests if the CharSequence contains any of the CharSequences in the given array, ignoring case.
	 *
	 * <p>
	 * A {@code null} {@code str} CharSequence will return {@code false}. A {@code null} or zero length search array will
	 * return {@code false}.
	 * </p>
	 *
	 * @param str
	 * 		The CharSequence to check, may be null
	 * @param searchCharSequences
	 * 		The array of CharSequences to search for, may be null. Individual CharSequences may be null as well.
	 *
	 * @return {@code true} if any of the search CharSequences are found, {@code false} otherwise
	 *
	 * @since 4.0.0
	 */
	public static boolean containsAnyIgnoreCase(final CharSequence str, final CharSequence... searchCharSequences) {
		return Strings.CI.containsAny(str, searchCharSequences);
	}

	/**
	 * Tests if CharSequence contains a search CharSequence irrespective of case,
	 * handling {@code null}. Case-insensitivity is defined as by {@link String#equalsIgnoreCase(String)}.
	 *
	 * <p>A {@code null} CharSequence will return {@code false}.
	 *
	 * @param str
	 * 		the CharSequence to check, may be null
	 * @param searchStr
	 * 		the CharSequence to find, may be null
	 *
	 * @return true if the CharSequence contains the search CharSequence irrespective of case or false if not or
	 * {@code null} string input
	 *
	 * @since 4.0.0
	 */
	public static boolean containsIgnoreCase(final CharSequence str, final CharSequence searchStr) {
		return Strings.CI.contains(str, searchStr);
	}

	/**
	 * Compares two CharSequences, returning {@code true} if they represent equal sequences of characters.
	 *
	 * <p>{@code null}s are handled without exceptions. Two {@code null}
	 * references are considered to be equal. The comparison is <strong>case-sensitive</strong>.</p>
	 *
	 * @param cs1
	 * 		the first CharSequence, may be {@code null}
	 * @param cs2
	 * 		the second CharSequence, may be {@code null}
	 *
	 * @return {@code true} if the CharSequences are equal (case-sensitive), or both {@code null}
	 *
	 * @since 4.0.0
	 */
	public static boolean equals(final CharSequence cs1, final CharSequence cs2) {
		return Strings.CS.equals(cs1, cs2);
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
	public static boolean equals(final CharSequence cs1, final CharSequence cs2, final int length) {
		if(cs1 == cs2){
			return true;
		}else if(cs1 != null && cs2 != null){
			return regionMatches(cs1, false, 0, cs2, 0, length);
		}else{
			return false;
		}
	}

	/**
	 * Compares given {@code str} to a CharSequences vararg of {@code searchStrs},
	 * returning {@code true} if the {@code string} is equal to any of the {@code searchStrs}.
	 *
	 * @param str
	 * 		to compare, may be {@code null}.
	 * @param searchStrs
	 * 		a vararg of strings, may be {@code null}.
	 *
	 * @return {@code true} if the string is equal (case-sensitive) to any other element of {@code searchStrs};
	 * {@code false} if {@code searchStrs} is null or contains no matches.
	 *
	 * @since 4.0.0
	 */
	public static boolean equalsAny(final CharSequence str, final CharSequence... searchStrs) {
		return Strings.CS.equalsAny(str, searchStrs);
	}

	/**
	 * Compares given {@code str} to a CharSequences vararg of {@code searchStrs},
	 * returning {@code true} if the {@code string} is equal to any of the {@code searchStrs}, ignoring case.
	 *
	 * @param str
	 * 		to compare, may be {@code null}.
	 * @param searchStrs
	 * 		a vararg of strings, may be {@code null}.
	 *
	 * @return {@code true} if the string is equal (case-insensitive) to any other element of {@code searchStrs};
	 * {@code false} if {@code searchStrs} is null or contains no matches.
	 *
	 * @since 4.0.0
	 */
	public static boolean equalsAnyIgnoreCase(final CharSequence str, final CharSequence... searchStrs) {
		return Strings.CI.equalsAny(str, searchStrs);
	}

	/**
	 * Compares two CharSequences, returning {@code true} if they represent
	 * equal sequences of characters, ignoring case.
	 *
	 * <p>{@code null}s are handled without exceptions. Two {@code null}
	 * references are considered equal. The comparison is <strong>case insensitive</strong>.</p>
	 *
	 * @param cs1
	 * 		the first CharSequence, may be {@code null}
	 * @param cs2
	 * 		the second CharSequence, may be {@code null}
	 *
	 * @return {@code true} if the CharSequences are equal (case-insensitive), or both {@code null}
	 *
	 * @since 4.0.0
	 */
	public static boolean equalsIgnoreCase(final CharSequence cs1, final CharSequence cs2) {
		return Strings.CI.equals(cs1, cs2);
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
	public static boolean equalsIgnoreCase(final CharSequence cs1, final CharSequence cs2, final int length) {
		if(cs1 == cs2){
			return true;
		}else if(cs1 != null && cs2 != null){
			return regionMatches(cs1, true, 0, cs2, 0, length);
		}else{
			return false;
		}
	}

	/**
	 * 取前 N 个字符，如字符总数大于 N，则会在字符末尾添加省略号
	 *
	 * @param str
	 * 		原始字符串
	 * @param length
	 * 		截取长度
	 *
	 * @return 截取后的字符串
	 *
	 * @since 2.3.2
	 */
	public static String formatEllipsis(final String str, final int length) {
		if(str != null){
			if(str.length() > length){
				return str.substring(0, length) + "...";
			}
		}

		return str;
	}

	/**
	 * Replaces all occurrences of a String within another String.
	 *
	 * <p>A {@code null} reference passed to this method is a no-op.</p>
	 *
	 * @param str
	 * 		text to search and replace in, may be null
	 * @param searchStr
	 * 		the String to search for, may be null
	 * @param replacement
	 * 		the String to replace it with, may be null
	 *
	 * @return the text with any replacements processed, {@code null} if null String input
	 *
	 * @since 4.0.0
	 */
	public static String replace(final String str, final String searchStr, final String replacement) {
		return Strings.CS.replace(str, searchStr, replacement);
	}

	/**
	 * Replaces a String with another String inside a larger String, for the first {@code max} values of the search
	 * String.
	 *
	 * <p>A {@code null} reference passed to this method is a no-op.</p>
	 *
	 * @param str
	 * 		text to search and replace in, may be null
	 * @param searchStr
	 * 		the String to search for, may be null
	 * @param replacement
	 * 		the String to replace it with, may be null
	 * @param max
	 * 		maximum number of values to replace, or {@code -1} if no maximum
	 *
	 * @return the text with any replacements processed, {@code null} if null String input
	 *
	 * @since 4.0.0
	 */
	public static String replace(final String str, final String searchStr, final String replacement,
	                             final int max) {
		return Strings.CS.replace(str, searchStr, replacement, max);
	}

	/**
	 * Case insensitively replaces a String with another String inside a larger String,
	 * for the first {@code max} values of the search String.
	 *
	 * <p>A {@code null} reference passed to this method is a no-op.</p>
	 *
	 * @param str
	 * 		text to search and replace in, may be null
	 * @param searchStr
	 * 		the String to search for (case-insensitive), may be null
	 * @param replacement
	 * 		the String to replace it with, may be null
	 *
	 * @return the text with any replacements processed, {@code null} if null String input
	 *
	 * @since 4.0.0
	 */
	public static String replaceIgnoreCase(final String str, final String searchStr, final String replacement) {
		return Strings.CI.replace(str, searchStr, replacement, -1);
	}

	/**
	 * Case insensitively replaces a String with another String inside a larger String,
	 * for the first {@code max} values of the search String.
	 *
	 * <p>A {@code null} reference passed to this method is a no-op.</p>
	 *
	 * @param str
	 * 		text to search and replace in, may be null
	 * @param searchStr
	 * 		the String to search for (case-insensitive), may be null
	 * @param replacement
	 * 		the String to replace it with, may be null
	 * @param max
	 * 		maximum number of values to replace, or {@code -1} if no maximum
	 *
	 * @return the text with any replacements processed, {@code null} if null String input
	 *
	 * @since 4.0.0
	 */
	public static String replaceIgnoreCase(final String str, final String searchStr, final String replacement,
	                                       final int max) {
		return Strings.CI.replace(str, searchStr, replacement, max);
	}

	/**
	 * Replaces the first substring of the text string that matches the given regular expression
	 * with the given replacement.
	 *
	 * <p>A {@code null} reference passed to this method is a no-op.</p>
	 *
	 * <p>The {@link Pattern#DOTALL} option is NOT automatically added.
	 * To use the DOTALL option prepend {@code "(?s)"} to the regex.
	 * DOTALL is also known as single-line mode in Perl.</p>
	 *
	 * @param str
	 * 		text to search and replace in, may be null
	 * @param regex
	 * 		the regular expression to which this string is to be matched
	 * @param replacement
	 * 		the string to be substituted for the first match
	 *
	 * @return the text with the first replacement processed, {@code null} if null String input
	 *
	 * @throws java.util.regex.PatternSyntaxException
	 * 		if the regular expression's syntax is invalid
	 * @since 4.0.0
	 */
	public static String replaceFirst(final String str, final String regex, final String replacement) {
		return RegExUtils.replaceFirst(str, regex, replacement);
	}

	/**
	 * Replaces a String with another String inside a larger String, once.
	 *
	 * <p>A {@code null} reference passed to this method is a no-op.</p>
	 *
	 * @param str
	 * 		text to search and replace in, may be null
	 * @param searchString
	 * 		the String to search for, may be null
	 * @param replacement
	 * 		the String to replace with, may be null
	 *
	 * @return the text with any replacements processed, {@code null} if null String input
	 *
	 * @since 4.0.0
	 */
	public static String replaceOnce(final String str, final String searchString, final String replacement) {
		return Strings.CS.replaceOnce(str, searchString, replacement);
	}

	/**
	 * Case insensitively replaces a String with another String inside a larger String, once.
	 *
	 * <p>A {@code null} reference passed to this method is a no-op.</p>
	 *
	 * @param str
	 * 		text to search and replace in, may be null
	 * @param searchString
	 * 		the String to search for (case-insensitive), may be null
	 * @param replacement
	 * 		the String to replace with, may be null
	 *
	 * @return the text with any replacements processed, {@code null} if null String input
	 *
	 * @since 4.0.0
	 */
	public static String replaceOnceIgnoreCase(final String str, final String searchString, final String replacement) {
		return Strings.CI.replaceOnce(str, searchString, replacement);
	}

	/**
	 * Replaces each substring of the text String that matches the given regular expression
	 * with the given replacement.
	 *
	 * <p>A {@code null} reference passed to this method is a no-op.</p>
	 *
	 * <p>Unlike in the {@link #replacePattern(String, String, String)} method, the {@link Pattern#DOTALL} option
	 * is NOT automatically added.
	 * To use the DOTALL option prepend {@code "(?s)"} to the regex.
	 * DOTALL is also known as single-line mode in Perl.</p>
	 *
	 * @param str
	 * 		text to search and replace in, may be null
	 * @param regex
	 * 		the regular expression to which this string is to be matched
	 * @param replacement
	 * 		the string to be substituted for each match
	 *
	 * @return the text with any replacements processed, {@code null} if null String input
	 *
	 * @throws java.util.regex.PatternSyntaxException
	 * 		if the regular expression's syntax is invalid
	 * @since 4.0.0
	 */
	public static String replaceAll(final String str, final String regex, final String replacement) {
		return RegExUtils.replaceAll(str, regex, replacement);
	}

	/**
	 * Compare two Strings lexicographically, as per {@link String#compareTo(String)}, returning :
	 * <ul>
	 *  <li>{@code int = 0}, if {@code str1} is equal to {@code str2} (or both {@code null})</li>
	 *  <li>{@code int < 0}, if {@code str1} is less than {@code str2}</li>
	 *  <li>{@code int > 0}, if {@code str1} is greater than {@code str2}</li>
	 * </ul>
	 *
	 * <p>This is a {@code null} safe version of :</p>
	 * <blockquote><pre>str1.compareTo(str2)</pre></blockquote>
	 *
	 * <p>{@code null} value is considered less than non-{@code null} value.
	 * Two {@code null} references are considered equal.</p>
	 *
	 * @param str1
	 * 		the String to compare from
	 * @param str2
	 * 		the String to compare to
	 *
	 * @return &lt; 0, 0, &gt; 0, if {@code str1} is respectively less, equal or greater than {@code str2}
	 *
	 * @since 4.0.0
	 */
	public static int compare(final String str1, final String str2) {
		return Strings.CS.compare(str1, str2);
	}

	/**
	 * Compare two Strings lexicographically, ignoring case differences,
	 * as per {@link String#compareToIgnoreCase(String)}, returning :
	 * <ul>
	 *  <li>{@code int = 0}, if {@code str1} is equal to {@code str2} (or both {@code null})</li>
	 *  <li>{@code int < 0}, if {@code str1} is less than {@code str2}</li>
	 *  <li>{@code int > 0}, if {@code str1} is greater than {@code str2}</li>
	 * </ul>
	 *
	 * <p>This is a {@code null} safe version of :</p>
	 * <blockquote><pre>str1.compareToIgnoreCase(str2)</pre></blockquote>
	 *
	 * <p>{@code null} value is considered less than non-{@code null} value.
	 * Two {@code null} references are considered equal.
	 * Comparison is case insensitive.</p>
	 *
	 * @param str1
	 * 		the String to compare from
	 * @param str2
	 * 		the String to compare to
	 *
	 * @return &lt; 0, 0, &gt; 0, if {@code str1} is respectively less, equal ou greater than {@code str2}, ignoring
	 * case differences.
	 *
	 * @since 4.0.0
	 */
	public static int compareIgnoreCase(final String str1, final String str2) {
		return Strings.CI.compare(str1, str2);
	}

	/**
	 * Removes all occurrences of a substring from within the source string.
	 *
	 * <p>A {@code null} source string will return {@code null}.
	 * An empty ("") source string will return the empty string.
	 * A {@code null} remove string will return the source string.
	 * An empty ("") remove string will return the source string.</p>
	 *
	 * @param str
	 * 		the source String to search, may be null
	 * @param remove
	 * 		the String to search for and remove, may be null
	 *
	 * @return the substring with the string removed if found, {@code null} if null String input
	 *
	 * @since 4.0.0
	 */
	public static String remove(final String str, final String remove) {
		return Strings.CS.remove(str, remove);
	}

	/**
	 * Case-insensitive removal of all occurrences of a substring from within
	 * the source string.
	 *
	 * <p>
	 * A {@code null} source string will return {@code null}. An empty ("")
	 * source string will return the empty string. A {@code null} remove string
	 * will return the source string. An empty ("") remove string will return
	 * the source string.
	 * </p>
	 *
	 * @param str
	 * 		the source String to search, may be null
	 * @param remove
	 * 		the String to search for (case-insensitive) and remove, may be
	 * 		null
	 *
	 * @return the substring with the string removed if found, {@code null} if null String input
	 *
	 * @since 4.0.0
	 */
	public static String removeIgnoreCase(final String str, final String remove) {
		return Strings.CI.remove(str, remove);
	}

	/**
	 * Removes the first substring of the text string that matches the given regular expression.
	 * <p>
	 * This method is a {@code null} safe equivalent to:
	 * <ul>
	 *  <li>{@code text.replaceFirst(regex, StringUtils.EMPTY)}</li>
	 *  <li>{@code Pattern.compile(regex).matcher(text).replaceFirst(StringUtils.EMPTY)}</li>
	 * </ul>
	 *
	 * <p>A {@code null} reference passed to this method is a no-op.</p>
	 *
	 * <p>The {@link Pattern#DOTALL} option is NOT automatically added.
	 * To use the DOTALL option prepend {@code "(?s)"} to the regex.
	 * DOTALL is also known as single-line mode in Perl.</p>
	 *
	 * @param text
	 * 		text to remove from, may be null
	 * @param regex
	 * 		the regular expression to which this string is to be matched
	 *
	 * @return the text with the first replacement processed,
	 * {@code null} if null String input
	 *
	 * @throws java.util.regex.PatternSyntaxException
	 * 		if the regular expression's syntax is invalid
	 * @since 4.0.0
	 */
	public static String removeFirst(final String text, final String regex) {
		return replaceFirst(text, regex, EMPTY);
	}

	/**
	 * Removes a char only if it is at the beginning of a source string,
	 * otherwise returns the source string.
	 *
	 * <p>A {@code null} source string will return {@code null}.
	 * An empty ("") source string will return the empty string.
	 * A {@code null} search char will return the source string.</p>
	 *
	 * @param str
	 * 		the source String to search, may be null.
	 * @param remove
	 * 		the char to search for and remove.
	 *
	 * @return the substring with the char removed if found, {@code null} if null String input.
	 *
	 * @since 4.0.0
	 */
	public static String removeStart(final String str, final char remove) {
		if(isEmpty(str)){
			return str;
		}
		return str.charAt(0) == remove ? str.substring(1) : str;
	}

	/**
	 * Removes a substring only if it is at the beginning of a source string,
	 * otherwise returns the source string.
	 *
	 * <p>A {@code null} source string will return {@code null}.
	 * An empty ("") source string will return the empty string.
	 * A {@code null} search string will return the source string.</p>
	 *
	 * @param str
	 * 		the source String to search, may be null
	 * @param remove
	 * 		the String to search for and remove, may be null
	 *
	 * @return the substring with the string removed if found, {@code null} if null String input
	 *
	 * @since 4.0.0
	 */
	public static String removeStart(final String str, final String remove) {
		return Strings.CS.removeStart(str, remove);
	}

	/**
	 * Case-insensitive removal of a substring if it is at the beginning of a source string,
	 * otherwise returns the source string.
	 *
	 * <p>A {@code null} source string will return {@code null}.
	 * An empty ("") source string will return the empty string.
	 * A {@code null} search string will return the source string.</p>
	 *
	 * @param str
	 * 		the source String to search, may be null
	 * @param remove
	 * 		the String to search for (case-insensitive) and remove, may be null
	 *
	 * @return the substring with the string removed if found, {@code null} if null String input
	 *
	 * @since 4.0.0
	 */
	public static String removeStartIgnoreCase(final String str, final String remove) {
		return Strings.CI.removeStart(str, remove);
	}

	/**
	 * Removes a substring only if it is at the end of a source string,
	 * otherwise returns the source string.
	 *
	 * <p>A {@code null} source string will return {@code null}.
	 * An empty ("") source string will return the empty string.
	 * A {@code null} search string will return the source string.</p>
	 *
	 * @param str
	 * 		the source String to search, may be null
	 * @param remove
	 * 		the String to search for and remove, may be null
	 *
	 * @return the substring with the string removed if found, {@code null} if null String input
	 *
	 * @since 4.0.0
	 */
	public static String removeEnd(final String str, final String remove) {
		return Strings.CS.removeEnd(str, remove);
	}

	/**
	 * Case-insensitive removal of a substring if it is at the end of a source string,
	 * otherwise returns the source string.
	 *
	 * <p>A {@code null} source string will return {@code null}.
	 * An empty ("") source string will return the empty string.
	 * A {@code null} search string will return the source string.</p>
	 *
	 * @param str
	 * 		the source String to search, may be null
	 * @param remove
	 * 		the String to search for (case-insensitive) and remove, may be null
	 *
	 * @return the substring with the string removed if found, {@code null} if null String input
	 *
	 * @since 4.0.0
	 */
	public static String removeEndIgnoreCase(final String str, final String remove) {
		return Strings.CI.removeEnd(str, remove);
	}

	/**
	 * Removes each substring of the text String that matches the given regular expression.
	 * <p>
	 * This method is a {@code null} safe equivalent to:
	 * <ul>
	 *  <li>{@code text.replaceAll(regex, StringUtils.EMPTY)}</li>
	 *  <li>{@code Pattern.compile(regex).matcher(text).replaceAll(StringUtils.EMPTY)}</li>
	 * </ul>
	 *
	 * <p>A {@code null} reference passed to this method is a no-op.</p>
	 *
	 * <p>Unlike in the {@link #removePattern(String, String)} method, the {@link Pattern#DOTALL} option
	 * is NOT automatically added.
	 * To use the DOTALL option prepend {@code "(?s)"} to the regex.
	 * DOTALL is also known as single-line mode in Perl.</p>
	 *
	 * @param text
	 * 		text to remove from, may be null
	 * @param regex
	 * 		the regular expression to which this string is to be matched
	 *
	 * @return the text with any removes processed, {@code null} if null String input
	 *
	 * @throws java.util.regex.PatternSyntaxException
	 * 		if the regular expression's syntax is invalid
	 * @since 4.0.0
	 */
	public static String removeAll(final String text, final String regex) {
		return RegExUtils.removeAll(text, regex);
	}

	public static boolean regionMatches(final CharSequence cs, final boolean ignoreCase, final int thisStart,
	                                    final CharSequence substring, final int start, final int length) {
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
			if(Character.toUpperCase(c1) != Character.toUpperCase(c2) &&
					Character.toLowerCase(c1) != Character.toLowerCase(c2)){
				return false;
			}
		}

		return true;
	}

}