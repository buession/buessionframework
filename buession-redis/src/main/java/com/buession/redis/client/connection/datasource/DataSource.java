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
package com.buession.redis.client.connection.datasource;

/**
 * Redis 数据源
 *
 * @author Yong.Teng
 */
public interface DataSource {

	/**
	 * 获取数据库
	 *
	 * @return 数据库
	 */
	int getDatabase();

	/**
	 * 设置数据库
	 *
	 * @param database
	 * 		数据库
	 */
	void setDatabase(int database);

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

}
