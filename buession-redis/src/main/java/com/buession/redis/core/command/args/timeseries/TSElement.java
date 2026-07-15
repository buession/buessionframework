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

import com.buession.redis.core.command.args.Argument;
import com.buession.redis.utils.ArgStringBuilder;
import com.buession.redis.utils.SafeEncoder;

/**
 * @param key
 * 		The key name for the time series
 * @param timestamp
 * 		The unix time (integer, in milliseconds) specifying the sample timestamp or * to set the sample
 * 		timestamp to the Unix time of the server's clock.
 * @param value
 * 		The numeric data value of the sample
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public record TSElement(String key, long timestamp, double value) implements Argument {

	/**
	 * 构造函数
	 *
	 * @param key
	 * 		The key name for the time series
	 * @param timestamp
	 * 		The unix time (integer, in milliseconds) specifying the sample timestamp or * to set the sample
	 * 		timestamp to the Unix time of the server's clock.
	 * @param value
	 * 		The numeric data value of the sample
	 */
	public TSElement(byte[] key, long timestamp, double value) {
		this(SafeEncoder.encode(key), timestamp, value);
	}

	/**
	 * Return the key name for the time series
	 *
	 * @return The key name for the time series
	 */
	public String getKey() {
		return key;
	}

	/**
	 * Return the unix time (integer, in milliseconds) specifying the sample timestamp or * to set the sample
	 * timestamp to the Unix time of the server's clock.
	 *
	 * @return The unix time (integer, in milliseconds) specifying the sample timestamp or * to set the sample
	 * timestamp to the Unix time of the server's clock.
	 */
	public long getTimestamp() {
		return timestamp;
	}

	/**
	 * Return the numeric data value of the sample
	 *
	 * @return The numeric data value of the sample
	 */
	public double getValue() {
		return value;
	}

	@Override
	public String toString() {
		return ArgStringBuilder.create().append(key()).append(timestamp()).append(value()).build();
	}

}
