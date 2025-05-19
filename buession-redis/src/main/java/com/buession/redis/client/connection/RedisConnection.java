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
package com.buession.redis.client.connection;

import com.buession.core.Destroyable;
import com.buession.core.Executor;
import com.buession.lang.Status;
import com.buession.net.ssl.SslConfiguration;
import com.buession.redis.client.connection.datasource.DataSource;
import com.buession.redis.core.PoolConfig;
import com.buession.redis.exception.RedisConnectionFailureException;
import com.buession.redis.exception.RedisException;
import com.buession.redis.pipeline.Pipeline;
import com.buession.redis.transaction.Transaction;

import java.io.Closeable;
import java.util.List;

/**
 * Redis 连接对象
 *
 * @author Yong.Teng
 */
public interface RedisConnection extends Destroyable, Closeable {

	/**
	 * 获取 Redis 数据源
	 *
	 * @return Redis 数据源
	 */
	DataSource getDataSource();

	/**
	 * 设置 Redis 数据源
	 *
	 * @param dataSource
	 * 		Redis 数据源
	 */
	void setDataSource(DataSource dataSource);

	/**
	 * 返回连接池配置
	 *
	 * @return 连接池配置
	 *
	 * @since 3.0.0
	 */
	PoolConfig getPoolConfig();

	/**
	 * 连接池配置
	 *
	 * @param poolConfig
	 * 		连接池配置
	 *
	 * @since 3.0.0
	 */
	void setPoolConfig(PoolConfig poolConfig);

	/**
	 * 获取连接超时
	 *
	 * @return 连接超时（单位：毫秒）
	 */
	int getConnectTimeout();

	/**
	 * 设置连接超时
	 *
	 * @param connectTimeout
	 * 		连接超时（单位：毫秒）
	 */
	void setConnectTimeout(int connectTimeout);

	/**
	 * 设置读取超时
	 *
	 * @return 读取超时（单位：毫秒）
	 */
	int getSoTimeout();

	/**
	 * 设置读取超时
	 *
	 * @param soTimeout
	 * 		读取超时（单位：毫秒）
	 */
	void setSoTimeout(int soTimeout);

	/**
	 * 返回 Infinite 读取超时
	 *
	 * @return Infinite 读取超时（单位：毫秒）
	 */
	int getInfiniteSoTimeout();

	/**
	 * 设置 Infinite 读取超时
	 *
	 * @param infiniteSoTimeout
	 * 		Infinite 读取超时（单位：毫秒）
	 */
	void setInfiniteSoTimeout(int infiniteSoTimeout);

	/**
	 * 返回是否启用 SSL 连接
	 *
	 * @return 启用 SSL 连接，返回 true; 否则，返回 false
	 */
	boolean isUseSsl();

	/**
	 * SSL 配置
	 *
	 * @return SSL 配置
	 */
	SslConfiguration getSslConfiguration();

	/**
	 * 设置 SSL 配置
	 *
	 * @param sslConfiguration
	 * 		SSL 配置
	 */
	void setSslConfiguration(SslConfiguration sslConfiguration);

	/**
	 * 连接 Redis
	 *
	 * @return 连接成功，则返回 Status.SUCCESS; 否则，返回 Status.FAILURE
	 *
	 * @throws RedisConnectionFailureException
	 * 		Redis 连接失败异常
	 */
	Status connect() throws RedisConnectionFailureException;

	/**
	 * 执行 Redis 命令
	 *
	 * @param executor
	 * 		命令执行器
	 * @param <R>
	 * 		返回值类型
	 *
	 * @return 不同命令，返回不同的结果
	 *
	 * @throws RedisException
	 * 		Redis Exception
	 */
	<R> R execute(final Executor<RedisConnection, R> executor) throws RedisException;

	/**
	 * 当前是否处于管道状态
	 *
	 * @return 处于管道状态，则返回 true; 否则，返回 false
	 */
	boolean isPipeline();

	/**
	 * 打开管道
	 *
	 * @return 管道
	 */
	Pipeline openPipeline();

	/**
	 * 关闭管道
	 */
	void closePipeline();

	/**
	 * 当前是否处于事务状态
	 *
	 * @return 处于事务状态，则返回 true; 否则，返回 false
	 */
	boolean isTransaction();

	/**
	 * 标记事务开始
	 *
	 * @return 事务
	 */
	Transaction multi();

	/**
	 * 执行所有事务块内的命令
	 *
	 * @return 事务块内所有命令的返回值
	 *
	 * @throws RedisException
	 * 		Redis Exception
	 */
	List<Object> exec() throws RedisException;

	/**
	 * 取消事务
	 *
	 * @throws RedisException
	 * 		Redis Exception
	 */
	void discard() throws RedisException;

	/**
	 * 检测是否处于连接状态
	 *
	 * @return 是否处于连接状态
	 */
	@Deprecated
	default boolean isConnect() {
		return isConnected();
	}

	/**
	 * 检测是否处于连接状态
	 *
	 * @return 是否处于连接状态
	 *
	 * @since 3.0.0
	 */
	boolean isConnected();

	/**
	 * 检测连接是否关闭
	 *
	 * @return 连接是否关闭
	 */
	boolean isClosed();

}
