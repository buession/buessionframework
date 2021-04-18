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

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 短链接生成器
 *
 * @author Yong.Teng
 */
public class ShortUrlGenerator {

	private final static String ALGO = "MD5";

	private final static int LENGTH = 6;

	private final static int GROUP_SIZE = 4;

	private ShortUrlGenerator(){

	}

	public final static String[] encode(final String url){
		Assert.isBlank(url, "Encode short url cloud not be null or empty");

		String md5Str = doEncode(url);
		String sTempSubString;
		String[] result = new String[GROUP_SIZE];

		for(int i = 0; i < GROUP_SIZE; i++){
			// 把加密字符按照 8 位一组 16 进制与 0x3FFFFFFF 进行位与运算
			sTempSubString = md5Str.substring(i << 3, i << 3 + 8);

			// 这里需要使用 long 型来转换，因为 Integer.parseInt() 只能处理 31 位, 首位为符号位, 如果不用 long ，则会越界
			long lHexLong = 0x3FFFFFFF & Long.parseLong(sTempSubString, 16);
			char[] outChars = new char[LENGTH];

			for(int j = 0; j < LENGTH; j++){
				// 把得到的值与 0x0000003D 进行位与运算，取得字符数组 chars 索引
				long index = 0x0000003D & lHexLong;
				// 把取得的字符相加
				outChars[i] = Constants.ALNUM[(int) index];
				// 每次循环按位右移 5 位
				lHexLong = lHexLong >> 5;
			}

			// 把字符串存入对应索引的输出数组
			result[i] = new String(outChars);
		}

		return result;
	}

	private final static String doEncode(final String url){
		try{
			MessageDigest messageDigest = MessageDigest.getInstance(ALGO);
			messageDigest.update(url.getBytes(StandardCharsets.UTF_8));
			return getFormattedText(messageDigest.digest());
		}catch(NoSuchAlgorithmException e){
			throw new SecurityException(e);
		}
	}

	private final static String getFormattedText(byte[] bytes){
		final StringBuilder buffer = new StringBuilder(bytes.length << 1);

		for(byte b : bytes){
			buffer.append(Constants.HEX_DIGITS[(b >> 4) & 0x0f]);
			buffer.append(Constants.HEX_DIGITS[b & 0x0f]);
		}

		return buffer.toString();
	}

}
