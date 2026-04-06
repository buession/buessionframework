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

import com.buession.core.utils.Assert;

import java.util.Collection;

/**
 *
 * 数组转换至 {@link Collection}
 *
 * @param <S>
 * 		原类型
 * @param <T>
 * 		目标类型
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public abstract class AbstractArrayCollectionConverter<S, T, C extends Collection<T>>
		extends BaseItemConverter<S, T, S[], C> implements Converter<S[], C> {

	private final C result;

	/**
	 * 构造函数
	 *
	 * @param itemConverter
	 * 		Collection item 转换器
	 */
	public AbstractArrayCollectionConverter(final C result, final Converter<S, T> itemConverter) {
		super(itemConverter);
		Assert.isNull(result, "result can not be null");
		this.result = result;
	}

	@Override
	public C convert(final S[] source) {
		if(source == null){
			return null;
		}else{
			for(S s : source){
				result.add(itemConverter.convert(s));
			}

			return result;
		}
	}

}
