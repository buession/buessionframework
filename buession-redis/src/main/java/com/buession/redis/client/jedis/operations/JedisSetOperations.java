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
 * | Copyright @ 2013-2022 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.client.jedis.operations;

import com.buession.lang.Status;
import com.buession.redis.client.connection.jedis.JedisConnection;
import com.buession.redis.client.jedis.JedisStandaloneClient;
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.internal.convert.Converters;
import com.buession.redis.core.internal.convert.jedis.response.ScanResultConverter;
import com.buession.redis.core.internal.jedis.JedisScanParams;

import java.util.List;
import java.util.Set;

/**
 * Jedis 单机模式集合命令操作
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public final class JedisSetOperations extends AbstractSetOperations<JedisConnection> {

	public JedisSetOperations(final JedisStandaloneClient client){
		super(client);
	}

	@Override
	public long sAdd(final String key, final String... members){
		final CommandArguments args = CommandArguments.create("key", key).put("members", members);
		final JedisCommand<Long> command = JedisCommand.<Long>create(ProtocolCommand.SADD)
				.general((cmd)->cmd.sadd(key, members)).pipeline((cmd)->cmd.sadd(key, members))
				.transaction((cmd)->cmd.sadd(key, members));
		return execute(command, args);
	}

	@Override
	public long sAdd(final byte[] key, final byte[]... members){
		final CommandArguments args = CommandArguments.create("key", key).put("members", members);
		final JedisCommand<Long> command = JedisCommand.<Long>create(ProtocolCommand.SADD)
				.general((cmd)->cmd.sadd(key, members)).pipeline((cmd)->cmd.sadd(key, members))
				.transaction((cmd)->cmd.sadd(key, members));
		return execute(command, args);
	}

	@Override
	public long sCard(final String key){
		final CommandArguments args = CommandArguments.create("key", key);
		final JedisCommand<Long> command = JedisCommand.<Long>create(ProtocolCommand.SCARD)
				.general((cmd)->cmd.scard(key)).pipeline((cmd)->cmd.scard(key)).transaction((cmd)->cmd.scard(key));
		return execute(command, args);
	}

	@Override
	public long sCard(final byte[] key){
		final CommandArguments args = CommandArguments.create("key", key);
		final JedisCommand<Long> command = JedisCommand.<Long>create(ProtocolCommand.SCARD)
				.general((cmd)->cmd.scard(key)).pipeline((cmd)->cmd.scard(key)).transaction((cmd)->cmd.scard(key));
		return execute(command, args);
	}

	@Override
	public Set<String> sDiff(final String... keys){
		final CommandArguments args = CommandArguments.create("keys", keys);
		final JedisCommand<Set<String>> command = JedisCommand.<Set<String>>create(ProtocolCommand.SDIFF)
				.general((cmd)->cmd.sdiff(keys)).pipeline((cmd)->cmd.sdiff(keys)).transaction((cmd)->cmd.sdiff(keys));
		return execute(command, args);
	}

	@Override
	public Set<byte[]> sDiff(final byte[]... keys){
		final CommandArguments args = CommandArguments.create("keys", keys);
		final JedisCommand<Set<byte[]>> command = JedisCommand.<Set<byte[]>>create(ProtocolCommand.SDIFF)
				.general((cmd)->cmd.sdiff(keys)).pipeline((cmd)->cmd.sdiff(keys)).transaction((cmd)->cmd.sdiff(keys));
		return execute(command, args);
	}

	@Override
	public long sDiffStore(final String destKey, final String... keys){
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("keys", keys);
		final JedisCommand<Long> command = JedisCommand.<Long>create(ProtocolCommand.SDIFFSTORE)
				.general((cmd)->cmd.sdiffstore(destKey, keys)).pipeline((cmd)->cmd.sdiffstore(destKey, keys))
				.transaction((cmd)->cmd.sdiffstore(destKey, keys));
		return execute(command, args);
	}

	@Override
	public long sDiffStore(final byte[] destKey, final byte[]... keys){
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("keys", keys);
		final JedisCommand<Long> command = JedisCommand.<Long>create(ProtocolCommand.SDIFFSTORE)
				.general((cmd)->cmd.sdiffstore(destKey, keys)).pipeline((cmd)->cmd.sdiffstore(destKey, keys))
				.transaction((cmd)->cmd.sdiffstore(destKey, keys));
		return execute(command, args);
	}

	@Override
	public Set<String> sInter(final String... keys){
		final CommandArguments args = CommandArguments.create("keys", keys);
		final JedisCommand<Set<String>> command = JedisCommand.<Set<String>>create(ProtocolCommand.SINTER)
				.general((cmd)->cmd.sinter(keys)).pipeline((cmd)->cmd.sinter(keys))
				.transaction((cmd)->cmd.sinter(keys));
		return execute(command, args);
	}

	@Override
	public Set<byte[]> sInter(final byte[]... keys){
		final CommandArguments args = CommandArguments.create("keys", keys);
		final JedisCommand<Set<byte[]>> command = JedisCommand.<Set<byte[]>>create(ProtocolCommand.SINTER)
				.general((cmd)->cmd.sinter(keys)).pipeline((cmd)->cmd.sinter(keys))
				.transaction((cmd)->cmd.sinter(keys));
		return execute(command, args);
	}

	@Override
	public long sInterStore(final String destKey, final String... keys){
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("keys", keys);
		final JedisCommand<Long> command = JedisCommand.<Long>create(ProtocolCommand.SINTERSTORE)
				.general((cmd)->cmd.sinterstore(destKey, keys)).pipeline((cmd)->cmd.sinterstore(destKey, keys))
				.transaction((cmd)->cmd.sinterstore(destKey, keys));
		return execute(command, args);
	}

	@Override
	public long sInterStore(final byte[] destKey, final byte[]... keys){
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("keys", keys);
		final JedisCommand<Long> command = JedisCommand.<Long>create(ProtocolCommand.SINTERSTORE)
				.general((cmd)->cmd.sinterstore(destKey, keys)).pipeline((cmd)->cmd.sinterstore(destKey, keys))
				.transaction((cmd)->cmd.sinterstore(destKey, keys));
		return execute(command, args);
	}

	@Override
	public boolean sIsMember(final String key, final String member){
		final CommandArguments args = CommandArguments.create("key", key).put("member", member);
		final JedisCommand<Boolean> command = JedisCommand.<Boolean>create(ProtocolCommand.SISMEMBER)
				.general((cmd)->cmd.sismember(key, member)).pipeline((cmd)->cmd.sismember(key, member))
				.transaction((cmd)->cmd.sismember(key, member));
		return execute(command, args);
	}

	@Override
	public boolean sIsMember(final byte[] key, final byte[] member){
		final CommandArguments args = CommandArguments.create("key", key).put("member", member);
		final JedisCommand<Boolean> command = JedisCommand.<Boolean>create(ProtocolCommand.SISMEMBER)
				.general((cmd)->cmd.sismember(key, member)).pipeline((cmd)->cmd.sismember(key, member))
				.transaction((cmd)->cmd.sismember(key, member));
		return execute(command, args);
	}

	@Override
	public Set<String> sMembers(final String key){
		final CommandArguments args = CommandArguments.create("key", key);
		final JedisCommand<Set<String>> command = JedisCommand.<Set<String>>create(ProtocolCommand.SMEMBERS)
				.general((cmd)->cmd.smembers(key)).pipeline((cmd)->cmd.smembers(key))
				.transaction((cmd)->cmd.smembers(key));
		return execute(command, args);
	}

	@Override
	public Set<byte[]> sMembers(final byte[] key){
		final CommandArguments args = CommandArguments.create("key", key);
		final JedisCommand<Set<byte[]>> command = JedisCommand.<Set<byte[]>>create(ProtocolCommand.SMEMBERS)
				.general((cmd)->cmd.smembers(key)).pipeline((cmd)->cmd.smembers(key))
				.transaction((cmd)->cmd.smembers(key));
		return execute(command, args);
	}

	@Override
	public Status sMove(final String key, final String destKey, final String member){
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey).put("member", member);
		final JedisCommand<Status> command = JedisCommand.<Status>create(ProtocolCommand.SMOVE)
				.general((cmd)->cmd.smove(key, destKey, member), Converters.ONE_STATUS_CONVERTER)
				.pipeline((cmd)->cmd.smove(key, destKey, member), Converters.ONE_STATUS_CONVERTER)
				.transaction((cmd)->cmd.smove(key, destKey, member), Converters.ONE_STATUS_CONVERTER);
		return execute(command, args);
	}

	@Override
	public Status sMove(final byte[] key, final byte[] destKey, final byte[] member){
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey).put("member", member);
		final JedisCommand<Status> command = JedisCommand.<Status>create(ProtocolCommand.SMOVE)
				.general((cmd)->cmd.smove(key, destKey, member), Converters.ONE_STATUS_CONVERTER)
				.pipeline((cmd)->cmd.smove(key, destKey, member), Converters.ONE_STATUS_CONVERTER)
				.transaction((cmd)->cmd.smove(key, destKey, member), Converters.ONE_STATUS_CONVERTER);
		return execute(command, args);
	}

	@Override
	public String sPop(final String key){
		final CommandArguments args = CommandArguments.create("key", key);
		final JedisCommand<String> command = JedisCommand.<String>create(ProtocolCommand.SPOP)
				.general((cmd)->cmd.spop(key)).pipeline((cmd)->cmd.spop(key)).transaction((cmd)->cmd.spop(key));
		return execute(command, args);
	}

	@Override
	public byte[] sPop(final byte[] key){
		final CommandArguments args = CommandArguments.create("key", key);
		final JedisCommand<byte[]> command = JedisCommand.<byte[]>create(ProtocolCommand.SPOP)
				.general((cmd)->cmd.spop(key)).pipeline((cmd)->cmd.spop(key)).transaction((cmd)->cmd.spop(key));
		return execute(command, args);
	}

	@Override
	public Set<String> sPop(final String key, final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("count", count);
		final JedisCommand<Set<String>> command = JedisCommand.<Set<String>>create(ProtocolCommand.SPOP)
				.general((cmd)->cmd.spop(key, count)).pipeline((cmd)->cmd.spop(key, count))
				.transaction((cmd)->cmd.spop(key, count));
		return execute(command, args);
	}

	@Override
	public Set<byte[]> sPop(final byte[] key, final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("count", count);
		final JedisCommand<Set<byte[]>> command = JedisCommand.<Set<byte[]>>create(ProtocolCommand.SPOP)
				.general((cmd)->cmd.spop(key, count)).pipeline((cmd)->cmd.spop(key, count))
				.transaction((cmd)->cmd.spop(key, count));
		return execute(command, args);
	}

	@Override
	public String sRandMember(final String key){
		final CommandArguments args = CommandArguments.create("key", key);
		final JedisCommand<String> command = JedisCommand.<String>create(ProtocolCommand.SRANDMEMBER)
				.general((cmd)->cmd.srandmember(key)).pipeline((cmd)->cmd.srandmember(key));
		return execute(command, args);
	}

	@Override
	public byte[] sRandMember(final byte[] key){
		final CommandArguments args = CommandArguments.create("key", key);
		final JedisCommand<byte[]> command = JedisCommand.<byte[]>create(ProtocolCommand.SRANDMEMBER)
				.general((cmd)->cmd.srandmember(key)).pipeline((cmd)->cmd.srandmember(key));
		return execute(command, args);
	}

	@Override
	public List<String> sRandMember(final String key, final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("count", count);
		final JedisCommand<List<String>> command = JedisCommand.<List<String>>create(ProtocolCommand.SRANDMEMBER)
				.general((cmd)->cmd.srandmember(key, (int) count)).pipeline((cmd)->cmd.srandmember(key, (int) count));
		return execute(command, args);
	}

	@Override
	public List<byte[]> sRandMember(final byte[] key, final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("count", count);
		final JedisCommand<List<byte[]>> command = JedisCommand.<List<byte[]>>create(ProtocolCommand.SRANDMEMBER)
				.general((cmd)->cmd.srandmember(key, (int) count)).pipeline((cmd)->cmd.srandmember(key, (int) count));
		return execute(command, args);
	}

	@Override
	public long sRem(final String key, final String... members){
		final CommandArguments args = CommandArguments.create("key", key).put("members", members);
		final JedisCommand<Long> command = JedisCommand.<Long>create(ProtocolCommand.SREM)
				.general((cmd)->cmd.srem(key, members)).pipeline((cmd)->cmd.srem(key, members))
				.transaction((cmd)->cmd.srem(key, members));
		return execute(command, args);
	}

	@Override
	public long sRem(final byte[] key, final byte[]... members){
		final CommandArguments args = CommandArguments.create("key", key).put("members", members);
		final JedisCommand<Long> command = JedisCommand.<Long>create(ProtocolCommand.SREM)
				.general((cmd)->cmd.srem(key, members)).pipeline((cmd)->cmd.srem(key, members))
				.transaction((cmd)->cmd.srem(key, members));
		return execute(command, args);
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final String cursor){
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor);
		final JedisCommand<ScanResult<List<String>>> command = JedisCommand.<ScanResult<List<String>>>create(
						ProtocolCommand.SSCAN)
				.general((cmd)->cmd.sscan(key, cursor),
						ScanResultConverter.ListScanResultConverter.STRING_LIST_CONVERTER)
				.pipeline((cmd)->cmd.sscan(key, cursor),
						ScanResultConverter.ListScanResultConverter.STRING_LIST_CONVERTER)
				.transaction((cmd)->cmd.sscan(key, cursor),
						ScanResultConverter.ListScanResultConverter.STRING_LIST_CONVERTER);
		return execute(command, args);
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor){
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor);
		final JedisCommand<ScanResult<List<byte[]>>> command = JedisCommand.<ScanResult<List<byte[]>>>create(
						ProtocolCommand.SSCAN)
				.general((cmd)->cmd.sscan(key, cursor),
						ScanResultConverter.ListScanResultConverter.BINARY_LIST_CONVERTER)
				.pipeline((cmd)->cmd.sscan(key, cursor),
						ScanResultConverter.ListScanResultConverter.BINARY_LIST_CONVERTER)
				.transaction((cmd)->cmd.sscan(key, cursor),
						ScanResultConverter.ListScanResultConverter.BINARY_LIST_CONVERTER);
		return execute(command, args);
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final String cursor, final String pattern){
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor).put("pattern", pattern);
		final JedisScanParams params = new JedisScanParams(pattern);
		final JedisCommand<ScanResult<List<String>>> command = JedisCommand.<ScanResult<List<String>>>create(
						ProtocolCommand.SSCAN)
				.general((cmd)->cmd.sscan(key, cursor, params),
						ScanResultConverter.ListScanResultConverter.STRING_LIST_CONVERTER)
				.pipeline((cmd)->cmd.sscan(key, cursor, params),
						ScanResultConverter.ListScanResultConverter.STRING_LIST_CONVERTER)
				.transaction((cmd)->cmd.sscan(key, cursor, params),
						ScanResultConverter.ListScanResultConverter.STRING_LIST_CONVERTER);
		return execute(command, args);
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor, final byte[] pattern){
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor).put("pattern", pattern);
		final JedisScanParams params = new JedisScanParams(pattern);
		final JedisCommand<ScanResult<List<byte[]>>> command = JedisCommand.<ScanResult<List<byte[]>>>create(
						ProtocolCommand.SSCAN)
				.general((cmd)->cmd.sscan(key, cursor, params),
						ScanResultConverter.ListScanResultConverter.BINARY_LIST_CONVERTER)
				.pipeline((cmd)->cmd.sscan(key, cursor, params),
						ScanResultConverter.ListScanResultConverter.BINARY_LIST_CONVERTER)
				.transaction((cmd)->cmd.sscan(key, cursor, params),
						ScanResultConverter.ListScanResultConverter.BINARY_LIST_CONVERTER);
		return execute(command, args);
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final String cursor, final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor).put("count", count);
		final JedisScanParams params = new JedisScanParams(count);
		final JedisCommand<ScanResult<List<String>>> command = JedisCommand.<ScanResult<List<String>>>create(
						ProtocolCommand.SSCAN)
				.general((cmd)->cmd.sscan(key, cursor, params),
						ScanResultConverter.ListScanResultConverter.STRING_LIST_CONVERTER)
				.pipeline((cmd)->cmd.sscan(key, cursor, params),
						ScanResultConverter.ListScanResultConverter.STRING_LIST_CONVERTER)
				.transaction((cmd)->cmd.sscan(key, cursor, params),
						ScanResultConverter.ListScanResultConverter.STRING_LIST_CONVERTER);
		return execute(command, args);
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor, final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor).put("count", count);
		final JedisScanParams params = new JedisScanParams(count);
		final JedisCommand<ScanResult<List<byte[]>>> command = JedisCommand.<ScanResult<List<byte[]>>>create(
						ProtocolCommand.SSCAN)
				.general((cmd)->cmd.sscan(key, cursor, params),
						ScanResultConverter.ListScanResultConverter.BINARY_LIST_CONVERTER)
				.pipeline((cmd)->cmd.sscan(key, cursor, params),
						ScanResultConverter.ListScanResultConverter.BINARY_LIST_CONVERTER)
				.transaction((cmd)->cmd.sscan(key, cursor, params),
						ScanResultConverter.ListScanResultConverter.BINARY_LIST_CONVERTER);
		return execute(command, args);
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final String cursor, final String pattern,
										  final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor).put("pattern", pattern)
				.put("count", count);
		final JedisScanParams params = new JedisScanParams(pattern, count);
		final JedisCommand<ScanResult<List<String>>> command = JedisCommand.<ScanResult<List<String>>>create(
						ProtocolCommand.SSCAN)
				.general((cmd)->cmd.sscan(key, cursor, params),
						ScanResultConverter.ListScanResultConverter.STRING_LIST_CONVERTER)
				.pipeline((cmd)->cmd.sscan(key, cursor, params),
						ScanResultConverter.ListScanResultConverter.STRING_LIST_CONVERTER)
				.transaction((cmd)->cmd.sscan(key, cursor, params),
						ScanResultConverter.ListScanResultConverter.STRING_LIST_CONVERTER);
		return execute(command, args);
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor, final byte[] pattern,
										  final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor).put("pattern", pattern)
				.put("count", count);
		final JedisScanParams params = new JedisScanParams(pattern, count);
		final JedisCommand<ScanResult<List<byte[]>>> command = JedisCommand.<ScanResult<List<byte[]>>>create(
						ProtocolCommand.SSCAN)
				.general((cmd)->cmd.sscan(key, cursor, params),
						ScanResultConverter.ListScanResultConverter.BINARY_LIST_CONVERTER)
				.pipeline((cmd)->cmd.sscan(key, cursor, params),
						ScanResultConverter.ListScanResultConverter.BINARY_LIST_CONVERTER)
				.transaction((cmd)->cmd.sscan(key, cursor, params),
						ScanResultConverter.ListScanResultConverter.BINARY_LIST_CONVERTER);
		return execute(command, args);
	}

	@Override
	public Set<String> sUnion(final String... keys){
		final CommandArguments args = CommandArguments.create("keys", keys);
		final JedisCommand<Set<String>> command = JedisCommand.<Set<String>>create(ProtocolCommand.SUNION)
				.general((cmd)->cmd.sunion(keys)).pipeline((cmd)->cmd.sunion(keys))
				.transaction((cmd)->cmd.sunion(keys));
		return execute(command, args);
	}

	@Override
	public Set<byte[]> sUnion(final byte[]... keys){
		final CommandArguments args = CommandArguments.create("keys", keys);
		final JedisCommand<Set<byte[]>> command = JedisCommand.<Set<byte[]>>create(ProtocolCommand.SUNION)
				.general((cmd)->cmd.sunion(keys)).pipeline((cmd)->cmd.sunion(keys))
				.transaction((cmd)->cmd.sunion(keys));
		return execute(command, args);
	}

	@Override
	public long sUnionStore(final String destKey, final String... keys){
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("keys", keys);
		final JedisCommand<Long> command = JedisCommand.<Long>create(ProtocolCommand.SUNIONSTORE)
				.general((cmd)->cmd.sunionstore(destKey, keys)).pipeline((cmd)->cmd.sunionstore(destKey, keys))
				.transaction((cmd)->cmd.sunionstore(destKey, keys));
		return execute(command, args);
	}

	@Override
	public long sUnionStore(final byte[] destKey, final byte[]... keys){
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("keys", keys);
		final JedisCommand<Long> command = JedisCommand.<Long>create(ProtocolCommand.SUNIONSTORE)
				.general((cmd)->cmd.sunionstore(destKey, keys)).pipeline((cmd)->cmd.sunionstore(destKey, keys))
				.transaction((cmd)->cmd.sunionstore(destKey, keys));
		return execute(command, args);
	}

}
