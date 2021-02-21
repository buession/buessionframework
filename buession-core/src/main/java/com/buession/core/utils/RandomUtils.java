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

import java.util.Random;

/**
 * 随机数工具类
 *
 * @author Yong.Teng
 * @since 1.2.0
 */
public class RandomUtils {

	private final static Random RANDOM = new Random();

	private RandomUtils(){
	}

	/**
	 * 返回随机布尔值
	 *
	 * @return 随机布尔值
	 *
	 * @since 1.2.0
	 */
	public final static boolean nextBoolean(){
		return RANDOM.nextBoolean();
	}

	/**
	 * 返回随机字节数组
	 *
	 * @param count
	 * 		返回数量
	 *
	 * @return 随机字节数组
	 *
	 * @since 1.2.0
	 */
	public final static byte[] nextBytes(final int count){
		Assert.isZeroNegative(count, "Count cannot be negative.");

		final byte[] result = new byte[count];
		RANDOM.nextBytes(result);
		return result;
	}

	/**
	 * 返回 0 到 Integer.MAX_VALUE 之间的随机 int
	 *
	 * @return 随机 int
	 *
	 * @since 1.2.0
	 */
	public final static int nextInt(){
		return nextInt(0, Integer.MAX_VALUE);
	}

	/**
	 * 返回 0 到 bound - 1 范围之间的随机 int
	 *
	 * @param bound
	 * 		bound
	 *
	 * @return 0 到 bound - 1 范围之间的随机 int
	 *
	 * @since 1.2.0
	 */
	public final static int nextInt(final int bound){
		return RANDOM.nextInt(bound);
	}

	/**
	 * 返回 start 到 end 范围之间的随机 int
	 *
	 * @param start
	 * 		起始值
	 * @param end
	 * 		结束值
	 *
	 * @return start 到 end 范围之间的随机 int
	 *
	 * @since 1.2.0
	 */
	public final static int nextInt(final int start, final int end){
		Assert.isTrue(start >= end, "Start value must be smaller or equal to end value.");
		Assert.isTrue(start < 0, "Both range values must be non-negative.");

		if(start == end){
			return start;
		}

		return start + RANDOM.nextInt(end - start);
	}

	/**
	 * 返回 0 到 Long.MAX_VALUE 之间的随机 long
	 *
	 * @return 随机 long
	 *
	 * @since 1.2.0
	 */
	public final static long nextLong(){
		return nextLong(0L, Long.MAX_VALUE);
	}

	/**
	 * 返回 0 到 bound - 1 范围之间的随机 long
	 *
	 * @param bound
	 * 		bound
	 *
	 * @return 0 到 bound - 1 范围之间的随机 long
	 *
	 * @since 1.2.0
	 */
	public final static long nextLong(final long bound){
		return nextLong(0, bound - 1L);
	}

	/**
	 * 返回 start 到 end 范围之间的随机 long
	 *
	 * @param start
	 * 		起始值
	 * @param end
	 * 		结束值
	 *
	 * @return start 到 end 范围之间的随机 long
	 *
	 * @since 1.2.0
	 */
	public final static long nextLong(final long start, final long end){
		Assert.isTrue(start >= end, "Start value must be smaller or equal to end value.");
		Assert.isTrue(start < 0, "Both range values must be non-negative.");

		if(start == end){
			return start;
		}

		return (long) nextDouble(start, end);
	}

	/**
	 * 返回 0 到 Float.MAX_VALUE 之间的随机 float
	 *
	 * @return 随机 float
	 *
	 * @since 1.2.0
	 */
	public final static float nextFloat(){
		return nextFloat(0L, Float.MAX_VALUE);
	}

	/**
	 * 返回 0 到 bound - 1 范围之间的随机 float
	 *
	 * @param bound
	 * 		bound
	 *
	 * @return 0 到 bound - 1 范围之间的随机 float
	 *
	 * @since 1.2.0
	 */
	public final static float nextFloat(final float bound){
		return nextFloat(0, bound - 1F);
	}

	/**
	 * 返回 start 到 end 范围之间的随机 float
	 *
	 * @param start
	 * 		起始值
	 * @param end
	 * 		结束值
	 *
	 * @return start 到 end 范围之间的随机 float
	 *
	 * @since 1.2.0
	 */
	public final static float nextFloat(final float start, final float end){
		Assert.isTrue(start >= end, "Start value must be smaller or equal to end value.");
		Assert.isTrue(start < 0, "Both range values must be non-negative.");

		if(start == end){
			return start;
		}

		return start + ((end - start) * RANDOM.nextFloat());
	}

	/**
	 * 返回 0 到 Double.MAX_VALUE 之间的随机 double
	 *
	 * @return 随机 double
	 *
	 * @since 1.2.0
	 */
	public final static double nextDouble(){
		return nextDouble(0L, Double.MAX_VALUE);
	}

	/**
	 * 返回 0 到 bound - 1 范围之间的随机 float
	 *
	 * @param bound
	 * 		bound
	 *
	 * @return 0 到 bound - 1 范围之间的随机 float
	 *
	 * @since 1.2.0
	 */
	public final static double nextDouble(final double bound){
		return nextDouble(0, bound - 1);
	}

	/**
	 * 返回 start 到 end 范围之间的随机 float
	 *
	 * @param start
	 * 		起始值
	 * @param end
	 * 		结束值
	 *
	 * @return start 到 end 范围之间的随机 float
	 *
	 * @since 1.2.0
	 */
	public final static double nextDouble(final double start, final double end){
		Assert.isTrue(start >= end, "Start value must be smaller or equal to end value.");
		Assert.isTrue(start < 0, "Both range values must be non-negative.");

		if(start == end){
			return start;
		}

		return start + ((end - start) * RANDOM.nextDouble());
	}

}
