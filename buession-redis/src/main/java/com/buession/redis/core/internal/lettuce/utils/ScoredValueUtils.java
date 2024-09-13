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
package com.buession.redis.core.internal.lettuce.utils;

import com.buession.redis.utils.SafeEncoder;
import io.lettuce.core.ScoredValue;

import java.util.Map;

/**
 * Lettuce {@link  ScoredValue} 工具类
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public class ScoredValueUtils {

	protected ScoredValueUtils() {

	}

	@SuppressWarnings({"unchecked"})
	public static ScoredValue<byte[]>[] fromStringMap(final Map<String, Double> values) {
		if(values == null){
			return null;
		}else{
			final ScoredValue<byte[]>[] result = new ScoredValue[values.size()];
			int i = 0;

			for(Map.Entry<String, Double> e : values.entrySet()){
				result[i++] = e.getValue() == null ? ScoredValue.empty() : ScoredValue.just(e.getValue(),
						SafeEncoder.encode(e.getKey()));
			}

			return result;
		}
	}

	@SuppressWarnings({"unchecked"})
	public static ScoredValue<byte[]>[] fromBinaryMap(final Map<byte[], Double> values) {
		if(values == null){
			return null;
		}else{
			final ScoredValue<byte[]>[] result = new ScoredValue[values.size()];
			int i = 0;

			for(Map.Entry<byte[], Double> e : values.entrySet()){
				result[i++] = e.getValue() == null ? ScoredValue.empty() : ScoredValue.just(e.getValue(),
						e.getKey());
			}

			return result;
		}
	}

}
