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
package com.buession.redis;

import com.buession.core.serializer.type.TypeReference;
import com.buession.core.utils.Assert;
import com.buession.redis.client.RedisClient;
import com.buession.redis.client.connection.RedisConnection;
import com.buession.redis.client.connection.RedisConnectionFactory;
import com.buession.redis.client.connection.RedisConnectionUtils;
import com.buession.redis.client.connection.datasource.DataSource;
import com.buession.redis.client.connection.datasource.jedis.JedisClusterDataSource;
import com.buession.redis.client.connection.datasource.jedis.JedisDataSource;
import com.buession.redis.client.connection.datasource.jedis.JedisSentinelDataSource;
import com.buession.redis.client.jedis.JedisStandaloneClient;
import com.buession.redis.client.jedis.JedisClusterClient;
import com.buession.redis.client.jedis.JedisSentinelClient;
import com.buession.redis.core.Command;
import com.buession.redis.core.Options;
import com.buession.redis.core.SessionCallback;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.exception.RedisException;
import com.buession.redis.pipeline.Pipeline;
import com.buession.redis.serializer.JacksonJsonSerializer;
import com.buession.redis.serializer.Serializer;
import org.springframework.lang.Nullable;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author Yong.Teng
 */
public abstract class RedisAccessor implements AutoCloseable {

	protected final static Options DEFAULT_OPTIONS = new Options();

	protected final static Serializer DEFAULT_SERIALIZER = new JacksonJsonSerializer();

	protected Options options = DEFAULT_OPTIONS;

	protected Serializer serializer;

	protected DataSource dataSource;

	protected RedisConnectionFactory connectionFactory;

	protected boolean enableTransactionSupport = false;

	protected final static ThreadLocal<Map<Integer, Function<?, ?>>> txConverters = new ThreadLocal<>();

	protected final static ThreadLocal<Integer> index = new ThreadLocal<>();

	static{
		DEFAULT_OPTIONS.setSerializer(DEFAULT_SERIALIZER);
		index.set(-1);
	}

	/**
	 * 构造函数
	 */
	public RedisAccessor(){
	}

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		数据源
	 */
	public RedisAccessor(DataSource dataSource){
		setDataSource(dataSource);
	}

	public Options getOptions(){
		return options;
	}

	public void setOptions(Options options){
		this.options = options;
	}

	@Nullable
	public RedisConnectionFactory getConnectionFactory(){
		return connectionFactory;
	}

	@Nullable
	public DataSource getDataSource(){
		return dataSource;
	}

	public void setDataSource(DataSource dataSource){
		this.dataSource = dataSource;
	}

	public void afterPropertiesSet() throws RedisException{
		Assert.isNull(getDataSource(), "DataSource is required");

		Options options = getOptions();
		if(options != null){
			serializer = options.getSerializer();
			enableTransactionSupport = options.isEnableTransactionSupport();
		}
		if(serializer == null){
			serializer = DEFAULT_SERIALIZER;
		}

		connectionFactory = new RedisConnectionFactory(getDataSource());
	}

	public void pipeline(){
		RedisConnection connection = fetchConnection();

		RedisClient client = doGetRedisClient();
		client.setConnection(connection);
		client.execute(new Command<Pipeline>() {

			@Override
			public ProtocolCommand getCommand(){
				return null;
			}

			@Override
			public Pipeline execute() throws RedisException{
				return client.getConnection().openPipeline();
			}

			@Override
			public Pipeline run(final CommandArguments arguments) throws RedisException{
				return client.getConnection().openPipeline();
			}

		});
	}

	public <R> R execute(final SessionCallback<R> callback) throws RedisException{
		Assert.isNull(callback, "callback cloud not be null.");
		checkInitialized();

		RedisConnection connection = fetchConnection();

		RedisClient client = doGetRedisClient();
		client.setConnection(connection);

		if(isTransactionOrPipeline(connection)){
			index.set(index.get() + 1);
		}

		try{
			return callback.execute(client);
		}catch(Exception e){
			throw new RedisException(e.getMessage(), e);
		}finally{
			RedisConnectionUtils.releaseConnection(connectionFactory, connection, enableTransactionSupport);
		}
	}

	public <SR, TR> TR execute(final SessionCallback<SR> callback, final Converter<SR, TR> converter)
			throws RedisException{
		Assert.isNull(callback, "callback cloud not be null.");
		checkInitialized();

		RedisConnection connection = fetchConnection();

		RedisClient client = doGetRedisClient();
		client.setConnection(connection);

		if(isTransactionOrPipeline(connection)){
			index.set(index.get() + 1);
		}

		try{
			return converter.convert(connection, callback.execute(client));
		}catch(Exception e){
			throw new RedisException(e.getMessage(), e);
		}finally{
			RedisConnectionUtils.releaseConnection(connectionFactory, connection, enableTransactionSupport);
		}
	}

