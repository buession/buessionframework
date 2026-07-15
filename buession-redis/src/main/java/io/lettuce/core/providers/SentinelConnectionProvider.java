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
package io.lettuce.core.providers;

import io.lettuce.core.ConnectionPool;
import io.lettuce.core.LettuceClientConfig;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulConnection;
import io.lettuce.core.codec.RedisCodec;
import io.lettuce.core.codec.StringCodec;
import io.lettuce.core.internal.HostAndPort;
import io.lettuce.core.protocol.CommandArgs;
import io.lettuce.core.sentinel.api.StatefulRedisSentinelConnection;
import io.lettuce.core.utils.IOUtils;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.Set;

/**
 * Lettuce Redis 哨兵连接池连接提供者
 *
 * @param <K>
 * 		Key 类型
 * @param <V>
 * 		值类型
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public class SentinelConnectionProvider<K, V> extends AbstractConnectionProvider<K, V> {

	private final Set<HostAndPort> sentinels;

	private final String masterName;

	private volatile ConnectionPool<K, V, StatefulConnection<K, V>> pool;

	private final SentinelConnectionFactory<String, String> sentinelConnectionFactory;

	private final LettuceClientConfig sentinelClientConfig;

	protected volatile StatefulRedisSentinelConnection<String, String> sentinelConnection;

	private final static Logger logger = LoggerFactory.getLogger(SentinelConnectionProvider.class);

	public SentinelConnectionProvider(final Set<HostAndPort> sentinels, final String masterName,
	                                  final LettuceClientConfig masterClientConfig,
	                                  final LettuceClientConfig sentinelClientConfig,
	                                  final RedisCodec<K, V> redisCodec) {
		this(sentinels, masterName, masterClientConfig, sentinelClientConfig, null, redisCodec);
	}

	public SentinelConnectionProvider(final Set<HostAndPort> sentinels, final String masterName,
	                                  final LettuceClientConfig masterClientConfig,
	                                  final LettuceClientConfig sentinelClientConfig,
	                                  final GenericObjectPoolConfig<StatefulConnection<K, V>> poolConfig,
	                                  final RedisCodec<K, V> redisCodec) {
		this.sentinels = sentinels;
		this.masterName = masterName;

		this.sentinelClientConfig = sentinelClientConfig;

		this.sentinelConnectionFactory = defaultSentinelConnectionFactory();

		this.pool = createPool(sentinels, masterName, masterClientConfig, poolConfig, redisCodec);
	}

	@Override
	public StatefulConnection<K, V> getConnection() {
		return pool.getResource();
	}

	@Override
	public StatefulConnection<K, V> getConnection(CommandArgs<K, V> commandArgs) {
		return pool.getResource();
	}

	public StatefulRedisSentinelConnection<String, String> getSentinelConnection() {
		if(sentinelConnection == null){
			for(HostAndPort sentinel : sentinels){
				try(StatefulRedisSentinelConnection<String, String> sentinelConnection = getSentinelConnection(
						sentinel)){
					this.sentinelConnection = sentinelConnection;
					break;
				}catch(Exception e){
					logger.warn("Connecting to Sentinel {} failure: {}.", sentinel, e.getMessage(), e);
				}
			}
		}

		return sentinelConnection;
	}

	public StatefulRedisSentinelConnection<String, String> getSentinelConnection(HostAndPort sentinel) {
		logger.debug("Connecting to Sentinel {}...", sentinel);

		try(StatefulRedisSentinelConnection<String, String> sentinelConnection = sentinelConnectionFactory.createConnection(
				sentinel, sentinelClientConfig)){
			return sentinelConnection;
		}catch(Exception e){
			logger.warn("Connecting to Sentinel {} failure: {}.", sentinel, e.getMessage(), e);
			return null;
		}
	}

	@Override
	public void close() {
		IOUtils.asyncCloseQuietly(sentinelConnection);
		IOUtils.closeQuietly(pool);
	}

	public HostAndPort getCurrentMaster() {
		StatefulRedisSentinelConnection<String, String> sentinelConnection = getSentinelConnection();
		SocketAddress masterAddr = sentinelConnection.sync().getMasterAddrByName(masterName);

		if(masterAddr instanceof InetSocketAddress addr){
			return HostAndPort.of(addr.getHostString(), addr.getPort());
		}

		return null;
	}

	private RedisClient createRedisClient(final Set<HostAndPort> sentinels, final String masterName,
	                                      final LettuceClientConfig clientConfig) {
		final RedisURI.Builder redisURIBuilder = RedisURI.builder();

		for(HostAndPort sentinel : sentinels){
			redisURIBuilder.withSentinel(sentinel.getHostText(), sentinel.getPort());
		}

		propertyMapper.from(clientConfig.getCredentialsProvider()).to(redisURIBuilder::withAuthentication);
		propertyMapper.from(clientConfig.getClientName()).to(redisURIBuilder::withClientName);

		if(clientConfig.getDatabase() >= 0){
			redisURIBuilder.withDatabase(clientConfig.getDatabase());
		}

		redisURIBuilder.withSentinelMasterId(masterName);

		if(clientConfig.isSsl()){
			redisURIBuilder.withSsl(true);
		}

		if(clientConfig.getSocketTimeout() != null && clientConfig.getSocketTimeout().isNegative() == false){
			redisURIBuilder.withTimeout(clientConfig.getSocketTimeout());
		}

		final RedisClient redisClient = RedisClient.create(createClientResources(clientConfig),
				redisURIBuilder.build());

		propertyMapper.from(createClientOptions(clientConfig)).to(redisClient::setOptions);

		return redisClient;
	}

	private ConnectionPool<K, V, StatefulConnection<K, V>> createPool(final Set<HostAndPort> sentinels,
	                                                                  final String masterName,
	                                                                  final LettuceClientConfig clientConfig,
	                                                                  final GenericObjectPoolConfig<StatefulConnection<K, V>> poolConfig,
	                                                                  final RedisCodec<K, V> redisCodec) {
		final RedisClient redisClient = createRedisClient(sentinels, masterName, clientConfig);
		if(poolConfig == null){
			return new ConnectionPool<>(redisClient, redisCodec);
		}else{
			return new ConnectionPool<>(redisClient, poolConfig, redisCodec);
		}
	}

	protected SentinelConnectionFactory<String, String> defaultSentinelConnectionFactory() {
		return (node, config)->{
			RedisURI redisURI = RedisURI.create(node.getHostText(), node.getPort());
			RedisClient redisClient = RedisClient.create(createClientResources(sentinelClientConfig), redisURI);

			propertyMapper.from(sentinelClientConfig.getCredentialsProvider()).to(redisURI::setCredentialsProvider);
			propertyMapper.from(sentinelClientConfig.getClientName()).to(redisURI::setClientName);

			propertyMapper.from(createClientOptions(sentinelClientConfig)).to(redisClient::setOptions);

			if(sentinelClientConfig.isSsl()){
				redisURI.setSsl(true);
			}

			if(sentinelClientConfig.getSocketTimeout() != null &&
					sentinelClientConfig.getSocketTimeout().isNegative() == false){
				redisURI.setTimeout(sentinelClientConfig.getSocketTimeout());
			}

			return redisClient.connectSentinel(StringCodec.UTF8);
		};
	}

	@FunctionalInterface
	protected interface SentinelConnectionFactory<K, V> {

		StatefulRedisSentinelConnection<K, V> createConnection(HostAndPort node, LettuceClientConfig config);

	}

}
