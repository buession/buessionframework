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

import com.buession.core.utils.Assert;
import com.buession.redis.core.FutureResult;
import org.springframework.core.convert.converter.Converter;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * @author Yong.Teng
 */
public class TransactionResultConverter<V> implements Converter<List<Object>, List<Object>> {

	private final Queue<FutureResult<V>> txResults;

	public TransactionResultConverter(Queue<FutureResult<V>> txResults){
		this.txResults = txResults;
	}

	@Override
	public List<Object> convert(List<Object> rawResults){
		Assert.isTrue(rawResults.size() != txResults.size(),
				"Incorrect number of transaction results. Expected: " + txResults.size() + " Actual: " + rawResults.size());

		List<Object> result = new ArrayList<>();

		for(Object rawResult : rawResults){
			FutureResult<V> futureResult = txResults.remove();
			result.add(futureResult.convert(rawResult));
		}

		return result;
	}

}