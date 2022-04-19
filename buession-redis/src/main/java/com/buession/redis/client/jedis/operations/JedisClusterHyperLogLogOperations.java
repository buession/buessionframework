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
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.internal.convert.Converters;
import com.buession.redis.core.internal.convert.response.OkStatusConverter;

/**
 * Jedis 集群模式 HyperLogLog 命令操作
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public final class JedisClusterHyperLogLogOperations extends AbstractHyperLogLogOperations<JedisClusterConnection> {

	public JedisClusterHyperLogLogOperations(final JedisClusterClient client){
		super(client);
	}

	@Override
	public Status pfAdd(final String key, final String... elements){
		final CommandArguments args = CommandArguments.create("key", key).put("elements", elements);
		final JedisClusterCommand<Status> command = JedisClusterCommand.<Status>create(ProtocolCommand.PFADD)
				.general((cmd)->cmd.pfadd(key, elements), Converters.ONE_STATUS_CONVERTER)
				.pipeline((cmd)->cmd.pfadd(key, elements), Converters.ONE_STATUS_CONVERTER)
				.transaction((cmd)->cmd.pfadd(key, elements), Converters.ONE_STATUS_CONVERTER);
		return execute(command, args);
	}

	@Override
	public Status pfAdd(final byte[] key, final byte[]... elements){
		final CommandArguments args = CommandArguments.create("key", key).put("elements", elements);
		final JedisClusterCommand<Status> command = JedisClusterCommand.<Status>create(ProtocolCommand.PFADD)
				.general((cmd)->cmd.pfadd(key, elements), Converters.ONE_STATUS_CONVERTER)
				.pipeline((cmd)->cmd.pfadd(key, elements), Converters.ONE_STATUS_CONVERTER)
				.transaction((cmd)->cmd.pfadd(key, elements), Converters.ONE_STATUS_CONVERTER);
		return execute(command, args);
	}

	@Override
	public Status pfMerge(final String destKey, final String... keys){
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("keys", keys);
		final JedisClusterCommand<Status> command = JedisClusterCommand.<Status>create(ProtocolCommand.PFMERGE)
				.general((cmd)->cmd.pfmerge(destKey, keys), OkStatusConverter.INSTANCE)
				.pipeline((cmd)->cmd.pfmerge(destKey, keys), OkStatusConverter.INSTANCE)
				.transaction((cmd)->cmd.pfmerge(destKey, keys), OkStatusConverter.INSTANCE);
		return execute(command, args);
	}

	@Override
	public Status pfMerge(final byte[] destKey, final byte[]... keys){
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("keys", keys);
		final JedisClusterCommand<Status> command = JedisClusterCommand.<Status>create(ProtocolCommand.PFMERGE)
				.general((cmd)->cmd.pfmerge(destKey, keys), OkStatusConverter.INSTANCE)
				.pipeline((cmd)->cmd.pfmerge(destKey, keys), OkStatusConverter.INSTANCE)
				.transaction((cmd)->cmd.pfmerge(destKey, keys), OkStatusConverter.INSTANCE);
		return execute(command, args);
	}

	@Override
	public long pfCount(final String... keys){
		final CommandArguments args = CommandArguments.create("keys", keys);
		final JedisClusterCommand<Long> command = JedisClusterCommand.<Long>create(ProtocolCommand.PFCOUNT)
				.general((cmd)->cmd.pfcount(keys)).pipeline((cmd)->cmd.pfcount(keys))
				.transaction((cmd)->cmd.pfcount(keys));
		return execute(command, args);
	}

	@Override
	public long pfCount(final byte[]... keys){
		final CommandArguments args = CommandArguments.create("keys", keys);
		final JedisClusterCommand<Long> command = JedisClusterCommand.<Long>create(ProtocolCommand.PFCOUNT)
				.general((cmd)->cmd.pfcount(keys)).pipeline((cmd)->cmd.pfcount(keys))
				.transaction((cmd)->cmd.pfcount(keys));
		return execute(command, args);
	}

}
