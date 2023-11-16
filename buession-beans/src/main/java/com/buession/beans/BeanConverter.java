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
package com.buession.beans;

import org.springframework.lang.Nullable;

/**
 * Bean 转换器
 *
 * @author Yong.Teng
 * @since 2.3.1
 */
@FunctionalInterface
public interface BeanConverter {

	/**
	 * 将原类型对象 source 转换成目标类型对象
	 *
	 * @param source
	 * 		源对象
	 * @param target
	 * 		目标对象
	 * @param <S>
	 * 		源类型
	 * @param <T>
	 * 		目标类型
	 *
	 * @return 目标对象
	 */
	@Nullable
	<S, T> T convert(final S source, final T target);

}
