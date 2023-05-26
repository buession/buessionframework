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

import java.io.Serializable;
import java.util.Set;

/**
 * SSL 配置
 *
 * @author Yong.Teng
 * @since 2.3.0
 */
public class SslConfigure implements Serializable {

	private final static long serialVersionUID = -1240916190474599542L;

	/**
	 * SSL protocol.
	 */
	private String protocol;

	/**
	 * Path of the key store file.
	 */
	private String keyStorePath;

	/**
	 * Key store type.
	 */
	private String keyStoreType;

	/**
	 * Password of the private key in the key store file.
	 */
	private String keyPassword;

	/**
	 * Password used to access the key store.
	 */
	private String keyStorePassword;

	/**
	 * Path of the trust store file.
	 */
	private String trustStorePath;

	/**
	 * Type of the trust store.
	 */
	private String trustStoreType;

	/**
	 * Store password for the trust store file.
	 */
	private String trustStorePassword;

	/**
	 * SSL algorithm to use.
	 */
	private Set<String> algorithms;

	/**
	 * Whether to enable server side certificate validation.
	 */
	private boolean validateServerCertificate = true;

	/**
	 * Whether to enable hostname verification.
	 */
	private boolean verifyHostname = true;

	/**
	 * Return SSL protocol.
	 *
	 * @return SSL protocol.
	 */
	public String getProtocol(){
		return protocol;
	}

	/**
	 * Sets SSL protocol.
	 *
	 * @param protocol
	 * 		SSL protocol.
	 */
	public void setProtocol(String protocol){
		this.protocol = protocol;
	}

	/**
	 * Return path of the key store file.
	 *
	 * @return Path of the key store file.
	 */
	public String getKeyStorePath(){
		return keyStorePath;
	}

	/**
	 * Sets path of the key store file.
	 *
	 * @param keyStorePath
	 * 		Path of the key store file.
	 */
	public void setKeyStorePath(String keyStorePath){
		this.keyStorePath = keyStorePath;
	}

	/**
	 * Return key store type.
	 *
	 * @return Key store type.
	 */
	public String getKeyStoreType(){
		return keyStoreType;
	}

	/**
	 * Sets key store type.
	 *
	 * @param keyStoreType
	 * 		Key store type.
	 */
	public void setKeyStoreType(String keyStoreType){
		this.keyStoreType = keyStoreType;
	}

	/**
	 * Return password of the private key in the key store file.
	 *
	 * @return Password of the private key in the key store file.
	 */
	public String getKeyPassword(){
		return keyPassword;
	}

	/**
	 * Sets password of the private key in the key store file.
	 *
	 * @param keyPassword
	 * 		Password of the private key in the key store file.
	 */
	public void setKeyPassword(String keyPassword){
		this.keyPassword = keyPassword;
	}

	/**
	 * Return password used to access the key store.
	 *
	 * @return Password used to access the key store.
	 */
	public String getKeyStorePassword(){
		return keyStorePassword;
	}

	/**
	 * Sets password used to access the key store.
	 *
	 * @param keyStorePassword
	 * 		Password used to access the key store.
	 */
	public void setKeyStorePassword(String keyStorePassword){
		this.keyStorePassword = keyStorePassword;
	}

	/**
	 * Return path of the trust store file.
	 *
	 * @return Path of the trust store file.
	 */
	public String getTrustStorePath(){
		return trustStorePath;
	}

	/**
	 * Sets Path of the trust store file.
	 *
	 * @param trustStorePath
	 * 		Path of the trust store file.
	 */
	public void setTrustStorePath(String trustStorePath){
		this.trustStorePath = trustStorePath;
	}

	/**
	 * Return type of the trust store.
	 *
	 * @return Type of the trust store.
	 */
	public String getTrustStoreType(){
		return trustStoreType;
	}

	/**
	 * Sets type of the trust store.
	 *
	 * @param trustStoreType
	 * 		Type of the trust store.
	 */
	public void setTrustStoreType(String trustStoreType){
		this.trustStoreType = trustStoreType;
	}

	/**
	 * Return store password for the trust store file.
	 *
	 * @return Store password for the trust store file.
	 */
	public String getTrustStorePassword(){
		return trustStorePassword;
	}

	/**
	 * Sets store password for the trust store file.
	 *
	 * @param trustStorePassword
	 * 		Store password for the trust store file.
	 */
	public void setTrustStorePassword(String trustStorePassword){
		this.trustStorePassword = trustStorePassword;
	}

	/**
	 * Return SSL algorithm to use.
	 *
	 * @return SSL algorithm to use.
	 */
	public Set<String> getAlgorithms(){
		return algorithms;
	}

	/**
	 * Sets SSL algorithm to use.
	 *
	 * @param algorithms
	 * 		SSL algorithm to use.
	 */
	public void setAlgorithm(Set<String> algorithms){
		this.algorithms = algorithms;
	}

	/**
	 * Return whether to enable server side certificate validation.
	 *
	 * @return true / false
	 */
	public boolean isValidateServerCertificate(){
		return validateServerCertificate;
	}

	/**
	 * Sets enable server side certificate validation.
	 *
	 * @param validateServerCertificate
	 * 		Whether to enable server side certificate validation.
	 */
	public void setValidateServerCertificate(boolean validateServerCertificate){
		this.validateServerCertificate = validateServerCertificate;
	}

	/**
	 * Return whether to enable hostname verification.
	 *
	 * @return true / false
	 */
	public boolean isVerifyHostname(){
		return verifyHostname;
	}

	/**
	 * Sets enable hostname verification.
	 *
	 * @param verifyHostname
	 * 		Whether to enable hostname verification.
	 */
	public void setVerifyHostname(boolean verifyHostname){
		this.verifyHostname = verifyHostname;
	}

}
