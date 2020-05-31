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
package com.buession.redis.client.jedis;

import com.buession.lang.Status;
import com.buession.redis.client.BinaryKeyRedisOperations;
import com.buession.redis.client.BinaryListRedisOperations;
import com.buession.redis.client.BinaryStringRedisOperations;
import com.buession.redis.client.GenericRedisClient;
import com.buession.redis.client.KeyRedisOperations;
import com.buession.redis.client.ListRedisOperations;
import com.buession.redis.client.StringRedisOperations;
import com.buession.redis.client.connection.RedisConnection;
import com.buession.redis.core.ScanResult;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Set;

/**
 * @author Yong.Teng
 */
public class JedisClient extends AbstractJedisRedisClient<Jedis> implements GenericRedisClient {

	public JedisClient(){
		super();
	}

	public JedisClient(RedisConnection connection){
		super(connection);
	}

	@Override
	public Status rename(final String key, final String newKey){
		return execute(keyOperations, (KeyRedisOperations ops)->ops.rename(key, newKey));
	}

	@Override
	public Status rename(final byte[] key, final byte[] newKey){
		return execute(binaryKeyOperations, (BinaryKeyRedisOperations ops)->ops.rename(key, newKey));
	}

	@Override
	public Status renameNx(final String key, final String newKey){
		return execute(keyOperations, (KeyRedisOperations ops)->ops.renameNx(key, newKey));
	}

	@Override
	public Status renameNx(final byte[] key, final byte[] newKey){
		return execute(binaryKeyOperations, (BinaryKeyRedisOperations ops)->ops.renameNx(key, newKey));
	}

	@Override
	public String randomKey(){
		return execute(keyOperations, (KeyRedisOperations ops)->ops.randomKey());
	}

	@Override
	public Set<String> keys(final String pattern){
		return execute(keyOperations, (KeyRedisOperations ops)->ops.keys(pattern));
	}

	@Override
	public Set<byte[]> keys(final byte[] pattern){
		return execute(binaryKeyOperations, (BinaryKeyRedisOperations ops)->ops.keys(pattern));
	}

	@Override
	public ScanResult<List<String>> scan(final String cursor){
		return execute(keyOperations, (KeyRedisOperations ops)->ops.scan(cursor));
	}

	@Override
	public ScanResult<List<byte[]>> scan(final byte[] cursor){
		return execute(binaryKeyOperations, (BinaryKeyRedisOperations ops)->ops.scan(cursor));
	}

	@Override
	public ScanResult<List<String>> scan(final String cursor, final String pattern){
		return execute(keyOperations, (KeyRedisOperations ops)->ops.scan(cursor, pattern));
	}

	@Override
	public ScanResult<List<byte[]>> scan(final byte[] cursor, final byte[] pattern){
		return execute(binaryKeyOperations, (BinaryKeyRedisOperations ops)->ops.scan(cursor, pattern));
	}

	@Override
	public ScanResult<List<String>> scan(final String cursor, final int count){
		return execute(keyOperations, (KeyRedisOperations ops)->ops.scan(cursor, count));
	}

	@Override
	public ScanResult<List<byte[]>> scan(final byte[] cursor, final int count){
		return execute(binaryKeyOperations, (BinaryKeyRedisOperations ops)->ops.scan(cursor, count));
	}

	@Override
	public ScanResult<List<String>> scan(final String cursor, final String pattern, final int count){
		return execute(keyOperations, (KeyRedisOperations ops)->ops.scan(cursor, pattern, count));
	}

	@Override
	public ScanResult<List<byte[]>> scan(final byte[] cursor, final byte[] pattern, final int count){
		return execute(binaryKeyOperations, (BinaryKeyRedisOperations ops)->ops.scan(cursor, pattern, count));
	}

	@Override
	public Long sort(final String key, final String destKey){
		return execute(keyOperations, (KeyRedisOperations ops)->ops.sort(key, destKey));
	}

	@Override
	public Long sort(final byte[] key, final byte[] destKey){
		return execute(binaryKeyOperations, (BinaryKeyRedisOperations ops)->ops.sort(key, destKey));
	}

	@Override
	public Long sort(final String key, final String destKey, final SortArgument sortArgument){
		return execute(keyOperations, (KeyRedisOperations ops)->ops.sort(key, destKey, sortArgument));
	}

	@Override
	public Long sort(final byte[] key, final byte[] destKey, final SortArgument sortArgument){
		return execute(binaryKeyOperations, (BinaryKeyRedisOperations ops)->ops.sort(key, destKey, sortArgument));
	}

	@Override
	public List<String> mGet(final String... keys){
		return execute(stringOperations, (StringRedisOperations ops)->ops.mGet(keys));
	}

	@Override
	public List<byte[]> mGet(final byte[]... keys){
		return execute(binaryStringOperations, (BinaryStringRedisOperations ops)->ops.mGet(keys));
	}

	@Override
	public String rPoplPush(final String source, final String destKey){
		return execute(listOperations, (ListRedisOperations ops)->ops.rPoplPush(source, destKey));
	}

	@Override
	public byte[] rPoplPush(final byte[] source, final byte[] destKey){
		return execute(binaryListOperations, (BinaryListRedisOperations ops)->ops.rPoplPush(source, destKey));
	}

	@Override
	public String brPoplPush(final String source, final String destKey, final int timeout){
		return execute(listOperations, (ListRedisOperations ops)->ops.brPoplPush(source, destKey, timeout));
	}

	@Override
	public byte[] brPoplPush(final byte[] source, final byte[] destKey, final int timeout){
		return execute(binaryListOperations, (BinaryListRedisOperations ops)->ops.brPoplPush(source, destKey,
				timeout));
	}

}
