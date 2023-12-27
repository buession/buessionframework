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
 * | Copyright @ 2013-2023 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.velocity.tools;

import com.buession.core.validator.Validate;
import com.buession.core.validator.routines.TelValidator;
import org.apache.velocity.tools.config.DefaultKey;
import org.apache.velocity.tools.generic.SafeConfig;

import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;

/**
 * 验证工具
 *
 * @author Yong.Teng
 * @since 2.3.2
 */
@DefaultKey("validate")
public class ValidateTool extends SafeConfig {

	/**
	 * 验证是否为 NULL
	 *
	 * @param var
	 * 		待验证的对象
	 *
	 * @return 为 NULL 时，返回 TRUE；否则，返回 FALSE
	 */
	public boolean isNull(final Object var) {
		return Validate.isNull(var);
	}

	/**
	 * 验证是否不为 NULL
	 *
	 * @param var
	 * 		待验证的对象
	 *
	 * @return 不为 NULL 时，返回 TRUE；否则，返回 FALSE
	 */
	public boolean isNotNull(final Object var) {
		return Validate.isNotNull(var);
	}

	/**
	 * 验证一个有序字符集合是否为 null、或空字符串或者空白字符
	 *
	 * @param charSequence
	 * 		待验证的有序字符集合
	 *
	 * @return 为 NULL 或长度为 0 或为空白字符串时，返回 TRUE；否则，返回 FALSE
	 */
	public boolean isBlank(final CharSequence charSequence) {
		return Validate.isNotNull(charSequence);
	}

	/**
	 * 验证一个有序字符集合是否不为 null、空字符串且不仅仅为空白字符串
	 *
	 * @param charSequence
	 * 		待验证的有序字符集合
	 *
	 * @return 不为 null、空字符串且不仅仅为空白字符串时，返回 TRUE；否则，返回 FALSE
	 */
	public boolean isNotBlank(final CharSequence charSequence) {
		return Validate.isNotBlank(charSequence);
	}

	/**
	 * 验证一个有序字符集合是否为空
	 *
	 * @param charSequence
	 * 		待验证的有序字符集合
	 *
	 * @return 为 NULL 或长度为 0 时，返回 TRUE；否则，返回 FALSE
	 */
	public boolean isEmpty(final CharSequence charSequence) {
		return Validate.isEmpty(charSequence);
	}

	/**
	 * 验证数组是否为空
	 *
	 * @param array
	 * 		待验证数组
	 *
	 * @return 为 NULL 或长度为 0 时，返回 TRUE；否则，返回 FALSE
	 */
	public boolean isEmpty(final float[] array) {
		return Validate.isEmpty(array);
	}

	/**
	 * 验证数组是否为空
	 *
	 * @param array
	 * 		待验证数组
	 *
	 * @return 为 NULL 或长度为 0 时，返回 TRUE；否则，返回 FALSE
	 */
	public boolean isEmpty(final double[] array) {
		return Validate.isEmpty(array);
	}

	/**
	 * 验证数组是否为空
	 *
	 * @param array
	 * 		待验证数组
	 *
	 * @return 为 NULL 或长度为 0 时，返回 TRUE；否则，返回 FALSE
	 */
	public boolean isEmpty(final short[] array) {
		return Validate.isEmpty(array);
	}

	/**
	 * 验证数组是否为空
	 *
	 * @param array
	 * 		待验证数组
	 *
	 * @return 为 NULL 或长度为 0 时，返回 TRUE；否则，返回 FALSE
	 */
	public boolean isEmpty(final int[] array) {
		return Validate.isEmpty(array);
	}

	/**
	 * 验证数组是否为空
	 *
	 * @param array
	 * 		待验证数组
	 *
	 * @return 为 NULL 或长度为 0 时，返回 TRUE；否则，返回 FALSE
	 */
	public boolean isEmpty(final long[] array) {
		return Validate.isEmpty(array);
	}

	/**
	 * 验证数组是否为空
	 *
	 * @param array
	 * 		待验证数组
	 *
	 * @return 为 NULL 或长度为 0 时，返回 TRUE；否则，返回 FALSE
	 */
	public boolean isEmpty(final byte[] array) {
		return Validate.isEmpty(array);
	}

	/**
	 * 验证数组是否为空
	 *
	 * @param array
	 * 		待验证数组
	 *
	 * @return 为 NULL 或长度为 0 时，返回 TRUE；否则，返回 FALSE
	 */
	public boolean isEmpty(final char[] array) {
		return Validate.isEmpty(array);
	}

	/**
	 * 验证数组是否为空
	 *
	 * @param array
	 * 		待验证数组
	 *
	 * @return 为 NULL 或长度为 0 时，返回 TRUE；否则，返回 FALSE
	 */
	public boolean isEmpty(final boolean[] array) {
		return Validate.isEmpty(array);
	}

