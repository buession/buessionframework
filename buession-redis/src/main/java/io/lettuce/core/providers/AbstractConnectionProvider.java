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

import com.buession.core.converter.mapper.PropertyMapper;
import io.lettuce.core.LettuceClientConfig;
import io.lettuce.core.SocketOptions;
import io.lettuce.core.api.AsyncCloseable;
import io.lettuce.core.resource.ClientResources;
import io.lettuce.core.resource.Delay;

/**
 * Lettuce Redis 连接提供者基类
 *
 * @param <K>
 * 		Key 类型
 * @param <V>
 * 		值类型
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public abstract class AbstractConnectionProvider<K, V> implements ConnectionProvider<K, V> {

	protected final static PropertyMapper propertyMapper = PropertyMapper.get().alwaysApplyingWhenNonNull();

	protected static ClientResources createClientResources(final LettuceClientConfig clientConfig) {
		ClientResources.Builder builder = ClientResources.builder();

		propertyMapper.from(clientConfig.getComputationThreadPoolSize()).to(builder::computationThreadPoolSize);
		propertyMapper.from(clientConfig.getIoThreadPoolSize()).to(builder::ioThreadPoolSize);
		propertyMapper.from(clientConfig.getReconnectDelay()).as(Delay::constant).to(builder::reconnectDelay);

		return builder.build();
	}

	protected static SocketOptions createSocketOptions(final LettuceClientConfig clientConfig) {
		final SocketOptions.Builder builder = SocketOptions.builder();

		propertyMapper.from(clientConfig.getConnectionTimeout()).to(builder::connectTimeout);

		return builder.build();
	}

	protected static void closeQuietly(AutoCloseable closeable) {
		if(closeable != null){
			try{
				closeable.close();
			}catch(Exception e){
				//
			}
		}
	}

	protected static void asyncCloseQuietly(AsyncCloseable closeable) {
		if(closeable != null){
			try{
				closeable.closeAsync();
			}catch(Exception e){
				//
			}
		}
	}

}
