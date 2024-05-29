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
package com.buession.redis.pipeline.jedis;

import com.buession.core.utils.Assert;
import com.buession.redis.pipeline.Pipeline;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author Yong.Teng
 */
public class JedisClusterPipeline implements Pipeline {

	private final redis.clients.jedis.ClusterPipeline delegate;

	private final static Logger logger = LoggerFactory.getLogger(JedisClusterPipeline.class);

	public JedisClusterPipeline(final redis.clients.jedis.ClusterPipeline pipeline) {
		Assert.isNull(pipeline, "Redis Pipeline cloud not be null.");
		this.delegate = pipeline;
	}

	public redis.clients.jedis.ClusterPipeline primitive() {
		return delegate;
	}

	@Override
	public void sync() {
		logger.info("Redis pipeline sync.");
		delegate.sync();
	}

	@Override
	public List<Object> syncAndReturnAll() {
		logger.info("Redis pipeline syncAndReturnAll.");
		return null;
	}

	@Override
	public void close() {
		logger.info("Redis pipeline close.");
		delegate.close();
	}

}
