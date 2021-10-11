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
package com.buession.core.converter.mapper;

import java.util.List;
import java.util.Set;

/**
 * POJO 映射
 *
 * @param <S>
 * 		源类型
 * @param <T>
 * 		目标类型
 *
 * @author Yong.Teng
 * @since 1.3.1
 */
public interface Mapper<S, T> {

	/**
	 * 将源对象映射到目标对象
	 *
	 * @param object
	 * 		源对象
	 *
	 * @return 目标对象实例
	 */
	T mapping(S object);

	/**
	 * 将源对象数组映射到目标对象数组
	 *
	 * @param object
	 * 		源对象数组
	 *
	 * @return 目标对象实例数组
	 */
	T[] mapping(S[] object);

	/**
	 * 将源 list 对象映射到目标 list 对象
	 *
	 * @param object
	 * 		源 list 对象
	 *
	 * @return 目标对象 list 实例
	 */
	List<T> mapping(List<S> object);

	/**
	 * 将源 set 对象映射到目标 set 对象
	 *
	 * @param object
	 * 		源 set 对象
	 *
	 * @return 目标对象 set 实例
	 */
	Set<T> mapping(Set<S> object);

}

