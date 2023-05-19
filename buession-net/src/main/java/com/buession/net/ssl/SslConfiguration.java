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
package com.buession.net.ssl;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocketFactory;

/**
 * SSL 配置
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public class SslConfiguration {

	/**
	 * {@link SSLContext}
	 *
	 * @since 2.3.0
	 */
	private SSLContext sslContext;

	/**
	 * {@link SSLSocketFactory}
	 */
	private SSLSocketFactory sslSocketFactory;

	/**
	 * {@link SSLParameters}
	 */
	private SSLParameters sslParameters;

	/**
	 * {@link HostnameVerifier}
	 */
	private HostnameVerifier hostnameVerifier;

	/**
	 * 构造函数
	 */
	public SslConfiguration(){
	}

	/**
	 * 构造函数
	 *
	 * @param sslSocketFactory
	 *        {@link SSLSocketFactory}
	 * @param sslParameters
	 *        {@link SSLParameters}
	 * @param hostnameVerifier
	 *        {@link HostnameVerifier}
	 */
	public SslConfiguration(SSLSocketFactory sslSocketFactory, SSLParameters sslParameters,
							HostnameVerifier hostnameVerifier){
		this.sslSocketFactory = sslSocketFactory;
		this.sslParameters = sslParameters;
		this.hostnameVerifier = hostnameVerifier;
	}

	/**
	 * 构造函数
	 *
	 * @param sslContext
	 *        {@link SSLContext}
	 * @param sslSocketFactory
	 *        {@link SSLSocketFactory}
	 * @param sslParameters
	 *        {@link SSLParameters}
	 * @param hostnameVerifier
	 *        {@link HostnameVerifier}
	 *
	 * @since 2.3.0
	 */
	public SslConfiguration(SSLContext sslContext, SSLSocketFactory sslSocketFactory, SSLParameters sslParameters,
							HostnameVerifier hostnameVerifier){
		this(sslSocketFactory, sslParameters, hostnameVerifier);
		this.sslContext = sslContext;
	}

	/**
	 * 返回 {@link SSLContext}
	 *
	 * @return {@link SSLContext}
	 *
	 * @since 2.3.0
	 */
	public SSLContext getSslContext(){
		return sslContext;
	}

	/**
	 * 设置 {@link SSLContext}
	 *
	 * @param sslContext
	 *        {@link SSLContext}
	 *
	 * @since 2.3.0
	 */
	public void setSslContext(SSLContext sslContext){
		this.sslContext = sslContext;
	}

	/**
	 * 返回 {@link SSLSocketFactory}
	 *
	 * @return {@link SSLSocketFactory}
	 */
	public SSLSocketFactory getSslSocketFactory(){
		return sslSocketFactory;
	}

	/**
	 * 设置 {@link SSLSocketFactory}
	 *
	 * @param sslSocketFactory
	 *        {@link SSLSocketFactory}
	 */
	public void setSslSocketFactory(final SSLSocketFactory sslSocketFactory){
		this.sslSocketFactory = sslSocketFactory;
	}

	/**
	 * 返回 {@link SSLParameters}
	 *
	 * @return {@link SSLParameters}
	 */
	public SSLParameters getSslParameters(){
		return sslParameters;
	}

	/**
	 * 设置 {@link SSLParameters}
	 *
	 * @param sslParameters
	 *        {@link SSLParameters}
	 */
	public void setSslParameters(final SSLParameters sslParameters){
		this.sslParameters = sslParameters;
	}

	/**
	 * 返回 {@link HostnameVerifier}
	 *
	 * @return {@link HostnameVerifier}
	 */
	public HostnameVerifier getHostnameVerifier(){
		return hostnameVerifier;
	}

	/**
	 * 设置 {@link HostnameVerifier}
	 *
	 * @param hostnameVerifier
	 *        {@link HostnameVerifier}
	 */
	public void setHostnameVerifier(final HostnameVerifier hostnameVerifier){
		this.hostnameVerifier = hostnameVerifier;
	}

}
