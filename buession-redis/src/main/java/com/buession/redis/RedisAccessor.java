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

import com.buession.core.Executor;
import com.buession.core.serializer.type.TypeReference;
import com.buession.core.utils.Assert;
import com.buession.redis.client.RedisClient;
import com.buession.redis.client.connection.RedisConnection;
import com.buession.redis.client.connection.jedis.JedisConnection;
import com.buession.redis.client.connection.jedis.ShardedJedisConnection;
import com.buession.redis.client.jedis.JedisClient;
import com.buession.redis.client.jedis.ShardedJedisClient;
import com.buession.redis.core.Options;
import com.buession.redis.core.SessionCallback;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.exception.RedisException;
import com.buession.redis.pipeline.Pipeline;
import com.buession.redis.serializer.JacksonJsonSerializer;
import com.buession.redis.serializer.Serializer;
import com.buession.redis.utils.KeyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;

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

	protected ThreadLocal<Integer> index = new ThreadLocal<>();

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

	public Pipeline pipeline(){
		return execute((cmd)->client.pipeline());
	}

	public <V> V execute(SessionCallback<V> callback) throws RedisException{
		Assert.isNull(callback, "callback cloud not be null.");

		try{
			return execute(callback, null, null);
		}catch(Exception e){
			throw new RedisException(e.getMessage(), e);
		}
	}

	protected <R> R execute(final Executor<RedisClient, R> executor, final ProtocolCommand command){
		return execute(executor, command, null);
	}

	protected <R> R execute(final Executor<RedisClient, R> executor, final ProtocolCommand command,
			final CommandArguments arguments){
		checkInitialized();

		String argumentsString = logger.isDebugEnabled() && arguments != null ? arguments.toString() : null;

		if(logger.isDebugEnabled()){
			if(arguments != null){
				logger.debug("Execute command '{}' width arguments: {}", command, argumentsString);
			}else{
				logger.debug("Execute command '{}'", command);
			}
		}

		if(isPipeline() || isTransaction()){
			updateIndex();
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
			JedisClient jedisClient = new JedisClient((JedisConnection) connection);

			jedisClient.setEnableTransactionSupport(enableTransactionSupport);
			jedisClient.setConnection(connection);

			return jedisClient;
		}else if(connection instanceof ShardedJedisConnection){
			ShardedJedisClient shardedJedisClient = new ShardedJedisClient((ShardedJedisConnection) connection);

			shardedJedisClient.setEnableTransactionSupport(enableTransactionSupport);
			shardedJedisClient.setConnection(connection);

			return shardedJedisClient;
		}else{
			throw new RedisException("Cloud not initialize RedisClient for: " + connection);
		}
	}

	protected final void checkInitialized(){
		if(client == null){
			throw new RedisException("RedisClient is not initialized. You can call the afterPropertiesSet method for " + "initialization.");
		}
	}

	protected boolean isTransaction(){
		return getConnection().isTransaction();
	}

	protected boolean isPipeline(){
		return getConnection().isPipeline();
	}

	protected void updateIndex(){
		Integer currentValue = index.get();

		if(currentValue == null){
			index.set(1);
		}else{
			index.set(currentValue.intValue() + 1);
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

	protected final static class TxResult<S, T> {

		private Converter<S, T> converter;

		private Class[] paramTypes;

		public TxResult(Converter<S, T> converter){
			this(converter, null);
		}

		public TxResult(Converter<S, T> converter, Class... paramTypes){
			this.converter = converter;
			this.paramTypes = paramTypes;
		}

		public Converter<S, T> getConverter(){
			return converter;
		}

		public Class[] getParamTypes(){
			return paramTypes;
		}

	}

	protected final static class StringDeserialize<V> implements Converter<String, V> {

		protected Serializer serializer;

		protected Class<V> clazz;

		protected TypeReference<V> type;

		public StringDeserialize(Serializer serializer){
			this.serializer = serializer;
		}

		public StringDeserialize(Serializer serializer, Class<V> clazz){
			this.serializer = serializer;
			this.clazz = clazz;
		}

		public StringDeserialize(Serializer serializer, TypeReference<V> type){
			this.serializer = serializer;
			this.type = type;
		}

		@Override
		public V convert(String source){
			if(type != null){
				return serializer.deserialize(source, type);
			}else if(clazz != null){
				return serializer.deserialize(source, clazz);
			}else{
				return serializer.deserialize(source);
			}
		}

	}

	protected final static class BinaryDeserialize<V> implements Converter<byte[], V> {

		protected Serializer serializer;

		protected Class<V> clazz;

		protected TypeReference<V> type;

		public BinaryDeserialize(Serializer serializer){
			this.serializer = serializer;
		}

		public BinaryDeserialize(Serializer serializer, Class<V> clazz){
			this.serializer = serializer;
			this.clazz = clazz;
		}

		public BinaryDeserialize(Serializer serializer, TypeReference<V> type){
			this.serializer = serializer;
			this.type = type;
		}

		@Override
		public V convert(byte[] source){
			if(type != null){
				return serializer.deserializeBytes(source, type);
			}else if(clazz != null){
				return serializer.deserializeBytes(source, clazz);
			}else{
				return serializer.deserializeBytes(source);
			}
		}

	}

	protected final static class StringMapDeserialize<V> implements Converter<Map<String, String>, Map<String, V>> {

		protected Serializer serializer;

		protected Class<V> clazz;

		protected TypeReference<V> type;

		public StringMapDeserialize(Serializer serializer){
			this.serializer = serializer;
		}

		public StringMapDeserialize(Serializer serializer, Class<V> clazz){
			this.serializer = serializer;
			this.clazz = clazz;
		}

		public StringMapDeserialize(Serializer serializer, TypeReference<V> type){
			this.serializer = serializer;
			this.type = type;
		}

		@Override
		public Map<String, V> convert(Map<String, String> source){
			if(type != null){
				return serializer.deserialize(source, type);
			}else if(clazz != null){
				return serializer.deserialize(source, clazz);
			}else{
				return serializer.deserialize(source);
			}
		}

	}

	protected final static class BinaryMapDeserialize<V> implements Converter<Map<byte[], byte[]>, Map<byte[], V>> {

		protected Serializer serializer;

		protected Class<V> clazz;

		protected TypeReference<V> type;

		public BinaryMapDeserialize(Serializer serializer){
			this.serializer = serializer;
		}

		public BinaryMapDeserialize(Serializer serializer, Class<V> clazz){
			this.serializer = serializer;
			this.clazz = clazz;
		}

		public BinaryMapDeserialize(Serializer serializer, TypeReference<V> type){
			this.serializer = serializer;
			this.type = type;
		}

		@Override
		public Map<byte[], V> convert(Map<byte[], byte[]> source){
			if(type != null){
				return serializer.deserializeBytes(source, type);
			}else if(clazz != null){
				return serializer.deserializeBytes(source, clazz);
			}else{
				return serializer.deserializeBytes(source);
			}
		}

	}

	protected final static class StringListDeserialize<V> implements Converter<List<String>, List<V>> {

		protected Serializer serializer;

		protected Class<V> clazz;

		protected TypeReference<V> type;

		public StringListDeserialize(Serializer serializer){
			this.serializer = serializer;
		}

		public StringListDeserialize(Serializer serializer, Class<V> clazz){
			this.serializer = serializer;
			this.clazz = clazz;
		}

		public StringListDeserialize(Serializer serializer, TypeReference<V> type){
			this.serializer = serializer;
			this.type = type;
		}

		@Override
		public List<V> convert(List<String> source){
			if(type != null){
				return serializer.deserialize(source, type);
			}else if(clazz != null){
				return serializer.deserialize(source, clazz);
			}else{
				return serializer.deserialize(source);
			}
		}

	}

	protected final static class BinaryListDeserialize<V> implements Converter<List<byte[]>, List<V>> {

		protected Serializer serializer;

		protected Class<V> clazz;

		protected TypeReference<V> type;

		public BinaryListDeserialize(Serializer serializer){
			this.serializer = serializer;
		}

		public BinaryListDeserialize(Serializer serializer, Class<V> clazz){
			this.serializer = serializer;
			this.clazz = clazz;
		}

		public BinaryListDeserialize(Serializer serializer, TypeReference<V> type){
			this.serializer = serializer;
			this.type = type;
		}

		@Override
		public List<V> convert(List<byte[]> source){
			if(type != null){
				return serializer.deserializeBytes(source, type);
			}else if(clazz != null){
				return serializer.deserializeBytes(source, clazz);
			}else{
				return serializer.deserializeBytes(source);
			}
		}

	}

	protected final static class StringSetDeserialize<V> implements Converter<List<String>, List<V>> {

		protected Serializer serializer;

		protected Class<V> clazz;

		protected TypeReference<V> type;

		public StringSetDeserialize(Serializer serializer){
			this.serializer = serializer;
		}

		public StringSetDeserialize(Serializer serializer, Class<V> clazz){
			this.serializer = serializer;
			this.clazz = clazz;
		}

		public StringSetDeserialize(Serializer serializer, TypeReference<V> type){
			this.serializer = serializer;
			this.type = type;
		}

		@Override
		public List<V> convert(List<String> source){
			if(type != null){
				return serializer.deserialize(source, type);
			}else if(clazz != null){
				return serializer.deserialize(source, clazz);
			}else{
				return serializer.deserialize(source);
			}
		}

	}

	protected final static class BinarySetDeserialize<V> implements Converter<List<byte[]>, List<V>> {

		protected Serializer serializer;

		protected Class<V> clazz;

		protected TypeReference<V> type;

		public BinarySetDeserialize(Serializer serializer){
			this.serializer = serializer;
		}

		public BinarySetDeserialize(Serializer serializer, Class<V> clazz){
			this.serializer = serializer;
			this.clazz = clazz;
		}

		public BinarySetDeserialize(Serializer serializer, TypeReference<V> type){
			this.serializer = serializer;
			this.type = type;
		}

		@Override
		public List<V> convert(List<byte[]> source){
			if(type != null){
				return serializer.deserializeBytes(source, type);
			}else if(clazz != null){
				return serializer.deserializeBytes(source, clazz);
			}else{
				return serializer.deserializeBytes(source);
			}
		}

	}

}
