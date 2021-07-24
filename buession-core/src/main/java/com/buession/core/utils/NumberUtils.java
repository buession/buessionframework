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
 * | Copyright @ 2013-2021 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.core.utils;

/**
 * 数字工具类
 *
 * @author Yong.Teng
 */
public class NumberUtils {

	/**
	 * 将 int 转换为 byte[]
	 *
	 * @param value
	 * 		int 类型的值
	 *
	 * @return byte[] 值
	 */
	public static byte[] int2bytes(final int value){
		byte[] result = new byte[4];

		for(int i = 0; i < 4; ++i){
			int offset = 32 - (i + 1) * 8;
			result[i] = (byte) ((value >> offset) & 0xff);
		}

		return result;
	}

	/**
	 * 将 byte[] 转换为 int
	 *
	 * @param value
	 * 		byte[] 类型的值
	 *
	 * @return int 值
	 */
	public static int bytes2int(final byte[] value){
		int result = 0;

		for(int i = 0; i < 4; ++i){
			result <<= 8;
			result |= (value[i] & 0xff);
		}

		return result;
	}

	/**
	 * 将 long 转换为 byte[]
	 *
	 * @param value
	 * 		long 类型的值
	 *
	 * @return byte[] 值
	 */
	public static byte[] long2bytes(final long value){
		byte[] result = new byte[8];

		for(int i = 0; i < 8; ++i){
			int offset = 64 - (i + 1) * 8;
			result[i] = (byte) ((value >> offset) & 0xff);
		}

		return result;
	}

	/**
	 * 将 byte[] 转换为 long
	 *
	 * @param value
	 * 		byte[] 类型的值
	 *
	 * @return long 值
	 */
	public static long bytes2long(final byte[] value){
		long result = 0;

		for(int i = 0; i < 8; ++i){
			result <<= 8;
			result |= (value[i] & 0xff);
		}

		return result;
	}

	/**
	 * 将 float 转换为 byte[]
	 *
	 * @param value
	 * 		float 类型的值
	 *
	 * @return byte[] 值
	 */
	public static byte[] float2bytes(final float value){
		return int2bytes(Float.floatToIntBits(value));
	}

	/**
	 * 将 byte[] 转换为 float
	 *
	 * @param value
	 * 		byte[] 类型的值
	 *
	 * @return float 值
	 */
	public static double bytes2float(final byte[] value){
		return (float) bytes2int(value);
	}

	/**
	 * 将 double 转换为 byte[]
	 *
	 * @param value
	 * 		double 类型的值
	 *
	 * @return byte[] 值
	 */
	public static byte[] double2bytes(final double value){
		return long2bytes(Double.doubleToLongBits(value));
	}

	/**
	 * 将 byte[] 转换为 double
	 *
	 * @param value
	 * 		byte[] 类型的值
	 *
	 * @return double 值
	 */
	public static double bytes2double(final byte[] value){
		return (double) bytes2long(value);
	}

}
