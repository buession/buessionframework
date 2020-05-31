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
 * | Copyright @ 2013-2019 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.core.command;

import com.buession.lang.Status;

/**
 * 持久化命令
 *
 * <p>详情说明
 * <a href="http://redisdoc.com/persistence/index.html" target="_blank">http://redisdoc.com/persistence/index.html</a>
 * </p>
 *
 * @author Yong.Teng
 */
public interface PersistenceCommand extends RedisCommands {

	/**
	 * 执行一个同步保存操作，将当前 Redis 实例的所有数据快照(snapshot)以 RDB 文件的形式保存到硬盘；
	 * 该操作，因为它会阻塞所有客户端
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/persistence/save.html" target="_blank">http://redisdoc
	 * .com/persistence/save.html</a></p>
	 *
	 * @return 保存成功返回 Status.SUCCESS；否则，返回 Status.FAILURE
	 */
	Status save();

	/**
	 * 在后台异步保存当前数据库的数据到磁盘
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/persistence/bgsave.html" target="_blank">http://redisdoc
	 * .com/persistence/bgsave.html</a></p>
	 *
	 * @return 反馈信息
	 */
	String bgSave();

	/**
	 * 执行一个 AOF文件 重写操作；重写会创建一个当前 AOF 文件的体积优化版本
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/persistence/bgrewriteaof.html" target="_blank">http://redisdoc
	 * .com/persistence/bgrewriteaof.html</a></p>
	 *
	 * @return 反馈信息
	 */
	String bgRewriteAof();

	/**
	 * 获取最近一次 Redis 成功将数据保存到磁盘上的时间，以 UNIX 时间戳格式表示
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/persistence/lastsave.html" target="_blank">http://redisdoc
	 * .com/persistence/lastsave.html</a></p>
	 *
	 * @return 最近一次成功将数据保存到磁盘上的时间
	 */
	Long lastSave();

}
