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
 * | Copyright @ 2013-2023 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package io.lettuce.core.connection;

import com.buession.core.utils.Assert;
import io.lettuce.core.ClientOptions;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.async.RedisAsyncCommands;
import io.lettuce.core.api.reactive.RedisReactiveCommands;
import io.lettuce.core.api.sync.RedisCommands;
import io.lettuce.core.protocol.RedisCommand;
import io.lettuce.core.resource.ClientResources;

import java.time.Duration;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author Yong.Teng
 * @since StandaloneStatefulRedisConnection
 */
public class StandaloneStatefulRedisConnection implements StatefulRedisConnection<byte[], byte[]> {

	private final StatefulRedisConnection<byte[], byte[]> delegate;

	public StandaloneStatefulRedisConnection(final StatefulRedisConnection<byte[], byte[]> delegate){
		Assert.isNull(delegate, "Origin StatefulRedisConnection cloud not be null.");
		this.delegate = delegate;
	}

	@Override
	public Duration getTimeout(){
		return delegate.getTimeout();
	}

	@Override
	public void setTimeout(Duration timeout){
		delegate.setTimeout(timeout);
	}

	@Deprecated
	@Override
	public void setTimeout(long timeout, TimeUnit unit){
		delegate.setTimeout(timeout, unit);
	}

	@Override
	public ClientOptions getOptions(){
		return delegate.getOptions();
	}

	@Override
	public ClientResources getResources(){
		return delegate.getResources();
	}

	@Override
	public RedisCommands<byte[], byte[]> sync(){
		return delegate.sync();
	}

	@Override
	public RedisAsyncCommands<byte[], byte[]> async(){
		return delegate.async();
	}

	@Override
	public RedisReactiveCommands<byte[], byte[]> reactive(){
		return delegate.reactive();
	}

	@Override
	public boolean isMulti(){
		return delegate.isMulti();
	}

	@Override
	public boolean isOpen(){
		return delegate.isOpen();
	}

	@Override
	public void setAutoFlushCommands(boolean autoFlush){
		delegate.setAutoFlushCommands(autoFlush);
	}

	@Override
	public void flushCommands(){
		delegate.flushCommands();
	}

	@Override
	public <T> RedisCommand<byte[], byte[], T> dispatch(RedisCommand<byte[], byte[], T> command){
		return delegate.dispatch(command);
	}

	@Override
	public Collection<RedisCommand<byte[], byte[], ?>> dispatch(
			Collection<? extends RedisCommand<byte[], byte[], ?>> redisCommands){
		return delegate.dispatch(redisCommands);
	}

	@Deprecated
	@Override
	public void reset(){
		delegate.reset();
	}

	@Override
	public CompletableFuture<Void> closeAsync(){
		return delegate.closeAsync();
	}

	@Override
	public void close(){
		delegate.close();
	}

}
