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
package com.buession.redis.core.internal.convert.jedis.response;

import com.buession.core.converter.Converter;
import com.buession.redis.core.KeyedZSetElement;
import redis.clients.jedis.BuilderFactory;

import java.util.List;

/**
 * jedis {@link redis.clients.jedis.resps.KeyedZSetElement} 转换为 {@link KeyedZSetElement}
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public final class KeyedZSetElementConverter
		implements Converter<redis.clients.jedis.resps.KeyedZSetElement, KeyedZSetElement> {

	@Override
	public KeyedZSetElement convert(final redis.clients.jedis.resps.KeyedZSetElement source) {
		return new KeyedZSetElement(source.getKey(), source.getElement(), source.getScore());
	}

	/**
	 * Jedis {@link List<byte[]>} 转换为 {@link List} {@link KeyedZSetElement}
	 *
	 * @author Yong.Teng
	 * @since 2.0.0
	 */
	public final static class BinaryDataKeyedZSetElementConverter implements Converter<List<byte[]>, KeyedZSetElement> {

		@Override
		public KeyedZSetElement convert(final List<byte[]> source) {
			return new KeyedZSetElement(source.get(0), source.get(1), BuilderFactory.DOUBLE.build(source.get(2)));
		}

	}

}
