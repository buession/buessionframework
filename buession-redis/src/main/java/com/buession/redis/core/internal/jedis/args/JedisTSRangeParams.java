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
package com.buession.redis.core.internal.jedis.args;

import com.buession.redis.core.command.args.timeseries.TSRangeArgument;
import com.buession.redis.core.internal.convert.jedis.params.AggregationTypeConverter;
import redis.clients.jedis.timeseries.TSRangeParams;

import java.util.Optional;

/**
 * Jedis {@link TSRangeParams} 扩展
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public final class JedisTSRangeParams extends TSRangeParams {

	/**
	 * 构造函数
	 *
	 * @param fromTimestamp
	 * 		起始时间戳
	 * @param toTimestamp
	 * 		结束时间戳
	 */
	public JedisTSRangeParams(final long fromTimestamp, final long toTimestamp) {
		super(fromTimestamp, toTimestamp);
	}

	/**
	 * 构造函数
	 *
	 * @param fromTimestamp
	 * 		起始时间戳
	 * @param toTimestamp
	 * 		结束时间戳
	 * @param tsRangeArgument
	 *        {@link TSRangeArgument}
	 */
	public JedisTSRangeParams(final long fromTimestamp, final long toTimestamp, final TSRangeArgument tsRangeArgument) {
		this(fromTimestamp, toTimestamp);

		if(tsRangeArgument != null){
			if(Boolean.TRUE.equals(tsRangeArgument.getLatest())){
				latest();
			}

			Optional.ofNullable(tsRangeArgument.getFilterByTimestamps()).ifPresent(this::filterByTS);

			if(tsRangeArgument.getFilterByValues() != null){
				filterByValues(tsRangeArgument.getFilterByValues().min(),
						tsRangeArgument.getFilterByValues().max());
			}

			if(tsRangeArgument.getAggregation() != null){
				Optional.ofNullable(tsRangeArgument.getAggregation().getAlign()).ifPresent(this::align);

				if(tsRangeArgument.getAggregation().getType() != null){
					AggregationTypeConverter aggregationTypeConverter = new AggregationTypeConverter();
					this.aggregation(aggregationTypeConverter.convert(tsRangeArgument.getAggregation().getType()),
							tsRangeArgument.getAggregation().getBucketDuration());
				}

				if(Boolean.TRUE.equals(tsRangeArgument.getAggregation().getEmpty())){
					empty();
				}
			}
		}
	}

	/**
	 * 构造函数
	 *
	 * @param fromTimestamp
	 * 		起始时间戳
	 * @param toTimestamp
	 * 		结束时间戳
	 * @param tsRangeArgument
	 *        {@link TSRangeArgument}
	 * @param count
	 * 		返回数量
	 */
	public JedisTSRangeParams(final long fromTimestamp, final long toTimestamp, final TSRangeArgument tsRangeArgument,
	                          final int count) {
		this(fromTimestamp, toTimestamp, tsRangeArgument);
		count(count);
	}

	/**
	 * 构造函数
	 *
	 * @param fromTimestamp
	 * 		起始时间戳
	 * @param toTimestamp
	 * 		结束时间戳
	 * @param count
	 * 		返回数量
	 */
	public JedisTSRangeParams(final long fromTimestamp, final long toTimestamp, final int count) {
		this(fromTimestamp, toTimestamp);
		count(count);
	}

}
