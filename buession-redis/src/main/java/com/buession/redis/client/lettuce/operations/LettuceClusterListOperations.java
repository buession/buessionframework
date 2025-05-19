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
import com.buession.lang.Status;
import com.buession.redis.client.lettuce.LettuceClusterClient;
import com.buession.redis.core.Direction;
import com.buession.redis.core.ListPosition;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.internal.lettuce.LettuceLPosArgs;
import com.buession.redis.utils.SafeEncoder;
import io.lettuce.core.KeyValue;
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
		final CommandArguments args = CommandArguments.create(key).add(index);
		final byte[] bKey = SafeEncoder.encode(key);

		return lIndex(bKey, index, SafeEncoder::encode, args);
	}

	@Override
	public byte[] lIndex(final byte[] key, final long index) {
		final CommandArguments args = CommandArguments.create(key).add(index);
		return lIndex(key, index, (v)->v, args);
	}

	@Override
	public Long lInsert(final byte[] key, final ListPosition position, final byte[] pivot, final byte[] value) {
		final CommandArguments args = CommandArguments.create(key).add(position).add(pivot)
				.add(value);
		final boolean before = ListPosition.BEFORE == position;

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, ProtocolCommand.LINSERT,
					(cmd)->cmd.linsert(key, before, pivot, value), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, ProtocolCommand.LINSERT,
					(cmd)->cmd.linsert(key, before, pivot, value), (v)->v)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, ProtocolCommand.LINSERT,
					(cmd)->cmd.linsert(key, before, pivot, value), (v)->v)
					.run(args);
		}
	}

	@Override
	public Status lSet(final byte[] key, final long index, final byte[] value) {
		final CommandArguments args = CommandArguments.create(key).add(index).add(value);

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, ProtocolCommand.LSET, (cmd)->cmd.lset(key, index, value),
					okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, ProtocolCommand.LSET,
					(cmd)->cmd.lset(key, index, value), okStatusConverter)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, ProtocolCommand.LSET, (cmd)->cmd.lset(key, index, value),
					okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Long lLen(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, ProtocolCommand.LLEN, (cmd)->cmd.llen(key), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, ProtocolCommand.LLEN, (cmd)->cmd.llen(key), (v)->v)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, ProtocolCommand.LLEN, (cmd)->cmd.llen(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public List<String> lRange(final String key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end);
		final byte[] bKey = SafeEncoder.encode(key);

		return lRange(bKey, start, end, binaryToStringListConverter, args);
	}

	@Override
	public List<byte[]> lRange(final byte[] key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end);
		return lRange(key, start, end, (v)->v, args);
	}

	@Override
	public Long lPos(final byte[] key, final byte[] element) {
		final CommandArguments args = CommandArguments.create(key);

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, ProtocolCommand.LPOS, (cmd)->cmd.lpos(key, element),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, ProtocolCommand.LPOS, (cmd)->cmd.lpos(key, element),
					(v)->v)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, ProtocolCommand.LPOS, (cmd)->cmd.lpos(key, element), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long lPos(final byte[] key, final byte[] element, final LPosArgument lPosArgument) {
		final CommandArguments args = CommandArguments.create(key).add(lPosArgument);
		final LPosArgs lPosArgs = LettuceLPosArgs.from(lPosArgument);

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, ProtocolCommand.LPOS,
					(cmd)->cmd.lpos(key, element, lPosArgs), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, ProtocolCommand.LPOS,
					(cmd)->cmd.lpos(key, element, lPosArgs), (v)->v)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, ProtocolCommand.LPOS, (cmd)->cmd.lpos(key, element, lPosArgs),
					(v)->v)
					.run(args);
		}
	}

	@Override
	public List<Long> lPos(final byte[] key, final byte[] element, final LPosArgument lPosArgument, final long count) {
		final CommandArguments args = CommandArguments.create(key).add(lPosArgument)
				.add(count);
		final LPosArgs lPosArgs = LettuceLPosArgs.from(lPosArgument);

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, ProtocolCommand.LPOS,
					(cmd)->cmd.lpos(key, element, (int) count, lPosArgs), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, ProtocolCommand.LPOS,
					(cmd)->cmd.lpos(key, element, (int) count, lPosArgs), (v)->v)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, ProtocolCommand.LPOS,
					(cmd)->cmd.lpos(key, element, (int) count, lPosArgs), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long lRem(final byte[] key, final byte[] value, final long count) {
		final CommandArguments args = CommandArguments.create(key).add(value).add(count);

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, ProtocolCommand.LREM, (cmd)->cmd.lrem(key, count, value),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, ProtocolCommand.LREM,
					(cmd)->cmd.lrem(key, count, value), (v)->v)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, ProtocolCommand.LREM, (cmd)->cmd.lrem(key, count, value), (v)->v)
					.run(args);
		}
	}

	@Override
	public Status lTrim(final byte[] key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create(key).add(start).add(end);

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, ProtocolCommand.LTRIM, (cmd)->cmd.ltrim(key, start, end),
					okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, ProtocolCommand.LTRIM,
					(cmd)->cmd.ltrim(key, start, end), okStatusConverter)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, ProtocolCommand.LTRIM, (cmd)->cmd.ltrim(key, start, end),
					okStatusConverter)
					.run(args);
		}
	}

	@Override
	public String lMove(final String key, final String destKey, final Direction from, final Direction to) {
		final CommandArguments args = CommandArguments.create(key).add(destKey).add(from)
				.add(to);
		return lMove(args);
	}

	@Override
	public byte[] lMove(final byte[] key, final byte[] destKey, final Direction from, final Direction to) {
		final CommandArguments args = CommandArguments.create(key).add(destKey).add(from)
				.add(to);
		return lMove(args);
	}

	@Override
	public String blMove(final String key, final String destKey, final Direction from, final Direction to,
						 final int timeout) {
		final CommandArguments args = CommandArguments.create(key).add(destKey).add(from)
				.add(to);
		return blMove(args);
	}

	@Override
	public byte[] blMove(final byte[] key, final byte[] destKey, final Direction from, final Direction to,
						 final int timeout) {
		final CommandArguments args = CommandArguments.create(key).add(destKey).add(from)
				.add(to).add(timeout);
		return blMove(args);
	}

	@Override
	public List<String> blPop(final String[] keys, final int timeout) {
		final CommandArguments args = CommandArguments.create(keys).add(timeout);
		final byte[][] bKeys = SafeEncoder.encode(keys);
		final Converter<KeyValue<byte[], byte[]>, List<String>> converter =
				(v)->ListBuilder.of(SafeEncoder.encode(v.getValue()));

		return blPop(bKeys, timeout, converter, args);
	}

	@Override
	public List<byte[]> blPop(final byte[][] keys, final int timeout) {
		final CommandArguments args = CommandArguments.create(keys).add(timeout);
		final Converter<KeyValue<byte[], byte[]>, List<byte[]>> converter = (v)->ListBuilder.of(v.getValue());

		return blPop(keys, timeout, converter, args);
	}

	@Override
	public List<String> brPop(final String[] keys, final int timeout) {
		final CommandArguments args = CommandArguments.create(keys).add(timeout);
		final byte[][] bKeys = SafeEncoder.encode(keys);
		final Converter<KeyValue<byte[], byte[]>, List<String>> converter =
				(v)->ListBuilder.of(SafeEncoder.encode(v.getValue()));

		return brPop(bKeys, timeout, converter, args);
	}

	@Override
	public List<byte[]> brPop(final byte[][] keys, final int timeout) {
		final CommandArguments args = CommandArguments.create(keys).add(timeout);
		final Converter<KeyValue<byte[], byte[]>, List<byte[]>> converter = (v)->ListBuilder.of(v.getValue());

		return brPop(keys, timeout, converter, args);
	}

	@Override
	public String brPoplPush(final String key, final String destKey, final int timeout) {
		final CommandArguments args = CommandArguments.create(key).add(destKey)
				.add(timeout);
		final byte[] bKey = SafeEncoder.encode(key);
		final byte[] bDestKey = SafeEncoder.encode(destKey);

		return brPoplPush(bKey, bDestKey, timeout, SafeEncoder::encode, args);
	}

	@Override
	public byte[] brPoplPush(final byte[] key, final byte[] destKey, final int timeout) {
		final CommandArguments args = CommandArguments.create(key).add(destKey)
				.add(timeout);
		return brPoplPush(key, destKey, timeout, (v)->v, args);
	}

	@Override
	public String lPop(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		final byte[] bKey = SafeEncoder.encode(key);

		return lPop(bKey, SafeEncoder::encode, args);
	}

	@Override
	public byte[] lPop(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return lPop(key, (v)->v, args);
	}

	@Override
	public Long lPush(final byte[] key, final byte[]... values) {
		final CommandArguments args = CommandArguments.create(key).add(values);

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, ProtocolCommand.LPUSH, (cmd)->cmd.lpush(key, values),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, ProtocolCommand.LPUSH, (cmd)->cmd.lpush(key, values),
					(v)->v)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, ProtocolCommand.LPUSH, (cmd)->cmd.lpush(key, values), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long lPushX(final byte[] key, final byte[]... values) {
		final CommandArguments args = CommandArguments.create(key).add(values);

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, ProtocolCommand.LPUSHX, (cmd)->cmd.lpushx(key, values),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, ProtocolCommand.LPUSHX,
					(cmd)->cmd.lpushx(key, values), (v)->v)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, ProtocolCommand.LPUSHX, (cmd)->cmd.lpushx(key, values), (v)->v)
					.run(args);
		}
	}

	@Override
	public String rPop(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		final byte[] bKey = SafeEncoder.encode(key);

		return rPop(bKey, SafeEncoder::encode, args);
	}

	@Override
	public byte[] rPop(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return rPop(key, (v)->v, args);
	}

	@Override
	public String rPoplPush(final String key, final String destKey) {
		final CommandArguments args = CommandArguments.create(key).add(destKey);
		final byte[] bKey = SafeEncoder.encode(key);
		final byte[] bDestKey = SafeEncoder.encode(destKey);

		return rPoplPush(bKey, bDestKey, SafeEncoder::encode, args);
	}

	@Override
	public byte[] rPoplPush(final byte[] key, final byte[] destKey) {
		final CommandArguments args = CommandArguments.create(key).add(destKey);
		return rPoplPush(key, destKey, (v)->v, args);
	}

	@Override
	public Long rPush(final byte[] key, final byte[]... values) {
		final CommandArguments args = CommandArguments.create(key).add(values);

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, ProtocolCommand.RPUSH, (cmd)->cmd.rpush(key, values),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, ProtocolCommand.RPUSH, (cmd)->cmd.rpush(key, values),
					(v)->v)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, ProtocolCommand.RPUSH, (cmd)->cmd.rpush(key, values), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long rPushX(final byte[] key, final byte[]... values) {
		final CommandArguments args = CommandArguments.create(key).add(values);

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, ProtocolCommand.RPUSHX, (cmd)->cmd.rpushx(key, values),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, ProtocolCommand.RPUSHX,
					(cmd)->cmd.rpushx(key, values), (v)->v)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, ProtocolCommand.RPUSHX, (cmd)->cmd.rpushx(key, values), (v)->v)
					.run(args);
		}
	}

	private <V> V lIndex(final byte[] key, final long index, final Converter<byte[], V> converter,
						 final CommandArguments args) {
		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, ProtocolCommand.LINDEX, (cmd)->cmd.lindex(key, index),
					converter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, ProtocolCommand.LINDEX, (cmd)->cmd.lindex(key, index),
					converter)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, ProtocolCommand.LINDEX, (cmd)->cmd.lindex(key, index), converter)
					.run(args);
		}
	}

	private <V> List<V> lRange(final byte[] key, final long start, final long end,
							   final Converter<List<byte[]>, List<V>> converter, final CommandArguments args) {
		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, ProtocolCommand.LRANGE,
					(cmd)->cmd.lrange(key, start, end), converter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, ProtocolCommand.LRANGE,
					(cmd)->cmd.lrange(key, start, end), converter)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, ProtocolCommand.LRANGE, (cmd)->cmd.lrange(key, start, end),
					converter)
					.run(args);
		}
	}

	private <V> V lMove(final CommandArguments args) {
		if(isPipeline()){
			return new LettuceClusterPipelineCommand<V, V>(client, ProtocolCommand.LMOVE)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<V, V>(client, ProtocolCommand.LMOVE)
					.run(args);
		}else{
			return new LettuceClusterCommand<V, V>(client, ProtocolCommand.LMOVE)
					.run(args);
		}
	}

	private <V> V blMove(final CommandArguments args) {
		if(isPipeline()){
			return new LettuceClusterPipelineCommand<V, V>(client, ProtocolCommand.BLMOVE)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<V, V>(client, ProtocolCommand.BLMOVE)
					.run(args);
		}else{
			return new LettuceClusterCommand<V, V>(client, ProtocolCommand.BLMOVE)
					.run(args);
		}
	}

	private <V> List<V> blPop(final byte[][] keys, final int timeout, final Converter<KeyValue<byte[], byte[]>,
			List<V>> converter, final CommandArguments args) {
		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, ProtocolCommand.BLPOP, (cmd)->cmd.blpop(timeout, keys),
					converter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, ProtocolCommand.BLPOP,
					(cmd)->cmd.blpop(timeout, keys), converter)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, ProtocolCommand.BLPOP, (cmd)->cmd.blpop(timeout, keys),
					converter)
					.run(args);
		}
	}

	private <V> List<V> brPop(final byte[][] keys, final int timeout, final Converter<KeyValue<byte[], byte[]>,
			List<V>> converter, final CommandArguments args) {
		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, ProtocolCommand.BRPOP, (cmd)->cmd.brpop(timeout, keys),
					converter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, ProtocolCommand.BRPOP,
					(cmd)->cmd.brpop(timeout, keys), converter)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, ProtocolCommand.BRPOP, (cmd)->cmd.brpop(timeout, keys),
					converter)
					.run(args);
		}
	}

	private <V> V brPoplPush(final byte[] key, final byte[] destKey, final int timeout,
							 final Converter<byte[], V> converter, final CommandArguments args) {
		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, ProtocolCommand.BRPOPLPUSH,
					(cmd)->cmd.brpoplpush(timeout, key, destKey), converter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, ProtocolCommand.BRPOPLPUSH,
					(cmd)->cmd.brpoplpush(timeout, key, destKey), converter)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, ProtocolCommand.BRPOPLPUSH,
					(cmd)->cmd.brpoplpush(timeout, key, destKey), converter)
					.run(args);
		}
	}

	private <V> V lPop(final byte[] key, final Converter<byte[], V> converter, final CommandArguments args) {
		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, ProtocolCommand.LPOP, (cmd)->cmd.lpop(key), converter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, ProtocolCommand.LPOP, (cmd)->cmd.lpop(key), converter)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, ProtocolCommand.LPOP, (cmd)->cmd.lpop(key), converter)
					.run(args);
		}
	}

	private <V> V rPop(final byte[] key, final Converter<byte[], V> converter, final CommandArguments args) {
		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, ProtocolCommand.RPOP, (cmd)->cmd.rpop(key), converter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, ProtocolCommand.RPOP, (cmd)->cmd.rpop(key), converter)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, ProtocolCommand.RPOP, (cmd)->cmd.rpop(key), converter)
					.run(args);
		}
	}

	private <V> V rPoplPush(final byte[] key, final byte[] destKey, final Converter<byte[], V> converter,
							final CommandArguments args) {
		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, ProtocolCommand.RPOPLPUSH,
					(cmd)->cmd.rpoplpush(key, destKey), converter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, ProtocolCommand.RPOPLPUSH,
					(cmd)->cmd.rpoplpush(key, destKey), converter)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, ProtocolCommand.RPOPLPUSH, (cmd)->cmd.rpoplpush(key, destKey),
					converter)
					.run(args);
		}
	}

}
