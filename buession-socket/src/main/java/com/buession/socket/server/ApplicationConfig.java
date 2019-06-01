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

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelOption;
import io.netty.handler.logging.LogLevel;

import java.io.Serializable;
import java.nio.file.Path;
import java.util.Map;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.buession.socket.utils.Constant;

/**
 * 
 *
 * @author Yong.Teng <webmaster@buession.com>
 */
public class ApplicationConfig implements Serializable {

	private static final long serialVersionUID = 1213889208132795664L;

	@NotNull(message = "Daemon Server host could not be null.")
	private String host;

	@Min(value = 1, message = "Daemon Server port could not less than 1.")
	@Max(value = 65535, message = "Daemon Server port could not greater than 65535.")
	private int port;

	@Max(value = 1, message = "The quantity of master event loop group thread could not be less than 1.")
	private int masterThreads = Constant.DEFAULT_MASTER_THREADS;

	@Max(value = 1, message = "The quantity of worker event loop group thread could not be less than 1.")
	private int workerThreads = Constant.DEFAULT_WORKER_THREADS;

	@SuppressWarnings("rawtypes")
	private Map<ChannelOption, Object> options;

	private ChannelHandler childHandler;

	private LogLevel logLevel;

	private Path pidFilePath;

	/**
	 * @return the host
	 */
	public String getHost() {
		return host;
	}

	/**
	 * @param host
	 *            the host to set
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * @return the port
	 */
	public int getPort() {
		return port;
	}

	/**
	 * @param port
	 *            the port to set
	 */
	public void setPort(int port) {
		this.port = port;
	}

	/**
	 * @return the masterThreads
	 */
	public int getMasterThreads() {
		return masterThreads;
	}

	/**
	 * @param masterThreads
	 *            the masterThreads to set
	 */
	public void setMasterThreads(int masterThreads) {
		this.masterThreads = masterThreads;
	}

	/**
	 * @return the workerThreads
	 */
	public int getWorkerThreads() {
		return workerThreads;
	}

	/**
	 * @param workerThreads
	 *            the workerThreads to set
	 */
	public void setWorkerThreads(int workerThreads) {
		this.workerThreads = workerThreads;
	}

	/**
	 * @return the options
	 */
	@SuppressWarnings("rawtypes")
	public Map<ChannelOption, Object> getOptions() {
		return options;
	}

	/**
	 * @param options
	 *            the options to set
	 */
	@SuppressWarnings("rawtypes")
	public void setOptions(Map<ChannelOption, Object> options) {
		this.options = options;
	}

	/**
	 * @return the childHandler
	 */
	public ChannelHandler getChildHandler() {
		return childHandler;
	}

	/**
	 * @param childHandler
	 *            the childHandler to set
	 */
	public void setChildHandler(ChannelHandler childHandler) {
		this.childHandler = childHandler;
	}

	/**
	 * @return the logLevel
	 */
	public LogLevel getLogLevel() {
		return logLevel;
	}

	/**
	 * @param logLevel
	 *            the logLevel to set
	 */
	public void setLogLevel(LogLevel logLevel) {
		this.logLevel = logLevel;
	}

	/**
	 * @return the pidFilePath
	 */
	public Path getPidFilePath() {
		return pidFilePath;
	}

	/**
	 * @param pidFilePath
	 *            the pidFilePath to set
	 */
	public void setPidFilePath(Path pidFilePath) {
		this.pidFilePath = pidFilePath;
	}

}