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
package io.lettuce.core;

import io.lettuce.core.api.StatefulConnection;
import io.lettuce.core.support.BoundedPoolConfig;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import java.time.Duration;

/**
 * Lettuce 连接池配置
 *
 * @param <K>
 * 		Key 类型
 * @param <V>
 * 		值类型
 * @param <C>
 *        {@link StatefulConnection} 类型
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public class LettucePoolConfig<K, V, C extends StatefulConnection<K, V>> extends GenericObjectPoolConfig<C> {

	/**
	 * 构造函数
	 */
	public LettucePoolConfig() {
		final BoundedPoolConfig boundedPoolConfig = BoundedPoolConfig.create();

		setMaxTotal(boundedPoolConfig.getMaxTotal());
		setMinIdle(boundedPoolConfig.getMinIdle());
		setMaxIdle(boundedPoolConfig.getMaxIdle());
		setTestOnCreate(boundedPoolConfig.isTestOnCreate());
		setTestWhileIdle(true);
		setMinEvictableIdleTime(Duration.ofMillis(60000));
		setTimeBetweenEvictionRuns(Duration.ofMillis(30000));
		setNumTestsPerEvictionRun(-1);
	}

}
