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
package com.buession.redis.client.jedis.operations;

import com.buession.lang.Status;
import com.buession.redis.client.jedis.JedisClusterClient;
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.Command;
import com.buession.redis.core.internal.convert.jedis.response.ScanResultConverter;
import com.buession.redis.core.internal.jedis.JedisScanParams;
import redis.clients.jedis.params.ScanParams;

import java.util.List;
import java.util.Set;

/**
 * Jedis 集群模式集合命令操作
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public final class JedisClusterSetOperations extends AbstractSetOperations<JedisClusterClient> {

	public JedisClusterSetOperations(final JedisClusterClient client) {
		super(client);
	}

	@Override
	public Long sAdd(final String key, final String... members) {
		final CommandArguments args = CommandArguments.create("key", key).put("members", (Object[]) members);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, Command.SADD, (cmd)->cmd.sadd(key, members),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, Command.SADD, (cmd)->cmd.sadd(key, members),
					(v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, Command.SADD, (cmd)->cmd.sadd(key, members), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long sAdd(final byte[] key, final byte[]... members) {
		final CommandArguments args = CommandArguments.create("key", key).put("members", (Object[]) members);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, Command.SADD, (cmd)->cmd.sadd(key, members),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, Command.SADD, (cmd)->cmd.sadd(key, members),
					(v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, Command.SADD, (cmd)->cmd.sadd(key, members), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long sCard(final String key) {
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, Command.SCARD, (cmd)->cmd.scard(key), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, Command.SCARD, (cmd)->cmd.scard(key), (v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, Command.SCARD, (cmd)->cmd.scard(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long sCard(final byte[] key) {
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, Command.SCARD, (cmd)->cmd.scard(key), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, Command.SCARD, (cmd)->cmd.scard(key), (v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, Command.SCARD, (cmd)->cmd.scard(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public Set<String> sDiff(final String... keys) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, Command.SDIFF, (cmd)->cmd.sdiff(keys), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, Command.SDIFF, (cmd)->cmd.sdiff(keys), (v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, Command.SDIFF, (cmd)->cmd.sdiff(keys), (v)->v)
					.run(args);
		}
	}

	@Override
	public Set<byte[]> sDiff(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, Command.SDIFF, (cmd)->cmd.sdiff(keys), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, Command.SDIFF, (cmd)->cmd.sdiff(keys), (v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, Command.SDIFF, (cmd)->cmd.sdiff(keys), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long sDiffStore(final String destKey, final String... keys) {
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("keys", (Object[]) keys);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, Command.SDIFFSTORE,
					(cmd)->cmd.sdiffstore(destKey, keys), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, Command.SDIFFSTORE,
					(cmd)->cmd.sdiffstore(destKey, keys), (v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, Command.SDIFFSTORE, (cmd)->cmd.sdiffstore(destKey, keys),
					(v)->v)
					.run(args);
		}
	}

	@Override
	public Long sDiffStore(final byte[] destKey, final byte[]... keys) {
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("keys", (Object[]) keys);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, Command.SDIFFSTORE,
					(cmd)->cmd.sdiffstore(destKey, keys), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, Command.SDIFFSTORE,
					(cmd)->cmd.sdiffstore(destKey, keys), (v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, Command.SDIFFSTORE, (cmd)->cmd.sdiffstore(destKey, keys),
					(v)->v)
					.run(args);
		}
	}

	@Override
	public Set<String> sInter(final String... keys) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, Command.SINTER, (cmd)->cmd.sinter(keys), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, Command.SINTER, (cmd)->cmd.sinter(keys), (v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, Command.SINTER, (cmd)->cmd.sinter(keys), (v)->v)
					.run(args);
		}
	}

	@Override
	public Set<byte[]> sInter(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, Command.SINTER, (cmd)->cmd.sinter(keys), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, Command.SINTER, (cmd)->cmd.sinter(keys), (v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, Command.SINTER, (cmd)->cmd.sinter(keys), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long sInterStore(final String destKey, final String... keys) {
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("keys", (Object[]) keys);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, Command.SINTERSTORE,
					(cmd)->cmd.sinterstore(destKey, keys), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, Command.SINTERSTORE,
					(cmd)->cmd.sinterstore(destKey, keys), (v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, Command.SINTERSTORE, (cmd)->cmd.sinterstore(destKey, keys),
					(v)->v)
					.run(args);
		}
	}

	@Override
	public Long sInterStore(final byte[] destKey, final byte[]... keys) {
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("keys", (Object[]) keys);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, Command.SINTERSTORE,
					(cmd)->cmd.sinterstore(destKey, keys), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, Command.SINTERSTORE,
					(cmd)->cmd.sinterstore(destKey, keys), (v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, Command.SINTERSTORE, (cmd)->cmd.sinterstore(destKey, keys),
					(v)->v)
					.run(args);
		}
	}

	@Override
	public Boolean sIsMember(final String key, final String member) {
		final CommandArguments args = CommandArguments.create("key", key).put("member", member);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, Command.SISMEMBER,
					(cmd)->cmd.sismember(key, member), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, Command.SISMEMBER,
					(cmd)->cmd.sismember(key, member), (v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, Command.SISMEMBER, (cmd)->cmd.sismember(key, member),
					(v)->v)
					.run(args);
		}
	}

	@Override
	public Boolean sIsMember(final byte[] key, final byte[] member) {
		final CommandArguments args = CommandArguments.create("key", key).put("member", member);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, Command.SISMEMBER,
					(cmd)->cmd.sismember(key, member), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, Command.SISMEMBER,
					(cmd)->cmd.sismember(key, member), (v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, Command.SISMEMBER, (cmd)->cmd.sismember(key, member),
					(v)->v)
					.run(args);
		}
	}

	@Override
	public List<Boolean> smIsMember(final String key, final String... members) {
		final CommandArguments args = CommandArguments.create("key", key).put("members", (Object[]) members);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, Command.SMISMEMBER,
					(cmd)->cmd.smismember(key, members), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, Command.SMISMEMBER,
					(cmd)->cmd.smismember(key, members), (v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, Command.SMISMEMBER, (cmd)->cmd.smismember(key, members),
					(v)->v)
					.run(args);
		}
	}

	@Override
	public List<Boolean> smIsMember(final byte[] key, final byte[]... members) {
		final CommandArguments args = CommandArguments.create("key", key).put("members", (Object[]) members);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, Command.SMISMEMBER,
					(cmd)->cmd.smismember(key, members), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, Command.SMISMEMBER,
					(cmd)->cmd.smismember(key, members), (v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, Command.SMISMEMBER, (cmd)->cmd.smismember(key, members),
					(v)->v)
					.run(args);
		}
	}

	@Override
	public Set<String> sMembers(final String key) {
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, Command.SMEMBERS, (cmd)->cmd.smembers(key), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, Command.SMEMBERS, (cmd)->cmd.smembers(key),
					(v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, Command.SMEMBERS, (cmd)->cmd.smembers(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public Set<byte[]> sMembers(final byte[] key) {
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, Command.SMEMBERS, (cmd)->cmd.smembers(key), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, Command.SMEMBERS, (cmd)->cmd.smembers(key),
					(v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, Command.SMEMBERS, (cmd)->cmd.smembers(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public Status sMove(final String key, final String destKey, final String member) {
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey).put("member", member);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, Command.SMOVE,
					(cmd)->cmd.smove(key, destKey, member), oneStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, Command.SMOVE,
					(cmd)->cmd.smove(key, destKey, member), oneStatusConverter)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, Command.SMOVE, (cmd)->cmd.smove(key, destKey, member),
					oneStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status sMove(final byte[] key, final byte[] destKey, final byte[] member) {
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey).put("member", member);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, Command.SMOVE,
					(cmd)->cmd.smove(key, destKey, member), oneStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, Command.SMOVE,
					(cmd)->cmd.smove(key, destKey, member), oneStatusConverter)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, Command.SMOVE, (cmd)->cmd.smove(key, destKey, member),
					oneStatusConverter)
					.run(args);
		}
	}

	@Override
	public String sPop(final String key) {
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, Command.SPOP, (cmd)->cmd.spop(key), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, Command.SPOP, (cmd)->cmd.spop(key), (v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, Command.SPOP, (cmd)->cmd.spop(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public byte[] sPop(final byte[] key) {
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, Command.SPOP, (cmd)->cmd.spop(key), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, Command.SPOP, (cmd)->cmd.spop(key), (v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, Command.SPOP, (cmd)->cmd.spop(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public Set<String> sPop(final String key, final int count) {
		final CommandArguments args = CommandArguments.create("key", key).put("count", count);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, Command.SPOP, (cmd)->cmd.spop(key, count), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, Command.SPOP, (cmd)->cmd.spop(key, count),
					(v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, Command.SPOP, (cmd)->cmd.spop(key, count), (v)->v)
					.run(args);
		}
	}

	@Override
	public Set<byte[]> sPop(final byte[] key, final int count) {
		final CommandArguments args = CommandArguments.create("key", key).put("count", count);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, Command.SPOP, (cmd)->cmd.spop(key, count), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, Command.SPOP, (cmd)->cmd.spop(key, count),
					(v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, Command.SPOP, (cmd)->cmd.spop(key, count), (v)->v)
					.run(args);
		}
	}

	@Override
	public String sRandMember(final String key) {
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, Command.SRANDMEMBER, (cmd)->cmd.srandmember(key),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, Command.SRANDMEMBER,
					(cmd)->cmd.srandmember(key), (v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, Command.SRANDMEMBER, (cmd)->cmd.srandmember(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public byte[] sRandMember(final byte[] key) {
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, Command.SRANDMEMBER, (cmd)->cmd.srandmember(key),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, Command.SRANDMEMBER,
					(cmd)->cmd.srandmember(key), (v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, Command.SRANDMEMBER, (cmd)->cmd.srandmember(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public List<String> sRandMember(final String key, final int count) {
		final CommandArguments args = CommandArguments.create("key", key).put("count", count);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, Command.SRANDMEMBER,
					(cmd)->cmd.srandmember(key, (int) count), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, Command.SRANDMEMBER,
					(cmd)->cmd.srandmember(key, (int) count), (v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, Command.SRANDMEMBER,
					(cmd)->cmd.srandmember(key, (int) count), (v)->v)
					.run(args);
		}
	}

	@Override
	public List<byte[]> sRandMember(final byte[] key, final int count) {
		final CommandArguments args = CommandArguments.create("key", key).put("count", count);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, Command.SRANDMEMBER,
					(cmd)->cmd.srandmember(key, (int) count), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, Command.SRANDMEMBER,
					(cmd)->cmd.srandmember(key, (int) count), (v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, Command.SRANDMEMBER,
					(cmd)->cmd.srandmember(key, (int) count), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long sRem(final String key, final String... members) {
		final CommandArguments args = CommandArguments.create("key", key).put("members", (Object[]) members);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, Command.SREM, (cmd)->cmd.srem(key, members),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, Command.SREM, (cmd)->cmd.srem(key, members),
					(v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, Command.SREM, (cmd)->cmd.srem(key, members), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long sRem(final byte[] key, final byte[]... members) {
		final CommandArguments args = CommandArguments.create("key", key).put("members", (Object[]) members);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, Command.SREM, (cmd)->cmd.srem(key, members),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, Command.SREM, (cmd)->cmd.srem(key, members),
					(v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, Command.SREM, (cmd)->cmd.srem(key, members), (v)->v)
					.run(args);
		}
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final String cursor) {
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor);
		final ScanResultConverter.ListScanResultConverter<String> listScanResultConverter =
				new ScanResultConverter.ListScanResultConverter<>();

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, Command.SSCAN, (cmd)->cmd.sscan(key, cursor),
					listScanResultConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, Command.SSCAN, (cmd)->cmd.sscan(key, cursor),
					listScanResultConverter)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, Command.SSCAN, (cmd)->cmd.sscan(key, cursor),
					listScanResultConverter)
					.run(args);
		}
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor) {
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor);
		final ScanResultConverter.ListScanResultConverter<byte[]> listScanResultConverter =
				new ScanResultConverter.ListScanResultConverter<>();

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, Command.SSCAN, (cmd)->cmd.sscan(key, cursor),
					listScanResultConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, Command.SSCAN, (cmd)->cmd.sscan(key, cursor),
					listScanResultConverter)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, Command.SSCAN, (cmd)->cmd.sscan(key, cursor),
					listScanResultConverter)
					.run(args);
		}
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final String cursor, final String pattern) {
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor).put("pattern", pattern);
		final ScanParams scanParams = new JedisScanParams(pattern);

		return sScan(key, cursor, scanParams, args);
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor, final byte[] pattern) {
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor).put("pattern", pattern);
		final ScanParams scanParams = new JedisScanParams(pattern);

		return sScan(key, cursor, scanParams, args);
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final String cursor, final int count) {
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor).put("count", count);
		final ScanParams scanParams = new JedisScanParams(count);

		return sScan(key, cursor, scanParams, args);
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor, final int count) {
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor).put("count", count);
		final ScanParams scanParams = new JedisScanParams(count);

		return sScan(key, cursor, scanParams, args);
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final String cursor, final String pattern,
										  final int count) {
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor).put("pattern", pattern)
				.put("count", count);
		final ScanParams scanParams = new JedisScanParams(pattern, count);

		return sScan(key, cursor, scanParams, args);
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor, final byte[] pattern,
										  final int count) {
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor).put("pattern", pattern)
				.put("count", count);
		final ScanParams scanParams = new JedisScanParams(pattern, count);

		return sScan(key, cursor, scanParams, args);
	}

	@Override
	public Set<String> sUnion(final String... keys) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, Command.SUNION, (cmd)->cmd.sunion(keys), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, Command.SUNION, (cmd)->cmd.sunion(keys), (v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, Command.SUNION, (cmd)->cmd.sunion(keys), (v)->v)
					.run(args);
		}
	}

	@Override
	public Set<byte[]> sUnion(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, Command.SUNION, (cmd)->cmd.sunion(keys), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, Command.SUNION, (cmd)->cmd.sunion(keys), (v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, Command.SUNION, (cmd)->cmd.sunion(keys), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long sUnionStore(final String destKey, final String... keys) {
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("keys", (Object[]) keys);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, Command.SUNIONSTORE,
					(cmd)->cmd.sunionstore(destKey, keys), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, Command.SUNIONSTORE,
					(cmd)->cmd.sunionstore(destKey, keys), (v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, Command.SUNIONSTORE, (cmd)->cmd.sunionstore(destKey, keys),
					(v)->v)
					.run(args);
		}
	}

	@Override
	public Long sUnionStore(final byte[] destKey, final byte[]... keys) {
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("keys", (Object[]) keys);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, Command.SUNIONSTORE,
					(cmd)->cmd.sunionstore(destKey, keys), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, Command.SUNIONSTORE,
					(cmd)->cmd.sunionstore(destKey, keys), (v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, Command.SUNIONSTORE, (cmd)->cmd.sunionstore(destKey, keys),
					(v)->v)
					.run(args);
		}
	}

	private ScanResult<List<String>> sScan(final String key, final String cursor, final ScanParams scanParams,
										   final CommandArguments args) {
		final ScanResultConverter.ListScanResultConverter<String> listScanResultConverter =
				new ScanResultConverter.ListScanResultConverter<>();

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, Command.SSCAN,
					(cmd)->cmd.sscan(key, cursor, scanParams), listScanResultConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, Command.SSCAN,
					(cmd)->cmd.sscan(key, cursor, scanParams), listScanResultConverter)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, Command.SSCAN, (cmd)->cmd.sscan(key, cursor, scanParams),
					listScanResultConverter)
					.run(args);
		}
	}

	private ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor, final ScanParams scanParams,
										   final CommandArguments args) {
		final ScanResultConverter.ListScanResultConverter<byte[]> listScanResultConverter =
				new ScanResultConverter.ListScanResultConverter<>();

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, Command.SSCAN,
					(cmd)->cmd.sscan(key, cursor, scanParams), listScanResultConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, Command.SSCAN,
					(cmd)->cmd.sscan(key, cursor, scanParams), listScanResultConverter)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, Command.SSCAN, (cmd)->cmd.sscan(key, cursor, scanParams),
					listScanResultConverter)
					.run(args);
		}
	}

}