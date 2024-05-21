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
package io.lettuce.core;

import com.buession.redis.core.RedisNode;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocketFactory;
import java.time.Duration;

/**
 * 默认客户端配置
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public class DefaultLettuceClientConfig implements LettuceClientConfig {

	private Duration connectionTimeout = Duration.ofMillis(RedisURI.DEFAULT_TIMEOUT);

	private Duration socketTimeout = connectionTimeout;

	private String user;

	private String password;

	private int database = RedisNode.DEFAULT_DATABASE;

	private String clientName;

	private boolean isSsl;

	private SSLSocketFactory sslSocketFactory;

	private SSLParameters sslParameters;

	private HostnameVerifier hostnameVerifier;

	private DefaultLettuceClientConfig() {

	}

	private DefaultLettuceClientConfig(final Duration connectionTimeout, final Duration socketTimeout,
									   final String user, final String password, final int database,
									   final String clientName, final boolean isSsl,
									   final SSLSocketFactory sslSocketFactory, final SSLParameters sslParameters,
									   final HostnameVerifier hostnameVerifier) {
		this.connectionTimeout = connectionTimeout;
		this.socketTimeout = socketTimeout;
		this.user = user;
		this.password = password;
		this.database = database;
		this.clientName = clientName;
		this.isSsl = isSsl;
		this.sslSocketFactory = sslSocketFactory;
		this.sslParameters = sslParameters;
		this.hostnameVerifier = hostnameVerifier;
	}

	@Override
	public Duration getConnectionTimeout() {
		return connectionTimeout;
	}

	@Override
	public Duration getSocketTimeout() {
		return socketTimeout;
	}

	@Override
	public String getUser() {
		return user;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public int getDatabase() {
		return database;
	}

	@Override
	public String getClientName() {
		return clientName;
	}

	@Override
	public boolean isSsl() {
		return isSsl;
	}

	@Override
	public SSLSocketFactory getSslSocketFactory() {
		return sslSocketFactory;
	}

	@Override
	public SSLParameters getSslParameters() {
		return sslParameters;
	}

	@Override
	public HostnameVerifier getHostnameVerifier() {
		return hostnameVerifier;
	}

	public static Builder builder() {
		return new Builder();
	}

	public final static class Builder {

		private final DefaultLettuceClientConfig lettuceClientConfig = new DefaultLettuceClientConfig();

		private Builder() {
		}

		public Builder timeout(final Duration timeout) {
			lettuceClientConfig.connectionTimeout = timeout;
			lettuceClientConfig.socketTimeout = timeout;
			return this;
		}

		public Builder timeoutMillis(final long timeoutMillis) {
			return timeout(Duration.ofMillis(timeoutMillis));
		}

		public Builder connectionTimeout(final Duration timeout) {
			lettuceClientConfig.connectionTimeout = timeout;
			return this;
		}

		public Builder connectionTimeoutMillis(final long timeoutMillis) {
			return connectionTimeout(Duration.ofMillis(timeoutMillis));
		}

		public Builder socketTimeout(final Duration timeout) {
			lettuceClientConfig.socketTimeout = timeout;
			return this;
		}

		public Builder socketTimeoutMillis(final long timeoutMillis) {
			return connectionTimeout(Duration.ofMillis(timeoutMillis));
		}

		public Builder user(final String user) {
			lettuceClientConfig.user = user;
			return this;
		}

		public Builder password(final String password) {
			lettuceClientConfig.password = password;
			return this;
		}

		public Builder credentials(final String user, final String password) {
			lettuceClientConfig.user = user;
			lettuceClientConfig.password = password;
			return this;
		}

		public Builder database(final int database) {
			lettuceClientConfig.database = database;
			return this;
		}

		public Builder clientName(final String clientName) {
			lettuceClientConfig.clientName = clientName;
			return this;
		}

		public Builder ssl(boolean isSsl) {
			lettuceClientConfig.isSsl = isSsl;
			return this;
		}

		public Builder sslSocketFactory(SSLSocketFactory sslSocketFactory) {
			lettuceClientConfig.sslSocketFactory = sslSocketFactory;
			return this;
		}

		public Builder sslParameters(SSLParameters sslParameters) {
			lettuceClientConfig.sslParameters = sslParameters;
			return this;
		}

		public Builder hostnameVerifier(HostnameVerifier hostnameVerifier) {
			lettuceClientConfig.hostnameVerifier = hostnameVerifier;
			return this;
		}

		public DefaultLettuceClientConfig build() {
			return lettuceClientConfig;
		}

		public static LettuceClientConfig create(final int connectionTimeoutMillis, final int soTimeoutMillis,
												 final String user, final String password, final int database,
												 final String clientName, final boolean isSsl,
												 final SSLSocketFactory sslSocketFactory,
												 final SSLParameters sslParameters,
												 final HostnameVerifier hostnameVerifier) {
			return new DefaultLettuceClientConfig(Duration.ofMillis(connectionTimeoutMillis),
					Duration.ofMillis(soTimeoutMillis), user, password, database, clientName, isSsl, sslSocketFactory
					, sslParameters, hostnameVerifier);
		}

		public static LettuceClientConfig create(final Duration connectionTimeout, final Duration soTimeoutMillis,
												 final String user, final String password, final int database,
												 final String clientName, final boolean isSsl,
												 final SSLSocketFactory sslSocketFactory,
												 final SSLParameters sslParameters,
												 final HostnameVerifier hostnameVerifier) {
			return new DefaultLettuceClientConfig(connectionTimeout, soTimeoutMillis, user, password, database,
					clientName, isSsl, sslSocketFactory, sslParameters, hostnameVerifier);
		}

	}

}
