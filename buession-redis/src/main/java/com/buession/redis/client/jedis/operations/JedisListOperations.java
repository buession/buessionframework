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
import com.buession.redis.client.jedis.JedisStandaloneClient;
import com.buession.redis.core.Direction;
import com.buession.redis.core.ListPosition;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.convert.Converters;
import com.buession.redis.core.convert.OkStatusConverter;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.args.ListDirection;
import redis.clients.jedis.params.LPosParams;

import java.util.List;

/**
 * Jedis 单机模式列表命令操作抽象类
 *
 * @author Yong.Teng
 */
public final class JedisListOperations extends AbstractListOperations<Jedis> {

	public JedisListOperations(final JedisStandaloneClient client){
		super(client);
	}

	@Override
	public String lIndex(final String key, final long index){
		final CommandArguments args = CommandArguments.create("key", key).put("index", index);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().lindex(key, index)), ProtocolCommand.LINDEX,
					args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().lindex(key, index)),
					ProtocolCommand.LINDEX, args);
		}else{
			return execute((cmd)->cmd.lindex(key, index), ProtocolCommand.LINDEX, args);
		}
	}

	@Override
	public byte[] lIndex(final byte[] key, final long index){
		final CommandArguments args = CommandArguments.create("key", key).put("index", index);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().lindex(key, index)), ProtocolCommand.LINDEX,
					args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().lindex(key, index)),
					ProtocolCommand.LINDEX, args);
		}else{
			return execute((cmd)->cmd.lindex(key, index), ProtocolCommand.LINDEX, args);
		}
	}

	@Override
	public Long lInsert(final String key, final ListPosition position, final String pivot, final String value){
		final CommandArguments args = CommandArguments.create("key", key).put("position", position).put("pivot", pivot)
				.put("value", value);
		final redis.clients.jedis.ListPosition pos = LIST_POSITION_JEDIS_CONVERTER.convert(position);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().linsert(key, pos, pivot, value)),
					ProtocolCommand.LINSERT, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().linsert(key, pos, pivot, value)),
					ProtocolCommand.LINSERT, args);
		}else{
			return execute((cmd)->cmd.linsert(key, pos, pivot, value), ProtocolCommand.LINSERT, args);
		}
	}

	@Override
	public Long lInsert(final byte[] key, final ListPosition position, final byte[] pivot, final byte[] value){
		final CommandArguments args = CommandArguments.create("key", key).put("position", position).put("pivot", pivot)
				.put("value", value);
		final redis.clients.jedis.ListPosition pos = LIST_POSITION_JEDIS_CONVERTER.convert(position);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().linsert(key, pos, pivot, value)),
					ProtocolCommand.LINSERT, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().linsert(key, pos, pivot, value)),
					ProtocolCommand.LINSERT, args);
		}else{
			return execute((cmd)->cmd.linsert(key, pos, pivot, value), ProtocolCommand.LINSERT, args);
		}
	}

	@Override
	public Status lSet(final String key, final long index, final String value){
		final CommandArguments args = CommandArguments.create("key", key).put("index", index).put("value", value);

		if(isPipeline()){
			return pipelineExecute(
					(cmd)->newJedisResult(getPipeline().lset(key, index, value), Converters.OK_STATUS_CONVERTER),
					ProtocolCommand.LSET, args);
		}else if(isTransaction()){
			return transactionExecute(
					(cmd)->newJedisResult(getTransaction().lset(key, index, value), Converters.OK_STATUS_CONVERTER),
					ProtocolCommand.LSET, args);
		}else{
			return execute((cmd)->cmd.lset(key, index, value), Converters.OK_STATUS_CONVERTER, ProtocolCommand.LSET,
					args);
		}
	}

	@Override
	public Status lSet(final byte[] key, final long index, final byte[] value){
		final CommandArguments args = CommandArguments.create("key", key).put("index", index).put("value", value);

		if(isPipeline()){
			return pipelineExecute(
					(cmd)->newJedisResult(getPipeline().lset(key, index, value), Converters.OK_STATUS_CONVERTER),
					ProtocolCommand.LSET, args);
		}else if(isTransaction()){
			return transactionExecute(
					(cmd)->newJedisResult(getTransaction().lset(key, index, value), Converters.OK_STATUS_CONVERTER),
					ProtocolCommand.LSET, args);
		}else{
			return execute((cmd)->cmd.lset(key, index, value), Converters.OK_STATUS_CONVERTER, ProtocolCommand.LSET,
					args);
		}
	}

	@Override
	public Long lLen(final String key){
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().llen(key)), ProtocolCommand.LLEN, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().llen(key)), ProtocolCommand.LLEN, args);
		}else{
			return execute((cmd)->cmd.llen(key), ProtocolCommand.LLEN, args);
		}
	}

	@Override
	public Long lLen(final byte[] key){
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().llen(key)), ProtocolCommand.LLEN, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().llen(key)), ProtocolCommand.LLEN, args);
		}else{
			return execute((cmd)->cmd.llen(key), ProtocolCommand.LLEN, args);
		}
	}

	@Override
	public List<String> lRange(final String key, final long start, final long end){
		final CommandArguments args = CommandArguments.create("key", key).put("start", start).put("end", end);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().lrange(key, start, end)), ProtocolCommand.LRANGE,
					args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().lrange(key, start, end)),
					ProtocolCommand.LRANGE,
					args);
		}else{
			return execute((cmd)->cmd.lrange(key, start, end), ProtocolCommand.LRANGE, args);
		}
	}

	@Override
	public List<byte[]> lRange(final byte[] key, final long start, final long end){
		final CommandArguments args = CommandArguments.create("key", key).put("start", start).put("end", end);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().lrange(key, start, end)), ProtocolCommand.LRANGE,
					args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().lrange(key, start, end)),
					ProtocolCommand.LRANGE,
					args);
		}else{
			return execute((cmd)->cmd.lrange(key, start, end), ProtocolCommand.LRANGE, args);
		}
	}

	@Override
	public Long lPos(final String key, final String element){
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().lpop(key)), ProtocolCommand.LPOS, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().lpop(key)), ProtocolCommand.LPOS, args);
		}else{
			return execute((cmd)->cmd.lpos(key, element), ProtocolCommand.LPOS, args);
		}
	}

	@Override
	public Long lPos(final byte[] key, final byte[] element){
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().lpop(key)), ProtocolCommand.LPOS, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().lpop(key)), ProtocolCommand.LPOS, args);
		}else{
			return execute((cmd)->cmd.lpos(key, element), ProtocolCommand.LPOS, args);
		}
	}

	@Override
	public Long lPos(final String key, final String element, final LPosArgument lPosArgument){
		final CommandArguments args = CommandArguments.create("key", key).put("lPosArgument", lPosArgument);
		final LPosParams lPosParams = L_POS_ARGUMENT_JEDIS_CONVERTER.convert(lPosArgument);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().lpos(key, element, lPosParams)),
					ProtocolCommand.LPOS, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().lpos(key, element, lPosParams)),
					ProtocolCommand.LPOS, args);
		}else{
			return execute((cmd)->cmd.lpos(key, element, lPosParams), ProtocolCommand.LPOS, args);
		}
	}

	@Override
	public Long lPos(final byte[] key, final byte[] element, final LPosArgument lPosArgument){
		final CommandArguments args = CommandArguments.create("key", key).put("lPosArgument", lPosArgument);
		final LPosParams lPosParams = L_POS_ARGUMENT_JEDIS_CONVERTER.convert(lPosArgument);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().lpos(key, element, lPosParams)),
					ProtocolCommand.LPOS, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().lpos(key, element, lPosParams)),
					ProtocolCommand.LPOS, args);
		}else{
			return execute((cmd)->cmd.lpos(key, element, lPosParams), ProtocolCommand.LPOS, args);
		}
	}

	@Override
	public List<Long> lPos(final String key, final String element, final LPosArgument lPosArgument, final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("lPosArgument", lPosArgument)
				.put("count", count);
		final LPosParams lPosParams = L_POS_ARGUMENT_JEDIS_CONVERTER.convert(lPosArgument);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().lpos(key, element, lPosParams, count)),
					ProtocolCommand.LPOS, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().lpos(key, element, lPosParams, count)),
					ProtocolCommand.LPOS, args);
		}else{
			return execute((cmd)->cmd.lpos(key, element, lPosParams, count), ProtocolCommand.LPOS, args);
		}
	}

	@Override
	public List<Long> lPos(final byte[] key, final byte[] element, final LPosArgument lPosArgument, final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("lPosArgument", lPosArgument)
				.put("count", count);
		final LPosParams lPosParams = L_POS_ARGUMENT_JEDIS_CONVERTER.convert(lPosArgument);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().lpos(key, element, lPosParams, count)),
					ProtocolCommand.LPOS, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().lpos(key, element, lPosParams, count)),
					ProtocolCommand.LPOS, args);
		}else{
			return execute((cmd)->cmd.lpos(key, element, lPosParams, count), ProtocolCommand.LPOS, args);
		}
	}

	@Override
	public Long lRem(final String key, final String value, final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("value", value).put("count", count);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().lrem(key, count, value)), ProtocolCommand.LREM,
					args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().lrem(key, count, value)),
					ProtocolCommand.LREM, args);
		}else{
			return execute((cmd)->cmd.lrem(key, count, value), ProtocolCommand.LREM, args);
		}
	}

	@Override
	public Long lRem(final byte[] key, final byte[] value, final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("value", value).put("count", count);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().lrem(key, count, value)), ProtocolCommand.LREM,
					args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().lrem(key, count, value)),
					ProtocolCommand.LREM, args);
		}else{
			return execute((cmd)->cmd.lrem(key, count, value), ProtocolCommand.LREM, args);
		}
	}

	@Override
	public Status lTrim(final String key, final long start, final long end){
		final CommandArguments args = CommandArguments.create("key", key).put("start", start).put("end", end);

		if(isPipeline()){
			return pipelineExecute(
					(cmd)->newJedisResult(getPipeline().ltrim(key, start, end), Converters.OK_STATUS_CONVERTER),
					ProtocolCommand.LTRIM, args);
		}else if(isTransaction()){
			return transactionExecute(
					(cmd)->newJedisResult(getTransaction().ltrim(key, start, end), Converters.OK_STATUS_CONVERTER),
					ProtocolCommand.LTRIM, args);
		}else{
			return execute((cmd)->cmd.ltrim(key, start, end), Converters.OK_STATUS_CONVERTER, ProtocolCommand.LTRIM,
					args);
		}
	}

	@Override
	public Status lTrim(final byte[] key, final long start, final long end){
		final CommandArguments args = CommandArguments.create("key", key).put("start", start).put("end", end);

		if(isPipeline()){
			return pipelineExecute(
					(cmd)->newJedisResult(getPipeline().ltrim(key, start, end), Converters.OK_STATUS_CONVERTER),
					ProtocolCommand.LTRIM, args);
		}else if(isTransaction()){
			return transactionExecute(
					(cmd)->newJedisResult(getTransaction().ltrim(key, start, end), Converters.OK_STATUS_CONVERTER),
					ProtocolCommand.LTRIM, args);
		}else{
			return execute((cmd)->cmd.ltrim(key, start, end), Converters.OK_STATUS_CONVERTER, ProtocolCommand.LTRIM,
					args);
		}
	}

	@Override
	public String lMove(final String key, final String destKey, final Direction from, final Direction to){
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey).put("from", from)
				.put("to", to);
		final ListDirection fromDirection = DIRECTION_JEDIS_CONVERTER.convert(from);
		final ListDirection toDirection = DIRECTION_JEDIS_CONVERTER.convert(to);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(
					getPipeline().lmove(key, destKey, fromDirection, toDirection)), ProtocolCommand.LMOVE, args);
		}else if(isTransaction()){
			return transactionExecute(
					(cmd)->newJedisResult(
							getTransaction().lmove(key, destKey, fromDirection, toDirection)), ProtocolCommand.LMOVE,
					args);
		}else{
			return execute((cmd)->cmd.lmove(key, destKey, fromDirection, toDirection), ProtocolCommand.LMOVE, args);
		}
	}

	@Override
	public byte[] lMove(final byte[] key, final byte[] destKey, final Direction from, final Direction to){
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey).put("from", from)
				.put("to", to);
		final ListDirection fromDirection = DIRECTION_JEDIS_CONVERTER.convert(from);
		final ListDirection toDirection = DIRECTION_JEDIS_CONVERTER.convert(to);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(
					getPipeline().lmove(key, destKey, fromDirection, toDirection)), ProtocolCommand.LMOVE, args);
		}else if(isTransaction()){
			return transactionExecute(
					(cmd)->newJedisResult(
							getTransaction().lmove(key, destKey, fromDirection, toDirection)), ProtocolCommand.LMOVE,
					args);
		}else{
			return execute((cmd)->cmd.lmove(key, destKey, fromDirection, toDirection), ProtocolCommand.LMOVE, args);
		}
	}

	@Override
	public String blMove(final String key, final String destKey, final Direction from, final Direction to,
						 final int timeout){
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey).put("from", from)
				.put("to", to).put("timeout", timeout);
		final ListDirection fromDirection = DIRECTION_JEDIS_CONVERTER.convert(from);
		final ListDirection toDirection = DIRECTION_JEDIS_CONVERTER.convert(to);

		if(isPipeline()){
			return pipelineExecute(
					(cmd)->newJedisResult(getPipeline().blmove(key, destKey, fromDirection, toDirection, timeout)),
					ProtocolCommand.BLMOVE, args);
		}else if(isTransaction()){
			return transactionExecute(
					(cmd)->newJedisResult(getTransaction().blmove(key, destKey, fromDirection, toDirection, timeout)),
					ProtocolCommand.BLMOVE, args);
		}else{
			return execute((cmd)->cmd.blmove(key, destKey, fromDirection, toDirection, timeout), ProtocolCommand.BLMOVE,
					args);
		}
	}

	@Override
	public byte[] blMove(final byte[] key, final byte[] destKey, final Direction from, final Direction to,
						 final int timeout){
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey).put("from", from)
				.put("to", to).put("timeout", timeout);
		final ListDirection fromDirection = DIRECTION_JEDIS_CONVERTER.convert(from);
		final ListDirection toDirection = DIRECTION_JEDIS_CONVERTER.convert(to);

		if(isPipeline()){
			return pipelineExecute(
					(cmd)->newJedisResult(getPipeline().blmove(key, destKey, fromDirection, toDirection, timeout)),
					ProtocolCommand.BLMOVE, args);
		}else if(isTransaction()){
			return transactionExecute(
					(cmd)->newJedisResult(getTransaction().blmove(key, destKey, fromDirection, toDirection, timeout)),
					ProtocolCommand.BLMOVE, args);
		}else{
			return execute((cmd)->cmd.blmove(key, destKey, fromDirection, toDirection, timeout), ProtocolCommand.BLMOVE,
					args);
		}
	}

	@Override
	public List<String> blPop(final String[] keys, final int timeout){
		final CommandArguments args = CommandArguments.create("keys", keys).put("timeout", timeout);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().blpop(keys)), ProtocolCommand.BLPOP, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().blpop(timeout, keys)),
					ProtocolCommand.BLPOP, args);
		}else{
			return execute((cmd)->cmd.blpop(timeout, keys), ProtocolCommand.BLPOP, args);
		}
	}

	@Override
	public List<byte[]> blPop(final byte[][] keys, final int timeout){
		final CommandArguments args = CommandArguments.create("keys", keys).put("timeout", timeout);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().blpop(keys)), ProtocolCommand.BLPOP, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().blpop(timeout, keys)),
					ProtocolCommand.BLPOP, args);
		}else{
			return execute((cmd)->cmd.blpop(timeout, keys), ProtocolCommand.BLPOP, args);
		}
	}

	@Override
	public List<String> brPop(final String[] keys, final int timeout){
		final CommandArguments args = CommandArguments.create("keys", keys).put("timeout", timeout);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().brpop(keys)), ProtocolCommand.BRPOP, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().brpop(timeout, keys)),
					ProtocolCommand.BRPOP, args);
		}else{
			return execute((cmd)->cmd.brpop(timeout, keys), ProtocolCommand.BRPOP, args);
		}
	}

	@Override
	public List<byte[]> brPop(final byte[][] keys, final int timeout){
		final CommandArguments args = CommandArguments.create("keys", keys).put("timeout", timeout);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().brpop(keys)), ProtocolCommand.BRPOP, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().brpop(timeout, keys)),
					ProtocolCommand.BRPOP, args);
		}else{
			return execute((cmd)->cmd.brpop(timeout, keys), ProtocolCommand.BRPOP, args);
		}
	}

	@Override
	public String lPop(final String key){
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().lpop(key)), ProtocolCommand.LPOP, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().lpop(key)), ProtocolCommand.LPOP, args);
		}else{
			return execute((cmd)->cmd.lpop(key), ProtocolCommand.LPOP, args);
		}
	}

	@Override
	public byte[] lPop(final byte[] key){
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().lpop(key)), ProtocolCommand.LPOP, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().lpop(key)), ProtocolCommand.LPOP, args);
		}else{
			return execute((cmd)->cmd.lpop(key), ProtocolCommand.LPOP, args);
		}
	}

	@Override
	public Long lPush(final String key, final String... values){
		final CommandArguments args = CommandArguments.create("key", key).put("values", values);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().lpush(key, values)), ProtocolCommand.LPUSH,
					args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().lpush(key, values)), ProtocolCommand.LPUSH,
					args);
		}else{
			return execute((cmd)->cmd.lpush(key, values), ProtocolCommand.LPUSH, args);
		}
	}

	@Override
	public Long lPush(final byte[] key, final byte[]... values){
		final CommandArguments args = CommandArguments.create("key", key).put("values", values);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().lpush(key, values)), ProtocolCommand.LPUSH,
					args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().lpush(key, values)), ProtocolCommand.LPUSH,
					args);
		}else{
			return execute((cmd)->cmd.lpush(key, values), ProtocolCommand.LPUSH, args);
		}
	}

	@Override
	public Long lPushX(final String key, final String... values){
		final CommandArguments args = CommandArguments.create("key", key).put("values", values);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().lpushx(key, values)), ProtocolCommand.LPUSHX,
					args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().lpushx(key, values)),
					ProtocolCommand.LPUSHX,
					args);
		}else{
			return execute((cmd)->cmd.lpushx(key, values), ProtocolCommand.LPUSHX, args);
		}
	}

	@Override
	public Long lPushX(final byte[] key, final byte[]... values){
		final CommandArguments args = CommandArguments.create("key", key).put("values", values);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().lpushx(key, values)), ProtocolCommand.LPUSHX,
					args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().lpushx(key, values)),
					ProtocolCommand.LPUSHX,
					args);
		}else{
			return execute((cmd)->cmd.lpushx(key, values), ProtocolCommand.LPUSHX, args);
		}
	}

	@Override
	public String rPop(final String key){
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().rpop(key)), ProtocolCommand.RPOP, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().rpop(key)), ProtocolCommand.RPOP, args);
		}else{
			return execute((cmd)->cmd.rpop(key), ProtocolCommand.RPOP, args);
		}
	}

	@Override
	public byte[] rPop(final byte[] key){
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().rpop(key)), ProtocolCommand.RPOP, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().rpop(key)), ProtocolCommand.RPOP, args);
		}else{
			return execute((cmd)->cmd.rpop(key), ProtocolCommand.RPOP, args);
		}
	}

	@Override
	public String rPoplPush(final String key, final String destKey){
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().rpoplpush(key, destKey)),
					ProtocolCommand.RPOPLPUSH, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().rpoplpush(key, destKey)),
					ProtocolCommand.RPOPLPUSH, args);
		}else{
			return execute((cmd)->cmd.rpoplpush(key, destKey), ProtocolCommand.RPOPLPUSH, args);
		}
	}

	@Override
	public byte[] rPoplPush(final byte[] key, final byte[] destKey){
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().rpoplpush(key, destKey)),
					ProtocolCommand.RPOPLPUSH, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().rpoplpush(key, destKey)),
					ProtocolCommand.RPOPLPUSH, args);
		}else{
			return execute((cmd)->cmd.rpoplpush(key, destKey), ProtocolCommand.RPOPLPUSH, args);
		}
	}

	@Override
	public Long rPush(final String key, final String... values){
		final CommandArguments args = CommandArguments.create("key", key).put("values", values);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().rpush(key, values)), ProtocolCommand.RPUSH,
					args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().rpush(key, values)), ProtocolCommand.RPUSH,
					args);
		}else{
			return execute((cmd)->cmd.rpush(key, values), ProtocolCommand.RPUSH, args);
		}
	}

	@Override
	public Long rPush(final byte[] key, final byte[]... values){
		final CommandArguments args = CommandArguments.create("key", key).put("values", values);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().rpush(key, values)), ProtocolCommand.RPUSH,
					args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().rpush(key, values)), ProtocolCommand.RPUSH,
					args);
		}else{
			return execute((cmd)->cmd.rpush(key, values), ProtocolCommand.RPUSH, args);
		}
	}

	@Override
	public Long rPushX(final String key, final String... values){
		final CommandArguments args = CommandArguments.create("key", key).put("values", values);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().rpushx(key, values)), ProtocolCommand.RPUSHX,
					args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().rpushx(key, values)),
					ProtocolCommand.RPUSHX, args);
		}else{
			return execute((cmd)->cmd.rpushx(key, values), ProtocolCommand.RPUSHX, args);
		}
	}

	@Override
	public Long rPushX(final byte[] key, final byte[]... values){
		final CommandArguments args = CommandArguments.create("key", key).put("values", values);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().rpushx(key, values)), ProtocolCommand.RPUSHX,
					args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().rpushx(key, values)),
					ProtocolCommand.RPUSHX, args);
		}else{
			return execute((cmd)->cmd.rpushx(key, values), ProtocolCommand.RPUSHX, args);
		}
	}

	@Override
	public String brPoplPush(final String key, final String destKey, final int timeout){
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey)
				.put("timeout", timeout);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().brpoplpush(key, destKey, timeout)),
					ProtocolCommand.BRPOPLPUSH, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().brpoplpush(key, destKey, timeout)),
					ProtocolCommand.BRPOPLPUSH, args);
		}else{
			return execute((cmd)->cmd.brpoplpush(key, destKey, timeout), ProtocolCommand.BRPOPLPUSH, args);
		}
	}

	@Override
	public byte[] brPoplPush(final byte[] key, final byte[] destKey, final int timeout){
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey)
				.put("timeout", timeout);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().brpoplpush(key, destKey, timeout)),
					ProtocolCommand.BRPOPLPUSH, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().brpoplpush(key, destKey, timeout)),
					ProtocolCommand.BRPOPLPUSH, args);
		}else{
			return execute((cmd)->cmd.brpoplpush(key, destKey, timeout), ProtocolCommand.BRPOPLPUSH, args);
		}
	}

}
