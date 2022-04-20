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
package com.buession.redis.core;

import java.io.Serializable;
import java.util.Map;
import java.util.StringJoiner;

/**
 * @author Yong.Teng
 * @since 2.0.0
 */
public class StreamPendingSummary implements Serializable {

	private final static long serialVersionUID = 4352578196582945851L;

	private final long total;

	private final StreamEntryId minId;

	private final StreamEntryId maxId;

	private final Map<String, Long> consumerMessageCount;

	public StreamPendingSummary(long total, StreamEntryId minId, StreamEntryId maxId,
								Map<String, Long> consumerMessageCount){
		this.total = total;
		this.minId = minId;
		this.maxId = maxId;
		this.consumerMessageCount = consumerMessageCount;
	}

	public long getTotal(){
		return total;
	}

	public StreamEntryId getMinId(){
		return minId;
	}

	public StreamEntryId getMaxId(){
		return maxId;
	}

	public Map<String, Long> getConsumerMessageCount(){
		return consumerMessageCount;
	}

	@Override
	public String toString(){
		return new StringJoiner(", ", "[", "]")
				.add("total=" + total)
				.add("minId=" + minId)
				.add("maxId=" + maxId)
				.add("consumerMessageCount=" + consumerMessageCount)
				.toString();
	}

}
