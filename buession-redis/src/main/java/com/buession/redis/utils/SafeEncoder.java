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

import com.buession.core.collect.Arrays;

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
		return strs == null ? null : Arrays.map(strs, byte[].class, (v)->v == null ? null :
				v.getBytes(StandardCharsets.UTF_8));
	}

	public static String encode(final byte[] data) {
		return data == null ? null : new String(data, StandardCharsets.UTF_8);
	}

	public static String[] encode(final byte[]... data) {
		return data == null ? null : Arrays.map(data, String.class, (v)->v == null ? null : new String(v,
				StandardCharsets.UTF_8));
	}

}
