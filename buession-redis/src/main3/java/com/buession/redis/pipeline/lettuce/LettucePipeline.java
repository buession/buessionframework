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
package com.buession.redis.pipeline.lettuce;

import com.buession.core.utils.Assert;
import com.buession.redis.core.internal.lettuce.LettuceResult;
import com.buession.redis.exception.RedisPipelineException;
import com.buession.redis.pipeline.Pipeline;
import io.lettuce.core.LettuceFutures;
import io.lettuce.core.RedisFuture;
import io.lettuce.core.api.PipeliningFlushState;
import io.lettuce.core.api.StatefulConnection;
import io.lettuce.core.output.CommandOutput;
import io.lettuce.core.protocol.RedisCommand;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

/**
 * @author Yong.Teng
 * @since 3.0.0
 */
public class LettucePipeline implements Pipeline {

	private final PipeliningFlushState flushState;

	private final StatefulConnection<byte[], byte[]> connection;

	private Queue<LettuceResult<?, ?>> ppline;

	public LettucePipeline(final StatefulConnection<byte[], byte[]> connection, final PipeliningFlushState flushState) {
		Assert.isNull(flushState, "Redis Pipeline cloud not be null.");
		this.connection = connection;
		this.flushState = flushState;
		this.flushState.onOpen(connection);
	}

	public LettucePipeline(final StatefulConnection<byte[], byte[]> connection, final PipeliningFlushState flushState
			, final Queue<LettuceResult<?, ?>> ppline) {
		this(connection, flushState);
		this.ppline = Optional.ofNullable(ppline).orElse(new LinkedList<>());
	}

	@Override
	public void sync() {
		List<RedisCommand<?, ?, ?>> futures = new ArrayList<>(ppline.size());

		for(LettuceResult<?, ?> result : ppline){
			futures.add(result.getHolder());
		}

		try{
			boolean done = LettuceFutures.awaitAll(connection.getTimeout().toMillis(), TimeUnit.MILLISECONDS,
					futures.toArray(new RedisFuture[futures.size()]));

			Exception problem = null;

			if(done){
				CommandOutput<?, ?, ?> output;

				for(LettuceResult<?, ?> result : ppline){
					output = result.getHolder().getOutput();
					if(output.hasError()){
						Exception err = new RedisPipelineException(output.getError());
						if(problem == null){
							problem = err;
						}
					}else{
						result.get();
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

	@Override
	public List<Object> syncAndReturnAll() {
		List<RedisCommand<?, ?, ?>> futures = new ArrayList<>(ppline.size());

		for(LettuceResult<?, ?> result : ppline){
			futures.add(result.getHolder());
		}

		try{
			boolean done = LettuceFutures.awaitAll(connection.getTimeout().toMillis(), TimeUnit.MILLISECONDS,
					futures.toArray(new RedisFuture[futures.size()]));

			List<Object> results = new ArrayList<>(futures.size());

			Exception problem = null;

			if(done){
				CommandOutput<?, ?, ?> output;

				for(LettuceResult<?, ?> result : ppline){
					output = result.getHolder().getOutput();
					if(output.hasError()){
						Exception err = new RedisPipelineException(output.getError());
						if(problem == null){
							problem = err;
						}
						results.add(err);
					}else{
						try{
							results.add(result.get());
						}catch(Exception e){
							if(problem == null){
								problem = e;
							}
							results.add(e);
						}
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

	@Override
	public void close() {
		flushState.onClose(connection);
		if(ppline != null){
			ppline.clear();
		}
	}

}
