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

import com.buession.redis.client.connection.jedis.JedisClusterConnection;
import com.buession.redis.client.jedis.JedisClusterClient;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.internal.convert.jedis.JedisConverters;

/**
 * Jedis 集群模式 Key 命令操作
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public final class JedisClusterKeyOperations extends AbstractKeyOperations<JedisClusterConnection> {

	public JedisClusterKeyOperations(final JedisClusterClient client){
		super(client);
	}

	@Override
	public long del(final String... keys){
		final CommandArguments args = CommandArguments.create("keys", keys);
		final JedisClusterCommand<Long> command = JedisClusterCommand.<Long>create(
						ProtocolCommand.DEL).general((cmd)->cmd.del(keys))
				.pipeline((cmd)->cmd.del(keys)).transaction((cmd)->cmd.del(keys));
		return execute(command, args);
	}

	@Override
	public long del(final byte[]... keys){
		final CommandArguments args = CommandArguments.create("keys", keys);
		final JedisClusterCommand<Long> command = JedisClusterCommand.<Long>create(
						ProtocolCommand.DEL).general((cmd)->cmd.del(keys))
				.pipeline((cmd)->cmd.del(keys)).transaction((cmd)->cmd.del(keys));
		return execute(command, args);
	}

	@Override
	public String dump(final String key){
		final CommandArguments args = CommandArguments.create("key", key);
		final JedisClusterCommand<String> command = JedisClusterCommand.<String>create(
						ProtocolCommand.DUMP).general((cmd)->cmd.dump(key), JedisConverters.BINARY_TO_STRING_CONVERTER)
				.pipeline((cmd)->cmd.dump(key), JedisConverters.BINARY_TO_STRING_CONVERTER)
				.transaction((cmd)->cmd.dump(key), JedisConverters.BINARY_TO_STRING_CONVERTER);
		return execute(command, args);
	}

	@Override
	public byte[] dump(final byte[] key){
		final CommandArguments args = CommandArguments.create("key", key);
		final JedisClusterCommand<byte[]> command = JedisClusterCommand.<byte[]>create(
						ProtocolCommand.DUMP).general((cmd)->cmd.dump(key))
				.pipeline((cmd)->cmd.dump(key)).transaction((cmd)->cmd.dump(key));
		return execute(command, args);
	}

	@Override
	public boolean exists(final String key){
		final CommandArguments args = CommandArguments.create("key", key);
		final JedisClusterCommand<Boolean> command = JedisClusterCommand.<Boolean>create(
						ProtocolCommand.EXISTS).general((cmd)->cmd.exists(key))
				.pipeline((cmd)->cmd.exists(key)).transaction((cmd)->cmd.exists(key));
		return execute(command, args);
	}

	@Override
	public boolean exists(final byte[] key){
		final CommandArguments args = CommandArguments.create("key", key);
		final JedisClusterCommand<Boolean> command = JedisClusterCommand.<Boolean>create(
						ProtocolCommand.EXISTS).general((cmd)->cmd.exists(key))
				.pipeline((cmd)->cmd.exists(key)).transaction((cmd)->cmd.exists(key));
		return execute(command, args);
	}

	@Override
	public long exists(final String... keys){
		final CommandArguments args = CommandArguments.create("keys", keys);
		final JedisClusterCommand<Long> command = JedisClusterCommand.<Long>create(
						ProtocolCommand.EXISTS).general((cmd)->cmd.exists(keys))
				.pipeline((cmd)->cmd.exists(keys)).transaction((cmd)->cmd.exists(keys));
		return execute(command, args);
	}

	@Override
	public long exists(final byte[]... keys){
		final CommandArguments args = CommandArguments.create("keys", keys);
		final JedisClusterCommand<Long> command = JedisClusterCommand.<Long>create(
						ProtocolCommand.EXISTS).general((cmd)->cmd.exists(keys))
				.pipeline((cmd)->cmd.exists(keys)).transaction((cmd)->cmd.exists(keys));
		return execute(command, args);
	}

}
