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
package com.buession.httpclient.apache;

import com.buession.core.converter.mapper.PropertyMapper;
import com.buession.httpclient.conn.ApacheNioClientConnectionManager;
import com.buession.httpclient.core.AbstractHttpClientBuilder;
import com.buession.httpclient.core.Configuration;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.nio.client.HttpAsyncClient;

import java.util.function.Consumer;

/**
 * Apache Http Async Client Builder
 *
 * @author Yong.Teng
 * @since 2.3.0
 */
public class ApacheHttpAsyncClientBuilder
		extends AbstractHttpClientBuilder<HttpAsyncClientBuilder, ApacheNioClientConnectionManager,
		HttpAsyncClient> {

	public ApacheHttpAsyncClientBuilder(final ApacheNioClientConnectionManager connectionManager){
		super(connectionManager);
	}

	@Override
	public HttpAsyncClient build(Consumer<HttpAsyncClientBuilder> consumer){
		final PropertyMapper propertyMapper = PropertyMapper.get().alwaysApplyingWhenNonNull();
		final Configuration.SSLConfiguration sslConfiguration =
				connectionManager.getConfiguration().getSslConfiguration();
		final HttpAsyncClientBuilder builder = HttpAsyncClientBuilder.create()
				.setConnectionManager(connectionManager.getClientConnectionManager());

		propertyMapper.from(connectionManager.getConnectionManagerShared())
				.to(builder::setConnectionManagerShared);

		if(sslConfiguration != null){
			propertyMapper.from(sslConfiguration.getHostnameVerifier()).to(builder::setSSLHostnameVerifier);
			propertyMapper.from(sslConfiguration.getSslContext()).to(builder::setSSLContext);
		}

		if(consumer != null){
			consumer.accept(builder);
		}

		final CloseableHttpAsyncClient closeableHttpAsyncClient = builder.build();

		closeableHttpAsyncClient.start();

		return builder.build();
	}

}
