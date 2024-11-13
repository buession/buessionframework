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
 * | Copyright @ 2013-2024 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.core.utils;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 对象工具
 *
 * @author Yong.Teng
 * @since 3.0.1
 */
public final class Objects {

	private Objects() {

	}

	/**
	 * 如果 {@code supplier1} 返回 null，则执行 {@code supplier2}，如果 {@code supplier2} 返回值不为 null，
	 * 则执行 {@code function.apply}，否则返回当前值
	 *
	 * @param supplier1
	 *        {@link Supplier}
	 * @param supplier2
	 *        {@link Supplier}
	 * @param function
	 *        {@link Function}
	 * @param <T>
	 * 		返回值类型
	 *
	 * @return 返回值
	 */
	public static <T> T applyIfAbsent(final Supplier<T> supplier1, final Supplier<T> supplier2,
									  final Function<T, T> function) {
		T value = supplier1.get();

		if(value == null){
			value = supplier2.get();

			if(value != null){
				function.apply(value);
			}
		}

		return value;
	}

}
