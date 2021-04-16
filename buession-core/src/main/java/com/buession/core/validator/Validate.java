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
package com.buession.core.validator;

import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;

import com.buession.core.validator.routines.EmailValidator;
import com.buession.core.validator.routines.IDCardValidator;
import com.buession.core.validator.routines.ISBNValidator;
import com.buession.core.validator.routines.IpValidator;
import com.buession.core.validator.routines.MobileValidator;
import com.buession.core.validator.routines.PostCodeValidator;
import com.buession.core.validator.routines.QQValidator;
import com.buession.core.validator.routines.TelValidator;
import com.buession.lang.IpType;

/**
 * 数据验证类
 *
 * @author Yong.Teng
 */
public class Validate {

	private Validate(){

	}

	/**
	 * 验证是否为 NULL
	 *
	 * @param var
	 * 		待验证的对象
	 *
	 * @return 为 NULL 时，返回 TRUE；否则，返回 FALSE
	 */
	public final static boolean isNull(final Object var){
		return var == null;
	}

	/**
	 * 验证是否不为 NULL
	 *
	 * @param var
	 * 		待验证的对象
	 *
	 * @return 不为 NULL 时，返回 TRUE；否则，返回 FALSE
	 */
	public final static boolean isNotNull(final Object var){
		return var != null;
	}

	/**
	 * 验证一个有序字符集合是否为空
	 *
	 * @param charSequence
	 * 		待验证的有序字符集合
	 *
	 * @return 为 NULL 或长度为 0 时，返回 TRUE；否则，返回 FALSE
	 */
	public final static boolean isEmpty(final CharSequence charSequence){
		return charSequence == null || charSequence.length() == 0;
	}

	/**
	 * 验证数组是否为空
	 *
	 * @param array
	 * 		待验证数组
	 *
	 * @return 为 NULL 或长度为 0 时，返回 TRUE；否则，返回 FALSE
	 */
	public final static boolean isEmpty(final float[] array){
		return array == null || array.length == 0;
	}

	/**
	 * 验证数组是否为空
	 *
	 * @param array
	 * 		待验证数组
	 *
	 * @return 为 NULL 或长度为 0 时，返回 TRUE；否则，返回 FALSE
	 */
	public final static boolean isEmpty(final double[] array){
		return array == null || array.length == 0;
	}

	/**
	 * 验证数组是否为空
	 *
	 * @param array
	 * 		待验证数组
	 *
	 * @return 为 NULL 或长度为 0 时，返回 TRUE；否则，返回 FALSE
	 */
	public final static boolean isEmpty(final short[] array){
		return array == null || array.length == 0;
	}

	/**
	 * 验证数组是否为空
	 *
	 * @param array
	 * 		待验证数组
	 *
	 * @return 为 NULL 或长度为 0 时，返回 TRUE；否则，返回 FALSE
	 */
	public final static boolean isEmpty(final int[] array){
		return array == null || array.length == 0;
	}

	/**
	 * 验证数组是否为空
	 *
	 * @param array
	 * 		待验证数组
	 *
	 * @return 为 NULL 或长度为 0 时，返回 TRUE；否则，返回 FALSE
	 */
	public final static boolean isEmpty(final long[] array){
		return array == null || array.length == 0;
	}

	/**
	 * 验证数组是否为空
	 *
	 * @param array
	 * 		待验证数组
	 *
	 * @return 为 NULL 或长度为 0 时，返回 TRUE；否则，返回 FALSE
	 */
	public final static boolean isEmpty(final byte[] array){
		return array == null || array.length == 0;
	}

	/**
	 * 验证数组是否为空
	 *
	 * @param array
	 * 		待验证数组
	 *
	 * @return 为 NULL 或长度为 0 时，返回 TRUE；否则，返回 FALSE
	 */
	public final static boolean isEmpty(final char[] array){
		return array == null || array.length == 0;
	}

	/**
	 * 验证数组是否为空
	 *
	 * @param array
	 * 		待验证数组
	 *
	 * @return 为 NULL 或长度为 0 时，返回 TRUE；否则，返回 FALSE
	 */
	public final static boolean isEmpty(final boolean[] array){
		return array == null || array.length == 0;
	}

