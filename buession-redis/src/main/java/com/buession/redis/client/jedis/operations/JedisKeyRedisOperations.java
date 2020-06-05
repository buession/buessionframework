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

import com.buession.redis.client.operations.KeyRedisOperations;
import com.buession.redis.core.ScanResult;

import java.util.List;

/**
 * @author Yong.Teng
 */
public interface JedisKeyRedisOperations extends KeyRedisOperations {

	@Override
	default ScanResult<List<String>> scan(final int cursor){
		return scan(Integer.toString(cursor));
	}

	@Override
	default ScanResult<List<String>> scan(final long cursor){
		return scan(Long.toString(cursor));
	}

	@Override
	default ScanResult<List<String>> scan(final int cursor, final String pattern){
		return scan(Integer.toString(cursor), pattern);
	}

	@Override
	default ScanResult<List<String>> scan(final long cursor, final String pattern){
		return scan(Long.toString(cursor), pattern);
	}

	@Override
	default ScanResult<List<String>> scan(final int cursor, final int count){
		return scan(Integer.toString(cursor), count);
	}

	@Override
	default ScanResult<List<String>> scan(final long cursor, final int count){
		return scan(Long.toString(cursor), count);
	}

	@Override
	default ScanResult<List<String>> scan(final int cursor, final String pattern, final int count){
		return scan(Integer.toString(cursor), pattern, count);
	}

	@Override
	default ScanResult<List<String>> scan(final long cursor, final String pattern, final int count){
		return scan(Long.toString(count), pattern, count);
	}

}
