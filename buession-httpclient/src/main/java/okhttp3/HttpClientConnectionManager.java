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
 * | Copyright @ 2013-2023 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package okhttp3;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * okhttp(3) 连接管理器
 *
 * @author Yong.Teng
 * @since 1.3.1
 */
public class HttpClientConnectionManager implements Closeable {

	private ConnectionPool connectionPool;

	/**
	 * 最大连接数
	 *
	 * @since 2.0.1
	 */
	private int maxConnections = 5;

	/**
	 * 闲连接存活时长，单位：毫秒
	 *
	 * @since 2.0.1
	 */
	private int idleConnectionTime = 5 * 60 * 1000;

	public HttpClientConnectionManager(){
	}

	public HttpClientConnectionManager(ConnectionPool connectionPool){
		this.connectionPool = connectionPool;
	}

	public ConnectionPool getConnectionPool(){
		if(connectionPool == null){
			connectionPool = new ConnectionPool(maxConnections, idleConnectionTime, TimeUnit.MILLISECONDS);
		}

		return connectionPool;
	}

	public void setConnectionPool(ConnectionPool connectionPool){
		this.connectionPool = connectionPool;
	}

	/**
	 * 设置最大链接数
	 *
	 * @param maxConnections
	 * 		最大链接数
	 *
	 * @since 2.0.1
	 */
	public void setMaxConnections(int maxConnections){
		this.maxConnections = maxConnections;
	}

	/**
	 * 设置闲连接存活时长，单位：毫秒
	 *
	 * @param idleConnectionTime
	 * 		闲连接存活时长
	 */
	public void setIdleConnectionTime(int idleConnectionTime){
		this.idleConnectionTime = idleConnectionTime;
	}

	@Override
	public void close() throws IOException{

	}

}
