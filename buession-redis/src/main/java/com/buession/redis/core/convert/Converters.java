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
 * | Copyright @ 2013-2020 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.core.convert;

import com.buession.core.converter.HashConverter;
import com.buession.core.converter.ListConverter;
import com.buession.lang.Status;
import com.buession.redis.utils.ReturnUtils;
import com.buession.redis.utils.SafeEncoder;
import org.springframework.core.convert.converter.Converter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Yong.Teng
 */
public abstract class Converters {

	public final static Converter<String, byte[]> stringToBinaryConverter(){
		return (value)->SafeEncoder.encode(value);
	}

	public final static HashConverter<String, String, byte[], byte[]> stringToBinaryHashConverter(){
		return new HashConverter<>((key)->SafeEncoder.encode(key), (value)->SafeEncoder.encode(value));
	}

	public final static ListConverter<String, byte[]> stringToBinaryListConverter(){
		return new ListConverter<>((value)->SafeEncoder.encode(value));
	}

	public final static Status okToStatus(final String result){
		return ReturnUtils.statusForOK(result);
	}

	public final static Converter<String, Status> okToStatusConverter(){
		return (source)->okToStatus(source);
	}

	public final static <V> List<V> collectionToList(final Collection<V> source){
		return source == null ? null : new ArrayList<>(source);
	}

	public final static Status pingResult(final String result){
		return ReturnUtils.statusForBool("PONG".equalsIgnoreCase(result));
	}

	public final static Converter<String, Status> pingResultConvert(){
		return (source)->pingResult(source);
	}

	public final static Converter<Integer, Status> positiveIntegerNumberToStatusConverter(){
		return (source)->ReturnUtils.statusForBool(source > 0);
	}

	public final static Converter<Long, Status> positiveLongNumberToStatusConverter(){
		return (source)->ReturnUtils.statusForBool(source > 0);
	}

	public final static Converter<Long, Status> equalOneToStatusConverter(){
		return (source)->ReturnUtils.statusForBool(source == 0);
	}

	public final static Converter<Boolean, Status> booleanToStatusConverter(){
		return (source)->ReturnUtils.statusForBool(source);
	}

	public final static <E extends Enum<E>> Converter<String, E> enumConverter(final Class<E> clazz){
		return (source)->ReturnUtils.enumValueOf(source, clazz);
	}

}