	@Override
	public void close() throws Exception{
		
	}

	protected RedisConnection fetchConnection(){
		if(enableTransactionSupport){
			return RedisConnectionUtils.bindConnection(connectionFactory, true);
		}else{
			return RedisConnectionUtils.getConnection(connectionFactory);
		}
	}

	protected RedisClient doGetRedisClient() throws RedisException{
		DataSource dataSource = getDataSource();
		if(dataSource instanceof JedisDataSource){
			return new JedisStandaloneClient();
		}else if(dataSource instanceof JedisSentinelDataSource){
			return new JedisSentinelClient();
		}else if(dataSource instanceof JedisClusterDataSource){
			return new JedisClusterClient();
		}else{
			throw new RedisException("Cloud not initialize RedisClient for: " + dataSource);
		}
	}

	protected final void checkInitialized(){
		if(connectionFactory == null){
			throw new RedisException(
					"RedisConnectionFactory is not initialized. You can call the afterPropertiesSet method for initialize.");
		}
	}

	protected boolean isTransaction(final RedisConnection connection){
		return connection.isTransaction();
	}

	protected boolean isPipeline(final RedisConnection connection){
		return connection.isPipeline();
	}

	protected boolean isTransactionOrPipeline(final RedisConnection connection){
		return isTransaction(connection) || isPipeline(connection);
	}

	protected static Map<Integer, Function<?, ?>> getTxConverters(){
		Map<Integer, Function<?, ?>> txResult = txConverters.get();

		if(txResult == null){
			txResult = new LinkedHashMap<>(16, 0.8F);
			txConverters.set(txResult);
		}

		return txResult;
	}

	protected void resetTransactionOrPipeline(){
		index.remove();
		txConverters.remove();
	}

	protected <V> Converter.SimpleStringConverter<V> stringObjectConverter(){
		return new Converter.SimpleStringConverter<>(this);
	}

	protected <V> Converter.SimpleBinaryConverter<V> binaryObjectConverter(){
		return new Converter.SimpleBinaryConverter<>(this);
	}

	protected <V> Converter.ClazzStringConverter<V> stringObjectConverter(final Class<V> clazz){
		return new Converter.ClazzStringConverter<>(this, clazz);
	}

	protected <V> Converter.ClazzBinaryConverter<V> binaryObjectConverter(final Class<V> clazz){
		return new Converter.ClazzBinaryConverter<>(this, clazz);
	}

	protected <V> Converter.TypeStringConverter<V> stringObjectConverter(final TypeReference<V> typeReference){
		return new Converter.TypeStringConverter<>(this, typeReference);
	}

	protected <V> Converter.TypeBinaryConverter<V> binaryObjectConverter(final TypeReference<V> typeReference){
		return new Converter.TypeBinaryConverter<>(this, typeReference);
	}

	protected <V> Converter.SimpleListStringConverter<V> listStringObjectConverter(){
		return new Converter.SimpleListStringConverter<>(this);
	}

	protected <V> Converter.SimpleListBinaryConverter<V> listBinaryObjectConverter(){
		return new Converter.SimpleListBinaryConverter<>(this);
	}

	protected <V> Converter.ClazzListStringConverter<V> listStringObjectConverter(final Class<V> clazz){
		return new Converter.ClazzListStringConverter<>(this, clazz);
	}

	protected <V> Converter.ClazzListBinaryConverter<V> listBinaryObjectConverter(final Class<V> clazz){
		return new Converter.ClazzListBinaryConverter<>(this, clazz);
	}

	protected <V> Converter.TypeListStringConverter<V> listStringObjectConverter(final TypeReference<V> typeReference){
		return new Converter.TypeListStringConverter<>(this, typeReference);
	}

	protected <V> Converter.TypeListBinaryConverter<V> listBinaryObjectConverter(final TypeReference<V> typeReference){
		return new Converter.TypeListBinaryConverter<>(this, typeReference);
	}

	protected <V> Converter.SimpleSetStringConverter<V> setStringObjectConverter(){
		return new Converter.SimpleSetStringConverter<>(this);
	}

	protected <V> Converter.SimpleSetBinaryConverter<V> setBinaryObjectConverter(){
		return new Converter.SimpleSetBinaryConverter<>(this);
	}

