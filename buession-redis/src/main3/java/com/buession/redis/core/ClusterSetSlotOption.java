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
package com.buession.redis.core;

/**
 * 详情 <a href="http://www.redis.cn/commands/cluster-setslot.html" target="_blank">http://www.redis.cn/commands/cluster-setslot.html</a>
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public enum ClusterSetSlotOption {

	/**
	 * 将一个哈希槽设置为 importing 状态
	 */
	IMPORTING,

	/**
	 * 将一个哈希槽设置为 migrating 状态
	 */
	MIGRATING,

	/**
	 * 从哈希槽中清除导入和迁移状态
	 */
	STABLE,

	/**
	 * 将一个哈希槽绑定到另一个不同的节点
	 */
	NODE

}
