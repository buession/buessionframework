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
package io.lettuce.core.utils;

import com.buession.core.utils.StringUtils;
import com.buession.core.validator.Validate;
import com.buession.core.HostAndPort;
import com.buession.redis.core.RedisNode;
import io.lettuce.core.RedisURI;

import java.net.URI;

/**
 *
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public class LettuceURIHelper {

	private LettuceURIHelper() {

	}

	public static boolean isValid(URI uri) {
		return Validate.isBlank(uri.getScheme()) || Validate.isBlank(uri.getHost()) || uri.getPort() == -1 ? false :
				true;
	}

	public static HostAndPort getHostAndPort(URI uri) {
		return new HostAndPort(uri.getHost(), uri.getPort());
	}

	/**
	 * Extracts the user from the given URI.
	 * <p>
	 * For details on the URI format and authentication examples.
	 * </p>
	 *
	 * @param uri
	 * 		the URI to extract the user from
	 *
	 * @return the user as a String, or null if user is empty or {@link URI#getUserInfo()} info is missing
	 */
	public static String getUser(URI uri) {
		String userInfo = uri.getUserInfo();
		if(userInfo != null){
			String user = StringUtils.split(userInfo, ":")[0];
			if(user.isEmpty()){
				user = null; // return null user is not specified
			}
			return user;
		}
		return null;
	}

	/**
	 * Extracts the password from the given URI.
	 * <p>
	 * For details on the URI format and authentication examples.
	 * </p>
	 *
	 * @param uri
	 * 		the URI to extract the password from
	 *
	 * @return the password as a String, or null if {@link URI#getUserInfo()} info is missing
	 *
	 * @throws IllegalArgumentException
	 * 		if {@link URI#getUserInfo()} is provided but does not contain a password
	 */
	public static String getPassword(URI uri) {
		String userInfo = uri.getUserInfo();
		if(userInfo != null){
			String[] userAndPassword = StringUtils.split(userInfo, ":", 2);
			if(userAndPassword.length < 2){
				throw new IllegalArgumentException("Password not provided in uri.");
			}
			return userAndPassword[1];
		}
		return null;
	}

	public static boolean hasDbIndex(URI uri) {
		if(uri.getPath() == null || uri.getPath().isEmpty()){
			return false;
		}

		String[] pathSplit = StringUtils.split(uri.getPath(), "/", 2);

		return pathSplit.length > 1 && Validate.isNotEmpty(pathSplit[1]);
	}

	public static int getDBIndex(URI uri) {
		String[] pathSplit = StringUtils.split(uri.getPath(), "/", 2);
		if(pathSplit.length > 1){
			String dbIndexStr = pathSplit[1];
			if(dbIndexStr.isEmpty()){
				return RedisNode.DEFAULT_DATABASE;
			}
			return Integer.parseInt(dbIndexStr);
		}else{
			return RedisNode.DEFAULT_DATABASE;
		}
	}

	public static boolean isRedisSSLScheme(URI uri) {
		return RedisURI.URI_SCHEME_REDIS_SECURE.equals(uri.getScheme()) ||
				RedisURI.URI_SCHEME_REDIS_SECURE_ALT.equals(uri.getScheme()) ||
				RedisURI.URI_SCHEME_REDIS_TLS_ALT.equals(uri.getScheme());
	}

}