	protected <V> Converter.ClazzSetStringConverter<V> setStringObjectConverter(final Class<V> clazz){
		return new Converter.ClazzSetStringConverter<>(this, clazz);
	}

	protected <V> Converter.ClazzSetBinaryConverter<V> setBinaryObjectConverter(final Class<V> clazz){
		return new Converter.ClazzSetBinaryConverter<>(this, clazz);
	}

	protected <V> Converter.TypeSetStringConverter<V> setStringObjectConverter(final TypeReference<V> typeReference){
		return new Converter.TypeSetStringConverter<>(this, typeReference);
	}

	protected <V> Converter.TypeSetBinaryConverter<V> setBinaryObjectConverter(final TypeReference<V> typeReference){
		return new Converter.TypeSetBinaryConverter<>(this, typeReference);
	}

	protected <V> Converter.SimpleMapStringConverter<V> mapStringObjectConverter(){
		return new Converter.SimpleMapStringConverter<>(this);
	}

	protected <V> Converter.SimpleMapBinaryConverter<V> mapBinaryObjectConverter(){
		return new Converter.SimpleMapBinaryConverter<>(this);
	}

	protected <V> Converter.ClazzMapStringConverter<V> mapStringObjectConverter(final Class<V> clazz){
		return new Converter.ClazzMapStringConverter<>(this, clazz);
	}

	protected <V> Converter.ClazzMapBinaryConverter<V> mapBinaryObjectConverter(final Class<V> clazz){
		return new Converter.ClazzMapBinaryConverter<>(this, clazz);
	}

	protected <V> Converter.TypeMapStringConverter<V> mapStringObjectConverter(final TypeReference<V> typeReference){
		return new Converter.TypeMapStringConverter<>(this, typeReference);
	}

	protected <V> Converter.TypeMapBinaryConverter<V> mapBinaryObjectConverter(final TypeReference<V> typeReference){
		return new Converter.TypeMapBinaryConverter<>(this, typeReference);
	}

	protected <V> Converter.SimpleScanResultListStringConverter<V> scanResultListStringConverter(){
		return new Converter.SimpleScanResultListStringConverter<>(this);
	}

	protected <V> Converter.SimpleScanResultListBinaryConverter<V> scanResultListBinaryConverter(){
		return new Converter.SimpleScanResultListBinaryConverter<>(this);
	}

	protected <V> Converter.ClazzScanResultListStringConverter<V> scanResultListStringConverter(final Class<V> clazz){
		return new Converter.ClazzScanResultListStringConverter<>(this, clazz);
	}

	protected <V> Converter.ClazzScanResultListBinaryConverter<V> scanResultListBinaryConverter(final Class<V> clazz){
		return new Converter.ClazzScanResultListBinaryConverter<>(this, clazz);
	}

	protected <V> Converter.TypeScanResultListStringConverter<V> scanResultListStringConverter(
			final TypeReference<V> typeReference){
		return new Converter.TypeScanResultListStringConverter<>(this, typeReference);
	}

	protected <V> Converter.TypeScanResultListBinaryConverter<V> scanResultListBinaryConverter(
			final TypeReference<V> typeReference){
		return new Converter.TypeScanResultListBinaryConverter<>(this, typeReference);
	}

	protected <V> Converter.SimpleScanResultMapStringConverter<V> scanResultMapStringConverter(){
		return new Converter.SimpleScanResultMapStringConverter<>(this);
	}

	protected <V> Converter.SimpleScanResultMapBinaryConverter<V> scanResultMapBinaryConverter(){
		return new Converter.SimpleScanResultMapBinaryConverter<>(this);
	}

	protected <V> Converter.ClazzScanResultMapStringConverter<V> scanResultMapStringConverter(final Class<V> clazz){
		return new Converter.ClazzScanResultMapStringConverter<>(this, clazz);
	}

	protected <V> Converter.ClazzScanResultMapBinaryConverter<V> scanResultMapBinaryConverter(final Class<V> clazz){
		return new Converter.ClazzScanResultMapBinaryConverter<>(this, clazz);
	}

	protected <V> Converter.TypeScanResultMapStringConverter<V> scanResultMapStringConverter(
			final TypeReference<V> typeReference){
		return new Converter.TypeScanResultMapStringConverter<>(this, typeReference);
	}

	protected <V> Converter.TypeScanResultMapBinaryConverter<V> scanResultMapBinaryConverter(
			final TypeReference<V> typeReference){
		return new Converter.TypeScanResultMapBinaryConverter<>(this, typeReference);
	}

}
