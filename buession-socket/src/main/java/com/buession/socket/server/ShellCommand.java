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

import java.util.ArrayList;
import java.util.List;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;
import org.kohsuke.args4j.spi.BooleanOptionHandler;

import com.buession.socket.codec.Command;
import com.buession.socket.utils.Constant;

/**
 * 命令行程序
 *
 * @author Yong.Teng <webmaster@buession.com>
 */
public abstract class ShellCommand {

	/**
	 * 服务端 IP 地址
	 */
	@Option(name = "--host", metaVar = "[=" + Constant.DEFAULT_HOST + "]", usage = "Set this server daemon host.", required = false)
	private String host = "0.0.0.0";

	/**
	 * 服务端端口
	 */
	@Option(name = "--port", metaVar = "[=" + Constant.DEFAULT_PORT + "]", usage = "Set this server daemon port.", required = false)
	private int port = 54321;

	/**
	 * PID 文件路径
	 */
	@Option(name = "--pid", metaVar = " ", usage = "Set this server daemon process PID file path.", required = false)
	private String pid = null;

	/**
	 * master 线程数
	 */
	@Option(name = "--M", metaVar = Constant.DEFAULT_MASTER_THREADS + "", usage = "Set the quantity of master event loop group thread for this server daemon process.", required = false)
	private int masterThreads = Constant.DEFAULT_MASTER_THREADS;

	/**
	 * worker 线程数
	 */
	@Option(name = "--W", metaVar = Constant.DEFAULT_WORKER_THREADS + "", usage = "Set the quantity of worker event loop group thread for this server daemon process.", required = false)
	private int workerThreads = Constant.DEFAULT_WORKER_THREADS;

	/**
	 * 日志文件路径
	 */
	@Option(name = "--l", metaVar = " ", usage = "Set this server daemon process log configuration file path.", required = false)
	private String logfile;

	/**
	 * 日志级别
	 */
	@Option(name = "--L", metaVar = " ", usage = "Set this server daemon process log level.", required = false)
	private String loglevel;

	/**
	 * start,reload,restart,stop
	 */
	@Option(name = "-k", metaVar = " ", usage = "start|reload|restart|stop", required = false)
	private Command command;

	/**
	 * 查看版本参数
	 */
	@Option(name = "--version", aliases = "-v", metaVar = " ", usage = "Show Server Version.", handler = BooleanOptionHandler.class, required = false)
	private boolean isVersion;

	/**
	 * 查看帮助参数
	 */
	@Option(name = "--help", aliases = "-h", metaVar = " ", usage = "Show help.", handler = BooleanOptionHandler.class, required = false)
	private boolean isHelp;

	/**
	 * 参数列表
	 */
	@Argument
	private List<String> arguments = new ArrayList<String>();

	/**
	 * 参数解析器对象
	 */
	private CmdLineParser cmdLineParser;

	/**
	 * 获取服务绑定的 IP 地址
	 * 
	 * @return 服务绑定的 IP 地址
	 */
	public String getHost() {
		return host;
	}

	/**
	 * 获取服务端口
	 * 
	 * @return 服务端口
	 */
	public int getPort() {
		return port;
	}

	/**
	 * 获取 PID 文件路径
	 * 
	 * @return PID 文件路径
	 */
	public String getPid() {
		return pid;
	}

	/**
	 * 获取 master 线程数
	 * 
	 * @return master 线程数
	 */
	public int getMasterThreads() {
		return masterThreads;
	}

	/**
	 * 获取 worker 线程数
	 * 
	 * @return worker 线程数
	 */
	public int getWorkerThreads() {
		return workerThreads;
	}

	/**
	 * 获取日志文件路径
	 * 
	 * @return 日志文件路径
	 */
	public String getLogfile() {
		return logfile;
	}

	/**
	 * 获取日志等级
	 * 
	 * @return 日志等级
	 */
	public String getLoglevel() {
		return loglevel;
	}

	/**
	 * 获取 Command
	 * 
	 * @return Command
	 */
	public Command getCommand() {
		return command;
	}

	/**
	 * 获取查看版本参数
	 * 
	 * @return 查看版本参数
	 */
	public boolean isVersion() {
		return isVersion;
	}

	/**
	 * 获取查看帮助参数
	 * 
	 * @return 查看帮助参数
	 */
	public boolean isHelp() {
		return isHelp;
	}

	/**
	 * 获取参数列表
	 * 
	 * @return 参数列表
	 */
	public List<String> getArguments() {
		return arguments;
	}

	/**
	 * 获取参数解析器对象
	 * 
	 * @return 参数解析器对象
	 */
	public CmdLineParser getCmdLineParser() {
		return cmdLineParser;
	}

	/**
	 * 设置参数解析器对象
	 * 
	 * @param cmdLineParser
	 *            参数解析器对象
	 */
	public void setCmdLineParser(CmdLineParser cmdLineParser) {
		this.cmdLineParser = cmdLineParser;
	}

}