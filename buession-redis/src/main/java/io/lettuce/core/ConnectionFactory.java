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
import io.lettuce.core.codec.RedisCodec;
import io.lettuce.core.cluster.RedisClusterClient;
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
 * @param <CONN>
 * 		连接类型
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public class ConnectionFactory<K, V, CONN extends StatefulConnection<K, V>> extends BasePooledObjectFactory<CONN> {

	private final AbstractRedisClient redisClient;

	private final RedisCodec<K, V> redisCodec;

	private final Supplier<CONN> objectMaker;

	private final static Logger logger = LoggerFactory.getLogger(ConnectionFactory.class);

	public ConnectionFactory(final AbstractRedisClient redisClient, final RedisCodec<K, V> redisCodec) {
		this.redisClient = redisClient;
		this.redisCodec = redisCodec;
		this.objectMaker = this::build;
	}

	@Override
	public CONN create() throws Exception {
		try{
			return objectMaker.get();
		}catch(RedisException e){
			logger.debug("Error while makeObject", e);
			throw e;
		}
	}

	@Override
	public boolean validateObject(PooledObject<CONN> connection) {
		final CONN conn = connection.getObject();
		try{
			return conn.isOpen();
		}catch(final Exception e){
			logger.warn("Error while validating pooled Connection object.", e);
			return false;
		}
	}

	@Override
	public PooledObject<CONN> wrap(CONN connection) {
		return new DefaultPooledObject<>(connection);
	}

	@Override
	public void destroyObject(PooledObject<CONN> connection) throws Exception {
		final CONN conn = connection.getObject();
		if(conn.isOpen()){
			try{
				conn.close();
			}catch(RuntimeException e){
				logger.debug("Error while close", e);
			}
		}
	}

	@SuppressWarnings({"unchecked"})
	private CONN build() {
		if(redisClient instanceof RedisClusterClient redisClusterClient){
			return (CONN) redisClusterClient.connect(redisCodec);
		}else if(redisClient instanceof RedisClient){
			RedisClient redisClient = (RedisClient) this.redisClient;
			return (CONN) redisClient.connect(redisCodec);
		}else{
			throw new IllegalArgumentException("Unsupported redisClient: " + redisClient.getClass());
		}
	}

}
