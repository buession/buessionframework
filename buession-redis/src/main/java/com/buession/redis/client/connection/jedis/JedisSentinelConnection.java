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
package com.buession.redis.client.connection.jedis;

import com.buession.core.utils.Assert;
import com.buession.core.validator.Validate;
import com.buession.lang.Status;
import com.buession.redis.client.connection.RedisSentinelConnection;
import com.buession.redis.client.connection.RedisSentinelNode;
import com.buession.redis.client.connection.datasource.jedis.JedisSentinelDataSource;
import com.buession.redis.core.Constants;
import com.buession.redis.core.PoolConfig;
import com.buession.redis.core.RedisNode;
import com.buession.redis.core.RedisServer;
import com.buession.redis.core.Role;
import com.buession.redis.core.internal.convert.response.OkStatusConverter;
import com.buession.redis.exception.RedisConnectionFailureException;
import redis.clients.jedis.CommandArguments;
import redis.clients.jedis.Connection;
import redis.clients.jedis.DefaultJedisClientConfig;
import redis.clients.jedis.JedisClientConfig;
import redis.clients.jedis.Protocol;
import redis.clients.jedis.RedisSentinelClient;
import redis.clients.jedis.builders.SentinelClientBuilder;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * Jedis 哨兵模式连接器
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public class JedisSentinelConnection extends AbstractJedisRedisConnection<RedisSentinelClient>
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
	public JedisSentinelConnection() {
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		Redis 数据源
	 */
	public JedisSentinelConnection(JedisSentinelDataSource dataSource) {
		super(dataSource);
	}

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		Redis 数据源
	 * @param poolConfig
	 * 		连接池配置
	 *
	 * @since 3.0.0
	 */
	public JedisSentinelConnection(JedisSentinelDataSource dataSource, PoolConfig poolConfig) {
		super(dataSource, poolConfig);
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
		try(Connection connection = getConnectionProvider().getConnection()){
			connection.sendCommand(Protocol.Command.SENTINEL, Protocol.SentinelKeyword.MYID);
			return connection.getBulkReply();
		}
	}

	@Override
	public List<RedisNode> sentinels(String masterName) {
		try(Connection connection = getConnectionProvider().getConnection()){
			connection.sendCommand(Protocol.Command.SENTINEL, Protocol.SentinelKeyword.SENTINELS.name(), masterName);
		}
		/*
		return connection.getObjectMultiBulkReply().stream()
				.map(BuilderFactory.STRING_MAP::build).collect(Collectors.toList());

		 */
		return null;
	}

	@Override
	public String sentinelSet(String masterName, Map<String, String> parameters) {
		Assert.isBlank(masterName, "Redis master name cloud not be 'null' or empty for loading sentinel set.");

		try(Connection connection = getConnectionProvider().getConnection()){
			CommandArguments args = new CommandArguments(Protocol.Command.SENTINEL).add(Protocol.SentinelKeyword.SET)
					.add(masterName);

			parameters.forEach((key, value)->args.add(key).add(value));
			connection.sendCommand(args);
			return connection.getStatusCodeReply();
		}
	}

	@Override
	public List<RedisServer> masters() {
		try(Connection connection = getConnectionProvider().getConnection()){
			connection.sendCommand(Protocol.Command.SENTINEL, Protocol.SentinelKeyword.MASTERS);
		}
		/*
		return connection.getObjectMultiBulkReply().stream().map(BuilderFactory.STRING_MAP::build)
				.collect(Collectors.toList());

		 */
		return null;
	}

	@Override
	public RedisServer master(String masterName) {
		Assert.isBlank(masterName, "Redis master name cloud not be 'null' or empty for loading master.");

		try(Connection connection = getConnectionProvider().getConnection()){
			connection.sendCommand(Protocol.Command.SENTINEL, Protocol.SentinelKeyword.MASTER.name(), masterName);
		}
		return null;//BuilderFactory.STRING_MAP.build(connection.getOne());
	}

	@Override
	public List<RedisServer> slaves(String masterName) {
		Assert.isBlank(masterName, "Redis master name cloud not be 'null' or empty for loading slaves.");

		try(Connection connection = getConnectionProvider().getConnection()){
			connection.sendCommand(Protocol.Command.SENTINEL, Protocol.SentinelKeyword.SLAVES.name(), masterName);
		}
		/*
		return connection.getObjectMultiBulkReply().stream().map(BuilderFactory.STRING_MAP::build)
				.collect(Collectors.toList());

		 */
		return null;
	}

	@Override
	public List<RedisServer> replicas(String masterName) {
		Assert.isBlank(masterName, "Redis master name cloud not be 'null' or empty for loading replicas.");

		try(Connection connection = getConnectionProvider().getConnection()){
			connection.sendCommand(Protocol.Command.SENTINEL, Protocol.SentinelKeyword.REPLICAS.name(), masterName);
		}
		/*
		return connection.getObjectMultiBulkReply().stream().map(BuilderFactory.STRING_MAP::build)
				.collect(Collectors.toList());

		 */
		return null;
	}

	@Override
	public Status failover(String masterName) {
		Assert.isBlank(masterName, "Redis master name cloud not be 'null' or empty for loading failover.");

		try(Connection connection = getConnectionProvider().getConnection()){
			connection.sendCommand(Protocol.Command.SENTINEL, Protocol.SentinelKeyword.FAILOVER.name(), masterName);
			return new OkStatusConverter().convert(connection.getStatusCodeReply());
		}
	}

	@Override
	public Status monitor(String masterName, String ip, int port, int quorum) {
		Assert.isBlank(masterName, "Redis master name cloud not be 'null' or empty for loading failover.");

		try(Connection connection = getConnectionProvider().getConnection()){
			CommandArguments args = new CommandArguments(Protocol.Command.SENTINEL).add(
					Protocol.SentinelKeyword.MONITOR).add(masterName).add(ip).add(port).add(quorum);
			connection.sendCommand(args);
			return new OkStatusConverter().convert(connection.getStatusCodeReply());
		}
	}

	@Override
	public Long reset(String pattern) {
		try(Connection connection = getConnectionProvider().getConnection()){
			connection.sendCommand(Protocol.Command.SENTINEL, Protocol.SentinelKeyword.RESET.name(), pattern);
			return connection.getIntegerReply();
		}
	}

	@Override
	public Status remove(String masterName) {
		Assert.isBlank(masterName, "Redis master name cloud be 'null' or empty when trying to remove.");

		try(Connection connection = getConnectionProvider().getConnection()){
			connection.sendCommand(Protocol.Command.SENTINEL, Protocol.SentinelKeyword.REMOVE.name(), masterName);
			return new OkStatusConverter().convert(connection.getStatusCodeReply());
		}
	}

	@Override
	protected void internalInit() {
		if(client == null){
			final JedisSentinelDataSource dataSource = (JedisSentinelDataSource) getDataSource();
			final DefaultJedisClientConfig.Builder clientConfigBuilder = DefaultJedisClientConfig.builder();

			commonClientConfigBuilder(clientConfigBuilder);

			if(dataSource.getDatabase() > 0){
				clientConfigBuilder.database(dataSource.getDatabase());
			}

			final SentinelClientBuilder<RedisSentinelClient> builder = RedisSentinelClient.builder()
					.sentinels(createNodes(dataSource.getSentinels(), RedisSentinelNode.DEFAULT_SENTINEL_PORT))
					.clientConfig(clientConfigBuilder.build())
					.sentinelClientConfig(createSentinelJedisClientConfig(dataSource));

			Optional.ofNullable(getConnectionPoolConfig()).ifPresent(builder::poolConfig);
			Optional.ofNullable(getCacheConfig()).ifPresent(builder::cacheConfig);
			propertyMapper.from(dataSource.getMasterName()).to(builder::masterName);

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

	protected JedisClientConfig createSentinelJedisClientConfig(final JedisSentinelDataSource dataSource) {
		final DefaultJedisClientConfig.Builder clientConfigBuilder = DefaultJedisClientConfig.builder();

		commonClientConfigBuilder(clientConfigBuilder);
		clientConfigBuilder.user(null);
		clientConfigBuilder.password(null);

		if(Validate.hasText(dataSource.getSentinelPassword())){
			clientConfigBuilder.password(dataSource.getSentinelPassword());
			propertyMapper.from(dataSource.getSentinelUsername()).to(clientConfigBuilder::user);
		}

		clientConfigBuilder.connectionTimeoutMillis(getSentinelConnectTimeout());
		clientConfigBuilder.socketTimeoutMillis(getSentinelSoTimeout());
		clientConfigBuilder.blockingSocketTimeoutMillis(getInfiniteSoTimeout());

		propertyMapper.from(dataSource.getSentinelClientName()).as(clientConfigBuilder::clientName);

		return clientConfigBuilder.build();
	}

	protected List<RedisServer> parseRedisServer(final List<Map<String, String>> nodes, final Role role) {
		if(nodes == null){
			return null;
		}

		return nodes.stream().filter(Objects::nonNull).map((node)->{
			Properties properties = new Properties();
			properties.putAll(node);

			RedisServer redisServer = new RedisServer(node.get("ip"), Integer.parseInt(node.get("port")), properties);
			redisServer.setName(node.get("name"));
			redisServer.setRole(role);

			return redisServer;
		}).collect(Collectors.toList());
	}

}
