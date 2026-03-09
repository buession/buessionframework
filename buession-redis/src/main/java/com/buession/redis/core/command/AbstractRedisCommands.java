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
package com.buession.redis.core.command;

import com.buession.core.collect.Arrays;
import com.buession.core.converter.Converter;
import com.buession.core.utils.ByteUtils;
import com.buession.core.validator.Validate;
import com.buession.redis.client.RedisClient;
import com.buession.redis.core.internal.convert.Converters;
import com.buession.redis.utils.SafeEncoder;

/**
 * Redis 命令操作
 *
 * @param <C>
 * 		Redis Client {@link RedisClient}
 *
 * @author Yong.Teng
 */
public abstract class AbstractRedisCommands<C extends RedisClient> implements RedisCommands {

	protected C client;

	public AbstractRedisCommands(final C client) {
		this.client = client;
	}

	protected boolean isPipeline() {
		return client.getConnection().isPipeline();
	}

	protected boolean isTransaction() {
		return client.getConnection().isTransaction();
	}

	protected <B extends BaseCommandBuilder<C, O, R, R>, O, R> R executeCommand(final B builder) {
		return builder.run();
	}

	protected <B extends BaseCommandBuilder<C, O, SR, R>, O, SR, R> R executeCommand(final B builder,
																					 final com.buession.redis.core.Command.Executor<O, SR> executor,
																					 final Converter<SR, R> converter) {
		return builder.executor(executor).converter(converter).run();
	}

	protected <B extends BaseCommandBuilder<C, O, R, R>, O, R> R executeCommand(final B builder,
																				final CommandArguments args) {
		return executeCommand(builder, args, (v)->v);
	}

	protected <B extends BaseCommandBuilder<C, O, SR, R>, O, SR, R> R executeCommand(final B builder,
																					 final CommandArguments args,
																					 final Converter<SR, R> converter) {
		return builder.arguments(args).converter(converter).run();
	}

	protected <B extends BaseCommandBuilder<C, O, SR, R>, O, SR, R> R executeCommand(final B builder,
																					 final CommandArguments args,
																					 final com.buession.redis.core.Command.Executor<O, SR> executor,
																					 final Converter<SR, R> converter) {
		return builder.arguments(args).executor(executor).converter(converter).run();
	}

	protected final String rawKey(final String key) {
		String prefix = client.getClientOptions().getPrefix();
		return Validate.isEmpty(prefix) ? key : prefix.concat(key);
	}

	protected final byte[] rawKey(byte[] key) {
		String prefix = client.getClientOptions().getPrefix();
		return Validate.isEmpty(prefix) ? key : ByteUtils.concat(SafeEncoder.encode(prefix), key);
	}

	protected final String[] rawKeys(final String[] keys) {
		String prefix = client.getClientOptions().getPrefix();
		return Validate.isEmpty(prefix) || Validate.isEmpty(keys) ? keys : Arrays.map(keys, String.class, this::rawKey);
	}

	protected final byte[][] rawKeys(final byte[][] keys) {
		String prefix = client.getClientOptions().getPrefix();

		if(Validate.isEmpty(prefix) || Validate.isEmpty(keys)){
			return keys;
		}

		byte[] prefixByte = SafeEncoder.encode(prefix);
		return Arrays.map(keys, byte[].class, (value)->ByteUtils.concat(prefixByte, value));
	}

	protected final byte[] rawBinaryKey(final String key) {
		return SafeEncoder.encode(rawKey(key));
	}

	protected final byte[][] rawBinaryKeys(final String[] keys) {
		final byte[][] result = new byte[keys.length][];

		for(int i = 0; i < keys.length; i++){
			result[i] = SafeEncoder.encode(rawKey(keys[i]));
		}

		return result;
	}

	protected final String rawStringKey(final byte[] key) {
		return SafeEncoder.encode(rawKey(key));
	}

	protected final String[] rawStringKeys(final byte[][] keys) {
		final String[] result = new String[keys.length];

		for(int i = 0; i < keys.length; i++){
			result[i] = SafeEncoder.encode(rawKey(keys[i]));
		}

		return result;
	}

}
