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
 * | Copyright @ 2013-2020 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.spring;

import com.buession.core.utils.Assert;
import com.buession.redis.core.Server;

/**
 * @author Yong.Teng
 */
public class RedisConnectionFactory {

	private String host = Server.DEFAULT_HOST;

	private int port = Server.DEFAULT_PORT;

	private String password;

	private int database = Server.DEFAULT_DATABASE;

	private int timeout = Server.DEFAULT_TIMEOUT;

	private boolean useSsl;

	private boolean usePool = true;

	private String clientName;

	public String getHost(){
		return host;
	}

	public void setHost(String host){
		this.host = host;
	}

	public int getPort(){
		return port;
	}

	public void setPort(int port){
		this.port = port;
	}

	public String getPassword(){
		return password;
	}

	public void setPassword(String password){
		this.password = password;
	}

	public int getDatabase(){
		return database;
	}

	public void setDatabase(int database){
		Assert.isNegative(database, "invalid DB index (a positive index required)");
		this.database = database;
	}

	public int getTimeout(){
		return timeout;
	}

	public void setTimeout(int timeout){
		this.timeout = timeout;
	}

	public boolean isUseSsl(){
		return useSsl;
	}

	public void setUseSsl(boolean useSsl){
		this.useSsl = useSsl;
	}

	public boolean isUsePool(){
		return getUsePool();
	}

	public boolean getUsePool(){
		return usePool;
	}

	public void setUsePool(boolean usePool){
		this.usePool = usePool;
	}

	public String getClientName(){
		return clientName;
	}

	public void setClientName(String clientName){
		this.clientName = clientName;
	}

}
