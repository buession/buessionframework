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

import com.buession.lang.Arch;
import com.buession.lang.Multiplexing;

import java.io.Serializable;

/**
 * Redis 服务器的信息
 *
 * @author Yong.Teng
 */
public class Server extends GenericRedisNode implements Serializable {

	private final static long serialVersionUID = 2729543496870054983L;

	/**
	 * Redis 服务器的宿主操作系统
	 */
	private String os;

	/**
	 * Redis 架构
	 */
	private Arch arch;

	/**
	 * Redis 所使用的事件处理机制
	 */
	private Multiplexing multiplexingApi;

	/**
	 * Redis Git SHA1
	 */
	private String gitSha1;

	/**
	 * Redis Git dirty flag
	 */
	private String gitDirty;

	/**
	 * Redis build id
	 */
	private String buildId;

	/**
	 * Redis 运行模式
	 */
	private Mode mode;

	/**
	 * Redis 配置文件路径
	 */
	private String configFile;

	/**
	 * Redis 可执行文件路径
	 */
	private String executable;

	/**
	 * Redis 服务器版本
	 */
	private String version;

	/**
	 * 编译 Redis 时所使用的 GCC 版本
	 */
	private String gccVersion;

	/**
	 * 服务器进程的 PID
	 */
	private int processId;

	/**
	 * Redis 服务器的随机标识符（用于 Sentinel 和集群）
	 */
	private String runId;

	/**
	 * 自 Redis 服务器启动以来的运行时间
	 */
	private Uptime uptime;

	/**
	 * 以分钟为单位进行自增的时钟，用于 LRU 管理
	 */
	private int lruClock;

	/**
	 * Redis 内部调度（进行关闭 timeout 的客户端，删除过期key等等）频率
	 */
	private int hz;

	/**
	 *
	 */
	private int configuredHz;

	/**
	 * 是否是主服务器
	 */
	private boolean isMaster;

	/**
	 * 获取 Redis 服务器的宿主操作系统
	 *
	 * @return Redis 服务器的宿主操作系统
	 */
	public String getOs(){
		return os;
	}

	/**
	 * 设置 Redis 服务器的宿主操作系统
	 *
	 * @param os
	 * 		Redis 服务器的宿主操作系统
	 */
	public void setOs(String os){
		this.os = os;
	}

	/**
	 * 获取 Redis 架构
	 *
	 * @return Redis 架构
	 */
	public Arch getArch(){
		return arch;
	}

	/**
	 * 设置 Redis 架构
	 *
	 * @param arch
	 * 		Redis 架构
	 */
	public void setArch(Arch arch){
		this.arch = arch;
	}

	/**
	 * 获取 Redis 所使用的事件处理机制
	 *
	 * @return Redis 所使用的事件处理机制
	 */
	public Multiplexing getMultiplexingApi(){
		return multiplexingApi;
	}

	/**
	 * 设置 Redis 所使用的事件处理机制
	 *
	 * @param multiplexingApi
	 * 		Redis 所使用的事件处理机制
	 */
	public void setMultiplexingApi(Multiplexing multiplexingApi){
		this.multiplexingApi = multiplexingApi;
	}

	/**
	 * 获取 Redis Git SHA1
	 *
	 * @return Redis Git SHA1
	 */
	public String getGitSha1(){
		return gitSha1;
	}

	/**
	 * 设置 Redis Git SHA1
	 *
	 * @param gitSha1
	 * 		Redis Git SHA1
	 */
	public void setGitSha1(String gitSha1){
		this.gitSha1 = gitSha1;
	}

	/**
	 * 获取 Redis Git dirty flag
	 *
	 * @return Redis Git dirty flag
	 */
	public String getGitDirty(){
		return gitDirty;
	}

	/**
	 * 设置 Redis Git dirty flag
	 *
	 * @param gitDirty
	 * 		Redis Git dirty flag
	 */
	public void setGitDirty(String gitDirty){
		this.gitDirty = gitDirty;
	}

	/**
	 * 获取 Redis build id
	 *
	 * @return Redis build id
	 */
	public String getBuildId(){
		return buildId;
	}

