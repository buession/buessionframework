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
package com.buession.core.utils;

import com.buession.lang.Status;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Key =&lt; Value 解析器，可以将字符串以特定的分隔符解析成 Key =&lt; Value 键值对
 *
 * @author Yong.Teng
 */
public class KeyValueParser {

	/**
	 * 键
	 */
	private String key;

	/**
	 * 字符串值
	 */
	private String value;

	/**
	 * Float 类型值
	 */
	private Float floatValue;

	/**
	 * Double 类型值
	 */
	private Double doubleValue;

	/**
	 * Short 类型值
	 */
	private Short shortValue;

	/**
	 * Integer 类型值
	 */
	private Integer intValue;

	/**
	 * Long 类型值
	 */
	private Long longValue;

	/**
	 * Boolean 类型值
	 */
	private Boolean boolValue;

	/**
	 * Status 类型值
	 */
	private Status statusValue;

	/**
	 * 构造函数
	 *
	 * @param str
	 * 		待解析字符串
	 * @param delimiter
	 * 		分隔符
	 */
	public KeyValueParser(final String str, final String delimiter){
		int i = str.indexOf(delimiter);
		this.key = str.substring(0, i);
		this.value = str.substring(i + 1);
	}

	/**
	 * 构造函数
	 *
	 * @param str
	 * 		待解析字符串
	 * @param delimiter
	 * 		分隔符
	 */
	public KeyValueParser(final String str, final char delimiter){
		int i = str.indexOf(delimiter);
		this.key = str.substring(0, i);
		this.value = str.substring(i + 1);
	}

	/**
	 * 检测键是否为 s
	 *
	 * @param s
	 * 		待检测的键
	 *
	 * @return 键为 s 时，返回 true；否则，返回 false
	 */
	public boolean isKey(String s){
		return Objects.equals(s, key);
	}

	/**
	 * 检测键是否匹配模式 pattern
	 *
	 * @param pattern
	 * 		模式
	 *
	 * @return 键是否匹配模式 pattern 时，返回 true；否则，返回 false
	 */
	public boolean isKey(Pattern pattern){
		return pattern != null && pattern.matcher(key).matches();
	}

	/**
	 * 返回键
	 *
	 * @return 键
	 */
	public String getKey(){
		return key;
	}

	/**
	 * 返回值
	 *
	 * @return 值
	 */
	public String getValue(){
		return value;
	}

	/**
	 * 返回 Float 类型的值
	 *
	 * @return Float 类型的值
	 */
	public Float getFloatValue(){
		if(floatValue == null){
			floatValue = Float.parseFloat(value);
		}

		return floatValue;
	}

	/**
	 * 返回 Double 类型的值
	 *
	 * @return Double 类型的值
	 */
	public Double getDoubleValue(){
		if(doubleValue == null){
			doubleValue = Double.parseDouble(value);
		}

		return doubleValue;
	}

	/**
	 * 返回 Short 类型的值
	 *
	 * @return Short 类型的值
	 */
	public Short getShortValue(){
		if(shortValue == null){
			shortValue = Short.parseShort(value);
		}

		return shortValue;
	}

	/**
	 * 返回 Integer 类型的值
	 *
	 * @return Integer 类型的值
	 */
	public Integer getIntegerValue(){
		if(intValue == null){
			intValue = Integer.parseInt(value);
		}

		return intValue;
	}

	/**
	 * 返回 Integer 类型的值
	 *
	 * @return Integer 类型的值
	 */
	public Integer getIntValue(){
		return getIntegerValue();
	}

	/**
	 * 返回 Long 类型的值
	 *
	 * @return Long 类型的值
	 */
	public Long getLongValue(){
		if(longValue == null){
			longValue = Long.parseLong(value);
		}

		return longValue;
	}

	/**
	 * 返回 Boolean 类型的值
	 *
	 * @return Boolean 类型的值，当 value 为 1 或不区分大小写为 true 时，返回 true；否则，返回 false
	 */
	public Boolean getBooleanValue(){
		if(boolValue == null){
			boolValue = Boolean.parseBoolean(value) || "1".equals(value);
		}

		return boolValue;
	}

	/**
	 * @return Boolean 类型的值，当 value 为 1 或不区分大小写为 true 时，返回 true；否则，返回 false
	 *
	 * @see #getBooleanValue()
	 */
	public Boolean getBoolValue(){
		return getBooleanValue();
	}

	/**
	 * 返回 Status 类型值
	 *
	 * @return Status 类型值。当 value 不区分大小写为 OK、yes、on、1、true 返回 Status.SUCCESS；否则，返回 Status.FAILURE
	 *
	 * @see Status
	 */
	public Status getStatusValue(){
		if(statusValue == null){
			statusValue =
					StatusUtils.valueOf("OK".equalsIgnoreCase(value) || "yes".equalsIgnoreCase(value) ||
							"on".equalsIgnoreCase(value) || getBooleanValue());
		}

		return statusValue;
	}

	/**
	 * 返回枚举类型的值，不区分大小写的获取枚举字段
	 *
	 * @param clazz
	 * 		枚举类
	 * @param <E>
	 * 		枚举类型
	 *
	 * @return 举类字段
	 *
	 * @see EnumUtils#getEnumIgnoreCase(Class, String)
	 */
	public <E extends Enum<E>> E getEnumValue(Class<E> clazz){
		return EnumUtils.getEnumIgnoreCase(clazz, value);
	}

}
