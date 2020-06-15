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
package com.buession.redis;

import com.buession.core.serializer.type.TypeReference;
import com.buession.core.utils.Assert;
import com.buession.redis.client.RedisClient;
import com.buession.redis.client.connection.RedisConnection;
import com.buession.redis.client.connection.jedis.JedisConnection;
import com.buession.redis.client.connection.jedis.ShardedJedisConnection;
import com.buession.redis.client.jedis.JedisClient;
import com.buession.redis.client.jedis.ShardedJedisClient;
import com.buession.redis.core.Executor;
import com.buession.redis.core.Options;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.exception.RedisException;
import com.buession.redis.serializer.JacksonJsonSerializer;
import com.buession.redis.serializer.Serializer;
import com.buession.redis.utils.KeyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Yong.Teng
 */
public abstract class RedisAccessor {

	protected final static Options DEFAULT_OPTIONS = new Options();

	protected final static Serializer DEFAULT_SERIALIZER = new JacksonJsonSerializer();

	protected Options options = DEFAULT_OPTIONS;

	protected Serializer serializer;

	protected RedisConnection connection;

	protected RedisClient client;

	protected boolean enableTransactionSupport = false;

	private final static Logger logger = LoggerFactory.getLogger(RedisAccessor.class);

	{
		DEFAULT_OPTIONS.setSerializer(DEFAULT_SERIALIZER);
	}

	public RedisAccessor(){
	}

	public RedisAccessor(RedisConnection connection){
		this.connection = connection;
	}

	public Options getOptions(){
		return options;
	}

	public void setOptions(Options options){
		this.options = options;
	}

	@Deprecated
	public Serializer getSerializer(){
		return serializer;
	}

	@Deprecated
	public void setSerializer(Serializer serializer){
		this.serializer = serializer;
	}

	public RedisConnection getConnection(){
		return connection;
	}

	public void setConnection(RedisConnection connection){
		this.connection = connection;
	}

	public RedisClient getClient(){
		return client;
	}

	public void afterPropertiesSet() throws RedisException{
		Assert.isNull(connection, "RedisConnection is required");

		Options options = getOptions();
		if(options != null){
			serializer = options.getSerializer();
		}
		if(serializer == null){
			serializer = DEFAULT_SERIALIZER;
		}

		enableTransactionSupport = options.isEnableTransactionSupport();

		client = doGetRedisClient(connection);
	}

	protected <R> R execute(final Executor<R> executor, final ProtocolCommand command){
		return execute(executor, command, null);
	}

	protected <R> R execute(final Executor<R> executor, final ProtocolCommand command,
			final CommandArguments arguments){
		String argumentsString = logger.isDebugEnabled() && arguments != null ? arguments.toString() : null;

		if(logger.isDebugEnabled()){
			if(arguments != null){
				logger.debug("Execute command '{}' width arguments: {}", command, argumentsString);
			}else{
				logger.debug("Execute command '{}'", command);
			}
		}

		try{
			return executor.execute(client);
		}catch(Exception e){
			if(logger.isDebugEnabled()){
				if(arguments != null){
					logger.error("Execute command '{}' width arguments: {}, failure: {}", command, argumentsString,
							e.getMessage());
				}else{
					logger.error("Execute command '{}', failure: {}", command, e.getMessage());
				}
			}
			throw e;
		}
	}

	protected RedisClient doGetRedisClient(RedisConnection connection) throws RedisException{
		if(connection instanceof JedisConnection){
			JedisClient jedisClient = new JedisClient(connection);

			jedisClient.setEnableTransactionSupport(enableTransactionSupport);
			jedisClient.setConnection(connection);

			return jedisClient;
		}else if(connection instanceof ShardedJedisConnection){
			ShardedJedisClient shardedJedisClient = new ShardedJedisClient(connection);

			shardedJedisClient.setEnableTransactionSupport(enableTransactionSupport);
			shardedJedisClient.setConnection(connection);

			return shardedJedisClient;
		}else{
			throw new RedisException("Cloud not initialize RedisClient for: " + connection);
		}
	}

	protected final String makeRawKey(final String key){
		return KeyUtil.makeRawKey(getOptions().getPrefix(), key);
	}

