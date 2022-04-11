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
 * | Copyright @ 2013-2022 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.core.internal.convert;

import com.buession.core.converter.BooleanStatusConverter;
import com.buession.core.converter.Converter;
import com.buession.core.converter.ListConverter;
import com.buession.core.converter.MapConverter;
import com.buession.core.converter.PredicateStatusConverter;
import com.buession.lang.Status;
import com.buession.redis.core.Constants;
import com.buession.redis.utils.SafeEncoder;
import org.springframework.lang.Nullable;

import java.util.Objects;

/**
 * @author Yong.Teng
 * @since 2.0.0
 */
public interface Converters {

	OkStatusConverter OK_STATUS_CONVERTER = new OkStatusConverter();

	PredicateStatusConverter<Long> LONG_ZERO_ONE_STATUS_CONVERTER = new PredicateStatusConverter<>(
			(val)->val == 0 || val == 1);

	Converter<Long, Status> LONG_STATUS_CONVERTER = new PredicateStatusConverter<>((val)->val > 0);

	Converter<byte[], Status> BINARY_OK_STATUS_CONVERTER = new Converter<byte[], Status>() {

		@Nullable
		@Override
		public Status convert(final byte[] source){
			return Objects.equals(Constants.OK_BINARY, source) ? Status.SUCCESS : Status.FAILURE;
		}

	};

	PingResultConverter PING_RESULT_CONVERTER = new PingResultConverter();

	InfoConverter INFO_CONVERTER = new InfoConverter();

	BooleanStatusConverter BOOLEAN_STATUS_CONVERTER = new BooleanStatusConverter();

	ListConverter<String, byte[]> STRING_LIST_TO_STRING_LIST_CONVERTER = new ListConverter<>(SafeEncoder::encode);

	Converter<byte[][], String[]> BINARY_ARRAY_TO_STRING_ARRAY_CONVERTER = new Converter<byte[][], String[]>() {

		@Nullable
		@Override
		public String[] convert(final byte[][] source){
			final String[] result = new String[source.length];

			for(int i = 0; i < source.length; i++){
				result[i] = SafeEncoder.encode(source[i]);
			}

			return result;
		}

	};

}
