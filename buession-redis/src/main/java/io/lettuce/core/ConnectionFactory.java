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
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
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
public class ConnectionFactory<K, V> implements PooledObjectFactory<StatefulConnection<K, V>> {

	private final static Logger logger = LoggerFactory.getLogger(ConnectionFactory.class);

	private final LettuceClientConfig clientConfig;

	private final HostAndPort hostAndPort;

	private final RedisCodec<K, V> redisCodec;

	private final Supplier<StatefulConnection<K, V>> objectMaker;

	public ConnectionFactory(final HostAndPort hostAndPort, final RedisCodec<K, V> redisCodec) {
		this(hostAndPort, DefaultLettuceClientConfig.builder().build(), redisCodec);
	}

	public ConnectionFactory(final HostAndPort hostAndPort, final LettuceClientConfig clientConfig,
	                         final RedisCodec<K, V> redisCodec) {
		this.hostAndPort = hostAndPort;
		this.clientConfig = clientConfig;
		this.redisCodec = redisCodec;
		this.objectMaker = this::build;
	}

	@Override
	public void activateObject(PooledObject<StatefulConnection<K, V>> pooledConnection) throws Exception {
		// what to do ??
	}

	@Override
	public void destroyObject(PooledObject<StatefulConnection<K, V>> pooledConnection) throws Exception {
		final StatefulConnection<K, V> connection = pooledConnection.getObject();
		if(connection.isOpen()){
			try{
				connection.close();
			}catch(RuntimeException e){
				logger.debug("Error while close", e);
			}
		}
	}

	@Override
	public PooledObject<StatefulConnection<K, V>> makeObject() throws Exception {
		try{
			final StatefulConnection<K, V> connection = objectMaker.get();
			return new DefaultPooledObject<>(connection);
		}catch(RedisException e){
			logger.debug("Error while makeObject", e);
			throw e;
		}
	}

	@Override
	public void passivateObject(PooledObject<StatefulConnection<K, V>> pooledConnection) throws Exception {
		final StatefulConnection<K, V> connection = pooledConnection.getObject();
		reAuthenticate(connection);
	}

	@Override
	public boolean validateObject(PooledObject<StatefulConnection<K, V>> pooledConnection) {
		final StatefulConnection<K, V> connection = pooledConnection.getObject();
		try{
			if(connection.isOpen() == false){
				return false;
			}
			reAuthenticate(connection);
			return true;//jedis.ping();
		}catch(final Exception e){
			logger.warn("Error while validating pooled Connection object.", e);
			return false;
		}
	}

	private StatefulConnection<K, V> build() {
		final PropertyMapper propertyMapper = PropertyMapper.get().alwaysApplyingWhenNonNull();
		final RedisURI redisURI = RedisURI.create(hostAndPort.getHostText(), hostAndPort.getPort());
		final RedisClient redisClient = RedisClient.create(clientConfig.getClientResources(), redisURI);
		ClientOptions clientOptions = null;

		propertyMapper.from(clientConfig.getCredentialsProvider()).to(redisURI::setCredentialsProvider);
		propertyMapper.from(clientConfig.getClientName()).to(redisURI::setClientName);

		if(clientConfig.getDatabase() >= 0){
			redisURI.setDatabase(clientConfig.getDatabase());
		}

		if(clientConfig.isSsl()){
			redisURI.setSsl(true);
		}

		if(clientConfig.getClientOptions() != null){
			if(clientConfig.getConnectionTimeout() != null &&
					clientConfig.getConnectionTimeout().isNegative() == false){
				SocketOptions socketOptions = clientConfig.getClientOptions().getSocketOptions().mutate()
						.connectTimeout(clientConfig.getConnectionTimeout()).build();
				clientOptions = clientConfig.getClientOptions().mutate().socketOptions(socketOptions).build();
			}else{
				clientOptions = clientConfig.getClientOptions();
			}
		}else{
			if(clientConfig.getConnectionTimeout() != null &&
					clientConfig.getConnectionTimeout().isNegative() == false){
				SocketOptions socketOptions = SocketOptions.builder()
						.connectTimeout(clientConfig.getConnectionTimeout())
						.build();
				clientOptions = ClientOptions.builder().socketOptions(socketOptions).build();
			}
		}
		propertyMapper.from(clientOptions).to(redisClient::setOptions);

		if(clientConfig.getSocketTimeout() != null && clientConfig.getSocketTimeout().isNegative() == false){
			redisURI.setTimeout(clientConfig.getSocketTimeout());
		}

		return redisClient.connect(redisCodec);
	}

	private void reAuthenticate(StatefulConnection<K, V> connection) throws Exception {
	}

}
