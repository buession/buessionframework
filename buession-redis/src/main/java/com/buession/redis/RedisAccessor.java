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
package com.buession.redis;

import com.buession.core.utils.Assert;
import com.buession.redis.client.RedisClient;
import com.buession.redis.client.connection.jedis.JedisConnectionFactory;
import com.buession.redis.client.connection.lettuce.LettuceConnectionFactory;
import com.buession.redis.client.connection.RedisConnection;
import com.buession.redis.client.connection.RedisConnectionFactory;
import com.buession.redis.client.connection.RedisConnectionUtils;
import com.buession.redis.client.connection.datasource.DataSource;
import com.buession.redis.client.connection.datasource.jedis.JedisClusterDataSource;
import com.buession.redis.client.connection.datasource.jedis.JedisDataSource;
import com.buession.redis.client.connection.datasource.jedis.JedisSentinelDataSource;
import com.buession.redis.client.connection.datasource.lettuce.LettuceClusterDataSource;
import com.buession.redis.client.connection.datasource.lettuce.LettuceDataSource;
import com.buession.redis.client.connection.datasource.lettuce.LettuceSentinelDataSource;
import com.buession.redis.client.jedis.JedisStandaloneClient;
import com.buession.redis.client.jedis.JedisClusterClient;
import com.buession.redis.client.jedis.JedisSentinelClient;
import com.buession.redis.client.lettuce.LettuceClusterClient;
import com.buession.redis.client.lettuce.LettuceSentinelClient;
import com.buession.redis.client.lettuce.LettuceStandaloneClient;
import com.buession.redis.core.Command;
import com.buession.redis.core.Options;
import com.buession.redis.core.SessionCallback;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.exception.RedisException;
import com.buession.redis.pipeline.Pipeline;
import com.buession.redis.serializer.JacksonJsonSerializer;
import com.buession.redis.serializer.Serializer;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.lang.Nullable;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Base class for {@link RedisTemplate} defining common properties. Not intended to be used directly.
 *
 * @author Yong.Teng
 */
public abstract class RedisAccessor implements InitializingBean, AutoCloseable {

	protected final static Options DEFAULT_OPTIONS = new Options();

	/**
	 * 默认序列化
	 */
	protected final static Serializer DEFAULT_SERIALIZER = new JacksonJsonSerializer();

	/**
	 * 配置选项
	 */
	protected Options options = DEFAULT_OPTIONS;

	/**
	 * 序列化
	 */
	protected Serializer serializer;

	/**
	 * Redis 数据源
	 */
	protected DataSource dataSource;

	/**
	 * Redis 连接工厂
	 */
	protected RedisConnectionFactory connectionFactory;

	protected RedisClient client;

	/**
	 * 是否启用事务支持
	 */
	protected boolean enableTransactionSupport = false;

	protected final static ThreadLocal<Map<Integer, Function<?, ?>>> txConverters = new ThreadLocal<>();

	protected final static ThreadLocal<Integer> index = new ThreadLocal<>();

	static {
		DEFAULT_OPTIONS.setSerializer(DEFAULT_SERIALIZER);
		index.set(-1);
	}

	/**
	 * 构造函数
	 */
	public RedisAccessor() {
	}

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		数据源
	 */
	public RedisAccessor(DataSource dataSource) {
		setDataSource(dataSource);
	}

	/**
	 * 返回配置选项
	 *
	 * @return 配置选项
	 */
	public Options getOptions() {
		return options;
	}

	/**
	 * 设置配置选项
	 *
	 * @param options
	 * 		配置选项
	 */
	public void setOptions(Options options) {
		this.options = options;
	}

	/**
	 * 返回 Redis 数据源
	 *
	 * @return Redis 数据源
	 */
	@Nullable
	public DataSource getDataSource() {
		return dataSource;
	}

	/**
	 * 设置 Redis 数据源
	 *
	 * @param dataSource
	 * 		Redis 数据源
	 */
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/**
	 * 返回 Redis 连接工厂
	 *
	 * @return Redis 连接工厂
	 */
	@Nullable
	public RedisConnectionFactory getConnectionFactory() {
		return connectionFactory;
	}

