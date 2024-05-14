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
package io.lettuce.core.support;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.codec.ByteArrayCodec;
import io.lettuce.core.support.ConnectionWrapping;
import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Yong.Teng
 * @since 2.4.0
 */
public class LettuceFactory extends BasePooledObjectFactory<StatefulRedisConnection<byte[], byte[]>> {

	private boolean wrapConnections;

	private RedisClient client;

	private int database;

	private AtomicReference<ConnectionWrapping.Origin<StatefulRedisConnection<byte[], byte[]>>> poolRef = new AtomicReference<>();

	@Override
	public StatefulRedisConnection<byte[], byte[]> create() throws Exception {
		return client.connect(new ByteArrayCodec());
	}

	@Override
	public PooledObject<StatefulRedisConnection<byte[], byte[]>> wrap(
			StatefulRedisConnection<byte[], byte[]> connection) {
		return new DefaultPooledObject<>(connection);
	}

	@Override
	public void activateObject(PooledObject<StatefulRedisConnection<byte[], byte[]>> pooledObject) throws Exception {
		StatefulRedisConnection<byte[], byte[]> connection = pooledObject.getObject();
		connection.sync().select(database);
	}

	@Override
	public boolean validateObject(PooledObject<StatefulRedisConnection<byte[], byte[]>> pooledObject) {
		StatefulRedisConnection<byte[], byte[]> connection = pooledObject.getObject();

		try{
			return connection.isOpen() && Objects.equals("PONG", connection.sync().ping());
		}catch(Exception e){
			return false;
		}
	}

	@Override
	public void destroyObject(PooledObject<StatefulRedisConnection<byte[], byte[]>> pooledObject) throws Exception {
		StatefulRedisConnection<byte[], byte[]> connection = pooledObject.getObject();

		if(connection.isOpen()){
			connection.close();
		}
	}

}
