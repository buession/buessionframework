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
package com.buession.net.utils;

import com.buession.core.utils.StringUtils;
import com.buession.core.validator.Validate;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author Yong.Teng
 */
public class InetAddressUtils {

	private InetAddressUtils() {
	}

	/**
	 * 将长整型转化为字符串形式带点的 IPV4 地址
	 *
	 * @param l
	 * 		合格的地址的长整型的表达形式
	 *
	 * @return IPV4 地址
	 */
	public static String long2ip(long l) {
		long[] result = new long[4];
		for(int i = 4; i > 0; i--){
			result[i - 1] = (l & 0xff);
			l = l >> 8;
		}

		return StringUtils.join(result, '.');
	}

	/**
	 * 将长整型转化为字符串形式带点的 IPV4 地址的 InetAddress 对象
	 *
	 * @param l
	 * 		合格的地址的长整型的表达形式
	 *
	 * @return IPV4 地址的 InetAddress 对象
	 */
	public static InetAddress long2InetAddress(long l) {
		String ip = long2ip(l);

		try{
			return InetAddress.getByName(ip);
		}catch(UnknownHostException e){
			return null;
		}
	}

	/**
	 * 将字符串形式带点的 IPV4 地址转化为长整型
	 *
	 * @param ip
	 * 		字符串形式带点的 IPV4 地址
	 *
	 * @return IPV4 地址的长整型
	 */
	public static long ip2long(String ip) {
		if(Validate.isIpV4(ip) == false){
			throw new IllegalArgumentException("Illegal ip: " + ip + ", must be ipv4.");
		}

		String[] numbers = StringUtils.splitByWholeSeparatorPreserveAllTokens(ip, ".");
		long result = 0L;

		for(int i = 0; i < 4; ++i){
			result = result << 8 | Integer.parseInt(numbers[i]);
		}

		return result;
	}

}