	/**
	 * 设置 Redis build id
	 *
	 * @param buildId
	 * 		Redis build id
	 */
	public void setBuildId(String buildId){
		this.buildId = buildId;
	}

	/**
	 * 获取 Redis 运行模式
	 *
	 * @return Redis 运行模式
	 */
	public Mode getMode(){
		return mode;
	}

	/**
	 * 设置 Redis 运行模式
	 *
	 * @param mode
	 * 		Redis 运行模式
	 */
	public void setMode(Mode mode){
		this.mode = mode;
	}

	/**
	 * 获取 Redis 配置文件路径
	 *
	 * @return Redis 配置文件路径
	 */
	public String getConfigFile(){
		return configFile;
	}

	/**
	 * 设置 Redis 配置文件路径
	 *
	 * @param configFile
	 * 		Redis 配置文件路径
	 */
	public void setConfigFile(String configFile){
		this.configFile = configFile;
	}

	/**
	 * 获取 Redis 可执行文件路径
	 *
	 * @return Redis 可执行文件路径
	 */
	public String getExecutable(){
		return executable;
	}

	/**
	 * 设置 Redis 可执行文件路径
	 *
	 * @param executable
	 * 		Redis 可执行文件路径
	 */
	public void setExecutable(String executable){
		this.executable = executable;
	}

	/**
	 * 获取 Redis 服务器版本
	 *
	 * @return Redis 服务器版本
	 */
	public String getVersion(){
		return version;
	}

	/**
	 * 设置 Redis 服务器版本
	 *
	 * @param version
	 * 		Redis 服务器版本
	 */
	public void setVersion(String version){
		this.version = version;
	}

	/**
	 * 获取编译 Redis 时所使用的 GCC 版本
	 *
	 * @return 编译 Redis 时所使用的 GCC 版本
	 */
	public String getGccVersion(){
		return gccVersion;
	}

	/**
	 * 设置编译 Redis 时所使用的 GCC 版本
	 *
	 * @param gccVersion
	 * 		编译 Redis 时所使用的 GCC 版本
	 */
	public void setGccVersion(String gccVersion){
		this.gccVersion = gccVersion;
	}

	/**
	 * 获取服务器进程的 PID
	 *
	 * @return 服务器进程的 PID
	 */
	public int getProcessId(){
		return processId;
	}

	/**
	 * 设置服务器进程的 PID
	 *
	 * @param processId
	 * 		服务器进程的 PID
	 */
	public void setProcessId(int processId){
		this.processId = processId;
	}

	/**
	 * 获取 Redis 服务器的随机标识符（用于 Sentinel 和集群）
	 *
	 * @return Redis 服务器的随机标识符（用于 Sentinel 和集群）
	 */
	public String getRunId(){
		return runId;
	}

	/**
	 * 设置 Redis 服务器的随机标识符（用于 Sentinel 和集群）
	 *
	 * @param runId
	 * 		Redis 服务器的随机标识符（用于 Sentinel 和集群）
	 */
	public void setRunId(String runId){
		this.runId = runId;
	}

	/**
	 * 获取自 Redis 服务器启动以来的运行时间
	 *
	 * @return 自 Redis 服务器启动以来的运行时间
	 */
	public Uptime getUptime(){
		return uptime;
	}

	/**
	 * 设置自 Redis 服务器启动以来的运行时间
	 *
	 * @param uptime
	 * 		自 Redis 服务器启动以来的运行时间
	 */
	public void setUptime(Uptime uptime){
		this.uptime = uptime;
	}

	/**
	 * 获取以分钟为单位进行自增的时钟，用于 LRU 管理
	 *
	 * @return 以分钟为单位进行自增的时钟，用于 LRU 管理
	 */
	public int getLruClock(){
		return lruClock;
	}

	/**
	 * 设置以分钟为单位进行自增的时钟，用于 LRU 管理
	 *
	 * @param lruClock
	 * 		以分钟为单位进行自增的时钟
	 */
	public void setLruClock(int lruClock){
		this.lruClock = lruClock;
	}