	/**
	 * 验证数组是否为空
	 *
	 * @param array
	 * 		待验证数组
	 *
	 * @return 为 NULL 或长度为 0 时，返回 TRUE；否则，返回 FALSE
	 */
	public boolean isEmpty(final Object[] array) {
		return Validate.isEmpty(array);
	}

	/**
	 * 验证一个容器是否为空
	 *
	 * @param collection
	 * 		待验证的容器
	 *
	 * @return 为 NULL 或不含有元素时，返回 TRUE；否则，返回 FALSE
	 */
	public boolean isEmpty(final Collection<?> collection) {
		return Validate.isEmpty(collection);
	}

	/**
	 * 验证一个 Map 是否为空
	 *
	 * @param map
	 * 		待验证的容器
	 *
	 * @return 为 NULL 或不含有元素时，返回 TRUE；否则，返回 FALSE
	 */
	public boolean isEmpty(final Map<?, ?> map) {
		return Validate.isEmpty(map);
	}

	/**
	 * 验证一个迭代器是否为空
	 *
	 * @param iterator
	 * 		待验证的迭代器
	 *
	 * @return 为 NULL 或不含有元素时，返回 TRUE；否则，返回 FALSE
	 */
	public boolean isEmpty(final Iterator<?> iterator) {
		return Validate.isEmpty(iterator);
	}

	/**
	 * 验证一个 Enumeration 是否为空
	 *
	 * @param enumeration
	 * 		待验证的迭代器
	 *
	 * @return 为 NULL 或不含有元素时，返回 TRUE；否则，返回 FALSE
	 */
	public boolean isEmpty(final Enumeration<?> enumeration) {
		return Validate.isEmpty(enumeration);
	}

	/**
	 * 验证一个有序字符集合是否不为空
	 *
	 * @param charSequence
	 * 		待验证的有序字符集合
	 *
	 * @return 不为 NULL 且长度大于 0 时，返回 TRUE；否则，返回 FALSE
	 */
	public boolean isNotEmpty(final CharSequence charSequence) {
		return Validate.isNotEmpty(charSequence);
	}

	/**
	 * 验证数组是否不为空
	 *
	 * @param array
	 * 		待验证数组
	 *
	 * @return 不为 NULL 且长度大于 0 时，返回 TRUE；否则，返回 FALSE
	 */
	public boolean isNotEmpty(final float[] array) {
		return Validate.isNotEmpty(array);
	}

	/**
	 * 验证数组是否不为空
	 *
	 * @param array
	 * 		待验证数组
	 *
	 * @return 不为 NULL 且长度大于 0 时，返回 TRUE；否则，返回 FALSE
	 */
	public boolean isNotEmpty(final double[] array) {
		return Validate.isNotEmpty(array);
	}

	/**
	 * 验证数组是否不为空
	 *
	 * @param array
	 * 		待验证数组
	 *
	 * @return 不为 NULL 且长度大于 0 时，返回 TRUE；否则，返回 FALSE
	 */
	public boolean isNotEmpty(final short[] array) {
		return Validate.isNotEmpty(array);
	}

	/**
	 * 验证数组是否不为空
	 *
	 * @param array
	 * 		待验证数组
	 *
	 * @return 不为 NULL 且长度大于 0 时，返回 TRUE；否则，返回 FALSE
	 */
	public boolean isNotEmpty(final int[] array) {
		return Validate.isNotEmpty(array);
	}

	/**
	 * 验证数组是否不为空
	 *
	 * @param array
	 * 		待验证数组
	 *
	 * @return 不为 NULL 且长度大于 0 时，返回 TRUE；否则，返回 FALSE
	 */
	public boolean isNotEmpty(final long[] array) {
		return Validate.isNotEmpty(array);
	}

	/**
	 * 验证数组是否不为空
	 *
	 * @param array
	 * 		待验证数组
	 *
	 * @return 不为 NULL 且长度大于 0 时，返回 TRUE；否则，返回 FALSE
	 */
	public boolean isNotEmpty(final byte[] array) {
		return Validate.isNotEmpty(array);
	}

	/**
	 * 验证数组是否不为空
	 *
	 * @param array
	 * 		待验证数组
	 *
	 * @return 不为 NULL 且长度大于 0 时，返回 TRUE；否则，返回 FALSE
	 */
	public boolean isNotEmpty(final char[] array) {
		return Validate.isNotEmpty(array);
	}

	/**
	 * 验证数组是否不为空
	 *
	 * @param array
	 * 		待验证数组
	 *
	 * @return 不为 NULL 且长度大于 0 时，返回 TRUE；否则，返回 FALSE
	 */
	public boolean isNotEmpty(final boolean[] array) {
		return Validate.isNotEmpty(array);
	}

