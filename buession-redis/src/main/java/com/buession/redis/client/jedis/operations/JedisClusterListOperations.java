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
import com.buession.redis.core.Direction;
import com.buession.redis.core.ListPosition;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.internal.convert.jedis.params.DirectionConverter;
import com.buession.redis.core.internal.convert.jedis.params.ListPositionConverter;
import com.buession.redis.core.internal.jedis.JedisLPosParams;
import redis.clients.jedis.args.ListDirection;
import redis.clients.jedis.params.LPosParams;

import java.util.List;

/**
 * Jedis 集群模式列表命令操作
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public final class JedisClusterListOperations extends AbstractListOperations<JedisClusterClient> {

	public JedisClusterListOperations(final JedisClusterClient client) {
		super(client);
	}

	@Override
	public String lIndex(final String key, final long index) {
		final CommandArguments args = CommandArguments.create("key", key).put("index", index);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, ProtocolCommand.LINDEX, (cmd)->cmd.lindex(key, index),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, ProtocolCommand.LINDEX, (cmd)->cmd.lindex(key, index),
					(v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, ProtocolCommand.LINDEX, (cmd)->cmd.lindex(key, index), (v)->v)
					.run(args);
		}
	}

	@Override
	public byte[] lIndex(final byte[] key, final long index) {
		final CommandArguments args = CommandArguments.create("key", key).put("index", index);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, ProtocolCommand.LINDEX, (cmd)->cmd.lindex(key, index),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, ProtocolCommand.LINDEX, (cmd)->cmd.lindex(key, index),
					(v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, ProtocolCommand.LINDEX, (cmd)->cmd.lindex(key, index), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long lInsert(final String key, final ListPosition position, final String pivot, final String value) {
		final CommandArguments args = CommandArguments.create("key", key).put("position", position).put("pivot", pivot)
				.put("value", value);
		final redis.clients.jedis.args.ListPosition listPosition = (new ListPositionConverter()).convert(position);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, ProtocolCommand.LINSERT,
					(cmd)->cmd.linsert(key, listPosition, pivot, value), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, ProtocolCommand.LINSERT,
					(cmd)->cmd.linsert(key, listPosition, pivot, value), (v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, ProtocolCommand.LINSERT,
					(cmd)->cmd.linsert(key, listPosition, pivot, value), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long lInsert(final byte[] key, final ListPosition position, final byte[] pivot, final byte[] value) {
		final CommandArguments args = CommandArguments.create("key", key).put("position", position).put("pivot", pivot)
				.put("value", value);
		final redis.clients.jedis.args.ListPosition listPosition = (new ListPositionConverter()).convert(position);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, ProtocolCommand.LINSERT,
					(cmd)->cmd.linsert(key, listPosition, pivot, value), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, ProtocolCommand.LINSERT,
					(cmd)->cmd.linsert(key, listPosition, pivot, value), (v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, ProtocolCommand.LINSERT,
					(cmd)->cmd.linsert(key, listPosition, pivot, value), (v)->v)
					.run(args);
		}
	}

	@Override
	public Status lSet(final String key, final long index, final String value) {
		final CommandArguments args = CommandArguments.create("key", key).put("index", index).put("value", value);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, ProtocolCommand.LINSERT,
					(cmd)->cmd.lset(key, index, value), okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, ProtocolCommand.LINSERT,
					(cmd)->cmd.lset(key, index, value), okStatusConverter)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, ProtocolCommand.LINSERT, (cmd)->cmd.lset(key, index, value),
					okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status lSet(final byte[] key, final long index, final byte[] value) {
		final CommandArguments args = CommandArguments.create("key", key).put("index", index).put("value", value);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, ProtocolCommand.LINSERT,
					(cmd)->cmd.lset(key, index, value), okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, ProtocolCommand.LINSERT,
					(cmd)->cmd.lset(key, index, value), okStatusConverter)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, ProtocolCommand.LINSERT, (cmd)->cmd.lset(key, index, value),
					okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Long lLen(final String key) {
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, ProtocolCommand.LLEN, (cmd)->cmd.llen(key), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, ProtocolCommand.LLEN, (cmd)->cmd.llen(key), (v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, ProtocolCommand.LLEN, (cmd)->cmd.llen(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long lLen(final byte[] key) {
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, ProtocolCommand.LLEN, (cmd)->cmd.llen(key), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, ProtocolCommand.LLEN, (cmd)->cmd.llen(key), (v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, ProtocolCommand.LLEN, (cmd)->cmd.llen(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public List<String> lRange(final String key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create("key", key).put("start", start).put("end", end);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, ProtocolCommand.LRANGE, (cmd)->cmd.lrange(key, start, end),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, ProtocolCommand.LRANGE,
					(cmd)->cmd.lrange(key, start, end), (v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, ProtocolCommand.LRANGE, (cmd)->cmd.lrange(key, start, end), (v)->v)
					.run(args);
		}
	}

	@Override
	public List<byte[]> lRange(final byte[] key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create("key", key).put("start", start).put("end", end);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, ProtocolCommand.LRANGE, (cmd)->cmd.lrange(key, start, end),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, ProtocolCommand.LRANGE,
					(cmd)->cmd.lrange(key, start, end), (v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, ProtocolCommand.LRANGE, (cmd)->cmd.lrange(key, start, end), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long lPos(final String key, final String element) {
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, ProtocolCommand.LPOS, (cmd)->cmd.lpos(key, element),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, ProtocolCommand.LPOS, (cmd)->cmd.lpos(key, element),
					(v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, ProtocolCommand.LPOS, (cmd)->cmd.lpos(key, element), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long lPos(final byte[] key, final byte[] element) {
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, ProtocolCommand.LPOS, (cmd)->cmd.lpos(key, element),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, ProtocolCommand.LPOS, (cmd)->cmd.lpos(key, element),
					(v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, ProtocolCommand.LPOS, (cmd)->cmd.lpos(key, element), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long lPos(final String key, final String element, final LPosArgument lPosArgument) {
		final CommandArguments args = CommandArguments.create("key", key).put("lPosArgument", lPosArgument);
		final LPosParams lPosParams = JedisLPosParams.from(lPosArgument);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, ProtocolCommand.LPOS,
					(cmd)->cmd.lpos(key, element, lPosParams), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, ProtocolCommand.LPOS,
					(cmd)->cmd.lpos(key, element, lPosParams), (v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, ProtocolCommand.LPOS, (cmd)->cmd.lpos(key, element, lPosParams),
					(v)->v)
					.run(args);
		}
	}

	@Override
	public Long lPos(final byte[] key, final byte[] element, final LPosArgument lPosArgument) {
		final CommandArguments args = CommandArguments.create("key", key).put("lPosArgument", lPosArgument);
		final LPosParams lPosParams = JedisLPosParams.from(lPosArgument);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, ProtocolCommand.LPOS,
					(cmd)->cmd.lpos(key, element, lPosParams), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, ProtocolCommand.LPOS,
					(cmd)->cmd.lpos(key, element, lPosParams), (v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, ProtocolCommand.LPOS, (cmd)->cmd.lpos(key, element, lPosParams),
					(v)->v)
					.run(args);
		}
	}

	@Override
	public List<Long> lPos(final String key, final String element, final LPosArgument lPosArgument, final long count) {
		final CommandArguments args = CommandArguments.create("key", key).put("lPosArgument", lPosArgument)
				.put("count", count);
		final LPosParams lPosParams = JedisLPosParams.from(lPosArgument);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, ProtocolCommand.LPOS,
					(cmd)->cmd.lpos(key, element, lPosParams, count), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, ProtocolCommand.LPOS,
					(cmd)->cmd.lpos(key, element, lPosParams, count), (v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, ProtocolCommand.LPOS,
					(cmd)->cmd.lpos(key, element, lPosParams, count), (v)->v)
					.run(args);
		}
	}

	@Override
	public List<Long> lPos(final byte[] key, final byte[] element, final LPosArgument lPosArgument, final long count) {
		final CommandArguments args = CommandArguments.create("key", key).put("lPosArgument", lPosArgument)
				.put("count", count);
		final LPosParams lPosParams = JedisLPosParams.from(lPosArgument);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, ProtocolCommand.LPOS,
					(cmd)->cmd.lpos(key, element, lPosParams, count), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, ProtocolCommand.LPOS,
					(cmd)->cmd.lpos(key, element, lPosParams, count), (v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, ProtocolCommand.LPOS,
					(cmd)->cmd.lpos(key, element, lPosParams, count), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long lRem(final String key, final String value, final long count) {
		final CommandArguments args = CommandArguments.create("key", key).put("value", value).put("count", count);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, ProtocolCommand.LREM, (cmd)->cmd.lrem(key, count, value),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, ProtocolCommand.LREM,
					(cmd)->cmd.lrem(key, count, value), (v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, ProtocolCommand.LREM, (cmd)->cmd.lrem(key, count, value), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long lRem(final byte[] key, final byte[] value, final long count) {
		final CommandArguments args = CommandArguments.create("key", key).put("value", value).put("count", count);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, ProtocolCommand.LREM, (cmd)->cmd.lrem(key, count, value),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, ProtocolCommand.LREM,
					(cmd)->cmd.lrem(key, count, value), (v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, ProtocolCommand.LREM, (cmd)->cmd.lrem(key, count, value), (v)->v)
					.run(args);
		}
	}

	@Override
	public Status lTrim(final String key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create("key", key).put("start", start).put("end", end);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, ProtocolCommand.LTRIM, (cmd)->cmd.ltrim(key, start, end),
					okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, ProtocolCommand.LTRIM,
					(cmd)->cmd.ltrim(key, start, end), okStatusConverter)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, ProtocolCommand.LTRIM, (cmd)->cmd.ltrim(key, start, end),
					okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status lTrim(final byte[] key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create("key", key).put("start", start).put("end", end);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, ProtocolCommand.LTRIM, (cmd)->cmd.ltrim(key, start, end),
					okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, ProtocolCommand.LTRIM,
					(cmd)->cmd.ltrim(key, start, end), okStatusConverter)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, ProtocolCommand.LTRIM, (cmd)->cmd.ltrim(key, start, end),
					okStatusConverter)
					.run(args);
		}
	}

	@Override
	public String lMove(final String key, final String destKey, final Direction from, final Direction to) {
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey).put("from", from)
				.put("to", to);
		final DirectionConverter directionConverter = new DirectionConverter();
		final ListDirection fromDirection = directionConverter.convert(from);
		final ListDirection toDirection = directionConverter.convert(to);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, ProtocolCommand.LMOVE,
					(cmd)->cmd.lmove(key, destKey, fromDirection, toDirection), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, ProtocolCommand.LMOVE,
					(cmd)->cmd.lmove(key, destKey, fromDirection, toDirection), (v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, ProtocolCommand.LMOVE,
					(cmd)->cmd.lmove(key, destKey, fromDirection, toDirection), (v)->v)
					.run(args);
		}
	}

	@Override
	public byte[] lMove(final byte[] key, final byte[] destKey, final Direction from, final Direction to) {
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey).put("from", from)
				.put("to", to);
		final DirectionConverter directionConverter = new DirectionConverter();
		final ListDirection fromDirection = directionConverter.convert(from);
		final ListDirection toDirection = directionConverter.convert(to);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, ProtocolCommand.LMOVE,
					(cmd)->cmd.lmove(key, destKey, fromDirection, toDirection), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, ProtocolCommand.LMOVE,
					(cmd)->cmd.lmove(key, destKey, fromDirection, toDirection), (v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, ProtocolCommand.LMOVE,
					(cmd)->cmd.lmove(key, destKey, fromDirection, toDirection), (v)->v)
					.run(args);
		}
	}

	@Override
	public String blMove(final String key, final String destKey, final Direction from, final Direction to,
						 final int timeout) {
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey).put("from", from)
				.put("to", to).put("timeout", timeout);
		final DirectionConverter directionConverter = new DirectionConverter();
		final ListDirection fromDirection = directionConverter.convert(from);
		final ListDirection toDirection = directionConverter.convert(to);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, ProtocolCommand.BLMOVE,
					(cmd)->cmd.blmove(key, destKey, fromDirection, toDirection, timeout), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, ProtocolCommand.BLMOVE,
					(cmd)->cmd.blmove(key, destKey, fromDirection, toDirection, timeout), (v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, ProtocolCommand.BLMOVE,
					(cmd)->cmd.blmove(key, destKey, fromDirection, toDirection, timeout), (v)->v)
					.run(args);
		}
	}

	@Override
	public byte[] blMove(final byte[] key, final byte[] destKey, final Direction from, final Direction to,
						 final int timeout) {
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey).put("from", from)
				.put("to", to).put("timeout", timeout);
		final DirectionConverter directionConverter = new DirectionConverter();
		final ListDirection fromDirection = directionConverter.convert(from);
		final ListDirection toDirection = directionConverter.convert(to);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, ProtocolCommand.BLMOVE,
					(cmd)->cmd.blmove(key, destKey, fromDirection, toDirection, timeout), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, ProtocolCommand.BLMOVE,
					(cmd)->cmd.blmove(key, destKey, fromDirection, toDirection, timeout), (v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, ProtocolCommand.BLMOVE,
					(cmd)->cmd.blmove(key, destKey, fromDirection, toDirection, timeout), (v)->v)
					.run(args);
		}
	}

	@Override
	public List<String> blPop(final String[] keys, final int timeout) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys).put("timeout", timeout);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, ProtocolCommand.BLPOP, (cmd)->cmd.blpop(timeout, keys),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, ProtocolCommand.BLPOP, (cmd)->cmd.blpop(timeout, keys),
					(v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, ProtocolCommand.BLPOP, (cmd)->cmd.blpop(timeout, keys), (v)->v)
					.run(args);
		}
	}

	@Override
	public List<byte[]> blPop(final byte[][] keys, final int timeout) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys).put("timeout", timeout);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, ProtocolCommand.BLPOP, (cmd)->cmd.blpop(timeout, keys),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, ProtocolCommand.BLPOP, (cmd)->cmd.blpop(timeout, keys),
					(v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, ProtocolCommand.BLPOP, (cmd)->cmd.blpop(timeout, keys), (v)->v)
					.run(args);
		}
	}

	@Override
	public List<String> brPop(final String[] keys, final int timeout) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys).put("timeout", timeout);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, ProtocolCommand.BRPOP, (cmd)->cmd.brpop(timeout, keys),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, ProtocolCommand.BRPOP, (cmd)->cmd.brpop(timeout, keys),
					(v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, ProtocolCommand.BRPOP, (cmd)->cmd.brpop(timeout, keys), (v)->v)
					.run(args);
		}
	}

	@Override
	public List<byte[]> brPop(final byte[][] keys, final int timeout) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys).put("timeout", timeout);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, ProtocolCommand.BRPOP, (cmd)->cmd.brpop(timeout, keys),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, ProtocolCommand.BRPOP, (cmd)->cmd.brpop(timeout, keys),
					(v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, ProtocolCommand.BRPOP, (cmd)->cmd.brpop(timeout, keys), (v)->v)
					.run(args);
		}
	}

	@Override
	public String brPoplPush(final String key, final String destKey, final int timeout) {
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey)
				.put("timeout", timeout);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, ProtocolCommand.BRPOPLPUSH,
					(cmd)->cmd.brpoplpush(key, destKey, timeout), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, ProtocolCommand.BRPOPLPUSH,
					(cmd)->cmd.brpoplpush(key, destKey, timeout), (v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, ProtocolCommand.BRPOPLPUSH,
					(cmd)->cmd.brpoplpush(key, destKey, timeout), (v)->v)
					.run(args);
		}
	}

	@Override
	public byte[] brPoplPush(final byte[] key, final byte[] destKey, final int timeout) {
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey)
				.put("timeout", timeout);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, ProtocolCommand.BRPOPLPUSH,
					(cmd)->cmd.brpoplpush(key, destKey, timeout), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, ProtocolCommand.BRPOPLPUSH,
					(cmd)->cmd.brpoplpush(key, destKey, timeout), (v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, ProtocolCommand.BRPOPLPUSH,
					(cmd)->cmd.brpoplpush(key, destKey, timeout), (v)->v)
					.run(args);
		}
	}

	@Override
	public String lPop(final String key) {
		final CommandArguments args = CommandArguments.create(key);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, ProtocolCommand.LPOP, (cmd)->cmd.lpop(key), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, ProtocolCommand.LPOP, (cmd)->cmd.lpop(key), (v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, ProtocolCommand.LPOP, (cmd)->cmd.lpop(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public byte[] lPop(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, ProtocolCommand.LPOP, (cmd)->cmd.lpop(key), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, ProtocolCommand.LPOP, (cmd)->cmd.lpop(key), (v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, ProtocolCommand.LPOP, (cmd)->cmd.lpop(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long lPush(final String key, final String... values) {
		final CommandArguments args = CommandArguments.create("key", key).put("values", (Object[]) values);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, ProtocolCommand.LPUSH, (cmd)->cmd.lpush(key, values),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, ProtocolCommand.LPUSH, (cmd)->cmd.lpush(key, values),
					(v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, ProtocolCommand.LPUSH, (cmd)->cmd.lpush(key, values), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long lPush(final byte[] key, final byte[]... values) {
		final CommandArguments args = CommandArguments.create("key", key).put("values", (Object[]) values);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, ProtocolCommand.LPUSH, (cmd)->cmd.lpush(key, values),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, ProtocolCommand.LPUSH, (cmd)->cmd.lpush(key, values),
					(v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, ProtocolCommand.LPUSH, (cmd)->cmd.lpush(key, values), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long lPushX(final String key, final String... values) {
		final CommandArguments args = CommandArguments.create("key", key).put("values", (Object[]) values);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, ProtocolCommand.LPUSHX, (cmd)->cmd.lpushx(key, values),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, ProtocolCommand.LPUSHX, (cmd)->cmd.lpushx(key, values),
					(v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, ProtocolCommand.LPUSHX, (cmd)->cmd.lpushx(key, values), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long lPushX(final byte[] key, final byte[]... values) {
		final CommandArguments args = CommandArguments.create("key", key).put("values", (Object[]) values);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, ProtocolCommand.LPUSHX, (cmd)->cmd.lpushx(key, values),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, ProtocolCommand.LPUSHX, (cmd)->cmd.lpushx(key, values),
					(v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, ProtocolCommand.LPUSHX, (cmd)->cmd.lpushx(key, values), (v)->v)
					.run(args);
		}
	}

	@Override
	public String rPop(final String key) {
		final CommandArguments args = CommandArguments.create(key);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, ProtocolCommand.RPOP, (cmd)->cmd.rpop(key), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, ProtocolCommand.RPOP, (cmd)->cmd.rpop(key), (v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, ProtocolCommand.RPOP, (cmd)->cmd.rpop(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public byte[] rPop(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, ProtocolCommand.RPOP, (cmd)->cmd.rpop(key), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, ProtocolCommand.RPOP, (cmd)->cmd.rpop(key), (v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, ProtocolCommand.RPOP, (cmd)->cmd.rpop(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public String rPoplPush(final String key, final String destKey) {
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, ProtocolCommand.RPOPLPUSH,
					(cmd)->cmd.rpoplpush(key, destKey), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, ProtocolCommand.RPOPLPUSH,
					(cmd)->cmd.rpoplpush(key, destKey), (v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, ProtocolCommand.RPOPLPUSH, (cmd)->cmd.rpoplpush(key, destKey),
					(v)->v)
					.run(args);
		}
	}

	@Override
	public byte[] rPoplPush(final byte[] key, final byte[] destKey) {
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, ProtocolCommand.RPOPLPUSH,
					(cmd)->cmd.rpoplpush(key, destKey), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, ProtocolCommand.RPOPLPUSH,
					(cmd)->cmd.rpoplpush(key, destKey), (v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, ProtocolCommand.RPOPLPUSH, (cmd)->cmd.rpoplpush(key, destKey),
					(v)->v)
					.run(args);
		}
	}

	@Override
	public Long rPush(final String key, final String... values) {
		final CommandArguments args = CommandArguments.create("key", key).put("values", (Object[]) values);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, ProtocolCommand.RPUSH, (cmd)->cmd.rpush(key, values),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, ProtocolCommand.RPUSH, (cmd)->cmd.rpush(key, values),
					(v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, ProtocolCommand.RPUSH, (cmd)->cmd.rpush(key, values), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long rPush(final byte[] key, final byte[]... values) {
		final CommandArguments args = CommandArguments.create("key", key).put("values", (Object[]) values);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, ProtocolCommand.RPUSH, (cmd)->cmd.rpush(key, values),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, ProtocolCommand.RPUSH, (cmd)->cmd.rpush(key, values),
					(v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, ProtocolCommand.RPUSH, (cmd)->cmd.rpush(key, values), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long rPushX(final String key, final String... values) {
		final CommandArguments args = CommandArguments.create("key", key).put("values", (Object[]) values);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, ProtocolCommand.RPUSHX, (cmd)->cmd.rpushx(key, values),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, ProtocolCommand.RPUSHX, (cmd)->cmd.rpushx(key, values),
					(v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, ProtocolCommand.RPUSHX, (cmd)->cmd.rpushx(key, values), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long rPushX(final byte[] key, final byte[]... values) {
		final CommandArguments args = CommandArguments.create("key", key).put("values", (Object[]) values);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, ProtocolCommand.RPUSHX, (cmd)->cmd.rpushx(key, values),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, ProtocolCommand.RPUSHX, (cmd)->cmd.rpushx(key, values),
					(v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, ProtocolCommand.RPUSHX, (cmd)->cmd.rpushx(key, values), (v)->v)
					.run(args);
		}
	}

}
