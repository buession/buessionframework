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
package com.buession.core.utils;

import java.util.Optional;
import java.util.function.Consumer;

/**
 * 对象工具类
 *
 * @author Yong.Teng
 * @since 2.3.2
 */
@Deprecated
public class ObjectUtils {

	private ObjectUtils() {

	}

	/**
	 * 当对象不为 null 时，执行方法
	 *
	 * @param object
	 * 		对象
	 * @param consumer
	 *        {@link Consumer}
	 * @param <T>
	 * 		对象引用类型
	 */
	public static <T> void invokeIfAvailable(final T object, final Consumer<T> consumer) {
		Optional.ofNullable(object).ifPresent(consumer);
	}

}
