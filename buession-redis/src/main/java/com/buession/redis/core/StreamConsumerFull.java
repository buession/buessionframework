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
import java.util.List;
import java.util.Map;

/**
 * @author Yong.Teng
 * @since 2.0.0
 */
public class StreamConsumerFull implements Serializable {

	private final static long serialVersionUID = -4058066725300830836L;

	private final String name;

	private final Long seenTime;

	private final Long pelCount;

	private final List<Long> pending;

	private final Map<String, Object> infos;

	public StreamConsumerFull(final String name, final Long seenTime, final Long pelCount, final List<Long> pending,
							  final Map<String, Object> infos){
		this.name = name;
		this.seenTime = seenTime;
		this.pelCount = pelCount;
		this.pending = pending;
		this.infos = infos;
	}

	public String getName(){
		return name;
	}

	public Long getSeenTime(){
		return seenTime;
	}

	public Long getPelCount(){
		return pelCount;
	}

	public List<Long> getPending(){
		return pending;
	}

	public Map<String, Object> getInfos(){
		return infos;
	}

	@Override
	public String toString(){
		return ObjectStringBuilder.create()
				.add("name", name)
				.add("seenTime", seenTime)
				.add("pelCount", pelCount)
				.add("pending", pending)
				.add("infos", infos)
				.build();
	}

}
