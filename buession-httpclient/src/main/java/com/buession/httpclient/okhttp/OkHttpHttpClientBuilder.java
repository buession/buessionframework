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
package com.buession.httpclient.okhttp;

import com.buession.core.converter.mapper.PropertyMapper;
import com.buession.httpclient.conn.OkHttpClientConnectionManager;
import com.buession.httpclient.core.AbstractHttpClientBuilder;
import com.buession.httpclient.core.Configuration;
import com.buession.net.ssl.SslConfiguration;
import okhttp3.HttpClientBuilder;
import okhttp3.OkHttpClient;

import java.util.function.Consumer;

/**
 * okhttp3 Http Client Builder
 *
 * @author Yong.Teng
 * @since 2.3.0
 */
public class OkHttpHttpClientBuilder extends AbstractHttpClientBuilder<HttpClientBuilder, OkHttpClientConnectionManager,
		OkHttpClient> {

	public OkHttpHttpClientBuilder(final OkHttpClientConnectionManager connectionManager){
		super(connectionManager);
	}

	@Override
	public OkHttpClient build(Consumer<HttpClientBuilder> consumer){
		final PropertyMapper propertyMapper = PropertyMapper.get().alwaysApplyingWhenNonNull();
		final Configuration configuration = connectionManager.getConfiguration();
		final SslConfiguration sslConfiguration = configuration.getSslConfiguration();
		final HttpClientBuilder builder = HttpClientBuilder.create()
				.setConnectionManager(connectionManager.getClientConnectionManager())
				.setRetryOnConnectionFailure(configuration.getRetryOnConnectionFailure())
				.setConnectTimeout(configuration.getConnectTimeout())
				.setReadTimeout(configuration.getReadTimeout())
				.setWriteTimeout(configuration.getWriteTimeout());

		propertyMapper.from(configuration.isAllowRedirects()).to(builder::setFollowRedirects);

		if(sslConfiguration != null){
			propertyMapper.from(sslConfiguration.getSslSocketFactory()).to(builder::setSSLSocketFactory);
			propertyMapper.from(sslConfiguration.getHostnameVerifier()).to(builder::setSSLHostnameVerifier);
			propertyMapper.from(sslConfiguration.getSslContext()).to(builder::setSSLContext);
		}

		if(consumer != null){
			consumer.accept(builder);
		}

		return builder.build();
	}

}
