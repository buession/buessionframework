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
package com.buession.redis.client.connection.datasource;

import com.buession.redis.core.RedisNode;

import java.util.List;

/**
 * Redis 哨兵（Sentinel）模式数据源
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public interface SentinelDataSource extends DataSource {

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
	 * 返回 Sentinel Client Name
	 *
	 * @return Sentinel Client Name
	 */
	String getSentinelClientName();

	/**
	 * 设置 Sentinel Client Name
	 *
	 * @param sentinelClientName
	 * 		Sentinel Client Name
	 */
	void setSentinelClientName(String sentinelClientName);

	/**
	 * 返回 Master 名称
	 *
	 * @return Master 名称
	 */
	String getMasterName();

	/**
	 * 设置 Master 名称
	 *
	 * @param masterName
	 * 		Master 名称
	 */
	void setMasterName(String masterName);

	/**
	 * 返回哨兵节点
	 *
	 * @return 哨兵节点
	 */
	List<RedisNode> getSentinels();

	/**
	 * 设置哨兵节点
	 *
	 * @param sentinels
	 * 		哨兵节点
	 */
	void setSentinels(List<RedisNode> sentinels);

}
