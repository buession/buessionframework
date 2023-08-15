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

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

/**
 * @author Yong.Teng
 * @since 2.3.0
 */
public class SSLContexts {

	/**
	 * Creates default factory based on the standard JSSE trust material
	 * ({@code cacerts} file in the security properties directory). System properties
	 * are not taken into consideration.
	 *
	 * @return the default SSL socket factory
	 */
	public static SSLContext createDefault() throws SSLInitializationException{
		try{
			final SSLContext sslContext = SSLContext.getInstance("TLS");
			sslContext.init(null, null, null);
			return sslContext;
		}catch(NoSuchAlgorithmException ex){
			throw new SSLInitializationException(ex.getMessage(), ex);
		}catch(KeyManagementException ex){
			throw new SSLInitializationException(ex.getMessage(), ex);
		}
	}

	/**
	 * Creates default SSL context based on system properties. This method obtains
	 * default SSL context by calling {@code SSLContext.getInstance("Default")}.
	 * Please note that {@code Default} algorithm is supported as of Java 6.
	 * This method will fall back onto {@link #createDefault()} when
	 * {@code Default} algorithm is not available.
	 *
	 * @return default system SSL context
	 */
	public static SSLContext createSystemDefault() throws SSLInitializationException{
		try{
			return SSLContext.getDefault();
		}catch(final NoSuchAlgorithmException ex){
			return createDefault();
		}
	}

}
