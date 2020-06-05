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
import redis.clients.jedis.commands.JedisCommands;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Yong.Teng
 */
public class DefaultJedisHashRedisOperations<C extends JedisCommands> extends AbstractJedisRedisOperations implements JedisHashRedisOperations {

	public DefaultJedisHashRedisOperations(final JedisRedisClient client){
		super(client);
	}

	@Override
	public boolean hExists(final String key, final String field){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("field",
				field);

		if(isTransaction()){
			return execute((C cmd)->getTransaction().hexists(key, field).get(), ProtocolCommand.HEXISTS, args);
		}else{
			return execute((C cmd)->cmd.hexists(key, field), ProtocolCommand.HEXISTS, args);
		}
	}

	@Override
	public Set<String> hKeys(final String key){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key);

		if(isTransaction()){
			return execute((C cmd)->getTransaction().hkeys(key).get(), ProtocolCommand.HKEYS, args);
		}else{
			return execute((C cmd)->cmd.hkeys(key), ProtocolCommand.HKEYS, args);
		}
	}

	@Override
	public List<String> hVals(final String key){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key);

		if(isTransaction()){
			return execute((C cmd)->getTransaction().hvals(key).get(), ProtocolCommand.HVALS, args);
		}else{
			return execute((C cmd)->cmd.hvals(key), ProtocolCommand.HVALS, args);
		}
	}

	@Override
	public Status hSet(final String key, final String field, final String value){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("field",
				field).put("value", value);

		if(isTransaction()){
			return execute((C cmd)->statusForBool(getTransaction().hset(key, field, value).get() > 0),
					ProtocolCommand.HSET, args);
		}else{
			return execute((C cmd)->statusForBool(cmd.hset(key, field, value) > 0), ProtocolCommand.HSET, args);
		}
	}

	@Override
	public Status hSetNx(final String key, final String field, final String value){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("field",
				field).put("value", value);

		if(isTransaction()){
			return execute((C cmd)->statusForBool(getTransaction().hsetnx(key, field, value).get() > 0),
					ProtocolCommand.HSETNX, args);
		}else{
			return execute((C cmd)->statusForBool(cmd.hsetnx(key, field, value) > 0), ProtocolCommand.HSETNX, args);
		}
	}

	@Override
	public String hGet(final String key, final String field){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("field",
				field);

		if(isTransaction()){
			return execute((C cmd)->getTransaction().hget(key, field).get(), ProtocolCommand.HGET, args);
		}else{
			return execute((C cmd)->cmd.hget(key, field), ProtocolCommand.HGET, args);
		}
	}

	@Override
	public Status hMSet(final String key, final Map<String, String> data){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("data",
				data);

		if(isTransaction()){
			return execute((C cmd)->statusForOK(getTransaction().hmset(key, data).get()), ProtocolCommand.HMSET, args);
		}else{
			return execute((C cmd)->statusForOK(cmd.hmset(key, data)), ProtocolCommand.HMSET, args);
		}
	}

	@Override
	public List<String> hMGet(final String key, final String... fields){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("fields",
				fields);

		if(isTransaction()){
			return execute((C cmd)->getTransaction().hmget(key, fields).get(), ProtocolCommand.HMGET, args);
		}else{
			return execute((C cmd)->cmd.hmget(key, fields), ProtocolCommand.HMGET, args);
		}
	}

	@Override
	public Map<String, String> hGetAll(final String key){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key);

		if(isTransaction()){
			return execute((C cmd)->getTransaction().hgetAll(key).get(), ProtocolCommand.HGETALL, args);
		}else{
			return execute((C cmd)->cmd.hgetAll(key), ProtocolCommand.HGETALL, args);
		}
	}

	@Override
	public Long hStrLen(final String key, final String field){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("field",
				field);

		if(isTransaction()){
			return execute((C cmd)->getTransaction().hstrlen(key, field).get(), ProtocolCommand.HSTRLEN, args);
		}else{
			return execute((C cmd)->cmd.hstrlen(key, field), ProtocolCommand.HSTRLEN, args);
		}
	}

	@Override
	public Long hLen(final String key){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key);

		if(isTransaction()){
			return execute((C cmd)->getTransaction().hlen(key).get(), ProtocolCommand.HLEN, args);
		}else{
			return execute((C cmd)->cmd.hlen(key), ProtocolCommand.HLEN, args);
		}
	}

	@Override
	public Long hIncrBy(final String key, final String field, final long value){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("field",
				field).put("value", value);

		if(isTransaction()){
			return execute((C cmd)->getTransaction().hincrBy(key, field, value).get(), ProtocolCommand.HINCRBY, args);
		}else{
			return execute((C cmd)->cmd.hincrBy(key, field, value), ProtocolCommand.HINCRBY, args);
		}
	}

	@Override
	public Double hIncrByFloat(final String key, final String field, final double value){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("field",
				field).put("value", value);

		if(isTransaction()){
			return execute((C cmd)->getTransaction().hincrByFloat(key, field, value).get(),
					ProtocolCommand.HINCRBYFLOAT, args);
		}else{
			return execute((C cmd)->cmd.hincrByFloat(key, field, value), ProtocolCommand.HINCRBYFLOAT, args);
		}
	}

	@Override
	public Long hDecrBy(final String key, final String field, final long value){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("field",
				field).put("value", value);

		final long val = value > 0 ? value * -1 : value;
		if(isTransaction()){
			return execute((C cmd)->getTransaction().hincrBy(key, field, val).get(), ProtocolCommand.HINCRBY, args);
		}else{
			return execute((C cmd)->cmd.hincrBy(key, field, val), ProtocolCommand.HINCRBY, args);
		}
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final String cursor){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("cursor",
				cursor);

		return execute(new Executor<C, ScanResult<Map<String, String>>>() {

			@Override
			public ScanResult<Map<String, String>> execute(C cmd){
				if(isTransaction()){
					throw new NotSupportedTransactionCommandException(ProtocolCommand.HSCAN);
				}else{
					return JedisClientUtils.mapScanResultConvert(cmd.hscan(key, cursor));
				}
			}

		}, ProtocolCommand.HSCAN, args);
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final String cursor, final String pattern){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("cursor",
				cursor).put("pattern", pattern);

		return execute(new Executor<C, ScanResult<Map<String, String>>>() {

			@Override
			public ScanResult<Map<String, String>> execute(C cmd){
				if(isTransaction()){
					throw new NotSupportedTransactionCommandException(ProtocolCommand.HSCAN);
				}else{
					return JedisClientUtils.mapScanResultConvert(cmd.hscan(key, cursor, new JedisScanParams(pattern)));
				}
			}

		}, ProtocolCommand.HSCAN, args);
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final String cursor, final int count){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("cursor",
				cursor).put("count", count);

		return execute(new Executor<C, ScanResult<Map<String, String>>>() {

			@Override
			public ScanResult<Map<String, String>> execute(C cmd){
				if(isTransaction()){
					throw new NotSupportedTransactionCommandException(ProtocolCommand.HSCAN);
				}else{
					return JedisClientUtils.mapScanResultConvert(cmd.hscan(key, cursor, new JedisScanParams(count)));
				}
			}

		}, ProtocolCommand.HSCAN, args);
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final String cursor, final String pattern,
			final int count){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("cursor",
				cursor).put("pattern", pattern).put("count", count);

		return execute(new Executor<C, ScanResult<Map<String, String>>>() {

			@Override
			public ScanResult<Map<String, String>> execute(C cmd){
				if(isTransaction()){
					throw new NotSupportedTransactionCommandException(ProtocolCommand.HSCAN);
				}else{
					return JedisClientUtils.mapScanResultConvert(cmd.hscan(key, cursor, new JedisScanParams(pattern,
							count)));
				}
			}

		}, ProtocolCommand.HSCAN, args);
	}

	@Override
	public Long hDel(final String key, final String... fields){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key);

		if(isTransaction()){
			return execute((C cmd)->getTransaction().hdel(key, fields).get(), ProtocolCommand.HDEL, args);
		}else{
			return execute((C cmd)->cmd.hdel(key, fields), ProtocolCommand.HDEL, args);
		}
	}

}
