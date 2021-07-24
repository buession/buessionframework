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

import java.lang.reflect.Field;

/**
 * 枚举工具类
 * More {@link org.apache.commons.lang3.EnumUtils}
 *
 * @author Yong.Teng
 */
public class EnumUtils extends org.apache.commons.lang3.EnumUtils {

	/**
	 * 判断枚举字段的值是否为 $VALUES
	 *
	 * @param field
	 * 		枚举字段
	 *
	 * @return 枚举字段的值为 $VALUES 返回 true；否则，返回 false
	 */
	public static boolean isEnumValuesField(final Field field){
		return field != null && (field.isEnumConstant() == false && "$VALUES".equals(field.getName()));
	}

	/**
	 * 判断枚举字段的值是否不为 $VALUES
	 *
	 * @param field
	 * 		枚举字段
	 *
	 * @return 枚举字段的值不为 $VALUES 返回 true；否则，返回 false
	 */
	public static boolean notEnumValuesField(final Field field){
		return field != null && (field.isEnumConstant() == false && "$VALUES".equals(field.getName()) == false);
	}

	/**
	 * 返回带指定名称的指定枚举类型的枚举常量
	 *
	 * @param enumClass
	 * 		要查询的枚举的类
	 * @param name
	 * 		要返回的枚举常量名称
	 * @param <E>
	 * 		要返回其常数的枚举类型
	 *
	 * @return 指定枚举类型的枚举常量
	 */
	public static <E extends Enum<E>> E valueOf(final Class<E> enumClass, final String name){
		return getEnum(enumClass, name);
	}

}
