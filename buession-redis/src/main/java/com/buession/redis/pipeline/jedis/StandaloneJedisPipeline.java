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
 * | Copyright @ 2013-2021 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.pipeline.jedis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Pipeline;

import java.util.List;

/**
 * 单例 Jedis 原生 Pipeline
 *
 * @author Yong.Teng
 * @since 1.2.1
 */
public class StandaloneJedisPipeline implements JedisNativePipeline<redis.clients.jedis.Pipeline> {

	private final redis.clients.jedis.Pipeline pipeline;

	private final static Logger logger = LoggerFactory.getLogger(StandaloneJedisPipeline.class);

	public StandaloneJedisPipeline(final redis.clients.jedis.Pipeline pipeline){
		this.pipeline = pipeline;
	}

	@Override
	public Pipeline getNativeObject(){
		return pipeline;
	}

	@Override
	public void sync(){
		logger.info("Redis pipeline sync.");
		pipeline.sync();
	}

	@Override
	public List<Object> syncAndReturnAll(){
		logger.info("Redis pipeline syncAndReturnAll.");
		return pipeline.syncAndReturnAll();
	}

	@Override
	public void close(){
		logger.info("Redis pipeline close.");
		pipeline.close();
	}

}