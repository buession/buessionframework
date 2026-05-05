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

import com.buession.core.utils.Assert;
import com.buession.core.validator.Validate;
import com.buession.redis.core.RedisNode;
import io.lettuce.core.resource.ClientResources;
import io.lettuce.core.utils.LettuceURIHelper;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocketFactory;
import java.net.URI;
import java.time.Duration;

/**
 * 默认客户端配置
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public class DefaultLettuceClientConfig implements LettuceClientConfig {

	private ClientResources clientResources = ClientResources.create();

	private ClientOptions clientOptions = ClientOptions.create();

	private SslOptions sslOptions = SslOptions.create();

	private Duration connectionTimeout = Duration.ofMillis(RedisURI.DEFAULT_TIMEOUT);

	private Duration socketTimeout = connectionTimeout;

	private RedisCredentialsProvider.ImmediateRedisCredentialsProvider credentialsProvider;

	private int database = RedisNode.DEFAULT_DATABASE;

	private String clientName;

	private boolean isSsl;

	private SSLSocketFactory sslSocketFactory;

	private SSLParameters sslParameters;

	private HostnameVerifier hostnameVerifier;

	private DefaultLettuceClientConfig() {

	}

	@Override
	public ClientResources getClientResources() {
		return clientResources;
	}

	@Override
	public ClientOptions getClientOptions() {
		return clientOptions;
	}

	@Override
	public SslOptions getSslOptions() {
		return sslOptions;
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
		return credentialsProvider == null ? null : credentialsProvider.resolveCredentialsNow().getUsername();
	}

	@Override
	public String getPassword() {
		return credentialsProvider == null ? null : new String(
				credentialsProvider.resolveCredentialsNow().getPassword());
	}

	public RedisCredentialsProvider getCredentialsProvider() {
		return credentialsProvider;
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

	/**
	 * Creates a new Builder pre-initialized with settings from the provided Redis URI.
	 * <p>
	 * The URI format is:
	 * {@code redis[s]://[username:password@]host:port[/database][?protocol=version]}
	 * </p>
	 * <p>
	 * Settings extracted from URI:
	 * <ul>
	 * <li>Credentials (username/password) if present in URI</li>
	 * <li>Database index if specified in path</li>
	 * <li>SSL enabled if scheme is "rediss"</li>
	 * <li>Protocol version if specified in query parameters</li>
	 * </ul>
	 *
	 * @param redisUri
	 * 		the Redis URI to extract settings from
	 *
	 * @return a new Builder pre-initialized from the URI
	 */
	public static Builder builder(URI redisUri) {
		Assert.isNull(redisUri, "Redis URI must not be null");
		Assert.isFalse(LettuceURIHelper.isValid(redisUri), "Invalid Redis URI");

		Builder builder = new Builder();

		String user = LettuceURIHelper.getUser(redisUri);
		String password = LettuceURIHelper.getPassword(redisUri);

		if(user != null || password != null){
			builder.credentials(RedisCredentials.just(user, password));
		}

		if(LettuceURIHelper.hasDbIndex(redisUri)){
			builder.database(LettuceURIHelper.getDBIndex(redisUri));
		}

		if(LettuceURIHelper.isRedisSSLScheme(redisUri)){
			builder.ssl(true);
		}

		return builder;
	}

	public final static class Builder {

		private final DefaultLettuceClientConfig lettuceClientConfig = new DefaultLettuceClientConfig();

		private String user;

		private String password;

		private Builder() {
		}

		public Builder clientResources(final ClientResources clientResources) {
			lettuceClientConfig.clientResources = clientResources;
			return this;
		}

		public Builder clientOptions(final ClientOptions clientOptions) {
			lettuceClientConfig.clientOptions = clientOptions;
			return this;
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
			return socketTimeout(Duration.ofMillis(timeoutMillis));
		}

		public Builder user(final String user) {
			this.user = user;
			return this;
		}

		public Builder password(final String password) {
			this.password = password;
			return this;
		}

		public Builder credentials(final String user, final String password) {
			this.user = user;
			this.password = password;
			return this;
		}

		public Builder credentials(final RedisCredentials redisCredentials) {
			lettuceClientConfig.credentialsProvider = new StaticCredentialsProvider(redisCredentials);
			return this;
		}

		public Builder credentials(
				final RedisCredentialsProvider.ImmediateRedisCredentialsProvider credentialsProvider) {
			lettuceClientConfig.credentialsProvider = credentialsProvider;
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

		public Builder sslOptions(SslOptions sslOptions) {
			lettuceClientConfig.sslOptions = sslOptions;
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
			if(lettuceClientConfig.credentialsProvider == null){
				if(Validate.hasText(password)){
					lettuceClientConfig.credentialsProvider =
							new StaticCredentialsProvider(RedisCredentials.just(user, password));
				}
			}

			return lettuceClientConfig;
		}

	}

}
