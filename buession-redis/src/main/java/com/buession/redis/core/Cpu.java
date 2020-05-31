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
package com.buession.redis.core;

import java.io.Serializable;

/**
 * CPU 的计算量统计信息
 *
 * @author Yong.Teng
 */
public class Cpu implements Serializable {

	private final static long serialVersionUID = 7374909500664048450L;

	/**
	 * Redis 服务器耗费的系统 CPU
	 */
	private double usedCpuSys;

	/**
	 * Redis 服务器耗费的用户 CPU
	 */
	private double usedCpuUser;

	/**
	 * Redis 后台进程耗费的系统 CPU
	 */
	private double usedCpuSysChildren;

	/**
	 * Redis 后台进程耗费的用户 CPU
	 */
	private double usedCpuUserChildren;

	/**
	 * 获取 Redis 服务器耗费的系统 CPU
	 *
	 * @return Redis 服务器耗费的系统 CPU
	 */
	public double getUsedCpuSys(){
		return usedCpuSys;
	}

	/**
	 * 设置 Redis 服务器耗费的系统 CPU
	 *
	 * @param usedCpuSys
	 * 		Redis 服务器耗费的系统 CPU
	 */
	public void setUsedCpuSys(double usedCpuSys){
		this.usedCpuSys = usedCpuSys;
	}

	/**
	 * 获取 Redis 服务器耗费的用户 CPU
	 *
	 * @return Redis 服务器耗费的用户 CPU
	 */
	public double getUsedCpuUser(){
		return usedCpuUser;
	}

	/**
	 * 设置 Redis 服务器耗费的用户 CPU
	 *
	 * @param usedCpuUser
	 * 		Redis 服务器耗费的用户 CPU
	 */
	public void setUsedCpuUser(double usedCpuUser){
		this.usedCpuUser = usedCpuUser;
	}

	/**
	 * 获取 Redis 后台进程耗费的系统 CPU
	 *
	 * @return Redis 后台进程耗费的系统 CPU
	 */
	public double getUsedCpuSysChildren(){
		return usedCpuSysChildren;
	}

	/**
	 * 设置 Redis 后台进程耗费的系统 CPU
	 *
	 * @param usedCpuSysChildren
	 * 		Redis 后台进程耗费的系统 CPU
	 */
	public void setUsedCpuSysChildren(double usedCpuSysChildren){
		this.usedCpuSysChildren = usedCpuSysChildren;
	}

	/**
	 * 获取 Redis 后台进程耗费的用户 CPU
	 *
	 * @return Redis 后台进程耗费的用户 CPU
	 */
	public double getUsedCpuUserChildren(){
		return usedCpuUserChildren;
	}

	/**
	 * 设置 Redis 后台进程耗费的用户 CPU
	 *
	 * @param usedCpuUserChildren
	 * 		Redis 后台进程耗费的用户 CPU
	 */
	public void setUsedCpuUserChildren(double usedCpuUserChildren){
		this.usedCpuUserChildren = usedCpuUserChildren;
	}

	@Override
	public String toString(){
		return "usedCpuSys=" + usedCpuSys + ", usedCpuUser=" + usedCpuUser + ", usedCpuSysChildren=" + usedCpuSysChildren + ", usedCpuUserChildren=" + usedCpuUserChildren;
	}

}
