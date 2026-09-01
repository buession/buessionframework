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
 * | Copyright @ 2013-2026 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.core.collect;

import com.buession.core.utils.Assert;
import com.buession.core.utils.StringUtils;
import com.buession.lang.Constants;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

/**
 * 数组工具类
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public class Arrays extends org.apache.commons.lang3.ArrayUtils {

	public final static String DEFAULT_GLUE = ", ";

	/**
	 * 将 byte 型数组拼接成字符串
	 *
	 * @param a
	 * 		需要拼接的数组
	 *
	 * @return 拼接后的字符串
	 */
	public static String toString(final byte[] a) {
		return toString(a, DEFAULT_GLUE);
	}

	/**
	 * 将 byte 型数组拼接成字符串
	 *
	 * @param a
	 * 		需要拼接的数组
	 * @param glue
	 * 		拼接字符串
	 *
	 * @return 拼接后的字符串
	 */
	public static String toString(final byte[] a, final String glue) {
		if(a == null){
			return null;
		}else if(a.length == 0){
			return Constants.EMPTY_STRING;
		}else{
			return StringUtils.join(a, glue);
		}
	}

	/**
	 * 将 char 型数组拼接成字符串
	 *
	 * @param a
	 * 		需要拼接的数组
	 *
	 * @return 拼接后的字符串
	 */
	public static String toString(final char[] a) {
		return toString(a, DEFAULT_GLUE);
	}

	/**
	 * 将 char 型数组拼接成字符串
	 *
	 * @param a
	 * 		需要拼接的数组
	 * @param glue
	 * 		拼接字符串
	 *
	 * @return 拼接后的字符串
	 */
	public static String toString(final char[] a, final String glue) {
		if(a == null){
			return null;
		}else if(a.length == 0){
			return Constants.EMPTY_STRING;
		}else{
			return StringUtils.join(a, glue);
		}
	}

	/**
	 * 将 short 型数组拼接成字符串
	 *
	 * @param a
	 * 		需要拼接的数组
	 *
	 * @return 拼接后的字符串
	 */
	public static String toString(final short[] a) {
		return toString(a, DEFAULT_GLUE);
	}

	/**
	 * 将 short 型数组拼接成字符串
	 *
	 * @param a
	 * 		需要拼接的数组
	 * @param glue
	 * 		拼接字符串
	 *
	 * @return 拼接后的字符串
	 */
	public static String toString(final short[] a, final String glue) {
		if(a == null){
			return null;
		}else if(a.length == 0){
			return Constants.EMPTY_STRING;
		}else{
			return StringUtils.join(a, glue);
		}
	}

	/**
	 * 将 int 型数组拼接成字符串
	 *
	 * @param a
	 * 		需要拼接的数组
	 *
	 * @return 拼接后的字符串
	 */
	public static String toString(final int[] a) {
		return toString(a, DEFAULT_GLUE);
	}

	/**
	 * 将 int 型数组拼接成字符串
	 *
	 * @param a
	 * 		需要拼接的数组
	 * @param glue
	 * 		拼接字符串
	 *
	 * @return 拼接后的字符串
	 */
	public static String toString(final int[] a, final String glue) {
		if(a == null){
			return null;
		}else if(a.length == 0){
			return Constants.EMPTY_STRING;
		}else{
			return StringUtils.join(a, glue);
		}
	}

	/**
	 * 将 long 型数组拼接成字符串
	 *
	 * @param a
	 * 		需要拼接的数组
	 *
	 * @return 拼接后的字符串
	 */
	public static String toString(final long[] a) {
		return toString(a, DEFAULT_GLUE);
	}

	/**
	 * 将 long 型数组拼接成字符串
	 *
	 * @param a
	 * 		需要拼接的数组
	 * @param glue
	 * 		拼接字符串
	 *
	 * @return 拼接后的字符串
	 */
	public static String toString(final long[] a, final String glue) {
		if(a == null){
			return null;
		}else if(a.length == 0){
			return Constants.EMPTY_STRING;
		}else{
			return StringUtils.join(a, glue);
		}
	}

	/**
	 * 将 float 型数组拼接成字符串
	 *
	 * @param a
	 * 		需要拼接的数组
	 *
	 * @return 拼接后的字符串
	 */
	public static String toString(final float[] a) {
		return toString(a, DEFAULT_GLUE);
	}

	/**
	 * 将 float 型数组拼接成字符串
	 *
	 * @param a
	 * 		需要拼接的数组
	 * @param glue
	 * 		拼接字符串
	 *
	 * @return 拼接后的字符串
	 */
	public static String toString(final float[] a, final String glue) {
		if(a == null){
			return null;
		}else if(a.length == 0){
			return Constants.EMPTY_STRING;
		}else{
			return StringUtils.join(a, glue);
		}
	}

	/**
	 * 将 double 型数组拼接成字符串
	 *
	 * @param a
	 * 		需要拼接的数组
	 *
	 * @return 拼接后的字符串
	 */
	public static String toString(final double[] a) {
		return toString(a, DEFAULT_GLUE);
	}

	/**
	 * 将 double 型数组拼接成字符串
	 *
	 * @param a
	 * 		需要拼接的数组
	 * @param glue
	 * 		拼接字符串
	 *
	 * @return 拼接后的字符串
	 */
	public static String toString(final double[] a, final String glue) {
		if(a == null){
			return null;
		}else if(a.length == 0){
			return Constants.EMPTY_STRING;
		}else{
			return StringUtils.join(a, glue);
		}
	}

	/**
	 * 将 boolean 型数组拼接成字符串
	 *
	 * @param a
	 * 		需要拼接的数组
	 *
	 * @return 拼接后的字符串
	 */
	public static String toString(final boolean[] a) {
		return toString(a, DEFAULT_GLUE);
	}

	/**
	 * 将 boolean 型数组拼接成字符串
	 *
	 * @param a
	 * 		需要拼接的数组
	 * @param glue
	 * 		拼接字符串
	 *
	 * @return 拼接后的字符串
	 */
	public static String toString(final boolean[] a, final String glue) {
		if(a == null){
			return null;
		}else if(a.length == 0){
			return Constants.EMPTY_STRING;
		}else{
			return StringUtils.join(a, glue);
		}
	}

	/**
	 * 将 O 型数组拼接成字符串
	 *
	 * @param a
	 * 		需要拼接的数组
	 * @param <O>
	 * 		类
	 *
	 * @return 拼接后的字符串
	 */
	public static <O> String toString(final O[] a) {
		return StringUtils.join(a, DEFAULT_GLUE);
	}

	/**
	 * 将 O 型数组拼接成字符串
	 *
	 * @param a
	 * 		需要拼接的数组
	 * @param glue
	 * 		拼接字符串
	 * @param <O>
	 * 		类
	 *
	 * @return 拼接后的字符串
	 */
	public static <O> String toString(final O[] a, final String glue) {
		return StringUtils.join(a, glue);
	}

	/**
	 * 将 byte 型数组转换为 List
	 *
	 * @param a
	 * 		需要转换的数组
	 *
	 * @return 转换结果
	 */
	public static List<Byte> toList(final byte[] a) {
		if(a == null){
			return null;
		}else{
			List<Byte> result = new ArrayList<>(a.length);

			for(byte v : a){
				result.add(v);
			}

			return result;
		}
	}

	/**
	 * 将 char 型数组转换为 List
	 *
	 * @param a
	 * 		需要转换的数组
	 *
	 * @return 转换结果
	 */
	public static List<Character> toList(final char[] a) {
		if(a == null){
			return null;
		}else{
			List<Character> result = new ArrayList<>(a.length);

			for(char v : a){
				result.add(v);
			}

			return result;
		}
	}

	/**
	 * 将 short 型数组转换为 List
	 *
	 * @param a
	 * 		需要转换的数组
	 *
	 * @return 转换结果
	 */
	public static List<Short> toList(final short[] a) {
		if(a == null){
			return null;
		}else{
			List<Short> result = new ArrayList<>(a.length);

			for(short v : a){
				result.add(v);
			}

			return result;
		}
	}

	/**
	 * 将 int 型数组转换为 List
	 *
	 * @param a
	 * 		需要转换的数组
	 *
	 * @return 转换结果
	 */
	public static List<Integer> toList(final int[] a) {
		if(a == null){
			return null;
		}else{
			List<Integer> result = new ArrayList<>(a.length);

			for(int v : a){
				result.add(v);
			}

			return result;
		}
	}

	/**
	 * 将 long 型数组转换为 List
	 *
	 * @param a
	 * 		需要转换的数组
	 *
	 * @return 转换结果
	 */
	public static List<Long> toList(final long[] a) {
		if(a == null){
			return null;
		}else{
			List<Long> result = new ArrayList<>(a.length);

			for(long v : a){
				result.add(v);
			}

			return result;
		}
	}

	/**
	 * 将 float 型数组转换为 List
	 *
	 * @param a
	 * 		需要转换的数组
	 *
	 * @return 转换结果
	 */
	public static List<Float> toList(final float[] a) {
		if(a == null){
			return null;
		}else{
			List<Float> result = new ArrayList<>(a.length);

			for(float v : a){
				result.add(v);
			}

			return result;
		}
	}

	/**
	 * 将 double 型数组转换为 List
	 *
	 * @param a
	 * 		需要转换的数组
	 *
	 * @return 转换结果
	 */
	public static List<Double> toList(final double[] a) {
		if(a == null){
			return null;
		}else{
			List<Double> result = new ArrayList<>(a.length);

			for(double v : a){
				result.add(v);
			}

			return result;
		}
	}

	/**
	 * 将 boolean 型数组转换为 List
	 *
	 * @param a
	 * 		需要转换的数组
	 *
	 * @return 转换结果
	 */
	public static List<Boolean> toList(final boolean[] a) {
		if(a == null){
			return null;
		}else{
			List<Boolean> result = new ArrayList<>(a.length);

			for(boolean v : a){
				result.add(v);
			}

			return result;
		}
	}

	/**
	 * 将数组转换为 List
	 *
	 * @param a
	 * 		需要转换的数组
	 * @param <O>
	 * 		类
	 *
	 * @return 转换结果
	 */
	public static <O> List<O> toList(final O[] a) {
		if(a == null){
			return null;
		}else{
			return java.util.Arrays.asList(a);
		}
	}

	/**
	 * 将 byte 型数组转换为 Set
	 *
	 * @param a
	 * 		需要转换的数组
	 *
	 * @return 转换结果
	 */
	public static Set<Byte> toSet(final byte[] a) {
		if(a == null){
			return null;
		}else{
			Set<Byte> result = new LinkedHashSet<>(a.length);

			for(byte v : a){
				result.add(v);
			}

			return result;
		}
	}

	/**
	 * 将 char 型数组转换为 Set
	 *
	 * @param a
	 * 		需要转换的数组
	 *
	 * @return 转换结果
	 */
	public static Set<Character> toSet(final char[] a) {
		if(a == null){
			return null;
		}else{
			Set<Character> result = new LinkedHashSet<>(a.length);

			for(char v : a){
				result.add(v);
			}

			return result;
		}
	}

	/**
	 * 将 short 型数组转换为 Set
	 *
	 * @param a
	 * 		需要转换的数组
	 *
	 * @return 转换结果
	 */
	public static Set<Short> toSet(final short[] a) {
		if(a == null){
			return null;
		}else{
			Set<Short> result = new LinkedHashSet<>(a.length);

			for(short v : a){
				result.add(v);
			}

			return result;
		}
	}

	/**
	 * 将 int 型数组转换为 Set
	 *
	 * @param a
	 * 		需要转换的数组
	 *
	 * @return 转换结果
	 */
	public static Set<Integer> toSet(final int[] a) {
		if(a == null){
			return null;
		}else{
			Set<Integer> result = new LinkedHashSet<>(a.length);

			for(int v : a){
				result.add(v);
			}

			return result;
		}
	}

	/**
	 * 将 long 型数组转换为 Set
	 *
	 * @param a
	 * 		需要转换的数组
	 *
	 * @return 转换结果
	 */
	public static Set<Long> toSet(final long[] a) {
		if(a == null){
			return null;
		}else{
			Set<Long> result = new LinkedHashSet<>(a.length);

			for(long v : a){
				result.add(v);
			}

			return result;
		}
	}

	/**
	 * 将 float 型数组转换为 Set
	 *
	 * @param a
	 * 		需要转换的数组
	 *
	 * @return 转换结果
	 */
	public static Set<Float> toSet(final float[] a) {
		if(a == null){
			return null;
		}else{
			Set<Float> result = new LinkedHashSet<>(a.length);

			for(float v : a){
				result.add(v);
			}

			return result;
		}
	}

	/**
	 * 将 double 型数组转换为 Set
	 *
	 * @param a
	 * 		需要转换的数组
	 *
	 * @return 转换结果
	 */
	public static Set<Double> toSet(final double[] a) {
		if(a == null){
			return null;
		}else{
			Set<Double> result = new LinkedHashSet<>(a.length);

			for(double v : a){
				result.add(v);
			}

			return result;
		}
	}

	/**
	 * 将 boolean 型数组转换为 Set
	 *
	 * @param a
	 * 		需要转换的数组
	 *
	 * @return 转换结果
	 */
	public static Set<Boolean> toSet(final boolean[] a) {
		if(a == null){
			return null;
		}else{
			Set<Boolean> result = new LinkedHashSet<>(a.length);

			for(boolean v : a){
				result.add(v);
			}

			return result;
		}
	}

	/**
	 * 将数组转换为 Set
	 *
	 * @param a
	 * 		需要转换的数组
	 * @param <O>
	 * 		类
	 *
	 * @return 转换结果
	 */
	public static <O> Set<O> toSet(final O[] a) {
		if(a == null){
			return null;
		}else{
			Set<O> result = new LinkedHashSet<>(a.length);

			Collections.addAll(result, a);

			return result;
		}
	}

	/**
	 * 将 byte 类型的数组转换成 String 类型的数组
	 *
	 * @param a
	 * 		byte 类型的数组
	 *
	 * @return String 类型的数组
	 */
	public static String[] toStringArray(final byte[] a) {
		return toStringArray(toObject(a));
	}

	/**
	 * 将 char 类型的数组转换成 String 类型的数组
	 *
	 * @param a
	 * 		char 类型的数组
	 *
	 * @return String 类型的数组
	 */
	public static String[] toStringArray(final char[] a) {
		return toStringArray(toObject(a));
	}

	/**
	 * 将 short 类型的数组转换成 String 类型的数组
	 *
	 * @param a
	 * 		short 类型的数组
	 *
	 * @return String 类型的数组
	 */
	public static String[] toStringArray(final short[] a) {
		return toStringArray(toObject(a));
	}

	/**
	 * 将 int 类型的数组转换成 String 类型的数组
	 *
	 * @param a
	 * 		int 类型的数组
	 *
	 * @return String 类型的数组
	 */
	public static String[] toStringArray(final int[] a) {
		return toStringArray(toObject(a));
	}

	/**
	 * 将 long 类型的数组转换成 String 类型的数组
	 *
	 * @param a
	 * 		long 类型的数组
	 *
	 * @return String 类型的数组
	 */
	public static String[] toStringArray(final long[] a) {
		return toStringArray(toObject(a));
	}

	/**
	 * 将 float 类型的数组转换成 String 类型的数组
	 *
	 * @param a
	 * 		float 类型的数组
	 *
	 * @return String 类型的数组
	 */
	public static String[] toStringArray(final float[] a) {
		return toStringArray(toObject(a));
	}

	/**
	 * 将 double 类型的数组转换成 String 类型的数组
	 *
	 * @param a
	 * 		float 类型的数组
	 *
	 * @return String 类型的数组
	 */
	public static String[] toStringArray(final double[] a) {
		return toStringArray(toObject(a));
	}

	/**
	 * 将 boolean 类型的数组转换成 String 类型的数组
	 *
	 * @param a
	 * 		float 类型的数组
	 *
	 * @return String 类型的数组
	 */
	public static String[] toStringArray(final boolean[] a) {
		return toStringArray(toObject(a));
	}

	/**
	 * 数组填充
	 *
	 * @param value
	 * 		重复内容
	 * @param size
	 * 		填充次数
	 *
	 * @return 数组
	 *
	 * @since 2.3.0
	 */
	public static byte[] repeat(final byte value, final int size) {
		Assert.isNegative(size, "Repeat size is negative.");

		byte[] result = new byte[size];

		java.util.Arrays.fill(result, value);

		return result;
	}

	/**
	 * 数组填充
	 *
	 * @param value
	 * 		重复内容
	 * @param size
	 * 		填充次数
	 *
	 * @return 数组
	 *
	 * @since 2.3.0
	 */
	public static char[] repeat(final char value, final int size) {
		Assert.isNegative(size, "Repeat size is negative.");

		char[] result = new char[size];

		java.util.Arrays.fill(result, value);

		return result;
	}

	/**
	 * 数组填充
	 *
	 * @param value
	 * 		重复内容
	 * @param size
	 * 		填充次数
	 *
	 * @return 数组
	 *
	 * @since 2.3.0
	 */
	public static boolean[] repeat(final boolean value, final int size) {
		Assert.isNegative(size, "Repeat size is negative.");

		boolean[] result = new boolean[size];

		java.util.Arrays.fill(result, value);

		return result;
	}

	/**
	 * 数组填充
	 *
	 * @param value
	 * 		重复内容
	 * @param size
	 * 		填充次数
	 *
	 * @return 数组
	 *
	 * @since 2.3.0
	 */
	public static short[] repeat(final short value, final int size) {
		Assert.isNegative(size, "Repeat size is negative.");

		short[] result = new short[size];

		java.util.Arrays.fill(result, value);

		return result;
	}

	/**
	 * 数组填充
	 *
	 * @param value
	 * 		重复内容
	 * @param size
	 * 		填充次数
	 *
	 * @return 数组
	 *
	 * @since 2.3.0
	 */
	public static int[] repeat(final int value, final int size) {
		Assert.isNegative(size, "Repeat size is negative.");

		int[] result = new int[size];

		java.util.Arrays.fill(result, value);

		return result;
	}

	/**
	 * 数组填充
	 *
	 * @param value
	 * 		重复内容
	 * @param size
	 * 		填充次数
	 *
	 * @return 数组
	 *
	 * @since 2.3.0
	 */
	public static long[] repeat(final long value, final int size) {
		Assert.isNegative(size, "Repeat size is negative.");

		long[] result = new long[size];

		java.util.Arrays.fill(result, value);

		return result;
	}

	/**
	 * 数组填充
	 *
	 * @param value
	 * 		重复内容
	 * @param size
	 * 		填充次数
	 *
	 * @return 数组
	 *
	 * @since 2.3.0
	 */
	public static float[] repeat(final float value, final int size) {
		Assert.isNegative(size, "Repeat size is negative.");

		float[] result = new float[size];

		java.util.Arrays.fill(result, value);

		return result;
	}

	/**
	 * 数组填充
	 *
	 * @param value
	 * 		重复内容
	 * @param size
	 * 		填充次数
	 *
	 * @return 数组
	 *
	 * @since 2.3.0
	 */
	public static double[] repeat(final double value, final int size) {
		Assert.isNegative(size, "Repeat size is negative.");

		double[] result = new double[size];

		java.util.Arrays.fill(result, value);

		return result;
	}

	/**
	 * 数组填充
	 *
	 * @param value
	 * 		重复内容
	 * @param size
	 * 		填充次数
	 * @param <T>
	 * 		数据类型
	 *
	 * @return 数组
	 *
	 * @since 2.3.0
	 */
	@SuppressWarnings("unchecked")
	public static <T> T[] repeat(final T value, final int size) {
		Assert.isNegative(size, "Repeat size is negative.");

		T[] result = (T[]) Array.newInstance(value.getClass(), size);

		java.util.Arrays.fill(result, value);

		return result;
	}

	/**
	 * 合并数组
	 *
	 * @param a
	 * 		待合并的数组
	 * @param b
	 * 		待合并的数组
	 *
	 * @return 合并后的数组
	 */
	public static byte[] merge(final byte[] a, final byte[]... b) {
		Assert.isNull(a, "Source array cloud not be null.");

		if(b == null){
			return clone(a);
		}else{
			byte[] result = a;

			for(byte[] item : b){
				result = addAll(result, item);
			}

			return result;
		}
	}

	/**
	 * 合并数组
	 *
	 * @param a
	 * 		待合并的数组
	 * @param b
	 * 		待合并的数组
	 *
	 * @return 合并后的数组
	 */
	public static char[] merge(final char[] a, final char[]... b) {
		Assert.isNull(a, "Source array cloud not be null.");

		if(b == null){
			return clone(a);
		}else{
			char[] result = a;

			for(char[] item : b){
				result = addAll(result, item);
			}

			return result;
		}
	}

	/**
	 * 合并数组
	 *
	 * @param a
	 * 		待合并的数组
	 * @param b
	 * 		待合并的数组
	 *
	 * @return 合并后的数组
	 */
	public static boolean[] merge(final boolean[] a, final boolean[]... b) {
		Assert.isNull(a, "Source array cloud not be null.");

		if(b == null){
			return clone(a);
		}else{
			boolean[] result = a;

			for(boolean[] item : b){
				result = addAll(result, item);
			}

			return result;
		}
	}

	/**
	 * 合并数组
	 *
	 * @param a
	 * 		待合并的数组
	 * @param b
	 * 		待合并的数组
	 *
	 * @return 合并后的数组
	 */
	public static short[] merge(final short[] a, final short[]... b) {
		Assert.isNull(a, "Source array cloud not be null.");

		if(b == null){
			return clone(a);
		}else{
			short[] result = a;

			for(short[] item : b){
				result = addAll(result, item);
			}

			return result;
		}
	}

	/**
	 * 合并数组
	 *
	 * @param a
	 * 		待合并的数组
	 * @param b
	 * 		待合并的数组
	 *
	 * @return 合并后的数组
	 */
	public static int[] merge(final int[] a, final int[]... b) {
		Assert.isNull(a, "Source array cloud not be null.");

		if(b == null){
			return clone(a);
		}else{
			int[] result = a;

			for(int[] item : b){
				result = addAll(result, item);
			}

			return result;
		}
	}

	/**
	 * 合并数组
	 *
	 * @param a
	 * 		待合并的数组
	 * @param b
	 * 		待合并的数组
	 *
	 * @return 合并后的数组
	 */
	public static long[] merge(final long[] a, final long[]... b) {
		Assert.isNull(a, "Source array cloud not be null.");

		if(b == null){
			return clone(a);
		}else{
			long[] result = a;

			for(long[] item : b){
				result = addAll(result, item);
			}

			return result;
		}
	}

	/**
	 * 合并数组
	 *
	 * @param a
	 * 		待合并的数组
	 * @param b
	 * 		待合并的数组
	 *
	 * @return 合并后的数组
	 */
	public static float[] merge(final float[] a, final float[]... b) {
		Assert.isNull(a, "Source array cloud not be null.");

		if(b == null){
			return clone(a);
		}else{
			float[] result = a;

			for(float[] item : b){
				result = addAll(result, item);
			}

			return result;
		}
	}

	/**
	 * 合并数组
	 *
	 * @param a
	 * 		待合并的数组
	 * @param b
	 * 		待合并的数组
	 *
	 * @return 合并后的数组
	 */
	public static double[] merge(final double[] a, final double[]... b) {
		Assert.isNull(a, "Source array cloud not be null.");

		if(b == null){
			return clone(a);
		}else{
			double[] result = a;

			for(double[] item : b){
				result = addAll(result, item);
			}

			return result;
		}
	}

	/**
	 * 合并数组
	 *
	 * @param a
	 * 		待合并的数组
	 * @param b
	 * 		待合并的数组
	 * @param <T>
	 * 		数据对象类型
	 *
	 * @return 合并后的数组
	 */
	@SafeVarargs
	public static <T> T[] merge(final T[] a, final T[]... b) {
		Assert.isNull(a, "Source array cloud not be null.");

		if(b == null){
			return clone(a);
		}else{
			T[] result = a;

			for(T[] item : b){
				result = addAll(result, item);
			}

			return result;
		}
	}

	/**
	 * 对数组的元素进行操作，返回一个新的数组
	 *
	 * @param arrays
	 * 		需操作的数组
	 * @param clazz
	 * 		目标数组类型
	 * @param fn
	 * 		Value 操作函数
	 * @param <S>
	 * 		源数组类型
	 * @param <T>
	 * 		目标数组类型
	 *
	 * @return 新的数组
	 */
	@SuppressWarnings("unchecked")
	public static <S, T> T[] map(final S[] arrays, final Class<T> clazz, final Function<S, T> fn) {
		if(arrays == null){
			return null;
		}else{
			T[] result = (T[]) Array.newInstance(clazz, arrays.length);

			for(int i = 0; i < arrays.length; i++){
				result[i] = fn.apply(arrays[i]);
			}

			return result;
		}
	}

	/**
	 * 对数组的元素进行操作
	 *
	 * @param arrays
	 * 		需操作的数组
	 * @param fn
	 * 		Value 操作函数
	 * @param <T>
	 * 		数组类型
	 *
	 * @return 数组
	 *
	 * @since 4.0.0
	 */
	public static <T> T[] map(final T[] arrays, final Function<T, T> fn) {
		if(arrays == null){
			return null;
		}else{
			for(int i = 0; i < arrays.length; i++){
				arrays[i] = fn.apply(arrays[i]);
			}

			return arrays;
		}
	}

	/**
	 * 将 Float 数组转换为 float 数组
	 *
	 * @param arrays
	 * 		Float 数组
	 *
	 * @return float 数组
	 *
	 * @since 5.0.0
	 */
	public static float[] converter(final Float[] arrays) {
		if(arrays == null){
			return null;
		}else{
			float[] result = new float[arrays.length];

			for(int i = 0; i < arrays.length; i++){
				result[i] = arrays[i];
			}

			return result;
		}
	}

	/**
	 * 将 float 数组转换为 Float 数组
	 *
	 * @param arrays
	 * 		float 数组
	 *
	 * @return Float 数组
	 *
	 * @since 5.0.0
	 */
	public static Float[] converter(final float[] arrays) {
		if(arrays == null){
			return null;
		}else{
			Float[] result = new Float[arrays.length];

			for(int i = 0; i < arrays.length; i++){
				result[i] = arrays[i];
			}

			return result;
		}
	}

	/**
	 * 将 Double 数组转换为 double 数组
	 *
	 * @param arrays
	 * 		Double 数组
	 *
	 * @return double 数组
	 *
	 * @since 5.0.0
	 */
	public static double[] converter(final Double[] arrays) {
		if(arrays == null){
			return null;
		}else{
			double[] result = new double[arrays.length];

			for(int i = 0; i < arrays.length; i++){
				result[i] = arrays[i];
			}

			return result;
		}
	}

	/**
	 * 将 double 数组转换为 Double 数组
	 *
	 * @param arrays
	 * 		double 数组
	 *
	 * @return Double 数组
	 *
	 * @since 5.0.0
	 */
	public static Double[] converter(final double[] arrays) {
		if(arrays == null){
			return null;
		}else{
			Double[] result = new Double[arrays.length];

			for(int i = 0; i < arrays.length; i++){
				result[i] = arrays[i];
			}

			return result;
		}
	}

	/**
	 * 将 Short 数组转换为 short 数组
	 *
	 * @param arrays
	 * 		Short 数组
	 *
	 * @return short 数组
	 *
	 * @since 5.0.0
	 */
	public static short[] converter(final Short[] arrays) {
		if(arrays == null){
			return null;
		}else{
			short[] result = new short[arrays.length];

			for(int i = 0; i < arrays.length; i++){
				result[i] = arrays[i];
			}

			return result;
		}
	}

	/**
	 * 将 short 数组转换为 Short 数组
	 *
	 * @param arrays
	 * 		short 数组
	 *
	 * @return Short 数组
	 *
	 * @since 5.0.0
	 */
	public static Short[] converter(final short[] arrays) {
		if(arrays == null){
			return null;
		}else{
			Short[] result = new Short[arrays.length];

			for(int i = 0; i < arrays.length; i++){
				result[i] = arrays[i];
			}

			return result;
		}
	}

	/**
	 * 将 Integer 数组转换为 int 数组
	 *
	 * @param arrays
	 * 		Integer 数组
	 *
	 * @return int 数组
	 *
	 * @since 5.0.0
	 */
	public static int[] converter(final Integer[] arrays) {
		if(arrays == null){
			return null;
		}else{
			int[] result = new int[arrays.length];

			for(int i = 0; i < arrays.length; i++){
				result[i] = arrays[i];
			}

			return result;
		}
	}

	/**
	 * 将 int 数组转换为 Integer 数组
	 *
	 * @param arrays
	 * 		int 数组
	 *
	 * @return Integer 数组
	 *
	 * @since 5.0.0
	 */
	public static Integer[] converter(final int[] arrays) {
		if(arrays == null){
			return null;
		}else{
			Integer[] result = new Integer[arrays.length];

			for(int i = 0; i < arrays.length; i++){
				result[i] = arrays[i];
			}

			return result;
		}
	}

	/**
	 * 将 Long 数组转换为 long 数组
	 *
	 * @param arrays
	 * 		Long 数组
	 *
	 * @return long 数组
	 *
	 * @since 5.0.0
	 */
	public static long[] converter(final Long[] arrays) {
		if(arrays == null){
			return null;
		}else{
			long[] result = new long[arrays.length];

			for(int i = 0; i < arrays.length; i++){
				result[i] = arrays[i];
			}

			return result;
		}
	}

	/**
	 * 将 long 数组转换为 Long 数组
	 *
	 * @param arrays
	 * 		long 数组
	 *
	 * @return Long 数组
	 *
	 * @since 5.0.0
	 */
	public static Long[] converter(final long[] arrays) {
		if(arrays == null){
			return null;
		}else{
			Long[] result = new Long[arrays.length];

			for(int i = 0; i < arrays.length; i++){
				result[i] = arrays[i];
			}

			return result;
		}
	}

}
