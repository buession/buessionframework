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
package com.buession.common.net;

/**
 * @author Yong.Teng
 */
public enum Protocol {

	FTP(21),

	SSH(22),

	SCP(22),

	TELNET(23),

	SMTP(25),

	TFTP(69),

	HTTP(80),

	POP3(110),

	HTTPS(443),

	MYSQL(3306),

	WINDOWS_RDP(3389),

	REDIS(6379),

	REDIS_SENTINEL(26379),

	MONGODB(27017);

	private int port;

	Protocol(int port){
		this.port = port;
	}

	public int getPort(){
		return port;
	}

}
