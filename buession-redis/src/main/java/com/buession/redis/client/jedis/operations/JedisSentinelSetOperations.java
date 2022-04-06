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
import com.buession.redis.client.jedis.JedisSentinelClient;
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.CommandNotSupported;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.internal.convert.Converters;
import com.buession.redis.core.internal.jedis.JedisScanParams;
import redis.clients.jedis.Jedis;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Jedis 哨兵模式集合命令操作
 *
 * @author Yong.Teng
 */
public final class JedisSentinelSetOperations extends AbstractSetOperations<Jedis> {

	public JedisSentinelSetOperations(final JedisSentinelClient client){
		super(client);
	}

	@Override
	public Long sAdd(final String key, final String... members){
		final CommandArguments args = CommandArguments.create("key", key).put("members", members);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().sadd(key, members)), ProtocolCommand.SADD, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().sadd(key, members)), ProtocolCommand.SADD,
					args);
		}else{
			return execute((cmd)->cmd.sadd(key, members), ProtocolCommand.SADD, args);
		}
	}

	@Override
	public Long sAdd(final byte[] key, final byte[]... members){
		final CommandArguments args = CommandArguments.create("key", key).put("members", members);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().sadd(key, members)), ProtocolCommand.SADD, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().sadd(key, members)), ProtocolCommand.SADD,
					args);
		}else{
			return execute((cmd)->cmd.sadd(key, members), ProtocolCommand.SADD, args);
		}
	}

	@Override
	public Long sCard(final String key){
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().scard(key)), ProtocolCommand.SCARD, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().scard(key)), ProtocolCommand.SCARD, args);
		}else{
			return execute((cmd)->cmd.scard(key), ProtocolCommand.SCARD, args);
		}
	}

	@Override
	public Long sCard(final byte[] key){
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().scard(key)), ProtocolCommand.SCARD, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().scard(key)), ProtocolCommand.SCARD, args);
		}else{
			return execute((cmd)->cmd.scard(key), ProtocolCommand.SCARD, args);
		}
	}

	@Override
	public Set<String> sDiff(final String key, final String... keys){
		final CommandArguments args = CommandArguments.create("key", key).put("keys", keys);
		final String[] sKeys = new String[keys == null ? 1 : keys.length + 1];

		sKeys[0] = key;
		if(keys != null){
			System.arraycopy(keys, 0, sKeys, 1, keys.length);
		}

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().sdiff(sKeys)), ProtocolCommand.SDIFF, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().sdiff(sKeys)), ProtocolCommand.SDIFF,
					args);
		}else{
			return execute((cmd)->cmd.sdiff(sKeys), ProtocolCommand.SDIFF, args);
		}
	}

	@Override
	public Set<byte[]> sDiff(final byte[] key, final byte[]... keys){
		final CommandArguments args = CommandArguments.create("key", key).put("keys", keys);
		final byte[][] sKeys = new byte[keys == null ? 1 : keys.length + 1][];

		sKeys[0] = key;
		if(keys != null){
			System.arraycopy(keys, 0, sKeys, 1, keys.length);
		}

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().sdiff(sKeys)), ProtocolCommand.SDIFF, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().sdiff(sKeys)), ProtocolCommand.SDIFF,
					args);
		}else{
			return execute((cmd)->cmd.sdiff(sKeys), ProtocolCommand.SDIFF, args);
		}
	}

	@Override
	public Long sDiffStore(final String destKey, final String... keys){
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("keys", keys);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().sdiffstore(destKey, keys)),
					ProtocolCommand.SDIFFSTORE, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().sdiffstore(destKey, keys)),
					ProtocolCommand.SDIFFSTORE, args);
		}else{
			return execute((cmd)->cmd.sdiffstore(destKey, keys), ProtocolCommand.SDIFFSTORE, args);
		}
	}

	@Override
	public Long sDiffStore(final byte[] destKey, final byte[]... keys){
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("keys", keys);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().sdiffstore(destKey, keys)),
					ProtocolCommand.SDIFFSTORE, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().sdiffstore(destKey, keys)),
					ProtocolCommand.SDIFFSTORE, args);
		}else{
			return execute((cmd)->cmd.sdiffstore(destKey, keys), ProtocolCommand.SDIFFSTORE, args);
		}
	}

	@Override
	public Set<String> sInter(final String key, final String... keys){
		final CommandArguments args = CommandArguments.create("key", key).put("keys", keys);
		final String[] sKeys = new String[keys == null ? 1 : keys.length + 1];

		sKeys[0] = key;
		if(keys != null){
			System.arraycopy(keys, 0, sKeys, 1, keys.length);
		}

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().sinter(sKeys)), ProtocolCommand.SINTER, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().sinter(sKeys)), ProtocolCommand.SINTER,
					args);
		}else{
			return execute((cmd)->cmd.sinter(sKeys), ProtocolCommand.SINTER, args);
		}
	}

	@Override
	public Set<byte[]> sInter(final byte[] key, final byte[]... keys){
		final CommandArguments args = CommandArguments.create("key", key).put("keys", keys);
		final byte[][] sKeys = new byte[keys == null ? 1 : keys.length + 1][];

		sKeys[0] = key;
		if(keys != null){
			System.arraycopy(keys, 0, sKeys, 1, keys.length);
		}

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().sdiff(sKeys)), ProtocolCommand.SINTER, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().sdiff(sKeys)), ProtocolCommand.SINTER,
					args);
		}else{
			return execute((cmd)->cmd.sdiff(sKeys), ProtocolCommand.SINTER, args);
		}
	}

	@Override
	public Long sInterStore(final String destKey, final String... keys){
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("keys", keys);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().sinterstore(destKey, keys)),
					ProtocolCommand.SINTERSTORE, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().sinterstore(destKey, keys)),
					ProtocolCommand.SINTERSTORE, args);
		}else{
			return execute((cmd)->cmd.sinterstore(destKey, keys), ProtocolCommand.SINTERSTORE, args);
		}
	}

	@Override
	public Long sInterStore(final byte[] destKey, final byte[]... keys){
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("keys", keys);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().sinterstore(destKey, keys)),
					ProtocolCommand.SINTERSTORE, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().sinterstore(destKey, keys)),
					ProtocolCommand.SINTERSTORE, args);
		}else{
			return execute((cmd)->cmd.sinterstore(destKey, keys), ProtocolCommand.SINTERSTORE, args);
		}
	}

	@Override
	public boolean sisMember(final String key, final String member){
		final CommandArguments args = CommandArguments.create("key", key).put("member", member);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().sismember(key, member)),
					ProtocolCommand.SISMEMBER, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().sismember(key, member)),
					ProtocolCommand.SISMEMBER, args);
		}else{
			return execute((cmd)->cmd.sismember(key, member), ProtocolCommand.SISMEMBER, args);
		}
	}

	@Override
	public boolean sisMember(final byte[] key, final byte[] member){
		final CommandArguments args = CommandArguments.create("key", key).put("member", member);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().sismember(key, member)),
					ProtocolCommand.SISMEMBER, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().sismember(key, member)),
					ProtocolCommand.SISMEMBER, args);
		}else{
			return execute((cmd)->cmd.sismember(key, member), ProtocolCommand.SISMEMBER, args);
		}
	}

	@Override
	public Set<String> sMembers(final String key){
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().smembers(key)), ProtocolCommand.SMEMBERS, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().smembers(key)), ProtocolCommand.SMEMBERS,
					args);
		}else{
			return execute((cmd)->cmd.smembers(key), ProtocolCommand.SMEMBERS, args);
		}
	}

	@Override
	public Set<byte[]> sMembers(final byte[] key){
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().smembers(key)), ProtocolCommand.SMEMBERS, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().smembers(key)), ProtocolCommand.SMEMBERS,
					args);
		}else{
			return execute((cmd)->cmd.smembers(key), ProtocolCommand.SMEMBERS, args);
		}
	}

	@Override
	public Boolean sIsMember(final String key, final String member){
		final CommandArguments args = CommandArguments.create("key", key).put("mmbers", member);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().sismember(key, member)),
					ProtocolCommand.SISMEMBER, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().sismember(key, member)),
					ProtocolCommand.SISMEMBER, args);
		}else{
			return execute((cmd)->cmd.sismember(key, member), ProtocolCommand.SISMEMBER, args);
		}
	}

	@Override
	public Boolean sIsMember(final byte[] key, final byte[] member){
		final CommandArguments args = CommandArguments.create("key", key).put("mmbers", member);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().sismember(key, member)),
					ProtocolCommand.SISMEMBER, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().sismember(key, member)),
					ProtocolCommand.SISMEMBER, args);
		}else{
			return execute((cmd)->cmd.sismember(key, member), ProtocolCommand.SISMEMBER, args);
		}
	}

	@Override
	public Status sMove(final String key, final String destKey, final String member){
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey).put("member", member);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().smove(key, destKey, member),
					Converters.LONG_STATUS_CONVERTER), ProtocolCommand.SMOVE, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().smove(key, destKey, member),
					Converters.LONG_STATUS_CONVERTER), ProtocolCommand.SMOVE, args);
		}else{
			return execute((cmd)->cmd.smove(key, destKey, member), Converters.LONG_STATUS_CONVERTER,
					ProtocolCommand.SMOVE, args);
		}
	}

	@Override
	public Status sMove(final byte[] key, final byte[] destKey, final byte[] member){
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey).put("member", member);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().smove(key, destKey, member),
					Converters.LONG_STATUS_CONVERTER), ProtocolCommand.SMOVE, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().smove(key, destKey, member),
					Converters.LONG_STATUS_CONVERTER), ProtocolCommand.SMOVE, args);
		}else{
			return execute((cmd)->cmd.smove(key, destKey, member), Converters.LONG_STATUS_CONVERTER,
					ProtocolCommand.SMOVE, args);
		}
	}

	@Override
	public String sPop(final String key){
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().spop(key)), ProtocolCommand.SPOP, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().spop(key)), ProtocolCommand.SPOP, args);
		}else{
			return execute((cmd)->cmd.spop(key), ProtocolCommand.SPOP, args);
		}
	}

	@Override
	public byte[] sPop(final byte[] key){
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().spop(key)), ProtocolCommand.SPOP, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().spop(key)), ProtocolCommand.SPOP, args);
		}else{
			return execute((cmd)->cmd.spop(key), ProtocolCommand.SPOP, args);
		}
	}

	@Override
	public Set<String> sPop(final String key, final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("count", count);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().spop(key, count)), ProtocolCommand.SPOP, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().spop(key, count)), ProtocolCommand.SPOP,
					args);
		}else{
			return execute((cmd)->cmd.spop(key, count), ProtocolCommand.SPOP, args);
		}
	}

	@Override
	public Set<byte[]> sPop(final byte[] key, final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("count", count);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().spop(key, count)), ProtocolCommand.SPOP, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().spop(key, count)), ProtocolCommand.SPOP,
					args);
		}else{
			return execute((cmd)->cmd.spop(key, count), ProtocolCommand.SPOP, args);
		}
	}

	@Override
	public String sRandMember(final String key){
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().srandmember(key)), ProtocolCommand.SRANDMEMBER,
					args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().srandmember(key)),
					ProtocolCommand.SRANDMEMBER, args);
		}else{
			return execute((cmd)->cmd.srandmember(key), ProtocolCommand.SRANDMEMBER, args);
		}
	}

	@Override
	public byte[] sRandMember(final byte[] key){
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().srandmember(key)), ProtocolCommand.SRANDMEMBER,
					args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().srandmember(key)),
					ProtocolCommand.SRANDMEMBER,
					args);
		}else{
			return execute((cmd)->cmd.srandmember(key), ProtocolCommand.SRANDMEMBER, args);
		}
	}

	@Override
	public Set<String> sRandMember(final String key, final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("count", count);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().srandmember(key, (int) count)),
					ProtocolCommand.SRANDMEMBER, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().srandmember(key, (int) count)),
					ProtocolCommand.SRANDMEMBER, args);
		}else{
			return execute((cmd)->new LinkedHashSet<>(cmd.srandmember(key, (int) count)), ProtocolCommand.SRANDMEMBER,
					args);
		}
	}

	@Override
	public Set<byte[]> sRandMember(final byte[] key, final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("count", count);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().srandmember(key, (int) count)),
					ProtocolCommand.SRANDMEMBER, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().srandmember(key, (int) count)),
					ProtocolCommand.SRANDMEMBER, args);
		}else{
			return execute((cmd)->new LinkedHashSet<>(cmd.srandmember(key, (int) count)), ProtocolCommand.SRANDMEMBER,
					args);
		}
	}

	@Override
	public Long sRem(final String key, final String... members){
		final CommandArguments args = CommandArguments.create("key", key).put("members", members);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().srem(key, members)), ProtocolCommand.SREM, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().srem(key, members)), ProtocolCommand.SREM,
					args);
		}else{
			return execute((cmd)->cmd.srem(key, members), ProtocolCommand.SREM, args);
		}
	}

	@Override
	public Long sRem(final byte[] key, final byte[]... members){
		final CommandArguments args = CommandArguments.create("key", key).put("members", members);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().srem(key, members)), ProtocolCommand.SREM, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().srem(key, members)), ProtocolCommand.SREM,
					args);
		}else{
			return execute((cmd)->cmd.srem(key, members), ProtocolCommand.SREM, args);
		}
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final String cursor){
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor);

		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.SSCAN, args);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.SSCAN, args);
		}else{
			return execute((cmd)->cmd.sscan(key, cursor), STRING_LIST_SCAN_RESULT_EXPOSE_CONVERTER,
					ProtocolCommand.SSCAN, args);
		}
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor){
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor);

		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.SSCAN, args);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.SSCAN, args);
		}else{
			return execute((cmd)->cmd.sscan(key, cursor), BINARY_LIST_SCAN_RESULT_EXPOSE_CONVERTER,
					ProtocolCommand.SSCAN, args);
		}
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final String cursor, final String pattern){
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor).put("pattern", pattern);

		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.SSCAN, args);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.SSCAN, args);
		}else{
			return execute((cmd)->cmd.sscan(key, cursor, new JedisScanParams(pattern)),
					STRING_LIST_SCAN_RESULT_EXPOSE_CONVERTER, ProtocolCommand.SSCAN, args);
		}
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor, final byte[] pattern){
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor).put("pattern", pattern);

		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.SSCAN, args);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.SSCAN, args);
		}else{
			return execute((cmd)->cmd.sscan(key, cursor, new JedisScanParams(pattern)),
					BINARY_LIST_SCAN_RESULT_EXPOSE_CONVERTER, ProtocolCommand.SSCAN, args);
		}
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final String cursor, final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor).put("count", count);

		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.SSCAN, args);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.SSCAN, args);
		}else{
			return execute((cmd)->cmd.sscan(key, cursor, new JedisScanParams(count)),
					STRING_LIST_SCAN_RESULT_EXPOSE_CONVERTER, ProtocolCommand.SSCAN, args);
		}
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor, final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor).put("count", count);

		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.SSCAN, args);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.SSCAN, args);
		}else{
			return execute((cmd)->cmd.sscan(key, cursor, new JedisScanParams(count)),
					BINARY_LIST_SCAN_RESULT_EXPOSE_CONVERTER, ProtocolCommand.SSCAN, args);
		}
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final String cursor, final String pattern,
										  final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor).put("pattern", pattern)
				.put("count", count);

		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.SSCAN, args);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.SSCAN, args);
		}else{
			return execute((cmd)->cmd.sscan(key, cursor, new JedisScanParams(pattern, count)),
					STRING_LIST_SCAN_RESULT_EXPOSE_CONVERTER, ProtocolCommand.SSCAN, args);
		}
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor, final byte[] pattern,
										  final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor).put("pattern", pattern)
				.put("count", count);

		if(isPipeline()){
			return execute(CommandNotSupported.PIPELINE, ProtocolCommand.SSCAN, args);
		}else if(isTransaction()){
			return execute(CommandNotSupported.TRANSACTION, ProtocolCommand.SSCAN, args);
		}else{
			return execute((cmd)->cmd.sscan(key, cursor, new JedisScanParams(pattern, count)),
					BINARY_LIST_SCAN_RESULT_EXPOSE_CONVERTER, ProtocolCommand.SSCAN, args);
		}
	}

	@Override
	public Set<String> sUnion(final String key, final String... keys){
		final CommandArguments args = CommandArguments.create("key", key).put("keys", keys);
		final String[] sKeys = new String[keys == null ? 1 : keys.length + 1];

		sKeys[0] = key;
		if(keys != null){
			System.arraycopy(keys, 0, sKeys, 1, keys.length);
		}

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().sunion(sKeys)), ProtocolCommand.SUNION, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().sunion(sKeys)), ProtocolCommand.SUNION,
					args);
		}else{
			return execute((cmd)->cmd.sunion(sKeys), ProtocolCommand.SUNION, args);
		}
	}

	@Override
	public Set<byte[]> sUnion(final byte[] key, final byte[]... keys){
		final CommandArguments args = CommandArguments.create("key", key).put("keys", keys);
		final byte[][] sKeys = new byte[keys == null ? 1 : keys.length + 1][];

		sKeys[0] = key;
		if(keys != null){
			System.arraycopy(keys, 0, sKeys, 1, keys.length);
		}

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().sunion(sKeys)), ProtocolCommand.SUNION, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().sunion(sKeys)), ProtocolCommand.SUNION,
					args);
		}else{
			return execute((cmd)->cmd.sunion(sKeys), ProtocolCommand.SUNION, args);
		}
	}

	@Override
	public Long sUnionStore(final String destKey, final String... keys){
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("keys", keys);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().sunionstore(destKey, keys)),
					ProtocolCommand.SUNIONSTORE, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().sunionstore(destKey, keys)),
					ProtocolCommand.SUNIONSTORE, args);
		}else{
			return execute((cmd)->cmd.sunionstore(destKey, keys), ProtocolCommand.SUNIONSTORE, args);
		}
	}

	@Override
	public Long sUnionStore(final byte[] destKey, final byte[]... keys){
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("keys", keys);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().sunionstore(destKey, keys)),
					ProtocolCommand.SUNIONSTORE, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().sunionstore(destKey, keys)),
					ProtocolCommand.SUNIONSTORE, args);
		}else{
			return execute((cmd)->cmd.sunionstore(destKey, keys), ProtocolCommand.SUNIONSTORE, args);
		}
	}

}