	/**
	 * 验证数组是否为空
	 *
	 * @param array
	 * 		待验证数组
	 * @param <O>
	 * 		类
	 *
	 * @return 为 NULL 或长度为 0 时，返回 TRUE；否则，返回 FALSE
	 */
	public final static <O> boolean isEmpty(final O[] array){
		return array == null || array.length == 0;
	}

	/**
	 * 验证一个容器是否为空
	 *
	 * @param collection
	 * 		待验证的容器
	 *
	 * @return 为 NULL 或不含有元素时，返回 TRUE；否则，返回 FALSE
	 */
	public final static boolean isEmpty(final Collection<?> collection){
		return collection == null || collection.isEmpty();
	}

	/**
	 * 验证一个 Map 是否为空
	 *
	 * @param map
	 * 		待验证的容器
	 *
	 * @return 为 NULL 或不含有元素时，返回 TRUE；否则，返回 FALSE
	 */
	public final static boolean isEmpty(final Map<?, ?> map){
		return map == null || map.isEmpty();
	}

	/**
	 * 验证一个迭代器是否为空
	 *
	 * @param iterator
	 * 		待验证的迭代器
	 *
	 * @return 为 NULL 或不含有元素时，返回 TRUE；否则，返回 FALSE
	 */
	public final static boolean isEmpty(final Iterator<?> iterator){
		return iterator == null || iterator.hasNext() == false;
	}

	/**
	 * 验证一个 Enumeration 是否为空
	 *
	 * @param enumeration
	 * 		待验证的迭代器
	 *
	 * @return 为 NULL 或不含有元素时，返回 TRUE；否则，返回 FALSE
	 */
	public final static boolean isEmpty(final Enumeration<?> enumeration){
		return enumeration == null || enumeration.hasMoreElements() == false;
	}

	/**
	 * 验证一个有序字符集合是否不为空
	 *
	 * @param charSequence
	 * 		待验证的有序字符集合
	 *
	 * @return 不为 NULL 且长度大于 0 时，返回 TRUE；否则，返回 FALSE
	 *
	 * @since 1.2.0
	 */
	public final static boolean isNotEmpty(final CharSequence charSequence){
		return charSequence != null && charSequence.length() > 0;
	}

	/**
	 * 验证数组是否不为空
	 *
	 * @param array
	 * 		待验证数组
	 *
	 * @return 不为 NULL 且长度大于 0 时，返回 TRUE；否则，返回 FALSE
	 *
	 * @since 1.2.0
	 */
	public final static boolean isNotEmpty(final float[] array){
		return array != null && array.length > 0;
	}

	/**
	 * 验证数组是否不为空
	 *
	 * @param array
	 * 		待验证数组
	 *
	 * @return 不为 NULL 且长度大于 0 时，返回 TRUE；否则，返回 FALSE
	 *
	 * @since 1.2.0
	 */
	public final static boolean isNotEmpty(final double[] array){
		return array != null && array.length > 0;
	}

	/**
	 * 验证数组是否不为空
	 *
	 * @param array
	 * 		待验证数组
	 *
	 * @return 不为 NULL 且长度大于 0 时，返回 TRUE；否则，返回 FALSE
	 *
	 * @since 1.2.0
	 */
	public final static boolean isNotEmpty(final short[] array){
		return array != null && array.length > 0;
	}

	/**
	 * 验证数组是否不为空
	 *
	 * @param array
	 * 		待验证数组
	 *
	 * @return 不为 NULL 且长度大于 0 时，返回 TRUE；否则，返回 FALSE
	 *
	 * @since 1.2.0
	 */
	public final static boolean isNotEmpty(final int[] array){
		return array != null && array.length > 0;
	}

	/**
	 * 验证数组是否不为空
	 *
	 * @param array
	 * 		待验证数组
	 *
	 * @return 不为 NULL 且长度大于 0 时，返回 TRUE；否则，返回 FALSE
	 *
	 * @since 1.2.0
	 */
	public final static boolean isNotEmpty(final long[] array){
		return array != null && array.length > 0;
	}

