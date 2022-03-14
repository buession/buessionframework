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
package com.buession.redis.utils;

import com.buession.core.converter.Converter;
import com.buession.redis.transaction.TxResult;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Yong.Teng
 * @since 1.2.1
 */
public class TxResultsUtils {

	private final static ThreadLocal<Map<Integer, TxResult>> txResults = new ThreadLocal<>();

	public static Map<Integer, TxResult> getTxResults(){
		Map<Integer, TxResult> txResult = txResults.get();

		if(txResult == null){
			txResult = new LinkedHashMap<>(16, 0.8F);
			txResults.set(txResult);
		}

		return txResult;
	}

	public static <S, T> void put(AtomicInteger index, Converter<S, T> converter, Class... paramTypes){
		put(index.get(), converter, paramTypes);
	}

	public static <S, T> void put(int index, Converter<S, T> converter, Class... paramTypes){
		getTxResults().put(index, new TxResult<>(converter, paramTypes));
	}

	public static List<Object> deserializeMixedResults(AtomicInteger index, List<Object> result){
		Map<Integer, TxResult> cache = txResults.get();

		if(cache == null){
			return result;
		}

		TxResult<?, ?> txResult;

		for(int i = 0; i < index.get(); i++){
			txResult = cache.get(i);
			if(txResult == null){
				continue;
			}

			Method method = ReflectionUtils.findMethod(txResult.getConverter().getClass(), "convert", txResult.getParamTypes());

			if(method != null){
				Object value = result.get(i);
				Object ret = ReflectionUtils.invokeMethod(method, txResult.getConverter(), value);

				result.set(i, ret);
			}
		}

		return result;
	}

	public static void remove(){
		txResults.remove();
	}

}
