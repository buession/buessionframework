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
 * | Copyright @ 2013-2021 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.core.hashing;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * @author Yong.Teng
 */
public class MurmurHashing extends AbstractHashing {

	@Override
	public long hash(byte[] key){
		return hash64A(key, 0x1234ABCD);
	}

	private static long hash64A(byte[] data, int seed){
		return hash64A(ByteBuffer.wrap(data), seed);
	}

	private static long hash64A(ByteBuffer buffer, int seed){
		ByteOrder byteOrder = buffer.order();
		buffer.order(ByteOrder.LITTLE_ENDIAN);

		long m = 0xc6a4a7935bd1e995L;
		int r = 47;
		long h = seed ^ (buffer.remaining() * m);

		long k;
		while(buffer.remaining() >= 8){
			k = buffer.getLong();

			k *= m;
			k ^= k >>> r;
			k *= m;

			h ^= k;
			h *= m;
		}

		if(buffer.remaining() > 0){
			ByteBuffer finish = ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN);
			finish.put(buffer).rewind();
			h ^= finish.getLong();
			h *= m;
		}

		h ^= h >>> r;
		h *= m;
		h ^= h >>> r;

		buffer.order(byteOrder);

		return h;
	}

}
