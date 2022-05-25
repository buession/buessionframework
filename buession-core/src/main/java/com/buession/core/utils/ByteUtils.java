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
import java.util.Arrays;

/**
 * {@link Byte}、byte 工具类
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public class ByteUtils {

	/**
	 * 将 byte 数组转换为 char
	 *
	 * @param bytes
	 * 		byte 数组
	 *
	 * @return char
	 */
	public static char toChar(byte[] bytes){
		return (char) (((bytes[0] & 0xFF) << 8) | (bytes[1] & 0xFF));
	}

	/**
	 * 将 byte 数组转换为 char 数组
	 *
	 * @param bytes
	 * 		byte 数组
	 *
	 * @return char 数组
	 */
	public static char[] toChars(byte[] bytes){
		ByteBuffer byteBuffer = ByteBuffer.allocate(bytes.length);

		byteBuffer.put(bytes);
		byteBuffer.flip();

		CharBuffer charBuffer = StandardCharsets.US_ASCII.decode(byteBuffer);
		return charBuffer.array();
	}

	/**
	 * Byte 连接
	 *
	 * @param dest
	 * 		目标对象
	 * @param source
	 * 		源对象
	 *
	 * @return 连接结果
	 */
	public static byte[] concat(byte[] dest, byte[] source){
		byte[] result = Arrays.copyOf(dest, dest.length + source.length);

		System.arraycopy(source, 0, result, dest.length, source.length);

		return result;
	}

}
