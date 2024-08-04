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
package io.lettuce.core.api;

import com.buession.core.utils.Assert;
import com.buession.redis.client.connection.lettuce.LettuceRedisConnection;
import io.lettuce.core.BufferedFlushing;
import io.lettuce.core.FlushEachCommand;
import io.lettuce.core.FlushOnClose;

/**
 * Strategy interface to control pipelining flush behavior. Lettuce writes (flushes) each command individually to the
 * Redis connection. Flushing behavior can be customized to optimize for performance. Flushing can be either stateless
 * or stateful. An example for stateful flushing is size-based (buffer) flushing to flush after a configured number of
 * commands.
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public interface PipeliningFlushPolicy {

	/**
	 * Return a policy to flush after each command (default behavior).
	 *
	 * @return A policy to flush after each command.
	 */
	static PipeliningFlushPolicy flushEachCommand() {
		return new FlushEachCommand();
	}

	/**
	 * Return a policy to flush only if {@link LettuceRedisConnection#closePipeline()} is called.
	 *
	 * @return A policy to flush after each command.
	 */
	static PipeliningFlushPolicy flushOnClose() {
		return new FlushOnClose();
	}

	/**
	 * Return a policy to buffer commands and to flush once reaching the configured {@code bufferSize}. The buffer is
	 * recurring so a buffer size of e.g. {@code 2} will flush after 2, 4, 6, … commands.
	 *
	 * @param bufferSize
	 * 		the number of commands to buffer before flushing. Must be greater than zero.
	 *
	 * @return A policy to flush buffered commands to the Redis connection once the configured number of commands was
	 * issued.
	 */
	static PipeliningFlushPolicy buffered(int bufferSize) {
		Assert.isFalse(bufferSize > 0, "Buffer size must be greater than 0");
		return ()->new BufferedFlushing(bufferSize);
	}

	PipeliningFlushState newPipeline();

}
