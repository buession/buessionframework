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
package com.buession.redis.core.internal.lettuce;

import com.buession.core.validator.Validate;
import com.buession.net.ssl.SslConfiguration;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocketFactory;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

/**
 * @author Yong.Teng
 * @since 2.3.0
 */
public class RedisClientBuilder {

	private final RedisURI.Builder redisUriBuilder = RedisURI.builder();

	private RedisClientBuilder(){
	}

	public static RedisClientBuilder create(){
		return new RedisClientBuilder();
	}

	public RedisClientBuilder host(final String host){
		redisUriBuilder.withHost(host);
		return this;
	}

	public RedisClientBuilder port(final int port){
		redisUriBuilder.withPort(port);
		return this;
	}

	public RedisClientBuilder connectionTimeout(final int connectionTimeout){
		redisUriBuilder.withTimeout(Duration.of(connectionTimeout, ChronoUnit.MILLIS));
		return this;
	}

	public RedisClientBuilder soTimeout(final int soTimeout){
		return this;
	}

	public RedisClientBuilder infiniteSoTimeout(final int infiniteSoTimeout){
		return this;
	}

	public RedisClientBuilder user(final String user){
		return this;
	}

	public RedisClientBuilder password(final String password){
		if(Validate.hasText(password)){
			redisUriBuilder.withPassword(password);
		}

		return this;
	}

	public RedisClientBuilder database(final int database){
		redisUriBuilder.withDatabase(database);
		return this;
	}

	public RedisClientBuilder ssl(final boolean ssl){
		redisUriBuilder.withSsl(ssl).withStartTls(ssl);
		return this;
	}

	public RedisClientBuilder sslConfiguration(final SslConfiguration sslConfiguration){
		if(sslConfiguration != null){
			hostnameVerifier(sslConfiguration.getHostnameVerifier());
		}

		return this;
	}

	public RedisClientBuilder sslSocketFactory(final SSLSocketFactory sslSocketFactory){
		return this;
	}

	public RedisClientBuilder sslParameters(final SSLParameters sslParameters){
		return this;
	}

	public RedisClientBuilder hostnameVerifier(final HostnameVerifier hostnameVerifier){
		if(hostnameVerifier != null){
			redisUriBuilder.withVerifyPeer(hostnameVerifier != null);
		}

		return this;
	}

	public RedisClientBuilder clientName(final String clientName){
		if(Validate.hasText(clientName)){
			redisUriBuilder.withClientName(clientName);
		}

		return this;
	}

	public RedisClient build(){
		return RedisClient.create(redisUriBuilder.build());
	}

}
