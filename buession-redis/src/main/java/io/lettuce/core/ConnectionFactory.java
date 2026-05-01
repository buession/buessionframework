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

import io.lettuce.core.api.StatefulConnection;
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

	private Supplier<StatefulConnection<K, V>> objectMaker;

	public ConnectionFactory(final HostAndPort hostAndPort) {
		this(ConnectionFactory.<K, V>builder().hostAndPort(hostAndPort).withDefaults());
	}

	public ConnectionFactory(final HostAndPort hostAndPort, final LettuceClientConfig clientConfig) {
		this(ConnectionFactory.<K, V>builder().hostAndPort(hostAndPort).clientConfig(clientConfig).withDefaults());
	}

	public ConnectionFactory(Builder<K, V> builder) {
		this.clientConfig = builder.getClientConfig();
		this.objectMaker = this::build;
	}

	public static <K, V> Builder<K, V> builder() {
		return new Builder<>();
	}

	private StatefulConnection<K, V> build() {
		return null;// connectionBuilder.build();
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

	private void reAuthenticate(StatefulConnection<K, V> jedis) throws Exception {
	}

	public static class Builder<K, V> {

		private LettuceClientConfig clientConfig;

		private HostAndPort hostAndPort;

		public Builder<K, V> clientConfig(LettuceClientConfig clientConfig) {
			this.clientConfig = clientConfig;
			return this;
		}

		public Builder<K, V> hostAndPort(HostAndPort hostAndPort) {
			this.hostAndPort = hostAndPort;
			return this;
		}

		public LettuceClientConfig getClientConfig() {
			return clientConfig;
		}

		public HostAndPort getHostAndPort() {
			return hostAndPort;
		}

		public ConnectionFactory<K, V> build() {
			withDefaults();
			return new ConnectionFactory<>(this);
		}

		private Builder<K, V> withDefaults() {
			return this;
		}

	}

}