	protected final String[] makeRawKeys(final String[] keys){
		return KeyUtil.makeRawKeys(getOptions().getPrefix(), keys);
	}

	protected final byte[] makeByteKey(byte[] key){
		return KeyUtil.makeByteKey(getOptions().getPrefix(), key);
	}

	protected final byte[][] makeByteKeys(final byte[][] keys){
		return KeyUtil.makeByteKeys(getOptions().getPrefix(), keys);
	}

	protected <V> String serialize(final V object){
		return serializer.serialize(object);
	}

	protected <V> String[] serialize(final V[] objects){
		return serializer.serialize(objects);
	}

	protected <V> byte[] serializeAsBytes(final V object){
		return serializer.serializeAsBytes(object);
	}

	protected <V> byte[][] serializeAsBytes(final V[] objects){
		return serializer.serializeAsBytes(objects);
	}

	protected <V> V deserialize(final String str){
		return serializer.deserialize(str);
	}

	protected <V> V deserializeBytes(final byte[] bytes){
		return serializer.deserializeBytes(bytes);
	}

	protected <V> List<V> deserialize(final List<String> str){
		return serializer.deserialize(str);
	}

	protected <V> List<V> deserializeBytes(final List<byte[]> bytes){
		return serializer.deserializeBytes(bytes);
	}

	protected <V> Set<V> deserialize(final Set<String> str){
		return serializer.deserialize(str);
	}

	protected <V> Set<V> deserializeBytes(final Set<byte[]> bytes){
		return serializer.deserializeBytes(bytes);
	}

	protected <V> Map<String, V> deserialize(final Map<String, String> str){
		return serializer.deserialize(str);
	}

	protected <V> Map<byte[], V> deserializeBytes(final Map<byte[], byte[]> bytes){
		return serializer.deserializeBytes(bytes);
	}

	protected <V> V deserialize(final String str, final Class<V> clazz){
		return serializer.deserialize(str, clazz);
	}

	protected <V> V deserializeBytes(final byte[] bytes, final Class<V> clazz){
		return serializer.deserializeBytes(bytes, clazz);
	}

	protected <V> List<V> deserialize(final List<String> str, final Class<V> clazz){
		return serializer.deserialize(str, clazz);
	}

	protected <V> List<V> deserializeBytes(final List<byte[]> bytes, final Class<V> clazz){
		return serializer.deserializeBytes(bytes, clazz);
	}

	protected <V> Set<V> deserialize(final Set<String> str, final Class<V> clazz){
		return serializer.deserialize(str, clazz);
	}

	protected <V> Set<V> deserializeBytes(final Set<byte[]> bytes, final Class<V> clazz){
		return serializer.deserializeBytes(bytes, clazz);
	}

	protected <V> Map<String, V> deserialize(final Map<String, String> str, final Class<V> clazz){
		return serializer.deserialize(str, clazz);
	}

	protected <V> Map<byte[], V> deserializeBytes(final Map<byte[], byte[]> bytes, final Class<V> clazz){
		return serializer.deserializeBytes(bytes, clazz);
	}

	protected <V> V deserialize(final String str, final TypeReference<V> type){
		return serializer.deserialize(str, type);
	}

	protected <V> V deserializeBytes(final byte[] bytes, final TypeReference<V> type){
		return serializer.deserializeBytes(bytes, type);
	}

	protected <V> List<V> deserialize(final List<String> str, final TypeReference<V> type){
		return serializer.deserialize(str, type);
	}

	protected <V> List<V> deserializeBytes(final List<byte[]> bytes, final TypeReference<V> type){
		return serializer.deserializeBytes(bytes, type);
	}

	protected <V> Set<V> deserialize(final Set<String> str, final TypeReference<V> type){
		return serializer.deserialize(str, type);
	}

	protected <V> Set<V> deserializeBytes(final Set<byte[]> bytes, final TypeReference<V> type){
		return serializer.deserializeBytes(bytes, type);
	}

	protected <V> Map<String, V> deserialize(final Map<String, String> str, final TypeReference<V> type){
		return serializer.deserialize(str, type);
	}

	protected <V> Map<byte[], V> deserializeBytes(final Map<byte[], byte[]> bytes, final TypeReference<V> type){
		return serializer.deserializeBytes(bytes, type);
	}

}
