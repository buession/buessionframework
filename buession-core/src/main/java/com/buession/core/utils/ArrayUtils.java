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
 * | Copyright @ 2013-2020 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.core.utils;

import com.buession.lang.Constants;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 数组工具类
 *
 * @author Yong.Teng
 */
public class ArrayUtils {

	public final static String DEFAULT_GLUE = ", ";

	public final static int INDEX_NOT_FOUND = -1;

	/**
	 * 检查数组中是否存在某个值
	 *
	 * @param a
	 * 		待搜索的数组
	 * @param value
	 * 		待搜索的值
	 * @param <O>
	 * 		数组类型
	 *
	 * @return 数组中否存在该值，则返回 true；否则，返回 false
	 */
	public static <O> boolean contains(final O[] a, final Object value){
		return indexOf(a, value) != INDEX_NOT_FOUND;
	}

	/**
	 * 获取指定值在数组中第一次出现处的索引，如果此数组中没有该值，则返回 -1
	 *
	 * @param a
	 * 		待搜索的数组
	 * @param value
	 * 		待搜索的值
	 * @param <O>
	 * 		数组类型
	 *
	 * @return 指定值在数组中第一次出现处的索引，未找到则返回 -1
	 */
	public static <O> int indexOf(final O[] a, final Object value){
		return indexOf(a, value, 0);
	}

	/**
	 * 获取指定值在数组中从 startIndex 位置开始，第一次出现处的索引，如果此数组中没有该值，则返回 -1
	 *
	 * @param a
	 * 		待搜索的数组
	 * @param value
	 * 		待搜索的值
	 * @param startIndex
	 * 		开始位置
	 * @param <O>
	 * 		数组类型
	 *
	 * @return 指定值在数组中第一次出现处的索引，未找到则返回 -1
	 */
	public static <O> int indexOf(final O[] a, final Object value, int startIndex){
		if(a == null){
			return INDEX_NOT_FOUND;
		}

		if(startIndex < 0){
			startIndex = 0;
		}

		if(value == null){
			for(int i = startIndex; i < a.length; i++){
				if(a[i] == null){
					return i;
				}
			}
		}else{
			for(int i = startIndex; i < a.length; i++){
				if(value.equals(a[i])){
					return i;
				}
			}
		}

		return INDEX_NOT_FOUND;
	}

	/**
	 * 获取指定值在数组中最后一次出现处的索引，如果此数组中没有该值，则返回 -1
	 *
	 * @param a
	 * 		待搜索的数组
	 * @param value
	 * 		待搜索的值
	 * @param <O>
	 * 		数组类型
	 *
	 * @return 指定值在数组中最后一次出现处的索引，未找到则返回 -1
	 */
	public static <O> int lastIndexOf(final O[] a, final Object value){
		return lastIndexOf(a, value, 0);
	}

	/**
	 * 获取指定值在数组中从 startIndex 位置开始，最后一次出现处的索引，如果此数组中没有该值，则返回 -1
	 *
	 * @param a
	 * 		待搜索的数组
	 * @param value
	 * 		待搜索的值
	 * @param startIndex
	 * 		开始位置
	 * @param <O>
	 * 		数组类型
	 *
	 * @return 指定值在数组中最后一次出现处的索引，未找到则返回 -1
	 */
	public static <O> int lastIndexOf(final O[] a, final Object value, int startIndex){
		if(a == null){
			return INDEX_NOT_FOUND;
		}

		if(startIndex < 0){
			startIndex = 0;
		}

		if(value == null){
			for(int i = a.length - 1; i >= startIndex; i--){
				if(a[i] == null){
					return i;
				}
			}
		}else{
			for(int i = a.length - 1; i >= startIndex; i--){
				if(value.equals(a[i])){
					return i;
				}
			}
		}

		return INDEX_NOT_FOUND;
	}

