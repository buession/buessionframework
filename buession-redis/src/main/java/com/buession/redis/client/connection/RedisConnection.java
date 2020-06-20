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
 * | Copyright @ 2013-2020 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.client.connection;

import com.buession.core.Executor;
import com.buession.lang.Status;
import com.buession.redis.client.connection.datasource.DataSource;
import com.buession.redis.pipeline.Pipeline;
import com.buession.redis.transaction.Transaction;
import com.buession.redis.exception.RedisException;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;

/**
 * Redis 链接对象
 *
 * @author Yong.Teng
 */
public interface RedisConnection extends Closeable {

	DataSource getDataSource();

	void setDataSource(DataSource dataSource);

	Status connect() throws IOException;

	<C, R> R execute(final Executor<C, R> executor) throws RedisException;

	Transaction getTransaction();

	boolean isTransaction();

	Pipeline getPipeline();

	boolean isPipeline();

	void multi();

	List<Object> exec();

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

	/**
	 * 切断链接
	 *
	 * @throws IOException
	 * 		IO 异常
	 */
	void disconnect() throws IOException;

}
