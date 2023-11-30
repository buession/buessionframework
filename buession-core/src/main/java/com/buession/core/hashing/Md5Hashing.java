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
package com.buession.core.hashing;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Yong.Teng
 */
public class Md5Hashing extends AbstractHashing {

	private final static ThreadLocal<MessageDigest> MD5_HOLDER = new ThreadLocal<>();

	@Override
	public long hash(byte[] key) {
		try{
			if(MD5_HOLDER.get() == null){
				MD5_HOLDER.set(MessageDigest.getInstance("MD5"));
			}
		}catch(NoSuchAlgorithmException e){
			MD5_HOLDER.remove();
			throw new IllegalStateException("++++ no md5 algorithm found");
		}

		MessageDigest md5 = MD5_HOLDER.get();

		md5.reset();
		md5.update(key);

		byte[] bKey = md5.digest();
		return ((long) (bKey[3] & 0xFF) << 24) | ((long) (bKey[2] & 0xFF) << 16) | ((long) (bKey[1] & 0xFF) << 8) |
				(long) (bKey[0] & 0xFF);
	}

}
