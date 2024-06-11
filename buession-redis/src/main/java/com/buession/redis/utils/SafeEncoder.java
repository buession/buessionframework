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
 * | Copyright @ 2013-2024 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.utils;

import java.nio.charset.StandardCharsets;

/**
 * @author Yong.Teng
 */
public class SafeEncoder {

	private SafeEncoder() {

	}

	public static byte[] encode(final String str) {
		return str == null ? null : str.getBytes(StandardCharsets.UTF_8);
	}

	public static byte[][] encode(final String... strs) {
		if(strs == null){
			return null;
		}else{
			final byte[][] result = new byte[strs.length][];

			for(int i = 0; i < strs.length; i++){
				result[i] = strs[i] == null ? null : strs[i].getBytes(StandardCharsets.UTF_8);
			}

			return result;
		}
	}

	public static String encode(final byte[] data) {
		return data == null ? null : new String(data, StandardCharsets.UTF_8);
	}

	public static String[] encode(final byte[]... data) {
		if(data == null){
			return null;
		}else{
			final String[] result = new String[data.length];

			for(int i = 0; i < data.length; i++){
				result[i] = data[i] == null ? null : new String(data[i], StandardCharsets.UTF_8);
			}

			return result;
		}
	}

}
