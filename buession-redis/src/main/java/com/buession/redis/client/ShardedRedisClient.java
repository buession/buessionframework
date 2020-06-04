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
package com.buession.redis.client;

import com.buession.lang.Status;
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.exception.NotSupportedCommandException;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Yong.Teng
 */
public interface ShardedRedisClient extends RedisClient {

	@Override
	default Status rename(final String key, final String newKey){
		throw new NotSupportedCommandException(ProtocolCommand.RENAME);
	}

	@Override
	default Status rename(final byte[] key, final byte[] newKey){
		throw new NotSupportedCommandException(ProtocolCommand.RENAME);
	}

	@Override
	default Status renameNx(final String key, final String newKey){
		throw new NotSupportedCommandException(ProtocolCommand.RENAMENX);
	}

	@Override
	default Status renameNx(final byte[] key, final byte[] newKey){
		throw new NotSupportedCommandException(ProtocolCommand.RENAMENX);
	}

	@Override
	default String randomKey(){
		throw new NotSupportedCommandException(ProtocolCommand.RANDOMKEY);
	}

	@Override
	default Set<String> keys(final String pattern){
		throw new NotSupportedCommandException(ProtocolCommand.KEYS);
	}

	@Override
	default Set<byte[]> keys(final byte[] pattern){
		throw new NotSupportedCommandException(ProtocolCommand.KEYS);
	}

	@Override
	default ScanResult<List<String>> scan(final int cursor){
		throw new NotSupportedCommandException(ProtocolCommand.SCAN);
	}

	@Override
	default ScanResult<List<String>> scan(final long cursor){
		throw new NotSupportedCommandException(ProtocolCommand.SCAN);
	}

	@Override
	default ScanResult<List<String>> scan(final String cursor){
		throw new NotSupportedCommandException(ProtocolCommand.SCAN);
	}

	@Override
	default ScanResult<List<byte[]>> scan(final byte[] cursor){
		throw new NotSupportedCommandException(ProtocolCommand.SCAN);
	}

	@Override
	default ScanResult<List<String>> scan(final int cursor, final String pattern){
		throw new NotSupportedCommandException(ProtocolCommand.SCAN);
	}

	@Override
	default ScanResult<List<byte[]>> scan(final int cursor, final byte[] pattern){
		throw new NotSupportedCommandException(ProtocolCommand.SCAN);
	}

	@Override
	default ScanResult<List<String>> scan(final long cursor, final String pattern){
		throw new NotSupportedCommandException(ProtocolCommand.SCAN);
	}

	@Override
	default ScanResult<List<byte[]>> scan(final long cursor, final byte[] pattern){
		throw new NotSupportedCommandException(ProtocolCommand.SCAN);
	}

	@Override
	default ScanResult<List<String>> scan(final String cursor, final String pattern){
		throw new NotSupportedCommandException(ProtocolCommand.SCAN);
	}

	@Override
	default ScanResult<List<byte[]>> scan(final byte[] cursor, final byte[] pattern){
		throw new NotSupportedCommandException(ProtocolCommand.SCAN);
	}

	@Override
	default ScanResult<List<String>> scan(final int cursor, final int count){
		throw new NotSupportedCommandException(ProtocolCommand.SCAN);
	}

	@Override
	default ScanResult<List<String>> scan(final long cursor, final int count){
		throw new NotSupportedCommandException(ProtocolCommand.SCAN);
	}

	@Override
	default ScanResult<List<String>> scan(final String cursor, final int count){
		throw new NotSupportedCommandException(ProtocolCommand.SCAN);
	}

	@Override
	default ScanResult<List<byte[]>> scan(final byte[] cursor, final int count){
		throw new NotSupportedCommandException(ProtocolCommand.SCAN);
	}

	@Override
	default ScanResult<List<String>> scan(final int cursor, final String pattern, final int count){
		throw new NotSupportedCommandException(ProtocolCommand.SCAN);
	}

	@Override
	default ScanResult<List<byte[]>> scan(final int cursor, final byte[] pattern, final int count){
		throw new NotSupportedCommandException(ProtocolCommand.SCAN);
	}

	@Override
	default ScanResult<List<String>> scan(final long cursor, final String pattern, final int count){
		throw new NotSupportedCommandException(ProtocolCommand.SCAN);
	}

