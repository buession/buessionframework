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
import java.util.List;
import java.util.StringJoiner;

/**
 * @author Yong.Teng
 * @since 2.0.0
 */
public class SlowLog implements Serializable {

	private static final long serialVersionUID = 4948377124212583969L;

	private final long id;

	private final long timeStamp;

	private final long executionTime;

	private final List<String> args;

	private final Client client;

	private final String clientName;

	public SlowLog(final long id, final long timeStamp, final long executionTime, final List<String> args,
				   final Client client, final String clientName){
		this.id = id;
		this.timeStamp = timeStamp;
		this.executionTime = executionTime;
		this.args = args;
		this.client = client;
		this.clientName = clientName;
	}

	public long getId(){
		return id;
	}

	public long getTimeStamp(){
		return timeStamp;
	}

	public long getExecutionTime(){
		return executionTime;
	}

	public List<String> getArgs(){
		return args;
	}

	public Client getClient(){
		return client;
	}

	public String getClientName(){
		return clientName;
	}

	@Override
	public String toString(){
		return new StringJoiner(", ", "{", "}")
				.add("id=" + id)
				.add("timeStamp=" + timeStamp)
				.add("executionTime=" + executionTime)
				.add("args=" + args)
				.add("client=" + client)
				.add("clientName=" + clientName)
				.toString();
	}

}
