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
package io.lettuce.core;

import com.buession.core.converter.mapper.PropertyMapper;
import io.lettuce.core.api.StatefulConnection;
import io.lettuce.core.codec.RedisCodec;
import io.lettuce.core.internal.HostAndPort;
import io.lettuce.core.resource.ClientResources;
import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Supplier;

/**
 * 连接工厂类
 *
 * @param <K>
 * 		Key 类型
 * @param <V>
 * 		值类型
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public class ConnectionFactory<K, V> extends BasePooledObjectFactory<StatefulConnection<K, V>> {

	private final static PropertyMapper propertyMapper = PropertyMapper.get().alwaysApplyingWhenNonNull();

	private final LettuceClientConfig clientConfig;

	private final HostAndPort hostAndPort;

	private final RedisCodec<K, V> redisCodec;

	private final boolean autoReconnect;

	private final Supplier<StatefulConnection<K, V>> objectMaker;

	private final static Logger logger = LoggerFactory.getLogger(ConnectionFactory.class);

	public ConnectionFactory(final HostAndPort hostAndPort, final RedisCodec<K, V> redisCodec) {
		this(hostAndPort, ClientOptions.DEFAULT_AUTO_RECONNECT, redisCodec);
	}

	public ConnectionFactory(final HostAndPort hostAndPort, final LettuceClientConfig clientConfig,
	                         final RedisCodec<K, V> redisCodec) {
		this(hostAndPort, clientConfig, ClientOptions.DEFAULT_AUTO_RECONNECT, redisCodec);
	}

	public ConnectionFactory(final HostAndPort hostAndPort, final boolean autoReconnect,
	                         final RedisCodec<K, V> redisCodec) {
		this(hostAndPort, DefaultLettuceClientConfig.builder().build(), autoReconnect, redisCodec);
	}

	public ConnectionFactory(final HostAndPort hostAndPort, final LettuceClientConfig clientConfig,
	                         final boolean autoReconnect, final RedisCodec<K, V> redisCodec) {
		this.hostAndPort = hostAndPort;
		this.clientConfig = clientConfig;
		this.autoReconnect = autoReconnect;
		this.redisCodec = redisCodec;
		this.objectMaker = this::build;
	}

	@Override
	public StatefulConnection<K, V> create() throws Exception {
		try{
			return objectMaker.get();
		}catch(RedisException e){
			logger.debug("Error while makeObject", e);
			throw e;
		}
	}

	@Override
	public void activateObject(final PooledObject<StatefulConnection<K, V>> connection) throws Exception {
		// The default implementation is a no-op.
	}

	@Override
	public boolean validateObject(PooledObject<StatefulConnection<K, V>> connection) {
		final StatefulConnection<K, V> conn = connection.getObject();
		try{
			return conn.isOpen();
		}catch(final Exception e){
			logger.warn("Error while validating pooled Connection object.", e);
			return false;
		}
	}

	@Override
	public PooledObject<StatefulConnection<K, V>> wrap(StatefulConnection<K, V> connection) {
		return new DefaultPooledObject<>(connection);
	}

	@Override
	public void destroyObject(PooledObject<StatefulConnection<K, V>> connection) throws Exception {
		final StatefulConnection<K, V> conn = connection.getObject();
		if(conn.isOpen()){
			try{
				conn.close();
			}catch(RuntimeException e){
				logger.debug("Error while close", e);
			}
		}
	}

	private StatefulConnection<K, V> build() {
		final RedisURI redisURI = RedisURI.create(hostAndPort.getHostText(), hostAndPort.getPort());

		propertyMapper.from(clientConfig.getCredentialsProvider()).to(redisURI::setCredentialsProvider);
		propertyMapper.from(clientConfig.getClientName()).to(redisURI::setClientName);

		if(clientConfig.getDatabase() >= 0){
			redisURI.setDatabase(clientConfig.getDatabase());
		}

		if(clientConfig.isSsl()){
			redisURI.setSsl(true);
		}

		if(clientConfig.getSocketTimeout() != null && clientConfig.getSocketTimeout().isNegative() == false){
			redisURI.setTimeout(clientConfig.getSocketTimeout());
		}

		final RedisClient redisClient = RedisClient.create(createClientResources(), redisURI);
		propertyMapper.from(createClientOptions()).to(redisClient::setOptions);

		return redisClient.connect(redisCodec);
	}

	private ClientOptions createClientOptions() {
		ClientOptions.Builder builder = ClientOptions.builder();

		builder.autoReconnect(autoReconnect);
		propertyMapper.from(clientConfig.getRequestQueueSize()).to(builder::requestQueueSize);
		builder.socketOptions(createSocketOptions());

		if(clientConfig.isSsl()){
			builder.sslOptions(clientConfig.getSslOptions());
		}

		return builder.build();
	}

	private SocketOptions createSocketOptions() {
		SocketOptions.Builder builder = SocketOptions.builder();

		propertyMapper.from(clientConfig.getConnectionTimeout()).to(builder::connectTimeout);

		return builder.build();
	}

	private ClientResources createClientResources() {
		ClientResources.Builder builder = ClientResources.builder();

		propertyMapper.from(clientConfig.getComputationThreadPoolSize()).to(builder::computationThreadPoolSize);
		propertyMapper.from(clientConfig.getIoThreadPoolSize()).to(builder::ioThreadPoolSize);
		//builder.reconnectDelay();

		return builder.build();
	}

}
