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
package com.buession.redis.core;

import com.buession.redis.utils.ObjectStringBuilder;

/**
 * 时间序列数据
 *
 * @param timestamp
 * 		时间戳
 * @param value
 * 		值
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public record TimeSeriesElement(Long timestamp, Double value) {

	@Override
	public int hashCode() {
		return 31 * Long.hashCode(timestamp) + Long.hashCode(Double.doubleToLongBits(value));
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == this){
			return true;
		}

		if(obj instanceof TimeSeriesElement other){
			return this.timestamp == other.timestamp && this.value == other.value;
		}

		return false;
	}

	@Override
	public String toString() {
		return ObjectStringBuilder.create()
				.append("timestamp=").append(timestamp())
				.append("value=").append(value())
				.build();
	}

}
