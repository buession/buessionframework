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
 * | Copyright @ 2013-2025 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.core.utils;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;

/**
 * {@link Character}、{@link char} 工具类
 *
 * @author Yong.Teng
 * @see org.apache.commons.lang3.CharUtils
 * @since 2.0.0
 */
public class CharacterUtils extends org.apache.commons.lang3.CharUtils {

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
	 * @since 4.0.0
	 */
	public static boolean startsWith(final CharSequence str, final char character, final boolean ignoreCase) {
		if(str == null){
			return false;
		}

		int length = str.length();
		if(length == 0){
			return false;
		}

		char c = str.charAt(0);
		if(ignoreCase){
			return Character.toUpperCase(c) == Character.toUpperCase(character);
		}else{
			return c == character;
		}
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
		for(char character : characters){
			if(startsWith(str, character, false)){
				return true;
			}
		}

		return false;
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
	 * @since 4.0.0
	 */
	public static boolean endsWith(final CharSequence str, final char character, final boolean ignoreCase) {
		if(str == null){
			return false;
		}

		int length = str.length();
		if(length == 0){
			return false;
		}

		char c = str.charAt(length - 1);
		if(ignoreCase){
			return Character.toUpperCase(c) == Character.toUpperCase(character);
		}else{
			return c == character;
		}
	}

	/**
	 * 将 char 转换为 byte 数组
	 *
	 * @param c
	 * 		char
	 *
	 * @return byte 数组
	 */
	public static byte[] toBytes(char c) {
		final byte[] result = new byte[2];

		result[0] = (byte) ((c & 0xFF00) >> 8);
		result[1] = (byte) (c & 0xFF);

		return result;
	}

	/**
	 * 将 char 数组转换为 byte 数组
	 *
	 * @param chars
	 * 		char 数组
	 *
	 * @return byte 数组
	 */
	public static byte[] toBytes(char[] chars) {
		CharBuffer charBuffer = CharBuffer.allocate(chars.length);

		charBuffer.put(chars);
		charBuffer.flip();

		ByteBuffer byteBuffer = StandardCharsets.UTF_8.encode(charBuffer);
		return byteBuffer.array();
	}

}
