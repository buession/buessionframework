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

import com.buession.core.converter.Converter;
import com.buession.core.utils.StringUtils;
import com.buession.lang.Status;
import com.buession.redis.client.lettuce.LettuceStandaloneClient;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.internal.convert.response.MapConverter;
import com.buession.redis.core.internal.lettuce.LettuceSetArgs;
import com.buession.redis.utils.SafeEncoder;
import io.lettuce.core.KeyValue;
import io.lettuce.core.SetArgs;
import io.lettuce.core.Value;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

/**
 * Lettuce 单机模式字符串命令操作
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public final class LettuceStringOperations extends AbstractStringOperations<LettuceStandaloneClient> {

	public LettuceStringOperations(final LettuceStandaloneClient client) {
		super(client);
	}

	@Override
	public Long append(final byte[] key, final byte[] value) {
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);

		if(isMulti()){
			return new LettuceAsyncCommand<>(client, ProtocolCommand.APPEND, (cmd)->cmd.append(key, value), (v)->v)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.APPEND, (cmd)->cmd.append(key, value), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long incr(final byte[] key) {
		final CommandArguments args = CommandArguments.create("key", key);

		if(isMulti()){
			return new LettuceAsyncCommand<>(client, ProtocolCommand.INCR, (cmd)->cmd.incr(key), (v)->v)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.INCR, (cmd)->cmd.incr(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long incrBy(final byte[] key, final long value) {
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);

		if(isMulti()){
			return new LettuceAsyncCommand<>(client, ProtocolCommand.INCRBY, (cmd)->cmd.incrby(key, value), (v)->v)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.INCRBY, (cmd)->cmd.incrby(key, value), (v)->v)
					.run(args);
		}
	}

	@Override
	public Double incrByFloat(final byte[] key, final double value) {
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);

		if(isMulti()){
			return new LettuceAsyncCommand<>(client, ProtocolCommand.INCRBYFLOAT, (cmd)->cmd.incrbyfloat(key, value),
					(v)->v)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.INCRBYFLOAT, (cmd)->cmd.incrbyfloat(key, value), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long decr(final byte[] key) {
		final CommandArguments args = CommandArguments.create("key", key);

		if(isMulti()){
			return new LettuceAsyncCommand<>(client, ProtocolCommand.DECR, (cmd)->cmd.decr(key), (v)->v)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.DECR, (cmd)->cmd.decr(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long decrBy(final byte[] key, final long value) {
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);

		if(isMulti()){
			return new LettuceAsyncCommand<>(client, ProtocolCommand.DECRBY, (cmd)->cmd.decrby(key, value), (v)->v)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.DECRBY, (cmd)->cmd.decrby(key, value), (v)->v)
					.run(args);
		}
	}

	@Override
	public String get(final String key) {
		final CommandArguments args = CommandArguments.create("key", key);
		final byte[] bKey = SafeEncoder.encode(key);

		return get(bKey, SafeEncoder::encode, args);
	}

	@Override
	public byte[] get(final byte[] key) {
		final CommandArguments args = CommandArguments.create("key", key);
		return get(key, (v)->v, args);
	}

	@Override
	public String getEx(final String key, final GetExArgument getExArgument) {
		final CommandArguments args = CommandArguments.create("key", key).put("getExArgument", getExArgument);
		return getEx(args);
	}

	@Override
	public byte[] getEx(final byte[] key, final GetExArgument getExArgument) {
		final CommandArguments args = CommandArguments.create("key", key).put("getExArgument", getExArgument);
		return getEx(args);
	}

	@Override
	public String getSet(final String key, final String value) {
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);
		final byte[] bKey = SafeEncoder.encode(key);
		final byte[] bValue = SafeEncoder.encode(value);

		return getSet(bKey, bValue, SafeEncoder::encode, args);
	}

	@Override
	public byte[] getSet(final byte[] key, final byte[] value) {
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);
		return getSet(key, value, (v)->v, args);
	}

	@Override
	public String getDel(final String key) {
		final CommandArguments args = CommandArguments.create("key", key);
		return getDel(args);
	}

	@Override
	public byte[] getDel(final byte[] key) {
		final CommandArguments args = CommandArguments.create("key", key);
		return getDel(args);
	}

	@Override
	public List<String> mGet(final String... keys) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys);
		final byte[][] bKeys = SafeEncoder.encode(keys);
		final com.buession.core.converter.ListConverter<KeyValue<byte[], byte[]>, String> listConverter =
				new com.buession.core.converter.ListConverter<>((v)->SafeEncoder.encode(v.getValue()));

		return mGet(bKeys, listConverter, args);
	}

	@Override
	public List<byte[]> mGet(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys);
		final com.buession.core.converter.ListConverter<KeyValue<byte[], byte[]>, byte[]> listConverter =
				new com.buession.core.converter.ListConverter<>(Value::getValue);

		return mGet(keys, listConverter, args);
	}

	@Override
	public Status mSet(final Map<String, String> values) {
		final CommandArguments args = CommandArguments.create("values", values);
		final Map<byte[], byte[]> bValues = (new MapConverter.StringToBinaryMapConverter()).convert(values);

		if(isMulti()){
			return new LettuceAsyncCommand<>(client, ProtocolCommand.MSET, (cmd)->cmd.mset(bValues), okStatusConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.MSET, (cmd)->cmd.mset(bValues), okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status mSetNx(final Map<String, String> values) {
		final CommandArguments args = CommandArguments.create("values", values);
		final Map<byte[], byte[]> bValues = (new MapConverter.StringToBinaryMapConverter()).convert(values);

		if(isMulti()){
			return new LettuceAsyncCommand<>(client, ProtocolCommand.MSETNX, (cmd)->cmd.msetnx(bValues),
					booleanStatusConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.MSETNX, (cmd)->cmd.msetnx(bValues),
					booleanStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status pSetEx(final byte[] key, final byte[] value, final int lifetime) {
		final CommandArguments args = CommandArguments.create("key", key).put("value", value).put("lifetime", lifetime);

		if(isMulti()){
			return new LettuceAsyncCommand<>(client, ProtocolCommand.PSETEX, (cmd)->cmd.psetex(key, lifetime, value),
					okStatusConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.PSETEX, (cmd)->cmd.psetex(key, lifetime, value),
					okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status set(final byte[] key, final byte[] value) {
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);

		if(isMulti()){
			return new LettuceAsyncCommand<>(client, ProtocolCommand.SET, (cmd)->cmd.set(key, value), okStatusConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.SET, (cmd)->cmd.set(key, value), okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status set(final byte[] key, final byte[] value, final SetArgument setArgument) {
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);
		final SetArgs setArgs = LettuceSetArgs.from(setArgument);

		if(isMulti()){
			return new LettuceAsyncCommand<>(client, ProtocolCommand.SET, (cmd)->cmd.set(key, value, setArgs),
					okStatusConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.SET, (cmd)->cmd.set(key, value, setArgs),
					okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status setEx(final byte[] key, final byte[] value, final int lifetime) {
		final CommandArguments args = CommandArguments.create("key", key).put("value", value).put("lifetime", lifetime);

		if(isMulti()){
			return new LettuceAsyncCommand<>(client, ProtocolCommand.SETEX, (cmd)->cmd.setex(key, lifetime, value),
					okStatusConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.SETEX, (cmd)->cmd.setex(key, lifetime, value),
					okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status setNx(final byte[] key, final byte[] value) {
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);

		if(isMulti()){
			return new LettuceAsyncCommand<>(client, ProtocolCommand.SETNX, (cmd)->cmd.setnx(key, value),
					booleanStatusConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.SETNX, (cmd)->cmd.setnx(key, value),
					booleanStatusConverter)
					.run(args);
		}
	}

	@Override
	public Long setRange(final byte[] key, final long offset, final byte[] value) {
		final CommandArguments args = CommandArguments.create("key", key).put("offset", offset).put("value", value);

		if(isMulti()){
			return new LettuceAsyncCommand<>(client, ProtocolCommand.SETRANGE, (cmd)->cmd.setrange(key, offset, value),
					(v)->v)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.SETRANGE, (cmd)->cmd.setrange(key, offset, value),
					(v)->v)
					.run(args);
		}
	}

	@Override
	public byte[] getRange(final byte[] key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create("key", key).put("start", start).put("end", end);

		if(isMulti()){
			return new LettuceAsyncCommand<>(client, ProtocolCommand.GETRANGE, (cmd)->cmd.getrange(key, start, end),
					(v)->v)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.GETRANGE, (cmd)->cmd.getrange(key, start, end), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long strlen(final byte[] key) {
		final CommandArguments args = CommandArguments.create("key", key);

		if(isMulti()){
			return new LettuceAsyncCommand<>(client, ProtocolCommand.STRLEN, (cmd)->cmd.strlen(key), (v)->v)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.STRLEN, (cmd)->cmd.strlen(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public String substr(final String key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create("key", key).put("start", start).put("end", end);
		final byte[] bKey = SafeEncoder.encode(key);
		final Converter<byte[], String> converter = (v)->StringUtils.substring(SafeEncoder.encode(v),
				(int) start, (int) end);

		return substr(bKey, converter, args);
	}

	@Override
	public byte[] substr(final byte[] key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create("key", key).put("start", start).put("end", end);
		final Converter<byte[], byte[]> converter = (v)->StringUtils.substring(SafeEncoder.encode(v), (int) start,
				(int) end).getBytes(StandardCharsets.UTF_8);

		return substr(key, converter, args);
	}

	private <V> V get(final byte[] key, final Converter<byte[], V> converter, final CommandArguments args) {
		if(isMulti()){
			return new LettuceAsyncCommand<>(client, ProtocolCommand.GET, (cmd)->cmd.get(key), converter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.GET, (cmd)->cmd.get(key), converter)
					.run(args);
		}
	}

	private <V> V getEx(final CommandArguments args) {
		if(isMulti()){
			return new LettuceAsyncCommand<V, V>(client, ProtocolCommand.GETEX)
					.run(args);
		}else{
			return new LettuceCommand<V, V>(client, ProtocolCommand.GETEX)
					.run(args);
		}
	}

	private <V> V getSet(final byte[] key, final byte[] value, final Converter<byte[], V> converter,
						 final CommandArguments args) {
		if(isMulti()){
			return new LettuceAsyncCommand<>(client, ProtocolCommand.GETSET, (cmd)->cmd.getset(key, value), converter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.GETSET, (cmd)->cmd.getset(key, value), converter)
					.run(args);
		}
	}

	private <V> V getDel(final CommandArguments args) {
		if(isMulti()){
			return new LettuceAsyncCommand<V, V>(client, ProtocolCommand.GETDEL)
					.run(args);
		}else{
			return new LettuceCommand<V, V>(client, ProtocolCommand.GETDEL)
					.run(args);
		}
	}

	private <V> List<V> mGet(final byte[][] keys, final Converter<List<KeyValue<byte[], byte[]>>, List<V>> converter,
							 final CommandArguments args) {
		if(isMulti()){
			return new LettuceAsyncCommand<>(client, ProtocolCommand.MGET, (cmd)->cmd.mget(keys), converter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.MGET, (cmd)->cmd.mget(keys), converter)
					.run(args);
		}
	}

	private <V> V substr(final byte[] key, final Converter<byte[], V> converter, final CommandArguments args) {
		if(isMulti()){
			return new LettuceAsyncCommand<>(client, ProtocolCommand.SUBSTR, (cmd)->cmd.get(key), converter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.SUBSTR, (cmd)->cmd.get(key), converter)
					.run(args);
		}
	}

}
