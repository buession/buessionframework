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
import com.buession.redis.core.Tuple;
import io.lettuce.core.ScoredValue;
import org.springframework.lang.Nullable;

/**
 * Lettuce {@link ScoredValue} 转换为 {@link  Tuple}
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public interface ScoredValueTupleConverter<V> extends Converter<ScoredValue<V>, Tuple> {

	final class StringScoredValueTupleConverter implements ScoredValueTupleConverter<String> {

		@Nullable
		@Override
		public Tuple convert(final ScoredValue<String> source) {
			if(source == null){
				return null;
			}else{
				return new Tuple(source.getValue(), source.getScore());
			}
		}

		public static ListConverter<ScoredValue<String>, Tuple> listConverter() {
			return new ListConverter<>(new StringScoredValueTupleConverter());
		}

	}

	final class BinaryScoredValueTupleConverter implements ScoredValueTupleConverter<byte[]> {

		@Nullable
		@Override
		public Tuple convert(final ScoredValue<byte[]> source) {
			if(source == null){
				return null;
			}else{
				return new Tuple(source.getValue(), source.getScore());
			}
		}

		public static ListConverter<ScoredValue<byte[]>, Tuple> listConverter() {
			return new ListConverter<>(new BinaryScoredValueTupleConverter());
		}

	}

}
