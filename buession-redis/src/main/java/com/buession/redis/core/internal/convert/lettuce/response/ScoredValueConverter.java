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
package com.buession.redis.core.internal.convert.lettuce.response;

import com.buession.core.converter.Converter;
import com.buession.core.converter.ListConverter;
import com.buession.core.converter.SetConverter;
import com.buession.redis.core.Tuple;
import io.lettuce.core.ScoredValue;

import java.util.List;
import java.util.Set;

/**
 * Lettuce {@link ScoredValue} 转换为 {@link Tuple}
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public final class ScoredValueConverter implements Converter<ScoredValue<byte[]>, Tuple> {

	@Override
	public Tuple convert(final ScoredValue<byte[]> source) {
		return new Tuple(source.getValue(), source.getScore());
	}

	/**
	 * Lettuce {@link List} {@link ScoredValue<byte[]>} 转换为 {@link List} {@link Tuple}
	 *
	 * @author Yong.Teng
	 * @since 3.0.0
	 */
	public final static class ListScoredValueConverter extends ListConverter<ScoredValue<byte[]>, Tuple> {

		public ListScoredValueConverter() {
			super(new ScoredValueConverter());
		}

	}

	/**
	 * Lettuce {@link Set} {@link ScoredValue<byte[]>} 转换为 {@link Set} {@link Tuple}
	 *
	 * @author Yong.Teng
	 * @since 3.0.0
	 */
	public final static class SetScoredValueConverter extends SetConverter<ScoredValue<byte[]>, Tuple> {

		public SetScoredValueConverter() {
			super(new ScoredValueConverter());
		}

	}

}
