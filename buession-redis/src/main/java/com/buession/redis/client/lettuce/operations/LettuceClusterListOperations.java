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

import com.buession.core.builder.ListBuilder;
import com.buession.core.converter.Converter;
import com.buession.core.converter.ListConverter;
import com.buession.lang.Status;
import com.buession.redis.client.lettuce.LettuceClusterClient;
import com.buession.redis.core.Direction;
import com.buession.redis.core.ListPosition;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.Command;
import com.buession.redis.core.command.args.LPosArgument;
import com.buession.redis.core.internal.convert.Converters;
import com.buession.redis.core.internal.lettuce.LettuceLPosArgs;
import com.buession.redis.core.internal.lettuce.utils.LMoveArgsUtils;
import com.buession.redis.utils.SafeEncoder;
import io.lettuce.core.KeyValue;
import io.lettuce.core.LMoveArgs;
import io.lettuce.core.LPosArgs;

import java.util.List;

/**
 * Lettuce 集群模式列表命令操作
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public final class LettuceClusterListOperations extends AbstractListOperations<LettuceClusterClient> {

	public LettuceClusterListOperations(final LettuceClusterClient client) {
		super(client);
	}

	@Override
	public String lIndex(final String key, final long index) {
		final CommandArguments args = CommandArguments.create("key", key).put("index", index);
		final byte[] bKey = SafeEncoder.encode(key);

		return lIndex(bKey, index, SafeEncoder::encode, args);
	}

	@Override
	public byte[] lIndex(final byte[] key, final long index) {
		final CommandArguments args = CommandArguments.create("key", key).put("index", index);
		return lIndex(key, index, (v)->v, args);
	}

	@Override
	public Long lInsert(final byte[] key, final ListPosition position, final byte[] pivot, final byte[] value) {
		final CommandArguments args = CommandArguments.create("key", key).put("position", position).put("pivot", pivot)
				.put("value", value);
		final boolean before = ListPosition.BEFORE == position;

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.LINSERT,
					(cmd)->cmd.linsert(key, before, pivot, value), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.LINSERT,
					(cmd)->cmd.linsert(key, before, pivot, value), (v)->v)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, Command.LINSERT,
					(cmd)->cmd.linsert(key, before, pivot, value), (v)->v)
					.run(args);
		}
	}

	@Override
	public Status lSet(final byte[] key, final long index, final byte[] value) {
		final CommandArguments args = CommandArguments.create("key", key).put("index", index).put("value", value);

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.LSET, (cmd)->cmd.lset(key, index, value),
					okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.LSET,
					(cmd)->cmd.lset(key, index, value), okStatusConverter)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, Command.LSET, (cmd)->cmd.lset(key, index, value),
					okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Long lLen(final byte[] key) {
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.LLEN, (cmd)->cmd.llen(key), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.LLEN, (cmd)->cmd.llen(key), (v)->v)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, Command.LLEN, (cmd)->cmd.llen(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public List<String> lRange(final String key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create("key", key).put("start", start).put("end", end);
		final byte[] bKey = SafeEncoder.encode(key);
		final ListConverter<byte[], String> listConverter = Converters.listBinaryToString();

		return lRange(bKey, start, end, listConverter, args);
	}

	@Override
	public List<byte[]> lRange(final byte[] key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create("key", key).put("start", start).put("end", end);
		return lRange(key, start, end, (v)->v, args);
	}

	@Override
	public Long lPos(final byte[] key, final byte[] element) {
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.LPOS, (cmd)->cmd.lpos(key, element),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.LPOS, (cmd)->cmd.lpos(key, element),
					(v)->v)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, Command.LPOS, (cmd)->cmd.lpos(key, element), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long lPos(final byte[] key, final byte[] element, final LPosArgument lPosArgument) {
		final CommandArguments args = CommandArguments.create("key", key).put("lPosArgument", lPosArgument);
		final LPosArgs lPosArgs = LettuceLPosArgs.from(lPosArgument);

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.LPOS,
					(cmd)->cmd.lpos(key, element, lPosArgs), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.LPOS,
					(cmd)->cmd.lpos(key, element, lPosArgs), (v)->v)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, Command.LPOS, (cmd)->cmd.lpos(key, element, lPosArgs),
					(v)->v)
					.run(args);
		}
	}

	@Override
	public List<Long> lPos(final byte[] key, final byte[] element, final LPosArgument lPosArgument, final int count) {
		final CommandArguments args = CommandArguments.create("key", key).put("lPosArgument", lPosArgument)
				.put("count", count);
		final LPosArgs lPosArgs = LettuceLPosArgs.from(lPosArgument);

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.LPOS,
					(cmd)->cmd.lpos(key, element, (int) count, lPosArgs), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.LPOS,
					(cmd)->cmd.lpos(key, element, (int) count, lPosArgs), (v)->v)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, Command.LPOS,
					(cmd)->cmd.lpos(key, element, (int) count, lPosArgs), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long lRem(final byte[] key, final byte[] value, final int count) {
		final CommandArguments args = CommandArguments.create("key", key).put("value", value).put("count", count);

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.LREM, (cmd)->cmd.lrem(key, count, value),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.LREM,
					(cmd)->cmd.lrem(key, count, value), (v)->v)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, Command.LREM, (cmd)->cmd.lrem(key, count, value), (v)->v)
					.run(args);
		}
	}

	@Override
	public Status lTrim(final byte[] key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create("key", key).put("start", start).put("end", end);

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.LTRIM, (cmd)->cmd.ltrim(key, start, end),
					okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.LTRIM,
					(cmd)->cmd.ltrim(key, start, end), okStatusConverter)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, Command.LTRIM, (cmd)->cmd.ltrim(key, start, end),
					okStatusConverter)
					.run(args);
		}
	}

	@Override
	public String lMove(final String key, final String destKey, final Direction from, final Direction to) {
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey).put("from", from)
				.put("to", to);
		final byte[] bKey = SafeEncoder.encode(key);
		final byte[] bDestKey = SafeEncoder.encode(destKey);
		final LMoveArgs lMoveArgs = LMoveArgsUtils.fromDirection(from, to);

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.LMOVE,
					(cmd)->cmd.lmove(bKey, bDestKey, lMoveArgs), SafeEncoder::encode)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.LMOVE,
					(cmd)->cmd.lmove(bKey, bDestKey, lMoveArgs), SafeEncoder::encode)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, Command.LMOVE,
					(cmd)->cmd.lmove(bKey, bDestKey, lMoveArgs), SafeEncoder::encode)
					.run(args);
		}
	}

	@Override
	public byte[] lMove(final byte[] key, final byte[] destKey, final Direction from, final Direction to) {
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey).put("from", from)
				.put("to", to);
		final LMoveArgs lMoveArgs = LMoveArgsUtils.fromDirection(from, to);

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.LMOVE,
					(cmd)->cmd.lmove(key, key, lMoveArgs), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.LMOVE,
					(cmd)->cmd.lmove(key, key, lMoveArgs), (v)->v)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, Command.LMOVE,
					(cmd)->cmd.lmove(key, key, lMoveArgs), (v)->v)
					.run(args);
		}
	}

	@Override
	public String blMove(final String key, final String destKey, final Direction from, final Direction to,
						 final int timeout) {
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey).put("from", from)
				.put("to", to);
		final byte[] bKey = SafeEncoder.encode(key);
		final byte[] bDestKey = SafeEncoder.encode(destKey);
		final LMoveArgs lMoveArgs = LMoveArgsUtils.fromDirection(from, to);

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.BLMOVE,
					(cmd)->cmd.lmove(bKey, bDestKey, lMoveArgs), SafeEncoder::encode)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.BLMOVE,
					(cmd)->cmd.lmove(bKey, bDestKey, lMoveArgs), SafeEncoder::encode)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, Command.BLMOVE,
					(cmd)->cmd.lmove(bKey, bDestKey, lMoveArgs), SafeEncoder::encode)
					.run(args);
		}
	}

	@Override
	public byte[] blMove(final byte[] key, final byte[] destKey, final Direction from, final Direction to,
						 final int timeout) {
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey).put("from", from)
				.put("to", to).put("timeout", timeout);
		final LMoveArgs lMoveArgs = LMoveArgsUtils.fromDirection(from, to);

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.BLMOVE,
					(cmd)->cmd.blmove(key, key, lMoveArgs, timeout), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.BLMOVE,
					(cmd)->cmd.blmove(key, key, lMoveArgs, timeout), (v)->v)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, Command.BLMOVE,
					(cmd)->cmd.blmove(key, key, lMoveArgs, timeout), (v)->v)
					.run(args);
		}
	}

	@Override
	public List<String> blPop(final String[] keys, final int timeout) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys).put("timeout", timeout);
		final byte[][] bKeys = SafeEncoder.encode(keys);
		final Converter<KeyValue<byte[], byte[]>, List<String>> converter =
				(v)->ListBuilder.of(SafeEncoder.encode(v.getValue()));

		return blPop(bKeys, timeout, converter, args);
	}

	@Override
	public List<byte[]> blPop(final byte[][] keys, final int timeout) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys).put("timeout", timeout);
		final Converter<KeyValue<byte[], byte[]>, List<byte[]>> converter = (v)->ListBuilder.of(v.getValue());

		return blPop(keys, timeout, converter, args);
	}

	@Override
	public List<String> brPop(final String[] keys, final int timeout) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys).put("timeout", timeout);
		final byte[][] bKeys = SafeEncoder.encode(keys);
		final Converter<KeyValue<byte[], byte[]>, List<String>> converter =
				(v)->ListBuilder.of(SafeEncoder.encode(v.getValue()));

		return brPop(bKeys, timeout, converter, args);
	}

	@Override
	public List<byte[]> brPop(final byte[][] keys, final int timeout) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys).put("timeout", timeout);
		final Converter<KeyValue<byte[], byte[]>, List<byte[]>> converter = (v)->ListBuilder.of(v.getValue());

		return brPop(keys, timeout, converter, args);
	}

	@Override
	public String brPoplPush(final String key, final String destKey, final int timeout) {
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey)
				.put("timeout", timeout);
		final byte[] bKey = SafeEncoder.encode(key);
		final byte[] bDestKey = SafeEncoder.encode(destKey);

		return brPoplPush(bKey, bDestKey, timeout, SafeEncoder::encode, args);
	}

	@Override
	public byte[] brPoplPush(final byte[] key, final byte[] destKey, final int timeout) {
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey)
				.put("timeout", timeout);
		return brPoplPush(key, destKey, timeout, (v)->v, args);
	}

	@Override
	public String lPop(final String key) {
		final CommandArguments args = CommandArguments.create("key", key);
		final byte[] bKey = SafeEncoder.encode(key);

		return lPop(bKey, SafeEncoder::encode, args);
	}

	@Override
	public byte[] lPop(final byte[] key) {
		final CommandArguments args = CommandArguments.create("key", key);
		return lPop(key, (v)->v, args);
	}

	@Override
	public Long lPush(final byte[] key, final byte[]... values) {
		final CommandArguments args = CommandArguments.create("key", key).put("values", (Object[]) values);

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.LPUSH, (cmd)->cmd.lpush(key, values),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.LPUSH, (cmd)->cmd.lpush(key, values),
					(v)->v)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, Command.LPUSH, (cmd)->cmd.lpush(key, values), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long lPushX(final byte[] key, final byte[]... values) {
		final CommandArguments args = CommandArguments.create("key", key).put("values", (Object[]) values);

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.LPUSHX, (cmd)->cmd.lpushx(key, values),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.LPUSHX,
					(cmd)->cmd.lpushx(key, values), (v)->v)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, Command.LPUSHX, (cmd)->cmd.lpushx(key, values), (v)->v)
					.run(args);
		}
	}

	@Override
	public String rPop(final String key) {
		final CommandArguments args = CommandArguments.create("key", key);
		final byte[] bKey = SafeEncoder.encode(key);

		return rPop(bKey, SafeEncoder::encode, args);
	}

	@Override
	public byte[] rPop(final byte[] key) {
		final CommandArguments args = CommandArguments.create("key", key);
		return rPop(key, (v)->v, args);
	}

	@Override
	public String rPoplPush(final String key, final String destKey) {
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey);
		final byte[] bKey = SafeEncoder.encode(key);
		final byte[] bDestKey = SafeEncoder.encode(destKey);

		return rPoplPush(bKey, bDestKey, SafeEncoder::encode, args);
	}

	@Override
	public byte[] rPoplPush(final byte[] key, final byte[] destKey) {
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey);
		return rPoplPush(key, destKey, (v)->v, args);
	}

	@Override
	public Long rPush(final byte[] key, final byte[]... values) {
		final CommandArguments args = CommandArguments.create("key", key).put("values", (Object[]) values);

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.RPUSH, (cmd)->cmd.rpush(key, values),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.RPUSH, (cmd)->cmd.rpush(key, values),
					(v)->v)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, Command.RPUSH, (cmd)->cmd.rpush(key, values), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long rPushX(final byte[] key, final byte[]... values) {
		final CommandArguments args = CommandArguments.create("key", key).put("values", (Object[]) values);

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.RPUSHX, (cmd)->cmd.rpushx(key, values),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.RPUSHX,
					(cmd)->cmd.rpushx(key, values), (v)->v)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, Command.RPUSHX, (cmd)->cmd.rpushx(key, values), (v)->v)
					.run(args);
		}
	}

	private <V> V lIndex(final byte[] key, final long index, final Converter<byte[], V> converter,
						 final CommandArguments args) {
		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.LINDEX, (cmd)->cmd.lindex(key, index),
					converter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.LINDEX, (cmd)->cmd.lindex(key, index),
					converter)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, Command.LINDEX, (cmd)->cmd.lindex(key, index), converter)
					.run(args);
		}
	}

	private <V> List<V> lRange(final byte[] key, final long start, final long end,
							   final Converter<List<byte[]>, List<V>> converter, final CommandArguments args) {
		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.LRANGE,
					(cmd)->cmd.lrange(key, start, end), converter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.LRANGE,
					(cmd)->cmd.lrange(key, start, end), converter)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, Command.LRANGE, (cmd)->cmd.lrange(key, start, end),
					converter)
					.run(args);
		}
	}

	private <V> V blMove(final CommandArguments args) {
		if(isPipeline()){
			return new LettuceClusterPipelineCommand<V, V>(client, Command.BLMOVE)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<V, V>(client, Command.BLMOVE)
					.run(args);
		}else{
			return new LettuceClusterCommand<V, V>(client, Command.BLMOVE)
					.run(args);
		}
	}

	private <V> List<V> blPop(final byte[][] keys, final int timeout, final Converter<KeyValue<byte[], byte[]>,
			List<V>> converter, final CommandArguments args) {
		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.BLPOP, (cmd)->cmd.blpop(timeout, keys),
					converter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.BLPOP,
					(cmd)->cmd.blpop(timeout, keys), converter)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, Command.BLPOP, (cmd)->cmd.blpop(timeout, keys),
					converter)
					.run(args);
		}
	}

	private <V> List<V> brPop(final byte[][] keys, final int timeout, final Converter<KeyValue<byte[], byte[]>,
			List<V>> converter, final CommandArguments args) {
		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.BRPOP, (cmd)->cmd.brpop(timeout, keys),
					converter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.BRPOP,
					(cmd)->cmd.brpop(timeout, keys), converter)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, Command.BRPOP, (cmd)->cmd.brpop(timeout, keys),
					converter)
					.run(args);
		}
	}

	private <V> V brPoplPush(final byte[] key, final byte[] destKey, final int timeout,
							 final Converter<byte[], V> converter, final CommandArguments args) {
		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.BRPOPLPUSH,
					(cmd)->cmd.brpoplpush(timeout, key, destKey), converter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.BRPOPLPUSH,
					(cmd)->cmd.brpoplpush(timeout, key, destKey), converter)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, Command.BRPOPLPUSH,
					(cmd)->cmd.brpoplpush(timeout, key, destKey), converter)
					.run(args);
		}
	}

	private <V> V lPop(final byte[] key, final Converter<byte[], V> converter, final CommandArguments args) {
		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.LPOP, (cmd)->cmd.lpop(key), converter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.LPOP, (cmd)->cmd.lpop(key), converter)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, Command.LPOP, (cmd)->cmd.lpop(key), converter)
					.run(args);
		}
	}

	private <V> V rPop(final byte[] key, final Converter<byte[], V> converter, final CommandArguments args) {
		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.RPOP, (cmd)->cmd.rpop(key), converter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.RPOP, (cmd)->cmd.rpop(key), converter)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, Command.RPOP, (cmd)->cmd.rpop(key), converter)
					.run(args);
		}
	}

	private <V> V rPoplPush(final byte[] key, final byte[] destKey, final Converter<byte[], V> converter,
							final CommandArguments args) {
		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, Command.RPOPLPUSH,
					(cmd)->cmd.rpoplpush(key, destKey), converter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, Command.RPOPLPUSH,
					(cmd)->cmd.rpoplpush(key, destKey), converter)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, Command.RPOPLPUSH, (cmd)->cmd.rpoplpush(key, destKey),
					converter)
					.run(args);
		}
	}

}
