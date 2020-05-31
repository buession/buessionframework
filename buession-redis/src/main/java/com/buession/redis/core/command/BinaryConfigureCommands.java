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
package com.buession.redis.core.command;

import com.buession.lang.Status;

import java.util.List;

/**
 * 配置选项命令
 *
 * <p>详情说明
 * <a href="http://redisdoc.com/configure/index.html" target="_blank">http://redisdoc.com/configure/index.html</a></p>
 *
 * @author Yong.Teng
 */
public interface BinaryConfigureCommands extends BinaryRedisCommands {

	/**
	 * 动态地调整 Redis 服务器的配置
	 *
	 * <p>详情说明
	 * <a href="http://redisdoc.com/configure/config_set.html" target="_blank">http://redisdoc.com/configure/config_set.html</a></p>
	 *
	 * @param parameter
	 * 		配置项
	 * @param value
	 * 		配置值
	 *
	 * @return 设置成功时返回，返回 Status.SUCCESS；否则，返回 Status.FAILURE
	 */
	Status configSet(final byte[] parameter, final float value);

	/**
	 * 动态地调整 Redis 服务器的配置
	 *
	 * <p>详情说明
	 * <a href="http://redisdoc.com/configure/config_set.html" target="_blank">http://redisdoc.com/configure/config_set.html</a></p>
	 *
	 * @param parameter
	 * 		配置项
	 * @param value
	 * 		配置值
	 *
	 * @return 设置成功时返回，返回 Status.SUCCESS；否则，返回 Status.FAILURE
	 */
	Status configSet(final byte[] parameter, final double value);

	/**
	 * 动态地调整 Redis 服务器的配置
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/configure/config_set.html" target="_blank">http://redisdoc
	 * .com/configure/config_set.html</a></p>
	 *
	 * @param parameter
	 * 		配置项
	 * @param value
	 * 		配置值
	 *
	 * @return 设置成功时返回，返回 Status.SUCCESS；否则，返回 Status.FAILURE
	 */
	Status configSet(final byte[] parameter, final int value);

	/**
	 * 动态地调整 Redis 服务器的配置
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/configure/config_set.html" target="_blank">http://redisdoc
	 * .com/configure/config_set.html</a></p>
	 *
	 * @param parameter
	 * 		配置项
	 * @param value
	 * 		配置值
	 *
	 * @return 设置成功时返回，返回 Status.SUCCESS；否则，返回 Status.FAILURE
	 */
	Status configSet(final byte[] parameter, final long value);

	/**
	 * 动态地调整 Redis 服务器的配置
	 *
	 * <p>详情说明
	 * <a href="http://redisdoc.com/configure/config_set.html" target="_blank">http://redisdoc.com/configure
	 * /config_set.html</a></p>
	 *
	 * @param parameter
	 * 		配置项
	 * @param value
	 * 		配置值
	 *
	 * @return 设置成功时返回，返回 Status.SUCCESS；否则，返回 Status.FAILURE
	 */
	Status configSet(final byte[] parameter, final byte[] value);

	/**
	 * 获取 Redis 服务器的配置参数
	 *
	 * <p>详情说明
	 * <a href="http://redisdoc.com/configure/config_get.html" target="_blank">http://redisdoc.com/configure/config_get.html</a></p>
	 *
	 * @param parameter
	 * 		配置项
	 *
	 * @return 给定配置参数的值
	 */
	List<byte[]> configGet(final byte[] parameter);

}
