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
import com.buession.redis.client.operations.BinaryHyperLogLogRedisOperations;
import com.buession.redis.client.operations.BinaryKeyRedisOperations;
import com.buession.redis.client.operations.BinaryListRedisOperations;
import com.buession.redis.client.operations.BinarySetRedisOperations;
import com.buession.redis.client.operations.BinarySortedSetRedisOperations;
import com.buession.redis.client.operations.BinaryStringRedisOperations;
import com.buession.redis.client.GenericRedisClient;
import com.buession.redis.client.operations.HyperLogLogRedisOperations;
import com.buession.redis.client.operations.KeyRedisOperations;
import com.buession.redis.client.operations.ListRedisOperations;
import com.buession.redis.client.operations.SetRedisOperations;
import com.buession.redis.client.operations.SortedSetRedisOperations;
import com.buession.redis.client.operations.StringRedisOperations;
import com.buession.redis.client.connection.RedisConnection;
import com.buession.redis.core.ScanResult;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Map;
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
		return execute(keyOperations, (ops)->ops.rename(key, newKey));
	}

	@Override
	public Status rename(final byte[] key, final byte[] newKey){
		return execute(binaryKeyOperations, (ops)->ops.rename(key, newKey));
	}

	@Override
	public Status renameNx(final String key, final String newKey){
		return execute(keyOperations, (ops)->ops.renameNx(key, newKey));
	}

	@Override
	public Status renameNx(final byte[] key, final byte[] newKey){
		return execute(binaryKeyOperations, (ops)->ops.renameNx(key, newKey));
	}

	@Override
	public String randomKey(){
		return execute(keyOperations, (ops)->ops.randomKey());
	}

	@Override
	public Set<String> keys(final String pattern){
		return execute(keyOperations, (ops)->ops.keys(pattern));
	}

	@Override
	public Set<byte[]> keys(final byte[] pattern){
		return execute(binaryKeyOperations, (ops)->ops.keys(pattern));
	}

	@Override
	public ScanResult<List<String>> scan(final String cursor){
		return execute(keyOperations, (ops)->ops.scan(cursor));
	}

	@Override
	public ScanResult<List<byte[]>> scan(final byte[] cursor){
		return execute(binaryKeyOperations, (ops)->ops.scan(cursor));
	}

	@Override
	public ScanResult<List<String>> scan(final String cursor, final String pattern){
		return execute(keyOperations, (ops)->ops.scan(cursor, pattern));
	}

	@Override
	public ScanResult<List<byte[]>> scan(final byte[] cursor, final byte[] pattern){
		return execute(binaryKeyOperations, (ops)->ops.scan(cursor, pattern));
	}

	@Override
	public ScanResult<List<String>> scan(final String cursor, final int count){
		return execute(keyOperations, (ops)->ops.scan(cursor, count));
	}

	@Override
	public ScanResult<List<byte[]>> scan(final byte[] cursor, final int count){
		return execute(binaryKeyOperations, (ops)->ops.scan(cursor, count));
	}

	@Override
	public ScanResult<List<String>> scan(final String cursor, final String pattern, final int count){
		return execute(keyOperations, (ops)->ops.scan(cursor, pattern, count));
	}

	@Override
	public ScanResult<List<byte[]>> scan(final byte[] cursor, final byte[] pattern, final int count){
		return execute(binaryKeyOperations, (ops)->ops.scan(cursor, pattern, count));
	}

	@Override
	public Long sort(final String key, final String destKey){
		return execute(keyOperations, (ops)->ops.sort(key, destKey));
	}

	@Override
	public Long sort(final byte[] key, final byte[] destKey){
		return execute(binaryKeyOperations, (ops)->ops.sort(key, destKey));
	}

	@Override
	public Long sort(final String key, final String destKey, final SortArgument sortArgument){
		return execute(keyOperations, (ops)->ops.sort(key, destKey, sortArgument));
	}

	@Override
	public Long sort(final byte[] key, final byte[] destKey, final SortArgument sortArgument){
		return execute(binaryKeyOperations, (ops)->ops.sort(key, destKey, sortArgument));
	}

	@Override
	public Status mSet(final Map<String, String> values){
		return execute(stringOperations, (ops)->ops.mSet(values));
	}

	@Override
	public Status mSetNx(final Map<String, String> values){
		return execute(stringOperations, (ops)->ops.mSetNx(values));
	}

	@Override
	public List<String> mGet(final String... keys){
		return execute(stringOperations, (ops)->ops.mGet());
	}

	@Override
	public List<byte[]> mGet(final byte[]... keys){
		return execute(binaryStringOperations, (ops)->ops.mGet());
	}

	@Override
	public String rPoplPush(final String source, final String destKey){
		return execute(listOperations, (ops)->ops.rPoplPush(source, destKey));
	}

	@Override
	public byte[] rPoplPush(final byte[] source, final byte[] destKey){
		return execute(binaryListOperations, (ops)->ops.rPoplPush(source, destKey));
	}

	@Override
	public String brPoplPush(final String source, final String destKey, final int timeout){
		return execute(listOperations, (ops)->ops.brPoplPush(source, destKey, timeout));
	}

	@Override
	public byte[] brPoplPush(final byte[] source, final byte[] destKey, final int timeout){
		return execute(binaryListOperations, (ops)->ops.brPoplPush(source, destKey, timeout));
	}

	@Override
	public Set<String> sDiff(final String... keys){
		return execute(setOperations, (ops)->ops.sDiff(keys));
	}

	@Override
	public Set<byte[]> sDiff(final byte[]... keys){
		return execute(binarySetOperations, (ops)->ops.sDiff(keys));
	}

	@Override
	public Long sDiffStore(final String destKey, final String... keys){
		return execute(setOperations, (ops)->ops.sDiffStore(destKey, keys));
	}

	@Override
	public Long sDiffStore(final byte[] destKey, final byte[]... keys){
		return execute(binarySetOperations, (ops)->ops.sDiffStore(destKey, keys));
	}

	@Override
	public Set<String> sInter(final String... keys){
		return execute(setOperations, (ops)->ops.sInter(keys));
	}

	@Override
	public Set<byte[]> sInter(final byte[]... keys){
		return execute(binarySetOperations, (ops)->ops.sInter(keys));
	}

	@Override
	public Long sInterStore(final String destKey, final String... keys){
		return execute(setOperations, (ops)->ops.sInterStore(destKey, keys));
	}

	@Override
	public Long sInterStore(final byte[] destKey, final byte[]... keys){
		return execute(binarySetOperations, (ops)->ops.sInterStore(destKey, keys));
	}

	@Override
	public Set<String> sUnion(final String... keys){
		return execute(setOperations, (ops)->ops.sUnion(keys));
	}

	@Override
	public Set<byte[]> sUnion(final byte[]... keys){
		return execute(binarySetOperations, (ops)->ops.sUnion(keys));
	}

	@Override
	public Long sUnionStore(final String destKey, final String... keys){
		return execute(setOperations, (ops)->ops.sUnionStore(destKey, keys));
	}

	@Override
	public Long sUnionStore(final byte[] destKey, final byte[]... keys){
		return execute(binarySetOperations, (ops)->ops.sUnionStore(destKey, keys));
	}

	@Override
	public Status sMove(final String source, final String destKey, final String member){
		return execute(setOperations, (ops)->ops.sMove(source, destKey, member));
	}

	@Override
	public Status sMove(final byte[] source, final byte[] destKey, final byte[] member){
		return execute(binarySetOperations, (ops)->ops.sMove(source, destKey, member));
	}

}
