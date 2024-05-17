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
 * | Copyright @ 2013-2024 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.client.lettuce.operations;

import com.buession.core.converter.BooleanStatusConverter;
import com.buession.lang.Status;
import com.buession.redis.client.lettuce.LettuceStandaloneClient;
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.internal.convert.lettuce.response.ValueScanCursorConverter;
import com.buession.redis.core.internal.convert.response.ListConverter;
import com.buession.redis.core.internal.convert.response.SetConverter;
import com.buession.redis.core.internal.lettuce.LettuceScanArgs;
import com.buession.redis.core.internal.lettuce.LettuceScanCursor;
import com.buession.redis.utils.SafeEncoder;

import java.util.List;
import java.util.Set;

/**
 * Lettuce 单机模式集合命令操作
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public final class LettuceSetOperations extends AbstractSetOperations<LettuceStandaloneClient> {

	public LettuceSetOperations(final LettuceStandaloneClient client) {
		super(client);
	}

	@Override
	public Long sAdd(final byte[] key, final byte[]... members) {
		final CommandArguments args = CommandArguments.create("key", key).put("members", (Object[]) members);
		return new LettuceCommand<>(client, ProtocolCommand.SADD, (cmd)->cmd.sadd(key, members), (v)->v)
				.run(args);
	}

	@Override
	public Long sCard(final byte[] key) {
		final CommandArguments args = CommandArguments.create("key", key);
		return new LettuceCommand<>(client, ProtocolCommand.SCARD, (cmd)->cmd.scard(key), (v)->v)
				.run(args);
	}

	@Override
	public Set<String> sDiff(final String... keys) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys);
		return new LettuceCommand<>(client, ProtocolCommand.SDIFF, (cmd)->cmd.sdiff(SafeEncoder.encode(keys)),
				new SetConverter.BinaryToStringSetConverter())
				.run(args);
	}

	@Override
	public Set<byte[]> sDiff(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys);
		return new LettuceCommand<>(client, ProtocolCommand.SDIFF, (cmd)->cmd.sdiff(keys), (v)->v)
				.run(args);
	}

	@Override
	public Long sDiffStore(final byte[] destKey, final byte[]... keys) {
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("keys", (Object[]) keys);
		return new LettuceCommand<>(client, ProtocolCommand.SDIFFSTORE, (cmd)->cmd.sdiffstore(destKey, keys), (v)->v)
				.run(args);
	}

	@Override
	public Set<String> sInter(final String... keys) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys);
		return new LettuceCommand<>(client, ProtocolCommand.SINTER, (cmd)->cmd.sinter(SafeEncoder.encode(keys)),
				new SetConverter.BinaryToStringSetConverter())
				.run(args);
	}

	@Override
	public Set<byte[]> sInter(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys);
		return new LettuceCommand<>(client, ProtocolCommand.SINTER, (cmd)->cmd.sinter(keys), (v)->v)
				.run(args);
	}

	@Override
	public Long sInterStore(final byte[] destKey, final byte[]... keys) {
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("keys", (Object[]) keys);
		return new LettuceCommand<>(client, ProtocolCommand.SINTERSTORE, (cmd)->cmd.sinterstore(destKey, keys), (v)->v)
				.run(args);
	}

	@Override
	public Boolean sIsMember(final byte[] key, final byte[] member) {
		final CommandArguments args = CommandArguments.create("key", key).put("member", member);
		return new LettuceCommand<>(client, ProtocolCommand.SISMEMBER, (cmd)->cmd.sismember(key, member), (v)->v)
				.run(args);
	}

	@Override
	public List<Boolean> smIsMember(final String key, final String... members) {
		final CommandArguments args = CommandArguments.create("key", key).put("members", (Object[]) members);
		return new LettuceCommand<List<Boolean>, List<Boolean>>(client, ProtocolCommand.SMISMEMBER)
				.run(args);
	}

	@Override
	public List<Boolean> smIsMember(final byte[] key, final byte[]... members) {
		final CommandArguments args = CommandArguments.create("key", key).put("members", (Object[]) members);
		return new LettuceCommand<List<Boolean>, List<Boolean>>(client, ProtocolCommand.SMISMEMBER)
				.run(args);
	}

	@Override
	public Set<String> sMembers(final String key) {
		final CommandArguments args = CommandArguments.create("key", key);
		return new LettuceCommand<>(client, ProtocolCommand.SMEMBERS, (cmd)->cmd.smembers(SafeEncoder.encode(key)),
				new SetConverter.BinaryToStringSetConverter())
				.run(args);
	}

	@Override
	public Set<byte[]> sMembers(final byte[] key) {
		final CommandArguments args = CommandArguments.create("key", key);
		return new LettuceCommand<>(client, ProtocolCommand.SMEMBERS, (cmd)->cmd.smembers(key), (v)->v)
				.run(args);
	}

	@Override
	public Status sMove(final byte[] key, final byte[] destKey, final byte[] member) {
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey).put("member", member);
		return new LettuceCommand<>(client, ProtocolCommand.SMOVE, (cmd)->cmd.smove(key, destKey, member),
				new BooleanStatusConverter())
				.run(args);
	}

	@Override
	public String sPop(final String key) {
		final CommandArguments args = CommandArguments.create("key", key);
		return new LettuceCommand<>(client, ProtocolCommand.SPOP, (cmd)->cmd.spop(SafeEncoder.encode(key)),
				SafeEncoder::encode)
				.run(args);
	}

	@Override
	public byte[] sPop(final byte[] key) {
		final CommandArguments args = CommandArguments.create("key", key);
		return new LettuceCommand<>(client, ProtocolCommand.SPOP, (cmd)->cmd.spop(key), (v)->v)
				.run(args);
	}

	@Override
	public Set<String> sPop(final String key, final long count) {
		final CommandArguments args = CommandArguments.create("key", key);
		return new LettuceCommand<>(client, ProtocolCommand.SPOP, (cmd)->cmd.spop(SafeEncoder.encode(key), count),
				new SetConverter.BinaryToStringSetConverter())
				.run(args);
	}

	@Override
	public Set<byte[]> sPop(final byte[] key, final long count) {
		final CommandArguments args = CommandArguments.create("key", key).put("count", count);
		return new LettuceCommand<>(client, ProtocolCommand.SPOP, (cmd)->cmd.spop(key, count), (v)->v)
				.run(args);
	}

	@Override
	public String sRandMember(final String key) {
		final CommandArguments args = CommandArguments.create("key", key);
		return new LettuceCommand<>(client, ProtocolCommand.SRANDMEMBER,
				(cmd)->cmd.srandmember(SafeEncoder.encode(key)), SafeEncoder::encode)
				.run(args);
	}

	@Override
	public byte[] sRandMember(final byte[] key) {
		final CommandArguments args = CommandArguments.create("key", key);
		return new LettuceCommand<>(client, ProtocolCommand.SRANDMEMBER, (cmd)->cmd.srandmember(key), (v)->v)
				.run(args);
	}

	@Override
	public List<String> sRandMember(final String key, final long count) {
		final CommandArguments args = CommandArguments.create("key", key).put("count", count);
		return new LettuceCommand<>(client, ProtocolCommand.SRANDMEMBER,
				(cmd)->cmd.srandmember(SafeEncoder.encode(key), count), new ListConverter.BinaryToStringListConverter())
				.run(args);
	}

	@Override
	public List<byte[]> sRandMember(final byte[] key, final long count) {
		final CommandArguments args = CommandArguments.create("key", key).put("count", count);
		return new LettuceCommand<>(client, ProtocolCommand.SRANDMEMBER, (cmd)->cmd.srandmember(key, count), (v)->v)
				.run(args);
	}

	@Override
	public Long sRem(final byte[] key, final byte[]... members) {
		final CommandArguments args = CommandArguments.create("key", key).put("members", (Object[]) members);
		return new LettuceCommand<>(client, ProtocolCommand.SREM, (cmd)->cmd.srem(key, members), (v)->v)
				.run(args);
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final String cursor) {
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor);
		return new LettuceCommand<>(client, ProtocolCommand.SSCAN, (cmd)->cmd.sscan(SafeEncoder.encode(key),
				new LettuceScanCursor(cursor)), (new ValueScanCursorConverter.BSKeyScanCursorConverter()))
				.run(args);
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor) {
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor);
		return new LettuceCommand<>(client, ProtocolCommand.SSCAN, (cmd)->cmd.sscan(key, new LettuceScanCursor(cursor)),
				new ValueScanCursorConverter<>())
				.run(args);
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final String cursor, final String pattern) {
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor).put("pattern", pattern);
		return new LettuceCommand<>(client, ProtocolCommand.SSCAN,
				(cmd)->cmd.sscan(SafeEncoder.encode(key), new LettuceScanCursor(cursor), new LettuceScanArgs(pattern)),
				(new ValueScanCursorConverter.BSKeyScanCursorConverter()))
				.run(args);
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor, final byte[] pattern) {
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor).put("pattern", pattern);
		return new LettuceCommand<>(client, ProtocolCommand.SSCAN,
				(cmd)->cmd.sscan(key, new LettuceScanCursor(cursor), new LettuceScanArgs(pattern)),
				new ValueScanCursorConverter<>())
				.run(args);
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final String cursor, final long count) {
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor).put("count", count);
		return new LettuceCommand<>(client, ProtocolCommand.SSCAN,
				(cmd)->cmd.sscan(SafeEncoder.encode(key), new LettuceScanCursor(cursor), new LettuceScanArgs(count)),
				(new ValueScanCursorConverter.BSKeyScanCursorConverter()))
				.run(args);
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor, final long count) {
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor).put("count", count);
		return new LettuceCommand<>(client, ProtocolCommand.SSCAN,
				(cmd)->cmd.sscan(key, new LettuceScanCursor(cursor), new LettuceScanArgs(count)),
				new ValueScanCursorConverter<>())
				.run(args);
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final String cursor, final String pattern,
										  final long count) {
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor).put("pattern", pattern)
				.put("count", count);
		return new LettuceCommand<>(client, ProtocolCommand.SSCAN,
				(cmd)->cmd.sscan(SafeEncoder.encode(key), new LettuceScanCursor(cursor),
						new LettuceScanArgs(pattern, count)),
				(new ValueScanCursorConverter.BSKeyScanCursorConverter()))
				.run(args);
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor, final byte[] pattern,
										  final long count) {
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor).put("pattern", pattern)
				.put("count", count);
		return new LettuceCommand<>(client, ProtocolCommand.SSCAN,
				(cmd)->cmd.sscan(key, new LettuceScanCursor(cursor), new LettuceScanArgs(pattern, count)),
				new ValueScanCursorConverter<>())
				.run(args);
	}

	@Override
	public Set<String> sUnion(final String... keys) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys);
		return new LettuceCommand<>(client, ProtocolCommand.SUNION, (cmd)->cmd.sunion(SafeEncoder.encode(keys)),
				new SetConverter.BinaryToStringSetConverter())
				.run(args);
	}

	@Override
	public Set<byte[]> sUnion(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys);
		return new LettuceCommand<>(client, ProtocolCommand.SUNION, (cmd)->cmd.sunion(keys), (v)->v)
				.run(args);
	}

	@Override
	public Long sUnionStore(final byte[] destKey, final byte[]... keys) {
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("keys", (Object[]) keys);
		return new LettuceCommand<>(client, ProtocolCommand.SUNIONSTORE, (cmd)->cmd.sunionstore(destKey, keys), (v)->v)
				.run(args);
	}

}