	@Override
	default ScanResult<List<byte[]>> scan(final long cursor, final byte[] pattern, final int count){
		throw new NotSupportedCommandException(ProtocolCommand.SCAN);
	}

	@Override
	default ScanResult<List<String>> scan(final String cursor, final String pattern, final int count){
		throw new NotSupportedCommandException(ProtocolCommand.SCAN);
	}

	@Override
	default ScanResult<List<byte[]>> scan(final byte[] cursor, final byte[] pattern, final int count){
		throw new NotSupportedCommandException(ProtocolCommand.SCAN);
	}

	@Override
	default Long sort(final String key, final String destKey){
		throw new NotSupportedCommandException(ProtocolCommand.SORT);
	}

	@Override
	default Long sort(final byte[] key, final byte[] destKey){
		throw new NotSupportedCommandException(ProtocolCommand.SORT);
	}

	@Override
	default Long sort(final String key, final String destKey, final SortArgument sortArgument){
		throw new NotSupportedCommandException(ProtocolCommand.SORT);
	}

	@Override
	default Long sort(final byte[] key, final byte[] destKey, final SortArgument sortArgument){
		throw new NotSupportedCommandException(ProtocolCommand.SORT);
	}

	@Override
	default Status mSet(final Map<String, String> values){
		throw new NotSupportedCommandException(ProtocolCommand.MGET);
	}

	@Override
	default Status mSetNx(final Map<String, String> values){
		throw new NotSupportedCommandException(ProtocolCommand.MGET);
	}

	@Override
	default List<String> mGet(final String... keys){
		throw new NotSupportedCommandException(ProtocolCommand.MGET);
	}

	@Override
	default List<byte[]> mGet(final byte[]... keys){
		throw new NotSupportedCommandException(ProtocolCommand.MGET);
	}

	@Override
	default String rPoplPush(final String source, final String destKey){
		throw new NotSupportedCommandException(ProtocolCommand.RPOPLPUSH);
	}

	@Override
	default byte[] rPoplPush(final byte[] source, final byte[] destKey){
		throw new NotSupportedCommandException(ProtocolCommand.RPOPLPUSH);
	}

	@Override
	default String brPoplPush(final String source, final String destKey, final int timeout){
		throw new NotSupportedCommandException(ProtocolCommand.BRPOPLPUSH);
	}

	@Override
	default byte[] brPoplPush(final byte[] source, final byte[] destKey, final int timeout){
		throw new NotSupportedCommandException(ProtocolCommand.BRPOPLPUSH);
	}

	@Override
	default Set<String> sDiff(final String... keys){
		throw new NotSupportedCommandException(ProtocolCommand.SDIFF);
	}

	@Override
	default Set<byte[]> sDiff(byte[]... keys){
		throw new NotSupportedCommandException(ProtocolCommand.SDIFF);
	}

	@Override
	default Long sDiffStore(final String destKey, final String... keys){
		throw new NotSupportedCommandException(ProtocolCommand.SDIFFSTORE);
	}

	@Override
	default Long sDiffStore(final byte[] destKey, final byte[]... keys){
		throw new NotSupportedCommandException(ProtocolCommand.SDIFFSTORE);
	}

	@Override
	default Set<String> sInter(final String... keys){
		throw new NotSupportedCommandException(ProtocolCommand.SINTER);
	}

	@Override
	default Set<byte[]> sInter(final byte[]... keys){
		throw new NotSupportedCommandException(ProtocolCommand.SINTER);
	}

	@Override
	default Long sInterStore(final String destKey, final String... keys){
		throw new NotSupportedCommandException(ProtocolCommand.SINTERSTORE);
	}

	@Override
	default Long sInterStore(final byte[] destKey, final byte[]... keys){
		throw new NotSupportedCommandException(ProtocolCommand.SINTERSTORE);
	}

	@Override
	default Set<String> sUnion(final String... keys){
		throw new NotSupportedCommandException(ProtocolCommand.SUNION);
	}

	@Override
	default Set<byte[]> sUnion(final byte[]... keys){
		throw new NotSupportedCommandException(ProtocolCommand.SUNION);
	}

	@Override
	default Long sUnionStore(final String destKey, final String... keys){
		throw new NotSupportedCommandException(ProtocolCommand.SUNIONSTORE);
	}