	@Override
	public void afterPropertiesSet() throws RedisException {
		Assert.isNull(getDataSource(), "DataSource is required");

		Options options = getOptions();
		if(options != null){
			serializer = options.getSerializer();
			enableTransactionSupport = options.isEnableTransactionSupport();
		}
		if(serializer == null){
			serializer = DEFAULT_SERIALIZER;
		}

		if(connectionFactory == null){
			DataSource dataSource = getDataSource();

			if(dataSource instanceof LettuceDataSource){
				connectionFactory = new LettuceConnectionFactory((LettuceDataSource) dataSource);
			}else if(dataSource instanceof JedisDataSource){
				connectionFactory = new JedisConnectionFactory((JedisDataSource) dataSource);
			}else{
				throw new RedisException("dataSource must be an instance of " + JedisDataSource.class.getName() +
						" or " + JedisDataSource.class.getName() + ", but an instance of " +
						dataSource.getClass().getName() + ".");
			}
		}
	}

	public void pipeline() {
		RedisConnection connection = fetchRequiredConnection();
		RedisClient client = fetchRequiredRedisClient(connection);

		client.execute(new Command<Pipeline>() {

			@Override
			public ProtocolCommand getCommand() {
				return null;
			}

			@Override
			public Pipeline execute() throws RedisException {
				return client.getConnection().openPipeline();
			}

			@Override
			public Pipeline run(final CommandArguments arguments) throws RedisException {
				return execute();
			}

		});
	}

	public <R> R execute(final SessionCallback<R> callback) throws RedisException {
		Assert.isNull(callback, "callback cloud not be null.");

		RedisConnection connection = fetchRequiredConnection();
		RedisClient client = fetchRequiredRedisClient(connection);

		if(isMulti(connection)){
			index.set(index.get() + 1);
		}

		try{
			return callback.execute(client);
		}finally{
			RedisConnectionUtils.releaseConnection(connectionFactory, connection, enableTransactionSupport);
		}
	}

	public <SR, TR> TR execute(final SessionCallback<SR> callback, final Converter<SR, TR> converter)
			throws RedisException {
		Assert.isNull(callback, "callback cloud not be null.");

		RedisConnection connection = fetchRequiredConnection();
		RedisClient client = fetchRequiredRedisClient(connection);

		if(isMulti(connection)){
			index.set(index.get() + 1);
		}

		try{
			return converter.convert(connection, callback.execute(client));
		}finally{
			RedisConnectionUtils.releaseConnection(connectionFactory, connection, enableTransactionSupport);
		}
	}

	@Override
	public void close() throws Exception {
		// empty
	}

	protected RedisConnection fetchConnection() {
		return RedisConnectionUtils.getConnection(connectionFactory, enableTransactionSupport);
	}

	protected RedisConnection fetchRequiredConnection() {
		checkInitialized();
		return fetchConnection();
	}

	protected RedisClient fetchRedisClient(final RedisConnection connection) throws RedisException {
		DataSource dataSource = getDataSource();

		if(dataSource instanceof JedisDataSource){
			return new JedisStandaloneClient();
		}else if(dataSource instanceof JedisSentinelDataSource){
			return new JedisSentinelClient();
		}else if(dataSource instanceof JedisClusterDataSource){
			return new JedisClusterClient();
		}else if(dataSource instanceof LettuceDataSource){
			return new LettuceStandaloneClient();
		}else if(dataSource instanceof LettuceSentinelDataSource){
			return new LettuceSentinelClient();
		}else if(dataSource instanceof LettuceClusterDataSource){
			return new LettuceClusterClient();
		}else{
			throw new RedisException("Cloud not initialize RedisClient for: " + dataSource);
		}
	}

	protected RedisClient fetchRequiredRedisClient(final RedisConnection connection) throws RedisException {
		if(client == null){
			client = fetchRedisClient(connection);
		}

		client.setConnection(connection);

		return client;
	}

	protected final void checkInitialized() {
		Assert.isNull(connectionFactory, ()->new RedisException(
				"RedisConnectionFactory is not initialized. You can call the afterPropertiesSet method for initialize."));
	}

	protected boolean isTransaction(final RedisConnection connection) {
		return connection.isTransaction();
	}

	protected boolean isPipeline(final RedisConnection connection) {
		return connection.isPipeline();
	}

	@Deprecated
	protected boolean isTransactionOrPipeline(final RedisConnection connection) {
		return isMulti(connection);
	}

	protected boolean isMulti(final RedisConnection connection) {
		return isTransaction(connection) || isPipeline(connection);
	}

	protected static Map<Integer, Function<?, ?>> getTxConverters() {
		Map<Integer, Function<?, ?>> txResult = txConverters.get();

		if(txResult == null){
			txResult = new LinkedHashMap<>(16, 0.8F);
			txConverters.set(txResult);
		}

		return txResult;
	}

	protected void resetTransactionOrPipeline() {
		index.remove();
		txConverters.remove();
	}

}