	/**
	 * 验证数组是否不为空
	 *
	 * @param array
	 * 		待验证数组
	 *
	 * @return 不为 NULL 且长度大于 0 时，返回 TRUE；否则，返回 FALSE
	 */
	public boolean isNotEmpty(final Object[] array) {
		return Validate.isNotEmpty(array);
	}

	/**
	 * 验证一个容器是否不为空
	 *
	 * @param collection
	 * 		待验证的容器
	 *
	 * @return 不为 NULL 且含有元素时，返回 TRUE；否则，返回 FALSE
	 */
	public boolean isNotEmpty(final Collection<?> collection) {
		return Validate.isNotEmpty(collection);
	}

	/**
	 * 验证一个 Map 是否不为空
	 *
	 * @param map
	 * 		待验证的容器
	 *
	 * @return 不为 NULL 且含有元素时，返回 TRUE；否则，返回 FALSE
	 */
	public boolean isNotEmpty(final Map<?, ?> map) {
		return Validate.isNotEmpty(map);
	}

	/**
	 * 验证一个迭代器是否不为空
	 *
	 * @param iterator
	 * 		待验证的迭代器
	 *
	 * @return 不为 NULL 且含有元素时，返回 TRUE；否则，返回 FALSE
	 */
	public boolean isNotEmpty(final Iterator<?> iterator) {
		return Validate.isNotEmpty(iterator);
	}

	/**
	 * 验证一个 Enumeration 是否不为空
	 *
	 * @param enumeration
	 * 		待验证的迭代器
	 *
	 * @return 不为 NULL 且含有元素时，返回 TRUE；否则，返回 FALSE
	 */
	public boolean isNotEmpty(final Enumeration<?> enumeration) {
		return Validate.isNotEmpty(enumeration);
	}

