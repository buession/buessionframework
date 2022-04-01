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
import java.util.Map;

/**
 * @author Yong.Teng
 * @since 2.0.0
 */
public class AclLog implements Serializable {

	private final static long serialVersionUID = -1362912160889551004L;

	private long count;

	private String reason;

	private String context;

	private String object;

	private String username;

	private String ageSeconds;

	private Client clientInfo;

	private Map<String, Object> logEntry;

	public AclLog(){
	}

	public AclLog(final long count, final String reason, final String context, final String object,
				  final String username, final String ageSeconds, final Client clientInfo,
				  final Map<String, Object> logEntry){
		this.count = count;
		this.reason = reason;
		this.context = context;
		this.object = object;
		this.username = username;
		this.ageSeconds = ageSeconds;
		this.clientInfo = clientInfo;
		this.logEntry = logEntry;
	}

	public long getCount(){
		return count;
	}

	public void setCount(long count){
		this.count = count;
	}

	public String getReason(){
		return reason;
	}

	public void setReason(String reason){
		this.reason = reason;
	}

	public String getContext(){
		return context;
	}

	public void setContext(String context){
		this.context = context;
	}

	public String getObject(){
		return object;
	}

	public void setObject(String object){
		this.object = object;
	}

	public String getUsername(){
		return username;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getAgeSeconds(){
		return ageSeconds;
	}

	public void setAgeSeconds(String ageSeconds){
		this.ageSeconds = ageSeconds;
	}

	public Client getClientInfo(){
		return clientInfo;
	}

	public void setClientInfo(Client clientInfo){
		this.clientInfo = clientInfo;
	}

	public Map<String, Object> getLogEntry(){
		return logEntry;
	}

	public void setLogEntry(Map<String, Object> logEntry){
		this.logEntry = logEntry;
	}

	@Override
	public String toString(){
		return "AclLog{" +
				"count=" + count +
				", reason='" + reason + '\'' +
				", context='" + context + '\'' +
				", object='" + object + '\'' +
				", username='" + username + '\'' +
				", ageSeconds='" + ageSeconds + '\'' +
				", clientInfo=" + clientInfo +
				", logEntry=" + logEntry +
				'}';
	}
}
