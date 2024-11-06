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
 * | Copyright @ 2013-2024 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.core.internal.lettuce;

import com.buession.core.converter.mapper.PropertyMapper;
import com.buession.core.validator.Validate;
import com.buession.net.ssl.SslConfiguration;
import com.buession.redis.client.connection.datasource.lettuce.LettuceRedisDataSource;
import io.lettuce.core.DefaultLettuceClientConfig;

/**
 * @author Yong.Teng
 * @since 3.0.0
 */
public class LettuceClientConfigBuilder {

	private final DefaultLettuceClientConfig.Builder builder = DefaultLettuceClientConfig.builder();

	private final PropertyMapper propertyMapper = PropertyMapper.get().alwaysApplyingWhenHasText();

	private LettuceClientConfigBuilder(final LettuceRedisDataSource dataSource,
									   final SslConfiguration sslConfiguration) {
		builder.connectionTimeoutMillis(dataSource.getConnectTimeout())
				.socketTimeoutMillis(dataSource.getSoTimeout())
				.ssl(sslConfiguration != null);

		propertyMapper.from(dataSource.getClientName()).to(builder::clientName);

		if(Validate.hasText(dataSource.getPassword())){
			propertyMapper.from(dataSource.getUsername()).to(builder::user);
			builder.password(dataSource.getPassword());
		}

		if(sslConfiguration != null){
			builder.sslSocketFactory(sslConfiguration.getSslSocketFactory())
					.sslParameters(sslConfiguration.getSslParameters())
					.hostnameVerifier(sslConfiguration.getHostnameVerifier());
		}
	}

	public static LettuceClientConfigBuilder create(final LettuceRedisDataSource dataSource,
													final SslConfiguration sslConfiguration) {
		return new LettuceClientConfigBuilder(dataSource, sslConfiguration);
	}

	public LettuceClientConfigBuilder user(final String user) {
		propertyMapper.from(user).to(builder::user);
		return this;
	}

	public LettuceClientConfigBuilder password(final String password) {
		propertyMapper.from(password).to(builder::password);
		return this;
	}

	public LettuceClientConfigBuilder database(final int database) {
		if(database >= 0){
			builder.database(database);
		}
		return this;
	}

	public LettuceClientConfigBuilder clientName(final String clientName) {
		propertyMapper.from(clientName).to(builder::clientName);
		return this;
	}

	public LettuceClientConfigBuilder connectTimeout(final int connectTimeout) {
		builder.connectionTimeoutMillis(connectTimeout);
		return this;
	}

	public LettuceClientConfigBuilder socketTimeout(final int socketTimeout) {
		builder.socketTimeoutMillis(socketTimeout);
		return this;
	}

	public LettuceClientConfigBuilder infiniteSoTimeout(final int blockingSocketTimeout) {
		return this;
	}

	public DefaultLettuceClientConfig build() {
		return builder.build();
	}

}
