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
package com.buession.core.converter;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * List =&gt; Set 转换器
 *
 * @param <S>
 * 		原类型
 * @param <T>
 * 		目标类型
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public class ListSetConverter<S, T> implements Converter<List<S>, Set<T>> {

	/**
	 * List item 转换器
	 */
	private final Converter<S, T> itemConverter;

	/**
	 * 构造函数
	 *
	 * @param itemConverter
	 * 		List item 转换器
	 */
	public ListSetConverter(final Converter<S, T> itemConverter) {
		this.itemConverter = itemConverter;
	}

	@Override
	public Set<T> convert(final List<S> source) {
		if(source == null){
			return null;
		}else{
			return source.stream().map(itemConverter::convert)
					.collect(Collectors.toCollection(source instanceof LinkedList ? LinkedHashSet::new : HashSet::new));
		}
	}

}
