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
 * | Copyright @ 2013-2022 Buession.com Inc.														       |
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
	 * 将 char 转换为 byte 数组
	 *
	 * @param c
	 * 		char
	 *
	 * @return byte 数组
	 */
	public static byte[] toBytes(char c){
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
	public static byte[] toBytes(char[] chars){
		CharBuffer charBuffer = CharBuffer.allocate(chars.length);

		charBuffer.put(chars);
		charBuffer.flip();

		ByteBuffer byteBuffer = StandardCharsets.UTF_8.encode(charBuffer);
		return byteBuffer.array();
	}

}
