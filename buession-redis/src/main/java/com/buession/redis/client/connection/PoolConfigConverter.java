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
package com.buession.redis.client.connection;

import com.buession.core.converter.Converter;
import com.buession.core.utils.Assert;
import com.buession.redis.core.PoolConfig;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.lang.Nullable;

/**
 * @author Yong.Teng
 * @since 2.3.0
 */
public class PoolConfigConverter<T> implements Converter<PoolConfig, GenericObjectPoolConfig<T>> {

	private final GenericObjectPoolConfig<T> poolConfig;

	public PoolConfigConverter(final GenericObjectPoolConfig<T> poolConfig){
		Assert.isNull(poolConfig, "GenericObjectPoolConfig cloud not be null.");
		this.poolConfig = poolConfig;
	}

	@Nullable
	@Override
	public GenericObjectPoolConfig<T> convert(PoolConfig config){
		poolConfig.setLifo(config.getLifo());
		poolConfig.setFairness(config.getFairness());
		poolConfig.setMaxWait(config.getMaxWait());
		poolConfig.setMinEvictableIdleTime(config.getMinEvictableIdleTime());
		poolConfig.setSoftMinEvictableIdleTime(config.getSoftMinEvictableIdleTime());
		poolConfig.setEvictionPolicyClassName(config.getEvictionPolicyClassName());
		poolConfig.setEvictorShutdownTimeout(config.getEvictorShutdownTimeout());
		poolConfig.setNumTestsPerEvictionRun(config.getNumTestsPerEvictionRun());
		poolConfig.setTestOnCreate(config.getTestOnCreate());
		poolConfig.setTestOnBorrow(config.getTestOnBorrow());
		poolConfig.setTestOnReturn(config.getTestOnReturn());
		poolConfig.setTestWhileIdle(config.getTestWhileIdle());
		poolConfig.setTimeBetweenEvictionRuns(config.getTimeBetweenEvictionRuns());
		poolConfig.setBlockWhenExhausted(config.getBlockWhenExhausted());
		poolConfig.setJmxEnabled(config.getJmxEnabled());
		poolConfig.setJmxNamePrefix(config.getJmxNamePrefix());
		poolConfig.setJmxNameBase(config.getJmxNameBase());
		poolConfig.setMaxTotal(config.getMaxTotal());
		poolConfig.setMinIdle(config.getMinIdle());
		poolConfig.setMaxIdle(config.getMaxIdle());
		
		return poolConfig;
	}

}
