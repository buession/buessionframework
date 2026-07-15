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
 * | Copyright @ 2013-2026 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.core;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocketFactory;

/**
 * Options to configure SSL options for the connections kept to Redis servers.
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public class SslOptions {

	private boolean enabled;

	private String keyStoreType;

	private String keyStore;

	private String keyStorePassword;

	private String trustStoreType;

	private String trustStore;

	private String trustStorePassword;

	private String protocol;

	private String[] cipherSuites;

	private SSLParameters sslParameters;

	private SSLSocketFactory sslSocketFactory;

	private HostnameVerifier hostnameVerifier;

	private SslVerifyMode sslVerifyMode;

	private int handshakeTimeout;

	public boolean isEnabled() {
		return getEnabled();
	}

	public boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getKeyStoreType() {
		return keyStoreType;
	}

	public void setKeyStoreType(String keyStoreType) {
		this.keyStoreType = keyStoreType;
	}

	public String getKeyStore() {
		return keyStore;
	}

	public void setKeyStore(String keyStore) {
		this.keyStore = keyStore;
	}

	public String getKeyStorePassword() {
		return keyStorePassword;
	}

	public void setKeyStorePassword(String keyStorePassword) {
		this.keyStorePassword = keyStorePassword;
	}

	public String getTrustStoreType() {
		return trustStoreType;
	}

	public void setTrustStoreType(String trustStoreType) {
		this.trustStoreType = trustStoreType;
	}

	public String getTrustStore() {
		return trustStore;
	}

	public void setTrustStore(String trustStore) {
		this.trustStore = trustStore;
	}

	public String getTrustStorePassword() {
		return trustStorePassword;
	}

	public void setTrustStorePassword(String trustStorePassword) {
		this.trustStorePassword = trustStorePassword;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String[] getCipherSuites() {
		return cipherSuites;
	}

	public void setCipherSuites(String[] cipherSuites) {
		this.cipherSuites = cipherSuites;
	}

	public SSLParameters getSslParameters() {
		return sslParameters;
	}

	public void setSslParameters(SSLParameters sslParameters) {
		this.sslParameters = sslParameters;
	}

	public SSLSocketFactory getSslSocketFactory() {
		return sslSocketFactory;
	}

	public void setSslSocketFactory(SSLSocketFactory sslSocketFactory) {
		this.sslSocketFactory = sslSocketFactory;
	}

	public HostnameVerifier getHostnameVerifier() {
		return hostnameVerifier;
	}

	public void setHostnameVerifier(HostnameVerifier hostnameVerifier) {
		this.hostnameVerifier = hostnameVerifier;
	}

	public SslVerifyMode getSslVerifyMode() {
		return sslVerifyMode;
	}

	public void setSslVerifyMode(SslVerifyMode sslVerifyMode) {
		this.sslVerifyMode = sslVerifyMode;
	}

	public int getHandshakeTimeout() {
		return handshakeTimeout;
	}

	public void setHandshakeTimeout(int handshakeTimeout) {
		this.handshakeTimeout = handshakeTimeout;
	}

	public enum SslVerifyMode {

		/**
		 * No verification at all.
		 */
		NONE,

		/**
		 * Verify the CA and certificate without verifying that the hostname matches.
		 */
		CA,

		/**
		 * Full certificate verification.
		 */
		FULL,

		/**
		 * DO NOT USE THIS IN PRODUCTION.
		 * <p>
		 * No verification at all.
		 */
		INSECURE;

	}

}
