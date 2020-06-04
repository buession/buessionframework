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
package com.buession.redis.client.jedis.operations;

import com.buession.core.utils.NumberUtils;
import com.buession.redis.client.BinaryKeyRedisOperations;
import com.buession.redis.core.ScanResult;

import java.util.List;

/**
 * @author Yong.Teng
 */
public interface JedisBinaryKeyRedisOperations extends BinaryKeyRedisOperations {

	@Override
	default ScanResult<List<byte[]>> scan(final int cursor, final byte[] pattern){
		return scan(NumberUtils.int2bytes(cursor), pattern);
	}

	@Override
	default ScanResult<List<byte[]>> scan(final long cursor, final byte[] pattern){
		return scan(NumberUtils.long2bytes(cursor), pattern);
	}

	@Override
	default ScanResult<List<byte[]>> scan(final int cursor, final byte[] pattern, final int count){
		return scan(NumberUtils.int2bytes(cursor), pattern, count);
	}

	@Override
	default ScanResult<List<byte[]>> scan(final long cursor, final byte[] pattern, final int count){
		return scan(NumberUtils.long2bytes(count), pattern, count);
	}

}
