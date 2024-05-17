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

import com.buession.core.collect.Maps;
import com.buession.core.converter.ListConverter;
import com.buession.lang.Status;
import com.buession.redis.client.lettuce.LettuceRedisClient;
import com.buession.redis.client.operations.StreamOperations;
import com.buession.redis.core.Stream;
import com.buession.redis.core.StreamConsumer;
import com.buession.redis.core.StreamEntry;
import com.buession.redis.core.StreamEntryId;
import com.buession.redis.core.StreamFull;
import com.buession.redis.core.StreamGroup;
import com.buession.redis.core.StreamPending;
import com.buession.redis.core.StreamPendingSummary;
import com.buession.redis.core.internal.convert.response.MapConverter;
import com.buession.redis.utils.SafeEncoder;

import java.util.List;
import java.util.Map;

/**
 * Lettuce Stream 命令操作抽象类
 *
 * @param <C>
 * 		Redis Client {@link LettuceRedisClient}
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public abstract class AbstractStreamOperations<C extends LettuceRedisClient> extends AbstractLettuceRedisOperations<C>
		implements StreamOperations {

	public AbstractStreamOperations(final C client) {
		super(client);
	}

	@Override
	public Long xAck(final String key, final String groupName, final StreamEntryId... ids) {
		return xAck(SafeEncoder.encode(key), SafeEncoder.encode(groupName), ids);
	}

	@Override
	public StreamEntryId xAdd(final String key, final StreamEntryId id, final Map<String, String> hash) {
		return xAdd(SafeEncoder.encode(key), id, (new MapConverter.StringToBinaryMapConverter()).convert(hash));
	}

	@Override
	public StreamEntryId xAdd(final String key, final StreamEntryId id, final Map<String, String> hash,
							  final XAddArgument xAddArgument) {
		return xAdd(SafeEncoder.encode(key), id, (new MapConverter.StringToBinaryMapConverter()).convert(hash),
				xAddArgument);
	}

	@Override
	public List<StreamEntry> xClaim(final String key, final String groupName, final String consumerName,
									final int minIdleTime, final StreamEntryId... ids) {
		return xClaim(SafeEncoder.encode(key), SafeEncoder.encode(groupName), SafeEncoder.encode(consumerName),
				minIdleTime, ids);
	}

	@Override
	public List<StreamEntry> xClaim(final String key, final String groupName, final String consumerName,
									final int minIdleTime, final StreamEntryId[] ids,
									final XClaimArgument xClaimArgument) {
		return xClaim(SafeEncoder.encode(key), SafeEncoder.encode(groupName), SafeEncoder.encode(consumerName),
				minIdleTime, ids, xClaimArgument);
	}

	@Override
	public List<StreamEntryId> xClaimJustId(final byte[] key, final byte[] groupName, final byte[] consumerName,
											final int minIdleTime, final StreamEntryId[] ids,
											final XClaimArgument xClaimArgument) {
		return xClaimJustId(SafeEncoder.encode(key), SafeEncoder.encode(groupName), SafeEncoder.encode(consumerName),
				minIdleTime, ids, xClaimArgument);
	}

	@Override
	public Long xDel(final byte[] key, final StreamEntryId... ids) {
		return xDel(SafeEncoder.encode(key), ids);
	}

	@Override
	public Status xGroupCreate(final byte[] key, final byte[] groupName, final StreamEntryId id,
							   final boolean makeStream) {
		return xGroupCreate(SafeEncoder.encode(key), SafeEncoder.encode(groupName), id, makeStream);
	}

	@Override
	public Status xGroupSetId(final byte[] key, final byte[] groupName, final StreamEntryId id) {
		return xGroupSetId(SafeEncoder.encode(key), SafeEncoder.encode(groupName), id);
	}

	@Override
	public List<StreamConsumer> xInfoConsumers(final byte[] key, final byte[] groupName) {
		return xInfoConsumers(SafeEncoder.encode(key), SafeEncoder.encode(groupName));
	}

	@Override
	public List<StreamGroup> xInfoGroups(final byte[] key) {
		return xInfoGroups(SafeEncoder.encode(key));
	}

	@Override
	public Stream xInfoStream(final byte[] key) {
		return xInfoStream(SafeEncoder.encode(key));
	}

	@Override
	public StreamFull xInfoStream(final byte[] key, final boolean full) {
		return xInfoStream(SafeEncoder.encode(key), full);
	}

	@Override
	public StreamFull xInfoStream(final byte[] key, final boolean full, final long count) {
		return xInfoStream(SafeEncoder.encode(key), full, count);
	}

	@Override
	public StreamPendingSummary xPending(final byte[] key, final byte[] groupName) {
		return xPending(SafeEncoder.encode(key), SafeEncoder.encode(groupName));
	}

	@Override
	public List<StreamPending> xPending(final byte[] key, final byte[] groupName, final long minIdleTime) {
		return xPending(SafeEncoder.encode(key), SafeEncoder.encode(groupName), minIdleTime);
	}

	@Override
	public List<StreamPending> xPending(final byte[] key, final byte[] groupName, final StreamEntryId start,
										final StreamEntryId end, final long count) {
		return xPending(SafeEncoder.encode(key), SafeEncoder.encode(groupName), start, end, count);
	}

	@Override
	public List<StreamPending> xPending(final byte[] key, final byte[] groupName, final byte[] consumerName) {
		return xPending(SafeEncoder.encode(key), SafeEncoder.encode(groupName), SafeEncoder.encode(consumerName));
	}

	@Override
	public List<StreamPending> xPending(final byte[] key, final byte[] groupName, final long minIdleTime,
										final StreamEntryId start, final StreamEntryId end, final long count) {
		return xPending(SafeEncoder.encode(key), SafeEncoder.encode(groupName), minIdleTime, start, end, count);
	}

	@Override
	public List<StreamPending> xPending(final byte[] key, final byte[] groupName, final long minIdleTime,
										final byte[] consumerName) {
		return xPending(SafeEncoder.encode(key), SafeEncoder.encode(groupName), minIdleTime,
				SafeEncoder.encode(consumerName));
	}

	@Override
	public List<StreamPending> xPending(final byte[] key, final byte[] groupName, final StreamEntryId start,
										final StreamEntryId end, final long count, final byte[] consumerName) {
		return xPending(SafeEncoder.encode(key), SafeEncoder.encode(groupName), start, end, count,
				SafeEncoder.encode(consumerName));
	}

	@Override
	public List<StreamPending> xPending(final byte[] key, final byte[] groupName, final long minIdleTime,
										final StreamEntryId start, final StreamEntryId end, final long count,
										final byte[] consumerName) {
		return xPending(SafeEncoder.encode(key), SafeEncoder.encode(groupName), minIdleTime, start, end, count,
				SafeEncoder.encode(consumerName));
	}

	@Override
	public List<StreamEntry> xRange(final byte[] key, final StreamEntryId start, final StreamEntryId end) {
		return xRange(SafeEncoder.encode(key), start, end);
	}

	@Override
	public List<StreamEntry> xRange(final byte[] key, final StreamEntryId start, final StreamEntryId end,
									final long count) {
		return xRange(SafeEncoder.encode(key), start, end, count);
	}

	@Override
	public List<Map<byte[], List<StreamEntry>>> xReadGroup(final byte[] groupName, final byte[] consumerName,
														   final Map<byte[], StreamEntryId> streams) {
		if(streams == null){
			return afterBianryXReadGroup(
					xReadGroup(SafeEncoder.encode(groupName), SafeEncoder.encode(consumerName), null));
		}else{
			final Map<String, StreamEntryId> xStreams = Maps.map(streams, SafeEncoder::encode, (id)->id);
			return afterBianryXReadGroup(
					xReadGroup(SafeEncoder.encode(groupName), SafeEncoder.encode(consumerName), xStreams));
		}
	}

	@Override
	public List<Map<byte[], List<StreamEntry>>> xReadGroup(final byte[] groupName, final byte[] consumerName,
														   final long count, final Map<byte[], StreamEntryId> streams) {
		if(streams == null){
			return afterBianryXReadGroup(
					xReadGroup(SafeEncoder.encode(groupName), SafeEncoder.encode(consumerName), count, null));
		}else{
			final Map<String, StreamEntryId> xStreams = Maps.map(streams, SafeEncoder::encode, (id)->id);
			return afterBianryXReadGroup(
					xReadGroup(SafeEncoder.encode(groupName), SafeEncoder.encode(consumerName), count, xStreams));
		}
	}

	@Override
	public List<Map<byte[], List<StreamEntry>>> xReadGroup(final byte[] groupName, final byte[] consumerName,
														   final int block, final Map<byte[], StreamEntryId> streams) {
		if(streams == null){
			return afterBianryXReadGroup(
					xReadGroup(SafeEncoder.encode(groupName), SafeEncoder.encode(consumerName), block, null));
		}else{
			final Map<String, StreamEntryId> xStreams = Maps.map(streams, SafeEncoder::encode, (id)->id);
			return afterBianryXReadGroup(
					xReadGroup(SafeEncoder.encode(groupName), SafeEncoder.encode(consumerName), block, xStreams));
		}
	}

	@Override
	public List<Map<byte[], List<StreamEntry>>> xReadGroup(final byte[] groupName, final byte[] consumerName,
														   final boolean isNoAck,
														   final Map<byte[], StreamEntryId> streams) {
		if(streams == null){
			return afterBianryXReadGroup(
					xReadGroup(SafeEncoder.encode(groupName), SafeEncoder.encode(consumerName), isNoAck, null));
		}else{
			final Map<String, StreamEntryId> xStreams = Maps.map(streams, SafeEncoder::encode, (id)->id);
			return afterBianryXReadGroup(
					xReadGroup(SafeEncoder.encode(groupName), SafeEncoder.encode(consumerName), isNoAck, xStreams));
		}
	}

	@Override
	public List<Map<byte[], List<StreamEntry>>> xReadGroup(final byte[] groupName, final byte[] consumerName,
														   final long count, final int block,
														   final Map<byte[], StreamEntryId> streams) {
		if(streams == null){
			return afterBianryXReadGroup(
					xReadGroup(SafeEncoder.encode(groupName), SafeEncoder.encode(consumerName), count, block, null));
		}else{
			final Map<String, StreamEntryId> xStreams = Maps.map(streams, SafeEncoder::encode, (id)->id);
			return afterBianryXReadGroup(
					xReadGroup(SafeEncoder.encode(groupName), SafeEncoder.encode(consumerName), count, block,
							xStreams));
		}
	}

	@Override
	public List<Map<byte[], List<StreamEntry>>> xReadGroup(final byte[] groupName, final byte[] consumerName,
														   final long count, final boolean isNoAck,
														   final Map<byte[], StreamEntryId> streams) {
		if(streams == null){
			return afterBianryXReadGroup(
					xReadGroup(SafeEncoder.encode(groupName), SafeEncoder.encode(consumerName), count, isNoAck, null));
		}else{
			final Map<String, StreamEntryId> xStreams = Maps.map(streams, SafeEncoder::encode, (id)->id);
			return afterBianryXReadGroup(
					xReadGroup(SafeEncoder.encode(groupName), SafeEncoder.encode(consumerName), count, isNoAck,
							xStreams));
		}
	}

	@Override
	public List<Map<byte[], List<StreamEntry>>> xReadGroup(final byte[] groupName, final byte[] consumerName,
														   final int block, final boolean isNoAck,
														   final Map<byte[], StreamEntryId> streams) {
		if(streams == null){
			return afterBianryXReadGroup(
					xReadGroup(SafeEncoder.encode(groupName), SafeEncoder.encode(consumerName), block, isNoAck, null));
		}else{
			final Map<String, StreamEntryId> xStreams = Maps.map(streams, SafeEncoder::encode, (id)->id);
			return afterBianryXReadGroup(
					xReadGroup(SafeEncoder.encode(groupName), SafeEncoder.encode(consumerName), block, block,
							xStreams));
		}
	}

	@Override
	public List<Map<byte[], List<StreamEntry>>> xReadGroup(final byte[] groupName, final byte[] consumerName,
														   final long count, final int block, final boolean isNoAck,
														   final Map<byte[], StreamEntryId> streams) {
		if(streams == null){
			return afterBianryXReadGroup(
					xReadGroup(SafeEncoder.encode(groupName), SafeEncoder.encode(consumerName), count, block, isNoAck,
							null));
		}else{
			final Map<String, StreamEntryId> xStreams = Maps.map(streams, SafeEncoder::encode, (id)->id);
			return afterBianryXReadGroup(
					xReadGroup(SafeEncoder.encode(groupName), SafeEncoder.encode(consumerName), count, block, isNoAck,
							xStreams));
		}
	}

	@Override
	public List<StreamEntry> xRevRange(final byte[] key, final StreamEntryId end, final StreamEntryId start) {
		return xRevRange(SafeEncoder.encode(key), end, start);
	}

	@Override
	public List<StreamEntry> xRevRange(final byte[] key, final StreamEntryId end, final StreamEntryId start,
									   final long count) {
		return xRevRange(SafeEncoder.encode(key), end, start, count);
	}

	@Override
	public Long xTrim(final String key, final XTrimArgument xTrimArgument) {
		return xTrim(SafeEncoder.encode(key), xTrimArgument);
	}

	@Override
	public Long xTrim(final String key, final XTrimArgument xTrimArgument, final long limit) {
		return xTrim(SafeEncoder.encode(key), xTrimArgument, limit);
	}

	protected static List<Map<byte[], List<StreamEntry>>> afterBianryXReadGroup(
			final List<Map<String, List<StreamEntry>>> data) {
		if(data == null){
			return null;
		}else{
			final ListConverter<Map<String, List<StreamEntry>>, Map<byte[], List<StreamEntry>>> converter =
					new ListConverter<>(new MapConverter.StringToBinaryKeyPrimitiveValueMapConverter<>());
			return converter.convert(data);
		}
	}

}
