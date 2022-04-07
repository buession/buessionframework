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
 * | Copyright @ 2013-2022 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.client.connection;

import com.buession.core.Destroyable;
import com.buession.core.Executor;
import com.buession.lang.Status;
import com.buession.net.ssl.SslConfiguration;
import com.buession.redis.client.connection.datasource.DataSource;
import com.buession.redis.pipeline.Pipeline;
import com.buession.redis.exception.RedisException;

import java.io.Closeable;
import java.io.IOException;
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
	 * 获取连接超时
	 *
	 * @return 连接超时（单位：秒）
	 */
	int getConnectTimeout();

	/**
	 * 设置连接超时
	 *
	 * @param connectTimeout
	 * 		连接超时（单位：秒）
	 */
	void setConnectTimeout(int connectTimeout);

	/**
	 * 设置读取超时
	 *
	 * @return 读取超时（单位：秒）
	 */
	int getSoTimeout();

	/**
	 * 设置读取超时
	 *
	 * @param soTimeout
	 * 		读取超时（单位：秒）
	 */
	void setSoTimeout(int soTimeout);

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
	 * @throws IOException
	 * 		I/O 异常
	 */
	Status connect() throws IOException;

	/**
	 * 执行 Redis 命令
	 *
	 * @param executor
	 * 		命令执行器
	 * @param <C>
	 * 		原始命令对象
	 * @param <R>
	 * 		返回值类型
	 *
	 * @return 不同命令，返回不同的结果
	 *
	 * @throws RedisException
	 * 		Redis Exception
	 */
	<C, R> R execute(final Executor<C, R> executor) throws RedisException;

	/**
	 * 当前是否处于事务状态
	 *
	 * @return 处于事务状态，则返回 true; 否则，返回 false
	 */
	boolean isTransaction();

	/**
	 * 获取管道
	 *
	 * @return 管道
	 */
	Pipeline pipeline();

	/**
	 * 当前是否处于管道状态
	 *
	 * @return 处于管道状态，则返回 true; 否则，返回 false
	 */
	boolean isPipeline();

	/**
	 * 标记事务开始
	 */
	void multi();

	/**
	 * 执行所有事务块内的命令
	 *
	 * @return 事务块内所有命令的返回值
	 */
	List<Object> exec();

	/**
	 * 取消事务
	 */
	void discard();

	/**
	 * 检测是否处于连接状态
	 *
	 * @return 是否处于连接状态
	 */
	boolean isConnect();

	/**
	 * 检测连接是否关闭
	 *
	 * @return 连接是否关闭
	 */
	boolean isClosed();

}