	/**
	 * 验证数组是否不为空
	 *
	 * @param array
	 * 		待验证数组
	 *
	 * @return 不为 NULL 且长度大于 0 时，返回 TRUE；否则，返回 FALSE
	 *
	 * @since 1.2.0
	 */
	public final static boolean isNotEmpty(final byte[] array){
		return array != null && array.length > 0;
	}

	/**
	 * 验证数组是否不为空
	 *
	 * @param array
	 * 		待验证数组
	 *
	 * @return 不为 NULL 且长度大于 0 时，返回 TRUE；否则，返回 FALSE
	 *
	 * @since 1.2.0
	 */
	public final static boolean isNotEmpty(final char[] array){
		return array != null && array.length > 0;
	}

	/**
	 * 验证数组是否不为空
	 *
	 * @param array
	 * 		待验证数组
	 *
	 * @return 不为 NULL 且长度大于 0 时，返回 TRUE；否则，返回 FALSE
	 *
	 * @since 1.2.0
	 */
	public final static boolean isNotEmpty(final boolean[] array){
		return array != null && array.length > 0;
	}

	/**
	 * 验证数组是否不为空
	 *
	 * @param array
	 * 		待验证数组
	 * @param <O>
	 * 		数组类型
	 *
	 * @return 不为 NULL 且长度大于 0 时，返回 TRUE；否则，返回 FALSE
	 *
	 * @since 1.2.0
	 */
	public final static <O> boolean isNotEmpty(final O[] array){
		return array != null && array.length > 0;
	}

	/**
	 * 验证一个容器是否不为空
	 *
	 * @param collection
	 * 		待验证的容器
	 *
	 * @return 不为 NULL 且含有元素时，返回 TRUE；否则，返回 FALSE
	 *
	 * @since 1.2.0
	 */
	public final static boolean isNotEmpty(final Collection<?> collection){
		return collection != null && collection.isEmpty() == false;
	}

	/**
	 * 验证一个 Map 是否不为空
	 *
	 * @param map
	 * 		待验证的容器
	 *
	 * @return 不为 NULL 且含有元素时，返回 TRUE；否则，返回 FALSE
	 *
	 * @since 1.2.0
	 */
	public final static boolean isNotEmpty(final Map<?, ?> map){
		return map != null && map.isEmpty() == false;
	}

	/**
	 * 验证一个迭代器是否不为空
	 *
	 * @param iterator
	 * 		待验证的迭代器
	 *
	 * @return 不为 NULL 且含有元素时，返回 TRUE；否则，返回 FALSE
	 *
	 * @since 1.2.0
	 */
	public final static boolean isNotEmpty(final Iterator<?> iterator){
		return iterator != null && iterator.hasNext();
	}

	/**
	 * 验证一个 Enumeration 是否不为空
	 *
	 * @param enumeration
	 * 		待验证的迭代器
	 *
	 * @return 不为 NULL 且含有元素时，返回 TRUE；否则，返回 FALSE
	 *
	 * @since 1.2.0
	 */
	public final static boolean isNotEmpty(final Enumeration<?> enumeration){
		return enumeration != null && enumeration.hasMoreElements();
	}