	@Override
	default Long sUnionStore(final byte[] destKey, final byte[]... keys){
		throw new NotSupportedCommandException(ProtocolCommand.SUNIONSTORE);
	}

	@Override
	default Status sMove(final String source, final String destKey, final String member){
		throw new NotSupportedCommandException(ProtocolCommand.SMOVE);
	}

	@Override
	default Status sMove(final byte[] source, final byte[] destKey, final byte[] member){
		throw new NotSupportedCommandException(ProtocolCommand.SMOVE);
	}

	@Override
	default Long zInterStore(final String destKey, final String... keys){
		throw new NotSupportedCommandException(ProtocolCommand.ZINTERSTORE);
	}

	@Override
	default Long zInterStore(final byte[] destKey, final byte[]... keys){
		throw new NotSupportedCommandException(ProtocolCommand.ZINTERSTORE);
	}

	@Override
	default Long zInterStore(final String destKey, final Aggregate aggregate, final String... keys){
		throw new NotSupportedCommandException(ProtocolCommand.ZINTERSTORE);
	}

	@Override
	default Long zInterStore(final byte[] destKey, final Aggregate aggregate, final byte[]... keys){
		throw new NotSupportedCommandException(ProtocolCommand.ZINTERSTORE);
	}

	@Override
	default Long zInterStore(final String destKey, final double[] weights, final String... keys){
		throw new NotSupportedCommandException(ProtocolCommand.ZINTERSTORE);
	}

	@Override
	default Long zInterStore(final byte[] destKey, final double[] weights, final byte[]... keys){
		throw new NotSupportedCommandException(ProtocolCommand.ZINTERSTORE);
	}

	@Override
	default Long zInterStore(final String destKey, final Aggregate aggregate, final double[] weights,
			final String... keys){
		throw new NotSupportedCommandException(ProtocolCommand.ZINTERSTORE);
	}

	@Override
	default Long zInterStore(final byte[] destKey, final Aggregate aggregate, final double[] weights,
			final byte[]... keys){
		throw new NotSupportedCommandException(ProtocolCommand.ZINTERSTORE);
	}

	@Override
	default Long zUnionStore(final String destKey, final String... keys){
		throw new NotSupportedCommandException(ProtocolCommand.ZUNIONSTORE);
	}

	@Override
	default Long zUnionStore(final byte[] destKey, final byte[]... keys){
		throw new NotSupportedCommandException(ProtocolCommand.ZUNIONSTORE);
	}

	@Override
	default Long zUnionStore(final String destKey, final Aggregate aggregate, final String... keys){
		throw new NotSupportedCommandException(ProtocolCommand.ZUNIONSTORE);
	}

	@Override
	default Long zUnionStore(final byte[] destKey, final Aggregate aggregate, final byte[]... keys){
		throw new NotSupportedCommandException(ProtocolCommand.ZUNIONSTORE);
	}

	@Override
	default Long zUnionStore(final String destKey, final double[] weights, final String... keys){
		throw new NotSupportedCommandException(ProtocolCommand.ZUNIONSTORE);
	}

	@Override
	default Long zUnionStore(final byte[] destKey, final double[] weights, final byte[]... keys){
		throw new NotSupportedCommandException(ProtocolCommand.ZUNIONSTORE);
	}

	@Override
	default Long zUnionStore(final String destKey, final Aggregate aggregate, final double[] weights,
			final String... keys){
		throw new NotSupportedCommandException(ProtocolCommand.ZUNIONSTORE);
	}

	@Override
	default Long zUnionStore(final byte[] destKey, final Aggregate aggregate, final double[] weights,
			final byte[]... keys){
		throw new NotSupportedCommandException(ProtocolCommand.ZUNIONSTORE);
	}

	@Override
	default Status pfMerge(final String destKey, final String... keys){
		throw new NotSupportedCommandException(ProtocolCommand.PFMERGE);
	}

	@Override
	default Status pfMerge(final byte[] destKey, final byte[]... keys){
		throw new NotSupportedCommandException(ProtocolCommand.PFMERGE);
	}

	@Override
	default Long pfCount(final String... keys){
		throw new NotSupportedCommandException(ProtocolCommand.PFCOUNT);
	}

	@Override
	default Long pfCount(final byte[]... keys){
		throw new NotSupportedCommandException(ProtocolCommand.PFCOUNT);
	}

}
