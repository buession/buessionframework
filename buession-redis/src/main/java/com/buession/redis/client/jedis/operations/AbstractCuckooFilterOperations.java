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
 * | Copyright @ 2013-2026 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.client.jedis.operations;

import com.buession.core.converter.MapEntryMapConverter;
import com.buession.lang.Status;
import com.buession.redis.client.jedis.JedisRedisClient;
import com.buession.redis.client.operations.CuckooFilterOperations;
import com.buession.redis.core.command.Command;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.internal.jedis.JedisCFInsertParams;
import com.buession.redis.core.internal.jedis.JedisCFReserveParams;
import com.buession.redis.utils.SafeEncoder;

import java.util.List;
import java.util.Map;

/**
 * Jedis 布谷鸟过滤器命令操作抽象类
 *
 * @param <C>
 * 		Redis Client {@link JedisRedisClient}
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public abstract class AbstractCuckooFilterOperations<C extends JedisRedisClient> extends AbstractJedisRedisOperations<C>
		implements CuckooFilterOperations {

	public AbstractCuckooFilterOperations(final C client) {
		super(client);
	}

	@Override
	public Status cfAdd(final String key, final String item) {
		final CommandArguments args = CommandArguments.create(key).add(item);
		return JedisCommandBuilder.<Boolean, Status>newBuilder(client, Command.CF_ADD)
				.executor((cmd)->cmd.cfAdd(key, item)).arguments(args).converter(booleanStatusConverter).run();
	}

	@Override
	public Status cfAdd(final byte[] key, final byte[] item) {
		return cfAdd(SafeEncoder.encode(key), SafeEncoder.encode(item));
	}

	@Override
	public Status cfAddNx(final String key, final String item) {
		final CommandArguments args = CommandArguments.create(key).add(item);
		return JedisCommandBuilder.<Boolean, Status>newBuilder(client, Command.CF_ADDNX)
				.executor((cmd)->cmd.cfAddNx(key, item)).arguments(args).converter(booleanStatusConverter).run();
	}

	@Override
	public Status cfAddNx(final byte[] key, final byte[] item) {
		return cfAdd(SafeEncoder.encode(key), SafeEncoder.encode(item));
	}

	@Override
	public Long cfCount(final String key, final String item) {
		final CommandArguments args = CommandArguments.create(key).add(item);
		return JedisCommandBuilder.<Long, Long>newBuilder(client, Command.CF_COUNT)
				.executor((cmd)->cmd.cfCount(key, item)).arguments(args).converter((v)->v).run();
	}

	@Override
	public Long cfCount(final byte[] key, final byte[] item) {
		return cfCount(SafeEncoder.encode(key), SafeEncoder.encode(item));
	}

	@Override
	public Status cfDel(final String key, final String item) {
		final CommandArguments args = CommandArguments.create(key);
		return JedisCommandBuilder.<Boolean, Status>newBuilder(client, Command.CF_DEL)
				.executor((cmd)->cmd.cfDel(key, item))
				.arguments(args).converter(booleanStatusConverter).run();
	}

	@Override
	public Status cfDel(final byte[] key, final byte[] item) {
		return cfDel(SafeEncoder.encode(key), SafeEncoder.encode(item));
	}

	@Override
	public Boolean cfExists(final String key, final String item) {
		final CommandArguments args = CommandArguments.create(key).add(item);
		return JedisCommandBuilder.<Boolean, Boolean>newBuilder(client, Command.CF_EXISTS)
				.executor((cmd)->cmd.cfExists(key, item)).arguments(args).converter((v)->v).run();
	}

	@Override
	public Boolean cfExists(final byte[] key, final byte[] item) {
		return cfExists(SafeEncoder.encode(key), SafeEncoder.encode(item));
	}

	@Override
	public Map<String, Object> cfInfo(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return JedisCommandBuilder.<Map<String, Object>, Map<String, Object>>newBuilder(client, Command.CF_INFO)
				.executor((cmd)->cmd.cfInfo(key)).arguments(args).converter((v)->v).run();
	}

	@Override
	public Map<String, Object> cfInfo(final byte[] key) {
		return cfInfo(SafeEncoder.encode(key));
	}

	@Override
	public List<Boolean> cfInsert(final String key, final String... items) {
		final CommandArguments args = CommandArguments.create(key).add("ITEMS").add(items);
		return JedisCommandBuilder.<List<Boolean>, List<Boolean>>newBuilder(client, Command.CF_INSERT)
				.executor((cmd)->cmd.cfInsert(key, items)).arguments(args).converter((v)->v).run();
	}

	@Override
	public List<Boolean> cfInsert(final byte[] key, final byte[]... items) {
		return cfInsert(SafeEncoder.encode(key), SafeEncoder.encode(items));
	}

	@Override
	public List<Boolean> cfInsert(final String key, final CFInsertArgument cfInsertArgument, final String... items) {
		final CommandArguments args = CommandArguments.create(key).add(cfInsertArgument).add("ITEMS").add(items);
		final JedisCFInsertParams cfInsertParams = JedisCFInsertParams.from(cfInsertArgument);
		return JedisCommandBuilder.<List<Boolean>, List<Boolean>>newBuilder(client, Command.CF_INSERT)
				.executor((cmd)->cmd.cfInsert(key, cfInsertParams, items)).arguments(args).converter((v)->v).run();
	}

	@Override
	public List<Boolean> cfInsert(final byte[] key, final CFInsertArgument cfInsertArgument, final byte[]... items) {
		return cfInsert(SafeEncoder.encode(key), cfInsertArgument, SafeEncoder.encode(items));
	}

	@Override
	public List<Boolean> cfInsertNx(final String key, final String... items) {
		final CommandArguments args = CommandArguments.create(key).add("ITEMS").add(items);
		return JedisCommandBuilder.<List<Boolean>, List<Boolean>>newBuilder(client, Command.CF_INSERTNX)
				.executor((cmd)->cmd.cfInsertNx(key, items)).arguments(args).converter((v)->v).run();
	}

	@Override
	public List<Boolean> cfInsertNx(final byte[] key, final byte[]... items) {
		return cfInsertNx(SafeEncoder.encode(key), SafeEncoder.encode(items));
	}

	@Override
	public List<Boolean> cfInsertNx(final String key, final CFInsertArgument cfInsertArgument, final String... items) {
		final CommandArguments args = CommandArguments.create(key).add(cfInsertArgument).add("ITEMS").add(items);
		final JedisCFInsertParams cfInsertParams = JedisCFInsertParams.from(cfInsertArgument);
		return JedisCommandBuilder.<List<Boolean>, List<Boolean>>newBuilder(client, Command.CF_INSERTNX)
				.executor((cmd)->cmd.cfInsertNx(key, cfInsertParams, items)).arguments(args).converter((v)->v).run();
	}

	@Override
	public List<Boolean> cfInsertNx(final byte[] key, final CFInsertArgument cfInsertArgument, final byte[]... items) {
		return cfInsertNx(SafeEncoder.encode(key), cfInsertArgument, SafeEncoder.encode(items));
	}

	@Override
	public Status cfLoadchunk(final String key, final long iterator, final byte[] data) {
		final CommandArguments args = CommandArguments.create(key).add(iterator).add(SafeEncoder.encode(data));
		return JedisCommandBuilder.<String, Status>newBuilder(client, Command.CF_LOADCHUNK)
				.executor((cmd)->cmd.cfLoadChunk(key, iterator, data)).arguments(args).converter(okStatusConverter)
				.run();
	}

	@Override
	public Status cfLoadchunk(final byte[] key, final long iterator, final byte[] data) {
		return cfLoadchunk(SafeEncoder.encode(key), iterator, data);
	}

	@Override
	public List<Boolean> cfMExists(final String key, final String... items) {
		final CommandArguments args = CommandArguments.create(key).add(items);
		return JedisCommandBuilder.<List<Boolean>, List<Boolean>>newBuilder(client, Command.CF_MEXISTS)
				.executor((cmd)->cmd.cfMExists(key, items)).arguments(args).converter((v)->v).run();
	}

	@Override
	public List<Boolean> cfMExists(final byte[] key, final byte[]... items) {
		return cfMExists(SafeEncoder.encode(key), SafeEncoder.encode(items));
	}

	@Override
	public Status cfReserve(final String key, final CFReserveArgument cfReserveArgument) {
		final CommandArguments args = CommandArguments.create(key).add(cfReserveArgument);
		return JedisCommandBuilder.<String, Status>newBuilder(client, Command.CF_RESERVE)
				.executor((cmd)->{
					if(cfReserveArgument.getBucketSize() == null && cfReserveArgument.getMaxIterations() == null &&
							cfReserveArgument.getExpansion() == null){
						return cmd.cfReserve(key, cfReserveArgument.getCapacity());
					}else{
						final JedisCFReserveParams cfReserveParams = JedisCFReserveParams.from(cfReserveArgument);
						return cmd.cfReserve(key, cfReserveArgument.getCapacity(), cfReserveParams);
					}
				}).arguments(args).converter(okStatusConverter)
				.run();
	}

	@Override
	public Status cfReserve(final byte[] key, final CFReserveArgument cfReserveArgument) {
		return cfReserve(SafeEncoder.encode(key), cfReserveArgument);
	}

	@Override
	public Map<Long, byte[]> cfScandump(final String key, final long iterator) {
		final CommandArguments args = CommandArguments.create(key).add(iterator);
		return JedisCommandBuilder.<Map.Entry<Long, byte[]>, Map<Long, byte[]>>newBuilder(client, Command.CF_SCANDUMP)
				.executor((cmd)->cmd.cfScanDump(key, iterator)).arguments(args)
				.converter(new MapEntryMapConverter<>((k)->k, (v)->v)).run();
	}

	@Override
	public Map<Long, byte[]> cfScandump(final byte[] key, final long iterator) {
		return cfScandump(SafeEncoder.encode(key), iterator);
	}

}
