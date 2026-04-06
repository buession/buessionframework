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
 * | Copyright @ 2013-2026 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.core.converter;

import java.util.Collection;
import java.util.stream.Collector;

/**
 * {@link Collection} 转换器抽象类
 *
 * @param <SV>
 * 		原元素类型
 * @param <TV>
 * 		目标元素类型
 * @param <ST>
 * 		原 {@link Collection} 类型
 * @param <TT>
 * 		目标 {@link Collection} 类型
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public abstract class AbstractCollectionConverter<SV, TV, ST extends Collection<SV>, TT extends Collection<TV>>
		extends BaseItemConverter<SV, TV, ST, TT> implements CollectionConverter<SV, TV, ST, TT> {

	/**
	 * 构造函数
	 *
	 * @param itemConverter
	 * 		item 转换器
	 */
	public AbstractCollectionConverter(final Converter<SV, TV> itemConverter) {
		super(itemConverter);
	}

	@Override
	public TT convert(final ST source) {
		return source == null ? null : source.stream().map(itemConverter::convert).collect(collect(source));
	}

	protected abstract Collector<TV, ?, TT> collect(final ST source);

}
