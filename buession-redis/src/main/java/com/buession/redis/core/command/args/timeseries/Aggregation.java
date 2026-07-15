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
package com.buession.redis.core.command.args.timeseries;

import com.buession.redis.core.AggregationType;
import com.buession.redis.utils.ArgStringBuilder;
import com.buession.redis.utils.SafeEncoder;

/**
 *
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public class Aggregation {

	private Long align;

	private AggregationType type;

	private Long bucketDuration;

	private byte[] bucketTimestamp;

	private Boolean empty;

	public Aggregation(final AggregationType type, final long bucketDuration) {
		this.type = type;
		this.bucketDuration = bucketDuration;
	}

	public Aggregation(final long align, final AggregationType type, final long bucketDuration,
					   final byte[] bucketTimestamp, final boolean empty) {
		this(type, bucketDuration);
		this.align = align;
		this.bucketTimestamp = bucketTimestamp;
		this.empty = empty;
	}

	public Long getAlign() {
		return align;
	}

	public Aggregation setAlign(long align) {
		this.align = align;
		return this;
	}

	public AggregationType getType() {
		return type;
	}

	public Aggregation setType(AggregationType type) {
		this.type = type;
		return this;
	}

	public Long getBucketDuration() {
		return bucketDuration;
	}

	public Aggregation setBucketDuration(long bucketDuration) {
		this.bucketDuration = bucketDuration;
		return this;
	}

	public byte[] getBucketTimestamp() {
		return bucketTimestamp;
	}

	public Aggregation setBucketTimestamp(byte[] bucketTimestamp) {
		this.bucketTimestamp = bucketTimestamp;
		return this;
	}

	public Aggregation bucketTimestampLow() {
		return setBucketTimestamp(SafeEncoder.encode("-"));
	}

	public Aggregation bucketTimestampMid() {
		return setBucketTimestamp(SafeEncoder.encode("~"));
	}

	public Aggregation bucketTimestampHigh() {
		return setBucketTimestamp(SafeEncoder.encode("+"));
	}

	public Boolean isEmpty() {
		return getEmpty();
	}

	public Boolean getEmpty() {
		return empty;
	}

	public Aggregation empty() {
		return setEmpty(true);
	}

	public Aggregation setEmpty(boolean empty) {
		this.empty = empty;
		return this;
	}

	@Override
	public String toString() {
		return ArgStringBuilder.create().add("ALIGN", getAlign()).add("AGGREGATION", getType())
				.append(getBucketDuration()).add("BUCKETTIMESTAMP", getBucketTimestamp())
				.append(Boolean.TRUE.equals(getEmpty()) ? "EMPTY" : "").build();
	}

}