	/**
	 * 将 byte 型数组拼接成字符串
	 *
	 * @param a
	 * 		需要拼接的数组
	 *
	 * @return 拼接后的字符串
	 */
	public static String toString(final byte[] a){
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
	public static String toString(final byte[] a, final String glue){
		if(a == null){
			return null;
		}else if(a.length == 0){
			return Constants.EMPTY_STRING;
		}else{
			StringBuilder sb = new StringBuilder();

			for(int i = 0; i < a.length; i++){
				sb.append(a[i]);
				if(i < a.length - 1){
					sb.append(glue);
				}
			}

			return sb.toString();
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
	public static String toString(final char[] a){
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
	public static String toString(final char[] a, final String glue){
		if(a == null){
			return null;
		}else if(a.length == 0){
			return Constants.EMPTY_STRING;
		}else{
			StringBuilder sb = new StringBuilder();

			for(int i = 0; i < a.length; i++){
				sb.append(a[i]);
				if(i < a.length - 1){
					sb.append(glue);
				}
			}

			return sb.toString();
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
	public static String toString(final short[] a){
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
	public static String toString(final short[] a, final String glue){
		if(a == null){
			return null;
		}else if(a.length == 0){
			return Constants.EMPTY_STRING;
		}else{
			StringBuilder sb = new StringBuilder();

			for(int i = 0; i < a.length; i++){
				sb.append(a[i]);
				if(i < a.length - 1){
					sb.append(glue);
				}
			}

			return sb.toString();
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
	public static String toString(final int[] a){
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
	public static String toString(final int[] a, final String glue){
		if(a == null){
			return null;
		}else if(a.length == 0){
			return Constants.EMPTY_STRING;
		}else{
			StringBuilder sb = new StringBuilder();

			for(int i = 0; i < a.length; i++){
				sb.append(a[i]);
				if(i < a.length - 1){
					sb.append(glue);
				}
			}

			return sb.toString();
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
	public static String toString(final long[] a){
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
	public static String toString(final long[] a, final String glue){
		if(a == null){
			return null;
		}else if(a.length == 0){
			return Constants.EMPTY_STRING;
		}else{
			StringBuilder sb = new StringBuilder();

			for(int i = 0; i < a.length; i++){
				sb.append(a[i]);
				if(i < a.length - 1){
					sb.append(glue);
				}
			}

			return sb.toString();
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
	public static String toString(final float[] a){
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
	public static String toString(final float[] a, final String glue){
		if(a == null){
			return null;
		}else if(a.length == 0){
			return Constants.EMPTY_STRING;
		}else{
			StringBuilder sb = new StringBuilder();

			for(int i = 0; i < a.length; i++){
				sb.append(a[i]);
				if(i < a.length - 1){
					sb.append(glue);
				}
			}

			return sb.toString();
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
	public static String toString(final double[] a){
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
	public static String toString(final double[] a, final String glue){
		if(a == null){
			return null;
		}else if(a.length == 0){
			return Constants.EMPTY_STRING;
		}else{
			StringBuilder sb = new StringBuilder();

			for(int i = 0; i < a.length; i++){
				sb.append(a[i]);
				if(i < a.length - 1){
					sb.append(glue);
				}
			}

			return sb.toString();
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
	public static String toString(final boolean[] a){
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
	public static String toString(final boolean[] a, final String glue){
		if(a == null){
			return null;
		}else if(a.length == 0){
			return Constants.EMPTY_STRING;
		}else{
			StringBuilder sb = new StringBuilder();

			for(int i = 0; i < a.length; i++){
				sb.append(a[i]);
				if(i < a.length - 1){
					sb.append(glue);
				}
			}

			return sb.toString();
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
	public static <O> String toString(final O[] a){
		return toString(a, DEFAULT_GLUE);
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
	public static <O> String toString(final O[] a, final String glue){
		if(a == null){
			return null;
		}

		return a.length == 0 ? Constants.EMPTY_STRING :
				Arrays.asList(a).stream().map(v->v.toString()).collect(Collectors.joining(glue));
	}

	/**
	 * 将 O 型 List 拼接成字符串
	 *
	 * @param data
	 * 		需要拼接的 List
	 * @param <O>
	 * 		类
	 *
	 * @return 拼接后的字符串
	 */
	public static <O> String toString(final List<O> data){
		return toString(data, DEFAULT_GLUE);
	}

	/**
	 * 将 O 型 List 拼接成字符串
	 *
	 * @param data
	 * 		需要拼接的 List
	 * @param glue
	 * 		拼接字符串
	 * @param <O>
	 * 		类
	 *
	 * @return 拼接后的字符串
	 */
	public static <O> String toString(final List<O> data, final String glue){
		if(data == null){
			return null;
		}

		return data.size() == 0 ? Constants.EMPTY_STRING :
				data.stream().map(v->v.toString()).collect(Collectors.joining(glue));
	}

	/**
	 * 将 O 型 Set 拼接成字符串
	 *
	 * @param data
	 * 		需要拼接的 Set
	 * @param <O>
	 * 		类
	 *
	 * @return 拼接后的字符串
	 */
	public static <O> String toString(final Set<O> data){
		return toString(data, DEFAULT_GLUE);
	}

	/**
	 * 将 O 型 Set 拼接成字符串
	 *
	 * @param data
	 * 		需要拼接的 Set
	 * @param glue
	 * 		拼接字符串
	 * @param <O>
	 * 		类
	 *
	 * @return 拼接后的字符串
	 */
	public static <O> String toString(final Set<O> data, final String glue){
		if(data == null){
			return null;
		}

		return data.size() == 0 ? Constants.EMPTY_STRING :
				data.stream().map(v->v.toString()).collect(Collectors.joining(glue));
	}

	/**
	 * 将 byte 型数组转换为 List
	 *
	 * @param a
	 * 		需要转换的数组
	 *
	 * @return 转换结果
	 */
	public static List<Byte> toList(final byte[] a){
		if(a == null){
			return null;
		}

		Byte[] temp = new Byte[a.length];
		for(int i = 0; i < a.length; i++){
			temp[i] = a[i];
		}

		return Arrays.asList(temp);
	}

	/**
	 * 将 char 型数组转换为 List
	 *
	 * @param a
	 * 		需要转换的数组
	 *
	 * @return 转换结果
	 */
	public static List<Character> toList(final char[] a){
		if(a == null){
			return null;
		}

		Character[] temp = new Character[a.length];
		for(int i = 0; i < a.length; i++){
			temp[i] = a[i];
		}

		return Arrays.asList(temp);
	}

	/**
	 * 将 short 型数组转换为 List
	 *
	 * @param a
	 * 		需要转换的数组
	 *
	 * @return 转换结果
	 */
	public static List<Short> toList(final short[] a){
		if(a == null){
			return null;
		}

		Short[] temp = new Short[a.length];
		for(int i = 0; i < a.length; i++){
			temp[i] = a[i];
		}

		return Arrays.asList(temp);
	}

	/**
	 * 将 int 型数组转换为 List
	 *
	 * @param a
	 * 		需要转换的数组
	 *
	 * @return 转换结果
	 */
	public static List<Integer> toList(final int[] a){
		return a == null ? null : Arrays.stream(a).boxed().collect(Collectors.toList());
	}

	/**
	 * 将 long 型数组转换为 List
	 *
	 * @param a
	 * 		需要转换的数组
	 *
	 * @return 转换结果
	 */
	public static List<Long> toList(final long[] a){
		return a == null ? null : Arrays.stream(a).boxed().collect(Collectors.toList());
	}

	/**
	 * 将 float 型数组转换为 List
	 *
	 * @param a
	 * 		需要转换的数组
	 *
	 * @return 转换结果
	 */
	public static List<Float> toList(final float[] a){
		if(a == null){
			return null;
		}

		Float[] temp = new Float[a.length];
		for(int i = 0; i < a.length; i++){
			temp[i] = a[i];
		}

		return Arrays.asList(temp);
	}

	/**
	 * 将 double 型数组转换为 List
	 *
	 * @param a
	 * 		需要转换的数组
	 *
	 * @return 转换结果
	 */
	public static List<Double> toList(final double[] a){
		return a == null ? null : Arrays.stream(a).boxed().collect(Collectors.toList());
	}

	/**
	 * 将 boolean 型数组转换为 List
	 *
	 * @param a
	 * 		需要转换的数组
	 *
	 * @return 转换结果
	 */
	public static List<Boolean> toList(final boolean[] a){
		if(a == null){
			return null;
		}

		Boolean[] temp = new Boolean[a.length];
		for(int i = 0; i < a.length; i++){
			temp[i] = a[i];
		}

		return Arrays.asList(temp);
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
	public final static <O> List<O> toList(final O[] a){
		return a == null ? null : Arrays.asList(a);
	}

	/**
	 * 将 byte 型数组转换为 Set
	 *
	 * @param a
	 * 		需要转换的数组
	 *
	 * @return 转换结果
	 */
	public static Set<Byte> toSet(final byte[] a){
		return a == null ? null : new LinkedHashSet<>(toList(a));
	}

	/**
	 * 将 char 型数组转换为 Set
	 *
	 * @param a
	 * 		需要转换的数组
	 *
	 * @return 转换结果
	 */
	public static Set<Character> toSet(final char[] a){
		return a == null ? null : new LinkedHashSet<>(toList(a));
	}

	/**
	 * 将 short 型数组转换为 Set
	 *
	 * @param a
	 * 		需要转换的数组
	 *
	 * @return 转换结果
	 */
	public static Set<Short> toSet(final short[] a){
		return a == null ? null : new LinkedHashSet<>(toList(a));
	}

	/**
	 * 将 int 型数组转换为 Set
	 *
	 * @param a
	 * 		需要转换的数组
	 *
	 * @return 转换结果
	 */
	public static Set<Integer> toSet(final int[] a){
		return a == null ? null : new LinkedHashSet<>(toList(a));
	}

	/**
	 * 将 long 型数组转换为 Set
	 *
	 * @param a
	 * 		需要转换的数组
	 *
	 * @return 转换结果
	 */
	public static Set<Long> toSet(final long[] a){
		return a == null ? null : new LinkedHashSet<>(toList(a));
	}

	/**
	 * 将 float 型数组转换为 Set
	 *
	 * @param a
	 * 		需要转换的数组
	 *
	 * @return 转换结果
	 */
	public static Set<Float> toSet(final float[] a){
		return a == null ? null : new LinkedHashSet<>(toList(a));
	}

	/**
	 * 将 double 型数组转换为 Set
	 *
	 * @param a
	 * 		需要转换的数组
	 *
	 * @return 转换结果
	 */
	public static Set<Double> toSet(final double[] a){
		return a == null ? null : new LinkedHashSet<>(toList(a));
	}

	/**
	 * 将 boolean 型数组转换为 Set
	 *
	 * @param a
	 * 		需要转换的数组
	 *
	 * @return 转换结果
	 */
	public static Set<Boolean> toSet(final boolean[] a){
		return a == null ? null : new LinkedHashSet<>(toList(a));
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
	public final static <O> Set<O> toSet(final O[] a){
		return a == null ? null : Arrays.asList(a).stream().collect(Collectors.toCollection(LinkedHashSet::new));
	}

	/**
	 * 将 short 类型的数组转换成 String 类型的数组
	 *
	 * @param data
	 * 		short 类型的数组
	 *
	 * @return String 类型的数组
	 */
	public final static String[] toStringArray(short[] data){
		if(data == null){
			return null;
		}

		String[] result = new String[data.length];

		for(int i = 0; i < data.length; i++){
			result[i] = Short.toString(data[i]);
		}

		return result;
	}

	/**
	 * 将 int 类型的数组转换成 String 类型的数组
	 *
	 * @param data
	 * 		int 类型的数组
	 *
	 * @return String 类型的数组
	 */
	public final static String[] toStringArray(int[] data){
		if(data == null){
			return null;
		}

		String[] result = new String[data.length];

		for(int i = 0; i < data.length; i++){
			result[i] = Integer.toString(data[i]);
		}

		return result;
	}

	/**
	 * 将 long 类型的数组转换成 String 类型的数组
	 *
	 * @param data
	 * 		long 类型的数组
	 *
	 * @return String 类型的数组
	 */
	public final static String[] toStringArray(long[] data){
		if(data == null){
			return null;
		}

		String[] result = new String[data.length];

		for(int i = 0; i < data.length; i++){
			result[i] = Long.toString(data[i]);
		}

		return result;
	}

	/**
	 * 将 float 类型的数组转换成 String 类型的数组
	 *
	 * @param data
	 * 		float 类型的数组
	 *
	 * @return String 类型的数组
	 */
	public final static String[] toStringArray(float[] data){
		if(data == null){
			return null;
		}

		String[] result = new String[data.length];

		for(int i = 0; i < data.length; i++){
			result[i] = Float.toString(data[i]);
		}

		return result;
	}

	/**
	 * 将 double 类型的数组转换成 String 类型的数组
	 *
	 * @param data
	 * 		float 类型的数组
	 *
	 * @return String 类型的数组
	 */
	public final static String[] toStringArray(double[] data){
		if(data == null){
			return null;
		}

		String[] result = new String[data.length];

		for(int i = 0; i < data.length; i++){
			result[i] = Double.toString(data[i]);
		}

		return result;
	}

	/**
	 * 将 double 类型的数组转换成 String 类型的数组
	 *
	 * @param data
	 * 		float 类型的数组
	 *
	 * @return String 类型的数组
	 */
	public final static String[] toStringArray(boolean[] data){
		if(data == null){
			return null;
		}

		String[] result = new String[data.length];

		for(int i = 0; i < data.length; i++){
			result[i] = Boolean.toString(data[i]);
		}

		return result;
	}

	/**
	 * 将 O 类型的数组转换成 String 类型的数组
	 *
	 * @param data
	 * 		O 类型的数组
	 * @param <O>
	 * 		类型
	 *
	 * @return String 类型的数组
	 */
	public final static <O> String[] toStringArray(O[] data){
		if(data == null){
			return null;
		}

		String[] result = new String[data.length];

		for(int i = 0; i < data.length; i++){
			result[i] = data[i] == null ? null : String.valueOf(data[i]);
		}

		return result;
	}

}