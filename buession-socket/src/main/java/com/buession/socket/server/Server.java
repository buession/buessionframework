/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license
 * agreements. See the NOTICE file distributed with this work for additional information regarding
 * copyright ownership. The ASF licenses this file to you under the Apache License, Version 2.0 
 * (the "License"); you may not use this file except in compliance with the License. You may obtain 
 * a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 * 
 * =================================================================================================
 * 
 * This software consists of voluntary contributions made by many individuals on behalf of the
 * Apache Software Foundation. For more information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 * 
 * +------------------------------------------------------------------------------------------------+
 * | License: http://buession.buession.com.cn/LICENSE 												|
 * | Author: Yong.Teng <webmaster@buession.com> 													|
 * | Copyright @ 2013-2015 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.socket.server;

import io.netty.channel.Channel;

import com.buession.core.Status;

/**
 * 服务接口
 *
 * @author Yong.Teng <webmaster@buession.com>
 */
public interface Server<T extends Channel> extends Runnable {

	/**
	 * 获取服务名称
	 * 
	 * @return 服务名称
	 */
	public String getName();

	/**
	 * 启动程序
	 * 
	 * @return 状态
	 */
	public Status start();

	/**
	 * 重载程序
	 * 
	 * @return 状态
	 */
	public Status reload();

	/**
	 * 重启程序
	 * 
	 * @return 状态
	 */
	public Status restart();

	/**
	 * 停止程序
	 * 
	 * @return 状态
	 */
	public Status stop();

	/**
	 * 获取进程 ID
	 * 
	 * @return 进程 ID
	 */
	public int getPid();

	/**
	 * 检查服务状态
	 * 
	 * @return 状态
	 */
	public Status status();

	/**
	 * 检查服务是否在运行
	 * 
	 * @return 服务是否在运行
	 */
	public boolean isRuning();

	/**
	 * 获取服务版本
	 * 
	 * @return 服务版本
	 */
	public String getVersion();

}