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
import com.buession.redis.client.connection.jedis.JedisClusterConnection;
import com.buession.redis.client.jedis.JedisClusterClient;
import com.buession.redis.core.Direction;
import com.buession.redis.core.ListPosition;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.internal.convert.jedis.params.DirectionConverter;
import com.buession.redis.core.internal.convert.jedis.params.LPosArgumentConverter;
import com.buession.redis.core.internal.convert.jedis.params.ListPositionConverter;
import com.buession.redis.core.internal.convert.jedis.response.OkStatusConverter;
import redis.clients.jedis.args.ListDirection;
import redis.clients.jedis.params.LPosParams;

import java.util.List;

/**
 * Jedis 集群模式列表命令操作
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public final class JedisClusterListOperations extends AbstractListOperations<JedisClusterConnection> {

	public JedisClusterListOperations(final JedisClusterClient client){
		super(client);
	}

	@Override
	public String lIndex(final String key, final long index){
		final CommandArguments args = CommandArguments.create("key", key).put("index", index);
		final JedisClusterCommand<String> command = JedisClusterCommand.<String>create(ProtocolCommand.LINDEX)
				.general((cmd)->cmd.lindex(key, index)).pipeline((cmd)->cmd.lindex(key, index))
				.transaction((cmd)->cmd.lindex(key, index));
		return execute(command, args);
	}

	@Override
	public byte[] lIndex(final byte[] key, final long index){
		final CommandArguments args = CommandArguments.create("key", key).put("index", index);
		final JedisClusterCommand<byte[]> command = JedisClusterCommand.<byte[]>create(ProtocolCommand.LINDEX)
				.general((cmd)->cmd.lindex(key, index)).pipeline((cmd)->cmd.lindex(key, index))
				.transaction((cmd)->cmd.lindex(key, index));
		return execute(command, args);
	}

	@Override
	public long lInsert(final String key, final ListPosition position, final String pivot, final String value){
		final CommandArguments args = CommandArguments.create("key", key).put("position", position).put("pivot", pivot)
				.put("value", value);
		final redis.clients.jedis.args.ListPosition pos = ListPositionConverter.INSTANCE.convert(position);
		final JedisClusterCommand<Long> command = JedisClusterCommand.<Long>create(ProtocolCommand.LINSERT)
				.general((cmd)->cmd.linsert(key, pos, pivot, value))
				.pipeline((cmd)->cmd.linsert(key, pos, pivot, value))
				.transaction((cmd)->cmd.linsert(key, pos, pivot, value));
		return execute(command, args);
	}

	@Override
	public long lInsert(final byte[] key, final ListPosition position, final byte[] pivot, final byte[] value){
		final CommandArguments args = CommandArguments.create("key", key).put("position", position).put("pivot", pivot)
				.put("value", value);
		final redis.clients.jedis.args.ListPosition pos = ListPositionConverter.INSTANCE.convert(position);
		final JedisClusterCommand<Long> command = JedisClusterCommand.<Long>create(ProtocolCommand.LINSERT)
				.general((cmd)->cmd.linsert(key, pos, pivot, value))
				.pipeline((cmd)->cmd.linsert(key, pos, pivot, value))
				.transaction((cmd)->cmd.linsert(key, pos, pivot, value));
		return execute(command, args);
	}

	@Override
	public Status lSet(final String key, final long index, final String value){
		final CommandArguments args = CommandArguments.create("key", key).put("index", index).put("value", value);
		final JedisClusterCommand<Status> command = JedisClusterCommand.<Status>create(ProtocolCommand.LINSERT)
				.general((cmd)->cmd.lset(key, index, value), OkStatusConverter.INSTANCE)
				.pipeline((cmd)->cmd.lset(key, index, value), OkStatusConverter.INSTANCE)
				.transaction((cmd)->cmd.lset(key, index, value), OkStatusConverter.INSTANCE);
		return execute(command, args);
	}

	@Override
	public Status lSet(final byte[] key, final long index, final byte[] value){
		final CommandArguments args = CommandArguments.create("key", key).put("index", index).put("value", value);
		final JedisClusterCommand<Status> command = JedisClusterCommand.<Status>create(ProtocolCommand.LINSERT)
				.general((cmd)->cmd.lset(key, index, value), OkStatusConverter.INSTANCE)
				.pipeline((cmd)->cmd.lset(key, index, value), OkStatusConverter.INSTANCE)
				.transaction((cmd)->cmd.lset(key, index, value), OkStatusConverter.INSTANCE);
		return execute(command, args);
	}

	@Override
	public long lLen(final String key){
		final CommandArguments args = CommandArguments.create("key", key);
		final JedisClusterCommand<Long> command = JedisClusterCommand.<Long>create(ProtocolCommand.LLEN)
				.general((cmd)->cmd.llen(key)).pipeline((cmd)->cmd.llen(key)).transaction((cmd)->cmd.llen(key));
		return execute(command, args);
	}

	@Override
	public long lLen(final byte[] key){
		final CommandArguments args = CommandArguments.create("key", key);
		final JedisClusterCommand<Long> command = JedisClusterCommand.<Long>create(ProtocolCommand.LLEN)
				.general((cmd)->cmd.llen(key)).pipeline((cmd)->cmd.llen(key)).transaction((cmd)->cmd.llen(key));
		return execute(command, args);
	}

	@Override
	public List<String> lRange(final String key, final long start, final long end){
		final CommandArguments args = CommandArguments.create("key", key).put("start", start).put("end", end);
		final JedisClusterCommand<List<String>> command = JedisClusterCommand.<List<String>>create(
						ProtocolCommand.LRANGE)
				.general((cmd)->cmd.lrange(key, start, end)).pipeline((cmd)->cmd.lrange(key, start, end))
				.transaction((cmd)->cmd.lrange(key, start, end));
		return execute(command, args);
	}

	@Override
	public List<byte[]> lRange(final byte[] key, final long start, final long end){
		final CommandArguments args = CommandArguments.create("key", key).put("start", start).put("end", end);
		final JedisClusterCommand<List<byte[]>> command = JedisClusterCommand.<List<byte[]>>create(
						ProtocolCommand.LRANGE)
				.general((cmd)->cmd.lrange(key, start, end)).pipeline((cmd)->cmd.lrange(key, start, end))
				.transaction((cmd)->cmd.lrange(key, start, end));
		return execute(command, args);
	}

	@Override
	public long lPos(final String key, final String element){
		final CommandArguments args = CommandArguments.create("key", key);
		final JedisClusterCommand<Long> command = JedisClusterCommand.<Long>create(ProtocolCommand.LPOS)
				.general((cmd)->cmd.lpos(key, element)).pipeline((cmd)->cmd.lpos(key, element))
				.transaction((cmd)->cmd.lpos(key, element));
		return execute(command, args);
	}

	@Override
	public long lPos(final byte[] key, final byte[] element){
		final CommandArguments args = CommandArguments.create("key", key);
		final JedisClusterCommand<Long> command = JedisClusterCommand.<Long>create(ProtocolCommand.LPOS)
				.general((cmd)->cmd.lpos(key, element)).pipeline((cmd)->cmd.lpos(key, element))
				.transaction((cmd)->cmd.lpos(key, element));
		return execute(command, args);
	}

	@Override
	public long lPos(final String key, final String element, final LPosArgument lPosArgument){
		final CommandArguments args = CommandArguments.create("key", key).put("lPosArgument", lPosArgument);
		final LPosParams params = LPosArgumentConverter.INSTANCE.convert(lPosArgument);
		final JedisClusterCommand<Long> command = JedisClusterCommand.<Long>create(ProtocolCommand.LPOS)
				.general((cmd)->cmd.lpos(key, element, params)).pipeline((cmd)->cmd.lpos(key, element, params))
				.transaction((cmd)->cmd.lpos(key, element, params));
		return execute(command, args);
	}

	@Override
	public long lPos(final byte[] key, final byte[] element, final LPosArgument lPosArgument){
		final CommandArguments args = CommandArguments.create("key", key).put("lPosArgument", lPosArgument);
		final LPosParams params = LPosArgumentConverter.INSTANCE.convert(lPosArgument);
		final JedisClusterCommand<Long> command = JedisClusterCommand.<Long>create(ProtocolCommand.LPOS)
				.general((cmd)->cmd.lpos(key, element, params)).pipeline((cmd)->cmd.lpos(key, element, params))
				.transaction((cmd)->cmd.lpos(key, element, params));
		return execute(command, args);
	}

	@Override
	public List<Long> lPos(final String key, final String element, final LPosArgument lPosArgument, final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("lPosArgument", lPosArgument)
				.put("count", count);
		final LPosParams params = LPosArgumentConverter.INSTANCE.convert(lPosArgument);
		final JedisClusterCommand<List<Long>> command = JedisClusterCommand.<List<Long>>create(ProtocolCommand.LPOS)
				.general((cmd)->cmd.lpos(key, element, params, count))
				.pipeline((cmd)->cmd.lpos(key, element, params, count))
				.transaction((cmd)->cmd.lpos(key, element, params, count));
		return execute(command, args);
	}

	@Override
	public List<Long> lPos(final byte[] key, final byte[] element, final LPosArgument lPosArgument, final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("lPosArgument", lPosArgument)
				.put("count", count);
		final LPosParams params = LPosArgumentConverter.INSTANCE.convert(lPosArgument);
		final JedisClusterCommand<List<Long>> command = JedisClusterCommand.<List<Long>>create(ProtocolCommand.LPOS)
				.general((cmd)->cmd.lpos(key, element, params, count))
				.pipeline((cmd)->cmd.lpos(key, element, params, count))
				.transaction((cmd)->cmd.lpos(key, element, params, count));
		return execute(command, args);
	}

	@Override
	public long lRem(final String key, final String value, final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("value", value).put("count", count);
		final JedisClusterCommand<Long> command = JedisClusterCommand.<Long>create(ProtocolCommand.LREM)
				.general((cmd)->cmd.lrem(key, count, value)).pipeline((cmd)->cmd.lrem(key, count, value))
				.transaction((cmd)->cmd.lrem(key, count, value));
		return execute(command, args);
	}

	@Override
	public long lRem(final byte[] key, final byte[] value, final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("value", value).put("count", count);
		final JedisClusterCommand<Long> command = JedisClusterCommand.<Long>create(ProtocolCommand.LREM)
				.general((cmd)->cmd.lrem(key, count, value)).pipeline((cmd)->cmd.lrem(key, count, value))
				.transaction((cmd)->cmd.lrem(key, count, value));
		return execute(command, args);
	}

	@Override
	public Status lTrim(final String key, final long start, final long end){
		final CommandArguments args = CommandArguments.create("key", key).put("start", start).put("end", end);
		final JedisClusterCommand<Status> command = JedisClusterCommand.<Status>create(ProtocolCommand.LTRIM)
				.general((cmd)->cmd.ltrim(key, start, end), OkStatusConverter.INSTANCE)
				.pipeline((cmd)->cmd.ltrim(key, start, end), OkStatusConverter.INSTANCE)
				.transaction((cmd)->cmd.ltrim(key, start, end), OkStatusConverter.INSTANCE);
		return execute(command, args);
	}

	@Override
	public Status lTrim(final byte[] key, final long start, final long end){
		final CommandArguments args = CommandArguments.create("key", key).put("start", start).put("end", end);
		final JedisClusterCommand<Status> command = JedisClusterCommand.<Status>create(ProtocolCommand.LTRIM)
				.general((cmd)->cmd.ltrim(key, start, end), OkStatusConverter.INSTANCE)
				.pipeline((cmd)->cmd.ltrim(key, start, end), OkStatusConverter.INSTANCE)
				.transaction((cmd)->cmd.ltrim(key, start, end), OkStatusConverter.INSTANCE);
		return execute(command, args);
	}

	@Override
	public String lMove(final String key, final String destKey, final Direction from, final Direction to){
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey).put("from", from)
				.put("to", to);
		final ListDirection fromDirection = DirectionConverter.INSTANCE.convert(from);
		final ListDirection toDirection = DirectionConverter.INSTANCE.convert(to);
		final JedisClusterCommand<String> command = JedisClusterCommand.<String>create(ProtocolCommand.LMOVE)
				.general((cmd)->cmd.lmove(key, destKey, fromDirection, toDirection))
				.pipeline((cmd)->cmd.lmove(key, destKey, fromDirection, toDirection))
				.transaction((cmd)->cmd.lmove(key, destKey, fromDirection, toDirection));
		return execute(command, args);
	}

	@Override
	public byte[] lMove(final byte[] key, final byte[] destKey, final Direction from, final Direction to){
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey).put("from", from)
				.put("to", to);
		final ListDirection fromDirection = DirectionConverter.INSTANCE.convert(from);
		final ListDirection toDirection = DirectionConverter.INSTANCE.convert(to);
		final JedisClusterCommand<byte[]> command = JedisClusterCommand.<byte[]>create(ProtocolCommand.LMOVE)
				.general((cmd)->cmd.lmove(key, destKey, fromDirection, toDirection))
				.pipeline((cmd)->cmd.lmove(key, destKey, fromDirection, toDirection))
				.transaction((cmd)->cmd.lmove(key, destKey, fromDirection, toDirection));
		return execute(command, args);
	}

	@Override
	public String blMove(final String key, final String destKey, final Direction from, final Direction to,
						 final int timeout){
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey).put("from", from)
				.put("to", to).put("timeout", timeout);
		final ListDirection fromDirection = DirectionConverter.INSTANCE.convert(from);
		final ListDirection toDirection = DirectionConverter.INSTANCE.convert(to);
		final JedisClusterCommand<String> command = JedisClusterCommand.<String>create(ProtocolCommand.BLMOVE)
				.general((cmd)->cmd.blmove(key, destKey, fromDirection, toDirection, timeout))
				.pipeline((cmd)->cmd.blmove(key, destKey, fromDirection, toDirection, timeout))
				.transaction((cmd)->cmd.blmove(key, destKey, fromDirection, toDirection, timeout));
		return execute(command, args);
	}

	@Override
	public byte[] blMove(final byte[] key, final byte[] destKey, final Direction from, final Direction to,
						 final int timeout){
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey).put("from", from)
				.put("to", to).put("timeout", timeout);
		final ListDirection fromDirection = DirectionConverter.INSTANCE.convert(from);
		final ListDirection toDirection = DirectionConverter.INSTANCE.convert(to);
		final JedisClusterCommand<byte[]> command = JedisClusterCommand.<byte[]>create(ProtocolCommand.BLMOVE)
				.general((cmd)->cmd.blmove(key, destKey, fromDirection, toDirection, timeout))
				.pipeline((cmd)->cmd.blmove(key, destKey, fromDirection, toDirection, timeout))
				.transaction((cmd)->cmd.blmove(key, destKey, fromDirection, toDirection, timeout));
		return execute(command, args);
	}

	@Override
	public List<String> blPop(final String[] keys, final int timeout){
		final CommandArguments args = CommandArguments.create("keys", keys).put("timeout", timeout);
		final JedisClusterCommand<List<String>> command = JedisClusterCommand.<List<String>>create(
						ProtocolCommand.BLPOP).general((cmd)->cmd.blpop(timeout, keys))
				.pipeline((cmd)->cmd.blpop(timeout, keys)).transaction((cmd)->cmd.blpop(timeout, keys));
		return execute(command, args);
	}

	@Override
	public List<byte[]> blPop(final byte[][] keys, final int timeout){
		final CommandArguments args = CommandArguments.create("keys", keys).put("timeout", timeout);
		final JedisClusterCommand<List<byte[]>> command = JedisClusterCommand.<List<byte[]>>create(
						ProtocolCommand.BLPOP).general((cmd)->cmd.blpop(timeout, keys))
				.pipeline((cmd)->cmd.blpop(timeout, keys)).transaction((cmd)->cmd.blpop(timeout, keys));
		return execute(command, args);
	}

	@Override
	public List<String> brPop(final String[] keys, final int timeout){
		final CommandArguments args = CommandArguments.create("keys", keys).put("timeout", timeout);
		final JedisClusterCommand<List<String>> command = JedisClusterCommand.<List<String>>create(
						ProtocolCommand.BRPOP).general((cmd)->cmd.brpop(timeout, keys))
				.pipeline((cmd)->cmd.brpop(timeout, keys)).transaction((cmd)->cmd.brpop(timeout, keys));
		return execute(command, args);
	}

	@Override
	public List<byte[]> brPop(final byte[][] keys, final int timeout){
		final CommandArguments args = CommandArguments.create("keys", keys).put("timeout", timeout);
		final JedisClusterCommand<List<byte[]>> command = JedisClusterCommand.<List<byte[]>>create(
						ProtocolCommand.BRPOP).general((cmd)->cmd.brpop(timeout, keys))
				.pipeline((cmd)->cmd.brpop(timeout, keys)).transaction((cmd)->cmd.brpop(timeout, keys));
		return execute(command, args);
	}

	@Override
	public String brPoplPush(final String key, final String destKey, final int timeout){
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey)
				.put("timeout", timeout);
		final JedisClusterCommand<String> command = JedisClusterCommand.<String>create(ProtocolCommand.BRPOPLPUSH)
				.general((cmd)->cmd.brpoplpush(key, destKey, timeout))
				.pipeline((cmd)->cmd.brpoplpush(key, destKey, timeout))
				.transaction((cmd)->cmd.brpoplpush(key, destKey, timeout));
		return execute(command, args);
	}

	@Override
	public byte[] brPoplPush(final byte[] key, final byte[] destKey, final int timeout){
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey)
				.put("timeout", timeout);
		final JedisClusterCommand<byte[]> command = JedisClusterCommand.<byte[]>create(ProtocolCommand.BRPOPLPUSH)
				.general((cmd)->cmd.brpoplpush(key, destKey, timeout))
				.pipeline((cmd)->cmd.brpoplpush(key, destKey, timeout))
				.transaction((cmd)->cmd.brpoplpush(key, destKey, timeout));
		return execute(command, args);
	}

	@Override
	public String lPop(final String key){
		final CommandArguments args = CommandArguments.create("key", key);
		final JedisClusterCommand<String> command = JedisClusterCommand.<String>create(ProtocolCommand.LPOP)
				.general((cmd)->cmd.lpop(key)).pipeline((cmd)->cmd.lpop(key)).transaction((cmd)->cmd.lpop(key));
		return execute(command, args);
	}

	@Override
	public byte[] lPop(final byte[] key){
		final CommandArguments args = CommandArguments.create("key", key);
		final JedisClusterCommand<byte[]> command = JedisClusterCommand.<byte[]>create(ProtocolCommand.LPOP)
				.general((cmd)->cmd.lpop(key)).pipeline((cmd)->cmd.lpop(key)).transaction((cmd)->cmd.lpop(key));
		return execute(command, args);
	}

	@Override
	public long lPush(final String key, final String... values){
		final CommandArguments args = CommandArguments.create("key", key).put("values", values);
		final JedisClusterCommand<Long> command = JedisClusterCommand.<Long>create(ProtocolCommand.LPUSH)
				.general((cmd)->cmd.lpush(key, values)).pipeline((cmd)->cmd.lpush(key, values))
				.transaction((cmd)->cmd.lpush(key, values));
		return execute(command, args);
	}

	@Override
	public long lPush(final byte[] key, final byte[]... values){
		final CommandArguments args = CommandArguments.create("key", key).put("values", values);
		final JedisClusterCommand<Long> command = JedisClusterCommand.<Long>create(ProtocolCommand.LPUSH)
				.general((cmd)->cmd.lpush(key, values)).pipeline((cmd)->cmd.lpush(key, values))
				.transaction((cmd)->cmd.lpush(key, values));
		return execute(command, args);
	}

	@Override
	public long lPushX(final String key, final String... values){
		final CommandArguments args = CommandArguments.create("key", key).put("values", values);
		final JedisClusterCommand<Long> command = JedisClusterCommand.<Long>create(ProtocolCommand.LPUSHX)
				.general((cmd)->cmd.lpushx(key, values)).pipeline((cmd)->cmd.lpushx(key, values))
				.transaction((cmd)->cmd.lpushx(key, values));
		return execute(command, args);
	}

	@Override
	public long lPushX(final byte[] key, final byte[]... values){
		final CommandArguments args = CommandArguments.create("key", key).put("values", values);
		final JedisClusterCommand<Long> command = JedisClusterCommand.<Long>create(ProtocolCommand.LPUSHX)
				.general((cmd)->cmd.lpushx(key, values)).pipeline((cmd)->cmd.lpushx(key, values))
				.transaction((cmd)->cmd.lpushx(key, values));
		return execute(command, args);
	}

	@Override
	public String rPop(final String key){
		final CommandArguments args = CommandArguments.create("key", key);
		final JedisClusterCommand<String> command = JedisClusterCommand.<String>create(ProtocolCommand.RPOP)
				.general((cmd)->cmd.rpop(key)).pipeline((cmd)->cmd.rpop(key)).transaction((cmd)->cmd.rpop(key));
		return execute(command, args);
	}

	@Override
	public byte[] rPop(final byte[] key){
		final CommandArguments args = CommandArguments.create("key", key);
		final JedisClusterCommand<byte[]> command = JedisClusterCommand.<byte[]>create(ProtocolCommand.RPOP)
				.general((cmd)->cmd.rpop(key)).pipeline((cmd)->cmd.rpop(key)).transaction((cmd)->cmd.rpop(key));
		return execute(command, args);
	}

	@Override
	public String rPoplPush(final String key, final String destKey){
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey);
		final JedisClusterCommand<String> command = JedisClusterCommand.<String>create(ProtocolCommand.RPOPLPUSH)
				.general((cmd)->cmd.rpoplpush(key, destKey)).pipeline((cmd)->cmd.rpoplpush(key, destKey))
				.transaction((cmd)->cmd.rpoplpush(key, destKey));
		return execute(command, args);
	}

	@Override
	public byte[] rPoplPush(final byte[] key, final byte[] destKey){
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey);
		final JedisClusterCommand<byte[]> command = JedisClusterCommand.<byte[]>create(ProtocolCommand.RPOPLPUSH)
				.general((cmd)->cmd.rpoplpush(key, destKey)).pipeline((cmd)->cmd.rpoplpush(key, destKey))
				.transaction((cmd)->cmd.rpoplpush(key, destKey));
		return execute(command, args);
	}

	@Override
	public long rPush(final String key, final String... values){
		final CommandArguments args = CommandArguments.create("key", key).put("values", values);
		final JedisClusterCommand<Long> command = JedisClusterCommand.<Long>create(ProtocolCommand.RPUSH)
				.general((cmd)->cmd.rpush(key, values)).pipeline((cmd)->cmd.rpush(key, values))
				.transaction((cmd)->cmd.rpush(key, values));
		return execute(command, args);
	}

	@Override
	public long rPush(final byte[] key, final byte[]... values){
		final CommandArguments args = CommandArguments.create("key", key).put("values", values);
		final JedisClusterCommand<Long> command = JedisClusterCommand.<Long>create(ProtocolCommand.RPUSH)
				.general((cmd)->cmd.rpush(key, values)).pipeline((cmd)->cmd.rpush(key, values))
				.transaction((cmd)->cmd.rpush(key, values));
		return execute(command, args);
	}

	@Override
	public long rPushX(final String key, final String... values){
		final CommandArguments args = CommandArguments.create("key", key).put("values", values);
		final JedisClusterCommand<Long> command = JedisClusterCommand.<Long>create(ProtocolCommand.RPUSHX)
				.general((cmd)->cmd.rpushx(key, values)).pipeline((cmd)->cmd.rpushx(key, values))
				.transaction((cmd)->cmd.rpushx(key, values));
		return execute(command, args);
	}

	@Override
	public long rPushX(final byte[] key, final byte[]... values){
		final CommandArguments args = CommandArguments.create("key", key).put("values", values);
		final JedisClusterCommand<Long> command = JedisClusterCommand.<Long>create(ProtocolCommand.RPUSHX)
				.general((cmd)->cmd.rpushx(key, values)).pipeline((cmd)->cmd.rpushx(key, values))
				.transaction((cmd)->cmd.rpushx(key, values));
		return execute(command, args);
	}

}
