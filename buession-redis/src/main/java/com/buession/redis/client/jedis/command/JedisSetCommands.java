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
package com.buession.redis.client.jedis.command;

import com.buession.lang.Status;
import com.buession.redis.client.jedis.JedisRedisClient;
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.command.Command;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.SetCommands;
import com.buession.redis.core.internal.convert.jedis.response.ScanResultConverter;
import com.buession.redis.core.internal.convert.response.OneStatusConverter;
import com.buession.redis.core.internal.jedis.JedisScanParams;

import java.util.List;
import java.util.Set;

/**
 * Jedis 集合命令
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public final class JedisSetCommands extends AbstractJedisRedisCommands implements SetCommands {

	public JedisSetCommands(final JedisRedisClient client) {
		super(client);
	}

	@Override
	public Long sAdd(final String key, final String... members) {
		final CommandArguments args = CommandArguments.create(key).add(members);
		return executeCommand(Command.SADD, args, (cmd)->cmd.sadd(key, members), (v)->v);
	}

	@Override
	public Long sAdd(final byte[] key, final byte[]... members) {
		final CommandArguments args = CommandArguments.create(key).add(members);
		return executeCommand(Command.SADD, args, (cmd)->cmd.sadd(key, members), (v)->v);
	}

	@Override
	public Long sCard(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.SCARD, args, (cmd)->cmd.scard(key), (v)->v);
	}

	@Override
	public Long sCard(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.SCARD, args, (cmd)->cmd.scard(key), (v)->v);
	}

	@Override
	public Set<String> sDiff(final String... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return executeCommand(Command.SDIFF, args, (cmd)->cmd.sdiff(keys), (v)->v);
	}

	@Override
	public Set<byte[]> sDiff(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return executeCommand(Command.SDIFF, args, (cmd)->cmd.sdiff(keys), (v)->v);
	}

	@Override
	public Long sDiffStore(final String destKey, final String... keys) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys);
		return executeCommand(Command.SDIFFSTORE, args, (cmd)->cmd.sdiffstore(destKey, keys), (v)->v);
	}

	@Override
	public Long sDiffStore(final byte[] destKey, final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys);
		return executeCommand(Command.SDIFFSTORE, args, (cmd)->cmd.sdiffstore(destKey, keys), (v)->v);
	}

	@Override
	public Set<String> sInter(final String... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return executeCommand(Command.SINTER, args, (cmd)->cmd.sinter(keys), (v)->v);
	}

	@Override
	public Set<byte[]> sInter(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return executeCommand(Command.SINTER, args, (cmd)->cmd.sinter(keys), (v)->v);
	}

	@Override
	public Long sInterCard(final String... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return executeCommand(Command.SINTERCARD, args, (cmd)->cmd.sintercard(keys), (v)->v);
	}

	@Override
	public Long sInterCard(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return executeCommand(Command.SINTERCARD, args, (cmd)->cmd.sintercard(keys), (v)->v);
	}

	@Override
	public Long sInterCard(final String[] keys, final int limit) {
		final CommandArguments args = CommandArguments.create(keys).add(limit);
		return executeCommand(Command.SINTERCARD, args, (cmd)->cmd.sintercard(limit, keys), (v)->v);
	}

	@Override
	public Long sInterCard(final byte[][] keys, final int limit) {
		final CommandArguments args = CommandArguments.create(keys).add(limit);
		return executeCommand(Command.SINTERCARD, args, (cmd)->cmd.sintercard(limit, keys), (v)->v);
	}

	@Override
	public Long sInterStore(final String destKey, final String... keys) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys);
		return executeCommand(Command.SINTERSTORE, args, (cmd)->cmd.sinterstore(destKey, keys), (v)->v);
	}

	@Override
	public Long sInterStore(final byte[] destKey, final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys);
		return executeCommand(Command.SINTERSTORE, args, (cmd)->cmd.sinterstore(destKey, keys), (v)->v);
	}

	@Override
	public Boolean sIsMember(final String key, final String member) {
		final CommandArguments args = CommandArguments.create(key).add(member);
		return executeCommand(Command.SISMEMBER, args, (cmd)->cmd.sismember(key, member), (v)->v);
	}

	@Override
	public Boolean sIsMember(final byte[] key, final byte[] member) {
		final CommandArguments args = CommandArguments.create(key).add(member);
		return executeCommand(Command.SISMEMBER, args, (cmd)->cmd.sismember(key, member), (v)->v);
	}

	@Override
	public Set<String> sMembers(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.SMEMBERS, args, (cmd)->cmd.smembers(key), (v)->v);
	}

	@Override
	public Set<byte[]> sMembers(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.SMEMBERS, args, (cmd)->cmd.smembers(key), (v)->v);
	}

	@Override
	public List<Boolean> smIsMember(final String key, final String... members) {
		final CommandArguments args = CommandArguments.create(key).add(members);
		return executeCommand(Command.SMISMEMBER, args, (cmd)->cmd.smismember(key, members), (v)->v);
	}

	@Override
	public List<Boolean> smIsMember(final byte[] key, final byte[]... members) {
		final CommandArguments args = CommandArguments.create(key).add(members);
		return executeCommand(Command.SMISMEMBER, args, (cmd)->cmd.smismember(key, members), (v)->v);
	}

	@Override
	public Status sMove(final String key, final String destKey, final String member) {
		final CommandArguments args = CommandArguments.create(key).add(destKey).add(member);
		return executeCommand(Command.SMOVE, args, (cmd)->cmd.smove(key, destKey, member), new OneStatusConverter());
	}

	@Override
	public Status sMove(final byte[] key, final byte[] destKey, final byte[] member) {
		final CommandArguments args = CommandArguments.create(key).add(destKey).add(member);
		return executeCommand(Command.SMOVE, args, (cmd)->cmd.smove(key, destKey, member), new OneStatusConverter());
	}

	@Override
	public String sPop(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.SPOP, args, (cmd)->cmd.spop(key), (v)->v);
	}

	@Override
	public byte[] sPop(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.SPOP, args, (cmd)->cmd.spop(key), (v)->v);
	}

	@Override
	public Set<String> sPop(final String key, final long count) {
		final CommandArguments args = CommandArguments.create(key).add(count);
		return executeCommand(Command.SPOP, args, (cmd)->cmd.spop(key, count), (v)->v);
	}

	@Override
	public Set<byte[]> sPop(final byte[] key, final long count) {
		final CommandArguments args = CommandArguments.create(key).add(count);
		return executeCommand(Command.SPOP, args, (cmd)->cmd.spop(key, count), (v)->v);
	}

	@Override
	public String sRandMember(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.SRANDMEMBER, args, (cmd)->cmd.srandmember(key), (v)->v);
	}

	@Override
	public byte[] sRandMember(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.SRANDMEMBER, args, (cmd)->cmd.srandmember(key), (v)->v);
	}

	@Override
	public List<String> sRandMember(final String key, final long count) {
		final CommandArguments args = CommandArguments.create(key).add(count);
		return executeCommand(Command.SRANDMEMBER, args, (cmd)->cmd.srandmember(key, (int) count), (v)->v);
	}

	@Override
	public List<byte[]> sRandMember(final byte[] key, final long count) {
		final CommandArguments args = CommandArguments.create(key).add(count);
		return executeCommand(Command.SRANDMEMBER, args, (cmd)->cmd.srandmember(key, (int) count), (v)->v);
	}

	@Override
	public Long sRem(final String key, final String... members) {
		final CommandArguments args = CommandArguments.create(key).add(members);
		return executeCommand(Command.SREM, args, (cmd)->cmd.srem(key, members), (v)->v);
	}

	@Override
	public Long sRem(final byte[] key, final byte[]... members) {
		final CommandArguments args = CommandArguments.create(key).add(members);
		return executeCommand(Command.SREM, args, (cmd)->cmd.srem(key, members), (v)->v);
	}

	@Override
	public ScanResult<String> sScan(final String key, final String cursor) {
		final CommandArguments args = CommandArguments.create(key).add(cursor);
		return executeCommand(Command.SSCAN, args, (cmd)->cmd.sscan(key, cursor),
				new ScanResultConverter<>((v)->v));
	}

	@Override
	public ScanResult<byte[]> sScan(final byte[] key, final byte[] cursor) {
		final CommandArguments args = CommandArguments.create(key).add(cursor);
		return executeCommand(Command.SSCAN, args, (cmd)->cmd.sscan(key, cursor),
				new ScanResultConverter<>((v)->v));
	}

	@Override
	public ScanResult<String> sScan(final String key, final String cursor, final String pattern) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(pattern);
		return executeCommand(Command.SSCAN, args, (cmd)->cmd.sscan(key, cursor, new JedisScanParams(pattern)),
				new ScanResultConverter<>((v)->v));
	}

	@Override
	public ScanResult<byte[]> sScan(final byte[] key, final byte[] cursor, final byte[] pattern) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(pattern);
		return executeCommand(Command.SSCAN, args, (cmd)->cmd.sscan(key, cursor, new JedisScanParams(pattern)),
				new ScanResultConverter<>((v)->v));
	}

	@Override
	public ScanResult<String> sScan(final String key, final String cursor, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(count);
		return executeCommand(Command.SSCAN, args, (cmd)->cmd.sscan(key, cursor, new JedisScanParams(count)),
				new ScanResultConverter<>((v)->v));
	}

	@Override
	public ScanResult<byte[]> sScan(final byte[] key, final byte[] cursor, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(count);
		return executeCommand(Command.SSCAN, args, (cmd)->cmd.sscan(key, cursor, new JedisScanParams(count)),
				new ScanResultConverter<>((v)->v));
	}

	@Override
	public ScanResult<String> sScan(final String key, final String cursor, final String pattern, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(pattern).add(count);
		return executeCommand(Command.SSCAN, args, (cmd)->cmd.sscan(key, cursor, new JedisScanParams(pattern, count)),
				new ScanResultConverter<>((v)->v));
	}

	@Override
	public ScanResult<byte[]> sScan(final byte[] key, final byte[] cursor, final byte[] pattern, final int count) {
		final CommandArguments args = CommandArguments.create(key).add(cursor).add(pattern).add(count);
		return executeCommand(Command.SSCAN, args, (cmd)->cmd.sscan(key, cursor, new JedisScanParams(pattern, count)),
				new ScanResultConverter<>((v)->v));
	}

	@Override
	public Set<String> sUnion(final String... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return executeCommand(Command.SUNION, args, (cmd)->cmd.sunion(keys), (v)->v);
	}

	@Override
	public Set<byte[]> sUnion(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(keys);
		return executeCommand(Command.SUNION, args, (cmd)->cmd.sunion(keys), (v)->v);
	}

	@Override
	public Long sUnionStore(final String destKey, final String... keys) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys);
		return executeCommand(Command.SUNIONSTORE, args, (cmd)->cmd.sunionstore(destKey, keys), (v)->v);
	}

	@Override
	public Long sUnionStore(final byte[] destKey, final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys);
		return executeCommand(Command.SUNIONSTORE, args, (cmd)->cmd.sunionstore(destKey, keys), (v)->v);
	}

}
