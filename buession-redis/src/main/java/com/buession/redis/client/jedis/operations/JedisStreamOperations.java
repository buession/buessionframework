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

import com.buession.redis.client.connection.jedis.JedisConnection;
import com.buession.redis.client.jedis.JedisStandaloneClient;
import com.buession.redis.core.StreamEntry;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.internal.convert.jedis.JedisConverters;
import com.buession.redis.utils.SafeEncoder;
import redis.clients.jedis.BuilderFactory;
import redis.clients.jedis.StreamEntryID;
import redis.clients.jedis.params.XAddParams;

import java.util.Map;

/**
 * Jedis 单机模式 Stream 命令操作抽象类
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public final class JedisStreamOperations extends AbstractStreamOperations<JedisConnection> {

	public JedisStreamOperations(final JedisStandaloneClient client){
		super(client);
	}

	@Override
	public long xAck(final String key, final String group, final String... ids){
		final CommandArguments args = CommandArguments.create("key", key).put("group", group).put("ids", ids);
		final StreamEntryID[] streamEntryIDS = ids == null ? null : new StreamEntryID[ids.length];

		if(ids != null){
			for(int i = 0; i < ids.length; i++){
				streamEntryIDS[i] = new StreamEntryID(ids[i]);
			}
		}

		final JedisCommand<Long> command = JedisCommand.<Long>create(ProtocolCommand.XACK)
				.general((cmd)->cmd.xack(key, group, streamEntryIDS))
				.pipeline((cmd)->cmd.xack(key, group, streamEntryIDS))
				.transaction((cmd)->cmd.xack(key, group, streamEntryIDS));
		return execute(command, args);
	}

	@Override
	public long xAck(byte[] key, byte[] group, byte[]... ids){
		final CommandArguments args = CommandArguments.create("key", key).put("group", group).put("ids", ids);
		final JedisCommand<Long> command = JedisCommand.<Long>create(ProtocolCommand.XACK)
				.general((cmd)->cmd.xack(key, group, ids))
				.pipeline((cmd)->cmd.xack(key, group, ids))
				.transaction((cmd)->cmd.xack(key, group, ids));
		return execute(command, args);
	}

	@Override
	public StreamEntry xAdd(final String key, final String id, final Map<String, String> hash){
		final CommandArguments args = CommandArguments.create("key", key).put("id", id).put("hash", hash);
		final JedisCommand<StreamEntry> command = JedisCommand.<StreamEntry>create(ProtocolCommand.XADD)
				.general((cmd)->cmd.xadd(key, new StreamEntryID(id), hash),
						JedisConverters.STREAM_ENTRY_RESULT_CONVERTER)
				.pipeline((cmd)->cmd.xadd(key, new StreamEntryID(id), hash),
						JedisConverters.STREAM_ENTRY_RESULT_CONVERTER)
				.transaction((cmd)->cmd.xadd(key, new StreamEntryID(id), hash),
						JedisConverters.STREAM_ENTRY_RESULT_CONVERTER);
		return execute(command, args);
	}

	@Override
	public StreamEntry xAdd(final byte[] key, final byte[] id, final Map<byte[], byte[]> hash){
		return xAdd(SafeEncoder.encode(key), SafeEncoder.encode(id),
				JedisConverters.BINARY_MAP_TO_STRING_MAP_CONVERTER.convert(hash));
	}

	@Override
	public StreamEntry xAdd(final String key, final String id, final Map<String, String> hash,
							final XAddArgument xAddArgument){
		final CommandArguments args = CommandArguments.create("key", key).put("id", id).put("hash", hash)
				.put("xAddArgument", xAddArgument);
		final XAddParams params = JedisConverters.X_ADD_ARGUMENT_CONVERTER.convert(xAddArgument).id(id);
		final JedisCommand<StreamEntry> command = JedisCommand.<StreamEntry>create(ProtocolCommand.XADD)
				.general((cmd)->cmd.xadd(key, hash, params), JedisConverters.STREAM_ENTRY_RESULT_CONVERTER)
				.pipeline((cmd)->cmd.xadd(key, hash, params), JedisConverters.STREAM_ENTRY_RESULT_CONVERTER)
				.transaction((cmd)->cmd.xadd(key, hash, params), JedisConverters.STREAM_ENTRY_RESULT_CONVERTER);
		return execute(command, args);
	}

	@Override
	public StreamEntry xAdd(final byte[] key, final byte[] id, final Map<byte[], byte[]> hash,
							final XAddArgument xAddArgument){
		final CommandArguments args = CommandArguments.create("key", key).put("id", id).put("hash", hash)
				.put("xAddArgument", xAddArgument);
		final XAddParams params = JedisConverters.X_ADD_ARGUMENT_CONVERTER.convert(xAddArgument).id(id);
		final JedisCommand<StreamEntry> command = JedisCommand.<StreamEntry>create(ProtocolCommand.XADD)
				.general((cmd)->cmd.xadd(key, hash, params), JedisConverters.BINARY_STREAM_ENTRY_RESULT_CONVERTER)
				.pipeline((cmd)->cmd.xadd(key, hash, params), JedisConverters.BINARY_STREAM_ENTRY_RESULT_CONVERTER)
				.transaction((cmd)->cmd.xadd(key, hash, params), JedisConverters.BINARY_STREAM_ENTRY_RESULT_CONVERTER);
		return execute(command, args);
	}
}
