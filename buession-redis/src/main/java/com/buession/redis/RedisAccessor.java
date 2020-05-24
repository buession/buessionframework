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

import com.buession.core.utils.Assert;
import com.buession.redis.client.RedisClient;
import com.buession.redis.client.connection.RedisConnection;
import com.buession.redis.client.connection.jedis.JedisPoolConnection;
import com.buession.redis.client.connection.jedis.ShardedJedisConnection;
import com.buession.redis.client.connection.jedis.SimpleJedisConnection;
import com.buession.redis.client.connection.jedis.SimpleShardedJedisConnection;
import com.buession.redis.client.jedis.JedisClient;
import com.buession.redis.client.jedis.ShardedJedisClient;
import com.buession.redis.core.Executor;
import com.buession.redis.core.Options;
import com.buession.redis.exception.RedisException;
import com.buession.redis.serializer.JacksonJsonSerializer;
import com.buession.redis.serializer.Serializer;
import com.buession.redis.utils.KeyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

	protected <R> R execute(final Executor<R> executor){
		try{
			return executor.execute(client);
		}catch(Exception e){
			logger.error("Execute executor failure: {}", e.getMessage(), e);
			throw e;
		}
	}

	protected RedisClient doGetRedisClient(RedisConnection connection) throws RedisException{
		if((connection instanceof SimpleJedisConnection) || (connection instanceof JedisPoolConnection)){
			JedisClient jedisClient = new JedisClient(connection);

			jedisClient.setEnableTransactionSupport(enableTransactionSupport);
			jedisClient.setConnection(connection);

			return jedisClient;
		}else if((connection instanceof SimpleShardedJedisConnection) || (connection instanceof ShardedJedisConnection)){
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

	protected final String[] makeRawKeys(final String... keys){
		return KeyUtil.makeRawKeys(getOptions().getPrefix(), keys);
	}

	protected final byte[] makeByteKey(byte[] key){
		return KeyUtil.makeByteKey(getOptions().getPrefix(), key);
	}

	protected final byte[][] makeByteKeys(final byte[]... keys){
		return KeyUtil.makeByteKeys(getOptions().getPrefix(), keys);
	}

	protected <V> String[] serializer(final V... values){
		if(values == null){
			return null;
		}else{
			final String[] temp = new String[values.length];

			for(int i = 0; i < values.length; i++){
				temp[i] = serializer.serialize(values[i]);
			}

			return temp;
		}
	}

	protected <V> byte[][] serializerAsByte(final V... values){
		if(values == null){
			return null;
		}else{
			final byte[][] temp = new byte[values.length][];

			for(int i = 0; i < values.length; i++){
				temp[i] = serializer.serializeAsBytes(values[i]);
			}

			return temp;
		}
	}

}
