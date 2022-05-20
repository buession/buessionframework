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
package com.buession.core.math;

/**
 * 数学方法工具类
 *
 * @author Yong.Teng
 */
public class Math {

	/**
	 * 计算两个数之间连续相加之和
	 *
	 * @param start
	 * 		起始数
	 * @param end
	 * 		结束数
	 *
	 * @return 两个数之间连续相加结果
	 */
	public static long continuousSum(short start, short end){
		return continuousSum((long) start, (long) end);
	}

	/**
	 * 计算两个数之间连续相加之和
	 *
	 * @param start
	 * 		起始数
	 * @param end
	 * 		结束数
	 *
	 * @return 两个数之间连续相加结果
	 */
	public static long continuousSum(int start, int end){
		return continuousSum((long) start, (long) end);
	}

	/**
	 * 计算两个数之间连续相加之和
	 *
	 * @param start
	 * 		起始数
	 * @param end
	 * 		结束数
	 *
	 * @return 两个数之间连续相加结果
	 */
	public static long continuousSum(long start, long end){
		if(start > end){
			start = start ^ end;
			end = start ^ end;
			start = start ^ end;
		}

		long count = end - start + 1;
		return count * start + count * ((count - 1) >> 1);
	}

	/**
	 * 获取合法范围内的值，如果 value &lt; min，则返回 min；如果 value &gt; max，则返回 max；否则，返回 value
	 *
	 * @param value
	 * 		当前值
	 * @param min
	 * 		最小值
	 * @param max
	 * 		最大值
	 *
	 * @return 合法范围内的值
	 */
	public static float rangeValue(float value, float min, float max){
		if(value < min){
			return min;
		}else if(value > max){
			return max;
		}else{
			return value;
		}
	}

	/**
	 * 获取合法范围内的值，如果 value &lt; min，则返回 min；如果 value &gt; max，则返回 max；否则，返回 value
	 *
	 * @param value
	 * 		当前值
	 * @param min
	 * 		最小值
	 * @param max
	 * 		最大值
	 *
	 * @return 合法范围内的值
	 */
	public static double rangeValue(double value, double min, double max){
		if(value < min){
			return min;
		}else if(value > max){
			return max;
		}else{
			return value;
		}
	}

	/**
	 * 获取合法范围内的值，如果 value &lt; min，则返回 min；如果 value &gt; max，则返回 max；否则，返回 value
	 *
	 * @param value
	 * 		当前值
	 * @param min
	 * 		最小值
	 * @param max
	 * 		最大值
	 *
	 * @return 合法范围内的值
	 */
	public static short rangeValue(short value, short min, short max){
		if(value < min){
			return min;
		}else if(value > max){
			return max;
		}else{
			return value;
		}
	}

	/**
	 * 获取合法范围内的值，如果 value &lt; min，则返回 min；如果 value &gt; max，则返回 max；否则，返回 value
	 *
	 * @param value
	 * 		当前值
	 * @param min
	 * 		最小值
	 * @param max
	 * 		最大值
	 *
	 * @return 合法范围内的值
	 */
	public static int rangeValue(int value, int min, int max){
		if(value < min){
			return min;
		}else if(value > max){
			return max;
		}else{
			return value;
		}
	}

	/**
	 * 获取合法范围内的值，如果 value &lt; min，则返回 min；如果 value &gt; max，则返回 max；否则，返回 value
	 *
	 * @param value
	 * 		当前值
	 * @param min
	 * 		最小值
	 * @param max
	 * 		最大值
	 *
	 * @return 合法范围内的值
	 */
	public static long rangeValue(long value, long min, long max){
		if(value < min){
			return min;
		}else if(value > max){
			return max;
		}else{
			return value;
		}
	}

}
