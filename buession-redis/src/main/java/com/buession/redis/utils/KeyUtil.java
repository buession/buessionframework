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
 * | Copyright @ 2013-2020 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.utils;

import com.buession.core.validator.Validate;

/**
 * @author Yong.Teng
 */
public class KeyUtil {

	private KeyUtil(){

	}

	public final static String makeRawKey(final String prefix, final String key){
		if(prefix == null){
			return key;
		}else{
			StringBuilder sb = new StringBuilder(prefix.length() + key.length());

			sb.append(prefix).append(key);

			return sb.toString();
		}
	}

	public final static String[] makeRawKeys(final String prefix, final String... keys){
		if(Validate.isEmpty(keys)){
			return keys;
		}

		String[] rawKeys = new String[keys.length];

		for(int i = 0; i < keys.length; i++){
			rawKeys[i] = makeRawKey(prefix, keys[i]);
		}

		return rawKeys;
	}

	public final static byte[] makeByteKey(final String prefix, byte[] key){
		if(prefix != null){
			byte[] prefixByte = SafeEncoder.encode(prefix);
			byte[] result = new byte[prefixByte.length + key.length];

			System.arraycopy(prefixByte, 0, result, 0, prefixByte.length);
			System.arraycopy(key, 0, result, prefixByte.length, key.length);

			return result;
		}

		return key;
	}

	public final static byte[] makeByteKey(final byte[] prefix, byte[] key){
		if(prefix != null){
			byte[] result = new byte[prefix.length + key.length];

			System.arraycopy(prefix, 0, result, 0, prefix.length);
			System.arraycopy(key, 0, result, prefix.length, key.length);

			return result;
		}

		return key;
	}

	public final static byte[][] makeByteKeys(final byte[] prefix, final byte[]... keys){
		if(Validate.isEmpty(keys)){
			return keys;
		}

		byte[][] byteKeys = new byte[keys.length][];

		for(int i = 0; i < keys.length; i++){
			byteKeys[i] = makeByteKey(prefix, keys[i]);
		}

		return byteKeys;
	}

}
