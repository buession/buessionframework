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
import com.buession.core.converter.ListConverter;
import com.buession.core.converter.SetConverter;
import com.buession.lang.Status;
import com.buession.redis.client.lettuce.LettuceRedisClient;
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.command.Command;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.SetCommands;
import com.buession.redis.core.internal.convert.lettuce.response.ScanCursorConverter;
import com.buession.redis.core.internal.lettuce.LettuceScanArgs;
import com.buession.redis.core.internal.lettuce.LettuceScanCursor;
import com.buession.redis.utils.SafeEncoder;

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
		return sAdd(SafeEncoder.encode(key), SafeEncoder.encode(members));
	}

	@Override
	public Long sAdd(final byte[] key, final byte[]... members) {
		final CommandArguments args = CommandArguments.create(key).add(members);
		return executeCommand(Command.SADD, args, (cmd)->cmd.sadd(key, members), (v)->v);
	}

	@Override
	public Long sCard(final String key) {
		return sCard(SafeEncoder.encode(key));
	}

	@Override
	public Long sCard(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.SCARD, args, (cmd)->cmd.scard(key), (v)->v);
	}

	@Override
	public Set<String> sDiff(final String... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return executeCommand(Command.SDIFF, args, (cmd)->cmd.sdiff(SafeEncoder.encode(keys)),
				new SetConverter<>(SafeEncoder::encode));
	}

	@Override
	public Set<byte[]> sDiff(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return executeCommand(Command.SDIFF, args, (cmd)->cmd.sdiff(keys), (v)->v);
	}

	@Override
	public Long sDiffStore(final String destKey, final String... keys) {
		return sDiffStore(SafeEncoder.encode(destKey), SafeEncoder.encode(keys));
	}

	@Override
	public Long sDiffStore(final byte[] destKey, final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys);
		return executeCommand(Command.SDIFFSTORE, args, (cmd)->cmd.sdiffstore(destKey, keys), (v)->v);
	}

	@Override
	public Set<String> sInter(final String... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return executeCommand(Command.SINTER, args, (cmd)->cmd.sinter(SafeEncoder.encode(keys)),
				new SetConverter<>(SafeEncoder::encode));
	}

	@Override
	public Set<byte[]> sInter(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return executeCommand(Command.SINTER, args, (cmd)->cmd.sinter(keys), (v)->v);
	}

	@Override
	public Long sInterCard(final String... keys) {
		return sInterCard(SafeEncoder.encode(keys));
	}

	@Override
	public Long sInterCard(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return executeCommand(Command.SINTERCARD, args, (cmd)->cmd.sintercard(keys), (v)->v);
	}

	@Override
	public Long sInterCard(final String[] keys, final int limit) {
		return sInterCard(SafeEncoder.encode(keys), limit);
	}

	@Override
	public Long sInterCard(final byte[][] keys, final int limit) {
		final CommandArguments args = CommandArguments.create(keys).add(limit);
		return executeCommand(Command.SINTERCARD, args, (cmd)->cmd.sintercard(limit, keys), (v)->v);
	}

	@Override
	public Long sInterStore(final String destKey, final String... keys) {
		return sInterStore(SafeEncoder.encode(destKey), SafeEncoder.encode(keys));
	}

	@Override
	public Long sInterStore(final byte[] destKey, final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys);
		return executeCommand(Command.SINTERSTORE, args, (cmd)->cmd.sinterstore(destKey, keys), (v)->v);
	}

	@Override
	public Boolean sIsMember(final String key, final String member) {
		return sIsMember(SafeEncoder.encode(key), SafeEncoder.encode(member));
	}

	@Override
	public Boolean sIsMember(final byte[] key, final byte[] member) {
		final CommandArguments args = CommandArguments.create(key).add(member);
		return executeCommand(Command.SISMEMBER, args, (cmd)->cmd.sismember(key, member), (v)->v);
	}

	@Override
	public Set<String> sMembers(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.SMEMBERS, args, (cmd)->cmd.smembers(SafeEncoder.encode(key)),
				new SetConverter<>(SafeEncoder::encode));
	}

	@Override
	public Set<byte[]> sMembers(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.SMEMBERS, args, (cmd)->cmd.smembers(key), (v)->v);
	}

	@Override
	public List<Boolean> smIsMember(final String key, final String... members) {
		return smIsMember(SafeEncoder.encode(key), SafeEncoder.encode(members));
	}

	@Override
	public List<Boolean> smIsMember(final byte[] key, final byte[]... members) {
		final CommandArguments args = CommandArguments.create(key).add(members);
		return executeCommand(Command.SMISMEMBER, args, (cmd)->cmd.smismember(key, members), (v)->v);
	}

	@Override
	public Status sMove(final String key, final String destKey, final String member) {
		return sMove(SafeEncoder.encode(key), SafeEncoder.encode(destKey), SafeEncoder.encode(member));
	}

	@Override
	public Status sMove(final byte[] key, final byte[] destKey, final byte[] member) {
		final CommandArguments args = CommandArguments.create(key).add(destKey).add(member);
		return executeCommand(Command.SMOVE, args, (cmd)->cmd.smove(key, destKey, member),
				new BooleanStatusConverter());
	}

	@Override
	public String sPop(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.SPOP, args, (cmd)->cmd.spop(SafeEncoder.encode(key)),
				SafeEncoder::encode);
	}

	@Override
	public byte[] sPop(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.SPOP, args, (cmd)->cmd.spop(key), (v)->v);
	}

	@Override
	public Set<String> sPop(final String key, final long count) {
		final CommandArguments args = CommandArguments.create(key).add(count);
		return executeCommand(Command.SPOP, args, (cmd)->cmd.spop(SafeEncoder.encode(key), count),
				new SetConverter<>(SafeEncoder::encode));
	}

	@Override
	public Set<byte[]> sPop(final byte[] key, final long count) {
		final CommandArguments args = CommandArguments.create(key).add(count);
		return executeCommand(Command.SPOP, args, (cmd)->cmd.spop(key, count), (v)->v);
	}

	@Override
	public String sRandMember(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.SRANDMEMBER, args, (cmd)->cmd.srandmember(SafeEncoder.encode(key)),
				SafeEncoder::encode);
	}

	@Override
	public byte[] sRandMember(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.SRANDMEMBER, args, (cmd)->cmd.srandmember(key), (v)->v);
	}

	@Override
	public List<String> sRandMember(final String key, final long count) {
		final CommandArguments args = CommandArguments.create(key).add(count);
		return executeCommand(Command.SRANDMEMBER, args, (cmd)->cmd.srandmember(SafeEncoder.encode(key), count),
				new ListConverter<>(SafeEncoder::encode));
	}

	@Override
	public List<byte[]> sRandMember(final byte[] key, final long count) {
		final CommandArguments args = CommandArguments.create(key).add(count);
		return executeCommand(Command.SRANDMEMBER, args, (cmd)->cmd.srandmember(key, count), (v)->v);
	}

	@Override
	public Long sRem(final String key, final String... members) {
		return sRem(SafeEncoder.encode(key), SafeEncoder.encode(members));
	}

	@Override
	public Long sRem(final byte[] key, final byte[]... members) {
		final CommandArguments args = CommandArguments.create(key).add(members);
		return executeCommand(Command.SREM, args, (cmd)->cmd.srem(key, members), (v)->v);
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final String cursor) {
		final CommandArguments args = CommandArguments.create(key).add(cursor);
		return executeCommand(Command.SSCAN, args,
				(cmd)->cmd.sscan(SafeEncoder.encode(key), new LettuceScanCursor(cursor)),
				new ScanCursorConverter.ValueScanCursorConverter.BSKeyScanCursorConverter());
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor) {
		final CommandArguments args = CommandArguments.create(key).add(cursor);
		return executeCommand(Command.SSCAN, args, (cmd)->cmd.sscan(key, new LettuceScanCursor(cursor)),
				new ScanCursorConverter.ValueScanCursorConverter<>());
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final String cursor, final String pattern) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(pattern);
		return executeCommand(Command.SSCAN, args,
				(cmd)->cmd.sscan(SafeEncoder.encode(key), new LettuceScanCursor(cursor),
						new LettuceScanArgs(pattern)),
				new ScanCursorConverter.ValueScanCursorConverter.BSKeyScanCursorConverter());
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor, final byte[] pattern) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(pattern);
		return executeCommand(Command.SSCAN, args,
				(cmd)->cmd.sscan(key, new LettuceScanCursor(cursor), new LettuceScanArgs(pattern)),
				new ScanCursorConverter.ValueScanCursorConverter<>());
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final String cursor, final long count) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(count);
		return executeCommand(Command.SSCAN, args,
				(cmd)->cmd.sscan(SafeEncoder.encode(key), new LettuceScanCursor(cursor),
						new LettuceScanArgs(count)),
				new ScanCursorConverter.ValueScanCursorConverter.BSKeyScanCursorConverter());
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor, final long count) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(count);
		return executeCommand(Command.SSCAN, args,
				(cmd)->cmd.sscan(key, new LettuceScanCursor(cursor), new LettuceScanArgs(count)),
				new ScanCursorConverter.ValueScanCursorConverter<>());
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final String cursor, final String pattern,
										  final long count) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(pattern).add(count);
		return executeCommand(Command.SSCAN, args,
				(cmd)->cmd.sscan(SafeEncoder.encode(key), new LettuceScanCursor(cursor),
						new LettuceScanArgs(pattern, count)),
				new ScanCursorConverter.ValueScanCursorConverter.BSKeyScanCursorConverter());
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor, final byte[] pattern,
										  final long count) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(pattern).add(count);
		return executeCommand(Command.SSCAN, args,
				(cmd)->cmd.sscan(key, new LettuceScanCursor(cursor), new LettuceScanArgs(pattern, count)),
				new ScanCursorConverter.ValueScanCursorConverter<>());
	}

	@Override
	public Set<String> sUnion(final String... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return executeCommand(Command.SUNION, args, (cmd)->cmd.sunion(SafeEncoder.encode(keys)),
				new SetConverter<>(SafeEncoder::encode));
	}

	@Override
	public Set<byte[]> sUnion(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return executeCommand(Command.SUNION, args, (cmd)->cmd.sunion(keys), (v)->v);
	}

	@Override
	public Long sUnionStore(final String destKey, final String... keys) {
		return sUnionStore(SafeEncoder.encode(destKey), SafeEncoder.encode(keys));
	}

	@Override
	public Long sUnionStore(final byte[] destKey, final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys);
		return executeCommand(Command.SUNIONSTORE, args, (cmd)->cmd.sunionstore(destKey, keys), (v)->v);
	}

}