	/**
	 * 获取 Redis 内部调度（进行关闭 timeout 的客户端，删除过期key等等）频率
	 *
	 * @return Redis 内部调度（进行关闭 timeout 的客户端，删除过期key等等）频率
	 */
	public int getHz(){
		return hz;
	}

	/**
	 * 设置 Redis 内部调度（进行关闭 timeout 的客户端，删除过期key等等）频率
	 *
	 * @param hz
	 * 		Redis 内部调度（进行关闭 timeout 的客户端，删除过期key等等）频率
	 */
	public void setHz(int hz){
		this.hz = hz;
	}

	public int getConfiguredHz(){
		return configuredHz;
	}

	public void setConfiguredHz(int configuredHz){
		this.configuredHz = configuredHz;
	}

	/**
	 * 获取是否为主服务器
	 *
	 * @return 是否为主服务器
	 */
	public boolean isMaster(){
		return getIsMaster();
	}

	/**
	 * 获取是否为主服务器
	 *
	 * @return 是否为主服务器
	 */
	public boolean getIsMaster(){
		return isMaster;
	}

	/**
	 * 标示为是否为主服务器
	 *
	 * @param isMaster
	 * 		是否为主服务器
	 */
	public void setMaster(boolean isMaster){
		setIsMaster(isMaster);
	}

	/**
	 * 标示为是否为主服务器
	 *
	 * @param isMaster
	 * 		是否为主服务器
	 */
	public void setIsMaster(boolean isMaster){
		this.isMaster = isMaster;
	}

	@Override
	public String toString(){
		return super.toString() + ", os='" + os + '\'' + ", arch=" + arch + ", multiplexingApi=" + multiplexingApi +
				", gitSha1" + "='" + gitSha1 + '\'' + ", gitDirty='" + gitDirty + '\'' + ", buildId='" + buildId + '\'' + ", " + "mode=" + mode + ", configFile='" + configFile + '\'' + ", executable='" + executable + '\'' + ", " + "version='" + version + '\'' + ", gccVersion='" + gccVersion + '\'' + ", processId=" + processId + "," + " " + "runId='" + runId + '\'' + ", uptime=" + uptime + ", lruClock=" + lruClock + ", hz=" + hz + ", " + "configuredHz=" + configuredHz + ", isMaster=" + isMaster;
	}

	public final static class Uptime {

		/**
		 * 自 Redis 服务器启动以来，经过的秒数
		 */
		private int seconds;

		/**
		 * 自 Redis 服务器启动以来，经过的天数
		 */
		private int days;

		/**
		 * 默认构造函数
		 */
		public Uptime(){
		}

		/**
		 * 构造函数
		 *
		 * @param seconds
		 * 		自 Redis 服务器启动以来，经过的秒数
		 * @param days
		 * 		自 Redis 服务器启动以来，经过的天数
		 */
		public Uptime(int seconds, int days){
			this.seconds = seconds;
			this.days = days;
		}

		/**
		 * 获取自 Redis 服务器启动以来，经过的秒数
		 *
		 * @return 自 Redis 服务器启动以来，经过的秒数
		 */
		public int getSeconds(){
			return seconds;
		}

		/**
		 * 设置自 Redis 服务器启动以来，经过的秒数
		 *
		 * @param seconds
		 * 		自 Redis 服务器启动以来，经过的秒数
		 */
		public void setSeconds(int seconds){
			this.seconds = seconds;
		}

		/**
		 * 获取自 Redis 服务器启动以来，经过的天数
		 *
		 * @return 自 Redis 服务器启动以来，经过的天数
		 */
		public int getDays(){
			return days;
		}

		/**
		 * 设置自 Redis 服务器启动以来，经过的天数
		 *
		 * @param days
		 * 		自 Redis 服务器启动以来，经过的天数
		 */
		public void setDays(int days){
			this.days = days;
		}

		@Override
		public String toString(){
			return "seconds=" + seconds + ", days=" + days;
		}
	}

	public enum Mode {

		STANDALONE("standalone");

		private String value;

		Mode(String value){
			this.value = value;
		}

		public String getValue(){
			return value;
		}

	}

}
