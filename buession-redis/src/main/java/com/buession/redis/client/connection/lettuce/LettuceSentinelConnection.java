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
package com.buession.redis.client.connection.lettuce;

import com.buession.core.utils.Assert;
import com.buession.core.validator.Validate;
import com.buession.lang.Status;
import com.buession.redis.client.connection.RedisSentinelConnection;
import com.buession.redis.client.connection.RedisSentinelNode;
import com.buession.redis.client.connection.datasource.lettuce.LettuceSentinelDataSource;
import com.buession.redis.core.Constants;
import com.buession.redis.core.PoolConfig;
import com.buession.redis.core.RedisNode;
import com.buession.redis.core.RedisServer;
import com.buession.redis.core.Role;
import com.buession.redis.exception.RedisConnectionFailureException;
import com.buession.redis.transaction.Transaction;
import com.buession.redis.utils.SafeEncoder;
import io.lettuce.core.DefaultLettuceClientConfig;
import io.lettuce.core.LettuceClientConfig;
import io.lettuce.core.RedisSentinelClient;
import io.lettuce.core.api.sync.RedisCommands;
import io.lettuce.core.builders.SentinelClientBuilder;
import io.lettuce.core.codec.RedisCodec;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * Lettuce 哨兵模式连接器
 *
 * @param <K>
 * 		Key 类型
 * @param <V>
 * 		值类型
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public class LettuceSentinelConnection<K, V> extends AbstractLettuceRedisConnection<K, V, RedisSentinelClient<K, V>>
		implements RedisSentinelConnection {

	/**
	 * 哨兵节点连接超时（单位：毫秒）
	 */
	private int sentinelConnectTimeout = Constants.DEFAULT_CONNECT_TIMEOUT;

	/**
	 * 哨兵节点读取超时（单位：毫秒）
	 */
	private int sentinelSoTimeout = Constants.DEFAULT_SO_TIMEOUT;

	/**
	 * 构造函数
	 */
	public LettuceSentinelConnection() {
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		Redis 数据源
	 */
	public LettuceSentinelConnection(LettuceSentinelDataSource dataSource) {
		super(dataSource);
	}

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		Redis 数据源
	 * @param poolConfig
	 * 		连接池配置
	 */
	public LettuceSentinelConnection(LettuceSentinelDataSource dataSource, PoolConfig poolConfig) {
		super(dataSource, poolConfig);
	}

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		Redis 数据源
	 * @param codec
	 * 		Redis 编解码器
	 */
	public LettuceSentinelConnection(LettuceSentinelDataSource dataSource, RedisCodec<K, V> codec) {
		super(dataSource, codec);
	}

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		Redis 数据源
	 * @param poolConfig
	 * 		连接池配置
	 * @param codec
	 * 		Redis 编解码器
	 */
	public LettuceSentinelConnection(LettuceSentinelDataSource dataSource, PoolConfig poolConfig,
	                                 RedisCodec<K, V> codec) {
		super(dataSource, poolConfig, codec);
	}

	@Override
	public int getSentinelConnectTimeout() {
		return sentinelConnectTimeout;
	}

	@Override
	public void setSentinelConnectTimeout(int sentinelConnectTimeout) {
		this.sentinelConnectTimeout = sentinelConnectTimeout;
	}

	@Override
	public int getSentinelSoTimeout() {
		return sentinelSoTimeout;
	}

	@Override
	public void setSentinelSoTimeout(int sentinelSoTimeout) {
		this.sentinelSoTimeout = sentinelSoTimeout;
	}

	@Override
	public String myId() {
		return client.myId();
	}

	@Override
	public List<RedisNode> sentinels(String masterName) {
		return client.sentinels(masterName);
	}

	@Override
	public String sentinelSet(String masterName, Map<String, String> parameters) {
		Assert.isBlank(masterName, "Redis master name cloud not be 'null' or empty for loading sentinel set.");
		return client.sentinelSet(masterName, parameters);
	}

	@Override
	public List<RedisServer> masters() {
		return client.masters();
	}

	@Override
	public RedisServer master(String masterName) {
		Assert.isBlank(masterName, "Redis master name cloud not be 'null' or empty for loading master.");
		return client.master(masterName);
	}

	@Override
	public List<RedisServer> slaves(String masterName) {
		Assert.isBlank(masterName, "Redis master name cloud not be 'null' or empty for loading slaves.");
		return client.slaves(masterName);
	}

	@Override
	public List<RedisServer> replicas(String masterName) {
		Assert.isBlank(masterName, "Redis master name cloud not be 'null' or empty for loading replicas.");
		return client.replicas(masterName);
	}

	@Override
	public Status failover(String masterName) {
		Assert.isBlank(masterName, "Redis master name cloud not be 'null' or empty for loading failover.");
		return client.failover(masterName);
	}

	@Override
	public Status monitor(String masterName, String ip, int port, int quorum) {
		Assert.isBlank(masterName, "Redis master name cloud not be 'null' or empty for loading failover.");
		return client.monitor(masterName, ip, port, quorum);
	}

	@Override
	public Long reset(String pattern) {
		return client.reset(pattern);
	}

	@Override
	public Status remove(String masterName) {
		Assert.isBlank(masterName, "Redis master name cloud be 'null' or empty when trying to remove.");
		return client.remove(masterName);
	}

	@Override
	public Transaction multi() {
		if(transaction == null){
			final RedisCommands<byte[], byte[]> commands = null;//conn.sync();
			commands.multi();
			//transaction = new DefaultTransactionProxy<>(new LettuceTransaction(commands), commands);
		}

		return transaction;
	}

	@Override
	protected void internalInit() {
		super.internalInit();
		if(client == null){
			final LettuceSentinelDataSource dataSource = (LettuceSentinelDataSource) getDataSource();
			final DefaultLettuceClientConfig.Builder clientConfigBuilder = DefaultLettuceClientConfig.builder();

			commonClientConfigBuilder(clientConfigBuilder);

			if(dataSource.getDatabase() > 0){
				clientConfigBuilder.database(dataSource.getDatabase());
			}

			final SentinelClientBuilder<K, V, RedisSentinelClient<K, V>> builder = RedisSentinelClient.<K, V>builder()
					.sentinels(createNodes(dataSource.getSentinels(), RedisSentinelNode.DEFAULT_SENTINEL_PORT))
					.clientConfig(clientConfigBuilder.build())
					.sentinelClientConfig(createSentinelLettuceClientConfig(dataSource)).codec(getCodec());

			Optional.ofNullable(getConnectionPoolConfig()).ifPresent(builder::poolConfig);
			//Optional.ofNullable(getCacheConfig()).ifPresent(builder::cacheConfig);
			if(Validate.hasText(dataSource.getMasterName())){
				builder.masterName(dataSource.getMasterName());
			}

			client = builder.build();
		}
	}

	@Override
	protected Status doConnect() throws RedisConnectionFailureException {
		if(isConnected()){
			return Status.SUCCESS;
		}

		return client == null ? Status.FAILURE : Status.SUCCESS;
	}

	protected LettuceClientConfig createSentinelLettuceClientConfig(final LettuceSentinelDataSource dataSource) {
		final DefaultLettuceClientConfig.Builder clientConfigBuilder = DefaultLettuceClientConfig.builder();

		commonClientConfigBuilder(clientConfigBuilder);
		clientConfigBuilder.user(null);
		clientConfigBuilder.password(null);

		if(Validate.hasText(dataSource.getSentinelPassword())){
			clientConfigBuilder.password(dataSource.getSentinelPassword());
			if(Validate.hasText(dataSource.getSentinelUsername())){
				clientConfigBuilder.user(dataSource.getSentinelUsername());
			}
		}

		clientConfigBuilder.connectionTimeoutMillis(getSentinelConnectTimeout());
		clientConfigBuilder.socketTimeoutMillis(getSentinelSoTimeout());

		propertyMapper.from(dataSource.getSentinelClientName()).as(clientConfigBuilder::clientName);

		return clientConfigBuilder.build();
	}

	protected List<RedisServer> parseRedisServer(final List<Map<K, V>> nodes, final Role role) {
		if(nodes == null){
			return null;
		}

		return nodes.stream().filter(Objects::nonNull).map((node)->{
			final Map<String, String> sNodes = new HashMap<>(node.size());
			final Properties properties = new Properties();

			node.forEach((key, value)->{
				sNodes.put(SafeEncoder.encode(getCodec().encodeKey(key).array()),
						SafeEncoder.encode(getCodec().encodeValue(value).array()));
			});

			properties.putAll(sNodes);

			final RedisServer redisServer = new RedisServer(sNodes.get("ip"), Integer.parseInt(sNodes.get("port")),
					properties);
			redisServer.setName(sNodes.get("name"));
			redisServer.setRole(role);

			return redisServer;
		}).collect(Collectors.toList());
	}

}
