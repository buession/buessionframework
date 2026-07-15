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

import com.buession.redis.core.internal.lettuce.LettuceResult;
import com.buession.redis.exception.RedisPipelineException;
import io.lettuce.core.api.PipeliningFlushPolicy;
import io.lettuce.core.api.PipeliningFlushState;
import io.lettuce.core.api.StatefulConnection;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Lettuce Pipeline
 *
 * @param <K>
 * 		Key 类型
 * @param <V>
 * 		值类型
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public class Pipeline<K, V> {

	private final StatefulConnection<K, V> connection;

	private final PipeliningFlushPolicy flushPolicy = PipeliningFlushPolicy.flushEachCommand();

	private final Queue<LettuceResult<?, ?>> ppline = new LinkedList<>();

	private PipeliningFlushState flushState;

	public Pipeline(final StatefulConnection<K, V> connection) {
		this.connection = connection;
		this.flushState = flushPolicy.newPipeline();
		this.flushState.onOpen(connection);
	}

	public void sync() {
		List<CompletableFuture<?>> futures = creeateFutures();

		try{
			boolean done = checkDone(futures);

			Exception problem = null;

			if(done){
				for(LettuceResult<?, ?> result : ppline){
					CompletableFuture<?> future = result.getHolder();

					if(future.isCompletedExceptionally()){
						String message = get(future);
						Exception exception = new RedisPipelineException(message);

						// remember only the first error
						if(problem == null){
							problem = exception;
						}
					}
				}
			}

			if(problem != null){
				throw new RedisPipelineException(problem.getMessage(), problem);
			}

			if(done){
				return;
			}

			throw new RedisPipelineException("Redis command timed out");
		}catch(Exception e){
			throw new RedisPipelineException(e);
		}
	}

	public List<Object> syncAndReturnAll() {
		List<CompletableFuture<?>> futures = creeateFutures();

		try{
			boolean done = checkDone(futures);
			List<Object> results = new ArrayList<>(futures.size());

			Exception problem = null;

			if(done){
				for(LettuceResult<?, ?> result : ppline){
					CompletableFuture<?> future = result.getHolder();

					if(future.isCompletedExceptionally()){
						String message = get(future);

						Exception exception = new RedisPipelineException(message);

						// remember only the first error
						if(problem == null){
							problem = exception;
						}

						results.add(exception);
					}
				}
			}

			if(problem != null){
				throw new RedisPipelineException(problem.getMessage(), problem);
			}

			if(done){
				return results;
			}

			throw new RedisPipelineException("Redis command timed out");
		}catch(Exception e){
			throw new RedisPipelineException(e);
		}
	}

	public void close() {
		flushState.onClose(connection);
		flushState = null;
		ppline.clear();
	}

	private List<CompletableFuture<?>> creeateFutures() {
		return ppline.stream().map(LettuceResult::getHolder).collect(Collectors.toList());
	}

	private boolean checkDone(final List<CompletableFuture<?>> futures) {
		return LettuceFutures.awaitAll(connection.getTimeout().toMillis(), TimeUnit.MILLISECONDS,
				futures.toArray(new RedisFuture[futures.size()]));
	}

	private String get(final CompletableFuture<?> future) {
		if(future instanceof io.lettuce.core.protocol.RedisCommand<?, ?, ?> rc){
			return rc.getOutput().getError();
		}else{
			try{
				future.get();
				return "";
			}catch(InterruptedException ignore){
				return "";
			}catch(ExecutionException e){
				return e.getCause().getMessage();
			}
		}
	}

}
