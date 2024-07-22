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

import com.buession.redis.core.StreamEntryId;

/**
 * {@code XADD} 命令参数
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public class XAddArgument {

	private MaxLenMinId<?> maxLenMinId;

	private Boolean noMkStream;

	private Long limit;

	/**
	 * 构造函数
	 */
	public XAddArgument() {

	}

	/**
	 * 构造函数
	 *
	 * @param maxLenMinId
	 * 		-
	 */
	public XAddArgument(final MaxLenMinId<?> maxLenMinId) {
		this.maxLenMinId = maxLenMinId;
	}

	/**
	 * 构造函数
	 *
	 * @param noMkStream
	 * 		-
	 */
	public XAddArgument(final Boolean noMkStream) {
		this.noMkStream = noMkStream;
	}

	/**
	 * 构造函数
	 *
	 * @param limit
	 * 		-
	 */
	public XAddArgument(final Long limit) {
		this.limit = limit;
	}

	/**
	 * 构造函数
	 *
	 * @param maxLenMinId
	 * 		-
	 * @param noMkStream
	 * 		-
	 */
	public XAddArgument(final MaxLenMinId<?> maxLenMinId, final Boolean noMkStream) {
		this(maxLenMinId);
		this.noMkStream = noMkStream;
	}

	/**
	 * 构造函数
	 *
	 * @param maxLenMinId
	 * 		-
	 * @param limit
	 * 		-
	 */
	public XAddArgument(final MaxLenMinId<?> maxLenMinId, final Long limit) {
		this(maxLenMinId);
		this.limit = limit;
	}

	/**
	 * 构造函数
	 *
	 * @param noMkStream
	 * 		-
	 * @param limit
	 * 		-
	 */
	public XAddArgument(final Boolean noMkStream, final Long limit) {
		this(noMkStream);
		this.limit = limit;
	}

	/**
	 * 构造函数
	 *
	 * @param maxLenMinId
	 * 		-
	 * @param noMkStream
	 * 		-
	 * @param limit
	 * 		-
	 */
	public XAddArgument(final MaxLenMinId<?> maxLenMinId, final Boolean noMkStream, final Long limit) {
		this(maxLenMinId, noMkStream);
		this.limit = limit;
	}

	public MaxLenMinId<?> getMaxLenMinId() {
		return maxLenMinId;
	}

	public void setMaxLenMinId(MaxLenMinId<?> maxLenMinId) {
		this.maxLenMinId = maxLenMinId;
	}

	public Boolean isNoMkStream() {
		return getNoMkStream();
	}

	public Boolean getNoMkStream() {
		return noMkStream;
	}

	public void noMkStream() {
		this.noMkStream = true;
	}

	public void setNoMkStream(Boolean noMkStream) {
		this.noMkStream = noMkStream;
	}

	public Long getLimit() {
		return limit;
	}

	public void setLimit(Long limit) {
		this.limit = limit;
	}

	@Override
	public String toString() {
		final ArgumentStringBuilder builder = ArgumentStringBuilder.create();

		if(maxLenMinId != null){
			builder.append(maxLenMinId.toString());
		}

		if(Boolean.TRUE.equals(noMkStream)){
			builder.append("NOMKSTREAM");
		}

		if(limit != null){
			builder.add("LIMIT", limit);
		}

		return builder.build();
	}

	public interface MaxLenMinId<T> {

		ApproximateExactTrimming getApproximateExactTrimming();

		T getValue();

	}

	public final static class MaxLen implements MaxLenMinId<Long> {

		private final ApproximateExactTrimming approximateExactTrimming;

		private final Long value;

		public MaxLen(final ApproximateExactTrimming approximateExactTrimming, final Long value) {
			this.approximateExactTrimming = approximateExactTrimming;
			this.value = value;
		}

		public ApproximateExactTrimming getApproximateExactTrimming() {
			return approximateExactTrimming;
		}

		@Override
		public Long getValue() {
			return value;
		}

		@Override
		public String toString() {
			final ArgumentStringBuilder builder = ArgumentStringBuilder.create();

			builder.add("MAXLEN", approximateExactTrimming.getValue() + " " + value);

			return builder.build();
		}

	}

	public final static class MinId implements MaxLenMinId<StreamEntryId> {

		private final ApproximateExactTrimming approximateExactTrimming;

		private final StreamEntryId value;

		public MinId(final ApproximateExactTrimming approximateExactTrimming, final StreamEntryId value) {
			this.approximateExactTrimming = approximateExactTrimming;
			this.value = value;
		}

		public ApproximateExactTrimming getApproximateExactTrimming() {
			return approximateExactTrimming;
		}

		@Override
		public StreamEntryId getValue() {
			return value;
		}

		@Override
		public String toString() {
			final ArgumentStringBuilder builder = ArgumentStringBuilder.create();

			builder.add("MINID", approximateExactTrimming.getValue() + " " + value);

			return builder.build();
		}

	}

	public enum IdType {
		MAXLEN,

		MINID
	}

	public enum ApproximateExactTrimming {
		APPROXIMATE("~"),

		EXACT("=");

		private final String value;

		ApproximateExactTrimming(final String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		@Override
		public String toString() {
			return getValue();
		}

	}

}
