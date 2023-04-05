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
package com.buession.httpclient;

import com.buession.core.utils.Assert;
import com.buession.httpclient.conn.ConnectionManager;
import com.buession.httpclient.core.ProtocolVersion;
import com.buession.httpclient.exception.RequestException;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * @author Yong.Teng
 * @since 2.3.0
 */
abstract class AbstractBaseHttpClient implements IBaseHttpClient {

	private ConnectionManager connectionManager;

	private ProtocolVersion httpVersion;

	/**
	 * 构造函数
	 */
	public AbstractBaseHttpClient(){
	}

	/**
	 * 构造函数
	 *
	 * @param connectionManager
	 * 		连接管理器
	 */
	public AbstractBaseHttpClient(ConnectionManager connectionManager){
		this.connectionManager = connectionManager;
	}

	@Override
	public ConnectionManager getConnectionManager(){
		return connectionManager;
	}

	@Override
	public void setConnectionManager(ConnectionManager connectionManager){
		this.connectionManager = connectionManager;
	}

	@Override
	public ProtocolVersion getHttpVersion(){
		return httpVersion;
	}

	@Override
	public void setHttpVersion(ProtocolVersion httpVersion){
		this.httpVersion = httpVersion;
	}

	protected static URL requiredURL(final URL url){
		Assert.isNull(url, "Request URL cloud not be null.");
		return url;
	}

	protected static URI URL2URI(final URL url) throws URISyntaxException{
		return requiredURL(url).toURI();
	}

	protected static <T> T execute(Execute<T> execute) throws IOException, RequestException{
		try{
			return execute.exec();
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

	protected static void asyncExecute(AsyncExecute execute) throws IOException, RequestException{
		try{
			execute.exec();
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

	protected interface Execute<T> {

		T exec() throws URISyntaxException, IOException, RequestException;

	}

	protected interface AsyncExecute {

		void exec() throws URISyntaxException, IOException, RequestException;

	}

}