	/**
	 * 验证一个有序字符集合是否不为 null、空字符串且不仅仅为空白字符串
	 *
	 * @param charSequence
	 * 		待验证的有序字符集合
	 *
	 * @return 不为 null、空字符串且不仅仅为空白字符串时，返回 TRUE；否则，返回 FALSE
	 */
	public boolean hasText(final CharSequence charSequence) {
		return Validate.hasText(charSequence);
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
	public boolean isBetween(final long value, final long min, final long max) {
		return Validate.isBetween(value, min, max);
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
	public boolean isBetween(final long value, final long min, final long max, final boolean isContain) {
		return Validate.isBetween(value, min, max, isContain);
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
	public boolean isBetween(final int value, final int min, final int max) {
		return Validate.isBetween(value, min, max);
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
	public boolean isBetween(final int value, final int min, final int max, final boolean isContain) {
		return Validate.isBetween(value, min, max, isContain);
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
	public boolean isBetween(final double value, final double min, final double max) {
		return Validate.isBetween(value, min, max);
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
	public boolean isBetween(final double value, final double min, final double max, final boolean isContain) {
		return Validate.isBetween(value, min, max, isContain);
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
	public boolean isBetween(final float value, final float min, final float max) {
		return Validate.isBetween(value, min, max);
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
	public boolean isBetween(final float value, final float min, final float max, final boolean isContain) {
		return Validate.isBetween(value, min, max, isContain);
	}

	/**
	 * 验证是否为英文字符串
	 *
	 * @param charSequence
	 * 		待验证的字符串
	 *
	 * @return Boolean
	 */
	public boolean isAlpha(final CharSequence charSequence) {
		return Validate.isAlpha(charSequence);
	}

	/**
	 * 验证是否为英文字符
	 *
	 * @param ch
	 * 		待验证的字符
	 *
	 * @return Boolean
	 */
	public boolean isAlpha(final char ch) {
		return Validate.isAlpha(ch);
	}

	/**
	 * 验证是否为数字字符串
	 *
	 * @param charSequence
	 * 		待验证的字符串
	 *
	 * @return Boolean
	 */
	public boolean isNumeric(final CharSequence charSequence) {
		return Validate.isNumeric(charSequence);
	}

	/**
	 * 验证是否为数字字符
	 *
	 * @param ch
	 * 		待验证的字符
	 *
	 * @return Boolean
	 */
	public boolean isNumeric(final char ch) {
		return Validate.isNumeric(ch);
	}

	/**
	 * 验证是否为数字字符串
	 *
	 * @param charSequence
	 * 		待验证的字符串
	 *
	 * @return Boolean
	 */
	public boolean isDigit(final CharSequence charSequence) {
		return Validate.isDigit(charSequence);
	}

	/**
	 * 验证是否为数字字符
	 *
	 * @param ch
	 * 		待验证的字符
	 *
	 * @return Boolean
	 */
	public boolean isDigit(final char ch) {
		return Validate.isDigit(ch);
	}

	/**
	 * 验证是否为英文或数字字符串
	 *
	 * @param charSequence
	 * 		待验证的字符串
	 *
	 * @return Boolean
	 */
	public boolean isAlnum(final CharSequence charSequence) {
		return Validate.isAlnum(charSequence);
	}

	/**
	 * 验证是否为英文或数字字符
	 *
	 * @param ch
	 * 		待验证的字符
	 *
	 * @return Boolean
	 */
	public boolean isAlnum(final char ch) {
		return Validate.isAlnum(ch);
	}

	/**
	 * 验证是否为 16 进制字符串
	 *
	 * @param charSequence
	 * 		待验证的字符串
	 *
	 * @return Boolean
	 */
	public boolean isXdigit(final CharSequence charSequence) {
		return Validate.isXdigit(charSequence);
	}

	/**
	 * 验证是否为 16 进制字符
	 *
	 * @param ch
	 * 		待验证的字符
	 *
	 * @return Boolean
	 */
	public boolean isXdigit(final char ch) {
		return Validate.isXdigit(ch);
	}

	/**
	 * 验证是否为电话号码
	 *
	 * @param charSequence
	 * 		待验证的字符串
	 *
	 * @return Boolean
	 */
	public boolean isTel(final CharSequence charSequence) {
		return Validate.isTel(charSequence);
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
	public boolean isTel(final CharSequence charSequence, final TelValidator.AreaCodeType areaCodeType) {
		return Validate.isTel(charSequence, areaCodeType);
	}

	/**
	 * 验证是否为手机号码
	 *
	 * @param charSequence
	 * 		待验证的字符串
	 *
	 * @return Boolean
	 */
	public boolean isMobile(final CharSequence charSequence) {
		return Validate.isMobile(charSequence);
	}

	/**
	 * 验证是否为邮政编码
	 *
	 * @param charSequence
	 * 		待验证的字符串
	 *
	 * @return Boolean
	 */
	public boolean isPostCode(final CharSequence charSequence) {
		return Validate.isPostCode(charSequence);
	}

	/**
	 * 验证是否为域名
	 *
	 * @param charSequence
	 * 		待验证的字符串
	 *
	 * @return Boolean
	 */
	public boolean isEmail(final CharSequence charSequence) {
		return Validate.isEmail(charSequence);
	}

	/**
	 * 验证是否为 QQValidator 号码
	 *
	 * @param charSequence
	 * 		待验证的字符串
	 *
	 * @return Boolean
	 */
	public boolean isQQ(final CharSequence charSequence) {
		return Validate.isQQ(charSequence);
	}

	/**
	 * 验证是否为身份证号码（仅支持 18 位身份证号码）
	 *
	 * @param charSequence
	 * 		待验证的字符串
	 *
	 * @return Boolean
	 */
	public boolean isIDCard(final CharSequence charSequence) {
		return Validate.isIDCard(charSequence);
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
	public boolean isIDCard(final CharSequence charSequence, final boolean strict, final Date birthday) {
		return Validate.isIDCard(charSequence, strict, birthday);
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
	public boolean isIsbn10(final CharSequence charSequence, final char separator) {
		return Validate.isIsbn10(charSequence, separator);
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
	public boolean isIsbn13(final CharSequence charSequence, final char separator) {
		return Validate.isIsbn13(charSequence, separator);
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
	public boolean isIsbn(final CharSequence charSequence, final char separator) {
		return Validate.isIsbn(charSequence, separator);
	}

	/**
	 * 验证字符串是否为 IPv4
	 *
	 * @param charSequence
	 * 		待验证的字符串
	 *
	 * @return Boolean
	 */
	public boolean isIpV4(final CharSequence charSequence) {
		return Validate.isIpV4(charSequence);
	}

	/**
	 * 验证字符串是否为 IPv6
	 *
	 * @param charSequence
	 * 		待验证的字符串
	 *
	 * @return Boolean
	 */
	public boolean isIpV6(final CharSequence charSequence) {
		return Validate.isIpV6(charSequence);
	}

	/**
	 * 验证字符串是否为 IP
	 *
	 * @param charSequence
	 * 		待验证的字符串
	 *
	 * @return Boolean
	 */
	public boolean isIp(final CharSequence charSequence) {
		return Validate.isIp(charSequence);
	}

	/**
	 * 验证是否为合法的端口
	 *
	 * @param port
	 * 		端口号
	 *
	 * @return Boolean
	 */
	public boolean isPort(final int port) {
		return Validate.isPort(port);
	}

	/**
	 * 验证字符串是否为合法的 MimeType
	 *
	 * @param charSequence
	 * 		待验证的字符串
	 *
	 * @return Boolean
	 */
	public boolean isMimeType(final CharSequence charSequence) {
		return Validate.isMimeType(charSequence);
	}

}
