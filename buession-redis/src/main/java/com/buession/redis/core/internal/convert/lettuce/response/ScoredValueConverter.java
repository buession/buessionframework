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

/**
 * Lettuce {@link ScoredValue} 转换为 {@link Tuple}
 *
 * @author Yong.Teng
 * @since 2.4.0
 */
public final class ScoredValueConverter implements Converter<ScoredValue<byte[]>, Tuple> {

	public final static ScoredValueConverter INSTANCE = new ScoredValueConverter();

	public final static SetConverter<ScoredValue<byte[]>, Tuple> SET_CONVERTER = new SetConverter<>(
			INSTANCE);

	public final static ListConverter<ScoredValue<byte[]>, Tuple> LIST_CONVERTER = new ListConverter<>(
			INSTANCE);

	@Override
	public Tuple convert(final ScoredValue<byte[]> source) {
		return new Tuple(source.getValue(), source.getScore());
	}

}