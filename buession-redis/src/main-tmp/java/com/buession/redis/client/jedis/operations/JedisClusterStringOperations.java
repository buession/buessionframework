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
package com.buession.redis.client.jedis.operations;

import com.buession.lang.Status;
import com.buession.redis.client.jedis.JedisClusterClient;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.Command;
import com.buession.redis.core.command.args.GetExArgument;
import com.buession.redis.core.command.args.SetArgument;
import com.buession.redis.core.internal.jedis.JedisGetExParams;
import com.buession.redis.core.internal.jedis.JedisSetParams;
import redis.clients.jedis.params.GetExParams;
import redis.clients.jedis.params.SetParams;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Jedis 集群模式字符串命令操作
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public final class JedisClusterStringOperations extends AbstractStringOperations<JedisClusterClient> {

	public JedisClusterStringOperations(final JedisClusterClient client) {
		super(client);
	}

	@Override
	public Long append(final String key, final String value) {
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, Command.APPEND, (cmd)->cmd.append(key, value),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, Command.APPEND, (cmd)->cmd.append(key, value),
					(v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, Command.APPEND, (cmd)->cmd.append(key, value), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long append(final byte[] key, final byte[] value) {
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, Command.APPEND, (cmd)->cmd.append(key, value),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, Command.APPEND, (cmd)->cmd.append(key, value),
					(v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, Command.APPEND, (cmd)->cmd.append(key, value), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long incr(final String key) {
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, Command.INCR, (cmd)->cmd.incr(key), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, Command.INCR, (cmd)->cmd.incr(key), (v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, Command.INCR, (cmd)->cmd.incr(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long incr(final byte[] key) {
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, Command.INCR, (cmd)->cmd.incr(key), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, Command.INCR, (cmd)->cmd.incr(key), (v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, Command.INCR, (cmd)->cmd.incr(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long incrBy(final String key, final long value) {
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, Command.INCRBY, (cmd)->cmd.incrBy(key, value),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, Command.INCRBY, (cmd)->cmd.incrBy(key, value),
					(v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, Command.INCRBY, (cmd)->cmd.incrBy(key, value), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long incrBy(final byte[] key, final long value) {
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, Command.INCRBY, (cmd)->cmd.incrBy(key, value),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, Command.INCRBY, (cmd)->cmd.incrBy(key, value),
					(v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, Command.INCRBY, (cmd)->cmd.incrBy(key, value), (v)->v)
					.run(args);
		}
	}

	@Override
	public Double incrByFloat(final String key, final double value) {
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, Command.INCRBYFLOAT,
					(cmd)->cmd.incrByFloat(key, value), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, Command.INCRBYFLOAT,
					(cmd)->cmd.incrByFloat(key, value), (v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, Command.INCRBYFLOAT, (cmd)->cmd.incrByFloat(key, value),
					(v)->v)
					.run(args);
		}
	}

	@Override
	public Double incrByFloat(final byte[] key, final double value) {
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, Command.INCRBYFLOAT,
					(cmd)->cmd.incrByFloat(key, value), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, Command.INCRBYFLOAT,
					(cmd)->cmd.incrByFloat(key, value), (v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, Command.INCRBYFLOAT, (cmd)->cmd.incrByFloat(key, value),
					(v)->v)
					.run(args);
		}
	}

	@Override
	public Long decr(final String key) {
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, Command.DECR, (cmd)->cmd.decr(key), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, Command.DECR, (cmd)->cmd.decr(key), (v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, Command.DECR, (cmd)->cmd.decr(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long decr(final byte[] key) {
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, Command.DECR, (cmd)->cmd.decr(key), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, Command.DECR, (cmd)->cmd.decr(key), (v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, Command.DECR, (cmd)->cmd.decr(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long decrBy(final String key, final long value) {
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, Command.DECRBY, (cmd)->cmd.decrBy(key, value),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, Command.DECRBY, (cmd)->cmd.decrBy(key, value),
					(v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, Command.DECRBY, (cmd)->cmd.decrBy(key, value), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long decrBy(final byte[] key, final long value) {
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, Command.DECRBY, (cmd)->cmd.decrBy(key, value),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, Command.DECRBY, (cmd)->cmd.decrBy(key, value),
					(v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, Command.DECRBY, (cmd)->cmd.decrBy(key, value), (v)->v)
					.run(args);
		}
	}

	@Override
	public String get(final String key) {
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, Command.GET, (cmd)->cmd.get(key), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, Command.GET, (cmd)->cmd.get(key), (v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, Command.GET, (cmd)->cmd.get(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public byte[] get(final byte[] key) {
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, Command.GET, (cmd)->cmd.get(key), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, Command.GET, (cmd)->cmd.get(key), (v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, Command.GET, (cmd)->cmd.get(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public String getEx(final String key, final GetExArgument getExArgument) {
		final CommandArguments args = CommandArguments.create("key", key).put("getExArgument", getExArgument);
		final GetExParams getExParams = JedisGetExParams.from(getExArgument);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, Command.GETEX, (cmd)->cmd.getEx(key, getExParams),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, Command.GETEX,
					(cmd)->cmd.getEx(key, getExParams), (v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, Command.GETEX, (cmd)->cmd.getEx(key, getExParams), (v)->v)
					.run(args);
		}
	}

	@Override
	public byte[] getEx(final byte[] key, final GetExArgument getExArgument) {
		final CommandArguments args = CommandArguments.create("key", key).put("getExArgument", getExArgument);
		final GetExParams getExParams = JedisGetExParams.from(getExArgument);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, Command.GETEX, (cmd)->cmd.getEx(key, getExParams),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, Command.GETEX,
					(cmd)->cmd.getEx(key, getExParams), (v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, Command.GETEX, (cmd)->cmd.getEx(key, getExParams), (v)->v)
					.run(args);
		}
	}

	@Override
	public String getSet(final String key, final String value) {
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, Command.GETSET, (cmd)->cmd.getSet(key, value),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, Command.GETSET, (cmd)->cmd.getSet(key, value),
					(v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, Command.GETSET, (cmd)->cmd.getSet(key, value), (v)->v)
					.run(args);
		}
	}

	@Override
	public byte[] getSet(final byte[] key, final byte[] value) {
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, Command.GETSET, (cmd)->cmd.getSet(key, value),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, Command.GETSET, (cmd)->cmd.getSet(key, value),
					(v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, Command.GETSET, (cmd)->cmd.getSet(key, value), (v)->v)
					.run(args);
		}
	}

	@Override
	public String getDel(final String key) {
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, Command.GETDEL, (cmd)->cmd.getDel(key), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, Command.GETDEL, (cmd)->cmd.getDel(key), (v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, Command.GETDEL, (cmd)->cmd.getDel(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public byte[] getDel(final byte[] key) {
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, Command.GETDEL, (cmd)->cmd.getDel(key), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, Command.GETDEL, (cmd)->cmd.getDel(key), (v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, Command.GETDEL, (cmd)->cmd.getDel(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public List<String> mGet(final String... keys) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, Command.MGET, (cmd)->cmd.mget(keys), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, Command.MGET, (cmd)->cmd.mget(keys), (v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, Command.MGET, (cmd)->cmd.mget(keys), (v)->v)
					.run(args);
		}
	}

	@Override
	public List<byte[]> mGet(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, Command.MGET, (cmd)->cmd.mget(keys), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, Command.MGET, (cmd)->cmd.mget(keys), (v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, Command.MGET, (cmd)->cmd.mget(keys), (v)->v)
					.run(args);
		}
	}

	@Override
	public Status mSet(final Map<String, String> values) {
		final CommandArguments args = CommandArguments.create("values", values);
		final List<String> temp = new ArrayList<>(values.size() * 2);

		values.forEach((key, value)->{
			temp.add(key);
			temp.add(value);
		});

		final String[] keysValues = temp.toArray(new String[0]);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, Command.MSET, (cmd)->cmd.mset(keysValues),
					okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, Command.MSET, (cmd)->cmd.mset(keysValues),
					okStatusConverter)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, Command.MSET, (cmd)->cmd.mset(keysValues),
					okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status mSetNx(final Map<String, String> values) {
		final CommandArguments args = CommandArguments.create("values", values);
		final List<String> temp = new ArrayList<>(values.size() * 2);

		values.forEach((key, value)->{
			temp.add(key);
			temp.add(value);
		});

		final String[] keysValues = temp.toArray(new String[0]);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, Command.MSETNX, (cmd)->cmd.msetnx(keysValues),
					oneStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, Command.MSETNX, (cmd)->cmd.msetnx(keysValues),
					oneStatusConverter)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, Command.MSETNX, (cmd)->cmd.msetnx(keysValues),
					oneStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status pSetEx(final String key, final String value, final int lifetime) {
		final CommandArguments args = CommandArguments.create("key", key).put("value", value).put("lifetime", lifetime);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, Command.PSETEX,
					(cmd)->cmd.psetex(key, lifetime, value), okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, Command.PSETEX,
					(cmd)->cmd.psetex(key, lifetime, value), okStatusConverter)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, Command.PSETEX, (cmd)->cmd.psetex(key, lifetime, value),
					okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status pSetEx(final byte[] key, final byte[] value, final int lifetime) {
		final CommandArguments args = CommandArguments.create("key", key).put("value", value).put("lifetime", lifetime);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, Command.PSETEX,
					(cmd)->cmd.psetex(key, lifetime, value), okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, Command.PSETEX,
					(cmd)->cmd.psetex(key, lifetime, value), okStatusConverter)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, Command.PSETEX, (cmd)->cmd.psetex(key, lifetime, value),
					okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status set(final String key, final String value) {
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, Command.SET, (cmd)->cmd.set(key, value),
					okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, Command.SET, (cmd)->cmd.set(key, value),
					okStatusConverter)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, Command.SET, (cmd)->cmd.set(key, value), okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status set(final byte[] key, final byte[] value) {
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, Command.SET, (cmd)->cmd.set(key, value),
					okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, Command.SET, (cmd)->cmd.set(key, value),
					okStatusConverter)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, Command.SET, (cmd)->cmd.set(key, value), okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status set(final String key, final String value, final SetArgument setArgument) {
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);
		final SetParams setParams = JedisSetParams.from(setArgument);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, Command.SET, (cmd)->cmd.set(key, value, setParams),
					okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, Command.SET,
					(cmd)->cmd.set(key, value, setParams), okStatusConverter)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, Command.SET, (cmd)->cmd.set(key, value, setParams),
					okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status set(final byte[] key, final byte[] value, final SetArgument setArgument) {
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);
		final SetParams setParams = JedisSetParams.from(setArgument);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, Command.SET, (cmd)->cmd.set(key, value, setParams),
					okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, Command.SET,
					(cmd)->cmd.set(key, value, setParams), okStatusConverter)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, Command.SET, (cmd)->cmd.set(key, value, setParams),
					okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status setEx(final String key, final String value, final int lifetime) {
		final CommandArguments args = CommandArguments.create("key", key).put("value", value).put("lifetime", lifetime);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, Command.SETEX,
					(cmd)->cmd.setex(key, lifetime, value), okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, Command.SETEX,
					(cmd)->cmd.setex(key, lifetime, value), okStatusConverter)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, Command.SETEX, (cmd)->cmd.setex(key, lifetime, value),
					okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status setEx(final byte[] key, final byte[] value, final int lifetime) {
		final CommandArguments args = CommandArguments.create("key", key).put("value", value).put("lifetime", lifetime);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, Command.SETEX,
					(cmd)->cmd.setex(key, lifetime, value), okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, Command.SETEX,
					(cmd)->cmd.setex(key, lifetime, value), okStatusConverter)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, Command.SETEX, (cmd)->cmd.setex(key, lifetime, value),
					okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status setNx(final String key, final String value) {
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, Command.SETNX, (cmd)->cmd.setnx(key, value),
					oneStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, Command.SETNX, (cmd)->cmd.setnx(key, value),
					oneStatusConverter)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, Command.SETNX, (cmd)->cmd.setnx(key, value),
					oneStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status setNx(final byte[] key, final byte[] value) {
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, Command.SETNX, (cmd)->cmd.setnx(key, value),
					oneStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, Command.SETNX, (cmd)->cmd.setnx(key, value),
					oneStatusConverter)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, Command.SETNX, (cmd)->cmd.setnx(key, value),
					oneStatusConverter)
					.run(args);
		}
	}

	@Override
	public Long setRange(final String key, final long offset, final String value) {
		final CommandArguments args = CommandArguments.create("key", key).put("offset", offset).put("value", value);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, Command.SETRANGE,
					(cmd)->cmd.setrange(key, offset, value), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, Command.SETRANGE,
					(cmd)->cmd.setrange(key, offset, value), (v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, Command.SETRANGE, (cmd)->cmd.setrange(key, offset, value),
					(v)->v)
					.run(args);
		}
	}

	@Override
	public Long setRange(final byte[] key, final long offset, final byte[] value) {
		final CommandArguments args = CommandArguments.create("key", key).put("offset", offset).put("value", value);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, Command.SETRANGE,
					(cmd)->cmd.setrange(key, offset, value), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, Command.SETRANGE,
					(cmd)->cmd.setrange(key, offset, value), (v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, Command.SETRANGE, (cmd)->cmd.setrange(key, offset, value),
					(v)->v)
					.run(args);
		}
	}

	@Override
	public String getRange(final String key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create("key", key).put("start", start).put("end", end);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, Command.GETRANGE,
					(cmd)->cmd.getrange(key, start, end), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, Command.GETRANGE,
					(cmd)->cmd.getrange(key, start, end), (v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, Command.GETRANGE, (cmd)->cmd.getrange(key, start, end),
					(v)->v)
					.run(args);
		}
	}

	@Override
	public byte[] getRange(final byte[] key, final long start, final long end) {
		final CommandArguments args = CommandArguments.create("key", key).put("start", start).put("end", end);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, Command.GETRANGE,
					(cmd)->cmd.getrange(key, start, end), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, Command.GETRANGE,
					(cmd)->cmd.getrange(key, start, end), (v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, Command.GETRANGE, (cmd)->cmd.getrange(key, start, end),
					(v)->v)
					.run(args);
		}
	}

	@Override
	public Long strlen(final String key) {
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, Command.STRLEN, (cmd)->cmd.strlen(key), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, Command.STRLEN, (cmd)->cmd.strlen(key), (v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, Command.STRLEN, (cmd)->cmd.strlen(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long strlen(final byte[] key) {
		final CommandArguments args = CommandArguments.create("key", key);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, Command.STRLEN, (cmd)->cmd.strlen(key), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, Command.STRLEN, (cmd)->cmd.strlen(key), (v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, Command.STRLEN, (cmd)->cmd.strlen(key), (v)->v)
					.run(args);
		}
	}

	@Override
	public String substr(final String key, final int start, final int end) {
		final CommandArguments args = CommandArguments.create("key", key).put("start", start).put("end", end);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, Command.SUBSTR, (cmd)->cmd.substr(key, start, end),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, Command.SUBSTR,
					(cmd)->cmd.substr(key, start, end), (v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, Command.SUBSTR, (cmd)->cmd.substr(key, start, end), (v)->v)
					.run(args);
		}
	}

	@Override
	public byte[] substr(final byte[] key, final int start, final int end) {
		final CommandArguments args = CommandArguments.create("key", key).put("start", start).put("end", end);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, Command.SUBSTR, (cmd)->cmd.substr(key, start, end),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, Command.SUBSTR,
					(cmd)->cmd.substr(key, start, end), (v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, Command.SUBSTR, (cmd)->cmd.substr(key, start, end), (v)->v)
					.run(args);
		}
	}

}
