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
 * 客户端配置
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public interface LettuceClientConfig {

	/**
	 * 返回连接超时
	 *
	 * @return 连接超时
	 */
	default Duration getConnectionTimeout() {
		return RedisURI.DEFAULT_TIMEOUT_DURATION;
	}

	/**
	 * 返回读取超时
	 *
	 * @return 读取超时
	 */
	default Duration getSocketTimeout() {
		return RedisURI.DEFAULT_TIMEOUT_DURATION;
	}

	/**
	 * 返回用户名
	 *
	 * @return 用户名
	 */
	default String getUser() {
		return null;
	}

	/**
	 * 返回密码
	 *
	 * @return 密码
	 */
	default String getPassword() {
		return null;
	}

	/**
	 * 返回数据库
	 *
	 * @return 数据库
	 */
	default int getDatabase() {
		return RedisNode.DEFAULT_DATABASE;
	}

	/**
	 * 返回客户端名称
	 *
	 * @return 客户端名称
	 */
	default String getClientName() {
		return null;
	}

	/**
	 * 返回是否为 TLS 连接
	 *
	 * @return <code>true</code> - to create a TLS connection. <code>false</code> - otherwise.
	 */
	default boolean isSsl() {
		return false;
	}

	/**
	 * 返回 {@link SSLSocketFactory}
	 *
	 * @return {@link SSLSocketFactory}
	 */
	default SSLSocketFactory getSslSocketFactory() {
		return null;
	}

	/**
	 * 返回 {@link SSLParameters}
	 *
	 * @return {@link SSLParameters}
	 */
	default SSLParameters getSslParameters() {
		return null;
	}

	/**
	 * 返回 {@link HostnameVerifier}
	 *
	 * @return {@link HostnameVerifier}
	 */
	default HostnameVerifier getHostnameVerifier() {
		return null;
	}

}
