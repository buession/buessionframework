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

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.nio.file.Path;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.buession.core.Status;
import com.buession.io.Console;
import com.buession.socket.server.process.ProcessId;

/**
 * 
 *
 * @author Yong.Teng <webmaster@buession.com>
 */
public abstract class AbstractServer<T extends Channel> implements Server<T> {

	private final static String daemonName = ManagementFactory.getRuntimeMXBean().getName();

	private final static ServerBootstrap bootstrap = new ServerBootstrap();

	private static int pid = 0;

	private ApplicationConfig applicationConfig;

	private static ChannelFuture channelFuture;

	private volatile static boolean isRuning = false;

	private final static Logger logger = LoggerFactory.getLogger(AbstractServer.class);

	static {
		System.setProperty("io.netty.tmpdir", System.getProperty("java.io.tmpdir"));
	}

	public AbstractServer(final ApplicationConfig applicationConfig) {
		this.applicationConfig = applicationConfig;
		Path pidFilePath = applicationConfig.getPidFilePath();

		if (pidFilePath == null) {
			pidFilePath = (new File("/var/run/" + getName() + ".pid")).toPath();
			applicationConfig.setPidFilePath(pidFilePath);
		}
	}

	@Override
	public String getName() {
		return daemonName;
	}

	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public Status start() {
		if (isRuning() == true || pid > 0) {
			final String message = String.format("Server %s (pid: %s) already running.", getName(),
					pid);
			logger.debug(message);
			Console.message(message);

			isRuning = true;

			return Status.FAILURE;
		}

		EventLoopGroup masterGroup = new NioEventLoopGroup(applicationConfig.getMasterThreads());
		EventLoopGroup workerGroup = new NioEventLoopGroup(applicationConfig.getWorkerThreads());

		Status status = Status.FAILURE;

		try {
			bootstrap.group(masterGroup, workerGroup).channel(NioServerSocketChannel.class)
					.childHandler(applicationConfig.getChildHandler());

			LogLevel logLevel = applicationConfig.getLogLevel();
			bootstrap.handler(logLevel == null ? new LoggingHandler()
					: new LoggingHandler(logLevel));

			Map<ChannelOption, Object> options = applicationConfig.getOptions();
			if (options != null) {
				for (Map.Entry<ChannelOption, Object> e : options.entrySet()) {
					bootstrap.childOption(e.getKey(), e.getValue());
				}
			}

			Channel channel = bootstrap
					.bind(applicationConfig.getHost(), applicationConfig.getPort()).sync()
					.channel();

			pid = Integer.parseInt(daemonName.split("@")[0]);
			logger.info(
					"Server started bind host: {} at port: {} with pid ({}) file path {} success, channel=>{}.",
					applicationConfig.getHost(), applicationConfig.getPort(), pid,
					applicationConfig.getPidFilePath(), channel);

			pidWrite();

			channelFuture = channel.closeFuture().sync();

			status = Status.SUCCESS;
			isRuning = true;
		} catch (InterruptedException e) {
			logger.error("Server started bind host: {} with port: {} failure: {}.",
					applicationConfig.getHost(), applicationConfig.getPort(), e.getMessage());
			e.printStackTrace();
		} finally {
			masterGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}

		return status;
	}

	@Override
	public Status reload() {
		if (stop() == Status.FAILURE) {
			return Status.FAILURE;
		}

		return start();
	}

	@Override
	public Status restart() {
		if (stop() == Status.FAILURE) {
			return Status.FAILURE;
		}

		return start();
	}

	@Override
	public Status stop() {
		isRuning = false;

		if (channelFuture != null) {
			channelFuture.channel().close();
		}

		ProcessId processId = new ProcessId(pid, applicationConfig.getPidFilePath());

		return processId.kill();
	}

	@Override
	public int getPid() {
		return pid;
	}

	@Override
	public Status status() {
		return isRuning() == true ? Status.SUCCESS : Status.FAILURE;
	}

	@Override
	public void run() {
		start();
	}

	@Override
	public boolean isRuning() {
		return isRuning;
	}

	@Override
	public String toString() {
		return getClass().getName() + "@" + Integer.toHexString(super.hashCode())
				+ "->Server [name=" + getName() + ", pid=" + pid + "]";
	}

	private void pidWrite() {
		ProcessId processId = new ProcessId(pid, applicationConfig.getPidFilePath());
		processId.write();
	}

}