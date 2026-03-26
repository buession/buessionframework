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

import com.buession.redis.core.command.args.timeseries.TSMRangeArgument;
import com.buession.redis.core.internal.convert.jedis.params.AggregationTypeConverter;
import com.buession.redis.utils.SafeEncoder;
import redis.clients.jedis.timeseries.TSMRangeParams;

import java.util.Optional;

/**
 * Jedis {@link TSMRangeParams} 扩展
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public final class JedisTSMRangeParams extends TSMRangeParams {

	/**
	 * 构造函数
	 *
	 * @param fromTimestamp
	 * 		起始时间戳
	 * @param toTimestamp
	 * 		结束时间戳
	 * @param filters
	 * 		一个或多个过滤表达式
	 */
	public JedisTSMRangeParams(final long fromTimestamp, final long toTimestamp, final String... filters) {
		super(fromTimestamp, toTimestamp);
		filter(filters);
	}

	/**
	 * 构造函数
	 *
	 * @param fromTimestamp
	 * 		起始时间戳
	 * @param toTimestamp
	 * 		结束时间戳
	 * @param filters
	 * 		一个或多个过滤表达式
	 */
	public JedisTSMRangeParams(final long fromTimestamp, final long toTimestamp, final byte[]... filters) {
		this(fromTimestamp, toTimestamp, SafeEncoder.encode(filters));
	}

	/**
	 * 构造函数
	 *
	 * @param fromTimestamp
	 * 		起始时间戳
	 * @param toTimestamp
	 * 		结束时间戳
	 * @param filters
	 * 		一个或多个过滤表达式
	 * @param tsMRangeArgument
	 *        {@link TSMRangeArgument}
	 */
	public JedisTSMRangeParams(final long fromTimestamp, final long toTimestamp, final String[] filters,
							   final TSMRangeArgument tsMRangeArgument) {
		this(fromTimestamp, toTimestamp, filters);

		if(tsMRangeArgument != null){
			if(Boolean.TRUE.equals(tsMRangeArgument.getLatest())){
				latest();
			}

			Optional.ofNullable(tsMRangeArgument.getFilterByTimestamps()).ifPresent(this::filterByTS);

			if(tsMRangeArgument.getFilterByValues() != null){
				filterByValues(tsMRangeArgument.getFilterByValues().getMin(),
						tsMRangeArgument.getFilterByValues().getMax());
			}

			if(tsMRangeArgument.getLabels() instanceof TSMRangeArgument.WithLabels){
				withLabels();
			}else if(tsMRangeArgument.getLabels() instanceof TSMRangeArgument.SelectedLabels){
				selectedLabels(((TSMRangeArgument.SelectedLabels) tsMRangeArgument.getLabels()).getValues());
			}

			if(tsMRangeArgument.getAggregation() != null){
				Optional.ofNullable(tsMRangeArgument.getAggregation().getAlign()).ifPresent(this::align);

				if(tsMRangeArgument.getAggregation().getType() != null){
					AggregationTypeConverter aggregationTypeConverter = new AggregationTypeConverter();
					this.aggregation(aggregationTypeConverter.convert(tsMRangeArgument.getAggregation().getType()),
							tsMRangeArgument.getAggregation().getBucketDuration());
				}

				if(Boolean.TRUE.equals(tsMRangeArgument.getAggregation().getEmpty())){
					empty();
				}
			}

			if(tsMRangeArgument.getGroupBy() != null){
				groupBy(tsMRangeArgument.getGroupBy().getLabel(), tsMRangeArgument.getGroupBy().getReduce());
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
	 * @param filters
	 * 		一个或多个过滤表达式
	 * @param tsMRangeArgument
	 *        {@link TSMRangeArgument}
	 */
	public JedisTSMRangeParams(final long fromTimestamp, final long toTimestamp, final byte[][] filters,
							   final TSMRangeArgument tsMRangeArgument) {
		this(fromTimestamp, toTimestamp, SafeEncoder.encode(filters), tsMRangeArgument);
	}

	/**
	 * 构造函数
	 *
	 * @param fromTimestamp
	 * 		起始时间戳
	 * @param toTimestamp
	 * 		结束时间戳
	 * @param filters
	 * 		一个或多个过滤表达式
	 * @param tsMRangeArgument
	 *        {@link TSMRangeArgument}
	 * @param count
	 * 		返回数量
	 */
	public JedisTSMRangeParams(final long fromTimestamp, final long toTimestamp, final String[] filters,
							   final TSMRangeArgument tsMRangeArgument, final int count) {
		this(fromTimestamp, toTimestamp, filters, tsMRangeArgument);
		count(count);
	}

	/**
	 * 构造函数
	 *
	 * @param fromTimestamp
	 * 		起始时间戳
	 * @param toTimestamp
	 * 		结束时间戳
	 * @param filters
	 * 		一个或多个过滤表达式
	 * @param tsMRangeArgument
	 *        {@link TSMRangeArgument}
	 * @param count
	 * 		返回数量
	 */
	public JedisTSMRangeParams(final long fromTimestamp, final long toTimestamp, final byte[][] filters,
							   final TSMRangeArgument tsMRangeArgument, final int count) {
		this(fromTimestamp, toTimestamp, filters, tsMRangeArgument);
		count(count);
	}

	/**
	 * 构造函数
	 *
	 * @param fromTimestamp
	 * 		起始时间戳
	 * @param toTimestamp
	 * 		结束时间戳
	 * @param filters
	 * 		一个或多个过滤表达式
	 * @param count
	 * 		返回数量
	 */
	public JedisTSMRangeParams(final long fromTimestamp, final long toTimestamp, final String[] filters,
							   final int count) {
		this(fromTimestamp, toTimestamp, filters);
		count(count);
	}

	/**
	 * 构造函数
	 *
	 * @param fromTimestamp
	 * 		起始时间戳
	 * @param toTimestamp
	 * 		结束时间戳
	 * @param filters
	 * 		一个或多个过滤表达式
	 * @param count
	 * 		返回数量
	 */
	public JedisTSMRangeParams(final long fromTimestamp, final long toTimestamp, final byte[][] filters,
							   final int count) {
		this(fromTimestamp, toTimestamp, filters);
		count(count);
	}

}
