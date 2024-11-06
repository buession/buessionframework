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
package com.buession.redis.exception;

import com.buession.core.builder.ListBuilder;

import java.util.Collections;
import java.util.List;

/**
 * Redis 管道异常
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public class RedisPipelineException extends RedisException {

	private final static long serialVersionUID = 1470569084378076197L;

	private final List<Object> results;

	public RedisPipelineException() {
		this(ListBuilder.empty());
	}

	public RedisPipelineException(String message) {
		this(message, ListBuilder.empty());
	}

	public RedisPipelineException(String message, Throwable cause) {
		this(message, cause, ListBuilder.empty());
	}

	public RedisPipelineException(Throwable cause) {
		this(cause, ListBuilder.empty());
	}

	public RedisPipelineException(String message, Throwable cause, boolean enableSuppression,
								  boolean writableStackTrace) {
		this(message, cause, enableSuppression, writableStackTrace, ListBuilder.empty());
	}

	public RedisPipelineException(List<Object> results) {
		super("Pipeline contained one or more invalid commands");
		this.results = Collections.unmodifiableList(results);
	}

	public RedisPipelineException(String message, List<Object> results) {
		super(message);
		this.results = Collections.unmodifiableList(results);
	}

	public RedisPipelineException(String message, Throwable cause, List<Object> results) {
		super(message, cause);
		this.results = Collections.unmodifiableList(results);
	}

	public RedisPipelineException(Throwable cause, List<Object> results) {
		super("Pipeline contained one or more invalid commands", cause);
		this.results = Collections.unmodifiableList(results);
	}

	public RedisPipelineException(String message, Throwable cause, boolean enableSuppression,
								  boolean writableStackTrace, List<Object> results) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.results = Collections.unmodifiableList(results);
	}

	public List<Object> getPipelineResult() {
		return results;
	}

}
