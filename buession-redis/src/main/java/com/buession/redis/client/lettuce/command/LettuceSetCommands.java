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
package com.buession.redis.client.lettuce.command;

import com.buession.core.converter.BooleanStatusConverter;
import com.buession.lang.Status;
import com.buession.redis.client.lettuce.LettuceRedisClient;
import com.buession.redis.core.Keyword;
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.command.RedisCommand;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.SetCommands;
import com.buession.redis.core.internal.convert.Converters;
import com.buession.redis.core.internal.convert.lettuce.response.ScanCursorConverter;
import com.buession.redis.core.internal.lettuce.args.LettuceScanArgs;
import com.buession.redis.core.internal.lettuce.args.LettuceScanCursor;
import com.buession.redis.utils.SafeEncoder;
import io.lettuce.core.ScanArgs;

import java.util.List;
import java.util.Set;

/**
 * Lettuce 集合命令
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public final class LettuceSetCommands extends AbstractLettuceRedisCommands implements SetCommands {

	public LettuceSetCommands(final LettuceRedisClient client) {
		super(client);
	}

	@Override
	public Long sAdd(final String key, final String... members) {
		final CommandArguments args = CommandArguments.create(key).add(members);
		return executeCommand(RedisCommand.SADD, args,
				(cmd)->cmd.sadd(SafeEncoder.encode(key), SafeEncoder.encode(members)),
				(cmd)->cmd.sadd(SafeEncoder.encode(key), SafeEncoder.encode(members)));
	}

	@Override
	public Long sAdd(final byte[] key, final byte[]... members) {
		final CommandArguments args = CommandArguments.create(key).add(members);
		return executeCommand(RedisCommand.SADD, args, (cmd)->cmd.sadd(key, members),
				(cmd)->cmd.sadd(key, members));
	}

	@Override
	public Long sCard(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.SCARD, args, (cmd)->cmd.scard(SafeEncoder.encode(key)),
				(cmd)->cmd.scard(SafeEncoder.encode(key)));
	}

	@Override
	public Long sCard(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.SCARD, args, (cmd)->cmd.scard(key), (cmd)->cmd.scard(key));
	}

	@Override
	public Set<String> sDiff(final String... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return executeCommand(RedisCommand.SDIFF, args, (cmd)->cmd.sdiff(SafeEncoder.encode(keys)),
				(cmd)->cmd.sdiff(SafeEncoder.encode(keys)), Converters.binarySetStringSetConverter());
	}

	@Override
	public Set<byte[]> sDiff(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return executeCommand(RedisCommand.SDIFF, args, (cmd)->cmd.sdiff(keys),
				(cmd)->cmd.sdiff(keys));
	}

	@Override
	public Long sDiffStore(final String destKey, final String... keys) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys);
		return executeCommand(RedisCommand.SDIFFSTORE, args,
				(cmd)->cmd.sdiffstore(SafeEncoder.encode(destKey), SafeEncoder.encode(keys)),
				(cmd)->cmd.sdiffstore(SafeEncoder.encode(destKey), SafeEncoder.encode(keys)));
	}

	@Override
	public Long sDiffStore(final byte[] destKey, final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys);
		return executeCommand(RedisCommand.SDIFFSTORE, args, (cmd)->cmd.sdiffstore(destKey, keys),
				(cmd)->cmd.sdiffstore(destKey, keys));
	}

	@Override
	public Set<String> sInter(final String... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return executeCommand(RedisCommand.SINTER, args, (cmd)->cmd.sinter(SafeEncoder.encode(keys)),
				(cmd)->cmd.sinter(SafeEncoder.encode(keys)), Converters.binarySetStringSetConverter());
	}

	@Override
	public Set<byte[]> sInter(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return executeCommand(RedisCommand.SINTER, args, (cmd)->cmd.sinter(keys),
				(cmd)->cmd.sinter(keys));
	}

	@Override
	public Long sInterCard(final String... keys) {
		final CommandArguments args = CommandArguments.create(keys.length).add(keys);
		return executeCommand(RedisCommand.SINTERCARD, args, (cmd)->cmd.sintercard(SafeEncoder.encode(keys)),
				(cmd)->cmd.sintercard(SafeEncoder.encode(keys)));
	}

	@Override
	public Long sInterCard(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(keys.length).add(keys);
		return executeCommand(RedisCommand.SINTERCARD, args, (cmd)->cmd.sintercard(keys),
				(cmd)->cmd.sintercard(keys));
	}

	@Override
	public Long sInterCard(final String[] keys, final int limit) {
		final CommandArguments args = CommandArguments.create(keys.length).add(keys).add(Keyword.Common.LIMIT)
				.add(limit);
		return executeCommand(RedisCommand.SINTERCARD, args, (cmd)->cmd.sintercard(limit, SafeEncoder.encode(keys)),
				(cmd)->cmd.sintercard(limit, SafeEncoder.encode(keys)));
	}

	@Override
	public Long sInterCard(final byte[][] keys, final int limit) {
		final CommandArguments args = CommandArguments.create(keys.length).add(keys).add(Keyword.Common.LIMIT)
				.add(limit);
		return executeCommand(RedisCommand.SINTERCARD, args, (cmd)->cmd.sintercard(limit, keys),
				(cmd)->cmd.sintercard(limit, keys));
	}

	@Override
	public Long sInterStore(final String destKey, final String... keys) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys);
		return executeCommand(RedisCommand.SINTERSTORE, args,
				(cmd)->cmd.sinterstore(SafeEncoder.encode(destKey), SafeEncoder.encode(keys)),
				(cmd)->cmd.sinterstore(SafeEncoder.encode(destKey), SafeEncoder.encode(keys)));
	}

	@Override
	public Long sInterStore(final byte[] destKey, final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys);
		return executeCommand(RedisCommand.SINTERSTORE, args, (cmd)->cmd.sinterstore(destKey, keys),
				(cmd)->cmd.sinterstore(destKey, keys));
	}

	@Override
	public Boolean sIsMember(final String key, final String member) {
		final CommandArguments args = CommandArguments.create(key, member);
		return executeCommand(RedisCommand.SISMEMBER, args,
				(cmd)->cmd.sismember(SafeEncoder.encode(key), SafeEncoder.encode(member)),
				(cmd)->cmd.sismember(SafeEncoder.encode(key), SafeEncoder.encode(member)));
	}

	@Override
	public Boolean sIsMember(final byte[] key, final byte[] member) {
		final CommandArguments args = CommandArguments.create(key, member);
		return executeCommand(RedisCommand.SISMEMBER, args, (cmd)->cmd.sismember(key, member),
				(cmd)->cmd.sismember(key, member));
	}

	@Override
	public Set<String> sMembers(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.SMEMBERS, args, (cmd)->cmd.smembers(SafeEncoder.encode(key)),
				(cmd)->cmd.smembers(SafeEncoder.encode(key)), Converters.binarySetStringSetConverter());
	}

	@Override
	public Set<byte[]> sMembers(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.SMEMBERS, args, (cmd)->cmd.smembers(key),
				(cmd)->cmd.smembers(key));
	}

	@Override
	public List<Boolean> smIsMember(final String key, final String... members) {
		final CommandArguments args = CommandArguments.create(key).add(members);
		return executeCommand(RedisCommand.SMISMEMBER, args,
				(cmd)->cmd.smismember(SafeEncoder.encode(key), SafeEncoder.encode(members)),
				(cmd)->cmd.smismember(SafeEncoder.encode(key), SafeEncoder.encode(members)));
	}

	@Override
	public List<Boolean> smIsMember(final byte[] key, final byte[]... members) {
		final CommandArguments args = CommandArguments.create(key).add(members);
		return executeCommand(RedisCommand.SMISMEMBER, args, (cmd)->cmd.smismember(key, members),
				(cmd)->cmd.smismember(key, members));
	}

	@Override
	public Status sMove(final String key, final String destKey, final String member) {
		final CommandArguments args = CommandArguments.create(key, destKey).add(member);
		return executeCommand(RedisCommand.SMOVE, args,
				(cmd)->cmd.smove(SafeEncoder.encode(key), SafeEncoder.encode(destKey), SafeEncoder.encode(member)),
				(cmd)->cmd.smove(SafeEncoder.encode(key), SafeEncoder.encode(destKey), SafeEncoder.encode(member)),
				new BooleanStatusConverter());
	}

	@Override
	public Status sMove(final byte[] key, final byte[] destKey, final byte[] member) {
		final CommandArguments args = CommandArguments.create(key, destKey).add(member);
		return executeCommand(RedisCommand.SMOVE, args, (cmd)->cmd.smove(key, destKey, member),
				(cmd)->cmd.smove(key, destKey, member), new BooleanStatusConverter());
	}

	@Override
	public String sPop(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.SPOP, args, (cmd)->cmd.spop(SafeEncoder.encode(key)),
				(cmd)->cmd.spop(SafeEncoder.encode(key)), SafeEncoder::encode);
	}

	@Override
	public byte[] sPop(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.SPOP, args, (cmd)->cmd.spop(key), (cmd)->cmd.spop(key));
	}

	@Override
	public Set<String> sPop(final String key, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(count);
		return executeCommand(RedisCommand.SPOP, args, (cmd)->cmd.spop(SafeEncoder.encode(key), count),
				(cmd)->cmd.spop(SafeEncoder.encode(key), count), Converters.binarySetStringSetConverter());
	}

	@Override
	public Set<byte[]> sPop(final byte[] key, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(count);
		return executeCommand(RedisCommand.SPOP, args, (cmd)->cmd.spop(key, count),
				(cmd)->cmd.spop(key, count));
	}

	@Override
	public String sRandMember(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.SRANDMEMBER, args, (cmd)->cmd.srandmember(SafeEncoder.encode(key)),
				(cmd)->cmd.srandmember(SafeEncoder.encode(key)), SafeEncoder::encode);
	}

	@Override
	public byte[] sRandMember(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(RedisCommand.SRANDMEMBER, args, (cmd)->cmd.srandmember(key),
				(cmd)->cmd.srandmember(key));
	}

	@Override
	public List<String> sRandMember(final String key, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(count);
		return executeCommand(RedisCommand.SRANDMEMBER, args, (cmd)->cmd.srandmember(SafeEncoder.encode(key), count),
				(cmd)->cmd.srandmember(SafeEncoder.encode(key), count), Converters.binaryListStringListConverter());
	}

	@Override
	public List<byte[]> sRandMember(final byte[] key, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(count);
		return executeCommand(RedisCommand.SRANDMEMBER, args, (cmd)->cmd.srandmember(key, count),
				(cmd)->cmd.srandmember(key, count));
	}

	@Override
	public Long sRem(final String key, final String... members) {
		final CommandArguments args = CommandArguments.create(key).add(members);
		return executeCommand(RedisCommand.SREM, args,
				(cmd)->cmd.srem(SafeEncoder.encode(key), SafeEncoder.encode(members)),
				(cmd)->cmd.srem(SafeEncoder.encode(key), SafeEncoder.encode(members)));
	}

	@Override
	public Long sRem(final byte[] key, final byte[]... members) {
		final CommandArguments args = CommandArguments.create(key).add(members);
		return executeCommand(RedisCommand.SREM, args, (cmd)->cmd.srem(key, members),
				(cmd)->cmd.srem(key, members));
	}

	@Override
	public ScanResult<String> sScan(final String key, final String cursor) {
		final CommandArguments args = CommandArguments.create(key).add(cursor);
		return executeCommand(RedisCommand.SSCAN, args,
				(cmd)->cmd.sscan(SafeEncoder.encode(key), new LettuceScanCursor(cursor)),
				(cmd)->cmd.sscan(SafeEncoder.encode(key), new LettuceScanCursor(cursor)),
				new ScanCursorConverter.ValueScanCursorConverter<>(SafeEncoder::encode));
	}

	@Override
	public ScanResult<byte[]> sScan(final byte[] key, final byte[] cursor) {
		final CommandArguments args = CommandArguments.create(key).add(cursor);
		return executeCommand(RedisCommand.SSCAN, args, (cmd)->cmd.sscan(key, new LettuceScanCursor(cursor)),
				(cmd)->cmd.sscan(key, new LettuceScanCursor(cursor)),
				new ScanCursorConverter.ValueScanCursorConverter<>((v)->v));
	}

	@Override
	public ScanResult<String> sScan(final String key, final String cursor, final String pattern) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(Keyword.Scan.MATCH).add(pattern);
		return sScan(SafeEncoder.encode(key), cursor, new LettuceScanArgs(pattern), args);
	}

	@Override
	public ScanResult<byte[]> sScan(final byte[] key, final byte[] cursor, final byte[] pattern) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(Keyword.Scan.MATCH).add(pattern);
		return sScan(key, cursor, new LettuceScanArgs(pattern), args);
	}

	@Override
	public ScanResult<String> sScan(final String key, final String cursor, final String pattern, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(Keyword.Scan.MATCH).add(pattern)
				.add(Keyword.Common.COUNT).add(count);
		return sScan(SafeEncoder.encode(key), cursor, new LettuceScanArgs(pattern, count), args);
	}

	@Override
	public ScanResult<byte[]> sScan(final byte[] key, final byte[] cursor, final byte[] pattern, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(Keyword.Scan.MATCH).add(pattern)
				.add(Keyword.Common.COUNT).add(count);
		return sScan(key, cursor, new LettuceScanArgs(pattern, count), args);
	}

	@Override
	public ScanResult<String> sScan(final String key, final String cursor, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(Keyword.Common.COUNT).add(count);
		return sScan(SafeEncoder.encode(key), cursor, new LettuceScanArgs(count), args);
	}

	@Override
	public ScanResult<byte[]> sScan(final byte[] key, final byte[] cursor, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(Keyword.Common.COUNT).add(count);
		return sScan(key, cursor, new LettuceScanArgs(count), args);
	}

	@Override
	public Set<String> sUnion(final String... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return executeCommand(RedisCommand.SUNION, args, (cmd)->cmd.sunion(SafeEncoder.encode(keys)),
				(cmd)->cmd.sunion(SafeEncoder.encode(keys)), Converters.binarySetStringSetConverter());
	}

	@Override
	public Set<byte[]> sUnion(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return executeCommand(RedisCommand.SUNION, args, (cmd)->cmd.sunion(keys),
				(cmd)->cmd.sunion(keys));
	}

	@Override
	public Long sUnionStore(final String destKey, final String... keys) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys);
		return executeCommand(RedisCommand.SUNIONSTORE, args,
				(cmd)->cmd.sunionstore(SafeEncoder.encode(destKey), SafeEncoder.encode(keys)),
				(cmd)->cmd.sunionstore(SafeEncoder.encode(destKey), SafeEncoder.encode(keys)));
	}

	@Override
	public Long sUnionStore(final byte[] destKey, final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys);
		return executeCommand(RedisCommand.SUNIONSTORE, args, (cmd)->cmd.sunionstore(destKey, keys),
				(cmd)->cmd.sunionstore(destKey, keys));
	}

	private ScanResult<String> sScan(final byte[] key, final String cursor, final ScanArgs scanArgs,
	                                 final CommandArguments args) {
		return executeCommand(RedisCommand.SSCAN, args, (cmd)->cmd.sscan(key, new LettuceScanCursor(cursor), scanArgs),
				(cmd)->cmd.sscan(key, new LettuceScanCursor(cursor), scanArgs),
				new ScanCursorConverter.ValueScanCursorConverter<>(SafeEncoder::encode));
	}

	private ScanResult<byte[]> sScan(final byte[] key, final byte[] cursor, final ScanArgs scanArgs,
	                                 final CommandArguments args) {
		return executeCommand(RedisCommand.SSCAN, args, (cmd)->cmd.sscan(key, new LettuceScanCursor(cursor), scanArgs),
				(cmd)->cmd.sscan(key, new LettuceScanCursor(cursor), scanArgs),
				new ScanCursorConverter.ValueScanCursorConverter<>((v)->v));
	}

}