	/**
	 * 验证一个有序字符集合是否含有打印字符
	 *
	 * @param charSequence
	 * 		待验证的有序字符集合
	 *
	 * @return 为 NULL 或长度为 0 时，返回 TRUE；否则，返回 FALSE
	 */
	public final static boolean hasText(final CharSequence charSequence){
		if(isEmpty(charSequence) == false){
			for(int i = 0, j = charSequence.length(); i < j; i++){
				if(Character.isWhitespace(charSequence.charAt(i)) == false){
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * 验证一个 long 型数值，是否在两个值之间
	 *
	 * @param value
	 * 		待验证的数值
	 * @param min
	 * 		最小值
	 * @param max
	 * 		最大值
	 *
	 * @return 在两个值之间时，返回 TRUE；否则，返回 FALSE
	 */
	public final static boolean isBetween(final long value, final long min, final long max){
		return isBetween(value, min, max, false);
	}

	/**
	 * 验证一个 long 型数值，是否在两个值之间
	 *
	 * @param value
	 * 		待验证的数值
	 * @param min
	 * 		最小值
	 * @param max
	 * 		最大值
	 * @param isContain
	 * 		是否包含最小值或最大值
	 *
	 * @return 在两个值之间时，返回 TRUE；否则，返回 FALSE
	 */
	public final static boolean isBetween(final long value, final long min, final long max, final boolean isContain){
		return isContain ? (value >= min && value <= max) : (value > min && value < max);
	}

	/**
	 * 验证一个 int 型数值，是否在两个值之间
	 *
	 * @param value
	 * 		待验证的数值
	 * @param min
	 * 		最小值
	 * @param max
	 * 		最大值
	 *
	 * @return 在两个值之间时，返回 TRUE；否则，返回 FALSE
	 */
	public final static boolean isBetween(final int value, final int min, final int max){
		return isBetween(value, min, max, false);
	}

	/**
	 * 验证一个 int 型数值，是否在两个值之间
	 *
	 * @param value
	 * 		待验证的数值
	 * @param min
	 * 		最小值
	 * @param max
	 * 		最大值
	 * @param isContain
	 * 		是否包含最小值或最大值
	 *
	 * @return 在两个值之间时，返回 TRUE；否则，返回 FALSE
	 */
	public final static boolean isBetween(final int value, final int min, final int max, final boolean isContain){
		return isContain ? (value >= min && value <= max) : (value > min && value < max);
	}

	/**
	 * 验证一个 double 型数值，是否在两个值之间
	 *
	 * @param value
	 * 		待验证的数值
	 * @param min
	 * 		最小值
	 * @param max
	 * 		最大值
	 *
	 * @return 在两个值之间时，返回 TRUE；否则，返回 FALSE
	 */
	public final static boolean isBetween(final double value, final double min, final double max){
		return isBetween(value, min, max, false);
	}

	/**
	 * 验证一个 double 型数值，是否在两个值之间
	 *
	 * @param value
	 * 		待验证的数值
	 * @param min
	 * 		最小值
	 * @param max
	 * 		最大值
	 * @param isContain
	 * 		是否包含最小值或最大值
	 *
	 * @return 在两个值之间时，返回 TRUE；否则，返回 FALSE
	 */
	public final static boolean isBetween(final double value, final double min, final double max,
										  final boolean isContain){
		return isContain ? (value >= min && value <= max) : (value > min && value < max);
	}

	/**
	 * 验证一个 float 型数值，是否在两个值之间
	 *
	 * @param value
	 * 		待验证的数值
	 * @param min
	 * 		最小值
	 * @param max
	 * 		最大值
	 *
	 * @return 在两个值之间时，返回 TRUE；否则，返回 FALSE
	 */
	public final static boolean isBetween(final float value, final float min, final float max){
		return isBetween(value, min, max, false);
	}

	/**
	 * 验证一个 float 型数值，是否在两个值之间
	 *
	 * @param value
	 * 		待验证的数值
	 * @param min
	 * 		最小值
	 * @param max
	 * 		最大值
	 * @param isContain
	 * 		是否包含最小值或最大值
	 *
	 * @return 在两个值之间时，返回 TRUE；否则，返回 FALSE
	 */
	public final static boolean isBetween(final float value, final float min, final float max,
										  final boolean isContain){
		return isContain ? (value >= min && value <= max) : (value > min && value < max);
	}

	/**
	 * 验证是否为英文字符串
	 *
	 * @param charSequence
	 * 		待验证的字符串
	 *
	 * @return Boolean
	 */
	public final static boolean isAlpha(final CharSequence charSequence){
		if(charSequence == null || charSequence.length() == 0){
			return false;
		}

		for(int i = 0, j = charSequence.length(); i < j; i++){
			if(isAlpha(charSequence.charAt(i)) == false){
				return false;
			}
		}

		return true;
	}

	/**
	 * 验证是否为英文字符
	 *
	 * @param ch
	 * 		待验证的字符
	 *
	 * @return Boolean
	 */
	public final static boolean isAlpha(final char ch){
		return (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z');
	}

	/**
	 * 验证是否为数字字符串
	 *
	 * @param charSequence
	 * 		待验证的字符串
	 *
	 * @return Boolean
	 */
	public final static boolean isNumeric(final CharSequence charSequence){
		if(charSequence == null || charSequence.length() == 0){
			return false;
		}

		for(int i = 0, j = charSequence.length(); i < j; i++){
			if(isNumeric(charSequence.charAt(i)) == false){
				return false;
			}
		}

		return true;
	}

	/**
	 * 验证是否为数字字符
	 *
	 * @param ch
	 * 		待验证的字符
	 *
	 * @return Boolean
	 */
	public final static boolean isNumeric(final char ch){
		return ch >= '0' && ch <= '9';
	}

	/**
	 * 验证是否为数字字符串
	 *
	 * @param charSequence
	 * 		待验证的字符串
	 *
	 * @return Boolean
	 */
	public final static boolean isDigit(final CharSequence charSequence){
		return isNumeric(charSequence);
	}

	/**
	 * 验证是否为数字字符
	 *
	 * @param ch
	 * 		待验证的字符
	 *
	 * @return Boolean
	 */
	public final static boolean isDigit(final char ch){
		return isNumeric(ch);
	}

	/**
	 * 验证是否为英文或数字字符串
	 *
	 * @param charSequence
	 * 		待验证的字符串
	 *
	 * @return Boolean
	 */
	public final static boolean isAlnum(final CharSequence charSequence){
		if(charSequence == null || charSequence.length() == 0){
			return false;
		}

		for(int i = 0, j = charSequence.length(); i < j; i++){
			if(isAlnum(charSequence.charAt(i)) == false){
				return false;
			}
		}

		return true;
	}

	/**
	 * 验证是否为英文或数字字符
	 *
	 * @param ch
	 * 		待验证的字符
	 *
	 * @return Boolean
	 */
	public final static boolean isAlnum(final char ch){
		return isAlpha(ch) || isNumeric(ch);
	}

	/**
	 * 验证是否为 16 进制字符串
	 *
	 * @param charSequence
	 * 		待验证的字符串
	 *
	 * @return Boolean
	 */
	public final static boolean isXdigit(final CharSequence charSequence){
		if(charSequence == null || charSequence.length() == 0){
			return false;
		}

		for(int i = 0, j = charSequence.length(); i < j; i++){
			if(isXdigit(charSequence.charAt(i)) == false){
				return false;
			}
		}

		return true;
	}

	/**
	 * 验证是否为 16 进制字符
	 *
	 * @param ch
	 * 		待验证的字符
	 *
	 * @return Boolean
	 */
	public final static boolean isXdigit(final char ch){
		return isNumeric(ch) || ch >= 'a' && ch <= 'f' || ch >= 'A' && ch <= 'F';
	}

	/**
	 * 验证是否为电话号码
	 *
	 * @param charSequence
	 * 		待验证的字符串
	 *
	 * @return Boolean
	 */
	public final static boolean isTel(final CharSequence charSequence){
		return isTel(charSequence, TelValidator.AreaCodeType.BOTH);
	}

	/**
	 * 验证是否为电话号码
	 *
	 * @param charSequence
	 * 		待验证的字符串
	 * @param areaCodeType
	 * 		是否包含区号
	 *
	 * @return Boolean
	 */
	public final static boolean isTel(final CharSequence charSequence, final TelValidator.AreaCodeType areaCodeType){
		return TelValidator.isValid(charSequence, areaCodeType);
	}

	/**
	 * 验证是否为手机号码
	 *
	 * @param charSequence
	 * 		待验证的字符串
	 *
	 * @return Boolean
	 */
	public final static boolean isMobile(final CharSequence charSequence){
		return MobileValidator.isValid(charSequence);
	}

	/**
	 * 验证是否为邮政编码
	 *
	 * @param charSequence
	 * 		待验证的字符串
	 *
	 * @return Boolean
	 */
	public final static boolean isPostCode(final CharSequence charSequence){
		return PostCodeValidator.isValid(charSequence);
	}

	/**
	 * 验证是否为域名
	 *
	 * @param charSequence
	 * 		待验证的字符串
	 *
	 * @return Boolean
	 */
	public final static boolean isEmail(final CharSequence charSequence){
		return EmailValidator.isValid(charSequence);
	}

	/**
	 * 验证是否为 QQValidator 号码
	 *
	 * @param charSequence
	 * 		待验证的字符串
	 *
	 * @return Boolean
	 */
	public final static boolean isQQ(final CharSequence charSequence){
		return QQValidator.isValid(charSequence);
	}

	/**
	 * 验证是否为身份证号码（仅支持 18 位身份证号码）
	 *
	 * @param charSequence
	 * 		待验证的字符串
	 *
	 * @return Boolean
	 */
	public final static boolean isIDCard(final CharSequence charSequence){
		return IDCardValidator.isValid(charSequence, false, null);
	}

	/**
	 * 验证是否为身份证号码（仅支持 18 位身份证号码）
	 *
	 * @param charSequence
	 * 		待验证的字符串
	 * @param strict
	 * 		是否和生日严格匹配
	 * @param birthday
	 * 		生日日期
	 *
	 * @return Boolean
	 */
	public final static boolean isIDCard(final CharSequence charSequence, final boolean strict, final Date birthday){
		return IDCardValidator.isValid(charSequence, strict, birthday);
	}

	/**
	 * 验证字符串是否为 ISBNValidator 10
	 *
	 * @param charSequence
	 * 		待验证的字符串
	 * @param separator
	 * 		ISBNValidator 分隔符
	 *
	 * @return Boolean
	 */
	public final static boolean isIsbn10(final CharSequence charSequence, final char separator){
		return ISBNValidator.isIsbn10(charSequence, separator);
	}

	/**
	 * 验证字符串是否为 ISBNValidator 13
	 *
	 * @param charSequence
	 * 		待验证的字符串
	 * @param separator
	 * 		ISBNValidator 分隔符
	 *
	 * @return Boolean
	 */
	public final static boolean isIsbn13(final CharSequence charSequence, final char separator){
		return ISBNValidator.isIsbn13(charSequence, separator);
	}

	/**
	 * 验证字符串是否为 ISBN
	 *
	 * @param charSequence
	 * 		待验证的字符串
	 * @param separator
	 * 		ISBNValidator 分隔符
	 *
	 * @return Boolean
	 */
	public final static boolean isIsbn(final CharSequence charSequence, final char separator){
		return ISBNValidator.isIsbn10(charSequence, separator) || ISBNValidator.isIsbn13(charSequence, separator);
	}

	/**
	 * 验证字符串是否为 IPv4
	 *
	 * @param charSequence
	 * 		待验证的字符串
	 *
	 * @return Boolean
	 */
	public final static boolean isIpV4(final CharSequence charSequence){
		return IpValidator.isValid(charSequence, IpType.IP_V4);
	}

	/**
	 * 验证字符串是否为 IPv6
	 *
	 * @param charSequence
	 * 		待验证的字符串
	 *
	 * @return Boolean
	 */
	public final static boolean isIpV6(final CharSequence charSequence){
		return IpValidator.isValid(charSequence, IpType.IP_V6);
	}

	/**
	 * 验证字符串是否为 IP
	 *
	 * @param charSequence
	 * 		待验证的字符串
	 *
	 * @return Boolean
	 */
	public final static boolean isIp(final CharSequence charSequence){
		return isIpV4(charSequence) || isIpV6(charSequence);
	}

	/**
	 * 验证是否为合法的端口
	 *
	 * @param port
	 * 		端口号
	 *
	 * @return Boolean
	 */
	public final static boolean isPort(final int port){
		return isBetween(port, 0, 65535, true);
	}

}