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
 * | Copyright @ 2013-2020 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.client.jedis.operations;

import com.buession.core.Executor;
import com.buession.lang.Status;
import com.buession.redis.client.jedis.JedisClientUtils;
import com.buession.redis.client.jedis.JedisRedisClient;
import com.buession.redis.core.JedisScanParams;
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.operations.OperationsCommandArguments;
import com.buession.redis.exception.NotSupportedTransactionCommandException;
import com.buession.redis.utils.ReturnUtils;
import redis.clients.jedis.commands.JedisCommands;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Yong.Teng
 */
public class DefaultHashRedisOperations<C extends JedisCommands> extends AbstractJedisRedisOperations implements JedisHashRedisOperations {

	public DefaultHashRedisOperations(final JedisRedisClient client){
		super(client);
	}

	@Override
	public boolean hExists(final String key, final String field){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"field", field);

		if(isTransaction()){
			return execute((C jc)->getTransaction().hexists(key, field).get(), ProtocolCommand.HEXISTS, arguments);
		}else{
			return execute((C jc)->jc.hexists(key, field), ProtocolCommand.HEXISTS, arguments);
		}
	}

	@Override
	public Set<String> hKeys(final String key){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key);

		if(isTransaction()){
			return execute((C jc)->getTransaction().hkeys(key).get(), ProtocolCommand.HKEYS, arguments);
		}else{
			return execute((C jc)->jc.hkeys(key), ProtocolCommand.HKEYS, arguments);
		}
	}

	@Override
	public List<String> hVals(final String key){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key);

		if(isTransaction()){
			return execute((C jc)->getTransaction().hvals(key).get(), ProtocolCommand.HVALS, arguments);
		}else{
			return execute((C jc)->jc.hvals(key), ProtocolCommand.HVALS, arguments);
		}
	}

	@Override
	public Status hSet(final String key, final String field, final String value){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"field", field).put("value", value);

		if(isTransaction()){
			return execute((C jc)->ReturnUtils.statusForBool(getTransaction().hset(key, field, value).get() > 0),
					ProtocolCommand.HSET, arguments);
		}else{
			return execute((C jc)->ReturnUtils.statusForBool(jc.hset(key, field, value) > 0), ProtocolCommand.HSET,
					arguments);
		}
	}

	@Override
	public Status hSetNx(final String key, final String field, final String value){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"field", field).put("value", value);

		if(isTransaction()){
			return execute((C jc)->ReturnUtils.statusForBool(getTransaction().hsetnx(key, field, value).get() > 0),
					ProtocolCommand.HSETNX, arguments);
		}else{
			return execute((C jc)->ReturnUtils.statusForBool(jc.hsetnx(key, field, value) > 0), ProtocolCommand.HSETNX
					, arguments);
		}
	}

	@Override
	public String hGet(final String key, final String field){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"field", field);

		if(isTransaction()){
			return execute((C jc)->getTransaction().hget(key, field).get(), ProtocolCommand.HGET, arguments);
		}else{
			return execute((C jc)->jc.hget(key, field), ProtocolCommand.HGET, arguments);
		}
	}

	@Override
	public Status hMSet(final String key, final Map<String, String> data){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"data", data);

		if(isTransaction()){
			return execute((C jc)->ReturnUtils.statusForOK(getTransaction().hmset(key, data).get()),
					ProtocolCommand.HMSET, arguments);
		}else{
			return execute((C jc)->ReturnUtils.statusForOK(jc.hmset(key, data)), ProtocolCommand.HMSET, arguments);
		}
	}

	@Override
	public List<String> hMGet(final String key, final String... fields){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"fields", fields);

		if(isTransaction()){
			return execute((C jc)->getTransaction().hmget(key, fields).get(), ProtocolCommand.HMGET, arguments);
		}else{
			return execute((C jc)->jc.hmget(key, fields), ProtocolCommand.HMGET, arguments);
		}
	}

	@Override
	public Map<String, String> hGetAll(final String key){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key);

		if(isTransaction()){
			return execute((C jc)->getTransaction().hgetAll(key).get(), ProtocolCommand.HGETALL, arguments);
		}else{
			return execute((C jc)->jc.hgetAll(key), ProtocolCommand.HGETALL, arguments);
		}
	}

	@Override
	public Long hStrLen(final String key, final String field){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"field", field);

		if(isTransaction()){
			return execute((C jc)->getTransaction().hstrlen(key, field).get(), ProtocolCommand.HSTRLEN, arguments);
		}else{
			return execute((C jc)->jc.hstrlen(key, field), ProtocolCommand.HSTRLEN, arguments);
		}
	}

	@Override
	public Long hLen(final String key){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key);

		if(isTransaction()){
			return execute((C jc)->getTransaction().hlen(key).get(), ProtocolCommand.HLEN, arguments);
		}else{
			return execute((C jc)->jc.hlen(key), ProtocolCommand.HLEN, arguments);
		}
	}

	@Override
	public Long hIncrBy(final String key, final String field, final long value){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"field", field).put("value", value);

		if(isTransaction()){
			return execute((C jc)->getTransaction().hincrBy(key, field, value).get(), ProtocolCommand.HINCRBY,
					arguments);
		}else{
			return execute((C jc)->jc.hincrBy(key, field, value), ProtocolCommand.HINCRBY, arguments);
		}
	}

	@Override
	public Double hIncrByFloat(final String key, final String field, final double value){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"field", field).put("value", value);

		if(isTransaction()){
			return execute((C jc)->getTransaction().hincrByFloat(key, field, value).get(),
					ProtocolCommand.HINCRBYFLOAT, arguments);
		}else{
			return execute((C jc)->jc.hincrByFloat(key, field, value), ProtocolCommand.HINCRBYFLOAT, arguments);
		}
	}

	@Override
	public Long hDecrBy(final String key, final String field, final long value){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"field", field).put("value", value);

		final long val = value > 0 ? value * -1 : value;
		if(isTransaction()){
			return execute((C jc)->getTransaction().hincrBy(key, field, val).get(), ProtocolCommand.HINCRBY,
					arguments);
		}else{
			return execute((C jc)->jc.hincrBy(key, field, val), ProtocolCommand.HINCRBY, arguments);
		}
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final String cursor){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"cursor", cursor);

		return execute(new Executor<C, ScanResult<Map<String, String>>>() {

			@Override
			public ScanResult<Map<String, String>> execute(C jc){
				if(isTransaction()){
					throw new NotSupportedTransactionCommandException(ProtocolCommand.HSCAN);
				}else{
					return JedisClientUtils.mapScanResultConvert(jc.hscan(key, cursor));
				}
			}

		}, ProtocolCommand.HSCAN, arguments);
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final String cursor, final String pattern){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"cursor", cursor).put("pattern", pattern);

		return execute(new Executor<C, ScanResult<Map<String, String>>>() {

			@Override
			public ScanResult<Map<String, String>> execute(C jc){
				if(isTransaction()){
					throw new NotSupportedTransactionCommandException(ProtocolCommand.HSCAN);
				}else{
					return JedisClientUtils.mapScanResultConvert(jc.hscan(key, cursor, new JedisScanParams(pattern)));
				}
			}

		}, ProtocolCommand.HSCAN, arguments);
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final String cursor, final int count){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"cursor", cursor).put("count", count);

		return execute(new Executor<C, ScanResult<Map<String, String>>>() {

			@Override
			public ScanResult<Map<String, String>> execute(C jc){
				if(isTransaction()){
					throw new NotSupportedTransactionCommandException(ProtocolCommand.HSCAN);
				}else{
					return JedisClientUtils.mapScanResultConvert(jc.hscan(key, cursor, new JedisScanParams(count)));
				}
			}

		}, ProtocolCommand.HSCAN, arguments);
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final String cursor, final String pattern,
			final int count){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"cursor", cursor).put("pattern", pattern).put("count", count);

		return execute(new Executor<C, ScanResult<Map<String, String>>>() {

			@Override
			public ScanResult<Map<String, String>> execute(C jc){
				if(isTransaction()){
					throw new NotSupportedTransactionCommandException(ProtocolCommand.HSCAN);
				}else{
					return JedisClientUtils.mapScanResultConvert(jc.hscan(key, cursor, new JedisScanParams(pattern,
							count)));
				}
			}

		}, ProtocolCommand.HSCAN, arguments);
	}

	@Override
	public Long hDel(final String key, final String... fields){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key);

		if(isTransaction()){
			return execute((C jc)->getTransaction().hdel(key, fields).get(), ProtocolCommand.HDEL, arguments);
		}else{
			return execute((C jc)->jc.hdel(key, fields), ProtocolCommand.HDEL, arguments);
		}
	}

}
