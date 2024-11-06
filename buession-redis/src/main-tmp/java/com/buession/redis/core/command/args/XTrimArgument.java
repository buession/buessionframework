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
package com.buession.redis.core.command.args;

/**
 * {@code XTRIM} 命令参数
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public class XTrimArgument {

	private MaxLenMinId<?> maxLenMinId;

	private ApproximateExactTrimming approximateExactTrimming;

	private Long limit;

	/**
	 * 构造函数
	 */
	public XTrimArgument() {
	}

	/**
	 * 构造函数
	 *
	 * @param maxLenMinId
	 * 		-
	 */
	public XTrimArgument(final MaxLenMinId<?> maxLenMinId) {
		this.maxLenMinId = maxLenMinId;
	}

	/**
	 * 构造函数
	 *
	 * @param limit
	 * 		-
	 */
	public XTrimArgument(final Long limit) {
		this.limit = limit;
	}

	/**
	 * 构造函数
	 *
	 * @param maxLenMinId
	 * 		-
	 * @param approximateExactTrimming
	 * 		-
	 */
	public XTrimArgument(final MaxLenMinId<?> maxLenMinId, final ApproximateExactTrimming approximateExactTrimming) {
		this(maxLenMinId);
		this.approximateExactTrimming = approximateExactTrimming;
	}

	/**
	 * 构造函数
	 *
	 * @param maxLenMinId
	 * 		-
	 * @param limit
	 * 		-
	 */
	public XTrimArgument(final MaxLenMinId<?> maxLenMinId, final Long limit) {
		this(maxLenMinId);
		this.limit = limit;
	}

	/**
	 * 构造函数
	 *
	 * @param maxLenMinId
	 * 		-
	 * @param approximateExactTrimming
	 * 		-
	 * @param limit
	 * 		-
	 */
	public XTrimArgument(final MaxLenMinId<?> maxLenMinId, final ApproximateExactTrimming approximateExactTrimming,
						 final Long limit) {
		this(maxLenMinId, approximateExactTrimming);
		this.limit = limit;
	}

	public MaxLenMinId<?> getMaxLenMinId() {
		return maxLenMinId;
	}

	public void setMaxLenMinId(final MaxLenMinId<?> maxLenMinId) {
		this.maxLenMinId = maxLenMinId;
	}

	public ApproximateExactTrimming getApproximateExactTrimming() {
		return approximateExactTrimming;
	}

	public void setApproximateExactTrimming(final ApproximateExactTrimming approximateExactTrimming) {
		this.approximateExactTrimming = approximateExactTrimming;
	}

	public Long getLimit() {
		return limit;
	}

	public void setLimit(final Long limit) {
		this.limit = limit;
	}

	@Override
	public String toString() {
		final ArgumentStringBuilder builder = ArgumentStringBuilder.create();

		if(maxLenMinId != null){
			builder.add(maxLenMinId.getSubCommand().name(), (approximateExactTrimming != null ?
					approximateExactTrimming.getValue() + " " : "") + maxLenMinId.getValue());
		}

		if(limit != null){
			builder.add("LIMIT", limit);
		}

		return builder.build();
	}

}
