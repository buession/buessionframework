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
package com.buession.lang;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 原生类型
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public class Primitive {

	public final static Map<Class<?>, Object> DEFAULT_TYPE_VALUES;

	static {
		Map<Class<?>, Object> values = new HashMap<>(8);
		values.put(boolean.class, false);
		values.put(byte.class, (byte) 0);
		values.put(short.class, (short) 0);
		values.put(int.class, 0);
		values.put(long.class, 0L);
		values.put(float.class, 0F);
		values.put(double.class, 0D);
		values.put(char.class, '\0');
		DEFAULT_TYPE_VALUES = Collections.unmodifiableMap(values);
	}

	/**
	 * 原始类型转包装类型
	 *
	 * @param type
	 * 		原始类型
	 * @param <T>
	 * 		包装类型
	 *
	 * @return 包装类型
	 *
	 * @since 2.3.1
	 */
	@SuppressWarnings({"unchecked"})
	public static <T> Class<T> primitiveToWrapper(final Class<T> type) {
		if(type == null || type.isPrimitive() == false){
			return type;
		}

		if(type == Short.TYPE){
			return (Class<T>) Short.class;
		}else if(type == Integer.TYPE){
			return (Class<T>) Integer.class;
		}else if(type == Long.TYPE){
			return (Class<T>) Long.class;
		}else if(type == Double.TYPE){
			return (Class<T>) Double.class;
		}else if(type == Float.TYPE){
			return (Class<T>) Float.class;
		}else if(type == Boolean.TYPE){
			return (Class<T>) Boolean.class;
		}else if(type == Byte.TYPE){
			return (Class<T>) Byte.class;
		}else if(type == Character.TYPE){
			return (Class<T>) Character.class;
		}else{
			return type;
		}
	}

}
