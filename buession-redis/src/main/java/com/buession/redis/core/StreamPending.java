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

import com.buession.redis.utils.ObjectStringBuilder;

import java.io.Serializable;

/**
 * @author Yong.Teng
 * @since 2.0.0
 */
public class StreamPending implements Serializable {

	private static final long serialVersionUID = -9058837565507153831L;

	private final StreamEntryId id;

	private final String consumerName;

	private final long idleTime;

	private final long deliveredTimes;

	public StreamPending(final StreamEntryId id, final String consumerName, final long idleTime,
						 final long deliveredTimes){
		this.id = id;
		this.consumerName = consumerName;
		this.idleTime = idleTime;
		this.deliveredTimes = deliveredTimes;
	}

	public StreamEntryId getId(){
		return id;
	}

	public String getConsumerName(){
		return consumerName;
	}

	public long getIdleTime(){
		return idleTime;
	}

	public long getDeliveredTimes(){
		return deliveredTimes;
	}

	@Override
	public String toString(){
		return ObjectStringBuilder.create()
				.add("id", id)
				.add("consumerName", consumerName)
				.add("idleTime", idleTime)
				.add("deliveredTimes", deliveredTimes)
				.build();
	}

}
